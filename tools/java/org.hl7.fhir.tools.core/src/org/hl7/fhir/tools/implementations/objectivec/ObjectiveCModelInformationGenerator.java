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
import org.hl7.fhir.definitions.ecore.fhir.ResourceDefn;
import org.hl7.fhir.definitions.ecore.fhir.SearchParameter;
import org.hl7.fhir.definitions.ecore.fhir.SearchType;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.tools.implementations.GeneratorUtils;
import org.hl7.fhir.tools.implementations.objectivec.ObjectiveCUtils.MethodParameter;
import org.hl7.fhir.utilities.Utilities;

/**
 * Creates the ModelInfo.h and ModelInfo.m file containing information on the
 * models.
 * 
 * @author Andrew Willison
 * 
 */
public class ObjectiveCModelInformationGenerator extends GenBlock {

  ObjectiveCModelGenerator rgen;

  private Definitions definitions;

  public Definitions getDefinitions() {
    return definitions;
  }

  /**
   * Instantiate with definitions.
   * 
   * @param defs
   */
  public ObjectiveCModelInformationGenerator(Definitions defs) {
    definitions = defs;
    rgen = new ObjectiveCModelGenerator(defs);
  }

  /**
   * Generate header for ModelInfo.h
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateInformationHeader() throws Exception {
    begin();

    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();
    // ln("using System.Xml;");
    // ln();
    ln("/*");
    ln("* A class with methods to retrieve informationa about the");
    ln("* FHIR definitions based on which this assembly was generated.");
    ln("*/");
    ln(ObjectiveCUtils.openInterface("ModelInfo", "NSObject", null));
    ln();

    // instance access
    ln(ObjectiveCUtils.generateStaticMethod("instance", ObjectiveCUtils.PREFIX + "ModelInfo", (List<MethodParameter>) null) + ";");
    ln();

    // generateSupportedResourcesList(definitions);
    ln(ObjectiveCUtils.generateMethod("supportedResources", "NSArray", (List<MethodParameter>) null) + ";");
    ln();

    // generateVersionInfo(definitions);
    ln(ObjectiveCUtils.generateMethod("version", "NSString", (List<MethodParameter>) null) + ";");
    ln();

    // generateTypeMappings(definitions);
    ln(ObjectiveCUtils.generateMethod("fhirStringToOcType", "NSDictionary", (List<MethodParameter>) null) + ";");
    ln(ObjectiveCUtils.generateMethod("ocTypeToFhirString", "NSDictionary", (List<MethodParameter>) null) + ";");
    ln();

    // generateSearchParams(definitions);
    ln(ObjectiveCUtils.generateMethod("searchParameters", "NSArray", (List<MethodParameter>) null) + ";");

    ln(ObjectiveCUtils.closeInterface());

    return end();
  }

  /**
   * Generate implementation
   * 
   * @return
   * @throws Exception
   */
  public GenBlock generateInformationImplementation() throws Exception {
    begin();

    // add header
    inc(rgen.headerBlock(definitions.getDate(), definitions.getVersion()));
    ln();

    // add imports
    ln(ObjectiveCUtils.generateImport("SearchParamDefinition"));
    ln(ObjectiveCUtils.generateImport("ModelInfo"));
    ln();

    generateImports(definitions);
    ln();

    // add private interface
    ln(ObjectiveCUtils.openInterfacePrivate("ModelInfo"));

    // --| add properties
    ln(ObjectiveCUtils.generateProperty("NSArray", "supportedResources"));
    ln(ObjectiveCUtils.generateProperty("NSString", "version"));
    ln(ObjectiveCUtils.generateProperty("NSDictionary", "fhirStringToOcType"));
    ln(ObjectiveCUtils.generateProperty("NSDictionary", "ocTypeToFhirString"));
    ln(ObjectiveCUtils.generateProperty("NSArray", "searchParameters"));

    // close private interface
    ln(ObjectiveCUtils.closeInterface());
    ln();

    // add implementation
    ln();
    ln(ObjectiveCUtils.openImplementation("ModelInfo"));
    ln();

    // add comment
    ln("/**");
    ln(" * Get the instance of IWCalendarDatasource");
    ln(" */");

    // add instance access
    ln(ObjectiveCUtils.generateStaticMethod("instance", ObjectiveCUtils.generateTypeName("ModelInfo"), (List<MethodParameter>) null));
    bs("{");
    ln("static " + ObjectiveCUtils.PREFIX + "ModelInfo *instance;");
    ln("@synchronized(self)");
    bs("{");
    ln("if (instance == nil)");
    bs("{");
    ln("instance = [[" + ObjectiveCUtils.PREFIX + "ModelInfo alloc] init];");
    es("}");
    ln("return instance;");
    es("}");
    es("}");
    ln();

    // add init
    ln("- (id)init");
    bs("{");
    ln("if(self=[super init])");
    bs("{");
    ln();

    // generate supported resource list
    generateSupportedResourcesList(definitions);
    ln();

    // generate version info
    generateVersionInfo(definitions);
    ln();

    // generate type map (FHIR type to Objective-C FHIR Type) & vice versa
    generateTypeMappings(definitions);
    ln();

    // generate search params
    generateSearchParams(definitions);

    // end init
    ln();
    es("}");
    ln("return self;");
    es("}");
    ln();

    // close implementation
    ln(ObjectiveCUtils.closeImplementation());

    return end();
  }

  /**
   * Generate imports for composites.
   * 
   * @param definitions
   * @throws Exception
   */
  private void generateImports(Definitions definitions) throws Exception {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : composites) {
      printCompositeImport(composite);
    }
  }

  /**
   * Print imports for all composites and sub-composites.
   * 
   * @param composite
   * @throws Exception
   */
  private void printCompositeImport(CompositeTypeDefn composite) throws Exception {

    ln(ObjectiveCUtils.generateImport(composite.getName()));

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        printCompositeImport(nested);
      }
    }
  }

  /**
   * Generate search params for definitions. NOT TESTED, Strictly converted from
   * C#
   * 
   * @param definitions2
   * @throws Exception
   */
  private void generateSearchParams(Definitions definitions2) throws Exception {
    ln("[self setSearchParameters:@[");
    bs();
    for (ResourceDefn resource : definitions.getResources())
      if (resource.isAbstract() == false)
        for (SearchParameter param : resource.getSearch()) {

          ln("[[" + ObjectiveCUtils.PREFIX + "SearchParamDefinition alloc] initWithResource:@\"" + ObjectiveCUtils.generateTypeName(resource.getName()) + "\"");
          nl(" name:@\"" + param.getName() + "\"");
          nl(" description:" + GeneratorUtils.makeCsStringLiteral(param.getDescription()));

          String type = Utilities.capitalize(param.getType().toString());
          nl(" type:" + ObjectiveCUtils.generateEnumMemberName("SearchParamType", type));

          nl(" compositeParams: ");
          if (param.getType() == SearchType.COMPOSITE) {
            nl("@[");
            for (String compositePar : param.getComposite())
              nl("@\"" + compositePar + "\", ");
            nl("]");
          } else {
            nl("nil");
          }

          nl(" path: ");
          if (param.getPath() != null && param.getPath().size() > 0) {
            nl("@[");
            for (String elem : param.getPath())
              nl("@\"" + elem + "\", ");
            nl("]");
          } else {
            nl("nil");
          }

          nl("], ");
        }
    es();
    ln("]];");
  }

  /**
   * Generate version info.
   * 
   * @param definitions
   */
  private void generateVersionInfo(Definitions definitions) {
    ln("[self setVersion:@\"" + definitions.getVersion() + "\"];");
  }

  /**
   * Generate a list of supported resources.
   * 
   * @param definitions
   */
  private void generateSupportedResourcesList(Definitions definitions) {

    ln("[self setSupportedResources:@[");
    bs();
    for (ResourceDefn resource : definitions.getResources())
      if (!resource.isAbstract())
        ln("@\"" + resource.getName() + "\",");
    es();
    ln("]];");
  }

  /**
   * Generate a map of FHIR types to Objective-C FHIR types, and a map of
   * Objective-C FHIR types to FHIR types.
   * 
   * @param definitions
   * @throws Exception
   */
  private void generateTypeMappings(Definitions definitions) throws Exception {

    List<CompositeTypeDefn> composites = new ArrayList<CompositeTypeDefn>();
    composites.addAll(definitions.getLocalCompositeTypes());
    composites.addAll(definitions.getResources());

    ln("[self setFhirStringToOcType:@{");
    bs();
    for (CompositeTypeDefn composite : composites) {
      generateTypeMapingsForTypeA(composite);
    }
    es();
    ln("}];");
    ln();
    ln("[self setOcTypeToFhirString:@{");
    bs();
    for (CompositeTypeDefn composite : composites) {
      generateTypeMapingsForTypeB(composite);
    }
    es();
    ln("}];");
  }

  /**
   * Generate FHIR types to Objective-C FHIR types map entries.
   * 
   * @param composite
   */
  private void generateTypeMapingsForTypeA(CompositeTypeDefn composite) {

    ln("@\"" + composite.getName() + "\" : [" + ObjectiveCUtils.generateTypeName(composite.getName()) + " class],");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateTypeMapingsForTypeA(nested);
      }
    }
  }

  /**
   * Generate Objective-C FHIR types to FHIR types map entries
   * 
   * @param composite
   */
  private void generateTypeMapingsForTypeB(CompositeTypeDefn composite) {

    String ocName = ObjectiveCUtils.generateTypeName(composite.getName());
    ln("@\"" + ocName + "\" : @\"" + composite.getName() + "\",");

    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateTypeMapingsForTypeB(nested);
      }
    }
  }
}
