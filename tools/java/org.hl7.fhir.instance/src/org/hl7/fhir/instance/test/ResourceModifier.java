package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.DiagnosticReport;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;

public class ResourceModifier {

  public static void main(String[] args) throws FileNotFoundException, Exception {
    // TODO Auto-generated method stub
//
    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    InputStream si = new FileInputStream("C:\\work\\org.hl7.fhir\\build\\source\\diagnosticreport\\diagnosticreport-example-dxa.xml");
    Resource r = xml.parse(si);
    si.close();
    
//    Bundle b = (Bundle) r;
//    for (BundleEntryComponent e : b.getEntry()) {
//      DiagnosticReport dr = (DiagnosticReport) e.getResource();
    DiagnosticReport dr = (DiagnosticReport) r;
      for (Resource c : dr.getContained()) {
        Observation o = (Observation) c;
        o.setSubject(dr.getSubject());
        o.addPerformer(dr.getPerformer());
      }
//    }
    OutputStream so = new FileOutputStream("C:\\work\\org.hl7.fhir\\build\\source\\diagnosticreport\\diagnosticreport-example-dxa.xml");
    xml.compose(so, r);
    so.close();
  }

}
