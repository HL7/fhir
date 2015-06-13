package org.hl7.fhir.definitions.model;


public class LogicalModel {
 
  private TypeDefn root;  
  private String id;
  private String source;
  private ResourceDefn resource;

  public TypeDefn getRoot()
  {
    return root;
  }

  public void setRoot(TypeDefn root)
  {
    this.root = root;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public ResourceDefn getResource() {
    return resource;
  }

  public void setResource(ResourceDefn resource) {
    this.resource = resource;
  }

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }

  
}
