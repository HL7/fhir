package org.hl7.fhir.definitions.model;

public class Dictionary {

  private String id;
  private String name;
  private String category;
  private String source;
  private ImplementationGuideDefn ig; 
  
  public Dictionary(String id, String name, String category, String source, ImplementationGuideDefn ig) {
    super();
    this.id = id;
    this.name = name;
    this.category = category;
    this.source = source;
    this.ig = ig;
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

  public ImplementationGuideDefn getIg() {
    return ig;
  } 
  
}
