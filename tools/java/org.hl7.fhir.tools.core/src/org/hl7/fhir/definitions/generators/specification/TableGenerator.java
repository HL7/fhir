package org.hl7.fhir.definitions.generators.specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Row;

public class TableGenerator extends BaseGenerator {
  public enum RenderMode {
    DATATYPE,
    RESOURCE,
    LOGICAL
  } 

  private final boolean ADD_REFERENCE_TO_TABLE = true;
  
  protected String dest; 
  protected String pageName;
  protected boolean inlineGraphics;
  
  public TableGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics) throws Exception {
    super();
    this.dest = dest;
    this.definitions = page.getDefinitions();
    this.page = page;
    this.pageName = pageName;
    this.inlineGraphics = inlineGraphics; 
  }
  
  protected boolean dictLinks() {
    return pageName != null;
  }
  protected Row genElement(ElementDefn e, HierarchicalTableGenerator gen, boolean resource, String path, boolean isProfile, String prefix, RenderMode mode, boolean isRoot, StandardsStatus rootStatus, boolean isAbstract, boolean isInterface) throws Exception {
    Row row = gen.new Row();

    row.setAnchor(path);
    boolean isProfiledExtension = isProfile && (e.getName().equals("extension") || e.getName().equals("modifierExtension"));
    row.getCells().add(gen.new Cell(null, dictLinks() ? pageName+"#"+path.replace("[", "_").replace("]", "_") : null, e.getName(), path+" : "+e.getDefinition(), null));
    Cell gc = gen.new Cell();
    row.getCells().add(gc);
    if (e.hasMustSupport() && e.isMustSupport()) {
      gc.addStyledText("This element must be supported", "S", "white", "red", prefix+"conformance-rules.html#mustSupport", false);
    }
    if (e.isModifier()) { 
      gc.addStyledText("This element is a modifier element", "?!", null, null, prefix+"conformance-rules.html#isModifier", false);
    }
    if (e.isSummary()) { 
      gc.addStyledText("This element is included in summaries", "\u03A3", null, null, prefix+"elementdefinition-definitions.html#ElementDefinition.isSummary", false);
    }
    if (!isRoot && (!e.getInvariants().isEmpty() || !e.getStatedInvariants().isEmpty())) { 
      gc.addStyledText("This element has or is affected by some invariants", "I", null, null, prefix+"conformance-rules.html#constraints", false);
    }
    if (isInterface) {
      gc.addStyledText("This is an abstract type", "«A»", null, null, prefix+"uml.html#abstract", false);      
    } else if (isAbstract) {
      gc.addStyledText("This is an interface resource", "«I»", null, null, prefix+"uml.html#interface", false);      
    }
    if (rootStatus != null)
      gc.addStyledText("Standards Status = "+rootStatus.toDisplay(), rootStatus.getAbbrev(), "black", rootStatus.getColor(), prefix+"versions.html#std-process", true);
    else if (e.getStandardsStatus() != null)
      gc.addStyledText("Standards Status = "+e.getStandardsStatus().toDisplay(), e.getStandardsStatus().getAbbrev(), "black", e.getStandardsStatus().getColor(), prefix+"versions.html#std-process", true);
    if (resource) {
      row.getCells().add(gen.new Cell()); 
  
      row.setIcon("icon_resource.png", HierarchicalTableGenerator.TEXT_ICON_RESOURCE);
      if (Utilities.noString(e.typeCode()))
        row.getCells().add(gen.new Cell(null, null, "n/a", null, null)); 
      else if ("Logical".equals(e.typeCode()))
        row.getCells().add(gen.new Cell(null, prefix+"structuredefinition.html#logical", e.typeCode(), null, null)); 
      else if ("Base".equals(e.typeCode()))
        row.getCells().add(gen.new Cell(null, prefix+definitions.getSrcFile("Base")+".html#"+e.typeCode(), e.typeCode(), null, null)); 
      else
        row.getCells().add(gen.new Cell(null, prefix+e.typeCode().toLowerCase()+".html", e.typeCode(), null, null)); 
      // todo: base elements
    } else {
      if (!e.getElements().isEmpty()) {
        row.getCells().add(gen.new Cell(null, null, path.contains(".") ? e.describeCardinality() : "", null, null)); 
        row.setIcon("icon_element.gif", HierarchicalTableGenerator.TEXT_ICON_ELEMENT);
        if (mode == RenderMode.RESOURCE)
          row.getCells().add(gen.new Cell(null, prefix+"types.html#BackBoneElement", "BackboneElement", null, null));
        else if (e.getName().equals("Type"))
          row.getCells().add(gen.new Cell(null, null, "", null, null)); 
        else if (e.getName().equals("Element"))
          row.getCells().add(gen.new Cell(null, prefix+"types.html#Base", "Base", null, null)); 
        else if (e.typeCode().equals("BackboneElement"))
          row.getCells().add(gen.new Cell(null, prefix+"types.html#BackBoneElement", "BackBoneElement", null, null));   
        else
          row.getCells().add(gen.new Cell(null, prefix+"types.html#Element", "Element", null, null));   
      } else if (e.getTypes().size() == 1) {
        row.getCells().add(gen.new Cell(null, null, path.contains(".") ? e.describeCardinality() : "", null, null)); 
        String t = e.getTypes().get(0).getName();
        Cell c;
        if (t.startsWith("@")) {
          row.setIcon("icon_reuse.png", HierarchicalTableGenerator.TEXT_ICON_REUSE);
          c = gen.new Cell("see ", "#"+t.substring(1), t.substring(t.lastIndexOf(".")+1), t.substring(1), null);
        } else if (isReference(t)) {
          row.setIcon("icon_reference.png", HierarchicalTableGenerator.TEXT_ICON_REFERENCE);
          c = gen.new Cell();
          if (ADD_REFERENCE_TO_TABLE) {
          c.getPieces().add(gen.new Piece(prefix+definitions.getSrcFile(t)+".html#"+t, t, null));
          c.getPieces().add(gen.new Piece(null, "(", null));
          }
          boolean first = true;
          for (String rt : e.getTypes().get(0).getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            if (first && isProfile && e.getTypes().get(0).getProfile() != null)
              c.getPieces().add(gen.new Piece(null, e.getTypes().get(0).getProfile(), null));
            else
              c.getPieces().add(gen.new Piece(prefix+findPage(rt)+".html", rt, null));
            first = false;
          }
          if (ADD_REFERENCE_TO_TABLE) 
            c.getPieces().add(gen.new Piece(null, ")", null));
        } else if (definitions.getPrimitives().containsKey(t)) {
          row.setIcon("icon_primitive.png", HierarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          c = gen.new Cell(null, prefix+"datatypes.html#"+t, t, null, null);
        } else {
          if (t.equals("Extension"))
            row.setIcon("icon_extension_simple.png", HierarchicalTableGenerator.TEXT_ICON_EXTENSION);
          else
            row.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          c = gen.new Cell(null, prefix+definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null);
        }
        row.getCells().add(c);
      } else {
        row.getCells().add(gen.new Cell(null, null, isRoot ? "" : e.describeCardinality(), null, null));   
        row.setIcon("icon_choice.gif", HierarchicalTableGenerator.TEXT_ICON_CHOICE);
        row.getCells().add(gen.new Cell(null, null, "", null, null));   
      }
    }
      
    Cell cc = gen.new Cell(null, e.getShortDefn() != null && Utilities.isURL(e.getShortDefn()) ? e.getShortDefn() : null, e.getShortDefn(), null, null);
    row.getCells().add(cc);
    
    // constraints
    if (isProfiledExtension) {
      cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, e.getTypes().get(0).getProfile(), null));
    }
    
    if (e.hasBinding() && e.getBinding() != null && e.getBinding().getBinding() != BindingMethod.Unbound) {
      if (cc.getPieces().size() == 1)
        cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(getBindingLink(prefix, e), e.getBinding().getValueSet() != null ? e.getBinding().getValueSet().present() : e.getBinding().getName(), 
            e.getBinding().getDefinition()));
      cc.getPieces().add(gen.new Piece(null, " (", null));
      BindingSpecification b = e.getBinding();
      if (b.hasMax() ) {
        cc.getPieces().add(gen.new Piece(prefix+"terminologies.html#"+b.getStrength().toCode(), b.getStrength().getDisplay(),  b.getStrength().getDefinition()));
        cc.getPieces().add(gen.new Piece(null, " but limited to ", null));
        ValueSet vs = b.getMaxValueSet();
        if (vs == null)
          cc.getPieces().add(gen.new Piece(b.getMaxReference(), b.getMaxReference(), null));
        else
          cc.getPieces().add(gen.new Piece(vs.hasUserData("external.url") ? vs.getUserString("external.url") : vs.getUserString("path"), vs.getName(), null));
      }  else
        cc.getPieces().add(gen.new Piece(prefix+"terminologies.html#"+b.getStrength().toCode(), b.getStrength().getDisplay(),  b.getStrength().getDefinition()));
      cc.getPieces().add(gen.new Piece(null, ")", null));
    }
    List<String> invs = new ArrayList<String>(e.getInvariants().keySet());
    Collections.sort(invs, new ConstraintsSorter());
    for (String name : invs) {
      Invariant inv = e.getInvariants().get(name);
      cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, "+ "+presentLevel(inv)+": "+inv.getEnglish(), inv.getId()).setStyle("font-style: italic"));
    }
    if (e.unbounded() && !isRoot) {
      if (cc.getPieces().size() > 0)
        cc.addPiece(gen.new Piece("br"));
      if (Utilities.noString(e.getOrderMeaning())) {
        // don't show this, this it's important: cc.getPieces().add(gen.new Piece(null, "This repeating element has no defined order", null));
      } else {
        cc.getPieces().add(gen.new Piece(null, "This repeating element order: "+e.getOrderMeaning(), null));
      }
    }
    if (isRoot && !Utilities.noString(e.typeCode()) && !"Logical".equals(e.typeCode())) {
      List<ElementDefn> ancestors = new ArrayList<ElementDefn>();
      ElementDefn f = definitions.getElementDefn(e.typeCode());
      while (f != null) {
        ancestors.add(0, f);
        f = Utilities.noString(f.typeCode()) || "Logical".equals(f.typeCode()) ? null : definitions.getElementDefn(f.typeCode());
      }
      
      cc.getPieces().add(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, "Elements defined in Ancestors: ", null));
      boolean first = true;
      for (ElementDefn fi : ancestors) { 
        for (ElementDefn fc : fi.getElements()) {
          if (first)
            first = false;
          else
            cc.getPieces().add(gen.new Piece(null, ", ", null));
          cc.getPieces().add(gen.new Piece(definitions.getSrcFile(fi.getName())+".html#"+fi.getName(), fc.getName(), fc.getDefinition()));
        }
      }
    }
    if (mode == RenderMode.LOGICAL) {
      String logical = e.getMappings().get("http://hl7.org/fhir/logical");
      Cell c = gen.new Cell();
      row.getCells().add(c);
      if (logical != null)
        presentLogicalMapping(gen, c, logical, prefix);
    }
      
    if (e.getTypes().size() > 1) {
      // create a child for each choice
      for (TypeRef tr : e.getTypes()) {
        Row choicerow = gen.new Row();
        String t = tr.getName();
        if (isReference(t)) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]", Utilities.capitalize(t)), null, null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_reference.png", HierarchicalTableGenerator.TEXT_ICON_REFERENCE);
          Cell c = gen.new Cell();
          choicerow.getCells().add(c);
          if (ADD_REFERENCE_TO_TABLE) {
            if (tr.getName().equals("canonical"))
              c.getPieces().add(gen.new Piece(prefix+"datatypes.html#canonical", "canonical", null));
            else
              c.getPieces().add(gen.new Piece(prefix+"references.html#Reference", "Reference", null));
            c.getPieces().add(gen.new Piece(null, "(", null));
          }
          boolean first = true;
          for (String rt : tr.getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            c.getPieces().add(gen.new Piece(prefix+findPage(rt)+".html", rt, null));
            first = false;
          }
          if (ADD_REFERENCE_TO_TABLE) 
            c.getPieces().add(gen.new Piece(null, ")", null));
          
        } else if (definitions.getPrimitives().containsKey(t)) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getPrimitives().get(t).getDefinition(), null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_primitive.png", HierarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          choicerow.getCells().add(gen.new Cell(null, prefix+"datatypes.html#"+t, t, null, null));
        } else if (definitions.getConstraints().containsKey(t)) {
          ProfiledType pt = definitions.getConstraints().get(t);
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]", Utilities.capitalize(pt.getBaseType())), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          choicerow.getCells().add(gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
        } else {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          choicerow.getCells().add(gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
        }
      
        choicerow.getCells().add(gen.new Cell());
//        choicerow.getCells().add(gen.new Cell());
        row.getSubRows().add(choicerow);
      }
    } else
      for (ElementDefn c : e.getElements()) {
        row.getSubRows().add(genElement(c, gen, false, path+'.'+c.getName(), isProfile, prefix, mode, false, null, false, false));
      }
    return row; 
  }      

  public class ConstraintsSorter implements Comparator<String> {

    @Override
    public int compare(String s0, String s1) {
    String[] parts0 = s0.split("\\-");
    String[] parts1 = s1.split("\\-");
    if (parts0.length != 2 || parts1.length != 2)
      return s0.compareTo(s1);
    int comp = parts0[0].compareTo(parts1[0]);
    if (comp == 0 && Utilities.isInteger(parts0[1]) && Utilities.isInteger(parts1[1]))
      return new Integer(parts0[1]).compareTo(new Integer(parts1[1]));
    else
      return parts0[1].compareTo(parts1[1]);
    }

  }
  private String presentLevel(Invariant inv) {
    if ("warning".equals(inv.getSeverity()))
      return "Warning";
    if ("best-practice".equals(inv.getSeverity()))
      return "Guideline";
    return "Rule";
  }

  
  private boolean isReference(String t) {
    return t.equals("Reference") || t.equals("CodeableReference") || t.equals("canonical"); 
  }  

  private void presentLogicalMapping(HierarchicalTableGenerator gen, Cell c, String logical, String prefix) {
    c.addPiece(gen.new Piece(null, logical, null));
  }

//  private void presentLogicalMappingWord(HierarchicalTableGenerator gen, Cell c, String p, String prefix) {
//    if (p.contains(".") && page.getDefinitions().hasResource(p.substring(0, p.indexOf(".")))) {
//      String rn = p.substring(0, p.indexOf("."));
//      String rp = p.substring(p.indexOf(".")+1);
//      c.addPiece(gen.new Piece(prefix+rn.toLowerCase()+".html", rn, null));
//      c.addPiece(gen.new Piece(null, ".", null));
//      ResourceDefn r;
//      ElementDefn e; 
//      try {
//        r = page.getDefinitions().getResourceByName(rn);
//        e = r.getRoot().getElementForPath(p, page.getDefinitions(), "logical mapping", true);
//      } catch (Exception e1) {
//        r = null;
//        e = null;
//      }
//      if (e == null)
//        c.addPiece(gen.new Piece(null, rp, null));
//      else
//        c.addPiece(gen.new Piece(prefix+rn.toLowerCase()+"-definitions.html#"+p, rp, null));
//    } else if (page.getDefinitions().hasResource(p)) {
//      c.addPiece(gen.new Piece(prefix+p.toLowerCase()+".html", p, null));
//    } else {
//      c.addPiece(gen.new Piece(null, p, null));
//    }
//    
//  }

  private String findPage(String rt) {
    if (rt.equalsIgnoreCase("any"))
      return "resourcelist";
    if (rt.equalsIgnoreCase("binary"))
      return "http";
    return rt.toLowerCase();
  } 
}
