package main

import (
	"flag"
	"fmt"
	"net/http"

	"github.com/intervention-engine/fhir/server"
)

func main() {
	smartAuth := flag.Bool("smart", false, "Enables SMART Authorization")
	log := flag.Bool("log", false, "Enable request logging")
	flag.Parse()
	s := server.NewServer("localhost")

	config := server.Config{UseSmartAuth: *smartAuth, UseLoggingMiddleware: *log}
	s.Run(config)
}

func HomeHandler(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(rw, "FHIR Server Yay! \\o/")
}
