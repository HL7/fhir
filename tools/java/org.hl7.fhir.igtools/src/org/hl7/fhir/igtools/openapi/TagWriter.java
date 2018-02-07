package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class TagWriter extends BaseWriter {


  public TagWriter(JsonObject object) {
    super(object);
  }

  public TagWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
  public ExternalDocsWriter variable(String name) {
    return new ExternalDocsWriter(ensureObject("externalDocs"));            
  }

}
