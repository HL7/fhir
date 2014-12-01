package org.hl7.fhir.definitions.generators.specification;

public class GeneratorUtils {

  public static String getSrcFile(String name, boolean definitions) throws Exception {
  	if (name == null)
  		throw new Exception("unknown null type");
  	if (name.equals("Attachment"))
  		return "datatypes";
  	if (name.equals("Identifier"))
  		return "datatypes";
  	if (name.equals("Period"))
  		return "datatypes";
  	if (name.equals("Timing"))
  		return "datatypes";
  	if (name.equals("Range"))
  		return "datatypes";
  	if (name.equals("HumanId"))
  		return "datatypes";
  	if (name.equals("ContactPoint"))
  		return "datatypes";
    if (name.equals("CodeableConcept"))
      return "datatypes";
    if (name.equals("Quantity"))
      return "datatypes";
    if (name.equals("SampledData"))
      return "datatypes";
  	if (name.equals("Ratio"))
  		return "datatypes";
  	if (name.equals("Age"))
  		return "datatypes";
  	if (name.equals("HumanName"))
  		return "datatypes";
  	if (name.equals("Coding"))
  		return "datatypes";
  	if (name.equals("Choice"))
  		return "datatypes";
  	if (name.equals("Address"))
  		return "datatypes";
  	if (name.equals("boolean"))
  		return "datatypes";
  	if (name.equals("integer"))
  		return "datatypes";
  	if (name.equals("decimal"))
  		return "datatypes";
  	if (name.equals("instant"))
  		return "datatypes";
  	if (name.equals("base64Binary"))
  		return "datatypes";
  	if (name.equals("dateTime"))
  		return "datatypes";
  	if (name.equals("string"))
  		return "datatypes";
  	if (name.equals("uri"))
  		return "datatypes";
  	if (name.equals("code"))
  		return "datatypes";
  	if (name.equals("oid"))
  		return "datatypes";
  	if (name.equals("uuid"))
  		return "datatypes";
  	if (name.equals("sid"))
  		return "datatypes";
  	if (name.equals("id"))
  		return "datatypes";
  	if (name.equals("Duration"))
  		return "datatypes";
  	if (name.equals("date"))
  		return "datatypes";
  	if (name.equals("time"))
  		return "datatypes";
  	if (name.equals("Money"))
  		return "datatypes";
    if (name.equals("narrative"))
      return "narrative";
    if (name.equalsIgnoreCase("xhtml"))
      return "narrative";
  	if (name.equals("Narrative"))
  		return "narrative";
    if (name.equals("Extension"))
      return "extensibility";
    if (name.equals("Resource"))
      return "resource";
    if (name.equals("Reference") && definitions)
      return "references";
    if (name.equals("Reference"))
      return "references";
    if (name.equals("Binary"))
      return "extras";
    if (name.equals("Any"))
      return "resourcelist";
    if (name.equals("*"))
      return "datatypes";
  	if (name.equals("resourceType"))
  		return "terminologies";
  	return name.toLowerCase();
  
  }

}
