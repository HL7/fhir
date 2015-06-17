package models

import (
	"encoding/json"
	"strings"
)

type reference Reference

func (r *Reference) UnmarshalJSON(data []byte) (err error) {
	ref := reference{}
	if err = json.Unmarshal(data, &ref); err == nil {
		splitURL := strings.Split(ref.Reference, "/")
		if len(splitURL) >= 3 {
			ref.ReferencedID = splitURL[len(splitURL)-1]
			ref.Type = splitURL[len(splitURL)-2]
		}
		external := strings.HasPrefix(ref.Reference, "http")
		ref.External = &external
		*r = Reference(ref)
		return
	}
	return err
}
