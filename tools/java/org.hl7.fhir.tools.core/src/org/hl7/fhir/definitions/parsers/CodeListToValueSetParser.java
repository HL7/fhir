package org.hl7.fhir.definitions.parsers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.igtools.spreadsheets.CodeSystemConvertor;
import org.hl7.fhir.r5.context.MetadataResourceManager;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.CodeSystemContentMode;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.Constants;
import org.hl7.fhir.r5.model.ConceptMap.ConceptMapGroupComponent;
import org.hl7.fhir.r5.model.ConceptMap.SourceElementComponent;
import org.hl7.fhir.r5.model.ConceptMap.TargetElementComponent;
import org.hl7.fhir.r5.model.ContactDetail;
import org.hl7.fhir.r5.model.ContactPoint;
import org.hl7.fhir.r5.model.Enumerations.ConceptMapRelationship;
import org.hl7.fhir.r5.model.Factory;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.model.ValueSet.ValueSetComposeComponent;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xls.XLSXmlParser.Sheet;

public class CodeListToValueSetParser {

  private Sheet sheet;
  private ValueSet valueSet;
  private String version;
  private String sheetName;
  private MetadataResourceManager<CodeSystem> codeSystems;
  private MetadataResourceManager<ConceptMap> maps;

  public CodeListToValueSetParser(Sheet sheet, String sheetName, ValueSet valueSet, String version, MetadataResourceManager<CodeSystem> codeSystems, MetadataResourceManager<ConceptMap> maps) throws Exception {
    super();
    this.sheet = sheet;
    this.sheetName = sheetName;
    this.valueSet = valueSet;
    this.version = version;
    this.codeSystems = codeSystems;
    this.maps = maps;

  }

  public void execute(String v2map, String v3map, boolean utg) throws Exception {
    boolean hasDefine = false;
    for (int row = 0; row < sheet.rows.size(); row++) {

      hasDefine = hasDefine || Utilities.noString(sheet.getColumn(row, "System"));
    }

    Map<String, ConceptDefinitionComponent> codes = new HashMap<String, ConceptDefinitionComponent>();
    Map<String, ConceptDefinitionComponent> codesById = new HashMap<String, ConceptDefinitionComponent>();
    
    Map<String, ConceptSetComponent> includes = new HashMap<String, ConceptSetComponent>();

    CodeSystem cs = null;
    
    if (hasDefine) {
      cs = new CodeSystem();
      if (utg)
        cs.setUrl("http://terminology.hl7.org/CodeSystem/"+sheetName);
      else
        cs.setUrl("http://hl7.org/fhir/"+sheetName);
      if (!valueSet.hasCompose())
        valueSet.setCompose(new ValueSetComposeComponent());
      valueSet.getCompose().addInclude().setSystem(cs.getUrl());
      cs.setVersion(version);
      cs.setCaseSensitive(true);
      cs.setContent(CodeSystemContentMode.COMPLETE);
      codeSystems.see(cs);

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
          cc.setDefinition(Utilities.appendPeriod(sheet.getColumn(row, "Definition")));
          if (!Utilities.noString(sheet.getColumn(row, "Comment")))
            ToolingExtensions.addCSComment(cc, sheet.getColumn(row, "Comment"));
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
          ToolingExtensions.addVSComment(cc, sheet.getColumn(row, "Comment"));
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
    if (cs != null)
      CodeSystemConvertor.populate(cs, valueSet);
  }

  private void generateConceptMapV2(String v2map, ValueSet vs, CodeSystem cs) throws Exception {
    ConceptMap cm = new ConceptMap();
    cm.setId("cm-"+vs.getId()+"-v2");
    cm.setVersion(Constants.VERSION);
    cm.setUserData("path", cm.getId()+".html");
    cm.setUserData("generate", true);
    cm.setUrl("http://hl7.org/fhir/ConceptMap/" + cm.getId());
    cm.setName("v2."+vs.getName());
    cm.setTitle("v2 map for " + vs.present());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ContactDetail cc : vs.getContact()) {
      ContactDetail cd = cm.addContact();
      cd.setName(cc.getName());
      for (ContactPoint ccs : cc.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());
    cm.setStatus(vs.getStatus()); // until we publish DSTU, then .review
    cm.setDate(vs.getDate());
    cm.setSource(Factory.newCanonical(vs.getUrl()));
    cm.setTarget(Factory.newCanonical(v2map));
    if (cs != null) 
      processV2ConceptDefs(cm, cs.getUrl(), cs.getConcept());
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      for (ConceptReferenceComponent c : cc.getConcept()) {
        processV2Map(cm, cc.getSystem(), c.getCode(), c.getUserString("v2"));
      }
    maps.see(cm);
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

      ConceptMapGroupComponent grp = getGroup(cm, url, "http://terminology.hl7.org/CodeSystem/v2-"+tbl);
      SourceElementComponent src = getSource(grp, code);
      TargetElementComponent tgt = src.addTarget();
      tgt.setCode(cd.trim());
      tgt.setComment(comm);

      if (rel.equals("="))
        tgt.setRelationship(ConceptMapRelationship.EQUIVALENT);
      else if (rel.equals("~"))
        tgt.setRelationship(ConceptMapRelationship.EQUIVALENT);
      else if (rel.equals(">"))
        tgt.setRelationship(ConceptMapRelationship.BROADER);
      else if (rel.equals("<")) {
        tgt.setRelationship(ConceptMapRelationship.NARROWER);
        if (!tgt.hasComment())
          throw new Exception("Missing comment for narrower match on "+cm.getName()+"/"+code);
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
    if (!v3map.startsWith("http://terminology.hl7.org/ValueSet/v3-"))
      v3map = "http://terminology.hl7.org/ValueSet/v3-"+v3map;

    ConceptMap cm = new ConceptMap();
    cm.setVersion(Constants.VERSION);
    cm.setId("cm-"+vs.getId()+"-v3");
    cm.setUserData("path", cm.getId()+".html");
    cm.setUserData("generate", true);
    cm.setUrl("http://hl7.org/fhir/ConceptMap/" + cm.getId());
    cm.setName("v3." + vs.getName());
    cm.setTitle("v3 map for " + vs.present());
    cm.setPublisher("HL7 (FHIR Project)");
    for (ContactDetail cc : vs.getContact()) {
      ContactDetail cd = cm.addContact();
      cd.setName(cc.getName());
      for (ContactPoint ccs : cc.getTelecom())
        cd.addTelecom(ccs.copy());
    }
    cm.setCopyright(vs.getCopyright());
    cm.setStatus(vs.getStatus()); // until we publish DSTU, then .review
    cm.setDate(vs.getDate());
    cm.setSource(Factory.newCanonical(vs.getUrl()));
    cm.setTarget(Factory.newCanonical(v3map));
    if (cs != null) 
      processV3ConceptDefs(cm, cs.getUrl(), cs.getConcept());
    for (ConceptSetComponent cc : vs.getCompose().getInclude())
      for (ConceptReferenceComponent c : cc.getConcept()) {
        processV3Map(cm, cc.getSystem(), c.getCode(), c.getUserString("v2"));
      }
    maps.see(cm);
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

      ConceptMapGroupComponent grp = getGroup(cm, url, "http://terminology.hl7.org/CodeSystem/v3-"+tbl);
      SourceElementComponent src = getSource(grp, code);
      TargetElementComponent tgt = src.addTarget();
      tgt.setCode(cd.trim());
      tgt.setComment(comm);

      if (rel == null || rel.equals("="))
        tgt.setRelationship(ConceptMapRelationship.EQUIVALENT);
      else if (rel.equals("~"))
        tgt.setRelationship(ConceptMapRelationship.EQUIVALENT);
      else if (rel.equals("<"))
        tgt.setRelationship(ConceptMapRelationship.BROADER);
      else if (rel.equals(">")) {
        tgt.setRelationship(ConceptMapRelationship.NARROWER);
        if (!tgt.hasComment())
          throw new Exception("Missing comment for narrower match on "+cm.getName()+"/"+code);
      } else
        throw new Exception("Unable to understand relationship character "+rel);
    }    
  }
}
