package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class ExternalDocsWriter extends BaseWriter {

  public ExternalDocsWriter(JsonObject object) {
    super(object);
  }
  
  public ExternalDocsWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
  public ExternalDocsWriter url(String value) {
    object.addProperty("url", value);
    return this;            
  }
  
}
