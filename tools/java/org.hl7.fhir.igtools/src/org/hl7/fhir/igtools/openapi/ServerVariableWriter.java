package org.hl7.fhir.igtools.openapi;

import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class ServerVariableWriter extends BaseWriter {

  public ServerVariableWriter(JsonObject object) {
    super(object);
  }

  public ServerVariableWriter enumValue(List<String> values) {
    JsonArray enums = forceArray("enum");
    for (String s : values)
      enums.add(new JsonPrimitive(s));
    return this;            
  }
  

  public ServerVariableWriter defaultValue(String value) {
    object.addProperty("default", value);
    return this;            
  }
  
  public ServerVariableWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  
}
