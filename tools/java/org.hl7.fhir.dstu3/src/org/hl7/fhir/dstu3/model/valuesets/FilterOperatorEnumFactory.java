package org.hl7.fhir.dstu3.model.valuesets;

import org.hl7.fhir.dstu3.model.EnumFactory;

public class FilterOperatorEnumFactory implements EnumFactory<FilterOperator> {

  public FilterOperator fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("=".equals(codeString))
      return FilterOperator.EQUAL;
    if ("is-a".equals(codeString))
      return FilterOperator.ISA;
    if ("is-not-a".equals(codeString))
      return FilterOperator.ISNOTA;
    if ("regex".equals(codeString))
      return FilterOperator.REGEX;
    if ("in".equals(codeString))
      return FilterOperator.IN;
    if ("not-in".equals(codeString))
      return FilterOperator.NOTIN;
    throw new IllegalArgumentException("Unknown FilterOperator code '"+codeString+"'");
  }

  public String toCode(FilterOperator code) {
    if (code == FilterOperator.EQUAL)
      return "=";
    if (code == FilterOperator.ISA)
      return "is-a";
    if (code == FilterOperator.ISNOTA)
      return "is-not-a";
    if (code == FilterOperator.REGEX)
      return "regex";
    if (code == FilterOperator.IN)
      return "in";
    if (code == FilterOperator.NOTIN)
      return "not-in";
    return "?";
  }

    public String toSystem(FilterOperator code) {
      return code.getSystem();
      }

}

