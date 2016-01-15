package server

import (
	"errors"
	"fmt"
	"net/http"
	"reflect"
	"sort"
	"strings"
	"time"

	"gopkg.in/mgo.v2/bson"

	"github.com/intervention-engine/fhir/models"
	"github.com/labstack/echo"
)

func BatchHandler(c *echo.Context) error {
	bundle := &models.Bundle{}
	err := c.Bind(bundle)
	if err != nil {
		return err
	}

	// TODO: If type is batch, ensure there are no interdendent resources

	entries := make([]*models.BundleEntryComponent, len(bundle.Entry))
	for i := range bundle.Entry {
		if bundle.Entry[i].Request == nil {
			// TODO: Use correct response code
			return errors.New("Entries in a batch operation require a request")
		} else if bundle.Entry[i].Request.Method != "POST" {
			// TODO: Use correct response code
			return errors.New("Only POST requests are currently supported")
		} else if strings.Contains(bundle.Entry[i].Request.Url, "/") {
			// TODO: Use correct response code
			return errors.New("Updating resources is not currently allowed")
		} else if bundle.Entry[i].Resource == nil {
			// TODO: Use correct response code
			return errors.New("Batch POST must have a resource body")
		}
		entries[i] = &bundle.Entry[i]
	}

	// Kind of pointless since we only support POST, but will be useful soon
	sort.Sort(byRequestMethod(entries))

	// Create a map containing references that can be looked up by passed in FullURL.  This allows the
	// existing references to be updated to new references (using newly assigned IDs).
	refMap := make(map[string]models.Reference)
	for _, entry := range entries {
		id := bson.NewObjectId()
		refMap[entry.FullUrl] = models.Reference{
			Reference:    fmt.Sprintf("%s/%s", entry.Request.Url, id.Hex()),
			Type:         entry.Request.Url,
			ReferencedID: id.Hex(),
			External:     new(bool),
		}
		// Update the entry with the new FullURL, Id, and LastUpdated
		entry.FullUrl = responseURL(c.Request(), entry.Request.Url, id.Hex()).String()
		reflect.ValueOf(entry.Resource).Elem().FieldByName("Id").SetString(id.Hex())
		UpdateLastUpdatedDate(entry.Resource)
	}
	// Update all the references to the entries (to reflect newly assigned IDs)
	updateAllReferences(entries, refMap)

	// Then store all of the resources in the database and update the entry response
	for _, entry := range entries {
		c := Database.C(models.PluralizeLowerResourceName(entry.Request.Url))
		err = c.Insert(entry.Resource)
		if err != nil {
			return err
		}

		entry.Request = nil
		entry.Response = &models.BundleEntryResponseComponent{
			Status:   "201",
			Location: entry.FullUrl,
			LastModified: &models.FHIRDateTime{
				Time:      time.Now(),
				Precision: models.Timestamp,
			},
		}
	}

	total := uint32(len(entries))
	bundle.Total = &total
	bundle.Type = fmt.Sprintf("%s-response", bundle.Type)

	c.Set("Bundle", bundle)
	c.Set("Resource", "Bundle")
	c.Set("Action", "create")

	// Send the response

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusOK, bundle)
}

func updateAllReferences(entries []*models.BundleEntryComponent, refMap map[string]models.Reference) {
	// First, get all the references by reflecting through the fields of each model
	var refs []*models.Reference
	for _, entry := range entries {
		model := entry.Resource
		if model != nil {
			entryRefs := findRefsInValue(reflect.ValueOf(model))
			refs = append(refs, entryRefs...)
		}
	}
	// Then iterate through and update as necessary
	for _, ref := range refs {
		newRef, found := refMap[ref.Reference]
		if found {
			*ref = newRef
		}
	}
}

func findRefsInValue(val reflect.Value) []*models.Reference {
	var refs []*models.Reference

	// Dereference pointers in order to simplify things
	if val.Kind() == reflect.Ptr {
		val = val.Elem()
	}

	// Make sure it's a valid thing, else return right away
	if !val.IsValid() {
		return refs
	}

	// Handle it if it's a ref, otherwise iterate its members for refs
	if val.Type() == reflect.TypeOf(models.Reference{}) {
		refs = append(refs, val.Addr().Interface().(*models.Reference))
	} else if val.Kind() == reflect.Struct {
		for i := 0; i < val.NumField(); i++ {
			subRefs := findRefsInValue(val.Field(i))
			refs = append(refs, subRefs...)
		}
	} else if val.Kind() == reflect.Slice {
		for i := 0; i < val.Len(); i++ {
			subRefs := findRefsInValue(val.Index(i))
			refs = append(refs, subRefs...)
		}
	}

	return refs
}

// Support sorting by request method, as defined in the spec
type byRequestMethod []*models.BundleEntryComponent

func (e byRequestMethod) Len() int {
	return len(e)
}
func (e byRequestMethod) Swap(i, j int) {
	e[i], e[j] = e[j], e[i]
}
func (e byRequestMethod) Less(i, j int) bool {
	methodMap := map[string]int{"DELETE": 0, "POST": 1, "PUT": 2, "GET": 3}
	return methodMap[e[i].Request.Method] < methodMap[e[j].Request.Method]
}
