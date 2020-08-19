package org.hl7.fhir.tools.publisher;

import java.io.IOException;

import org.hl7.fhir.convertors.loaders.BaseLoaderR5.ILoaderKnowledgeProvider;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.utilities.cache.NpmPackage;

import com.google.gson.JsonSyntaxException;

public class UTGLoader implements ILoaderKnowledgeProvider {

  private String version;
  
  public UTGLoader(String version) {
    super();
    this.version = "current".equals(version) ? "1.0.0" : version;
  }

  @Override
  public String getResourcePath(Resource resource) {
    String path = "http://terminology.hl7.org/"+version+"/"+resource.fhirType()+"-"+resource.getId()+".html";
    resource.setUserData("external.url", path);
    return path;
  }

  @Override
  public ILoaderKnowledgeProvider forNewPackage(NpmPackage npm) throws JsonSyntaxException, IOException {
    return this;
  }

}
