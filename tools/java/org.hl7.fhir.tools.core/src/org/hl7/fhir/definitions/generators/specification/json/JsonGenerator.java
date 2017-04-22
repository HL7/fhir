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
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.DefinedCode;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.r4.model.ValueSet;
import org.hl7.fhir.r4.model.ValueSet.ValueSetExpansionContainsComponent;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.tools.publisher.BuildWorkerContext;
import org.hl7.fhir.utilities.Utilities;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class JsonGenerator  {

	private Definitions definitions;
	private List<ElementDefn> structures = new ArrayList<ElementDefn>();
	private Map<ElementDefn, String> types = new HashMap<ElementDefn, String>();
	private List<String> typenames = new ArrayList<String>();
	private List<TypeRef> datatypes = new ArrayList<TypeRef>();
	private Map<String, ValueSet> enums = new HashMap<String, ValueSet>();
	private Map<String, String> enumDefs = new HashMap<String, String>();
  private BuildWorkerContext workerContext;

	public JsonGenerator(Definitions definitions, BuildWorkerContext workerContext, List<TypeRef> types) throws UnsupportedEncodingException {
		this.definitions = definitions;
		this.workerContext = workerContext;
		datatypes.addAll(types);
	}
  
	public JsonObject generate(ElementDefn root, String version, String genDate, JsonObject mainSchema) throws Exception {
		enums.clear();
		enumDefs.clear();

		JsonObject schema = mainSchema;
		JsonObject definitions;
		
		if (schema == null) {
			schema = new JsonObject();
			schema.addProperty("$schema", "http://json-schema.org/draft-04/schema#");
			schema.addProperty("id", "http://hl7.org/fhir/json-schema/"+root.getName());
			schema.addProperty("$ref", "#/definitions/"+root.getName().replace(".",  "_"));
			schema.addProperty("description", "see http://hl7.org/fhir/json.html#schema for information about the FHIR Json Schemas");
			definitions = new JsonObject();
			schema.add("definitions", definitions);
		} else 
			definitions = schema.getAsJsonObject("definitions");
    
		scanTypes(root, root);
		
		generateType(root, root.getName(), root, true, definitions, mainSchema != null);
		for (ElementDefn e : structures) {
			generateType(root, types.get(e), e, false, definitions, mainSchema != null);
		}

		return schema;
	}

  private JsonObject generateType(ElementDefn root, String name, ElementDefn struc, boolean isResource, JsonObject base, boolean relative) throws IOException, Exception {
    String parent = isResource ? root.typeCode() : "BackboneElement"; 

    JsonObject r = new JsonObject();
	
	name = name.replace(".",  "_");
    base.add(name, r);
	
    JsonArray ao = new JsonArray();
    r.add("allOf", ao);
	// If a root element, do not include reference
	if(!name.equals("Element")) {
		JsonObject sup = new JsonObject();
		ao.add(sup);
		// The Element is Type, Structure or blank, then it is Element
		if( (parent == null) || (parent.isEmpty()) || (parent.equals("Type")) || (parent.equals("Structure")))
			parent="Element";
		sup.addProperty("$ref", (relative ? "#" : parent.replace(".",  "_")+"#") +"/definitions/"+parent.replace(".",  "_"));
	}
    JsonObject self = new JsonObject();
    ao.add(self);
    self.addProperty("description", root.getDefinition());
    Set<String> required = new HashSet<String>();
    JsonObject props = new JsonObject();
    self.add("properties", props);

    if (isResource && definitions.hasResource(root.getName())) {
      JsonObject rt = new JsonObject();
      props.add("resourceType", rt);
      rt.addProperty("description", "This is a "+root.getName()+" resource");
      rt.addProperty("type", "string");
      JsonArray enums = new JsonArray();
      enums.add(new JsonPrimitive(root.getName()));
      rt.add("enum", enums);
      required.add("resourceType");
    }
    
		for (ElementDefn e : struc.getElements()) {
			if (e.getName().equals("[type]"))
				generateAny(root, e, "", props, relative);
			else 
				generateElement(root, e, required, props, relative);
		}
		if (required.size() > 0) {
		  JsonArray req = new JsonArray();
		  self.add("required", req);
		  for (String s : required) {
		    req.add(new JsonPrimitive(s));
		  }
		}
		return props;
	}

	private void generateAny(ElementDefn root, ElementDefn e, String prefix, JsonObject props, boolean relative) throws Exception {
		for (TypeRef t : datatypes) {
			JsonObject property = new JsonObject();
			JsonObject property_ = null;
			String en = e.getName().replace("[x]",  "");
			props.add(en+upFirst(t.getName()), property);
			property.addProperty("description", e.getDefinition());
			String tref = null;
			String type = null;
			String pattern = null;
			if (definitions.getPrimitives().containsKey(t.getName())) {
				DefinedCode def = definitions.getPrimitives().get(t.getName());
				type = def.getJsonType();
				pattern = def.getRegex();
				if (!Utilities.noString(pattern))
					property.addProperty("pattern", pattern);
				
				property.addProperty("type", type);
				property_ = new JsonObject();
				props.add("_"+en+upFirst(t.getName()), property_);
				property_.addProperty("description", "Extensions for "+en+upFirst(t.getName()));
				tref = (relative ? "#" : "Element.schema.json#") +"/definitions/Element";
				property_.addProperty("$ref", tref);
			} else {
				String tn = encodeType(e, t, true);
				tref = (relative ? "#" : tn.replace(".",  "_")+".schema.json#") +"/definitions/"+tn.replace(".",  "_");
				property.addProperty("$ref", tref);
			}
		}
	}

	private void generateElement(ElementDefn root, ElementDefn e, Set<String> required, JsonObject props, boolean relative) throws Exception {
		if (e.getTypes().size() > 1 || (e.getTypes().size() == 1 && e.getTypes().get(0).isWildcardType())) {
			if (!e.getName().contains("[x]"))
				throw new Exception("Element "+e.getName()+" in "+root.getName()+" has multiple types as a choice doesn't have a [x] in the element name");
			if (e.getTypes().size() == 1)
				generateAny(root, e, e.getName().replace("[x]", ""), props, relative);
			else {
				for (TypeRef t : e.getTypes()) {
					JsonObject property = new JsonObject();
					JsonObject property_ = null;
					String en = e.getName().replace("[x]",  "");
					props.add(en+upFirst(t.getName()), property);
					property.addProperty("description", e.getDefinition());
					String tref = null;
					String type = null;
					String pattern = null;
					if (definitions.getPrimitives().containsKey(t.getName())) {
						DefinedCode def = definitions.getPrimitives().get(t.getName());
						type = def.getJsonType();
						pattern = def.getRegex();
						if (!Utilities.noString(pattern))
							property.addProperty("pattern", pattern);
						
						property.addProperty("type", type);
						property_ = new JsonObject();
						props.add("_"+en+upFirst(t.getName()), property_);
						property_.addProperty("description", "Extensions for "+en+upFirst(t.getName()));
						tref = (relative ? "#" : "Element.schema.json#") +"/definitions/Element";
						property_.addProperty("$ref", tref);
					} else {
						String tn = encodeType(e, t, true);
						tref = (relative ? "#" : tn.replace(".",  "_")+".schema.json#") +"/definitions/"+tn.replace(".",  "_");
						property.addProperty("$ref", tref);
					}
				}
			}
		} else {
			JsonObject property = new JsonObject();
			JsonObject property_ = null;
			props.add(e.getName(), property);
			property.addProperty("description", e.getDefinition());
			String tref = null;
			String type = null;
			String pattern = null;

			if (e.usesCompositeType()/* && types.containsKey(root.getElementByName(e.typeCode().substring(1)))*/) {
				ElementDefn ref = root.getElementByName(definitions, e.typeCode().substring(1), true, false);
				String rtn = types.get(ref);
				if (rtn == null)
					throw new Exception("logic error in schema generator (null composite reference in "+types.toString()+")");
				
				if(rtn == "Type")
					rtn = "Element";
				type=rtn;
				tref = "#/definitions/"+rtn.replace(".",  "_");
			} else if (e.getTypes().size() == 0 && e.getElements().size() > 0){
				tref = "#/definitions/"+types.get(e).replace(".",  "_");
				type=types.get(e).replace(".",  "_");
			}	else if (e.getTypes().size() == 1) {
					String tn = encodeType(e, e.getTypes().get(0), true); 
					type=tn;
					if (definitions.getPrimitives().containsKey(e.typeCode())) {
						DefinedCode def = definitions.getPrimitives().get(e.typeCode());
						type = def.getJsonType();
						pattern = def.getRegex();
						property_ = new JsonObject();
						props.add("_"+e.getName(), property_);
						property_.addProperty("description", "Extensions for "+e.getName());
						tref = (relative ? "#" : "Element.schema.json#") +"/definitions/Element";
						BindingSpecification cd = e.getBinding();
							
						if (cd != null && (cd.getBinding() == BindingSpecification.BindingMethod.CodeList)) {
							ValueSet vs = cd.getValueSet();
							if (vs!= null) {
								ValueSet ex = workerContext.expandVS(vs, true, false).getValueset();
								JsonArray enums = new JsonArray();
								for (ValueSetExpansionContainsComponent cc : ex.getExpansion().getContains()) {
									enums.add(new JsonPrimitive(cc.getCode()));
								}
								property.add("enum", enums);
								pattern = null;
							}
						}
					} else {
						tref = (relative ? "#" : tn.replace(".",  "_")+".schema.json#") +"/definitions/"+tn.replace(".",  "_");
					}
			} else
				throw new Exception("how do we get here? "+e.getName()+" in "+root.getName()+" "+Integer.toString(e.getTypes().size()));

			if (e.unbounded()) {
				property.addProperty("type", "array");
				if (property_ != null) {
					property_.addProperty("type", "array");
					JsonObject items = new JsonObject();
					property.add("items", items);
					items.addProperty("type", type);
					if (!Utilities.noString(pattern))
						items.addProperty("pattern", pattern);

					items = new JsonObject();
					property_.add("items", items);
					items.addProperty("$ref", tref);
				} else { 
					JsonObject items = new JsonObject();
					property.add("items", items);
					items.addProperty("$ref", tref);
				}
			} else {
				if (property_ != null) {
					property.addProperty("type", type);
					if (!Utilities.noString(pattern))
						property.addProperty("pattern", pattern);
					
					property_.addProperty("$ref", tref);
				} else if("div".equals(e.getName()) && "xhtml".equals(type)) {
					// Is there a better type, or ref for html?
					property.addProperty("type", "string");
				} else {
					property.addProperty("$ref", tref);
				}
			}
			if (e.getMinCardinality() > 0 && property_ == null)
			  required.add(e.getName());
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
			  return "ResourceList";
			else if (definitions.getConstraints().containsKey(type.getName())) {
			  ProfiledType pt = definitions.getConstraints().get(type.getName());
			  return pt.getBaseType();
			}
			else
			  return type.getName();
		} else if (type.getParams().size() > 1)
			throw new Exception("multiple type parameters are only supported on resource");
		else  
			return type.getName()+"_"+upFirst(type.getParams().get(0));
	}

	
	
}
