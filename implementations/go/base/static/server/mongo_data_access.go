package server

import (
	"errors"
	"fmt"
	"net/url"
	"reflect"
	"strconv"
	"time"

	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

// MasterSession manages a master session connected to the Mongo database.
// The user is responsible for creating and closing this master session.
// Each request to the database should first obtain a new WorkerSession,
// perform the desired operation(s), then close the WorkerSession.
type MasterSession struct {
	session *mgo.Session
	dbname  string
}

// WorkerSession is obtained from MasterSession.GetWorkerSession() and manages
// a temporary copy of the master session. A WorkerSession should be used for all
// requests to the database. When a WorkerSession is no longer needed it must
// be closed.
type WorkerSession struct {
	session *mgo.Session
	dbname  string
}

// NewMasterSession returns a new MasterSession object with an established
// session and database. Once instantiated the MasterSession object cannot
// be changed.
func NewMasterSession(session *mgo.Session, dbname string) (master *MasterSession) {
	return &MasterSession{
		session: session,
		dbname:  dbname,
	}
}

// GetWorkerSession returns a new WorkerSession with a copy of the master
// mongo session.
func (ms *MasterSession) GetWorkerSession() (worker *WorkerSession) {

	if ms.session == nil {
		return nil
	}

	return &WorkerSession{
		session: ms.session.Copy(),
		dbname:  ms.dbname,
	}
}

// SetTimeout sets the session timeout for requests made using
// the worker's session. The default timeout is 1 minute.
func (ws *WorkerSession) SetTimeout(d time.Duration) {
	if ws.session != nil {
		ws.session.SetSocketTimeout(d)
	}
}

// DB returns the mongo database available on the current session.
func (ws *WorkerSession) DB() (db *mgo.Database) {
	if ws.session != nil && ws.dbname != "" {
		db = ws.session.DB(ws.dbname)
	}
	return
}

// Close closes the master session copy used by WorkerSession
func (ws *WorkerSession) Close() {
	if ws.session != nil {
		ws.session.Close()
	}
}

// NewMongoDataAccessLayer returns an implementation of DataAccessLayer that is backed by a Mongo database
func NewMongoDataAccessLayer(ms *MasterSession, interceptors map[string]InterceptorList) DataAccessLayer {
	return &mongoDataAccessLayer{
		MasterSession: ms,
		Interceptors:  interceptors,
	}
}

type mongoDataAccessLayer struct {
	MasterSession *MasterSession
	Interceptors  map[string]InterceptorList
}

// InterceptorList is a list of interceptors registered for a given database operation
type InterceptorList []Interceptor

// Interceptor optionally executes functions on a specified resource type before and after
// a database operation involving that resource. To register an interceptor for ALL resource
// types use a "*" as the resourceType.
type Interceptor struct {
	ResourceType string
	Handler      InterceptorHandler
}

// InterceptorHandler is an interface that defines three methods that are executed on a resource
// before the database operation, after the database operation SUCCEEDS, and after the database
// operation FAILS.
type InterceptorHandler interface {
	Before(resource interface{})
	After(resource interface{})
	OnError(err error, resource interface{})
}

// invokeInterceptorsBefore invokes the interceptor list for the given resource type before a database
// operation occurs.
func (dal *mongoDataAccessLayer) invokeInterceptorsBefore(op, resourceType string, resource interface{}) {

	for _, interceptor := range dal.Interceptors[op] {
		if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
			interceptor.Handler.Before(resource)
		}
	}
}

// invokeInterceptorsAfter invokes the interceptor list for the given resource type after a database
// operation occurs and succeeds.
func (dal *mongoDataAccessLayer) invokeInterceptorsAfter(op, resourceType string, resource interface{}) {

	for _, interceptor := range dal.Interceptors[op] {
		if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
			interceptor.Handler.After(resource)
		}
	}
}

// invokeInterceptorsOnError invokes the interceptor list for the given resource type after a database
// operation occurs and fails.
func (dal *mongoDataAccessLayer) invokeInterceptorsOnError(op, resourceType string, err error, resource interface{}) {

	for _, interceptor := range dal.Interceptors[op] {
		if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
			interceptor.Handler.OnError(err, resource)
		}
	}
}

// hasInterceptorsForOpAndType checks if any interceptors are registered for a particular database operation AND resource type
func (dal *mongoDataAccessLayer) hasInterceptorsForOpAndType(op, resourceType string) bool {

	if len(dal.Interceptors[op]) > 0 {
		for _, interceptor := range dal.Interceptors[op] {
			if interceptor.ResourceType == resourceType || interceptor.ResourceType == "*" {
				// At least 1 interceptor is registered for this database operation and resource type
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

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	collection := worker.DB().C(models.PluralizeLowerResourceName(resourceType))
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

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(bsonID.Hex())
	resourceType := reflect.TypeOf(resource).Elem().Name()
	collection := worker.DB().C(models.PluralizeLowerResourceName(resourceType))
	updateLastUpdatedDate(resource)

	dal.invokeInterceptorsBefore("Create", resourceType, resource)

	err = collection.Insert(resource)

	if err == nil {
		dal.invokeInterceptorsAfter("Create", resourceType, resource)
	} else {
		dal.invokeInterceptorsOnError("Create", resourceType, err, resource)
	}

	return convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) Put(id string, resource interface{}) (createdNew bool, err error) {
	bsonID, err := convertIDToBsonID(id)
	if err != nil {
		return false, convertMongoErr(err)
	}

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	resourceType := reflect.TypeOf(resource).Elem().Name()
	collection := worker.DB().C(models.PluralizeLowerResourceName(resourceType))
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(bsonID.Hex())
	updateLastUpdatedDate(resource)

	if dal.hasInterceptorsForOpAndType("Update", resourceType) {
		oldResource, getError := dal.Get(id, resourceType)
		if getError == nil {
			dal.invokeInterceptorsBefore("Update", resourceType, oldResource)
		}
	}

	info, err := collection.UpsertId(bsonID.Hex(), resource)

	if err == nil {
		createdNew = (info.Updated == 0)
		if createdNew {
			dal.invokeInterceptorsAfter("Create", resourceType, resource)
		} else {
			dal.invokeInterceptorsAfter("Update", resourceType, resource)
		}
	} else {
		dal.invokeInterceptorsOnError("Update", resourceType, err, resource)
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

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	var resource interface{}
	var getError error
	hasInterceptor := dal.hasInterceptorsForOpAndType("Delete", resourceType)

	if hasInterceptor {
		// Although this is a delete operation we need to get the resource first so we can
		// run any interceptors on the resource before it's deleted.
		resource, getError = dal.Get(id, resourceType)
		dal.invokeInterceptorsBefore("Delete", resourceType, resource)
	}

	collection := worker.DB().C(models.PluralizeLowerResourceName(resourceType))
	err = collection.RemoveId(bsonID.Hex())

	if hasInterceptor {
		if err == nil && getError == nil {
			dal.invokeInterceptorsAfter("Delete", resourceType, resource)
		} else {
			dal.invokeInterceptorsOnError("Delete", resourceType, err, resource)
		}
	}

	return convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) ConditionalDelete(query search.Query) (count int, err error) {

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	IDsToDelete, err := dal.FindIDs(query)
	if err != nil {
		return 0, err
	}
	// There is the potential here for the delete to fail if the slice of IDs
	// is too large (exceeding Mongo's 16MB document size limit).
	deleteQuery := bson.M{"_id": bson.M{"$in": IDsToDelete}}
	resourceType := query.Resource
	collection := worker.DB().C(models.PluralizeLowerResourceName(resourceType))

	if dal.hasInterceptorsForOpAndType("Delete", resourceType) {
		/* Interceptors for a conditional delete are tricky since an interceptor is only run
		   AFTER the database operation and only on resources that were SUCCESSFULLY deleted. We use
		   the following approach:
		   1. Bulk delete those resources by ID
		   2. Search again using the SAME query, to verify that those resources were in fact deleted
		   3. Run the interceptor(s) on all resources that ARE NOT in the second search (since they were truly deleted)
		*/

		// get the resources that are about to be deleted
		var bundle *models.Bundle
		bundle, err = dal.Search(url.URL{}, query) // the baseURL argument here does not matter

		if err == nil {
			for _, elem := range bundle.Entry {
				dal.invokeInterceptorsBefore("Delete", resourceType, elem.Resource)
			}

			// Do the bulk delete by ID.
			info, err := collection.RemoveAll(deleteQuery)
			deletedIds := make([]string, len(IDsToDelete))

			if info != nil {
				count = info.Removed
			}

			if err != nil {
				for _, elem := range bundle.Entry {
					dal.invokeInterceptorsOnError("Delete", resourceType, err, elem.Resource)
				}
				return count, convertMongoErr(err)
			}

			var searchErr error

			if count < len(IDsToDelete) {
				// Some but not all resources were removed, so use the original search query
				// to see which resources are left.
				var failBundle *models.Bundle
				failBundle, searchErr = dal.Search(url.URL{}, query)
				deletedIds = setDiff(IDsToDelete, getResourceIdsFromBundle(failBundle))
			} else {
				// All resources were successfully removed
				deletedIds = IDsToDelete
			}

			if searchErr == nil {
				for _, elem := range bundle.Entry {
					id := reflect.ValueOf(elem.Resource).Elem().FieldByName("Id").String()

					if elementInSlice(id, deletedIds) {
						// This resource was confirmed deleted
						dal.invokeInterceptorsAfter("Delete", resourceType, elem.Resource)
					} else {
						// This resource was not confirmed deleted, which is an error
						resourceErr := errors.New(fmt.Sprintf("ConditionalDelete: failed to delete resource %s with ID %s", resourceType, id))
						dal.invokeInterceptorsOnError("Delete", resourceType, resourceErr, elem.Resource)
					}
				}
			}
			return count, convertMongoErr(err)
		}
	}

	// do the bulk delete the usual way
	info, err := collection.RemoveAll(deleteQuery)
	if info != nil {
		count = info.Removed
	}

	return count, convertMongoErr(err)
}

func (dal *mongoDataAccessLayer) Search(baseURL url.URL, searchQuery search.Query) (*models.Bundle, error) {

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

	searcher := search.NewMongoSearcher(worker.DB())

	result, total, err := searcher.Search(searchQuery)
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

		if searchQuery.UsesIncludes() || searchQuery.UsesRevIncludes() {
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
	bundle.Total = &total

	// Add links for paging
	bundle.Link = generatePagingLinks(baseURL, searchQuery, total)

	return &bundle, nil
}

func (dal *mongoDataAccessLayer) FindIDs(searchQuery search.Query) (IDs []string, err error) {

	worker := dal.MasterSession.GetWorkerSession()
	defer worker.Close()

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
	searcher := search.NewMongoSearcher(worker.DB())
	results, _, err := searcher.Search(newQuery)
	if err != nil {
		return nil, convertMongoErr(err)
	}

	resultsVal := reflect.ValueOf(results).Elem()
	IDs = make([]string, resultsVal.Len())

	for i := 0; i < resultsVal.Len(); i++ {
		IDs[i] = resultsVal.Index(i).FieldByName("Id").String()
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
