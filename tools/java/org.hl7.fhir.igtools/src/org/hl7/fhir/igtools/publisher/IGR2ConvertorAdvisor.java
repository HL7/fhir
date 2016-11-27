package org.hl7.fhir.igtools.publisher;

import org.hl7.fhir.convertors.VersionConvertorAdvisor;
import org.hl7.fhir.dstu2.model.Resource;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.exceptions.FHIRException;

public class IGR2ConvertorAdvisor implements VersionConvertorAdvisor {

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
  }

  @Override
  public CodeSystem getCodeSystem(ValueSet src) {
    return null;
  }

}
