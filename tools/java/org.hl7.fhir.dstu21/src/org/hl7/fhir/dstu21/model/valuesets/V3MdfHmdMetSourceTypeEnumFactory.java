package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3MdfHmdMetSourceTypeEnumFactory implements EnumFactory<V3MdfHmdMetSourceType> {

  public V3MdfHmdMetSourceType fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("C".equals(codeString))
      return V3MdfHmdMetSourceType.C;
    if ("D".equals(codeString))
      return V3MdfHmdMetSourceType.D;
    if ("I".equals(codeString))
      return V3MdfHmdMetSourceType.I;
    if ("N".equals(codeString))
      return V3MdfHmdMetSourceType.N;
    if ("R".equals(codeString))
      return V3MdfHmdMetSourceType.R;
    if ("U".equals(codeString))
      return V3MdfHmdMetSourceType.U;
    throw new IllegalArgumentException("Unknown V3MdfHmdMetSourceType code '"+codeString+"'");
  }

  public String toCode(V3MdfHmdMetSourceType code) {
    if (code == V3MdfHmdMetSourceType.C)
      return "C";
    if (code == V3MdfHmdMetSourceType.D)
      return "D";
    if (code == V3MdfHmdMetSourceType.I)
      return "I";
    if (code == V3MdfHmdMetSourceType.N)
      return "N";
    if (code == V3MdfHmdMetSourceType.R)
      return "R";
    if (code == V3MdfHmdMetSourceType.U)
      return "U";
    return "?";
  }


}

