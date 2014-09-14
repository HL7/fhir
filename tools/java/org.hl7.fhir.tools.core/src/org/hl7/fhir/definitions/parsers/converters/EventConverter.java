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

import org.hl7.fhir.definitions.ecore.fhir.EventDefn;
import org.hl7.fhir.definitions.ecore.fhir.EventUsage;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.utilities.Utilities;


public class EventConverter 
{
	public static List<EventDefn> buildEventsFromFhirModel( Collection<org.hl7.fhir.definitions.model.EventDefn> events )
	{
		List<EventDefn> result = new ArrayList<EventDefn>();
		
	    for (org.hl7.fhir.definitions.model.EventDefn event : events) 
	    {
    		result.add(buildEventFromFhirModel(event));
	    }
	    
	    return result;
	}
	
	
	public static EventDefn buildEventFromFhirModel( org.hl7.fhir.definitions.model.EventDefn event )
	{
		EventDefn result = FhirFactory.eINSTANCE.createEventDefn();
		
		result.setCode( event.getCode() );
		result.setDefinition( Utilities.cleanupTextString(event.getDefinition()) );
		result.getFollowUps().addAll( event.getFollowUps() );
		
		for( org.hl7.fhir.definitions.model.EventUsage usage : event.getUsages() )
		{
			EventUsage newUsage = FhirFactory.eINSTANCE.createEventUsage();
			
			newUsage.setNotes( Utilities.cleanupTextString(usage.getNotes()) );
			newUsage.getRequestResources().addAll( usage.getRequestResources() );
			newUsage.getRequestAggregations().addAll( usage.getRequestAggregations() );
			newUsage.getResponseResources().addAll( usage.getResponseResources() );
			newUsage.getResponseAggregations().addAll( usage.getResponseAggregations() );
			
			result.getUsage().add(newUsage);
		}
		
		return result;
	}
}
