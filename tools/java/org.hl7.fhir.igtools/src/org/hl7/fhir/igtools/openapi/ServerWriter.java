package org.hl7.fhir.igtools.openapi;

import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonObject;

public class ServerWriter extends BaseWriter {


  public ServerWriter(JsonObject object) {
    super(object);
  }

  public ServerWriter description(String value) {
    if (!Utilities.noString(value))
      object.addProperty("description", value);
    return this;            
  }
  
  public ServerVariableWriter variable(String name) {
    return new ServerVariableWriter(ensureMapObject("variables", name));            
  }
}
