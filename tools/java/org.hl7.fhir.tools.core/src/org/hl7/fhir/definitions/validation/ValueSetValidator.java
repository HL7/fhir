package org.hl7.fhir.definitions.validation;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;

public class ValueSetValidator {

  private Definitions definitions;
  private String path;

  public ValueSetValidator(Definitions definitions, String path) {
    this.definitions = definitions;
    this.path = path;
  }

  public void validate(ValueSet vs, boolean internal) throws Exception {

    if (internal &&  vs.getDefine() != null) {
      for (ValueSetDefineConceptComponent t : vs.getDefine().getConcept())
        checkLowercase(t);
    }
  }

  private void checkLowercase(ValueSetDefineConceptComponent c) throws Exception {
//    if (c.getCode() != null && !c.getCode().getValue().toLowerCase().equals(c.getCode().getValue()))
//      throw new Exception("Value set "+path+": code '"+c.getCode().getValue()+"' is not all lowercase");    
//    for (ValueSetDefineConceptComponent t : c.getConcept())
//      checkLowercase(t);
  }

}
