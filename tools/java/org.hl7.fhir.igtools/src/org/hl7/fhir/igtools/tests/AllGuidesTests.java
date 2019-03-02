package org.hl7.fhir.igtools.tests;

import org.hl7.fhir.igtools.publisher.Publisher;
import org.hl7.fhir.igtools.publisher.Publisher.CacheOption;
import org.hl7.fhir.r5.test.utils.TestingUtilities;
import org.hl7.fhir.utilities.Utilities;
import org.junit.Test;

public class AllGuidesTests {

  private void test(String path) throws Exception {
    System.out.println("=======================================================================================");
    System.out.println("Publish IG "+path);
    Publisher pub = new Publisher();
    pub.setConfigFile(path);
    pub.setTxServer("http://tx.fhir.org");
    pub.setCacheOption(CacheOption.LEAVE);
    pub.execute();
    System.out.println("=======================================================================================");
    System.out.println("");
  }

  @Test
  public void testIg40() throws Exception {
    test(Utilities.path(TestingUtilities.home(), "tests", "ig", "ig.json"));
  } 

  @Test
  public void testIg30() throws Exception {
    test(Utilities.path(TestingUtilities.home(), "tests", "ig30", "ig.json"));
  }
  
  @Test
  public void testIg14() throws Exception {
    test(Utilities.path(TestingUtilities.home(), "tests", "ig14", "ig.json"));
  }
  
  @Test
  public void testIg10() throws Exception {
    test(Utilities.path(TestingUtilities.home(), "tests", "ig10", "ig.json"));
  } 
}
