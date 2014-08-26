package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.AtomEntry;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.Profile.ElementSlicingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ResourceSlicingRules;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.ResourceReference;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Cell;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Piece;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.Row;
import org.hl7.fhir.utilities.xhtml.HeirarchicalTableGenerator.TableModel;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

/**
 * This class provides a set of utility operations for working with Profiles. 
 * Key functionality:
 *  * getChildMap --?
 *  * getChildList
 *  * generateSnapshot: Given a base (snapshot) profile structure, and a differential profile, generate a new snapshot profile
 *  * generateExtensionsTable: generate the HTML for a heirarchical table presentation of the extensions 
 *  * generateTable: generate  the HTML for a heirarchical table presentation of a structure 
 *  * summarise: describe the contents of a profile
 * @author Grahame
 *
 */
public class ProfileUtilities {

  public class StrucResult {

    private Profile profile;
    private ProfileStructureComponent structure;

    public StrucResult(Profile profile, ProfileStructureComponent structure) {
      this.profile = profile;
      this.structure = structure;
    }

    public Profile getProfile() {
      return profile;
    }

    public ProfileStructureComponent getStructure() {
      return structure;
    }

  }

  public class ExtensionResult {

    private Profile profile;
    private ProfileExtensionDefnComponent extension;

    public ExtensionResult(Profile profile, ProfileExtensionDefnComponent extension) {
      this.profile = profile;
      this.extension = extension;
    }

    public Profile getProfile() {
      return profile;
    }

    public ProfileExtensionDefnComponent getExtension() {
      return extension;
    }

  }


  private WorkerContext context;
  
  public ProfileUtilities(WorkerContext context) {
    super();
    this.context = context;
  }

  private class UnusedTracker {
    private boolean used;
    
  }

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
    String getLinkForExtension(Profile profile, String url);
    String resolveBinding(ElementDefinitionBindingComponent binding);
    String getLinkForProfile(Profile profile, String url);
  }


/**
 * Given a Structure, navigate to the element given by the path and return the direct children of that element
 *   
 * @param structure The structure to navigate into
 * @param path The path of the element within the structure to get the children for
 * @return A Map containing the name of the element child (not the path) and the child itself (an Element)
 */
  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, String path) {
    HashMap<String, ElementComponent> res = new HashMap<String, Profile.ElementComponent>(); 
    
    for (ElementComponent e : structure.getSnapshot().getElement()) 
    {
      String p = e.getPathSimple();
      
      if (e.getDefinition() != null && !Utilities.noString(e.getDefinition().getNameReferenceSimple()) && path.startsWith(p)) 
      {
    	/* The path we are navigating to is on or below this element, but the element defers its definition to another named part of the
    	 * structure.
    	 */
        if (path.length() > p.length())
        {
          // The path navigates further into the referenced element, so go ahead along the path over there
          return getChildMap(structure, e.getDefinition().getNameReferenceSimple()+"."+path.substring(p.length()+1));
        }
        else
        {
          // The path we are looking for is actually this element, but since it defers it definition, go get the referenced element
          return getChildMap(structure, e.getDefinition().getNameReferenceSimple());
        }
      } 
      else if (p.startsWith(path+".")) 
      {
    	  // The path of the element is a child of the path we're looking for (i.e. the parent),
    	  // so add this element to the result.
          String tail = p.substring(path.length()+1);
          
          // Only add direct children, not any deeper paths
          if (!tail.contains(".")) {
            res.put(tail, e);
          }
        }
      }
    
    return res;
  }

  
  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, ElementComponent element) {
	  	return getChildMap(structure, element.getPathSimple());
  }
  

  /**
   * Given a Structure, navigate to the element given by the path and return the direct children of that element
   *   
   * @param structure The structure to navigate into
   * @param path The path of the element within the structure to get the children for
   * @return A List containing the element children (all of them are Elements)
   */
  public static List<ElementComponent> getChildList(ProfileStructureComponent structure, String path) {
    List<ElementComponent> res = new ArrayList<Profile.ElementComponent>(); 
    
    for (ElementComponent e : structure.getSnapshot().getElement()) 
    {
      String p = e.getPathSimple();
    
      if (!Utilities.noString(e.getDefinition().getNameReferenceSimple()) && path.startsWith(p)) 
      {
        if (path.length() > p.length())
          return getChildList(structure, e.getDefinition().getNameReferenceSimple()+"."+path.substring(p.length()+1));
        else
          return getChildList(structure, e.getDefinition().getNameReferenceSimple());
      }
      else if (p.startsWith(path+".") && !p.equals(path)) 
      {
          String tail = p.substring(path.length()+1);
          if (!tail.contains(".")) {
            res.add(e);
          }
        }

      }
    
    return res;
  }

  
  public static List<ElementComponent> getChildList(ProfileStructureComponent structure, ElementComponent element) {
	  	return getChildList(structure, element.getPathSimple());
	  }

  /**
   * Given a base (snapshot) profile structure, and a differential profile, generate a new snapshot profile
   *  
   * @param base - the base structure on which the differential will be applied 
   * @param differential - the differential to apply to the base 
   * @param url - where the base has relative urls for profile references, these need to be converted to absolutes by prepending this URL
   * @return
   * @throws Exception 
   */
  public void generateSnapshot(ProfileStructureComponent base, ProfileStructureComponent derived, String url, String profileName) throws Exception {
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
    processPaths(derived.getSnapshot(), base.getSnapshot(), derived.getDifferential(), baseCursor, diffCursor, base.getSnapshot().getElement().size()-1, derived.getDifferential().getElement().size()-1, url, profileName+"."+derived.getNameSimple(), null);
  }

  /**
   * @throws Exception 
   */
  private void processPaths(ConstraintComponent result, ConstraintComponent base, ConstraintComponent differential, int baseCursor, int diffCursor, int baseLimit, 
      int diffLimit, String url, String profileName, String contextPath) throws Exception {
    
    // just repeat processing entries until we run out of our allowed scope (1st entry, the allowed scope is all the entries)
    while (baseCursor <= baseLimit) {
      // get the current focus of the base, and decide what to do
      ElementComponent currentBase = base.getElement().get(baseCursor); 
      String cpath = fixedPath(contextPath, currentBase.getPathSimple());
      List<ElementComponent> diffMatches = getDiffMatches(differential, cpath, diffCursor, diffLimit); // get a list of matching elements in scope

      // in the simple case, source is not sliced. 
      if (currentBase.getSlicing() == null) {
        if (diffMatches.isEmpty()) { // the differential doesn't say anything about this item
          // so we just copy it in
          ElementComponent outcome = updateURLs(url, currentBase.copy());
          outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
          result.getElement().add(outcome); 
          baseCursor++;
        } else if (diffMatches.size() == 1) {// one matching element in the differential
          ElementComponent outcome = updateURLs(url, currentBase.copy());
          outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
          outcome.setNameSimple(diffMatches.get(0).getNameSimple());
          outcome.setSlicing(null);
          updateFromDefinition(outcome, diffMatches.get(0));
          if (outcome.getPathSimple().endsWith("[x]") && outcome.getDefinition().getType().size() == 1 && !outcome.getDefinition().getType().get(0).getCodeSimple().equals("*")) // if the base profile allows multiple types, but the profile only allows one, rename it 
            outcome.setPathSimple(outcome.getPathSimple().substring(0, outcome.getPathSimple().length()-3)+Utilities.capitalize(outcome.getDefinition().getType().get(0).getCodeSimple()));
          result.getElement().add(outcome);
          baseCursor++;
          diffCursor = differential.getElement().indexOf(diffMatches.get(0))+1;
          if (differential.getElement().size() > diffCursor && isDataType(outcome.getDefinition().getType())) { 
            if (pathStartsWith(differential.getElement().get(diffCursor).getPathSimple(), diffMatches.get(0).getPathSimple()+".")) {
              if (outcome.getDefinition().getType().size() > 1)
                throw new Exception(diffMatches.get(0).getPathSimple()+" has children ("+differential.getElement().get(diffCursor).getPathSimple()+") and multiple types ("+asString(outcome.getDefinition().getType())+") in profile "+profileName);
              ProfileStructureComponent dt = getStructureForDataType(outcome.getDefinition().getType().get(0));
              if (dt == null)
                throw new Exception(diffMatches.get(0).getPathSimple()+" has children ("+differential.getElement().get(diffCursor).getPathSimple()+") for type "+asString(outcome.getDefinition().getType())+" in profile "+profileName+", but can't find type");
              int start = diffCursor;
              while (differential.getElement().size() > diffCursor && pathStartsWith(differential.getElement().get(diffCursor).getPathSimple(), diffMatches.get(0).getPathSimple()+".")) 
                diffCursor++;
              processPaths(result, dt.getSnapshot(), differential, 1 /* starting again on the data type, but skip the root */, start-1, dt.getSnapshot().getElement().size()-1, 
                  diffCursor - 1, url, profileName+"/"+dt.getNameSimple(), diffMatches.get(0).getPathSimple()); 
            }
          }
        } else {
          // ok, the differential slices the item. Let's check our pre-conditions to ensure that this is correct
          if (!unbounded(currentBase.getDefinition()) && !isSlicedToOneOnly(diffMatches.get(0))) 
            // you can only slice an element that doesn't repeat if the sum total of your slices is limited to 1
            // (but you might do that in order to split up constraints by type)
            throw new Exception("Attempt to a slice an element that does not repeat: "+currentBase.getPathSimple()); 
          if (diffMatches.get(0).getSlicing() == null && !isExtension(currentBase)) // well, the diff has set up a slice, but hasn't defined it. this is an error
            throw new Exception("differential does not have a slice: "+currentBase.getPathSimple()); 
            
          // well, if it passed those preconditions then we slice the dest. 
          // we're just going to accept the differential slicing at face value
          ElementComponent outcome = updateURLs(url, currentBase.copy());
          outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
          
          if (diffMatches.get(0).getSlicing() == null) 
            outcome.setSlicing(makeExtensionSlicing());
          else            
            outcome.setSlicing(diffMatches.get(0).getSlicing().copy());
          result.getElement().add(outcome);
          
          // differential - if the first one in the list has a name, we'll process it. Else we'll treat it as the base definition of the slice.
          int start = 0;
          if (diffMatches.get(0).getName() == null) {
            updateFromDefinition(outcome, diffMatches.get(0));
            start = 1;
          }
          
          // now, for each entry in the diff matches, we're going to process the base item 
          // our processing scope for base is all the children of the current path
          int nbl = findEndOfElement(base, baseCursor);
          int ndc = diffCursor;
          int ndl = diffCursor;
          for (int i = start; i < diffMatches.size(); i++) {
            // our processing scope for the differential is the item in the list, and all the items before the next one in the list
            ndc = differential.getElement().indexOf(diffMatches.get(i));
            ndl = findEndOfElement(differential, ndc);
            // now we process the base scope repeatedly for each instance of the item in the differential list
            processPaths(result, base, differential, baseCursor, ndc, nbl, ndl, url, profileName, contextPath);
          }
          // ok, done with that - next in the base list
          baseCursor = nbl+1;
          diffCursor = ndl+1;
        }
      } else {
        // the item is already sliced in the base profile.
        // here's the rules
        //  1. irrespective of whether the slicing is ordered or not, the definition order must be maintained
        //  2. slice element names have to match.
        //  3. new slices must be introduced at the end
        // corallory: you can't re-slice existing slices. is that ok? 

        // we're going to need this:
        String path = currentBase.getPathSimple();
        ElementComponent original = currentBase;
        
        if (diffMatches.isEmpty()) { // the differential doesn't say anything about this item
          // copy across the currentbase, and all of it's children and siblings
          while (baseCursor < base.getElement().size() && base.getElement().get(baseCursor).getPathSimple().startsWith(path)) {
            result.getElement().add(updateURLs(url, base.getElement().get(baseCursor).copy())); // so we just copy it in
            baseCursor++;
          }
        } else {
          // first - check that the slicing is ok
          boolean closed = currentBase.getSlicing().getRulesSimple() == ResourceSlicingRules.closed;
          int diffpos = 0;
          if (diffMatches.get(0).getSlicing() != null) { // it might be null if the differential doesn't want to say anything about slicing
            diffpos++; // if there's a slice on the first, we'll ignore any content it has
            ElementSlicingComponent dSlice = diffMatches.get(0).getSlicing();
            ElementSlicingComponent bSlice = currentBase.getSlicing(); 
            if (!orderMatches(dSlice.getOrdered(), bSlice.getOrdered()) || !discriiminatorMatches(dSlice.getDiscriminatorSimple(), bSlice.getDiscriminatorSimple()) ||
                 !ruleMatches(dSlice.getRulesSimple(), bSlice.getRulesSimple()))
              throw new Exception("Slicing rules on differential do not match those on base");
          }
          ElementComponent outcome = updateURLs(url, currentBase.copy());
          outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
          if (diffMatches.get(0).getSlicing() != null) {
            updateFromSlicing(outcome.getSlicing(), diffMatches.get(0).getSlicing());
            updateFromDefinition(outcome, diffMatches.get(0)); // if there's no slice, we don't want to update the unsliced description
          }
          
          // now, we have two lists, base and diff. we're going to work through base, looking for matches in diff.
          List<ElementComponent> baseMatches = getSiblings(base.getElement(), currentBase);
          for (ElementComponent baseItem : baseMatches) {
            baseCursor = base.getElement().indexOf(baseItem);
            outcome = updateURLs(url, baseItem.copy());
            outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
            outcome.setSlicing(null);
            result.getElement().add(outcome);
            if (diffpos < diffMatches.size() && diffMatches.get(diffpos).getNameSimple().equals(outcome.getNameSimple())) {
              // if there's a diff, we update the outcome with diff 
              updateFromDefinition(outcome, diffMatches.get(diffpos));
              //then process any children
              int nbl = findEndOfElement(base, baseCursor);
              int ndc = differential.getElement().indexOf(diffMatches.get(diffpos));
              int ndl = findEndOfElement(differential, ndc);
              // now we process the base scope repeatedly for each instance of the item in the differential list
              processPaths(result, base, differential, baseCursor, ndc, nbl, ndl, url, profileName, contextPath);
              // ok, done with that - now set the cursors for if this is the end
              baseCursor = nbl+1;
              diffCursor = ndl+1;
              diffpos++;              
            } else {
              baseCursor++;
              // just copy any children on the base
              while (baseCursor < base.getElement().size() && base.getElement().get(baseCursor).getPathSimple().startsWith(path) && !base.getElement().get(baseCursor).getPathSimple().equals(path)) {
                result.getElement().add(updateURLs(url, currentBase.copy())); 
                baseCursor++;
              }
            }
          }
          // finally, we process any remaining entries in diff, which are new (and which are only allowed if the base wasn't closed
          if (closed && diffpos < diffMatches.size()) 
            throw new Exception("The base snapshot marks a slicing as closed, but the differential tries to extend it in "+profileName+" at "+path+" ("+cpath+")");
          while (diffpos < diffMatches.size()) {
            ElementComponent diffItem = diffMatches.get(diffpos); 
            for (ElementComponent baseItem : baseMatches) 
              if (baseItem.getNameSimple().equals(diffItem.getNameSimple()))
                throw new Exception("Named items are out of order in the slice");
            outcome = updateURLs(url, original.copy());
            outcome.setPathSimple(fixedPath(contextPath, outcome.getPathSimple()));
            outcome.setSlicing(null);
            result.getElement().add(outcome);
            updateFromDefinition(outcome, diffItem);
            diffpos++;
          }
        }
      }
    }      
  }

  
  private boolean pathStartsWith(String p1, String p2) {
    return p1.startsWith(p2);
  }

  private boolean pathMatches(String p1, String p2) {
    return p1.equals(p2) || (p2.endsWith("[x]") && p1.startsWith(p2.substring(0, p2.length()-3)) && !p1.substring(p2.length()-3).contains("."));
  }


  private String fixedPath(String contextPath, String pathSimple) {
    if (contextPath == null)
      return pathSimple;
    return contextPath+"."+pathSimple.substring(pathSimple.indexOf(".")+1);
  }


  private ProfileStructureComponent getStructureForDataType(TypeRefComponent type) {
    if (type.getProfile() != null && !type.getCodeSimple().equals("ResourceReference") && !type.getCodeSimple().equals("Extension")) 
      throw new Error("handling profiles is not supported yet");
    for (AtomEntry<Profile> ae : context.getProfiles().values()) {
      if (ae.getResource().getNameSimple().equals(type.getCodeSimple())) {
        return ae.getResource().getStructure().get(0);
      }
    }
    return null;
  }


  private String asString(List<TypeRefComponent> types) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (TypeRefComponent type : types) {
      if (first) first = false; else b.append(", ");
      b.append(type.getCodeSimple());
      if (type.getProfile() != null)
        b.append("{"+type.getProfileSimple()+"}");
    }
    return b.toString();
  }


  private boolean isDataType(List<TypeRefComponent> types) {
    if (types.isEmpty())
      return false;
    for (TypeRefComponent type : types) {
      String t = type.getCodeSimple();
      if (!isDataType(t) && !t.equals("ResourceReference") && !t.equals("Extension") && !isPrimitive(t))
        return false;
    }
    return true;
  }


  /**
   * Finds internal references in an Element's Binding and Profile references (in TypeRef) and bases them on the given url
   * @param url - the base url to use to turn internal references into absolute references 
   * @param element - the Element to update
   * @return - the updated Element
   */
  private ElementComponent updateURLs(String url, ElementComponent element) {
    if (element.getDefinition() != null) {
      ElementDefinitionComponent defn = element.getDefinition();
      if (defn.getBinding() != null && defn.getBinding().getReference() instanceof ResourceReference && ((ResourceReference)defn.getBinding().getReference()).getReferenceSimple().startsWith("#"))
        ((ResourceReference)defn.getBinding().getReference()).setReferenceSimple(url+((ResourceReference)defn.getBinding().getReference()).getReferenceSimple());
      for (TypeRefComponent t : defn.getType()) {
        if (t.getProfile() != null && t.getProfileSimple().startsWith("#")) {
          t.setProfileSimple(url+t.getProfileSimple());
        }
      }
    }
    return element;
  }

  private List<ElementComponent> getSiblings(List<ElementComponent> list, ElementComponent current) {
    List<ElementComponent> result = new ArrayList<Profile.ElementComponent>();
    String path = current.getPathSimple();
    int cursor = list.indexOf(current)+1;
    while (cursor < list.size() && list.get(cursor).getPathSimple().length() >= path.length()) {
      if (pathMatches(list.get(cursor).getPathSimple(), path))
        result.add(list.get(cursor));
      cursor++;
    }
    return result;
  }

  private void updateFromSlicing(ElementSlicingComponent dst, ElementSlicingComponent src) {
    if (src.getOrdered() != null)
      dst.setOrdered(src.getOrdered().copy());
    if (src.getDiscriminator() != null)
      dst.setDiscriminator(src.getDiscriminator().copy());
    if (src.getRules() != null)
      dst.setRules(src.getRules().copy());
  }

  private boolean orderMatches(BooleanType diff, BooleanType base) {
    return (diff == null) || (base == null) || (diff == base);
  }

  private boolean discriiminatorMatches(String diff, String base) {
    return (diff == null) || (base == null) || (diff.equals(base));
  }

  private boolean ruleMatches(ResourceSlicingRules diff, ResourceSlicingRules base) {
    return (diff == null) || (base == null) || (diff == base) || (diff == ResourceSlicingRules.open) ||
        ((diff == ResourceSlicingRules.openAtEnd && base == ResourceSlicingRules.closed));
  }

  private boolean isSlicedToOneOnly(ElementComponent e) {
    return (e.getSlicing() != null && e.getDefinition() != null && e.getDefinition().getMax() != null && e.getDefinition().getMaxSimple().equals("1"));
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
      String statedPath = context.getElement().get(i).getPathSimple();
      if (statedPath.equals(path) || (path.endsWith("[x]") && statedPath.length() > path.length() && statedPath.substring(0, path.length()-3).equals(path.substring(0, path.length()-3)) && !statedPath.substring(path.length()).contains("."))) {
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
    StringType max = definition.getMax();
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
      for (StringType s : src.getSynonym()) {
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
      if (!src.getType().isEmpty()) {
        dst.getType().clear();
        for (TypeRefComponent t : src.getType())
          dst.getType().add(t.copy());
      }      
      // todo: mappings are cumulative - or does one replace another?
      for (ElementDefinitionMappingComponent s : src.getMapping()) {
        boolean found = false;
        for (ElementDefinitionMappingComponent d : dst.getMapping()) {
          found = found || (d.getIdentitySimple().equals(s.getIdentitySimple()) && d.getMapSimple().equals(s.getMapSimple()));
        }
        if (!found)
          dst.getMapping().add(s);
      }
      
      // todo: constraints are cumulative - or does one replace another?
      for (ElementDefinitionConstraintComponent s : src.getConstraint()) {
        boolean found = false;
        for (ElementDefinitionConstraintComponent d : dst.getConstraint()) {
          found = found || (d.getKeySimple().equals(s.getKeySimple()));
        }
        if (!found)
          dst.getConstraint().add(s);
      }
    }
  }
  
  public XhtmlNode generateExtensionsTable(String defFile, Profile profile, String imageFolder, boolean inlineGraphics, ProfileKnowledgeProvider pkp, String profileBaseName) throws Exception {
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
      genExtension(defFile, gen, re.getSubRows(), ext, profile, pkp, profileBaseName);
    }
    return gen.generate(model);
  }
  
  private void genExtension(String defFile, HeirarchicalTableGenerator gen, List<Row> rows, ProfileExtensionDefnComponent ext, Profile profile, ProfileKnowledgeProvider pkp, String profileBaseFileName) throws Exception {
    Row r = gen.new Row();
    rows.add(r);
    r.setAnchor(ext.getCodeSimple());
    ElementComponent e = ext.getElement().get(0);
    r.getCells().add(gen.new Cell(null, defFile == null ? "" : defFile+"#extension."+ext.getCodeSimple(), ext.getCodeSimple(), e.getDefinition().getFormalSimple(), null));
    r.getCells().add(gen.new Cell(null, null, describeCardinality(e.getDefinition(), null, new UnusedTracker()), null, null));
    if (ext.getElement().size() == 1) {
      r.setIcon("icon_extension_simple.png");
      genTypes(gen, pkp, r, e, profileBaseFileName, profile);
    } else {
      r.setIcon("icon_extension_complex.png");
      r.getCells().add(gen.new Cell());
    }

    Cell c = generateDescription(gen, r, e, null, true, profile.getUrlSimple(), null, pkp, profile);
    c.addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, describeExtensionContext(ext), null));
    
    List<ElementComponent> children = getChildren(ext.getElement(), e);
    for (ElementComponent child : children)
      genElement(defFile == null ? "" : defFile+"#extension.", gen, r.getSubRows(), child, ext.getElement(), profile, pkp, true, profileBaseFileName);
  }

  private void genTypes(HeirarchicalTableGenerator gen, ProfileKnowledgeProvider pkp, Row r, ElementComponent e, String profileBaseFileName, Profile profile) throws Exception {
    Cell c = gen.new Cell();
    r.getCells().add(c);
    boolean first = true;
    for (TypeRefComponent t : e.getDefinition().getType()) {
      if (first) 
        first = false; 
      else 
        c.addPiece(gen.new Piece(null,", ", null));
      if (t.getCodeSimple().equals("ResourceReference") || (t.getCodeSimple().equals("Resource") && t.getProfile() != null)) {
        if (t.getProfileSimple().startsWith("http://hl7.org/fhir/Profile/")) {
          String rn = t.getProfileSimple().substring(28);
          c.addPiece(gen.new Piece(pkp.getLinkFor(rn), rn, null));
        } else if (t.getProfileSimple().startsWith("#"))
          c.addPiece(gen.new Piece(profileBaseFileName+"."+t.getProfileSimple().substring(1).toLowerCase()+".html", t.getProfileSimple(), null));
        else
          c.addPiece(gen.new Piece(t.getProfileSimple(), t.getProfileSimple(), null));
      } else if (t.getProfile() != null) { // a profiled type
        String ref;
        ref = pkp.getLinkForProfile(profile, t.getProfileSimple());
        if (ref != null) {
          String[] parts = ref.split("\\|");
          c.addPiece(gen.new Piece(parts[0], parts[1], t.getCodeSimple()));
        } else
          c.addPiece(gen.new Piece(ref, t.getCodeSimple(), null));
      } else if (pkp.hasLinkFor(t.getCodeSimple())) {
        c.addPiece(gen.new Piece(pkp.getLinkFor(t.getCodeSimple()), t.getCodeSimple(), null));
      } else
        c.addPiece(gen.new Piece(null, t.getCodeSimple(), null));
    }
  }
  
  private String describeExtensionContext(ProfileExtensionDefnComponent ext) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (StringType t : ext.getContext())
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

  private String describeCardinality(ElementDefinitionComponent definition, ElementDefinitionComponent fallback, UnusedTracker tracker) {
    org.hl7.fhir.instance.model.IntegerType min = definition.getMin();
    StringType max = definition.getMax();
    if (min == null && fallback != null)
      min = fallback.getMin();
    if (max == null && fallback != null)
      max = fallback.getMax();
    
    tracker.used = max == null || !max.getValue().equals("0");

    if (min == null && max == null)
      return null;
    else
      return (min == null ? "" : Integer.toString(min.getValue())) + ".." + (max == null ? "" : max.getValue());
  }

  public XhtmlNode generateTable(String defFile, ProfileStructureComponent structure, boolean diff, String imageFolder, boolean inlineGraphics, Profile profile, ProfileKnowledgeProvider pkp, String profileBaseFileName) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(imageFolder, inlineGraphics);
    TableModel model = gen.initNormalTable();
    List<ElementComponent> list = diff ? structure.getDifferential().getElement() : structure.getSnapshot().getElement();
    genElement(defFile == null ? null : defFile+"#"+structure.getNameSimple()+".", gen, model.getRows(), list.get(0), list, profile, pkp, diff, profileBaseFileName);
    return gen.generate(model);
  }

  private void genElement(String defPath, HeirarchicalTableGenerator gen, List<Row> rows, ElementComponent element, List<ElementComponent> all, Profile profile, ProfileKnowledgeProvider pkp, boolean showMissing, String profileBaseFileName) throws Exception {
    if (!onlyInformationIsMapping(all, element)) { // we don't even show it in this case
    Row row = gen.new Row();
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
    String ref = defPath == null ? null : defPath + makePathLink(element);
    UnusedTracker used = new UnusedTracker();
    used.used = true;
    Cell left = gen.new Cell(null, ref, s, !hasDef ? null : element.getDefinition().getFormalSimple(), null);
    row.getCells().add(left);
    if (ext) {
      if (element.getDefinition() != null && element.getDefinition().getType().size() == 1 && element.getDefinition().getType().get(0).getProfile() != null) {
        ExtensionDefinition extDefn = pkp.getExtensionDefinition(profile, element.getDefinition().getType().get(0).getProfileSimple());
        if (extDefn == null) {
            row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition(), null, used), null, null));
            row.getCells().add(gen.new Cell(null, null, "?? "+element.getDefinition().getType().get(0).getProfileSimple(), null, null));
            generateDescription(gen, row, element, null, used.used, profile.getUrlSimple(), element.getDefinition().getType().get(0).getProfileSimple(), pkp, profile);
          } else {
            row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition(), extDefn.getDefn().getElement().get(0).getDefinition(), used), null, null));
            genTypes(gen, pkp, row, extDefn.getDefn().getElement().get(0), profileBaseFileName, profile);
            generateDescription(gen, row, element, extDefn.getDefn().getElement().get(0), used.used, profile.getUrlSimple(), element.getDefinition().getType().get(0).getProfileSimple(), pkp, profile);
        }
      } else if (element.getDefinition() != null) {
          row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition(), null, used), null, null));
          genTypes(gen, pkp, row, element, profileBaseFileName, profile);
          generateDescription(gen, row, element, null, used.used, null, null, pkp, profile);
      } else {
          row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition(), null, used), null, null));
          row.getCells().add(gen.new Cell());
          generateDescription(gen, row, element, null, used.used, null, null, pkp, profile);
      }
    } else {
        row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element.getDefinition(), null, used), null, null));
        if (hasDef)
          genTypes(gen, pkp, row, element, profileBaseFileName, profile);
        else
          row.getCells().add(gen.new Cell());
        generateDescription(gen, row, element, null, used.used, null, null, pkp, profile);
      }
      if (element.getSlicing() != null) {
        row.setIcon("icon_slice.png");
        row.getCells().get(2).getPieces().clear();
        for (Cell cell : row.getCells())
          for (Piece p : cell.getPieces()) {
            p.addStyle("font-style: italic");
          }
        
      }
      if (used.used || showMissing)
        rows.add(row);
      if (!used.used) {
        for (Cell cell : row.getCells())
          for (Piece p : cell.getPieces()) {
            p.setStyle("text-decoration:line-through");
            p.setReference(null);
          }
      } else{
        List<ElementComponent> children = getChildren(all, element);
        for (ElementComponent child : children)
          genElement(defPath, gen, row.getSubRows(), child, all, profile, pkp, showMissing, profileBaseFileName);
      }
    }
  }


  private String makePathLink(ElementComponent element) {
    if (element.getName() == null)
      return element.getPathSimple();
    if (!element.getPathSimple().contains("."))
      return element.getNameSimple();
    return element.getPathSimple().substring(0, element.getPathSimple().lastIndexOf("."))+"."+element.getNameSimple();
  }

  private Cell generateDescription(HeirarchicalTableGenerator gen, Row row, ElementComponent definition, ElementComponent fallback, boolean used, String baseURL, String url, ProfileKnowledgeProvider pkp, Profile profile) {
    // TODO Auto-generated method stub
    Cell c = gen.new Cell();
    row.getCells().add(c);                

    if (used) {
      if (definition.getDefinition() != null && definition.getDefinition().getShort() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.addPiece(gen.new Piece(null, definition.getDefinition().getShortSimple(), null));
      } else if (fallback != null && fallback.getDefinition() != null && fallback.getDefinition().getShort() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.addPiece(gen.new Piece(null, fallback.getDefinition().getShortSimple(), null));
      }
      if (url != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        String fullUrl = url.startsWith("#") ? baseURL+url : url;
        String ref = pkp.getLinkForExtension(profile, url);
        c.getPieces().add(gen.new Piece(null, "URL: ", null).addStyle("font-weight:bold"));
        c.getPieces().add(gen.new Piece(ref, fullUrl, null));
      }

      if (definition.getSlicing() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.getPieces().add(gen.new Piece(null, "Slice: ", null).addStyle("font-weight:bold"));
        c.getPieces().add(gen.new Piece(null, describeSlice(definition.getSlicing()), null));
      }
      if (definition.getDefinition() != null) {
        if (definition.getDefinition().getBinding() != null) {
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          String ref = pkp.resolveBinding(definition.getDefinition().getBinding());
          c.getPieces().add(gen.new Piece(null, "Binding: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(ref, definition.getDefinition().getBinding().getNameSimple(), null));
        }
        for (ElementDefinitionConstraintComponent inv : definition.getDefinition().getConstraint()) {
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Inv-"+inv.getKeySimple()+": ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, inv.getHumanSimple(), null));
        }
        if (definition.getDefinition().getValue() != null) {        
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Fixed Value: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, "(todo)", null));
        }
        // ?? example from definition    
      }
    }
    return c;
  }

  public String describeSlice(ElementSlicingComponent slicing) {
    return (slicing.getOrderedSimple() ? "Ordered, " : "Unordered, ")+describe(slicing.getRulesSimple())+", by "+slicing.getDiscriminatorSimple();
  }

  private String describe(ResourceSlicingRules rules) {
    switch (rules) {
    case closed : return "Closed";
    case open : return "Open";
    case openAtEnd : return "Open At End";
    default:
      return "??";
    }
  }

  private boolean onlyInformationIsMapping(List<ElementComponent> list, ElementComponent e) {
    return (e.getName() == null && e.getSlicing() == null && (e.getDefinition() == null || onlyInformationIsMapping(e.getDefinition()))) &&
        getChildren(list, e).isEmpty();
  }

  private boolean onlyInformationIsMapping(ElementDefinitionComponent d) {
    return d.getShort() == null && d.getFormal() == null && 
        d.getRequirements() == null && d.getSynonym().isEmpty() && d.getMin() == null &&
        d.getMax() == null && d.getType().isEmpty() && d.getNameReference() == null && 
        d.getExample() == null && d.getValue() == null && d.getMaxLength() == null &&
        d.getCondition().isEmpty() && d.getConstraint().isEmpty() && d.getMustSupport() == null &&
        d.getBinding() == null;
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


  public StrucResult getStructure(Profile source, String url) {
    Profile profile;
    String code;
    if (url.startsWith("#")) {
      profile = source;
      code = url.substring(1);
    } else {
      String[] parts = url.split("\\#");
      profile = context.getProfiles().get(parts[0]).getResource();
      code = parts[1];
    }

    if (profile != null) {
      ProfileStructureComponent structure = null;
      for (ProfileStructureComponent s : profile.getStructure()) {
        if (s.getNameSimple().equals(code)) 
          structure = s;
      }
      if (structure != null)
        return new StrucResult(profile, structure);
    }
    return null;
  }

  public ExtensionResult getExtensionDefn(Profile source, String url) {
    Profile profile;
    String code;
    if (url.startsWith("#")) {
      profile = source;
      code = url.substring(1);
    } else {
      String[] parts = url.split("\\#");
      profile = context.getProfiles().get(parts[0]).getResource();
      code = parts[1];
    }

    if (profile != null) {
      ProfileExtensionDefnComponent defn = null;
      for (ProfileExtensionDefnComponent s : profile.getExtensionDefn()) {
        if (s.getCodeSimple().equals(code)) 
          defn = s;
      }
      if (defn != null)
        return new ExtensionResult(profile, defn);
    }
    return null;
  }

}
