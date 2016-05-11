package org.hl7.fhir.dstu3.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.ShExGenerator;
import org.hl7.fhir.dstu3.utils.ShExGenerator.HTMLLinkPolicy;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.utilities.TextFile;
import org.junit.Test;

public class ShexGeneratorTests {

  private void doTest(String name) throws FileNotFoundException, IOException, FHIRException {
    String workingDirectory = FileSystems.getDefault().getPath(System.getProperty("user.dir"), "..", "..", "..", "publish").toString();
    if (TestingUtilities.context == null) {
      // For the time being, put the validation entry in org/hl7/fhir/dstu3/data
      Path path = FileSystems.getDefault().getPath(workingDirectory, "validation-min.xml.zip");
      TestingUtilities.context = SimpleWorkerContext.fromPack(path.toString());
    }
    StructureDefinition sd = TestingUtilities.context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+name);
    if(sd == null) {
      throw new FHIRException("StructuredDefinition for " + name + "was null");
    }
    Path outPath = FileSystems.getDefault().getPath(workingDirectory, name+".shexn");
    TextFile.stringToFile(new ShExGenerator(TestingUtilities.context).generate(HTMLLinkPolicy.NONE, sd), outPath.toString());
  }

  @Test
  public void testId() throws FHIRException, IOException {
    doTest("id");
  }

  @Test
  public void testUri() throws FHIRException, IOException {
    doTest("uri");
  }


  @Test
  public void testObservation() throws FHIRException, IOException {
    doTest("Observation");
  }

  @Test
  public void testRef() throws FHIRException, IOException {
    doTest("Reference");
  }
  
  @Test
  public void testAccount() throws FHIRException, IOException {
    doTest("Account");
  }

  @Test
  public void testMedicationOrder() throws FHIRException, IOException {
    doTest("MedicationOrder");
  }

  @Test
  public void testAllergyIntolerance() throws FHIRException, IOException {
    doTest("AllergyIntolerance");
  }

  @Test
  public void testCoding() throws FHIRException, IOException {
    doTest("Coding");
  }

  @Test
  public void testTiming() throws FHIRException, IOException {
    doTest("Timing");
  }
}
