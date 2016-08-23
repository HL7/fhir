package org.hl7.fhir.dstu3.test;

import static org.junit.Assert.*;

import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.dstu3.validation.ValidationEngine;
import org.junit.Assert;
import org.junit.Test;

public class ValidationEngineTests {

  @Test
  public static void testCurrentXml() throws Exception {
    System.out.println("Validate patient-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().checkAllInvariants();
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 3);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public static void testCurrentJson() throws Exception {
    System.out.println("Validate patient-example.json in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 3);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public static void test160() throws Exception {
    System.out.println("Validate patient-example.xml in v1.6.0 version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir.2016Sep\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().checkAllInvariants();
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.2016Sep\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 3);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public static void test140() throws Exception {
    System.out.println("Validate patient-example.xml in v1.4.0 version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir.2016May\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().checkAllInvariants();
    ve.getValidator().setNoInvariantChecks(true);
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir.2016May\\build\\publish\\patient-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 2);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public static void testCurrentDataElement() throws Exception {
    System.out.println("Validate dataelement-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().checkAllInvariants();
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir\\build\\publish\\dataelement-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }

  @Test
  public static void testCurrentDataElementLabMaster() throws Exception {
    System.out.println("Validate dataelement-labtestmaster-example.xml in Current version");
    ValidationEngine ve = new ValidationEngine();
    ve.loadDefinitions("C:\\work\\org.hl7.fhir\\build\\publish");
    ve.connectToTSServer("http://fhir3.healthintersections.com.au/open");
    ve.getValidator().checkAllInvariants();
    OperationOutcome op = ve.validate("C:\\work\\org.hl7.fhir\\build\\publish\\dataelement-labtestmaster-example.xml", null);
    int e = errors(op);
    int w = warnings(op);
    int h = hints(op);
    Assert.assertTrue(e == 0);
    Assert.assertTrue(w == 1);
    Assert.assertTrue(h == 0);
    System.out.println("  .. done: "+Integer.toString(e)+" errors, "+Integer.toString(w)+" warnings, "+Integer.toString(h)+" hints");
  }


  private static int errors(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.ERROR || vm.getSeverity() == IssueSeverity.FATAL)
        i++;
    }
    return i;
  }

  private static int warnings(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.WARNING)
        i++;
    }
    return i;
  }

  private static int hints(OperationOutcome op) {
    int i = 0;
    for (OperationOutcomeIssueComponent vm : op.getIssue()) {
      if (vm.getSeverity() == IssueSeverity.INFORMATION)
        i++;
    }
    return i;
  }

  public static void execute() throws Exception {
//    testCurrentXml();
//    testCurrentJson();
//    test160();
//    test140();
//    testCurrentDataElement();
    testCurrentDataElementLabMaster();
    System.out.println("Finished");
  }

}
