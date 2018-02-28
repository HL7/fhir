package org.hl7.fhir.r4.utils;

import java.util.ArrayList;
import java.util.List;


public class TypesUtilities {
  
  public static List<String> wildcardTypes() {
    List<String> res = new ArrayList<String>();

    // see master list in datatypes.html
    res.add("base64Binary");
    res.add("boolean");
    res.add("canonical");
    res.add("code");
    res.add("date");
    res.add("dateTime");
    res.add("decimal");
    res.add("id");
    res.add("instant");
    res.add("integer");
    res.add("markdown");
    res.add("oid");
    res.add("positiveInt");
    res.add("string");
    res.add("time");
    res.add("unsignedInt");
    res.add("uri");
    res.add("url");
    res.add("uuid");

    res.add("Address");
    res.add("Age");
    res.add("Annotation");
    res.add("Attachment");
    res.add("CodeableConcept");
    res.add("Coding");
    res.add("ContactPoint");
    res.add("Count");
    res.add("Distance");
    res.add("Duration");
    res.add("HumanName");
    res.add("Identifier");
    res.add("Money");
    res.add("Period");
    res.add("Quantity");
    res.add("Range");
    res.add("Ratio");
    res.add("Reference");
    res.add("SampledData");
    res.add("Signature");
    res.add("Timing");
    
    res.add("Dosage");

    return res;
  }
}
