package org.hl7.fhir.validation.r4.tests;

import java.io.IOException;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r4.validation.NativeHostServices;
import org.hl7.fhir.utilities.TextFile;

public class NativeHostServiceTester {

  public static void main(String[] args) throws Exception {
    System.out.println("starting...");
    
    NativeHostServices svc = new NativeHostServices();
    svc.init("C:\\work\\org.hl7.fhir\\build\\publish\\igpack.zip");
    svc.connectToTxSvc("http://tx.fhir.org/r3");
    System.out.println("base: "+svc.status());

    svc.seeResource(TextFile.fileToBytes("C:\\work\\fhirserver\\resources\\dicom\\CID_2.xml"));
    System.out.println("added: "+svc.status());
    
    svc.dropResource("ValueSet", "dicm-2-AnatomicModifier", "http://dicom.nema.org/medical/dicom/current/output/chtml/part16/sect_CID_2.html", "20160314");
    System.out.println("removed: "+svc.status());

    System.out.println("validate:");
    byte[] res = svc.validateResource("my-loc", TextFile.fileToBytes("C:\\work\\fhirserver\\resources\\r3\\patient-group.xml"), "XML");
    System.out.println(new String(res));
    
    System.out.println("convert:");
    byte[] r4 = svc.convertResource(TextFile.fileToBytes("C:\\work\\org.hl7.fhir.old\\org.hl7.fhir.dstu2\\build\\publish\\patient-example.xml"), "xml", "r2");
    System.out.println(new String(r4));    
    
    System.out.println("unconvert:");
    byte[] r2 = svc.convertResource(TextFile.fileToBytes("C:\\work\\org.hl7.fhir\\build\\publish\\patient-example.xml"), "xml", "r2");
    System.out.println(new String(r2));    
    
    
    System.out.println("done");
  }

}
