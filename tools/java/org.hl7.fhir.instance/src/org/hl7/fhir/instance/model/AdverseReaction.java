package org.hl7.fhir.instance.model;

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

// Generated on Tue, Dec 10, 2013 15:07+1100 for FHIR v0.12

import java.util.*;

/**
 * Specific reactions to a substance.
 */
public class AdverseReaction extends Resource {

    public enum ReactionSeverity {
        severe, // Severe complications arose due to the reaction.
        serious, // Serious inconvenience to the subject.
        moderate, // Moderate inconvenience to the subject.
        minor, // Minor inconvenience to the subject.
        Null; // added to help the parsers
        public static ReactionSeverity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("severe".equals(codeString))
          return severe;
        if ("serious".equals(codeString))
          return serious;
        if ("moderate".equals(codeString))
          return moderate;
        if ("minor".equals(codeString))
          return minor;
        throw new Exception("Unknown ReactionSeverity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case severe: return "severe";
            case serious: return "serious";
            case moderate: return "moderate";
            case minor: return "minor";
            default: return "?";
          }
        }
    }

  public static class ReactionSeverityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("severe".equals(codeString))
          return ReactionSeverity.severe;
        if ("serious".equals(codeString))
          return ReactionSeverity.serious;
        if ("moderate".equals(codeString))
          return ReactionSeverity.moderate;
        if ("minor".equals(codeString))
          return ReactionSeverity.minor;
        throw new Exception("Unknown ReactionSeverity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ReactionSeverity.severe)
        return "severe";
      if (code == ReactionSeverity.serious)
        return "serious";
      if (code == ReactionSeverity.moderate)
        return "moderate";
      if (code == ReactionSeverity.minor)
        return "minor";
      return "?";
      }
    }

    public enum ExposureType {
        drugadmin, // Drug Administration.
        immuniz, // Immunization.
        coincidental, // In the same area as the substance.
        Null; // added to help the parsers
        public static ExposureType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("drugadmin".equals(codeString))
          return drugadmin;
        if ("immuniz".equals(codeString))
          return immuniz;
        if ("coincidental".equals(codeString))
          return coincidental;
        throw new Exception("Unknown ExposureType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case drugadmin: return "drugadmin";
            case immuniz: return "immuniz";
            case coincidental: return "coincidental";
            default: return "?";
          }
        }
    }

  public static class ExposureTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("drugadmin".equals(codeString))
          return ExposureType.drugadmin;
        if ("immuniz".equals(codeString))
          return ExposureType.immuniz;
        if ("coincidental".equals(codeString))
          return ExposureType.coincidental;
        throw new Exception("Unknown ExposureType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ExposureType.drugadmin)
        return "drugadmin";
      if (code == ExposureType.immuniz)
        return "immuniz";
      if (code == ExposureType.coincidental)
        return "coincidental";
      return "?";
      }
    }

    public enum CausalityExpectation {
        likely, // Likely that this specific exposure caused the reaction.
        unlikely, // Unlikely that this specific exposure caused the reaction - the exposure is being linked to for information purposes.
        confirmed, // It has been confirmed that this exposure was one of the causes of the reaction.
        unknown, // It is unknown whether this exposure had anything to do with the reaction.
        Null; // added to help the parsers
        public static CausalityExpectation fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("likely".equals(codeString))
          return likely;
        if ("unlikely".equals(codeString))
          return unlikely;
        if ("confirmed".equals(codeString))
          return confirmed;
        if ("unknown".equals(codeString))
          return unknown;
        throw new Exception("Unknown CausalityExpectation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case likely: return "likely";
            case unlikely: return "unlikely";
            case confirmed: return "confirmed";
            case unknown: return "unknown";
            default: return "?";
          }
        }
    }

  public static class CausalityExpectationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("likely".equals(codeString))
          return CausalityExpectation.likely;
        if ("unlikely".equals(codeString))
          return CausalityExpectation.unlikely;
        if ("confirmed".equals(codeString))
          return CausalityExpectation.confirmed;
        if ("unknown".equals(codeString))
          return CausalityExpectation.unknown;
        throw new Exception("Unknown CausalityExpectation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CausalityExpectation.likely)
        return "likely";
      if (code == CausalityExpectation.unlikely)
        return "unlikely";
      if (code == CausalityExpectation.confirmed)
        return "confirmed";
      if (code == CausalityExpectation.unknown)
        return "unknown";
      return "?";
      }
    }

    public static class AdverseReactionSymptomComponent extends BackboneElement {
        /**
         * Indicates the specific sign or symptom that was observed.
         */
        protected CodeableConcept code;

        /**
         * The severity of the sign or symptom.
         */
        protected Enumeration<ReactionSeverity> severity;

      public AdverseReactionSymptomComponent() {
        super();
      }

      public AdverseReactionSymptomComponent(CodeableConcept code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Indicates the specific sign or symptom that was observed.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Indicates the specific sign or symptom that was observed.)
         */
        public AdverseReactionSymptomComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #severity} (The severity of the sign or symptom.)
         */
        public Enumeration<ReactionSeverity> getSeverity() { 
          return this.severity;
        }

        /**
         * @param value {@link #severity} (The severity of the sign or symptom.)
         */
        public AdverseReactionSymptomComponent setSeverity(Enumeration<ReactionSeverity> value) { 
          this.severity = value;
          return this;
        }

        /**
         * @return The severity of the sign or symptom.
         */
        public ReactionSeverity getSeveritySimple() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        /**
         * @param value The severity of the sign or symptom.
         */
        public AdverseReactionSymptomComponent setSeveritySimple(ReactionSeverity value) { 
          if (value == null)
            this.severity = null;
          else {
            if (this.severity == null)
              this.severity = new Enumeration<ReactionSeverity>();
            this.severity.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Indicates the specific sign or symptom that was observed.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("severity", "code", "The severity of the sign or symptom.", 0, java.lang.Integer.MAX_VALUE, severity));
        }

      public AdverseReactionSymptomComponent copy(AdverseReaction e) {
        AdverseReactionSymptomComponent dst = new AdverseReactionSymptomComponent();
        dst.code = code == null ? null : code.copy();
        dst.severity = severity == null ? null : severity.copy();
        return dst;
      }

  }

    public static class AdverseReactionExposureComponent extends BackboneElement {
        /**
         * When the exposure occurred.
         */
        protected DateTime exposureDate;

        /**
         * The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        protected Enumeration<ExposureType> exposureType;

        /**
         * A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        protected Enumeration<CausalityExpectation> causalityExpectation;

        /**
         * Substance(s) that is presumed to have caused the adverse reaction.
         */
        protected ResourceReference substance;

      public AdverseReactionExposureComponent() {
        super();
      }

        /**
         * @return {@link #exposureDate} (When the exposure occurred.)
         */
        public DateTime getExposureDate() { 
          return this.exposureDate;
        }

        /**
         * @param value {@link #exposureDate} (When the exposure occurred.)
         */
        public AdverseReactionExposureComponent setExposureDate(DateTime value) { 
          this.exposureDate = value;
          return this;
        }

        /**
         * @return When the exposure occurred.
         */
        public DateAndTime getExposureDateSimple() { 
          return this.exposureDate == null ? null : this.exposureDate.getValue();
        }

        /**
         * @param value When the exposure occurred.
         */
        public AdverseReactionExposureComponent setExposureDateSimple(DateAndTime value) { 
          if (value == null)
            this.exposureDate = null;
          else {
            if (this.exposureDate == null)
              this.exposureDate = new DateTime();
            this.exposureDate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #exposureType} (The type of exposure: Drug Administration, Immunization, Coincidental.)
         */
        public Enumeration<ExposureType> getExposureType() { 
          return this.exposureType;
        }

        /**
         * @param value {@link #exposureType} (The type of exposure: Drug Administration, Immunization, Coincidental.)
         */
        public AdverseReactionExposureComponent setExposureType(Enumeration<ExposureType> value) { 
          this.exposureType = value;
          return this;
        }

        /**
         * @return The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public ExposureType getExposureTypeSimple() { 
          return this.exposureType == null ? null : this.exposureType.getValue();
        }

        /**
         * @param value The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public AdverseReactionExposureComponent setExposureTypeSimple(ExposureType value) { 
          if (value == null)
            this.exposureType = null;
          else {
            if (this.exposureType == null)
              this.exposureType = new Enumeration<ExposureType>();
            this.exposureType.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #causalityExpectation} (A statement of how confident that the recorder was that this exposure caused the reaction.)
         */
        public Enumeration<CausalityExpectation> getCausalityExpectation() { 
          return this.causalityExpectation;
        }

        /**
         * @param value {@link #causalityExpectation} (A statement of how confident that the recorder was that this exposure caused the reaction.)
         */
        public AdverseReactionExposureComponent setCausalityExpectation(Enumeration<CausalityExpectation> value) { 
          this.causalityExpectation = value;
          return this;
        }

        /**
         * @return A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        public CausalityExpectation getCausalityExpectationSimple() { 
          return this.causalityExpectation == null ? null : this.causalityExpectation.getValue();
        }

        /**
         * @param value A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        public AdverseReactionExposureComponent setCausalityExpectationSimple(CausalityExpectation value) { 
          if (value == null)
            this.causalityExpectation = null;
          else {
            if (this.causalityExpectation == null)
              this.causalityExpectation = new Enumeration<CausalityExpectation>();
            this.causalityExpectation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #substance} (Substance(s) that is presumed to have caused the adverse reaction.)
         */
        public ResourceReference getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Substance(s) that is presumed to have caused the adverse reaction.)
         */
        public AdverseReactionExposureComponent setSubstance(ResourceReference value) { 
          this.substance = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("exposureDate", "dateTime", "When the exposure occurred.", 0, java.lang.Integer.MAX_VALUE, exposureDate));
          childrenList.add(new Property("exposureType", "code", "The type of exposure: Drug Administration, Immunization, Coincidental.", 0, java.lang.Integer.MAX_VALUE, exposureType));
          childrenList.add(new Property("causalityExpectation", "code", "A statement of how confident that the recorder was that this exposure caused the reaction.", 0, java.lang.Integer.MAX_VALUE, causalityExpectation));
          childrenList.add(new Property("substance", "Resource(Substance)", "Substance(s) that is presumed to have caused the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, substance));
        }

      public AdverseReactionExposureComponent copy(AdverseReaction e) {
        AdverseReactionExposureComponent dst = new AdverseReactionExposureComponent();
        dst.exposureDate = exposureDate == null ? null : exposureDate.copy();
        dst.exposureType = exposureType == null ? null : exposureType.copy();
        dst.causalityExpectation = causalityExpectation == null ? null : causalityExpectation.copy();
        dst.substance = substance == null ? null : substance.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * When the reaction occurred.
     */
    protected DateTime reactionDate;

    /**
     * The subject of the adverse reaction.
     */
    protected ResourceReference subject;

    /**
     * To say that a reaction to substance did not occur.
     */
    protected Boolean didNotOccurFlag;

    /**
     * Who recorded the reaction.
     */
    protected ResourceReference recorder;

    /**
     * The signs and symptoms that were observed as part of the reaction.
     */
    protected List<AdverseReactionSymptomComponent> symptom = new ArrayList<AdverseReactionSymptomComponent>();

    /**
     * An exposure to a substance that preceded a reaction occurrence.
     */
    protected List<AdverseReactionExposureComponent> exposure = new ArrayList<AdverseReactionExposureComponent>();

    public AdverseReaction() {
      super();
    }

    public AdverseReaction(ResourceReference subject, Boolean didNotOccurFlag) {
      super();
      this.subject = subject;
      this.didNotOccurFlag = didNotOccurFlag;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #reactionDate} (When the reaction occurred.)
     */
    public DateTime getReactionDate() { 
      return this.reactionDate;
    }

    /**
     * @param value {@link #reactionDate} (When the reaction occurred.)
     */
    public AdverseReaction setReactionDate(DateTime value) { 
      this.reactionDate = value;
      return this;
    }

    /**
     * @return When the reaction occurred.
     */
    public DateAndTime getReactionDateSimple() { 
      return this.reactionDate == null ? null : this.reactionDate.getValue();
    }

    /**
     * @param value When the reaction occurred.
     */
    public AdverseReaction setReactionDateSimple(DateAndTime value) { 
      if (value == null)
        this.reactionDate = null;
      else {
        if (this.reactionDate == null)
          this.reactionDate = new DateTime();
        this.reactionDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the adverse reaction.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the adverse reaction.)
     */
    public AdverseReaction setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #didNotOccurFlag} (To say that a reaction to substance did not occur.)
     */
    public Boolean getDidNotOccurFlag() { 
      return this.didNotOccurFlag;
    }

    /**
     * @param value {@link #didNotOccurFlag} (To say that a reaction to substance did not occur.)
     */
    public AdverseReaction setDidNotOccurFlag(Boolean value) { 
      this.didNotOccurFlag = value;
      return this;
    }

    /**
     * @return To say that a reaction to substance did not occur.
     */
    public boolean getDidNotOccurFlagSimple() { 
      return this.didNotOccurFlag == null ? null : this.didNotOccurFlag.getValue();
    }

    /**
     * @param value To say that a reaction to substance did not occur.
     */
    public AdverseReaction setDidNotOccurFlagSimple(boolean value) { 
        if (this.didNotOccurFlag == null)
          this.didNotOccurFlag = new Boolean();
        this.didNotOccurFlag.setValue(value);
      return this;
    }

    /**
     * @return {@link #recorder} (Who recorded the reaction.)
     */
    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Who recorded the reaction.)
     */
    public AdverseReaction setRecorder(ResourceReference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #symptom} (The signs and symptoms that were observed as part of the reaction.)
     */
    public List<AdverseReactionSymptomComponent> getSymptom() { 
      return this.symptom;
    }

    // syntactic sugar
    /**
     * @return {@link #symptom} (The signs and symptoms that were observed as part of the reaction.)
     */
    public AdverseReactionSymptomComponent addSymptom() { 
      AdverseReactionSymptomComponent t = new AdverseReactionSymptomComponent();
      this.symptom.add(t);
      return t;
    }

    /**
     * @return {@link #exposure} (An exposure to a substance that preceded a reaction occurrence.)
     */
    public List<AdverseReactionExposureComponent> getExposure() { 
      return this.exposure;
    }

    // syntactic sugar
    /**
     * @return {@link #exposure} (An exposure to a substance that preceded a reaction occurrence.)
     */
    public AdverseReactionExposureComponent addExposure() { 
      AdverseReactionExposureComponent t = new AdverseReactionExposureComponent();
      this.exposure.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("reactionDate", "dateTime", "When the reaction occurred.", 0, java.lang.Integer.MAX_VALUE, reactionDate));
        childrenList.add(new Property("subject", "Resource(Patient)", "The subject of the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("didNotOccurFlag", "boolean", "To say that a reaction to substance did not occur.", 0, java.lang.Integer.MAX_VALUE, didNotOccurFlag));
        childrenList.add(new Property("recorder", "Resource(Practitioner|Patient)", "Who recorded the reaction.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("symptom", "", "The signs and symptoms that were observed as part of the reaction.", 0, java.lang.Integer.MAX_VALUE, symptom));
        childrenList.add(new Property("exposure", "", "An exposure to a substance that preceded a reaction occurrence.", 0, java.lang.Integer.MAX_VALUE, exposure));
      }

      public AdverseReaction copy() {
        AdverseReaction dst = new AdverseReaction();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.reactionDate = reactionDate == null ? null : reactionDate.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.didNotOccurFlag = didNotOccurFlag == null ? null : didNotOccurFlag.copy();
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.symptom = new ArrayList<AdverseReactionSymptomComponent>();
        for (AdverseReactionSymptomComponent i : symptom)
          dst.symptom.add(i.copy(dst));
        dst.exposure = new ArrayList<AdverseReactionExposureComponent>();
        for (AdverseReactionExposureComponent i : exposure)
          dst.exposure.add(i.copy(dst));
        return dst;
      }

      protected AdverseReaction typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.AdverseReaction;
   }


}

