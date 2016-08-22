package org.hl7.fhir.dstu3.validation;

/*
Copyright (c) 2011+, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMResult;
import javax.xml.transform.sax.SAXSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.apache.commons.io.IOUtils;
import org.hl7.fhir.dstu3.elementmodel.Manager;
import org.hl7.fhir.dstu3.elementmodel.Manager.FhirFormat;
import org.hl7.fhir.dstu3.exceptions.DefinitionException;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.IParser;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.BaseConformance;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.ImplementationGuide;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.dstu3.model.OperationOutcome.IssueType;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.ResourceType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpansionCache;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.SimpleWorkerContext;
import org.hl7.fhir.dstu3.utils.XmlLocationAnnotator;
import org.hl7.fhir.dstu3.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.SchemaInputSource;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.ls.LSInput;
import org.w3c.dom.ls.LSResourceResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import com.google.gson.JsonObject;

/**
 * This is just a wrapper around the InstanceValidator class for convenient use 
 * 
 * The following resource formats are supported: XML, JSON, Turtle
 * The following verions are supported: 1.4.0, 1.6.0, and current
 * 
 * Note: the validation engine is not threadsafe
 * To Use:
 *  
 * 1/ Initialise
 *    ValidationEngine validator = new ValidationEngine();
 *    validator.loadDefinitions(src);
 *      - this must refer to the igpack.zip for the version of the spec against which you wnat to validate
 *       it can be a url or a file reference. It can nominate the igpack.zip directly, 
 *       or it can name the container alone (e.g. just the spec URL).
 *       The validation engine does not cache igpack.zip. the user must manage that if desired 
 *
 *    validator.connectToTSServer(txServer);
 *      - this is optional; in the absence of a terminology service, snomed, loinc etc will not be validated
 *      
 *    validator.loadIg(src);
 *      - call this any number of times for the Implementation Guide of interest. This is also a reference
 *        to the igpack.zip for the implementation guide - same rules as above
 *        the version of the IGPack must match that of the spec (todo: enforce this?)
 *         
 *    validator.loadQuestionnaire(src)
 *      - url or filename of a questionnaire to load. Any loaded questionnaires will be used while validating
 *      
 *    validator.setNative(doNative);
 *      - whether to do xml/json/rdf schema validation as well
 *
 *   You only need to do this initialization once. You can validate as many times as you like
 *   
 * 2. validate
 *    validator.validate(src, profiles);
 *      - source (as stream, byte[]), or url or filename of a resource to validate. 
 *        Also validate against any profiles (as canonical URLS, equivalent to listing them in Resource.meta.profile)
 *        
 *        if the source is provided as byte[] or stream, you need to provide a format too, though you can 
 *        leave that as null, and the validator will guess
 *         
 * @author Grahame Grieve
 *
 */
public class ValidationEngine {

  private SimpleWorkerContext context;
  private InstanceValidator validator;
  private List<ValidationMessage> messages = new ArrayList<ValidationMessage>();
  private boolean doNative;
  private Map<String, byte[]> binaries = new HashMap<String, byte[]>();

  public void loadDefinitions(String src) throws Exception {
    Map<String, byte[]> source = loadSource(src, "igpack.zip");   
    context = SimpleWorkerContext.fromDefinitions(source);
    validator  = new InstanceValidator(context);    
    grabNatives(source, "http://hl7.org/fhir");
  }

  Map<String, byte[]> loadSource(String src, String defname) throws Exception {
    System.out.println("  .. load "+src);
    if (Utilities.noString(src)) {
      throw new FHIRException("Definitions Source '' could not be processed");
    } else if (src.startsWith("https:") || src.startsWith("http:")) {
      return loadFromUrl(src);
    } else if (new File(src).exists()) {
      return loadFromFile(src, defname);      
    } else {
      throw new FHIRException("Definitions Source '"+src+"' could not be processed");
    }
  }

  private Map<String, byte[]> loadFromUrl(String src) throws Exception {
    if (!src.endsWith("validator.pack"))
      src = Utilities.pathReverse(src, "validator.pack");

    try {
      URL url = new URL(src);
      URLConnection c = url.openConnection();
      byte[] cnt = IOUtils.toByteArray(c.getInputStream());
      return readZip(new ByteArrayInputStream(cnt));
    } catch (Exception e) {
      throw new Exception("Unable to find definitions at URL '"+src+"': "+e.getMessage(), e);
    }
  }

  private Map<String, byte[]> loadFromFile(String src, String defname) throws FileNotFoundException, IOException {
    File f = new File(src);
    if (f.isDirectory()) {
      if (defname == null)
        throw new IOException("You must provide a fie name, not a directory name");
      if (new File(Utilities.path(src, defname)).exists())
        return readZip(new FileInputStream(Utilities.path(src, defname)));
      else {
        Map<String, byte[]> res = new HashMap<String, byte[]>();
        for (File ff : f.listFiles()) {
          FhirFormat fmt = checkIsResource(ff.getAbsolutePath());
          if (ff != null) {
            res.put(Utilities.changeFileExt(ff.getName(), fmt.getExtension()), TextFile.fileToBytes(ff.getAbsolutePath()));
          }
        }
        return res;
      }
    } else {
      if (src.endsWith(".zip") || (defname != null && src.endsWith(defname)))
        return readZip(new FileInputStream(src));
      else {
        Map<String, byte[]> res = new HashMap<String, byte[]>();
        res.put(f.getName(), TextFile.fileToBytes(src));
        return res;
      }
    }
  }

  private FhirFormat checkIsResource(String path) {
    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.XML);
      return FhirFormat.XML;
    } catch (Exception e) {
    }
    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.JSON);
      return FhirFormat.JSON;
    } catch (Exception e) {
    }
    try {
      Manager.parse(context, new FileInputStream(path), FhirFormat.TURTLE);
      return FhirFormat.TURTLE;
    } catch (Exception e) {
    }
    return null;
  }

  private Map<String, byte[]> readZip(InputStream stream) throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    ZipInputStream zip = new ZipInputStream(stream);
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      String name = ze.getName();
      InputStream in = zip;
      ByteArrayOutputStream b = new ByteArrayOutputStream();
      int n;
      byte[] buf = new byte[1024];
      while ((n = in.read(buf, 0, 1024)) > -1) {
        b.write(buf, 0, n);
      }          
      res.put(name, b.toByteArray());
      zip.closeEntry();
    }
    zip.close();   
    return res;
  }

  public void connectToTSServer(String url) throws URISyntaxException {
    if (url == null) {
      context.setCanRunWithoutTerminology(true);
    } else
      context.connectToTSServer(url);
  }

  public void loadIg(String src) throws IOException, FHIRException, Exception {
    String canonical = null;
    Map<String, byte[]> source = loadSource(src, "pack.zip");
    for (Entry<String, byte[]> t : source.entrySet()) {
      String fn = t.getKey();
      Resource res = null;
      if (fn.endsWith(".xml"))
        res = new XmlParser().parse(t.getValue());
      else if (fn.endsWith(".json"))
        res = new JsonParser().parse(t.getValue());
//      else if (fn.endsWith(".ttl"))
//        res = new RdfParser().parse(t.getValue());

      if (res != null && res instanceof BaseConformance) {
        context.seeResource(((BaseConformance) res).getUrl(), res);
      } else if (res != null && res instanceof Questionnaire) {
        context.seeResource(((Questionnaire) res).getUrl(), res);
      } 
      if (res instanceof ImplementationGuide)
        canonical = ((ImplementationGuide) res).getUrl();
    }
    if (canonical != null)
      grabNatives(source, canonical);
  }

  private void grabNatives(Map<String, byte[]> source, String prefix) {
    for (Entry<String, byte[]> e : source.entrySet()) {
      if (e.getKey().endsWith(".zip"))
        binaries.put(prefix+"#"+e.getKey(), e.getValue());
    }
  }

  public void setQuestionnaires(List<String> questionnaires) {
//    validator.set
  }

  public void setNative(boolean doNative) {
    this.doNative = doNative;
  }


  public OperationOutcome validate(String source, List<String> profiles) throws Exception {
    Map<String, byte[]> s = loadSource(source, null);
    if (s.size() != 1)
      throw new Exception("Unable to find a single resource to validate");
    byte[] focus = null;
    FhirFormat cntType = null;
    for (Entry<String, byte[]> t: s.entrySet()) {
      focus = t.getValue();
      if (t.getKey().endsWith(".json"))
        cntType = FhirFormat.JSON; 
      else if (t.getKey().endsWith(".xml"))
        cntType = FhirFormat.XML; 
      else if (t.getKey().endsWith(".ttl"))
        cntType = FhirFormat.TURTLE; 
      else
        throw new Exception("Todo: Determining resource type is not yet done");
    }
    return validate(focus, cntType, profiles);
  }
  
  public OperationOutcome validate(byte[] source, FhirFormat cntType, List<String> profiles) throws Exception {
    if (doNative) {
      if (cntType == FhirFormat.JSON)
        validateJsonSchema();
      if (cntType == FhirFormat.XML)
        validateXmlSchema();
      if (cntType == FhirFormat.TURTLE)
        validateSHEX();
    }
    messages.clear();
    validator.validate(messages, new ByteArrayInputStream(source), cntType, new ValidationProfileSet(profiles));
    return getOutcome();
  }

  private void validateSHEX() {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "SHEX Validation is not done yet", IssueSeverity.INFORMATION));
  }

  private void validateXmlSchema() throws FileNotFoundException, IOException, SAXException {
    XmlValidator xml = new XmlValidator(messages, loadSchemas(), loadTransforms());
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "XML Schema Validation is not done yet", IssueSeverity.INFORMATION));
  }

  private Map<String, byte[]> loadSchemas() throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (Entry<String, byte[]> e : readZip(new ByteArrayInputStream(binaries.get("http://hl7.org/fhir#fhir-all-xsd.zip"))).entrySet()) {
      if (e.getKey().equals("fhir-single.xsd"))
        res.put(e.getKey(), e.getValue());
      if (e.getKey().equals("fhir-invariants.sch"))
        res.put(e.getKey(), e.getValue());
    }
    return res;
  }
  
  private Map<String, byte[]> loadTransforms() throws IOException {
    Map<String, byte[]> res = new HashMap<String, byte[]>();
    for (Entry<String, byte[]> e : readZip(new ByteArrayInputStream(binaries.get("http://hl7.org/fhir#fhir-all-xsd.zip"))).entrySet()) {
      if (e.getKey().endsWith(".xsl"))
        res.put(e.getKey(), e.getValue());
    }
    return res;
  }

  private void validateJsonSchema() {
    messages.add(new ValidationMessage(Source.InstanceValidator, IssueType.INFORMATIONAL, "JSON Schema Validation is not done yet", IssueSeverity.INFORMATION));   
  }

  public List<ValidationMessage> getMessages() {
    return messages;
  }

  private OperationOutcome getOutcome() throws DefinitionException {
    OperationOutcome op = new OperationOutcome();
    for (ValidationMessage vm : messages) {
      op.getIssue().add(vm.asIssue(op));
    }
    new NarrativeGenerator("", "", context).generate(op);
    return op;
  }

  public InstanceValidator getValidator() {
    return validator;
  }


  
//  static final String JAXP_SCHEMA_LANGUAGE = "http://java.sun.com/xml/jaxp/properties/schemaLanguage";
//  static final String W3C_XML_SCHEMA = "http://www.w3.org/2001/XMLSchema";
//  static final String JAXP_SCHEMA_SOURCE = "http://java.sun.com/xml/jaxp/properties/schemaSource";
//
//  private byte[] source;
//  private Map<String, byte[]> definitions = new HashMap<String, byte[]>();
//  private List<ValidationMessage> outputs;  
//  private OperationOutcome outcome;
//	private StructureDefinition profile;
//	private Questionnaire questionnaire;
//	private String profileURI;
//	private SimpleWorkerContext context;
//	private Schema schema;
//	private byte[] schCache = null;
//	private ValueSetExpansionCache cache;
//	private List<String> extensionDomains = new ArrayList<String>();
//	private boolean anyExtensionsAllowed;
//
//
//  public String getProfileURI() {
//		return profileURI;
//	}
//
//	public void setProfileURI(String profileURI) {
//		this.profileURI = profileURI;
//	}
//
//  public void process() throws Exception {
//		if (isXml())
//			processXml();
//		else if (isJson())
//			processJson();
//		else if (isTurtle()) 
//      processTurtle();
//		else
//		  throw new Exception("Unable to detemine format");
//	}
//	
//  private boolean isXml() throws FHIRFormatError {
//  	
//	  int x = position(source, '<'); 
//	  int j = position(source, '{');
//    int t = position(source, '@');
//	  if (x == Integer.MAX_VALUE && j == Integer.MAX_VALUE || (t < j && t < x))
//	  	return false;
//	  return (x < j);
//  }
//
//  private boolean isJson() throws FHIRFormatError {  
//    int x = position(source, '<'); 
//    int j = position(source, '{');
//    int t = position(source, '@');
//    if (x == Integer.MAX_VALUE && j == Integer.MAX_VALUE || (t < j && t < x))
//      return false;
//    return (j < x);
//  }
//
//  private boolean isTurtle() throws FHIRFormatError {  
//    int x = position(source, '<'); 
//    int j = position(source, '{');
//    int t = position(source, '@');
//    if (x == Integer.MAX_VALUE && j == Integer.MAX_VALUE || (t > j) || (t > x))
//      return false;
//    return true;
//  }
//
//	private int position(byte[] bytes, char target) {
//		byte t = (byte) target;
//		for (int i = 0; i < bytes.length; i++)
//			if (bytes[i] == t)
//				return i;
//		return Integer.MAX_VALUE;
//	  
//  }
//
//	public void processXml() throws Exception {
//
//    // ok all loaded
//    System.out.println("  .. validate (xml)");
//
//    // 1. schema validation 
//    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//    factory.setNamespaceAware(true);
//    Document doc;
//    
//    factory.setValidating(false);
//    factory.setSchema(schema);
//    DocumentBuilder builder = factory.newDocumentBuilder();
//    builder.setErrorHandler(new ValidationErrorHandler(outputs, "XML Source"));
//      doc = builder.parse(new ByteArrayInputStream(source));
//
//		// 3. internal validation. reparse without schema to "help", and use a special parser that keeps location data for us
//    factory = DocumentBuilderFactory.newInstance();
//    factory.setNamespaceAware(true);
//    factory.setValidating(false);
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer nullTransformer = transformerFactory.newTransformer();
//		DocumentBuilder docBuilder = factory.newDocumentBuilder();
//		doc = docBuilder.newDocument();
//		DOMResult domResult = new DOMResult(doc);
//		SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
//		saxParserFactory.setNamespaceAware(true);
//		saxParserFactory.setValidating(false);
//		SAXParser saxParser = saxParserFactory.newSAXParser();
//		XMLReader xmlReader = saxParser.getXMLReader();
//    XmlLocationAnnotator locationAnnotator = new XmlLocationAnnotator(xmlReader, doc);
//    InputSource inputSource = new InputSource(new ByteArrayInputStream(source));
//    SAXSource saxSource = new SAXSource(locationAnnotator, inputSource);
//    nullTransformer.transform(saxSource, domResult);
//
//		if (cache != null)
//		  context.setCache(cache);
//    context.setQuestionnaire(questionnaire);
//
//		InstanceValidator validator = new InstanceValidator(context);
//		validator.setAnyExtensionsAllowed(anyExtensionsAllowed);
//		validator.getExtensionDomains().addAll(extensionDomains);
//
////		if (logical != null)
////      validator.validateLogical(outputs, doc, logical));
////		else
//		 if (profile != null)
//      validator.validate(outputs, doc, profile);
//    else if (profileURI != null)
//      validator.validate(outputs, doc, profileURI);
//    else
//      validator.validate(outputs, doc);
//    
//		try {
//		  context.newXmlParser().parse(new ByteArrayInputStream(source));
//		} catch (Exception e) {
//			outputs.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, "??", e.getMessage(), IssueSeverity.ERROR));
//		}
//        
//    OperationOutcome op = new OperationOutcome();
//    for (ValidationMessage vm : outputs) {
//      op.getIssue().add(vm.asIssue(op));
//    }
//    new NarrativeGenerator("", "", context).generate(op);
//    outcome = op;
//  }
//
//  public void processTurtle() throws Exception {
//    throw new Exception("Not done yet");
//  }
//  
//  public void processJson() throws Exception {
//		outputs = new ArrayList<ValidationMessage>();
//
//		// ok all loaded
//    System.out.println("  .. validate (json)");
//
//    com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
//    JsonObject obj = parser.parse(new String(source)).getAsJsonObject();
//
//		if (cache == null)
//		  cache = new ValueSetExpansionCache(context, null);
//		InstanceValidator validator = new InstanceValidator(context);
//		validator.setAnyExtensionsAllowed(anyExtensionsAllowed);
//		validator.getExtensionDomains().addAll(extensionDomains);
//
//		if (profile != null)
//			validator.validate(outputs, obj, profile);
//		else if (profileURI != null)
//			validator.validate(outputs, obj, profileURI);
//		else
//			validator.validate(outputs, obj);
//
//		try {
//		  new JsonParser().parse(new ByteArrayInputStream(source));
//		} catch (Exception e) {
//			outputs.add(new ValidationMessage(Source.InstanceValidator, IssueType.STRUCTURE, -1, -1, "??", e.getMessage(), IssueSeverity.ERROR));
//		}
//
//		OperationOutcome op = new OperationOutcome();
//		for (ValidationMessage vm : outputs) {
//			op.getIssue().add(vm.asIssue(op));
//		}
//		new NarrativeGenerator("", "", context).generate(op);
//		outcome = op;
//	}
//
//  public class ValidatorResourceResolver implements LSResourceResolver {
//
//    private Map<String, byte[]> files;
//
//    public ValidatorResourceResolver(Map<String, byte[]> files) {
//      this.files = files;
//    }
//
//    @Override
//    public LSInput resolveResource(final String type, final String namespaceURI, final String publicId, String systemId, final String baseURI) {
//      //      if (!(namespaceURI.equals("http://hl7.org/fhir"))) //|| namespaceURI.equals("http://www.w3.org/1999/xhtml")))
//      if (new File("C:\\work\\org.hl7.fhir\\build\\publish\\"+systemId).exists())
//        try {
//          return new SchemaInputSource(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\"+systemId), publicId, systemId, namespaceURI);
//        } catch (FileNotFoundException e) {
//          e.printStackTrace();
//        }
//      if (!files.containsKey(systemId))
//        return null;
//      return new SchemaInputSource(new ByteArrayInputStream(files.get(systemId)), publicId, systemId, namespaceURI);
//    }
//  }
//
//  private Schema readSchema() throws SAXException, FileNotFoundException {
//    StreamSource[] sources = new StreamSource[1];
////    sources[0] = new StreamSource(new ByteArrayInputStream(definitions.get("fhir-all.xsd")));
//    sources[0] = new StreamSource(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\publish\\fhir-all.xsd"));
//
//    SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
//    schemaFactory.setErrorHandler(new ValidationErrorHandler(outputs, "xml source"));
//    schemaFactory.setResourceResolver(new ValidatorResourceResolver(definitions));
//    Schema schema = schemaFactory.newSchema(sources);
//    return schema;
//  }
//
//  private void processSchematronOutput(byte[] out)
//      throws ParserConfigurationException, SAXException, IOException {
//    DocumentBuilderFactory factory;
//    DocumentBuilder builder;
//    Document doc;
//    factory = DocumentBuilderFactory.newInstance();
//    factory.setNamespaceAware(true);
//    builder = factory.newDocumentBuilder();
//    doc = builder.parse(new ByteArrayInputStream(out));
//    NodeList nl = doc.getDocumentElement().getElementsByTagNameNS("http://purl.oclc.org/dsdl/svrl", "failed-assert");
//    if (nl.getLength() > 0) {
//      for (int i = 0; i < nl.getLength(); i++) {
//        Element e = (Element) nl.item(i);
//        outputs.add(new ValidationMessage(Source.Schematron, IssueType.INVARIANT, e.getAttribute("location"), e.getTextContent(), IssueSeverity.ERROR));
//      }
//    }
//  }
//
//  public List<ValidationMessage> getOutputs() {
//    return outputs;
//  }
//
//  public void setOutputs(List<ValidationMessage> outputs) {
//    this.outputs = outputs;
//  }
//
//  public byte[] getSource() {
//    return source;
//  }
//
//  public Map<String, byte[]> getDefinitions() {
//    return definitions;
//  }
//
//  public OperationOutcome getOutcome() {
//    return outcome;
//  }
//
//  public void setSource(byte[] source) {
//    this.source = source;
//  }
//
//  public StructureDefinition getProfile() {
//    return profile;
//  }
//
//  public void setProfile(StructureDefinition profile) {
//    this.profile = profile;
//  }
//
//  public Questionnaire getQuestionnaire() {
//    return questionnaire;
//  }
//
//  public void setQuestionnaire(Questionnaire questionnaire) {
//    this.questionnaire = questionnaire;
//  }
//
//  public void init() throws SAXException, IOException, FHIRException {
//    outputs = new ArrayList<ValidationMessage>();
//		context = SimpleWorkerContext.fromDefinitions(definitions);    
//		schema = readSchema();
//  }
//
//  public SimpleWorkerContext getContext() {
//    return context;
//	}
//
//
//	public void readDefinitions(byte[] defn) throws IOException, SAXException, FHIRException {
//		ZipInputStream zip = new ZipInputStream(new ByteArrayInputStream(defn));
//		ZipEntry ze;
//		while ((ze = zip.getNextEntry()) != null) {
//			if (!ze.getName().endsWith(".zip") && !ze.getName().endsWith(".jar") ) { // skip saxon .zip
//				String name = ze.getName();
//				InputStream in = zip;
//				ByteArrayOutputStream b = new ByteArrayOutputStream();
//				int n;
//				byte[] buf = new byte[1024];
//				while ((n = in.read(buf, 0, 1024)) > -1) {
//					b.write(buf, 0, n);
//				}        
//				getDefinitions().put(name, b.toByteArray());
//			}
//			zip.closeEntry();
//		}
//		zip.close();    
//		init();
//	}
//
//	public void readDefinitions(String definitions) throws IOException, SAXException, FHIRException {
//    System.out.println("  .. load definitions from "+definitions);
//		byte[] defn;
//		if (Utilities.noString(definitions)) {
//			throw new FHIRException("No definitions specified");
//		} else if (definitions.startsWith("https:") || definitions.startsWith("http:")) {
//			defn = loadFromUrl(definitions);
//		} else if (new File(definitions).exists()) {
//			defn = loadFromFile(definitions);      
//		} else
//			throw new DefinitionException("Unable to find FHIR validation Pack (source = "+definitions+")");
//		readDefinitions(defn);
//	}
//
//
//  public byte[] loadFromUrl(String src) throws IOException {
//		URL url = new URL(src);
//		byte[] str = IOUtils.toByteArray(url.openStream());
//		return str;
//	}
//
//	public byte[] loadFromFile(String src) throws IOException {
//		FileInputStream in = new FileInputStream(src);
//		byte[] b = new byte[in.available()];
//		in.read(b);
//		in.close();
//		return b;
//	}
//
//	public void loadProfile(String profile) throws DefinitionException, Exception {
//		if (!Utilities.noString(profile)) { 
//	    System.out.println("  .. load profile "+profile);
//			if (getContext().hasResource(StructureDefinition.class, profile))
//				setProfile(getContext().fetchResource(StructureDefinition.class, profile));
//			else
//				setProfile(readProfile(loadResourceCnt(profile, "profile")));
//		}
//	}
//
//  public void loadQuestionnaire(String questionnaire) throws DefinitionException, Exception {
//    if (!Utilities.noString(questionnaire)) { 
//      System.out.println("  .. load questionnaire "+questionnaire);
//      if (getContext().hasResource(Questionnaire.class, questionnaire))
//        setQuestionnaire(getContext().fetchResource(Questionnaire.class, questionnaire));
//      else
//        setQuestionnaire(readQuestionnaire(loadResourceCnt(questionnaire, "questionnaire")));
//		}
//	}
//
//	private StructureDefinition readProfile(byte[] content) throws Exception {
//		IParser xml = context.newXmlParser();
//		return (StructureDefinition) xml.parse(new ByteArrayInputStream(content));
//	}
//
//  private Bundle readLogical(byte[] content) throws Exception {
//    IParser xml = context.newXmlParser();
//    return (Bundle) xml.parse(new ByteArrayInputStream(content));
//  }
//
//  private Questionnaire readQuestionnaire(byte[] content) throws Exception {
//    IParser xml = context.newXmlParser();
//    return (Questionnaire) xml.parse(new ByteArrayInputStream(content));
//  }
//
//	private byte[] loadResourceCnt(String profile, String paramName) throws DefinitionException, IOException {
//		if (Utilities.noString(profile)) {
//			return null;
//		} else if (profile.startsWith("https:") || profile.startsWith("http:")) {
//			return loadFromUrl(profile);
//		} else if (new File(profile).exists()) {
//			return loadFromFile(profile);      
//		} else
//			throw new DefinitionException("Unable to find named "+paramName+" (source = "+profile+")");
//	}
//
//	public void reset() {
//		source = null;
//		outputs = null;  
//		outcome = null;
//		profile = null;
//		profileURI = null;
//  }
//
//	public List<String> getExtensionDomains() {
//		return extensionDomains;
//	}
//
//	public void setExtensionDomains(List<String> extensionDomains) {
//		this.extensionDomains = extensionDomains;
//	}
//
//	public boolean isAnyExtensionsAllowed() {
//		return anyExtensionsAllowed;
//	}
//
//	public void setAnyExtensionsAllowed(boolean anyExtensionsAllowed) {
//		this.anyExtensionsAllowed = anyExtensionsAllowed;
//	}
//
//	public void connectToTSServer(String url) throws URISyntaxException {
//    System.out.println("  .. connect to terminology server "+url);
//    context.connectToTSServer(url);
//	}
//
//


//private String txServer;
//
//
//
//public void setTsServer(String txServer) {
//  this.txServer = txServer;
//}
//
//
//
//private void setProfile(String profile) {
//  this.profile = profile;
//}
//
//private void setQuestionnaire(String questionnaire) {
//  this.questionnaire = questionnaire;
//}
//
//
//public List<ValidationMessage> outputs() {
//  return engine.getOutputs();
//}
//
//
///**
// * The source (file name, folder name, url) of the FHIR validation pack. This can be the 
// * fhir url, an alternative url of a local copy of the fhir spec, the name of 
// * a zip file containing the fhir spec, the name of a directory containing the
// * fhir spec 
// */
//private String definitions;
//
///**
// * Additional location to get structures from
// */
//private List<String> folders = new ArrayList<String>();
//
///**
// * A specific profile against which to validate the instance (optional)
// */
//private String profile;
//
//private String questionnaire;
//
///**
// * The name of the resource/feed to validate. this can be the actual source as json or xml, a file name, a zip file, 
// * or a url. If the source identifies a collection of resources and/or feeds, they
// * will all be validated
// */
//private String source;
//
//
//ValidationEngine engine = new ValidationEngine();
//
//public void process() throws Exception {
//  engine.readDefinitions(definitions);
//  for (String folder : folders)
//    engine.getContext().loadFromFolder(folder);
//  engine.connectToTSServer(txServer == null ? "http://fhir3.healthintersections.com.au/open" : txServer);
//  engine.loadProfile(profile);
//  engine.loadQuestionnaire(questionnaire);
//  engine.setSource(loadSource());
//  engine.process();
//}
//
//private byte[] loadSource() throws IOException {
//}
//
//private byte[] loadFromUrl(String src) throws IOException {
//  URL url = new URL(src);
//  byte[] str = IOUtils.toByteArray(url.openStream());
//  return str;
//}
//
//private byte[] loadFromFile(String src) throws IOException {
//  FileInputStream in = new FileInputStream(src);
//  byte[] b = new byte[in.available()];
//  in.read(b);
//  in.close();
//  return b;
//}
//
//
//public String getSource() {
//  return source;
//}
//
//public void setSource(String source) {
//  this.source = source;
//}
//
//
//public String getOutcome() throws IOException {
//  ByteArrayOutputStream b = new ByteArrayOutputStream();
//  new XmlParser().compose(b, engine.getOutcome(), true); 
//  b.close();
//  return b.toString();
//}
//
//public String getDefinitions() {
//  return definitions;
//}
//
//public void setDefinitions(String definitions) {
//  this.definitions = definitions;
//}
//
//
//
//public List<String> getFolders() {
//  return folders;
//}
//
//public void addFolder(String value) {
//  folders.add(value);
//}
//
//
}
