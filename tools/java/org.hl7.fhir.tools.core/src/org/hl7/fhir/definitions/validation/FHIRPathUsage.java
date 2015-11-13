package org.hl7.fhir.definitions.validation;

/**
 * We haev to deal with FHIR Paths before we've built the framework that can
 * validate them. So we part them here to validate later
 * 
 * @author Grahame Grieve
 *
 */
public class FHIRPathUsage {
  private String location;
  private String resource;
  private String context;
  private String description;
  private String expression;
  private String xpath;
  
  public FHIRPathUsage(String location, String resource, String context, String description, String expression, String xpath) {
    super();
    this.location = location;
    this.resource = resource;
    this.context = context;
    this.description = description;
    this.expression = expression;
    this.xpath = xpath;
  }
  
  public FHIRPathUsage(String location, String resource, String context, String description, String expression) {
    super();
    this.location = location;
    this.resource = resource;
    this.context = context;
    this.description = description;
    this.expression = expression;
  }

  public String getLocation() {
    return location;
  }

  public String getContext() {
    return context;
  }

  public String getDescription() {
    return description;
  }

  public String getExpression() {
    return expression;
  }

  public String getXpath() {
    return xpath;
  }

  public String getResource() {
    return resource;
  }
  
  
}
