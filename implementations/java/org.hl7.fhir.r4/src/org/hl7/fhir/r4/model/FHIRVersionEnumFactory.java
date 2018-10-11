package org.hl7.fhir.r4.model;

import org.hl7.fhir.exceptions.FHIRException;

public class FHIRVersionEnumFactory implements EnumFactory<FHIRVersion> {
  public FHIRVersion fromCode(String codeString) throws IllegalArgumentException {
    if (codeString == null || "".equals(codeString))
      if (codeString == null || "".equals(codeString))
        return null;
    FHIRVersion c = FHIRVersion.fromCode(codeString);
    if (c == null)
      throw new IllegalArgumentException("Unknown FHIRVersion code '"+codeString+"'");
    return c;
  }
  public Enumeration<FHIRVersion> fromType(Base code) throws FHIRException {
    if (code == null)
      return null;
    if (code.isEmpty())
      return new Enumeration<FHIRVersion>(this);
    String codeString = ((PrimitiveType) code).asStringValue();
    if (codeString == null || "".equals(codeString))
      return null;
    FHIRVersion c = FHIRVersion.fromCode(codeString);
    if (c == null)
      throw new IllegalArgumentException("Unknown FHIRVersion code '"+codeString+"'");
    return new Enumeration<FHIRVersion>(this, c);
  }
  public String toCode(FHIRVersion code) {
    if (code == null)
      return null;
    return code.toCode();
  }
  public String toSystem(FHIRVersion code) {
    return code.getSystem();
  }
}