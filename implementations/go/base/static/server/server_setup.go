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

// AddInterceptor adds a new interceptor for a particular HTTP verb and FHIR resource. For example:
// AddInterceptor("POST", "Patient", handleFunc) would register the function "handleFunc"
// to be run against a Patient resource when it is created, before being added to the database.
//
// To run a handler against ALL resources pass "*" as the resourceType.
//
// Supported HTTP verbs are: POST, PUT, DELETE
func (f *FHIRServer) AddInterceptor(httpVerb, resourceType string, handler InterceptorHandler) error {

	if httpVerb == "POST" || httpVerb == "PUT" || httpVerb == "DELETE" {
		f.Interceptors[httpVerb] = append(f.Interceptors[httpVerb], Interceptor{ResourceType: resourceType, Handler: handler})
		return nil
	}
	return errors.New(fmt.Sprintf("AddInterceptor: unsupported HTTP verb %s", httpVerb))
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
