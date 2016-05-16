package org.hl7.fhir.igtools.publisher;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.attribute.FileTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.metamodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.Type;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.utils.IWorkerContext;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.igtools.publisher.ValidationPresenter.ValiationOutcomes;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;

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
  private String txServer = "http://fhir3.healthintersections.com.au/open";
  private boolean reside;

  private String igName;
  
  private IFetchFile fetcher = new SimpleFetcher();
  private SimpleWorkerContext context;
  private InstanceValidator validator;

  private Map<ImplementationGuidePackageResourceComponent, FetchedFile> fileMap = new HashMap<ImplementationGuidePackageResourceComponent, FetchedFile>();
  private List<FetchedFile> fileList = new ArrayList<FetchedFile>();
  private List<Resource> loaded = new ArrayList<Resource>();
  private ImplementationGuide ig;

  private void execute() throws Exception {
    initialize();

    load();
    validate();
    generate();
    while (reside) { // terminated externally
      System.out.println("Watching for changes on a 5sec cycle");
      wait(5000);
      if (load()) {
        validate();
        generate();
      }
    }
    log("Done");
  }

  private void initialize() throws Exception {
    log("Load Configuration");
    JsonObject obj = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(configFile));
    igName = Utilities.path(Utilities.getDirectoryForFile(configFile), obj.get("source").getAsString());

    log("Check destination");
    File f = new File(output);
    if (!f.exists())
      Utilities.createDirectory(output);
    else if (!f.isDirectory())
      throw new Exception(String.format("Error: Output must be a folder (%s)", output));
    Utilities.createDirectory(Utilities.path(output, "publish"));
    Utilities.createDirectory(Utilities.path(output, "fragments"));
    Utilities.createDirectory(Utilities.path(output, "pages"));

    log("Load Validation Pack");
    //    context = SimpleWorkerContext.fromClassPath();
    context = SimpleWorkerContext.fromPack("C:\\work\\org.hl7.fhir\\build\\publish\\validation.xml.zip");
    log("Connect to Terminology Server");
    context.connectToTSServer(txServer);
    validator = new InstanceValidator(context);
    validator.setAllowXsiLocation(true);
    
    TextFile.bytesToFile(context.getBinaries().get("fhir.css"), Utilities.path(output, "publish", "fhir.css"));    
  }

  private boolean load() throws Exception {
    for (Resource r : loaded)
      context.dropResource(r);

    fileList.clear();
    boolean needToBuild = false;
    log("Load Implementation Guide from "+igName);
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
        if (f.getResource() == null)
          f.setResource(parse(f));
        context.seeResource(((BaseConformance) f.getResource()).getUrl(), f.getResource());
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

    List<ValiationOutcomes> errs = new ArrayList<ValiationOutcomes>();
    for (FetchedFile f : fileList) 
      validate(errs, f);
    new ValidationPresenter(context).generate(ig.getName(), errs, Utilities.path(output, "validation.html"));
  }

  private void validate(List<ValiationOutcomes> errs, FetchedFile file) throws Exception {
    ValiationOutcomes e = new ValiationOutcomes(file);
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
    log("Generating Outputs");
    for (FetchedFile f : fileList) 
      generateOutputs(f);

    //    generateSummaryOutputs();
  }

  private void log(String s) {
    System.out.println(s);
  }

  private void generateOutputs(FetchedFile f) throws Exception {
    new org.hl7.fhir.dstu3.metamodel.XmlParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(output, "publish", f.getElement().fhirType()+"-"+f.getId()+".xml")), OutputStyle.PRETTY, "??");
    new org.hl7.fhir.dstu3.metamodel.JsonParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(output, "publish", f.getElement().fhirType()+"-"+f.getId()+".json")), OutputStyle.PRETTY, "??");
    new org.hl7.fhir.dstu3.metamodel.TurtleParser(context).compose(f.getElement(), new FileOutputStream(Utilities.path(output, "publish", f.getElement().fhirType()+"-"+f.getId()+".ttl")), OutputStyle.PRETTY, "??");
  }

  public static void main(String[] args) throws Exception {
    System.out.println("FHIR Implementation Guide Publisher");
    Publisher self = new Publisher();
    self.configFile = getNamedParam(args, "-ig");
    self.output = getNamedParam(args, "-out");
    self.pathToSpec = getNamedParam(args, "-spec");
    self.txServer = getNamedParam(args, "-tx");
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
