package org.hl7.fhir.instance.test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Bundle;
import org.hl7.fhir.instance.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.instance.model.DataElement;
import org.hl7.fhir.instance.model.DecimalType;
import org.hl7.fhir.instance.model.DiagnosticReport;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Observation;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.formats.IParser.OutputStyle;

public class ResourceModifier {

  public static void main(String[] args) throws FileNotFoundException, Exception {
//   process("C:\\work\\org.hl7.fhir\\build\\source\\documentreference\\valueset-clinical-speciality.xml");
    System.out.println("");
    System.out.println("Finished");
  }

  private static void process(String filename) throws Exception {
    System.out.println("process "+filename);
    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    InputStream si = new FileInputStream(filename);
    Resource r = xml.parse(si);
    si.close();
    
    OutputStream so = new FileOutputStream(filename);
    xml.compose(so, r);
    so.close();
  }

}
