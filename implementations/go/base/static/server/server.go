package main

import (
	"fmt"
	"net/http"

	"github.com/intervention-engine/fhir/server"
)

func main() {
	s := server.NewServer("localhost")

	s.Run()
}

func HomeHandler(rw http.ResponseWriter, r *http.Request) {
	fmt.Fprintln(rw, "FHIR Server Yay! \\o/")
}
