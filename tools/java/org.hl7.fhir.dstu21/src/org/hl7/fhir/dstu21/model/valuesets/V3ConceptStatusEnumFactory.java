package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3ConceptStatusEnumFactory implements EnumFactory<V3ConceptStatus> {

  public V3ConceptStatus fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("A".equals(codeString))
      return V3ConceptStatus.A;
    if ("D".equals(codeString))
      return V3ConceptStatus.D;
    if ("P".equals(codeString))
      return V3ConceptStatus.P;
    if ("R".equals(codeString))
      return V3ConceptStatus.R;
    throw new IllegalArgumentException("Unknown V3ConceptStatus code '"+codeString+"'");
  }

  public String toCode(V3ConceptStatus code) {
    if (code == V3ConceptStatus.A)
      return "A";
    if (code == V3ConceptStatus.D)
      return "D";
    if (code == V3ConceptStatus.P)
      return "P";
    if (code == V3ConceptStatus.R)
      return "R";
    return "?";
  }


}

