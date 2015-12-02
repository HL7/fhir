package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3SpecialArrangementEnumFactory implements EnumFactory<V3SpecialArrangement> {

  public V3SpecialArrangement fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3SpecialArrangement code '"+codeString+"'");
  }

  public String toCode(V3SpecialArrangement code) {
    return "?";
  }


}

