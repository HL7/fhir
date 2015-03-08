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

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.instance.model.ElementDefinition.BindingStrength;
import org.hl7.fhir.utilities.Utilities;


public class BindingConverter 
{
	public static List<BindingDefn> buildBindingsFromFhirModel( 
			Collection<org.hl7.fhir.definitions.model.BindingSpecification> bindings, CompositeTypeDefn parent )
	{
		List<BindingDefn> result = new ArrayList<BindingDefn>();
		
	    for (org.hl7.fhir.definitions.model.BindingSpecification binding : bindings) 
	    {
	    	if( !binding.getName().equals("*unbound*") )
	    	{
	    		result.add(buildBindingFromFhirModel(binding, parent));
	    	}
	    }
	    
	    return result;
	}
	
	public static BindingDefn buildBindingFromFhirModel( org.hl7.fhir.definitions.model.BindingSpecification spec,
				CompositeTypeDefn parent)
	{
		BindingDefn result = FhirFactory.eINSTANCE.createBindingDefn();
		
		result.setId( Integer.parseInt( spec.getId() ) );
		result.setName( spec.getName() );
		
		if( parent == null )
			result.setFullName( spec.getName() );		// this is a global binding
		else
			result.setFullName( parent.getName() + "." + spec.getName() );

		result.setDefinition( spec.getDefinition() );
		result.setDescription( spec.getDescription() );
		result.setExample( spec.getStrength() == BindingStrength.EXAMPLE );
		
		result.setBinding( BindingType.get(spec.getBinding().ordinal()) );
		
		String artifact = spec.getReference();
		if( artifact != null && artifact.startsWith("#"))
			artifact = artifact.substring(1);
		
		result.setReference( artifact );
		result.setSource( spec.getSource() );
	
		result.setV2Map(spec.getV2Map());
		result.setV3Map(spec.getV3Map());
		
		for( org.hl7.fhir.definitions.model.DefinedCode code : spec.getCodes() )
		{
			DefinedCode convertedCode = convertFromFhirDefinedCode( code );
			result.getCode().add( convertedCode );
		}
		
		return result;
	}

	
	public static DefinedCode convertFromFhirDefinedCode( org.hl7.fhir.definitions.model.DefinedCode code) 
	{
		DefinedCode result = FhirFactory.eINSTANCE.createDefinedCode();
		
		result.setId( code.getId() );
		result.setCode( code.getCode() );
		result.setDefinition( Utilities.cleanupTextString(code.getDefinition()) );
		result.setDisplay( Utilities.cleanupTextString(code.getDisplay()));
		result.setSystem( Utilities.cleanupTextString(code.getSystem()));
		result.setComment( Utilities.cleanupTextString(code.getComment()));
    result.setV2Map(code.getV2Map());
    result.setV3Map(code.getV3Map());
    
		if( !Utilities.noString(code.getParent()) )
		  result.setParent(code.getParent());
		
		return  result;
	}

	public static BindingDefn buildResourceTypeBinding(Definitions definitions) {
		BindingDefn result = FhirFactory.eINSTANCE.createBindingDefn();
		
		result.setId(10000);
		result.setName("ResourceType");
		result.setFullName(result.getName());

		result.setDescription("List of all supported FHIR Resources");
		result.setDefinition(result.getDescription());
		
		result.setBinding(BindingType.CODE_LIST);
		
		for(ResourceDefn resource : definitions.getResources() )
		{
			DefinedCode code = FhirFactory.eINSTANCE.createDefinedCode();
			code.setCode(resource.getName());
			code.setDefinition("The " + code.getCode() + " resource");
			result.getCode().add(code);
		}
		
		return result;
	}
}
