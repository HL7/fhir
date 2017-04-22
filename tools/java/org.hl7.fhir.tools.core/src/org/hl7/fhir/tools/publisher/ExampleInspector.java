package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.apache.jena.graph.Triple;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.ResIterator;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.apache.jena.riot.RDFFormat;
import org.apache.jena.sparql.util.Context;
import org.apache.jena.sparql.util.IsoMatcher;
import org.everit.json.schema.loader.SchemaLoader;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.r4.context.IWorkerContext;
import org.hl7.fhir.r4.elementmodel.Element;
import org.hl7.fhir.r4.elementmodel.Manager;
import org.hl7.fhir.r4.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.r4.elementmodel.ObjectConverter;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Base;
import org.hl7.fhir.r4.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.OperationDefinition;
import org.hl7.fhir.r4.model.SearchParameter;
import org.hl7.fhir.r4.model.StructureDefinition;
import org.hl7.fhir.r4.model.TypeDetails;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.utils.FHIRPathEngine;
import org.hl7.fhir.r4.utils.FHIRPathEngine.IEvaluationContext;
import org.hl7.fhir.r4.utils.IResourceValidator.BestPracticeWarningLevel;
import org.hl7.fhir.r4.utils.IResourceValidator.IValidatorResourceFetcher;
import org.hl7.fhir.r4.utils.IResourceValidator.IdStatus;
import org.hl7.fhir.r4.utils.IResourceValidator.ReferenceValidationPolicy;
import org.hl7.fhir.r4.validation.InstanceValidator;
import org.hl7.fhir.r4.validation.XmlValidator;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.exceptions.PathEngineException;
import org.hl7.fhir.rdf.ModelComparer;
import org.hl7.fhir.rdf.ShExValidator;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.Logger.LogMessageType;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.xml.NamespaceContextMap;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.github.jsonldjava.utils.JsonUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;

public class ExampleInspector implements IValidatorResourceFetcher {

  public static class EValidationFailed extends Exception {
    private static final long serialVersionUID = 1538324138218778487L;
    public EValidationFailed(String arg0) {
      super(arg0);
    }
  }

  private class ExampleHostServices implements IEvaluationContext {

    @Override
    public Base resolveConstant(Object appContext, String name) throws PathEngineException {
      return null;
    }

    @Override
    public TypeDetails resolveConstantType(Object appContext, String name) throws PathEngineException {
      return null;
    }

    @Override
    public boolean log(String argument, List<Base> focus) {
//      System.out.println("FHIRPath log :"+focus.toString());
      return false;
    }

    @Override
    public FunctionDetails resolveFunction(String functionName) {
      return null;
    }

    @Override
    public TypeDetails checkFunction(Object appContext, String functionName, List<TypeDetails> parameters) throws PathEngineException {
      return null;
    }

    @Override
    public List<Base> executeFunction(Object appContext, String functionName, List<List<Base>> parameters) {
      return null;
    }

    @Override
    public Base resolveReference(Object appContext, String url) {
      try {
        String[] s = url.split("/");
        if (s.length != 2 || !definitions.getResources().containsKey(s[0]))
          return null;
        String fn = Utilities.path(rootDir, s[0].toLowerCase()+"-"+s[1]+".xml");
        File f = new File(fn);
        if (!f.exists())
          return null;
        XmlParser xml = new XmlParser();
        return xml.parse(new FileInputStream(f));
      } catch (Exception e) {
        return null;
      }
    }

  }
  private static final boolean VALIDATE_CONFORMANCE_REFERENCES = true;
  private static final boolean VALIDATE_BY_PROFILE = true;
  private static final boolean VALIDATE_BY_SCHEMATRON = false;
  private static final boolean VALIDATE_BY_JSON_SCHEMA = true;
  private static final boolean VALIDATE_RDF = true;
  
  private IWorkerContext context;
  private String rootDir;
  private String xsltDir;
  private List<ValidationMessage> errorsInt;
  private List<ValidationMessage> errorsExt;
  private Logger logger;
  private Definitions definitions;
  private boolean byProfile = VALIDATE_BY_PROFILE;
  private boolean bySchematron = VALIDATE_BY_SCHEMATRON;
  private boolean byJsonSchema = VALIDATE_BY_JSON_SCHEMA;
  private boolean byRdf = VALIDATE_RDF;
  private ExampleHostServices hostServices; 
  
  public ExampleInspector(IWorkerContext context, Logger logger, String rootDir, String xsltDir, List<ValidationMessage> errors, Definitions definitions) throws JsonSyntaxException, FileNotFoundException, IOException {
    super();
    this.context = context;
    this.logger = logger;
    this.rootDir = rootDir;
    this.xsltDir = xsltDir;
    this.errorsExt = errors;
    this.errorsInt = new ArrayList<ValidationMessage>();
    this.definitions = definitions;
    hostServices = new ExampleHostServices();
    jsonLdDefns = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(Utilities.path(rootDir, "fhir.jsonld")));
  }

  private XmlValidator xml;
  private InstanceValidator validator;
  private int errorCount = 0;
  private int warningCount = 0;
  private int informationCount = 0;

  private org.everit.json.schema.Schema jschema;
  private FHIRPathEngine fpe;
  private JsonObject jsonLdDefns;
  private ShExValidator shex;
  
  public void prepare() throws Exception {
    validator = new InstanceValidator(context, hostServices);
    validator.setSuppressLoincSnomedMessages(true);
    validator.setResourceIdRule(IdStatus.REQUIRED);
    validator.setBestPracticeWarningLevel(BestPracticeWarningLevel.Warning);
    validator.getExtensionDomains().add("http://hl7.org/fhir/StructureDefinition/us-core-");
    validator.setFetcher(this);

    xml = new XmlValidator(errorsInt, loadSchemas(), loadTransforms());

    if (VALIDATE_BY_JSON_SCHEMA) {
      String source = TextFile.fileToString(Utilities.path(rootDir, "fhir.schema.json"));
      JSONObject rawSchema = new JSONObject(new JSONTokener(source));
      jschema = SchemaLoader.load(rawSchema);
    }
    if (VALIDATE_RDF) {
      shex = new ShExValidator(Utilities.path(rootDir, "fhir.shex"));
    }
    
    fpe = new FHIRPathEngine(context);
    checkJsonLd();
  }

  
  private void checkJsonLd() throws IOException {
    String s1 = "{\r\n"+
        "  \"@type\": \"fhir:Claim\",\r\n"+
        "  \"@id\": \"http://hl7.org/fhir/Claim/760152\",\r\n"+
        "  \"decimal\": 123.45,\r\n"+
        "  \"@context\": {\r\n"+
        "    \"fhir\": \"http://hl7.org/fhir/\",\r\n"+
        "    \"xsd\": \"http://www.w3.org/2001/XMLSchema#\",\r\n"+
        "    \"decimal\": {\r\n"+
        "      \"@id\": \"fhir:value\",\r\n"+
        "      \"@type\": \"xsd:decimal\"\r\n"+
        "    }\r\n"+
        "  }\r\n"+
      "}\r\n";
    String s2 = "{\r\n"+
        "  \"@type\": \"fhir:Claim\",\r\n"+
        "  \"@id\": \"http://hl7.org/fhir/Claim/760152\",\r\n"+
        "  \"decimal\": \"123.45\",\r\n"+
        "  \"@context\": {\r\n"+
        "    \"fhir\": \"http://hl7.org/fhir/\",\r\n"+
        "    \"xsd\": \"http://www.w3.org/2001/XMLSchema#\",\r\n"+
        "    \"decimal\": {\r\n"+
        "      \"@id\": \"fhir:value\",\r\n"+
        "      \"@type\": \"xsd:decimal\"\r\n"+
        "    }\r\n"+
        "  }\r\n"+
        "}\r\n";
    Model m1 = ModelFactory.createDefaultModel();
    Model m2 = ModelFactory.createDefaultModel();
    m1.read(new StringReader(s1), null, "JSON-LD");
    m2.read(new StringReader(s2), null, "JSON-LD");
    List<String> diffs = new ModelComparer().setModel1(m1, "j1").setModel2(m2, "j2").compare();
    if (!diffs.isEmpty()) {
      System.out.println("not isomorphic");
      for (String s : diffs) {
        System.out.println("  "+s);
      }
    }
  }

  private Map<String, byte[]> loadTransforms() throws FileNotFoundException, IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (String s : new File(xsltDir).list()) {
      if (s.endsWith(".xslt"))
        res.put(s, TextFile.fileToBytes(Utilities.path(xsltDir, s)));
    }
    return res;
  }

  private Map<String, byte[]> loadSchemas() throws FileNotFoundException, IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    res.put("fhir-single.xsd", TextFile.fileToBytes(Utilities.path(rootDir, "fhir-single.xsd")));
    res.put("fhir-xhtml.xsd", TextFile.fileToBytes(Utilities.path(rootDir, "fhir-xhtml.xsd")));
    res.put("xml.xsd", TextFile.fileToBytes(Utilities.path(rootDir, "xml.xsd")));
    for (String s : new File(rootDir).list()) {
      if (s.endsWith(".sch"))
        res.put(s, TextFile.fileToBytes(Utilities.path(rootDir, s)));
    }
    return res;
  }

//  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
//  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
//  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";

  public void validate(String n, String rt, StructureDefinition profile) {
    if (VALIDATE_BY_PROFILE)
      doValidate(n, rt, profile);
  }
  
  public void validate(String n, String rt) {
    doValidate(n, rt, null);    
  }
  
  public void doValidate(String n, String rt, StructureDefinition profile) {
    errorsInt.clear();
    logger.log(" ...validate " + n, LogMessageType.Process);
    try {
      Element e = validateLogical(Utilities.path(rootDir, n+".xml"), profile, FhirFormat.XML);
      org.w3c.dom.Element xe = validateXml(Utilities.path(rootDir, n+".xml"), profile == null ? null : profile.getId());

      validateLogical(Utilities.path(rootDir, n+".json"), profile, FhirFormat.JSON);
      validateJson(Utilities.path(rootDir, n+".xml"), profile == null ? null : profile.getId());
      validateRDF(Utilities.path(rootDir, n+".ttl"), Utilities.path(rootDir, n+".jsonld"), rt);
      
      checkSearchParameters(xe, e);
    } catch (Exception e) {
      e.printStackTrace();
      errorsInt.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, n, e.getMessage(), IssueSeverity.ERROR));
    }
    
    for (ValidationMessage m : errorsInt) {
      if (!m.getLevel().equals(IssueSeverity.INFORMATION) && !m.getLevel().equals(IssueSeverity.WARNING)) {
        m.setMessage(n+":: "+m.getLocation()+": "+m.getMessage());
        errorsExt.add(m);
        logger.log(m.getMessage(), LogMessageType.Error);
      }
      if (m.getLevel() == IssueSeverity.WARNING)
        warningCount++;
      else if (m.getLevel() == IssueSeverity.INFORMATION)
        informationCount++;
      else
        errorCount++;
    }
  }
 
  private Element validateLogical(String f, StructureDefinition profile, FhirFormat fmt) throws Exception {
    Element e = Manager.parse(context, new CSFileInputStream(f), fmt);
    new DefinitionsUsageTracker(definitions).updateUsage(e);
    validator.validate(null, errorsInt, e);
    if (profile != null) {
      validator.validate(null, errorsInt, e, profile);
    }
    return e;
  }


  private org.w3c.dom.Element validateXml(String f, String profile) throws FileNotFoundException, IOException, ParserConfigurationException, SAXException, FHIRException  {
    org.w3c.dom.Element e = xml.checkBySchema(f, false);
    if (VALIDATE_BY_SCHEMATRON) {
      xml.checkBySchematron(f, "fhir-invariants.sch", false);
      if (profile != null && new File(Utilities.path(rootDir, profile+".sch")).exists()) {
        xml.checkBySchematron(f, profile+".sch", false);
      }
    }
    return e;
  }

  private void validateJson(String f, String profile) throws FileNotFoundException, IOException {
    if (VALIDATE_BY_JSON_SCHEMA) {
      jschema.validate(new CSFileInputStream(f));
    }
  }

  private void validateRDF(String fttl, String fjld, String rt) throws FileNotFoundException, IOException {
    if (VALIDATE_RDF && new File(fjld).exists()) {
      FileInputStream f = new FileInputStream(fjld);
      int size = f.available();
      f.close();
      if (size > 1000000)
        return;
      // replace @context with the contents of the right context file
      JsonObject json = (JsonObject) new com.google.gson.JsonParser().parse(TextFile.fileToString(fjld));
      json.remove("@context");
      json.add("@context", jsonLdDefns.get("@context"));
      Gson gson = new GsonBuilder().setPrettyPrinting().create();
      String jcnt = gson.toJson(json);
//      TextFile.stringToFile(jcnt, "c:\\temp\\jsonld\\"+rt+".jsonld");
      // parse to a model
      Model mj = ModelFactory.createDefaultModel();
      mj.read(new StringReader(jcnt), null, "JSON-LD");

      // read turtle file into Jena
      Model mt = RDFDataMgr.loadModel(fttl);
      // use ShEx to validate turtle file - TODO
      shex.validate(mt);

//      List<String> diffs = new ModelComparer().setModel1(mt, "ttl").setModel2(mj, "json").compare();
//      if (!diffs.isEmpty()) {
//        System.out.println("not isomorphic");
//        for (String s : diffs) {
//          System.out.println("  "+s);
//        }
//        RDFDataMgr.write(new FileOutputStream("c:\\temp\\json.nt"), mj, RDFFormat.NTRIPLES_UTF8);
//        RDFDataMgr.write(new FileOutputStream("c:\\temp\\ttl.nt"), mt, RDFFormat.NTRIPLES_UTF8);
//      }
    }
  }

  public void summarise() throws EValidationFailed {
    logger.log("Summary: Errors="+Integer.toString(errorCount)+", Warnings="+Integer.toString(warningCount)+", Information messages="+Integer.toString(informationCount), LogMessageType.Error);
    if (errorCount > 0)
      throw new EValidationFailed("Resource Examples failed instance validation");
  }


  private void checkSearchParameters(org.w3c.dom.Element xe, Element e) throws FHIRException {
    // test the base
    testSearchParameters(xe, xe.getTagName(), false);
    testSearchParameters(e);
    
    if (e.fhirType().equals("Bundle")) {
      for (Element be : e.getChildrenByName("entry")) {
        Element res = be.getNamedChild("resource");
        if (res != null)
          testSearchParameters(res);
      }
      // XPath is turned off. We don't really care about this; ust that the xpaths compile, which is otherwise checked
//      // for ZXath, iterating the entries running xpaths takes too long. What we're going to do
//      // is list all the resources, and then evaluate all the paths...
//      Set<String> names = new HashSet<String>();
//      org.w3c.dom.Element child = XMLUtil.getFirstChild(xe);
//      while (child != null) {
//        if (child.getNodeName().equals("entry")) {
//          org.w3c.dom.Element grandchild = XMLUtil.getFirstChild(child);
//          while (grandchild != null) {
//            if (grandchild.getNodeName().equals("resource"))
//              names.add(XMLUtil.getFirstChild(grandchild).getNodeName());
//            grandchild = XMLUtil.getNextSibling(grandchild);
//          }
//        }
//        child = XMLUtil.getNextSibling(child);
//      }
//      for (String name : names)
//        testSearchParameters(xe, name, true);
    }
  }

  private void testSearchParameters(Element e) throws FHIRException {
    ResourceDefn r = definitions.getResources().get(e.fhirType());
    if (r != null) {
      for (SearchParameterDefn sp : r.getSearchParams().values()) {
        if (!Utilities.noString(sp.getExpression())) {
          if (sp.getExpressionNode() == null)
            sp.setExpressionNode(fpe.parse(sp.getExpression()));
          if (fpe.evaluate(e, sp.getExpressionNode()).size() > 0)
            sp.setWorks(true);
        }
      }
    }
  }
  
  private void testSearchParameters(org.w3c.dom.Element xe, String rn, boolean inBundle) throws FHIRException {
    ResourceDefn r = definitions.getResources().get(rn);
    for (SearchParameterDefn sp : r.getSearchParams().values()) {
      if (!sp.isXPathDone() && !Utilities.noString(sp.getXPath())) {
        try {
          sp.setXPathDone(true);
          NamespaceContext context = new NamespaceContextMap("f", "http://hl7.org/fhir", "h", "http://www.w3.org/1999/xhtml");
          XPathFactory factory = XPathFactory.newInstance();
          XPath xpath = factory.newXPath();
          xpath.setNamespaceContext(context);
          XPathExpression expression;
          expression = inBundle ? xpath.compile("/f:Bundle/f:entry/f:resource/"+sp.getXPath()) : xpath.compile("/"+sp.getXPath());
          NodeList resultNodes = (NodeList) expression.evaluate(xe, XPathConstants.NODESET);
          if (resultNodes.getLength() > 0)
            sp.setWorks(true);
        } catch (Exception e1) {
          throw new FHIRException("Xpath \"" + sp.getXPath() + "\" execution failed: " + e1.getMessage(), e1);
        }
      }
    }
  }

  public boolean isByProfile() {
    return byProfile;
  }


  public void setByProfile(boolean byProfile) {
    this.byProfile = byProfile;
  }


  public boolean isBySchematron() {
    return bySchematron;
  }


  public void setBySchematron(boolean bySchematron) {
    this.bySchematron = bySchematron;
  }


  public boolean isByJsonSchema() {
    return byJsonSchema;
  }


  public void setByJsonSchema(boolean byJsonSchema) {
    this.byJsonSchema = byJsonSchema;
  }


  public boolean isByRdf() {
    return byRdf;
  }


  public void setByRdf(boolean byRdf) {
    this.byRdf = byRdf;
  }


  @Override
  public Element fetch(Object appContext, String url) throws IOException, FHIRException {
    String[] parts = url.split("\\/");
    if (parts.length == 2 && definitions.hasResource(parts[0])) {
      ResourceDefn r = definitions.getResourceByName(parts[0]);
      for (Example e : r.getExamples()) {
        if (e.getElement() == null && e.hasXml()) {
          e.setElement(new org.hl7.fhir.r4.elementmodel.XmlParser(context).parse(e.getXml()));
        }
        if (e.getElement() != null) {
          if (e.getElement().fhirType().equals("Bundle")) {
            for (Base b : e.getElement().listChildrenByName("entry")) {
              if (b.getChildByName("resource").hasValues()) {
                Element res = (Element) b.getChildByName("resource").getValues().get(0);
                if (res.fhirType().equals(parts[0]) && parts[1].equals(res.getChildValue("id"))) {
                  return res;
                }
              }
            }
          } else  if (e.getElement().fhirType().equals(parts[0]) && e.getId().equals(parts[1])) {
            return e.getElement();
          }
        }
      }
      try {
        if (parts[0].equals("StructureDefinition")) //, "CodeSystem", "OperationDefinition", "SearchParameter", "ValueSet"))
          return new ObjectConverter(context).convert(context.fetchResourceWithException(StructureDefinition.class, "http://hl7.org/fhir/"+parts[0]+"/"+parts[1]));
        if (parts[0].equals("OperationDefinition")) //, "CodeSystem", "OperationDefinition", "SearchParameter", "ValueSet"))
          return new ObjectConverter(context).convert(context.fetchResourceWithException(OperationDefinition.class, "http://hl7.org/fhir/"+parts[0]+"/"+parts[1]));
        if (parts[0].equals("SearchParameter")) //, "CodeSystem", "OperationDefinition", "SearchParameter", "ValueSet"))
          return new ObjectConverter(context).convert(context.fetchResourceWithException(SearchParameter.class, "http://hl7.org/fhir/"+parts[0]+"/"+parts[1]));
        if (parts[0].equals("ValueSet")) //, "CodeSystem", "OperationDefinition", "SearchParameter", "ValueSet"))
          return new ObjectConverter(context).convert(context.fetchResourceWithException(ValueSet.class, "http://hl7.org/fhir/"+parts[0]+"/"+parts[1]));
        if (parts[0].equals("CodeSystem")) //, "CodeSystem", "OperationDefinition", "SearchParameter", "ValueSet"))
          return new ObjectConverter(context).convert(context.fetchResourceWithException(CodeSystem.class, "http://hl7.org/fhir/"+parts[0]+"/"+parts[1]));
      } catch (Exception e) {
        return null;
      }
      return null;
    } else
      return null;
  }


  @Override
  public ReferenceValidationPolicy validationPolicy(Object appContext, String path, String url) {
    String[] parts = url.split("\\/");
    if (VALIDATE_CONFORMANCE_REFERENCES) {
      if (Utilities.existsInList(url, "ValueSet/LOINCDepressionAnswersList", "ValueSet/LOINCDifficultyAnswersList", "CodeSystem/npi-taxonomy", "ValueSet/1.2.3.4.5", "StructureDefinition/daf-patient", "ValueSet/zika-affected-area"))
        return ReferenceValidationPolicy.IGNORE;
      if (parts.length == 2 && definitions.hasResource(parts[0])) {
        if (Utilities.existsInList(parts[0], "StructureDefinition", "StructureMap", "DataElement", "CapabilityStatement", "MessageDefinition", "OperationDefinition", "SearchParameter", "CompartmentDefinition", "ImplementationGuide", "CodeSystem", "ValueSet", "ConceptMap", "ExpansionProfile", "NamingSystem"))
          return ReferenceValidationPolicy.CHECK_EXISTS_AND_TYPE;
      }    
    }
    return ReferenceValidationPolicy.CHECK_TYPE_IF_EXISTS;
  }


  @Override
  public boolean resolveURL(Object appContext, String path, String url) throws IOException, FHIRException {
    if (path.endsWith(".fullUrl"))
      return true;
    if (url.startsWith("http://hl7.org/fhir")) {
      if (url.contains("#"))
        url = url.substring(0, url.indexOf("#"));
      String[] parts = url.split("\\/");
      if (parts.length >= 5 &&  definitions.hasResource(parts[4])) {
        if ("DataElement".equals(parts[4]))
          return true;
        Element res = fetch(appContext, url.substring(20));
        return true; // disable this test. Try again for R4. res != null || Utilities.existsInList(parts[4], "NamingSystem", "CapabilityStatement", "CompartmentDefinition", "ConceptMap");
      } else if (context.fetchCodeSystem(url) != null)
        return true;
      else if (definitions.getMapTypes().containsKey(url))
        return true;
      else if (url.startsWith("http://hl7.org/fhir/sid") || Utilities.existsInList(url, "http://hl7.org/fhir/ConsentPolicy/opt-in", "http://hl7.org/fhir/ConsentPolicy/opt-out", "http://hl7.org/fhir/api", 
          "http://hl7.org/fhir/terminology-server", "http://hl7.org/fhir/knowledge-repository", "http://hl7.org/fhir/measure-processor"))
        return true;
      else
        return true; // disable this test. Try again for R4
    } else
      return true;
  }
  
 }
