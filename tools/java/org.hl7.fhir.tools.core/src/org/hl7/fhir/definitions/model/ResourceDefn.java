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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.utilities.Utilities;

public class ResourceDefn  {
  public class StringPair {
    public String name;
    public String value;
  }

  private class InheritedMapping {
    private String path;
    private Map<String, String> mappings = new HashMap<String, String>();
  }

  private List<Example> examples = new ArrayList<Example>();
  private Map<String, SearchParameterDefn> searchParams = new HashMap<String, SearchParameterDefn>();
  private List<Operation> operations = new ArrayList<Operation>();
  private List<Profile> conformancePackages = new ArrayList<Profile>();

  private String status;
  private boolean abstract_;
  private WorkGroup wg;
  private Profile conformancePack;

  private String name = null;
  private String enteredInErrorStatus;
  private String fmmLevel;
  private String fmmLevelNoWarnings;

  private List<InheritedMapping> inheritedMappings = new ArrayList<InheritedMapping>();

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }


  private String definition = null;

  public String getDefinition()
  {
    return definition;
  }

  public void setDefinition(String def)
  {
    this.definition = def;
  }


  // EK: This function supports the new eCore model
  // It it still defined in terms of the old functionality,
  // we need to refactor all references to getContents()
  // out of all generators.
  public List<ElementDefn> getContents()
  {
    if( getRoot() != null )
      return getRoot().getElements();
    else
      return new ArrayList<ElementDefn>();
  }





  private TypeDefn root;   

  public TypeDefn getRoot()
  {
    return root;
  }

  public void setRoot(TypeDefn root)
  {
    this.root = root;
  }


  private boolean forFutureUse = false;
  private String requirements;
  private boolean publishedInProfile;

  public boolean isForFutureUse()
  {
    return forFutureUse;
  }

  public void setForFutureUse(boolean future)
  {
    forFutureUse = future;
  }

  public List<Example> getExamples() {
    return examples;
  }

  public Map<String, SearchParameterDefn> getSearchParams() {
    return searchParams;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  } 

  public void setRequirements(String text) {
    this.requirements = text;

  }

  public String getRequirements() {
    return requirements;
  }

  public StructureDefinition getProfile() {
    return root.getProfile();
  }

  public void setProfile(StructureDefinition profile) {
    this.root.setProfile(profile);
  }

  public List<Operation> getOperations() {
    return operations;
  }


  public boolean isPublishedInProfile() {
    return publishedInProfile;
  }

  public void setPublishedInProfile(boolean value) {
    publishedInProfile = value;
  }

  public List<Profile> getConformancePackages() {
    return conformancePackages;
  }

  public boolean isAbstract() {
    return abstract_;
  }

  public void setAbstract(boolean abstract_) {
    this.abstract_ = abstract_;
  }

  public WorkGroup getWg() {
    return wg;
  }

  public void setWg(WorkGroup wg) {
    this.wg = wg;
  }

  public Profile getConformancePack() {
    return conformancePack;
  }

  public void setConformancePack(Profile conformancePack) {
    this.conformancePack = conformancePack;
  }

  public String getEnteredInErrorStatus() {
    return enteredInErrorStatus;
  }

  public void setEnteredInErrorStatus(String enteredInErrorStatus) {
    this.enteredInErrorStatus = enteredInErrorStatus;
  }

  public void addMapping(String path, String map, String value) {
    if (!Utilities.noString(value)) {
      InheritedMapping im = null;
      for (InheritedMapping t : inheritedMappings) {
        if (t.path.equals(path)) 
          im = t;
      }
      if (im == null) {
        im = new InheritedMapping();
        inheritedMappings.add(im);
      }
      im.path = path;
      im.mappings.put(map, value);  
    }
  }

  public List<StringPair> getMappings(String m) {
    List<StringPair> results = new ArrayList<ResourceDefn.StringPair>();
    for (InheritedMapping im : inheritedMappings) {
      if (im.mappings.containsKey(m)) {
        StringPair p = new StringPair();
        p.name = im.path;
        p.value = im.mappings.get(m);
        results.add(p);
      }
    }
    return results;
  }

  public String getFmmLevel() {
    return fmmLevel;
  }

  public void setFmmLevel(String fmmLevel) {
    this.fmmLevel = fmmLevel;
  }

  public String getFmmLevelNoWarnings() {
    return fmmLevelNoWarnings;
  }

  public void setFmmLevelNoWarnings(String fmmLevelNoWarnings) {
    this.fmmLevelNoWarnings = fmmLevelNoWarnings;
  }

  public Profile getConformancePackage(String id) {
    for (Profile p : conformancePackages)
      if (p.getId().equals(id))
        return p;
    return null;
  }
  
  
}
