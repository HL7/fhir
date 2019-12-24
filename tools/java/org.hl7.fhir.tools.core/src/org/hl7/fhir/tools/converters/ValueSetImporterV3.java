package org.hl7.fhir.tools.converters;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.hl7.fhir.definitions.model.WorkGroup;
import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.r5.formats.FormatUtilities;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r5.model.CodeSystem.CodeSystemHierarchyMeaning;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.CodeSystem.PropertyType;
import org.hl7.fhir.r5.model.CodeType;
import org.hl7.fhir.r5.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.r5.model.DateTimeType;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.Factory;
import org.hl7.fhir.r5.model.InstantType;
import org.hl7.fhir.r5.model.Meta;
import org.hl7.fhir.r5.model.Narrative;
import org.hl7.fhir.r5.model.Narrative.NarrativeStatus;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.r5.model.ValueSet.FilterOperator;
import org.hl7.fhir.r5.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities.ConceptStatus;
import org.hl7.fhir.r5.terminologies.ValueSetUtilities;
import org.hl7.fhir.r5.utils.NarrativeGenerator;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.tools.publisher.SectionNumberer;
import org.hl7.fhir.utilities.CSFile;
import org.hl7.fhir.utilities.CSFileInputStream;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.validation.ValidationMessage;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

public class ValueSetImporterV3 extends ValueSetImporterBase {
  private List<ValidationMessage> errors; 
  private PageProcessor page;
  public class VSPack {

    public ValueSet vs;
    public CodeSystem cs;

  }

  public ValueSetImporterV3(PageProcessor page, List<ValidationMessage> errors) {
    super();
    this.page = page;
    this.errors = errors;
  }

  private static String nodeToString(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToString((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(Utilities.escapeXml(n.getTextContent()));
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("<br/>\r\n");
    return b.toString();
  }

  private static String nodeToText(Element node) throws Exception {
    StringBuilder b = new StringBuilder();
    Node n = node.getFirstChild();
    while (n != null) {
      if (n.getNodeType() == Node.ELEMENT_NODE) {
        b.append(nodeToText((Element) n));
      } else if (n.getNodeType() == Node.TEXT_NODE) {
        b.append(n.getTextContent());
      }
      n = n.getNextSibling();
    }
    if (node.getNodeName().equals("p"))
      b.append("\r\n");
    return b.toString();
  }

  private static class CodeInfo {
    boolean select;
    String code;
    String display;
    String displayNL;
    String definition;
    String definitionNL;
    String textDefinition;
    String partOf;
    boolean inactive;
    DateTimeType deprecated;

    List<String> parents = new ArrayList<String>();
    List<CodeInfo> children = new ArrayList<CodeInfo>();

    public void write(int lvl, StringBuilder s, ValueSet vs, List<ConceptDefinitionComponent> list, ConceptDefinitionComponent owner,
        Map<String, ConceptDefinitionComponent> handled, CodeSystem cs, boolean doPart) throws Exception {
      if (!select && children.size() == 0)
        return;

      if (handled.containsKey(code)) {
        if (owner == null)
          throw new Exception("Error handling poly-hierarchy - subsequent mention is on the root");
        CodeSystemUtilities.addOtherChild(cs, owner, code);
        s.append(" <tr><td>").append(Integer.toString(lvl)).append("</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        s.append("<a href=\"#").append(Utilities.escapeXml(Utilities.nmtokenize(code))).append("\">")
        .append(Utilities.escapeXml(code)).append("</a></td><td></td><td></td></tr>\r\n");
      } else {
        ConceptDefinitionComponent concept = new CodeSystem.ConceptDefinitionComponent();
        handled.put(code, concept);
        concept.setCode(code);
        concept.setDisplay(display);
        concept.setDefinition(textDefinition);
        if (displayNL != null)
          concept.addDesignation().setLanguage("nl").setValue(displayNL).getUse().setSystem("http://terminology.hl7.org/CodeSystem/designation-usage").setCode("display");
        if (definitionNL != null)
          concept.addDesignation().setLanguage("nl").setValue(definitionNL).getUse().setSystem("http://terminology.hl7.org/CodeSystem/designation-usage").setCode("definition");
        
        if (doPart && partOf != null)
          concept.addProperty().setCode("partOf").setValue(new CodeType(partOf));
        if (!concept.hasDefinition())
          concept.setDefinition(concept.getDisplay());
        String d = "";
        if (!select) {
          CodeSystemUtilities.setNotSelectable(cs, concept);
          d = d + " <b><i>Abstract</i></b>";
        }
        if (deprecated != null) {
          CodeSystemUtilities.setDeprecated(cs, concept, deprecated);
          d = d + " <b><i>Deprecated</i></b>";
        }
        if (inactive)
          CodeSystemUtilities.setStatus(cs, concept, ConceptStatus.Retired);

        list.add(concept);

        s.append(" <tr" + (deprecated != null ? " style=\"background: #EFEFEF\"" : "") + "><td>" + Integer.toString(lvl) + "</td><td>");
        for (int i = 1; i < lvl; i++)
          s.append("&nbsp;&nbsp;");
        if (select) {
          s.append(Utilities.escapeXml(code) + "<a name=\"" + cs.getId()+"-"+ Utilities.escapeXml(Utilities.nmtokenize(code)) + "\"> </a>" + d + "</td><td>"
              + Utilities.escapeXml(display) + "</td><td>");
        } else
          s.append("<span style=\"color: grey\"><i>(" + Utilities.escapeXml(code) + ")</i></span>" + d + "</td><td><a name=\""
              + cs.getId()+"-"+ Utilities.escapeXml(Utilities.nmtokenize(code)) + "\">&nbsp;</a></td><td>");
        if (definition != null)
          s.append(definition);
        if (doPart) {
          if (doPart && partOf != null)
            s.append("</td><td>"+partOf);
          else
            s.append("</td><td>");
        }
        s.append("</td></tr>\r\n");
        for (CodeInfo child : children) {
          child.write(lvl + 1, s, vs, concept.getConcept(), concept, handled, cs, doPart);
        }
      }
    }
  }

  private void buildV3CodeSystem(VSPack vp, String id, String date, Element e, String csOid, String vsOid, Element nl) throws Exception {
    StringBuilder s = new StringBuilder();
    ValueSet vs = new ValueSet();
    vs.setUserData("filename", Utilities.path("v3", id, "vs.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://terminology.hl7.org/ValueSet/" + vs.getId());
    ValueSetUtilities.markStatus(vs, null, StandardsStatus.EXTERNAL, null,  "0", null, null);
    ValueSetUtilities.makeShareable(vs);
    vs.setName("v3." + id);
    vs.setTitle("v3 Code System " + id);
    vs.setPublisher("HL7, Inc");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    
    vs.setId("v3-" + FormatUtilities.makeId(id));
    vs.setUserData("path", "v3" + "/" + id + "/" + "vs.html");
    
    vs.setUserData("filename", "valueset-"+id);
    vs.addExtension().setUrl(ToolingExtensions.EXT_WORKGROUP).setValue(new CodeType("vocab"));

    Element r = XMLUtil.getNamedChild(e, "releasedVersion");
    if (r != null) {
      s.append("<p>Release Date: " + r.getAttribute("releaseDate") + "</p>\r\n");
      vs.setDateElement(new DateTimeType(r.getAttribute("releaseDate")));
      vs.setVersion(r.getAttribute("releaseDate"));
    }
//    if (csOid != null)
//      s.append("<p>OID for code system: " + csOid + "</p>\r\n");
    if (vsOid != null) {
//      s.append("<p>OID for value set: " + vsOid + " (this is the value set that includes the entire code system)</p>\r\n");
      ValueSetUtilities.setOID(vs, "urn:oid:"+vsOid);
      
    }
    r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"), "text");
    if (r == null)
      r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "definition"), "text");
    if (r != null) {
//      s.append("<h2>Description</h2>\r\n");
//      s.append("<p>").append(nodeToString(r)).append("</p>\r\n");
//      s.append("<hr/>\r\n");
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r));
      r = XMLUtil.getNamedChildByAttribute(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(nl, "annotations"), "documentation"), "description"), "text", "lang", "nl");
      if (r == null)
        r = XMLUtil.getNamedChildByAttribute(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(nl, "annotations"), "documentation"), "definition"), "text", "lang", "nl");
      if (r != null)
        ToolingExtensions.addLanguageTranslation(vs.getDescriptionElement(), "nl", XMLUtil.htmlToXmlEscapedPlainText(r));
    } else
      vs.setDescription("**** MISSING DEFINITIONS ****");

    CodeSystem cs = new CodeSystem();
    CodeSystemUtilities.markStatus(cs, null, StandardsStatus.EXTERNAL, null,  "0", null);
    cs.setUrl("http://terminology.hl7.org/CodeSystem/v3-" + id);
    cs.setId("v3-"+FormatUtilities.makeId(id));
    CodeSystemUtilities.setOID(cs, "urn:oid:"+csOid);
    CodeSystemConvertor.populate(cs, vs);
    cs.setUserData("path", "v3" + "/" + id + "/" + "cs.html");
    cs.setUserData("filename", "v3" + "/" + id + "/" + "cs.html");
    if (!vs.hasCompose())
      vs.setCompose(new ValueSetComposeComponent());
    vs.getCompose().addInclude().setSystem(cs.getUrl());
    vs.setExperimental(false);
    cs.setCaseSensitive(true);
    cs.setContent(CodeSystemContentMode.COMPLETE);
    cs.setValueSet(vs.getUrl());
    vs.setImmutable(true);
    cs.setHierarchyMeaning(CodeSystemHierarchyMeaning.ISA);
    
    String partOfName = getRelationship(e, "ComponentOf");  
    if (partOfName != null) {
      cs.addProperty().setCode("partOf").setDescription("This relationship indicates that the source concept is a component of the target concept").setType(PropertyType.CODE).setUri("http://hl7.org/fhir/codesystem-hierarchy-meaning#part-of");
    }

    List<CodeInfo> codes = new ArrayList<CodeInfo>();
    // first, collate all the codes
    Element c = XMLUtil.getFirstChild(XMLUtil.getNamedChild(e, "releasedVersion"));
    while (c != null) {
      if (c.getNodeName().equals("concept")) {
        CodeInfo ci = new CodeInfo();
        ci.select = !"false".equals(c.getAttribute("isSelectable"));
        r = XMLUtil.getNamedChild(c, "code");
        ci.code = r == null ? null : r.getAttribute("code");
        Element enl = getCodeFromSecondary(nl, ci.code);
        r = XMLUtil.getNamedChild(c, "printName");
        ci.display = r == null ? null : r.getAttribute("text");
        r = XMLUtil.getNamedChildByAttribute(enl, "printName", "language", "nl");
        ci.displayNL = r == null ? null : r.getAttribute("text");
        r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "documentation"), "definition"), "text");
        ci.definition = r == null ? null : nodeToString(r);
        ci.textDefinition = r == null ? null : nodeToText(r).trim();
        r = XMLUtil.getNamedChildByAttribute(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(enl, "annotations"), "documentation"), "definition"), "text", "lang", "nl");
        ci.definitionNL = r == null ? null : nodeToText(r).trim();
        if (partOfName != null)
         ci.partOf = getRelationshipValue(c, partOfName);  
        if ("retired".equals(XMLUtil.getNamedChild(c, "code").getAttribute("status")))
          ci.inactive = true;
        Element di = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(c, "annotations"), "appInfo"), "deprecationInfo");
        if (di != null) {
          String dd = di.getAttribute("deprecationEffectiveVersion");
          if (dd.contains("-"))
            ci.deprecated = DateTimeType.parseV3(dd.substring(dd.indexOf("-")+1));
        }
        List<Element> pl = new ArrayList<Element>();
        XMLUtil.getNamedChildren(c, "conceptRelationship", pl);
        for (Element p : pl) {
          if (p.getAttribute("relationshipName").equals("Specializes"))
            ci.parents.add(XMLUtil.getFirstChild(p).getAttribute("code"));
        }
        codes.add(ci);
      }
      c = XMLUtil.getNextSibling(c);
    }

    // now, organise the hierarchy
    for (CodeInfo ci : codes) {
      for (String p : ci.parents) {
        CodeInfo pi = null;
        for (CodeInfo cip : codes) {
          if (cip.code != null && cip.code.equals(p))
            pi = cip;
        }
        if (pi != null)
          pi.children.add(ci);
      }
    }

    s.append("<table class=\"grid\">\r\n");
    if (partOfName != null)
      s.append(" <tr><td><b>Level</b></td><td><b>Code</b></td><td><b>Display</b></td><td><b>Definition</b></td><td><b>PartOf</b></td></tr>\r\n");
    else
      s.append(" <tr><td><b>Level</b></td><td><b>Code</b></td><td><b>Display</b></td><td><b>Definition</b></td></tr>\r\n");
    Map<String, ConceptDefinitionComponent> handled = new HashMap<String, ConceptDefinitionComponent>();
    for (CodeInfo ci : codes) {
      if (ci.parents.size() == 0) {
        ci.write(1, s, vs, cs.getConcept(), null, handled, cs, partOfName != null);
      }
    }
    s.append("</table>\r\n");

    vp.vs = vs;
    vp.cs = cs;

    cs.setText(new Narrative());
    cs.getText().setStatus(NarrativeStatus.GENERATED);
    cs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    page.getVsValidator().validate(page.getValidationErrors(), "v3 code system "+id, cs, false, true);
    page.getCodeSystems().see(vp.cs);

    vs.setText(new Narrative());
    vs.getText().setStatus(NarrativeStatus.GENERATED);
    vs.getText().setDiv(new XhtmlParser().parse("<div>" + s.toString() + "</div>", "div").getElement("div"));
    page.getVsValidator().validate(page.getValidationErrors(), "v3 valueset "+id, vs, false, true);
    page.getValueSets().see(vp.vs);

  }

  private Element getCodeFromSecondary(Element cs, String code) {
    if (cs == null)
      return null;
    List<Element> rvl = XMLUtil.getNamedChildren(cs, "releasedVersion");
    for (Element rv : rvl) {
      List<Element> cl = XMLUtil.getNamedChildren(rv, "concept");
      for (Element c : cl) {
        if (code.equals(XMLUtil.getNamedChildAttribute(c, "code", "code")))
            return c;
      }
    }
    return null;
  }

  private String getRelationshipValue(Element e, String partOfName) {
    List<Element> rl = new ArrayList<Element>();
    XMLUtil.getNamedChildren(e, "conceptRelationship", rl);
    for (Element r : rl) {
      if (partOfName.equals(r.getAttribute("relationshipName")))
        return XMLUtil.getNamedChildAttribute(r, "targetConcept", "code"); 
    }
    return null;
  }

  private String getRelationship(Element e, String string) {
    Element rv = XMLUtil.getNamedChild(e, "releasedVersion");
    if (rv == null)
      return null;
    List<Element> rl = new ArrayList<Element>();
    XMLUtil.getNamedChildren(rv, "supportedConceptRelationship", rl);
    for (Element r : rl) {
      if ("ComponentOf".equals(r.getAttribute("relationshipKind")))
        return r.getAttribute("name");
    }
    return null;
  }

  public void execute() throws Exception {
    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    factory.setNamespaceAware(true);
    DocumentBuilder builder = factory.newDocumentBuilder();
    page.setV3src(builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v3" + File.separator + "source.xml"))));
    Document nl = builder.parse(new CSFileInputStream(new CSFile(page.getFolders().srcDir + "v3" + File.separator + "source_nl.xml")));
    
    
    String dt = null;
    Map<String, CodeSystem> codesystems = new HashMap<String, CodeSystem>();
    Set<String> cslist = new HashSet<String>();

    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {

      if (e.getNodeName().equals("header")) {
        Element d = XMLUtil.getNamedChild(e, "renderingInformation");
        if (d != null)
          dt = d.getAttribute("renderingTime");
      }

      if (e.getNodeName().equals("codeSystem")) {
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name")) && !deprecated(e)) {
          String id = e.getAttribute("name");
          if (cslist.contains(id))
            throw new Exception("Duplicate v3 name: "+id);
          cslist.add(id);
          Element rv = XMLUtil.getNamedChild(e, "releasedVersion");
          if (rv != null && (rv.getAttribute("hl7MaintainedIndicator").equals("true") || ini.getBooleanProperty("CodeSystems", id))) {
            String vsOid = getVSForCodeSystem(page.getV3src().getDocumentElement(), e.getAttribute("codeSystemId"));
            VSPack vp = new VSPack();
            buildV3CodeSystem(vp, id, dt, e, e.getAttribute("codeSystemId"), vsOid, getNLcS(nl, id));
            if (vp.vs.hasDate())
              vp.vs.getMeta().setLastUpdatedElement(new InstantType(vp.vs.getDate()));
            else
              vp.vs.getMeta().setLastUpdated(page.getGenDate().getTime());
            if (!vp.cs.hasMeta())
              vp.cs.setMeta(new Meta());
            vp.cs.getMeta().setLastUpdated(vp.vs.getMeta().getLastUpdated());
            codesystems.put(e.getAttribute("codeSystemId"), vp.cs);
          } // else if (r == null)
          // page.log("unowned code system: "+id);
        }
      }

      if (e.getNodeName().equals("valueSet")) {
        String iniV = ini.getStringProperty("ValueSets", e.getAttribute("name"));
        if (iniV != null) {
          String id = e.getAttribute("name");
          ValueSet vs;
          if (iniV.startsWith("->")) {
            vs = buildV3ValueSetAsCodeSystem(id, e, iniV.substring(2));
          } else { 
            if (!iniV.equals("1"))
              id = iniV;
            vs = buildV3ValueSet(id, dt, e, codesystems, ini);
          }
          if (cslist.contains(vs.getId()))
            throw new Exception("Duplicate v3 name: "+vs.getId());
          cslist.add(vs.getId());

          vs.setUserData("path", "v3" + "/" + id + "/" + "vs.html");
          ValueSetUtilities.setOID(vs, "urn:oid:"+e.getAttribute("id"));
          if (vs.hasDate())
            vs.getMeta().setLastUpdatedElement(new InstantType(vs.getDate()));
          else
            vs.getMeta().setLastUpdated(page.getGenDate().getTime());
          page.getValueSets().see(vs);
          page.getDefinitions().getValuesets().see(vs);
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private Element getNLcS(Document nl, String id) {
    Element e = XMLUtil.getFirstChild(nl.getDocumentElement());
    while (e != null) {
      if (e.getNodeName().equals("codeSystem") && e.getAttribute("name").equals(id))
        return e;
      e = XMLUtil.getNextSibling(e);
    }
    return null;
  }

  private boolean deprecated(Element cs) {
    Element e = XMLUtil.getNamedChild(cs, "annotations");
    e = XMLUtil.getNamedChild(e, "appInfo");
    e = XMLUtil.getNamedChild(e, "deprecationInfo");
    return e != null;
  }

  private String getVSForCodeSystem(Element documentElement, String oid) {
    // we need to find a value set that has the content from the supported code system, and nothing ese
    Element element = XMLUtil.getFirstChild(documentElement);
    while (element != null) {
      Element version = XMLUtil.getNamedChild(element, "version");
      if (version != null) {
        Element content = XMLUtil.getNamedChild(version, "content");
        if (oid.equals(content.getAttribute("codeSystem")) && content.getFirstChild() == null)
          return element.getAttribute("id");
      }
      element = XMLUtil.getNextSibling(element);
    }

    return null;
  }

  private ValueSet buildV3ValueSetAsCodeSystem(String id, Element e, String csname) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    vs.setUserData("filename", Utilities.path("v3", id, "vs.html"));
    vs.setUserData("path", Utilities.path("v3", id, "vs.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://terminology.hl7.org/ValueSet/" + vs.getId());
    ValueSetUtilities.markStatus(vs, null, StandardsStatus.EXTERNAL, null,  "0", null, null);
    ValueSetUtilities.makeShareable(vs);
    vs.setName("v3."+id);
    vs.setTitle("V3 Value Set"+id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r));
    } else {
      vs.setDescription("No Description Provided");
    }
    vs.setPublisher("HL7 v3");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://www.hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(false);
    vs.setImmutable("true".equals(e.getAttribute("isImmutable")));

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null)
      vs.setVersion(r.getAttribute("versionDate"));
    String[] parts = csname.split("\\&");
    ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
    vs.setCompose(compose);
    for (String cs : parts) {
      if (cs.contains(":")) {
        compose.addInclude().setSystem(cs);
      } else if (cs.contains(".")) {
        String[] csp = cs.split("\\.");
        if (csp.length != 2)
          throw new Exception("unhandled value set specifier "+cs+" in ini file");
        ConceptSetComponent inc = compose.addInclude();
        inc.setSystem("http://terminology.hl7.org/CodeSystem/v3-"+csp[0]);
        inc.addFilter().setProperty("concept").setOp(FilterOperator.ISA).setValue(csp[1]);
      } else {
        compose.addInclude().setSystem("http://terminology.hl7.org/CodeSystem/v3-"+cs);
      }
    }

    NarrativeGenerator gen = new NarrativeGenerator("../../", "v3/"+id, page.getWorkerContext()).setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
    gen.generate(vs, null);
    page.getVsValidator().validate(page.getValidationErrors(), "v3 value set as code system "+id, vs, false, true);
    return vs;
  }

  private ValueSet buildV3ValueSet(String id, String dt, Element e, Map<String, CodeSystem> codesystems, IniFile vsini) throws DOMException, Exception {
    ValueSet vs = new ValueSet();
    vs.setUserData("filename", Utilities.path("v3", id, "vs.html"));
    vs.setUserData("path", Utilities.path("v3", id, "vs.html"));
    vs.setId("v3-"+FormatUtilities.makeId(id));
    vs.setUrl("http://terminology.hl7.org/ValueSet/" + vs.getId());
    ValueSetUtilities.markStatus(vs, null, StandardsStatus.EXTERNAL, null,  "0", null, null);
    ValueSetUtilities.makeShareable(vs);
    vs.setName("v3."+id);
    vs.setTitle("V3 Value Set"+id);
    Element r = XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(XMLUtil.getNamedChild(e, "annotations"), "documentation"), "description"),
        "text");
    if (r != null) {
      vs.setDescription(XMLUtil.htmlToXmlEscapedPlainText(r));
    } else {
      vs.setDescription("No Description Provided");
    }
    vs.setPublisher("HL7 v3");
    vs.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://www.hl7.org"));
    vs.setStatus(PublicationStatus.ACTIVE);
    vs.setExperimental(false);
    vs.setImmutable("true".equals(e.getAttribute("isImmutable")));

    r = XMLUtil.getNamedChild(e, "version");
    if (r != null) {
      vs.setVersion(r.getAttribute("versionDate"));

      // ok, now the content
      ValueSetComposeComponent compose = new ValueSet.ValueSetComposeComponent();
      vs.setCompose(compose);
      Element content = XMLUtil.getNamedChild(r, "content");
      if (content == null)
        throw new Exception("Unable to find content for ValueSet " + id);
      String csu = null;
      CodeSystem cs = codesystems.get(content.getAttribute("codeSystem"));
      if (cs == null)
        csu = getUrlForCS(content.getAttribute("codeSystem"));
      if (cs == null && csu == null)  {
        Element ee = XMLUtil.getNamedChild(e, "supportedCodeSystem");
        throw new Exception("Error Processing ValueSet " + id + ", unable to resolve code system '"
            + (ee == null ? content.getAttribute("codeSystem") : e.getTextContent()) + "'");
      }
      ConceptSetComponent imp = new ValueSet.ConceptSetComponent();

      if (XMLUtil.hasNamedChild(content, "combinedContent")) {
        if (!id.equals("SecurityControlObservationValue") && !id.equals("ProvenanceEventCurrentState"))
          throw new Exception("check logic; this is fragile code, and each value set needs manual review- id is "+id);
        Element part = XMLUtil.getFirstChild(XMLUtil.getNamedChild(content, "combinedContent"));
        while (part != null) {
          if (part.getNodeName().equals("unionWithContent"))
            compose.addInclude().addValueSet("http://terminology.hl7.org/ValueSet/v3-" + XMLUtil.getNamedChild(part, "valueSetRef").getAttribute("name"));
          else
            throw new Exception("unknown value set construction method");
          part = XMLUtil.getNextSibling(part);
        }
      } else {
        // simple value set
        compose.getInclude().add(imp);
        if (cs == null)
          imp.setSystem(csu);
        else
          imp.setSystem(cs.getUrl());

        if (!XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent().equals(content.getAttribute("codeSystem")))
          throw new Exception("Unexpected codeSystem oid on content for ValueSet " + id + ": expected '"
              + XMLUtil.getNamedChild(r, "supportedCodeSystem").getTextContent() + "', found '" + content.getAttribute("codeSystem") + "'");

        List<String> codes = new ArrayList<String>();

        Element cnt = XMLUtil.getFirstChild(content);
        while (cnt != null) {
          if (cnt.getNodeName().equals("codeBasedContent") && (XMLUtil.getNamedChild(cnt, "includeRelatedCodes") != null)) {
            // common case: include a child and all or some of it's descendants
            ConceptSetFilterComponent f = new ValueSet.ConceptSetFilterComponent();
            f.setOp(FilterOperator.ISA);
            f.setProperty("concept");
            f.setValue(cnt.getAttribute("code"));
            imp.getFilter().add(f);
            if ("false".equals(cnt.getAttribute("includeHeadCode"))) {
              compose.addExclude().setSystem(imp.getSystem()).addConcept().setCode(cnt.getAttribute("code"));
            }
          } else if (cnt.getNodeName().equals("codeBasedContent") && cnt.hasAttribute("code")) {
            codes.add(cnt.getAttribute("code"));
          }
          cnt = XMLUtil.getNextSibling(cnt);
        }
        if (vsini.getStringProperty("Order", id) != null) {
          List<String> order = new ArrayList<String>();
          for (String s : vsini.getStringProperty("Order", id).split("\\,")) {
            order.add(s);
          }
          for (String c : order) {
            if (codes.contains(c))
              imp.addConcept().setCode(c);
          }
          for (String c : codes) {
            if (!order.contains(c))
              imp.addConcept().setCode(c);
          }
        } else
          for (String c : codes) {
            imp.addConcept().setCode(c);
          }
      }
    }
    NarrativeGenerator gen = new NarrativeGenerator("../../", "v3/"+id, page.getWorkerContext()).setTooCostlyNoteEmpty(PageProcessor.TOO_MANY_CODES_TEXT_EMPTY).setTooCostlyNoteNotEmpty(PageProcessor.TOO_MANY_CODES_TEXT_NOT_EMPTY);
    gen.generate(vs, null);
    page.getVsValidator().validate(page.getValidationErrors(), "v3 valueset "+id, vs, false, true);
    return vs;
  }

  private String getUrlForCS(String oid) {
    if (oid.equals("2.16.840.1.113883.6.121"))
      return "urn:iso:std:iso:3166";
    if (oid.equals("2.16.840.1.113883.6.1"))
      return "http://loinc.org";
    return null;
  }

  public void produce(SectionNumberer sects) throws Exception {

    IniFile ini = new IniFile(page.getFolders().srcDir + "v3" + File.separator + "valuesets.ini");

    Element e = XMLUtil.getFirstChild(page.getV3src().getDocumentElement());
    while (e != null) {
      if (e.getNodeName().equals("codeSystem")) {
        if (!ini.getBooleanProperty("Exclude", e.getAttribute("name")) && !deprecated(e)) {
          Element rv = XMLUtil.getNamedChild(e, "releasedVersion");
          if (rv != null && (rv.getAttribute("hl7MaintainedIndicator").equals("true") || ini.getBooleanProperty("CodeSystems",  e.getAttribute("name")))) {
            String id = e.getAttribute("name");
            Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
            String mid = FormatUtilities.makeId(id);
            ValueSet vs = page.getValueSets().get("http://terminology.hl7.org/ValueSet/v3-"+mid);
            if (vs == null) {
              boolean match = false;
              for (String s : page.getValueSets().keys()) {
                if (s.contains(mid)) {
                  match = true;
                  System.out.println("found: " +s);
                }
              }
             System.out.println("no match for http://terminology.hl7.org/ValueSet/v3-"+mid);
            }
            CodeSystem cs = (CodeSystem) vs.getUserData("cs");
            
            String src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-cs.html");
            String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "cs.html", cs, null, null, "V3 CodeSystem", null, null, wg());
            sf = sects.addSectionNumbers(Utilities.path("v3", id, "cs.html"), "template-v3", sf, Utilities.oidTail(e.getAttribute("codeSystemId")), 2, null, null);
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "cs.html");
            page.getHTMLChecker().registerExternal("v3" + File.separator + id + File.separator + "cs.html");

            src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-vs.html");
            sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "vs.html", vs, null, null, "V3 ValueSet", null, null, wg());
            sf = sects.addSectionNumbers(Utilities.path("v3", id, "vs.html"), "template-v3", sf, Utilities.oidTail(e.getAttribute("codeSystemId")), 2, null, null);
            TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "vs.html");
            page.getHTMLChecker().registerExternal("v3" + File.separator + id + File.separator + "vs.html");
          }
        }
      }
      if (e.getNodeName().equals("valueSet")) {
        String iniV = ini.getStringProperty("ValueSets", e.getAttribute("name"));
        if (iniV != null) {
          String id = e.getAttribute("name");
          if (!(iniV.equals("1") || iniV.startsWith("->")))
            id = iniV;

          Utilities.createDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
          Utilities.clearDirectory(page.getFolders().dstDir + "v3" + File.separator + id);
          String src = TextFile.fileToString(page.getFolders().srcDir + "v3" + File.separator + "template-vs.html");
          ValueSet vs = page.getValueSets().get("http://terminology.hl7.org/ValueSet/v3-"+FormatUtilities.makeId(id));
          String sf = page.processPageIncludes(id + ".html", src, "v3Vocab", null, "v3" + File.separator + id + File.separator + "vs.html", vs, null, "V3 ValueSet", null, null, wg());
          sf = sects.addSectionNumbers(Utilities.path("v3", id, "vs.html"), "template-v3", sf, Utilities.oidTail(e.getAttribute("id")), 2, null, null);
          TextFile.stringToFile(sf, page.getFolders().dstDir + "v3" + File.separator + id + File.separator + "vs.html");
          page.getHTMLChecker().registerExternal("v3" + File.separator + id + File.separator + "vs.html");
        }
      }
      e = XMLUtil.getNextSibling(e);
    }
  }

  private WorkGroup wg() {
    return page.getDefinitions().getWorkgroups().get("vocab");
  }

}
