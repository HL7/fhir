package org.hl7.fhir.instance.formats;

/*
  Copyright (c) 2011-2014, HL7, Inc.
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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.IntegerType;
import org.hl7.fhir.instance.model.BooleanType;
import org.hl7.fhir.utilities.Utilities;

public class XmlComposer extends XmlComposerBase {

  private void composeElementElements(Element element) throws Exception {
    for (Extension e : element.getExtensions()) {
      composeExtension("extension", e);
    }
  }

  private void composeBackboneElements(BackboneElement element) throws Exception {
    composeElementElements(element);
    for (Extension e : element.getModifierExtensions()) {
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
      if (element.getUrl() != null)
        xml.attribute("url", element.getUrl().getValue());
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
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Narrative.NarrativeStatusEnumFactory());
      composeXhtml("div", element.getDiv());
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
      composeString("version", element.getVersion());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      composeBoolean("primary", element.getPrimary());
      composeResourceReference("valueSet", element.getValueSet());
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
        composeEnumeration("comparator", element.getComparator(), new Quantity.QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
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

  private void composeSampledData(String name, SampledData element) throws Exception {
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
      xml.close(FHIR_NS, name);
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      composeTypeAttributes(element);
      xml.open(FHIR_NS, name);
      composeElementElements(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Identifier.IdentifierUseEnumFactory());
      composeString("label", element.getLabel());
      composeUri("system", element.getSystem());
      composeString("value", element.getValue());
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
        composeEnumeration("comparator", element.getComparator(), new Age.QuantityComparatorEnumFactory());
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
        composeEnumeration("comparator", element.getComparator(), new Count.QuantityComparatorEnumFactory());
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
        composeEnumeration("comparator", element.getComparator(), new Money.QuantityComparatorEnumFactory());
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
        composeEnumeration("comparator", element.getComparator(), new Distance.QuantityComparatorEnumFactory());
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
        composeEnumeration("comparator", element.getComparator(), new Duration.QuantityComparatorEnumFactory());
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
        composeEnumeration("when", element.getWhen(), new Schedule.EventTimingEnumFactory());
      composeDecimal("duration", element.getDuration());
      if (element.getUnits() != null)
        composeEnumeration("units", element.getUnits(), new Schedule.UnitsOfTimeEnumFactory());
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
        composeEnumeration("system", element.getSystem(), new Contact.ContactSystemEnumFactory());
      composeString("value", element.getValue());
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Contact.ContactUseEnumFactory());
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
        composeEnumeration("use", element.getUse(), new Address.AddressUseEnumFactory());
      composeString("text", element.getText());
      for (StringType e : element.getLine()) 
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
        composeEnumeration("use", element.getUse(), new HumanName.NameUseEnumFactory());
      composeString("text", element.getText());
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

  private void composeResourceAttributes(Resource element) throws Exception {
    composeElementAttributes(element);
  }

  private void composeResourceElements(Resource element) throws Exception {
    composeBackboneElements(element);
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
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("date", element.getDate());
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
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new AdverseReaction.ReactionSeverityEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAdverseReactionAdverseReactionExposureComponent(String name, AdverseReaction.AdverseReactionExposureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDateTime("date", element.getDate());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new AdverseReaction.ExposureTypeEnumFactory());
      if (element.getCausalityExpectation() != null)
        composeEnumeration("causalityExpectation", element.getCausalityExpectation(), new AdverseReaction.CausalityExpectationEnumFactory());
      composeResourceReference("substance", element.getSubstance());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Alert.AlertStatusEnumFactory());
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
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getCriticality() != null)
        composeEnumeration("criticality", element.getCriticality(), new AllergyIntolerance.CriticalityEnumFactory());
      if (element.getSensitivityType() != null)
        composeEnumeration("sensitivityType", element.getSensitivityType(), new AllergyIntolerance.SensitivitytypeEnumFactory());
      composeDateTime("recordedDate", element.getRecordedDate());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new AllergyIntolerance.SensitivitystatusEnumFactory());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("recorder", element.getRecorder());
      composeResourceReference("substance", element.getSubstance());
      for (ResourceReference e : element.getReaction()) 
        composeResourceReference("reaction", e);
      for (ResourceReference e : element.getSensitivityTest()) 
        composeResourceReference("sensitivityTest", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAppointment(String name, Appointment element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeInteger("priority", element.getPriority());
      composeCode("status", element.getStatus());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      composeString("description", element.getDescription());
      composeInstant("start", element.getStart());
      composeInstant("end", element.getEnd());
      for (ResourceReference e : element.getSlot()) 
        composeResourceReference("slot", e);
      composeResourceReference("location", element.getLocation());
      composeString("comment", element.getComment());
      composeResourceReference("order", element.getOrder());
      for (Appointment.AppointmentParticipantComponent e : element.getParticipant()) 
        composeAppointmentAppointmentParticipantComponent("participant", e);
      composeResourceReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTime("lastModified", element.getLastModified());
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
      composeResourceReference("actor", element.getActor());
      if (element.getRequired() != null)
        composeEnumeration("required", element.getRequired(), new Appointment.ParticipantrequiredEnumFactory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Appointment.ParticipationstatusEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAppointmentResponse(String name, AppointmentResponse element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("appointment", element.getAppointment());
      for (CodeableConcept e : element.getParticipantType()) 
        composeCodeableConcept("participantType", e);
      for (ResourceReference e : element.getIndividual()) 
        composeResourceReference("individual", e);
      if (element.getParticipantStatus() != null)
        composeEnumeration("participantStatus", element.getParticipantStatus(), new AppointmentResponse.ParticipantstatusEnumFactory());
      composeString("comment", element.getComment());
      composeInstant("start", element.getStart());
      composeInstant("end", element.getEnd());
      composeResourceReference("lastModifiedBy", element.getLastModifiedBy());
      composeDateTime("lastModified", element.getLastModified());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeAvailability(String name, Availability element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeResourceReference("actor", element.getActor());
      composePeriod("planningHorizon", element.getPlanningHorizon());
      composeString("comment", element.getComment());
      composeDateTime("lastModified", element.getLastModified());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("patient", element.getPatient());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan.CarePlanStatusEnumFactory());
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
      composeBackboneElements(element);
      composeCodeableConcept("role", element.getRole());
      composeResourceReference("member", element.getMember());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan.CarePlanGoalStatusEnumFactory());
      composeString("notes", element.getNotes());
      for (ResourceReference e : element.getConcern()) 
        composeResourceReference("concern", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (StringType e : element.getGoal()) 
        composeString("goal", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan.CarePlanActivityStatusEnumFactory());
      composeBoolean("prohibited", element.getProhibited());
      for (ResourceReference e : element.getActionResulting()) 
        composeResourceReference("actionResulting", e);
      composeString("notes", element.getNotes());
      composeResourceReference("detail", element.getDetail());
      composeCarePlanCarePlanActivitySimpleComponent("simple", element.getSimple());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCarePlanCarePlanActivitySimpleComponent(String name, CarePlan.CarePlanActivitySimpleComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getCategory() != null)
        composeEnumeration("category", element.getCategory(), new CarePlan.CarePlanActivityCategoryEnumFactory());
      composeCodeableConcept("code", element.getCode());
      composeType("timing", element.getTiming());
      composeResourceReference("location", element.getLocation());
      for (ResourceReference e : element.getPerformer()) 
        composeResourceReference("performer", e);
      composeResourceReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeString("details", element.getDetails());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeComposition(String name, Composition element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeDateTime("date", element.getDate());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("class", element.getClass_());
      composeString("title", element.getTitle());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Composition.CompositionStatusEnumFactory());
      composeCoding("confidentiality", element.getConfidentiality());
      composeResourceReference("subject", element.getSubject());
      for (ResourceReference e : element.getAuthor()) 
        composeResourceReference("author", e);
      for (Composition.CompositionAttesterComponent e : element.getAttester()) 
        composeCompositionCompositionAttesterComponent("attester", e);
      composeResourceReference("custodian", element.getCustodian());
      composeCompositionCompositionEventComponent("event", element.getEvent());
      composeResourceReference("encounter", element.getEncounter());
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
      composeDateTime("time", element.getTime());
      composeResourceReference("party", element.getParty());
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
      for (ResourceReference e : element.getDetail()) 
        composeResourceReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCompositionSectionComponent(String name, Composition.SectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("title", element.getTitle());
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("content", element.getContent());
      for (Composition.SectionComponent e : element.getSection()) 
        composeCompositionSectionComponent("section", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMap(String name, ConceptMap element) throws Exception {
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
      composeString("copyright", element.getCopyright());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new ConceptMap.ValuesetStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
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
      composeUri("codeSystem", element.getCodeSystem());
      composeCode("code", element.getCode());
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
      composeUri("element", element.getElement());
      composeUri("codeSystem", element.getCodeSystem());
      composeString("code", element.getCode());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConceptMapConceptMapElementMapComponent(String name, ConceptMap.ConceptMapElementMapComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("codeSystem", element.getCodeSystem());
      composeCode("code", element.getCode());
      if (element.getEquivalence() != null)
        composeEnumeration("equivalence", element.getEquivalence(), new ConceptMap.ConceptEquivalenceEnumFactory());
      composeString("comments", element.getComments());
      for (ConceptMap.OtherElementComponent e : element.getProduct()) 
        composeConceptMapOtherElementComponent("product", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("asserter", element.getAsserter());
      composeDate("dateAsserted", element.getDateAsserted());
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Condition.ConditionStatusEnumFactory());
      composeCodeableConcept("certainty", element.getCertainty());
      composeCodeableConcept("severity", element.getSeverity());
      composeType("onset", element.getOnset());
      composeType("abatement", element.getAbatement());
      composeConditionConditionStageComponent("stage", element.getStage());
      for (Condition.ConditionEvidenceComponent e : element.getEvidence()) 
        composeConditionConditionEvidenceComponent("evidence", e);
      for (Condition.ConditionLocationComponent e : element.getLocation()) 
        composeConditionConditionLocationComponent("location", e);
      for (Condition.ConditionRelatedItemComponent e : element.getRelatedItem()) 
        composeConditionConditionRelatedItemComponent("relatedItem", e);
      composeString("notes", element.getNotes());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("summary", element.getSummary());
      for (ResourceReference e : element.getAssessment()) 
        composeResourceReference("assessment", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (ResourceReference e : element.getDetail()) 
        composeResourceReference("detail", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeString("detail", element.getDetail());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConditionConditionRelatedItemComponent(String name, Condition.ConditionRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Condition.ConditionRelationshipTypeEnumFactory());
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("target", element.getTarget());
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
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Conformance.ConformanceStatementStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeId("fhirVersion", element.getFhirVersion());
      composeBoolean("acceptUnknown", element.getAcceptUnknown());
      for (CodeType e : element.getFormat()) 
        composeCode("format", e);
      for (ResourceReference e : element.getProfile()) 
        composeResourceReference("profile", e);
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
      composeBackboneElements(element);
      composeString("description", element.getDescription());
      composeUri("url", element.getUrl());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance.RestfulConformanceModeEnumFactory());
      composeString("documentation", element.getDocumentation());
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
      composeBoolean("cors", element.getCors());
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
      composeBackboneElements(element);
      composeCode("type", element.getType());
      composeBase64Binary("blob", element.getBlob());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("type", element.getType());
      composeResourceReference("profile", element.getProfile());
      for (Conformance.ResourceInteractionComponent e : element.getInteraction()) 
        composeConformanceResourceInteractionComponent("interaction", e);
      composeBoolean("readHistory", element.getReadHistory());
      composeBoolean("updateCreate", element.getUpdateCreate());
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
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Conformance.TypeRestfulInteractionEnumFactory());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      composeUri("definition", element.getDefinition());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Conformance.SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentation());
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
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Conformance.SystemRestfulInteractionEnumFactory());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceRestOperationComponent(String name, Conformance.ConformanceRestOperationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      composeResourceReference("definition", element.getDefinition());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
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
      composeBackboneElements(element);
      composeCoding("code", element.getCode());
      if (element.getCategory() != null)
        composeEnumeration("category", element.getCategory(), new Conformance.MessageSignificanceCategoryEnumFactory());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance.MessageConformanceEventModeEnumFactory());
      for (Coding e : element.getProtocol()) 
        composeCoding("protocol", e);
      composeCode("focus", element.getFocus());
      composeResourceReference("request", element.getRequest());
      composeResourceReference("response", element.getResponse());
      composeString("documentation", element.getDocumentation());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance.DocumentModeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeResourceReference("profile", element.getProfile());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeContraindication(String name, Contraindication element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("patient", element.getPatient());
      composeCodeableConcept("category", element.getCategory());
      composeCode("severity", element.getSeverity());
      for (ResourceReference e : element.getImplicated()) 
        composeResourceReference("implicated", e);
      composeString("detail", element.getDetail());
      composeDateTime("date", element.getDate());
      composeResourceReference("author", element.getAuthor());
      composeIdentifier("identifier", element.getIdentifier());
      composeUri("reference", element.getReference());
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
      composeDateTime("date", element.getDate());
      composeResourceReference("author", element.getAuthor());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDataElement(String name, DataElement element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DataElement.ResourceObservationDefStatusEnumFactory());
      composeDateTime("date", element.getDate());
      composeString("name", element.getName());
      for (CodeableConcept e : element.getCategory()) 
        composeCodeableConcept("category", e);
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      composeString("question", element.getQuestion());
      composeString("definition", element.getDefinition());
      composeString("comments", element.getComments());
      composeString("requirements", element.getRequirements());
      for (StringType e : element.getSynonym()) 
        composeString("synonym", e);
      composeCode("type", element.getType());
      composeType("example", element.getExample());
      composeInteger("maxLength", element.getMaxLength());
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
      composeBoolean("isExtensible", element.getIsExtensible());
      if (element.getConformance() != null)
        composeEnumeration("conformance", element.getConformance(), new DataElement.BindingConformanceEnumFactory());
      composeString("description", element.getDescription());
      composeResourceReference("valueSet", element.getValueSet());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDataElementDataElementMappingComponent(String name, DataElement.DataElementMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("uri", element.getUri());
      composeString("name", element.getName());
      composeString("comments", element.getComments());
      composeString("map", element.getMap());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturer());
      composeString("model", element.getModel());
      composeString("version", element.getVersion());
      composeDate("expiry", element.getExpiry());
      composeString("udi", element.getUdi());
      composeString("lotNumber", element.getLotNumber());
      composeResourceReference("owner", element.getOwner());
      composeResourceReference("location", element.getLocation());
      composeResourceReference("patient", element.getPatient());
      for (Contact e : element.getContact()) 
        composeContact("contact", e);
      composeUri("url", element.getUrl());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceObservationReport(String name, DeviceObservationReport element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeInstant("instant", element.getInstant());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("source", element.getSource());
      composeResourceReference("subject", element.getSubject());
      for (DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent e : element.getVirtualDevice()) 
        composeDeviceObservationReportDeviceObservationReportVirtualDeviceComponent("virtualDevice", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent e : element.getChannel()) 
        composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent("channel", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent e : element.getMetric()) 
        composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent("metric", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDeviceObservationReportDeviceObservationReportVirtualDeviceChannelMetricComponent(String name, DeviceObservationReport.DeviceObservationReportVirtualDeviceChannelMetricComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeResourceReference("observation", element.getObservation());
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
      composeResourceReference("encounter", element.getEncounter());
      composeString("clinicalNotes", element.getClinicalNotes());
      for (ResourceReference e : element.getSupportingInformation()) 
        composeResourceReference("supportingInformation", e);
      for (ResourceReference e : element.getSpecimen()) 
        composeResourceReference("specimen", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      if (element.getPriority() != null)
        composeEnumeration("priority", element.getPriority(), new DiagnosticOrder.DiagnosticOrderPriorityEnumFactory());
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
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
      composeCodeableConcept("description", element.getDescription());
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("actor", element.getActor());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      for (ResourceReference e : element.getSpecimen()) 
        composeResourceReference("specimen", e);
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder.DiagnosticOrderStatusEnumFactory());
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
      composeCodeableConcept("name", element.getName());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticReport.DiagnosticReportStatusEnumFactory());
      composeDateTime("issued", element.getIssued());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      for (ResourceReference e : element.getRequestDetail()) 
        composeResourceReference("requestDetail", e);
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeType("diagnostic", element.getDiagnostic());
      for (ResourceReference e : element.getSpecimen()) 
        composeResourceReference("specimen", e);
      for (ResourceReference e : element.getResult()) 
        composeResourceReference("result", e);
      for (ResourceReference e : element.getImagingStudy()) 
        composeResourceReference("imagingStudy", e);
      for (DiagnosticReport.DiagnosticReportImageComponent e : element.getImage()) 
        composeDiagnosticReportDiagnosticReportImageComponent("image", e);
      composeString("conclusion", element.getConclusion());
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
      composeString("comment", element.getComment());
      composeResourceReference("link", element.getLink());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentManifest(String name, DocumentManifest element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (ResourceReference e : element.getSubject()) 
        composeResourceReference("subject", e);
      for (ResourceReference e : element.getRecipient()) 
        composeResourceReference("recipient", e);
      composeCodeableConcept("type", element.getType());
      for (ResourceReference e : element.getAuthor()) 
        composeResourceReference("author", e);
      composeDateTime("created", element.getCreated());
      composeUri("source", element.getSource());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DocumentManifest.DocumentReferenceStatusEnumFactory());
      composeResourceReference("supercedes", element.getSupercedes());
      composeString("description", element.getDescription());
      composeCodeableConcept("confidentiality", element.getConfidentiality());
      for (ResourceReference e : element.getContent()) 
        composeResourceReference("content", e);
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
      composeCodeableConcept("class", element.getClass_());
      for (ResourceReference e : element.getAuthor()) 
        composeResourceReference("author", e);
      composeResourceReference("custodian", element.getCustodian());
      composeUri("policyManager", element.getPolicyManager());
      composeResourceReference("authenticator", element.getAuthenticator());
      composeDateTime("created", element.getCreated());
      composeInstant("indexed", element.getIndexed());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DocumentReference.DocumentReferenceStatusEnumFactory());
      composeCodeableConcept("docStatus", element.getDocStatus());
      for (DocumentReference.DocumentReferenceRelatesToComponent e : element.getRelatesTo()) 
        composeDocumentReferenceDocumentReferenceRelatesToComponent("relatesTo", e);
      composeString("description", element.getDescription());
      for (CodeableConcept e : element.getConfidentiality()) 
        composeCodeableConcept("confidentiality", e);
      composeCode("primaryLanguage", element.getPrimaryLanguage());
      composeCode("mimeType", element.getMimeType());
      for (UriType e : element.getFormat()) 
        composeUri("format", e);
      composeInteger("size", element.getSize());
      composeString("hash", element.getHash());
      composeUri("location", element.getLocation());
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
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new DocumentReference.DocumentRelationshipTypeEnumFactory());
      composeResourceReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("address", element.getAddress());
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
      composeString("name", element.getName());
      composeString("value", element.getValue());
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

  private void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Encounter.EncounterStateEnumFactory());
      if (element.getClass_() != null)
        composeEnumeration("class", element.getClass_(), new Encounter.EncounterClassEnumFactory());
      for (CodeableConcept e : element.getType()) 
        composeCodeableConcept("type", e);
      composeResourceReference("subject", element.getSubject());
      for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
        composeEncounterEncounterParticipantComponent("participant", e);
      composeResourceReference("fulfills", element.getFulfills());
      composePeriod("period", element.getPeriod());
      composeDuration("length", element.getLength());
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      for (Encounter.EncounterLocationComponent e : element.getLocation()) 
        composeEncounterEncounterLocationComponent("location", e);
      composeResourceReference("serviceProvider", element.getServiceProvider());
      composeResourceReference("partOf", element.getPartOf());
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
      composeResourceReference("individual", element.getIndividual());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeResourceReference("origin", element.getOrigin());
      composeCodeableConcept("admitSource", element.getAdmitSource());
      composePeriod("period", element.getPeriod());
      for (Encounter.EncounterHospitalizationAccomodationComponent e : element.getAccomodation()) 
        composeEncounterEncounterHospitalizationAccomodationComponent("accomodation", e);
      composeCodeableConcept("diet", element.getDiet());
      for (CodeableConcept e : element.getSpecialCourtesy()) 
        composeCodeableConcept("specialCourtesy", e);
      for (CodeableConcept e : element.getSpecialArrangement()) 
        composeCodeableConcept("specialArrangement", e);
      composeResourceReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeResourceReference("dischargeDiagnosis", element.getDischargeDiagnosis());
      composeBoolean("reAdmission", element.getReAdmission());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeResourceReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeResourceReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("subject", element.getSubject());
      composeDateTime("date", element.getDate());
      composeString("note", element.getNote());
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
      composeString("name", element.getName());
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("born", element.getBorn());
      composeType("age", element.getAge());
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
      composeBackboneElements(element);
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
        composeEnumeration("type", element.getType(), new Group.GroupTypeEnumFactory());
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
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
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
      for (ResourceReference e : element.getOrder()) 
        composeResourceReference("order", e);
        for (Enumeration<ImagingStudy.ImagingModality> e : element.getModality()) 
          composeEnumeration("modality", e, new ImagingStudy.ImagingModalityEnumFactory());
      composeResourceReference("referrer", element.getReferrer());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory());
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
      composeBackboneElements(element);
      composeInteger("number", element.getNumber());
      if (element.getModality() != null)
        composeEnumeration("modality", element.getModality(), new ImagingStudy.ModalityEnumFactory());
      composeOid("uid", element.getUid());
      composeString("description", element.getDescription());
      composeInteger("numberOfInstances", element.getNumberOfInstances());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy.InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrl());
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
      composeBackboneElements(element);
      composeInteger("number", element.getNumber());
      composeOid("uid", element.getUid());
      composeOid("sopclass", element.getSopclass());
      composeString("type", element.getType());
      composeString("title", element.getTitle());
      composeUri("url", element.getUrl());
      composeResourceReference("attachment", element.getAttachment());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("date", element.getDate());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeResourceReference("subject", element.getSubject());
      composeBoolean("refusedIndicator", element.getRefusedIndicator());
      composeBoolean("reported", element.getReported());
      composeResourceReference("performer", element.getPerformer());
      composeResourceReference("requester", element.getRequester());
      composeResourceReference("manufacturer", element.getManufacturer());
      composeResourceReference("location", element.getLocation());
      composeString("lotNumber", element.getLotNumber());
      composeDate("expirationDate", element.getExpirationDate());
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
      composeBackboneElements(element);
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

  private void composeImmunizationRecommendation(String name, ImmunizationRecommendation element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("subject", element.getSubject());
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
      composeDateTime("date", element.getDate());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeInteger("doseNumber", element.getDoseNumber());
      composeCodeableConcept("forecastStatus", element.getForecastStatus());
      for (ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent e : element.getDateCriterion()) 
        composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent("dateCriterion", e);
      composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent("protocol", element.getProtocol());
      for (ResourceReference e : element.getSupportingImmunization()) 
        composeResourceReference("supportingImmunization", e);
      for (ResourceReference e : element.getSupportingPatientInformation()) 
        composeResourceReference("supportingPatientInformation", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationDateCriterionComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTime("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeImmunizationRecommendationImmunizationRecommendationRecommendationProtocolComponent(String name, ImmunizationRecommendation.ImmunizationRecommendationRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeInteger("doseSequence", element.getDoseSequence());
      composeString("description", element.getDescription());
      composeResourceReference("authority", element.getAuthority());
      composeString("series", element.getSeries());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeDateTime("date", element.getDate());
      composeBoolean("ordered", element.getOrdered());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new List_.ListModeEnumFactory());
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
      composeBoolean("deleted", element.getDeleted());
      composeDateTime("date", element.getDate());
      composeResourceReference("item", element.getItem());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("name", element.getName());
      composeString("description", element.getDescription());
      composeCodeableConcept("type", element.getType());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeAddress("address", element.getAddress());
      composeCodeableConcept("physicalType", element.getPhysicalType());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeResourceReference("managingOrganization", element.getManagingOrganization());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Location.LocationStatusEnumFactory());
      composeResourceReference("partOf", element.getPartOf());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Location.LocationModeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeDecimal("longitude", element.getLongitude());
      composeDecimal("latitude", element.getLatitude());
      composeDecimal("altitude", element.getAltitude());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Media.MediaTypeEnumFactory());
      composeCodeableConcept("subtype", element.getSubtype());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeString("deviceName", element.getDeviceName());
      composeInteger("height", element.getHeight());
      composeInteger("width", element.getWidth());
      composeInteger("frames", element.getFrames());
      composeInteger("length", element.getLength());
      composeAttachment("content", element.getContent());
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
        composeEnumeration("kind", element.getKind(), new Medication.MedicationKindEnumFactory());
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
      composeResourceReference("item", element.getItem());
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
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationAdministration.MedicationAdminStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("practitioner", element.getPractitioner());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("prescription", element.getPrescription());
      composeBoolean("wasNotGiven", element.getWasNotGiven());
      for (CodeableConcept e : element.getReasonNotGiven()) 
        composeCodeableConcept("reasonNotGiven", e);
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      for (ResourceReference e : element.getDevice()) 
        composeResourceReference("device", e);
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
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("dispenser", element.getDispenser());
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
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationDispense.MedicationDispenseStatusEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("medication", element.getMedication());
      composeDateTime("whenPrepared", element.getWhenPrepared());
      composeDateTime("whenHandedOver", element.getWhenHandedOver());
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
      composeBackboneElements(element);
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
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

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
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
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("dateWritten", element.getDateWritten());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationPrescription.MedicationPrescriptionStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("prescriber", element.getPrescriber());
      composeResourceReference("encounter", element.getEncounter());
      composeType("reason", element.getReason());
      composeResourceReference("medication", element.getMedication());
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
      composeString("text", element.getText());
      composeCodeableConcept("additionalInstructions", element.getAdditionalInstructions());
      composeType("timing", element.getTiming());
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
      composeResourceReference("medication", element.getMedication());
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
      composeBackboneElements(element);
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
      for (ResourceReference e : element.getDevice()) 
        composeResourceReference("device", e);
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
      composeSchedule("timing", element.getTiming());
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
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeId("identifier", element.getIdentifier());
      composeInstant("timestamp", element.getTimestamp());
      composeCoding("event", element.getEvent());
      composeMessageHeaderMessageHeaderResponseComponent("response", element.getResponse());
      composeMessageHeaderMessageSourceComponent("source", element.getSource());
      for (MessageHeader.MessageDestinationComponent e : element.getDestination()) 
        composeMessageHeaderMessageDestinationComponent("destination", e);
      composeResourceReference("enterer", element.getEnterer());
      composeResourceReference("author", element.getAuthor());
      composeResourceReference("receiver", element.getReceiver());
      composeResourceReference("responsible", element.getResponsible());
      composeCodeableConcept("reason", element.getReason());
      for (ResourceReference e : element.getData()) 
        composeResourceReference("data", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageHeaderResponseComponent(String name, MessageHeader.MessageHeaderResponseComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identifier", element.getIdentifier());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new MessageHeader.ResponseCodeEnumFactory());
      composeResourceReference("details", element.getDetails());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageSourceComponent(String name, MessageHeader.MessageSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      composeString("software", element.getSoftware());
      composeString("version", element.getVersion());
      composeContact("contact", element.getContact());
      composeUri("endpoint", element.getEndpoint());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeMessageHeaderMessageDestinationComponent(String name, MessageHeader.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      composeResourceReference("target", element.getTarget());
      composeUri("endpoint", element.getEndpoint());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamespace(String name, Namespace element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Namespace.NamespaceTypeEnumFactory());
      composeString("name", element.getName());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Namespace.NamespaceStatusEnumFactory());
      composeCode("country", element.getCountry());
      composeCodeableConcept("category", element.getCategory());
      composeString("responsible", element.getResponsible());
      composeString("description", element.getDescription());
      composeString("usage", element.getUsage());
      for (Namespace.NamespaceUniqueIdComponent e : element.getUniqueId()) 
        composeNamespaceNamespaceUniqueIdComponent("uniqueId", e);
      composeNamespaceNamespaceContactComponent("contact", element.getContact());
      composeResourceReference("replacedBy", element.getReplacedBy());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamespaceNamespaceUniqueIdComponent(String name, Namespace.NamespaceUniqueIdComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Namespace.NamespaceIdentifierTypeEnumFactory());
      composeString("value", element.getValue());
      composeBoolean("preferred", element.getPreferred());
      composePeriod("period", element.getPeriod());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeNamespaceNamespaceContactComponent(String name, Namespace.NamespaceContactComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeHumanName("name", element.getName());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
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
        composeEnumeration("status", element.getStatus(), new Observation.ObservationStatusEnumFactory());
      if (element.getReliability() != null)
        composeEnumeration("reliability", element.getReliability(), new Observation.ObservationReliabilityEnumFactory());
      composeCodeableConcept("bodySite", element.getBodySite());
      composeCodeableConcept("method", element.getMethod());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("specimen", element.getSpecimen());
      for (ResourceReference e : element.getPerformer()) 
        composeResourceReference("performer", e);
      composeResourceReference("encounter", element.getEncounter());
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
      composeString("text", element.getText());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeObservationObservationRelatedComponent(String name, Observation.ObservationRelatedComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Observation.ObservationRelationshiptypesEnumFactory());
      composeResourceReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOperationDefinition(String name, OperationDefinition element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeUri("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("title", element.getTitle());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new OperationDefinition.ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      if (element.getKind() != null)
        composeEnumeration("kind", element.getKind(), new OperationDefinition.OperationKindEnumFactory());
      composeCode("name", element.getName());
      composeString("notes", element.getNotes());
      composeResourceReference("base", element.getBase());
      composeBoolean("system", element.getSystem());
      for (CodeType e : element.getType()) 
        composeCode("type", e);
      composeBoolean("instance", element.getInstance());
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
      composeCode("name", element.getName());
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new OperationDefinition.OperationParameterUseEnumFactory());
      composeInteger("min", element.getMin());
      composeString("max", element.getMax());
      composeString("documentation", element.getDocumentation());
      composeCoding("type", element.getType());
      composeResourceReference("profile", element.getProfile());
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
      composeBackboneElements(element);
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new OperationOutcome.IssueSeverityEnumFactory());
      composeCoding("type", element.getType());
      composeString("details", element.getDetails());
      for (StringType e : element.getLocation()) 
        composeString("location", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeDateTime("date", element.getDate());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeResourceReference("target", element.getTarget());
      composeType("reason", element.getReason());
      composeResourceReference("authority", element.getAuthority());
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
      composeBackboneElements(element);
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
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("request", element.getRequest());
      composeDateTime("date", element.getDate());
      composeResourceReference("who", element.getWho());
      composeType("authority", element.getAuthority());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new OrderResponse.OrderOutcomeCodeEnumFactory());
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
      composeString("name", element.getName());
      composeCodeableConcept("type", element.getType());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      composeResourceReference("partOf", element.getPartOf());
      for (Organization.OrganizationContactComponent e : element.getContact()) 
        composeOrganizationOrganizationContactComponent("contact", e);
      for (ResourceReference e : element.getLocation()) 
        composeResourceReference("location", e);
      composeBoolean("active", element.getActive());
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
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeAddress("address", element.getAddress());
      if (element.getGender() != null)
        composeEnumeration("gender", element.getGender(), new Organization.AdministrativeGenderEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeOther(String name, Other element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDate("created", element.getCreated());
      xml.close(FHIR_NS, name);
    }
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      for (HumanName e : element.getName()) 
        composeHumanName("name", e);
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      if (element.getGender() != null)
        composeEnumeration("gender", element.getGender(), new Patient.AdministrativeGenderEnumFactory());
      composeDateTime("birthDate", element.getBirthDate());
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
      for (ResourceReference e : element.getCareProvider()) 
        composeResourceReference("careProvider", e);
      composeResourceReference("managingOrganization", element.getManagingOrganization());
      for (Patient.PatientLinkComponent e : element.getLink()) 
        composePatientPatientLinkComponent("link", e);
      composeBoolean("active", element.getActive());
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
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeAddress("address", element.getAddress());
      if (element.getGender() != null)
        composeEnumeration("gender", element.getGender(), new Patient.AdministrativeGenderEnumFactory());
      composeResourceReference("organization", element.getOrganization());
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
      composeResourceReference("other", element.getOther());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Patient.LinkTypeEnumFactory());
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
      composeHumanName("name", element.getName());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      for (Address e : element.getAddress()) 
        composeAddress("address", e);
      if (element.getGender() != null)
        composeEnumeration("gender", element.getGender(), new Practitioner.AdministrativeGenderEnumFactory());
      composeDateTime("birthDate", element.getBirthDate());
      for (Attachment e : element.getPhoto()) 
        composeAttachment("photo", e);
      composeResourceReference("organization", element.getOrganization());
      for (CodeableConcept e : element.getRole()) 
        composeCodeableConcept("role", e);
      for (CodeableConcept e : element.getSpecialty()) 
        composeCodeableConcept("specialty", e);
      composePeriod("period", element.getPeriod());
      for (ResourceReference e : element.getLocation()) 
        composeResourceReference("location", e);
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
      composeResourceReference("issuer", element.getIssuer());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getBodySite()) 
        composeCodeableConcept("bodySite", e);
      for (CodeableConcept e : element.getIndication()) 
        composeCodeableConcept("indication", e);
      for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
        composeProcedureProcedurePerformerComponent("performer", e);
      composePeriod("date", element.getDate());
      composeResourceReference("encounter", element.getEncounter());
      composeString("outcome", element.getOutcome());
      for (ResourceReference e : element.getReport()) 
        composeResourceReference("report", e);
      for (CodeableConcept e : element.getComplication()) 
        composeCodeableConcept("complication", e);
      composeString("followUp", element.getFollowUp());
      for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
        composeProcedureProcedureRelatedItemComponent("relatedItem", e);
      composeString("notes", element.getNotes());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeResourceReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Procedure.ProcedureRelationshipTypeEnumFactory());
      composeResourceReference("target", element.getTarget());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeUri("url", element.getUrl());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      for (Coding e : element.getCode()) 
        composeCoding("code", e);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Profile.ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      composeString("requirements", element.getRequirements());
      composeId("fhirVersion", element.getFhirVersion());
      for (Profile.ProfileMappingComponent e : element.getMapping()) 
        composeProfileProfileMappingComponent("mapping", e);
      for (Profile.ProfileStructureComponent e : element.getStructure()) 
        composeProfileProfileStructureComponent("structure", e);
      for (Profile.ProfileExtensionDefnComponent e : element.getExtensionDefn()) 
        composeProfileProfileExtensionDefnComponent("extensionDefn", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileMappingComponent(String name, Profile.ProfileMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identity", element.getIdentity());
      composeUri("uri", element.getUri());
      composeString("name", element.getName());
      composeString("comments", element.getComments());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileStructureComponent(String name, Profile.ProfileStructureComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("type", element.getType());
      composeUri("base", element.getBase());
      composeString("name", element.getName());
      composeBoolean("publish", element.getPublish());
      composeString("purpose", element.getPurpose());
      composeProfileConstraintComponent("snapshot", element.getSnapshot());
      composeProfileConstraintComponent("differential", element.getDifferential());
      for (Profile.ProfileStructureSearchParamComponent e : element.getSearchParam()) 
        composeProfileProfileStructureSearchParamComponent("searchParam", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileConstraintComponent(String name, Profile.ConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      for (Profile.ElementComponent e : element.getElement()) 
        composeProfileElementComponent("element", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementComponent(String name, Profile.ElementComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("path", element.getPath());
        for (Enumeration<Profile.PropertyRepresentation> e : element.getRepresentation()) 
          composeEnumeration("representation", e, new Profile.PropertyRepresentationEnumFactory());
      composeString("name", element.getName());
      composeProfileElementSlicingComponent("slicing", element.getSlicing());
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementSlicingComponent(String name, Profile.ElementSlicingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("discriminator", element.getDiscriminator());
      composeBoolean("ordered", element.getOrdered());
      if (element.getRules() != null)
        composeEnumeration("rules", element.getRules(), new Profile.ResourceSlicingRulesEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionComponent(String name, Profile.ElementDefinitionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("short", element.getShort());
      composeString("formal", element.getFormal());
      composeString("comments", element.getComments());
      composeString("requirements", element.getRequirements());
      for (StringType e : element.getSynonym()) 
        composeString("synonym", e);
      composeInteger("min", element.getMin());
      composeString("max", element.getMax());
      for (Profile.TypeRefComponent e : element.getType()) 
        composeProfileTypeRefComponent("type", e);
      composeString("nameReference", element.getNameReference());
      composeType("value", element.getValue());
      composeType("example", element.getExample());
      composeInteger("maxLength", element.getMaxLength());
      for (IdType e : element.getCondition()) 
        composeId("condition", e);
      for (Profile.ElementDefinitionConstraintComponent e : element.getConstraint()) 
        composeProfileElementDefinitionConstraintComponent("constraint", e);
      composeBoolean("mustSupport", element.getMustSupport());
      composeBoolean("isModifier", element.getIsModifier());
      composeProfileElementDefinitionBindingComponent("binding", element.getBinding());
      for (Profile.ElementDefinitionMappingComponent e : element.getMapping()) 
        composeProfileElementDefinitionMappingComponent("mapping", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileTypeRefComponent(String name, Profile.TypeRefComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("code", element.getCode());
      composeUri("profile", element.getProfile());
        for (Enumeration<Profile.ResourceAggregationMode> e : element.getAggregation()) 
          composeEnumeration("aggregation", e, new Profile.ResourceAggregationModeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionConstraintComponent(String name, Profile.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("key", element.getKey());
      composeString("name", element.getName());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new Profile.ConstraintSeverityEnumFactory());
      composeString("human", element.getHuman());
      composeString("xpath", element.getXpath());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionBindingComponent(String name, Profile.ElementDefinitionBindingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      composeBoolean("isExtensible", element.getIsExtensible());
      if (element.getConformance() != null)
        composeEnumeration("conformance", element.getConformance(), new Profile.BindingConformanceEnumFactory());
      composeString("description", element.getDescription());
      composeType("reference", element.getReference());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileElementDefinitionMappingComponent(String name, Profile.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeId("identity", element.getIdentity());
      composeString("map", element.getMap());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileStructureSearchParamComponent(String name, Profile.ProfileStructureSearchParamComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("name", element.getName());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Profile.SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeString("xpath", element.getXpath());
      for (CodeType e : element.getTarget()) 
        composeCode("target", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProfileProfileExtensionDefnComponent(String name, Profile.ProfileExtensionDefnComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      if (element.getContextType() != null)
        composeEnumeration("contextType", element.getContextType(), new Profile.ExtensionContextEnumFactory());
      for (StringType e : element.getContext()) 
        composeString("context", e);
      for (Profile.ElementComponent e : element.getElement()) 
        composeProfileElementComponent("element", e);
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
      composePeriod("period", element.getPeriod());
      composeInstant("recorded", element.getRecorded());
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("location", element.getLocation());
      for (UriType e : element.getPolicy()) 
        composeUri("policy", e);
      for (Provenance.ProvenanceAgentComponent e : element.getAgent()) 
        composeProvenanceProvenanceAgentComponent("agent", e);
      for (Provenance.ProvenanceEntityComponent e : element.getEntity()) 
        composeProvenanceProvenanceEntityComponent("entity", e);
      composeString("integritySignature", element.getIntegritySignature());
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
      composeUri("reference", element.getReference());
      composeString("display", element.getDisplay());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      if (element.getRole() != null)
        composeEnumeration("role", element.getRole(), new Provenance.ProvenanceEntityRoleEnumFactory());
      composeCoding("type", element.getType());
      composeUri("reference", element.getReference());
      composeString("display", element.getDisplay());
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeUri("identifier", element.getIdentifier());
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
      composeUri("identifier", element.getIdentifier());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new Query.QueryOutcomeEnumFactory());
      composeInteger("total", element.getTotal());
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
      for (ResourceReference e : element.getReference()) 
        composeResourceReference("reference", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeString("version", element.getVersion());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Questionnaire.QuestionnaireStatusEnumFactory());
      composeDateTime("date", element.getDate());
      composeString("publisher", element.getPublisher());
      composeQuestionnaireGroupComponent("group", element.getGroup());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkId());
      composeString("title", element.getTitle());
      for (Coding e : element.getConcept()) 
        composeCoding("concept", e);
      composeString("text", element.getText());
      composeBoolean("required", element.getRequired());
      composeBoolean("repeats", element.getRepeats());
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
      composeString("linkId", element.getLinkId());
      for (Coding e : element.getConcept()) 
        composeCoding("concept", e);
      composeString("text", element.getText());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Questionnaire.AnswerFormatEnumFactory());
      composeBoolean("required", element.getRequired());
      composeBoolean("repeats", element.getRepeats());
      composeResourceReference("options", element.getOptions());
      for (Questionnaire.GroupComponent e : element.getGroup()) 
        composeQuestionnaireGroupComponent("group", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswers(String name, QuestionnaireAnswers element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("questionnaire", element.getQuestionnaire());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new QuestionnaireAnswers.QuestionnaireAnswersStatusEnumFactory());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDateTime("authored", element.getAuthored());
      composeResourceReference("source", element.getSource());
      composeResourceReference("encounter", element.getEncounter());
      composeQuestionnaireAnswersGroupComponent("group", element.getGroup());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeQuestionnaireAnswersGroupComponent(String name, QuestionnaireAnswers.GroupComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeString("linkId", element.getLinkId());
      composeString("title", element.getTitle());
      composeString("text", element.getText());
      composeResourceReference("subject", element.getSubject());
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
      composeString("linkId", element.getLinkId());
      composeString("text", element.getText());
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
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new ReferralRequest.ReferralstatusEnumFactory());
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("specialty", element.getSpecialty());
      composeCodeableConcept("priority", element.getPriority());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("requester", element.getRequester());
      for (ResourceReference e : element.getRecipient()) 
        composeResourceReference("recipient", e);
      composeResourceReference("encounter", element.getEncounter());
      composeDateTime("dateSent", element.getDateSent());
      composeCodeableConcept("reason", element.getReason());
      composeString("description", element.getDescription());
      for (CodeableConcept e : element.getServiceRequested()) 
        composeCodeableConcept("serviceRequested", e);
      for (ResourceReference e : element.getSupportingInformation()) 
        composeResourceReference("supportingInformation", e);
      composePeriod("fulfillmentTime", element.getFulfillmentTime());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeResourceReference("patient", element.getPatient());
      composeCodeableConcept("relationship", element.getRelationship());
      composeHumanName("name", element.getName());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      if (element.getGender() != null)
        composeEnumeration("gender", element.getGender(), new RelatedPerson.AdministrativeGenderEnumFactory());
      composeAddress("address", element.getAddress());
      for (Attachment e : element.getPhoto()) 
        composeAttachment("photo", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeRiskAssessment(String name, RiskAssessment element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeDateTime("date", element.getDate());
      composeResourceReference("condition", element.getCondition());
      composeResourceReference("performer", element.getPerformer());
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("method", element.getMethod());
      for (ResourceReference e : element.getBasis()) 
        composeResourceReference("basis", e);
      for (RiskAssessment.RiskAssessmentPredictionComponent e : element.getPrediction()) 
        composeRiskAssessmentRiskAssessmentPredictionComponent("prediction", e);
      composeString("mitigation", element.getMitigation());
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
      composeDecimal("relativeRisk", element.getRelativeRisk());
      composeType("when", element.getWhen());
      composeString("rationale", element.getRationale());
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
      composeBackboneElements(element);
      composeCodeableConcept("type", element.getType());
      for (CodeableConcept e : element.getSubtype()) 
        composeCodeableConcept("subtype", e);
      if (element.getAction() != null)
        composeEnumeration("action", element.getAction(), new SecurityEvent.SecurityEventActionEnumFactory());
      composeInstant("dateTime", element.getDateTime());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new SecurityEvent.SecurityEventOutcomeEnumFactory());
      composeString("outcomeDesc", element.getOutcomeDesc());
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
      composeResourceReference("reference", element.getReference());
      composeString("userId", element.getUserId());
      composeString("altId", element.getAltId());
      composeString("name", element.getName());
      composeBoolean("requestor", element.getRequestor());
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
      composeString("identifier", element.getIdentifier());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent.NetworkTypeEnumFactory());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
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
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("reference", element.getReference());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent.ObjectTypeEnumFactory());
      if (element.getRole() != null)
        composeEnumeration("role", element.getRole(), new SecurityEvent.ObjectRoleEnumFactory());
      if (element.getLifecycle() != null)
        composeEnumeration("lifecycle", element.getLifecycle(), new SecurityEvent.ObjectLifecycleEnumFactory());
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeString("name", element.getName());
      composeString("description", element.getDescription());
      composeBase64Binary("query", element.getQuery());
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
      composeString("type", element.getType());
      composeBase64Binary("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSlot(String name, Slot element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      composeResourceReference("availability", element.getAvailability());
      if (element.getFreeBusyType() != null)
        composeEnumeration("freeBusyType", element.getFreeBusyType(), new Slot.SlotstatusEnumFactory());
      composeInstant("start", element.getStart());
      composeInstant("end", element.getEnd());
      composeBoolean("overbooked", element.getOverbooked());
      composeString("comment", element.getComment());
      composeDateTime("lastModified", element.getLastModified());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      for (Identifier e : element.getIdentifier()) 
        composeIdentifier("identifier", e);
      composeCodeableConcept("type", element.getType());
      for (Specimen.SpecimenSourceComponent e : element.getSource()) 
        composeSpecimenSpecimenSourceComponent("source", e);
      composeResourceReference("subject", element.getSubject());
      composeIdentifier("accessionIdentifier", element.getAccessionIdentifier());
      composeDateTime("receivedTime", element.getReceivedTime());
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
      if (element.getRelationship() != null)
        composeEnumeration("relationship", element.getRelationship(), new Specimen.HierarchicalRelationshipTypeEnumFactory());
      for (ResourceReference e : element.getTarget()) 
        composeResourceReference("target", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeResourceReference("collector", element.getCollector());
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
      composeString("description", element.getDescription());
      composeCodeableConcept("procedure", element.getProcedure());
      for (ResourceReference e : element.getAdditive()) 
        composeResourceReference("additive", e);
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
      composeString("description", element.getDescription());
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeResourceReference("additive", element.getAdditive());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubscription(String name, Subscription element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeString("criteria", element.getCriteria());
      for (Contact e : element.getContact()) 
        composeContact("contact", e);
      composeString("reason", element.getReason());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Subscription.SubscriptionStatusEnumFactory());
      composeString("error", element.getError());
      composeSubscriptionSubscriptionChannelComponent("channel", element.getChannel());
      composeInstant("end", element.getEnd());
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
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Subscription.SubscriptionChannelTypeEnumFactory());
      composeUri("url", element.getUrl());
      composeString("payload", element.getPayload());
      composeString("header", element.getHeader());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubscriptionSubscriptionTagComponent(String name, Subscription.SubscriptionTagComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeUri("term", element.getTerm());
      composeUri("scheme", element.getScheme());
      composeString("description", element.getDescription());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("description", element.getDescription());
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
      composeDateTime("expiry", element.getExpiry());
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
      composeResourceReference("substance", element.getSubstance());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      composeResourceAttributes(element);
      xml.open(FHIR_NS, name);
      composeResourceElements(element);
      composeCodeableConcept("kind", element.getKind());
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Supply.ValuesetSupplyStatusEnumFactory());
      composeResourceReference("orderedItem", element.getOrderedItem());
      composeResourceReference("patient", element.getPatient());
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
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Supply.ValuesetSupplyDispenseStatusEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("suppliedItem", element.getSuppliedItem());
      composeResourceReference("supplier", element.getSupplier());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeResourceReference("destination", element.getDestination());
      for (ResourceReference e : element.getReceiver()) 
        composeResourceReference("receiver", e);
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
      composeString("purpose", element.getPurpose());
      composeBoolean("immutable", element.getImmutable());
      composeString("publisher", element.getPublisher());
      for (Contact e : element.getTelecom()) 
        composeContact("telecom", e);
      composeString("description", element.getDescription());
      composeString("copyright", element.getCopyright());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new ValueSet.ValuesetStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeBoolean("extensible", element.getExtensible());
      composeDateTime("date", element.getDate());
      composeDate("stableDate", element.getStableDate());
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
      composeUri("system", element.getSystem());
      composeString("version", element.getVersion());
      composeBoolean("caseSensitive", element.getCaseSensitive());
      for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
        composeValueSetValueSetDefineConceptComponent("concept", e);
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetDefineConceptComponent(String name, ValueSet.ValueSetDefineConceptComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
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
      composeUri("system", element.getSystem());
      composeString("version", element.getVersion());
      for (CodeType e : element.getCode()) 
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
      composeBackboneElements(element);
      composeCode("property", element.getProperty());
      if (element.getOp() != null)
        composeEnumeration("op", element.getOp(), new ValueSet.FilterOperatorEnumFactory());
      composeCode("value", element.getValue());
      xml.close(FHIR_NS, name);
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      composeElementAttributes(element);
      xml.open(FHIR_NS, name);
      composeBackboneElements(element);
      composeIdentifier("identifier", element.getIdentifier());
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
      composeBackboneElements(element);
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
        composeValueSetValueSetExpansionContainsComponent("contains", e);
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
    else if (resource instanceof Appointment)
      composeAppointment("Appointment", (Appointment)resource);
    else if (resource instanceof AppointmentResponse)
      composeAppointmentResponse("AppointmentResponse", (AppointmentResponse)resource);
    else if (resource instanceof Availability)
      composeAvailability("Availability", (Availability)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan("CarePlan", (CarePlan)resource);
    else if (resource instanceof Composition)
      composeComposition("Composition", (Composition)resource);
    else if (resource instanceof ConceptMap)
      composeConceptMap("ConceptMap", (ConceptMap)resource);
    else if (resource instanceof Condition)
      composeCondition("Condition", (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance("Conformance", (Conformance)resource);
    else if (resource instanceof Contraindication)
      composeContraindication("Contraindication", (Contraindication)resource);
    else if (resource instanceof DataElement)
      composeDataElement("DataElement", (DataElement)resource);
    else if (resource instanceof Device)
      composeDevice("Device", (Device)resource);
    else if (resource instanceof DeviceObservationReport)
      composeDeviceObservationReport("DeviceObservationReport", (DeviceObservationReport)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder("DiagnosticOrder", (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport("DiagnosticReport", (DiagnosticReport)resource);
    else if (resource instanceof DocumentManifest)
      composeDocumentManifest("DocumentManifest", (DocumentManifest)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference("DocumentReference", (DocumentReference)resource);
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof Group)
      composeGroup("Group", (Group)resource);
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
    else if (resource instanceof Namespace)
      composeNamespace("Namespace", (Namespace)resource);
    else if (resource instanceof Observation)
      composeObservation("Observation", (Observation)resource);
    else if (resource instanceof OperationDefinition)
      composeOperationDefinition("OperationDefinition", (OperationDefinition)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome("OperationOutcome", (OperationOutcome)resource);
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
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
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

