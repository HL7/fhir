package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class SupplyKindEnumFactory implements EnumFactory<SupplyKind> {

  public SupplyKind fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("central".equals(codeString))
      return SupplyKind.CENTRAL;
    if ("nonstock".equals(codeString))
      return SupplyKind.NONSTOCK;
    throw new IllegalArgumentException("Unknown SupplyKind code '"+codeString+"'");
  }

  public String toCode(SupplyKind code) {
    if (code == SupplyKind.CENTRAL)
      return "central";
    if (code == SupplyKind.NONSTOCK)
      return "nonstock";
    return "?";
  }


}

