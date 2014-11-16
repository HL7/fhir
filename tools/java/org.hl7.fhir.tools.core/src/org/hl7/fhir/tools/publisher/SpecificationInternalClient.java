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
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.utils.ResourceUtilities;

public class SpecificationInternalClient implements FHIRClient {

  private PageProcessor page;
  private Bundle feed;

  public SpecificationInternalClient(PageProcessor page, Bundle feed) {
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
  public <T extends Resource> T read(Class<T> resource, String id) {
    if (feed != null && ResourceUtilities.getById(feed, null, id) != null) {
      
    }
    String[] path = id.split("/");
    if (path.length != 2)
      return null;
    try {
      ResourceDefn r = page.getDefinitions().getResourceByName(path[0]);
      for (Example e : r.getExamples()) {
        if (e.getId().equals(path[1])) {
          return (T) new XmlParser().parse(new FileInputStream(e.getPath()));
        }
      }
      return null;
    } catch (Exception e) {
      return null;
    }
  }

  @Override
  public <T extends Resource> T vread(Class<T> resource, String id, String versionid) {
    throw new Error("vread not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> T update(Class<T> resourceClass, T resource, String id) {
    throw new Error("update not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> boolean delete(Class<T> resourceClass, String id) {
    throw new Error("delete not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> OperationOutcome create(Class<T> resourceClass, T resource) {
    throw new Error("create not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass, String id) {
    throw new Error("create not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(Class<T> resource, String id) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(Calendar lastUpdate, Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(Calendar lastUpdate) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> OperationOutcome validate(Class<T> resourceClass, T resource, String id) {
    throw new Error("validate not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle search(Class<T> resourceClass, Map<String, String> params) {
    throw new Error("search not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle searchPost(Class<T> resourceClass, T resource, Map<String, String> params) {
    throw new Error("searchPost not supported by the internal specification client");
  }

  @Override
  public Bundle transaction(Bundle batch) {
    throw new Error("transaction not supported by the internal specification client");
  }

  @Override
  public VersionInfo getVersions() {
    throw new Error("getVersions not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(DateAndTime lastUpdate, Class<T> resourceClass, String id) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(DateAndTime lastUpdate, Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history(DateAndTime lastUpdate) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public <T extends Resource> Bundle history() {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public Bundle fetchFeed(String url) {
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
  public <T extends Resource> Bundle history(Class<T> resourceClass) {
    throw new Error("history not supported by the internal specification client");
  }

  @Override
  public ValueSet expandValueset(ValueSet source) throws Exception {
    throw new Error("expandValueset not supported by the internal specification client");
  }


}
