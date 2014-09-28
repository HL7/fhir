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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.Config;
import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.BindingType;
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
import org.hl7.fhir.tools.implementations.objectivec.ObjectiveCUtils.MethodParameter;
import org.hl7.fhir.utilities.Utilities;

public class ObjectiveCModelGenerator extends GenBlock {
  private Definitions definitions;

  public Definitions getDefinitions() {
    return definitions;
  }

  public ObjectiveCModelGenerator(Definitions defs) {
    definitions = defs;
  }

  /**
   * Generate the header containing the license, version, and generation date.
   * 
   * @param genDate
   * @param version
   * @return
   */
  public GenBlock headerBlock(Date genDate, String version) {
    begin();

    ln("/*");
    ln(Config.FULL_LICENSE_CODE);
    ln(" * Generated on " + Config.DATE_FORMAT().format(genDate));
    nl(" for FHIR v" + version);
    ln(" */");

    return end();
  }

  /**
   * Print imports for required elements.
   * 
   * @param elements
   * @throws Exception
   */
  public void printImports(List<ElementDefn> elements) throws Exception {

    List<String> added = new ArrayList<String>();
    for (ElementDefn element : elements) {

      String name = GeneratorUtils.getMemberTypeForElement(getDefinitions(), element).getName();

      if (!element.isPrimitiveValueElement() && !added.contains(name)) {
        added.add(name);
        ln(ObjectiveCUtils.generateImport(name));
      }
    }
  }

  /**
   * Check if element is a primitive value element.
   * 
   * @param composite
   * @return
   */
  private boolean hasPrimitiveValueElement(CompositeTypeDefn composite) {
    for (ElementDefn element : composite.getElement())
      if (element.isPrimitiveValueElement())
        return true;

    return false;
  }

  /**
   * Generate the header file for the composite.
   * 
   * @param composite
   * @return
   * @throws Exception
   */
  public GenBlock generateCompositeHeader(CompositeTypeDefn composite) throws Exception {
    begin();

    // add header
    headerBlock(getDefinitions().getDate(), getDefinitions().getVersion());

    // add content
    compositeClassHeader(composite);

    return end();
  }

  /**
   * Generate the implementation file for the composite.
   * 
   * @param composite
   * @return
   * @throws Exception
   */
  public GenBlock generateCompositeImplementation(CompositeTypeDefn composite) throws Exception {
    begin();

    // add header
    headerBlock(getDefinitions().getDate(), getDefinitions().getVersion());

    // add content
    compositeClassImplementation(composite);

    return end();
  }

  /**
   * Generate the header file for Bindings.h
   * 
   * @param globalEnums
   * @return
   * @throws Exception
   */
  public GenBlock generateGlobalEnumsHeader(List<BindingDefn> globalEnums) throws Exception {
    begin();

    // add header
    headerBlock(definitions.getDate(), definitions.getVersion());

    // add enums
    ln("");
    writeEnums(globalEnums);
    ln("");

    return end();
  }

  /**
   * Generate the header file for BindingsHelper.h
   * 
   * @param globalEnums
   * @return
   * @throws Exception
   */
  public GenBlock generateGlobalEnumsHelperHeader(List<BindingDefn> globalEnums) throws Exception {
    begin();

    // add header
    headerBlock(definitions.getDate(), definitions.getVersion());

    // add imports
    ln();
    ln(ObjectiveCUtils.generateImport("Bindings"));
    ln();

    // all composites
    List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
    allComplexTypes.addAll(definitions.getLocalCompositeTypes());
    allComplexTypes.addAll(definitions.getResources());

    // add imports for classes with enums
    for (CompositeTypeDefn composite : allComplexTypes) {
      for (BindingDefn binding : composite.getBinding()) {
        if (GeneratorUtils.isEnumerableCodeList(binding)) {
          ln(ObjectiveCUtils.generateImport(composite.getName()));
          break;
        }
      }
    }

    ln();

    // add public interface
    ln(ObjectiveCUtils.openInterface("BindingsHelper", "NSObject", null));

    // add methods
    generateEnumHelperHeader(globalEnums);

    // end interface
    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Generate the implementation file for BindingsHelper.m
   * 
   * @param globalEnums
   * @return
   * @throws Exception
   */
  public GenBlock generateGlobalEnumsHelperImplementation(List<BindingDefn> globalEnums) throws Exception {
    begin();

    // add header
    headerBlock(definitions.getDate(), definitions.getVersion());
    ln();

    // add imports
    ln();
    ln(ObjectiveCUtils.generateImport("BindingsHelper"));

    // open implementation
    ln(ObjectiveCUtils.openImplementation("BindingsHelper"));

    // add methods
    generateEnumHelperImplementation(globalEnums);

    // close implementation
    ln(ObjectiveCUtils.closeImplementation());

    return end();
  }

  /**
   * Generate constrained Header. Eg. Age, Count, Duration, Disease, Money, etc.
   * 
   * @param constrained
   * @return
   * @throws Exception
   */
  public GenBlock generateConstrainedHeader(ConstrainedTypeDefn constrained) throws Exception {
    begin();
    headerBlock(definitions.getDate(), definitions.getVersion());

    // add comments
    ln("/*");
    ln(" * " + constrained.getAnnotations().getShortDefinition());
    ln(" * ");
    ln(" * [FhirComposite(");
    nl("\"" + constrained.getName() + "\"");
    nl(")]");
    ln(" * [Serializable]");
    ln(" */");
    ln();

    // determine type and generate required imports.
    String type = constrained.getConstrainedBaseType().getName();
    if (type == null) {
      type = "NSObject";
    } else {
      ln(ObjectiveCUtils.generateImport(type));
      ln();
      type = ObjectiveCUtils.generateTypeName(type);
    }

    // add interface
    ln(ObjectiveCUtils.openInterface(constrained.getName(), type, null));
    ln("// TODO: Add code to enforce these constraints:");
    for (Invariant inv : constrained.getDetail())
      ln("// * " + inv.getHuman());

    ln(ObjectiveCUtils.closeInterface());
    return end();
  }

  /**
   * Generate constrained Implementation. Eg. Age, Count, Duration, Disease,
   * Money, etc.
   * 
   * @param constrained
   * @return
   * @throws Exception
   */
  public GenBlock generateConstrainedImplementation(ConstrainedTypeDefn constrained) throws Exception {
    begin();
    headerBlock(definitions.getDate(), definitions.getVersion());

    // add comments
    ln("/*");
    ln(" * " + constrained.getAnnotations().getShortDefinition());
    ln(" * ");
    ln(" * [FhirComposite(");
    nl("\"" + constrained.getName() + "\"");
    nl(")]");
    ln(" * [Serializable]");
    ln(" */");
    ln();

    // add imports
    ln(ObjectiveCUtils.generateImport(constrained.getName()));
    ln();

    // add implementation
    ln(ObjectiveCUtils.openImplementation(constrained.getName()));
    ln("// TODO: Add code to enforce these constraints:");
    for (Invariant inv : constrained.getDetail())
      ln("// * " + inv.getHuman());

    ln(ObjectiveCUtils.closeImplementation());
    return end();
  }

  /**
   * Create contents of Composite header.
   * 
   * @param composite
   * @return
   * @throws Exception
   */
  public GenBlock compositeClassHeader(CompositeTypeDefn composite) throws Exception {
    begin();

    // add composite comment
    ln("/*");
    ln(" * " + composite.getAnnotations().getShortDefinition());
    ln(" *");
    if (composite.isComposite())
      ln(" * [FhirComposite(");
    else if (composite.isReference())
      ln(" * [FhirReference(");
    nl("\"" + composite.getName() + "\"");
    nl(")]");
    ln(" * [Serializable]");
    if (composite.isAbstract())
      ln(" * [Abstract]");
    ln(" */");
    ln();

    // Derive from appropriate baseclass
    String type;
    if (composite.getBaseType() != null) {
      type = ObjectiveCUtils.generateTypeName(composite.getBaseType().getName());
      ln(ObjectiveCUtils.generateImport(composite.getBaseType().getName()));
      ln();
    } else {
      type = "NSObject";
    }

    // determine parent class
    {
      String[] nameComponenets = composite.getFullName().split("\\.");
      int count = nameComponenets.length;
      if (count > 1) {
        ln(ObjectiveCUtils.generateImport(nameComponenets[0]));
      }
    }

    // base types only
    if (type == "NSObject") {
      ln(ObjectiveCUtils.generateImport("EnumHelper"));
      ln(ObjectiveCUtils.generateClassReference("ErrorList"));
    }

    ln();

    // Generate class references
    generateClassReferences(composite.getElement());
    ln();

    // Generate Constants
    if (hasPrimitiveValueElement(composite)) {
      PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
      generateExtraPrimitiveMembersConstantAttributesHeader(prim, composite.getName());
      ln();
    }

    // Generate Class
    ln(ObjectiveCUtils.openInterface(composite.getName(), type, null));
    ln();

    // Generate extra members if this type contains a primitive Value member
    if (hasPrimitiveValueElement(composite)) {
      PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
      generateExtraPrimitiveMembersHeader(prim, ObjectiveCUtils.generateTypeName(composite.getName()));
      ln();
    }

    // Generate local bindings
    if (composite.getBinding().size() > 0)
      writeEnums(composite.getBinding());

    // Generate this classes properties
    for (ElementDefn member : composite.getElement())
      printMemberProperty(composite, member);

    // Generate Validate() routine
    ln(ObjectiveCUtils.generateMethod("validate", ObjectiveCUtils.PREFIX + "ErrorList", (List<MethodParameter>) null) + ";");

    ln();
    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Create contents of Composite implementation.
   * 
   * @param composite
   * @return
   * @throws Exception
   */
  public GenBlock compositeClassImplementation(CompositeTypeDefn composite) throws Exception {
    begin();

    ln("/*");
    ln(" * " + composite.getAnnotations().getShortDefinition());
    ln(" */");

    // Generate imports
    ln(ObjectiveCUtils.generateImport(composite.getName()));
    ln();
    printImports(composite.getElement());
    ln();
    ln(ObjectiveCUtils.generateImport("ErrorList"));
    ln();

    // Generate Constants
    if (hasPrimitiveValueElement(composite)) {
      PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
      generateExtraPrimitiveMembersConstantAttributesMethods(prim, composite.getName());
      ln();
    }

    // Generate the class itself
    ln(ObjectiveCUtils.openImplementation(composite.getName()));
    ln();
    // Generate extra members if this type contains a primitive Value member
    if (hasPrimitiveValueElement(composite)) {
      PrimitiveDefn prim = definitions.findPrimitive(composite.getName());
      generateExtraPrimitiveMembersImplementation(prim, ObjectiveCUtils.generateTypeName(composite.getName()));
    }

    // Generate Simple Value Access
    for (ElementDefn member : composite.getElement())
      generateSimpleValueAccess(composite, member);

    // Generate Validate() routine
    printValidationMethod(composite);
    ln();
    ln(ObjectiveCUtils.closeImplementation());

    return end();
  }

  /**
   * Generate class references for any objects used by the class.
   * 
   * @param elements
   * @throws Exception
   */
  public void generateClassReferences(List<ElementDefn> elements) throws Exception {

    List<String> added = new ArrayList<String>();
    for (ElementDefn element : elements) {

      String name = GeneratorUtils.getMemberTypeForElement(getDefinitions(), element).getName();

      if (!element.isPrimitiveValueElement() && !added.contains(name)) {
        added.add(name);
        ln(ObjectiveCUtils.generateClassReference(name));
      }
    }
  }

  /**
   * Generate implementation of validation methods for a composite. TODO.
   * (currently loops infinitely.
   * 
   * @param composite
   */
  private void printValidationMethod(CompositeTypeDefn composite) {

    // TODO

    // String specifier = "override";

    // if( composite.getBaseType() == null ) specifier = "virtual";

    ln(ObjectiveCUtils.generateMethod("validate", ObjectiveCUtils.PREFIX + "ErrorList", (List<MethodParameter>) null));
    bs("{");
    ln(ObjectiveCUtils.PREFIX + "ErrorList *result = [[" + ObjectiveCUtils.PREFIX + "ErrorList alloc] init];");
    ln();

    if (composite.getBaseType() != null) {
      ln("[result addValidation:[super validate]];");
      ln();
    } else {
      ln("// [result addValidationRange:[self validateRules]];");
      ln();
    }

    for (ElementDefn member : composite.getElement()) {
      if (member.isPrimitiveValueElement())
        continue;

      String memberName = member.getGeneratorAnnotations().get(CLASSGEN_MEMBER_NAME);
      String memberType = member.getGeneratorAnnotations().get(CLASSGEN_MEMBER_TYPE);

      ln("if(self." + memberName + " != nil )");
      bs();
      if (member.isRepeating()) {
        ln("for(" + ObjectiveCUtils.generateTypeName(memberType) + " *elem in self." + memberName + ")");
        bs();
        ln("[result addValidationRange:[elem validate]];");
        es();
      } else
        ln("[result addValidationRange:[self." + memberName + " validate]];");
      es();
    }
    ln();
    ln("return result;");
    es("}");
  }

  /**
   * print property for {@link ElementDefn}.
   * 
   * @param context
   * @param member
   * @throws Exception
   */
  private void printMemberProperty(CompositeTypeDefn context, ElementDefn member) throws Exception {

    // add comment
    ln("/*");
    ln(" * " + member.getAnnotation().getShortDefinition());
    ln(" */");

    // StringBuilder is used to pass by reference.
    TypeRef tref;
    StringBuilder memberOCType = new StringBuilder();
    StringBuilder memberType = new StringBuilder();
    StringBuilder memberName = new StringBuilder();

    // Determine the most appropriate FHIR type to use for this
    // (possibly polymorphic) element.
    tref = GeneratorUtils.getMemberTypeForElement(getDefinitions(), member);

    // check if simple member and generate member info (values passed by ref)
    boolean isSimpleElement = generateMemberInformation(context, member, tref, memberOCType, memberType, memberName);

    ln(ObjectiveCUtils.generateProperty(member));
    ln();

    if (isSimpleElement)
      // If this element is of a type that is a FHIR primitive, generate extra
      // helper access methods to get to easily get to the elements Value
      // property.
      generateSimpleValueAccessHeader(member, tref, memberOCType.toString(), memberType.toString(), memberName.toString());
  }

  /**
   * Generate implementation for simple value access.
   * 
   * @param context
   * @param member
   * @throws Exception
   */
  public void generateSimpleValueAccess(CompositeTypeDefn context, ElementDefn member) throws Exception {

    TypeRef tref;
    StringBuilder memberOCType = new StringBuilder();
    StringBuilder memberType = new StringBuilder();
    StringBuilder memberName = new StringBuilder();

    // Determine the most appropriate FHIR type to use for this
    // (possibly polymorphic) element.
    tref = GeneratorUtils.getMemberTypeForElement(getDefinitions(), member);

    boolean isSimpleElement = generateMemberInformation(context, member, tref, memberOCType, memberType, memberName);

    if (isSimpleElement)
      // If this element is of a type that is a FHIR primitive, generate extra
      // helper
      // access methods to get to easily get to the elements Value property.
      generateSimpleValueAccessMethods(member, tref, memberOCType.toString(), memberType.toString(), memberName.toString());
  }

  public final static String CLASSGEN_MEMBER_NAME = "classgen.membername";
  public final static String CLASSGEN_MEMBER_OCTYPE = "classgen.memberoctype";
  public final static String CLASSGEN_MEMBER_TYPE = "classgen.membertype";

  /**
   * Generate Member information.
   * 
   * @param context
   * @param member
   * @param tref
   * @param memberOCType
   *          - pass by ref
   * @param memberType
   *          - pass by ref
   * @param memberName
   *          - pass by ref
   * @return
   * @throws Exception
   */
  private boolean generateMemberInformation(CompositeTypeDefn context, ElementDefn member, TypeRef tref, StringBuilder memberOCType, StringBuilder memberType,
      StringBuilder memberName) throws Exception {

    if (GeneratorUtils.isCodeWithCodeList(getDefinitions(), tref)) {

      // Strongly typed enums use a special Code<T> type
      memberType.append(tref.getName());
      memberOCType.append(ObjectiveCUtils.PREFIX + "Code/*<" + memberType + ">*/");

    } else if (member.isPrimitiveValueElement()) {

      // Primitive elements' value property maps directly to a C# type
      memberType.append(ObjectiveCUtils.mapPrimitiveToType(context.getName()));
      memberOCType.append(memberType.toString());

    } else {

      // normal
      memberType.append(tref.getName());
      memberOCType.append(ObjectiveCUtils.generateTypeName(memberType.toString()));

    }

    // Make NSArray if it is a repeating element
    if (member.getMaxCardinality() == -1) {
      memberOCType.setLength(0);
      memberOCType.append("NSArray/*<" + memberType + ">*/");
    }

    memberName.append(ObjectiveCUtils.generateMemberName(member));

    boolean isSimpleElement = Character.isLowerCase(tref.getName().charAt(0)) && !member.isPrimitiveValueElement();

    if (isSimpleElement)
      memberName.append("Element");

    // store annotations
    member.getGeneratorAnnotations().put(CLASSGEN_MEMBER_NAME, memberName.toString());
    member.getGeneratorAnnotations().put(CLASSGEN_MEMBER_OCTYPE, memberOCType.toString());
    member.getGeneratorAnnotations().put(CLASSGEN_MEMBER_TYPE, memberType.toString());

    return isSimpleElement;
  }

  /**
   * Generate the simple access property for the member.
   * 
   * @param member
   * @param tref
   * @param memberOcType
   * @param memberType
   * @param memberName
   * @throws Exception
   */
  private void generateSimpleValueAccessHeader(ElementDefn member, TypeRef tref, String memberOcType, String memberType, String memberName) throws Exception {

    boolean isList = member.getMaxCardinality() == -1;
    boolean isTypedEnum = GeneratorUtils.isCodeWithCodeList(getDefinitions(), tref);
    String ocType = null;
    String simpleMemberName = ObjectiveCUtils.generateMemberName(member);

    if (isTypedEnum)
      ocType = ObjectiveCUtils.generateEnumTypeName(tref.getBindingRef());
    else
      ocType = ObjectiveCUtils.mapPrimitiveToType(tref.getName());

    if (isList)
      ocType = "NSArray /*<" + ocType + ">*/";

    ln(ObjectiveCUtils.generateProperty(ocType, simpleMemberName));
    ln();
  }

  /**
   * Generate the implementation content for simple value access.
   * 
   * @param member
   * @param tref
   * @param memberOcType
   * @param memberType
   * @param memberName
   * @throws Exception
   */
  private void generateSimpleValueAccessMethods(ElementDefn member, TypeRef tref, String memberOcType, String memberType, String memberName) throws Exception {

    boolean isList = member.getMaxCardinality() == -1;
    boolean isTypedEnum = GeneratorUtils.isCodeWithCodeList(getDefinitions(), tref);
    String simpleMemberName = ObjectiveCUtils.generateMemberName(member);

    if (isTypedEnum)
      generateEnumSimpleAccessContent(tref, isList, simpleMemberName, memberName, memberOcType, memberType);
    else {
      generateSimpleAccessContent(tref, isList, simpleMemberName, memberName, memberOcType, memberType);
    }
    ln();
  }

  /**
   * Generate simple access implementation for enums.
   * 
   * @param tref
   * @param isList
   * @param simpleMemberName
   * @param memberName
   * @param memberOcType
   * @param memberType
   * @throws Exception
   */
  private void generateEnumSimpleAccessContent(TypeRef tref, boolean isList, String simpleMemberName, String memberName, String memberOcType, String memberType)
      throws Exception {

    String ocType = ObjectiveCUtils.generateEnumTypeName(tref.getBindingRef());
    String singleOcType = ocType;

    if (isList) {
      ocType = "NSArray /*<" + ocType + ">*/";
    }

    ln(ObjectiveCUtils.generateMethod(simpleMemberName, ocType, (List<MethodParameter>) null));
    bs("{");
    generateEnumSimpleAccessGetContent(isList, simpleMemberName, singleOcType, memberName, memberOcType, memberType);
    es("}");
    ln();

    ln(ObjectiveCUtils.generateMethod("set", "void", new MethodParameter(Utilities.capitalize(simpleMemberName), ocType, simpleMemberName)));
    bs("{");
    generateEnumSimpleAccessSetContent(isList, simpleMemberName, singleOcType, memberName, memberOcType, memberType);
    es("}");
    ln();
  }

  /**
   * Generate Enum Get method for simple access element member.
   * 
   * @param isList
   * @param simpleMemberName
   * @param singleOcType
   * @param memberName
   * @param memberOcType
   * @param memberType
   */
  private void generateEnumSimpleAccessGetContent(boolean isList, String simpleMemberName, String singleOcType, String memberName, String memberOcType,
      String memberType) {

    if (isList) {

      ln("if(self." + memberName + ")");
      bs("{");
      ln("NSMutableArray *array = [NSMutableArray new];");
      ln("for(" + ObjectiveCUtils.generateTypeName(memberType) + " *elem in self." + memberName + ")");
      bs();
      ln("[array addObject:[NSNumber numberWithInt:[" + ObjectiveCUtils.PREFIX + "EnumHelper parseString:[elem value] enumType:kEnumType"
          + singleOcType.replaceFirst("k", "") + "]]];");
      es();
      ln("return [NSArray arrayWithArray:array];");
      es("}");
      if (isList) {
        ln("return nil;");
      }
    } else {
      ln("return [" + ObjectiveCUtils.PREFIX + "EnumHelper parseString:[self." + memberName + " value] enumType:kEnumType" + singleOcType.replaceFirst("k", "")
          + "];");
    }
  }

  /**
   * Generate Enum Set method for simple access element member.
   * 
   * @param isList
   * @param simpleMemberName
   * @param singleOcType
   * @param memberName
   * @param memberOcType
   * @param memberType
   */
  private void generateEnumSimpleAccessSetContent(boolean isList, String simpleMemberName, String singleOcType, String memberName, String memberOcType,
      String memberType) {

    if (isList) {

      ln("if(" + simpleMemberName + ")");
      bs("{");
      ln("NSMutableArray *array = [NSMutableArray new];");
      ln("for(NSNumber *value in self." + simpleMemberName + ")");
      bs();
      ln("[array addObject:[" + ObjectiveCUtils.PREFIX + "EnumHelper enumToString:[value intValue] enumType:kEnumType" + singleOcType.replaceFirst("k", "")
          + "]];");
      es();
      ln("[self set" + Utilities.capitalize(memberName) + ":[NSArray arrayWithArray:array]];");
      es("}");
      ln("else");
      bs("{");
      ln("[self set" + Utilities.capitalize(memberName) + ":nil];");
      es("}");

    } else {
      ln("[self set" + Utilities.capitalize(memberName) + ":");
      nl("[[" + memberOcType + " alloc] initWithValue:");
      nl("[" + ObjectiveCUtils.PREFIX + "EnumHelper enumToString:" + simpleMemberName + " enumType:kEnumType" + singleOcType.replaceFirst("k", "") + "]]];");
    }
  }

  /**
   * Generate simple access implementation.
   * 
   * @param tref
   * @param isList
   * @param simpleMemberName
   * @param memberName
   * @param memberOcType
   * @param memberType
   * @throws Exception
   */
  private void generateSimpleAccessContent(TypeRef tref, boolean isList, String simpleMemberName, String memberName, String memberOcType, String memberType)
      throws Exception {

    String ocType = ObjectiveCUtils.mapPrimitiveToType(tref.getName());
    String singleOcType = ocType;

    if (isList) {
      ocType = "NSArray /*<" + ocType + ">*/";
    }

    ln(ObjectiveCUtils.generateMethod(simpleMemberName, ocType, (List<MethodParameter>) null));
    bs("{");
    generateSimpleAccessGetContent(isList, simpleMemberName, singleOcType, memberName, memberOcType, memberType);
    es("}");
    ln();

    ln(ObjectiveCUtils.generateMethod("set", "void", new MethodParameter(Utilities.capitalize(simpleMemberName), ocType, simpleMemberName)));
    bs("{");
    generateSimpleAccessSetContent(isList, simpleMemberName, singleOcType, memberName, memberOcType, memberType);
    es("}");
    ln();
  }

  /**
   * Generate Get method for simple access element member.
   * 
   * @param isList
   * @param simpleMemberName
   * @param singleOcType
   * @param memberName
   * @param memberOcType
   * @param memberType
   */
  private void generateSimpleAccessGetContent(boolean isList, String simpleMemberName, String singleOcType, String memberName, String memberOcType,
      String memberType) {

    ln("if(self." + memberName + ")");
    bs("{");
    if (!isList) {
      ln("return [self." + memberName + " value];");
    } else {
      ln("NSMutableArray *array = [NSMutableArray new];");
      ln("for(" + ObjectiveCUtils.generateTypeName(memberType) + " *elem in self." + memberName + ")");
      bs();
      ln("[array addObject:[elem value]];");
      es();
      ln("return [NSArray arrayWithArray:array];");
    }
    es("}");
    ln("return nil;");
  }

  /**
   * Generate Set method for simple access element member.
   * 
   * @param isList
   * @param simpleMemberName
   * @param singleOcType
   * @param memberName
   * @param memberOcType
   * @param memberType
   */
  private void generateSimpleAccessSetContent(boolean isList, String simpleMemberName, String singleOcType, String memberName, String memberOcType,
      String memberType) {

    ln("if(" + simpleMemberName + ")");
    bs("{");
    if (!isList) {
      ln("[self set" + Utilities.capitalize(memberName) + ":[[" + memberOcType + " alloc] initWithValue:" + simpleMemberName + "]];");
    } else {
      ln("NSMutableArray *array = [NSMutableArray new];");
      ln("for(" + singleOcType + " *value in " + simpleMemberName + ")");
      bs();
      ln("[array addObject:[[" + ObjectiveCUtils.generateTypeName(memberType) + " alloc] initWithValue:value]];");
      es();
      ln("[self set" + Utilities.capitalize(memberName) + ":[NSArray arrayWithArray:array]];");
    }
    es("}");
    ln("else");
    bs("{");
    ln("[self set" + Utilities.capitalize(memberName) + ":nil];");
    es("}");
  }

  /**
   * Loops through bindings to find global enums.
   * 
   * @param bindings
   * @return
   * @throws Exception
   */
  public GenBlock writeEnums(List<BindingDefn> bindings) throws Exception {
    begin();

    for (BindingDefn binding : bindings) {
      if (GeneratorUtils.isEnumerableCodeList(binding)) {
        printEnum(binding);
      }
    }

    return end();
  }

  /**
   * Print enum definition.
   * 
   * @param binding
   * @return print the enum for a binding.
   * @throws Exception
   */
  public GenBlock printEnum(BindingDefn binding) throws Exception {
    begin();

    // Comments
    ln("/*");
    ln(" * " + binding.getDefinition());
    ln(" */");

    // create enum
    ln("typedef enum ");
    bs("{");
    for (DefinedCode code : binding.getCode()) {
      String definition = code.getDefinition();

      // enum name
      ln(ObjectiveCUtils.generateEnumMemberName(binding.getName(), code.getCode()) + ",");

      if (definition != null)
        nl(" // " + code.getDefinition());
    }
    // close enum
    es("} " + ObjectiveCUtils.generateEnumTypeName(binding.getName()) + ";");
    ln();

    return end();
  }

  /**
   * Generate enum Handling methods for the header file.
   * 
   * @param bindings
   * @return
   * @throws Exception
   */
  private GenBlock generateEnumHelperHeader(List<BindingDefn> bindings) throws Exception {
    begin();

    // search for Enums in Bindings
    for (BindingDefn binding : bindings) {
      if (GeneratorUtils.isEnumerableCodeList(binding)) {
        // generate parse/string methods to convert enums
        printHeaderMethodsForEnumConversion(binding);
      }
    }

    // search for Enums in all Composities
    List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
    allComplexTypes.addAll(definitions.getLocalCompositeTypes());
    allComplexTypes.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : allComplexTypes) {
      for (BindingDefn binding : composite.getBinding()) {
        if (GeneratorUtils.isEnumerableCodeList(binding)) {
          // generate parse/string methods to convert enums
          printHeaderMethodsForEnumConversion(binding);
        }
      }
    }

    return end();
  }

  /**
   * Generate enum Handling methods for the implementation file.
   * 
   * @param bindings
   * @return
   * @throws Exception
   */
  private GenBlock generateEnumHelperImplementation(List<BindingDefn> bindings) throws Exception {
    begin();

    // generate methods for bindings
    for (BindingDefn binding : bindings) {
      if (GeneratorUtils.isEnumerableCodeList(binding)) {
        generateMethodsForEnumConversion(binding);
      }
    }

    List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
    allComplexTypes.addAll(definitions.getLocalCompositeTypes());
    allComplexTypes.addAll(definitions.getResources());

    // generate methods for composites
    for (CompositeTypeDefn composite : allComplexTypes) {
      for (BindingDefn binding : composite.getBinding()) {
        if (GeneratorUtils.isEnumerableCodeList(binding)) {
          generateMethodsForEnumConversion(binding);
        }
      }
    }

    return end();
  }

  /**
   * Print methods for converting enums formatted for header files.
   * 
   * @param binding
   * @throws Exception
   */
  private void printHeaderMethodsForEnumConversion(BindingDefn binding) throws Exception {
    String name = binding.getName();

    ln();
    ln(ObjectiveCUtils.generateStaticMethod("parse" + name, ObjectiveCUtils.generateEnumTypeName(name), new MethodParameter("String", "NSString", "value"))
        + ";");
    ln(ObjectiveCUtils.generateStaticMethod("string", "NSString", new MethodParameter(name, ObjectiveCUtils.generateEnumTypeName(name), "name")) + ";");
    ln();
  }

  /**
   * Print methods for converting enums formated for implementation files
   * 
   * @param binding
   * @return
   * @throws Exception
   */
  private GenBlock generateMethodsForEnumConversion(BindingDefn binding) throws Exception {
    begin();

    String name = binding.getName();
    String typeName = ObjectiveCUtils.generateEnumTypeName(name);

    // add comment
    ln("/*");
    ln(" * Conversion of " + typeName + "from string");
    ln(" */");

    // add static method to parse
    ln(ObjectiveCUtils.generateStaticMethod("parse" + name, typeName, new MethodParameter("String", "NSString", "value")));
    bs("{");

    // create temp variable
    ln(typeName + " result;");
    ln();

    // evaluate statements to find enum value
    printValueParseCases(binding);
    ln();

    // return result
    ln("return result;");
    es("}");
    ln();

    // add comments
    ln("/*");
    ln(" * Conversion of " + binding.getName() + "to string");
    ln(" */");

    // add static method to convert to string
    ln(ObjectiveCUtils.generateStaticMethod("string", "NSString", new MethodParameter(name, typeName, "name")));
    bs("{");

    // evaluate statements to find string value
    printValueToStringCases(binding);

    es("}");

    return end();
  }

  /**
   * Print if, else if, else structure to evaluate strings for enum types.
   * 
   * @param binding
   * @throws Exception
   */
  private void printValueParseCases(BindingDefn binding) throws Exception {
    boolean isFirstClause = true;

    // print for each case
    for (DefinedCode code : binding.getCode()) {

      if (!isFirstClause)
        ln("else ");
      else
        ln();
      isFirstClause = false;

      nl("if( [value isEqualToString:@");
      nl("\"" + code.getCode() + "\"])");
      bs();
      ln("result = " + ObjectiveCUtils.generateEnumMemberName(binding.getName(), code.getCode()));
      nl(";");
      es();
    }
    ln("else");
    bs();
    ln("@throw [NSException exceptionWithName:@\"Unrecognized\" reason:@\"Unrecognized ");
    nl(ObjectiveCUtils.generateEnumTypeName(binding.getName()) + "\" userInfo:nil];");
    es();
  }

  /**
   * Print if, else if, else structure to evaluate enum types for strings.
   * 
   * @param binding
   * @throws Exception
   */
  private void printValueToStringCases(BindingDefn binding) throws Exception {
    boolean isFirstClause = true;

    // print for each case
    for (DefinedCode code : binding.getCode()) {
      if (!isFirstClause)
        ln("else ");
      else
        ln();

      isFirstClause = false;

      nl("if( name==");
      nl(ObjectiveCUtils.generateEnumMemberName(binding.getName(), code.getCode()));
      nl(" )");
      bs();
      ln("return @");
      nl("\"" + code.getCode() + "\";");
      es();
    }
    ln("else");
    bs();
    ln("@throw [NSException exceptionWithName:@\"Unrecognized\" reason:@\"Unrecognized ");
    nl(ObjectiveCUtils.generateEnumTypeName(binding.getName()) + "\" userInfo:nil];");
    es();
  }

  /**
   * Generate pattern the value must be conformed to; for the header.
   * 
   * @param primitive
   * @param compositeName
   */
  private void generateExtraPrimitiveMembersConstantAttributesHeader(PrimitiveDefn primitive, String compositeName) {

    if (primitive.getPattern() != null) {
      ln("// Must conform to the pattern ");
      nl("\"" + primitive.getPattern() + "\"");
      ln(ObjectiveCUtils.generateConstantForHeader("NSString", "PATTERN_" + compositeName.toUpperCase()));
      ln();
    }
  }

  /**
   * Generate pattern the value must conform to; for the implementation.
   * 
   * @param primitive
   * @param compositeName
   */
  private void generateExtraPrimitiveMembersConstantAttributesMethods(PrimitiveDefn primitive, String compositeName) {

    if (primitive.getPattern() != null) {
      ln(ObjectiveCUtils.generateConstantForMethods("NSString", "PATTERN_" + compositeName.toUpperCase(), "@\"" + primitive.getPattern().replace("\\", "\\\\")
          + "\""));
    }
  }

  /**
   * Generate init method for primitive values. (not sure how useful this is
   * right now).
   * 
   * @param primitive
   * @param className
   * @return
   * @throws Exception
   */
  public GenBlock generateExtraPrimitiveMembersHeader(PrimitiveDefn primitive, String className) throws Exception {

    begin();

    String ocPrimitive = ObjectiveCUtils.mapPrimitiveToType(primitive.getName());

    // Generate constructor, taking one parameter - the primitive value
    ln("- (id)initWithValue:(" + ocPrimitive + ObjectiveCUtils.checkPointerType(ocPrimitive) + ")value;");

    return end();
  }

  /**
   * Add init implementation for primitive member.
   * 
   * @param primitive
   * @param className
   * @return
   * @throws Exception
   */
  public GenBlock generateExtraPrimitiveMembersImplementation(PrimitiveDefn primitive, String className) throws Exception {

    String ocPrimitive = ObjectiveCUtils.mapPrimitiveToType(primitive.getName());
    // boolean isNullablePrimitive = ocPrimitive.startsWith("NS");

    begin();

    // Generate init, taking one parameter - the primitive value
    ln("- (id)initWithValue:(" + ocPrimitive + ObjectiveCUtils.checkPointerType(ocPrimitive) + ")value");
    bs("{");
    ln("if(self=[super init])");
    bs("{");
    ln();
    ln("[self setValue:value];");
    ln();
    es("}");
    ln("return self;");
    es("}");
    ln();

    // Generate empty constructor

    // ! NOTE ! commented out in the C# version

    // Generate the cast from a C# primitive to the Fhir primitive
    // ln("public static implicit operator ");
    // nl(className);
    // nl("(" + csharpPrimitive + " value)");
    // bs("{");
    // ln("if(value == null)");
    // ln("  return null;");
    // ln("else");
    // ln("  return new " ); nl(className + "(value);");
    // es("}");
    // ln();

    // Generate the cast from the Fhir primitive to the C# primitive
    // This is an explicit cast because you'll lose information about
    // dataAbsentReasons, refid, extensions
    // ln("public static explicit operator ");
    // nl(csharpPrimitive);
    // nl("(" + className + " value)");
    // bs("{");
    // ln("if(value != null)");
    // ln("  return value.Value;");
    // ln("else");
    // ln("  return null;");
    // es("}");
    // ln();

    // If the FhirPrimitive represents data using a C# nullable
    // primitive, generate another cast from the FhirPrimitive to the
    // non-nullable C# primitive.
    // if( isNullablePrimitive )
    // {
    // String nonNullablePrimitive = csharpPrimitive.substring(0,
    // csharpPrimitive.length()-1);
    //
    // ln("public static explicit operator ");
    // nl(nonNullablePrimitive);
    // nl("(" + className + " source)");
    // bs("{");
    // ln("if(source != null && source.Value != null)");
    // ln("  return source.Value.Value;");
    // ln("else");
    // ln("  throw new InvalidCastException();");
    // es("}");
    // }

    return end();
  }

  /**
   * Generate header for Enum Helper.
   * 
   * @param definitions
   * @param enums
   * @return
   * @throws Exception
   */
  public GenBlock generateEnumHelperHeader(Definitions definitions, List<BindingDefn> enums) throws Exception {
    List<BindingDefn> enumerableLists = new ArrayList<BindingDefn>();

    begin();

    // Only bindings that are truly enumerable ('complete' codelists),
    // can be turned into enums, the rest remain Code.
    for (BindingDefn enumeration : enums)
      if (GeneratorUtils.isEnumerableCodeList(enumeration))
        enumerableLists.add(enumeration);

    // add header
    inc(headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    // add imports
    ln(ObjectiveCUtils.generateImport("Bindings"));
    ln();

    // add enums
    generateEnumTypesList();
    ln();

    // add interface
    ln(ObjectiveCUtils.openInterface("EnumHelper", "NSObject", null));
    ln();

    // add helper method to parse enum
    ln(generateEnumHelperParseMethod() + ";");
    ln();

    // add helper method to serialize enum
    ln(generateEnumHelperStringMethod() + ";");
    ln();

    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Generate enum listing all enums.
   * 
   * @throws Exception
   */
  private void generateEnumTypesList() throws Exception {

    // add comments
    ln("/*");
    ln(" * List of enums types");
    ln(" */");

    // add enum
    ln("typedef enum ");
    bs("{");

    // add enumerable binding value to list
    enumsNameForBinding(definitions.getBinding());

    // composite enums
    List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
    allComplexTypes.addAll(definitions.getLocalCompositeTypes());
    allComplexTypes.addAll(definitions.getResources());

    // add composite enums to list
    for (CompositeTypeDefn composite : allComplexTypes) {
      generateEnumTypeListComposite(composite);
    }

    // name enum
    es("} kEnumType;");
  }

  /**
   * Print enum name for binding of a composite.
   * 
   * @param composite
   * @throws Exception
   */
  private void generateEnumTypeListComposite(CompositeTypeDefn composite) throws Exception {

    enumsNameForBinding(composite.getBinding());

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateEnumTypeListComposite(nested);
      }
    }
  }

  /**
   * Print enum name for binding when binding is an enumerable code list.
   * 
   * @param bindings
   * @throws Exception
   */
  private void enumsNameForBinding(List<BindingDefn> bindings) throws Exception {
    for (BindingDefn binding : bindings) {
      if (GeneratorUtils.isEnumerableCodeList(binding)) {
        ln(ObjectiveCUtils.generateEnumMemberName("EnumType", binding.getName()) + ",");
      }
    }
  }

  /**
   * Generate Enum Helper parse method.
   * 
   * @return
   */
  public String generateEnumHelperParseMethod() {
    List<MethodParameter> params = new ArrayList<MethodParameter>();
    params.add(new MethodParameter("String", "NSString", "value"));
    params.add(new MethodParameter("enumType", "kEnumType", "enumType"));

    return ObjectiveCUtils.generateStaticMethod("parse", "int", params);
  }

  /**
   * Generate Enum Helper serialize method.
   * 
   * @return
   */
  public String generateEnumHelperStringMethod() {
    List<MethodParameter> params = new ArrayList<MethodParameter>();
    params.add(new MethodParameter("", "int", "value"));
    params.add(new MethodParameter("enumType", "kEnumType", "enumType"));

    return ObjectiveCUtils.generateStaticMethod("enumToString", "NSString", params);
  }

  /**
   * Generate implementation for Enum Helper.
   * 
   * @param definitions
   * @param enums
   * @return
   * @throws Exception
   */
  public GenBlock generateEnumHelperImplemention(Definitions definitions, List<BindingDefn> enums) throws Exception {
    List<BindingDefn> enumerableLists = new ArrayList<BindingDefn>();

    begin();

    // Only bindings that are truly enumerable ('complete' codelists),
    // can be turned into enums, the rest remain Code.
    for (BindingDefn enumeration : enums)
      if (GeneratorUtils.isEnumerableCodeList(enumeration))
        enumerableLists.add(enumeration);

    // add header
    inc(headerBlock(definitions.getDate(), definitions.getVersion()));

    // add imports
    ln(ObjectiveCUtils.generateImport("EnumHelper"));
    ln(ObjectiveCUtils.generateImport("BindingsHelper"));
    ln();

    // add implementation
    ln(ObjectiveCUtils.openImplementation("EnumHelper"));

    // add parse method
    ln(generateEnumHelperParseMethod());
    bs("{");
    enumParseCases(enumerableLists);
    es("}");
    ln();

    // and serialize method
    ln(generateEnumHelperStringMethod());
    bs("{");
    enumToStringCases(enumerableLists);
    es("}");

    ln(ObjectiveCUtils.closeImplementation());
    return end();
  }

  /**
   * Add cases to parse on the Enum Helper.
   * 
   * @param enums
   * @throws Exception
   */
  private void enumParseCases(List<BindingDefn> enums) throws Exception {
    boolean first = true;

    // for each enum
    for (BindingDefn enu : enums) {
      if (enu.getBinding() == BindingType.CODE_LIST) {
        enumParseCase(enu, first);
        first = false;
      }
    }
    ln("else");
    bs("{");
    ln("@throw [NSException exceptionWithName:@\"ArgumentException\" reason:@\"Unrecognized value \" userInfo:nil];");
    es("}");
    ln();
  }

  /**
   * Determine parse method to call on bindings helper for the Enum Helper.
   * 
   * @param enu
   * @param first
   * @throws Exception
   */
  private void enumParseCase(BindingDefn enu, boolean first) throws Exception {

    if (first)
      ln("if");
    else
      ln("else if");

    nl("(enumType == " + ObjectiveCUtils.generateEnumMemberName("EnumType", enu.getName()) + ")");
    bs("{");
    ln("return [" + ObjectiveCUtils.PREFIX + "BindingsHelper parse" + enu.getName() + "String:value];");
    es("}");
  }

  /**
   * Add cases to serialize the Enum Helper.
   * 
   * @param enums
   * @throws Exception
   */
  private void enumToStringCases(List<BindingDefn> enums) throws Exception {
    boolean first = true;

    for (BindingDefn enu : enums) {
      if (enu.getBinding() == BindingType.CODE_LIST) {
        enumToStringCase(enu, first);
        first = false;
      }
    }
    ln("else");
    bs();
    ln("@throw [NSException exceptionWithName:@\"ArgumentException\" reason:@\"Unrecognized enumeration \" userInfo:nil];");
    es();

    ln();
  }

  /**
   * Determine serialize method to call on bindings helper for the Enum Helper.
   * 
   * @param enu
   * @param first
   * @throws Exception
   */
  private void enumToStringCase(BindingDefn enu, boolean first) throws Exception {

    if (first)
      ln("if");
    else
      ln("else if");

    String enumType = ObjectiveCUtils.generateEnumMemberName("EnumType", enu.getName());

    nl("(enumType == " + enumType + ")");
    bs();
    ln("return ");
    nl("[" + ObjectiveCUtils.PREFIX + "BindingsHelper string" + enu.getName() + ":value];");
    es();
  }
}
