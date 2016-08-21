package org.hl7.fhir.convertors;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;

public class IGPackConverter140 {
  
  public static void main(String[] args) throws Exception {
    CKMImporter self = new CKMImporter();
    for (String s : new File("C:\\temp\\igpack").list()) {
//      if (s.endsWith(".xml") && !s.contains("z-")) {
      if (s.equals("expansions.xml")) {
        System.out.println("process "+s);
        org.hl7.fhir.dstu2016may.formats.XmlParser xp = new org.hl7.fhir.dstu2016may.formats.XmlParser();
        org.hl7.fhir.dstu2016may.model.Resource r14 = xp.parse(new FileInputStream("C:\\temp\\igpack\\"+s));
        org.hl7.fhir.dstu3.model.Resource r17 = VersionConvertor_14_20.convertResource(r14);
        org.hl7.fhir.dstu3.formats.XmlParser xc = new org.hl7.fhir.dstu3.formats.XmlParser();
        xc.setOutputStyle(OutputStyle.PRETTY);
        xc.compose(new FileOutputStream("C:\\temp\\igpack\\z-"+s), r17);
      }
    }
    System.out.println("done");
  }

}
