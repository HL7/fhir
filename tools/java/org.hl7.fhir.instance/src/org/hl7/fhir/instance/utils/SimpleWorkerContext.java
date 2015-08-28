package org.hl7.fhir.instance.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.ParserType;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.utils.client.FHIRToolingClient;
import org.hl7.fhir.instance.validation.IResourceValidator;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;

/*
 * This is a stand alone implementation of worker context for use inside a tool.
 * It loads from the validation package (validation-min.xml.zip), and has a 
 * very light cient to connect to an open unauthenticated terminology service
 */

public class SimpleWorkerContext implements IWorkerContext {
  
  // all maps are to the full URI
  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  private Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  private Map<String, StructureDefinition> structures = new HashMap<String, StructureDefinition>();
//private Map<String, DataElement> dataElements = new HashMap<String, DataElement>();
//  private Map<String, SearchParameter> searchParameters = new HashMap<String, SearchParameter>();
  
  private FHIRToolingClient tsServer;

  // -- Initializations
  /**
   * Load the working context from the validation pack
   * 
   * @param path
   *           filename of the validation pack
   * @return
   * @throws Exception
   */
  public static SimpleWorkerContext fromPack(String path) throws Exception {
    SimpleWorkerContext res = new SimpleWorkerContext();
    res.loadFromPack(path);
    return res;
  }

  public static SimpleWorkerContext fromClassPath() throws Exception {
    SimpleWorkerContext res = new SimpleWorkerContext();
    res.loadFromStream(SimpleWorkerContext.class.getResourceAsStream("validation.zip"));
    return res;
  }

  public static SimpleWorkerContext fromDefinitions(Map<String, byte[]> source) throws Exception {
    SimpleWorkerContext res = new SimpleWorkerContext();
    for (String name : source.keySet()) {
      if (name.endsWith(".xml")) {
      	res.loadFromFile(new ByteArrayInputStream(source.get(name)), name);
      }
    }
    return res;
  }

  public void connectToTSServer(String url) throws URISyntaxException {
  	tsServer = new FHIRToolingClient(url);
  }

  private void loadFromFile(InputStream stream, String name) throws Exception {
    XmlParser xml = new XmlParser();
    Bundle f = (Bundle) xml.parse(stream);
    for (BundleEntryComponent e : f.getEntry()) {

      if (e.getFullUrl() == null) {
        System.out.println("unidentified resource in " + name+" (no fullUrl)");
      }
      if (e.getResource() instanceof StructureDefinition)
        seeProfile(e.getFullUrl(), (StructureDefinition) e.getResource());
      else if (e.getResource() instanceof ValueSet)
        seeValueSet(e.getFullUrl(), (ValueSet) e.getResource());
      else if (e.getResource() instanceof ConceptMap)
        maps.put(((ConceptMap) e.getResource()).getUrl(), (ConceptMap) e.getResource());
    }
  }

  private void seeValueSet(String url, ValueSet vs) throws Exception {
  	if (valueSets.containsKey(vs.getUrl()))
  		throw new Exception("Duplicate Profile " + vs.getUrl());
  	valueSets.put(vs.getId(), vs);
  	valueSets.put(vs.getUrl(), vs);
  	if (!vs.getUrl().equals(url))
    	valueSets.put(url, vs);
  	if (vs.hasCodeSystem()) {
  		codeSystems.put(vs.getCodeSystem().getSystem().toString(), vs);
  	}
  }

  private void seeProfile(String url, StructureDefinition p) throws Exception {
  	if (structures.containsKey(p.getUrl()))
  		throw new Exception("Duplicate structures " + p.getUrl());
  	structures.put(p.getId(), p);
  	structures.put(p.getUrl(), p);
  	if (!p.getUrl().equals(url))
  		structures.put(url, p);
  }
  
  private void loadFromPack(String path) throws Exception {
    loadFromStream(new CSFileInputStream(path));
  }

  private void loadFromStream(InputStream stream) throws Exception {
    ZipInputStream zip = new ZipInputStream(stream);
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (ze.getName().endsWith(".xml")) {
        String name = ze.getName();
        loadFromFile(zip, name);
      }
      zip.closeEntry();
    }
    zip.close();
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
  public IResourceValidator newValidator() throws Exception {
    return new InstanceValidator(this);
  }
  
  @SuppressWarnings("unchecked")
	@Override
  public <T extends Resource> T fetchResource(Class<T> class_, String uri) throws EOperationOutcome, Exception {
    if (class_ == StructureDefinition.class && !uri.contains("/"))
      uri = "http://hl7.org/fhir/StructureDefinition/"+uri;
    
    if (uri.startsWith("http:")) {
      if (uri.contains("#"))
        uri = uri.substring(0, uri.indexOf("#"));
      if (class_ == StructureDefinition.class) {
        if (structures.containsKey(uri))
          return (T) structures.get(uri);
        else
          return null;
      } else if (class_ == ValueSet.class) {
        if (valueSets.containsKey(uri))
          return (T) valueSets.get(uri);
        else if (codeSystems.containsKey(uri))
          return (T) codeSystems.get(uri);
        else
          return null;      
      }
    }
    if (class_ == null && uri.contains("/")) {
      return null;      
    }
      
    throw new Error("not done yet");
  }
  
  
  @Override
  public ValueSet fetchCodeSystem(String system) {
    return codeSystems.get(system);
  }
  
  @Override
  public boolean supportsSystem(String system) {
    if (codeSystems.containsKey(system))
    	return true;
    else {
    	Conformance conf = tsServer.getConformanceStatement();
    	for (Extension ex : ToolingExtensions.getExtensions(conf, "http://hl7.org/fhir/StructureDefinition/conformance-supported-system")) {
    		if (system.equals(((UriType) ex.getValue()).getValue())) {
    			return true;
    		}
    	}
    }
    return false;
  }
  
  @Override
  public ValueSetExpansionOutcome expandVS(ValueSet vs) {
    try {
    	Map<String, String> params = new HashMap<String, String>();
    	params.put("_limit", "10000");
    	params.put("_incomplete", "true");
    	params.put("profile", "http://www.healthintersections.com.au/fhir/expansion/no-details");
    	ValueSet result = tsServer.expandValueset(vs, params);
    	return new ValueSetExpansionOutcome(result);  
    } catch (Exception e) {
      return new ValueSetExpansionOutcome("Error expanding ValueSet \""+vs.getUrl()+": "+e.getMessage());
    }
  }
  
  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc) {
    ValueSet vs = new ValueSet();
    vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().getInclude().add(inc);
    ValueSetExpansionOutcome vse = expandVS(vs);
    return vse.getValueset().getExpansion();
  }
  
  @Override
  public ValidationResult validateCode(String system, String code, String display) {
    try {
    	if (codeSystems.containsKey(system)) {
    		return verifyCode(codeSystems.get(system), code, display);
    	} 
    } catch (Exception e) {
      return new ValidationResult(IssueSeverity.ERROR, "Error validating code \""+code+"\" in system \""+system+"\": "+e.getMessage());
    }
    throw new Error("not done yet");
  }
  @Override
  public ValidationResult validateCode(String system, String code, String display, ValueSet vs) {
    throw new Error("not done yet");
  }
  @Override
  public ValidationResult validateCode(String system, String code, String display, ConceptSetComponent vsi) {
    throw new Error("not done yet");
  }

  @Override
  public List<ConceptMap> findMapsForSource(String url) {
    List<ConceptMap> res = new ArrayList<ConceptMap>();
    for (ConceptMap map : maps.values())
      if (((Reference) map.getSource()).getReference().equals(url)) 
        res.add(map);
    return res;
  }

//  private String version;
  
//  private IFHIRClient client = new NullClient();
//
//  private ITerminologyServices terminologyServices = new NullTerminologyServices();
//  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
//  private Map<String, StructureDefinition> extensionDefinitions = new HashMap<String, StructureDefinition>();
//  private List<String> resourceNames = new ArrayList<String>();
//  private Map<String, Questionnaire> questionnaires = new HashMap<String, Questionnaire>();
//
//  public WorkerContext() {
//    super();
//  }
//
//  public WorkerContext(ITerminologyServices conceptLocator, IFHIRClient client, Map<String, ValueSet> codeSystems, Map<String, ValueSet> valueSets,
//      Map<String, ConceptMap> maps, Map<String, StructureDefinition> profiles) {
//    super();
//    if (conceptLocator != null)
//      this.terminologyServices = conceptLocator;
//    if (client != null)
//      this.client = client;
//    if (codeSystems != null)
//      this.codeSystems = codeSystems;
//    if (valueSets != null)
//      this.valueSets = valueSets;
//    if (maps != null)
//      this.maps = maps;
//    if (profiles != null)
//      this.profiles = profiles;
//  }
//
//  public ITerminologyServices getTerminologyServices() {
//    return terminologyServices;
//  }
//
//  public boolean hasClient() {
//    return !(client == null || client instanceof NullClient);
//  }
//
//  public IFHIRClient getClient() {
//    return client;
//  }
//
//  public Map<String, ValueSet> getCodeSystems() {
//    return codeSystems;
//  }
//
//  public Map<String, DataElement> getDataElements() {
//    return dataElements;
//  }
//
//  public Map<String, ValueSet> getValueSets() {
//    return valueSets;
//  }
//
//  public Map<String, ConceptMap> getMaps() {
//    return maps;
//  }
//
//  public Map<String, StructureDefinition> getProfiles() {
//    return profiles;
//  }
//
//  public Map<String, StructureDefinition> getExtensionDefinitions() {
//    return extensionDefinitions;
//  }
//
//  public Map<String, Questionnaire> getQuestionnaires() {
//    return questionnaires;
//  }
//
//  public WorkerContext setTerminologyServices(ITerminologyServices terminologyServices) {
//    this.terminologyServices = terminologyServices;
//    return this;
//  }
//
//  public WorkerContext clone(IFHIRClient altClient) {
//    WorkerContext res = new WorkerContext(terminologyServices, null, codeSystems, valueSets, maps, profiles);
//    res.extensionDefinitions.putAll(extensionDefinitions);
//    res.version = version;
//    res.client = altClient;
//    return res;
//  }
//
//  public void seeExtensionDefinition(String url, StructureDefinition ed) throws Exception {
//    if (extensionDefinitions.get(ed.getUrl()) != null)
//      throw new Exception("duplicate extension definition: " + ed.getUrl());
//    extensionDefinitions.put(ed.getId(), ed);
//    extensionDefinitions.put(url, ed);
//    extensionDefinitions.put(ed.getUrl(), ed);
//  }
//
//  public void seeQuestionnaire(String url, Questionnaire theQuestionnaire) throws Exception {
//    if (questionnaires.get(theQuestionnaire.getId()) != null)
//      throw new Exception("duplicate extension definition: " + theQuestionnaire.getId());
//    questionnaires.put(theQuestionnaire.getId(), theQuestionnaire);
//    questionnaires.put(url, theQuestionnaire);
//  }
//
//  public void seeValueSet(String url, ValueSet vs) throws Exception {
//    if (valueSets.containsKey(vs.getUrl()))
//      throw new Exception("Duplicate Profile " + vs.getUrl());
//    valueSets.put(vs.getId(), vs);
//    valueSets.put(url, vs);
//    valueSets.put(vs.getUrl(), vs);
//    if (vs.hasCodeSystem()) {
//      codeSystems.put(vs.getCodeSystem().getSystem().toString(), vs);
//    }
//  }
//
//  public void seeProfile(String url, StructureDefinition p) throws Exception {
//    if (profiles.containsKey(p.getUrl()))
//      throw new Exception("Duplicate Profile " + p.getUrl());
//    profiles.put(p.getId(), p);
//    profiles.put(url, p);
//    profiles.put(p.getUrl(), p);
//  }
//
//  public class NullClient implements IFHIRClient {
//
//    @Override
//    public VersionInfo getVersions() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public IFHIRClient initialize(String baseServiceUrl) throws URISyntaxException {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public void initialize(String baseServiceUrl, int recordCount) throws URISyntaxException {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public void setPreferredResourceFormat(ResourceFormat resourceFormat) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public String getPreferredResourceFormat() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public void setPreferredFeedFormat(FeedFormat feedFormat) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public String getPreferredFeedFormat() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public int getMaximumRecordCount() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public void setMaximumRecordCount(int recordCount) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Conformance getConformanceStatement() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Conformance getConformanceStatement(boolean useOptionsVerb) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> T read(Class<T> resource, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> T vread(Class<T> resource, String id, String versionid) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> T update(Class<T> resourceClass, T resource, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> OperationOutcome create(Class<T> resourceClass, T resource) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Date lastUpdate, Class<T> resourceClass, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Class<T> resource, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Date lastUpdate, Class<T> resourceClass) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Class<T> resourceClass) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Calendar lastUpdate) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history(Date lastUpdate) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle history() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> OperationOutcome validate(Class<T> resourceClass, T resource, String id) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle search(Class<T> resourceClass, Map<String, String> params) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Bundle searchPost(Class<T> resourceClass, T resource, Map<String, String> params) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Bundle transaction(Bundle batch) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Bundle fetchFeed(String url) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public ValueSet expandValueset(ValueSet source) throws Exception {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public <T extends Resource> Parameters operateType(Class<T> resourceClass, String name, Parameters params) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Conformance getConformanceStatementQuick() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public Conformance getConformanceStatementQuick(boolean useOptionsVerb) {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public String getAddress() {
//      throw new Error("call to NullClient");
//    }
//
//    @Override
//    public ValueSet expandValueset(ValueSet source, Map<String, String> params) throws Exception {
//      throw new Error("call to NullClient");
//    }
//
//  }
//
//  public StructureDefinition getExtensionStructure(StructureDefinition context, String url) throws Exception {
//    if (url.startsWith("#")) {
//      throw new Error("Contained extensions not done yet");
//    } else {
//      if (url.contains("#"))
//        url = url.substring(0, url.indexOf("#"));
//      StructureDefinition res = extensionDefinitions.get(url);
//      if (res == null)
//        res = profiles.get(url);
//      if (res == null)
//        return null;
//      if (res.getSnapshot() == null || res.getSnapshot().getElement().isEmpty())
//        throw new Exception("no snapshot on extension for url " + url);
//      return res;
//    }
//  }
//
//  public class NullTerminologyServices implements ITerminologyServices {
//
//    @Override
//    public boolean supportsSystem(String system) {
//      return false;
//    }
//
//    @Override
//    public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
//      throw new Error("call to NullTerminologyServices");
//    }
//
//    @Override
//    public ValidationResult validateCode(String system, String code, String display) {
//      throw new Error("call to NullTerminologyServices");
//    }
//
//    @Override
//    public ValueSetExpansionComponent expandVS(ConceptSetComponent inc) throws Exception {
//      throw new Error("call to NullTerminologyServices");
//    }
//
//    @Override
//    public boolean checkVS(ConceptSetComponent vsi, String system, String code) {
//      throw new Error("call to NullTerminologyServices");
//    }
//
//    @Override
//    public boolean verifiesSystem(String system) {
//      return false;
//    }
//
//    @Override
//    public ValueSetExpansionOutcome expand(ValueSet vs) {
//      throw new Error("call to NullTerminologyServices");
//    }
//
//  }
//
//  public String getVersion() {
//    return version;
//  }
//
//  public void setVersion(String version) {
//    this.version = version;
//  }
//
//  @Override
//  public boolean isResource(String name) {
//    if (resourceNames.contains(name))
//      return true;
//    StructureDefinition sd = profiles.get("http://hl7.org/fhir/StructureDefinition/" + name);
//    return sd != null && (sd.getBase().endsWith("Resource") || sd.getBase().endsWith("DomainResource"));
//  }
//
//  public List<String> getResourceNames() {
//    return resourceNames;
//  }
//
//  public StructureDefinition getTypeStructure(TypeRefComponent type) {
//    if (type.hasProfile())
//      return profiles.get(type.getProfile().get(0).getValue());
//    else
//      return profiles.get(type.getCode());
//  }
//
//  public Map<String, SearchParameter> getSearchParameters() {
//    return searchParameters;
//  }

  private ValidationResult verifyCode(ValueSet vs, String code, String display) throws Exception {
    if (vs.hasExpansion() && !vs.hasCodeSystem()) {
      ValueSetExpansionContainsComponent cc = findCode(vs.getExpansion().getContains(), code);
      if (cc == null)
        return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+vs.getCodeSystem().getSystem());
      if (display == null)
        return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(cc.getDisplay()));
      if (cc.hasDisplay()) {
        if (display.equalsIgnoreCase(cc.getDisplay()))
          return new ValidationResult(new ConceptDefinitionComponent().setCode(code).setDisplay(cc.getDisplay()));
        return new ValidationResult(IssueSeverity.ERROR, "Display Name for "+code+" must be '"+cc.getDisplay()+"'");
      }
      return null;
    } else {
      ConceptDefinitionComponent cc = findCodeInConcept(vs.getCodeSystem().getConcept(), code);
      if (cc == null)
        return new ValidationResult(IssueSeverity.ERROR, "Unknown Code "+code+" in "+vs.getCodeSystem().getSystem());
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

  

}
