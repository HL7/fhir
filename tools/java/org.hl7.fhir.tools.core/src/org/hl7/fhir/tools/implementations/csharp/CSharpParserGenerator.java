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

import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;


public class CSharpParserGenerator extends GenBlock
{
	private CSharpModelGenerator rgen;

	private Definitions definitions;
	
	
	public Definitions getDefinitions() {
		return definitions;
	}


	public CSharpParserGenerator(Definitions defs)
	{
		definitions = defs;
		rgen = new CSharpModelGenerator(defs);
	}

	
	public GenBlock generateConstrainedParser( ConstrainedTypeDefn constrained ) throws Exception
	{
		CompositeTypeDefn baseType = (CompositeTypeDefn)
				getDefinitions().findType(constrained.getConstrainedBaseType().getFullName());

		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln("using System.Xml;");
		ln();
		ln("namespace Hl7.Fhir.Parsers");
		bs("{");
			ln("/// <summary>");
			ln("/// Parser for constrained " + constrained.getName() + " instances");
			ln("/// </summary>");
			ln("internal static partial class " + constrained.getName() + "Parser");
			bs("{");	
				String returnType = GeneratorUtils.buildFullyScopedTypeName(constrained);

			ln("/// <summary>");
			ln("/// Parse " + constrained.getName());
			ln("/// </summary>");
			ln("public static ");
				nl( returnType );
				nl(" ");
				nl("Parse"); nl(GeneratorUtils.generateCSharpTypeName(constrained.getName()));
				nl("(IFhirReader reader, ErrorList errors, ");
				nl(returnType + " existingInstance = null )");
			bs("{");	
				ln( returnType );
					nl(" result = existingInstance != null ? existingInstance : ");
					nl("new " + returnType + "();");
					
					ln(buildCompositeOrConstrainedParserCall(baseType, "result"));
						nl(";");
				
				ln("return result;");
			es("}");
			ln();		
			es("}");
		es("}");
	
		return end();
	}
	

	public GenBlock generateCompositeParser( CompositeTypeDefn composite, Definitions definitions ) throws Exception
	{
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln("using System.Xml;");
		ln();
		ln("namespace Hl7.Fhir.Parsers");
		bs("{");
			ln("/// <summary>");
			ln("/// Parser for " + composite.getName() + " instances");
			ln("/// </summary>");
			ln("internal static partial class " + GeneratorUtils.generateCSharpTypeName(composite.getName()) + "Parser");
			bs("{");
				compositeParserFunction(composite);
			es("}");
		es("}");
	
		return end();
	}
	
	
	private GenBlock compositeParserFunction( CompositeTypeDefn composite ) throws Exception
	{
		begin();
		
		String returnType = GeneratorUtils.buildFullyScopedTypeName(composite);
				
		ln("/// <summary>");
		ln("/// Parse " + composite.getName());
		ln("/// </summary>");
		ln("public static ");
			nl( returnType );
			nl(" ");
			nl("Parse"); nl(GeneratorUtils.generateCSharpTypeName(composite.getName())); 
			nl("(IFhirReader reader, ErrorList errors, ");
			nl(returnType + " existingInstance = null )");
		bs("{");	
			ln( returnType );
				nl(" result = existingInstance != null ? existingInstance : ");
				nl("new " + returnType + "();");
				
			buildCompositeElementParser(composite);
			
			ln("return result;");
		es("}");
		ln();
	
		// Generate the nested local types in this scope
		if( composite.getLocalCompositeTypes().size() > 0)
		{
			for( CompositeTypeDefn subtype : composite.getLocalCompositeTypes() )
				compositeParserFunction( subtype ); 			
		}
		
		return end();
	}


	private void buildCompositeElementParser(CompositeTypeDefn composite) throws Exception 
	{
//		ln("try");
//		bs("{");
        	ln("string currentElementName = reader.CurrentElementName;");
        	ln("reader.EnterElement();");
            ln(); 
        	ln("while (reader.HasMoreElements())");
            bs("{");           	
              // Generate this classes properties, getAllElements() will list
            	// all elements within this composite and its basetypes
              ln("var atName = reader.CurrentElementName;");
            	List<ElementDefn> allProperties = composite.getAllElements();
            	generateMemberParsers(allProperties, composite.isResource());
            es("}");
          ln();
          ln("reader.LeaveElement();");
//		es("}");
//		ln("catch (Exception ex)");
//		bs("{");
//			ln("errors.Add(ex.Message, reader);");
//		es("}");   
	}

	
	
	private GenBlock generateMemberParsers( List<ElementDefn> elements, boolean inResource ) throws Exception
	{
		begin();

		boolean first = true;
		
		for( ElementDefn member : elements )
		{			
			ln("// Parse element " + member.getName());	
			generateMemberParser(member, first);
			first = false;
		}
		
		if( elements.size() > 0 )
			ln("else");
        bs("{");
             ln("errors.Add(String.Format(\"Encountered unknown element {0} while parsing {1}\", reader.CurrentElementName, currentElementName), reader);");
             ln("reader.SkipSubElementsFor(currentElementName);");
             ln("result = null;");
        es("}");
		        	         
		return end();
	}


	public GenBlock generateMemberParser(ElementDefn member, boolean first) throws Exception 
	{
		begin();
		
		// First determine the possible names of the properties in the
		// instance, which might be multiple because of polymorph elements 
		if( first )
			ln("if( " );
		else
			ln("else if( ");
		
		nl( buildCheckForElementClause(member,false) );
		nl(" )");
		
		if( member.isRepeating() )
			parseRepeatingElement(member);
		else
			parseSingleElement(member);			
					
		ln();
		
		return end();
	}
	
	

	private void parseRepeatingElement(ElementDefn member) throws Exception
	{
		String resultMember = "result." + member.getGeneratorAnnotations().get(CSharpModelGenerator.CLASSGEN_MEMBER_NAME);
		//resultMember += "Element";
		
		TypeRef resultType = GeneratorUtils.getMemberTypeForElement(getDefinitions(),member);
				
		bs("{");
			// Allocate an List in the property we are going to fill. 
			ln(resultMember);
				nl(" = new List<");
								
				if( GeneratorUtils.isCodeWithCodeList(getDefinitions(), resultType ))
				{
					nl("Code<");
					nl(GeneratorUtils.buildFullyScopedTypeName(resultType.getFullBindingRef()));
					nl(">");
				}
				else
					nl(GeneratorUtils.buildFullyScopedTypeName(resultType));
				nl(">();");
			ln("reader.EnterArray();");
			ln();
			ln("while( ");
				nl( buildCheckForElementClause(member,true) );
				nl(" )");
			
			bs();
				ln(resultMember + ".Add(");
					nl( buildParserCall(member) );
					nl(");");
			es();
			ln();
			ln("reader.LeaveArray();");
		es("}");
	}
	
	
	private void parseSingleElement( ElementDefn member ) throws Exception
	{
	  String memberName = member.getGeneratorAnnotations().get(CSharpModelGenerator.CLASSGEN_MEMBER_NAME);
    //resultMember += "Element";
    
		bs();
		   
		ln("result." + memberName );
			nl(" = ");
			nl( buildParserCall(member) );
			nl(";");
		es();
	}
	
	
	private String buildParserCall(ElementDefn member) throws Exception
	{
		TypeRef resultTypeRef = GeneratorUtils.getMemberTypeForElement(getDefinitions(),member);
		
		// Check specials cases: parsing of enumerated codes, contained resources and
		// choice properties of type Data, Composite or Primitive
		if( !member.isPolymorph() && GeneratorUtils.isCodeWithCodeList(getDefinitions(), member.getType().get(0)) )
			return buildEnumeratedCodeParserCall(member.getType().get(0));
		else if( member.containsResource() )
			return buildContainedResourceParserCall();
		else if( resultTypeRef.getName().equals(TypeRef.ELEMENT_TYPE_NAME) ) 
			return buildPolymorphParserCall(resultTypeRef);
		else if( member.isPrimitiveContents() )
			return buildPrimitiveParserCall( member );
		else
		{
			TypeDefn resultType = getDefinitions().findType(resultTypeRef.getFullName());	
			if (resultType != null)
			  return buildParserCall(resultType);
			 else
				// return "not done yet";
				throw new Exception("unable to find type for "+resultTypeRef.getFullName());
		}
	}
	
	
	private String buildPrimitiveParserCall(ElementDefn member) throws Exception {
		String fhirPrimitive = member.getType().get(0).getName();
	  String csharpPrimitive = GeneratorUtils.mapPrimitiveToFhirCSharpType(fhirPrimitive);
		
		String call = "ReadPrimitiveContents(typeof(" + csharpPrimitive + "))";
		
		String method = null;

    // "value" members map directly to the Value property using the C# native type
		if( member.isPrimitiveValueElement() )
		  method = "ParseValue";
		else
		  method = "Parse";
		
		String result = csharpPrimitive + "." + method + "(reader." + call + ")";
				
		return result;
	}


	public static String buildParserCall(TypeDefn def) throws Exception
	{
		if( def.isComposite() || def.isConstrained() )
			return buildCompositeOrConstrainedParserCall(def); 
		else
			throw new Exception( "Cannot handle element of type " + def.getName() + " to generate parser call." );
	}
	
	private String buildCheckForElementClause( ElementDefn member, Boolean inArray )
	{
		String clause;
		
		if( !inArray  )
		{
		  if(!member.isPolymorph())
		    clause = "atName == " + "\"" + member.getName() + "\"";
		  else
		    clause = "atName.StartsWith(" + "\"" + member.getName() + "\")";
		}
		else
		{
			clause = "ParserUtils.IsAtArrayElement";
			clause += "(reader, \"" + member.getName() + "\")";
		}
		
		return clause;
	}
	
	
	private static String buildCompositeOrConstrainedParserCall(TypeDefn type) throws Exception
	{
		return buildCompositeOrConstrainedParserCall(type, null);
	}
	
	private static String buildCompositeOrConstrainedParserCall(TypeDefn type, String existingInstanceName) throws Exception
	{		
		StringBuilder result = new StringBuilder();
		
		if( type.isGloballyDefined() )
		{
			// A type defined on the global level, child of Definitions
			result.append(GeneratorUtils.generateCSharpTypeName(type.getName()) + "Parser" );	
		}
		else
		{
			// A type defined inside a globally defined composite 
			result.append(((CompositeTypeDefn)type.getScope()).getName() + "Parser");
		}

		result.append(".Parse" + GeneratorUtils.generateCSharpTypeName(type.getName()) );
		
		if( existingInstanceName == null )
			result.append("(reader, errors)");
		else
		{
			result.append("(reader, errors, ");
			result.append(existingInstanceName);
			result.append(")");
		}
		
		return result.toString(); 
	}
	

	private String buildEnumeratedCodeParserCall(TypeRef ref) throws Exception
	{		
		StringBuffer result = new StringBuffer();
		
		result.append("CodeParser.ParseCode");
		
		BindingDefn binding = getDefinitions().findBinding(ref.getFullBindingRef());
		String enumType = GeneratorUtils.buildFullyScopedTypeName(binding.getFullName());
								
		result.append("<" + enumType + ">");
		result.append("(reader, errors)");
		
		return result.toString();
	}

	
	private String buildContainedResourceParserCall() throws Exception
	{
		return "ParserUtils.ParseContainedResource(reader,errors)";
	}
	
	
	private String buildPolymorphParserCall(TypeRef type) throws Exception 
	{
		return "FhirParser.Parse" +	type.getName() + "(reader, errors)";
	}
}
