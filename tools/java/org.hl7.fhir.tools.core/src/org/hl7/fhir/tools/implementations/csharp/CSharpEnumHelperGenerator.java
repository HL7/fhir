package org.hl7.fhir.tools.implementations.csharp;

import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;

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
public class CSharpEnumHelperGenerator extends GenBlock {

	CSharpModelGenerator rgen;
	
	private Definitions definitions;
	
	
	public Definitions getDefinitions() {
		return definitions;
	}

	
	public CSharpEnumHelperGenerator(Definitions defs)
	{
		definitions = defs;
		
		rgen = new CSharpModelGenerator(defs);
	}
	
	public GenBlock generateEnumHelper( Definitions definitions, List<BindingDefn> enums ) throws Exception
	{
		List<BindingDefn> enumerableLists = new ArrayList<BindingDefn>();
		
		// Only bindings that are truly enumerable ('complete' codelists),
		// can be turned into enums, the rest remain Code.
		for( BindingDefn enumeration : enums )
			if( GeneratorUtils.isEnumerableCodeList(enumeration) )
				enumerableLists.add(enumeration);
		
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion()) );
		
		ln("namespace Hl7.Fhir.Model");
		bs("{");
			ln("public static class EnumHelper");
			bs("{");
				ln("public static bool TryParseEnum(string value, Type enumType, out object result)");
				bs("{");
					enumParseCases(enumerableLists);
				es("}");
				ln();
			    ln("public static string EnumToString(object value, Type enumType)");
			    bs("{");
			    	enumToStringCases(enumerableLists);
			    es("}");
			es("}");
		es("}");
		
		return end();
	}


	private void enumParseCases(List<BindingDefn> enums) throws Exception {
		boolean first = true;

		for( BindingDefn enu : enums)
		{
			if( enu.getBinding() == BindingType.CODE_LIST)
			{
				enumParseCase(enu,first);
				first = false;
			}
		}
		ln("else");
		bs("{");
			ln("result = null;");
			ln("return false;");
		es("}");
		ln();
	}
	
	private void enumParseCase(BindingDefn enu, boolean first) throws Exception
	{
		String name = GeneratorUtils.buildFullyScopedTypeName(enu.getFullName());
		
		if(first)
			ln("if");
		else
			ln("else if");
		
		nl("(enumType == typeof(" + name + "))");
		bs("{");
			ln(name + " res;");
			ln("bool success = ");
				nl(name+"Handling.TryParse(value, out res);");
			ln("result = res;");
			ln("return success;");
		es("}");
	}
	
	private void enumToStringCases(List<BindingDefn> enums) throws Exception {
		boolean first = true;

		for( BindingDefn enu : enums)
		{
			if( enu.getBinding() == BindingType.CODE_LIST)
			{
				enumToStringCase(enu,first);
				first = false;
			}
		}
		ln("else");
       	bs();
       		ln("throw new ArgumentException(\"Unrecognized enumeration \"");
				nl(" + enumType.Name);");
       	es(); 
       
		ln();
	}


	private void enumToStringCase(BindingDefn enu, boolean first) throws Exception
	{
		String name = GeneratorUtils.buildFullyScopedTypeName(enu.getFullName());
		
		if(first)
			ln("if");
		else
			ln("else if");
		
		nl("(enumType == typeof(" + name + "))");
		bs();
			ln("return ");
				nl(name+"Handling.ToString(");
				nl("(" + name + ")value);");
		es();
	}	
}