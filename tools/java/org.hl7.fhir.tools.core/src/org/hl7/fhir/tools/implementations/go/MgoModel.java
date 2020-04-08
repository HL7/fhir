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
import java.io.Writer;
import java.util.ArrayList;

import org.hl7.fhir.definitions.model.*;
import org.hl7.fhir.tools.implementations.GenBlock;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroup;

public class MgoModel {
    private String name;
    private File outputFile;
    private STGroup templateGroup;
    private Definitions definitions;
    private String[] imports;

    public MgoModel(String name, Definitions definitions, File outputFile, STGroup templateGroup, String... imports) {
        this.name = name;
        this.definitions = definitions;
        this.outputFile = outputFile;
        this.templateGroup = templateGroup;
        this.imports = imports;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public void generate() throws Exception {
        GenBlock fileBlock = new GenBlock();
        generateHeader(fileBlock);
        generateResourceStruct(fileBlock);
        generateCustomMarshallersAndUnMarshallers(fileBlock);
        generateComponentStructs(fileBlock);

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
        if (imports.length == 1) {
            fileBlock.ln(String.format("import \"%s\"", imports[0]));
            fileBlock.ln();
        } else if (imports.length > 1) {
            fileBlock.bs("import (");
            for (String i : imports) {
                fileBlock.ln(String.format("\"%s\"",i));
            }
            fileBlock.es(")");
            fileBlock.ln();
        }
    }

    private void generateResourceStruct(GenBlock fileBlock) {
        fileBlock.bs("type " + name + " struct {");
        if (definitions.getResources().get(name) != null) {
            fileBlock.ln("Id string `json:\"id\" bson:\"_id\"`");
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
        if (definitions.getResources().containsKey(name)) {
            ST st = templateGroup.getInstanceOf("generic_resource_marshaller.go");
            st.add("Name", name);
            fileBlock.ln(st.render());
        }

        generateCustomUnmarshaller(name, getRootDefinition(), fileBlock);
    }

    private void generateCustomUnmarshaller(String structName, ElementDefn elementDefn, GenBlock fileBlock) {
        ArrayList<String> resourceFields = new ArrayList<String>();
        for (ElementDefn elementDefinition : elementDefn.getElements()) {
            for (TypeRef typeRef : elementDefinition.getTypes()) {
                if ("Resource".equals(typeRef.getName()) || "Resource".equals(typeRef.getResolvedTypeName())) {
                    resourceFields.add(getFieldName(elementDefinition, typeRef));
                }
            }
        }

        if (!resourceFields.isEmpty()) {
            ST st = templateGroup.getInstanceOf("generic_resource_unmarshaller.go");
            st.add("Name", structName);
            st.add("LowerName", lowercase(structName));
            st.add("Fields", resourceFields);
            fileBlock.ln(st.render());
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
                    block.ln(getFieldDefinition(fieldName.concat("String"), m.typify("string")));
                    block.ln(getFieldDefinition(fieldName.concat("Integer"), m.typify("int32")));
                    block.ln(getFieldDefinition(fieldName.concat("DateTime"), m.typify("FHIRDateTime")));
                    block.ln(getFieldDefinition(fieldName.concat("Boolean"), m.typify("bool")));
                    block.ln(getFieldDefinition(fieldName.concat("CodeableConcept"), m.typify("CodeableConcept")));
                    block.ln(getFieldDefinition(fieldName.concat("Range"), m.typify("Range")));
                } else if (elementType.equals("base64Binary")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("string")));
                } else if (elementType.equals("boolean")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("bool")));
                } else if (elementType.equals("integer")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("int32")));
                } else if (elementType.equals("unsignedInt") || elementType.equals("positiveInt")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("uint32")));
                } else if (elementType.equals("decimal")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("float64")));
                } else if (elementType.equals("instant") || elementType.equals("date") || elementType.equals("dateTime") || elementType.equals("time")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("FHIRDateTime")));
                } else if (elementType.equals("string") || elementType.equals("uri") || elementType.equals("code") || elementType.equals("id") || elementType.equals("oid") || elementType.equals("xhtml") || elementType.equals("markdown")) {
                    if (!fieldName.equals("Id")) {
                        block.ln(getFieldDefinition(fieldName, m.typify("string")));
                    }
                } else if (elementType.equals("idref")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("Reference")));
                } else if (elementType.equals("Resource")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("interface{}")));
                } else if (elementType.equals("Age") || elementType.equals("Count") || elementType.equals("Duration") || elementType.equals("Money") || elementType.equals("Distance")|| elementType.equals("SimpleQuantity")) {
                    block.ln(getFieldDefinition(fieldName, m.typify("Quantity")));
                } else {
                    if (typeRef.isElementReference()) {
                        elementType = getComponentStructName(elementType);
                    }
                    block.ln(getFieldDefinition(fieldName, m.typify(elementType)));
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
            } else if (! "string".equals(type) && ! "interface{}".equals(type)) {
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
        String typeName = lowercase(fieldName);
        return String.format("%s %s `bson:\"%s,omitempty\" json:\"%s,omitempty\"`", fieldName, fieldType, typeName, typeName);
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

    private String capitalize(String str) {
        return Character.toUpperCase(str.charAt(0)) + (str.length() > 1 ? str.substring(1) : "");
    }

    private String lowercase(String str) {
        return Character.toLowerCase(str.charAt(0)) + (str.length() > 1 ? str.substring(1) : "");
    }

    private TypeDefn getRootDefinition() {
        TypeDefn el;
        ResourceDefn resource = definitions.getResources().get(name);
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
