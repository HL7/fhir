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
 * An action that is performed on a patient. This can be a physical 'thing' like an operation, or less invasive like counseling or hypnotherapy.
 */
public class Procedure extends Resource {

    public enum ProcedureRelationshipType {
        causedby, // This procedure had to be performed because of the related one.
        becauseof, // This procedure caused the related one to be performed.
        Null; // added to help the parsers
        public static ProcedureRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("caused-by".equals(codeString))
          return causedby;
        if ("because-of".equals(codeString))
          return becauseof;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case causedby: return "caused-by";
            case becauseof: return "because-of";
            default: return "?";
          }
        }
    }

  public static class ProcedureRelationshipTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("caused-by".equals(codeString))
          return ProcedureRelationshipType.causedby;
        if ("because-of".equals(codeString))
          return ProcedureRelationshipType.becauseof;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRelationshipType.causedby)
        return "caused-by";
      if (code == ProcedureRelationshipType.becauseof)
        return "because-of";
      return "?";
      }
    }

    public static class ProcedurePerformerComponent extends BackboneElement {
        /**
         * The practitioner who was involved in the procedure.
         */
        protected ResourceReference person;

        /**
         * The actual object that is the target of the reference (The practitioner who was involved in the procedure.)
         */
        protected Practitioner personTarget;

        /**
         * E.g. surgeon, anaethetist, endoscopist.
         */
        protected CodeableConcept role;

        private static final long serialVersionUID = -1680776731L;

      public ProcedurePerformerComponent() {
        super();
      }

        /**
         * @return {@link #person} (The practitioner who was involved in the procedure.)
         */
        public ResourceReference getPerson() { 
          return this.person;
        }

        /**
         * @param value {@link #person} (The practitioner who was involved in the procedure.)
         */
        public ProcedurePerformerComponent setPerson(ResourceReference value) { 
          this.person = value;
          return this;
        }

        /**
         * @return {@link #person} (The actual object that is the target of the reference. The practitioner who was involved in the procedure.)
         */
        public Practitioner getPersonTarget() { 
          return this.personTarget;
        }

        /**
         * @param value {@link #person} (The actual object that is the target of the reference. The practitioner who was involved in the procedure.)
         */
        public ProcedurePerformerComponent setPersonTarget(Practitioner value) { 
          this.personTarget = value;
          return this;
        }

        /**
         * @return {@link #role} (E.g. surgeon, anaethetist, endoscopist.)
         */
        public CodeableConcept getRole() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (E.g. surgeon, anaethetist, endoscopist.)
         */
        public ProcedurePerformerComponent setRole(CodeableConcept value) { 
          this.role = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("person", "Resource(Practitioner)", "The practitioner who was involved in the procedure.", 0, java.lang.Integer.MAX_VALUE, person));
          childrenList.add(new Property("role", "CodeableConcept", "E.g. surgeon, anaethetist, endoscopist.", 0, java.lang.Integer.MAX_VALUE, role));
        }

      public ProcedurePerformerComponent copy() {
        ProcedurePerformerComponent dst = new ProcedurePerformerComponent();
        dst.person = person == null ? null : person.copy();
        dst.role = role == null ? null : role.copy();
        return dst;
      }

  }

    public static class ProcedureRelatedItemComponent extends BackboneElement {
        /**
         * The nature of the relationship.
         */
        protected Enumeration<ProcedureRelationshipType> type;

        /**
         * The related item - e.g. a procedure.
         */
        protected ResourceReference target;

        /**
         * The actual object that is the target of the reference (The related item - e.g. a procedure.)
         */
        protected Resource targetTarget;

        private static final long serialVersionUID = 829932234L;

      public ProcedureRelatedItemComponent() {
        super();
      }

        /**
         * @return {@link #type} (The nature of the relationship.)
         */
        public Enumeration<ProcedureRelationshipType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The nature of the relationship.)
         */
        public ProcedureRelatedItemComponent setType(Enumeration<ProcedureRelationshipType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The nature of the relationship.
         */
        public ProcedureRelationshipType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The nature of the relationship.
         */
        public ProcedureRelatedItemComponent setTypeSimple(ProcedureRelationshipType value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Enumeration<ProcedureRelationshipType>();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (The related item - e.g. a procedure.)
         */
        public ResourceReference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (The related item - e.g. a procedure.)
         */
        public ProcedureRelatedItemComponent setTarget(ResourceReference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} (The actual object that is the target of the reference. The related item - e.g. a procedure.)
         */
        public Resource getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} (The actual object that is the target of the reference. The related item - e.g. a procedure.)
         */
        public ProcedureRelatedItemComponent setTargetTarget(Resource value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The nature of the relationship.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("target", "Resource(AdverseReaction|AllergyIntolerance|CarePlan|Condition|DeviceObservationReport|DiagnosticReport|FamilyHistory|ImagingStudy|Immunization|ImmunizationRecommendation|MedicationAdministration|MedicationDispense|MedicationPrescription|MedicationStatement|Observation|Procedure)", "The related item - e.g. a procedure.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ProcedureRelatedItemComponent copy() {
        ProcedureRelatedItemComponent dst = new ProcedureRelatedItemComponent();
        dst.type = type == null ? null : type.copy();
        dst.target = target == null ? null : target.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The person on whom the procedure was performed.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (The person on whom the procedure was performed.)
     */
    protected Patient subjectTarget;

    /**
     * The specific procedure that is performed. Use text if the exact nature of the procedure can't be coded.
     */
    protected CodeableConcept type;

    /**
     * Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.
     */
    protected List<CodeableConcept> bodySite = new ArrayList<CodeableConcept>();

    /**
     * The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.
     */
    protected List<CodeableConcept> indication = new ArrayList<CodeableConcept>();

    /**
     * Limited to 'real' people rather than equipment.
     */
    protected List<ProcedurePerformerComponent> performer = new ArrayList<ProcedurePerformerComponent>();

    /**
     * The dates over which the procedure was performed. Allows a period to support complex procedures that span more than one date, and also allows for the length of the procedure to be captured.
     */
    protected Period date;

    /**
     * The encounter during which the procedure was performed.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (The encounter during which the procedure was performed.)
     */
    protected Encounter encounterTarget;

    /**
     * What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    protected String_ outcome;

    /**
     * This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.
     */
    protected List<ResourceReference> report = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    protected List<DiagnosticReport> reportTarget = new ArrayList<DiagnosticReport>();


    /**
     * Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.
     */
    protected List<CodeableConcept> complication = new ArrayList<CodeableConcept>();

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

    private static final long serialVersionUID = 1314586946L;

    public Procedure() {
      super();
    }

    public Procedure(ResourceReference subject, CodeableConcept type) {
      super();
      this.subject = subject;
      this.type = type;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The person on whom the procedure was performed.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The person on whom the procedure was performed.)
     */
    public Procedure setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. The person on whom the procedure was performed.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. The person on whom the procedure was performed.)
     */
    public Procedure setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #type} (The specific procedure that is performed. Use text if the exact nature of the procedure can't be coded.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The specific procedure that is performed. Use text if the exact nature of the procedure can't be coded.)
     */
    public Procedure setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #bodySite} (Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.)
     */
    public List<CodeableConcept> getBodySite() { 
      return this.bodySite;
    }

    // syntactic sugar
    /**
     * @return {@link #bodySite} (Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.)
     */
    public CodeableConcept addBodySite() { 
      CodeableConcept t = new CodeableConcept();
      this.bodySite.add(t);
      return t;
    }

    /**
     * @return {@link #indication} (The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.)
     */
    public List<CodeableConcept> getIndication() { 
      return this.indication;
    }

    // syntactic sugar
    /**
     * @return {@link #indication} (The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.)
     */
    public CodeableConcept addIndication() { 
      CodeableConcept t = new CodeableConcept();
      this.indication.add(t);
      return t;
    }

    /**
     * @return {@link #performer} (Limited to 'real' people rather than equipment.)
     */
    public List<ProcedurePerformerComponent> getPerformer() { 
      return this.performer;
    }

    // syntactic sugar
    /**
     * @return {@link #performer} (Limited to 'real' people rather than equipment.)
     */
    public ProcedurePerformerComponent addPerformer() { 
      ProcedurePerformerComponent t = new ProcedurePerformerComponent();
      this.performer.add(t);
      return t;
    }

    /**
     * @return {@link #date} (The dates over which the procedure was performed. Allows a period to support complex procedures that span more than one date, and also allows for the length of the procedure to be captured.)
     */
    public Period getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The dates over which the procedure was performed. Allows a period to support complex procedures that span more than one date, and also allows for the length of the procedure to be captured.)
     */
    public Procedure setDate(Period value) { 
      this.date = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The encounter during which the procedure was performed.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The encounter during which the procedure was performed.)
     */
    public Procedure setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. The encounter during which the procedure was performed.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. The encounter during which the procedure was performed.)
     */
    public Procedure setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #outcome} (What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.)
     */
    public String_ getOutcome() { 
      return this.outcome;
    }

    /**
     * @param value {@link #outcome} (What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.)
     */
    public Procedure setOutcome(String_ value) { 
      this.outcome = value;
      return this;
    }

    /**
     * @return What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    public String getOutcomeSimple() { 
      return this.outcome == null ? null : this.outcome.getValue();
    }

    /**
     * @param value What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    public Procedure setOutcomeSimple(String value) { 
      if (value == null)
        this.outcome = null;
      else {
        if (this.outcome == null)
          this.outcome = new String_();
        this.outcome.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #report} (This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public List<ResourceReference> getReport() { 
      return this.report;
    }

    // syntactic sugar
    /**
     * @return {@link #report} (This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public ResourceReference addReport() { 
      ResourceReference t = new ResourceReference();
      this.report.add(t);
      return t;
    }

    /**
     * @return {@link #report} (The actual objects that are the target of the reference. This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public List<DiagnosticReport> getReportTarget() { 
      return this.reportTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #report} (Add an actual object that is the target of the reference. This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public DiagnosticReport addReportTarget() { 
      DiagnosticReport r = new DiagnosticReport();
      this.reportTarget.add(r);
      return r;
    }

    /**
     * @return {@link #complication} (Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.)
     */
    public List<CodeableConcept> getComplication() { 
      return this.complication;
    }

    // syntactic sugar
    /**
     * @return {@link #complication} (Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.)
     */
    public CodeableConcept addComplication() { 
      CodeableConcept t = new CodeableConcept();
      this.complication.add(t);
      return t;
    }

    /**
     * @return {@link #followUp} (If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.)
     */
    public String_ getFollowUp() { 
      return this.followUp;
    }

    /**
     * @param value {@link #followUp} (If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.)
     */
    public Procedure setFollowUp(String_ value) { 
      this.followUp = value;
      return this;
    }

    /**
     * @return If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.
     */
    public String getFollowUpSimple() { 
      return this.followUp == null ? null : this.followUp.getValue();
    }

    /**
     * @param value If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.
     */
    public Procedure setFollowUpSimple(String value) { 
      if (value == null)
        this.followUp = null;
      else {
        if (this.followUp == null)
          this.followUp = new String_();
        this.followUp.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #relatedItem} (Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.)
     */
    public List<ProcedureRelatedItemComponent> getRelatedItem() { 
      return this.relatedItem;
    }

    // syntactic sugar
    /**
     * @return {@link #relatedItem} (Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.)
     */
    public ProcedureRelatedItemComponent addRelatedItem() { 
      ProcedureRelatedItemComponent t = new ProcedureRelatedItemComponent();
      this.relatedItem.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Any other notes about the procedure - e.g. the operative notes.)
     */
    public String_ getNotes() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (Any other notes about the procedure - e.g. the operative notes.)
     */
    public Procedure setNotes(String_ value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return Any other notes about the procedure - e.g. the operative notes.
     */
    public String getNotesSimple() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value Any other notes about the procedure - e.g. the operative notes.
     */
    public Procedure setNotesSimple(String value) { 
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
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Resource(Patient)", "The person on whom the procedure was performed.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("type", "CodeableConcept", "The specific procedure that is performed. Use text if the exact nature of the procedure can't be coded.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("bodySite", "CodeableConcept", "Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("indication", "CodeableConcept", "The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("performer", "", "Limited to 'real' people rather than equipment.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("date", "Period", "The dates over which the procedure was performed. Allows a period to support complex procedures that span more than one date, and also allows for the length of the procedure to be captured.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "The encounter during which the procedure was performed.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("outcome", "string", "What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.", 0, java.lang.Integer.MAX_VALUE, outcome));
        childrenList.add(new Property("report", "Resource(DiagnosticReport)", "This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.", 0, java.lang.Integer.MAX_VALUE, report));
        childrenList.add(new Property("complication", "CodeableConcept", "Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.", 0, java.lang.Integer.MAX_VALUE, complication));
        childrenList.add(new Property("followUp", "string", "If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.", 0, java.lang.Integer.MAX_VALUE, followUp));
        childrenList.add(new Property("relatedItem", "", "Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.", 0, java.lang.Integer.MAX_VALUE, relatedItem));
        childrenList.add(new Property("notes", "string", "Any other notes about the procedure - e.g. the operative notes.", 0, java.lang.Integer.MAX_VALUE, notes));
      }

      public Procedure copy() {
        Procedure dst = new Procedure();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.type = type == null ? null : type.copy();
        dst.bodySite = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : bodySite)
          dst.bodySite.add(i.copy());
        dst.indication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : indication)
          dst.indication.add(i.copy());
        dst.performer = new ArrayList<ProcedurePerformerComponent>();
        for (ProcedurePerformerComponent i : performer)
          dst.performer.add(i.copy());
        dst.date = date == null ? null : date.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.report = new ArrayList<ResourceReference>();
        for (ResourceReference i : report)
          dst.report.add(i.copy());
        dst.complication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : complication)
          dst.complication.add(i.copy());
        dst.followUp = followUp == null ? null : followUp.copy();
        dst.relatedItem = new ArrayList<ProcedureRelatedItemComponent>();
        for (ProcedureRelatedItemComponent i : relatedItem)
          dst.relatedItem.add(i.copy());
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

