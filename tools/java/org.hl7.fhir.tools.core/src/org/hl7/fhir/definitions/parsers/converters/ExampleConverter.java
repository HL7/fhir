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

import org.hl7.fhir.definitions.ecore.fhir.Example;
import org.hl7.fhir.definitions.ecore.fhir.ExampleType;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.utilities.Utilities;


public class ExampleConverter 
{
	public static List<Example> buildExamplesFromFhirModel( Collection<org.hl7.fhir.definitions.model.Example> examples )
	{
		List<Example> result = new ArrayList<Example>();
		
	    for (org.hl7.fhir.definitions.model.Example example : examples) 
	    {
    		result.add(buildExampleFromFhirModel(example));
	    }
	    
	    return result;
	}
	
	
	public static Example buildExampleFromFhirModel( org.hl7.fhir.definitions.model.Example example )
	{
		Example result = FhirFactory.eINSTANCE.createExample();
		
		result.setName( example.getName() );
		result.setIdentity( example.getId() );
		result.setType( ExampleType.get( example.getType().ordinal() ) );
		result.setDescription( Utilities.cleanupTextString(example.getDescription()) );
		result.setPath( example.getPath().getName() );
		
		return result;
	}
}
