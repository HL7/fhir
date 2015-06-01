package org.hl7.fhir.definitions.model;

public class Dictionary {

  private String id;
  private String name;
  private String category;
  private String source;
  
  public Dictionary(String id, String name, String category, String source) {
    super();
    this.id = id;
    this.name = name;
    this.category = category;
    this.source = source;
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

  public String getSource() {
    return source;
  } 
  
}
