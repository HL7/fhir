package auth

import (
	"fmt"
	"net/http"
	"strings"

	"github.com/gin-gonic/gin"
)

// HEARTScopesHandler middleware provides authorization checking aginst the
// scopes described in the Health Relationship Trust Profile for Fast Healthcare
// Interoperability Resources (FHIR) OAuth 2.0 Scopes
// http://openid.bitbucket.org/HEART/openid-heart-fhir-oauth2.html and the
// SMART App Authorization Guide
// http://docs.smarthealthit.org/authorization/
//
// The assumption is that gin handlers run before this one will take care of
// handling the OAuth 2.0 token introspection or OpenID Connect authentication.
// This handler looks at the scopes provided in the gin.Context to see if they
// are appropriate for accessing the resource.
func HEARTScopesHandler(resourceName string) gin.HandlerFunc {
	allResourcesAllScope := "user/*.*"
	allResourcesReadScope := "user/*.read"
	allResourcesWriteScope := "user/*.write"
	readScope := fmt.Sprintf("user/%s.read", resourceName)
	writeScope := fmt.Sprintf("user/%s.write", resourceName)
	allScope := fmt.Sprintf("user/%s.*", resourceName)
	return func(c *gin.Context) {
		_, exists := c.Get("UserInfo")
		if exists {
			// This is an OIDC authenticated request. Let it pass through.
			return
		}

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

func includesAnyScope(c *gin.Context, scopes ...string) bool {
	grantedScopes, exists := c.Get("scopes")
	if exists {
		allGrantedScopes := strings.Join(grantedScopes.([]string), " ")
		for _, neededScope := range scopes {
			if strings.Contains(allGrantedScopes, neededScope) {
				return true
			}
		}
	}
	return false
}
