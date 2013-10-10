package org.hl7.fhir.definitions.generators.specification;

import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.publisher.QaTracker;
import org.hl7.fhir.utilities.Logger;


public class XPathQueryGenerator {

  private Definitions definitions;
  private Logger log;
  private QaTracker qa;

  public XPathQueryGenerator(Definitions definitions, Logger log, QaTracker qa) {
    super();
    this.definitions = definitions;
    this.log = log;
    this.qa = qa;
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
