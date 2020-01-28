package org.hl7.fhir.definitions.uml;

public abstract class UMLEntity {

  private String name;
  private String documentation;
  
  public UMLEntity(String name) {
    super();
    this.name = name;
  }
  public String getName() {
    return name;
  }
  public String getDocumentation() {
    return documentation;
  }
  public void setDocumentation(String documentation) {
    this.documentation = documentation;
  }
  public boolean hasDocumentation() {
    return documentation != null;
  }
  
  
}
