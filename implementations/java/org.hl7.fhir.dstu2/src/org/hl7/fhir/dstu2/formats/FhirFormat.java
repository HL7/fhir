package org.hl7.fhir.dstu2.formats;

public enum FhirFormat {
  XML, JSON, TURTLE, TEXT, VBAR;

  public String getExtension() {
    switch (this) {
    case JSON:
      return "json";
    case TURTLE:
      return "ttl";
    case XML:
      return "xml";
    case TEXT:
      return "txt";
    case VBAR:
      return "hl7";
    }
    return null;
  }

}
