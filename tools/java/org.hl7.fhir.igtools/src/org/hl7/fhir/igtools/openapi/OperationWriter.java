package org.hl7.fhir.igtools.openapi;

import com.google.gson.JsonObject;

public class OperationWriter extends BaseWriter {

  public OperationWriter(JsonObject object) {
    super(object);
  }
  
  public OperationWriter summary(String value) {
    object.addProperty("summary", value);
    return this;            
  }
  
  public OperationWriter description(String value) {
    object.addProperty("description", value);
    return this;            
  }
  

  public ExternalDocsWriter variable(String name) {
    return new ExternalDocsWriter(ensureObject("externalDocs"));            
  }
  
  public OperationWriter operationId(String value) {
    object.addProperty("operationId", value);
    return this;            
  }
  
  public OperationWriter deprecated(boolean value) {
    object.addProperty("deprecated", value);
    return this;            
  }
  

  public ServerWriter server(String url) {
    return new ServerWriter(ensureArrayObject("servers", "url", url)); 
  }
  
  public ParameterWriter parameter(String name) {
    return new ParameterWriter(ensureMapObject("parameters", name));
  }

  public OperationWriter pathRef(String name, String url) {
    ensureMapObject("parameters", name).addProperty("$ref", url);
    return this;
  }
  

  public RequestBodyWriter request() {
    return new RequestBodyWriter(ensureObject("requestBody"));
  }

  public ResponsesWriter responses() {
    return new ResponsesWriter(ensureObject("responses"));
  }

}
