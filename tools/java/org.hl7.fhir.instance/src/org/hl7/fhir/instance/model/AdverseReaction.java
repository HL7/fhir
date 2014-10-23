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

// Generated on Thu, Oct 23, 2014 22:01+1100 for FHIR v0.3.0

import java.util.*;

/**
 * Records an unexpected reaction suspected to be related to the exposure of the reaction subject to a substance.
 */
public class AdverseReaction extends Resource {

    public enum ReactionSeverity {
        SEVERE, // Severe complications arose due to the reaction.
        SERIOUS, // Serious inconvenience to the subject.
        MODERATE, // Moderate inconvenience to the subject.
        MINOR, // Minor inconvenience to the subject.
        NULL; // added to help the parsers
        public static ReactionSeverity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("severe".equals(codeString))
          return SEVERE;
        if ("serious".equals(codeString))
          return SERIOUS;
        if ("moderate".equals(codeString))
          return MODERATE;
        if ("minor".equals(codeString))
          return MINOR;
        throw new Exception("Unknown ReactionSeverity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case SEVERE: return "severe";
            case SERIOUS: return "serious";
            case MODERATE: return "moderate";
            case MINOR: return "minor";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case SEVERE: return "Severe complications arose due to the reaction.";
            case SERIOUS: return "Serious inconvenience to the subject.";
            case MODERATE: return "Moderate inconvenience to the subject.";
            case MINOR: return "Minor inconvenience to the subject.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case SEVERE: return "severe";
            case SERIOUS: return "serious";
            case MODERATE: return "moderate";
            case MINOR: return "minor";
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
          return ReactionSeverity.SEVERE;
        if ("serious".equals(codeString))
          return ReactionSeverity.SERIOUS;
        if ("moderate".equals(codeString))
          return ReactionSeverity.MODERATE;
        if ("minor".equals(codeString))
          return ReactionSeverity.MINOR;
        throw new Exception("Unknown ReactionSeverity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ReactionSeverity.SEVERE)
        return "severe";
      if (code == ReactionSeverity.SERIOUS)
        return "serious";
      if (code == ReactionSeverity.MODERATE)
        return "moderate";
      if (code == ReactionSeverity.MINOR)
        return "minor";
      return "?";
      }
    }

    public enum ExposureType {
        DRUGADMIN, // Drug Administration.
        IMMUNIZ, // Immunization.
        COINCIDENTAL, // In the same area as the substance.
        NULL; // added to help the parsers
        public static ExposureType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("drugadmin".equals(codeString))
          return DRUGADMIN;
        if ("immuniz".equals(codeString))
          return IMMUNIZ;
        if ("coincidental".equals(codeString))
          return COINCIDENTAL;
        throw new Exception("Unknown ExposureType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRUGADMIN: return "drugadmin";
            case IMMUNIZ: return "immuniz";
            case COINCIDENTAL: return "coincidental";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRUGADMIN: return "Drug Administration.";
            case IMMUNIZ: return "Immunization.";
            case COINCIDENTAL: return "In the same area as the substance.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRUGADMIN: return "drugadmin";
            case IMMUNIZ: return "immuniz";
            case COINCIDENTAL: return "coincidental";
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
          return ExposureType.DRUGADMIN;
        if ("immuniz".equals(codeString))
          return ExposureType.IMMUNIZ;
        if ("coincidental".equals(codeString))
          return ExposureType.COINCIDENTAL;
        throw new Exception("Unknown ExposureType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ExposureType.DRUGADMIN)
        return "drugadmin";
      if (code == ExposureType.IMMUNIZ)
        return "immuniz";
      if (code == ExposureType.COINCIDENTAL)
        return "coincidental";
      return "?";
      }
    }

    public enum CausalityExpectation {
        LIKELY, // Likely that this specific exposure caused the reaction.
        UNLIKELY, // Unlikely that this specific exposure caused the reaction - the exposure is being linked to for information purposes.
        CONFIRMED, // It has been confirmed that this exposure was one of the causes of the reaction.
        UNKNOWN, // It is unknown whether this exposure had anything to do with the reaction.
        NULL; // added to help the parsers
        public static CausalityExpectation fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("likely".equals(codeString))
          return LIKELY;
        if ("unlikely".equals(codeString))
          return UNLIKELY;
        if ("confirmed".equals(codeString))
          return CONFIRMED;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        throw new Exception("Unknown CausalityExpectation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case LIKELY: return "likely";
            case UNLIKELY: return "unlikely";
            case CONFIRMED: return "confirmed";
            case UNKNOWN: return "unknown";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case LIKELY: return "Likely that this specific exposure caused the reaction.";
            case UNLIKELY: return "Unlikely that this specific exposure caused the reaction - the exposure is being linked to for information purposes.";
            case CONFIRMED: return "It has been confirmed that this exposure was one of the causes of the reaction.";
            case UNKNOWN: return "It is unknown whether this exposure had anything to do with the reaction.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case LIKELY: return "likely";
            case UNLIKELY: return "unlikely";
            case CONFIRMED: return "confirmed";
            case UNKNOWN: return "unknown";
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
          return CausalityExpectation.LIKELY;
        if ("unlikely".equals(codeString))
          return CausalityExpectation.UNLIKELY;
        if ("confirmed".equals(codeString))
          return CausalityExpectation.CONFIRMED;
        if ("unknown".equals(codeString))
          return CausalityExpectation.UNKNOWN;
        throw new Exception("Unknown CausalityExpectation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CausalityExpectation.LIKELY)
        return "likely";
      if (code == CausalityExpectation.UNLIKELY)
        return "unlikely";
      if (code == CausalityExpectation.CONFIRMED)
        return "confirmed";
      if (code == CausalityExpectation.UNKNOWN)
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
         * @return {@link #severity} (The severity of the sign or symptom.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
         */
        public Enumeration<ReactionSeverity> getSeverityElement() { 
          return this.severity;
        }

        /**
         * @param value {@link #severity} (The severity of the sign or symptom.). This is the underlying object with id, value and extensions. The accessor "getSeverity" gives direct access to the value
         */
        public AdverseReactionSymptomComponent setSeverityElement(Enumeration<ReactionSeverity> value) { 
          this.severity = value;
          return this;
        }

        /**
         * @return The severity of the sign or symptom.
         */
        public ReactionSeverity getSeverity() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        /**
         * @param value The severity of the sign or symptom.
         */
        public AdverseReactionSymptomComponent setSeverity(ReactionSeverity value) { 
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
        protected DateTimeType date;

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
        protected Reference substance;

        /**
         * The actual object that is the target of the reference (Substance that is presumed to have caused the adverse reaction.)
         */
        protected Substance substanceTarget;

        private static final long serialVersionUID = 1654286186L;

      public AdverseReactionExposureComponent() {
        super();
      }

        /**
         * @return {@link #date} (Identifies the initial date of the exposure that is suspected to be related to the reaction.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public DateTimeType getDateElement() { 
          return this.date;
        }

        /**
         * @param value {@link #date} (Identifies the initial date of the exposure that is suspected to be related to the reaction.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
         */
        public AdverseReactionExposureComponent setDateElement(DateTimeType value) { 
          this.date = value;
          return this;
        }

        /**
         * @return Identifies the initial date of the exposure that is suspected to be related to the reaction.
         */
        public DateAndTime getDate() { 
          return this.date == null ? null : this.date.getValue();
        }

        /**
         * @param value Identifies the initial date of the exposure that is suspected to be related to the reaction.
         */
        public AdverseReactionExposureComponent setDate(DateAndTime value) { 
          if (value == null)
            this.date = null;
          else {
            if (this.date == null)
              this.date = new DateTimeType();
            this.date.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of exposure: Drug Administration, Immunization, Coincidental.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<ExposureType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of exposure: Drug Administration, Immunization, Coincidental.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public AdverseReactionExposureComponent setTypeElement(Enumeration<ExposureType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public ExposureType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of exposure: Drug Administration, Immunization, Coincidental.
         */
        public AdverseReactionExposureComponent setType(ExposureType value) { 
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
         * @return {@link #causalityExpectation} (A statement of how confident that the recorder was that this exposure caused the reaction.). This is the underlying object with id, value and extensions. The accessor "getCausalityExpectation" gives direct access to the value
         */
        public Enumeration<CausalityExpectation> getCausalityExpectationElement() { 
          return this.causalityExpectation;
        }

        /**
         * @param value {@link #causalityExpectation} (A statement of how confident that the recorder was that this exposure caused the reaction.). This is the underlying object with id, value and extensions. The accessor "getCausalityExpectation" gives direct access to the value
         */
        public AdverseReactionExposureComponent setCausalityExpectationElement(Enumeration<CausalityExpectation> value) { 
          this.causalityExpectation = value;
          return this;
        }

        /**
         * @return A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        public CausalityExpectation getCausalityExpectation() { 
          return this.causalityExpectation == null ? null : this.causalityExpectation.getValue();
        }

        /**
         * @param value A statement of how confident that the recorder was that this exposure caused the reaction.
         */
        public AdverseReactionExposureComponent setCausalityExpectation(CausalityExpectation value) { 
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
        public Reference getSubstance() { 
          return this.substance;
        }

        /**
         * @param value {@link #substance} (Substance that is presumed to have caused the adverse reaction.)
         */
        public AdverseReactionExposureComponent setSubstance(Reference value) { 
          this.substance = value;
          return this;
        }

        /**
         * @return {@link #substance} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Substance that is presumed to have caused the adverse reaction.)
         */
        public Substance getSubstanceTarget() { 
          return this.substanceTarget;
        }

        /**
         * @param value {@link #substance} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Substance that is presumed to have caused the adverse reaction.)
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
          childrenList.add(new Property("substance", "Reference(Substance)", "Substance that is presumed to have caused the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, substance));
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
    protected DateTimeType date;

    /**
     * The subject of the adverse reaction.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The subject of the adverse reaction.)
     */
    protected Patient subjectTarget;

    /**
     * If true, indicates that no reaction occurred.
     */
    protected BooleanType didNotOccurFlag;

    /**
     * Identifies the individual responsible for the information in the reaction record.
     */
    protected Reference recorder;

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

    private static final long serialVersionUID = 264165454L;

    public AdverseReaction() {
      super();
    }

    public AdverseReaction(Reference subject, BooleanType didNotOccurFlag) {
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

    /**
     * @return {@link #identifier} (This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #date} (The date (and possibly time) when the reaction began.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date (and possibly time) when the reaction began.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public AdverseReaction setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date (and possibly time) when the reaction began.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date (and possibly time) when the reaction began.
     */
    public AdverseReaction setDate(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTimeType();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (The subject of the adverse reaction.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The subject of the adverse reaction.)
     */
    public AdverseReaction setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The subject of the adverse reaction.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The subject of the adverse reaction.)
     */
    public AdverseReaction setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #didNotOccurFlag} (If true, indicates that no reaction occurred.). This is the underlying object with id, value and extensions. The accessor "getDidNotOccurFlag" gives direct access to the value
     */
    public BooleanType getDidNotOccurFlagElement() { 
      return this.didNotOccurFlag;
    }

    /**
     * @param value {@link #didNotOccurFlag} (If true, indicates that no reaction occurred.). This is the underlying object with id, value and extensions. The accessor "getDidNotOccurFlag" gives direct access to the value
     */
    public AdverseReaction setDidNotOccurFlagElement(BooleanType value) { 
      this.didNotOccurFlag = value;
      return this;
    }

    /**
     * @return If true, indicates that no reaction occurred.
     */
    public boolean getDidNotOccurFlag() { 
      return this.didNotOccurFlag == null ? false : this.didNotOccurFlag.getValue();
    }

    /**
     * @param value If true, indicates that no reaction occurred.
     */
    public AdverseReaction setDidNotOccurFlag(boolean value) { 
        if (this.didNotOccurFlag == null)
          this.didNotOccurFlag = new BooleanType();
        this.didNotOccurFlag.setValue(value);
      return this;
    }

    /**
     * @return {@link #recorder} (Identifies the individual responsible for the information in the reaction record.)
     */
    public Reference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Identifies the individual responsible for the information in the reaction record.)
     */
    public AdverseReaction setRecorder(Reference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recorder} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the individual responsible for the information in the reaction record.)
     */
    public Resource getRecorderTarget() { 
      return this.recorderTarget;
    }

    /**
     * @param value {@link #recorder} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the individual responsible for the information in the reaction record.)
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

    /**
     * @return {@link #symptom} (The signs and symptoms that were observed as part of the reaction.)
     */
    // syntactic sugar
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

    /**
     * @return {@link #exposure} (An exposure to a substance that preceded a reaction occurrence.)
     */
    // syntactic sugar
    public AdverseReactionExposureComponent addExposure() { 
      AdverseReactionExposureComponent t = new AdverseReactionExposureComponent();
      this.exposure.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this reaction that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("date", "dateTime", "The date (and possibly time) when the reaction began.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("subject", "Reference(Patient)", "The subject of the adverse reaction.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("didNotOccurFlag", "boolean", "If true, indicates that no reaction occurred.", 0, java.lang.Integer.MAX_VALUE, didNotOccurFlag));
        childrenList.add(new Property("recorder", "Reference(Practitioner|Patient)", "Identifies the individual responsible for the information in the reaction record.", 0, java.lang.Integer.MAX_VALUE, recorder));
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

