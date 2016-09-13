package main

import (
	"flag"

	"github.com/intervention-engine/fhir/server"
)

func main() {
	reqLog := flag.Bool("reqlog", false, "Enables request logging -- do NOT use in production")
	dbName := flag.String("dbname", "fhir", "Mongo database name")
	idxConfigPath := flag.String("idxconfig", "config/indexes.conf", "Path to the indexes config file")

	flag.Parse()
	s := server.NewServer("localhost")
	if *reqLog {
		s.Engine.Use(server.RequestLoggerHandler)
	}

	config := server.DefaultConfig

	if *dbName != "" {
		config.DatabaseName = *dbName
	}

	if *idxConfigPath != "" {
		config.IndexConfigPath = *idxConfigPath
	}

	s.Run(config)
}
