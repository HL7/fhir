package org.hl7.fhir.instance.utils;

import java.net.URI;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.ValueSet.CodeSelectionMode;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class NarrativeGenerator {

  public void generate(ValueSet vs, Map<String, AtomEntry> codeSystems) throws Exception {
    XhtmlNode x = new XhtmlNode();
    x.setNodeType(NodeType.Element);
    x.setName("div");
    if (vs.getExpansion() != null)
      throw new Exception("Error: should not encounter value set expansion at this point");
    if (vs.getCompose() != null) 
      generateComposition(x, vs, codeSystems);
    if (vs.getDefine() != null)
      generateDefinition(x, vs);
    vs.getText().setDiv(x);
    vs.getText().setStatusSimple(NarrativeStatus.generated);
  }

  private void generateDefinition(XhtmlNode x, ValueSet vs) {
    XhtmlNode h = x.addTag("h2");
    h.addText(vs.getNameSimple());
    XhtmlNode p = x.addTag("p");
    p.addText(vs.getDescriptionSimple());
    p = x.addTag("p");
    p.addText("This value set defines it's own terms in the system "+vs.getDefine().getSystemSimple());
    XhtmlNode t = x.addTag("table");
    addTableHeaderRowStandard(t);
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      addDefineRowToTable(t, c, 0);
    }    
  }

  private void addTableHeaderRowStandard(XhtmlNode t) {
    XhtmlNode tr = t.addTag("tr");
    XhtmlNode td = tr.addTag("td");
    XhtmlNode b = td.addTag("b");
    b.addText("Code");
    td = tr.addTag("td");
    b = td.addTag("b");
    b.addText("Display");
    td = tr.addTag("td");
    b = td.addTag("b");
    b.addText("Definition");
  }

  private void addDefineRowToTable(XhtmlNode t, ValueSetDefineConceptComponent c, int i) {
    XhtmlNode tr = t.addTag("tr");
    XhtmlNode td = tr.addTag("td");
    String s = Utilities.padLeft("", '.', i*2);
    td.addText(s+c.getCodeSimple());
    td = tr.addTag("td");
    td.addText(c.getDisplaySimple());
    td = tr.addTag("td");
    td.addText(c.getDefinitionSimple());
    for (ValueSetDefineConceptComponent cc : c.getConcept()) {
      addDefineRowToTable(t, cc, i+1);
    }    
  }


  private void generateComposition(XhtmlNode x, ValueSet vs, Map<String, AtomEntry> codeSystems) {
    XhtmlNode h = x.addTag("h2");
    h.addText(vs.getNameSimple());
    XhtmlNode p = x.addTag("p");
    p.addText(vs.getDescriptionSimple());
    p = x.addTag("p");
    p.addText("This value set includes terms defined in other code systems, using the following rules:");
    XhtmlNode ul = x.addTag("ul");
    XhtmlNode li;
    for (Uri imp : vs.getCompose().getImport()) {
      li = ul.addTag("li");
      li.addText("Import all the codes that are part of "+imp.toString());
    }
    for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
      genInclude(ul, inc, "Include", codeSystems);      
    }
    for (ConceptSetComponent exc : vs.getCompose().getExclude()) {
      genInclude(ul, exc, "Exclude", codeSystems);      
    }
  }

  private void genInclude(XhtmlNode ul, ConceptSetComponent inc, String type, Map<String, AtomEntry> codeSystems) {
    XhtmlNode li;
    li = ul.addTag("li");
    AtomEntry e = codeSystems.get(inc.getSystemSimple().toString());
    
    if (inc.getModeSimple() == CodeSelectionMode.system) { 
      li.addText(type+" all codes defined in ");
      addCsRef(inc, li, e);
    } else { 
      
      if (inc.getModeSimple() == CodeSelectionMode.children)
        li.addText(type+" immediate Children of the following codes as defined in ");
      else if (inc.getModeSimple() == CodeSelectionMode.descendants)
        li.addText(type+" all descendents of the following codes (but not the codes themselves) as defined in ");
      else if (inc.getModeSimple() == CodeSelectionMode.all)
        li.addText(type+" these codes and all their descendents as defined in ");
      else // if (inc.getModeSimple() == CodeSelectionMode.code)
        li.addText(type+" these codes as defined in ");
      addCsRef(inc, li, e);
      
      if (inc.getCode().size() > 0) {
        XhtmlNode t = li.addTag("table");
        addTableHeaderRowStandard(t);
        for (Code c : inc.getCode()) {
          XhtmlNode tr = t.addTag("tr");
          XhtmlNode td = tr.addTag("td");
          td.addText(c.getValue());         
          ValueSetDefineConceptComponent cc = getConceptForCode(e, c.getValue());
          if (cc != null) {
            td = tr.addTag("td");
            td.addText(cc.getDisplaySimple());
            td = tr.addTag("td");
            td.addText(cc.getDefinitionSimple());
          }
        }
      }
    }
  }

  private ValueSetDefineConceptComponent getConceptForCode(AtomEntry e, String code) {
    if (e == null)
      return null;
    ValueSet vs = (ValueSet) e.getResource();
    if (vs.getDefine() == null)
      return null;
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      ValueSetDefineConceptComponent v = getConceptForCode(c, code);   
      if (v != null)
        return v;
    }
    return null;
  }
  
  
  
  private ValueSetDefineConceptComponent getConceptForCode(ValueSetDefineConceptComponent c, String code) {
    if (code.equals(c.getCodeSimple()))
      return c;
    for (ValueSetDefineConceptComponent cc : c.getConcept()) {
      ValueSetDefineConceptComponent v = getConceptForCode(cc, code);   
      if (v != null)
        return v;
    }
    return null;
  }

  private void addCsRef(ConceptSetComponent inc, XhtmlNode li, AtomEntry cs) {
    if (cs != null && cs.getLinks().get("self") != null) {
      XhtmlNode a = li.addTag("a");
      a.setAttribute("href", cs.getLinks().get("self"));
      a.addText(inc.getSystemSimple().toString());
    } else 
      li.addText(inc.getSystemSimple().toString());
  }


}
