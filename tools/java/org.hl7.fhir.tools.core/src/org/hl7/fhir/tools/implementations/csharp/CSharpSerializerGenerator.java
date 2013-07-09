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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;


public class CSharpSerializerGenerator extends GenBlock
{
	CSharpModelGenerator rgen;
	
	private Definitions definitions;
	
	
	public Definitions getDefinitions() {
		return definitions;
	}

	
	public CSharpSerializerGenerator(Definitions defs)
	{
		definitions = defs;
		
		rgen = new CSharpModelGenerator(defs);
	}


	public GenBlock generateResourceSerializer() throws Exception
	{
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln("using System.Xml;");
		ln("using Newtonsoft.Json;");
		ln("using Hl7.Fhir.Serializers;");
		ln();
		ln("namespace Hl7.Fhir.Serializers");
		bs("{");		
			ln("/*");
			ln("* Starting point for serializing resources");
			ln("*/");
			ln("public static partial class FhirSerializer");
			bs("{");
				resourceSerializer(definitions);
				ln();
				elementSerializer(definitions);			
			es("}");
		es("}");
	
		return end();
	}

	private void elementSerializer(Definitions definitions) throws Exception {
		ln("internal static void SerializeElement(Element value, IFhirWriter writer, bool summary)");
		bs("{");
			List<TypeDefn> composites = new ArrayList<TypeDefn>();
			composites.addAll(definitions.getLocalCompositeTypes());
			composites.addAll(definitions.getLocalConstrainedTypes());
			generateSerializationCases(composites);
		es("}");
		ln();
	}

	private void resourceSerializer(Definitions definitions) throws Exception {
		ln("internal static void SerializeResource(Resource value, IFhirWriter writer, bool summary)");
		bs("{");
			generateSerializationCases(definitions.getResources());
		es("}");
		ln();
	}
	
	private void generateSerializationCases(List<?> types) throws Exception
	{
		boolean firstTime = true;
		
		for( Object t : types)
		{
			TypeDefn type = (TypeDefn)t;
		
			if( t instanceof CompositeTypeDefn && ((CompositeTypeDefn)t).isAbstract())
				continue;
			
			if( firstTime )
				ln("if");
			else
				ln("else if");
			
			firstTime = false;
			
			String typeName = GeneratorUtils.buildFullyScopedTypeName(type);
			nl("(value.GetType() == typeof(");
			nl( typeName + "))");
			bs();
				String serializerCall = buildSerializerCall(type);				
				ln(serializerCall + "(");
					nl("(" + typeName + ")");
					nl("value, writer, summary);");
			es();				
		}
		
		ln("else");
		bs();
			ln("throw new Exception(\"Encountered unknown type \" + ");
				nl("value.GetType().Name);");
		es();
	}
	
	
	public GenBlock generateCompositeSerializer( CompositeTypeDefn composite ) throws Exception
	{
		begin();
		
		inc( rgen.header(definitions.getDate(), definitions.getVersion() ) );
		ln();
		ln("using Hl7.Fhir.Model;");
		ln("using System.Xml;");
		ln("using Newtonsoft.Json;");
		ln("using Hl7.Fhir.Serializers;");
		
		ln();
		ln("namespace Hl7.Fhir.Serializers");
		bs("{");
			ln("/*");
			ln("* Serializer for " + composite.getName() + " instances");
			ln("*/");
			ln("internal static partial class " + GeneratorUtils.generateCSharpTypeName(composite.getName()) + "Serializer");
			bs("{");
				compositeSerializerFunction(composite);
			es("}");
		es("}");
	
		return end();
	}


	
	public GenBlock compositeSerializerFunction( CompositeTypeDefn composite ) throws Exception
	{
		begin();
				
		String valueType = GeneratorUtils.buildFullyScopedTypeName(composite);
		EList<ElementDefn> allElements = composite.getAllElements();
		
		ln("public static void ");
			nl("Serialize");
			nl(GeneratorUtils.generateCSharpTypeName(composite.getName())); 
			nl("(" + valueType + " value, ");
			nl("IFhirWriter writer, bool summary)");			

		bs("{");
			if( composite.isResource() )
			{
				// Resources, being top-level objects, have their
				// resource name as a single root member
				ln("writer.WriteStartRootObject(\"");
					nl(composite.getName());
					nl("\");");
			}
			ln("writer.WriteStartComplexContent();");	
			ln();
		
			// Generate this classes properties
			if( allElements.size() > 0)
			{
		     
	      List<ElementDefn> sortedElements = new ArrayList<ElementDefn>();
	      
	      // Make sure elements that need to be serialized as attributes in Xml
	      // are sorted and generated first, since the streaming Xml writer api
	      // will need to have them before the elements come in.
	      for( ElementDefn elem : allElements )
	        sortedElements.add(elem);
	      Collections.sort(sortedElements, new Comparator<ElementDefn>()
	        {
	          public int compare(ElementDefn e1, ElementDefn e2)
	          {
	            if(e1.getXmlFormatHint() == XmlFormatHint.ATTRIBUTE) 
	              return -1;
	            else
	              return 1;
	          }
	        });

				generateMemberSerializers( sortedElements );
				ln();
			}
			
			ln("writer.WriteEndComplexContent();");
			
			if( composite.isResource() )
				ln("writer.WriteEndRootObject();");
		es("}");
		ln();
		
		// Generate the nested local types in this scope
		if( composite.getLocalCompositeTypes().size() > 0)
		{
			for( CompositeTypeDefn subtype : composite.getLocalCompositeTypes() )
				compositeSerializerFunction( subtype ); 			
		}
		
		return end();
	}

	
	public GenBlock generateMemberSerializers( List<ElementDefn> elements ) throws Exception
	{
		begin();
				
		for( ElementDefn member : elements )
		{			
			ln("// Serialize element " + member.getName());
			generateMemberSerializer(member);
		}
		         
		return end();
	}
		
	private void generateMemberSerializer(ElementDefn member) throws Exception 
	{
		String propertyName = "value." + 
		        member.getGeneratorAnnotations().get(CSharpModelGenerator.CLASSGEN_MEMBER_NAME);
	  		
		ln("if(" + propertyName + " != null");
		
		if(!member.isSummaryItem())
		  nl(" && !summary");
		
		if( member.isRepeating() )
		{
			nl(" && " + propertyName + ".Count > 0)");
			bs("{");
				serializeRepeatingElement(member, propertyName);
			es("}");
		}
		else
		{		
			nl(")");

		  if( member.isPrimitiveContents() )
		  {
		    bs(); serializePrimitiveValue(member, propertyName); es();
		  }
		  else
		  {
		     bs("{");
           serializeSingleElement(member, propertyName);
         es("}");
		  }			
		}			
					
		ln();
	}
	
	
	private void serializePrimitiveValue(ElementDefn member, String propertyName)
	{ 
    // Primitive elements only serialize the element's Value.
    ln("writer.WritePrimitiveContents(");   
    nl("\"" + member.getName() + "\", " );
    
    if( member.getName().equals("value") )
      nl("value, ");
    else
      nl(propertyName + ", " );
    
    nl("XmlSerializationHint." + member.getXmlFormatHint().getName());
    nl(");");
	}
	
	private void serializeRepeatingElement( ElementDefn member, String propertyName  ) throws Exception
	{	
		ln("writer.WriteStartArrayElement(");
			nl("\"" + member.getName() + "\"");
			nl(");");

		ln("foreach(var item in " + propertyName + ")");
		bs("{");
			ln("writer.WriteStartArrayMember(");
				nl("\"" + member.getName() + "\"");
				nl(");");
	
			buildSerializeStatement("item", member);
			
			ln("writer.WriteEndArrayMember();");
		es("}"); 
		
		ln("writer.WriteEndArrayElement();");
	}
	
	private void serializeSingleElement( ElementDefn member, String propertyName ) throws Exception
	{	
		if( member.isPolymorph() )
		{
			ln("writer.WriteStartElement( ");
				nl("SerializationUtil.BuildPolymorphicName(");
				nl("\"" + member.getName() + "\", "); 
				nl(propertyName);
				nl(".GetType()) );");
		}
		else
		{
			ln("writer.WriteStartElement(");
			nl("\"" + member.getName() + "\"");	
			nl(");");
		}										

		buildSerializeStatement(propertyName, member);
		
	  ln("writer.WriteEndElement();");
	}


	private void buildSerializeStatement(String propertyName,	ElementDefn member) throws Exception
	{
	  // Handle special cases: we need a switch when handling polymorph serializations
		if( member.isPolymorph() )
			ln("FhirSerializer.SerializeElement(");
		
		// Special case: contained resources
		else if( member.containsResource() )
			ln("FhirSerializer.SerializeResource(");
		
		// Normal case - a normal element
	  else 
	  {
	    if( GeneratorUtils.isCodeWithCodeList(definitions, member.getType().get(0)) )
  		{
  			String enumType = GeneratorUtils.buildFullyScopedTypeName(member.getType().get(0).getFullBindingRef());
  			ln("CodeSerializer.SerializeCode<");
  				nl(enumType);
  				nl(">(");
  		}
  		else
  		{
  			TypeRef ref = member.getType().get(0);
  			TypeDefn type = definitions.findType(ref.getFullName());
  			String serializerCall;
				serializerCall = buildSerializerCall(type);
  			ln(serializerCall + "(");
  		}
	  }
		
		nl(propertyName + ", writer, summary);");
	}
	
	private String buildSerializerCall( TypeDefn t ) throws Exception
	{
		TypeDefn typeToUse = t;
		
		if( t instanceof ConstrainedTypeDefn )
		{
			TypeRef baseType = ((ConstrainedTypeDefn)t).getConstrainedBaseType();
			typeToUse = definitions.findType(baseType.getFullName());
		}
		
		return GeneratorUtils.buildFullyScopedSerializerTypeName(typeToUse.getFullName()) +
				".Serialize" + GeneratorUtils.generateCSharpTypeName(typeToUse.getName());
	}
}
