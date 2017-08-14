package org.hl7.fhir.utg.v3;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.r4.formats.XmlParser;
import org.hl7.fhir.r4.formats.IParser.OutputStyle;
import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.r4.model.DateTimeType;
import org.hl7.fhir.r4.model.StringType;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.convertors.VersionConvertor_30_40;
import org.hl7.fhir.dstu3.utils.client.FHIRToolingClient;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.utg.BaseGenerator;
import org.hl7.fhir.utilities.IniFile;
import org.hl7.fhir.utilities.Utilities;

public class CDASourceGenerator extends BaseGenerator {
  
  public CDASourceGenerator(String dest, Map<String, CodeSystem> csmap, Set<String> knownCS, String nlmUsername, String nlmPassword) {
    super(dest, csmap, knownCS);
    try {
      client = new FHIRToolingClient("https://cts.nlm.nih.gov/fhir", nlmUsername, nlmPassword);
      client.setTimeout(30000);
    } catch (URISyntaxException e) {
    }
  }

  private String cdasource;
  private IniFile cdaini;
  private FHIRToolingClient client;
  private Set<String> systems = new HashSet<String>();

  public void load(String cdaSource) {
    this.cdasource = cdaSource;
    this.cdaini = new IniFile(Utilities.changeFileExt(cdasource, ".ini"));

  }

  public void loadValueSetsSource() throws IOException, URISyntaxException, FHIRException {
    Map<String, String> oids = new HashMap<String, String>();
    listValueSets(oids);
    for (String oid : sorted(oids.keySet())) {
      String name = sanitize(oids.get(oid));
      try {
        org.hl7.fhir.dstu3.model.ValueSet vs3 = client.read(org.hl7.fhir.dstu3.model.ValueSet.class, oid);
        ValueSet vs = VersionConvertor_30_40.convertValueSet(vs3);
        for (ConceptSetComponent cmp : vs.getCompose().getInclude()) 
          checkCompose(oids.get(oid), cmp);
        for (ConceptSetComponent cmp : vs.getCompose().getExclude()) 
          checkCompose(oids.get(oid), cmp);
        new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "cda", "ccda-"+name+".xml")), vs);
//        System.out.println("fetch "+oid+": "+name+": ok");
      } catch (Exception e) {
        System.out.println("fetch "+oid+": "+name+": error : "+e.getMessage());
      }
    }
    System.out.println(Integer.toString(oids.size())+" CDA value sets saved");
    System.out.println("Unknown systems");
    for (String s : sorted(systems)) 
      if (!knownCS.contains(s)) 
        System.out.println("  "+s);
  }

  private String sanitize(String string) {
    StringBuilder b = new StringBuilder();
    boolean upper = false;
    for (char ch : string.toCharArray()) {
      if (Character.isAlphabetic(ch) || Character.isDigit(ch) || ch == '_') {
        if (upper) {
          b.append(Character.toUpperCase(ch));
          upper = false;
        } else
          b.append(ch);
      } else
        upper = true;
    }
    return b.toString();
  }

  private void checkCompose(String string, ConceptSetComponent cmp) {
    if (cmp.hasSystem()) {
      if (cmp.getSystem().startsWith("http://hl7.org/fhir/v3"))
        cmp.setSystem(cmp.getSystem().replace("http://hl7.org/fhir/v3/", "http://hl7.org/fhir/ig/vocab-poc/CodeSystem/v3-"));
      systems.add(cmp.getSystem());
    }
  }
  
  private void listValueSets(Map<String, String> oids) throws IOException {
    Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cdasource), "UTF-8"));         
    String line = br.readLine(); // skip the headers
    while ((line = br.readLine()) != null) {
      String[] cols = line.split("\\|");
      while (cols.length < 11) {
        line = line + " "+br.readLine();
        cols = line.split("\\|");
      }
      oids.put(cols[1].trim(), cols[0].trim());
    }
    br.close(); 
  }


  public void loadValueSetExpansions() throws IOException {
    Map<String, ValueSet> valueSets = new HashMap<String, ValueSet>();
    BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(cdasource), "UTF-8"));         
    String line = br.readLine(); // skip the headers
    while ((line = br.readLine()) != null) {
      String[] cols = line.split("\\|");
      while (cols.length < 11) {
        line = line + " "+br.readLine();
        cols = line.split("\\|");
      }
      String valueSetName = cols[0].trim();
      String valueSetOID = cols[1].trim();
      String definitionVersion = cols[2].trim();
      String expansionVersion = cols[3].trim();
      String purposeCF = cols[4].trim();
      String purposeDE = cols[5].trim();
      String purposeIC = cols[6].trim();
      String purposeEC = cols[7].trim();
      String code = cols[8].trim();
      String description = cols[9].trim();
      String codeSystemName = cols[10].trim();
      String codeSystemOID = cols[11].trim();
      String codeSystemVersion = cols[12].trim();
      String codeSystem = identifyOID(codeSystemOID);
      
      ValueSet vs = valueSets.get(valueSetOID);
      if (vs == null) {
        vs = new ValueSet();
        String name = Utilities.makeId(valueSetName);
        vs.setId("ccda-"+makeSafeId(name));
        vs.setUrl("http://hl7.org/fhir/ig/vocab-poc/ValueSet/"+vs.getId());
        vs.setName("CCDA"+name);
        vs.setTitle(valueSetName);
        vs.addIdentifier().setSystem("urn:ietf:rfc:3986").setValue("urn:oid:"+valueSetOID);
        vs.setVersion(definitionVersion);
        vs.setDateElement(DateTimeType.parseV3(definitionVersion));
        vs.setDescription(purposeCF);
        vs.setPurpose(purposeDE);
        if (!Utilities.noString(purposeIC))
          vs.getCompose().addInclude().addExtension(vsext("rulesDescription"), new StringType(purposeIC));
        if (!Utilities.noString(purposeEC))
          vs.getCompose().addExclude().addExtension(vsext("rulesDescription"), new StringType(purposeEC));
        valueSets.put(valueSetOID, vs);
        String uuid = cdaini.getStringProperty("uuid", vs.getId());
        if (Utilities.noString(uuid)) {
          uuid = Utilities.makeUuidUrn();
          cdaini.setStringProperty("uuid", vs.getId(), uuid, null);
        }
        vs.getExpansion().setIdentifier(uuid);
        vs.getExpansion().setTimestampElement(new DateTimeType(expansionVersion.substring(11)));
        vs.getExpansion().addParameter().setName("CCDA Version").setValue(new StringType(expansionVersion.substring(0, 10)));
      }
      vs.getExpansion().addContains().setCode(code).setDisplay(description).setSystem(codeSystem).setVersion(codeSystemVersion);      
    }
    br.close(); 
    System.out.println("");
    for (ValueSet vs : valueSets.values()) {
      new XmlParser().setOutputStyle(OutputStyle.PRETTY).compose(new FileOutputStream(Utilities.path(dest, "cda", vs.getId()+".xml")), vs);      
    }
    cdaini.save();
    System.out.println(Integer.toString(valueSets.size())+" CDA value sets saved");
  }

 
}
