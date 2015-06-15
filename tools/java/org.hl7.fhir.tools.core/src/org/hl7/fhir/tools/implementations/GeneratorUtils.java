package org.hl7.fhir.tools.implementations;

import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.Annotations;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.FhirFactory;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.definitions.ecore.fhir.XmlFormatHint;
import org.hl7.fhir.utilities.Utilities;

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

public class GeneratorUtils {

	public static class NamedElementGroup
	{
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		private String name;

		
		private List<ElementDefn> elements;


		public List<ElementDefn> getElements() {
			return elements;
		}
		public void setElements(List<ElementDefn> elements) {
			this.elements = elements;
		}
		
	}
	
	
	public static boolean isCSharpReservedWord(String word) {
	    if (word.equals("abstract")) return true;
	    if (word.equals("as")) return true;
	    if (word.equals("base")) return true;
	    if (word.equals("bool")) return true;
	    if (word.equals("break")) return true;
	    if (word.equals("byte")) return true;
	    if (word.equals("case")) return true;
	    if (word.equals("catch")) return true;
	    if (word.equals("char")) return true;
	    if (word.equals("checked")) return true;
	    if (word.equals("class")) return true;
	    if (word.equals("const")) return true;
	    if (word.equals("continue")) return true;
	    if (word.equals("decimal")) return true;
	    if (word.equals("default")) return true;
	    if (word.equals("delegate")) return true;
	    if (word.equals("do")) return true;
	    if (word.equals("double")) return true;
	    if (word.equals("else")) return true;
	    if (word.equals("enum")) return true;
	    if (word.equals("event")) return true;
	    if (word.equals("explicit")) return true;
	    if (word.equals("extern")) return true;
	    if (word.equals("false")) return true;
	    if (word.equals("finally")) return true;
	    if (word.equals("fixed")) return true;
	    if (word.equals("float")) return true;
	    if (word.equals("for")) return true;
	    if (word.equals("foreach")) return true;
	    if (word.equals("goto")) return true;
	    if (word.equals("if")) return true;
	    if (word.equals("implicit")) return true;
	    if (word.equals("in")) return true;
	    if (word.equals("in(genericmodifier)")) return true;
	    if (word.equals("int")) return true;
	    if (word.equals("interface")) return true;
	    if (word.equals("internal")) return true;
	    if (word.equals("is")) return true;
	    if (word.equals("lock")) return true;
	    if (word.equals("long")) return true;
	    if (word.equals("namespace")) return true;
	    if (word.equals("new")) return true;
	    if (word.equals("null")) return true;
	    if (word.equals("object")) return true;
	    if (word.equals("operator")) return true;
	    if (word.equals("out")) return true;
	    if (word.equals("out(genericmodifier)")) return true;
	    if (word.equals("override")) return true;
	    if (word.equals("params")) return true;
	    if (word.equals("private")) return true;
	    if (word.equals("protected")) return true;
	    if (word.equals("public")) return true;
	    if (word.equals("readonly")) return true;
	    if (word.equals("ref")) return true;
	    if (word.equals("return")) return true;
	    if (word.equals("sbyte")) return true;
	    if (word.equals("sealed")) return true;
	    if (word.equals("short")) return true;
	    if (word.equals("sizeof")) return true;
	    if (word.equals("stackalloc")) return true;
	    if (word.equals("static")) return true;
	    if (word.equals("string")) return true;
	    if (word.equals("struct")) return true;
	    if (word.equals("switch")) return true;
	    if (word.equals("this")) return true;
	    if (word.equals("throw")) return true;
	    if (word.equals("true")) return true;
	    if (word.equals("try")) return true;
	    if (word.equals("typeof")) return true;
	    if (word.equals("uint")) return true;
	    if (word.equals("ulong")) return true;
	    if (word.equals("unchecked")) return true;
	    if (word.equals("unsafe")) return true;
	    if (word.equals("ushort")) return true;
	    if (word.equals("using")) return true;
	    if (word.equals("virtual")) return true;
	    if (word.equals("void")) return true;
	    if (word.equals("volatile")) return true;
	    if (word.equals("while")) return true;
	    return false;
	}
	
	public static boolean isJavaReservedWord(String word) {
    if (word.equals("abstract")) return true;   
		if (word.equals("assert")) return true;
		if (word.equals("boolean")) return true;
		if (word.equals("break")) return true; 	
		if (word.equals("byte")) return true; 	
		if (word.equals("case")) return true;
		if (word.equals("catch")) return true; 	
		if (word.equals("char")) return true; 	
    if (word.equals("class")) return true;  
		if (word.equals("const")) return true; 	
		if (word.equals("continue")) return true; 	
		if (word.equals("default")) return true;
		if (word.equals("double")) return true; 	
		if (word.equals("do")) return true; 	
		if (word.equals("else")) return true; 	
		if (word.equals("enum")) return true; 	
		if (word.equals("extends")) return true; 	
		if (word.equals("false")) return true;
		if (word.equals("final")) return true; 	
		if (word.equals("finally")) return true; 	
		if (word.equals("float")) return true; 	
		if (word.equals("for")) return true; 	
		if (word.equals("goto")) return true; 	
		if (word.equals("if")) return true;
		if (word.equals("implements")) return true; 	
		if (word.equals("import")) return true; 	
		if (word.equals("instanceof")) return true; 	
		if (word.equals("int")) return true; 	
		if (word.equals("interface")) return true; 	
		if (word.equals("long")) return true;
		if (word.equals("native")) return true; 	
		if (word.equals("new")) return true; 	
		if (word.equals("null")) return true; 	
		if (word.equals("package")) return true; 	
		if (word.equals("private")) return true; 	
		if (word.equals("protected")) return true;
		if (word.equals("public")) return true; 	
		if (word.equals("return")) return true; 	
		if (word.equals("short")) return true; 	
		if (word.equals("static")) return true; 	
		if (word.equals("strictfp")) return true; 	
		if (word.equals("super")) return true;
		if (word.equals("switch")) return true; 	
		if (word.equals("synchronized")) return true; 	
		if (word.equals("this")) return true; 	
		if (word.equals("throw")) return true; 	
		if (word.equals("throws")) return true; 	
		if (word.equals("transient")) return true;
		if (word.equals("true")) return true; 	
		if (word.equals("try")) return true; 	
		if (word.equals("void")) return true; 	
		if (word.equals("volatile")) return true;
    if (word.equals("while")) return true;
    if (word.equals("Exception")) return true;
		return false;
	}

	public static boolean isDelphiReservedWord(String word) {
	  if (word.equals("and")) return true;
	  if (word.equals("array")) return true;
	  if (word.equals("as")) return true;
	  if (word.equals("asm")) return true;
	  if (word.equals("begin")) return true;
	  if (word.equals("case")) return true;
	  if (word.equals("class")) return true;
	  if (word.equals("const")) return true;
	  if (word.equals("constructor")) return true;
	  if (word.equals("create")) return true;
	  if (word.equals("destructor")) return true;
	  if (word.equals("dispinterface")) return true;
	  if (word.equals("div")) return true;
	  if (word.equals("do")) return true;
	  if (word.equals("downto")) return true;
	  if (word.equals("else")) return true;
	  if (word.equals("end")) return true;
	  if (word.equals("except")) return true;
	  if (word.equals("exports")) return true;
	  if (word.equals("file")) return true;
	  if (word.equals("finalization")) return true;
	  if (word.equals("finally")) return true;
	  if (word.equals("for")) return true;
	  if (word.equals("function")) return true;
	  if (word.equals("goto")) return true;
	  if (word.equals("if")) return true;
	  if (word.equals("implementation")) return true;
	  if (word.equals("in")) return true;
	  if (word.equals("inherited")) return true;
	  if (word.equals("initialization")) return true;
	  if (word.equals("inline")) return true;
	  if (word.equals("interface")) return true;
	  if (word.equals("is")) return true;
	  if (word.equals("label")) return true;
	  if (word.equals("library")) return true;
	  if (word.equals("link")) return true;
	  if (word.equals("mod")) return true;
	  if (word.equals("nil")) return true;
	  if (word.equals("not")) return true;
	  if (word.equals("object")) return true;
	  if (word.equals("of")) return true;
	  if (word.equals("or")) return true;
	  if (word.equals("out")) return true;
	  if (word.equals("packed")) return true;
	  if (word.equals("procedure")) return true;
	  if (word.equals("program")) return true;
	  if (word.equals("property")) return true;
	  if (word.equals("raise")) return true;
	  if (word.equals("record")) return true;
	  if (word.equals("repeat")) return true;
	  if (word.equals("resourcestring")) return true;
	  if (word.equals("set")) return true;
	  if (word.equals("shl")) return true;
	  if (word.equals("shr")) return true;
	  if (word.equals("string")) return true;
	  if (word.equals("then")) return true;
	  if (word.equals("threadvar")) return true;
	  if (word.equals("to")) return true;
	  if (word.equals("try")) return true;
	  if (word.equals("type")) return true;
	  if (word.equals("unit")) return true;
	  if (word.equals("until")) return true;
	  if (word.equals("uses")) return true;
	  if (word.equals("var")) return true;
	  if (word.equals("while")) return true;
	  if (word.equals("with")) return true;
	  if (word.equals("xor")) return true;
    return false;
	}

		
	public static String mapPrimitiveToCSharpType(String name) throws Exception
	{
		if (name.equals("boolean"))
			return "bool?";
    else if (name.equals("integer"))
      return "int?";
    else if (name.equals("positiveInt"))
      return "int?";
    else if (name.equals("unsignedInt"))
      return "int?";
		else if (name.equals("decimal"))
			return "decimal?";
		else if (name.equals("base64Binary"))
			return "byte[]";
		else if (name.equals("instant"))
			return "DateTimeOffset?";
		else if (name.equals("string"))
			return "string";
		else if (name.equals("uri"))
			return "string";
		else if (name.equals("code"))
			return "string";
		else if (name.equals("oid"))
			return "string";
		else if (name.equals("uuid"))
			return "string";
		else if (name.equals("sid"))
			return "string";
		else if (name.equals("id"))
			return "string";
		else if (name.equals("xhtml"))
			return "string";
		else if (name.equals("date"))
			return "string";
    else if (name.equals("dateTime"))
      return "string";
    else if (name.equals("time"))
      return "string";
		else
			throw new Exception( "Unrecognized primitive " + name );
	}
	
	public static String mapPrimitiveToFhirCSharpType(String name) throws Exception 
	{
		if (name.equals("boolean"))
			return "FhirBoolean";
    else if (name.equals("integer"))
      return "Integer";
    else if (name.equals("positiveInt"))
      return "PositiveInt";
    else if (name.equals("unsignedInt"))
      return "UnsignedInt";
		else if (name.equals("decimal"))
			return "FhirDecimal";
		else if (name.equals("base64Binary"))
			return "Base64Binary";
		else if (name.equals("instant"))
			return "Instant";
		else if (name.equals("string"))
			return "FhirString";
		else if (name.equals("uri"))
			return "FhirUri";
		else if (name.equals("code"))
			return "Code";
		else if (name.equals("oid"))
			return "Oid";
		else if (name.equals("uuid"))
			return "Uuid";
		else if (name.equals("sid"))
			return "Sid";
		else if (name.equals("id"))
			return "Id";
		else if (name.equals("xhtml"))
			return "FhirString";
		else if (name.equals("xml:lang"))
			return "FhirString";
    else if (name.equals("date"))
      return "Date";
    else if (name.equals("time"))
      return "Time";
		else if (name.equals("dateTime"))
			return "FhirDateTime";
		else
			throw new Exception( "Unrecognized primitive " + name );
	}
	
		
	
	public static String generateCSharpTypeName(String name) throws Exception {
		String result;
		
		name = name.replace("-", "");
		
		if( Character.isLowerCase(name.charAt(0)) )
			result = mapPrimitiveToFhirCSharpType(name);
		else
		{		  
			result = Utilities.capitalize(name);
			if(result.equals("Reference")) result = "ResourceReference";
		}
		
		return result;
	}
		
	public static String makeCsStringLiteral(String contents)
	{
	  contents = "@" + "\"" + contents.replace("\"", "\"\"") + "\"";
	  
	  return contents;
	}
	
	
	public static String buildFullyScopedTypeName( TypeDefn type ) throws Exception
	{
		return buildFullyScopedTypeName(type.getFullName());
	}
	
	public static String buildFullyScopedTypeName( TypeRef type ) throws Exception
	{
		return buildFullyScopedTypeName(type.getFullName());
	}
	
	
	private static final String HL7NAMESPACE = "Hl7.Fhir.Model";
	
	public static String buildFullyScopedTypeName( String fullName ) throws Exception
	{		
  	//String[] nameParts = fullName == null ? "DomainResource".split("\\.") : fullName.split("\\.");
  	String[] nameParts = fullName.split("\\.");
  	
		if( nameParts.length == 1 )
			// Globally defined name
			return HL7NAMESPACE + "." + GeneratorUtils.generateCSharpTypeName(nameParts[0]);
		else
			return HL7NAMESPACE + "." + GeneratorUtils.generateCSharpTypeName(nameParts[0]) +
				 "." + GeneratorUtils.generateCSharpTypeName(nameParts[1]);
	}

	public static String buildFullyScopedBindingTypeName( String fullName ) throws Exception
  {   
    //String[] nameParts = fullName == null ? "DomainResource".split("\\.") : fullName.split("\\.");
    String[] nameParts = fullName.split("\\.");
    
    if( nameParts.length == 1 )
      // Globally defined name
      return HL7NAMESPACE + "." + GeneratorUtils.generateCSharpTypeName(Utilities.capitalize(nameParts[0]));
    else
      return HL7NAMESPACE + "." + GeneratorUtils.generateCSharpTypeName(nameParts[0]) +
         "." + GeneratorUtils.generateCSharpTypeName(Utilities.capitalize(nameParts[1]));
  }
	
	
	public static String buildFullyScopedSerializerTypeName( String fullName ) throws Exception
	{		
		String[] nameParts = fullName.split("\\.");
		return GeneratorUtils.generateCSharpTypeName(nameParts[0]) + "Serializer";
	}
	
	public static String generateCSharpEnumMemberName(String name) {
		String result = name;
		
		if (result.equals("<"))
			result = "LessThan";
		else if (result.equals("<="))
			result = "LessOrEqual";
		else if (result.equals(">"))
			result = "GreaterThan";
		else if (result.equals(">="))
			result = "GreaterOrEqual";
		else if (result.equals("="))
			result = "Equal";
		
		if (result.startsWith("-"))
			result = result.replace("-", "Minus");
		
		if (Utilities.isInteger(result))
			result = "N" + result;
		
		result = result.replace("-", "_");
		result = result.replace("+", "Plus"); 
		
		result = Utilities.camelCase(result);
		result = Utilities.capitalize(result);
 
		return result;
	}
	
	
	
	public static String generateCSharpMemberName(ElementDefn member) throws Exception
	{
		String result = Utilities.capitalize(member.getName());
		
		// At this moment, Extension contains both an inherited property extensions,
		// and it redefines extensions for nested extensions, change the name here
		if( result.equals("Extension") && member.getParentType().getName().equals("Extension") )
			result = "NestedExtension";

		// The property "id" on Element is the internal id, give it a nicer name.
		if( member.getName().equals("id") && member.getXmlFormatHint() == XmlFormatHint.ATTRIBUTE )
		  result = "ElementId";
		
		// Pluralize for arrays
		//if( member.isRepeating() ) result += "s";
		
		// An attribute cannot have the same name as a nested type
		for( CompositeTypeDefn composite : member.getParentType().getLocalCompositeTypes() )
		{
		  String csName = generateCSharpTypeName(composite.getName());
			if( csName.equals(result) )
			{
				result += "_";
				break;
			}
		}
		
		// An attribute cannot have the same name as a nested enums
		// (and enums are only generated for codelists)
		for( BindingDefn binding : member.getParentType().getBinding() )
		{
			if( binding.getName().equals(result) && isEnumerableCodeList(binding) )
			{
				result += "_";
				break;
			}
		}

		
		// An attribute cannot have the same name as its enclosing type
		String csName = generateCSharpTypeName(member.getParentType().getName());
		if( result.equals(csName) )
				result += "_";
		
		return result;
	}


		
	/* If we have a combination of types for an element, we should generate
	 * the element to have a type of the most specialized possible supertype.
	 * 
	 * If categories of types are mixed (primitives/composites), the function
	 * will return the appropriate supertype.
	 */
	public static TypeRef getMemberTypeForElement( Definitions defs, ElementDefn elem )
			throws Exception
	{
		if( elem.containsReference() )
			return elem.getType().get(0);	// the element's type is already correct, "Resource"
									
		if( !elem.isPolymorph() )
			return elem.getType().get(0);	// no polymorphism -> the type itself
		else
			return newTypeRef(TypeRef.ELEMENT_TYPE_NAME);   // the polymophic Element type
	}
		
	
	public static boolean isCodeWithCodeList( Definitions defs, TypeRef ref )
	{
		if( ref.getFullBindingRef() == null ) return false;
		
		if( ref.getName().equals("code") )
		{
			BindingDefn bindingDef = defs.findBinding(ref.getFullBindingRef());
			
			return isEnumerableCodeList(bindingDef);
		}
		
		// All other cases
		return false;
	}
	
	public static boolean isEnumerableCodeList( BindingDefn binding )
	{
		if( binding != null )
			return binding.getBinding() == BindingType.CODE_LIST;
		else
			return false;
	}
		
	public static TypeRef newTypeRef(String name)
	{
		TypeRef result = FhirFactory.eINSTANCE.createTypeRef();
		result.setName(name);
		result.setFullName(name);
		
		return result;
	}
	
	public static ElementDefn buildSimpleElementDefn(String name, String type, String definition, int minCard, int maxCard)
  {
    ElementDefn newElem = FhirFactory.eINSTANCE.createElementDefn();
    newElem.setName(name);
    newElem.setMinCardinality(minCard);
    newElem.setMaxCardinality(maxCard);
  
    Annotations elemAnn = FhirFactory.eINSTANCE.createAnnotations();
    elemAnn.setShortDefinition(definition);
    newElem.setAnnotation(elemAnn);

    TypeRef elemTypeRef = newTypeRef(type);
    newElem.getType().add(elemTypeRef);
    
    return newElem;
  }

}
