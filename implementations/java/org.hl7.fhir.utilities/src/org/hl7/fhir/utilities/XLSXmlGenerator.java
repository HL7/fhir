package org.hl7.fhir.utilities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.utilities.xml.XMLWriter;

public class XLSXmlGenerator {

  private XMLWriter source;

  public class SimpleSheet extends ArrayList<List<String>> {
    public void addRow(String... args) {
      ArrayList<String> row = new ArrayList<String>();
      for (String s : args)
        row.add(s);
      add(row);
    }
  }
  public XLSXmlGenerator(String filename, String author, String genDate) throws Exception {
    super();
    this.source = new XMLWriter(new FileOutputStream(filename), "UTF-8");
    start(author, genDate);
  }

  private void start(String author, String genDate) throws IOException {
    source.setPretty(true);
    source.setPrettyHeader(true);
    source.setXmlHeader(true);
    source.start();
    source.processingInstruction("mso-application progid=\"Excel.Sheet\"");
    source.setDefaultNamespace("urn:schemas-microsoft-com:office:spreadsheet");
    source.namespace("urn:schemas-microsoft-com:office:office", "o");
    source.namespace("urn:schemas-microsoft-com:office:excel", "x");
    source.namespace("urn:schemas-microsoft-com:office:spreadsheet", "ss");
    source.namespace("http://www.w3.org/TR/REC-html40", "html");
    source.open("urn:schemas-microsoft-com:office:spreadsheet", "Workbook");
    source.open("urn:schemas-microsoft-com:office:office", "DocumentProperties");
    source.element("urn:schemas-microsoft-com:office:office", "Author", author);
    source.element("urn:schemas-microsoft-com:office:office", "Created", genDate);
    source.close();
  }
  
  public void addSimpleSheet(String name, List<List<String>> cells) throws IOException {
    source.attribute("ss:Name", name);
    
    source.open("Worksheet");
    source.open("Table");
    for (List<String> row : cells) {
      source.open("Row");
      for (String s : row) {
        source.open("Cell");
        source.attribute("ss:Type", "String");
        source.element("Data", s);
        source.close("Cell");
      }
      source.close("Row");      
    }
    source.close("Table");
    source.close("Worksheet");
  }
  
  public void finish() throws IOException {
   source.close();
   source.close();
  }
  

}
