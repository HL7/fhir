package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.List;

public class UMLModel extends UMLEntity {
  
  private List<UMLPackage> packages = new ArrayList<>();

  
  public UMLModel(String name) {
    super(name);
  }

  public UMLModel(String name, String doco) {
    super(name);
    setDocumentation(doco);
  }

  public List<UMLPackage> getPackages() {
    return packages;
  }

  public UMLPackage getPackage(String name) {
    for (UMLPackage p : packages) {
      if (p.getName().equals(name)) {
        return p;
      }
    }
    UMLPackage p = new UMLPackage(name);
    packages.add(p);
    return p;
  }

  public boolean hasPackage(String name) {
    for (UMLPackage p : packages) {
      if (name.equals(p.getName())) {
        return true;
      }
    }
    return false;
  }
}
