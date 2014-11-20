package org.hl7.fhir.definitions.model;

public class WorkGroup {

  private String code;
  private String name;
  private String url;
  public WorkGroup(String code, String name, String url) {
    super();
    this.code = code;
    this.name = name;
    this.url = url;
  }
  public String getCode() {
    return code;
  }
  public String getName() {
    return name;
  }
  public String getUrl() {
    return url;
  }
  
  
}
