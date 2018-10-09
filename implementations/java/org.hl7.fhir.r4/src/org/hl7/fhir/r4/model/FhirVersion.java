package org.hl7.fhir.r4.model;

public enum FhirVersion {
  DSTU1,
  DSTU2,
  DST2016May,
  STU3,
  R4;

  public static FhirVersion fromString(String v) {
    if ("1.0.2".equals(v))
      return FhirVersion.DSTU2;
    if ("1.0".equals(v))
      return FhirVersion.DSTU2;
    if ("1.4.0".equals(v))
      return FhirVersion.DST2016May;
    if ("1.4".equals(v))
      return FhirVersion.DST2016May;
    if ("3.0.1".equals(v))
      return FhirVersion.STU3;
    if ("3.0".equals(v))
      return FhirVersion.STU3;
    if ("3.5.0".equals(v))
      return FhirVersion.R4;
    if ("3.6.0".equals(v))
      return FhirVersion.R4;
    return null;
  }
}
