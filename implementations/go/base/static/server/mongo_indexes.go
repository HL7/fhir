package server

import (
	"bufio"
	"errors"
	"fmt"
	"log"
	"os"
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
	f, err := os.Open(config.IndexConfigPath)
	if err != nil {
		log.Println("[WARNING] Could not find indexes configuration file")
		return
	}
	defer f.Close()

	// parse the config file
	var indexMap = make(IndexMap)
	scanner := bufio.NewScanner(f)

	for scanner.Scan() {
		line := strings.TrimSpace(scanner.Text())

		// Skip blank lines or lines with bash-style comments
		if line != "" && !strings.HasPrefix(line, "#") {

			collectionName, index, err := parseIndex(line)

			if err != nil {
				log.Printf("[WARNING] %s\n", err.Error())
				continue
			}

			indexMap[collectionName] = append(indexMap[collectionName], index)
		}
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
}

// parseIndex parses a line from the index config file and returns a new *mgo.Index struct
func parseIndex(line string) (collectionName string, newIndex *mgo.Index, err error) {

	// Begin parsing new index from next line of file
	// format: <collection_name>.<index(es)>
	config := strings.SplitN(line, ".", 2)
	if len(config) < 2 {
		// Bad index format
		return "", nil, newParseIndexError(line, "Not of format <collection_name>.<index(es)>")
	}

	collectionName = config[0]
	if len(collectionName) == 0 {
		// No collection name provided
		return "", nil, newParseIndexError(line, "No collection name given")
	}

	indexSpec := config[1]
	if len(indexSpec) == 0 {
		// No index specification provided
		return "", nil, newParseIndexError(line, "No index key(s) given")
	}

	if string(indexSpec[0]) == "(" {
		// this is a compound index spec
		newIndex, err = parseCompoundIndex(indexSpec)
	} else {
		// this is a standard index spec
		newIndex, err = parseStandardIndex(indexSpec)
	}

	if err != nil {
		return "", nil, newParseIndexError(line, err.Error())
	}

	// build the index in the background; do not block other connections
	newIndex.Background = true
	return collectionName, newIndex, nil
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
