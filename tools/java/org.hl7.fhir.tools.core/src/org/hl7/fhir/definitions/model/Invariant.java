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
public class Invariant {

  private String context;
  private String english;
  private String ocl;
  private String xpath;
  private String id;
  private String fixedName; // when the invariant is only one one of a set of choices
  private String severity;
  private String turtle;
  private String requirements;
  
  public String getId()
  {
	  return id;
  }
  
  public void setId( String id )
  {
	  this.id = id;
  }
  

  public String getRequirements() {
    return requirements;
  }

  public void setRequirements(String requirements) {
    this.requirements = requirements;
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    this.context = context;
  }

  public String getEnglish() {
    return english;
  }

  public void setEnglish(String english) {
    this.english = english;
  }

  public String getOcl() {
    return ocl;
  }

  public void setOcl(String ocl) {
    this.ocl = ocl;
  }

  public String getXpath() {
    return xpath;
  }

  public void setXpath(String xpath) {
    this.xpath = xpath;
  }

  public String getFixedName() {
    return fixedName;
  }

  public void setFixedName(String fixedName) {
    this.fixedName = fixedName;
  }

  public String getSeverity() {
    return severity;
  }

  public void setSeverity(String value) {
    this.severity = value;    
  }

  public String getTurtle() {
    return turtle;
  }

  public void setTurtle(String turtle) {
    this.turtle = turtle;
  }

  
  
}
