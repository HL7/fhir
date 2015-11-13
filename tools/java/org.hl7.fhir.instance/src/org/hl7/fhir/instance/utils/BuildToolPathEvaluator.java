package org.hl7.fhir.instance.utils;

import java.util.List;

import org.hl7.fhir.instance.model.Base;

public class BuildToolPathEvaluator extends FHIRPathEvaluator {

  
  public BuildToolPathEvaluator(IWorkerContext worker) {
    super(worker);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    throw new Error("yet to do");
  }

}
