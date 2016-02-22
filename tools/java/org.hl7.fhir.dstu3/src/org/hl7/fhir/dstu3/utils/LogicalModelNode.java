package org.hl7.fhir.dstu3.utils;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.utils.FHIRPathEngine.MapExpression;

public class LogicalModelNode {

  private Type data;
  private List<LogicalModelNode> children;
  private ElementDefinition definition;
  private String mapping;
  private List<MapExpression> expressions;
  
  
  public LogicalModelNode(ElementDefinition definition) {
    super();
    this.definition = definition;
    children = new ArrayList<LogicalModelNode>();
  }
  
  public LogicalModelNode(ElementDefinition definition, Type data) {
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
      for (LogicalModelNode lmn : children) {
        if (!lmn.isEmpty())
          return false;
      }
      return true;
    }
  }

  public Type getData() {
    return data;
  }

  public List<LogicalModelNode> getChildren() {
    return children;
  }

  public ElementDefinition getDefinition() {
    return definition;
  }

  public List<MapExpression> getExpressions() {
    return expressions;
  }

  public void setExpressions(List<MapExpression> expressions) {
    this.expressions = expressions;
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
