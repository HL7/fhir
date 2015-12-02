package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3QueryQuantityUnitEnumFactory implements EnumFactory<V3QueryQuantityUnit> {

  public V3QueryQuantityUnit fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("CH".equals(codeString))
      return V3QueryQuantityUnit.CH;
    if ("LI".equals(codeString))
      return V3QueryQuantityUnit.LI;
    if ("PG".equals(codeString))
      return V3QueryQuantityUnit.PG;
    if ("RD".equals(codeString))
      return V3QueryQuantityUnit.RD;
    throw new IllegalArgumentException("Unknown V3QueryQuantityUnit code '"+codeString+"'");
  }

  public String toCode(V3QueryQuantityUnit code) {
    if (code == V3QueryQuantityUnit.CH)
      return "CH";
    if (code == V3QueryQuantityUnit.LI)
      return "LI";
    if (code == V3QueryQuantityUnit.PG)
      return "PG";
    if (code == V3QueryQuantityUnit.RD)
      return "RD";
    return "?";
  }


}

