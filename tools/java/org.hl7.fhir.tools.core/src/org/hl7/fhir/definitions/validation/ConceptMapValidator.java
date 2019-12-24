package org.hl7.fhir.definitions.validation;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.r5.model.ConceptMap;

public class ConceptMapValidator {

  private Definitions definitions;
  private String path;

  public ConceptMapValidator(Definitions definitions, String path) {
    this.definitions = definitions;
    this.path = path;
  }

  public void validate(ConceptMap vs, boolean internal) throws Exception {

  }

}
