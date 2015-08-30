package org.hl7.fhir.tools.publisher;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.validation.ValueSetValidator;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.DateTimeType;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetCodeSystemComponent;
import org.hl7.fhir.instance.terminologies.ValueSetUtilities;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.tools.publisher.ValueSetImporterV2.TableRegistration;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValueSetImporterV2 {

  public class TableRegistration {
    private List<String> versionPoints = new ArrayList<String>(); 
  }

  private class OIDEntry {
    private String id;
    private String name;
    private String oid;
    private String code;
    private String ver;
    public OIDEntry(String id, String name, String oid, String code, String ver) {
      super();
      this.id = id;
      this.name = name;
      this.oid = oid;
      this.code = code;
      this.ver = ver;
    }
    public String getId() {
      return id;
    }
    public String getName() {
      return name;
    }
    public String getOid() {
      return oid;
    }
    public String getCode() {
      return code;
    }
    
  }
  private static final String HTTP_separator = "/";
  private List<ValidationMessage> errors; 
  private PageProcessor page;
  private List<ValueSet> valuesets = new ArrayList<ValueSet>();
  private Map<String, OIDEntry> oids = new HashMap<String, OIDEntry>();
  private Map<String, TableRegistration> tables = new HashMap<String, TableRegistration>();
  
  public ValueSetImporterV2(PageProcessor page, List<ValidationMessage> errors) {
    super();
    this.page = page;
    this.errors = errors;
  }

  public void execute() throws Exception {
    loadOIds();
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
  
  private void loadOIds() throws IOException {
    oids.clear();
    valuesets.clear();
    tables.clear();
    
    FileInputStream fis = new FileInputStream(Utilities.path(page.getFolders().srcDir, "v2", "v2oids.txt"));
    BufferedReader br = new BufferedReader(new InputStreamReader(fis));
    String line = null;
    while ((line = br.readLine()) != null) {
      if (!line.startsWith("//")) {
        String[] cells = line.split("\\t");
        String id = Utilities.padLeft(cells[0], '0', 4) + (cells.length == 5 ? "-"+cells[4] : "");
        oids.put(id, new OIDEntry(cells[0], cells[1], cells[2], cells[3], cells.length > 4 ? cells[4] : null));
        if (cells.length > 4) {
          if (!tables.containsKey(Utilities.padLeft(cells[0], '0', 4))) {
            TableRegistration tr = new TableRegistration();
            tables.put(Utilities.padLeft(cells[0], '0', 4), tr);
          }
          tables.get(Utilities.padLeft(cells[0], '0', 4)).versionPoints.add(cells[4]);
        } else 
          tables.put(id, new TableRegistration());
      }
    }
    br.close();    
  }

  private void updateNarrative(ValueSet vs) {
    XhtmlNode table = vs.getText().getDiv().getElement("table");
    List<String> langs = new ArrayList<String>(); 
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
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
    for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
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
      for (ConceptDefinitionComponent c : vs.getCodeSystem().getConcept()) {
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
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      if (tables.containsKey(id)) {
        if (tables.get(id).versionPoints.isEmpty()) {
          ValueSet vs = buildV2Valueset(id, e);
          valuesets.add(vs);
          vs.setId("v2-"+FormatUtilities.makeId(id));
          vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + "index.html");
          page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
          page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
          page.getValueSets().put(vs.getUrl(), vs);
          page.getCodeSystems().put(vs.getCodeSystem().getSystem().toString(), vs);
        } else { // versioned
          TableRegistration tbl = tables.get(id);
          for (int i = 0; i < tbl.versionPoints.size(); i++) {
            String ver = tbl.versionPoints.get(i);
            String nver = i < tbl.versionPoints.size() - 1 ? tbl.versionPoints.get(i+1) : null;
            ValueSet vs = buildV2ValuesetVersioned(id, ver, nver, e);
            valuesets.add(vs);
            vs.setId("v2-"+FormatUtilities.makeId(ver)+"-"+id);
            vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + ver + HTTP_separator + "index.html");
            page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
            page.getDefinitions().getCodeSystems().put(vs.getCodeSystem().getSystem(), vs);
            page.getValueSets().put(vs.getUrl(), vs);
            page.getCodeSystems().put(vs.getCodeSystem().getSystem().toString(), vs);
          }
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
    
  }

  private ValueSet buildV2Valueset(String id, Element e) throws Exception {
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setId("v2-"+FormatUtilities.makeId(id));
    vs.setUserData("filename", Utilities.path("v2", id, "index.html"));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName("v2 table " + id);
    vs.setPublisher("HL7, Inc");
    vs.setVersion("2.7");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://hl7.org"));
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setExperimental(true);
    vs.setDateElement(new DateTimeType("2011-01-28")); // v2.7 version
    ValueSetCodeSystemComponent def = new ValueSet.ValueSetCodeSystemComponent();
    vs.setCodeSystem(def);
    OIDEntry oe = oids.get(id);
    if (oe != null)
      ToolingExtensions.setOID(def, "urn:oid:"+oe.getOid());
    def.setCaseSensitive(false);
    def.setSystem("http://hl7.org/fhir/v2/" + id);
    StringBuilder s = new StringBuilder();

    String desc = "";
    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    while (c != null) {
      String ver = c.getAttribute("version");
      if (!ver.contains(" ")) {
        desc = c.getAttribute("desc");
        vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ( " + desc + ")");
        vs.setName("v2 " + desc);

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
        String ver = c.getAttribute("version");
        if (!ver.contains(" ")) {
          Element g = XMLUtil.getFirstChild(c);
          while (g != null) {
            if (cd.equals(g.getAttribute("code"))) {
              if (min == null)
                min = ver;
              max = ver;
            }
            g = XMLUtil.getNextSibling(g);
          }
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = ("2.1".equals(min) ? "from v2.1" : "added v" + min) + ("2.8.2".equals(max) ? "" : ", removed after v" + max);
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
    page.getVsValidator().validate(errors, "v2 table "+id, vs, false, true);
    return vs;
  }

  private ValueSet buildV2ValuesetVersioned(String id, String version, String endVersion, Element e) throws Exception {
    StringBuilder s = new StringBuilder();

    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setId("v2-"+FormatUtilities.makeId(version)+"-"+id);
    vs.setUserData("filename", Utilities.path("v2", id, version, "index.html"));
    vs.setUrl("http://hl7.org/fhir/ValueSet/"+vs.getId());
    vs.setName("v2 table " + id + ", Version " + version);
    vs.setPublisher("HL7, Inc");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.OTHER, "http://hl7.org"));
    vs.setStatus(ConformanceResourceStatus.ACTIVE);
    vs.setExperimental(false);
    vs.setVersion(id);
    vs.setDateElement(new DateTimeType("2011-01-28")); // v2.7 version
    ValueSetCodeSystemComponent def = new ValueSet.ValueSetCodeSystemComponent();
    OIDEntry oe = oids.get(id+"-"+version);
    if (oe != null)
      ToolingExtensions.setOID(def, "urn:oid:"+oe.getOid());
    vs.setCodeSystem(def);
    def.setCaseSensitive(true);
    def.setSystem("http://hl7.org/fhir/v2/" + id + "/" + version);

    String desc = "";
    String minlim = null;
    String maxlim = null;

    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    boolean started = false;
    while (c != null) {
      String ver = c.getAttribute("version");
      if (!ver.contains(" ")) {
        if ((!started && ver.equals(version)) || (started && !ver.equals(endVersion))) {
          started = true;
          if (minlim == null)
            minlim = ver;
          maxlim = ver;
          desc = c.getAttribute("desc");
          vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ver " + ver + " ( " + desc + ")");
          Element g = XMLUtil.getFirstChild(c);
          while (g != null) {
            codes.put(g.getAttribute("code"), g.getAttribute("desc"));
            g = XMLUtil.getNextSibling(g);
          }
        } else if (started)
          started = false;
      }
      c = XMLUtil.getNextSibling(c);
    }

    s.append("<p>").append(Utilities.escapeXml(desc)).append("</p>\r\n");
    s.append("<table class=\"grid\">");
    s.append("<tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Version</b></td></tr>");
    List<String> cs = new ArrayList<String>();
    cs.addAll(codes.keySet());
    Collections.sort(cs);
    started = false;
    for (String cd : cs) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      started = false;
      while (c != null) {
        String ver = c.getAttribute("version");
        if (!ver.contains(" ")) {
          if ((!started && ver.equals(version)) || (started && !ver.equals(endVersion))) {
            started = true;
            Element g = XMLUtil.getFirstChild(c);
            while (g != null) {
              if (cd.equals(g.getAttribute("code"))) {
                if (min == null)
                  min = ver;
                max = ver;
              }
              g = XMLUtil.getNextSibling(g);
            }
          } else if (started)
            started = false;
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
    page.getVsValidator().validate(errors, "v2 table "+id, vs, false, true);
    return vs;
  }

  public String getIndex(Document v2src) throws IOException {
    loadOIds();
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>URI</b></td><td><b>ID</b></td><td><b>Comments</b></td></tr>\r\n");
    Element e = XMLUtil.getFirstChild(v2src.getDocumentElement());
    while (e != null) {
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      if (tables.containsKey(id)) {
        String name = "";
        // we use the latest description of the table
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          name = c.getAttribute("desc");
          c = XMLUtil.getNextSibling(c);
        }
        if (!tables.get(id).versionPoints.isEmpty()) {
          s.append(" <tr><td>http://hl7.org/fhir/v2/").append(id).append("</td><td>").append(name).append("</td><td>").append("Version Dependent. Use one of:<ul>");
          for (String v : tables.get(id).versionPoints)
            s.append(" <li><a href=\"v2/").append(id).append("/").append(v).append("/index.html\">").append(v).append("+</a></li>");
          s.append("</ul></td></tr>\r\n");
        } else
          s.append(" <tr><td><a href=\"v2/").append(id).append("/index.html\">http://hl7.org/fhir/v2/").append(id).append("</a></td><td>").append(name).append("</td><td></td></tr>\r\n");
      }
      e = XMLUtil.getNextSibling(e);
    }
    s.append("</table>\r\n");
    return s.toString();
  }

  public void produce(SectionNumberer sects) throws Exception {
    loadOIds();
    Element e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      if (tables.containsKey(id)) {
        if (tables.get(id).versionPoints.isEmpty()) {
          String iid = id;
          while (iid.startsWith("0"))
            iid = iid.substring(1);
          Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl.html");
          ValueSet vs = page.getValueSets().get("http://hl7.org/fhir/ValueSet/v2-"+id);
          String sf = page.processPageIncludes(id + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + "index.html", vs, null, "V2 Table", null);
          sf = sects.addSectionNumbers("v2" + id + ".html", "template-v2", sf, iid, 2, null, null);
          TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + "index.html");
          page.getEpub().registerExternal("v2" + File.separator + id + File.separator + "index.html");
        } else {
          String iid = id;
          while (iid.startsWith("0"))
            iid = iid.substring(1);
          Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          int i = 0;
          for (String ver : tables.get(id).versionPoints) {
            if (!Utilities.noString(ver)) {
              i++;
              Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
              Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver);
              String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl-ver.html");
              ValueSet vs = page.getValueSets().get("http://hl7.org/fhir/ValueSet/v2-"+FormatUtilities.makeId(ver)+"-"+id);
              String sf = page.processPageIncludes(id + "|" + ver + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + ver + File.separator + "index.html", vs, null, "V2 Table", null);
              sf = sects.addSectionNumbers("v2" + id + "." + ver + ".html", "template-v2", sf, iid + "." + Integer.toString(i), 3, null, null);
              TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
              page.getEpub().registerExternal("v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
            }
          }
        }
      }
      e = XMLUtil.getNextSibling(e);
    }

    
  }


}
