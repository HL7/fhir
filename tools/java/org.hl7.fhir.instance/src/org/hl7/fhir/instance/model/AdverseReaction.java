package org.hl7.fhir.instance.model;

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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * Records an unexpected reaction suspected to be related to the exposure of the reaction subject to a substance.
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

        private static final long serialVersionUID = -1856198542L;

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

      public AdverseReactionSymptomComponent copy() {
        AdverseReactionSymptomComponent dst = new AdverseReactionSymptomComponent();
        dst.code = code == null ? null : code.copy();
        dst.severity = severity == null ? null : severity.copy();
        return dst;
      }

  }

    public static class AdverseReactionExposureComponent extends BackboneElement {
        /**
         * Identifies the initial date of the exposure that is suspected to be related to the reaction.
         */
        protected DateTime date;

        /**
         * The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        protected Enumeration<ExposureType> type;

        /**
         * A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        protected Enumeration<CausalityExpectation> causalityExpectation;

        /**
         * Substance that is presumed to have caused the adverse reaction.
         */
        protected ResourceReference substance;

        /**
         * The actual object that is the target of the reference (Substance that is presumed to have caused the adverse reaction.)
         */
        protected Substance substanceTarget;

        private static final long serialVersionUID = 520803518L;

      public AdverseReactionExposureComponent() {
        super();
      }

        /**
         * @return {@link #date} (Identifies the initial date of the exposure that is suspected to be related to the reaction.)
         */
        public DateTime getDate() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (Identifies the initial date of the exposure that is suspected to be related to the reaction.)
         */
        public AdverseReactionExposureComponent setDate(DateTime value) { 
          this.date = value;
          return this;
        }

        /**
         * @return Identifies the initial date of the exposure that is suspected to be related to the reaction.
         */
        public DateAndTime getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value Identifies the initial date of the exposure that is suspected to be related to the reaction.
         */
        public AdverseReactionExposureComponent setDateSimple(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of exposure: Drug Administration, Immunization, Coincidental.)
         */
        public Enumeration<ExposureType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of exposure: Drug Administration, Immunization, Coincidental.)
         */
        public AdverseReactionExposureComponent setType(Enumeration<ExposureType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public ExposureType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public AdverseReactionExposureComponent setTypeSimple(ExposureType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ExposureType>();
            this.type.setValue(value);
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
         * @return {@link #substance} (Substance that is presumed to have caused the adverse reaction.)
         */
        public ResourceReference getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Substance that is presumed to have caused the adverse reaction.)
         */
        public AdverseReactionExposureComponent setSubstance(ResourceReference value) { 
          this.substance = value;
          return this;
        }

        /**
         * @return {@link #substance} (The actual object that is the target of the reference. Substance that is presumed to have caused the adverse reaction.)
         */
        public Substance getSubstanceTarget() { 
          return this.substanceTarget;
        }

        /**
         * @param value {@link #substance} (The actual object that is the target of the reference. Substance that is presumed to have caused the adverse reaction.)
         */
        public AdverseReactionExposureComponent setSubstanceTarget(Substance value) { 
          this.substanceTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("date", "dateTime", "Identifies the initial date of the exposure that is suspected to be related to the reaction.", 0, java.lang.Integer.MAX_VALUE, date));
          childrenList.add(new Property("type", "code", "The type of exposure: Drug Administration, Immunization, Coincidental.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("causalityExpectation", "code", "A statement of how confident that the recorder was that this exposure caused the reaction.", 0, java.lang.Integer.MAX_VALUE, causalityExpectation));
          childrenList.add(new Property("substance", "Resource(Substance)", "Substance that is presumed to have caused the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, substance));
        }

      public AdverseReactionExposureComponent copy() {
        AdverseReactionExposureComponent dst = new AdverseReactionExposureComponent();
        dst.date = date == null ? null : date.copy();
        dst.type = type == null ? null : type.copy();
        dst.causalityExpectation = causalityExpectation == null ? null : causalityExpectation.copy();
        dst.substance = substance == null ? null : substance.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The date (and possibly time) when the reaction began.
     */
    protected DateTime date;

    /**
     * The subject of the adverse reaction.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The subject of the adverse reaction.)
     */
    protected Patient subjectTarget;

    /**
     * If true, indicates that no reaction occurred.
     */
    protected Boolean didNotOccurFlag;

    /**
     * Identifies the individual responsible for the information in the reaction record.
     */
    protected ResourceReference recorder;

    /**
     * The actual object that is the target of the reference (Identifies the individual responsible for the information in the reaction record.)
     */
    protected Resource recorderTarget;

    /**
     * The signs and symptoms that were observed as part of the reaction.
     */
    protected List<AdverseReactionSymptomComponent> symptom = new ArrayList<AdverseReactionSymptomComponent>();

    /**
     * An exposure to a substance that preceded a reaction occurrence.
     */
    protected List<AdverseReactionExposureComponent> exposure = new ArrayList<AdverseReactionExposureComponent>();

    private static final long serialVersionUID = -1004725142L;

    public AdverseReaction() {
      super();
    }

    public AdverseReaction(ResourceReference subject, Boolean didNotOccurFlag) {
      super();
      this.subject = subject;
      this.didNotOccurFlag = didNotOccurFlag;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #date} (The date (and possibly time) when the reaction began.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date (and possibly time) when the reaction began.)
     */
    public AdverseReaction setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date (and possibly time) when the reaction began.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date (and possibly time) when the reaction began.
     */
    public AdverseReaction setDateSimple(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
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
     * @return {@link #subject} (The actual object that is the target of the reference. The subject of the adverse reaction.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The subject of the adverse reaction.)
     */
    public AdverseReaction setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #didNotOccurFlag} (If true, indicates that no reaction occurred.)
     */
    public Boolean getDidNotOccurFlag() { 
      return this.didNotOccurFlag;
    }

    /**
     * @param value {@link #didNotOccurFlag} (If true, indicates that no reaction occurred.)
     */
    public AdverseReaction setDidNotOccurFlag(Boolean value) { 
      this.didNotOccurFlag = value;
      return this;
    }

    /**
     * @return If true, indicates that no reaction occurred.
     */
    public boolean getDidNotOccurFlagSimple() { 
      return this.didNotOccurFlag == null ? false : this.didNotOccurFlag.getValue();
    }

    /**
     * @param value If true, indicates that no reaction occurred.
     */
    public AdverseReaction setDidNotOccurFlagSimple(boolean value) { 
        if (this.didNotOccurFlag == null)
          this.didNotOccurFlag = new Boolean();
        this.didNotOccurFlag.setValue(value);
      return this;
    }

    /**
     * @return {@link #recorder} (Identifies the individual responsible for the information in the reaction record.)
     */
    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Identifies the individual responsible for the information in the reaction record.)
     */
    public AdverseReaction setRecorder(ResourceReference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recorder} (The actual object that is the target of the reference. Identifies the individual responsible for the information in the reaction record.)
     */
    public Resource getRecorderTarget() { 
      return this.recorderTarget;
    }

    /**
     * @param value {@link #recorder} (The actual object that is the target of the reference. Identifies the individual responsible for the information in the reaction record.)
     */
    public AdverseReaction setRecorderTarget(Resource value) { 
      this.recorderTarget = value;
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
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("date", "dateTime", "The date (and possibly time) when the reaction began.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("subject", "Resource(Patient)", "The subject of the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("didNotOccurFlag", "boolean", "If true, indicates that no reaction occurred.", 0, java.lang.Integer.MAX_VALUE, didNotOccurFlag));
        childrenList.add(new Property("recorder", "Resource(Practitioner|Patient)", "Identifies the individual responsible for the information in the reaction record.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("symptom", "", "The signs and symptoms that were observed as part of the reaction.", 0, java.lang.Integer.MAX_VALUE, symptom));
        childrenList.add(new Property("exposure", "", "An exposure to a substance that preceded a reaction occurrence.", 0, java.lang.Integer.MAX_VALUE, exposure));
      }

      public AdverseReaction copy() {
        AdverseReaction dst = new AdverseReaction();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.date = date == null ? null : date.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.didNotOccurFlag = didNotOccurFlag == null ? null : didNotOccurFlag.copy();
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.symptom = new ArrayList<AdverseReactionSymptomComponent>();
        for (AdverseReactionSymptomComponent i : symptom)
          dst.symptom.add(i.copy());
        dst.exposure = new ArrayList<AdverseReactionExposureComponent>();
        for (AdverseReactionExposureComponent i : exposure)
          dst.exposure.add(i.copy());
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

