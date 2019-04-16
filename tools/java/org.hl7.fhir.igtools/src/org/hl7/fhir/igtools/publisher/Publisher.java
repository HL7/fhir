package org.hl7.fhir.igtools.publisher;

import java.awt.EventQueue;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
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
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.NotImplementedException;
import org.apache.commons.lang3.SystemUtils;
import org.hl7.fhir.convertors.IGR2ConvertorAdvisor;
import org.hl7.fhir.convertors.R2016MayToR4Loader;
import org.hl7.fhir.convertors.R2016MayToR5Loader;
import org.hl7.fhir.convertors.R2ToR4Loader;
import org.hl7.fhir.convertors.R2ToR5Loader;
import org.hl7.fhir.convertors.R3ToR4Loader;
import org.hl7.fhir.convertors.R3ToR5Loader;
import org.hl7.fhir.convertors.R4ToR5Loader;
import org.hl7.fhir.convertors.TerminologyClientFactory;
import org.hl7.fhir.convertors.VersionConvertorAdvisor40;
import org.hl7.fhir.convertors.VersionConvertorAdvisor50;
import org.hl7.fhir.convertors.VersionConvertor_10_40;
import org.hl7.fhir.convertors.VersionConvertor_10_50;
import org.hl7.fhir.convertors.VersionConvertor_14_30;
import org.hl7.fhir.convertors.VersionConvertor_14_40;
import org.hl7.fhir.convertors.VersionConvertor_14_50;
import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.convertors.VersionConvertor_30_50;
import org.hl7.fhir.convertors.VersionConvertor_40_50;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.exceptions.PathEngineException;
import org.hl7.fhir.igtools.publisher.FetchedFile.FetchedBundleType;
import org.hl7.fhir.igtools.publisher.IFetchFile.FetchState;
import org.hl7.fhir.igtools.renderers.BaseRenderer;
import org.hl7.fhir.igtools.renderers.CodeSystemRenderer;
import org.hl7.fhir.igtools.renderers.JsonXhtmlRenderer;
import org.hl7.fhir.igtools.renderers.StructureDefinitionRenderer;
import org.hl7.fhir.igtools.renderers.StructureMapRenderer;
import org.hl7.fhir.igtools.renderers.ValidationPresenter;
import org.hl7.fhir.igtools.renderers.ValueSetRenderer;
import org.hl7.fhir.igtools.renderers.XmlXHtmlRenderer;
import org.hl7.fhir.igtools.spreadsheets.IgSpreadsheetParser;
import org.hl7.fhir.igtools.templates.TemplateManager;
import org.hl7.fhir.igtools.ui.GraphicalPublisher;
import org.hl7.fhir.r5.conformance.ConstraintJavaGenerator;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.context.IWorkerContext.ILoggingService;
import org.hl7.fhir.r5.context.IWorkerContext.ValidationResult;
import org.hl7.fhir.r5.context.SimpleWorkerContext;
import org.hl7.fhir.r5.elementmodel.Element;
import org.hl7.fhir.r5.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r5.elementmodel.ObjectConverter;
import org.hl7.fhir.r5.elementmodel.ParserBase.ValidationPolicy;
import org.hl7.fhir.r5.elementmodel.TurtleParser;
import org.hl7.fhir.r5.formats.IParser.OutputStyle;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.Base;
import org.hl7.fhir.r5.model.Bundle;
import org.hl7.fhir.r5.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r5.model.Bundle.BundleType;
import org.hl7.fhir.r5.model.CapabilityStatement;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeableConcept;
import org.hl7.fhir.r5.model.Coding;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.ContactDetail;
import org.hl7.fhir.r5.model.ContactPoint;
import org.hl7.fhir.r5.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r5.model.DateTimeType;
import org.hl7.fhir.r5.model.DomainResource;
import org.hl7.fhir.r5.model.ElementDefinition;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.r5.model.Enumeration;
import org.hl7.fhir.r5.model.Enumerations.FHIRVersion;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.ExpressionNode;
import org.hl7.fhir.r5.model.FhirPublication;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.ImplementationGuide.GuidePageGeneration;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionGroupingComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionPageComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDefinitionResourceComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.ImplementationGuideDependsOnComponent;
import org.hl7.fhir.r5.model.ImplementationGuide.SPDXLicense;
import org.hl7.fhir.r5.model.MetadataResource;
import org.hl7.fhir.r5.model.Parameters;
import org.hl7.fhir.r5.model.PrimitiveType;
import org.hl7.fhir.r5.model.Reference;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.r5.model.ResourceFactory;
import org.hl7.fhir.r5.model.ResourceType;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionContextComponent;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.model.StructureMap;
import org.hl7.fhir.r5.model.StructureMap.StructureMapModelMode;
import org.hl7.fhir.r5.model.StructureMap.StructureMapStructureComponent;
import org.hl7.fhir.r5.model.TypeDetails;
import org.hl7.fhir.r5.model.UriType;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.openapi.OpenApiGenerator;
import org.hl7.fhir.r5.openapi.Writer;
import org.hl7.fhir.r5.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.r5.utils.EOperationOutcome;
import org.hl7.fhir.r5.utils.FHIRPathEngine;
import org.hl7.fhir.r5.utils.FHIRPathEngine.IEvaluationContext;
import org.hl7.fhir.r5.utils.IResourceValidator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator;
import org.hl7.fhir.r5.utils.NPMPackageGenerator.Category;
import org.hl7.fhir.r5.utils.NarrativeGenerator;
import org.hl7.fhir.r5.utils.NarrativeGenerator.ILiquidTemplateProvider;
import org.hl7.fhir.r5.utils.NarrativeGenerator.IReferenceResolver;
import org.hl7.fhir.r5.utils.NarrativeGenerator.ITypeParser;
import org.hl7.fhir.r5.utils.NarrativeGenerator.ResourceWithReference;
import org.hl7.fhir.r5.utils.StructureMapUtilities;
import org.hl7.fhir.r5.utils.StructureMapUtilities.StructureMapAnalysis;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.r5.utils.client.FHIRToolingClient;
import org.hl7.fhir.r5.utils.formats.Turtle;
import org.hl7.fhir.r5.validation.CodeSystemValidator;
import org.hl7.fhir.r5.validation.InstanceValidator;
import org.hl7.fhir.r5.validation.ProfileValidator;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.JsonMerger;
import org.hl7.fhir.utilities.MarkDownProcessor;
import org.hl7.fhir.utilities.MarkDownProcessor.Dialect;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.hl7.fhir.utilities.cache.NpmPackage;
import org.hl7.fhir.utilities.cache.PackageCacheManager;
import org.hl7.fhir.utilities.cache.PackageGenerator.PackageType;
import org.hl7.fhir.utilities.json.JsonTrackingParser;
import org.hl7.fhir.utilities.cache.ToolsVersion;
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
 * Documentation: see http://wiki.hl7.org/index.php?title=IG_Publisher_Documentation
 * 
 * @author Grahame Grieve
 *
 */

public class Publisher implements IWorkerContext.ILoggingService, IReferenceResolver {

  public class IGPublisherHostServices implements IEvaluationContext {

    @Override
    public Base resolveConstant(Object appContext, String name, boolean beforeContext) throws PathEngineException {
//      if ("id".equals(name))
        return null;
//      throw new NotImplementedException("Not done yet @ IGPublisherHostServices.resolveConstant("+appContext.toString()+", \""+name+"\")");
    }

    @Override
    public TypeDetails resolveConstantType(Object appContext, String name) throws PathEngineException {
      throw new NotImplementedException("Not done yet (IGPublisherHostServices.resolveConstantType)");
    }

    @Override
    public boolean log(String argument, List<Base> focus) {
      return false;
    }

    @Override
    public FunctionDetails resolveFunction(String functionName) {
      throw new NotImplementedException("Not done yet (IGPublisherHostServices.resolveFunction)");
    }

    @Override
    public TypeDetails checkFunction(Object appContext, String functionName, List<TypeDetails> parameters) throws PathEngineException {
      throw new NotImplementedException("Not done yet (IGPublisherHostServices.checkFunction)");
    }

    @Override
    public List<Base> executeFunction(Object appContext, String functionName, List<List<Base>> parameters) {
      throw new NotImplementedException("Not done yet (IGPublisherHostServices.executeFunction)");
    }

    @Override
    public Base resolveReference(Object appContext, String url) {
      if (appContext instanceof Element)  {
        Element bnd = (Element) appContext;
        if (bnd.fhirType().equals("Bundle")) {
          for (Element be : bnd.getChildrenByName("entry")) {
            Element res = be.getNamedChild("resource");
            if (res != null) { 
              String fullUrl = be.getChildValue("fullUrl");
              String rt = res.fhirType();
              String id = res.getChildValue("id");
              if (url.equals(fullUrl))
                return res;
              if (url.equals(rt+"/"+id))
                return res;
            }
          }
        }
      }
      if (Utilities.isAbsoluteUrl(url)) {
        if (url.startsWith(igpkp.getCanonical())) {
          url = url.substring(igpkp.getCanonical().length());
          if (url.startsWith("/"))
            url = url.substring(1);
        } else
          return null;;
      }
      for (FetchedFile f : fileList) {
        for (FetchedResource r : f.getResources()) {
          if (r.getElement() != null && url.equals(r.getElement().fhirType()+"/"+r.getId()))
            return r.getElement();
        }
      }
      return null;
    }

    @Override
    public boolean conformsToProfile(Object appContext, Base item, String url) throws FHIRException {
      IResourceValidator val = context.newValidator();
      List<ValidationMessage> valerrors = new ArrayList<ValidationMessage>();
      if (item instanceof Resource) {
        val.validate(appContext, valerrors, (Resource) item, url);
        boolean ok = true;
        for (ValidationMessage v : valerrors)
          ok = ok && v.getLevel().isError();
        return ok;
      }
      throw new NotImplementedException("Not done yet (IGPublisherHostServices.conformsToProfile), when item is element");
    }
  }

  public class TypeParserR2 implements ITypeParser {

    @Override
    public Base parseType(String xml, String type) throws IOException, FHIRException {
      org.hl7.fhir.dstu2.model.Type t = new org.hl7.fhir.dstu2.formats.XmlParser().parseType(xml, type); 
      return new VersionConvertor_10_50(null).convertType(t);
    }
  }

  public class TypeParserR14 implements ITypeParser {

    @Override
    public Base parseType(String xml, String type) throws IOException, FHIRException {
      org.hl7.fhir.dstu2016may.model.Type t = new org.hl7.fhir.dstu2016may.formats.XmlParser().parseType(xml, type); 
      return VersionConvertor_14_50.convertType(t);
    }
  }

  public class TypeParserR3 implements ITypeParser {

    @Override
    public Base parseType(String xml, String type) throws IOException, FHIRException {
      org.hl7.fhir.dstu3.model.Type t = new org.hl7.fhir.dstu3.formats.XmlParser().parseType(xml, type); 
      return VersionConvertor_30_50.convertType(t);
    }
  }

  public class TypeParserR4 implements ITypeParser {

    @Override
    public Base parseType(String xml, String type) throws IOException, FHIRException {
      org.hl7.fhir.r4.model.Type t = new org.hl7.fhir.r4.formats.XmlParser().parseType(xml, type); 
      return VersionConvertor_40_50.convertType(t);
    }
  }



  public enum IGBuildMode { MANUAL, AUTOBUILD, WEBSERVER, PUBLICATION }


  public enum LinkTargetType {

  }


  public enum CacheOption {
    LEAVE, CLEAR_ERRORS, CLEAR_ALL;
  }

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
  private FHIRToolingClient webTxServer;
  private static String txServer = "http://tx.fhir.org";
//  private static String txServer = "http://local.fhir.org:960";
  private String igPack = "";
  private boolean watch;
  private boolean debug;
  private boolean isChild;
  private boolean cacheVersion;
  private boolean appendTrailingSlashInDataFile;

  private Publisher childPublisher = null;
  private String childOutput = "";
  private GenerationTool tool;
  private boolean genExampleNarratives = true;

  private List<String> resourceDirs = new ArrayList<String>();
  private List<String> pagesDirs = new ArrayList<String>();
  private String tempDir;
  private String outputDir;
  private String specPath;
  private String qaDir;
  private String version;
  private List<String> suppressedMessages = new ArrayList<String>();

  private String igName;
  private IGBuildMode mode; // for the IG publication infrastructure

  private IFetchFile fetcher = new SimpleFetcher(this);
  private SimpleWorkerContext context; // 
  private InstanceValidator validator;
  private ProfileValidator pvalidator;
  private CodeSystemValidator csvalidator;
  private IGKnowledgeProvider igpkp;
  private List<SpecMapManager> specMaps = new ArrayList<SpecMapManager>();
  private boolean first;

  private Map<String, SpecificationPackage> specifications;
  private Map<ImplementationGuideDefinitionResourceComponent, FetchedFile> fileMap = new HashMap<ImplementationGuideDefinitionResourceComponent, FetchedFile>();
  private Map<String, FetchedFile> altMap = new HashMap<String, FetchedFile>();
  private List<FetchedFile> fileList = new ArrayList<FetchedFile>();
  private List<FetchedFile> changeList = new ArrayList<FetchedFile>();
  private List<String> fileNames = new ArrayList<String>();
  private Map<String, FetchedFile> relativeNames = new HashMap<String, FetchedFile>();
  private Set<String> bndIds = new HashSet<String>();
  private List<Resource> loaded = new ArrayList<Resource>();
  private ImplementationGuide sourceIg;
  private ImplementationGuide publishedIg;
  private List<ValidationMessage> errors = new ArrayList<ValidationMessage>();
  private Calendar execTime = Calendar.getInstance();
  private Set<String> otherFilesStartup = new HashSet<String>();
  private Set<String> otherFilesRun = new HashSet<String>();
  private Set<String> regenList = new HashSet<String>();
  private StringBuilder filelog;
  private Set<String> allOutputs = new HashSet<String>();
  private Set<FetchedResource> examples = new HashSet<FetchedResource>();
  private HashMap<String, FetchedResource> resources = new HashMap<String, FetchedResource>();
  private HashMap<String, ImplementationGuideDefinitionPageComponent> igPages = new HashMap<String, ImplementationGuideDefinitionPageComponent>();
  private List<String> logOptions = new ArrayList<String>();
  private List<String> listedURLExemptions = new ArrayList<String>();
  private String jekyllCommand = "jekyll";
  private boolean makeQA = true;

  private long globalStart;

  private ILoggingService logger = this;

  private HTLMLInspector inspector;

  private List<String> prePagesDirs = new ArrayList<String>();
  private HashMap<String, PreProcessInfo> preProcessInfo = new HashMap<String, PreProcessInfo>();

  private String historyPage;

  private String vsCache;

  private String adHocTmpDir;

  private NarrativeGenerator gen;

  private String businessVersion;

  private boolean allowBrokenHtml;
  private CacheOption cacheOption;

  private List<CodeableConcept> jurisdictions;
  private String configFileRootPath;

  private MarkDownProcessor markdownEngine;
  private List<ValueSet> expansions = new ArrayList<>();

  private String npmName;

  private NPMPackageGenerator npm;

  private PackageCacheManager pcm;

  private JsonArray buildInfo;

  private TemplateManager templateManager;

  private String rootDir;
  private String templatePck;
  
  private boolean templateLoaded ;

  private String packagesFolder;
  private String targetOutput;
  private String targetOutputNested;

  private String folderToDelete;

  private String specifiedVersion;

  private NpmPackage packge;

  private String txLog;

  private boolean includeHeadings;
  private String openApiTemplate;
  private Map<String, String> extraTemplates = new HashMap<>();
  private String license;
  private String htmlTemplate;
  private String mdTemplate;
  private boolean brokenLinksError;
  private String nestedIgConfig;
  private String igArtifactsPage;
  private String nestedIgOutput;
  private boolean genExamples;
  private boolean doTransforms;
  private List<String> spreadsheets = new ArrayList<>();
  private List<String> bundles = new ArrayList<>();

  private IGPublisherLiquidTemplateServices templateProvider;

  private List<NpmPackage> npmList = new ArrayList<>();

  private String repoRoot;

  private ValidationServices validationFetcher;
  
  private class PreProcessInfo {
    private String xsltName;
    private byte[] xslt;
    private String relativePath;
    public PreProcessInfo(String xsltName, String relativePath) throws IOException {
      this.xsltName = xsltName;
      if (xsltName!=null)
        this.xslt = TextFile.fileToBytes(xsltName);
      this.relativePath = relativePath;
    }
    public boolean hasXslt() {
      return xslt!=null;
    }
    public byte[] getXslt() {
      return xslt;
    }
    public boolean hasRelativePath() {
      return relativePath != null && !relativePath.isEmpty();
    }
    public String getRelativePath() {
      return relativePath;
    }
  }
  public void execute() throws Exception {
    globalStart = System.nanoTime();
    initialize();
    log("Load Content");
    try {
      createIg();
    } catch (Exception e) {
      recordOutcome(e, null);
      throw e;
    }

    if (watch) {
      first = false;
      log("Watching for changes on a 5sec cycle");
      while (watch) { // terminated externally
        Thread.sleep(5000);
        if (load()) {
          log("Processing changes to "+Integer.toString(changeList.size())+(changeList.size() == 1 ? " file" : " files")+" @ "+genTime());
          long startTime = System.nanoTime();
          loadConformance();
          generateNarratives();
          checkDependencies();
          validate();
          generate();
          clean();
          long endTime = System.nanoTime();
          processTxLog(Utilities.path(destDir != null ? destDir : outputDir, "qa-tx.html"));
          ValidationPresenter val = new ValidationPresenter(version, businessVersion, igpkp, childPublisher == null? null : childPublisher.getIgpkp(), outputDir, npmName, childPublisher == null? null : childPublisher.npmName, 
              new BallotChecker(repoRoot).check(igpkp.getCanonical(), npmName, businessVersion, historyPage, version));
          log("Finished. "+presentDuration(endTime - startTime)+". Validation output in "+val.generate(sourceIg.getName(), errors, fileList, Utilities.path(destDir != null ? destDir : outputDir, "qa.html"), suppressedMessages));
          recordOutcome(null, val);
        }
      }
    } else
      log("Done");
    if (templateLoaded && new File(rootDir).exists())
      Utilities.clearDirectory(Utilities.path(rootDir, "template"));
    if (folderToDelete != null) {
      try {
        Utilities.clearDirectory(folderToDelete);
        new File(folderToDelete).delete();
      } catch (Throwable e) {
        // nothing
      }
    }
  }


  private void processTxLog(String path) throws FileNotFoundException, IOException {
    if (txLog != null) {
      String tx = TextFile.fileToString(txLog);
      PrintStream f = new PrintStream(new FileOutputStream(path));
      String title = "Terminology Server Log";
      f.println("<html><head><title>"+title+"</title></head><body><h2>"+title+"</h2>");
      f.print(tx);
      f.println("</head></html>");
    }
    
  }


  public void createIg() throws Exception, IOException, EOperationOutcome, FHIRException {
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
    ValidationPresenter val = new ValidationPresenter(version, businessVersion, igpkp, childPublisher == null? null : childPublisher.getIgpkp(), outputDir, npmName, childPublisher == null? null : childPublisher.npmName, 
        new BallotChecker(repoRoot).check(igpkp.getCanonical(), npmName, businessVersion, historyPage, version));
    if (isChild()) {
      log("Finished. "+presentDuration(endTime - startTime));      
    } else {
      processTxLog(Utilities.path(destDir != null ? destDir : outputDir, "qa-tx.html"));
      log("Finished. "+presentDuration(endTime - startTime)+". Validation output in "+val.generate(sourceIg.getName(), errors, fileList, Utilities.path(destDir != null ? destDir : outputDir, "qa.html"), suppressedMessages));
    }
    recordOutcome(null, val);
  }

  private void recordOutcome(Exception ex, ValidationPresenter val) {
    try {
      JsonObject j = new JsonObject();
      if (sourceIg != null) {
        j.addProperty("url", sourceIg.getUrl());
        j.addProperty("name", sourceIg.getName());
      }
      if (publishedIg != null && publishedIg.hasPackageId()) {
        j.addProperty("package-id", publishedIg.getPackageId());
        j.addProperty("ig-ver", publishedIg.getVersion());
      }
      j.addProperty("date", new SimpleDateFormat("EEE, dd MMM, yyyy HH:mm:ss Z", new Locale("en", "US")).format(execTime.getTime()));
      if (val != null) {
        j.addProperty("errs", val.getErr());
        j.addProperty("warnings", val.getWarn());
        j.addProperty("hints", val.getInfo());
      }
      if (ex != null)
        j.addProperty("exception", ex.getMessage());
      j.addProperty("version", version);
      if (templatePck != null)
        j.addProperty("template", templatePck);
      j.addProperty("tool", Constants.VERSION+" ("+ToolsVersion.TOOLS_VERSION+")");
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String json = gson.toJson(j);
      TextFile.stringToFile(json, Utilities.path(destDir != null ? destDir : outputDir, "qa.json"));
    } catch (Exception e) {
      // nothing at all
    }
  }
  
  public CacheOption getCacheOption() {
    return cacheOption;
  }


  public void setCacheOption(CacheOption cacheOption) {
    this.cacheOption = cacheOption;
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
          return new ResourceWithReference(path, gen.new ResourceWrapperMetaElement(r.getElement()));
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

  private void generateNarratives() throws Exception {
    dlog(LogCategory.PROGRESS, "gen narratives");
    for (FetchedFile f : fileList) {
      System.out.println(f.getName());
      for (FetchedResource r : f.getResources()) {
        if (r.getExampleUri()==null || genExampleNarratives) {
          dlog(LogCategory.PROGRESS, "narrative for "+f.getName()+" : "+r.getId());
          if (r.getResource() != null && isConvertableResource(r.getResource().fhirType())) {
            boolean regen = false;
            gen.setDefinitionsTarget(igpkp.getDefinitionsName(r));
            if (r.getResource() instanceof DomainResource && !(((DomainResource) r.getResource()).hasText() && ((DomainResource) r.getResource()).getText().hasDiv()))
              regen = gen.generate((DomainResource) r.getResource(), otherFilesStartup);
            if (r.getResource() instanceof Bundle)
              regen = gen.generate((Bundle) r.getResource(), false, otherFilesStartup);
            if (regen)
              r.setElement(convertToElement(r.getResource()));
          } else {
            if ("http://hl7.org/fhir/StructureDefinition/DomainResource".equals(r.getElement().getProperty().getStructure().getBaseDefinition()) && !hasNarrative(r.getElement())) {
              gen.generate(r.getElement(), true, getTypeLoader(f,r));
            } else if (r.getElement().fhirType().equals("Bundle")) {
              for (Element e : r.getElement().getChildrenByName("entry")) {
                Element res = e.getNamedChild("resource");
                if (res!=null && "http://hl7.org/fhir/StructureDefinition/DomainResource".equals(res.getProperty().getStructure().getBaseDefinition()) && !hasNarrative(res)) {
                  gen.generate(gen.new ResourceContext(r.getElement(), res), res, true, getTypeLoader(f,r));
                }
              }
            }
          }
        } else {
          dlog(LogCategory.PROGRESS, "skipped narrative for "+f.getName()+" : "+r.getId());
        }
      }
    }
  }

  private boolean isConvertableResource(String t) {
    return Utilities.existsInList(t, "StructureDefinition", "ValueSet", "CodeSystem", "Conformance", "CapabilityStatement", 
        "ConceptMap", "OperationOutcome", "CompartmentDefinition", "OperationDefinition", "ImplementationGuide");
  }


  private ITypeParser getTypeLoader(FetchedFile f, FetchedResource r) throws Exception {
    String ver = r.getConfig() == null ? null : ostr(r.getConfig(), "version");
    if (ver == null)
      ver = version; // fall back to global version
    if (ver.equals("3.0.1") || ver.equals("3.0.0")) {
      return new TypeParserR3();
    } else if (ver.equals("4.0.0")) {
      return new TypeParserR4();
    } else if (ver.equals("1.4.0")) {
      return new TypeParserR14();
    } else if (ver.equals("1.0.2")) {
      return new TypeParserR2();
    } else if (ver.equals(Constants.VERSION)) {
      return null;
    } else
      throw new FHIRException("Unsupported version "+ver);
  }


  private void log(LogCategory progress, String message) {
    log(message);
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

  private String genDate() {
    return new SimpleDateFormat("dd/MM/yyyy", new Locale("en", "US")).format(execTime.getTime());
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
          if (bc.getUrl() != null && bc.getUrl().equals(uri))
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

  private boolean bool(JsonObject obj, String name) throws Exception {
    if (!obj.has(name))
      return false;
    if (!(obj.get(name) instanceof JsonPrimitive))
      return false;
    JsonPrimitive p = (JsonPrimitive) obj.get(name);
    if (p.isBoolean())
      return p.getAsBoolean();
    return false;
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
    if (obj == null)
      return null;
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
    if (obj == null || !obj.has(name))
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

  public void initialize() throws Exception {
    pcm = new PackageCacheManager(mode == null || mode == IGBuildMode.MANUAL || mode == IGBuildMode.PUBLICATION, ToolsVersion.TOOLS_VERSION);
    if (mode == IGBuildMode.PUBLICATION)
      log("Build Formal Publication package, intended for "+getTargetOutput());
    
    templateManager = new TemplateManager(pcm);
    templateProvider = new IGPublisherLiquidTemplateServices();
    log("Package Cache: "+pcm.getFolder());
    if (packagesFolder != null) {
      log("Loading Packages from "+packagesFolder);
      pcm.loadFromFolder(packagesFolder);
    }
    fetcher.setResourceDirs(resourceDirs);
    first = true;
    initializeFromJson();   
  }

  private void initializeFromJson() throws Exception {
    JsonObject configuration = null;
    if (configFile == null) {
      adHocTmpDir = Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-scratch");
      log("Using inbuilt IG template in "+sourceDir);
      copyDefaultTemplate();
      buildConfigFile();
    } else
      log("Load Configuration from "+configFile);
    try {
      configuration = JsonTrackingParser.parseJsonFile(configFile);
    } catch (Exception e) {
      throw new Exception("Error Reading JSON Config file at "+configFile+": "+e.getMessage(), e);
    }
    repoRoot = Utilities.getDirectoryForFile(configFile);
    if (configuration.has("redirect")) { // redirect to support auto-build for complex projects with IG folder in subdirectory
      String redirectFile = Utilities.path(Utilities.getDirectoryForFile(configFile), configuration.get("redirect").getAsString());
      log("Redirecting to Configuration from " + redirectFile);
      configFile = redirectFile;
      configuration = JsonTrackingParser.parseJsonFile(redirectFile);
    }
    if (configuration.has("logging")) {
      for (JsonElement n : configuration.getAsJsonArray("logging")) {
        String level = ((JsonPrimitive) n).getAsString();
        System.out.println("Logging " + level);
        logOptions.add(level);
      }
    }
    if (configuration.has("exampleNarratives")) {
      genExampleNarratives = configuration.get("exampleNarratives").getAsBoolean();
    }

    if (configuration.has("tool") && !"jekyll".equals(str(configuration, "tool")))
      throw new Exception("Error: At present, configuration file must include a \"tool\" property with a value of 'jekyll'");
    tool = GenerationTool.Jekyll;
    version = ostr(configuration, "version");
    if (Utilities.noString(version))
      version = Constants.VERSION;
    
    if (configuration.has("paths") && !(configuration.get("paths") instanceof JsonObject))
      throw new Exception("Error: if configuration file has a \"paths\", it must be an object");
    JsonObject paths = configuration.getAsJsonObject("paths");
    if (fetcher instanceof ZipFetcher) {
      rootDir = configFileRootPath;
    } else {
      rootDir = Utilities.getDirectoryForFile(configFile);
      if (Utilities.noString(rootDir))
        rootDir = getCurentDirectory();
      // We need the root to be expressed as a full path.  getDirectoryForFile will do that in general, but not in Eclipse
      rootDir = new File(rootDir).getCanonicalPath();
    }

    
    if (Utilities.existsInList(version.substring(0,  3), "1.0", "1.4", "1.6", "3.0"))
      markdownEngine = new MarkDownProcessor(Dialect.DARING_FIREBALL);
    else
      markdownEngine = new MarkDownProcessor(Dialect.COMMON_MARK);
    
    log("Root directory: "+rootDir);
    if (paths != null && paths.get("resources") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("resources"))
        resourceDirs.add(Utilities.path(rootDir, ((JsonPrimitive) e).getAsString()));
    } else
      resourceDirs.add(Utilities.path(rootDir, str(paths, "resources", "resources")));
    if (paths != null && paths.get("pages") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("pages"))
        pagesDirs.add(Utilities.path(rootDir, ((JsonPrimitive) e).getAsString()));
    } else
      pagesDirs.add(Utilities.path(rootDir, str(paths, "pages", "pages")));
    
    if (mode != IGBuildMode.WEBSERVER){
      tempDir = Utilities.path(rootDir, str(paths, "temp", "temp"));
      String p = str(paths, "output", "output");
      outputDir = Paths.get(p).isAbsolute() ? p : Utilities.path(rootDir, p);
    }
    
   qaDir = Utilities.path(rootDir, str(paths, "qa"));
   vsCache = ostr(paths, "txCache");
   templateProvider.clear();
   if (paths.has("liquid")) {
     templateProvider.load(Utilities.path(rootDir, str(paths, "liquid", "liquid")));
   } 
    
   if (mode == IGBuildMode.WEBSERVER) 
      vsCache = Utilities.path(System.getProperty("java.io.tmpdir"), "fhircache");
    else if (vsCache != null)
      vsCache = Utilities.path(rootDir, vsCache);
    else if (mode == IGBuildMode.AUTOBUILD)
      vsCache = Utilities.path(System.getProperty("java.io.tmpdir"), "fhircache");
    else
      vsCache = Utilities.path(System.getProperty("user.home"), "fhircache");

    specPath = str(paths, "specification", "http://hl7.org/fhir/");
    if (configuration.has("pre-process")) {
      if (configuration.get("pre-process") instanceof JsonArray) {
        for (JsonElement e : (JsonArray) configuration.get("pre-process")) {
          handlePreProcess((JsonObject)e, rootDir);
        }
      } else
        handlePreProcess(configuration.getAsJsonObject("pre-process"), rootDir);
    }

    igName = Utilities.path(resourceDirs.get(0), str(configuration, "source", "ig.xml"));

    dlog(LogCategory.INIT, "Check folders");
    for (String s : resourceDirs) {
      dlog(LogCategory.INIT, "Source: "+s);
      checkDir(s);
    }
    for (String s : pagesDirs) {
      dlog(LogCategory.INIT, "Pages: "+s);
      checkDir(s);
    }
    dlog(LogCategory.INIT, "Temp: "+tempDir);
    Utilities.clearDirectory(tempDir);
    forceDir(tempDir);
    forceDir(Utilities.path(tempDir, "_includes"));
    forceDir(Utilities.path(tempDir, "_data"));
    dlog(LogCategory.INIT, "Output: "+outputDir);
    forceDir(outputDir);
    Utilities.clearDirectory(outputDir);
    dlog(LogCategory.INIT, "Temp: "+qaDir);
    forceDir(qaDir);

    Utilities.createDirectory(vsCache);
    if (cacheOption == CacheOption.CLEAR_ALL || (mode == IGBuildMode.AUTOBUILD)) {
      log("Terminology Cache is at "+vsCache+". Clearing now");
      Utilities.clearDirectory(vsCache);
    } else if (cacheOption == CacheOption.CLEAR_ERRORS) {
        log("Terminology Cache is at "+vsCache+". Clearing Errors now");
        log("Deleted "+Integer.toString(clearErrors(vsCache))+" files");
    } else
      log("Terminology Cache is at "+vsCache+". "+Integer.toString(Utilities.countFilesInDirectory(vsCache))+" files in cache");
    if (!new File(vsCache).exists())
      throw new Exception("Unable to access or create the cache directory at "+vsCache);

    SpecificationPackage spec = null; 

    loadFromBuildServer();
    if (specifications != null) { 
      // web server mode: use one of the preloaded versions...
      String sver = version;
      if (sver.lastIndexOf(".") > sver.indexOf("."))
        sver = sver.substring(0, sver.lastIndexOf("."));
      spec = specifications.get(sver);
      if (spec == null)
        throw new FHIRException("Unable to find specification for version "+sver);
    } else
      spec = loadCorePackage();
    
    context = spec.makeContext();
    context.setIgnoreProfileErrors(true);
    context.setLogger(logger);
    context.setAllowLoadingDuplicates(true);
    context.setExpandCodesLimit(1000);
    context.setExpansionProfile(makeExpProfile());
    try {
      new ConfigFileConverter().convert(configFile, context, pcm);
    } catch (Exception e) {
      System.out.println("exception generating new IG");
      e.printStackTrace();
    }
    log("Load Terminology Cache from "+vsCache);
    context.initTS(vsCache);
    String sct = str(configuration, "sct-edition", "http://snomed.info/sct/900000000000207008");
    context.getExpansionParameters().addParameter("system-version", "http://snomed.info/sct|"+sct);
    txLog = Utilities.createTempFile("fhir-ig-", ".log").getAbsolutePath();
    context.getExpansionParameters().addParameter("activeOnly", "true".equals(ostr(configuration, "activeOnly")));
    if (mode != IGBuildMode.WEBSERVER) {
      if (txServer == null || !txServer.contains(":")) {
        log("WARNING: Running without terminology server - terminology content will likely not publish correctly");
        context.setCanRunWithoutTerminology(true);
        txLog = null;
      } else {
        log("Connect to Terminology Server at "+txServer);
        checkTSVersion(vsCache, context.connectToTSServer(TerminologyClientFactory.makeClient(txServer, FhirPublication.fromCode(version)), txLog));
      }
    } else 
      checkTSVersion(vsCache, context.connectToTSServer(TerminologyClientFactory.makeClient(webTxServer.getAddress(), FhirPublication.fromCode(version)), txLog));
    
    
    loadSpecDetails(context.getBinaries().get("spec.internals"));
    igpkp = new IGKnowledgeProvider(context, checkAppendSlash(specPath), configuration, errors, version.equals("1.0.2"));
    igpkp.loadSpecPaths(specMaps.get(0));
    fetcher.setPkp(igpkp);

    if (configuration.has("fixed-business-version")) {
      businessVersion = configuration.getAsJsonPrimitive("fixed-business-version").getAsString();
    }
    
    inspector = new HTLMLInspector(outputDir, specMaps, this, igpkp.getCanonical(), igpkp.isHL7Checks());
    inspector.getManual().add("full-ig.zip");
    historyPage = ostr(paths, "history");
    if (historyPage != null) {
      inspector.getManual().add(historyPage);
      inspector.getManual().add(Utilities.pathURL(igpkp.getCanonical(), historyPage));
    }
    inspector.getManual().add("qa.html");
    inspector.getManual().add("qa-tx.html");
    if (configuration.has("exemptHtmlPatterns")) {
      for (JsonElement e : configuration.getAsJsonArray("exemptHtmlPatterns"))
        inspector.getExemptHtmlPatterns().add(e.getAsString());
    }
    allowBrokenHtml = "true".equals(ostr(configuration, "allow-broken-links"));
    inspector.setStrict("true".equals(ostr(configuration, "allow-malformed-html")));
    inspector.setPcm(pcm);
    makeQA = mode == IGBuildMode.WEBSERVER ? false : !"true".equals(ostr(configuration, "suppress-qa"));
    
    JsonArray deps = configuration.getAsJsonArray("dependencyList");
    if (deps != null) {
      for (JsonElement dep : deps) {
        loadIg((JsonObject) dep);
      }
    }
    if (configuration.has("template"))
      loadTemplate(configuration, str(configuration, "template"));

    // ;
    validator = new InstanceValidator(context, new IGPublisherHostServices()); // todo: host services for reference resolution....
    validator.setAllowXsiLocation(true);
    validator.setNoBindingMsgSuppressed(true);
    validator.setNoExtensibleWarnings(true);
    validator.setHintAboutNonMustSupport(bool(configuration, "hintAboutNonMustSupport"));
    validator.setAnyExtensionsAllowed(bool(configuration, "anyExtensionsAllowed"));
    
    pvalidator = new ProfileValidator();
    pvalidator.setContext(context);
    csvalidator = new CodeSystemValidator();
    if (configuration.has("check-aggregation") && configuration.get("check-aggregation").getAsBoolean())
      pvalidator.setCheckAggregation(true);
    if (configuration.has("check-mustSupport") && configuration.get("check-mustSupport").getAsBoolean())
      pvalidator.setCheckMustSupport(true);

    if (paths.get("extension-domains") instanceof JsonArray) {
      for (JsonElement e : (JsonArray) paths.get("extension-domains"))
        validator.getExtensionDomains().add(((JsonPrimitive) e).getAsString());
    }
    if (configuration.has("jurisdiction")) {
      jurisdictions = new ArrayList<CodeableConcept>();
      for (String s : configuration.getAsJsonPrimitive("jurisdiction").getAsString().trim().split("\\,")) {
        CodeableConcept cc = new CodeableConcept();
        jurisdictions.add(cc);
        Coding c = cc.addCoding();
        String sc = s.trim();
        if (Utilities.isInteger(sc)) 
          c.setSystem("http://unstats.un.org/unsd/methods/m49/m49.htm").setCode(sc);
        else
          c.setSystem("urn:iso:std:iso:3166").setCode(sc);
        ValidationResult vr = context.validateCode(c, null);
        if (vr.getDisplay() != null)
          c.setDisplay(vr.getDisplay());
      }
    }
    if (configuration.has("suppressedWarningFile")) {
      String suppressPath = configuration.getAsJsonPrimitive("suppressedWarningFile").getAsString();
      if (!suppressPath.isEmpty())
        loadSuppressedMessages(Utilities.path(rootDir, suppressPath));
    }
    validationFetcher = new ValidationServices(context, igpkp, fileList, npmList );
    validator.setFetcher(validationFetcher);
    for (String s : context.getBinaries().keySet())
      if (needFile(s)) {
        if (makeQA)
          checkMakeFile(context.getBinaries().get(s), Utilities.path(qaDir, s), otherFilesStartup);
        checkMakeFile(context.getBinaries().get(s), Utilities.path(tempDir, s), otherFilesStartup);
      }
    otherFilesStartup.add(Utilities.path(tempDir, "_data"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "fhir.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "structuredefinitions.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_data", "pages.json"));
    otherFilesStartup.add(Utilities.path(tempDir, "_includes"));

    JsonArray urls = configuration.getAsJsonArray("special-urls");
    if (urls != null) {
      for (JsonElement url : urls) {
        listedURLExemptions.add(url.getAsString());
      }
    }
    includeHeadings = !configuration.has("includeHeadings") || configuration.get("includeHeadings").getAsBoolean();
    openApiTemplate = configuration.has("openapi-template") ? configuration.get("openapi-template").getAsString() : null;
    license = ostr(configuration, "license");
    htmlTemplate = configuration.has("html-template") ? str(configuration, "html-template") : null;
    mdTemplate = configuration.has("md-template") ? str(configuration, "md-template") : null;
    npmName = configuration.has("npm-name") ? configuration.get("npm-name").getAsString(): null;
    brokenLinksError = "error".equals(ostr(configuration, "broken-links"));
    nestedIgConfig = configuration.has("nestedIgConfig") ? configuration.get("nestedIgConfig").getAsString() : null;
    nestedIgOutput = configuration.has("nestedIgOutput") ? configuration.get("nestedIgOutput").getAsString() : null;
    igArtifactsPage = configuration.has("igArtifactsPage") ? configuration.get("igArtifactsPage").getAsString() : null;
    genExamples = "true".equals(ostr(configuration, "gen-examples"));
    doTransforms = "true".equals(ostr(configuration, "do-transforms"));
    appendTrailingSlashInDataFile = "true".equals(ostr(configuration, "append-slash-to-dependency-urls"));
    
    JsonArray array = configuration.getAsJsonArray("spreadsheets");
    if (array != null) {
      for (JsonElement be : array) 
        spreadsheets.add(be.getAsString());
    }
    array = configuration.getAsJsonArray("bundles");
    if (array != null) {
      for (JsonElement be : array) 
        bundles.add(be.getAsString());
    }
    JsonArray templates = configuration.getAsJsonArray("extraTemplates");
    if (templates!=null) {
      for (JsonElement template : templates) {
        if (template.isJsonPrimitive())
          extraTemplates.put(template.getAsString(), template.getAsString());
        else {
          if (!((JsonObject)template).has("name") || !((JsonObject)template).has("description"))
            throw new Exception("extraTemplates must be an array of objects with 'name' and 'description' properties");
          extraTemplates.put(((JsonObject)template).get("name").getAsString(), ((JsonObject)template).get("description").getAsString());
        }
      }
    }
    log("Initialization complete");
    // now, do regeneration
    JsonArray regenlist = configuration.getAsJsonArray("regenerate");
    if (regenlist != null)
      for (JsonElement regen : regenlist)
        regenList.add(((JsonPrimitive) regen).getAsString());

  }


  private void loadFromBuildServer() {
    log("Contacting Build Server...");
    try {
      buildInfo = pcm.loadFromBuildServer();
      log(" ... done");
    } catch (Throwable e) {
      buildInfo = null;
      log(" ... Running without information from build ("+e.getMessage()+")");
    }
  }


  void handlePreProcess(JsonObject pp, String root) throws Exception {
    String path = Utilities.path(root, str(pp, "folder"));
    checkDir(path);
    prePagesDirs.add(path);
    String prePagesXslt = null;
    if (pp.has("transform")) {
      prePagesXslt = Utilities.path(root, str(pp, "transform"));
      checkFile(prePagesXslt);
    }
    String relativePath = null;
    if (pp.has("relativePath")) {
      relativePath = str(pp, "relativePath");
    }
    PreProcessInfo ppinfo = new PreProcessInfo(prePagesXslt, relativePath);
    preProcessInfo.put(path, ppinfo);
  }

  private void loadSuppressedMessages(String messageFile) throws Exception {
    InputStreamReader r = new InputStreamReader(new FileInputStream(messageFile));
    StringBuilder b = new StringBuilder();
    while (r.ready()) {
      char c = (char) r.read();
      if (c == '\r' || c == '\n') {
        if (b.length() > 0)
          suppressedMessages.add(b.toString());
        b = new StringBuilder();
      } else
        b.append(c);
    }
    if (b.length() > 0)
      suppressedMessages.add(b.toString());
    r.close();
  }
  
  private void checkTSVersion(String dir, String version) throws FileNotFoundException, IOException {
    if (Utilities.noString(version))
      return;

    // we wipe the terminology cache if the terminology server cersion has changed
    File verFile = new File(Utilities.path(dir, "version.ctl"));
    if (verFile.exists()) {
      String ver = TextFile.fileToString(verFile);
      if (!ver.equals(version)) {
        log("Terminology Server Version has changed from "+ver+" to "+version+", so clearing txCache");
        Utilities.clearDirectory(dir);
      }
    }
    TextFile.stringToFile(version, verFile);
  }


  private int clearErrors(String dirName) throws FileNotFoundException, IOException {
    File dir = new File(dirName);
    int i = 0;
    for (File f : dir.listFiles()) {
      String s = TextFile.fileToString(f);
      if (s.contains("OperationOutcome")) {
        f.delete();
        i++;
      }
    }
    return i;
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

  private void copyDefaultTemplate() throws IOException, FHIRException {
    if (!new File(adHocTmpDir).exists())
      Utilities.createDirectory(adHocTmpDir);
    Utilities.clearDirectory(adHocTmpDir);

    PackageCacheManager pcm = new PackageCacheManager(true, ToolsVersion.TOOLS_VERSION);
    
    NpmPackage npm = null; 
    npm = pcm.loadPackage("hl7.fhir.core", Constants.VERSION);
    
    ZipInputStream zip = new ZipInputStream(npm.load("other", "ig-template.zip"));
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
  }

  private void buildConfigFile() throws IOException, org.hl7.fhir.exceptions.FHIRException, FHIRFormatError {
    configFile = Utilities.path(adHocTmpDir, "ig.json");
    // temporary config, until full ig template is in place
    String v = specifiedVersion != null ? specifiedVersion : Constants.VERSION; 
    String igs = v.equals("3.0.1") ? "ig3.xml" : "ig4.xml";
    TextFile.stringToFile(
            "{\r\n"+
            "  \"tool\" : \"jekyll\",\r\n"+
            "  \"canonicalBase\" : \"http://hl7.org/fhir/ig\",\r\n"+
            "  \"npm-name\" : \"hl7.fhir.test.ig\",\r\n"+
            "  \"license\" : \"not-open-source\",\r\n"+
            "  \"version\" : \""+v+"\",\r\n"+
            "  \"resources\" : {},\r\n"+
            "  \"paths\" : {\r\n"+
            "    \"resources\" : \"resources\",\r\n"+
            "    \"pages\" : \"pages\",\r\n"+
            "    \"temp\" : \"temp\",\r\n"+
            "    \"qa\" : \"qa\",\r\n"+
            "    \"output\" : \"output\",\r\n"+
            "    \"specification\" : \"http://build.fhir.org/\"\r\n"+
            "  },\r\n"+
            "  \"sct-edition\": \"http://snomed.info/sct/900000000000207008\",\r\n"+
            "  \"source\": \""+igs+"\"\r\n"+
            "}\r\n", configFile, false);
    Utilities.createDirectory(Utilities.path(adHocTmpDir, "resources"));
    Utilities.createDirectory(Utilities.path(adHocTmpDir, "pages"));
  }

  private SpecificationPackage loadCorePackage() throws Exception {
    NpmPackage pi = null;
    
    String v = version.equals(Constants.VERSION) ? "current" : version;

    if (Utilities.noString(igPack)) {
      pi = pcm.loadPackage("hl7.fhir.core", v);
    } else
      pi = pcm.extractLocally(igPack);
    if (pi == null) {
      String url = getMasterSource();
      InputStream src = fetchFromSource("hl7.fhir.core-"+v, url);
      pi = pcm.addPackageToCache("hl7.fhir.core", v, src);
    }
    if (v.equals("current")) {
      // currency of the current core package is a problem, since its not really version controlled.
      // we'll check for a specified version...
      log("Checking hl7.fhir.core-"+v+" currency");
      int cacheVersion = getBuildVersionForCorePackage(pi);
      int lastAcceptableVersion = ToolsVersion.TOOLS_VERSION;
      if (cacheVersion < lastAcceptableVersion) {
        log("Updating hl7.fhir.core-"+version+" package from source (too old - is "+cacheVersion+", must be "+lastAcceptableVersion);
        pi = pcm.addPackageToCache("hl7.fhir.core", "current", fetchFromSource("hl7.fhir.core-"+v, getMasterSource()));
      } else {
        log("   ...  ok: is "+cacheVersion+", must be "+lastAcceptableVersion);
      }
    }
    log("Load hl7.fhir.core-"+v+" package from "+pi.description());
    if (pi != null)
      npmList.add(pi);
    return loadPack(pi);
  }


  private int getBuildVersionForCorePackage(NpmPackage pi) throws IOException {
    if (!pi.getNpm().has("tools-version"))
      return 0;
    return pi.getNpm().get("tools-version").getAsInt();
  }


  private String getMasterSource() {
    if ("1.0.2".equals(version)) return "http://hl7.org/fhir/DSTU2/package.tgz";
    if ("1.4.0".equals(version)) return "http://hl7.org/fhir/2016May/package.tgz";
    if ("3.0.1".equals(version)) return "http://hl7.org/fhir/STU3/package.tgz";
    if (Constants.VERSION.equals(version)) return "http://build.fhir.org/package.tgz";
    throw new Error("unknown version "+version);
  }

  private InputStream fetchFromSource(String id, String source) throws IOException {
    log("Fetch "+id+" package from "+source);
    URL url = new URL(source+"?nocache=" + System.currentTimeMillis());
    URLConnection c = url.openConnection();
    return c.getInputStream();
  }


  private SpecificationPackage loadPack(NpmPackage pi) throws Exception {  
    packge = pi;
    SpecificationPackage sp = null;
    if ("1.0.2".equals(version)) {
      sp = SpecificationPackage.fromPackage(pi, new R2ToR5Loader());
    } else if ("1.4.0".equals(version)) {
      sp = SpecificationPackage.fromPackage(pi, new R2016MayToR5Loader());
    } else if ("3.0.1".equals(version)) {
      sp = SpecificationPackage.fromPackage(pi, new R3ToR5Loader());
    } else if ("4.0.0".equals(version)) {
      sp = SpecificationPackage.fromPackage(pi, new R4ToR5Loader());
    } else 
      sp = SpecificationPackage.fromPackage(pi);
    sp.loadOtherContent(pi);
    
    if (!version.equals(Constants.VERSION)) {
      // If it wasn't a 4.0 source, we need to set the ids because they might not have been set in the source
      ProfileUtilities utils = new ProfileUtilities(context, new ArrayList<ValidationMessage>(), igpkp);
      for (StructureDefinition sd : sp.makeContext().allStructures()) {
        utils.setIds(sd, true);
      }
    }
    return sp;
  }
  
  private Parameters makeExpProfile() {
    Parameters ep  = new Parameters();
    ep.addParameter("profile-url", "dc8fd4bc-091a-424a-8a3b-6198ef146891"); // change this to blow the cache
    // all defaults....
    return ep;
  }

  private void loadIg(JsonObject dep) throws Exception {
    String name = str(dep, "name");
    if (!Utilities.isToken(name))
      throw new Exception("IG Name must be a valid token ("+name+")");
    String canonical = ostr(dep, "location");
    String igver = ostr(dep, "version");
    if (Utilities.noString(igver))
      throw new Exception("You must specify a version for the IG "+name+" ("+canonical+")");
    String packageId = ostr(dep, "package");
    if (Utilities.noString(packageId))
      packageId = pcm.getPackageId(canonical);
    if (Utilities.noString(canonical) && !Utilities.noString(packageId))
      canonical = pcm.getPackageUrl(packageId);
    if (Utilities.noString(canonical))
      throw new Exception("You must specify a canonical URL for the IG "+name);
    
    NpmPackage pi = packageId == null ? null : pcm.loadPackageFromCacheOnly(packageId, igver);
    if (pi != null)
      npmList.add(pi);
    if (pi == null) {
      pi = resolveDependency(canonical, packageId, igver);
      if (pi == null) {
        if (Utilities.noString(packageId))
          throw new Exception("Package Id for guide at "+canonical+" is unknown (contact FHIR Product Director");
        else
          throw new Exception("Unknown Package "+packageId+"#"+igver);
      }
    }
    log("Load "+name+" ("+canonical+") from "+packageId+"#"+igver);
    if (ostr(dep, "package") == null && packageId != null)
      dep.addProperty("package", packageId);

    String webref = pi.getWebLocation();
    String location = dep.has("location") ? dep.get("location").getAsString() : ""; 
    if (location.startsWith(".."))
      webref = location;
    
    SpecMapManager igm = new SpecMapManager(TextFile.streamToBytes(pi.load("other", "spec.internals")), pi.getNpm().getAsJsonObject("dependencies").get("hl7.fhir.core").getAsString());
    igm.setName(name);
    igm.setBase(canonical);
    specMaps.add(igm);
    if (!version.equals(igm.getVersion())) {
      log("Version mismatch. This IG is version "+version+", while the IG '"+name+"' is from version "+igm.getVersion()+" (will try to run anyway)");
    }
    
    for (String fn : pi.list("package")) {
      if (fn.endsWith(".json") && fn.contains("-")) {
        Resource r = null;
        String t = fn.substring(0, fn.indexOf("-"));
        if (Utilities.existsInList(t, "StructureDefinition", "ValueSet", "CodeSystem", "SearchParameter", "OperationDefinition", "Questionnaire","ConceptMap","StructureMap", "NamingSystem")) {
          if (igm.getVersion().equals("3.0.1") || igm.getVersion().equals("3.0.0")) {
            org.hl7.fhir.dstu3.model.Resource res = new org.hl7.fhir.dstu3.formats.JsonParser().parse(pi.load("package", fn));
            r = VersionConvertor_30_50.convertResource(res, true);
          } else if (igm.getVersion().equals("4.0.0")) {
            org.hl7.fhir.r4.model.Resource res = new org.hl7.fhir.r4.formats.JsonParser().parse(pi.load("package", fn));
            r = VersionConvertor_40_50.convertResource(res);
          } else if (igm.getVersion().equals("1.4.0")) {
            org.hl7.fhir.dstu2016may.model.Resource res = new org.hl7.fhir.dstu2016may.formats.JsonParser().parse(pi.load("package", fn));
            r = VersionConvertor_14_50.convertResource(res);
          } else if (igm.getVersion().equals("1.0.2")) {
            org.hl7.fhir.dstu2.model.Resource res = new org.hl7.fhir.dstu2.formats.JsonParser().parse(pi.load("package", fn));
            VersionConvertorAdvisor50 advisor = new IGR2ConvertorAdvisor5();
            r = new VersionConvertor_10_50(advisor ).convertResource(res);
          } else if (igm.getVersion().equals(Constants.VERSION)) {
            r = new JsonParser().parse(pi.load("package", fn));
          } else
            throw new Exception("Unsupported version "+igm.getVersion());
        }
        if (r != null) {
          if (r instanceof MetadataResource) {
            String u = ((MetadataResource) r).getUrl();
            if (u != null) {
              String p = igm.getPath(u);
              if (p == null)
                throw new Exception("Internal error in IG "+name+" map: No identity found for "+u);
              r.setUserData("path", webref+"/"+ igpkp.doReplacements(p, r, null, null));
              String v = ((MetadataResource) r).getVersion();
              if (v!=null) {
                u = u + "|" + v;
                p = igm.getPath(u);
                if (p == null)
                  System.out.println("In IG "+name+" map: No identity found for "+u);
                r.setUserData("versionpath", canonical+"/"+ igpkp.doReplacements(p, r, null, null));
              }
            }
            context.cacheResource(r);
          }
        }
      } 
    }
  }


  private NpmPackage resolveDependency(String canonical, String packageId, String igver) throws Exception {
    if (packageId != null) 
      return pcm.loadPackage(packageId, igver);
    
    JsonObject pl;
    System.out.println("Fetch Package history from "+Utilities.pathURL(canonical, "package-list.json"));
    pl = fetchJson(Utilities.pathURL(canonical, "package-list.json"));
    if (!canonical.equals(pl.get("canonical").getAsString()))
      throw new Exception("Canonical mismatch fetching package list for "+igver);
    for (JsonElement e : pl.getAsJsonArray("list")) {
      JsonObject o = (JsonObject) e;
      if (igver.equals(o.get("version").getAsString())) {
        InputStream src = fetchFromSource(pl.get("package-id").getAsString()+"-"+igver, Utilities.pathURL(o.get("path").getAsString(), "package.tgz"));
        return pcm.addPackageToCache(pl.get("package-id").getAsString(), igver, src);
      }
    }
    return null;
  }

  private JsonObject fetchJson(String source) throws IOException {
    URL url = new URL(source+"?nocache=" + System.currentTimeMillis());
    URLConnection c = url.openConnection();
    return JsonTrackingParser.parseJson(c.getInputStream());
  }


  private Map<String, byte[]> fetchDefinitions(String source, String name) throws Exception {
    // todo: if filename is a URL
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    String filename;
    if (source.startsWith("http:") || source.startsWith("https:"))
      filename = fetchFromURL(source, name);
    else if (Utilities.isAbsoluteFileName(source))
      filename = Utilities.path(source, "validator.pack");
    else 
      filename = Utilities.path(fetcher.pathForFile(configFile), source, "validator.pack");
    ZipInputStream zip = null;
    InputStream stream = fetcher.openAsStream(filename);
    zip = new ZipInputStream(stream);
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

    if (!source.endsWith("validator.pack"))
      source = Utilities.pathURL(source, "validator.pack");
    try {
      URL url = new URL(source+"?nocache=" + System.currentTimeMillis());
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
    FetchState state = fetcher.check(dir);
    if (state == FetchState.NOT_FOUND)
      throw new Exception(String.format("Error: folder %s not found", dir));
    else if (state == FetchState.FILE)
      throw new Exception(String.format("Error: Output must be a folder (%s)", dir));
  }

  private void checkFile(String fn) throws Exception {
    FetchState state = fetcher.check(fn);
    if (state == FetchState.NOT_FOUND)
      throw new Exception(String.format("Error: file %s not found", fn));
    else if (state == FetchState.DIR)
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
    dlog(LogCategory.PROGRESS, "Check Generate "+path);
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
    if (s.endsWith(".css") && !isChild())
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
    SpecMapManager map = new SpecMapManager(bs, version);
    map.setBase(specPath);
    specMaps.add(map);
  }


  private boolean load() throws Exception {
    validationFetcher.initOtherUrls();
    fileList.clear();
    changeList.clear();
    bndIds.clear();
    boolean needToBuild = false;
    FetchedFile igf = fetcher.fetch(igName);
    needToBuild = noteFile(IG_NAME, igf) || needToBuild;
    if (needToBuild) {
      sourceIg = (ImplementationGuide) parse(igf);
      FetchedResource igr = igf.addResource();
      loadAsElementModel(igf, igr);
      igr.setResource(sourceIg);
      igr.setId(sourceIg.getId()).setTitle(sourceIg.getName());
    } else {
      // special case; the source is updated during the build, so we track it differently
      altMap.get(IG_NAME).getResources().get(0).setResource(sourceIg);
    }
    validationFetcher.getOtherUrls().add(sourceIg.getUrl());
    for (SpecMapManager s :  specMaps) {
      validationFetcher.getOtherUrls().add(s.getBase());
    }

    if (npmName == null)
      throw new Exception("A package name (npm-name) is required to publish implementation guides. For further information, see http://wiki.hl7.org/index.php?title=FHIR_NPM_Package_Spec#Package_name");
    publishedIg = sourceIg.copy();
    if (!publishedIg.hasLicense())
      publishedIg.setLicense(licenseAsEnum());
    if (!publishedIg.hasPackageId())
      publishedIg.setPackageId(npmName);
    if (!publishedIg.hasFhirVersion())
      publishedIg.addFhirVersion(FHIRVersion.fromCode(version));
    if (!publishedIg.hasVersion() && businessVersion != null)
      publishedIg.setVersion(businessVersion);
    if (publishedIg.hasPackageId())
      pcm.recordMap(igpkp.getCanonical(), publishedIg.getPackageId());
    
    String id = npmName+"-"+businessVersion;
    if (npmName.startsWith("hl7.")) {
      if (!id.matches("[A-Za-z0-9\\-\\.]{1,64}"))
        throw new FHIRException("The generated ID is '"+id+"' which is not valid");
      publishedIg.setId(id);
    } else if (!id.equals(publishedIg.getId()))
      errors.add(new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, "ImplementationGuide.id", "The Implementation Guide Resource id should be "+id, IssueSeverity.WARNING));
      
    // load any bundles
    if (sourceDir != null || igpkp.isAutoPath())
      needToBuild = loadResources(needToBuild, igf);
    needToBuild = loadSpreadsheets(needToBuild, igf);
    needToBuild = loadBundles(needToBuild, igf);
    for (ImplementationGuideDefinitionResourceComponent res : sourceIg.getDefinition().getResource()) {
      if (!res.hasReference())
        throw new Exception("Missing source reference on "+res.getName());
      if (!bndIds.contains(res.getReference().getReference())) {
        FetchedFile f = fetcher.fetch(res.getReference(), igf);
        boolean rchanged = noteFile(res, f);
        needToBuild = rchanged || needToBuild;
        if (rchanged) 
          loadAsElementModel(f, f.addResource());
        if (res.hasExampleCanonicalType()) {
          if (f.getResources().size()!=1)
            throw new Exception("Can't have an exampleFor unless the file has exactly one resource");
          FetchedResource r = f.getResources().get(0);
          examples.add(r);
          String ref = res.getExampleCanonicalType().getValueAsString();
          if (ref.contains(":")) {
            r.setExampleUri(ref);
          } else if (sourceIg.getUrl().contains("ImplementationGuide/"))
            r.setExampleUri(sourceIg.getUrl().substring(0, sourceIg.getUrl().indexOf("ImplementationGuide/")) + ref);
          else
            r.setExampleUri(Utilities.pathURL(sourceIg.getUrl(), ref));
        }
      }
    }

    // load static pages
    needToBuild = loadPrePages() || needToBuild;
    needToBuild = loadPages() || needToBuild;

    if (sourceIg.getDefinition().hasPage())
      loadIgPages(sourceIg.getDefinition().getPage(), igPages);

    for (FetchedFile f: fileList) {
      for (FetchedResource r: f.getResources()) {
        resources.put(igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null), r);
      }
    }
    
    for (ImplementationGuideDependsOnComponent dep : publishedIg.getDependsOn()) {
      if (!dep.hasPackageId()) {
        dep.setPackageId(pcm.getPackageId(dep.getUri()));
      }
      if (!dep.hasPackageId()) 
        throw new FHIRException("Unknown package id for "+dep.getUri());
    }
    npm = new NPMPackageGenerator(Utilities.path(outputDir, "package.tgz"), igpkp.getCanonical(), targetUrl(), PackageType.IG,  publishedIg, genTime());
    execTime = Calendar.getInstance();

    gen = new NarrativeGenerator("", "", context, this).setLiquidServices(templateProvider, validator.getExternalHostServices());
    gen.setCorePath(checkAppendSlash(specPath));
    gen.setDestDir(Utilities.path(tempDir));
    gen.setPkp(igpkp);

    return needToBuild;
  }

  private String targetUrl() {
    if (mode == null)
      return "file://"+outputDir;
    switch (mode) {
    case AUTOBUILD: return targetOutput == null ? "https://build.fhir.org/ig/[org]/[repo]" : targetOutput;
    case MANUAL: return "file://"+outputDir;
    case WEBSERVER: return "http://unknown";
    case PUBLICATION: return targetOutput;
    default: return igpkp.getCanonical();
    }
  }


  private void loadTemplate(JsonObject configuration, String src) throws Exception {
    log("Fetch Template: "+src);
    NpmPackage template = templateManager.fetch(src);
    if (!template.isType(PackageType.TEMPLATE))
      throw new Exception("The referenced package '"+src+"' does not have the correct type - is "+template.type()+" but should be a template");
    String templateDir = Utilities.path(rootDir, "template");
    Utilities.createDirectory(templateDir);
    Utilities.clearDirectory(templateDir);
    template.copyTo("template", templateDir);
    List<String> files = new ArrayList<String>();
    copyFiles(Utilities.path(templateDir, "jekyll"), Utilities.path(templateDir, "jekyll"), tempDir, files); 
    for (String s : files)
      otherFilesStartup.add(Utilities.path(tempDir, s)); 
    JsonObject tc = JsonTrackingParser.parseJson(template.load("template", "config.json"));
    new JsonMerger().merge(configuration, tc);
    templateLoaded = true;
    templatePck = template.name();
  }


  private void copyFiles(String base, String folder, String dest, List<String> files) throws IOException {
    for (File f : new File(folder).listFiles()) {
      if (f.isDirectory()) {
        copyFiles(base, f.getAbsolutePath(),  Utilities.path(dest, f.getName()), files);
      } else {
        String dst = Utilities.path(dest, f.getName());
        FileUtils.copyFile(f, new File(dst), true);
        files.add(f.getAbsolutePath().substring(base.length()+1));
      }
    } 
  }


  private void templateBeforeGenerate() throws IOException {
   //
   // load it into temp
   // if it as an initial any file, let it run 
   // load template configuration for templates / defaults
   if (debug)
     waitForInput("before-generate");
  }

  private void templateAfterGenerate() throws IOException {
    if (debug)
      waitForInput("before-generate");
  }
  
  private void waitForInput(String string) throws IOException {
    System.out.print("At point '"+string+"' - press enter to continue: ");
    while (System.in.read() != 10) {};
  }


  private void loadIgPages(ImplementationGuideDefinitionPageComponent page, HashMap<String, ImplementationGuideDefinitionPageComponent> map) throws FHIRException {
    if (page.hasName() && page.hasNameUrlType())
      map.put(page.getNameUrlType().getValue(), page);
    for (ImplementationGuideDefinitionPageComponent childPage: page.getPage()) {
      loadIgPages(childPage, map);
    }
  }

  private boolean loadPrePages() throws Exception {
    boolean changed = false;
    if (prePagesDirs.isEmpty())
      return false;

    for (String prePagesDir : prePagesDirs) {
      FetchedFile dir = fetcher.fetch(prePagesDir);
      dir.setRelativePath("");
      if (!dir.isFolder())
        throw new Exception("pre-processed page reference is not a folder");
      if (loadPrePages(dir, dir.getPath()))
        changed = true;
    }
    return changed;
  }

  private boolean loadPrePages(FetchedFile dir, String basePath) throws Exception {
    boolean changed = false;
    PreProcessInfo ppinfo = preProcessInfo.get(basePath);
    if (!altMap.containsKey("pre-page/"+dir.getPath())) {
      changed = true;
      altMap.put("pre-page/"+dir.getPath(), dir);
      dir.setProcessMode(ppinfo.hasXslt() ? FetchedFile.PROCESS_XSLT : FetchedFile.PROCESS_NONE);
      dir.setXslt(ppinfo.getXslt());
      if (ppinfo.hasRelativePath()) {
        if (dir.getRelativePath().isEmpty())
          dir.setRelativePath(ppinfo.getRelativePath());
        else
          dir.setRelativePath(ppinfo.getRelativePath() + File.separator + dir.getRelativePath());

      }
      addFile(dir);
    }
    for (String link : dir.getFiles()) {
      FetchedFile f = fetcher.fetch(link);
      f.setRelativePath(f.getPath().substring(basePath.length()+1));
      if (f.isFolder())
        changed = loadPrePages(f, basePath) || changed;
      else
        changed = loadPrePage(f, ppinfo) || changed;
    }
    return changed;
  }

  private boolean loadPrePage(FetchedFile file, PreProcessInfo ppinfo) {
    FetchedFile existing = altMap.get("pre-page/"+file.getPath());
    if (existing == null || existing.getTime() != file.getTime() || existing.getHash() != file.getHash()) {
      file.setProcessMode(ppinfo.hasXslt() && !file.getPath().endsWith(".md") ? FetchedFile.PROCESS_XSLT : FetchedFile.PROCESS_NONE);
      file.setXslt(ppinfo.getXslt());
      if (ppinfo.hasRelativePath())
        file.setRelativePath(ppinfo.getRelativePath() + File.separator + file.getRelativePath());
      addFile(file);
      altMap.put("pre-page/"+file.getPath(), file);
      return true;
    } else {
      return false;
    }
  }

  private boolean loadPages() throws Exception {
    boolean changed = false;
    for (String pagesDir: pagesDirs) {
      FetchedFile dir = fetcher.fetch(pagesDir);
      dir.setRelativePath("");
      if (!dir.isFolder())
        throw new Exception("page reference is not a folder");
      if (loadPages(dir, dir.getPath()))
        changed = true;
    }
    return changed;
  }

  private boolean loadPages(FetchedFile dir, String basePath) throws Exception {
    boolean changed = false;
    if (!altMap.containsKey("page/"+dir.getPath())) {
      changed = true;
      altMap.put("page/"+dir.getPath(), dir);
      dir.setProcessMode(FetchedFile.PROCESS_NONE);
      addFile(dir);
    }
    for (String link : dir.getFiles()) {
      FetchedFile f = fetcher.fetch(link);
      f.setRelativePath(f.getPath().substring(basePath.length()+1));
      if (f.isFolder())
        changed = loadPages(f, basePath) || changed;
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
    for (String be : bundles) {
      needToBuild = loadBundle(be, needToBuild, igf);
    }
    return needToBuild;
  }

  private boolean loadBundle(String name, boolean needToBuild, FetchedFile igf) throws Exception {
    FetchedFile f = fetcher.fetch(new Reference().setReference("Bundle/"+name), igf);
    boolean changed = noteFile("Bundle/"+name, f);
    if (changed) {
      f.setBundle(new FetchedResource());
      f.setBundleType(FetchedBundleType.NATIVE);
      loadAsElementModel(f, f.getBundle());
      List<Element> entries = new ArrayList<Element>();
      f.getBundle().getElement().getNamedChildren("entry", entries);
      for (Element bnde : entries) {
        Element res = bnde.getNamedChild("resource"); 
        FetchedResource r = f.addResource();
        r.setElement(res);
        r.setId(res.getIdBase());
        List<Element> profiles = new ArrayList<Element>();
        Element meta = res.getNamedChild("meta");
        if (meta != null)
          meta.getNamedChildren("profile", profiles);
        for (Element p : profiles)
          r.getProfiles().add(p.primitiveValue());
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else
      f = altMap.get("Bundle/"+name);
    ImplementationGuideDefinitionGroupingComponent pck = publishedIg.getDefinition().addGrouping().setName(f.getTitle());
    pck.setId(name);
    for (FetchedResource r : f.getResources()) {
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
      ImplementationGuideDefinitionResourceComponent res = publishedIg.getDefinition().addResource();
      res.setGroupingId(pck.getId());
      res.setName(r.getId()).setReference(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
    }
    return changed || needToBuild;
  }

  private boolean loadResources(boolean needToBuild, FetchedFile igf) throws Exception { // igf is not currently used, but it was about relative references? 
    List<FetchedFile> resources = fetcher.scan(sourceDir, context, igpkp.isAutoPath());
    for (FetchedFile ff : resources) {
      if (!ff.matches(igf))
        needToBuild = loadResource(needToBuild, ff);
    }
    return needToBuild;
  }

  private boolean loadResource(boolean needToBuild, FetchedFile f) throws Exception {
    dlog(LogCategory.PROGRESS, "load "+f.getPath());
    boolean changed = noteFile(f.getPath(), f);
    if (changed) {
      loadAsElementModel(f, f.addResource());
    }
    for (FetchedResource r : f.getResources()) {
      ImplementationGuideDefinitionResourceComponent res = publishedIg.getDefinition().addResource();
      res.setName(r.getTitle()).setReference(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
    }
    return changed || needToBuild;
  }


  private boolean loadSpreadsheets(boolean needToBuild, FetchedFile igf) throws Exception {
    Set<String> knownValueSetIds = new HashSet<>();
    for (String s : spreadsheets) {
      needToBuild = loadSpreadsheet(s, needToBuild, igf, knownValueSetIds);
    }
    return needToBuild;
  }

  private boolean loadSpreadsheet(String name, boolean needToBuild, FetchedFile igf, Set<String> knownValueSetIds) throws Exception {
    if (name.startsWith("!"))
      return false;

    FetchedFile f = fetcher.fetchResourceFile(name);
    boolean changed = noteFile("Spreadsheet/"+name, f);
    if (changed) {
      f.getValuesetsToLoad().clear();
      dlog(LogCategory.PROGRESS, "load "+f.getPath());
      Bundle bnd = new IgSpreadsheetParser(context, execTime, igpkp.getCanonical(), f.getValuesetsToLoad(), first, context.getBinaries().get("mappingSpaces.details"), knownValueSetIds).parse(f);
      f.setBundle(new FetchedResource());
      f.setBundleType(FetchedBundleType.SPREADSHEET);
      f.getBundle().setResource(bnd);
      for (BundleEntryComponent b : bnd.getEntry()) {
        FetchedResource r = f.addResource();
        r.setResource(b.getResource());
        r.setId(b.getResource().getId());
        r.setElement(convertToElement(r.getResource()));
        r.setTitle(r.getElement().getChildValue("name"));
        igpkp.findConfiguration(f, r);
      }
    } else {
      f = altMap.get("Spreadsheet/"+name);
    }

    for (String id : f.getValuesetsToLoad().keySet()) {
      if (!knownValueSetIds.contains(id)) {
        String vr = f.getValuesetsToLoad().get(id);
        FetchedFile fv = fetcher.fetchFlexible(vr);
        boolean vrchanged = noteFile("sp-ValueSet/"+vr, fv);
        if (vrchanged) {
          loadAsElementModel(fv, fv.addResource());
          checkImplicitResourceIdentity(id, fv);
        }
        knownValueSetIds.add(id);
        // ok, now look for an implicit code system with the same name
        boolean crchanged = false;
        String cr = vr.replace("valueset-", "codesystem-");
        if (!cr.equals(vr)) {
          if (fetcher.canFetchFlexible(cr)) {
            fv = fetcher.fetchFlexible(cr);
            crchanged = noteFile("sp-CodeSystem/"+vr, fv);
            if (crchanged) {
              loadAsElementModel(fv, fv.addResource());
              checkImplicitResourceIdentity(id, fv);
            }
          }
        }
        changed = changed || vrchanged || crchanged;
      }
    }
    ImplementationGuideDefinitionGroupingComponent pck = publishedIg.getDefinition().addGrouping().setName(f.getTitle());
    pck.setId(name);
    for (FetchedResource r : f.getResources()) {
      bndIds.add(r.getElement().fhirType()+"/"+r.getId());
      ImplementationGuideDefinitionResourceComponent res = publishedIg.getDefinition().addResource();
      res.setGroupingId(pck.getId());
      res.setName(r.getTitle()).setReference(new Reference().setReference(r.getElement().fhirType()+"/"+r.getId()));
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
    scan("NamingSystem");
    scan("CodeSystem");
    scan("ValueSet");
    scan("ConceptMap");
    scan("DataElement");
    scan("StructureDefinition");
    scan("OperationDefinition");
    scan("CapabilityStatement");
    scan("Questionnaire");
    scan("PlanDefinition");
    
    load("NamingSystem");
    load("CodeSystem");
    load("ValueSet");
    load("ConceptMap");
    load("DataElement");
    load("StructureDefinition");
    load("OperationDefinition");
    load("CapabilityStatement");
    load("Questionnaire");
    load("PlanDefinition");
    generateSnapshots();
    checkConformanceResources();
    generateLogicalMaps();
    load("StructureMap");
    generateAdditionalExamples();
    executeTransforms();
    validateExpressions();
  }

  private void checkConformanceResources() {
    log(LogCategory.PROGRESS, "check profiles");
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          log(LogCategory.PROGRESS, "process profile: "+r.getId());
          StructureDefinition sd = (StructureDefinition) r.getResource();
          f.getErrors().addAll(pvalidator.validate(sd, false));
        }
        if (r.getElement().fhirType().equals("CodeSystem")) {
          log(LogCategory.PROGRESS, "process CodeSystem: "+r.getId());
          CodeSystem cs = (CodeSystem) r.getResource();
          f.getErrors().addAll(csvalidator.validate(cs, false));
        }
      }
    }
  }


  private void executeTransforms() throws FHIRException, Exception {
    if (doTransforms) {
      MappingServices services = new MappingServices(context, igpkp.getCanonical());
      StructureMapUtilities utils = new StructureMapUtilities(context, services, igpkp);

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

  private boolean noteFile(ImplementationGuideDefinitionResourceComponent key, FetchedFile file) {
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
  	  if (file.getRelativePath()!=null)
  	    relativeNames.put(file.getRelativePath(), file);
  	  changeList.add(file);
//  	}
  }

  private void loadAsElementModel(FetchedFile file, FetchedResource r) throws Exception {
    file.getErrors().clear();
    Element e = null;

    try {        
      if (file.getContentType().contains("json"))
        e = loadFromJson(file);
      else if (file.getContentType().contains("xml")) {
        e = loadFromXml(file);
      } else
        throw new Exception("Unable to determine file type for "+file.getName());
    } catch (Exception ex) {
      throw new Exception("Unable to parse "+file.getName()+": " +ex.getMessage(), ex);
    }
    if (e != null) {
      try {
        boolean altered = false;

        String id = e.getChildValue("id");
        if (Utilities.noString(id)) {
          if (e.hasChild("url")) {
            String url = e.getChildValue("url");
            String prefix = Utilities.pathURL(igpkp.getCanonical(), e.fhirType())+"/";
            if (url.startsWith(prefix)) {
              id = e.getChildValue("url").substring(prefix.length());
              e.setChildValue("id", id);
              altered = true;
            } 
            if (Utilities.noString(id)) {
              throw new Exception("Resource has no id in "+file.getPath());
            }
          }
        }
        r.setElement(e).setId(id);
        igpkp.findConfiguration(file, r);

        String ver = r.getConfig() == null ? null : ostr(r.getConfig(), "version");
        if (ver == null)
          ver = version; // fall back to global version

        // version check: for some conformance resources, they may be saved in a different vrsion from that stated for the IG. 
        // so we might need to convert them prior to loading. Note that this is different to the conversion below - we need to 
        // convert to the current version. Here, we need to convert to the stated version. Note that we need to do this after
        // the first load above because above, we didn't have enough data to get the configuration, but we do now. 
        if (!ver.equals(version)) {
          //          System.out.println("Need to do version conversion on "+r.getElement().fhirType()+" from "+ver+" to "+version);
          if (file.getContentType().contains("json"))
            e = loadFromJsonWithVersionChange(file, ver, version);
          else if (file.getContentType().contains("xml"))
            e = loadFromXmlWithVersionChange(file, ver, version);
          else
            throw new Exception("Unable to determine file type for "+file.getName());
          r.setElement(e);
        }

        r.setTitle(e.getChildValue("name"));
        Element m = e.getNamedChild("meta");
        if (m != null) {
          List<Element> profiles = m.getChildrenByName("profile");
          for (Element p : profiles)
            r.getProfiles().add(p.getValue());
        }
        if ("1.0.1".equals(ver)) {
          file.getErrors().clear();
          org.hl7.fhir.dstu2.model.Resource res2 = null;
          if (file.getContentType().contains("json"))
            res2 = new org.hl7.fhir.dstu2.formats.JsonParser().parse(file.getSource());
          else if (file.getContentType().contains("xml"))
            res2 = new org.hl7.fhir.dstu2.formats.XmlParser().parse(file.getSource());
          org.hl7.fhir.r5.model.Resource res = new VersionConvertor_10_50(null).convertResource(res2);
          e = convertToElement(res);
          r.setElement(e).setId(id).setTitle(e.getChildValue("name"));
          r.setResource(res);
        }
        if ((altered && r.getResource() != null) || (ver.equals(Constants.VERSION) && r.getResource() == null))
          r.setResource(new ObjectConverter(context).convert(r.getElement()));
        if ((altered && r.getResource() == null))
          if (file.getContentType().contains("json"))
            saveToJson(file, e);
          else if (file.getContentType().contains("xml"))
            saveToXml(file, e);
      } catch ( Exception ex ) {
        throw new Exception("Unable to determine type for  "+file.getName()+": " +ex.getMessage(), ex);
      }
    }
  }

  private Element loadFromXml(FetchedFile file) throws Exception {
    org.hl7.fhir.r5.elementmodel.XmlParser xp = new org.hl7.fhir.r5.elementmodel.XmlParser(context);
    xp.setAllowXsiLocation(true);
    xp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors());
    Element res = xp.parse(new ByteArrayInputStream(file.getSource()));
    if (res == null)
      throw new Exception("Unable to parse XML for "+file.getName());
    return res;
  }

  private Element loadFromJson(FetchedFile file) throws Exception {
    org.hl7.fhir.r5.elementmodel.JsonParser jp = new org.hl7.fhir.r5.elementmodel.JsonParser(context);
    jp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors());
    return jp.parse(new ByteArrayInputStream(file.getSource()));
  }

  private void saveToXml(FetchedFile file, Element e) throws Exception {
    org.hl7.fhir.r5.elementmodel.XmlParser xp = new org.hl7.fhir.r5.elementmodel.XmlParser(context);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    xp.compose(e, bs, OutputStyle.PRETTY, null);
    file.setSource(bs.toByteArray());
  }

  private void saveToJson(FetchedFile file, Element e) throws Exception {
    org.hl7.fhir.r5.elementmodel.JsonParser jp = new org.hl7.fhir.r5.elementmodel.JsonParser(context);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    jp.compose(e, bs, OutputStyle.PRETTY, null);
    file.setSource(bs.toByteArray());
  }

  private Element loadFromXmlWithVersionChange(FetchedFile file, String srcV, String dstV) throws Exception {
    InputStream src = new ByteArrayInputStream(file.getSource());
    ByteArrayOutputStream dst = new ByteArrayOutputStream();
    if ("3.0.1".equals(srcV) && "1.4.0".equals(dstV)) {
      org.hl7.fhir.dstu3.model.Resource r3 = new org.hl7.fhir.dstu3.formats.XmlParser().parse(src);
      org.hl7.fhir.dstu2016may.model.Resource r14 = VersionConvertor_14_30.convertResource(r3);
      new org.hl7.fhir.dstu2016may.formats.XmlParser().compose(dst, r14);
    } else if ("3.0.1".equals(srcV) && Constants.VERSION.equals(dstV)) {
      org.hl7.fhir.dstu3.model.Resource r3 = new org.hl7.fhir.dstu3.formats.XmlParser().parse(src);
      org.hl7.fhir.r5.model.Resource r5 = VersionConvertor_30_50.convertResource(r3, false);
      new org.hl7.fhir.r5.formats.XmlParser().compose(dst, r5);
    } else if ("4.0.0".equals(srcV) && Constants.VERSION.equals(dstV)) {
      org.hl7.fhir.r4.model.Resource r4 = new org.hl7.fhir.r4.formats.XmlParser().parse(src);
      org.hl7.fhir.r5.model.Resource r5 = VersionConvertor_40_50.convertResource(r4);
      new org.hl7.fhir.r5.formats.XmlParser().compose(dst, r5);
    } else 
      throw new Exception("Conversion from "+srcV+" to "+dstV+" is not supported yet"); // because the only know reason to do this is 3.0.1 --> 1.40
    org.hl7.fhir.r5.elementmodel.XmlParser xp = new org.hl7.fhir.r5.elementmodel.XmlParser(context);
    xp.setAllowXsiLocation(true);
    xp.setupValidation(ValidationPolicy.EVERYTHING, file.getErrors());
    file.getErrors().clear();
    Element res = xp.parse(new ByteArrayInputStream(dst.toByteArray()));
    if (res == null)
      throw new Exception("Unable to parse XML for "+file.getName());
    return res;
  }

  private Element loadFromJsonWithVersionChange(FetchedFile file, String srcV, String dstV) throws Exception {
    throw new Exception("Version converting JSON resources is not supported yet"); // because the only know reason to do this is Forge, and it only works with XML
  }

  private void scan(String type) throws Exception {
    log(LogCategory.PROGRESS, "process type: "+type);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(type)) {
          String url = r.getElement().getChildValue("url");
          if (url != null)
            validationFetcher.getOtherUrls().add(url);
        }
      }
    }
  }
        
  private void load(String type) throws Exception {
    log(LogCategory.PROGRESS, "process type: "+type);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(type)) {
          log(LogCategory.PROGRESS, "process res: "+r.getId());
          if (r.getResource() == null)
            try {
              if (f.getBundleType() == FetchedBundleType.NATIVE)
                r.setResource(parseInternal(f, r));
              else
                r.setResource(parse(f)); 
              r.getResource().setUserData("element", r.getElement());
            } catch (Exception e) {
              throw new Exception("Error parsing "+f.getName()+": "+e.getMessage(), e);
            }
          if (!r.isValidated()) {
            if (r.getResource() instanceof DomainResource && !(((DomainResource) r.getResource()).hasText() && ((DomainResource) r.getResource()).getText().hasDiv())) {
              gen.setDefinitionsTarget(igpkp.getDefinitionsName(r));
              gen.generate((DomainResource) r.getResource(), otherFilesStartup);
              r.setElement(convertToElement(r.getResource()));
            }
            validate(f, r);
          }
          if (r.getResource() instanceof MetadataResource) {
            MetadataResource bc = (MetadataResource) r.getResource();
            if (bc == null)
              throw new Exception("Error: conformance resource "+f.getPath()+" could not be loaded");
            boolean altered = false;
            if (bc.hasUrl()) {
              if (adHocTmpDir == null && !listedURLExemptions.contains(bc.getUrl()) && !bc.getUrl().equals(Utilities.pathURL(igpkp.getCanonical(), bc.fhirType(), bc.getId())))
                f.getErrors().add(new ValidationMessage(Source.ProfileValidator, IssueType.INVALID, bc.getUrl(), "conformance resource "+f.getPath()+" canonical URL ("+Utilities.pathURL(igpkp.getCanonical(), bc.fhirType(), bc.getId())+") does not match the URL ("+bc.getUrl()+")", IssueSeverity.ERROR));
                // throw new Exception("Error: conformance resource "+f.getPath()+" canonical URL ("+Utilities.pathURL(igpkp.getCanonical(), bc.fhirType(), bc.getId())+") does not match the URL ("+bc.getUrl()+")");            
            } else if (bc.hasId())
              bc.setUrl(Utilities.pathURL(igpkp.getCanonical(), bc.fhirType(), bc.getId()));
            else
              throw new Exception("Error: conformance resource "+f.getPath()+" has neither id nor url");


            if (businessVersion != null) {
              if (!bc.hasVersion()) {
                altered = true;
              } else if (!bc.getVersion().equals(businessVersion))
                System.out.println("Business version mismatch in "+f.getName()+" - overriding from "+bc.getVersion()+" to "+businessVersion);
              bc.setVersion(businessVersion);
            }
            if (jurisdictions != null) {
              altered = true;
              bc.getJurisdiction().clear();
              bc.getJurisdiction().addAll(jurisdictions);
            }
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
            igpkp.checkForPath(f, r, bc, false);
            try {
              //            if (!(bc instanceof StructureDefinition))
              context.cacheResource(bc);
            } catch (Exception e) {
              throw new Exception("Exception loading "+bc.getUrl()+": "+e.getMessage(), e);
            }
          }
        } else if (r.getElement().fhirType().equals("Bundle")) {
          Bundle b = (Bundle) r.getResource();
          if (b == null) {
            try {
              b = (Bundle) convertFromElement(r.getElement());
              r.setResource(b);
            } catch (Exception e) { 
              log(LogCategory.PROGRESS, "Ignoring conformance resources in Bundle "+f.getName()+" because :"+e.getMessage());
            }
          }
          if (b != null) {
            for (BundleEntryComponent be : b.getEntry()) {
              if (be.hasResource() && be.getResource().fhirType().equals(type)) {
                MetadataResource mr = (MetadataResource) be.getResource();
                if (mr.hasUrl()) {
                  if (!mr.hasUserData("path"))
                    igpkp.checkForPath(f,  r,  mr, true);
                  context.cacheResource(mr);
                } else
                  log(LogCategory.PROGRESS, "Ignoring resource "+type+"/"+mr.getId()+" in Bundle "+f.getName()+" because it has no canonical URL");
                 
              }
            }
          }
        }
      }
    }
  }
  
  private void dlog(LogCategory category, String s) {
    logger.logDebugMessage(category, Utilities.padRight(s, ' ', 80)+" ("+presentDuration(System.nanoTime()-globalStart)+"sec)");
  }

  private void generateAdditionalExamples() throws Exception {
    if (genExamples) {
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
    dlog(LogCategory.PROGRESS, "Generate Snapshots");
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
    dlog(LogCategory.PROGRESS, "Check Snapshot for "+sd.getUrl());
    sd.setFhirVersion(FHIRVersion.fromCode(version));
    ProfileUtilities utils = new ProfileUtilities(context, f.getErrors(), igpkp);
    StructureDefinition base = sd.hasBaseDefinition() ? fetchSnapshotted(sd.getBaseDefinition()) : null;
    utils.setIds(sd, true);
    if (sd.getKind() != StructureDefinitionKind.LOGICAL) {
      if (!sd.hasSnapshot()) {
        dlog(LogCategory.PROGRESS, "Generate Snapshot for "+sd.getUrl());
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
        utils.generateSnapshot(base, sd, sd.getUrl(), Utilities.extractBaseUrl(base.getUserString("path")), sd.getName());
        changed = true;
      }
    } else { //sd.getKind() == StructureDefinitionKind.LOGICAL
      dlog(LogCategory.PROGRESS, "Generate Snapshot for Logical Model "+sd.getUrl());
      if (!sd.hasSnapshot()) {
        utils.populateLogicalSnapshot(sd);
        changed = true;
      }
    }
    if (changed || (!r.getElement().hasChild("snapshot") && sd.hasSnapshot()))
      r.setElement(convertToElement(sd));
    r.setSnapshotted(true);
    dlog(LogCategory.CONTEXT, "Context.See "+sd.getUrl());
    context.cacheResource(sd);
  }

  private void validateExpressions() {
    dlog(LogCategory.PROGRESS, "validate Expressions");
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
          n = fpe.parse(inv.getExpression(), sd.getUrl()+"#"+ed.getId()+" / "+inv.getKey());
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

  private Resource parseContent(String name, String contentType, String parseVersion, byte[] source) throws Exception {
    if (parseVersion.equals("3.0.1") || parseVersion.equals("3.0.0")) {
      org.hl7.fhir.dstu3.model.Resource res;
      if (contentType.contains("json"))
        res = new org.hl7.fhir.dstu3.formats.JsonParser().parse(source);
      else if (contentType.contains("xml"))
        res = new org.hl7.fhir.dstu3.formats.XmlParser().parse(source);
      else
        throw new Exception("Unable to determine file type for "+name);
      return VersionConvertor_30_50.convertResource(res, false);
    } else if (parseVersion.equals("4.0.0")) {
      org.hl7.fhir.r4.model.Resource res;
      if (contentType.contains("json"))
        res = new org.hl7.fhir.r4.formats.JsonParser().parse(source);
      else if (contentType.contains("xml"))
        res = new org.hl7.fhir.r4.formats.XmlParser().parse(source);
      else
        throw new Exception("Unable to determine file type for "+name);
      return VersionConvertor_40_50.convertResource(res);
    } else if (parseVersion.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.model.Resource res;
      if (contentType.contains("json"))
        res = new org.hl7.fhir.dstu2016may.formats.JsonParser().parse(source);
      else if (contentType.contains("xml"))
        res = new org.hl7.fhir.dstu2016may.formats.XmlParser().parse(source);
      else
        throw new Exception("Unable to determine file type for "+name);
      return VersionConvertor_14_50.convertResource(res);
    } else if (parseVersion.equals("1.0.2")) {
      org.hl7.fhir.dstu2.model.Resource res;
      if (contentType.contains("json"))
        res = new org.hl7.fhir.dstu2.formats.JsonParser().parse(source);
      else if (contentType.contains("xml"))
        res = new org.hl7.fhir.dstu2.formats.XmlParser().parse(source);
      else
        throw new Exception("Unable to determine file type for "+name);

      VersionConvertorAdvisor50 advisor = new IGR2ConvertorAdvisor5();
      return new VersionConvertor_10_50(advisor ).convertResource(res);
    } else if (parseVersion.equals(Constants.VERSION)) {
      if (contentType.contains("json"))
        return new JsonParser().parse(source);
      else if (contentType.contains("xml"))
        return new XmlParser().parse(source);
      else
        throw new Exception("Unable to determine file type for "+name);
    } else
      throw new Exception("Unsupported version "+parseVersion);
    
  }

  private Resource parseInternal(FetchedFile file, FetchedResource res) throws Exception {
    String parseVersion = version;
    if (!file.getResources().isEmpty())
      parseVersion = str(file.getResources().get(0).getConfig(), "version", version);
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new org.hl7.fhir.r5.elementmodel.XmlParser(context).compose(res.getElement(), bs, OutputStyle.NORMAL, null);
    return parseContent("Entry "+res.getId()+" in "+file.getName(), "xml", parseVersion, bs.toByteArray());
  }
  
  private Resource parse(FetchedFile file) throws Exception {
    String parseVersion = version;
    if (!file.getResources().isEmpty())
      parseVersion = str(file.getResources().get(0).getConfig(), "version", version);
    return parseContent(file.getName(), file.getContentType(), parseVersion, file.getSource());
  }

  private void validate() throws Exception {
    for (FetchedFile f : fileList) {
      dlog(LogCategory.PROGRESS, " .. validate "+f.getName());
      if (first)
        dlog(LogCategory.PROGRESS, " .. "+f.getName());
      for (FetchedResource r : f.getResources()) {
        if (!r.isValidated()) {
          dlog(LogCategory.PROGRESS, "     validating "+r.getTitle());
          validate(f, r);
        }
      }
    }
  }

  private void validate(FetchedFile file, FetchedResource r) throws Exception {
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
    } else {
      validator.validate(r.getElement(), errs, r.getElement());
    }
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
    otherFilesRun.add(Utilities.path(outputDir, "package.tgz"));
    for (String rg : regenList)
      regenerate(rg);

    updateImplementationGuide();
    
    for (FetchedFile f : changeList)
      generateNativeOutputs(f, false);
    
    templateBeforeGenerate();

    for (FetchedFile f : changeList)
      generateHtmlOutputs(f, false);

    ValidationPresenter.filterMessages(errors, suppressedMessages, false);
    if (!changeList.isEmpty())
      generateSummaryOutputs();
    
    cleanOutput(tempDir);
    
    templateAfterGenerate();
    
    if (nestedIgConfig != null) {
      if (watch) {
        throw new Exception("Cannot run in watch mode when IG has a nested IG.");
      }
      if (nestedIgOutput == null || igArtifactsPage == null) {
        throw new Exception("If nestedIgConfig is specified, then nestedIgOutput and igArtifactsPage must also be specified.");
      }
      inspector.setAltRootFolder(nestedIgOutput);
      log("");
      log("**************************");
      log("Processing nested IG: " + nestedIgConfig);
      childPublisher = new Publisher();
      childPublisher.setConfigFile(Utilities.path(Utilities.getDirectoryForFile(this.getConfigFile()), nestedIgConfig));
      childPublisher.setJekyllCommand(this.getJekyllCommand());
      childPublisher.setTxServer(this.getTxServer());
      childPublisher.setDebug(this.debug);
      childPublisher.setCacheOption(this.getCacheOption());
      childPublisher.setIsChild(true);
      childPublisher.setMode(this.getMode());
      childPublisher.setTargetOutput(this.getTargetOutputNested());
      
      try {
        childPublisher.execute();
        log("Done processing nested IG: " + nestedIgConfig);
        log("**************************");
        childPublisher.updateInspector(inspector, nestedIgOutput);
      } catch (Exception e) {
        log("Publishing Child IG Failed: " + nestedIgConfig);
        throw e;
      }
      createToc(childPublisher.getSourceIg().getDefinition().getPage(), igArtifactsPage, nestedIgOutput);
    }
    
    if (runTool()) {

      if (!changeList.isEmpty()) {
        File df = makeSpecFile();
        npm.addFile(Category.OTHER, "spec.internals", TextFile.fileToBytes(df.getAbsolutePath()));
        npm.finish();

        if (mode == null || mode == IGBuildMode.MANUAL) {
          if (cacheVersion)
            pcm.addPackageToCache(publishedIg.getPackageId(), publishedIg.getVersion(), new FileInputStream(npm.filename()));
          else
            pcm.addPackageToCache(publishedIg.getPackageId(), "dev", new FileInputStream(npm.filename()));        
        } else if (mode == IGBuildMode.PUBLICATION)
          pcm.addPackageToCache(publishedIg.getPackageId(), publishedIg.getVersion(), new FileInputStream(npm.filename()));
        generateZips(df);
      }
    }
    
    if (childPublisher!=null) {
      // Combine list of files so that the validation report will include everything
      fileList.addAll(childPublisher.getFileList());
    }
    
    if (!isChild()) {
      log("Checking Output HTML");
      List<ValidationMessage> linkmsgs = inspector.check(mode == IGBuildMode.AUTOBUILD ? 
          Utilities.escapeXml(sourceIg.present())+" - CI build (v"+businessVersion+"). See the <a href=\""+igpkp.getCanonical()+"/history.html\">Directory of published versions</a></p>" : mode == IGBuildMode.PUBLICATION ? 
              "This will be filled in by the publication tooling" : 
              Utilities.escapeXml(sourceIg.present())+" - Local Development build (v"+businessVersion+"). See the <a href=\""+igpkp.getCanonical()+"/history.html\">Directory of published versions</a></p>");
      ValidationPresenter.filterMessages(linkmsgs, suppressedMessages, true);
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
      for (FetchedFile f : fileList)
        ValidationPresenter.filterMessages(f.getErrors(), suppressedMessages, false);
      log("Build final .zip");
      if (brokenLinksError && linkmsgs.size() > 0)
        throw new Error("Halting build because broken links have been found, and these are disallowed in the IG control file");
      ZipGenerator zip = new ZipGenerator(Utilities.path(tempDir, "full-ig.zip"));
      zip.addFolder(outputDir, "site/", false);
      zip.addFileSource("index.html", REDIRECT_SOURCE, false);
      zip.close();
      Utilities.copyFile(Utilities.path(tempDir, "full-ig.zip"), Utilities.path(outputDir, "full-ig.zip"));
      log("Final .zip built");
    }
  }





  private void updateImplementationGuide() throws Exception {
    for (ImplementationGuideDefinitionResourceComponent res : publishedIg.getDefinition().getResource()) {
      FetchedResource r = null;
      for (FetchedFile tf : fileList) {
        for (FetchedResource tr : tf.getResources()) {
          if (tr.getLocalRef().equals(res.getReference().getReference())) {
            r = tr;
          }
        }
      }
      if (r != null) {
        String path = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
        res.addExtension().setUrl("http://hl7.org/fhir/StructureDefinition/implementationguide-page").setValue(new UriType(path));
        inspector.addLinkToCheck("Implementation Guide", path, "??");
      }
    }

    FetchedResource r = altMap.get(IG_NAME).getResources().get(0);
    if (!publishedIg.hasText() || !publishedIg.getText().hasDiv())
      publishedIg.setText(((ImplementationGuide)r.getResource()).getText());
    r.setResource(publishedIg);
    r.setElement(convertToElement(publishedIg));
    
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new JsonParser().setOutputStyle(OutputStyle.NORMAL).compose(bs, publishedIg);
    npm.addFile(Category.RESOURCE, "ig-r4.json", bs.toByteArray());
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
    generateNativeOutputs(f, true);
    generateHtmlOutputs(f, true);
  }

  private Element convertToElement(Resource res) throws IOException, org.hl7.fhir.exceptions.FHIRException, FHIRFormatError, DefinitionException {
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    if (version.equals("3.0.1")) {
      org.hl7.fhir.dstu3.formats.JsonParser jp = new org.hl7.fhir.dstu3.formats.JsonParser();
      jp.compose(bs, VersionConvertor_30_50.convertResource(res, false));
    } else if (version.equals("4.0.0")) {
      org.hl7.fhir.r4.formats.JsonParser jp = new org.hl7.fhir.r4.formats.JsonParser();
      jp.compose(bs, VersionConvertor_40_50.convertResource(res));
    } else if (version.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.formats.JsonParser jp = new org.hl7.fhir.dstu2016may.formats.JsonParser();
      jp.compose(bs, VersionConvertor_14_50.convertResource(res));
    } else if (version.equals("1.0.2")) {
        org.hl7.fhir.dstu2.formats.JsonParser jp = new org.hl7.fhir.dstu2.formats.JsonParser();
        jp.compose(bs, new VersionConvertor_10_50(new IGR2ConvertorAdvisor5()).convertResource(res));
    } else { // if (version.equals(Constants.VERSION)) {
      org.hl7.fhir.r5.formats.JsonParser jp = new org.hl7.fhir.r5.formats.JsonParser();
      jp.compose(bs, res);
    }
      ByteArrayInputStream bi = new ByteArrayInputStream(bs.toByteArray());
    return new org.hl7.fhir.r5.elementmodel.JsonParser(context).parse(bi);
  }

  private Resource convertFromElement(Element res) throws IOException, org.hl7.fhir.exceptions.FHIRException, FHIRFormatError, DefinitionException {
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new org.hl7.fhir.r5.elementmodel.JsonParser(context).compose(res, bs, OutputStyle.NORMAL, null);
    ByteArrayInputStream bi = new ByteArrayInputStream(bs.toByteArray());
    if (version.equals("3.0.1")) {
      org.hl7.fhir.dstu3.formats.JsonParser jp = new org.hl7.fhir.dstu3.formats.JsonParser();
      return  VersionConvertor_30_50.convertResource(jp.parse(bi), false);
    } else if (version.equals("4.0.0")) {
      org.hl7.fhir.r4.formats.JsonParser jp = new org.hl7.fhir.r4.formats.JsonParser();
      return  VersionConvertor_40_50.convertResource(jp.parse(bi));
    } else if (version.equals("1.4.0")) {
      org.hl7.fhir.dstu2016may.formats.JsonParser jp = new org.hl7.fhir.dstu2016may.formats.JsonParser();
      return  VersionConvertor_14_50.convertResource(jp.parse(bi));
    } else if (version.equals("1.0.2")) {
        org.hl7.fhir.dstu2.formats.JsonParser jp = new org.hl7.fhir.dstu2.formats.JsonParser();
        return new VersionConvertor_10_50(null).convertResource(jp.parse(bi));
    } else { // if (version.equals(Constants.VERSION)) {
      org.hl7.fhir.r5.formats.JsonParser jp = new org.hl7.fhir.r5.formats.JsonParser();
      return jp.parse(bi);
    } 
  }

  private void cleanOutput(String folder) throws IOException {
    for (File f : new File(folder).listFiles()) {
      cleanOutputFile(f);
    }
  }


  public void cleanOutputFile(File f) {
 // Lloyd: this was changed from getPath to getCanonicalPath, but 
 // Grahame: changed it back because this was achange that broke everything, and with no reason provided
    if (!isValidFile(f.getPath())) {
      if (!f.isDirectory()) {
        f.delete();
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

  private File makeSpecFile() throws Exception {
    SpecMapManager map = new SpecMapManager(npmName, version, Constants.VERSION, Integer.toString(ToolsVersion.TOOLS_VERSION), execTime, igpkp.getCanonical());
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        String u = igpkp.getCanonical()+r.getUrlTail();
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          String uc = ((MetadataResource) r.getResource()).getUrl();
          if (uc != null && !u.equals(uc) && !isListedURLExemption(uc) && adHocTmpDir == null)
            f.getErrors().add(new ValidationMessage(Source.Publisher, IssueType.BUSINESSRULE, f.getName(), "URL Mismatch "+u+" vs "+uc, IssueSeverity.ERROR));
          if (uc != null && !u.equals(uc))
            map.path(uc, igpkp.getLinkFor(r));
          String v = ((MetadataResource) r.getResource()).getVersion();
          if (v != null) {
            map.path(uc + "|" + v, v + "/" + igpkp.getLinkFor(r));
          }
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
    map.save(df.getCanonicalPath());
    return df;
  }
  private void generateZips(File df) throws Exception {

    if (generateExampleZip(FhirFormat.XML))
      generateDefinitions(FhirFormat.XML, df.getCanonicalPath());
    if (generateExampleZip(FhirFormat.JSON))
      generateDefinitions(FhirFormat.JSON, df.getCanonicalPath());
    if (supportsTurtle() && generateExampleZip(FhirFormat.TURTLE))
      generateDefinitions(FhirFormat.TURTLE, df.getCanonicalPath());
    generateExpansions();
    generateValidationPack(df.getCanonicalPath());
    // Create an IG-specific named igpack to make is easy to grab the igpacks for multiple igs without the names colliding (Talk to Lloyd before removing this)
    FileUtils.copyFile(new File(Utilities.path(outputDir, "validator.pack")),new File(Utilities.path(outputDir, "validator-" + sourceIg.getId() + ".pack")));
    generateCsvZip();
    generateExcelZip();
    generateRegistryUploadZip(df.getCanonicalPath());
  }

  private boolean supportsTurtle() {
    return !Utilities.existsInList(version, "1.0.2", "1.4.0");
  }


  private void generateExpansions() throws FileNotFoundException, IOException {
    Bundle exp = new Bundle();
    exp.setType(BundleType.COLLECTION);
    exp.setId(UUID.randomUUID().toString());
    exp.getMeta().setLastUpdated(execTime.getTime());
    for (ValueSet vs : expansions) {
      exp.addEntry().setResource(vs).setFullUrl(vs.getUrl());
    }
    
    new JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(outputDir, "expansions.json")), exp);
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(outputDir, "expansions.xml")), exp);
    ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "expansions.json.zip"));
    zip.addFileName("expansions.json", Utilities.path(outputDir, "expansions.json"), false);
    zip.close();
    zip = new ZipGenerator(Utilities.path(outputDir, "expansions.xml.zip"));
    zip.addFileName("expansions.xml", Utilities.path(outputDir, "expansions.xml"), false);
    zip.close();
  }


  private boolean isListedURLExemption(String uc) {
    return listedURLExemptions.contains(uc);
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
        if (version.equals("3.0.1")) {
          org.hl7.fhir.dstu3.model.Resource r3 = VersionConvertor_30_50.convertResource(r.getResource(), false);
          if (fmt.equals(FhirFormat.JSON))
            new org.hl7.fhir.dstu3.formats.JsonParser().compose(bs, r3);
          else if (fmt.equals(FhirFormat.XML))
            new org.hl7.fhir.dstu3.formats.XmlParser().compose(bs, r3);
          else if (fmt.equals(FhirFormat.TURTLE))
            new org.hl7.fhir.dstu3.formats.RdfParser().compose(bs, r3);
        } else if (version.equals("4.0.0")) {
          org.hl7.fhir.r4.model.Resource r4 = VersionConvertor_40_50.convertResource(r.getResource());
          if (fmt.equals(FhirFormat.JSON))
            new org.hl7.fhir.r4.formats.JsonParser().compose(bs, r4);
          else if (fmt.equals(FhirFormat.XML))
            new org.hl7.fhir.r4.formats.XmlParser().compose(bs, r4);
          else if (fmt.equals(FhirFormat.TURTLE))
            new org.hl7.fhir.r4.formats.RdfParser().compose(bs, r4);
        } else if (version.equals("1.4.0")) {
          org.hl7.fhir.dstu2016may.model.Resource r14 = VersionConvertor_14_50.convertResource(r.getResource());
          if (fmt.equals(FhirFormat.JSON))
            new org.hl7.fhir.dstu2016may.formats.JsonParser().compose(bs, r14);
          else if (fmt.equals(FhirFormat.XML))
            new org.hl7.fhir.dstu2016may.formats.XmlParser().compose(bs, r14);
          else if (fmt.equals(FhirFormat.TURTLE))
            new org.hl7.fhir.dstu2016may.formats.RdfParser().compose(bs, r14);
        } else if (version.equals("1.0.2")) {
          VersionConvertorAdvisor50 advisor = new IGR2ConvertorAdvisor5();
          org.hl7.fhir.dstu2.model.Resource r14 = new VersionConvertor_10_50(advisor).convertResource(r.getResource());
          if (fmt.equals(FhirFormat.JSON))
            new org.hl7.fhir.dstu2.formats.JsonParser().compose(bs, r14);
          else if (fmt.equals(FhirFormat.XML))
            new org.hl7.fhir.dstu2.formats.XmlParser().compose(bs, r14);
          else if (fmt.equals(FhirFormat.TURTLE))
            throw new Exception("Turtle is not supported for releases < 3");
        } else {
          if (fmt.equals(FhirFormat.JSON))
            new JsonParser().compose(bs, r.getResource());
          else if (fmt.equals(FhirFormat.XML))
            new XmlParser().compose(bs, r.getResource());
          else if (fmt.equals(FhirFormat.TURTLE))
            new TurtleParser(context).compose(r.getElement(), bs, OutputStyle.PRETTY, igpkp.getCanonical());
        }
        zip.addBytes(r.getElement().fhirType()+"-"+r.getId()+"."+fmt.getExtension(), bs.toByteArray(), false);
      }
      zip.addFileName("spec.internals", specFile, false);
      zip.close();
    }
  }

  private void generateExcelZip()  throws Exception {
    generateZipByExtension(Utilities.path(outputDir, "excels.zip"), ".xlsx");
  }

  private void generateCsvZip()  throws Exception {
    generateZipByExtension(Utilities.path(outputDir, "csvs.zip"), ".csv");
  }

  private void generateRegistryUploadZip(String specFile)  throws Exception {
    ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "registry.fhir.org.zip"));
    zip.addFileName("spec.internals", specFile, false);
    StringBuilder ri = new StringBuilder();
    ri.append("[registry]\r\n");
    ri.append("toolversion="+getToolingVersion()+"\r\n");
    ri.append("fhirversion="+version+"\r\n");
    int i = 0;
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          try {
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            org.hl7.fhir.dstu3.model.Resource r3 = VersionConvertor_30_50.convertResource(r.getResource(), false);
            new org.hl7.fhir.dstu3.formats.JsonParser().compose(bs, r3);
            zip.addBytes(r.getElement().fhirType()+"-"+r.getId()+".json", bs.toByteArray(), false);
          } catch (Exception e) {
            log("Can't store "+r.getElement().fhirType()+"-"+r.getId()+" in R3 format for registry.fhir.org");
          }
          i++;
        }
      }
    }
    ri.append("resourcecount="+Integer.toString(i)+"\r\n");
    zip.addBytes("registry.info", ri.toString().getBytes(Charsets.UTF_8), false);
    zip.close();
  }

  private void generateValidationPack(String specFile)  throws Exception {
    String sch = makeTempZip(".sch");
    String js = makeTempZip(".schema.json");
    String shex = makeTempZip(".shex");

    ZipGenerator zip = new ZipGenerator(Utilities.path(outputDir, "validator.pack"));
    zip.addBytes("version.info", makeNewVersionInfo(version), false);
    zip.addFileName("spec.internals", specFile, false);
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
          ByteArrayOutputStream bs = new ByteArrayOutputStream();
          if (version.equals("3.0.1") || version.equals("3.0.0")) {
            new org.hl7.fhir.dstu3.formats.JsonParser().compose(bs, VersionConvertor_30_50.convertResource(r.getResource(), false));
          } else if (version.equals("4.0.0")) {
            new org.hl7.fhir.r4.formats.JsonParser().compose(bs, VersionConvertor_40_50.convertResource(r.getResource()));
          } else if (version.equals("1.4.0")) {
            new org.hl7.fhir.dstu2016may.formats.JsonParser().compose(bs, VersionConvertor_14_50.convertResource(r.getResource()));
          } else if (version.equals("1.0.2")) {
            VersionConvertorAdvisor50 advisor = new IGR2ConvertorAdvisor5();
            new org.hl7.fhir.dstu2.formats.JsonParser().compose(bs, new VersionConvertor_10_50(advisor ).convertResource(r.getResource()));
          } else if (version.equals(Constants.VERSION)) {
            new JsonParser().compose(bs, r.getResource());
          } else
            throw new Exception("Unsupported version "+version);
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

  private byte[] makeNewVersionInfo(String version) throws IOException {
    String is = "[FHIR]\r\nversion="+version+"\r\n";
    IniFile ini = new IniFile(new ByteArrayInputStream(TextFile.stringToBytes(is, false)));
    ini.setStringProperty("IG", "version", version, null);
    ini.setStringProperty("IG", "date",  new SimpleDateFormat("yyyyMMddhhmmssZ").format(execTime.getTime()), null);
    ByteArrayOutputStream b = new ByteArrayOutputStream();
    ini.save(b);
    return b.toByteArray();
  }


  private String makeTempZip(String ext) throws IOException {
    File tmp = File.createTempFile("fhir", "zip");
    tmp.deleteOnExit();
    if (generateZipByExtension(tmp.getCanonicalPath(), ext))
      return tmp.getCanonicalPath();
    else
      return null;
  }

  private boolean generateZipByExtension(String path, String ext) throws IOException {
    Set<String> files = new HashSet<String>();
    for (String s : new File(outputDir).list()) {
      if (s.endsWith(ext))
        files.add(s);
    }
    if (files.size() == 0)
      return false;
    ZipGenerator zip = new ZipGenerator(path);
    for (String fn : files)
      zip.addFileName(fn, Utilities.path(outputDir, fn), false);
    zip.close();
    return true;
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

//    dumpVars();

    try {
	    if (SystemUtils.IS_OS_WINDOWS)
	      exec.execute(org.apache.commons.exec.CommandLine.parse("cmd /C "+jekyllCommand+" build --destination \""+outputDir+"\""));
	    else
	      exec.execute(org.apache.commons.exec.CommandLine.parse(jekyllCommand+" build --destination \""+outputDir+"\""));
    } catch (IOException ioex) {
    	log("Jekyll has failed - not installed (correcty?). Complete output from running Jekyll: " + pumpHandler.getBufferString());
    	throw ioex;
    }
    return true;
  }

  private void dumpVars() {
    log("---- Props -------------");
    Properties properties = System.getProperties();
    properties.forEach((k, v) -> log((String) k + ": "+ v));
    log("---- Vars -------------");
    System.getenv().forEach((k, v) -> {
      log(k + ":" + v);
    });
    log("-----------------------");
  }


  private void generateSummaryOutputs() throws Exception {
    log("Generating Summary Outputs");
    generateResourceReferences();

    generateDataFile();
    
    // now, list the profiles - all the profiles
    JsonObject data = new JsonObject();
    int i = 0;
    JsonObject maturities = new JsonObject();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getResource() != null && r.getResource() instanceof DomainResource) {
          String fmm = ToolingExtensions.readStringExtension((DomainResource) r.getResource(), ToolingExtensions.EXT_FMM_LEVEL);
          if (fmm != null)
            maturities.addProperty(r.getResource().fhirType()+"-"+r.getId(), fmm);
        }
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();

          JsonObject item = new JsonObject();
          data.add(sd.getId(), item);
          item.addProperty("index", i);
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
          if (sd.hasContext()) {
            JsonArray contexts = new JsonArray();
            item.add("contexts", contexts);
            for (StructureDefinitionContextComponent ec : sd.getContext()) {
              JsonObject citem = new JsonObject();
              contexts.add(citem);
              citem.addProperty("type", ec.getType().getDisplay());
              citem.addProperty("type", ec.getExpression());
            }
          }
          i++;
        }
      }
    }
    if (maturities.keySet().size() > 0)
      data.add("maturities", maturities);

    for (FetchedResource r: examples) {
      FetchedResource baseRes = getResourceForUri(r.getExampleUri());
      if (baseRes == null)
        throw new Exception ("Unable to find exampleFor resource " + r.getExampleUri() + " for resource " + r.getUrlTail());
      baseRes.addExample(r);
    }

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(data);
    TextFile.stringToFile(json, Utilities.path(tempDir, "_data", "structuredefinitions.json"));

    if (sourceIg.getDefinition().hasPage()) {
      JsonObject pages = new JsonObject();
      addPageData(pages, sourceIg.getDefinition().getPage(), "0", "");
      //   gson = new GsonBuilder().setPrettyPrinting().create();
      //   json = gson.toJson(pages);
      json = pages.toString();
      TextFile.stringToFile(json, Utilities.path(tempDir, "_data", "pages.json"));

      createToc();
      if (htmlTemplate != null || mdTemplate != null) {
        applyPageTemplate(htmlTemplate, mdTemplate, sourceIg.getDefinition().getPage());
      }
    }
  }


  public String license() throws Exception {
    if (Utilities.noString(license))
      throw new Exception("A license is required in the configuration file, and it must be a SPDX license identifier (see https://spdx.org/licenses/), or \"not-open-source\"");
    return license;
  }

  public SPDXLicense licenseAsEnum() throws Exception {
    return SPDXLicense.fromCode(license());
  }



  private String url(List<ContactPoint> telecom) {
    for (ContactPoint cp : telecom) {
      if (cp.getSystem() == ContactPointSystem.URL)
        return cp.getValue();
    }
    return null;
  }


  private String email(List<ContactPoint> telecom) {
    for (ContactPoint cp : telecom) {
      if (cp.getSystem() == ContactPointSystem.EMAIL)
        return cp.getValue();
    }
    return null;
  }

  private void applyPageTemplate(String htmlTemplate, String mdTemplate, ImplementationGuideDefinitionPageComponent page) throws Exception {
    String p = page.getNameUrlType().getValue();
    String sourceName = null;
    String template = null;
    if (htmlTemplate != null && page.getGeneration() == GuidePageGeneration.HTML  && !relativeNames.keySet().contains(p) && p.endsWith(".html")) {
      sourceName = p.substring(0, p.indexOf(".html")) + ".xml";
      template = htmlTemplate;
    } else if (mdTemplate != null && page.getGeneration() == GuidePageGeneration.MARKDOWN  && !relativeNames.keySet().contains(p) && p.endsWith(".html")) {
      sourceName = p.substring(0, p.indexOf(".html")) + ".md";
      template = mdTemplate;
    }
    if (sourceName!=null) {
      String sourcePath = Utilities.path("_includes", sourceName);
      if (!relativeNames.keySet().contains(sourcePath) && !sourceName.equals("toc.xml"))
        throw new Exception("Template based HTML file " + p + " is missing source file " + sourceName);
      FetchedFile f = relativeNames.get(sourcePath);
      String s = "---\r\n---\r\n{% include " + template + "%}";
      String targetPath = Utilities.path(tempDir, p);
      TextFile.stringToFile(s, targetPath);
      if (f==null) // toc.xml
        checkMakeFile(s.getBytes(), targetPath, otherFilesRun);
      else
        checkMakeFile(s.getBytes(), targetPath, f.getOutputNames());
    }

    for (ImplementationGuideDefinitionPageComponent childPage : page.getPage()) {
      applyPageTemplate(htmlTemplate, mdTemplate, childPage);
    }
  }

  private String breadCrumbForPage(ImplementationGuideDefinitionPageComponent page, boolean withLink) throws FHIRException {
    if (withLink)
      return "<li><a href='" + page.getNameUrlType().getValue() + "'><b>" + Utilities.escapeXml(page.getTitle()) + "</b></a></li>";
    else
      return "<li><b>" + Utilities.escapeXml(page.getTitle()) + "</b></li>";
          }

  private void addPageData(JsonObject pages, ImplementationGuideDefinitionPageComponent page, String label, String breadcrumb) throws FHIRException {
    if (!page.hasNameUrlType())
      errors.add(new ValidationMessage(Source.Publisher, IssueType.REQUIRED, "Base IG resource", "The page \""+page.getTitle()+"\" is missing a name/source element", IssueSeverity.ERROR));
    else
      addPageData(pages, page, page.getNameUrlType().getValue(), page.getTitle(), label, breadcrumb);
  }

  private void addPageData(JsonObject pages, ImplementationGuideDefinitionPageComponent page, String source, String title, String label, String breadcrumb) throws FHIRException {
    FetchedResource r = resources.get(source);
    if (r==null)
      addPageDataRow(pages, source, title, label + (page.hasPage() ? ".0" : ""), breadcrumb + breadCrumbForPage(page, false), null);
    else
      addPageDataRow(pages, source, title, label, breadcrumb + breadCrumbForPage(page, false), r.getExamples());
    if ( r != null ) {
      Map<String, String> vars = makeVars(r);
      String outputName = determineOutputName(igpkp.getProperty(r, "base"), r, vars, null, "");
      if (igpkp.wantGen(r, "xml")) {
        outputName = determineOutputName(igpkp.getProperty(r, "format"), r, vars, "xml", "");
        addPageDataRow(pages, outputName, page.getTitle() + " - XML Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
      }
      if (igpkp.wantGen(r, "json")) {
        outputName = determineOutputName(igpkp.getProperty(r, "format"), r, vars, "json", "");
        addPageDataRow(pages, outputName, page.getTitle() + " - JSON Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
      }
      if (igpkp.wantGen(r, "ttl")) {
        outputName = determineOutputName(igpkp.getProperty(r, "format"), r, vars, "ttl", "");
        addPageDataRow(pages, outputName, page.getTitle() + " - TTL Representation", label, breadcrumb + breadCrumbForPage(page, false), null);
      }

      if (page.hasGeneration() && page.getGeneration().equals(GuidePageGeneration.GENERATED) /*page.getKind().equals(ImplementationGuide.GuidePageKind.RESOURCE) */) {
        outputName = determineOutputName(igpkp.getProperty(r, "defns"), r, vars, null, "definitions");
        addPageDataRow(pages, outputName, page.getTitle() + " - Definitions", label, breadcrumb + breadCrumbForPage(page, false), null);
        for (String templateName : extraTemplates.keySet()) {
          String templateDesc = extraTemplates.get(templateName);
          outputName = igpkp.getProperty(r, templateName);
          if (outputName == null)
            outputName = r.getElement().fhirType()+"-"+r.getId()+"-"+templateName+".html";
          else
            outputName = igpkp.doReplacements(outputName, r, vars, "");
          addPageDataRow(pages, outputName, page.getTitle() + " - " + templateDesc, label, breadcrumb + breadCrumbForPage(page, false), null);
        }
      }
    }

    int i = 1;
    for (ImplementationGuideDefinitionPageComponent childPage : page.getPage()) {
      addPageData(pages, childPage, (label.equals("0") ? "" : label+".") + Integer.toString(i), breadcrumb + breadCrumbForPage(page, true));
      i++;
    }
  }

  private boolean dontDoThis() {
    return false;
  }


  private void addPageDataRow(JsonObject pages, String url, String title, String label, String breadcrumb, Set<FetchedResource> examples) throws FHIRException {
    JsonObject jsonPage = new JsonObject();
    pages.add(url, jsonPage);
    jsonPage.addProperty("title", title);
    jsonPage.addProperty("label", label);
    jsonPage.addProperty("breadcrumb", breadcrumb);

    String baseUrl = url;

    if (baseUrl.indexOf(".html") > 0) {
    	baseUrl = baseUrl.substring(0, baseUrl.indexOf(".html"));
    }

    for (String pagesDir: pagesDirs) {
      String contentFile = pagesDir + File.separator + "_includes" + File.separator + baseUrl + "-intro.xml";
      if (new File(contentFile).exists()) {
        jsonPage.addProperty("intro", baseUrl+"-intro.xml");
        jsonPage.addProperty("intro-type", "xml");
      } else {
        contentFile = pagesDir + File.separator + "_includes" + File.separator + baseUrl + "-intro.md";
        if (new File(contentFile).exists()) {
          jsonPage.addProperty("intro", baseUrl+"-intro.md");
          jsonPage.addProperty("intro-type", "md");
        }
      }

      contentFile = pagesDir + File.separator + "_includes" + File.separator + baseUrl + "-notes.xml";
      if (new File(contentFile).exists()) {
        jsonPage.addProperty("notes", baseUrl+"-notes.xml");
        jsonPage.addProperty("notes-type", "xml");
      } else {
        contentFile = pagesDir + File.separator + "_includes" + File.separator + baseUrl + "-notes.md";
        if (new File(contentFile).exists()) {
          jsonPage.addProperty("notes", baseUrl+"-notes.md");
          jsonPage.addProperty("notes-type", "md");
        }
      }
    }

    for (String prePagesDir: prePagesDirs) {
      PreProcessInfo ppinfo = preProcessInfo.get(prePagesDir);
      String baseFile = prePagesDir + File.separator;
      if (ppinfo.relativePath.equals(""))
        baseFile = baseFile + "_includes" + File.separator;
      else if (!ppinfo.relativePath.equals("_includes"))
        continue;
      baseFile = baseFile + baseUrl;
      String contentFile = baseFile + "-intro.xml";
      if (new File(contentFile).exists()) {
        jsonPage.addProperty("intro", baseUrl+"-intro.xml");
        jsonPage.addProperty("intro-type", "xml");
      } else {
        contentFile = baseFile + "-intro.md";
        if (new File(contentFile).exists()) {
          jsonPage.addProperty("intro", baseUrl+"-intro.md");
          jsonPage.addProperty("intro-type", "md");
        }
      }
  
      contentFile = baseFile + "-notes.xml";
      if (new File(contentFile).exists()) {
        jsonPage.addProperty("notes", baseUrl+"-notes.xml");
        jsonPage.addProperty("notes-type", "xml");
      } else {
        contentFile = baseFile + "-notes.md";
        if (new File(contentFile).exists()) {
          jsonPage.addProperty("notes", baseUrl+"-notes.md");
          jsonPage.addProperty("notes-type", "md");
        }        
      }
    }
    
    if (examples != null) {
      JsonArray exampleArray = new JsonArray();
      jsonPage.add("examples", exampleArray);

      TreeSet<ImplementationGuideDefinitionPageComponent> examplePages = new TreeSet<ImplementationGuideDefinitionPageComponent>(new ImplementationGuideDefinitionPageComponentComparator());
      for (FetchedResource exampleResource: examples) {
        ImplementationGuideDefinitionPageComponent page = pageForFetchedResource(exampleResource);
        if (page!=null)
          examplePages.add(page);
        // else
        //   throw new Error("Unable to find page for resource "+ exampleResource.getId());
      }
      for (ImplementationGuideDefinitionPageComponent examplePage : examplePages) {
        JsonObject exampleItem = new JsonObject();
        exampleArray.add(exampleItem);
        exampleItem.addProperty("url", examplePage.getNameUrlType().getValue());
        exampleItem.addProperty("title", examplePage.getTitle());
      }
    }
  }

    
  private void createToc() throws IOException, FHIRException {
    createToc(null, null, null);
  }
  
  private void createToc(ImplementationGuideDefinitionPageComponent insertPage, String insertAfterName, String insertOffset) throws IOException, FHIRException {
    String s = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><div style=\"col-12\"><table style=\"border:0px;font-size:11px;font-family:verdana;vertical-align:top;\" cellpadding=\"0\" border=\"0\" cellspacing=\"0\"><tbody>";
    s = s + createTocPage(sourceIg.getDefinition().getPage(), insertPage, insertAfterName, insertOffset, null, "", "0", false);
    s = s + "</tbody></table></div>";
    TextFile.stringToFile(s, Utilities.path(tempDir, "_includes", "toc.xml"));
  }

  private String createTocPage(ImplementationGuideDefinitionPageComponent page, ImplementationGuideDefinitionPageComponent insertPage, String insertAfterName, String insertOffset, String currentOffset, String indents, String label, boolean last) throws FHIRException {
    String s = "<tr style=\"border:0px;padding:0px;vertical-align:top;background-color:white;\">";
    s = s + "<td style=\"vertical-align:top;text-align:left;background-color:white;padding:0px 4px 0px 4px;white-space:nowrap;background-image:url(tbl_bck0.png)\" class=\"hierarchy\">";
    s = s + "<img style=\"background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"tbl_spacer.png\"/>";
    s = s + indents;
    if (!label.equals("0")) {
      if (last)
        s = s + "<img style=\"background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"tbl_vjoin_end.png\"/>";
      else
        s = s + "<img style=\"background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"tbl_vjoin.png\"/>";
    }
    s = s + "<img style=\"background-color:white;background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"icon_page.gif\"/>";
    s = s + "<a title=\"" + Utilities.escapeXml(page.getTitle()) + "\" href=\"" + (currentOffset!=null ? currentOffset + "/" : "") + (page.hasNameUrlType() ? page.getNameUrlType().getValue() : "?name?") +"\">" + label + " " + Utilities.escapeXml(page.getTitle()) + "</a></td></tr>";

    int total = page.getPage().size();
    int i = 1;
    for (ImplementationGuideDefinitionPageComponent childPage : page.getPage()) {
      String newIndents = indents;
      if (!label.equals("0")) {
        if (last)
          newIndents = newIndents + "<img style=\"background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"tbl_blank.png\"/>";
        else
          newIndents = newIndents + "<img style=\"background-color:inherit\" alt=\".\" class=\"hierarchy\" src=\"tbl_vline.png\"/>";
      }
      if (insertAfterName!=null && childPage.getNameUrlType().getValue().equals(insertAfterName)) {
        total++;
      }
      
      s = s + createTocPage(childPage, insertPage, insertAfterName, insertOffset, currentOffset, newIndents, (label.equals("0") ? "" : label+".") + Integer.toString(i), i==total);
      i++;
      if (insertAfterName!=null && childPage.getNameUrlType().getValue().equals(insertAfterName)) {
        s = s + createTocPage(insertPage, null, null, "", insertOffset, newIndents, (label.equals("0") ? "" : label+".") + Integer.toString(i), i==total);
        i++;
      }
    }
    return s;
  }

  private ImplementationGuideDefinitionPageComponent pageForFetchedResource(FetchedResource r) throws FHIRException {
    String key = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
    return igPages.get(key);
  }

  private class ImplementationGuideDefinitionPageComponentComparator implements Comparator<ImplementationGuideDefinitionPageComponent> {
    @Override
    public int compare(ImplementationGuideDefinitionPageComponent x, ImplementationGuideDefinitionPageComponent y) {
      try {
        return x.getNameUrlType().getValue().compareTo(y.getNameUrlType().getValue());
      } catch (FHIRException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        return 0;
      }
    }
  }

  private void generateDataFile() throws IOException, FHIRException {
    JsonObject data = new JsonObject();
    data.addProperty("path", checkAppendSlash(specPath));
    data.addProperty("canonical", igpkp.getCanonical());
    data.addProperty("igId", sourceIg.getId());
    data.addProperty("igName", sourceIg.getName());
    data.addProperty("packageId", npmName);
    data.addProperty("igVer", businessVersion);
    data.addProperty("errorCount", getErrorCount());
    data.addProperty("version", specMaps.get(0).getVersion());
    data.addProperty("revision", specMaps.get(0).getBuild());
    data.addProperty("versionFull", specMaps.get(0).getVersion()+"-"+specMaps.get(0).getBuild());
    data.addProperty("toolingVersion", Constants.VERSION);
    data.addProperty("toolingRevision", ToolsVersion.TOOLS_VERSION_STR);
    data.addProperty("toolingVersionFull", Constants.VERSION+" ("+ToolsVersion.TOOLS_VERSION_STR+")");
    data.addProperty("totalFiles", fileList.size());
    data.addProperty("processedFiles", changeList.size());
    data.addProperty("genDate", genTime());
    data.addProperty("genDay", genDate());
    JsonObject ig = new JsonObject();
    data.add("ig", ig);
    ig.addProperty("id", sourceIg.getId());
    ig.addProperty("name", sourceIg.getName());
    ig.addProperty("title", sourceIg.getTitle());
    ig.addProperty("url", sourceIg.getUrl());
    if (businessVersion!=null)
      ig.addProperty("version", businessVersion);
    else
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
    for (Enumeration<FHIRVersion> v : sourceIg.getFhirVersion()) {
      ig.addProperty("fhirVersion", v.asStringValue());
      break;
    }

    for (SpecMapManager sm : specMaps) {
      if (sm.getName() != null)
        data.addProperty(sm.getName(), appendTrailingSlashInDataFile ? sm.getBase() : Utilities.appendForwardSlash(sm.getBase()));
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

  private class Item {
    public Item(FetchedFile f, FetchedResource r, String sort) {
      this.f= f;
      this.r = r;
      this.sort = sort;
    }
    public Item() {
      // TODO Auto-generated constructor stub
    }
    private String sort;
    private FetchedFile f;
    private FetchedResource r;
  }
  private class ItemSorter implements Comparator<Item> {

    @Override
    public int compare(Item a0, Item a1) {
      return a0.sort.compareTo(a1.sort);
    }
  }

  private void generateProfiles() throws Exception {
    List<Item> items = new ArrayList<Item>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && sd.getKind() == StructureDefinitionKind.RESOURCE) {
            items.add(new Item(f, r, sd.hasTitle() ? sd.getTitle() : sd.hasName() ? sd.getName() : r.getTitle()));
          }
        }
      }
    }
    if (items.size() > 0) {
      Collections.sort(items, new ItemSorter());
      StringBuilder list = new StringBuilder();
      StringBuilder lists = new StringBuilder();
      StringBuilder table = new StringBuilder();
      StringBuilder listMM = new StringBuilder();
      StringBuilder listsMM = new StringBuilder();
      StringBuilder tableMM = new StringBuilder();
      for (Item i : items) {
        StructureDefinition sd = (StructureDefinition) i.r.getResource();
        genEntryItem(list, lists, table, listMM, listsMM, tableMM, i.f, i.r, i.sort, null);
      }
      fragment("list-profiles", list.toString(), otherFilesRun);
      fragment("list-simple-profiles", lists.toString(), otherFilesRun);
      fragment("table-profiles", table.toString(), otherFilesRun);
      fragment("list-profiles-mm", listMM.toString(), otherFilesRun);
      fragment("list-simple-profiles-mm", listsMM.toString(), otherFilesRun);
      fragment("table-profiles-mm", tableMM.toString(), otherFilesRun);
    }
  }

  private void generateExtensions() throws Exception {
    List<Item> items = new ArrayList<Item>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && sd.getType().equals("Extension")) {
            items.add(new Item(f, r, sd.hasTitle() ? sd.getTitle() : sd.hasName() ? sd.getName() : r.getTitle()));
          }
        }
      }
    }

    StringBuilder list = new StringBuilder();
    StringBuilder lists = new StringBuilder();
    StringBuilder table = new StringBuilder();
    StringBuilder listMM = new StringBuilder();
    StringBuilder listsMM = new StringBuilder();
    StringBuilder tableMM = new StringBuilder();
    for (Item i : items) {
      StructureDefinition sd = (StructureDefinition) i.r.getResource();
      genEntryItem(list, lists, table, listMM, listsMM, tableMM, i.f, i.r, i.sort, null);
    }
    fragment("list-extensions", list.toString(), otherFilesRun);
    fragment("list-simple-extensions", lists.toString(), otherFilesRun);
    fragment("table-extensions", table.toString(), otherFilesRun);
    fragment("list-extensions-mm", listMM.toString(), otherFilesRun);
    fragment("list-simple-extensions-mm", listsMM.toString(), otherFilesRun);
    fragment("table-extensions-mm", tableMM.toString(), otherFilesRun);
  }

  private void generateLogicals() throws Exception {
    List<Item> items = new ArrayList<Item>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals("StructureDefinition")) {
          StructureDefinition sd = (StructureDefinition) r.getResource();
          if (sd.getKind() == StructureDefinitionKind.LOGICAL) {
            items.add(new Item(f, r, sd.hasTitle() ? sd.getTitle() : sd.hasName() ? sd.getName() : r.getTitle()));
          }
        }
      }
    }

    StringBuilder list = new StringBuilder();
    StringBuilder lists = new StringBuilder();
    StringBuilder table = new StringBuilder();
    StringBuilder listMM = new StringBuilder();
    StringBuilder listsMM = new StringBuilder();
    StringBuilder tableMM = new StringBuilder();
    for (Item i : items) {
      StructureDefinition sd = (StructureDefinition) i.r.getResource();
      genEntryItem(list, lists, table, listMM, listsMM, tableMM, i.f, i.r, i.sort, null);
    }
    fragment("list-logicals", list.toString(), otherFilesRun);
    fragment("list-simple-logicals", lists.toString(), otherFilesRun);
    fragment("table-logicals", table.toString(), otherFilesRun);
    fragment("list-logicals-mm", listMM.toString(), otherFilesRun);
    fragment("list-simple-logicals-mm", listsMM.toString(), otherFilesRun);
    fragment("table-logicals-mm", tableMM.toString(), otherFilesRun);
  }

  private void genEntryItem(
        StringBuilder list, StringBuilder lists, StringBuilder table, 
        StringBuilder listMM, StringBuilder listsMM, StringBuilder tableMM, 
        FetchedFile f, FetchedResource r, String name, String prefixType) throws Exception {
    String ref = igpkp.doReplacements(igpkp.getLinkFor(r), r, null, null);
    if (Utilities.noString(ref))
      throw new Exception("No reference found for "+r.getId());
    if (prefixType != null)
      if (ref.contains("."))
        ref = ref.substring(0, ref.lastIndexOf("."))+"."+prefixType+ref.substring(ref.lastIndexOf("."));
      else
        ref = ref+"."+prefixType;
    PrimitiveType desc = new StringType(r.getTitle());
    if (r.getResource() != null && r.getResource() instanceof MetadataResource) {
      name = ((MetadataResource) r.getResource()).present();
      desc = getDesc((MetadataResource) r.getResource(), desc);
    }
    list.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a> "+Utilities.escapeXml(desc.asStringValue())+"</li>\r\n");
    lists.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a></li>\r\n");
    table.append(" <tr><td><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a> </td><td>"+new BaseRenderer(context, null, igpkp, specMaps, markdownEngine, packge).processMarkdown("description", desc )+"</td></tr>\r\n");
    
    if (listMM != null) {
      String mm = "";
      if (r.getResource() != null && r.getResource() instanceof DomainResource) {
        String fmm = ToolingExtensions.readStringExtension((DomainResource) r.getResource(), ToolingExtensions.EXT_FMM_LEVEL);
        if (fmm != null)
          mm = " <a class=\"fmm\" href=\"versions.html#maturity\" title=\"Maturity Level\">"+fmm+"</a>";
      }
      listMM.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a>"+mm+" "+Utilities.escapeXml(desc.asStringValue())+"</li>\r\n");
      listsMM.append(" <li><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a>"+mm+"</li>\r\n");
      tableMM.append(" <tr><td><a href=\""+ref+"\">"+Utilities.escapeXml(name)+"</a> </td><td>"+new BaseRenderer(context, null, igpkp, specMaps, markdownEngine, packge).processMarkdown("description", desc )+"</td><td>"+mm+"</td></tr>\r\n");
    }
  }

  private void generateResourceReferences(ResourceType rt) throws Exception {
    List<Item> items = new ArrayList<Item>();
    for (FetchedFile f : fileList) {
      for (FetchedResource r : f.getResources()) {
        if (r.getElement().fhirType().equals(rt.toString())) {
            if (r.getResource() instanceof MetadataResource) {
              MetadataResource md = (MetadataResource) r.getResource();
              items.add(new Item(f, r, md.hasTitle() ? md.getTitle() : md.hasName() ? md.getName() : r.getTitle()));
            } else
              items.add(new Item(f, r, Utilities.noString(r.getTitle()) ? r.getId() : r.getTitle()));
        }
      }
    }

    StringBuilder list = new StringBuilder();
    StringBuilder lists = new StringBuilder();
    StringBuilder table = new StringBuilder();
    StringBuilder listMM = new StringBuilder();
    StringBuilder listsMM = new StringBuilder();
    StringBuilder tableMM = new StringBuilder();
    StringBuilder listJ = new StringBuilder();
    StringBuilder listsJ = new StringBuilder();
    StringBuilder tableJ = new StringBuilder();
    StringBuilder listX = new StringBuilder();
    StringBuilder listsX = new StringBuilder();
    StringBuilder tableX = new StringBuilder();
    if (items.size() > 0) {
      for (Item i : items) {
        String name = i.r.getTitle();
        if (Utilities.noString(name))
          name = rt.toString();
        genEntryItem(list, lists, table, listMM, listsMM, tableMM, i.f, i.r, i.sort, null);
        genEntryItem(listJ, listsJ, tableJ, null, null, null, i.f, i.r, i.sort, "json");
        genEntryItem(listX, listsX, tableX, null, null, null, i.f, i.r, i.sort, "xml");
      }
    }
    fragment("list-"+Utilities.pluralizeMe(rt.toString().toLowerCase()), list.toString(), otherFilesRun);
    fragment("list-simple-"+Utilities.pluralizeMe(rt.toString().toLowerCase()), lists.toString(), otherFilesRun);
    fragment("table-"+Utilities.pluralizeMe(rt.toString().toLowerCase()), table.toString(), otherFilesRun);
    fragment("list-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-json", listJ.toString(), otherFilesRun);
    fragment("list-simple-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-json", listsJ.toString(), otherFilesRun);
    fragment("table-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-json", tableJ.toString(), otherFilesRun);
    fragment("list-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-xml", listX.toString(), otherFilesRun);
    fragment("list-simple-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-xml", listsX.toString(), otherFilesRun);
    fragment("table-"+Utilities.pluralizeMe(rt.toString().toLowerCase())+"-xml", tableX.toString(), otherFilesRun);
  }

  @SuppressWarnings("rawtypes")
  private PrimitiveType getDesc(MetadataResource r, PrimitiveType desc) {
    if (r instanceof CodeSystem) {
      CodeSystem v = (CodeSystem) r;
      if (v.hasDescription())
        return v.getDescriptionElement();
    }
    if (r instanceof ValueSet) {
      ValueSet v = (ValueSet) r;
      if (v.hasDescription())
        return v.getDescriptionElement();
    }
    if (r instanceof StructureDefinition) {
      StructureDefinition v = (StructureDefinition) r;
      if (v.hasDescription())
        return v.getDescriptionElement();
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

  private void generateNativeOutputs(FetchedFile f, boolean regen) throws IOException, FHIRException {
    for (FetchedResource r : f.getResources()) {
        dlog(LogCategory.PROGRESS, "Produce resources for "+r.getElement().fhirType()+"/"+r.getId());
        saveNativeResourceOutputs(f, r);
    }    
  }
  private void generateHtmlOutputs(FetchedFile f, boolean regen) throws TransformerException, IOException {
    if (f.getProcessMode() == FetchedFile.PROCESS_NONE) {
      String dst = tempDir;
      if (f.getRelativePath().startsWith(File.separator))
        dst = dst + f.getRelativePath();
      else
        dst = dst + File.separator + f.getRelativePath();
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
//      String dst = tempDir + f.getPath().substring(prePagesDir.length());
      String dst = tempDir;
      if (f.getRelativePath().startsWith(File.separator))
        dst = dst + f.getRelativePath();
      else
        dst = dst + File.separator + f.getRelativePath();
      try {
        if (f.isFolder()) {
          f.getOutputNames().add(dst);
          Utilities.createDirectory(dst);
        } else
          checkMakeFile(transform(f.getSource(), f.getXslt()), dst, f.getOutputNames());
      } catch (Exception e) {
        log("Exception generating page "+dst+": "+e.getMessage());
      }
    } else {
      for (FetchedResource r : f.getResources()) {
        try {
          dlog(LogCategory.PROGRESS, "Produce outputs for "+r.getElement().fhirType()+"/"+r.getId());
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


  private byte[] transform(byte[] source, byte[] xslt) throws TransformerException {
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
        map.put("sd.Type", sd.getType());
        map.put("sd.Type-plural", Utilities.pluralize(sd.getType(), 2));
        map.put("sd.type", !sd.hasType() ? "" : sd.getType().toLowerCase());
        map.put("sd.type-plural", !sd.hasType() ? "" : Utilities.pluralize(sd.getType(), 2).toLowerCase());
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
   * @throws IOException 
   * @throws FHIRException 
   * @throws FileNotFoundException
   * @throws Exception
   */
  private void saveNativeResourceOutputs(FetchedFile f, FetchedResource r) throws FHIRException, IOException {
    ByteArrayOutputStream bs = new ByteArrayOutputStream();
    new org.hl7.fhir.r5.elementmodel.JsonParser(context).compose(r.getElement(), bs, OutputStyle.NORMAL, igpkp.getCanonical());
    npm.addFile(Category.RESOURCE, r.getElement().fhirType()+"-"+r.getId()+".json", bs.toByteArray());
    if (igpkp.wantGen(r, "xml") || forHL7orFHIR()) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".xml");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.r5.elementmodel.XmlParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
    }
    if (igpkp.wantGen(r, "json") || forHL7orFHIR()) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".json");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.r5.elementmodel.JsonParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
    } 
    if (igpkp.wantGen(r, "ttl")) {
      String path = Utilities.path(tempDir, r.getElement().fhirType()+"-"+r.getId()+".ttl");
      f.getOutputNames().add(path);
      FileOutputStream stream = new FileOutputStream(path);
      new org.hl7.fhir.r5.elementmodel.TurtleParser(context).compose(r.getElement(), stream, OutputStyle.PRETTY, igpkp.getCanonical());
      stream.close();
    }    
  }

  private boolean forHL7orFHIR() {
    return igpkp.getCanonical().contains("hl7.org") || igpkp.getCanonical().contains("fhir.org") ;
  }


  private void saveDirectResourceOutputs(FetchedFile f, FetchedResource r, Map<String, String> vars) throws FileNotFoundException, Exception {
    String baseName = igpkp.getProperty(r, "base");
    if (r.getResource() != null && r.getResource() instanceof StructureDefinition) {
      if (igpkp.hasProperty(r, "template-base-"+((StructureDefinition) r.getResource()).getKind().toCode().toLowerCase()))
        genWrapper(f, r, igpkp.getProperty(r, "template-base-"+((StructureDefinition) r.getResource()).getKind().toCode().toLowerCase()), baseName, f.getOutputNames(), vars, null, "");
      else
        genWrapper(f, r, igpkp.getProperty(r, "template-base"), baseName, f.getOutputNames(), vars, null, "");
    } else
      genWrapper(f, r, igpkp.getProperty(r, "template-base"), baseName, f.getOutputNames(), vars, null, "");
    genWrapper(null, r, igpkp.getProperty(r, "template-defns"), igpkp.getProperty(r, "defns"), f.getOutputNames(), vars, null, "definitions");
    for (String templateName : extraTemplates.keySet()) {
      String output = igpkp.getProperty(r, templateName);
       if (output == null)
        output = r.getElement().fhirType()+"-"+r.getId()+"-"+templateName+".html";
      genWrapper(null, r, igpkp.getProperty(r, "template-"+templateName), output, f.getOutputNames(), vars, null, templateName);
    }
    if (igpkp.wantGen(r, "maturity") && r.getResource() != null)
      fragment(r.getResource().fhirType()+"-"+r.getId()+"-maturity",  genFmmBanner(r), f.getOutputNames());

    String template = igpkp.getProperty(r, "template-format");
    if (igpkp.wantGen(r, "xml")) {
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "xml", "");
    }
    if (igpkp.wantGen(r, "json")) {
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "json", "");
    } 

    if (igpkp.wantGen(r, "ttl")) {
      if (tool == GenerationTool.Jekyll)
        genWrapper(null, r, template, igpkp.getProperty(r, "format"), f.getOutputNames(), vars, "ttl", "");
    }

    if (igpkp.wantGen(r, "xml-html")) {
      XmlXHtmlRenderer x = new XmlXHtmlRenderer();
      org.hl7.fhir.r5.elementmodel.XmlParser xp = new org.hl7.fhir.r5.elementmodel.XmlParser(context);
      xp.setLinkResolver(igpkp);
      xp.setShowDecorations(false);
      xp.compose(r.getElement(), x);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-xml-html", x.toString(), f.getOutputNames(), r, vars, "xml");
    }
    if (igpkp.wantGen(r, "json-html")) {
      JsonXhtmlRenderer j = new JsonXhtmlRenderer();
      org.hl7.fhir.r5.elementmodel.JsonParser jp = new org.hl7.fhir.r5.elementmodel.JsonParser(context);
      jp.setLinkResolver(igpkp);
      jp.compose(r.getElement(), j);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-json-html", j.toString(), f.getOutputNames(), r, vars, "json");
    }

    if (igpkp.wantGen(r, "ttl-html")) {
      org.hl7.fhir.r5.elementmodel.TurtleParser ttl = new org.hl7.fhir.r5.elementmodel.TurtleParser(context);
      ttl.setLinkResolver(igpkp);
      Turtle rdf = new Turtle();
      ttl.compose(r.getElement(), rdf, "");
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-ttl-html", rdf.asHtml(), f.getOutputNames(), r, vars, "ttl");
    }

    if (igpkp.wantGen(r, "html")) {
      XhtmlNode xhtml = getXhtml(r);
      String html = xhtml == null ? "" : new XhtmlComposer(XhtmlComposer.XML).compose(xhtml);
      fragment(r.getElement().fhirType()+"-"+r.getId()+"-html", html, f.getOutputNames(), r, vars, null);
    }
    //  NarrativeGenerator gen = new NarrativeGenerator(null, null, context);
    //  gen.generate(f.getElement(), false);
    //  xhtml = getXhtml(f);
    //  html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
    //  fragment(f.getId()+"-gen-html", html);
  }

  private String genFmmBanner(FetchedResource r) throws FHIRException {
    String fmm = null;
    StandardsStatus ss = null;
    if (r.getResource() instanceof DomainResource) {
      fmm = ToolingExtensions.readStringExtension((DomainResource) r.getResource(), ToolingExtensions.EXT_FMM_LEVEL);
      ss = ToolingExtensions.getStandardsStatus((DomainResource) r.getResource());
    }
    if (ss == null)
      ss = StandardsStatus.TRIAL_USE;
    if (fmm != null)
      return "<table class=\"cols\"><tbody><tr>"+
        "<td><a href=\""+checkAppendSlash(specPath)+"versions.html#maturity\">Maturity Level</a>: "+fmm+"</td>"+
        "<td>&nbsp;<a href=\""+checkAppendSlash(specPath)+"versions.html#std-process\" title=\"Standard Status\">"+ss.toDisplay()+"</a></td>"+
        "</tr></tbody></table>";
    else
      return "";
  }


  private void genWrapper(FetchedFile ff, FetchedResource r, String template, String outputName, Set<String> outputTracker, Map<String, String> vars, String format, String extension) throws FileNotFoundException, IOException, FHIRException {
    if (template != null && !template.isEmpty()) {
      boolean existsAsPage = false;
      if (ff != null) {
        String fn = igpkp.getLinkFor(r);
        for (String pagesDir: pagesDirs) {
          if (altMap.containsKey("page/"+Utilities.path(pagesDir, fn))) {
            existsAsPage = true;
            break;
          }
        }
        if (!existsAsPage && !prePagesDirs.isEmpty()) {
          for (String prePagesDir : prePagesDirs) {
            if (altMap.containsKey("page/"+Utilities.path(prePagesDir, fn))) {
              existsAsPage = true;
              break;
            }
          }
        }
      }
      if (!existsAsPage) {
        template = fetcher.openAsString(Utilities.path(fetcher.pathForFile(configFile), template));
        template = igpkp.doReplacements(template, r, vars, format);

        outputName = determineOutputName(outputName, r, vars, format, extension);
        if (!outputName.contains("#")) {
          String path = Utilities.path(tempDir, outputName);
          checkMakeFile(template.getBytes(Charsets.UTF_8), path, outputTracker);
        }
      }
    }
  }

  private String determineOutputName(String outputName, FetchedResource r, Map<String, String> vars, String format, String extension) throws FHIRException {
    if (outputName == null)
      outputName = "{{[type]}}-{{[id]}}"+(extension.equals("")? "":"-"+extension)+(format==null? "": ".{{[fmt]}}")+".html";
    if (outputName.contains("{{["))
      outputName = igpkp.doReplacements(outputName, r, vars, format);
    return outputName;
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
    CodeSystemRenderer csr = new CodeSystemRenderer(context, specPath, cs, igpkp, specMaps, markdownEngine, packge);
    if (igpkp.wantGen(fr, "summary"))
      fragment("CodeSystem-"+cs.getId()+"-summary", csr.summary(igpkp.wantGen(fr, "xml"), igpkp.wantGen(fr, "json"), igpkp.wantGen(fr, "ttl")), f.getOutputNames(), fr, vars, null);
    if (igpkp.wantGen(fr, "content"))
      fragment("CodeSystem-"+cs.getId()+"-content", csr.content(otherFilesRun), f.getOutputNames(), fr, vars, null);
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
    ValueSetRenderer vsr = new ValueSetRenderer(context, specPath, vs, igpkp, specMaps, markdownEngine, packge);
    if (igpkp.wantGen(r, "summary"))
      fragment("ValueSet-"+vs.getId()+"-summary", vsr.summary(r, igpkp.wantGen(r, "xml"), igpkp.wantGen(r, "json"), igpkp.wantGen(r, "ttl")), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "cld"))
      try {
        fragment("ValueSet-"+vs.getId()+"-cld", vsr.cld(otherFilesRun), f.getOutputNames(), r, vars, null);
      } catch (Exception e) {
        fragmentError(vs.getId()+"-cld", e.getMessage(), null, f.getOutputNames());
      }

    if (igpkp.wantGen(r, "xref"))
      fragment("ValueSet-"+vs.getId()+"-xref", vsr.xref(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "expansion")) {
      ValueSetExpansionOutcome exp = context.expandVS(vs, true, true);
      if (exp.getValueset() != null) {
        expansions.add(exp.getValueset());
                
        NarrativeGenerator gen = new NarrativeGenerator("", null, context).setLiquidServices(templateProvider, validator.getExternalHostServices());
        gen.setTooCostlyNoteNotEmpty("This value set has >1000 codes in it. In order to keep the publication size manageable, only a selection (1000 codes) of the whole set of codes is shown");
        gen.setTooCostlyNoteEmpty("This value set cannot be expanded because of the way it is defined - it has an infinite number of members");
        exp.getValueset().setCompose(null);
        exp.getValueset().setText(null);
        gen.generate(null, exp.getValueset(), false);
        String html = new XhtmlComposer(XhtmlComposer.XML).compose(exp.getValueset().getText().getDiv());
        fragment("ValueSet-"+vs.getId()+"-expansion", html, f.getOutputNames(), r, vars, null);
        
      } else {
        if (exp.getError() != null) { 
          fragmentError("ValueSet-"+vs.getId()+"-expansion", "No Expansion for this valueset (not supported by Publication Tooling)", "Publication Tooling Error: "+exp.getError(), f.getOutputNames());
          f.getErrors().add(new ValidationMessage(Source.TerminologyEngine, IssueType.EXCEPTION, vs.getId(), exp.getError(), IssueSeverity.ERROR).setTxLink(exp.getTxLink()));
        } else {
          fragmentError("ValueSet-"+vs.getId()+"-expansion", "No Expansion for this valueset (not supported by Publication Tooling)", "Unknown Error", f.getOutputNames());
          f.getErrors().add(new ValidationMessage(Source.TerminologyEngine, IssueType.EXCEPTION, vs.getId(), "Unknown Error expanding ValueSet", IssueSeverity.ERROR).setTxLink(exp.getTxLink()));
        }
      }
    }
  }

  private void fragmentError(String name, String error, String overlay, Set<String> outputTracker) throws IOException, FHIRException {
    if (Utilities.noString(overlay))
      fragment(name, "<p><span style=\"color: maroon; font-weight: bold\">"+Utilities.escapeXml(error)+"</span></p>\r\n", outputTracker);
    else
      fragment(name, "<p><span style=\"color: maroon; font-weight: bold\" title=\""+Utilities.escapeXml(overlay)+"\">"+Utilities.escapeXml(error)+"</span></p>\r\n", outputTracker);
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws IOException
   */
  private void generateOutputsConceptMap(FetchedFile f, FetchedResource r, ConceptMap cm, Map<String, String> vars) throws IOException, FHIRException {
    if (igpkp.wantGen(r, "summary"))
      fragmentError("ConceptMap-"+cm.getId()+"-summary", "yet to be done: concept map summary", null, f.getOutputNames());
    if (igpkp.wantGen(r, "content"))
      fragmentError("ConceptMap-"+cm.getId()+"-content", "yet to be done: table presentation of the concept map", null, f.getOutputNames());
    if (igpkp.wantGen(r, "xref"))
      fragmentError("ConceptMap-"+cm.getId()+"-xref", "yet to be done: list of all places where concept map is used", null, f.getOutputNames());
  }

  private void generateOutputsCapabilityStatement(FetchedFile f, FetchedResource r, CapabilityStatement cpbs, Map<String, String> vars) throws Exception {
    if (igpkp.wantGen(r, "swagger") || igpkp.wantGen(r, "openapi")) {
      Writer oa = null;
      if (openApiTemplate != null) 
        oa = new Writer(new FileOutputStream(Utilities.path(tempDir, cpbs.getId()+ ".openapi.json")), new FileInputStream(Utilities.path(Utilities.getDirectoryForFile(configFile), openApiTemplate)));
      else
        oa = new Writer(new FileOutputStream(Utilities.path(tempDir, cpbs.getId()+ ".openapi.json")));
      String lic = license();
      String displ = context.doValidateCode(new Coding("http://hl7.org/fhir/spdx-license",  lic, null), null, false).getDisplay();
      new OpenApiGenerator(context, cpbs, oa).generate(displ, "http://spdx.org/licenses/"+lic+".html");
      oa.commit();
      otherFilesRun.add(Utilities.path(tempDir, cpbs.getId()+ ".openapi.json"));
      npm.addFile(Category.OPENAPI, cpbs.getId()+ ".openapi.json", TextFile.fileToBytes(Utilities.path(tempDir, cpbs.getId()+ ".openapi.json")));
    }
  }

  private void generateOutputsStructureDefinition(FetchedFile f, FetchedResource r, StructureDefinition sd, Map<String, String> vars, boolean regen) throws Exception {
    // todo : generate shex itself
    if (igpkp.wantGen(r, "shex"))
      fragmentError("StructureDefinition-"+sd.getId()+"-shex", "yet to be done: shex as html", null, f.getOutputNames());

    // todo : generate json schema itself. JSON Schema generator
//    if (igpkp.wantGen(r, ".schema.json")) {
//      String path = Utilities.path(tempDir, r.getId()+".sch");
//      f.getOutputNames().add(path);
//      new ProfileUtilities(context, errors, igpkp).generateSchematrons(new FileOutputStream(path), sd);
//    }
    if (igpkp.wantGen(r, "json-schema"))
      fragmentError("StructureDefinition-"+sd.getId()+"-json-schema", "yet to be done: json schema as html", null, f.getOutputNames());

    StructureDefinitionRenderer sdr = new StructureDefinitionRenderer(context, checkAppendSlash(specPath), sd, Utilities.path(tempDir), igpkp, specMaps, markdownEngine, packge);
    if (igpkp.wantGen(r, "summary"))
      fragment("StructureDefinition-"+sd.getId()+"-summary", sdr.summary(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "header"))
      fragment("StructureDefinition-"+sd.getId()+"-header", sdr.header(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "diff"))
      fragment("StructureDefinition-"+sd.getId()+"-diff", sdr.diff(igpkp.getDefinitionsName(r), otherFilesRun), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "snapshot"))
      fragment("StructureDefinition-"+sd.getId()+"-snapshot", sdr.snapshot(igpkp.getDefinitionsName(r), otherFilesRun), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "grid"))
      fragment("StructureDefinition-"+sd.getId()+"-grid", sdr.grid(igpkp.getDefinitionsName(r), otherFilesRun), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "pseudo-xml"))
      fragmentError("StructureDefinition-"+sd.getId()+"-pseudo-xml", "yet to be done: Xml template", null, f.getOutputNames());
    if (igpkp.wantGen(r, "pseudo-json"))
      fragment("StructureDefinition-"+sd.getId()+"-pseudo-json", sdr.pseudoJson(), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "pseudo-ttl"))
      fragmentError("StructureDefinition-"+sd.getId()+"-pseudo-ttl", "yet to be done: Turtle template", null, f.getOutputNames());
    if (igpkp.wantGen(r, "uml"))
      fragmentError("StructureDefinition-"+sd.getId()+"-uml", "yet to be done: UML as SVG", null, f.getOutputNames());
    if (igpkp.wantGen(r, "tx"))
      fragment("StructureDefinition-"+sd.getId()+"-tx", sdr.tx(includeHeadings, true), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "tx-must-support"))
      fragment("StructureDefinition-"+sd.getId()+"-tx-must-support", sdr.tx(includeHeadings, false), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "tx-diff"))
      fragment("StructureDefinition-"+sd.getId()+"-tx-diff", sdr.txDiff(includeHeadings, true), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "tx-diff-must-support"))
      fragment("StructureDefinition-"+sd.getId()+"-tx-diff-must-support", sdr.txDiff(includeHeadings, false), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "inv"))
      fragment("StructureDefinition-"+sd.getId()+"-inv", sdr.inv(includeHeadings), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "dict"))
      fragment("StructureDefinition-"+sd.getId()+"-dict", sdr.dict(true), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "dict-active"))
      fragment("StructureDefinition-"+sd.getId()+"-dict-active", sdr.dict(false), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "maps"))
      fragment("StructureDefinition-"+sd.getId()+"-maps", sdr.mappings(false), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "maps"))
      fragment("StructureDefinition-"+sd.getId()+"-maps-all", sdr.mappings(true), f.getOutputNames(), r, vars, null);
    if (igpkp.wantGen(r, "xref"))
      fragmentError("StructureDefinition-"+sd.getId()+"-sd-xref", "Yet to be done: xref", null, f.getOutputNames());
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && igpkp.wantGen(r, "span"))
      fragment("StructureDefinition-"+sd.getId()+"-span", sdr.span(true, igpkp.getCanonical(), otherFilesRun), f.getOutputNames(), r, vars, null);
    if (sd.getDerivation() == TypeDerivationRule.CONSTRAINT && igpkp.wantGen(r, "spanall"))
      fragment("StructureDefinition-"+sd.getId()+"-spanall", sdr.span(true, igpkp.getCanonical(), otherFilesRun), f.getOutputNames(), r, vars, null);

    if (igpkp.wantGen(r, "example-list"))
      fragment("StructureDefinition-example-list-"+sd.getId(), sdr.exampleList(fileList), f.getOutputNames(), r, vars, null);

    if (igpkp.wantGen(r, "csv")) {
      String path = Utilities.path(tempDir, r.getId()+".csv");
      f.getOutputNames().add(path);
      new ProfileUtilities(context, errors, igpkp).generateCsvs(new FileOutputStream(path), sd, true);
    }

    if (igpkp.wantGen(r, "java")) {
      ConstraintJavaGenerator jg = new ConstraintJavaGenerator(context, version, tempDir, sourceIg.getUrl());
      try {
        f.getOutputNames().add(jg.generate(sd));
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    if (igpkp.wantGen(r, "xlsx")) {
      String path = Utilities.path(tempDir, r.getId()+".xlsx");
      f.getOutputNames().add(path);
      new ProfileUtilities(context, errors, igpkp).generateXlsx(new FileOutputStream(path), sd, true);
    }

    if (!regen && sd.getKind() != StructureDefinitionKind.LOGICAL &&  igpkp.wantGen(r, "sch")) {
      String path = Utilities.path(tempDir, r.getId()+".sch");
      f.getOutputNames().add(path);
      new ProfileUtilities(context, errors, igpkp).generateSchematrons(new FileOutputStream(path), sd);
      npm.addFile(Category.SCHEMATRON, r.getId()+".sch", IOUtils.toByteArray(Utilities.path(tempDir, r.getId()+".sch")));
    }
    if (igpkp.wantGen(r, "sch"))
      fragmentError("StructureDefinition-"+sd.getId()+"-sch", "yet to be done: schematron as html", null, f.getOutputNames());
  }

  private String checkAppendSlash(String s) {
    return s.endsWith("/") ? s : s+"/";
  }

  private void generateOutputsStructureMap(FetchedFile f, FetchedResource r, StructureMap map, Map<String,String> vars) throws Exception {
    StructureMapRenderer smr = new StructureMapRenderer(context, checkAppendSlash(specPath), map, Utilities.path(tempDir), igpkp, specMaps, markdownEngine, packge);
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

  private XhtmlNode getXhtml(FetchedResource r) throws FHIRException {
    if (r.getResource() != null && r.getResource() instanceof DomainResource) {
      DomainResource dr = (DomainResource) r.getResource();
      if (dr.getText().hasDiv())
        return dr.getText().getDiv();
      else
        return null;
    }
    if (r.getResource() != null && r.getResource() instanceof Bundle) {
      Bundle b = (Bundle) r.getResource();
      return new NarrativeGenerator("",  null, context).setLiquidServices(templateProvider, validator.getExternalHostServices()).renderBundle(b);
    }
    if (r.getElement().fhirType().equals("Bundle")) {
      return new NarrativeGenerator("",  null, context).renderBundle(r.getElement());
    } else {
      return getHtmlForResource(r.getElement());
    }
  }

  private XhtmlNode getHtmlForResource(Element element) {
    Element text = element.getNamedChild("text");
    if (text == null)
      return null;
    Element div = text.getNamedChild("div");
    if (div == null)
      return null;
    else
      return div.getXhtml();
  }

  private void fragment(String name, String content, Set<String> outputTracker) throws IOException, FHIRException {
    fragment(name, content, outputTracker, null, null, null);
  }
  private void fragment(String name, String content, Set<String> outputTracker, FetchedResource r, Map<String, String> vars, String format) throws IOException, FHIRException {
    String fixedContent = (r==null? content : igpkp.doReplacements(content, r, vars, format));
    if (checkMakeFile(fixedContent.getBytes(Charsets.UTF_8), Utilities.path(tempDir, "_includes", name+".xhtml"), outputTracker)) {
      if (mode != IGBuildMode.AUTOBUILD && makeQA)
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

  private static void runGUI() throws InterruptedException, InvocationTargetException {
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

  public void setTxServer(String s) {
    if (!Utilities.noString(s))
      txServer = s;
  }

  String getTxServer() {
    return txServer;
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
    fetcher.setLogger(logger);
  }

  public String getQAFile() throws IOException {
    return Utilities.path(outputDir, "qa.html");
  }

  @Override
  public void logMessage(String msg) {
    System.out.println(msg);
    if (mode == IGBuildMode.MANUAL) {
      try {
        String logPath = Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-publisher-tmp.log");
        if (filelog==null) {
          filelog = new StringBuilder();
          System.out.println("File log: " + logPath);
        }
        filelog.append(msg+"\r\n");
        TextFile.stringToFile(filelog.toString(), logPath);
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
  }

  @Override
  public void logDebugMessage(LogCategory category, String msg) {
    if (logOptions.contains(category.toString().toLowerCase()))
      logMessage(msg);
  }

  public static String buildReport(String ig, String source, String log, String qafile) throws Exception {
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
    b.append(txServer);
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
      b.append(TextFile.fileToString(determineActualIG(ig)));
    }

    b.append("\r\n");
    b.append("\r\n");
    return b.toString();
  }


  public static void main(String[] args) throws Exception {
    int exitCode = 0;
    if (hasParam(args, "-gui") || args.length == 0) {
      runGUI();
      // Returning here ends the main thread but leaves the GUI running
      return; 
    } else if (hasParam(args, "-help") || hasParam(args, "-?") || hasParam(args, "/?") || hasParam(args, "?")) {
      System.out.println("");
      System.out.println("To use this publisher to publish a FHIR Implementation Guide, run ");
      System.out.println("with the commands");
      System.out.println("");
      System.out.println("-spec [igpack.zip] -ig [source] -tx [url] -packages [path] -watch");
      System.out.println("");
      System.out.println("-spec: a path or a url where the igpack for the version of the core FHIR");
      System.out.println("  specification used by the ig being published is located.  If not specified");
      System.out.println("  the tool will retrieve the file from the web based on the specified FHIR version");
      System.out.println("-ig: a path or a url where the implementation guide control file is found");
      System.out.println("  see Wiki for Documentation");
      System.out.println("-tx: (optional) Address to use for terminology server ");
      System.out.println("  (default is http://tx.fhir.org)");
      System.out.println("  use 'n/a' to run without a terminology server");
      System.out.println("-watch (optional): if this is present, the publisher will not terminate;");
      System.out.println("  instead, it will stay running, an watch for changes to the IG or its ");
      System.out.println("  contents and re-run when it sees changes ");
      System.out.println("");
      System.out.println("-packages: a directory to load packages (*.tgz) from before resolving dependencies");
      System.out.println("           this parameter can be present multiple times");
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
      System.out.println("the publisher also supports the param -proxy=[address]:[port] for if you use a proxy (stupid java won't pick up the system settings)");
      System.out.println("");
      System.out.println("For additional information, see http://wiki.hl7.org/index.php?title=Proposed_new_FHIR_IG_build_Process");
    } else if (hasParam(args, "-convert")) {
      // convert a igpack.zip to a package.tgz
      IGPack2NpmConvertor conv = new IGPack2NpmConvertor();
      conv.setSource(getNamedParam(args, "-source"));
      conv.setDest(getNamedParam(args, "-dest"));
      conv.setPackageId(getNamedParam(args, "-npm-name"));
      conv.setVersionIg(getNamedParam(args, "-version"));
      conv.setLicense(getNamedParam(args, "-license"));
      conv.setWebsite(getNamedParam(args, "-website"));
      conv.execute();
    } else if (hasParam(args, "publish-update")) {
      throw new Error("not done yet");      
    } else if (hasParam(args, "-multi")) {
      int i = 1;
      for (String ig : TextFile.fileToString(getNamedParam(args, "-multi")).split("\\r?\\n")) {
        if (!ig.startsWith(";")) {
          System.out.println("=======================================================================================");
          System.out.println("Publish IG "+ig);
          Publisher self = new Publisher();
          self.setConfigFile(determineActualIG(ig));
          self.setTxServer(getNamedParam(args, "-tx"));
          if (hasParam(args, "-resetTx"))
            self.setCacheOption(CacheOption.CLEAR_ALL);
          else if (hasParam(args, "-resetTxErrors"))
            self.setCacheOption(CacheOption.CLEAR_ERRORS);
          else
            self.setCacheOption(CacheOption.LEAVE);
          try {
              self.execute();
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
      Publisher self = new Publisher();
      System.out.println("FHIR Implementation Guide Publisher (v"+self.getToolingVersion()+", gen-code v"+Constants.VERSION+" / "+ToolsVersion.TOOLS_VERSION_STR+") @ "+nowAsString());
      System.out.println("Detected Java version: " + System.getProperty("java.version")+" from "+System.getProperty("java.home")+" on "+System.getProperty("os.arch")+" ("+System.getProperty("sun.arch.data.model")+"bit). "+toMB(Runtime.getRuntime().maxMemory())+"MB available");
      System.out.print("["+System.getProperty("user.dir")+"]");
      for (int i = 0; i < args.length; i++) {
          System.out.print(" "+args[i]);
      }      
      System.out.println();
      if (hasParam(args, "-source")) {
        // run with standard template. this is publishing lite
        self.setSourceDir(getNamedParam(args, "-source"));
        self.setDestDir(getNamedParam(args, "-destination"));
        self.specifiedVersion = getNamedParam(args, "-version");
      } else if(!hasParam(args, "-ig") && args.length == 1 && new File(args[0]).exists()) {
        self.setConfigFile(args[0]);
      } else if (hasParam(args, "-prompt")) {
        IniFile ini = new IniFile("publisher.ini");
        String last = ini.getStringProperty("execute", "path");
        boolean ok = false;
        if (Utilities.noString(last)) {
          while (!ok) {
            System.out.print("Enter path of IG: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            last = reader.readLine();
            if (new File(last).exists())
              ok = true;
            else
              System.out.println("Can't find "+last);
          } 
        } else {
          while (!ok) {
            System.out.print("Enter path of IG ["+last+"]: ");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String nlast = reader.readLine();
            if (Utilities.noString(nlast))
              nlast = last;
            if (new File(nlast).exists()) {
              ok = true;
              last = nlast;
            } else
              System.out.println("Can't find "+nlast);
          }
        }
        ini.setStringProperty("execute", "path", last, null);
        ini.save();
        if (new File(last).isDirectory())
          self.setConfigFile(Utilities.path(last, "ig.json"));
        else
          self.setConfigFile(last);
      } else if (hasParam(args, "-simplifier")) {
        if (!hasParam(args, "-destination"))
          throw new Exception("A destination folder (-destination) must be provided for the output from processing the simplifier IG");
        if (!hasParam(args, "-canonical"))
          throw new Exception("A canonical URL (-canonical) must be provided in order to process a simplifier IG");
        if (!hasParam(args, "-npm-name"))
          throw new Exception("A package name (-npm-name) must be provided in order to process a simplifier IG");
        if (!hasParam(args, "-license"))
          throw new Exception("A license code (-license) must be provided in order to process a simplifier IG");
        List<String> packages = new ArrayList<String>();
        for (int i = 0; i < args.length; i++) {
          if (args[i].equals("-dependsOn")) 
            packages.add(args[i+1]);
        }
        // create an appropriate ig.json in the specified folder
        self.setConfigFile(generateIGFromSimplifier(getNamedParam(args, "-simplifier"), getNamedParam(args, "-destination"), getNamedParam(args, "-canonical"), getNamedParam(args, "-npm-name"), getNamedParam(args, "-license"), packages));
        self.folderToDelete = Utilities.getDirectoryForFile(self.getConfigFile());
      } else {
        self.setConfigFile(determineActualIG(getNamedParam(args, "-ig")));
        if (Utilities.noString(self.getConfigFile()))
          throw new Exception("No Implementation Guide Specified (-ig parameter)");
        if (!(new File(self.getConfigFile()).isAbsolute()))
          self.setConfigFile(Utilities.path(System.getProperty("user.dir"), self.getConfigFile()));
      }
      self.setJekyllCommand(getNamedParam(args, "-jekyll"));
      self.setIgPack(getNamedParam(args, "-spec"));
      String proxy = getNamedParam(args, "-proxy");
      if (!Utilities.noString(proxy)) {
        String[] p = proxy.split("\\:");
        System.setProperty("http.proxyHost", p[0]);
        System.setProperty("http.proxyPort", p[1]);
      }
      self.setTxServer(getNamedParam(args, "-tx"));
      self.setPackagesFolder(getNamedParam(args, "-packages"));
      if (hasNamedParam(args, "-auto-ig-build")) {
        self.setMode(IGBuildMode.AUTOBUILD);
        self.targetOutput = getNamedParam(args, "-target");
      }
      self.watch = hasParam(args, "-watch");
      self.debug = hasParam(args, "-debug");
      self.cacheVersion = hasParam(args, "-cacheVersion");
      if (hasParam(args, "-publish")) {
        self.setMode(IGBuildMode.PUBLICATION);
        self.targetOutput = getNamedParam(args, "-publish");        
        self.targetOutputNested = getNamedParam(args, "-nested");        
      }
      if (hasParam(args, "-resetTx"))
        self.setCacheOption(CacheOption.CLEAR_ALL);
      else if (hasParam(args, "-resetTxErrors"))
        self.setCacheOption(CacheOption.CLEAR_ERRORS);
      else
        self.setCacheOption(CacheOption.LEAVE);
      try {
        self.execute();
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
        if (self.mode == IGBuildMode.MANUAL) {
          TextFile.stringToFile(buildReport(getNamedParam(args, "-ig"), getNamedParam(args, "-source"), self.filelog.toString(), Utilities.path(self.qaDir, "validation.txt")), Utilities.path(System.getProperty("java.io.tmpdir"), "fhir-ig-publisher.log"));
        }
      }
    }
    System.exit(exitCode);
  }

  private static String generateIGFromSimplifier(String folder, String output, String canonical, String npmName, String license, List<String> packages) throws Exception {
    String ig = Utilities.path(System.getProperty("java.io.tmpdir"), "simplifier", UUID.randomUUID().toString().toLowerCase());
    Utilities.createDirectory(ig);
    String config = Utilities.path(ig, "ig.json");
    String pages =  Utilities.path(ig, "pages");
    String resources =  Utilities.path(ig, "resources");
    Utilities.createDirectory(pages);
    Utilities.createDirectory(resources);
    Utilities.createDirectory(Utilities.path(ig, "temp"));
    Utilities.createDirectory(Utilities.path(ig, "txCache"));
    // now, copy the entire simplifer folder to pages
    Utilities.copyDirectory(folder, pages, null);
    // now, copy the resources to resources;
    Utilities.copyDirectory(Utilities.path(folder, "artifacts"), resources, null);
    JsonObject json = new JsonObject();
    JsonObject paths = new JsonObject();
    json.add("paths", paths);
    JsonArray reslist = new JsonArray();
    paths.add("resources", reslist);
    reslist.add(new JsonPrimitive("resources"));
    paths.addProperty("pages", "pages");
    paths.addProperty("temp", "temp");
    paths.addProperty("output", output);
    paths.addProperty("qa", "qa");
    paths.addProperty("specification", "http://build.fhir.org");
    json.addProperty("version", "3.0.1");
    json.addProperty("license", license);
    json.addProperty("npm-name", npmName);
    JsonObject defaults = new JsonObject();
    json.add("defaults", defaults);
    JsonObject any = new JsonObject();
    defaults.add("Any", any);
    any.addProperty("java", false);
    any.addProperty("xml", false);
    any.addProperty("json", false);
    any.addProperty("ttl", false);
    json.addProperty("canonicalBase", canonical);
    json.addProperty("sct-edition", "http://snomed.info/sct/731000124108");
    json.addProperty("source", determineSource(resources, Utilities.path(folder, "artifacts")));
    json.addProperty("path-pattern", "[type]-[id].html");
    JsonObject resn = new JsonObject();
    json.add("resources", resn);
    resn.addProperty("*", "*");
    JsonArray deplist = new JsonArray();
    json.add("dependencyList", deplist);
    for (String d : packages) {
      String[] p = d.split("\\#");
      JsonObject dep = new JsonObject();
      deplist.add(dep);
      dep.addProperty("package", p[0]);
      dep.addProperty("version", p[1]);
      dep.addProperty("name", "n"+deplist.size());
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    TextFile.stringToFile(gson.toJson(json), config);
    return config;
  }


  private static String determineSource(String folder, String srcF) throws Exception {
    for (File f : new File(folder).listFiles()) {
      String src = TextFile.fileToString(f);
      if (src.contains("<ImplementationGuide "))
        return f.getName();
    }
    throw new Exception("Unable to find Implementation Guide in "+srcF); 
  }


  private void setPackagesFolder(String value) {
    packagesFolder = value;    
  }


  public void setJekyllCommand(String theJekyllCommand) {
    if (!Utilities.noString(theJekyllCommand)) {
      this.jekyllCommand = theJekyllCommand;
    }
  }

  public String getJekyllCommand() {
    return this.jekyllCommand;
  }



  private static String determineActualIG(String ig) throws Exception {
    File f = new File(ig);
    if (!f.exists())
      throw new Exception("Unable to find the nominated IG at "+f.getAbsolutePath());
    if (f.isDirectory() && new File(Utilities.path(ig, "ig.json")).exists())
      return Utilities.path(ig, "ig.json");
    else
      return ig;
  }


  private String getToolingVersion() {
    InputStream vis = Publisher.class.getResourceAsStream("/version.info");
    if (vis != null) {
      IniFile vi = new IniFile(vis);
      if (vi.getStringProperty("FHIR", "buildId") != null)
        return vi.getStringProperty("FHIR", "version")+"-"+vi.getStringProperty("FHIR", "buildId");
      else
        return vi.getStringProperty("FHIR", "version")+"-"+vi.getStringProperty("FHIR", "revision");
    }
    return "?? (not a build IGPublisher?)";
  }


  private static String toMB(long maxMemory) {
    return Long.toString(maxMemory / (1024*1024));
  }


  public IGBuildMode getMode() {
    return mode;
  }


  public void setMode(IGBuildMode mode) {
    this.mode = mode;
  }


  private static String nowAsString() {
    Calendar cal = Calendar.getInstance();
    DateFormat df = DateFormat.getDateTimeInstance(DateFormat.FULL, DateFormat.MEDIUM);
    return df.format(cal.getTime());
  }


  public Map<String, SpecificationPackage> getSpecifications() {
    return specifications;
  }


  public void setSpecifications(Map<String, SpecificationPackage> specifications) {
    this.specifications = specifications;
  }


  public void setFetcher(ZipFetcher theFetcher) {
    fetcher = theFetcher;
  }


  public void setContext(SimpleWorkerContext theContext) {
    context = theContext;
  }


  public void setSpecPath(String theSpecPath) {
    specPath = theSpecPath;
  }


  public void setTempDir(String theTempDir) {
    tempDir = theTempDir;
  }

  public void setOutputDir(String theDir) {
    outputDir = theDir;
  }


  public void setIgName(String theIgName) {
    igName = theIgName;
  }

  public void setConfigFileRootPath(String theConfigFileRootPath) {
    configFileRootPath = theConfigFileRootPath;
  }


  public FHIRToolingClient getWebTxServer() {
    return webTxServer;
  }


  public void setWebTxServer(FHIRToolingClient webTxServer) {
    this.webTxServer = webTxServer;
  }

  public void setDebug(boolean theDebug) {
    this.debug = theDebug;
  }

  public void setIsChild(boolean newIsChild) {
    this.isChild = newIsChild;
  }
  
  public boolean isChild() {
    return this.isChild;
  }
  
  public IGKnowledgeProvider getIgpkp() {
    return igpkp;
  }

  public List<FetchedFile> getFileList() {
    return fileList;
  }

  public ImplementationGuide getSourceIg() {
    return sourceIg;
  }


  public String getTargetOutput() {
    return targetOutput;
  }


  public void setTargetOutput(String targetOutput) {
    this.targetOutput = targetOutput;
  }


  public String getTargetOutputNested() {
    return targetOutputNested;
  }


  public void setTargetOutputNested(String targetOutputNested) {
    this.targetOutputNested = targetOutputNested;
  }
  
  private void updateInspector(HTLMLInspector parentInspector, String path) {
    parentInspector.getManual().add(path+"/full-ig.zip");
    parentInspector.getManual().add("../"+historyPage);
    parentInspector.getSpecMaps().addAll(specMaps);
  }
  
}
