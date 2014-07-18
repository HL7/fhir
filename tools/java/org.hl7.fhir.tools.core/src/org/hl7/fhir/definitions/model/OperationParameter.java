package org.hl7.fhir.definitions.model;

public class OperationParameter {

  private String name;
  private String use;
  private String doc;
  private String optional;
  private String type;

  public OperationParameter(String name, String use, String doco, String optional, String type) {
    this.name = name; 
    this.use = use;
    this.doc = doco; 
    this.optional = optional; 
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public String getOptional() {
    return optional;
  }

  public void setOptional(String optional) {
    this.optional = optional;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

}
