package server

// TODO: This code can and should be cleaned up.  For now, it is more or less a port of the code that used to exist
// for every resource controller.

import (
	"encoding/json"
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"reflect"
	"strings"

	"github.com/gorilla/context"
	"github.com/gorilla/mux"
	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"gopkg.in/mgo.v2/bson"
)

type ResourceController struct {
	Name string
}

func (rc *ResourceController) IndexHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	defer func() {
		if r := recover(); r != nil {
			rw.Header().Set("Content-Type", "application/json; charset=utf-8")
			switch x := r.(type) {
			case search.Error:
				rw.WriteHeader(x.HTTPStatus)
				json.NewEncoder(rw).Encode(x.OperationOutcome)
				return
			default:
				outcome := &models.OperationOutcome{
					Issue: []models.OperationOutcomeIssueComponent{
						models.OperationOutcomeIssueComponent{
							Severity: "fatal",
							Code:     "exception",
						},
					},
				}
				rw.WriteHeader(http.StatusInternalServerError)
				json.NewEncoder(rw).Encode(outcome)
			}
		}
	}()

	result := models.NewSliceForResourceName(rc.Name, 0, 0)

	// Create and execute the Mongo query based on the http query params
	searcher := search.NewMongoSearcher(Database)
	searchQuery := search.Query{Resource: rc.Name, Query: r.URL.RawQuery}
	mgoQuery := searcher.CreateQuery(searchQuery)

	// Horrible, horrible hack (for now) to ensure patients are sorted by name.  This is needed by
	// the frontend, else paging won't work correctly.  This should be removed when the general
	// sorting feature is implemented.
	if rc.Name == "Patient" {
		// To add insult to injury, mongo will not let us sort by family *and* given name:
		// Executor error: BadValue cannot sort with keys that are parallel arrays
		mgoQuery = mgoQuery.Sort("name.0.family.0" /*", name.0.given.0"*/, "_id")
	}

	err := mgoQuery.All(result)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	var entryList []models.BundleEntryComponent
	resultVal := reflect.ValueOf(result).Elem()
	for i := 0; i < resultVal.Len(); i++ {
		var entry models.BundleEntryComponent
		entry.Resource = resultVal.Index(i).Addr().Interface()
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
			http.Error(rw, err.Error(), http.StatusInternalServerError)
		}
		total = uint32(intTotal)
	} else {
		// We can figure out the total by adding the offset and # results returned
		total = uint32(options.Offset + resultVal.Len())
	}
	bundle.Total = &total

	// Add links for paging
	bundle.Link = generatePagingLinks(r, searchQuery, total)

	context.Set(r, rc.Name, reflect.ValueOf(result).Elem().Interface())
	context.Set(r, "Resource", rc.Name)
	context.Set(r, "Action", "search")

	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(&bundle)
}

func generatePagingLinks(r *http.Request, query search.Query, total uint32) []models.BundleLinkComponent {
	links := make([]models.BundleLinkComponent, 0, 5)
	values := query.NormalizedQueryValues(false)
	options := query.Options()
	count := uint32(options.Count)
	offset := uint32(options.Offset)

	// First create the base URL for paging
	baseURL := responseURL(r, query.Resource)

	// Self link
	links = append(links, newLink("self", baseURL, values, count, offset))

	// First link
	links = append(links, newLink("first", baseURL, values, count, uint32(0)))

	// Previous link
	if offset > uint32(0) {
		newOffset := offset - count
		// Handle case where paging is uneven (e.g., count=10&offset=5)
		if count > offset {
			newOffset = uint32(0)
		}
		links = append(links, newLink("previous", baseURL, values, offset-newOffset, newOffset))
	}

	// Next Link
	if total > (offset + count) {
		links = append(links, newLink("next", baseURL, values, count, offset+count))
	}

	// Last Link
	remainder := (total - offset) % count
	if total < offset {
		remainder = uint32(0)
	}
	newOffset := total - remainder
	if remainder == uint32(0) && total > count {
		newOffset = total - count
	}
	links = append(links, newLink("last", baseURL, values, count, newOffset))

	return links
}

func newLink(relation string, baseURL *url.URL, values url.Values, count uint32, offset uint32) models.BundleLinkComponent {
	values.Set(search.CountParam, fmt.Sprint(count))
	values.Set(search.OffsetParam, fmt.Sprint(offset))
	baseURL.RawQuery = values.Encode()
	return models.BundleLinkComponent{Relation: relation, Url: baseURL.String()}
}

func (rc *ResourceController) LoadResource(r *http.Request) (interface{}, error) {
	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		return nil, errors.New("Invalid id")
	}

	c := Database.C(models.PluralizeLowerResourceName(rc.Name))
	result := models.NewStructForResourceName(rc.Name)
	err := c.Find(bson.M{"_id": id.Hex()}).One(result)
	if err != nil {
		return nil, err
	}

	context.Set(r, rc.Name, result)
	context.Set(r, "Resource", rc.Name)
	return result, nil
}

func (rc *ResourceController) ShowHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	context.Set(r, "Action", "read")
	_, err := rc.LoadResource(r)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}
	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(context.Get(r, rc.Name))
}

func (rc *ResourceController) CreateHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	decoder := json.NewDecoder(r.Body)
	resource := models.NewStructForResourceName(rc.Name)
	err := decoder.Decode(resource)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	c := Database.C(models.PluralizeLowerResourceName(rc.Name))
	i := bson.NewObjectId()
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(i.Hex())
	err = c.Insert(resource)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	context.Set(r, rc.Name, resource)
	context.Set(r, "Resource", rc.Name)
	context.Set(r, "Action", "create")

	rw.Header().Add("Location", responseURL(r, rc.Name, i.Hex()).String())
	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	rw.WriteHeader(http.StatusCreated)
	json.NewEncoder(rw).Encode(resource)
}

func (rc *ResourceController) UpdateHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {

	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		http.Error(rw, "Invalid id", http.StatusBadRequest)
	}

	decoder := json.NewDecoder(r.Body)
	resource := models.NewStructForResourceName(rc.Name)
	err := decoder.Decode(resource)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	c := Database.C(models.PluralizeLowerResourceName(rc.Name))
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(id.Hex())
	err = c.Update(bson.M{"_id": id.Hex()}, resource)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	context.Set(r, rc.Name, resource)
	context.Set(r, "Resource", rc.Name)
	context.Set(r, "Action", "update")

	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(resource)
}

func (rc *ResourceController) DeleteHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		http.Error(rw, "Invalid id", http.StatusBadRequest)
	}

	c := Database.C(models.PluralizeLowerResourceName(rc.Name))

	err := c.Remove(bson.M{"_id": id.Hex()})
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
		return
	}

	context.Set(r, rc.Name, id.Hex())
	context.Set(r, "Resource", rc.Name)
	context.Set(r, "Action", "delete")
}

func responseURL(r *http.Request, paths ...string) *url.URL {
	responseURL := url.URL{}
	if r.TLS == nil {
		responseURL.Scheme = "http"
	} else {
		responseURL.Scheme = "https"
	}
	responseURL.Host = r.Host
	responseURL.Path = fmt.Sprintf("/%s", strings.Join(paths, "/"))

	return &responseURL
}
