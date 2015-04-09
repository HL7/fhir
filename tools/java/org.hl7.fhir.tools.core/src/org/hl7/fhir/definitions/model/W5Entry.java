package org.hl7.fhir.definitions.model;

public class W5Entry {

  private String code;
  private String description;
  private boolean display;
  
  
  public W5Entry(String code, String description, boolean display) {
    super();
    this.code = code;
    this.description = description;
    this.display = display;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public boolean isDisplay() {
    return display;
  }
  public void setDisplay(boolean display) {
    this.display = display;
  }
  public String getCode() {
    return code;
  }
  public void setCode(String code) {
    this.code = code;
  }
  
  
}
