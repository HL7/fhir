package org.hl7.fhir.tools.publisher;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.instance.client.FeedFormat;
import org.hl7.fhir.instance.client.IFHIRClient;
import org.hl7.fhir.instance.client.ResourceFormat;
import org.hl7.fhir.instance.formats.IParser;
import org.hl7.fhir.instance.formats.JsonParser;
import org.hl7.fhir.instance.formats.ParserType;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Parameters;
import org.hl7.fhir.instance.model.Questionnaire;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionComponent;
import org.hl7.fhir.instance.terminologies.ValueSetExpander.ValueSetExpansionOutcome;
import org.hl7.fhir.instance.utils.EOperationOutcome;
import org.hl7.fhir.instance.utils.INarrativeGenerator;
import org.hl7.fhir.instance.utils.IWorkerContext;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.validation.IResourceValidator;
import org.hl7.fhir.instance.validation.InstanceValidator;

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
public class BuildWorkerContext implements IWorkerContext {

  private SpecificationTerminologyServices terminologyServices;
  private IFHIRClient client;
  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private Map<String, DataElement> dataElements = new HashMap<String, DataElement>();
  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  private Map<String, ConceptMap> maps = new HashMap<String, ConceptMap>();
  private Map<String, StructureDefinition> profiles = new HashMap<String, StructureDefinition>();
  private Map<String, SearchParameter> searchParameters = new HashMap<String, SearchParameter>();
  private Map<String, StructureDefinition> extensionDefinitions = new HashMap<String, StructureDefinition>();
  private String version;
  private List<String> resourceNames = new ArrayList<String>();
  private Map<String, Questionnaire> questionnaires = new HashMap<String, Questionnaire>();
  private Definitions definitions;


  public BuildWorkerContext(Definitions definitions, SpecificationTerminologyServices conceptLocator, IFHIRClient client, Map<String, ValueSet> codeSystems,
      Map<String, ValueSet> valueSets, Map<String, ConceptMap> maps, Map<String, StructureDefinition> profiles) {
    super();
    this.definitions = definitions;
    this.terminologyServices = conceptLocator;
    this.client = client;
    this.codeSystems = codeSystems;
    this.valueSets = valueSets;
    this.maps = maps;
    this.profiles = profiles;
  }

  public SpecificationTerminologyServices getTerminologyServices() {
    return terminologyServices;
  }

  public boolean hasClient() {
    return client != null;
  }

  public IFHIRClient getClient() {
    return client;
  }

  public Map<String, ValueSet> getCodeSystems() {
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

  public BuildWorkerContext setTerminologyServices(SpecificationTerminologyServices terminologyServices) {
    this.terminologyServices = terminologyServices;
    return this;
  }

  public BuildWorkerContext clone(IFHIRClient altClient) {
    BuildWorkerContext res = new BuildWorkerContext(definitions, terminologyServices, null, codeSystems, valueSets, maps, profiles);
    res.extensionDefinitions.putAll(extensionDefinitions);
    res.version = version;
    res.client = altClient;
    return res;
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

  public void seeValueSet(String url, ValueSet vs) throws Exception {
    if (valueSets.containsKey(vs.getUrl()))
      throw new Exception("Duplicate Profile "+vs.getUrl());
    valueSets.put(vs.getId(), vs);
    valueSets.put(url, vs);
    valueSets.put(vs.getUrl(), vs);
	  if (vs.hasCodeSystem()) {
	    codeSystems.put(vs.getCodeSystem().getSystem().toString(), vs);
    }
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
    return sd != null && (sd.getBase().endsWith("Resource") || sd.getBase().endsWith("DomainResource"));
  }

  public List<String> getResourceNames() {
    return resourceNames;
  }

  public StructureDefinition getTypeStructure(TypeRefComponent type) {
    if (type.hasProfile())
      return profiles.get(type.getProfile().get(0).getValue());
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

  @SuppressWarnings("unchecked")
  @Override
  public <T extends Resource> T fetchResource(Class<T> class_, String uri) throws EOperationOutcome, Exception {
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
      }
    }
    if (class_ == null && uri.contains("/")) {
      return null;      
    }
      
    throw new Error("not done yet");
  }

  @Override
  public <T extends Resource> boolean hasResource(Class<T> class_, String uri) {
    throw new Error("not done yet");
  }

  @Override
  public INarrativeGenerator getNarrativeGenerator(String prefix, String basePath) {
    return new NarrativeGenerator(prefix, basePath, this);
  }

  @Override
  public IResourceValidator newValidator() throws Exception {
    return new InstanceValidator(this, null);
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
  public ValueSet fetchCodeSystem(String system) {
    return codeSystems.get(system);
  }

  @Override
  public boolean supportsSystem(String system) {
    return terminologyServices.supportsSystem(system);
  }

  @Override
  public ValueSetExpansionOutcome expandVS(ValueSet source) {
    return terminologyServices.expand(source);
  }

  @Override
  public ValueSetExpansionComponent expandVS(ConceptSetComponent inc) {
    return terminologyServices.expandVS(inc);
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display) {
    ValidationResult res = terminologyServices.validateCode(system, code, display);
    return res;
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display, ValueSet vs) {
    throw new Error("not done yet");
  }

  @Override
  public ValidationResult validateCode(String system, String code, String display, ConceptSetComponent vsi) {
    throw new Error("not done yet");
  }

}
