package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.utilities.Utilities;

public class ProfileUtilities {

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
          if (diffMatches.get(0).getSlicing() == null) // well, the diff has set up a slice, but hasn't defined it. this is an error
            throw new Exception("differential does not have a slice"); 
            
          // well, if it passed those preconditions then we slice the dest. 
          // we're just going to accept the differential slicing at face value
          ElementComponent outcome = currentBase.copy();
          updateFromDefinition(outcome, diffMatches.get(0));
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
      src.getType().clear();
      src.getType().addAll(dst.getType());
      
      // todo: mappings are cumulative - or does one replace another?
      dst.getMapping().addAll(src.getMapping());
      
      // todo: constraints are cumulative - or does one replace another?
      dst.getConstraint().addAll(src.getConstraint());
    }
    
  }
  
  
}
