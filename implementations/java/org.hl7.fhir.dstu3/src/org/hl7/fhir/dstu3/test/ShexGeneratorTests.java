package org.hl7.fhir.dstu3.test;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ShExGenerator;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.utilities.TextFile;
import org.junit.Test;

public class ShexGeneratorTests {

  private static IWorkerContext context;

  private void doTest(String name) throws FileNotFoundException, IOException, FHIRException {
    if (context == null)
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation-min.xml.zip");
    StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    TextFile.stringToFile(new ShExGenerator(context).generate(sd), "c:\\temp\\"+name+".shex");
  }
  
  @Test
  public void testAccount() throws FHIRException, IOException {
    doTest("Account");
  }
}
