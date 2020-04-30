package org.hl7.fhir.definitions.uml;

public class UMLAssociation extends UMLFeature {

  public UMLAssociation(String name) {
    super(name);
  }

  private UMLClass type;

  public UMLClass getType() {
    return type;
  }

  public void setType(UMLClass type) {
    this.type = type;
  }
  

}
