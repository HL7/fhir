package org.hl7.fhir.definitions.parsers.converters;

import org.hl7.fhir.instance.formats.IParser.OutputStyle;
import org.hl7.fhir.instance.formats.XmlParser;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.utilities.CSVReader;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;

public class DICOMConverter {

  // to prepare the source:
  // get http://medical.nema.org/medical/dicom/current/output/chtml/part16/chapter_D.html in Chrome
  // view the source 
  // copy and paste the sousce to a text file (don't just save as source)
  //  delete the content before and after the table-contents, and add a date attribute 
  // get the date from here: http://medical.nema.org/medical/dicom/current/output/chtml
  
  private static final String SRC_LOCAL = "C:\\work\\org.hl7.fhir\\build\\source\\dicom\\partD.html";
  private static final String SRC = "http://medical.nema.org/medical/dicom/current/output/chtml/part16/chapter_D.html";
  private static final String DST = "C:\\work\\org.hl7.fhir\\build\\source\\valueset\\valueset-dicom-dcim.xml";
  
  public static void main(String[] args) throws Exception {
    Map<String, String> sctTrans = new HashMap<String, String>();
    loadSCTTrans(sctTrans);
    
    // converts from DICOM generated HTML to value set.
    ValueSet vs = new ValueSet();
    vs.setId("valueset-dicom-dcim");
    vs.setUrl("http://nema.org/dicom/vs/dicm");
    vs.setVersion("01");
    vs.setName("DICOM Controlled Terminology Definitions");
    vs.setPublisher("NEMA/DICOM");
    vs.addContact().addTelecom().setSystem(ContactPointSystem.URL).setValue(SRC);
    vs.setDescription("DICOM Code Definitions (Coding Scheme Designator \"DCM\" Coding Scheme Version \"01\")");
    vs.setRequirements("This value is published as part of FHIR in order to make the codes available to FHIR terminology services and so implementers can easily leverage the codes");
    vs.setCopyright("These codes are excerpted from Digital Imaging and Communications in Medicine (DICOM) Standard, Part 16: Content Mapping Resource, Copyright 2011 by the National Electrical Manufacturers Association");
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.getDefine().setSystem("http://nema.org/dicom/dicm").setCaseSensitive(true);
    System.out.println("parse XML");
    FileInputStream instream = new FileInputStream(SRC_LOCAL);

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(instream);
    System.out.println("process");
    
    vs.setDateElement(new DateTimeType(xdoc.getDocumentElement().getAttribute("date")));
    Element tb = XMLUtil.getNamedChild(xdoc.getDocumentElement(), "table");
    tb = XMLUtil.getNamedChild(tb, "tbody");
    Element tr = XMLUtil.getFirstChild(tb);
    while (tr != null) {
      Element td = XMLUtil.getFirstChild(tr);
      String code = Utilities.normalizeSameCase(td.getTextContent().trim());
      td = XMLUtil.getNextSibling(td);
      String display = Utilities.normalizeSameCase(td.getTextContent().trim());
      td = XMLUtil.getNextSibling(td);
      String definition = Utilities.normalizeSameCase(td.getTextContent().trim());
      if (definition != null && definition.endsWith("."))
        definition = definition.substring(0, definition.length()-1);
      td = XMLUtil.getNextSibling(td);
      ConceptDefinitionComponent cc = vs.getDefine().addConcept();
      cc.setCode(code).setDisplay(display).setDefinition(definition);

      String comments = Utilities.normalizeSameCase(td.getTextContent().trim());
      if (!Utilities.noString(comments)) {
        if (comments.toLowerCase().contains("retired")) {
          ToolingExtensions.setDeprecated(cc);
          if (comments.toLowerCase().contains("replaced by")) {
            int is = comments.indexOf("(");
            int ie = comments.indexOf(")");
            if (ie > 1 && is > 1 && ie > is) {
              String parts[] = parseLine(comments.substring(is+1, ie));
              if (parts.length == 3) {
                if (parts[1].equals("UMLS")) {
                  // ignore these
                } else {
                  Coding c;
                  if (parts[1].equals("SRT") && sctTrans.containsKey(parts[0]))
                    c = new Coding().setCode(sctTrans.get(parts[0])).setSystem(getSystem(parts[1])).setDisplay(parts[2]);
                  else
                    c = new Coding().setCode(parts[0]).setSystem(getSystem(parts[1])).setDisplay(parts[2]);
                  ToolingExtensions.setExtension(cc, ToolingExtensions.EXT_REPLACED_BY, c);
                }
              }
              else 
                throw new Exception("Unable to parse "+comments);
            } else
              throw new Exception("Unable to parse "+comments);
          }
        }  else {
          ToolingExtensions.addComment(cc, comments);
        }
      }
      tr = XMLUtil.getNextSibling(tr);
    }
    System.out.println("save");
    new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(DST), vs);
    
    System.out.println("done");
  }

  private static void loadSCTTrans(Map<String, String> sctTrans) throws Exception {
    System.out.println("load SCT Translations");
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document xdoc = builder.parse(new FileInputStream("C:\\work\\org.hl7.fhir\\build\\source\\dicom\\sct-trans.xml"));
    Element e = XMLUtil.getFirstChild(xdoc.getDocumentElement());
    while (e != null) {
      String ss = XMLUtil.getNamedChild(e, "SnomedId").getTextContent();
      String sd = XMLUtil.getNamedChild(e, "ConceptId").getTextContent();
      sctTrans.put(ss,  sd);
      e = XMLUtil.getNextSibling(xdoc.getDocumentElement());
    }
  }

  private static String getSystem(String sys) {
    if (sys.equals("DCM")) {
      return "http://nema.org/dicom/dicm";
    } else if (sys.equals("SRT")) {
      return "http://snomed.info/sct";
    } else if (sys.equals("LN")) {
      return "http://loinc.org";
    } else {
      throw new Error("unknown: "+sys);
    }
  }

  private static String[] parseLine(String s) {
    List<String> res = new ArrayList<String>();
    StringBuilder b = new StringBuilder();
    boolean inQuote = false;

    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c == '"') 
        inQuote = !inQuote;
      else if (!inQuote && c == ',') {
        res.add(b.toString().trim());
        b = new StringBuilder();
      }
      else 
        b.append(c);
    }
    res.add(b.toString().trim());
    String[] r = new String[] {};
    r = res.toArray(r);
    return r;
  }

  private static Date getLastModifiedDate(HttpResponse response) throws DateParseException {
    Header header = response.getFirstHeader("Date");
    if (header != null) {
        return DateUtils.parseDate( header.getValue());
    }
    return null;
}
}
