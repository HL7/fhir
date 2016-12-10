package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.NamespaceContext;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;

import org.everit.json.schema.loader.SchemaLoader;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.elementmodel.Element;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.FHIRPathEngine;
import org.hl7.fhir.dstu3.utils.IResourceValidator.BestPracticeWarningLevel;
import org.hl7.fhir.dstu3.utils.IResourceValidator.IdStatus;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.dstu3.validation.XmlValidator;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.Logger;
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

public class ExampleInspector {

  public static class EValidationFailed extends Exception {
    private static final long serialVersionUID = 1538324138218778487L;
    public EValidationFailed(String arg0) {
      super(arg0);
    }
  }

  private static final boolean VALIDATE_BY_PROFILE = true;
  private static final boolean VALIDATE_BY_SCHEMATRON = false;
  private static final boolean VALIDATE_BY_JSON_SCHEMA = false;
  
  private IWorkerContext context;
  private String rootDir;
  private String xsltDir;
  private List<ValidationMessage> errorsInt;
  private List<ValidationMessage> errorsExt;
  private Logger logger;
  private Map<String, ResourceDefn> definitions;

  
  public ExampleInspector(IWorkerContext context, Logger logger, String rootDir, String xsltDir, List<ValidationMessage> errors, Map<String, ResourceDefn> definitions) {
    super();
    this.context = context;
    this.logger = logger;
    this.rootDir = rootDir;
    this.xsltDir = xsltDir;
    this.errorsExt = errors;
    this.errorsInt = new ArrayList<ValidationMessage>();
    this.definitions = definitions;
  }

  private XmlValidator xml;
  private InstanceValidator validator;
  private int errorCount = 0;
  private int warningCount = 0;
  private int informationCount = 0;

  private org.everit.json.schema.Schema jschema;
  private FHIRPathEngine fpe;
  
  public void prepare() throws FileNotFoundException, IOException, SAXException {
    validator = new InstanceValidator(context);
    validator.setSuppressLoincSnomedMessages(true);
    validator.setResourceIdRule(IdStatus.REQUIRED);
    validator.setBestPracticeWarningLevel(BestPracticeWarningLevel.Warning);
    validator.getExtensionDomains().add("http://hl7.org/fhir/StructureDefinition/us-core-");

    xml = new XmlValidator(errorsInt, loadSchemas(), loadTransforms());

    if (VALIDATE_BY_JSON_SCHEMA) {
      String source = TextFile.fileToString(Utilities.path(rootDir, "fhir.schema.json"));
      JSONObject rawSchema = new JSONObject(new JSONTokener(source));
      jschema = SchemaLoader.load(rawSchema);
    }
    
    fpe = new FHIRPathEngine(context);
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

  public void validate(String n, StructureDefinition profile) {
    if (VALIDATE_BY_PROFILE)
      doValidate(n, profile);
  }
  
  public void validate(String n) {
    doValidate(n, null);    
  }
  
  public void doValidate(String n, StructureDefinition profile) {
    errorsInt.clear();
    logger.log(" ...validate " + n, LogMessageType.Process);
    try {
      Element e = validateLogical(Utilities.path(rootDir, n+".xml"), profile, FhirFormat.XML);
      org.w3c.dom.Element xe = validateXml(Utilities.path(rootDir, n+".xml"), profile == null ? null : profile.getId());

      validateLogical(Utilities.path(rootDir, n+".json"), profile, FhirFormat.JSON);
      validateJson(Utilities.path(rootDir, n+".xml"), profile == null ? null : profile.getId());
      //    validateTurtle(Utilities.path(rootDir, n+".xml"), null);
      
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

 
  public void summarise() throws EValidationFailed {
    logger.log("Summary: Errors="+Integer.toString(errorCount)+", Warnings="+Integer.toString(warningCount)+", Hints="+Integer.toString(informationCount), LogMessageType.Error);
    if (errorCount > 0)
      throw new EValidationFailed("Resource Examples failed instance validation");
  }


//  private void validateTurtleFile(String n, InstanceValidator validator, StructureDefinition profile) throws Exception {
//    // instance validator
//    File f = new File(Utilities.path(page.getFolders().dstDir, n + ".ttl"));
//    if (!f.exists())
//      return;
//
////  first, ShEx validation
//    ShExValidator shexval = new ShExValidator();
//    shexval.validate(Utilities.path(page.getFolders().dstDir, n + ".ttl"), Utilities.path(page.getFolders().dstDir, "fhir.shex"));
//    
//    List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
//    validator.validate(issues, new FileInputStream(f), FhirFormat.TURTLE);
//    
////    
// 
////    
////    com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
////    JsonObject obj = parser.parse(TextFile.fileToString()).getAsJsonObject();
////
////    // the build tool validation focuses on codes and identifiers
////    List<ValidationMessage> issues = new ArrayList<ValidationMessage>();
////    validator.validate(issues, obj);
//////    System.out.println("  -j- "+validator.reportTimes());
////    // if (profile != null)
////    // validator.validateInstanceByProfile(issues, root, profile);
////    for (ValidationMessage m : issues) {
////      if (!m.getLevel().equals(IssueSeverity.INFORMATION) && !m.getLevel().equals(IssueSeverity.WARNING))
////        logError("  " + m.summary(), typeforSeverity(m.getLevel()));
////
////      if (m.getLevel() == IssueSeverity.WARNING)
////        warningCount++;
////      else if (m.getLevel() == IssueSeverity.INFORMATION)
////        informationCount++;
////      else
////        errorCount++;
////    }
//  }
//
//
////  
////  StringBuilder vallog = new StringBuilder();
////
////  private void logError(String string, LogMessageType typeforSeverity) {
////    page.log(string, typeforSeverity);
////    vallog.append(string+"\r\n");
////    try {
////      TextFile.stringToFileNoPrefix(vallog.toString(), "validation.log");
////    } catch (Exception e) {
////    }
////  }
////
////  private LogMessageType typeforSeverity(IssueSeverity level) {
////    switch (level) {
////    case ERROR:
////      return LogMessageType.Error;
////    case FATAL:
////      return LogMessageType.Error;
////    case INFORMATION:
////      return LogMessageType.Hint;
////    case WARNING:
////      return LogMessageType.Warning;
////    default:
////      return LogMessageType.Error;
////    }
////  }

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
    ResourceDefn r = definitions.get(e.fhirType());
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
    ResourceDefn r = definitions.get(rn);
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
  
 }
