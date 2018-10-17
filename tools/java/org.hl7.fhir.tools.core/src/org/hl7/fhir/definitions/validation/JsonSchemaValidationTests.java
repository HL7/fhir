package org.hl7.fhir.definitions.validation;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.everit.json.schema.ValidationException;
import org.everit.json.schema.loader.SchemaLoader;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.junit.Test;

public class JsonSchemaValidationTests {
  static private org.everit.json.schema.Schema jschema;

  @Test
  public void testBundle() throws FileNotFoundException, IOException {
    testFile("bundle-transaction");
  }

  @Test
  public void testBundleTransaction() throws FileNotFoundException, IOException {
    testFile("bundle-transaction");
  }

  @Test
  public void testBundleResponse() throws FileNotFoundException, IOException {
    testFile("bundle-response");
  }


  @Test
  public void testConformanceRle() throws FileNotFoundException, IOException {
    testFile("ehrsrle\\conformance-ehrs-rle");
  }


  @Test
  public void testCareplanIntegrated() throws FileNotFoundException, IOException {
    testFile("careplan-example-integrated");
  }


  @Test
  public void testGuidanceresponseExample() throws FileNotFoundException, IOException {
    testFile("guidanceresponse-example");
  }


  @Test
  public void testMeasureBreastfeeding() throws FileNotFoundException, IOException {
    testFile("measure-exclusive-breastfeeding");
  }


  @Test
  public void testMeasureCMS146Example() throws FileNotFoundException, IOException {
    testFile("measure-cms146-example");
  }


  @Test
  public void testMeasureCompAExample() throws FileNotFoundException, IOException {
    testFile("measure-component-a-example");
  }


  @Test
  public void testMeasureCompBExample() throws FileNotFoundException, IOException {
    testFile("measure-component-b-example");
  }


  @Test
  public void testMeasurePredExample() throws FileNotFoundException, IOException {
    testFile("measure-predecessor-example");
  }


  @Test
  public void testMeasureReportExample() throws FileNotFoundException, IOException {
    testFile("measurereport-cms146-cat3-example");
  }


  @Test
  public void testMeasureReportExampleB() throws FileNotFoundException, IOException {
    testFile("measurereport-cms146-cat2-example");
  }


  @Test
  public void testMeasureReportCExample() throws FileNotFoundException, IOException {
    testFile("measurereport-cms146-cat1-example");
  }


  @Test
  public void testPlandefinitionExample() throws FileNotFoundException, IOException {
    testFile("plandefinition-protocol-example");
  }


  @Test
  public void testPlandefinitionExampleOpt() throws FileNotFoundException, IOException {
    testFile("plandefinition-options-example");
  }


  @Test
  public void testProvenanceExample() throws FileNotFoundException, IOException {
    testFile("provenance-example");
  }


  @Test
  public void testQuestionnaireAssessment() throws FileNotFoundException, IOException {
    testFile("questionnaire-zika-virus-exposure-assessment");
  }


  @Test
  public void testQcqifExample() throws FileNotFoundException, IOException {
    testFile("cqif\\questionnaire-cqif-example");
  }


  @Test
  public void testRequestgroupExample() throws FileNotFoundException, IOException {
    testFile("requestgroup-example");
  }


  @Test
  public void testRequestgroupExample1() throws FileNotFoundException, IOException {
    testFile("requestgroup-kdn5-example");
  }


  @Test
  public void testtestreporEexample() throws FileNotFoundException, IOException {
    testFile("testreport-example");
  }


  @Test
  public void testtestscriptexample() throws FileNotFoundException, IOException {
    testFile("testscript-example");
  }


  @Test
  public void testtestscripthistory() throws FileNotFoundException, IOException {
    testFile("testscript-example-history");
  }


  @Test
  public void testtestscriptmultisystem() throws FileNotFoundException, IOException {
    testFile("testscript-example-multisystem");
  }


  @Test
  public void testtestscriptreadtest() throws FileNotFoundException, IOException {
    testFile("testscript-example-readtest");
  }


  @Test
  public void testtestscriptrule() throws FileNotFoundException, IOException {
    testFile("testscript-example-rule");
  }


  @Test
  public void testtestscriptsearch() throws FileNotFoundException, IOException {
    testFile("testscript-example-search");
  }


  @Test
  public void testtestscriptupdate() throws FileNotFoundException, IOException {
    testFile("testscript-example-update");
  }


  @Test
  public void testvaluesetexpansion() throws FileNotFoundException, IOException {
    testFile("valueset-example-expansion");
  }


  @Test
  public void testehrsrle() throws FileNotFoundException, IOException {
    testFile("ehrsrle\\conformance-ehrs-rle");
  }


  @Test
  public void testcqifexample() throws FileNotFoundException, IOException {
    testFile("cqif\\questionnaire-cqif-example");
  }


  @Test
  public void testcqifexampleq() throws FileNotFoundException, IOException {
    testFile("cqif\\questionnaire-cqif-example");
  }



  @Test
  public void testPatient() throws FileNotFoundException, IOException {
    testFile("patient-example");
  }

  public void testFile(String name) throws FileNotFoundException, IOException {
    if (jschema == null) {
      String source = TextFile.fileToString(Utilities.path("C:\\work\\org.hl7.fhir\\build\\publish", "fhir.schema.json"));
      JSONObject rawSchema = new JSONObject(new JSONTokener(source));
      jschema = SchemaLoader.load(rawSchema);
    }
    
    JSONObject jo = new JSONObject(new JSONTokener(new CSFileInputStream(Utilities.path("C:\\work\\org.hl7.fhir\\build\\publish", name+".json"))));
    try {
      jschema.validate(jo);
    } catch (ValidationException e) {
      System.out.println(e.getMessage());
//      e.getCausingExceptions().stream()
//          .map(ValidationException::getMessage)
//          .forEach(System.out::println);
      throw e;
    }
  }

}
