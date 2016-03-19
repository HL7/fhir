package models

import (
	"fmt"
	"strings"
)

func (o *OperationOutcome) Error() string {
	if len(o.Issue) == 0 {
		return "Unspecified OperationOutome"
	}

	messages := make([]string, len(o.Issue))
	for i := range o.Issue {
		issue := o.Issue[i]
		messages[i] = fmt.Sprintf("[%s] %s: %s", issue.Severity, issue.Code, issue.Diagnostics)
	}
	return strings.Join(messages, "\n")
}
