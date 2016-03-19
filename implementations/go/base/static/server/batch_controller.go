package server

import (
	"errors"
	"fmt"
	"net/http"
	"reflect"
	"sort"
	"strings"

	"gopkg.in/mgo.v2/bson"

	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"github.com/labstack/echo"
)

// BatchController handles FHIR batch operations via input bundles
type BatchController struct {
	DAL DataAccessLayer
}

// NewBatchController creates a new BatchController based on the passed in DAL
func NewBatchController(dal DataAccessLayer) *BatchController {
	return &BatchController{DAL: dal}
}

// Post processes and incoming batch request
func (b *BatchController) Post(c *echo.Context) error {
	bundle := &models.Bundle{}
	err := c.Bind(bundle)
	if err != nil {
		return err
	}

	// TODO: If type is batch, ensure there are no interdendent resources

	// Loop through the entries, ensuring they have a request and that we support the method,
	// while also creating a new entries array that can be sorted by method.
	entries := make([]*models.BundleEntryComponent, len(bundle.Entry))
	for i := range bundle.Entry {
		if bundle.Entry[i].Request == nil {
			// TODO: Use correct response code
			return errors.New("Entries in a batch operation require a request")
		}

		switch bundle.Entry[i].Request.Method {
		default:
			// TODO: Use correct response code
			return errors.New("Operation currently unsupported in batch requests: " + bundle.Entry[i].Request.Method)
		case "DELETE":
			if bundle.Entry[i].Request.Url == "" {
				// TODO: Use correct response code
				return errors.New("Batch DELETE must have a URL")
			}
		case "POST":
			if bundle.Entry[i].Resource == nil {
				// TODO: Use correct response code
				return errors.New("Batch POST must have a resource body")
			}
		}
		entries[i] = &bundle.Entry[i]
	}

	sort.Sort(byRequestMethod(entries))

	// Now loop through the entries, assigning new IDs to those that are POST and fixing any references
	// to reference the new ID.
	refMap := make(map[string]models.Reference)
	newIDs := make([]string, len(entries))
	for i, entry := range entries {
		if entry.Request.Method == "POST" {
			id := bson.NewObjectId().Hex()
			newIDs[i] = id
			refMap[entry.FullUrl] = models.Reference{
				Reference:    fmt.Sprintf("%s/%s", entry.Request.Url, id),
				Type:         entry.Request.Url,
				ReferencedID: id,
				External:     new(bool),
			}
			entry.FullUrl = responseURL(c.Request(), entry.Request.Url, id).String()
		}
	}
	// Update all the references to the entries (to reflect newly assigned IDs)
	updateAllReferences(entries, refMap)

	// Then make the changes in the database and update the entry response
	for i, entry := range entries {
		switch entry.Request.Method {
		case "DELETE":
			rURL := entry.Request.Url
			if strings.Contains(rURL, "/") && !strings.Contains(rURL, "?") {
				// It's a normal DELETE
				parts := strings.SplitN(entry.Request.Url, "/", 2)
				if len(parts) != 2 {
					return fmt.Errorf("Couldn't identify resource and id to delete from %s", entry.Request.Url)
				}
				if err := b.DAL.Delete(parts[1], parts[0]); err != nil && err != ErrNotFound {
					return err
				}
			} else {
				// It's a conditional (query-based) delete
				parts := strings.SplitN(entry.Request.Url, "?", 2)
				query := search.Query{Resource: parts[0], Query: parts[1]}
				if _, err := b.DAL.ConditionalDelete(query); err != nil {
					return err
				}
			}

			entry.Request = nil
			entry.Response = &models.BundleEntryResponseComponent{
				Status: "204",
			}
		case "POST":
			if err := b.DAL.PostWithID(newIDs[i], entry.Resource); err != nil {
				return err
			}
			entry.Request = nil
			entry.Response = &models.BundleEntryResponseComponent{
				Status:   "201",
				Location: entry.FullUrl,
			}
			if meta, ok := models.GetResourceMeta(entry.Resource); ok {
				entry.Response.LastModified = meta.LastUpdated
			}
		}
	}

	total := uint32(len(entries))
	bundle.Total = &total
	bundle.Type = fmt.Sprintf("%s-response", bundle.Type)

	c.Set("Bundle", bundle)
	c.Set("Resource", "Bundle")
	c.Set("Action", "batch")

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
