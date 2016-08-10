package org.hl7.fhir.tools.publisher;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.dstu3.model.Bundle;
import org.hl7.fhir.dstu3.model.Bundle.BundleEntryComponent;
import org.hl7.fhir.convertors.VersionConvertor;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.exceptions.FHIRFormatError;
import org.hl7.fhir.dstu3.formats.XmlParser;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.model.StructureDefinition.StructureDefinitionKind;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utilities.CommaSeparatedStringBuilder;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlComposer;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class SpecDifferenceEvaluator {

  public class SpecPackage {
//    private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
//    private Map<String, ValueSet> expansions = new HashMap<String, ValueSet>();
    private Map<String, StructureDefinition> types = new HashMap<String, StructureDefinition>();
    private Map<String, StructureDefinition> resources = new HashMap<String, StructureDefinition>();
//    private Map<String, StructureDefinition> extensions = new HashMap<String, StructureDefinition>();
//    private Map<String, StructureDefinition> profiles = new HashMap<String, StructureDefinition>();
    public Map<String, StructureDefinition> getTypes() {
      return types;
    }
    public Map<String, StructureDefinition> getResources() {
      return resources;
    }
    
  }
  
  private SpecPackage original = new SpecPackage();
  private SpecPackage revision = new SpecPackage();
  private Map<String, String> renames = new HashMap<String, String>();
  
  private XhtmlNode tbl;
  
  
  
  
  public void loadFromIni(IniFile ini) {
    String[] names = ini.getPropertyNames("r2-renames");
    for (String n : names)
      // note reverse of order
      renames.put(ini.getStringProperty("r2-renames", n), n);
  }
  
  public SpecPackage getOriginal() {
    return original;
  }
  public SpecPackage getRevision() {
    return revision;
  }

  public static void main(String[] args) throws Exception {
    System.out.println("gen diff");
    SpecDifferenceEvaluator self = new SpecDifferenceEvaluator();
    self.loadFromIni(new IniFile("C:\\work\\org.hl7.fhir\\build\\source\\fhir.ini"));
//    loadVS2(self.original.valuesets, "C:\\work\\org.hl7.fhir.dstu2.original\\build\\publish\\valuesets.xml");
//    loadVS(self.revision.valuesets, "C:\\work\\org.hl7.fhir.dstu2.original\\build\\publish\\valuesets.xml");

    loadSD2(self.original.types, "C:\\work\\org.hl7.fhir.dstu2.original\\build\\publish\\profiles-types.xml");
    loadSD(self.revision.types, "C:\\work\\org.hl7.fhir\\build\\publish\\profiles-types.xml");
    loadSD2(self.original.resources, "C:\\work\\org.hl7.fhir.dstu2.original\\build\\publish\\profiles-resources.xml");
    loadSD(self.revision.resources, "C:\\work\\org.hl7.fhir\\build\\publish\\profiles-resources.xml");
    
    StringBuilder b = new StringBuilder();
    b.append("<html>\r\n");
    b.append("<head>\r\n");
    b.append("<link href=\"fhir.css\" rel=\"stylesheet\"/>\r\n");
    b.append("</head>\r\n");
    b.append("<body>\r\n");
    b.append(self.getDiffAsHtml());
    b.append("</body>\r\n");
    b.append("</html>\r\n");
    TextFile.stringToFile(b.toString(), "c:\\temp\\diff.html");
    System.out.println("done");
  }
  
  private static void loadSD2(Map<String, StructureDefinition> map, String fn) throws FHIRException, FileNotFoundException, IOException {
    org.hl7.fhir.dstu2.model.Bundle bundle = (org.hl7.fhir.dstu2.model.Bundle) new org.hl7.fhir.dstu2.formats.XmlParser().parse(new FileInputStream(fn));
    for (org.hl7.fhir.dstu2.model.Bundle.BundleEntryComponent be : bundle.getEntry()) {
      if (be.getResource() instanceof org.hl7.fhir.dstu2.model.StructureDefinition) {
        org.hl7.fhir.dstu2.model.StructureDefinition sd = (org.hl7.fhir.dstu2.model.StructureDefinition) be.getResource();
        map.put(sd.getName(), new VersionConvertor(null).convertStructureDefinition(sd));
      }
    }
    
  }
  private static void loadSD(Map<String, StructureDefinition> map, String fn) throws FHIRFormatError, FileNotFoundException, IOException {
    Bundle bundle = (Bundle) new XmlParser().parse(new FileInputStream(fn));
    for (BundleEntryComponent be : bundle.getEntry()) {
      if (be.getResource() instanceof StructureDefinition) {
        StructureDefinition sd = (StructureDefinition) be.getResource();
        map.put(sd.getName(), sd);
      }
    }
  }

  public String getDiffAsHtml(StructureDefinition rev) throws IOException {

    StructureDefinition orig = original.resources.get(checkRename(rev.getName()));
    if (orig == null)
      orig = original.types.get(checkRename(rev.getName()));
    if (orig == null)
      return "<p>This "+rev.getKind().toCode()+" did not exist in Release 2</p>";
    else {
      start();
      compare(orig, rev);
      return  "<p>This table summarises the change in this resource from Release 2 to this version</p>\r\n"+ new XhtmlComposer().setPretty(true).compose(tbl);
    }
  }
  
  public String getDiffAsHtml() throws IOException {
    start();
    
    header("Types");
    for (String s : sorted(revision.types.keySet())) {
      StructureDefinition orig = original.types.get(s);
      StructureDefinition rev = revision.types.get(s);
      if (orig == null) {
        markNew(rev.getName(), true);
      } else if (rev.getKind() == StructureDefinitionKind.PRIMITIVETYPE) {
        markNoChanges(rev.getName(), true);
      } else if (rev.hasDerivation() && orig.hasDerivation() && rev.getDerivation() != orig.getDerivation()) {
        markChanged(rev.getName(), "Changed from a "+orig.getDerivation().toCode()+" to a "+rev.getDerivation().toCode(), true);
      } else {
        compare(orig, rev);
      }
    }
    for (String s : sorted(original.types.keySet())) {
      StructureDefinition orig = original.types.get(s);
      StructureDefinition rev = revision.types.get(s);
      if (rev == null)
        markDeleted(orig.getName(), true);
    }
    
    header("Resources");
    for (String s : sorted(revision.resources.keySet())) {
      StructureDefinition orig = original.resources.get(checkRename(s));
      StructureDefinition rev = revision.resources.get(s);
      if (orig == null) {
        markNew(rev.getName(), true);
      } else {
        compare(orig, rev);
      }
    }
    for (String s : sorted(original.resources.keySet())) {
      StructureDefinition orig = original.resources.get(s);
      StructureDefinition rev = revision.resources.get(s);
      if (rev == null)
        markDeleted(orig.getName(), true);
    }
    
    return new XhtmlComposer().setPretty(true).compose(tbl);
  }
  
  private Object checkRename(String s) {
    if (renames.containsKey(s))
      return renames.get(s);
    else 
      return s;
  }

  private List<String> sorted(Set<String> keys) {
    List<String> list = new ArrayList<String>();
    list.addAll(keys);
    Collections.sort(list);
    return list;
  }
  private void header(String title) {
    tbl.addTag("tr").setAttribute("class", "diff-title").addTag("td").setAttribute("colspan", "2").addText(title);
  }
  
  private void start() {
    tbl = new XhtmlNode(NodeType.Element, "table");
    tbl.setAttribute("class", "grid");
    
  }
  
  private void markNoChanges(String name, boolean item) {
    XhtmlNode tr = tbl.addTag("tr").setAttribute("class", item ? "diff-item" : "diff-entry");
    XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
    XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");
    left.addText(name);
    right.addText("No Changes");
  }
  
  private void markChanged(String name, String change, boolean item) {
    XhtmlNode tr = tbl.addTag("tr").setAttribute("class", item ? "diff-item" : "diff-entry");
    XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
    XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");
    left.addText(name);
    right.addText(change);
  }
  
  private void markDeleted(String name, boolean item) {
    XhtmlNode tr = tbl.addTag("tr").setAttribute("class", item ? "diff-del-item" : "diff-del");
    XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
    XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");
    left.addText(name);
    right.addText("deleted");
  }
  
  private void markNew(String name, boolean item) {
    XhtmlNode tr = tbl.addTag("tr").setAttribute("class", item ? "diff-new-item" : "diff-new");
    XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
    XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");
    left.addText(name);
    right.addText("added");    
  }

  private void compare(StructureDefinition orig, StructureDefinition rev) {
    XhtmlNode tr = tbl.addTag("tr").setAttribute("class", "diff-item");
    XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
    left.addText(rev.getName());
    XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");

    // first, we must match revision elements to old elements
    boolean changed = false;
    if (!orig.getName().equals(rev.getName())) {
      changed = true;
      right.addText("Name Changed from "+orig.getName()+" to "+rev.getName());
    }
    for (ElementDefinition ed : rev.getDifferential().getElement()) { 
      ElementDefinition oed = getMatchingElement(rev.getName(), orig.getDifferential().getElement(), ed);
      if (oed != null) {
        ed.setUserData("match", oed);
        oed.setUserData("match", ed);
      }
    }

    for (ElementDefinition ed : rev.getDifferential().getElement()) {
      ElementDefinition oed = (ElementDefinition) ed.getUserData("match");
      if (oed == null) {
        changed = true;
        markNew(ed.getPath(), false);        
      } else 
        changed = compareElement(ed, oed) || changed;
    }
    
    List<String> dels = new ArrayList<String>();
    
    for (ElementDefinition ed : orig.getDifferential().getElement()) {
      if (ed.getUserData("match") == null) {
        changed = true;
        boolean marked = false;
        for (String s : dels)
          if (ed.getPath().startsWith(s+".")) 
            marked = true;
        if (!marked) {
          dels.add(ed.getPath());
        markDeleted(ed.getPath(), false);
        }
      }
    }

    if (!changed)
      tr.addText("No Changes");
    
    for (ElementDefinition ed : rev.getDifferential().getElement()) 
      ed.clearUserData("match");
    for (ElementDefinition ed : orig.getDifferential().getElement()) 
      ed.clearUserData("match");
    
  }

  private ElementDefinition getMatchingElement(String tn, List<ElementDefinition> list, ElementDefinition target) {
    // now, look for matches by name (ignoring slicing for now)
    String tp = mapPath(tn, target.getPath());
    if (tp.endsWith("[x]"))
      tp = tp.substring(0, tp.length()-3);
    for (ElementDefinition ed : list) {
      String p = ed.getPath();
      if (p.endsWith("[x]"))
        p = p.substring(0, p.length()-3);
      if (p.equals(tp))
        return ed;
    }
    return null;
  }
  
  /**
   * change from rev to original. TODO: make this a config file somewhere?
   * 
   * @param tn
   * @param name
   * @return
   */
  private String mapPath(String tn, String path) {
    if (renames.containsKey(path))
      return renames.get(path);
    for (String r : renames.keySet()) {
      if (path.startsWith(r+"."))
        return renames.get(r)+"."+path.substring(r.length()+1);
    }
    return path;
  }

  private boolean compareElement(ElementDefinition rev, ElementDefinition orig) {
    CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
    String rn = tail(rev.getPath());
    String on = tail(orig.getPath());
    if (!rn.equals(on) && rev.getPath().contains("."))
      b.append("Renamed from "+on+" to " +rn);

    if (rev.getMin() != orig.getMin())
      b.append("Min Cardinality changed from "+Integer.toString(orig.getMin())+" to " +Integer.toString(rev.getMin()));

    if (!rev.getMax().equals(orig.getMax()))
      b.append("Max Cardinality changed from "+orig.getMax()+" to " +rev.getMax());
    
    String types = analyseTypes(rev, orig);
    if (!Utilities.noString(types))
      b.append(types);
    
    if (b.length() > 0) {
      XhtmlNode tr = tbl.addTag("tr").setAttribute("class", "diff-entry");
      XhtmlNode left = tr.addTag("td").setAttribute("class", "diff-left");
      left.addText(rev.getPath());
      XhtmlNode right = tr.addTag("td").setAttribute("class", "diff-right");
      right.addText(b.toString());
    }
    return b.length() > 0;
  }
  
  private String tail(String path) {
    return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
  }
  
  private String analyseTypes(ElementDefinition rev, ElementDefinition orig) {
    if (rev.getType().size() == 1 && orig.getType().size() == 1) {
      String r = describeType(rev.getType().get(0));
      String o = describeType(orig.getType().get(0));
      if ((r == null && o == null) || r.equals(o))
        return null;
      else
        return "Type changed from "+o+" to "+r; 
    } else {
      CommaSeparatedStringBuilder b = new CommaSeparatedStringBuilder();
      for (TypeRefComponent tr : orig.getType()) {
        if (!hasType(rev.getType(), tr))
          b.append("Remove "+describeType(tr));
      }
      for (TypeRefComponent tr : rev.getType()) {
        if (!hasType(orig.getType(), tr) && !isAbstractType(tr.getCode()))
          b.append("Add "+describeType(tr));
      }
      return b.toString();
    }
  }
  
  private boolean isAbstractType(String code) {
    return Utilities.existsInList(code, "Element", "BackboneElement");
  }
  
  private boolean hasType(List<TypeRefComponent> types, TypeRefComponent tr) {
    for (TypeRefComponent t : types) {
      if (t.getCode().equals(tr.getCode())) {
        if ((!t.hasProfile() && !tr.hasProfile()) || (t.getProfile().equals(tr.getProfile())))
          return true;
      }
    }
    return false;
  }
  private String describeType(TypeRefComponent tr) {
    if (!tr.hasProfile()) 
      return tr.getCode();
    else if (tr.getCode().equals("Reference") && tr.getProfile().startsWith("http://hl7.org/fhir/StructureDefinition/"))
      return tr.getCode()+"("+tr.getProfile().substring(40)+")";
    else
      return tr.getCode()+"{"+tr.getProfile()+"}";
  }
 
}
