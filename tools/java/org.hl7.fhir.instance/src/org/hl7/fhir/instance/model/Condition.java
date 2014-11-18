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
 * Use to record detailed information about conditions, problems or diagnoses recognized by a clinician. There are many uses including: recording a Diagnosis during an Encounter; populating a problem List or a Summary Statement, such as a Discharge Summary.
 */
public class Condition extends DomainResource {

    public enum ConditionStatus {
        PROVISIONAL, // This is a tentative diagnosis - still a candidate that is under consideration.
        WORKING, // The patient is being treated on the basis that this is the condition, but it is still not confirmed.
        CONFIRMED, // There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition.
        REFUTED, // This condition has been ruled out by diagnostic and clinical evidence.
        NULL; // added to help the parsers
        public static ConditionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("provisional".equals(codeString))
          return PROVISIONAL;
        if ("working".equals(codeString))
          return WORKING;
        if ("confirmed".equals(codeString))
          return CONFIRMED;
        if ("refuted".equals(codeString))
          return REFUTED;
        throw new Exception("Unknown ConditionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PROVISIONAL: return "provisional";
            case WORKING: return "working";
            case CONFIRMED: return "confirmed";
            case REFUTED: return "refuted";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PROVISIONAL: return "This is a tentative diagnosis - still a candidate that is under consideration.";
            case WORKING: return "The patient is being treated on the basis that this is the condition, but it is still not confirmed.";
            case CONFIRMED: return "There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition.";
            case REFUTED: return "This condition has been ruled out by diagnostic and clinical evidence.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PROVISIONAL: return "provisional";
            case WORKING: return "working";
            case CONFIRMED: return "confirmed";
            case REFUTED: return "refuted";
            default: return "?";
          }
        }
    }

  public static class ConditionStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("provisional".equals(codeString))
          return ConditionStatus.PROVISIONAL;
        if ("working".equals(codeString))
          return ConditionStatus.WORKING;
        if ("confirmed".equals(codeString))
          return ConditionStatus.CONFIRMED;
        if ("refuted".equals(codeString))
          return ConditionStatus.REFUTED;
        throw new Exception("Unknown ConditionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConditionStatus.PROVISIONAL)
        return "provisional";
      if (code == ConditionStatus.WORKING)
        return "working";
      if (code == ConditionStatus.CONFIRMED)
        return "confirmed";
      if (code == ConditionStatus.REFUTED)
        return "refuted";
      return "?";
      }
    }

    public static class ConditionStageComponent extends BackboneElement {
        /**
         * A simple summary of the stage such as "Stage 3". The determination of the stage is disease-specific.
         */
        protected CodeableConcept summary;

        /**
         * Reference to a formal record of the evidence on which the staging assessment is based.
         */
        protected List<Reference> assessment = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        protected List<Resource> assessmentTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = 652796354L;

      public ConditionStageComponent() {
        super();
      }

        /**
         * @return {@link #summary} (A simple summary of the stage such as "Stage 3". The determination of the stage is disease-specific.)
         */
        public CodeableConcept getSummary() { 
          return this.summary;
        }

        /**
         * @param value {@link #summary} (A simple summary of the stage such as "Stage 3". The determination of the stage is disease-specific.)
         */
        public ConditionStageComponent setSummary(CodeableConcept value) { 
          this.summary = value;
          return this;
        }

        /**
         * @return {@link #assessment} (Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        public List<Reference> getAssessment() { 
          return this.assessment;
        }

        /**
         * @return {@link #assessment} (Reference to a formal record of the evidence on which the staging assessment is based.)
         */
    // syntactic sugar
        public Reference addAssessment() { //3
          Reference t = new Reference();
          this.assessment.add(t);
          return t;
        }

        /**
         * @return {@link #assessment} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        public List<Resource> getAssessmentTarget() { 
          return this.assessmentTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("summary", "CodeableConcept", "A simple summary of the stage such as 'Stage 3'. The determination of the stage is disease-specific.", 0, java.lang.Integer.MAX_VALUE, summary));
          childrenList.add(new Property("assessment", "Reference(Any)", "Reference to a formal record of the evidence on which the staging assessment is based.", 0, java.lang.Integer.MAX_VALUE, assessment));
        }

      public ConditionStageComponent copy() {
        ConditionStageComponent dst = new ConditionStageComponent();
        copyValues(dst);
        dst.summary = summary == null ? null : summary.copy();
        dst.assessment = new ArrayList<Reference>();
        for (Reference i : assessment)
          dst.assessment.add(i.copy());
        return dst;
      }

  }

    public static class ConditionEvidenceComponent extends BackboneElement {
        /**
         * A manifestation or symptom that led to the recording of this condition.
         */
        protected CodeableConcept code;

        /**
         * Links to other relevant information, including pathology reports.
         */
        protected List<Reference> detail = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (Links to other relevant information, including pathology reports.)
         */
        protected List<Resource> detailTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = -1404486983L;

      public ConditionEvidenceComponent() {
        super();
      }

        /**
         * @return {@link #code} (A manifestation or symptom that led to the recording of this condition.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A manifestation or symptom that led to the recording of this condition.)
         */
        public ConditionEvidenceComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #detail} (Links to other relevant information, including pathology reports.)
         */
        public List<Reference> getDetail() { 
          return this.detail;
        }

        /**
         * @return {@link #detail} (Links to other relevant information, including pathology reports.)
         */
    // syntactic sugar
        public Reference addDetail() { //3
          Reference t = new Reference();
          this.detail.add(t);
          return t;
        }

        /**
         * @return {@link #detail} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Links to other relevant information, including pathology reports.)
         */
        public List<Resource> getDetailTarget() { 
          return this.detailTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A manifestation or symptom that led to the recording of this condition.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("detail", "Reference(Any)", "Links to other relevant information, including pathology reports.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public ConditionEvidenceComponent copy() {
        ConditionEvidenceComponent dst = new ConditionEvidenceComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.detail = new ArrayList<Reference>();
        for (Reference i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

  }

    public static class ConditionLocationComponent extends BackboneElement {
        /**
         * Code that identifies the structural location.
         */
        protected CodeableConcept code;

        /**
         * Detailed anatomical location information.
         */
        protected StringType detail;

        private static final long serialVersionUID = -406205954L;

      public ConditionLocationComponent() {
        super();
      }

        /**
         * @return {@link #code} (Code that identifies the structural location.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies the structural location.)
         */
        public ConditionLocationComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #detail} (Detailed anatomical location information.). This is the underlying object with id, value and extensions. The accessor "getDetail" gives direct access to the value
         */
        public StringType getDetailElement() { 
          return this.detail;
        }

        /**
         * @param value {@link #detail} (Detailed anatomical location information.). This is the underlying object with id, value and extensions. The accessor "getDetail" gives direct access to the value
         */
        public ConditionLocationComponent setDetailElement(StringType value) { 
          this.detail = value;
          return this;
        }

        /**
         * @return Detailed anatomical location information.
         */
        public String getDetail() { 
          return this.detail == null ? null : this.detail.getValue();
        }

        /**
         * @param value Detailed anatomical location information.
         */
        public ConditionLocationComponent setDetail(String value) { 
          if (Utilities.noString(value))
            this.detail = null;
          else {
            if (this.detail == null)
              this.detail = new StringType();
            this.detail.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Code that identifies the structural location.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("detail", "string", "Detailed anatomical location information.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public ConditionLocationComponent copy() {
        ConditionLocationComponent dst = new ConditionLocationComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.detail = detail == null ? null : detail.copy();
        return dst;
      }

  }

    public static class ConditionDueToComponent extends BackboneElement {
        /**
         * Code that identifies the target of this relationship. The code takes the place of a detailed instance target.
         */
        protected CodeableConcept codeableConcept;

        /**
         * Target of the relationship.
         */
        protected Reference target;

        /**
         * The actual object that is the target of the reference (Target of the relationship.)
         */
        protected Resource targetTarget;

        private static final long serialVersionUID = -864422450L;

      public ConditionDueToComponent() {
        super();
      }

        /**
         * @return {@link #codeableConcept} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public CodeableConcept getCodeableConcept() { 
          return this.codeableConcept;
        }

        /**
         * @param value {@link #codeableConcept} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public ConditionDueToComponent setCodeableConcept(CodeableConcept value) { 
          this.codeableConcept = value;
          return this;
        }

        /**
         * @return {@link #target} (Target of the relationship.)
         */
        public Reference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (Target of the relationship.)
         */
        public ConditionDueToComponent setTarget(Reference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Target of the relationship.)
         */
        public Resource getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Target of the relationship.)
         */
        public ConditionDueToComponent setTargetTarget(Resource value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeableConcept", "CodeableConcept", "Code that identifies the target of this relationship. The code takes the place of a detailed instance target.", 0, java.lang.Integer.MAX_VALUE, codeableConcept));
          childrenList.add(new Property("target", "Reference(Condition|Procedure|MedicationAdministration|Immunization|MedicationStatement)", "Target of the relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ConditionDueToComponent copy() {
        ConditionDueToComponent dst = new ConditionDueToComponent();
        copyValues(dst);
        dst.codeableConcept = codeableConcept == null ? null : codeableConcept.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    public static class ConditionOccurredFollowingComponent extends BackboneElement {
        /**
         * Code that identifies the target of this relationship. The code takes the place of a detailed instance target.
         */
        protected CodeableConcept codeableConcept;

        /**
         * Target of the relationship.
         */
        protected Reference target;

        /**
         * The actual object that is the target of the reference (Target of the relationship.)
         */
        protected Resource targetTarget;

        private static final long serialVersionUID = -864422450L;

      public ConditionOccurredFollowingComponent() {
        super();
      }

        /**
         * @return {@link #codeableConcept} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public CodeableConcept getCodeableConcept() { 
          return this.codeableConcept;
        }

        /**
         * @param value {@link #codeableConcept} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public ConditionOccurredFollowingComponent setCodeableConcept(CodeableConcept value) { 
          this.codeableConcept = value;
          return this;
        }

        /**
         * @return {@link #target} (Target of the relationship.)
         */
        public Reference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (Target of the relationship.)
         */
        public ConditionOccurredFollowingComponent setTarget(Reference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Target of the relationship.)
         */
        public Resource getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Target of the relationship.)
         */
        public ConditionOccurredFollowingComponent setTargetTarget(Resource value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeableConcept", "CodeableConcept", "Code that identifies the target of this relationship. The code takes the place of a detailed instance target.", 0, java.lang.Integer.MAX_VALUE, codeableConcept));
          childrenList.add(new Property("target", "Reference(Condition|Procedure|MedicationAdministration|Immunization|MedicationStatement)", "Target of the relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ConditionOccurredFollowingComponent copy() {
        ConditionOccurredFollowingComponent dst = new ConditionOccurredFollowingComponent();
        copyValues(dst);
        dst.codeableConcept = codeableConcept == null ? null : codeableConcept.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Indicates the patient who the condition record is associated with.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Indicates the patient who the condition record is associated with.)
     */
    protected Patient subjectTarget;

    /**
     * Encounter during which the condition was first asserted.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (Encounter during which the condition was first asserted.)
     */
    protected Encounter encounterTarget;

    /**
     * Person who takes responsibility for asserting the existence of the condition as part of the electronic record.
     */
    protected Reference asserter;

    /**
     * The actual object that is the target of the reference (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    protected Resource asserterTarget;

    /**
     * Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    protected DateType dateAsserted;

    /**
     * Identification of the condition, problem or diagnosis.
     */
    protected CodeableConcept code;

    /**
     * A category assigned to the condition. E.g. complaint | symptom | finding | diagnosis.
     */
    protected CodeableConcept category;

    /**
     * The clinical status of the condition.
     */
    protected Enumeration<ConditionStatus> status;

    /**
     * The degree of confidence that this condition is correct.
     */
    protected CodeableConcept certainty;

    /**
     * A subjective assessment of the severity of the condition as evaluated by the clinician.
     */
    protected CodeableConcept severity;

    /**
     * Estimated or actual date or date-time  the condition began, in the opinion of the clinician.
     */
    protected Type onset;

    /**
     * The date or estimated date that the condition resolved or went into remission. This is called "abatement" because of the many overloaded connotations associated with "remission" or "resolution" - Conditions are never really resolved, but they can abate.
     */
    protected Type abatement;

    /**
     * Clinical stage or grade of a condition. May include formal severity assessments.
     */
    protected ConditionStageComponent stage;

    /**
     * Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.
     */
    protected List<ConditionEvidenceComponent> evidence = new ArrayList<ConditionEvidenceComponent>();

    /**
     * The anatomical location where this condition manifests itself.
     */
    protected List<ConditionLocationComponent> location = new ArrayList<ConditionLocationComponent>();

    /**
     * Further conditions, problems, diagnoses, procedures or events or the substance that caused/triggered this Condition.
     */
    protected List<ConditionDueToComponent> dueTo = new ArrayList<ConditionDueToComponent>();

    /**
     * Further conditions, problems, diagnoses, procedures or events or the substance that preceded this Condition.
     */
    protected List<ConditionOccurredFollowingComponent> occurredFollowing = new ArrayList<ConditionOccurredFollowingComponent>();

    /**
     * Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    protected StringType notes;

    private static final long serialVersionUID = -2135620529L;

    public Condition() {
      super();
    }

    public Condition(Reference subject, CodeableConcept code, Enumeration<ConditionStatus> status) {
      super();
      this.subject = subject;
      this.code = code;
      this.status = status;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (Indicates the patient who the condition record is associated with.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Indicates the patient who the condition record is associated with.)
     */
    public Condition setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Indicates the patient who the condition record is associated with.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Indicates the patient who the condition record is associated with.)
     */
    public Condition setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (Encounter during which the condition was first asserted.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Encounter during which the condition was first asserted.)
     */
    public Condition setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Encounter during which the condition was first asserted.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Encounter during which the condition was first asserted.)
     */
    public Condition setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #asserter} (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Reference getAsserter() { 
      return this.asserter;
    }

    /**
     * @param value {@link #asserter} (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Condition setAsserter(Reference value) { 
      this.asserter = value;
      return this;
    }

    /**
     * @return {@link #asserter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Resource getAsserterTarget() { 
      return this.asserterTarget;
    }

    /**
     * @param value {@link #asserter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Condition setAsserterTarget(Resource value) { 
      this.asserterTarget = value;
      return this;
    }

    /**
     * @return {@link #dateAsserted} (Estimated or actual date the condition/problem/diagnosis was first detected/suspected.). This is the underlying object with id, value and extensions. The accessor "getDateAsserted" gives direct access to the value
     */
    public DateType getDateAssertedElement() { 
      return this.dateAsserted;
    }

    /**
     * @param value {@link #dateAsserted} (Estimated or actual date the condition/problem/diagnosis was first detected/suspected.). This is the underlying object with id, value and extensions. The accessor "getDateAsserted" gives direct access to the value
     */
    public Condition setDateAssertedElement(DateType value) { 
      this.dateAsserted = value;
      return this;
    }

    /**
     * @return Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    public DateAndTime getDateAsserted() { 
      return this.dateAsserted == null ? null : this.dateAsserted.getValue();
    }

    /**
     * @param value Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    public Condition setDateAsserted(DateAndTime value) { 
      if (value == null)
        this.dateAsserted = null;
      else {
        if (this.dateAsserted == null)
          this.dateAsserted = new DateType();
        this.dateAsserted.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #code} (Identification of the condition, problem or diagnosis.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (Identification of the condition, problem or diagnosis.)
     */
    public Condition setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #category} (A category assigned to the condition. E.g. complaint | symptom | finding | diagnosis.)
     */
    public CodeableConcept getCategory() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (A category assigned to the condition. E.g. complaint | symptom | finding | diagnosis.)
     */
    public Condition setCategory(CodeableConcept value) { 
      this.category = value;
      return this;
    }

    /**
     * @return {@link #status} (The clinical status of the condition.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ConditionStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The clinical status of the condition.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Condition setStatusElement(Enumeration<ConditionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The clinical status of the condition.
     */
    public ConditionStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The clinical status of the condition.
     */
    public Condition setStatus(ConditionStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ConditionStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #certainty} (The degree of confidence that this condition is correct.)
     */
    public CodeableConcept getCertainty() { 
      return this.certainty;
    }

    /**
     * @param value {@link #certainty} (The degree of confidence that this condition is correct.)
     */
    public Condition setCertainty(CodeableConcept value) { 
      this.certainty = value;
      return this;
    }

    /**
     * @return {@link #severity} (A subjective assessment of the severity of the condition as evaluated by the clinician.)
     */
    public CodeableConcept getSeverity() { 
      return this.severity;
    }

    /**
     * @param value {@link #severity} (A subjective assessment of the severity of the condition as evaluated by the clinician.)
     */
    public Condition setSeverity(CodeableConcept value) { 
      this.severity = value;
      return this;
    }

    /**
     * @return {@link #onset} (Estimated or actual date or date-time  the condition began, in the opinion of the clinician.)
     */
    public Type getOnset() { 
      return this.onset;
    }

    /**
     * @param value {@link #onset} (Estimated or actual date or date-time  the condition began, in the opinion of the clinician.)
     */
    public Condition setOnset(Type value) { 
      this.onset = value;
      return this;
    }

    /**
     * @return {@link #abatement} (The date or estimated date that the condition resolved or went into remission. This is called "abatement" because of the many overloaded connotations associated with "remission" or "resolution" - Conditions are never really resolved, but they can abate.)
     */
    public Type getAbatement() { 
      return this.abatement;
    }

    /**
     * @param value {@link #abatement} (The date or estimated date that the condition resolved or went into remission. This is called "abatement" because of the many overloaded connotations associated with "remission" or "resolution" - Conditions are never really resolved, but they can abate.)
     */
    public Condition setAbatement(Type value) { 
      this.abatement = value;
      return this;
    }

    /**
     * @return {@link #stage} (Clinical stage or grade of a condition. May include formal severity assessments.)
     */
    public ConditionStageComponent getStage() { 
      return this.stage;
    }

    /**
     * @param value {@link #stage} (Clinical stage or grade of a condition. May include formal severity assessments.)
     */
    public Condition setStage(ConditionStageComponent value) { 
      this.stage = value;
      return this;
    }

    /**
     * @return {@link #evidence} (Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.)
     */
    public List<ConditionEvidenceComponent> getEvidence() { 
      return this.evidence;
    }

    /**
     * @return {@link #evidence} (Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.)
     */
    // syntactic sugar
    public ConditionEvidenceComponent addEvidence() { //3
      ConditionEvidenceComponent t = new ConditionEvidenceComponent();
      this.evidence.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The anatomical location where this condition manifests itself.)
     */
    public List<ConditionLocationComponent> getLocation() { 
      return this.location;
    }

    /**
     * @return {@link #location} (The anatomical location where this condition manifests itself.)
     */
    // syntactic sugar
    public ConditionLocationComponent addLocation() { //3
      ConditionLocationComponent t = new ConditionLocationComponent();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #dueTo} (Further conditions, problems, diagnoses, procedures or events or the substance that caused/triggered this Condition.)
     */
    public List<ConditionDueToComponent> getDueTo() { 
      return this.dueTo;
    }

    /**
     * @return {@link #dueTo} (Further conditions, problems, diagnoses, procedures or events or the substance that caused/triggered this Condition.)
     */
    // syntactic sugar
    public ConditionDueToComponent addDueTo() { //3
      ConditionDueToComponent t = new ConditionDueToComponent();
      this.dueTo.add(t);
      return t;
    }

    /**
     * @return {@link #occurredFollowing} (Further conditions, problems, diagnoses, procedures or events or the substance that preceded this Condition.)
     */
    public List<ConditionOccurredFollowingComponent> getOccurredFollowing() { 
      return this.occurredFollowing;
    }

    /**
     * @return {@link #occurredFollowing} (Further conditions, problems, diagnoses, procedures or events or the substance that preceded this Condition.)
     */
    // syntactic sugar
    public ConditionOccurredFollowingComponent addOccurredFollowing() { //3
      ConditionOccurredFollowingComponent t = new ConditionOccurredFollowingComponent();
      this.occurredFollowing.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.). This is the underlying object with id, value and extensions. The accessor "getNotes" gives direct access to the value
     */
    public StringType getNotesElement() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.). This is the underlying object with id, value and extensions. The accessor "getNotes" gives direct access to the value
     */
    public Condition setNotesElement(StringType value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    public String getNotes() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    public Condition setNotes(String value) { 
      if (Utilities.noString(value))
        this.notes = null;
      else {
        if (this.notes == null)
          this.notes = new StringType();
        this.notes.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Reference(Patient)", "Indicates the patient who the condition record is associated with.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "Encounter during which the condition was first asserted.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("asserter", "Reference(Practitioner|Patient)", "Person who takes responsibility for asserting the existence of the condition as part of the electronic record.", 0, java.lang.Integer.MAX_VALUE, asserter));
        childrenList.add(new Property("dateAsserted", "date", "Estimated or actual date the condition/problem/diagnosis was first detected/suspected.", 0, java.lang.Integer.MAX_VALUE, dateAsserted));
        childrenList.add(new Property("code", "CodeableConcept", "Identification of the condition, problem or diagnosis.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("category", "CodeableConcept", "A category assigned to the condition. E.g. complaint | symptom | finding | diagnosis.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("status", "code", "The clinical status of the condition.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("certainty", "CodeableConcept", "The degree of confidence that this condition is correct.", 0, java.lang.Integer.MAX_VALUE, certainty));
        childrenList.add(new Property("severity", "CodeableConcept", "A subjective assessment of the severity of the condition as evaluated by the clinician.", 0, java.lang.Integer.MAX_VALUE, severity));
        childrenList.add(new Property("onset[x]", "dateTime|Age", "Estimated or actual date or date-time  the condition began, in the opinion of the clinician.", 0, java.lang.Integer.MAX_VALUE, onset));
        childrenList.add(new Property("abatement[x]", "date|Age|boolean", "The date or estimated date that the condition resolved or went into remission. This is called 'abatement' because of the many overloaded connotations associated with 'remission' or 'resolution' - Conditions are never really resolved, but they can abate.", 0, java.lang.Integer.MAX_VALUE, abatement));
        childrenList.add(new Property("stage", "", "Clinical stage or grade of a condition. May include formal severity assessments.", 0, java.lang.Integer.MAX_VALUE, stage));
        childrenList.add(new Property("evidence", "", "Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.", 0, java.lang.Integer.MAX_VALUE, evidence));
        childrenList.add(new Property("location", "", "The anatomical location where this condition manifests itself.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("dueTo", "", "Further conditions, problems, diagnoses, procedures or events or the substance that caused/triggered this Condition.", 0, java.lang.Integer.MAX_VALUE, dueTo));
        childrenList.add(new Property("occurredFollowing", "", "Further conditions, problems, diagnoses, procedures or events or the substance that preceded this Condition.", 0, java.lang.Integer.MAX_VALUE, occurredFollowing));
        childrenList.add(new Property("notes", "string", "Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.", 0, java.lang.Integer.MAX_VALUE, notes));
      }

      public Condition copy() {
        Condition dst = new Condition();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.asserter = asserter == null ? null : asserter.copy();
        dst.dateAsserted = dateAsserted == null ? null : dateAsserted.copy();
        dst.code = code == null ? null : code.copy();
        dst.category = category == null ? null : category.copy();
        dst.status = status == null ? null : status.copy();
        dst.certainty = certainty == null ? null : certainty.copy();
        dst.severity = severity == null ? null : severity.copy();
        dst.onset = onset == null ? null : onset.copy();
        dst.abatement = abatement == null ? null : abatement.copy();
        dst.stage = stage == null ? null : stage.copy();
        dst.evidence = new ArrayList<ConditionEvidenceComponent>();
        for (ConditionEvidenceComponent i : evidence)
          dst.evidence.add(i.copy());
        dst.location = new ArrayList<ConditionLocationComponent>();
        for (ConditionLocationComponent i : location)
          dst.location.add(i.copy());
        dst.dueTo = new ArrayList<ConditionDueToComponent>();
        for (ConditionDueToComponent i : dueTo)
          dst.dueTo.add(i.copy());
        dst.occurredFollowing = new ArrayList<ConditionOccurredFollowingComponent>();
        for (ConditionOccurredFollowingComponent i : occurredFollowing)
          dst.occurredFollowing.add(i.copy());
        dst.notes = notes == null ? null : notes.copy();
        return dst;
      }

      protected Condition typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Condition;
   }


}

