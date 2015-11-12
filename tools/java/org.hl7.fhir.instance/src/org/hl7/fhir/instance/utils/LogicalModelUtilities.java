package org.hl7.fhir.instance.utils;

import java.util.List;

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.StructureDefinition;

public class LogicalModelUtilities {

  private IWorkerContext context;
  
  /**
   * Given a logical model, with a Logical Mapping column, 
   * generate profiles consistent with it 
   * 
   * @param logical
   * @return
   */
  public List<StructureDefinition> generateProfiles(StructureDefinition logical) {
    return null;
  }
  
  public Bundle generateExamples(StructureDefinition logical) {
    return null;
  }
  
  
}
