package org.hl7.fhir.convertors.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.convertors.R2ToR3Loader;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

@RunWith(Parameterized.class)
public class R2R3ConversionTests {

  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    root = "C:\\work\\org.hl7.fhir\\build"; // todo: where else to get this from?
    String srcFile = Utilities.path(root, "source", "release2", "examples.zip");
    ZipInputStream stream = new ZipInputStream(new FileInputStream(srcFile));

    Map<String, byte[]> examples = new HashMap<String, byte[]>();
    ZipEntry entry;
    while((entry = stream.getNextEntry())!=null) {
      String n = entry.getName();
      byte[] buffer = new byte[2048];
      ByteArrayOutputStream output = null;
      output = new ByteArrayOutputStream();
      int len = 0;
      while ((len = stream.read(buffer)) > 0)
        output.write(buffer, 0, len);
      examples.put(n, output.toByteArray());
    }
    List<Object[]> objects = new ArrayList<Object[]>(examples.size());

    for (String id : examples.keySet()) {
      objects.add(new Object[] { id, examples.get(id)});
    }

    return objects;
  }
  
  private static String root;
  private static SimpleWorkerContext contextR2;
  private static SimpleWorkerContext contextR3;

  private final byte[] content;
  private final String name;

  public R2R3ConversionTests(String name, byte[] content) {
    this.name = name;
    this.content = content;
  }

  @SuppressWarnings("deprecation")
  @Test
  public void test() throws FileNotFoundException, IOException, FHIRException, org.hl7.fhir.exceptions.FHIRException {
    checkLoad();
    
    StructureMapUtilities sm = new StructureMapUtilities(contextR3);
    
    // load the example (r2)
    org.hl7.fhir.dstu3.elementmodel.Element r2 = new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).parse(new ByteArrayInputStream(content));

    // load the r2 to R3 map
    String mapFile = Utilities.path(root, "implementations", "r2maps", "R2toR3", r2.fhirType()+".map");
    StructureMap s = sm.parse(TextFile.fileToString(mapFile));
    
    // convert from r2 to r3
    
    // validate against R3
    
    // load the R3 to R2 map
    mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", r2.fhirType()+".map");
    s = sm.parse(TextFile.fileToString(mapFile));
    
    // convert to R2
    
    // compare the XML
  }

  private void checkLoad() throws IOException, FHIRException {
    if (contextR2 != null)
      return;
    R2ToR3Loader ldr = new R2ToR3Loader();
    System.out.println("loading R2");
    contextR2 = new SimpleWorkerContext();
    contextR2.loadFromFile("C:\\work\\org.hl7.fhir\\build\\source\\release2\\profiles-types.xml", ldr);
    contextR2.loadFromFile("C:\\work\\org.hl7.fhir\\build\\source\\release2\\profiles-resources.xml", ldr);
    contextR2.loadFromFile("C:\\work\\org.hl7.fhir\\build\\source\\release2\\expansions.xml", ldr);
    
    System.out.println("loading R3");
    contextR3 = new SimpleWorkerContext();
    contextR3.loadFromFile("C:\\work\\org.hl7.fhir\\build\\publish\\profiles-types.xml", null);
    contextR3.loadFromFile("C:\\work\\org.hl7.fhir\\build\\publish\\profiles-resources.xml", null);
    contextR3.loadFromFile("C:\\work\\org.hl7.fhir\\build\\publish\\expansions.xml", null);

    contextR2.setExpansionProfile(new ExpansionProfile().setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()));
    contextR3.setExpansionProfile(new ExpansionProfile().setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()));
    contextR2.setName("R2");
    contextR3.setName("R3");
    System.out.println("loaded");
  }

}
