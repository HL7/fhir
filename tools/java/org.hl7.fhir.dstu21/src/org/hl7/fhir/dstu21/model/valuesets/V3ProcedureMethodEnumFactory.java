package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ProcedureMethodEnumFactory implements EnumFactory<V3ProcedureMethod> {

  public V3ProcedureMethod fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3ProcedureMethod code '"+codeString+"'");
  }

  public String toCode(V3ProcedureMethod code) {
    return "?";
  }


}

