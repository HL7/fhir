package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class HeaderWriter extends ParameterWriter {

  public HeaderWriter(JsonObject object) {
    super(object);
  }

  public ParameterWriter in(ParameterLocation value) {
    if (value != ParameterLocation.header)
      throw new Error("Invalid value for header");
    super.in(value);
    return this;
  }  
}
