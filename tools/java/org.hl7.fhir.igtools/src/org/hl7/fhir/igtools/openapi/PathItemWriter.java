package org.hl7.fhir.igtools.openapi;


import com.google.gson.JsonObject;

public class PathItemWriter extends BaseWriter {


  public PathItemWriter(JsonObject object) {
    super(object);
  }

  public PathItemWriter summary(String value) {
    object.addProperty("summary", value);
    return this;            
  }
  
  public PathItemWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }

  public OperationWriter operation(String op) {
    return new OperationWriter(ensureMapObject(op));     
  }

  
  
}
