package org.hl7.fhir.instance.formats;

/*
  Copyright (c) 2011-2013, HL7, Inc.
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

// Generated on Sat, Jun 1, 2013 09:23+1000 for FHIR v0.09

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.utilities.Utilities;
import java.net.*;
import java.math.*;

public class XmlComposer extends XmlComposerBase {

  private void composeElementElements(Element element) throws Exception {
    for (Extension e : element.getExtensions()) {
      composeExtension("extension", e);
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

  private void composeInteger(String name, Integer value) throws Exception {
    if (value != null) {
      composeElementAttributes(value);
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDateTime(String name, DateTime value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeCode(String name, Code value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDate(String name, Date value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeDecimal(String name, Decimal value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeUri(String name, Uri value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeId(String name, Id value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeBase64Binary(String name, Base64Binary value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeOid(String name, Oid value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeString(String name, String_ value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeBoolean(String name, Boolean value) throws Exception {
    if (value != null) {
      composeElementAttributes(value);
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeUuid(String name, Uuid value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      composeElementAttributes(value);
      if (value.getValue() != null) 
        xml.attribute("value", toString(value.getValue()));
        
      xml.open(FHIR_NS, name);
      composeElementElements(value);
      xml.close(FHIR_NS, name);
    }    
  }    

  private void composeInstant(String name, Instant value) throws Exception {
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
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("url", element.getUrl());
      composeBoolean("isModifier", element.getIsModifier());
      composeType("value", element.getValue());
      for (Extension e : element.getExtension()) 
        composeExtension("extension", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Narrative().new NarrativeStatusEnumFactory());
      composeXhtml("div", element.getDiv());
      for (Narrative.NarrativeBlobComponent e : element.getBlob()) 
        composeNarrativeNarrativeBlobComponent("blob", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNarrativeNarrativeBlobComponent(String name, Narrative.NarrativeBlobComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("mimeType", element.getMimeType());
      composeBase64Binary("content", element.getContent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDateTime("start", element.getStart());
      composeDateTime("end", element.getEnd());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
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
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Quantity().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeChoice(String name, Choice element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCode());
      for (Choice.ChoiceOptionComponent e : element.getOption()) 
        composeChoiceChoiceOptionComponent("option", e);
      composeBoolean("isOrdered", element.getIsOrdered());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeChoiceChoiceOptionComponent(String name, Choice.ChoiceOptionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("contentType", element.getContentType());
      composeCode("language", element.getLanguage());
      composeBase64Binary("data", element.getData());
      composeUri("url", element.getUrl());
      composeInteger("size", element.getSize());
      composeBase64Binary("hash", element.getHash());
      composeString("title", element.getTitle());
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

  private void composeArray(String name, Array element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeQuantity("origin", element.getOrigin());
      composeDecimal("period", element.getPeriod());
      composeDecimal("factor", element.getFactor());
      composeDecimal("lowerLimit", element.getLowerLimit());
      composeDecimal("upperLimit", element.getUpperLimit());
      composeInteger("dimensions", element.getDimensions());
      composeString("data", element.getData());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeResourceReference(String name, ResourceReference element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("type", element.getType());
      composeString("reference", element.getReference());
      composeString("display", element.getDisplay());
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
      composeString("text", element.getText());
      composeString("primary", element.getPrimary());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Identifier().new IdentifierUseEnumFactory());
      composeString("label", element.getLabel());
      composeUri("system", element.getSystem());
      composeString("key", element.getKey());
      composePeriod("period", element.getPeriod());
      composeResourceReference("assigner", element.getAssigner());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Age().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Count().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Money().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Distance().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Duration().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSchedule(String name, Schedule element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Period e : element.getEvent()) 
        composePeriod("event", e);
      composeScheduleScheduleRepeatComponent("repeat", element.getRepeat());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeScheduleScheduleRepeatComponent(String name, Schedule.ScheduleRepeatComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("frequency", element.getFrequency());
      if (element.getWhen() != null)
        composeEnumeration("when", element.getWhen(), new Schedule().new EventTimingEnumFactory());
      composeDecimal("duration", element.getDuration());
      if (element.getUnits() != null)
        composeEnumeration("units", element.getUnits(), new Schedule().new UnitsOfTimeEnumFactory());
      composeInteger("count", element.getCount());
      composeDateTime("end", element.getEnd());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContact(String name, Contact element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getSystem() != null)
        composeEnumeration("system", element.getSystem(), new Contact().new ContactSystemEnumFactory());
      composeString("value", element.getValue());
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Contact().new ContactUseEnumFactory());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Address().new AddressUseEnumFactory());
      composeString("text", element.getText());
      for (String_ e : element.getLine()) 
        composeString("line", e);
      composeString("city", element.getCity());
      composeString("state", element.getState());
      composeString("zip", element.getZip());
      composeString("country", element.getCountry());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new HumanName().new NameUseEnumFactory());
      composeString("text", element.getText());
      for (String_ e : element.getFamily()) 
        composeString("family", e);
      for (String_ e : element.getGiven()) 
        composeString("given", e);
      for (String_ e : element.getPrefix()) 
        composeString("prefix", e);
      for (String_ e : element.getSuffix()) 
        composeString("suffix", e);
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDemographics(String name, Demographics element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (HumanName e : element.getName()) 
        composeHumanName("name", e);
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeCoding("gender", element.getGender());
      composeDateTime("birthDate", element.getBirthDate());
      composeBoolean("deceased", element.getDeceased());
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      for (ResourceReference e : element.getPhoto()) 
        composeResourceReference("photo", e);
      composeCodeableConcept("maritalStatus", element.getMaritalStatus());
      for (Demographics.DemographicsLanguageComponent e : element.getLanguage()) 
        composeDemographicsDemographicsLanguageComponent("language", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDemographicsDemographicsLanguageComponent(String name, Demographics.DemographicsLanguageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("language", element.getLanguage());
      composeCodeableConcept("mode", element.getMode());
      composeCodeableConcept("proficiencyLevel", element.getProficiencyLevel());
      composeBoolean("preference", element.getPreference());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeResourceAttributes(Resource element) throws Exception {
    composeElementAttributes(element);
  }

  private void composeResourceElements(Resource element) throws Exception {
    composeElementElements(element);
    composeCode("language", element.getLanguage());
    composeNarrative("text", element.getText());
    for (Resource r : element.getContained()) {
      if (r.getXmlId() == null)
        throw new Exception("Contained Resource has no id - one must be assigned"); // we can't assign one here - what points to it?
      xml.open(FHIR_NS, "contained");
      composeResource(r);
      xml.close(FHIR_NS, "contained");
    }
  }

  private void composeAdverseReaction(String name, AdverseReaction element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeDateTime("reactionDate", element.getReactionDate());
      composeResourceReference("subject", element.getSubject());
      composeBoolean("didNotOccurFlag", element.getDidNotOccurFlag());
      composeResourceReference("recorder", element.getRecorder());
      for (AdverseReaction.AdverseReactionSymptomComponent e : element.getSymptom()) 
        composeAdverseReactionAdverseReactionSymptomComponent("symptom", e);
      for (AdverseReaction.AdverseReactionExposureComponent e : element.getExposure()) 
        composeAdverseReactionAdverseReactionExposureComponent("exposure", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAdverseReactionAdverseReactionSymptomComponent(String name, AdverseReaction.AdverseReactionSymptomComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new AdverseReaction().new ReactionSeverityEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAdverseReactionAdverseReactionExposureComponent(String name, AdverseReaction.AdverseReactionExposureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDateTime("exposureDate", element.getExposureDate());
      if (element.getExposureType() != null)
        composeEnumeration("exposureType", element.getExposureType(), new AdverseReaction().new ExposureTypeEnumFactory());
      if (element.getCausalityExpectation() != null)
        composeEnumeration("causalityExpectation", element.getCausalityExpectation(), new AdverseReaction().new CausalityExpectationEnumFactory());
      composeResourceReference("substance", element.getSubstance());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Alert().new AlertStatusEnumFactory());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeString("note", element.getNote());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getCriticality() != null)
        composeEnumeration("criticality", element.getCriticality(), new AllergyIntolerance().new CriticalityEnumFactory());
      if (element.getSensitivityType() != null)
        composeEnumeration("sensitivityType", element.getSensitivityType(), new AllergyIntolerance().new SensitivitytypeEnumFactory());
      composeDateTime("recordedDate", element.getRecordedDate());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new AllergyIntolerance().new SensitivitystatusEnumFactory());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("recorder", element.getRecorder());
      composeResourceReference("substance", element.getSubstance());
      for (ResourceReference e : element.getReactions()) 
        composeResourceReference("reactions", e);
      for (ResourceReference e : element.getSensitivityTest()) 
        composeResourceReference("sensitivityTest", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("patient", element.getPatient());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanStatusEnumFactory());
      composePeriod("period", element.getPeriod());
      composeDateTime("modified", element.getModified());
      for (ResourceReference e : element.getConcern()) 
        composeResourceReference("concern", e);
      for (CarePlan.CarePlanParticipantComponent e : element.getParticipant()) 
        composeCarePlanCarePlanParticipantComponent("participant", e);
      for (CarePlan.CarePlanGoalComponent e : element.getGoal()) 
        composeCarePlanCarePlanGoalComponent("goal", e);
      for (CarePlan.CarePlanActivityComponent e : element.getActivity()) 
        composeCarePlanCarePlanActivityComponent("activity", e);
      composeString("notes", element.getNotes());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("role", element.getRole());
      composeResourceReference("member", element.getMember());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanGoalStatusEnumFactory());
      composeString("notes", element.getNotes());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getCategory() != null)
        composeEnumeration("category", element.getCategory(), new CarePlan().new CarePlanActivityCategoryEnumFactory());
      composeCodeableConcept("code", element.getCode());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanActivityStatusEnumFactory());
      composeBoolean("prohibited", element.getProhibited());
      composeType("timing", element.getTiming());
      composeResourceReference("location", element.getLocation());
      for (ResourceReference e : element.getPerformer()) 
        composeResourceReference("performer", e);
      composeResourceReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeString("details", element.getDetails());
      for (ResourceReference e : element.getActionTaken()) 
        composeResourceReference("actionTaken", e);
      composeString("notes", element.getNotes());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeDateTime("date", element.getDate());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeConformanceConformanceProposalComponent("proposal", element.getProposal());
      composeId("fhirVersion", element.getFhirVersion());
      composeBoolean("acceptUnknown", element.getAcceptUnknown());
      for (Code e : element.getFormat()) 
        composeCode("format", e);
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
      composeElementElements(element);
      composeString("name", element.getName());
      composeString("version", element.getVersion());
      composeDateTime("releaseDate", element.getReleaseDate());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("description", element.getDescription());
      composeUri("url", element.getUrl());
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceProposalComponent(String name, Conformance.ConformanceProposalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("description", element.getDescription());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new RestfulConformanceModeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
        composeConformanceConformanceRestResourceComponent("resource", e);
      composeBoolean("batch", element.getBatch());
      composeBoolean("history", element.getHistory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getService()) 
        composeCodeableConcept("service", e);
      composeString("description", element.getDescription());
      for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
        composeConformanceConformanceRestSecurityCertificateComponent("certificate", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("type", element.getType());
      composeBase64Binary("blob", element.getBlob());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("type", element.getType());
      composeResourceReference("profile", element.getProfile());
      for (Conformance.ConformanceRestResourceOperationComponent e : element.getOperation()) 
        composeConformanceConformanceRestResourceOperationComponent("operation", e);
      composeBoolean("readHistory", element.getReadHistory());
      for (String_ e : element.getSearchInclude()) 
        composeString("searchInclude", e);
      for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getSearchParam()) 
        composeConformanceConformanceRestResourceSearchParamComponent("searchParam", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceOperationComponent(String name, Conformance.ConformanceRestResourceOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Conformance().new RestfulOperationEnumFactory());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeUri("source", element.getSource());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Conformance().new SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentation());
      for (Code e : element.getTarget()) 
        composeCode("target", e);
      for (String_ e : element.getChain()) 
        composeString("chain", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("endpoint", element.getEndpoint());
      composeInteger("reliableCache", element.getReliableCache());
      composeString("documentation", element.getDocumentation());
      for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
        composeConformanceConformanceMessagingEventComponent("event", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCode());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new MessageConformanceEventModeEnumFactory());
      for (Coding e : element.getProtocol()) 
        composeCoding("protocol", e);
      composeCode("focus", element.getFocus());
      composeUri("request", element.getRequest());
      composeUri("response", element.getResponse());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new DocumentModeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeUri("profile", element.getProfile());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCoverage(String name, Coverage element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("issuer", element.getIssuer());
      composePeriod("period", element.getPeriod());
      composeCoding("type", element.getType());
      composeIdentifier("identifier", element.getIdentifier());
      composeIdentifier("group", element.getGroup());
      composeIdentifier("plan", element.getPlan());
      composeIdentifier("subplan", element.getSubplan());
      composeInteger("dependent", element.getDependent());
      composeInteger("sequence", element.getSequence());
      composeCoverageCoverageSubscriberComponent("subscriber", element.getSubscriber());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCoverageCoverageSubscriberComponent(String name, Coverage.CoverageSubscriberComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeHumanName("name", element.getName());
      composeAddress("address", element.getAddress());
      composeDate("birthdate", element.getBirthdate());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturer());
      composeString("model", element.getModel());
      composeString("version", element.getVersion());
      composeDeviceDeviceIdentityComponent("identity", element.getIdentity());
      composeResourceReference("owner", element.getOwner());
      for (Identifier e : element.getAssignedId()) 
        composeIdentifier("assignedId", e);
      composeResourceReference("location", element.getLocation());
      composeResourceReference("patient", element.getPatient());
      for (Contact e : element.getContact()) 
        composeContact("contact", e);
      composeUri("url", element.getUrl());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceDeviceIdentityComponent(String name, Device.DeviceIdentityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("gtin", element.getGtin());
      composeString("lot", element.getLot());
      composeString("serialNumber", element.getSerialNumber());
      composeDate("expiry", element.getExpiry());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilities(String name, DeviceCapabilities element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturer());
      composeResourceReference("identity", element.getIdentity());
      for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent e : element.getVirtualDevice()) 
        composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent("virtualDevice", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent e : element.getChannel()) 
        composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent("channel", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent e : element.getMetric()) 
        composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent("metric", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeString("key", element.getKey());
      composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent("info", element.getInfo());
      for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent e : element.getFacet()) 
        composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent("facet", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new DeviceCapabilities().new DeviceDataTypeEnumFactory());
      composeString("units", element.getUnits());
      composeCode("ucum", element.getUcum());
      composeArray("array", element.getArray());
      composeUri("system", element.getSystem());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeDecimal("scale", element.getScale());
      composeString("key", element.getKey());
      composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent("info", element.getInfo());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceLog(String name, DeviceLog element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeInstant("instant", element.getInstant());
      composeResourceReference("capabilities", element.getCapabilities());
      composeResourceReference("subject", element.getSubject());
      for (DeviceLog.DeviceLogItemComponent e : element.getItem()) 
        composeDeviceLogDeviceLogItemComponent("item", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceLogDeviceLogItemComponent(String name, DeviceLog.DeviceLogItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("key", element.getKey());
      composeString("value", element.getValue());
        for (Enumeration<DeviceLog.DeviceValueFlag> e : element.getFlag()) 
          composeEnumeration("flag", e, new DeviceLog().new DeviceValueFlagEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceObservation(String name, DeviceObservation element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("code", element.getCode());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeInstant("issued", element.getIssued());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("device", element.getDevice());
      for (ResourceReference e : element.getMeasurement()) 
        composeResourceReference("measurement", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("orderer", element.getOrderer());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("visit", element.getVisit());
      composeString("clinicalNotes", element.getClinicalNotes());
      for (ResourceReference e : element.getSpecimen()) 
        composeResourceReference("specimen", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
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
      composeElementElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
      composeDateTime("date", element.getDate());
      composeResourceReference("actor", element.getActor());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      for (ResourceReference e : element.getSpecimen()) 
        composeResourceReference("specimen", e);
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
      for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
        composeDiagnosticOrderDiagnosticOrderEventComponent("event", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticReport().new ObservationStatusEnumFactory());
      composeInstant("issued", element.getIssued());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("performer", element.getPerformer());
      composeIdentifier("reportId", element.getReportId());
      for (DiagnosticReport.DiagnosticReportRequestDetailComponent e : element.getRequestDetail()) 
        composeDiagnosticReportDiagnosticReportRequestDetailComponent("requestDetail", e);
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeDateTime("diagnosticTime", element.getDiagnosticTime());
      composeDiagnosticReportResultGroupComponent("results", element.getResults());
      for (ResourceReference e : element.getImage()) 
        composeResourceReference("image", e);
      composeString("conclusion", element.getConclusion());
      for (CodeableConcept e : element.getCodedDiagnosis()) 
        composeCodeableConcept("codedDiagnosis", e);
      for (Attachment e : element.getRepresentation()) 
        composeAttachment("representation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticReportDiagnosticReportRequestDetailComponent(String name, DiagnosticReport.DiagnosticReportRequestDetailComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeResourceReference("visit", element.getVisit());
      composeIdentifier("requestOrderId", element.getRequestOrderId());
      composeIdentifier("receiverOrderId", element.getReceiverOrderId());
      for (CodeableConcept e : element.getRequestTest()) 
        composeCodeableConcept("requestTest", e);
      composeCodeableConcept("bodySite", element.getBodySite());
      composeResourceReference("requester", element.getRequester());
      composeString("clinicalInfo", element.getClinicalInfo());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticReportResultGroupComponent(String name, DiagnosticReport.ResultGroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("name", element.getName());
      composeResourceReference("specimen", element.getSpecimen());
      for (DiagnosticReport.ResultGroupComponent e : element.getGroup()) 
        composeDiagnosticReportResultGroupComponent("group", e);
      for (ResourceReference e : element.getResult()) 
        composeResourceReference("result", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocument(String name, Document element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeIdentifier("versionIdentifier", element.getVersionIdentifier());
      composeInstant("created", element.getCreated());
      composeCoding("class", element.getClass_());
      composeCodeableConcept("type", element.getType());
      composeString("title", element.getTitle());
      composeCoding("confidentiality", element.getConfidentiality());
      composeResourceReference("subject", element.getSubject());
      for (ResourceReference e : element.getAuthor()) 
        composeResourceReference("author", e);
      for (Document.DocumentAttesterComponent e : element.getAttester()) 
        composeDocumentDocumentAttesterComponent("attester", e);
      composeResourceReference("custodian", element.getCustodian());
      composeDocumentDocumentEventComponent("event", element.getEvent());
      composeResourceReference("visit", element.getVisit());
      composeId("replaces", element.getReplaces());
      for (ResourceReference e : element.getProvenance()) 
        composeResourceReference("provenance", e);
      composeAttachment("stylesheet", element.getStylesheet());
      composeAttachment("representation", element.getRepresentation());
      for (Document.SectionComponent e : element.getSection()) 
        composeDocumentSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentDocumentAttesterComponent(String name, Document.DocumentAttesterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Document().new DocumentAttestationModeEnumFactory());
      composeDateTime("time", element.getTime());
      composeResourceReference("party", element.getParty());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentDocumentEventComponent(String name, Document.DocumentEventComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getCode()) 
        composeCodeableConcept("code", e);
      composePeriod("period", element.getPeriod());
      for (ResourceReference e : element.getDetail()) 
        composeResourceReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentSectionComponent(String name, Document.SectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("content", element.getContent());
      for (Document.SectionComponent e : element.getSection()) 
        composeDocumentSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      for (ResourceReference e : element.getAuthor()) 
        composeResourceReference("author", e);
      composeResourceReference("custodian", element.getCustodian());
      composeResourceReference("authenticator", element.getAuthenticator());
      composeDateTime("created", element.getCreated());
      composeInstant("indexed", element.getIndexed());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DocumentReference().new DocumentReferenceStatusEnumFactory());
      composeCodeableConcept("docStatus", element.getDocStatus());
      composeResourceReference("supercedes", element.getSupercedes());
      composeString("description", element.getDescription());
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      composeCode("primaryLanguage", element.getPrimaryLanguage());
      composeCode("mimeType", element.getMimeType());
      composeCodeableConcept("format", element.getFormat());
      composeInteger("size", element.getSize());
      composeString("hash", element.getHash());
      composeUri("location", element.getLocation());
      composeDocumentReferenceDocumentReferenceServiceComponent("service", element.getService());
      composeDocumentReferenceDocumentReferenceContextComponent("context", element.getContext());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      for (DocumentReference.DocumentReferenceServiceParameterComponent e : element.getParameter()) 
        composeDocumentReferenceDocumentReferenceServiceParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponent(String name, DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeString("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getCode()) 
        composeCodeableConcept("code", e);
      composePeriod("period", element.getPeriod());
      composeCodeableConcept("facilityType", element.getFacilityType());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      for (FamilyHistory.FamilyHistoryRelationComponent e : element.getRelation()) 
        composeFamilyHistoryFamilyHistoryRelationComponent("relation", e);
      composeString("note", element.getNote());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponent(String name, FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("deceased", element.getDeceased());
      composeString("note", element.getNote());
      for (FamilyHistory.FamilyHistoryRelationConditionComponent e : element.getCondition()) 
        composeFamilyHistoryFamilyHistoryRelationConditionComponent("condition", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponent(String name, FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("onset", element.getOnset());
      composeString("note", element.getNote());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Group().new GroupTypeEnumFactory());
      composeBoolean("actual", element.getActual());
      composeCodeableConcept("code", element.getCode());
      composeString("name", element.getName());
      composeInteger("quantity", element.getQuantity());
      for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
        composeGroupGroupCharacteristicComponent("characteristic", e);
      for (ResourceReference e : element.getMember()) 
        composeResourceReference("member", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      composeType("value", element.getValue());
      composeBoolean("exclude", element.getExclude());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("subject", element.getSubject());
      composeOid("uid", element.getUid());
      composeIdentifier("accessionNo", element.getAccessionNo());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("referrer", element.getReferrer());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy().new InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrl());
      composeInteger("numberOfSeries", element.getNumberOfSeries());
      composeInteger("numberOfInstances", element.getNumberOfInstances());
      composeString("clinicalInformation", element.getClinicalInformation());
      for (Coding e : element.getProcedure()) 
        composeCoding("procedure", e);
      composeResourceReference("interpreter", element.getInterpreter());
      composeString("description", element.getDescription());
      for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
        composeImagingStudyImagingStudySeriesComponent("series", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("number", element.getNumber());
      if (element.getModality() != null)
        composeEnumeration("modality", element.getModality(), new ImagingStudy().new ImageModalityEnumFactory());
      composeOid("uid", element.getUid());
      composeString("description", element.getDescription());
      composeInteger("numberOfInstances", element.getNumberOfInstances());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy().new InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrl());
      composeOid("locationUID", element.getLocationUID());
      composeCoding("bodySite", element.getBodySite());
      composeDateTime("dateTime", element.getDateTime());
      for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
        composeImagingStudyImagingStudySeriesInstanceComponent("instance", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("number", element.getNumber());
      composeOid("uid", element.getUid());
      composeOid("sopclass", element.getSopclass());
      composeString("type", element.getType());
      composeString("title", element.getTitle());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy().new InstanceAvailabilityEnumFactory());
      composeDateTime("dateTime", element.getDateTime());
      composeImagingStudyImagingStudySeriesInstanceImageComponent("image", element.getImage());
      composeUri("url", element.getUrl());
      composeResourceReference("attachment", element.getAttachment());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceImageComponent(String name, ImagingStudy.ImagingStudySeriesInstanceImageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("rows", element.getRows());
      composeInteger("columns", element.getColumns());
      composeInteger("bitsAllocated", element.getBitsAllocated());
      composeInteger("numberOfFrames", element.getNumberOfFrames());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("requester", element.getRequester());
      composeResourceReference("performer", element.getPerformer());
      composeResourceReference("manufacturer", element.getManufacturer());
      composeResourceReference("location", element.getLocation());
      composeDateTime("date", element.getDate());
      composeBoolean("reported", element.getReported());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeString("lotNumber", element.getLotNumber());
      composeDate("expirationDate", element.getExpirationDate());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeImmunizationImmunizationRefusalComponent("refusal", element.getRefusal());
      for (Immunization.ImmunizationReactionComponent e : element.getReaction()) 
        composeImmunizationImmunizationReactionComponent("reaction", e);
      composeImmunizationImmunizationVaccinationProtocolComponent("vaccinationProtocol", element.getVaccinationProtocol());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationRefusalComponent(String name, Immunization.ImmunizationRefusalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("reason", element.getReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDateTime("date", element.getDate());
      composeResourceReference("detail", element.getDetail());
      composeBoolean("reported", element.getReported());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("doseSequence", element.getDoseSequence());
      composeString("description", element.getDescription());
      composeResourceReference("authority", element.getAuthority());
      composeString("series", element.getSeries());
      composeInteger("seriesDoses", element.getSeriesDoses());
      composeCodeableConcept("doseTarget", element.getDoseTarget());
      composeCodeableConcept("doseStatus", element.getDoseStatus());
      composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationProfile(String name, ImmunizationProfile element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      for (ImmunizationProfile.ImmunizationProfileRecommendationComponent e : element.getRecommendation()) 
        composeImmunizationProfileImmunizationProfileRecommendationComponent("recommendation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDateTime("recommendationDate", element.getRecommendationDate());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeInteger("doseNumber", element.getDoseNumber());
      if (element.getForecastStatus() != null)
        composeEnumeration("forecastStatus", element.getForecastStatus(), new ImmunizationProfile().new ImmunizationForecastStatusEnumFactory());
      for (ImmunizationProfile.ImmunizationProfileRecommendationDateCriterionComponent e : element.getDateCriterion()) 
        composeImmunizationProfileImmunizationProfileRecommendationDateCriterionComponent("dateCriterion", e);
      composeImmunizationProfileImmunizationProfileRecommendationProtocolComponent("protocol", element.getProtocol());
      for (ResourceReference e : element.getSupportingImmunization()) 
        composeResourceReference("supportingImmunization", e);
      for (ImmunizationProfile.ImmunizationProfileRecommendationSupportingAdverseEventReportComponent e : element.getSupportingAdverseEventReport()) 
        composeImmunizationProfileImmunizationProfileRecommendationSupportingAdverseEventReportComponent("supportingAdverseEventReport", e);
      for (ResourceReference e : element.getSupportingPatientObservation()) 
        composeResourceReference("supportingPatientObservation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationDateCriterionComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTime("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationProtocolComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInteger("doseSequence", element.getDoseSequence());
      composeString("description", element.getDescription());
      composeResourceReference("authority", element.getAuthority());
      composeString("series", element.getSeries());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationSupportingAdverseEventReportComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationSupportingAdverseEventReportComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Id e : element.getIdentifier()) 
        composeId("identifier", e);
      composeCodeableConcept("reportType", element.getReportType());
      composeDateTime("reportDate", element.getReportDate());
      composeString("text", element.getText());
      for (ResourceReference e : element.getReaction()) 
        composeResourceReference("reaction", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("source", element.getSource());
      composeDateTime("date", element.getDate());
      composeBoolean("ordered", element.getOrdered());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new List_().new ListModeEnumFactory());
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
      composeElementElements(element);
      for (CodeableConcept e : element.getFlag()) 
        composeCodeableConcept("flag", e);
      composeBoolean("deleted", element.getDeleted());
      composeResourceReference("item", element.getItem());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeString("description", element.getDescription());
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeContact("telecom", element.getTelecom());
      composeAddress("address", element.getAddress());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeResourceReference("provider", element.getProvider());
      composeBoolean("active", element.getActive());
      composeResourceReference("partOf", element.getPartOf());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeDecimal("longitude", element.getLongitude());
      composeDecimal("latitude", element.getLatitude());
      composeDecimal("altitude", element.getAltitude());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeCodeableConcept("code", element.getCode());
      composeBoolean("isBrand", element.getIsBrand());
      composeResourceReference("manufacturer", element.getManufacturer());
      if (element.getKind() != null)
        composeEnumeration("kind", element.getKind(), new Medication().new MedicationKindEnumFactory());
      composeMedicationMedicationProductComponent("product", element.getProduct());
      composeMedicationMedicationPackageComponent("package", element.getPackage());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
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
      composeElementElements(element);
      composeResourceReference("item", element.getItem());
      composeRatio("amount", element.getAmount());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
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
      composeElementElements(element);
      composeResourceReference("item", element.getItem());
      composeQuantity("amount", element.getAmount());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getExternalID()) 
        composeIdentifier("externalID", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationAdministration().new MedicationAdminStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("practitioner", element.getPractitioner());
      composeResourceReference("visit", element.getVisit());
      composeResourceReference("prescription", element.getPrescription());
      composeBoolean("wasNotGiven", element.getWasNotGiven());
      for (CodeableConcept e : element.getReasonNotGiven()) 
        composeCodeableConcept("reasonNotGiven", e);
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      for (ResourceReference e : element.getAdministrationDevice()) 
        composeResourceReference("administrationDevice", e);
      for (MedicationAdministration.MedicationAdministrationDosageComponent e : element.getDosage()) 
        composeMedicationAdministrationMedicationAdministrationDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeSchedule("timing", element.getTiming());
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
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("externalID", element.getExternalID());
      composeCodeableConcept("status", element.getStatus());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("dispenser", element.getDispenser());
      composeResourceReference("visit", element.getVisit());
      for (ResourceReference e : element.getAuthorizingPrescription()) 
        composeResourceReference("authorizingPrescription", e);
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
      composeElementElements(element);
      composeIdentifier("externalID", element.getExternalID());
      composeCodeableConcept("status", element.getStatus());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("medication", element.getMedication());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeResourceReference("destination", element.getDestination());
      for (ResourceReference e : element.getReceiver()) 
        composeResourceReference("receiver", e);
      for (MedicationDispense.MedicationDispenseDispenseDosageComponent e : element.getDosage()) 
        composeMedicationDispenseMedicationDispenseDispenseDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponent(String name, MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeSchedule("timing", element.getTiming());
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
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getReason()) 
        composeCodeableConcept("reason", e);
      for (ResourceReference e : element.getResponsibleParty()) 
        composeResourceReference("responsibleParty", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getExternalID()) 
        composeIdentifier("externalID", e);
      composeDateTime("dateWritten", element.getDateWritten());
      composeCodeableConcept("status", element.getStatus());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("prescriber", element.getPrescriber());
      composeResourceReference("encounter", element.getEncounter());
      composeType("reasonForPrescribing", element.getReasonForPrescribing());
      composeResourceReference("medication", element.getMedication());
      for (MedicationPrescription.MedicationPrescriptionDosageInstructionsComponent e : element.getDosageInstructions()) 
        composeMedicationPrescriptionMedicationPrescriptionDosageInstructionsComponent("dosageInstructions", e);
      composeMedicationPrescriptionMedicationPrescriptionDispenseComponent("dispense", element.getDispense());
      composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent("substitution", element.getSubstitution());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionsComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("dosageInstructionsText", element.getDosageInstructionsText());
      composeType("additionalInstructions", element.getAdditionalInstructions());
      composeSchedule("timing", element.getTiming());
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
      composeElementElements(element);
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeInteger("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowed());
      composeQuantity("quantity", element.getQuantity());
      composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("patient", element.getPatient());
      composeBoolean("wasNotGiven", element.getWasNotGiven());
      for (CodeableConcept e : element.getReasonNotGiven()) 
        composeCodeableConcept("reasonNotGiven", e);
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      for (ResourceReference e : element.getAdministrationDevice()) 
        composeResourceReference("administrationDevice", e);
      for (MedicationStatement.MedicationStatementDosageComponent e : element.getDosage()) 
        composeMedicationStatementMedicationStatementDosageComponent("dosage", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeSchedule("timing", element.getTiming());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessage(String name, Message element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeId("identifier", element.getIdentifier());
      composeInstant("timestamp", element.getTimestamp());
      composeCode("event", element.getEvent());
      composeMessageMessageResponseComponent("response", element.getResponse());
      composeMessageMessageSourceComponent("source", element.getSource());
      composeMessageMessageDestinationComponent("destination", element.getDestination());
      composeResourceReference("enterer", element.getEnterer());
      composeResourceReference("author", element.getAuthor());
      composeResourceReference("receiver", element.getReceiver());
      composeResourceReference("responsible", element.getResponsible());
      composePeriod("effective", element.getEffective());
      composeCodeableConcept("reason", element.getReason());
      for (ResourceReference e : element.getData()) 
        composeResourceReference("data", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageMessageResponseComponent(String name, Message.MessageResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeId("identifier", element.getIdentifier());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Message().new ResponseCodeEnumFactory());
      composeResourceReference("details", element.getDetails());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageMessageSourceComponent(String name, Message.MessageSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeString("software", element.getSoftware());
      composeString("version", element.getVersion());
      composeContact("contact", element.getContact());
      composeUri("endpoint", element.getEndpoint());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageMessageDestinationComponent(String name, Message.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeResourceReference("target", element.getTarget());
      composeUri("endpoint", element.getEndpoint());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      composeCodeableConcept("interpretation", element.getInterpretation());
      composeString("comments", element.getComments());
      composeType("applies", element.getApplies());
      composeInstant("issued", element.getIssued());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Observation().new ObservationStatusEnumFactory());
      if (element.getReliability() != null)
        composeEnumeration("reliability", element.getReliability(), new Observation().new ObservationReliabilityEnumFactory());
      composeCodeableConcept("bodySite", element.getBodySite());
      composeCodeableConcept("method", element.getMethod());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("performer", element.getPerformer());
      for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
        composeObservationObservationReferenceRangeComponent("referenceRange", e);
      for (Observation.ObservationComponentComponent e : element.getComponent()) 
        composeObservationObservationComponentComponent("component", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("meaning", element.getMeaning());
      composeType("range", element.getRange());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservationObservationComponentComponent(String name, Observation.ObservationComponentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
        composeOperationOutcomeOperationOutcomeIssueComponent("issue", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new OperationOutcome().new IssueSeverityEnumFactory());
      composeCoding("type", element.getType());
      composeString("details", element.getDetails());
      for (String_ e : element.getLocation()) 
        composeString("location", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeDateTime("date", element.getDate());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeResourceReference("target", element.getTarget());
      composeString("reason", element.getReason());
      composeResourceReference("authority", element.getAuthority());
      composeResourceReference("payment", element.getPayment());
      composeOrderOrderWhenComponent("when", element.getWhen());
      for (ResourceReference e : element.getDetail()) 
        composeResourceReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeSchedule("schedule", element.getSchedule());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("request", element.getRequest());
      composeDateTime("date", element.getDate());
      composeResourceReference("who", element.getWho());
      composeResourceReference("authority", element.getAuthority());
      composeMoney("cost", element.getCost());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new OrderResponse().new OrderOutcomeCodeEnumFactory());
      composeString("description", element.getDescription());
      for (ResourceReference e : element.getFulfillment()) 
        composeResourceReference("fulfillment", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (String_ e : element.getName()) 
        composeString("name", e);
      composeCodeableConcept("type", element.getType());
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeBoolean("active", element.getActive());
      for (Organization.OrganizationAccreditationComponent e : element.getAccreditation()) 
        composeOrganizationOrganizationAccreditationComponent("accreditation", e);
      composeResourceReference("partOf", element.getPartOf());
      for (Organization.OrganizationContactEntityComponent e : element.getContactEntity()) 
        composeOrganizationOrganizationContactEntityComponent("contactEntity", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrganizationOrganizationAccreditationComponent(String name, Organization.OrganizationAccreditationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("issuer", element.getIssuer());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrganizationOrganizationContactEntityComponent(String name, Organization.OrganizationContactEntityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      composeHumanName("name", element.getName());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeAddress("address", element.getAddress());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (ResourceReference e : element.getLink()) 
        composeResourceReference("link", e);
      composeBoolean("active", element.getActive());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDemographics("details", element.getDetails());
      for (Patient.ContactComponent e : element.getContact()) 
        composePatientContactComponent("contact", e);
      composePatientAnimalComponent("animal", element.getAnimal());
      composeResourceReference("provider", element.getProvider());
      composeType("multipleBirth", element.getMultipleBirth());
      composeDateTime("deceasedDate", element.getDeceasedDate());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getRelationship()) 
        composeCodeableConcept("relationship", e);
      composeDemographics("details", element.getDetails());
      composeResourceReference("organization", element.getOrganization());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("species", element.getSpecies());
      composeCodeableConcept("breed", element.getBreed());
      composeCodeableConcept("genderStatus", element.getGenderStatus());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePicture(String name, Picture element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("operator", element.getOperator());
      composeIdentifier("identifier", element.getIdentifier());
      composeIdentifier("accessionNo", element.getAccessionNo());
      composeIdentifier("studyId", element.getStudyId());
      composeIdentifier("seriesId", element.getSeriesId());
      composeCodeableConcept("method", element.getMethod());
      composeResourceReference("requester", element.getRequester());
      if (element.getModality() != null)
        composeEnumeration("modality", element.getModality(), new Picture().new ImageModalityEnumFactory());
      composeString("deviceName", element.getDeviceName());
      composeInteger("height", element.getHeight());
      composeInteger("width", element.getWidth());
      composeInteger("bits", element.getBits());
      composeInteger("frames", element.getFrames());
      composeDuration("frameDelay", element.getFrameDelay());
      composeCodeableConcept("view", element.getView());
      composeAttachment("content", element.getContent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDemographics("details", element.getDetails());
      composeResourceReference("organization", element.getOrganization());
      for (CodeableConcept e : element.getRole()) 
        composeCodeableConcept("role", e);
      for (CodeableConcept e : element.getSpecialty()) 
        composeCodeableConcept("specialty", e);
      composePeriod("period", element.getPeriod());
      for (Practitioner.PractitionerQualificationComponent e : element.getQualification()) 
        composePractitionerPractitionerQualificationComponent("qualification", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composePeriod("period", element.getPeriod());
      composeResourceReference("issuer", element.getIssuer());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProblem(String name, Problem element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("visit", element.getVisit());
      composeResourceReference("asserter", element.getAsserter());
      composeDate("dateAsserted", element.getDateAsserted());
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Problem().new ProblemStatusEnumFactory());
      composeCodeableConcept("certainty", element.getCertainty());
      composeCodeableConcept("severity", element.getSeverity());
      composeType("onset", element.getOnset());
      composeType("abatement", element.getAbatement());
      composeProblemProblemStageComponent("stage", element.getStage());
      for (Problem.ProblemEvidenceComponent e : element.getEvidence()) 
        composeProblemProblemEvidenceComponent("evidence", e);
      for (Problem.ProblemLocationComponent e : element.getLocation()) 
        composeProblemProblemLocationComponent("location", e);
      for (Problem.ProblemRelatedItemComponent e : element.getRelatedItem()) 
        composeProblemProblemRelatedItemComponent("relatedItem", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProblemProblemStageComponent(String name, Problem.ProblemStageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("summary", element.getSummary());
      for (ResourceReference e : element.getAssessment()) 
        composeResourceReference("assessment", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProblemProblemEvidenceComponent(String name, Problem.ProblemEvidenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      for (ResourceReference e : element.getDetails()) 
        composeResourceReference("details", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProblemProblemLocationComponent(String name, Problem.ProblemLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("code", element.getCode());
      composeString("details", element.getDetails());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProblemProblemRelatedItemComponent(String name, Problem.ProblemRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Problem().new ProblemRelationshipTypeEnumFactory());
      composeResourceReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeProcedureProcedureDescriptionComponent("description", element.getDescription());
      composeString("indication", element.getIndication());
      for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
        composeProcedureProcedurePerformerComponent("performer", e);
      composePeriod("date", element.getDate());
      composeResourceReference("visit", element.getVisit());
      composeString("outcome", element.getOutcome());
      for (ResourceReference e : element.getReport()) 
        composeResourceReference("report", e);
      composeString("complication", element.getComplication());
      composeString("followUp", element.getFollowUp());
      for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
        composeProcedureProcedureRelatedItemComponent("relatedItem", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedureDescriptionComponent(String name, Procedure.ProcedureDescriptionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("notes", element.getNotes());
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeResourceReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Procedure().new ProcedureRelationshipTypeEnumFactory());
      composeResourceReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      composeProfileProfileStatusComponent("status", element.getStatus());
      for (Profile.ProfileImportComponent e : element.getImport()) 
        composeProfileProfileImportComponent("import", e);
      composeCode("bundle", element.getBundle());
      for (Profile.ProfileStructureComponent e : element.getStructure()) 
        composeProfileProfileStructureComponent("structure", e);
      for (Profile.ProfileExtensionDefnComponent e : element.getExtensionDefn()) 
        composeProfileProfileExtensionDefnComponent("extensionDefn", e);
      for (Profile.ProfileBindingComponent e : element.getBinding()) 
        composeProfileProfileBindingComponent("binding", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileStatusComponent(String name, Profile.ProfileStatusComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Profile().new ResourceProfileStatusEnumFactory());
      composeDateTime("date", element.getDate());
      composeString("comment", element.getComment());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileImportComponent(String name, Profile.ProfileImportComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("uri", element.getUri());
      composeString("prefix", element.getPrefix());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileStructureComponent(String name, Profile.ProfileStructureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("type", element.getType());
      composeString("name", element.getName());
      composeString("purpose", element.getPurpose());
      composeUri("profile", element.getProfile());
      for (Profile.ElementComponent e : element.getElement()) 
        composeProfileElementComponent("element", e);
      for (Profile.ProfileStructureSearchParamComponent e : element.getSearchParam()) 
        composeProfileProfileStructureSearchParamComponent("searchParam", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementComponent(String name, Profile.ElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("path", element.getPath());
      composeString("name", element.getName());
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      composeBoolean("bundled", element.getBundled());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionComponent(String name, Profile.ElementDefinitionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("short", element.getShort());
      composeString("formal", element.getFormal());
      composeString("comments", element.getComments());
      composeString("requirements", element.getRequirements());
      for (String_ e : element.getSynonym()) 
        composeString("synonym", e);
      composeInteger("min", element.getMin());
      composeString("max", element.getMax());
      for (Profile.TypeRefComponent e : element.getType()) 
        composeProfileTypeRefComponent("type", e);
      composeString("nameReference", element.getNameReference());
      composeType("value", element.getValue());
      composeInteger("maxLength", element.getMaxLength());
      for (Id e : element.getCondition()) 
        composeId("condition", e);
      for (Profile.ElementDefinitionConstraintComponent e : element.getConstraint()) 
        composeProfileElementDefinitionConstraintComponent("constraint", e);
      composeBoolean("mustSupport", element.getMustSupport());
      composeBoolean("mustUnderstand", element.getMustUnderstand());
      composeString("binding", element.getBinding());
      for (Profile.ElementDefinitionMappingComponent e : element.getMapping()) 
        composeProfileElementDefinitionMappingComponent("mapping", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileTypeRefComponent(String name, Profile.TypeRefComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCode());
      composeUri("profile", element.getProfile());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionConstraintComponent(String name, Profile.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeId("key", element.getKey());
      composeString("name", element.getName());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new Profile().new ConstraintSeverityEnumFactory());
      composeString("human", element.getHuman());
      composeString("xpath", element.getXpath());
      composeString("ocl", element.getOcl());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionMappingComponent(String name, Profile.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("target", element.getTarget());
      composeString("map", element.getMap());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileStructureSearchParamComponent(String name, Profile.ProfileStructureSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Profile().new SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileExtensionDefnComponent(String name, Profile.ProfileExtensionDefnComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeId("code", element.getCode());
      if (element.getContextType() != null)
        composeEnumeration("contextType", element.getContextType(), new Profile().new ExtensionContextEnumFactory());
      for (String_ e : element.getContext()) 
        composeString("context", e);
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileBindingComponent(String name, Profile.ProfileBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeString("definition", element.getDefinition());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Profile().new BindingTypeEnumFactory());
      composeBoolean("isExtensible", element.getIsExtensible());
      if (element.getConformance() != null)
        composeEnumeration("conformance", element.getConformance(), new Profile().new BindingConformanceEnumFactory());
      composeType("reference", element.getReference());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (ResourceReference e : element.getTarget()) 
        composeResourceReference("target", e);
      composeProvenanceProvenanceActivityComponent("activity", element.getActivity());
      for (Provenance.ProvenancePartyComponent e : element.getParty()) 
        composeProvenanceProvenancePartyComponent("party", e);
      composeString("signature", element.getSignature());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenanceProvenanceActivityComponent(String name, Provenance.ProvenanceActivityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composePeriod("period", element.getPeriod());
      composeInstant("recorded", element.getRecorded());
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("location", element.getLocation());
      composeUri("policy", element.getPolicy());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenanceProvenancePartyComponent(String name, Provenance.ProvenancePartyComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUri("identifier", element.getIdentifier());
      composeString("description", element.getDescription());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeUri("id", element.getId());
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      composeQueryQueryResponseComponent("response", element.getResponse());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryParameterComponent(String name, Query.QueryParameterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("name", element.getName());
      composeString("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponseComponent(String name, Query.QueryResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("id", element.getId());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new Query().new QueryOutcomeEnumFactory());
      composeInteger("total", element.getTotal());
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      composeQueryQueryResponseFirstComponent("first", element.getFirst());
      composeQueryQueryResponsePreviousComponent("previous", element.getPrevious());
      composeQueryQueryResponseNextComponent("next", element.getNext());
      composeQueryQueryResponseLastComponent("last", element.getLast());
      for (ResourceReference e : element.getReference()) 
        composeResourceReference("reference", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponseFirstComponent(String name, Query.QueryResponseFirstComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponsePreviousComponent(String name, Query.QueryResponsePreviousComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponseNextComponent(String name, Query.QueryResponseNextComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQueryQueryResponseLastComponent(String name, Query.QueryResponseLastComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Query.QueryParameterComponent e : element.getParameter()) 
        composeQueryQueryParameterComponent("parameter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Questionnaire().new ObservationStatusEnumFactory());
      composeInstant("authored", element.getAuthored());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeResourceReference("source", element.getSource());
      composeCodeableConcept("name", element.getName());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("visit", element.getVisit());
      for (Questionnaire.AnswerComponent e : element.getAnswer()) 
        composeQuestionnaireAnswerComponent("answer", e);
      for (Questionnaire.SectionComponent e : element.getSection()) 
        composeQuestionnaireSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswerComponent(String name, Questionnaire.AnswerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      composeResourceReference("evidence", element.getEvidence());
      composeString("remarks", element.getRemarks());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireSectionComponent(String name, Questionnaire.SectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCodeableConcept("name", element.getName());
      for (Questionnaire.AnswerComponent e : element.getAnswer()) 
        composeQuestionnaireAnswerComponent("answer", e);
      for (Questionnaire.SectionComponent e : element.getSection()) 
        composeQuestionnaireSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEvent(String name, SecurityEvent element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
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
      composeElementElements(element);
      composeCodeableConcept("eventId", element.getEventId());
      for (CodeableConcept e : element.getCode()) 
        composeCodeableConcept("code", e);
      if (element.getAction() != null)
        composeEnumeration("action", element.getAction(), new SecurityEvent().new SecurityEventActionEnumFactory());
      composeInstant("dateTime", element.getDateTime());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new SecurityEvent().new SecurityEventOutcomeEnumFactory());
      composeString("outcomeDesc", element.getOutcomeDesc());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventParticipantComponent(String name, SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getRole()) 
        composeCodeableConcept("role", e);
      composeCodeableConcept("mediaId", element.getMediaId());
      composeString("userId", element.getUserId());
      composeString("otherUserId", element.getOtherUserId());
      composeString("name", element.getName());
      composeBoolean("requestor", element.getRequestor());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("identifier", element.getIdentifier());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent().new NetworkTypeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("site", element.getSite());
      composeString("identifier", element.getIdentifier());
      for (Coding e : element.getType()) 
        composeCoding("type", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventObjectComponent(String name, SecurityEvent.SecurityEventObjectComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCoding("idType", element.getIdType());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent().new ObjectTypeEnumFactory());
      if (element.getRole() != null)
        composeEnumeration("role", element.getRole(), new SecurityEvent().new ObjectRoleEnumFactory());
      if (element.getLifecycle() != null)
        composeEnumeration("lifecycle", element.getLifecycle(), new SecurityEvent().new ObjectLifecycleEnumFactory());
      composeString("identifier", element.getIdentifier());
      composeString("sensitivity", element.getSensitivity());
      composeString("name", element.getName());
      composeBase64Binary("query", element.getQuery());
      for (SecurityEvent.SecurityEventObjectDetailsComponent e : element.getDetails()) 
        composeSecurityEventSecurityEventObjectDetailsComponent("details", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventObjectDetailsComponent(String name, SecurityEvent.SecurityEventObjectDetailsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeString("type", element.getType());
      composeBase64Binary("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeString("name", element.getName());
      composeCoding("type", element.getType());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new ValueSet().new ValuesetStatusEnumFactory());
      composeDateTime("date", element.getDate());
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
      composeElementElements(element);
      composeUri("system", element.getSystem());
      for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
        composeValueSetValueSetDefineConceptComponent("concept", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetDefineConceptComponent(String name, ValueSet.ValueSetDefineConceptComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("code", element.getCode());
      composeBoolean("abstract", element.getAbstract());
      composeString("display", element.getDisplay());
      composeString("definition", element.getDefinition());
      for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
        composeValueSetValueSetDefineConceptComponent("concept", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (Uri e : element.getImport()) 
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
      composeElementElements(element);
      composeUri("system", element.getSystem());
      composeString("version", element.getVersion());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new ValueSet().new CodeSelectionModeEnumFactory());
      for (Code e : element.getCode()) 
        composeCode("code", e);
      for (ValueSet.ConceptSetFilterComponent e : element.getFilter()) 
        composeValueSetConceptSetFilterComponent("filter", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeCode("property", element.getProperty());
      if (element.getOp() != null)
        composeEnumeration("op", element.getOp(), new ValueSet().new FilterOperatorEnumFactory());
      composeCode("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeInstant("timestamp", element.getTimestamp());
      for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
        composeValueSetValueSetExpansionContainsComponent("contains", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
        composeValueSetValueSetExpansionContainsComponent("contains", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeVisit(String name, Visit element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Visit().new EncounterStateEnumFactory());
      if (element.getClass_() != null)
        composeEnumeration("class", element.getClass_(), new Visit().new EncounterClassEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeResourceReference("subject", element.getSubject());
      for (Visit.VisitParticipantComponent e : element.getParticipant()) 
        composeVisitVisitParticipantComponent("participant", e);
      composeResourceReference("fulfills", element.getFulfills());
      composeDuration("length", element.getLength());
      composeType("reason", element.getReason());
      composeResourceReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeVisitVisitHospitalizationComponent("hospitalization", element.getHospitalization());
      for (Visit.VisitLocationComponent e : element.getLocation()) 
        composeVisitVisitLocationComponent("location", e);
      composeResourceReference("serviceProvider", element.getServiceProvider());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeVisitVisitParticipantComponent(String name, Visit.VisitParticipantComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeResourceReference("practitioner", element.getPractitioner());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeVisitVisitHospitalizationComponent(String name, Visit.VisitHospitalizationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeCodeableConcept("preAdmissionTest", element.getPreAdmissionTest());
      composeResourceReference("origin", element.getOrigin());
      composeCodeableConcept("admitSource", element.getAdmitSource());
      composePeriod("period", element.getPeriod());
      for (Visit.VisitHospitalizationAccomodationComponent e : element.getAccomodation()) 
        composeVisitVisitHospitalizationAccomodationComponent("accomodation", e);
      composeCodeableConcept("diet", element.getDiet());
      for (CodeableConcept e : element.getSpecialCourtesy()) 
        composeCodeableConcept("specialCourtesy", e);
      for (CodeableConcept e : element.getSpecialArrangement()) 
        composeCodeableConcept("specialArrangement", e);
      composeResourceReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeBoolean("reAdmission", element.getReAdmission());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeVisitVisitHospitalizationAccomodationComponent(String name, Visit.VisitHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeResourceReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeVisitVisitLocationComponent(String name, Visit.VisitLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      composeResourceReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  @Override
  protected void composeResource(Resource resource) throws Exception {
    if (resource instanceof AdverseReaction)
      composeAdverseReaction("AdverseReaction", (AdverseReaction)resource);
    else if (resource instanceof Alert)
      composeAlert("Alert", (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance("AllergyIntolerance", (AllergyIntolerance)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof Conformance)
      composeConformance("Conformance", (Conformance)resource);
    else if (resource instanceof Coverage)
      composeCoverage("Coverage", (Coverage)resource);
    else if (resource instanceof Device)
      composeDevice("Device", (Device)resource);
    else if (resource instanceof DeviceCapabilities)
      composeDeviceCapabilities("DeviceCapabilities", (DeviceCapabilities)resource);
    else if (resource instanceof DeviceLog)
      composeDeviceLog("DeviceLog", (DeviceLog)resource);
    else if (resource instanceof DeviceObservation)
      composeDeviceObservation("DeviceObservation", (DeviceObservation)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder("DiagnosticOrder", (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport("DiagnosticReport", (DiagnosticReport)resource);
    else if (resource instanceof Document)
      composeDocument("Document", (Document)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference("DocumentReference", (DocumentReference)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy("ImagingStudy", (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization("Immunization", (Immunization)resource);
    else if (resource instanceof ImmunizationProfile)
      composeImmunizationProfile("ImmunizationProfile", (ImmunizationProfile)resource);
    else if (resource instanceof List_)
      composeList_("List", (List_)resource);
    else if (resource instanceof Location)
      composeLocation("Location", (Location)resource);
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
    else if (resource instanceof Message)
      composeMessage("Message", (Message)resource);
    else if (resource instanceof Observation)
      composeObservation("Observation", (Observation)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome("OperationOutcome", (OperationOutcome)resource);
    else if (resource instanceof Order)
      composeOrder("Order", (Order)resource);
    else if (resource instanceof OrderResponse)
      composeOrderResponse("OrderResponse", (OrderResponse)resource);
    else if (resource instanceof Organization)
      composeOrganization("Organization", (Organization)resource);
    else if (resource instanceof Patient)
      composePatient("Patient", (Patient)resource);
    else if (resource instanceof Picture)
      composePicture("Picture", (Picture)resource);
    else if (resource instanceof Practitioner)
      composePractitioner("Practitioner", (Practitioner)resource);
    else if (resource instanceof Problem)
      composeProblem("Problem", (Problem)resource);
    else if (resource instanceof Procedure)
      composeProcedure("Procedure", (Procedure)resource);
    else if (resource instanceof Profile)
      composeProfile("Profile", (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance("Provenance", (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery("Query", (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire("Questionnaire", (Questionnaire)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent("SecurityEvent", (SecurityEvent)resource);
    else if (resource instanceof Specimen)
      composeSpecimen("Specimen", (Specimen)resource);
    else if (resource instanceof Substance)
      composeSubstance("Substance", (Substance)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Visit)
      composeVisit("Visit", (Visit)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  @SuppressWarnings("unchecked")
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
    else if (type instanceof Choice)
       composeChoice(prefix+"Choice", (Choice) type);
    else if (type instanceof Attachment)
       composeAttachment(prefix+"Attachment", (Attachment) type);
    else if (type instanceof Ratio)
       composeRatio(prefix+"Ratio", (Ratio) type);
    else if (type instanceof Array)
       composeArray(prefix+"Array", (Array) type);
    else if (type instanceof ResourceReference)
       composeResourceReference(prefix+"Resource", (ResourceReference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
    else if (type instanceof Schedule)
       composeSchedule(prefix+"Schedule", (Schedule) type);
    else if (type instanceof Contact)
       composeContact(prefix+"Contact", (Contact) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
    else if (type instanceof Demographics)
       composeDemographics(prefix+"Demographics", (Demographics) type);
    else if (type instanceof Integer)
       composeInteger(prefix+"Integer", (Integer) type);
    else if (type instanceof DateTime)
       composeDateTime(prefix+"DateTime", (DateTime) type);
    else if (type instanceof Code)
       composeCode(prefix+"Code", (Code) type);
    else if (type instanceof Date)
       composeDate(prefix+"Date", (Date) type);
    else if (type instanceof Decimal)
       composeDecimal(prefix+"Decimal", (Decimal) type);
    else if (type instanceof Uri)
       composeUri(prefix+"Uri", (Uri) type);
    else if (type instanceof Id)
       composeId(prefix+"Id", (Id) type);
    else if (type instanceof Base64Binary)
       composeBase64Binary(prefix+"Base64Binary", (Base64Binary) type);
    else if (type instanceof Oid)
       composeOid(prefix+"Oid", (Oid) type);
    else if (type instanceof String_)
       composeString(prefix+"String", (String_) type);
    else if (type instanceof Boolean)
       composeBoolean(prefix+"Boolean", (Boolean) type);
    else if (type instanceof Uuid)
       composeUuid(prefix+"Uuid", (Uuid) type);
    else if (type instanceof Instant)
       composeInstant(prefix+"Instant", (Instant) type);
    else
      throw new Exception("Unhanded type");
  }

}

