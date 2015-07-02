package org.hl7.fhir.instance.test;

import java.io.IOException;

import org.hl7.fhir.utilities.Utilities;

public class UtilitiesTests {

  public static void main(String[] args) throws IOException {
    System.out.println("Same: "+Utilities.compareIgnoreWhitespace("C:\\temp\\union-1.xml", "C:\\temp\\union-1a.xml"));
  }

}
