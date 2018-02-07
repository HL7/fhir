package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class RequestBodyWriter extends BaseWriter {

  public RequestBodyWriter(JsonObject object) {
    super(object);
  }

  public RequestBodyWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
  public RequestBodyWriter required(boolean value) {
    object.addProperty("required", value);
    return this;            
  }
  
  public MediaTypeObjectWriter content(String type) {
    return new MediaTypeObjectWriter(ensureMapObject("content", type));
  }
  
}
