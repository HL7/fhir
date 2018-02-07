package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class ComponentsWriter extends BaseWriter {

  public ComponentsWriter(JsonObject object) {
    super(object);
  }

  public ComponentsWriter schema(String name, JsonObject jsonSchema) {
    ensureMapObject("schemas", name).add("$ref", jsonSchema);
    return this;
  }
  
  public ComponentsWriter schemaRef(String name, String uri) {
    ensureMapObject("schemas", name).addProperty("$ref", uri);
    return this;
  }
}
