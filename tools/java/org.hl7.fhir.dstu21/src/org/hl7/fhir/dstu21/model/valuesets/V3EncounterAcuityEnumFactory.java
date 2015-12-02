package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3EncounterAcuityEnumFactory implements EnumFactory<V3EncounterAcuity> {

  public V3EncounterAcuity fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3EncounterAcuity code '"+codeString+"'");
  }

  public String toCode(V3EncounterAcuity code) {
    return "?";
  }


}

