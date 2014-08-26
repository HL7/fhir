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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

import java.math.*;
/**
 * An assessment of the likely outcome(s) for a patient or other subject as well as the likelihood of each outcome.
 */
public class RiskAssessment extends Resource {

    public static class RiskAssessmentPredictionComponent extends BackboneElement {
        /**
         * One of the potential outcomes for the patient (e.g. remission, death,  a particular condition).
         */
        protected CodeableConcept outcome;

        /**
         * How likely is the outcome (in the specified timeframe).
         */
        protected Type probability;

        /**
         * Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).
         */
        protected DecimalType relativeRisk;

        /**
         * Indicates the period of time or age range of the subject to which the specified probability applies.
         */
        protected Type when;

        /**
         * Additional information explaining the basis for the prediction.
         */
        protected StringType rationale;

        private static final long serialVersionUID = 647967428L;

      public RiskAssessmentPredictionComponent() {
        super();
      }

      public RiskAssessmentPredictionComponent(CodeableConcept outcome) {
        super();
        this.outcome = outcome;
      }

        /**
         * @return {@link #outcome} (One of the potential outcomes for the patient (e.g. remission, death,  a particular condition).)
         */
        public CodeableConcept getOutcome() { 
          return this.outcome;
        }

        /**
         * @param value {@link #outcome} (One of the potential outcomes for the patient (e.g. remission, death,  a particular condition).)
         */
        public RiskAssessmentPredictionComponent setOutcome(CodeableConcept value) { 
          this.outcome = value;
          return this;
        }

        /**
         * @return {@link #probability} (How likely is the outcome (in the specified timeframe).)
         */
        public Type getProbability() { 
          return this.probability;
        }

        /**
         * @param value {@link #probability} (How likely is the outcome (in the specified timeframe).)
         */
        public RiskAssessmentPredictionComponent setProbability(Type value) { 
          this.probability = value;
          return this;
        }

        /**
         * @return {@link #relativeRisk} (Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).)
         */
        public DecimalType getRelativeRisk() { 
          return this.relativeRisk;
        }

        /**
         * @param value {@link #relativeRisk} (Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).)
         */
        public RiskAssessmentPredictionComponent setRelativeRisk(DecimalType value) { 
          this.relativeRisk = value;
          return this;
        }

        /**
         * @return Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).
         */
        public BigDecimal getRelativeRiskSimple() { 
          return this.relativeRisk == null ? null : this.relativeRisk.getValue();
        }

        /**
         * @param value Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).
         */
        public RiskAssessmentPredictionComponent setRelativeRiskSimple(BigDecimal value) { 
          if (value == null)
            this.relativeRisk = null;
          else {
            if (this.relativeRisk == null)
              this.relativeRisk = new DecimalType();
            this.relativeRisk.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #when} (Indicates the period of time or age range of the subject to which the specified probability applies.)
         */
        public Type getWhen() { 
          return this.when;
        }

        /**
         * @param value {@link #when} (Indicates the period of time or age range of the subject to which the specified probability applies.)
         */
        public RiskAssessmentPredictionComponent setWhen(Type value) { 
          this.when = value;
          return this;
        }

        /**
         * @return {@link #rationale} (Additional information explaining the basis for the prediction.)
         */
        public StringType getRationale() { 
          return this.rationale;
        }

        /**
         * @param value {@link #rationale} (Additional information explaining the basis for the prediction.)
         */
        public RiskAssessmentPredictionComponent setRationale(StringType value) { 
          this.rationale = value;
          return this;
        }

        /**
         * @return Additional information explaining the basis for the prediction.
         */
        public String getRationaleSimple() { 
          return this.rationale == null ? null : this.rationale.getValue();
        }

        /**
         * @param value Additional information explaining the basis for the prediction.
         */
        public RiskAssessmentPredictionComponent setRationaleSimple(String value) { 
          if (value == null)
            this.rationale = null;
          else {
            if (this.rationale == null)
              this.rationale = new StringType();
            this.rationale.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("outcome", "CodeableConcept", "One of the potential outcomes for the patient (e.g. remission, death,  a particular condition).", 0, java.lang.Integer.MAX_VALUE, outcome));
          childrenList.add(new Property("probability[x]", "decimal|Range|CodeableConcept", "How likely is the outcome (in the specified timeframe).", 0, java.lang.Integer.MAX_VALUE, probability));
          childrenList.add(new Property("relativeRisk", "decimal", "Indicates the risk for this particular subject (with their specific characteristics) divided by the risk of the population in general.  (Numbers greater than 1 = higher risk than the population, numbers less than 1 = lower risk.).", 0, java.lang.Integer.MAX_VALUE, relativeRisk));
          childrenList.add(new Property("when[x]", "Period|Range", "Indicates the period of time or age range of the subject to which the specified probability applies.", 0, java.lang.Integer.MAX_VALUE, when));
          childrenList.add(new Property("rationale", "string", "Additional information explaining the basis for the prediction.", 0, java.lang.Integer.MAX_VALUE, rationale));
        }

      public RiskAssessmentPredictionComponent copy() {
        RiskAssessmentPredictionComponent dst = new RiskAssessmentPredictionComponent();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.probability = probability == null ? null : probability.copy();
        dst.relativeRisk = relativeRisk == null ? null : relativeRisk.copy();
        dst.when = when == null ? null : when.copy();
        dst.rationale = rationale == null ? null : rationale.copy();
        return dst;
      }

  }

    /**
     * The patient or group the risk assessment applies to.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The patient or group the risk assessment applies to.)
     */
    protected Resource subjectTarget;

    /**
     * The date (and possibly time) the risk assessment was performed.
     */
    protected DateTimeType date;

    /**
     * For assessments or prognosis specific to a particular condition, indicates the condition being assessed.
     */
    protected ResourceReference condition;

    /**
     * The actual object that is the target of the reference (For assessments or prognosis specific to a particular condition, indicates the condition being assessed.)
     */
    protected Condition conditionTarget;

    /**
     * The provider or software application that performed the assessment.
     */
    protected ResourceReference performer;

    /**
     * The actual object that is the target of the reference (The provider or software application that performed the assessment.)
     */
    protected Resource performerTarget;

    /**
     * Business identifier assigned to the risk assessment.
     */
    protected Identifier identifier;

    /**
     * The algorithm, processs or mechanism used to evaluate the risk.
     */
    protected CodeableConcept method;

    /**
     * Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).
     */
    protected List<ResourceReference> basis = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).)
     */
    protected List<Resource> basisTarget = new ArrayList<Resource>();


    /**
     * Describes the expected outcome for the subject.
     */
    protected List<RiskAssessmentPredictionComponent> prediction = new ArrayList<RiskAssessmentPredictionComponent>();

    /**
     * A description of the steps that might be taken to reduce the identified risk(s).
     */
    protected StringType mitigation;

    private static final long serialVersionUID = -1398763765L;

    public RiskAssessment() {
      super();
    }

    /**
     * @return {@link #subject} (The patient or group the risk assessment applies to.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient or group the risk assessment applies to.)
     */
    public RiskAssessment setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The patient or group the risk assessment applies to.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The patient or group the risk assessment applies to.)
     */
    public RiskAssessment setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #date} (The date (and possibly time) the risk assessment was performed.)
     */
    public DateTimeType getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date (and possibly time) the risk assessment was performed.)
     */
    public RiskAssessment setDate(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date (and possibly time) the risk assessment was performed.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date (and possibly time) the risk assessment was performed.
     */
    public RiskAssessment setDateSimple(DateAndTime value) { 
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
     * @return {@link #condition} (For assessments or prognosis specific to a particular condition, indicates the condition being assessed.)
     */
    public ResourceReference getCondition() { 
      return this.condition;
    }

    /**
     * @param value {@link #condition} (For assessments or prognosis specific to a particular condition, indicates the condition being assessed.)
     */
    public RiskAssessment setCondition(ResourceReference value) { 
      this.condition = value;
      return this;
    }

    /**
     * @return {@link #condition} (The actual object that is the target of the reference. For assessments or prognosis specific to a particular condition, indicates the condition being assessed.)
     */
    public Condition getConditionTarget() { 
      return this.conditionTarget;
    }

    /**
     * @param value {@link #condition} (The actual object that is the target of the reference. For assessments or prognosis specific to a particular condition, indicates the condition being assessed.)
     */
    public RiskAssessment setConditionTarget(Condition value) { 
      this.conditionTarget = value;
      return this;
    }

    /**
     * @return {@link #performer} (The provider or software application that performed the assessment.)
     */
    public ResourceReference getPerformer() { 
      return this.performer;
    }

    /**
     * @param value {@link #performer} (The provider or software application that performed the assessment.)
     */
    public RiskAssessment setPerformer(ResourceReference value) { 
      this.performer = value;
      return this;
    }

    /**
     * @return {@link #performer} (The actual object that is the target of the reference. The provider or software application that performed the assessment.)
     */
    public Resource getPerformerTarget() { 
      return this.performerTarget;
    }

    /**
     * @param value {@link #performer} (The actual object that is the target of the reference. The provider or software application that performed the assessment.)
     */
    public RiskAssessment setPerformerTarget(Resource value) { 
      this.performerTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Business identifier assigned to the risk assessment.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Business identifier assigned to the risk assessment.)
     */
    public RiskAssessment setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #method} (The algorithm, processs or mechanism used to evaluate the risk.)
     */
    public CodeableConcept getMethod() { 
      return this.method;
    }

    /**
     * @param value {@link #method} (The algorithm, processs or mechanism used to evaluate the risk.)
     */
    public RiskAssessment setMethod(CodeableConcept value) { 
      this.method = value;
      return this;
    }

    /**
     * @return {@link #basis} (Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).)
     */
    public List<ResourceReference> getBasis() { 
      return this.basis;
    }

    // syntactic sugar
    /**
     * @return {@link #basis} (Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).)
     */
    public ResourceReference addBasis() { 
      ResourceReference t = new ResourceReference();
      this.basis.add(t);
      return t;
    }

    /**
     * @return {@link #basis} (The actual objects that are the target of the reference. Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).)
     */
    public List<Resource> getBasisTarget() { 
      return this.basisTarget;
    }

    /**
     * @return {@link #prediction} (Describes the expected outcome for the subject.)
     */
    public List<RiskAssessmentPredictionComponent> getPrediction() { 
      return this.prediction;
    }

    // syntactic sugar
    /**
     * @return {@link #prediction} (Describes the expected outcome for the subject.)
     */
    public RiskAssessmentPredictionComponent addPrediction() { 
      RiskAssessmentPredictionComponent t = new RiskAssessmentPredictionComponent();
      this.prediction.add(t);
      return t;
    }

    /**
     * @return {@link #mitigation} (A description of the steps that might be taken to reduce the identified risk(s).)
     */
    public StringType getMitigation() { 
      return this.mitigation;
    }

    /**
     * @param value {@link #mitigation} (A description of the steps that might be taken to reduce the identified risk(s).)
     */
    public RiskAssessment setMitigation(StringType value) { 
      this.mitigation = value;
      return this;
    }

    /**
     * @return A description of the steps that might be taken to reduce the identified risk(s).
     */
    public String getMitigationSimple() { 
      return this.mitigation == null ? null : this.mitigation.getValue();
    }

    /**
     * @param value A description of the steps that might be taken to reduce the identified risk(s).
     */
    public RiskAssessment setMitigationSimple(String value) { 
      if (value == null)
        this.mitigation = null;
      else {
        if (this.mitigation == null)
          this.mitigation = new StringType();
        this.mitigation.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("subject", "Resource(Patient|Group)", "The patient or group the risk assessment applies to.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("date", "dateTime", "The date (and possibly time) the risk assessment was performed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("condition", "Resource(Condition)", "For assessments or prognosis specific to a particular condition, indicates the condition being assessed.", 0, java.lang.Integer.MAX_VALUE, condition));
        childrenList.add(new Property("performer", "Resource(Practitioner|Device)", "The provider or software application that performed the assessment.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("identifier", "Identifier", "Business identifier assigned to the risk assessment.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("method", "CodeableConcept", "The algorithm, processs or mechanism used to evaluate the risk.", 0, java.lang.Integer.MAX_VALUE, method));
        childrenList.add(new Property("basis", "Resource(Any)", "Indicates the source data considered as part of the assessment (FamilyHistory, Observations, Procedures, Conditions, etc.).", 0, java.lang.Integer.MAX_VALUE, basis));
        childrenList.add(new Property("prediction", "", "Describes the expected outcome for the subject.", 0, java.lang.Integer.MAX_VALUE, prediction));
        childrenList.add(new Property("mitigation", "string", "A description of the steps that might be taken to reduce the identified risk(s).", 0, java.lang.Integer.MAX_VALUE, mitigation));
      }

      public RiskAssessment copy() {
        RiskAssessment dst = new RiskAssessment();
        dst.subject = subject == null ? null : subject.copy();
        dst.date = date == null ? null : date.copy();
        dst.condition = condition == null ? null : condition.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.method = method == null ? null : method.copy();
        dst.basis = new ArrayList<ResourceReference>();
        for (ResourceReference i : basis)
          dst.basis.add(i.copy());
        dst.prediction = new ArrayList<RiskAssessmentPredictionComponent>();
        for (RiskAssessmentPredictionComponent i : prediction)
          dst.prediction.add(i.copy());
        dst.mitigation = mitigation == null ? null : mitigation.copy();
        return dst;
      }

      protected RiskAssessment typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.RiskAssessment;
   }


}

