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
import java.io.Writer;
import java.util.*;

import org.hl7.fhir.definitions.model.*;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.hl7.fhir.utilities.Utilities;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class MgoModel {
    private String name;
    private File outputFile;
    private STGroup templateGroup;
    private Definitions definitions;

    public MgoModel(String name, Definitions definitions, File outputFile, STGroup templateGroup) {
        this.name = name;
        this.definitions = definitions;
        this.outputFile = outputFile;
        this.templateGroup = templateGroup;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void generate() throws Exception {
        GenBlock fileBlock = new GenBlock();
        generateHeader(fileBlock);
        generateResourceStruct(fileBlock);
        generateCustomMarshallersAndUnMarshallers(fileBlock);
        generateComponentStructs(fileBlock);
        generateResourcePlusStruct(fileBlock);

        outputFile.createNewFile();
        Writer modelFile = new BufferedWriter(new FileWriter(outputFile));
        modelFile.write(fileBlock.toString());
        modelFile.flush();
        modelFile.close();
    }

    private void generateHeader(GenBlock fileBlock) {
        fileBlock.ln(COPYRIGHT);
        fileBlock.ln();
        fileBlock.ln("package models");
        fileBlock.ln();
        Set<String> imports = discoverImports(getRootDefinition());
        if (imports.size() == 1) {
            fileBlock.ln(String.format("import \"%s\"", imports.iterator().next()));
            fileBlock.ln();
        } else if (imports.size() > 1) {
            fileBlock.bs("import (");
            for (String importPackage : imports) {
                fileBlock.ln(String.format("\"%s\"", importPackage));
            }
            fileBlock.es(")");
            fileBlock.ln();
        }
    }

    private void generateResourceStruct(GenBlock fileBlock) {
        fileBlock.bs("type " + name + " struct {");
        if (name.equals("Resource")) {
          fileBlock.ln(getFieldDefinition("ResourceType", GoType.STRING.type()));
        }
        for (TypeRef ref: getRootDefinition().getTypes()) {
            if (definitions.hasElementDefn(ref.getName())) {
                fileBlock.ln(String.format("%s `bson:\",inline\"`", ref.getName()));
            }
        }
        for (ElementDefn elementDefinition : getRootDefinition().getElements()) {
            generateFields(fileBlock, elementDefinition);
        }
        fileBlock.es("}");
    }

    private void generateComponentStructs(GenBlock fileBlock) {
        for (ElementDefn elementDefinition : getRootDefinition().getElements()) {
            generateComponentStruct(fileBlock, elementDefinition);
        }
    }

    private void generateCustomMarshallersAndUnMarshallers(GenBlock fileBlock) throws Exception {
        if (definitions.getResources().containsKey(name) || name.equals("Parameters")) {
            ST st = templateGroup.getInstanceOf("generic_resource_marshaller.go");
            st.add("Name", name);
            fileBlock.ln(st.render());
        }

        // Do NOT generate unmarshaller for base resources, because it causes the container resource to fail
        // unmarshalling.  See: http://www.scriptscoop.net/t/d2ea1a251aa8/json-unmarshal-fails-when-embedded-type-has-unmarshaljson.html
        // So... instead, we must embed the base resource's custom unmarshalling into the specific (container) resource.
        if (isResource(name)) {
            generateCustomUnmarshaller(name, getRootDefinition(), fileBlock);
        }
    }

    private void generateCustomUnmarshaller(String structName, ElementDefn elementDefn, GenBlock fileBlock) {
        List<String> resourceFields = new ArrayList<String>();
        Map<String, Boolean> fieldSliceIndicators = new HashMap<String, Boolean>();
        populateFieldNameAndSliceIndicatorsForUnmarshaller(elementDefn, resourceFields, fieldSliceIndicators);
        
        if(!resourceFields.isEmpty() || isResource(structName)) {
          ST st = templateGroup.getInstanceOf("generic_resource_unmarshaller.go");
          st.add("Name", structName);
          st.add("LowerName", lowercase(structName));
          st.add("Fields", resourceFields);
          st.add("FieldSliceIndicators", fieldSliceIndicators);
          st.add("IsResource", isResource(structName));
          fileBlock.ln(st.render());
        }

    }

    private void populateFieldNameAndSliceIndicatorsForUnmarshaller(ElementDefn elementDefn, List<String> resourceFields, Map<String, Boolean> fieldSliceIndicator) {
        // First add the fields and slice indicators for this resource's base resources
        for (TypeRef typeRef : elementDefn.getTypes()) {
            if (definitions.getBaseResources().containsKey(typeRef.getName())) {
                ElementDefn base = definitions.getBaseResources().get(typeRef.getName()).getRoot();
                populateFieldNameAndSliceIndicatorsForUnmarshaller(base, resourceFields, fieldSliceIndicator);
            }
        }

        // Then add the fields and slice indicators for this resource's elements
        for (ElementDefn elementDefinition : elementDefn.getElements()) {
            for (TypeRef typeRef : elementDefinition.getTypes()) {
                if ("Resource".equals(typeRef.getName()) || "Resource".equals(typeRef.getResolvedTypeName())) {
                    String fieldName = getFieldName(elementDefinition, typeRef);
                    resourceFields.add(fieldName);
                    fieldSliceIndicator.put(fieldName, elementDefinition.getMaxCardinality() > 1);
                }
            }
        }
    }

    private void generateFields(GenBlock block, ElementDefn elementDefinition) {
        if (isComponent(elementDefinition)) {
            String fieldName = getFieldName(elementDefinition, null);
            String structName = getComponentStructName(elementDefinition);
            Integer card = elementDefinition.getMaxCardinality();
            String modifier = card == null || card > 1 ? "[]" : "*";
            block.ln(getFieldDefinition(fieldName, modifier, structName));
        } else {
            for (TypeRef typeRef : elementDefinition.getTypes()) {
                String elementType = typeRef.getName();
                if (elementType.startsWith("@")) {
                    elementType = typeRef.getResolvedTypeName();
                }

                String fieldName = getFieldName(elementDefinition, typeRef);
                Modifier m = new Modifier(elementDefinition);

                if (elementType.equals("*")) {
                    for (FHIRType ft : FHIRType.values()) {
                        if (ft.isOpen()) {
                            block.ln(getFieldDefinition(fieldName.concat(capitalize(ft.type())), m.typify(ft.goType())));
                        }
                    }
                } else {
                    try {
                        block.ln(getFieldDefinition(fieldName, m.typify(FHIRType.byType(elementType).goType())));
                    } catch (IllegalArgumentException e) {
                        // Wasn't a recognized core FHIR type -- so it must be a component
                        if (typeRef.isElementReference()) {
                            elementType = getComponentStructName(elementType);
                        }
                        block.ln(getFieldDefinition(fieldName, m.typify(elementType)));
                    }
                }
            }
        }
    }

    private static final class Modifier {
        private final boolean isSlice;

        public Modifier(ElementDefn el) {
            this.isSlice = el.getMaxCardinality() == null || el.getMaxCardinality() > 1;
        }

        public String typify(String type) {
            String result = type;
            if (isSlice) {
                result = "[]".concat(type);
            } else if (! GoType.STRING.type().equals(type) && ! GoType.INTERFACE.type().equals(type)) {
                result = "*".concat(type);
            }
            return result;
        }
    }

    private String getFieldName(ElementDefn elementDefinition, TypeRef type) {
        StringBuilder elementName = new StringBuilder(elementDefinition.getName().replace("[x]", ""));
        if (elementDefinition.getTypes().size() > 1) {
            elementName.append(capitalize(type.getName()));
        }
        return capitalize(elementName.toString());
    }

    private String getFieldDefinition(String fieldName, String fieldTypeModifier, String fieldType) {
        String typeName = lowercase(fieldName);
        return String.format("%s %s%s `bson:\"%s,omitempty\" json:\"%s,omitempty\"`", fieldName, fieldTypeModifier, fieldType, typeName, typeName);
    }

    private String getFieldDefinition(String fieldName, String fieldType) {
        String jsonName = lowercase(fieldName);
        String bsonName = jsonName.equals(FHIRType.ID.type()) ? "_id" : jsonName;
        return String.format("%s %s `bson:\"%s,omitempty\" json:\"%s,omitempty\"`", fieldName, fieldType, bsonName, jsonName);
    }

    private boolean isComponent(ElementDefn elementDefinition) {
        return elementDefinition.getTypes().isEmpty();
    }

    private String getComponentStructName(ElementDefn elementDefinition) {
        return getComponentStructName(elementDefinition.getDeclaredTypeName());
    }

    private String getComponentStructName(String typeName) {
        typeName = capitalize(typeName);
        if (! typeName.startsWith(name)) {
            typeName = name.concat(typeName);
        }
        return typeName;
    }

    private void generateComponentStruct(GenBlock block, ElementDefn elementDefinition) {
        if(isComponent(elementDefinition)) {
            String structName = getComponentStructName(elementDefinition);
            block.ln();
            block.bs(String.format("type %s struct {", structName));
            // Ideally we would programmatically figure out it inherits from BackboneElement, but this is not an ideal
            // world, and I don't think the generator actually tells you that information (it's assumed?)
            block.ln("BackboneElement `bson:\",inline\"`");
            for (ElementDefn nestedElement : elementDefinition.getElements()) {
                generateFields(block, nestedElement);
            }
            block.es("}");

            generateCustomUnmarshaller(structName, elementDefinition, block);

            for (ElementDefn nestedElement : elementDefinition.getElements()) {
                generateComponentStruct(block, nestedElement);
            }
        }

    }

    private void generateResourcePlusStruct(GenBlock fileBlock) {
        ResourceDefn resource = definitions.getResources().get(name);
        if (resource == null) {
            return;
        }

        // First find all the possible resources to include
        ArrayList<ResourcePlusIncludeInfo> includeInfos = new ArrayList<ResourcePlusIncludeInfo>();
        for (SearchParameterDefn p : resource.getSearchParams().values()) {
            if (SearchParameterDefn.SearchType.reference.equals(p.getType())) {
                for (String target : p.getWorkingTargets()) {
                    // TODO: Someday support Any, but with a collection-per-resource and mongo, this isn't feasible today
                    if ("Any".equals(target)) {
                        continue;
                    }
                    for (int i=0; i < p.getPaths().size(); i++) {
                        String path = p.getPaths().get(i);
                        String paramName = p.getCode().replaceAll("-", "");
                        StringBuilder fieldBuf = new StringBuilder("Included").append(target).append("ResourcesReferencedBy").append(capitalize(paramName));
                        if (p.getPaths().size() > 1) {
                            fieldBuf.append("Path").append(i + 1);
                        }

                        boolean multipleCardinality = false;
                        if (! path.endsWith("(0)")) {
                            try {
                                ElementDefn el = resource.getRoot().getElementForPath(path, definitions, "resolving search parameter path", true);
                                if (el.getMaxCardinality() > 1) {
                                    multipleCardinality = true;
                                }
                            } catch (Exception e) {
                                System.err.println("Couldn't determine cardinality for parameter " + p.getCode() + " path: " + p.getPaths().get(i));
                            }
                        }

                        includeInfos.add(new ResourcePlusIncludeInfo(fieldBuf.toString(), target, multipleCardinality));
                    }
                }
            }
        }

        // Then find all the possible resources to revinclude
        ArrayList<ResourcePlusRevIncludeInfo> revIncludeInfos = new ArrayList<ResourcePlusRevIncludeInfo>();
        for (ResourceDefn other : definitions.getResources().values()) {
            for (SearchParameterDefn p : other.getSearchParams().values()) {
                if (SearchParameterDefn.SearchType.reference.equals(p.getType())) {
                    boolean targetsResource = false;
                    for (String target : p.getWorkingTargets()) {
                        if (resource.getName().equals(target) || "Any".equals(target)) {
                            targetsResource = true;
                            break;
                        }
                    }
                    if (targetsResource) {
                        for (int i = 0; i < p.getPaths().size(); i++) {
                            String paramName = p.getCode().replaceAll("-", "");
                            StringBuilder fieldBuf = new StringBuilder("RevIncluded").append(other.getName()).append("ResourcesReferencing").append(capitalize(paramName));
                            if (p.getPaths().size() > 1) {
                                fieldBuf.append("Path").append(i + 1);
                            }
                            revIncludeInfos.add(new ResourcePlusRevIncludeInfo(other.getName(), fieldBuf.toString()));
                        }
                    }
                }
            }
        }

        fileBlock.ln();
        fileBlock.bs(String.format("type %sPlus struct {", name));
        fileBlock.ln(String.format("%s `bson:\",inline\"`", name));
        fileBlock.ln(String.format("%sPlusRelatedResources `bson:\",inline\"`", name));
        fileBlock.es("}");

        fileBlock.ln();
        fileBlock.bs(String.format("type %sPlusRelatedResources struct {", name));
        for (ResourcePlusIncludeInfo info : includeInfos) {
            fileBlock.ln(String.format("%s *[]%s `bson:\"%s,omitempty\"`", info.getField(), info.getTarget(), info.getMongoField()));
        }
        for (ResourcePlusRevIncludeInfo info : revIncludeInfos) {
            fileBlock.ln(String.format("%s *[]%s `bson:\"%s,omitempty\"`", info.getField(), info.getResource(), info.getMongoField()));
        }
        fileBlock.es("}");

        String alias = name.toLowerCase().substring(0,1);
        for (ResourcePlusIncludeInfo info : includeInfos) {
            fileBlock.ln();
            if (info.isMultipleCardinality()) {
                fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) Get%s() (%s []%s, err error) {", alias, name, info.getField(), Utilities.pluralizeMe(lowercase(info.getTarget())), info.getTarget()));
                fileBlock.bs(String.format("if %s.%s == nil {", alias, info.getField()));
                fileBlock.ln(String.format("err = errors.New(\"Included %s not requested\")", Utilities.pluralizeMe(lowercase(info.getTarget()))));
                fileBlock.es("} else {");
                fileBlock.bs();
                fileBlock.ln(String.format("%s = *%s.%s", Utilities.pluralizeMe(lowercase(info.getTarget())), alias, info.getField()));
                fileBlock.es("}");
                fileBlock.ln("return");
                fileBlock.es("}");
            } else {
                String singular = info.getField().replaceFirst("Resources", "Resource");
                fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) Get%s() (%s *%s, err error) {", alias, name, singular, lowercase(info.getTarget()), info.getTarget()));
                fileBlock.bs(String.format("if %s.%s == nil {", alias, info.getField()));
                fileBlock.ln(String.format("err = errors.New(\"Included %s not requested\")", Utilities.pluralizeMe(info.getTarget().toLowerCase())));
                fileBlock.es(String.format("} else if len(*%s.%s) > 1 {", alias, info.getField()));
                fileBlock.bs();
                fileBlock.ln(String.format("err = fmt.Errorf(\"Expected 0 or 1 %s, but found %s\", len(*%s.%s))", lowercase(info.getTarget()), "%d", alias, info.getField()));
                fileBlock.es(String.format("} else if len(*%s.%s) == 1 {", alias, info.getField()));
                fileBlock.bs();
                fileBlock.ln(String.format("%s = &(*%s.%s)[0]", lowercase(info.getTarget()), alias, info.getField()));
                fileBlock.es("}");
                fileBlock.ln("return");
                fileBlock.es("}");
            }
        }

        for (ResourcePlusRevIncludeInfo info : revIncludeInfos) {
            fileBlock.ln();
            fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) Get%s() (%s []%s, err error) {", alias, name, info.getField(), Utilities.pluralizeMe(lowercase(info.getResource())), info.getResource()));
            fileBlock.bs(String.format("if %s.%s == nil {", alias, info.getField()));
            fileBlock.ln(String.format("err = errors.New(\"RevIncluded %s not requested\")", Utilities.pluralizeMe(lowercase(info.getResource()))));
            fileBlock.es("} else {");
            fileBlock.bs();
            fileBlock.ln(String.format("%s = *%s.%s", Utilities.pluralizeMe(lowercase(info.getResource())), alias, info.getField()));
            fileBlock.es("}");
            fileBlock.ln("return");
            fileBlock.es("}");
        }

        fileBlock.ln();
        fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) GetIncludedResources() map[string]interface{} {", alias, name));
        fileBlock.ln("resourceMap := make(map[string]interface{})");
        for (ResourcePlusIncludeInfo info : includeInfos) {
            fileBlock.bs(String.format("if %s.%s != nil {", alias, info.getField()));
            // Normally I'd use 'i' as the index, but 'i' is already defined as the alias for resources whose name starts with with 'I'
            fileBlock.bs(String.format("for idx := range *%s.%s {", alias, info.getField()));
            fileBlock.ln(String.format("rsc := (*%s.%s)[idx]", alias, info.getField()));
            fileBlock.ln("resourceMap[rsc.Id] = &rsc");
            fileBlock.es("}");
            fileBlock.es("}");
        }
        fileBlock.ln("return resourceMap");
        fileBlock.es("}");

        fileBlock.ln();
        fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) GetRevIncludedResources() map[string]interface{} {", alias, name));
        fileBlock.ln("resourceMap := make(map[string]interface{})");
        for (ResourcePlusRevIncludeInfo info : revIncludeInfos) {
            fileBlock.bs(String.format("if %s.%s != nil {", alias, info.getField()));
            fileBlock.bs(String.format("for idx := range *%s.%s {", alias, info.getField()));
            fileBlock.ln(String.format("rsc := (*%s.%s)[idx]", alias, info.getField()));
            fileBlock.ln("resourceMap[rsc.Id] = &rsc");
            fileBlock.es("}");
            fileBlock.es("}");
        }
        fileBlock.ln("return resourceMap");
        fileBlock.es("}");

        fileBlock.ln();
        fileBlock.bs(String.format("func (%s *%sPlusRelatedResources) GetIncludedAndRevIncludedResources() map[string]interface{} {", alias, name));
        fileBlock.ln("resourceMap := make(map[string]interface{})");
        for (ResourcePlusIncludeInfo info : includeInfos) {
            fileBlock.bs(String.format("if %s.%s != nil {", alias, info.getField()));
            fileBlock.bs(String.format("for idx := range *%s.%s {", alias, info.getField()));
            fileBlock.ln(String.format("rsc := (*%s.%s)[idx]", alias, info.getField()));
            fileBlock.ln("resourceMap[rsc.Id] = &rsc");
            fileBlock.es("}");
            fileBlock.es("}");
        }
        for (ResourcePlusRevIncludeInfo info : revIncludeInfos) {
            fileBlock.bs(String.format("if %s.%s != nil {", alias, info.getField()));
            fileBlock.bs(String.format("for idx := range *%s.%s {", alias, info.getField()));
            fileBlock.ln(String.format("rsc := (*%s.%s)[idx]", alias, info.getField()));
            fileBlock.ln("resourceMap[rsc.Id] = &rsc");
            fileBlock.es("}");
            fileBlock.es("}");
        }
        fileBlock.ln("return resourceMap");
        fileBlock.es("}");

    }

    private class ResourcePlusIncludeInfo {
        private final String field;
        private final String target;
        private final boolean multipleCardinality;

        public ResourcePlusIncludeInfo(String field, String target, boolean multipleCardinality) {
            this.field = field;
            this.target = target;
            this.multipleCardinality = multipleCardinality;
        }

        public String getField() {
            return field;
        }

        public String getTarget() {
            return target;
        }

        public boolean isMultipleCardinality() {
            return multipleCardinality;
        }

        public String getMongoField() {
            return "_" + lowercase(field);
        }
    }

    private class ResourcePlusRevIncludeInfo {
        private final String resource;
        private final String field;

        public ResourcePlusRevIncludeInfo(String resource, String field) {
            this.resource = resource;
            this.field = field;
        }

        public String getResource() {
            return resource;
        }

        public String getField() {
            return field;
        }

        public String getMongoField() {
            return "_" + lowercase(field);
        }
    }


    /**
     * Discovers the needed imports for a file depending on the elements
     * going into it.
     */
    private Set<String> discoverImports(ElementDefn elementDefn) {
        Set<String> imports = new HashSet<String>();

        // If it's a resource (not base resource), we know it needs encoding/json
        if (definitions.getResources().containsKey(elementDefn.getName())) {
            imports.add("encoding/json");
        } else {
            // Look in this resource's elements
            for (ElementDefn childElementDefn : elementDefn.getElements()) {
                if (isComponent(childElementDefn)) {
                    imports.addAll(discoverImports(childElementDefn));
                } else if (! definitions.getBaseResources().containsKey(elementDefn.getName())) {
                    // Note -- the above restriction ensures that we don't mistakenly add imports to base resources
                    // since their custom unmarshallers will actually be in the specific (non-base) resources
                    for (TypeRef typeRef : childElementDefn.getTypes()) {
                        if ("Resource".equals(typeRef.getName()) || "Resource".equals(typeRef.getResolvedTypeName())) {
                            imports.add("encoding/json");
                        }
                    }
                }
            }
        }

        // If it supports _includes, it will need fmt and errors too
        if (definitions.getResources().containsKey(elementDefn.getName())) {
            ResourceDefn resource = definitions.getResources().get(elementDefn.getName());
            for (SearchParameterDefn p : resource.getSearchParams().values()) {
                if (SearchParameterDefn.SearchType.reference.equals(p.getType()) && ! p.getPaths().isEmpty() && !p.getWorkingTargets().isEmpty()) {
                    // NOTE: We don't support _include on params that are Any
                    if (!p.getWorkingTargets().contains("Any")) {
                        imports.add("errors");
                        imports.add("fmt");
                        break;
                    }
                }
            }
        }
        
        if (isResource(name)) {
          imports.add("errors");
          imports.add("fmt");
        }

        // If it supports _revincludes, it will need errors too
        if (definitions.getResources().containsKey(elementDefn.getName())) {
            ResourceDefn resource = definitions.getResources().get(elementDefn.getName());
            for (ResourceDefn other : definitions.getResources().values()) {
                for (SearchParameterDefn p : other.getSearchParams().values()) {
                    if (SearchParameterDefn.SearchType.reference.equals(p.getType()) && !p.getPaths().isEmpty() && !p.getWorkingTargets().isEmpty()) {
                        if (p.getWorkingTargets().contains(resource.getName()) || p.getWorkingTargets().contains("Any")) {
                            imports.add("errors");
                            break;
                        }
                    }
                }
            }
        }

        return imports;
    }

    private String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + (str.length() > 1 ? str.substring(1) : "");
    }

    private String lowercase(String str) {
        return Character.toLowerCase(str.charAt(0)) + (str.length() > 1 ? str.substring(1) : "");
    }

    private TypeDefn getRootDefinition() {
        ResourceDefn resource = definitions.getResources().get(name);
        if (resource == null) {
            resource = definitions.getBaseResources().get(name);
        }

        TypeDefn el;
        if (resource != null) {
            el = resource.getRoot();
        } else {
            el = definitions.getInfrastructure().get(name);
            if (el == null)
                el = definitions.getTypes().get(name);
            if (el == null)
                el =  definitions.getStructures().get(name);
        }
        return el;
    }
    
    // Check to see if this resource is a Resource or DomainResource
    private boolean isResource(String rName) {
      if (definitions.getResources().get(rName) != null || rName.equals("Parameters")) {
        for (TypeRef ref: getRootDefinition().getTypes()) {
          if ((ref.getName().equals("Resource") || ref.getName().equals("DomainResource")) &&
              !rName.equals("DomainResource")) {
            return true;
          }
        }
      }

      return false;
    }

    private static final String COPYRIGHT =
            "// Copyright (c) 2011-2015, HL7, Inc & The MITRE Corporation\n" +
            "// All rights reserved.\n" +
            "//\n" +
            "// Redistribution and use in source and binary forms, with or without modification,\n" +
            "// are permitted provided that the following conditions are met:\n" +
            "//\n" +
            "//     * Redistributions of source code must retain the above copyright notice, this\n" +
            "//       list of conditions and the following disclaimer.\n" +
            "//     * Redistributions in binary form must reproduce the above copyright notice,\n" +
            "//       this list of conditions and the following disclaimer in the documentation\n" +
            "//       and/or other materials provided with the distribution.\n" +
            "//     * Neither the name of HL7 nor the names of its contributors may be used to\n" +
            "//       endorse or promote products derived from this software without specific\n" +
            "//       prior written permission.\n" +
            "//\n" +
            "// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND\n" +
            "// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED\n" +
            "// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED.\n" +
            "// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT,\n" +
            "// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT\n" +
            "// NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR\n" +
            "// PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,\n" +
            "// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)\n" +
            "// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE\n" +
            "// POSSIBILITY OF SUCH DAMAGE.";
}
