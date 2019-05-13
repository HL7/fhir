package org.hl7.fhir.definitions.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.validation.PatternFinder.ReferenceElement;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.Resource;
import org.hl7.fhir.utilities.Utilities;

public class PatternFinder {

  public class ReferenceElement {
    ElementDefn root;
    ElementDefn focus;
    public ReferenceElement(ElementDefn root, ElementDefn focus) {
      super();
      this.root = root;
      this.focus = focus;
    }

  }

  private List<ReferenceElement> list = new ArrayList<>();
  private Definitions definitions;
  
  
  public PatternFinder(Definitions definitions) {
    super();
    this.definitions = definitions;
  }

  public void registerReference(ElementDefn root, ElementDefn focus) {
    list.add(new ReferenceElement(root, focus));
  }
  
  public static List<String> sorted(Set<String> set) {
    List<String> list = new ArrayList<>();
    list.addAll(set);
    Collections.sort(list);
    return list;
  }


  public void report() throws FHIRException {
    
    for (String rn : sorted(definitions.getResources().keySet())) {
      ResourceDefn r = definitions.getResourceByName(rn);
      System.out.println(rn+": "+r.getRoot().getMapping("http://hl7.org/v3"));
    }
    
    for (String rn : sorted(definitions.getResources().keySet())) {
      ResourceDefn r = definitions.getResourceByName(rn);
      System.out.println(rn+": "+r.getRimClass().toString());    
      
    }
    Map<String, List<String>> set = new HashMap<>();
    for (ReferenceElement e : list) {
      String n = sortRefs(e.focus);
      if (!set.containsKey(n))
        set.put(n, new ArrayList<>());
      set.get(n).add(e.focus.getPath());
    }
    List<String> sorted = new ArrayList<>();
    for (String n : set.keySet())
      sorted.add(n);
    Collections.sort(sorted);
    for (String s : sorted)
      if (s.contains(",") && (s.contains("Patient") || s.contains("Practitioner") || s.contains("Organization") ))
        System.out.println(s+": "+set.get(s).toString());
    for (String s : sorted)
      if (s.contains(",") && !(s.contains("Patient") || s.contains("Practitioner") || s.contains("Organization") ))
        System.out.println(s+": "+set.get(s).toString());
//    throw new Error("stop!");
  }

  private String sortRefs(ElementDefn focus) {
    List<String> s = new ArrayList<>();
    for (TypeRef t : focus.getTypes()) {
      if ("Reference".equals(t.getName())) {
        s.addAll(t.getParams());
      }
    }
    Collections.sort(s);
    return s.toString();
  }
}
