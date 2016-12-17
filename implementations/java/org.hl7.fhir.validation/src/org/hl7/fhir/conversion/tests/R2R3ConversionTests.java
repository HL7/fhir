package org.hl7.fhir.conversion.tests;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
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
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.test.TestingUtilities;
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

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@RunWith(Parameterized.class)
public class R2R3ConversionTests implements ITransformerServices {

  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    root = Paths.get(".").toAbsolutePath().normalize().toString();
    if (!(new File(Utilities.path(root, "implementations", "r2maps", "outcomes.json")).exists()))
      throw new Error("You must set the default directory to the build directroy when you execute these tests");
    r2r3Outcomes = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(root, "implementations", "r2maps", "outcomes.json")));
        
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
  private static JsonObject r2r3Outcomes;
  
  private final byte[] content;
  private final String name;

  public R2R3ConversionTests(String name, byte[] content) {
    this.name = name;
    this.content = content;
  }

  @SuppressWarnings("deprecation")
  @Test
  public void test() throws Exception {
    checkLoad();
    
    StructureMapUtilities smu3 = new StructureMapUtilities(contextR3, library, this);
    StructureMapUtilities smu2 = new StructureMapUtilities(contextR2, library, this);
    String tn = null;
    String id = null;
    try {
      // load the example (r2)
      org.hl7.fhir.dstu3.elementmodel.Element r2 = new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).parse(new ByteArrayInputStream(content));
      tn = r2.fhirType();
      id = r2.getChildValue("id");
      
      // load the r2 to R3 map
      String mapFile = Utilities.path(root, "implementations", "r2maps", "R2toR3", r2.fhirType()+".map");
      if (new File(mapFile).exists()) {
        StructureMap sm = smu3.parse(TextFile.fileToString(mapFile));
        tn = smu3.getTargetType(sm).getType();

        // convert from r2 to r3
        Resource r3 = ResourceFactory.createResource(tn);
        smu3.transform(null, r2, sm, r3);

        // validate against R3
        IResourceValidator validator = contextR3.newValidator();
        List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
        validator.validate(null, errors, r3);
        check(errors);

        // load the R3 to R2 map
        //      mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", r2.fhirType()+".map");
        //      s = sm.parse(TextFile.fileToString(mapFile));
        mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", r3.fhirType()+".map");
        sm = smu2.parse(TextFile.fileToString(mapFile));

        // convert to R2
        StructureDefinition sd = smu2.getTargetType(sm);
        org.hl7.fhir.dstu3.elementmodel.Element ro2 = Manager.build(contextR2, sd);
        smu2.transform(null, r3, sm, ro2);

        // compare the XML
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).compose(ro2, bs, OutputStyle.PRETTY, null);
        
        TextFile.bytesToFile(content, Utilities.path(root, "implementations", "r2maps", "test-output", tn+"-"+id+".input.xml"));
        TextFile.bytesToFile(bs.toByteArray(), Utilities.path(root, "implementations", "r2maps", "test-output", tn+"-"+id+".output.xml"));
        String s = TestingUtilities.checkXMLIsSame(new ByteArrayInputStream(content), new ByteArrayInputStream(bs.toByteArray()));
        if (s != null)
          throw new Exception("Round trip failed: "+s);
      }
      if (tn != null && id != null)
        updateOutcomes(tn, id, null);
    } catch (Exception e) {
      if (tn != null && id != null)
        updateOutcomes(tn, id, (e.getMessage() == null ? "NullPointerException" : e.getMessage()).split("\\r?\\n"));
      throw e;
    }
  }

  private void updateOutcomes(String tn, String id, String[] errs) throws IOException {
    JsonObject r = r2r3Outcomes.getAsJsonObject(tn);
    if (r == null) {
      r = new JsonObject();
      r2r3Outcomes.add(tn, r);
    }
    JsonObject i = r.getAsJsonObject(id);
    if (i == null) {
      i = new JsonObject();
      r.add(id, i);
    }
    if (errs == null || errs.length == 0) {
      if (i.has("errors"))
        i.remove("errors");
    } else {
      JsonArray arr = i.getAsJsonArray("errors");
      if (arr == null) {
        arr = new JsonArray();
        i.add("errors", arr);
      }
      while (arr.size() > 0)
        arr.remove(0);
      for (String e : errs)
        arr.add(new JsonPrimitive(e));
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(r2r3Outcomes);
    TextFile.stringToFile(json, (Utilities.path(root, "implementations", "r2maps", "outcomes.json")));
    
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
    R2ToR3Loader ldr = new R2ToR3Loader().setPatchUrls(true);
    System.out.println("loading R2");
    contextR2 = new SimpleWorkerContext();
    contextR2.loadFromFile(Utilities.path(root,"source","release2","profiles-types.xml"), ldr);
    contextR2.loadFromFile(Utilities.path(root,"source","release2","profiles-resources.xml"), ldr);
    contextR2.loadFromFile(Utilities.path(root,"source","release2","expansions.xml"), ldr);
    
    System.out.println("loading R3");
    contextR3 = new SimpleWorkerContext();
    contextR3.loadFromFile(Utilities.path(root,"publish","profiles-types.xml"), null);
    contextR3.loadFromFile(Utilities.path(root,"publish","profiles-resources.xml"), null);
    contextR3.loadFromFile(Utilities.path(root,"publish","expansions.xml"), null);

    contextR2.setExpansionProfile(new ExpansionProfile().setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()));
    contextR3.setExpansionProfile(new ExpansionProfile().setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()));
    contextR2.setName("R2");
    contextR3.setName("R3");
    contextR3.setValidatorFactory(new InstanceValidatorFactory());
    
    
    System.out.println("loading Maps");
    library = new HashMap<String, StructureMap>();
    loadLib(Utilities.path(root,"implementations","r2maps", "R2toR3"));
    loadLib(Utilities.path(root,"implementations","r2maps", "R3toR2"));
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
