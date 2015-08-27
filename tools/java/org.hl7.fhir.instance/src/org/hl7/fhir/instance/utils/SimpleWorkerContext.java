package org.hl7.fhir.instance.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
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
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.validation.IResourceValidator;
import org.hl7.fhir.instance.validation.InstanceValidator;
import org.hl7.fhir.utilities.CSFileInputStream;

/*
 * This is a stand alone implementation of worker context for use inside a tool.
 * It loads from the validation package (validation-min.xml.zip), and has a 
 * very light cient to connect to an open unauthenticated terminology service
 */

public class SimpleWorkerContext implements IWorkerContext {
  
  // all maps are to the full URI
  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private Map<String, DataElement> dataElements = new HashMap<String, DataElement>();
  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  private Map<String, ConceptMap> conceptMaps = new HashMap<String, ConceptMap>();
  private Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  private Map<String, StructureDefinition> structures = new HashMap<String, StructureDefinition>();
  private Map<String, SearchParameter> searchParameters = new HashMap<String, SearchParameter>();
  
  private static void loadFromFile(SimpleWorkerContext theCtx, InputStream stream, String name) throws Exception {
//    XmlParser xml = new XmlParser();
//    Bundle f = (Bundle) xml.parse(stream);
//    for (BundleEntryComponent e : f.getEntry()) {
//
//      if (e.getFullUrl() == null) {
//        System.out.println("unidentified resource in " + name+" (no fullUrl)");
//      }
//      if (e.getResource() instanceof StructureDefinition)
//        theCtx.seeProfile(e.getFullUrl(), (StructureDefinition) e.getResource());
//      else if (e.getResource() instanceof ValueSet)
//        theCtx.seeValueSet(e.getFullUrl(), (ValueSet) e.getResource());
//      else if (e.getResource() instanceof StructureDefinition)
//        theCtx.seeExtensionDefinition(e.getFullUrl(), (StructureDefinition) e.getResource());
//      else if (e.getResource() instanceof ConceptMap)
//        theCtx.getMaps().put(((ConceptMap) e.getResource()).getUrl(), (ConceptMap) e.getResource());
//    }
  }

  private static void loadFromPack(SimpleWorkerContext theCtx, String path) throws Exception {
    loadFromStream(theCtx, new CSFileInputStream(path));
  }

  private static void loadFromStream(SimpleWorkerContext theCtx, InputStream stream) throws Exception {
    ZipInputStream zip = new ZipInputStream(stream);
    ZipEntry ze;
    while ((ze = zip.getNextEntry()) != null) {
      if (ze.getName().endsWith(".xml")) {
        String name = ze.getName();
        loadFromFile(theCtx, zip, name);
      }
      zip.closeEntry();
    }
    zip.close();
  }

  public static SimpleWorkerContext fromDefinitions(Map<String, byte[]> source) throws Exception {
    SimpleWorkerContext res = new SimpleWorkerContext();
    for (String name : source.keySet()) {
      if (name.endsWith(".xml")) {
        loadFromFile(res, new ByteArrayInputStream(source.get(name)), name);
      }
    }
    return res;
  }

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
    loadFromPack(res, path);
    return res;
  }

  public static SimpleWorkerContext fromClassPath() throws Exception {
    SimpleWorkerContext res = new SimpleWorkerContext();
    loadFromStream(res, SimpleWorkerContext.class.getResourceAsStream("validation.zip"));
    return res;
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
  public <T extends Resource> T fetchResource(Class<T> class_, String uri) throws EOperationOutcome, Exception {
    throw new Exception("not done yet");
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
  
  
  @Override
  public ValueSet fetchCodeSystem(String system) {
    throw new Error("not done yet");
  }
  @Override
  public boolean supportsSystem(String system) {
    throw new Error("not done yet");
  }
  @Override
  public ValueSetExpansionOutcome expandVS(ValueSet source) {
    throw new Error("not done yet");
  }
  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc) {
    throw new Error("not done yet");
  }
  @Override
  public ValidationResult validateCode(String system, String code, String display) {
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
    throw new Error("not done yet");
    //  // if (((Reference) a.getSource()).getReference().equals(url)) {

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

}
