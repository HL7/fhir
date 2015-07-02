package org.hl7.fhir.definitions.model;

import java.util.HashMap;
import java.util.Map;

public class Compartment {

  private String name;
  private String title;
  private String description;
  private String identity;
  private String membership;
  private Map<ResourceDefn, String> resources = new HashMap<ResourceDefn, String>();
  
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public Map<ResourceDefn, String> getResources() {
    return resources;
  }
  public String getIdentity() {
    return identity;
  }
  public void setIdentity(String identity) {
    this.identity = identity;
  }
  public String getMembership() {
    return membership;
  }
  public void setMembership(String membership) {
    this.membership = membership;
  }
  public String getPathForName(String name) {
    for (ResourceDefn r : resources.keySet()) {
      if (r.getName().equals(name)) 
        return resources.get(r);
    }
    return "";
  }
  public String getUri() {
    return "http://hl7.org/fhir/compartment/"+getTitle();

  }
  
  
}
