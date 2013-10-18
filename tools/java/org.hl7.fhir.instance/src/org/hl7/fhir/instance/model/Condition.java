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

// Generated on Fri, Oct 18, 2013 12:16+1100 for FHIR v0.12

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

  public class ConditionStatusEnumFactory implements EnumFactory {
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

  public class ConditionRelationshipTypeEnumFactory implements EnumFactory {
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

    public class ConditionStageComponent extends Element {
        /**
         * A simple summary of the stage such as "Stage 3". The determination of the stage is disease-specific.
         */
        protected CodeableConcept summary;

        /**
         * Reference to a formal record of the evidence on which the staging assessment is based.
         */
        protected List<ResourceReference> assessment = new ArrayList<ResourceReference>();

        public CodeableConcept getSummary() { 
          return this.summary;
        }

        public void setSummary(CodeableConcept value) { 
          this.summary = value;
        }

        public List<ResourceReference> getAssessment() { 
          return this.assessment;
        }

    // syntactic sugar
        public ResourceReference addAssessment() { 
          ResourceReference t = new ResourceReference();
          this.assessment.add(t);
          return t;
        }

      public ConditionStageComponent copy(Condition e) {
        ConditionStageComponent dst = e.new ConditionStageComponent();
        dst.summary = summary == null ? null : summary.copy();
        dst.assessment = new ArrayList<ResourceReference>();
        for (ResourceReference i : assessment)
          dst.assessment.add(i.copy());
        return dst;
      }

  }

    public class ConditionEvidenceComponent extends Element {
        /**
         * A manifestation or symptom that led to the recording of this condition.
         */
        protected CodeableConcept code;

        /**
         * Links to other relevant information, including pathology reports.
         */
        protected List<ResourceReference> detail = new ArrayList<ResourceReference>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public List<ResourceReference> getDetail() { 
          return this.detail;
        }

    // syntactic sugar
        public ResourceReference addDetail() { 
          ResourceReference t = new ResourceReference();
          this.detail.add(t);
          return t;
        }

      public ConditionEvidenceComponent copy(Condition e) {
        ConditionEvidenceComponent dst = e.new ConditionEvidenceComponent();
        dst.code = code == null ? null : code.copy();
        dst.detail = new ArrayList<ResourceReference>();
        for (ResourceReference i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

  }

    public class ConditionLocationComponent extends Element {
        /**
         * Code that identifies the structural location.
         */
        protected CodeableConcept code;

        /**
         * Detailed anatomical location information.
         */
        protected String_ detail;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public String_ getDetail() { 
          return this.detail;
        }

        public void setDetail(String_ value) { 
          this.detail = value;
        }

        public String getDetailSimple() { 
          return this.detail == null ? null : this.detail.getValue();
        }

        public void setDetailSimple(String value) { 
          if (value == null)
            this.detail = null;
          else {
            if (this.detail == null)
              this.detail = new String_();
            this.detail.setValue(value);
          }
        }

      public ConditionLocationComponent copy(Condition e) {
        ConditionLocationComponent dst = e.new ConditionLocationComponent();
        dst.code = code == null ? null : code.copy();
        dst.detail = detail == null ? null : detail.copy();
        return dst;
      }

  }

    public class ConditionRelatedItemComponent extends Element {
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

        public Enumeration<ConditionRelationshipType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<ConditionRelationshipType> value) { 
          this.type = value;
        }

        public ConditionRelationshipType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(ConditionRelationshipType value) { 
            if (this.type == null)
              this.type = new Enumeration<ConditionRelationshipType>();
            this.type.setValue(value);
        }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public ResourceReference getTarget() { 
          return this.target;
        }

        public void setTarget(ResourceReference value) { 
          this.target = value;
        }

      public ConditionRelatedItemComponent copy(Condition e) {
        ConditionRelatedItemComponent dst = e.new ConditionRelatedItemComponent();
        dst.type = type == null ? null : type.copy();
        dst.code = code == null ? null : code.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this condition that are defined by business processed and/ or used to refer to it when a direct URL refernce to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Subject of this condition.
     */
    protected ResourceReference subject;

    /**
     * Encounter during which the condition was first asserted.
     */
    protected ResourceReference encounter;

    /**
     * Person who takes responsibility for asserting the existence of the condition as part of the electronic record.
     */
    protected ResourceReference asserter;

    /**
     * Estimated or actual date the condition/problem/diagnosis was first detected/suspected.
     */
    protected Date dateAsserted;

    /**
     * Identification of the condition, problem or diagnosis.
     */
    protected CodeableConcept code;

    /**
     * A category assigned to the condition. E.g. finding | Condition | diagnosis | concern | condition.
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

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public ResourceReference getAsserter() { 
      return this.asserter;
    }

    public void setAsserter(ResourceReference value) { 
      this.asserter = value;
    }

    public Date getDateAsserted() { 
      return this.dateAsserted;
    }

    public void setDateAsserted(Date value) { 
      this.dateAsserted = value;
    }

    public String getDateAssertedSimple() { 
      return this.dateAsserted == null ? null : this.dateAsserted.getValue();
    }

    public void setDateAssertedSimple(String value) { 
      if (value == null)
        this.dateAsserted = null;
      else {
        if (this.dateAsserted == null)
          this.dateAsserted = new Date();
        this.dateAsserted.setValue(value);
      }
    }

    public CodeableConcept getCode() { 
      return this.code;
    }

    public void setCode(CodeableConcept value) { 
      this.code = value;
    }

    public CodeableConcept getCategory() { 
      return this.category;
    }

    public void setCategory(CodeableConcept value) { 
      this.category = value;
    }

    public Enumeration<ConditionStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<ConditionStatus> value) { 
      this.status = value;
    }

    public ConditionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(ConditionStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ConditionStatus>();
        this.status.setValue(value);
    }

    public CodeableConcept getCertainty() { 
      return this.certainty;
    }

    public void setCertainty(CodeableConcept value) { 
      this.certainty = value;
    }

    public CodeableConcept getSeverity() { 
      return this.severity;
    }

    public void setSeverity(CodeableConcept value) { 
      this.severity = value;
    }

    public Type getOnset() { 
      return this.onset;
    }

    public void setOnset(Type value) { 
      this.onset = value;
    }

    public Type getAbatement() { 
      return this.abatement;
    }

    public void setAbatement(Type value) { 
      this.abatement = value;
    }

    public ConditionStageComponent getStage() { 
      return this.stage;
    }

    public void setStage(ConditionStageComponent value) { 
      this.stage = value;
    }

    public List<ConditionEvidenceComponent> getEvidence() { 
      return this.evidence;
    }

    // syntactic sugar
    public ConditionEvidenceComponent addEvidence() { 
      ConditionEvidenceComponent t = new ConditionEvidenceComponent();
      this.evidence.add(t);
      return t;
    }

    public List<ConditionLocationComponent> getLocation() { 
      return this.location;
    }

    // syntactic sugar
    public ConditionLocationComponent addLocation() { 
      ConditionLocationComponent t = new ConditionLocationComponent();
      this.location.add(t);
      return t;
    }

    public List<ConditionRelatedItemComponent> getRelatedItem() { 
      return this.relatedItem;
    }

    // syntactic sugar
    public ConditionRelatedItemComponent addRelatedItem() { 
      ConditionRelatedItemComponent t = new ConditionRelatedItemComponent();
      this.relatedItem.add(t);
      return t;
    }

    public String_ getNotes() { 
      return this.notes;
    }

    public void setNotes(String_ value) { 
      this.notes = value;
    }

    public String getNotesSimple() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    public void setNotesSimple(String value) { 
      if (value == null)
        this.notes = null;
      else {
        if (this.notes == null)
          this.notes = new String_();
        this.notes.setValue(value);
      }
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
        dst.stage = stage == null ? null : stage.copy(dst);
        dst.evidence = new ArrayList<ConditionEvidenceComponent>();
        for (ConditionEvidenceComponent i : evidence)
          dst.evidence.add(i.copy(dst));
        dst.location = new ArrayList<ConditionLocationComponent>();
        for (ConditionLocationComponent i : location)
          dst.location.add(i.copy(dst));
        dst.relatedItem = new ArrayList<ConditionRelatedItemComponent>();
        for (ConditionRelatedItemComponent i : relatedItem)
          dst.relatedItem.add(i.copy(dst));
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

