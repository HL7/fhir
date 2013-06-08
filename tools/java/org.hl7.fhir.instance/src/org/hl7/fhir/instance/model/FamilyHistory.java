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

// Generated on Sat, Jun 8, 2013 18:38+1000 for FHIR v0.09

import java.util.*;

/**
 * Significant health events and conditions for people related to the subject relevant in the context of care for the subject
 */
public class FamilyHistory extends Resource {

    public class FamilyHistoryRelationComponent extends Element {
        /**
         * This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair"
         */
        private String_ name;

        /**
         * The type of relationship this person has to the patient (father, mother, brother etc.) At the moment this is a code linking to a fixed set of values. I'm not sure if there is an international standard for this. A fixed (possibly extensible) set of codes feels better than a codeable concept for somehting like this...
         */
        private CodeableConcept relationship;

        /**
         * If this resource is indicating that the related person is deceased, then an indicator of whether the person is deceased (yes) or not (no) or the age or age range or description of age at death - can be indicated here. If the reason for death is known, then it can be indicated in the outomce code of the condition - in this case the deceased property should still be set.
         */
        private Type deceased;

        /**
         * This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.
         */
        private String_ note;

        /**
         * The significant problemss (or condition) that the family member had. This is a repeating section to allow a system to represent more than one condition per resource, though there is nothing stopping multiple resources - one per condition.
         */
        private List<FamilyHistoryRelationConditionComponent> condition = new ArrayList<FamilyHistoryRelationConditionComponent>();

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

  }

    public class FamilyHistoryRelationConditionComponent extends Element {
        /**
         * The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system
         */
        private CodeableConcept type;

        /**
         * Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.
         */
        private CodeableConcept outcome;

        /**
         * Either the age of onset, range of approximate age or descriptive string can be recorded.
         */
        private Type onset;

        /**
         * An area where general notes can be placed about this specific condition.
         */
        private String_ note;

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

  }

    /**
     * The person who this history concerns
     */
    private ResourceReference subject;

    /**
     * The related person. Each FamilyHistory resource contains the entire family history for a single person.
     */
    private List<FamilyHistoryRelationComponent> relation = new ArrayList<FamilyHistoryRelationComponent>();

    /**
     * Conveys information about family history not specific to individual relations.
     */
    private String_ note;

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public List<FamilyHistoryRelationComponent> getRelation() { 
      return this.relation;
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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.FamilyHistory;
   }


}

