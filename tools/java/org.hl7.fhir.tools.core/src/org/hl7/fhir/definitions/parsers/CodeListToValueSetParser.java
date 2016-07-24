package org.hl7.fhir.definitions.parsers;

import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.formats.FormatUtilities;
import org.hl7.fhir.dstu3.formats.IParser;
import org.hl7.fhir.dstu3.formats.JsonParser;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.formats.IParser.OutputStyle;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.ConceptMap;
import org.hl7.fhir.dstu3.model.ContactPoint;
import org.hl7.fhir.dstu3.model.Factory;
import org.hl7.fhir.dstu3.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapContactComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.dstu3.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.dstu3.model.Enumerations.ConceptMapEquivalence;
import org.hl7.fhir.dstu3.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetContactComponent;
import org.hl7.fhir.dstu3.terminologies.CodeSystemUtilities;
import org.hl7.fhir.dstu3.utils.NarrativeGenerator;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.igtools.spreadsheets.TabDelimitedSpreadSheet;
import org.hl7.fhir.tools.publisher.EPubManager;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.XLSXmlParser.Sheet;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;

public class CodeListToValueSetParser {

  private Sheet sheet;
  private ValueSet valueSet;
  private String version;
  private String sheetName;
  private TabDelimitedSpreadSheet tabfmt;
  private Map<String, CodeSystem> codeSystems;
  private Map<String, ConceptMap> maps;

  public CodeListToValueSetParser(Sheet sheet, String sheetName, ValueSet valueSet, String version, TabDelimitedSpreadSheet tabfmt, 
      Map<String, CodeSystem> codeSystems, Map<String, ConceptMap> maps) throws Exception {
    super();
    this.sheet = sheet;
    this.sheetName = sheetName;
    this.valueSet = valueSet;
    this.version = version;
    this.tabfmt = tabfmt;
    this.codeSystems = codeSystems;
    this.maps = maps;

    tabfmt.column("System");
    tabfmt.column("Id");
    tabfmt.column("Abstract");
    tabfmt.column("Code");
    tabfmt.column("Display");
    tabfmt.column("Definition");
    tabfmt.column("Comment");
    tabfmt.column("v2");
    tabfmt.column("v3");
    tabfmt.column("Parent");
    for (String ct : sheet.columns) 
      if (ct.startsWith("Display:"))
        tabfmt.column(ct);
  }

  public void execute(String v2map, String v3map) throws Exception {
    boolean hasDefine = false;
    for (int row = 0; row < sheet.rows.size(); row++) {
      tabfmt.row();
      tabfmt.cell(sheet.getColumn(row, "System"));
      tabfmt.cell(sheet.getColumn(row, "Id"));
      tabfmt.cell(sheet.getColumn(row, "Abstract"));
      tabfmt.cell(sheet.getColumn(row, "Code"));
      tabfmt.cell(sheet.getColumn(row, "Display"));
      tabfmt.cell(sheet.getColumn(row, "Definition"));
      tabfmt.cell(sheet.getColumn(row, "Comment"));
      tabfmt.cell(sheet.getColumn(row, "v2"));
      tabfmt.cell(sheet.getColumn(row, "v3"));
      tabfmt.cell(sheet.getColumn(row, "Parent"));
      for (String ct : sheet.columns) 
        if (ct.startsWith("Display:"))
          tabfmt.cell(sheet.getColumn(row, ct));

      hasDefine = hasDefine || Utilities.noString(sheet.getColumn(row, "System"));
    }

    Map<String, ConceptDefinitionComponent> codes = new HashMap<String, ConceptDefinitionComponent>();
    Map<String, ConceptDefinitionComponent> codesById = new HashMap<String, ConceptDefinitionComponent>();
    
    Map<String, ConceptSetComponent> includes = new HashMap<String, ConceptSetComponent>();

    CodeSystem cs = null;
    
    if (hasDefine) {
      cs = new CodeSystem();
      cs.setUrl("http://hl7.org/fhir/"+sheetName);
      valueSet.getCompose().addInclude().setSystem(cs.getUrl());
      CodeSystemConvertor.populate(cs, valueSet);
      cs.setVersion(version);
      cs.setCaseSensitive(true);
      cs.setContent(CodeSystemContentMode.COMPLETE);
      codeSystems.put(cs.getUrl(), cs);

      for (int row = 0; row < sheet.rows.size(); row++) {
        if (Utilities.noString(sheet.getColumn(row, "System"))) {

          ConceptDefinitionComponent cc = new ConceptDefinitionComponent(); 
          cc.setUserData("id", sheet.getColumn(row, "Id"));
          cc.setCode(sheet.getColumn(row, "Code"));
          if (codes.containsKey(cc.getCode()))
            throw new Exception("Duplicate Code "+cc.getCode());
          codes.put(cc.getCode(), cc);
          codesById.put(cc.getUserString("id"), cc);
          cc.setDisplay(sheet.getColumn(row, "Display"));
          if (sheet.getColumn(row, "Abstract").toUpperCase().equals("Y"))
          	CodeSystemUtilities.setNotSelectable(cs, cc);
          if (cc.hasCode() && !cc.hasDisplay())
            cc.setDisplay(Utilities.humanize(cc.getCode()));
          cc.setDefinition(sheet.getColumn(row, "Definition"));
          if (!Utilities.noString(sheet.getColumn(row, "Comment")))
            ToolingExtensions.addComment(cc, sheet.getColumn(row, "Comment"));
          cc.setUserData("v2", sheet.getColumn(row, "v2"));
          cc.setUserData("v3", sheet.getColumn(row, "v3"));
          for (String ct : sheet.columns) 
            if (ct.startsWith("Display:") && !Utilities.noString(sheet.getColumn(row, ct)))
              cc.addDesignation().setLanguage(ct.substring(8)).setValue(sheet.getColumn(row, ct));
          String parent = sheet.getColumn(row, "Parent");
          if (Utilities.noString(parent))
            cs.addConcept(cc);
          else if (parent.startsWith("#") && codesById.containsKey(parent.substring(1)))
            codesById.get(parent.substring(1)).addConcept(cc);
          else if (codes.containsKey(parent))
            codes.get(parent).addConcept(cc);
          else
            throw new Exception("Parent "+parent+" not resolved in "+sheetName);
        }
      }
    }

    for (int row = 0; row < sheet.rows.size(); row++) {
      if (!Utilities.noString(sheet.getColumn(row, "System"))) {
        String system = sheet.getColumn(row, "System");
        ConceptSetComponent t = includes.get(system);
        if (t == null) {
          if (!valueSet.hasCompose())
            valueSet.setCompose(new ValueSetComposeComponent());
          t = valueSet.getCompose().addInclude();
          t.setSystem(system);
          includes.put(system, t);
        }
        ConceptReferenceComponent cc = t.addConcept();
        cc.setCode(sheet.getColumn(row, "Code"));
        if (codes.containsKey(cc.getCode()))
          throw new Exception("Duplicate Code "+cc.getCode());
        codes.put(cc.getCode(), null);
        cc.setDisplay(sheet.getColumn(row, "Display"));
        if (!Utilities.noString(sheet.getColumn(row, "Definition")))
          ToolingExtensions.addDefinition(cc, sheet.getColumn(row, "Definition"));
        if (!Utilities.noString(sheet.getColumn(row, "Comment")))
          ToolingExtensions.addComment(cc, sheet.getColumn(row, "Comment"));
        cc.setUserDataINN("v2", sheet.getColumn(row, "v2"));
        cc.setUserDataINN("v3", sheet.getColumn(row, "v3"));
        for (String ct : sheet.columns) 
          if (ct.startsWith("Display:") && !Utilities.noString(sheet.getColumn(row, ct)))
            cc.addDesignation().setLanguage(ct.substring(8)).setValue(sheet.getColumn(row, ct));       
      }
    }

    if (!Utilities.noString(v2map)) {
      generateConceptMapV2(v2map, valueSet, cs);
    }
    if (!Utilities.noString(v3map)) {
      generateConceptMapV3(v3map, valueSet, cs);
    }
  }

  private void generateConceptMapV2(String v2map, ValueSet vs, CodeSystem cs) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setId("cm-"+vs.getId()+"-v2");
    cm.setUserData("path", cm.getId()+".html");
    cm.setUserData("generate", true);
    cm.setUrl("http://hl7.org/fhir/ConceptMap/" + cm.getId());
    cm.setName("v2 map for " + vs.getName());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ValueSetContactComponent cc : vs.getContact()) {
      ConceptMapContactComponent cd = cm.addContact();
      cd.setName(cc.getName());
      for (ContactPoint ccs : cc.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());
    cm.setStatus(vs.getStatus()); // until we publish DSTU, then .review
    cm.setDate(vs.getDate());
    cm.setSource(Factory.makeReference(vs.getUrl()));
    cm.setTarget(Factory.makeReference(v2map));
    if (cs != null) 
      processV2ConceptDefs(cm, cs.getUrl(), cs.getConcept());
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      for (ConceptReferenceComponent c : cc.getConcept()) {
        processV2Map(cm, cc.getSystem(), c.getCode(), c.getUserString("v2"));
      }
    maps.put(cm.getUrl(), cm);
  }

  private void processV2ConceptDefs(ConceptMap cm, String url, List<ConceptDefinitionComponent> list) throws Exception {
    for (ConceptDefinitionComponent c : list) {
      processV2Map(cm, url, c.getCode(), c.getUserString("v2"));
      processV2ConceptDefs(cm, url, c.getConcept());
    }
  }

  private void processV2Map(ConceptMap cm, String url, String code, String v2map) throws Exception {
    if (Utilities.noString(v2map)) 
      return;
    for (String m : v2map.split(",")) {
      // analyse m
      String[] n = m.split("\\(");
      String comm = (n.length > 1) ? n[1].substring(0, n[1].length() - 1) : null;
      n = n[0].split("\\.");
      if (n.length != 2)
        throw new Exception("Error processing v3 map value for "+cm.getName()+"."+code+" '"+m+"' - format should be CodeSystem.code (comment) - the comment bit is optional");
      String rel = n[0].substring(0, 1);
      String tbl = n[0].substring(1);
      String cd = n[1];

      ConceptMapGroupComponent grp = getGroup(cm, url, "http://hl7.org/fhir/v2/"+tbl);
      SourceElementComponent src = getSource(grp, code);
      TargetElementComponent tgt = src.addTarget();
      tgt.setCode(cd);
      tgt.setComments(comm);

      if (rel.equals("="))
        tgt.setEquivalence(ConceptMapEquivalence.EQUAL);
      else if (rel.equals("~"))
        tgt.setEquivalence(ConceptMapEquivalence.EQUIVALENT);
      else if (rel.equals(">"))
        tgt.setEquivalence(ConceptMapEquivalence.WIDER);
      else if (rel.equals("<")) {
        tgt.setEquivalence(ConceptMapEquivalence.NARROWER);
        if (!tgt.hasComments())
          throw new Exception("Missing comments for narrower match on "+cm.getName()+"/"+code);
      } else
        throw new Exception("Unable to understand relationship character "+rel);
    }    
  }

  private ConceptMapGroupComponent getGroup(ConceptMap map, String srcs, String tgts) {
    for (ConceptMapGroupComponent grp : map.getGroup()) {
      if (grp.getSource().equals(srcs) && grp.getTarget().equals(tgts))
        return grp;
    }
    ConceptMapGroupComponent grp = map.addGroup(); 
    grp.setSource(srcs);
    grp.setTarget(tgts);
    return grp;
  }

  private SourceElementComponent getSource(ConceptMapGroupComponent grp, String code) {
    for (SourceElementComponent s : grp.getElement()) {
      if (code.equals(s.getCode()))
        return s;
    }
    return grp.addElement().setCode(code);
  }

  private void generateConceptMapV3(String v3map, ValueSet vs, CodeSystem cs) throws Exception {
    if (!v3map.startsWith("http://hl7.org/fhir/ValueSet/v3-"))
      v3map = "http://hl7.org/fhir/ValueSet/v3-"+v3map;

    ConceptMap cm = new ConceptMap();
    cm.setId("cm-"+vs.getId()+"-v3");
    cm.setUserData("path", cm.getId()+".html");
    cm.setUserData("generate", true);
    cm.setUrl("http://hl7.org/fhir/ConceptMap/" + cm.getId());
    cm.setName("v3 map for " + vs.getName());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ValueSetContactComponent cc : vs.getContact()) {
      ConceptMapContactComponent cd = cm.addContact();
      cd.setName(cc.getName());
      for (ContactPoint ccs : cc.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());
    cm.setStatus(vs.getStatus()); // until we publish DSTU, then .review
    cm.setDate(vs.getDate());
    cm.setSource(Factory.makeReference(vs.getUrl()));
    cm.setTarget(Factory.makeReference(v3map));
    if (cs != null) 
      processV3ConceptDefs(cm, cs.getUrl(), cs.getConcept());
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      for (ConceptReferenceComponent c : cc.getConcept()) {
        processV3Map(cm, cc.getSystem(), c.getCode(), c.getUserString("v2"));
      }
    maps.put(cm.getUrl(), cm);
  }

  private void processV3ConceptDefs(ConceptMap cm, String url, List<ConceptDefinitionComponent> list) throws Exception {
    for (ConceptDefinitionComponent c : list) {
      processV3Map(cm, url, c.getCode(), c.getUserString("v3"));
      processV3ConceptDefs(cm, url, c.getConcept());
    }
  }

  private void processV3Map(ConceptMap cm, String url, String code, String v3map) throws Exception {
    if (Utilities.noString(v3map)) 
      return;
    for (String m : v3map.split(",")) {
      // analyse m
      String[] n = m.split("\\(");
      String comm = (n.length > 1) ? n[1].substring(0, n[1].length() - 1) : null;
      n = n[0].split("\\.");
      if (n.length != 2)
        throw new Exception("Error processing v3 map value for "+cm.getName()+"."+code+" '"+m+"' - format should be CodeSystem.code (comment) - the comment bit is optional");
      String rel = null;
      String tbl = n[0];
      if (Utilities.existsInList(n[0].substring(0,1), "=", "~", ">", "<")) {
        rel = n[0].substring(0, 1);
        tbl = n[0].substring(1);
      }
      String cd = n[1];

      ConceptMapGroupComponent grp = getGroup(cm, url, "http://hl7.org/fhir/v3/"+tbl);
      SourceElementComponent src = getSource(grp, code);
      TargetElementComponent tgt = src.addTarget();
      tgt.setCode(cd);
      tgt.setComments(comm);

      if (rel == null || rel.equals("="))
        tgt.setEquivalence(ConceptMapEquivalence.EQUAL);
      else if (rel.equals("~"))
        tgt.setEquivalence(ConceptMapEquivalence.EQUIVALENT);
      else if (rel.equals("<"))
        tgt.setEquivalence(ConceptMapEquivalence.WIDER);
      else if (rel.equals(">")) {
        tgt.setEquivalence(ConceptMapEquivalence.NARROWER);
        if (!tgt.hasComments())
          throw new Exception("Missing comments for narrower match on "+cm.getName()+"/"+code);
      } else
        throw new Exception("Unable to understand relationship character "+rel);
    }    
  }
}
