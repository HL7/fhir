package org.hl7.fhir.instance.utils;

import java.util.List;

import org.hl7.fhir.instance.model.Base;
import org.hl7.fhir.instance.utils.FHIRPathEvaluator;

public class BuildToolPathEvaluator extends FHIRPathEvaluator {

  @Override
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    throw new Error("yet to do");
  }

}
