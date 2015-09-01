

// The "<LowerName>" sub-type is needed to avoid infinite recursion in UnmarshalJSON
type <LowerName> <Name>

// Custom unmarshaller to properly unmarshal embedded resources (represented as interface{})
func (x *<Name>) UnmarshalJSON(data []byte) (err error) {
	x2 := <LowerName>{}
	if err = json.Unmarshal(data, &x2); err == nil {
		<Fields:{f | x2.<f> = MapToResource(x2.<f>, false) <\n>*x = <Name>(x2) }>
	}
	return
}