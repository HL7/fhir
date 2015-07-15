package org.hl7.fhir.definitions.validation;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;

public class SpellChecker {
  private Set<String> words = new HashSet<String>();
  private Set<String> additional = new HashSet<String>();
  private String addFile;

  public SpellChecker(String srcFolder, Definitions definitions) throws IOException {
    addFile = Utilities.path(srcFolder, "spelling", "add.txt");
    loadWords(srcFolder);
    loadFromDefinitions(definitions);
  }


  private void loadFromDefinitions(Definitions definitions) {
    for (ResourceDefn r : definitions.getResources().values()) {
      loadFromElement(r.getRoot());
    }
    for (TypeDefn t : definitions.getTypes().values())
      loadFromElement(t);
    for (TypeDefn t : definitions.getInfrastructure().values())
      loadFromElement(t);
    for (TypeDefn t : definitions.getStructures().values())
      loadFromElement(t);
  }


  private void loadFromElement(ElementDefn ed) {
    words.add(ed.getName().toLowerCase());
    for (String s : ed.getAliases())
      words.add(s.toLowerCase());
    for (ElementDefn c : ed.getElements()) {
      loadFromElement(c);
    }
  }


  private void loadWords(String srcFolder) throws IOException {
    loadDict(Utilities.path(srcFolder, "spelling", "english.dic"));  
    loadDict(Utilities.path(srcFolder, "spelling", "center.dic"));  
    loadDict(Utilities.path(srcFolder, "spelling", "color.dic"));  
    loadDict(Utilities.path(srcFolder, "spelling", "labeled.dic"));  
    loadDict(Utilities.path(srcFolder, "spelling", "yze.dic"));  
} 

  private void loadDict(String path) throws IOException {
    //Construct BufferedReader from InputStreamReader
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(path)));
   
    String line = null;
    while ((line = br.readLine()) != null) {
      words.add(line);
    }
   
    br.close();    
  }


  public boolean ok(String w) {
    w = w.toLowerCase();
    if (words.contains(w))
      return true;
    additional.add(w);
    return false;
   }


  public void close() throws Exception {
    StringBuilder b = new StringBuilder();
    for (String s : additional)
      b.append(s+"\r\n");
    TextFile.stringToFile(b.toString(), addFile);
    
  }

}
