package org.hl7.fhir.igtools.openapi;


import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonObject;

public class InfoWriter extends BaseWriter {


  public InfoWriter(JsonObject object) {
    super(object);
  }

  public InfoWriter title(String value) {
    if (!Utilities.noString(value))
      object.addProperty("title", value);
    return this;            
  }

  public InfoWriter description(String value) {
    if (!Utilities.noString(value))
      object.addProperty("description", value);
    return this;            
  }
  
  public InfoWriter termsOfService(String value) {
    if (!Utilities.noString(value))
      object.addProperty("termsOfService", value);
    return this;            
  }

  public InfoWriter version(String value) {
    if (!Utilities.noString(value))
      object.addProperty("version", value);
    return this;            
  }

  public InfoWriter contact(String name, String email, String url) {
    JsonObject person = new JsonObject();
    person.addProperty("name", name);
    if (!Utilities.noString(email))
      person.addProperty("email", email);
    if (!Utilities.noString(url))
      person.addProperty("url", url);
    object.add("contact", person);
    return this;            
  }
  
  public InfoWriter license(String name, String url) {
    JsonObject license = new JsonObject();
    license.addProperty("name", name);
    if (!Utilities.noString(url))
      license.addProperty("url", url);
    object.add("license", license);
    return this;            
  }
  
  
}
