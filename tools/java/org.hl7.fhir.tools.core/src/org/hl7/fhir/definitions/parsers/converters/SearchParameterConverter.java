package org.hl7.fhir.definitions.parsers.converters;

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
import java.util.Set;

import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.SearchParameter;
import org.hl7.fhir.definitions.ecore.fhir.SearchType;
import org.hl7.fhir.utilities.Utilities;

public class SearchParameterConverter {
  public static List<SearchParameter> buildSearchParametersFromFhirModel(org.hl7.fhir.definitions.model.ResourceDefn resource, Collection<org.hl7.fhir.definitions.model.SearchParameterDefn> searchParameters) {
    List<SearchParameter> result = new ArrayList<SearchParameter>();

    for (org.hl7.fhir.definitions.model.SearchParameterDefn searchParameter : searchParameters) {
      result.add(buildSearchParameterFromFhirModel(resource, searchParameter));
    }

    return result;
  }

  public static SearchParameter buildSearchParameterFromFhirModel(org.hl7.fhir.definitions.model.ResourceDefn resource, org.hl7.fhir.definitions.model.SearchParameterDefn searchParameter) {
    SearchParameter result = FhirFactory.eINSTANCE.createSearchParameter();

    result.setName(searchParameter.getCode());
    result.setDescription(Utilities.cleanupTextString(searchParameter.getDescription()));
    result.setType(SearchType.get(searchParameter.getType().ordinal()));
    
    if( result.getType() == SearchType.COMPOSITE )
      result.getComposite().addAll(searchParameter.getComposites());
    else
      result.getPath().addAll(searchParameter.getPaths());
    
    // Read in the Resource Targets
    Set<String> t = searchParameter.getWorkingTargets();
    if( result.getType() == SearchType.REFERENCE ) {
      for (String rn : t) {
        if (!rn.equals("Any")) 
	        result.getTarget().add(rn);
      }
  	  if (result.getTarget().size() == 0){
    		// Need to grab the types that are included on the actual types from path definition
    		// And add those types here (as long as it isn't any)
  	    String ep = searchParameter.getPaths().get(0);
  	    org.hl7.fhir.definitions.model.ElementDefn el = resource.getRoot().getElementByName(ep);
  	    if (el != null){
  	      org.hl7.fhir.definitions.model.TypeRef tr = el.getTypes().get(0);
  	      if (!tr.isAnyReference() && tr.isResourceReference()){
  	        tr.getParams();
  	        for (String rn : tr.getParams()) {
  	          if (!rn.equals("Any")) 
  	            result.getTarget().add(rn);
  	        }
  	      }
  	    }
  	  }
    }
    
    return result;
  }
}
