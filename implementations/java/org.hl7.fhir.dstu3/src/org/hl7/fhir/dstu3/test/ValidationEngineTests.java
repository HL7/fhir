package org.hl7.fhir.dstu3.test;

import org.junit.Assert.*;

import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.validation.ValidationEngine;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Assert;
import org.junit.Test;

public class ValidationEngineTests {

  public static boolean inbuild;

  @Test
  public void testCurrentXml() throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent) 
    System.out.println("Validate patient-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.path, "publish\\patient-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 2);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void testCurrentJson() throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent)
    System.out.println("Validate patient-example.json in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.path, "publish\\patient-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 2);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void test160() throws Exception {
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }

    if (!TestingUtilities.silent)
    System.out.println("Validate patient-example.xml in v1.6.0 version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir.2016Sep\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.2016Sep\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 2);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void test140() throws Exception {
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (!TestingUtilities.silent)
    System.out.println("Validate patient-example.xml in v1.4.0 version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir.2016May\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().setNoInvariantChecks(true);
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.2016May\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void testCurrentDataElement() throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent)
    System.out.println("Validate dataelement-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.path, "publish\\dataelement-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void testCurrentDataElementLabMaster() throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent)
    System.out.println("Validate dataelement-labtestmaster-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.path, "publish\\dataelement-labtestmaster-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void testCurrentDaf() throws Exception {
    // can't do DAF in the build, since it won't have been built.
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent)
    System.out.println("Validate DAF patient-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    if (!TestingUtilities.silent)
      System.out.println("  .. load FHIR from " +Utilities.path(TestingUtilities.path, "publish")+"publish");
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    if (!TestingUtilities.silent)
      System.out.println("  .. load IG from " +Utilities.path(TestingUtilities.path, "publish")+"guides\\daf2\\output");
    ve.loadIg(Utilities.path(TestingUtilities.path, "guides\\daf2\\output"));
    OperationOutcome op = ve.validate(Utilities.path(TestingUtilities.path, "guides\\daf2\\output\\Patient-example.xml"), null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 3);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 0);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public void testTransform() throws Exception {
    if (TestingUtilities.path == null)
      TestingUtilities.path = "C:\\work\\org.hl7.fhir\\build";
    if (!TestingUtilities.silent)
      System.out.println("Transform CCDA");
    ValidationEngine ve = new ValidationEngine();
    if (!TestingUtilities.silent)
      System.out.println("  .. load FHIR from " +Utilities.path(TestingUtilities.path, "publish"));
    ve.loadDefinitions(Utilities.path(TestingUtilities.path, "publish"));
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    if (!TestingUtilities.silent)
      System.out.println("  .. load CCDA from " +Utilities.path(TestingUtilities.path, "guides\\ccda2\\cdalogical"));
    ve.loadIg(Utilities.path(TestingUtilities.path, "guides\\ccda2\\cdalogical"));
    if (!TestingUtilities.silent)
      System.out.println("  .. load Maps from " +Utilities.path(TestingUtilities.path, "guides\\ccda2\\resources\\structuremap"));
    ve.loadIg(Utilities.path(TestingUtilities.path, "guides\\ccda2\\resources\\structuremap"));
    Resource r = ve.transform(Utilities.path(TestingUtilities.path, "guides\\ccda2\\cdalogical\\examples\\ccd.xml"), "http://hl7.org/fhir/StructureMap/cda");
    if (!TestingUtilities.silent)
      System.out.println("  .. done");
  }

  @Test
  public void test140Telus() throws Exception {
    if (inbuild) {
      Assert.assertTrue(true);
      return;
    }
    if (!TestingUtilities.silent)
    System.out.println("Validate Telus Practitioner-example-practitioner.xml in 1.4.0");
    ValidationEngine ve = new ValidationEngine();
    if (!TestingUtilities.silent)
    System.out.println("  .. load FHIR from C:\\temp\\igpack\\igpack.zip");
    ve.loadDefinitions("C:\\temp\\igpack");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    if (!TestingUtilities.silent)
      System.out.println("  .. load IG from C:\\temp\\telus");
    ve.loadIg("C:\\temp\\telus");
    OperationOutcome op = ve.validate("C:\\temp\\telus\\example-a1-101-e110.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 0);
    Assert.assertTrue(h == 3);
    if (!TestingUtilities.silent)
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
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
