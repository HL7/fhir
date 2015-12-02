package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MaterialFormEnumFactory implements EnumFactory<V3MaterialForm> {

  public V3MaterialForm fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3MaterialForm code '"+codeString+"'");
  }

  public String toCode(V3MaterialForm code) {
    return "?";
  }


}

