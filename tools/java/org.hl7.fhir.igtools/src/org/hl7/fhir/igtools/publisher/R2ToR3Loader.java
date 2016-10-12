package org.hl7.fhir.igtools.publisher;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.convertors.VersionConvertor_10_20;
import org.hl7.fhir.convertors.VersionConvertor_10_20.VersionConvertorAdvisor;
import org.hl7.fhir.dstu2.formats.JsonParser;
import org.hl7.fhir.dstu2.formats.XmlParser;
import org.hl7.fhir.dstu2.model.Resource;
import org.hl7.fhir.dstu3.context.SimpleWorkerContext.IContextResourceLoader;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.exceptions.FHIRException;

public class R2ToR3Loader implements IContextResourceLoader, VersionConvertorAdvisor {

  private List<CodeSystem> cslist = new ArrayList<>();

  @Override
  public Bundle loadBundle(InputStream stream, boolean isJson) throws FHIRException, IOException {
    Resource r2 = null;
    if (isJson)
      r2 = new JsonParser().parse(stream);
    else
      r2 = new XmlParser().parse(stream);
    org.hl7.fhir.dstu3.model.Resource r3 = new VersionConvertor_10_20(this).convertResource(r2);
    Bundle b = (Bundle) r3;
    for (CodeSystem cs : cslist) {
      BundleEntryComponent be = b.addEntry();
      be.setFullUrl(cs.getUrl());
      be.setResource(cs);
    }
    return b;
  }

  @Override
  public boolean ignoreEntry(BundleEntryComponent src) {
    return false;
  }

  @Override
  public Resource convert(org.hl7.fhir.dstu3.model.Resource resource) throws FHIRException {
    return null;
  }

  @Override
  public void handleCodeSystem(CodeSystem cs, ValueSet vs) {
    cs.setId(vs.getId());
    cslist.add(cs);
    
  }

  @Override
  public CodeSystem getCodeSystem(ValueSet src) {
    return null;
  }

}
