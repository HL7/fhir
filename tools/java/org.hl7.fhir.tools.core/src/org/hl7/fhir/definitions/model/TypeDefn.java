package org.hl7.fhir.definitions.model;

import org.hl7.fhir.r4.model.StructureDefinition;

public class TypeDefn extends ElementDefn {
  private StructureDefinition profile;
  private String fmmLevel = "1";
  
  public StructureDefinition getProfile() {
    return profile;
  }

  public void setProfile(StructureDefinition profile) {
    this.profile = profile;
  }

  public String getFmmLevel() {
    return fmmLevel;
  }

  public void setFmmLevel(String fmmLevel) {
    this.fmmLevel = fmmLevel;
  }

  
}
