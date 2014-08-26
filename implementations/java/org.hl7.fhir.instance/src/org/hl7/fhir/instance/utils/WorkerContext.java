package org.hl7.fhir.instance.utils;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FeedFormat;
import org.hl7.fhir.instance.client.ResourceFormat;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.validation.ExtensionLocatorService;
import org.hl7.fhir.utilities.CSFileInputStream;

/*
 *  private static Map<String, Profile> loadProfiles() throws Exception {
    HashMap<String, Profile> result = new HashMap<String, Profile>();
    AtomFeed feed = new XmlParser().parseGeneral(new FileInputStream(PROFILES)).getFeed();
    for (AtomEntry<? extends Resource> e : feed.getEntryList()) {
      if (e.getResource() instanceof Profile) {
        result.put(e.getId(), (Profile) e.getResource());
      }
    }
    return result;
  }

  private static final String TEST_PROFILE = "C:\\work\\org.hl7.fhir\\build\\publish\\namespace.profile.xml";
  private static final String PROFILES = "C:\\work\\org.hl7.fhir\\build\\publish\\profiles-resources.xml";

 */
public class WorkerContext {

	private TerminologyServices terminologyServices = new NullTerminologyServices();
  private ExtensionLocatorService extensionLocator = new NullExtensionResolver();
  private FHIRClient client = new NullClient();
  private Map<String, AtomEntry<ValueSet>> codeSystems = new HashMap<String, AtomEntry<ValueSet>>();
  private Map<String, AtomEntry<ValueSet>> valueSets = new HashMap<String, AtomEntry<ValueSet>>();
  private Map<String, AtomEntry<ConceptMap>> maps = new HashMap<String, AtomEntry<ConceptMap>>();
  private Map<String, AtomEntry<Profile>> profiles = new HashMap<String, AtomEntry<Profile>>();


  public WorkerContext() {
    super();
  }

  public WorkerContext(TerminologyServices conceptLocator, ExtensionLocatorService extensionLocator, FHIRClient client, Map<String, AtomEntry<ValueSet>> codeSystems,
      Map<String, AtomEntry<ValueSet>> valueSets, Map<String, AtomEntry<ConceptMap>> maps, Map<String, AtomEntry<Profile>> profiles) {
    super();
    if (conceptLocator != null)
      this.terminologyServices = conceptLocator;
    if (extensionLocator != null)
      this.extensionLocator = extensionLocator;
    if (client != null)
      this.client = client;
    if (codeSystems != null)
      this.codeSystems = codeSystems;
    if (valueSets != null)
      this.valueSets = valueSets;
    if (maps != null)
      this.maps = maps;
    if (profiles != null)
      this.profiles = profiles;
  }

  public TerminologyServices getTerminologyServices() {
    return terminologyServices;
  }

  public ExtensionLocatorService getExtensionLocator() {
    return extensionLocator;
  }

  public boolean hasClient() {
  	return !(client == null || client instanceof NullClient);
  }
  public FHIRClient getClient() {
    return client;
  }

  public Map<String, AtomEntry<ValueSet>> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, AtomEntry<ValueSet>> getValueSets() {
    return valueSets;
  }

  public Map<String, AtomEntry<ConceptMap>> getMaps() {
    return maps;
  }

  public Map<String, AtomEntry<Profile>> getProfiles() {
    return profiles;
  }

  public void setTerminologyServices(TerminologyServices terminologyServices) {
    this.terminologyServices = terminologyServices;    
  }


  public WorkerContext clone(FHIRClient altClient) {
    WorkerContext res = new WorkerContext(terminologyServices, extensionLocator, null, codeSystems, valueSets, maps, profiles);
    res.client = altClient;
    return res;
  }

  // -- Initializations
  /**
   * Load the working context from the validation pack
   * 
   * @param path filename of the validation pack
   * @return
   * @throws Exception 
   */
  public static WorkerContext fromPack(String path) throws Exception {
    WorkerContext res = new WorkerContext();
    res.loadFromPack(path);
    return res;
  }


  public static WorkerContext fromDefinitions(Map<String, byte[]> source) throws Exception {
    WorkerContext res = new WorkerContext();
    for (String name : source.keySet()) {
      if (name.endsWith(".xml")) {
        res.loadFromFile(new ByteArrayInputStream(source.get(name)), name);        
      }
    }
    return null;
  }

  private void loadFromPack(String path) throws Exception {
    ZipInputStream zip = new ZipInputStream(new CSFileInputStream(path));
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

	@SuppressWarnings("unchecked")
  private void loadFromFile(InputStream stream, String name) throws Exception {
    XmlParser xml = new XmlParser();
    AtomFeed f = xml.parseGeneral(stream).getFeed();
    for (AtomEntry<?> e : f.getEntryList()) {
      if (e.getId() == null) {
        System.out.println("unidentified resource "+e.getLinks().get("self")+" in "+name);
      }
      Resource r = e.getResource();
      if (r instanceof Profile)
        seeProfile((AtomEntry<Profile>) e);
      else if (r instanceof ValueSet)
        seeValueSet((AtomEntry<ValueSet>) e);
      else if (r instanceof ConceptMap)
        maps.put(((ConceptMap) r).getIdentifierSimple(), (AtomEntry<ConceptMap>) e);
    }
  }

  public void seeValueSet(AtomEntry<ValueSet> e) {
	  ValueSet vs = (ValueSet) e.getResource();
	  valueSets.put(vs.getIdentifierSimple(), e);
	  if (vs.getDefine() != null) {
	    codeSystems.put(vs.getDefine().getSystemSimple().toString(), e);
	  }
  }

  public void seeProfile(AtomEntry<Profile> e) {
	  Profile p = (Profile) e.getResource();
	  if (p.getStructure().get(0).getName() != null)
	    profiles.put(p.getStructure().get(0).getNameSimple(), e);
	  else 
	    profiles.put(p.getStructure().get(0).getTypeSimple(), e);
	  profiles.put(e.getId(), e);
  }

  public class NullExtensionResolver implements ExtensionLocatorService {

    @Override
    public ExtensionLocationResponse locateExtension(String uri) {
      throw new Error("call to NullExtensionResolver");
    }
  }
  public class NullTerminologyServices implements TerminologyServices {

    @Override
    public boolean supportsSystem(String system) {
      throw new Error("call to NullTerminologyServices");
    }

    @Override
    public ValueSetDefineConceptComponent getCodeDefinition(String system, String code) {
      throw new Error("call to NullTerminologyServices");
    }

    @Override
    public ValidationResult validateCode(String system, String code, String display) {
      throw new Error("call to NullTerminologyServices");
    }

    @Override
    public List<ValueSetExpansionContainsComponent> expandVS(ConceptSetComponent inc) throws Exception {
      throw new Error("call to NullTerminologyServices");
    }

    @Override
    public boolean checkVS(ConceptSetComponent vsi, String system, String code) {
      throw new Error("call to NullTerminologyServices");
    }

    @Override
    public boolean verifiesSystem(String system) {
      throw new Error("call to NullTerminologyServices");
    }

  }

  public class NullClient implements FHIRClient {

	  @Override
	  public VersionInfo getVersions() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public FHIRClient initialize(String baseServiceUrl) throws URISyntaxException {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public void initialize(String baseServiceUrl, int recordCount) throws URISyntaxException {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public void setPreferredResourceFormat(ResourceFormat resourceFormat) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public String getPreferredResourceFormat() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public void setPreferredFeedFormat(FeedFormat feedFormat) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public String getPreferredFeedFormat() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public int getMaximumRecordCount() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public void setMaximumRecordCount(int recordCount) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public Conformance getConformanceStatement() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public Conformance getConformanceStatement(boolean useOptionsVerb) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<T> read(Class<T> resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<T> vread(Class<T> resource, String id, String versionid) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id, List<AtomCategory> tags) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
	  	throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource, List<AtomCategory> tags) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(Class<T> resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(Calendar lastUpdate) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed history() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> params) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> AtomFeed searchPost(Class<T> resourceClass, T resource, Map<String, String> params) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public AtomFeed transaction(AtomFeed batch) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public List<AtomCategory> getAllTags() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> getAllTagsForResourceType(Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> getTagsForResource(Class<T> resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> getTagsForResourceVersion(Class<T> resource, String id, String versionId) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> deleteTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public AtomFeed fetchFeed(String url) {
      throw new Error("call to NullClient");
	  }

    @Override
    public ValueSet expandValueset(ValueSet source) throws Exception {
      throw new Error("call to NullClient");
    }

  }

}

    