package server

import (
	"gopkg.in/mgo.v2"
)

var (
	MongoSession *mgo.Session
	Database     *mgo.Database
)

// Config is used to hold information about the configuration of the FHIR
// server.
type Config struct {
	// Determines whether the server will enforce authorization decisions based on
	// OAuth 2 scopes specified by SMART App Authorization. See SmartAuthHandler
	// for more details
	UseSmartAuth bool

	// Enable the basic Echo logger
	UseLoggingMiddleware bool
}
