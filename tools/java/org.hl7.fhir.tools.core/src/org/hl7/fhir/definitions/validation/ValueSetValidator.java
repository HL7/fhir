package org.hl7.fhir.definitions.validation;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.utils.WorkerContext;

public class ValueSetValidator {

  private WorkerContext context;

  public ValueSetValidator(WorkerContext context) {
    this.context = context;
  }

  public void validate(ValueSet vs, boolean internal) throws Exception {

  }


}
