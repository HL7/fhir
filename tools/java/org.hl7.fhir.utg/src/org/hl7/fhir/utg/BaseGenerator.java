package org.hl7.fhir.utg;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.r4.model.CodeSystem;
import org.hl7.fhir.utilities.Utilities;

public class BaseGenerator {
  
  protected String dest;
  protected Map<String, CodeSystem> csmap;
  protected Set<String> knownCS;
  
  
  public BaseGenerator(String dest, Map<String, CodeSystem> csmap, Set<String> knownCS) {
    super();
    this.dest = dest;
    this.csmap = csmap;
    this.knownCS = knownCS;
    knownCS.add("http://snomed.info/sct");
    knownCS.add("http://loinc.org");
    knownCS.add("http://hl7.org/fhir/sid/cvx");
    knownCS.add("http://phdsc.org/standards/payer-typology.asp");
    knownCS.add("http://www.nlm.nih.gov/research/umls/rxnorm");
    knownCS.add("http://ncimeta.nci.nih.gov");
  }

  protected List<String> sorted(Set<String> keys) {
    List<String> res = new ArrayList<String>();
    res.addAll(keys);
    Collections.sort(res);
    return res;
  }
  
  protected String csext(String name) {
    return "http://hl7.org/fhir/StructureDefinition/codeSystem-"+name;
  }

  protected String vsext(String name) {
    return "http://hl7.org/fhir/StructureDefinition/valueSet-"+name;
  }

  protected String makeSafeId(String s) {
    if (s.contains("("))
      s = s.substring(0, s.indexOf("("));
    return Utilities.makeId(s);
  }



  protected String identifyOID(String oid) {
    if (Utilities.noString(oid))
      return null;
    if ("SNOMEDCT".equals(oid))
      return "http://snomed.info/sct";
    if ("2.16.840.1.113883.6.96".equals(oid))
      return "http://snomed.info/sct";
    if ("2.16.840.1.113883.6.1".equals(oid))
      return "http://loinc.org";
    if ("2.16.840.1.113883.12.292".equals(oid))
      return "http://hl7.org/fhir/sid/cvx";
    if ("2.16.840.1.113883.3.221.5".equals(oid))
      return "http://phdsc.org/standards/payer-typology.asp";
    if ("2.16.840.1.113883.6.88".equals(oid))
      return "http://www.nlm.nih.gov/research/umls/rxnorm";
     
    if (csmap.containsKey(oid))
      return csmap.get(oid).getUrl();

    return "urn:oid:"+oid;
  }

  public String getDest() {
    return dest;
  }

  public void setDest(String dest) {
    this.dest = dest;
  }

  public Map<String, CodeSystem> getCsmap() {
    return csmap;
  }

  public void setCsmap(Map<String, CodeSystem> csmap) {
    this.csmap = csmap;
  }


}
