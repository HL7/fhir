package org.hl7.fhir.conversion.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.convertors.R2ToR3Loader;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.utils.IResourceValidator;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.ITransformerServices;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.TransformContext;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.InstanceValidatorFactory;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.xml.sax.SAXException;

@RunWith(Parameterized.class)
public class R2R3ConversionTests implements ITransformerServices {

  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    root = "C:\\work\\org.hl7.fhir\\build"; // todo: where else to get this from?
    r2r3ini = new IniFile(Utilities.path(root, "implementations", "r2maps", "status.ini"));
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
//      if (n.contains("flag"))
      examples.put(n, output.toByteArray());
    }
    List<String> names = new ArrayList<String>(examples.size());
    names.addAll(examples.keySet());
    Collections.sort(names);
    
    List<Object[]> objects = new ArrayList<Object[]>(examples.size());

    for (String id : names) {
      objects.add(new Object[] { id, examples.get(id)});
    }

    return objects;
  }
  
  private static String root;
  private static SimpleWorkerContext contextR2;
  private static SimpleWorkerContext contextR3;
  private static Map<String, StructureMap> library;
  private static IniFile r2r3ini;
  
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
    
    StructureMapUtilities smu = new StructureMapUtilities(contextR3, library, this);
    String tn = null;
    try {
      // load the example (r2)
      org.hl7.fhir.dstu3.elementmodel.Element r2 = new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).parse(new ByteArrayInputStream(content));
      tn = r2.fhirType();
      
      // load the r2 to R3 map
      String mapFile = Utilities.path(root, "implementations", "r2maps", "R2toR3", r2.fhirType()+".map");
      if (new File(mapFile).exists()) {
        StructureMap sm = smu.parse(TextFile.fileToString(mapFile));
        tn = smu.getTargetType(sm).getType();

        // convert from r2 to r3
        Resource r3 = ResourceFactory.createResource(tn);
        smu.transform(null, r2, sm, r3);

        // validate against R3
        IResourceValidator validator = contextR3.newValidator();
        List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
        validator.validate(null, errors, r3);
        check(errors);


        // load the R3 to R2 map
        //      mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", r2.fhirType()+".map");
        //      s = sm.parse(TextFile.fileToString(mapFile));

        // convert to R2

        // compare the XML
      }
      r2r3ini.setStringProperty("outcomes-"+tn, name, "success", null);
      r2r3ini.save();
    } catch (Exception e) {
      r2r3ini.setStringProperty("outcomes-"+tn, name, "error: " +e.getMessage().replace("\r\n", "; "), null);
      r2r3ini.save();
      throw e;
    }
  }

  private void check(List<ValidationMessage> errors) throws FHIRException {
    StringBuilder b = new StringBuilder();
    for (ValidationMessage vm : errors) {
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL) {
        b.append(vm.getLocation()+": "+vm.getMessage()+"\r\n");
      }
    }
    if (b.length() > 0)
      throw new FHIRException(b.toString());
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
    contextR3.setValidatorFactory(new InstanceValidatorFactory());
    
    
    System.out.println("loading Maps");
    library = new HashMap<String, StructureMap>();
    loadLib("C:\\work\\org.hl7.fhir\\build\\implementations\\r2maps\\R2toR3");
    loadLib("C:\\work\\org.hl7.fhir\\build\\implementations\\r2maps\\R3toR2");
    System.out.println("loaded");
  }

  private void loadLib(String dir) throws FileNotFoundException, IOException {
    StructureMapUtilities smu = new StructureMapUtilities(contextR3);
    for (String s : new File(dir).list()) {
      String map = TextFile.fileToString(Utilities.path(dir, s));
      try {
        StructureMap sm = smu.parse(map);
        library.put(sm.getUrl(), sm);
      } catch (FHIRException e) {
        System.out.println("Unable to load "+s+": "+e.getMessage());
//        e.printStackTrace();
      }
    }
  }

  @Override
  public void log(String message) {
    System.out.println(message);
  }

  @Override
  public Base createResource(TransformContext context, Base res) {
    throw new Error("createResource not done yet");
  }

  @Override
  public Coding translate(Object appInfo, Coding source, String conceptMapUrl) throws FHIRException {
    throw new Error("translate not done yet");
  }

}
