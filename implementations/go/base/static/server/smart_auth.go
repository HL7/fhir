package server

import (
	"fmt"
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

// SmartAuthHandler middleware provides authorization checking aginst the scopes
// described in the SMART App Authorization Guide
// http://docs.smarthealthit.org/authorization/
//
// The assumption is that there is an API Gatway that is deployed in front of
// this go application which handles OAuth 2 token introspection and then
// provides information about the request in HTTP headers.
//
// This code assumes that two headers will be present in the HTTP request
// X-DELEGATED - This will be set to "true" when the request is being made by
// a client on behalf of a user. If this header is not present or any value
// other than "true", this middleware will pass the request through.
// X-SCOPE - The scopes that have been granted for this particular request.
func SmartAuthHandler(resourceName string) gin.HandlerFunc {
	allResourcesAllScope := "user/*.*"
	allResourcesReadScope := "user/*.read"
	allResourcesWriteScope := "user/*.write"
	readScope := fmt.Sprintf("user/%s.read", resourceName)
	writeScope := fmt.Sprintf("user/%s.write", resourceName)
	allScope := fmt.Sprintf("user/%s.*", resourceName)
	return func(c *gin.Context) {
		if c.Request.Header.Get("X-DELEGATED") != "true" && c.Request.Header.Get("X-USER") == "" {
			c.String(http.StatusForbidden, `
					This server has been configured to use SMART Authorization but can't
					find the X-DELEGATED or X-USER headers that are expected if this app
					is running behind the nginx gateway.

					See https://github.com/mitre/argonaut-gateway for more info.
					`)
			c.Abort()
			return
		}
		if c.Request.Header.Get("X-DELEGATED") == "true" {
			if c.Request.Method == "GET" {
				if !includesAnyScope(c, allResourcesAllScope, allResourcesReadScope, readScope, allScope) {
					c.String(http.StatusForbidden, "You do not have permission to view this resource")
					c.Abort()
					return
				}
			} else {
				if !includesAnyScope(c, allResourcesAllScope, allResourcesWriteScope, writeScope, allScope) {
					c.String(http.StatusForbidden, "You do not have permission to modify this resource")
					c.Abort()
					return
				}
			}
		}
	}
}

func includesAnyScope(c *gin.Context, scopes ...string) bool {
	grantedScopes := c.Request.Header.Get("X-SCOPE")
	for _, neededScope := range scopes {
		if strings.Contains(grantedScopes, neededScope) {
			return true
		}
	}
	return false
}
