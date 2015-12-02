package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class V3PeriodicIntervalOfTimeAbbreviationEnumFactory implements EnumFactory<V3PeriodicIntervalOfTimeAbbreviation> {

  public V3PeriodicIntervalOfTimeAbbreviation fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    throw new IllegalArgumentException("Unknown V3PeriodicIntervalOfTimeAbbreviation code '"+codeString+"'");
  }

  public String toCode(V3PeriodicIntervalOfTimeAbbreviation code) {
    return "?";
  }


}

