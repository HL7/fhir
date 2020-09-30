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
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.r5.utils.TypesUtilities;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;

public class XSDGenerator  {

  private boolean forCodeGeneration; 
  private OutputStreamWriter writer;
	private Definitions definitions;
	private List<ElementDefn> structures = new ArrayList<ElementDefn>();
	private Map<ElementDefn, String> types = new HashMap<ElementDefn, String>();
	private List<String> typenames = new ArrayList<String>();
	private List<TypeRef> datatypes = new ArrayList<TypeRef>();
	private Map<String, ValueSet> enums = new HashMap<String, ValueSet>();
	private Map<String, String> enumDefs = new HashMap<String, String>();
  private BuildWorkerContext workerContext;
  private Set<String> allenums = new HashSet<String>();

	public XSDGenerator(OutputStreamWriter out, Definitions definitions, boolean forCodeGeneration, BuildWorkerContext workerContext, Set<String> allenums) throws UnsupportedEncodingException {
    writer = out;
		this.definitions = definitions;
		this.forCodeGeneration = forCodeGeneration;
		this.workerContext = workerContext;
		if (allenums != null)
	    this.allenums = allenums;
	}

  private void write(String s) throws IOException {
    writer.write(s);
  }
  
	public void setDataTypes(List<TypeRef> types) throws Exception {
		datatypes.addAll(types);
	}

	public void generate(ElementDefn root, String version, String genDate, boolean outer) throws Exception {
		enums.clear();
		enumDefs.clear();

		if (outer) {
		  write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\r\n");
		  write("<!-- \r\n");
		  write(Config.FULL_LICENSE_CODE);
		  write("\r\n");
		  write("  Generated on "+genDate+" for FHIR v"+version+" \r\n");
      write("\r\n");
      write("  Note: the schemas &amp; schematrons do not contain all of the rules about what makes resources\r\n");
      write("  valid. Implementers will still need to be familiar with the content of the specification and with\r\n");
      write("  any profiles that apply to the resources in order to make a conformant implementation.\r\n");
      write("\r\n");
		  write("-->\r\n");
		  write("<xs:schema xmlns:xs=\"http://www.w3.org/2001/XMLSchema\" xmlns=\"http://hl7.org/fhir\" xmlns:xhtml=\"http://www.w3.org/1999/xhtml\" "+
		      "targetNamespace=\"http://hl7.org/fhir\" elementFormDefault=\"qualified\" version=\"1.0\">\r\n");
		  write("  <xs:include schemaLocation=\"fhir-base.xsd\"/>\r\n");
		}
		write("  <xs:element name=\""+root.getName()+"\" type=\""+root.getName()+"\">\r\n");
		write("    <xs:annotation>\r\n");
		write("      <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(root.getDefinition())+"</xs:documentation>\r\n");
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
	  if (allenums.contains(en)) 
	    return;
	  allenums.add(en);
	  write("  <xs:simpleType name=\""+en+"-list\">\r\n");
	  write("    <xs:restriction base=\"code-primitive\">\r\n");
	  ValueSet vs = enums.get(en);
	  vs.setUserData(ToolResourceUtilities.NAME_VS_USE_MARKER, true);
	  ValueSet ex = workerContext.expandVS(vs, true, false).getValueset();
	  for (ValueSetExpansionContainsComponent cc : ex.getExpansion().getContains()) {
	    genIncludedCode(cc);
	  }

	  write("    </xs:restriction>\r\n");
	  write("  </xs:simpleType>\r\n");

	  write("  <xs:complexType name=\""+en+"\">\r\n");
	  write("    <xs:annotation>\r\n");
	  write("      <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(enumDefs.get(en))+"</xs:documentation>\r\n");
	  write("      <xs:documentation xml:lang=\"en\">If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
	  write("    </xs:annotation>\r\n");
	  write("    <xs:complexContent>\r\n");
	  write("      <xs:extension base=\"Element\">\r\n");
	  write("        <xs:attribute name=\"value\" type=\""+en + "-list\" use=\"optional\"/>\r\n");
	  write("      </xs:extension>\r\n");
	  write("    </xs:complexContent>\r\n");
	  write("  </xs:complexType>\r\n");
	}

	private void genIncludedCode(ValueSetExpansionContainsComponent cc) throws IOException {
	  write("      <xs:enumeration value=\"" + Utilities.escapeXml(cc.getCode()) + "\">\r\n");
	  write("        <xs:annotation>\r\n");
	  write("          <xs:documentation xml:lang=\"en\">" + Utilities.escapeXml(cc.getDisplay()) + "</xs:documentation>\r\n"); // todo: do we need to look the definition up?
	  CodeSystem cs = workerContext.fetchCodeSystem(cc.getSystem());
	  if (cs != null && cc.hasCode()) {
	    ConceptDefinitionComponent c = getCodeDefinition(cc.getCode(), cs.getConcept());
	    if (c != null)
	      for (ConceptDefinitionDesignationComponent l : c.getDesignation())
	        if (l.hasLanguage())
	          write("          <xs:documentation xml:lang=\""+l.getLanguage()+"\">"+Utilities.escapeXml(l.getValue())+"</xs:documentation>\r\n");
	  }
	  write("        </xs:annotation>\r\n");
	  write("      </xs:enumeration>\r\n");
	}


  private ConceptDefinitionComponent getCodeDefinition(String code, List<ConceptDefinitionComponent> list) {
    for (ConceptDefinitionComponent cc : list) {
      if (code.equals(cc.getCode())) {
        return cc;
      }
      ConceptDefinitionComponent t = getCodeDefinition(code, cc.getConcept());
      if (t != null)
        return t;
    }
    return null;
  }

  private void generateType(ElementDefn root, String name, ElementDefn struc, boolean isResource) throws IOException, Exception {
		write("  <xs:complexType name=\""+name+"\">\r\n");
		write("    <xs:annotation>\r\n");
		write("      <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(root.getDefinition())+"</xs:documentation>\r\n");
		if (isResource)
			write("      <xs:documentation xml:lang=\"en\">If the element is present, it must have either a @value, an @id, or extensions</xs:documentation>\r\n");
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
	  String close = " minOccurs=\"0\">";
	  if (!forCodeGeneration) {
	    write("          <xs:choice minOccurs=\""+e.getMinCardinality().toString()+"\" maxOccurs=\"1\">\r\n");
	    if (e.hasDefinition()) {
	      write("            <xs:annotation>\r\n");
	      write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
	      write("            </xs:annotation>\r\n");
	    }
	    close = "/>";
	  } 
		for (TypeRef t : datatypes) {
			if (t.isResourceReference()) {
				write("           <xs:element name=\"Resource\" type=\"Reference\""+close+"\r\n");				
			} else {
				write("           <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\""+close+"\r\n");				
			}
	    if (forCodeGeneration) {
        write("            <xs:annotation>\r\n");
	      if (e.hasDefinition()) {
	        write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of the elements, but only one)</xs:documentation>\r\n");
	      } else {
          write("              <xs:documentation xml:lang=\"en\">(choose any one of the elements, but only one)</xs:documentation>\r\n");	        
	      }
        write("            </xs:annotation>\r\n");
        write("           </xs:element>\r\n");       
	    }
		}
    if (!forCodeGeneration) 
      write("         </xs:choice>\r\n");
	}


	private void generateAny(ElementDefn root, ElementDefn e, String prefix, String close) throws Exception {
		for (String t : TypesUtilities.wildcardTypes()) {
			if (!definitions.getInfrastructure().containsKey(t) && !definitions.getConstraints().containsKey(t)) {
			  String en = prefix != null ? prefix + upFirst(t) : t;
			  //write("       <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\"/>\r\n");        
  	    write("            <xs:element name=\""+en+"\" type=\""+t+"\""+close+"\r\n");
        if (forCodeGeneration) {
          write("              <xs:annotation>\r\n");
          if (e.hasDefinition()) {
            write("                <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of "+prefix+"*, but only one)</xs:documentation>\r\n");
          } else {
            write("                <xs:documentation xml:lang=\"en\">(choose any one of "+prefix+"*, but only one)</xs:documentation>\r\n");         
          }
          write("              </xs:annotation>\r\n");
          write("             </xs:element>\r\n");       
        }
			}
		}
	}

	private void generateElement(ElementDefn root, ElementDefn e) throws Exception {
		write("          ");
		if (e.getTypes().size() > 1 || (e.getTypes().size() == 1 && e.getTypes().get(0).isWildcardType())) {
			if (!e.getName().contains("[x]"))
				throw new Exception("Element "+e.getName()+" in "+root.getName()+" has multiple types as a choice doesn't have a [x] in the element name");
	    String close = " minOccurs=\"0\">";
	    if (!forCodeGeneration) {
	      write("<xs:choice minOccurs=\""+e.getMinCardinality().toString()+"\" maxOccurs=\""+(e.unbounded() ? "unbounded" : "1")+"\" ");
	      write(">\r\n");
	      if (e.hasDefinition()) {
	        write("            <xs:annotation>\r\n");
	        write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
	        write("            </xs:annotation>\r\n");
	      }
	      close = "/>";
	    }
			if (e.getTypes().size() == 1)
				generateAny(root, e, e.getName().replace("[x]", ""), close);
			else
				for (TypeRef t : e.getTypes()) {
					String tn = encodeType(e, t, true);
					String n = e.getName().replace("[x]", nameForType(tn));
					if (t.getName().equals("Reference"))
 	          n = e.getName().replace("[x]", "Reference");
  			  write("            <xs:element name=\""+n+"\" type=\""+encodeType(e, t, true)+"\""+close+"\r\n");
          if (forCodeGeneration) {
            write("              <xs:annotation>\r\n");
            if (e.hasDefinition()) {
              write("                <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of "+e.getName().replace("[x]", "")+"*, but only one)</xs:documentation>\r\n");
            } else {
              write("                <xs:documentation xml:lang=\"en\">(choose any one of "+e.getName().replace("[x]", "")+"*, but only one)</xs:documentation>\r\n");         
            }
            write("              </xs:annotation>\r\n");
            write("             </xs:element>\r\n");       
          }
				}
	    if (!forCodeGeneration) {
  			write("          </xs:choice>\r\n");
	    }
		} else {
			String tn = null;
			if ("extension".equals(e.getName()))
				write("<xs:element name=\""+e.getName()+"\" type=\"Extension\" ");
			else if (e.usesCompositeType()/* && types.containsKey(root.getElementByName(e.typeCode().substring(1)))*/) {
				ElementDefn ref = root.getElementByName(definitions, e.typeCode().substring(1), true, false, null);
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

			if (tn != null && !(tn.equals("Narrative") && e.getName().equals("text") && root.getElements().contains(e))) { 
				write(" type=\""+tn+"\"");
			}

			write(">\r\n");
			if (e.hasDefinition()) {
				write("            <xs:annotation>\r\n");
				write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
				write("           </xs:annotation>\r\n");
			}
			write("          </xs:element>\r\n");
			if (tn != null && !(tn.equals("Narrative") && e.getName().equals("text") && root.getElements().contains(e))) { 
			  if (tn.equals("FHIRDefinedType")) 
			    enums.put("FHIRDefinedType", definitions.getValuesets().get("http://hl7.org/fhir/ValueSet/defined-types"));
			  else if  (tn.equals("FHIRAllTypes")) 
          enums.put("FHIRAllTypes", definitions.getValuesets().get("http://hl7.org/fhir/ValueSet/all-types"));
			}
		}
	}

	private CharSequence nameForType(String type) {
	  if (definitions.getConstraints().containsKey(type))
      return definitions.getConstraints().get(type).getBaseType();
    else 
      return Utilities.capitalize(type);
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


  protected boolean isEnum(BindingSpecification cd) {
    boolean ok = cd.getBinding() == (BindingSpecification.BindingMethod.CodeList) || (cd.getStrength() == BindingStrength.REQUIRED && cd.getBinding() == BindingMethod.ValueSet);
    if (ok) {
      if (cd.getValueSet() != null && cd.getValueSet().hasCompose() && cd.getValueSet().getCompose().getInclude().size() == 1) {
        ConceptSetComponent inc = cd.getValueSet().getCompose().getIncludeFirstRep();
        if (inc.hasSystem() && !inc.hasFilter() && !inc.hasConcept() && !inc.getSystem().startsWith("http://hl7.org/fhir")) {
          ok = false;
        }
        if (inc.hasSystem()) {
          CodeSystem cs = workerContext.fetchCodeSystem(inc.getSystem());
          if (cs != null && cs.getCompositional()) {
            ok = false;
          }
        }
      }
    }
    return ok;
  }
  
	private String encodeType(ElementDefn e, TypeRef type, boolean params) throws Exception {
    if (type.isResourceReference())
      return "Reference";
    else if (type.isCanonical())
      return "canonical";
    else if (type.isCodeableReference())
      return "CodeableReference";
		else if (type.getName().equals("code")) {
			String en = null;
			if (e.hasBinding()) {
				BindingSpecification cd = e.getBinding();
				if (cd != null && isEnum(cd)) {
				  if (cd.getValueSet() == null) {
				    throw new Error("no value for "+cd.getUri());
				  }
					en = namify(cd.getValueSet().getName());
					if (!cd.isShared()) {
					  enums.put(en, cd.getValueSet());
					  enumDefs.put(en, cd.getDefinition());
					}
					return en;
				}
			}
			return "code";

		} else if (!type.hasParams() || !params) {
			if (type.getName().equals("Resource"))
			  return "ResourceContainer";
			else if (definitions.getConstraints().containsKey(type.getName())) {
			  ProfiledType pt = definitions.getConstraints().get(type.getName());
			  return pt.getBaseType();
			} else
			  return type.getName();
		} else if (type.getParams().size() > 1)
			throw new Exception("multiple type parameters are only supported on resource");
		else  
			return type.getName()+"_"+upFirst(type.getParams().get(0));
	}

  private String namify(String name) {
    StringBuilder b = new StringBuilder();
    boolean ws = false;
    for (char c : name.toCharArray()) {
      if ((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z')) {
        if (ws) {
          ws = false;
          b.append(Character.toUpperCase(c));
        } else 
          b.append(c);          
      } else 
        ws = true;        
    }
    return b.toString();
  }

  public OutputStreamWriter getWriter() {
    return writer;
  }
	
	
}
