package org.hl7.fhir.dstu3.utils;

import java.util.List;

import org.hl7.fhir.dstu3.model.Base;

public class BuildToolPathEvaluator extends FluentPathEngine {

  
  public BuildToolPathEvaluator(IWorkerContext worker) {
    super(worker);
    // TODO Auto-generated constructor stub
  }

  @Override
  protected void getChildrenByName(Base item, String name, List<Base> result) {
    throw new Error("yet to do");
  }

}
