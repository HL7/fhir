package org.hl7.fhir.tools.publisher;

import java.io.FileInputStream;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Example;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FeedFormat;
import org.hl7.fhir.instance.client.ResourceFormat;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.AtomCategory;
import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.AtomFeed;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;

public class SpecificationInternalClient implements FHIRClient {

  private PageProcessor page;
  private AtomFeed feed;

  public SpecificationInternalClient(PageProcessor page, AtomFeed feed) {
    this.page = page;
    this.feed = feed;
  }

  @Override
  public FHIRClient initialize(String baseServiceUrl) throws URISyntaxException {
    throw new Error("initialize not supported by the internal specification client");

  }

  @Override
  public void setPreferredResourceFormat(ResourceFormat resourceFormat) {
    throw new Error("setPreferredResourceFormat not supported by the internal specification client");
  }

  @Override
  public String getPreferredResourceFormat() {
    throw new Error("getPreferredResourceFormat not supported by the internal specification client");
  }

  @Override
  public void setPreferredFeedFormat(FeedFormat feedFormat) {
    throw new Error("setPreferredFeedFormat not supported by the internal specification client");
  }

  @Override
  public String getPreferredFeedFormat() {
    throw new Error("getPreferredFeedFormat not supported by the internal specification client");
  }

  @Override
  public Conformance getConformanceStatement() {
    throw new Error("getConformanceStatement not supported by the internal specification client");
  }

  @Override
  public Conformance getConformanceStatement(boolean useOptionsVerb) {
    throw new Error("getConformanceStatement not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<T> read(Class<T> resource, String id) {
    if (feed != null && feed.getById(id) != null) {
      
    }
    String[] path = id.split("/");
    if (path.length != 2)
      return null;
    try {
      ResourceDefn r = page.getDefinitions().getResourceByName(path[0]);
      for (Example e : r.getExamples()) {
        if (e.getId().equals(path[1])) {
          AtomEntry<Resource> ae = new AtomEntry<Resource>();
          ae.setId(id);
          ae.getLinks().put("self", e.getFileTitle()+".html");
          ae.setResource(new XmlParser().parse(new FileInputStream(e.getPath())));
          return (AtomEntry<T>) ae;
        }
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public <T extends Resource> AtomEntry<T> vread(Class<T> resource, String id, String versionid) {
    throw new Error("vread not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id) {
    throw new Error("update not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<T> update(Class<T> resourceClass, T resource, String id, List<AtomCategory> tags) {
    throw new Error("update not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
    throw new Error("delete not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource) {
    throw new Error("create not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<OperationOutcome> create(Class<T> resourceClass, T resource, List<AtomCategory> tags) {
    throw new Error("create not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass, String id) {
    throw new Error("create not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(Class<T> resource, String id) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(Calendar lastUpdate, Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(Calendar lastUpdate) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomEntry<OperationOutcome> validate(Class<T> resourceClass, T resource, String id) {
    throw new Error("validate not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed search(Class<T> resourceClass, Map<String, String> params) {
    throw new Error("search not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed searchPost(Class<T> resourceClass, T resource, Map<String, String> params) {
    throw new Error("searchPost not supported by the internal specification client");
  }

  @Override
  public AtomFeed transaction(AtomFeed batch) {
    throw new Error("transaction not supported by the internal specification client");
  }

  @Override
  public List<AtomCategory> getAllTags() {
    throw new Error("getAllTags not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> getAllTagsForResourceType(Class<T> resourceClass) {
    throw new Error("getAllTagsForResourceType not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> getTagsForResource(Class<T> resource, String id) {
    throw new Error("getTagsForResource not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> getTagsForResourceVersion(Class<T> resource, String id, String versionId) {
    throw new Error("getTagsForResourceVersion not supported by the internal specification client");
  }

  @Override
  public VersionInfo getVersions() {
    throw new Error("getVersions not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass, String id) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate, Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history(DateAndTime lastUpdate) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> AtomFeed history() {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> deleteTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
    throw new Error("deleteTags not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id) {
    throw new Error("createTags not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> List<AtomCategory> createTags(List<AtomCategory> tags, Class<T> resourceClass, String id, String version) {
    throw new Error("createTags not supported by the internal specification client");
  }

  @Override
  public AtomFeed fetchFeed(String url) {
    throw new Error("fetchFeed not supported by the internal specification client");
  }

  @Override
  public void initialize(String baseServiceUrl, int recordCount) throws URISyntaxException {
    // TODO Auto-generated method stub
    
  }

  @Override
  public int getMaximumRecordCount() {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public void setMaximumRecordCount(int recordCount) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public <T extends Resource> AtomFeed history(Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public ValueSet expandValueset(ValueSet source) throws Exception {
    throw new Error("expandValueset not supported by the internal specification client");
  }


}
