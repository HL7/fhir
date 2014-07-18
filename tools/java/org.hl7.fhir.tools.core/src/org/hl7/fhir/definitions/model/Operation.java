package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

public class Operation {

  private String name;
  private String use;
  private String doco;
  private List<OperationParameter> parameters = new ArrayList<OperationParameter>();
  private String title;
  private String footer;

  public Operation(String name, String use, String title, String doco, String footer) {
    this.name = name;
    this.title = title;
    this.use = use;
    this.doco = doco;
    this.footer = footer;
    
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

  public String getDoco() {
    return doco;
  }

  public void setDoco(String doco) {
    this.doco = doco;
  }

  public List<OperationParameter> getParameters() {
    return parameters ;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getFooter() {
    return footer;
  }

  public void setFooter(String footer) {
    this.footer = footer;
  }

}
