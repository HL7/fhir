package org.hl7.fhir.instance.utils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.Bundle.BundleType;

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
  
  // start at 0, and keep calling this with index++ until it returns null
  public Bundle generateExample(StructureDefinition logical, int index) {
    Bundle bnd = new Bundle();
    bnd.setType(BundleType.COLLECTION);
    bnd.setId(UUID.randomUUID().toString().toLowerCase());
    bnd.getMeta().setLastUpdated(new Date());
    boolean hasData = false;
    
    // ok, we need work through the logical model figuring out where we are 
    if (hasData)
      return null;
    else
      return bnd;
  }
  
  
}
