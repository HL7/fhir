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

func (rc *ResourceController) IndexHandler(c *echo.Context) error {
	defer func() error {
		if r := recover(); r != nil {
			switch x := r.(type) {
			case search.Error:
				return c.JSON(x.HTTPStatus, x.OperationOutcome)
			default:
				outcome := &models.OperationOutcome{
					Issue: []models.OperationOutcomeIssueComponent{
						models.OperationOutcomeIssueComponent{
							Severity: "fatal",
							Code:     "exception",
						},
					},
				}
				return c.JSON(http.StatusInternalServerError, outcome)
			}
		}
		return nil
	}()

	result := models.NewSliceForResourceName(rc.Name, 0, 0)

	// Create and execute the Mongo query based on the http query params
	searcher := search.NewMongoSearcher(Database)
	searchQuery := search.Query{Resource: rc.Name, Query: c.Request().URL.RawQuery}
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
		return err
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
	return c.JSON(http.StatusOK, bundle)
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
		return err
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
		return err
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
