package server

import (
	"fmt"
	"net/http"
	"net/url"
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/intervention-engine/fhir/models"
	"github.com/intervention-engine/fhir/search"
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

func (rc *ResourceController) IndexHandler(c *gin.Context) {
	defer func() {
		if r := recover(); r != nil {
			switch x := r.(type) {
			case search.Error:
				c.JSON(x.HTTPStatus, x.OperationOutcome)
				return
			default:
				outcome := models.NewOperationOutcome("fatal", "exception", "")
				c.JSON(http.StatusInternalServerError, outcome)
				return
			}
		}
	}()

	searchQuery := search.Query{Resource: rc.Name, Query: c.Request.URL.RawQuery}
	baseURL := responseURL(c.Request, rc.Name)
	bundle, err := rc.DAL.Search(*baseURL, searchQuery)
	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Set("bundle", bundle)
	c.Set("Resource", rc.Name)
	c.Set("Action", "search")

	c.Header("Access-Control-Allow-Origin", "*")
	c.JSON(http.StatusOK, bundle)
}

func (rc *ResourceController) LoadResource(c *gin.Context) (interface{}, error) {
	result, err := rc.DAL.Get(c.Param("id"), rc.Name)
	if err != nil {
		return nil, err
	}

	c.Set(rc.Name, result)
	c.Set("Resource", rc.Name)
	return result, nil
}

func (rc *ResourceController) ShowHandler(c *gin.Context) {
	c.Set("Action", "read")
	_, err := rc.LoadResource(c)
	if err != nil && err != ErrNotFound {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Header("Access-Control-Allow-Origin", "*")
	if err == ErrNotFound {
		c.Status(http.StatusNotFound)
		return
	}
	resource, _ := c.Get(rc.Name)
	c.JSON(http.StatusOK, resource)
}

func (rc *ResourceController) CreateHandler(c *gin.Context) {
	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		c.JSON(http.StatusBadRequest, oo)
		return
	}

	id, err := rc.DAL.Post(resource)
	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "create")

	c.Header("Location", responseURL(c.Request, rc.Name, id).String())
	c.Header("Access-Control-Allow-Origin", "*")
	c.JSON(http.StatusCreated, resource)
}

func (rc *ResourceController) UpdateHandler(c *gin.Context) {
	resource := models.NewStructForResourceName(rc.Name)
	err := c.Bind(resource)
	if err != nil {
		oo := models.NewOperationOutcome("fatal", "exception", err.Error())
		c.JSON(http.StatusBadRequest, oo)
		return
	}

	createdNew, err := rc.DAL.Put(c.Param("id"), resource)
	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Set(rc.Name, resource)
	c.Set("Resource", rc.Name)
	c.Set("Action", "update")

	c.Header("Access-Control-Allow-Origin", "*")
	if createdNew {
		c.JSON(http.StatusCreated, resource)
	} else {
		c.JSON(http.StatusOK, resource)
	}
}

func (rc *ResourceController) DeleteHandler(c *gin.Context) {
	id := c.Param("id")

	if err := rc.DAL.Delete(id, rc.Name); err != nil && err != ErrNotFound {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Set(rc.Name, id)
	c.Set("Resource", rc.Name)
	c.Set("Action", "delete")

	c.Header("Access-Control-Allow-Origin", "*")
	c.Status(http.StatusNoContent)
}

func (rc *ResourceController) ConditionalDeleteHandler(c *gin.Context) {
	query := search.Query{Resource: rc.Name, Query: c.Request.URL.RawQuery}
	_, err := rc.DAL.ConditionalDelete(query)
	if err != nil {
		c.AbortWithError(http.StatusInternalServerError, err)
		return
	}

	c.Set("Resource", rc.Name)
	c.Set("Action", "delete")

	c.Header("Access-Control-Allow-Origin", "*")
	c.Status(http.StatusNoContent)
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
