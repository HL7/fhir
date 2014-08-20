package org.hl7.fhir.utilities.tests;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.XLSXmlGenerator;

public class XLSXmlGeneratorTests {

  /**
   * @param args
   * @throws Exception 
   */
  public static void main(String[] args) throws Exception {
    XLSXmlGenerator xls = new XLSXmlGenerator("c:\\temp\\testxls.xml", "FHIR Project", "2012-03-19T11:12:07Z");
    xls.addSimpleSheet("Test Sheet", makeTestSheet());
    xls.finish();

  }

  private static List<List<String>> makeTestSheet() {
    List<List<String>> res = new ArrayList<List<String>>();
    List<String> row = new ArrayList<String>();
    res.add(row);
    row.add("H1");
    row.add("H2");
    row.add("H3");
    row = new ArrayList<String>();
    res.add(row);
    row.add("c1");
    row.add("");
    row.add("c3asdasd ");
    return res;
  }

}
