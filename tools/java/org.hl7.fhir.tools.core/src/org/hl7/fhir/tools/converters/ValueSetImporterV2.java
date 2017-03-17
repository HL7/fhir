package org.hl7.fhir.tools.converters;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.dstu3.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.dstu3.model.DateTimeType;
import org.hl7.fhir.dstu3.model.Enumerations.PublicationStatus;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.Narrative;
import org.hl7.fhir.dstu3.model.Narrative.NarrativeStatus;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.dstu3.terminologies.CodeSystemUtilities;
import org.hl7.fhir.dstu3.terminologies.ValueSetUtilities;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.tools.publisher.SectionNumberer;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class ValueSetImporterV2 extends ValueSetImporterBase {

  public class VSPack {

    public ValueSet vs;
    public CodeSystem cs;

  }
  private static final String HTTP_separator = "/";
  private static final String MAX_VER = "2.8.2";

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
      this.oid = oid.trim();
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
  private List<ValidationMessage> errors; 
  private PageProcessor page;
  private List<VSPack> valuesets = new ArrayList<VSPack>();
  private Map<String, OIDEntry> oids = new HashMap<String, OIDEntry>();
  private Map<String, Set<String>> vsImports = new HashMap<String, Set<String>>();
  private Map<String, TableRegistration> tables = new HashMap<String, TableRegistration>();
  private String date;
  private IniFile ini;
  
  public ValueSetImporterV2(PageProcessor page, List<ValidationMessage> errors) throws IOException {
    super();
    this.page = page;
    this.errors = errors;
    ini = new IniFile(Utilities.path(page.getFolders().srcDir, "v2", "v2.ini"));
  }

  public void execute() throws Exception {
    loadOIds();
    executeMaster();
    // now, load additional language sources
    date = ini.getStringProperty("v2", "date");
    int count = ini.getIntegerProperty("v2", "langcount");
    for (int  i = 1; i <= count; i++) {
      loadLanguagePack(ini.getStringProperty("v2", "lang"+Integer.toString(i)));
    }
    for (VSPack vs : valuesets) 
      if (vs.cs != null)
        updateNarrative(vs.vs, vs.cs);
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
    
    for (String n : ini.getPropertyNames("valuesets")) {
      String v = ini.getStringProperty("valuesets", n);
      Set<String> cslist = new HashSet<String>();
      for (String p : v.split("\\;")) {
        if (p.length() == 4)
          cslist.add("http://hl7.org/fhir/v2/"+p);
        else
          cslist.add(p);
      }
      vsImports.put(n, cslist);
    }
  }

  private void updateNarrative(ValueSet vs, CodeSystem cs) {
    XhtmlNode table = vs.getText().getDiv().getElement("table");
    List<String> langs = new ArrayList<String>(); 
    for (ConceptDefinitionComponent c : cs.getConcept()) {
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
    for (ConceptDefinitionComponent c : cs.getConcept()) {
      tr = table.getChildNodes().get(r);
      i = 2;
      for (String s : langs) {
        tr.addTag(i, "td").addText(getLang(c, s));
        i++;
      }
      r++;
    }
    cs.setText(vs.getText());
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

    for (VSPack vs : valuesets) {
      String id = vs.vs.getId().substring(3);
      if (id.contains("-")) {
        // version specific
      } else {
        processV2Table(vs, tblindex.get(id));
      }
    }
    
  }

  private void processV2Table(VSPack vs, Element element) {
    if (element != null) {
      if (vs.cs != null)
        for (ConceptDefinitionComponent c : vs.cs.getConcept()) {
          processV2Code(c, element);
      }
    }
    for (Element e : getLangList(element)) {
      if (!ToolingExtensions.hasLanguageTranslation(vs.vs.getNameElement(), e.getAttribute("lang")))
        ToolingExtensions.addLanguageTranslation(vs.vs.getNameElement(), e.getAttribute("lang"), e.getAttribute("value"));
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
    if (!Utilities.noString(value))
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
          VSPack vp = new VSPack();
          buildV2CodeSystem(vp, id, e);
          valuesets.add(vp);
          vp.vs.setId("v2-"+FormatUtilities.makeId(id));
          vp.vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + "index.html");
          vp.cs.setId("v2-"+FormatUtilities.makeId(id));
          vp.cs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + "index.html");
        } else { // versioned
          TableRegistration tbl = tables.get(id);
          for (int i = 0; i < tbl.versionPoints.size(); i++) {
            String ver = tbl.versionPoints.get(i);
            String nver = i < tbl.versionPoints.size() - 1 ? tbl.versionPoints.get(i+1) : null;
            VSPack vp = new VSPack();
            buildV2CodeSystemVersioned(vp, id, ver, nver, e);
            valuesets.add(vp);
            vp.vs.setId("v2-"+FormatUtilities.makeId(ver)+"-"+id);
            vp.vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + ver + HTTP_separator + "index.html");
            vp.cs.setId("v2-"+FormatUtilities.makeId(ver)+"-"+id);
            vp.cs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + ver + HTTP_separator + "index.html");
          }
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
    
    // now, value sets
    e = XMLUtil.getFirstChild(page.getV2src().getDocumentElement());
    while (e != null) {
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      if (vsImports.containsKey(id)) {
        VSPack vp = new VSPack();
        ValueSet vs = buildV2ValueSet(id, e);
        vp.vs = vs;
        valuesets.add(vp);
        vs.setId("v2-"+FormatUtilities.makeId(id));
        vs.setUserData("path", "v2" + HTTP_separator + id + HTTP_separator + "index.html");
        page.getDefinitions().getValuesets().put(vs.getUrl(), vs);
        page.getValueSets().put(vs.getUrl(), vs);
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private ValueSet buildV2ValueSet(String id, Element e) throws Exception {
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setId("v2-"+FormatUtilities.makeId(id));
    vs.setUserData("filename", Utilities.path("v2", id, "index.html"));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName("v2 table " + id);
    vs.setPublisher("HL7, Inc");
    vs.setVersion(MAX_VER);
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(true);
    vs.setDateElement(new DateTimeType(date));
    ToolingExtensions.setStringExtension(vs, ToolingExtensions.EXT_BALLOT_STATUS, "External");
    StringBuilder s = new StringBuilder();
    Set<String> sources = vsImports.get(id);

    String desc = "";
    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    Map<String, String> comments = new HashMap<String, String>();
    while (c != null) {
      String ver = c.getAttribute("version");
      if (!ver.contains(" ")) {
        desc = c.getAttribute("desc");
        vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ( " + desc + ")");
        vs.setName("v2 " + desc);

        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          codes.put(g.getAttribute("code"), g.getAttribute("desc"));
          if (!Utilities.noString(g.getAttribute("comments")))
            comments.put(g.getAttribute("code"), g.getAttribute("comments"));
          g = XMLUtil.getNextSibling(g);
        }
      }
      c = XMLUtil.getNextSibling(c);
    }
    s.append("<p>").append(Utilities.escapeXml(desc)).append("</p>\r\n");
    s.append("<table class=\"grid\">");
    s.append("<tr><td><b>Code</b></td><td><b>System</b></td><td><b>Description</b></td><td><b>Comment</b></td><td><b>Version</b></td></tr>");
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
      String ver = "added v" + min + (MAX_VER.equals(max) ? "" : ", removed after v" + max);
      ConceptSetComponent cset = checkAddCSReference(vs, sources, cd);
      if (cset == null) {
        String translate = ini.getStringProperty("translate", id+"/"+cd);
        if (!Utilities.noString(translate)) {
          String comment = "";
          if (comments.containsKey(cd)) {
            comment = comments.get(cd);
          }
          String nm = Utilities.nmtokenize(cd);
          s.append("<tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(nm) + "\"> </a></td><td>??::<b>"+translate+"</b></td><td>" + Utilities.escapeXml(codes.get(cd))
            + "</td><td>Wrongly Included as "+cd+". " + Utilities.escapeXml(comment) + "</td><td>" + ver + "</td></tr>");
        } else if (!ini.getBooleanProperty("ignore", id+"/"+cd))
          throw new Exception("No Match for "+cd+" in "+sources.toString());
      } else {
        ConceptReferenceComponent concept = cset.addConcept();
        concept.setCode(cd);
        concept.setDisplay(codes.get(cd)); // we deem the v2 description to
        String comment = "";
        if (comments.containsKey(cd)) {
          comment = comments.get(cd);
          ToolingExtensions.addVSComment(concept, comment);
        }
        // be display name, not
        // definition. Open for
        // consideration
        String nm = Utilities.nmtokenize(cd);
        s.append("<tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(nm) + "\"> </a></td><td>"+cset.getSystem()+"</td><td>" + Utilities.escapeXml(codes.get(cd))
        + "</td><td>" + Utilities.escapeXml(comment) + "</td><td>" + ver + "</td></tr>");
      }
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

  private ConceptSetComponent checkAddCSReference(ValueSet vs, Set<String> sources, String cd) throws Exception {
    ConceptSetComponent cset = null;
    for (String system : sources) {
      CodeSystem cs = page.getCodeSystems().get(system);
      if (cs == null)
        throw new Exception("Unable to resolve code system "+system);
      if (definesCode(cs.getConcept(), cd)) {
        if (cset != null)
          throw new Exception("Multiple possible matches for "+cd+" in "+sources.toString());
        if (!vs.hasCompose())
          vs.setCompose(new ValueSetComposeComponent());
        for (ConceptSetComponent cc : vs.getCompose().getInclude()) {
          if (cc.getSystem().equals(system)) 
            cset = cc;
        }
        if (cset == null)
          cset = vs.getCompose().addInclude().setSystem(system);
      }
    }
    return cset;
  }

  private boolean definesCode(List<ConceptDefinitionComponent> list, String cd) {
    for (ConceptDefinitionComponent c : list) {
      if (cd.equalsIgnoreCase(c.getCode()))
        return true;
      if (definesCode(c.getConcept(), cd))
        return true;
    }
    return false;
  }

  private void buildV2CodeSystem(VSPack vp, String id, Element e) throws Exception {
    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setId("v2-"+FormatUtilities.makeId(id));
    vs.setUserData("filename", Utilities.path("v2", id, "index.html"));
    vs.setUserData("path", Utilities.path("v2", id, "index.html"));
    vs.setUrl("http://hl7.org/fhir/ValueSet/" + vs.getId());
    vs.setName("v2 table " + id);
    vs.setPublisher("HL7, Inc");
    vs.setVersion(MAX_VER);
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(true);
    vs.setDateElement(new DateTimeType(date)); 
    ToolingExtensions.setStringExtension(vs, ToolingExtensions.EXT_BALLOT_STATUS, "External");
    
    
    StringBuilder s = new StringBuilder();

    CodeSystem cs = new CodeSystem();
    ToolingExtensions.setStringExtension(cs, ToolingExtensions.EXT_BALLOT_STATUS, "External");
    String desc = "";
    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> cases = new HashMap<String, String>();
    Map<String, String> codes = new HashMap<String, String>();
    Map<String, String> comments = new HashMap<String, String>();
    Map<String, String> commentVersions = new HashMap<String, String>();
    while (c != null) {
      String ver = c.getAttribute("version");
      if (!ver.contains(" ")) {
        desc = c.getAttribute("desc");
        vs.setDescription("FHIR Value set/code system definition for HL7 v2 table " + id + " ( " + desc + ")");
        vs.setName("v2 " + desc);

        Element g = XMLUtil.getFirstChild(c);
        while (g != null) {
          cases.put(g.getAttribute("code").toLowerCase(), g.getAttribute("code"));
          codes.put(g.getAttribute("code").toLowerCase(), g.getAttribute("desc"));
          if (!Utilities.noString(g.getAttribute("comments"))) {
            if (!comments.containsKey(g.getAttribute("code").toLowerCase()))
              commentVersions.put(g.getAttribute("code").toLowerCase(), ver);
            comments.put(g.getAttribute("code").toLowerCase(), g.getAttribute("comments"));
          }
          g = XMLUtil.getNextSibling(g);
        }
      }
      c = XMLUtil.getNextSibling(c);
    }
    s.append("<p>").append(Utilities.escapeXml(desc)).append("</p>\r\n");
    s.append("<table class=\"grid\">");
    s.append("<tr><td><b>Code</b></td><td><b>Description</b></td><td><b>Comment</b></td><td><b>Version</b></td></tr>");
    List<String> css = new ArrayList<String>();
    css.addAll(codes.keySet());
    Collections.sort(css);
    for (String cd : css) {
      String min = null;
      String max = null;
      c = XMLUtil.getFirstChild(e);
      while (c != null) {
        String ver = c.getAttribute("version");
        if (!ver.contains(" ")) {
          Element g = XMLUtil.getFirstChild(c);
          while (g != null) {
            if (cd.equalsIgnoreCase(g.getAttribute("code"))) {
              if (min == null)
                min = ver;
              max = ver;
            }
            g = XMLUtil.getNextSibling(g);
          }
        }
        c = XMLUtil.getNextSibling(c);
      }
      String ver = ("2.1".equals(min) ? "from v2.1" : "added v" + min) + (MAX_VER.equals(max) ? "" : ", removed after v" + max);
      ConceptDefinitionComponent concept = new CodeSystem.ConceptDefinitionComponent();
      concept.setCode(cases.get(cd));
      concept.setDisplay(codes.get(cd)); // we deem the v2 description to
      String comment = "";
      String commentVer = "";
      if (comments.containsKey(cd)) {
        comment = comments.get(cd);
        commentVer = commentVersions.get(cd);
        ToolingExtensions.addCSComment(concept, comment);
      }
      // be display name, not
      // definition. Open for
      // consideration
      if (!(MAX_VER.equals(max)) || comment.equalsIgnoreCase("deprecated")) {
        CodeSystemUtilities.setDeprecated(cs, concept, dateForVersion(Utilities.noString(commentVer) ? max : commentVer));
        if (Utilities.noString(comment))
          comment = "deprecated";
      }
      cs.getConcept().add(concept);
      String nm = Utilities.nmtokenize(concept.getCode());
      s.append("<tr><td>" + Utilities.escapeXml(concept.getCode()) + "<a name=\"" + Utilities.escapeXml(nm) + "\"> </a></td><td>" + Utilities.escapeXml(codes.get(cd))
         + "</td><td>" + Utilities.escapeXml(comment) + "</td><td>" + ver + "</td></tr>");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.ADDITIONAL); // because we add
    // v2 versioning
    // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    CodeSystemUtilities.makeShareable(cs);
    CodeSystemConvertor.populate(cs, vs);
    cs.setContent(CodeSystemContentMode.COMPLETE);
    OIDEntry oe = oids.get(id);
    if (oe != null)
      CodeSystemUtilities.setOID(cs, "urn:oid:"+oe.getOid());
    cs.setCaseSensitive(false);
    cs.setUrl("http://hl7.org/fhir/v2/" + id);
    cs.setId("v2-"+FormatUtilities.makeId(id));
    cs.setUserData("filename", Utilities.path("v2", id, "index.html"));
    if (!vs.hasCompose())
      vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().addInclude().setSystem(cs.getUrl());
    vp.vs = vs;
    vp.cs = cs;
    page.getVsValidator().validate(errors, "v2 table "+id, cs, false, true);
    page.getCodeSystems().put(vp.cs.getUrl(), vp.cs);
    page.getVsValidator().validate(errors, "v2 table "+id, vs, false, true);
    page.getValueSets().put(vp.vs.getUrl(), vp.vs);

  }

  private DateTimeType dateForVersion(String version) throws Exception {
    if (Utilities.noString(version))
      throw new Exception("Unknown deprecation time");
    if (version.equals("2.1"))
      return new DateTimeType("1990-01");
    if (version.equals("2.2"))
      return new DateTimeType("1994-12");
    if (version.equals("2.3"))
      return new DateTimeType("1997-03");
    if (version.equals("2.3"))
      return new DateTimeType("1997-03");
    if (version.equals("2.3.1"))
      return new DateTimeType("1999-05");
    if (version.equals("2.4"))
      return new DateTimeType("2000-11");
    if (version.equals("2.5"))
      return new DateTimeType("2003-07");
    if (version.equals("2.5.1"))
      return new DateTimeType("2007-04");
    if (version.equals("2.6"))
      return new DateTimeType("2007-10");
    if (version.equals("2.7"))
      return new DateTimeType("2011-01");
    if (version.equals("2.7.1"))
      return new DateTimeType("2011-01");
    if (version.equals("2.8"))
      return new DateTimeType("2014-02");
    if (version.equals("2.8.1"))
      return new DateTimeType("2014-08");
    if (version.equals("2.8.2"))
      return new DateTimeType("2015-09");
    throw new Exception("Unknown version "+version);
  }

  private void buildV2CodeSystemVersioned(VSPack vp, String id, String version, String endVersion, Element e) throws Exception {
    StringBuilder s = new StringBuilder();

    ValueSet vs = new ValueSet();
    ValueSetUtilities.makeShareable(vs);
    vs.setId("v2-"+FormatUtilities.makeId(version)+"-"+id);
    vs.setUserData("filename", Utilities.path("v2", id, version, "index.html"));
    vs.setUserData("path", Utilities.path("v2", id, version, "index.html"));
    vs.setUrl("http://hl7.org/fhir/ValueSet/"+vs.getId());
    vs.setName("v2 table " + id + ", Version " + version);
    vs.setPublisher("HL7, Inc");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(false);
    vs.setVersion(id);
    vs.setDateElement(new DateTimeType(date)); 
    vs.setDescription("v2 table definition for "+vs.getName());
    ToolingExtensions.setStringExtension(vs, ToolingExtensions.EXT_BALLOT_STATUS, "External");
    CodeSystem cs = new CodeSystem();
    CodeSystemUtilities.makeShareable(cs);
    CodeSystemConvertor.populate(cs, vs);
    ToolingExtensions.setStringExtension(cs, ToolingExtensions.EXT_BALLOT_STATUS, "External");
    cs.setUserData("spec.vs.cs", vs);
    cs.setContent(CodeSystemContentMode.COMPLETE);

    OIDEntry oe = oids.get(id+"-"+version);
    if (oe != null)
      CodeSystemUtilities.setOID(cs, "urn:oid:"+oe.getOid());
    cs.setCaseSensitive(true);
    cs.setUrl("http://hl7.org/fhir/v2/" + id + "/" + version);
    cs.setId("v2-"+FormatUtilities.makeId(version)+"-"+id);
    cs.setUserData("filename", Utilities.path("v2", id, version, "index.html"));
    if (!vs.hasCompose())
      vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().addInclude().setSystem(cs.getUrl());

    String desc = "";
    String minlim = null;
    String maxlim = null;

    // we use the latest description of the table
    Element c = XMLUtil.getFirstChild(e);
    Map<String, String> codes = new HashMap<String, String>();
    Map<String, String> comments = new HashMap<String, String>();
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
            if (!Utilities.noString(g.getAttribute("comments")))
              comments.put(g.getAttribute("code"), g.getAttribute("comments"));
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
    List<String> css = new ArrayList<String>();
    css.addAll(codes.keySet());
    Collections.sort(css);
    started = false;
    for (String cd : css) {
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
              if (cd.equalsIgnoreCase(g.getAttribute("code"))) {
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
      ConceptDefinitionComponent concept = new CodeSystem.ConceptDefinitionComponent();
      concept.setCode(cd);
      concept.setDisplay(codes.get(cd)); // we deem the v2 description to
      if (comments.containsKey(cd))
        ToolingExtensions.addCSComment(concept, comments.get(cd));
      
      
      // be display name, not
      // definition. Open for
      // consideration
      cs.getConcept().add(concept);
      s.append("<tr><td>" + Utilities.escapeXml(cd) + "<a name=\"" + Utilities.escapeXml(Utilities.nmtokenize(cd)) + "\"> </a></td><td>"
          + Utilities.escapeXml(codes.get(cd)) + "</td><td>" + ver + "</td></tr>");
    }
    s.append("</table>\r\n");
    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.ADDITIONAL); // because we add
    // v2 versioning
    // information
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    vp.vs = vs;
    vp.cs = cs;
    page.getVsValidator().validate(errors, "v2 table "+id, cs, false, true);
    page.getCodeSystems().put(vp.cs.getUrl(), vp.cs);
    page.getVsValidator().validate(errors, "v2 table "+id, vs, false, true);
    page.getValueSets().put(vp.vs.getUrl(), vp.vs);
  }

  public String getIndex(Document v2src, boolean cs) throws IOException {
    loadOIds();
    StringBuilder s = new StringBuilder();
    s.append("<table class=\"grid\">\r\n");
    s.append(" <tr><td><b>URI</b> (all prefixed with http://hl7.org/fhir/v2/)</td><td><b>ID</b></td><td><b>OID</b></td></tr>\r\n");
    Element e = XMLUtil.getFirstChild(v2src.getDocumentElement());
    while (e != null) {
      String id = Utilities.padLeft(e.getAttribute("id"), '0', 4);
      if (tables.containsKey(id) || (!cs && vsImports.containsKey(id))) {
        String name = "";
        // we use the latest description of the table
        Element c = XMLUtil.getFirstChild(e);
        while (c != null) {
          name = c.getAttribute("desc");
          c = XMLUtil.getNextSibling(c);
        }
        if (tables.containsKey(id) && !tables.get(id).versionPoints.isEmpty()) {
          s.append(" <tr><td>").append(id).append("</td><td>").append(name).append("Version Dependent. Use one of:<ul>");
          for (String v : tables.get(id).versionPoints)
            s.append(" <li><a href=\"v2/").append(id).append("/").append(v).append("/index.html\">").append(v).append("+</a></li>");
          s.append("</ul></td><td></td></tr>\r\n");
        } else
          s.append(" <tr><td><a href=\"v2/").append(id).append("/index.html\">").append(id).append("</a></td><td>").append(name).append("</td><td></td></tr>\r\n");
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
      String iid = id;
      while (iid.startsWith("0"))
        iid = iid.substring(1);
      if (tables.containsKey(id)) {
        if (tables.get(id).versionPoints.isEmpty()) {
          Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
          String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-tbl.html");
          ValueSet vs = page.getValueSets().get("http://hl7.org/fhir/ValueSet/v2-"+id);
          String sf = page.processPageIncludes(id + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + "index.html", vs, null, "V2 Table", null, null, wg());
          sf = sects.addSectionNumbers("v2" + File.separator + id + File.separator +  "index.html", "template-v2", sf, iid, 2, null, null);
          TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + "index.html");
          page.getHTMLChecker().registerExternal("v2" + File.separator + id + File.separator + "index.html");
        } else {
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
              String sf = page.processPageIncludes(id + "|" + ver + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + ver + File.separator + "index.html", vs, null, "V2 Table", null, null, wg());
              sf = sects.addSectionNumbers("v2" + File.separator + id + "/" + ver + File.separator +  "index.html", "template-v2", sf, iid + "." + Integer.toString(i), 3, null, null);
              TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
              page.getHTMLChecker().registerExternal("v2" + File.separator + id + File.separator + ver + File.separator + "index.html");
            }
          }
        }
      } else if (vsImports.containsKey(id)) {
        Utilities.createDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        Utilities.clearDirectory(page.getFolders().dstDir + "v2" + File.separator + id);
        String src = TextFile.fileToString(page.getFolders().srcDir + "v2" + File.separator + "template-vs.html");
        ValueSet vs = page.getValueSets().get("http://hl7.org/fhir/ValueSet/v2-"+id);
        String sf = page.processPageIncludes(id + ".html", src, "v2Vocab", null, "v2" + File.separator + id + File.separator + "index.html", vs, null, "V2 Table", null, null, wg());
        sf = sects.addSectionNumbers("v2" + File.separator + id + File.separator +  "index.html", "template-v2", sf, iid, 2, null, null);
        TextFile.stringToFile(sf, page.getFolders().dstDir + "v2" + File.separator + id + File.separator + "index.html");
        page.getHTMLChecker().registerExternal("v2" + File.separator + id + File.separator + "index.html");        
      }
      e = XMLUtil.getNextSibling(e);
    }

    
  }

  private WorkGroup wg() {
    return page.getDefinitions().getWorkgroups().get("vocab");
  }


}
