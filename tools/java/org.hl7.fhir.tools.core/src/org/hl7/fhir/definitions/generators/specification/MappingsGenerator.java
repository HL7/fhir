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
package org.hl7.fhir.definitions.generators.specification;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.utilities.Utilities;

public class MappingsGenerator {

	public class Sorter implements Comparator<String> {

	  private int v(String s){
      if (ElementDefn.DICOM_MAPPING.equals(s))
        return 1;
      if (ElementDefn.XDS_MAPPING.equals(s))
        return 2;
      if (ElementDefn.PROV_MAPPING.equals(s))
        return 3;
      if (ElementDefn.vCard_MAPPING.equals(s))
        return 4;
      if (ElementDefn.RIM_MAPPING.equals(s))
        return 5;
	    if (ElementDefn.v2_MAPPING.equals(s))
	      return 6;
	    if (ElementDefn.LOINC_MAPPING.equals(s))
	      return 7;
	    if (ElementDefn.SNOMED_MAPPING.equals(s))
	      return 8;
      if (ElementDefn.CDA_MAPPING.equals(s))
        return 9;
      if (ElementDefn.iCAL_MAPPING.equals(s))
        return 10;
      if (ElementDefn.ServD_MAPPING.equals(s))
        return 11;
	    return 0;
	  }

	  @Override
    public int compare(String o1, String o2) {
      return v(o1) - v(o2);
    }

  }

  private Object preamble(String s) {
    if (ElementDefn.PROV_MAPPING.equals(s))
      return "<p>The provenance resource is based on known practices in the\r\nHL7 implementation space, particularly those found in the \r\n"+
"v2 EVN segment, the v3 ControlAct Wrapper, the CDA header, and \r\nthe IHE ATNA (<a href=\"http://www.rfc3881.net\">RFC 3881</a>).\r\n"+
"The conceptual model underlying the design is the <a href=\"http://www.w3.org/2011/prov/wiki/Main_Page\">W3C\r\nProvenance Specification</a>. Though the content and format\r\n"+
"of the resource is designed to meet specific requirements for FHIR, all the parts of the resource are formally mapped to the PROV-O\r\n"+
"specification, and FHIR resources can be transformed to their W3C\r\nPROV equivalent.</p>";
    return "";
  }
	
  String mappings;
	String mappingsList;
	
	public void generate(ResourceDefn resource) {
		StringBuilder s = new StringBuilder();
		List<String> maps = new ArrayList<String>();
		listKnownMappings(resource.getRoot(), maps);
		Collections.sort(maps, new Sorter());
		StringBuilder list = new StringBuilder();
		for (String m : maps) {
			list.append("|"+titleFor(m) + "#"+m);

			s.append("<a name=\""+m+"\"> </a><a name=\""+m.toLowerCase()+"\"> </a><h3>Mappings for "+titleFor(m)+" ("+m+")</h3>");
			s.append(preamble(m));
			s.append("<table class=\"grid\">\r\n");
			genElement(s, 0, resource.getRoot(), m, true);
			s.append("</table>\r\n");
		}
	  mappings = s.toString();
	  mappingsList = list.length() == 0 ? "" : list.toString().substring(1);
	}


  public void generate(List<ElementDefn> elements) {
		StringBuilder s = new StringBuilder();
		List<String> maps = new ArrayList<String>();
		for (ElementDefn e : elements)
			listKnownMappings(e, maps);
		Collections.sort(maps);

		StringBuilder list = new StringBuilder();
		boolean first = true;
		for (String m : maps) {
			list.append("|"+titleFor(m) + "#"+m);
      s.append("<a name=\""+m+"\"> </a>\r\n");
      s.append("<a name=\""+m.toLowerCase()+"\"> </a>\r\n");
			s.append("<h3>Mappings for "+titleFor(m)+" ("+m+")</h3>\r\n");
			s.append("<table class=\"grid\">\r\n");
			for (ElementDefn e : elements) 
				if (elementHasMapping(e, m)) {
				  genElement(s, 0, e, m, first);
				  first = false;
				}
			s.append("</table>\r\n");
		}
	  mappings = s.toString();
	  mappingsList = list.toString().substring(1);
	}
	

  public static String titleFor(String m) {
    if (m.equals(ElementDefn.RIM_MAPPING))
      return "RIM";
    if (m.equals(ElementDefn.CDA_MAPPING))
      return "CDA (R2)";
    if (m.equals(ElementDefn.v2_MAPPING))
      return "HL7 v2";
    if (m.equals(ElementDefn.DICOM_MAPPING))
      return "DICOM";
    if (m.equals(ElementDefn.vCard_MAPPING))
      return "vCard";
    if (m.equals(ElementDefn.iCAL_MAPPING))
      return "iCalendar";
    if (m.equals(ElementDefn.ServD_MAPPING))
      return "ServD";
    
    if (ElementDefn.XDS_MAPPING.equals(m))
      return "XDS";
    if (ElementDefn.PROV_MAPPING.equals(m))
      return "W3C PROV";
     if (ElementDefn.LOINC_MAPPING.equals(m))
       return "LOINC";
     if (ElementDefn.SNOMED_MAPPING.equals(m))
       return "SNOMED-CT";    
    return m.toUpperCase();
  }

  public static String idFor(String m) {
    if (m.equals(ElementDefn.RIM_MAPPING))
      return "rim";
    if (m.equals(ElementDefn.CDA_MAPPING))
      return "cda";
    if (m.equals(ElementDefn.v2_MAPPING))
      return "v2";
    if (m.equals(ElementDefn.DICOM_MAPPING))
      return "dicom";
    if (m.equals(ElementDefn.vCard_MAPPING))
      return "vcard";
    if (m.equals(ElementDefn.iCAL_MAPPING))
      return "ical";
    if (m.equals(ElementDefn.ServD_MAPPING))
      return "servd";
    
    if (ElementDefn.XDS_MAPPING.equals(m))
      return "xds";
    if (ElementDefn.PROV_MAPPING.equals(m))
      return "w3c.prov";
     if (ElementDefn.LOINC_MAPPING.equals(m))
       return "loinc";
     if (ElementDefn.SNOMED_MAPPING.equals(m))
       return "sct";    
    return m.toLowerCase();
  }

	private boolean elementHasMapping(ElementDefn e, String m) {
		if (e.getMappings().containsKey(m))
			return true;
		for (ElementDefn child : e.getElements())
			if (elementHasMapping(child, m))
				return true;
			
		return false;
	}

	private void genElement(StringBuilder s, int indent, ElementDefn elem, String m, boolean first) {
		s.append(" <tr><td>");
		if (indent == 0) {
      s.append("<a name=\""+elem.getName()+"\"> </a>");
      s.append("<a name=\""+elem.getName().toLowerCase()+"\"> </a>");
		}
		for (int i = 0; i < indent; i++) {
			s.append("&nbsp;");
			s.append("&nbsp;");
			s.append("&nbsp;");
			s.append("&nbsp;");
		}
		if (indent == 0) 
  		   s.append("<b>"+elem.getName()+"</b>");
		else
		  s.append(elem.getName());
		s.append("</td><td>"+Utilities.escapeXml(elem.getMappings().get(m)).replace("\n", "<br/>\n")+"</td></tr>\r\n");
		for (ElementDefn child :elem.getElements()) {
			genElement(s, indent+1, child, m, false);
		}
	}

	private void listKnownMappings(ElementDefn e, List<String> maps) {
		for (String s : e.getMappings().keySet())
			if (!maps.contains(s))
				maps.add(s);
		for (ElementDefn c : e.getElements())
			listKnownMappings(c,  maps);		
	}

	public String getMappings() {
		return mappings;
	}

	public String getMappingsList() {
		return mappingsList;
	}

}
