package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.dstu3.context.BaseWorkerContext;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.formats.IParser;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.ParserType;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.DataElement;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.ExpansionProfile;
import org.hl7.fhir.dstu3.model.ExpansionProfile.SystemVersionProcessingMode;
import org.hl7.fhir.dstu3.model.MetadataResource;
import org.hl7.fhir.dstu3.model.NamingSystem;
import org.hl7.fhir.dstu3.model.NamingSystem.NamingSystemIdentifierType;
import org.hl7.fhir.dstu3.model.NamingSystem.NamingSystemUniqueIdComponent;
import org.hl7.fhir.dstu3.model.OperationDefinition;
import org.hl7.fhir.dstu3.model.OperationOutcome;
import org.hl7.fhir.dstu3.model.Parameters;
import org.hl7.fhir.dstu3.model.Parameters.ParametersParameterComponent;
import org.hl7.fhir.dstu3.model.Questionnaire;
import org.hl7.fhir.dstu3.model.Reference;
import org.hl7.fhir.dstu3.model.Resource;
import org.hl7.fhir.dstu3.model.SearchParameter;
import org.hl7.fhir.dstu3.model.StringType;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.dstu3.terminologies.CodeSystemUtilities;
import org.hl7.fhir.dstu3.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.dstu3.utils.INarrativeGenerator;
import org.hl7.fhir.dstu3.utils.IResourceValidator;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.client.EFhirClientException;
import org.hl7.fhir.dstu3.utils.client.FHIRToolingClient;
import org.hl7.fhir.dstu3.validation.InstanceValidator;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.exceptions.TerminologyServiceException;
import org.hl7.fhir.exceptions.UcumException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.OIDUtils;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ucum.UcumEssenceService;
import org.hl7.fhir.utilities.ucum.UcumService;
import org.hl7.fhir.utilities.validation.ValidationMessage.IssueSeverity;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.hl7.fhir.utilities.xml.XMLWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import com.google.gson.JsonElement;
import com.google.gson.JsonNull;
import com.google.gson.JsonObject;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonWriter;

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
public class BuildWorkerContext extends BaseWorkerContext implements IWorkerContext {

  private static final String SNOMED_EDITION = "900000000000207008"; // international
//  private static final String SNOMED_EDITION = "731000124108"; // us edition

  //  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
//  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
//  private Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  private Map<String, DataElement> dataElements = new HashMap<String, DataElement>();
  private Map<String, StructureDefinition> profiles = new HashMap<String, StructureDefinition>();
  private Map<String, SearchParameter> searchParameters = new HashMap<String, SearchParameter>();
  private Map<String, StructureDefinition> extensionDefinitions = new HashMap<String, StructureDefinition>();
  
  private UcumService ucum;
  private String version;
  private List<String> resourceNames = new ArrayList<String>();
  private Map<String, Questionnaire> questionnaires = new HashMap<String, Questionnaire>();
  private Map<String, OperationDefinition> operations = new HashMap<String, OperationDefinition>();
  private Definitions definitions;
  private Map<String, Concept> snomedCodes = new HashMap<String, Concept>();
  private Map<String, Concept> loincCodes = new HashMap<String, Concept>();
  private boolean triedServer = false;
  private boolean serverOk = false;
  


  public BuildWorkerContext(Definitions definitions, FHIRToolingClient client, Map<String, CodeSystem> codeSystems, Map<String, ValueSet> valueSets, Map<String, ConceptMap> maps, Map<String, StructureDefinition> profiles) throws UcumException {
    super();
    this.definitions = definitions;
    this.txServer = client;
    this.codeSystems = codeSystems;
    this.valueSets = valueSets;
    this.maps = maps;
    this.profiles = profiles;
    this.cacheValidation = true;
    setExpansionProfile(buildExpansionProfile());
  }

  private ExpansionProfile buildExpansionProfile() {
    ExpansionProfile res = new ExpansionProfile();
    res.setUrl("urn:uuid:"+UUID.randomUUID().toString().toLowerCase());
    res.setExcludeNested(false);
    res.setIncludeDesignations(true);
    // res.setActiveOnly(true);
    res.addFixedVersion().setSystem("http://snomed.info/sct").setVersion("http://snomed.info/sct/"+SNOMED_EDITION).setMode(SystemVersionProcessingMode.DEFAULT); // value sets are allowed to override this. for now
    return res;
  }

  public boolean hasClient() {
    return txServer != null;
  }

  public FHIRToolingClient getClient() {
    return txServer;
  }

  public Map<String, CodeSystem> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, DataElement> getDataElements() {
    return dataElements;
  }

  public Map<String, ValueSet> getValueSets() {
    return valueSets;
  }

  public Map<String, ConceptMap> getMaps() {
    return maps;
  }

  public Map<String, StructureDefinition> getProfiles() {
    return profiles;
  }

  public Map<String, StructureDefinition> getExtensionDefinitions() {
    return extensionDefinitions;
  }

  public Map<String, Questionnaire> getQuestionnaires() {
    return questionnaires;
  }

  public Map<String, OperationDefinition> getOperations() {
    return operations;
  }

  public void seeExtensionDefinition(String url, StructureDefinition ed) throws Exception {
    if (extensionDefinitions.get(ed.getUrl()) != null)
      throw new Exception("duplicate extension definition: " + ed.getUrl());
    extensionDefinitions.put(ed.getId(), ed);
    extensionDefinitions.put(url, ed);
    extensionDefinitions.put(ed.getUrl(), ed);
  }

  public void seeQuestionnaire(String url, Questionnaire theQuestionnaire) throws Exception {
    if (questionnaires.get(theQuestionnaire.getId()) != null)
      throw new Exception("duplicate extension definition: "+theQuestionnaire.getId());
    questionnaires.put(theQuestionnaire.getId(), theQuestionnaire);
    questionnaires.put(url, theQuestionnaire);
  }

  public void seeOperation(OperationDefinition opd) throws Exception {
    if (operations.get(opd.getUrl()) != null)
      throw new Exception("duplicate extension definition: "+opd.getUrl());
    operations.put(opd.getUrl(), opd);
    operations.put(opd.getId(), opd);
  }

  public void seeValueSet(String url, ValueSet vs) throws Exception {
    if (valueSets.containsKey(vs.getUrl()))
      throw new Exception("Duplicate value set "+vs.getUrl());
    valueSets.put(vs.getId(), vs);
    valueSets.put(url, vs);
    valueSets.put(vs.getUrl(), vs);
    throw new Error("this is not used");
  }

  public void seeProfile(String url, StructureDefinition p) throws Exception {
    if (profiles.containsKey(p.getUrl()))
      throw new Exception("Duplicate Profile "+p.getUrl());
    profiles.put(p.getId(), p);
    profiles.put(url, p);
    profiles.put(p.getUrl(), p);
  }

  public StructureDefinition getExtensionStructure(StructureDefinition context, String url) throws Exception {
    if (url.startsWith("#")) {
      throw new Error("Contained extensions not done yet");
    } else {
      if (url.contains("#"))
        url = url.substring(0, url.indexOf("#"));
      StructureDefinition res = extensionDefinitions.get(url);
      if (res == null)
        res = profiles.get(url);
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
    StructureDefinition sd = profiles.get("http://hl7.org/fhir/StructureDefinition/" + name);
    return sd != null && (sd.getBaseDefinition().endsWith("Resource") || sd.getBaseDefinition().endsWith("DomainResource"));
  }

  public List<String> getResourceNames() {
    return resourceNames;
  }

  public StructureDefinition getTypeStructure(TypeRefComponent type) {
    if (type.hasProfile())
      return profiles.get(type.getProfile());
    else
      return profiles.get(type.getCode());
  }

  public Map<String, SearchParameter> getSearchParameters() {
    return searchParameters;
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

  public <T extends Resource> T fetchResource(Class<T> class_, String uri) {
    try {
      return fetchResourceWithException(class_, uri);
    } catch (FHIRException e) {
      throw new Error(e);
    }
  }
  
  @SuppressWarnings("unchecked")
  @Override
  public <T extends Resource> T fetchResourceWithException(Class<T> class_, String uri) throws FHIRException {
    if (class_ == StructureDefinition.class && !uri.contains("/"))
      uri = "http://hl7.org/fhir/StructureDefinition/"+uri;

    
    if (uri.startsWith("http:")) {
      if (uri.contains("#"))
        uri = uri.substring(0, uri.indexOf("#"));
      if (class_ == StructureDefinition.class) {
        if (profiles.containsKey(uri))
          return (T) profiles.get(uri);
        else if (extensionDefinitions.containsKey(uri))
          return (T) extensionDefinitions.get(uri);
        else
          return null;
      } else if (class_ == ValueSet.class) {
        if (valueSets.containsKey(uri))
          return (T) valueSets.get(uri);
        else if (codeSystems.containsKey(uri))
          return (T) codeSystems.get(uri);
        else
          return null;      
      } else if (class_ == OperationDefinition.class) {
        OperationDefinition od = operations.get(uri);
        if (od == null)
          System.out.println("Unable to resolve OperationDefinition "+uri);
        return (T) od;
      } else if (class_ == SearchParameter.class) {
        SearchParameter res = searchParameters.get(uri);
        if (res == null) {
          StringBuilder b = new StringBuilder();
          for (String s : searchParameters.keySet()) {
            b.append(s);
            b.append("\r\n");
          }
          try {
            TextFile.stringToFile(b.toString(), "c:\\temp\\sp.txt");
          } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
          }
        }
        
          
        return (T) res;
      }
    }
    if (class_ == null && uri.contains("/")) {
      return null;      
    }
      
    if (class_ == Questionnaire.class)
      return null;
    throw new FHIRException("not done yet: can't fetch "+uri);
  }

  @Override
  public <T extends Resource> boolean hasResource(Class<T> class_, String uri) {
    try {
      return fetchResource(class_, uri) != null;
    } catch (Exception e) {
      return false;
    }
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
  public List<ConceptMap> findMapsForSource(String url) {
    List<ConceptMap> res = new ArrayList<ConceptMap>();
    for (ConceptMap map : maps.values())
      if (((Reference) map.getSource()).getReference().equals(url)) 
        res.add(map);
    return res;
  }

  @Override
  public boolean supportsSystem(String system) throws TerminologyServiceException {
    return "http://snomed.info/sct".equals(system) || "http://loinc.org".equals(system) || "http://unitsofmeasure.org".equals(system) || super.supportsSystem(system) ;
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
    if (codeSystems.containsKey(system))
      return findCodeInConcept(codeSystems.get(system).getConcept(), code);
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
//       HttpGet httpget = new HttpGet("http://fhir3.healthintersections.com.au/snomed/tool/"+SNOMED_EDITION+"/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20"));
      HttpGet httpget = new HttpGet("http://local.healthintersections.com.au:960/snomed/tool/"+SNOMED_EDITION+"/"+URLEncoder.encode(code, "UTF-8").replace("+", "%20")); // don't like the url encoded this way
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

  
  public ValidationResult validateCode(String system, String code, String display) {
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
      if (codeSystems.containsKey(system) && codeSystems.get(system) != null) {
        return verifyCode(codeSystems.get(system), code, display);
      }
      if (system.startsWith("http://example.org"))
        return new ValidationResult(new ConceptDefinitionComponent());
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.ERROR, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
     return new ValidationResult(IssueSeverity.WARNING, "Unknown code system "+system);
  }

  
  private ValidationResult verifyUcum(String code, String display) {
    String s = ucum.validate(code);
//    if (s != null)
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
        if (txServer == null) {
          txServer = new FHIRToolingClient(tsServer);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("code", code);
        params.put("system", "http://loinc.org");
        Parameters result = txServer.lookupCode(params);

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

  @Override
  public ValueSetExpansionOutcome expandOnServer(ValueSet vs, String cacheFn) throws Exception {
    if (!triedServer || serverOk) {
      JsonParser parser = new JsonParser();
      try {
        triedServer = true;
        serverOk = false;
        // for this, we use the FHIR client
        if (txServer == null) {
          txServer = new FHIRToolingClient(tsServer);
        }
        Map<String, String> params = new HashMap<String, String>();
        params.put("_limit", PageProcessor.CODE_LIMIT_EXPANSION);
        params.put("_incomplete", "true");
        System.out.println("Use Tx Server from BWS for value set "+(vs.hasUrl() ? vs.getUrl() : "??")+" on "+systems(vs));
        ValueSet result = txServer.expandValueset(vs, expProfile.setIncludeDefinition(false), params);
        serverOk = true;
        FileOutputStream s = new FileOutputStream(cacheFn);
        parser.compose(s, result);
        s.close();

        return new ValueSetExpansionOutcome(result);
      } catch (EFhirClientException e) {
        serverOk = true;
        FileOutputStream s = new FileOutputStream(cacheFn);
        if (e.getServerErrors().isEmpty())
          parser.compose(s, buildOO(e.getMessage()));
        else
          parser.compose(s, e.getServerErrors().get(0));
        s.close();

        throw new Exception(e.getServerErrors().get(0).getIssue().get(0).getDetails().getText());
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
  
  
  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc, boolean heirarchy) {
    ValueSet vs = new ValueSet();
    vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().getInclude().add(inc);
    ValueSetExpansionOutcome vse = expandVS(vs, true, heirarchy);
    if (vse.getValueset() == null)
      return null;
    else
      return vse.getValueset().getExpansion();
  }

  public void saveCache() throws IOException {
    saveValidationCache();
  }

  @Override
  protected void loadValidationCache() throws JsonSyntaxException, Exception {
    File dir = new File(validationCachePath);
    if (!dir.exists())
      return;
    
    if (new File(Utilities.path(validationCachePath, "noy-supported.txt")).exists())
      for (String s : TextFile.fileToString(Utilities.path(validationCachePath, "not-supported.txt")).split("\\r?\\n"))
        nonSupportedCodeSystems.add(s);
    
    String[] files = dir.list();
    for (String f : files) {
      if (f.endsWith(".txt"))
        break;
      String fn = Utilities.path(validationCachePath, f);
      com.google.gson.JsonParser  parser = new com.google.gson.JsonParser();
      JsonObject json = (JsonObject) parser.parse(TextFile.fileToString(fn));
      Map<String, ValidationResult> t = new HashMap<String, IWorkerContext.ValidationResult>();
      for (JsonElement i : json.getAsJsonArray("outcomes")) {
        JsonObject o = (JsonObject) i;
        String s = o.get("hash").getAsString();
        JsonElement j = o.get("message");
        String m = null;
        if (!(j instanceof JsonNull))
          m = o.get("message").getAsString();
        j = o.get("severity");
        IssueSeverity sev = null;
        if (!(j instanceof JsonNull))
          sev = IssueSeverity.fromCode(j.getAsString());
        ConceptDefinitionComponent def = null;
        j = o.get("definition");
        if (j != null && j instanceof JsonObject) {
          def = new ConceptDefinitionComponent();
          o = (JsonObject) j;
          if (o.get("abstract").getAsBoolean())
            CodeSystemUtilities.setNotSelectable(null, def);
          if (!(o.get("code") instanceof JsonNull))
            def.setCode(o.get("code").getAsString());
          if (!(o.get("definition") instanceof JsonNull))
            def.setDefinition(o.get("definition").getAsString());
          if (!(o.get("display") instanceof JsonNull))
            def.setDisplay(o.get("display").getAsString());
        }
        t.put(s, new ValidationResult(sev, m, def));
      }
      validationCache.put(json.get("url").getAsString(), t);
    }
    
  }

  private void saveValidationCache() throws IOException {
    if (noTerminologyServer)
      return;
    
    File dir = new File(validationCachePath);
    if (dir.exists())
      Utilities.clearDirectory(validationCachePath);
    else
      dir.mkdir();

    String sl = null;
    for (String s : nonSupportedCodeSystems)
      sl = sl == null ? s : sl + "\r\n" + s;
    TextFile.stringToFile(sl, Utilities.path(validationCachePath, "not-supported.txt"));

    for (String s : validationCache.keySet()) {
      String fn = Utilities.path(validationCachePath, makeFileName(s)+".json");
      String cnt = "";
      if (new File(fn).exists())
        cnt = TextFile.fileToString(fn);
      StringWriter st = new StringWriter();
      JsonWriter gson = new JsonWriter(st);
      gson.setIndent("  ");
      gson.beginObject();
      gson.name("url");
      gson.value(s);
      gson.name("outcomes");
      gson.beginArray();
      Map<String, ValidationResult> t = validationCache.get(s);
      for (String sp : sorted(t.keySet())) {
        ValidationResult vr = t.get(sp);
        gson.beginObject();
        gson.name("hash");
        gson.value(sp);
        gson.name("severity");
        if (vr.getSeverity() == null)
          gson.nullValue();
        else
          gson.value(vr.getSeverity().toCode());
        gson.name("message");
        gson.value(vr.getMessage());
        if (vr.asConceptDefinition() != null) {
          gson.name("definition");
          gson.beginObject();
          gson.name("abstract");
          gson.value(CodeSystemUtilities.isNotSelectable(null, vr.asConceptDefinition()));
          gson.name("code");
          gson.value(vr.asConceptDefinition().getCode());
          gson.name("definition");
          gson.value(vr.asConceptDefinition().getDefinition());
          gson.name("display");
          gson.value(vr.asConceptDefinition().getDisplay());
          gson.endObject();
        }
        gson.endObject();
      }
      gson.endArray();
      gson.endObject();
      gson.close();
      String ncnt = st.toString();
      if (!ncnt.equals(cnt))
        TextFile.stringToFile(ncnt, fn);
    }
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
  public Set<String> typeTails() {
    return new HashSet<String>(Arrays.asList("Integer","UnsignedInt","PositiveInt","Decimal","DateTime","Date","Time","Instant","String","Uri","Oid","Uuid","Id","Boolean","Code","Markdown","Base64Binary","Coding","CodeableConcept","Attachment","Identifier","Quantity","SampledData","Range","Period","Ratio","HumanName","Address","ContactPoint","Timing","Reference","Annotation","Signature","Meta"));
  }

  @Override
  public List<StructureDefinition> allStructures() {
    List<StructureDefinition> result = new ArrayList<StructureDefinition>();
    result.addAll(profiles.values());
    return result;
  }

  @Override
  public List<MetadataResource> allConformanceResources() {
    List<MetadataResource> result = new ArrayList<MetadataResource>();
    result.addAll(profiles.values());
    result.addAll(valueSets.values());
    result.addAll(codeSystems.values());
    result.addAll(maps.values());
    result.addAll(transforms.values());
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



}
