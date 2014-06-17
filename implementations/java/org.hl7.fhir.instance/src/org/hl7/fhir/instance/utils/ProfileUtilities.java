package org.hl7.fhir.instance.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
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


}
