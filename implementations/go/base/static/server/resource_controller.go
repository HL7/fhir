package server

// TODO: This code can and should be cleaned up.  For now, it is more or less a port of the code that used to exist
// for every resource controller.

import (
	"errors"
	"fmt"
	"net/http"
	"net/url"
	"reflect"
	"strings"
	"time"

	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"github.com/labstack/echo"
	"gopkg.in/mgo.v2/bson"
)

type ResourceController struct {
	Name string
}

type ResourcePlusIncludes interface {
	GetIncludedResources() map[string]interface{}
}

func (rc *ResourceController) IndexHandler(c *echo.Context) error {
	defer func() error {
		if r := recover(); r != nil {
			switch x := r.(type) {
			case search.Error:
				return c.JSON(x.HTTPStatus, x.OperationOutcome)
			default:
				outcome := models.NewOperationOutcome("fatal", "exception", "")
				return c.JSON(http.StatusInternalServerError, outcome)
			}
		}
		return nil
	}()

	// Create and execute the Mongo query based on the http query params
	searcher := search.NewMongoSearcher(Database)
	searchQuery := search.Query{Resource: rc.Name, Query: c.Request().URL.RawQuery}

	var result interface{}
	var err error
	usesIncludes := len(searchQuery.Options().Include) > 0
	// Only use (slower) pipeline if it is needed
	if usesIncludes {
		result = models.NewSlicePlusForResourceName(rc.Name, 0, 0)
		err = searcher.CreatePipeline(searchQuery).All(result)
	} else {
		result = models.NewSliceForResourceName(rc.Name, 0, 0)
		err = searcher.CreateQuery(searchQuery).All(result)
	}
	if err != nil {
		return err
	}

	includesMap := make(map[string]interface{})
	var entryList []models.BundleEntryComponent
	resultVal := reflect.ValueOf(result).Elem()
	for i := 0; i < resultVal.Len(); i++ {
		var entry models.BundleEntryComponent
		entry.Resource = resultVal.Index(i).Addr().Interface()
		entry.Search = &models.BundleEntrySearchComponent{Mode: "match"}
		entryList = append(entryList, entry)

		if usesIncludes {
			rpi, ok := entry.Resource.(ResourcePlusIncludes)
			if ok {
				for k, v := range rpi.GetIncludedResources() {
					includesMap[k] = v
				}
			}
		}
	}

	if usesIncludes {
		for _, v := range includesMap {
			var entry models.BundleEntryComponent
			entry.Resource = v
			entry.Search = &models.BundleEntrySearchComponent{Mode: "include"}
			entryList = append(entryList, entry)
		}
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
			return err
		}
		total = uint32(intTotal)
	} else {
		// We can figure out the total by adding the offset and # results returned
		total = uint32(options.Offset + resultVal.Len())
	}
	bundle.Total = &total

	// Add links for paging
	bundle.Link = generatePagingLinks(c.Request(), searchQuery, total)

	c.Set(rc.Name, reflect.ValueOf(result).Elem().Interface())
	c.Set("Resource", rc.Name)
	c.Set("Action", "search")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusOK, &bundle)
}

func generatePagingLinks(r *http.Request, query search.Query, total uint32) []models.BundleLinkComponent {
	links := make([]models.BundleLinkComponent, 0, 5)
	values := query.NormalizedQueryValues(false)
	options := query.Options()

	// First create the base URL for paging
	baseURL := responseURL(r, query.Resource)

	// Self link
	links = append(links, newLink("self", baseURL, values, *options))

	// First link
	firstOptions := *options
	firstOptions.Offset = 0
	links = append(links, newLink("first", baseURL, values, firstOptions))

	// Previous link
	if options.Offset > 0 {
		previousOptions := *options
		previousOptions.Offset = options.Offset - options.Count
		// Handle case where paging is uneven (e.g., count=10&offset=5)
		if previousOptions.Count > previousOptions.Offset {
			previousOptions.Offset = 0
		}
		previousOptions.Count = options.Offset - previousOptions.Offset
		links = append(links, newLink("previous", baseURL, values, previousOptions))
	}

	// Next Link
	if total > uint32(options.Offset+options.Count) {
		nextOptions := *options
		nextOptions.Offset = options.Offset + options.Count
		links = append(links, newLink("next", baseURL, values, nextOptions))
	}

	// Last Link
	lastOptions := *options
	remainder := (int(total) - options.Offset) % options.Count
	if int(total) < options.Offset {
		remainder = 0
	}
	newOffset := int(total) - remainder
	if remainder == 0 && int(total) > options.Count {
		newOffset = int(total) - options.Count
	}
	lastOptions.Offset = newOffset
	links = append(links, newLink("last", baseURL, values, lastOptions))

	return links
}

func newLink(relation string, baseURL *url.URL, values url.Values, options search.QueryOptions) models.BundleLinkComponent {
	baseURL.RawQuery = options.QueryValues().Encode()
	return models.BundleLinkComponent{Relation: relation, Url: baseURL.String()}
}

func (rc *ResourceController) LoadResource(c *echo.Context) (interface{}, error) {
	var id bson.ObjectId

	idString := c.Param("id")
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		return nil, errors.New("Invalid id")
	}

	collection := Database.C(models.PluralizeLowerResourceName(rc.Name))
	result := models.NewStructForResourceName(rc.Name)
	err := collection.Find(bson.M{"_id": id.Hex()}).One(result)
	if err != nil {
		return nil, err
	}

	c.Set(rc.Name, result)
	c.Set("Resource", rc.Name)
	return result, nil
}

func (rc *ResourceController) ShowHandler(c *echo.Context) error {
	c.Set("Action", "read")
	_, err := rc.LoadResource(c)
	if err != nil {
		return err
	}

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusOK, c.Get(rc.Name))
}

func (rc *ResourceController) CreateHandler(c *echo.Context) error {
	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		return c.JSON(http.StatusBadRequest, oo)
	}

	collection := Database.C(models.PluralizeLowerResourceName(rc.Name))
	i := bson.NewObjectId()
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(i.Hex())
	UpdateLastUpdatedDate(resource)
	err = collection.Insert(resource)
	if err != nil {
		return err
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "create")

	c.Response().Header().Add("Location", responseURL(c.Request(), rc.Name, i.Hex()).String())
	c.Response().Header().Set("Access-Control-Allow-Origin", "*")

	return c.JSON(http.StatusCreated, resource)
}

func (rc *ResourceController) UpdateHandler(c *echo.Context) error {

	var id bson.ObjectId

	idString := c.Param("id")
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		return errors.New("Invalid id")
	}

	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		return c.JSON(http.StatusBadRequest, oo)
	}

	collection := Database.C(models.PluralizeLowerResourceName(rc.Name))
	reflect.ValueOf(resource).Elem().FieldByName("Id").SetString(id.Hex())
	UpdateLastUpdatedDate(resource)
	err = collection.Update(bson.M{"_id": id.Hex()}, resource)
	if err != nil {
		return err
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "update")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusOK, resource)
}

func (rc *ResourceController) DeleteHandler(c *echo.Context) error {
	var id bson.ObjectId

	idString := c.Param("id")
	if bson.IsObjectIdHex(idString) {
		id = bson.ObjectIdHex(idString)
	} else {
		return errors.New("Invalid id")
	}

	collection := Database.C(models.PluralizeLowerResourceName(rc.Name))

	err := collection.Remove(bson.M{"_id": id.Hex()})
	if err != nil {
		return err
	}

	c.Set(rc.Name, id.Hex())
	c.Set("Resource", rc.Name)
	c.Set("Action", "delete")
	return nil
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

func UpdateLastUpdatedDate(resource interface{}) {
	m := reflect.ValueOf(resource).Elem().FieldByName("Meta")
	if m.IsNil() {
		newMeta := &models.Meta{}
		m.Set(reflect.ValueOf(newMeta))
	}
	now := &models.FHIRDateTime{Time: time.Now(), Precision: models.Timestamp}
	m.Elem().FieldByName("LastUpdated").Set(reflect.ValueOf(now))
}
