package org.hl7.fhir.definitions.model;

/*-
 * #%L
 * org.hl7.fhir.publisher.core
 * %%
 * Copyright (C) 2014 - 2019 Health Level 7
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */


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
import java.util.Collection;
import java.util.List;

import org.hl7.fhir.r5.model.ElementDefinition.ReferenceVersionRules;
import org.hl7.fhir.utilities.Utilities;



/*
 * Syntax for type declarations
 * 
 * typeSpec = '@' elementreference | '[param]' | 'xhtml' | 'xml:ID' | 'xml:lang'
 * 'Interval(' orderedType ')' | 'Reference(' resourceParams ')' | Resource | type
 * ('|' type)* | '*'
 * 
 * resourceParams = resourceType ('|' resourceType)* | Any 
 * type = primitiveType | dataType | structure
 * 
 */

public class TypeRef {

  private String name;
	private String profile;
	private List<String> params = new ArrayList<String>();
	private List<String> aggregations = new ArrayList<String>();
	private ReferenceVersionRules versioning;
  private List<String> patterns;

	public TypeRef()
	{
	}
	
	public TypeRef(String name)
	{
		this.name = name;
	}
	
	public String getName() {
		return name;
	}

	public TypeRef setName(String name) {
		this.name = name;
		return this;
	}

	public List<String> getParams() {
		return params;
	}

	public boolean hasParams() {
		return params.size() > 0;
	}

	public List<String> getAggregations() {
		return aggregations;
	}

	public boolean hasAggregations() {
		return aggregations.size() > 0;
	}

	
	private String resolvedTypeName;
	
	public String getResolvedTypeName()
	{
		return resolvedTypeName;
	}
	
	public void setResolvedTypeName(String value)
	{
		resolvedTypeName = value;
	}
	
	
	public String getProfile() {
    return profile;
  }

  public void setProfile(String profile) {
    this.profile = profile;
  }

  public boolean isUnboundGenericParam() {
		return name.equals("[param]");
	}

	public boolean isXhtml() {
		return name.equalsIgnoreCase("xhtml");
	}

	public boolean isXmlLang()
	{
		return name.equalsIgnoreCase("xml:lang");
	}
	
	public boolean isWildcardType() {
		return name.equals("*");
	}
	
  public boolean isResourceReference()
  {
    // When the type is Reference(X), this is a resource reference
    return name.equals("Reference") && !params.isEmpty();
  }
  
  public boolean isCodeableReference()
  {
    // When the type is Reference(X), this is a resource reference
    return name.equals("CodeableReference") && !params.isEmpty();
  }
  
  public boolean isCanonical()
  {
    // When the type is Reference(X), this is a resource reference
    return name.equals("canonical") && !params.isEmpty();
  }
  
	public boolean isContainedReference()
	{
		// When the type is Resource, it's a contained resource
		return name.equals("Reference") && params.isEmpty();
	}
	

	public boolean isElementReference()
	{
		return name.startsWith("@");
	}
	
	public boolean isBoundGeneric()
	{
		return params.size() > 0;
	}
	
	public boolean isAnyReference()
	{
		return  isResourceReference() && 
				hasParams() && 
				getParams().size() == 1 &&
				getParams().get(0).equals(ANY_RESOURCE_GENERIC_ARG);
	}

	private boolean isExtension() 
	{
	    return name.equals("Extension");
	}


	public final static String ANY_RESOURCE_GENERIC_ARG = "Any";
	
	public boolean isSpecialType() {
		return isXhtml() || isUnboundGenericParam() || isXmlLang() 
				|| isWildcardType() || name.equals("Type") || name.equals("Narrative") || name.equals("Resource") || name.equals("ResourceBase")
				|| name.equals("SharedDefinition") || isResourceReference() || name.equals("Structure") || name.equals("OperationOutcome") ||
				isContainedReference() || isExtension();
	}

  public String summary() {
		String s = name;
		if (hasParams()) {
			s = s + "(";
			for (String p : params)
				s = s + p + ',';
			s = s.substring(0, s.length() - 1) + ')';
		}
		return s;
	}

	public String summaryFormal() {
		String s = name;
		if (hasParams()) {
			s = s + "(";
			for (String p : params)
				s = s + p + '|';
			s = s.substring(0, s.length() - 1) + ')';
		}
		return s;
	}

	public static boolean isFhirPrimitiveType(String tn) {
		return tn.equals("boolean") || tn.equals("integer")
				|| tn.equals("decimal") || tn.equals("base64Binary")
				|| tn.equals("instant") || tn.equals("string")
				|| tn.equals("uri") || tn.equals("code") || tn.equals("oid")
				|| tn.equals("uuid") || tn.equals("sid") || tn.equals("id")
				|| tn.equals("date") || tn.equals("dateTime");
	}

  public boolean hasProfile() {
    return !Utilities.noString(profile);
  }

  public ReferenceVersionRules getVersioning() {
    return versioning;
  }

  public void setVersioning(ReferenceVersionRules versioning) {
    this.versioning = versioning;
  }

  @Override
  public String toString() {
    return summary();
  }
  
  public List<String> getPatterns() {
    return patterns;
  }

  public void setPatterns(List<String> patterns) {
    this.patterns = patterns;
  }

//  public Collection<? extends String> compare(String path, TypeRef that) {
//    List<String> result = new ArrayList<>();
//    if (Definitions.existsThat(that, path, result)) {
//      Definitions.compareField(this.name, that.name, path, "name", result);
//      Definitions.compareField(this.profile, that.profile, path, "profile", result);
//      Definitions.compareField(this.params, that.params, path, "params", result);
//      Definitions.compareField(this.aggregations, that.aggregations, path, "aggregations", result);
//      Definitions.compareField(this.versioning, that.versioning, path, "versioning", result);
//      Definitions.compareField(this.patterns, that.patterns, path, "patterns", result);
//    }
//    return result;  
//  }
//
//  
  
}
