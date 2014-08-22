package org.hl7.fhir.definitions.generators.specification;
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
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.saxon.value.YearMonthDurationValue;

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
import org.hl7.fhir.definitions.model.ProfiledType;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.PrimitiveType;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.TypeDefn;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.DateAndTime;
import org.hl7.fhir.instance.model.Enumeration;
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
import org.hl7.fhir.utilities.xhtml.XhtmlParser;

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
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ type.getCode());
    p.setNameSimple(type.getCode());
    p.setPublisherSimple("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    p.setDescriptionSimple("Base Profile for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDateSimple(new DateAndTime(genDate));
    p.setStatusSimple(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublishSimple(true); 
    cd.setTypeSimple(type.getCode());
    cd.setPurposeSimple("This is the defintion for the "+type.getCode()+" datatype");
    cd.setBaseSimple("http://hl7.org/fhir/Profile/Element"); // master profile
    cd.setDifferential(new ConstraintComponent());
    ElementComponent ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPathSimple(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShortSimple("Primitive Type " +type.getCode());
    ec.getDefinition().setFormalSimple(type.getDefinition());
    ec.getDefinition().setCommentsSimple(type.getComment());
    ec.getDefinition().setMinSimple(0);
    ec.getDefinition().setMaxSimple("*");
    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple("Element"));
    
//    ec = new ElementComponent();
//    cd.getDifferential().getElement().add(ec);
//    ec.setPathSimple("value");
//    ec.addRepresentationSimple(PropertyRepresentation.xmlAttr);
//    ec.setDefinition(new ElementDefinitionComponent());
//    ec.getDefinition().setShortSimple("Primitive value for " +type.getCode());
//    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple(type.getSchemaType()));
    
    reset();
    // now. the snapshot
    cd.setSnapshot(new ConstraintComponent());
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPathSimple(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShortSimple("Primitive Type " +type.getCode());
    ec.getDefinition().setFormalSimple(type.getDefinition());
    ec.getDefinition().setCommentsSimple(type.getComment());
    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple("Element"));
    ec.getDefinition().setMinSimple(0);
    ec.getDefinition().setMaxSimple("*");

    makeExtensionSlice("extension", null, p, cd.getSnapshot(), null, type.getCode());
    
    
//    ec = new ElementComponent();
//    cd.getSnapshot().getElement().add(ec);
//    ec.setPathSimple("value");
//    ec.addRepresentationSimple(PropertyRepresentation.xmlAttr);
//    ec.setDefinition(new ElementDefinitionComponent());
//    ec.getDefinition().setShortSimple("Primitive value for " +type.getCode());
//    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple(type.getSchemaType()));

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }

  public Profile generate(DefinedStringPattern type, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ type.getCode());
    p.setNameSimple(type.getCode());
    p.setPublisherSimple("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    p.setDescriptionSimple("Base Profile for "+type.getCode()+" Resource: "+type.getDefinition());
    p.setDateSimple(new DateAndTime(genDate));
    p.setStatusSimple(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublishSimple(true); 
    cd.setTypeSimple(type.getCode());
    cd.setPurposeSimple("This is the defintion for the "+type.getCode()+" datatype");
    cd.setBaseSimple("http://hl7.org/fhir/Profile/Element"); // master profile
    cd.setDifferential(new ConstraintComponent());
    ElementComponent ec = new ElementComponent();
    cd.getDifferential().getElement().add(ec);
    ec.setPathSimple(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShortSimple("Primitive Type " +type.getCode());
    ec.getDefinition().setFormalSimple(type.getDefinition());
    ec.getDefinition().setCommentsSimple(type.getComment());
    ec.getDefinition().setMinSimple(0);
    ec.getDefinition().setMaxSimple("*");
    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple("Element"));
    
//    ec = new ElementComponent();
//    cd.getDifferential().getElement().add(ec);
//    ec.setPathSimple("value");
//    ec.addRepresentationSimple(PropertyRepresentation.xmlAttr);
//    ec.setDefinition(new ElementDefinitionComponent());
//    ec.getDefinition().setShortSimple("Primitive value for " +type.getCode());
//    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple(type.getSchemaType()));
    
    reset();
    // now. the snapshot
    cd.setSnapshot(new ConstraintComponent());
    ec = new ElementComponent();
    cd.getSnapshot().getElement().add(ec);
    ec.setPathSimple(type.getCode());
    ec.setDefinition(new ElementDefinitionComponent());
    ec.getDefinition().setShortSimple("Primitive Type " +type.getCode());
    ec.getDefinition().setFormalSimple(type.getDefinition());
    ec.getDefinition().setCommentsSimple(type.getComment());
    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple("Element"));
    ec.getDefinition().setMinSimple(0);
    ec.getDefinition().setMaxSimple("*");

    makeExtensionSlice("extension", null, p, cd.getSnapshot(), null, type.getCode());
    
    
//    ec = new ElementComponent();
//    cd.getSnapshot().getElement().add(ec);
//    ec.setPathSimple("value");
//    ec.addRepresentationSimple(PropertyRepresentation.xmlAttr);
//    ec.setDefinition(new ElementDefinitionComponent());
//    ec.getDefinition().setShortSimple("Primitive value for " +type.getCode());
//    ec.getDefinition().getType().add(new TypeRefComponent().setCodeSimple(type.getSchemaType()));

    containedSlices.clear();

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }

  public Profile generate(TypeDefn t, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ t.getName());
    p.setNameSimple(t.getName());
    p.setPublisherSimple("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    p.setDescriptionSimple("Base Profile for "+t.getName()+" Resource");
    p.setRequirementsSimple(t.getRequirements());
    p.setDateSimple(new DateAndTime(genDate));
    p.setStatusSimple(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublishSimple(true); 
    cd.setTypeSimple(t.getName());
    cd.setPurposeSimple("This is the defintion for the "+t.getName()+" datatype");
    cd.setBaseSimple("http://hl7.org/fhir/Profile/Element"); // master profile
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
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }
  
  public Profile generate(ProfiledType pt, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ pt.getName());
    p.setNameSimple(pt.getName());
    p.setPublisherSimple("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    p.setDescriptionSimple("Base Profile for "+pt.getName()+" Resource");
    p.setDescriptionSimple(pt.getDefinition());
    p.setDateSimple(new DateAndTime(genDate));
    p.setStatusSimple(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublishSimple(true); 
    cd.setTypeSimple(pt.getBaseType());
    cd.setNameSimple(pt.getName());
    cd.setPurposeSimple("This is the invariants for the "+pt.getName()+" data type profile");
    cd.setBaseSimple("http://hl7.org/fhir/Profile/"+pt.getBaseType());
    ElementComponent e = new ElementComponent();
    e.setPathSimple(pt.getBaseType());
    e.setNameSimple(pt.getName());
    e.setDefinition(new ElementDefinitionComponent());
    e.getDefinition().setShortSimple(pt.getDefinition());
    e.getDefinition().setFormalSimple(pt.getDescription());
    e.getDefinition().setMinSimple(1);
    e.getDefinition().setMaxSimple("1");
    e.getDefinition().setIsModifierSimple(false);
    
    ElementDefinitionConstraintComponent inv = new ElementDefinitionConstraintComponent();
    inv.setKeySimple("1");
    inv.setNameSimple(pt.getInvariant().getName());
    inv.setSeveritySimple(ConstraintSeverity.error);
    inv.setHumanSimple(pt.getInvariant().getEnglish());
    inv.setXpathSimple(pt.getInvariant().getXpath());
    e.getDefinition().getConstraint().add(inv);
    cd.setDifferential(new ConstraintComponent());
    cd.getDifferential().getElement().add(e);

    reset();
    
    // now, the snapshot
    ProfileStructureComponent base = getTypeSnapshot(pt.getBaseType());
    new ProfileUtilities(context).generateSnapshot(base, cd, "http://hl7.org/fhir/Profile/"+pt.getBaseType(), p.getNameSimple());

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addTag("h2").addText("Data type "+pt.getName());
    div.addTag("p").addText(pt.getDefinition());
    div.addTag("h3").addText("Rule");
    div.addTag("p").addText(pt.getInvariant().getEnglish());
    div.addTag("p").addText("XPath:");
    div.addTag("blockquote").addTag("pre").addText(pt.getInvariant().getXpath());
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }
  
  private ProfileStructureComponent getTypeSnapshot(String baseType) throws Exception {
    Profile p = definitions.getElementDefn(baseType).getProfile();
    for (ProfileStructureComponent s : p.getStructure()) {
      if (s.getTypeSimple().equals(baseType) && s.getSnapshot() != null)
        return s;
    }
    throw new Exception("Unable to find snapshot for "+baseType);
  }

  public Profile generate(ResourceDefn r, Calendar genDate) throws Exception {
    Profile p = new Profile();
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ r.getRoot().getName());
    p.setNameSimple(r.getRoot().getName());
    p.setPublisherSimple("HL7 FHIR Standard");
    p.getTelecom().add(Factory.newContact(ContactSystem.url, "http://hl7.org/fhir"));
    p.setDescriptionSimple("Base Profile for "+r.getRoot().getName()+" Resource");
    p.setRequirementsSimple(r.getRequirements());
    p.setDateSimple(new DateAndTime(genDate));
    p.setStatusSimple(Profile.ResourceProfileStatus.fromCode("draft")); // DSTU

    Set<String> containedSlices = new HashSet<String>();

    // first, the differential
    Profile.ProfileStructureComponent cd = new Profile.ProfileStructureComponent();
    p.getStructure().add(cd);
    cd.setPublishSimple(true); 
    cd.setTypeSimple(r.getRoot().getName());
    cd.setPurposeSimple("This is the definition for the "+r.getRoot().getName()+" resource");
    cd.setDifferential(new ConstraintComponent());
//    cd.setBaseSimple("http://hl7.org/fhir/Profile/Resource"); // this is semi-fictional, and causes errors.
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
    p.getText().setStatusSimple(NarrativeStatus.generated);
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
    p.setUrlSimple("http://hl7.org/fhir/Profile/"+ id);
    p.setNameSimple(profile.metadata("name"));
    p.setPublisherSimple(profile.metadata("author.name"));
    if (profile.hasMetadata("author.reference"))
      p.getTelecom().add(Factory.newContact(ContactSystem.url, profile.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    if (profile.hasMetadata("description"))
      p.setDescriptionSimple(profile.metadata("description"));
    if (profile.hasMetadata("requirements"))
      p.setRequirementsSimple(profile.metadata("requirements"));

    if (profile.hasMetadata("date"))
      p.setDate(Factory.newDateTime(profile.metadata("date").substring(0, 10)));
    else
      p.setDateSimple(new DateAndTime(genDate));

    if (profile.hasMetadata("status")) 
      p.setStatusSimple(Profile.ResourceProfileStatus.fromCode(profile.metadata("status")));

    Set<String> containedSlices = new HashSet<String>();

    for (ResourceDefn resource : profile.getResources()) {
      Profile.ProfileStructureComponent c = new Profile.ProfileStructureComponent();
      p.getStructure().add(c);
      c.setPublishSimple(true); // todo: when should this be set to true?
      c.setTypeSimple(resource.getRoot().getName());
      c.setBaseSimple("http://hl7.org/fhir/Profile/"+c.getTypeSimple());
      if (!"".equals(resource.getRoot().getProfileName()))
        c.setName(Factory.newString_(resource.getRoot().getProfileName()));
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
      ProfileStructureComponent base = definitions.getSnapShotForType(c.getTypeSimple());
      new ProfileUtilities(context).generateSnapshot(base, c, "http://hl7.org/fhir/Profile/"+c.getTypeSimple(), p.getNameSimple());
      reset();
    }
   
    for (ExtensionDefn ex : profile.getExtensions())
      p.getExtensionDefn().add(generateExtensionDefn(ex, p));

    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.addText("to do");
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }

  private Profile.SearchParamType getSearchParamType(SearchType type) {
    switch (type) {
    case number:
      return Profile.SearchParamType.number;
    case string:
      return Profile.SearchParamType.string;
    case date:
      return Profile.SearchParamType.date;
    case reference:
      return Profile.SearchParamType.reference;
    case token:
      return Profile.SearchParamType.token;
    case composite:
      return Profile.SearchParamType.composite;
    case quantity:
      return Profile.SearchParamType.quantity;
    }
    return null;
  }

  private void makeSearchParam(Profile p, ProfileStructureComponent s, String rn, SearchParameter i) {
    ProfileStructureSearchParamComponent result = new ProfileStructureSearchParamComponent();
    result.setNameSimple(i.getCode());
    result.setTypeSimple(getSearchParamType(i.getType()));
    result.setDocumentation(Factory.newString_(i.getDescription()));
    result.setXpathSimple(i.getXPath());
    s.getSearchParam().add(result);
  }


  private ElementDefinitionBindingComponent generateBinding(String bn, Profile p) throws Exception {
    BindingSpecification src = definitions.getBindingByName(bn);
    if (src == null)
      return null;

    ElementDefinitionBindingComponent dst = new Profile.ElementDefinitionBindingComponent();
    dst.setName(Factory.newString_(src.getName()));
    if (src.getBindingStrength() != null) {
      dst.setConformanceSimple(convert(src.getBindingStrength()));
      dst.setIsExtensibleSimple(src.getExtensibility() == BindingExtensibility.Extensible);
    } else {
      if (src.getElementType() == ElementType.Simple) {
        dst.setConformanceSimple(convert(BindingStrength.Required));
        dst.setIsExtensibleSimple(false);
      }
      else if (src.getElementType() == ElementType.Complex) {
        dst.setIsExtensibleSimple(true);
        if (src.isExample()) {
          dst.setConformanceSimple(convert(BindingStrength.Example));
        } else {
          dst.setConformanceSimple(convert(BindingStrength.Preferred));
        }
      }
      else {
        dst.setConformanceSimple(convert(BindingStrength.Unstated));
        dst.setIsExtensibleSimple(true);
      }
    }
    dst.setDescription(Factory.newString_(src.getDefinition()));
    if (src.getBinding() != Binding.Unbound)
      dst.setReference(buildReference(src));    
    return dst;
  }

  private Type buildReference(BindingSpecification src) throws Exception {
    switch (src.getBinding()) {
    case Unbound: return null;
    case CodeList:
      if (src.getReference().startsWith("#"))
        return Factory.makeResourceReference("http://hl7.org/fhir/vs/"+src.getReference().substring(1));
      else
        throw new Exception("not done yet");
    case ValueSet: 
      if (!Utilities.noString(src.getReference()))
        if (src.getReference().startsWith("http"))
          return Factory.makeResourceReference(src.getReference());
        else if (src.getReference().startsWith("valueset-"))
          return Factory.makeResourceReference("http://hl7.org/fhir/vs/"+src.getReference().substring(9));
        else
          return Factory.makeResourceReference("http://hl7.org/fhir/vs/"+src.getReference());
      else
        return null; // throw new Exception("not done yet");
    case Reference: return Factory.newUri(src.getReference());
    case Special: 
      return Factory.makeResourceReference("http://hl7.org/fhir/vs/"+src.getReference().substring(1));
    default: 
      throw new Exception("not done yet");
    }
  }

  private BindingConformance convert(org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength bindingStrength) throws Exception {
    if (bindingStrength == null)
      return null;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Preferred)
      return BindingConformance.preferred;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Required)
      return BindingConformance.required;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Example)
      return BindingConformance.example;
    if (bindingStrength == org.hl7.fhir.definitions.model.BindingSpecification.BindingStrength.Unstated)
      return null;
    throw new Exception("unknown value BindingStrength."+bindingStrength.toString());
  }

  private ProfileExtensionDefnComponent generateExtensionDefn(ExtensionDefn src, Profile p) throws Exception {
    ProfileExtensionDefnComponent dst = new Profile.ProfileExtensionDefnComponent();
    dst.setCode(Factory.newCode(src.getCode()));
    dst.setDisplaySimple(src.getDefinition().getShortDefn());
    for (String c : src.getContext().split(";")) {
      dst.getContext().add(Factory.newString_(c));
    }
    dst.setContextTypeSimple(convertContextType(src.getType()));
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
    elem.setPathSimple(thisPath);

    dDst.setShort(Factory.newString_(dSrc.getShortDefn()));
    dDst.setFormal(Factory.newString_(dSrc.getDefinition()));
    dDst.setComments(Factory.newString_(dSrc.getComments()));
    if (dSrc.getMaxCardinality() == null)
      dDst.setMax(Factory.newString_("*"));
    else
      dDst.setMax(Factory.newString_(dSrc.getMaxCardinality().toString()));
    dDst.setMin(Factory.newInteger(dSrc.getMinCardinality()));
    dDst.setMustSupport(Factory.newBoolean(dSrc.isMustSupport()));
    dDst.setIsModifier(Factory.newBoolean(dSrc.isModifier()));
    // dDst.
    for (TypeRef t : dSrc.getTypes()) {
      if (t.hasParams()) {
        for (String tp : t.getParams()) {
          TypeRefComponent type = new Profile.TypeRefComponent();
          type.setCode(Factory.newCode(t.getName()));
          type.setProfileSimple("http://hl7.org/fhir/Profile/"+tp);
          dDst.getType().add(type);
        }
      } else {
        TypeRefComponent type = new Profile.TypeRefComponent();
        type.setCode(Factory.newCode(t.getName()));
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
      return ExtensionContext.datatype;
    if (type == ContextType.Resource)
      return ExtensionContext.resource;
    if (type == ContextType.Extension)
      return ExtensionContext.extension;
    if (type == ContextType.Mapping)
      return ExtensionContext.mapping;

    throw new Exception("unknown value ContextType."+type.toString());
  }

  /**
   * note: snapshot implies that we are generating a resource or a data type; for other profiles, the snapshot is generated elsewhere
   */
  private Profile.ElementComponent defineElement(ProfileDefn pd, Profile p, ConstraintComponent c, ElementDefn e, String path, Set<String> slices, List<SliceHandle> parentSlices, SnapShotMode snapshot) throws Exception 
  {
    Profile.ElementComponent ce = new Profile.ElementComponent();
    c.getElement().add(ce);
        
    ce.setPath(Factory.newString_(path));
    
    if (e.isXmlAttribute())
      ce.addRepresentationSimple(PropertyRepresentation.xmlAttr);
    List<SliceHandle> myParents = new ArrayList<ProfileGenerator.SliceHandle>();
    myParents.addAll(parentSlices);
    
    // If this element has a profile name, and this is the first of the
    // slicing group, add a slicing group "entry" (= first slice member,
    // which holds Slicing information)
    if (!Utilities.noString(e.getProfileName())) {
      if (!Utilities.noString(e.getDiscriminator()) && !slices.contains(path)) {
        ce.setSlicing(new Profile.ElementSlicingComponent());
        ce.getSlicing().setDiscriminatorSimple(e.getDiscriminator());
        ce.getSlicing().setOrderedSimple(false);
        ce.getSlicing().setRulesSimple(ResourceSlicingRules.open);
        ce = new Profile.ElementComponent();
        c.getElement().add(ce);
        ce.setPath(Factory.newString_(path));
        slices.add(path);
      }
      SliceHandle hnd = new SliceHandle();
      hnd.name = path; // though this it not used?
      myParents.add(hnd);
      ce.setName(Factory.newString_(e.getProfileName()));
    }
    addToPaths(myParents, path, ce, p.getNameSimple());
    
    ce.setDefinition(new Profile.ElementDefinitionComponent());
    if (!"".equals(e.getComments()))
      ce.getDefinition().setComments(Factory.newString_(e.getComments()));
    if (!"".equals(e.getShortDefn()))
      ce.getDefinition().setShort(Factory.newString_(e.getShortDefn()));
    if (!"".equals(e.getDefinition())) {
      ce.getDefinition().setFormal(Factory.newString_(e.getDefinition()));
      if ("".equals(e.getShortDefn()))
        ce.getDefinition().setShort(Factory.newString_(e.getDefinition()));
    }
    ce.getDefinition().setMustSupport(Factory.newBoolean(e.isMustSupport()));


    // no purpose here
    ce.getDefinition().setMin(Factory.newInteger(e.getMinCardinality()));
    ce.getDefinition().setMax(Factory.newString_(e.getMaxCardinality() == null ? "*" : e.getMaxCardinality().toString()));

    if (e.typeCode().startsWith("@"))  {
      ce.getDefinition().setNameReferenceSimple(getNameForPath(myParents, e.typeCode().substring(1)));
    } else {
      if (!Utilities.noString(e.getStatedProfile())) {
        if (e.getTypes().size() != 1)
          throw new Exception("mismatched type count for path " + path + " in profile " + p.getNameSimple());
        TypeRefComponent type = new Profile.TypeRefComponent();
        if (e.getTypes().get(0).getName().equals("Resource"))
          type.setCodeSimple("ResourceReference");
        else
          type.setCodeSimple(e.getTypes().get(0).getName());
        type.setProfileSimple(e.getStatedProfile());
        ce.getDefinition().getType().add(type);
      } else {
        for (TypeRef t : e.getTypes())  {
          // If this is Resource(A|B|C), duplicate the ResourceReference for each
          if(t.hasParams() && "Resource".equals(t.getName()))
          {
            for(String param : t.getParams()) {    
              TypeRefComponent type = new Profile.TypeRefComponent();
              type.setCodeSimple("ResourceReference");
              if (param.startsWith("http:"))
                type.setProfileSimple(param);
              else 
                type.setProfileSimple("http://hl7.org/fhir/Profile/"+param);
              ce.getDefinition().getType().add(type);
            }
          } else {
            TypeRefComponent type = new Profile.TypeRefComponent();
            type.setCodeSimple(t.summaryFormal());
            ce.getDefinition().getType().add(type);
            if (e.getTypes().size() == 1 && !Utilities.noString(e.getStatedProfile()))
              type.setProfileSimple(e.getStatedProfile());
          }
        }
      }
    }
    
    // ce.setConformance(getType(e.getConformance()));
    if (!"".equals(e.getCondition())) {
      IdType cond = Factory.newId(e.getCondition());
      if (cond != null)
        ce.getDefinition().getCondition().add(cond);
    }
    // we don't know mustSupport here
    ce.getDefinition().setIsModifier(Factory.newBoolean(e.isModifier()));
    for (String n : definitions.getMapTypes().keySet()) {
      addMapping(p, ce.getDefinition(), n, e.getMapping(n));
    }
    ToolingExtensions.addDisplayHint(ce.getDefinition(), e.getDisplayHint());

    for (String in : e.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = new Profile.ElementDefinitionConstraintComponent();
      Invariant inv = e.getInvariants().get(in);
      con.setKeySimple(inv.getId());
      con.setNameSimple(inv.getName());
      con.setSeveritySimple(ConstraintSeverity.error);
      con.setHumanSimple(inv.getEnglish());
      con.setXpathSimple(inv.getXpath());
      ce.getDefinition().getConstraint().add(con);
    }
    // we don't have anything to say about constraints on resources

    if (!Utilities.noString(e.getBindingName())) {
      ce.getDefinition().setBinding(generateBinding(e.getBindingName(), p));
      bindings.add(e.getBindingName());
    }

    if( e.hasAggregation() )
    {
      TypeRefComponent t = new Profile.TypeRefComponent();
      ce.getDefinition().getType().add(t);
      t.setProfile(Factory.newUri(e.getAggregation()));
      Enumeration<ResourceAggregationMode> en = new Enumeration<ResourceAggregationMode>();
      en.setValue(ResourceAggregationMode.bundled);
      t.getAggregation().add(en);
    }


    if (snapshot != SnapShotMode.None && !e.getElements().isEmpty()) {    
      makeExtensionSlice("extension", pd, p, c, e, path);
      if (snapshot == SnapShotMode.Resource) { 
        makeExtensionSlice("modifierExtension", pd, p, c, e, path);

        if (!path.contains(".")) {
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("language")));
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("text")));
          c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("contained")));
        }
      }
    }
    Set<String> containedSlices = new HashSet<String>();
    for (ElementDefn child : e.getElements()) 
      defineElement(pd, p, c, child, path+"."+child.getName(), containedSlices, myParents, snapshot);
    
    return ce;
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
      String name = tail(ce.getPathSimple());
      if (pathNames.contains(name))
        throw new Exception("Need to improve name generation algorithm (name = "+name+", on path = "+ce.getPathSimple()+")");
      pathNames.add(name);
      ce.setNameSimple(name);
    }
    return ce.getNameSimple();
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
      ElementComponent ex = createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName(extensionName));
      c.getElement().add(ex);
  }
  
  private void addMapping(Profile p, ElementDefinitionComponent definition, String target, String map) {
    if (!Utilities.noString(map)) {
      String id = definitions.getMapTypes().get(target).getId();
      if (!mappingExists(p, id)) {
        ProfileMappingComponent pm = new ProfileMappingComponent();
        p.getMapping().add(pm);
        pm.setIdentitySimple(id);
        pm.setUriSimple(target);
        pm.setNameSimple(definitions.getMapTypes().get(target).getTitle());
      }
      ElementDefinitionMappingComponent m = new Profile.ElementDefinitionMappingComponent();
      m.setIdentitySimple(id);
      m.setMapSimple(map);
      definition.getMapping().add(m);
    }
  }

  private boolean mappingExists(Profile p, String id) {
    for (ProfileMappingComponent t : p.getMapping()) {
      if (id.equals(t.getIdentitySimple()))
        return true;
    }
    return false;
  }

  private ElementComponent createBaseDefinition(Profile p, String path, ElementDefn src) throws URISyntaxException {
    ElementComponent ce = new Profile.ElementComponent();
    ce.setPath(Factory.newString_(path+"."+src.getName()));
    ce.setDefinition(new Profile.ElementDefinitionComponent());
    ce.getDefinition().setShort(Factory.newString_(src.getShortDefn()));
    ce.getDefinition().setFormal(Factory.newString_(src.getDefinition()));
    ce.getDefinition().setComments(Factory.newString_(src.getComments()));
    ce.getDefinition().setRequirements(Factory.newString_(src.getRequirements()));
    for (String a : src.getAliases())
      ce.getDefinition().getSynonym().add(Factory.newString_(a));
    ce.getDefinition().setMin(Factory.newInteger(src.getMinCardinality()));
    ce.getDefinition().setMax(Factory.newString_(src.getMaxCardinality() == null ? "*" : src.getMaxCardinality().toString()));
    ce.getDefinition().getType().add(new Profile.TypeRefComponent());
    ce.getDefinition().getType().get(0).setCode(Factory.newCode(src.typeCode()));
    // this one should never be used
    if (!Utilities.noString(src.getStatedProfile()))
      ce.getDefinition().getType().get(0).setProfile(Factory.newUri(src.getStatedProfile()));
    // todo? conditions, constraints, binding, mapping
    ce.getDefinition().setIsModifierSimple(src.isModifier());
    return ce;
  }

  public static ProfileDefn wrapProfile(Profile profile) {
    ProfileDefn p = new ProfileDefn();
    p.setSource(profile);
    return p;
  }


}
