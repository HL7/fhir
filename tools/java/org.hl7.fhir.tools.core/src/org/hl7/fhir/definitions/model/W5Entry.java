package org.hl7.fhir.definitions.model;

public class W5Entry {

  private String Description;
  private boolean display;
  
  
  public W5Entry(String description, boolean display) {
    super();
    Description = description;
    this.display = display;
  }
  public String getDescription() {
    return Description;
  }
  public void setDescription(String description) {
    Description = description;
  }
  public boolean isDisplay() {
    return display;
  }
  public void setDisplay(boolean display) {
    this.display = display;
  }
  
  
}
