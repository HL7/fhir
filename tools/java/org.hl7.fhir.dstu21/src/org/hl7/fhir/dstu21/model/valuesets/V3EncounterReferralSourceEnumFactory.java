package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3EncounterReferralSourceEnumFactory implements EnumFactory<V3EncounterReferralSource> {

  public V3EncounterReferralSource fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3EncounterReferralSource code '"+codeString+"'");
  }

  public String toCode(V3EncounterReferralSource code) {
    return "?";
  }


}

