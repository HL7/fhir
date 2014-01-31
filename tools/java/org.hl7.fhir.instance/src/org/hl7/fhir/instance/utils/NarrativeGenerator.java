package org.hl7.fhir.instance.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.instance.model.Code;
import org.hl7.fhir.instance.model.CodeableConcept;
import org.hl7.fhir.instance.model.Coding;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Identifier;
import org.hl7.fhir.instance.model.Period;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.ConceptMap.ConceptMapConceptComponent;
import org.hl7.fhir.instance.model.ConceptMap.ConceptMapConceptMapComponent;
import org.hl7.fhir.instance.model.ConceptMap.OtherConceptComponent;
import org.hl7.fhir.instance.model.Conformance;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestOperationComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceComponent;
import org.hl7.fhir.instance.model.Conformance.ConformanceRestResourceOperationComponent;
import org.hl7.fhir.instance.model.Conformance.SystemRestfulOperation;
import org.hl7.fhir.instance.model.Conformance.TypeRestfulOperation;
import org.hl7.fhir.instance.model.Contact;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.DateTime;
import org.hl7.fhir.instance.model.Element;
import org.hl7.fhir.instance.model.Extension;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.OperationOutcome;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.OperationOutcome.OperationOutcomeIssueComponent;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Property;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.instance.model.ValueSet;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.instance.model.ValueSet.ConceptSetFilterComponent;
import org.hl7.fhir.instance.model.ValueSet.FilterOperator;
import org.hl7.fhir.instance.model.ValueSet.ValueSetDefineConceptComponent;
import org.hl7.fhir.instance.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class NarrativeGenerator {

  private String prefix;
  private ConceptLocator conceptLocator;
  private Map<String, AtomEntry<ValueSet>> codeSystems;
  private Map<String, AtomEntry<ValueSet>> valueSets;
  private Map<String, AtomEntry<ConceptMap>> maps;
  
  public NarrativeGenerator(String prefix, ConceptLocator conceptLocator, Map<String, AtomEntry<ValueSet>> codeSystems, Map<String, AtomEntry<ValueSet>> valueSets, Map<String, AtomEntry<ConceptMap>> maps) {
    super();
    this.prefix = prefix;
    this.conceptLocator = conceptLocator;
    this.codeSystems = codeSystems;
    this.valueSets = valueSets;
    this.maps = maps;
  }

  public void generate(Resource r,  Profile profile) throws Exception {
    if (r instanceof ConceptMap) {
      generate((ConceptMap) r);
    } else if (r instanceof ValueSet) {
      generate((ValueSet) r);
    } else if (r instanceof OperationOutcome) {
      generate((OperationOutcome) r);
    } else if (r instanceof Conformance) {
      generate((Conformance) r);  
    } else {
      generateByProfile(r, profile);
    }
  }
  
  private void generateByProfile(Resource r, Profile profile) throws Exception {
    if (r.hasModifierExtensions())
      throw new Exception("Unable to generate narrative for resource of type "+r.getResourceType().toString()+" because it has modifier extensions");
    ProfileStructureComponent ps = getByName(profile, r.getResourceType().toString());
    
    XhtmlNode x = new XhtmlNode(NodeType.Element, "div");
    x.addTag("p").addTag("b").addText("Generated Narrative");
    try {
      generateByProfile(r, r, ps.getElement(), ps.getElement().get(0), getChildrenForPath(ps.getElement(), r.getResourceType().toString()), x, r.getResourceType().toString());
    } catch (Exception e) {
      x.addTag("p").addTag("b").setAttribute("style", "color: maroon").addText("Exception generating Narrative: "+e.getMessage());
    }
    inject(r, x,  NarrativeStatus.generated);
  }

  private void generateByProfile(Resource res, Element e, List<ElementComponent> allElements, ElementComponent defn, List<ElementComponent> children,  XhtmlNode x, String path) throws Exception {
    if (children.isEmpty()) {
      renderLeaf(res, e, defn, x, false);
    } else {
      for (Property p : e.children()) {
        ElementComponent child = getElementDefinition(children, path+"."+p.getName());
        List<ElementComponent> grandChildren = getChildrenForPath(allElements, path+"."+p.getName());
        for (Element v : p.getValues()) {
          if (v instanceof org.hl7.fhir.instance.model.Type) {
            XhtmlNode para = x.addTag("p");
            para.addTag("b").addText(p.getName());
            para.addText(": ");
            // generateByProfile(v, elements, child, para, path+"."+p.getName());
            renderLeaf(res, v, child, para, false);
          } else if (canDoTable(grandChildren)) {
            x.addTag("h3").addText(Utilities.capitalize(Utilities.camelCase(Utilities.pluralizeMe(p.getName()))));
            XhtmlNode tbl = x.addTag("table").setAttribute("class", "grid");
            addColumnHeadings(tbl.addTag("tr"), grandChildren);
          } else {
            XhtmlNode bq = x.addTag("blockquote");
          bq.addTag("p").addTag("b").addText(p.getName());
            generateByProfile(res, v, allElements, child, grandChildren, bq, path+"."+p.getName());
        }
      }
    }
  }
  }

  
  private void addColumnHeadings(XhtmlNode tr, List<ElementComponent> grandChildren) {
    for (ElementComponent e : grandChildren) 
      tr.addTag("td").addTag("b").addText(Utilities.capitalize(tail(e.getPathSimple())));
  }

  private String tail(String path) {
    return path.substring(path.lastIndexOf(".")+1);
  }

  private boolean canDoTable(List<ElementComponent> grandChildren) {
    boolean result = true;
    for (ElementComponent e : grandChildren) {
      if (!isPrimitive(e))
        return false;
    }
    return result;
  }

  private boolean isPrimitive(ElementComponent e) {
    //we can tell if e is a primitive because it has types
    return !e.getDefinition().getType().isEmpty();
  }
  
  private ElementComponent getElementDefinition(List<ElementComponent> elements, String path) {
    for (ElementComponent element : elements)
      if (element.getPathSimple().equals(path))
        return element;      
    return null;
  }

  private void renderLeaf(Resource res, Element e, ElementComponent defn, XhtmlNode x, boolean title) throws Exception {
    if (e instanceof String_)
      x.addText(((String_) e).getValue());
    else if (e instanceof DateTime)
      x.addText(((DateTime) e).getValue().toHumanDisplay());
    else if (e instanceof Enumeration)
      x.addText(((Enumeration) e).getValue().toString()); // todo: look up a display name if there is one
    else if (e instanceof Boolean)
      x.addText(((Boolean) e).getValue().toString());
    else if (e instanceof CodeableConcept) {
      renderCodeableConcept((CodeableConcept) e, x);
    } else if (e instanceof ResourceReference) {
      ResourceReference r = (ResourceReference) e;
      if (r.getDisplay() != null)
        x.addText(r.getDisplaySimple());
      else if (r.getReference() != null) {
        Resource resT = getResourceByReference(res, r.getReferenceSimple()); 
        if (resT != null) {
          x.addText(displayResource(resT));          
        } else {
          x.addText(((ResourceReference) e).getReferenceSimple());
        }
      } else 
        x.addText("null");
    } else if (e instanceof Identifier) {
      x.addText(((Identifier) e).getValueSimple());
    } else if (e instanceof Period) {
      Period p = (Period) e;
      x.addText((p.getStartSimple() == null ? "??" : p.getStartSimple().toString()) + (p.getEndSimple() == null ? "(ongoing)" : p.getEndSimple().toString()));
    } else 
      throw new Exception("type "+e.getClass().getName()+" not handled yet");      
  }

  private String displayResource(Resource resT) {
    return "todo";
  }

  private Resource getResourceByReference(Resource res, String referenceSimple) {
    return null;
  }

  private void renderCodeableConcept(CodeableConcept cc, XhtmlNode x) {
    if (!Utilities.noString(cc.getTextSimple()))
      x.addText(cc.getTextSimple());
    else
      x.addText("todo");
  }

  private List<ElementComponent> getChildrenForPath(List<ElementComponent> elements, String path) {
    List<ElementComponent> results = new ArrayList<Profile.ElementComponent>();
    for (ElementComponent e : elements) {
      if (e.getPathSimple().startsWith(path+".") && !e.getPathSimple().substring(path.length()+1).contains(".") && !(e.getPathSimple().endsWith(".extension") || e.getPathSimple().endsWith(".modifierExtension")))
        results.add(e);
    }
    return results;
  }

  private ProfileStructureComponent getByName(Profile profile, String name) throws Exception {
    for (ProfileStructureComponent t : profile.getStructure()) {
      if (t.getTypeSimple().equals(name)) {
        return t;
      }
    }
    throw new Exception("unable to find entry point for "+name);
  }

  public void generate(ConceptMap cm) throws Exception {
    XhtmlNode x = new XhtmlNode(NodeType.Element, "div");
    x.addTag("h2").addText(cm.getNameSimple()+" ("+cm.getIdentifierSimple()+")");

    XhtmlNode p = x.addTag("p");
    p.addText("Mapping from ");
    AddVsRef(cm.getSource().getReferenceSimple(), p);
    p.addText(" to ");
    AddVsRef(cm.getTarget().getReferenceSimple(), p);
    
    p = x.addTag("p");
    if (cm.getExperimentalSimple())
      p.addText(Utilities.capitalize(cm.getStatusSimple().toString())+" (not intended for production usage). ");
    else
      p.addText(Utilities.capitalize(cm.getStatusSimple().toString())+". ");
    p.addText("Published on "+cm.getDateSimple().toHumanDisplay()+" by "+cm.getPublisherSimple());
    if (!cm.getTelecom().isEmpty()) {
      p.addText(" (");
      boolean first = true;
      for (Contact c : cm.getTelecom()) {
        if (first) 
          first = false;
        else
          p.addText(", ");
        addTelecom(p, c);
      }
      p.addText(")");
    }
    p.addText(". ");
    p.addText(cm.getCopyrightSimple());
    if (!Utilities.noString(cm.getDescriptionSimple())) 
      x.addTag("p").addText(cm.getDescriptionSimple());

    x.addTag("br");

    if (!cm.getConcept().isEmpty()) {
      ConceptMapConceptComponent cc = cm.getConcept().get(0);
      String src = cc.getSystemSimple();
      boolean comments = false;
      boolean ok = cc.getMap().size() == 1;
      Map<String, HashSet<String>> sources = new HashMap<String, HashSet<String>>();
      sources.put("code", new HashSet<String>());
      Map<String, HashSet<String>> targets = new HashMap<String, HashSet<String>>();
      targets.put("code", new HashSet<String>());
      if (ok) {
        String dst = cc.getMap().get(0).getSystemSimple();
        for (ConceptMapConceptComponent ccl : cm.getConcept()) {
          ok = ok && src.equals(ccl.getSystemSimple()) && ccl.getMap().size() == 1 && dst.equals(ccl.getMap().get(0).getSystemSimple()) && ccl.getDependsOn().isEmpty() && ccl.getMap().get(0).getProduct().isEmpty();
          if (ccl.getSystemSimple() != null)
            sources.get("code").add(ccl.getSystemSimple());
          for (OtherConceptComponent d : ccl.getDependsOn()) {
            if (!sources.containsKey(d.getConceptSimple()))
              sources.put(d.getConceptSimple(), new HashSet<String>());
            sources.get(d.getConceptSimple()).add(d.getSystemSimple());
          }
          for (ConceptMapConceptMapComponent ccm : ccl.getMap()) {
            comments = comments || !Utilities.noString(ccm.getCommentsSimple());
            if (ccm.getSystemSimple() != null)
              targets.get("code").add(ccm.getSystemSimple());
            for (OtherConceptComponent d : ccm.getProduct()) {
              if (!targets.containsKey(d.getConceptSimple()))
                targets.put(d.getConceptSimple(), new HashSet<String>());
              targets.get(d.getConceptSimple()).add(d.getSystemSimple());
            }
            
          }
        }
      }
      
      String display;
      if (ok) {
        // simple 
        XhtmlNode tbl = x.addTag("table").setAttribute("class", "grid");
        XhtmlNode tr = tbl.addTag("tr");
        tr.addTag("td").addTag("b").addText("Source Code");
        tr.addTag("td").addTag("b").addText("Equivalence");
        tr.addTag("td").addTag("b").addText("Destination Code");
        if (comments)
          tr.addTag("td").addTag("b").addText("Comments");
        for (ConceptMapConceptComponent ccl : cm.getConcept()) {
          tr = tbl.addTag("tr");
          XhtmlNode td = tr.addTag("td");
          td.addText(ccl.getCodeSimple());
          display = getDisplayForConcept(ccl.getSystemSimple(), ccl.getCodeSimple());
          if (display != null)
            td.addText(" ("+display+")");
          ConceptMapConceptMapComponent ccm = ccl.getMap().get(0); 
          tr.addTag("td").addText(ccm.getEquivalenceSimple().toString());
          td = tr.addTag("td");
          td.addText(ccm.getCodeSimple());
          display = getDisplayForConcept(ccm.getSystemSimple(), ccm.getCodeSimple());
          if (display != null)
            td.addText(" ("+display+")");
          if (comments)
            tr.addTag("td").addText(ccm.getCommentsSimple());
        }
      } else {
        XhtmlNode tbl = x.addTag("table").setAttribute("class", "grid");
        XhtmlNode tr = tbl.addTag("tr");
        XhtmlNode td;
        tr.addTag("td").setAttribute("colspan", Integer.toString(sources.size())).addTag("b").addText("Source Concept");
        tr.addTag("td").addTag("b").addText("Equivalence");
        tr.addTag("td").setAttribute("colspan", Integer.toString(targets.size())).addTag("b").addText("Destination Concept");
        if (comments)
          tr.addTag("td").addTag("b").addText("Comments");
        tr = tbl.addTag("tr");
        if (sources.get("code").size() == 1) 
          tr.addTag("td").addTag("b").addText("Code "+sources.get("code").toString()+"");
        else 
          tr.addTag("td").addTag("b").addText("Code");
        for (String s : sources.keySet()) {
          if (!s.equals("code")) {
            if (sources.get(s).size() == 1)
              tr.addTag("td").addTag("b").addText(getDescForConcept(s) +" "+sources.get(s).toString());
            else 
              tr.addTag("td").addTag("b").addText(getDescForConcept(s));
          }
        }
        tr.addTag("td");
        if (targets.get("code").size() == 1) 
          tr.addTag("td").addTag("b").addText("Code "+targets.get("code").toString());
        else 
          tr.addTag("td").addTag("b").addText("Code");
        for (String s : targets.keySet()) {
          if (!s.equals("code")) {
            if (targets.get(s).size() == 1)
              tr.addTag("td").addTag("b").addText(getDescForConcept(s) +" "+targets.get(s).toString()+"");
            else 
              tr.addTag("td").addTag("b").addText(getDescForConcept(s));
          }
        }
        if (comments)
          tr.addTag("td");
        
        for (ConceptMapConceptComponent ccl : cm.getConcept()) {
          tr = tbl.addTag("tr");
          td = tr.addTag("td");
          if (sources.get("code").size() == 1) 
            td.addText(ccl.getCodeSimple());
          else
            td.addText(ccl.getSystemSimple()+" / "+ccl.getCodeSimple());
          display = getDisplayForConcept(ccl.getSystemSimple(), ccl.getCodeSimple());
          if (display != null)
            td.addText(" ("+display+")");
          
          for (String s : sources.keySet()) {
            if (!s.equals("code")) { 
              td = tr.addTag("td");
              td.addText(getCode(ccl.getDependsOn(), s, sources.get(s).size() != 1));
              display = getDisplay(ccl.getDependsOn(), s);
              if (display != null)
                td.addText(" ("+display+")");
            }
          }
          ConceptMapConceptMapComponent ccm = ccl.getMap().get(0); 
          tr.addTag("td").addText(ccm.getEquivalenceSimple().toString());
          td = tr.addTag("td");
          if (targets.get("code").size() == 1) 
            td.addText(ccm.getCodeSimple());
          else
            td.addText(ccm.getSystemSimple()+" / "+ccm.getCodeSimple());
          display = getDisplayForConcept(ccm.getSystemSimple(), ccm.getCodeSimple());
          if (display != null)
            td.addText(" ("+display+")");

          for (String s : targets.keySet()) {
            if (!s.equals("code")) { 
              td = tr.addTag("td");
              td.addText(getCode(ccm.getProduct(), s, targets.get(s).size() != 1));
              display = getDisplay(ccm.getProduct(), s);
              if (display != null)
                td.addText(" ("+display+")");
            }
          }
          if (comments)
            tr.addTag("td").addText(ccm.getCommentsSimple());
        }
      }
    }
   
    inject(cm, x, NarrativeStatus.generated);
  }
  
  
  
  private void inject(Resource r, XhtmlNode x, NarrativeStatus status) {
    if (r.getText() == null)
      r.setText(new Narrative());
    if (r.getText().getDiv() == null || r.getText().getDiv().getChildNodes().isEmpty()) {
      r.getText().setDiv(x);
      r.getText().setStatusSimple(status);
    } else {
      XhtmlNode n = r.getText().getDiv();
      n.addTag("hr");
      n.getChildNodes().addAll(x.getChildNodes());
    }
  }

  private String getDisplay(List<OtherConceptComponent> list, String s) {
    for (OtherConceptComponent c : list) {
      if (s.equals(c.getConceptSimple()))
        return getDisplayForConcept(c.getSystemSimple(), c.getCodeSimple());
    }
    return null;
  }

  private String getDisplayForConcept(String system, String code) {
    if (code == null)
      return null;
    if (codeSystems.containsKey(system)) {
      ValueSet vs = codeSystems.get(system).getResource();
      return getDisplayForConcept(code, vs.getDefine().getConcept(), vs.getDefine().getCaseSensitiveSimple());
    } else if (conceptLocator != null) {
      ValueSetDefineConceptComponent cl = conceptLocator.locate(system, code);
      return cl == null ? null : cl.getDisplaySimple();
    } else
      return null;
  }

  private String getDisplayForConcept(String code, List<ValueSetDefineConceptComponent> concept, boolean cs) {
    for (ValueSetDefineConceptComponent t : concept) {
      if ((cs && code.equals(t.getCodeSimple()) || (!cs && code.equalsIgnoreCase(t.getCodeSimple()))))
          return t.getDisplaySimple();
      String disp = getDisplayForConcept(code, t.getConcept(), cs);
      if (disp != null)
        return disp;
    }
    return null;
  }

  private String getDescForConcept(String s) {
    if (s.startsWith("http://hl7.org/fhir/v2/element/"))
        return "v2 "+s.substring("http://hl7.org/fhir/v2/element/".length()); 
    return s;
  }

  private String getCode(List<OtherConceptComponent> list, String s, boolean withSystem) {
    for (OtherConceptComponent c : list) {
      if (s.equals(c.getConceptSimple()))
        if (withSystem)
          return c.getSystemSimple()+" / "+c.getCodeSimple();
        else
          return c.getCodeSimple();
    }
    return null;
  }

  private void addTelecom(XhtmlNode p, Contact c) {
    if (c.getSystemSimple() == ContactSystem.phone) {
      p.addText("Phone: "+c.getValueSimple());
    } else if (c.getSystemSimple() == ContactSystem.fax) {
      p.addText("Fax: "+c.getValueSimple());
    } else if (c.getSystemSimple() == ContactSystem.email) {
      p.addTag("a").setAttribute("href",  "mailto:"+c.getValueSimple()).addText(c.getValueSimple());
    } else if (c.getSystemSimple() == ContactSystem.url) {
      if (c.getValueSimple().length() > 30)
        p.addTag("a").setAttribute("href", c.getValueSimple()).addText(c.getValueSimple().substring(0, 30)+"...");
      else
        p.addTag("a").setAttribute("href", c.getValueSimple()).addText(c.getValueSimple());
    }    
  }

  /**
   * This generate is optimised for the FHIR build process itself in as much as it 
   * generates hyperlinks in the narrative that are only going to be correct for
   * the purposes of the build. This is to be reviewed in the future.
   *  
   * @param vs
   * @param codeSystems
   * @throws Exception
   */
  public void generate(ValueSet vs) throws Exception {
    XhtmlNode x = new XhtmlNode(NodeType.Element, "div");
    if (vs.getExpansion() != null) {
      if (vs.getDefine() == null && vs.getCompose() == null)
        generateExpansion(x, vs);
      else
        throw new Exception("Error: should not encounter value set expansion at this point");
    }
    boolean hasExtensions = false;
    if (vs.getDefine() != null)
      hasExtensions = generateDefinition(x, vs);
    if (vs.getCompose() != null) 
      hasExtensions = generateComposition(x, vs) || hasExtensions;
    inject(vs, x, hasExtensions ? NarrativeStatus.extensions :  NarrativeStatus.generated);
  }

  private boolean generateExpansion(XhtmlNode x, ValueSet vs) {
    boolean hasExtensions = false;
    Map<ConceptMap, String> mymaps = new HashMap<ConceptMap, String>();
    for (AtomEntry<ConceptMap> a : maps.values()) {
      if (a.getResource().getSource().getReferenceSimple().equals(vs.getIdentifierSimple())) {
        String url = "";
        if (valueSets.containsKey(a.getResource().getTarget().getReferenceSimple()))
            url = valueSets.get(a.getResource().getTarget().getReferenceSimple()).getLinks().get("path");
        mymaps.put(a.getResource(), url);
      }
    }

    XhtmlNode h = x.addTag("h3");
    h.addText(vs.getDescriptionSimple());
    if (vs.getCopyright() != null)
      generateCopyright(x, vs);

    XhtmlNode t = x.addTag("table");
    XhtmlNode tr = t.addTag("tr");
    tr.addTag("td").addTag("b").addText("Code");
    tr.addTag("td").addTag("b").addText("System");
    tr.addTag("td").addTag("b").addText("Display");

    addMapHeaders(tr, mymaps);
    for (ValueSetExpansionContainsComponent c : vs.getExpansion().getContains()) {
      addExpansionRowToTable(t, c, 0, mymaps);
    }    
    return hasExtensions;
  }

  private boolean generateDefinition(XhtmlNode x, ValueSet vs) {
    boolean hasExtensions = false;
    Map<ConceptMap, String> mymaps = new HashMap<ConceptMap, String>();
    for (AtomEntry<ConceptMap> a : maps.values()) {
      if (a.getResource().getSource().getReferenceSimple().equals(vs.getIdentifierSimple())) {
        String url = "";
        if (valueSets.containsKey(a.getResource().getTarget().getReferenceSimple()))
            url = valueSets.get(a.getResource().getTarget().getReferenceSimple()).getLinks().get("path");
        mymaps.put(a.getResource(), url);
      }
    }

    XhtmlNode h = x.addTag("h2");
    h.addText(vs.getNameSimple());
    XhtmlNode p = x.addTag("p");
    smartAddText(p, vs.getDescriptionSimple());
    if (vs.getCopyright() != null)
      generateCopyright(x, vs);
    p = x.addTag("p");
    p.addText("This value set defines its own terms in the system "+vs.getDefine().getSystemSimple());
    XhtmlNode t = x.addTag("table");
    boolean commentS = false;
    boolean deprecated = false;
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      commentS = commentS || conceptsHaveComments(c);
      deprecated = deprecated || conceptsHaveDeprecated(c);
    }
    addMapHeaders(addTableHeaderRowStandard(t, commentS, deprecated), mymaps);
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      hasExtensions = addDefineRowToTable(t, c, 0, commentS, deprecated, mymaps) || hasExtensions;
    }    
    return hasExtensions;
  }

  private void addMapHeaders(XhtmlNode tr, Map<ConceptMap, String> mymaps) {
	  for (ConceptMap m : mymaps.keySet()) {
	  	XhtmlNode td = tr.addTag("td");
	  	XhtmlNode b = td.addTag("b");
	  	XhtmlNode a = b.addTag("a");
	  	a.setAttribute("href", prefix+mymaps.get(m));
	  	a.addText(m.getDescriptionSimple());	  	
	  }	  
  }

	private void smartAddText(XhtmlNode p, String text) {
    String[] lines = text.split("\\r\\n");
    for (int i = 0; i < lines.length; i++) {
      if (i > 0)
        p.addTag("br");
      p.addText(lines[i]);
    }
  }

  private boolean conceptsHaveComments(ValueSetDefineConceptComponent c) {
    if (ToolingExtensions.hasComment(c)) 
      return true;
    for (ValueSetDefineConceptComponent g : c.getConcept()) 
      if (conceptsHaveComments(g))
        return true;
    return false;
  }

  private boolean conceptsHaveDeprecated(ValueSetDefineConceptComponent c) {
    if (ToolingExtensions.hasDeprecated(c)) 
      return true;
    for (ValueSetDefineConceptComponent g : c.getConcept()) 
      if (conceptsHaveDeprecated(g))
        return true;
    return false;
  }

  private void generateCopyright(XhtmlNode x, ValueSet vs) {
    XhtmlNode p = x.addTag("p");
    p.addTag("b").addText("Copyright Statement:");
    smartAddText(p, " " + vs.getCopyrightSimple());
  }


  private XhtmlNode addTableHeaderRowStandard(XhtmlNode t, boolean comments, boolean deprecated) {
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
    if (deprecated) {
      tr.addTag("td").addTag("b").addText("Deprecated");
    }
    if (comments) {
      tr.addTag("td").addTag("b").addText("Comments");
    }
    return tr;
  }

  private void addExpansionRowToTable(XhtmlNode t, ValueSetExpansionContainsComponent c, int i, Map<ConceptMap, String> mymaps) {
    XhtmlNode tr = t.addTag("tr");
    XhtmlNode td = tr.addTag("td");
    
    
    String s = Utilities.padLeft("", '.', i*2);
    td.addText(s);
    AtomEntry<? extends Resource> e = codeSystems.get(c.getSystemSimple());
    if (e == null)
      td.addText(c.getCodeSimple());
    else {
      XhtmlNode a = td.addTag("a");
      a.addText(c.getCodeSimple());
      a.setAttribute("href", prefix+getCsRef(e)+"#"+Utilities.nmtokenize(c.getCodeSimple()));
      
    }
    td = tr.addTag("td");
    td.addText(c.getSystemSimple());
    td = tr.addTag("td");
    if (c.getDisplaySimple() != null)
      td.addText(c.getDisplaySimple());

    for (ConceptMap m : mymaps.keySet()) {
      td = tr.addTag("td");
      List<ConceptMapConceptMapComponent> mappings = findMappingsForCode(c.getCodeSimple(), m);
      boolean first = true;
      for (ConceptMapConceptMapComponent mapping : mappings) {
        if (!first)
            td.addTag("br");
        first = false;
        XhtmlNode span = td.addTag("span");
        span.setAttribute("title", mapping.getEquivalenceSimple().toString());
        span.addText(getCharForEquivalence(mapping));
        XhtmlNode a = td.addTag("a");
        a.setAttribute("href", prefix+mymaps.get(m)+"#"+mapping.getCodeSimple());
        a.addText(mapping.getCodeSimple());
        if (!Utilities.noString(mapping.getCommentsSimple()))
          td.addTag("i").addText("("+mapping.getCommentsSimple()+")");
      }
    }
    for (ValueSetExpansionContainsComponent cc : c.getContains()) {
      addExpansionRowToTable(t, cc, i+1, mymaps);
    }    
  }

  private boolean addDefineRowToTable(XhtmlNode t, ValueSetDefineConceptComponent c, int i, boolean comment, boolean deprecated, Map<ConceptMap, String> maps) {
    boolean hasExtensions = false;
    XhtmlNode tr = t.addTag("tr");
    XhtmlNode td = tr.addTag("td");
    String s = Utilities.padLeft("", '.', i*2);
    td.addText(s);
    td.addText(c.getCodeSimple());
    XhtmlNode a = td.addTag("a");
    a.setAttribute("name", Utilities.nmtokenize(c.getCodeSimple()));
    a.addText(" ");
    
    td = tr.addTag("td");
    if (c.getDisplaySimple() != null)
      td.addText(c.getDisplaySimple());
    td = tr.addTag("td");
    if (c.getDefinitionSimple() != null)
      smartAddText(td, c.getDefinitionSimple());
    if (deprecated) {
      td = tr.addTag("td");
      s = ToolingExtensions.getDeprecated(c);
      if (s != null) {
        smartAddText(td, s);
        hasExtensions = true;
      }
    }
    if (comment) {
      td = tr.addTag("td");
      s = ToolingExtensions.getComment(c);
      if (s != null) {
        smartAddText(td, s);
        hasExtensions = true;
      }
    }
    for (ConceptMap m : maps.keySet()) {
      td = tr.addTag("td");
      List<ConceptMapConceptMapComponent> mappings = findMappingsForCode(c.getCodeSimple(), m);
      boolean first = true;
      for (ConceptMapConceptMapComponent mapping : mappings) {
      	if (!first)
      		  td.addTag("br");
      	first = false;
      	XhtmlNode span = td.addTag("span");
      	span.setAttribute("title", mapping.getEquivalenceSimple().toString());
      	span.addText(getCharForEquivalence(mapping));
      	a = td.addTag("a");
      	a.setAttribute("href", prefix+maps.get(m)+"#"+mapping.getCodeSimple());
      	a.addText(mapping.getCodeSimple());
        if (!Utilities.noString(mapping.getCommentsSimple()))
          td.addTag("i").addText("("+mapping.getCommentsSimple()+")");
      }
    }
    for (Code e : ToolingExtensions.getSubsumes(c)) {
      hasExtensions = true;
      tr = t.addTag("tr");
      td = tr.addTag("td");
      s = Utilities.padLeft("", '.', i*2);
      td.addText(s);
      a = td.addTag("a");
      a.setAttribute("href", "#"+Utilities.nmtokenize(e.getValue()));
      a.addText(c.getCodeSimple());
    }
    for (ValueSetDefineConceptComponent cc : c.getConcept()) {
      hasExtensions = addDefineRowToTable(t, cc, i+1, comment, deprecated, maps) || hasExtensions;
    }    
    return hasExtensions;
  }


  private String getCharForEquivalence(ConceptMapConceptMapComponent mapping) {
	  switch (mapping.getEquivalenceSimple()) {
	  case equal : return "=";
	  case equivalent : return "~";
	  case wider : return "<";
	  case narrower : return ">";
	  case inexact : return "><";
	  case unmatched : return "-";
	  case disjoint : return "!=";
    default: return "?";
	  }
  }

	private List<ConceptMapConceptMapComponent> findMappingsForCode(String code, ConceptMap map) {
	  List<ConceptMapConceptMapComponent> mappings = new ArrayList<ConceptMapConceptMapComponent>();
	  
  	for (ConceptMapConceptComponent c : map.getConcept()) {
	  	if (c.getCodeSimple().equals(code)) 
	  		mappings.addAll(c.getMap());
	  }
	  return mappings;
  }

	private boolean generateComposition(XhtmlNode x, ValueSet vs) throws Exception {
	  boolean hasExtensions = false;
    if (vs.getDefine() == null) {
      XhtmlNode h = x.addTag("h2");
      h.addText(vs.getNameSimple());
      XhtmlNode p = x.addTag("p");
      smartAddText(p, vs.getDescriptionSimple());
      if (vs.getCopyright() != null)
        generateCopyright(x, vs);
      p = x.addTag("p");
      p.addText("This value set includes codes defined in other code systems, using the following rules:");
    } else {
      XhtmlNode p = x.addTag("p");
      p.addText("In addition, this value set includes codes defined in other code systems, using the following rules:");

    }
    XhtmlNode ul = x.addTag("ul");
    XhtmlNode li;
    for (Uri imp : vs.getCompose().getImport()) {
      li = ul.addTag("li");
      li.addText("Import all the codes that are part of ");
      AddVsRef(imp.getValue(), li);
    }
    for (ConceptSetComponent inc : vs.getCompose().getInclude()) {
      hasExtensions = genInclude(ul, inc, "Include") || hasExtensions;      
    }
    for (ConceptSetComponent exc : vs.getCompose().getExclude()) {
      hasExtensions = genInclude(ul, exc, "Exclude") || hasExtensions;      
    }
    return hasExtensions;
  }

  private void AddVsRef(String value, XhtmlNode li) {

    AtomEntry<? extends Resource> vs = valueSets.get(value);
    if (vs == null) 
      vs = codeSystems.get(value); 
    if (vs != null) {
      String ref= vs.getLinks().get("path");
      XhtmlNode a = li.addTag("a");
      a.setAttribute("href", prefix+ref.replace("\\", "/"));
      a.addText(value);
    } else if (value.equals("http://snomed.info/sct") || value.equals("http://snomed.info/id")) {
      XhtmlNode a = li.addTag("a");
      a.setAttribute("href", value);
      a.addText("SNOMED-CT");      
    }
    else 
      li.addText(value);
  }

  private  boolean genInclude(XhtmlNode ul, ConceptSetComponent inc, String type) throws Exception {
    boolean hasExtensions = false;
    XhtmlNode li;
    li = ul.addTag("li");
    AtomEntry<? extends Resource> e = codeSystems.get(inc.getSystemSimple());
    
    if (inc.getCode().size() == 0 && inc.getFilter().size() == 0) { 
      li.addText(type+" all codes defined in ");
      addCsRef(inc, li, e);
    } else { 
      if (inc.getCode().size() > 0) {
        li.addText(type+" these codes as defined in ");
        addCsRef(inc, li, e);
      
        XhtmlNode t = li.addTag("table");
        boolean hasComments = false;
        for (Code c : inc.getCode()) {
          hasComments = hasComments || c.hasExtension(ToolingExtensions.EXT_COMMENT);
        }
        if (hasComments)
          hasExtensions = true;
        addTableHeaderRowStandard(t, hasComments, false);
        for (Code c : inc.getCode()) {
          XhtmlNode tr = t.addTag("tr");
          tr.addTag("td").addText(c.getValue());
          ValueSetDefineConceptComponent cc = getConceptForCode(e, c.getValue(), inc.getSystemSimple());
          
          XhtmlNode td = tr.addTag("td");
          if (c.hasExtension(ToolingExtensions.EXT_DISPLAY))
            td.addText(ToolingExtensions.readStringExtension(c, ToolingExtensions.EXT_DISPLAY));
          else if (cc != null && !Utilities.noString(cc.getDisplaySimple()))
            td.addText(cc.getDisplaySimple());
          
          td = tr.addTag("td");
          if (c.hasExtension(ToolingExtensions.EXT_DEFINITION))
            smartAddText(td, ToolingExtensions.readStringExtension(c, ToolingExtensions.EXT_DEFINITION));
          else if (cc != null && !Utilities.noString(cc.getDefinitionSimple()))
            smartAddText(td, cc.getDefinitionSimple());

          if (c.hasExtension(ToolingExtensions.EXT_COMMENT)) {
            smartAddText(tr.addTag("td"), "Note: "+ToolingExtensions.readStringExtension(c, ToolingExtensions.EXT_COMMENT));
          }
        }
      }
      for (ConceptSetFilterComponent f : inc.getFilter()) {
        li.addText(type+" codes from ");
        addCsRef(inc, li, e);
        li.addText(" where "+f.getPropertySimple()+" "+describe(f.getOpSimple())+" ");
        if (e != null && codeExistsInValueSet(e, f.getValueSimple())) {
          XhtmlNode a = li.addTag("a");
          a.addText(f.getValueSimple());
          a.setAttribute("href", prefix+getCsRef(e)+"#"+Utilities.nmtokenize(f.getValueSimple()));
        } else
          li.addText(f.getValueSimple());
      }
    }
    return hasExtensions;
  }

  private String describe(FilterOperator opSimple) {
    switch (opSimple) {
    case equal: return " = ";
    case isa: return " is-a ";
    case isnota: return " is-not-a ";
    case regex: return " matches (by regex) ";
    
    }
    return null;
  }

  private <T extends Resource> ValueSetDefineConceptComponent getConceptForCode(AtomEntry<T> e, String code, String system) {
    if (e == null) {
      if (conceptLocator != null)
        return conceptLocator.locate(system, code);
      else
        return null;
    }
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

  private  <T extends Resource> void addCsRef(ConceptSetComponent inc, XhtmlNode li, AtomEntry<T> cs) {
    String ref = null;
    if (cs != null) {
      cs.getLinks().get("path");
      if (Utilities.noString(ref))
        ref = cs.getLinks().get("self");
    }
    if (cs != null && ref != null) {
      if (!Utilities.noString(prefix) && ref.startsWith("http://hl7.org/fhir/"))
        ref = ref.substring(20)+"/index.html";
      XhtmlNode a = li.addTag("a");
      a.setAttribute("href", prefix+ref.replace("\\", "/"));
      a.addText(inc.getSystemSimple().toString());
    } else 
      li.addText(inc.getSystemSimple().toString());
  }

  private  <T extends Resource> String getCsRef(AtomEntry<T> cs) {
    String ref = cs.getLinks().get("path");
    if (Utilities.noString(ref))
      ref = cs.getLinks().get("self");
    return ref.replace("\\", "/");
  }

  private  <T extends Resource> boolean codeExistsInValueSet(AtomEntry<T> cs, String code) {
    ValueSet vs = (ValueSet) cs.getResource();
    for (ValueSetDefineConceptComponent c : vs.getDefine().getConcept()) {
      if (inConcept(code, c))
        return true;
    }
    return false;
  }

  private boolean inConcept(String code, ValueSetDefineConceptComponent c) {
    if (c.getCodeSimple() != null && c.getCodeSimple().equals(code))
      return true;
    for (ValueSetDefineConceptComponent g : c.getConcept()) {
      if (inConcept(code, g))
        return true;
    }
    return false;
  }

  /**
   * This generate is optimised for the build tool in that it tracks the source extension. 
   * But it can be used for any other use.
   *  
   * @param vs
   * @param codeSystems
   * @throws Exception
   */
  public void generate(OperationOutcome op) throws Exception {
    XhtmlNode x = new XhtmlNode(NodeType.Element, "div");
    boolean hasSource = false;
    boolean hasType = false;
    boolean success = true;
    for (OperationOutcomeIssueComponent i : op.getIssue()) {
    	success = success && i.getSeveritySimple() != IssueSeverity.information;
    	hasSource = hasSource || i.hasExtension(ToolingExtensions.EXT_ISSUE_SOURCE);
    	hasType = hasType || i.getType() != null;
    }
    if (success)
    	x.addTag("p").addText("All OK");
    if (op.getIssue().size() > 0) {
    		XhtmlNode tbl = x.addTag("table");
    		tbl.setAttribute("class", "grid"); // on the basis that we'll most likely be rendered using the standard fhir css, but it doesn't really matter
    		XhtmlNode tr = tbl.addTag("tr");
    		tr.addTag("td").addTag("b").addText("Severity");
    		tr.addTag("td").addTag("b").addText("Location");
    		tr.addTag("td").addTag("b").addText("Details");
    		if (hasType)
    			tr.addTag("td").addTag("b").addText("Type");
    		if (hasSource)
    			tr.addTag("td").addTag("b").addText("Source");
    		for (OperationOutcomeIssueComponent i : op.getIssue()) {
    			tr = tbl.addTag("tr");
    			tr.addTag("td").addText(i.getSeverity().toString());
    			XhtmlNode td = tr.addTag("td");
    			boolean d = false;
    			for (String_ s : i.getLocation()) {
    				if (d)
    					td.addText(", ");
    				else
    					d = true;
    				td.addText(s.getValue());      		
    			}
    			smartAddText(tr.addTag("td"), i.getDetailsSimple());
    			if (hasType)
    				tr.addTag("td").addText(gen(i.getType()));
    			if (hasSource)
    				tr.addTag("td").addText(gen(i.getExtension(ToolingExtensions.EXT_ISSUE_SOURCE)));
    		}    
    	}
    inject(op, x, hasSource ? NarrativeStatus.extensions :  NarrativeStatus.generated);  	
  }


	private String gen(Extension extension) throws Exception {
		if (extension.getValue() instanceof Code)
			return ((Code) extension.getValue()).getValue();
		if (extension.getValue() instanceof Coding)
			return gen((Coding) extension.getValue());

	  throw new Exception("Unhandled type "+extension.getValue().getClass().getName());
  }

	private String gen(Coding type) {
	  if (type == null)
	  	return null;
	  if (type.getDisplay() != null)
	  	return type.getDisplaySimple();
	  if (type.getCode() != null)
	  	return type.getCodeSimple();
	  return null;
  }

  public void generate(Conformance conf) {
    XhtmlNode x = new XhtmlNode(NodeType.Element, "div");
    x.addTag("h2").addText(conf.getNameSimple());
    smartAddText(x.addTag("p"), conf.getDescriptionSimple());
    ConformanceRestComponent rest = conf.getRest().get(0);
    XhtmlNode t = x.addTag("table");
    addTableRow(t, "Mode", rest.getModeSimple().toString());
    addTableRow(t, "Description", rest.getDocumentationSimple());
    
    addTableRow(t, "Transaction", showOp(rest, SystemRestfulOperation.transaction));
    addTableRow(t, "System History", showOp(rest, SystemRestfulOperation.historysystem));
    addTableRow(t, "System Search", showOp(rest, SystemRestfulOperation.searchsystem));
    
    t = x.addTag("table");
    XhtmlNode tr = t.addTag("tr");
    tr.addTag("th").addTag("b").addText("Resource Type");
    tr.addTag("th").addTag("b").addText("Profile");
    tr.addTag("th").addTag("b").addText("Read");
    tr.addTag("th").addTag("b").addText("V-Read");
    tr.addTag("th").addTag("b").addText("Search");
    tr.addTag("th").addTag("b").addText("Update");
    tr.addTag("th").addTag("b").addText("Updates");
    tr.addTag("th").addTag("b").addText("Create");
    tr.addTag("th").addTag("b").addText("Delete");
    tr.addTag("th").addTag("b").addText("History");
    
    for (ConformanceRestResourceComponent r : rest.getResource()) {
      tr = t.addTag("tr");
      tr.addTag("td").addText(r.getTypeSimple());
      if (r.getProfile() != null) {
      	XhtmlNode a = tr.addTag("td").addTag("a");
      	a.addText(r.getProfile().getReferenceSimple());
      	a.setAttribute("href", prefix+r.getProfile().getReferenceSimple());
      }
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.read));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.vread));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.searchtype));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.update));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.historyinstance));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.create));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.delete));
      tr.addTag("td").addText(showOp(r, TypeRestfulOperation.historytype));
    }
    
    inject(conf, x, NarrativeStatus.generated);
  }

  private String showOp(ConformanceRestResourceComponent r, TypeRestfulOperation on) {
    for (ConformanceRestResourceOperationComponent op : r.getOperation()) {
      if (op.getCodeSimple() == on)
        return "y";
    }
    return "";
  }

  private String showOp(ConformanceRestComponent r, SystemRestfulOperation on) {
    for (ConformanceRestOperationComponent op : r.getOperation()) {
      if (op.getCodeSimple() == on)
        return "y";
    }	
    return "";
  }

  private void addTableRow(XhtmlNode t, String name, String value) {
    XhtmlNode tr = t.addTag("tr");
    tr.addTag("td").addText(name);
    tr.addTag("td").addText(value);    
  }

  private String showBoolean(Boolean bool) {
    if (bool == null)
      return "";
    if (bool.getValue())
      return "y"; 
    return "";        
  }


}
