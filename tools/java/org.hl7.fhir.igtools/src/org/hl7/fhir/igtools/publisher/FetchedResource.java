package org.hl7.fhir.igtools.publisher;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.hl7.fhir.r5.elementmodel.Element;
import org.hl7.fhir.r5.model.Resource;

import com.google.gson.JsonObject;

public class FetchedResource {
  private String id;
  private String title;
  private Resource resource;
  private Element element;
  private JsonObject config;
  private boolean validated;
  private List<String> profiles = new ArrayList<String>();
  private boolean snapshotted;
  private String exampleUri;
  private boolean ValidateByUserData;
  private HashSet<FetchedResource> examples = new HashSet<FetchedResource>();

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
    return title == null ? element.fhirType()+" " +id : title;
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
  public boolean isSnapshotted() {
    return snapshotted;
  }
  public void setSnapshotted(boolean snapshotted) {
    this.snapshotted = snapshotted;
    
  }
  public Object getLocalRef() {
    return element.fhirType()+"/"+id;
  }

  public String getExampleUri() {
    return exampleUri;
  }  

  public void setExampleUri(String exampleUri) {
    this.exampleUri = exampleUri;
  }  

  public HashSet<FetchedResource> getExamples() {
    return examples;
  }  

  public void addExample(FetchedResource r) {
    this.examples.add(r);
  }
  
  public boolean isValidateByUserData() {
    return ValidateByUserData;
  }
  public void setValidateByUserData(boolean validateByUserData) {
    ValidateByUserData = validateByUserData;
  }
  
  
}
