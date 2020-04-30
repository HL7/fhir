package org.hl7.fhir.definitions.uml;

import java.util.ArrayList;
import java.util.List;

public class UMLEnumeration extends UMLType {

  public class UMLEnumerationCode extends UMLEntity {

    public UMLEnumerationCode(String name) {
      super(name);
    }
  }

  private List<UMLEnumerationCode> codes = new ArrayList<>();
  
  public UMLEnumeration(String name) {
    super(name);
  }

  public List<UMLEnumerationCode> getCodes() {
    return codes;
  }

}
