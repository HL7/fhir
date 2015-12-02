package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class MaxOccursEnumFactory implements EnumFactory<MaxOccurs> {

  public MaxOccurs fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("*".equals(codeString))
      return MaxOccurs.ASTERISK;
    throw new IllegalArgumentException("Unknown MaxOccurs code '"+codeString+"'");
  }

  public String toCode(MaxOccurs code) {
    if (code == MaxOccurs.ASTERISK)
      return "*";
    return "?";
  }


}

