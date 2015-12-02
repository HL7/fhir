package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class BindingConformanceEnumFactory implements EnumFactory<BindingConformance> {

  public BindingConformance fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("required".equals(codeString))
      return BindingConformance.REQUIRED;
    if ("preferred".equals(codeString))
      return BindingConformance.PREFERRED;
    if ("example".equals(codeString))
      return BindingConformance.EXAMPLE;
    throw new IllegalArgumentException("Unknown BindingConformance code '"+codeString+"'");
  }

  public String toCode(BindingConformance code) {
    if (code == BindingConformance.REQUIRED)
      return "required";
    if (code == BindingConformance.PREFERRED)
      return "preferred";
    if (code == BindingConformance.EXAMPLE)
      return "example";
    return "?";
  }


}

