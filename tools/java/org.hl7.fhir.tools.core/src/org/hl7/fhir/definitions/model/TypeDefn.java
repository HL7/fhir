package org.hl7.fhir.definitions.model;

import org.hl7.fhir.instance.model.Profile;

public class TypeDefn extends ElementDefn {
  private Profile profile;

  public Profile getProfile() {
    return profile;
  }

  public void setProfile(Profile profile) {
    this.profile = profile;
  } 

  
}
