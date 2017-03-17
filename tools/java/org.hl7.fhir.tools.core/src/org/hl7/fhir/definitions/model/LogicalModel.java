package org.hl7.fhir.definitions.model;

import org.hl7.fhir.dstu3.model.StructureDefinition;

public class LogicalModel {
 
  private String id;
  private String source;
  private ResourceDefn resource;
  private StructureDefinition definition;
  private WorkGroup wg;

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

  public WorkGroup getWg() {
    return wg;
  }

  public void setWg(WorkGroup wg) {
    this.wg = wg;
  }
  
}
