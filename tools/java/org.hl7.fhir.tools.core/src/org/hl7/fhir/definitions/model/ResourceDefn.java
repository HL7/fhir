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

import org.hl7.fhir.definitions.model.ResourceDefn.RimClass;
import org.hl7.fhir.exceptions.FHIRException;
import org.hl7.fhir.r5.model.StructureDefinition;
import org.hl7.fhir.utilities.StandardsStatus;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xml.XMLUtil;
import org.w3c.dom.Element;

public class ResourceDefn  {

  public enum RimClass {
    UNKNOWN, ANY, ENTITY, ROLE, ACT, SPECIAL;
    
    public String toCode() {
      switch (this) {
      case ACT: return "Act";
      case ANY: return "*";
      case ENTITY: return "Entity";
      case ROLE: return "Role";
      case SPECIAL: return "(special)";
      case UNKNOWN: return "??";
      }
      return null;
    }
  }

  public enum SecurityCategorization {
    ANONYMOUS, BUSINESS, INDIVIDUAL, PATIENT, NOT_CLASSIFIED;
    
    public String toCode() {
      switch (this) {
      case ANONYMOUS: return "anonymous";
      case BUSINESS: return "business";
      case INDIVIDUAL: return "individual";
      case NOT_CLASSIFIED: return "not-classified";
      case PATIENT: return "patient";
      }
      return null;
    }

    public String toDisplay() {
      switch (this) {
      case ANONYMOUS: return "Anonymous";
      case BUSINESS: return "Business";
      case INDIVIDUAL: return "Individual";
      case NOT_CLASSIFIED: return "Not Classified";
      case PATIENT: return "Patient";
      }
      return null;
    }

    public static SecurityCategorization fromCode(String sc) throws FHIRException {
      if ("anonymous".equals(sc)) return ANONYMOUS;
      if ("business".equals(sc)) return BUSINESS;
      if ("individual".equals(sc)) return INDIVIDUAL;
      if ("not-classified".equals(sc)) return NOT_CLASSIFIED;
      if ("patient".equals(sc)) return PATIENT;
      if ("n/a".equals(sc)) return null;
      throw new FHIRException("unknown SecurityCategorization code "+sc);
    }

    public String toIndex() {
      switch (this) {
      case ANONYMOUS: return "0";
      case BUSINESS: return "1";
      case INDIVIDUAL: return "2";
      case PATIENT: return "3";
      case NOT_CLASSIFIED: return "4";
      }
      return null;
    }
  }
  
  public enum FMGApproval { 
    APPROVED, PENDING, NOPROPOSAL, NOTRELEVANT;
    
    public static FMGApproval fromCode(String s) {
      if (Utilities.noString(s))
        return NOPROPOSAL;
      if (s.equals("approved"))
        return FMGApproval.APPROVED;
      if (s.equals("pending"))
        return FMGApproval.PENDING;
      if (s.equals("n/a"))
        return FMGApproval.NOTRELEVANT;
      return NOPROPOSAL;
    }

  }

  public static class PointSpec {
    private double x;
    private double y;
     public PointSpec(double x, double y) {
      super();
      this.x = x;
      this.y = y;
    }
    public double getX() {
      return x;
    }
    public double getY() {
      return y;
    }
    
  }

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

  private StandardsStatus status = StandardsStatus.TRIAL_USE;
  private boolean abstract_;
  private boolean interface_;
  private WorkGroup wg;
  private Profile conformancePack;

  private String name = null;
  private String enteredInErrorStatus;
  private String fmmLevel;
  private String proposedOrder;
  private String display;
  private ElementDefn template;
  private List<String> hints = new ArrayList<String>();
  private Map<String, PointSpec> layout = new HashMap<String, PointSpec>();
  private SecurityCategorization securityCategorization;
  
  private List<InheritedMapping> inheritedMappings = new ArrayList<InheritedMapping>();
  public FMGApproval approval;

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
  private String normativePackage;
  private String normativeVersion;

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

  public StandardsStatus getStatus() {
    return status;
  }

  public void setStatus(StandardsStatus status) {
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

  public Profile getConformancePackage(String id) {
    for (Profile p : conformancePackages)
      if (p.getId().equals(id))
        return p;
    return null;
  }

  public String getProposedOrder() {
    return proposedOrder;
  }

  public void setProposedOrder(String proposedOrder) {
    this.proposedOrder = proposedOrder;
  }

  public String getDisplay() {
    return display;
  }

  public void setDisplay(String display) {
    this.display = display;
  }

  public Example getExampleById(String id) {
    for (Example e : examples) {
      if (e.getId().equals(id))
        return e;
      if ("Bundle".equals(e.getResourceName())) {
        List<Element> children = new ArrayList<Element>();
        XMLUtil.getNamedChildren(e.getXml().getDocumentElement(), "entry", children);
        for (Element c : children) {
          Element res = XMLUtil.getFirstChild(XMLUtil.getNamedChild(c, "resource"));
          if (id.equals(XMLUtil.getNamedChildValue(res, "id")))
            return e;
        }
      }
    }
    return null;
  }

  public ElementDefn getTemplate() {
    return template;
  }

  public void setTemplate(ElementDefn template) {
    this.template = template;
  }

  
  public String getNormativePackage() {
    return normativePackage;
  }

  public void setNormativePackage(String value) {
     this.normativePackage = value; 
  }

  public void addHints(List<String> hints) {
    this.hints.addAll(hints);
    
  }

  public List<String> getHints() {
    return hints;
  }

  public Map<String, PointSpec> getLayout() {
    return layout;
  }

  public Operation getOperationByName(String name) {
    for (Operation t : getOperations()) {
      if (t.getName().equals(name)) {
        return t;
      }
    }
    return null;
  }

  public FMGApproval getApproval() {
    return approval;
  }

  public void setApproval(FMGApproval approval) {
    this.approval = approval;
  }

  public SecurityCategorization getSecurityCategorization() {
    return securityCategorization;
  }

  public void setSecurityCategorization(SecurityCategorization securityCategorization) {
    this.securityCategorization = securityCategorization;
  }

  public String getNormativeVersion() {
    return normativeVersion;
  }

  public void setNormativeVersion(String normativeVersion) {
    this.normativeVersion = normativeVersion;
  }

  public RimClass getRimClass() {
    String mapping = root.getMapping("http://hl7.org/v3");
    if (Utilities.noString(mapping))
      return RimClass.UNKNOWN;
    if (mapping.contains("["))
      mapping = mapping.substring(0, mapping.indexOf("["));
    if (Utilities.existsInList(mapping, "Act", "ControlAct", "FinancialContract", "FinanicalTransaction", "Account", "InvoiceElement", "Exposure", "DeviceTask", "ContextStructure", "Document", 
        "Supply", "Diet", "DiagnosticImage", "Observation", "PublicHealthCase", "WorkingList", "PatientEncounter", "Procedure", "SubstanceAdministration",
        // illegal, but in use
        "Appointment", "ActSIte", "FinancialConsent", "Coverage", "Encounter", ".outboundRelationship", 
        "act"))
      return RimClass.ACT;
    if (Utilities.existsInList(mapping, "Entity", "Place", "Organization", "Person", "LivingSubject", "NonPersonLivingSubject", "Material", "ManufacturedMaterial", "Container", "Device", 
        "ManufacturedProduct"))
      return RimClass.ENTITY;
    if (Utilities.existsInList(mapping, "Role", "Access", "Patient", "LicensedEntity", "QualifiedEntity", "Employee", "RoleLink",
        ".Role"))
      return RimClass.ROLE;
    if (Utilities.existsInList(mapping, "Act, Entity or Role"))
      return RimClass.ANY;
    if (Utilities.existsInList(mapping, "ED", "N/A", "n/a", "N/A - RIM doesn't know how to do this"))
      return RimClass.SPECIAL;
    if (mapping.toLowerCase().startsWith("n/a"))
      return RimClass.SPECIAL;
    return RimClass.UNKNOWN;
//    throw new Error("Didn't understand RIM Mapping: "+mapping+" for "+getName());
  }
  
  public String getMappingUrl() {
    String url = null;
    if (getName().equals("fivews"))
      url = "http://hl7.org/fhir/fivews";
    else if (Utilities.existsInList(getName(), "event", "request", "definition"))
      url = "http://hl7.org/fhir/workflow";
    else 
      url = "http://hl7.org/fhir/interface";
    return url;
  }

  public boolean isInterface() {
    return interface_;
  }

  public void setInterface(boolean interface_) {
    this.interface_ = interface_;
  }
  
  
}
