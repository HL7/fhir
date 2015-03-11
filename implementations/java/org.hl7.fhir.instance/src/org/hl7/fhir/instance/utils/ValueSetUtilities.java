package org.hl7.fhir.instance.utils;

import org.hl7.fhir.instance.model.Meta;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;

public class ValueSetUtilities {

  public static ValueSet makeShareable(ValueSet vs) {
    if (!vs.hasMeta())
      vs.setMeta(new Meta());
    vs.getMeta().getProfile().add(new UriType("http://hl7.org/fhir/StructureDefinition/valueset-shareable-definition"));
    return vs;
  }

}
