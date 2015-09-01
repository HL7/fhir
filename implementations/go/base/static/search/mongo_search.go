package search

import (
	"fmt"
	"regexp"
	"strings"

	mgo "gopkg.in/mgo.v2"
	"gopkg.in/mgo.v2/bson"
)

// MongoSearcher implements FHIR searches using the Mongo database.
type MongoSearcher struct {
	db *mgo.Database
}

// NewMongoSearcher creates a new instance of a MongoSearcher, given a pointer
// to an mgo.Database.
func NewMongoSearcher(db *mgo.Database) *MongoSearcher {
	return &MongoSearcher{db}
}

// CreateQuery takes a FHIR-based Query and returns a pointer to the
// corresponding mgo.Query.  The caller is responsible for executing
// the returned query (allowing flexibility in how results are returned).
func (m *MongoSearcher) CreateQuery(query Query) *mgo.Query {
	c := m.db.C(MongoCollectionNames[query.Resource])
	o := m.createQueryObject(query)
	return c.Find(o)
}

func (m *MongoSearcher) createQueryObject(query Query) bson.M {
	result := bson.M{}
	for _, p := range m.createParamObjects(query.Resource, query.Params()) {
		merge(result, p)
	}
	return result
}

func (m *MongoSearcher) createParamObjects(resource string, params []SearchParam) []bson.M {
	results := make([]bson.M, len(params))
	for i, p := range params {
		switch p := p.(type) {
		case *CompositeParam:
			results[i] = m.createCompositeQueryObject(resource, p)
		case *DateParam:
			results[i] = m.createDateQueryObject(p)
		case *NumberParam:
			results[i] = m.createNumberQueryObject(p)
		case *QuantityParam:
			results[i] = m.createQuantityQueryObject(p)
		case *ReferenceParam:
			results[i] = m.createReferenceQueryObject(p)
		case *StringParam:
			results[i] = m.createStringQueryObject(p)
		case *TokenParam:
			results[i] = m.createTokenQueryObject(p)
		case *URIParam:
			results[i] = m.createURIQueryObject(p)
		case *OrParam:
			results[i] = m.createOrQueryObject(resource, p)
		default:
			results[i] = bson.M{}
		}
	}

	return results
}

func (m *MongoSearcher) createCompositeQueryObject(resource string, c *CompositeParam) bson.M {
	panic("Unimplemented: composite search parameters")
}

func (m *MongoSearcher) createDateQueryObject(d *DateParam) bson.M {
	if d.Prefix != "" && d.Prefix != EQ {
		panic(fmt.Sprintf("Unimplemented: date search prefix: %s", d.Prefix))
	}

	single := func(p SearchParamPath) bson.M {
		switch p.Type {
		// TODO: Fix for date (as opposed to dateTime/instant)
		case "date", "dateTime", "instant":
			return buildBSON(p.Path, bson.M{
				"time": bson.M{
					"$gte": d.Date.RangeLowIncl(),
					"$lt":  d.Date.RangeHighExcl(),
				},
			})
		case "Period":
			return buildBSON(p.Path, bson.M{
				"start.time": bson.M{
					"$lt": d.Date.RangeHighExcl(),
				},
				"$or": []bson.M{
					bson.M{
						"end.time": bson.M{
							"$gte": d.Date.RangeLowIncl(),
						},
					},
					bson.M{
						"end": nil,
					},
				},
			})
		case "Timing":
			return buildBSON(p.Path, bson.M{
				"event.time": bson.M{
					"$gte": d.Date.RangeLowIncl(),
					"$lt":  d.Date.RangeHighExcl(),
				},
			})
		default:
			return bson.M{}
		}
	}

	return orPaths(single, d.Paths)
}

func (m *MongoSearcher) createNumberQueryObject(n *NumberParam) bson.M {
	if n.Prefix != "" && n.Prefix != EQ {
		panic(fmt.Sprintf("Unimplemented: number search prefix: %s", n.Prefix))
	}

	single := func(p SearchParamPath) bson.M {
		l, _ := n.Number.RangeLowIncl().Float64()
		h, _ := n.Number.RangeHighExcl().Float64()
		return buildBSON(p.Path, bson.M{
			"$gte": l,
			"$lt":  h,
		})
	}

	return orPaths(single, n.Paths)
}

func (m *MongoSearcher) createQuantityQueryObject(q *QuantityParam) bson.M {
	if q.Prefix != "" && q.Prefix != EQ {
		panic(fmt.Sprintf("Unimplemented: quantity search prefix: %s", q.Prefix))
	}

	single := func(p SearchParamPath) bson.M {
		l, _ := q.Number.RangeLowIncl().Float64()
		h, _ := q.Number.RangeHighExcl().Float64()
		criteria := bson.M{
			"value": bson.M{
				"$gte": l,
				"$lt":  h,
			},
		}
		if q.System == "" {
			criteria["$or"] = []bson.M{
				bson.M{"code": ci(q.Code)},
				bson.M{"units": ci(q.Code)},
			}
		} else {
			criteria["code"] = ci(q.Code)
			criteria["system"] = ci(q.System)
		}
		return buildBSON(p.Path, criteria)
	}

	return orPaths(single, q.Paths)
}

func (m *MongoSearcher) createReferenceQueryObject(r *ReferenceParam) bson.M {
	single := func(p SearchParamPath) bson.M {
		criteria := bson.M{}
		id, ok := r.GetID()
		if ok {
			criteria["referenceid"] = ci(id)
			typ, ok := r.GetType()
			if ok {
				criteria["type"] = typ
			}
		} else {
			url, ok := r.GetURL()
			if ok {
				criteria["reference"] = ci(url)
			}
		}
		return buildBSON(p.Path, criteria)
	}

	return orPaths(single, r.Paths)
}

func (m *MongoSearcher) createStringQueryObject(s *StringParam) bson.M {
	single := func(p SearchParamPath) bson.M {
		switch p.Type {
		case "HumanName":
			return buildBSON(p.Path, bson.M{
				"$or": []bson.M{
					bson.M{"text": cisw(s.String)},
					bson.M{"family": cisw(s.String)},
					bson.M{"given": cisw(s.String)},
				},
			})
		case "Address":
			return buildBSON(p.Path, bson.M{
				"$or": []bson.M{
					bson.M{"text": cisw(s.String)},
					bson.M{"line": cisw(s.String)},
					bson.M{"city": cisw(s.String)},
					bson.M{"state": cisw(s.String)},
					bson.M{"postalCode": cisw(s.String)},
					bson.M{"country": cisw(s.String)},
				},
			})
		default:
			var criteria bson.RegEx
			if s.Name == "_id" {
				criteria = ci(s.String)
			} else {
				criteria = cisw(s.String)
			}
			return buildBSON(p.Path, criteria)
		}
	}

	return orPaths(single, s.Paths)
}

func (m *MongoSearcher) createTokenQueryObject(t *TokenParam) bson.M {
	single := func(p SearchParamPath) bson.M {
		criteria := bson.M{}
		switch p.Type {
		case "Coding":
			criteria = bson.M{}
			criteria["code"] = ci(t.Code)
			if !t.AnySystem {
				criteria["system"] = ci(t.System)
			}
		case "CodeableConcept":
			if t.AnySystem {
				criteria["coding.code"] = ci(t.Code)
			} else {
				criteria["coding"] = bson.M{"$elemMatch": bson.M{"system": ci(t.System), "code": ci(t.Code)}}
			}
		case "Identifier":
			criteria["value"] = ci(t.Code)
			if !t.AnySystem {
				criteria["system"] = ci(t.System)
			}
		case "ContactPoint":
			criteria["value"] = ci(t.Code)
			if !t.AnySystem {
				criteria["use"] = ci(t.System)
			}
		case "code", "boolean", "string":
			// criteria isn't a bson, so just return the right answer
			return buildBSON(p.Path, ci(t.Code))
		}

		return buildBSON(p.Path, criteria)
	}

	return orPaths(single, t.Paths)
}

func (m *MongoSearcher) createURIQueryObject(u *URIParam) bson.M {
	single := func(p SearchParamPath) bson.M {
		return buildBSON(p.Path, u.URI)
	}

	return orPaths(single, u.Paths)
}

func (m *MongoSearcher) createOrQueryObject(resource string, o *OrParam) bson.M {
	return bson.M{
		"$or": m.createParamObjects(resource, o.Items),
	}
}

func buildBSON(path string, criteria interface{}) bson.M {
	result := bson.M{}

	normalizedPath := strings.Replace(path, "[]", "", -1)
	bCriteria, ok := criteria.(bson.M)
	if ok {
		pathRegex := regexp.MustCompile("(.*\\[\\][^\\.]*)\\.?([^\\[\\]]*)")
		if m := pathRegex.FindStringSubmatch(path); m != nil && len(bCriteria) > 1 {
			// Need to use an $elemMatch because there is an array in the path
			// and the search criteria is a composite
			left := strings.Replace(m[1], "[]", "", -1)
			right := m[2]
			var resultCriteria bson.M
			if len(right) > 0 {
				resultCriteria = bson.M{}
				for k, v := range bCriteria {
					// Pull out the $or and process it separately under $elemMatch
					if isQueryOperator(k) {
						processQueryOperatorCriteria(right, k, v, resultCriteria)
					} else {
						resultCriteria[fmt.Sprintf("%s.%s", right, k)] = v
					}
				}
			} else {
				resultCriteria = bCriteria
			}
			result[left] = bson.M{"$elemMatch": resultCriteria}
		} else {
			// Path has no array or criteria is singular
			for k, v := range bCriteria {
				// Pull out the $or and process it separately as top level condition
				if isQueryOperator(k) {
					processQueryOperatorCriteria(path, k, v, result)
				} else {
					result[fmt.Sprintf("%s.%s", normalizedPath, k)] = v
				}
			}
		}
	} else {
		// Criteria is singular, so we don't care about arrays
		result[normalizedPath] = criteria
	}

	return result
}

func isQueryOperator(key string) bool {
	return len(key) > 0 && key[0] == '$'
}

func processQueryOperatorCriteria(path string, key string, value interface{}, result bson.M) {
	switch key {
	case "$or":
		processOrCriteria(path, value, result)
	default:
		criteria, ok := result[path]
		if !ok {
			criteria = bson.M{}
			result[path] = criteria
		}
		criteria.(bson.M)[key] = value
	}
}

func processOrCriteria(path string, orValue interface{}, result bson.M) {
	if ors, ok := orValue.([]bson.M); ok {
		newOrs := make([]bson.M, len(ors))
		for i := range ors {
			newOrs[i] = buildBSON(path, ors[i])
		}
		result["$or"] = newOrs
	} else {
		panic(fmt.Sprintf("$or operator used with non-array: %v", orValue))
	}
}

// Case-insensitive match
func ci(s string) bson.RegEx {
	return bson.RegEx{Pattern: fmt.Sprintf("^%s$", regexp.QuoteMeta(s)), Options: "i"}
}

// Case-insensitive starts-with
func cisw(s string) bson.RegEx {
	return bson.RegEx{Pattern: fmt.Sprintf("^%s", regexp.QuoteMeta(s)), Options: "i"}
}

// When multiple paths are present, they should be represented as an OR.
// objFunc is a function that generates a single query for a path
func orPaths(objFunc func(SearchParamPath) bson.M, paths []SearchParamPath) bson.M {
	results := make([]bson.M, 0, len(paths))
	for i := range paths {
		results = append(results, objFunc(paths[i]))
	}

	if len(results) == 1 {
		return results[0]
	}

	return bson.M{"$or": results}
}

func merge(into bson.M, from bson.M) {
	var and []bson.M
	if intoAnd, ok := into["$and"]; ok {
		and = intoAnd.([]bson.M)
	}

	for k, v := range from {
		if k == "$and" {
			and = append(and, v.([]bson.M)...)
		} else if _, ok := into[k]; ok {
			and = append(and, bson.M{k: v})
		} else {
			into[k] = v
		}
	}

	if len(and) > 0 {
		into["$and"] = and
	}
}
