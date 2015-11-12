package org.hl7.fhir.definitions.model;

import org.hl7.fhir.instance.model.StructureDefinition;

public class LogicalModel {
 
  private String id;
  private String source;
  private ResourceDefn resource;
  private StructureDefinition definition;

  public LogicalModel() {
    super();
  }

  public LogicalModel(StructureDefinition definition) {
    super();
    this.definition = definition;
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

  public StructureDefinition getDefinition() {
    return definition;
  }

  public boolean hasResource() {
    return resource != null;
  }

  public void setDefinition(StructureDefinition definition) {
    this.definition = definition;
  }

}
