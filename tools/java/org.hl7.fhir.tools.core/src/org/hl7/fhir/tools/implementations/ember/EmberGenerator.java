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

        for (Map.Entry<String, ResourceDefn> entry : namesAndDefinitions.entrySet()) {
            generateEmberModel(entry.getKey(), dirs.get("modelDir"), templateGroup, definitions, "encoding/json");
        }


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

    private static String dasherize(String str){
      String[] r = str.split("(?=\\p{Upper})");
      return String.join("-", r).toLowerCase();
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
