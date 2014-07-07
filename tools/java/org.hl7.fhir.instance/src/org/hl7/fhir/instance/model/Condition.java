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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

/**
 * Use to record detailed information about conditions, problems or diagnoses recognized by a clinician. There are many uses including: recording a Diagnosis during an Encounter; populating a problem List or a Summary Statement, such as a Discharge Summary.
 */
public class Condition extends Resource {

    public enum ConditionStatus {
        provisional, // This is a tentative diagnosis - still a candidate that is under consideration.
        working, // The patient is being treated on the basis that this is the condition, but it is still not confirmed.
        confirmed, // There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition.
        refuted, // This condition has been ruled out by diagnostic and clinical evidence.
        Null; // added to help the parsers
        public static ConditionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("provisional".equals(codeString))
          return provisional;
        if ("working".equals(codeString))
          return working;
        if ("confirmed".equals(codeString))
          return confirmed;
        if ("refuted".equals(codeString))
          return refuted;
        throw new Exception("Unknown ConditionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case provisional: return "provisional";
            case working: return "working";
            case confirmed: return "confirmed";
            case refuted: return "refuted";
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
          return ConditionStatus.provisional;
        if ("working".equals(codeString))
          return ConditionStatus.working;
        if ("confirmed".equals(codeString))
          return ConditionStatus.confirmed;
        if ("refuted".equals(codeString))
          return ConditionStatus.refuted;
        throw new Exception("Unknown ConditionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConditionStatus.provisional)
        return "provisional";
      if (code == ConditionStatus.working)
        return "working";
      if (code == ConditionStatus.confirmed)
        return "confirmed";
      if (code == ConditionStatus.refuted)
        return "refuted";
      return "?";
      }
    }

    public enum ConditionRelationshipType {
        dueto, // this condition follows the identified condition/procedure/substance and is a consequence of it.
        following, // this condition follows the identified condition/procedure/substance, but it is not known whether they are causually linked.
        Null; // added to help the parsers
        public static ConditionRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("due-to".equals(codeString))
          return dueto;
        if ("following".equals(codeString))
          return following;
        throw new Exception("Unknown ConditionRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case dueto: return "due-to";
            case following: return "following";
            default: return "?";
          }
        }
    }

  public static class ConditionRelationshipTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("due-to".equals(codeString))
          return ConditionRelationshipType.dueto;
        if ("following".equals(codeString))
          return ConditionRelationshipType.following;
        throw new Exception("Unknown ConditionRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConditionRelationshipType.dueto)
        return "due-to";
      if (code == ConditionRelationshipType.following)
        return "following";
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
        protected List<ResourceReference> assessment = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        protected List<Resource> assessmentTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = -1698066074L;

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
        public List<ResourceReference> getAssessment() { 
          return this.assessment;
        }

    // syntactic sugar
        /**
         * @return {@link #assessment} (Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        public ResourceReference addAssessment() { 
          ResourceReference t = new ResourceReference();
          this.assessment.add(t);
          return t;
        }

        /**
         * @return {@link #assessment} (The actual objects that are the target of the reference. Reference to a formal record of the evidence on which the staging assessment is based.)
         */
        public List<Resource> getAssessmentTarget() { 
          return this.assessmentTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("summary", "CodeableConcept", "A simple summary of the stage such as 'Stage 3'. The determination of the stage is disease-specific.", 0, java.lang.Integer.MAX_VALUE, summary));
          childrenList.add(new Property("assessment", "Resource(Any)", "Reference to a formal record of the evidence on which the staging assessment is based.", 0, java.lang.Integer.MAX_VALUE, assessment));
        }

      public ConditionStageComponent copy() {
        ConditionStageComponent dst = new ConditionStageComponent();
        dst.summary = summary == null ? null : summary.copy();
        dst.assessment = new ArrayList<ResourceReference>();
        for (ResourceReference i : assessment)
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
        protected List<ResourceReference> detail = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Links to other relevant information, including pathology reports.)
         */
        protected List<Resource> detailTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = 1835722845L;

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
        public List<ResourceReference> getDetail() { 
          return this.detail;
        }

    // syntactic sugar
        /**
         * @return {@link #detail} (Links to other relevant information, including pathology reports.)
         */
        public ResourceReference addDetail() { 
          ResourceReference t = new ResourceReference();
          this.detail.add(t);
          return t;
        }

        /**
         * @return {@link #detail} (The actual objects that are the target of the reference. Links to other relevant information, including pathology reports.)
         */
        public List<Resource> getDetailTarget() { 
          return this.detailTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A manifestation or symptom that led to the recording of this condition.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("detail", "Resource(Any)", "Links to other relevant information, including pathology reports.", 0, java.lang.Integer.MAX_VALUE, detail));
        }

      public ConditionEvidenceComponent copy() {
        ConditionEvidenceComponent dst = new ConditionEvidenceComponent();
        dst.code = code == null ? null : code.copy();
        dst.detail = new ArrayList<ResourceReference>();
        for (ResourceReference i : detail)
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
        protected String_ detail;

        private static final long serialVersionUID = -1468883543L;

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
         * @return {@link #detail} (Detailed anatomical location information.)
         */
        public String_ getDetail() { 
          return this.detail;
        }

        /**
         * @param value {@link #detail} (Detailed anatomical location information.)
         */
        public ConditionLocationComponent setDetail(String_ value) { 
          this.detail = value;
          return this;
        }

        /**
         * @return Detailed anatomical location information.
         */
        public String getDetailSimple() { 
          return this.detail == null ? null : this.detail.getValue();
        }

        /**
         * @param value Detailed anatomical location information.
         */
        public ConditionLocationComponent setDetailSimple(String value) { 
          if (value == null)
            this.detail = null;
          else {
            if (this.detail == null)
              this.detail = new String_();
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
        dst.code = code == null ? null : code.copy();
        dst.detail = detail == null ? null : detail.copy();
        return dst;
      }

  }

    public static class ConditionRelatedItemComponent extends BackboneElement {
        /**
         * The type of relationship that this condition has to the related item.
         */
        protected Enumeration<ConditionRelationshipType> type;

        /**
         * Code that identifies the target of this relationship. The code takes the place of a detailed instance target.
         */
        protected CodeableConcept code;

        /**
         * Target of the relationship.
         */
        protected ResourceReference target;

        /**
         * The actual object that is the target of the reference (Target of the relationship.)
         */
        protected Resource targetTarget;

        private static final long serialVersionUID = -7869756L;

      public ConditionRelatedItemComponent() {
        super();
      }

      public ConditionRelatedItemComponent(Enumeration<ConditionRelationshipType> type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The type of relationship that this condition has to the related item.)
         */
        public Enumeration<ConditionRelationshipType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of relationship that this condition has to the related item.)
         */
        public ConditionRelatedItemComponent setType(Enumeration<ConditionRelationshipType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of relationship that this condition has to the related item.
         */
        public ConditionRelationshipType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of relationship that this condition has to the related item.
         */
        public ConditionRelatedItemComponent setTypeSimple(ConditionRelationshipType value) { 
            if (this.type == null)
              this.type = new Enumeration<ConditionRelationshipType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies the target of this relationship. The code takes the place of a detailed instance target.)
         */
        public ConditionRelatedItemComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #target} (Target of the relationship.)
         */
        public ResourceReference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (Target of the relationship.)
         */
        public ConditionRelatedItemComponent setTarget(ResourceReference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} (The actual object that is the target of the reference. Target of the relationship.)
         */
        public Resource getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} (The actual object that is the target of the reference. Target of the relationship.)
         */
        public ConditionRelatedItemComponent setTargetTarget(Resource value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The type of relationship that this condition has to the related item.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("code", "CodeableConcept", "Code that identifies the target of this relationship. The code takes the place of a detailed instance target.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("target", "Resource(Condition|Procedure|MedicationAdministration|Immunization|MedicationStatement)", "Target of the relationship.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ConditionRelatedItemComponent copy() {
        ConditionRelatedItemComponent dst = new ConditionRelatedItemComponent();
        dst.type = type == null ? null : type.copy();
        dst.code = code == null ? null : code.copy();
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
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (Indicates the patient who the condition record is associated with.)
     */
    protected Patient subjectTarget;

    /**
     * Encounter during which the condition was first asserted.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (Encounter during which the condition was first asserted.)
     */
    protected Encounter encounterTarget;

    /**
     * Person who takes responsibility for asserting the existence of the condition as part of the electronic record.
     */
    protected ResourceReference asserter;

    /**
     * The actual object that is the target of the reference (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    protected Resource asserterTarget;

    /**
     * Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    protected Date dateAsserted;

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
     * Estimated or actual date the condition began, in the opinion of the clinician.
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
     * Further conditions, problems, diagnoses, procedures or events that are related in some way to this condition, or the substance that caused/triggered this Condition.
     */
    protected List<ConditionRelatedItemComponent> relatedItem = new ArrayList<ConditionRelatedItemComponent>();

    /**
     * Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    protected String_ notes;

    private static final long serialVersionUID = -1164607486L;

    public Condition() {
      super();
    }

    public Condition(ResourceReference subject, CodeableConcept code, Enumeration<ConditionStatus> status) {
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

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (Indicates the patient who the condition record is associated with.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Indicates the patient who the condition record is associated with.)
     */
    public Condition setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. Indicates the patient who the condition record is associated with.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. Indicates the patient who the condition record is associated with.)
     */
    public Condition setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (Encounter during which the condition was first asserted.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (Encounter during which the condition was first asserted.)
     */
    public Condition setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. Encounter during which the condition was first asserted.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. Encounter during which the condition was first asserted.)
     */
    public Condition setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #asserter} (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public ResourceReference getAsserter() { 
      return this.asserter;
    }

    /**
     * @param value {@link #asserter} (Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Condition setAsserter(ResourceReference value) { 
      this.asserter = value;
      return this;
    }

    /**
     * @return {@link #asserter} (The actual object that is the target of the reference. Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Resource getAsserterTarget() { 
      return this.asserterTarget;
    }

    /**
     * @param value {@link #asserter} (The actual object that is the target of the reference. Person who takes responsibility for asserting the existence of the condition as part of the electronic record.)
     */
    public Condition setAsserterTarget(Resource value) { 
      this.asserterTarget = value;
      return this;
    }

    /**
     * @return {@link #dateAsserted} (Estimated or actual date the condition/problem/diagnosis was first detected/suspected.)
     */
    public Date getDateAsserted() { 
      return this.dateAsserted;
    }

    /**
     * @param value {@link #dateAsserted} (Estimated or actual date the condition/problem/diagnosis was first detected/suspected.)
     */
    public Condition setDateAsserted(Date value) { 
      this.dateAsserted = value;
      return this;
    }

    /**
     * @return Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    public DateAndTime getDateAssertedSimple() { 
      return this.dateAsserted == null ? null : this.dateAsserted.getValue();
    }

    /**
     * @param value Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    public Condition setDateAssertedSimple(DateAndTime value) { 
      if (value == null)
        this.dateAsserted = null;
      else {
        if (this.dateAsserted == null)
          this.dateAsserted = new Date();
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
     * @return {@link #status} (The clinical status of the condition.)
     */
    public Enumeration<ConditionStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The clinical status of the condition.)
     */
    public Condition setStatus(Enumeration<ConditionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The clinical status of the condition.
     */
    public ConditionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The clinical status of the condition.
     */
    public Condition setStatusSimple(ConditionStatus value) { 
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
     * @return {@link #onset} (Estimated or actual date the condition began, in the opinion of the clinician.)
     */
    public Type getOnset() { 
      return this.onset;
    }

    /**
     * @param value {@link #onset} (Estimated or actual date the condition began, in the opinion of the clinician.)
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

    // syntactic sugar
    /**
     * @return {@link #evidence} (Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.)
     */
    public ConditionEvidenceComponent addEvidence() { 
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

    // syntactic sugar
    /**
     * @return {@link #location} (The anatomical location where this condition manifests itself.)
     */
    public ConditionLocationComponent addLocation() { 
      ConditionLocationComponent t = new ConditionLocationComponent();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #relatedItem} (Further conditions, problems, diagnoses, procedures or events that are related in some way to this condition, or the substance that caused/triggered this Condition.)
     */
    public List<ConditionRelatedItemComponent> getRelatedItem() { 
      return this.relatedItem;
    }

    // syntactic sugar
    /**
     * @return {@link #relatedItem} (Further conditions, problems, diagnoses, procedures or events that are related in some way to this condition, or the substance that caused/triggered this Condition.)
     */
    public ConditionRelatedItemComponent addRelatedItem() { 
      ConditionRelatedItemComponent t = new ConditionRelatedItemComponent();
      this.relatedItem.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.)
     */
    public String_ getNotes() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.)
     */
    public Condition setNotes(String_ value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    public String getNotesSimple() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.
     */
    public Condition setNotesSimple(String value) { 
      if (value == null)
        this.notes = null;
      else {
        if (this.notes == null)
          this.notes = new String_();
        this.notes.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient)", "Indicates the patient who the condition record is associated with.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "Encounter during which the condition was first asserted.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("asserter", "Resource(Practitioner|Patient)", "Person who takes responsibility for asserting the existence of the condition as part of the electronic record.", 0, java.lang.Integer.MAX_VALUE, asserter));
        childrenList.add(new Property("dateAsserted", "date", "Estimated or actual date the condition/problem/diagnosis was first detected/suspected.", 0, java.lang.Integer.MAX_VALUE, dateAsserted));
        childrenList.add(new Property("code", "CodeableConcept", "Identification of the condition, problem or diagnosis.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("category", "CodeableConcept", "A category assigned to the condition. E.g. complaint | symptom | finding | diagnosis.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("status", "code", "The clinical status of the condition.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("certainty", "CodeableConcept", "The degree of confidence that this condition is correct.", 0, java.lang.Integer.MAX_VALUE, certainty));
        childrenList.add(new Property("severity", "CodeableConcept", "A subjective assessment of the severity of the condition as evaluated by the clinician.", 0, java.lang.Integer.MAX_VALUE, severity));
        childrenList.add(new Property("onset[x]", "date|Age", "Estimated or actual date the condition began, in the opinion of the clinician.", 0, java.lang.Integer.MAX_VALUE, onset));
        childrenList.add(new Property("abatement[x]", "date|Age|boolean", "The date or estimated date that the condition resolved or went into remission. This is called 'abatement' because of the many overloaded connotations associated with 'remission' or 'resolution' - Conditions are never really resolved, but they can abate.", 0, java.lang.Integer.MAX_VALUE, abatement));
        childrenList.add(new Property("stage", "", "Clinical stage or grade of a condition. May include formal severity assessments.", 0, java.lang.Integer.MAX_VALUE, stage));
        childrenList.add(new Property("evidence", "", "Supporting Evidence / manifestations that are the basis on which this condition is suspected or confirmed.", 0, java.lang.Integer.MAX_VALUE, evidence));
        childrenList.add(new Property("location", "", "The anatomical location where this condition manifests itself.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("relatedItem", "", "Further conditions, problems, diagnoses, procedures or events that are related in some way to this condition, or the substance that caused/triggered this Condition.", 0, java.lang.Integer.MAX_VALUE, relatedItem));
        childrenList.add(new Property("notes", "string", "Additional information about the Condition. This is a general notes/comments entry  for description of the Condition, its diagnosis and prognosis.", 0, java.lang.Integer.MAX_VALUE, notes));
      }

      public Condition copy() {
        Condition dst = new Condition();
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
        dst.relatedItem = new ArrayList<ConditionRelatedItemComponent>();
        for (ConditionRelatedItemComponent i : relatedItem)
          dst.relatedItem.add(i.copy());
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

