package search

import (
	"fmt"
	"sync"
)

var registry *Registry
var registryOnce sync.Once

// GlobalRegistry returns an instance of the global search parameter registry
func GlobalRegistry() *Registry {
	registryOnce.Do(func() {
		registry = new(Registry)
		registry.infos = make(map[string]map[string]SearchParamInfo)
		registry.parsers = make(map[string]ParameterParser)
	})
	return registry
}

// Registry supports the registration and lookup of FHIR search parameters, both standard and custom.  For custom
// search parameters, a parameter type implementation may also need to be registered.
type Registry struct {
	infosLock   sync.RWMutex
	infos       map[string]map[string]SearchParamInfo
	parsersLock sync.RWMutex
	parsers     map[string]ParameterParser
}

// RegisterParameterInfo registers search param info for a given resource and name (as represented in the info).  If the
// parameter is not of a standard fhir type (e.g., token, date, etc), then a SearchParameter for the given type should
// also be registered.
func (r *Registry) RegisterParameterInfo(param SearchParamInfo) {
	r.infosLock.Lock()
	defer r.infosLock.Unlock()
	rMap, ok := r.infos[param.Resource]
	if !ok {
		rMap = make(map[string]SearchParamInfo)
		r.infos[param.Resource] = rMap
	}
	rMap[param.Name] = param

	// For now, also register in SearchParameterDictionary
	rMap, ok = SearchParameterDictionary[param.Resource]
	if !ok {
		rMap = make(map[string]SearchParamInfo)
		SearchParameterDictionary[param.Resource] = rMap
	}
	rMap[param.Name] = param
}

// LookupParameterInfo looks up search parameter info by resource and name.  If no parameter info is registered, it will
// return an error.
func (r *Registry) LookupParameterInfo(resource, name string) (param SearchParamInfo, err error) {
	r.infosLock.RLock()
	defer r.infosLock.RUnlock()
	param, ok := r.infos[resource][name]
	if !ok {
		return SearchParamInfo{}, fmt.Errorf("Could not find info for parameter %s for resource %s", name, resource)
	}
	return param, nil
}

// RegisterParameterParser registers a parameter parser for a given type name.
func (r *Registry) RegisterParameterParser(paramType string, parser ParameterParser) {
	r.parsersLock.Lock()
	defer r.parsersLock.Unlock()
	r.parsers[paramType] = parser
}

// LookupParameterParser looks up a parameter parser by type.  If no parser is registered, it will return an error.
func (r *Registry) LookupParameterParser(paramType string) (parser ParameterParser, err error) {
	r.parsersLock.RLock()
	defer r.parsersLock.RUnlock()
	p, ok := r.parsers[paramType]
	if !ok {
		return nil, fmt.Errorf("Could not find parameter parser for %s", paramType)
	}
	return p, nil
}

// ParameterParser parses search parameter data into a SearchParam implementation.
type ParameterParser func(info SearchParamInfo, data SearchParamData) (SearchParam, error)
