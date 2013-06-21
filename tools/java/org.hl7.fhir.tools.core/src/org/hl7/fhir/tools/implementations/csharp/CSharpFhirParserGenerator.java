package org.hl7.fhir.tools.implementations.csharp;

/*
 Copyright (c) 2011-2013, HL7, Inc
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

import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.utilities.Utilities;


public class CSharpFhirParserGenerator extends GenBlock
{
	private CSharpModelGenerator rgen;

	private Definitions definitions;
	
	
	public Definitions getDefinitions() {
		return definitions;
	}


	public CSharpFhirParserGenerator(Definitions defs)
	{
		definitions = defs;
		rgen = new CSharpModelGenerator(defs);
	}

	public GenBlock generateResourceParser( Definitions definitions ) throws Exception
	{		
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln("using System.Xml;");
		ln();
		ln("namespace Hl7.Fhir.Parsers");
		bs("{");		
			ln("/*");
			ln("* Starting point for parsing resources");
			ln("*/");
			ln("public static partial class FhirParser");
			bs("{");
				ln("internal static Resource ParseResource(IFhirReader reader, ErrorList errors)");
				bs("{");
					ln("try");
					bs("{");
						ln("reader.MoveToContent();");
						ln();
						generateResourceCases(definitions.getResources());
					es("}");
					ln("catch( Exception xe )");
					bs("{");
						ln("errors.Add( xe.Message, reader);");
						ln("return null;");
					es("}");
				es("}");
				
			ln();
			generateElementParser();
			es("}");
		es("}");
	
		return end();
	}
	
	
	private GenBlock buildPolymorphParser(String polymorphTypeName, List<TypeDefn> composites) throws Exception {
		begin();
		
		ln("internal static ");
			nl(polymorphTypeName);
			nl(" Parse");
			nl(polymorphTypeName);
			nl("(IFhirReader reader, ErrorList errors)");
		bs("{");
			ln("try");
			bs("{");
				ln("reader.MoveToContent();");
				ln();
				generatePolymorphCases(composites);
			es("}");
			ln("catch( Exception xe )");
			bs("{");
				ln("errors.Add( xe.Message, reader);");
				ln("return null;");
			es("}");
		es("}");
				
		return end();
	}
	
  private GenBlock generateElementParser() throws Exception
	{
    List<TypeDefn> composites = new ArrayList<TypeDefn>();
    
    composites.addAll(getDefinitions().getLocalConstrainedTypes());
    composites.addAll(getDefinitions().getLocalCompositeTypes());
    
		return buildPolymorphParser("Element", composites);
	}
	
	private void generatePolymorphCases(List<?> types) throws Exception
	{
		boolean firstTime = true;
		
		for( Object t : types )
		{
			TypeDefn type = (TypeDefn)t;
			
			// Don't generate parser calls for abstract types
			if( t instanceof CompositeTypeDefn && ((CompositeTypeDefn)t).isAbstract())
				continue;
			
			if( firstTime )
				ln("if");
			else
				ln("else if");
			
			firstTime = false;
			String elementName;
			
			// By convention, choice types use xxxxResource instead of
			// xxxxResourceReference, so check for this
			if( type.getName().equals("ResourceReference"))
			  elementName = "Resource";
			else
			  elementName = Utilities.capitalize(type.getName()); 
			
			nl("( ParserUtils.IsAtFhirElementEndingWith(reader, \"");
				nl( elementName );
				nl("\" ))");
			bs();
				ln("return ");
					nl( CSharpParserGenerator.buildParserCall(type) );
					nl(";");
			es();				
		}
		
		ln("else");
		bs("{");
			ln("errors.Add(String.Format(");
				nl("\"Encountered unrecognized datatype '{0}'\",");
		        nl("	reader.CurrentElementName), reader);");
		    ln("reader.SkipSubElementsFor(reader.CurrentElementName);");
		    ln("return null;");
		es("}");
	}


	private void generateResourceCases(List<ResourceDefn> localResources)
	{
		boolean firstTime = true;
		
		for( ResourceDefn resource : localResources)
		{
			if( resource.isAbstract() ) continue;
			
			if( firstTime )
				ln("if");
			else
				ln("else if");
			
			firstTime = false;
			
			nl("( ParserUtils.IsAtFhirElement(reader, \"");
				nl( resource.getName() );
				nl("\" ) )");
			bs();
				ln("return " + resource.getName() + "Parser");
					nl(".Parse" + resource.getName());
					nl("(reader, errors);");
			es();				
		}
		
		ln("else");
		bs("{");
			ln("errors.Add(String.Format(");
				nl("\"Encountered unrecognized resource '{0}'\",");
		        nl("	reader.CurrentElementName), reader);");
		    ln("reader.SkipSubElementsFor(reader.CurrentElementName);");
		    ln("return null;");
		es("}");
	}
	
}
