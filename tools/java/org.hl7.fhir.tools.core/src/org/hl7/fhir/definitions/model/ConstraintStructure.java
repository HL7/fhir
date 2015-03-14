package org.hl7.fhir.definitions.model;
/*
Copyright (c) 2011+, HL7, Inc
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
import org.hl7.fhir.instance.model.StructureDefinition;

// publishing details about a profile + the profile
public class ConstraintStructure {

  private String title; // human readable name
  private String id; // id in the resource, which is also the file name root
  private StructureDefinition resource;
  private ResourceDefn defn; // temporary, until we get around to building the resource 
  private ImplementationGuide usage;
    
  public ConstraintStructure(StructureDefinition resource, ImplementationGuide usage) {
    this.id = resource.getId();
    this.title = resource.getName();
    this.resource = resource;
    if (usage == null)
      throw new Error("No usage on profile on "+resource.getName());
    this.usage = usage;
  }

  public ConstraintStructure(String id, String title, ResourceDefn defn, ImplementationGuide usage) {
    this.id = id;
    this.title = title;
    this.defn = defn;
    this.usage = usage;
    if (usage == null)
      throw new Error("No usage on profile "+id+" ("+title+"):");
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StructureDefinition getResource() {
    return resource;
  }

  public void setResource(StructureDefinition resource) {
    this.resource = resource;
  }

  public ResourceDefn getDefn() {
    return defn;
  }

  public void setDefn(ResourceDefn defn) {
    this.defn = defn;
  }

  public ImplementationGuide getUsage() {
    return usage;
  }

  public void setUsage(ImplementationGuide usage) {
    this.usage = usage;
  }

  
  
}
