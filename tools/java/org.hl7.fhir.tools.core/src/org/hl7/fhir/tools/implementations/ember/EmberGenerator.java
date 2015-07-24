package org.hl7.fhir.tools.implementations.ember;

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
import java.util.*;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

public class EmberGenerator extends BaseGenerator implements PlatformGenerator {

    @Override
    public String getName() {
        return "ember";
    }

    @Override
    public String getTitle() {
        return "Ember";
    }

    @Override
    public String getDescription(String version, String svnRevision) {
        return "Generates ember models for FHIR resources";
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
    public void generate(Definitions definitions, String destDir, String implDir, String version, Date genDate, Logger logger, String svnRevision)
            throws Exception {
        final String basedDir = Utilities.path(implDir, "base");

        Map<String, String> dirs = new HashMap<String, String>() {{
            put("modelDir", Utilities.path(basedDir, "app", "models"));
            put("serializerDir", Utilities.path(basedDir, "app", "serializers"));
        }};

        createDirStructure(dirs);

        STGroup templateGroup = new STRawGroupDir(Utilities.path(basedDir, "templates"));

        Map<String, TypeDefn> typeDefs = definitions.getTypes();
        for (String name : typeDefs.keySet()) {
            generateEmberModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, TypeDefn> infDefs = definitions.getInfrastructure();
        for (String name : infDefs.keySet()) {
            generateEmberModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, TypeDefn> structs = definitions.getStructures();
        for (String name : structs.keySet()) {
            generateEmberModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
        // generateGoRouting(namesAndDefinitions, dirs.get("serverDir"));

        for (Map.Entry<String, ResourceDefn> entry : namesAndDefinitions.entrySet()) {
            generateEmberModel(entry.getKey(), dirs.get("modelDir"), templateGroup, definitions, "encoding/json");
            // generateGoController(entry.getKey(), entry.getValue(), dirs.get("serverDir"), templateGroup);
        }

        // generateGoUtil(namesAndDefinitions.keySet(), dirs.get("modelDir"), templateGroup);

        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference_ext.go")), new File(dirs.get("modelDir")));
        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference.go")), new File(dirs.get("modelDir")));
        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "fhirdatetime.go")), new File(dirs.get("modelDir")));
        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "config.go")), new File(dirs.get("serverDir")));
        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server_setup.go")), new File(dirs.get("serverDir")));
        // Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server.go")), new File(Utilities.path(basedDir, "app")));

        ZipGenerator zip = new ZipGenerator(destDir + getReference(version));
        zip.addFolder(implDir, "model", false);
        zip.close();
    }

    private void createDirStructure(Map<String, String> dirs) throws IOException {
        for (String dir : dirs.values()) {
            Utilities.createDirectory(Utilities.path(dir));
            Utilities.clearDirectory(Utilities.path(dir));
        }
    }

    private void generateEmberModel(String name, String modelDir, STGroup templateGroup, Definitions definitions, String... imports) throws Exception {
        File modelFile = new File(Utilities.path(modelDir, dasherize(name) + ".js"));
        if ("Element".equals(name)) {
          return;
        }
        EmberModel model = new EmberModel(name, definitions, modelFile, templateGroup, imports);
        model.generate();
    }

    private void generateGoController(String name, ResourceDefn def, String controllerDir, STGroup templateGroup) throws IOException {
        File controllerFile = new File(Utilities.path(controllerDir, name.toLowerCase() + ".go"));
        ST controllerTemplate = templateGroup.getInstanceOf("generic_controller.go");

        boolean searchBySubject = false;
        boolean searchByPatient = false;
        boolean searchByCode = false;

        // This algorithm for generating search controllers is better than before, but should
        // eventually be replaced by a much more comprehensive solution.
        if (def.getRoot().getElementByName("subject") != null) {
            searchBySubject = true;
        } else if (def.getRoot().getElementByName("patient") != null) {
            searchByPatient = true;
        } else if (def.getRoot().getElementByName("code") != null) {
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

    private void generateGoUtil(Set<String> resourceNames, String outputDir, STGroup templateGroup) throws IOException {
        // Sort the resources by name just for consistent (diff-able) output
        ArrayList<String> resourceList = new ArrayList<String>(resourceNames);
        Collections.sort(resourceList);

        ST utilTemplate = templateGroup.getInstanceOf("util.go");
        utilTemplate.add("Resources", resourceList);

        File outputFile = new File(Utilities.path(outputDir, "util.go"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private static String dasherize(String str){
      String[] r = str.split("(?=\\p{Upper})");
      return "".join("-", r).toLowerCase();
    }

    @Override
    public boolean doesCompile() {
        return false;
    }

    @Override
    public boolean compile(String rootDir, List<String> errors, Logger logger) throws Exception {
        return false;
    }

    @Override
    public boolean doesTest() {
        return false;
    }

    @Override
    public void test(FolderManager folders, Collection<String> names) throws Exception {}

    @Override
    public void loadAndSave(FolderManager folders, String sourceFile, String destFile) throws Exception {}

    @Override
    public String checkFragments(FolderManager folders, String fragmentsXml) throws Exception {
        return null;
    }

    @Override
    public void generate(org.hl7.fhir.definitions.ecore.fhir.Definitions definitions, String destDir, String implDir, String version, Date genDate,
                         Logger logger, String svnRevision) throws Exception {}
}
