package org.hl7.fhir.definitions.model;
import org.hl7.fhir.dstu3.model.ImplementationGuide.ImplementationGuidePackageResourceComponent;
import org.hl7.fhir.dstu3.model.StructureDefinition;

// publishing details about a profile + the profile
public class ConstraintStructure {

  private String title; // human readable name
  private String id; // id in the resource, which is also the file name root
  private StructureDefinition resource;
  private ResourceDefn defn; // temporary, until we get around to building the resource 
  private ImplementationGuideDefn usage;
  private String owner; // id of the AP that owns this
  private ImplementationGuidePackageResourceComponent resourceInfo;
  private WorkGroup wg;
  private String fmm;
    
  public ConstraintStructure(StructureDefinition resource, ImplementationGuideDefn usage, WorkGroup wg, String fmm) {
    this.id = resource.getId();
    this.title = resource.getName();
    this.resource = resource;
    if (usage == null)
      throw new Error("No usage on profile on "+resource.getName());
    this.usage = usage;
    this.wg = wg;
    this.fmm = fmm;
  }

  public ConstraintStructure(String id, String title, ResourceDefn defn, ImplementationGuideDefn usage, WorkGroup wg, String fmm) {
    this.id = id;
    this.title = title;
    this.defn = defn;
    this.usage = usage;
    if (usage == null)
      throw new Error("No usage on profile "+id+" ("+title+"):");
    this.wg = wg;
    this.fmm = fmm;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public StructureDefinition getResource() {
    return resource;
  }

  public void setResource(StructureDefinition resource) {
    this.resource = resource;
  }

  public ResourceDefn getDefn() {
    return defn;
  }

  public void setDefn(ResourceDefn defn) {
    this.defn = defn;
  }

  public ImplementationGuideDefn getUsage() {
    return usage;
  }

  public void setUsage(ImplementationGuideDefn usage) {
    this.usage = usage;
  }

  public String getOwner() {
    return owner;
  }

  public void setOwner(String owner) {
    this.owner = owner;
  }

  public void setResourceInfo(ImplementationGuidePackageResourceComponent resourceInfo) {
    this.resourceInfo = resourceInfo;
    
  }

  public ImplementationGuidePackageResourceComponent getResourceInfo() {
    return resourceInfo;
  }

  public WorkGroup getWg() {
    return wg;
  }

  public void setWg(WorkGroup wg) {
    this.wg = wg;
  }

  public String getFmm() {
    return fmm;
  }

  public void setFmm(String fmm) {
    this.fmm = fmm;
  }

  
  
}
