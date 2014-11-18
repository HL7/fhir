package org.hl7.fhir.instance.model;

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

// Generated on Tue, Nov 18, 2014 14:45+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Risk of harmful or undesirable, physiological response which is unique to an individual and associated with exposure to a substance.
 */
public class AllergyIntolerance extends DomainResource {

    public enum AllergyIntoleranceStatus {
        UNCONFIRMED, // A low level of certainty about the propensity for a reaction to the identified Substance.
        CONFIRMED, // A high level of certainty about the propensity for a reaction to the identified Substance, which may include clinical evidence by testing or rechallenge.
        RESOLVED, // A reaction to the identified Substance has been clinically reassessed by testing or rechallenge and considered to be resolved.
        REFUTED, // A propensity for a reaction to the identified Substance has been disproven with a high level of clinical certainty, which may include testing or rechallenge, and is refuted.
        NULL; // added to help the parsers
        public static AllergyIntoleranceStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unconfirmed".equals(codeString))
          return UNCONFIRMED;
        if ("confirmed".equals(codeString))
          return CONFIRMED;
        if ("resolved".equals(codeString))
          return RESOLVED;
        if ("refuted".equals(codeString))
          return REFUTED;
        throw new Exception("Unknown AllergyIntoleranceStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case UNCONFIRMED: return "unconfirmed";
            case CONFIRMED: return "confirmed";
            case RESOLVED: return "resolved";
            case REFUTED: return "refuted";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case UNCONFIRMED: return "A low level of certainty about the propensity for a reaction to the identified Substance.";
            case CONFIRMED: return "A high level of certainty about the propensity for a reaction to the identified Substance, which may include clinical evidence by testing or rechallenge.";
            case RESOLVED: return "A reaction to the identified Substance has been clinically reassessed by testing or rechallenge and considered to be resolved.";
            case REFUTED: return "A propensity for a reaction to the identified Substance has been disproven with a high level of clinical certainty, which may include testing or rechallenge, and is refuted.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case UNCONFIRMED: return "Unconfirmed";
            case CONFIRMED: return "Confirmed";
            case RESOLVED: return "Resolved";
            case REFUTED: return "Refuted";
            default: return "?";
          }
        }
    }

  public static class AllergyIntoleranceStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unconfirmed".equals(codeString))
          return AllergyIntoleranceStatus.UNCONFIRMED;
        if ("confirmed".equals(codeString))
          return AllergyIntoleranceStatus.CONFIRMED;
        if ("resolved".equals(codeString))
          return AllergyIntoleranceStatus.RESOLVED;
        if ("refuted".equals(codeString))
          return AllergyIntoleranceStatus.REFUTED;
        throw new Exception("Unknown AllergyIntoleranceStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AllergyIntoleranceStatus.UNCONFIRMED)
        return "unconfirmed";
      if (code == AllergyIntoleranceStatus.CONFIRMED)
        return "confirmed";
      if (code == AllergyIntoleranceStatus.RESOLVED)
        return "resolved";
      if (code == AllergyIntoleranceStatus.REFUTED)
        return "refuted";
      return "?";
      }
    }

    public enum AllergyIntoleranceCriticality {
        LOW, // The potential clinical impact of a future reaction is estimated as low risk. Future exposure to the Substance is considered a relative contra-indication.
        HIGH, // The potential clinical impact of a future reaction is estimated as high risk. Future exposure to the Substance may be considered an absolute contra-indication.
        NULL; // added to help the parsers
        public static AllergyIntoleranceCriticality fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("low".equals(codeString))
          return LOW;
        if ("high".equals(codeString))
          return HIGH;
        throw new Exception("Unknown AllergyIntoleranceCriticality code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case LOW: return "low";
            case HIGH: return "high";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case LOW: return "The potential clinical impact of a future reaction is estimated as low risk. Future exposure to the Substance is considered a relative contra-indication.";
            case HIGH: return "The potential clinical impact of a future reaction is estimated as high risk. Future exposure to the Substance may be considered an absolute contra-indication.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case LOW: return "Low Risk";
            case HIGH: return "High Risk";
            default: return "?";
          }
        }
    }

  public static class AllergyIntoleranceCriticalityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("low".equals(codeString))
          return AllergyIntoleranceCriticality.LOW;
        if ("high".equals(codeString))
          return AllergyIntoleranceCriticality.HIGH;
        throw new Exception("Unknown AllergyIntoleranceCriticality code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AllergyIntoleranceCriticality.LOW)
        return "low";
      if (code == AllergyIntoleranceCriticality.HIGH)
        return "high";
      return "?";
      }
    }

    public enum AllergyIntoleranceType {
        IMMUNE, // Immune mediated reaction, including allergic reactions and hypersensitivities.
        NONIMMUNE, // A non-immune mediated reaction, which can include pseudoallergic reactions, side effects, intolerances, drug toxicities (eg to Gentamicin), drug-drug interactions, food-drug interactions, and drug-disease interactions.
        NULL; // added to help the parsers
        public static AllergyIntoleranceType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("immune".equals(codeString))
          return IMMUNE;
        if ("non-immune".equals(codeString))
          return NONIMMUNE;
        throw new Exception("Unknown AllergyIntoleranceType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case IMMUNE: return "immune";
            case NONIMMUNE: return "non-immune";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case IMMUNE: return "Immune mediated reaction, including allergic reactions and hypersensitivities.";
            case NONIMMUNE: return "A non-immune mediated reaction, which can include pseudoallergic reactions, side effects, intolerances, drug toxicities (eg to Gentamicin), drug-drug interactions, food-drug interactions, and drug-disease interactions.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case IMMUNE: return "Immune Mediated";
            case NONIMMUNE: return "Non-immune mediated";
            default: return "?";
          }
        }
    }

  public static class AllergyIntoleranceTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("immune".equals(codeString))
          return AllergyIntoleranceType.IMMUNE;
        if ("non-immune".equals(codeString))
          return AllergyIntoleranceType.NONIMMUNE;
        throw new Exception("Unknown AllergyIntoleranceType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AllergyIntoleranceType.IMMUNE)
        return "immune";
      if (code == AllergyIntoleranceType.NONIMMUNE)
        return "non-immune";
      return "?";
      }
    }

    public enum AllergyIntoleranceCategory {
        FOOD, // Any substance consumed to provide nutritional support for the body.
        MEDICATION, // Substances administered to achieve a physiological effect.
        ENVIRONMENT, // Substances that are encountered in the environment.
        NULL; // added to help the parsers
        public static AllergyIntoleranceCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("food".equals(codeString))
          return FOOD;
        if ("medication".equals(codeString))
          return MEDICATION;
        if ("environment".equals(codeString))
          return ENVIRONMENT;
        throw new Exception("Unknown AllergyIntoleranceCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case FOOD: return "food";
            case MEDICATION: return "medication";
            case ENVIRONMENT: return "environment";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case FOOD: return "Any substance consumed to provide nutritional support for the body.";
            case MEDICATION: return "Substances administered to achieve a physiological effect.";
            case ENVIRONMENT: return "Substances that are encountered in the environment.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case FOOD: return "Food";
            case MEDICATION: return "Medication";
            case ENVIRONMENT: return "Environment";
            default: return "?";
          }
        }
    }

  public static class AllergyIntoleranceCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("food".equals(codeString))
          return AllergyIntoleranceCategory.FOOD;
        if ("medication".equals(codeString))
          return AllergyIntoleranceCategory.MEDICATION;
        if ("environment".equals(codeString))
          return AllergyIntoleranceCategory.ENVIRONMENT;
        throw new Exception("Unknown AllergyIntoleranceCategory code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AllergyIntoleranceCategory.FOOD)
        return "food";
      if (code == AllergyIntoleranceCategory.MEDICATION)
        return "medication";
      if (code == AllergyIntoleranceCategory.ENVIRONMENT)
        return "environment";
      return "?";
      }
    }

    public enum ReactionEventCertainty {
        UNLIKELY, // There is a low level of clinical certainty that the reaction was caused by the identified Substance.
        LIKELY, // There is a high level of clinical certainty that the reaction was caused by the identified Substance.
        CONFIRMED, // There is a very high level of clinical certainty that the reaction was due to the identified Substance, which may include clinical evidence by testing or rechallenge.
        NULL; // added to help the parsers
        public static ReactionEventCertainty fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unlikely".equals(codeString))
          return UNLIKELY;
        if ("likely".equals(codeString))
          return LIKELY;
        if ("confirmed".equals(codeString))
          return CONFIRMED;
        throw new Exception("Unknown ReactionEventCertainty code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case UNLIKELY: return "unlikely";
            case LIKELY: return "likely";
            case CONFIRMED: return "confirmed";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case UNLIKELY: return "There is a low level of clinical certainty that the reaction was caused by the identified Substance.";
            case LIKELY: return "There is a high level of clinical certainty that the reaction was caused by the identified Substance.";
            case CONFIRMED: return "There is a very high level of clinical certainty that the reaction was due to the identified Substance, which may include clinical evidence by testing or rechallenge.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case UNLIKELY: return "Unlikely";
            case LIKELY: return "Likely";
            case CONFIRMED: return "Confirmed";
            default: return "?";
          }
        }
    }

  public static class ReactionEventCertaintyEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("unlikely".equals(codeString))
          return ReactionEventCertainty.UNLIKELY;
        if ("likely".equals(codeString))
          return ReactionEventCertainty.LIKELY;
        if ("confirmed".equals(codeString))
          return ReactionEventCertainty.CONFIRMED;
        throw new Exception("Unknown ReactionEventCertainty code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ReactionEventCertainty.UNLIKELY)
        return "unlikely";
      if (code == ReactionEventCertainty.LIKELY)
        return "likely";
      if (code == ReactionEventCertainty.CONFIRMED)
        return "confirmed";
      return "?";
      }
    }

    public enum ReactionEventSeverity {
        MILD, // Causes mild physiological effects.
        MODERATE, // Causes moderate physiological effects.
        SEVERE, // Causes severe physiological effects.
        NULL; // added to help the parsers
        public static ReactionEventSeverity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("mild".equals(codeString))
          return MILD;
        if ("moderate".equals(codeString))
          return MODERATE;
        if ("severe".equals(codeString))
          return SEVERE;
        throw new Exception("Unknown ReactionEventSeverity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case MILD: return "mild";
            case MODERATE: return "moderate";
            case SEVERE: return "severe";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case MILD: return "Causes mild physiological effects.";
            case MODERATE: return "Causes moderate physiological effects.";
            case SEVERE: return "Causes severe physiological effects.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case MILD: return "Mild";
            case MODERATE: return "Moderate";
            case SEVERE: return "Severe";
            default: return "?";
          }
        }
    }

  public static class ReactionEventSeverityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("mild".equals(codeString))
          return ReactionEventSeverity.MILD;
        if ("moderate".equals(codeString))
          return ReactionEventSeverity.MODERATE;
        if ("severe".equals(codeString))
          return ReactionEventSeverity.SEVERE;
        throw new Exception("Unknown ReactionEventSeverity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ReactionEventSeverity.MILD)
        return "mild";
      if (code == ReactionEventSeverity.MODERATE)
        return "moderate";
      if (code == ReactionEventSeverity.SEVERE)
        return "severe";
      return "?";
      }
    }

    public static class AllergyIntoleranceEventComponent extends BackboneElement {
        /**
         * Identification of the specific substance considered to be responsible for the Adverse Reaction event. Note: the substance for a specific reaction may be different to the substance identified as the cause of the risk, but must be consistent with it. For instance, it may be a more specific substance (e.g. a brand medication) or a composite substance that includes the identified substance. It must be clinically safe to only process the AllergyIntolerance.substance and ignore the AllergyIntolerance.event.substance.
         */
        protected CodeableConcept substance;

        /**
         * Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.
         */
        protected Enumeration<ReactionEventCertainty> certainty;

        /**
         * Clinical symptoms and/or signs that are observed or associated with the Adverse Reaction Event.
         */
        protected List<CodeableConcept> manifestation = new ArrayList<CodeableConcept>();

        /**
         * Text description about the Reaction as a whole, including details of the manifestation if required.
         */
        protected StringType description;

        /**
         * Record of the date and/or time of the onset of the Reaction.
         */
        protected DateTimeType onset;

        /**
         * The amount of time that the Adverse Reaction persisted.
         */
        protected Duration duration;

        /**
         * Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.
         */
        protected Enumeration<ReactionEventSeverity> severity;

        /**
         * Identification of the route by which the subject was exposed to the substance.
         */
        protected CodeableConcept exposureRoute;

        /**
         * Additional text about the Adverse Reaction event not captured in other fields.
         */
        protected StringType comment;

        private static final long serialVersionUID = 536798130L;

      public AllergyIntoleranceEventComponent() {
        super();
      }

        /**
         * @return {@link #substance} (Identification of the specific substance considered to be responsible for the Adverse Reaction event. Note: the substance for a specific reaction may be different to the substance identified as the cause of the risk, but must be consistent with it. For instance, it may be a more specific substance (e.g. a brand medication) or a composite substance that includes the identified substance. It must be clinically safe to only process the AllergyIntolerance.substance and ignore the AllergyIntolerance.event.substance.)
         */
        public CodeableConcept getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Identification of the specific substance considered to be responsible for the Adverse Reaction event. Note: the substance for a specific reaction may be different to the substance identified as the cause of the risk, but must be consistent with it. For instance, it may be a more specific substance (e.g. a brand medication) or a composite substance that includes the identified substance. It must be clinically safe to only process the AllergyIntolerance.substance and ignore the AllergyIntolerance.event.substance.)
         */
        public AllergyIntoleranceEventComponent setSubstance(CodeableConcept value) { 
          this.substance = value;
          return this;
        }

        /**
         * @return {@link #certainty} (Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.). This is the underlying object with id, value and extensions. The accessor "getCertainty" gives direct access to the value
         */
        public Enumeration<ReactionEventCertainty> getCertaintyElement() { 
          return this.certainty;
        }

        /**
         * @param value {@link #certainty} (Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.). This is the underlying object with id, value and extensions. The accessor "getCertainty" gives direct access to the value
         */
        public AllergyIntoleranceEventComponent setCertaintyElement(Enumeration<ReactionEventCertainty> value) { 
          this.certainty = value;
          return this;
        }

        /**
         * @return Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.
         */
        public ReactionEventCertainty getCertainty() { 
          return this.certainty == null ? null : this.certainty.getValue();
        }

        /**
         * @param value Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.
         */
        public AllergyIntoleranceEventComponent setCertainty(ReactionEventCertainty value) { 
          if (value == null)
            this.certainty = null;
          else {
            if (this.certainty == null)
              this.certainty = new Enumeration<ReactionEventCertainty>();
            this.certainty.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #manifestation} (Clinical symptoms and/or signs that are observed or associated with the Adverse Reaction Event.)
         */
        public List<CodeableConcept> getManifestation() { 
          return this.manifestation;
        }

        /**
         * @return {@link #manifestation} (Clinical symptoms and/or signs that are observed or associated with the Adverse Reaction Event.)
         */
    // syntactic sugar
        public CodeableConcept addManifestation() { //3
          CodeableConcept t = new CodeableConcept();
          this.manifestation.add(t);
          return t;
        }

        /**
         * @return {@link #description} (Text description about the Reaction as a whole, including details of the manifestation if required.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Text description about the Reaction as a whole, including details of the manifestation if required.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public AllergyIntoleranceEventComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Text description about the Reaction as a whole, including details of the manifestation if required.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Text description about the Reaction as a whole, including details of the manifestation if required.
         */
        public AllergyIntoleranceEventComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #onset} (Record of the date and/or time of the onset of the Reaction.). This is the underlying object with id, value and extensions. The accessor "getOnset" gives direct access to the value
         */
        public DateTimeType getOnsetElement() { 
          return this.onset;
        }

        /**
         * @param value {@link #onset} (Record of the date and/or time of the onset of the Reaction.). This is the underlying object with id, value and extensions. The accessor "getOnset" gives direct access to the value
         */
        public AllergyIntoleranceEventComponent setOnsetElement(DateTimeType value) { 
          this.onset = value;
          return this;
        }

        /**
         * @return Record of the date and/or time of the onset of the Reaction.
         */
        public DateAndTime getOnset() { 
          return this.onset == null ? null : this.onset.getValue();
        }

        /**
         * @param value Record of the date and/or time of the onset of the Reaction.
         */
        public AllergyIntoleranceEventComponent setOnset(DateAndTime value) { 
          if (value == null)
            this.onset = null;
          else {
            if (this.onset == null)
              this.onset = new DateTimeType();
            this.onset.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #duration} (The amount of time that the Adverse Reaction persisted.)
         */
        public Duration getDuration() { 
          return this.duration;
        }

        /**
         * @param value {@link #duration} (The amount of time that the Adverse Reaction persisted.)
         */
        public AllergyIntoleranceEventComponent setDuration(Duration value) { 
          this.duration = value;
          return this;
        }

        /**
         * @return {@link #severity} (Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
         */
        public Enumeration<ReactionEventSeverity> getSeverityElement() { 
          return this.severity;
        }

        /**
         * @param value {@link #severity} (Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
         */
        public AllergyIntoleranceEventComponent setSeverityElement(Enumeration<ReactionEventSeverity> value) { 
          this.severity = value;
          return this;
        }

        /**
         * @return Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.
         */
        public ReactionEventSeverity getSeverity() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        /**
         * @param value Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.
         */
        public AllergyIntoleranceEventComponent setSeverity(ReactionEventSeverity value) { 
          if (value == null)
            this.severity = null;
          else {
            if (this.severity == null)
              this.severity = new Enumeration<ReactionEventSeverity>();
            this.severity.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #exposureRoute} (Identification of the route by which the subject was exposed to the substance.)
         */
        public CodeableConcept getExposureRoute() { 
          return this.exposureRoute;
        }

        /**
         * @param value {@link #exposureRoute} (Identification of the route by which the subject was exposed to the substance.)
         */
        public AllergyIntoleranceEventComponent setExposureRoute(CodeableConcept value) { 
          this.exposureRoute = value;
          return this;
        }

        /**
         * @return {@link #comment} (Additional text about the Adverse Reaction event not captured in other fields.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
         */
        public StringType getCommentElement() { 
          return this.comment;
        }

        /**
         * @param value {@link #comment} (Additional text about the Adverse Reaction event not captured in other fields.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
         */
        public AllergyIntoleranceEventComponent setCommentElement(StringType value) { 
          this.comment = value;
          return this;
        }

        /**
         * @return Additional text about the Adverse Reaction event not captured in other fields.
         */
        public String getComment() { 
          return this.comment == null ? null : this.comment.getValue();
        }

        /**
         * @param value Additional text about the Adverse Reaction event not captured in other fields.
         */
        public AllergyIntoleranceEventComponent setComment(String value) { 
          if (Utilities.noString(value))
            this.comment = null;
          else {
            if (this.comment == null)
              this.comment = new StringType();
            this.comment.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("substance", "CodeableConcept", "Identification of the specific substance considered to be responsible for the Adverse Reaction event. Note: the substance for a specific reaction may be different to the substance identified as the cause of the risk, but must be consistent with it. For instance, it may be a more specific substance (e.g. a brand medication) or a composite substance that includes the identified substance. It must be clinically safe to only process the AllergyIntolerance.substance and ignore the AllergyIntolerance.event.substance.", 0, java.lang.Integer.MAX_VALUE, substance));
          childrenList.add(new Property("certainty", "code", "Statement about the degree of clinical certainty that the Specific Substance was the cause of the Manifestation in this reaction event.", 0, java.lang.Integer.MAX_VALUE, certainty));
          childrenList.add(new Property("manifestation", "CodeableConcept", "Clinical symptoms and/or signs that are observed or associated with the Adverse Reaction Event.", 0, java.lang.Integer.MAX_VALUE, manifestation));
          childrenList.add(new Property("description", "string", "Text description about the Reaction as a whole, including details of the manifestation if required.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("onset", "dateTime", "Record of the date and/or time of the onset of the Reaction.", 0, java.lang.Integer.MAX_VALUE, onset));
          childrenList.add(new Property("duration", "Duration", "The amount of time that the Adverse Reaction persisted.", 0, java.lang.Integer.MAX_VALUE, duration));
          childrenList.add(new Property("severity", "code", "Clinical assessment of the severity of the reaction event as a whole, potentially considering multiple different manifestations.", 0, java.lang.Integer.MAX_VALUE, severity));
          childrenList.add(new Property("exposureRoute", "CodeableConcept", "Identification of the route by which the subject was exposed to the substance.", 0, java.lang.Integer.MAX_VALUE, exposureRoute));
          childrenList.add(new Property("comment", "string", "Additional text about the Adverse Reaction event not captured in other fields.", 0, java.lang.Integer.MAX_VALUE, comment));
        }

      public AllergyIntoleranceEventComponent copy() {
        AllergyIntoleranceEventComponent dst = new AllergyIntoleranceEventComponent();
        copyValues(dst);
        dst.substance = substance == null ? null : substance.copy();
        dst.certainty = certainty == null ? null : certainty.copy();
        dst.manifestation = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : manifestation)
          dst.manifestation.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.onset = onset == null ? null : onset.copy();
        dst.duration = duration == null ? null : duration.copy();
        dst.severity = severity == null ? null : severity.copy();
        dst.exposureRoute = exposureRoute == null ? null : exposureRoute.copy();
        dst.comment = comment == null ? null : comment.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Date when the sensitivity was recorded.
     */
    protected DateTimeType recordedDate;

    /**
     * Indicates who has responsibility for the record.
     */
    protected Reference recorder;

    /**
     * The actual object that is the target of the reference (Indicates who has responsibility for the record.)
     */
    protected Resource recorderTarget;

    /**
     * The patient who has the allergy or intolerance.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who has the allergy or intolerance.)
     */
    protected Patient subjectTarget;

    /**
     * Identification of a substance, or a class of substances, that is considered to be responsible for the Adverse reaction risk.
     */
    protected CodeableConcept substance;

    /**
     * Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.
     */
    protected Enumeration<AllergyIntoleranceStatus> status;

    /**
     * Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.
     */
    protected Enumeration<AllergyIntoleranceCriticality> criticality;

    /**
     * Identification of the underlying physiological mechanism for the Reaction Risk.
     */
    protected Enumeration<AllergyIntoleranceType> type;

    /**
     * Category of the identified Substance.
     */
    protected Enumeration<AllergyIntoleranceCategory> category;

    /**
     * Represents the date and/or time of the last known occurence of a reaction event.
     */
    protected DateTimeType lastOccurence;

    /**
     * Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.
     */
    protected StringType comment;

    /**
     * Details about each Adverse Reaction Event linked to exposure to the identified Substance.
     */
    protected List<AllergyIntoleranceEventComponent> event = new ArrayList<AllergyIntoleranceEventComponent>();

    private static final long serialVersionUID = -1433178694L;

    public AllergyIntolerance() {
      super();
    }

    public AllergyIntolerance(Reference subject, CodeableConcept substance) {
      super();
      this.subject = subject;
      this.substance = substance;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #recordedDate} (Date when the sensitivity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedDate" gives direct access to the value
     */
    public DateTimeType getRecordedDateElement() { 
      return this.recordedDate;
    }

    /**
     * @param value {@link #recordedDate} (Date when the sensitivity was recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedDate" gives direct access to the value
     */
    public AllergyIntolerance setRecordedDateElement(DateTimeType value) { 
      this.recordedDate = value;
      return this;
    }

    /**
     * @return Date when the sensitivity was recorded.
     */
    public DateAndTime getRecordedDate() { 
      return this.recordedDate == null ? null : this.recordedDate.getValue();
    }

    /**
     * @param value Date when the sensitivity was recorded.
     */
    public AllergyIntolerance setRecordedDate(DateAndTime value) { 
      if (value == null)
        this.recordedDate = null;
      else {
        if (this.recordedDate == null)
          this.recordedDate = new DateTimeType();
        this.recordedDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #recorder} (Indicates who has responsibility for the record.)
     */
    public Reference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Indicates who has responsibility for the record.)
     */
    public AllergyIntolerance setRecorder(Reference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recorder} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Indicates who has responsibility for the record.)
     */
    public Resource getRecorderTarget() { 
      return this.recorderTarget;
    }

    /**
     * @param value {@link #recorder} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Indicates who has responsibility for the record.)
     */
    public AllergyIntolerance setRecorderTarget(Resource value) { 
      this.recorderTarget = value;
      return this;
    }

    /**
     * @return {@link #subject} (The patient who has the allergy or intolerance.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who has the allergy or intolerance.)
     */
    public AllergyIntolerance setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who has the allergy or intolerance.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who has the allergy or intolerance.)
     */
    public AllergyIntolerance setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #substance} (Identification of a substance, or a class of substances, that is considered to be responsible for the Adverse reaction risk.)
     */
    public CodeableConcept getSubstance() { 
      return this.substance;
    }

    /**
     * @param value {@link #substance} (Identification of a substance, or a class of substances, that is considered to be responsible for the Adverse reaction risk.)
     */
    public AllergyIntolerance setSubstance(CodeableConcept value) { 
      this.substance = value;
      return this;
    }

    /**
     * @return {@link #status} (Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<AllergyIntoleranceStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public AllergyIntolerance setStatusElement(Enumeration<AllergyIntoleranceStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.
     */
    public AllergyIntoleranceStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.
     */
    public AllergyIntolerance setStatus(AllergyIntoleranceStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<AllergyIntoleranceStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #criticality} (Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getCriticality" gives direct access to the value
     */
    public Enumeration<AllergyIntoleranceCriticality> getCriticalityElement() { 
      return this.criticality;
    }

    /**
     * @param value {@link #criticality} (Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getCriticality" gives direct access to the value
     */
    public AllergyIntolerance setCriticalityElement(Enumeration<AllergyIntoleranceCriticality> value) { 
      this.criticality = value;
      return this;
    }

    /**
     * @return Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.
     */
    public AllergyIntoleranceCriticality getCriticality() { 
      return this.criticality == null ? null : this.criticality.getValue();
    }

    /**
     * @param value Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.
     */
    public AllergyIntolerance setCriticality(AllergyIntoleranceCriticality value) { 
      if (value == null)
        this.criticality = null;
      else {
        if (this.criticality == null)
          this.criticality = new Enumeration<AllergyIntoleranceCriticality>();
        this.criticality.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #type} (Identification of the underlying physiological mechanism for the Reaction Risk.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<AllergyIntoleranceType> getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Identification of the underlying physiological mechanism for the Reaction Risk.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public AllergyIntolerance setTypeElement(Enumeration<AllergyIntoleranceType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Identification of the underlying physiological mechanism for the Reaction Risk.
     */
    public AllergyIntoleranceType getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Identification of the underlying physiological mechanism for the Reaction Risk.
     */
    public AllergyIntolerance setType(AllergyIntoleranceType value) { 
      if (value == null)
        this.type = null;
      else {
        if (this.type == null)
          this.type = new Enumeration<AllergyIntoleranceType>();
        this.type.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #category} (Category of the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
     */
    public Enumeration<AllergyIntoleranceCategory> getCategoryElement() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (Category of the identified Substance.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
     */
    public AllergyIntolerance setCategoryElement(Enumeration<AllergyIntoleranceCategory> value) { 
      this.category = value;
      return this;
    }

    /**
     * @return Category of the identified Substance.
     */
    public AllergyIntoleranceCategory getCategory() { 
      return this.category == null ? null : this.category.getValue();
    }

    /**
     * @param value Category of the identified Substance.
     */
    public AllergyIntolerance setCategory(AllergyIntoleranceCategory value) { 
      if (value == null)
        this.category = null;
      else {
        if (this.category == null)
          this.category = new Enumeration<AllergyIntoleranceCategory>();
        this.category.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #lastOccurence} (Represents the date and/or time of the last known occurence of a reaction event.). This is the underlying object with id, value and extensions. The accessor "getLastOccurence" gives direct access to the value
     */
    public DateTimeType getLastOccurenceElement() { 
      return this.lastOccurence;
    }

    /**
     * @param value {@link #lastOccurence} (Represents the date and/or time of the last known occurence of a reaction event.). This is the underlying object with id, value and extensions. The accessor "getLastOccurence" gives direct access to the value
     */
    public AllergyIntolerance setLastOccurenceElement(DateTimeType value) { 
      this.lastOccurence = value;
      return this;
    }

    /**
     * @return Represents the date and/or time of the last known occurence of a reaction event.
     */
    public DateAndTime getLastOccurence() { 
      return this.lastOccurence == null ? null : this.lastOccurence.getValue();
    }

    /**
     * @param value Represents the date and/or time of the last known occurence of a reaction event.
     */
    public AllergyIntolerance setLastOccurence(DateAndTime value) { 
      if (value == null)
        this.lastOccurence = null;
      else {
        if (this.lastOccurence == null)
          this.lastOccurence = new DateTimeType();
        this.lastOccurence.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #comment} (Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public StringType getCommentElement() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public AllergyIntolerance setCommentElement(StringType value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.
     */
    public String getComment() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.
     */
    public AllergyIntolerance setComment(String value) { 
      if (Utilities.noString(value))
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new StringType();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #event} (Details about each Adverse Reaction Event linked to exposure to the identified Substance.)
     */
    public List<AllergyIntoleranceEventComponent> getEvent() { 
      return this.event;
    }

    /**
     * @return {@link #event} (Details about each Adverse Reaction Event linked to exposure to the identified Substance.)
     */
    // syntactic sugar
    public AllergyIntoleranceEventComponent addEvent() { //3
      AllergyIntoleranceEventComponent t = new AllergyIntoleranceEventComponent();
      this.event.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this allergy/intolerance concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("recordedDate", "dateTime", "Date when the sensitivity was recorded.", 0, java.lang.Integer.MAX_VALUE, recordedDate));
        childrenList.add(new Property("recorder", "Reference(Practitioner|Patient)", "Indicates who has responsibility for the record.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who has the allergy or intolerance.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("substance", "CodeableConcept", "Identification of a substance, or a class of substances, that is considered to be responsible for the Adverse reaction risk.", 0, java.lang.Integer.MAX_VALUE, substance));
        childrenList.add(new Property("status", "code", "Assertion about certainty associated with the propensity, or potential risk, of a reaction to the identified Substance.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("criticality", "code", "Estimate of the potential clinical harm, or seriousness, of the reaction to the identified Substance.", 0, java.lang.Integer.MAX_VALUE, criticality));
        childrenList.add(new Property("type", "code", "Identification of the underlying physiological mechanism for the Reaction Risk.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("category", "code", "Category of the identified Substance.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("lastOccurence", "dateTime", "Represents the date and/or time of the last known occurence of a reaction event.", 0, java.lang.Integer.MAX_VALUE, lastOccurence));
        childrenList.add(new Property("comment", "string", "Additional narrative about the propensity for the Adverse Reaction, not captured in other fields.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("event", "", "Details about each Adverse Reaction Event linked to exposure to the identified Substance.", 0, java.lang.Integer.MAX_VALUE, event));
      }

      public AllergyIntolerance copy() {
        AllergyIntolerance dst = new AllergyIntolerance();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.recordedDate = recordedDate == null ? null : recordedDate.copy();
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.substance = substance == null ? null : substance.copy();
        dst.status = status == null ? null : status.copy();
        dst.criticality = criticality == null ? null : criticality.copy();
        dst.type = type == null ? null : type.copy();
        dst.category = category == null ? null : category.copy();
        dst.lastOccurence = lastOccurence == null ? null : lastOccurence.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.event = new ArrayList<AllergyIntoleranceEventComponent>();
        for (AllergyIntoleranceEventComponent i : event)
          dst.event.add(i.copy());
        return dst;
      }

      protected AllergyIntolerance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.AllergyIntolerance;
   }


}

