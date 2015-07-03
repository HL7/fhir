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
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.ecore.fhir.BindingDefn;
import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.NamingSystem;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.ExtensionContext;
import org.hl7.fhir.instance.model.ValueSet;

/**
 * This class is the root to all the definitions in FHIR. There are the
 * following kinds of items: DefinedResources - a named list of resources that
 * are defined, with element definitions for the resources Known Resources - a
 * list of Resource names with their definitions for all known definitions,
 * whether defined or not Concept Domains - a list of concept domains Events - a
 * list of message events Documents - a list of defined document profiles
 * 
 * @author Grahame
 * 
 */
public class Definitions implements org.hl7.fhir.instance.utils.NameResolver {

  public static final String RIM_MAPPING = "http://hl7.org/v3";
  public static final String v2_MAPPING = "http://hl7.org/v2";
  public static final String LOINC_MAPPING = "http://loinc.org";
  public static final String SNOMED_MAPPING = "http://snomed.info";
  
  /// value sets
  private Map<String, BindingSpecification> commonBindings = new HashMap<String, BindingSpecification>();
  private Map<String, ValueSet> boundValueSets = new HashMap<String, ValueSet>(); // indexed by ValueSet.url
  private Set<BindingSpecification> unresolvedBindings = new HashSet<BindingSpecification>();
  private List<BindingSpecification> allBindings = new ArrayList<BindingSpecification>();

  // base definitions - types and resources of various kinds
  private Map<String, DefinedCode> primitives = new HashMap<String, DefinedCode>();
	private Map<String, ProfiledType> constraints = new HashMap<String, ProfiledType>();
	private Map<String, TypeDefn> types = new HashMap<String, TypeDefn>();
	private Map<String, TypeDefn> structures = new HashMap<String, TypeDefn>();
	private Map<String, TypeDefn> infrastructure = new HashMap<String, TypeDefn>();
  private Map<String, ResourceDefn> baseResources = new HashMap<String, ResourceDefn>();
  private Map<String, ResourceDefn> resources = new HashMap<String, ResourceDefn>();
  private Map<String, WorkGroup> workgroups = new HashMap<String, WorkGroup>();

	// profiles not owned by a particular resource
  private Map<String, ImplementationGuide> igs = new HashMap<String, ImplementationGuide>();
  private List<ImplementationGuide> sortedIgs = new ArrayList<ImplementationGuide>();
  private List<Profile> packList = new ArrayList<Profile>();
  private Map<String, Profile> packMap = new HashMap<String, Profile>();
  private Map<String, Dictionary> dictionaries = new HashMap<String, Dictionary>();

  // indexes of above
  private Map<String, DefinedCode> knownResources = new HashMap<String, DefinedCode>();
  private List<TypeRef> knownTypes = new ArrayList<TypeRef>();
  private Map<String, ArrayList<String>> statusCodes = new HashMap<String, ArrayList<String>>();

  // access to raw resources - to be removed and replaced by worker context at some stage
  private Map<String, ValueSet> valuesets = new HashMap<String, ValueSet>();
  private Map<String, ConceptMap> conceptMaps = new HashMap<String, ConceptMap>();
  private Map<String, ValueSet> codeSystems = new HashMap<String, ValueSet>();
  private Map<String, ValueSet> extraValuesets = new HashMap<String, ValueSet>();

  // other miscellaineous lists
  private List<String> deletedResources = new ArrayList<String>();
  private List<String> shared = new ArrayList<String>(); 
  private List<String> aggregationEndpoints = new ArrayList<String>();
  private Map<String, EventDefn> events = new HashMap<String, EventDefn>();
  private Map<String, String> diagrams = new HashMap<String, String>();
  private Map<String, MappingSpace> mapTypes = new HashMap<String, MappingSpace>();
  private List<Compartment> compartments = new ArrayList<Compartment>();
  private List<String> pastVersions = new ArrayList<String>();
  private Map<String, String> TLAs = new HashMap<String, String>();

  private Map<String, W5Entry> w5s = new HashMap<String, W5Entry>();
  private Map<String, String> typePages = new HashMap<String, String>();
  private Map<String, String> pageTitles = new HashMap<String, String>();
  private Map<String, Set<String>> searchRules = new HashMap<String, Set<String>>();
  
  // Returns the root TypeDefn of a CompositeType or Resource,
	// excluding future Resources (as they don't have definitions yet).
	public TypeDefn getElementDefn(String name) throws Exception {
    
		TypeDefn root = null;
		if (types.containsKey(name))
			root = types.get(name);
		if (structures.containsKey(name))
			root = structures.get(name);
		if (infrastructure.containsKey(name))
			root = infrastructure.get(name);
    if (baseResources.containsKey(name))
      return baseResources.get(name).getRoot();
		if (resources.containsKey(name))
			root = resources.get(name).getRoot();
		if (root == null)
			throw new Exception("unable to find resource or composite type " + name);
		return root;
	}

  // Returns true if the root ElementDefn of a CompositeType or Resource can be found, 
  // excluding future Resources (as they don't have definitions yet).
  public boolean hasElementDefn(String name) {
    ElementDefn root = null;
    if (types.containsKey(name))
      root = types.get(name);
    if (structures.containsKey(name))
      root = structures.get(name);
    if (infrastructure.containsKey(name))
      root = infrastructure.get(name);
    if (baseResources.containsKey(name))
      root = baseResources.get(name).getRoot();
    if (resources.containsKey(name))
      root = resources.get(name).getRoot();
    return root != null;
  }

//	// Returns a list of Bindings as found on the "Bindings" tab in
//	// terminologies/bindings.xml and the "Binding" column on
//	// CompositeTypes and Resources.
//	public Map<String, BindingSpecification> getBindings() {
//		return bindings;
//	}
//
//
//	public BindingSpecification getBindingByName(String name) {
//		return bindings.get(name);
//	}
	
	// Returns all PrimitiveTypes (both imported and with a
	// restriction pattern as found in the primitives.xls
	// file on the "Imports" and "String Patterns" tab.
	public Map<String, DefinedCode> getPrimitives() {
		return primitives;
	}

	// Returns the list of names (as codes) of all ConstrainedTypes.
	// These ConstrainedTypes are found in the fhir.ini
	// as <constrained>=<base> and the constraints are found
	// on the "Restrictions" tab of the <base>.
	public Map<String, ProfiledType> getConstraints() {
		return constraints;
	}

	// List the CompositeTypes as found under [types] that aren't
	// ConstrainedTypes.
	public Map<String, TypeDefn> getTypes() {
		return types;
	}

	// List the CompositeTypes as found under [structures] that aren't
	// ConstrainedTypes.
	public Map<String, TypeDefn> getStructures() {
		return structures;
	}

	// List the CompositeTypes as found under [infrastructure] that aren't
	// ConstrainedTypes.
	public Map<String, TypeDefn> getInfrastructure() {
		return infrastructure;
	}

	// A list of names of resources under [special-resources]
	public List<String> getAggregationEndpoints() {
		return aggregationEndpoints;
	}

	// List of resources, excluding future resources
	public Map<String, ResourceDefn> getResources() {
		return resources;
	}
	

	public ResourceDefn getResourceByName(String name) throws Exception {
		ResourceDefn root = null;
		if (resources.containsKey(name))
			root = resources.get(name);
    if (root == null)
      root = baseResources.get(name);
		if (root == null)
			throw new Exception("unable to find resource '" + name+"'");
		return root;
	}

	public boolean hasResource(String name) {
		return resources.containsKey(name);
	}
	
	
	// List of all names of Resources (as code), including "future" resources
	// (but not special resources, as these aren't resources)
	public Map<String, DefinedCode> getKnownResources() {
		return knownResources;
	}

	// List of all CompositeTypes (constrained and unconstrained)
	// and PrimitiveTypes (both imported and with restrictions)
	public List<TypeRef> getKnownTypes() {
		return knownTypes;
	}

	public boolean hasType(String name) {
		for (TypeRef td : knownTypes) {
			if (td.getName().equals(name))
				return true;
		}

		return false;
	}

	// List of Events as collected from the "Events" tab of the Resources
	public Map<String, EventDefn> getEvents() {
		return events;
	}

  // Returns all defined Profiles, which are the profiles found
  // under [profiles] in fhir.ini
  public Map<String, Profile> getPackMap() {
    return packMap;
  }

  public List<Profile> getPackList() {
    return packList;
  }

//  public BindingSpecification getBindingByReference(String ref, BindingSpecification other) {
//    for (BindingSpecification b : bindings.values()) {
//      if (ref.equals(b.getReference()) && other != b)
//        return b;
//    }
//    return null;
//  }
//  
//  public BindingSpecification getBindingByReference(String ref) {
//    for (BindingSpecification b : bindings.values()) {
//      if (ref.equals(b.getReference()))
//        return b;
//    }
//    return null;
//  }
//  
  public boolean dataTypeIsSharedInfo(String name)  {
    try {
      return hasElementDefn(name) && getElementDefn(name).typeCode().equals("SharedDefinition");
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public List<String> getDeletedResources() {
    return deletedResources;
  }

  public Map<String, String> getDiagrams() {
    return diagrams;
  }
  
  public Map<String, ResourceDefn> getBaseResources() {
    return baseResources;
  }

  public Map<String, BindingSpecification> getCommonBindings() {
    return commonBindings;
  }

  public List<String> getShared() {
    return shared;
  }

  private List<String> sortedNames;
  private List<String> vsFixups = new ArrayList<String>();
  private List<NamingSystem> namingSystems = new ArrayList<NamingSystem>();
  
  public List<String> sortedResourceNames() {
    if (sortedNames == null) {
      sortedNames = new ArrayList<String>();
      sortedNames.addAll(getResources().keySet());
      Collections.sort(sortedNames);
    }
    return sortedNames;
  }

  public Map<String, ConceptMap> getConceptMaps() {
    return conceptMaps;
  }

  public Map<String, ValueSet> getValuesets() {
    return valuesets;
  }

  public Map<String, ValueSet> getCodeSystems() {
    return codeSystems;
  }

  public Map<String, ValueSet> getExtraValuesets() {
    return extraValuesets;
  }

  public List<Compartment> getCompartments() {
    return compartments;
  }

  public Compartment getCompartmentByName(String n) {
    for (Compartment c : compartments)
      if (c.getName().equals(n))
        return c;
    return null;
  }

  public boolean hasPrimitiveType(String name) {
    return primitives.containsKey(name);
  }
  
  public Map<String, ArrayList<String>> getStatusCodes() {
    return statusCodes;
  }

  public List<String> getPastVersions() {
    return pastVersions;
  }

  public Map<String, MappingSpace> getMapTypes() {
    return mapTypes;
  }

  public StructureDefinition getSnapShotForType(String type) throws Exception {
    ElementDefn e = getElementDefn(type); 
    if (e != null && e instanceof TypeDefn) {
      TypeDefn t = (TypeDefn) e;
      if (t.getProfile().getSnapshot() != null)
        return t.getProfile();
      throw new Exception("unable to find snapshot for "+type);
      
    }
    ResourceDefn r = getResourceByName(type);
    if (r != null) {
      if (r.getProfile().getSnapshot() != null)
        return r.getProfile();
      throw new Exception("unable to find snapshot for "+type);
    }
    throw new Exception("unable to find base definition for "+type);
  }

  public StructureDefinition getSnapShotForBase(String base) throws Exception {
    StructureDefinition p = getProfileByURL(base);
    if (p == null)
      throw new Exception("unable to find base definition "+base);
    if (p.getSnapshot() != null)
      return p;
    throw new Exception("unable to find snapshot for "+base);
  }

  private StructureDefinition getProfileByURL(String base) throws Exception {
    for (ResourceDefn r : resources.values()) {
      if (r.getProfile().getUrl().equals(base))
        return r.getProfile();
      for (Profile cp : r.getConformancePackages()) {
        for (ConstraintStructure p : cp.getProfiles()) {
          if (p.getResource() != null && base.equals(p.getResource().getUrl()))
            return p.getResource();
        }
      }
    }
    for (Profile cp : packList) {
      for (ConstraintStructure p : cp.getProfiles()) {
        if (p.getResource() != null && base.equals(p.getResource().getUrl()))
          return p.getResource();
      }      
    }
    if (base.startsWith("http://hl7.org/fhir/StructureDefinition/") && hasType(base.substring(40))) {
      TypeDefn t = getElementDefn(base.substring(40));
      return t.getProfile();
    }
    return null;
  }

  public String getSourceFile(String type) {
    return null;
  }

  public Map<String, WorkGroup> getWorkgroups() {
    return workgroups;
  }

  public Map<String, String> getTLAs() {
    return TLAs;
  }

  public Map<String, W5Entry> getW5s() {
    return w5s;
  }

  public String getSrcFile(String name) throws Exception {
    if (name == null)
      throw new Exception("unknown null type");
    String lname = name.toLowerCase();
    if (typePages.containsKey(lname))
      return typePages.get(lname);
    if (hasType(name))
      return "datatypes";
    return lname;
  }

  public Map<String, String> getTypePages() {
    return typePages;
  }

  public Map<String, ImplementationGuide> getIgs() {
    return igs;
  }

  public Map<String, Dictionary> getDictionaries() {
    return dictionaries;
  }

  public List<ImplementationGuide> getSortedIgs() {
    return sortedIgs;
  }

  public ImplementationGuide getUsageIG(String usage, String context) throws Exception {
    if (!igs.containsKey(usage))
      throw new Exception("Attempt to use an undefined implementation guide '"+usage+"' @ "+context);
    return igs.get(usage);
  }

  public void checkContextValid(ExtensionContext contextType, String value, String context) throws Exception {
    if (contextType == ExtensionContext.DATATYPE) {
      if (value.equals("*") || value.equals("Any"))
        return;
      if (primitives.containsKey(value))
        return;
      String[] parts = value.split("\\.");
      if (hasType(parts[0]) && getElementByPath(parts) != null)
        return;
      
      throw new Error("The data type context '"+value+"' is not valid @ "+context);
      
    } else if (contextType == ExtensionContext.RESOURCE) {
      if (value.startsWith("@"))
        value = value.substring(1);
      if (value.equals("*") || value.equals("Any"))
        return;
      String[] parts = value.split("\\.");
      if (sortedResourceNames().contains(value))
        return;
      if (getElementByPath(parts) != null)
        return;
      
      throw new Error("The resource context '"+value+"' is not valid @ "+context);
    } else
    throw new Error("not checked yet @ "+context);
    
  }

  private Object getElementByPath(String[] parts) throws Exception {
    ElementDefn e;
    try {
      e = getElementDefn(parts[0]);
    } catch (Exception e1) {
     return null;
    }
    int i = 1;
    while (e != null && i < parts.length) {
      if (e.getAcceptableGenericTypes().isEmpty() && hasType(e.typeCode()))
        e = getElementDefn(e.typeCode());
      e = e.getElementByName(parts[i], true, this);
      i++;
    }
    return e;
  }

  public Map<String, String> getPageTitles() {
    return pageTitles;
  }

  public Map<String, ValueSet> getBoundValueSets() {
    return boundValueSets;
  }

  public Set<BindingSpecification> getUnresolvedBindings() {
    return unresolvedBindings;
  }

  public List<BindingSpecification> getAllBindings() {
    return allBindings;
  }

  @Override
  public boolean isResource(String name) {
    return hasResource(name);
  }

  public boolean hasLogicalModel(String name) {
    for (ImplementationGuide ig : getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        if (lm.getResource().getName().equals(name))
          return true;
        if (lm.getId().equals(name))
          return true;
      }
    }
    return false;
  }

  public ImplementationGuide getIGforLogicalModel(String name) {
    for (ImplementationGuide ig : getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        if (lm.getResource().getName().equals(name))
          return ig;
        if (lm.getId().equals(name))
          return ig;
      }
    }
    return null;
  }

  public LogicalModel getLogicalModel(String name) {
    for (ImplementationGuide ig : getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        if (lm.getResource().getName().equals(name))
          return lm;
        if (lm.getId().equals(name))
          return lm;
      }
    }
    return null;
  }

  public List<String> getVsFixups() {
    return vsFixups;
  }

  public void seachRule(String type, String searchTypes) {
    Set<String> set = new HashSet<String>();
    for (String s : searchTypes.split(",")) {
      set.add(s);
    }
    searchRules.put(type, set);
  }

  public Map<String, Set<String>> getSearchRules() {
    return searchRules;
  }

  public List<NamingSystem> getNamingSystems() {
    return namingSystems ;
  }

  
}
