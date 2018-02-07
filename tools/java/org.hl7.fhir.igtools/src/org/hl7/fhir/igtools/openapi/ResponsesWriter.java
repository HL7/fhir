package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class ResponsesWriter extends BaseWriter {

  public ResponsesWriter(JsonObject object) {
    super(object);
  }

  public ResponseObjectWriter defaultResponse() {
    return new ResponseObjectWriter(ensureObject("default"));
  }
  
}
