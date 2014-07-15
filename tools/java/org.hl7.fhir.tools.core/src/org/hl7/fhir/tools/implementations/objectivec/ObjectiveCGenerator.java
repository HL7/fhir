package org.hl7.fhir.tools.implementations.objectivec;

/*
 Copyright (c) 2011-2014, HL7, Inc
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
import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.definitions.ecore.fhir.CompositeTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.ConstrainedTypeDefn;
import org.hl7.fhir.definitions.ecore.fhir.NameScope;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;

/**
 * Objective C generator. Based off the CSharpGenerator (November-2013)
 * 
 * @author Andrew Willison
 * 
 */
public class ObjectiveCGenerator extends BaseGenerator implements PlatformGenerator {

  @Override
  public String getName() {
    return "objectivec";
  }

  @Override
  public String getTitle() {
    return "Objective-C";
  }

  @Override
  public String getDescription() {
    return "Resource definitions, Preliminary XML & Json parsers";
  }

  @Override
  public boolean isECoreGenerator() {
    return true;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)
      throws Exception {

    throw new UnsupportedOperationException("The Obj-C generator uses eCore, not ElementDefn-style definition.");
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {

    char sl = File.separatorChar;
    String modelDir = "Model" + sl;
    String serializersDir = "Serializers" + sl;

    // Create folders
    File f = new OCFile(implDir + modelDir);
    if (!f.exists())
      f.mkdir();
    File s = new OCFile(implDir + serializersDir);
    if (!s.exists())
      s.mkdir();

    List<String> generatedFilenames = new ArrayList<String>();

    // Generate Bindings and Bindings Helper
    // --| Bindings contains global enums
    // --| BindingsHelper contains serializer/parser for all enums

    {
      String enumsFilename = modelDir + ObjectiveCUtils.generateTypeName("Bindings") + ".h";

      new ObjectiveCModelGenerator(definitions).generateGlobalEnumsHeader(definitions.getBinding()).toFile(implDir + enumsFilename);

      generatedFilenames.add(enumsFilename);

      String enumHelperHelperFileName = modelDir + ObjectiveCUtils.generateTypeName("BindingsHelper") + ".h";
      new ObjectiveCModelGenerator(definitions).generateGlobalEnumsHelperHeader(definitions.getBinding()).toFile(implDir + enumHelperHelperFileName);

      generatedFilenames.add(enumHelperHelperFileName);

      String enumHelperMethodsFileName = modelDir + ObjectiveCUtils.generateTypeName("BindingsHelper") + ".m";
      new ObjectiveCModelGenerator(definitions).generateGlobalEnumsHelperImplementation(definitions.getBinding()).toFile(implDir + enumHelperMethodsFileName);

      generatedFilenames.add(enumHelperMethodsFileName);
    }

    // Generate Model Info, contains:
    // --| Allows for converting Fhir Name to Objective-C Class and vice versa
    // --| Stock search params
    // --| Suported resources
    {
      String headerFilename = modelDir + ObjectiveCUtils.generateTypeName("ModelInfo") + ".h";
      new ObjectiveCModelInformationGenerator(definitions).generateInformationHeader().toFile(implDir + headerFilename);
      generatedFilenames.add(headerFilename);

      String methodsFilename = modelDir + ObjectiveCUtils.generateTypeName("ModelInfo") + ".m";
      new ObjectiveCModelInformationGenerator(definitions).generateInformationImplementation().toFile(implDir + methodsFilename);
      generatedFilenames.add(methodsFilename);
    }

    // Create composite types and sub-types (sub-classes).
    // Sub classes are all created as individual files.
    List<CompositeTypeDefn> allComplexTypes = new ArrayList<CompositeTypeDefn>();
    allComplexTypes.addAll(definitions.getLocalCompositeTypes());
    allComplexTypes.addAll(definitions.getResources());

    for (CompositeTypeDefn composite : allComplexTypes) {
      // Generate model for all other classes
      generateCompositeClass(definitions, composite, modelDir, implDir, generatedFilenames);
    }

    // Generate constrained types. Eg. Age, Count, Duration, Disease, Money,
    // etc.
    for (ConstrainedTypeDefn constrained : definitions.getLocalConstrainedTypes()) {
      // Build oc class for constrained type
      String constrainedHeaderFilename = modelDir + ObjectiveCUtils.generateTypeName(constrained.getName()) + ".h";
      new ObjectiveCModelGenerator(definitions).generateConstrainedHeader(constrained).toFile(implDir + constrainedHeaderFilename);
      generatedFilenames.add(constrainedHeaderFilename);

      String constrainedMethodsFilename = modelDir + ObjectiveCUtils.generateTypeName(constrained.getName()) + ".m";
      new ObjectiveCModelGenerator(definitions).generateConstrainedImplementation(constrained).toFile(implDir + constrainedMethodsFilename);
      generatedFilenames.add(constrainedMethodsFilename);

    }

    // Collect all bindings to generate the EnumHelper class
    // EnumHelper Simplifies accessing the BindingsHelper (one method to convert
    // to string/enum)

    List<BindingDefn> allBindings = new ArrayList<BindingDefn>();
    allBindings.addAll(definitions.getBinding());
    for (NameScope ns : definitions.getLocalCompositeTypes()) {
      allBindings.addAll(ns.getBinding());
    }
    for (NameScope ns : definitions.getResources()) {
      allBindings.addAll(ns.getBinding());
    }
    {
      String enumHelperHeaderFilename = modelDir + ObjectiveCUtils.generateTypeName("EnumHelper") + ".h";
      new ObjectiveCModelGenerator(definitions).generateEnumHelperHeader(definitions, allBindings).toFile(implDir + enumHelperHeaderFilename);
      generatedFilenames.add(enumHelperHeaderFilename);

      String enumHelperMethodFilename = modelDir + ObjectiveCUtils.generateTypeName("EnumHelper") + ".m";
      new ObjectiveCModelGenerator(definitions).generateEnumHelperImplemention(definitions, allBindings).toFile(implDir + enumHelperMethodFilename);
      generatedFilenames.add(enumHelperMethodFilename);
    }

    // Generate resource Serializer Order List - order of values in xml/json
    {

      String orderHeaderFilename = serializersDir + ObjectiveCUtils.generateTypeName("SerializerOrder") + ".h";
      new ObjectiveCSerializerGenerator(definitions).generateResourceSerializerOrderHeader().toFile(implDir + orderHeaderFilename);
      generatedFilenames.add(orderHeaderFilename);

      String orderMethodsFilename = serializersDir + ObjectiveCUtils.generateTypeName("SerializerOrder") + ".m";
      new ObjectiveCSerializerGenerator(definitions).generateResourceSerializerOrderImplementation().toFile(implDir + orderMethodsFilename);
      generatedFilenames.add(orderMethodsFilename);
    }

    // Generate resource Serializer Order List - Same list as above and paired
    // with objective-c fhir types
    {
      String orderPairHeaderFilename = serializersDir + ObjectiveCUtils.generateTypeName("SerializerOrderPair") + ".h";
      new ObjectiveCSerializerGenerator(definitions).generateSerializerOrderPairHeader().toFile(implDir + orderPairHeaderFilename);
      generatedFilenames.add(orderPairHeaderFilename);

      String orderPairMethodsFilename = serializersDir + ObjectiveCUtils.generateTypeName("SerializerOrderPair") + ".m";
      new ObjectiveCSerializerGenerator(definitions).generateSerializerOrderPairImplementation().toFile(implDir + orderPairMethodsFilename);
      generatedFilenames.add(orderPairMethodsFilename);

    }

    // last: zip the whole lot up
    ZipGenerator zip = new ZipGenerator(Utilities.path(destDir, getReference(definitions.getVersion())));
    zip.addFolder(Utilities.path(implDir, ""), "", false);
    zip.close();
  }

  
  @Override
  public String getVersion() {
    return "0.01";
  }

  @Override
  public boolean doesCompile() {
    return false;
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) {

    // TODO: Compile is not implemented yet.

    /*
     * String solutionDirectory = Utilities.path(rootDir, "implementations",
     * "objectivec"); String solutionFile = Utilities.path(solutionDirectory,
     * "Hl7.Fhir.ocproj"); DotNetCompileResult result =
     * DotNetFramework.compile(solutionFile, this.logger);
     * 
     * // If result == null, the compile function will have logged the reason
     * if( result == null ) return false;
     * 
     * // If there was an error, print the message else if(result.exitValue !=
     * 0) { logger.log(result.message); return false; }
     * 
     * return addCompiledAssemblyToCsharpZip(rootDir, solutionDirectory);
     */
    return false;
  }

  @Override
  public boolean doesTest() {
    return false;
  }

  @Override
  public void loadAndSave(String rootDir, String sourceFile, String destFile) throws Exception {

  }

  @Override
  public String checkFragments(String rootDir, String fragments, boolean inProcess) throws Exception {
    return "Not supported by Objective-C implementation";
  }

  /**
   * Generate a composite class.
   * 
   * @param definitions
   * @param composite
   * @param modelDir
   * @param implDir
   * @param generatedFilenames
   * @throws Exception
   */
  private void generateCompositeClass(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, CompositeTypeDefn composite, String modelDir,
      String implDir, List<String> generatedFilenames) throws Exception {

    String compositeHeaderFilename = modelDir + ObjectiveCUtils.generateTypeName(composite.getName()) + ".h";
    new ObjectiveCModelGenerator(definitions).generateCompositeHeader(composite).toFile(implDir + compositeHeaderFilename);
    generatedFilenames.add(compositeHeaderFilename);

    String compositeMethodsFilename = modelDir + ObjectiveCUtils.generateTypeName(composite.getName()) + ".m";
    new ObjectiveCModelGenerator(definitions).generateCompositeImplementation(composite).toFile(implDir + compositeMethodsFilename);
    generatedFilenames.add(compositeMethodsFilename);

    // Generate composite subclasses
    if (composite.getLocalCompositeTypes().size() > 0) {
      for (CompositeTypeDefn nested : composite.getLocalCompositeTypes()) {
        generateCompositeClass(definitions, nested, modelDir, implDir, generatedFilenames);
      }
    }
  }
}
