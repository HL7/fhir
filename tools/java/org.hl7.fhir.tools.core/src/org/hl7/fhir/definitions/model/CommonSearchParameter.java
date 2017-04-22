package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.r4.model.SearchParameter;

public class CommonSearchParameter {

  private String code;
  private String id;
  private List<String> resources = new ArrayList<String>();

  private SearchParameter definition;
  
  public List<String> getResources() {
    return resources;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public SearchParameter getDefinition() {
    return definition;
  }

  public void setDefinition(SearchParameter definition) {
    this.definition = definition;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

}
