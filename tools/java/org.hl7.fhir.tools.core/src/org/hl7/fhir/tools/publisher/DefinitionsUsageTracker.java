package org.hl7.fhir.tools.publisher;

import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.r4.elementmodel.Element;
import org.hl7.fhir.utilities.Utilities;

public class DefinitionsUsageTracker {

  private Definitions definitions;
  
  public DefinitionsUsageTracker(Definitions definitions) {
    this.definitions = definitions;
  }

  public void updateUsage(org.hl7.fhir.r4.elementmodel.Element ex) throws Exception {
    ResourceDefn rd = definitions.getResources().get(ex.fhirType());
    if (rd != null) {
      usage(ex, rd.getRoot(), ex.fhirType());
    }
  }
    
  private void usage(org.hl7.fhir.r4.elementmodel.Element instance, ElementDefn definition, String path) throws Exception {
    definition.setCoveredByExample(true);
    for (Element c : instance.getChildren()) {
      String p = c.getProperty().getDefinition().getPath();
      ElementDefn ed = definitions.getElementByPath(p.split("\\."), "example usage", true);
      if (ed != null)
        usage(c, ed, path+"."+c.getName());
//      else if (!c.getName().equals("extension"))
//        System.out.println("error finding "+c.getName()+" at "+path);
    }
    
  }


}
