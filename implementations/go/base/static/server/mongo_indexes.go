package server

import (
	"errors"
	"fmt"
	"io/ioutil"
	"log"
	"strings"

	mgo "gopkg.in/mgo.v2"
)

// IndexMap is a map of index arrays with the collection name as the key. Each index array
// contains one or more *mgo.Index indexes.
type IndexMap map[string][]*mgo.Index

// ConfigureIndexes ensures that all indexes listed in the provided indexes.conf file
// are part of the Mongodb fhir database. If an index does not exist yet ConfigureIndexes
// creates a new index in the background using mgo.collection.EnsureIndex(). Depending
// on the size of the collection it may take some time before the index is created.
// This will block the current thread until the indexing completes, but will not block
// other connections to the mongo database.
func ConfigureIndexes(session *mgo.Session, config Config) {
	var err error

	db := session.DB(config.DatabaseName)

	// Read the config file
	idxConfig, err := ioutil.ReadFile(config.IndexConfigPath)
	if err != nil {
		log.Println("[WARNING] Could not find indexes configuration file")
	}

	// parse the config file
	indexMap, err := parseIndexes(string(idxConfig), config.DatabaseName)

	if err != nil {
		log.Fatal(err.Error())
	}

	// ensure all indexes in the config file
	for k := range indexMap {
		collection := db.C(k)

		for _, index := range indexMap[k] {
			log.Printf("Ensuring index: %s.%s: %s\n", config.DatabaseName, k, sprintIndexKeys(index))
			err = collection.EnsureIndex(*index)

			if err != nil {
				log.Printf("[WARNING] Could not ensure index: %s.%s: %s\n", config.DatabaseName, k, sprintIndexKeys(index))
			}
		}
	}
	session.Close()
}

func parseIndexes(fileContents string, configuredDBName string) (IndexMap, error) {
	var indexMap = make(IndexMap)
	lines := strings.Split(fileContents, "\n")
	for _, line := range lines {

		// Skip blank lines or lines with bash-style comments
		if line == "" {
			continue
		}

		if string(line[0]) == "#" {
			continue
		}

		// Begin parsing new index from next line of file
		// format: <db_name>.<collection_name>.<index(es)>
		var newIndex *mgo.Index
		var err error

		config := strings.SplitN(line, ".", 3)
		if len(config) < 3 {
			// Bad index format
			return nil, newParseIndexError(line, "Not of format <db_name>.<collection_name>.<index(es)>")
		}

		dbName := config[0]
		if dbName != configuredDBName {
			// Wrong database name
			return nil, newParseIndexError(line, "DB name does not match server configuration")
		}

		collectionName := config[1]
		if len(collectionName) == 0 {
			// No collection name provided
			return nil, newParseIndexError(line, "No collection name given")
		}

		indexSpec := config[2]
		if len(indexSpec) == 0 {
			// No index specification provided
			return nil, newParseIndexError(line, "No index key(s) given")
		}

		if string(indexSpec[0]) == "(" {
			// this is a compound index spec
			newIndex, err = parseCompoundIndex(indexSpec)
		} else {
			// this is a standard index spec
			newIndex, err = parseStandardIndex(indexSpec)
		}

		if err != nil {
			return nil, newParseIndexError(line, err.Error())
		}

		// build the index in the background; do not block other connections
		newIndex.Background = true

		indexMap[collectionName] = append(indexMap[collectionName], newIndex)
	}
	return indexMap, nil
}

// parseStandardIndex parses an index of the form:
// <db_name>.<collection_name>.<key>_(-)1
func parseStandardIndex(indexSpec string) (*mgo.Index, error) {

	key := parseIndexKey(indexSpec)

	if key == "" {
		// invalid key format, was not parsed successfully
		return nil, errors.New("Standard key not of format: <key>_(-)1")
	}

	return &mgo.Index{
		Key: []string{key},
	}, nil
}

// parseCompoundIndex parses an index of the form:
// <db_name>.<collection_name>.(<key1>_(-)1, <key2>_(-)1, ...)
func parseCompoundIndex(indexSpec string) (*mgo.Index, error) {

	// Check that the compound indexes are listed inside parentheses
	if !strings.HasPrefix(indexSpec, "(") || !strings.HasSuffix(indexSpec, ")") {
		return nil, errors.New("Compound key not of format: (<key1>_(-)1, <key2>_(-)1, ...)")
	}

	// Each element of specs is a standard key of the format <key>_(-)1
	// Note: if only one key is specified in the compound format a standard (not compound) key will be returned
	specs := strings.Split(indexSpec[1:len(indexSpec)-1], ",")

	var keys []string

	for _, spec := range specs {
		key := parseIndexKey(strings.Trim(spec, " ")) // trim leading and trailing whitespace before parsing
		if key == "" {
			return nil, errors.New("Compound key sub-key not of format: <key>_(-)1")
		}
		keys = append(keys, key)
	}
	return &mgo.Index{
		Key: keys,
	}, nil
}

// parseIndexKey converts the standard mongo index key format: "<key>_(-)1"
// to the format used by mgo.Index: "(-)<key>"
func parseIndexKey(spec string) string {

	keyAndDirection := strings.Split(spec, "_")

	if len(keyAndDirection) != 2 {
		return ""
	}

	direction := ""
	if keyAndDirection[1] == "-1" {
		direction = "-"
	}
	return fmt.Sprintf("%s%s", direction, keyAndDirection[0])
}

func newParseIndexError(indexName, reason string) error {
	return fmt.Errorf("Index '%s' is invalid: %s", indexName, reason)
}

func sprintIndexKeys(index *mgo.Index) string {

	var keystr string
	keys := index.Key

	if len(keys) == 0 {
		return ""
	}

	if len(keys) > 1 {
		keystr = "("

		for i := 0; i < len(keys)-1; i++ {
			keystr += keys[i] + ", "
		}
		keystr += keys[len(keys)-1] + ")"
	} else {
		keystr = keys[0]
	}

	return keystr
}
