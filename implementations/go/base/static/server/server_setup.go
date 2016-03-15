package server

import (
	"log"

	"github.com/labstack/echo"
	"github.com/labstack/echo/middleware"
	"gopkg.in/mgo.v2"
)

type FHIRServer struct {
	DatabaseHost     string
	Echo             *echo.Echo
	MiddlewareConfig map[string][]echo.Middleware
}

func (f *FHIRServer) AddMiddleware(key string, middleware echo.Middleware) {
	f.MiddlewareConfig[key] = append(f.MiddlewareConfig[key], middleware)
}

func NewServer(databaseHost string) *FHIRServer {
	server := &FHIRServer{DatabaseHost: databaseHost, MiddlewareConfig: make(map[string][]echo.Middleware)}
	server.Echo = echo.New()
	return server
}

func (f *FHIRServer) Run(config Config) {
	var err error

	// Setup the database
	if MongoSession, err = mgo.Dial(f.DatabaseHost); err != nil {
		panic(err)
	}
	log.Println("Connected to mongodb")
	defer MongoSession.Close()

	Database = MongoSession.DB("fhir")

	if config.UseLoggingMiddleware {
		f.Echo.Use(middleware.Logger())
	}
	RegisterRoutes(f.Echo, f.MiddlewareConfig, config)
	f.Echo.Run(":3001")
}
