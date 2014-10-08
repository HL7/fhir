package org.hl7.fhir.tools.implementations.csharp;

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

import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.SearchParameter;
import org.hl7.fhir.definitions.ecore.fhir.SearchType;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;


public class CSharpModelInformationGenerator extends GenBlock
{
	CSharpModelGenerator rgen;
	
	private Definitions definitions;
	
	
	public Definitions getDefinitions() {
		return definitions;
	}

	
	public CSharpModelInformationGenerator(Definitions defs)
	{
		definitions = defs;
		
		rgen = new CSharpModelGenerator(defs);
	}

	public GenBlock generateInformation() throws Exception
	{
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln();
		ln("namespace Hl7.Fhir.Model");
		bs("{");		
			ln("/*");
			ln("* A class with methods to retrieve informationa about the");
			ln("* FHIR definitions based on which this assembly was generated.");
			ln("*/");
			ln("public static partial class ModelInfo");
			bs("{");
				generateSupportedResourcesList(definitions);
				ln();		
				generateVersionInfo(definitions);
				ln();
				generateTypeMappings(definitions);
				ln();
				generateSearchParams(definitions);
			es("}");
		es("}");
	
		return end();
	}


	private void generateSearchParams(Definitions definitions2) 
	{
    ln("public static List<SearchParamDefinition> SearchParameters = ");
    bs(); 
      ln("new List<SearchParamDefinition>");
      bs("{");  
      for( ResourceDefn resource : definitions.getResources() )
        if( resource.isAbstract() == false )
         for( SearchParameter param : resource.getSearch() )
            {
              ln("new SearchParamDefinition() { ");
              nl("Resource = \"" + resource.getName() + "\"" );
              nl(", Name = \"" + param.getName() + "\"" );
              nl(", Description = " + GeneratorUtils.makeCsStringLiteral(param.getDescription()) );
              
              String type = Utilities.capitalize(param.getType().toString());
              nl(", Type = Conformance.SearchParamType." + type );

              if( param.getType() == SearchType.COMPOSITE)
              {
                nl(", CompositeParams = new string[] { ");
                for( String compositePar : param.getComposite() )
                  nl("\"" + compositePar + "\", ");
                nl("}");
              }
              
              if( param.getPath() != null && param.getPath().size() > 0)
              {
                nl(", Path = new string[] { ");
                for( String elem : param.getPath() )
                  nl("\"" + elem + "\", ");
                nl("}");
              }

              nl(" }, ");          
            }
	    es("};");
  }


  private void generateVersionInfo(Definitions definitions) {
		ln("public static string Version");
		bs("{");
			ln("get { return \"" + definitions.getVersion() + "\"; }");
		es("}");
	}
	
	
	private void generateSupportedResourcesList(Definitions definitions) 
	{
		ln("public static List<string> SupportedResources = ");
		bs();	
    	ln("new List<string>");
			bs("{");	
				for( ResourceDefn resource : definitions.getResources() )
				  if(!resource.isAbstract() && !resource.isFuture())
				    ln("\"" + resource.getName() + "\",");
      es("};");
    es();
	}


	private void generateTypeMappings(Definitions definitions) throws Exception
	{
		ln("public static Dictionary<string,Type> FhirTypeToCsType =");
		bs();
			ln("new Dictionary<string,Type>()");
			bs("{");
				for( TypeDefn type : definitions.getType() )
				{
				  if(type instanceof ResourceDefn && ((ResourceDefn)type).isFuture()) continue;

				  String cSharpName = GeneratorUtils.buildFullyScopedTypeName(type); 
					ln( tuple("\"" + type.getName() + "\"", "typeof(" + cSharpName + ")" ) );
				}
			es("};");
		es();
		ln();
		ln("public static Dictionary<Type,string> FhirCsTypeToString =");
		bs();
			ln("new Dictionary<Type,string>()");
			bs("{");
				for( TypeDefn type : definitions.getType() )
				{
          if(type instanceof ResourceDefn && ((ResourceDefn)type).isFuture() ) continue;
          
          String cSharpName = GeneratorUtils.buildFullyScopedTypeName(type);				
					ln( tuple("typeof(" + cSharpName + ")", "\"" + type.getName() + "\"" ) );
				}
			es("};");
		es();
	}
	
	private String tuple( String left, String right )
	{
		return "{ " + left + ", " + right + " },";
	}
	
}
