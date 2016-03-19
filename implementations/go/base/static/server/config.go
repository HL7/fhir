package server

import "gopkg.in/mgo.v2"

// Although we got rid of the global in the fhir package, the ie project still needs it
// Once ie removes the dependency on the global, this should go away
var Database *mgo.Database

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
