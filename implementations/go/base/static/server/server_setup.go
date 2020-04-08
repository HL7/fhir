package server

import (
	"errors"
	"fmt"
	"github.com/gin-gonic/gin"
	"gopkg.in/mgo.v2"
	"log"
)

type FHIRServer struct {
	DatabaseHost     string
	Engine           *gin.Engine
	MiddlewareConfig map[string][]gin.HandlerFunc
	Interceptors     map[string]InterceptorList
}

func (f *FHIRServer) AddMiddleware(key string, middleware gin.HandlerFunc) {
	f.MiddlewareConfig[key] = append(f.MiddlewareConfig[key], middleware)
}

// AddInterceptor adds a new interceptor for a particular database operation and FHIR resource.
// For example:
// AddInterceptor("Create", "Patient", patientInterceptorHandler) would register the
// patientInterceptorHandler methods to be run against a Patient resource when it is created.
//
// To run a handler against ALL resources pass "*" as the resourceType.
//
// Supported database operations are: "Create", "Update", "Delete"
func (f *FHIRServer) AddInterceptor(op, resourceType string, handler InterceptorHandler) error {

	if op == "Create" || op == "Update" || op == "Delete" {
		f.Interceptors[op] = append(f.Interceptors[op], Interceptor{ResourceType: resourceType, Handler: handler})
		return nil
	}
	return errors.New(fmt.Sprintf("AddInterceptor: unsupported database operation %s", op))
}

func NewServer(databaseHost string) *FHIRServer {
	server := &FHIRServer{
		DatabaseHost:     databaseHost,
		MiddlewareConfig: make(map[string][]gin.HandlerFunc),
		Interceptors:     make(map[string]InterceptorList),
	}
	server.Engine = gin.Default()
	return server
}

func (f *FHIRServer) Run(config Config) {
	var err error

	// Setup the database
	session, err := mgo.Dial(f.DatabaseHost)
	if err != nil {
		panic(err)
	}
	log.Println("Connected to mongodb")
	defer session.Close()

	Database = session.DB("fhir")

	RegisterRoutes(f.Engine, f.MiddlewareConfig, NewMongoDataAccessLayer(Database, f.Interceptors), config)

	f.Engine.Run(":3001")
}
