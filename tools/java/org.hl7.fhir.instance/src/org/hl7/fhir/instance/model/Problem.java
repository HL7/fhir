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

// Generated on Fri, Jun 7, 2013 00:21+1000 for FHIR v0.09

import java.util.*;

/**
 * Use to record detailed information about problems or diagnoses recognised by a clinician. There are many uses including: recording a Diagnosis during an Visit; populating a Problem List or a Summary Statement, such as a Discharge Summary
 */
public class Problem extends Resource {

    public enum ProblemStatus {
        provisional, // 
        working, // 
        confirmed, // 
        refuted, // 
        Null; // added to help the parsers
        public static ProblemStatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown ProblemStatus code '"+codeString+"'");
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

  public class ProblemStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("provisional".equals(codeString))
          return ProblemStatus.provisional;
        if ("working".equals(codeString))
          return ProblemStatus.working;
        if ("confirmed".equals(codeString))
          return ProblemStatus.confirmed;
        if ("refuted".equals(codeString))
          return ProblemStatus.refuted;
        throw new Exception("Unknown ProblemStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProblemStatus.provisional)
        return "provisional";
      if (code == ProblemStatus.working)
        return "working";
      if (code == ProblemStatus.confirmed)
        return "confirmed";
      if (code == ProblemStatus.refuted)
        return "refuted";
      return "?";
      }
    }

    public enum ProblemRelationshipType {
        dueMinusto, // 
        follows, // 
        Null; // added to help the parsers
        public static ProblemRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("due-to".equals(codeString))
          return dueMinusto;
        if ("follows".equals(codeString))
          return follows;
        throw new Exception("Unknown ProblemRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case dueMinusto: return "due-to";
            case follows: return "follows";
            default: return "?";
          }
        }
    }

  public class ProblemRelationshipTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("due-to".equals(codeString))
          return ProblemRelationshipType.dueMinusto;
        if ("follows".equals(codeString))
          return ProblemRelationshipType.follows;
        throw new Exception("Unknown ProblemRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProblemRelationshipType.dueMinusto)
        return "due-to";
      if (code == ProblemRelationshipType.follows)
        return "follows";
      return "?";
      }
    }

    public class ProblemStageComponent extends Element {
        /**
         * A simple summary of the stage such as "Stage 3". The determination of the stage is disease-specific
         */
        private CodeableConcept summary;

        /**
         * Reference to a formal record of the evidence on which the staging assessment is based
         */
        private List<ResourceReference> assessment = new ArrayList<ResourceReference>();

        public CodeableConcept getSummary() { 
          return this.summary;
        }

        public void setSummary(CodeableConcept value) { 
          this.summary = value;
        }

        public List<ResourceReference> getAssessment() { 
          return this.assessment;
        }

  }

    public class ProblemEvidenceComponent extends Element {
        /**
         * A manifestion or symptom that led to the recording of this problem/diagnosis
         */
        private CodeableConcept code;

        /**
         * Links to other relevant information, including pathology reports
         */
        private List<ResourceReference> details = new ArrayList<ResourceReference>();

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public List<ResourceReference> getDetails() { 
          return this.details;
        }

  }

    public class ProblemLocationComponent extends Element {
        /**
         * Code that identifies the structural location
         */
        private CodeableConcept code;

        /**
         * Detailed anatomical location information
         */
        private String_ details;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public String_ getDetails() { 
          return this.details;
        }

        public void setDetails(String_ value) { 
          this.details = value;
        }

        public String getDetailsSimple() { 
          return this.details == null ? null : this.details.getValue();
        }

        public void setDetailsSimple(String value) { 
          if (value == null)
            this.details = null;
          else {
            if (this.details == null)
              this.details = new String_();
            this.details.setValue(value);
          }
        }

  }

    public class ProblemRelatedItemComponent extends Element {
        /**
         * The type of relationship that this problem/diagnosis has to the related item
         */
        private Enumeration<ProblemRelationshipType> type;

        /**
         * Target of the relationship
         */
        private ResourceReference target;

        public Enumeration<ProblemRelationshipType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<ProblemRelationshipType> value) { 
          this.type = value;
        }

        public ProblemRelationshipType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(ProblemRelationshipType value) { 
            if (this.type == null)
              this.type = new Enumeration<ProblemRelationshipType>();
            this.type.setValue(value);
        }

        public ResourceReference getTarget() { 
          return this.target;
        }

        public void setTarget(ResourceReference value) { 
          this.target = value;
        }

  }

    /**
     * Subject of this problem
     */
    private ResourceReference subject;

    /**
     * Visit during which the problem was first asserted
     */
    private ResourceReference visit;

    /**
     * Person who asserts this problem
     */
    private ResourceReference asserter;

    /**
     * Estimated or actual date the problem/diagnosis was first detected/suspected
     */
    private Date dateAsserted;

    /**
     * Identification of the problem or diagnosis.
     */
    private CodeableConcept code;

    /**
     * A category assigned to the problem/diagnosis. E.g. finding | problem | diagnosis | concern | condition
     */
    private CodeableConcept category;

    /**
     * The clinical status of the problem or diagnosis
     */
    private Enumeration<ProblemStatus> status;

    /**
     * The degree of confidence that this problem/diagnosis is correct
     */
    private CodeableConcept certainty;

    /**
     * A subjective assessment of the severity of the Problem/Diagnosis as evaluated by the clinician.
     */
    private CodeableConcept severity;

    /**
     * Estimated or actual date the problem/diagnosis began, in the opinion of the clinician
     */
    private Type onset;

    /**
     * The date or estimated date that the problem/diagnosis resolved or went into remission. This is called "abatement" because of the many overloaded connotations associated with "remission" or "resolution" - problems are never really resolved, but they can abate.
     */
    private Type abatement;

    /**
     * Clinical stage or grade of a problem/diagnosis. May include formal severity assessments
     */
    private ProblemStageComponent stage;

    /**
     * Supporting Evidence / manfiestions that are the basis on which this problem/diagnosis is suspected or confirmed
     */
    private List<ProblemEvidenceComponent> evidence = new ArrayList<ProblemEvidenceComponent>();

    /**
     * The anatomical location where this problem/diagnosis manifests itself
     */
    private List<ProblemLocationComponent> location = new ArrayList<ProblemLocationComponent>();

    /**
     * Further problems, diagnoses, procedures or events that are related in some way to this problem/diagnosis, or the substance that caused/triggered this problem
     */
    private List<ProblemRelatedItemComponent> relatedItem = new ArrayList<ProblemRelatedItemComponent>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
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

    public Enumeration<ProblemStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<ProblemStatus> value) { 
      this.status = value;
    }

    public ProblemStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(ProblemStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ProblemStatus>();
        this.status.setValue(value);
      }
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

    public ProblemStageComponent getStage() { 
      return this.stage;
    }

    public void setStage(ProblemStageComponent value) { 
      this.stage = value;
    }

    public List<ProblemEvidenceComponent> getEvidence() { 
      return this.evidence;
    }

    public List<ProblemLocationComponent> getLocation() { 
      return this.location;
    }

    public List<ProblemRelatedItemComponent> getRelatedItem() { 
      return this.relatedItem;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Problem;
   }


}

