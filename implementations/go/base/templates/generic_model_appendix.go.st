

type <ModelName>Bundle struct {
    Id     string `json:"id,omitempty"`
	Type   string `json:"resourceType,omitempty"`
	Base   string `json:"base,omitempty"`
	Total  int    `json:"total,omitempty"`
	Link   []BundleLinkComponent `json:"link,omitempty"`
	Entry  []<ModelName>BundleEntry `json:"entry,omitempty"`
}

type <ModelName>BundleEntry struct {
	Id        string `json:"id,omitempty"`
    Base      string `json:"base,omitempty"`
    Link      []BundleLinkComponent `json:"link,omitempty"`
	Resource  <ModelName> `json:"resource,omitempty"`
}

func (resource *<ModelName>) MarshalJSON() ([]byte, error) {
	x := struct {
		ResourceType string `json:"resourceType"`
		<ModelName>
	}{
		ResourceType: "<ModelName>",
		<ModelName>:  *resource,
	}
	return json.Marshal(x)
}
