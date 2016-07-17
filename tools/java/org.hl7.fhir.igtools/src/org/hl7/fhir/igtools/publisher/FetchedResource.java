package org.hl7.fhir.igtools.publisher;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.model.Resource;

import com.google.gson.JsonObject;

public class FetchedResource {
  private String id;
  private String title;
  private Resource resource;
  private Element element;
  private JsonObject config;
  private boolean validated;
  private List<String> profiles = new ArrayList<String>();

  public Resource getResource() {
    return resource;
  }
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  public Element getElement() {
    return element;
  }
  public FetchedResource setElement(Element element) {
    this.element = element;
    return this;
  }

  public String getId() {
    return id;
  }
  public FetchedResource setId(String id) {
    this.id = id;
    return this;
  }
  public String getTitle() {
    return title == null ? "{title?}" : title;
  }
  public FetchedResource setTitle(String title) {
    this.title = title;
    return this;
  }
  
  public JsonObject getConfig() {
    return config;
  }
  public void setConfig(JsonObject config) {
    this.config = config;
  }
  
  public boolean isValidated() {
    return validated;
  }
  public void setValidated(boolean validated) {
    this.validated = validated;
  }
  public List<String> getProfiles() {
    return profiles;
  }
  public String getUrlTail() {
    return "/"+element.fhirType()+"/"+id;
  }

  
}
