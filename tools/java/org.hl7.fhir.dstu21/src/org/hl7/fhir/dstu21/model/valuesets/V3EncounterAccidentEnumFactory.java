package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3EncounterAccidentEnumFactory implements EnumFactory<V3EncounterAccident> {

  public V3EncounterAccident fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3EncounterAccident code '"+codeString+"'");
  }

  public String toCode(V3EncounterAccident code) {
    return "?";
  }


}

