/*
Copyright (c) 2011-2013, HL7, Inc
All rights reserved.

Redistribution and use in source and binary forms, with or without modification, 
are permitted provided that the following conditions are met:

 * Redistributions of source code must retain the above copyright notice, this 
   list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, 
   this list of conditions and the following disclaimer in the documentation 
   and/or other materials provided with the distribution.
 * Neither the name of HL7 nor the names of its contributors may be used to 
   endorse or promote products derived from this software without specific 
   prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND 
ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED 
WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. 
IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, 
INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT 
NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR 
PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, 
WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) 
ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE 
POSSIBILITY OF SUCH DAMAGE.

*/
package org.hl7.fhir.tools.publisher;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sourceforge.plantuml.SourceStringReader;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class DiagramGenerator {

  private PageProcessor page;

  private Map<String, String> founddefinitions = new HashMap<String, String>();
  
  public DiagramGenerator(PageProcessor page) {
    super();
    this.page = page;
  }

  public String generateFromSource(String title, String filename) throws Exception {
    String src = TextFile.fileToString(filename);
    if (src.startsWith("[diagram]")) {
      IniFile ini = new IniFile(filename);
      String[] classes = ini.getStringProperty("diagram", "classes").split(",");
      StringBuilder s = new StringBuilder();
      StringBuilder s2 = new StringBuilder();
      s.append("@startuml\r\n");
      s.append("title "+ini.getStringProperty("diagram", "title")+"\r\n");
      s.append("skinparam nodesep 10\r\n");
      s.append("skinparam ranksep 10\r\n");
      s.append("skinparam classBackgroundColor Aliceblue\r\n\r\n");
      s.append("skinparam classBorderColor Gray\r\n\r\n");
      s.append("skinparam classArrowColor Navy\r\n\r\n");

      List<org.hl7.fhir.definitions.model.ElementDefn> queue = new ArrayList<org.hl7.fhir.definitions.model.ElementDefn>();
      List<String> elementClasses = new ArrayList<String>();
      Map<String, String> replacements = new HashMap<String, String>();
      Map<org.hl7.fhir.definitions.model.ElementDefn, String> names = new HashMap<org.hl7.fhir.definitions.model.ElementDefn, String>();
      Map<org.hl7.fhir.definitions.model.ElementDefn, String> defns = new HashMap<org.hl7.fhir.definitions.model.ElementDefn, String>();
      Set<String> entryPoints = new HashSet<String>(); // if we want to finish supporting composite UML diagrams
      s.append("\r\n"+s2);
      for (String c : classes) {
        queue.add(page.getDefinitions().getElementDefn(c));
        names.put(page.getDefinitions().getElementDefn(c), c);
        
        if (c.startsWith("Resource"))
          defns.put(page.getDefinitions().getElementDefn(c), "resources-definitions.html#"+c);
        else if (c.equals("Narrative")) 
            defns.put(page.getDefinitions().getElementDefn(c), "formats-definitions.html#"+c);
        else if (c.equals("Extension")) 
            defns.put(page.getDefinitions().getElementDefn(c), "extensibility-definitions.html#"+c);
        else
          defns.put(page.getDefinitions().getElementDefn(c), "datatypes-definitions.html#"+c);
        s.append("Element <|-"+ini.getStringProperty("directions", c)+"- "+c+" << (D, #FFA500) >> \r\n"+s2);
      }
      while (queue.size() > 0) {
        org.hl7.fhir.definitions.model.ElementDefn r = queue.get(0);
        queue.remove(0);
        generateDiagramClass(r, queue, names, defns, s, s2, elementClasses, null, false, true, replacements, entryPoints);
      }  
      s.append("\r\n"+s2);
      s.append("hide methods\r\n");
      for (String en : elementClasses) {
        s.append("hide "+en+" circle\r\n");
      }
      s.append("hide Element circle\r\n");
      s.append("@enduml\r\n");
      return produceImageFromSource(title, s.toString(), s.toString()+"####"+getDefns(), page.getFolders().dstDir + title + ".png");
    } else {
      return produceImageFromSource(title, src, src+"####"+getDefns(), page.getFolders().dstDir + title + ".png");
    }
  }
  
  private String produceImageFromSource(String title, String src, String spec, String dest) throws Exception {
    // this is a time consuming step. We cache the last outcome
    String lastSpec = null;
    title = title.toLowerCase();
    if (new File(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".png").exists())
      lastSpec = TextFile.fileToString(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".plantuml-source");
    if (!spec.equals(lastSpec)) {
      TextFile.stringToFile(src, page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".plantuml-source");
      SourceStringReader rdr = new SourceStringReader(src);
      FileOutputStream png = new FileOutputStream(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".png");
      String map = rdr.generateImage(png);  
      map = processMap(map.substring(map.indexOf(")")+1).replace("name=\"plantuml_map\"", "name=\""+title+"\"").replace("id=\"plantuml_map\"", "id=\""+title+"\""));
      TextFile.stringToFile(map, page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".map");
      TextFile.stringToFile(spec, page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".plantuml-source");
    }
    Utilities.copyFile(new File(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".png"), new File(dest));
    String s = TextFile.fileToString(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+title+".map");
    return s;
  }

  private String processMap(String s) {
    for (String n : founddefinitions.keySet()) {
      s = s.replace("title=\""+n+"\"", "title=\""+Utilities.escapeXml(founddefinitions.get(n))+"\"");
    }
    return s;
  }

  public String generate(ResourceDefn resource, String n) throws Exception {
    StringBuilder s = new StringBuilder();
    StringBuilder s2 = new StringBuilder();
    s.append("@startuml\r\n");
    s.append("title "+resource.getName()+"\r\n");
    s.append("skinparam nodesep 20\r\n");
    s.append("skinparam ranksep 20\r\n");
    s.append("skinparam classBackgroundColor Aliceblue\r\n\r\n");
    s.append("skinparam classBorderColor Gray\r\n\r\n");
    s.append("skinparam classArrowColor Navy\r\n\r\n");

    String defn = resource.getName().toLowerCase()+"-definitions.html#"+resource.getName();
    
    List<org.hl7.fhir.definitions.model.ElementDefn> queue = new ArrayList<org.hl7.fhir.definitions.model.ElementDefn>();
    List<String> elementClasses = new ArrayList<String>();
    Map<String, String> replacements = new HashMap<String, String>();
    Map<org.hl7.fhir.definitions.model.ElementDefn, String> names = new HashMap<org.hl7.fhir.definitions.model.ElementDefn, String>(); 
    Set<String> entryPoints = new HashSet<String>(); // if we want to finish supporting composite UML diagrams
    Map<org.hl7.fhir.definitions.model.ElementDefn, String> defns = new HashMap<org.hl7.fhir.definitions.model.ElementDefn, String>(); 
    queue.add(resource.getRoot());
    names.put(resource.getRoot(), resource.getName());

    defns.put(resource.getRoot(), defn);
    while (queue.size() > 0) {
      org.hl7.fhir.definitions.model.ElementDefn r = queue.get(0);
      queue.remove(0);
      generateDiagramClass(r, queue, names, defns, s, s2, elementClasses, resource.getRoot(), r == resource.getRoot(), true, replacements, entryPoints);
    }  
    s.append("\r\n"+s2);
    s.append("hide methods\r\n");
    for (String en : elementClasses) {
      if (!replacements.containsKey(en))
        s.append("hide "+en+" circle\r\n");
    }
    s.append("@enduml\r\n");
    return produceImageFromSource(resource.getName(), s.toString(), s.toString() +"####"+getDefns(), page.getFolders().dstDir + n + ".png");
    
  }
  
private String getDefns() {
    StringBuilder s = new StringBuilder();
    for (String n : founddefinitions.keySet())
      s.append(n+"::"+founddefinitions.get(n)+"|||");
    return s.toString();
  }

 
  private void generateDiagramClass(org.hl7.fhir.definitions.model.ElementDefn r, List<org.hl7.fhir.definitions.model.ElementDefn> queue, 
      Map<org.hl7.fhir.definitions.model.ElementDefn, String> names, Map<org.hl7.fhir.definitions.model.ElementDefn, String> defns, 
      StringBuilder s, StringBuilder s2, List<String> elementClasses, ElementDefn root, boolean entry, boolean resource, Map<String, String> replacements, Set<String> entryPoints) throws Exception {
    String rn; 
    if (names.keySet().contains(r))
      rn = names.get(r);
//    else if (!Utilities.noString(r.getDeclaredTypeName()))
//      rn = r.getDeclaredTypeName();
    else
      rn = Utilities.capitalize(r.getName());
    String dn;
    if (defns.keySet().contains(r))
      dn = defns.get(r);
    else 
      dn = "??";
    if (r.hasStatedType() && !r.getStatedType().equals(rn)) {
      elementClasses.add(r.getStatedType());
      replacements.put(rn,r.getStatedType());
    }
    String cn = rn;
    if (replacements.containsKey(rn))
      cn = replacements.get(rn);
    
    for (org.hl7.fhir.definitions.model.ElementDefn e : r.getElements()) {
      if (e.getTypes().size() == 0 || e.typeCode().startsWith("@") || page.getDefinitions().dataTypeIsSharedInfo(e.typeCode())) {
        String n;
        org.hl7.fhir.definitions.model.ElementDefn t = null;
        if (names.keySet().contains(e))
          n = names.get(e);
        else if (page.getDefinitions().dataTypeIsSharedInfo(e.typeCode())) {
          n = e.typeCode();
          t = page.getDefinitions().getElementDefn(n);
          if (!names.containsKey(t)) {
        	  names.put(t, n);
        	  queue.add(t);
          }
        } else if (e.typeCode().startsWith("@")) {
          ElementDefn src = root.getElementForPath(e.typeCode().substring(1), page.getDefinitions(), "type cross reference");
          if (names.containsKey(src))
            n = names.get(src);      
          else {
            n = Utilities.capitalize(e.getName());
            names.put(e, n);
            if (e.isUmlBreak())
              entryPoints.add(e.typeCode().substring(1));
            else
              queue.add(e);
            defns.put(e, dn+"."+e.getName());
          }          
          if (src.hasStatedType() && !src.getStatedType().equals(n)) {
            replacements.put(n, src.getStatedType());
            elementClasses.add(src.getStatedType());
          }
        } else {
          n = Utilities.capitalize(e.getName());
          names.put(e, n);
          if (e.isUmlBreak())
            entryPoints.add(e.getName());
          else
            queue.add(e);
          defns.put(e, dn+"."+e.getName());
        }
        if (n == null)
        	n = "??";
        String ta = t != null ? "(D, #FFD700)" : "(E, Aliceblue)";
        if (e.hasStatedType() && !e.getStatedType().equals(n)) {
          replacements.put(n, e.getStatedType());
          elementClasses.add(e.getStatedType());
        }

        if (replacements.containsKey(n))
          n = replacements.get(n);
        if (page.getDefinitions().hasType(rn))
          s.append(cn+" << (D, #FFA500) >> *-"+e.getUmlDir()+"- \""+e.describeCardinality()+"\" "+n+"  << "+ta+" >> : "+e.getName()+"\r\n");
        else if (!entry)
          s.append(cn+" << (E, Aliceblue) >>  *-"+e.getUmlDir()+"- \""+e.describeCardinality()+"\" "+n+"  << "+ta+" >> : "+e.getName()+"\r\n");
        else if (resource)
          s.append(cn+" << (R, #FF7700) >> *-"+e.getUmlDir()+"- \""+e.describeCardinality()+"\" "+n+"  << "+ta+" >> : "+e.getName()+"\r\n");
        else
          s.append(cn+" << (D, #FFA500) >> *-"+e.getUmlDir()+"- \""+e.describeCardinality()+"\" "+n+"  << "+ta+" >> : "+e.getName()+"\r\n");
      }
    }
    s2.append("url of "+cn+" is [["+dn+"]]\r\n");
    founddefinitions.put(dn, r.getEnhancedDefinition());
    if (entry)
      s2.append("class "+cn+" << (R, #FF7700) >> {\r\n");
    else if (page.getDefinitions().dataTypeIsSharedInfo(r.typeCode()) || page.getDefinitions().hasType(rn)) {
      s2.append("class "+cn+" << (D, #FFD700) >> {\r\n");
      elementClasses.add(rn);
    } else {
      s2.append("class "+cn+" << (E, Aliceblue ) >> {\r\n");
      elementClasses.add(rn);
    }
    for (org.hl7.fhir.definitions.model.ElementDefn e : r.getElements()) {
      if (e.getTypes().size() > 0 && !e.typeCode().startsWith("@") && !page.getDefinitions().dataTypeIsSharedInfo(e.typeCode())) {
        String url = (dn+"."+e.getName()).replace("[", "_").replace("]", "_");
        founddefinitions.put(url, e.getEnhancedDefinition());
        s2.append(" "+e.getName()+" : "+e.typeCode()+" "+e.describeCardinality()+" [["+url+"]]\r\n");
      }
    }
    s2.append("  --\r\n}\r\n\r\n");
  }


/*
 * Candidate diagram source for Alex Henket. Retired for now
  private void generateDiagramSource(ResourceDefn resource) throws Exception {
    XMLWriter w = new XMLWriter(new FileOutputStream(page.getFolders().rootDir+"temp"+File.separator+"diagram"+File.separator+resource.getName().toLowerCase()+".diagram-source"), "UTF-8");
    w.setPretty(true);
    w.start();
    w.attribute("name", resource.getName());
    w.open("diagram");
    List<org.hl7.fhir.definitions.model.ElementDefn> queue = new ArrayList<org.hl7.fhir.definitions.model.ElementDefn>();
    Map<org.hl7.fhir.definitions.model.ElementDefn, String> names = new HashMap<org.hl7.fhir.definitions.model.ElementDefn, String>(); 
    queue.add(resource.getRoot());
    names.put(resource.getRoot(), resource.getName());
    while (queue.size() > 0) {
      org.hl7.fhir.definitions.model.ElementDefn r = queue.get(0);
      queue.remove(0);
      generateDiagramClass(r, queue, names, w, r == resource.getRoot());
    }
    w.close("diagram");
    w.close();
  }

  private void generateDiagramClass(org.hl7.fhir.definitions.model.ElementDefn r, List<org.hl7.fhir.definitions.model.ElementDefn> queue, Map<org.hl7.fhir.definitions.model.ElementDefn, String> names, XMLWriter w, boolean entry) throws Exception {
    w.attribute("name", names.get(r));
    if (entry) 
      w.attribute("entry", "yes");
    w.open("class");
    for (org.hl7.fhir.definitions.model.ElementDefn e : r.getElements()) {
      if (e.getTypes().size() > 0) {
        w.attribute("name", e.getName());
        w.attribute("type", e.typeCode());
        w.attribute("cardinality", e.describeCardinality());
        w.open("attribute");
        w.close("attribute");        
      }
    }
    for (org.hl7.fhir.definitions.model.ElementDefn e : r.getElements()) {
      if (e.getTypes().size() == 0) {
        w.attribute("name", e.getName());
        w.attribute("cardinality", e.describeCardinality());
        String n;
        if (names.keySet().contains(e))
          n = names.get(e);
        else {
          n = Utilities.capitalize(e.getName());
          names.put(e, n);
          queue.add(e);
        }
        w.attribute("target", n);
        w.open("association");
        w.close("association");
        //queue.add(e);
      }
    }
    w.close("class");
    
  }
*/
  

}
