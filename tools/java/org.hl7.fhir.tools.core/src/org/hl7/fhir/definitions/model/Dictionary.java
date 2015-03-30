package org.hl7.fhir.definitions.model;

public class Dictionary {

  private String id;
  private String name;
  private String category;
  public Dictionary(String id, String name, String category) {
    super();
    this.id = id;
    this.name = name;
    this.category = category;
  }
  public String getId() {
    return id;
  }
  public String getName() {
    return name;
  }
  public String getCategory() {
    return category;
  }
  
  
}
