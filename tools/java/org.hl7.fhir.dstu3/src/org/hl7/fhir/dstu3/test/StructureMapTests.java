package org.hl7.fhir.dstu3.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.StructureMapCompiler;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Test;

public class StructureMapTests {

  private static IWorkerContext context;

  private void testParse(String path) throws FileNotFoundException, IOException, FHIRException {
    if (context == null)
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation-min.xml.zip");
    new StructureMapCompiler(context).parse(TextFile.fileToString(Utilities.path("C:\\work\\org.hl7.fhir\\build", path)));
  }
  
  @Test
  public void testParseAny() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\any.map");
  }

  @Test
  public void testParseBL() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\bl.map");
  }

  @Test
  public void testParseED() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\ed.map");
  }

  @Test
  public void testParseCD() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\cd.map");
  }

  @Test
  public void testParseAD() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\ad.map");
  }

  @Test
  public void testParsePQ() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\pq.map");
  }

  @Test
  public void testParseIVLTS() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\ivl-ts.map");
  }

  @Test
  public void testParseCDA() throws FHIRException, IOException {
    testParse("guides\\ccda\\maps\\cda.map");
  }


}
