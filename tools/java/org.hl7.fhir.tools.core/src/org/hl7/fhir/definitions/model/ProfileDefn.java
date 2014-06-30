package org.hl7.fhir.definitions.model;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.Profile;

public class ProfileDefn {

  // for use within the tool
  private Profile source;

  // transient loading details, if loaded from a spreadsheet
  private List<ResourceDefn> resources = new ArrayList<ResourceDefn>();
  private Map<String, ArrayList<String>> metadata = new HashMap<String, ArrayList<String>>();
  private List<ExtensionDefn> extensions = new ArrayList<ExtensionDefn>();
  private List<BindingSpecification> bindings = new ArrayList<BindingSpecification>();
  
 
  
  
  public Map<String, ArrayList<String>> getMetadata() {
    return metadata;
  }

  public List<ResourceDefn> getResources() {
    return resources;
  }

  
  public String metadata(String name) {
    if (source != null) {
      if ("description".equals(name))
        return source.getDescriptionSimple();
      if ("name".equals(name))
        return source.getNameSimple();
      if ("date".equals(name))
        return source.getDateSimple().toString();
      if ("status".equals(name))
        return source.getStatusSimple().toCode();
      if ("author.name".equals(name))
        return source.getPublisherSimple();
      if ("url".equals(name))
        return source.getUrlSimple();
//      throw new Error("metadata request for "+name);
    }
    if (!metadata.containsKey(name))
      return "";
    ArrayList<String> a = metadata.get(name);
    if (a.size() == 1) 
      return a.get(0);
    else
      return "";
  }

  public boolean hasMetadata(String name) {
    String s = metadata(name);
    return (s != null && !s.equals(""));
  }

  public void putMetadata(String name, String value) {
    ArrayList<String> a;
    if (metadata.containsKey(name))
      a = metadata.get(name);
    else {
      a = new ArrayList<String>();
      metadata.put(name, a);
    }
    a.add(value);
  }

  public void forceMetadata(String name, String value) {
    if (metadata.containsKey(name))
      metadata.remove(name);
    ArrayList<String> a = new ArrayList<String>();
    metadata.put(name, a);
    a.add(value);
  }

  public List<ExtensionDefn> getExtensions() {
    return extensions;
  }

  public List<BindingSpecification> getBindings() {
    return bindings;
  }

  public Profile getSource() {
    return source;
  }

  public void setSource(Profile source) {
    this.source = source;
  }

  public void addMetadata(String name, String value) {
    ArrayList<String> values = new ArrayList<String>();
    values.add(value);
    metadata.put(name, values);
  }


  
  
}
