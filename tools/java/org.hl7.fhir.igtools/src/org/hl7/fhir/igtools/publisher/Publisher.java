package org.hl7.fhir.igtools.publisher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.metamodel.Element;
import org.hl7.fhir.dstu3.metamodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ProfileUtilities;
import org.hl7.fhir.dstu3.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.Turtle;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.igtools.renderers.JsonXhtmlRenderer;
import org.hl7.fhir.igtools.renderers.StructureDefinitionRenderer;
import org.hl7.fhir.igtools.renderers.ValidationPresenter;
import org.hl7.fhir.igtools.renderers.XmlXHtmlRenderer;
import org.hl7.fhir.igtools.renderers.ValidationPresenter.ValidationOutcomes;
import org.hl7.fhir.igtools.renderers.ValueSetRenderer;
import org.hl7.fhir.rdf.RdfGenerator;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.w3c.dom.Document;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

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
  private String pathToSpec;
  private String configFile;
  private String output;
  private String txServer = "http://local.healthintersections.com.au:960/open";
  private boolean reside;

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

  private Map<ImplementationGuidePackageResourceComponent, FetchedFile> fileMap = new HashMap<ImplementationGuidePackageResourceComponent, FetchedFile>();
  private List<FetchedFile> fileList = new ArrayList<FetchedFile>();
  private List<Resource> loaded = new ArrayList<Resource>();
  private ImplementationGuide ig;
  private List<ValidationOutcomes> errs = new ArrayList<ValidationOutcomes>();

  private void execute() throws Exception {
    initialize();

    load();
    validate();
    generate();
    log("  ... Validation output in "+new ValidationPresenter(context).generate(ig.getName(), errs, Utilities.path(validationDir, "validation.html")));

    while (reside) { // terminated externally
      System.out.println("Watching for changes on a 5sec cycle");
      wait(5000);
      if (load()) {
        validate();
        generate();
        log("  ... Validation output in "+new ValidationPresenter(context).generate(ig.getName(), errs, Utilities.path(validationDir, "validation.html")));
      }
    }
    log("Done");
  }

  private void initialize() throws Exception {
    log("Load Configuration");
    JsonObject obj = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(configFile));
    igName = Utilities.path(Utilities.getDirectoryForFile(configFile), obj.get("source").getAsString());

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
      context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\igpack.zip");
    }
    context.setAllowLoadingDuplicates(true);
    log("Connect to Terminology Server");
    context.connectToTSServer(txServer);
    validator = new InstanceValidator(context);
    validator.setAllowXsiLocation(true);

    loadSpecDetails(context.getBinaries().get("spec.internals"));
    ValidationOutcomes e = new ValidationOutcomes(new InternalFile());
    errs.add(e);
    igpkp = new IGKnowledgeProvider(context, pathToSpec, obj, e);
    igpkp.loadSpecPaths(specDetails.get("paths").getAsJsonObject());
    for (String s : context.getBinaries().keySet())
      if (needFile(s)) {
        TextFile.bytesToFile(context.getBinaries().get(s), Utilities.path(contentDir, s));    
        TextFile.bytesToFile(context.getBinaries().get(s), Utilities.path(validationDir, s));
      }
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
    for (Resource r : loaded)
      context.dropResource(r);

    fileList.clear();
    boolean needToBuild = false;
    log("Load Implementation Guide");
    FetchedFile igf = fetcher.fetch(igName);
    needToBuild = noteFile(null, igf) || needToBuild;
    if (needToBuild) {
      ig = (ImplementationGuide) parse(igf);
      igf.setType(ResourceType.ImplementationGuide);
      igf.setResource(ig);
    } else
      ig = (ImplementationGuide) fileMap.get(null).getResource();

    for (ImplementationGuidePackageComponent pack : ig.getPackage()) {
      for (ImplementationGuidePackageResourceComponent res : pack.getResource()) {
        FetchedFile f = fetcher.fetch(res.getSource(), igf);
        needToBuild = noteFile(res, f) || needToBuild;
        determineType(f);
      }     
    }

    if (needToBuild) {
      log("Processing Conformance Resources");
      load(ResourceType.NamingSystem);
      load(ResourceType.CodeSystem);
      load(ResourceType.ValueSet);
      load(ResourceType.DataElement);
      load(ResourceType.StructureDefinition);
      load(ResourceType.ConceptMap);
      load(ResourceType.StructureMap);
      generateSnapshots();
    }
    return needToBuild;
  }

  private boolean noteFile(ImplementationGuidePackageResourceComponent key, FetchedFile file) {
    FetchedFile existing = fileMap.get(key);
    if (existing == null || existing.getTime() != file.getTime() || existing.getSource() != file.getSource()) {
      fileList.add(file);
      fileMap.put(key, file);
      return true;
    } else {
      fileList.add(existing); // this one is already parsed
      return false;
    }
  }

  private void determineType(FetchedFile file) throws Exception {
    try {
      if (file.getType() == null) {
        if (file.getContentType().contains("json"))
          file.setType(determineTypeFromJson(file.getSource()));
        else if (file.getContentType().contains("xml"))
          file.setType(determineTypeFromXml(file.getSource()));
        else 
          throw new Exception("Unable to determine file type for "+file.getName());
      }

    } catch (Exception e) {
      throw new Exception("Unable to parse "+file.getName()+": " +e.getMessage(), e);
    }
    if (file.getType() == ResourceType.Bundle)
      throw new Exception("Error processing "+file.getName()+": Bundles are not supported");
  }

  private ResourceType determineTypeFromXml(byte[] source) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    // xxe protection
    factory.setFeature("http://apache.org/xml/features/disallow-doctype-decl", true);
    factory.setFeature("http://xml.org/sax/features/external-general-entities", false);
    factory.setFeature("http://xml.org/sax/features/external-parameter-entities", false);
    factory.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
    factory.setXIncludeAware(false);
    factory.setExpandEntityReferences(false);
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new ByteArrayInputStream(source));
    org.w3c.dom.Element element = doc.getDocumentElement();
    String ns = element.getNamespaceURI();
    String name = element.getLocalName();
    if (!ns.equals(FormatUtilities.FHIR_NS))
      return null;
    return ResourceType.fromCode(name);
  }

  private ResourceType determineTypeFromJson(byte[] source) throws Exception {
    String s = new String(source);
    JsonObject obj = (JsonObject) new com.google.gson.JsonParser().parse(s);
    JsonElement rt = obj.get("resourceType");
    if (rt == null) 
      return null;
    return ResourceType.fromCode(rt.getAsString());
  }

  private void load(ResourceType type) throws Exception {
    for (FetchedFile f : fileList) {
      if (f.getType() == type) {
        if (f.getElement() == null)
          validate(f);
        if (f.getResource() == null)
          f.setResource(parse(f));
        BaseConformance bc = (BaseConformance) f.getResource();
        igpkp.checkForPath(bc);
        context.seeResource(bc.getUrl(), bc);
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
    log("Validating Resources");

    for (FetchedFile f : fileList)
      if (f.getElement() == null)
        validate(f);
    
  }

  private void validate(FetchedFile file) throws Exception {
    ValidationOutcomes e = new ValidationOutcomes(file);
    errs.add(e);
    if (file.getContentType().contains("json"))
      file.setElement(validator.validate(e.getErrors(), new ByteArrayInputStream(file.getSource()), FhirFormat.JSON));
    else if (file.getContentType().contains("xml"))
      file.setElement(validator.validate(e.getErrors(), new ByteArrayInputStream(file.getSource()), FhirFormat.XML));
    else
      throw new Exception("Unable to determine file type for "+file.getName());
    file.setId(file.getElement().getChildValue("id"));
  }

  private void generate() throws Exception {
    log("Generating Outputs in "+output);
    for (FetchedFile f : fileList) 
      generateOutputs(f);

    generateSummaryOutputs();
  }

  private void generateSummaryOutputs() throws IOException {
    JsonObject data = new JsonObject();
    data.addProperty("path", pathToSpec);
    
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    String json = gson.toJson(data);
    TextFile.stringToFile(json, Utilities.path(dataDir, "fhir.json"));    
  }

  private void log(String s) {
    System.out.println(s);
  }

  private void generateOutputs(FetchedFile f) throws Exception {
    System.out.println("Generating outputs for "+f.getName());
    
    saveDirectResourceOutputs(f);
    
    // now, start generating resource type specific stuff 
    if (f.getResource() != null) { // we only do this for conformance resources we've already loaded
      switch (f.getResource().getResourceType()) {
      case CodeSystem:
        generateOutputsCodeSystem((CodeSystem) f.getResource());
        break;
      case ValueSet:
        generateOutputsValueSet((ValueSet) f.getResource());
        break;
      case ConceptMap:
        generateOutputsConceptMap((ConceptMap) f.getResource());
        break;
        
      case DataElement:
        break;
      case StructureDefinition:
        generateOutputsStructureDefinition((StructureDefinition) f.getResource());
        break;
      case StructureMap:
        break;
      default:
        // nothing to do...    
      }      
    }
    
//    NarrativeGenerator gen = new NarrativeGenerator(null, null, context);
//    gen.generate(f.getElement(), false);
//    xhtml = getXhtml(f);
//    html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
//    fragment(f.getId()+"-gen-html", html);
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
  private void saveDirectResourceOutputs(FetchedFile f) throws FileNotFoundException, Exception {
    new org.hl7.fhir.dstu3.metamodel.XmlParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".xml")), OutputStyle.PRETTY, "??");
    new org.hl7.fhir.dstu3.metamodel.JsonParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".json")), OutputStyle.PRETTY, "??");
    new org.hl7.fhir.dstu3.metamodel.TurtleParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(contentDir, f.getElement().fhirType()+"-"+f.getId()+".ttl")), OutputStyle.PRETTY, "??");
    
    XmlXHtmlRenderer x = new XmlXHtmlRenderer();
    org.hl7.fhir.dstu3.metamodel.XmlParser xp = new org.hl7.fhir.dstu3.metamodel.XmlParser(context);
    xp.setLinkResolver(igpkp);
    xp.compose(f.getElement(), x);
    fragment(f.getId()+"-xml-html", x.toString());

    JsonXhtmlRenderer j = new JsonXhtmlRenderer();
    org.hl7.fhir.dstu3.metamodel.JsonParser jp = new org.hl7.fhir.dstu3.metamodel.JsonParser(context);
    jp.setLinkResolver(igpkp);
    jp.compose(f.getElement(), j);
    fragment(f.getId()+"-json-html", j.toString());

    org.hl7.fhir.dstu3.metamodel.TurtleParser ttl = new org.hl7.fhir.dstu3.metamodel.TurtleParser(context);
    ttl.setLinkResolver(igpkp);
    Turtle rdf = new Turtle();
    ttl.compose(f.getElement(), rdf, "??");
    fragment(f.getId()+"-ttl-html", rdf.asHtml());
    
    XhtmlNode xhtml = getXhtml(f);
    String html = xhtml == null ? "" : new XhtmlComposer().compose(xhtml);
    fragment(f.getId()+"-html", html);    
  }

  /**
   * Generate:
   *   summary
   *   content as html
   *   xref
   * @param resource
   * @throws IOException 
   */
  private void generateOutputsCodeSystem(CodeSystem cs) throws IOException {
    // TODO Auto-generated method stub
    fragmentError(cs.getId()+"-cs-summary", "yet to be done: code system summary");
    fragmentError(cs.getId()+"-cs-content", "yet to be done: code system definition");
    fragmentError(cs.getId()+"-cs-xref", "yet to be done: list of all value sets where the code system is used");
  }

  /**
   * Save the expansion as html 
   * Genrate: 
   *   summary
   *   Content logical definition
   *   cross-reference
   *   
   * todo: should we save it as a resource too? at this time, no: it's not safe to do that; encourages abuse
   * @param vs
   * @throws IOException
   * @throws FHIRException 
   */
  private void generateOutputsValueSet(ValueSet vs) throws IOException, FHIRException {
    ValueSetRenderer vsr = new ValueSetRenderer(context, pathToSpec, vs, igpkp);
    fragment(vs.getId()+"-vs-summary", vsr.summary());
    try {
      fragment(vs.getId()+"-vs-cld", vsr.cld());
    } catch (Exception e) {
      fragmentError(vs.getId()+"-vs-cld", e.getMessage());
    }

    fragment(vs.getId()+"-vs-xref", vsr.xref());
    ValueSetExpansionOutcome exp = context.expandVS(vs, true);
    if (exp.getValueset() != null) {
      NarrativeGenerator gen = new NarrativeGenerator(null, null, context);
      gen.generate(exp.getValueset(), false);
      String html = new XhtmlComposer().compose(exp.getValueset().getText().getDiv());
      fragment(vs.getId()+"-expansion", html);
    } else if (exp.getError() != null) 
      fragmentError(vs.getId()+"-expansion", exp.getError());
    else 
      fragmentError(vs.getId()+"-expansion", exp.getError());
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
  private void generateOutputsConceptMap(ConceptMap cm) throws IOException {
    fragmentError(cm.getId()+"-cm-summary", "yet to be done: concept map summary");
    fragmentError(cm.getId()+"-cm-content", "yet to be done: table presentation of the concept map");
    fragmentError(cm.getId()+"-cm-xref", "yet to be done: list of all places where concept map is used");
  }

  private void generateOutputsStructureDefinition(StructureDefinition sd) throws Exception {
    // todo : generate shex itself
    fragmentError(sd.getId()+"-shex", "yet to be done: shex as html");
    
    // todo : generate schematron itself
    fragmentError(sd.getId()+"-sch", "yet to be done: schematron as html");

    // todo : generate json schema itself
    fragmentError(sd.getId()+"-json-schema", "yet to be done: json schema as html");
    
    StructureDefinitionRenderer sdr = new StructureDefinitionRenderer(context, pathToSpec+"/", sd, Utilities.path(contentDir), igpkp, specDetails.getAsJsonObject("maps"));
    fragment(sd.getId()+"-sd-summary", sdr.summary());
    fragment(sd.getId()+"-header", sdr.header());
    fragment(sd.getId()+"-diff", sdr.diff(igpkp.getDefinitions(sd)));
    fragment(sd.getId()+"-snapshot", sdr.snapshot(igpkp.getDefinitions(sd)));
    fragmentError(sd.getId()+"-xml", "yet to be done: Xml template");
    fragmentError(sd.getId()+"-json", "yet to be done: Json template");
    fragmentError(sd.getId()+"-ttl", "yet to be done: Turtle template");
    fragmentError(sd.getId()+"-uml", "yet to be done: UML as SVG");
    fragment(sd.getId()+"-tx", sdr.tx());
    fragment(sd.getId()+"-inv", sdr.inv());
    fragment(sd.getId()+"-dict", sdr.dict());
    fragment(sd.getId()+"-maps", sdr.mappings());
    fragmentError(sd.getId()+"-sd-xref", "Yet to be done: xref");
  }

  private XhtmlNode getXhtml(FetchedFile f) {
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
    TextFile.stringToFile(content, Utilities.path(includesDir, name+".xhtml"), false);
    TextFile.stringToFile(pageWrap(content, name), Utilities.path(validationDir, name+".html"), true);
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
    self.reside = hasParam(args, "-reside");

    if (self.configFile == null || self.pathToSpec == null) {
      System.out.println("");
      System.out.println("To use this publisher, run with the commands");
      System.out.println("");
      System.out.println("-ig [source] -out [folder] -spec [path] -tx [url] -reside ");
      System.out.println("");
      System.out.println("-ig: a path or a url where the implementation guide control file is found");
      System.out.println("  see Wiki for Documentation");
      System.out.println("-out: a local folder where the output from the IG publisher will be generated");
      System.out.println("-spec: the location of the FHIR specification relative to the guide");
      System.out.println("  (can be an absolute URL, or relative if the guide will be published with FHIR)");
      System.out.println("-tx: (optional) Address to use for terminology server ");
      System.out.println("  (default is http://fhir3.healthintersections.com.au)");
      System.out.println("-reside (optional): if this is present, the publisher will not terminate;");
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
