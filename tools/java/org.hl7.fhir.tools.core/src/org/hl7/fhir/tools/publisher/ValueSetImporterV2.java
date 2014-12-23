package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.DateType;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineComponent;
import org.hl7.fhir.instance.model.ValueSet.ValuesetStatus;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class ValueSetImporterV2 {
  private static final String HTTP_separator = "/";

  private PageProcessor page;
  private List<ValueSet> valuesets = new ArrayList<ValueSet>();
  
  public ValueSetImporterV2(PageProcessor page) {
    super();
    this.page = page;
  }

  public void execute() throws Exception {
    executeMaster();
    // now, load additional language sources
    IniFile ini = new IniFile(Utilities.path(page.getFolders().srcDir, "v2", "v2.ini"));
    int count = ini.getIntegerProperty("v2", "langcount");
    for (int  i = 1; i <= count; i++) {
      loadLanguagePack(ini.getStringProperty("v2", "lang"+Integer.toString(i)));
    }
    for (ValueSet vs : valuesets) 
      updateNarrative(vs);
  }
  
  private void updateNarrative(ValueSet vs) {
    XhtmlNode table = vs.getText().getDiv().getElement("table");
    List<String> langs = new ArrayList<String>(); 
    for (ConceptDefinitionComponent c : vs.getDefine().getConcept()) {
      for (ConceptDefinitionDesignationComponent d : c.getDesignation()) {
        if (d.hasLanguage() && !d.hasUse()) {
          if (!langs.contains(d.getLanguage()))
            langs.add(d.getLanguage());
        }
      }
    }
    Collections.sort(langs);
    XhtmlNode tr = table.getChildNodes().get(0);
    int i = 2;
    for (String s : langs) {
      tr.addTag(i, "td").addTag("b").addText(page.getTranslations().getLangDesc(s));
      i++;
    }
    int r = 1;
    for (ConceptDefinitionComponent c : vs.getDefine().getConcept()) {
      tr = table.getChildNodes().get(r);
      i = 2;
      for (String s : langs) {
        tr.addTag(i, "td").addText(getLang(c, s));
        i++;
      }
      r++;
    }
  }

  private String getLang(ConceptDefinitionComponent c, String s) {
    for (ConceptDefinitionDesignationComponent d : c.getDesignation()) {
      if (d.hasLanguage() && !d.hasUse()) {
        if (d.getLanguage().equals(s))
          return d.getValue();
      }
    }
    return "";
  }

  private void loadLanguagePack(String source) throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    Document doc = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v2" + File.separator + source)));
    Map<String, Element> tblindex = new HashMap<String, Element>();
    
    // index the table list
    Element e = XMLUtil.getFirstChild(doc.getDocumentElement());
    while (e != null) {
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      tblindex.put(id,  e);
      e = XMLUtil.getNextSibling(e);
    }

    for (ValueSet vs : valuesets) {
      String id = vs.getId().substring(3);
      if (id.contains("-")) {
        // version specific
      } else {
        processV2Table(vs, tblindex.get(id));
      }
    }
    
  }

  private void processV2Table(ValueSet vs, Element element) {
    if (element != null) {
      for (ConceptDefinitionComponent c : vs.getDefine().getConcept()) {
        processV2Code(c, element);
      }
    }
    for (Element e : getLangList(element)) {
      if (!ToolingExtensions.hasLanguageTranslation(vs.getNameElement(), e.getAttribute("lang")))
        ToolingExtensions.addLanguageTranslation(vs.getNameElement(), e.getAttribute("lang"), e.getAttribute("value"));
    }
  }
  
  private void processV2Code(ConceptDefinitionComponent c, Element element) {
    Element ver = XMLUtil.getLastChild(element);
    while (ver != null) {
       Element code = getChildForCode(ver, c.getCode());
       for (Element e : getLangList(code)) {
         if (!hasLangDesc(c, e.getAttribute("lang")))
           addLangDesc(c, e.getAttribute("lang"), e.getAttribute("value"));
       }
       ver = XMLUtil.getPrevSibling(ver);
    }    
  }

  private boolean hasLangDesc(ConceptDefinitionComponent c, String lang) {
    for (ConceptDefinitionDesignationComponent cd : c.getDesignation()) {
      if (cd.getLanguage().equals(lang))
        return true;
    }
    return false;
  }

  private void addLangDesc(ConceptDefinitionComponent c, String lang, String value) {
    c.addDesignation().setLanguage(lang).setValue(value);
  }

  private List<Element> getLangList(Element node) {
    List<Element> results = new ArrayList<Element>();
    if (node != null) {
      Element e = XMLUtil.getFirstChild(node);
      while (e != null) {
        if (e.getNodeName().equals("desc") && e.hasAttribute("lang"))
          results.add(e);
        e = XMLUtil.getNextSibling(e);
      }
    }
    return results;
  }

  private Element getChildForCode(Element node, String code) {
    Element e = XMLUtil.getFirstChild(node);
    while (e != null) {
      if (e.getNodeName().equals("item") && e.getAttribute("code").equals(code))
        return e;
      e = XMLUtil.getNextSibling(e);
    }
    return null;
  }

  public void executeMaster() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV2src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v2" + File.separator + "source.xml"))));

    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String st = e.getAttribute("state");
      if ("include".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        ValueSet vs = buildV2Valueset(id, e);
        valuesets.add(vs);
        vs.setId("v2-"+FormatUtilities.makeId(id));
        vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + "index.html");
        page.getDefinitions().getValuesets().put(vs.getIdentifier(), vs);
        page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystem(), vs);
        page.getValueSets().put(vs.getIdentifier(), vs);
        page.getCodeSystems().put(vs.getDefine().getSystem().toString(), vs);
      } else if ("versioned".equals(st)) {
        String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
        List<String> versions = new ArrayList<String>();
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          if (XMLUtil.getFirstChild(c) != null && !versions.contains(c.getAttribute("namespace"))) {
            versions.add(c.getAttribute("namespace"));
          }
          c = XMLUtil.getNextSibling(c);
        }
        for (String ver : versions) {
          ValueSet vs = buildV2ValuesetVersioned(id, ver, e);
          valuesets.add(vs);
          vs.setId("v2-"+FormatUtilities.makeId(ver)+"-"+id);
          vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + ver + HTTP_separator + "index.html");
          page.getDefinitions().getValuesets().put(vs.getIdentifier(), vs);
          page.getDefinitions().getCodeSystems().put(vs.getDefine().getSystem(), vs);
          page.getValueSets().put(vs.getIdentifier(), vs);
          page.getCodeSystems().put(vs.getDefine().getSystem().toString(), vs);
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
    
  }

  private ValueSet buildV2Valueset(String id, Element e) throws Exception {
    ValueSet vs = new ValueSet();
    vs.setId("v2-"+FormatUtilities.makeId(id));
    vs.setUserData("filename", Utilities.path("v2", id, "index.html"));
    vs.setIdentifier("http://hl7.org/fhir/v2/vs/" + id);
    vs.setName("v2 table " + id);
    vs.setPublisher("HL7, Inc");
    vs.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(ValuesetStatus.ACTIVE);
    vs.setDateElement(new DateTimeType("2011-01-28")); // v2.7 version
    ValueSetDefineComponent def = new ValueSet.ValueSetDefineComponent();
    vs.setDefine(def);
    def.setCaseSensitive(true);
    def.setSystem("http://hl7.org/fhir/v2/" + id);
    StringBuilder s = new StringBuilder();

    String desc = "";
    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    while (c != null) {
      desc = c.getAttribute("desc");
      vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ( " + desc + ")");
      vs.setName("v2 " + desc);

      Element g = XMLUtil.getFirstChild(c);
      while (g != null) {
        codes.put(g.getAttribute("code"), g.getAttribute("desc"));
        g = XMLUtil.getNextSibling(g);
      }
      c = XMLUtil.getNextSibling(c);
    }
    s.append("<p>").append(Utilities.escapeXml(desc)).append("</p>\r\n");
    s.append("<table class=\"grid\">");
    s.append("<tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Version</b></td></tr>");
    List<String> cs = new ArrayList<String>();
    cs.addAll(codes.keySet());
    Collections.sort(cs);
    for (String cd : cs) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      while (c != null) {
        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          if (cd.equals(g.getAttribute("code"))) {
            if (min == null)
              min = c.getAttribute("version");
            max = c.getAttribute("version");
          }
          g = XMLUtil.getNextSibling(g);
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = ("2.1".equals(min) ? "from v2.1" : "added v" + min) + ("2.7".equals(max) ? "" : ", removed after v" + max);
      ConceptDefinitionComponent concept = new ValueSet.ConceptDefinitionComponent();
      concept.setCode(cd);
      concept.setDisplay(codes.get(cd)); // we deem the v2 description to
      // be display name, not
      // definition. Open for
      // consideration
      if (!("2.7".equals(max)))
        ToolingExtensions.markDeprecated(concept);
      def.getConcept().add(concept);
      String nm = Utilities.nmtokenize(cd);
      s.append("<tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(nm) + "\"> </a></td><td>" + Utilities.escapeXml(codes.get(cd))
          + "</td><td>" + ver + "</td></tr>");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.ADDITIONAL); // because we add
    // v2 versioning
    // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    new ValueSetValidator(page.getWorkerContext()).validate("v2 table "+id, vs, false, true);
    return vs;
  }

  private ValueSet buildV2ValuesetVersioned(String id, String version, Element e) throws Exception {
    StringBuilder s = new StringBuilder();

    ValueSet vs = new ValueSet();
    vs.setId("v2-"+FormatUtilities.makeId(version)+"-"+id);
    vs.setUserData("filename", Utilities.path("v2", id, version, "index.html"));
    vs.setIdentifier("http://hl7.org/fhir/v2/vs/" + id + "/" + version);
    vs.setName("v2 table " + id + ", Version " + version);
    vs.setPublisher("HL7, Inc");
    vs.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(ValuesetStatus.ACTIVE);
    vs.setDateElement(new DateTimeType("2011-01-28")); // v2.7 version
    ValueSetDefineComponent def = new ValueSet.ValueSetDefineComponent();
    vs.setDefine(def);
    def.setCaseSensitive(true);
    def.setSystem("http://hl7.org/fhir/v2/" + id + "/" + version);

    String desc = "";
    String minlim = null;
    String maxlim = null;

    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    while (c != null) {
      if (version.equals(c.getAttribute("namespace"))) {
        if (minlim == null)
          minlim = c.getAttribute("version");
        maxlim = c.getAttribute("version");
        desc = c.getAttribute("desc");
        vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ver " + version + " ( " + desc + ")");
        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          codes.put(g.getAttribute("code"), g.getAttribute("desc"));
          g = XMLUtil.getNextSibling(g);
        }
      }
      c = XMLUtil.getNextSibling(c);
    }

    s.append("<p>").append(Utilities.escapeXml(desc)).append("</p>\r\n");
    s.append("<table class=\"grid\">");
    s.append("<tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Version</b></td></tr>");
    List<String> cs = new ArrayList<String>();
    cs.addAll(codes.keySet());
    Collections.sort(cs);
    for (String cd : cs) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      while (c != null) {
        if (version.equals(c.getAttribute("namespace"))) {
          Element g = XMLUtil.getFirstChild(c);
          while (g != null) {
            if (cd.equals(g.getAttribute("code"))) {
              if (min == null)
                min = c.getAttribute("version");
              max = c.getAttribute("version");
            }
            g = XMLUtil.getNextSibling(g);
          }
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = (minlim.equals(min) ? "from v" + minlim : "added v" + min) + (maxlim.equals(max) ? "" : ", removed after v" + max);
      ConceptDefinitionComponent concept = new ValueSet.ConceptDefinitionComponent();
      concept.setCode(cd);
      concept.setDisplay(codes.get(cd)); // we deem the v2 description to
      // be display name, not
      // definition. Open for
      // consideration
      def.getConcept().add(concept);
      s.append("<tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(Utilities.nmtokenize(cd)) + "\"> </a></td><td>"
          + Utilities.escapeXml(codes.get(cd)) + "</td><td>" + ver + "</td></tr>");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.ADDITIONAL); // because we add
    // v2 versioning
    // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    new ValueSetValidator(page.getWorkerContext()).validate("v2 table "+id, vs, false, true);
    return vs;
  }


}
