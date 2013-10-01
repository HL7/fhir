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

// Generated on Tue, Oct 1, 2013 21:45+1000 for FHIR v0.11

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

  public class ReactionSeverityEnumFactory implements EnumFactory {
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

  public class ExposureTypeEnumFactory implements EnumFactory {
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

  public class CausalityExpectationEnumFactory implements EnumFactory {
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

    public class AdverseReactionSymptomComponent extends Element {
        /**
         * Indicates the specific sign or symptom that was observed.
         */
        protected CodeableConcept code;

        /**
         * The severity of the sign or symptom.
         */
        protected Enumeration<ReactionSeverity> severity;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public Enumeration<ReactionSeverity> getSeverity() { 
          return this.severity;
        }

        public void setSeverity(Enumeration<ReactionSeverity> value) { 
          this.severity = value;
        }

        public ReactionSeverity getSeveritySimple() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        public void setSeveritySimple(ReactionSeverity value) { 
          if (value == null)
            this.severity = null;
          else {
            if (this.severity == null)
              this.severity = new Enumeration<ReactionSeverity>();
            this.severity.setValue(value);
          }
        }

      public AdverseReactionSymptomComponent copy(AdverseReaction e) {
        AdverseReactionSymptomComponent dst = e.new AdverseReactionSymptomComponent();
        dst.code = code == null ? null : code.copy();
        dst.severity = severity == null ? null : severity.copy();
        return dst;
      }

  }

    public class AdverseReactionExposureComponent extends Element {
        /**
         * When the exposure occurred.
         */
        protected DateTime exposureDate;

        /**
         * Drug Administration, Immunization, Coincidental.
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

        public DateTime getExposureDate() { 
          return this.exposureDate;
        }

        public void setExposureDate(DateTime value) { 
          this.exposureDate = value;
        }

        public String getExposureDateSimple() { 
          return this.exposureDate == null ? null : this.exposureDate.getValue();
        }

        public void setExposureDateSimple(String value) { 
          if (value == null)
            this.exposureDate = null;
          else {
            if (this.exposureDate == null)
              this.exposureDate = new DateTime();
            this.exposureDate.setValue(value);
          }
        }

        public Enumeration<ExposureType> getExposureType() { 
          return this.exposureType;
        }

        public void setExposureType(Enumeration<ExposureType> value) { 
          this.exposureType = value;
        }

        public ExposureType getExposureTypeSimple() { 
          return this.exposureType == null ? null : this.exposureType.getValue();
        }

        public void setExposureTypeSimple(ExposureType value) { 
          if (value == null)
            this.exposureType = null;
          else {
            if (this.exposureType == null)
              this.exposureType = new Enumeration<ExposureType>();
            this.exposureType.setValue(value);
          }
        }

        public Enumeration<CausalityExpectation> getCausalityExpectation() { 
          return this.causalityExpectation;
        }

        public void setCausalityExpectation(Enumeration<CausalityExpectation> value) { 
          this.causalityExpectation = value;
        }

        public CausalityExpectation getCausalityExpectationSimple() { 
          return this.causalityExpectation == null ? null : this.causalityExpectation.getValue();
        }

        public void setCausalityExpectationSimple(CausalityExpectation value) { 
          if (value == null)
            this.causalityExpectation = null;
          else {
            if (this.causalityExpectation == null)
              this.causalityExpectation = new Enumeration<CausalityExpectation>();
            this.causalityExpectation.setValue(value);
          }
        }

        public ResourceReference getSubstance() { 
          return this.substance;
        }

        public void setSubstance(ResourceReference value) { 
          this.substance = value;
        }

      public AdverseReactionExposureComponent copy(AdverseReaction e) {
        AdverseReactionExposureComponent dst = e.new AdverseReactionExposureComponent();
        dst.exposureDate = exposureDate == null ? null : exposureDate.copy();
        dst.exposureType = exposureType == null ? null : exposureType.copy();
        dst.causalityExpectation = causalityExpectation == null ? null : causalityExpectation.copy();
        dst.substance = substance == null ? null : substance.copy();
        return dst;
      }

  }

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

    public DateTime getReactionDate() { 
      return this.reactionDate;
    }

    public void setReactionDate(DateTime value) { 
      this.reactionDate = value;
    }

    public String getReactionDateSimple() { 
      return this.reactionDate == null ? null : this.reactionDate.getValue();
    }

    public void setReactionDateSimple(String value) { 
      if (value == null)
        this.reactionDate = null;
      else {
        if (this.reactionDate == null)
          this.reactionDate = new DateTime();
        this.reactionDate.setValue(value);
      }
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public Boolean getDidNotOccurFlag() { 
      return this.didNotOccurFlag;
    }

    public void setDidNotOccurFlag(Boolean value) { 
      this.didNotOccurFlag = value;
    }

    public boolean getDidNotOccurFlagSimple() { 
      return this.didNotOccurFlag == null ? null : this.didNotOccurFlag.getValue();
    }

    public void setDidNotOccurFlagSimple(boolean value) { 
        if (this.didNotOccurFlag == null)
          this.didNotOccurFlag = new Boolean();
        this.didNotOccurFlag.setValue(value);
    }

    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    public void setRecorder(ResourceReference value) { 
      this.recorder = value;
    }

    public List<AdverseReactionSymptomComponent> getSymptom() { 
      return this.symptom;
    }

    // syntactic sugar
    public AdverseReactionSymptomComponent addSymptom() { 
      AdverseReactionSymptomComponent t = new AdverseReactionSymptomComponent();
      this.symptom.add(t);
      return t;
    }

    public List<AdverseReactionExposureComponent> getExposure() { 
      return this.exposure;
    }

    // syntactic sugar
    public AdverseReactionExposureComponent addExposure() { 
      AdverseReactionExposureComponent t = new AdverseReactionExposureComponent();
      this.exposure.add(t);
      return t;
    }

      public AdverseReaction copy() {
        AdverseReaction dst = new AdverseReaction();
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

