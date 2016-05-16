package org.hl7.fhir.tools.implementations.java;

import org.hl7.fhir.definitions.model.ElementDefn;

public class ImpliedBaseType {

  private ElementDefn root;
  private String name;

  public ImpliedBaseType(String name, ElementDefn root) {
    super();
    this.name = name;
    this.root = root;
  }

  public ElementDefn getRoot() {
    return root;
  }

  public String getName() {
    return name;
  }

}
