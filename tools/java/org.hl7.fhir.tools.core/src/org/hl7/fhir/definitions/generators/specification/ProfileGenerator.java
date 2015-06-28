package org.hl7.fhir.definitions.generators.specification;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingMethod;
import org.hl7.fhir.definitions.model.ConstraintStructure;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ImplementationGuide;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.Operation;
import org.hl7.fhir.definitions.model.OperationParameter;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.Profile;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameterDefn;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.FormatUtilities;
import org.hl7.fhir.instance.model.ContactPoint;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.ElementDefinition;
import org.hl7.fhir.instance.model.ElementDefinition.AggregationMode;
import org.hl7.fhir.instance.model.ElementDefinition.ConstraintSeverity;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.ElementDefinitionSlicingComponent;
import org.hl7.fhir.instance.model.ElementDefinition.PropertyRepresentation;
import org.hl7.fhir.instance.model.ElementDefinition.SlicingRules;
import org.hl7.fhir.instance.model.ElementDefinition.TypeRefComponent;
import org.hl7.fhir.instance.model.Enumerations.ConformanceResourceStatus;
import org.hl7.fhir.instance.model.Enumerations.SearchParamType;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.OperationDefinition.OperationDefinitionParameterComponent;
import org.hl7.fhir.instance.model.OperationDefinition.OperationDefinitionParameterBindingComponent;
import org.hl7.fhir.instance.model.OperationDefinition.OperationKind;
import org.hl7.fhir.instance.model.OperationDefinition.OperationParameterUse;
import org.hl7.fhir.instance.model.OperationDefinition;
import org.hl7.fhir.instance.model.Reference;
import org.hl7.fhir.instance.model.OperationOutcome.IssueSeverity;
import org.hl7.fhir.instance.model.SearchParameter;
import org.hl7.fhir.instance.model.SearchParameter.SearchParameterContactComponent;
import org.hl7.fhir.instance.model.StructureDefinition;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionContactComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionDifferentialComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionMappingComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionSnapshotComponent;
import org.hl7.fhir.instance.model.StructureDefinition.StructureDefinitionType;
import org.hl7.fhir.instance.model.valuesets.IssueType;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.model.UriType;
import org.hl7.fhir.instance.utils.NarrativeGenerator;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ProfileUtilities.ProfileKnowledgeProvider;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.utils.WorkerContext;
import org.hl7.fhir.instance.validation.ValidationMessage;
import org.hl7.fhir.instance.validation.ValidationMessage.Source;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;

public class ProfileGenerator {

  public enum SnapShotMode {
    None, 
    Resource,
    DataType
  } 

  private WorkerContext context;
  private Definitions definitions;

  // status
  // note that once we start slicing, the slices keep their own maps, but all share the master pathname list
  private final Map<String, ElementDefinition> paths = new HashMap<String, ElementDefinition>();
  private final List<String> pathNames = new ArrayList<String>();
  private ProfileKnowledgeProvider pkp;
  private Calendar genDate;

  private static class SliceHandle {
    private String name;
    private Map<String, ElementDefinition> paths = new HashMap<String, ElementDefinition>();
  }

  public ProfileGenerator(Definitions definitions, WorkerContext context, ProfileKnowledgeProvider pkp, Calendar genDate) {
    super();
    this.definitions = definitions;
    this.context = context;
    this.pkp = pkp;
    this.genDate = genDate;
  }

  public StructureDefinition generate(PrimitiveType type) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(type.getCode());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ type.getCode());
    p.setBase("http://hl7.org/fhir/StructureDefinition/Element"); // master profile
    p.setType(StructureDefinitionType.TYPE);
    p.setAbstract(false);

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(ConformanceResourceStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    ElementDefinition ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setShort("Primitive Type " +type.getCode());
    ec.setDefinition(type.getDefinition());
    ec.setComments(type.getComment());
    ec.setMin(0);
    ec.setMax("*");
    ec.getType().add(new TypeRefComponent().setCode("Element"));
    
    ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setPath(type.getCode()+".value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setShort("Primitive value for " +type.getCode());
    ec.setDefinition("Primitive value for " +type.getCode());
    ec.setMin(0);
    ec.setMax("1");
    ec.getType().add(new TypeRefComponent().setCode("xs:"+type.getSchemaType()));
    
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ec = new ElementDefinition();
    p.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setShort("Primitive Type " +type.getCode());
    ec.setDefinition(type.getDefinition());
    ec.setComments(type.getComment());
    ec.getType().add(new TypeRefComponent().setCode(type.getCode()));
    ec.setMin(0);
    ec.setMax("*");

    ec = new ElementDefinition();
    p.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode()+".id");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition("unique id for the element within a resource (for internal references)");
    ec.setMin(0);
    ec.setMax("1");
    ec.setShort("xml:id (or equivalent in JSON)");
    ec.getType().add(new TypeRefComponent().setCode("id"));
    
    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());
        
    ec = new ElementDefinition();
    p.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode()+".value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition("The actual value");
    ec.setMin(0);
    ec.setMax("1");
    ec.setShort("Primitive value for " +type.getCode());
    ec.getType().add(new TypeRefComponent().setCode("xs:"+type.getSchemaType()));
    
    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  public StructureDefinition generate(DefinedStringPattern type) throws Exception {

    StructureDefinition p = new StructureDefinition();
    p.setId(type.getCode());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ type.getCode());
    p.setBase("http://hl7.org/fhir/StructureDefinition/Element"); // master profile
    p.setType(StructureDefinitionType.TYPE);
    p.setAbstract(false);

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(ConformanceResourceStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    ElementDefinition ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setPath(type.getCode());
    
    ec.setShort("Primitive Type " +type.getCode());
    ec.setDefinition(type.getDefinition());
    ec.setComments(type.getComment());
    ec.setMin(0);
    ec.setMax("*");
    ec.getType().add(new TypeRefComponent().setCode("Element"));
    
    ec = new ElementDefinition();
    p.getDifferential().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    
    ec.setShort("Primitive value for " +type.getCode());
    ec.setDefinition("Primitive value for " +type.getCode());
    ec.setMin(0);
    ec.setMax("1");
    ec.getType().add(new TypeRefComponent().setCode("xs:"+type.getBase()));
    
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    ec = new ElementDefinition();
    p.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode());
    
    ec.setShort("Primitive Type " +type.getCode());
    ec.setDefinition(type.getDefinition());
    ec.setComments(type.getComment());
    ec.getType().add(new TypeRefComponent().setCode(type.getCode()));
    ec.setMin(0);
    ec.setMax("*");

    makeExtensionSlice("extension", p, p.getSnapshot(), null, type.getCode());
    
    
    ec = new ElementDefinition();
    p.getSnapshot().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    
    ec.setDefinition("Primitive value for " +type.getCode());
    ec.setShort("Primitive value for " +type.getCode());
    ec.setMin(0);
    ec.setMax("1");
    ec.getType().add(new TypeRefComponent().setCode("xs:"+type.getBase()));

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  public StructureDefinition generate(TypeDefn t) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(t.getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ t.getName());
    p.setBase("http://hl7.org/fhir/StructureDefinition/Element"); // master profile
    p.setType(StructureDefinitionType.TYPE);
    p.setAbstract(false);

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(t.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+t.getName()+" Resource");
    p.setRequirements(t.getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(ConformanceResourceStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true);
    
    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.DataType, true);

    containedSlices.clear();
    
    p.getDifferential().getElement().get(0).getType().clear();
    p.getDifferential().getElement().get(0).addType().setCode("Element");
    p.getSnapshot().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).addType().setCode("Element");

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }
  
  public StructureDefinition generate(ProfiledType pt) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(pt.getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ pt.getName());
    p.setBase("http://hl7.org/fhir/StructureDefinition/"+pt.getBaseType());
    p.setType(StructureDefinitionType.CONSTRAINT);
    p.setAbstract(false);

    ToolResourceUtilities.updateUsage(p, "core");
    p.setName(pt.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base StructureDefinition for "+pt.getName()+" Resource");
    p.setDescription(pt.getDefinition());
    p.setDate(genDate.getTime());
    p.setStatus(ConformanceResourceStatus.fromCode("draft")); // DSTU

    // first, the differential
    p.setName(pt.getName());
    ElementDefinition e = new ElementDefinition();
    e.setPath(pt.getBaseType());
    e.setName(pt.getName());
    e.setShort(pt.getDefinition());
    e.setDefinition(pt.getDescription());
    e.setMin(1);
    e.setMax("1");
    e.setIsModifier(false);
    
    String s = definitions.getTLAs().get(pt.getName().toLowerCase());
    if (s == null)
      throw new Exception("There is no TLA for '"+pt.getName()+"' in fhir.ini");
    ElementDefinitionConstraintComponent inv = new ElementDefinitionConstraintComponent();
    inv.setKey(s+"-1");
    inv.setRequirements(pt.getInvariant().getRequirements());
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman(pt.getInvariant().getEnglish());
    inv.setXpath(pt.getInvariant().getXpath());
    e.getConstraint().add(inv);
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    p.getDifferential().getElement().add(e);

    reset();
    
    // now, the snapshot
    StructureDefinition base = getTypeSnapshot(pt.getBaseType());
    new ProfileUtilities(context).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+pt.getBaseType(), p.getName(), pkp);

    p.getDifferential().getElement().get(0).getType().clear();
    p.getDifferential().getElement().get(0).addType().setCode(pt.getBaseType());
    p.getSnapshot().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).addType().setCode(pt.getBaseType());
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addTag("h2").addText("Data type "+pt.getName());
    div.addTag("p").addText(pt.getDefinition());
    div.addTag("h3").addText("Rule");
    div.addTag("p").addText(pt.getInvariant().getEnglish());
    div.addTag("p").addText("XPath:");
    div.addTag("blockquote").addTag("pre").addText(pt.getInvariant().getXpath());
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }
  
  private StructureDefinition getTypeSnapshot(String baseType) throws Exception {
    StructureDefinition p = definitions.getElementDefn(baseType).getProfile();
    if (p != null &&  p.hasSnapshot())
      return p;
    throw new Exception("Unable to find snapshot for "+baseType);
  }

  public StructureDefinition generate(Profile pack, ResourceDefn r, String usage) throws Exception {
    StructureDefinition p = new StructureDefinition();
    p.setId(r.getRoot().getName());
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ r.getRoot().getName());
    p.setType(StructureDefinitionType.RESOURCE);
    if (r.getName().equals("Resource")) {
      p.setBase("http://hl7.org/fhir/StructureDefinition/"+r.getRoot().typeCode());
      p.setAbstract(false);
    } else {
      p.setAbstract(true);      
    }

    if (r.getFmmLevel() != null)
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL, Integer.parseInt(r.getFmmLevel()));
    if (r.getFmmLevelNoWarnings() != null)
      ToolingExtensions.addIntegerExtension(p, ToolingExtensions.EXT_FMM_LEVEL_NO_WARN, Integer.parseInt(r.getFmmLevelNoWarnings()));
    ToolResourceUtilities.updateUsage(p, usage);
    p.setName(r.getRoot().getName());
    p.setPublisher("Health Level Seven International"+(r.getWg() == null ? "" : " ("+r.getWg().getName()+")"));
    p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    if (r.getWg() != null)
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, r.getWg().getUrl()));
    p.setDescription("Base StructureDefinition for "+r.getRoot().getName()+" Resource");
    p.setRequirements(r.getRequirements());
    p.setDate(genDate.getTime());
    p.setStatus(ConformanceResourceStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(null, p, p.getDifferential().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true);

    reset();
    // now. the snapshot
    p.setSnapshot(new StructureDefinitionSnapshotComponent());
    defineElement(null, p, p.getSnapshot().getElement(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource, true);

    List<String> names = new ArrayList<String>();
    names.addAll(r.getSearchParams().keySet());
    Collections.sort(names);
    for (String pn : names) {
      pack.getSearchParameters().add(makeSearchParam(p, r.getName()+"-"+pn.replace("_", ""), r.getName(), r.getSearchParams().get(pn)));
    }
    containedSlices.clear();

    if (r.getName().equals("Resource")) {
      p.getDifferential().getElement().get(0).getType().clear();
      p.getSnapshot().getElement().get(0).getType().clear();
    } else {
      p.getDifferential().getElement().get(0).getType().clear();
      p.getDifferential().getElement().get(0).addType().setCode(r.getRoot().typeCode());
      p.getSnapshot().getElement().get(0).getType().clear();
      p.getSnapshot().getElement().get(0).addType().setCode(r.getRoot().typeCode());
    }
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }
  
  private void reset() {
    paths.clear();
    pathNames.clear();  
  }

  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, ImplementationGuide usage, List<ValidationMessage> issues) throws Exception {
    
    try {
      return generate(pack, profile, resource, id, null, usage, issues);
    } catch (Exception e) {
      throw new Exception("Error processing profile '"+id+"': "+e.getMessage(), e);
    }
  }
  
  public StructureDefinition generate(Profile pack, ConstraintStructure profile, ResourceDefn resource, String id, String html, ImplementationGuide usage, List<ValidationMessage> issues) throws Exception {
    if (profile.getResource() != null)
      return profile.getResource();
    
    StructureDefinition p = new StructureDefinition();
    p.setId(FormatUtilities.makeId(id));
    p.setUrl("http://hl7.org/fhir/StructureDefinition/"+ id);
    if (!resource.getRoot().getTypes().isEmpty() && (resource.getRoot().getTypes().get(0).getProfile() != null))
      p.setBase(resource.getRoot().getTypes().get(0).getProfile());
    else
      p.setBase("http://hl7.org/fhir/StructureDefinition/"+resource.getName());    
    p.setType(StructureDefinitionType.CONSTRAINT);
    p.setAbstract(false);

    
    ToolResourceUtilities.updateUsage(p, usage.getCode());
    p.setName(pack.metadata("name"));
    p.setPublisher(pack.metadata("author.name"));
    if (pack.hasMetadata("author.reference"))
      p.addContact().getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, pack.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    p.setDescription(resource.getRoot().getShortDefn());    
    if (!p.hasDescriptionElement() && pack.hasMetadata("description"))
      p.setDescription(pack.metadata("description"));
    if (pack.hasMetadata("requirements"))
      p.setRequirements(pack.metadata("requirements"));

    if (pack.hasMetadata("date"))
      p.setDateElement(Factory.newDateTime(pack.metadata("date").substring(0, 10)));
    else
      p.setDate(genDate.getTime());

    if (pack.hasMetadata("status")) 
      p.setStatus(ConformanceResourceStatus.fromCode(pack.metadata("status")));
    if (pack.getMetadata().containsKey("code"))
      for (String s : pack.getMetadata().get("code")) 
        if (!Utilities.noString(s))
          p.getCode().add(Factory.makeCoding(s));

    if (pack.hasMetadata("datadictionary"))
      ToolingExtensions.setStringExtension(p, "http://hl7.org/fhir/StructureDefinition/datadictionary", pack.metadata("datadictionary"));
    
    Set<String> containedSlices = new HashSet<String>();

    p.setDifferential(new StructureDefinitionDifferentialComponent());
    defineElement(pack, p, p.getDifferential().getElement(), resource.getRoot(), resource.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None, true);
    List<String> names = new ArrayList<String>();
    names.addAll(resource.getSearchParams().keySet());
    Collections.sort(names);
    for (String pn : names) {
      pack.getSearchParameters().add(makeSearchParam(p, pack.getId()+"-"+resource.getName()+"-"+pn, resource.getName(), resource.getSearchParams().get(pn)));
    }
    StructureDefinition base = definitions.getSnapShotForBase(p.getBase());
    
    List<String> errors = new ArrayList<String>();
    new ProfileUtilities(context).sortDifferential(base, p, p.getName(), pkp, errors);
    for (String s : errors)
      issues.add(new ValidationMessage(Source.ProfileValidator, IssueType.STRUCTURE, -1, -1, p.getBase(), s, IssueSeverity.WARNING));
    reset();
    // ok, c is the differential. now we make the snapshot
    new ProfileUtilities(context).generateSnapshot(base, p, "http://hl7.org/fhir/StructureDefinition/"+p.getType(), p.getName(), pkp);
    reset();

    p.getDifferential().getElement().get(0).getType().clear();
    p.getDifferential().getElement().get(0).addType().setCode(p.getSnapshot().getElement().get(0).getPath());
    p.getSnapshot().getElement().get(0).getType().clear();
    p.getSnapshot().getElement().get(0).addType().setCode(p.getSnapshot().getElement().get(0).getPath());

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    checkHasTypes(p);
    return p;
  }

  private SearchParamType getSearchParamType(SearchParameterDefn.SearchType type) {
    switch (type) {
    case number:
      return SearchParamType.NUMBER;
    case string:
      return SearchParamType.STRING;
    case date:
      return SearchParamType.DATE;
    case reference:
      return SearchParamType.REFERENCE;
    case token:
      return SearchParamType.TOKEN;
    case uri:
      return SearchParamType.URI;
    case composite:
      return SearchParamType.COMPOSITE;
    case quantity:
      return SearchParamType.QUANTITY;
    }
    return null;
  }

  public SearchParameter makeSearchParam(StructureDefinition p, String id, String rn, SearchParameterDefn spd) throws Exception  {
    SearchParameter sp = new SearchParameter();
    sp.setId(id.replace("[", "").replace("]", ""));
    sp.setUrl("http://hl7.org/fhir/SearchParameter/"+sp.getId());
    sp.setName(spd.getCode());
    sp.setDate(genDate.getTime());
    sp.setPublisher(p.getPublisher());
    for (StructureDefinitionContactComponent tc : p.getContact()) {
      SearchParameterContactComponent t = sp.addContact();
      t.setNameElement(tc.getNameElement().copy());
      for (ContactPoint ts : tc.getTelecom())
        t.getTelecom().add(ts.copy());
    }
    sp.setBase(p.getName());
    sp.setType(getSearchParamType(spd.getType()));
    sp.setDescription(spd.getDescription());
    String xpath = spd.getXPath();
    if (xpath != null) {
      if (xpath.contains("[x]"))
        xpath = convertToXpath(xpath);
      sp.setXpath(xpath);
    }
    
    for(String target : spd.getTargets()) {
    	if("Any".equals(target) == true) {
    	    if(spd.getTargets().size()>1)
    	      throw new Exception("Can not declare multiple target codes for SearchParameter when one of them is 'Any'");
    	  
    	    for(String resourceName : definitions.sortedResourceNames())
    	      sp.addTarget(resourceName);
    	}
    	else
    	  sp.addTarget(target);
    }
    
    return sp;
  }


  private String convertToXpath(String xpath) {
    String[] parts = xpath.split("\\/");
    StringBuilder b = new StringBuilder();
    boolean first = true;
    for (String p : parts) {
      if (first)
        first = false;
      else
        b.append("/");
      if (p.startsWith("f:")) {
        String v = p.substring(2);
        if (v.endsWith("[x]"))
          b.append("*[starts-with(local-name(.), '"+v.replace("[x]", "")+"')]");
        else
          b.append(p);
      }        
      else
        b.append(p);
    }
    return b.toString();
  }

  private ElementDefinitionBindingComponent generateBinding(BindingSpecification src) throws Exception {
    if (src == null)
      return null;

    ElementDefinitionBindingComponent dst = new ElementDefinitionBindingComponent();
    dst.setStrength(src.getStrength());    
    dst.setDescription(src.getDefinition());
    if (src.getBinding() != BindingMethod.Unbound)
      dst.setValueSet(buildReference(src));    
    return dst;
  }

  private Type buildReference(BindingSpecification src) throws Exception {
    switch (src.getBinding()) {
    case Unbound: return null;
    case CodeList:
      if (src.getReference().startsWith("#"))
        return Factory.makeReference("http://hl7.org/fhir/vs/"+src.getReference().substring(1));
      else
        throw new Exception("not done yet");
    case ValueSet: 
      if (!Utilities.noString(src.getReference()))
        if (src.getReference().startsWith("http"))
          return Factory.makeReference(src.getReference());
        else if (src.getReference().startsWith("valueset-"))
          return Factory.makeReference("http://hl7.org/fhir/vs/"+src.getReference().substring(9));
        else
          return Factory.makeReference("http://hl7.org/fhir/vs/"+src.getReference());
      else
        return null; // throw new Exception("not done yet");
    case Reference: return Factory.newUri(src.getReference());
    case Special: 
      return Factory.makeReference("http://hl7.org/fhir/vs/"+src.getReference().substring(1));
    default: 
      throw new Exception("not done yet");
    }
  }

  /**
   * note: snapshot implies that we are generating a resource or a data type; for other profiles, the snapshot is generated elsewhere
   */
  private ElementDefinition defineElement(Profile ap, StructureDefinition p, List<ElementDefinition> elements, ElementDefn e, String path, Set<String> slices, List<SliceHandle> parentSlices, SnapShotMode snapshot, boolean root) throws Exception 
  {
    ElementDefinition ce = new ElementDefinition();
    elements.add(ce);
        
    ce.setPath(path);
    
    if (e.isXmlAttribute())
      ce.addRepresentation(PropertyRepresentation.XMLATTR);
    List<SliceHandle> myParents = new ArrayList<ProfileGenerator.SliceHandle>();
    myParents.addAll(parentSlices);
    
    // If this element has a profile name, and this is the first of the
    // slicing group, add a slicing group "entry" (= first slice member,
    // which holds Slicing information)
    if (!Utilities.noString(e.getProfileName())) {
      if (e.getDiscriminator().size() > 0 && !slices.contains(path)) {
        ce.setSlicing(new ElementDefinitionSlicingComponent());
        ce.getSlicing().setDescription(e.getSliceDescription());
        String[] d = e.getDiscriminator().get(0).split("\\|");
        if (d.length >= 1)
          ce.getSlicing().addDiscriminator(d[0].trim());
        if (d.length >= 2)
          ce.getSlicing().setOrdered(Boolean.parseBoolean(d[1].trim()));
        else
          ce.getSlicing().setOrdered(false);
        if (d.length >= 3)
          ce.getSlicing().setRules(SlicingRules.fromCode(d[2].trim()));
        else
          ce.getSlicing().setRules(SlicingRules.OPEN);
        for (int i = 1; i < e.getDiscriminator().size(); i++) { // we've already process the first in the list
          ce.getSlicing().addDiscriminator(e.getDiscriminator().get(i).trim());
        }
        ce = new ElementDefinition();
        elements.add(ce);
        ce.setPath(path);
        slices.add(path);
      }
      SliceHandle hnd = new SliceHandle();
      hnd.name = path; // though this it not used?
      myParents.add(hnd);
      ce.setName(e.getProfileName());
    }
    addToPaths(myParents, path, ce, p.getName());
    
    if (!"".equals(e.getComments()))
      ce.setComments(e.getComments());
    if (!"".equals(e.getShortDefn()))
      ce.setShort(e.getShortDefn());
    if (!"".equals(e.getDefinition())) {
      ce.setDefinition(e.getDefinition());
      if ("".equals(e.getShortDefn()))
        ce.setShort(e.getDefinition());
    }
    if (e.hasMustSupport())
      ce.setMustSupport(e.isMustSupport());

    if (e.getMaxLength() != null) 
      ce.setMax(e.getMaxLength()); 
    
    // no purpose here
    if (e.getMinCardinality() != null)
      ce.setMin(e.getMinCardinality());
    if (e.getMaxCardinality() != null)
      ce.setMax(e.getMaxCardinality() == Integer.MAX_VALUE ? "*" : e.getMaxCardinality().toString());

    if (!root) {
      if (e.typeCode().startsWith("@"))  {
        ce.setNameReference(getNameForPath(myParents, e.typeCode().substring(1)));
      } else {
        List<TypeRef> expandedTypes = new ArrayList<TypeRef>();
        for (TypeRef t : e.getTypes()) {
          // Expand any Resource(A|B|C) references
          if(t.hasParams() && !"Reference".equals(t.getName())) {
            throw new Exception("Only resource types can specify parameters.  Path " + path + " in profile " + p.getName());
          }
          if(t.getParams().size() > 1)
          {
            if (t.getProfile() != null && t.getParams().size() !=1) {
              throw new Exception("Cannot declare profile on a resource reference declaring multiple resource types.  Path " + path + " in profile " + p.getName());
            }
            for(String param : t.getParams()) {
              TypeRef childType = new TypeRef(t.getName());
              childType.getParams().add(param);
              childType.getAggregations().addAll(t.getAggregations());
              expandedTypes.add(childType);
            }
          } else if (!t.getName().startsWith("=")) {
            expandedTypes.add(t);
          }
        }
        if (expandedTypes.isEmpty()) {
          if (snapshot != SnapShotMode.None)
            ce.addType().setCode(snapshot == SnapShotMode.DataType ? "Element" : "BackboneElement");
        } else for (TypeRef t : expandedTypes) {
          TypeRefComponent type = new TypeRefComponent();
          type.setCode(t.getName());
          String profile = t.getProfile();
          if (profile == null && t.hasParams()) {
            profile = t.getParams().get(0);
          }
          if (profile != null) {
            if (profile.startsWith("http:") || profile.startsWith("#")) {
              type.addProfile(profile);
            } else {
              type.addProfile("http://hl7.org/fhir/StructureDefinition/" + (profile.equals("Any") ? "Resource" : profile));
            }
          }

          for (String aggregation : t.getAggregations()) {
            type.addAggregation(AggregationMode.fromCode(aggregation));
          }	      	

          ce.getType().add(type);
        }
      }
    }
    // ce.setConformance(getType(e.getConformance()));
    for (Invariant id : e.getStatedInvariants()) 
      ce.addCondition(id.getId());
    
    ce.setFixed(e.getFixed());
    ce.setPattern(e.getPattern());
    ce.setDefaultValue(e.getDefaultValue());
    ce.setMeaningWhenMissing(e.getMeaningWhenMissing());
    ce.setExample(e.getExample());
    ce.setRequirements(e.getRequirements());
    for (String s : e.getAliases())
      ce.addAlias(s);
    
    // we don't know mustSupport here
    if (e.hasModifier())
      ce.setIsModifier(e.isModifier());
    if (e.hasSummaryItem())
      ce.setIsSummaryElement(Factory.newBoolean(e.isSummary()));
    
    for (String n : definitions.getMapTypes().keySet()) {
      addMapping(p, ce, n, e.getMapping(n), null);
    }
    if (ap != null) {
      for (String n : ap.getMappingSpaces().keySet()) {
        addMapping(p, ce, n, e.getMapping(n), ap);
      }
    }
    ToolingExtensions.addDisplayHint(ce, e.getDisplayHint());

    for (String in : e.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = new ElementDefinitionConstraintComponent();
      Invariant inv = e.getInvariants().get(in);
      con.setKey(inv.getId());
      con.setRequirements(inv.getRequirements());
      if (Utilities.noString(inv.getSeverity()))
        con.setSeverity(ConstraintSeverity.ERROR);
      else
        con.setSeverity(ConstraintSeverity.fromCode(inv.getSeverity()));
      con.setHuman(inv.getEnglish());
      con.setXpath(inv.getXpath());
      ce.getConstraint().add(con);
    }
    // we don't have anything to say about constraints on resources

    if (e.hasBinding()) {
      ce.setBinding(generateBinding(e.getBinding()));
    }

    if (snapshot != SnapShotMode.None && !e.getElements().isEmpty()) {    
//      makeExtensionSlice("extension", p, c, e, path);
//      if (snapshot == SnapShotMode.Resource) { 
//        makeExtensionSlice("modifierExtension", p, c, e, path);

//        if (!path.contains(".")) {
//          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("Resource").getRoot().getElementByName("language")));
//          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName("text")));
//          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName("contained")));
//        }
//      }
    }
    Set<String> containedSlices = new HashSet<String>();
    if (snapshot != SnapShotMode.None) {
      if (!root && Utilities.noString(e.typeCode())) {
        if (snapshot == SnapShotMode.Resource)
          defineAncestorElements("BackboneElement", path, snapshot, containedSlices, p, elements);
        else
          defineAncestorElements("Element", path, snapshot, containedSlices, p, elements);
      } else if (root && !Utilities.noString(e.typeCode())) 
        defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements);
    }
    for (ElementDefn child : e.getElements()) 
      defineElement(ap, p, elements, child, path+"."+child.getName(), containedSlices, myParents, snapshot, false);
    
    return ce;
  }
 
  private String actualTypeName(String type) {
    if (type.equals("Type"))
      return "Element";
    if (type.equals("Structure"))
      return "Element";
    return type;
  }
  private void defineAncestorElements(String type, String path, SnapShotMode snapshot, Set<String> containedSlices, StructureDefinition p, List<ElementDefinition> elements) throws Exception {
    ElementDefn e = definitions.getElementDefn(actualTypeName(type));
    if (!Utilities.noString(e.typeCode()))
      defineAncestorElements(e.typeCode(), path, snapshot, containedSlices, p, elements);
    
    for (ElementDefn child : e.getElements()) 
      defineElement(null, p, elements, child, path+"."+child.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), snapshot, false);
    
  }

  /*
  *     // resource
    // domain resource
    for (ElementDefn child : definitions.getBaseResources().get("DomainResource").getRoot().getElements()) 
      defineElement(null, p, p.getSnapshot(), child, r.getRoot().getName()+"."+child.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource);

  */
    /*
  private String registerMapping(ConformancePackage ap, StructureDefinition p, String m) {
    for (StructureDefinitionMappingComponent map : p.getMapping()) {
      if (map.getUri().equals(m))
        return map.getIdentity();
    }
    StructureDefinitionMappingComponent map = new StructureDefinitionMappingComponent();
    MappingSpace space = definitions.getMapTypes().get(m);
    if (space != null)
      map.setIdentity(space.getId());
    else
      map.setIdentity("m" + Integer.toString(p.getMapping().size()+1));
    map.setUri(m);
    String name = ap.metadata(m+"-name");
    if (Utilities.noString(name) && space != null)
      name = space.getTitle();
    if (!Utilities.noString(name))
      map.setName(name);
    String comments = ap.metadata(m+"-comments");
    if (Utilities.noString(comments) && space != null)
        comments = space.getPreamble();
    if (!Utilities.noString(comments))
      map.setComments(comments);
    return map.getIdentity();
  }
  */

  private void addToPaths(List<SliceHandle> myParents, String path, ElementDefinition ce, String profileName) throws Exception {
    Map<String, ElementDefinition> pmap = paths;
    if (!myParents.isEmpty())
      pmap = myParents.get(myParents.size()-1).paths;
    if (pmap.containsKey(path))
      throw new Exception("duplicate path "+path+" on profile "+profileName);
    pmap.put(path, ce);   
  }

  private String getNameForElement(ElementDefinition ce) throws Exception {
    if (ce.getName() == null) {
      String name = tail(ce.getPath());
      if (pathNames.contains(name))
        throw new Exception("Need to improve name generation algorithm (name = "+name+", on path = "+ce.getPath()+")");
      pathNames.add(name);
      ce.setName(name);
    }
    return ce.getName();
  }

  private String getNameForPath(List<SliceHandle> myParents, String path) throws Exception {
    for (int i = myParents.size()-1; i >= 0; i--) {
      Map<String, ElementDefinition> pmap = myParents.get(i).paths;;
      if (pmap.containsKey(path))
        return getNameForElement(pmap.get(path));
    }
    Map<String, ElementDefinition> pmap = paths;
    if (pmap.containsKey(path))
      return getNameForElement(pmap.get(path));
    throw new Exception("Unable to find element for path "+path);  
  }

  private String tail(String path) {
    return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
  }

  private void makeExtensionSlice(String extensionName, StructureDefinition p, StructureDefinitionSnapshotComponent c, ElementDefn e, String path) throws URISyntaxException, Exception {
      ElementDefinition ex = createBaseDefinition(p, path, definitions.getBaseResources().get("DomainResource").getRoot().getElementByName(extensionName));
      c.getElement().add(ex);
  }
  
  private void addMapping(StructureDefinition p, ElementDefinition definition, String target, String map, Profile pack) {
    if (!Utilities.noString(map)) {
      String id;
      if (pack != null && pack.getMappingSpaces().containsKey(target))
        id = pack.getMappingSpaces().get(target).getId();
      else
        id = definitions.getMapTypes().get(target).getId();
      
      if (!mappingExists(p, id)) {
        StructureDefinitionMappingComponent pm = new StructureDefinitionMappingComponent();
        p.getMapping().add(pm);
        pm.setIdentity(id);
        pm.setUri(target);
        if (pack != null && pack.getMappingSpaces().containsKey(target))
          pm.setName(pack.getMappingSpaces().get(target).getTitle());
        else
          pm.setName(definitions.getMapTypes().get(target).getTitle());
      }
      boolean found = false;
      for (ElementDefinitionMappingComponent m : definition.getMapping()) {
        found = found || (m.getIdentity().equals(id) && m.getMap().equals(map)); 
      }
      if (!found) {
        ElementDefinitionMappingComponent m = new ElementDefinitionMappingComponent();
        m.setIdentity(id);
        m.setMap(map);
        definition.getMapping().add(m);
      }
    }
  }


  private boolean mappingExists(StructureDefinition p, String id) {
    for (StructureDefinitionMappingComponent t : p.getMapping()) {
      if (id.equals(t.getIdentity()))
        return true;
    }
    return false;
  }

  private ElementDefinition createBaseDefinition(StructureDefinition p, String path, ElementDefn src) throws URISyntaxException {
    ElementDefinition ce = new ElementDefinition();
    ce.setPath(path+"."+src.getName());
    ce.setShort(src.getShortDefn());
    ce.setDefinition(src.getDefinition());
    ce.setComments(src.getComments());
    ce.setRequirements(src.getRequirements());
    for (String a : src.getAliases())
      ce.addAlias(a);
    ce.setMin(src.getMinCardinality());
    if (src.getMaxCardinality() != null)
      ce.setMax(src.getMaxCardinality() == Integer.MAX_VALUE ? "*" : src.getMaxCardinality().toString());
    ce.getType().add(new TypeRefComponent());
    ce.getType().get(0).setCode(src.typeCode());
    // this one should never be used
    if (!Utilities.noString(src.getTypes().get(0).getProfile()))
      ce.getType().get(0).addProfile(src.getTypes().get(0).getProfile());
    // todo? conditions, constraints, binding, mapping
    if (src.hasModifier())
      ce.setIsModifier(src.isModifier());
    if (src.hasSummaryItem())
      ce.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      ce.addCondition(id.getId());
    return ce;
  }

  public ConstraintStructure wrapProfile(StructureDefinition profile) throws Exception {
    return new ConstraintStructure(profile, definitions.getUsageIG((String) profile.getUserData(ToolResourceUtilities.NAME_SPEC_USAGE), "generating profile "+profile.getId()));
  }

  public void convertElements(ElementDefn src, StructureDefinition ed, String path) throws Exception {
    ElementDefinition dst = new ElementDefinition();
    if (!ed.hasDifferential())
      ed.setDifferential(new StructureDefinitionDifferentialComponent());
    ed.getDifferential().getElement().add(dst);
    String thisPath = path == null ? "Extension" : path;
    dst.setPath(thisPath);
    if (dst.getPath().endsWith(".extension"))
      dst.setName(src.getName());
    
    dst.setShort(src.getShortDefn());
    dst.setDefinition(src.getDefinition());
    dst.setComments(src.getComments());
    if (src.getMaxCardinality() != null) {
      if (src.getMaxCardinality() == Integer.MAX_VALUE)
        dst.setMax("*");
      else
        dst.setMax(src.getMaxCardinality().toString());
    }
    if (src.getMinCardinality() != null)
      dst.setMin(src.getMinCardinality());
    if (src.getFixed() != null)
      dst.setFixed(src.getFixed());
    if (src.hasMustSupport())
      dst.setMustSupport(src.isMustSupport());
    if (src.hasModifier())
      dst.setIsModifier(src.isModifier());
    if (src.hasSummaryItem())
      dst.setIsSummaryElement(Factory.newBoolean(src.isSummary()));
    for (Invariant id : src.getStatedInvariants()) 
      dst.addCondition(id.getId());

    // dDst.
    for (TypeRef t : src.getTypes()) {
      if (t.hasParams()) {
        for (String tp : t.getParams()) {
          ElementDefinition.TypeRefComponent type = new ElementDefinition.TypeRefComponent();
          type.setCode(t.getName());
          if (t.hasProfile())
            type.addProfile(t.getProfile()); // this should only happen if t.getParams().size() == 1
          else
            type.addProfile("http://hl7.org/fhir/StructureDefinition/"+(tp.equals("Any") ? "Resource" : tp));
          dst.getType().add(type);
        }
      } else {
        ElementDefinition.TypeRefComponent type = new ElementDefinition.TypeRefComponent();
        type.setCode(t.getName());
        dst.getType().add(type);
      }
    }
    if (definitions != null) { // igtodo - catch this
      for (String mu : definitions.getMapTypes().keySet()) {
        if (src.hasMapping(mu)) {
          addMapping(ed, dst, mu, src.getMapping(mu), null);
        }
      }
    }
    if (src.hasBinding())
      dst.setBinding(generateBinding(src.getBinding()));
    if (src.getElements().isEmpty()) {
      if (path == null)
        throw new Exception("?error parsing extension");
    } else {
      ElementDefn url = src.getElements().get(0);
      if (!url.getName().equals("url"))
        throw new Exception("first child of extension should be "+url);
      convertElements(url, ed, thisPath+".url");
      if (!hasValue(src)) {
        ElementDefn value = new ElementDefn();
        value.setName("value[x]");
        value.setMinCardinality(0);
        value.setMaxCardinality(0);
        src.getElements().add(value);
      }
      if (src.getElements().size() == 2 && 
          src.getElements().get(0).getName().equals("url") &&
          src.getElements().get(1).getName().equals("value[x]")) {
        ElementDefn value = src.getElements().get(1);
        convertElements(value, ed, thisPath+".value[x]");
      } else {
        for (ElementDefn child : src.getElements()) {
          if (child != url) {
            if (child.getName().startsWith("value"))
              convertElements(child, ed, thisPath+"."+child.getName());
            else {
              if (child.getElements().size() == 0 || !child.getElements().get(0).getName().equals("url")) {
                ElementDefn childUrl = new ElementDefn();
                childUrl.setName("url");
                childUrl.getTypes().add(new TypeRef("uri"));
                childUrl.setFixed(new UriType(child.getName()));
                child.getElements().add(0, childUrl);
              }
              if (!hasValue(child)) {
                ElementDefn value = new ElementDefn();
                value.setName("value[x]");
                value.setMinCardinality(0);
                value.setMaxCardinality(0);
                child.getElements().add(value);
              }
              convertElements(child, ed, thisPath+".extension");
            }
          }
        }
      }
    }
  }

  private boolean hasValue(ElementDefn child) {
    for (ElementDefn v : child.getElements()) {
      if (v.getName().startsWith("value"))
        return true;
    }
    return false;
  }

  public OperationDefinition generate(ResourceDefn r, Operation op) throws Exception {
    String name = r.getName().toLowerCase()+"-"+op.getName();
    OperationDefinition opd = new OperationDefinition();
    opd.setId(FormatUtilities.makeId(r.getName()+"-"+op.getName()));
    opd.setUrl("http://hl7.org/fhir/OperationDefinition/"+r.getName()+"-"+op.getName());
    opd.setName(op.getTitle());
    opd.setPublisher("HL7 (FHIR Project)");
    opd.addContact().getTelecom().add(org.hl7.fhir.instance.model.Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    opd.getContact().get(0).getTelecom().add(org.hl7.fhir.instance.model.Factory.newContactPoint(ContactPointSystem.EMAIL, "fhir@lists.hl7.org"));
    opd.setDescription(op.getDoco());
    opd.setStatus(ConformanceResourceStatus.DRAFT);
    opd.setDate(genDate.getTime());
    if (op.getKind().toLowerCase().equals("operation"))
      opd.setKind(OperationKind.OPERATION);
    else if (op.getKind().toLowerCase().equals("query"))
      opd.setKind(OperationKind.QUERY);
    else {
      throw new Exception("Unrecognized operation kind: '" + op.getKind() + "' for operation " + name);
    }
    opd.setCode(op.getName());
    opd.setNotes(op.getFooter());
    opd.setSystem(op.isSystem());
    if (op.isType())
      opd.addType(r.getName());
    opd.setInstance(op.isInstance());
    for (OperationParameter p : op.getParameters()) {
      produceOpParam(op.getName(), opd.getParameter(), p, null);
    }
    NarrativeGenerator gen = new NarrativeGenerator("", context);
    gen.generate(opd);
    return opd;
  }

  private void produceOpParam(String path, List<OperationDefinitionParameterComponent> opd, OperationParameter p, OperationParameterUse defUse) throws Exception {
    OperationDefinitionParameterComponent pp = new OperationDefinitionParameterComponent();
    pp.setName(p.getName());
    if (p.getUse().equals("in"))
      pp.setUse(OperationParameterUse.IN);
    else if (p.getUse().equals("out"))
      pp.setUse(OperationParameterUse.OUT);
    else if (path.contains("."))
      pp.setUse(defUse);
    else
      throw new Exception("Unable to determine parameter use: "+p.getUse()+" at "+path+"."+p.getName()); // but this is validated elsewhere
    pp.setDocumentation(p.getDoc());
    pp.setMin(p.getMin());
    pp.setMax(p.getMax());
    if (p.getBs() != null)
      pp.setBinding(new OperationDefinitionParameterBindingComponent().setStrength(p.getBs().getStrength()).setValueSet(buildReference(p.getBs())));
    Reference ref = new Reference();
    if (p.getProfile() != null) {
      ref.setReference(p.getProfile());
      pp.setProfile(ref);
    }
    opd.add(pp);
    if (p.getType().equals("Tuple")) {
      for (OperationParameter part : p.getParts()) {
        produceOpParam(path+"."+p.getName(), pp.getPart(), part, pp.getUse());
      }
    } else
      pp.setType(p.getType());
  }

  private void checkHasTypes(StructureDefinition p) {
    for (ElementDefinition ed : p.getSnapshot().getElement())
      if (!ed.hasType() && !ed.hasNameReference() && !(ed.getPath().equals("Resource") || ed.getPath().equals("Element")))
        throw new Error("No Type on "+ed.getPath());
  }


}
