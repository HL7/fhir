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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

import java.util.*;

/**
 * Significant health events and conditions for people related to the subject relevant in the context of care for the subject.
 */
public class FamilyHistory extends Resource {

    public class FamilyHistoryRelationComponent extends Element {
        /**
         * This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".
         */
        protected String_ name;

        /**
         * The type of relationship this person has to the patient (father, mother, brother etc.) At the moment this is a code linking to a fixed set of values. I'm not sure if there is an international standard for this. A fixed (possibly extensible) set of codes feels better than a codeable concept for something like this...
         */
        protected CodeableConcept relationship;

        /**
         * If this resource is indicating that the related person is deceased, then an indicator of whether the person is deceased (yes) or not (no) or the age or age range or description of age at death - can be indicated here. If the reason for death is known, then it can be indicated in the outcome code of the condition - in this case the deceased property should still be set.
         */
        protected Type deceased;

        /**
         * This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.
         */
        protected String_ note;

        /**
         * The significant Conditions (or condition) that the family member had. This is a repeating section to allow a system to represent more than one condition per resource, though there is nothing stopping multiple resources - one per condition.
         */
        protected List<FamilyHistoryRelationConditionComponent> condition = new ArrayList<FamilyHistoryRelationConditionComponent>();

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
        }

        public CodeableConcept getRelationship() { 
          return this.relationship;
        }

        public void setRelationship(CodeableConcept value) { 
          this.relationship = value;
        }

        public Type getDeceased() { 
          return this.deceased;
        }

        public void setDeceased(Type value) { 
          this.deceased = value;
        }

        public String_ getNote() { 
          return this.note;
        }

        public void setNote(String_ value) { 
          this.note = value;
        }

        public String getNoteSimple() { 
          return this.note == null ? null : this.note.getValue();
        }

        public void setNoteSimple(String value) { 
          if (value == null)
            this.note = null;
          else {
            if (this.note == null)
              this.note = new String_();
            this.note.setValue(value);
          }
        }

        public List<FamilyHistoryRelationConditionComponent> getCondition() { 
          return this.condition;
        }

    // syntactic sugar
        public FamilyHistoryRelationConditionComponent addCondition() { 
          FamilyHistoryRelationConditionComponent t = new FamilyHistoryRelationConditionComponent();
          this.condition.add(t);
          return t;
        }

      public FamilyHistoryRelationComponent copy(FamilyHistory e) {
        FamilyHistoryRelationComponent dst = e.new FamilyHistoryRelationComponent();
        dst.name = name == null ? null : name.copy();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.deceased = deceased == null ? null : deceased.copy();
        dst.note = note == null ? null : note.copy();
        dst.condition = new ArrayList<FamilyHistoryRelationConditionComponent>();
        for (FamilyHistoryRelationConditionComponent i : condition)
          dst.condition.add(i.copy(e));
        return dst;
      }

  }

    public class FamilyHistoryRelationConditionComponent extends Element {
        /**
         * The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system.
         */
        protected CodeableConcept type;

        /**
         * Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.
         */
        protected CodeableConcept outcome;

        /**
         * Either the age of onset, range of approximate age or descriptive string can be recorded.
         */
        protected Type onset;

        /**
         * An area where general notes can be placed about this specific condition.
         */
        protected String_ note;

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public CodeableConcept getOutcome() { 
          return this.outcome;
        }

        public void setOutcome(CodeableConcept value) { 
          this.outcome = value;
        }

        public Type getOnset() { 
          return this.onset;
        }

        public void setOnset(Type value) { 
          this.onset = value;
        }

        public String_ getNote() { 
          return this.note;
        }

        public void setNote(String_ value) { 
          this.note = value;
        }

        public String getNoteSimple() { 
          return this.note == null ? null : this.note.getValue();
        }

        public void setNoteSimple(String value) { 
          if (value == null)
            this.note = null;
          else {
            if (this.note == null)
              this.note = new String_();
            this.note.setValue(value);
          }
        }

      public FamilyHistoryRelationConditionComponent copy(FamilyHistory e) {
        FamilyHistoryRelationConditionComponent dst = e.new FamilyHistoryRelationConditionComponent();
        dst.type = type == null ? null : type.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.onset = onset == null ? null : onset.copy();
        dst.note = note == null ? null : note.copy();
        return dst;
      }

  }

    /**
     * The person who this history concerns.
     */
    protected ResourceReference subject;

    /**
     * Conveys information about family history not specific to individual relations.
     */
    protected String_ note;

    /**
     * The related person. Each FamilyHistory resource contains the entire family history for a single person.
     */
    protected List<FamilyHistoryRelationComponent> relation = new ArrayList<FamilyHistoryRelationComponent>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public String_ getNote() { 
      return this.note;
    }

    public void setNote(String_ value) { 
      this.note = value;
    }

    public String getNoteSimple() { 
      return this.note == null ? null : this.note.getValue();
    }

    public void setNoteSimple(String value) { 
      if (value == null)
        this.note = null;
      else {
        if (this.note == null)
          this.note = new String_();
        this.note.setValue(value);
      }
    }

    public List<FamilyHistoryRelationComponent> getRelation() { 
      return this.relation;
    }

    // syntactic sugar
    public FamilyHistoryRelationComponent addRelation() { 
      FamilyHistoryRelationComponent t = new FamilyHistoryRelationComponent();
      this.relation.add(t);
      return t;
    }

      public FamilyHistory copy() {
        FamilyHistory dst = new FamilyHistory();
        dst.subject = subject == null ? null : subject.copy();
        dst.note = note == null ? null : note.copy();
        dst.relation = new ArrayList<FamilyHistoryRelationComponent>();
        for (FamilyHistoryRelationComponent i : relation)
          dst.relation.add(i.copy(dst));
        return dst;
      }

      protected FamilyHistory typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.FamilyHistory;
   }


}

