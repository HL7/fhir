package org.hl7.fhir.definitions.generators.specification.json;
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
import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.dstu3.model.CodeSystem;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.dstu3.model.CodeSystem.ConceptDefinitionDesignationComponent;
import org.hl7.fhir.dstu3.model.ValueSet;
import org.hl7.fhir.dstu3.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class JsonGenerator  {

  private OutputStreamWriter writer;
	private Definitions definitions;
	private List<ElementDefn> structures = new ArrayList<ElementDefn>();
	private Map<ElementDefn, String> types = new HashMap<ElementDefn, String>();
	private List<String> typenames = new ArrayList<String>();
	private List<TypeRef> datatypes = new ArrayList<TypeRef>();
	private Map<String, ValueSet> enums = new HashMap<String, ValueSet>();
	private Map<String, String> enumDefs = new HashMap<String, String>();
  private BuildWorkerContext workerContext;

	public JsonGenerator(OutputStreamWriter out, Definitions definitions, BuildWorkerContext workerContext) throws UnsupportedEncodingException {
    writer = out;
		this.definitions = definitions;
		this.workerContext = workerContext;
	}

  private void write(String s) throws IOException {
//    writer.write(s);
  }
  
	public void setDataTypes(List<TypeRef> types) throws Exception {
		datatypes.addAll(types);
	}

	public void generate(ElementDefn root, String version, String genDate, boolean outer) throws Exception {
		enums.clear();
		enumDefs.clear();

		JsonObject schema = new JsonObject();
    schema.addProperty("$schema", "http://json-schema.org/draft-04/schema#");
    schema.addProperty("id", "http://hl7.org/fhir/json-schema/"+root.getName());
    schema.addProperty("$ref", "#/definitions/"+root.getName());
    schema.addProperty("description", "see http://hl7.org/fhir/json.html#schema for information about the FHIR Json Schemas");
    JsonObject definitions = new JsonObject();
    schema.add("definitions", definitions);
    
		scanTypes(root, root);
		
		generateType(root, root.getName(), root, true, definitions);

		for (ElementDefn e : structures) {
			generateType(root, types.get(e), e, false, definitions);
		}

//		for (String en : enums.keySet()) {
//			generateEnum(en);
//		}
		if (outer) {
		  Gson gson = new GsonBuilder().setPrettyPrinting().create();
		  String json = gson.toJson(schema);
	    writer.write(json);
		  writer.flush();
		} 
	}

	private void generateEnum(String en) throws IOException {
		write("  <xs:simpleType name=\""+en+"-list\">\r\n");
		write("    <xs:restriction base=\"xs:string\">\r\n");
		ValueSet vs = enums.get(en);
		ValueSet ex = workerContext.expandVS(vs, true).getValueset();
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

  private void generateType(ElementDefn root, String name, ElementDefn struc, boolean isResource, JsonObject base) throws IOException, Exception {
    String parent = isResource ? root.typeCode() : "BackboneElement"; 

    JsonObject r = new JsonObject();
    base.add(name, r);
    JsonArray ao = new JsonArray();
    r.add("allOf", ao);
    JsonObject sup = new JsonObject();
    ao.add(sup);
    sup.addProperty("$ref", parent+"/definitions/"+parent);
    JsonObject self = new JsonObject();
    ao.add(self);
    self.addProperty("description", root.getDefinition());
    Set<String> required = new HashSet<String>();
    JsonObject props = new JsonObject();
    self.add("properties", props);
    
		for (ElementDefn e : struc.getElements()) {
			if (e.getName().equals("[type]"))
				generateAny(root, e, required, props);
			else 
				generateElement(root, e, required, props);
		}
//		write("        </xs:sequence>\r\n");
//		write("      </xs:extension>\r\n");
//		write("    </xs:complexContent>\r\n");
//		write("  </xs:complexType>\r\n");
	}

	private void generateAny(ElementDefn root, ElementDefn e, Set<String> required, JsonObject props) throws Exception {
	  JsonObject property = new JsonObject();
    props.add(e.getName(), property);
	  property.addProperty("type", "[any]");
		for (TypeRef t : datatypes) {
//			if (t.isResourceReference()) {
//				write("           <xs:element name=\"Resource\" type=\"Reference\""+close+"\r\n");				
//			} else {
//				write("           <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\""+close+"\r\n");				
//			}
//	    if (forCodeGeneration) {
//        write("            <xs:annotation>\r\n");
//	      if (e.hasDefinition()) {
//	        write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of the elements, but only one)</xs:documentation>\r\n");
//	      } else {
//          write("              <xs:documentation xml:lang=\"en\">(choose any one of the elements, but only one)</xs:documentation>\r\n");	        
//	      }
//        write("            </xs:annotation>\r\n");
//        write("           </xs:element>\r\n");       
//	    }
		}
	}


	private void generateAny(ElementDefn root, ElementDefn e, String prefix, String close) throws Exception {
		for (TypeRef t : definitions.getKnownTypes()) {
			if (!definitions.getInfrastructure().containsKey(t.getName()) && !definitions.getConstraints().containsKey(t.getName())) {
			  String en = prefix != null ? prefix + upFirst(t.getName()) : t.getName();
			  //write("       <xs:element name=\""+t.getName()+"\" type=\""+t.getName()+"\"/>\r\n");        
  	    write("            <xs:element name=\""+en+"\" type=\""+t.getName()+"\""+close+"\r\n");
//        if (forCodeGeneration) {
//          write("              <xs:annotation>\r\n");
//          if (e.hasDefinition()) {
//            write("                <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of "+prefix+"*, but only one)</xs:documentation>\r\n");
//          } else {
//            write("                <xs:documentation xml:lang=\"en\">(choose any one of "+prefix+"*, but only one)</xs:documentation>\r\n");         
//          }
//          write("              </xs:annotation>\r\n");
//          write("             </xs:element>\r\n");       
//        }
			}
		}
	}

	private void generateElement(ElementDefn root, ElementDefn e, Set<String> required, JsonObject props) throws Exception {
		if (e.getTypes().size() > 1 || (e.getTypes().size() == 1 && e.getTypes().get(0).isWildcardType())) {
//			if (!e.getName().contains("[x]"))
//				throw new Exception("Element "+e.getName()+" in "+root.getName()+" has multiple types as a choice doesn't have a [x] in the element name");
//	    String close = " minOccurs=\"0\">";
//	    if (!forCodeGeneration) {
//	      write("<xs:choice minOccurs=\""+e.getMinCardinality().toString()+"\" maxOccurs=\""+(e.unbounded() ? "unbounded" : "1")+"\" ");
//	      write(">\r\n");
//	      if (e.hasDefinition()) {
//	        write("            <xs:annotation>\r\n");
//	        write("              <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+"</xs:documentation>\r\n");
//	        write("            </xs:annotation>\r\n");
//	      }
//	      close = "/>";
//	    }
//			if (e.getTypes().size() == 1)
//				generateAny(root, e, e.getName().replace("[x]", ""), close);
//			else
//				for (TypeRef t : e.getTypes()) {
//					String tn = encodeType(e, t, true);
//					String n = e.getName().replace("[x]", nameForType(tn));
//					if (t.getName().equals("Reference"))
// 	          n = e.getName().replace("[x]", "Reference");
//  			  write("            <xs:element name=\""+n+"\" type=\""+encodeType(e, t, true)+"\""+close+"\r\n");
//          if (forCodeGeneration) {
//            write("              <xs:annotation>\r\n");
//            if (e.hasDefinition()) {
//              write("                <xs:documentation xml:lang=\"en\">"+Utilities.escapeXml(e.getDefinition())+" (choose any one of "+e.getName().replace("[x]", "")+"*, but only one)</xs:documentation>\r\n");
//            } else {
//              write("                <xs:documentation xml:lang=\"en\">(choose any one of "+e.getName().replace("[x]", "")+"*, but only one)</xs:documentation>\r\n");         
//            }
//            write("              </xs:annotation>\r\n");
//            write("             </xs:element>\r\n");       
//          }
//				}
//	    if (!forCodeGeneration) {
//  			write("          </xs:choice>\r\n");
//	    }
		} else {
	    JsonObject property = new JsonObject();
	    props.add(e.getName(), property);
	    property.addProperty("description", e.getDefinition());
      String tref;

			if ("extension".equals(e.getName())) {
				tref = "Extension.schema.json/definitions/Extension";
			} else if (e.usesCompositeType()/* && types.containsKey(root.getElementByName(e.typeCode().substring(1)))*/) {
				ElementDefn ref = root.getElementByName(definitions, e.typeCode().substring(1), true, false);
				String rtn = types.get(ref);
				if (rtn == null)
				  throw new Exception("logic error in schema generator (null composite reference in "+types.toString()+")");
				tref = "#definitions/"+rtn;
			} else if (e.getTypes().size() == 0 && e.getElements().size() > 0){
			  tref = "#definitions/"+types.get(e);
			}	else if (e.getTypes().size() == 1) {
        String tn = encodeType(e, e.getTypes().get(0), true);
        tref = tn+".schema.json/definitions/"+tn;
			} else
				throw new Exception("how do we get here? "+e.getName()+" in "+root.getName()+" "+Integer.toString(e.getTypes().size()));

      if (e.unbounded()) {
        property.addProperty("type", "array");
        JsonObject items = new JsonObject();
        property.add("items", items);
        items.addProperty("$ref", tref);
      } else
        property.addProperty("$ref", tref);
			
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

	private String encodeType(ElementDefn e, TypeRef type, boolean params) throws Exception {
		if (type.isResourceReference())
			return "Reference";
		else if (type.getName().equals("code")) {
			String en = null;
			if (e.hasBinding()) {
				BindingSpecification cd = e.getBinding();
				if (cd != null && cd.getBinding() == BindingSpecification.BindingMethod.CodeList) {
					en = cd.getValueSet().getName();
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
			else
			  return type.getName();
		} else if (type.getParams().size() > 1)
			throw new Exception("multiple type parameters are only supported on resource");
		else  
			return type.getName()+"_"+upFirst(type.getParams().get(0));
	}

  public OutputStreamWriter getWriter() {
    return writer;
  }
	
	
}
