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
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Piece;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;

public class TableGenerator extends BaseGenerator {
  protected String dest; 
  
  public TableGenerator(String dest, PageProcessor page) {
    super();
    this.dest = dest;
    this.definitions = page.getDefinitions();
    this.page = page;
  }

  protected Row genElement(ElementDefn e, HeirarchicalTableGenerator gen, boolean resource) throws Exception {
    Row row = gen.new Row();
  
    row.getCells().add(gen.new Cell(null, null, e.getName(), e.getDefinition(), null));
  
    if (resource) {
      row.getCells().add(gen.new Cell()); 
  
      row.setIcon("icon_resource.png");
      row.getCells().add(gen.new Cell(null, "resources.html", "Resource", null, null)); 
      // todo: base elements
    } else {
      
      if (!e.getElements().isEmpty()) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); // todo: invariants
        row.setIcon("icon_element.gif");
        row.getCells().add(gen.new Cell()); // todo: extensions?  
      } else if (e.getTypes().size() == 1) {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null)); // todo: invariants
        String t = e.getTypes().get(0).getName();
        Cell c;
        if (t.startsWith("@")) {
          row.setIcon("icon_reuse.png");
          c = gen.new Cell("see ", "test", t.substring(t.lastIndexOf(".")+1), t.substring(1), null);
        } else if (t.equals("Resource")) {
          row.setIcon("icon_reference.png");
          c = gen.new Cell();
          boolean first = true;
          for (String rt : e.getTypes().get(0).getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            c.getPieces().add(gen.new Piece(rt+".html", rt, null));
            first = false;
          }
        } else if (definitions.getPrimitives().containsKey(t)) {
          row.setIcon("icon_primitive.png");
          c = gen.new Cell(null, "datatypes.html#"+t, t, null, null);
        } else {
          row.setIcon("icon_datatype.gif");
          c = gen.new Cell(null, "datatypes.html#"+t, t, null, null);
        }
        row.getCells().add(c);
      } else {
        row.getCells().add(gen.new Cell()); 
        row.setIcon("icon_choice.gif");
        row.getCells().add(gen.new Cell(null, null, "", null, null));   
      }
    }
    row.getCells().add(gen.new Cell(null, null, e.getShortDefn(), null, null));
    
    // constraints
    Cell cc = gen.new Cell();
    row.getCells().add(cc);
  
    if (e.hasBinding() && definitions.getBindingByName(e.getBindingName()) != null && definitions.getBindingByName(e.getBindingName()).getBinding() != Binding.Unbound) {
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
      if (!cc.getPieces().isEmpty())
        cc.getPieces().add(gen.new Piece("br"));
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
            c.getPieces().add(gen.new Piece(rt+".html", rt, null));
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
          choicerow.getCells().add(gen.new Cell(null, "datatypes.html#"+t, t, null, null));
        }
      
        choicerow.getCells().add(gen.new Cell());
        choicerow.getCells().add(gen.new Cell());
        row.getSubRows().add(choicerow);
      }
    } else
      for (ElementDefn c : e.getElements())
        row.getSubRows().add(genElement(c, gen, false));
    return row;
  }
}
