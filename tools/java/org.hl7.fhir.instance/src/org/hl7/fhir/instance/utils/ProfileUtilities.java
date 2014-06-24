package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.httpclient.methods.GetMethod;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.String_;
import org.hl7.fhir.utilities.Utilities;

public class ProfileUtilities {

  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, ElementComponent element) {
  	return getChildMap(structure, element.getPathSimple());
  }
  
  public static Map<String, ElementComponent> getChildMap(ProfileStructureComponent structure, String path) {
    HashMap<String, ElementComponent> res = new HashMap<String, Profile.ElementComponent>(); 
    for (ElementComponent e : structure.getElement()) {
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
    for (ElementComponent e : structure.getElement()) {
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
  public ProfileStructureComponent generateSnapshot(ProfileStructureComponent base, ProfileStructureComponent differential) throws Exception {
    if (!differential.getTypeSimple().equals(base.getTypeSimple()))
      throw new Exception("Mismatch types between base and snapshot");
      
    ProfileStructureComponent result = new ProfileStructureComponent();
    result.setNameSimple(differential.getNameSimple());
    result.setTypeSimple(differential.getTypeSimple());
    result.setPublishSimple(differential.getPublishSimple());
    result.setPublishSimple(differential.getPublishSimple());
    for (ElementComponent e : base.getElement()) {
      result.getElement().add(e.copy());
    }
    for (ElementComponent src : differential.getElement()) {
      ElementComponent dst = getComponentByName(result, src.getPathSimple());
      if (dst == null)
        throw new Exception("found no matching profile element for "+src.getPathSimple());
      if (dst.getDefinition() == null)
        dst.setDefinition(new ElementDefinitionComponent());
      
      updateFromDefinition(dst.getDefinition(), src.getDefinition());
    }
    return result;
  }

  private ElementComponent getComponentByName(ProfileStructureComponent context, String path) {
    for (ElementComponent e : context.getElement()) 
      if (e.getPathSimple().equals(path))
        return e;
    return null;
  }

  private void updateFromDefinition(ElementDefinitionComponent dst, ElementDefinitionComponent src) {
    if (src != null) {
      dst.setShortSimple(src.getShortSimple());
      dst.setFormalSimple(src.getFormalSimple());
      if (src.getCommentsSimple() != null)
        dst.setCommentsSimple(src.getCommentsSimple());
      if (src.getRequirementsSimple() != null)
        dst.setRequirementsSimple(src.getRequirementsSimple());
      for (String_ s : src.getSynonym()) {
        if (!dst.hasSynonymSimple(s.getValue()))
          dst.getSynonym().add(s.copy());
      }
      dst.setMinSimple(src.getMinSimple());
      dst.setMaxSimple(src.getMaxSimple());
      if (src.getValue() != null)
        dst.setValue(src.getValue());
      if (src.getExample() != null)
        dst.setExample(src.getExample());
      if (src.getMaxLength() != null)
        dst.setMaxLengthSimple(src.getMaxLengthSimple());
      // todo: what to do about conditions? 
      // condition : id 0..*
      if (src.getMustSupportSimple())
        dst.setMustSupportSimple(true);
      // profiles cannot change : isModifier
      if (src.getBinding() != null)
        dst.setBinding(src.getBinding());
      
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
