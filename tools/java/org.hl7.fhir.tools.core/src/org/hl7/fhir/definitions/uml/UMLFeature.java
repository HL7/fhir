package org.hl7.fhir.definitions.uml;

public abstract class UMLFeature extends UMLEntity {
  
  public UMLFeature(String name) {
    super(name);
  }
  private String min;
  private String max;

  public String getMin() {
    return min;
  }
  public void setMin(String min) {
    this.min = min;
  }
  public String getMax() {
    return max;
  }
  public void setMax(String max) {
    this.max = max;
  }
}
