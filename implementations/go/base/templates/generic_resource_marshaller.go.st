

// Custom marshaller to add the resourceType property, as required by the specification
func (resource *<Name>) MarshalJSON() ([]byte, error) {
	x := struct {
		ResourceType string `json:"resourceType"`
		<Name>
	}{
		ResourceType: "<Name>",
		<Name>:  *resource,
	}
	return json.Marshal(x)
}