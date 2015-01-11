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
import java.util.List;
import java.util.Map;

import org.hl7.fhir.instance.model.ConceptMap;
import org.hl7.fhir.instance.model.Profile;
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
public class Definitions {

  public static final String RIM_MAPPING = "http://hl7.org/v3";
  public static final String v2_MAPPING = "http://hl7.org/v2";
  public static final String LOINC_MAPPING = "http://loinc.org";
  public static final String SNOMED_MAPPING = "http://snomed.info";
  
  // todo: these binding registries that create global name uniqueness requirement need to be removed?
  // but global name uniqueness is still required? 
  private Map<String, BindingSpecification> bindings = new HashMap<String, BindingSpecification>();
  private List<BindingSpecification> commonBindings = new ArrayList<BindingSpecification>();

  // base definitions - types and resources of various kinds
  private Map<String, DefinedCode> primitives = new HashMap<String, DefinedCode>();
	private Map<String, ProfiledType> constraints = new HashMap<String, ProfiledType>();
	private Map<String, TypeDefn> types = new HashMap<String, TypeDefn>();
	private Map<String, TypeDefn> structures = new HashMap<String, TypeDefn>();
	private Map<String, TypeDefn> infrastructure = new HashMap<String, TypeDefn>();
  private Map<String, ResourceDefn> baseResources = new HashMap<String, ResourceDefn>();
  private Map<String, ResourceDefn> resources = new HashMap<String, ResourceDefn>();
  private Map<String, WorkGroup> workgroups = new HashMap<String, WorkGroup>();

	// conformance packages not owned by a particular resource
  private Map<String, ConformancePackage> packs = new HashMap<String, ConformancePackage>();

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
    if (resources.containsKey(name))
      root = resources.get(name).getRoot();
    return root != null;
  }

	// Returns a list of Bindings as found on the "Bindings" tab in
	// terminologies/bindings.xml and the "Binding" column on
	// CompositeTypes and Resources.
	public Map<String, BindingSpecification> getBindings() {
		return bindings;
	}


	public BindingSpecification getBindingByName(String name) {
		return bindings.get(name);
	}
	
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
	public Map<String, ConformancePackage> getConformancePackages() {
		return packs;
	}

  public BindingSpecification getBindingByReference(String ref, BindingSpecification other) {
    for (BindingSpecification b : bindings.values()) {
      if (ref.equals(b.getReference()) && other != b)
        return b;
    }
    return null;
  }
  
  public BindingSpecification getBindingByReference(String ref) {
    for (BindingSpecification b : bindings.values()) {
      if (ref.equals(b.getReference()))
        return b;
    }
    return null;
  }
  
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

  public List<BindingSpecification> getCommonBindings() {
    return commonBindings;
  }

  public List<String> getShared() {
    return shared;
  }

  private List<String> sortedNames;
  
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

  public Profile getSnapShotForType(String type) throws Exception {
    ResourceDefn r = getResourceByName(type);
    if (r == null)
      throw new Exception("unable to find base definition for "+type);
    if (r.getProfile().getSnapshot() != null)
      return r.getProfile();
    throw new Exception("unable to find snapshot for "+type);
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

//  public Profile getProfileByURL(String url) {
//    if (url.contains("#"))
//      url = url.substring(0, url.indexOf('#'));
//    for (ProfileDefn p : profiles.values())
//      if (p.getSource() != null && p.getSource().getUrl().equals(url))
//        return p.getSource();
//    for (ResourceDefn rd : resources.values()) {
//      for (RegisteredProfile p : rd.getProfiles()) {
//        if (p.getProfile().getSource().getUrl().equals(url)) {
//          return p.getProfile().getSource();
//        }
//      }
//    }
//    return null;
//  }
//
//  public Profile getSnapShotForProfile(String base) throws Exception {
//    String[] parts = base.split("#");
//    if (parts[0].startsWith("http://hl7.org/fhir/Profile/") && parts.length == 1) {
//      String name = base.substring(28);
//      if (hasType(name) || hasResource(name)) 
//        return getSnapShotForType(name);
//    }
//    Profile p = getProfileByURL(parts[0]);
//    if (p == null)
//      throw new Exception("unable to find base definition for "+base);
//    return p;
//  }
//
}
