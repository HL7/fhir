package org.hl7.fhir.definitions.model;

public class OperationTuplePart {

  private String name;
  private String doc;
  private int min;
  private String max;
  private String type;
  private String profile;

  public OperationTuplePart(String name, String doco, int min, String max, String type, String profile) {
    this.name = name; 
    this.doc = doco; 
    this.min = min; 
    this.max = max; 
    this.type = type;
    this.profile = profile;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public String getProfile() {
    return profile;
  }

  public void setType(String type) {
    this.type = type;
  }

  public void setProfile(String profile) {
    this.profile = profile;
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
