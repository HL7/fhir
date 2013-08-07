package org.hl7.fhir.definitions.generators.specification;
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
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextStreamWriter;
import org.hl7.fhir.utilities.Utilities;

public class SchematronGenerator  extends TextStreamWriter {
			
	private Logger logger;

  public SchematronGenerator(OutputStream out, Logger logger) throws UnsupportedEncodingException {
    super(out);
    this.logger = logger;
  }

	public void generate(Definitions definitions) throws Exception
	{
    ln("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
    ln_i("<sch:schema xmlns:sch=\"http://purl.oclc.org/dsdl/schematron\" queryBinding=\"xslt2\">");
//    ln_i("<sch:schema xmlns:sch=\"http://purl.oclc.org/dsdl/schematron\">");
    ln("<sch:ns prefix=\"f\" uri=\"http://hl7.org/fhir\"/>");
    ln("<sch:ns prefix=\"a\" uri=\"http://www.w3.org/2005/Atom\"/>");
    ln("<sch:ns prefix=\"h\" uri=\"http://www.w3.org/1999/xhtml\"/>");
    for (ResourceDefn root : definitions.getResources().values()) {
      ln_i("<sch:pattern>");
      ln("<sch:title>"+root.getName()+"</sch:title>");

      ArrayList<String> l = new ArrayList<String>();
      generateInvariants("/a:feed/a:entry/a:content", root.getRoot(), definitions, l);
      ln_o("  </sch:pattern>");
    }
    ln_o("</sch:schema>");
    flush();
    close();

	}

	public void generate(ElementDefn root, Definitions definitions) throws Exception	{
		ln("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		ln_i("<sch:schema xmlns:sch=\"http://purl.oclc.org/dsdl/schematron\" queryBinding=\"xslt2\">");
		ln("<sch:ns prefix=\"f\" uri=\"http://hl7.org/fhir\"/>");
    ln("<sch:ns prefix=\"a\" uri=\"http://www.w3.org/2005/Atom\"/>");
    ln("<sch:ns prefix=\"h\" uri=\"http://www.w3.org/1999/xhtml\"/>");
		ln_i("<sch:pattern>");
		ln("<sch:title>"+root.getName()+"</sch:title>");

		ArrayList<String> parents = new ArrayList<String>();
    generateInvariants("", root, definitions, parents);
		ln_o("</sch:pattern>");
		ln_o("</sch:schema>");
		flush();
		close();
	}

	private ElementDefn getType(TypeRef tr, Definitions definitions) throws Exception {
    String tn = tr.getName();
    if (tn.equals("Resource"))
      tn = "ResourceReference";
    if (definitions.getPrimitives().containsKey(tn) || isSpecialType(tn) || tn.contains("@") || tn.equals("xml:lang")) 
      return null;
    
    if (definitions.getConstraints().containsKey(tn)) 
      return definitions.getElementDefn(definitions.getConstraints().get(tn).getComment());
    else
      return definitions.getElementDefn(tn);    
	}
	
	private void genChildren(String path, String typeCode, ElementDefn ed, Definitions definitions, List<String> parents) throws Exception {
	  if (!path.contains("//")) {
	    ArrayList<String> l = new ArrayList<String>(parents);
	    l.add(typeCode);

	    for (ElementDefn cd : ed.getElements()) {
	      if (!Utilities.noString(cd.typeCode()) && l.contains(cd.typeCode())) {
	        // well, we've recursed. What's going to happen now is that we're going to write this as // because we're going to keep recursing.
	        // the next call will write this rule, and then terminate
	        generateInvariants(path+"/", cd, definitions, l);
	      } else
	        generateInvariants(path, cd, definitions, l);
	    }
	  }
	}
	
	private void generateInvariants(String path, ElementDefn ed, Definitions definitions, List<String> parents) throws Exception {
	  //logger.log("generate: "+path+" ("+parents.toString()+")");
	  String name = ed.getName();
	  if (name.contains("("))
	    name = name.substring(0, name.indexOf("("));
    if (ed.getElements().size() > 0) {
	    path = path + "/f:"+name;
	    genInvs(path, ed);
	    genChildren(path, null, ed, definitions, parents);
	  } else {
	    for (TypeRef tr : ed.typeCode().equals("*") ? allTypes() : ed.getTypes()) {
	      String en = name.replace("[x]", Utilities.capitalize(tr.summary()));
	      if (en.contains("("))
	        en = en.substring(0, en.indexOf("("));
	      String sPath = path + "/f:"+en;
	      genInvs(sPath, ed);
	      ElementDefn td = getType(tr, definitions);
	      if (td != null) {
	        genInvs(sPath, td);
	        genChildren(sPath, tr.summary(), td, definitions, parents);
	      }
	    }
	  }
	}

  private List<TypeRef> allTypes() {
    return new ArrayList<TypeRef>();
  }

  private void genInvs(String path, ElementDefn ed) throws Exception {
    
    int c = 0;
    for (Invariant inv : ed.getInvariants().values()) {
      if (inv.getFixedName() == null || path.endsWith(inv.getFixedName()))
        c++;
    }
    if (c > 0) {
	    ln_i("<sch:rule context=\""+path+"\">");
	    for (Invariant inv : ed.getInvariants().values()) {
	      if (inv.getFixedName() == null || path.endsWith(inv.getFixedName())) {
	        if (inv.getXpath().contains("&lt;") || inv.getXpath().contains("&gt;"))
	          throw new Exception("error in xpath - do not escape xml characters in the xpath in the excel spreadsheet");
	        ln("<sch:assert test=\""+Utilities.escapeXml(inv.getXpath().replace("\"", "'"))+"\">Inv-"+inv.getId()+": "+inv.getEnglish()+"</sch:assert>");
	      }
	    }
      ln_o("</sch:rule>");
      //"/f:"+root.getName()
	  }
  }

  private boolean isSpecialType(String tn) {
    return tn.equals("idref") || tn.equals("xhtml");
  }

//	private void generateElement(String name, ElementDefn e) throws IOException {
//		writeEntry(name+"."+e.getName(), e.getMinCardinality(), e.getMaxCardinality(), e.getTypes(), e.getBindingName(), e);
//		for (ElementDefn c : e.getElements())	{
//		   generateElement(name+"."+e.getName(), c);
//		}
//		
//	}
//
//	private void writeEntry(String path, Integer min, Integer max, List<TypeRef> types, String conceptDomain, ElementDefn e) throws IOException {
//		write("      <elementDefinition>\r\n");
//		write("        <name>"+Utilities.escapeXml(path)+"</name>\r\n");
//		write("        <cardinality>\r\n");
//		write("          <minOccurs>"+min.toString()+"</minOccurs>\r\n");
//		if (max == null)
//			write("          <maxOccurs>unbounded</maxOccurs>\r\n");
//		else
//			write("          <maxOccurs>"+max.toString()+"</maxOccurs>\r\n");
//		write("        </cardinality>\r\n");
//		write("        <condition>"+Utilities.escapeXml(e.getCondition())+"</condition>\r\n");
//		if (types != null && types.size() > 0)
//		{ 
//			write("        <types>\r\n");
//			for (TypeRef t : types)
//			{
//				if (t.hasParams()) {
//					write("          <type>"+t.getName()+"(");
//		            boolean firstp = true;
//		            for (String p : t.getParams()) {
//		            	if (!firstp)
//		            		write("|");
//		            	write(p);
//		            	firstp = false;
//		            }
//					write(")</type>\r\n");
//				}
//				else
//					write("          <type>"+t.getName()+"</type>\r\n");
//			}
//			
//			write("        </types>\r\n");
//		}
//		if (hasValue(conceptDomain))
//			write("        <conceptDomain>"+Utilities.escapeXml(conceptDomain)+"</conceptDomain>\r\n");
//		write("        <mustUnderstand>"+Boolean.toString(e.isMustUnderstand())+"</mustUnderstand>\r\n");
//		write("        <definition>"+Utilities.escapeXml(e.getDefinition())+"</definition>\r\n");
//		write("        <requirements>"+Utilities.escapeXml(e.getRequirements())+"</requirements>\r\n");
//		if (hasValue(e.getComments()))
//			write("        <comments>"+Utilities.escapeXml(e.getComments())+"</comments>\r\n");
//		write("        <rim>"+Utilities.escapeXml(e.getRimMapping())+"</rim>\r\n");
//		if (hasValue(e.getV2Mapping()))
//			write("        <v2>"+Utilities.escapeXml(e.getV2Mapping())+"</v2>\r\n");
//		if (hasValue(e.getTodo()))
//			write("        <todo>"+Utilities.escapeXml(e.getTodo())+"</todo>\r\n");
//		write("      </elementDefinition>\r\n");
//		
//	}
//	
//	
//	private boolean hasValue(String s) {
//		return s != null && !"".equals(s);
//	}
//
//	public void setConceptDomains(Map<String, BindingSpecification> conceptDomains) {
//		this.conceptDomains = conceptDomains;
//		
//	}
}
