package org.hl7.fhir.utg.fhir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.CodeSystem;

public class CodeSystemRetoucher {

  public static void main(String[] args) throws FHIRFormatError, FileNotFoundException, IOException {
    for (File f : new File("C:\\work\\org.hl7.fhir.intl\\vocab-poc\\fhir\\codeSystems").listFiles()) {
      System.out.println("fix : "+f.getAbsolutePath());
      CodeSystem cs = (CodeSystem) new XmlParser().parse(new FileInputStream(f));
      cs.setUrl("http://hl7.org/fhir/ig/vocab-poc/CodeSystem/"+cs.getId());
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(f), cs);
    }

  }

}
