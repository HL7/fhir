package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class SupplyItemEnumFactory implements EnumFactory<SupplyItem> {

  public SupplyItem fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("medication".equals(codeString))
      return SupplyItem.MEDICATION;
    if ("device".equals(codeString))
      return SupplyItem.DEVICE;
    throw new IllegalArgumentException("Unknown SupplyItem code '"+codeString+"'");
  }

  public String toCode(SupplyItem code) {
    if (code == SupplyItem.MEDICATION)
      return "medication";
    if (code == SupplyItem.DEVICE)
      return "device";
    return "?";
  }


}

