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
import java.io.File;

import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;

public abstract class ResourceGenerator {
  protected String name;
  protected File outputFile;
  protected Definitions definitions;
  protected ElementDefn textElement;
  
  public ResourceGenerator(String name, Definitions definitions, File outputFile) {
    this.name = name;
    this.definitions = definitions;
    this.outputFile = outputFile;
    
    // The <text/> element is really inherited from the root Resource...
    // probably a better way to do this...
    this.textElement = new ElementDefn();
    this.textElement.setName("text");
    this.textElement.setMinCardinality(0);
    this.textElement.setMaxCardinality(1);
    this.textElement.setDeclaredTypeName("Narrative");
    this.textElement.getTypes().add(new TypeRef("Narrative"));
  }

  protected String getEmbeddedClassName(ElementDefn elementDefinition, String resourceName) {
    String cname = elementDefinition.getDeclaredTypeName();
    return resourceName + Character.toUpperCase(cname.charAt(0)) + cname.substring(1);
  }

  protected TypeDefn getRootDefinition() {
    TypeDefn el;
    ResourceDefn resource = definitions.getResources().get(name);
    if (resource != null) {
      el = resource.getRoot();
    } else {
      el = definitions.getInfrastructure().get(name);
      el = (el == null) ? definitions.getTypes().get(name) : el;
      el = (el == null) ? definitions.getStructures().get(name) : el;
    }
    return el;
  }

  protected abstract String generateTypeName(ElementDefn elementDefinition, TypeRef type);
}
