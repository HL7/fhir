package search

import (
	"fmt"
	"sync"

	"gopkg.in/mgo.v2/bson"
)

var mongoRegistry *MongoRegistry
var mongoRegistryOnce sync.Once

// GlobalMongoRegistry returns an instance of the global search parameter registry
func GlobalMongoRegistry() *MongoRegistry {
	mongoRegistryOnce.Do(func() {
		mongoRegistry = new(MongoRegistry)
		mongoRegistry.builders = make(map[string]BSONBuilder)
	})
	return mongoRegistry
}

// MongoRegistry supports the registration and lookup of Mongo search parameter implementations as BSON builders.
type MongoRegistry struct {
	buildersLock sync.RWMutex
	builders     map[string]BSONBuilder
}

// RegisterBSONBuilder registers a BSON builder for a given parameter type.
func (r *MongoRegistry) RegisterBSONBuilder(paramType string, builder BSONBuilder) {
	r.buildersLock.Lock()
	defer r.buildersLock.Unlock()
	r.builders[paramType] = builder
}

// LookupBSONBuilder looks up a BSON builder by type.  If no builder is registered, it will return an error.
func (r *MongoRegistry) LookupBSONBuilder(paramType string) (builder BSONBuilder, err error) {
	r.buildersLock.RLock()
	defer r.buildersLock.RUnlock()
	b, ok := r.builders[paramType]
	if !ok {
		return nil, fmt.Errorf("Could not find BSON builder for %s", paramType)
	}
	return b, nil
}

// BSONBuilder returns a BSON object representing the passed in search parameter.  This BSON object is expected to be
// merged with other objects and passed into Mongo's Find function.
type BSONBuilder func(param SearchParam, searcher *MongoSearcher) (object bson.M, err error)
