package org.hl7.fhir.r4.test;

import static org.junit.Assert.*;

import java.io.FileInputStream;

import org.fhir.ucum.UcumEssenceService;
import org.hl7.fhir.r4.context.SimpleWorkerContext;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Patient;
import org.hl7.fhir.r4.test.support.TestingUtilities;
import org.hl7.fhir.r4.utils.FHIRPathEngine;
import org.hl7.fhir.r4.utils.LiquidEngine;
import org.hl7.fhir.r4.utils.LiquidEngine.LiquidDocument;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import junit.framework.Assert;

public class LiquidEngineTests {

  private Patient patient;
  
  @Before
  public void setUp() throws Exception {
    if (patient == null)
      patient = (Patient) new XmlParser().parse(new FileInputStream(Utilities.path(TestingUtilities.content(), "patient-example.xml")));
    if (TestingUtilities.context == null) {
      SimpleWorkerContext wc = SimpleWorkerContext.fromPack(Utilities.path(TestingUtilities.content(), "definitions.xml.zip"));
      wc.setUcumService(new UcumEssenceService(Utilities.path(TestingUtilities.home(), "tests", "ucum-essence.xml")));
      TestingUtilities.context = wc;
    }
  }

  @Test
  public void testConstant() throws Exception {
    checkProcess("test", "test");
  }

  @Test
  public void testSimple() throws Exception {
    checkProcess("{{ Patient.id}}", "example");
  }

  @Test
  public void testSimple1() throws Exception {
    checkProcess("{{ Patient.id }}", "example");
  }

  @Test
  public void testSimple2() throws Exception {
    checkProcess("{{Patient.id}}", "example");
  }

  @Test
  public void testMix1() throws Exception {
    checkProcess("t{{Patient.id}}t", "texamplet");
  }

  @Test
  public void testIf() throws Exception {
    checkProcess("{% if Patient.id = 'example'%} yes {%else%} no {%endif%}", " yes ");
  }

  @Test
  public void testLoop() throws Exception {
    checkProcess("{%loop name in Patient.name%}{{name.family}}{%endloop%}", "ChalmersWindsor");
  }

  private void checkProcess(String source, String expected) throws Exception {
    LiquidEngine engine = new LiquidEngine(TestingUtilities.context, null);
    LiquidDocument doc = engine.parse(source);
    String output = engine.evaluate(doc, patient, null);
    Assert.assertTrue(expected.equals(output));
  }

}
