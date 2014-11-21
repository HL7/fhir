package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.client.FHIRClient;
import org.hl7.fhir.instance.client.FHIRSimpleClient;
import org.hl7.fhir.instance.formats.JsonComposer;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.BindingConformance;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ResourceSlicingRules;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.ExtensionDefinition;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.PrimitiveType;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.Resource;
import org.hl7.fhir.instance.model.StringType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.utils.WorkerContext.ExtensionDefinitionResult;
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

  private WorkerContext context;
  
  public ProfileUtilities(WorkerContext context) {
    super();
    this.context = context;
  }

  private class UnusedTracker {
    private boolean used;
    
  }

//  public static class ExtensionDefinition {
//    private String url;
//    private List<Profile> profiles = new ArrayList<Profile>();
//    private ProfileExtensionDefnComponent defn;
//    private ElementDefinition element;
//    
//    public ExtensionDefinition(String url, List<Profile> profiles, Profile profile, ProfileExtensionDefnComponent defn, ElementDefinition element) {
//      super();
//      this.url = url;
//      this.profiles.addAll(profiles);
//      if (profiles.isEmpty() || profiles.get(profiles.size()-1) != profile)
//        this.profiles.add(profile);
//      this.defn = defn;
//      this.element = element;
//    }
//    public String getUrl() {
//      return url;
//    }
//    public List<Profile> getProfiles() {
//      return profiles;
//    }
//    public ProfileExtensionDefnComponent getDefn() {
//      return defn;
//    }
//    public ElementDefinition getElement() {
//      return element;
//    }
//    
//  }

  public interface ProfileKnowledgeProvider {
    boolean isDatatype(String typeSimple);
    boolean hasLinkFor(String typeSimple);
    String getLinkFor(String typeSimple) throws Exception;
    String resolveBinding(ElementDefinitionBindingComponent binding);
    String getLinkForProfile(Profile profile, String url) throws Exception;
  }


/**
 * Given a Structure, navigate to the element given by the path and return the direct children of that element
 *   
 * @param structure The structure to navigate into
 * @param path The path of the element within the structure to get the children for
 * @return A Map containing the name of the element child (not the path) and the child itself (an Element)
 */
  public static Map<String, ElementDefinition> getChildMap(Profile profile, String path) {
    HashMap<String, ElementDefinition> res = new HashMap<String, ElementDefinition>(); 
    
    for (ElementDefinition e : profile.getSnapshot().getElement()) 
    {
      String p = e.getPath();
      
      if (e != null && !Utilities.noString(e.getNameReference()) && path.startsWith(p)) 
      {
    	/* The path we are navigating to is on or below this element, but the element defers its definition to another named part of the
    	 * structure.
    	 */
        if (path.length() > p.length())
        {
          // The path navigates further into the referenced element, so go ahead along the path over there
          return getChildMap(profile, e.getNameReference()+"."+path.substring(p.length()+1));
        }
        else
        {
          // The path we are looking for is actually this element, but since it defers it definition, go get the referenced element
          return getChildMap(profile, e.getNameReference());
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

  
  public static Map<String, ElementDefinition> getChildMap(Profile profile, ElementDefinition element) {
	  	return getChildMap(profile, element.getPath());
  }
  

  /**
   * Given a Structure, navigate to the element given by the path and return the direct children of that element
   *   
   * @param structure The structure to navigate into
   * @param path The path of the element within the structure to get the children for
   * @return A List containing the element children (all of them are Elements)
   */
  public static List<ElementDefinition> getChildList(Profile profile, String path) {
    List<ElementDefinition> res = new ArrayList<ElementDefinition>(); 
    
    for (ElementDefinition e : profile.getSnapshot().getElement()) 
    {
      String p = e.getPath();
    
      if (!Utilities.noString(e.getNameReference()) && path.startsWith(p)) 
      {
        if (path.length() > p.length())
          return getChildList(profile, e.getNameReference()+"."+path.substring(p.length()+1));
        else
          return getChildList(profile, e.getNameReference());
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

  
  public static List<ElementDefinition> getChildList(Profile structure, ElementDefinition element) {
	  	return getChildList(structure, element.getPath());
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
  public void generateSnapshot(Profile base, Profile derived, String url, String profileName) throws Exception {
    if (base == null)
      throw new Exception("no base profile provided");
    if (derived == null) 
      throw new Exception("no derived structure provided");
    if (!derived.getType().equals(base.getType()))
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
    processPaths(derived.getSnapshot(), base.getSnapshot(), derived.getDifferential(), baseCursor, diffCursor, base.getSnapshot().getElement().size()-1, derived.getDifferential().getElement().size()-1, url, profileName+"."+derived.getName(), null);
  }

  /**
   * @throws Exception 
   */
  private void processPaths(ConstraintComponent result, ConstraintComponent base, ConstraintComponent differential, int baseCursor, int diffCursor, int baseLimit, 
      int diffLimit, String url, String profileName, String contextPath) throws Exception {
    
    // just repeat processing entries until we run out of our allowed scope (1st entry, the allowed scope is all the entries)
    while (baseCursor <= baseLimit) {
      // get the current focus of the base, and decide what to do
      ElementDefinition currentBase = base.getElement().get(baseCursor); 
      String cpath = fixedPath(contextPath, currentBase.getPath());
      List<ElementDefinition> diffMatches = getDiffMatches(differential, cpath, diffCursor, diffLimit); // get a list of matching elements in scope

      // in the simple case, source is not sliced. 
      if (currentBase.getSlicing() == null) {
        if (diffMatches.isEmpty()) { // the differential doesn't say anything about this item
          // so we just copy it in
          ElementDefinition outcome = updateURLs(url, currentBase.copy());
          outcome.setPath(fixedPath(contextPath, outcome.getPath()));
          result.getElement().add(outcome); 
          baseCursor++;
        } else if (diffMatches.size() == 1) {// one matching element in the differential
          ElementDefinition outcome = updateURLs(url, currentBase.copy());
          outcome.setPath(fixedPath(contextPath, outcome.getPath()));
          outcome.setName(diffMatches.get(0).getName());
          outcome.setSlicing(null);
          updateFromDefinition(outcome, diffMatches.get(0));
          if (outcome.getPath().endsWith("[x]") && outcome.getType().size() == 1 && !outcome.getType().get(0).getCode().equals("*")) // if the base profile allows multiple types, but the profile only allows one, rename it 
            outcome.setPath(outcome.getPath().substring(0, outcome.getPath().length()-3)+Utilities.capitalize(outcome.getType().get(0).getCode()));
          result.getElement().add(outcome);
          baseCursor++;
          diffCursor = differential.getElement().indexOf(diffMatches.get(0))+1;
          if (differential.getElement().size() > diffCursor && isDataType(outcome.getType())) { 
            if (pathStartsWith(differential.getElement().get(diffCursor).getPath(), diffMatches.get(0).getPath()+".")) {
              if (outcome.getType().size() > 1)
                throw new Exception(diffMatches.get(0).getPath()+" has children ("+differential.getElement().get(diffCursor).getPath()+") and multiple types ("+typeCode(outcome.getType())+") in profile "+profileName);
              Profile dt = getProfileForDataType(outcome.getType().get(0));
              if (dt == null)
                throw new Exception(diffMatches.get(0).getPath()+" has children ("+differential.getElement().get(diffCursor).getPath()+") for type "+typeCode(outcome.getType())+" in profile "+profileName+", but can't find type");
              int start = diffCursor;
              while (differential.getElement().size() > diffCursor && pathStartsWith(differential.getElement().get(diffCursor).getPath(), diffMatches.get(0).getPath()+".")) 
                diffCursor++;
              processPaths(result, dt.getSnapshot(), differential, 1 /* starting again on the data type, but skip the root */, start-1, dt.getSnapshot().getElement().size()-1, 
                  diffCursor - 1, url, profileName+"/"+dt.getName(), diffMatches.get(0).getPath()); 
            }
          }
        } else {
          // ok, the differential slices the item. Let's check our pre-conditions to ensure that this is correct
          if (!unbounded(currentBase) && !isSlicedToOneOnly(diffMatches.get(0))) 
            // you can only slice an element that doesn't repeat if the sum total of your slices is limited to 1
            // (but you might do that in order to split up constraints by type)
            throw new Exception("Attempt to a slice an element that does not repeat: "+currentBase.getPath()); 
          if (diffMatches.get(0).getSlicing() == null && !isExtension(currentBase)) // well, the diff has set up a slice, but hasn't defined it. this is an error
            throw new Exception("differential does not have a slice: "+currentBase.getPath()); 
            
          // well, if it passed those preconditions then we slice the dest. 
          // we're just going to accept the differential slicing at face value
          ElementDefinition outcome = updateURLs(url, currentBase.copy());
          outcome.setPath(fixedPath(contextPath, outcome.getPath()));
          
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
        String path = currentBase.getPath();
        ElementDefinition original = currentBase;
        
        if (diffMatches.isEmpty()) { // the differential doesn't say anything about this item
          // copy across the currentbase, and all of it's children and siblings
          while (baseCursor < base.getElement().size() && base.getElement().get(baseCursor).getPath().startsWith(path)) {
            result.getElement().add(updateURLs(url, base.getElement().get(baseCursor).copy())); // so we just copy it in
            baseCursor++;
          }
        } else {
          // first - check that the slicing is ok
          boolean closed = currentBase.getSlicing().getRules() == ResourceSlicingRules.CLOSED;
          int diffpos = 0;
          if (diffMatches.get(0).getSlicing() != null) { // it might be null if the differential doesn't want to say anything about slicing
            diffpos++; // if there's a slice on the first, we'll ignore any content it has
            ElementDefinitionSlicingComponent dSlice = diffMatches.get(0).getSlicing();
            ElementDefinitionSlicingComponent bSlice = currentBase.getSlicing(); 
            if (!orderMatches(dSlice.getOrderedElement(), bSlice.getOrderedElement()) || !discriiminatorMatches(dSlice.getDiscriminator(), bSlice.getDiscriminator()) ||
                 !ruleMatches(dSlice.getRules(), bSlice.getRules()))
              throw new Exception("Slicing rules on differential do not match those on base");
          }
          ElementDefinition outcome = updateURLs(url, currentBase.copy());
          outcome.setPath(fixedPath(contextPath, outcome.getPath()));
          if (diffMatches.get(0).getSlicing() != null) {
            updateFromSlicing(outcome.getSlicing(), diffMatches.get(0).getSlicing());
            updateFromDefinition(outcome, diffMatches.get(0)); // if there's no slice, we don't want to update the unsliced description
          }
          
          // now, we have two lists, base and diff. we're going to work through base, looking for matches in diff.
          List<ElementDefinition> baseMatches = getSiblings(base.getElement(), currentBase);
          for (ElementDefinition baseItem : baseMatches) {
            baseCursor = base.getElement().indexOf(baseItem);
            outcome = updateURLs(url, baseItem.copy());
            outcome.setPath(fixedPath(contextPath, outcome.getPath()));
            outcome.setSlicing(null);
            result.getElement().add(outcome);
            if (diffpos < diffMatches.size() && diffMatches.get(diffpos).getName().equals(outcome.getName())) {
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
              while (baseCursor < base.getElement().size() && base.getElement().get(baseCursor).getPath().startsWith(path) && !base.getElement().get(baseCursor).getPath().equals(path)) {
                result.getElement().add(updateURLs(url, currentBase.copy())); 
                baseCursor++;
              }
            }
          }
          // finally, we process any remaining entries in diff, which are new (and which are only allowed if the base wasn't closed
          if (closed && diffpos < diffMatches.size()) 
            throw new Exception("The base snapshot marks a slicing as closed, but the differential tries to extend it in "+profileName+" at "+path+" ("+cpath+")");
          while (diffpos < diffMatches.size()) {
            ElementDefinition diffItem = diffMatches.get(diffpos); 
            for (ElementDefinition baseItem : baseMatches) 
              if (baseItem.getName().equals(diffItem.getName()))
                throw new Exception("Named items are out of order in the slice");
            outcome = updateURLs(url, original.copy());
            outcome.setPath(fixedPath(contextPath, outcome.getPath()));
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


  private Profile getProfileForDataType(TypeRefComponent type) {
    if (type.getProfile() != null && !type.getCode().equals("Reference") && !type.getCode().equals("Extension")) 
      throw new Error("handling profiles is not supported yet");
    for (Profile ae : context.getProfiles().values()) {
      if (ae.getName().equals(type.getCode())) {
        return ae;
      }
    }
    return null;
  }


  public static String typeCode(List<TypeRefComponent> types) {
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (TypeRefComponent type : types) {
      if (first) first = false; else b.append(", ");
      b.append(type.getCode());
      if (type.getProfile() != null)
        b.append("{"+type.getProfile()+"}");
    }
    return b.toString();
  }


  private boolean isDataType(List<TypeRefComponent> types) {
    if (types.isEmpty())
      return false;
    for (TypeRefComponent type : types) {
      String t = type.getCode();
      if (!isDataType(t) && !t.equals("Reference") && !t.equals("Extension") && !isPrimitive(t))
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
  private ElementDefinition updateURLs(String url, ElementDefinition element) {
    if (element != null) {
      ElementDefinition defn = element;
      if (defn.getBinding() != null && defn.getBinding().getReference() instanceof Reference && ((Reference)defn.getBinding().getReference()).getReference().startsWith("#"))
        ((Reference)defn.getBinding().getReference()).setReference(url+((Reference)defn.getBinding().getReference()).getReference());
      for (TypeRefComponent t : defn.getType()) {
        if (t.getProfile() != null && t.getProfile().startsWith("#")) {
          t.setProfile(url+t.getProfile());
        }
      }
    }
    return element;
  }

  private List<ElementDefinition> getSiblings(List<ElementDefinition> list, ElementDefinition current) {
    List<ElementDefinition> result = new ArrayList<ElementDefinition>();
    String path = current.getPath();
    int cursor = list.indexOf(current)+1;
    while (cursor < list.size() && list.get(cursor).getPath().length() >= path.length()) {
      if (pathMatches(list.get(cursor).getPath(), path))
        result.add(list.get(cursor));
      cursor++;
    }
    return result;
  }
 
  private void updateFromSlicing(ElementDefinitionSlicingComponent dst, ElementDefinitionSlicingComponent src) {
    if (src.getOrderedElement() != null)
      dst.setOrderedElement(src.getOrderedElement().copy());
    if (src.getDiscriminator().isEmpty())
      dst.getDiscriminator().addAll(src.getDiscriminator());
    if (src.getRulesElement() != null)
      dst.setRulesElement(src.getRulesElement().copy());
  }

  private boolean orderMatches(BooleanType diff, BooleanType base) {
    return (diff == null) || (base == null) || (diff == base);
  }

  private boolean discriiminatorMatches(List<IdType> diff, List<IdType> base) {
    if (diff.isEmpty() || base.isEmpty()) 
    	return true; 
    if (diff.size() != base.size())
    	return false;
    for (int i = 0; i < diff.size(); i++)
    	if (diff.get(i).getValue().equals(base.get(i).getValue()))
    		return false;
    return true;
  }

  private boolean ruleMatches(ResourceSlicingRules diff, ResourceSlicingRules base) {
    return (diff == null) || (base == null) || (diff == base) || (diff == ResourceSlicingRules.OPEN) ||
        ((diff == ResourceSlicingRules.OPENATEND && base == ResourceSlicingRules.CLOSED));
  }

  private boolean isSlicedToOneOnly(ElementDefinition e) {
    return (e.getSlicing() != null && e != null && e.getMaxElement() != null && e.getMax().equals("1"));
  }

  private ElementDefinitionSlicingComponent makeExtensionSlicing() {
  	ElementDefinitionSlicingComponent slice = new ElementDefinitionSlicingComponent();
    slice.addDiscriminator("url");
    slice.setOrdered(true);
    slice.setRules(ResourceSlicingRules.OPENATEND);
    return slice;
  }

  private boolean isExtension(ElementDefinition currentBase) {
    return currentBase.getPath().endsWith(".extension") || currentBase.getPath().endsWith(".modifierExtension");
  }

  private List<ElementDefinition> getDiffMatches(ConstraintComponent context, String path, int start, int end) {
    List<ElementDefinition> result = new ArrayList<ElementDefinition>();
    for (int i = start; i <= end; i++) {
      String statedPath = context.getElement().get(i).getPath();
      if (statedPath.equals(path) || (path.endsWith("[x]") && statedPath.length() > path.length() && statedPath.substring(0, path.length()-3).equals(path.substring(0, path.length()-3)) && !statedPath.substring(path.length()).contains("."))) {
        result.add(context.getElement().get(i));
      }
    }
    return result;
  }

  private int findEndOfElement(ConstraintComponent context, int cursor) {
    int result = cursor;
    String path = context.getElement().get(cursor).getPath()+".";
    while (result < context.getElement().size()- 1 && context.getElement().get(result+1).getPath().startsWith(path))
      result++;
    return result;
  }

  private boolean unbounded(ElementDefinition definition) {
    StringType max = definition.getMaxElement();
    if (max == null)
      return false; // this is not valid
    if (max.getValue().equals("1"))
      return false;
    if (max.getValue().equals("0"))
      return false;
    return true;
  }

  private void updateFromDefinition(ElementDefinition dest, ElementDefinition source) {
    ElementDefinition dst = dest;
    ElementDefinition src = source;
    
    if (src != null) {
      if (src.getShortElement() != null)
        dst.setShortElement(src.getShortElement().copy());
      if (src.getFormalElement() != null)
        dst.setFormalElement(src.getFormalElement().copy());
      if (src.getCommentsElement() != null)
        dst.setCommentsElement(src.getCommentsElement().copy());
      if (src.getRequirementsElement() != null)
        dst.setRequirementsElement(src.getRequirementsElement().copy());
      for (StringType s : src.getSynonym()) {
        if (!dst.hasSynonym(s.getValue()))
          dst.getSynonym().add(s.copy());
      }
      if (src.getMinElement() != null)
        dst.setMinElement(src.getMinElement().copy());
      if (src.getMaxElement() != null)
        dst.setMaxElement(src.getMaxElement().copy());
      if (src.getFixed() != null)
        dst.setFixed(src.getFixed().copy());
      if (src.getPattern() != null)
        dst.setPattern(src.getPattern().copy());
      if (src.getExample() != null)
        dst.setExample(src.getExample().copy());
      if (src.getMaxLengthElement() != null)
        dst.setMaxLengthElement(src.getMaxLengthElement().copy());
      // todo: what to do about conditions? 
      // condition : id 0..*
      if (src.getMustSupportElement() != null)
        dst.setMustSupportElement(src.getMustSupportElement().copy());
      // profiles cannot change : isModifier
      if (src.getBinding() != null)
        dst.setBinding(src.getBinding().copy());
      if (src.getIsSummaryElement() != null)
        dst.setIsSummaryElement(src.getIsSummaryElement().copy());
      if (src.getDefaultValue() != null)
        dst.setDefaultValue(src.getDefaultValue().copy());
      if (src.getMeaningWhenMissingElement() != null)
        dst.setMeaningWhenMissingElement(src.getMeaningWhenMissingElement().copy());

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
          found = found || (d.getIdentity().equals(s.getIdentity()) && d.getMap().equals(s.getMap()));
        }
        if (!found)
          dst.getMapping().add(s);
      }
      
      // todo: constraints are cumulative - or does one replace another?
      for (ElementDefinitionConstraintComponent s : src.getConstraint()) {
        boolean found = false;
        for (ElementDefinitionConstraintComponent d : dst.getConstraint()) {
          found = found || (d.getKey().equals(s.getKey()));
        }
        if (!found)
          dst.getConstraint().add(s);
      }
    }   
  }
  
  public XhtmlNode generateExtensionTable(String defFile, ExtensionDefinition ed, String imageFolder, boolean inlineGraphics, ProfileKnowledgeProvider pkp) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(imageFolder, inlineGraphics);
    TableModel model = gen.initNormalTable();
    
    Row r = gen.new Row();
    model.getRows().add(r);
    r.getCells().add(gen.new Cell(null, defFile == null ? "" : defFile+"#extension."+ed.getName(), ed.getElement().get(0).getIsModifier() ? "modifierExtension" : "extension", null, null));
    r.getCells().add(gen.new Cell());
    r.getCells().add(gen.new Cell(null, null, null, null, null));
    r.getCells().add(gen.new Cell("", "", "Extension", null, null));

    Cell c = gen.new Cell("", "", "URL = "+ed.getUrl(), null, null);
    c.addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, ed.getName()+": "+ed.getDescription(), null));
    c.addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, describeExtensionContext(ed), null));
    r.getCells().add(c);
    
    if (ed.getElement().size() == 1) {
      r.setIcon("icon_extension_simple.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION_SIMPLE);
      genSimpleExtension(defFile, gen, r.getSubRows(), ed, pkp);
    } else {
      r.setIcon("icon_extension_complex.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION_COMPLEX);
      List<ElementDefinition> children = getChildren(ed.getElement(), ed.getElement().get(0));
      for (ElementDefinition child : children)
        genElement(defFile == null ? "" : defFile+"#extension.", gen, r.getSubRows(), child, ed.getElement(), null, pkp, true, null, false, false);
    }
    return gen.generate(model);
  }
  
  private void genSimpleExtension(String defFile, HeirarchicalTableGenerator gen, List<Row> rows, ExtensionDefinition ext, ProfileKnowledgeProvider pkp) throws Exception {
    Row r = gen.new Row();
    rows.add(r);
    r.setAnchor("value");
    ElementDefinition e = ext.getElement().get(0);
    String name;
    if (e.getType().size() == 1)
      name = "value"+Utilities.capitalize(e.getType().get(0).getCode());
    else
      name = "value[x]";
    
    r.getCells().add(gen.new Cell(null, defFile == null ? "" : defFile+"#extension.value[x]", name, e.getFormal(), null));
    r.getCells().add(gen.new Cell());
    r.getCells().add(gen.new Cell(null, null, describeCardinality(e, null, new UnusedTracker()), null, null));
    genTypes(gen, pkp, r, e, null, null);

    Cell c = generateDescription(gen, r, e, null, true, null, null, pkp, null);
    c.addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, describeExtensionContext(ext), null));
   }

//  private void genComplexExtension(String defFile, HeirarchicalTableGenerator gen, List<Row> rows, ExtensionDefinition ext, ProfileKnowledgeProvider pkp) throws Exception {
//    Row r = gen.new Row();
//    rows.add(r);
//    r.setAnchor(ext.getName());
//    ElementDefinition e = ext.getElement().get(0);
//    r.getCells().add(gen.new Cell(null, defFile == null ? "" : defFile+"#extension."+ext.getName(), ext.getName(), e.getFormal(), null));
//    r.getCells().add(gen.new Cell());
//    r.getCells().add(gen.new Cell(null, null, describeCardinality(e, null, new UnusedTracker()), null, null));
//    if (ext.getElement().size() == 1) {
//      r.setIcon("icon_extension_simple.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION_SIMPLE);
//      genTypes(gen, pkp, r, e, null, null);
//    } else {
//      r.setIcon("icon_extension_complex.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION_COMPLEX);
//      r.getCells().add(gen.new Cell());
//    }
//
//    Cell c = generateDescription(gen, r, e, null, true, ext.getUrl(), null, pkp, null);
//    c.addPiece(gen.new Piece("br")).addPiece(gen.new Piece(null, describeExtensionContext(ext), null));
//    
//  }

  private void genTypes(HeirarchicalTableGenerator gen, ProfileKnowledgeProvider pkp, Row r, ElementDefinition e, String profileBaseFileName, Profile profile) throws Exception {
    Cell c = gen.new Cell();
    r.getCells().add(c);
    boolean first = true;
    for (TypeRefComponent t : e.getType()) {
      if (first) 
        first = false; 
      else 
        c.addPiece(gen.new Piece(null,", ", null));
      if (t.getCode().equals("Reference") || (t.getCode().equals("Resource") && t.getProfileElement() != null)) {
        if (t.getProfile().startsWith("http://hl7.org/fhir/Profile/")) {
          String rn = t.getProfile().substring(28);
          c.addPiece(gen.new Piece(pkp.getLinkFor(rn), rn, null));
        } else if (t.getProfile().startsWith("#"))
          c.addPiece(gen.new Piece(profileBaseFileName+"."+t.getProfile().substring(1).toLowerCase()+".html", t.getProfile(), null));
        else
          c.addPiece(gen.new Piece(t.getProfile(), t.getProfile(), null));
      } else if (t.getProfileElement() != null) { // a profiled type
        String ref;
        ref = pkp.getLinkForProfile(profile, t.getProfile());
        if (ref != null) {
          String[] parts = ref.split("\\|");
          c.addPiece(gen.new Piece(parts[0], parts[1], t.getCode()));
        } else
          c.addPiece(gen.new Piece(ref, t.getCode(), null));
      } else if (pkp.hasLinkFor(t.getCode())) {
        c.addPiece(gen.new Piece(pkp.getLinkFor(t.getCode()), t.getCode(), null));
      } else
        c.addPiece(gen.new Piece(null, t.getCode(), null));
    }
  }
  
  private String describeExtensionContext(ExtensionDefinition ext) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (StringType t : ext.getContext())
      b.append(t.getValue());
    switch (ext.getContextType()) {
    case DATATYPE: return "Use on data type: "+b.toString();
    case EXTENSION: return "Use on extension: "+b.toString();
    case RESOURCE: return "Use on element: "+b.toString();
    case MAPPING: return "Use where element has mapping: "+b.toString();
    default:
      return "??";
    }
  }

  private String describeCardinality(ElementDefinition definition, ElementDefinition fallback, UnusedTracker tracker) {
    IntegerType min = definition.getMinElement();
    StringType max = definition.getMaxElement();
    if (min == null && fallback != null)
      min = fallback.getMinElement();
    if (max == null && fallback != null)
      max = fallback.getMaxElement();
    
    tracker.used = max == null || !max.getValue().equals("0");

    if (min == null && max == null)
      return null;
    else
      return (min == null ? "" : Integer.toString(min.getValue())) + ".." + (max == null ? "" : max.getValue());
  }

  public XhtmlNode generateTable(String defFile, Profile profile, boolean diff, String imageFolder, boolean inlineGraphics, ProfileKnowledgeProvider pkp, String profileBaseFileName, boolean snapshot) throws Exception {
    HeirarchicalTableGenerator gen = new HeirarchicalTableGenerator(imageFolder, inlineGraphics);
    TableModel model = gen.initNormalTable();
    List<ElementDefinition> list = diff ? profile.getDifferential().getElement() : profile.getSnapshot().getElement();
    List<Profile> profiles = new ArrayList<Profile>();
    profiles.add(profile);    
    genElement(defFile == null ? null : defFile+"#"+profile.getName()+".", gen, model.getRows(), list.get(0), list, profiles, pkp, diff, profileBaseFileName, null, snapshot);
    return gen.generate(model);
  }

  private void genElement(String defPath, HeirarchicalTableGenerator gen, List<Row> rows, ElementDefinition element, List<ElementDefinition> all, List<Profile> profiles, ProfileKnowledgeProvider pkp, boolean showMissing, String profileBaseFileName, Boolean extensions, boolean snapshot) throws Exception {
    Profile profile = profiles == null ? null : profiles.get(profiles.size()-1);
    String s = tail(element.getPath());
    if (!snapshot && extensions != null && extensions != (s.equals("extension") || s.equals("modifierExtension"))) 
      return;
    
    if (!onlyInformationIsMapping(all, element)) { 
      Row row = gen.new Row();
      row.setAnchor(element.getPath());
      boolean hasDef = element != null;
      boolean ext = false;
      if (s.equals("extension") || s.equals("modifierExtension")) { 
        row.setIcon("icon_extension_simple.png", HeirarchicalTableGenerator.TEXT_ICON_EXTENSION_SIMPLE);
        ext = true;
      } else if (!hasDef || element.getType().size() == 0)
        row.setIcon("icon_element.gif", HeirarchicalTableGenerator.TEXT_ICON_ELEMENT);
      else if (hasDef && element.getType().size() > 1) {
        if (allTypesAre(element.getType(), "Reference"))
          row.setIcon("icon_reference.png", HeirarchicalTableGenerator.TEXT_ICON_REFERENCE);
        else
          row.setIcon("icon_choice.gif", HeirarchicalTableGenerator.TEXT_ICON_CHOICE);
      } else if (hasDef && element.getType().get(0).getCode().startsWith("@"))
        row.setIcon("icon_reuse.png", HeirarchicalTableGenerator.TEXT_ICON_REUSE);
      else if (hasDef && isPrimitive(element.getType().get(0).getCode()))
        row.setIcon("icon_primitive.png", HeirarchicalTableGenerator.TEXT_ICON_PRIMITIVE);
      else if (hasDef && isReference(element.getType().get(0).getCode()))
        row.setIcon("icon_reference.png", HeirarchicalTableGenerator.TEXT_ICON_REFERENCE);
      else if (hasDef && isDataType(element.getType().get(0).getCode()))
        row.setIcon("icon_datatype.gif", HeirarchicalTableGenerator.TEXT_ICON_DATATYPE);
      else
        row.setIcon("icon_resource.png", HeirarchicalTableGenerator.TEXT_ICON_RESOURCE);
      String ref = defPath == null ? null : defPath + makePathLink(element);
      UnusedTracker used = new UnusedTracker();
      used.used = true;
      Cell left = gen.new Cell(null, ref, s, !hasDef ? null : element.getFormal(), null);
      row.getCells().add(left);
      Cell gc = gen.new Cell();
      row.getCells().add(gc);
      if (element != null && element.getIsModifier())
        gc.addImage("modifier.png", "This element is a modifier element", "M");
      if (element != null && element.getMustSupport()) 
        gc.addImage("mustsupport.png", "This element must be supported", "S");
      if (element != null && element.getIsSummary()) 
        gc.addImage("summary.png", "This element is included in summaries", "Σ");
      if (element != null && (!element.getConstraint().isEmpty() || !element.getCondition().isEmpty())) 
        gc.addImage("lock.png", "This element has or is affected by some invariants", "I");

      ExtensionDefinitionResult extDefn = null;
      if (ext) {
        if (element != null && element.getType().size() == 1 && element.getType().get(0).getProfile() != null) {
          extDefn = context.getExtensionDefinition(null, element.getType().get(0).getProfile());
          if (extDefn == null) {
            row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element, null, used), null, null));
            row.getCells().add(gen.new Cell(null, null, "?? "+element.getType().get(0).getProfile(), null, null));
            generateDescription(gen, row, element, null, used.used, profile.getUrl(), element.getType().get(0).getProfile(), pkp, profile);
          } else {
            String name = urltail(element.getType().get(0).getProfile());
            left.getPieces().get(0).setText(name);
            // left.getPieces().get(0).setReference((String) extDefn.getExtensionDefinition().getTag("filename"));
            left.getPieces().get(0).setHint("Extension URL = "+element.getType().get(0).getProfile());
            row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element, extDefn.getElementDefinition(), used), null, null));
            genTypes(gen, pkp, row, extDefn.getElementDefinition(), profileBaseFileName, profile);
            generateDescription(gen, row, element, extDefn.getElementDefinition(), used.used, null, null, pkp, profile);
          }
        } else{
          row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element, null, used), null, null));
          genTypes(gen, pkp, row, element, profileBaseFileName, profile);
          generateDescription(gen, row, element, null, used.used, null, null, pkp, profile);
        }
      } else {
        row.getCells().add(gen.new Cell(null, null, !hasDef ? null : describeCardinality(element, null, used), null, null));
        if (hasDef)
          genTypes(gen, pkp, row, element, profileBaseFileName, profile);
        else
          row.getCells().add(gen.new Cell());
        generateDescription(gen, row, element, null, used.used, null, null, pkp, profile);
      }
      if (element.getSlicing() != null) {
        if (standardExtensionSlicing(element)) {
          used.used = false;
          showMissing = false;
        } else {
          row.setIcon("icon_slice.png", HeirarchicalTableGenerator.TEXT_ICON_SLICE);
          row.getCells().get(2).getPieces().clear();
          for (Cell cell : row.getCells())
            for (Piece p : cell.getPieces()) {
              p.addStyle("font-style: italic");
            }
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
        List<ElementDefinition> children = getChildren(all, element);
        for (ElementDefinition child : children)
          genElement(defPath, gen, row.getSubRows(), child, all, profiles, pkp, showMissing, profileBaseFileName, false, snapshot);
        if (!snapshot) 
          for (ElementDefinition child : children)
            genElement(defPath, gen, row.getSubRows(), child, all, profiles, pkp, showMissing, profileBaseFileName, true, false);
      }
    }
  }


  private String urltail(String path) {
    if (path.contains("#"))
      return path.substring(path.lastIndexOf('#')+1);
    if (path.contains("/"))
      return path.substring(path.lastIndexOf('/')+1);
    else
      return path;

  }


  private boolean standardExtensionSlicing(ElementDefinition element) {
    String t = tail(element.getPath());
    return (t.equals("extension") || t.equals("modifierExtension"))
          && element.getSlicing().getRules() != ResourceSlicingRules.CLOSED && element.getSlicing().getDiscriminator().size() == 1 && element.getSlicing().getDiscriminator().get(0).getValue().equals("url");
  }


  private String makePathLink(ElementDefinition element) {
    if (element.getName() == null)
      return element.getPath();
    if (!element.getPath().contains("."))
      return element.getName();
    return element.getPath().substring(0, element.getPath().lastIndexOf("."))+"."+element.getName();
  }

  private Cell generateDescription(HeirarchicalTableGenerator gen, Row row, ElementDefinition definition, ElementDefinition fallback, boolean used, String baseURL, String url, ProfileKnowledgeProvider pkp, Profile profile) throws Exception {
    // TODO Auto-generated method stub
    Cell c = gen.new Cell();
    row.getCells().add(c);                

    if (used) {
      if (definition != null && definition.getShort() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.addPiece(gen.new Piece(null, definition.getShort(), null));
      } else if (fallback != null && fallback != null && fallback.getShort() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.addPiece(gen.new Piece(null, fallback.getShort(), null));
      }
      if (url != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        String fullUrl = url.startsWith("#") ? baseURL+url : url;
        ExtensionDefinitionResult ed = context.getExtensionDefinition(null, url);
        String ref = ed == null ? null : (String) ed.getExtensionDefinition().getTag("filename");
        c.getPieces().add(gen.new Piece(null, "URL: ", null).addStyle("font-weight:bold"));
        c.getPieces().add(gen.new Piece(ref, fullUrl, null));
      }

      if (definition.getSlicing() != null) {
        if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
        c.getPieces().add(gen.new Piece(null, "Slice: ", null).addStyle("font-weight:bold"));
        c.getPieces().add(gen.new Piece(null, describeSlice(definition.getSlicing()), null));
      }
      if (definition != null) {
        if (definition.getBinding() != null) {
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          String ref = pkp.resolveBinding(definition.getBinding());
          c.getPieces().add(gen.new Piece(null, "Binding: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(ref, definition.getBinding().getName(), null));
          if (definition.getBinding().getConformance() != null || definition.getBinding().getIsExtensibleElement() != null) {
            c.getPieces().add(gen.new Piece(null, " (", null));
            if (definition.getBinding().getConformance() != null)
              c.getPieces().add(gen.new Piece(null, definition.getBinding().getConformance().toCode(), definition.getBinding().getConformance().getDefinition()));
            if (definition.getBinding().getConformance() != null && definition.getBinding().getIsExtensibleElement() != null) 
              c.getPieces().add(gen.new Piece(null, ", ", null));
            if (definition.getBinding().getIsExtensibleElement() != null)
              c.getPieces().add(gen.new Piece(null, definition.getBinding().getIsExtensible() ? "extensible" : "not extensible", null));
            c.getPieces().add(gen.new Piece(null, ")", null));
          }
        }
        for (ElementDefinitionConstraintComponent inv : definition.getConstraint()) {
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Inv-"+inv.getKey()+": ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, inv.getHuman(), null));
        }
        if (definition.getFixed() != null) {        
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Fixed Value: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, buildJson(definition.getFixed()), null).addStyle("color: darkgreen"));
        } else if (definition.getPattern() != null) {        
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Required Pattern: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, buildJson(definition.getPattern()), null).addStyle("color: darkgreen"));
        } else if (definition.getExample() != null) {        
          if (!c.getPieces().isEmpty()) c.addPiece(gen.new Piece("br"));
          c.getPieces().add(gen.new Piece(null, "Example: ", null).addStyle("font-weight:bold"));
          c.getPieces().add(gen.new Piece(null, buildJson(definition.getExample()), null).addStyle("color: darkgreen"));
        }
      }
    }
    return c;
  }

  private String buildJson(Type value) throws Exception {
    if (value instanceof PrimitiveType)
      return ((PrimitiveType) value).asStringValue();
    
    JsonComposer json = new JsonComposer();
    return json.composeString(value, false);
  }


  public String describeSlice(ElementDefinitionSlicingComponent slicing) {
    return (slicing.getOrdered() ? "Ordered, " : "Unordered, ")+describe(slicing.getRules())+", by "+commas(slicing.getDiscriminator());
  }

  private String commas(List<IdType> discriminator) {
    CommaSeparatedStringBuilder c = new CommaSeparatedStringBuilder();
    for (IdType id : discriminator)
      c.append(id.asStringValue());
    return c.toString();
  }


  private String describe(ResourceSlicingRules rules) {
    switch (rules) {
    case CLOSED : return "Closed";
    case OPEN : return "Open";
    case OPENATEND : return "Open At End";
    default:
      return "??";
    }
  }

  private boolean onlyInformationIsMapping(List<ElementDefinition> list, ElementDefinition e) {
    return (e.getName() == null && e.getSlicing() == null && (e == null || onlyInformationIsMapping(e))) &&
        getChildren(list, e).isEmpty();
  }

  private boolean onlyInformationIsMapping(ElementDefinition d) {
    return d.getShort() == null && d.getFormal() == null && 
        d.getRequirements() == null && d.getSynonym().isEmpty() && d.getMinElement() == null &&
        d.getMax() == null && d.getType().isEmpty() && d.getNameReference() == null && 
        d.getExample() == null && d.getFixed() == null && d.getMaxLengthElement() == null &&
        d.getCondition().isEmpty() && d.getConstraint().isEmpty() && d.getMustSupportElement() == null &&
        d.getBinding() == null;
  }

  private boolean allTypesAre(List<TypeRefComponent> types, String name) {
    for (TypeRefComponent t : types) {
      if (!t.getCode().equals(name))
        return false;
    }
    return true;
  }

  private List<ElementDefinition> getChildren(List<ElementDefinition> all, ElementDefinition element) {
    List<ElementDefinition> result = new ArrayList<ElementDefinition>();
    int i = all.indexOf(element)+1;
    while (i < all.size() && all.get(i).getPath().length() > element.getPath().length()) {
      if ((all.get(i).getPath().substring(0, element.getPath().length()+1).equals(element.getPath()+".")) && !all.get(i).getPath().substring(element.getPath().length()+1).contains(".")) 
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
    return Utilities.existsInList(value, "Identifier", "HumanName", "Address", "ContactPoint", "Timing", "Quantity", "Attachment", "Range", 
          "Period", "Ratio", "CodeableConcept", "Coding", "SampledData", "Age", "Distance", "Duration", "Count", "Money");
  }

  private boolean isReference(String value) {
    return value.equals("Reference");
  }

  private boolean isPrimitive(String value) {
    return Utilities.existsInList(value, "boolean", "integer", "decimal", "base64Binary", "instant", "string", "date", "dateTime", "code", "oid", "uuid", "id");
  }

//  private static String listStructures(Profile p, ProfileKnowledgeProvider pkp) throws Exception {
//    StringBuilder b = new StringBuilder();
//    boolean first = true;
//    for (ProfileStructureComponent s : p.getStructure()) {
//      if (first)
//        first = false;
//      else
//        b.append(", ");
//      if (pkp != null && pkp.hasLinkFor(s.getType()))
//        b.append("<a href=\""+pkp.getLinkFor(s.getType())+"\">"+s.getType()+"</a>");
//    else
//        b.append(s.getType());
//    }
//    return b.toString();
//  }


  public Profile getProfile(Profile source, String url) throws Exception {
    Profile profile;
    String code;
    if (url.startsWith("#")) {
      profile = source;
      code = url.substring(1);
    } else {
      String[] parts = url.split("\\#");
      if (!context.getProfiles().containsKey(parts[0])) {
      	if (parts[0].startsWith("http:") || parts[0].startsWith("https:")) {
        	String[] ps = parts[0].split("\\/Profile\\/");
        	if (ps.length != 2)
        		throw new Exception("Unable to understand address of profile: "+parts[0]);
        	FHIRClient client = new FHIRSimpleClient();
        	client.initialize(ps[0]);
        	Profile ae = client.read(Profile.class, ps[1]);
        	context.getProfiles().put(parts[0], ae);
      	} else
      		return null;
      }
      profile = context.getProfiles().get(parts[0]);
      code = parts.length < 2 ? null : parts[1];
    }

  	if (profile == null) 
  		return null;
  	if (code == null)
  		return profile;
  	for (Resource r : profile.getContained()) {
  		if (r instanceof Profile && r.getId().equals(code))
  			return (Profile) r;
    }
    return null;
  }

//  public ExtensionResult getExtensionDefn(Profile source, String url) {
//    Profile profile;
//    String code;
//    if (url.startsWith("#")) {
//      profile = source;
//      code = url.substring(1);
//    } else {
//      String[] parts = url.split("\\#");
//      profile = context.getProfiles().get(parts[0]).getResource();
//      code = parts[1];
//    }
//
//    if (profile != null) {
//      ProfileExtensionDefnComponent defn = null;
//      for (ProfileExtensionDefnComponent s : profile.getExtensionDefn()) {
//        if (s.getName().equals(code)) 
//          defn = s;
//      }
//      if (defn != null)
//        return new ExtensionResult(profile, defn);
//    }
//    return null;
//  }
//
  
}
