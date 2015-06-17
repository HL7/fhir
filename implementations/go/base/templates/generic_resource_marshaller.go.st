

// Custom marshaller to add the resourceType property, as required by the specification
func (resource *<Name>) MarshalJSON() ([]byte, error) {
	resource.ResourceType = "<Name>"
	// Dereferencing the pointer to avoid infinite recursion.
	// Passing in plain old x (a pointer to <Name>), would cause this same
	// MarshallJSON function to be called again
	return json.Marshal(*resource)
}

func (x *<Name>) GetBSON() (interface{}, error) {
	x.ResourceType = "<Name>"
	// See comment in MarshallJSON to see why we dereference
	return *x, nil
}
