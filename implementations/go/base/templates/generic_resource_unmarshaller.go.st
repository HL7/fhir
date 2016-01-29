

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
		<if(IsResource)>
		return x.checkResourceType()
		<endif>
	}
	return
}

<if(IsResource)>
func (x *<Name>) SetBSON(raw bson.Raw) error {
	x2 := <LowerName>{}
	err := raw.Unmarshal(&x2)
	if err != nil {
		return err
	}
	*x = <Name>(x2)
	return x.checkResourceType()
}

func (x *<Name>) checkResourceType() error {
	if x.ResourceType == "" {
		x.ResourceType = "<Name>"
	} else if x.ResourceType != "<Name>" {
		return errors.New(fmt.Sprintf("Expected resourceType to be <Name>, instead received %s", x.ResourceType))
	}
	return nil
}
<endif>
