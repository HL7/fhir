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
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingExtensibility;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength;
import org.hl7.fhir.definitions.model.BindingSpecification.ElementType;
import org.hl7.fhir.definitions.model.DefinedStringPattern;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn.ContextType;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.MappingSpace;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.ContactPoint.ContactPointSystem;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.IdType;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.BindingConformance;
import org.hl7.fhir.instance.model.Profile.ConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ConstraintSeverity;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.Profile.ExtensionContext;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileMappingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureSearchParamComponent;
import org.hl7.fhir.instance.model.Profile.PropertyRepresentation;
import org.hl7.fhir.instance.model.Profile.ResourceAggregationMode;
import org.hl7.fhir.instance.model.Profile.ResourceSlicingRules;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.instance.utils.ProfileUtilities;
import org.hl7.fhir.instance.utils.ToolingExtensions;
import org.hl7.fhir.instance.utils.WorkerContext;
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
  private Set<String> bindings = new HashSet<String>();

  // status
  // note that once we start slicing, the slices keep their own maps, but all share the master pathname list
  private Map<String, Profile.ElementComponent> paths = new HashMap<String, Profile.ElementComponent>();
  private List<String> pathNames = new ArrayList<String>();
  private class SliceHandle {
    private String name;
    private Map<String, Profile.ElementComponent> paths = new HashMap<String, Profile.ElementComponent>();
  }

  public ProfileGenerator(Definitions definitions, WorkerContext context) {
    super();
    this.definitions = definitions;
    this.context = context;
  }

  public Profile generate(PrimitiveType type, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ type.getCode());
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base Profile for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDate(new DateAndTime(genDate));
    p.setStatus(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublish(true); 
    cd.setType(type.getCode());
    cd.setPurpose("This is the defintion for the "+type.getCode()+" datatype");
    cd.setBase("http://hl7.org/fhir/Profile/Element"); // master profile
    cd.setDifferential(new ConstraintComponent());
    ElementComponent ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive Type " +type.getCode());
    ec.getDefinition().setFormal(type.getDefinition());
    ec.getDefinition().setComments(type.getComment());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("*");
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("Element"));
    
    ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive value for " +type.getCode());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("1");
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("xsd:"+type.getSchemaType()));
    
    reset();
    // now. the snapshot
    cd.setSnapshot(new ConstraintComponent());
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive Type " +type.getCode());
    ec.getDefinition().setFormal(type.getDefinition());
    ec.getDefinition().setComments(type.getComment());
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("Element"));
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("*");

    makeExtensionSlice("extension", null, p, cd.getSnapshot(), null, type.getCode());
        
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("1");
    ec.getDefinition().setShort("Primitive value for " +type.getCode());
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("xsd:"+type.getSchemaType()));
    
    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }

  public Profile generate(DefinedStringPattern type, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ type.getCode());
    p.setName(type.getCode());
    p.setPublisher("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base Profile for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDate(new DateAndTime(genDate));
    p.setStatus(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublish(true); 
    cd.setType(type.getCode());
    cd.setPurpose("This is the defintion for the "+type.getCode()+" datatype");
    cd.setBase("http://hl7.org/fhir/Profile/Element"); // master profile
    cd.setDifferential(new ConstraintComponent());
    ElementComponent ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive Type " +type.getCode());
    ec.getDefinition().setFormal(type.getDefinition());
    ec.getDefinition().setComments(type.getComment());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("*");
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("Element"));
    
    ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive value for " +type.getCode());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("1");
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("xsd:"+type.getBase()));
    
    reset();
    // now. the snapshot
    cd.setSnapshot(new ConstraintComponent());
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPath(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive Type " +type.getCode());
    ec.getDefinition().setFormal(type.getDefinition());
    ec.getDefinition().setComments(type.getComment());
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("Element"));
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("*");

    makeExtensionSlice("extension", null, p, cd.getSnapshot(), null, type.getCode());
    
    
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPath("value");
    ec.addRepresentation(PropertyRepresentation.XMLATTR);
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShort("Primitive value for " +type.getCode());
    ec.getDefinition().setMin(0);
    ec.getDefinition().setMax("1");
    ec.getDefinition().getType().add(new TypeRefComponent().setCode("xsd:"+type.getBase()));

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }

  public Profile generate(TypeDefn t, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ t.getName());
    p.setName(t.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base Profile for "+t.getName()+" Resource");
    p.setRequirements(t.getRequirements());
    p.setDate(new DateAndTime(genDate));
    p.setStatus(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublish(true); 
    cd.setType(t.getName());
    cd.setPurpose("This is the defintion for the "+t.getName()+" datatype");
    cd.setBase("http://hl7.org/fhir/Profile/Element"); // master profile
    cd.setDifferential(new ConstraintComponent());
    defineElement(null, p, cd.getDifferential(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None);
    
    reset();
    // now. the snapshot
    cd.setSnapshot(new ConstraintComponent());
    defineElement(null, p, cd.getSnapshot(), t, t.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.DataType);

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }
  
  public Profile generate(ProfiledType pt, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ pt.getName());
    p.setName(pt.getName());
    p.setPublisher("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base Profile for "+pt.getName()+" Resource");
    p.setDescription(pt.getDefinition());
    p.setDate(new DateAndTime(genDate));
    p.setStatus(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublish(true); 
    cd.setType(pt.getBaseType());
    cd.setName(pt.getName());
    cd.setPurpose("This is the invariants for the "+pt.getName()+" data type profile");
    cd.setBase("http://hl7.org/fhir/Profile/"+pt.getBaseType());
    ElementComponent e = new ElementComponent();
    e.setPath(pt.getBaseType());
    e.setName(pt.getName());
    e.setDefinition(new ElementDefinitionComponent());
    e.getDefinition().setShort(pt.getDefinition());
    e.getDefinition().setFormal(pt.getDescription());
    e.getDefinition().setMin(1);
    e.getDefinition().setMax("1");
    e.getDefinition().setIsModifier(false);
    
    ElementDefinitionConstraintComponent inv = new ElementDefinitionConstraintComponent();
    inv.setKey("1");
    inv.setName(pt.getInvariant().getName());
    inv.setSeverity(ConstraintSeverity.ERROR);
    inv.setHuman(pt.getInvariant().getEnglish());
    inv.setXpath(pt.getInvariant().getXpath());
    e.getDefinition().getConstraint().add(inv);
    cd.setDifferential(new ConstraintComponent());
    cd.getDifferential().getElement().add(e);

    reset();
    
    // now, the snapshot
    ProfileStructureComponent base = getTypeSnapshot(pt.getBaseType());
    new ProfileUtilities(context).generateSnapshot(base, cd, "http://hl7.org/fhir/Profile/"+pt.getBaseType(), p.getName());

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
    return p;
  }
  
  private ProfileStructureComponent getTypeSnapshot(String baseType) throws Exception {
    Profile p = definitions.getElementDefn(baseType).getProfile();
    for (ProfileStructureComponent s : p.getStructure()) {
      if (s.getType().equals(baseType) && s.getSnapshot() != null)
        return s;
    }
    throw new Exception("Unable to find snapshot for "+baseType);
  }

  public Profile generate(ResourceDefn r, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ r.getRoot().getName());
    p.setName(r.getRoot().getName());
    p.setPublisher("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, "http://hl7.org/fhir"));
    p.setDescription("Base Profile for "+r.getRoot().getName()+" Resource");
    p.setRequirements(r.getRequirements());
    p.setDate(new DateAndTime(genDate));
    p.setStatus(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublish(true); 
    cd.setType(r.getRoot().getName());
    cd.setPurpose("This is the definition for the "+r.getRoot().getName()+" resource");
    cd.setDifferential(new ConstraintComponent());
//    cd.setBase("http://hl7.org/fhir/Profile/Resource"); // this is semi-fictional, and causes errors.
    defineElement(null, p, cd.getDifferential(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None);

    reset();
    // now. the snashot
    cd.setSnapshot(new ConstraintComponent());
    defineElement(null, p, cd.getSnapshot(), r.getRoot(), r.getRoot().getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.Resource);

    List<String> names = new ArrayList<String>();
    names.addAll(r.getSearchParams().keySet());
    Collections.sort(names);
    for (String pn : names) {
      SearchParameter param = r.getSearchParams().get(pn);
      makeSearchParam(p, cd, r.getName(), param);
    }
    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }
  
  private void reset() {
    paths.clear();
    pathNames.clear();  
  }

  public Profile generate(ProfileDefn profile, String id, Calendar genDate) throws Exception {
    return generate(profile, id, null, genDate);
  }
  
  public Profile generate(ProfileDefn profile, String id, String html, Calendar genDate) throws Exception {
    if (profile.getSource() != null)
      return profile.getSource();
    Profile p = new Profile();
    p.setUrl("http://hl7.org/fhir/Profile/"+ id);
    p.setName(profile.metadata("name"));
    p.setPublisher(profile.metadata("author.name"));
    if (profile.hasMetadata("author.reference"))
      p.getTelecom().add(Factory.newContactPoint(ContactPointSystem.URL, profile.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    if (profile.hasMetadata("description"))
      p.setDescription(profile.metadata("description"));
    if (profile.hasMetadata("requirements"))
      p.setRequirements(profile.metadata("requirements"));

    if (profile.hasMetadata("date"))
      p.setDateElement(Factory.newDateTime(profile.metadata("date").substring(0, 10)));
    else
      p.setDate(new DateAndTime(genDate));

    if (profile.hasMetadata("status")) 
      p.setStatus(Profile.ResourceProfileStatus.fromCode(profile.metadata("status")));

    Set<String> containedSlices = new HashSet<String>();

    for (ResourceDefn resource : profile.getResources()) {
      Profile.ProfileStructureComponent c = new Profile.ProfileStructureComponent();
      p.getStructure().add(c);
      c.setPublish(resource.isPublishedInProfile());
      c.setType(resource.getRoot().getName());
      c.setBase("http://hl7.org/fhir/Profile/"+c.getType());
      if (!"".equals(resource.getRoot().getProfileName()))
        c.setName(resource.getRoot().getProfileName());
      c.setDifferential(new ConstraintComponent());
      defineElement(profile, p, c.getDifferential(), resource.getRoot(), resource.getName(), containedSlices, new ArrayList<ProfileGenerator.SliceHandle>(), SnapShotMode.None);
      List<String> names = new ArrayList<String>();
      names.addAll(resource.getSearchParams().keySet());
      Collections.sort(names);
      for (String pn : names) {
        SearchParameter param = resource.getSearchParams().get(pn);
        makeSearchParam(p, c, resource.getName(), param);
      }
      reset();
      // ok, c is the differential. now we make the snapshot
      ProfileStructureComponent base = definitions.getSnapShotForType(c.getType());
      new ProfileUtilities(context).generateSnapshot(base, c, "http://hl7.org/fhir/Profile/"+c.getType(), p.getName());
      reset();
    }
   
    for (ExtensionDefn ex : profile.getExtensions())
      p.getExtensionDefn().add(generateExtensionDefn(ex, p));

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatus(NarrativeStatus.GENERATED);
    p.getText().setDiv(div);
    return p;
  }

  private Profile.SearchParamType getSearchParamType(SearchType type) {
    switch (type) {
    case number:
      return Profile.SearchParamType.NUMBER;
    case string:
      return Profile.SearchParamType.STRING;
    case date:
      return Profile.SearchParamType.DATE;
    case reference:
      return Profile.SearchParamType.REFERENCE;
    case token:
      return Profile.SearchParamType.TOKEN;
    case composite:
      return Profile.SearchParamType.COMPOSITE;
    case quantity:
      return Profile.SearchParamType.QUANTITY;
    }
    return null;
  }

  private void makeSearchParam(Profile p, ProfileStructureComponent s, String rn, SearchParameter i) {
    ProfileStructureSearchParamComponent result = new ProfileStructureSearchParamComponent();
    result.setName(i.getCode());
    result.setType(getSearchParamType(i.getType()));
    result.setDocumentation(i.getDescription());
    result.setXpath(i.getXPath());
    s.getSearchParam().add(result);
  }


  private ElementDefinitionBindingComponent generateBinding(String bn, Profile p) throws Exception {
    BindingSpecification src = definitions.getBindingByName(bn);
    if (src == null)
      return null;

    ElementDefinitionBindingComponent dst = new Profile.ElementDefinitionBindingComponent();
    dst.setName(src.getName());
    if (src.getBindingStrength() != null) {
      dst.setConformance(convert(src.getBindingStrength()));
      dst.setIsExtensible(src.getExtensibility() == BindingExtensibility.Extensible);
    } else {
      if (src.getElementType() == ElementType.Simple) {
        dst.setConformance(convert(BindingStrength.Required));
        dst.setIsExtensible(false);
      }
      else if (src.getElementType() == ElementType.Complex) {
        dst.setIsExtensible(true);
        if (src.isExample()) {
          dst.setConformance(convert(BindingStrength.Example));
        } else {
          dst.setConformance(convert(BindingStrength.Preferred));
        }
      }
      else {
        dst.setConformance(convert(BindingStrength.Unstated));
        dst.setIsExtensible(true);
      }
    }
    if (src.getExtensible() != null)
      dst.setIsExtensible(src.getExtensible());
    if (src.getConformance() != null)
      dst.setConformance(src.getConformance());
    
    dst.setDescription(src.getDefinition());
    if (src.getBinding() != Binding.Unbound)
      dst.setReference(buildReference(src));    
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

  private BindingConformance convert(org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength bindingStrength) throws Exception {
    if (bindingStrength == null)
      return null;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Preferred)
      return BindingConformance.PREFERRED;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Required)
      return BindingConformance.REQUIRED;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Example)
      return BindingConformance.EXAMPLE;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Unstated)
      return null;
    throw new Exception("unknown value BindingStrength."+bindingStrength.toString());
  }

  private ProfileExtensionDefnComponent generateExtensionDefn(ExtensionDefn src, Profile p) throws Exception {
    ProfileExtensionDefnComponent dst = new Profile.ProfileExtensionDefnComponent();
    dst.setCode(src.getCode());
    if (!Utilities.noString(src.getDisplay()))
      dst.setDisplay(src.getDisplay());
    else
      dst.setDisplay(src.getDefinition().getShortDefn());
    for (String c : src.getContext().split(";")) {
      dst.addContext(c);
    }
    dst.setContextType(convertContextType(src.getType()));
    addExtensionElements(dst, src, p, null);
    return dst;
  }
    
  private void addExtensionElements(ProfileExtensionDefnComponent dst, ExtensionDefn src, Profile p, String path) throws Exception {
    ElementDefn dSrc = src.getDefinition();
    ElementDefinitionComponent dDst = new Profile.ElementDefinitionComponent();
    ElementComponent elem = new Profile.ElementComponent();
    dst.getElement().add(elem);
    elem.setDefinition(dDst);
    String thisPath = path == null ? src.getCode() : path+"."+src.getCode();
    elem.setPath(thisPath);

    dDst.setShort(dSrc.getShortDefn());
    dDst.setFormal(dSrc.getDefinition());
    dDst.setComments(dSrc.getComments());
    if (dSrc.getMaxCardinality() == null)
      dDst.setMax("*");
    else
      dDst.setMax(dSrc.getMaxCardinality().toString());
    dDst.setMin(dSrc.getMinCardinality());
    dDst.setMustSupport(dSrc.isMustSupport());
    dDst.setIsModifier(dSrc.isModifier());
    dDst.setIsSummaryElement(Factory.newBoolean(dSrc.isSummaryItem()));
    for (Invariant id : dSrc.getStatedInvariants()) 
      dDst.addCondition(id.getId());
    
    // dDst.
    for (TypeRef t : dSrc.getTypes()) {
      if (t.hasParams()) {
        for (String tp : t.getParams()) {
          TypeRefComponent type = new Profile.TypeRefComponent();
          type.setCode(t.getName());
          type.setProfile("http://hl7.org/fhir/Profile/"+tp);
          dDst.getType().add(type);
        }
      } else {
        TypeRefComponent type = new Profile.TypeRefComponent();
        type.setCode(t.getName());
        dDst.getType().add(type);
      }
    }
    for (String mu : definitions.getMapTypes().keySet()) {
      if (dSrc.hasMapping(mu)) {
        addMapping(p, dDst, mu, dSrc.getMapping(mu));
      }
    }
    if (!Utilities.noString(dSrc.getBindingName()))
      dDst.setBinding(generateBinding(dSrc.getBindingName(), p));
    for (ExtensionDefn child : src.getChildren())
      addExtensionElements(dst, child, p, thisPath); 
  }


  private ExtensionContext convertContextType(ContextType type) throws Exception {
    if (type == ContextType.DataType)
      return ExtensionContext.DATATYPE;
    if (type == ContextType.Resource)
      return ExtensionContext.RESOURCE;
    if (type == ContextType.Extension)
      return ExtensionContext.EXTENSION;
    if (type == ContextType.Mapping)
      return ExtensionContext.MAPPING;

    throw new Exception("unknown value ContextType."+type.toString());
  }

  /**
   * note: snapshot implies that we are generating a resource or a data type; for other profiles, the snapshot is generated elsewhere
   */
  private Profile.ElementComponent defineElement(ProfileDefn pd, Profile p, ConstraintComponent c, ElementDefn e, String path, Set<String> slices, List<SliceHandle> parentSlices, SnapShotMode snapshot) throws Exception 
  {
    Profile.ElementComponent ce = new Profile.ElementComponent();
    c.getElement().add(ce);
        
    ce.setPath(path);
    
    if (e.isXmlAttribute())
      ce.addRepresentation(PropertyRepresentation.XMLATTR);
    List<SliceHandle> myParents = new ArrayList<ProfileGenerator.SliceHandle>();
    myParents.addAll(parentSlices);
    
    // If this element has a profile name, and this is the first of the
    // slicing group, add a slicing group "entry" (= first slice member,
    // which holds Slicing information)
    if (!Utilities.noString(e.getProfileName())) {
      if (!Utilities.noString(e.getDiscriminator()) && !slices.contains(path)) {
        ce.setSlicing(new Profile.ElementSlicingComponent());
        String[] d = e.getDiscriminator().split("\\|");
        if (d.length >= 1)
          ce.getSlicing().setDiscriminator(d[0].trim());
        if (d.length >= 2)
          ce.getSlicing().setOrdered(Boolean.getBoolean(d[1].trim()));
        else
          ce.getSlicing().setOrdered(false);
        if (d.length >= 3)
          ce.getSlicing().setRules(ResourceSlicingRules.fromCode(d[2].trim()));
        else
          ce.getSlicing().setRules(ResourceSlicingRules.OPEN);
        ce = new Profile.ElementComponent();
        c.getElement().add(ce);
        ce.setPath(path);
        slices.add(path);
      }
      SliceHandle hnd = new SliceHandle();
      hnd.name = path; // though this it not used?
      myParents.add(hnd);
      ce.setName(e.getProfileName());
    }
    addToPaths(myParents, path, ce, p.getName());
    
    ce.setDefinition(new Profile.ElementDefinitionComponent());
    if (!"".equals(e.getComments()))
      ce.getDefinition().setComments(e.getComments());
    if (!"".equals(e.getShortDefn()))
      ce.getDefinition().setShort(e.getShortDefn());
    if (!"".equals(e.getDefinition())) {
      ce.getDefinition().setFormal(e.getDefinition());
      if ("".equals(e.getShortDefn()))
        ce.getDefinition().setShort(e.getDefinition());
    }
    ce.getDefinition().setMustSupport(e.isMustSupport());

    if (e.getMaxLength() != null) 
      ce.getDefinition().setMax(e.getMaxLength()); 
    
    // no purpose here
    ce.getDefinition().setMin(e.getMinCardinality());
    ce.getDefinition().setMax(e.getMaxCardinality() == null ? "*" : e.getMaxCardinality().toString());

    if (e.typeCode().startsWith("@"))  {
      ce.getDefinition().setNameReference(getNameForPath(myParents, e.typeCode().substring(1)));
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
        } else {
        	expandedTypes.add(t);
        }
			}
    	for (TypeRef t : expandedTypes) {
        TypeRefComponent type = new Profile.TypeRefComponent();
      	type.setCode(t.getName());
	      String profile = t.getProfile();
	      if (profile == null && t.hasParams()) {
	      	profile = t.getParams().get(0);
	      }
	      if (profile != null) {
		      if (profile.startsWith("http:") || profile.startsWith("#")) {
		      	type.setProfile(profile);
		      } else {
	          type.setProfile("http://hl7.org/fhir/Profile/" + profile);
 	      	}
 	      }
 	      
 	      for (String aggregation : t.getAggregations()) {
					type.addAggregation(ResourceAggregationMode.fromCode(aggregation));
				}	      	
    		
        ce.getDefinition().getType().add(type);
    	}
    }
    
    // ce.setConformance(getType(e.getConformance()));
    for (Invariant id : e.getStatedInvariants()) 
      ce.getDefinition().addCondition(id.getId());
    
    // we don't know mustSupport here
    ce.getDefinition().setIsModifier(e.isModifier());
    ce.getDefinition().setIsSummaryElement(Factory.newBoolean(e.isSummaryItem()));
    
    for (String n : definitions.getMapTypes().keySet()) {
      addMapping(p, ce.getDefinition(), n, e.getMapping(n));
    }
    ToolingExtensions.addDisplayHint(ce.getDefinition(), e.getDisplayHint());

    for (String in : e.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = new Profile.ElementDefinitionConstraintComponent();
      Invariant inv = e.getInvariants().get(in);
      con.setKey(inv.getId());
      con.setName(inv.getName());
      if (Utilities.noString(inv.getSeverity()))
        con.setSeverity(ConstraintSeverity.ERROR);
      else
        con.setSeverity(ConstraintSeverity.fromCode(inv.getSeverity()));
      con.setHuman(inv.getEnglish());
      con.setXpath(inv.getXpath());
      ce.getDefinition().getConstraint().add(con);
    }
    // we don't have anything to say about constraints on resources

    if (!Utilities.noString(e.getBindingName())) {
      ce.getDefinition().setBinding(generateBinding(e.getBindingName(), p));
      bindings.add(e.getBindingName());
    }

    for (String m : e.getMappings().keySet()) {
      ElementDefinitionMappingComponent map = ce.getDefinition().addMapping();
      map.setIdentity(registerMapping(p, pd, m));
      map.setMap(e.getMappings().get(m));
    }

    if (snapshot != SnapShotMode.None && !e.getElements().isEmpty()) {    
      makeExtensionSlice("extension", pd, p, c, e, path);
      if (snapshot == SnapShotMode.Resource) { 
        makeExtensionSlice("modifierExtension", pd, p, c, e, path);

        if (!path.contains(".")) {
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseReference().getRoot().getElementByName("language")));
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseReference().getRoot().getElementByName("text")));
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseReference().getRoot().getElementByName("contained")));
        }
      }
    }
    Set<String> containedSlices = new HashSet<String>();
    for (ElementDefn child : e.getElements()) 
      defineElement(pd, p, c, child, path+"."+child.getName(), containedSlices, myParents, snapshot);
    
    return ce;
  }
  
  private String registerMapping(Profile p, ProfileDefn pd, String m) {
    for (ProfileMappingComponent map : p.getMapping()) {
      if (map.getUri().equals(m))
        return map.getIdentity();
    }
    ProfileMappingComponent map = new ProfileMappingComponent();
    MappingSpace space = definitions.getMapTypes().get(m);
    if (space != null)
      map.setIdentity(space.getId());
    else
      map.setIdentity("m" + Integer.toString(p.getMapping().size()+1));
    map.setUri(m);
    String name = pd.metadata(m+"-name");
    if (Utilities.noString(name) && space != null)
      name = space.getTitle();
    if (!Utilities.noString(name))
      map.setName(name);
    String comments = pd.metadata(m+"-comments");
    if (Utilities.noString(comments) && space != null)
        comments = space.getPreamble();
    if (!Utilities.noString(comments))
      map.setComments(comments);
    return map.getIdentity();
  }

  private void addToPaths(List<SliceHandle> myParents, String path, ElementComponent ce, String profileName) throws Exception {
    Map<String, Profile.ElementComponent> pmap = paths;
    if (!myParents.isEmpty())
      pmap = myParents.get(myParents.size()-1).paths;
    if (pmap.containsKey(path))
      throw new Exception("duplicate path "+path+" on profile "+profileName);
    pmap.put(path, ce);   
  }

  private String getNameForElement(ElementComponent ce) throws Exception {
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
      Map<String, Profile.ElementComponent> pmap = myParents.get(i).paths;;
      if (pmap.containsKey(path))
        return getNameForElement(pmap.get(path));
    }
    Map<String, Profile.ElementComponent> pmap = paths;
    if (pmap.containsKey(path))
      return getNameForElement(pmap.get(path));
    throw new Exception("Unable to find element for path "+path);  
  }

  private String tail(String path) {
    return path.contains(".") ? path.substring(path.lastIndexOf(".")+1) : path;
  }

  private void makeExtensionSlice(String extensionName, ProfileDefn pd, Profile p, ConstraintComponent c, ElementDefn e, String path) throws URISyntaxException, Exception {
      ElementComponent ex = createBaseDefinition(p, path, definitions.getBaseReference().getRoot().getElementByName(extensionName));
      c.getElement().add(ex);
  }
  
  private void addMapping(Profile p, ElementDefinitionComponent definition, String target, String map) {
    if (!Utilities.noString(map)) {
      String id = definitions.getMapTypes().get(target).getId();
      if (!mappingExists(p, id)) {
        ProfileMappingComponent pm = new ProfileMappingComponent();
        p.getMapping().add(pm);
        pm.setIdentity(id);
        pm.setUri(target);
        pm.setName(definitions.getMapTypes().get(target).getTitle());
      }
      ElementDefinitionMappingComponent m = new Profile.ElementDefinitionMappingComponent();
      m.setIdentity(id);
      m.setMap(map);
      definition.getMapping().add(m);
    }
  }

  private boolean mappingExists(Profile p, String id) {
    for (ProfileMappingComponent t : p.getMapping()) {
      if (id.equals(t.getIdentity()))
        return true;
    }
    return false;
  }

  private ElementComponent createBaseDefinition(Profile p, String path, ElementDefn src) throws URISyntaxException {
    ElementComponent ce = new Profile.ElementComponent();
    ce.setPath(path+"."+src.getName());
    ce.setDefinition(new Profile.ElementDefinitionComponent());
    ce.getDefinition().setShort(src.getShortDefn());
    ce.getDefinition().setFormal(src.getDefinition());
    ce.getDefinition().setComments(src.getComments());
    ce.getDefinition().setRequirements(src.getRequirements());
    for (String a : src.getAliases())
      ce.getDefinition().addSynonym(a);
    ce.getDefinition().setMin(src.getMinCardinality());
    ce.getDefinition().setMax(src.getMaxCardinality() == null ? "*" : src.getMaxCardinality().toString());
    ce.getDefinition().getType().add(new Profile.TypeRefComponent());
    ce.getDefinition().getType().get(0).setCode(src.typeCode());
    // this one should never be used
    if (!Utilities.noString(src.getTypes().get(0).getProfile()))
      ce.getDefinition().getType().get(0).setProfile(src.getTypes().get(0).getProfile());
    // todo? conditions, constraints, binding, mapping
    ce.getDefinition().setIsModifier(src.isModifier());
    ce.getDefinition().setIsSummaryElement(Factory.newBoolean(src.isSummaryItem()));
    for (Invariant id : src.getStatedInvariants()) 
      ce.getDefinition().addCondition(id.getId());
    return ce;
  }

  public static ProfileDefn wrapProfile(Profile profile) {
    ProfileDefn p = new ProfileDefn();
    p.setSource(profile);
    return p;
  }


}
