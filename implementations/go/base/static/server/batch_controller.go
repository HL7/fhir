package server

import (
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"reflect"
	"regexp"
	"sort"
	"strings"

	"gopkg.in/mgo.v2/bson"

	"github.com/gin-gonic/gin"
	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
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
func (b *BatchController) Post(c *gin.Context) {
	bundle := &models.Bundle{}
	err := FHIRBind(c, bundle)
	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	// TODO: If type is batch, ensure there are no interdependent resources

	// Loop through the entries, ensuring they have a request and that we support the method,
	// while also creating a new entries array that can be sorted by method.
	entries := make([]*models.BundleEntryComponent, len(bundle.Entry))
	for i := range bundle.Entry {
		if bundle.Entry[i].Request == nil {
			c.AbortWithError(http.StatusBadRequest, errors.New("Entries in a batch operation require a request"))
			return
		}

		switch bundle.Entry[i].Request.Method {
		default:
			c.AbortWithError(http.StatusNotImplemented,
				errors.New("Operation currently unsupported in batch requests: "+bundle.Entry[i].Request.Method))
			return
		case "DELETE":
			if bundle.Entry[i].Request.Url == "" {
				c.AbortWithError(http.StatusBadRequest, errors.New("Batch DELETE must have a URL"))
				return
			}
		case "POST":
			if bundle.Entry[i].Resource == nil {
				c.AbortWithError(http.StatusBadRequest, errors.New("Batch POST must have a resource body"))
				return
			}
		case "PUT":
			if bundle.Entry[i].Resource == nil {
				c.AbortWithError(http.StatusBadRequest, errors.New("Batch PUT must have a resource body"))
				return
			}
		}
		entries[i] = &bundle.Entry[i]
	}

	sort.Sort(byRequestMethod(entries))

	// Now loop through the entries, assigning new IDs to those that are POST or Conditional PUT and fixing any
	// references to reference the new ID.
	refMap := make(map[string]models.Reference)
	newIDs := make([]string, len(entries))
	for i, entry := range entries {
		if entry.Request.Method == "POST" {
			// Create a new ID and add it to the reference map
			id := bson.NewObjectId().Hex()
			newIDs[i] = id
			refMap[entry.FullUrl] = models.Reference{
				Reference:    entry.Request.Url + "/" + id,
				Type:         entry.Request.Url,
				ReferencedID: id,
				External:     new(bool),
			}

			// Rewrite the FullUrl using the new ID
			entry.FullUrl = responseURL(c.Request, entry.Request.Url, id).String()
		} else if entry.Request.Method == "PUT" && isConditional(entry) {
			// We need to process conditionals referencing temp IDs in a second pass, so skip them here
			if strings.Contains(entry.Request.Url, "urn:uuid:") || strings.Contains(entry.Request.Url, "urn%3Auuid%3A") {
				continue
			}

			if err := b.resolveConditionalPut(c.Request, i, entry, newIDs, refMap); err != nil {
				c.AbortWithError(http.StatusInternalServerError, err)
				return
			}
		}
	}

	// Second pass to take care of conditionals referencing temporary IDs.  Known limitation: if a conditional
	// references a temp ID also defined by a conditional, we error out if it hasn't been resolved yet -- too many
	// rabbit holes.
	for i, entry := range entries {
		if entry.Request.Method == "PUT" && isConditional(entry) {
			// Use a regex to swap out the temp IDs with the new IDs
			for oldID, ref := range refMap {
				re := regexp.MustCompile("([=,])(" + oldID + "|" + url.QueryEscape(oldID) + ")(&|,|$)")
				entry.Request.Url = re.ReplaceAllString(entry.Request.Url, "${1}"+ref.Reference+"${3}")
			}

			if strings.Contains(entry.Request.Url, "urn:uuid:") || strings.Contains(entry.Request.Url, "urn%3Auuid%3A") {
				c.AbortWithError(http.StatusNotImplemented,
					errors.New("Cannot resolve conditionals referencing other conditionals"))
				return
			}

			if err := b.resolveConditionalPut(c.Request, i, entry, newIDs, refMap); err != nil {
				c.AbortWithError(http.StatusInternalServerError, err)
				return
			}
		}
	}

	// Update all the references to the entries (to reflect newly assigned IDs)
	updateAllReferences(entries, refMap)

	// Then make the changes in the database and update the entry response
	for i, entry := range entries {
		switch entry.Request.Method {
		case "DELETE":
			if !isConditional(entry) {
				// It's a normal DELETE
				parts := strings.SplitN(entry.Request.Url, "/", 2)
				if len(parts) != 2 {
					c.AbortWithError(http.StatusInternalServerError,
						fmt.Errorf("Couldn't identify resource and id to delete from %s", entry.Request.Url))
					return
				}
				if err := b.DAL.Delete(parts[1], parts[0]); err != nil && err != ErrNotFound {
					c.AbortWithError(http.StatusInternalServerError, err)
					return
				}
			} else {
				// It's a conditional (query-based) delete
				parts := strings.SplitN(entry.Request.Url, "?", 2)
				query := search.Query{Resource: parts[0], Query: parts[1]}
				if _, err := b.DAL.ConditionalDelete(query); err != nil {
					c.AbortWithError(http.StatusInternalServerError, err)
					return
				}
			}

			entry.Request = nil
			entry.Response = &models.BundleEntryResponseComponent{
				Status: "204",
			}
		case "POST":
			if err := b.DAL.PostWithID(newIDs[i], entry.Resource); err != nil {
				c.AbortWithError(http.StatusInternalServerError, err)
				return
			}
			entry.Request = nil
			entry.Response = &models.BundleEntryResponseComponent{
				Status:   "201",
				Location: entry.FullUrl,
			}
			if meta, ok := models.GetResourceMeta(entry.Resource); ok {
				entry.Response.LastModified = meta.LastUpdated
			}
		case "PUT":
			// Because we pre-process conditional PUTs, we know this is always a normal PUT operation
			entry.FullUrl = responseURL(c.Request, entry.Request.Url).String()
			parts := strings.SplitN(entry.Request.Url, "/", 2)
			if len(parts) != 2 {
				c.AbortWithError(http.StatusInternalServerError,
					fmt.Errorf("Couldn't identify resource and id to put from %s", entry.Request.Url))
				return
			}
			createdNew, err := b.DAL.Put(parts[1], entry.Resource)
			if err != nil {
				c.AbortWithError(http.StatusInternalServerError, err)
				return
			}
			entry.Request = nil
			entry.Response = new(models.BundleEntryResponseComponent)
			entry.Response.Location = entry.FullUrl
			if createdNew {
				entry.Response.Status = "201"
			} else {
				entry.Response.Status = "200"
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

	c.Header("Access-Control-Allow-Origin", "*")
	c.JSON(http.StatusOK, bundle)
}

func (b *BatchController) resolveConditionalPut(request *http.Request, entryIndex int, entry *models.BundleEntryComponent, newIDs []string, refMap map[string]models.Reference) error {
	// Do a preflight to either get the existing ID, get a new ID, or detect multiple matches (not allowed)
	parts := strings.SplitN(entry.Request.Url, "?", 2)
	query := search.Query{Resource: parts[0], Query: parts[1]}

	var id string
	if IDs, err := b.DAL.FindIDs(query); err == nil {
		switch len(IDs) {
		case 0:
			id = bson.NewObjectId().Hex()
		case 1:
			id = IDs[0]
		default:
			return ErrMultipleMatches
		}
	} else {
		return err
	}

	// Rewrite the PUT as a normal (non-conditional) PUT
	entry.Request.Url = query.Resource + "/" + id

	// Add the new ID to the reference map
	newIDs[entryIndex] = id
	refMap[entry.FullUrl] = models.Reference{
		Reference:    entry.Request.Url,
		Type:         query.Resource,
		ReferencedID: id,
		External:     new(bool),
	}

	// Rewrite the FullUrl using the new ID
	entry.FullUrl = responseURL(request, entry.Request.Url, id).String()

	return nil
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

func isConditional(entry *models.BundleEntryComponent) bool {
	if entry.Request == nil {
		return false
	} else if entry.Request.Method != "PUT" && entry.Request.Method != "DELETE" {
		return false
	}
	return !strings.Contains(entry.Request.Url, "/") || strings.Contains(entry.Request.Url, "?")
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
