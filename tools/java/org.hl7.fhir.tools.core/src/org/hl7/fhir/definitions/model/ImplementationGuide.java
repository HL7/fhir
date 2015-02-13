package org.hl7.fhir.definitions.model;

public class ImplementationGuide {

  private String code;
  private String name;
  private String page;
  public ImplementationGuide(String code, String name, String page) {
    super();
    this.code = code;
    this.name = name;
    this.page = page;
  }
  public String getCode() {
    return code;
  }
  public String getName() {
    return name;
  }
  public String getPage() {
    return page;
  }
  
}
