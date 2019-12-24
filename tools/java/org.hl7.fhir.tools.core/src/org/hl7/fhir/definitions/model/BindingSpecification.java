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
import java.util.List;
import java.util.Map;

import org.fhir.ucum.Utilities;
import org.hl7.fhir.definitions.generators.specification.ToolResourceUtilities;
import org.hl7.fhir.r5.context.MetadataResourceManager;
import org.hl7.fhir.r5.model.CodeSystem;
import org.hl7.fhir.r5.model.CodeSystem.ConceptDefinitionComponent;
import org.hl7.fhir.r5.model.Enumerations.BindingStrength;
import org.hl7.fhir.r5.model.Enumerations.PublicationStatus;
import org.hl7.fhir.r5.model.ValueSet;
import org.hl7.fhir.r5.model.ValueSet.ConceptReferenceComponent;
import org.hl7.fhir.r5.model.ValueSet.ConceptSetComponent;
import org.hl7.fhir.r5.terminologies.CodeSystemUtilities;
import org.hl7.fhir.r5.terminologies.ValueSetUtilities;
import org.hl7.fhir.r5.utils.ToolingExtensions;

/**
 * A concept domain - a use of terminology in FHIR.
 * A concept domain has a name, a definition, and information about what is in it (bindingType and binding/details, and maybe a list of defined codes) 
 * 
 * Technically, a concept domain is a facade on the full model in the core principles
 * 
 * @author Grahame
 *
 */
public class BindingSpecification {
  
  public static final String DEFAULT_OID_CS = "2.16.840.1.113883.4.642.1.";
  public static final String DEFAULT_OID_VS = "2.16.840.1.113883.4.642.3.";
  
  public enum BindingMethod {
    Unbound,
    CodeList, 
    ValueSet,
    Special
  }
  
  // for common bindings - make sure binding doesn't cross code / Coding boundary
  public enum ElementType {
    Unknown,
    Simple,
    Complex
  }

  // use tracking
  private ElementType elementType = ElementType.Unknown;
  private String usageContext;
  private List<String> useContexts = new ArrayList<String>(); // slated for removal
  private BindingMethod binding;
  private String source; // for useful error messages during build
  private String v2Map;
  private String v3Map;
  private boolean shared;

  // in ElementDefinition.binding 
  private String name;
  private BindingStrength strength;
  private String description;
  private String reference;
  private ValueSet valueSet;
  private String maxReference;
  private ValueSet maxValueSet;
    
  // to move into valueset 
	private String definition;
  private String uri; // used as the official value set identifier if provided, else one will be synthesized. For when code list is actually a value set defined elsewhere
  private String webSite;
  private String email;
  private String copyright;
//  private List<DefinedCode> codes = new ArrayList<DefinedCode>();
  private String csOid;
  private String vsOid;
//  private List<DefinedCode> childCodes;
  private PublicationStatus status;
  private List<DefinedCode> allCodes;
  

  
  
  // analysis during run time

  
  public BindingSpecification(String usageContext, String name, boolean shared) {
    super();
    this.usageContext = usageContext;
    this.name = name; 
    this.shared = shared;
  }

  public String getUsageContext() {
    return usageContext;
  }


  public String getName() {
    return name;
  }

//  public void setName(String name) {
//    this.name = name;
//  }

  public String getDefinition() {
    return definition;
  }

  public void setDefinition(String definition) {
    this.definition = definition;
  }

  public BindingMethod getBinding() {
    return binding;
  }

  public void setBindingMethod(BindingMethod binding) {
    this.binding = binding;
  }

  public String getReference() {
    return reference;
  }

  public BindingSpecification setReference(String reference) {
    this.reference = reference;
    return this;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

//  public List<DefinedCode> getCodes() {
//    return codes;
//  }
//	
	public boolean hasReference() {
	  return !(reference == null || reference.equals(""));
	}

  public String getSource() {
    return source;
  }

  public void setSource(String source) {
    this.source = source;
  }
  
  public BindingStrength getStrength() {
    return strength;
  }

  public void setStrength(BindingStrength strength) {
    this.strength = strength;
  }

//  public static BindingSpecification getBindingFromList(Map<String, BindingSpecification> conceptDomains, String conceptDomain) {
//	  for (BindingSpecification cd : conceptDomains.values())
//		  if (cd.name.equals(conceptDomain))
//			  return cd;
//	
//	  return null;
//  }

  public List<String> getUseContexts() {
    return useContexts;
  }

  public void setUseContexts(List<String> useContexts) {
    this.useContexts = useContexts;
  }

//  public boolean hasExternalCodes() {
//    boolean external = false;
//    for (DefinedCode c : codes)
//      if (!Utilities.noString(c.getSystem()))
//        external = true;
//    return external;
//  }
//
//  public boolean hasInternalCodes() {
//    boolean internal = false;
//    for (DefinedCode c : codes)
//      if (Utilities.noString(c.getSystem()))
//        internal = true;
//    return internal;
//  }
//
//  
//  public List<String> getVSSources() {
//    List<String> vslist = new ArrayList<String>();
//    boolean internal = false;
//    for (DefinedCode c : codes) {
//      if (Utilities.noString(c.getSystem())) {
//        internal = true;
//      } else {
//        if (!vslist.contains(c.getSystem()))
//          vslist.add(c.getSystem());
//      }
//    }
//    if (internal)
//      vslist.add(0, "");
//    return vslist;
//  }

  
//  public List<DefinedCode> getChildCodes() throws Exception {
//    if (childCodes == null) {
//      childCodes = new ArrayList<DefinedCode>();
//      for (DefinedCode c : codes) {
//        if (c.hasParent()) { 
//          DefinedCode p = getCode(c.getParent());
//          if (p == null)
//            throw new Exception("unable to find parent Code '"+c.getParent()+"' for code '"+c.getCode()+"'");
//          p.getChildCodes().add(c);
//        } else
//          childCodes.add(c);
//      }
//    }
//    return childCodes;
//  }
//
//  public DefinedCode getCode(String code) {
//    for (DefinedCode c : codes) {
//      if (code.equals(c.getCode()))
//        return c;
//      if (code.equals("#"+c.getId()))
//        return c;
//    }
//    return null;
//  }
//
//  public boolean isHeirachical() {
//    boolean hasParent = false;
//    for (DefinedCode c : getCodes()) {
//      hasParent = hasParent || c.hasParent();
//    }
//    return hasParent;
//  }

  public ElementType getElementType() {
    return elementType;
  }

  public void setElementType(ElementType elementType) {
    this.elementType = elementType;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  public String getWebSite() {
    return webSite;
  }

  public void setWebSite(String webSite) {
    this.webSite = webSite;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCopyright() {
    return copyright;
  }

  public void setCopyright(String copyright) {
    this.copyright = copyright;
  }

  public String getV2Map() {
    return v2Map;
  }

  public void setV2Map(String v2Map) {
    this.v2Map = v2Map;
  }

  public String getV3Map() {
    return v3Map;
  }

  public void setV3Map(String v3Map) {
    this.v3Map = v3Map;
  }

  public String getCsOid() {
    return csOid;
  }

  public void setCsOid(String csOid) {
    this.csOid = csOid;
  }

  public String getVsOid() {
    return vsOid;
  }

  public void setVsOid(String vsOid) {
    this.vsOid = vsOid;
    if (!Utilities.noString(vsOid) && valueSet != null) 
      ValueSetUtilities.setOID(valueSet, vsOid);
  }

  public PublicationStatus getStatus() {
    return status;
  }

  public void setStatus(PublicationStatus status) {
    this.status = status;
  }

  public ValueSet getValueSet() {
    return valueSet;
  }

  public void setValueSet(ValueSet valueSet) {
    this.valueSet = valueSet;
    ToolResourceUtilities.updateUsage(valueSet, usageContext);
    if (!Utilities.noString(vsOid)) 
      ValueSetUtilities.setOID(valueSet, vsOid);
  }

  public List<DefinedCode> getAllCodes(MetadataResourceManager<CodeSystem> codeSystems, MetadataResourceManager<ValueSet> valueSets, boolean wantComplete) throws Exception {
    if (allCodes == null || allCodes.size() == 0 || wantComplete) {
      allCodes = new ArrayList<DefinedCode>();
      if (valueSet != null) {
        valueSet.setUserData(ToolResourceUtilities.NAME_VS_USE_MARKER, true);
        getAllCodesForValueSet(codeSystems, valueSets, wantComplete, valueSet);
      }   
    }
    return allCodes;
  }

  private void getAllCodesForValueSet(MetadataResourceManager<CodeSystem> codeSystems, MetadataResourceManager<ValueSet> valueSets, boolean wantComplete, ValueSet vs) throws Exception {
    if (vs.hasCompose()) {
      for (ConceptSetComponent cc : vs.getCompose().getInclude()) {
        if (cc.hasFilter() && wantComplete)
          throw new Exception("Filters are not supported in this context (getting all codes for code generation");
        if (cc.hasValueSet() && wantComplete)
          throw new Exception("Value Sets are not supported in this context (getting all codes for code generation");
        if (!cc.hasConcept()) {
          if (codeSystems != null) {
            CodeSystem cs1 = codeSystems.get(cc.getSystem());
            if (cs1 != null) {
              getAllCodesForCodeSystem(cs1);
            } else if (wantComplete)
              throw new Exception("Unable to resolve code system "+cc.getSystem());
          } else if (wantComplete)
            throw new Exception("Unable to expand value set "); 
        } else 
          for (ConceptReferenceComponent c : cc.getConcept())
            processCode(c, cc.getSystem());
      }
    }
  }

  private void getAllCodesForCodeSystem(CodeSystem cs) throws Exception {
    for (ConceptDefinitionComponent c : cs.getConcept())
      processCode(cs, c, cs.getUrl(), null);
  }
  
  private void processCode(ConceptReferenceComponent c, String system) {
    DefinedCode code = new DefinedCode();
    code.setCode(c.getCode());
    code.setDisplay(c.getDisplay());
    code.setSystem(system);
    allCodes.add(code);
  }

  private void processCode(CodeSystem cs, ConceptDefinitionComponent c, String system, String parent) {
    DefinedCode code = new DefinedCode();
    code.setCode(c.getCode());
    code.setDisplay(c.getDisplay());
    code.setComment(ToolingExtensions.getCSComment(c));
    code.setDefinition(c.getDefinition());
    code.setParent(parent);
    code.setSystem(system);
    code.setAbstract(CodeSystemUtilities.isNotSelectable(cs, c));
    allCodes.add(code);
    for (ConceptDefinitionComponent cc : c.getConcept())
      processCode(cs, cc, system, c.getCode());
  }

  public boolean isShared() {
    return shared;
  }

  public String getMaxReference() {
    return maxReference;
  }

  public void setMaxReference(String maxReference) {
    this.maxReference = maxReference;
  }

  public ValueSet getMaxValueSet() {
    return maxValueSet;
  }

  public void setMaxValueSet(ValueSet maxValueSet) {
    this.maxValueSet = maxValueSet;
  }

  public boolean hasMax() {
    return maxValueSet != null || maxReference != null;
  }  
    
}
