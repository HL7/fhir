package server

import (
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

// AbortNonJSONRequestsMiddleware is middleware that responds to any request that Accepts a Content-Type
// other than JSON (or a JSON flavor) with a 406 Not Acceptable status.
func AbortNonJSONRequestsMiddleware(c *gin.Context) {
	acceptHeader := c.Request.Header.Get("Accept")
	if acceptHeader != "" && !strings.Contains(acceptHeader, "json") && !strings.Contains(acceptHeader, "*/*") {
		c.AbortWithStatus(http.StatusNotAcceptable)
	}
	c.Next()
}

// ReadOnlyMiddleware makes the API read-only and responds to any requests that are not
// GET, HEAD, or OPTIONS with a 405 Method Not Allowed error.
func ReadOnlyMiddleware(c *gin.Context) {
	method := c.Request.Method
	switch method {
	// allowed methods:
	case "GET", "HEAD", "OPTIONS":
		c.Next()
	// all other methods:
	default:
		c.AbortWithStatus(http.StatusMethodNotAllowed)
	}
}
