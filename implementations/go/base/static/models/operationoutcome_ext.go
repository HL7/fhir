package models

import (
	"bytes"
	"fmt"
	"strings"
)

func (o *OperationOutcome) Error() string {
	if len(o.Issue) == 0 {
		return "Unspecified OperationOutome"
	}

	messages := make([]string, len(o.Issue))
	for i := range o.Issue {
		var buffer bytes.Buffer
		buffer.WriteString(fmt.Sprintf("[%s] %s: ", o.Issue[i].Severity, o.Issue[i].Code))
		if o.Issue[i].Details != nil {
			d := o.Issue[i].Details
			if d.Text != "" {
				buffer.WriteString(o.Issue[i].Details.Text)
			} else if len(d.Coding) > 0 && d.Coding[0].Display != "" {
				buffer.WriteString(d.Coding[0].Display)
			}
		}
		if o.Issue[i].Diagnostics != "" {
			buffer.WriteString(fmt.Sprintf(" (dx: %s)", o.Issue[i].Diagnostics))
		}
		messages[i] = buffer.String()
	}

	return strings.Join(messages, "\n")
}
