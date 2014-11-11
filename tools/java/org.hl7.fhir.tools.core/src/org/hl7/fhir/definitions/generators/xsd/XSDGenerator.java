package org.hl7.fhir.definitions.generators.xsd;
/*
Copyright (c) 2011+, HL7, Inc
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
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.utilities.Utilities;

public class XSDGenerator  {

  private OutputStreamWriter writer;
	private Definitions definitions;
	private List<ElementDefn> structures = new ArrayList<ElementDefn>();
	private Map<ElementDefn, String> types = new HashMap<ElementDefn, String>();
	private List<String> typenames = new ArrayList<String>();
	private List<TypeRef> datatypes = new ArrayList<TypeRef>();
	private Map<String, BindingSpecification> tx;
	private Map<String, List<DefinedCode>> enums = new HashMap<String, List<DefinedCode>>();
	private Map<String, String> enumDefs = new HashMap<String, String>();

	public XSDGenerator(OutputStreamWriter out, Definitions definitions) throws UnsupportedEncodingException {
    writer = out;
		this.definitions = definitions;
	}

  private void write(String s) throws IOException {
    writer.write(s);
  }
  
	public void setDataTypes(List<TypeRef> types) throws Exception {
		datatypes.addAll(types);
	}

	public void generate(ElementDefn root, Map<String, BindingSpecification> tx, String version, String genDate, boolean outer) throws Exception
	{
		this.tx = tx;
		enums.clear();
		enumDefs.clear();

		if (outer) {
		  write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		  write("<!-- \r\n");
		  write(Config.FULL_LICENSE_CODE);
		  write("\r\n");
		  write("  Generated on "+genDate+" for FHIR v"+version+" \r\n");
		  write("-->\r\n");
		  write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://hl7.org/fhir\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\" "+
		      "targetNamespace=\"http://hl7.org/fhir\" elementFormDefault=\"qualified\" version=\""+version+"\">\r\n");
		  write("  <xs:include schemaLocation=\"fhir-base.xsd\"/>\r\n");
		}
		write("  <xs:element name=\""+root.getName()+"\" type=\""+root.getName()+"\">\r\n");
		write("    <xs:annotation>\r\n");
		write("      <xs:documentation>"+Utilities.escapeXml(root.getDefinition())+"</xs:documentation>\r\n");
		write("    </xs:annotation>\r\n");
		write("  </xs:element>\r\n");

		scanTypes(root, root);
		
		generateType(root, root.getName(), root, true);

		for (ElementDefn e : structures) {
			generateType(root, types.get(e), e, false);
		}

		for (String en : enums.keySet()) {
			generateEnum(en);
		}
		if (outer) {
		  write("</xs:schema>\r\n");
		  writer.flush();
		}
	}

	private void generateEnum(String en) throws IOException {
		write("  <xs:simpleType name=\""+en+"-list\">\r\n");
		write("    <xs:restriction base=\"xs:string\">\r\n");
		for (DefinedCode c : enums.get(en)) {
			write("      <xs:enumeration value=\""+c.getCode()+"\">\r\n");
			write("        <xs:annotation>\r\n");
			write("          <xs:documentation>"+Utilities.escapeXml(c.getDefinition())+"</xs:documentation>\r\n");
			write("        </xs:annotation>\r\n");
			write("      </xs:enumeration>\r\n");
		}
		write("    </xs:restriction>\r\n");
		write("  </xs:simpleType>\r\n");

		write("  <xs:complexType name=\""+en+"\">\r\n");
		write("    <xs:annotation>\r\n");
		write("      <xs:documentation>"+Utilities.escapeXml(enumDefs.get(en))+"</xs:documentation>\r\n");
		write("      <xs:documentation>If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
		write("    </xs:annotation>\r\n");
		write("    <xs:complexContent>\r\n");
		write("      <xs:extension base=\"Element\">\r\n");
		write("        <xs:attribute name=\"value\" type=\""+en + "-list\" use=\"optional\"/>\r\n");
		write("      </xs:extension>\r\n");
		write("    </xs:complexContent>\r\n");
		write("  </xs:complexType>\r\n");
	}

	private void generateType(ElementDefn root, String name, ElementDefn struc, boolean isResource) throws IOException, Exception {
		write("  <xs:complexType name=\""+name+"\">\r\n");
		write("    <xs:annotation>\r\n");
		write("      <xs:documentation>"+Utilities.escapeXml(root.getDefinition())+"</xs:documentation>\r\n");
		if (isResource)
			write("      <xs:documentation>If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
		write("    </xs:annotation>\r\n");
		write("    <xs:complexContent>\r\n");
		if (isResource)
			write("      <xs:extension base=\""+root.typeCode()+"\">\r\n");
		else
			write("      <xs:extension base=\"BackboneElement\">\r\n");
		write("        <xs:sequence>\r\n");

		for (ElementDefn e : struc.getElements()) {
			if (e.getName().equals("[type]"))
				generateAny(root, e);
			else 
				generateElement(root, e);
		}
		write("        </xs:sequence>\r\n");
		write("      </xs:extension>\r\n");
		write("    </xs:complexContent>\r\n");
		write("  </xs:complexType>\r\n");
	}

	private void generateAny(ElementDefn root, ElementDefn e) throws Exception {
		write("          <xs:choice minOccurs=\""+e.getMinCardinality().toString()+"\" maxOccurs=\"1\">\r\n");
		if (e.hasDefinition()) {
			write("            <xs:annotation>\r\n");
			write("              <xs:documentation>"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
			write("            </xs:annotation>\r\n");
		}
		for (TypeRef t : datatypes) {
			if (t.isResourceReference())
				write("           <xs:element name=\"Resource\" type=\"Reference\"/>\r\n");				
			else if (t.hasParams()) {
				for (String p : t.getParams()) {
					write("           <xs:element name=\""+t.getName()+"_"+upFirst(p)+"\" type=\""+t.getName()+"_"+upFirst(p)+"\"/>\r\n");				
				}
			} else {
				write("           <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\"/>\r\n");				
			}
		}
		write("         </xs:choice>\r\n");

	}


	private void generateAny(ElementDefn root, ElementDefn e, String prefix) throws Exception {
		for (TypeRef t : definitions.getKnownTypes()) {
			if (!definitions.getInfrastructure().containsKey(t.getName()) && !definitions.getConstraints().containsKey(t.getName())) {
			  String en = prefix != null ? prefix + upFirst(t.getName()) : t.getName();
			  //write("       <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\"/>\r\n");        
  	    write("            <xs:element name=\""+en+"\" type=\""+t.getName()+"\"/>\r\n");
			}
		}
	}

	private void generateElement(ElementDefn root, ElementDefn e) throws Exception {
		write("          ");
		if (e.getTypes().size() > 1 || (e.getTypes().size() == 1 && e.getTypes().get(0).isWildcardType())) {
			if (!e.getName().contains("[x]"))
				throw new Exception("Element "+e.getName()+" in "+root.getName()+" has multiple types as a choice doesn't have a [x] in the element name");
			write("<xs:choice minOccurs=\""+e.getMinCardinality().toString()+"\" maxOccurs=\""+(e.unbounded() ? "unbounded" : "1")+"\" ");
			write(">\r\n");
			if (e.hasDefinition()) {
				write("            <xs:annotation>\r\n");
				write("              <xs:documentation>"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
				write("            </xs:annotation>\r\n");
			}
			if (e.getTypes().size() == 1)
				generateAny(root, e, e.getName().replace("[x]", ""));
			else
				for (TypeRef t : e.getTypes()) {
					String tn = encodeType(e, t, true);
					String n = e.getName().replace("[x]", tn.toUpperCase().substring(0, 1) + tn.substring(1));
					if (t.getName().equals("Reference"))
 	          n = e.getName().replace("[x]", "Reference");
  			  write("            <xs:element name=\""+n+"\" type=\""+encodeType(e, t, true)+"\"/>\r\n");
				}
			write("          </xs:choice>\r\n");
		} else {
			String tn = null;
			if ("extension".equals(e.getName()))
				write("<xs:element name=\""+e.getName()+"\" type=\"Extension\" ");
			else if (e.usesCompositeType()/* && types.containsKey(root.getElementByName(e.typeCode().substring(1)))*/) {
				ElementDefn ref = root.getElementByName(e.typeCode().substring(1));
				String rtn = types.get(ref);
				if (rtn == null)
				  throw new Exception("logic error in schema generator (null composite reference in "+types.toString()+")");
				write("<xs:element name=\""+e.getName()+"\" type=\""+rtn+"\" ");
			} else if (e.getTypes().size() == 0 && e.getElements().size() > 0){
				write("<xs:element name=\""+e.getName()+"\" type=\""+types.get(e)+"\" ");
			}	else if (e.getTypes().size() == 1) {
			  write("<xs:element name=\""+e.getName()+"\" ");
			  tn = encodeType(e, e.getTypes().get(0), true);
			  if (tn.equals("Narrative") && e.getName().equals("text") && root.getElements().contains(e)) 
			    write("type=\""+tn+"\" ");
			} else
				throw new Exception("how do we get here? "+e.getName()+" in "+root.getName()+" "+Integer.toString(e.getTypes().size()));

			write("minOccurs=\""+e.getMinCardinality().toString()+"\"");
			if (e.unbounded())
				write(" maxOccurs=\"unbounded\"");
			else
				write(" maxOccurs=\"1\"");

			if (tn != null && !(tn.equals("Narrative") && e.getName().equals("text") && root.getElements().contains(e))) 
				write(" type=\""+tn+"\"");

			write(">\r\n");
			if (e.hasDefinition()) {
				write("            <xs:annotation>\r\n");
				write("              <xs:documentation>"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
				write("           </xs:annotation>\r\n");
			}
			write("          </xs:element>\r\n");
		}
	}

	private void scanTypes(ElementDefn root, ElementDefn focus) {
	  for (ElementDefn e : focus.getElements()) {
	    if (e.getTypes().size() == 0 && e.getElements().size() > 0) {
        int i = 0;
        String tn = root.getName()+"."+upFirst(e.getName())+ (i == 0 ? "" : Integer.toString(i));
        while (typenames.contains(tn)) {
          i++;
          tn = root.getName()+"."+upFirst(e.getName())+ (i == 0 ? "" : Integer.toString(i));
        }
        structures.add(e);
        typenames.add(tn);
        types.put(e, tn);
        scanTypes(root, e);
	    }
	  }
	}

	private String upFirst(String name) {
		return name.toUpperCase().charAt(0)+name.substring(1);
	}

	private String encodeType(ElementDefn e, TypeRef type, boolean params) throws Exception {
		if (type.isResourceReference())
			return "Reference";
		else if (type.getName().equals("code")) {
			String en = null;
			if (e.hasBinding()) {
				BindingSpecification cd = getConceptDomainByName(tx, e.getBindingName());
				if (cd != null && cd.getBinding() == BindingSpecification.Binding.CodeList ) {
					en = cd.getName();
					if (!definitions.getCommonBindings().contains(cd) && cd.getUseContexts().size() <= 1) {
						enums.put(en, cd.getCodes());
						enumDefs.put(en, cd.getDefinition());
					}
					return en;
				}
			}
			return "code";

		} else if (!type.hasParams() || !params) {
			if (type.getName().equals("Resource"))
			  return "ResourceContainer";
			else
			  return type.getName();
		} else if (type.getParams().size() > 1)
			throw new Exception("multiple type parameters are only supported on resource");
		else  
			return type.getName()+"_"+upFirst(type.getParams().get(0));
	}

	private BindingSpecification getConceptDomainByName(Map<String, BindingSpecification> tx, String conceptDomain) throws Exception {		
		for (BindingSpecification cd : tx.values()) {
			if (cd.getName().equals(conceptDomain))
				return cd; 
		}
		return null;
	}

  public OutputStreamWriter getWriter() {
    return writer;
  }
	
	
}
