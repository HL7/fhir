

type <ModelName>Bundle struct {
	Type         string	`json:"resourceType,omitempty"`
	Title        string	`json:"title,omitempty"`
	Id           string	`json:"id,omitempty"`
	Updated      time.Time	`json:"updated,omitempty"`
	TotalResults int	`json:"totalResults,omitempty"`
	Entry        []<ModelName>BundleEntry	`json:"entry,omitempty"`
	Category     <ModelName>Category	`json:"category,omitempty"`
}

type <ModelName>BundleEntry struct {
	Title    string	`json:"title,omitempty"`
	Id       string	`json:"id,omitempty"`
	Content  <ModelName>	`json:"content,omitempty"`
	Category <ModelName>Category	`json:"category,omitempty"`
}

type <ModelName>Category struct {
	Term   string	`json:"term,omitempty"`
	Label  string	`json:"label,omitempty"`
	Scheme string	`json:"scheme,omitempty"`
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
