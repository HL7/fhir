package org.hl7.fhir.tools.implementations.objectivec;
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
import java.beans.Introspector;
import java.util.ArrayList;
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeDefn;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.utilities.Utilities;

/**
 * Utilities used to format attributes/information into objective c formats.
 * 
 * @author Andrew Willison
 * 
 */
public class ObjectiveCUtils {

  public static final String PREFIX = "FHIR";

  /**
   * FHIR type to Objective-C type.
   * 
   * @param name
   *          - FHIR type
   * @return
   * @throws Exception
   */
  public static String mapPrimitiveToType(String name) throws Exception {
    if (name.equals("boolean"))
      return "NSNumber";
    else if (name.equals("integer"))
      return "NSNumber";
    else if (name.equals("decimal"))
      return "NSDecimalNumber";
    else if (name.equals("base64Binary"))
      return "NSData";
    else if (name.equals("instant"))
      return "NSDate";
    else if (name.equals("string"))
      return "NSString";
    else if (name.equals("uri"))
      return "NSString";
    else if (name.equals("code"))
      return "NSString";
    else if (name.equals("oid"))
      return "NSString";
    else if (name.equals("uuid"))
      return "NSString";
    else if (name.equals("sid"))
      return "NSString";
    else if (name.equals("id"))
      return "NSString";
    else if (name.equals("xhtml"))
      return "NSString";
    else if (name.equals("date"))
      return "NSString";
    else if (name.equals("time"))
      return "NSString";
    else if (name.equals("dateTime"))
      return "NSString";
    else
      throw new Exception("Unrecognized primitive " + name);
  }

  /**
   * Open the implementation for the .m file of a class.
   * 
   * @param className
   * @return
   */
  public static String openImplementation(String className) {

    return "@implementation " + generateTypeName(className);
  }

  /**
   * Open the interface for the .h file of a class.
   * 
   * @param className
   * @param type
   * @param delegates
   * @return
   */
  public static String openInterface(String className, String type, List<String> delegates) {

    String value = "@interface " + generateTypeName(className) + " : " + type;

    // Add delegates
    if (delegates != null && delegates.size() != 0) {

      value += "<";

      for (int i = 0; i < delegates.size(); i++) {
        String delegate = delegates.get(i);
        value += delegate;

        if (i + 1 != delegates.size()) {
          value += ",";
        }
      }

      value += ">";
    }

    return value;
  }

  /**
   * Open the private interface for the .m file of a class.
   * 
   * @param className
   * @return
   */
  public static String openInterfacePrivate(String className) {

    String value = "@interface " + generateTypeName(className) + "()";
    return value;
  }

  /**
   * Generate an import line.
   * 
   * @param name
   * @return
   * @throws Exception
   */
  public static String generateImport(String name) throws Exception {

    return "#import \"" + generateTypeName(name) + ".h\"";
  }

  /**
   * Generate a class reference line.
   * 
   * @param name
   * @return
   * @throws Exception
   */
  public static String generateClassReference(String name) throws Exception {

    return "@class " + generateTypeName(name) + ";";
  }

  /**
   * Generate a property.
   * 
   * @param ocType
   *          - Objective-C or custom class type
   * @param name
   *          - variable name
   * @return
   */
  public static String generateProperty(String ocType, String name) {

    String pointer = checkPointerType(ocType);
    if (pointer.length() == 1) {
      return "@property (nonatomic) " + ocType + pointer + name + ";";
    } else {
      return "@property (nonatomic, strong) " + ocType + pointer + name + ";";
    }
  }

  /**
   * Generate a property from a {@link ElementDefn} member.
   * 
   * @param member
   * @return
   */
  public static String generateProperty(ElementDefn member) {

    String name = member.getGeneratorAnnotations().get(ObjectiveCModelGenerator.CLASSGEN_MEMBER_NAME);
    String ocType = member.getGeneratorAnnotations().get(ObjectiveCModelGenerator.CLASSGEN_MEMBER_OCTYPE);

    return generateProperty(ocType, name);
  }

  /**
   * Generate the constant portion in the header file.
   * 
   * @param ocType
   * @param name
   * @return
   */
  public static String generateConstantForHeader(String ocType, String name) {
    return "extern " + ocType + checkPointerType(ocType) + "const " + name + ";";
  }

  /**
   * Generate the constant portion in the methods file.
   * 
   * @param ocType
   * @param name
   * @param value
   * @return
   */
  public static String generateConstantForMethods(String ocType, String name, String value) {
    return ocType + checkPointerType(ocType) + "const " + name + " = " + value + ";";
  }

  /**
   * Generate a method.
   * 
   * @param name
   * @param returnType
   * @param parameter
   * @return
   */
  public static String generateMethod(String name, String returnType, MethodParameter parameter) {

    List<MethodParameter> list = new ArrayList<MethodParameter>();
    list.add(parameter);
    return generateMethod("-", name, returnType, list);
  }

  /**
   * Generate a method.
   * 
   * @param name
   * @param returnType
   * @param parameters
   * @return
   */
  public static String generateMethod(String name, String returnType, List<MethodParameter> parameters) {

    return generateMethod("-", name, returnType, parameters);
  }

  /**
   * Generate a static method.
   * 
   * @param name
   * @param returnType
   * @param parameter
   * @return
   */
  public static String generateStaticMethod(String name, String returnType, MethodParameter parameter) {

    List<MethodParameter> list = null;

    if (parameter != null) {
      list = new ArrayList<MethodParameter>();
      list.add(parameter);
    }

    return generateMethod("+", name, returnType, list);
  }

  /**
   * Generate a static method.
   * 
   * @param name
   * @param returnType
   * @param parameters
   * @return
   */
  public static String generateStaticMethod(String name, String returnType, List<MethodParameter> parameters) {

    return generateMethod("+", name, returnType, parameters);
  }

  /**
   * Generate a method.
   * 
   * @param modifier
   * @param name
   * @param returnType
   * @param parameters
   * @return
   */
  private static String generateMethod(String modifier, String name, String returnType, List<MethodParameter> parameters) {

    String value = modifier + " (" + returnType + checkPointerType(returnType) + ")" + name;

    if (parameters != null && parameters.size() != 0) {

      for (int i = 0; i < parameters.size(); i++) {
        MethodParameter parameter = parameters.get(i);

        value += parameter.methodPortion + ":(" + parameter.type + checkPointerType(parameter.type) + ")" + parameter.name;

        if (i + 1 != parameters.size()) {
          value += " ";
        }
      }
    }
    return value;
  }

  /**
   * Close Interface.
   * 
   * @return
   */
  public static String closeInterface() {

    return "@end";
  }

  /**
   * Close Interface.
   * 
   * @return
   */
  public static String closeImplementation() {

    return "@end";
  }

  /**
   * Check whether the variable requires a pointer.
   * 
   * @param name
   * @return
   */
  public static String checkPointerType(String name) {

    if (name.startsWith(PREFIX) || name.startsWith("NS")) {

      return " *";
    }
    return " ";
  }

  /**
   * Generate enum member name. Prefix 'k' and combines enumType with name.
   * 
   * @param enumType
   * @param name
   * @return
   * @throws Exception
   */
  public static String generateEnumMemberName(String enumType, String name) throws Exception {
    return "k" + GeneratorUtils.generateCSharpTypeName(enumType) + GeneratorUtils.generateCSharpEnumMemberName(name);
  }

  /**
   * Generate enum type name. Prefixes 'k' and uses {@link GeneratorUtils}
   * .generateCSharpTypeName.
   * 
   * @param name
   * @return
   * @throws Exception
   */
  public static String generateEnumTypeName(String name) throws Exception {
    return "k" + GeneratorUtils.generateCSharpTypeName(name);
  }

  /**
   * Generate member name. Decapitalizes first letter.
   * 
   * @param composite
   * @return
   */
  public static String generateMemberName(TypeDefn composite) {
    return Introspector.decapitalize(composite.getName());
  }

  /**
   * Generate member name from {@link ElementDefn} member (originally from
   * CSharp utils).
   * 
   * @param member
   * @return
   */
  public static String generateMemberName(ElementDefn member) {
    String result = Introspector.decapitalize(member.getName());

    // At this moment, Extension contains both an inherited property extensions,
    // and it redefines extensions for nested extensions, change the name here
    if (result.equals("extension") && member.getParentType().getName().equals("extension"))
      result = "nestedExtension";

    // The property "_id" is the internal id, give it a nicer name.
    if (result.equals("_id"))
      result = "localId";

    // Pluralize for arrays
    // if( member.isRepeating() ) result += "s";

    // An attribute cannot have the same name as a nested type
    for (CompositeTypeDefn composite : member.getParentType().getLocalCompositeTypes()) {
      if (composite.getName().equals(result)) {
        result += "_";
        break;
      }
    }

    // An attribute cannot have the same name as a nested enums
    // (and enums are only generated for codelists)
    for (BindingDefn binding : member.getParentType().getBinding()) {
      if (binding.getName().equals(result) && GeneratorUtils.isEnumerableCodeList(binding)) {
        result += "_";
        break;
      }
    }

    // An attribute cannot have the same name as its enclosing type
    if (result.equals(member.getParentType().getFullName()))
      result += "_";

    if (GeneratorUtils.isCSharpReservedWord(result)) {
      result += "_";
    }

    return result;
  }

  /**
   * Generate type name. Prefix with {@link ObjectiveCUtils}.PREFIX, and removes
   * 'HL7.FHIR.MODEL.' and '.'
   * 
   * @param name
   * @return
   */
  public static String generateTypeName(String name) {

    if (name.startsWith("NS")) {
      return name;
    }
    // switch names of resource and base resource
    if (name.equals("Resource"))
      name = "BaseResource";
    if (name.equals("ResourceReference"))
      name = "Resource";
    return PREFIX + Utilities.capitalize(name.replace("/Hl7\\.Fhir\\.Model\\./", "").replaceAll("\\.", ""));
  }

  /**
   * Method Parameter Info class.
   * 
   * @author Andrew Willison
   * 
   */
  public static class MethodParameter {

    public String methodPortion;
    public String type;
    public String name;

    public MethodParameter(String methodPortion, String type, String name) {

      this.methodPortion = methodPortion;
      this.type = type;
      this.name = name;
    }
  }
}
