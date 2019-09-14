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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.igtools.spreadsheets.MappingSpace;
import org.hl7.fhir.igtools.spreadsheets.TypeRef;
import org.hl7.fhir.r5.context.IWorkerContext;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.ConceptMap;
import org.hl7.fhir.r5.model.NamingSystem;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.r5.model.StructureDefinition.ExtensionContextType;
import org.hl7.fhir.r5.model.StructureDefinition.StructureDefinitionContextComponent;
import org.hl7.fhir.r5.model.TypeDetails;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.utils.FHIRPathEngine;
import org.hl7.fhir.utilities.Utilities;

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

  public class NamespacePair {

    public String desc;
    public String page;
    private boolean notUnique;

    public NamespacePair(String desc, String page, boolean notUnique) {
      if (page == null)
        throw new Error("Page is null for "+desc); 
      this.desc = desc;
      this.page = page;
      this.notUnique = notUnique;
    }

  }

  public class PageInformation {

    private String fmm;
    private String wg;
    private String wgCode;
    private String name;

    public PageInformation(String name) {
      super();
      this.name = name;
    }

    public String getFmm() {
      return fmm;
    }

    public void setFmm(String fmm) {
      this.fmm = fmm;
    }

    public String getWg() {
      return wg;
    }

    public void setWg(String wg) {
      this.wg = wg;
    }

    public String getWgCode() {
      return wgCode;
    }

    public void setWgCode(String code) {
      wgCode = code;
    }

    public String getName() {
      return name;
    }

    public void setName(String name) {
      this.name = name;
    }

    
  }

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
  private Map<String, ResourceDefn> resourceTemplates = new HashMap<String, ResourceDefn>();
  private Map<String, WorkGroup> workgroups = new HashMap<String, WorkGroup>();

	// profiles not owned by a particular resource
  private Map<String, ImplementationGuideDefn> igs = new HashMap<String, ImplementationGuideDefn>();
  private List<ImplementationGuideDefn> sortedIgs = new ArrayList<ImplementationGuideDefn>();
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
  private Map<String, CodeSystem> codeSystems = new HashMap<String, CodeSystem>();
  private Map<String, ValueSet> extraValuesets = new HashMap<String, ValueSet>();
  private Set<String> styleExemptions = new HashSet<String>();

  // other miscellaineous lists
  private List<String> shared = new ArrayList<String>(); 
  private List<String> aggregationEndpoints = new ArrayList<String>();
  private Map<String, EventDefn> events = new HashMap<String, EventDefn>();
  private Map<String, String> diagrams = new HashMap<String, String>();
  private Map<String, MappingSpace> mapTypes = new HashMap<String, MappingSpace>();
  private List<Compartment> compartments = new ArrayList<Compartment>();
  private List<String> pastVersions = new ArrayList<String>();
  private Map<String, String> TLAs = new HashMap<String, String>();

  private Map<String, W5Entry> w5s = new HashMap<String, W5Entry>();
  private List<W5Entry> w5list = new ArrayList<W5Entry>();
  private Map<String, String> typePages = new HashMap<String, String>();
  private Map<String, String> pageTitles = new HashMap<String, String>();
  private Map<String, Set<String>> searchRules = new HashMap<String, Set<String>>();
  private Map<String, CommonSearchParameter> commonSearchParameters = new HashMap<String, CommonSearchParameter>();
  private Map<String, NamespacePair> redirectList = new HashMap<String, NamespacePair>();
  
  
  // Returns the root TypeDefn of a CompositeType or Resource,
	// excluding future Resources (as they don't have definitions yet).
	public TypeDefn getElementDefn(String name) throws Exception {
    if (name.equals("Type") || name.equals("Structure"))
      name = "Element";
    
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
		if (hasLogicalModel(name))
		  root = getLogicalModel(name).getResource().getRoot();
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
  
  // List of resources, excluding future resources
  public Map<String, ResourceDefn> getResourceTemplates() {
    return resourceTemplates;
  }
  

	public ResourceDefn getResourceByName(String name) throws FHIRException {
		ResourceDefn root = null;
		if (resources.containsKey(name))
			root = resources.get(name);
    if (root == null)
      root = baseResources.get(name);
		if (root == null)
			throw new FHIRException("unable to find resource '" + name+"'");
		return root;
	}

	public boolean hasResource(String name) {
		return resources.containsKey(name) || baseResources.containsKey(name);
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

  private List<String> sortedNames, sortedTypeNames;
  private List<String> vsFixups = new ArrayList<String>();
  private List<NamingSystem> namingSystems = new ArrayList<NamingSystem>();
  private Set<String> structuralPages = new HashSet<String>();
  private Map<String, PageInformation> pageInfo = new HashMap<String, Definitions.PageInformation>();
  private Map<String, ConstraintStructure> profileIds = new HashMap<String, ConstraintStructure>();
  private boolean loaded;
  private int valueSetCount;
  
  public List<String> sortedResourceNames() {
    if (sortedNames == null) {
      sortedNames = new ArrayList<String>();
      sortedNames.addAll(getResources().keySet());
      Collections.sort(sortedNames);
    }
    return sortedNames;
  }

  public List<String> sortedTypeNames() {
    if (sortedTypeNames == null) {
      sortedTypeNames = new ArrayList<String>();
      sortedTypeNames.addAll(getTypes().keySet());
      sortedTypeNames.addAll(getStructures().keySet());
      sortedTypeNames.addAll(getInfrastructure().keySet());
      Collections.sort(sortedTypeNames);
    }
    return sortedTypeNames;
  }

  public Map<String, ConceptMap> getConceptMaps() {
    return conceptMaps;
  }

  public Map<String, ValueSet> getValuesets() {
    return valuesets;
  }

  public Map<String, CodeSystem> getCodeSystems() {
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
      if (c.getName().equalsIgnoreCase(n))
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

  public Map<String, WorkGroup> getWorkgroups() {
    return workgroups;
  }

  public Map<String, String> getTLAs() {
    return TLAs;
  }

  public Map<String, W5Entry> getW5s() {
    return w5s;
  }

  public String getSrcFile(String name) {
    if (name == null)
      throw new Error("unknown null type");
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

  public Map<String, ImplementationGuideDefn> getIgs() {
    return igs;
  }

  public Map<String, Dictionary> getDictionaries() {
    return dictionaries;
  }

  public List<ImplementationGuideDefn> getSortedIgs() {
    return sortedIgs;
  }

  public ImplementationGuideDefn getUsageIG(String usage, String context) throws Exception {
    if (!igs.containsKey(usage))
      throw new Exception("Attempt to use an undefined implementation guide '"+usage+"' @ "+context);
    return igs.get(usage);
  }

  public void checkContextValid(StructureDefinitionContextComponent ec, String context, IWorkerContext worker) throws Exception {
    if (ec.getType() == ExtensionContextType.ELEMENT) {
      if (ec.getExpression().equals("*")) {
        ec.setExpression("Element");
      }
      if (ec.getExpression().equals("Any")) {
        ec.setExpression("Element");
      }

      if (ec.getExpression().equals("Element")) {
        return;
      }
      if (ec.getExpression().equals("Resource")) {
        return;
      }
      
      if (primitives.containsKey(ec.getExpression()))
        return;
      String[] parts = ec.getExpression().split("\\.");
      if (hasType(parts[0]) && getElementByPath(parts, "check extension context", true) != null)
        return;
      if (hasResource(parts[0])  && getElementByPath(parts, "check extension context", true) != null)
        return;
      throw new Error("The element context '"+ec.getExpression()+"' is not valid @ "+context);      
    } else if (ec.getType() == ExtensionContextType.FHIRPATH) {
      FHIRPathEngine fpe = new FHIRPathEngine(worker);
      TypeDetails td = fpe.check(null, null, null, ec.getExpression());
      if (td.hasNoTypes())
        throw new Error("The resource context '"+ec.getExpression()+"' is not valid @ "+context);
      else
        ec.setUserData("type-details", td);
    } else if (ec.getType() == ExtensionContextType.EXTENSION) {
      if (!Utilities.isAbsoluteUrl(ec.getExpression()))
        throw new Error("The extension context '"+ec.getExpression()+"' is not valid @ "+context);
    } else
      throw new Error("not checked yet @ "+context);    
  }

  public ElementDefn getElementByPath(String[] parts, String purpose, boolean followType) throws Exception {
    ElementDefn e;
    try {
      e = getElementDefn(parts[0]);
    } catch (Exception e1) {
     return null;
    }
    int i = 1;
    while (e != null && i < parts.length) {
      if (hasType(e.typeCode()) && !"BackboneElement".equals(e.typeCode()))
        e = getElementDefn(e.typeCode());
      e = e.getElementByName(parts[i], true, this, purpose, followType);
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

  public boolean isResource(String name) {
    return hasResource(name);
  }

  public boolean hasLogicalModel(String name) {
    for (ImplementationGuideDefn ig : getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        if (lm.getResource() != null && (lm.getResource().getName().equals(name) || lm.getResource().getRoot().getName().equals(name)))
          return true;
        if (lm.getId().equals(name))
          return true;
      }
    }
    return false;
  }

  public ImplementationGuideDefn getIGforLogicalModel(String name) {
    for (ImplementationGuideDefn ig : getSortedIgs()) {
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
    for (ImplementationGuideDefn ig : getSortedIgs()) {
      for (LogicalModel lm : ig.getLogicalModels()) {
        if (lm.getResource() != null && (lm.getResource().getName().equals(name) || lm.getResource().getRoot().getName().equals(name) ))
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
    return namingSystems;
  }

  public Set<String> getStructuralPages() {
    return structuralPages;
  }

  public PageInformation page(String file) {
    if (!pageInfo.containsKey(file)) 
      pageInfo.put(file, new PageInformation(file));
    
    return pageInfo.get(file);
  }

  public Map<String, PageInformation> getPageInfo() {
    return pageInfo;
  }

  public Set<String> getStyleExemptions() {
    return styleExemptions;
  }

  public List<W5Entry> getW5list() {
    return w5list;
  }

  public Map<String, ConstraintStructure> getProfileIds() {
    return profileIds;
  }

  public boolean isLoaded() {
    return loaded;
  }

  public void setLoaded(boolean loaded) {
    this.loaded = loaded;
  }

  public void addNs(String url, String name, String page) throws Exception {
    addNs(url, name, page, false);
  }
  
  public void addNs(String url, String name, String page, boolean notUnique) throws Exception {
//    if (page == null || page.startsWith("null"))
//      throw new Exception("error in path (null) for "+url);
      
    if (!url.startsWith("http://hl7.org/fhir")) 
      throw new Exception("namespace wrong: "+url);  
    else if (redirectList == null) 
      throw new Exception("namespace missed the boat");  
    else if (!redirectList.containsKey(url)) 
      redirectList.put(url, new NamespacePair(name, page, notUnique));
    else if (!notUnique) {
      if (redirectList.get(url).notUnique)
        redirectList.put(url, new NamespacePair(name, page, notUnique));
//      else if (!redirectList.get(url).page.equals(page))
//        throw new Exception("namespace conflict: "+url+", page "+page+" vs "+redirectList.get(url).page);
    }
  }

  public Map<String, NamespacePair> getRedirectList() {
    return redirectList;
  }
  
  public void clearRedirectList() {
    redirectList = null;
  }

  public boolean hasConcreteResource(String name) {
    for (ResourceDefn rd : baseResources.values())
      if (!rd.isAbstract() && rd.getName().equals(name))
        return true;
    return resources.containsKey(name);
  }

  public boolean hasBaseType(String name) {
    if (name == null)
      return false;
    for (DefinedCode dc : primitives.values()) {
      if (/* dc instanceof PrimitiveType && */ dc.getCode().equals(name))
        return true;
    }
    return name.equals("xhtml") || types.containsKey(name) || structures.containsKey(name) || infrastructure.containsKey(name);
  }

  public boolean hasAbstractResource(String name) {
    for (ResourceDefn rd : baseResources.values())
      if (rd.isAbstract() && rd.getName().equals(name))
        return true;
    return false;
  }

  public ConstraintStructure findProfile(String id) {
    return profileIds.get(id);
  }

  public Map<String, CommonSearchParameter> getCommonSearchParameters() {
    return commonSearchParameters;
  }

  public Set<String> getAllTypeNames() {
    Set<String> res = new HashSet<String>();
    res.add("Element");
    res.addAll(types.keySet());
    res.addAll(structures.keySet());
    res.addAll(infrastructure.keySet());
    return res;
  }

  public void clean() {

    commonBindings = null;
    boundValueSets = null;
    unresolvedBindings = null;
    allBindings = null;
    constraints = null;
    resourceTemplates = null;

    packMap = null;
    dictionaries = null;
    knownResources = null;
    statusCodes = null;
    valueSetCount = valuesets.size();
    valuesets = null;
    conceptMaps = null;
    codeSystems = null;
    extraValuesets = null;
    styleExemptions = null;

    aggregationEndpoints = null;
    events = null;
    diagrams = null;
    compartments = null;
    pastVersions = null;
    TLAs = null;
    w5s = null;
    w5list = null;
    typePages = null;
    pageTitles = null;
    searchRules = null;
    commonSearchParameters = null;
    redirectList = null;
    vsFixups = null;
    namingSystems = null;
    structuralPages = null;
    profileIds = null;
       
  }

  public int getValueSetCount() {
    return valueSetCount;
  }

  public List<String> listAllPatterns(String name) {
    List<String> names = new ArrayList<>();
    Queue<String> plist = new LinkedList<>();
    plist.add(name);
    while (!plist.isEmpty()) {
      name = plist.remove(); 
      names.add(name);
      for (ImplementationGuideDefn ig : getSortedIgs()) {
        for (LogicalModel lm : ig.getLogicalModels()) {
          if (lm.getResource().getRoot().typeCode().equals(name)) {
            plist.add(lm.getResource().getRoot().getName());
          }
        }
      }
    }
    return names;
  }

}
