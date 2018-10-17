package org.hl7.fhir.tools.converters;

import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Bundle;

public class DSTU3ValidationConvertor {

  private Bundle source;
  private VersionConvertor_30_40 vc;
  
  public void convert(String bundleSource, String bundleTarget) throws Exception {
    System.out.println("Convert "+bundleSource);
    
    try {
      source = (Bundle) new XmlParser().parse(new FileInputStream(bundleSource));
      org.hl7.fhir.dstu3.model.Bundle target = VersionConvertor_30_40.convertBundle(source);
      new org.hl7.fhir.dstu3.formats.XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(bundleTarget), target);     
    } catch (Exception e) {
      throw new Exception(e);
    } 
  }
  
  public void convertJ(String bundleSource, String bundleTarget) throws Exception {
    System.out.println("Convert "+bundleSource);
    
    try {
      source = (Bundle) new XmlParser().parse(new FileInputStream(bundleSource));
      org.hl7.fhir.dstu3.model.Bundle target = VersionConvertor_30_40.convertBundle(source);
      new org.hl7.fhir.dstu3.formats.JsonParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(bundleTarget), target);     
    } catch (Exception e) {
      throw new Exception(e);
    } 
  }
}
