package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

public class CommonSearchParameter {

  private String code;
  private List<String> resources = new ArrayList<String>();

  public List<String> getResources() {
    return resources;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }



}
