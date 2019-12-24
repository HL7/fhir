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
public class PrimitiveType extends DefinedCode {

  private String schemaType;
  private String v2;
  private String v3;
  private String regEx;
  private String jsonType;

  public PrimitiveType() {
    super();
  }

  public PrimitiveType(String code, String definition, String comment) {
    super(code, definition, comment);
  }

  public void setSchemaType(String value) {
    schemaType = value;
    
  }

  public String getSchemaType() {
    return schemaType;
  }

  
  public void setJsonType(String value) {
    jsonType = value;
    
  }

  @Override
  public String getJsonType() {
    return jsonType;
  }

  
  public void setRegex(String value)
  {
	regEx = value;  
  }

  @Override
  public String getRegex()
  {
	  return regEx;
  }

  public String getV2() {
    return v2;
  }

  public void setV2(String v2) {
    this.v2 = v2;
  }

  public String getV3() {
    return v3;
  }

  public void setV3(String v3) {
    this.v3 = v3;
  }

  public String getFHIRPathType() {
    String s = getCode();
    if (s.equals("boolean"))
      return "Boolean";
    if (s.equals("base64Binary"))
      return "String";
    if (s.equals("date"))
      return "Date";
    if (s.equals("dateTime"))
      return "DateTime";
    if (s.equals("time"))
      return "Time";
    if (s.equals("instant"))
      return "DateTime";
    if (s.equals("string"))
      return "String";
    if (s.equals("uri"))
      return "String";
    if (s.equals("integer"))
      return "Integer";
    if (s.equals("integer64"))
      return "Integer"; // see https://chat.fhir.org/#narrow/stream/179266-fhirpath/topic/Integer64
    if (s.equals("decimal"))
      return "Decimal";
    
    throw new Error("Unsupported type "+getCode());
  }

}
