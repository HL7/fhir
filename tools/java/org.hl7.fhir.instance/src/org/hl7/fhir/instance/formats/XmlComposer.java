package org.hl7.fhir.instance.formats;

/*
  Copyright (c) 2011+, HL7, Inc.
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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.utilities.Utilities;

public class XmlComposer extends XmlComposerBase {

  private void composeElementElements(Element element) throws Exception {
    for (Extension e : element.getExtension()) {
      composeExtension("extension", e);
    }
  }

  private void composeBackboneElements(BackboneElement element) throws Exception {
    composeElementElements(element);
    for (Extension e : element.getModifierExtension()) {
      composeExtension("modifierExtension", e);
    }
  }

  private <E extends Enum<E>> void composeEnumeration(String name, Enumeration<E> value, EnumFactory e) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", e.toCode(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeInteger(String name, IntegerType value) throws Exception {
    if (value != null) {
      composeElementAttributes(value);
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDateTime(String name, DateTimeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeCode(String name, CodeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDate(String name, DateType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDecimal(String name, DecimalType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeUri(String name, UriType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeId(String name, IdType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeBase64Binary(String name, Base64BinaryType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeTime(String name, TimeType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeOid(String name, OidType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeString(String name, StringType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeBoolean(String name, BooleanType value) throws Exception {
    if (value != null) {
      composeElementAttributes(value);
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeUuid(String name, UuidType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeInstant(String name, InstantType value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeExtension(String name, Extension element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      if (element.getUrlElement() != null)
        xml.attribute("url", element.getUrlElement().getValue());
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeType("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Narrative.NarrativeStatusEnumFactory());
      composeXhtml("div", element.getDiv());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDateTime("start", element.getStartElement());
      composeDateTime("end", element.getEndElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("system", element.getSystemElement());
      composeString("version", element.getVersionElement());
      composeCode("code", element.getCodeElement());
      composeString("display", element.getDisplayElement());
      composeBoolean("primary", element.getPrimaryElement());
      composeReference("valueSet", element.getValueSet());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRange(String name, Range element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuantity(String name, Quantity element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Quantity.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("contentType", element.getContentTypeElement());
      composeCode("language", element.getLanguageElement());
      composeBase64Binary("data", element.getDataElement());
      composeUri("url", element.getUrlElement());
      composeInteger("size", element.getSizeElement());
      composeBase64Binary("hash", element.getHashElement());
      composeString("title", element.getTitleElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRatio(String name, Ratio element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeQuantity("numerator", element.getNumerator());
      composeQuantity("denominator", element.getDenominator());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSampledData(String name, SampledData element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeQuantity("origin", element.getOrigin());
      composeDecimal("period", element.getPeriodElement());
      composeDecimal("factor", element.getFactorElement());
      composeDecimal("lowerLimit", element.getLowerLimitElement());
      composeDecimal("upperLimit", element.getUpperLimitElement());
      composeInteger("dimensions", element.getDimensionsElement());
      composeString("data", element.getDataElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeReference(String name, Reference element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("reference", element.getReferenceElement());
      composeString("display", element.getDisplayElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCodeableConcept(String name, CodeableConcept element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Coding e : element.getCoding()) 
        composeCoding("coding", e);
      composeString("text", element.getTextElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new Identifier.IdentifierUseEnumFactory());
      composeString("label", element.getLabelElement());
      composeUri("system", element.getSystemElement());
      composeString("value", element.getValueElement());
      composePeriod("period", element.getPeriod());
      composeReference("assigner", element.getAssigner());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Age.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Count.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Money.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Distance.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValueElement());
      if (element.getComparatorElement() != null)
        composeEnumeration("comparator", element.getComparatorElement(), new Duration.QuantityComparatorEnumFactory());
      composeString("units", element.getUnitsElement());
      composeUri("system", element.getSystemElement());
      composeCode("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinition(String name, ElementDefinition element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("path", element.getPathElement());
        for (Enumeration<ElementDefinition.PropertyRepresentation> e : element.getRepresentation()) 
          composeEnumeration("representation", e, new ElementDefinition.PropertyRepresentationEnumFactory());
      composeString("name", element.getNameElement());
      composeElementDefinitionElementDefinitionSlicingComponent("slicing", element.getSlicing());
      composeString("short", element.getShortElement());
      composeString("formal", element.getFormalElement());
      composeString("comments", element.getCommentsElement());
      composeString("requirements", element.getRequirementsElement());
      for (StringType e : element.getSynonym()) 
        composeString("synonym", e);
      composeInteger("min", element.getMinElement());
      composeString("max", element.getMaxElement());
      for (ElementDefinition.TypeRefComponent e : element.getType()) 
        composeElementDefinitionTypeRefComponent("type", e);
      composeString("nameReference", element.getNameReferenceElement());
      composeType("defaultValue", element.getDefaultValue());
      composeString("meaningWhenMissing", element.getMeaningWhenMissingElement());
      composeType("fixed", element.getFixed());
      composeType("pattern", element.getPattern());
      composeType("example", element.getExample());
      composeInteger("maxLength", element.getMaxLengthElement());
      for (IdType e : element.getCondition()) 
        composeId("condition", e);
      for (ElementDefinition.ElementDefinitionConstraintComponent e : element.getConstraint()) 
        composeElementDefinitionElementDefinitionConstraintComponent("constraint", e);
      composeBoolean("mustSupport", element.getMustSupportElement());
      composeBoolean("isModifier", element.getIsModifierElement());
      composeBoolean("isSummary", element.getIsSummaryElement());
      composeElementDefinitionElementDefinitionBindingComponent("binding", element.getBinding());
      for (ElementDefinition.ElementDefinitionMappingComponent e : element.getMapping()) 
        composeElementDefinitionElementDefinitionMappingComponent("mapping", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinitionElementDefinitionSlicingComponent(String name, ElementDefinition.ElementDefinitionSlicingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (IdType e : element.getDiscriminator()) 
        composeId("discriminator", e);
      composeString("description", element.getDescriptionElement());
      composeBoolean("ordered", element.getOrderedElement());
      if (element.getRulesElement() != null)
        composeEnumeration("rules", element.getRulesElement(), new ElementDefinition.ResourceSlicingRulesEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinitionTypeRefComponent(String name, ElementDefinition.TypeRefComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCodeElement());
      composeUri("profile", element.getProfileElement());
        for (Enumeration<ElementDefinition.ResourceAggregationMode> e : element.getAggregation()) 
          composeEnumeration("aggregation", e, new ElementDefinition.ResourceAggregationModeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinitionElementDefinitionConstraintComponent(String name, ElementDefinition.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeId("key", element.getKeyElement());
      composeString("name", element.getNameElement());
      if (element.getSeverityElement() != null)
        composeEnumeration("severity", element.getSeverityElement(), new ElementDefinition.ConstraintSeverityEnumFactory());
      composeString("human", element.getHumanElement());
      composeString("xpath", element.getXpathElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinitionElementDefinitionBindingComponent(String name, ElementDefinition.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getNameElement());
      composeBoolean("isExtensible", element.getIsExtensibleElement());
      if (element.getConformanceElement() != null)
        composeEnumeration("conformance", element.getConformanceElement(), new ElementDefinition.BindingConformanceEnumFactory());
      composeString("description", element.getDescriptionElement());
      composeType("reference", element.getReference());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeElementDefinitionElementDefinitionMappingComponent(String name, ElementDefinition.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeId("identity", element.getIdentityElement());
      composeString("map", element.getMapElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeTiming(String name, Timing element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Period e : element.getEvent()) 
        composePeriod("event", e);
      composeTimingTimingRepeatComponent("repeat", element.getRepeat());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeTimingTimingRepeatComponent(String name, Timing.TimingRepeatComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("frequency", element.getFrequencyElement());
      if (element.getWhenElement() != null)
        composeEnumeration("when", element.getWhenElement(), new Timing.EventTimingEnumFactory());
      composeDecimal("duration", element.getDurationElement());
      if (element.getUnitsElement() != null)
        composeEnumeration("units", element.getUnitsElement(), new Timing.UnitsOfTimeEnumFactory());
      composeInteger("count", element.getCountElement());
      composeDateTime("end", element.getEndElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new Address.AddressUseEnumFactory());
      composeString("text", element.getTextElement());
      for (StringType e : element.getLine()) 
        composeString("line", e);
      composeString("city", element.getCityElement());
      composeString("state", element.getStateElement());
      composeString("postalCode", element.getPostalCodeElement());
      composeString("country", element.getCountryElement());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new HumanName.NameUseEnumFactory());
      composeString("text", element.getTextElement());
      for (StringType e : element.getFamily()) 
        composeString("family", e);
      for (StringType e : element.getGiven()) 
        composeString("given", e);
      for (StringType e : element.getPrefix()) 
        composeString("prefix", e);
      for (StringType e : element.getSuffix()) 
        composeString("suffix", e);
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContactPoint(String name, ContactPoint element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getSystemElement() != null)
        composeEnumeration("system", element.getSystemElement(), new ContactPoint.ContactPointSystemEnumFactory());
      composeString("value", element.getValueElement());
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new ContactPoint.ContactPointUseEnumFactory());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeParameters(String name, Parameters element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Parameters.ParametersParameterComponent e : element.getParameter()) 
        composeParametersParametersParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeParametersParametersParameterComponent(String name, Parameters.ParametersParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeType("value", element.getValue());
      if (element.getResource() != null) {
        xml.open(FHIR_NS, "resource");
        composeResource(element.getResource());
        xml.close(FHIR_NS, "resource");
      }
      xml.close(FHIR_NS, name);
    }
  }

  private void composeResourceAttributes(Resource element) throws Exception {
  }

  private void composeResourceElements(Resource element) throws Exception {
      composeId("id", element.getIdElement());
      composeResourceResourceMetaComponent("meta", element.getMeta());
      composeUri("implicitRules", element.getImplicitRulesElement());
      composeCode("language", element.getLanguageElement());
  }

  private void composeResourceResourceMetaComponent(String name, Resource.ResourceMetaComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("versionId", element.getVersionIdElement());
      composeInstant("lastUpdated", element.getLastUpdatedElement());
      for (UriType e : element.getProfile()) 
        composeUri("profile", e);
      for (Coding e : element.getSecurity()) 
        composeCoding("security", e);
      for (Coding e : element.getTag()) 
        composeCoding("tag", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDomainResourceAttributes(DomainResource element) throws Exception {
    composeResourceAttributes(element);
  }

  private void composeDomainResourceElements(DomainResource element) throws Exception {
    composeResourceElements(element);
      composeNarrative("text", element.getText());
      for (Resource e : element.getContained()) 
      {
        xml.open(FHIR_NS, "contained");
        composeResource(e);
        xml.close(FHIR_NS, "contained");
      }
      for (Extension e : element.getExtension()) 
        composeExtension("extension", e);
      for (Extension e : element.getModifierExtension()) 
        composeExtension("modifierExtension", e);
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Alert.AlertStatusEnumFactory());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeString("note", element.getNoteElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("recordedDate", element.getRecordedDateElement());
      composeReference("recorder", element.getRecorder());
      composeReference("subject", element.getSubject());
      composeCodeableConcept("substance", element.getSubstance());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new AllergyIntolerance.AllergyIntoleranceStatusEnumFactory());
      if (element.getCriticalityElement() != null)
        composeEnumeration("criticality", element.getCriticalityElement(), new AllergyIntolerance.AllergyIntoleranceCriticalityEnumFactory());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new AllergyIntolerance.AllergyIntoleranceTypeEnumFactory());
      if (element.getCategoryElement() != null)
        composeEnumeration("category", element.getCategoryElement(), new AllergyIntolerance.AllergyIntoleranceCategoryEnumFactory());
      composeDateTime("lastOccurence", element.getLastOccurenceElement());
      composeString("comment", element.getCommentElement());
      for (AllergyIntolerance.AllergyIntoleranceEventComponent e : element.getEvent()) 
        composeAllergyIntoleranceAllergyIntoleranceEventComponent("event", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAllergyIntoleranceAllergyIntoleranceEventComponent(String name, AllergyIntolerance.AllergyIntoleranceEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("substance", element.getSubstance());
      if (element.getCertaintyElement() != null)
        composeEnumeration("certainty", element.getCertaintyElement(), new AllergyIntolerance.ReactionEventCertaintyEnumFactory());
      for (CodeableConcept e : element.getManifestation()) 
        composeCodeableConcept("manifestation", e);
      composeString("description", element.getDescriptionElement());
      composeDateTime("onset", element.getOnsetElement());
      composeDuration("duration", element.getDuration());
      if (element.getSeverityElement() != null)
        composeEnumeration("severity", element.getSeverityElement(), new AllergyIntolerance.ReactionEventSeverityEnumFactory());
      composeCodeableConcept("exposureRoute", element.getExposureRoute());
      composeString("comment", element.getCommentElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAppointment(String name, Appointment element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeInteger("priority", element.getPriorityElement());
      composeCode("status", element.getStatusElement());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      composeString("description", element.getDescriptionElement());
      composeInstant("start", element.getStartElement());
      composeInstant("end", element.getEndElement());
      for (Reference e : element.getSlot()) 
        composeReference("slot", e);
      composeReference("location", element.getLocation());
      composeString("comment", element.getCommentElement());
      composeReference("order", element.getOrder());
      for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
        composeAppointmentAppointmentParticipantComponent("participant", e);
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTime("lastModified", element.getLastModifiedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAppointmentAppointmentParticipantComponent(String name, Appointment.AppointmentParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeReference("actor", element.getActor());
      if (element.getRequiredElement() != null)
        composeEnumeration("required", element.getRequiredElement(), new Appointment.ParticipantrequiredEnumFactory());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Appointment.ParticipationstatusEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAppointmentResponse(String name, AppointmentResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("appointment", element.getAppointment());
      for (CodeableConcept e : element.getParticipantType()) 
        composeCodeableConcept("participantType", e);
      for (Reference e : element.getIndividual()) 
        composeReference("individual", e);
      if (element.getParticipantStatusElement() != null)
        composeEnumeration("participantStatus", element.getParticipantStatusElement(), new AppointmentResponse.ParticipantstatusEnumFactory());
      composeString("comment", element.getCommentElement());
      composeInstant("start", element.getStartElement());
      composeInstant("end", element.getEndElement());
      composeReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTime("lastModified", element.getLastModifiedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAvailability(String name, Availability element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeReference("actor", element.getActor());
      composePeriod("planningHorizon", element.getPlanningHorizon());
      composeString("comment", element.getCommentElement());
      composeDateTime("lastModified", element.getLastModifiedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBasic(String name, Basic element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDate("created", element.getCreatedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBinary(String name, Binary element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCode("contentType", element.getContentTypeElement());
      composeBase64Binary("content", element.getContentElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBundle(String name, Bundle element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Bundle.BundleTypeEnumFactory());
      composeUri("base", element.getBaseElement());
      composeInteger("total", element.getTotalElement());
      for (Bundle.BundleLinkComponent e : element.getLink()) 
        composeBundleBundleLinkComponent("link", e);
      for (Bundle.BundleEntryComponent e : element.getEntry()) 
        composeBundleBundleEntryComponent("entry", e);
      composeBase64Binary("signature", element.getSignatureElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBundleBundleLinkComponent(String name, Bundle.BundleLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("relation", element.getRelationElement());
      composeUri("url", element.getUrlElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBundleBundleEntryComponent(String name, Bundle.BundleEntryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("base", element.getBaseElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Bundle.BundleEntryStatusEnumFactory());
      composeUri("search", element.getSearchElement());
      composeDecimal("score", element.getScoreElement());
      composeBundleBundleEntryDeletedComponent("deleted", element.getDeleted());
      if (element.getResource() != null) {
        xml.open(FHIR_NS, "resource");
        composeResource(element.getResource());
        xml.close(FHIR_NS, "resource");
      }
      xml.close(FHIR_NS, name);
    }
  }

  private void composeBundleBundleEntryDeletedComponent(String name, Bundle.BundleEntryDeletedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("type", element.getTypeElement());
      composeId("id", element.getIdElement());
      composeId("versionId", element.getVersionIdElement());
      composeInstant("instant", element.getInstantElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("patient", element.getPatient());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new CarePlan.CarePlanStatusEnumFactory());
      composePeriod("period", element.getPeriod());
      composeDateTime("modified", element.getModifiedElement());
      for (Reference e : element.getConcern()) 
        composeReference("concern", e);
      for (CarePlan.CarePlanParticipantComponent e : element.getParticipant()) 
        composeCarePlanCarePlanParticipantComponent("participant", e);
      for (CarePlan.CarePlanGoalComponent e : element.getGoal()) 
        composeCarePlanCarePlanGoalComponent("goal", e);
      for (CarePlan.CarePlanActivityComponent e : element.getActivity()) 
        composeCarePlanCarePlanActivityComponent("activity", e);
      composeString("notes", element.getNotesElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("role", element.getRole());
      composeReference("member", element.getMember());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("description", element.getDescriptionElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new CarePlan.CarePlanGoalStatusEnumFactory());
      composeString("notes", element.getNotesElement());
      for (Reference e : element.getConcern()) 
        composeReference("concern", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (UriType e : element.getGoal()) 
        composeUri("goal", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new CarePlan.CarePlanActivityStatusEnumFactory());
      composeBoolean("prohibited", element.getProhibitedElement());
      for (Reference e : element.getActionResulting()) 
        composeReference("actionResulting", e);
      composeString("notes", element.getNotesElement());
      composeReference("detail", element.getDetail());
      composeCarePlanCarePlanActivitySimpleComponent("simple", element.getSimple());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanActivitySimpleComponent(String name, CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getCategoryElement() != null)
        composeEnumeration("category", element.getCategoryElement(), new CarePlan.CarePlanActivityCategoryEnumFactory());
      composeCodeableConcept("code", element.getCode());
      composeType("scheduled", element.getScheduled());
      composeReference("location", element.getLocation());
      for (Reference e : element.getPerformer()) 
        composeReference("performer", e);
      composeReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeString("details", element.getDetailsElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponse(String name, ClaimResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("request", element.getRequest());
      for (Identifier e : element.getRequestIdentifier()) 
        composeIdentifier("requestIdentifier", e);
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDate("date", element.getDateElement());
      composeReference("organization", element.getOrganization());
      composeReference("requestProvider", element.getRequestProvider());
      composeReference("requestOrganization", element.getRequestOrganization());
      if (element.getOutcomeElement() != null)
        composeEnumeration("outcome", element.getOutcomeElement(), new ClaimResponse.RSLinkEnumFactory());
      composeString("disposition", element.getDispositionElement());
      composeCoding("payeeType", element.getPayeeType());
      for (ClaimResponse.ItemsComponent e : element.getItem()) 
        composeClaimResponseItemsComponent("item", e);
      for (ClaimResponse.AddedItemComponent e : element.getAdditem()) 
        composeClaimResponseAddedItemComponent("additem", e);
      for (ClaimResponse.ErrorsComponent e : element.getError()) 
        composeClaimResponseErrorsComponent("error", e);
      composeMoney("totalCost", element.getTotalCost());
      composeMoney("unallocDeductable", element.getUnallocDeductable());
      composeMoney("totalBenefit", element.getTotalBenefit());
      composeMoney("paymentAdjustment", element.getPaymentAdjustment());
      composeCoding("paymentAdjustmentReason", element.getPaymentAdjustmentReason());
      composeDate("paymentDate", element.getPaymentDateElement());
      composeMoney("paymentAmount", element.getPaymentAmount());
      composeIdentifier("paymentRef", element.getPaymentRef());
      composeCoding("reserved", element.getReserved());
      composeCoding("form", element.getForm());
      for (ClaimResponse.NotesComponent e : element.getNote()) 
        composeClaimResponseNotesComponent("note", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseItemsComponent(String name, ClaimResponse.ItemsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequenceLinkId", element.getSequenceLinkIdElement());
      for (IntegerType e : element.getNoteNumber()) 
        composeInteger("noteNumber", e);
      for (ClaimResponse.ItemAdjudicationComponent e : element.getAdjudication()) 
        composeClaimResponseItemAdjudicationComponent("adjudication", e);
      for (ClaimResponse.ItemDetailComponent e : element.getDetail()) 
        composeClaimResponseItemDetailComponent("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseItemAdjudicationComponent(String name, ClaimResponse.ItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimal("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseItemDetailComponent(String name, ClaimResponse.ItemDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequenceLinkId", element.getSequenceLinkIdElement());
      for (ClaimResponse.DetailAdjudicationComponent e : element.getAdjudication()) 
        composeClaimResponseDetailAdjudicationComponent("adjudication", e);
      for (ClaimResponse.ItemSubdetailComponent e : element.getSubdetail()) 
        composeClaimResponseItemSubdetailComponent("subdetail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseDetailAdjudicationComponent(String name, ClaimResponse.DetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimal("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseItemSubdetailComponent(String name, ClaimResponse.ItemSubdetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequenceLinkId", element.getSequenceLinkIdElement());
      for (ClaimResponse.SubdetailAdjudicationComponent e : element.getAdjudication()) 
        composeClaimResponseSubdetailAdjudicationComponent("adjudication", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseSubdetailAdjudicationComponent(String name, ClaimResponse.SubdetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimal("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseAddedItemComponent(String name, ClaimResponse.AddedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (IntegerType e : element.getSequenceLinkId()) 
        composeInteger("sequenceLinkId", e);
      composeCoding("service", element.getService());
      composeMoney("fee", element.getFee());
      for (IntegerType e : element.getNoteNumberLinkId()) 
        composeInteger("noteNumberLinkId", e);
      for (ClaimResponse.AddedItemAdjudicationComponent e : element.getAdjudication()) 
        composeClaimResponseAddedItemAdjudicationComponent("adjudication", e);
      for (ClaimResponse.AddedItemsDetailComponent e : element.getDetail()) 
        composeClaimResponseAddedItemsDetailComponent("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseAddedItemAdjudicationComponent(String name, ClaimResponse.AddedItemAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimal("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseAddedItemsDetailComponent(String name, ClaimResponse.AddedItemsDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("service", element.getService());
      composeMoney("fee", element.getFee());
      for (ClaimResponse.AddedItemDetailAdjudicationComponent e : element.getAdjudication()) 
        composeClaimResponseAddedItemDetailAdjudicationComponent("adjudication", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseAddedItemDetailAdjudicationComponent(String name, ClaimResponse.AddedItemDetailAdjudicationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      composeMoney("amount", element.getAmount());
      composeDecimal("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseErrorsComponent(String name, ClaimResponse.ErrorsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequenceLinkId", element.getSequenceLinkIdElement());
      composeInteger("detailSequenceLinkId", element.getDetailSequenceLinkIdElement());
      composeInteger("subdetailSequenceLinkId", element.getSubdetailSequenceLinkIdElement());
      composeCoding("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeClaimResponseNotesComponent(String name, ClaimResponse.NotesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("number", element.getNumberElement());
      composeCoding("type", element.getType());
      composeString("text", element.getTextElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCommunicationRequest(String name, CommunicationRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("category", element.getCategory());
      composeReference("sender", element.getSender());
      for (Reference e : element.getRecipient()) 
        composeReference("recipient", e);
      for (CommunicationRequest.CommunicationRequestMessagePartComponent e : element.getMessagePart()) 
        composeCommunicationRequestCommunicationRequestMessagePartComponent("messagePart", e);
      for (CodeableConcept e : element.getMedium()) 
        composeCodeableConcept("medium", e);
      composeReference("requester", element.getRequester());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new CommunicationRequest.CommunicationRequestStatusEnumFactory());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new CommunicationRequest.CommunicationRequestModeEnumFactory());
      composeReference("encounter", element.getEncounter());
      composeDateTime("scheduledTime", element.getScheduledTimeElement());
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      composeDateTime("orderedOn", element.getOrderedOnElement());
      composeReference("subject", element.getSubject());
      composeCodeableConcept("priority", element.getPriority());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCommunicationRequestCommunicationRequestMessagePartComponent(String name, CommunicationRequest.CommunicationRequestMessagePartComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeType("content", element.getContent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTime("date", element.getDateElement());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      composeString("title", element.getTitleElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Composition.CompositionStatusEnumFactory());
      composeCoding("confidentiality", element.getConfidentiality());
      composeReference("subject", element.getSubject());
      for (Reference e : element.getAuthor()) 
        composeReference("author", e);
      for (Composition.CompositionAttesterComponent e : element.getAttester()) 
        composeCompositionCompositionAttesterComponent("attester", e);
      composeReference("custodian", element.getCustodian());
      for (Composition.CompositionEventComponent e : element.getEvent()) 
        composeCompositionCompositionEventComponent("event", e);
      composeReference("encounter", element.getEncounter());
      for (Composition.SectionComponent e : element.getSection()) 
        composeCompositionSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCompositionCompositionAttesterComponent(String name, Composition.CompositionAttesterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
        for (Enumeration<Composition.CompositionAttestationMode> e : element.getMode()) 
          composeEnumeration("mode", e, new Composition.CompositionAttestationModeEnumFactory());
      composeDateTime("time", element.getTimeElement());
      composeReference("party", element.getParty());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCompositionCompositionEventComponent(String name, Composition.CompositionEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getCode()) 
        composeCodeableConcept("code", e);
      composePeriod("period", element.getPeriod());
      for (Reference e : element.getDetail()) 
        composeReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("title", element.getTitleElement());
      composeCodeableConcept("code", element.getCode());
      for (Composition.SectionComponent e : element.getSection()) 
        composeCompositionSectionComponent("section", e);
      composeReference("content", element.getContent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMap(String name, ConceptMap element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeString("identifier", element.getIdentifierElement());
      composeString("version", element.getVersionElement());
      composeString("name", element.getNameElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      composeString("copyright", element.getCopyrightElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new ConceptMap.ValuesetStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeDateTime("date", element.getDateElement());
      composeType("source", element.getSource());
      composeType("target", element.getTarget());
      for (ConceptMap.ConceptMapElementComponent e : element.getElement()) 
        composeConceptMapConceptMapElementComponent("element", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMapConceptMapElementComponent(String name, ConceptMap.ConceptMapElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("codeSystem", element.getCodeSystemElement());
      composeCode("code", element.getCodeElement());
      for (ConceptMap.OtherElementComponent e : element.getDependsOn()) 
        composeConceptMapOtherElementComponent("dependsOn", e);
      for (ConceptMap.ConceptMapElementMapComponent e : element.getMap()) 
        composeConceptMapConceptMapElementMapComponent("map", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMapOtherElementComponent(String name, ConceptMap.OtherElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("element", element.getElementElement());
      composeUri("codeSystem", element.getCodeSystemElement());
      composeString("code", element.getCodeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMapConceptMapElementMapComponent(String name, ConceptMap.ConceptMapElementMapComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("codeSystem", element.getCodeSystemElement());
      composeCode("code", element.getCodeElement());
      if (element.getEquivalenceElement() != null)
        composeEnumeration("equivalence", element.getEquivalenceElement(), new ConceptMap.ConceptEquivalenceEnumFactory());
      composeString("comments", element.getCommentsElement());
      for (ConceptMap.OtherElementComponent e : element.getProduct()) 
        composeConceptMapOtherElementComponent("product", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("subject", element.getSubject());
      composeReference("encounter", element.getEncounter());
      composeReference("asserter", element.getAsserter());
      composeDate("dateAsserted", element.getDateAssertedElement());
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Condition.ConditionStatusEnumFactory());
      composeCodeableConcept("certainty", element.getCertainty());
      composeCodeableConcept("severity", element.getSeverity());
      composeType("onset", element.getOnset());
      composeType("abatement", element.getAbatement());
      composeConditionConditionStageComponent("stage", element.getStage());
      for (Condition.ConditionEvidenceComponent e : element.getEvidence()) 
        composeConditionConditionEvidenceComponent("evidence", e);
      for (Condition.ConditionLocationComponent e : element.getLocation()) 
        composeConditionConditionLocationComponent("location", e);
      for (Condition.ConditionDueToComponent e : element.getDueTo()) 
        composeConditionConditionDueToComponent("dueTo", e);
      for (Condition.ConditionOccurredFollowingComponent e : element.getOccurredFollowing()) 
        composeConditionConditionOccurredFollowingComponent("occurredFollowing", e);
      composeString("notes", element.getNotesElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("summary", element.getSummary());
      for (Reference e : element.getAssessment()) 
        composeReference("assessment", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (Reference e : element.getDetail()) 
        composeReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeString("detail", element.getDetailElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionDueToComponent(String name, Condition.ConditionDueToComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("codeableConcept", element.getCodeableConcept());
      composeReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionOccurredFollowingComponent(String name, Condition.ConditionOccurredFollowingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("codeableConcept", element.getCodeableConcept());
      composeReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeString("identifier", element.getIdentifierElement());
      composeString("version", element.getVersionElement());
      composeString("name", element.getNameElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Conformance.ConformanceStatementStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeDateTime("date", element.getDateElement());
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeId("fhirVersion", element.getFhirVersionElement());
      composeBoolean("acceptUnknown", element.getAcceptUnknownElement());
      for (CodeType e : element.getFormat()) 
        composeCode("format", e);
      for (Reference e : element.getProfile()) 
        composeReference("profile", e);
      for (Conformance.ConformanceRestComponent e : element.getRest()) 
        composeConformanceConformanceRestComponent("rest", e);
      for (Conformance.ConformanceMessagingComponent e : element.getMessaging()) 
        composeConformanceConformanceMessagingComponent("messaging", e);
      for (Conformance.ConformanceDocumentComponent e : element.getDocument()) 
        composeConformanceConformanceDocumentComponent("document", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceSoftwareComponent(String name, Conformance.ConformanceSoftwareComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeString("version", element.getVersionElement());
      composeDateTime("releaseDate", element.getReleaseDateElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("description", element.getDescriptionElement());
      composeUri("url", element.getUrlElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new Conformance.RestfulConformanceModeEnumFactory());
      composeString("documentation", element.getDocumentationElement());
      composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
        composeConformanceConformanceRestResourceComponent("resource", e);
      for (Conformance.SystemInteractionComponent e : element.getInteraction()) 
        composeConformanceSystemInteractionComponent("interaction", e);
      for (Conformance.ConformanceRestOperationComponent e : element.getOperation()) 
        composeConformanceConformanceRestOperationComponent("operation", e);
      for (UriType e : element.getDocumentMailbox()) 
        composeUri("documentMailbox", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeBoolean("cors", element.getCorsElement());
      for (CodeableConcept e : element.getService()) 
        composeCodeableConcept("service", e);
      composeString("description", element.getDescriptionElement());
      for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
        composeConformanceConformanceRestSecurityCertificateComponent("certificate", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("type", element.getTypeElement());
      composeBase64Binary("blob", element.getBlobElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("type", element.getTypeElement());
      composeReference("profile", element.getProfile());
      for (Conformance.ResourceInteractionComponent e : element.getInteraction()) 
        composeConformanceResourceInteractionComponent("interaction", e);
      if (element.getVersioningElement() != null)
        composeEnumeration("versioning", element.getVersioningElement(), new Conformance.VersioningPolicyEnumFactory());
      composeBoolean("readHistory", element.getReadHistoryElement());
      composeBoolean("updateCreate", element.getUpdateCreateElement());
      for (StringType e : element.getSearchInclude()) 
        composeString("searchInclude", e);
      for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getSearchParam()) 
        composeConformanceConformanceRestResourceSearchParamComponent("searchParam", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceResourceInteractionComponent(String name, Conformance.ResourceInteractionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getCodeElement() != null)
        composeEnumeration("code", element.getCodeElement(), new Conformance.TypeRestfulInteractionEnumFactory());
      composeString("documentation", element.getDocumentationElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeUri("definition", element.getDefinitionElement());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Conformance.SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentationElement());
      for (CodeType e : element.getTarget()) 
        composeCode("target", e);
      for (StringType e : element.getChain()) 
        composeString("chain", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceSystemInteractionComponent(String name, Conformance.SystemInteractionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getCodeElement() != null)
        composeEnumeration("code", element.getCodeElement(), new Conformance.SystemRestfulInteractionEnumFactory());
      composeString("documentation", element.getDocumentationElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeReference("definition", element.getDefinition());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("endpoint", element.getEndpointElement());
      composeInteger("reliableCache", element.getReliableCacheElement());
      composeString("documentation", element.getDocumentationElement());
      for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
        composeConformanceConformanceMessagingEventComponent("event", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      if (element.getCategoryElement() != null)
        composeEnumeration("category", element.getCategoryElement(), new Conformance.MessageSignificanceCategoryEnumFactory());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new Conformance.MessageConformanceEventModeEnumFactory());
      for (Coding e : element.getProtocol()) 
        composeCoding("protocol", e);
      composeCode("focus", element.getFocusElement());
      composeReference("request", element.getRequest());
      composeReference("response", element.getResponse());
      composeString("documentation", element.getDocumentationElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new Conformance.DocumentModeEnumFactory());
      composeString("documentation", element.getDocumentationElement());
      composeReference("profile", element.getProfile());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContract(String name, Contract element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      composeDateTime("issued", element.getIssuedElement());
      composePeriod("applies", element.getApplies());
      for (Reference e : element.getAuthor()) 
        composeReference("author", e);
      for (Reference e : element.getGrantor()) 
        composeReference("grantor", e);
      for (Reference e : element.getGrantee()) 
        composeReference("grantee", e);
      for (Reference e : element.getWitness()) 
        composeReference("witness", e);
      composeIdentifier("identifier", element.getIdentifier());
      for (Contract.ContractTermComponent e : element.getTerm()) 
        composeContractContractTermComponent("term", e);
      composeAttachment("friendly", element.getFriendly());
      composeAttachment("legal", element.getLegal());
      composeAttachment("rule", element.getRule());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContractContractTermComponent(String name, Contract.ContractTermComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      composeReference("subject", element.getSubject());
      composeString("text", element.getTextElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContraindication(String name, Contraindication element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("patient", element.getPatient());
      composeCodeableConcept("category", element.getCategory());
      composeCode("severity", element.getSeverityElement());
      for (Reference e : element.getImplicated()) 
        composeReference("implicated", e);
      composeString("detail", element.getDetailElement());
      composeDateTime("date", element.getDateElement());
      composeReference("author", element.getAuthor());
      composeIdentifier("identifier", element.getIdentifier());
      composeUri("reference", element.getReferenceElement());
      for (Contraindication.ContraindicationMitigationComponent e : element.getMitigation()) 
        composeContraindicationContraindicationMitigationComponent("mitigation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContraindicationContraindicationMitigationComponent(String name, Contraindication.ContraindicationMitigationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("action", element.getAction());
      composeDateTime("date", element.getDateElement());
      composeReference("author", element.getAuthor());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCoverage(String name, Coverage element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("issuer", element.getIssuer());
      composePeriod("period", element.getPeriod());
      composeCoding("type", element.getType());
      composeIdentifier("identifier", element.getIdentifier());
      composeString("group", element.getGroupElement());
      composeString("plan", element.getPlanElement());
      composeString("subplan", element.getSubplanElement());
      composeInteger("dependent", element.getDependentElement());
      composeInteger("sequence", element.getSequenceElement());
      composeReference("subscriber", element.getSubscriber());
      composeIdentifier("network", element.getNetwork());
      for (Reference e : element.getContract()) 
        composeReference("contract", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeString("version", element.getVersionElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DataElement.ResourceObservationDefStatusEnumFactory());
      composeDateTime("date", element.getDateElement());
      composeString("name", element.getNameElement());
      for (CodeableConcept e : element.getCategory()) 
        composeCodeableConcept("category", e);
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      composeString("question", element.getQuestionElement());
      composeString("definition", element.getDefinitionElement());
      composeString("comments", element.getCommentsElement());
      composeString("requirements", element.getRequirementsElement());
      for (StringType e : element.getSynonym()) 
        composeString("synonym", e);
      composeCode("type", element.getTypeElement());
      composeType("example", element.getExample());
      composeInteger("maxLength", element.getMaxLengthElement());
      composeCodeableConcept("units", element.getUnits());
      composeDataElementDataElementBindingComponent("binding", element.getBinding());
      for (DataElement.DataElementMappingComponent e : element.getMapping()) 
        composeDataElementDataElementMappingComponent("mapping", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDataElementDataElementBindingComponent(String name, DataElement.DataElementBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeBoolean("isExtensible", element.getIsExtensibleElement());
      if (element.getConformanceElement() != null)
        composeEnumeration("conformance", element.getConformanceElement(), new DataElement.BindingConformanceEnumFactory());
      composeString("description", element.getDescriptionElement());
      composeReference("valueSet", element.getValueSet());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("uri", element.getUriElement());
      composeString("name", element.getNameElement());
      composeString("comments", element.getCommentsElement());
      composeString("map", element.getMapElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturerElement());
      composeString("model", element.getModelElement());
      composeString("version", element.getVersionElement());
      composeDate("expiry", element.getExpiryElement());
      composeString("udi", element.getUdiElement());
      composeString("lotNumber", element.getLotNumberElement());
      composeReference("owner", element.getOwner());
      composeReference("location", element.getLocation());
      composeReference("patient", element.getPatient());
      for (ContactPoint e : element.getContact()) 
        composeContactPoint("contact", e);
      composeUri("url", element.getUrlElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceComponent(String name, DeviceComponent element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeIdentifier("identifier", element.getIdentifier());
      composeInstant("lastSystemChange", element.getLastSystemChangeElement());
      composeReference("source", element.getSource());
      composeReference("parent", element.getParent());
      for (CodeableConcept e : element.getOperationalStatus()) 
        composeCodeableConcept("operationalStatus", e);
      composeCodeableConcept("parameterGroup", element.getParameterGroup());
      if (element.getMeasurementPrincipleElement() != null)
        composeEnumeration("measurementPrinciple", element.getMeasurementPrincipleElement(), new DeviceComponent.MeasurementPrincipleEnumFactory());
      for (DeviceComponent.DeviceComponentProductionSpecificationComponent e : element.getProductionSpecification()) 
        composeDeviceComponentDeviceComponentProductionSpecificationComponent("productionSpecification", e);
      composeCodeableConcept("languageCode", element.getLanguageCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceComponentDeviceComponentProductionSpecificationComponent(String name, DeviceComponent.DeviceComponentProductionSpecificationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("specType", element.getSpecType());
      composeIdentifier("componentId", element.getComponentId());
      composeString("productionSpec", element.getProductionSpecElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceUseRequest(String name, DeviceUseRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DeviceUseRequest.DeviceUseRequestStatusEnumFactory());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new DeviceUseRequest.DeviceUseRequestModeEnumFactory());
      composeReference("device", element.getDevice());
      composeReference("encounter", element.getEncounter());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      for (StringType e : element.getNotes()) 
        composeString("notes", e);
      for (CodeableConcept e : element.getPrnReason()) 
        composeCodeableConcept("prnReason", e);
      composeDateTime("orderedOn", element.getOrderedOnElement());
      composeDateTime("recordedOn", element.getRecordedOnElement());
      composeReference("subject", element.getSubject());
      composeType("timing", element.getTiming());
      if (element.getPriorityElement() != null)
        composeEnumeration("priority", element.getPriorityElement(), new DeviceUseRequest.DeviceUseRequestPriorityEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceUseStatement(String name, DeviceUseStatement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      composePeriod("whenUsed", element.getWhenUsed());
      composeReference("device", element.getDevice());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      for (StringType e : element.getNotes()) 
        composeString("notes", e);
      composeDateTime("recordedOn", element.getRecordedOnElement());
      composeReference("subject", element.getSubject());
      composeType("timing", element.getTiming());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("encounter", element.getEncounter());
      composeString("clinicalNotes", element.getClinicalNotesElement());
      for (Reference e : element.getSupportingInformation()) 
        composeReference("supportingInformation", e);
      for (Reference e : element.getSpecimen()) 
        composeReference("specimen", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      if (element.getPriorityElement() != null)
        composeEnumeration("priority", element.getPriorityElement(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory());
      for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
        composeDiagnosticOrderDiagnosticOrderEventComponent("event", e);
      for (DiagnosticOrder.DiagnosticOrderItemComponent e : element.getItem()) 
        composeDiagnosticOrderDiagnosticOrderItemComponent("item", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderEventComponent(String name, DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      composeCodeableConcept("description", element.getDescription());
      composeDateTime("dateTime", element.getDateTimeElement());
      composeReference("actor", element.getActor());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (Reference e : element.getSpecimen()) 
        composeReference("specimen", e);
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
        composeDiagnosticOrderDiagnosticOrderEventComponent("event", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeCodeableConcept("name", element.getName());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DiagnosticReport.DiagnosticReportStatusEnumFactory());
      composeDateTime("issued", element.getIssuedElement());
      composeReference("subject", element.getSubject());
      composeReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      for (Reference e : element.getRequestDetail()) 
        composeReference("requestDetail", e);
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeType("diagnostic", element.getDiagnostic());
      for (Reference e : element.getSpecimen()) 
        composeReference("specimen", e);
      for (Reference e : element.getResult()) 
        composeReference("result", e);
      for (Reference e : element.getImagingStudy()) 
        composeReference("imagingStudy", e);
      for (DiagnosticReport.DiagnosticReportImageComponent e : element.getImage()) 
        composeDiagnosticReportDiagnosticReportImageComponent("image", e);
      composeString("conclusion", element.getConclusionElement());
      for (CodeableConcept e : element.getCodedDiagnosis()) 
        composeCodeableConcept("codedDiagnosis", e);
      for (Attachment e : element.getPresentedForm()) 
        composeAttachment("presentedForm", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticReportDiagnosticReportImageComponent(String name, DiagnosticReport.DiagnosticReportImageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("comment", element.getCommentElement());
      composeReference("link", element.getLink());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentManifest(String name, DocumentManifest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (Reference e : element.getSubject()) 
        composeReference("subject", e);
      for (Reference e : element.getRecipient()) 
        composeReference("recipient", e);
      composeCodeableConcept("type", element.getType());
      for (Reference e : element.getAuthor()) 
        composeReference("author", e);
      composeDateTime("created", element.getCreatedElement());
      composeUri("source", element.getSourceElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DocumentManifest.DocumentReferenceStatusEnumFactory());
      composeReference("supercedes", element.getSupercedes());
      composeString("description", element.getDescriptionElement());
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      for (Reference e : element.getContent()) 
        composeReference("content", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      for (Reference e : element.getAuthor()) 
        composeReference("author", e);
      composeReference("custodian", element.getCustodian());
      composeUri("policyManager", element.getPolicyManagerElement());
      composeReference("authenticator", element.getAuthenticator());
      composeDateTime("created", element.getCreatedElement());
      composeInstant("indexed", element.getIndexedElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new DocumentReference.DocumentReferenceStatusEnumFactory());
      composeCodeableConcept("docStatus", element.getDocStatus());
      for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
        composeDocumentReferenceDocumentReferenceRelatesToComponent("relatesTo", e);
      composeString("description", element.getDescriptionElement());
      for (CodeableConcept e : element.getConfidentiality()) 
        composeCodeableConcept("confidentiality", e);
      composeCode("primaryLanguage", element.getPrimaryLanguageElement());
      composeCode("mimeType", element.getMimeTypeElement());
      for (UriType e : element.getFormat()) 
        composeUri("format", e);
      composeInteger("size", element.getSizeElement());
      composeBase64Binary("hash", element.getHashElement());
      composeUri("location", element.getLocationElement());
      composeDocumentReferenceDocumentReferenceServiceComponent("service", element.getService());
      composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceRelatesToComponent(String name, DocumentReference.DocumentReferenceRelatesToComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getCodeElement() != null)
        composeEnumeration("code", element.getCodeElement(), new DocumentReference.DocumentRelationshipTypeEnumFactory());
      composeReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("address", element.getAddressElement());
      for (DocumentReference.DocumentReferenceServiceParameterComponent e : element.getParameter()) 
        composeDocumentReferenceDocumentReferenceServiceParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponent(String name, DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeString("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getEvent()) 
        composeCodeableConcept("event", e);
      composePeriod("period", element.getPeriod());
      composeCodeableConcept("facilityType", element.getFacilityType());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEligibility(String name, Eligibility element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDate("date", element.getDateElement());
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Encounter.EncounterStateEnumFactory());
      if (element.getClass_Element() != null)
        composeEnumeration("class", element.getClass_Element(), new Encounter.EncounterClassEnumFactory());
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeReference("subject", element.getSubject());
      for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
        composeEncounterEncounterParticipantComponent("participant", e);
      composeReference("fulfills", element.getFulfills());
      composePeriod("period", element.getPeriod());
      composeDuration("length", element.getLength());
      composeCodeableConcept("reason", element.getReason());
      composeReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      for (Encounter.EncounterLocationComponent e : element.getLocation()) 
        composeEncounterEncounterLocationComponent("location", e);
      composeReference("serviceProvider", element.getServiceProvider());
      composeReference("partOf", element.getPartOf());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterParticipantComponent(String name, Encounter.EncounterParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeReference("individual", element.getIndividual());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeReference("origin", element.getOrigin());
      composeCodeableConcept("admitSource", element.getAdmitSource());
      composePeriod("period", element.getPeriod());
      for (Encounter.EncounterHospitalizationAccomodationComponent e : element.getAccomodation()) 
        composeEncounterEncounterHospitalizationAccomodationComponent("accomodation", e);
      composeCodeableConcept("diet", element.getDiet());
      for (CodeableConcept e : element.getSpecialCourtesy()) 
        composeCodeableConcept("specialCourtesy", e);
      for (CodeableConcept e : element.getSpecialArrangement()) 
        composeCodeableConcept("specialArrangement", e);
      composeReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeReference("dischargeDiagnosis", element.getDischargeDiagnosis());
      composeBoolean("reAdmission", element.getReAdmissionElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeExplanationOfBenefit(String name, ExplanationOfBenefit element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("request", element.getRequest());
      for (Identifier e : element.getRequestIdentifier()) 
        composeIdentifier("requestIdentifier", e);
      if (element.getOutcomeElement() != null)
        composeEnumeration("outcome", element.getOutcomeElement(), new ExplanationOfBenefit.RSLinkEnumFactory());
      composeString("disposition", element.getDispositionElement());
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDate("date", element.getDateElement());
      composeReference("organization", element.getOrganization());
      composeReference("requestProvider", element.getRequestProvider());
      composeReference("requestOrganization", element.getRequestOrganization());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeExtensionDefinition(String name, ExtensionDefinition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("url", element.getUrlElement());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("name", element.getNameElement());
      composeString("display", element.getDisplayElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new ExtensionDefinition.ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeDateTime("date", element.getDateElement());
      composeString("requirements", element.getRequirementsElement());
      for (ExtensionDefinition.ExtensionDefinitionMappingComponent e : element.getMapping()) 
        composeExtensionDefinitionExtensionDefinitionMappingComponent("mapping", e);
      if (element.getContextTypeElement() != null)
        composeEnumeration("contextType", element.getContextTypeElement(), new ExtensionDefinition.ExtensionContextEnumFactory());
      for (StringType e : element.getContext()) 
        composeString("context", e);
      for (ElementDefinition e : element.getElement()) 
        composeElementDefinition("element", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeExtensionDefinitionExtensionDefinitionMappingComponent(String name, ExtensionDefinition.ExtensionDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identity", element.getIdentityElement());
      composeUri("uri", element.getUriElement());
      composeString("name", element.getNameElement());
      composeString("comments", element.getCommentsElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("patient", element.getPatient());
      composeDateTime("date", element.getDateElement());
      composeString("note", element.getNoteElement());
      for (FamilyHistory.FamilyHistoryRelationComponent e : element.getRelation()) 
        composeFamilyHistoryFamilyHistoryRelationComponent("relation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponent(String name, FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("born", element.getBorn());
      composeType("age", element.getAge());
      composeType("deceased", element.getDeceased());
      composeString("note", element.getNoteElement());
      for (FamilyHistory.FamilyHistoryRelationConditionComponent e : element.getCondition()) 
        composeFamilyHistoryFamilyHistoryRelationConditionComponent("condition", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponent(String name, FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("onset", element.getOnset());
      composeString("note", element.getNoteElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Group.GroupTypeEnumFactory());
      composeBoolean("actual", element.getActualElement());
      composeCodeableConcept("code", element.getCode());
      composeString("name", element.getNameElement());
      composeInteger("quantity", element.getQuantityElement());
      for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
        composeGroupGroupCharacteristicComponent("characteristic", e);
      for (Reference e : element.getMember()) 
        composeReference("member", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeType("value", element.getValue());
      composeBoolean("exclude", element.getExcludeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHealthcareService(String name, HealthcareService element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("location", element.getLocation());
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      for (HealthcareService.ServiceTypeComponent e : element.getServiceType()) 
        composeHealthcareServiceServiceTypeComponent("serviceType", e);
      composeString("serviceName", element.getServiceNameElement());
      composeString("comment", element.getCommentElement());
      composeString("extraDetails", element.getExtraDetailsElement());
      composeCodeableConcept("freeProvisionCode", element.getFreeProvisionCode());
      composeCodeableConcept("eligibility", element.getEligibility());
      composeString("eligibilityNote", element.getEligibilityNoteElement());
      composeCodeableConcept("appointmentRequired", element.getAppointmentRequired());
      composeUri("imageURI", element.getImageURIElement());
      for (HealthcareService.HealthcareServiceAvailableTimeComponent e : element.getAvailableTime()) 
        composeHealthcareServiceHealthcareServiceAvailableTimeComponent("availableTime", e);
      for (HealthcareService.HealthcareServiceNotAvailableTimeComponent e : element.getNotAvailableTime()) 
        composeHealthcareServiceHealthcareServiceNotAvailableTimeComponent("notAvailableTime", e);
      composeString("availabilityExceptions", element.getAvailabilityExceptionsElement());
      composeString("publicKey", element.getPublicKeyElement());
      for (StringType e : element.getProgramName()) 
        composeString("programName", e);
      for (ContactPoint e : element.getContactPoint()) 
        composeContactPoint("contactPoint", e);
      for (CodeableConcept e : element.getCharacteristic()) 
        composeCodeableConcept("characteristic", e);
      for (CodeableConcept e : element.getReferralMethod()) 
        composeCodeableConcept("referralMethod", e);
      for (CodeableConcept e : element.getSetting()) 
        composeCodeableConcept("setting", e);
      for (CodeableConcept e : element.getTargetGroup()) 
        composeCodeableConcept("targetGroup", e);
      for (CodeableConcept e : element.getCoverageArea()) 
        composeCodeableConcept("coverageArea", e);
      for (CodeableConcept e : element.getCatchmentArea()) 
        composeCodeableConcept("catchmentArea", e);
      for (CodeableConcept e : element.getServiceCode()) 
        composeCodeableConcept("serviceCode", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHealthcareServiceServiceTypeComponent(String name, HealthcareService.ServiceTypeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getSpecialty()) 
        composeCodeableConcept("specialty", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHealthcareServiceHealthcareServiceAvailableTimeComponent(String name, HealthcareService.HealthcareServiceAvailableTimeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getDaysOfWeek()) 
        composeCodeableConcept("daysOfWeek", e);
      composeBoolean("allDay", element.getAllDayElement());
      composeDateTime("availableStartTime", element.getAvailableStartTimeElement());
      composeDateTime("availableEndTime", element.getAvailableEndTimeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHealthcareServiceHealthcareServiceNotAvailableTimeComponent(String name, HealthcareService.HealthcareServiceNotAvailableTimeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("description", element.getDescriptionElement());
      composeDateTime("startDate", element.getStartDateElement());
      composeDateTime("endDate", element.getEndDateElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeDateTime("started", element.getStartedElement());
      composeReference("subject", element.getSubject());
      composeOid("uid", element.getUidElement());
      composeIdentifier("accession", element.getAccession());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (Reference e : element.getOrder()) 
        composeReference("order", e);
        for (Enumeration<ImagingStudy.ImagingModality> e : element.getModalityList()) 
          composeEnumeration("modalityList", e, new ImagingStudy.ImagingModalityEnumFactory());
      composeReference("referrer", element.getReferrer());
      if (element.getAvailabilityElement() != null)
        composeEnumeration("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrlElement());
      composeInteger("numberOfSeries", element.getNumberOfSeriesElement());
      composeInteger("numberOfInstances", element.getNumberOfInstancesElement());
      composeString("clinicalInformation", element.getClinicalInformationElement());
      for (Coding e : element.getProcedure()) 
        composeCoding("procedure", e);
      composeReference("interpreter", element.getInterpreter());
      composeString("description", element.getDescriptionElement());
      for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
        composeImagingStudyImagingStudySeriesComponent("series", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("number", element.getNumberElement());
      if (element.getModalityElement() != null)
        composeEnumeration("modality", element.getModalityElement(), new ImagingStudy.ModalityEnumFactory());
      composeOid("uid", element.getUidElement());
      composeString("description", element.getDescriptionElement());
      composeInteger("numberOfInstances", element.getNumberOfInstancesElement());
      if (element.getAvailabilityElement() != null)
        composeEnumeration("availability", element.getAvailabilityElement(), new ImagingStudy.InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrlElement());
      composeCoding("bodySite", element.getBodySite());
      composeDateTime("dateTime", element.getDateTimeElement());
      for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
        composeImagingStudyImagingStudySeriesInstanceComponent("instance", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("number", element.getNumberElement());
      composeOid("uid", element.getUidElement());
      composeOid("sopclass", element.getSopclassElement());
      composeString("type", element.getTypeElement());
      composeString("title", element.getTitleElement());
      composeUri("url", element.getUrlElement());
      composeReference("attachment", element.getAttachment());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("date", element.getDateElement());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeReference("subject", element.getSubject());
      composeBoolean("refusedIndicator", element.getRefusedIndicatorElement());
      composeBoolean("reported", element.getReportedElement());
      composeReference("performer", element.getPerformer());
      composeReference("requester", element.getRequester());
      composeReference("manufacturer", element.getManufacturer());
      composeReference("location", element.getLocation());
      composeString("lotNumber", element.getLotNumberElement());
      composeDate("expirationDate", element.getExpirationDateElement());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeImmunizationImmunizationExplanationComponent("explanation", element.getExplanation());
      for (Immunization.ImmunizationReactionComponent e : element.getReaction()) 
        composeImmunizationImmunizationReactionComponent("reaction", e);
      for (Immunization.ImmunizationVaccinationProtocolComponent e : element.getVaccinationProtocol()) 
        composeImmunizationImmunizationVaccinationProtocolComponent("vaccinationProtocol", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationExplanationComponent(String name, Immunization.ImmunizationExplanationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getReason()) 
        composeCodeableConcept("reason", e);
      for (CodeableConcept e : element.getRefusalReason()) 
        composeCodeableConcept("refusalReason", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDateTime("date", element.getDateElement());
      composeReference("detail", element.getDetail());
      composeBoolean("reported", element.getReportedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("doseSequence", element.getDoseSequenceElement());
      composeString("description", element.getDescriptionElement());
      composeReference("authority", element.getAuthority());
      composeString("series", element.getSeriesElement());
      composeInteger("seriesDoses", element.getSeriesDosesElement());
      composeCodeableConcept("doseTarget", element.getDoseTarget());
      composeCodeableConcept("doseStatus", element.getDoseStatus());
      composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendation(String name, ImmunizationRecommendation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("subject", element.getSubject());
      for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent e : element.getRecommendation()) 
        composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent("recommendation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDateTime("date", element.getDateElement());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeInteger("doseNumber", element.getDoseNumberElement());
      composeCodeableConcept("forecastStatus", element.getForecastStatus());
      for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent e : element.getDateCriterion()) 
        composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent("dateCriterion", e);
      composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent("protocol", element.getProtocol());
      for (Reference e : element.getSupportingImmunization()) 
        composeReference("supportingImmunization", e);
      for (Reference e : element.getSupportingPatientInformation()) 
        composeReference("supportingPatientInformation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTime("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("doseSequence", element.getDoseSequenceElement());
      composeString("description", element.getDescriptionElement());
      composeReference("authority", element.getAuthority());
      composeString("series", element.getSeriesElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("source", element.getSource());
      composeDateTime("date", element.getDateElement());
      composeBoolean("ordered", element.getOrderedElement());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new List_.ListModeEnumFactory());
      for (List_.ListEntryComponent e : element.getEntry()) 
        composeList_ListEntryComponent("entry", e);
      composeCodeableConcept("emptyReason", element.getEmptyReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeList_ListEntryComponent(String name, List_.ListEntryComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getFlag()) 
        composeCodeableConcept("flag", e);
      composeBoolean("deleted", element.getDeletedElement());
      composeDateTime("date", element.getDateElement());
      composeReference("item", element.getItem());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("name", element.getNameElement());
      composeString("description", element.getDescriptionElement());
      composeCodeableConcept("type", element.getType());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeAddress("address", element.getAddress());
      composeCodeableConcept("physicalType", element.getPhysicalType());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeReference("managingOrganization", element.getManagingOrganization());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Location.LocationStatusEnumFactory());
      composeReference("partOf", element.getPartOf());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new Location.LocationModeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDecimal("longitude", element.getLongitudeElement());
      composeDecimal("latitude", element.getLatitudeElement());
      composeDecimal("altitude", element.getAltitudeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Media.MediaTypeEnumFactory());
      composeCodeableConcept("subtype", element.getSubtype());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("created", element.getCreatedElement());
      composeReference("subject", element.getSubject());
      composeReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeString("deviceName", element.getDeviceNameElement());
      composeInteger("height", element.getHeightElement());
      composeInteger("width", element.getWidthElement());
      composeInteger("frames", element.getFramesElement());
      composeInteger("duration", element.getDurationElement());
      composeAttachment("content", element.getContent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeString("name", element.getNameElement());
      composeCodeableConcept("code", element.getCode());
      composeBoolean("isBrand", element.getIsBrandElement());
      composeReference("manufacturer", element.getManufacturer());
      if (element.getKindElement() != null)
        composeEnumeration("kind", element.getKindElement(), new Medication.MedicationKindEnumFactory());
      composeMedicationMedicationProductComponent("product", element.getProduct());
      composeMedicationMedicationPackageComponent("package", element.getPackage());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("form", element.getForm());
      for (Medication.MedicationProductIngredientComponent e : element.getIngredient()) 
        composeMedicationMedicationProductIngredientComponent("ingredient", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationProductIngredientComponent(String name, Medication.MedicationProductIngredientComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("item", element.getItem());
      composeRatio("amount", element.getAmount());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("container", element.getContainer());
      for (Medication.MedicationPackageContentComponent e : element.getContent()) 
        composeMedicationMedicationPackageContentComponent("content", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationPackageContentComponent(String name, Medication.MedicationPackageContentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("item", element.getItem());
      composeQuantity("amount", element.getAmount());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new MedicationAdministration.MedicationAdminStatusEnumFactory());
      composeReference("patient", element.getPatient());
      composeReference("practitioner", element.getPractitioner());
      composeReference("encounter", element.getEncounter());
      composeReference("prescription", element.getPrescription());
      composeBoolean("wasNotGiven", element.getWasNotGivenElement());
      for (CodeableConcept e : element.getReasonNotGiven()) 
        composeCodeableConcept("reasonNotGiven", e);
      composeType("effectiveTime", element.getEffectiveTime());
      composeReference("medication", element.getMedication());
      for (Reference e : element.getDevice()) 
        composeReference("device", e);
      for (MedicationAdministration.MedicationAdministrationDosageComponent e : element.getDosage()) 
        composeMedicationAdministrationMedicationAdministrationDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeType("timing", element.getTiming());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationDispense(String name, MedicationDispense element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory());
      composeReference("patient", element.getPatient());
      composeReference("dispenser", element.getDispenser());
      for (Reference e : element.getAuthorizingPrescription()) 
        composeReference("authorizingPrescription", e);
      for (MedicationDispense.MedicationDispenseDispenseComponent e : element.getDispense()) 
        composeMedicationDispenseMedicationDispenseDispenseComponent("dispense", e);
      composeMedicationDispenseMedicationDispenseSubstitutionComponent("substitution", element.getSubstitution());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseComponent(String name, MedicationDispense.MedicationDispenseDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new MedicationDispense.MedicationDispenseStatusEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeReference("medication", element.getMedication());
      composeDateTime("whenPrepared", element.getWhenPreparedElement());
      composeDateTime("whenHandedOver", element.getWhenHandedOverElement());
      composeReference("destination", element.getDestination());
      for (Reference e : element.getReceiver()) 
        composeReference("receiver", e);
      for (MedicationDispense.MedicationDispenseDispenseDosageComponent e : element.getDosage()) 
        composeMedicationDispenseMedicationDispenseDispenseDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponent(String name, MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("schedule", element.getSchedule());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getReason()) 
        composeCodeableConcept("reason", e);
      for (Reference e : element.getResponsibleParty()) 
        composeReference("responsibleParty", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("dateWritten", element.getDateWrittenElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory());
      composeReference("patient", element.getPatient());
      composeReference("prescriber", element.getPrescriber());
      composeReference("encounter", element.getEncounter());
      composeType("reason", element.getReason());
      composeReference("medication", element.getMedication());
      for (MedicationPrescription.MedicationPrescriptionDosageInstructionComponent e : element.getDosageInstruction()) 
        composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent("dosageInstruction", e);
      composeMedicationPrescriptionMedicationPrescriptionDispenseComponent("dispense", element.getDispense());
      composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent("substitution", element.getSubstitution());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("text", element.getTextElement());
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("scheduled", element.getScheduled());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDispenseComponent(String name, MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("medication", element.getMedication());
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeInteger("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowedElement());
      composeQuantity("quantity", element.getQuantity());
      composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("patient", element.getPatient());
      composeBoolean("wasNotGiven", element.getWasNotGivenElement());
      for (CodeableConcept e : element.getReasonNotGiven()) 
        composeCodeableConcept("reasonNotGiven", e);
      composePeriod("whenGiven", element.getWhenGiven());
      composeReference("medication", element.getMedication());
      for (Reference e : element.getDevice()) 
        composeReference("device", e);
      for (MedicationStatement.MedicationStatementDosageComponent e : element.getDosage()) 
        composeMedicationStatementMedicationStatementDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeTiming("schedule", element.getSchedule());
      composeType("asNeeded", element.getAsNeeded());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeader(String name, MessageHeader element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeId("identifier", element.getIdentifierElement());
      composeInstant("timestamp", element.getTimestampElement());
      composeCoding("event", element.getEvent());
      composeMessageHeaderMessageHeaderResponseComponent("response", element.getResponse());
      composeMessageHeaderMessageSourceComponent("source", element.getSource());
      for (MessageHeader.MessageDestinationComponent e : element.getDestination()) 
        composeMessageHeaderMessageDestinationComponent("destination", e);
      composeReference("enterer", element.getEnterer());
      composeReference("author", element.getAuthor());
      composeReference("receiver", element.getReceiver());
      composeReference("responsible", element.getResponsible());
      composeCodeableConcept("reason", element.getReason());
      for (Reference e : element.getData()) 
        composeReference("data", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identifier", element.getIdentifierElement());
      if (element.getCodeElement() != null)
        composeEnumeration("code", element.getCodeElement(), new MessageHeader.ResponseCodeEnumFactory());
      composeReference("details", element.getDetails());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeString("software", element.getSoftwareElement());
      composeString("version", element.getVersionElement());
      composeContactPoint("contact", element.getContact());
      composeUri("endpoint", element.getEndpointElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getNameElement());
      composeReference("target", element.getTarget());
      composeUri("endpoint", element.getEndpointElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamingSystem(String name, NamingSystem element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new NamingSystem.NamingsystemTypeEnumFactory());
      composeString("name", element.getNameElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new NamingSystem.NamingsystemStatusEnumFactory());
      composeCode("country", element.getCountryElement());
      composeCodeableConcept("category", element.getCategory());
      composeString("responsible", element.getResponsibleElement());
      composeString("description", element.getDescriptionElement());
      composeString("usage", element.getUsageElement());
      for (NamingSystem.NamingSystemUniqueIdComponent e : element.getUniqueId()) 
        composeNamingSystemNamingSystemUniqueIdComponent("uniqueId", e);
      composeNamingSystemNamingSystemContactComponent("contact", element.getContact());
      composeReference("replacedBy", element.getReplacedBy());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamingSystemNamingSystemUniqueIdComponent(String name, NamingSystem.NamingSystemUniqueIdComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new NamingSystem.NamingsystemIdentifierTypeEnumFactory());
      composeString("value", element.getValueElement());
      composeBoolean("preferred", element.getPreferredElement());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamingSystemNamingSystemContactComponent(String name, NamingSystem.NamingSystemContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeHumanName("name", element.getName());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrder(String name, NutritionOrder element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeReference("orderer", element.getOrderer());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("encounter", element.getEncounter());
      composeDateTime("dateTime", element.getDateTimeElement());
      for (Reference e : element.getAllergyIntolerance()) 
        composeReference("allergyIntolerance", e);
      for (CodeableConcept e : element.getFoodPreferenceModifier()) 
        composeCodeableConcept("foodPreferenceModifier", e);
      for (CodeableConcept e : element.getExcludeFoodModifier()) 
        composeCodeableConcept("excludeFoodModifier", e);
      for (NutritionOrder.NutritionOrderItemComponent e : element.getItem()) 
        composeNutritionOrderNutritionOrderItemComponent("item", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new NutritionOrder.NutritionOrderStatusEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemComponent(String name, NutritionOrder.NutritionOrderItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeType("scheduled", element.getScheduled());
      composeBoolean("isInEffect", element.getIsInEffectElement());
      composeNutritionOrderNutritionOrderItemOralDietComponent("oralDiet", element.getOralDiet());
      composeNutritionOrderNutritionOrderItemSupplementComponent("supplement", element.getSupplement());
      composeNutritionOrderNutritionOrderItemEnteralFormulaComponent("enteralFormula", element.getEnteralFormula());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietComponent(String name, NutritionOrder.NutritionOrderItemOralDietComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getCode()) 
        composeCodeableConcept("code", e);
      for (NutritionOrder.NutritionOrderItemOralDietNutrientsComponent e : element.getNutrients()) 
        composeNutritionOrderNutritionOrderItemOralDietNutrientsComponent("nutrients", e);
      for (NutritionOrder.NutritionOrderItemOralDietTextureComponent e : element.getTexture()) 
        composeNutritionOrderNutritionOrderItemOralDietTextureComponent("texture", e);
      for (CodeableConcept e : element.getFluidConsistencyType()) 
        composeCodeableConcept("fluidConsistencyType", e);
      composeString("description", element.getDescriptionElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietNutrientsComponent(String name, NutritionOrder.NutritionOrderItemOralDietNutrientsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("modifier", element.getModifier());
      composeType("amount", element.getAmount());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemOralDietTextureComponent(String name, NutritionOrder.NutritionOrderItemOralDietTextureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("modifier", element.getModifier());
      composeCodeableConcept("foodType", element.getFoodType());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemSupplementComponent(String name, NutritionOrder.NutritionOrderItemSupplementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeQuantity("quantity", element.getQuantity());
      composeString("name", element.getNameElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNutritionOrderNutritionOrderItemEnteralFormulaComponent(String name, NutritionOrder.NutritionOrderItemEnteralFormulaComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("baseFormulaType", element.getBaseFormulaType());
      for (CodeableConcept e : element.getAdditiveType()) 
        composeCodeableConcept("additiveType", e);
      for (Quantity e : element.getCaloricDensity()) 
        composeQuantity("caloricDensity", e);
      for (CodeableConcept e : element.getRouteofAdministration()) 
        composeCodeableConcept("routeofAdministration", e);
      for (Quantity e : element.getRate()) 
        composeQuantity("rate", e);
      composeString("baseFormulaName", element.getBaseFormulaNameElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      if (element.getDataAbsentReasonElement() != null)
        composeEnumeration("dataAbsentReason", element.getDataAbsentReasonElement(), new Observation.DataAbsentReasonEnumFactory());
      composeCodeableConcept("interpretation", element.getInterpretation());
      composeString("comments", element.getCommentsElement());
      composeType("applies", element.getApplies());
      composeInstant("issued", element.getIssuedElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Observation.ObservationStatusEnumFactory());
      if (element.getReliabilityElement() != null)
        composeEnumeration("reliability", element.getReliabilityElement(), new Observation.ObservationReliabilityEnumFactory());
      composeCodeableConcept("bodySite", element.getBodySite());
      composeCodeableConcept("method", element.getMethod());
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("subject", element.getSubject());
      composeReference("specimen", element.getSpecimen());
      for (Reference e : element.getPerformer()) 
        composeReference("performer", e);
      composeReference("encounter", element.getEncounter());
      for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
        composeObservationObservationReferenceRangeComponent("referenceRange", e);
      for (Observation.ObservationRelatedComponent e : element.getRelated()) 
        composeObservationObservationRelatedComponent("related", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      composeCodeableConcept("meaning", element.getMeaning());
      composeRange("age", element.getAge());
      composeString("text", element.getTextElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Observation.ObservationRelationshiptypesEnumFactory());
      composeReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationDefinition(String name, OperationDefinition element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("identifier", element.getIdentifierElement());
      composeString("version", element.getVersionElement());
      composeString("title", element.getTitleElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new OperationDefinition.ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeDateTime("date", element.getDateElement());
      if (element.getKindElement() != null)
        composeEnumeration("kind", element.getKindElement(), new OperationDefinition.OperationKindEnumFactory());
      composeCode("name", element.getNameElement());
      composeString("notes", element.getNotesElement());
      composeReference("base", element.getBase());
      composeBoolean("system", element.getSystemElement());
      for (CodeType e : element.getType()) 
        composeCode("type", e);
      composeBoolean("instance", element.getInstanceElement());
      for (OperationDefinition.OperationDefinitionParameterComponent e : element.getParameter()) 
        composeOperationDefinitionOperationDefinitionParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationDefinitionOperationDefinitionParameterComponent(String name, OperationDefinition.OperationDefinitionParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("name", element.getNameElement());
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new OperationDefinition.OperationParameterUseEnumFactory());
      composeInteger("min", element.getMinElement());
      composeString("max", element.getMaxElement());
      composeString("documentation", element.getDocumentationElement());
      composeCoding("type", element.getType());
      composeReference("profile", element.getProfile());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
        composeOperationOutcomeOperationOutcomeIssueComponent("issue", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getSeverityElement() != null)
        composeEnumeration("severity", element.getSeverityElement(), new OperationOutcome.IssueSeverityEnumFactory());
      composeCoding("type", element.getType());
      composeString("details", element.getDetailsElement());
      for (StringType e : element.getLocation()) 
        composeString("location", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaim(String name, OralHealthClaim element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDate("date", element.getDateElement());
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      if (element.getUseElement() != null)
        composeEnumeration("use", element.getUseElement(), new OralHealthClaim.UseLinkEnumFactory());
      composeCoding("priority", element.getPriority());
      composeCoding("fundsReserve", element.getFundsReserve());
      composeReference("enterer", element.getEnterer());
      composeReference("facility", element.getFacility());
      composeOralHealthClaimPayeeComponent("payee", element.getPayee());
      composeReference("referral", element.getReferral());
      for (OralHealthClaim.DiagnosisComponent e : element.getDiagnosis()) 
        composeOralHealthClaimDiagnosisComponent("diagnosis", e);
      for (Coding e : element.getCondition()) 
        composeCoding("condition", e);
      composeReference("patient", element.getPatient());
      for (OralHealthClaim.CoverageComponent e : element.getCoverage()) 
        composeOralHealthClaimCoverageComponent("coverage", e);
      for (Coding e : element.getException()) 
        composeCoding("exception", e);
      composeString("school", element.getSchoolElement());
      composeDate("accident", element.getAccidentElement());
      composeCoding("accidentType", element.getAccidentType());
      for (Coding e : element.getInterventionException()) 
        composeCoding("interventionException", e);
      for (OralHealthClaim.MissingTeethComponent e : element.getMissingteeth()) 
        composeOralHealthClaimMissingTeethComponent("missingteeth", e);
      composeOralHealthClaimOrthodonticPlanComponent("orthoPlan", element.getOrthoPlan());
      for (OralHealthClaim.ItemsComponent e : element.getItem()) 
        composeOralHealthClaimItemsComponent("item", e);
      for (Coding e : element.getAdditionalMaterials()) 
        composeCoding("additionalMaterials", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimPayeeComponent(String name, OralHealthClaim.PayeeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("type", element.getType());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      composeReference("person", element.getPerson());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimDiagnosisComponent(String name, OralHealthClaim.DiagnosisComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequence", element.getSequenceElement());
      composeCoding("diagnosis", element.getDiagnosis());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimCoverageComponent(String name, OralHealthClaim.CoverageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequence", element.getSequenceElement());
      composeBoolean("focal", element.getFocalElement());
      composeReference("coverage", element.getCoverage());
      composeString("businessArrangement", element.getBusinessArrangementElement());
      composeCoding("relationship", element.getRelationship());
      for (StringType e : element.getPreauthref()) 
        composeString("preauthref", e);
      composeReference("claimResponse", element.getClaimResponse());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimMissingTeethComponent(String name, OralHealthClaim.MissingTeethComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("tooth", element.getTooth());
      composeCoding("reason", element.getReason());
      composeDate("extractiondate", element.getExtractiondateElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimOrthodonticPlanComponent(String name, OralHealthClaim.OrthodonticPlanComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDate("start", element.getStartElement());
      composeMoney("examFee", element.getExamFee());
      composeMoney("diagnosticFee", element.getDiagnosticFee());
      composeMoney("initialPayment", element.getInitialPayment());
      composeInteger("durationMonths", element.getDurationMonthsElement());
      composeInteger("paymentCount", element.getPaymentCountElement());
      composeMoney("periodicPayment", element.getPeriodicPayment());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimItemsComponent(String name, OralHealthClaim.ItemsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequence", element.getSequenceElement());
      composeCoding("type", element.getType());
      composeReference("provider", element.getProvider());
      composeCoding("service", element.getService());
      composeDate("serviceDate", element.getServiceDateElement());
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimal("factor", element.getFactorElement());
      composeDecimal("points", element.getPointsElement());
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
      composeCoding("bodySite", element.getBodySite());
      for (Coding e : element.getSubsite()) 
        composeCoding("subsite", e);
      for (Coding e : element.getModifier()) 
        composeCoding("modifier", e);
      for (OralHealthClaim.DetailComponent e : element.getDetail()) 
        composeOralHealthClaimDetailComponent("detail", e);
      composeOralHealthClaimProsthesisComponent("prosthesis", element.getProsthesis());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimDetailComponent(String name, OralHealthClaim.DetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequence", element.getSequenceElement());
      composeCoding("type", element.getType());
      composeCoding("service", element.getService());
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimal("factor", element.getFactorElement());
      composeDecimal("points", element.getPointsElement());
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
      for (OralHealthClaim.SubDetailComponent e : element.getSubDetail()) 
        composeOralHealthClaimSubDetailComponent("subDetail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimSubDetailComponent(String name, OralHealthClaim.SubDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("sequence", element.getSequenceElement());
      composeCoding("type", element.getType());
      composeCoding("service", element.getService());
      composeQuantity("quantity", element.getQuantity());
      composeMoney("unitPrice", element.getUnitPrice());
      composeDecimal("factor", element.getFactorElement());
      composeDecimal("points", element.getPointsElement());
      composeMoney("net", element.getNet());
      composeCoding("udi", element.getUdi());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOralHealthClaimProsthesisComponent(String name, OralHealthClaim.ProsthesisComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeBoolean("initial", element.getInitialElement());
      composeDate("priorDate", element.getPriorDateElement());
      composeCoding("priorMaterial", element.getPriorMaterial());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("date", element.getDateElement());
      composeReference("subject", element.getSubject());
      composeReference("source", element.getSource());
      composeReference("target", element.getTarget());
      composeType("reason", element.getReason());
      composeReference("authority", element.getAuthority());
      composeOrderOrderWhenComponent("when", element.getWhen());
      for (Reference e : element.getDetail()) 
        composeReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeTiming("schedule", element.getSchedule());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("request", element.getRequest());
      composeDateTime("date", element.getDateElement());
      composeReference("who", element.getWho());
      composeType("authority", element.getAuthority());
      if (element.getCodeElement() != null)
        composeEnumeration("code", element.getCodeElement(), new OrderResponse.OrderOutcomeCodeEnumFactory());
      composeString("description", element.getDescriptionElement());
      for (Reference e : element.getFulfillment()) 
        composeReference("fulfillment", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("name", element.getNameElement());
      composeCodeableConcept("type", element.getType());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      composeReference("partOf", element.getPartOf());
      for (Organization.OrganizationContactComponent e : element.getContact()) 
        composeOrganizationOrganizationContactComponent("contact", e);
      for (Reference e : element.getLocation()) 
        composeReference("location", e);
      composeBoolean("active", element.getActiveElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrganizationOrganizationContactComponent(String name, Organization.OrganizationContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("purpose", element.getPurpose());
      composeHumanName("name", element.getName());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeAddress("address", element.getAddress());
      if (element.getGenderElement() != null)
        composeEnumeration("gender", element.getGenderElement(), new Organization.AdministrativeGenderEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOther(String name, Other element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDate("created", element.getCreatedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (HumanName e : element.getName()) 
        composeHumanName("name", e);
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      if (element.getGenderElement() != null)
        composeEnumeration("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory());
      composeDateTime("birthDate", element.getBirthDateElement());
      composeType("deceased", element.getDeceased());
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      composeCodeableConcept("maritalStatus", element.getMaritalStatus());
      composeType("multipleBirth", element.getMultipleBirth());
      for (Attachment e : element.getPhoto()) 
        composeAttachment("photo", e);
      for (Patient.ContactComponent e : element.getContact()) 
        composePatientContactComponent("contact", e);
      composePatientAnimalComponent("animal", element.getAnimal());
      for (CodeableConcept e : element.getCommunication()) 
        composeCodeableConcept("communication", e);
      for (Reference e : element.getCareProvider()) 
        composeReference("careProvider", e);
      composeReference("managingOrganization", element.getManagingOrganization());
      for (Patient.PatientLinkComponent e : element.getLink()) 
        composePatientPatientLinkComponent("link", e);
      composeBoolean("active", element.getActiveElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getRelationship()) 
        composeCodeableConcept("relationship", e);
      composeHumanName("name", element.getName());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeAddress("address", element.getAddress());
      if (element.getGenderElement() != null)
        composeEnumeration("gender", element.getGenderElement(), new Patient.AdministrativeGenderEnumFactory());
      composeReference("organization", element.getOrganization());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("species", element.getSpecies());
      composeCodeableConcept("breed", element.getBreed());
      composeCodeableConcept("genderStatus", element.getGenderStatus());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatientPatientLinkComponent(String name, Patient.PatientLinkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("other", element.getOther());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Patient.LinkTypeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeHumanName("name", element.getName());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      if (element.getGenderElement() != null)
        composeEnumeration("gender", element.getGenderElement(), new Practitioner.AdministrativeGenderEnumFactory());
      composeDateTime("birthDate", element.getBirthDateElement());
      for (Attachment e : element.getPhoto()) 
        composeAttachment("photo", e);
      composeReference("organization", element.getOrganization());
      for (CodeableConcept e : element.getRole()) 
        composeCodeableConcept("role", e);
      for (CodeableConcept e : element.getSpecialty()) 
        composeCodeableConcept("specialty", e);
      composePeriod("period", element.getPeriod());
      for (Reference e : element.getLocation()) 
        composeReference("location", e);
      for (Practitioner.PractitionerQualificationComponent e : element.getQualification()) 
        composePractitionerPractitionerQualificationComponent("qualification", e);
      for (CodeableConcept e : element.getCommunication()) 
        composeCodeableConcept("communication", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composePeriod("period", element.getPeriod());
      composeReference("issuer", element.getIssuer());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("patient", element.getPatient());
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
        composeProcedureProcedurePerformerComponent("performer", e);
      composePeriod("date", element.getDate());
      composeReference("encounter", element.getEncounter());
      composeString("outcome", element.getOutcomeElement());
      for (Reference e : element.getReport()) 
        composeReference("report", e);
      for (CodeableConcept e : element.getComplication()) 
        composeCodeableConcept("complication", e);
      composeString("followUp", element.getFollowUpElement());
      for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
        composeProcedureProcedureRelatedItemComponent("relatedItem", e);
      composeString("notes", element.getNotesElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Procedure.ProcedureRelationshipTypeEnumFactory());
      composeReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureRequest(String name, ProcedureRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      composeType("timing", element.getTiming());
      composeReference("encounter", element.getEncounter());
      composeReference("performer", element.getPerformer());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new ProcedureRequest.ProcedureRequestStatusEnumFactory());
      if (element.getModeElement() != null)
        composeEnumeration("mode", element.getModeElement(), new ProcedureRequest.ProcedureRequestModeEnumFactory());
      for (StringType e : element.getNotes()) 
        composeString("notes", e);
      composeType("asNeeded", element.getAsNeeded());
      composeDateTime("orderedOn", element.getOrderedOnElement());
      composeReference("orderer", element.getOrderer());
      if (element.getPriorityElement() != null)
        composeEnumeration("priority", element.getPriorityElement(), new ProcedureRequest.ProcedureRequestPriorityEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("url", element.getUrlElement());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("version", element.getVersionElement());
      composeString("name", element.getNameElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Profile.ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeDateTime("date", element.getDateElement());
      composeString("requirements", element.getRequirementsElement());
      composeId("fhirVersion", element.getFhirVersionElement());
      for (Profile.ProfileMappingComponent e : element.getMapping()) 
        composeProfileProfileMappingComponent("mapping", e);
      composeCode("type", element.getTypeElement());
      composeUri("base", element.getBaseElement());
      composeProfileConstraintComponent("snapshot", element.getSnapshot());
      composeProfileConstraintComponent("differential", element.getDifferential());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileMappingComponent(String name, Profile.ProfileMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identity", element.getIdentityElement());
      composeUri("uri", element.getUriElement());
      composeString("name", element.getNameElement());
      composeString("comments", element.getCommentsElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileConstraintComponent(String name, Profile.ConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (ElementDefinition e : element.getElement()) 
        composeElementDefinition("element", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Reference e : element.getTarget()) 
        composeReference("target", e);
      composePeriod("period", element.getPeriod());
      composeInstant("recorded", element.getRecordedElement());
      composeCodeableConcept("reason", element.getReason());
      composeReference("location", element.getLocation());
      for (UriType e : element.getPolicy()) 
        composeUri("policy", e);
      for (Provenance.ProvenanceAgentComponent e : element.getAgent()) 
        composeProvenanceProvenanceAgentComponent("agent", e);
      for (Provenance.ProvenanceEntityComponent e : element.getEntity()) 
        composeProvenanceProvenanceEntityComponent("entity", e);
      composeString("integritySignature", element.getIntegritySignatureElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUri("reference", element.getReferenceElement());
      composeString("display", element.getDisplayElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getRoleElement() != null)
        composeEnumeration("role", element.getRoleElement(), new Provenance.ProvenanceEntityRoleEnumFactory());
      composeCoding("type", element.getType());
      composeUri("reference", element.getReferenceElement());
      composeString("display", element.getDisplayElement());
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("identifier", element.getIdentifierElement());
      for (Extension e : element.getParameter()) 
        composeExtension("parameter", e);
      composeQueryQueryResponseComponent("response", element.getResponse());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponseComponent(String name, Query.QueryResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("identifier", element.getIdentifierElement());
      if (element.getOutcomeElement() != null)
        composeEnumeration("outcome", element.getOutcomeElement(), new Query.QueryOutcomeEnumFactory());
      composeInteger("total", element.getTotalElement());
      for (Extension e : element.getParameter()) 
        composeExtension("parameter", e);
      for (Extension e : element.getFirst()) 
        composeExtension("first", e);
      for (Extension e : element.getPrevious()) 
        composeExtension("previous", e);
      for (Extension e : element.getNext()) 
        composeExtension("next", e);
      for (Extension e : element.getLast()) 
        composeExtension("last", e);
      for (Reference e : element.getReference()) 
        composeReference("reference", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("version", element.getVersionElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Questionnaire.QuestionnaireStatusEnumFactory());
      composeDateTime("date", element.getDateElement());
      composeString("publisher", element.getPublisherElement());
      composeQuestionnaireGroupComponent("group", element.getGroup());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkIdElement());
      composeString("title", element.getTitleElement());
      for (Coding e : element.getConcept()) 
        composeCoding("concept", e);
      composeString("text", element.getTextElement());
      composeBoolean("required", element.getRequiredElement());
      composeBoolean("repeats", element.getRepeatsElement());
      for (Questionnaire.GroupComponent e : element.getGroup()) 
        composeQuestionnaireGroupComponent("group", e);
      for (Questionnaire.QuestionComponent e : element.getQuestion()) 
        composeQuestionnaireQuestionComponent("question", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireQuestionComponent(String name, Questionnaire.QuestionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkIdElement());
      for (Coding e : element.getConcept()) 
        composeCoding("concept", e);
      composeString("text", element.getTextElement());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Questionnaire.AnswerFormatEnumFactory());
      composeBoolean("required", element.getRequiredElement());
      composeBoolean("repeats", element.getRepeatsElement());
      composeReference("options", element.getOptions());
      for (Questionnaire.GroupComponent e : element.getGroup()) 
        composeQuestionnaireGroupComponent("group", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswers(String name, QuestionnaireAnswers element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("questionnaire", element.getQuestionnaire());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory());
      composeReference("subject", element.getSubject());
      composeReference("author", element.getAuthor());
      composeDateTime("authored", element.getAuthoredElement());
      composeReference("source", element.getSource());
      composeReference("encounter", element.getEncounter());
      composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkIdElement());
      composeString("title", element.getTitleElement());
      composeString("text", element.getTextElement());
      composeReference("subject", element.getSubject());
      for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
        composeQuestionnaireAnswersGroupComponent("group", e);
      for (QuestionnaireAnswers.QuestionComponent e : element.getQuestion()) 
        composeQuestionnaireAnswersQuestionComponent("question", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswersQuestionComponent(String name, QuestionnaireAnswers.QuestionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkIdElement());
      composeString("text", element.getTextElement());
      for (QuestionnaireAnswers.QuestionAnswerComponent e : element.getAnswer()) 
        composeQuestionnaireAnswersQuestionAnswerComponent("answer", e);
      for (QuestionnaireAnswers.GroupComponent e : element.getGroup()) 
        composeQuestionnaireAnswersGroupComponent("group", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswersQuestionAnswerComponent(String name, QuestionnaireAnswers.QuestionAnswerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeType("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeReferralRequest(String name, ReferralRequest element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new ReferralRequest.ReferralstatusEnumFactory());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("specialty", element.getSpecialty());
      composeCodeableConcept("priority", element.getPriority());
      composeReference("patient", element.getPatient());
      composeReference("requester", element.getRequester());
      for (Reference e : element.getRecipient()) 
        composeReference("recipient", e);
      composeReference("encounter", element.getEncounter());
      composeDateTime("dateSent", element.getDateSentElement());
      composeCodeableConcept("reason", element.getReason());
      composeString("description", element.getDescriptionElement());
      for (CodeableConcept e : element.getServiceRequested()) 
        composeCodeableConcept("serviceRequested", e);
      for (Reference e : element.getSupportingInformation()) 
        composeReference("supportingInformation", e);
      composePeriod("fulfillmentTime", element.getFulfillmentTime());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeReference("patient", element.getPatient());
      composeCodeableConcept("relationship", element.getRelationship());
      composeHumanName("name", element.getName());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      if (element.getGenderElement() != null)
        composeEnumeration("gender", element.getGenderElement(), new RelatedPerson.AdministrativeGenderEnumFactory());
      composeAddress("address", element.getAddress());
      for (Attachment e : element.getPhoto()) 
        composeAttachment("photo", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRiskAssessment(String name, RiskAssessment element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeReference("subject", element.getSubject());
      composeDateTime("date", element.getDateElement());
      composeReference("condition", element.getCondition());
      composeReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("method", element.getMethod());
      for (Reference e : element.getBasis()) 
        composeReference("basis", e);
      for (RiskAssessment.RiskAssessmentPredictionComponent e : element.getPrediction()) 
        composeRiskAssessmentRiskAssessmentPredictionComponent("prediction", e);
      composeString("mitigation", element.getMitigationElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRiskAssessmentRiskAssessmentPredictionComponent(String name, RiskAssessment.RiskAssessmentPredictionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("probability", element.getProbability());
      composeDecimal("relativeRisk", element.getRelativeRiskElement());
      composeType("when", element.getWhen());
      composeString("rationale", element.getRationaleElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSearchParameter(String name, SearchParameter element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("url", element.getUrlElement());
      composeString("name", element.getNameElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("requirements", element.getRequirementsElement());
      composeCode("base", element.getBaseElement());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new SearchParameter.SearchParamTypeEnumFactory());
      composeString("description", element.getDescriptionElement());
      composeString("xpath", element.getXpathElement());
      for (CodeType e : element.getTarget()) 
        composeCode("target", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEvent(String name, SecurityEvent element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeSecurityEventSecurityEventEventComponent("event", element.getEvent());
      for (SecurityEvent.SecurityEventParticipantComponent e : element.getParticipant()) 
        composeSecurityEventSecurityEventParticipantComponent("participant", e);
      composeSecurityEventSecurityEventSourceComponent("source", element.getSource());
      for (SecurityEvent.SecurityEventObjectComponent e : element.getObject()) 
        composeSecurityEventSecurityEventObjectComponent("object", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventEventComponent(String name, SecurityEvent.SecurityEventEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getSubtype()) 
        composeCodeableConcept("subtype", e);
      if (element.getActionElement() != null)
        composeEnumeration("action", element.getActionElement(), new SecurityEvent.SecurityEventActionEnumFactory());
      composeInstant("dateTime", element.getDateTimeElement());
      if (element.getOutcomeElement() != null)
        composeEnumeration("outcome", element.getOutcomeElement(), new SecurityEvent.SecurityEventOutcomeEnumFactory());
      composeString("outcomeDesc", element.getOutcomeDescElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventParticipantComponent(String name, SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (CodeableConcept e : element.getRole()) 
        composeCodeableConcept("role", e);
      composeReference("reference", element.getReference());
      composeString("userId", element.getUserIdElement());
      composeString("altId", element.getAltIdElement());
      composeString("name", element.getNameElement());
      composeBoolean("requestor", element.getRequestorElement());
      composeCoding("media", element.getMedia());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("identifier", element.getIdentifierElement());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new SecurityEvent.NetworkTypeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("site", element.getSiteElement());
      composeString("identifier", element.getIdentifierElement());
      for (Coding e : element.getType()) 
        composeCoding("type", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventObjectComponent(String name, SecurityEvent.SecurityEventObjectComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeReference("reference", element.getReference());
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new SecurityEvent.ObjectTypeEnumFactory());
      if (element.getRoleElement() != null)
        composeEnumeration("role", element.getRoleElement(), new SecurityEvent.ObjectRoleEnumFactory());
      if (element.getLifecycleElement() != null)
        composeEnumeration("lifecycle", element.getLifecycleElement(), new SecurityEvent.ObjectLifecycleEnumFactory());
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeString("name", element.getNameElement());
      composeString("description", element.getDescriptionElement());
      composeBase64Binary("query", element.getQueryElement());
      for (SecurityEvent.SecurityEventObjectDetailComponent e : element.getDetail()) 
        composeSecurityEventSecurityEventObjectDetailComponent("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventObjectDetailComponent(String name, SecurityEvent.SecurityEventObjectDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("type", element.getTypeElement());
      composeBase64Binary("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSlot(String name, Slot element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeReference("availability", element.getAvailability());
      if (element.getFreeBusyTypeElement() != null)
        composeEnumeration("freeBusyType", element.getFreeBusyTypeElement(), new Slot.SlotstatusEnumFactory());
      composeInstant("start", element.getStartElement());
      composeInstant("end", element.getEndElement());
      composeBoolean("overbooked", element.getOverbookedElement());
      composeString("comment", element.getCommentElement());
      composeDateTime("lastModified", element.getLastModifiedElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      for (Specimen.SpecimenSourceComponent e : element.getSource()) 
        composeSpecimenSpecimenSourceComponent("source", e);
      composeReference("subject", element.getSubject());
      composeIdentifier("accessionIdentifier", element.getAccessionIdentifier());
      composeDateTime("receivedTime", element.getReceivedTimeElement());
      composeSpecimenSpecimenCollectionComponent("collection", element.getCollection());
      for (Specimen.SpecimenTreatmentComponent e : element.getTreatment()) 
        composeSpecimenSpecimenTreatmentComponent("treatment", e);
      for (Specimen.SpecimenContainerComponent e : element.getContainer()) 
        composeSpecimenSpecimenContainerComponent("container", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimenSpecimenSourceComponent(String name, Specimen.SpecimenSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getRelationshipElement() != null)
        composeEnumeration("relationship", element.getRelationshipElement(), new Specimen.HierarchicalRelationshipTypeEnumFactory());
      for (Reference e : element.getTarget()) 
        composeReference("target", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeReference("collector", element.getCollector());
      for (StringType e : element.getComment()) 
        composeString("comment", e);
      composeType("collected", element.getCollected());
      composeQuantity("quantity", element.getQuantity());
      composeCodeableConcept("method", element.getMethod());
      composeCodeableConcept("sourceSite", element.getSourceSite());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimenSpecimenTreatmentComponent(String name, Specimen.SpecimenTreatmentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("description", element.getDescriptionElement());
      composeCodeableConcept("procedure", element.getProcedure());
      for (Reference e : element.getAdditive()) 
        composeReference("additive", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimenSpecimenContainerComponent(String name, Specimen.SpecimenContainerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("description", element.getDescriptionElement());
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeType("additive", element.getAdditive());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeString("criteria", element.getCriteriaElement());
      for (ContactPoint e : element.getContact()) 
        composeContactPoint("contact", e);
      composeString("reason", element.getReasonElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Subscription.SubscriptionStatusEnumFactory());
      composeString("error", element.getErrorElement());
      composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      composeInstant("end", element.getEndElement());
      for (Subscription.SubscriptionTagComponent e : element.getTag()) 
        composeSubscriptionSubscriptionTagComponent("tag", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubscriptionSubscriptionChannelComponent(String name, Subscription.SubscriptionChannelComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getTypeElement() != null)
        composeEnumeration("type", element.getTypeElement(), new Subscription.SubscriptionChannelTypeEnumFactory());
      composeUri("url", element.getUrlElement());
      composeString("payload", element.getPayloadElement());
      composeString("header", element.getHeaderElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubscriptionSubscriptionTagComponent(String name, Subscription.SubscriptionTagComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("term", element.getTermElement());
      composeUri("scheme", element.getSchemeElement());
      composeString("description", element.getDescriptionElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("description", element.getDescriptionElement());
      composeSubstanceSubstanceInstanceComponent("instance", element.getInstance());
      for (Substance.SubstanceIngredientComponent e : element.getIngredient()) 
        composeSubstanceSubstanceIngredientComponent("ingredient", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubstanceSubstanceInstanceComponent(String name, Substance.SubstanceInstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTime("expiry", element.getExpiryElement());
      composeQuantity("quantity", element.getQuantity());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubstanceSubstanceIngredientComponent(String name, Substance.SubstanceIngredientComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeRatio("quantity", element.getQuantity());
      composeReference("substance", element.getSubstance());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeCodeableConcept("kind", element.getKind());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Supply.ValuesetSupplyStatusEnumFactory());
      composeReference("orderedItem", element.getOrderedItem());
      composeReference("patient", element.getPatient());
      for (Supply.SupplyDispenseComponent e : element.getDispense()) 
        composeSupplySupplyDispenseComponent("dispense", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSupplySupplyDispenseComponent(String name, Supply.SupplyDispenseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new Supply.ValuesetSupplyDispenseStatusEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeReference("suppliedItem", element.getSuppliedItem());
      composeReference("supplier", element.getSupplier());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeReference("destination", element.getDestination());
      for (Reference e : element.getReceiver()) 
        composeReference("receiver", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSupportingDocumentation(String name, SupportingDocumentation element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCoding("ruleset", element.getRuleset());
      composeCoding("originalRuleset", element.getOriginalRuleset());
      composeDate("date", element.getDateElement());
      composeReference("target", element.getTarget());
      composeReference("provider", element.getProvider());
      composeReference("organization", element.getOrganization());
      composeIdentifier("requestIdentifier", element.getRequestIdentifier());
      composeReference("request", element.getRequest());
      composeIdentifier("responseIdentifier", element.getResponseIdentifier());
      composeReference("response", element.getResponse());
      composeReference("author", element.getAuthor());
      composeReference("subject", element.getSubject());
      for (SupportingDocumentation.SupportingDocumentationDetailComponent e : element.getDetail()) 
        composeSupportingDocumentationSupportingDocumentationDetailComponent("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSupportingDocumentationSupportingDocumentationDetailComponent(String name, SupportingDocumentation.SupportingDocumentationDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("linkId", element.getLinkIdElement());
      composeType("content", element.getContent());
      composeDateTime("dateTime", element.getDateTimeElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      composeDomainResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeDomainResourceElements(element);
      composeUri("identifier", element.getIdentifierElement());
      composeString("version", element.getVersionElement());
      composeString("name", element.getNameElement());
      composeString("purpose", element.getPurposeElement());
      composeBoolean("immutable", element.getImmutableElement());
      composeString("publisher", element.getPublisherElement());
      for (ContactPoint e : element.getTelecom()) 
        composeContactPoint("telecom", e);
      composeString("description", element.getDescriptionElement());
      composeString("copyright", element.getCopyrightElement());
      if (element.getStatusElement() != null)
        composeEnumeration("status", element.getStatusElement(), new ValueSet.ValuesetStatusEnumFactory());
      composeBoolean("experimental", element.getExperimentalElement());
      composeBoolean("extensible", element.getExtensibleElement());
      composeDateTime("date", element.getDateElement());
      composeDate("stableDate", element.getStableDateElement());
      composeValueSetValueSetDefineComponent("define", element.getDefine());
      composeValueSetValueSetComposeComponent("compose", element.getCompose());
      composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("system", element.getSystemElement());
      composeString("version", element.getVersionElement());
      composeBoolean("caseSensitive", element.getCaseSensitiveElement());
      for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
        composeValueSetConceptDefinitionComponent("concept", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptDefinitionComponent(String name, ValueSet.ConceptDefinitionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("code", element.getCodeElement());
      composeBoolean("abstract", element.getAbstractElement());
      composeString("display", element.getDisplayElement());
      composeString("definition", element.getDefinitionElement());
      for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
        composeValueSetConceptDefinitionDesignationComponent("designation", e);
      for (ValueSet.ConceptDefinitionComponent e : element.getConcept()) 
        composeValueSetConceptDefinitionComponent("concept", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptDefinitionDesignationComponent(String name, ValueSet.ConceptDefinitionDesignationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("language", element.getLanguageElement());
      composeCoding("use", element.getUse());
      composeString("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (UriType e : element.getImport()) 
        composeUri("import", e);
      for (ValueSet.ConceptSetComponent e : element.getInclude()) 
        composeValueSetConceptSetComponent("include", e);
      for (ValueSet.ConceptSetComponent e : element.getExclude()) 
        composeValueSetConceptSetComponent("exclude", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptSetComponent(String name, ValueSet.ConceptSetComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("system", element.getSystemElement());
      composeString("version", element.getVersionElement());
      for (ValueSet.ConceptReferenceComponent e : element.getConcept()) 
        composeValueSetConceptReferenceComponent("concept", e);
      for (ValueSet.ConceptSetFilterComponent e : element.getFilter()) 
        composeValueSetConceptSetFilterComponent("filter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptReferenceComponent(String name, ValueSet.ConceptReferenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("code", element.getCodeElement());
      composeString("display", element.getDisplayElement());
      for (ValueSet.ConceptDefinitionDesignationComponent e : element.getDesignation()) 
        composeValueSetConceptDefinitionDesignationComponent("designation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("property", element.getPropertyElement());
      if (element.getOpElement() != null)
        composeEnumeration("op", element.getOpElement(), new ValueSet.FilterOperatorEnumFactory());
      composeCode("value", element.getValueElement());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTime("timestamp", element.getTimestampElement());
      for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
        composeValueSetValueSetExpansionContainsComponent("contains", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("system", element.getSystemElement());
      composeBoolean("abstract", element.getAbstractElement());
      composeString("version", element.getVersionElement());
      composeCode("code", element.getCodeElement());
      composeString("display", element.getDisplayElement());
      for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
        composeValueSetValueSetExpansionContainsComponent("contains", e);
      xml.close(FHIR_NS, name);
    }
  }

  @Override
  protected void composeResource(Resource resource) throws Exception {
    if (resource instanceof Parameters)
      composeParameters("Parameters", (Parameters)resource);
    else if (resource instanceof Alert)
      composeAlert("Alert", (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance("AllergyIntolerance", (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment("Appointment", (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse("AppointmentResponse", (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability("Availability", (Availability)resource);
    else if (resource instanceof Basic)
      composeBasic("Basic", (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else if (resource instanceof Bundle)
      composeBundle("Bundle", (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse("ClaimResponse", (ClaimResponse)resource);
    else if (resource instanceof CommunicationRequest)
      composeCommunicationRequest("CommunicationRequest", (CommunicationRequest)resource);
    else if (resource instanceof Composition)
      composeComposition("Composition", (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap("ConceptMap", (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition("Condition", (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance("Conformance", (Conformance)resource);
    else if (resource instanceof Contract)
      composeContract("Contract", (Contract)resource);
    else if (resource instanceof Contraindication)
      composeContraindication("Contraindication", (Contraindication)resource);
    else if (resource instanceof Coverage)
      composeCoverage("Coverage", (Coverage)resource);
    else if (resource instanceof DataElement)
      composeDataElement("DataElement", (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice("Device", (Device)resource);
    else if (resource instanceof DeviceComponent)
      composeDeviceComponent("DeviceComponent", (DeviceComponent)resource);
    else if (resource instanceof DeviceUseRequest)
      composeDeviceUseRequest("DeviceUseRequest", (DeviceUseRequest)resource);
    else if (resource instanceof DeviceUseStatement)
      composeDeviceUseStatement("DeviceUseStatement", (DeviceUseStatement)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder("DiagnosticOrder", (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport("DiagnosticReport", (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest("DocumentManifest", (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference("DocumentReference", (DocumentReference)resource);
    else if (resource instanceof Eligibility)
      composeEligibility("Eligibility", (Eligibility)resource);
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit("ExplanationOfBenefit", (ExplanationOfBenefit)resource);
    else if (resource instanceof ExtensionDefinition)
      composeExtensionDefinition("ExtensionDefinition", (ExtensionDefinition)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService("HealthcareService", (HealthcareService)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy("ImagingStudy", (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization("Immunization", (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation("ImmunizationRecommendation", (ImmunizationRecommendation)resource);
    else if (resource instanceof List_)
      composeList_("List", (List_)resource);
    else if (resource instanceof Location)
      composeLocation("Location", (Location)resource);
    else if (resource instanceof Media)
      composeMedia("Media", (Media)resource);
    else if (resource instanceof Medication)
      composeMedication("Medication", (Medication)resource);
    else if (resource instanceof MedicationAdministration)
      composeMedicationAdministration("MedicationAdministration", (MedicationAdministration)resource);
    else if (resource instanceof MedicationDispense)
      composeMedicationDispense("MedicationDispense", (MedicationDispense)resource);
    else if (resource instanceof MedicationPrescription)
      composeMedicationPrescription("MedicationPrescription", (MedicationPrescription)resource);
    else if (resource instanceof MedicationStatement)
      composeMedicationStatement("MedicationStatement", (MedicationStatement)resource);
    else if (resource instanceof MessageHeader)
      composeMessageHeader("MessageHeader", (MessageHeader)resource);
    else if (resource instanceof NamingSystem)
      composeNamingSystem("NamingSystem", (NamingSystem)resource);
    else if (resource instanceof NutritionOrder)
      composeNutritionOrder("NutritionOrder", (NutritionOrder)resource);
    else if (resource instanceof Observation)
      composeObservation("Observation", (Observation)resource);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition("OperationDefinition", (OperationDefinition)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome("OperationOutcome", (OperationOutcome)resource);
    else if (resource instanceof OralHealthClaim)
      composeOralHealthClaim("OralHealthClaim", (OralHealthClaim)resource);
    else if (resource instanceof Order)
      composeOrder("Order", (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse("OrderResponse", (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization("Organization", (Organization)resource);
    else if (resource instanceof Other)
      composeOther("Other", (Other)resource);
    else if (resource instanceof Patient)
      composePatient("Patient", (Patient)resource);
    else if (resource instanceof Practitioner)
      composePractitioner("Practitioner", (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure("Procedure", (Procedure)resource);
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest("ProcedureRequest", (ProcedureRequest)resource);
    else if (resource instanceof Profile)
      composeProfile("Profile", (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance("Provenance", (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery("Query", (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire("Questionnaire", (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers("QuestionnaireAnswers", (QuestionnaireAnswers)resource);
    else if (resource instanceof ReferralRequest)
      composeReferralRequest("ReferralRequest", (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson("RelatedPerson", (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment("RiskAssessment", (RiskAssessment)resource);
    else if (resource instanceof SearchParameter)
      composeSearchParameter("SearchParameter", (SearchParameter)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent("SecurityEvent", (SecurityEvent)resource);
    else if (resource instanceof Slot)
      composeSlot("Slot", (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen("Specimen", (Specimen)resource);
    else if (resource instanceof Subscription)
      composeSubscription("Subscription", (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance("Substance", (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply("Supply", (Supply)resource);
    else if (resource instanceof SupportingDocumentation)
      composeSupportingDocumentation("SupportingDocumentation", (SupportingDocumentation)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeResource(String name, Resource resource) throws Exception {
    if (resource instanceof Parameters)
      composeParameters(name, (Parameters)resource);
    else if (resource instanceof Alert)
      composeAlert(name, (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(name, (AllergyIntolerance)resource);
    else if (resource instanceof Appointment)
      composeAppointment(name, (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse(name, (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability(name, (Availability)resource);
    else if (resource instanceof Basic)
      composeBasic(name, (Basic)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else if (resource instanceof Bundle)
      composeBundle(name, (Bundle)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan(name, (CarePlan)resource);
    else if (resource instanceof ClaimResponse)
      composeClaimResponse(name, (ClaimResponse)resource);
    else if (resource instanceof CommunicationRequest)
      composeCommunicationRequest(name, (CommunicationRequest)resource);
    else if (resource instanceof Composition)
      composeComposition(name, (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap(name, (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition(name, (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance(name, (Conformance)resource);
    else if (resource instanceof Contract)
      composeContract(name, (Contract)resource);
    else if (resource instanceof Contraindication)
      composeContraindication(name, (Contraindication)resource);
    else if (resource instanceof Coverage)
      composeCoverage(name, (Coverage)resource);
    else if (resource instanceof DataElement)
      composeDataElement(name, (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice(name, (Device)resource);
    else if (resource instanceof DeviceComponent)
      composeDeviceComponent(name, (DeviceComponent)resource);
    else if (resource instanceof DeviceUseRequest)
      composeDeviceUseRequest(name, (DeviceUseRequest)resource);
    else if (resource instanceof DeviceUseStatement)
      composeDeviceUseStatement(name, (DeviceUseStatement)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder(name, (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport(name, (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest(name, (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference(name, (DocumentReference)resource);
    else if (resource instanceof Eligibility)
      composeEligibility(name, (Eligibility)resource);
    else if (resource instanceof Encounter)
      composeEncounter(name, (Encounter)resource);
    else if (resource instanceof ExplanationOfBenefit)
      composeExplanationOfBenefit(name, (ExplanationOfBenefit)resource);
    else if (resource instanceof ExtensionDefinition)
      composeExtensionDefinition(name, (ExtensionDefinition)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory(name, (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup(name, (Group)resource);
    else if (resource instanceof HealthcareService)
      composeHealthcareService(name, (HealthcareService)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy(name, (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization(name, (Immunization)resource);
    else if (resource instanceof ImmunizationRecommendation)
      composeImmunizationRecommendation(name, (ImmunizationRecommendation)resource);
    else if (resource instanceof List_)
      composeList_(name, (List_)resource);
    else if (resource instanceof Location)
      composeLocation(name, (Location)resource);
    else if (resource instanceof Media)
      composeMedia(name, (Media)resource);
    else if (resource instanceof Medication)
      composeMedication(name, (Medication)resource);
    else if (resource instanceof MedicationAdministration)
      composeMedicationAdministration(name, (MedicationAdministration)resource);
    else if (resource instanceof MedicationDispense)
      composeMedicationDispense(name, (MedicationDispense)resource);
    else if (resource instanceof MedicationPrescription)
      composeMedicationPrescription(name, (MedicationPrescription)resource);
    else if (resource instanceof MedicationStatement)
      composeMedicationStatement(name, (MedicationStatement)resource);
    else if (resource instanceof MessageHeader)
      composeMessageHeader(name, (MessageHeader)resource);
    else if (resource instanceof NamingSystem)
      composeNamingSystem(name, (NamingSystem)resource);
    else if (resource instanceof NutritionOrder)
      composeNutritionOrder(name, (NutritionOrder)resource);
    else if (resource instanceof Observation)
      composeObservation(name, (Observation)resource);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition(name, (OperationDefinition)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome(name, (OperationOutcome)resource);
    else if (resource instanceof OralHealthClaim)
      composeOralHealthClaim(name, (OralHealthClaim)resource);
    else if (resource instanceof Order)
      composeOrder(name, (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse(name, (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization(name, (Organization)resource);
    else if (resource instanceof Other)
      composeOther(name, (Other)resource);
    else if (resource instanceof Patient)
      composePatient(name, (Patient)resource);
    else if (resource instanceof Practitioner)
      composePractitioner(name, (Practitioner)resource);
    else if (resource instanceof Procedure)
      composeProcedure(name, (Procedure)resource);
    else if (resource instanceof ProcedureRequest)
      composeProcedureRequest(name, (ProcedureRequest)resource);
    else if (resource instanceof Profile)
      composeProfile(name, (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance(name, (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery(name, (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire(name, (Questionnaire)resource);
    else if (resource instanceof QuestionnaireAnswers)
      composeQuestionnaireAnswers(name, (QuestionnaireAnswers)resource);
    else if (resource instanceof ReferralRequest)
      composeReferralRequest(name, (ReferralRequest)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(name, (RelatedPerson)resource);
    else if (resource instanceof RiskAssessment)
      composeRiskAssessment(name, (RiskAssessment)resource);
    else if (resource instanceof SearchParameter)
      composeSearchParameter(name, (SearchParameter)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent(name, (SecurityEvent)resource);
    else if (resource instanceof Slot)
      composeSlot(name, (Slot)resource);
    else if (resource instanceof Specimen)
      composeSpecimen(name, (Specimen)resource);
    else if (resource instanceof Subscription)
      composeSubscription(name, (Subscription)resource);
    else if (resource instanceof Substance)
      composeSubstance(name, (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply(name, (Supply)resource);
    else if (resource instanceof SupportingDocumentation)
      composeSupportingDocumentation(name, (SupportingDocumentation)resource);
    else if (resource instanceof ValueSet)
      composeValueSet(name, (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeType(String prefix, Type type) throws Exception {
    if (type == null)
      ;
    else if (type instanceof Age)
       composeAge(prefix+"Age", (Age) type);
    else if (type instanceof Count)
       composeCount(prefix+"Count", (Count) type);
    else if (type instanceof Money)
       composeMoney(prefix+"Money", (Money) type);
    else if (type instanceof Distance)
       composeDistance(prefix+"Distance", (Distance) type);
    else if (type instanceof Duration)
       composeDuration(prefix+"Duration", (Duration) type);
    else if (type instanceof Period)
       composePeriod(prefix+"Period", (Period) type);
    else if (type instanceof Coding)
       composeCoding(prefix+"Coding", (Coding) type);
    else if (type instanceof Range)
       composeRange(prefix+"Range", (Range) type);
    else if (type instanceof Quantity)
       composeQuantity(prefix+"Quantity", (Quantity) type);
    else if (type instanceof Attachment)
       composeAttachment(prefix+"Attachment", (Attachment) type);
    else if (type instanceof Ratio)
       composeRatio(prefix+"Ratio", (Ratio) type);
    else if (type instanceof SampledData)
       composeSampledData(prefix+"SampledData", (SampledData) type);
    else if (type instanceof Reference)
       composeReference(prefix+"Reference", (Reference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
    else if (type instanceof ElementDefinition)
       composeElementDefinition(prefix+"ElementDefinition", (ElementDefinition) type);
    else if (type instanceof Timing)
       composeTiming(prefix+"Timing", (Timing) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
    else if (type instanceof ContactPoint)
       composeContactPoint(prefix+"ContactPoint", (ContactPoint) type);
    else if (type instanceof IntegerType)
       composeInteger(prefix+"Integer", (IntegerType) type);
    else if (type instanceof DateTimeType)
       composeDateTime(prefix+"DateTime", (DateTimeType) type);
    else if (type instanceof CodeType)
       composeCode(prefix+"Code", (CodeType) type);
    else if (type instanceof DateType)
       composeDate(prefix+"Date", (DateType) type);
    else if (type instanceof DecimalType)
       composeDecimal(prefix+"Decimal", (DecimalType) type);
    else if (type instanceof UriType)
       composeUri(prefix+"Uri", (UriType) type);
    else if (type instanceof IdType)
       composeId(prefix+"Id", (IdType) type);
    else if (type instanceof Base64BinaryType)
       composeBase64Binary(prefix+"Base64Binary", (Base64BinaryType) type);
    else if (type instanceof TimeType)
       composeTime(prefix+"Time", (TimeType) type);
    else if (type instanceof OidType)
       composeOid(prefix+"Oid", (OidType) type);
    else if (type instanceof StringType)
       composeString(prefix+"String", (StringType) type);
    else if (type instanceof BooleanType)
       composeBoolean(prefix+"Boolean", (BooleanType) type);
    else if (type instanceof UuidType)
       composeUuid(prefix+"Uuid", (UuidType) type);
    else if (type instanceof InstantType)
       composeInstant(prefix+"Instant", (InstantType) type);
    else
      throw new Exception("Unhanded type");
  }

}

