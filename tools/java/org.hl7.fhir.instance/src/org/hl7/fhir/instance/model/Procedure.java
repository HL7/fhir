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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * An action that is performed on a patient. This can be a physical 'thing' like an operation, or less invasive like counseling or hypnotherapy.
 */
public class Procedure extends Resource {

    public enum ProcedureRelationshipType {
        causedby, // This procedure had to be performed because of the related one.
        caused, // This procedure caused the related one to be performed.
        Null; // added to help the parsers
        public static ProcedureRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("caused-by".equals(codeString))
          return causedby;
        if ("caused".equals(codeString))
          return caused;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case causedby: return "caused-by";
            case caused: return "caused";
            default: return "?";
          }
        }
    }

  public class ProcedureRelationshipTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("caused-by".equals(codeString))
          return ProcedureRelationshipType.causedby;
        if ("caused".equals(codeString))
          return ProcedureRelationshipType.caused;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRelationshipType.causedby)
        return "caused-by";
      if (code == ProcedureRelationshipType.caused)
        return "caused";
      return "?";
      }
    }

    public class ProcedurePerformerComponent extends Element {
        /**
         * The practitioner who was involved in the procedure.
         */
        protected ResourceReference person;

        /**
         * E.g. surgeon, anaethetist, endoscopist.
         */
        protected CodeableConcept role;

        public ResourceReference getPerson() { 
          return this.person;
        }

        public void setPerson(ResourceReference value) { 
          this.person = value;
        }

        public CodeableConcept getRole() { 
          return this.role;
        }

        public void setRole(CodeableConcept value) { 
          this.role = value;
        }

      public ProcedurePerformerComponent copy(Procedure e) {
        ProcedurePerformerComponent dst = e.new ProcedurePerformerComponent();
        dst.person = person == null ? null : person.copy();
        dst.role = role == null ? null : role.copy();
        return dst;
      }

  }

    public class ProcedureRelatedItemComponent extends Element {
        /**
         * The nature of the relationship.
         */
        protected Enumeration<ProcedureRelationshipType> type;

        /**
         * The related item - e.g. a procedure.
         */
        protected ResourceReference target;

        public Enumeration<ProcedureRelationshipType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<ProcedureRelationshipType> value) { 
          this.type = value;
        }

        public ProcedureRelationshipType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(ProcedureRelationshipType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ProcedureRelationshipType>();
            this.type.setValue(value);
          }
        }

        public ResourceReference getTarget() { 
          return this.target;
        }

        public void setTarget(ResourceReference value) { 
          this.target = value;
        }

      public ProcedureRelatedItemComponent copy(Procedure e) {
        ProcedureRelatedItemComponent dst = e.new ProcedureRelatedItemComponent();
        dst.type = type == null ? null : type.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    /**
     * The person on whom the procedure was performed.
     */
    protected ResourceReference subject;

    /**
     * The specific procedure that is performed.
     */
    protected CodeableConcept type;

    /**
     * Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.
     */
    protected List<CodeableConcept> bodySite = new ArrayList<CodeableConcept>();

    /**
     * The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.
     */
    protected String_ indication;

    /**
     * Limited to 'real' people rather than equipment.
     */
    protected List<ProcedurePerformerComponent> performer = new ArrayList<ProcedurePerformerComponent>();

    /**
     * The dates over which the period was performed. Allows a period to support complex procedures that span more that one date, and also allows for the length of the procedure to be captured.
     */
    protected Period date;

    /**
     * The encounter during which the procedure was performed.
     */
    protected ResourceReference encounter;

    /**
     * What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    protected String_ outcome;

    /**
     * This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.
     */
    protected List<ResourceReference> report = new ArrayList<ResourceReference>();

    /**
     * Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.
     */
    protected String_ complication;

    /**
     * If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.
     */
    protected String_ followUp;

    /**
     * Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.
     */
    protected List<ProcedureRelatedItemComponent> relatedItem = new ArrayList<ProcedureRelatedItemComponent>();

    /**
     * Any other notes about the procedure - e.g. the operative notes.
     */
    protected String_ notes;

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public CodeableConcept getType() { 
      return this.type;
    }

    public void setType(CodeableConcept value) { 
      this.type = value;
    }

    public List<CodeableConcept> getBodySite() { 
      return this.bodySite;
    }

    // syntactic sugar
    public CodeableConcept addBodySite() { 
      CodeableConcept t = new CodeableConcept();
      this.bodySite.add(t);
      return t;
    }

    public String_ getIndication() { 
      return this.indication;
    }

    public void setIndication(String_ value) { 
      this.indication = value;
    }

    public String getIndicationSimple() { 
      return this.indication == null ? null : this.indication.getValue();
    }

    public void setIndicationSimple(String value) { 
      if (value == null)
        this.indication = null;
      else {
        if (this.indication == null)
          this.indication = new String_();
        this.indication.setValue(value);
      }
    }

    public List<ProcedurePerformerComponent> getPerformer() { 
      return this.performer;
    }

    // syntactic sugar
    public ProcedurePerformerComponent addPerformer() { 
      ProcedurePerformerComponent t = new ProcedurePerformerComponent();
      this.performer.add(t);
      return t;
    }

    public Period getDate() { 
      return this.date;
    }

    public void setDate(Period value) { 
      this.date = value;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public String_ getOutcome() { 
      return this.outcome;
    }

    public void setOutcome(String_ value) { 
      this.outcome = value;
    }

    public String getOutcomeSimple() { 
      return this.outcome == null ? null : this.outcome.getValue();
    }

    public void setOutcomeSimple(String value) { 
      if (value == null)
        this.outcome = null;
      else {
        if (this.outcome == null)
          this.outcome = new String_();
        this.outcome.setValue(value);
      }
    }

    public List<ResourceReference> getReport() { 
      return this.report;
    }

    // syntactic sugar
    public ResourceReference addReport() { 
      ResourceReference t = new ResourceReference();
      this.report.add(t);
      return t;
    }

    public String_ getComplication() { 
      return this.complication;
    }

    public void setComplication(String_ value) { 
      this.complication = value;
    }

    public String getComplicationSimple() { 
      return this.complication == null ? null : this.complication.getValue();
    }

    public void setComplicationSimple(String value) { 
      if (value == null)
        this.complication = null;
      else {
        if (this.complication == null)
          this.complication = new String_();
        this.complication.setValue(value);
      }
    }

    public String_ getFollowUp() { 
      return this.followUp;
    }

    public void setFollowUp(String_ value) { 
      this.followUp = value;
    }

    public String getFollowUpSimple() { 
      return this.followUp == null ? null : this.followUp.getValue();
    }

    public void setFollowUpSimple(String value) { 
      if (value == null)
        this.followUp = null;
      else {
        if (this.followUp == null)
          this.followUp = new String_();
        this.followUp.setValue(value);
      }
    }

    public List<ProcedureRelatedItemComponent> getRelatedItem() { 
      return this.relatedItem;
    }

    // syntactic sugar
    public ProcedureRelatedItemComponent addRelatedItem() { 
      ProcedureRelatedItemComponent t = new ProcedureRelatedItemComponent();
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

      public Procedure copy() {
        Procedure dst = new Procedure();
        dst.subject = subject == null ? null : subject.copy();
        dst.type = type == null ? null : type.copy();
        dst.bodySite = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : bodySite)
          dst.bodySite.add(i.copy());
        dst.indication = indication == null ? null : indication.copy();
        dst.performer = new ArrayList<ProcedurePerformerComponent>();
        for (ProcedurePerformerComponent i : performer)
          dst.performer.add(i.copy(dst));
        dst.date = date == null ? null : date.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.report = new ArrayList<ResourceReference>();
        for (ResourceReference i : report)
          dst.report.add(i.copy());
        dst.complication = complication == null ? null : complication.copy();
        dst.followUp = followUp == null ? null : followUp.copy();
        dst.relatedItem = new ArrayList<ProcedureRelatedItemComponent>();
        for (ProcedureRelatedItemComponent i : relatedItem)
          dst.relatedItem.add(i.copy(dst));
        dst.notes = notes == null ? null : notes.copy();
        return dst;
      }

      protected Procedure typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Procedure;
   }


}

