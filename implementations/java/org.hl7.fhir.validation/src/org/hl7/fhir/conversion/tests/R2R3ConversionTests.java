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
import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.Base;
import org.hl7.fhir.dstu3.model.Coding;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.test.support.TestingUtilities;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.MetadataResource;
import org.hl7.fhir.dstu3.model.PractitionerRole;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.utils.IResourceValidator;
import org.hl7.fhir.dstu3.utils.IResourceValidator.IValidatorResourceFetcher;
import org.hl7.fhir.dstu3.utils.IResourceValidator.ReferenceValidationPolicy;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.ITransformerServices;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.TransformContext;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.InstanceValidatorFactory;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
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
public class R2R3ConversionTests implements ITransformerServices, IValidatorResourceFetcher {

  private static final boolean SAVING = true;

  @Parameters(name = "{index}: id {0}")
  public static Iterable<Object[]> data() throws ParserConfigurationException, SAXException, IOException {
    root = Paths.get(".").toAbsolutePath().normalize().toString();
    if (!(new File(Utilities.path(root, "implementations", "r2maps", "outcomes.json")).exists()))
      throw new Error("You must set the default directory to the build directroy when you execute these tests");
    r2r3Outcomes = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(root, "implementations", "r2maps", "outcomes.json")));
    rules = new IniFile(Utilities.path(root, "implementations", "r2maps", "test-rules.ini"));
          
    String srcFile = Utilities.path(root, "source", "release2", "examples.zip");
    ZipInputStream stream = new ZipInputStream(new FileInputStream(srcFile));

    String filter = System.getProperty("resource");
    if (filter != null)
      filter = filter.toLowerCase();
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
      if (Utilities.noString(filter) || n.contains(filter))
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
  private static IniFile rules;
  private List<Resource> extras;
  private final byte[] content;
  private final String name;
  private String workingid;

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
    workingid = null;

    Exception executionError = null;
    List<ValidationMessage> r3validationErrors = new ArrayList<ValidationMessage>();
    String roundTripError = null;
    try {
      extras = new ArrayList<Resource>();
      
      // load the example (r2)
      org.hl7.fhir.dstu3.elementmodel.Element r2 = new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).parse(new ByteArrayInputStream(content));
      tn = r2.fhirType();
      workingid = r2.getChildValue("id");
      if (SAVING)
        TextFile.bytesToFile(content, Utilities.path(root, "implementations", "r2maps", "test-output", tn+"-"+workingid+".input.xml"));
            
      // load the r2 to R3 map
      String mapFile = Utilities.path(root, "implementations", "r2maps", "R2toR3", r2.fhirType()+".map");
      if (new File(mapFile).exists()) {
        StructureMap sm = smu3.parse(TextFile.fileToString(mapFile));
        tn = smu3.getTargetType(sm).getType();

        // convert from r2 to r3
        Resource r3 = ResourceFactory.createResource(tn);
        smu3.transform(contextR3, r2, sm, r3);

        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        new org.hl7.fhir.dstu3.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(bs, r3);
        if (SAVING) {
          TextFile.bytesToFile(bs.toByteArray(), Utilities.path(root, "implementations", "r2maps", "test-output", tn+"-"+workingid+".r3.xml"));
        for (Resource r : extras) {
          bs = new ByteArrayOutputStream();
          new org.hl7.fhir.dstu3.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(bs, r);
          TextFile.bytesToFile(bs.toByteArray(), Utilities.path(root, "implementations", "r2maps", "test-output", r.fhirType()+"-"+r.getId()+".r3.xml"));
        }
        }

        // validate against R3
        IResourceValidator validator = contextR3.newValidator();
        validator.setNoTerminologyChecks(true);
        validator.setFetcher(this);
        validator.validate(null, r3validationErrors, r3);

        // load the R3 to R2 map
        //      mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", r2.fhirType()+".map");
        //      s = sm.parse(TextFile.fileToString(mapFile));
        mapFile = Utilities.path(root, "implementations", "r2maps", "R3toR2", getMapFor(r3.fhirType(), r2.fhirType())+".map");
        sm = smu2.parse(TextFile.fileToString(mapFile));

        // convert to R2
        StructureDefinition sd = smu2.getTargetType(sm);
        org.hl7.fhir.dstu3.elementmodel.Element ro2 = Manager.build(contextR2, sd);
        smu2.transform(contextR2, r3, sm, ro2);

        // compare the XML
        bs = new ByteArrayOutputStream();
        new org.hl7.fhir.dstu3.elementmodel.XmlParser(contextR2).compose(ro2, bs, OutputStyle.PRETTY, null);
        if (SAVING)
          TextFile.bytesToFile(bs.toByteArray(), Utilities.path(root, "implementations", "r2maps", "test-output", tn+"-"+workingid+".output.xml"));
               
//        check(errors, tn, workingid);
        roundTripError = TestingUtilities.checkXMLIsSame(new ByteArrayInputStream(content), new ByteArrayInputStream(bs.toByteArray()));
        if (roundTripError != null && roundTripError.equals(rules.getStringProperty(tn+"/"+workingid, "roundtrip")))
          roundTripError = null;
      }
    } catch (Exception e) {
      executionError = e;
    }
    if (tn != null && workingid != null)
      updateOutcomes(tn, workingid, executionError, r3validationErrors, roundTripError);
    if (executionError != null)
      throw executionError;
  }

  private String getMapFor(String r3, String r2) {
    if (r2.equals("DiagnosticOrder"))
      return "ProcedureRequestDO";
    else
      return r3;
  }

  private void updateOutcomes(String tn, String id, Exception executionError, List<ValidationMessage> r3validationErrors, String roundTripError) throws IOException {
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
    // now, update with outcomes
    // execution
    if (i.has("execution"))
      i.remove("execution");
    if (executionError == null)
      i.addProperty("execution", true);
    else
      i.addProperty("execution", executionError.getMessage());
    // round-trip check  
    if (i.has("round-trip"))
      i.remove("round-trip");
    if (roundTripError != null)
      i.addProperty("round-trip", roundTripError);
    // r3.validation errors
    if (i.has("r3.errors")) {
      i.remove("r3.errors");
    }
    if (r3validationErrors.size() > 0) {
      JsonArray arr = new JsonArray();
      i.add("r3.errors", arr);
      for (ValidationMessage e : r3validationErrors)
        arr.add(new JsonPrimitive(e.summary()));      
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(r2r3Outcomes);
    TextFile.stringToFile(json, (Utilities.path(root, "implementations", "r2maps", "outcomes.json")));
    
  }

  private void check(List<ValidationMessage> errors, String tn, String id) throws FHIRException {
    StringBuilder b = new StringBuilder();
    for (ValidationMessage vm : errors) {
      if (vm.getMessage() == null)
        break;
      if (vm.getMessage().contains("Error null validating Coding"))
        break;
      String s = rules.getStringProperty(tn+"/"+id, "validation");
      if (!Utilities.noString(s)) {
        boolean ok = false;
        for (String m : s.split("\\;"))
          if (vm.getMessage().contains(m.trim()))
            ok = true;
       if (ok)
          break;
      }
      s = rules.getStringProperty(tn, "validation");
      if (!Utilities.noString(s)) {
        boolean ok = false;
        for (String m : s.split("\\;"))
          if (vm.getMessage().contains(m.trim()))
            ok = true;
       if (ok)
          break;
      }
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL) {
        b.append("[R3 validation error] "+vm.getLocation()+": "+vm.getMessage()+"\r\n");
      }
    }
    if (b.length() > 0)
      throw new FHIRException(b.toString());
  }

  /*
   * Supporting multiple versions at once is a little tricky. We're going to have 2 contexts:
   * - an R2 context which is used to read/write R2 instances 
   * - an R3 context which is used to perform the transforms
   * 
   * R2 structure definitions are cloned into R3 context with a modified URL (as DSTU2/)  
   *  
   */
  private void checkLoad() throws IOException, FHIRException {
    if (contextR2 != null)
      return;
    R2ToR3Loader ldr = new R2ToR3Loader().setPatchUrls(true).setKillPrimitives(true);
    System.out.println("loading R2");
    contextR2 = new SimpleWorkerContext();
    contextR2.setAllowLoadingDuplicates(true);
    contextR2.loadFromFile(Utilities.path(root,"source","release2","profiles-types.xml"), ldr);
    contextR2.loadFromFile(Utilities.path(root,"source","release2","profiles-resources.xml"), ldr);
    contextR2.loadFromFile(Utilities.path(root,"source","release2","expansions.xml"), ldr);
    
    System.out.println("loading R3");
    contextR3 = new SimpleWorkerContext();
    contextR2.setAllowLoadingDuplicates(true);
    contextR3.loadFromFile(Utilities.path(root,"publish","profiles-types.xml"), null);
    contextR3.loadFromFile(Utilities.path(root,"publish","profiles-resources.xml"), null);
    contextR3.loadFromFile(Utilities.path(root,"publish","extension-definitions.xml"), null);
    contextR3.loadFromFile(Utilities.path(root,"publish","valuesets.xml"), null);
    contextR3.setCanRunWithoutTerminology(true);

    for (StructureDefinition sd : contextR2.allStructures()) {
      StructureDefinition sdn = sd.copy();
      sdn.getExtension().clear();
      contextR3.seeResource(sdn.getUrl(), sdn);
    }
    
    for (StructureDefinition sd : contextR3.allStructures()) {
      if (sd.getKind() == StructureDefinitionKind.PRIMITIVETYPE) {
        contextR2.seeResource(sd.getUrl(), sd);
        StructureDefinition sdn = sd.copy();
        sdn.setUrl(sdn.getUrl().replace("http://hl7.org/fhir/", "http://hl7.org/fhir/DSTU2/"));
        sdn.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/elementdefinition-namespace").setValue(new UriType("http://hl7.org/fhir"));
        contextR2.seeResource(sdn.getUrl(), sdn);
        contextR3.seeResource(sdn.getUrl(), sdn);
      }
    }
        
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
        System.out.println("Unable to load "+Utilities.path(dir, s)+": "+e.getMessage());
//        e.printStackTrace();
      }
    }
  }

  @Override
  public void log(String message) {
    System.out.println(message);
  }

  @Override
  public Base createResource(Object appInfo, Base res) {
    if (res instanceof Resource && (res.fhirType().equals("CodeSystem") || res.fhirType().equals("CareTeam")) || res.fhirType().equals("PractitionerRole")) {
      Resource r = (Resource) res;
      extras.add(r);
      r.setId(workingid+"-"+extras.size());
    }
    return res;
  }

  @Override
  public Coding translate(Object appInfo, Coding source, String conceptMapUrl) throws FHIRException {
    throw new Error("translate not done yet");
  }

  @Override
  public Base createType(Object appInfo, String name) throws FHIRException {
    SimpleWorkerContext context = (SimpleWorkerContext) appInfo;
     if (context == contextR2) {
       StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/DSTU2/StructureDefinition/"+name);
       if (sd == null)
         throw new FHIRException("Type not found: '"+name+"'");
       return Manager.build(context, sd);
     } else
       return ResourceFactory.createResourceOrType(name);
  }

  @Override
  public Base resolveReference(Object appContext, String url) {
    for (Resource r : extras) {
      if (r instanceof MetadataResource) {
        MetadataResource mr = (MetadataResource) r;
        if (url.equals(mr.getUrl()))
          return mr;
      }
      if (url.equals(r.fhirType()+"/"+r.getId()))
        return r;
    }
    
    return null;
  }

  @Override
  public List<Base> performSearch(Object appContext, String url) {
    List<Base> results = new ArrayList<Base>();
    String[] parts = url.split("\\?");
    if (parts.length == 2 && parts[0].substring(1).equals("PractitionerRole")) {
      String[] vals = parts[1].split("\\=");
      if (vals.length == 2 && vals[0].equals("practitioner"))
      for (Resource r : extras) {
        if (r instanceof PractitionerRole && ((PractitionerRole) r).getPractitioner().getReference().equals("Practitioner/"+vals[1])) {
          results.add(r);
        }
      }
    }
    return results;
  }

  @Override
  public Element fetch(Object appContext, String url) throws FHIRFormatError, DefinitionException, IOException, FHIRException {
    return null;
  }

  @Override
  public ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url) {
    return ReferenceValidationPolicy.IGNORE;
  }

  @Override
  public boolean resolveURL(Object appContext, String path, String url) throws IOException, FHIRException {
    return true;
  }

}
