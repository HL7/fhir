package org.hl7.fhir.definitions.model;

import java.util.HashMap;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;

/*
Copyright (c) 2011-2014, HL7, Inc
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
    Spreadsheet, Profile;
  }

  private String name;
  private String description;
  private String sourceFilename;
  private String destFilename;
  private String filepath;
  private ProfileDefn profile;
  private ProfileInputType type;
  private Profile resource;
  private Map<String, Example> examples = new HashMap<String, Example>();

  public RegisteredProfile(String name, String description, String destFilename, String sourceFilename, String filepath, ProfileInputType type) {
    super();
    this.name = name;
    this.description = description;
    this.sourceFilename = sourceFilename;
    this.destFilename = destFilename;
    this.filepath = filepath;
    this.type= type;
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
  public String getSourceFilename() {
    return sourceFilename;
  }
  public String getDestFilename() {
    return destFilename;
  }
  public ProfileDefn getProfile() {
    return profile;
  }
  public String getFilepath() {
    return filepath;
  }

  public ProfileInputType getType() {
    return type;
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

  public String getTitle() {
    return destFilename.contains(".") ? destFilename.substring(0, destFilename.lastIndexOf(".")) : destFilename;
  }

  public String getDestFilenameNoExt() {
    return getTitle();
  }

  public Map<String, Example> getExamples() {
    return examples;
  }



}
