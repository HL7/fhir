package org.hl7.fhir.definitions.generators.specification;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;


public class XPathQueryGenerator {

  private Definitions definitions;

  public XPathQueryGenerator(Definitions definitions) {
    super();
    this.definitions = definitions;
  }

  public Definitions getDefinitions() {
    return definitions;
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;
  }

  public String generateXpath(List<String> list) {
    if (list.size() == 1) {
      return "f:"+list.get(0).replace(".", "/f:");
    }
    return null;
  }
}
