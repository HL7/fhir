package server

import (
	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
)

const (
	MIMEJSONFHIR = "application/json+fhir"
	MIMEXMLFHIR  = "application/xml+fhir"
)

func FHIRBind(c *gin.Context, obj interface{}) error {
	if c.Request.Method == "GET" {
		return c.BindWith(obj, binding.Form)
	}
	switch c.ContentType() {
	case MIMEJSONFHIR:
		return c.BindJSON(obj)
	case MIMEXMLFHIR:
		return c.BindWith(obj, binding.XML)
	}
	return c.Bind(obj)
}
