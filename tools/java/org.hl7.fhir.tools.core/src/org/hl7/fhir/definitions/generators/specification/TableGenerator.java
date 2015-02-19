package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;

public class TableGenerator extends BaseGenerator {
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
  protected Row genElement(ElementDefn e, HeirarchicalTableGenerator gen, boolean resource, String path, boolean isProfile) throws Exception {
    Row row = gen.new Row();

    row.setAnchor(path);
    boolean isProfiledExtension = isProfile && (e.getName().equals("extension") || e.getName().equals("modifierExtension"));
    row.getCells().add(gen.new Cell(null, dictLinks() ? pageName+"#"+path.replace("[", "_").replace("]", "_") : null, e.getName(), e.getDefinition(), null));
    Cell gc = gen.new Cell();
    row.getCells().add(gc);
    if (e.hasMustSupport() && e.isMustSupport()) 
      gc.addImage("mustsupport.png", "This element must be supported", "S");
    if (e.isModifier()) 
      gc.addImage("modifier.png", "This element is a modifier element", "M");
    if (e.isSummary()) 
      gc.addImage("summary.png", "This element is included in summaries", "Σ");
    if (!e.getInvariants().isEmpty() || !e.getStatedInvariants().isEmpty()) 
      gc.addImage("lock.png", "This element has or is affected by some invariants", "I");
  
    if (resource) {
      row.getCells().add(gen.new Cell()); 
  
      row.setIcon("icon_resource.png", HeirarchicalTableGenerator.TEXT_ICON_RESOURCE);
      if (Utilities.noString(e.typeCode()))
        row.getCells().add(gen.new Cell(null, null, "n/a", null, null)); 
      else
        row.getCells().add(gen.new Cell(null, e.typeCode().toLowerCase()+".html", e.typeCode(), null, null)); 
      // todo: base elements
    } else {
      if (!e.getElements().isEmpty()) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); 
        row.setIcon("icon_element.gif", HeirarchicalTableGenerator.TEXT_ICON_ELEMENT);
        row.getCells().add(gen.new Cell(null, "element.html", "Element", null, null));   
      } else if (e.getTypes().size() == 1) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); 
        String t = e.getTypes().get(0).getName();
        Cell c;
        if (t.startsWith("@")) {
          row.setIcon("icon_reuse.png", HeirarchicalTableGenerator.TEXT_ICON_REUSE);
          c = gen.new Cell("see ", "#"+t.substring(1), t.substring(t.lastIndexOf(".")+1), t.substring(1), null);
        } else if (t.equals("Reference")) {
          row.setIcon("icon_reference.png", HeirarchicalTableGenerator.TEXT_ICON_REFERENCE);
          c = gen.new Cell();
          boolean first = true;
          for (String rt : e.getTypes().get(0).getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            if (first && isProfile && e.getTypes().get(0).getProfile() != null)
              c.getPieces().add(gen.new Piece(null, e.getTypes().get(0).getProfile(), null));
            else
              c.getPieces().add(gen.new Piece(findPage(rt)+".html", rt, null));
            first = false;
          }
        } else if (definitions.getPrimitives().containsKey(t)) {
          row.setIcon("icon_primitive.png", HeirarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          c = gen.new Cell(null, "datatypes.html#"+t, t, null, null);
        } else {
          if (t.equals("Extension"))
            row.setIcon("icon_extension_simple.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION);
          else
            row.setIcon("icon_datatype.gif", HeirarchicalTableGenerator.TEXT_ICON_DATATYPE);
          c = gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null);
        }
        row.getCells().add(c);
      } else {
        row.getCells().add(gen.new Cell()); 
        row.setIcon("icon_choice.gif", HeirarchicalTableGenerator.TEXT_ICON_CHOICE);
        row.getCells().add(gen.new Cell(null, null, "", null, null));   
      }
    }
      
    Cell cc = gen.new Cell(null, Utilities.isURL(e.getShortDefn()) ? e.getShortDefn() : null, e.getShortDefn(), null, null);
    row.getCells().add(cc);
    
    // constraints
    if (isProfiledExtension) {
      cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, e.getTypes().get(0).getProfile(), null));
    }
    
    if (e.hasBinding() && definitions.getBindingByName(e.getBindingName()) != null && definitions.getBindingByName(e.getBindingName()).getBinding() != Binding.Unbound) {
      if (cc.getPieces().size() == 1)
        cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(getBindingLink(e), e.getBindingName(), definitions.getBindingByName(e.getBindingName()).getDefinition()));
      cc.getPieces().add(gen.new Piece(null, " (", null));
      BindingSpecification b = definitions.getBindingByName(e.getBindingName());
      if (b.getBindingStrength() == BindingStrength.Example) {
        cc.getPieces().add(gen.new Piece("terminologies.html#example",    "Example", "These codes are an example of the type of codes that may be used"));
      } else if (b.getBindingStrength() == BindingStrength.Preferred) {
        cc.getPieces().add(gen.new Piece("terminologies.html#incomplete", "Incomplete", "If one of the defined codes is appropriate, it must be used, or some other code may be used"));
      } else if (b.getBindingStrength() == BindingStrength.Required) {
        cc.getPieces().add(gen.new Piece("terminologies.html#code",       "Required",   "One of the the defined codes must be used"));
      } else {
        cc.getPieces().add(gen.new Piece(null, "??", null));
      }
      cc.getPieces().add(gen.new Piece(null, ")", null));
    }
    for (String name : e.getInvariants().keySet()) {
      Invariant inv = e.getInvariants().get(name);
      cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, inv.getEnglish(), inv.getId()).setStyle("font-style: italic"));
    }
    
    if (e.getTypes().size() > 1) {
      // create a child for each choice
      for (TypeRef tr : e.getTypes()) {
        Row choicerow = gen.new Row();
        String t = tr.getName();
        if (t.equals("Reference")) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  "Reference"), null, null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_reference.png", HeirarchicalTableGenerator.TEXT_ICON_REFERENCE);
          Cell c = gen.new Cell();
          choicerow.getCells().add(c);
          boolean first = true;
          for (String rt : tr.getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            c.getPieces().add(gen.new Piece(findPage(rt)+".html", rt, null));
            first = false;
          }
        } else if (definitions.getPrimitives().containsKey(t)) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getPrimitives().get(t).getDefinition(), null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_primitive.png", HeirarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          choicerow.getCells().add(gen.new Cell(null, "datatypes.html#"+t, t, null, null));
        } else {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell());
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_datatype.gif", HeirarchicalTableGenerator.TEXT_ICON_DATATYPE);
          choicerow.getCells().add(gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
        }
      
        choicerow.getCells().add(gen.new Cell());
//        choicerow.getCells().add(gen.new Cell());
        row.getSubRows().add(choicerow);
      }
    } else
      for (ElementDefn c : e.getElements())
        row.getSubRows().add(genElement(c, gen, false, path+'.'+c.getName(), isProfile));
    return row;
  }

  private String findPage(String rt) {
    if (rt.equalsIgnoreCase("any"))
      return "resourcelist";
    if (rt.equalsIgnoreCase("binary"))
      return "http";
    return rt.toLowerCase();
  }
}
