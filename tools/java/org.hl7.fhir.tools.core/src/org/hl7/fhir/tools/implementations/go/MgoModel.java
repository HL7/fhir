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
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;

public class MgoModel extends ResourceGenerator {
    private String[] imports;

    public MgoModel(String name, Definitions definitions, File outputFile, String... imports) {
        super(name, definitions, outputFile);
        this.imports = imports;
    }

    public void generate() throws Exception {
      
      outputFile.createNewFile();
      GenBlock fileBlock = new GenBlock();
      
      generateMainHeader(fileBlock);
      generateResourceHeader(fileBlock);

      TypeDefn root = getRootDefinition();    

      // first loop through and generate the field elements
      for (ElementDefn elementDefinition : root.getElements()) {
        generateElement(fileBlock, elementDefinition, name);
      }

      generateMainFooter(fileBlock);
      
      // next loop through and generate all of the embedded types from this schema
      for (ElementDefn elementDefinition : root.getElements()) {
        generateEmbeddedType(fileBlock, elementDefinition, name);
      }

      Writer modelFile = new BufferedWriter(new FileWriter(outputFile));
      modelFile.write(fileBlock.toString());
      modelFile.flush();
      modelFile.close();

    }

    protected void generateElement(GenBlock block, ElementDefn elementDefinition, String resourceName) {
      List<TypeRef> types = elementDefinition.getTypes();
      if(types.size() > 0) {
        for (TypeRef typeRef : types) {

          String elementType = typeRef.getName();

          if (elementType.startsWith("@")) {
              elementType = typeRef.getResolvedTypeName();
          }

          String generatedName = generateTypeName(elementDefinition, typeRef);
          String titleizedName = generatedName.substring(0, 1).toUpperCase() + generatedName.substring(1);

          Integer card = elementDefinition.getMaxCardinality();
          String cardString = "";
          String pointerString = "";
          if (card == null || card > 1) {
              cardString = "[]";
          } else {
              pointerString = "*";
          }

          if (elementType.equals("*")) {
              //block.ln("field :"+generateTypeName(elementDefinition,typeRef) + ", type: Hash");
              block.ln(titleizedName + "String " + cardString + "string " + "  `bson:\"" + generatedName + "string,omitempty\" json:\"" + generatedName + "string,omitempty\"`");
              block.ln(titleizedName + "Integer " + cardString + "int32 " + "  `bson:\"" + generatedName + "integer,omitempty\" json:\"" + generatedName + "integer,omitempty\"`");
              block.ln(titleizedName + "DateTime " + cardString + "*FHIRDateTime " + "  `bson:\"" + generatedName + "datetime,omitempty\" json:\"" + generatedName + "datetime,omitempty\"`");
              block.ln(titleizedName + "Boolean " + cardString + "*bool " + "  `bson:\"" + generatedName + "boolean,omitempty\" json:\"" + generatedName + "boolean,omitempty\"`");
              block.ln(titleizedName + "CodeableConcept *" + cardString + "CodeableConcept " + "  `bson:\"" + generatedName + "codeableconcept,omitempty\" json:\"" + generatedName + "codeableconcept,omitempty\"`");
              block.ln(titleizedName + "Range " + cardString + "*Range " + "  `bson:\"" + generatedName + "range,omitempty\" json:\"" + generatedName + "range,omitempty\"`");
          } else if (elementType.equals("base64Binary")) {
              block.ln(titleizedName + " " + cardString + "string " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("boolean")) {
              block.ln(titleizedName + " *" + cardString + "bool " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("integer")) {
              block.ln(titleizedName + " " + cardString + "int32 " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("unsignedInt") || elementType.equals("positiveInt")) {
              block.ln(titleizedName + " " + cardString + "uint32 " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("decimal")) {
              block.ln(titleizedName + " " + cardString + "float64 " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("instant") || elementType.equals("date") || elementType.equals("dateTime") || elementType.equals("time")) {
              block.ln(titleizedName + " *" + cardString + "FHIRDateTime " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("string") || elementType.equals("uri") || elementType.equals("code") || elementType.equals("id") || elementType.equals("oid") || elementType.equals("xhtml")) {
              if (titleizedName.equals("Gender"))
                  block.ln(titleizedName + " *CodeableConcept " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
              else if (!titleizedName.equals("Id"))
                  block.ln(titleizedName + " " + cardString + "string " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("Resource") || elementType.equals("idref")) {
              block.ln(titleizedName + " " + cardString + "*Reference " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else if (elementType.equals("Age") || elementType.equals("Count") || elementType.equals("Duration") || elementType.equals("Money")) {
              block.ln(titleizedName + " *Quantity " + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          } else {
              if (typeRef.isElementReference()) {
                  elementType = resourceName + elementType;
              }
              block.ln(titleizedName + " " + pointerString + cardString + elementType + "  `bson:\"" + generatedName + ",omitempty\" json:\"" + generatedName + ",omitempty\"`");
          }
        }
       } else if(types.size() == 0) {
          String typeName = generateTypeName(elementDefinition, null);
          String titleizedName = typeName.substring(0, 1).toUpperCase() + typeName.substring(1);
          String className = getEmbeddedClassName(elementDefinition, resourceName);
          Integer card = elementDefinition.getMaxCardinality();
          if( card == null || card > 1){
            block.ln(titleizedName + "  []" + className + "  `bson:\"" + typeName + ",omitempty\" json:\"" + typeName +",omitempty\"`");
          }else{
            block.ln(titleizedName + "  *" + className + "  `bson:\"" + typeName + ",omitempty\" json:\"" + typeName +",omitempty\"`");
          }

      }

    }

    protected void generateEmbeddedType(GenBlock block, ElementDefn elementDefinition, String resourceName) {
      List<TypeRef> types = elementDefinition.getTypes();
      if(types.size() == 0) {
          block.ln(String.format("// This is an ugly hack to deal with the embedded %s structure", generateTypeName(elementDefinition, null)));
          String className = getEmbeddedClassName(elementDefinition, resourceName);
          block.bs(String.format("type %s struct {", className));
          for (ElementDefn nestedElement : elementDefinition.getElements()) {
            generateElement(block, nestedElement, resourceName);
          }
          block.es("}");

          for (ElementDefn nestedElement : elementDefinition.getElements()) {
            generateEmbeddedType(block, nestedElement, resourceName);
          }
      }

    }

    protected void generateMainHeader(GenBlock fileBlock) {
      fileBlock.ln("// Copyright (c) 2011-2015, HL7, Inc & The MITRE Corporation");
      fileBlock.ln("// All rights reserved.");
      fileBlock.ln("// ");
      fileBlock.ln("// Redistribution and use in source and binary forms, with or without modification, ");
      fileBlock.ln("// are permitted provided that the following conditions are met:");
      fileBlock.ln("// ");
      fileBlock.ln("//     * Redistributions of source code must retain the above copyright notice, this ");
      fileBlock.ln("//       list of conditions and the following disclaimer.");
      fileBlock.ln("//     * Redistributions in binary form must reproduce the above copyright notice, ");
      fileBlock.ln("//       this list of conditions and the following disclaimer in the documentation ");
      fileBlock.ln("//       and/or other materials provided with the distribution.");
      fileBlock.ln("//     * Neither the name of HL7 nor the names of its contributors may be used to ");
      fileBlock.ln("//       endorse or promote products derived from this software without specific ");
      fileBlock.ln("//       prior written permission.");
      fileBlock.ln("// ");
      fileBlock.ln("// THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ");
      fileBlock.ln("// ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED ");
      fileBlock.ln("// WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. ");
      fileBlock.ln("// IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, ");
      fileBlock.ln("// INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT ");
      fileBlock.ln("// NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR ");
      fileBlock.ln("// PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, ");
      fileBlock.ln("// WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ");
      fileBlock.ln("// ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE ");
      fileBlock.ln("// POSSIBILITY OF SUCH DAMAGE.");
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

    protected void generateMainFooter(GenBlock fileBlock) {
      fileBlock.es("}");
    }

    protected void generateResourceHeader(GenBlock fileBlock) {
      fileBlock.bs("type " + name + " struct {");
      fileBlock.ln("Id string `json:\"-\" bson:\"_id\"`");
      //generateSearchParams(fileBlock);
    }

    private void generateSearchParams(GenBlock block){
      ResourceDefn resource = definitions.getResources().get(name);
      if(resource !=null){
        Set<String> params = resource.getSearchParams().keySet();
        block.ln("type searchParams string");
        block.ln("const (");
        block.bs("_ = iota");
        for (String param : params) {
          block.ln(param);
        }
        block.es(")");
      }
    }
    
    @Override
    protected String generateTypeName(ElementDefn elementDefinition, TypeRef type) {
      String elementName = elementDefinition.getName().replace("[x]", "");
      if (elementDefinition.getTypes().size() > 1) {
        String typeName = type.getName();
        typeName = Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
        elementName += typeName;
      }
      return elementName;
    }

}
