package org.hl7.fhir.igtools.spreadsheets;
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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.hl7.fhir.dstu3.context.BaseWorkerContext;
import org.hl7.fhir.dstu3.context.IWorkerContext;
import org.hl7.fhir.dstu3.model.ElementDefinition;
import org.hl7.fhir.dstu3.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition;
import org.hl7.fhir.dstu3.utils.ToolingExtensions;
import org.hl7.fhir.utilities.Utilities;

public class TypeParser {


  public List<TypeRef> parse(String n, boolean inProfile, String profileExtensionBase, BaseWorkerContext resolver, boolean allowElement) throws Exception {
    return parse(n, inProfile, profileExtensionBase, resolver, allowElement, null);
  }

  public List<TypeRef> parse(String n, boolean inProfile, String profileExtensionBase, BaseWorkerContext resolver, boolean allowElement, String sheetName) throws Exception {
    ArrayList<TypeRef> a = new ArrayList<TypeRef>();

    String exceptionPrefix = sheetName==null ? "":"Error parsing sheet " + sheetName + " - ";

    if (n == null || n.equals("") || n.startsWith("!"))
      return a;

    // We use "|" as a separator both between types as well as to separate resources when
    // we reference a Resource.  This step converts the separators inside a resource reference
    // to allow splitting
    if (n.indexOf("(") != -1 && n.indexOf("|") != -1) {
      String[] typeParts = n.split("[\\(\\)]");
      n = "";
      for (int i=0;i<typeParts.length;i++) {
        n = n + typeParts[i++];
        if (i<typeParts.length) {
          n = n + "(" + typeParts[i].replace("|",",") + ")";
        }
      }
    }

    String[] typeList = n.split("[\\|]");
    for (int i=0; i<typeList.length; i++) {
      TypeRef t = new TypeRef();
      String typeString = typeList[i].trim();
      if (typeString.contains("<")) {
        if (!inProfile) {
          throw new Exception(exceptionPrefix + "Can't specify aggregation mode for types unless defining a profile: "+typeString);
        }
        int startPos = typeString.indexOf("<");
        int endPos = typeString.indexOf(">");
        if (endPos < startPos) {
          throw new Exception(exceptionPrefix + "Missing '>' in data type definition: " + typeList[i]);
        }
        t.getAggregations().addAll(Arrays.asList(typeString.substring(startPos + 1, endPos).trim().split(",")));

        typeString = typeString.substring(0, startPos);
      }

      if (typeString.contains("{")) {
        if (!inProfile) {
          throw new Exception(exceptionPrefix + "Can't specify profile for types unless defining a profile");
        }
        int startPos = typeString.indexOf("{");
        int endPos = typeString.indexOf("}");
        if (endPos < startPos) {
          throw new Exception(exceptionPrefix + "Missing '}' in data type definition: " + typeList[i]);
        }
        String pt = typeString.substring(startPos + 1, endPos).trim();
        typeString = typeString.substring(0, startPos);
        if (pt.startsWith("#")) {
          // what to do here depends on what it refers to 
          if (typeString.equals("Extension"))
            pt = profileExtensionBase + pt.substring(1);
          else if (typeString.startsWith("Reference"))
            pt = pt.substring(1).toLowerCase();
          else
            throw new Exception(exceptionPrefix + "Unhandled case");				    
        }
        t.setProfile(pt);
      }

      if (typeString.contains("(")) {
        int startPos = typeString.indexOf("(");
        int endPos = typeString.indexOf(")");
        if (endPos < startPos) {
          throw new Exception(exceptionPrefix + "Missing ')' in data type definition: " + typeList[i]);
        }
        String[] params = typeString.substring(startPos + 1, endPos).split(",");
        for (int j=0;j<params.length;j++) {
          if (typeString.startsWith("Reference("))
            if (inProfile && !resolver.getResourceNames().contains(params[j].trim()) && !"Any".equals(params[j].trim()))
              throw new Exception(exceptionPrefix + "Unknown resource "+params[j].trim());
          t.getParams().add(params[j].trim());
        }
        typeString = typeString.substring(0, startPos);
      }

      t.setName(typeString.trim());
      if (t.getName().equals("Element") && !allowElement)
        throw new Exception(exceptionPrefix + "The type 'Element' is illegal in this context");
      a.add(t);
    }

    return a;
  }

  public List<TypeRefComponent> convert(IWorkerContext context, String path, List<TypeRef> types, boolean resource, ElementDefinition ed) throws Exception {
    List<TypeRefComponent> list = new ArrayList<TypeRefComponent>();
    for (TypeRef t : types) {
      // Expand any Resource(A|B|C) references
      if(t.hasParams() && !"Reference".equals(t.getName())) {
        throw new Exception("Only resource references can specify parameters.  Path " + path);
      }
      if (t.getParams().size() > 0) {
        if (t.getProfile() != null && t.getParams().size() !=1) {
          throw new Exception("Cannot declare profile on a resource reference declaring multiple resource types.  Path " + path);
        }
        if (t.getProfile() != null) {
          TypeRefComponent childType = new TypeRefComponent();
          childType.setCode(t.getName());
          childType.setProfile(t.getProfile());
          list.add(childType);
        } else          
          for(String param : t.getParams()) {
            TypeRefComponent childType = new TypeRefComponent();
            childType.setCode(t.getName());
            childType.setProfile("http://hl7.org/fhir/StructureDefinition/"+param);
            list.add(childType);
          }
      } else if (t.isWildcardType()) {
        // this list is filled out manually because it may be running before the types referred to have been loaded
        for (String n : wildcardTypes()) 
          list.add(new TypeRefComponent().setCode(n));
      } else if (Utilities.noString(t.getName()) && t.getProfile() != null) {
        TypeRefComponent tc = new TypeRefComponent();
        tc.setProfile(t.getProfile());
        StructureDefinition sd = context.fetchResource(StructureDefinition.class, t.getProfile());
        if (sd != null)
          tc.setCode(sd.getType());
        list.add(tc);
      } else if (t.getName().startsWith("=")){
        if (resource)
          list.add(new TypeRefComponent().setCode("BackboneElement"));
        else
          list.add(new TypeRefComponent().setCode("Element"));
        ToolingExtensions.addStringExtension(ed, "http://hl7.org/fhir/StructureDefinition/structuredefinition-explicit-type-name", t.getName().substring(1));
      } else {
        StructureDefinition sd = context.fetchResource(StructureDefinition.class, "http://hl7.org/fhir/StructureDefinition/"+t.getName());
        if (sd == null)
          throw new Exception("Unknown type '"+t.getName()+"'");
        TypeRefComponent tc = new TypeRefComponent().setCode(sd.getType());
        list.add(tc);
        tc.setProfile(t.getProfile());
      }
    }    
    return list;
  }

  public static List<String> wildcardTypes() {
    List<String> res = new ArrayList<String>();
    res.add("base64Binary");
    res.add("boolean");
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

    res.add("Meta");
    return res;
  }

}
