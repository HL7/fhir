package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3EditStatusEnumFactory implements EnumFactory<V3EditStatus> {

  public V3EditStatus fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("A".equals(codeString))
      return V3EditStatus.A;
    if ("I".equals(codeString))
      return V3EditStatus.I;
    if ("O".equals(codeString))
      return V3EditStatus.O;
    if ("P".equals(codeString))
      return V3EditStatus.P;
    if ("R".equals(codeString))
      return V3EditStatus.R;
    throw new IllegalArgumentException("Unknown V3EditStatus code '"+codeString+"'");
  }

  public String toCode(V3EditStatus code) {
    if (code == V3EditStatus.A)
      return "A";
    if (code == V3EditStatus.I)
      return "I";
    if (code == V3EditStatus.O)
      return "O";
    if (code == V3EditStatus.P)
      return "P";
    if (code == V3EditStatus.R)
      return "R";
    return "?";
  }


}

