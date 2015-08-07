package org.hl7.fhir.definitions.model;

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.StructureDefinition;

public class ProfiledType {
  private String name;
  private String definition;
  private String description;
  private String baseType;
  private Map<String, String> rules = new HashMap<String, String>();
  
  private Invariant invariant;
  private StructureDefinition profile;
  

  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getDescription() {
    return description;
  }
  
  
  public String getDefinition() {
    return definition;
  }
  public void setDefinition(String definition) {
    this.definition = definition;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getBaseType() {
    return baseType;
  }
  public void setBaseType(String baseType) {
    this.baseType = baseType;
  }
  public Invariant getInvariant() {
    return invariant;
  }
  public void setInvariant(Invariant invariant) {
    this.invariant = invariant;
  }
  public StructureDefinition getProfile() {
    return profile;
  }
  public void setProfile(StructureDefinition profile) {
    this.profile = profile;
  }
  public Map<String, String> getRules() {
    return rules;
  }


  
  
}
