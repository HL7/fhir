package org.hl7.fhir.validation.r4.tests;

import org.hl7.fhir.r4.model.OperationOutcome;
import org.hl7.fhir.r4.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.r4.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.r4.test.support.TestingUtilities;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.validation.ValidationEngine;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Assert;
import org.junit.Test;

public class ValidationEngineTests {

  private static final String DEF_TX = "http://tx.fhir.org/r3";
  
  public static boolean inbuild;

  @Test
  public void testCurrentXml() throws Exception {
    if (!TestingUtilities.silent) 
    System.out.println("Validate patient-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.home(),  "publish\\patient-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    if (!TestingUtilities.silent)
      System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 0);
  }

  @Test
  public void testCurrentJson() throws Exception {
    if (!TestingUtilities.silent)
    System.out.println("Validate patient-example.json in Current version");
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.home(),  "publish\\patient-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void test140() throws Exception {
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (!TestingUtilities.silent)
    System.out.println("Validate patient-example.xml in v1.4.0 version");
    ValidationEngine ve = new ValidationEngine("C:\\work\\org.hl7.fhir.old\\org.hl7.fhir.2016May\\build\\publish", DEF_TX);
    ve.connectToTSServer(DEF_TX);
    ve.setNoInvariantChecks(true);
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.old\\org.hl7.fhir.2016May\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 1);
    Assert.assertTrue(w == 2);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void test102() throws Exception {
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (!TestingUtilities.silent)
      System.out.println("Validate patient-example.xml in v1.0.2 version");
    ValidationEngine ve = new ValidationEngine("C:\\work\\org.hl7.fhir.old\\org.hl7.fhir.dstu2\\build\\publish", DEF_TX);
    ve.connectToTSServer(DEF_TX);
    ve.setNoInvariantChecks(true);
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.old\\org.hl7.fhir.dstu2\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void testCurrentDataElement() throws Exception {
    if (!TestingUtilities.silent)
    System.out.println("Validate dataelement-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.home(),  "publish\\dataelement-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 1);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void testCurrentDataElementLabMaster() throws Exception {
    if (!TestingUtilities.silent)
    System.out.println("Validate dataelement-labtestmaster-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.home(),  "publish\\dataelement-labtestmaster-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void testCurrentDaf() throws Exception {
    // can't do DAF in the build, since it won't have been built.
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (!TestingUtilities.silent)
    System.out.println("Validate USCore patient-example.xml in Current version");
    if (!TestingUtilities.silent)
      System.out.println("  .. load FHIR from " +Utilities.path(TestingUtilities.home(),  "publish"));
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    if (!TestingUtilities.silent)
      System.out.println("  .. load IG from core and daf");
    ve.loadIg(Utilities.path(TestingUtilities.us(),  "core", "output"));
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.us(),  "core", "examples", "patient-example.xml"), null);
    if (!TestingUtilities.silent)
      for (OperationOutcomeIssueComponent issue : op.getIssue())
        System.out.println("  - "+issue.getDetails());
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }

  @Test
  public void testTransform() throws Exception {
    if (!TestingUtilities.silent)
      System.out.println("Transform CCDA");
    if (!TestingUtilities.silent)
      System.out.println("  .. load FHIR from " +Utilities.path(TestingUtilities.home(),  "publish"));
    ValidationEngine ve = new ValidationEngine(Utilities.path(TestingUtilities.home(),  "publish"), DEF_TX);
    ve.connectToTSServer(DEF_TX);
    if (!TestingUtilities.silent)
      System.out.println("  .. load CCDA from " +Utilities.path(TestingUtilities.home(),  "guides\\ccda2\\mapping\\logical"));
    ve.loadIg(Utilities.path(TestingUtilities.home(),  "guides\\ccda2\\mapping\\logical"));
    if (!TestingUtilities.silent)
      System.out.println("  .. load Maps from " +Utilities.path(TestingUtilities.home(),  "guides\\ccda2\\mapping\\map"));
    ve.loadIg(Utilities.path(TestingUtilities.home(),  "guides\\ccda2\\mapping\\map"));
    Resource r = ve.transform(Utilities.path(TestingUtilities.home(),  "guides\\ccda2\\mapping\\example\\ccd.xml"), "http://hl7.org/fhir/StructureMap/cda");
    if (!TestingUtilities.silent)
      System.out.println("  .. done");
  }

  @Test
  public void test140Telus() throws Exception {
//    if (inbuild) {
//      Assert.assertTrue(true);
//      return;
//    }
//    if (!TestingUtilities.silent)
//      System.out.println("Validate Telus Practitioner-example-practitioner.xml in 1.4.0");
//    ValidationEngine ve = new ValidationEngine();
//    if (!TestingUtilities.silent)
//      System.out.println("  .. load FHIR from C:\\temp\\igpack\\igpack.zip");
//    ve.loadDefinitions("C:\\temp\\igpack");
//    ve.connectToTSServer("http://tx.fhir.org/r3");
//    if (!TestingUtilities.silent)
//      System.out.println("  .. load IG from C:\\temp\\telus");
//    ve.loadIg("C:\\temp\\telus");
//    OperationOutcome op = ve.validate("C:\\temp\\telus\\example-a1-101-e110.xml", null);
//    int e = errors(op);
//    int w = warnings(op);
//    int h = hints(op);
//    Assert.assertTrue(e == 0);
//    Assert.assertTrue(w == 0);
//    Assert.assertTrue(h == 3);
//    if (!TestingUtilities.silent)
//      System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" information messages");
  }


  private int errors(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.ERROR || vm.getSeverity() == IssueSeverity.FATAL)
        i++;
    }
    return i;
  }

  private int warnings(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.WARNING)
        i++;
    }
    return i;
  }

  private int hints(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.INFORMATION)
        i++;
    }
    return i;
  }

  public static void execute() throws Exception {
    ValidationEngineTests self = new ValidationEngineTests();
//    self.testCurrentXml();
//    self.testCurrentJson();
//    self.test160();
//    self.test140();
//    self.testCurrentDataElement();
//    self.testCurrentDataElementLabMaster();
//    self.testCurrentDaf();
    self.test140Telus();
//    self.testTransform();
    System.out.println("Finished");
  }

}
