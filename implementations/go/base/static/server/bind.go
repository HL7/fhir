package server

import (
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/gin-gonic/gin/binding"
)

func FHIRBind(c *gin.Context, obj interface{}) error {
	if c.Request.Method == "GET" {
		return c.BindWith(obj, binding.Form)
	}

	if strings.Contains(c.ContentType(), "json") {
		return c.BindJSON(obj)
	}

	return c.Bind(obj)
}
