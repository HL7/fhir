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

import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.instance.validation.ExtensionLocatorService;
import org.hl7.fhir.utilities.CSFileInputStream;

/*
 *  private static Map<String, Profile> loadProfiles() throws Exception {
    HashMap<String, Profile> result = new HashMap<String, Profile>();
    Bundle feed = new XmlParser().parseGeneral(new FileInputStream(PROFILES)).getFeed();
    for (AtomEntry<? extends Resource> e : feed.getEntryList()) {
      if (e.getReference() instanceof Profile) {
        result.put(e.getId(), (Profile) e.getReference());
      }
    }
    return result;
  }

  private static final String TEST_PROFILE = "C:\\work\\org.hl7.fhir\\build\\publish\\namespace.profile.xml";
  private static final String PROFILES = "C:\\work\\org.hl7.fhir\\build\\publish\\profiles-resources.xml";

 */
public class WorkerContext {

	public static class ExtensionDefinitionResult {
	  private ExtensionDefinition ex;
    private ElementDefinition ed;

    public ExtensionDefinitionResult(ExtensionDefinition ex, ElementDefinition ed) {
	    super();
	    this.ex = ex;
	    this.ed = ed;
	  }

    public ExtensionDefinition getExtensionDefinition() {
      return ex;
    }

    public ElementDefinition getElementDefinition() {
      return ed;
    }

  }

	private TerminologyServices terminologyServices = new NullTerminologyServices();
  private ExtensionLocatorService extensionLocator = new NullExtensionResolver();
  private FHIRClient client = new NullClient();
  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  private Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  private Map<String, Profile> profiles = new HashMap<String, Profile>();
  private Map<String, ExtensionDefinition> extensionDefinitions = new HashMap<String, ExtensionDefinition>();


  public WorkerContext() {
    super();
  }

  public WorkerContext(TerminologyServices conceptLocator, ExtensionLocatorService extensionLocator, FHIRClient client, Map<String, ValueSet> codeSystems,
      Map<String, ValueSet> valueSets, Map<String, ConceptMap> maps, Map<String, Profile> profiles) {
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

  public Map<String, ValueSet> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, ValueSet> getValueSets() {
    return valueSets;
  }

  public Map<String, ConceptMap> getMaps() {
    return maps;
  }

  public Map<String, Profile> getProfiles() {
    return profiles;
  }

  public Map<String, ExtensionDefinition> getExtensionDefinitions() {
    return extensionDefinitions;
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
    return res;
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
    Bundle f = (Bundle) xml.parse(stream);
    for (BundleEntryComponent e : f.getEntry()) {
      if (e.getResource().getId() == null) {
        System.out.println("unidentified resource in "+name);
      }
      if (e.getResource() instanceof Profile)
        seeProfile((Profile) e.getResource());
      else if (e.getResource() instanceof ValueSet)
        seeValueSet((ValueSet) e.getResource());
      else if (e.getResource() instanceof ExtensionDefinition)
        seeExtensionDefinition((ExtensionDefinition) e.getResource());
      else if (e.getResource() instanceof ConceptMap)
        maps.put(((ConceptMap) e.getResource()).getIdentifier(), (ConceptMap) e.getResource());
    }
  }

  public void seeExtensionDefinition(ExtensionDefinition ed) throws Exception {
    if (extensionDefinitions.get(ed.getUrl()) != null)
      throw new Exception("duplicate extension definition: "+ed.getUrl());
    extensionDefinitions.put(ed.getUrl(), ed);
  }

  public void seeValueSet(ValueSet vs) {
	  valueSets.put(vs.getIdentifier(), vs);
	  if (vs.getDefine() != null) {
	    codeSystems.put(vs.getDefine().getSystem().toString(), vs);
	  }
  }

  public void seeProfile(Profile p) {
	  profiles.put(p.getId(), p);
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
    public ConceptDefinitionComponent getCodeDefinition(String system, String code) {
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
	  public <T extends Resource> T read(Class<T> resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> T vread(Class<T> resource, String id, String versionid) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> T update(Class<T> resourceClass, T resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
	  	throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> OperationOutcome create(Class<T> resourceClass, T resource) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(DateAndTime lastUpdate, Class<T> resourceClass, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(Class<T> resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(DateAndTime lastUpdate, Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(Class<T> resourceClass) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(Calendar lastUpdate) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history(DateAndTime lastUpdate) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle history() {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> OperationOutcome validate(Class<T> resourceClass, T resource, String id) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle search(Class<T> resourceClass, Map<String, String> params) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public <T extends Resource> Bundle searchPost(Class<T> resourceClass, T resource, Map<String, String> params) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public Bundle transaction(Bundle batch) {
      throw new Error("call to NullClient");
	  }

	  @Override
	  public Bundle fetchFeed(String url) {
      throw new Error("call to NullClient");
	  }

    @Override
    public ValueSet expandValueset(ValueSet source) throws Exception {
      throw new Error("call to NullClient");
    }

  }

	public ExtensionDefinitionResult getExtensionDefinition(ExtensionDefinitionResult context, String url) throws Exception {
	  if (context != null && (!url.startsWith("http:") || !url.startsWith("https:"))) {
	    throw new Exception("not supported yet");
	  } else if (url.contains("#")) {
      String[] parts = url.split("\\#");	      
      ExtensionDefinition res = extensionDefinitions.get(parts[0]);
      return res == null ? null : new ExtensionDefinitionResult(res, getElement(url, res.getElement(), parts[1]));      
	  } else {
	  ExtensionDefinition res = extensionDefinitions.get(url);
	    return res == null ? null : new ExtensionDefinitionResult(res, res.getElement().get(0));
	  }
  }

  private ElementDefinition getElement(String context, List<ElementDefinition> elements, String path) throws Exception {
    for (ElementDefinition element : elements) {
      if (element.getPath().equals("Extension."+path))
        return element;
    }
    throw new Exception("Unable to find extension path "+context);
  }

}

    