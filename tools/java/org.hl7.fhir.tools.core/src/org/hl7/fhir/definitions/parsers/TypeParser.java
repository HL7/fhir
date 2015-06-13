package org.hl7.fhir.definitions.parsers;
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

import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.utils.NameResolver;

public class TypeParser {


	public List<TypeRef> parse(String n, boolean inProfile, String profileExtensionBase, NameResolver resolver) throws Exception {
		ArrayList<TypeRef> a = new ArrayList<TypeRef>();

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
			String typeString = typeList[i];
			if (typeString.contains("<")) {
				if (!inProfile) {
					throw new Exception("Can't specify aggregation mode for types unless defining a profile");
				}
				int startPos = typeString.indexOf("<");
				int endPos = typeString.indexOf(">");
				if (endPos < startPos) {
					throw new Exception("Missing '>' in data type definition: " + typeList[i]);
				}
				t.getAggregations().addAll(Arrays.asList(typeString.substring(startPos + 1, endPos).trim().split(",")));
					
				typeString = typeString.substring(0, startPos);
			}
			
			if (typeString.contains("{")) {
				if (!inProfile) {
					throw new Exception("Can't specify profile for types unless defining a profile");
				}
				int startPos = typeString.indexOf("{");
				int endPos = typeString.indexOf("}");
				if (endPos < startPos) {
					throw new Exception("Missing '}' in data type definition: " + typeList[i]);
				}
				String pt = typeString.substring(startPos + 1, endPos).trim();
				if (pt.startsWith("#"))
				  pt = profileExtensionBase + pt.substring(1);
				t.setProfile(pt);
				typeString = typeString.substring(0, startPos);
			}
			
			if (typeString.contains("(")) {
				int startPos = typeString.indexOf("(");
				int endPos = typeString.indexOf(")");
				if (endPos < startPos) {
					throw new Exception("Missing ')' in data type definition: " + typeList[i]);
				}
				String[] params = typeString.substring(startPos + 1, endPos).split(",");
				for (int j=0;j<params.length;j++) {
	        if (typeString.startsWith("Reference("))
	          if (inProfile && !resolver.isResource(params[j].trim()) && !"Any".equals(params[j].trim()))
	            throw new Exception("Unknown resource "+params[j].trim());
					t.getParams().add(params[j].trim());
				}
				typeString = typeString.substring(0, startPos);
			}
			
			t.setName(typeString.trim());
			a.add(t);
		}

		return a;
	}

}
