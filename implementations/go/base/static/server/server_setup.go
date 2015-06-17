package server

import (
	"fmt"
	"log"
	"time"

	"github.com/gin-gonic/gin"
	"github.com/itsjamie/gin-cors"
	"gopkg.in/mgo.v2"
)

type AfterRoutes func(*gin.Engine)

type FHIRServer struct {
	Config           Config
	Engine           *gin.Engine
	MiddlewareConfig map[string][]gin.HandlerFunc
	AfterRoutes      []AfterRoutes
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
	return fmt.Errorf("AddInterceptor: unsupported database operation %s", op)
}

func NewServer(config Config) *FHIRServer {
	server := &FHIRServer{
		Config:           config,
		MiddlewareConfig: make(map[string][]gin.HandlerFunc),
		Interceptors:     make(map[string]InterceptorList),
	}
	server.Engine = gin.Default()

	if config.Debug {
		gin.SetMode(gin.DebugMode)
	} else {
		gin.SetMode(gin.ReleaseMode)
	}

	server.Engine.Use(cors.Middleware(cors.Config{
		Origins:         "*",
		Methods:         "GET, PUT, POST, DELETE",
		RequestHeaders:  "Origin, Authorization, Content-Type, If-Match, If-None-Exist",
		ExposedHeaders:  "Location, ETag, Last-Modified",
		MaxAge:          86400 * time.Second, // Preflight expires after 1 day
		Credentials:     true,
		ValidateHeaders: false,
	}))

	server.Engine.Use(AbortNonJSONRequestsMiddleware)

	if config.ReadOnly {
		server.Engine.Use(ReadOnlyMiddleware)
	}

	return server
}

func (f *FHIRServer) Run() {
	var err error

	// Establish initial connection to mongo
	session, err := mgo.Dial(f.Config.DatabaseHost)
	if err != nil {
		panic(err)
	}
	defer session.Close()

	session.SetSocketTimeout(f.Config.DatabaseSocketTimeout)

	Database = session.DB(f.Config.DatabaseName)
	log.Println("MongoDB: Connected")

	// Establish fhir database session
	masterSession := NewMasterSession(session, f.Config.DatabaseName)

	// Ensure all indexes
	NewIndexer(f.Config).ConfigureIndexes(masterSession)

	// Establish admin session
	masterAdminSession := NewMasterSession(session, "admin")

	// Kick off the database op monitoring routine. This periodically checks db.currentOp() and
	// kills client-initiated operations exceeding the configurable timeout. Do this AFTER the index
	// build to ensure no index build processes are killed unintentionally.
	ticker := time.NewTicker(f.Config.DatabaseKillOpPeriod)
	go killLongRunningOps(ticker, masterAdminSession, f.Config)

	// Register all API routes
	RegisterRoutes(f.Engine, f.MiddlewareConfig, NewMongoDataAccessLayer(masterSession, f.Interceptors, f.Config), f.Config)

	for _, ar := range f.AfterRoutes {
		ar(f.Engine)
	}

	// If not in -readonly mode, clear the count cache
	if !f.Config.ReadOnly {
		worker := masterSession.GetWorkerSession()
		defer worker.Close()
		err = worker.DB().C("countcache").DropCollection()
		if err != nil {
			log.Println("Server: Failed to clear cache, or cache was empty")
		}
	} else {
		log.Println("Server: Running in read-only mode")
	}

	f.Engine.Run(":3001")
}
