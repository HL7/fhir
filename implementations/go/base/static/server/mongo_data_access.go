package server

import (
	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
	"net/url"
	"reflect"
	"strconv"
	"time"
)

// NewMongoDataAccessLayer returns an implementation of DataAccessLayer that is backed by a Mongo database
func NewMongoDataAccessLayer(db *mgo.Database, interceptors map[string]InterceptorList) DataAccessLayer {
	return &mongoDataAccessLayer{
		Database:     db,
		Interceptors: interceptors,
	}
}

type mongoDataAccessLayer struct {
	Database     *mgo.Database
	Interceptors map[string]InterceptorList
}

// Interceptor executes a function on a specified resource type immediately AFTER
// the resource is modified in the database. To register an interceptor for ALL resource
// types use a "*" as the resourceType.
type Interceptor struct {
	ResourceType string
	Handler      InterceptorHandler
}

// InterceptorHandler is a function that is executed on a single FHIR resource
type InterceptorHandler func(interface{})

// InterceptorList is a list of interceptors registered for a given HTTP verb
type InterceptorList []Interceptor

// invokeInterceptors invokes the interceptor list for a particular HTTP verb and resource type.
// Supported verbs are: POST, PUT, DELETE
func (dal *mongoDataAccessLayer) invokeInterceptors(httpVerb, resourceType string, resource interface{}) {

	for _, interceptor := range dal.Interceptors[httpVerb] {
		if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
			interceptor.Handler(resource)
		}
	}
}

// hasInterceptorsForVerbAndType checks if any interceptors are registered for a particular HTTP verb AND resource type
func (dal *mongoDataAccessLayer) hasInterceptorsForVerbAndType(httpVerb, resourceType string) bool {

	if len(dal.Interceptors[httpVerb]) > 0 {
		for _, interceptor := range dal.Interceptors[httpVerb] {
			if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
				// At least 1 interceptor is registered for this verb and resource type
				return true
			}
		}
	}
	return false
}

func (dal *mongoDataAccessLayer) Get(id, resourceType string) (result interface{}, err error) {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return nil, convertMongoErr(err)
	}

	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	result = models.NewStructForResourceName(resourceType)
	if err = collection.FindId(bsonID.Hex()).One(result); err != nil {
		return nil, convertMongoErr(err)
	}
	return
}

func (dal *mongoDataAccessLayer) Post(resource interface{}) (id string, err error) {
	id = bson.NewObjectId().Hex()
	err = convertMongoErr(dal.PostWithID(id, resource))
	return
}

func (dal *mongoDataAccessLayer) PostWithID(id string, resource interface{}) error {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return convertMongoErr(err)
	}

	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(bsonID.Hex())
	resourceType := reflect.TypeOf(resource).Elem().Name()
	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	updateLastUpdatedDate(resource)
	err = collection.Insert(resource)

	if err == nil {
		dal.invokeInterceptors("POST", resourceType, resource)
	}
	return convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) Put(id string, resource interface{}) (createdNew bool, err error) {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return false, convertMongoErr(err)
	}

	resourceType := reflect.TypeOf(resource).Elem().Name()
	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(bsonID.Hex())
	updateLastUpdatedDate(resource)

	info, err := collection.UpsertId(bsonID.Hex(), resource)
	if err == nil {
		createdNew = (info.Updated == 0)
		dal.invokeInterceptors("PUT", resourceType, resource)
	}

	return createdNew, convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) ConditionalPut(query search.Query, resource interface{}) (id string, createdNew bool, err error) {
	if IDs, err := dal.FindIDs(query); err == nil {
		switch len(IDs) {
		case 0:
			id = bson.NewObjectId().Hex()
		case 1:
			id = IDs[0]
		default:
			return "", false, ErrMultipleMatches
		}
	} else {
		return "", false, err
	}

	createdNew, err = dal.Put(id, resource)
	return id, createdNew, err
}

func (dal *mongoDataAccessLayer) Delete(id, resourceType string) error {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return convertMongoErr(err)
	}

	var resource interface{}
	var getError error
	hasInterceptor := dal.hasInterceptorsForVerbAndType("DELETE", resourceType)

	if hasInterceptor {
		// Although this is a delete operation we need to get the resource first so we can
		// run any interceptors on the resource before it's deleted.
		resource, getError = dal.Get(id, resourceType)
	}

	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	err = collection.RemoveId(bsonID.Hex())

	if err == nil && hasInterceptor {
		if getError == nil {
			dal.invokeInterceptors("DELETE", resourceType, resource)
		}
	}

	return convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) ConditionalDelete(query search.Query) (count int, err error) {
	resourceType := query.Resource
	searcher := search.NewMongoSearcher(dal.Database)
	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	defaultQueryObject := searcher.CreateQueryObject(query)
	var queryObject bson.M

	if dal.hasInterceptorsForVerbAndType("DELETE", resourceType) {
		/* Interceptors for a conditional delete are tricky since an interceptor is only run
		   AFTER the database operation and only on resources that were SUCCESSFULLY deleted. We use
		   the following approach:
		   1. Search for all matching resources by the original query (returns a bundle of resources)
		   2. Bulk delete those resources by ID
		   3. Search again using the SAME query, to verify that those resources were in fact deleted
		   4. Run the interceptor(s) on all resources that ARE NOT in the second search (since they were truly deleted)
		*/

		// get the resources that are about to be deleted
		var bundle *models.Bundle
		bundle, err = dal.Search(url.URL{}, query) // the baseURL argument here does not matter

		if err == nil {
			resourceIds := getResourceIdsFromBundle(bundle)
			queryObject = bson.M{ "_id": bson.M{"$in": resourceIds} }

			// do the bulk delete by ID
			info, err := collection.RemoveAll(queryObject)
			successfulIds := make([]string, len(resourceIds))

			if info != nil {
				count = info.Removed
			}

			if err != nil {
				return count, convertMongoErr(err)
			}

			var failBundle *models.Bundle
			var searchErr error

			if count < len(resourceIds) {
				// Not all resources were removed...
				failBundle, searchErr = dal.Search(url.URL{}, query) // original search query
				successfulIds = setDiff(resourceIds, getResourceIdsFromBundle(failBundle))
			} else {
				// All resources were successfully removed
				successfulIds = resourceIds
			}

			if searchErr == nil {
				for _, elem := range bundle.Entry {
					id := reflect.ValueOf(elem.Resource).Elem().FieldByName("Id").String()

					if elementInSlice(id, successfulIds) {
						// This resource was confirmed deleted
						dal.invokeInterceptors("DELETE", resourceType, elem.Resource)
					}
				}
			}
			return count, convertMongoErr(err)
		}
	} else {
		// No interceptor(s) registered, use the default conditional query
		queryObject = defaultQueryObject
	}

	// do the bulk delete the usual way
	info, err := collection.RemoveAll(queryObject)
	if info != nil {
		count = info.Removed
	}

	return count, convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) Search(baseURL url.URL, searchQuery search.Query) (*models.Bundle, error) {
	searcher := search.NewMongoSearcher(dal.Database)

	var result interface{}
	var err error
	usesIncludes := len(searchQuery.Options().Include) > 0
	usesRevIncludes := len(searchQuery.Options().RevInclude) > 0
	// Only use (slower) pipeline if it is needed
	if usesIncludes || usesRevIncludes {
		result = models.NewSlicePlusForResourceName(searchQuery.Resource, 0, 0)
		err = searcher.CreatePipeline(searchQuery).All(result)
	} else {
		result = models.NewSliceForResourceName(searchQuery.Resource, 0, 0)
		err = searcher.CreateQuery(searchQuery).All(result)
	}
	if err != nil {
		return nil, convertMongoErr(err)
	}

	includesMap := make(map[string]interface{})
	var entryList []models.BundleEntryComponent
	resultVal := reflect.ValueOf(result).Elem()
	for i := 0; i < resultVal.Len(); i++ {
		var entry models.BundleEntryComponent
		entry.Resource = resultVal.Index(i).Addr().Interface()
		entry.Search = &models.BundleEntrySearchComponent{Mode: "match"}
		entryList = append(entryList, entry)

		if usesIncludes || usesRevIncludes {
			rpi, ok := entry.Resource.(ResourcePlusRelatedResources)
			if ok {
				for k, v := range rpi.GetIncludedAndRevIncludedResources() {
					includesMap[k] = v
				}
			}
		}
	}

	for _, v := range includesMap {
		var entry models.BundleEntryComponent
		entry.Resource = v
		entry.Search = &models.BundleEntrySearchComponent{Mode: "include"}
		entryList = append(entryList, entry)
	}

	var bundle models.Bundle
	bundle.Id = bson.NewObjectId().Hex()
	bundle.Type = "searchset"
	bundle.Entry = entryList

	options := searchQuery.Options()

	// Need to get the true total (not just how many were returned in this response)
	var total uint32
	if resultVal.Len() == options.Count || resultVal.Len() == 0 {
		// Need to get total count from the server, since there may be more or the offset was too high
		intTotal, err := searcher.CreateQueryWithoutOptions(searchQuery).Count()
		if err != nil {
			return nil, convertMongoErr(err)
		}
		total = uint32(intTotal)
	} else {
		// We can figure out the total by adding the offset and # results returned
		total = uint32(options.Offset + resultVal.Len())
	}
	bundle.Total = &total

	// Add links for paging
	bundle.Link = generatePagingLinks(baseURL, searchQuery, total)

	return &bundle, nil
}

func (dal *mongoDataAccessLayer) FindIDs(searchQuery search.Query) (IDs []string, err error) {
	// First create a new query with the unsupported query options filtered out
	oldParams := searchQuery.URLQueryParameters(false)
	newParams := search.URLQueryParameters{}
	for _, param := range oldParams.All() {
		switch param.Key {
		case search.ContainedParam, search.ContainedTypeParam, search.ElementsParam, search.IncludeParam,
			search.RevIncludeParam, search.SummaryParam:
			continue
		default:
			newParams.Add(param.Key, param.Value)
		}
	}
	newQuery := search.Query{Resource: searchQuery.Resource, Query: newParams.Encode()}

	// Now search on that query, unmarshaling to a temporary struct and converting results to []string
	searcher := search.NewMongoSearcher(dal.Database)
	mgoQuery := searcher.CreateQuery(newQuery).Select(bson.M{"_id": 1})
	results := []struct {
		ID string `bson:"_id"`
	}{}
	if err := mgoQuery.All(&results); err != nil {
		return nil, err
	}
	IDs = make([]string, len(results))
	for i := range results {
		IDs[i] = results[i].ID
	}

	return IDs, nil
}

// ResourcePlusRelatedResources is an interface to capture those structs that implement the functions for
// getting included and rev-included resources
type ResourcePlusRelatedResources interface {
	GetIncludedAndRevIncludedResources() map[string]interface{}
	GetIncludedResources() map[string]interface{}
	GetRevIncludedResources() map[string]interface{}
}

func generatePagingLinks(baseURL url.URL, query search.Query, total uint32) []models.BundleLinkComponent {
	links := make([]models.BundleLinkComponent, 0, 5)
	params := query.URLQueryParameters(true)
	offset := 0
	if pOffset := params.Get(search.OffsetParam); pOffset != "" {
		offset, _ = strconv.Atoi(pOffset)
		if offset < 0 {
			offset = 0
		}
	}
	count := search.NewQueryOptions().Count
	if pCount := params.Get(search.CountParam); pCount != "" {
		count, _ = strconv.Atoi(pCount)
		if count < 1 {
			count = search.NewQueryOptions().Count
		}
	}

	// Self link
	links = append(links, newLink("self", baseURL, params, offset, count))

	// First link
	links = append(links, newLink("first", baseURL, params, 0, count))

	// Previous link
	if offset > 0 {
		prevOffset := offset - count
		// Handle case where paging is uneven (e.g., count=10&offset=5)
		if prevOffset < 0 {
			prevOffset = 0
		}
		prevCount := offset - prevOffset
		links = append(links, newLink("previous", baseURL, params, prevOffset, prevCount))
	}

	// Next Link
	if total > uint32(offset+count) {
		nextOffset := offset + count
		links = append(links, newLink("next", baseURL, params, nextOffset, count))
	}

	// Last Link
	remainder := (int(total) - offset) % count
	if int(total) < offset {
		remainder = 0
	}
	newOffset := int(total) - remainder
	if remainder == 0 && int(total) > count {
		newOffset = int(total) - count
	}
	links = append(links, newLink("last", baseURL, params, newOffset, count))

	return links
}

func newLink(relation string, baseURL url.URL, params search.URLQueryParameters, offset int, count int) models.BundleLinkComponent {
	params.Set(search.OffsetParam, strconv.Itoa(offset))
	params.Set(search.CountParam, strconv.Itoa(count))
	baseURL.RawQuery = params.Encode()
	return models.BundleLinkComponent{Relation: relation, Url: baseURL.String()}
}

func convertIDToBsonID(id string) (bson.ObjectId, error) {
	if bson.IsObjectIdHex(id) {
		return bson.ObjectIdHex(id), nil
	}
	return bson.ObjectId(""), models.NewOperationOutcome("fatal", "exception", "Id must be a valid BSON ObjectId")
}

func updateLastUpdatedDate(resource interface{}) {
	m := reflect.ValueOf(resource).Elem().FieldByName("Meta")
	if m.IsNil() {
		newMeta := &models.Meta{}
		m.Set(reflect.ValueOf(newMeta))
	}
	now := &models.FHIRDateTime{Time: time.Now(), Precision: models.Timestamp}
	m.Elem().FieldByName("LastUpdated").Set(reflect.ValueOf(now))
}

func convertMongoErr(err error) error {
	switch err {
	default:
		return err
	case mgo.ErrNotFound:
		return ErrNotFound
	}
}

// getResourceIdsFromBundle parses a slice of BSON resource IDs from a valid
// bundle of resources (typically returned from a search operation). Order is
// preserved.
func getResourceIdsFromBundle(bundle *models.Bundle) []string {
	resourceIds := make([]string, int(*bundle.Total))
	for i, elem := range bundle.Entry {
		resourceIds[i] = reflect.ValueOf(elem.Resource).Elem().FieldByName("Id").String()
	}
	return resourceIds
}

// setDiff returns all the elements in slice X that are not in slice Y
func setDiff(X, Y []string) []string {
	m := make(map[string]int)

    for _, y := range Y {
        m[y]++
    }

    var ret []string
    for _, x := range X {
        if m[x] > 0 {
            m[x]--
            continue
        }
        ret = append(ret, x)
    }

    return ret
}

// elementInSlice tests if a string element is in a larger slice of strings
func elementInSlice(element string, slice []string) bool {
	for _, el := range slice {
		if element == el {
			return true
		}
	}
	return false
}
