package server

import (
	"gopkg.in/mgo.v2"
)

var (
	MongoSession *mgo.Session
	Database     *mgo.Database
)
