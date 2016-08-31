package auth

import (
	"encoding/json"
	"net/http"
	"net/url"
	"strings"

	"github.com/gin-gonic/gin"
	"github.com/juju/errors"
	"github.com/mitre/heart"
)

// OAuthIntrospectionHandler creates a gin.HandlerFunc that can be used to
// introspect OAuth 2.0 tokens provided in the request.
//
// This middleware will abort any requests that do not have an Authorization header. It will
// also halt requests if the provided bearer token is inactive or expired.
//
// If a valid token is provided, the gin.Context is augmented by setting the following variables:
// scopes will be a []string containing all scopes valid for the provided token. subject will be
// an identifier for the user who delegated the authority represented by the token. clientID will
// contain the identifier for the client issuing the request.
//
// clientID is the identifier for the OAuth 2.0 client allowed to access the
// token introspection endpoint
// clientSecret is the secret for the registered client
// endpoint is the URL for the token introspection endpoint at the OAuth 2.0
// authorization server.
//
// This is for performing token introspection using a "plain" OAuth 2.0 client.
// For HEART profiled OAuth 2.0 see:
//   https://github.com/mitre/heart/blob/master/middleware.go
func OAuthIntrospectionHandler(clientID, clientSecret, endpoint string) gin.HandlerFunc {
	return func(c *gin.Context) {
		auth := c.Request.Header.Get("Authorization")
		if auth == "" {
			c.String(http.StatusForbidden, "No Authorization header provided")
			c.Abort()
			return
		}
		token := strings.TrimPrefix(auth, "Bearer ")
		if token == auth {
			c.String(http.StatusForbidden, "Could not find bearer token in Authorization header")
			c.Abort()
			return
		}
		values := url.Values{"client_id": {clientID}, "client_secret": {clientSecret}, "token": {token}}
		resp, err := http.PostForm(endpoint, values)
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, errors.Annotate(err, "Couldn't connect to the introspection endpoint"))
			return
		}
		defer resp.Body.Close()
		decoder := json.NewDecoder(resp.Body)
		ir := heart.IntrospectionResponse{}
		err = decoder.Decode(&ir)
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, errors.Annotate(err, "Couldn't decode the introspection response"))
			return
		}
		if !ir.Active {
			c.String(http.StatusForbidden, "Provided token is no longer active or valid")
			c.Abort()
			return
		}
		c.Set("scopes", ir.SplitScope())
		c.Set("subject", ir.SUB)
		c.Set("clientID", ir.ClientID)
	}
}
