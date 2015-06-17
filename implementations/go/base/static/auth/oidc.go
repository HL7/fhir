package auth

import (
	"encoding/gob"
	"encoding/json"
	"net/http"
	"strings"
	"time"

	"golang.org/x/oauth2"

	"github.com/gin-gonic/contrib/sessions"
	"github.com/gin-gonic/gin"
	"github.com/icrowley/fake"
	"github.com/juju/errors"
	"github.com/mitre/heart"
)

func init() {
	gob.Register(&oauth2.Token{})
}

// OIDCAuthenticationHandler is a middleware that will check for the presence of a session with
// a UserInfo value set. If it exists, it will assume that the has logged in at some point. It will then
// check the session for a token. If the token has not expired, it will set the
// UserInfo in a UserInfo value on the gin Context.
//
// If there is no UserInfo value present in the session or if the OpenIDTokenResponse has expired, the
// user will be redirected to the provided redirectURI.
func OIDCAuthenticationHandler(config oauth2.Config) gin.HandlerFunc {
	return func(c *gin.Context) {
		// Let things pass through to the redirect path, as it sets up the session
		if strings.Contains(c.Request.URL.Path, "/redirect") {
			return
		}
		session := sessions.Default(c)
		ui := session.Get("UserInfo")
		if ui != nil {
			tokenInterface := session.Get("token")
			if tokenInterface != nil {
				token := tokenInterface.(*oauth2.Token)
				if token.Expiry.After(time.Now()) {
					c.Set("UserInfo", session.Get("UserInfo"))
					return
				}
				session.Delete("UserInfo")
				session.Delete("token")
			}
		}
		state := fake.CharactersN(20)
		session.Set("state", state)
		err := session.Save()
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, err)
			return
		}
		authURL := config.AuthCodeURL(state)
		c.Redirect(http.StatusFound, authURL)
		c.Abort()
	}
}

// RedirectHandler provides a gin.HandlerFunc to process the authentication response from an
// Open ID Provider.
func RedirectHandler(config oauth2.Config, successfulAuthRedirectURL, userInfoURL string) gin.HandlerFunc {
	return func(c *gin.Context) {
		session := sessions.Default(c)
		authError := c.Query("error")
		if authError != "" {
			session.Delete("state")
			err := errors.Unauthorizedf("OP was unable to successfully authenticate your request: %s", authError)
			c.AbortWithError(http.StatusInternalServerError, err)
			return
		}
		serverState := c.Query("state")
		localState := session.Get("state")
		if localState == nil {
			err := errors.NotValidf("Couldn't find the local state or nonce to verify the response from the OP")
			c.AbortWithError(http.StatusInternalServerError, err)
			return
		}
		if localState.(string) != serverState {
			c.String(http.StatusForbidden, "State did not match")
			c.Abort()
			return
		}

		code := c.Query("code")
		token, err := config.Exchange(oauth2.NoContext, code)
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, err)
			return
		}
		session.Set("token", token)
		client := config.Client(oauth2.NoContext, token)
		resp, err := client.Get(userInfoURL)
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, errors.Annotate(err, "Couldn't connect to the user info endpoint"))
			return
		}
		defer resp.Body.Close()
		decoder := json.NewDecoder(resp.Body)
		userInfo := &heart.UserInfo{}
		err = decoder.Decode(userInfo)
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, errors.Annotate(err, "Couldn't decode the token response"))
			return
		}
		session.Set("UserInfo", userInfo)
		session.Delete("state")
		err = session.Save()
		if err != nil {
			c.AbortWithError(http.StatusInternalServerError, err)
			return
		}
		c.Redirect(http.StatusFound, successfulAuthRedirectURL)
	}
}
