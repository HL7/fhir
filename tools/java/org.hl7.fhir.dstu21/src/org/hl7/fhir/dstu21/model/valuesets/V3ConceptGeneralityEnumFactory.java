package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ConceptGeneralityEnumFactory implements EnumFactory<V3ConceptGenerality> {

  public V3ConceptGenerality fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("A".equals(codeString))
      return V3ConceptGenerality.A;
    if ("L".equals(codeString))
      return V3ConceptGenerality.L;
    if ("S".equals(codeString))
      return V3ConceptGenerality.S;
    throw new IllegalArgumentException("Unknown V3ConceptGenerality code '"+codeString+"'");
  }

  public String toCode(V3ConceptGenerality code) {
    if (code == V3ConceptGenerality.A)
      return "A";
    if (code == V3ConceptGenerality.L)
      return "L";
    if (code == V3ConceptGenerality.S)
      return "S";
    return "?";
  }


}

