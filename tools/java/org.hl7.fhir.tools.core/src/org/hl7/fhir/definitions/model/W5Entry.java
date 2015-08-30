package org.hl7.fhir.definitions.model;

import java.util.HashSet;
import java.util.Set;

public class W5Entry {

  private String code;
  private String description;
  private boolean display;
  private Set<String> subClasses = new HashSet<String>();
  
  
  public W5Entry(String code, String description, boolean display, String subclasses) {
    super();
    this.code = code;
    this.description = description;
    this.display = display;
    if (subclasses != null) {
      String[] parts = subclasses.split("\\,");
      for (String s : parts)
        subClasses.add(s);
    }
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
  public Set<String> getSubClasses() {
    return subClasses;
  }
  
  
}
