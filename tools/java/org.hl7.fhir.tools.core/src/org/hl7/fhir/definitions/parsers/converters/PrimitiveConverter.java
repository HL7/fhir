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

import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;


public class PrimitiveConverter 
{
	public static List<PrimitiveDefn> buildPrimitiveTypesFromFhirModel( Collection<org.hl7.fhir.definitions.model.DefinedCode> primitives )
			throws Exception
	{
		List<PrimitiveDefn> result = new ArrayList<PrimitiveDefn>();
		
		for(org.hl7.fhir.definitions.model.DefinedCode primitive : primitives )
		{
			result.add(buildPrimitiveTypeFromFhirModel( primitive ) );
		}
		
		// Add "xhtml" as a primitive. Could do this by changing fhir.ini,
		// but don't know consequences for old code, so for now, do it like this.
		//result.add( buildXhtmlPrimitiveType() );
				
		return result;
	}

	private static PrimitiveDefn buildPrimitiveTypeFromFhirModel( org.hl7.fhir.definitions.model.DefinedCode primitive) 
			throws Exception
	{
		PrimitiveDefn result = FhirFactory.eINSTANCE.createPrimitiveDefn();
		
		result.setName( primitive.getCode() );
		
		result.setAnnotations(FhirFactory.eINSTANCE.createAnnotations());
		result.getAnnotations().setDefinition( Utilities.cleanupTextString(primitive.getDefinition()) );
		result.getAnnotations().setComment( Utilities.cleanupTextString(primitive.getComment()) );
		
		if( primitive instanceof org.hl7.fhir.definitions.model.PrimitiveType )
		{
			org.hl7.fhir.definitions.model.PrimitiveType prim = (org.hl7.fhir.definitions.model.PrimitiveType)primitive; 
			result.setXsdtype( prim.getSchemaType() );
			
			if( prim.getRegEx() != null && prim.getRegEx().length() > 0)
				result.setPattern( ((org.hl7.fhir.definitions.model.PrimitiveType)primitive).getRegEx() );
		}
		else if ( primitive instanceof org.hl7.fhir.definitions.model.DefinedStringPattern )
		{
			result.setXsdtype("string");
			result.setPattern( ((org.hl7.fhir.definitions.model.DefinedStringPattern)primitive).getRegex());
		}
		else
			throw new Exception( "Cannot build an eCore primitive type for unknown class " + primitive.getClass().getName() );

		return result;
	}

//	private static PrimitiveDefn buildXhtmlPrimitiveType()
//	{
//		PrimitiveDefn xhtml = FhirFactory.eINSTANCE.createPrimitiveDefn();
//		xhtml.setName("xhtml");
//		xhtml.setXsdtype("string");
//		
//		xhtml.setAnnotations(FhirFactory.eINSTANCE.createAnnotations());
//		xhtml.getAnnotations().setDefinition( "A string of xhtml data, with a <div> as root" );
//		xhtml.getAnnotations().setComment( "Can contain only the basic html formatting elements described in chapters 7-11 (except section 4 of chapter 9) and 15 of the HTML 4.0 standard, <a> elements (either name or href), images, and internally contained style attributes." );
//		
//		return xhtml;
//	}
	
	
	public static Collection<CompositeTypeDefn> buildCompositeTypesForPrimitives(
			Collection<PrimitiveDefn> primitives) {
		
		List<CompositeTypeDefn> result = new ArrayList<CompositeTypeDefn>();
		
		for(PrimitiveDefn primitive : primitives )
		{
			result.add(buildCompositeTypeForPrimitive( primitive ) );
		}

		return result;
	}

	private static CompositeTypeDefn buildCompositeTypeForPrimitive(PrimitiveDefn primitive) 
	{
		CompositeTypeDefn result = FhirFactory.eINSTANCE.createCompositeTypeDefn();

		// set baseclass to "Element" (needs to be generated too in Global, just like Resource)
		// (baseclass Element has the "id" and "extension" element shared by all composites)
		
		TypeRef ref = FhirFactory.eINSTANCE.createTypeRef();
		ref.setName("Element");
		result.setBaseType(ref);
		
		// This composite type has the same name as the imported primitive
		result.setName(primitive.getName());
		result.setFullName(primitive.getName());
		
		// Set documentation to something reasonable
		Annotations typeAnn = FhirFactory.eINSTANCE.createAnnotations();
		typeAnn.setShortDefinition("Typed element containing the primitive " + result.getName());
		typeAnn.setDefinition(typeAnn.getShortDefinition());
		result.setAnnotations(typeAnn);
		
		// add "value" element of the correct primitive type
		ElementDefn valueElement = GeneratorUtils.buildSimpleElementDefn("value", primitive.getName(),
		              "Primitive value of the element", 0, 1);
		valueElement.setPrimitiveContents(true);
		valueElement.setXmlFormatHint(XmlFormatHint.ATTRIBUTE);
		valueElement.setSummaryItem(true);
		result.getElement().add(valueElement);
		
		return result;
	}
	
}
