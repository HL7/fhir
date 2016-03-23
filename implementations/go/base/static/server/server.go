package main

import (
	"flag"

	"github.com/intervention-engine/fhir/server"
)

func main() {
	smartAuth := flag.Bool("smart", false, "Enables SMART Authorization")
	flag.Parse()
	s := server.NewServer("localhost")

	config := server.Config{UseSmartAuth: *smartAuth}
	s.Run(config)
}
