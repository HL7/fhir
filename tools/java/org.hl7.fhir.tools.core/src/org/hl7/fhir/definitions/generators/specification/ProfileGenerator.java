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
import org.hl7.fhir.instance.model.Factory;
import org.hl7.fhir.instance.model.Narrative;
import org.hl7.fhir.instance.model.Narrative.NarrativeStatus;
import org.hl7.fhir.instance.model.Profile;
import org.hl7.fhir.instance.model.Profile.BindingConformance;
import org.hl7.fhir.instance.model.Profile.ConstraintSeverity;
import org.hl7.fhir.instance.model.Profile.ElementComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionConstraintComponent;
import org.hl7.fhir.instance.model.Profile.ElementDefinitionMappingComponent;
import org.hl7.fhir.instance.model.Profile.ExtensionContext;
import org.hl7.fhir.instance.model.Profile.ProfileBindingComponent;
import org.hl7.fhir.instance.model.Profile.ProfileExtensionDefnComponent;
import org.hl7.fhir.instance.model.Profile.ProfileStructureSearchParamComponent;
import org.hl7.fhir.instance.model.Profile.SearchParamType;
import org.hl7.fhir.instance.model.Profile.TypeRefComponent;
import org.hl7.fhir.instance.model.Type;
import org.hl7.fhir.utilities.Utilities;
import org.hl7.fhir.utilities.xhtml.NodeType;
import org.hl7.fhir.utilities.xhtml.XhtmlNode;
import org.hl7.fhir.utilities.xhtml.XhtmlParser;
//import org.hl7.fhir.instance.model.Factory;

public class ProfileGenerator {

  private Definitions definitions;
  private Set<String> bindings = new HashSet<String>();


  public ProfileGenerator(Definitions definitions) {
    super();
    this.definitions = definitions;
  }

  public Profile generate(ProfileDefn profile, String html, boolean addBase) throws Exception {
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
    Profile.ProfileStatusComponent s = p.new ProfileStatusComponent();
    p.setStatus(s);
    if (profile.hasMetadata("date"))
      s.setDate(Factory.newDateTime(profile.metadata("date").substring(0, 10)));

    if (profile.hasMetadata("status")) 
      s.setCodeSimple(Profile.ResourceProfileStatus.fromCode(profile.metadata("status")));

    for (ResourceDefn resource : profile.getResources()) {
      Profile.ProfileStructureComponent c = p.new ProfileStructureComponent();
      p.getStructure().add(c);
      c.setType(Factory.newCode(resource.getRoot().getName()));
      // we don't profile URI when we generate in this mode - we are generating an actual statement, not a re-reference
      if (!"".equals(resource.getRoot().getProfileName()))
        c.setName(Factory.newString_(resource.getRoot().getProfileName()));
      // no purpose element here
      defineElement(p, c, resource.getRoot(), resource.getName(), addBase);

      for (SearchParameter i : resource.getSearchParams().values()) {
        c.getSearchParam().add(makeSearchParam(p, i));
      }
    }
    for (ElementDefn elem : profile.getElements()) {
      Profile.ProfileStructureComponent c = p.new ProfileStructureComponent();
      p.getStructure().add(c);
      c.setType(Factory.newCode(elem.getName()));
      // we don't profile URI when we generate in this mode - we are generating an actual statement, not a re-reference
      if (!"".equals(elem.getProfileName()))
        c.setName(Factory.newString_(elem.getProfileName()));
      // no purpose element here
      defineElement(p, c, elem, elem.getName(), addBase);
    }

    for (String bn : bindings) {
      if (!"!".equals(bn)) {
        BindingSpecification bs = definitions.getBindingByName(bn);
        if (bs == null)
          System.out.println("no binding found for "+bn);
        else
          p.getBinding().add(generateBinding(bs, p));
      }
    }

    for (ExtensionDefn ex : profile.getExtensions())
      p.getExtensionDefn().add(generateExtensionDefn(ex, p));
    for (BindingSpecification b : profile.getBindings()) 
      p.getBinding().add(generateBinding(b, p));
    XhtmlNode div = new XhtmlNode();
    div.setName("div");
    div.setNodeType(NodeType.Element);
    div.getChildNodes().add(new XhtmlParser().parseFragment(html));
    p.setText(new Narrative());
    p.getText().setStatusSimple(NarrativeStatus.generated);
    p.getText().setDiv(div);
    return p;
  }

  private ProfileStructureSearchParamComponent makeSearchParam(Profile p, SearchParameter i) {
    ProfileStructureSearchParamComponent result = p.new ProfileStructureSearchParamComponent();
    result.setName(Factory.newString_(i.getCode()));
    result.setTypeSimple(getSearchParamType(i.getType()));
    result.setDocumentation(Factory.newString_(i.getDescription()));    
    return result;
  }



  private SearchParamType getSearchParamType(SearchType type) {
    switch (type) {
    case integer: return SearchParamType.integer;
    case string: return SearchParamType.string;
    case text: return SearchParamType.text;
    case date: return SearchParamType.date;
    case reference: return SearchParamType.reference;
    case token: return SearchParamType.token;
    case composite: return SearchParamType.composite;
    }
    return null;
  }

  private ProfileBindingComponent generateBinding(BindingSpecification src, Profile p) throws Exception {
    ProfileBindingComponent dst = p.new ProfileBindingComponent();
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
        return Factory.makeResourceReference("ValueSet", "http://hl7.org/fhir/valueset/"+src.getReference().substring(1));
      else
        throw new Exception("not done yet");
    case ValueSet: 
      if (!Utilities.noString(src.getReference()))
        if (src.getReference().startsWith("http"))
          return Factory.makeResourceReference("ValueSet", src.getReference());
        else if (src.getReference().startsWith("valueset-"))
          return Factory.makeResourceReference("ValueSet", "http://hl7.org/fhir/vs/"+src.getReference().substring(9));
        else
          return Factory.makeResourceReference("ValueSet", "http://hl7.org/fhir/vs/"+src.getReference());
      else
        return null; // throw new Exception("not done yet");
    case Reference: return Factory.newUri(src.getReference());
    case Special: 
      return Factory.makeResourceReference("ValueSet", "http://hl7.org/fhir/"+src.getReference().substring(1));
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
    ProfileExtensionDefnComponent dst = p.new ProfileExtensionDefnComponent();
    dst.setCode(Factory.newCode(src.getCode()));
    dst.getContext().add(Factory.newString_(src.getContext()));
    dst.setContextTypeSimple(convertContextType(src.getType()));

    ElementDefn dSrc = src.getDefinition();
    ElementDefinitionComponent dDst = p.new ElementDefinitionComponent();
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
    dDst.setMustUnderstand(Factory.newBoolean(dSrc.isModifier()));
    // dDst.
    for (TypeRef t : dSrc.getTypes()) {
      TypeRefComponent type = p.new TypeRefComponent();
      type.setCode(Factory.newCode(t.summary()));
      dDst.getType().add(type);
    }
    if (dSrc.hasMapping(ElementDefn.RIM_MAPPING)) {
      ElementDefinitionMappingComponent m = p.new ElementDefinitionMappingComponent();
      m.setMap(Factory.newString_("RIM"));
      m.setTarget(Factory.newString_(dSrc.getMapping(ElementDefn.RIM_MAPPING)));
      dDst.getMapping().add(m);
    }
    dDst.setBinding(Factory.newString_(dSrc.getBindingName()));
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

  private void defineElement(Profile p, Profile.ProfileStructureComponent c, ElementDefn e, String path, boolean addBase) throws Exception {
    Profile.ElementComponent ce = p.new ElementComponent();
    c.getElement().add(ce);
    ce.setPath(Factory.newString_(path));
    if (!"".equals(e.getProfileName()))
      ce.setName(Factory.newString_(e.getProfileName()));
    ce.setDefinition(p.new ElementDefinitionComponent());
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
    for (TypeRef t : e.getTypes()) {
      TypeRefComponent type = p.new TypeRefComponent();
      type.setCode(Factory.newCode(t.summaryFormal()));
      ce.getDefinition().getType().add(type);
    }
    // ce.setConformance(getType(e.getConformance()));
    if (!"".equals(e.getCondition()))
      ce.getDefinition().getCondition().add(Factory.newId(e.getCondition()));
    // we don't know mustSupport here
    ce.getDefinition().setMustUnderstand(Factory.newBoolean(e.isModifier()));
    // todo: mappings

    for (String in : e.getInvariants().keySet()) {
      ElementDefinitionConstraintComponent con = p.new ElementDefinitionConstraintComponent();
      Invariant inv = e.getInvariants().get(in);
      con.setKeySimple(inv.getId());
      con.setNameSimple(inv.getName());
      con.setSeveritySimple(ConstraintSeverity.error);
      con.setHumanSimple(inv.getEnglish());
      con.setXpathSimple(inv.getXpath());
      ce.getDefinition().getConstraint().add(con);
    }
    // we don't have anything to say about constraints on resources

    if (!"".equals(e.getBindingName())) {
      ce.getDefinition().setBinding(Factory.newString_(e.getBindingName()));
      bindings.add(e.getBindingName());
    }

    if( e.hasAggregation() )
    {
      ce.setBundled(Factory.newBoolean(true));
      TypeRefComponent t = p.new TypeRefComponent();
      ce.getDefinition().getType().add(t);
      t.setProfile(Factory.newUri(e.getAggregation()));
    }

    if (addBase) {
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("extension")));
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("text")));
      c.getElement().add(createBaseDefinition(p, path, definitions.getBaseResource().getRoot().getElementByName("contained")));
    }
    for (ElementDefn child : e.getElements()) {
      defineElement(p, c, child, path+"."+child.getName(), false);
    }
  }

  private ElementComponent createBaseDefinition(Profile p, String path, ElementDefn src) throws URISyntaxException {
    ElementComponent ce = p.new ElementComponent();
    ce.setPath(Factory.newString_(path+"."+src.getName()));
    ce.setDefinition(p.new ElementDefinitionComponent());
    ce.getDefinition().setShort(Factory.newString_(src.getShortDefn()));
    ce.getDefinition().setFormal(Factory.newString_(src.getDefinition()));
    ce.getDefinition().setComments(Factory.newString_(src.getComments()));
    ce.getDefinition().setRequirements(Factory.newString_(src.getRequirements()));
    for (String a : src.getAliases())
      ce.getDefinition().getSynonym().add(Factory.newString_(a));
    ce.getDefinition().setMin(Factory.newInteger(src.getMinCardinality()));
    ce.getDefinition().setMax(Factory.newString_(src.getMaxCardinality() == null ? "*" : src.getMaxCardinality().toString()));
    ce.getDefinition().getType().add(p.new TypeRefComponent());
    ce.getDefinition().getType().get(0).setCode(Factory.newCode(src.typeCode()));
    // this one should never be used
    if (!Utilities.noString(src.getProfile()))
      ce.getDefinition().getType().get(0).setProfile(Factory.newUri(src.getProfile()));
    // todo? conditions, constraints, binding, mapping
    if (src.isModifier())
      ce.getDefinition().setMustUnderstand(Factory.newBoolean(true));
    return ce;
  }


}
