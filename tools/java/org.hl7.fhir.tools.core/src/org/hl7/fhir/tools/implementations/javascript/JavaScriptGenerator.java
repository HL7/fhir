package org.hl7.fhir.tools.implementations.javascript;

/*
Contributed by Mitre Corporation

Copyright (c) 2011-2014, HL7, Inc & The MITRE Corporation
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
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.stringtemplate.v4.ST;

public class JavaScriptGenerator extends BaseGenerator implements PlatformGenerator {
  
  public static char SEPARATOR = File.separatorChar;

  @Override
  public String getName() {
    return "javascript";
  }

  @Override
  public String getTitle() {
    return "JavaScript";
  }

  @Override
  public String getDescription() {
    return "Generates Mongoose models for FHIR resources";
  }

  @Override
  public String getVersion() {
    return "0.1";
  }

  @Override
  public boolean isECoreGenerator() {
    return false;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
    String baseDir = Utilities.path(implDir, "app");
    String modelDir = Utilities.path(baseDir, "models");
    String controllerDir = Utilities.path(baseDir, "controllers");
    Utilities.createDirectory(modelDir);
    Utilities.clearDirectory(modelDir);
    Utilities.createDirectory(controllerDir);
    Utilities.clearDirectory(controllerDir);
    Utilities.copyFileToDirectory(new File(Utilities.path(implDir, "templates", "resource_history.js")), new File(modelDir));

    String genericControllerTemplate = TextFile.fileToString(Utilities.path(implDir, "templates", "generic_controller.js.st"));
    Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
    for (String name : namesAndDefinitions.keySet()) {
      generateMongooseModel(name, modelDir, definitions);
      generateExpressController(name, controllerDir, genericControllerTemplate);
    }
        
    ZipGenerator zip = new ZipGenerator(destDir+getReference(version));
    zip.addFolder(implDir, "mongoose", false);
    zip.close();    
  }
  
  private void generateMongooseModel(String name, String modelDir, Definitions definitions) throws Exception {
    File modelFile = new File(Utilities.path(modelDir, name.toLowerCase() + ".js"));
    MongooseModel model = new MongooseModel(name, definitions, modelFile);
    model.generate();
  }
  
  private void generateExpressController(String name, String controllerDir, String genericControllerTemplate) throws IOException {
    File controllerFile = new File(Utilities.path(controllerDir, name.toLowerCase() + ".js"));
    ST controllerTemplate = new ST(genericControllerTemplate);
    
    controllerTemplate.add("ModelName", name);
    controllerTemplate.add("LowerCaseModelName", name.toLowerCase());
    
    Writer controllerWriter = new BufferedWriter(new FileWriter(controllerFile));
    controllerWriter.write(controllerTemplate.render());
    controllerWriter.flush();
    controllerWriter.close();
  }

  @Override
  public boolean doesCompile() {
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
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    return false;
  }

  @Override
  public String checkFragments(String rootDir, String fragmentsXml, boolean inProcess) throws Exception {
    return null;
  }

}
