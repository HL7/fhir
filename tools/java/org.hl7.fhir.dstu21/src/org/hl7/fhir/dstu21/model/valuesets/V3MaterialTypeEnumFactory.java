package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MaterialTypeEnumFactory implements EnumFactory<V3MaterialType> {

  public V3MaterialType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3MaterialType code '"+codeString+"'");
  }

  public String toCode(V3MaterialType code) {
    return "?";
  }


}

