package org.hl7.fhir.instance.terminologies;

import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;

public class ValueSetUtilities {

  public static ValueSet makeShareable(ValueSet vs) {
    if (!vs.hasMeta())
      vs.setMeta(new Meta());
    for (UriType t : vs.getMeta().getProfile()) 
      if (t.getValue().equals("http://hl7.org/fhir/StructureDefinition/valueset-shareable-definition"))
        return vs;
    vs.getMeta().getProfile().add(new UriType("http://hl7.org/fhir/StructureDefinition/valueset-shareable-definition"));
    return vs;
  }

  public static void checkShareable(ValueSet vs) {
    if (!vs.hasMeta())
      throw new Error("ValueSet "+vs.getUrl()+" is not shareable");
    for (UriType t : vs.getMeta().getProfile()) {
      if (t.getValue().equals("http://hl7.org/fhir/StructureDefinition/valueset-shareable-definition"))
        return;
    }
    throw new Error("ValueSet "+vs.getUrl()+" is not shareable");    
  }

}
