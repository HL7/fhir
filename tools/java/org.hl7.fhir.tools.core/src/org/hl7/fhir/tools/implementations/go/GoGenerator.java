package org.hl7.fhir.tools.implementations.go;

/*
Contributed by Mitre Corporation

Copyright (c) 2011-2015, HL7, Inc & The MITRE Corporation
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
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.TextFile;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.stringtemplate.v4.ST;

public class GoGenerator extends BaseGenerator implements PlatformGenerator {

  @Override
  public String getName() {
    return "go";
  }

  @Override
  public String getTitle() {
    // TODO Auto-generated method stub
    return "Go";
  }

  @Override
  public String getDescription(String version, String svnRevision) {
    return "Generates mgo models for FHIR resources";
  }

  @Override
  public String getVersion() {
    // TODO Auto-generated method stub
    return "0.1";
  }

  @Override
  public boolean isECoreGenerator() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)
      throws Exception {
    final String basedDir = Utilities.path(implDir, "base");

    Map<String, String> dirs = new HashMap<String, String>() {{
      put("modelDir", Utilities.path(basedDir, "app", "models"));
      put("serverDir", Utilities.path(basedDir, "app", "server"));
    }};

    createDirStructrue(dirs);
    //Utilities.copyDirectory(resourcesDir, basedDir, null);

    Map<String, TypeDefn> typeDefs = definitions.getTypes();
    for(String name: typeDefs.keySet()){
      generateMgoModel(name, dirs.get("modelDir"), definitions);
    }

    Map<String, TypeDefn> infDefs = definitions.getInfrastructure();
    for(String name: infDefs.keySet()){
      generateMgoModel(name, dirs.get("modelDir"), definitions);
    }

    Map<String, TypeDefn> structs = definitions.getStructures();
    for(String name: structs.keySet()){
      generateMgoModel(name, dirs.get("modelDir"), definitions);
    }

    String genericControllerTemplate = TextFile.fileToString(Utilities.path(basedDir, "templates", "generic_controller.go.st"));
    String genericBundleTemplate = TextFile.fileToString(Utilities.path(basedDir, "templates", "generic_bundle.go.st"));
    
    Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
    generateGoRouting(namesAndDefinitions, dirs.get("serverDir"));
    
    for (String name : namesAndDefinitions.keySet()) {
      generateMgoModel(name, dirs.get("modelDir"), definitions, "time");
      generateBundleStructs(name, dirs.get("modelDir"), genericBundleTemplate);
      generateGoController(name, dirs.get("serverDir"), genericControllerTemplate);
    }
    
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference_ext.go")), new File(dirs.get("modelDir")));
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference.go")), new File(dirs.get("modelDir")));
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "fhirdatetime.go")), new File(dirs.get("modelDir")));
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "config.go")), new File(dirs.get("serverDir")));
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server_setup.go")), new File(dirs.get("serverDir")));
    Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server.go")), new File(Utilities.path(basedDir, "app")));

    ZipGenerator zip = new ZipGenerator(destDir+getReference(version));
    zip.addFolder(implDir, "mgo", false);
    zip.close();
  }

  private void createDirStructrue(Map<String, String> dirs) throws IOException {
    for (String dir : dirs.values()) {
      Utilities.createDirectory(Utilities.path(dir));
      Utilities.clearDirectory(Utilities.path(dir));
    }
  }

  private void generateMgoModel(String name, String modelDir, Definitions definitions, String... imports) throws Exception {
    File modelFile = new File(Utilities.path(modelDir, name.toLowerCase() + ".go"));
    MgoModel model = new MgoModel(name, definitions, modelFile, imports);
    model.generate();
  }
  
  private void generateBundleStructs(String name, String modelDir, String genericBundleTemplate) throws IOException {
    File modelFile = new File(Utilities.path(modelDir, name.toLowerCase() + ".go"));
    ST bundleTemplate = new ST(genericBundleTemplate);
    
    bundleTemplate.add("ModelName", name);
    
    Writer bundleWriter = new BufferedWriter(new FileWriter(modelFile, true));
    bundleWriter.write(bundleTemplate.render());
    bundleWriter.flush();
    bundleWriter.close();
  }
  
  private void generateGoController(String name, String controllerDir, String genericControllerTemplate) throws IOException {
    File controllerFile = new File(Utilities.path(controllerDir, name.toLowerCase() + ".go"));
    ST controllerTemplate = new ST(genericControllerTemplate);
    
    boolean searchBySubject = false;
    boolean searchByPatient = false;
    boolean searchByCode = false;
    
    if(name.equals("Condition") || name.equals("Observation") || name.equals("MedicationStatement") || name.equals("Encounter")) {
      searchBySubject = true;
    }
    if(name.equals("MedicationStatement")) {
      searchByPatient = true;
    }
    if(name.equals("Medication")) {
      searchByCode = true;
    }
    
    controllerTemplate.add("searchBySubject", searchBySubject);
    controllerTemplate.add("searchByPatient", searchByPatient);
    controllerTemplate.add("searchByCode", searchByCode);
    controllerTemplate.add("ModelName", name);
    controllerTemplate.add("LowerCaseModelName", name.toLowerCase());
    
    Writer controllerWriter = new BufferedWriter(new FileWriter(controllerFile));
    controllerWriter.write(controllerTemplate.render());
    controllerWriter.flush();
    controllerWriter.close();
  }
  
  private void generateGoRouting(Map<String, ResourceDefn> namesAndDefinitions, String serverDir) throws IOException {
    File serverFile = new File(Utilities.path(serverDir, "routing.go"));
    
    BufferedWriter serverWriter = new BufferedWriter(new FileWriter(serverFile));
    
    serverWriter.write("package server");
    serverWriter.newLine();
    serverWriter.newLine();
    serverWriter.write("import (");
    serverWriter.newLine();
    serverWriter.write("\"github.com/codegangsta/negroni\"");
    serverWriter.newLine();
    serverWriter.write("\"github.com/gorilla/mux\"");
    serverWriter.newLine();
    serverWriter.write(")");
    serverWriter.newLine();
    serverWriter.write("func RegisterRoutes(router *mux.Router, config map[string][]negroni.Handler) {");
    serverWriter.newLine();
    serverWriter.newLine();
     
    for (String name : namesAndDefinitions.keySet()) {
      String lower = name.toLowerCase();
      serverWriter.write(lower + "Base := router.Path(\"/" + name + "\").Subrouter()");
      serverWriter.newLine();
      //serverWriter.write(lower + "Base.Methods(\"GET\").HandlerFunc(" + name + "IndexHandler)");
      serverWriter.write(lower + "Base.Methods(\"GET\").Handler(negroni.New(append(config[\"" + name + "Index\"], negroni.HandlerFunc(" + name + "IndexHandler))...))");
      serverWriter.newLine();
      //serverWriter.write(lower + "Base.Methods(\"POST\").HandlerFunc(" + name + "CreateHandler)");
      serverWriter.write(lower + "Base.Methods(\"POST\").Handler(negroni.New(append(config[\"" + name + "Create\"], negroni.HandlerFunc(" + name + "CreateHandler))...))");
      serverWriter.newLine();
      serverWriter.newLine();
      serverWriter.write(lower + " := router.Path(\"/" + name + "/{id}\").Subrouter()");
      serverWriter.newLine();
      //serverWriter.write(lower + ".Methods(\"GET\").HandlerFunc(" + name + "ShowHandler)");
      serverWriter.write(lower + ".Methods(\"GET\").Handler(negroni.New(append(config[\"" + name + "Show\"], negroni.HandlerFunc(" + name + "ShowHandler))...))");
      serverWriter.newLine();
      //serverWriter.write(lower + ".Methods(\"PUT\").HandlerFunc(" + name + "UpdateHandler)");
      serverWriter.write(lower + ".Methods(\"PUT\").Handler(negroni.New(append(config[\"" + name + "Update\"], negroni.HandlerFunc(" + name + "UpdateHandler))...))");
      serverWriter.newLine();
      //serverWriter.write(lower + ".Methods(\"DELETE\").HandlerFunc(" + name + "DeleteHandler)");
      serverWriter.write(lower + ".Methods(\"DELETE\").Handler(negroni.New(append(config[\"" + name + "Delete\"], negroni.HandlerFunc(" + name + "DeleteHandler))...))");
      serverWriter.newLine();
      serverWriter.newLine();
    }
    
    serverWriter.write("}");
    
    serverWriter.flush();
    serverWriter.close();
  }

  @Override
  public boolean doesCompile() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean doesTest() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void loadAndSave(FolderManager folders, String sourceFile, String destFile) throws Exception {
    // TODO Auto-generated method stub
  }

  @Override
  public String checkFragments(FolderManager folders, String fragmentsXml) throws Exception {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void test(FolderManager folders, Collection<String> names) throws Exception {
    // TODO Auto-generated method stub
  }

  @Override
  public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate,
      Logger logger, String svnRevision) throws Exception {
    // TODO Auto-generated method stub
    
  }

}
