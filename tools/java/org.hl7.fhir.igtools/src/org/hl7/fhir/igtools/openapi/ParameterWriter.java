package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class ParameterWriter extends BaseWriter {

  public enum ParameterLocation {
    query, header, path, cookie;
  }
  public enum ParameterStyle {
    matrix, label, form, simple, spaceDelimited, pipeDelimited, deepObject;
  }

  public ParameterWriter(JsonObject object) {
    super(object);
  }

  public ParameterWriter in(ParameterLocation value) {
    object.addProperty("in", value.toString());
    return this;            
  }
  

  public ParameterWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
  public ParameterWriter required(boolean value) {
    object.addProperty("required", value);
    return this;            
  }
  
  public ParameterWriter deprecated(boolean value) {
    object.addProperty("deprecated", value);
    return this;            
  }
  
  public ParameterWriter allowEmptyValue(boolean value) {
    object.addProperty("allowEmptyValue", value);
    return this;            
  }
  

  public ParameterWriter style(ParameterStyle value) {
    object.addProperty("style", value.toString());
    return this;            
  }
  
  
  public ParameterWriter explode(boolean value) {
    object.addProperty("explode", value);
    return this;            
  }
  
  public ParameterWriter allowReserved(boolean value) {
    object.addProperty("allowReserved", value);
    return this;            
  }
    

  public ParameterWriter schema(JsonObject jsonSchema) {
    object.add("schema", jsonSchema);
    return this;
  }
  
  public ParameterWriter schemaRef(String name, String uri) {
    JsonObject schema = new JsonObject();
    schema.addProperty("$ref", uri);
    object.add("schema", schema);
    return this;
  }
  

  public ParameterWriter example(JsonObject example) {
    object.add("example", example);
    return this;
  }
  
}
