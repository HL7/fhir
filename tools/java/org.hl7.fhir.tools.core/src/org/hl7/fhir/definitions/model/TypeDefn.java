package org.hl7.fhir.definitions.model;

import org.hl7.fhir.instance.model.StructureDefinition;

public class TypeDefn extends ElementDefn {
  private StructureDefinition profile;

  public StructureDefinition getProfile() {
    return profile;
  }

  public void setProfile(StructureDefinition profile) {
    this.profile = profile;
  } 

  
}
