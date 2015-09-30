package server

// TODO: This code can and should be cleaned up.  For now, it is more or less a port of the code that used to exist
// for every resource controller.

import (
	"encoding/json"
	"errors"
	"net/http"
	"os"
	"reflect"

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
	c := Database.C(models.PluralizeLowerResourceName(rc.Name))

	r.ParseForm()
	if len(r.Form) == 0 {
		iter := c.Find(nil).Limit(100).Iter()
		err := iter.All(result)
		if err != nil {
			http.Error(rw, err.Error(), http.StatusInternalServerError)
		}
	} else {
		searcher := search.NewMongoSearcher(Database)
		query := search.Query{Resource: rc.Name, Query: r.URL.RawQuery}
		err := searcher.CreateQuery(query).All(result)
		if err != nil {
			http.Error(rw, err.Error(), http.StatusInternalServerError)
		}
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
	var total = uint32(resultVal.Len())
	bundle.Total = &total
	bundle.Entry = entryList

	context.Set(r, rc.Name, reflect.ValueOf(result).Elem().Interface())
	context.Set(r, "Resource", rc.Name)
	context.Set(r, "Action", "search")

	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(&bundle)
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

	host, err := os.Hostname()
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}
	rw.Header().Add("Location", "http://"+host+":3001/"+rc.Name+"/"+i.Hex())
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
