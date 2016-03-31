package server

import (
	"bytes"
	"io/ioutil"
	"log"

	"github.com/gin-gonic/gin"
)

// RequestLoggerHandler is a handler intended to be used during debugging to log out the request details including
// the request headers and the request body.  This should not be used in production as it has performance implications.
func RequestLoggerHandler(c *gin.Context) {
	if c.Request != nil {

		buf, _ := ioutil.ReadAll(c.Request.Body)
		c.Request.Body.Close()

		log.Println("-----------------------------------------------------------------------------------------------------")
		log.Println("REQUEST HEADERS:")
		for k, v := range c.Request.Header {
			log.Printf("\t%s: %s\n", k, v)
		}
		log.Printf("\nREQUEST BODY:\n%s\n", buf)
		log.Println("-----------------------------------------------------------------------------------------------------")

		c.Request.Body = ioutil.NopCloser(bytes.NewReader(buf))
	}
	c.Next()
}
