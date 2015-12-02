package org.hl7.fhir.dstu21.model.valuesets;

import org.hl7.fhir.dstu21.model.EnumFactory;

public class Exception_EnumFactory implements EnumFactory<Exception_> {

  public Exception_ fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      return null;
    if ("student".equals(codeString))
      return Exception_.STUDENT;
    if ("disabled".equals(codeString))
      return Exception_.DISABLED;
    throw new IllegalArgumentException("Unknown Exception_ code '"+codeString+"'");
  }

  public String toCode(Exception_ code) {
    if (code == Exception_.STUDENT)
      return "student";
    if (code == Exception_.DISABLED)
      return "disabled";
    return "?";
  }


}

