package org.hl7.fhir.igtools.publisher;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;

import com.google.gson.JsonObject;

public class FetchedFile {
  private String path;
  private String name;
  private String id;
  
  private byte[] source;
  private long time;
  private String contentType;
  private String title;
  private ResourceType type;
  private Resource resource;
  private Element element;
  private JsonObject config;
  private List<FetchedFile> dependencies;
  
  public String getPath() {
    return path;
  }
  public void setPath(String path) {
    this.path = path;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public byte[] getSource() {
    return source;
  }
  public void setSource(byte[] source) {
    this.source = source;
  }
  public long getTime() {
    return time;
  }
  public void setTime(long time) {
    this.time = time;
  }
  public String getContentType() {
    return contentType;
  }
  public void setContentType(String contentType) {
    this.contentType = contentType;
  }
  public ResourceType getType() {
    return type;
  }
  public void setType(ResourceType type) {
    this.type = type;
  }
  public Resource getResource() {
    return resource;
  }
  public void setResource(Resource resource) {
    this.resource = resource;
  }
  public Element getElement() {
    return element;
  }
  public void setElement(Element element) {
    this.element = element;
  }
  public String getId() {
    return id;
  }
  public void setId(String id) {
    this.id = id;
  }
  public JsonObject getConfig() {
    return config;
  }
  public void setConfig(JsonObject config) {
    this.config = config;
  }
  public List<FetchedFile> getDependencies() {
    return dependencies;
  }
  public void setDependencies(List<FetchedFile> dependencies) {
    this.dependencies = dependencies;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
 
}