package org.hl7.fhir.tools.converters;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.convertors.VersionConvertorAdvisor;
import org.hl7.fhir.convertors.VersionConvertor_10_30;
import org.hl7.fhir.dstu2.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu2.model.Resource;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CompartmentDefinition;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.exceptions.FHIRException;

public class DSTU2ValidationConvertor implements VersionConvertorAdvisor {

  private Bundle source;
  private VersionConvertor_10_30 vc;
  
  public void convert(String bundleSource, String bundleTarget) throws Exception {
    System.out.println("Convert "+bundleSource);
    vc = new VersionConvertor_10_30(this);
    
    try {
      source = (Bundle) new XmlParser().parse(new FileInputStream(bundleSource));
      org.hl7.fhir.dstu2.model.Bundle target = vc.convertBundle(source);
      new org.hl7.fhir.dstu2.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(bundleTarget), target);     
    } catch (Exception e) {
      throw new Exception(e);
    } 
  }
  
  @Override
  public boolean ignoreEntry(BundleEntryComponent src) {
    return src.getResource() instanceof CompartmentDefinition || src.getResource() instanceof CodeSystem;
  }

  public static void main(String[] args) throws Exception {
    DSTU2ValidationConvertor dstu2 = new DSTU2ValidationConvertor();
    String src = "C:\\work\\org.hl7.fhir\\build\\publish\\";
    String dst = "C:\\work\\org.hl7.fhir\\build\\temp\\";
    dstu2.convert(src  + "profiles-types.xml", dst  + "profiles-types-r2.xml");
    dstu2.convert(src + "profiles-resources.xml", dst + "profiles-resources-r2.xml");
    dstu2.convert(src + "profiles-others.xml", dst + "profiles-others-r2.xml");
    dstu2.convert(src + "extension-definitions.xml", dst + "extension-definitions-r2.xml");
    dstu2.convert(src + "search-parameters.xml", dst + "search-parameters-r2.xml");
    dstu2.convert(src + "valuesets.xml", dst + "valuesets-r2.xml");
    dstu2.convert(src + "v2-tables.xml", dst + "v2-tables-r2.xml");
    dstu2.convert(src + "v3-codesystems.xml", dst + "v3-codesystems-r2.xml");
    dstu2.convert(src + "conceptmaps.xml", dst + "conceptmaps-r2.xml");
    dstu2.convert(src + "dataelements.xml", dst + "dataelements-r2.xml");
  }

  @Override
  public Resource convert(org.hl7.fhir.dstu3.model.Resource resource) throws FHIRException {
    if (resource instanceof ValueSet) {
      ValueSet vs = (ValueSet) resource;
      if (vs.hasCompose() && vs.getCompose().getExclude().isEmpty() && vs.getCompose().getInclude().size() == 1 && !vs.getCompose().getInclude().get(0).hasValueSet()) {
        String url = vs.getCompose().getInclude().get(0).getSystem();
        CodeSystem cs = findCodeSystem(url);
        if (cs != null) {
          org.hl7.fhir.dstu2.model.ValueSet vst = vc.convertValueSet(vs);
          vst.setCompose(null);
          vst.setCodeSystem(vc.convertCodeSystem(cs));
          return vst;
        }
      }
    }   
    return null;
  }


  private CodeSystem findCodeSystem(String url) {
    for (BundleEntryComponent b : source.getEntry()) {
      if (b.getResource() instanceof CodeSystem) {
        CodeSystem cs = (CodeSystem) b.getResource();
        if (cs.getUrl().equals(url))
          return cs;
      }
    }
    return null;
  }

  @Override
  public void handleCodeSystem(CodeSystem tgtcs, ValueSet vs) {
   throw new Error("not done yet");
    
  }

  @Override
  public CodeSystem getCodeSystem(ValueSet src) {
    return null;
  }


}
