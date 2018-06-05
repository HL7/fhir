package org.hl7.fhir.validation.r4.tests;

import static org.junit.Assert.*;

import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.validation.ValidationEngine;
import org.hl7.fhir.r4.validation.Validator;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.junit.Before;
import org.junit.Test;

public class CDAValidationTestCase {

  private SimpleWorkerContext context;

  @Test
  public void test() throws Exception {
    Validator.main(new String[] {"c:\\temp\\cda.xml", "-ig", "hl7.fhir.cda"});
  }

}
