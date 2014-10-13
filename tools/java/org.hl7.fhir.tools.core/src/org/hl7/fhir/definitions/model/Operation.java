package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;

public class Operation {

  private String name;
  private boolean system;
  private boolean type;
  private boolean instance;
  private String kind;
  private String doco;
  private List<OperationParameter> parameters = new ArrayList<OperationParameter>();
  private String title;
  private String footer;

  public Operation(String name, boolean system, boolean type, boolean instance, String kind, String title, String doco, String footer) {
    this.name = name;
    this.title = title;
    this.system = system;
    this.type = type;
    this.instance = instance;
    this.kind = kind;
    this.doco = doco;
    this.footer = footer;
    
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  
  public boolean isSystem() {
    return system;
  }

  public void setSystem(boolean system) {
    this.system = system;
  }

  public boolean isType() {
    return type;
  }

  public void setType(boolean type) {
    this.type = type;
  }

  public boolean isInstance() {
    return instance;
  }

  public void setInstance(boolean instance) {
    this.instance = instance;
  }

	public String getKind() {
		return kind;
	}
	
	public void setKind(String kind) {
		this.kind = kind;
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
