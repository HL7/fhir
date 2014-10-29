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
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;



/*
 * "Old" Syntax for type declarations in Fhir
 * 
 * typeSpec = '@' elementreference | '[param]' | 'xhtml' | 'xml:ID' |
 * 			'Interval(' orderedType ')' | 'Reference(' resourceParams ')' | 
 * 			type('|' type)* | '*'
 * 
 * resourceParams = resourceType ('|' resourceType)* | Any 
 * type = primitiveType | dataType | structure
 * 
 */

public class TypeRefConverter 
{
	public static List<TypeRef> buildTypeRefsFromFhirModel( 
						List<org.hl7.fhir.definitions.model.TypeRef> refs ) throws Exception
	{
		List<TypeRef> result = new ArrayList<TypeRef>();

		for( org.hl7.fhir.definitions.model.TypeRef ref : refs )
		{
				result.add( buildTypeRefsFromFhirModel(ref) );
		}
		
		return result;
	}
	

	// The current type column in the Excel sheet contains some special type names
	// that are mapped to their correct eCore-defined types in using this function
	public static TypeRef buildTypeRefsFromFhirModel( org.hl7.fhir.definitions.model.TypeRef ref )
			throws Exception
	{
		TypeRef convertedType = FhirFactory.eINSTANCE.createTypeRef();
		
		if( ref.isElementReference() )
			convertedType.setName( ref.getResolvedTypeName() );
		else if( ref.isXmlLang() )
		{
			// The special type "xml:lang" is not a FHIR basetype, but indicates
			// that the attribute is present as an "xml:lang" attribute in XML,
			// and as a normal attribute in Json.
			convertedType.setName("code");
		}
		else if( ref.isWildcardType() )
		{
			// The baseclass of all primitives and composites is Element,
			// so the wildcard ("*") is represented by this baseclass
			convertedType.setName(TypeRef.ELEMENT_TYPE_NAME);
		}
		else
		{
			// Excel mentions "Resource", but this is actually either a "ResourceReference"
			// or a contained Resource (in the last case, the name is correct and passes
			// unaltered)
			if( ref.isResourceReference() )
			{
				convertedType.setName(TypeRef.RESOURCEREF_TYPE_NAME);
				
				if( !ref.isAnyReference() )					
					convertedType.getResourceParams().addAll(ref.getParams());					
			}
			else
				convertedType.setName( ref.getName() );
		}

		return convertedType;
	}
	

	public static TypeRef buildTypeRefsFromFhirTypeName( String fhirTypeName ) throws Exception
	{	
		org.hl7.fhir.definitions.model.TypeRef oldRef = new org.hl7.fhir.definitions.model.TypeRef();
		oldRef.setName(fhirTypeName);
		
		return buildTypeRefsFromFhirModel(oldRef);
	}


	public static void Fix(TypeRef ref, NameScope scope) 
	{
		TypeDefn referredType = findTypeDefn(ref.getName(), scope);
		
		if( referredType != null )
			ref.setFullName(referredType.getFullName());
			
		if( ref.getBindingRef() != null )
		{
			BindingDefn referredBinding = findBindingDefn(ref.getBindingRef(), scope);
			if( referredBinding != null )
				ref.setFullBindingRef(referredBinding.getFullName());	
		}
	}
	
	private static TypeDefn findTypeDefn(String localName, NameScope scope)
	{
		NameScope current = scope;
		
		do
		{
			for( TypeDefn type : current.getType() )
			{
				if( type.getName().equals(localName) )
					return type;
			}

			current = current.getContainingScope();		
		}
		while( current != null );
		
		return null;
	}
	
	
	private static BindingDefn findBindingDefn(String localName, NameScope scope)
	{
		NameScope current = scope;
		
		do
		{
			for( BindingDefn binding : current.getBinding() )
			{
				if( binding.getName().equals(localName) )
					return binding;
			}

			current = current.getContainingScope();		
		}
		while( current != null );
		
		return null;
	}	
}
