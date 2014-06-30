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
 * Significant health events and conditions for people related to the subject relevant in the context of care for the subject.
 */
public class FamilyHistory extends Resource {

    public static class FamilyHistoryRelationComponent extends BackboneElement {
        /**
         * This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".
         */
        protected String_ name;

        /**
         * The type of relationship this person has to the patient (father, mother, brother etc.).
         */
        protected CodeableConcept relationship;

        /**
         * The actual or approximate date of birth of the relative.
         */
        protected Type born;

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

        private static final long serialVersionUID = 482918149L;

      public FamilyHistoryRelationComponent() {
        super();
      }

      public FamilyHistoryRelationComponent(CodeableConcept relationship) {
        super();
        this.relationship = relationship;
      }

        /**
         * @return {@link #name} (This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".)
         */
        public FamilyHistoryRelationComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value This will either be a name or a description.  E.g. "Aunt Susan", "my cousin with the red hair".
         */
        public FamilyHistoryRelationComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #relationship} (The type of relationship this person has to the patient (father, mother, brother etc.).)
         */
        public CodeableConcept getRelationship() { 
          return this.relationship;
        }

        /**
         * @param value {@link #relationship} (The type of relationship this person has to the patient (father, mother, brother etc.).)
         */
        public FamilyHistoryRelationComponent setRelationship(CodeableConcept value) { 
          this.relationship = value;
          return this;
        }

        /**
         * @return {@link #born} (The actual or approximate date of birth of the relative.)
         */
        public Type getBorn() { 
          return this.born;
        }

        /**
         * @param value {@link #born} (The actual or approximate date of birth of the relative.)
         */
        public FamilyHistoryRelationComponent setBorn(Type value) { 
          this.born = value;
          return this;
        }

        /**
         * @return {@link #deceased} (If this resource is indicating that the related person is deceased, then an indicator of whether the person is deceased (yes) or not (no) or the age or age range or description of age at death - can be indicated here. If the reason for death is known, then it can be indicated in the outcome code of the condition - in this case the deceased property should still be set.)
         */
        public Type getDeceased() { 
          return this.deceased;
        }

        /**
         * @param value {@link #deceased} (If this resource is indicating that the related person is deceased, then an indicator of whether the person is deceased (yes) or not (no) or the age or age range or description of age at death - can be indicated here. If the reason for death is known, then it can be indicated in the outcome code of the condition - in this case the deceased property should still be set.)
         */
        public FamilyHistoryRelationComponent setDeceased(Type value) { 
          this.deceased = value;
          return this;
        }

        /**
         * @return {@link #note} (This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.)
         */
        public String_ getNote() { 
          return this.note;
        }

        /**
         * @param value {@link #note} (This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.)
         */
        public FamilyHistoryRelationComponent setNote(String_ value) { 
          this.note = value;
          return this;
        }

        /**
         * @return This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.
         */
        public String getNoteSimple() { 
          return this.note == null ? null : this.note.getValue();
        }

        /**
         * @param value This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.
         */
        public FamilyHistoryRelationComponent setNoteSimple(String value) { 
          if (value == null)
            this.note = null;
          else {
            if (this.note == null)
              this.note = new String_();
            this.note.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #condition} (The significant Conditions (or condition) that the family member had. This is a repeating section to allow a system to represent more than one condition per resource, though there is nothing stopping multiple resources - one per condition.)
         */
        public List<FamilyHistoryRelationConditionComponent> getCondition() { 
          return this.condition;
        }

    // syntactic sugar
        /**
         * @return {@link #condition} (The significant Conditions (or condition) that the family member had. This is a repeating section to allow a system to represent more than one condition per resource, though there is nothing stopping multiple resources - one per condition.)
         */
        public FamilyHistoryRelationConditionComponent addCondition() { 
          FamilyHistoryRelationConditionComponent t = new FamilyHistoryRelationConditionComponent();
          this.condition.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "This will either be a name or a description.  E.g. 'Aunt Susan', 'my cousin with the red hair'.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("relationship", "CodeableConcept", "The type of relationship this person has to the patient (father, mother, brother etc.).", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("born[x]", "Period|date|string", "The actual or approximate date of birth of the relative.", 0, java.lang.Integer.MAX_VALUE, born));
          childrenList.add(new Property("deceased[x]", "boolean|Age|Range|date|string", "If this resource is indicating that the related person is deceased, then an indicator of whether the person is deceased (yes) or not (no) or the age or age range or description of age at death - can be indicated here. If the reason for death is known, then it can be indicated in the outcome code of the condition - in this case the deceased property should still be set.", 0, java.lang.Integer.MAX_VALUE, deceased));
          childrenList.add(new Property("note", "string", "This property allows a non condition-specific note to the made about the related person. Ideally, the note would be in the condition property, but this is not always possible.", 0, java.lang.Integer.MAX_VALUE, note));
          childrenList.add(new Property("condition", "", "The significant Conditions (or condition) that the family member had. This is a repeating section to allow a system to represent more than one condition per resource, though there is nothing stopping multiple resources - one per condition.", 0, java.lang.Integer.MAX_VALUE, condition));
        }

      public FamilyHistoryRelationComponent copy() {
        FamilyHistoryRelationComponent dst = new FamilyHistoryRelationComponent();
        dst.name = name == null ? null : name.copy();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.born = born == null ? null : born.copy();
        dst.deceased = deceased == null ? null : deceased.copy();
        dst.note = note == null ? null : note.copy();
        dst.condition = new ArrayList<FamilyHistoryRelationConditionComponent>();
        for (FamilyHistoryRelationConditionComponent i : condition)
          dst.condition.add(i.copy());
        return dst;
      }

  }

    public static class FamilyHistoryRelationConditionComponent extends BackboneElement {
        /**
         * The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system.
         */
        protected CodeableConcept type;

        /**
         * Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.
         */
        protected CodeableConcept outcome;

        /**
         * Either the age of onset, range of approximate age or descriptive string can be recorded.  For conditions with multiple occurrences, this describes the first known occurrence.
         */
        protected Type onset;

        /**
         * An area where general notes can be placed about this specific condition.
         */
        protected String_ note;

        private static final long serialVersionUID = 196636125L;

      public FamilyHistoryRelationConditionComponent() {
        super();
      }

      public FamilyHistoryRelationConditionComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system.)
         */
        public FamilyHistoryRelationConditionComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #outcome} (Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.)
         */
        public CodeableConcept getOutcome() { 
          return this.outcome;
        }

        /**
         * @param value {@link #outcome} (Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.)
         */
        public FamilyHistoryRelationConditionComponent setOutcome(CodeableConcept value) { 
          this.outcome = value;
          return this;
        }

        /**
         * @return {@link #onset} (Either the age of onset, range of approximate age or descriptive string can be recorded.  For conditions with multiple occurrences, this describes the first known occurrence.)
         */
        public Type getOnset() { 
          return this.onset;
        }

        /**
         * @param value {@link #onset} (Either the age of onset, range of approximate age or descriptive string can be recorded.  For conditions with multiple occurrences, this describes the first known occurrence.)
         */
        public FamilyHistoryRelationConditionComponent setOnset(Type value) { 
          this.onset = value;
          return this;
        }

        /**
         * @return {@link #note} (An area where general notes can be placed about this specific condition.)
         */
        public String_ getNote() { 
          return this.note;
        }

        /**
         * @param value {@link #note} (An area where general notes can be placed about this specific condition.)
         */
        public FamilyHistoryRelationConditionComponent setNote(String_ value) { 
          this.note = value;
          return this;
        }

        /**
         * @return An area where general notes can be placed about this specific condition.
         */
        public String getNoteSimple() { 
          return this.note == null ? null : this.note.getValue();
        }

        /**
         * @param value An area where general notes can be placed about this specific condition.
         */
        public FamilyHistoryRelationConditionComponent setNoteSimple(String value) { 
          if (value == null)
            this.note = null;
          else {
            if (this.note == null)
              this.note = new String_();
            this.note.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "The actual condition specified. Could be a coded condition (like MI or Diabetes) or a less specific string like 'cancer' depending on how much is known about the condition and the capabilities of the creating system.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("outcome", "CodeableConcept", "Indicates what happened as a result of this condition.  If the condition resulted in death, deceased date is captured on the relation.", 0, java.lang.Integer.MAX_VALUE, outcome));
          childrenList.add(new Property("onset[x]", "Age|Range|string", "Either the age of onset, range of approximate age or descriptive string can be recorded.  For conditions with multiple occurrences, this describes the first known occurrence.", 0, java.lang.Integer.MAX_VALUE, onset));
          childrenList.add(new Property("note", "string", "An area where general notes can be placed about this specific condition.", 0, java.lang.Integer.MAX_VALUE, note));
        }

      public FamilyHistoryRelationConditionComponent copy() {
        FamilyHistoryRelationConditionComponent dst = new FamilyHistoryRelationConditionComponent();
        dst.type = type == null ? null : type.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.onset = onset == null ? null : onset.copy();
        dst.note = note == null ? null : note.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this family history record that are defined by business processes and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The person who this history concerns.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The person who this history concerns.)
     */
    protected Patient subjectTarget;

    /**
     * The date (and possibly time) when the family history was taken.
     */
    protected DateTime date;

    /**
     * Conveys information about family history not specific to individual relations.
     */
    protected String_ note;

    /**
     * The related person. Each FamilyHistory resource contains the entire family history for a single person.
     */
    protected List<FamilyHistoryRelationComponent> relation = new ArrayList<FamilyHistoryRelationComponent>();

    private static final long serialVersionUID = -851296977L;

    public FamilyHistory() {
      super();
    }

    public FamilyHistory(ResourceReference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this family history record that are defined by business processes and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this family history record that are defined by business processes and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The person who this history concerns.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The person who this history concerns.)
     */
    public FamilyHistory setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The person who this history concerns.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The person who this history concerns.)
     */
    public FamilyHistory setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #date} (The date (and possibly time) when the family history was taken.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date (and possibly time) when the family history was taken.)
     */
    public FamilyHistory setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date (and possibly time) when the family history was taken.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date (and possibly time) when the family history was taken.
     */
    public FamilyHistory setDateSimple(DateAndTime value) { 
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
     * @return {@link #note} (Conveys information about family history not specific to individual relations.)
     */
    public String_ getNote() { 
      return this.note;
    }

    /**
     * @param value {@link #note} (Conveys information about family history not specific to individual relations.)
     */
    public FamilyHistory setNote(String_ value) { 
      this.note = value;
      return this;
    }

    /**
     * @return Conveys information about family history not specific to individual relations.
     */
    public String getNoteSimple() { 
      return this.note == null ? null : this.note.getValue();
    }

    /**
     * @param value Conveys information about family history not specific to individual relations.
     */
    public FamilyHistory setNoteSimple(String value) { 
      if (value == null)
        this.note = null;
      else {
        if (this.note == null)
          this.note = new String_();
        this.note.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #relation} (The related person. Each FamilyHistory resource contains the entire family history for a single person.)
     */
    public List<FamilyHistoryRelationComponent> getRelation() { 
      return this.relation;
    }

    // syntactic sugar
    /**
     * @return {@link #relation} (The related person. Each FamilyHistory resource contains the entire family history for a single person.)
     */
    public FamilyHistoryRelationComponent addRelation() { 
      FamilyHistoryRelationComponent t = new FamilyHistoryRelationComponent();
      this.relation.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this family history record that are defined by business processes and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient)", "The person who this history concerns.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("date", "dateTime", "The date (and possibly time) when the family history was taken.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("note", "string", "Conveys information about family history not specific to individual relations.", 0, java.lang.Integer.MAX_VALUE, note));
        childrenList.add(new Property("relation", "", "The related person. Each FamilyHistory resource contains the entire family history for a single person.", 0, java.lang.Integer.MAX_VALUE, relation));
      }

      public FamilyHistory copy() {
        FamilyHistory dst = new FamilyHistory();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.date = date == null ? null : date.copy();
        dst.note = note == null ? null : note.copy();
        dst.relation = new ArrayList<FamilyHistoryRelationComponent>();
        for (FamilyHistoryRelationComponent i : relation)
          dst.relation.add(i.copy());
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

