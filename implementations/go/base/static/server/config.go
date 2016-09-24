package server

import (
	"github.com/intervention-engine/fhir/auth"
	"gopkg.in/mgo.v2"
)

// Although we got rid of the global in the fhir package, the ie project still needs it
// Once ie removes the dependency on the global, this should go away
var Database *mgo.Database

// DefaultConfig is the default server configuration
var DefaultConfig = Config{
	ServerURL:       "http://localhost:3001",
	IndexConfigPath: "config/indexes.conf",
	DatabaseName:    "fhir",
	Auth:            auth.None(),
}

// Config is used to hold information about the configuration of the FHIR
// server.
type Config struct {
	// ServerURL is the full URL for the root of the server. This may be used
	// by other middleware to compute redirect URLs
	ServerURL string
	// Auth determines what, if any authentication and authorization will be used
	// by the FHIR server
	Auth auth.Config
	// IndexConfigPath is the path to an indexes.conf configuration file, specifying
	// what mongo indexes the server should create (or verify) on startup
	IndexConfigPath string
	// DatabaseName is the name of the mongo database used for the fhir database.
	// Typically this will be the DefaultDatabaseName
	DatabaseName string
}
