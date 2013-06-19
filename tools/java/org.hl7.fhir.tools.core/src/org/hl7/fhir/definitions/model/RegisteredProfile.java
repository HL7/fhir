package org.hl7.fhir.definitions.model;

import org.hl7.fhir.instance.model.Profile;

/*
Copyright (c) 2011-2013, HL7, Inc
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

/**
 * a profile registered against a resource as part of FHIR itself
 *   
 * @author Grahame
 *
 */
public class RegisteredProfile {

  public enum ProfileInputType {
    Spreadsheet;
  }

  private String name;
  private String description;
  private String filename;
  private String filepath;
  private ProfileDefn profile;
  private String example;
  private ProfileInputType type;
  private String examplePath;
  private Profile resource;
  
  
  public RegisteredProfile(String name, String description, String filename, String filepath, ProfileInputType type, String example, String examplePath) {
    super();
    this.name = name;
    this.description = description;
    this.filename = filename;
    this.filepath = filepath;
    this.type= type;
    this.example = example;
    this.examplePath = examplePath;
  }
  
  public String getName() {
    return name;
  }
  public String getDescription() {
    return description;
  }
  public void setDescription(String description) {
    this.description = description;
  }
  public String getFilename() {
    return filename;
  }
  public ProfileDefn getProfile() {
    return profile;
  }
  public String getFilepath() {
    return filepath;
  }

  public String getExample() {
    return example;
  }

  public ProfileInputType getType() {
    return type;
  }

  public String getExamplePath() {
    return examplePath;
  }

  public void setProfile(ProfileDefn profile) {
    this.profile = profile;
  }

  public Profile getResource() {
    return resource;
  }

  public void setResource(Profile resource) {
    this.resource = resource;
  }
  

 
}
