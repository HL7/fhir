package server

import (
	"fmt"
	"net/http"
	"net/url"
	"strings"

	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
	"github.com/labstack/echo"
)

type ResourceController struct {
	Name string
	DAL  DataAccessLayer
}

func NewResourceController(name string, dal DataAccessLayer) *ResourceController {
	return &ResourceController{
		Name: name,
		DAL:  dal,
	}
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

	searchQuery := search.Query{Resource: rc.Name, Query: c.Request().URL.RawQuery}
	baseURL := responseURL(c.Request(), rc.Name)
	bundle, err := rc.DAL.Search(*baseURL, searchQuery)
	if err != nil {
		return err
	}

	c.Set("bundle", bundle)
	c.Set("Resource", rc.Name)
	c.Set("Action", "search")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusOK, bundle)
}

func (rc *ResourceController) LoadResource(c *echo.Context) (interface{}, error) {
	result, err := rc.DAL.Get(c.Param("id"), rc.Name)
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
	if err != nil && err != ErrNotFound {
		return err
	}

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	if err == ErrNotFound {
		return c.NoContent(http.StatusNotFound)
	}
	return c.JSON(http.StatusOK, c.Get(rc.Name))
}

func (rc *ResourceController) CreateHandler(c *echo.Context) error {
	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		return c.JSON(http.StatusBadRequest, oo)
	}

	id, err := rc.DAL.Post(resource)
	if err != nil {
		return err
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "create")

	c.Response().Header().Add("Location", responseURL(c.Request(), rc.Name, id).String())
	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.JSON(http.StatusCreated, resource)
}

func (rc *ResourceController) UpdateHandler(c *echo.Context) error {
	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		return c.JSON(http.StatusBadRequest, oo)
	}

	createdNew, err := rc.DAL.Put(c.Param("id"), resource)
	if err != nil {
		return err
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "update")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	if createdNew {
		return c.JSON(http.StatusCreated, resource)
	}
	return c.JSON(http.StatusOK, resource)
}

func (rc *ResourceController) DeleteHandler(c *echo.Context) error {
	id := c.Param("id")

	if err := rc.DAL.Delete(id, rc.Name); err != nil && err != ErrNotFound {
		return err
	}

	c.Set(rc.Name, id)
	c.Set("Resource", rc.Name)
	c.Set("Action", "delete")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.NoContent(http.StatusNoContent)
}

func (rc *ResourceController) ConditionalDeleteHandler(c *echo.Context) error {
	query := search.Query{Resource: rc.Name, Query: c.Request().URL.RawQuery}
	_, err := rc.DAL.ConditionalDelete(query)
	if err != nil {
		return err
	}

	c.Set("Resource", rc.Name)
	c.Set("Action", "delete")

	c.Response().Header().Set("Access-Control-Allow-Origin", "*")
	return c.NoContent(http.StatusNoContent)
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
