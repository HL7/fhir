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

import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.DefinedCode;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.Invariant;
import org.hl7.fhir.definitions.ecore.fhir.PrimitiveDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;


public class CSharpModelGenerator extends GenBlock
{
	private Definitions definitions;
	
	public Definitions getDefinitions() {
		return definitions;
	}


	public CSharpModelGenerator(Definitions defs) {
		definitions = defs;
	}

	public GenBlock generateComposite( CompositeTypeDefn composite ) throws Exception
	{
		begin();
		
		header(getDefinitions().getDate(), getDefinitions().getVersion());
		
		ln("namespace Hl7.Fhir.Model");
		bs("{");
			compositeClass( composite ); 
		es("}");
		
		return end();
	}

	
	public GenBlock generateGlobalEnums( List<BindingDefn> globalEnums ) throws Exception
	{
		begin();
		
		header(definitions.getDate(), definitions.getVersion());
		
		ln("namespace Hl7.Fhir.Model");
		bs("{");
			enums(globalEnums); 
		es("}");
		
		return end();
	}

	

	public GenBlock generateConstrained( ConstrainedTypeDefn constrained ) throws Exception
	{
		begin();
		
		header(definitions.getDate(), definitions.getVersion());
		
		ln("namespace Hl7.Fhir.Model");
		bs("{");
			ln("/// <summary>");
			ln("/// " +  constrained.getAnnotations().getShortDefinition());
			ln("/// </summary>");
			ln("[FhirComposite("); nl("\"" + constrained.getName() + "\""); nl(")]");
			ln("public partial class " +  GeneratorUtils.generateCSharpTypeName(constrained.getName()) );
				nl(" : ");
				nl(GeneratorUtils.generateCSharpTypeName(constrained.getConstrainedBaseType().getName()));
			bs("{");
				ln("// TODO: Add code to enforce these constraints:");
				for( Invariant inv : constrained.getDetails() ) 
					ln("// * " + inv.getHuman() );
			es("}");
		es("}");
				
		return end();
	}
	
	public GenBlock header(Date genDate, String version)
	{
		begin();
		
		ln("using System;");
		ln("using System.Collections.Generic;");
		ln("using Hl7.Fhir.Support;");
		ln("using System.Xml.Linq;");
		ln();
		ln("/*");
		ln(Config.FULL_LICENSE_CODE);
		ln("*/");
		ln();
		ln("//");
		ln("// Generated on " + Config.DATE_FORMAT().format(genDate));
				nl(" for FHIR v" + version);
		ln("//");
		
		return end();
	}
	
	public GenBlock compositeClass( CompositeTypeDefn composite ) throws Exception
	{
		begin();
		
		ln("/// <summary>");
		ln("/// " + composite.getAnnotations().getShortDefinition());
		ln("/// </summary>");

		// Generate the class itself		
		compositeClassHeader( composite );
		bs("{");		
			// Generate local bindings
			if( composite.getBindings().size() > 0)
				enums( composite.getBindings() );
			
			// Generate the nested local types in this scope
			if( composite.getLocalCompositeTypes().size() > 0)
				nestedLocalTypes( composite.getLocalCompositeTypes() ); 			
	
			// Generate extra members if this type contains a primitive Value member
			if( composite.isPrimitiveContents() )
			{
				PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
				generateExtraPrimitiveMembers(prim, GeneratorUtils.generateCSharpTypeName(composite.getName()));
			}
			
			// Generate this classes properties
			for( ElementDefn member : composite.getElements() )
				generateMemberProperty(composite, member);
		es("}");
		ln();
		
		return end();
	}

	
	
	private void generateMemberProperty(CompositeTypeDefn context, ElementDefn member)
			throws Exception {
		ln("/// <summary>");
		ln("/// " + member.getAnnotation().getShortDefinition());
		ln("/// </summary>");
		ln("public ");
		
		if( member.getMaxCardinality() == -1 )  nl("List<");		

		// Determine the most appropriate FHIR type to use for this
		// (possibly polymorphic) element.
		TypeRef tref = GeneratorUtils.getMemberTypeForElement(getDefinitions(),member);
		
		if( GeneratorUtils.isCodeWithCodeList( getDefinitions(), tref ) )
		{
			nl("Code<" + GeneratorUtils.buildFullyScopedTypeName(tref.getFullBindingRef()) + ">");
		}
		else if( member.isPrimitiveContents() )
		{
			nl( GeneratorUtils.mapPrimitiveToCSharpType( context.getName() ));
		}
		else 
		{
			nl( GeneratorUtils.generateCSharpTypeName(tref.getName()) );
		}

		if( member.getMaxCardinality() == -1 ) nl(">");
	
		nl( " " + GeneratorUtils.generateCSharpMemberName(member) );
		nl(" { get; set; }");
		ln();
	}
	

	private void nestedLocalTypes( List<CompositeTypeDefn> nestedTypes) throws Exception
	{
		begin();

		for( CompositeTypeDefn nested : nestedTypes )
		{
			compositeClass( nested );
			ln();
		}
		
		end();
	}

	
	private void compositeClassHeader(CompositeTypeDefn composite) throws Exception
	{
		if( composite.isComposite() )
			ln("[FhirComposite(");
		else if( composite.isResource() )
			ln("[FhirResource(");
		nl("\"" + composite.getName() + "\""); nl(")]");
		ln( "public ");
			if( composite.isAbstract() ) nl("abstract ");
			nl("partial class " + GeneratorUtils.generateCSharpTypeName(composite.getName()) );
				
		// Derive from appropriate baseclass
		if( composite.getBaseType() != null ) 
		{
			nl( " : " ); 
			nl(GeneratorUtils.buildFullyScopedTypeName(composite.getBaseType()));
		}
	}
	
	public GenBlock enums( List<BindingDefn> bindings ) throws Exception
	{
		begin();
		
		for( BindingDefn binding : bindings ) 
		{
			if( GeneratorUtils.isEnumerableCodeList(binding) )
			{	
				generateEnum(binding);
				ln();
				
				generateEnumHandling(binding);
				ln();
			}
		}
	
		return end();
	}


	public GenBlock generateEnumHandling(BindingDefn binding) 
	{
		begin();
		
		ln("/// <summary>");
		ln("/// Conversion of " + binding.getName() + "from and into string");
		ln("/// <summary>");
		ln("public static class " + binding.getName() + "Handling");
		bs("{"); 
			ln("public static bool TryParse");
				nl("(string value, ");
				nl("out " + binding.getName() + " result)");
			bs("{");
				ln( "result = default(" + binding.getName() + ");");
				ln();					
				enumValueParseCases(binding);
				ln();
				ln("return true;");					
			es("}");
			ln();
			ln("public static string ToString");
				nl("(" + binding.getName() + " value)");
			bs("{");
				enumValueToStringCases(binding);
			es("}");
		es("}");
		
		return end();
	}

	
	private void enumValueParseCases(BindingDefn binding) 
	{
		boolean isFirstClause = true;
			
		for( DefinedCode code : binding.getCodes() ) 
		{					
			if( !isFirstClause )
				ln("else ");
			else
				ln();
					
			isFirstClause = false;
			
			nl("if( value==");
				nl("\"" + code.getCode() + "\")");
			bs();
				ln("result = " + binding.getName());
					nl("." + GeneratorUtils.generateCSharpEnumMemberName(code.getCode()));
					nl(";");
			es();						
		}
		ln("else");
		bs();
			ln("return false;");
		es();
	}

	private void enumValueToStringCases(BindingDefn binding) 
	{
		boolean isFirstClause = true;
			
		for( DefinedCode code : binding.getCodes() ) 
		{					
			if( !isFirstClause )
				ln("else ");
			else
				ln();
					
			isFirstClause = false;
			
			nl("if( value==");
				nl(binding.getName());
				nl("." + GeneratorUtils.generateCSharpEnumMemberName(code.getCode()));
				nl(" )");
			bs();
				ln("return ");
					nl("\"" + code.getCode() + "\";");
			es();						
		}
	    ln("else");
	    bs();
	    	ln("throw new ArgumentException(\"Unrecognized ");
	    		nl(binding.getName());
	    		nl(" value: \" + value.ToString());");
	    es();
	}


	public GenBlock generateEnum(BindingDefn binding) throws Exception {
		begin();
		
		ln("/// <summary>");
		ln("/// " + binding.getAnnotations().getDefinition() );
		ln("/// </summary>");
		ln("public enum " + 
				GeneratorUtils.generateCSharpTypeName(binding.getName()));
		bs("{");
			for( DefinedCode code : binding.getCodes() ) 
			{
				String definition = code.getDefinition();
				
				ln(GeneratorUtils.generateCSharpEnumMemberName(code.getCode()) + ",");
				
				if( definition != null )
					nl(" // " + code.getDefinition());		
			}
		es("}");
		
		return end();
	}
	
	
	public GenBlock generateExtraPrimitiveMembers(PrimitiveDefn primitive, String className) throws Exception
	{	
		String csharpPrimitive = GeneratorUtils.mapPrimitiveToCSharpType(primitive.getName()); 
		boolean isNullablePrimitive = csharpPrimitive.endsWith("?");

		begin();
		
    	if( primitive.getPattern() != null )
    	{
    		ln("// Must conform to the pattern ");
    			nl( "\"" + primitive.getPattern() + "\"" );
    		ln("public const string PATTERN = @");
    			nl("\"" + primitive.getPattern() + "\";");
    		ln();
    	}
    
    	
    	// Generate constructor, taking one parameter - the primitive value
        ln("public " + className);
        	nl("(" + csharpPrimitive + " value)");
        bs("{");
        	ln( "Contents = value; ");
        es("}");
        ln();

    	// Generate empty constructor
        ln("public " + className + "()");
        	nl(": this(null) {}");
        ln();
        
        // Generate the cast from a C# primitive to the Fhir primitive
        ln("public static implicit operator ");
        	nl(className);
        	nl("(" + csharpPrimitive + " value)");
        bs("{");
            ln( "return new " );
            	nl(className + "(value);");
        es("}");
        ln();
        
        // Generate the cast from the Fhir primitive to the C# primitive
        // This is an explicit cast because you'll lose information about
        // dataAbsentReasons, refid, extensions
        ln("public static explicit operator ");
        	nl(csharpPrimitive);
        	nl("(" + className + " value)");
        bs("{");
            ln("return value.Contents;");
        es("}");
        ln();
        
        // If the FhirPrimitive represents data using a C# nullable
        // primitive, generate another cast from the FhirPrimitive to the
        // non-nullable C# primitive.
        if( isNullablePrimitive )
        {
        	String nonNullablePrimitive = csharpPrimitive.substring(0, csharpPrimitive.length()-1);
        	
        	ln("public static explicit operator ");
        		nl(nonNullablePrimitive);
        		nl("(" + className + " source)");
        	bs("{");
	            ln("if (source.Contents.HasValue)");
	            ln("	return source.Contents.Value;");
	            ln("else");
	            ln("	throw new InvalidCastException();");
	        es("}");
        }
        
        return end();
	}


}
