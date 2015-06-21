package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

public class OperationParameter {

  private String name;
  private String use;
  private String doc;
  private int min;
  private String max;
  private String type;
  private String profile;
  private List<OperationParameter> parts;
  private BindingSpecification bs;

  public OperationParameter(String name, String use, String doco, int min, String max, String type, String profile) {
    this.name = name; 
    this.use = use;
    this.doc = doco; 
    this.min = min; 
    this.max = max; 
    this.type = type;
    this.profile = profile;
    if (type.equals("Tuple"))
      parts = new ArrayList<OperationParameter>();
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

  public List<OperationParameter> getParts() {
    return parts;
  }

  public BindingSpecification getBs() {
    return bs;
  }

  public void setBs(BindingSpecification bs) {
    this.bs = bs;
  }

}
