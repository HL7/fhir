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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;
import org.hl7.fhir.definitions.ecore.fhir.impl.CompositeTypeDefnImpl;
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

	
	// I know that can't be all, but cannot find a more generic approach now.
	private String escape(String docText)
	{
	  String result = docText;
    result = result.replace("&", "&amp;");  // This one first ;-)
    
	  result = result.replace("<", "&lt;");
	  result = result.replace(">", "&gt;");
	  
	  return result;
	}
	
	private GenBlock generateDocComment( String summary, String remarks )
	{
    begin();
    
    if(summary != null)
    {     
      ln("/// <summary>");
      ln("/// " + escape(summary));
      ln("/// </summary>");
      
      if(remarks != null)
      {
        ln("/// <remarks>");
          nl(escape(remarks));
          nl("</remarks>");
      }
    }
    
    return end();
	}
	
	private GenBlock generateDocComment( String summary )
	{
	  return generateDocComment(summary, null);
	}
	

	public GenBlock generateConstrained( ConstrainedTypeDefn constrained ) throws Exception
	{
		begin();
		
		header(definitions.getDate(), definitions.getVersion());
		
		ln("namespace Hl7.Fhir.Model");
		bs("{");
		  generateDocComment(constrained.getAnnotations().getShortDefinition());
		  
			ln("[FhirType("); nl("\"" + constrained.getName() + "\""); nl(")]");
			//ln("[Serializable]");
			ln("public partial class " +  GeneratorUtils.generateCSharpTypeName(constrained.getName()) );
				nl(" : ");
				nl(GeneratorUtils.generateCSharpTypeName(constrained.getConstrainedBaseType().getName()));
			bs("{");
			 generateMetaTypeMembers( constrained.getName(), constrained.isReference() );
				ln("// TODO: Add code to enforce these constraints:");
				for( Invariant inv : constrained.getDetail() ) 
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
		ln("using Hl7.Fhir.Introspection;");
		ln("using Hl7.Fhir.Validation;");
		ln("using System.Linq;");
		ln("using System.Runtime.Serialization;");
		
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

		generateDocComment(composite.getAnnotations().getShortDefinition());

		// Generate the class itself		
		compositeClassHeader( composite );
		bs("{");	
		  // Generate the meta-type members
		  generateMetaTypeMembers( composite.getName(), composite.isReference() );
		
			// Generate local bindings
			if( composite.getBinding().size() > 0)
				enums( composite.getBinding() );
			
			// Generate the nested local types in this scope
			if( composite.getLocalCompositeTypes().size() > 0)
				nestedLocalTypes( composite.getLocalCompositeTypes() ); 			
	
			String className = GeneratorUtils.generateCSharpTypeName(composite.getName());
			
			// Generate extra members if this type contains a primitive Value member
			if( hasPrimitiveValueElement(composite) )
			{
				PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
				generateExtraPrimitiveMembers(prim, className);
			}
			
			generateMembers(composite);
			
			generateCopyTo(composite);
			
			// Generate the DeepCopy() function for non-abstract classes
			if(!composite.isAbstract())
			{
        generateDeepCopy(className);
        ln();
			}
		
			generateCompare(composite,false);
			generateCompare(composite,true);
			// Generate Validate() routine			
			//generateValidationMethod(composite);

			// Put in the NotifyPropertyChanged bits
			//String derivation = composite.getName();
			
			if( composite.getBaseType() == null )
			{
				ln("public event System.ComponentModel.PropertyChangedEventHandler PropertyChanged;");
				ln("protected void OnPropertyChanged(String property)");
				bs("{");
				ln("if (PropertyChanged != null)");
				ln("	PropertyChanged(this, new System.ComponentModel.PropertyChangedEventArgs(property));");
				es("}");
			}

		es("}");
		ln();
		
		return end();
	}


  private void generateMetaTypeMembers(String name, boolean isResource) 
  {
    String modifier = isResource && name.equals("Resource") ? "virtual" : "override";
    if(isResource)
    {
      ln("[NotMapped]");
      ln("public " + modifier + " ResourceType ResourceType { get { return ResourceType." + name + "; } }");
    }
      
    ln("[NotMapped]");      
    ln("public override string TypeName { get { return " + "\"" + name + "\"" + "; } }");
    ln();
  }


  private void generateMembers(CompositeTypeDefn composite) throws Exception {
    int numChildren = 0;
    CompositeTypeDefn baseScan = composite;
    while(baseScan.getBaseType() != null)
    {
      baseScan = (CompositeTypeDefnImpl)composite.resolve(baseScan.getBaseType());
      numChildren += baseScan.getElement().size();
    }

    // Start ordering the elements at 10 (increase by 10), but start after the
    // numbers used in our baseclasses 
    int order = numChildren*10 + 10;
    
    // Make sure elements that need to be serialized as attributes in Xml
    // are sorted and generated first, since the streaming Xml writer api
    // will need to have them before the elements come in.
    List<ElementDefn> sortedElements = new ArrayList<ElementDefn>();
    
    for( ElementDefn elem : composite.getElement() )
      sortedElements.add(elem);
    
    Collections.sort(sortedElements, new Comparator<ElementDefn>()
      {
        @Override
        public int compare(ElementDefn e1, ElementDefn e2)
        {
          if(e1.getXmlFormatHint() == XmlFormatHint.ATTRIBUTE) 
            return -1;
          else
            return 1;
        }
      }); 
    
    // Generate this classes properties
    for( ElementDefn member : composite.getElement() )
    {
    	generateMemberProperty(composite, member, order);
    	order += 10;
    }
  }

  
  private void generateCopyTo(CompositeTypeDefn composite) throws Exception
  {
    String className = GeneratorUtils.generateCSharpTypeName(composite.getName());
//    Boolean isBase = className.equals("Resource") || className.equals("Element");
    
    ln("public override IDeepCopyable CopyTo(IDeepCopyable other)");
    bs("{");      
      ln("var dest = other as " + className + ";");
      ln();
      ln("if (dest != null)");
      bs("{");
//        if(!isBase) ln("base.CopyTo(dest);");
        ln("base.CopyTo(dest);");   // Since there's now a virtual CopyTo in Base.cs, you can always call base
        
        for( ElementDefn member : composite.getElement() )
        {
          String memberName = member.getGeneratorAnnotations().get(CLASSGEN_MEMBER_NAME);
          String memberType = member.getGeneratorAnnotations().get(CLASSGEN_MEMBER_CSTYPE);
    
          ln("if(" + memberName + " != null) ");
          if(member.isPrimitiveContents())
            nl("dest." + memberName + " = " + memberName + ";");            
          else
          {
            String rhs = memberName + ".DeepCopy()";
            if( member.getMaxCardinality() == -1 )
              rhs = "new " + memberType + "(" + rhs + ")";
            else
              rhs = "(" + memberType + ")" + rhs;
            
            nl("dest." + memberName + " = " + rhs + ";");
          }
        }
        
        ln("return dest;");
      es("}");    
      ln("else");
        ln("\tthrow new ArgumentException(\"Can only copy to an object of the same type\", \"other\");");
    es("}");
    ln();
  }

  
  private void generateCompare(CompositeTypeDefn composite, boolean genExactly) throws Exception
  {
    String className = GeneratorUtils.generateCSharpTypeName(composite.getName());
    Boolean isBase = className.equals("Resource") || className.equals("Element");
    //String override = isBase ? "virtual" : "override";
    String override = "override";
    String method = genExactly ? "IsExactly" : "Matches";
        
    ln("public " + override + " bool " + method + "(IDeepComparable other)");
    bs("{");
      ln("var otherT = other as " + className + ";");
      ln("if(otherT == null) return false;");
      ln();
      
      if(!isBase) ln("if(!base." + method + "(otherT)) return false;");
          
      for( ElementDefn member : composite.getElement() )
      {
        String memberName = member.getGeneratorAnnotations().get(CLASSGEN_MEMBER_NAME);
  
        if(member.isPrimitiveContents())
          ln("if( " + memberName + " != otherT." + memberName + " ) return false;");            
        else
          ln("if( !DeepComparable." + method + "(" + memberName + ", otherT." + memberName + ")) return false;");
      }
      ln();
      ln("return true;");
    es("}");
    ln();    
  }
  
	private boolean hasPrimitiveValueElement( CompositeTypeDefn composite )
	{
	  for( ElementDefn element : composite.getElement() )
	    if( element.isPrimitiveValueElement() )
	      return true;

	  return false;
	}
	
	private void generateMemberProperty(CompositeTypeDefn context, ElementDefn member, int order)
			throws Exception {
  
    // Determine the most appropriate FHIR type to use for this
    // (possibly polymorphic) element.
    TypeRef tref = GeneratorUtils.getMemberTypeForElement(getDefinitions(),member);
    boolean isFhirPrimitive = Character.isLowerCase(tref.getName().charAt(0));
    boolean needsNativeProperty = (isFhirPrimitive && member.getXmlFormatHint() != XmlFormatHint.ELEMENT) ||
                                    member.isPrimitiveValueElement();
    boolean hasBothPrimitiveAndElementProperty = isFhirPrimitive && !needsNativeProperty;
    
    String choiceType = null;
    String choices = "";
        
    if(member.isPolymorph())
    {     
      for(TypeRef choiceTRef : member.getType())
      {    
        String name = choiceTRef.getName();
        
        if(name.equals(TypeRef.ELEMENT_TYPE_NAME))
          choiceType = "DatatypeChoice";
        else if(name.equals(TypeRef.RESOURCE_TYPE_NAME))
          choiceType = "ResourceChoice";
        else
          choiceType = "DatatypeChoice";
        
        choices += "typeof(" + GeneratorUtils.buildFullyScopedTypeName(choiceTRef) + "),";
      }
      
      if(choices.endsWith(",")) choices = choices.substring(0, choices.length()-1);
    }
    
    generateDocComment(member.getAnnotation().getShortDefinition());
		
	  ln("[FhirElement(\"" + member.getName() + "\"");
		  
	  if(member.isPrimitiveValueElement())
		    nl(", IsPrimitiveValue=true");
		  
    if(member.getXmlFormatHint() != XmlFormatHint.ELEMENT)
	      nl(", XmlSerialization=XmlSerializationHint." + member.getXmlFormatHint().getName());
    
    if(member.isSummaryItem())
        nl(", InSummary=true");
    
    if(order != 0)
      nl(", Order=" + Integer.toString(order));

    if(choiceType!=null) // None
      nl(", Choice=ChoiceType." + choiceType);
    nl(")]");    
          
    if(choices.length() > 0)
    {
      ln("[AllowedTypes(");
      nl(choices);
      nl(")]");
    }
    
    if(!member.isPolymorph() && member.getType().get(0).getName().equals("Reference"))
    {
      ln("[References(");
      boolean isNext = false;
      
      for(String resParam : member.getType().get(0).getResourceParams() )
      {
        if(isNext) nl(",");
        nl("\"" + resParam + "\"");
        isNext =true;
      }
      
      nl(")]");
    }
    
    // a. If min > 0 -> element(s) must be present -> we need to validate
    // b. If max != 1 -> it's a list with no, or any cardinality -> validate (also because this checks lists have no null elements)
//    // b1. If max == -1 -> a list with any length, no additional validation requirements beyond min
//    // b2. If max == 1 -> a single element, no additional validation requirements beyond min
//    if(member.getMinCardinality() > 0 || (member.getMaxCardinality() != -1 && member.getMaxCardinality() != 1) )
    if(member.getMinCardinality() > 0 || member.getMaxCardinality() != 1)
    {
      ln("[Cardinality(");
      nl("Min=" + Integer.toString(member.getMinCardinality()));
      nl(",Max=" + Integer.toString(member.getMaxCardinality()));
      nl(")]");
    }
    
		if(needsNativeProperty) addPrimitiveValidators(tref.getName());

		ln("[DataMember]");
		ln("public ");
			
		String memberCsType;
		
		if( GeneratorUtils.isCodeWithCodeList( getDefinitions(), tref ) )
		  // Strongly typed enums use a special Code<T> type
		  memberCsType = "Code<" + GeneratorUtils.buildFullyScopedTypeName(tref.getFullBindingRef()) + ">";	
		else if( needsNativeProperty )
	    // Primitive elements' value property maps directly to a C# type
		  memberCsType = GeneratorUtils.mapPrimitiveToCSharpType(tref.getName());
		else 
			memberCsType = GeneratorUtils.buildFullyScopedTypeName(tref);

		String singleElementCsType = memberCsType;
		
		// Surround with List<T> if it is a repeating element
		if( member.getMaxCardinality() == -1 )
		  memberCsType = "List<" + memberCsType + ">";
	
		String memberName = GeneratorUtils.generateCSharpMemberName(member);
		
		if(hasBothPrimitiveAndElementProperty)
		{
		  memberName += "Element";
		}
		
		member.getGeneratorAnnotations().put(CLASSGEN_MEMBER_NAME, memberName);
		member.getGeneratorAnnotations().put(CLASSGEN_MEMBER_CSTYPE, memberCsType);
	
		nl( memberCsType + " " + memberName  );
		
		bs("{");
		
		if(member.getMaxCardinality() == -1)
		{
		  ln("get { if(_" + memberName + "==null) _" + memberName + " = new " + memberCsType + "();");
		  nl(" return _"+memberName+"; }");		  
		  //get { if (_Relationship == null) _Relationship = new List<Hl7.Fhir.Model.CodeableConcept>(); return _Relationship; }
		}
		else
		  ln("get { return _"+memberName+"; }");
		
		ln("set { _"+memberName+" = value; OnPropertyChanged(\""+memberName+"\"); }");
		es("}");
		ln();
		
		if(!member.isPrimitiveValueElement())
		{
		  // Primitives have this value as a protected member in their base, Primitive<T>
  		ln( "private " + memberCsType + " _" + memberName + ";" );
  		ln();
		}
		
		if(hasBothPrimitiveAndElementProperty)
	    // If this element is of a type that is a FHIR primitive, generate extra helper
	    // access methods to get to easily get to the elements Value property.
		  generateSimpleValueAccess(member, tref, memberCsType, singleElementCsType, memberName);
	}


  private void addPrimitiveValidators(String primitive) {
   // Add any specific validators placed atop native types
   if(primitive.equals("code"))
     ln("[CodePattern]");
   else if(primitive.equals("date"))
     ln("[DatePattern]");
   else if(primitive.equals("dateTime"))
     ln("[DateTimePattern]");
   else if(primitive.equals("id"))
     ln("[IdPattern]");
   else if(primitive.equals("instant"))
     ln("[InstantPattern]");
   else if(primitive.equals("xhtml"))
     ln("[NarrativeXhtmlPattern]");
   else if(primitive.equals("oid"))
     ln("[OidPattern]");
   else if(primitive.equals("uri"))
     ln("[UriPattern]");
   else if(primitive.equals("uuid") )
     ln("[UuidPattern]");
  }


  private void generateSimpleValueAccess(ElementDefn member, TypeRef tref, String memberCsType, 
      String singleElementCsType, String memberName) throws Exception {
	  boolean isList = member.getMaxCardinality() == -1; 
	  boolean isTypedEnum = GeneratorUtils.isCodeWithCodeList( getDefinitions(), tref ); 
	  String csType = null;
	  String simpleMemberName = GeneratorUtils.generateCSharpMemberName(member);
	  
	  if( isTypedEnum  )
	    csType = GeneratorUtils.buildFullyScopedTypeName(tref.getFullBindingRef()) + "?";
	  else
	    csType = GeneratorUtils.mapPrimitiveToCSharpType(tref.getName() );
	  

	  if( isList )
	    csType = "IEnumerable<" + csType + ">";

	  generateDocComment(member.getAnnotation().getShortDefinition(), "This uses the native .NET datatype, rather than the FHIR equivalent" );
	  
	  ln("[NotMapped]");
	  ln("[IgnoreDataMemberAttribute]");
	  ln("public " + csType + " " + simpleMemberName);
	  bs("{");
	    ln("get { return " + memberName + " != null ? ");
	    if(!isList)
	      nl(memberName + ".Value");
	    else
	      nl(memberName + ".Select(elem => elem.Value)");
	    nl(" : null; }");
	    ln("set");
	    bs("{");
	      ln("if(value == null)");
	      ln("  " + memberName + " = null; ");
	      ln("else");
	      ln("  " + memberName + " = new " + memberCsType);
	      
	      if( !isList )
	        nl("(value);");
	      else
	      {
	        nl("(value.Select(elem=>new ");
	        nl(singleElementCsType + "(elem)));");
	      }
	      ln("OnPropertyChanged(\""+simpleMemberName+"\");");
	    es("}");

//	    Some new constructs for in-place editing of array. Not too sure whether
//	    thats a good idea
//
//      public string GetLine(int index)
//      {
//          return LineElement != null ? LineElement[index].Value : null;
//      }
//
//      public void SetLine(int index, string value)
//      {
//          if (LineElement == null) LineElement = new List<FhirString>();
//          LineElement[index] = new FhirString(value);
//      }

	  es("}");
	  ln();
  }
	

	public final static String CLASSGEN_MEMBER_NAME = "classgen.membername";
	public final static String CLASSGEN_MEMBER_CSTYPE = "classgen.membercstype";
	
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
	  // Avoid generating type attributes with names for the abstract baseclasses Element
	  // since that's both a primitive and a composite type
		if( composite.isComposite() && !composite.isAbstract() )
		{
		    ln("[FhirType(\"" + composite.getName() + "\")]" );
		}
		else if( composite.isReference() && !composite.isAbstract() )
		{
			ln("[FhirType(\"" + composite.getName() + "\", IsResource=true)]" );
		}
		
		//ln("[Serializable]");
		ln("[DataContract]");
		ln( "public ");
			if( composite.isAbstract() ) nl("abstract ");
			nl("partial class " + GeneratorUtils.generateCSharpTypeName(composite.getName()) );
				
		// Derive from appropriate baseclass
		if( composite.getBaseType() != null ) 
		{
			nl( " : " ); 						
			
			String baseName = composite.getBaseType().getFullName();
			
	    boolean isFhirPrimitive = Character.isLowerCase(composite.getName().charAt(0));
	    
	    if(isFhirPrimitive)
	    {
	      String memberCsType = GeneratorUtils.mapPrimitiveToCSharpType(composite.getName());	
			  baseName = "Primitive<" + memberCsType + ">";
	    }
	    
		  nl(GeneratorUtils.buildFullyScopedTypeName(baseName));
			nl(", System.ComponentModel.INotifyPropertyChanged");
		}
		else
		{
		  nl( " : " );
		  nl("Base");
			//nl(" : System.ComponentModel.INotifyPropertyChanged");
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
				
			//	generateEnumHandling(binding);
			//	ln();
			}
		}
	
		return end();
	}


//	public GenBlock generateEnumHandling(BindingDefn binding) 
//	{
//		begin();
//		
//		ln("/// <summary>");
//		ln("/// Conversion of " + binding.getName() + "from and into string");
//		ln("/// <summary>");
//		ln("public static class " + binding.getName() + "Handling");
//		bs("{"); 
//			ln("public static bool TryParse");
//				nl("(string value, ");
//				nl("out " + binding.getName() + " result)");
//			bs("{");
//				ln( "result = default(" + binding.getName() + ");");
//				ln();					
//				enumValueParseCases(binding);
//				ln();
//				ln("return true;");					
//			es("}");
//			ln();
//			ln("public static string ToString");
//				nl("(" + binding.getName() + " value)");
//			bs("{");
//				enumValueToStringCases(binding);
//			es("}");
//		es("}");
//		
//		return end();
//	}

	
//	private void enumValueParseCases(BindingDefn binding) 
//	{
//		boolean isFirstClause = true;
//			
//		for( DefinedCode code : binding.getCode() ) 
//		{					
//			if( !isFirstClause )
//				ln("else ");
//			else
//				ln();
//					
//			isFirstClause = false;
//			
//			nl("if( value==");
//				nl("\"" + code.getCode() + "\")");
//			bs();
//				ln("result = " + binding.getName());
//					nl("." + GeneratorUtils.generateCSharpEnumMemberName(code.getCode()));
//					nl(";");
//			es();						
//		}
//		ln("else");
//		bs();
//			ln("return false;");
//		es();
//	}
//
//	private void enumValueToStringCases(BindingDefn binding) 
//	{
//		boolean isFirstClause = true;
//			
//		for( DefinedCode code : binding.getCode() ) 
//		{					
//			if( !isFirstClause )
//				ln("else ");
//			else
//				ln();
//					
//			isFirstClause = false;
//			
//			nl("if( value==");
//				nl(binding.getName());
//				nl("." + GeneratorUtils.generateCSharpEnumMemberName(code.getCode()));
//				nl(" )");
//			bs();
//				ln("return ");
//					nl("\"" + code.getCode() + "\";");
//			es();						
//		}
//	    ln("else");
//	    bs();
//	    	ln("throw new ArgumentException(\"Unrecognized ");
//	    		nl(binding.getName());
//	    		nl(" value: \" + value.ToString());");
//	    es();
//	}


	public GenBlock generateEnum(BindingDefn binding) throws Exception {
		begin();

		generateDocComment(binding.getDefinition());
		ln("[FhirEnumeration(\"" + binding.getName() + "\")]");
		ln("public enum " + 
				GeneratorUtils.generateCSharpTypeName(binding.getName()));
		bs("{");
			for( DefinedCode code : binding.getCode() ) 
			{
				String definition = code.getDefinition();
				
				if( definition != null ) {
				  generateDocComment(code.getDefinition());
				}
				ln("[EnumLiteral(" + "\"" + code.getCode() + "\"" +  ")]");
				ln(GeneratorUtils.generateCSharpEnumMemberName(code.getCode()) + ",");
			}
		es("}");
		
		return end();
	}
	
	
	public GenBlock generateExtraPrimitiveMembers(PrimitiveDefn primitive, String className) throws Exception
	{	
		String csharpPrimitive = GeneratorUtils.mapPrimitiveToCSharpType(primitive.getName()); 
		//boolean isNullablePrimitive = csharpPrimitive.endsWith("?");

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
        	ln( "Value = value; ");
        es("}");
        ln();

    	// Generate empty constructor
        ln("public " + className + "()");
        	nl(": this(");
        	nl("(" + csharpPrimitive + ")"); // Avoid ambiguous this() calls by specifying type of null
        	nl("null) {}");
        ln();
      
        // Generate the cast from a C# primitive to the Fhir primitive
//        ln("public static implicit operator ");
//        	nl(className);
//        	nl("(" + csharpPrimitive + " value)");
//        bs("{");
//            ln("if(value == null)");
//            ln("  return null;");
//            ln("else");
//            ln("  return new " );	nl(className + "(value);");
//        es("}");
//        ln();
        
        // Generate the cast from the Fhir primitive to the C# primitive
        // This is an explicit cast because you'll lose information about
        // dataAbsentReasons, refid, extensions
//        ln("public static explicit operator ");
//        	nl(csharpPrimitive);
//        	nl("(" + className + " value)");
//        bs("{");
//          ln("if(value != null)");
//          ln("  return value.Value;");
//          ln("else");
//          ln("  return null;");
//        es("}");
//        ln();
        
        // If the FhirPrimitive represents data using a C# nullable
        // primitive, generate another cast from the FhirPrimitive to the
        // non-nullable C# primitive.
//        if( isNullablePrimitive )
//        {
//        	String nonNullablePrimitive = csharpPrimitive.substring(0, csharpPrimitive.length()-1);
//        	
//        	ln("public static explicit operator ");
//        		nl(nonNullablePrimitive);
//        		nl("(" + className + " source)");
//        	bs("{");
//	            ln("if(source != null && source.Value != null)");
//	            ln("	return source.Value.Value;");
//	            ln("else");
//	            ln("	throw new InvalidCastException();");
//	        es("}");
//        }
       
        return end();
	}


  private void generateDeepCopy(String className) {
    ln("public override IDeepCopyable DeepCopy()");
    bs("{");
      ln("return CopyTo(new " + className + "());");
    es("}");
  }


}
