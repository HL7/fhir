package org.hl7.fhir.definitions.generators.specification;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r4.model.Enumerations.BindingStrength;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.utils.ToolingExtensions;
import org.hl7.fhir.tools.publisher.PageProcessor;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HierarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ResourceDependencyGenerator  extends BaseGenerator {
  public enum RenderMode {
    DATATYPE,
    RESOURCE,
    LOGICAL
  }
 
  protected String dest; 
  protected String pageName;
  protected boolean inlineGraphics;
  private StandardsStatus sstatus;
  private String fmm;
  
  public ResourceDependencyGenerator(String dest, PageProcessor page, String pageName, boolean inlineGraphics, String fmm, StandardsStatus sstatus) throws Exception {
    super();
    this.dest = dest;
    this.definitions = page.getDefinitions();
    this.page = page;
    this.pageName = pageName.toLowerCase();
    this.inlineGraphics = inlineGraphics;
    this.sstatus = sstatus;
    this.fmm = fmm;
  }

  public XhtmlNode generate(ElementDefn e, String prefix) throws Exception {
    HierarchicalTableGenerator gen = new HierarchicalTableGenerator(dest, inlineGraphics, true);
    RenderMode mode = RenderMode.RESOURCE;
    TableModel model = initTable(gen, prefix, mode == RenderMode.LOGICAL);

    
    model.getRows().add(genElement(e, gen, true, e.getName(), false, prefix, mode, true));
    
    return gen.generate(model, prefix, 0, null);
  }

  protected boolean dictLinks() {
    return pageName != null;
  }
  
  protected Row genElement(ElementDefn e, HierarchicalTableGenerator gen, boolean resource, String path, boolean isProfile, String prefix, RenderMode mode, boolean isRoot) throws Exception {
    Row row = gen.new Row();

    row.setAnchor(path);
    // 1. Name 
    Cell gc = gen.new Cell(null, dictLinks() ? pageName+"#"+path.replace("[", "_").replace("]", "_") : null, e.getName(), path+" : "+e.getDefinition(), null);
    row.getCells().add(gc);
    if (e.getStandardsStatus() != null) {
      gc.addPiece(gen.new Piece(null, " ", null));
      gc.addStyledText("Ballot Status = "+e.getStandardsStatus().toDisplay(), e.getStandardsStatus().getAbbrev(), "black", e.getStandardsStatus().getColor(), prefix+"versions.html#std-process", true);
    }
       
    Cell dc;
    if (resource) {
      row.getCells().add(gen.new Cell()); // card. 
  
      row.setIcon("icon_resource.png", HierarchicalTableGenerator.TEXT_ICON_RESOURCE);
      if (Utilities.noString(e.typeCode())) {
        row.getCells().add(gen.new Cell(null, null, "n/a", null, null)); 
        row.getCells().add(dc = gen.new Cell()); // analysis 
      } else {
        row.getCells().add(gen.new Cell(null, prefix+e.typeCode().toLowerCase()+".html", e.typeCode(), null, null)); // type
        row.getCells().add(dc = gen.new Cell(null, null, path.contains(".") ? e.describeCardinality() : "", null, null)); // analysis
      }
    } else {
      if (!e.getElements().isEmpty()) {
        row.getCells().add(gen.new Cell(null, null, path.contains(".") ? e.describeCardinality() : "", null, null)); // card.
        row.setIcon("icon_element.gif", HierarchicalTableGenerator.TEXT_ICON_ELEMENT);
        if (mode == RenderMode.RESOURCE)
          row.getCells().add(gen.new Cell(null, prefix+"backboneelement.html", "BackboneElement", null, null));
        else if (e.getName().equals("Element"))
          row.getCells().add(gen.new Cell(null, null, "n/a", null, null)); 
        else
          row.getCells().add(gen.new Cell(null, prefix+"element.html", "Element", null, null));   
        row.getCells().add(dc = gen.new Cell()); // analysis 
      } else if (e.getTypes().size() == 1) {
        row.getCells().add(gen.new Cell(null, null, path.contains(".") ? e.describeCardinality() : "", null, null)); // card.
        String t = e.getTypes().get(0).getName();
        Cell c;
        if (t.startsWith("@")) {
          row.setIcon("icon_reuse.png", HierarchicalTableGenerator.TEXT_ICON_REUSE);
          row.getCells().add(c = gen.new Cell("see ", "#"+t.substring(1), t.substring(t.lastIndexOf(".")+1), t.substring(1), null));
          row.getCells().add(dc = gen.new Cell()); // analysis 
        } else if (t.equals("Reference") || t.equals("canonical")) {
          row.setIcon("icon_reference.png", HierarchicalTableGenerator.TEXT_ICON_REFERENCE);
          row.getCells().add(c = gen.new Cell());
          c.getPieces().add(gen.new Piece(prefix+"references.html", t, null));
          c.getPieces().add(gen.new Piece(null, "(", null));
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
          c.getPieces().add(gen.new Piece(null, ")", null));
          row.getCells().add(dc = gen.new Cell()); // analysis 
          for (String rt : e.getTypes().get(0).getParams()) 
            addTypeToAnalysis(gen, row, dc, true, e.getStandardsStatus(), rt);
        } else if (definitions.getPrimitives().containsKey(t)) {
          row.setIcon("icon_primitive.png", HierarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          row.getCells().add(c = gen.new Cell(null, prefix+"datatypes.html#"+t, t, null, null));
          row.getCells().add(dc = gen.new Cell()); // analysis 
          addTypeToAnalysis(gen, row, dc, false, e.getStandardsStatus(), e.typeCode());
        } else {
          if (t.equals("Extension"))
            row.setIcon("icon_extension_simple.png", HierarchicalTableGenerator.TEXT_ICON_EXTENSION);
          else
            row.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          row.getCells().add(c = gen.new Cell(null, prefix+definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
          row.getCells().add(dc = gen.new Cell()); // analysis 
          addTypeToAnalysis(gen, row, dc, false, e.getStandardsStatus(), t);
        }
      } else {
        row.getCells().add(gen.new Cell(null, null, e.describeCardinality(), null, null));   
        row.setIcon("icon_choice.gif", HierarchicalTableGenerator.TEXT_ICON_CHOICE);
        row.getCells().add(gen.new Cell(null, null, "", null, null));   
        row.getCells().add(dc = gen.new Cell()); // analysis 
      }
    }
         
    if (e.hasBinding() && e.getBinding() != null && e.getBinding().getBinding() != BindingMethod.Unbound && (e.getBinding().getStrength() == BindingStrength.REQUIRED || e.getBinding().getStrength() == BindingStrength.EXTENSIBLE) ) {
      addBindingToAnalysis(gen, row, dc, e.getBinding().getStrength() == BindingStrength.REQUIRED, e.getStandardsStatus(), e.getBinding());
      
//      if (cc.getPieces().size() == 1)
//        cc.addPiece(gen.new Piece("br"));
//      cc.getPieces().add(gen.new Piece(getBindingLink(prefix, e), e.getBinding().getValueSet() != null ? e.getBinding().getValueSet().getName() : e.getBinding().getName(), 
//            e.getBinding().getDefinition()));
//      cc.getPieces().add(gen.new Piece(null, " (", null));
//      BindingSpecification b = e.getBinding();
//      if (b.hasMax() ) {
//        cc.getPieces().add(gen.new Piece(prefix+"terminologies.html#"+b.getStrength().toCode(), b.getStrength().getDisplay(),  b.getStrength().getDefinition()));
//        cc.getPieces().add(gen.new Piece(null, " but limited to ", null));
//        ValueSet vs = b.getMaxValueSet();
//        cc.getPieces().add(gen.new Piece(vs.getUserString("path"), vs.getName(), null));
//      }  else
//        cc.getPieces().add(gen.new Piece(prefix+"terminologies.html#"+b.getStrength().toCode(), b.getStrength().getDisplay(),  b.getStrength().getDefinition()));
//      cc.getPieces().add(gen.new Piece(null, ")", null));
    }
         
    if (e.getTypes().size() > 1) {
      // create a child for each choice
      for (TypeRef tr : e.getTypes()) {
        Row choicerow = gen.new Row();
        String t = tr.getName();
        if (t.equals("Reference")) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  "Reference"), null, null));
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_reference.png", HierarchicalTableGenerator.TEXT_ICON_REFERENCE);
          Cell c = gen.new Cell();
          choicerow.getCells().add(c);
            c.getPieces().add(gen.new Piece(prefix+"references.html", "Reference", null));
            c.getPieces().add(gen.new Piece(null, "(", null));
          boolean first = true;
          for (String rt : tr.getParams()) {
            if (!first)
              c.getPieces().add(gen.new Piece(null, " | ", null));
            c.getPieces().add(gen.new Piece(prefix+findPage(rt)+".html", rt, null));
            first = false;
          }
          choicerow.getCells().add(dc = gen.new Cell()); // analysis 
          for (String rt : e.getTypes().get(0).getParams()) 
            addTypeToAnalysis(gen, choicerow, dc, true, e.getStandardsStatus(), rt);
          
        } else if (definitions.getPrimitives().containsKey(t)) {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getPrimitives().get(t).getDefinition(), null));
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_primitive.png", HierarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
          choicerow.getCells().add(gen.new Cell(null, prefix+"datatypes.html#"+t, t, null, null));
          choicerow.getCells().add(dc = gen.new Cell()); // analysis 
          addTypeToAnalysis(gen, choicerow, dc, false, e.getStandardsStatus(), t);
        } else if (definitions.getConstraints().containsKey(t)) {
          ProfiledType pt = definitions.getConstraints().get(t);
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]", Utilities.capitalize(pt.getBaseType())), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          choicerow.getCells().add(gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
          choicerow.getCells().add(dc = gen.new Cell()); // analysis 
          addTypeToAnalysis(gen, choicerow, dc, false, e.getStandardsStatus(), t);
        } else {
          choicerow.getCells().add(gen.new Cell(null, null, e.getName().replace("[x]",  Utilities.capitalize(t)), definitions.getTypes().containsKey(t) ? definitions.getTypes().get(t).getDefinition() : null, null));
          choicerow.getCells().add(gen.new Cell(null, null, "", null, null));
          choicerow.setIcon("icon_datatype.gif", HierarchicalTableGenerator.TEXT_ICON_DATATYPE);
          choicerow.getCells().add(gen.new Cell(null, definitions.getSrcFile(t)+".html#"+t.replace("*", "open"), t, null, null));
          choicerow.getCells().add(dc = gen.new Cell()); // analysis 
          addTypeToAnalysis(gen, choicerow, dc, false, e.getStandardsStatus(), t);
        }
      
        row.getSubRows().add(choicerow);
      }
    } else
      for (ElementDefn c : e.getElements())
        row.getSubRows().add(genElement(c, gen, false, path+'.'+c.getName(), isProfile, prefix, mode, false));
    return row;
  }

  
  private void addBindingToAnalysis(HierarchicalTableGenerator gen, Row row, Cell dc, boolean req, StandardsStatus elementStatus, BindingSpecification binding) throws FHIRException {
    String tgtFMM = null;
    StandardsStatus tgtSS = null;
    ValueSet vs = binding.getValueSet();
    if (vs != null) {
      tgtFMM = ToolingExtensions.readStringExtension(vs, ToolingExtensions.EXT_FMM_LEVEL);
      tgtSS = ToolingExtensions.getStandardsStatus(vs);
    } else if (Utilities.existsInList(binding.getReference(), "http://www.rfc-editor.org/bcp/bcp13.txt")) {
      tgtFMM = "5";
      tgtSS = StandardsStatus.EXTERNAL;      
    }
    if (elementStatus == null)
      elementStatus = sstatus;
    
    if (tgtFMM == null)
      addError(gen, row, dc, "Binding Error: Unable to resolve vs '"+binding.getReference()+"' to check dependencies", null);
    else {
      boolean ok = elementStatus.canDependOn(tgtSS);
      if (ok)
        ok = fmm.compareTo(tgtFMM) <= 0;
      if (ok)
        ; // addInfo(gen, row, dc, "Binding OK (ValueSet = FMM"+tgtFMM+"-"+tgtSS.toDisplay()+" vs. Element = FMM"+fmm+"-"+elementStatus.toDisplay()+")", null);
      else
        addError(gen, row, dc, "Binding Error: (ValueSet = FMM"+tgtFMM+"-"+tgtSS.toDisplay()+" vs. Element = FMM"+fmm+"-"+elementStatus.toDisplay()+")", vs.getUserString("path"));
    }      
  }

  private void addTypeToAnalysis(HierarchicalTableGenerator gen, Row row, Cell dc, boolean ref, StandardsStatus elementStatus, String type) throws Exception {
    String tgtFMM = null;
    StandardsStatus tgtSS = null;
    if (definitions.getConstraints().containsKey(type))
      type = definitions.getConstraints().get(type).getBaseType();
    
    if (definitions.hasResource(type)) {
      ResourceDefn r = definitions.getResourceByName(type);
      tgtFMM = r.getFmmLevel();
      tgtSS = r.getStatus();
    } else if (definitions.getBaseResources().containsKey(type)) {
       ResourceDefn r = definitions.getBaseResources().get(type);
       tgtFMM = r.getFmmLevel();
       tgtSS = r.getStatus();
    } else if ("Any".equals(type)) {
      tgtFMM = "1";
      tgtSS = StandardsStatus.TRIAL_USE;
    } else if (definitions.hasPrimitiveType(type)) {
      tgtFMM = "5";
      tgtSS = StandardsStatus.NORMATIVE;
    } else if ("*".equals(type)) {
      // todo: what...?
      tgtFMM = "2";
      tgtSS = StandardsStatus.TRIAL_USE;
    } else {
      TypeDefn t = definitions.getElementDefn(type);
      if (t != null) {
        tgtFMM = t.getFmmLevel();
        tgtSS = t.getStandardsStatus();
      }
    }
    if (elementStatus == null)
      elementStatus = sstatus;
    
    if (tgtFMM == null)
      addError(gen, row, dc, "Error: Unable to resolve type '"+type+"' to check dependencies", null);
    else {
      boolean ok = elementStatus.canDependOn(tgtSS);
      if (ok)
        ok = fmm.compareTo(tgtFMM) <= 0;
      if (ok)
        ; // addInfo(gen, row, dc, "OK ("+type+" = FMM"+tgtFMM+"-"+tgtSS.toDisplay()+" vs. Element = FMM"+fmm+"-"+elementStatus.toDisplay()+")", null);
      else if (ref)
        addWarning(gen, row, dc, "Type Warning: ("+type+" = FMM"+tgtFMM+"-"+tgtSS.toDisplay()+" vs. Element = FMM"+fmm+"-"+elementStatus.toDisplay()+")", null);
      else
        addError(gen, row, dc, "Type Error: ("+type+" = FMM"+tgtFMM+"-"+tgtSS.toDisplay()+" vs. Element = FMM"+fmm+"-"+elementStatus.toDisplay()+")", null);
    }      
  }

  private void addInfo(HierarchicalTableGenerator gen, Row row, Cell cell, String text, String link) {
    if (cell.getPieces().size() > 0)
      cell.addPiece(gen.new Piece("br"));
    cell.addPiece(gen.new Piece(link, text, null));
  }

  private void addError(HierarchicalTableGenerator gen, Row row, Cell cell, String text, String link) {
    row.setColor("#ffe6e6");
    addInfo(gen, row, cell, text, link);
  }

  private void addWarning(HierarchicalTableGenerator gen, Row row, Cell cell, String text, String link) {
    if (!"#ffe6e6".equals(row.getColor()))
      row.setColor("#fff0b3");
    addInfo(gen, row, cell, text, link);
  }

  private TableModel initTable(HierarchicalTableGenerator gen, String prefix, boolean b) {

    TableModel model = gen.new TableModel();

    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Name", "The logical name of the element", null, 0));
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Card.", "Minimum and Maximum # of times the the element can appear in the instance", null, 0));
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Type", "Reference to the type of the element", null, 100));
    model.getTitles().add(gen.new Title(null, model.getDocoRef(), "Dependency Analysis", "Additional information about the element", null, 0));
    return model;
  }

  private boolean hasLogicalMapping(ElementDefn e) {
    if (e.getMappings().containsKey("http://hl7.org/fhir/logical"))
        return true;
    for (ElementDefn c : e.getElements())
      if (hasLogicalMapping(c))
        return true;
    return false;
  }


  private String findPage(String rt) {
    if (rt.equalsIgnoreCase("any"))
      return "resourcelist";
    if (rt.equalsIgnoreCase("binary"))
      return "http";
    return rt.toLowerCase();
  }
}
