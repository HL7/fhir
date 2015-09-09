package server

import (
	"encoding/json"
	"fmt"
	"log"
	"net/http"
	"os"
	"errors"

	"github.com/gorilla/context"
	"github.com/gorilla/mux"
	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"gopkg.in/mgo.v2/bson"
)

func <ModelName>IndexHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	defer func() {
		if r := recover(); r != nil {
			switch x := r.(type) {
			case search.UnsupportedError:
				http.Error(rw, x.Error(), http.StatusNotImplemented)
			case search.InvalidSearchError:
				http.Error(rw, x.Error(), http.StatusBadRequest)
			default:
				http.Error(rw, fmt.Sprintf("%s", x), http.StatusInternalServerError)
			}
		}
	}()

	var result []models.<ModelName>
	c := Database.C("<CollectionName>")

	r.ParseForm()
	if (len(r.Form) == 0) {
		iter := c.Find(nil).Limit(100).Iter()
		err := iter.All(&result)
		if err != nil {
			http.Error(rw, err.Error(), http.StatusInternalServerError)
		}
	} else {
		searcher := search.NewMongoSearcher(Database)
		query := search.Query{Resource: "<ModelName>", Query: r.URL.RawQuery}
		err := searcher.CreateQuery(query).All(&result)
		if err != nil {
			http.Error(rw, err.Error(), http.StatusInternalServerError)
		}
	}
	
	var <LowerCaseModelName>EntryList []models.BundleEntryComponent
	for i := range result {
		var entry models.BundleEntryComponent
		entry.Resource = &result[i]
		<LowerCaseModelName>EntryList = append(<LowerCaseModelName>EntryList, entry)
	}

	var bundle models.Bundle
	bundle.Id = bson.NewObjectId().Hex()
	bundle.Type = "searchset"
	var total = uint32(len(result))
    bundle.Total = &total
	bundle.Entry = <LowerCaseModelName>EntryList

	log.Println("Setting <LowerCaseModelName> search context")
	context.Set(r, "<ModelName>", result)
	context.Set(r, "Resource", "<ModelName>")
	context.Set(r, "Action", "search")

	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(&bundle)
}

func Load<ModelName>(r *http.Request) (*models.<ModelName>, error) {
	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	}	else {
		return nil, errors.New("Invalid id")
	}

	c := Database.C("<CollectionName>")
	result := models.<ModelName>{}
	err := c.Find(bson.M{"_id": id.Hex()}).One(&result)
	if err != nil {
		return nil, err
	}

	log.Println("Setting <LowerCaseModelName> read context")
	context.Set(r, "<ModelName>", result)
	context.Set(r, "Resource", "<ModelName>")
	return &result, nil
}

func <ModelName>ShowHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	context.Set(r, "Action", "read")
  _, err := Load<ModelName>(r)
  if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}
	rw.Header().Set("Content-Type", "application/json; charset=utf-8")
	rw.Header().Set("Access-Control-Allow-Origin", "*")
	json.NewEncoder(rw).Encode(context.Get(r, "<ModelName>"))
}

func <ModelName>CreateHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	decoder := json.NewDecoder(r.Body)
	<LowerCaseModelName> := &models.<ModelName>{}
	err := decoder.Decode(<LowerCaseModelName>)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	c := Database.C("<CollectionName>")
	i := bson.NewObjectId()
	<LowerCaseModelName>.Id = i.Hex()
	err = c.Insert(<LowerCaseModelName>)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	log.Println("Setting <LowerCaseModelName> create context")
	context.Set(r, "<ModelName>", <LowerCaseModelName>)
	context.Set(r, "Resource", "<ModelName>")
	context.Set(r, "Action", "create")

	host, err := os.Hostname()
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	rw.Header().Add("Location", "http://" + host + ":3001/<ModelName>/" + i.Hex())
}

func <ModelName>UpdateHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {

	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	}	else {
		http.Error(rw, "Invalid id", http.StatusBadRequest)
	}

	decoder := json.NewDecoder(r.Body)
	<LowerCaseModelName> := &models.<ModelName>{}
	err := decoder.Decode(<LowerCaseModelName>)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	c := Database.C("<CollectionName>")
	<LowerCaseModelName>.Id = id.Hex()
	err = c.Update(bson.M{"_id": id.Hex()}, <LowerCaseModelName>)
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
	}

	log.Println("Setting <LowerCaseModelName> update context")
	context.Set(r, "<ModelName>", <LowerCaseModelName>)
	context.Set(r, "Resource", "<ModelName>")
	context.Set(r, "Action", "update")
}

func <ModelName>DeleteHandler(rw http.ResponseWriter, r *http.Request, next http.HandlerFunc) {
	var id bson.ObjectId

	idString := mux.Vars(r)["id"]
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	}	else {
		http.Error(rw, "Invalid id", http.StatusBadRequest)
	}

	c := Database.C("<CollectionName>")

	err := c.Remove(bson.M{"_id": id.Hex()})
	if err != nil {
		http.Error(rw, err.Error(), http.StatusInternalServerError)
		return
	}

	log.Println("Setting <LowerCaseModelName> delete context")
	context.Set(r, "<ModelName>", id.Hex())
	context.Set(r, "Resource", "<ModelName>")
	context.Set(r, "Action", "delete")
}
