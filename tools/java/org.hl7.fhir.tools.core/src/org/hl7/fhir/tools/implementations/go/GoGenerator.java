package org.hl7.fhir.tools.implementations.go;

/*
Contributed by Mitre Corporation

Copyright (c) 2011-2017, HL7, Inc & The MITRE Corporation
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
import java.text.SimpleDateFormat;

import org.hl7.fhir.definitions.model.*;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.dstu3.validation.ValidationMessage;
import org.hl7.fhir.tools.implementations.BaseGenerator;
import org.hl7.fhir.tools.publisher.PlatformGenerator;
import org.hl7.fhir.utilities.Logger;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.ZipGenerator;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;
import org.stringtemplate.v4.STRawGroupDir;
import org.stringtemplate.v4.StringRenderer;

public class GoGenerator extends BaseGenerator implements PlatformGenerator {

    private static final List<String> UNSUPPORTED_SEARCH_PARAMS = Arrays.asList("_query", "_text", "_content", "email", "phone");

    private static final List<SearchParameterDefn.SearchType> UNSUPPORTED_SEARCH_PARAM_TYPES = Arrays.asList(SearchParameterDefn.SearchType.composite);
 
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
    public void generate(Definitions definitions, String destDir, String actualImpl, String implDir, String version, Date genDate, Logger logger, String svnRevision) throws Exception {
        final String basedDir = Utilities.path(actualImpl, "base");

        Map<String, String> dirs = new HashMap<String, String>() {{
            put("authDir", Utilities.path(basedDir, "app", "auth"));
            put("modelDir", Utilities.path(basedDir, "app", "models"));
            put("searchDir", Utilities.path(basedDir, "app", "search"));
            put("serverDir", Utilities.path(basedDir, "app", "server"));
            put("conformanceDir", Utilities.path(basedDir, "app", "conformance"));
            put("configDir", Utilities.path(basedDir, "app", "config"));
        }};

        createDirStructure(dirs);

        STGroup templateGroup = new STRawGroupDir(Utilities.path(basedDir, "templates"));
        templateGroup.registerRenderer(String.class, new StringRenderer());

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
        generateGoRouting(namesAndDefinitions.keySet(), dirs.get("serverDir"), templateGroup);

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
        generateConformanceStatement(definitions, dirs.get("conformanceDir"), templateGroup);
        generateIndexesConfig(definitions, dirs.get("configDir"), templateGroup);

        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "auth", "config.go")), new File(dirs.get("authDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "auth", "heart_auth.go")), new File(dirs.get("authDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "auth", "oauth.go")), new File(dirs.get("authDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "auth", "oidc.go")), new File(dirs.get("authDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "codeableconcept_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "operationoutcome_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference_ext.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "reference.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "fhirdatetime.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "models", "constructors.go")), new File(dirs.get("modelDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "mongo_registry.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "mongo_search.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "registry.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "search_param_types.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "search", "url_query_parser.go")), new File(dirs.get("searchDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "batch_controller.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "bind.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "config.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "data_access.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "mongo_data_access.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "request_logger.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "resource_controller.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "server_setup.go")), new File(dirs.get("serverDir")));
        Utilities.copyFileToDirectory(new File(Utilities.path(basedDir, "static", "server", "mongo_indexes.go")), new File(dirs.get("serverDir")));

        ZipGenerator zip = new ZipGenerator(destDir + getReference(version));
        zip.addFolder(actualImpl, "mgo", false);
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

    private void generateGoRouting(Set<String> resourceNames, String outputDir, STGroup templateGroup) throws IOException {
        // Sort the resources by name just for consistent (diff-able) output
        ArrayList<String> resourceList = new ArrayList<String>(resourceNames);
        Collections.sort(resourceList);

        ST utilTemplate = templateGroup.getInstanceOf("routing.go");
        utilTemplate.add("Resources", resourceList);

        File outputFile = new File(Utilities.path(outputDir, "routing.go"));
        Writer routingWriter = new BufferedWriter(new FileWriter(outputFile));
        routingWriter.write(utilTemplate.render());
        routingWriter.flush();
        routingWriter.close();
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
            searchInfo.addAllSearchParams(getSearchParameterDefinitions(definitions, defn, true));
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

    private void generateConformanceStatement(Definitions definitions, String outputDir, STGroup templateGroup) throws IOException {
        ST utilTemplate = templateGroup.getInstanceOf("capability_statement.json");

        // Generate current ISO8601 datetime
        Date currDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
        String dateString = format.format(currDate);
        utilTemplate.add("Date", dateString);

        // List of resources
        ArrayList<ResourceSearchInfo> searchInfos = new ArrayList<ResourceSearchInfo>(definitions.getResources().size());
        for (ResourceDefn defn: definitions.getResources().values()) {
            ResourceSearchInfo searchInfo = new ResourceSearchInfo(defn.getName());
            searchInfo.addAllSearchParams(getSearchParameterDefinitions(definitions, defn, true));
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
        utilTemplate.add("ResourceSearchInfos", searchInfos);

        File outputFile = new File(Utilities.path(outputDir, "capability_statement.json"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private void generateIndexesConfig(Definitions definitions, String outputDir, STGroup templateGroup) throws IOException {
        ST utilTemplate = templateGroup.getInstanceOf("indexes.conf");        

        ArrayList<ResourceSearchInfo> searchInfos = new ArrayList<ResourceSearchInfo>(definitions.getResources().size());
        for (ResourceDefn defn: definitions.getResources().values()) {
            ResourceSearchInfo searchInfo = new ResourceSearchInfo(defn.getName());
            searchInfo.addAllSearchParams(getSearchParameterDefinitions(definitions, defn, false));
            searchInfos.add(searchInfo);
        }

        // Go through the search infos to create the set of unique required indexes.
        // We do this to simplify the data passed to the template and to avoid duplicates.
        //
        // For example, a duplicate would arise in the following scenario:
        // Account has two search parameters "patient" and "subject" that both reference the 
        // same search path "subject". This would cause the index "accounts.subject.referenceid_1"
        // to be listed twice in the config file unless we test for these redundant search parameters.
        ArrayList<ResourceIndexInfo> indexInfos = new ArrayList<ResourceIndexInfo>();

        for (ResourceSearchInfo searchInfo: searchInfos) {
            ResourceIndexInfo indexInfo = new ResourceIndexInfo(Utilities.pluralizeMe(searchInfo.getName().toLowerCase()));
            
            for (SearchParam searchParam: searchInfo.getSearchParams()) {
                List<SearchPath> paths = searchParam.getPaths();

                if (searchParam.getType().equals(SearchParameterDefn.SearchType.reference) && paths.size() > 0) {
                    String key = paths.get(0).getPath();
                    MgoIndex newIndex = new MgoIndex(key);

                    if (!indexInfo.hasIndex(newIndex)) {
                        indexInfo.addIndex(newIndex);
                    }
                }
            }
            indexInfo.sortIndexes(); // to ensure the final result is deterministic
            indexInfos.add(indexInfo);
        }

        // Sort the resource search infos so that the final result is deterministic
        Collections.sort(indexInfos, new Comparator<ResourceIndexInfo>() {
            @Override
            public int compare(ResourceIndexInfo a, ResourceIndexInfo b) {
                return a.name.compareTo(b.name);
            }
        });
        utilTemplate.add("ResourceIndexInfos", indexInfos);

        File outputFile = new File(Utilities.path(outputDir, "indexes.conf"));
        Writer controllerWriter = new BufferedWriter(new FileWriter(outputFile));
        controllerWriter.write(utilTemplate.render());
        controllerWriter.flush();
        controllerWriter.close();
    }

    private List<SearchParam> getSearchParameterDefinitions(Definitions definitions, ResourceDefn resource, boolean useArrayNotation) {
        ArrayList<SearchParam> params = new ArrayList<SearchParam>();
        for (TypeRef ref: resource.getRoot().getTypes()) {
            if (definitions.getResources().containsKey(ref.getName())) {
                params.addAll(getSearchParameterDefinitions(definitions, definitions.getResources().get(ref.getName()), true));
            } else if (definitions.getBaseResources().containsKey(ref.getName())) {
                params.addAll(getSearchParameterDefinitions(definitions, definitions.getBaseResources().get(ref.getName()), true));
            }
        }
        for (SearchParameterDefn p : resource.getSearchParams().values()) {

            if (UNSUPPORTED_SEARCH_PARAMS.contains(p.getCode())) {
                continue;
            }

            if (UNSUPPORTED_SEARCH_PARAM_TYPES.contains(p.getType())) {
                continue;
            }

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
                    String cleanPath = path;
                    if (path.contains("(0)")) {
                        cleanPath = path.replaceAll("\\(0\\)", "");
                    }
                    ElementDefn el = resource.getRoot().getElementForPath(cleanPath, definitions, "Resolving Search Parameter Path", true, true);
                    path = enhancePath(definitions, resource, path, useArrayNotation);
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
                    System.err.println("Couldn't process search parameter " + p.getCode() + "; path: " + path + "; error: " + e.toString());
                }
            }
            param.sortPaths(); // Sort the path list so that the final result is deterministic

            for (SearchParameterDefn.CompositeDefinition comp : p.getComposites()) {
                param.addComposite(comp.getDefinition());
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

    private String enhancePath(Definitions definitions, ResourceDefn resource, String path, boolean useArrayNotation) {
        StringBuilder newPath = new StringBuilder();

        String[] parts = path.split("\\.");
        // Intentionally skip first part (resource name) then detect and mark arrays in path
        for (int i = 1; i < parts.length; i++) {
            try {
                boolean zeroIndexer = false;
                if (parts[i].endsWith("(0)")) {
                    parts[i] = parts[i].substring(0, parts[i].length() - 3);
                    zeroIndexer = true;
                }
                String partialPath = String.join(".", Arrays.copyOfRange(parts, 0, i+1));
                ElementDefn el = resource.getRoot().getElementForPath(partialPath, definitions, "resolving search parameter path", true, true);

                if (useArrayNotation && el.getMaxCardinality() > 1) {
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

    /*
        ResourceIndexInfo defines a list of indexes to be created for a specific 
        collection in the fhir database. This information is passed to the indexes.conf.st
        template.
    */
    private static class ResourceIndexInfo {
        private final String name;  // pluralized
        private final ArrayList<MgoIndex> indexes;

        public ResourceIndexInfo(String name) {
            this.name = name;
            this.indexes = new ArrayList<MgoIndex>();
        }

        public String getName() {
            return name;
        }

        public ArrayList<MgoIndex> getIndexes() {
            return indexes;
        }

        public boolean hasIndex(MgoIndex testIndex) {
            for (MgoIndex index: indexes) {
                if (index.getName().equals(testIndex.getName())) {
                    return true;
                }
            }
            return false;
        }

        public void addIndex(MgoIndex index) {
            indexes.add(index);
        }

        public void sortIndexes() {
            Collections.sort(indexes, new Comparator<MgoIndex>() {
                @Override
                public int compare(MgoIndex a, MgoIndex b) {
                    return a.name.compareTo(b.name);
                }
            });
        }
    }

    /*
        MgoIndex defines a single index to be created in mongo.
        In the indexes.conf.st template this definition will create an entry like:
        <collection_name>.<index_name>_1
        
        The index name could potentially contain sub-fields too, e.g. "subject.referenceid".
        For now we create all indexes in ascending order (hence the "_1")
    */
    private static class MgoIndex {
        private final String name;

        public MgoIndex(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
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
}
