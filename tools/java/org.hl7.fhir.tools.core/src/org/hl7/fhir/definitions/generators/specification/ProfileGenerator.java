package org.hl7.fhir.definitions.generators.specification;
/*
Copyright (c) 2011-2013, HL7, Inc
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
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Set;

import org.hl7.fhir.definitions.model.BindingSpecification;
import org.hl7.fhir.definitions.model.BindingSpecification.Binding;
import org.hl7.fhir.definitions.model.BindingSpecification.BindingExtensibility;
import org.hl7.fhir.definitions.model.Definitions;
import org.hl7.fhir.definitions.model.ElementDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn;
import org.hl7.fhir.definitions.model.ExtensionDefn.ContextType;
import org.hl7.fhir.definitions.model.Invariant;
import org.hl7.fhir.definitions.model.ProfileDefn;
import org.hl7.fhir.definitions.model.ResourceDefn;
import org.hl7.fhir.definitions.model.SearchParameter;
import org.hl7.fhir.definitions.model.SearchParameter.SearchType;
import org.hl7.fhir.definitions.model.TypeRef;
import org.hl7.fhir.instance.formats.XmlComposer;
import org.hl7.fhir.instance.model.Contact.ContactSystem;
import org.hl7.fhir.instance.model.Enumeration;
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Id;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.BindingConformance;
import org.hl7.fhir.instance.model.Profile.ConstraintSeverity;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionBindingComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.Profile.ExtensionContext;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileMappingComponent;
import org.hl7.fhir.instance.model.Profile.PropertyRepresentation;
import org.hl7.fhir.instance.model.Profile.ResourceAggregationMode;
import org.hl7.fhir.instance.model.Profile.ResourceSlicingRules;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
//import org.hl7.fhir.instance.model.Factory;

public class ProfileGenerator {

  public enum GenerationMode { Element, Backbone, Resource  }

  private Definitions definitions;
  private Set<String> bindings = new HashSet<String>();


  public ProfileGenerator(Definitions definitions) {
    super();
    this.definitions = definitions;
  }

  public Profile generate(ProfileDefn profile, String html, GenerationMode mode) throws Exception {
    if (profile.getSource() != null)
      return profile.getSource();
    Profile p = new Profile();
    p.setName(Factory.newString_(profile.metadata("name")));
    p.setPublisher(Factory.newString_(profile.metadata("author.name")));
    if (profile.hasMetadata("author.reference"))
      p.getTelecom().add(Factory.newContact(ContactSystem.url, profile.metadata("author.reference")));
    //  <code> opt Zero+ Coding assist with indexing and finding</code>
    if (profile.hasMetadata("intention"))
      throw new Exception("profile intention is not supported any more ("+p.getName()+")");
    if (profile.hasMetadata("description"))
      p.setDescription(Factory.newString_(profile.metadata("description")));
    if (profile.hasMetadata("evidence"))
      throw new Exception("profile evidence is not supported any more ("+p.getName()+")");
    if (profile.hasMetadata("comments"))
      throw new Exception("profile comments is not supported any more ("+p.getName()+")");
    if (profile.hasMetadata("requirements"))
      p.setRequirementsSimple(profile.metadata("requirements"));

    if (profile.hasMetadata("date"))
      p.setDate(Factory.newDateTime(profile.metadata("date").substring(0, 10)));

    if (profile.hasMetadata("status")) 
      p.setStatusSimple(Profile.ResourceProfileStatus.fromCode(profile.metadata("status")));

    Set<String> containedSlices = new HashSet<String>();

    for (ResourceDefn resource : profile.getResources()) {
      Profile.ProfileStructureComponent c = new Profile.ProfileStructureComponent();
      p.getStructure().add(c);
      c.setPublishSimple(true); // todo: when should this be set to true?
      c.setType(Factory.newCode(resource.getRoot().getName()));
      // we don't profile URI when we generate in this mode - we are generating an actual statement, not a re-reference
      if (!"".equals(resource.getRoot().getProfileName()))
        c.setName(Factory.newString_(resource.getRoot().getProfileName()));
      // no purpose element here
      defineElement(profile, p, c, resource.getRoot(), resource.getName(), mode, containedSlices);
    }
    containedSlices.clear();
    for (ElementDefn elem : profile.getElements()) {
      Profile.ProfileStructureComponent c = new Profile.ProfileStructureComponent();
      p.getStructure().add(c);
      c.setType(Factory.newCode(elem.getName()));
      // we don't profile URI when we generate in this mode - we are generating an actual statement, not a re-reference
      if (!"".equals(elem.getProfileName()))
        c.setName(Factory.newString_(elem.getProfileName()));
      // no purpose element here
      defineElement(profile, p, c, elem, elem.getName(), mode, containedSlices);
    }

//    for (String bn : bindings) {
//      if (!"!".equals(bn)) {
//        BindingSpecification bs = definitions.getBindingByName(bn);
//        if (bs == null)
//          System.out.println("no binding found for "+bn);
//        else
//          p.getBinding().add(generateBinding(bs, p));
//      }
//    }

    for (ExtensionDefn ex : profile.getExtensions())
      p.getExtensionDefn().add(generateExtensionDefn(ex, p));

//    for (BindingSpecification b : profile.getBindings()) 
//      p.getBinding().add(generateBinding(b, p));
    XhtmlNode div = new XhtmlNode(NodeType.Element, "div");
    div.getChildNodes().add(new XhtmlParser().parseFragment(html));
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }

  private ElementDefinitionBindingComponent generateBinding(String bn, Profile p) throws Exception {
    BindingSpecification src = definitions.getBindingByName(bn);
    if (src == null)
      return null;
    
    ElementDefinitionBindingComponent dst = new Profile.ElementDefinitionBindingComponent();
    dst.setName(Factory.newString_(src.getName()));
    dst.setConformanceSimple(convert(src.getBindingStrength()));
    dst.setIsExtensibleSimple(src.getExtensibility() == BindingExtensibility.Extensible);
    if (src.getBinding() == Binding.Unbound)
      dst.setDescription(Factory.newString_(src.getDefinition()));
    else
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
      return Factory.makeResourceReference("http://hl7.org/fhir/"+src.getReference().substring(1));
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
    dst.getContext().add(Factory.newString_(src.getContext()));
    dst.setContextTypeSimple(convertContextType(src.getType()));

    ElementDefn dSrc = src.getDefinition();
    ElementDefinitionComponent dDst = new Profile.ElementDefinitionComponent();
    dst.setDefinition(dDst);

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
      TypeRefComponent type = new Profile.TypeRefComponent();
      type.setCode(Factory.newCode(t.summary()));
      dDst.getType().add(type);
    }
    for (String mu : ElementDefn.getAllMappingUris()) {
      if (dSrc.hasMapping(mu)) {
        addMapping(p, dDst, mu, dSrc.getMapping(mu));
      }
    }
    if (!Utilities.noString(dSrc.getBindingName()))
      dDst.setBinding(generateBinding(dSrc.getBindingName(), p));
    return dst;
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

  private Profile.ElementComponent defineElement(ProfileDefn pd, Profile p, Profile.ProfileStructureComponent c, ElementDefn e, String path, GenerationMode mode, Set<String> slices) throws Exception {
    Profile.ElementComponent ce = new Profile.ElementComponent();
    c.getElement().add(ce);
    ce.setPath(Factory.newString_(path));
    if (e.isXmlAttribute())
      ce.addRepresentationSimple(PropertyRepresentation.xmlAttr);
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
      ce.setName(Factory.newString_(e.getProfileName()));
    }
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


    // no purpose here
    ce.getDefinition().setMin(Factory.newInteger(e.getMinCardinality()));
    ce.getDefinition().setMax(Factory.newString_(e.getMaxCardinality() == null ? "*" : e.getMaxCardinality().toString()));
    if (e.typeCode().startsWith("@")) {
      ce.getDefinition().setNameReferenceSimple(e.typeCode().substring(1));
    } else {
      for (TypeRef t : e.getTypes()) {
        TypeRefComponent type = new Profile.TypeRefComponent();
        type.setCode(Factory.newCode(t.summaryFormal()));
        ce.getDefinition().getType().add(type);
      }
    }
    // ce.setConformance(getType(e.getConformance()));
    if (!"".equals(e.getCondition())) {
      Id cond = Factory.newId(e.getCondition());
      if (cond != null)
        ce.getDefinition().getCondition().add(cond);
    }
    // we don't know mustSupport here
    ce.getDefinition().setIsModifier(Factory.newBoolean(e.isModifier()));
    addMapping(p, ce.getDefinition(), "http://loinc.org", e.getMapping(ElementDefn.LOINC_MAPPING));
    addMapping(p, ce.getDefinition(), "http://snomed.info", e.getMapping(ElementDefn.SNOMED_MAPPING));
    addMapping(p, ce.getDefinition(), "http://hl7.org/v3", e.getMapping(ElementDefn.RIM_MAPPING));
    addMapping(p, ce.getDefinition(), "http://hl7.org/v3/cda", e.getMapping(ElementDefn.CDA_MAPPING));
    addMapping(p, ce.getDefinition(), "http://hl7.org/v2", e.getMapping(ElementDefn.v2_MAPPING));
    addMapping(p, ce.getDefinition(), "http://nema.org/dicom", e.getMapping(ElementDefn.DICOM_MAPPING));
    addMapping(p, ce.getDefinition(), "http://w3.org/vcard", e.getMapping(ElementDefn.vCard_MAPPING));
    addMapping(p, ce.getDefinition(), "http://www.ietf.org/rfc/rfc2445.txt", e.getMapping(ElementDefn.iCAL_MAPPING));
    addMapping(p, ce.getDefinition(), "http://www.omg.org/spec/ServD/1.0/", e.getMapping(ElementDefn.ServD_MAPPING));
    addMapping(p, ce.getDefinition(), "http://ihe.net/xds", e.getMapping(ElementDefn.XDS_MAPPING));
    addMapping(p, ce.getDefinition(), ElementDefn.PROV_MAPPING, e.getMapping(ElementDefn.PROV_MAPPING));

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

    Set<String> containedSlices = new HashSet<String>();
    if (e.getElementByName("extension") != null) {
      ElementComponent ex = createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("extension"));
      ex.setNameSimple("base extension");
      ex.setSlicing(new Profile.ElementSlicingComponent());
      ex.getSlicing().setDiscriminatorSimple("url");
      ex.getSlicing().setOrderedSimple(false);
      ex.getSlicing().setRulesSimple(ResourceSlicingRules.open);
      ex.setDefinition(null);
      c.getElement().add(ex);
      containedSlices.add("extension");
      for (ElementDefn child : e.getElements()) {
        if (child.getName().equals("extension")) {
          String t = child.getProfile();
          ElementComponent elem = defineElement(pd, p, c, child, path+"."+child.getName(), GenerationMode.Element, containedSlices);
          elem.getDefinition().getType().get(0).setProfileSimple(t);
          }
      }
    } else if (mode != GenerationMode.Element && e.getTypes().size() > 0) {
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("extension")));
    }
      
    if ((mode == GenerationMode.Resource) || (mode == GenerationMode.Backbone && e.getTypes().size() == 0)) {
      if (e.getElementByName("modifierExtension") != null) {
        ElementComponent ex = createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("modifierExtension"));
        ex.setNameSimple("base modifier extension");
        ex.setSlicing(new Profile.ElementSlicingComponent());
        ex.getSlicing().setDiscriminatorSimple("url");
        ex.getSlicing().setOrderedSimple(false);
        ex.getSlicing().setRulesSimple(ResourceSlicingRules.open);
        ex.setDefinition(null);
        c.getElement().add(ex);
        containedSlices.add("modifierExtension");
        for (ElementDefn child : e.getElements()) {
          if (child.getName().equals("modifierExtension")) {
            String t = child.getProfile();
            ElementComponent elem = defineElement(pd, p, c, child, path+"."+child.getName(), GenerationMode.Element, containedSlices);
            elem.getDefinition().getType().get(0).setProfileSimple(t);
          }
        }
      } else {
        c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("modifierExtension")));
      }
    }
    
    if (mode == GenerationMode.Resource) {
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("text")));
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("contained")));
    }
    for (ElementDefn child : e.getElements()) {
      if (!child.getName().equals("extension"))
        defineElement(pd, p, c, child, path+"."+child.getName(), mode != GenerationMode.Element ? GenerationMode.Backbone : GenerationMode.Element, containedSlices);
    }
    return ce;
  }

  private void addMapping(Profile p, ElementDefinitionComponent definition, String target, String map) {
    if (!Utilities.noString(map)) {
      String id = MappingsGenerator.idFor(target);
      if (!mappingExists(p, id)) {
        ProfileMappingComponent pm = new ProfileMappingComponent();
        p.getMapping().add(pm);
        pm.setIdentitySimple(id);
        pm.setUriSimple(target);
        pm.setNameSimple(MappingsGenerator.titleFor(target));
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
    if (!Utilities.noString(src.getProfile()))
      ce.getDefinition().getType().get(0).setProfile(Factory.newUri(src.getProfile()));
    // todo? conditions, constraints, binding, mapping
    ce.getDefinition().setIsModifierSimple(src.isModifier());
    return ce;
  }


}
