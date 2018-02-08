package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class MediaTypeObjectWriter extends BaseWriter {

  public MediaTypeObjectWriter(JsonObject object) {
    super(object);
  }

  public MediaTypeObjectWriter schema(JsonObject jsonSchema) {
    object.add("schema", jsonSchema);
    return this;
  }
  
  public MediaTypeObjectWriter schemaRef(String uri) {
    JsonObject schema = new JsonObject();
    schema.addProperty("$ref", uri);
    object.add("schema", schema);
    return this;
  }
  
  public MediaTypeObjectWriter example(JsonObject example) {
    object.add("example", example);
    return this;
  }
  
}
