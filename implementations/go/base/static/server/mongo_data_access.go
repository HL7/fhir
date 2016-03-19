package server

import (
	"net/url"
	"reflect"
	"strconv"
	"time"

	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

// NewMongoDataAccessLayer returns an implementation of DataAccessLayer that is backed by a Mongo database
func NewMongoDataAccessLayer(db *mgo.Database) DataAccessLayer {
	return &mongoDataAccessLayer{Database: db}
}

type mongoDataAccessLayer struct {
	Database *mgo.Database
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
	return convertMongoErr(collection.Insert(resource))
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
	}
	return createdNew, convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) Delete(id, resourceType string) error {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return convertMongoErr(err)
	}

	collection := dal.Database.C(models.PluralizeLowerResourceName(resourceType))
	return convertMongoErr(collection.RemoveId(bsonID.Hex()))
}

func (dal *mongoDataAccessLayer) ConditionalDelete(query search.Query) (count int, err error) {
	searcher := search.NewMongoSearcher(dal.Database)
	queryObject := searcher.CreateQueryObject(query)

	collection := dal.Database.C(models.PluralizeLowerResourceName(query.Resource))
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
