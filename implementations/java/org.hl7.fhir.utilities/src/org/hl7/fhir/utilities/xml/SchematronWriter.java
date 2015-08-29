package org.hl7.fhir.utilities.xml;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;


import org.hl7.fhir.utilities.TextStreamWriter;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.SchematronWriter.Section;


public class SchematronWriter  extends TextStreamWriter  {

  public class Assert {
    private String test;
    private String message; 
  }
  
  public class Rule {
    private String name; 
    private List<Assert> asserts = new ArrayList<Assert>();   
    public void assrt(String test, String message) {
      Assert a = new Assert();
      a.test = test;
      a.message = message;
      asserts.add(a);
    }
  }
  public class Section {
    private String title;
    private List<Rule> rules = new ArrayList<Rule>();
    
    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public Rule rule(String name) {
      for (Rule r : rules) {
        if (r.name.equals(name))
          return r;
      }
      Rule r = new Rule();
      r.name = name;
      rules.add(r);
      return r;
    }
  }
  
  private List<Section> sections = new ArrayList<Section>();


  public SchematronWriter(OutputStream out) throws UnsupportedEncodingException {
    super(out);
  }

  public Section addSection(String title) {
    Section s = new Section();
    s.title = title;
    sections.add(s);
    return s;
  }
  
  public void dump(String rn) throws IOException {
    ln("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    ln_i("<sch:schema xmlns:sch=\"http://purl.oclc.org/dsdl/schematron\" queryBinding=\"xslt2\">");
    ln("<sch:ns prefix=\"f\" uri=\"http://hl7.org/fhir\"/>");
    ln("<sch:ns prefix=\"h\" uri=\"http://www.w3.org/1999/xhtml\"/>");
    if (rn != null) {
      ln("<!-- ");
      ln("  This file contains just the constraints for the resource "+rn);
      ln("  It is provided for documentation purposes. When actually validating,");
      ln("  always use fhir-invariants.sch (because of the way containment works)");
      ln("  Alternatively you can use this file to build a smaller version of");
      ln("  fhir-invariants.sch (the contents are identical; only include those ");
      ln("  resources relevant to your implementation).");
      ln("-->");
    }

    for (Section s : sections) {
      ln_i("<sch:pattern>");
      ln("<sch:title>"+s.title+"</sch:title>");
      for (Rule r : s.rules) {
        ln_i("<sch:rule context=\""+r.name+"\">");
        for (Assert a : r.asserts) 
          ln("<sch:assert test=\""+Utilities.escapeXml(a.test)+"\">"+Utilities.escapeXml(a.message)+"</sch:assert>");
        ln_o("</sch:rule>");
      }
      ln_o("</sch:pattern>");
    }  
    ln_o("</sch:schema>");
    flush();
    close();
  }
  
}
