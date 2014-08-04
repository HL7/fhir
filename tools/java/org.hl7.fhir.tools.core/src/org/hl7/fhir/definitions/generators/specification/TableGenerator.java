package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Piece;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.genImage;

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
  
    if (resource) {
      row.getCells().add(gen.new Cell()); 
  
      row.setIcon("icon_resource.png");
      row.getCells().add(gen.new Cell(null, "resources.html", "Resource", null, null)); 
      // todo: base elements
    } else {
      
      if (!e.getElements().isEmpty()) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); 
        row.setIcon("icon_element.gif");
        row.getCells().add(gen.new Cell(null, null, "Element", null, null));   
      } else if (e.getTypes().size() == 1) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); 
        String t = e.getTypes().get(0).getName();
        Cell c;
        if (t.startsWith("@")) {
          row.setIcon("icon_reuse.png");
          c = gen.new Cell("see ", "#"+t.substring(1), t.substring(t.lastIndexOf(".")+1), t.substring(1), null);
        } else if (t.equals("Resource")) {
          row.setIcon("icon_reference.png");
          c = gen.new Cell();
          boolean first = true;
          for (String rt : e.getTypes().get(0).getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            if (first && isProfile && e.hasStatedProfile())
              c.getPieces().add(gen.new Piece(null, e.getStatedProfile(), null));
            else
              c.getPieces().add(gen.new Piece(findPage(rt)+".html", rt, null));
            first = false;
          }
        } else if (definitions.getPrimitives().containsKey(t)) {
          row.setIcon("icon_primitive.png");
          c = gen.new Cell(null, "datatypes.html#"+t, t, null, null);
        } else {
          if (t.equals("Extension"))
            row.setIcon("icon_extension_simple.png");
          else
            row.setIcon("icon_datatype.gif");
          c = gen.new Cell(null, GeneratorUtils.getSrcFile(t, false)+".html#"+t.replace("*", "open"), t, null, null);
        }
        row.getCells().add(c);
      } else {
        row.getCells().add(gen.new Cell()); 
        row.setIcon("icon_choice.gif");
        row.getCells().add(gen.new Cell(null, null, "", null, null));   
      }
    }
    Cell cc = gen.new Cell(null, null, e.getShortDefn(), null, null);
    row.getCells().add(cc);
    
    // constraints
    if (isProfiledExtension) {
      cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, e.getStatedProfile(), null));
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
      if (cc.getPieces().size() == 1)
        cc.addPiece(gen.new Piece("br"));
      cc.getPieces().add(gen.new Piece(null, inv.getEnglish(), inv.getId()));
    }
    
    if (e.getTypes().size() > 1) {
      // create a child for each choice
      for (TypeRef tr : e.getTypes()) {
        Row choicerow = gen.new Row();
        String t = tr.getName();
        if (t.equals("Resource")) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  "Resource"), null, null));
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_reference.png");
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
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_primitive.png");
          choicerow.getCells().add(gen.new Cell(null, "datatypes.html#"+t, t, null, null));
        } else {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));
          choicerow.setIcon("icon_datatype.gif");
          choicerow.getCells().add(gen.new Cell(null, GeneratorUtils.getSrcFile(t, false)+".html#"+t.replace("*", "open"), t, null, null));
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
