package org.hl7.fhir.tools.publisher;

import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.dstu3.elementmodel.Element;

public class DefinitionsUsageTracker {

  private Map<String, ResourceDefn> definitions;

  public DefinitionsUsageTracker(Definitions definitions) {
    this.definitions = definitions.getResources();
  }

  public DefinitionsUsageTracker(Map<String, ResourceDefn> definitions) {
    this.definitions = definitions;
  }

  public void updateUsage(org.hl7.fhir.dstu3.elementmodel.Element ex) {
    ResourceDefn rd = definitions.get(ex.fhirType());
    if (rd != null) {
      usage(ex, rd.getRoot());
    }
    
  }

  private void usage(Element instance, ElementDefn definition) {
    definition.setCoveredByExample(true);
    for (Element c : instance.getChildren()) {
      ElementDefn cd = null;
      for (ElementDefn t : definition.getElements()) {
        if (c.getName().equals(t.getName()))
          cd = t;
      }
      if (cd != null)
        usage(c, cd);
    }
    
  }


}
