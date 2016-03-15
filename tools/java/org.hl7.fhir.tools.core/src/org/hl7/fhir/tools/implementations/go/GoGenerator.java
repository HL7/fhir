package org.hl7.fhir.tools.implementations.go;

/*
Contributed by Mitre Corporation

Copyright (c) 2011-2016, HL7, Inc & The MITRE Corporation
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
import org.hl7.fhir.instance.validation.ValidationMessage;
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

    private static final List<String> UNSUPPORTED_SEARCH_PARAMS = Arrays.asList("_query", "_text", "_content");

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

        Map<String, ResourceDefn> baseResources = definitions.getBaseResources();
        for (String name : baseResources.keySet()) {
            generateMgoModel(name, dirs.get("modelDir"), templateGroup, definitions);
        }

        Map<String, ResourceDefn> namesAndDefinitions = definitions.getResources();
        generateGoRouting(namesAndDefinitions, dirs.get("serverDir"));

        for (Map.Entry<String, ResourceDefn> entry : namesAndDefinitions.entrySet()) {
            generateMgoModel(entry.getKey(), dirs.get("modelDir"), templateGroup, definitions);
        }

        Set<String> resourcesPlusParameters = new HashSet<String>();
        resourcesPlusParameters.addAll(namesAndDefinitions.keySet());
        resourcesPlusParameters.add("Parameters");
        // We need to add in Parameters when we are generating the util file. MapToResource
        // needs to know about the Resource for the Bundle unmarshaling to work correctly
        generateGoUtil(resourcesPlusParameters, dirs.get("modelDir"), templateGroup);
        // We do not need Parameters in the resource helpers or search since it is an odd resource
        // with no web endpoint.
        generateResourceHelpers(namesAndDefinitions.keySet(), dirs.get("modelDir"), templateGroup);
        generateSearchParameterDictionary(definitions, dirs.get("searchDir"), templateGroup);

        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "codeableconcept_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "fhirdatetime.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "constructors.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "mongo_search.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "search_param_types.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "url_query_parser.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "batch_controller.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "config.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "resource_controller.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server_setup.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "smart_auth.go")), new File(dirs.get("serverDir")));
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

    private void generateMgoModel(String name, String modelDir, STGroup templateGroup, Definitions definitions) throws Exception {
        File modelFile = new File(Utilities.path(modelDir, name.toLowerCase() + ".go"));
        MgoModel model = new MgoModel(name, definitions, modelFile, templateGroup);
        model.generate();
    }

    private void generateGoRouting(Map<String, ResourceDefn> namesAndDefinitions, String serverDir) throws IOException {
        File serverFile = new File(Utilities.path(serverDir, "routing.go"));

        BufferedWriter serverWriter = new BufferedWriter(new FileWriter(serverFile));

        serverWriter.write("package server");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("import \"github.com/labstack/echo\"");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("func RegisterController(name string, e *echo.Echo, m []echo.Middleware, config Config) {");
        serverWriter.newLine();
        serverWriter.write("\trc := ResourceController{name}");
        serverWriter.newLine();
        serverWriter.write("\trcBase := e.Group(\"/\" + name)");
        serverWriter.newLine();
        serverWriter.write("\trcBase.Get(\"\", rc.IndexHandler)");
        serverWriter.newLine();
        serverWriter.write("\trcBase.Post(\"\", rc.CreateHandler)");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\trcItem := rcBase.Group(\"/:id\")");
        serverWriter.newLine();
        serverWriter.write("\trcItem.Get(\"\", rc.ShowHandler)");
        serverWriter.newLine();
        serverWriter.write("\trcItem.Put(\"\", rc.UpdateHandler)");
        serverWriter.newLine();
        serverWriter.write("\trcItem.Delete(\"\", rc.DeleteHandler)");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\tif len(m) > 0 {");
        serverWriter.newLine();
        serverWriter.write("\t\trcBase.Use(m...)");
        serverWriter.newLine();
        serverWriter.write("\t}");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\tif config.UseSmartAuth {");
        serverWriter.newLine();
        serverWriter.write("\t\trcBase.Use(SmartAuthHandler(name))");
        serverWriter.newLine();
        serverWriter.write("\t}");
        serverWriter.write("}");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("func RegisterRoutes(e *echo.Echo, config map[string][]echo.Middleware, serverConfig Config) {");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\t// Batch Support");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\te.Post(\"/\", BatchHandler)");
        serverWriter.newLine();
        serverWriter.newLine();
        serverWriter.write("\t// Resources");
        serverWriter.newLine();
        serverWriter.newLine();

        for (String name : namesAndDefinitions.keySet()) {
            serverWriter.write("\tRegisterController(\""+ name + "\", e, config[\"" + name + "\"], serverConfig)");
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

    private void generateResourceHelpers(Set<String> resourceNames, String outputDir, STGroup templateGroup) throws IOException {
        // Sort the resources by name just for consistent (diff-able) output
        ArrayList<String> resourceList = new ArrayList<String>(resourceNames);
        Collections.sort(resourceList);

        HashMap<String, String> pluralMap = new HashMap<String, String>();
        for (String resource : resourceList) {
            pluralMap.put(resource, Utilities.pluralizeMe(resource.toLowerCase()));
        }

        ST utilTemplate = templateGroup.getInstanceOf("resource_helpers.go");
        utilTemplate.add("Resources", resourceList);
        utilTemplate.add("Plurals", pluralMap);

        File outputFile = new File(Utilities.path(outputDir, "resource_helpers.go"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private void generateSearchParameterDictionary(Definitions definitions, String outputDir, STGroup templateGroup) throws IOException {
        ArrayList<ResourceSearchInfo> searchInfos = new ArrayList<ResourceSearchInfo>(definitions.getResources().size());
        for (ResourceDefn defn: definitions.getResources().values()) {
            ResourceSearchInfo searchInfo = new ResourceSearchInfo(defn.getName());
            searchInfo.addAllSearchParams(getSearchParameterDefinitions(definitions, defn));
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

    private List<SearchParam> getSearchParameterDefinitions(Definitions definitions, ResourceDefn resource) {
        ArrayList<SearchParam> params = new ArrayList<SearchParam>();
        for (TypeRef ref: resource.getRoot().getTypes()) {
            if (definitions.getResources().containsKey(ref.getName())) {
                params.addAll(getSearchParameterDefinitions(definitions, definitions.getResources().get(ref.getName())));
            } else if (definitions.getBaseResources().containsKey(ref.getName())) {
                params.addAll(getSearchParameterDefinitions(definitions, definitions.getBaseResources().get(ref.getName())));
            }
        }
        for (SearchParameterDefn p : resource.getSearchParams().values()) {
            if (p.getPaths().isEmpty() && p.getComposites().isEmpty()) {
                // We know we don't support _query, _text, or _content, so don't make a big fuss
                if (! UNSUPPORTED_SEARCH_PARAMS.contains(p.getCode())) {
                    System.err.println("No search paths or composites provided for " + resource.getName() + "/" + p.getCode());
                }
                continue;
            } else if ("_id".equals(p.getCode()) && ! SearchParameterDefn.SearchType.token.equals(p.getType())) {
                // FHIR defines a string-based id search too... but it's for the "internal" id that we don't support right now.
                // It also introduces a notion of multiple param definitions for the same named param -- which we also don't support.
                // So, only allow the expected "token" form of the "_id" param through.
                continue;
            }

            SearchParam param = new SearchParam(p.getCode(), p.getType());
            for (String path: p.getPaths()) {
                try {
                    // Some paths (in bundle parameters) have an indexer at the end.  In that case, we only support
                    // index 0 (which, lucky for us, is all that is needed in DSTU2).  Still, we need to strip the
                    // indexer for the rest of this to work.
                    boolean zeroIndexer = false;
                    if (path.endsWith("(0)")) {
                        path = path.substring(0, path.length() - 3);
                        zeroIndexer = true;
                    }
                    ElementDefn el = resource.getRoot().getElementForPath(path, definitions, "Resolving Search Parameter Path", true);
                    path = enhancePath(definitions, resource, path, zeroIndexer);
                    // Special support for id since we store it as _id (TODO: this probably breaks the notion of the "internal" id)
                    if ("_id".equals(param.getName())) {
                        path = "_id";
                    }
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

            for (String target : p.getWorkingTargets()) {
                param.addTarget(target);
            }
            param.sortTargets();
            params.add(param);
        }
        return params;
    }

    private String enhancePath(Definitions definitions, ResourceDefn resource, String path, boolean zeroIndexer) {
        StringBuilder newPath = new StringBuilder();

        String[] parts = path.split("\\.");
        // Intentionally skip first part (resource name) then detect and mark arrays in path
        for (int i = 1; i < parts.length; i++) {
            try {
                String partialPath = String.join(".", Arrays.copyOfRange(parts, 0, i+1));
                ElementDefn el = resource.getRoot().getElementForPath(partialPath, definitions, "resolving search parameter path", true);
                if (el.getMaxCardinality() > 1) {
                    newPath.append(zeroIndexer ? "[0]" : "[]");
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

        public void addAllSearchParams(Collection<SearchParam> params) {
            searchParams.addAll(params);
        }
    }

    private static class SearchParam {
        private final String name;
        private final SearchParameterDefn.SearchType type;
        private final List<SearchPath> paths;
        private final List<String> composites;
        private final List<String> targets;

        public SearchParam(String name, SearchParameterDefn.SearchType type) {
            this.name = name;
            this.type = type;
            this.paths = new ArrayList<SearchPath>();
            this.composites = new ArrayList<String>();
            this.targets = new ArrayList<String>();
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

        public List<String> getTargets() {
            return targets;
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

        public void sortTargets() {
            Collections.sort(targets);
        }

        public void addPath(SearchPath path) {
            paths.add(path);
        }

        public void addComposite(String composite) {
            composites.add(composite);
        }

        public void addTarget(String target) {
            targets.add(target);
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

    /** Derived from: http://hl7.org/fhir/DSTU2/search.html#table */
    private boolean searchParamTypeSupportsDataType(SearchParameterDefn.SearchType searchType, String dataType) {
        try {
            FHIRType ft = FHIRType.byType(dataType);
            switch (searchType) {
                case composite:
                    // TODO: Support composite
                    break;
                case number:
                    return Arrays.asList(FHIRType.DECIMAL, FHIRType.INTEGER, FHIRType.UNSIGNEDINT, FHIRType.POSITIVEINT).contains(ft);
                case string:
                    return Arrays.asList(FHIRType.STRING, FHIRType.ADDRESS, FHIRType.HUMANNAME).contains(ft);
                case date:
                    return Arrays.asList(FHIRType.DATE, FHIRType.DATETIME, FHIRType.INSTANT, FHIRType.PERIOD, FHIRType.TIMING).contains(ft);
                case quantity:
                    return Arrays.asList(FHIRType.QUANTITY, FHIRType.MONEY, FHIRType.SIMPLEQUANTITY, FHIRType.DURATION, FHIRType.COUNT, FHIRType.DISTANCE, FHIRType.AGE).contains(ft);
                case reference:
                    return FHIRType.REFERENCE == ft;
                case token:
                    return Arrays.asList(FHIRType.BOOLEAN, FHIRType.CODE, FHIRType.STRING, FHIRType.CODEABLECONCEPT, FHIRType.CODING, FHIRType.CONTACTPOINT, FHIRType.IDENTIFIER).contains(ft);
                case uri:
                    return FHIRType.URI == ft;
            }
        } catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public boolean doesCompile() {
        return false;
    }

    @Override
    public boolean compile(String rootDir, List<String> errors, Logger logger, List<ValidationMessage> issues) throws Exception {
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
