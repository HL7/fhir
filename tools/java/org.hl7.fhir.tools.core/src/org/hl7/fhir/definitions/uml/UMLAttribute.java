package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r5.model.Enumerations.BindingStrength;

public class UMLAttribute extends UMLFeature {

  private UMLType type; 
  private List<String> types = new ArrayList<>();
  private List<String> targets = new ArrayList<>();
  private BindingStrength binding;
  private String valueSet;
  
  public UMLAttribute(String name) {
    super(name);
  }
  
  public UMLAttribute(String name, String min, String max, UMLType type) {
    super(name);
    setMin(min);
    setMax(max);
    setType(type);
  }

  public UMLType getType() {
    return type;
  }
  public List<String> getTypes() {
    return types;
  }

  public List<String> getTargets() {
    return targets;
  }
  public BindingStrength getBinding() {
    return binding;
  }
  public void setBinding(BindingStrength binding) {
    this.binding = binding;
  }
  public String getValueSet() {
    return valueSet;
  }
  public void setValueSet(String valueSet) {
    this.valueSet = valueSet;
  }
  public void setType(UMLType type) {
    this.type = type;
  }

  public boolean hasTypes() {
    return !types.isEmpty();
  }

  public boolean hasTargets() {
    return !targets.isEmpty();
  }

  public boolean hasBinding() {
    return binding != null && valueSet != null;
  }



}
