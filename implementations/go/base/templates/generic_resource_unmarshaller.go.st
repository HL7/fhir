

// The "<LowerName>" sub-type is needed to avoid infinite recursion in UnmarshalJSON
type <LowerName> <Name>

// Custom unmarshaller to properly unmarshal embedded resources (represented as interface{})
func (x *<Name>) UnmarshalJSON(data []byte) (err error) {
	x2 := <LowerName>{}
	if err = json.Unmarshal(data, &x2); err == nil {
<Fields:{f | <\t><\t>if x2.<f> != nil <\u007B>
<if(FieldSliceIndicators.(f))>
			for i := range x2.<f> <\u007B>
    			x2.<f>[i] = MapToResource(x2.<f>[i], true)
    		<\u007D>
<else>
			x2.<f> = MapToResource(x2.<f>, true)
<endif>
		<\u007D>}>
		*x = <Name>(x2)
	}
	return
}