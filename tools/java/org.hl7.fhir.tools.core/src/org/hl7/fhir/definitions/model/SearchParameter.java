package org.hl7.fhir.definitions.model;

import java.util.ArrayList;
import java.util.List;
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

public class SearchParameter {
  public enum SearchType {
    composite, // search parameter is a composite of others
    integer,  // search parameter must be a simple name 
    string,   // search parameter is a simple string, like a name part
    text,     // search parameter is into a long string - text filter
    date,     // search parameter is onto a date
    quantity, // search parameter is onto a quantity (= token + -lower & -upper, and canonical)
    reference,// search parameter refers to a resource reference
    token;   // search parameter is onto a pair of fixed value strings, space and value. Space is optional
  }
  
  private String code;
  private String description;
  private SearchType type;
  private List<String> paths = new ArrayList<String>();
  private List<String> composites = new ArrayList<String>();
  
  public String getCode() {
    return code;
  }
  
  public void setCode(String code) {
    this.code = code;
  }
  
  public String getDescription() {
    return description;
  }
  
  public SearchType getType() {
    return type;
  }
  
  public SearchParameter(String code, String description, SearchType type) {
    super();
    this.code = code;
    this.description = description;
    this.type = type;
  }
  
  public List<String> getPaths() {
    return paths;
  }

  public List<String> getComposites() {
    return composites;
  }

  public String getPathSummary() {
    StringBuilder b = new StringBuilder();
    for (String s : paths) {
      b.append(", "+s);
    }
    return b.length() == 0 ? "" : b.toString().substring(2);
  }
  
  
}
