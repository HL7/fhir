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
import java.util.*;

import org.hl7.fhir.definitions.model.*;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.FolderManager;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;

public class GoGenerator extends BaseGenerator implements PlatformGenerator {

    @Override
    public String getName() {
        return "go";
    }

    @Override
    public String getTitle() {
        return "Go";
    }

    @Override
    public String getDescription(String version, String svnRevision) {
        return "Generates mgo models for FHIR resources";
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
            put("searchDir", Utilities.path(basedDir, "app", "search"));
            put("serverDir", Utilities.path(basedDir, "app", "server"));
        }};

        createDirStructure(dirs);

        STGroup templateGroup = new STRawGroupDir(Utilities.path(basedDir, "templates"));

        Map<String, TypeDefn> typeDefs = definitions.getTypes();
        for (String name : typeDefs.keySet()) {
            generateMgoModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, TypeDefn> infDefs = definitions.getInfrastructure();
        for (String name : infDefs.keySet()) {
            generateMgoModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, TypeDefn> structs = definitions.getStructures();
        for (String name : structs.keySet()) {
            generateMgoModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
        generateGoRouting(namesAndDefinitions, dirs.get("serverDir"));

        for (Map.Entry<String, ResourceDefn> entry : namesAndDefinitions.entrySet()) {
            generateMgoModel(entry.getKey(), dirs.get("modelDir"), templateGroup, definitions, "encoding/json");
            generateGoController(entry.getKey(), dirs.get("serverDir"), templateGroup);
        }

        generateGoUtil(namesAndDefinitions.keySet(), dirs.get("modelDir"), templateGroup);
        generateMongoCollectionNames(namesAndDefinitions.keySet(), dirs.get("searchDir"), templateGroup);
        generateSearchParameterDictionary(definitions, dirs.get("searchDir"), templateGroup);

        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "fhirdatetime.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "mongo_search.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "search_param_types.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "config.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server_setup.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server.go")), new File(Utilities.path(basedDir, "app")));

        ZipGenerator zip = new ZipGenerator(destDir + getReference(version));
        zip.addFolder(implDir, "mgo", false);
        zip.close();
    }

    private void createDirStructure(Map<String, String> dirs) throws IOException {
        for (String dir : dirs.values()) {
            Utilities.createDirectory(Utilities.path(dir));
            Utilities.clearDirectory(Utilities.path(dir));
        }
    }

    private void generateMgoModel(String name, String modelDir, STGroup templateGroup, Definitions definitions, String... imports) throws Exception {
        File modelFile = new File(Utilities.path(modelDir, name.toLowerCase() + ".go"));
        MgoModel model = new MgoModel(name, definitions, modelFile, templateGroup, imports);
        model.generate();
    }

    private void generateGoController(String name, String controllerDir, STGroup templateGroup) throws IOException {
        File controllerFile = new File(Utilities.path(controllerDir, name.toLowerCase() + ".go"));
        ST controllerTemplate = templateGroup.getInstanceOf("generic_controller.go");

        controllerTemplate.add("ModelName", name);
        controllerTemplate.add("CollectionName", getMongoCollectionName(name));
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

    private void generateMongoCollectionNames(Set<String> resourceNames, String outputDir, STGroup templateGroup) throws IOException {
        // Sort the resources by name just for consistent (diff-able) output
        ArrayList<String> resourceList = new ArrayList<String>(resourceNames);
        Collections.sort(resourceList);

        HashMap<String, String> collectionMap = new HashMap<String, String>();
        for (String resource : resourceList) {
            collectionMap.put(resource, getMongoCollectionName(resource));
        }

        ST utilTemplate = templateGroup.getInstanceOf("mongo_collection_names.go");
        utilTemplate.add("Resources", resourceList);
        utilTemplate.add("Collections", collectionMap);

        File outputFile = new File(Utilities.path(outputDir, "mongo_collection_names.go"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private void generateSearchParameterDictionary(Definitions definitions, String outputDir, STGroup templateGroup) throws IOException {
        ArrayList<ResourceSearchInfo> searchInfos = new ArrayList<ResourceSearchInfo>(definitions.getResources().size());
        for (ResourceDefn defn: definitions.getResources().values()) {
            ResourceSearchInfo searchInfo = new ResourceSearchInfo(defn.getName());
            for (SearchParameterDefn p : defn.getSearchParams().values()) {
                if (p.getPaths().isEmpty() && p.getComposites().isEmpty()) {
                    System.err.println("No search paths or composites provided for " + defn.getName() + "/" + p.getCode());
                    continue;
                }

                SearchParam param = new SearchParam(p.getCode(), p.getType());
                for (String path: p.getPaths()) {
                    try {
                        ElementDefn el = defn.getRoot().getElementForPath(path, definitions, "Resolving Search Parameter Path", true);
                        path = enhancePath(definitions, defn, path);
                        // Add each path and type
                        for (TypeRef typeRef : el.getTypes()) {
                            if (el.getTypes().size() > 1) {
                                // There is a bug (at least I think it is a bug) in Observation that results in a
                                // fixed path (like "valueDateTime") having multiple types ("Period" and "dateTime").
                                // The following "if" block fixes that:
                                if (!path.endsWith("[x]")) {
                                    if (!path.toLowerCase().contains(typeRef.getName().toLowerCase())) {
                                        continue;
                                    }
                                }

                                if (searchParamTypeSupportsDataType(param.getType(), typeRef.getName())) {
                                    String fixedPath = path.replace("[x]", Utilities.capitalize(typeRef.getName()));
                                    param.addPath(new SearchPath(fixedPath, typeRef.getName()));
                                }
                            } else {
                                String fixedPath = path.replace("[x]", "");
                                param.addPath(new SearchPath(fixedPath, typeRef.getName()));
                            }
                        }
                    } catch (Exception e) {
                        System.err.println("Couldn't process search parameter " + p.getCode() + " path: " + path);
                    }
                }
                param.sortPaths(); // Sort the path list so that the final result is deterministic

                for (String comp : p.getComposites()) {
                    param.addComposite(comp);
                }
                param.sortComposites();

                searchInfo.addSearchParam(param);
            }
            searchInfo.sortSearchParams(); // Sort the param list so that the final result is deterministic
            searchInfos.add(searchInfo);
        }

        // Sort the resource search infos so that the final result is deterministic
        Collections.sort(searchInfos, new Comparator<ResourceSearchInfo>() {
            @Override
            public int compare(ResourceSearchInfo a, ResourceSearchInfo b) {
                return a.name.compareTo(b.name);
            }
        });

        ST utilTemplate = templateGroup.getInstanceOf("search_parameter_dictionary.go");
        utilTemplate.add("ResourceSearchInfos", searchInfos);

        File outputFile = new File(Utilities.path(outputDir, "search_parameter_dictionary.go"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private String enhancePath(Definitions definitions, ResourceDefn resource, String path) {
        StringBuilder newPath = new StringBuilder();

        String[] parts = path.split("\\.");
        // Intentionally skip first part (resource name) then detect and mark arrays in path
        for (int i = 1; i < parts.length; i++) {
            try {
                String partialPath = String.join(".", Arrays.copyOfRange(parts, 0, i+1));
                ElementDefn el = resource.getRoot().getElementForPath(partialPath, definitions, "resolving search parameter path", true);
                if (el.getMaxCardinality() > 1) {
                    newPath.append("[]");
                }
                newPath.append(parts[i]).append(".");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (newPath.length() > 0) {
            newPath.deleteCharAt(newPath.length()-1);
        }
        return newPath.toString();
    }


    private static class ResourceSearchInfo {
        private final String name;
        private final List<SearchParam> searchParams;

        public ResourceSearchInfo(String name) {
            this.name = name;
            this.searchParams = new ArrayList<SearchParam>();
            addDefaultSearchParams();
        }

        private void addDefaultSearchParams() {
            SearchParam param = new SearchParam("_id", SearchParameterDefn.SearchType.string);
            param.addPath(new SearchPath("_id", "string"));
            searchParams.add(param);
        }

        public String getName() {
            return name;
        }

        public List<SearchParam> getSearchParams() {
            return searchParams;
        }

        public void sortSearchParams() {
            Collections.sort(searchParams, new Comparator<SearchParam>() {
                @Override
                public int compare(SearchParam a, SearchParam b) {
                    int ret = a.name.compareTo(b.name);
                    if (ret == 0) {
                        ret = a.type.compareTo(b.type);
                    }
                    return ret;
                }
            });
        }

        public void addSearchParam(SearchParam param) {
            searchParams.add(param);
        }
    }

    private static class SearchParam {
        private final String name;
        private final SearchParameterDefn.SearchType type;
        private final List<SearchPath> paths;
        private final List<String> composites;

        public SearchParam(String name, SearchParameterDefn.SearchType type) {
            this.name = name;
            this.type = type;
            this.paths = new ArrayList<SearchPath>();
            this.composites = new ArrayList<String>();
        }

        public String getName() {
            return name;
        }

        public SearchParameterDefn.SearchType getType() {
            return type;
        }

        public List<SearchPath> getPaths() {
            return paths;
        }

        public List<String> getComposites() {
            return composites;
        }

        public void sortPaths() {
            Collections.sort(paths, new Comparator<SearchPath>() {
                @Override
                public int compare(SearchPath a, SearchPath b) {
                    int ret = a.path.compareTo(b.path);
                    if (ret == 0) {
                        ret = a.type.compareTo(b.type);
                    }
                    return ret;
                }
            });
        }

        public void sortComposites() {
            Collections.sort(composites);
        }

        public void addPath(SearchPath path) {
            paths.add(path);
        }

        public void addComposite(String composite) {
            composites.add(composite);
        }
    }

    private static class SearchPath {
        private final String path;
        private final String type;

        public SearchPath(String path, String type) {
            this.path = path;
            this.type = type;
        }

        public String getPath() {
            return path;
        }

        public String getType() {
            return type;
        }
    }

    private boolean searchParamTypeSupportsDataType(SearchParameterDefn.SearchType searchType, String dataType) {
        switch (searchType) {
            case composite:
                // TODO: Support composite
                break;
            case number:
                return Arrays.asList("decimal", "integer", "unsignedInt", "positiveInt").contains(dataType);
            case string:
                return Arrays.asList("string", "Address", "HumanName").contains(dataType);
            case date:
                return Arrays.asList("date", "dateTime", "instant", "Period", "Timing").contains(dataType);
            case quantity:
                return Arrays.asList("Quantity", "Money", "SimpleQuantity", "Duration", "Count", "Distance", "Age").contains(dataType);
            case reference:
                return "Reference".equals(dataType);
            case token:
                return Arrays.asList("boolean", "code", "string", "CodeableConcept", "Coding", "ContactPoint", "Identifier").contains(dataType);
            case uri:
                return "uri".equals(dataType);
        }
        return false;
    }

    private String getMongoCollectionName(String resourceName) {
        return Utilities.pluralizeMe(resourceName.toLowerCase());
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
