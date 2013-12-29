package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;

public interface ConceptLocator {
    public class ValidationResult {
      private IssueSeverity severity;
      private String message;
      public ValidationResult(IssueSeverity severity, String message) {
        super();
        this.severity = severity;
        this.message = message;
      }
      public IssueSeverity getSeverity() {
        return severity;
      }
      public String getMessage() {
        return message;
      }
      
      
  }
    public ValueSetDefineConceptComponent locate(String system, String code);
    public ValidationResult validate(String system, String code, String display);
    public boolean verifiesSystem(String system);
  }