package org.hl7.fhir.igtools.publisher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.ObjectConverter;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.elementmodel.ParserBase.ValidationPolicy;
import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.Constants;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.dstu3.utils.EOperationOutcome;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.Turtle;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.igtools.publisher.Publisher.GenerationTool;
import org.hl7.fhir.igtools.renderers.CodeSystemRenderer;
import org.hl7.fhir.igtools.renderers.JsonXhtmlRenderer;
import org.hl7.fhir.igtools.renderers.StructureDefinitionRenderer;
import org.hl7.fhir.igtools.renderers.ValidationPresenter;
import org.hl7.fhir.igtools.renderers.XmlXHtmlRenderer;
import org.hl7.fhir.igtools.spreadsheets.IgSpreadsheetParser;
import org.hl7.fhir.igtools.renderers.ValueSetRenderer;
import org.hl7.fhir.rdf.RdfGenerator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import java.util.concurrent.TimeUnit;

/**
 * Implementation Guide Publisher
 * 
 * If you want to use this inside a FHIR server, and not to access content 
 * on a local folder, provide your own implementation of the file fetcher
 * 
 * rough sequence of activities:
 * 
 *   load the context using the internal validation pack
 *   connect to the terminology service
 *   
 *   parse the implementation guide
 *   find all the source files and determine the resource type
 *   load resources in this order:
 *     naming system
 *     code system
 *     value set 
 *     data element?
 *     structure definition
 *     concept map
 *     structure map
 *      
 *   validate all source files (including the IG itself)
 *   
 *   for each source file:
 *     generate all outputs
 *     
 *   generate summary file
 *   
 *   
 * @author Grahame Grieve
 *
 */

public class Publisher {
  public enum GenerationTool {
    Jekyll
  }

  private static final String IG_NAME = "!ig!";

  private String pathToSpec;
  private String configFile;
  private String output;
  private GenerationTool tool;
  private String txServer = "http://fhir3.healthintersections.com.au/open";
  private boolean watch;

  private String igName;

  private String contentDir;
  private String includesDir;
  private String dataDir;
  private String validationDir;

  private IFetchFile fetcher = new SimpleFetcher();
  private SimpleWorkerContext context;
  private InstanceValidator validator;
  private IGKnowledgeProvider igpkp;
  private JsonObject specDetails;
  private boolean first;

  private Map<ImplementationGuidePackageResourceComponent, FetchedFile> fileMap = new HashMap<ImplementationGuidePackageResourceComponent, FetchedFile>();
  private Map<String, FetchedFile> altMap = new HashMap<String, FetchedFile>();
  private List<FetchedFile> fileList = new ArrayList<FetchedFile>();
  private List<FetchedFile> changeList = new ArrayList<FetchedFile>();
  private Set<String> bndIds = new HashSet<String>();
  private List<Resource> loaded = new ArrayList<Resource>();
  private ImplementationGuide ig;
  private List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
  private JsonObject configuration;
  private Calendar execTime = Calendar.getInstance();
  private JsonObject toolConfig;

  private void execute() throws Exception {
    initialize();
    log("Load Implementation Guide");
    load();

    long startTime = System.nanoTime();
    log("Processing Conformance Resources");
    loadConformance();
    log("Validating Resources");
    validate();
    log("Generating Outputs in "+output);
    generate();
    long endTime = System.nanoTime();
    clean();
    log(" ... finished. "+presentDuration(endTime - startTime)+". Validation output in "+new ValidationPresenter(context).generate(ig.getName(), errors, fileList, Utilities.path(validationDir, "validation.html")));

    if (watch) {
      first = false;
      System.out.println("Watching for changes on a 5sec cycle");
      while (watch) { // terminated externally
        Thread.sleep(5000);
        if (load()) {
          log("Processing changes to "+Integer.toString(changeList.size())+(changeList.size() == 1 ? " file" : " files")+" @ "+genTime());
          startTime = System.nanoTime();
          loadConformance();
          checkDependencies();
          validate();
          generate();
          clean();
          endTime = System.nanoTime();
          log(" ... finished. "+presentDuration(endTime - startTime)+". Validation output in "+new ValidationPresenter(context).generate(ig.getName(), errors, fileList, Utilities.path(validationDir, "validation.html")));
        }
      }
    } else
      log("Done");
  }

  private void clean() throws Exception {
    for (Resource r : loaded)
      context.dropResource(r);
    for (FetchedFile f : fileList)
      f.dropSource();
  }

  private String genTime() {
    return new SimpleDateFormat("EEE, MMM d, yyyy HH:mmZ", new Locale("en", "US")).format(execTime.getTime());
  }

  private void checkDependencies() {
    // first, we load all the direct dependency lists
    for (FetchedFile f : fileList) 
      if (f.getDependencies() == null)
        loadDependencyList(f);

    // now, we keep adding to the change list till there's no change
    boolean changed;
    do {
      changed = false;
      for (FetchedFile f : fileList) {
        if (!changeList.contains(f)) {
          boolean dep = false;
          for (FetchedFile d : f.getDependencies()) 
            if (changeList.contains(d)) 
              dep = true;
          if (dep) {
            changeList.add(f);
            changed = true;
          }
        }
      }
    } while (changed);    
  }

  private void loadDependencyList(FetchedFile f) {
    f.setDependencies(new ArrayList<FetchedFile>());
    for (FetchedResource r : f.getResources()) {
      if (r.getElement().fhirType().equals("ValueSet"))
        loadValueSetDependencies(f, r);
      else if (r.getElement().fhirType().equals("StructureDefinition"))
        loadProfileDependencies(f, r);
      else
        ; // all other resource types don't have dependencies that we care about for rendering purposes
    }
  }

  private void loadValueSetDependencies(FetchedFile f, FetchedResource r) {
    ValueSet vs = (ValueSet) r.getResource();
    for (UriType vsi : vs.getCompose().getImport()) {
      FetchedFile fi = getFileForUri(vsi.getValue());
      if (fi != null)
        f.getDependencies().add(fi);
    }
    for (ConceptSetComponent vsc : vs.getCompose().getInclude()) {
      FetchedFile fi = getFileForUri(vsc.getSystem());
      if (fi != null)
        f.getDependencies().add(fi);
    }
    for (ConceptSetComponent vsc : vs.getCompose().getExclude()) {
      FetchedFile fi = getFileForUri(vsc.getSystem());
      if (fi != null)
        f.getDependencies().add(fi);
    }
  }

  private FetchedFile getFileForUri(String uri) {
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof BaseConformance) {
          BaseConformance bc = (BaseConformance) r.getResource();
          if (bc.getUrl().equals(uri)) 
            return f;
        }
      }
    }
    return null;
  }

  private void loadProfileDependencies(FetchedFile f, FetchedResource r) {
    StructureDefinition sd = (StructureDefinition) r.getResource();
    FetchedFile fi = getFileForUri(sd.getBaseDefinition());
    if (fi != null)
      f.getDependencies().add(fi);
  }

  private String presentDuration(long duration) {
    duration = duration / 1000000;
    String res = "";    // ;
    long days       = TimeUnit.MILLISECONDS.toDays(duration);
    long hours      = TimeUnit.MILLISECONDS.toHours(duration) -
        TimeUnit.DAYS.toHours(TimeUnit.MILLISECONDS.toDays(duration));
    long minutes    = TimeUnit.MILLISECONDS.toMinutes(duration) -
        TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(duration));
    long seconds    = TimeUnit.MILLISECONDS.toSeconds(duration) -
        TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(duration));
    long millis     = TimeUnit.MILLISECONDS.toMillis(duration) - 
        TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(duration));

    if (days > 0)      
      res = String.format("%dd %02d:%02d:%02d.%04d", days, hours, minutes, seconds, millis);
    else if (hours > 0 || minutes > 0)                
      res = String.format("%02d:%02d:%02d.%04d", hours, minutes, seconds, millis);
    else
      res = String.format("%02d.%04d", seconds, millis);
    return res;  
  }

  private void initialize() throws Exception {
    first = true;
    log("Load Configuration");

    configuration = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(configFile));
    if (!configuration.has("tool") || !(configuration.get("tool") instanceof JsonObject))
      throw new Exception("Error: configuration file must include a \"tool\" object");
    toolConfig = configuration.getAsJsonObject("tool");
    tool = readType();

    igName = Utilities.path(Utilities.getDirectoryForFile(configFile), configuration.get("source").getAsString());

    log("Publish "+igName);

    log("Check destination");
    File f = new File(output);
    if (!f.exists())
      Utilities.createDirectory(output);
    else if (!f.isDirectory())
      throw new Exception(String.format("Error: Output must be a folder (%s)", output));

    contentDir = Utilities.path(output, "html");
    includesDir = Utilities.path(contentDir, "_includes");
    dataDir = Utilities.path(contentDir, "_data"); 
    validationDir = Utilities.path(output, "generation");

    Utilities.createDirectory(contentDir);
    Utilities.createDirectory(includesDir);
    Utilities.createDirectory(dataDir);
    Utilities.createDirectory(validationDir);

    log("Load Validation Pack");
    try {
      context = SimpleWorkerContext.fromClassPath("igpack.zip");
    } catch (NullPointerException npe) {
      System.out.println("Unable to find igpack.zip in the jar");
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\temp\\igpack.zip");
    }
    context.setAllowLoadingDuplicates(true);
    context.setExpandCodesLimit(1000);
    log("Connect to Terminology Server at "+txServer);
    String home = Utilities.path(System.getProperty("user.home"), "fhircache");
    Utilities.createDirectory(home);
    context.initTS(home, txServer);
    context.connectToTSServer(txServer);
    // ;
    validator = new InstanceValidator(context);
    validator.setAllowXsiLocation(true);

    loadSpecDetails(context.getBinaries().get("spec.internals"));
    igpkp = new IGKnowledgeProvider(context, pathToSpec, configuration, errors);
    igpkp.loadSpecPaths(specDetails.get("paths").getAsJsonObject());
    fetcher.setPkp(igpkp);
    for (String s : context.getBinaries().keySet())
      if (needFile(s)) {
        checkMakeFile(context.getBinaries().get(s), Utilities.path(contentDir, s));    
        checkMakeFile(context.getBinaries().get(s), Utilities.path(validationDir, s));
      }
  }


  private GenerationTool readType() throws Exception {
    // TODO Auto-generated method stub
    if (!toolConfig.has("type"))
      throw new Exception("Error: configuration tool object must include a \"type\" property");
    String t = toolConfig.get("type").getAsString();
    if ("jekyll".equals(t))
      return GenerationTool.Jekyll;
    else
      throw new Exception("Error: -tool parameter '"+tool+"' not recognised - must be \"jekyll\"");

  }

  private void checkMakeFile(byte[] bs, String path) throws IOException {
    File f = new CSFile(path);
    byte[] existing = null;
    if (f.exists())
      existing = TextFile.fileToBytes(path);
    if (!Arrays.equals(bs, existing))
      TextFile.bytesToFile(bs, path);
  }

  private boolean needFile(String s) {
    if (s.endsWith(".css"))
      return true;
    if (s.startsWith("tbl"))
      return true;
    if (s.startsWith("icon"))
      return true;
    if (Utilities.existsInList(s, "modifier.png", "mustsupport.png", "summary.png", "lock.png", "external.png", "cc0.png", "target.png"))
      return true;
    return false;
  }

  public void loadSpecDetails(byte[] bs) throws IOException {
    String s = TextFile.bytesToString(bs);
    Gson g = new Gson();
    specDetails = g.fromJson(s, JsonObject.class);
  }



  private boolean load() throws Exception {

    fileList.clear();
    changeList.clear();
    bndIds.clear();
    boolean needToBuild = false;
    FetchedFile igf = fetcher.fetch(igName);
    needToBuild = noteFile(IG_NAME, igf) || needToBuild;
    if (needToBuild) {
      ig = (ImplementationGuide) parse(igf);
      FetchedResource igr = igf.addResource(); 
      igr.setElement(new ObjectConverter(context).convert(ig));
      igr.setResource(ig);
      igr.setId(ig.getId()).setTitle(ig.getName());
    } else
      ig = (ImplementationGuide) altMap.get(IG_NAME).getResources().get(0).getResource();

    // load any bundles 
    needToBuild = loadSpreadsheets(needToBuild, igf);
    needToBuild = loadBundles(needToBuild, igf);
    for (ImplementationGuidePackageComponent pack : ig.getPackage()) {
      for (ImplementationGuidePackageResourceComponent res : pack.getResource()) {
        if (!bndIds.contains(res.getSourceReference().getReference())) {
          FetchedFile f = fetcher.fetch(res.getSource(), igf);
          needToBuild = noteFile(res, f) || needToBuild;
          determineType(f);
        }
      }     
    }
    execTime = Calendar.getInstance();
    return needToBuild;
  }

  private boolean loadBundles(boolean needToBuild, FetchedFile igf) throws Exception {
    JsonArray bundles = configuration.getAsJsonArray("bundles");
    if (bundles != null) {
      for (JsonElement be : bundles) {
        needToBuild = loadBundle((JsonPrimitive) be, needToBuild, igf);
      }
    }
    return needToBuild;
  }

  private boolean loadBundle(JsonPrimitive be, boolean needToBuild, FetchedFile igf) throws Exception {
    FetchedFile f = fetcher.fetch(new Reference().setReference("Bundle/"+be.getAsString()), igf);
    boolean changed = noteFile("Bundle/"+be.getAsString(), f);
    if (changed) {
      f.setBundle((Bundle) parse(f));
      for (BundleEntryComponent b : f.getBundle().getEntry()) {
        FetchedResource r = f.addResource();
        r.setResource(b.getResource());
        r.setId(b.getResource().getId());
        r.setElement(new ObjectConverter(context).convert(r.getResource()));          
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else 
      f = altMap.get("Bundle/"+be.getAsString());
    for (FetchedResource r : f.getResources()) 
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
    return changed || needToBuild;
  }

  private boolean loadSpreadsheets(boolean needToBuild, FetchedFile igf) throws Exception {
    JsonArray spreadsheets = configuration.getAsJsonArray("spreadsheets");
    if (spreadsheets != null) {
      for (JsonElement be : spreadsheets) {
        needToBuild = loadSpreadsheet((JsonPrimitive) be, needToBuild, igf);
      }
    }
    return needToBuild;
  }

  private boolean loadSpreadsheet(JsonPrimitive be, boolean needToBuild, FetchedFile igf) throws Exception {
    String path = Utilities.path(Utilities.getDirectoryForFile(igName), be.getAsString());
    FetchedFile f = fetcher.fetch(path);
    boolean changed = noteFile("Spreadsheet/"+be.getAsString(), f);
    if (changed) {
      f.getValuesetsToLoad().clear();
      f.setBundle(new IgSpreadsheetParser(context, execTime, igpkp.getCanonical(), f.getValuesetsToLoad(), first).parse(f));
      for (BundleEntryComponent b : f.getBundle().getEntry()) {
        FetchedResource r = f.addResource();
        r.setResource(b.getResource());
        r.setId(b.getResource().getId());
        r.setElement(new ObjectConverter(context).convert(r.getResource()));          
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else 
      f = altMap.get("Spreadsheet/"+be.getAsString());
    for (String vr : f.getValuesetsToLoad()) {
      path = Utilities.path(Utilities.getDirectoryForFile(igName), vr);
      FetchedFile fv = fetcher.fetchFlexible(path);
      boolean vrchanged = noteFile("sp-ValueSet/"+vr, fv);
      if (vrchanged) {
        determineType(fv);
        // check the canonical URL
        String url = fv.getResources().get(0).getElement().getChildValue("url");
        String id = fv.getResources().get(0).getId();
        if (!tail(url).equals(id)) 
          throw new Exception("resource id/url mismatch: "+id+" vs "+url);
//        if (!url.startsWith(igpkp.getCanonical())) 
//          throw new Exception("base/ resource url mismatch: "+igpkp.getCanonical()+" vs "+url);

      }
      changed = changed || vrchanged;
    }
    for (FetchedResource r : f.getResources()) 
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
    return changed || needToBuild;
  }


  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  private void loadConformance() throws Exception {
    load("NamingSystem");
    load("CodeSystem");
    load("ValueSet");
    load("DataElement");
    load("StructureDefinition");
    load("ConceptMap");
    load("StructureMap");
    generateSnapshots();
  }

  private boolean noteFile(ImplementationGuidePackageResourceComponent key, FetchedFile file) {
    FetchedFile existing = fileMap.get(key);
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      fileList.add(file);
      fileMap.put(key, file);
      changeList.add(file);
      return true;
    } else {
      fileList.add(existing); // this one is already parsed
      return false;
    }
  }

  private boolean noteFile(String key, FetchedFile file) {
    FetchedFile existing = altMap.get(key);
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      fileList.add(file);
      altMap.put(key, file);
      changeList.add(file);
      return true;
    } else {
      fileList.add(existing); // this one is already parsed
      return false;
    }
  }

  private void determineType(FetchedFile file) throws Exception {
    if (file.getResources().isEmpty()) {
      file.getErrors().clear();
      Element e = null;
      try {
        if (file.getContentType().contains("json"))
          e = loadFromJson(file);
        else if (file.getContentType().contains("xml"))
          e = loadFromXml(file);
        else 
          throw new Exception("Unable to determine file type for "+file.getName());

      } catch (Exception ex) {
        throw new Exception("Unable to parse "+file.getName()+": " +ex.getMessage(), ex);
      }
      FetchedResource r = file.addResource();
      r.setElement(e).setId(e.getChildValue("id")).setTitle(e.getChildValue("name"));
      igpkp.findConfiguration(file, r);
    }
  }

  private Element loadFromXml(FetchedFile file) throws Exception {
    org.hl7.fhir.dstu3.elementmodel.XmlParser xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(context);
    xp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors()); 
    return xp.parse(new ByteArrayInputStream(file.getSource()));
  }

  private Element loadFromJson(FetchedFile file) throws Exception {
    org.hl7.fhir.dstu3.elementmodel.JsonParser jp = new org.hl7.fhir.dstu3.elementmodel.JsonParser(context);
    jp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors()); 
    return jp.parse(new ByteArrayInputStream(file.getSource()));
  }

  private void load(String type) throws Exception {
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(type)) {
          if (!r.isValidated()) 
            validate(f, r);
          if (r.getResource() == null)
            r.setResource(parse(f)); // we won't get to here if we're a bundle 
          BaseConformance bc = (BaseConformance) r.getResource();
          igpkp.checkForPath(f, r, bc);
          context.seeResource(bc.getUrl(), bc);
        }
      }
    }
  }

  private void generateSnapshots() throws DefinitionException, FHIRException {
    ProfileUtilities utils = new ProfileUtilities(context, null, null);
    for (StructureDefinition derived : context.allStructures()) {
      if (!derived.hasSnapshot()) {
        StructureDefinition base = context.fetchResource(StructureDefinition.class, derived.getBaseDefinition());
        if (base != null)
          utils.generateSnapshot(base, derived, derived.getUrl(), derived.getName());
      }
    }
  }

  private Resource parse(FetchedFile file) throws Exception {

    if (file.getContentType().contains("json"))
      return new JsonParser().parse(file.getSource());
    else if (file.getContentType().contains("xml"))
      return new XmlParser().parse(file.getSource());
    else 
      throw new Exception("Unable to determine file type for "+file.getName());
  }

  private void validate() throws Exception {
    for (FetchedFile f : fileList) {
      if (first)
        log(" .. "+f.getName());
      for (FetchedResource r : f.getResources()) {
        if (!r.isValidated()) {
          validate(f, r);
        }
      }
    }
  }

  private void validate(FetchedFile file, FetchedResource r) throws Exception {
    validator.validate(file.getErrors(), r.getElement());
    r.setValidated(true);
    if (r.getConfig() == null)
      igpkp.findConfiguration(file, r);
  }

  private void generate() throws Exception {
    for (FetchedFile f : changeList) 
      generateOutputs(f);

    if (!changeList.isEmpty())
      generateSummaryOutputs();
  }

  private void generateSummaryOutputs() throws IOException {
    JsonObject data = new JsonObject();
    data.addProperty("path", pathToSpec);
    data.addProperty("canonical", igpkp.getCanonical());
    data.addProperty("errorCount", getErrorCount());
    data.addProperty("version", Constants.VERSION);
    data.addProperty("revision", Constants.REVISION);
    data.addProperty("versionFull", Constants.VERSION+"-"+Constants.REVISION);
    data.addProperty("totalFiles", fileList.size());
    data.addProperty("processedFiles", changeList.size());
    data.addProperty("genDate", genTime());

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(data);
    TextFile.stringToFile(json, Utilities.path(dataDir, "fhir.json"));    
  }

  private Number getErrorCount() {
    int result = countErrs(errors);
    for (FetchedFile f : fileList) {
      result = result + countErrs(f.getErrors());
    }
    return result;
  }

  private int countErrs(List<ValidationMessage> list) {
    int i = 0;
    for (ValidationMessage vm : list) {
      if (vm.getLevel() == IssueSeverity.ERROR || vm.getLevel() == IssueSeverity.FATAL)
        i++;
    }
    return i;
  }

  private void log(String s) {
    System.out.println(s);
  }

  private void generateOutputs(FetchedFile f) throws Exception {
    System.out.println(" * "+f.getName());

    for (FetchedResource r : f.getResources()) {
      saveDirectResourceOutputs(r);

      // now, start generating resource type specific stuff 
      if (r.getResource() != null) { // we only do this for conformance resources we've already loaded
        switch (r.getResource().getResourceType()) {
        case CodeSystem:
          generateOutputsCodeSystem(r, (CodeSystem) r.getResource());
          break;
        case ValueSet:
          generateOutputsValueSet(r, (ValueSet) r.getResource());
          break;
        case ConceptMap:
          generateOutputsConceptMap(r, (ConceptMap) r.getResource());
          break;

        case DataElement:
          break;
        case StructureDefinition:
          generateOutputsStructureDefinition(r, (StructureDefinition) r.getResource());
          break;
        case StructureMap:
          break;
        default:
          // nothing to do...    
        }      
      }
    }
  }

  private boolean wantGen(FetchedResource f, String code) {
    if (f.getConfig() != null && hasBoolean(f.getConfig(), code))
      return getBoolean(f.getConfig(), code);
    JsonObject cfg = configuration.getAsJsonObject("defaults");
    if (cfg != null)
      cfg = cfg.getAsJsonObject(f.getElement().fhirType());
    if (cfg != null && hasBoolean(cfg, code))
      return getBoolean(cfg, code);
    cfg = configuration.getAsJsonObject("defaults");
    if (cfg != null)
      cfg = cfg.getAsJsonObject("Any");
    if (cfg != null && hasBoolean(cfg, code))
      return getBoolean(cfg, code);
    return true;
  }


  private boolean hasBoolean(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    return e != null && e instanceof JsonPrimitive && ((JsonPrimitive) e).isBoolean();
  }

  private boolean getBoolean(JsonObject obj, String code) {
    JsonElement e = obj.get(code);
    return e != null && e instanceof JsonPrimitive && ((JsonPrimitive) e).getAsBoolean();
  }

  /**
   * saves the resource as XML, JSON, Turtle, 
   * then all 3 of those as html with embedded links to the definitions
   * then the narrative as html
   *  
   * @param f
   * @throws FileNotFoundException
   * @throws Exception
   */
  private void saveDirectResourceOutputs(FetchedResource f) throws FileNotFoundException, Exception {
    if (wantGen(f, "xml")) {
      new org.hl7.fhir.dstu3.elementmodel.XmlParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".xml")), OutputStyle.PRETTY, "??");
      if (tool == GenerationTool.Jekyll)
        genWrapper(f, "xml");  
    }
    if (wantGen(f, "json")) {
      new org.hl7.fhir.dstu3.elementmodel.JsonParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".json")), OutputStyle.PRETTY, "??");
      if (tool == GenerationTool.Jekyll)
        genWrapper(f, "json");  
    }
    if (wantGen(f, "ttl")) {
      new org.hl7.fhir.dstu3.elementmodel.TurtleParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".ttl")), OutputStyle.PRETTY, "??");
      if (tool == GenerationTool.Jekyll)
        genWrapper(f, "ttl");  
    }

    if (wantGen(f, "xml-html")) {
      XmlXHtmlRenderer x = new XmlXHtmlRenderer();
      org.hl7.fhir.dstu3.elementmodel.XmlParser xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(context);
      xp.setLinkResolver(igpkp);
      xp.compose(f.getElement(), x);
      fragment(f.getId()+"-xml-html", x.toString());
    }
    if (wantGen(f, "json-html")) {
      JsonXhtmlRenderer j = new JsonXhtmlRenderer();
      org.hl7.fhir.dstu3.elementmodel.JsonParser jp = new org.hl7.fhir.dstu3.elementmodel.JsonParser(context);
      jp.setLinkResolver(igpkp);
      jp.compose(f.getElement(), j);
      fragment(f.getId()+"-json-html", j.toString());
    }

    if (wantGen(f, "ttl-html")) {
      org.hl7.fhir.dstu3.elementmodel.TurtleParser ttl = new org.hl7.fhir.dstu3.elementmodel.TurtleParser(context);
      ttl.setLinkResolver(igpkp);
      Turtle rdf = new Turtle();
      ttl.compose(f.getElement(), rdf, "??");
      fragment(f.getId()+"-ttl-html", rdf.asHtml());
    }

    if (wantGen(f, "html")) {
      XhtmlNode xhtml = getXhtml(f);
      String html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
      fragment(f.getId()+"-html", html);  
    }
    //  NarrativeGenerator gen = new NarrativeGenerator(null, null, context);
    //  gen.generate(f.getElement(), false);
    //  xhtml = getXhtml(f);
    //  html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
    //  fragment(f.getId()+"-gen-html", html);
  }

  private void genWrapper(FetchedResource f, String format) throws FileNotFoundException, IOException {
    if (toolConfig.has("source-wrapper-template")) {
      String template = TextFile.fileToString(Utilities.path(Utilities.getDirectoryForFile(configFile), toolConfig.get("source-wrapper-template").getAsString()));
      template = template.replace("{{[title]}}", f.getTitle());
      template = template.replace("{{[name]}}", f.getId()+"-"+format+"-html");
      TextFile.stringToFile(template, Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+"."+format+".html"), false);
    }    
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws IOException 
   * @throws FHIRException 
   * @throws EOperationOutcome 
   */
  private void generateOutputsCodeSystem(FetchedResource f, CodeSystem cs) throws IOException, EOperationOutcome, FHIRException {
    CodeSystemRenderer csr = new CodeSystemRenderer(context, pathToSpec, cs, igpkp);
    if (wantGen(f, "summary")) 
      fragment(cs.getId()+"-cs-summary", csr.summary(wantGen(f, "xml"), wantGen(f, "json"), wantGen(f, "ttl")));
    if (wantGen(f, "content")) 
      fragment(cs.getId()+"-cs-content", csr.content());
    if (wantGen(f, "xref")) 
      fragment(cs.getId()+"-cs-xref", csr.xref());
  }

  /**
   * Genrate: 
   *   summary
   *   Content logical definition
   *   cross-reference
   *   
   * and save the expansion as html. todo: should we save it as a resource too? at this time, no: it's not safe to do that; encourages abuse
   * @param vs
   * @throws IOException
   * @throws FHIRException 
   */
  private void generateOutputsValueSet(FetchedResource f, ValueSet vs) throws IOException, FHIRException {
    ValueSetRenderer vsr = new ValueSetRenderer(context, pathToSpec, vs, igpkp);
    if (wantGen(f, "summary")) 
      fragment(vs.getId()+"-vs-summary", vsr.summary(wantGen(f, "xml"), wantGen(f, "json"), wantGen(f, "ttl")));
    if (wantGen(f, "cld")) 
      try {
        fragment(vs.getId()+"-vs-cld", vsr.cld());
      } catch (Exception e) {
        fragmentError(vs.getId()+"-vs-cld", e.getMessage());
      }

    if (wantGen(f, "xref")) 
      fragment(vs.getId()+"-vs-xref", vsr.xref());
    if (wantGen(f, "expansion")) { 
      ValueSetExpansionOutcome exp = context.expandVS(vs, true);
      if (exp.getValueset() != null) {
        NarrativeGenerator gen = new NarrativeGenerator("", null, context);
        gen.setTooCostlyNote("This value set has >1000 codes in it. In order to keep the publication size manageable, only a selection (1000 codes) of the whole set of codes is shown");
        exp.getValueset().setCompose(null);
        exp.getValueset().setText(null);
        gen.generate(exp.getValueset(), false);
        String html = new XhtmlComposer().compose(exp.getValueset().getText().getDiv());
        fragment(vs.getId()+"-expansion", html);
      } else if (exp.getError() != null) 
        fragmentError(vs.getId()+"-expansion", exp.getError());
      else 
        fragmentError(vs.getId()+"-expansion", "Unknown Error");
    }
  }

  private void fragmentError(String name, String error) throws IOException {
    fragment(name, "<p style=\"color: maroon; font-weight: bold\">"+Utilities.escapeXml(error)+"</p>\r\n");
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws IOException 
   */
  private void generateOutputsConceptMap(FetchedResource f, ConceptMap cm) throws IOException {
    if (wantGen(f, "summary")) 
      fragmentError(cm.getId()+"-cm-summary", "yet to be done: concept map summary");
    if (wantGen(f, "content")) 
      fragmentError(cm.getId()+"-cm-content", "yet to be done: table presentation of the concept map");
    if (wantGen(f, "xref")) 
      fragmentError(cm.getId()+"-cm-xref", "yet to be done: list of all places where concept map is used");
  }

  private void generateOutputsStructureDefinition(FetchedResource f, StructureDefinition sd) throws Exception {
    // todo : generate shex itself
    if (wantGen(f, "shex")) 
      fragmentError(sd.getId()+"-shex", "yet to be done: shex as html");

    // todo : generate schematron itself
    if (wantGen(f, "sch")) 
      fragmentError(sd.getId()+"-sch", "yet to be done: schematron as html");

    // todo : generate json schema itself
    if (wantGen(f, "json-schema")) 
      fragmentError(sd.getId()+"-json-schema", "yet to be done: json schema as html");

    StructureDefinitionRenderer sdr = new StructureDefinitionRenderer(context, pathToSpec+"/", sd, Utilities.path(contentDir), igpkp, specDetails.getAsJsonObject("maps"));
    if (wantGen(f, "summary")) 
      fragment(sd.getId()+"-sd-summary", sdr.summary());
    if (wantGen(f, "header")) 
      fragment(sd.getId()+"-header", sdr.header());
    if (wantGen(f, "diff")) 
      fragment(sd.getId()+"-diff", sdr.diff(igpkp.getDefinitions(sd)));
    if (wantGen(f, "snapshot")) 
      fragment(sd.getId()+"-snapshot", sdr.snapshot(igpkp.getDefinitions(sd)));
    if (wantGen(f, "template-xml")) 
      fragmentError(sd.getId()+"-template-xml", "yet to be done: Xml template");
    if (wantGen(f, "template-json")) 
      fragmentError(sd.getId()+"-template-json", "yet to be done: Json template");
    if (wantGen(f, "template-ttl")) 
      fragmentError(sd.getId()+"-template-ttl", "yet to be done: Turtle template");
    if (wantGen(f, "uml")) 
      fragmentError(sd.getId()+"-uml", "yet to be done: UML as SVG");
    if (wantGen(f, "tx")) 
      fragment(sd.getId()+"-tx", sdr.tx());
    if (wantGen(f, "inv")) 
      fragment(sd.getId()+"-inv", sdr.inv());
    if (wantGen(f, "dict")) 
      fragment(sd.getId()+"-dict", sdr.dict());
    if (wantGen(f, "maps")) 
      fragment(sd.getId()+"-maps", sdr.mappings());
    if (wantGen(f, "xref")) 
      fragmentError(sd.getId()+"-sd-xref", "Yet to be done: xref");
  }

  private XhtmlNode getXhtml(FetchedResource f) {
    Element text = f.getElement().getNamedChild("text");
    if (text == null)
      return null;
    Element div = text.getNamedChild("div");
    if (div == null)
      return null;
    else
      return div.getXhtml();
  }

  private void fragment(String name, String content) throws IOException {
    File f = new File(Utilities.path(includesDir, name+".xhtml"));
    String s = f.exists() ? TextFile.fileToString(f.getAbsolutePath()) : "";
    if (!s.equals(content)) { 
      TextFile.stringToFile(content, Utilities.path(includesDir, name+".xhtml"), false);
      TextFile.stringToFile(pageWrap(content, name), Utilities.path(validationDir, name+".html"), true);
    }
  }

  private String pageWrap(String content, String title) {
    return "<html>\r\n"+
        "<head>\r\n"+
        "  <title>"+title+"</title>\r\n"+
        "  <link rel=\"stylesheet\" href=\"fhir.css\"/>\r\n"+
        "</head>\r\n"+
        "<body>\r\n"+
        content+
        "</body>\r\n"+
        "</html>\r\n";
  }

  public static void main(String[] args) throws Exception {
    System.out.println("FHIR Implementation Guide Publisher");
    Publisher self = new Publisher();
    self.configFile = getNamedParam(args, "-ig");
    self.output = getNamedParam(args, "-out");
    self.pathToSpec = getNamedParam(args, "-spec");
    self.setTxServer(getNamedParam(args, "-tx"));
    self.watch = hasParam(args, "-watch");

    if (self.configFile == null || self.pathToSpec == null) {
      System.out.println("");
      System.out.println("To use this publisher, run with the commands");
      System.out.println("");
      System.out.println("-ig [source] -out [folder] -spec [path] -tx [url] -watch");
      System.out.println("");
      System.out.println("-ig: a path or a url where the implementation guide control file is found");
      System.out.println("  see Wiki for Documentation");
      System.out.println("-out: a local folder where the output from the IG publisher will be generated");
      System.out.println("-spec: the location of the FHIR specification relative to the guide");
      System.out.println("  (can be an absolute URL, or relative if the guide will be published with FHIR)");
      System.out.println("-tx: (optional) Address to use for terminology server ");
      System.out.println("  (default is http://fhir3.healthintersections.com.au)");
      System.out.println("-watch (optional): if this is present, the publisher will not terminate;");
      System.out.println("  instead, it will stay running, an watch for changes to the IG or its ");
      System.out.println("  contents and re-run when it sees changes ");
      System.out.println("");
      System.out.println("The most important output from the publisher is validation.html");
      System.out.println("");
      System.out.println("For additional information, see http://wiki.hl7.org/index.php?title=Proposed_new_FHIR_IG_build_Process");
    } else 
      try {
        self.execute();
      } catch (Exception e) {
        System.out.println("Publishing Implementation Guide Failed: "+e.getMessage());
        System.out.println("");
        System.out.println("Stack Dump (for debugging):");
        e.printStackTrace();
      }
  }



  private void setTxServer(String s) {
    if (!Utilities.noString(s))
      txServer = s;

  }

  private static boolean hasParam(String[] args, String param) {
    for (String a : args)
      if (a.equals(param))
        return true;
    return false;
  }

  private static String getNamedParam(String[] args, String param) {
    boolean found = false;
    for (String a : args) {
      if (found)
        return a;
      if (a.equals(param)) {
        found = true;
      }
    }
    return null;
  }


}
