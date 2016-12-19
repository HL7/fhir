package org.hl7.fhir.igtools.publisher;

import java.awt.EventQueue;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;
import java.util.concurrent.TimeUnit;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.swing.UIManager;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.codec.Charsets;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.PumpStreamHandler;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.SystemUtils;
import org.hl7.fhir.convertors.R2ToR3Loader;
import org.hl7.fhir.convertors.VersionConvertorAdvisor;
import org.hl7.fhir.convertors.VersionConvertor_10_20;
import org.hl7.fhir.convertors.VersionConvertor_14_20;
import org.hl7.fhir.dstu3.conformance.ProfileUtilities;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext;
import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.elementmodel.ObjectConverter;
import org.hl7.fhir.dstu3.elementmodel.ParserBase.ValidationPolicy;
import org.hl7.fhir.dstu3.elementmodel.TurtleParser;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.Bundle.BundleType;
import org.hl7.fhir.dstu3.model.CapabilityStatement;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.Constants;
import org.hl7.fhir.dstu3.model.ContactDetail;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.DomainResource;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.dstu3.model.Enumerations.PublicationStatus;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.ExpressionNode;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePageComponent;
import org.hl7.fhir.dstu3.model.MetadataResource;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceFactory;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.dstu3.model.StructureMap;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapModelMode;
import org.hl7.fhir.dstu3.model.StructureMap.StructureMapStructureComponent;
import org.hl7.fhir.dstu3.model.UriType;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.dstu3.utils.EOperationOutcome;
import org.hl7.fhir.dstu3.utils.FHIRPathEngine;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator.IReferenceResolver;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator.ResourceWithReference;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities;
import org.hl7.fhir.dstu3.utils.StructureMapUtilities.StructureMapAnalysis;
import org.hl7.fhir.dstu3.utils.formats.Turtle;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.igtools.renderers.BaseRenderer;
import org.hl7.fhir.igtools.renderers.CodeSystemRenderer;
import org.hl7.fhir.igtools.renderers.JsonXhtmlRenderer;
import org.hl7.fhir.igtools.renderers.StructureDefinitionRenderer;
import org.hl7.fhir.igtools.renderers.StructureMapRenderer;
import org.hl7.fhir.igtools.renderers.SwaggerGenerator;
import org.hl7.fhir.igtools.renderers.ValidationPresenter;
import org.hl7.fhir.igtools.renderers.ValueSetRenderer;
import org.hl7.fhir.igtools.renderers.XmlXHtmlRenderer;
import org.hl7.fhir.igtools.spreadsheets.IgSpreadsheetParser;
import org.hl7.fhir.igtools.ui.GraphicalPublisher;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

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

public class Publisher implements IWorkerContext.ILoggingService, IReferenceResolver {
  
  public static final boolean USE_COMMONS_EXEC = true;

  public enum GenerationTool {
    Jekyll
  }

  private static final String IG_NAME = "!ig!";

  private static final String REDIRECT_SOURCE = "<html>\r\n<head>\r\n<meta http-equiv=\"Refresh\" content=\"0; url=site/index.html\"/>\r\n</head>\r\n"+
       "<body>\r\n<p>See here: <a href=\"site/index.html\">this link</a>.</p>\r\n</body>\r\n</html>\r\n";

  private String configFile;
  private String sourceDir;
  private String destDir;
  private String txServer = "http://fhir3.healthintersections.com.au/open";
//  private String txServer = "http://local.healthintersections.com.au:960/open";
  private String igPack = "";
  private boolean watch;

  private GenerationTool tool;

  private List<String> resourceDirs = new ArrayList<String>();
  private String pagesDir;
  private String tempDir;
  private String outputDir;
  private String specPath;
  private String qaDir;
  private String version;

  private String igName;
  private boolean autoBuildMode; // for the IG publication infrastructure

  private IFetchFile fetcher = new SimpleFetcher(resourceDirs);
  private SimpleWorkerContext context;
  private InstanceValidator validator;
  private IGKnowledgeProvider igpkp;
  private List<SpecMapManager> specMaps = new ArrayList<SpecMapManager>();
  private boolean first;

  private Map<ImplementationGuidePackageResourceComponent, FetchedFile> fileMap = new HashMap<ImplementationGuidePackageResourceComponent, FetchedFile>();
  private Map<String, FetchedFile> altMap = new HashMap<String, FetchedFile>();
  private List<FetchedFile> fileList = new ArrayList<FetchedFile>();
  private List<FetchedFile> changeList = new ArrayList<FetchedFile>();
  private List<String> fileNames = new ArrayList<String>();
  private Set<String> bndIds = new HashSet<String>();
  private List<Resource> loaded = new ArrayList<Resource>();
  private ImplementationGuide sourceIg;
  private ImplementationGuide pubIg;
  private List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
  private JsonObject configuration;
  private Calendar execTime = Calendar.getInstance();
  private Set<String> otherFilesStartup = new HashSet<String>();
  private Set<String> otherFilesRun = new HashSet<String>();
  private Set<String> regenList = new HashSet<String>();
  private StringBuilder filelog;
  private Set<String> allOutputs = new HashSet<String>();
  private Set<FetchedResource> examples = new HashSet<FetchedResource>();
  private HashMap<String, FetchedResource> resources = new HashMap<String, FetchedResource>();
  private HashMap<String, ImplementationGuidePageComponent> igPages = new HashMap<String, ImplementationGuidePageComponent>();

  private long globalStart;

  private ILoggingService logger = this;

  private HTLMLInspector inspector;

  private String prePagesDir;

  private String prePagesXslt;

  private byte[] xslt;

  private String historyPage;

  private String vsCache;

  private String adHocTmpDir;

  private NarrativeGenerator gen;

  public void execute(boolean clearCache) throws Exception {
    globalStart = System.nanoTime();
    initialize(clearCache);
    log("Load Content");
    load();

    long startTime = System.nanoTime();
    log("Processing Conformance Resources");
    loadConformance();
    log("Generating Narratives");
    generateNarratives();
    log("Validating Resources");
    try {
      validate();
    } catch (Exception ex){
      log(ex.toString());
      throw(ex);
    }
    log("Generating Outputs in "+outputDir);
    generate();
    long endTime = System.nanoTime();
    clean();
    log("Finished. "+presentDuration(endTime - startTime)+". Validation output in "+new ValidationPresenter().generate(sourceIg.getName(), errors, fileList, Utilities.path(destDir != null ? destDir : outputDir, "qa.html")));

    if (watch) {
      first = false;
      log("Watching for changes on a 5sec cycle");
      while (watch) { // terminated externally
        Thread.sleep(5000);
        if (load()) {
          log("Processing changes to "+Integer.toString(changeList.size())+(changeList.size() == 1 ? " file" : " files")+" @ "+genTime());
          startTime = System.nanoTime();
          loadConformance();
          generateNarratives();
          checkDependencies();
          validate();
          generate();
          clean();
          endTime = System.nanoTime();
          log("Finished. "+presentDuration(endTime - startTime)+". Validation output in "+new ValidationPresenter().generate(sourceIg.getName(), errors, fileList, Utilities.path(destDir != null ? destDir : outputDir, "qa.html")));
        }
      }
    } else
      log("Done");
  }

  @Override
  public ResourceWithReference resolve(String url) {
    String[] parts = url.split("\\/");
    if (parts.length != 2)
      return null;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement() != null && r.getElement().fhirType().equals(parts[0]) && r.getId().equals(parts[1])) {
          String path = igpkp.getLinkFor(r);
          return new ResourceWithReference(path, gen.new ResurceWrapperMetaElement(r.getElement()));
        }
      }
    }
    for (SpecMapManager sp : specMaps) {
      String fp = sp.getBase()+"/"+url;
      String path;
      try {
        path = sp.getPath(fp);
      } catch (Exception e) {
        path = null;
      }
      if (path != null)
        return new ResourceWithReference(path, null);
    }
    return null;
    
  }

  private void generateNarratives() throws IOException, EOperationOutcome, FHIRException {
    dlog("gen narratives");
    gen = new NarrativeGenerator("", "", context, this);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        dlog("narrative for "+f.getName()+" : "+r.getId());
        if (r.getResource() != null) {
          boolean regen = false;
          if (r.getResource() instanceof DomainResource && !(((DomainResource) r.getResource()).hasText() && ((DomainResource) r.getResource()).getText().hasDiv()))
            regen = gen.generate((DomainResource) r.getResource());
          if (r.getResource() instanceof Bundle)
            regen = gen.generate((Bundle) r.getResource());
          if (regen)
            r.setElement(new ObjectConverter(context).convert(r.getResource()));
        } else {
          if ("http://hl7.org/fhir/StructureDefinition/DomainResource".equals(r.getElement().getProperty().getStructure().getBaseDefinition()) && !hasNarrative(r.getElement())) {
            gen.generate(r.getElement(), true);
          }
        }
      }
    }
  }

  private boolean hasNarrative(Element element) {
    return element.hasChild("text") && element.getNamedChild("text").hasChild("div");
  }

  private void clean() throws Exception {
    for (Resource r : loaded)
      context.dropResource(r);
    for (FetchedFile f : fileList)
      f.dropSource();
    if (destDir != null) {
      if (!(new File(destDir).exists()))
        Utilities.createDirectory(destDir);
      Utilities.copyDirectory(outputDir, destDir, null);
    }
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
    for (ConceptSetComponent cc : vs.getCompose().getInclude()) {
      for (UriType vsi : cc.getValueSet()) {
        FetchedFile fi = getFileForUri(vsi.getValue());
        if (fi != null)
          f.getDependencies().add(fi);
      }
    }
    for (ConceptSetComponent cc : vs.getCompose().getExclude()) {
      for (UriType vsi : cc.getValueSet()) {
        FetchedFile fi = getFileForUri(vsi.getValue());
        if (fi != null)
          f.getDependencies().add(fi);
      }
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
      if (getResourceForUri(f, uri) != null)
        return f;
    }
    return null;
  }

  private FetchedResource getResourceForUri(FetchedFile f, String uri) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          MetadataResource bc = (MetadataResource) r.getResource();
          if (bc.getUrl().equals(uri))
          return r;
        }
      }
    return null;
    }

  private FetchedResource getResourceForUri(String uri) {
    for (FetchedFile f : fileList) {
      FetchedResource r = getResourceForUri(f, uri);
      if (r != null)
        return r;
    }
    return null;
  }

  private void loadProfileDependencies(FetchedFile f, FetchedResource r) {
    StructureDefinition sd = (StructureDefinition) r.getResource();
    FetchedFile fi = getFileForUri(sd.getBaseDefinition());
    if (fi != null)
      f.getDependencies().add(fi);
  }

  private String str(JsonObject obj, String name) throws Exception {
    if (!obj.has(name))
      throw new Exception("Property "+name+" not found");
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new Exception("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new Exception("Property "+name+" not a string");
    return p.getAsString();
  }

  private String ostr(JsonObject obj, String name) throws Exception {
    if (!obj.has(name))
      return null;
    if (!(obj.get(name) instanceof JsonPrimitive))
      return null;
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      return null;
    return p.getAsString();
  }

  private String str(JsonObject obj, String name, String defValue) throws Exception {
    if (!obj.has(name))
      return defValue;
    if (!(obj.get(name) instanceof JsonPrimitive))
      throw new Exception("Property "+name+" not a primitive");
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (!p.isString())
      throw new Exception("Property "+name+" not a string");
    return p.getAsString();
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

  public void initialize(boolean clearCache) throws Exception {
    first = true;
    boolean copyTemplate = false;
    if (configFile == null) {
      buildConfigFile();
      copyTemplate = true;
    } else
      log("Load Configuration from "+configFile);
    configuration = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(configFile));
    if (!"jekyll".equals(str(configuration, "tool")))
      throw new Exception("Error: configuration file must include a \"tool\" property with a value of 'jekyll'");
    tool = GenerationTool.Jekyll;
    version = ostr(configuration, "version");
    if (Utilities.noString(version))
      version = Constants.VERSION;
    
    if (!configuration.has("paths") || !(configuration.get("paths") instanceof JsonObject))
      throw new Exception("Error: configuration file must include a \"paths\" object");
    JsonObject paths = configuration.getAsJsonObject("paths");
    String root = Utilities.getDirectoryForFile(configFile);
    if (Utilities.noString(root))
      root = getCurentDirectory();
    // We need the root to be expressed as a full path.  getDirectoryForFile will do that in general, but not in Eclipse
    root = new File(root).getAbsolutePath();
    log("Root directory: "+root);
    if (paths.get("resources") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("resources"))
        resourceDirs.add(Utilities.path(root, ((JsonPrimitive) e).getAsString()));
    } else
      resourceDirs.add(Utilities.path(root, str(paths, "resources", "resources")));
    pagesDir =  Utilities.path(root, str(paths, "pages", "pages"));
    tempDir = Utilities.path(root, str(paths, "temp", "temp"));
    outputDir = Utilities.path(root, str(paths, "output", "output"));
    qaDir = Utilities.path(root, str(paths, "qa"));
    vsCache = ostr(paths, "txCache");
    if (vsCache != null)
      vsCache = Utilities.path(root, vsCache);
    else if (autoBuildMode)
      vsCache = Utilities.path(System.getProperty("java.io.tmpdir"), "fhircache");
    else
      vsCache = Utilities.path(System.getProperty("user.home"), "fhircache");

    specPath = str(paths, "specification");
    if (configuration.has("pre-process")) {
      JsonObject pp = configuration.getAsJsonObject("pre-process");
      prePagesDir = Utilities.path(root, str(pp, "folder"));
      prePagesXslt = Utilities.path(root, str(pp, "transform"));
      checkDir(prePagesDir);
      checkFile(prePagesXslt);
      xslt = TextFile.fileToBytes(prePagesXslt);
    }

    igName = Utilities.path(resourceDirs.get(0), configuration.get("source").getAsString());

    inspector = new HTLMLInspector(outputDir, specMaps);
    historyPage = ostr(paths, "history");
    if (historyPage != null)
      inspector.getManual().add(historyPage);

    dlog("Check folders");
    for (String s : resourceDirs) {
      dlog("Source: "+s);
      checkDir(s);
    }
    dlog("Pages: "+pagesDir);
    checkDir(pagesDir);
    dlog("Temp: "+tempDir);
    Utilities.clearDirectory(tempDir);
    forceDir(tempDir);
    forceDir(Utilities.path(tempDir, "_includes"));
    forceDir(Utilities.path(tempDir, "_data"));
    dlog("Output: "+outputDir);
    forceDir(outputDir);
    Utilities.clearDirectory(outputDir);
    dlog("Temp: "+qaDir);
    forceDir(qaDir);

    Utilities.createDirectory(vsCache);
    if (clearCache || autoBuildMode) {
      log("Terminology Cache is at "+vsCache+". Clearing now");
      Utilities.clearDirectory(vsCache);
    } else
      log("Terminology Cache is at "+vsCache);
    if (!new File(vsCache).exists())
      throw new Exception("Unable to access or create the cache directory at "+vsCache);
    
    if (!igPack.isEmpty()) {
      if (igPack.startsWith("http:")) {
        throw new Error("cannot specify a web location for -spec at the moment"); /// gg to do this
      } else {
        File igPackFile = new File(igPack);
        log("Loading ig pack from specified path " + igPackFile.getAbsolutePath());
        context = SimpleWorkerContext.fromPack(igPackFile.getAbsolutePath());
      }
    } else if (version.equals(Constants.VERSION)) {
      try {
        log("Load Validation Pack (internal)");
        context = SimpleWorkerContext.fromClassPath("igpack.zip");
      } catch (NullPointerException npe) {
        log("Unable to find igpack.zip in the jar. Attempting to use local igpack.zip");
        
      	File igPackFile = null;
      	
      	if (System.getProperty("igpack") != null) {
      		igPackFile = new File(System.getProperty("igpack"));
      	}
      	
      	if (igPackFile == null || !igPackFile.exists()) {
      		igPackFile = new File("C:\\work\\org.hl7.fhir\\build\\publish\\igpack.zip");
      	}
      	
      	if (igPackFile != null && igPackFile.exists()) {
      		log("Found local ig pack at " + igPackFile.getAbsolutePath());
      		context = SimpleWorkerContext.fromPack(igPackFile.getAbsolutePath());
      	}
      	else {
      		log("No local igpack.zip found");
      	}
      }
    } else
      loadValidationPack();
    if (copyTemplate)
      copyTemplate();
    context.setLogger(logger);
    log("Definitions "+context.getVersion());
    context.setAllowLoadingDuplicates(true);
    context.setExpandCodesLimit(1000);
    log("Connect to Terminology Server at "+txServer);
    context.setExpansionProfile(makeExpProfile());
    context.initTS(vsCache, txServer);
    String sct = str(configuration, "sct-edition", "");
    if (Utilities.noString(sct))
      throw new Exception("Must specify which SNOMED CT edition ('sct-edition') to use in the config file");
    context.getExpansionProfile().addFixedVersion().setSystem("http://snomed.info/sct").setVersion(sct);
    context.getExpansionProfile().setActiveOnly("true".equals(ostr(configuration, "activeOnly")));
    if (txServer == null || !txServer.contains(":")) {
      log("WARNING: Running without terminology server - terminology content will likely not publish correctly");
      context.setCanRunWithoutTerminology(true);
    } else
      context.connectToTSServer(txServer);
    
    // ;
    validator = new InstanceValidator(context);
    validator.setAllowXsiLocation(true);
    validator.setNoBindingMsgSuppressed(true);
    if (paths.get("extension-domains") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("extension-domains"))
        validator.getExtensionDomains().add(((JsonPrimitive) e).getAsString());
    }

    loadSpecDetails(context.getBinaries().get("spec.internals"));
    igpkp = new IGKnowledgeProvider(context, specPath, configuration, errors);
    igpkp.loadSpecPaths(specMaps.get(0));
    fetcher.setPkp(igpkp);
    validator.setFetcher(new ValidationServices(context, igpkp, fileList));
    for (String s : context.getBinaries().keySet())
      if (needFile(s)) {
        checkMakeFile(context.getBinaries().get(s), Utilities.path(qaDir, s), otherFilesStartup);
        checkMakeFile(context.getBinaries().get(s), Utilities.path(tempDir, s), otherFilesStartup);
      }
    otherFilesStartup.add(Utilities.path(tempDir, "_data"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "fhir.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "structuredefinitions.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "pages.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_includes"));

    JsonArray deps = configuration.getAsJsonArray("dependencyList");
    if (deps != null) {
      for (JsonElement dep : deps) {
        loadIg((JsonObject) dep);
      }
    }
    log("Initialization complete");
    // now, do regeneration
    JsonArray regenlist = configuration.getAsJsonArray("regenerate");
    if (regenlist != null)
      for (JsonElement regen : regenlist)
        regenList.add(((JsonPrimitive) regen).getAsString());
  }

  public class FoundResource {
    private String path;
    private FhirFormat format;
    private String type;
    private String id;
    private String url;
    public FoundResource(String path, FhirFormat format, String type, String id, String url) {
      super();
      this.path = path;
      this.format = format;
      this.type = type;
      this.id = id;
    }
    public String getPath() {
      return path;
    }
    public FhirFormat getFormat() {
      return format;
    }
    public String getType() {
      return type;
    }
    public String getId() {
      return id;
    }
    public String getUrl() {
      return url;
    }
  }

  private void copyTemplate() throws IOException {
    byte[] template = context.getBinaries().get("ig-template.zip");
    ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(template));
    byte[] buffer = new byte[2048];
    ZipEntry entry;
    while((entry = zip.getNextEntry())!=null) {
      String filename = Utilities.path(adHocTmpDir, entry.getName());
      String dir = Utilities.getDirectoryForFile(filename);
      Utilities.createDirectory(dir);
      FileOutputStream output = new FileOutputStream(filename);
      int len = 0;
      while ((len = zip.read(buffer)) > 0)
        output.write(buffer, 0, len);
      output.close();
    }
    zip.close();
    // replace config
    configuration = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(configFile));
  }
  
  private void buildConfigFile() throws IOException, org.hl7.fhir.exceptions.FHIRException, FHIRFormatError {
    log("Process Resources from "+sourceDir);
    adHocTmpDir = Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-scratch");
    if (!new File(adHocTmpDir).exists())
      Utilities.createDirectory(adHocTmpDir);
    Utilities.clearDirectory(adHocTmpDir);
    configFile = Utilities.path(adHocTmpDir, "ig.json");
    // temporary config, until full ig template is in place
    TextFile.stringToFile(
            "{\r\n"+
            "  \"tool\" : \"jekyll\",\r\n"+
            "  \"paths\" : {\r\n"+
            "    \"resources\" : \"resources\",\r\n"+
            "    \"pages\" : \"pages\",\r\n"+
            "    \"temp\" : \"temp\",\r\n"+
            "    \"qa\" : \"qa\",\r\n"+
            "    \"output\" : \"output\",\r\n"+
            "    \"specification\" : \"http://build.fhir.org/\"\r\n"+
            "  },\r\n"+
            "  \"sct-edition\": \"http://snomed.info/sct/900000000000207008\",\r\n"+
            "  \"source\": \"ig.xml\"\r\n"+
            "}\r\n", configFile, false);
    Utilities.createDirectory(Utilities.path(adHocTmpDir, "resources"));
    Utilities.createDirectory(Utilities.path(adHocTmpDir, "pages"));
  }

  private void loadValidationPack() throws FileNotFoundException, IOException, FHIRException {
    String source;
    if (version.equals("1.4.0")) 
      source = "http://hl7.org/fhir/2016May/igpack.zip"; 
    else if (version.equals("1.0.2")) 
      source = "http://hl7.org/fhir/DSTU2/igpack.zip";
    else
      throw new FHIRException("Unsupported version "+version);
    
    String fn = "";
    if (new File("c:\\temp\\igpack\\igpack.zip").exists())
      fn = "c:\\temp\\igpack\\igpack.zip";
    else {
      log("Fetch Validation Pack from "+source);
      fn = grabToLocalCache(source);
      }
    log("Local Validation Pack from cache location " + fn);
    if ("1.0.2".equals(version)) {
      context = SimpleWorkerContext.fromPack(fn, new R2ToR3Loader());
    } else
      context = SimpleWorkerContext.fromPack(fn);
  }

  private String grabToLocalCache(String source) throws IOException {
    String fn = Utilities.path(vsCache, "validation-"+version+".zip");
    File f = new File(fn);
    if (!f.exists()) {
      URL url = new URL(source);
      URLConnection c = url.openConnection();
      byte[] cnt = IOUtils.toByteArray(c.getInputStream());
      TextFile.bytesToFile(cnt, fn); 
    }
    return fn;
  }

  private ExpansionProfile makeExpProfile() {
    ExpansionProfile ep  = new ExpansionProfile();
    ep.setId("dc8fd4bc-091a-424a-8a3b-6198ef146891"); // change this to blow the cache
    ep.setUrl("http://hl7.org/fhir/ExpansionProfile/"+ep.getId());
    // all defaults....
    return ep;
  }

  private void loadIg(JsonObject dep) throws Exception {
    String name = str(dep, "name");
    if (!Utilities.isToken(name))
      throw new Exception("IG Name must be a valid token ("+name+")");
    String location = str(dep, "location");
    String source = ostr(dep, "source");
    if (Utilities.noString(source))
      source = location;
    log("Load "+name+" ("+location+") from "+source);
    Map<String, byte[]> files = fetchDefinitions(source, name);
    SpecMapManager igm = new SpecMapManager(files.get("spec.internals"));
    igm.setName(name);
    igm.setBase(location);
    specMaps.add(igm);
    if (!Constants.VERSION.equals(igm.getVersion()))
      log("Version mismatch. This IG is version "+Constants.VERSION+", while the IG is from version "+igm.getVersion()+". Will try to run anyway)");

    for (String fn : files.keySet()) {
      if (fn.endsWith(".json")) {
        Resource r;
        try {
          org.hl7.fhir.dstu3.formats.JsonParser jp = new org.hl7.fhir.dstu3.formats.JsonParser();
          r = jp.parse(new ByteArrayInputStream(files.get(fn)));
        } catch (Exception e) {
          throw new Exception("Unable to parse "+fn+" from IG "+name, e);
        }
        if (r instanceof MetadataResource) {
          String u = ((MetadataResource) r).getUrl();
          if (u != null) {
            String p = igm.getPath(u);
            if (p == null)
              throw new Exception("Internal error in IG "+name+" map: No identity found for "+u);
            r.setUserData("path", location+"/"+p);
            context.seeResource(u, r);
          }
        }
      }
    }
  }

  private Map<String, byte[]> fetchDefinitions(String source, String name) throws Exception {
    // todo: if filename is a URL
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    String filename;
    if (source.startsWith("http:") || source.startsWith("https:"))
      filename = fetchFromURL(source, name);
    else if (Utilities.isAbsoluteFileName(source))
      filename = Utilities.path(source, "definitions.json.zip");
    else
      filename = Utilities.path(Utilities.getDirectoryForFile(configFile), source, "definitions.json.zip");
    ZipInputStream zip = new ZipInputStream(new FileInputStream(filename));
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      int size;
      byte[] buffer = new byte[2048];

      ByteArrayOutputStream bytes = new ByteArrayOutputStream();
      BufferedOutputStream bos = new BufferedOutputStream(bytes, buffer.length);

      while ((size = zip.read(buffer, 0, buffer.length)) != -1) {
        bos.write(buffer, 0, size);
      }
      bos.flush();
      bos.close();
      res.put(ze.getName(), bytes.toByteArray());

      zip.closeEntry();
    }
    zip.close();
    return res;
  }

  private String fetchFromURL(String source, String name) throws Exception {
    String filename = Utilities.path(vsCache, name+".cache");
    if (new File(filename).exists())
      return filename;
    
    if (!source.endsWith("definitions.json.zip") && !source.endsWith("definitions.xml.zip"))
      source = Utilities.pathReverse(source, "definitions.json.zip");
    try {
      URL url = new URL(source);
      URLConnection c = url.openConnection();
      byte[] cnt = IOUtils.toByteArray(c.getInputStream());
      TextFile.bytesToFile(cnt, filename);
      return filename;
    } catch (Exception e) {
      throw new Exception("Unable to load definitions from URL '"+source+"': "+e.getMessage(), e);
    }   
  }

  private static String getCurentDirectory() {
    String currentDirectory;
    File file = new File(".");
    currentDirectory = file.getAbsolutePath();
    return currentDirectory;
  }

  private void checkDir(String dir) throws Exception {
    File f = new File(dir);
    if (!f.exists())
      throw new Exception(String.format("Error: folder %s not found", dir));
    else if (!f.isDirectory())
      throw new Exception(String.format("Error: Output must be a folder (%s)", dir));
  }

  private void checkFile(String fn) throws Exception {
    File f = new File(fn);
    if (!f.exists())
      throw new Exception(String.format("Error: folder %s not found", fn));
    else if (f.isDirectory())
      throw new Exception(String.format("Error: Output must be a file (%s)", fn));
  }

  private void forceDir(String dir) throws Exception {
    File f = new File(dir);
    if (!f.exists())
      Utilities.createDirectory(dir);
    else if (!f.isDirectory())
      throw new Exception(String.format("Error: Output must be a folder (%s)", dir));
  }

  private boolean checkMakeFile(byte[] bs, String path, Set<String> outputTracker) throws IOException {
    dlog("Check Generate "+path);
    if (first) {
      String s = path.toLowerCase();
      if (allOutputs.contains(s))
        throw new Error("Error generating build: the file "+path+" is being generated more than once (may differ by case)");
      allOutputs.add(s);
    }
    outputTracker.add(path);
    File f = new CSFile(path);
    byte[] existing = null;
    if (f.exists())
      existing = TextFile.fileToBytes(path);
    if (!Arrays.equals(bs, existing)) {
      TextFile.bytesToFile(bs, path);
      return true;
    }
    return false;
  }

  private boolean needFile(String s) {
    if (s.endsWith(".css"))
      return true;
    if (s.startsWith("tbl"))
      return true;
    if (s.startsWith("icon"))
      return true;
    if (Utilities.existsInList(s, "modifier.png", "mustsupport.png", "summary.png", "lock.png", "external.png", "cc0.png", "target.png", "link.svg"))
      return true;
    return false;
  }

  public void loadSpecDetails(byte[] bs) throws IOException {
    SpecMapManager map = new SpecMapManager(bs);
    map.setBase(specPath);
    specMaps.add(map);
  }


  private boolean load() throws Exception {
    fileList.clear();
    changeList.clear();
    bndIds.clear();
    boolean needToBuild = false;
    FetchedFile igf = fetcher.fetch(igName);
    needToBuild = noteFile(IG_NAME, igf) || needToBuild;
    if (needToBuild) {
      sourceIg = (ImplementationGuide) parse(igf);
      FetchedResource igr = igf.addResource();
      igr.setElement(loadFromXml(igf));
      igr.setResource(sourceIg);
      igr.setId(sourceIg.getId()).setTitle(sourceIg.getName());
    } else {
      // special case; the source is updated during the build, so we track it differently
      altMap.get(IG_NAME).getResources().get(0).setResource(sourceIg);
    }

    pubIg = sourceIg.copy();

    // load any bundles
    if (sourceDir != null)
      needToBuild = loadResources(needToBuild, igf);
    needToBuild = loadSpreadsheets(needToBuild, igf);
    needToBuild = loadBundles(needToBuild, igf);
    for (ImplementationGuidePackageComponent pack : sourceIg.getPackage()) {
      for (ImplementationGuidePackageResourceComponent res : pack.getResource()) {
        if (!res.hasSourceReference())
          throw new Exception("Missing source reference on "+res.getName());
        if (!bndIds.contains(res.getSourceReference().getReference())) {
          FetchedFile f = fetcher.fetch(res.getSource(), igf);
          needToBuild = noteFile(res, f) || needToBuild;
          determineType(f);
          if (res.hasExampleFor() && res.getExampleFor().hasReference()) {
            if (f.getResources().size()!=1)
              throw new Exception("Can't have an exampleFor unless the file has exactly one resource");
            FetchedResource r = f.getResources().get(0);
            examples.add(r);
            String ref = res.getExampleFor().getReference();
            if (ref.contains(":")) {
              r.setExampleUri(ref);
            } else if (sourceIg.getUrl().contains("ImplementationGuide/")) 
              r.setExampleUri(sourceIg.getUrl().substring(0, sourceIg.getUrl().indexOf("ImplementationGuide/")) + ref);
            else
              r.setExampleUri(Utilities.pathReverse(sourceIg.getUrl(), ref));
          }
        }
      }
    }
  
    // load static pages
    needToBuild = loadPrePages() || needToBuild;
    needToBuild = loadPages() || needToBuild;

    loadIgPages(sourceIg.getPage(), igPages);

    for (FetchedFile f: fileList) {
      for (FetchedResource r: f.getResources()) {
        resources.put(igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null), r);
      }
    }
    
    execTime = Calendar.getInstance();
    return needToBuild;
  }

  private void loadIgPages(ImplementationGuidePageComponent page, HashMap<String, ImplementationGuidePageComponent> map) {
    map.put(page.getSource(), page);
    for (ImplementationGuidePageComponent childPage: page.getPage()) {
      loadIgPages(childPage, map);
    }
  }

  private boolean loadPrePages() throws Exception {
    if (prePagesDir == null)
      return false;

    FetchedFile dir = fetcher.fetch(prePagesDir);
    if (!dir.isFolder())
      throw new Exception("pre-processed page reference is not a folder");
    return loadPrePages(dir);
  }

  private boolean loadPrePages(FetchedFile dir) throws Exception {
    boolean changed = false;
    if (!altMap.containsKey("pre-page/"+dir.getPath())) {
      changed = true;
      altMap.put("pre-page/"+dir.getPath(), dir);
      dir.setProcessMode(FetchedFile.PROCESS_XSLT);
      addFile(dir);
    }
    for (String link : dir.getFiles()) {
      FetchedFile f = fetcher.fetch(link);
      if (f.isFolder())
        changed = loadPrePages(f) || changed;
      else
        changed = loadPrePage(f) || changed;
    }
    return changed;
  }

  private boolean loadPrePage(FetchedFile file) {
    FetchedFile existing = altMap.get("pre-page/"+file.getPath());
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      file.setProcessMode(FetchedFile.PROCESS_XSLT);
      addFile(file);
      altMap.put("pre-page/"+file.getPath(), file);
      return true;
    } else {
      return false;
    }
  }

  private boolean loadPages() throws Exception {
    FetchedFile dir = fetcher.fetch(pagesDir);
    if (!dir.isFolder())
      throw new Exception("page reference is not a folder");
    return loadPages(dir);
  }

  private boolean loadPages(FetchedFile dir) throws Exception {
    boolean changed = false;
    if (!altMap.containsKey("page/"+dir.getPath())) {
      changed = true;
      altMap.put("page/"+dir.getPath(), dir);
      dir.setProcessMode(FetchedFile.PROCESS_NONE);
      addFile(dir);
    }
    for (String link : dir.getFiles()) {
      FetchedFile f = fetcher.fetch(link);
      if (f.isFolder())
        changed = loadPages(f) || changed;
      else
        changed = loadPage(f) || changed;
    }
    return changed;
  }

  private boolean loadPage(FetchedFile file) {
    FetchedFile existing = altMap.get("page/"+file.getPath());
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      file.setProcessMode(FetchedFile.PROCESS_NONE);
      addFile(file);
      altMap.put("page/"+file.getPath(), file);
      return true;
    } else {
      return false;
    }
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
        r.setElement(convertToElement(r.getResource()));
        for (UriType p : b.getResource().getMeta().getProfile())
          r.getProfiles().add(p.asStringValue());
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else
      f = altMap.get("Bundle/"+be.getAsString());
    ImplementationGuidePackageComponent pck = pubIg.addPackage().setName(f.getTitle());
    for (FetchedResource r : f.getResources()) {
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
      ImplementationGuidePackageResourceComponent res = pck.addResource();
      res.setExample(false).setName(r.getId()).setSource(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
    }
    return changed || needToBuild;
  }

  private boolean loadResources(boolean needToBuild, FetchedFile igf) throws Exception {
    List<FetchedFile> resources = fetcher.scan(sourceDir, context);
    for (FetchedFile ff : resources) {
      needToBuild = loadResource(needToBuild, ff);
    }
    return needToBuild;
  }

  private boolean loadResource(boolean needToBuild, FetchedFile f) throws Exception {
    dlog("load "+f.getPath());
    boolean changed = noteFile(f.getPath(), f);
    if (changed) {
      determineType(f);
    }
    ImplementationGuidePackageComponent pck = pubIg.getPackageFirstRep();
    for (FetchedResource r : f.getResources()) {
      ImplementationGuidePackageResourceComponent res = pck.addResource();
      res.setExample(false).setName(r.getTitle()).setSource(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
    }
    return changed || needToBuild;
  }


  private boolean loadSpreadsheets(boolean needToBuild, FetchedFile igf) throws Exception {
    Set<String> knownValueSetIds = new HashSet<>();
    JsonArray spreadsheets = configuration.getAsJsonArray("spreadsheets");
    if (spreadsheets != null) {
      for (JsonElement be : spreadsheets) {
        needToBuild = loadSpreadsheet((JsonPrimitive) be, needToBuild, igf, knownValueSetIds);
      }
    }
    return needToBuild;
  }

  private boolean loadSpreadsheet(JsonPrimitive be, boolean needToBuild, FetchedFile igf, Set<String> knownValueSetIds) throws Exception {
    if (be.getAsString().startsWith("!"))
      return false;

    String path = Utilities.path(Utilities.getDirectoryForFile(igName), be.getAsString());
    FetchedFile f = fetcher.fetch(path);
    boolean changed = noteFile("Spreadsheet/"+be.getAsString(), f);
    if (changed) {
      f.getValuesetsToLoad().clear();
      dlog("load "+path);
      f.setBundle(new IgSpreadsheetParser(context, execTime, igpkp.getCanonical(), f.getValuesetsToLoad(), first, context.getBinaries().get("mappingSpaces.details"), knownValueSetIds).parse(f));
      for (BundleEntryComponent b : f.getBundle().getEntry()) {
        FetchedResource r = f.addResource();
        r.setResource(b.getResource());
        r.setId(b.getResource().getId());
        r.setElement(convertToElement(r.getResource()));
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else {
      f = altMap.get("Spreadsheet/"+be.getAsString());
    }

    for (String id : f.getValuesetsToLoad().keySet()) {
      if (!knownValueSetIds.contains(id)) {
        String vr = f.getValuesetsToLoad().get(id);
        path = Utilities.path(Utilities.getDirectoryForFile(igName), vr);
        FetchedFile fv = fetcher.fetchFlexible(path);
        boolean vrchanged = noteFile("sp-ValueSet/"+vr, fv);
        if (vrchanged) {
          determineType(fv);
          checkImplicitResourceIdentity(id, fv);
        }
        knownValueSetIds.add(id);
        // ok, now look for an implicit code system with the same name
        boolean crchanged = false;
        String cr = vr.replace("valueset-", "codesystem-");
        if (!cr.equals(vr)) {
          path = Utilities.path(Utilities.getDirectoryForFile(igName), cr);
          if (fetcher.canFetchFlexible(path)) {
            fv = fetcher.fetchFlexible(path);
            crchanged = noteFile("sp-CodeSystem/"+vr, fv);
            if (crchanged) {
              determineType(fv);
              checkImplicitResourceIdentity(id, fv);
            }
          }
        }
        changed = changed || vrchanged || crchanged;
      }
    }
    ImplementationGuidePackageComponent pck = pubIg.addPackage().setName(f.getTitle());
    for (FetchedResource r : f.getResources()) {
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
      ImplementationGuidePackageResourceComponent res = pck.addResource();
      res.setExample(false).setName(r.getTitle()).setSource(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
    }
    return changed || needToBuild;
  }


  private void checkImplicitResourceIdentity(String id, FetchedFile fv) throws Exception {
    // check the resource ids:
    String rid = fv.getResources().get(0).getId();
    String rurl = fv.getResources().get(0).getElement().getChildValue("url");
    if (Utilities.noString(rurl))
      throw new Exception("ValueSet has no canonical URL "+fv.getName());
    if (!id.equals(rid))
      throw new Exception("ValueSet has wrong id ("+rid+", expecting "+id+") in "+fv.getName());
    if (!tail(rurl).equals(rid))
      throw new Exception("resource id/url mismatch: "+id+" vs "+rurl+" for "+fv.getResources().get(0).getTitle()+" in "+fv.getName());
    if (!rurl.startsWith(igpkp.getCanonical()))
      throw new Exception("base/ resource url mismatch: "+igpkp.getCanonical()+" vs "+rurl);
  }


  private String tail(String url) {
    return url.substring(url.lastIndexOf("/")+1);
  }

  private void loadConformance() throws Exception {
    load("NamingSystem");
    load("CodeSystem");
    load("ValueSet");
    load("ConceptMap");
    load("DataElement");
    load("StructureDefinition");
    load("OperationDefinition");
    load("CapabilityStatement");
    generateSnapshots();
    generateLogicalMaps();
    load("StructureMap");
    generateAdditionalExamples();
    executeTransforms();
    validateExpressions();
  }

  private void executeTransforms() throws FHIRException, Exception {
    if ("true".equals(ostr(configuration, "do-transforms"))) {
      MappingServices services = new MappingServices(context, igpkp.getCanonical());
      StructureMapUtilities utils = new StructureMapUtilities(context, context.getTransforms(), services, igpkp);
      
      // ok, our first task is to generate the profiles
      for (FetchedFile f : changeList) {
        List<StructureMap> worklist = new ArrayList<StructureMap>();
        for (FetchedResource r : f.getResources()) {
          if (r.getResource() != null && r.getResource() instanceof StructureDefinition) {
            List<StructureMap> transforms = context.findTransformsforSource(((StructureDefinition) r.getResource()).getUrl());
            worklist.addAll(transforms);
          }
        }

        for (StructureMap map : worklist) {
          StructureMapAnalysis analysis = utils.analyse(null, map);
          map.setUserData("analysis", analysis);
          for (StructureDefinition sd : analysis.getProfiles()) {
            FetchedResource nr = new FetchedResource();
            nr.setElement(convertToElement(sd));
            nr.setId(sd.getId());
            nr.setResource(sd);
            nr.setTitle("Generated Profile (by Transform)");
            f.getResources().add(nr);
            igpkp.findConfiguration(f, nr);
            sd.setUserData("path", igpkp.getLinkFor(nr));
            generateSnapshot(f, nr, sd, true);
          }
        }
      }
      
      for (FetchedFile f : changeList) {
        Map<FetchedResource, List<StructureMap>> worklist = new HashMap<FetchedResource, List<StructureMap>>();
        for (FetchedResource r : f.getResources()) {
          List<StructureMap> transforms = context.findTransformsforSource(r.getElement().getProperty().getStructure().getUrl());
          if (transforms.size() > 0) {
            worklist.put(r, transforms);
          }
        }
        for (Entry<FetchedResource, List<StructureMap>> t : worklist.entrySet()) {
          int i = 0;
          for (StructureMap map : t.getValue()) {
            boolean ok = true;
            String tgturl = null;
            for (StructureMapStructureComponent st : map.getStructure()) {
              if (st.getMode() == StructureMapModelMode.TARGET) {
                if (tgturl == null)
                  tgturl = st.getUrl();
                else
                  ok = false;
              }
            }
            if (ok) {
              Resource target = new Bundle().setType(BundleType.COLLECTION);
              if (tgturl != null) {
                StructureDefinition tsd = context.fetchResource(StructureDefinition.class, tgturl);
                if (tsd == null) 
                  throw new Exception("Unable to find definition "+tgturl);
                target = ResourceFactory.createResource(tsd.getType());
              }
              if (t.getValue().size() > 1)
                target.setId(t.getKey().getId()+"-map-"+Integer.toString(i));
              else
                target.setId(t.getKey().getId()+"-map");
              i++;
              services.reset();
              utils.transform(target, t.getKey().getElement(), map, target);
              FetchedResource nr = new FetchedResource();
              nr.setElement(convertToElement(target));
              nr.setId(target.getId());
              nr.setResource(target);
              nr.setTitle("Generated Example (by Transform)");
              nr.setValidateByUserData(true);
              f.getResources().add(nr);
              igpkp.findConfiguration(f, nr);
            }
          }
        }
      }
    }
  }

  private boolean noteFile(ImplementationGuidePackageResourceComponent key, FetchedFile file) {
    FetchedFile existing = fileMap.get(key);
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      fileList.add(file);
      fileMap.put(key, file);
      addFile(file);
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
      addFile(file);
      return true;
    } else {
      fileList.add(existing); // this one is already parsed
      return false;
    }
  }

  private void addFile(FetchedFile file) {
//  	if (fileNames.contains(file.getPath())) {
//  		dlog("Found multiple definitions for file: " + file.getName()+ ".  Using first definition only.");
//  	} else {
  	  fileNames.add(file.getPath());
  	  changeList.add(file);
//  	}
  }

  private void determineType(FetchedFile file) throws Exception {

    if (file.getResources().isEmpty()) {
      file.getErrors().clear();
      Element e = null;
      FetchedResource r = null;

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
      try {

        r = file.addResource();
        String id = e.getChildValue("id");
        if (Utilities.noString(id))
          throw new Exception("Resource has no id in "+file.getPath());
        r.setElement(e).setId(id).setTitle(e.getChildValue("name"));
        Element m = e.getNamedChild("meta");
        if (m != null) {
          List<Element> profiles = m.getChildrenByName("profile");
          for (Element p : profiles)
            r.getProfiles().add(p.getValue());
        }
        igpkp.findConfiguration(file, r);
        String ver = r.getConfig() == null ? null : ostr(r.getConfig(), "version");
        if (ver == null)
          ver = version; // fall back to global version
        if ("1.0.1".equals(ver)) {
          file.getErrors().clear();
          org.hl7.fhir.dstu2.model.Resource res2 = null;
          if (file.getContentType().contains("json"))
            res2 = new org.hl7.fhir.dstu2.formats.JsonParser().parse(file.getSource());
          else if (file.getContentType().contains("xml"))
            res2 = new org.hl7.fhir.dstu2.formats.XmlParser().parse(file.getSource());
          org.hl7.fhir.dstu3.model.Resource res = new VersionConvertor_10_20(null).convertResource(res2);
          e = convertToElement(res);
          r.setElement(e).setId(id).setTitle(e.getChildValue("name"));
          r.setResource(res); 
        }
      } catch ( Exception ex ) {
        throw new Exception("Unable to determine type for  "+file.getName()+": " +ex.getMessage(), ex);
      }
    }
  }

  private Element loadFromXml(FetchedFile file) throws Exception {
    org.hl7.fhir.dstu3.elementmodel.XmlParser xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(context);
    xp.setAllowXsiLocation(true);
    xp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors());
    Element res = xp.parse(new ByteArrayInputStream(file.getSource()));
    if (res == null)
      throw new Exception("Unable to parse XML for "+file.getName());
    return res;
  }

  private Element loadFromJson(FetchedFile file) throws Exception {
    org.hl7.fhir.dstu3.elementmodel.JsonParser jp = new org.hl7.fhir.dstu3.elementmodel.JsonParser(context);
    jp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors());
    return jp.parse(new ByteArrayInputStream(file.getSource()));
  }

  private void load(String type) throws Exception {
    dlog("process type: "+type);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(type)) {
          dlog("process res: "+r.getId());
          if (!r.isValidated())
            validate(f, r);
          if (r.getResource() == null)
            try {
              r.setResource(parse(f)); // we won't get to here if we're a bundle
            } catch (Exception e) {
              throw new Exception("Error parsing "+f.getName()+": "+e.getMessage(), e);
            }
          MetadataResource bc = (MetadataResource) r.getResource();
          boolean altered = false;
          if (!bc.hasDate()) {
            altered = true;
            bc.setDateElement(new DateTimeType(execTime));
          }
          if (!bc.hasStatus()) {
            altered = true;
            bc.setStatus(PublicationStatus.DRAFT);
          }
          if (altered)
            r.setElement(convertToElement(bc));
          igpkp.checkForPath(f, r, bc);
          try {
//            if (!(bc instanceof StructureDefinition))
              context.seeResource(bc.getUrl(), bc);
          } catch (Exception e) {
            throw new Exception("Exception loading "+bc.getUrl()+": "+e.getMessage(), e);
          }
        }
      }
    }
  }

  private void dlog(String s) {
    logger.logDebugMessage(Utilities.padRight(s, ' ', 80)+" ("+presentDuration(System.nanoTime()-globalStart)+"sec)");
  }

  private void generateAdditionalExamples() throws Exception {
    if ("true".equals(ostr(configuration, "gen-examples"))) {
      ProfileUtilities utils = new ProfileUtilities(context, null, null);
      for (FetchedFile f : changeList) {
        List<StructureDefinition> list = new ArrayList<StructureDefinition>();
        for (FetchedResource r : f.getResources()) {
          if (r.getResource() instanceof StructureDefinition) {
            list.add((StructureDefinition) r.getResource());
          }
        }
        for (StructureDefinition sd : list) {
          for (Element e : utils.generateExamples(sd, false)) {
            FetchedResource nr = new FetchedResource();
            nr.setElement(e);
            nr.setId(e.getChildValue("id"));
            nr.setTitle("Generated Example");
            nr.getProfiles().add(sd.getUrl());
            f.getResources().add(nr);
            igpkp.findConfiguration(f, nr);
          }
        }
      }
    }
  }

  private void generateSnapshots() throws Exception {
    dlog("Generate Snapshots");
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() instanceof StructureDefinition && !r.isSnapshotted()) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          generateSnapshot(f, r, sd, false);
        }
      }
    }

    for (StructureDefinition derived : context.allStructures()) {
      if (!derived.hasSnapshot() && derived.hasBaseDefinition()) {
        throw new Exception("No snapshot found on "+derived.getUrl());
      }
    }
  }

  private void generateSnapshot(FetchedFile f, FetchedResource r, StructureDefinition sd, boolean close) throws Exception {
    boolean changed = false;
    dlog("Check Snapshot for "+sd.getUrl());
    ProfileUtilities utils = new ProfileUtilities(context, f.getErrors(), igpkp);
    StructureDefinition base = sd.hasBaseDefinition() ? fetchSnapshotted(sd.getBaseDefinition()) : null;
    utils.setIds(sd, true);
    if (sd.getKind() != StructureDefinitionKind.LOGICAL) {
      if (!sd.hasSnapshot()) {
        dlog("Generate Snapshot for "+sd.getUrl());
        if (base == null)
          throw new Exception("base is null ("+sd.getBaseDefinition()+" from "+sd.getUrl()+")");
        List<String> errors = new ArrayList<String>();
        if (close)
          utils.closeDifferential(base, sd);
        else
          utils.sortDifferential(base, sd, "profile "+sd.getUrl(), errors);
        for (String s : errors)
          f.getErrors().add(new ValidationMessage(Source.ProfileValidator, IssueType.INVALID, sd.getUrl(), s, IssueSeverity.ERROR));
        utils.setIds(sd, true);

        String p = sd.getDifferential().getElement().get(0).getPath();
        if (p.contains(".")) {
          changed = true;
          sd.getDifferential().getElement().add(0, new ElementDefinition().setPath(p.substring(0, p.indexOf("."))));
        }
        utils.generateSnapshot(base, sd, sd.getUrl(), sd.getName());
        changed = true;
      }
    } else { //sd.getKind() == StructureDefinitionKind.LOGICAL
      dlog("Generate Snapshot for Logical Model "+sd.getUrl());
      if (!sd.hasSnapshot()) {
        utils.populateLogicalSnapshot(sd);
        changed = true;
      }
    }
    if (changed || (!r.getElement().hasChild("snapshot") && sd.hasSnapshot()))
      r.setElement(convertToElement(sd));
    r.setSnapshotted(true);
    dlog("Context.See "+sd.getUrl());
    context.seeResource(sd.getUrl(), sd);
  }

  private void validateExpressions() {
    dlog("validate Expressions");
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() instanceof StructureDefinition && !r.isSnapshotted()) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          validateExpressions(f, sd);
        }
      }
    }
  }
  
  private void validateExpressions(FetchedFile f, StructureDefinition sd) {
    FHIRPathEngine fpe = new FHIRPathEngine(context);
    for (ElementDefinition ed : sd.getSnapshot().getElement()) {
      for (ElementDefinitionConstraintComponent inv : ed.getConstraint()) {
        validateExpression(f, sd, fpe, ed, inv);
      }
    }
  }

  private void validateExpression(FetchedFile f, StructureDefinition sd, FHIRPathEngine fpe, ElementDefinition ed, ElementDefinitionConstraintComponent inv) {
    if (inv.hasExpression()) {
      try {
        ExpressionNode n = (ExpressionNode) inv.getUserData("validator.expression.cache");
        if (n == null) {
          n = fpe.parse(inv.getExpression());
          inv.setUserData("validator.expression.cache", n);
        }
        fpe.check(null, sd, ed.getPath(), n);
      } catch (Exception e) {
        f.getErrors().add(new ValidationMessage(Source.ProfileValidator, IssueType.INVALID, sd.getUrl()+":"+ed.getPath()+":"+inv.getKey(), e.getMessage(), IssueSeverity.ERROR));
      }
    }
  }

  private StructureDefinition fetchSnapshotted(String url) throws Exception {
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() instanceof StructureDefinition) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getUrl().equals(url)) {
            if (!r.isSnapshotted())
              generateSnapshot(f, r, sd, false);
            return sd;
          }
        }
      }
    }
    return context.fetchResource(StructureDefinition.class, url);
  }

  private void generateLogicalMaps() throws Exception {
    StructureMapUtilities mu = new StructureMapUtilities(context, null, null);
    for (FetchedFile f : fileList) {
      List<StructureMap> maps = new ArrayList<StructureMap>();
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() instanceof StructureDefinition) {
          StructureMap map = mu.generateMapFromMappings((StructureDefinition) r.getResource());
          if (map != null) {
            maps.add(map);
          }
        }
      }
      for (StructureMap map : maps) {
        FetchedResource nr = f.addResource();
        nr.setResource(map);
        nr.setElement(convertToElement(map));
        nr.setId(map.getId());
        nr.setTitle(map.getName());
        igpkp.findConfiguration(f, nr);
      }
    }
  }

  private Resource parse(FetchedFile file) throws Exception {
    if (version.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.model.Resource res;
      if (file.getContentType().contains("json"))
        res = new org.hl7.fhir.dstu2016may.formats.JsonParser().parse(file.getSource());
      else if (file.getContentType().contains("xml"))
        res = new org.hl7.fhir.dstu2016may.formats.XmlParser().parse(file.getSource());
      else
        throw new Exception("Unable to determine file type for "+file.getName());
      return VersionConvertor_14_20.convertResource(res);
    } else if (version.equals("1.0.2")) {
      org.hl7.fhir.dstu2.model.Resource res;
      if (file.getContentType().contains("json"))
        res = new org.hl7.fhir.dstu2.formats.JsonParser().parse(file.getSource());
      else if (file.getContentType().contains("xml"))
        res = new org.hl7.fhir.dstu2.formats.XmlParser().parse(file.getSource());
      else
        throw new Exception("Unable to determine file type for "+file.getName());
      
      VersionConvertorAdvisor advisor = new IGR2ConvertorAdvisor();
      return new VersionConvertor_10_20(advisor ).convertResource(res);
    } else if (version.equals(Constants.VERSION)) {
      if (file.getContentType().contains("json"))
        return new JsonParser().parse(file.getSource());
      else if (file.getContentType().contains("xml"))
        return new XmlParser().parse(file.getSource());
      else
        throw new Exception("Unable to determine file type for "+file.getName());
    } else
      throw new Exception("Unsupported version "+version);
  }

  private void validate() throws Exception {
    for (FetchedFile f : fileList) {
      dlog(" .. validate "+f.getName());
      if (first)
        dlog(" .. "+f.getName());
      for (FetchedResource r : f.getResources()) {
        if (!r.isValidated()) {
          dlog("     validating "+r.getTitle());
          validate(f, r);
        }
      }
    }
  }

  private void validate(FetchedFile file, FetchedResource r) throws Exception {
    assert resources.isEmpty();
    List<ValidationMessage> errs = new ArrayList<ValidationMessage>();
    if (r.isValidateByUserData()) {
      Resource res = r.getResource();
      if (res instanceof Bundle) {
        validator.validate(null, errs, r.getElement());
        
        for (BundleEntryComponent be : ((Bundle) res).getEntry()) {
          Resource ber = be.getResource();
          if (ber.hasUserData("profile"))
            validator.validate(r.getElement(), errs, ber, ber.getUserString("profile"));
        }
        
      } else if (res.hasUserData("profile")) {
        validator.validate(null, errs, res, res.getUserString("profile"));
      }
    } else
      validator.validate(null, errs, r.getElement());
    for (ValidationMessage vm : errs) {
      file.getErrors().add(vm.setLocation(r.getElement().fhirType()+"/"+r.getId()+": "+vm.getLocation()));
    }
    r.setValidated(true);
    if (r.getConfig() == null)
      igpkp.findConfiguration(file, r);
  }
  
  private void generate() throws Exception {
    forceDir(tempDir);
    forceDir(Utilities.path(tempDir, "_includes"));
    forceDir(Utilities.path(tempDir, "data"));

    otherFilesRun.clear();
    for (String rg : regenList)
      regenerate(rg);

    updateImplementationGuide();

    for (FetchedFile f : changeList)
      generateOutputs(f, false);

    if (!changeList.isEmpty())
      generateSummaryOutputs();

    cleanOutput(tempDir);

    if (runTool())
      if (!changeList.isEmpty())
        generateZips();

    log("Checking Output HTML");
    List<ValidationMessage> linkmsgs = inspector.check();
    int bl = 0;
    int lf = 0;
    for (ValidationMessage m : linkmsgs) {
      if (m.getLevel() == IssueSeverity.ERROR) {
        if (m.getType() == IssueType.NOTFOUND)
          bl++;
        else
          lf++;
      } else if (m.getLevel() == IssueSeverity.FATAL) {
        throw new Exception(m.getMessage());
      }
    }
    log("  ... "+Integer.toString(inspector.total())+" html "+checkPlural("file", inspector.total())+", "+Integer.toString(lf)+" "+checkPlural("page", lf)+" invalid xhtml ("+Integer.toString((lf*100)/(inspector.total() == 0 ? 1 : inspector.total()))+"%)");
    log("  ... "+Integer.toString(inspector.links())+" "+checkPlural("link", inspector.links())+", "+Integer.toString(bl)+" broken "+checkPlural("link", lf)+" ("+Integer.toString((bl*100)/(inspector.links() == 0 ? 1 : inspector.links()))+"%)");
    errors.addAll(linkmsgs);
    log("Build final .zip");
    ZipGenerator zip = new ZipGenerator(Utilities.path(tempDir, "full-ig.zip"));
    zip.addFolder(outputDir, "site/", false);
    zip.addFileSource("index.html", REDIRECT_SOURCE, false);
    zip.close();
    Utilities.copyFile(Utilities.path(tempDir, "full-ig.zip"), Utilities.path(outputDir, "full-ig.zip"));
  }

  private void updateImplementationGuide() throws Exception {
    for (ImplementationGuidePackageComponent pck : pubIg.getPackage()) {
      for (ImplementationGuidePackageResourceComponent res : pck.getResource()) {
        FetchedResource r = null;
        for (FetchedFile tf : fileList) {
          for (FetchedResource tr : tf.getResources()) {
            if (tr.getLocalRef().equals(res.getSourceReference().getReference())) {
              r = tr;
            }
          }
        }
        if (r != null) {
          String path = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
          res.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/implementationguide-page").setValue(new UriType(path));
          inspector.addLinkToCheck("Implementation Guide", path);
        }
      }
    }

    FetchedResource r = altMap.get(IG_NAME).getResources().get(0);
    r.setResource(pubIg);
    r.setElement(convertToElement(pubIg));
  }

  private String checkPlural(String word, int c) {
    return c == 1 ? word : Utilities.pluralizeMe(word);
  }

  private void regenerate(String uri) throws Exception {
    Resource res ;
    if (uri.contains("/StructureDefinition/"))
      res = context.fetchResource(StructureDefinition.class, uri);
    else
      throw new Exception("Unable to process "+uri);

    if (res == null)
      throw new Exception("Unable to find regeneration source for "+uri);

    MetadataResource bc = (MetadataResource) res;

    FetchedFile f = new FetchedFile();
    FetchedResource r = f.addResource();
    r.setResource(res);
    r.setId(bc.getId());
    r.setTitle(bc.getName());
    r.setValidated(true);
    r.setElement(convertToElement(bc));
    igpkp.findConfiguration(f, r);
    bc.setUserData("config", r.getConfig());
    generateOutputs(f, true);
  }

  private Element convertToElement(Resource res) throws IOException, org.hl7.fhir.exceptions.FHIRException, FHIRFormatError, DefinitionException {
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    if (version.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.formats.JsonParser jp = new org.hl7.fhir.dstu2016may.formats.JsonParser();
      jp.compose(bs, VersionConvertor_14_20.convertResource(res));
    } else if (version.equals("1.0.2")) {
        org.hl7.fhir.dstu2.formats.JsonParser jp = new org.hl7.fhir.dstu2.formats.JsonParser();
        jp.compose(bs, new VersionConvertor_10_20(new IGR2ConvertorAdvisor()).convertResource(res));
    } else { // if (version.equals(Constants.VERSION)) {
      org.hl7.fhir.dstu3.formats.JsonParser jp = new org.hl7.fhir.dstu3.formats.JsonParser();
      jp.compose(bs, res);
    }  
    ByteArrayInputStream bi = new ByteArrayInputStream(bs.toByteArray());
    return new org.hl7.fhir.dstu3.elementmodel.JsonParser(context).parse(bi);
  }

  private void cleanOutput(String folder) throws IOException {
    for (File f : new File(folder).listFiles()) {
// Lloyd this was changed from getPath
      if (!isValidFile(f.getAbsolutePath())) {
        if (!f.isDirectory()) {
          f.delete();
        }
      }
    }
  }

  private boolean isValidFile(String p) {
    if (otherFilesStartup.contains(p))
      return true;
    if (otherFilesRun.contains(p))
      return true;
    for (FetchedFile f : fileList)
      if (f.getOutputNames().contains(p))
        return true;
    for (FetchedFile f : altMap.values())
      if (f.getOutputNames().contains(p))
        return true;
    return false;
  }

  private void generateZips() throws Exception {
    SpecMapManager map = new SpecMapManager(Constants.VERSION, Constants.REVISION, execTime, igpkp.getCanonical());
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        String u = igpkp.getCanonical()+r.getUrlTail();
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          String uc = ((MetadataResource) r.getResource()).getUrl();
          if (uc != null && !u.equals(uc) && !u.startsWith("http://hl7.org/fhir/template-adhoc-ig") && !(r.getResource() instanceof CodeSystem) && !(r.getResource() instanceof ImplementationGuide))
            throw new Exception("URL Mismatch "+u+" vs "+uc);
        }
        map.path(u, igpkp.getLinkFor(r));
      }
    }
    for (String s : new File(outputDir).list()) {
      if (s.endsWith(".html")) {
        map.target(s);
      }
    }
    File df = File.createTempFile("fhir", "tmp");
    df.deleteOnExit();
    map.save(df.getAbsolutePath());
    if (generateExampleZip(FhirFormat.XML))
      generateDefinitions(FhirFormat.XML, df.getAbsolutePath());
    if (generateExampleZip(FhirFormat.JSON))
      generateDefinitions(FhirFormat.JSON, df.getAbsolutePath());
    if (generateExampleZip(FhirFormat.TURTLE))
      generateDefinitions(FhirFormat.TURTLE, df.getAbsolutePath());
    generateValidationPack();
  }

  private void generateDefinitions(FhirFormat fmt, String specFile)  throws Exception {
    // public definitions
    Set<FetchedResource> files = new HashSet<FetchedResource>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          files.add(r);
        }
      }
    }
    if (!files.isEmpty()) {
      ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "definitions."+fmt.getExtension()+".zip"));
      for (FetchedResource r : files) {
        ByteArrayOutputStream bs = new ByteArrayOutputStream();
        if (fmt.equals(FhirFormat.JSON))
          new JsonParser().compose(bs, r.getResource());
        else if (fmt.equals(FhirFormat.XML))
          new XmlParser().compose(bs, r.getResource());
        else if (fmt.equals(FhirFormat.TURTLE))
          new TurtleParser(context).compose(r.getElement(), bs, OutputStyle.PRETTY, igpkp.getCanonical());
        zip.addBytes(r.getElement().fhirType()+"-"+r.getId()+"."+fmt.getExtension(), bs.toByteArray(), false);
      }
      zip.addFileName("spec.internals", specFile, false);
      zip.close();
    }
  }

  private void generateValidationPack()  throws Exception {
    String sch = makeTempZip(".sch");
    String js = makeTempZip(".schema.json");
    String shex = makeTempZip(".shex");
    
    ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "validator.pack"));
    zip.addBytes("version.info", context.getBinaries().get("version.info"), false);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          ByteArrayOutputStream bs = new ByteArrayOutputStream();
          new JsonParser().compose(bs, r.getResource());
          zip.addBytes(r.getElement().fhirType()+"-"+r.getId()+".json", bs.toByteArray(), false);
        }
      }
    }
    if (sch != null)
      zip.addFileName("schematron.zip", sch, false);
    if (js != null)
      zip.addFileName("json.schema.zip", sch, false);
    if (shex != null)
      zip.addFileName("shex.zip", sch, false);
    zip.close();
  }

  private String makeTempZip(String ext) throws IOException {
    Set<String> files = new HashSet<String>();
    for (String s : new File(outputDir).list()) {
      if (s.endsWith(ext))
        files.add(s);
    }
    if (files.size() == 0)
      return null;
    File tmp = File.createTempFile("fhir", "zip");
    tmp.deleteOnExit();
    ZipGenerator zip = new ZipGenerator(tmp.getAbsolutePath());
    for (String fn : files)
      zip.addFileName(fn, Utilities.path(outputDir, fn), false);
    zip.close();
    return tmp.getAbsolutePath();
  }

  private boolean generateExampleZip(FhirFormat fmt) throws Exception {
    Set<String> files = new HashSet<String>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        String fn = Utilities.path(outputDir, r.getElement().fhirType()+"-"+r.getId()+"."+fmt.getExtension());
        if (new File(fn).exists())
          files.add(fn);
      }
    }
    if (!files.isEmpty()) {
      ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "examples."+fmt.getExtension()+".zip"));
      for (String fn : files)
        zip.addFileName(fn.substring(fn.lastIndexOf(File.separator)+1), fn, false);
      zip.close();

    }
    return !files.isEmpty();
  }

  private boolean runTool() throws Exception {
    switch (tool) {
    case Jekyll: return runJekyll();
    default:
      throw new Exception("unimplemented tool");
    }
  }

  public class MyFilterHandler extends OutputStream {

    private byte[] buffer;
    private int length;

    public MyFilterHandler() {
      buffer = new byte[256];
    }
    
    public String getBufferString() {
    	return new String(this.buffer);
    }

    private boolean passJekyllFilter(String s) {
      if (Utilities.noString(s))
        return false;
      if (s.contains("Source:"))
        return true;
      if (s.contains("Liquid Exception:"))
    	  return true;
      if (s.contains("Destination:"))
        return false;
      if (s.contains("Configuration"))
        return false;
      if (s.contains("Incremental build:"))
        return false;
      if (s.contains("Auto-regeneration:"))
        return false;
      return true;
    }

    @Override
    public void write(int b) throws IOException {
      buffer[length] = (byte) b;
      length++;
      if (b == 10) { // eoln
        String s = new String(buffer, 0, length);
        if (passJekyllFilter(s))
          log("Jekyll: "+s.trim());
        length = 0;
      }
    }
  }

/*  private boolean passJekyllFilter(String s) {
    if (Utilities.noString(s))
      return false;
    if (s.contains("Source:"))
      return false;
    if (s.contains("Destination:"))
      return false;
    if (s.contains("Configuration"))
      return false;
    if (s.contains("Incremental build:"))
      return false;
    if (s.contains("Auto-regeneration:"))
      return false;
    return true;
  }*/

  private boolean runJekyll() throws IOException, InterruptedException {
    DefaultExecutor exec = new DefaultExecutor();
    exec.setExitValue(0);
    MyFilterHandler pumpHandler = new MyFilterHandler();
    PumpStreamHandler pump = new PumpStreamHandler(pumpHandler);
    exec.setStreamHandler(pump);
    exec.setWorkingDirectory(new File(tempDir));
    
    try {    
	    if (SystemUtils.IS_OS_WINDOWS)
	      exec.execute(org.apache.commons.exec.CommandLine.parse("cmd /C jekyll build --destination "+outputDir));
	    else
	      exec.execute(org.apache.commons.exec.CommandLine.parse("jekyll build --destination "+outputDir));
    } catch (IOException ioex) {
    	log("Complete output from Jekyll: " + pumpHandler.getBufferString());
    	throw ioex;
    }
    
    return true;
  }

  private void generateSummaryOutputs() throws Exception {
    log("Generating Summary Outputs");
    generateResourceReferences();

    generateDataFile();

    // now, list the profiles - all the profiles
    JsonObject data = new JsonObject();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();

          JsonObject item = new JsonObject();
          data.add(sd.getId(), item);
          item.addProperty("url", sd.getUrl());
          item.addProperty("name", sd.getName());
          item.addProperty("path", sd.getUserString("path"));
          item.addProperty("kind", sd.getKind().toCode());
          item.addProperty("type", sd.getType());
          item.addProperty("base", sd.getBaseDefinition());
          StructureDefinition base = sd.hasBaseDefinition() ? context.fetchResource(StructureDefinition.class, sd.getBaseDefinition()) : null;
          if (base != null) {
            item.addProperty("basename", base.getName());
            item.addProperty("basepath", base.getUserString("path"));
          }
          item.addProperty("status", sd.getStatus().toCode());
          item.addProperty("date", sd.getDate().toString());
          item.addProperty("publisher", sd.getPublisher());
          item.addProperty("copyright", sd.getCopyright());
          item.addProperty("description", sd.getDescription());
          if (sd.getContextType() != null)
            item.addProperty("contextType", sd.getContextType().getDisplay());
          if (!sd.getContext().isEmpty()) {
            JsonArray contexts = new JsonArray();
            item.add("contexts", contexts);
            for (StringType context : sd.getContext()) {
              contexts.add(new JsonPrimitive(context.asStringValue()));
            }
          }
        }
      }
    }

    for (FetchedResource r: examples) {
      FetchedResource baseRes = getResourceForUri(r.getExampleUri());
      if (baseRes == null)
        throw new Exception ("Unable to find exampleFor resource " + r.getExampleUri() + " for resource " + r.getUrlTail());
      baseRes.addExample(r);
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(data);
    TextFile.stringToFile(json, Utilities.path(tempDir, "_data", "structuredefinitions.json"));

    if (sourceIg.hasPage()) {
      JsonObject pages = new JsonObject();
      addPageDataRow(pages, "toc.html", "Table of Contents", "#", breadCrumbForPage(sourceIg.getPage(), true), examples);
      addPageData(pages, sourceIg.getPage(), "0", "", true);
      //   gson = new GsonBuilder().setPrettyPrinting().create();
      //   json = gson.toJson(pages);
      json = pages.toString();
      TextFile.stringToFile(json, Utilities.path(tempDir, "_data", "pages.json"));
    }
  }

  private String breadCrumbForPage(ImplementationGuidePageComponent page, boolean withLink) {
    if (withLink)
      return "<li><a href='" + page.getSource() + "'><b>" + Utilities.escapeXml(page.getTitle()) + "</b></a></li>";
    else
      return "<li><b>" + Utilities.escapeXml(page.getTitle()) + "</b></li>";
          }

  private void addPageData(JsonObject pages, ImplementationGuidePageComponent page, String label, String breadcrumb, boolean includeExamples) {
    addPageData(pages, page, page.getSource(), page.getTitle(), label, breadcrumb, includeExamples);
        }
  
  private void addPageData(JsonObject pages, ImplementationGuidePageComponent page, String source, String title, String label, String breadcrumb, boolean includeExamples) {
    String sourceBase = Utilities.changeFileExt(page.getSource(), "");
    addPageDataRow(pages, source, title, label, breadcrumb + breadCrumbForPage(page, false), null);
    addPageDataRow(pages, sourceBase + ".xml.html", page.getTitle() + " - XML Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
    addPageDataRow(pages, sourceBase + ".json.html", page.getTitle() + " - JSON Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
    addPageDataRow(pages, sourceBase + ".ttl.html", page.getTitle() + " - TTL Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
    
    if (page.getKind().equals(ImplementationGuide.GuidePageKind.RESOURCE) && page.getFormat().equals("generated")) {
      addPageDataRow(pages, sourceBase + "-definitions.html", page.getTitle() + " - Definitions", label, breadcrumb + breadCrumbForPage(page, false), null);
      addPageDataRow(pages, sourceBase + "-mappings.html", page.getTitle() + " - Mappings", label, breadcrumb + breadCrumbForPage(page, false), null);
      if (includeExamples) {
        FetchedResource r = resources.get(source);
        if (r != null)
          addPageDataRow(pages, sourceBase + "-examples.html", page.getTitle() + " - Examples", label, breadcrumb + breadCrumbForPage(page, false), r.getExamples());
      } else
        addPageDataRow(pages, sourceBase + "-examples.html", page.getTitle() + " - Examples", label, breadcrumb + breadCrumbForPage(page, false), null);
      addPageDataRow(pages, sourceBase + ".profile.xml.html", page.getTitle() + " - Profile XML", label, breadcrumb + breadCrumbForPage(page, false), null);
      addPageDataRow(pages, sourceBase + ".profile.json.html", page.getTitle() + " - Profile JSON", label, breadcrumb + breadCrumbForPage(page, false), null);
      addPageDataRow(pages, sourceBase + ".profile.ttl.html", page.getTitle() + " - Profile TTL", label, breadcrumb + breadCrumbForPage(page, false), null);
    }
    int i = 1;
    for (ImplementationGuidePageComponent childPage : page.getPage()) {
      addPageData(pages, childPage, (label.equals("0") ? "" : label+".") + Integer.toString(i), breadcrumb + breadCrumbForPage(page, true), includeExamples);
      i++;
    }
  }

  private void addPageDataRow(JsonObject pages, String url, String title, String label, String breadcrumb, Set<FetchedResource> examples) {
    JsonObject jsonPage = new JsonObject();
    pages.add(url, jsonPage);
    jsonPage.addProperty("title", title);
    jsonPage.addProperty("label", label);
    jsonPage.addProperty("breadcrumb", breadcrumb);
    
    String baseUrl = url;
    
    if (baseUrl.indexOf(".html") > 0) {
    	baseUrl = baseUrl.substring(0, baseUrl.indexOf(".html"));
    }
    
    String contentFile = pagesDir + File.separator + "_includes" + File.separator + "content-" + baseUrl + "-intro.html";
    if (new File(contentFile).exists()) {
      jsonPage.addProperty("intro", "content-" + baseUrl+"-intro.html");
    }

    contentFile = pagesDir + File.separator + "_includes" + File.separator + "content-" + baseUrl + "-notes.html";
    if (new File(contentFile).exists()) {
      jsonPage.addProperty("notes", "content-" + baseUrl+"-notes.html");
    }

    if (examples != null) {
      JsonArray exampleArray = new JsonArray();
      jsonPage.add("examples", exampleArray);
      
      TreeSet<ImplementationGuidePageComponent> examplePages = new TreeSet<ImplementationGuidePageComponent>(new ImplementationGuidePageComponentComparator());
      for (FetchedResource exampleResource: examples) {
        ImplementationGuidePageComponent page = pageForFetchedResource(exampleResource);
        if (page!=null)
          examplePages.add(page);
        // else
        //   throw new Error("Unable to find page for resource "+ exampleResource.getId());
      }
      for (ImplementationGuidePageComponent examplePage : examplePages) {
        JsonObject exampleItem = new JsonObject();
        exampleArray.add(exampleItem);
        exampleItem.addProperty("url", examplePage.getSource());
        exampleItem.addProperty("title", examplePage.getTitle());
      }
    }
  }
  
  private ImplementationGuidePageComponent pageForFetchedResource(FetchedResource r) {
    String key = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
    return igPages.get(key);
  }

  private class ImplementationGuidePageComponentComparator implements Comparator<ImplementationGuidePageComponent> {
    @Override
    public int compare(ImplementationGuidePageComponent x, ImplementationGuidePageComponent y) {
      return x.getSource().compareTo(y.getSource());
    }      
  }

  private void generateDataFile() throws IOException {
    JsonObject data = new JsonObject();
    data.addProperty("path", specPath);
    data.addProperty("canonical", igpkp.getCanonical());
    data.addProperty("igId", sourceIg.getId());
    data.addProperty("igName", sourceIg.getName());
    data.addProperty("errorCount", getErrorCount());
    data.addProperty("version", Constants.VERSION);
    data.addProperty("revision", Constants.REVISION);
    data.addProperty("versionFull", Constants.VERSION+"-"+Constants.REVISION);
    data.addProperty("totalFiles", fileList.size());
    data.addProperty("processedFiles", changeList.size());
    data.addProperty("genDate", genTime());
    JsonObject ig = new JsonObject();
    data.add("ig", ig);
    ig.addProperty("id", sourceIg.getId());
    ig.addProperty("name", sourceIg.getName());
    ig.addProperty("url", sourceIg.getUrl());
    ig.addProperty("version", sourceIg.getVersion());
    ig.addProperty("status", sourceIg.getStatusElement().asStringValue());
    ig.addProperty("experimental", sourceIg.getExperimental());
    ig.addProperty("publisher", sourceIg.getPublisher());
    if (sourceIg.hasContact()) {
      JsonArray jc = new JsonArray();
      ig.add("contact", jc);
      for (ContactDetail c : sourceIg.getContact()) {
        JsonObject jco = new JsonObject();
        jc.add(jco);
        jco.addProperty("name", c.getName());
        if (c.hasTelecom()) {
          JsonArray jct = new JsonArray();
          jco.add("telecom", jct);
          for (ContactPoint cc : c.getTelecom()) {
            jct.add(new JsonPrimitive(cc.getValue()));
          }
        }
      }
    }
    ig.addProperty("date", sourceIg.getDateElement().asStringValue());
    ig.addProperty("description", sourceIg.getDescription());
    ig.addProperty("copyright", sourceIg.getCopyright());
    ig.addProperty("fhirVersion", sourceIg.getFhirVersion());

    for (SpecMapManager sm : specMaps) {
      if (sm.getName() != null)
        data.addProperty(sm.getName(), sm.getBase());
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(data);
    TextFile.stringToFile(json, Utilities.path(tempDir, "_data", "fhir.json"));
  }

  private void generateResourceReferences() throws Exception {
    for (ResourceType rt : ResourceType.values()) {
      generateResourceReferences(rt);
    }
    generateProfiles();
    generateExtensions();
    generateLogicals();
  }

  private void generateProfiles() throws Exception {
    StringBuilder list = new StringBuilder();
    StringBuilder table = new StringBuilder();
    boolean found = false;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && sd.getKind() == StructureDefinitionKind.RESOURCE) {
            found = true;
            String name = sd.getName();
            genEntryItem(list, table, f, r, name);
          }
        }
      }
    }
    if (found) {
      fragment("list-profiles", list.toString(), otherFilesRun);
      fragment("table-profiles", table.toString(), otherFilesRun);
    }
  }

  private void generateExtensions() throws Exception {
    StringBuilder list = new StringBuilder();
    StringBuilder table = new StringBuilder();
    boolean found = false;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && sd.getType().equals("Extension")) {
            found = true;
            String name = sd.getName();
            genEntryItem(list, table, f, r, name);
          }
        }
      }
    }
    if (found) {
      fragment("list-extensions", list.toString(), otherFilesRun);
      fragment("table-extensions", table.toString(), otherFilesRun);
    }
  }

  private void generateLogicals() throws Exception {
    StringBuilder list = new StringBuilder();
    StringBuilder table = new StringBuilder();
    boolean found = false;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getKind() == StructureDefinitionKind.LOGICAL) {
            found = true;
            String name = sd.getName();
            genEntryItem(list, table, f, r, name);
          }
        }
      }
    }
    if (found) {
      fragment("list-logicals", list.toString(), otherFilesRun);
      fragment("table-logicals", table.toString(), otherFilesRun);
    }
  }

  private void genEntryItem(StringBuilder list, StringBuilder table, FetchedFile f, FetchedResource r, String name) throws Exception {
    String ref = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
    String desc = r.getTitle();
    if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
      name = ((MetadataResource) r.getResource()).getName();
      desc = getDesc((MetadataResource) r.getResource(), desc);
    }
    list.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a> "+Utilities.escapeXml(desc)+"</li>\r\n");
    table.append(" <tr><td><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a> </td><td>"+new BaseRenderer(context, null, igpkp, specMaps).processMarkdown("description", desc)+"</td></tr>\r\n");
  }

  private void generateResourceReferences(ResourceType rt) throws Exception {
    StringBuilder list = new StringBuilder();
    StringBuilder table = new StringBuilder();
    boolean found = false;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(rt.toString())) {
          found = true;
          String name = r.getTitle();
          if (Utilities.noString(name))
            name = rt.toString();
          genEntryItem(list, table, f, r, name);
        }
      }
    }
    if (found) {
      fragment("list-"+Utilities.pluralizeMe(rt.toString().toLowerCase()), list.toString(), otherFilesRun);
      fragment("table-"+Utilities.pluralizeMe(rt.toString().toLowerCase()), table.toString(), otherFilesRun);
    }
  }

  private String getDesc(MetadataResource r, String desc) {
    if (r instanceof CodeSystem) {
      CodeSystem v = (CodeSystem) r;
      if (v.hasDescription())
        return v.getDescription();
    }
    if (r instanceof ValueSet) {
      ValueSet v = (ValueSet) r;
      if (v.hasDescription())
        return v.getDescription();
    }
    if (r instanceof StructureDefinition) {
      StructureDefinition v = (StructureDefinition) r;
      if (v.hasDescription())
        return v.getDescription();
    }
    return desc;
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
    if (first)
      logger.logMessage(Utilities.padRight(s, ' ', 80)+" ("+presentDuration(System.nanoTime()-globalStart)+"sec)");
    else
      logger.logMessage(s);
  }

  private void generateOutputs(FetchedFile f, boolean regen) throws TransformerException {
  //      log(" * "+f.getName());

    if (f.getProcessMode() == FetchedFile.PROCESS_NONE) {
      String dst = tempDir + f.getPath().substring(pagesDir.length());
      try {
        if (f.isFolder()) {
          f.getOutputNames().add(dst);
          Utilities.createDirectory(dst);
        } else
          checkMakeFile(f.getSource(), dst, f.getOutputNames());
      } catch (IOException e) {
        log("Exception generating page "+dst+": "+e.getMessage());
      }
    } else if (f.getProcessMode() == FetchedFile.PROCESS_XSLT) {
      String dst = tempDir + f.getPath().substring(prePagesDir.length());
      try {
        if (f.isFolder()) {
          f.getOutputNames().add(dst);
          Utilities.createDirectory(dst);
        } else
          checkMakeFile(transform(f.getSource()), dst, f.getOutputNames());
      } catch (IOException e) {
        log("Exception generating page "+dst+": "+e.getMessage());
      }
    } else {
      for (FetchedResource r : f.getResources()) {
        try {
          dlog("Produce outputs for "+r.getElement().fhirType()+"/"+r.getId());
          Map<String, String> vars = makeVars(r);
          saveDirectResourceOutputs(f, r, vars);

          // now, start generating resource type specific stuff
          if (r.getResource() != null) { // we only do this for conformance resources we've already loaded
            switch (r.getResource().getResourceType()) {
            case CodeSystem:
              generateOutputsCodeSystem(f, r, (CodeSystem) r.getResource(), vars);
              break;
            case ValueSet:
              generateOutputsValueSet(f, r, (ValueSet) r.getResource(), vars);
              break;
            case ConceptMap:
              generateOutputsConceptMap(f, r, (ConceptMap) r.getResource(), vars);
              break;

            case DataElement:
              break;
            case CapabilityStatement:
              generateOutputsCapabilityStatement(f, r, (CapabilityStatement) r.getResource(), vars);
              break;
            case StructureDefinition:
              generateOutputsStructureDefinition(f, r, (StructureDefinition) r.getResource(), vars, regen);
              break;
            case StructureMap:
              generateOutputsStructureMap(f, r, (StructureMap) r.getResource(), vars);
              break;
            default:
              // nothing to do...
            }
          }
        } catch (Exception e) {
          log("Exception generating resource "+f.getName()+"::"+r.getElement().fhirType()+"/"+r.getId()+": "+e.getMessage());
          e.printStackTrace();
          for (StackTraceElement m : e.getStackTrace()) {
              log("   "+m.toString());
          }
        }
      }
    }
  }


  private byte[] transform(byte[] source) throws TransformerException {
    TransformerFactory f = TransformerFactory.newInstance();
    StreamSource xsrc = new StreamSource(new ByteArrayInputStream(xslt));
    Transformer t = f.newTransformer(xsrc);

    StreamSource src = new StreamSource(new ByteArrayInputStream(source));
    ByteArrayOutputStream out = new ByteArrayOutputStream();
    StreamResult res = new StreamResult(out);
    t.transform(src, res);
    return out.toByteArray();
  }

  private Map<String, String> makeVars(FetchedResource r) {
    Map<String, String> map = new HashMap<String, String>();
    if (r.getResource() != null) {
      switch (r.getResource().getResourceType()) {
      case StructureDefinition:
        StructureDefinition sd = (StructureDefinition) r.getResource();
        String url = sd.getBaseDefinition();
        StructureDefinition base = context.fetchResource(StructureDefinition.class, url);
        if (base != null) {
          map.put("parent-name", base.getName());
          map.put("parent-link", base.getUserString("path"));
        } else {
          map.put("parent-name", "?? Unknown reference");
          map.put("parent-link", "??");
        }
        return map;
      default: return null;
      }
    } else
      return null;
  }

  /**
   * saves the resource as XML, JSON, Turtle,
   * then all 3 of those as html with embedded links to the definitions
   * then the narrative as html
   *
   * @param r
   * @throws FileNotFoundException
   * @throws Exception
   */
  private void saveDirectResourceOutputs(FetchedFile f, FetchedResource r, Map<String, String> vars) throws FileNotFoundException, Exception {
    genWrapper(f, r, igpkp.getProperty(r, "template-base"), igpkp.getProperty(r, "base"), f.getOutputNames(), vars, null, "");
    genWrapper(null, r, igpkp.getProperty(r, "template-defns"), igpkp.getProperty(r, "defns"), f.getOutputNames(), vars, null, "definitions");
    JsonArray templateNames = configuration.getAsJsonArray("extraTemplates");
    if (templateNames!=null)
      for (JsonElement name : templateNames) {
        if (!name.isJsonPrimitive())
          throw new Exception("extraTemplates must be an array of simple strings");
        String output = igpkp.getProperty(r, name.getAsString());
        if (output == null)
          output = r.getElement().fhirType()+"-"+r.getId()+"-"+name.getAsString()+".html";
        genWrapper(null, r, igpkp.getProperty(r, "template-"+name.getAsString()), output, f.getOutputNames(), vars, null, name.getAsString());
      }

    String template = igpkp.getProperty(r, "template-format");
    if (igpkp.wantGen(r, "xml")) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".xml");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.dstu3.elementmodel.XmlParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "xml", "");
    }
    if (igpkp.wantGen(r, "json")) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".json");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.dstu3.elementmodel.JsonParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "json", "");
    }
    if (igpkp.wantGen(r, "ttl")) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".ttl");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.dstu3.elementmodel.TurtleParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "ttl", "");
    }

    if (igpkp.wantGen(r, "xml-html")) {
      XmlXHtmlRenderer x = new XmlXHtmlRenderer();
      org.hl7.fhir.dstu3.elementmodel.XmlParser xp = new org.hl7.fhir.dstu3.elementmodel.XmlParser(context);
      xp.setLinkResolver(igpkp);
      xp.compose(r.getElement(), x);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-xml-html", x.toString(), f.getOutputNames(), r, vars, "xml");
    }
    if (igpkp.wantGen(r, "json-html")) {
      JsonXhtmlRenderer j = new JsonXhtmlRenderer();
      org.hl7.fhir.dstu3.elementmodel.JsonParser jp = new org.hl7.fhir.dstu3.elementmodel.JsonParser(context);
      jp.setLinkResolver(igpkp);
      jp.compose(r.getElement(), j);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-json-html", j.toString(), f.getOutputNames(), r, vars, "json");
    }

    if (igpkp.wantGen(r, "ttl-html")) {
      org.hl7.fhir.dstu3.elementmodel.TurtleParser ttl = new org.hl7.fhir.dstu3.elementmodel.TurtleParser(context);
      ttl.setLinkResolver(igpkp);
      Turtle rdf = new Turtle();
      ttl.compose(r.getElement(), rdf, "");
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-ttl-html", rdf.asHtml(), f.getOutputNames(), r, vars, "ttl");
    }

    if (igpkp.wantGen(r, "html")) {
      XhtmlNode xhtml = getXhtml(r);
      String html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-html", html, f.getOutputNames(), r, vars, null);
    }
    //  NarrativeGenerator gen = new NarrativeGenerator(null, null, context);
    //  gen.generate(f.getElement(), false);
    //  xhtml = getXhtml(f);
    //  html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
    //  fragment(f.getId()+"-gen-html", html);
  }

  private void genWrapper(FetchedFile ff, FetchedResource r, String template, String outputName, Set<String> outputTracker, Map<String, String> vars, String format, String extension) throws FileNotFoundException, IOException {
    if (template != null && !template.isEmpty()) {
      boolean existsAsPage = false;
      if (ff != null) {
        String fn = igpkp.getLinkFor(r);
        existsAsPage = altMap.containsKey("page/"+Utilities.path(pagesDir, fn));
        if (!existsAsPage && prePagesDir != null)
          existsAsPage = altMap.containsKey("page/"+Utilities.path(prePagesDir, fn));
      }
      if (!existsAsPage) {
        template = TextFile.fileToString(Utilities.path(Utilities.getDirectoryForFile(configFile), template));
        template = igpkp.doReplacements(template, r, vars, format);

        if (outputName == null)
          outputName = r.getElement().fhirType()+"-"+r.getId()+(extension.equals("")? "":"-"+extension)+(format==null? "": "."+format)+".html";
        if (outputName.contains("{{["))
          outputName = igpkp.doReplacements(outputName, r, vars, format);
        if (!outputName.contains("#")) {
          String path = Utilities.path(tempDir, outputName);
          checkMakeFile(template.getBytes(Charsets.UTF_8), path, outputTracker);
        }
      }
    }
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws org.hl7.fhir.exceptions.FHIRException
   * @throws Exception
   */
  private void generateOutputsCodeSystem(FetchedFile f, FetchedResource fr, CodeSystem cs, Map<String, String> vars) throws Exception {
    CodeSystemRenderer csr = new CodeSystemRenderer(context, specPath, cs, igpkp, specMaps);
    if (igpkp.wantGen(fr, "summary"))
      fragment("CodeSystem-"+cs.getId()+"-summary", csr.summary(igpkp.wantGen(fr, "xml"), igpkp.wantGen(fr, "json"), igpkp.wantGen(fr, "ttl")), f.getOutputNames(), fr, vars, null);
    if (igpkp.wantGen(fr, "content"))
      fragment("CodeSystem-"+cs.getId()+"-content", csr.content(), f.getOutputNames(), fr, vars, null);
    if (igpkp.wantGen(fr, "xref"))
      fragment("CodeSystem-"+cs.getId()+"-xref", csr.xref(), f.getOutputNames(), fr, vars, null);
  }

  /**
   * Genrate:
   *   summary
   *   Content logical definition
   *   cross-reference
   *
   * and save the expansion as html. todo: should we save it as a resource too? at this time, no: it's not safe to do that; encourages abuse
   * @param vs
   * @throws org.hl7.fhir.exceptions.FHIRException
   * @throws Exception
   */
  private void generateOutputsValueSet(FetchedFile f, FetchedResource r, ValueSet vs, Map<String, String> vars) throws Exception {
    ValueSetRenderer vsr = new ValueSetRenderer(context, specPath, vs, igpkp, specMaps);
    if (igpkp.wantGen(r, "summary"))
      fragment("ValueSet-"+vs.getId()+"-summary", vsr.summary(r, igpkp.wantGen(r, "xml"), igpkp.wantGen(r, "json"), igpkp.wantGen(r, "ttl")), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "cld"))
      try {
        fragment("ValueSet-"+vs.getId()+"-cld", vsr.cld(), f.getOutputNames(), r, vars, null);
      } catch (Exception e) {
        fragmentError(vs.getId()+"-cld", e.getMessage(), f.getOutputNames());
      }

    if (igpkp.wantGen(r, "xref"))
      fragment("ValueSet-"+vs.getId()+"-xref", vsr.xref(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "expansion")) {
      ValueSetExpansionOutcome exp = context.expandVS(vs, true, true);
      if (exp.getValueset() != null) {
        NarrativeGenerator gen = new NarrativeGenerator("", null, context);
        gen.setTooCostlyNoteNotEmpty("This value set has >1000 codes in it. In order to keep the publication size manageable, only a selection (1000 codes) of the whole set of codes is shown");
        gen.setTooCostlyNoteEmpty("This value set cannot be expanded because of the way it is defined - it has an infinite number of members");
        exp.getValueset().setCompose(null);
        exp.getValueset().setText(null);
        gen.generate(exp.getValueset(), false);
        String html = new XhtmlComposer().compose(exp.getValueset().getText().getDiv());
        fragment("ValueSet-"+vs.getId()+"-expansion", html, f.getOutputNames(), r, vars, null);
      } else if (exp.getError() != null)
        fragmentError("ValueSet-"+vs.getId()+"-expansion", exp.getError(), f.getOutputNames());
      else
        fragmentError("ValueSet-"+vs.getId()+"-expansion", "Unknown Error", f.getOutputNames());
    }
  }

  private void fragmentError(String name, String error, Set<String> outputTracker) throws IOException {
    fragment(name, "<p style=\"color: maroon; font-weight: bold\">"+Utilities.escapeXml(error)+"</p>\r\n", outputTracker);
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws IOException
   */
  private void generateOutputsConceptMap(FetchedFile f, FetchedResource r, ConceptMap cm, Map<String, String> vars) throws IOException {
    if (igpkp.wantGen(r, "summary"))
      fragmentError("ConceptMap-"+cm.getId()+"-summary", "yet to be done: concept map summary", f.getOutputNames());
    if (igpkp.wantGen(r, "content"))
      fragmentError("ConceptMap-"+cm.getId()+"-content", "yet to be done: table presentation of the concept map", f.getOutputNames());
    if (igpkp.wantGen(r, "xref"))
      fragmentError("ConceptMap-"+cm.getId()+"-xref", "yet to be done: list of all places where concept map is used", f.getOutputNames());
  }

  private void generateOutputsCapabilityStatement(FetchedFile f, FetchedResource r, CapabilityStatement cpbs, Map<String, String> vars) throws Exception {
    if (igpkp.wantGen(r, "swagger")) {
      String path = Utilities.path(tempDir, r.getId()+"-swagger.yaml");
      f.getOutputNames().add(path);
      SwaggerGenerator sg = new SwaggerGenerator(context, version);
      sg.generate(cpbs);
      sg.save(path);
    }
  }
  
  private void generateOutputsStructureDefinition(FetchedFile f, FetchedResource r, StructureDefinition sd, Map<String, String> vars, boolean regen) throws Exception {
    // todo : generate shex itself
    if (igpkp.wantGen(r, "shex"))
      fragmentError("StructureDefinition-"+sd.getId()+"-shex", "yet to be done: shex as html", f.getOutputNames());

    // todo : generate json schema itself. JSON Schema generator
//    if (igpkp.wantGen(r, ".schema.json")) {
//      String path = Utilities.path(tempDir, r.getId()+".sch");
//      f.getOutputNames().add(path);
//      new ProfileUtilities(context, errors, igpkp).generateSchematrons(new FileOutputStream(path), sd);
//    }
    if (igpkp.wantGen(r, "json-schema"))
      fragmentError("StructureDefinition-"+sd.getId()+"-json-schema", "yet to be done: json schema as html", f.getOutputNames());

    StructureDefinitionRenderer sdr = new StructureDefinitionRenderer(context, checkAppendSlash(specPath), sd, Utilities.path(tempDir), igpkp, specMaps);
    if (igpkp.wantGen(r, "summary"))
      fragment("StructureDefinition-"+sd.getId()+"-summary", sdr.summary(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "header"))
      fragment("StructureDefinition-"+sd.getId()+"-header", sdr.header(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "diff"))
      fragment("StructureDefinition-"+sd.getId()+"-diff", sdr.diff(igpkp.getDefinitionsName(r)), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "snapshot"))
      fragment("StructureDefinition-"+sd.getId()+"-snapshot", sdr.snapshot(igpkp.getDefinitionsName(r)), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "grid"))
      fragment("StructureDefinition-"+sd.getId()+"-grid", sdr.grid(igpkp.getDefinitionsName(r)), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "pseudo-xml"))
      fragmentError("StructureDefinition-"+sd.getId()+"-pseudo-xml", "yet to be done: Xml template", f.getOutputNames());
    if (igpkp.wantGen(r, "pseudo-json"))
      fragmentError("StructureDefinition-"+sd.getId()+"-pseudo-json", "yet to be done: Json template", f.getOutputNames());
    if (igpkp.wantGen(r, "pseudo-ttl"))
      fragmentError("StructureDefinition-"+sd.getId()+"-pseudo-ttl", "yet to be done: Turtle template", f.getOutputNames());
    if (igpkp.wantGen(r, "uml"))
      fragmentError("StructureDefinition-"+sd.getId()+"-uml", "yet to be done: UML as SVG", f.getOutputNames());
    if (igpkp.wantGen(r, "tx"))
      fragment("StructureDefinition-"+sd.getId()+"-tx", sdr.tx(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "inv"))
      fragment("StructureDefinition-"+sd.getId()+"-inv", sdr.inv(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "dict"))
      fragment("StructureDefinition-"+sd.getId()+"-dict", sdr.dict(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "maps"))
      fragment("StructureDefinition-"+sd.getId()+"-maps", sdr.mappings(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "xref"))
      fragmentError("StructureDefinition-"+sd.getId()+"-sd-xref", "Yet to be done: xref", f.getOutputNames());
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && igpkp.wantGen(r, "span"))
      fragment("StructureDefinition-"+sd.getId()+"-span", sdr.span(true, igpkp.getCanonical()), f.getOutputNames(), r, vars, null);
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && igpkp.wantGen(r, "spanall"))
      fragment("StructureDefinition-"+sd.getId()+"-spanall", sdr.span(true, igpkp.getCanonical()), f.getOutputNames(), r, vars, null);

    if (igpkp.wantGen(r, "example-list"))
      fragment("StructureDefinition-example-list-"+sd.getId(), sdr.exampleList(fileList), f.getOutputNames(), r, vars, null);

    if (igpkp.wantGen(r, "csv")) {
      String path = Utilities.path(tempDir, r.getId()+".csv");
      f.getOutputNames().add(path);
      new ProfileUtilities(context, errors, igpkp).generateCsvs(new FileOutputStream(path), sd, true);
    }

    if (!regen && sd.getKind() != StructureDefinitionKind.LOGICAL &&  igpkp.wantGen(r, ".sch")) {
      String path = Utilities.path(tempDir, r.getId()+".sch");
      f.getOutputNames().add(path);
      new ProfileUtilities(context, errors, igpkp).generateSchematrons(new FileOutputStream(path), sd);
    }
    if (igpkp.wantGen(r, "sch"))
      fragmentError("StructureDefinition-"+sd.getId()+"-sch", "yet to be done: schematron as html", f.getOutputNames());
  }

  private String checkAppendSlash(String s) {
    return s.endsWith("/") ? s : s+"/";
  }

  private void generateOutputsStructureMap(FetchedFile f, FetchedResource r, StructureMap map, Map<String,String> vars) throws Exception {
    StructureMapRenderer smr = new StructureMapRenderer(context, checkAppendSlash(specPath), map, Utilities.path(tempDir), igpkp, specMaps);
    if (igpkp.wantGen(r, "summary"))
      fragment("StructureMap-"+map.getId()+"-summary", smr.summary(r, igpkp.wantGen(r, "xml"), igpkp.wantGen(r, "json"), igpkp.wantGen(r, "ttl")), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "content"))
      fragment("StructureMap-"+map.getId()+"-content", smr.content(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "profiles"))
      fragment("StructureMap-"+map.getId()+"-profiles", smr.profiles(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "script"))
      fragment("StructureMap-"+map.getId()+"-script", smr.script(), f.getOutputNames(), r, vars, null);
// to generate:
    // map file
    // summary table 
    // profile index
    
  }

  private XhtmlNode getXhtml(FetchedResource r) {
    if (r.getResource() != null && r.getResource() instanceof DomainResource) {
      DomainResource dr = (DomainResource) r.getResource();
      if (dr.getText().hasDiv())
        return dr.getText().getDiv();
      else
        return null;
    }
    if (r.getResource() != null && r.getResource() instanceof Bundle) {
      Bundle b = (Bundle) r.getResource();
      if (b.hasEntry() && b.getEntryFirstRep().hasResource() && b.getEntryFirstRep().getResource() instanceof DomainResource) {
        DomainResource dr = (DomainResource) b.getEntryFirstRep().getResource();
        if ( dr.getText().hasDiv())
          return dr.getText().getDiv();
        else
          return null;
      }
    }
    Element text = r.getElement().getNamedChild("text");
    if (text == null)
      return null;
    Element div = text.getNamedChild("div");
    if (div == null)
      return null;
    else
      return div.getXhtml();
  }

  private void fragment(String name, String content, Set<String> outputTracker) throws IOException {
    fragment(name, content, outputTracker, null, null, null);
  }
  private void fragment(String name, String content, Set<String> outputTracker, FetchedResource r, Map<String, String> vars, String format) throws IOException {
    String fixedContent = (r==null? content : igpkp.doReplacements(content, r, vars, format));
    if (checkMakeFile(fixedContent.getBytes(Charsets.UTF_8), Utilities.path(tempDir, "_includes", name+".xhtml"), outputTracker)) {
      TextFile.stringToFile(pageWrap(fixedContent, name), Utilities.path(qaDir, name+".html"), true);
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

  public void setConfigFile(String configFile) {
    this.configFile = configFile;
  }
  
  public String getSourceDir() {
    return sourceDir;
  }

  public void setSourceDir(String sourceDir) {
    this.sourceDir = sourceDir;
  }

  public String getDestDir() {
    return destDir;
  }

  public void setDestDir(String destDir) {
    this.destDir = destDir;
  }

  public String getConfigFile() {
    return configFile;
  }

  private static void runGUI() {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
          GraphicalPublisher window = new GraphicalPublisher();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  private void setTxServer(String s) {
    if (!Utilities.noString(s))
      txServer = s;
  }

  private void setIgPack(String s) {
    if (!Utilities.noString(s))
      igPack = s;
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

  private static boolean hasNamedParam(String[] args, String param) {
    for (String a : args) {
      if (a.equals(param)) {
        return true;
      }
    }
    return false;
  }

  public void setLogger(ILoggingService logger) {
    this.logger = logger;

  }

  @Override
  public void logMessage(String msg) {
    System.out.println(msg);
    filelog.append(msg+"\r\n");
  }

  public String getQAFile() throws IOException {
    return Utilities.path(outputDir, "qa.html");
  }

  @Override
  public void logDebugMessage(String msg) {
    filelog.append(msg+"\r\n");
    try {
      TextFile.stringToFile(filelog.toString(), Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-publisher-tmp.log"));
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public static String buildReport(String ig, String source, String log, String qafile) throws FileNotFoundException, IOException {
    StringBuilder b = new StringBuilder();
    b.append("= Log =\r\n");
    b.append(log);
    b.append("\r\n\r\n");
    b.append("= System =\r\n");

    b.append("ig: ");
    b.append(ig);
    b.append("\r\n");

    b.append("current.dir: ");
    b.append(getCurentDirectory());
    b.append("\r\n");

    b.append("ig: ");
    b.append(ig);
    b.append("\r\n");

    b.append("source: ");
    b.append(source);
    b.append("\r\n");

    b.append("user.dir: ");
    b.append(System.getProperty("user.home"));
    b.append("\r\n");

    b.append("tx.server: ");
    b.append("http://fhir3.healthintersections.com.au/open");
    b.append("\r\n");

    b.append("tx.cache: ");
    b.append(Utilities.path(System.getProperty("user.home"), "fhircache"));
    b.append("\r\n");

    b.append("\r\n");

    b.append("= Validation =\r\n");
    if (qafile != null && new File(qafile).exists())
      b.append(TextFile.fileToString(qafile));

    b.append("\r\n");
    b.append("\r\n");

    if (ig != null) {
      b.append("= IG =\r\n");
      b.append(TextFile.fileToString(ig));
    }

    b.append("\r\n");
    b.append("\r\n");
    return b.toString();
  }


  public static void main(String[] args) throws Exception {
    int exitCode = 0;
    if (hasParam(args, "-gui") || args.length == 0) {
      runGUI();
    } else if (hasParam(args, "-help") || hasParam(args, "-?") || hasParam(args, "/?") || hasParam(args, "?")) {
      System.out.println("");
      System.out.println("To use this publisher to publish a FHIR Implementation Guide, run ");
      System.out.println("with the commands");
      System.out.println("");
      System.out.println("-spec [igpack.zip] -ig [source] -tx [url] -watch");
      System.out.println("");
      System.out.println("-spec: a path or a url where the igpack for the version of the core FHIR");
      System.out.println("  specification used by the ig being published is located.  If not specified");
      System.out.println("  the tool will retrieve the file from the web based on the specified FHIR version");
      System.out.println("-ig: a path or a url where the implementation guide control file is found");
      System.out.println("  see Wiki for Documentation");
      System.out.println("-tx: (optional) Address to use for terminology server ");
      System.out.println("  (default is http://fhir3.healthintersections.com.au)");
      System.out.println("  use 'n/a' to run without a terminology server");
      System.out.println("-watch (optional): if this is present, the publisher will not terminate;");
      System.out.println("  instead, it will stay running, an watch for changes to the IG or its ");
      System.out.println("  contents and re-run when it sees changes ");
      System.out.println("");
      System.out.println("The most important output from the publisher is qa.html");
      System.out.println("");
      System.out.println("Alternatively, you can run the Publisher directly against a folder containing");
      System.out.println("a set of resources, to validate and represent them");
      System.out.println("");
      System.out.println("-source [source] -destination [dest] -tx [url]");
      System.out.println("");
      System.out.println("-source: a local to scan for resources (e.g. logical models)");
      System.out.println("-destination: where to put the output (including qa.html)");
      System.out.println("");
      System.out.println("For additional information, see http://wiki.hl7.org/index.php?title=Proposed_new_FHIR_IG_build_Process");
    } else if (hasParam(args, "-multi")) {
      int i = 1;
      for (String ig : TextFile.fileToString(getNamedParam(args, "-multi")).split("\\r?\\n")) {
        if (!ig.startsWith(";")) {
          System.out.println("=======================================================================================");
          System.out.println("Publish IG "+ig);
          Publisher self = new Publisher();
          self.setConfigFile(ig);
          self.setTxServer(getNamedParam(args, "-tx"));
          self.filelog = new StringBuilder();
          try {
            self.execute(hasParam(args, "-resetTx"));
          } catch (Exception e) {
            exitCode = 1;
            System.out.println("Publishing Implementation Guide Failed: "+e.getMessage());
            System.out.println("");
            System.out.println("Stack Dump (for debugging):");
            e.printStackTrace();
            break;
          }
          TextFile.stringToFile(buildReport(ig, null, self.filelog.toString(), Utilities.path(self.qaDir, "validation.txt")), Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-publisher-"+Integer.toString(i)+".log"));
          System.out.println("=======================================================================================");
          System.out.println("");
          System.out.println("");
          i++;
        }
      }
    } else {
      System.out.println("FHIR Implementation Guide Publisher ("+Constants.VERSION+"-"+Constants.REVISION+") @ "+nowAsString());
      Publisher self = new Publisher();
      if (hasParam(args, "-source")) {
        // run with standard template. this is publishing lite
        self.setSourceDir(getNamedParam(args, "-source"));
        self.setDestDir(getNamedParam(args, "-destination"));
      } else {
        self.setConfigFile(getNamedParam(args, "-ig"));
        if (Utilities.noString(self.getConfigFile()))
          throw new Exception("No Implementation Guide Specified (-ig parameter)");
        if (!(new File(self.getConfigFile()).isAbsolute()))
          self.setConfigFile(Utilities.path(System.getProperty("user.dir"), self.getConfigFile()));
      }
      self.setIgPack(getNamedParam(args, "-spec"));
      self.setTxServer(getNamedParam(args, "-tx"));
      self.setAutoBuildMode(hasNamedParam(args, "-auto-ig-build"));
      self.watch = hasParam(args, "-watch");
      self.filelog = new StringBuilder();
      try {
        self.execute(hasParam(args, "-resetTx"));
      } catch (Exception e) {
        exitCode = 1;
        self.log("Publishing Content Failed: "+e.getMessage());
        self.log("");
        self.log("Use -? to get command line help");
        self.log("");
        self.log("Stack Dump (for debugging):");
        e.printStackTrace();
        for (StackTraceElement st : e.getStackTrace()) {
          self.filelog.append(st.toString());
        }
        exitCode = 1;
      } finally {
        TextFile.stringToFile(buildReport(getNamedParam(args, "-ig"), getNamedParam(args, "-source"), self.filelog.toString(), Utilities.path(self.qaDir, "validation.txt")), Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-publisher.log"));
      }
    }
    System.exit(exitCode);
  }

  private void setAutoBuildMode(boolean value) {
    autoBuildMode = value;
    
  }

  private static String nowAsString() {
    Calendar cal = Calendar.getInstance();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
    return df.format(cal.getTime());
  }


}
