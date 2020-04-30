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
import org.hl7.fhir.definitions.model.ImplementationGuideDefn;
import org.hl7.fhir.definitions.model.LogicalModel;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
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


  public String generateReport() throws FHIRException {

    
    StringBuilder output = new StringBuilder();
    
    Map<String, List<ReferenceElement>> set = new HashMap<>();
    for (ReferenceElement e : list) {
      String n = sortRefs(e.focus);
      if (!set.containsKey(n))
        set.put(n, new ArrayList<>());
      set.get(n).add(e);
    }
    List<String> sorted = new ArrayList<>();
    for (String n : set.keySet())
      sorted.add(n);
    Collections.sort(sorted);
    
    output.append("<p>Participation type Patterns</p>\r\n");
    output.append("<table class=\"grid\">\r\n");
    output.append("<tr><td><b>Resources</b></td><td><b>Pattern</b> (or candidates)</td><td><b>Locations</b></td></tr>\r\n"); // <b>RIM Classes</b>
    for (String s : sorted) {
      if (s.contains(",") && (s.contains("Patient") || s.contains("Practitioner") || s.contains("Organization") )) {
        addPatternToTable(s, set.get(s), output);
      }
    }
    output.append("</table>\r\n");
    output.append("<p>Other Patterns</p>\r\n");
    output.append("<table class=\"grid\">\r\n");
    output.append("<tr><td><b>Resources</b></td><td><b>Pattern</b> (or candidates)</td><td><b>Locations</b></td></tr>\r\n");
    for (String s : sorted)
      if (s.contains(",") && !(s.contains("Patient") || s.contains("Practitioner") || s.contains("Organization") ))
        addPatternToTable(s, set.get(s), output);
    output.append("</table>\r\n");
    return output.toString();
  }

 
  private void addPatternToTable(String s, List<ReferenceElement> list2, StringBuilder output) throws FHIRException {
    output.append("<tr><td>");
    boolean first = true;
    for (String r : s.split("\\,")) {
      if (first) first = false; else output.append(", ");
      output.append("<a href=\""+r.toLowerCase().trim()+".html#"+r.trim()+"\">"+r.trim()+"</a>");
    }      
    output.append("</td><td>");
    output.append(findMatchingPattern(s));
    output.append("</td><td>");
//    first = true;
//    Set<String> rc = new HashSet<>();
//    for (String r : s.split("\\,")) {
//      rc.add(definitions.getResourceByName(r.trim()).getRimClass().toCode());
//    }      
//    for (String r : rc) {
//      if (first) first = false; else output.append(", ");
//      output.append(r);
//    }
//    output.append("</td><td>");
    first = true;
    for (ReferenceElement e : list2) {
      if (first) first = false; else output.append("<br/>");
      String rn = e.root.getName();
      if (definitions.hasResource(rn))
        output.append("<a href=\""+rn.toLowerCase()+"-definitions.html#"+e.focus.getPath()+"\">"+e.focus.getPath()+"</a>");
      else
        output.append("<a href=\"datatypes-definitions.html#"+e.focus.getPath()+"\">"+e.focus.getPath()+"</a>");
      
    }
    output.append("</td></tr>\r\n");
  }

  private String findMatchingPattern(String p) {
    // try and find a direct pattern match
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
        for (String s : lm.getImplementations())
          b.append(s);
        if (b.toString().equals(p))
          return "<a href=\""+lm.getResource().getName()+".html#"+lm.getResource().getRoot().getName()+"\">"+lm.getResource().getRoot().getName()+"</a>";
      }
    }
    String[] pl = p.split("\\,");
    Set<String> ps = new HashSet<>();
    for (String s : pl)
      ps.add(s.trim());
    
    // note any close patterns
    CommaSeparatedStringBuilder pb = new CommaSeparatedStringBuilder("<br/>");
    for (ImplementationGuideDefn ig : definitions.getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        Set<String> bs = new HashSet<>();
        for (String s : lm.getImplementations())
          bs.add(s);
        if (!bs.isEmpty()) {
        Set<String> missed = new HashSet<>();
        Set<String> extra = new HashSet<>();
        Utilities.analyseStringDiffs(ps, bs, missed, extra);
        if (missed.size() + extra.size() <= 3) {
          String s = "<a href=\""+lm.getResource().getName()+".html#"+lm.getResource().getRoot().getName()+"\">"+lm.getResource().getRoot().getName()+"</a>";
          if (missed.size() > 0)
            s = s + " + " + missed.toString();
          if (extra.size() > 0)
            s = s + " - " + extra.toString();
          pb.append(s);
        }
        }
      }
    }
    
    return pb.toString();
  }

  private String sortRefs(ElementDefn focus) {
    List<String> s = new ArrayList<>();
    for (TypeRef t : focus.getTypes()) {
      if ("Reference".equals(t.getName())) {
        for (String p : t.getParams()) {
          if (definitions.hasLogicalModel(p)) 
            s.addAll(definitions.getLogicalModel(p).getImplementations());
          else
            s.add(p);
        }
      }
    }
    Collections.sort(s);
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    for (String n : s)
      b.append(n);
    return b.toString();
  }
}
