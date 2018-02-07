package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class ResponseObjectWriter extends BaseWriter {

  public ResponseObjectWriter(JsonObject object) {
    super(object);
  }

  public ResponseObjectWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  

  public HeaderWriter header(String name) {
    return new HeaderWriter(ensureMapObject("headers", name));
  }
  
  public MediaTypeObjectWriter content(String type) {
    return new MediaTypeObjectWriter(ensureMapObject("content", type));
  }
  
}
