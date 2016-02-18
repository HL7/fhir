package models

import (
	"errors"
	"fmt"
	"reflect"
	"strings"

	"gopkg.in/mgo.v2/bson"
)

// GetBSON translates the FHIR extension syntax to a syntax that is more suitable for storage and sorting in MongoDB.
//
// Extension {
//   Url: "http://example.org/fhir/extensions/foo",
//   ValueString: "bar",
// }
//
// becomes
//
// bson.M {
//   "@context": bson.M {
//     "foo": contextDefinition {
//       ID: "http://example.org/fhir/extensions/foo",
//       Type: "string",
//     },
//   },
//   "foo": "bar",
// }
func (e Extension) GetBSON() (interface{}, error) {
	value := reflect.ValueOf(e)
	for i := 0; i < value.NumField(); i++ {
		field := value.Field(i)
		fieldName := value.Type().Field(i).Name
		if !strings.HasPrefix(fieldName, "Value") {
			continue
		}

		var val interface{}
		switch field.Kind() {
		case reflect.Ptr, reflect.Slice, reflect.Map, reflect.Interface:
			if !field.IsNil() {
				val = field.Elem().Interface()
				break
			}
		default:
			if field.CanInterface() && !reflect.DeepEqual(field.Interface(), reflect.Zero(field.Type()).Interface()) {
				val = field.Interface()
				break
			}
		}

		if val != nil {
			return bsonExtension(e.Url, getTypeFromValueXFieldName(fieldName), val)
		}
	}

	// If we got this far, then all values were nil or zero.  This is likely an empty string.
	return bsonExtension(e.Url, "string", "")
}

func bsonExtension(url string, fhirType string, value interface{}) (extension bson.M, err error) {
	var i int
	if i = strings.LastIndex(url, "/"); i < 0 || i == (len(url)-1) {
		err = fmt.Errorf("Couldn't determine extension name for %s", url)
		return
	}
	name := url[i+1:]
	extension = bson.M{
		"@context": bson.M{
			name: contextDefinition{
				ID:   url,
				Type: fhirType,
			},
		},
		name: value,
	}
	return
}

// SetBSON translates the stored extension syntax to the FHIR extension syntax.
//
// bson.M {
//   "@context": bson.M {
//     "foo": bson.M {
//       "@id": "http://example.org/fhir/extensions/foo",
//       "@type": "string",
//     },
//   },
//   "foo": "bar",
// }
//
// becomes
//
// Extension {
//   Url: "http://example.org/fhir/extensions/foo",
//   ValueString: "bar",
// }
func (e *Extension) SetBSON(raw bson.Raw) error {
	// Since we don't know the exact structure (property names), use a streaming approach with bson.RawD
	var rd bson.RawD
	err := raw.Unmarshal(&rd)
	if err != nil {
		return err
	}

	// Ensure there are only two sub-documents, then identify them
	if len(rd) != 2 {
		return errors.New("Couldn't properly unmarshal extension; unrecognized format in BSON")
	}
	var context map[string]contextDefinition
	var dataElement bson.RawDocElem
	for i := range rd {
		switch rd[i].Name {
		case "@context":
			rd[i].Value.Unmarshal(&context)
		default:
			dataElement = rd[i]
		}
	}
	if _, ok := context[dataElement.Name]; !ok {
		return fmt.Errorf("Couldn't properly unmarshal extension; key %s not found in @context", dataElement.Name)
	}

	// Use reflection to find the value field we must set
	fhirType := context[dataElement.Name].Type
	fieldName := fmt.Sprintf("Value%s%s", strings.ToUpper(fhirType[:1]), fhirType[1:])
	field := reflect.ValueOf(e).Elem().FieldByName(fieldName)
	if !field.IsValid() {
		return fmt.Errorf("Couldn't find extension field %s", fieldName)
	} else if !field.CanSet() {
		return fmt.Errorf("Couldn't set a value for field %s", fieldName)
	}

	// Use reflection to set the field
	val := reflect.New(field.Type())
	if err := dataElement.Value.Unmarshal(val.Interface()); err != nil {
		return err
	}
	field.Set(val.Elem())

	// Now set the URL
	e.Url = context[dataElement.Name].ID

	return nil
}

type contextDefinition struct {
	ID   string `bson:"@id,omitempty"`
	Type string `bson:"@type,omitempty"`
}

// getTypeFromValueXFieldName takes in a FHIR type with an uppercase letter and fixes it so it is lowercase if
// it is a FHIR "primitive". This function has little to no value outside of the intended use case -- which is
// to create the right type based on the field names for extension Value[x] properties.
func getTypeFromValueXFieldName(valueField string) string {
	fhirType := strings.TrimPrefix(valueField, "Value")
	switch fhirType {
	case "Instant", "Time", "Date", "DateTime", "Decimal", "Boolean", "Integer", "String", "Uri", "Base64Binary",
		"UnsignedInt", "PositiveInt", "Code", "Id", "Markdown", "Oid":
		fhirType = fmt.Sprintf("%s%s", strings.ToLower(fhirType[:1]), fhirType[1:])
	}
	return fhirType
}
