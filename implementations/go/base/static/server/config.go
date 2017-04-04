package server

import (
	"time"

	"github.com/intervention-engine/fhir/auth"
	"gopkg.in/mgo.v2"
)

// Although we got rid of the global in the fhir package, the ie project still needs it
// Once ie removes the dependency on the global, this should go away
var Database *mgo.Database

// Config is used to hold information about the configuration of the FHIR server.
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

	// DatabaseHost is the url of the running mongo instance to use for the fhir database.
	DatabaseHost string

	// DatabaseName is the name of the mongo database used for the fhir database.
	// Typically this will be the default DatabaseName "fhir".
	DatabaseName string

	// DatabaseSocketTimeout is the amount of time the mgo driver will wait for a response
	// from mongo before timing out.
	DatabaseSocketTimeout time.Duration

	// DatabaseOpTimeout is the amount of time GoFHIR will wait before killing a long-running
	// database process. This defaults to a reasonable upper bound for slow, pipelined queries: 30s.
	DatabaseOpTimeout time.Duration

	// DatabaseKillOpPeriod is the length of time between scans of the database to kill long-running ops.
	DatabaseKillOpPeriod time.Duration

	// CountTotalResults toggles whether the searcher should also get a total
	// count of the total results of a search. In practice this is a performance hit
	// for large datasets.
	CountTotalResults bool

	// EnableCISearches toggles whether the mongo searches uses regexes to maintain
	// case-insesitivity when performing searches on string fields, codes, etc.
	EnableCISearches bool

	// ReadOnly toggles whether the server is in read-only mode. In read-only
	// mode any HTTP verb other than GET, HEAD or OPTIONS is rejected.
	ReadOnly bool

	// Debug toggles debug-level logging.
	Debug bool
}

// DefaultConfig is the default server configuration
var DefaultConfig = Config{
	ServerURL:             "",
	IndexConfigPath:       "config/indexes.conf",
	DatabaseHost:          "localhost:27017",
	DatabaseName:          "fhir",
	DatabaseSocketTimeout: 2 * time.Minute,
	DatabaseOpTimeout:     90 * time.Second,
	DatabaseKillOpPeriod:  10 * time.Second,
	Auth:                  auth.None(),
	EnableCISearches:      true,
	CountTotalResults:     true,
	ReadOnly:              false,
	Debug:                 false,
}
