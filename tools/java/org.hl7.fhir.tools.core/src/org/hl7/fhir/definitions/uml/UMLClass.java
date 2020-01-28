package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.List;

public class UMLClass extends UMLType {

  public enum UMLClassType {
    Class, Interface, Pattern
  }

  private UMLClassType type;
  private boolean abstract_;
  private UMLClass specialises;
  private List<UMLAttribute> attributes = new ArrayList<>();
  private List<UMLAssociation> associations = new ArrayList<>();

  public UMLClass(String name, UMLClassType type) {
    super(name);
    this.type = type;
  }

  public UMLClassType getType() {
    return type;
  }

  public void setType(UMLClassType type) {
    this.type = type;
  }

  public boolean hasSpecialises() {
    return specialises != null;
  }

  public UMLClass getSpecialises() {
    return specialises;
  }

  public void setSpecialises(UMLClass specialises) {
    this.specialises = specialises;
  }

  public boolean isAbstract() {
    return abstract_;
  }

  public List<UMLAttribute> getAttributes() {
    return attributes;
  }

  public List<UMLAssociation> getAssociations() {
    return associations;
  }

  public void setAbstract(boolean abstract_) {
    this.abstract_ = abstract_;
  }

  public boolean hasAttributes() {
    return !attributes.isEmpty();
  }

  public boolean hasAssociations() {
    return !associations.isEmpty();
  }

}
