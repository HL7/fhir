package org.hl7.fhir.dstu3.conformance;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.Type;

public class LogicalModelMode {

  private Type data;
  private List<LogicalModelMode> children;
  private ElementDefinition definition;
  private String mapping;
  
  
  public LogicalModelMode(ElementDefinition definition) {
    super();
    this.definition = definition;
    children = new ArrayList<LogicalModelMode>();
  }
  
  public LogicalModelMode(ElementDefinition definition, Type data) {
    super();
    this.definition = definition;
    this.data = data;
  }
  
  public boolean isEmpty() {
    if (data != null)
      return false;
    else if (children == null || children.isEmpty())
      return true;
    else {
      for (LogicalModelMode lmn : children) {
        if (!lmn.isEmpty())
          return false;
      }
      return true;
    }
  }

  public Type getData() {
    return data;
  }

  public List<LogicalModelMode> getChildren() {
    return children;
  }

  public ElementDefinition getDefinition() {
    return definition;
  }
  
  public boolean hasData() {
    return data != null;
  }

  public void setData(Type data) {
    this.data = data;
  }
  
  public boolean hasChildren() {
    return children != null && !children.isEmpty();
  }

  public String getMapping() {
    return mapping;
  }

  public void setMapping(String mapping) {
    this.mapping = mapping;
  }
  
  
}
