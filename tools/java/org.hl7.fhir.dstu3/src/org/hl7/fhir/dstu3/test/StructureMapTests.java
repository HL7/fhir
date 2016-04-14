package org.hl7.fhir.dstu3.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.metamodel.Element;
import org.hl7.fhir.dstu3.metamodel.Manager;
import org.hl7.fhir.dstu3.metamodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapTransform;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.StructureMapCompiler;
import org.hl7.fhir.dstu3.utils.StructureMapTransformer;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Test;

public class StructureMapTests {

  private static IWorkerContext context;

  private void testParse(String path) throws FileNotFoundException, IOException, FHIRException {
    if (context == null)
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation-min.xml.zip");
    StructureMapCompiler scm = new StructureMapCompiler(context);
    StructureMap map = scm.parse(TextFile.fileToString(Utilities.path("C:\\work\\org.hl7.fhir\\build", path)));
    TextFile.stringToFile(scm.render(map), Utilities.path("C:\\work\\org.hl7.fhir\\build", path+".out"));
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

  @Test
  public void testLoadCDA() throws FileNotFoundException, Exception {
    if (context == null)
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation-min.xml.zip");
    
    for (String f : new File("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\CDA").list()) {
      try {
        StructureDefinition sd = (StructureDefinition) new XmlParser().parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\CDA\\"+f));
        ((SimpleWorkerContext) context).seeResource(sd.getUrl(), sd);
      } catch (Exception e) {
      }
    }

   Map<String, StructureMap> maps = new HashMap<String, StructureMap>();
   
    for (String f : new File("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\maps").list()) {
      try {
        StructureMap map = new StructureMapCompiler(context).parse(TextFile.fileToString("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\maps\\"+ f));
        maps.put(map.getUrl(), map);
      } catch (Exception e) {
      }
    }
        
    Element cda = Manager.parse(context, new FileInputStream("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\Example\\ccd.xml"), FhirFormat.XML, false);
    Manager.compose(context, cda, new FileOutputStream("C:\\work\\org.hl7.fhir\\build\\guides\\ccda\\Example\\ccd.out.xml"), FhirFormat.XML, OutputStyle.PRETTY, null);
    StructureMapTransformer transformer = new StructureMapTransformer(context, maps, null);
    transformer.transform(null, cda, maps.get("http://hl7.org/fhir/StructureMap/cda"), new Bundle());
  }
}
