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

    XmlParser xml = new XmlParser();
    xml.setOutputStyle(OutputStyle.PRETTY);
    InputStream si = new FileInputStream("C:\\work\\org.hl7.fhir\\build\\guides\\hspc\\hspc-QuantitativeLab-dataelements.xml");
    Resource r = xml.parse(si);
    si.close();
    
    Bundle b = (Bundle) r;
    for (BundleEntryComponent e : b.getEntry()) {
      DataElement de = (DataElement) e.getResource();
      Extension ext = ToolingExtensions.getExtension(de, "http://hl7.org/fhir/StructureDefinition/minValue");
      if (ext != null) {
        DecimalType dec = (DecimalType) ext.getValue();
        de.getElement().get(0).setMinValue(dec);
        ToolingExtensions.removeExtension(de, "http://hl7.org/fhir/StructureDefinition/minValue");
      }
      ext = ToolingExtensions.getExtension(de, "http://hl7.org/fhir/StructureDefinition/maxValue");
      if (ext != null) {
        DecimalType dec = (DecimalType) ext.getValue();
        de.getElement().get(0).setMaxValue(dec);
        ToolingExtensions.removeExtension(de, "http://hl7.org/fhir/StructureDefinition/maxValue");
      }
    }
    
    OutputStream so = new FileOutputStream("C:\\work\\org.hl7.fhir\\build\\guides\\hspc\\hspc-QuantitativeLab-dataelements2.xml");
    xml.compose(so, r);
    so.close();
  }

}
