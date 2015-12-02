package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3OrganizationNameTypeEnumFactory implements EnumFactory<V3OrganizationNameType> {

  public V3OrganizationNameType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("A".equals(codeString))
      return V3OrganizationNameType.A;
    if ("L".equals(codeString))
      return V3OrganizationNameType.L;
    if ("ST".equals(codeString))
      return V3OrganizationNameType.ST;
    throw new IllegalArgumentException("Unknown V3OrganizationNameType code '"+codeString+"'");
  }

  public String toCode(V3OrganizationNameType code) {
    if (code == V3OrganizationNameType.A)
      return "A";
    if (code == V3OrganizationNameType.L)
      return "L";
    if (code == V3OrganizationNameType.ST)
      return "ST";
    return "?";
  }


}

