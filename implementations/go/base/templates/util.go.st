package models

import "encoding/json"

/* When FHIR JSON is unmarshalled, types that are interface{} just get unmarshaled to map[string]interface{}.
   This function converts that unmarshaled map to a specific resource type. */
func MapToResource(resourceMap interface{}, asPointer bool) interface{} {
	b, _ := json.Marshal(&resourceMap)
	m := resourceMap.(map[string]interface{})
	t := m["resourceType"]
	switch t {
	<Resources:{r |
case "<r>":
	x := <r>{\}
	json.Unmarshal(b, &x)
	if asPointer {
		return &x
	\} else {
		return x
	\}
}>
	}
	return nil
}