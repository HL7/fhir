package main

import (
	"flag"

	"github.com/intervention-engine/fhir/server"
)

func main() {
	smartAuth := flag.Bool("smart", false, "Enables SMART Authorization")
	reqLog := flag.Bool("reqlog", false, "Enables request logging -- do NOT use in production")
	flag.Parse()
	s := server.NewServer("localhost")
	if *reqLog {
		s.Engine.Use(server.RequestLoggerHandler)
	}

	config := server.Config{UseSmartAuth: *smartAuth}
	s.Run(config)
}
