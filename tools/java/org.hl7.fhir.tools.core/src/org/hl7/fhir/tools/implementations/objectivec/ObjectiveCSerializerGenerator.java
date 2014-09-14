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
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.Definitions;
import org.hl7.fhir.definitions.ecore.fhir.ElementDefn;
import org.hl7.fhir.definitions.ecore.fhir.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.implementations.objectivec.ObjectiveCUtils.MethodParameter;

/**
 * Generates helper methods containing the order that objects should be
 * serialized.
 * 
 * Two Classes are generate:
 * <ul>
 * <li>SerializerOrder - list of members</li>
 * 
 * <li>SerializeOrderPair - pairs member with objective-c type</li>
 * </ul>
 * @author Andrew Willlison
 * 
 */
public class ObjectiveCSerializerGenerator extends GenBlock {

  ObjectiveCModelGenerator rgen;

  private Definitions definitions;

  public Definitions getDefinitions() {
    return definitions;
  }

  public ObjectiveCSerializerGenerator(Definitions defs) {
    definitions = defs;

    rgen = new ObjectiveCModelGenerator(defs);
  }

  /**
   * Generate header for serializer order.
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateResourceSerializerOrderHeader() throws Exception {
    begin();

    // add header
    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    String file = "SerializerOrder";

    // add comments
    ln("/*");
    ln(" * Order of dictionaries");
    ln(" */");

    // add interface
    ln(ObjectiveCUtils.openInterface(file, "NSObject", null));
    ln();

    // add order
    ln(generateMethodSerializerOrder() + ";");
    ln();

    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Generate implementation for serializer order.
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateResourceSerializerOrderImplementation() throws Exception {
    begin();

    String file = "SerializerOrder";

    // add header
    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    // add comments
    ln("/*");
    ln(" * Order of dictionaries");
    ln(" */");
    ln();

    // add imports
    ln(ObjectiveCUtils.generateImport(file));
    ln();

    // add private interface
    ln(ObjectiveCUtils.openInterfacePrivate(file));
    ln();

    // add private methods
    generateMethodsComposite();
    ln();

    ln(ObjectiveCUtils.closeInterface());
    ln();

    // add implementation
    ln(ObjectiveCUtils.openImplementation(file));
    ln();

    // add methods
    generateSingleMethodsCompositeWithContent();
    ln();

    // add order content
    generateMethodSerializeOrderContent();

    ln(ObjectiveCUtils.closeImplementation());

    return end();
  }

  /**
   * Generate composite methods.
   */
  private void generateMethodsComposite() {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : composites) {
      generateMethodComposite(composite);
    }
  }

  /**
   * Generate method for composite.
   * 
   * @param composite
   */
  private void generateMethodComposite(CompositeTypeDefn composite) {

    ln(generateSingleMethodComposite(composite) + ";");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateMethodComposite(nested);
      }
    }
  }

  /**
   * Generate single method for accessing order lists.
   * 
   * @param composite
   * @return
   */
  private String generateSingleMethodComposite(CompositeTypeDefn composite) {

    String typeName = ObjectiveCUtils.generateTypeName(composite.getName());
    return ObjectiveCUtils.generateStaticMethod("orderArrayFor" + typeName, "NSArray", (List<MethodParameter>) null);
  }

  /**
   * Generate method with content.
   * 
   * @throws Exception
   */
  private void generateSingleMethodsCompositeWithContent() throws Exception {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : composites) {
      generateSingleMethodCompositeWithContent(composite);
    }
  }

  /**
   * Generate composite method with content for composite.
   * 
   * @param composite
   * @throws Exception
   */
  private void generateSingleMethodCompositeWithContent(CompositeTypeDefn composite) throws Exception {

    ln(generateSingleMethodComposite(composite));
    bs("{");
    ln("return @[");
    bs("");
    // Generate this classes properties
    for (ElementDefn member : composite.getAllElements()) {
      ln("@\"" + ObjectiveCUtils.generateMemberName(member) + "\",");
    }
    es("];");
    es("}");
    ln();

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateSingleMethodCompositeWithContent(nested);
      }
    }
  }

  /**
   * Generate method for serialize order.
   * 
   * @return
   */
  private String generateMethodSerializerOrder() {

    return ObjectiveCUtils.generateStaticMethod("orderArrayFor", "NSArray", new MethodParameter("Type", "NSString", "className"));
  }

  /**
   * Generate the case statements for the serialize order method.
   */
  private void generateMethodSerializeOrderContent() {

    boolean firstTime = true;

    // add method line
    ln(generateMethodSerializerOrder());
    bs("{");

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    // add case statements
    for (CompositeTypeDefn composite : composites) {

      if (firstTime)
        ln("if");
      else
        ln("else if");

      firstTime = false;
      generateMethodSerializeOrderContentCase(composite);

    }
    ln("else");
    bs();
    ln("@throw [NSException exceptionWithName:@\"Unknown Type\" reason:[NSString stringWithFormat:@\"Encountered unknown type %@\", className] userInfo:nil];");
    es();

    es("}");

  }

  /**
   * Generate serialize order case content for composite.
   * 
   * @param composite
   */
  private void generateMethodSerializeOrderContentCase(CompositeTypeDefn composite) {

    String typeName = ObjectiveCUtils.generateTypeName(composite.getName());
    nl("([className isEqualToString:@\"" + typeName + "\"])");
    bs("{");
    ln("return [self orderArrayFor" + typeName + "];");
    es("}");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        ln("else if");
        generateMethodSerializeOrderContentCase(nested);
      }
    }
  }

  // ----------------------------------------------- Serialize Order Pair

  /**
   * Generate Serializer Order Pair header.
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateSerializerOrderPairHeader() throws Exception {
    begin();

    // add header
    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    String file = "SerializerOrderPair";

    // add comments
    ln("/*");
    ln(" * Order of dictionaries");
    ln(" */");

    // add interface
    ln(ObjectiveCUtils.openInterface(file, "NSObject", null));
    ln();

    // add method
    ln(generateMethodSerializerOrderPair() + ";");
    ln();

    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Create Serializer Order Pair implementation.
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateSerializerOrderPairImplementation() throws Exception {
    begin();

    String file = "SerializerOrderPair";

    // add header
    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    // add comments
    ln("/*");
    ln(" * Order of dictionaries");
    ln(" */");
    ln();

    // add import
    ln(ObjectiveCUtils.generateImport(file));
    ln();

    // add private interface
    ln(ObjectiveCUtils.openInterfacePrivate(file));
    ln();

    // add methods
    generateMethodsCompositePair();
    ln();

    ln(ObjectiveCUtils.closeInterface());
    ln();

    // add implementation
    ln(ObjectiveCUtils.openImplementation(file));
    ln();

    // add method content
    generateSingleMethodsCompositePairWithContent();
    ln();

    // add single method content
    generateMethodSerializerOrderPairContent();

    ln(ObjectiveCUtils.closeImplementation());
    return end();
  }

  /**
   * Generate Method for single order serializer pair method.
   * 
   * @return
   */
  private String generateMethodSerializerOrderPair() {

    return ObjectiveCUtils.generateStaticMethod("orderPairArrayFor", "NSArray", new MethodParameter("Type", "NSString", "fhirOcType"));
  }

  /**
   * Generate methods for composite member pairs.
   */
  private void generateMethodsCompositePair() {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : composites) {
      generateMethodCompositePair(composite);
    }
  }

  /**
   * Generate method for a specific composite member pairs.
   * 
   * @param composite
   */
  private void generateMethodCompositePair(CompositeTypeDefn composite) {

    ln(generateSingleMethodCompositePair(composite) + ";");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateMethodCompositePair(nested);
      }
    }
  }

  /**
   * Generate single method to return list of member/octype pair.
   * 
   * @param composite
   * @return
   */
  private String generateSingleMethodCompositePair(CompositeTypeDefn composite) {

    String typeName = ObjectiveCUtils.generateTypeName(composite.getName());
    return ObjectiveCUtils.generateStaticMethod("orderPairArrayFor" + typeName, "NSArray", (List<MethodParameter>) null);
  }

  /**
   * Generate composite pair lists.
   * 
   * @throws Exception
   */
  private void generateSingleMethodsCompositePairWithContent() throws Exception {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : composites) {
      generateSingleMethodCompositePairWithContent(composite);
    }
  }

  /**
   * Generate the member/octype pair dictionary for a specific composite.
   * 
   * @param composite
   * @throws Exception
   */
  private void generateSingleMethodCompositePairWithContent(CompositeTypeDefn composite) throws Exception {

    ln(generateSingleMethodCompositePair(composite));
    bs("{");
    ln("return @[");
    bs("");
    // Generate this classes properties
    for (ElementDefn member : composite.getAllElements()) {
      ln("@[");
      nl("@\"" + ObjectiveCUtils.generateMemberName(member) + "\",");
      nl("@\"" + generateMemberOcType(composite, member) + "\",");
      nl("],");
    }
    es("];");
    es("}");
    ln();

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateSingleMethodCompositePairWithContent(nested);
      }
    }
  }

  /**
   * Generate objective c member type.
   * 
   * @param context
   * @param member
   * @return
   * @throws Exception
   */
  private String generateMemberOcType(CompositeTypeDefn context, ElementDefn member) throws Exception {

    TypeRef tref = GeneratorUtils.getMemberTypeForElement(getDefinitions(), member);
    String memberType;
    String memberOCType;

    if (member.isPrimitiveValueElement()) {

      // Primitive elements' value property maps directly to a OC type
      memberType = ObjectiveCUtils.mapPrimitiveToType(context.getName());
      memberOCType = memberType.toString();

    } else {

      memberType = tref.getName();
      memberOCType = ObjectiveCUtils.generateTypeName(memberType.toString());
    }

    return memberOCType;
  }

  /**
   * Generate content of single order serialized pair method.
   * 
   */
  private void generateMethodSerializerOrderPairContent() {

    boolean firstTime = true;

    // add method
    ln(generateMethodSerializerOrderPair());
    bs("{");

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    // add case statements.
    for (CompositeTypeDefn composite : composites) {

      if (firstTime)
        ln("if");
      else
        ln("else if");

      firstTime = false;

      // add content
      generateMethodSerializeOrderPairContentCase(composite);

    }
    ln("else");
    bs();
    ln("@throw [NSException exceptionWithName:@\"Unknown Type\" reason:[NSString stringWithFormat:@\"Encountered unknown type %@\", fhirOcType] userInfo:nil];");
    es();

    es("}");

  }

  /**
   * Generate serialize order pair comparison for case.
   * 
   * @param composite
   */
  private void generateMethodSerializeOrderPairContentCase(CompositeTypeDefn composite) {

    String typeName = ObjectiveCUtils.generateTypeName(composite.getName());
    nl("([fhirOcType isEqualToString:@\"" + typeName + "\"])");
    bs("{");
    ln("return [self orderPairArrayFor" + typeName + "];");
    es("}");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        ln("else if");
        generateMethodSerializeOrderPairContentCase(nested);
      }
    }
  }
}
