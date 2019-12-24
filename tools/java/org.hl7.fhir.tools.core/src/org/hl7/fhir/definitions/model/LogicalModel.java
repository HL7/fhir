package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.ResourceDefn.PointSpec;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.utilities.Utilities;

public class LogicalModel {
 
  private String id;
  private String source;
  private ResourceDefn resource;
  private StructureDefinition definition;
  private WorkGroup wg;
  private Map<String, PointSpec> layout = new HashMap<String, PointSpec>();
  private List<String> implementations = new ArrayList<>();
  
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

  public Map<String, PointSpec> getLayout() {
    return layout;
  }

  public String getMappingUrl() {
    return resource.getMappingUrl();
  }

  public List<String> getImplementations() {
    return implementations;
  }
  

}
