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

// Generated on Sun, Sep 22, 2013 06:57+1000 for FHIR v0.11

import org.hl7.fhir.instance.model.*;
import org.hl7.fhir.instance.model.Integer;
import org.hl7.fhir.instance.model.Boolean;
import org.hl7.fhir.utilities.Utilities;
import java.net.*;
import java.math.*;

public class JsonComposer extends JsonComposerBase {

  private void composeElement(Element element) throws Exception {
    if (element.getXmlId() != null)
      prop("_id", element.getXmlId());
    if (element.getExtensions().size() > 0) {
      openArray("extension");
      for (Extension ex : element.getExtensions())
        composeExtension(null, ex);
      closeArray();
    }
  }

  private <E extends Enum<E>> void composeEnumeration(String name, Enumeration<E> value, EnumFactory e) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", e.toCode(value.getValue()));
      close();
    }    
  }    

  private void composeInteger(String name, Integer value) throws Exception {
    if (value != null) {
      open(name);
      composeElement(value);
        prop("value", java.lang.Integer.valueOf(value.getValue()));
      close();
    }    
  }    

  private void composeDateTime(String name, DateTime value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeCode(String name, Code value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeDate(String name, Date value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeDecimal(String name, Decimal value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeUri(String name, Uri value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeId(String name, Id value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeBase64Binary(String name, Base64Binary value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeOid(String name, Oid value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeString(String name, String_ value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeBoolean(String name, Boolean value) throws Exception {
    if (value != null) {
      open(name);
      composeElement(value);
        prop("value", value.getValue());
      close();
    }    
  }    

  private void composeUuid(String name, Uuid value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || !Utilities.noString(value.getValue()))) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeInstant(String name, Instant value) throws Exception {
    if (value != null && (!Utilities.noString(value.getXmlId()) || value.hasExtensions() || value.getValue() != null)) {
      open(name);
      composeElement(value);
      if (value.getValue() != null) 
        prop("value", toString(value.getValue()));
      close();
    }    
  }    

  private void composeExtension(String name, Extension element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("url", element.getUrl());
      composeBoolean("isModifier", element.getIsModifier());
      composeType("value", element.getValue());
      close();
    }
  }

  private void composeNarrative(String name, Narrative element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Narrative().new NarrativeStatusEnumFactory());
      composeXhtml("div", element.getDiv());
      close();
    }
  }

  private void composePeriod(String name, Period element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTime("start", element.getStart());
      composeDateTime("end", element.getEnd());
      close();
    }
  }

  private void composeCoding(String name, Coding element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      close();
    }
  }

  private void composeRange(String name, Range element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("low", element.getLow());
      composeQuantity("high", element.getHigh());
      close();
    }
  }

  private void composeQuantity(String name, Quantity element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Quantity().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeChoice(String name, Choice element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      if (element.getOption().size() > 0) {
        openArray("option");
        for (Choice.ChoiceOptionComponent e : element.getOption()) 
          composeChoiceChoiceOptionComponent(null, e);
        closeArray();
      };
      composeBoolean("isOrdered", element.getIsOrdered());
      close();
    }
  }

  private void composeChoiceChoiceOptionComponent(String name, Choice.ChoiceOptionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      close();
    }
  }

  private void composeAttachment(String name, Attachment element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("contentType", element.getContentType());
      composeCode("language", element.getLanguage());
      composeBase64Binary("data", element.getData());
      composeUri("url", element.getUrl());
      composeInteger("size", element.getSize());
      composeBase64Binary("hash", element.getHash());
      composeString("title", element.getTitle());
      close();
    }
  }

  private void composeRatio(String name, Ratio element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("numerator", element.getNumerator());
      composeQuantity("denominator", element.getDenominator());
      close();
    }
  }

  private void composeSampledData(String name, SampledData element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeQuantity("origin", element.getOrigin());
      composeDecimal("period", element.getPeriod());
      composeDecimal("factor", element.getFactor());
      composeDecimal("lowerLimit", element.getLowerLimit());
      composeDecimal("upperLimit", element.getUpperLimit());
      composeInteger("dimensions", element.getDimensions());
      composeString("data", element.getData());
      close();
    }
  }

  private void composeResourceReference(String name, ResourceReference element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("type", element.getType());
      composeString("reference", element.getReference());
      composeString("display", element.getDisplay());
      close();
    }
  }

  private void composeCodeableConcept(String name, CodeableConcept element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCoding().size() > 0) {
        openArray("coding");
        for (Coding e : element.getCoding()) 
          composeCoding(null, e);
        closeArray();
      };
      composeString("text", element.getText());
      composeString("primary", element.getPrimary());
      close();
    }
  }

  private void composeIdentifier(String name, Identifier element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Identifier().new IdentifierUseEnumFactory());
      composeString("label", element.getLabel());
      composeUri("system", element.getSystem());
      composeString("key", element.getKey());
      composePeriod("period", element.getPeriod());
      composeResourceReference("assigner", element.getAssigner());
      close();
    }
  }

  private void composeAge(String name, Age element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Age().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeCount(String name, Count element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Count().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeMoney(String name, Money element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Money().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeDistance(String name, Distance element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Distance().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeDuration(String name, Duration element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("value", element.getValue());
      if (element.getComparator() != null)
        composeEnumeration("comparator", element.getComparator(), new Duration().new QuantityComparatorEnumFactory());
      composeString("units", element.getUnits());
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      close();
    }
  }

  private void composeSchedule(String name, Schedule element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Period e : element.getEvent()) 
          composePeriod(null, e);
        closeArray();
      };
      composeScheduleScheduleRepeatComponent("repeat", element.getRepeat());
      close();
    }
  }

  private void composeScheduleScheduleRepeatComponent(String name, Schedule.ScheduleRepeatComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("frequency", element.getFrequency());
      if (element.getWhen() != null)
        composeEnumeration("when", element.getWhen(), new Schedule().new EventTimingEnumFactory());
      composeDecimal("duration", element.getDuration());
      if (element.getUnits() != null)
        composeEnumeration("units", element.getUnits(), new Schedule().new UnitsOfTimeEnumFactory());
      composeInteger("count", element.getCount());
      composeDateTime("end", element.getEnd());
      close();
    }
  }

  private void composeContact(String name, Contact element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getSystem() != null)
        composeEnumeration("system", element.getSystem(), new Contact().new ContactSystemEnumFactory());
      composeString("value", element.getValue());
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Contact().new ContactUseEnumFactory());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeAddress(String name, Address element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new Address().new AddressUseEnumFactory());
      composeString("text", element.getText());
      if (element.getLine().size() > 0) {
        openArray("line");
        for (String_ e : element.getLine()) 
          composeString(null, e);
        closeArray();
      };
      composeString("city", element.getCity());
      composeString("state", element.getState());
      composeString("zip", element.getZip());
      composeString("country", element.getCountry());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeHumanName(String name, HumanName element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getUse() != null)
        composeEnumeration("use", element.getUse(), new HumanName().new NameUseEnumFactory());
      composeString("text", element.getText());
      if (element.getFamily().size() > 0) {
        openArray("family");
        for (String_ e : element.getFamily()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getGiven().size() > 0) {
        openArray("given");
        for (String_ e : element.getGiven()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getPrefix().size() > 0) {
        openArray("prefix");
        for (String_ e : element.getPrefix()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getSuffix().size() > 0) {
        openArray("suffix");
        for (String_ e : element.getSuffix()) 
          composeString(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeResourceElements(Resource element) throws Exception {
    composeElement(element);
    if (element.getText() != null)
      composeNarrative("text", element.getText());
    if (element.getContained().size() > 0) {
      openArray("contained");
      for (Resource r : element.getContained()) {
        if (r.getXmlId() == null)
          throw new Exception("Contained Resource has no id - one must be assigned"); // we can't assign one here - what points to it?
        open(null);
        composeResource(r);
        close();
      }
      closeArray();
    }
  }

  private void composeAdverseReaction(String name, AdverseReaction element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeDateTime("reactionDate", element.getReactionDate());
      composeResourceReference("subject", element.getSubject());
      composeBoolean("didNotOccurFlag", element.getDidNotOccurFlag());
      composeResourceReference("recorder", element.getRecorder());
      if (element.getSymptom().size() > 0) {
        openArray("symptom");
        for (AdverseReaction.AdverseReactionSymptomComponent e : element.getSymptom()) 
          composeAdverseReactionAdverseReactionSymptomComponent(null, e);
        closeArray();
      };
      if (element.getExposure().size() > 0) {
        openArray("exposure");
        for (AdverseReaction.AdverseReactionExposureComponent e : element.getExposure()) 
          composeAdverseReactionAdverseReactionExposureComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeAdverseReactionAdverseReactionSymptomComponent(String name, AdverseReaction.AdverseReactionSymptomComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new AdverseReaction().new ReactionSeverityEnumFactory());
      close();
    }
  }

  private void composeAdverseReactionAdverseReactionExposureComponent(String name, AdverseReaction.AdverseReactionExposureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTime("exposureDate", element.getExposureDate());
      if (element.getExposureType() != null)
        composeEnumeration("exposureType", element.getExposureType(), new AdverseReaction().new ExposureTypeEnumFactory());
      if (element.getCausalityExpectation() != null)
        composeEnumeration("causalityExpectation", element.getCausalityExpectation(), new AdverseReaction().new CausalityExpectationEnumFactory());
      composeResourceReference("substance", element.getSubstance());
      close();
    }
  }

  private void composeAlert(String name, Alert element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Alert().new AlertStatusEnumFactory());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeString("note", element.getNote());
      close();
    }
  }

  private void composeAllergyIntolerance(String name, AllergyIntolerance element) throws Exception {
    if (element != null) {
      open(name);
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
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (ResourceReference e : element.getReaction()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getSensitivityTest().size() > 0) {
        openArray("sensitivityTest");
        for (ResourceReference e : element.getSensitivityTest()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeCarePlan(String name, CarePlan element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("patient", element.getPatient());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanStatusEnumFactory());
      composePeriod("period", element.getPeriod());
      composeDateTime("modified", element.getModified());
      if (element.getConcern().size() > 0) {
        openArray("concern");
        for (ResourceReference e : element.getConcern()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (CarePlan.CarePlanParticipantComponent e : element.getParticipant()) 
          composeCarePlanCarePlanParticipantComponent(null, e);
        closeArray();
      };
      if (element.getGoal().size() > 0) {
        openArray("goal");
        for (CarePlan.CarePlanGoalComponent e : element.getGoal()) 
          composeCarePlanCarePlanGoalComponent(null, e);
        closeArray();
      };
      if (element.getActivity().size() > 0) {
        openArray("activity");
        for (CarePlan.CarePlanActivityComponent e : element.getActivity()) 
          composeCarePlanCarePlanActivityComponent(null, e);
        closeArray();
      };
      composeString("notes", element.getNotes());
      close();
    }
  }

  private void composeCarePlanCarePlanParticipantComponent(String name, CarePlan.CarePlanParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("role", element.getRole());
      composeResourceReference("member", element.getMember());
      close();
    }
  }

  private void composeCarePlanCarePlanGoalComponent(String name, CarePlan.CarePlanGoalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanGoalStatusEnumFactory());
      composeString("notes", element.getNotes());
      close();
    }
  }

  private void composeCarePlanCarePlanActivityComponent(String name, CarePlan.CarePlanActivityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCategory() != null)
        composeEnumeration("category", element.getCategory(), new CarePlan().new CarePlanActivityCategoryEnumFactory());
      composeCodeableConcept("code", element.getCode());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new CarePlan().new CarePlanActivityStatusEnumFactory());
      composeBoolean("prohibited", element.getProhibited());
      composeType("timing", element.getTiming());
      composeResourceReference("location", element.getLocation());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (ResourceReference e : element.getPerformer()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeResourceReference("product", element.getProduct());
      composeQuantity("dailyAmount", element.getDailyAmount());
      composeQuantity("quantity", element.getQuantity());
      composeString("details", element.getDetails());
      if (element.getActionTaken().size() > 0) {
        openArray("actionTaken");
        for (ResourceReference e : element.getActionTaken()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeString("notes", element.getNotes());
      close();
    }
  }

  private void composeCondition(String name, Condition element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("asserter", element.getAsserter());
      composeDate("dateAsserted", element.getDateAsserted());
      composeCodeableConcept("code", element.getCode());
      composeCodeableConcept("category", element.getCategory());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Condition().new ConditionStatusEnumFactory());
      composeCodeableConcept("certainty", element.getCertainty());
      composeCodeableConcept("severity", element.getSeverity());
      composeType("onset", element.getOnset());
      composeType("abatement", element.getAbatement());
      composeConditionConditionStageComponent("stage", element.getStage());
      if (element.getEvidence().size() > 0) {
        openArray("evidence");
        for (Condition.ConditionEvidenceComponent e : element.getEvidence()) 
          composeConditionConditionEvidenceComponent(null, e);
        closeArray();
      };
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Condition.ConditionLocationComponent e : element.getLocation()) 
          composeConditionConditionLocationComponent(null, e);
        closeArray();
      };
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Condition.ConditionRelatedItemComponent e : element.getRelatedItem()) 
          composeConditionConditionRelatedItemComponent(null, e);
        closeArray();
      };
      composeString("notes", element.getNotes());
      close();
    }
  }

  private void composeConditionConditionStageComponent(String name, Condition.ConditionStageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("summary", element.getSummary());
      if (element.getAssessment().size() > 0) {
        openArray("assessment");
        for (ResourceReference e : element.getAssessment()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConditionConditionEvidenceComponent(String name, Condition.ConditionEvidenceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConditionConditionLocationComponent(String name, Condition.ConditionLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeString("detail", element.getDetail());
      close();
    }
  }

  private void composeConditionConditionRelatedItemComponent(String name, Condition.ConditionRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Condition().new ConditionRelationshipTypeEnumFactory());
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeConformance(String name, Conformance element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeString("description", element.getDescription());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Conformance().new ConformanceStatementStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      composeConformanceConformanceSoftwareComponent("software", element.getSoftware());
      composeConformanceConformanceImplementationComponent("implementation", element.getImplementation());
      composeId("fhirVersion", element.getFhirVersion());
      composeBoolean("acceptUnknown", element.getAcceptUnknown());
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (Code e : element.getFormat()) 
          composeCode(null, e);
        closeArray();
      };
      if (element.getRest().size() > 0) {
        openArray("rest");
        for (Conformance.ConformanceRestComponent e : element.getRest()) 
          composeConformanceConformanceRestComponent(null, e);
        closeArray();
      };
      if (element.getMessaging().size() > 0) {
        openArray("messaging");
        for (Conformance.ConformanceMessagingComponent e : element.getMessaging()) 
          composeConformanceConformanceMessagingComponent(null, e);
        closeArray();
      };
      if (element.getDocument().size() > 0) {
        openArray("document");
        for (Conformance.ConformanceDocumentComponent e : element.getDocument()) 
          composeConformanceConformanceDocumentComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceSoftwareComponent(String name, Conformance.ConformanceSoftwareComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeString("version", element.getVersion());
      composeDateTime("releaseDate", element.getReleaseDate());
      close();
    }
  }

  private void composeConformanceConformanceImplementationComponent(String name, Conformance.ConformanceImplementationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("description", element.getDescription());
      composeUri("url", element.getUrl());
      close();
    }
  }

  private void composeConformanceConformanceRestComponent(String name, Conformance.ConformanceRestComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new RestfulConformanceModeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeConformanceConformanceRestSecurityComponent("security", element.getSecurity());
      if (element.getResource().size() > 0) {
        openArray("resource");
        for (Conformance.ConformanceRestResourceComponent e : element.getResource()) 
          composeConformanceConformanceRestResourceComponent(null, e);
        closeArray();
      };
      composeBoolean("batch", element.getBatch());
      composeBoolean("history", element.getHistory());
      if (element.getQuery().size() > 0) {
        openArray("query");
        for (Conformance.ConformanceRestQueryComponent e : element.getQuery()) 
          composeConformanceConformanceRestQueryComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityComponent(String name, Conformance.ConformanceRestSecurityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getService().size() > 0) {
        openArray("service");
        for (CodeableConcept e : element.getService()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeString("description", element.getDescription());
      if (element.getCertificate().size() > 0) {
        openArray("certificate");
        for (Conformance.ConformanceRestSecurityCertificateComponent e : element.getCertificate()) 
          composeConformanceConformanceRestSecurityCertificateComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestSecurityCertificateComponent(String name, Conformance.ConformanceRestSecurityCertificateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("type", element.getType());
      composeBase64Binary("blob", element.getBlob());
      close();
    }
  }

  private void composeConformanceConformanceRestResourceComponent(String name, Conformance.ConformanceRestResourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("type", element.getType());
      composeResourceReference("profile", element.getProfile());
      if (element.getOperation().size() > 0) {
        openArray("operation");
        for (Conformance.ConformanceRestResourceOperationComponent e : element.getOperation()) 
          composeConformanceConformanceRestResourceOperationComponent(null, e);
        closeArray();
      };
      composeBoolean("readHistory", element.getReadHistory());
      if (element.getSearchInclude().size() > 0) {
        openArray("searchInclude");
        for (String_ e : element.getSearchInclude()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getSearchParam().size() > 0) {
        openArray("searchParam");
        for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getSearchParam()) 
          composeConformanceConformanceRestResourceSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestResourceOperationComponent(String name, Conformance.ConformanceRestResourceOperationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Conformance().new RestfulOperationEnumFactory());
      composeString("documentation", element.getDocumentation());
      close();
    }
  }

  private void composeConformanceConformanceRestResourceSearchParamComponent(String name, Conformance.ConformanceRestResourceSearchParamComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeUri("source", element.getSource());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Conformance().new SearchParamTypeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeString("xpath", element.getXpath());
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (Code e : element.getTarget()) 
          composeCode(null, e);
        closeArray();
      };
      if (element.getChain().size() > 0) {
        openArray("chain");
        for (String_ e : element.getChain()) 
          composeString(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceRestQueryComponent(String name, Conformance.ConformanceRestQueryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeString("documentation", element.getDocumentation());
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Conformance.ConformanceRestResourceSearchParamComponent e : element.getParameter()) 
          composeConformanceConformanceRestResourceSearchParamComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceMessagingComponent(String name, Conformance.ConformanceMessagingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("endpoint", element.getEndpoint());
      composeInteger("reliableCache", element.getReliableCache());
      composeString("documentation", element.getDocumentation());
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (Conformance.ConformanceMessagingEventComponent e : element.getEvent()) 
          composeConformanceConformanceMessagingEventComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeConformanceConformanceMessagingEventComponent(String name, Conformance.ConformanceMessagingEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new MessageConformanceEventModeEnumFactory());
      if (element.getProtocol().size() > 0) {
        openArray("protocol");
        for (Coding e : element.getProtocol()) 
          composeCoding(null, e);
        closeArray();
      };
      composeCode("focus", element.getFocus());
      composeResourceReference("request", element.getRequest());
      composeResourceReference("response", element.getResponse());
      composeString("documentation", element.getDocumentation());
      close();
    }
  }

  private void composeConformanceConformanceDocumentComponent(String name, Conformance.ConformanceDocumentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Conformance().new DocumentModeEnumFactory());
      composeString("documentation", element.getDocumentation());
      composeResourceReference("profile", element.getProfile());
      close();
    }
  }

  private void composeCoverage(String name, Coverage element) throws Exception {
    if (element != null) {
      open(name);
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
      close();
    }
  }

  private void composeCoverageCoverageSubscriberComponent(String name, Coverage.CoverageSubscriberComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeHumanName("name", element.getName());
      composeAddress("address", element.getAddress());
      composeDate("birthdate", element.getBirthdate());
      close();
    }
  }

  private void composeDevice(String name, Device element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturer());
      composeString("model", element.getModel());
      composeString("version", element.getVersion());
      composeDate("expiry", element.getExpiry());
      composeDeviceDeviceIdentityComponent("identity", element.getIdentity());
      composeResourceReference("owner", element.getOwner());
      if (element.getAssignedId().size() > 0) {
        openArray("assignedId");
        for (Identifier e : element.getAssignedId()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("location", element.getLocation());
      composeResourceReference("patient", element.getPatient());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Contact e : element.getContact()) 
          composeContact(null, e);
        closeArray();
      };
      composeUri("url", element.getUrl());
      close();
    }
  }

  private void composeDeviceDeviceIdentityComponent(String name, Device.DeviceIdentityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("gtin", element.getGtin());
      composeString("lot", element.getLot());
      composeString("serialNumber", element.getSerialNumber());
      close();
    }
  }

  private void composeDeviceCapabilities(String name, DeviceCapabilities element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeCodeableConcept("type", element.getType());
      composeString("manufacturer", element.getManufacturer());
      composeResourceReference("identity", element.getIdentity());
      if (element.getVirtualDevice().size() > 0) {
        openArray("virtualDevice");
        for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent e : element.getVirtualDevice()) 
          composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getChannel().size() > 0) {
        openArray("channel");
        for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent e : element.getChannel()) 
          composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getMetric().size() > 0) {
        openArray("metric");
        for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent e : element.getMetric()) 
          composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeString("key", element.getKey());
      composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent("info", element.getInfo());
      if (element.getFacet().size() > 0) {
        openArray("facet");
        for (DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent e : element.getFacet()) 
          composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new DeviceCapabilities().new DeviceDataTypeEnumFactory());
      composeString("units", element.getUnits());
      composeCode("ucum", element.getUcum());
      composeSampledData("template", element.getTemplate());
      composeUri("system", element.getSystem());
      close();
    }
  }

  private void composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent(String name, DeviceCapabilities.DeviceCapabilitiesVirtualDeviceChannelMetricFacetComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeDecimal("scale", element.getScale());
      composeString("key", element.getKey());
      composeDeviceCapabilitiesDeviceCapabilitiesVirtualDeviceChannelMetricInfoComponent("info", element.getInfo());
      close();
    }
  }

  private void composeDeviceLog(String name, DeviceLog element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeInstant("instant", element.getInstant());
      composeResourceReference("capabilities", element.getCapabilities());
      composeResourceReference("subject", element.getSubject());
      if (element.getItem().size() > 0) {
        openArray("item");
        for (DeviceLog.DeviceLogItemComponent e : element.getItem()) 
          composeDeviceLogDeviceLogItemComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceLogDeviceLogItemComponent(String name, DeviceLog.DeviceLogItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("key", element.getKey());
      composeString("value", element.getValue());
      if (element.getFlag().size() > 0) {
        openArray("flag");
        for (Enumeration<DeviceLog.DeviceValueFlag> e : element.getFlag()) 
          composeEnumeration(null, e, new DeviceLog().new DeviceValueFlagEnumFactory());
        closeArray();
      };
      close();
    }
  }

  private void composeDeviceObservation(String name, DeviceObservation element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeInstant("issued", element.getIssued());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("device", element.getDevice());
      if (element.getMeasurement().size() > 0) {
        openArray("measurement");
        for (ResourceReference e : element.getMeasurement()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDiagnosticOrder(String name, DiagnosticOrder element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("orderer", element.getOrderer());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("encounter", element.getEncounter());
      composeString("clinicalNotes", element.getClinicalNotes());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (ResourceReference e : element.getSpecimen()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
      if (element.getPriority() != null)
        composeEnumeration("priority", element.getPriority(), new DiagnosticOrder().new DiagnosticOrderPriorityEnumFactory());
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent(null, e);
        closeArray();
      };
      if (element.getItem().size() > 0) {
        openArray("item");
        for (DiagnosticOrder.DiagnosticOrderItemComponent e : element.getItem()) 
          composeDiagnosticOrderDiagnosticOrderItemComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderEventComponent(String name, DiagnosticOrder.DiagnosticOrderEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
      composeDateTime("date", element.getDate());
      composeResourceReference("actor", element.getActor());
      close();
    }
  }

  private void composeDiagnosticOrderDiagnosticOrderItemComponent(String name, DiagnosticOrder.DiagnosticOrderItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      if (element.getSpecimen().size() > 0) {
        openArray("specimen");
        for (ResourceReference e : element.getSpecimen()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeCodeableConcept("bodySite", element.getBodySite());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticOrder().new DiagnosticOrderStatusEnumFactory());
      if (element.getEvent().size() > 0) {
        openArray("event");
        for (DiagnosticOrder.DiagnosticOrderEventComponent e : element.getEvent()) 
          composeDiagnosticOrderDiagnosticOrderEventComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDiagnosticReport(String name, DiagnosticReport element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new DiagnosticReport().new ObservationStatusEnumFactory());
      composeDateTime("issued", element.getIssued());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("performer", element.getPerformer());
      composeIdentifier("reportId", element.getReportId());
      if (element.getRequestDetail().size() > 0) {
        openArray("requestDetail");
        for (DiagnosticReport.DiagnosticReportRequestDetailComponent e : element.getRequestDetail()) 
          composeDiagnosticReportDiagnosticReportRequestDetailComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("serviceCategory", element.getServiceCategory());
      composeDateTime("diagnosticTime", element.getDiagnosticTime());
      composeDiagnosticReportResultGroupComponent("results", element.getResults());
      if (element.getImage().size() > 0) {
        openArray("image");
        for (ResourceReference e : element.getImage()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeString("conclusion", element.getConclusion());
      if (element.getCodedDiagnosis().size() > 0) {
        openArray("codedDiagnosis");
        for (CodeableConcept e : element.getCodedDiagnosis()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getRepresentation().size() > 0) {
        openArray("representation");
        for (Attachment e : element.getRepresentation()) 
          composeAttachment(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDiagnosticReportDiagnosticReportRequestDetailComponent(String name, DiagnosticReport.DiagnosticReportRequestDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("encounter", element.getEncounter());
      composeIdentifier("requestOrderId", element.getRequestOrderId());
      composeIdentifier("receiverOrderId", element.getReceiverOrderId());
      if (element.getRequestTest().size() > 0) {
        openArray("requestTest");
        for (CodeableConcept e : element.getRequestTest()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeCodeableConcept("bodySite", element.getBodySite());
      composeResourceReference("requester", element.getRequester());
      composeString("clinicalInfo", element.getClinicalInfo());
      close();
    }
  }

  private void composeDiagnosticReportResultGroupComponent(String name, DiagnosticReport.ResultGroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("name", element.getName());
      composeResourceReference("specimen", element.getSpecimen());
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (DiagnosticReport.ResultGroupComponent e : element.getGroup()) 
          composeDiagnosticReportResultGroupComponent(null, e);
        closeArray();
      };
      if (element.getResult().size() > 0) {
        openArray("result");
        for (ResourceReference e : element.getResult()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocument(String name, Document element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeIdentifier("versionIdentifier", element.getVersionIdentifier());
      composeInstant("created", element.getCreated());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      composeString("title", element.getTitle());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Document().new DocumentStatusEnumFactory());
      composeCoding("confidentiality", element.getConfidentiality());
      composeResourceReference("subject", element.getSubject());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (ResourceReference e : element.getAuthor()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getAttester().size() > 0) {
        openArray("attester");
        for (Document.DocumentAttesterComponent e : element.getAttester()) 
          composeDocumentDocumentAttesterComponent(null, e);
        closeArray();
      };
      composeResourceReference("custodian", element.getCustodian());
      composeDocumentDocumentEventComponent("event", element.getEvent());
      composeResourceReference("encounter", element.getEncounter());
      composeId("replaces", element.getReplaces());
      if (element.getProvenance().size() > 0) {
        openArray("provenance");
        for (ResourceReference e : element.getProvenance()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeAttachment("stylesheet", element.getStylesheet());
      composeAttachment("representation", element.getRepresentation());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Document.SectionComponent e : element.getSection()) 
          composeDocumentSectionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocumentDocumentAttesterComponent(String name, Document.DocumentAttesterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new Document().new DocumentAttestationModeEnumFactory());
      composeDateTime("time", element.getTime());
      composeResourceReference("party", element.getParty());
      close();
    }
  }

  private void composeDocumentDocumentEventComponent(String name, Document.DocumentEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocumentSectionComponent(String name, Document.SectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("content", element.getContent());
      if (element.getSection().size() > 0) {
        openArray("section");
        for (Document.SectionComponent e : element.getSection()) 
          composeDocumentSectionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocumentReference(String name, DocumentReference element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("masterIdentifier", element.getMasterIdentifier());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("subtype", element.getSubtype());
      if (element.getAuthor().size() > 0) {
        openArray("author");
        for (ResourceReference e : element.getAuthor()) 
          composeResourceReference(null, e);
        closeArray();
      };
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
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceComponent(String name, DocumentReference.DocumentReferenceServiceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      composeString("address", element.getAddress());
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (DocumentReference.DocumentReferenceServiceParameterComponent e : element.getParameter()) 
          composeDocumentReferenceDocumentReferenceServiceParameterComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceServiceParameterComponent(String name, DocumentReference.DocumentReferenceServiceParameterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeString("value", element.getValue());
      close();
    }
  }

  private void composeDocumentReferenceDocumentReferenceContextComponent(String name, DocumentReference.DocumentReferenceContextComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getCode().size() > 0) {
        openArray("code");
        for (CodeableConcept e : element.getCode()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeCodeableConcept("facilityType", element.getFacilityType());
      close();
    }
  }

  private void composeEncounter(String name, Encounter element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Encounter().new EncounterStateEnumFactory());
      if (element.getClass() != null)
        composeEnumeration("class", element.getClass_(), new Encounter().new EncounterClassEnumFactory());
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (Encounter.EncounterParticipantComponent e : element.getParticipant()) 
          composeEncounterEncounterParticipantComponent(null, e);
        closeArray();
      };
      composeResourceReference("fulfills", element.getFulfills());
      composeDateTime("start", element.getStart());
      composeDuration("length", element.getLength());
      composeType("reason", element.getReason());
      composeResourceReference("indication", element.getIndication());
      composeCodeableConcept("priority", element.getPriority());
      composeEncounterEncounterHospitalizationComponent("hospitalization", element.getHospitalization());
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (Encounter.EncounterLocationComponent e : element.getLocation()) 
          composeEncounterEncounterLocationComponent(null, e);
        closeArray();
      };
      composeResourceReference("serviceProvider", element.getServiceProvider());
      composeResourceReference("partOf", element.getPartOf());
      close();
    }
  }

  private void composeEncounterEncounterParticipantComponent(String name, Encounter.EncounterParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getType().size() > 0) {
        openArray("type");
        for (Enumeration<Encounter.ParticipantType> e : element.getType()) 
          composeEnumeration(null, e, new Encounter().new ParticipantTypeEnumFactory());
        closeArray();
      };
      composeResourceReference("practitioner", element.getPractitioner());
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationComponent(String name, Encounter.EncounterHospitalizationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIdentifier("preAdmissionIdentifier", element.getPreAdmissionIdentifier());
      composeResourceReference("origin", element.getOrigin());
      composeCodeableConcept("admitSource", element.getAdmitSource());
      composePeriod("period", element.getPeriod());
      if (element.getAccomodation().size() > 0) {
        openArray("accomodation");
        for (Encounter.EncounterHospitalizationAccomodationComponent e : element.getAccomodation()) 
          composeEncounterEncounterHospitalizationAccomodationComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("diet", element.getDiet());
      if (element.getSpecialCourtesy().size() > 0) {
        openArray("specialCourtesy");
        for (CodeableConcept e : element.getSpecialCourtesy()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSpecialArrangement().size() > 0) {
        openArray("specialArrangement");
        for (CodeableConcept e : element.getSpecialArrangement()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("destination", element.getDestination());
      composeCodeableConcept("dischargeDisposition", element.getDischargeDisposition());
      composeBoolean("reAdmission", element.getReAdmission());
      close();
    }
  }

  private void composeEncounterEncounterHospitalizationAccomodationComponent(String name, Encounter.EncounterHospitalizationAccomodationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("bed", element.getBed());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeEncounterEncounterLocationComponent(String name, Encounter.EncounterLocationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("location", element.getLocation());
      composePeriod("period", element.getPeriod());
      close();
    }
  }

  private void composeFamilyHistory(String name, FamilyHistory element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeString("note", element.getNote());
      if (element.getRelation().size() > 0) {
        openArray("relation");
        for (FamilyHistory.FamilyHistoryRelationComponent e : element.getRelation()) 
          composeFamilyHistoryFamilyHistoryRelationComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationComponent(String name, FamilyHistory.FamilyHistoryRelationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeCodeableConcept("relationship", element.getRelationship());
      composeType("deceased", element.getDeceased());
      composeString("note", element.getNote());
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (FamilyHistory.FamilyHistoryRelationConditionComponent e : element.getCondition()) 
          composeFamilyHistoryFamilyHistoryRelationConditionComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeFamilyHistoryFamilyHistoryRelationConditionComponent(String name, FamilyHistory.FamilyHistoryRelationConditionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("outcome", element.getOutcome());
      composeType("onset", element.getOnset());
      composeString("note", element.getNote());
      close();
    }
  }

  private void composeGVFMeta(String name, GVFMeta element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getSubject().size() > 0) {
        openArray("subject");
        for (GVFMeta.GVFMetaSubjectComponent e : element.getSubject()) 
          composeGVFMetaGVFMetaSubjectComponent(null, e);
        closeArray();
      };
      composeAttachment("sourceFile", element.getSourceFile());
      if (element.getGvfVersion() != null)
        composeEnumeration("gvfVersion", element.getGvfVersion(), new GVFMeta().new GvfVersionEnumFactory());
      composeUri("referenceFasta", element.getReferenceFasta());
      composeUri("featureGFF3", element.getFeatureGFF3());
      composeDate("fileDate", element.getFileDate());
      if (element.getIndividual().size() > 0) {
        openArray("individual");
        for (String_ e : element.getIndividual()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getPopulation() != null)
        composeEnumeration("population", element.getPopulation(), new GVFMeta().new PopulationEnumFactory());
      composeGVFMetaGVFMetaPlatformComponent("platform", element.getPlatform());
      if (element.getSequencingScope() != null)
        composeEnumeration("sequencingScope", element.getSequencingScope(), new GVFMeta().new SequencingScopeEnumFactory());
      if (element.getCaptureMethod() != null)
        composeEnumeration("captureMethod", element.getCaptureMethod(), new GVFMeta().new CaptureMethodEnumFactory());
      composeUri("captureRegions", element.getCaptureRegions());
      composeString("sequenceAlignment", element.getSequenceAlignment());
      composeString("variantCalling", element.getVariantCalling());
      composeString("sampleDescription", element.getSampleDescription());
      if (element.getGenomicSource() != null)
        composeEnumeration("genomicSource", element.getGenomicSource(), new GVFMeta().new SourceEnumFactory());
      close();
    }
  }

  private void composeGVFMetaGVFMetaSubjectComponent(String name, GVFMeta.GVFMetaSubjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("patient", element.getPatient());
      composeString("fieldId", element.getFieldId());
      close();
    }
  }

  private void composeGVFMetaGVFMetaPlatformComponent(String name, GVFMeta.GVFMetaPlatformComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getClass() != null)
        composeEnumeration("class", element.getClass_(), new GVFMeta().new PlatformClassEnumFactory());
      composeString("version", element.getVersion());
      if (element.getName() != null)
        composeEnumeration("name", element.getName(), new GVFMeta().new PlatformNameEnumFactory());
      composeString("identity", element.getIdentity());
      composeInteger("readLength", element.getReadLength());
      if (element.getReadType() != null)
        composeEnumeration("readType", element.getReadType(), new GVFMeta().new PlatformReadTypeEnumFactory());
      composeInteger("readPairSpan", element.getReadPairSpan());
      composeInteger("averageCoverage", element.getAverageCoverage());
      close();
    }
  }

  private void composeGVFVariant(String name, GVFVariant element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeGVFVariantGVFVariantSubjectComponent("subject", element.getSubject());
      composeResourceReference("meta", element.getMeta());
      composeAttachment("sourceFile", element.getSourceFile());
      composeString("seqid", element.getSeqid());
      composeString("source", element.getSource());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new GVFVariant().new FeatureTypeEnumFactory());
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      composeInteger("score", element.getScore());
      if (element.getStrand() != null)
        composeEnumeration("strand", element.getStrand(), new GVFVariant().new StrandEnumFactory());
      composeString("featureId", element.getFeatureId());
      composeString("alias", element.getAlias());
      composeGVFVariantGVFVariantDbxrefComponent("dbxref", element.getDbxref());
      if (element.getVariantSeq().size() > 0) {
        openArray("variantSeq");
        for (String_ e : element.getVariantSeq()) 
          composeString(null, e);
        closeArray();
      };
      composeString("referenceSeq", element.getReferenceSeq());
      if (element.getVariantFreq().size() > 0) {
        openArray("variantFreq");
        for (Decimal e : element.getVariantFreq()) 
          composeDecimal(null, e);
        closeArray();
      };
      if (element.getVariantEffect().size() > 0) {
        openArray("variantEffect");
        for (GVFVariant.GVFVariantVariantEffectComponent e : element.getVariantEffect()) 
          composeGVFVariantGVFVariantVariantEffectComponent(null, e);
        closeArray();
      };
      composeGVFVariantGVFVariantStartRangeComponent("startRange", element.getStartRange());
      composeGVFVariantGVFVariantEndRangeComponent("endRange", element.getEndRange());
      if (element.getVariantCodon().size() > 0) {
        openArray("variantCodon");
        for (String_ e : element.getVariantCodon()) 
          composeString(null, e);
        closeArray();
      };
      composeString("referenceCodon", element.getReferenceCodon());
      if (element.getVariantAA().size() > 0) {
        openArray("variantAA");
        for (String_ e : element.getVariantAA()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getReferenceAA().size() > 0) {
        openArray("referenceAA");
        for (String_ e : element.getReferenceAA()) 
          composeString(null, e);
        closeArray();
      };
      composeGVFVariantGVFVariantBreakpointDetailComponent("breakpointDetail", element.getBreakpointDetail());
      composeGVFVariantGVFVariantSequenceContextComponent("sequenceContext", element.getSequenceContext());
      if (element.getIndividual().size() > 0) {
        openArray("individual");
        for (String_ e : element.getIndividual()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getSample().size() > 0) {
        openArray("sample");
        for (GVFVariant.GVFVariantSampleComponent e : element.getSample()) 
          composeGVFVariantGVFVariantSampleComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGVFVariantGVFVariantSubjectComponent(String name, GVFVariant.GVFVariantSubjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("patient", element.getPatient());
      composeString("fileId", element.getFileId());
      close();
    }
  }

  private void composeGVFVariantGVFVariantDbxrefComponent(String name, GVFVariant.GVFVariantDbxrefComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getDatabase() != null)
        composeEnumeration("database", element.getDatabase(), new GVFVariant().new DatabaseEnumFactory());
      composeString("identity", element.getIdentity());
      close();
    }
  }

  private void composeGVFVariantGVFVariantVariantEffectComponent(String name, GVFVariant.GVFVariantVariantEffectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getSequenceVariant() != null)
        composeEnumeration("sequenceVariant", element.getSequenceVariant(), new GVFVariant().new SequenceVariantEnumFactory());
      composeInteger("index", element.getIndex());
      if (element.getFeatureType() != null)
        composeEnumeration("featureType", element.getFeatureType(), new GVFVariant().new FeatureTypeEnumFactory());
      if (element.getFeatureId().size() > 0) {
        openArray("featureId");
        for (String_ e : element.getFeatureId()) 
          composeString(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGVFVariantGVFVariantStartRangeComponent(String name, GVFVariant.GVFVariantStartRangeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      close();
    }
  }

  private void composeGVFVariantGVFVariantEndRangeComponent(String name, GVFVariant.GVFVariantEndRangeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      close();
    }
  }

  private void composeGVFVariantGVFVariantBreakpointDetailComponent(String name, GVFVariant.GVFVariantBreakpointDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("seqid", element.getSeqid());
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      if (element.getStrand() != null)
        composeEnumeration("strand", element.getStrand(), new GVFVariant().new StrandEnumFactory());
      close();
    }
  }

  private void composeGVFVariantGVFVariantSequenceContextComponent(String name, GVFVariant.GVFVariantSequenceContextComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("fivePrime", element.getFivePrime());
      composeString("threePrime", element.getThreePrime());
      close();
    }
  }

  private void composeGVFVariantGVFVariantSampleComponent(String name, GVFVariant.GVFVariantSampleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getPhased().size() > 0) {
        openArray("phased");
        for (String_ e : element.getPhased()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getGenotype().size() > 0) {
        openArray("genotype");
        for (String_ e : element.getGenotype()) 
          composeString(null, e);
        closeArray();
      };
      if (element.getVariantReads().size() > 0) {
        openArray("variantReads");
        for (Integer e : element.getVariantReads()) 
          composeInteger(null, e);
        closeArray();
      };
      composeInteger("totalReads", element.getTotalReads());
      if (element.getZygosity() != null)
        composeEnumeration("zygosity", element.getZygosity(), new GVFVariant().new ZygosityEnumFactory());
      close();
    }
  }

  private void composeGeneExpression(String name, GeneExpression element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeGeneExpressionGeneExpressionGeneComponent("gene", element.getGene());
      if (element.getMicroarray().size() > 0) {
        openArray("microarray");
        for (ResourceReference e : element.getMicroarray()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getRnaSeq().size() > 0) {
        openArray("rnaSeq");
        for (GeneExpression.GeneExpressionRnaSeqComponent e : element.getRnaSeq()) 
          composeGeneExpressionGeneExpressionRnaSeqComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGeneExpressionGeneExpressionGeneComponent(String name, GeneExpression.GeneExpressionGeneComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identifier", element.getIdentifier());
      composeGeneExpressionGeneExpressionGeneCoordinateComponent("coordinate", element.getCoordinate());
      close();
    }
  }

  private void composeGeneExpressionGeneExpressionGeneCoordinateComponent(String name, GeneExpression.GeneExpressionGeneCoordinateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("chromosome", element.getChromosome());
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      close();
    }
  }

  private void composeGeneExpressionGeneExpressionRnaSeqComponent(String name, GeneExpression.GeneExpressionRnaSeqComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("inputLab", element.getInputLab());
      composeResourceReference("inputAnalysis", element.getInputAnalysis());
      composeDecimal("expression", element.getExpression());
      if (element.getIsoform().size() > 0) {
        openArray("isoform");
        for (GeneExpression.GeneExpressionRnaSeqIsoformComponent e : element.getIsoform()) 
          composeGeneExpressionGeneExpressionRnaSeqIsoformComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGeneExpressionGeneExpressionRnaSeqIsoformComponent(String name, GeneExpression.GeneExpressionRnaSeqIsoformComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeDecimal("expression", element.getExpression());
      close();
    }
  }

  private void composeGeneticAnalysis(String name, GeneticAnalysis element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDate("date", element.getDate());
      composeGeneticAnalysisGeneticAnalysisGeneticAnalysisSummaryComponent("geneticAnalysisSummary", element.getGeneticAnalysisSummary());
      composeGeneticAnalysisGeneticAnalysisDnaRegionAnalysisTestCoverageComponent("dnaRegionAnalysisTestCoverage", element.getDnaRegionAnalysisTestCoverage());
      composeGeneticAnalysisGeneticAnalysisGeneticAnalysisDiscreteResultComponent("geneticAnalysisDiscreteResult", element.getGeneticAnalysisDiscreteResult());
      close();
    }
  }

  private void composeGeneticAnalysisGeneticAnalysisGeneticAnalysisSummaryComponent(String name, GeneticAnalysis.GeneticAnalysisGeneticAnalysisSummaryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCoding("geneticDiseaseAssessed", element.getGeneticDiseaseAssessed());
      composeCoding("medicationAssesed", element.getMedicationAssesed());
      composeCoding("genomicSourceClass", element.getGenomicSourceClass());
      composeCoding("geneticDiseaseAnalysisOverallInterpretation", element.getGeneticDiseaseAnalysisOverallInterpretation());
      composeCoding("geneticDiseaseAnalysisOverallCarrierInterpertation", element.getGeneticDiseaseAnalysisOverallCarrierInterpertation());
      composeCoding("drugEfficacyAnalysisOverallInterpretation", element.getDrugEfficacyAnalysisOverallInterpretation());
      composeString("geneticAnalysisSummaryReport", element.getGeneticAnalysisSummaryReport());
      composeString("reasonForStudyAdditionalNote", element.getReasonForStudyAdditionalNote());
      close();
    }
  }

  private void composeGeneticAnalysisGeneticAnalysisDnaRegionAnalysisTestCoverageComponent(String name, GeneticAnalysis.GeneticAnalysisDnaRegionAnalysisTestCoverageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getDnaRegionOfInterest().size() > 0) {
        openArray("dnaRegionOfInterest");
        for (GeneticAnalysis.GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent e : element.getDnaRegionOfInterest()) 
          composeGeneticAnalysisGeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGeneticAnalysisGeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent(String name, GeneticAnalysis.GeneticAnalysisDnaRegionAnalysisTestCoverageDnaRegionOfInterestComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("genomicReferenceSequenceIdentifier", element.getGenomicReferenceSequenceIdentifier());
      composeInteger("regionOfInterestStart", element.getRegionOfInterestStart());
      composeInteger("regionOfInterestStop", element.getRegionOfInterestStop());
      composeString("referenceNucleotide", element.getReferenceNucleotide());
      composeString("variableNucleotide", element.getVariableNucleotide());
      composeString("genechipId", element.getGenechipId());
      composeString("genechipManufacturerId", element.getGenechipManufacturerId());
      composeString("genechipVersion", element.getGenechipVersion());
      close();
    }
  }

  private void composeGeneticAnalysisGeneticAnalysisGeneticAnalysisDiscreteResultComponent(String name, GeneticAnalysis.GeneticAnalysisGeneticAnalysisDiscreteResultComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getDnaAnalysisDiscreteSequenceVariation().size() > 0) {
        openArray("dnaAnalysisDiscreteSequenceVariation");
        for (GeneticAnalysis.GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent e : element.getDnaAnalysisDiscreteSequenceVariation()) 
          composeGeneticAnalysisGeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGeneticAnalysisGeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent(String name, GeneticAnalysis.GeneticAnalysisGeneticAnalysisDiscreteResultDnaAnalysisDiscreteSequenceVariationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("geneIdentifier", element.getGeneIdentifier());
      composeString("genomicReferenceSequenceIdentifier", element.getGenomicReferenceSequenceIdentifier());
      composeString("transcriptReferenceIdentifier", element.getTranscriptReferenceIdentifier());
      composeString("alleleName", element.getAlleleName());
      composeString("dnaSequenceVariationIdentifier", element.getDnaSequenceVariationIdentifier());
      composeString("dnaSequenceVariation", element.getDnaSequenceVariation());
      composeCoding("dnaSequenceVariationType", element.getDnaSequenceVariationType());
      composeString("aminoAcidChange", element.getAminoAcidChange());
      composeCoding("aminoAcidChangeType", element.getAminoAcidChangeType());
      composeString("dnaRegionName", element.getDnaRegionName());
      composeCoding("allellicState", element.getAllellicState());
      composeCoding("genomicSourceClass", element.getGenomicSourceClass());
      composeString("dnaSequenceVariationDisplayName", element.getDnaSequenceVariationDisplayName());
      composeCoding("geneticDiseaseSequenceVariationInterpretation", element.getGeneticDiseaseSequenceVariationInterpretation());
      composeCoding("drugMetabolismSequenceVariationInterpretatioin", element.getDrugMetabolismSequenceVariationInterpretatioin());
      composeCoding("drugEfficacySequenceVariationInterpretation", element.getDrugEfficacySequenceVariationInterpretation());
      composeCoding("geneticVariantAssessment", element.getGeneticVariantAssessment());
      close();
    }
  }

  private void composeGroup(String name, Group element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Group().new GroupTypeEnumFactory());
      composeBoolean("actual", element.getActual());
      composeCodeableConcept("code", element.getCode());
      composeString("name", element.getName());
      composeInteger("quantity", element.getQuantity());
      if (element.getCharacteristic().size() > 0) {
        openArray("characteristic");
        for (Group.GroupCharacteristicComponent e : element.getCharacteristic()) 
          composeGroupGroupCharacteristicComponent(null, e);
        closeArray();
      };
      if (element.getMember().size() > 0) {
        openArray("member");
        for (ResourceReference e : element.getMember()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeGroupGroupCharacteristicComponent(String name, Group.GroupCharacteristicComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      composeType("value", element.getValue());
      composeBoolean("exclude", element.getExclude());
      close();
    }
  }

  private void composeImagingStudy(String name, ImagingStudy element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("subject", element.getSubject());
      composeOid("uid", element.getUid());
      composeIdentifier("accessionNo", element.getAccessionNo());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getModality().size() > 0) {
        openArray("modality");
        for (Enumeration<ImagingStudy.ImagingModality> e : element.getModality()) 
          composeEnumeration(null, e, new ImagingStudy().new ImagingModalityEnumFactory());
        closeArray();
      };
      composeResourceReference("referrer", element.getReferrer());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy().new InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrl());
      composeInteger("numberOfSeries", element.getNumberOfSeries());
      composeInteger("numberOfInstances", element.getNumberOfInstances());
      composeString("clinicalInformation", element.getClinicalInformation());
      if (element.getProcedure().size() > 0) {
        openArray("procedure");
        for (Coding e : element.getProcedure()) 
          composeCoding(null, e);
        closeArray();
      };
      composeResourceReference("interpreter", element.getInterpreter());
      composeString("description", element.getDescription());
      if (element.getSeries().size() > 0) {
        openArray("series");
        for (ImagingStudy.ImagingStudySeriesComponent e : element.getSeries()) 
          composeImagingStudyImagingStudySeriesComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImagingStudyImagingStudySeriesComponent(String name, ImagingStudy.ImagingStudySeriesComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("number", element.getNumber());
      if (element.getModality() != null)
        composeEnumeration("modality", element.getModality(), new ImagingStudy().new ModalityEnumFactory());
      composeOid("uid", element.getUid());
      composeString("description", element.getDescription());
      composeInteger("numberOfInstances", element.getNumberOfInstances());
      if (element.getAvailability() != null)
        composeEnumeration("availability", element.getAvailability(), new ImagingStudy().new InstanceAvailabilityEnumFactory());
      composeUri("url", element.getUrl());
      composeCoding("bodySite", element.getBodySite());
      composeDateTime("dateTime", element.getDateTime());
      if (element.getInstance().size() > 0) {
        openArray("instance");
        for (ImagingStudy.ImagingStudySeriesInstanceComponent e : element.getInstance()) 
          composeImagingStudyImagingStudySeriesInstanceComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImagingStudyImagingStudySeriesInstanceComponent(String name, ImagingStudy.ImagingStudySeriesInstanceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("number", element.getNumber());
      composeOid("uid", element.getUid());
      composeOid("sopclass", element.getSopclass());
      composeString("type", element.getType());
      composeString("title", element.getTitle());
      composeUri("url", element.getUrl());
      composeResourceReference("attachment", element.getAttachment());
      close();
    }
  }

  private void composeImmunization(String name, Immunization element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
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
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (Immunization.ImmunizationReactionComponent e : element.getReaction()) 
          composeImmunizationImmunizationReactionComponent(null, e);
        closeArray();
      };
      composeImmunizationImmunizationVaccinationProtocolComponent("vaccinationProtocol", element.getVaccinationProtocol());
      close();
    }
  }

  private void composeImmunizationImmunizationExplanationComponent(String name, Immunization.ImmunizationExplanationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getReason().size() > 0) {
        openArray("reason");
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getRefusalReason().size() > 0) {
        openArray("refusalReason");
        for (CodeableConcept e : element.getRefusalReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImmunizationImmunizationReactionComponent(String name, Immunization.ImmunizationReactionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTime("date", element.getDate());
      composeResourceReference("detail", element.getDetail());
      composeBoolean("reported", element.getReported());
      close();
    }
  }

  private void composeImmunizationImmunizationVaccinationProtocolComponent(String name, Immunization.ImmunizationVaccinationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("doseSequence", element.getDoseSequence());
      composeString("description", element.getDescription());
      composeResourceReference("authority", element.getAuthority());
      composeString("series", element.getSeries());
      composeInteger("seriesDoses", element.getSeriesDoses());
      composeCodeableConcept("doseTarget", element.getDoseTarget());
      composeCodeableConcept("doseStatus", element.getDoseStatus());
      composeCodeableConcept("doseStatusReason", element.getDoseStatusReason());
      close();
    }
  }

  private void composeImmunizationProfile(String name, ImmunizationProfile element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      if (element.getRecommendation().size() > 0) {
        openArray("recommendation");
        for (ImmunizationProfile.ImmunizationProfileRecommendationComponent e : element.getRecommendation()) 
          composeImmunizationProfileImmunizationProfileRecommendationComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDateTime("recommendationDate", element.getRecommendationDate());
      composeCodeableConcept("vaccineType", element.getVaccineType());
      composeInteger("doseNumber", element.getDoseNumber());
      if (element.getForecastStatus() != null)
        composeEnumeration("forecastStatus", element.getForecastStatus(), new ImmunizationProfile().new ImmunizationForecastStatusEnumFactory());
      if (element.getDateCriterion().size() > 0) {
        openArray("dateCriterion");
        for (ImmunizationProfile.ImmunizationProfileRecommendationDateCriterionComponent e : element.getDateCriterion()) 
          composeImmunizationProfileImmunizationProfileRecommendationDateCriterionComponent(null, e);
        closeArray();
      };
      composeImmunizationProfileImmunizationProfileRecommendationProtocolComponent("protocol", element.getProtocol());
      if (element.getSupportingImmunization().size() > 0) {
        openArray("supportingImmunization");
        for (ResourceReference e : element.getSupportingImmunization()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getSupportingAdverseEventReport().size() > 0) {
        openArray("supportingAdverseEventReport");
        for (ImmunizationProfile.ImmunizationProfileRecommendationSupportingAdverseEventReportComponent e : element.getSupportingAdverseEventReport()) 
          composeImmunizationProfileImmunizationProfileRecommendationSupportingAdverseEventReportComponent(null, e);
        closeArray();
      };
      if (element.getSupportingPatientObservation().size() > 0) {
        openArray("supportingPatientObservation");
        for (ResourceReference e : element.getSupportingPatientObservation()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationDateCriterionComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationDateCriterionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeDateTime("value", element.getValue());
      close();
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationProtocolComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationProtocolComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInteger("doseSequence", element.getDoseSequence());
      composeString("description", element.getDescription());
      composeResourceReference("authority", element.getAuthority());
      composeString("series", element.getSeries());
      close();
    }
  }

  private void composeImmunizationProfileImmunizationProfileRecommendationSupportingAdverseEventReportComponent(String name, ImmunizationProfile.ImmunizationProfileRecommendationSupportingAdverseEventReportComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Id e : element.getIdentifier()) 
          composeId(null, e);
        closeArray();
      };
      composeCodeableConcept("reportType", element.getReportType());
      composeDateTime("reportDate", element.getReportDate());
      composeString("text", element.getText());
      if (element.getReaction().size() > 0) {
        openArray("reaction");
        for (ResourceReference e : element.getReaction()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeList_(String name, List_ element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("source", element.getSource());
      composeDateTime("date", element.getDate());
      composeBoolean("ordered", element.getOrdered());
      if (element.getMode() != null)
        composeEnumeration("mode", element.getMode(), new List_().new ListModeEnumFactory());
      if (element.getEntry().size() > 0) {
        openArray("entry");
        for (List_.ListEntryComponent e : element.getEntry()) 
          composeList_ListEntryComponent(null, e);
        closeArray();
      };
      composeCodeableConcept("emptyReason", element.getEmptyReason());
      close();
    }
  }

  private void composeList_ListEntryComponent(String name, List_.ListEntryComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getFlag().size() > 0) {
        openArray("flag");
        for (CodeableConcept e : element.getFlag()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeBoolean("deleted", element.getDeleted());
      composeDateTime("date", element.getDate());
      composeResourceReference("item", element.getItem());
      close();
    }
  }

  private void composeLocation(String name, Location element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeString("description", element.getDescription());
      if (element.getType().size() > 0) {
        openArray("type");
        for (CodeableConcept e : element.getType()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeContact("telecom", element.getTelecom());
      composeAddress("address", element.getAddress());
      composeLocationLocationPositionComponent("position", element.getPosition());
      composeResourceReference("provider", element.getProvider());
      composeBoolean("active", element.getActive());
      composeResourceReference("partOf", element.getPartOf());
      close();
    }
  }

  private void composeLocationLocationPositionComponent(String name, Location.LocationPositionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeDecimal("longitude", element.getLongitude());
      composeDecimal("latitude", element.getLatitude());
      composeDecimal("altitude", element.getAltitude());
      close();
    }
  }

  private void composeMedia(String name, Media element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Media().new MediaTypeEnumFactory());
      composeCodeableConcept("subtype", element.getSubtype());
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTime("dateTime", element.getDateTime());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("requester", element.getRequester());
      composeResourceReference("operator", element.getOperator());
      composeCodeableConcept("view", element.getView());
      composeString("deviceName", element.getDeviceName());
      composeInteger("height", element.getHeight());
      composeInteger("width", element.getWidth());
      composeInteger("frames", element.getFrames());
      composeInteger("length", element.getLength());
      composeAttachment("content", element.getContent());
      close();
    }
  }

  private void composeMedication(String name, Medication element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("name", element.getName());
      composeCodeableConcept("code", element.getCode());
      composeBoolean("isBrand", element.getIsBrand());
      composeResourceReference("manufacturer", element.getManufacturer());
      if (element.getKind() != null)
        composeEnumeration("kind", element.getKind(), new Medication().new MedicationKindEnumFactory());
      composeMedicationMedicationProductComponent("product", element.getProduct());
      composeMedicationMedicationPackageComponent("package", element.getPackage());
      close();
    }
  }

  private void composeMedicationMedicationProductComponent(String name, Medication.MedicationProductComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("form", element.getForm());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (Medication.MedicationProductIngredientComponent e : element.getIngredient()) 
          composeMedicationMedicationProductIngredientComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationMedicationProductIngredientComponent(String name, Medication.MedicationProductIngredientComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("item", element.getItem());
      composeRatio("amount", element.getAmount());
      close();
    }
  }

  private void composeMedicationMedicationPackageComponent(String name, Medication.MedicationPackageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("container", element.getContainer());
      if (element.getContent().size() > 0) {
        openArray("content");
        for (Medication.MedicationPackageContentComponent e : element.getContent()) 
          composeMedicationMedicationPackageContentComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationMedicationPackageContentComponent(String name, Medication.MedicationPackageContentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("item", element.getItem());
      composeQuantity("amount", element.getAmount());
      close();
    }
  }

  private void composeMedicationAdministration(String name, MedicationAdministration element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationAdministration().new MedicationAdminStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("practitioner", element.getPractitioner());
      composeResourceReference("encounter", element.getEncounter());
      composeResourceReference("prescription", element.getPrescription());
      composeBoolean("wasNotGiven", element.getWasNotGiven());
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      if (element.getAdministrationDevice().size() > 0) {
        openArray("administrationDevice");
        for (ResourceReference e : element.getAdministrationDevice()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationAdministration.MedicationAdministrationDosageComponent e : element.getDosage()) 
          composeMedicationAdministrationMedicationAdministrationDosageComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationAdministrationMedicationAdministrationDosageComponent(String name, MedicationAdministration.MedicationAdministrationDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeSchedule("timing", element.getTiming());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationDispense(String name, MedicationDispense element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationDispense().new MedicationDispenseStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("dispenser", element.getDispenser());
      if (element.getAuthorizingPrescription().size() > 0) {
        openArray("authorizingPrescription");
        for (ResourceReference e : element.getAuthorizingPrescription()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDispense().size() > 0) {
        openArray("dispense");
        for (MedicationDispense.MedicationDispenseDispenseComponent e : element.getDispense()) 
          composeMedicationDispenseMedicationDispenseDispenseComponent(null, e);
        closeArray();
      };
      composeMedicationDispenseMedicationDispenseSubstitutionComponent("substitution", element.getSubstitution());
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseComponent(String name, MedicationDispense.MedicationDispenseDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIdentifier("identifier", element.getIdentifier());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationDispense().new MedicationDispenseStatusEnumFactory());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("medication", element.getMedication());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeResourceReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (ResourceReference e : element.getReceiver()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationDispense.MedicationDispenseDispenseDosageComponent e : element.getDosage()) 
          composeMedicationDispenseMedicationDispenseDispenseDosageComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseDispenseDosageComponent(String name, MedicationDispense.MedicationDispenseDispenseDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeType("additionalInstructions", element.getAdditionalInstructions());
      composeType("timing", element.getTiming());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationDispenseMedicationDispenseSubstitutionComponent(String name, MedicationDispense.MedicationDispenseSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      if (element.getReason().size() > 0) {
        openArray("reason");
        for (CodeableConcept e : element.getReason()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getResponsibleParty().size() > 0) {
        openArray("responsibleParty");
        for (ResourceReference e : element.getResponsibleParty()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationPrescription(String name, MedicationPrescription element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTime("dateWritten", element.getDateWritten());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new MedicationPrescription().new MedicationPrescriptionStatusEnumFactory());
      composeResourceReference("patient", element.getPatient());
      composeResourceReference("prescriber", element.getPrescriber());
      composeResourceReference("encounter", element.getEncounter());
      composeType("reasonForPrescribing", element.getReasonForPrescribing());
      composeResourceReference("medication", element.getMedication());
      if (element.getDosageInstruction().size() > 0) {
        openArray("dosageInstruction");
        for (MedicationPrescription.MedicationPrescriptionDosageInstructionComponent e : element.getDosageInstruction()) 
          composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(null, e);
        closeArray();
      };
      composeMedicationPrescriptionMedicationPrescriptionDispenseComponent("dispense", element.getDispense());
      composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent("substitution", element.getSubstitution());
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDosageInstructionComponent(String name, MedicationPrescription.MedicationPrescriptionDosageInstructionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("dosageInstructionsText", element.getDosageInstructionsText());
      composeType("additionalInstructions", element.getAdditionalInstructions());
      composeType("timing", element.getTiming());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("doseQuantity", element.getDoseQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionDispenseComponent(String name, MedicationPrescription.MedicationPrescriptionDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("medication", element.getMedication());
      composePeriod("validityPeriod", element.getValidityPeriod());
      composeInteger("numberOfRepeatsAllowed", element.getNumberOfRepeatsAllowed());
      composeQuantity("quantity", element.getQuantity());
      composeDuration("expectedSupplyDuration", element.getExpectedSupplyDuration());
      close();
    }
  }

  private void composeMedicationPrescriptionMedicationPrescriptionSubstitutionComponent(String name, MedicationPrescription.MedicationPrescriptionSubstitutionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      composeCodeableConcept("reason", element.getReason());
      close();
    }
  }

  private void composeMedicationStatement(String name, MedicationStatement element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("patient", element.getPatient());
      composeBoolean("wasNotGiven", element.getWasNotGiven());
      if (element.getReasonNotGiven().size() > 0) {
        openArray("reasonNotGiven");
        for (CodeableConcept e : element.getReasonNotGiven()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("whenGiven", element.getWhenGiven());
      composeResourceReference("medication", element.getMedication());
      if (element.getAdministrationDevice().size() > 0) {
        openArray("administrationDevice");
        for (ResourceReference e : element.getAdministrationDevice()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getDosage().size() > 0) {
        openArray("dosage");
        for (MedicationStatement.MedicationStatementDosageComponent e : element.getDosage()) 
          composeMedicationStatementMedicationStatementDosageComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMedicationStatementMedicationStatementDosageComponent(String name, MedicationStatement.MedicationStatementDosageComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeSchedule("timing", element.getTiming());
      composeCodeableConcept("site", element.getSite());
      composeCodeableConcept("route", element.getRoute());
      composeCodeableConcept("method", element.getMethod());
      composeQuantity("quantity", element.getQuantity());
      composeRatio("rate", element.getRate());
      composeRatio("maxDosePerPeriod", element.getMaxDosePerPeriod());
      close();
    }
  }

  private void composeMessage(String name, Message element) throws Exception {
    if (element != null) {
      open(name);
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
      if (element.getData().size() > 0) {
        openArray("data");
        for (ResourceReference e : element.getData()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMessageMessageResponseComponent(String name, Message.MessageResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeId("identifier", element.getIdentifier());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new Message().new ResponseCodeEnumFactory());
      composeResourceReference("details", element.getDetails());
      close();
    }
  }

  private void composeMessageMessageSourceComponent(String name, Message.MessageSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeString("software", element.getSoftware());
      composeString("version", element.getVersion());
      composeContact("contact", element.getContact());
      composeUri("endpoint", element.getEndpoint());
      close();
    }
  }

  private void composeMessageMessageDestinationComponent(String name, Message.MessageDestinationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeResourceReference("target", element.getTarget());
      composeUri("endpoint", element.getEndpoint());
      close();
    }
  }

  private void composeMicroarray(String name, Microarray element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getSubject().size() > 0) {
        openArray("subject");
        for (Microarray.MicroarraySubjectComponent e : element.getSubject()) 
          composeMicroarrayMicroarraySubjectComponent(null, e);
        closeArray();
      };
      composeResourceReference("organization", element.getOrganization());
      composeDate("date", element.getDate());
      composeMicroarrayMicroarrayScannerComponent("scanner", element.getScanner());
      if (element.getSample().size() > 0) {
        openArray("sample");
        for (Microarray.MicroarraySampleComponent e : element.getSample()) 
          composeMicroarrayMicroarraySampleComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMicroarrayMicroarraySubjectComponent(String name, Microarray.MicroarraySubjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("patient", element.getPatient());
      if (element.getSampleId().size() > 0) {
        openArray("sampleId");
        for (String_ e : element.getSampleId()) 
          composeString(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeMicroarrayMicroarrayScannerComponent(String name, Microarray.MicroarrayScannerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("manufacturer", element.getManufacturer());
      composeString("name", element.getName());
      composeString("version", element.getVersion());
      close();
    }
  }

  private void composeMicroarrayMicroarraySampleComponent(String name, Microarray.MicroarraySampleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeCodeableConcept("organism", element.getOrganism());
      composeMicroarrayMicroarraySampleSpecimenComponent("specimen", element.getSpecimen());
      composeMicroarrayMicroarraySampleGeneComponent("gene", element.getGene());
      composeDecimal("intensity", element.getIntensity());
      composeBoolean("isControl", element.getIsControl());
      close();
    }
  }

  private void composeMicroarrayMicroarraySampleSpecimenComponent(String name, Microarray.MicroarraySampleSpecimenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("type", element.getType());
      composeCodeableConcept("source", element.getSource());
      close();
    }
  }

  private void composeMicroarrayMicroarraySampleGeneComponent(String name, Microarray.MicroarraySampleGeneComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeMicroarrayMicroarraySampleGeneCoordinateComponent("coordinate", element.getCoordinate());
      close();
    }
  }

  private void composeMicroarrayMicroarraySampleGeneCoordinateComponent(String name, Microarray.MicroarraySampleGeneCoordinateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("chromosome", element.getChromosome());
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      close();
    }
  }

  private void composeObservation(String name, Observation element) throws Exception {
    if (element != null) {
      open(name);
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
      if (element.getReferenceRange().size() > 0) {
        openArray("referenceRange");
        for (Observation.ObservationReferenceRangeComponent e : element.getReferenceRange()) 
          composeObservationObservationReferenceRangeComponent(null, e);
        closeArray();
      };
      if (element.getComponent().size() > 0) {
        openArray("component");
        for (Observation.ObservationComponentComponent e : element.getComponent()) 
          composeObservationObservationComponentComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeObservationObservationReferenceRangeComponent(String name, Observation.ObservationReferenceRangeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("meaning", element.getMeaning());
      composeType("range", element.getRange());
      close();
    }
  }

  private void composeObservationObservationComponentComponent(String name, Observation.ObservationComponentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("name", element.getName());
      composeType("value", element.getValue());
      close();
    }
  }

  private void composeOperationOutcome(String name, OperationOutcome element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIssue().size() > 0) {
        openArray("issue");
        for (OperationOutcome.OperationOutcomeIssueComponent e : element.getIssue()) 
          composeOperationOutcomeOperationOutcomeIssueComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeOperationOutcomeOperationOutcomeIssueComponent(String name, OperationOutcome.OperationOutcomeIssueComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new OperationOutcome().new IssueSeverityEnumFactory());
      composeCoding("type", element.getType());
      composeString("details", element.getDetails());
      if (element.getLocation().size() > 0) {
        openArray("location");
        for (String_ e : element.getLocation()) 
          composeString(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeOrder(String name, Order element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeDateTime("date", element.getDate());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("source", element.getSource());
      composeResourceReference("target", element.getTarget());
      composeString("reason", element.getReason());
      composeResourceReference("authority", element.getAuthority());
      composeOrderOrderWhenComponent("when", element.getWhen());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (ResourceReference e : element.getDetail()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeOrderOrderWhenComponent(String name, Order.OrderWhenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composeSchedule("schedule", element.getSchedule());
      close();
    }
  }

  private void composeOrderResponse(String name, OrderResponse element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("request", element.getRequest());
      composeDateTime("date", element.getDate());
      composeResourceReference("who", element.getWho());
      composeResourceReference("authority", element.getAuthority());
      composeMoney("cost", element.getCost());
      if (element.getCode() != null)
        composeEnumeration("code", element.getCode(), new OrderResponse().new OrderOutcomeCodeEnumFactory());
      composeString("description", element.getDescription());
      if (element.getFulfillment().size() > 0) {
        openArray("fulfillment");
        for (ResourceReference e : element.getFulfillment()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeOrganization(String name, Organization element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeString("name", element.getName());
      composeCodeableConcept("type", element.getType());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      composeResourceReference("partOf", element.getPartOf());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Organization.OrganizationContactComponent e : element.getContact()) 
          composeOrganizationOrganizationContactComponent(null, e);
        closeArray();
      };
      composeBoolean("active", element.getActive());
      close();
    }
  }

  private void composeOrganizationOrganizationContactComponent(String name, Organization.OrganizationContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("purpose", element.getPurpose());
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      close();
    }
  }

  private void composeOther(String name, Other element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("code", element.getCode());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeDate("created", element.getCreated());
      close();
    }
  }

  private void composePatient(String name, Patient element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      if (element.getName().size() > 0) {
        openArray("name");
        for (HumanName e : element.getName()) 
          composeHumanName(null, e);
        closeArray();
      };
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeCodeableConcept("gender", element.getGender());
      composeDateTime("birthDate", element.getBirthDate());
      composeType("deceased", element.getDeceased());
      if (element.getAddress().size() > 0) {
        openArray("address");
        for (Address e : element.getAddress()) 
          composeAddress(null, e);
        closeArray();
      };
      composeCodeableConcept("maritalStatus", element.getMaritalStatus());
      composeType("multipleBirth", element.getMultipleBirth());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Patient.ContactComponent e : element.getContact()) 
          composePatientContactComponent(null, e);
        closeArray();
      };
      composePatientAnimalComponent("animal", element.getAnimal());
      if (element.getCommunication().size() > 0) {
        openArray("communication");
        for (CodeableConcept e : element.getCommunication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("provider", element.getProvider());
      if (element.getLink().size() > 0) {
        openArray("link");
        for (ResourceReference e : element.getLink()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeBoolean("active", element.getActive());
      close();
    }
  }

  private void composePatientContactComponent(String name, Patient.ContactComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getRelationship().size() > 0) {
        openArray("relationship");
        for (CodeableConcept e : element.getRelationship()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      composeResourceReference("organization", element.getOrganization());
      close();
    }
  }

  private void composePatientAnimalComponent(String name, Patient.AnimalComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("species", element.getSpecies());
      composeCodeableConcept("breed", element.getBreed());
      composeCodeableConcept("genderStatus", element.getGenderStatus());
      close();
    }
  }

  private void composePractitioner(String name, Practitioner element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeAddress("address", element.getAddress());
      composeCodeableConcept("gender", element.getGender());
      composeDateTime("birthDate", element.getBirthDate());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      composeResourceReference("organization", element.getOrganization());
      if (element.getRole().size() > 0) {
        openArray("role");
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getSpecialty().size() > 0) {
        openArray("specialty");
        for (CodeableConcept e : element.getSpecialty()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      if (element.getQualification().size() > 0) {
        openArray("qualification");
        for (Practitioner.PractitionerQualificationComponent e : element.getQualification()) 
          composePractitionerPractitionerQualificationComponent(null, e);
        closeArray();
      };
      if (element.getCommunication().size() > 0) {
        openArray("communication");
        for (CodeableConcept e : element.getCommunication()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composePractitionerPractitionerQualificationComponent(String name, Practitioner.PractitionerQualificationComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("code", element.getCode());
      composePeriod("period", element.getPeriod());
      composeResourceReference("issuer", element.getIssuer());
      close();
    }
  }

  private void composeProcedure(String name, Procedure element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeCodeableConcept("type", element.getType());
      if (element.getBodySite().size() > 0) {
        openArray("bodySite");
        for (CodeableConcept e : element.getBodySite()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeString("indication", element.getIndication());
      if (element.getPerformer().size() > 0) {
        openArray("performer");
        for (Procedure.ProcedurePerformerComponent e : element.getPerformer()) 
          composeProcedureProcedurePerformerComponent(null, e);
        closeArray();
      };
      composePeriod("date", element.getDate());
      composeResourceReference("encounter", element.getEncounter());
      composeString("outcome", element.getOutcome());
      if (element.getReport().size() > 0) {
        openArray("report");
        for (ResourceReference e : element.getReport()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeString("complication", element.getComplication());
      composeString("followUp", element.getFollowUp());
      if (element.getRelatedItem().size() > 0) {
        openArray("relatedItem");
        for (Procedure.ProcedureRelatedItemComponent e : element.getRelatedItem()) 
          composeProcedureProcedureRelatedItemComponent(null, e);
        closeArray();
      };
      composeString("notes", element.getNotes());
      close();
    }
  }

  private void composeProcedureProcedurePerformerComponent(String name, Procedure.ProcedurePerformerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("person", element.getPerson());
      composeCodeableConcept("role", element.getRole());
      close();
    }
  }

  private void composeProcedureProcedureRelatedItemComponent(String name, Procedure.ProcedureRelatedItemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Procedure().new ProcedureRelationshipTypeEnumFactory());
      composeResourceReference("target", element.getTarget());
      close();
    }
  }

  private void composeProfile(String name, Profile element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeString("description", element.getDescription());
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Coding e : element.getCode()) 
          composeCoding(null, e);
        closeArray();
      };
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Profile().new ResourceProfileStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      composeId("fhirVersion", element.getFhirVersion());
      if (element.getStructure().size() > 0) {
        openArray("structure");
        for (Profile.ProfileStructureComponent e : element.getStructure()) 
          composeProfileProfileStructureComponent(null, e);
        closeArray();
      };
      if (element.getExtensionDefn().size() > 0) {
        openArray("extensionDefn");
        for (Profile.ProfileExtensionDefnComponent e : element.getExtensionDefn()) 
          composeProfileProfileExtensionDefnComponent(null, e);
        closeArray();
      };
      if (element.getBinding().size() > 0) {
        openArray("binding");
        for (Profile.ProfileBindingComponent e : element.getBinding()) 
          composeProfileProfileBindingComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileProfileStructureComponent(String name, Profile.ProfileStructureComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("type", element.getType());
      composeString("name", element.getName());
      composeBoolean("publish", element.getPublish());
      composeString("purpose", element.getPurpose());
      if (element.getElement().size() > 0) {
        openArray("element");
        for (Profile.ElementComponent e : element.getElement()) 
          composeProfileElementComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileElementComponent(String name, Profile.ElementComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("path", element.getPath());
      composeString("name", element.getName());
      composeProfileElementSlicingComponent("slicing", element.getSlicing());
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      close();
    }
  }

  private void composeProfileElementSlicingComponent(String name, Profile.ElementSlicingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeId("discriminator", element.getDiscriminator());
      composeBoolean("ordered", element.getOrdered());
      if (element.getRules() != null)
        composeEnumeration("rules", element.getRules(), new Profile().new ResourceSlicingRulesEnumFactory());
      close();
    }
  }

  private void composeProfileElementDefinitionComponent(String name, Profile.ElementDefinitionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("short", element.getShort());
      composeString("formal", element.getFormal());
      composeString("comments", element.getComments());
      composeString("requirements", element.getRequirements());
      if (element.getSynonym().size() > 0) {
        openArray("synonym");
        for (String_ e : element.getSynonym()) 
          composeString(null, e);
        closeArray();
      };
      composeInteger("min", element.getMin());
      composeString("max", element.getMax());
      if (element.getType().size() > 0) {
        openArray("type");
        for (Profile.TypeRefComponent e : element.getType()) 
          composeProfileTypeRefComponent(null, e);
        closeArray();
      };
      composeString("nameReference", element.getNameReference());
      composeType("value", element.getValue());
      composeType("example", element.getExample());
      composeInteger("maxLength", element.getMaxLength());
      if (element.getCondition().size() > 0) {
        openArray("condition");
        for (Id e : element.getCondition()) 
          composeId(null, e);
        closeArray();
      };
      if (element.getConstraint().size() > 0) {
        openArray("constraint");
        for (Profile.ElementDefinitionConstraintComponent e : element.getConstraint()) 
          composeProfileElementDefinitionConstraintComponent(null, e);
        closeArray();
      };
      composeBoolean("mustSupport", element.getMustSupport());
      composeBoolean("isModifier", element.getIsModifier());
      composeUri("binding", element.getBinding());
      if (element.getMapping().size() > 0) {
        openArray("mapping");
        for (Profile.ElementDefinitionMappingComponent e : element.getMapping()) 
          composeProfileElementDefinitionMappingComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeProfileTypeRefComponent(String name, Profile.TypeRefComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      composeUri("profile", element.getProfile());
      composeBoolean("bundled", element.getBundled());
      close();
    }
  }

  private void composeProfileElementDefinitionConstraintComponent(String name, Profile.ElementDefinitionConstraintComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeId("key", element.getKey());
      composeString("name", element.getName());
      if (element.getSeverity() != null)
        composeEnumeration("severity", element.getSeverity(), new Profile().new ConstraintSeverityEnumFactory());
      composeString("human", element.getHuman());
      composeString("xpath", element.getXpath());
      composeString("ocl", element.getOcl());
      close();
    }
  }

  private void composeProfileElementDefinitionMappingComponent(String name, Profile.ElementDefinitionMappingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("target", element.getTarget());
      composeString("map", element.getMap());
      close();
    }
  }

  private void composeProfileProfileExtensionDefnComponent(String name, Profile.ProfileExtensionDefnComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      if (element.getContextType() != null)
        composeEnumeration("contextType", element.getContextType(), new Profile().new ExtensionContextEnumFactory());
      if (element.getContext().size() > 0) {
        openArray("context");
        for (String_ e : element.getContext()) 
          composeString(null, e);
        closeArray();
      };
      composeProfileElementDefinitionComponent("definition", element.getDefinition());
      close();
    }
  }

  private void composeProfileProfileBindingComponent(String name, Profile.ProfileBindingComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("name", element.getName());
      composeBoolean("isExtensible", element.getIsExtensible());
      if (element.getConformance() != null)
        composeEnumeration("conformance", element.getConformance(), new Profile().new BindingConformanceEnumFactory());
      composeString("description", element.getDescription());
      composeType("reference", element.getReference());
      close();
    }
  }

  private void composeProvenance(String name, Provenance element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (ResourceReference e : element.getTarget()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composePeriod("period", element.getPeriod());
      composeInstant("recorded", element.getRecorded());
      composeCodeableConcept("reason", element.getReason());
      composeResourceReference("location", element.getLocation());
      if (element.getPolicy().size() > 0) {
        openArray("policy");
        for (Uri e : element.getPolicy()) 
          composeUri(null, e);
        closeArray();
      };
      if (element.getAgent().size() > 0) {
        openArray("agent");
        for (Provenance.ProvenanceAgentComponent e : element.getAgent()) 
          composeProvenanceProvenanceAgentComponent(null, e);
        closeArray();
      };
      if (element.getEntity().size() > 0) {
        openArray("entity");
        for (Provenance.ProvenanceEntityComponent e : element.getEntity()) 
          composeProvenanceProvenanceEntityComponent(null, e);
        closeArray();
      };
      composeString("signature", element.getSignature());
      close();
    }
  }

  private void composeProvenanceProvenanceAgentComponent(String name, Provenance.ProvenanceAgentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCoding("role", element.getRole());
      composeCoding("type", element.getType());
      composeUri("reference", element.getReference());
      composeString("display", element.getDisplay());
      close();
    }
  }

  private void composeProvenanceProvenanceEntityComponent(String name, Provenance.ProvenanceEntityComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getRole() != null)
        composeEnumeration("role", element.getRole(), new Provenance().new ProvenanceEntityRoleEnumFactory());
      composeCoding("type", element.getType());
      composeUri("reference", element.getReference());
      composeString("display", element.getDisplay());
      composeProvenanceProvenanceAgentComponent("agent", element.getAgent());
      close();
    }
  }

  private void composeQuery(String name, Query element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeUri("identifier", element.getIdentifier());
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Extension e : element.getParameter()) 
          composeExtension(null, e);
        closeArray();
      };
      composeQueryQueryResponseComponent("response", element.getResponse());
      close();
    }
  }

  private void composeQueryQueryResponseComponent(String name, Query.QueryResponseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("identifier", element.getIdentifier());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new Query().new QueryOutcomeEnumFactory());
      composeInteger("total", element.getTotal());
      if (element.getParameter().size() > 0) {
        openArray("parameter");
        for (Extension e : element.getParameter()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getFirst().size() > 0) {
        openArray("first");
        for (Extension e : element.getFirst()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getPrevious().size() > 0) {
        openArray("previous");
        for (Extension e : element.getPrevious()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getNext().size() > 0) {
        openArray("next");
        for (Extension e : element.getNext()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getLast().size() > 0) {
        openArray("last");
        for (Extension e : element.getLast()) 
          composeExtension(null, e);
        closeArray();
      };
      if (element.getReference().size() > 0) {
        openArray("reference");
        for (ResourceReference e : element.getReference()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaire(String name, Questionnaire element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new Questionnaire().new ObservationStatusEnumFactory());
      composeDateTime("authored", element.getAuthored());
      composeResourceReference("subject", element.getSubject());
      composeResourceReference("author", element.getAuthor());
      composeResourceReference("source", element.getSource());
      composeCodeableConcept("name", element.getName());
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("encounter", element.getEncounter());
      if (element.getQuestion().size() > 0) {
        openArray("question");
        for (Questionnaire.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireQuestionComponent(null, e);
        closeArray();
      };
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeQuestionnaireQuestionComponent(String name, Questionnaire.QuestionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("name", element.getName());
      composeString("text", element.getText());
      composeType("answer", element.getAnswer());
      if (element.getChoice().size() > 0) {
        openArray("choice");
        for (Coding e : element.getChoice()) 
          composeCoding(null, e);
        closeArray();
      };
      composeType("options", element.getOptions());
      composeType("data", element.getData());
      composeString("remarks", element.getRemarks());
      close();
    }
  }

  private void composeQuestionnaireGroupComponent(String name, Questionnaire.GroupComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("name", element.getName());
      composeString("header", element.getHeader());
      composeString("text", element.getText());
      composeResourceReference("subject", element.getSubject());
      if (element.getQuestion().size() > 0) {
        openArray("question");
        for (Questionnaire.QuestionComponent e : element.getQuestion()) 
          composeQuestionnaireQuestionComponent(null, e);
        closeArray();
      };
      if (element.getGroup().size() > 0) {
        openArray("group");
        for (Questionnaire.GroupComponent e : element.getGroup()) 
          composeQuestionnaireGroupComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeRelatedPerson(String name, RelatedPerson element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeResourceReference("patient", element.getPatient());
      composeCodeableConcept("relationship", element.getRelationship());
      composeHumanName("name", element.getName());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeCodeableConcept("gender", element.getGender());
      composeAddress("address", element.getAddress());
      if (element.getPhoto().size() > 0) {
        openArray("photo");
        for (Attachment e : element.getPhoto()) 
          composeAttachment(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEvent(String name, SecurityEvent element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeSecurityEventSecurityEventEventComponent("event", element.getEvent());
      if (element.getParticipant().size() > 0) {
        openArray("participant");
        for (SecurityEvent.SecurityEventParticipantComponent e : element.getParticipant()) 
          composeSecurityEventSecurityEventParticipantComponent(null, e);
        closeArray();
      };
      composeSecurityEventSecurityEventSourceComponent("source", element.getSource());
      if (element.getObject().size() > 0) {
        openArray("object");
        for (SecurityEvent.SecurityEventObjectComponent e : element.getObject()) 
          composeSecurityEventSecurityEventObjectComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEventSecurityEventEventComponent(String name, SecurityEvent.SecurityEventEventComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCodeableConcept("type", element.getType());
      if (element.getSubtype().size() > 0) {
        openArray("subtype");
        for (CodeableConcept e : element.getSubtype()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      if (element.getAction() != null)
        composeEnumeration("action", element.getAction(), new SecurityEvent().new SecurityEventActionEnumFactory());
      composeInstant("dateTime", element.getDateTime());
      if (element.getOutcome() != null)
        composeEnumeration("outcome", element.getOutcome(), new SecurityEvent().new SecurityEventOutcomeEnumFactory());
      composeString("outcomeDesc", element.getOutcomeDesc());
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantComponent(String name, SecurityEvent.SecurityEventParticipantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getRole().size() > 0) {
        openArray("role");
        for (CodeableConcept e : element.getRole()) 
          composeCodeableConcept(null, e);
        closeArray();
      };
      composeResourceReference("reference", element.getReference());
      composeString("userId", element.getUserId());
      composeString("authId", element.getAuthId());
      composeString("name", element.getName());
      composeBoolean("requestor", element.getRequestor());
      composeCoding("media", element.getMedia());
      composeSecurityEventSecurityEventParticipantNetworkComponent("network", element.getNetwork());
      close();
    }
  }

  private void composeSecurityEventSecurityEventParticipantNetworkComponent(String name, SecurityEvent.SecurityEventParticipantNetworkComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identifier", element.getIdentifier());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent().new NetworkTypeEnumFactory());
      close();
    }
  }

  private void composeSecurityEventSecurityEventSourceComponent(String name, SecurityEvent.SecurityEventSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("site", element.getSite());
      composeString("identifier", element.getIdentifier());
      if (element.getType().size() > 0) {
        openArray("type");
        for (Coding e : element.getType()) 
          composeCoding(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectComponent(String name, SecurityEvent.SecurityEventObjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeResourceReference("reference", element.getReference());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SecurityEvent().new ObjectTypeEnumFactory());
      if (element.getRole() != null)
        composeEnumeration("role", element.getRole(), new SecurityEvent().new ObjectRoleEnumFactory());
      if (element.getLifecycle() != null)
        composeEnumeration("lifecycle", element.getLifecycle(), new SecurityEvent().new ObjectLifecycleEnumFactory());
      composeCodeableConcept("sensitivity", element.getSensitivity());
      composeString("name", element.getName());
      composeBase64Binary("query", element.getQuery());
      if (element.getDetail().size() > 0) {
        openArray("detail");
        for (SecurityEvent.SecurityEventObjectDetailComponent e : element.getDetail()) 
          composeSecurityEventSecurityEventObjectDetailComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSecurityEventSecurityEventObjectDetailComponent(String name, SecurityEvent.SecurityEventObjectDetailComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("type", element.getType());
      composeBase64Binary("value", element.getValue());
      close();
    }
  }

  private void composeSequence(String name, Sequence element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("patient", element.getPatient());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new Sequence().new SequenceTypeEnumFactory());
      composeCodeableConcept("species", element.getSpecies());
      composeSequenceSequenceSampleComponent("sample", element.getSample());
      composeSequenceSequenceCoordinateComponent("coordinate", element.getCoordinate());
      composeResourceReference("inputLab", element.getInputLab());
      composeResourceReference("inputAnalysis", element.getInputAnalysis());
      composeSequenceSequenceInputVariantComponent("inputVariant", element.getInputVariant());
      composeInteger("quality", element.getQuality());
      composeDecimal("quantity", element.getQuantity());
      composeString("read", element.getRead());
      close();
    }
  }

  private void composeSequenceSequenceSampleComponent(String name, Sequence.SequenceSampleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getClass() != null)
        composeEnumeration("class", element.getClass_(), new Sequence().new SampleClassEnumFactory());
      composeCodeableConcept("source", element.getSource());
      close();
    }
  }

  private void composeSequenceSequenceCoordinateComponent(String name, Sequence.SequenceCoordinateComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getChromosome() != null)
        composeEnumeration("chromosome", element.getChromosome(), new Sequence().new ChromosomeEnumFactory());
      composeInteger("start", element.getStart());
      composeInteger("end", element.getEnd());
      close();
    }
  }

  private void composeSequenceSequenceInputVariantComponent(String name, Sequence.SequenceInputVariantComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("sampleId", element.getSampleId());
      composeResourceReference("variant", element.getVariant());
      close();
    }
  }

  private void composeSequencingAnalysis(String name, SequencingAnalysis element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeDate("date", element.getDate());
      composeString("name", element.getName());
      composeSequencingAnalysisSequencingAnalysisGenomeComponent("genome", element.getGenome());
      if (element.getFile().size() > 0) {
        openArray("file");
        for (Attachment e : element.getFile()) 
          composeAttachment(null, e);
        closeArray();
      };
      if (element.getInputLab().size() > 0) {
        openArray("inputLab");
        for (ResourceReference e : element.getInputLab()) 
          composeResourceReference(null, e);
        closeArray();
      };
      if (element.getInputAnalysis().size() > 0) {
        openArray("inputAnalysis");
        for (ResourceReference e : element.getInputAnalysis()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSequencingAnalysisSequencingAnalysisGenomeComponent(String name, SequencingAnalysis.SequencingAnalysisGenomeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getName() != null)
        composeEnumeration("name", element.getName(), new SequencingAnalysis().new RefGenomeEnumFactory());
      composeString("build", element.getBuild());
      close();
    }
  }

  private void composeSequencingLab(String name, SequencingLab element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeResourceReference("subject", element.getSubject());
      composeString("organization", element.getOrganization());
      composeString("name", element.getName());
      composeDate("date", element.getDate());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SequencingLab().new SequencingTypeEnumFactory());
      composeSequencingLabSequencingLabSystemComponent("system", element.getSystem());
      composeSequencingLabSequencingLabSpecimenComponent("specimen", element.getSpecimen());
      if (element.getFile().size() > 0) {
        openArray("file");
        for (Attachment e : element.getFile()) 
          composeAttachment(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSequencingLabSequencingLabSystemComponent(String name, SequencingLab.SequencingLabSystemComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getClass() != null)
        composeEnumeration("class", element.getClass_(), new SequencingLab().new SequencingSystemClassEnumFactory());
      composeString("version", element.getVersion());
      if (element.getName() != null)
        composeEnumeration("name", element.getName(), new SequencingLab().new SequencingSystemNameEnumFactory());
      composeString("identity", element.getIdentity());
      close();
    }
  }

  private void composeSequencingLabSequencingLabSpecimenComponent(String name, SequencingLab.SequencingLabSpecimenComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new SequencingLab().new SequencingSpecimenTypeEnumFactory());
      composeCodeableConcept("source", element.getSource());
      close();
    }
  }

  private void composeSpecimen(String name, Specimen element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCodeableConcept("type", element.getType());
      if (element.getSource().size() > 0) {
        openArray("source");
        for (Specimen.SpecimenSourceComponent e : element.getSource()) 
          composeSpecimenSpecimenSourceComponent(null, e);
        closeArray();
      };
      composeResourceReference("subject", element.getSubject());
      if (element.getAccessionIdentifier().size() > 0) {
        openArray("accessionIdentifier");
        for (Identifier e : element.getAccessionIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeDateTime("receivedTime", element.getReceivedTime());
      composeSpecimenSpecimenCollectionComponent("collection", element.getCollection());
      if (element.getTreatment().size() > 0) {
        openArray("treatment");
        for (Specimen.SpecimenTreatmentComponent e : element.getTreatment()) 
          composeSpecimenSpecimenTreatmentComponent(null, e);
        closeArray();
      };
      if (element.getContainer().size() > 0) {
        openArray("container");
        for (Specimen.SpecimenContainerComponent e : element.getContainer()) 
          composeSpecimenSpecimenContainerComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenSourceComponent(String name, Specimen.SpecimenSourceComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getRelationship() != null)
        composeEnumeration("relationship", element.getRelationship(), new Specimen().new HierarchicalRelationshipTypeEnumFactory());
      if (element.getTarget().size() > 0) {
        openArray("target");
        for (ResourceReference e : element.getTarget()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenCollectionComponent(String name, Specimen.SpecimenCollectionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("collector", element.getCollector());
      if (element.getComment().size() > 0) {
        openArray("comment");
        for (String_ e : element.getComment()) 
          composeString(null, e);
        closeArray();
      };
      composeDateTime("collectedTime", element.getCollectedTime());
      composeQuantity("quantity", element.getQuantity());
      composeCodeableConcept("method", element.getMethod());
      composeCodeableConcept("sourceSite", element.getSourceSite());
      close();
    }
  }

  private void composeSpecimenSpecimenTreatmentComponent(String name, Specimen.SpecimenTreatmentComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("description", element.getDescription());
      composeCodeableConcept("procedure", element.getProcedure());
      if (element.getAdditive().size() > 0) {
        openArray("additive");
        for (ResourceReference e : element.getAdditive()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSpecimenSpecimenContainerComponent(String name, Specimen.SpecimenContainerComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getIdentifier().size() > 0) {
        openArray("identifier");
        for (Identifier e : element.getIdentifier()) 
          composeIdentifier(null, e);
        closeArray();
      };
      composeString("description", element.getDescription());
      composeCodeableConcept("type", element.getType());
      composeQuantity("capacity", element.getCapacity());
      composeQuantity("specimenQuantity", element.getSpecimenQuantity());
      composeResourceReference("additive", element.getAdditive());
      close();
    }
  }

  private void composeSubstance(String name, Substance element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeString("name", element.getName());
      composeCodeableConcept("type", element.getType());
      composeString("description", element.getDescription());
      composeCodeableConcept("status", element.getStatus());
      composePeriod("effectiveTime", element.getEffectiveTime());
      composeQuantity("quantity", element.getQuantity());
      if (element.getIngredient().size() > 0) {
        openArray("ingredient");
        for (ResourceReference e : element.getIngredient()) 
          composeResourceReference(null, e);
        closeArray();
      };
      composeCodeableConcept("quantityMode", element.getQuantityMode());
      close();
    }
  }

  private void composeSupply(String name, Supply element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeCodeableConcept("name", element.getName());
      composeIdentifier("identifier", element.getIdentifier());
      composeCode("status", element.getStatus());
      composeResourceReference("orderedItem", element.getOrderedItem());
      composeResourceReference("patient", element.getPatient());
      if (element.getDispense().size() > 0) {
        openArray("dispense");
        for (Supply.SupplyDispenseComponent e : element.getDispense()) 
          composeSupplySupplyDispenseComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeSupplySupplyDispenseComponent(String name, Supply.SupplyDispenseComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeIdentifier("identifier", element.getIdentifier());
      composeCode("status", element.getStatus());
      composeCodeableConcept("type", element.getType());
      composeQuantity("quantity", element.getQuantity());
      composeResourceReference("suppliedItem", element.getSuppliedItem());
      composeResourceReference("supplier", element.getSupplier());
      composePeriod("whenPrepared", element.getWhenPrepared());
      composePeriod("whenHandedOver", element.getWhenHandedOver());
      composeResourceReference("destination", element.getDestination());
      if (element.getReceiver().size() > 0) {
        openArray("receiver");
        for (ResourceReference e : element.getReceiver()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeUser(String name, User element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeHumanName("name", element.getName());
      composeUri("provider", element.getProvider());
      composeString("login", element.getLogin());
      composeString("password", element.getPassword());
      if (element.getLevel() != null)
        composeEnumeration("level", element.getLevel(), new User().new UserLevelEnumFactory());
      composeInteger("sessionLength", element.getSessionLength());
      if (element.getContact().size() > 0) {
        openArray("contact");
        for (Contact e : element.getContact()) 
          composeContact(null, e);
        closeArray();
      };
      if (element.getPatient().size() > 0) {
        openArray("patient");
        for (ResourceReference e : element.getPatient()) 
          composeResourceReference(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeVCFMeta(String name, VCFMeta element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getSubject().size() > 0) {
        openArray("subject");
        for (VCFMeta.VCFMetaSubjectComponent e : element.getSubject()) 
          composeVCFMetaVCFMetaSubjectComponent(null, e);
        closeArray();
      };
      composeAttachment("sourceFile", element.getSourceFile());
      if (element.getFileFormat() != null)
        composeEnumeration("fileFormat", element.getFileFormat(), new VCFMeta().new VersionEnumFactory());
      composeDate("fileDate", element.getFileDate());
      composeString("reference", element.getReference());
      composeUri("assembly", element.getAssembly());
      composeVCFMetaVCFMetaContigComponent("contig", element.getContig());
      if (element.getInfo().size() > 0) {
        openArray("info");
        for (VCFMeta.VCFMetaInfoComponent e : element.getInfo()) 
          composeVCFMetaVCFMetaInfoComponent(null, e);
        closeArray();
      };
      if (element.getFilter().size() > 0) {
        openArray("filter");
        for (VCFMeta.VCFMetaFilterComponent e : element.getFilter()) 
          composeVCFMetaVCFMetaFilterComponent(null, e);
        closeArray();
      };
      if (element.getFormat().size() > 0) {
        openArray("format");
        for (VCFMeta.VCFMetaFormatComponent e : element.getFormat()) 
          composeVCFMetaVCFMetaFormatComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeVCFMetaVCFMetaSubjectComponent(String name, VCFMeta.VCFMetaSubjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("patient", element.getPatient());
      composeString("fileId", element.getFileId());
      close();
    }
  }

  private void composeVCFMetaVCFMetaContigComponent(String name, VCFMeta.VCFMetaContigComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeUri("url", element.getUrl());
      close();
    }
  }

  private void composeVCFMetaVCFMetaInfoComponent(String name, VCFMeta.VCFMetaInfoComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeInteger("number", element.getNumber());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new VCFMeta().new TypeEnumFactory());
      composeString("description", element.getDescription());
      close();
    }
  }

  private void composeVCFMetaVCFMetaFilterComponent(String name, VCFMeta.VCFMetaFilterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeString("description", element.getDescription());
      close();
    }
  }

  private void composeVCFMetaVCFMetaFormatComponent(String name, VCFMeta.VCFMetaFormatComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeInteger("number", element.getNumber());
      if (element.getType() != null)
        composeEnumeration("type", element.getType(), new VCFMeta().new TypeEnumFactory());
      composeString("description", element.getDescription());
      close();
    }
  }

  private void composeVCFVariant(String name, VCFVariant element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      if (element.getSubject().size() > 0) {
        openArray("subject");
        for (VCFVariant.VCFVariantSubjectComponent e : element.getSubject()) 
          composeVCFVariantVCFVariantSubjectComponent(null, e);
        closeArray();
      };
      composeAttachment("sourceFile", element.getSourceFile());
      composeResourceReference("meta", element.getMeta());
      composeString("chrom", element.getChrom());
      composeInteger("pos", element.getPos());
      composeString("identity", element.getIdentity());
      composeString("ref", element.getRef());
      if (element.getAlt().size() > 0) {
        openArray("alt");
        for (String_ e : element.getAlt()) 
          composeString(null, e);
        closeArray();
      };
      composeInteger("qual", element.getQual());
      composeString("filter", element.getFilter());
      composeVCFVariantVCFVariantInfoComponent("info", element.getInfo());
      if (element.getSample().size() > 0) {
        openArray("sample");
        for (VCFVariant.VCFVariantSampleComponent e : element.getSample()) 
          composeVCFVariantVCFVariantSampleComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeVCFVariantVCFVariantSubjectComponent(String name, VCFVariant.VCFVariantSubjectComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeResourceReference("patient", element.getPatient());
      composeString("fileId", element.getFileId());
      close();
    }
  }

  private void composeVCFVariantVCFVariantInfoComponent(String name, VCFVariant.VCFVariantInfoComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      composeString("value", element.getValue());
      close();
    }
  }

  private void composeVCFVariantVCFVariantSampleComponent(String name, VCFVariant.VCFVariantSampleComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      if (element.getField().size() > 0) {
        openArray("field");
        for (VCFVariant.VCFVariantSampleFieldComponent e : element.getField()) 
          composeVCFVariantVCFVariantSampleFieldComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeVCFVariantVCFVariantSampleFieldComponent(String name, VCFVariant.VCFVariantSampleFieldComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeString("identity", element.getIdentity());
      if (element.getValue().size() > 0) {
        openArray("value");
        for (String_ e : element.getValue()) 
          composeString(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSet(String name, ValueSet element) throws Exception {
    if (element != null) {
      open(name);
      composeResourceElements(element);
      composeString("identifier", element.getIdentifier());
      composeString("version", element.getVersion());
      composeString("name", element.getName());
      composeString("publisher", element.getPublisher());
      if (element.getTelecom().size() > 0) {
        openArray("telecom");
        for (Contact e : element.getTelecom()) 
          composeContact(null, e);
        closeArray();
      };
      composeString("description", element.getDescription());
      composeString("copyright", element.getCopyright());
      if (element.getStatus() != null)
        composeEnumeration("status", element.getStatus(), new ValueSet().new ValuesetStatusEnumFactory());
      composeBoolean("experimental", element.getExperimental());
      composeDateTime("date", element.getDate());
      composeValueSetValueSetDefineComponent("define", element.getDefine());
      composeValueSetValueSetComposeComponent("compose", element.getCompose());
      composeValueSetValueSetExpansionComponent("expansion", element.getExpansion());
      close();
    }
  }

  private void composeValueSetValueSetDefineComponent(String name, ValueSet.ValueSetDefineComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("system", element.getSystem());
      composeBoolean("caseSensitive", element.getCaseSensitive());
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
          composeValueSetValueSetDefineConceptComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetDefineConceptComponent(String name, ValueSet.ValueSetDefineConceptComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("code", element.getCode());
      composeBoolean("abstract", element.getAbstract());
      composeString("display", element.getDisplay());
      composeString("definition", element.getDefinition());
      if (element.getConcept().size() > 0) {
        openArray("concept");
        for (ValueSet.ValueSetDefineConceptComponent e : element.getConcept()) 
          composeValueSetValueSetDefineConceptComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetComposeComponent(String name, ValueSet.ValueSetComposeComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      if (element.getImport().size() > 0) {
        openArray("import");
        for (Uri e : element.getImport()) 
          composeUri(null, e);
        closeArray();
      };
      if (element.getInclude().size() > 0) {
        openArray("include");
        for (ValueSet.ConceptSetComponent e : element.getInclude()) 
          composeValueSetConceptSetComponent(null, e);
        closeArray();
      };
      if (element.getExclude().size() > 0) {
        openArray("exclude");
        for (ValueSet.ConceptSetComponent e : element.getExclude()) 
          composeValueSetConceptSetComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptSetComponent(String name, ValueSet.ConceptSetComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("system", element.getSystem());
      composeString("version", element.getVersion());
      if (element.getCode().size() > 0) {
        openArray("code");
        for (Code e : element.getCode()) 
          composeCode(null, e);
        closeArray();
      };
      if (element.getFilter().size() > 0) {
        openArray("filter");
        for (ValueSet.ConceptSetFilterComponent e : element.getFilter()) 
          composeValueSetConceptSetFilterComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetConceptSetFilterComponent(String name, ValueSet.ConceptSetFilterComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeCode("property", element.getProperty());
      if (element.getOp() != null)
        composeEnumeration("op", element.getOp(), new ValueSet().new FilterOperatorEnumFactory());
      composeCode("value", element.getValue());
      close();
    }
  }

  private void composeValueSetValueSetExpansionComponent(String name, ValueSet.ValueSetExpansionComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeInstant("timestamp", element.getTimestamp());
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
      close();
    }
  }

  private void composeValueSetValueSetExpansionContainsComponent(String name, ValueSet.ValueSetExpansionContainsComponent element) throws Exception {
    if (element != null) {
      open(name);
      composeElement(element);
      composeUri("system", element.getSystem());
      composeCode("code", element.getCode());
      composeString("display", element.getDisplay());
      if (element.getContains().size() > 0) {
        openArray("contains");
        for (ValueSet.ValueSetExpansionContainsComponent e : element.getContains()) 
          composeValueSetValueSetExpansionContainsComponent(null, e);
        closeArray();
      };
      close();
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
    else if (resource instanceof Condition)
      composeCondition("Condition", (Condition)resource);
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
    else if (resource instanceof Encounter)
      composeEncounter("Encounter", (Encounter)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory("FamilyHistory", (FamilyHistory)resource);
    else if (resource instanceof GVFMeta)
      composeGVFMeta("GVFMeta", (GVFMeta)resource);
    else if (resource instanceof GVFVariant)
      composeGVFVariant("GVFVariant", (GVFVariant)resource);
    else if (resource instanceof GeneExpression)
      composeGeneExpression("GeneExpression", (GeneExpression)resource);
    else if (resource instanceof GeneticAnalysis)
      composeGeneticAnalysis("GeneticAnalysis", (GeneticAnalysis)resource);
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
    else if (resource instanceof Message)
      composeMessage("Message", (Message)resource);
    else if (resource instanceof Microarray)
      composeMicroarray("Microarray", (Microarray)resource);
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
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson("RelatedPerson", (RelatedPerson)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent("SecurityEvent", (SecurityEvent)resource);
    else if (resource instanceof Sequence)
      composeSequence("Sequence", (Sequence)resource);
    else if (resource instanceof SequencingAnalysis)
      composeSequencingAnalysis("SequencingAnalysis", (SequencingAnalysis)resource);
    else if (resource instanceof SequencingLab)
      composeSequencingLab("SequencingLab", (SequencingLab)resource);
    else if (resource instanceof Specimen)
      composeSpecimen("Specimen", (Specimen)resource);
    else if (resource instanceof Substance)
      composeSubstance("Substance", (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply("Supply", (Supply)resource);
    else if (resource instanceof User)
      composeUser("User", (User)resource);
    else if (resource instanceof VCFMeta)
      composeVCFMeta("VCFMeta", (VCFMeta)resource);
    else if (resource instanceof VCFVariant)
      composeVCFVariant("VCFVariant", (VCFVariant)resource);
    else if (resource instanceof ValueSet)
      composeValueSet("ValueSet", (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary("Binary", (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  protected void composeNamedResource(String name, Resource resource) throws Exception {
    if (resource instanceof AdverseReaction)
      composeAdverseReaction(name, (AdverseReaction)resource);
    else if (resource instanceof Alert)
      composeAlert(name, (Alert)resource);
    else if (resource instanceof AllergyIntolerance)
      composeAllergyIntolerance(name, (AllergyIntolerance)resource);
    else if (resource instanceof CarePlan)
      composeCarePlan(name, (CarePlan)resource);
    else if (resource instanceof Condition)
      composeCondition(name, (Condition)resource);
    else if (resource instanceof Conformance)
      composeConformance(name, (Conformance)resource);
    else if (resource instanceof Coverage)
      composeCoverage(name, (Coverage)resource);
    else if (resource instanceof Device)
      composeDevice(name, (Device)resource);
    else if (resource instanceof DeviceCapabilities)
      composeDeviceCapabilities(name, (DeviceCapabilities)resource);
    else if (resource instanceof DeviceLog)
      composeDeviceLog(name, (DeviceLog)resource);
    else if (resource instanceof DeviceObservation)
      composeDeviceObservation(name, (DeviceObservation)resource);
    else if (resource instanceof DiagnosticOrder)
      composeDiagnosticOrder(name, (DiagnosticOrder)resource);
    else if (resource instanceof DiagnosticReport)
      composeDiagnosticReport(name, (DiagnosticReport)resource);
    else if (resource instanceof Document)
      composeDocument(name, (Document)resource);
    else if (resource instanceof DocumentReference)
      composeDocumentReference(name, (DocumentReference)resource);
    else if (resource instanceof Encounter)
      composeEncounter(name, (Encounter)resource);
    else if (resource instanceof FamilyHistory)
      composeFamilyHistory(name, (FamilyHistory)resource);
    else if (resource instanceof GVFMeta)
      composeGVFMeta(name, (GVFMeta)resource);
    else if (resource instanceof GVFVariant)
      composeGVFVariant(name, (GVFVariant)resource);
    else if (resource instanceof GeneExpression)
      composeGeneExpression(name, (GeneExpression)resource);
    else if (resource instanceof GeneticAnalysis)
      composeGeneticAnalysis(name, (GeneticAnalysis)resource);
    else if (resource instanceof Group)
      composeGroup(name, (Group)resource);
    else if (resource instanceof ImagingStudy)
      composeImagingStudy(name, (ImagingStudy)resource);
    else if (resource instanceof Immunization)
      composeImmunization(name, (Immunization)resource);
    else if (resource instanceof ImmunizationProfile)
      composeImmunizationProfile(name, (ImmunizationProfile)resource);
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
    else if (resource instanceof Message)
      composeMessage(name, (Message)resource);
    else if (resource instanceof Microarray)
      composeMicroarray(name, (Microarray)resource);
    else if (resource instanceof Observation)
      composeObservation(name, (Observation)resource);
    else if (resource instanceof OperationOutcome)
      composeOperationOutcome(name, (OperationOutcome)resource);
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
    else if (resource instanceof Profile)
      composeProfile(name, (Profile)resource);
    else if (resource instanceof Provenance)
      composeProvenance(name, (Provenance)resource);
    else if (resource instanceof Query)
      composeQuery(name, (Query)resource);
    else if (resource instanceof Questionnaire)
      composeQuestionnaire(name, (Questionnaire)resource);
    else if (resource instanceof RelatedPerson)
      composeRelatedPerson(name, (RelatedPerson)resource);
    else if (resource instanceof SecurityEvent)
      composeSecurityEvent(name, (SecurityEvent)resource);
    else if (resource instanceof Sequence)
      composeSequence(name, (Sequence)resource);
    else if (resource instanceof SequencingAnalysis)
      composeSequencingAnalysis(name, (SequencingAnalysis)resource);
    else if (resource instanceof SequencingLab)
      composeSequencingLab(name, (SequencingLab)resource);
    else if (resource instanceof Specimen)
      composeSpecimen(name, (Specimen)resource);
    else if (resource instanceof Substance)
      composeSubstance(name, (Substance)resource);
    else if (resource instanceof Supply)
      composeSupply(name, (Supply)resource);
    else if (resource instanceof User)
      composeUser(name, (User)resource);
    else if (resource instanceof VCFMeta)
      composeVCFMeta(name, (VCFMeta)resource);
    else if (resource instanceof VCFVariant)
      composeVCFVariant(name, (VCFVariant)resource);
    else if (resource instanceof ValueSet)
      composeValueSet(name, (ValueSet)resource);
    else if (resource instanceof Binary)
      composeBinary(name, (Binary)resource);
    else
      throw new Exception("Unhanded resource type "+resource.getClass().getName());
  }

  @SuppressWarnings("unchecked")
  protected void composeType(String prefix, Type type) throws Exception {
    if (type == null)
      ;
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
    else if (type instanceof SampledData)
       composeSampledData(prefix+"SampledData", (SampledData) type);
    else if (type instanceof ResourceReference)
       composeResourceReference(prefix+"Resource", (ResourceReference) type);
    else if (type instanceof CodeableConcept)
       composeCodeableConcept(prefix+"CodeableConcept", (CodeableConcept) type);
    else if (type instanceof Identifier)
       composeIdentifier(prefix+"Identifier", (Identifier) type);
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
    else if (type instanceof Schedule)
       composeSchedule(prefix+"Schedule", (Schedule) type);
    else if (type instanceof Contact)
       composeContact(prefix+"Contact", (Contact) type);
    else if (type instanceof Address)
       composeAddress(prefix+"Address", (Address) type);
    else if (type instanceof HumanName)
       composeHumanName(prefix+"HumanName", (HumanName) type);
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

