package org.hl7.fhir.utg;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.exceptions.FHIRFormatError;
import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.model.Resource;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.utg.v2.V2CsvExporter;
import org.hl7.fhir.utg.v3.V3MifExporter;
import org.hl7.fhir.utilities.Utilities;

public class UTGExporter {

  public static void main(String[] args) throws Exception {
    String source = args[0]; // the vocabulary repository to load
    String v2dest = args[1]; // where to generate v2 output
    String v3dest = args[2]; // MIF File to generate
    new UTGExporter(source, v2dest, v3dest).execute();
  }

  private V2CsvExporter v2;
  private V3MifExporter v3;
  private String source;
  private Map<String, CodeSystem> codeSystems = new HashMap<String, CodeSystem>();
  private Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
  
  public UTGExporter(String source, String v2dest, String v3dest) throws IOException {
    this.source = source;
    v2 = new V2CsvExporter(v2dest);
    v3 = new V3MifExporter(v2dest);
  }

  private void execute() throws FHIRFormatError, IOException {
    loadEverything();
    v2.generate(codeSystems, valueSets);
  }

  private void loadEverything() throws FHIRFormatError, IOException {
    System.out.println("Loading Vocab from "+source);
//    loadVocab("fhir");
    loadVocab("v2");
    loadVocab("v3");
//    loadVocab("cda");
//    loadVocab("cimi");
  }

  private void loadVocab(String folder) throws IOException, FHIRFormatError {
    int count = 0;
    System.out.println(folder+"...");
    File dir = new File(Utilities.path(source, folder));
    for (File f : dir.listFiles())
      if (f.getName().endsWith(".xml")) {
        count++;
        loadFile(f);
      }
    dir = new File(Utilities.path(source, folder, "codeSystems"));
    if (dir.exists())
      for (File f : dir.listFiles())
        if (f.getName().endsWith(".xml")) { 
          count++;
          loadFile(f);
        }
    dir = new File(Utilities.path(source, folder, "valueSets"));
    if (dir.exists())
      for (File f : new File(Utilities.path(source, folder,"valueSets")).listFiles())
        if (f.getName().endsWith(".xml")) {
          count++;
          loadFile(f);
        }
    System.out.println(Integer.toString(count)+" resources loaded");    
  }

  private void loadFile(File f) throws FHIRFormatError, FileNotFoundException, IOException {
    Resource r = new XmlParser().parse(new FileInputStream(f));
    if (r instanceof CodeSystem) {
      CodeSystem cs = (CodeSystem) r;
      codeSystems.put(cs.getUrl(), cs);
    }
    if (r instanceof ValueSet) {
      ValueSet vs = (ValueSet) r;
      valueSets.put(vs.getUrl(), vs);
    }
    
  }
}
