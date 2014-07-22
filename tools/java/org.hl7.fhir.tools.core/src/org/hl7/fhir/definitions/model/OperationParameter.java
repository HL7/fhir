package org.hl7.fhir.definitions.model;

public class OperationParameter {

  private String name;
  private String use;
  private String doc;
  private int min;
  private String max;
  private String type;

  public OperationParameter(String name, String use, String doco, int min, String max, String type) {
    this.name = name; 
    this.use = use;
    this.doc = doco; 
    this.min = min; 
    this.max = max; 
    this.type = type;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUse() {
    return use;
  }

  public void setUse(String use) {
    this.use = use;
  }

  public String getDoc() {
    return doc;
  }

  public void setDoc(String doc) {
    this.doc = doc;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public int getMin() {
    return min;
  }

  public void setMin(int min) {
    this.min = min;
  }

  public String getMax() {
    return max;
  }

  public void setMax(String max) {
    this.max = max;
  }

  public String describeCardinality() {
    return Integer.toString(min)+".."+max;
  }

}
