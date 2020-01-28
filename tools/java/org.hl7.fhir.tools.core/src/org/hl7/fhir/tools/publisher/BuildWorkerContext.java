package org.hl7.fhir.tools.publisher;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.fhir.ucum.UcumEssenceService;
import org.fhir.ucum.UcumException;
import org.fhir.ucum.UcumService;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.exceptions.DefinitionException;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.TerminologyServiceException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.conformance.ProfileUtilities;
import org.hl7.fhir.r5.conformance.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.r5.context.BaseWorkerContext;
import org.hl7.fhir.r5.context.CanonicalResourceManager;
import org.hl7.fhir.r5.context.HTMLClientLogger;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.formats.IParser;
import org.hl7.fhir.r5.formats.JsonParser;
import org.hl7.fhir.r5.formats.ParserType;
import org.hl7.fhir.r5.formats.XmlParser;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.r5.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.r5.model.ImplementationGuide;
import org.hl7.fhir.r5.model.NamingSystem;
import org.hl7.fhir.r5.model.NamingSystem.NamingSystemIdentifierType;
import org.hl7.fhir.r5.model.NamingSystem.NamingSystemUniqueIdComponent;
import org.hl7.fhir.r5.model.OperationOutcome;
import org.hl7.fhir.r5.model.Parameters;
import org.hl7.fhir.r5.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.r5.model.StringType;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.r5.model.StructureDefinition.TypeDerivationRule;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.r5.terminologies.TerminologyClient;
import org.hl7.fhir.r5.terminologies.TerminologyClientR5;
import org.hl7.fhir.r5.utils.INarrativeGenerator;
import org.hl7.fhir.r5.utils.IResourceValidator;
import org.hl7.fhir.r5.utils.NarrativeGenerator;
import org.hl7.fhir.r5.utils.client.EFhirClientException;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.OIDUtils;
import org.hl7.fhir.utilities.TranslatorXml;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueType;
import org.hl7.fhir.utilities.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.validation.ValidationOptions;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

/*
 *  private static Map<String, StructureDefinition> loadProfiles() throws Exception {
 HashMap<String, StructureDefinition> result = new HashMap<String, StructureDefinition>();
 Bundle feed = new XmlParser().parseGeneral(new FileInputStream(PROFILES)).getFeed();
 for (AtomEntry<? extends Resource> e : feed.getEntryList()) {
 if (e.getReference() instanceof StructureDefinition) {
 result.put(e.getId(), (StructureDefinition) e.getReference());
 }
 }
 return result;
 }

 private static final String TEST_PROFILE = "C:\\work\\org.hl7.fhir\\build\\publish\\namespace.profile.xml";
 private static final String PROFILES = "C:\\work\\org.hl7.fhir\\build\\publish\\profiles-resources.xml";

 igtodo - things to add: 
 - version
 - list of resource names

 */
public class BuildWorkerContext extends BaseWorkerContext implements IWorkerContext, ProfileKnowledgeProvider {

  private static final String SNOMED_EDITION = "900000000000207008"; // international
//  private static final String SNOMED_EDITION = "731000124108"; // us edition

  
  private UcumService ucum;
  private String version;
  private List<String> resourceNames = new ArrayList<String>();
  private Definitions definitions;
  private Map<String, Concept> snomedCodes = new HashMap<String, Concept>();
  private Map<String, Concept> loincCodes = new HashMap<String, Concept>();
  private boolean triedServer = false;
  private boolean serverOk = false;
  


  public BuildWorkerContext(Definitions definitions, TerminologyClient client, CanonicalResourceManager<CodeSystem> codeSystems, CanonicalResourceManager<ValueSet> valueSets, CanonicalResourceManager<ConceptMap> maps, CanonicalResourceManager<StructureDefinition> profiles, CanonicalResourceManager<ImplementationGuide> guides, String folder) throws UcumException, ParserConfigurationException, SAXException, IOException, FHIRException {
    super(codeSystems, valueSets, maps, profiles, guides);
    this.definitions = definitions;
    this.txClient = client;
    this.txLog = new HTMLClientLogger(null);
    setExpansionProfile(buildExpansionProfile());
    this.setTranslator(new TranslatorXml(Utilities.path(folder, "implementations", "translations.xml")));
  }

  private Parameters buildExpansionProfile() {
    Parameters res = new Parameters();
    res.addParameter("profile-url", "urn:uuid:"+UUID.randomUUID().toString().toLowerCase());
    res.addParameter("excludeNested", false);
    res.addParameter("includeDesignations", true);
    // res.addParameter("activeOnly", true);
    res.addParameter("system-version", "http://snomed.info/sct|http://snomed.info/sct/"+SNOMED_EDITION); // value sets are allowed to override this. for now
    return res;
  }

  public boolean hasClient() {
    return txClient != null;
  }

  public TerminologyClient getClient() {
    return txClient;
  }

  public StructureDefinition getExtensionStructure(StructureDefinition context, String url) throws Exception {
    if (url.startsWith("#")) {
      throw new Error("Contained extensions not done yet");
    } else {
      if (url.contains("#"))
        url = url.substring(0, url.indexOf("#"));
      StructureDefinition res = getStructure(url);
      if (res == null)
        return null;
      if (res.getSnapshot() == null || res.getSnapshot().getElement().isEmpty())
        throw new Exception("no snapshot on extension for url " + url);
      return res;
    }
  }


  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }

  public boolean isResource(String name) {
    if (resourceNames.contains(name))
      return true;
    StructureDefinition sd = getStructure("http://hl7.org/fhir/StructureDefinition/" + name);
    return sd != null && (sd.getBaseDefinition().endsWith("Resource") || sd.getBaseDefinition().endsWith("DomainResource"));
  }

  public List<String> getResourceNames() {
    return resourceNames;
  }

  public StructureDefinition getTypeStructure(TypeRefComponent type) {
    if (type.hasProfile())
      return getStructure(type.getProfile().get(0).getValue());
    else
      return getStructure(type.getWorkingCode());
  }

  @Override
  public IParser getParser(ParserType type) {
    switch (type) {
    case JSON: return newJsonParser();
    case XML: return newXmlParser();
    default:
      throw new Error("Parser Type "+type.toString()+" not supported");
    }
  }

  @Override
  public IParser getParser(String type) {
    if (type.equalsIgnoreCase("JSON"))
      return new JsonParser();
  if (type.equalsIgnoreCase("XML"))
    return new XmlParser();
  throw new Error("Parser Type "+type.toString()+" not supported");
  }

  @Override
  public IParser newJsonParser() {
    return new JsonParser();
  }

  @Override
  public IParser newXmlParser() {
    return new XmlParser();
  }

  @Override
  public INarrativeGenerator getNarrativeGenerator(String prefix, String basePath) {
    return new NarrativeGenerator(prefix, basePath, this);
  }

  @Override
  public IResourceValidator newValidator() {
    throw new Error("check this");
//    return new InstanceValidator(this, null);
  }

  @Override
  public List<ConceptMap> findMapsForSource(String url) throws FHIRException {
    List<ConceptMap> res = new ArrayList<ConceptMap>();
    for (ConceptMap map : listMaps())
      if (map.getSourceCanonicalType().getValue().equals(url)) 
        res.add(map);
    return res;
  }

  @Override
  public boolean supportsSystem(String system) throws TerminologyServiceException {
    return "http://snomed.info/sct".equals(system) || "http://www.nlm.nih.gov/research/umls/rxnorm".equals(system) || "http://loinc.org".equals(system) || "http://unitsofmeasure.org".equals(system) || super.supportsSystem(system) ;
  }
  
  public static class Concept {
    private String display; // preferred
    private List<String> displays = new ArrayList<String>();
    public String shortN;

    public Concept() {
      
    }

    public Concept(String d) {
      display = d;
      displays.add(d);
    }

    public boolean has(String d) {
      if (display.equalsIgnoreCase(d))
        return true;
      for (String s : displays)
        if (s.equalsIgnoreCase(d))
          return true;
      return false;
    }

    public String summary() {
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      b.append(display);
      for (String s : displays)
        if (!s.equalsIgnoreCase(display))
          b.append(s);
      return b.toString();
    }
  }
 
  public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
    if (system == null)
      return null;
    if (system.equals("http://snomed.info/sct"))
      try {
        return locateSnomed(code);
      } catch (Exception e) {
      }        
    if (system.equals("http://loinc.org"))
      try {
        return locateLoinc(code);
      } catch (Exception e) {
      }     
    CodeSystem cs = fetchCodeSystem(system);
    if (cs != null)
      return findCodeInConcept(cs.getConcept(), code);
    return null;
  }

  private ConceptDefinitionComponent locateSnomed(String code) throws Exception {
    if (!snomedCodes.containsKey(code))
      queryForTerm(code);
    if (!snomedCodes.containsKey(code))
      return null;
    ConceptDefinitionComponent cc = new ConceptDefinitionComponent();
    cc.setCode(code);
    cc.setDisplay(snomedCodes.get(code).display);
    return cc;
  }

  private ValidationResult verifySnomed(String code, String display) throws Exception {
    SnomedServerResponse response = null;
    if (!snomedCodes.containsKey(code))
      response = queryForTerm(code);
    if (snomedCodes.containsKey(code))
      if (display == null)
        return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(snomedCodes.get(code).display));
      else if (snomedCodes.get(code).has(display))
        return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(display));
      else 
        return new ValidationResult(IssueSeverity.WARNING, "Snomed Display Name for "+code+" must be one of '"+snomedCodes.get(code).summary()+"'");
    
    if (response != null) // this is a wrong expression 
      return new ValidationResult(IssueSeverity.ERROR, "The Snomed Expression "+code+" must use the form "+response.correctExpression);
    else  if (serverOk)
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Snomed Code "+code);
    else
      return new ValidationResult(IssueSeverity.WARNING, "Unknown Snomed Code "+code);
  }

  private static class SnomedServerResponse  {
    String correctExpression;
    String display;
  }

  private SnomedServerResponse queryForTerm(String code) throws Exception {
    if (!triedServer || serverOk) {
      triedServer = true;
      HttpClient httpclient = new DefaultHttpClient();
      HttpGet httpget = new HttpGet("http://tx.fhir.org/r4/snomed/tool/"+SNOMED_EDITION+"/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20")); 
//      HttpGet httpget = new HttpGet("http://local.fhir.org:960/r4/snomed/tool/"+SNOMED_EDITION+"/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20")); // don't like the url encoded this way
      HttpResponse response = httpclient.execute(httpget);
      HttpEntity entity = response.getEntity();
      InputStream instream = entity.getContent();
      try {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document xdoc = builder.parse(instream);
        // we always get back a version, and a type. What we do depends on the type 
        String t = xdoc.getDocumentElement().getAttribute("type");
        serverOk = true;
        if (t.equals("error")) 
          throw new Exception(xdoc.getDocumentElement().getAttribute("message"));
        if (t.equals("description"))
          throw new Exception("The Snomed code (\""+code+"\") is a description id not a concept id which is not valid");
        if (t.equals("concept")) {
          Concept c = new Concept();
          c.display = xdoc.getDocumentElement().getAttribute("display");
          Element child = XMLUtil.getFirstChild(xdoc.getDocumentElement());
          while (child != null) {
            c.displays.add(child.getAttribute("value"));
            child = XMLUtil.getNextSibling(child);
          }
          snomedCodes.put(code, c);
          return null;
        }
        if (t.equals("expression")) {
          SnomedServerResponse resp = new SnomedServerResponse();
          resp.correctExpression = xdoc.getDocumentElement().getAttribute("expressionMinimal");
          resp.display = xdoc.getDocumentElement().getAttribute("display");
          if (!snomedCodes.containsKey(resp.correctExpression)) {
            Concept c = new Concept();
            c.display = resp.display;
            snomedCodes.put(resp.correctExpression, c);
          }
          return resp;
        }
        throw new Exception("Unrecognised response from server");
      } finally {
        instream.close();
      }
    } else
      return null;
  }

  private ConceptDefinitionComponent locateLoinc(String code) throws Exception {
    if (!loincCodes.containsKey(code))
      return null;
    ConceptDefinitionComponent cc = new ConceptDefinitionComponent();
    cc.setCode(code);
    String s = loincCodes.get(code).display;
    cc.setDisplay(s);
    return cc;
  }

  private ValidationResult verifyLoinc(String code, String display) throws Exception {
    if (!loincCodes.containsKey(code)) {
      String d = lookupLoinc(code);
      if (d != null)
        loincCodes.put(code, new Concept(d));
      else
        return new ValidationResult(IssueSeverity.ERROR, "Unknown Loinc Code "+code);
    }
    Concept lc = loincCodes.get(code);
    if (display == null)
      return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(lc.display));
    if (!lc.has(display))
      return new ValidationResult(IssueSeverity.WARNING, "Loinc Display Name for "+code+" must be one of '"+lc.summary()+"'");
    return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(lc.display));
  }

  private ValidationResult verifyCode(CodeSystem cs, String code, String display) throws Exception {
    ConceptDefinitionComponent cc = findCodeInConcept(cs.getConcept(), code);
    if (cc == null)
      return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+cs.getUrl());
    if (display == null)
      return new ValidationResult(cc);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    if (cc.hasDisplay()) {
      b.append(cc.getDisplay());
      if (display.equalsIgnoreCase(cc.getDisplay()))
        return new ValidationResult(cc);
    }
    for (ConceptDefinitionDesignationComponent ds : cc.getDesignation()) {
      b.append(ds.getValue());
      if (display.equalsIgnoreCase(ds.getValue()))
        return new ValidationResult(cc);
    }
    return new ValidationResult(IssueSeverity.ERROR, "Display Name for "+code+" must be one of '"+b.toString()+"'");
  }

  private ValueSetExpansionContainsComponent findCode(List<ValueSetExpansionContainsComponent> contains, String code) {
    for (ValueSetExpansionContainsComponent cc : contains) {
      if (code.equals(cc.getCode()))
        return cc;
      ValueSetExpansionContainsComponent c = findCode(cc.getContains(), code);
      if (c != null)
        return c;
    }
    return null;
  }

  private ConceptDefinitionComponent findCodeInConcept(List<ConceptDefinitionComponent> concept, String code) {
    for (ConceptDefinitionComponent cc : concept) {
      if (code.equals(cc.getCode()))
        return cc;
      ConceptDefinitionComponent c = findCodeInConcept(cc.getConcept(), code);
      if (c != null)
        return c;
    }
    return null;
  }

  
  public ValidationResult validateCode(ValidationOptions options, String system, String code, String display) {
    try {
      if (system.equals("http://snomed.info/sct"))
        return verifySnomed(code, display);
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.WARNING, "Error validating snomed code \""+code+"\": "+e.getMessage());
    }
    try {
      if (system.equals("http://loinc.org"))
        return verifyLoinc(code, display);
      if (system.equals("http://unitsofmeasure.org"))
        return verifyUcum(code, display);
      CodeSystem cs = fetchCodeSystem(system);
      if (cs != null) {
        return verifyCode(cs, code, display);
      }
      if (system.startsWith("http://example.org"))
        return new ValidationResult(new ConceptDefinitionComponent());
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.ERROR, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
    return super.validateCode(options, system, code, display);
  }

  
  private ValidationResult verifyUcum(String code, String display) {
    String s = ucum.validate(code);
    if (s != null)
      System.out.println("UCUM eror: "+s);
//      return new ValidationResult(IssueSeverity.ERROR, s);
//    else {
      ConceptDefinitionComponent def = new ConceptDefinitionComponent();
      def.setCode(code);
      def.setDisplay(ucum.getCommonDisplay(code));
      return new ValidationResult(def);
//    }
  }

  public void loadSnomed(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(filename));
    Element code = XMLUtil.getFirstChild(xdoc.getDocumentElement());
    while (code != null) {
      if (Utilities.noString(code.getAttribute("no"))) {
        Concept c = new Concept();
        c.display = code.getAttribute("display");
        Element child = XMLUtil.getFirstChild(code);
        while (child != null) {
          c.displays.add(child.getAttribute("value"));
          child = XMLUtil.getNextSibling(child);
        }
        snomedCodes.put(code.getAttribute("id"), c);
      }
      code = XMLUtil.getNextSibling(code);
    }
  }

  public void loadUcum(String filename) throws UcumException {
    this.ucum = new UcumEssenceService(filename);
  }
  
  public void saveSnomed(String filename) throws Exception {
    FileOutputStream file = new FileOutputStream(filename);
    XMLWriter xml = new XMLWriter(file, "UTF-8");
    xml.setPretty(true);
    xml.setLineType(XMLWriter.LINE_UNIX);
    xml.start();
    xml.comment("the build tool builds these from the designated snomed server, when it can", true);
    xml.enter("snomed");
    
    List<String> ids = new ArrayList<String>();
    ids.addAll(snomedCodes.keySet());
    Collections.sort(ids);
    for (String s : ids) {
      xml.attribute("id", s);
      Concept c = snomedCodes.get(s);
      xml.attribute("display", c.display);
      if (c.displays.size() == 0)
        xml.element("concept", null);
      else {
        xml.enter("concept");
        for (String d : c.displays) {
          xml.attribute("value", d);
          xml.element("display", null);
        }
        xml.exit("concept");
      }
    }
    xml.exit("snomed");
    xml.end();
  }
  
  public void loadLoinc(String filename) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new CSFileInputStream(filename));
    Element code = XMLUtil.getFirstChild(xdoc.getDocumentElement());
    while (code != null) {
      Concept c = new Concept();
      c.display = code.getAttribute("long");
      c.shortN = code.getAttribute("short"); 
      if (!code.getAttribute("long").equalsIgnoreCase(code.getAttribute("short")))
        c.displays.add(code.getAttribute("short"));
      loincCodes.put(code.getAttribute("id"), c);
      code = XMLUtil.getNextSibling(code);
    }
  }

  public void saveLoinc(String filename) throws IOException {
    XMLWriter xml = new XMLWriter(new FileOutputStream(filename), "UTF-8");
    xml.setPretty(true);
    xml.setLineType(XMLWriter.LINE_UNIX);
    xml.start();
    xml.enter("loinc");
    List<String> codes = new ArrayList<String>();
    codes.addAll(loincCodes.keySet());
    Collections.sort(codes);
    for (String c : codes) {
      xml.attribute("id", c);
      Concept cc = loincCodes.get(c);
      xml.attribute("short", cc.shortN);
      xml.attribute("long", cc.display);
      xml.element("concept");
    }
    xml.exit("loinc");
    xml.end();
    xml.close();
  }
  
  public boolean verifiesSystem(String system) {
    return true;
  }
  
  private String lookupLoinc(String code) throws Exception {
    if (true) { //(!triedServer || serverOk) {
      try {
        triedServer = true;
        // for this, we use the FHIR client
        if (txClient == null) {
          txClient = new TerminologyClientR5(tsServer);
          this.txLog = new HTMLClientLogger(null);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("system", "http://loinc.org");
        Parameters result = txClient.lookupCode(params);

        for (ParametersParameterComponent p : result.getParameter()) {
          if (p.getName().equals("display"))
            return ((StringType) p.getValue()).asStringValue();
        }
        throw new Exception("Did not find LOINC code in return values");
      } catch (EFhirClientException e) {
        serverOk = true;
        throw e;
      } catch (Exception e) {
        serverOk = false;
        throw e;
      }
    } else
      throw new Exception("Server is not available");
  }

  private String systems(ValueSet vs) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (ConceptSetComponent inc : vs.getCompose().getInclude())
      b.append(inc.getSystem());
    return b.toString();
  }

  private OperationOutcome buildOO(String message) {
    OperationOutcome oo = new OperationOutcome();
    oo.addIssue().setSeverity(OperationOutcome.IssueSeverity.ERROR).setCode(OperationOutcome.IssueType.EXCEPTION).getDetails().setText(message);
    return oo;
  }

//  if (expandedVSCache == null)
//    expandedVSCache = new ValueSetExpansionCache(workerContext, Utilities.path(folders.srcDir, "vscache"));
//  ValueSetExpansionOutcome result = expandedVSCache.getExpander().expand(vs);
//  private ValueSetExpansionCache expandedVSCache;
//  if (expandedVSCache == null)
//    expandedVSCache = new ValueSetExpansionCache(workerContext, Utilities.path(folders.srcDir, "vscache"));
//  private ValueSetExpansionOutcome loadFromCache(String cachefn) {
//    // TODO Auto-generated method stub
//    return null;
//  }
//
//  ValueSetExpansionOutcome result = expandedVSCache.getExpander().expand(vs);
//  if (expandedVSCache == null)
//    expandedVSCache = new ValueSetExpansionCache(workerContext, Utilities.path(folders.srcDir, "vscache"));
//  ValueSetExpansionOutcome result = expandedVSCache.getExpander().expand(vs);
//
//  
//  public ValueSet expandVS(ValueSet vs) throws Exception {
//    JsonParser parser = new JsonParser();
//    parser.setOutputStyle(OutputStyle.NORMAL);
//    parser.compose(b, vs);
//    b.close();
//    String hash = Integer.toString(new String(b.toByteArray()).hashCode());
//    String fn = Utilities.path(cache, hash+".json");
//    if (new File(fn).exists()) {
//      Resource r = parser.parse(new FileInputStream(fn));
//      if (r instanceof OperationOutcome)
//        throw new Exception(((OperationOutcome) r).getIssue().get(0).getDetails());
//      else
//        return ((ValueSet) ((Bundle)r).getEntry().get(0).getResource());
//    }
//    vs.setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase()); // that's all we're going to set
//        
//    if (!triedServer || serverOk) {
//      try {
//        triedServer = true;
//        serverOk = false;
//        // for this, we use the FHIR client
//        IFHIRClient client = new FHIRSimpleClient();
//        client.initialize(tsServer);
//        Map<String, String> params = new HashMap<String, String>();
//        params.put("_query", "expand");
//        params.put("limit", "500");
//        ValueSet result = client.expandValueset(vs);
//        serverOk = true;
//        FileOutputStream s = new FileOutputStream(fn);
//        parser.compose(s, result);
//        s.close();
//
//        return result;
//      } catch (EFhirClientException e) {
//        serverOk = true;
//        FileOutputStream s = new FileOutputStream(fn);
//        parser.compose(s, e.getServerErrors().get(0));
//        s.close();
//
//        throw new Exception(e.getServerErrors().get(0).getIssue().get(0).getDetails());
//      } catch (Exception e) {
//        serverOk = false;
//        throw e;
//      }
//    } else
//      throw new Exception("Server is not available");
//  }
  
 
  public void saveCache() throws IOException {
    txCache.save();
  }


  private List<String> sorted(Set<String> keySet) {
    List<String> results = new ArrayList<String>();
    results.addAll(keySet);
    Collections.sort(results);
    return results;
  }

  private String makeFileName(String s) {
    return s.replace("http://hl7.org/fhir/ValueSet/", "").replace("http://", "").replace("/", "_");
  }

  @Override
  public String getAbbreviation(String name) {
    String s = definitions.getTLAs().get(name.toLowerCase());
    if (Utilities.noString(s))
      return "xxx";
    else
      return s;
  }

  public void setDefinitions(Definitions definitions) {
    this.definitions = definitions;    
  }

  @Override
  public List<StructureDefinition> allStructures() {
    List<StructureDefinition> result = new ArrayList<StructureDefinition>();
    result.addAll(listStructures());
    return result;
  }


  @Override
  public String oid2Uri(String oid) {
    String uri = OIDUtils.getUriForOid(oid);
    if (uri != null)
      return uri;
//    for (NamingSystem ns : systems) {
//      if (hasOid(ns, oid)) {
//        uri = getUri(ns);
//        if (uri != null)
//          return null;
//      }
//    }
    return null;
  }

  private String getUri(NamingSystem ns) {
    for (NamingSystemUniqueIdComponent id : ns.getUniqueId()) {
      if (id.getType() == NamingSystemIdentifierType.URI)
        return id.getValue();
    }
    return null;
  }

  private boolean hasOid(NamingSystem ns, String oid) {
    for (NamingSystemUniqueIdComponent id : ns.getUniqueId()) {
      if (id.getType() == NamingSystemIdentifierType.OID && id.getValue().equals(oid))
        return true;
    }
    return false;
  }

  @Override
  public boolean hasCache() {
    return true;
  }

  @Override
  public List<String> getTypeNames() {
    List<String> names = new ArrayList<String>();
    for (TypeRef tr : definitions.getKnownTypes())
      names.add(tr.getName());
    return names;
  }

  public List<StructureDefinition> getExtensionDefinitions() {
    List<StructureDefinition> res = new ArrayList<StructureDefinition>();
    for (StructureDefinition sd : listStructures()) {
      if (sd.getType().equals("Extension") && sd.getDerivation() == TypeDerivationRule.CONSTRAINT)
        res.add(sd);
    }
    return res;
  }

  public List<StructureDefinition> getProfiles() {
    List<StructureDefinition> res = new ArrayList<StructureDefinition>();
    for (StructureDefinition sd : listStructures()) {
      if (!sd.getType().equals("Extension") && sd.getDerivation() == TypeDerivationRule.CONSTRAINT)
        res.add(sd);
    }
    return res;
  }

  @Override
  public UcumService getUcumService() {
    return ucum;
  }

  public void generateSnapshot(StructureDefinition p) throws DefinitionException, FHIRException {
    generateSnapshot(p, false);
  }
  public void generateSnapshot(StructureDefinition p, boolean ifLogical) throws DefinitionException, FHIRException {
    if (!p.hasSnapshot() && (ifLogical || p.getKind() != StructureDefinitionKind.LOGICAL)) {
      if (!p.hasBaseDefinition())
        throw new DefinitionException("Profile "+p.getName()+" ("+p.getUrl()+") has no base and no snapshot");
      StructureDefinition sd = fetchResource(StructureDefinition.class, p.getBaseDefinition());
      if (sd == null)
        throw new DefinitionException("Profile "+p.getName()+" ("+p.getUrl()+") base "+p.getBaseDefinition()+" could not be resolved");
      List<ValidationMessage> msgs = new ArrayList<ValidationMessage>();
      List<String> errors = new ArrayList<String>();
      ProfileUtilities pu = new ProfileUtilities(this, msgs, this);
      pu.setThrowException(false);
      pu.sortDifferential(sd, p, p.getUrl(), errors, true);
      for (String err : errors)
        msgs.add(new ValidationMessage(Source.ProfileValidator, IssueType.EXCEPTION, p.getUserString("path"), "Error sorting Differential: "+err, ValidationMessage.IssueSeverity.ERROR));
      pu.generateSnapshot(sd, p, p.getUrl(), Utilities.extractBaseUrl(sd.getUserString("path")), p.getName());
      for (ValidationMessage msg : msgs) {
        if ((msg.getLevel() == ValidationMessage.IssueSeverity.ERROR) || msg.getLevel() == ValidationMessage.IssueSeverity.FATAL)
          throw new DefinitionException("Profile "+p.getName()+" ("+p.getUrl()+"). Error generating snapshot: "+msg.getMessage());
      }
      if (!p.hasSnapshot())
        throw new FHIRException("Profile "+p.getName()+" ("+p.getUrl()+"). Error generating snapshot");
      pu = null;
    }
  }

  @Override
  public boolean isDatatype(String typeSimple) {
    throw new Error("Not done yet");
  }

  @Override
  public boolean hasLinkFor(String typeSimple) {
    throw new Error("Not done yet");
  }

  @Override
  public String getLinkFor(String corePath, String typeSimple) {
    throw new Error("Not done yet");
  }

  @Override
  public BindingResolution resolveBinding(StructureDefinition def, ElementDefinitionBindingComponent binding, String path) throws FHIRException {
    throw new Error("Not done yet");
  }

  @Override
  public BindingResolution resolveBinding(StructureDefinition def, String url, String path) throws FHIRException {
    throw new Error("Not done yet");
  }

  @Override
  public String getLinkForProfile(StructureDefinition profile, String url) {
    throw new Error("Not done yet");
  }

  @Override
  public boolean prependLinks() {
    throw new Error("Not done yet");
  }

}
