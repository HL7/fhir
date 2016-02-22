package org.hl7.fhir.tools.publisher;

import org.hl7.fhir.dstu3.test.FHIRPathTests;

public class PublisherTestSuites {

  public void initialTests() throws Exception {
    new FHIRPathTests().testPaths();  
  }

}
