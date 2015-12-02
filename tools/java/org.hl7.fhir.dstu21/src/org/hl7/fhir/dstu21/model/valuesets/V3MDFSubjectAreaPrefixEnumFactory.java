package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MDFSubjectAreaPrefixEnumFactory implements EnumFactory<V3MDFSubjectAreaPrefix> {

  public V3MDFSubjectAreaPrefix fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("COI".equals(codeString))
      return V3MDFSubjectAreaPrefix.COI;
    if ("DIM".equals(codeString))
      return V3MDFSubjectAreaPrefix.DIM;
    if ("RIM".equals(codeString))
      return V3MDFSubjectAreaPrefix.RIM;
    if ("STW".equals(codeString))
      return V3MDFSubjectAreaPrefix.STW;
    throw new IllegalArgumentException("Unknown V3MDFSubjectAreaPrefix code '"+codeString+"'");
  }

  public String toCode(V3MDFSubjectAreaPrefix code) {
    if (code == V3MDFSubjectAreaPrefix.COI)
      return "COI";
    if (code == V3MDFSubjectAreaPrefix.DIM)
      return "DIM";
    if (code == V3MDFSubjectAreaPrefix.RIM)
      return "RIM";
    if (code == V3MDFSubjectAreaPrefix.STW)
      return "STW";
    return "?";
  }


}

