package org.hl7.fhir.r4.model;

import org.hl7.fhir.exceptions.FHIRException;

public class FhirVersionEnumFactory implements EnumFactory<FhirVersion> {
  public FhirVersion fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      if (codeString == null || "".equals(codeString))
        return null;
    FhirVersion c = FhirVersion.fromCode(codeString);
    if (c == null)
      throw new IllegalArgumentException("Unknown FHIRVersion code '"+codeString+"'");
    return c;
  }
  public Enumeration<FhirVersion> fromType(Base code) throws FHIRException {
    if (code == null)
      return null;
    if (code.isEmpty())
      return new Enumeration<FhirVersion>(this);
    String codeString = ((PrimitiveType) code).asStringValue();
    if (codeString == null || "".equals(codeString))
      return null;
    FhirVersion c = FhirVersion.fromCode(codeString);
    if (c == null)
      throw new IllegalArgumentException("Unknown FHIRVersion code '"+codeString+"'");
    return new Enumeration<FhirVersion>(this, c);
  }
  public String toCode(FhirVersion code) {
    if (code == null)
      return null;
    return code.toCode();
  }
  public String toSystem(FhirVersion code) {
    return code.getSystem();
  }
}