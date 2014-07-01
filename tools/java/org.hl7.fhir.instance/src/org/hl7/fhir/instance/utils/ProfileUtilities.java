package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementSlicingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ResourceSlicingRules;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.instance.model.Uri;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Piece;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;

public class ProfileUtilities {

  public static class ExtensionDefinition {
    private String url;
    private ProfileExtensionDefnComponent defn;
    public ExtensionDefinition(String url, ProfileExtensionDefnComponent defn) {
      super();
      this.url = url;
      this.defn = defn;
    }
    public String getUrl() {
      return url;
    }
    public ProfileExtensionDefnComponent getDefn() {
      return defn;
    }
    
  }

  public interface ProfileKnowledgeProvider {
    boolean isDatatype(String typeSimple);
    boolean hasLinkFor(String typeSimple);
    String getLinkFor(String typeSimple) throws Exception;
    ExtensionDefinition getExtensionDefinition(Profile profile, String profileReference);
  }

  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, ElementComponent element) {
  	return getChildMap(structure, element.getPathSimple());
  }
  
  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, String path) {
    HashMap<String, ElementComponent> res = new HashMap<String, Profile.ElementComponent>(); 
    for (ElementComponent e : structure.getSnapshot().getElement()) {
      String p = e.getPathSimple();
      if (!Utilities.noString(e.getDefinition().getNameReferenceSimple()) && path.startsWith(p)) {
        if (path.length() > p.length())
          return getChildMap(structure, e.getDefinition().getNameReferenceSimple()+"."+path.substring(p.length()+1));
        else
          return getChildMap(structure, e.getDefinition().getNameReferenceSimple());
      } else if (p.startsWith(path+".") && !p.equals(path)) {
          String tail = p.substring(path.length()+1);
          if (!tail.contains(".")) {
            res.put(tail, e);
          }
        }

      }
    return res;
  }

  public static List<ElementComponent> getChildList(ProfileStructureComponent structure, ElementComponent element) {
  	return getChildList(structure, element.getPathSimple());
  }
  
  public static List<ElementComponent> getChildList(ProfileStructureComponent structure, String path) {
    List<ElementComponent> res = new ArrayList<Profile.ElementComponent>(); 
    for (ElementComponent e : structure.getSnapshot().getElement()) {
      String p = e.getPathSimple();
      if (!Utilities.noString(e.getDefinition().getNameReferenceSimple()) && path.startsWith(p)) {
        if (path.length() > p.length())
          return getChildList(structure, e.getDefinition().getNameReferenceSimple()+"."+path.substring(p.length()+1));
        else
          return getChildList(structure, e.getDefinition().getNameReferenceSimple());
      } else if (p.startsWith(path+".") && !p.equals(path)) {
          String tail = p.substring(path.length()+1);
          if (!tail.contains(".")) {
            res.add(e);
          }
        }

      }
    return res;
  }


  /**
   * Given a base (snapshot) profile structure, and a differential profile, generate a new snapshot profile
   *  
   * @param base
   * @param differential
   * @return
   * @throws Exception 
   */
  public void generateSnapshot(ProfileStructureComponent base, ProfileStructureComponent derived) throws Exception {
    if (base == null)
      throw new Exception("no base profile provided");
    if (derived == null) 
      throw new Exception("no derived structure provided");
    if (!derived.getTypeSimple().equals(base.getTypeSimple()))
      throw new Exception("Mismatch types between base and snapshot");
      
    derived.setSnapshot(new ConstraintComponent());
    
    // so we have two lists - the base list, and the differential list 
    // the differential list is only allowed to include things that are in the base list, but 
    // is allowed to include them multiple times - thereby slicing them
    
    // our approach is to walk through the base list, and see whether the differential 
    // says anything about them. 
    int baseCursor = 0;
    int diffCursor = 0; // we need a diff cursor because we can only look ahead, in the bound scoped by longer paths
    
    // we actually delegate the work to a subroutine so we can re-enter it with a different cursors
    processPaths(derived.getSnapshot(), base.getSnapshot(), derived.getDifferential(), baseCursor, diffCursor, base.getSnapshot().getElement().size()-1, derived.getDifferential().getElement().size()-1);
  }

  /**
   * @throws Exception 
   */
  private void processPaths(ConstraintComponent result, ConstraintComponent base, ConstraintComponent differential, int baseCursor, int diffCursor, int baseLimit, int diffLimit) throws Exception {
    
    // just repeat processing entries until we run out of our allowed scope (1st entry, the allowed scope is all the entries)
    while (baseCursor <= baseLimit) {
      // get the current focus of the base, and decide what to do
      ElementComponent currentBase = base.getElement().get(baseCursor); 
      // in the simple case, source is not sliced. 
      if (currentBase.getSlicing() == null) {
        List<ElementComponent> diffMatches = getDiffMatches(differential, currentBase.getPathSimple(), diffCursor, diffLimit); // get a list of matching elements in scope
        if (diffMatches.isEmpty()) { // the differential doesn't say anything about this item 
          result.getElement().add(currentBase.copy()); // so we just copy it in
          baseCursor++;
        } else if (diffMatches.size() == 1) {// one matching element in the differential
          ElementComponent outcome = currentBase.copy();
          updateFromDefinition(outcome, diffMatches.get(0));
          result.getElement().add(outcome);
          baseCursor++;
          diffCursor = differential.getElement().indexOf(diffMatches.get(0))+1;
        } else {
          // ok, the differential slices the item. Let's check our pre-conditions to ensure that this is correct
          if (!unbounded(currentBase.getDefinition())) // query - are you allowed to slice one that doesn't? to be resolved later
            throw new Exception("Attempt to a slice an element that does not repeat"); 
          if (diffMatches.get(0).getSlicing() == null && !isExtension(currentBase)) // well, the diff has set up a slice, but hasn't defined it. this is an error
            throw new Exception("differential does not have a slice"); 
            
          // well, if it passed those preconditions then we slice the dest. 
          // we're just going to accept the differential slicing at face value
          ElementComponent outcome = currentBase.copy();
          updateFromDefinition(outcome, diffMatches.get(0));
          if (diffMatches.get(0).getSlicing() == null) 
            outcome.setSlicing(makeExtensionSlicing());
          else            
            outcome.setSlicing(diffMatches.get(0).getSlicing().copy());
          result.getElement().add(outcome);

          // now, for each entry in the diff matches, we're going to process the base item 
          // our processing scope for base is all the children of the current path
          int nbl = findEndOfElement(base, baseCursor);
          int ndc = diffCursor;
          int ndl = diffCursor;
          for (int i = 1; i < diffMatches.size(); i++) {
            // our processing scope for the differential is the item in the list, and all the items before the next one in the list
            ndc = differential.getElement().indexOf(diffMatches.get(i));
            ndl = findEndOfElement(differential, ndc);
            // now we process the base scope repeatedly for each instance of the item in the differential list
            processPaths(result, base, differential, baseCursor, ndc, nbl, ndl);
          }
          // ok, done with that - next in the base list
          baseCursor = nbl+1;
          diffCursor = ndl+1;
        }
      } else {
        // the item is already sliced in the base profile.
        // we have to sort out the rules for how merging slicing works
        throw new Exception("not done yet");
      }
    }      
  }

  private ElementSlicingComponent makeExtensionSlicing() {
    ElementSlicingComponent slice = new ElementSlicingComponent();
    slice.setDiscriminatorSimple("url");
    slice.setOrderedSimple(true);
    slice.setRulesSimple(ResourceSlicingRules.openAtEnd);
    return slice;
  }

  private boolean isExtension(ElementComponent currentBase) {
    return currentBase.getPathSimple().endsWith(".extension") || currentBase.getPathSimple().endsWith(".modifierExtension");
  }

  private List<ElementComponent> getDiffMatches(ConstraintComponent context, String path, int start, int end) {
    List<ElementComponent> result = new ArrayList<Profile.ElementComponent>();
    for (int i = start; i <= end; i++) {
      if (context.getElement().get(i).getPathSimple().equals(path)) {
        result.add(context.getElement().get(i));
      }
    }
    return result;
  }

  private int findEndOfElement(ConstraintComponent context, int cursor) {
    int result = cursor;
    String path = context.getElement().get(cursor).getPathSimple()+".";
    while (result < context.getElement().size()- 1 && context.getElement().get(result+1).getPathSimple().startsWith(path))
      result++;
    return result;
  }

  private boolean unbounded(ElementDefinitionComponent definition) {
    String_ max = definition.getMax();
    if (max == null)
      return false; // this is not valid
    if (max.getValue().equals("1"))
      return false;
    if (max.getValue().equals("0"))
      return false;
    return true;
  }

  private void updateFromDefinition(ElementComponent dest, ElementComponent source) {
    if (dest.getDefinition() == null) 
      dest.setDefinition(new ElementDefinitionComponent());

    ElementDefinitionComponent dst = dest.getDefinition();
    ElementDefinitionComponent src = source.getDefinition();
    
    if (src != null) {
      if (src.getShort() != null)
        dst.setShort(src.getShort().copy());
      if (src.getFormal() != null)
        dst.setFormal(src.getFormal().copy());
      if (src.getComments() != null)
        dst.setComments(src.getComments().copy());
      if (src.getRequirements() != null)
        dst.setRequirements(src.getRequirements().copy());
      for (String_ s : src.getSynonym()) {
        if (!dst.hasSynonymSimple(s.getValue()))
          dst.getSynonym().add(s.copy());
      }
      if (src.getMin() != null)
        dst.setMin(src.getMin().copy());
      if (src.getMax() != null)
        dst.setMax(src.getMax().copy());
      if (src.getValue() != null)
        dst.setValue(src.getValue().copy());
      if (src.getExample() != null)
        dst.setExample(src.getExample().copy());
      if (src.getMaxLength() != null)
        dst.setMaxLength(src.getMaxLength().copy());
      // todo: what to do about conditions? 
      // condition : id 0..*
      if (src.getMustSupport() != null)
        dst.setMustSupport(src.getMustSupport().copy());
      // profiles cannot change : isModifier
      if (src.getBinding() != null)
        dst.setBinding(src.getBinding().copy());
      
      // todo: is this actually right? 
      dst.getType().clear();
      for (TypeRefComponent t : src.getType())
        dst.getType().add(t.copy());
      
      // todo: mappings are cumulative - or does one replace another?
      dst.getMapping().addAll(src.getMapping());
      
      // todo: constraints are cumulative - or does one replace another?
      dst.getConstraint().addAll(src.getConstraint());
    }
    
  }
  
  public XhtmlNode generateExtensionsTable(Profile profile, String imageFolder, boolean inlineGraphics, ProfileKnowledgeProvider pkp) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(imageFolder, inlineGraphics);
    TableModel model = gen.initNormalTable();
    
    Row re = gen.new Row();
    model.getRows().add(re);
    re.setIcon("icon_profile.png");
    re.getCells().add(gen.new Cell(null, null, "Extensions", null, null));
    re.getCells().add(gen.new Cell());
    re.getCells().add(gen.new Cell());
    re.getCells().add(gen.new Cell(null, null, "Extensions defined by the URL \""+profile.getUrlSimple()+"\"", null, null));

    for (ProfileExtensionDefnComponent ext : profile.getExtensionDefn()) {
      genExtension(gen, re.getSubRows(), ext, profile, pkp);
    }
    return gen.generate(model);
  }
  
  private void genExtension(HeirarchicalTableGenerator gen, List<Row> rows, ProfileExtensionDefnComponent ext, Profile profile, ProfileKnowledgeProvider pkp) throws Exception {
    Row r = gen.new Row();
    rows.add(r);
    ElementComponent e = ext.getElement().get(0);
    r.getCells().add(gen.new Cell(null, null, ext.getCodeSimple(), e.getDefinition().getFormalSimple(), null));
    r.getCells().add(gen.new Cell(null, null, describeCardinality(e.getDefinition()), null, null));
    if (ext.getElement().size() == 1) {
      r.setIcon("icon_extension_simple.png");
      genTypes(gen, pkp, r, e);
    } else {
      r.setIcon("icon_extension_complex.png");
      r.getCells().add(gen.new Cell());
    }

    r.getCells().add(gen.new Cell(null, null, e.getDefinition().getShortSimple(), null, null).addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, describeExtensionContext(ext), null)));
    List<ElementComponent> children = getChildren(ext.getElement(), e);
    for (ElementComponent child : children)
      genElement(gen, r.getSubRows(), child, ext.getElement(), profile, pkp);
  }

  private void genTypes(HeirarchicalTableGenerator gen, ProfileKnowledgeProvider pkp, Row r, ElementComponent e) throws Exception {
    Cell c = gen.new Cell();
    r.getCells().add(c);
    boolean first = true;
    for (TypeRefComponent t : e.getDefinition().getType()) {
      if (first) first = false; else c.addPiece(gen.new Piece(null,", ", null));
      if (t.getCodeSimple().equals("ResourceReference")) {
        if (t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/")) {
          String rn = t.getProfileSimple().substring(28);
          c.addPiece(gen.new Piece(pkp.getLinkFor(rn), rn, null));
        } else
          c.addPiece(gen.new Piece(t.getProfileSimple(), t.getProfileSimple(), null));
      } else if (pkp.hasLinkFor(t.getCodeSimple())) {
        c.addPiece(gen.new Piece(pkp.getLinkFor(t.getCodeSimple()), t.getCodeSimple(), null));
      } else
        c.addPiece(gen.new Piece(null, t.getCodeSimple(), null));
    }
  }
  
  private String describeExtensionContext(ProfileExtensionDefnComponent ext) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String_ t : ext.getContext())
      b.append(t.getValue());
    switch (ext.getContextTypeSimple()) {
    case datatype: return "Use on data type: "+b.toString();
    case extension: return "Use on extension: "+b.toString();
    case resource: return "Use on element: "+b.toString();
    case mapping: return "Use where element has mapping: "+b.toString();
    default:
      return "??";
    }
  }

  private String describeCardinality(ElementDefinitionComponent definition) {
      return Integer.toString(definition.getMinSimple()) + ".." + definition.getMaxSimple();
  }

  public XhtmlNode generateTable(ProfileStructureComponent structure, boolean diff, String imageFolder, boolean inlineGraphics, Profile profile, ProfileKnowledgeProvider pkp) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(imageFolder, inlineGraphics);
    TableModel model = gen.initNormalTable();
    List<ElementComponent> list = diff ? structure.getDifferential().getElement() : structure.getSnapshot().getElement();
    genElement(gen, model.getRows(), list.get(0), list, profile, pkp);
    return gen.generate(model);
  }

  private void genElement(HeirarchicalTableGenerator gen, List<Row> rows, ElementComponent element, List<ElementComponent> all, Profile profile, ProfileKnowledgeProvider pkp) throws Exception {
    Row row = gen.new Row();
    rows.add(row);
    row.setAnchor(element.getPathSimple());
    String s = tail(element.getPathSimple());
    boolean hasDef = element.getDefinition() != null;
    boolean ext = false;
    if (s.equals("extension") || s.equals("modifierExtension")) { 
      row.setIcon("icon_extension_simple.png");
      ext = true;
    } else if (!hasDef || element.getDefinition().getType().size() == 0)
      row.setIcon("icon_element.gif");
    else if (hasDef && element.getDefinition().getType().size() > 1) {
      if (allTypesAre(element.getDefinition().getType(), "ResourceReference"))
        row.setIcon("icon_reference.png");
      else
        row.setIcon("icon_choice.gif");
    } else if (hasDef && element.getDefinition().getType().get(0).getCode().getValue().startsWith("@"))
      row.setIcon("icon_reuse.png");
    else if (hasDef && isPrimitive(element.getDefinition().getType().get(0).getCode().getValue()))
      row.setIcon("icon_primitive.png");
    else if (hasDef && isReference(element.getDefinition().getType().get(0).getCode().getValue()))
      row.setIcon("icon_reference.png");
    else if (hasDef && isDataType(element.getDefinition().getType().get(0).getCode().getValue()))
      row.setIcon("icon_datatype.gif");
    else
      row.setIcon("icon_resource.png");
    String ref = null;
    row.getCells().add(gen.new Cell(null, ref, s, !hasDef ? null : element.getDefinition().getFormalSimple(), null));
    row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition()), null, null));
    if (ext) {
      if (element.getDefinition() != null && element.getDefinition().getType().size() == 1 && element.getDefinition().getType().get(0).getProfile() != null) {
        ExtensionDefinition extDefn = pkp.getExtensionDefinition(profile, element.getDefinition().getType().get(0).getProfileSimple());
        if (extDefn == null) {
          row.getCells().add(gen.new Cell());
          row.getCells().add(gen.new Cell(null, null, !hasDef ? null : element.getDefinition().getShortSimple(), null, null));                
        } else {
          genTypes(gen, pkp, row, extDefn.getDefn().getElement().get(0));
          row.getCells().add(gen.new Cell(null, null, extDefn.getDefn().getElement().get(0).getDefinition().getFormalSimple(), null, null));
        }
      } else if (element.getDefinition() != null) {
        genTypes(gen, pkp, row, element);
        row.getCells().add(gen.new Cell(null, null, !hasDef ? null : element.getDefinition().getShortSimple(), null, null));      
      } else {
        row.getCells().add(gen.new Cell());
        row.getCells().add(gen.new Cell(null, null, !hasDef ? null : element.getDefinition().getShortSimple(), null, null));      
      }
    } else {
      if (hasDef)
        genTypes(gen, pkp, row, element);
      else
        row.getCells().add(gen.new Cell());
      row.getCells().add(gen.new Cell(null, null, !hasDef ? null : element.getDefinition().getShortSimple(), null, null));
    }
    List<ElementComponent> children = getChildren(all, element);
    for (ElementComponent child : children)
      genElement(gen, row.getSubRows(), child, all, profile, pkp);
  }

  private boolean allTypesAre(List<TypeRefComponent> types, String name) {
    for (TypeRefComponent t : types) {
      if (!t.getCodeSimple().equals(name))
        return false;
    }
    return true;
  }

  private List<ElementComponent> getChildren(List<ElementComponent> all, ElementComponent element) {
    List<ElementComponent> result = new ArrayList<Profile.ElementComponent>();
    int i = all.indexOf(element)+1;
    while (i < all.size() && all.get(i).getPathSimple().length() > element.getPathSimple().length()) {
      if ((all.get(i).getPathSimple().substring(0, element.getPathSimple().length()+1).equals(element.getPathSimple()+".")) && !all.get(i).getPathSimple().substring(element.getPathSimple().length()+1).contains(".")) 
        result.add(all.get(i));
      i++;
    }
    return result;
  }

  private String tail(String path) {
    if (path.contains("."))
      return path.substring(path.lastIndexOf('.')+1);
    else
      return path;
  }

  private boolean isDataType(String value) {
    return Utilities.existsInList(value, "Identifier", "HumanName", "Address", "Contact", "Schedule", "Quantity", "Attachment", "Range", 
          "Period", "Ratio", "CodeableConcept", "Coding", "SampledData", "Age", "Distance", "Duration", "Count", "Money");
  }

  private boolean isReference(String value) {
    return value.equals("ResourceReference");
  }

  private boolean isPrimitive(String value) {
    return Utilities.existsInList(value, "boolean", "integer", "decimal", "base64Binary", "instant", "string", "date", "dateTime", "code", "oid", "uuid", "id");
  }

  public static String summarise(Profile p, ProfileKnowledgeProvider pkp) throws Exception {
    if (p.getExtensionDefn().isEmpty())
      return "This profile has constraints on the following resources: "+listStructures(p, pkp);
    else if (p.getStructure().isEmpty())
      return "This profile defines "+Integer.toString(p.getExtensionDefn().size())+" extensions.";
    else
      return "This profile defines "+Integer.toString(p.getExtensionDefn().size())+" extensions and has constraints on the following resources: "+listStructures(p, pkp);
  }

  private static String listStructures(Profile p, ProfileKnowledgeProvider pkp) throws Exception {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (ProfileStructureComponent s : p.getStructure()) {
      if (first)
        first = false;
      else
        b.append(", ");
      if (pkp != null && pkp.hasLinkFor(s.getTypeSimple()))
        b.append("<a href=\""+pkp.getLinkFor(s.getTypeSimple())+"\">"+s.getTypeSimple()+"</a>");
      else
        b.append(s.getTypeSimple());
    }
    return b.toString();
  }

}
