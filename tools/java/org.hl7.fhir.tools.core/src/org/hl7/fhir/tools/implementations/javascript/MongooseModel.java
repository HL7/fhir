package org.hl7.fhir.tools.implementations.javascript;

/*
Contributed by Mitre Corporation

Copyright (c) 2011+, HL7, Inc & The MITRE Corporation
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

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.tools.implementations.GenBlock;

public class MongooseModel {
    private String name;
    private File javaScriptFile;
    private Definitions definitions;
    
    public MongooseModel(String name, Definitions definitions, File javaScriptFile) {
      this.name = name;
      this.definitions = definitions;
      this.javaScriptFile = javaScriptFile;
    }
    
    public void generate() throws Exception {
      javaScriptFile.createNewFile();
      GenBlock fileBlock = new GenBlock();
      fileBlock.ln("// Copyright (c) 2011+, HL7, Inc & The MITRE Corporation");
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
      fileBlock.ln("var mongoose = require('mongoose');");
      fileBlock.ln();
      fileBlock.ln("var " + name + "Schema = new mongoose.Schema({");
      fileBlock.bs();
      
      ResourceDefn resource = definitions.getResourceByName(name);
      for (Iterator<ElementDefn> iterator = resource.getRoot().getElements().iterator(); iterator.hasNext();) {
        ElementDefn elementDefinition = iterator.next();
        generateElement(fileBlock, elementDefinition, iterator.hasNext());
      }
      fileBlock.es();
      fileBlock.ln("});");
      fileBlock.ln();
      fileBlock.ln("mongoose.model('" + name +"', " + name+ "Schema);");
      Writer modelFile = new BufferedWriter(new FileWriter(javaScriptFile));
      modelFile.write(fileBlock.toString());
      modelFile.flush();
      modelFile.close();
    }
    
    private void generateElement(GenBlock block, ElementDefn elementDefinition, boolean includeTrailingComma) {
      List<TypeRef> types = elementDefinition.getTypes();
      if(types.size() > 0) {
        for (Iterator<TypeRef> iterator = types.iterator(); iterator.hasNext();) {
          TypeRef typeRef = iterator.next();
          String elementType = typeRef.getName();
          //block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
          //block.bs();
          if (elementType.equals("boolean")) {
            block.ln(generateTypeName(elementDefinition,typeRef) + ": Boolean,");
          } else if(elementType.equals("integer") || elementType.equals("decimal")) {
            block.ln(generateTypeName(elementDefinition,typeRef) + ": Number,");
          } else if(elementType.equals("instant") || elementType.equals("date") || elementType.equals("dateTime")) {
            block.ln(generateTypeName(elementDefinition,typeRef) + ": Date,");
          } else if(elementType.equals("string") || elementType.equals("uri") || elementType.equals("code")) {
            block.ln(generateTypeName(elementDefinition,typeRef) + ": String,");
          } else if(elementType.equals("Resource")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateResourceSchema(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
          } else if(elementType.equals("CodeableConcept")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateCodeableConceptSchema(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
          } else if(elementType.equals("Coding")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateCodingScheama(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
          } else if(elementType.equals("Age") || elementType.equals("Quantity") || elementType.equals("Count")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateQuantitySchema(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
          } else if(elementType.equals("HumanName")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateHumanNameSchema(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);          
          } else if(elementType.equals("Identifier")) {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            generateIdentifierSchema(block);
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);          
          } else {
            block.ln(generateTypeName(elementDefinition, typeRef) + generateTypeOpening(elementDefinition));
            block.bs();
            block.es();
            generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
          }
          //block.es();
          //generateTypeClosing(elementDefinition,block,iterator.hasNext(),includeTrailingComma);
//          if (elementDefinition.getMaxCardinality() == null) {
//            block.ln("}]" + (iterator.hasNext() || includeTrailingComma ? "," : ""));
//          } else {
//            block.ln("}" + (iterator.hasNext() || includeTrailingComma ? "," : ""));
//          }
        }
      } else if(types.size() == 0) {
        block.ln(generateTypeName(elementDefinition, null) + generateTypeOpening(elementDefinition));
        block.bs();
        for (Iterator<ElementDefn> iterator = elementDefinition.getElements().iterator(); iterator.hasNext();) {
          ElementDefn nestedElement = iterator.next();
          generateElement(block, nestedElement, iterator.hasNext());
        }
        block.es();
        if (elementDefinition.unbounded()) {
          block.ln("}]" + (includeTrailingComma ? "," : ""));
        } else {
          block.ln("}" + (includeTrailingComma ? "," : ""));
        }
      }
    }
    
    private String generateTypeName(ElementDefn elementDefinition, TypeRef type) {
      String elementName = elementDefinition.getName().replace("[x]", "");
      if (elementDefinition.getTypes().size() > 1) {
        String typeName = type.getName();
        typeName = Character.toUpperCase(typeName.charAt(0)) + typeName.substring(1);
        elementName += typeName;
      }
      if(elementName.equals("type")){
        elementName = "fhirType";
      } else if(elementName.equals("collection")){
        elementName = "fhirCollection";
      }
      return elementName;
    }
    
    private String generateTypeOpening(ElementDefn elementDefinition) {
      if (elementDefinition.unbounded()) {
        return ": [{";
      } else {
        return ": {";
      }
    }
    
    private void generateTypeClosing(ElementDefn elementDefinition, GenBlock block, boolean hasNext, boolean includeTrailingComma) {
      if (elementDefinition.unbounded()) {
        block.ln("}]" + (hasNext || includeTrailingComma ? "," : ""));
      } else {
        block.ln("}" + (hasNext || includeTrailingComma ? "," : ""));
      }
    }
    
    private void generateResourceSchema(GenBlock block) {
      generateValueSchema(block, "reference", true);
      generateValueSchema(block, "display", false);
    }
    
    private void generateCodeableConceptSchema(GenBlock block) {
      block.ln("coding: [{");
      block.bs();
      generateValueSchema(block, "system", true);
      generateValueSchema(block, "code", true);
      generateValueSchema(block, "display", false);
      block.es();
      block.ln("}]");
    }
    
    private void generateValueSchema(GenBlock block, String valueName, boolean includeTrailingComma) {
      block.ln(valueName + ": String"  + (includeTrailingComma ? "," : ""));
      //block.ln("}" + (includeTrailingComma ? "," : ""));
    }
    
    private void generateCodingScheama(GenBlock block) {
      generateValueSchema(block, "system", true);
      generateValueSchema(block, "code", true);
      generateValueSchema(block, "display", false);
    }
    
    private void generateQuantitySchema(GenBlock block) {
      generateValueSchema(block, "value", true);
      generateValueSchema(block, "units", true);
      generateValueSchema(block, "system", true);
      generateValueSchema(block, "code", false);
      
    }
    
    private void generateHumanNameSchema(GenBlock block) {
      generateValueSchema(block, "use", true);
      generateValueSchema(block, "text", true);
      block.ln("family: [String],");
      block.ln("given: [String],");
      block.ln("prefix: [String],");
      block.ln("suffix: [String]");
    }
    
    private void generateIdentifierSchema(GenBlock block) {
      generateValueSchema(block, "use", true);
      generateValueSchema(block, "label", true);
      generateValueSchema(block, "system", true);
      generateValueSchema(block, "value", false);
    }
}
