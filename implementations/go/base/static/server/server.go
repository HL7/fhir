package main

import (
	"flag"
	"github.com/intervention-engine/fhir/server"
	"github.com/itsjamie/gin-cors"
	"time"
)

func main() {
	smartAuth := flag.Bool("smart", false, "Enables SMART Authorization")
	reqLog := flag.Bool("reqlog", false, "Enables request logging -- do NOT use in production")
	flag.Parse()
	s := server.NewServer("localhost")
	if *reqLog {
		s.Engine.Use(server.RequestLoggerHandler)
	}

	s.Engine.Use(cors.Middleware(cors.Config{
		Origins:         "*",
		Methods:         "GET, PUT, POST, DELETE",
		RequestHeaders:  "Origin, Authorization, Content-Type",
		ExposedHeaders:  "",
		MaxAge:          86400 * time.Second, // Preflight expires after 1 day
		Credentials:     true,
		ValidateHeaders: false,
	}))

	config := server.Config{UseSmartAuth: *smartAuth}
	s.Run(config)
}
