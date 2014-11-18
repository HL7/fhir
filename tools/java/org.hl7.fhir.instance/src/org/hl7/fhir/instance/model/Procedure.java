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
 * An action that is performed on a patient. This can be a physical 'thing' like an operation, or less invasive like counseling or hypnotherapy.
 */
public class Procedure extends DomainResource {

    public enum ProcedureRelationshipType {
        CAUSEDBY, // This procedure had to be performed because of the related one.
        BECAUSEOF, // This procedure caused the related one to be performed.
        NULL; // added to help the parsers
        public static ProcedureRelationshipType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("caused-by".equals(codeString))
          return CAUSEDBY;
        if ("because-of".equals(codeString))
          return BECAUSEOF;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CAUSEDBY: return "caused-by";
            case BECAUSEOF: return "because-of";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case CAUSEDBY: return "This procedure had to be performed because of the related one.";
            case BECAUSEOF: return "This procedure caused the related one to be performed.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CAUSEDBY: return "caused-by";
            case BECAUSEOF: return "because-of";
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
          return ProcedureRelationshipType.CAUSEDBY;
        if ("because-of".equals(codeString))
          return ProcedureRelationshipType.BECAUSEOF;
        throw new Exception("Unknown ProcedureRelationshipType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRelationshipType.CAUSEDBY)
        return "caused-by";
      if (code == ProcedureRelationshipType.BECAUSEOF)
        return "because-of";
      return "?";
      }
    }

    public static class ProcedurePerformerComponent extends BackboneElement {
        /**
         * The practitioner who was involved in the procedure.
         */
        protected Reference person;

        /**
         * The actual object that is the target of the reference (The practitioner who was involved in the procedure.)
         */
        protected Practitioner personTarget;

        /**
         * E.g. surgeon, anaethetist, endoscopist.
         */
        protected CodeableConcept role;

        private static final long serialVersionUID = -749890249L;

      public ProcedurePerformerComponent() {
        super();
      }

        /**
         * @return {@link #person} (The practitioner who was involved in the procedure.)
         */
        public Reference getPerson() { 
          return this.person;
        }

        /**
         * @param value {@link #person} (The practitioner who was involved in the procedure.)
         */
        public ProcedurePerformerComponent setPerson(Reference value) { 
          this.person = value;
          return this;
        }

        /**
         * @return {@link #person} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The practitioner who was involved in the procedure.)
         */
        public Practitioner getPersonTarget() { 
          return this.personTarget;
        }

        /**
         * @param value {@link #person} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The practitioner who was involved in the procedure.)
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
          childrenList.add(new Property("person", "Reference(Practitioner)", "The practitioner who was involved in the procedure.", 0, java.lang.Integer.MAX_VALUE, person));
          childrenList.add(new Property("role", "CodeableConcept", "E.g. surgeon, anaethetist, endoscopist.", 0, java.lang.Integer.MAX_VALUE, role));
        }

      public ProcedurePerformerComponent copy() {
        ProcedurePerformerComponent dst = new ProcedurePerformerComponent();
        copyValues(dst);
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
        protected Reference target;

        /**
         * The actual object that is the target of the reference (The related item - e.g. a procedure.)
         */
        protected Resource targetTarget;

        private static final long serialVersionUID = 41929784L;

      public ProcedureRelatedItemComponent() {
        super();
      }

        /**
         * @return {@link #type} (The nature of the relationship.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<ProcedureRelationshipType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The nature of the relationship.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ProcedureRelatedItemComponent setTypeElement(Enumeration<ProcedureRelationshipType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The nature of the relationship.
         */
        public ProcedureRelationshipType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The nature of the relationship.
         */
        public ProcedureRelatedItemComponent setType(ProcedureRelationshipType value) { 
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
        public Reference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (The related item - e.g. a procedure.)
         */
        public ProcedureRelatedItemComponent setTarget(Reference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The related item - e.g. a procedure.)
         */
        public Resource getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The related item - e.g. a procedure.)
         */
        public ProcedureRelatedItemComponent setTargetTarget(Resource value) { 
          this.targetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The nature of the relationship.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("target", "Reference(AllergyIntolerance|CarePlan|Condition|DeviceObservationReport|DiagnosticReport|FamilyHistory|ImagingStudy|Immunization|ImmunizationRecommendation|MedicationAdministration|MedicationDispense|MedicationPrescription|MedicationStatement|Observation|Procedure)", "The related item - e.g. a procedure.", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ProcedureRelatedItemComponent copy() {
        ProcedureRelatedItemComponent dst = new ProcedureRelatedItemComponent();
        copyValues(dst);
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
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The person on whom the procedure was performed.)
     */
    protected Patient patientTarget;

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
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The encounter during which the procedure was performed.)
     */
    protected Encounter encounterTarget;

    /**
     * What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    protected StringType outcome;

    /**
     * This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.
     */
    protected List<Reference> report = new ArrayList<Reference>();
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
    protected StringType followUp;

    /**
     * Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.
     */
    protected List<ProcedureRelatedItemComponent> relatedItem = new ArrayList<ProcedureRelatedItemComponent>();

    /**
     * Any other notes about the procedure - e.g. the operative notes.
     */
    protected StringType notes;

    private static final long serialVersionUID = -1009620347L;

    public Procedure() {
      super();
    }

    public Procedure(Reference patient, CodeableConcept type) {
      super();
      this.patient = patient;
      this.type = type;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #patient} (The person on whom the procedure was performed.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (The person on whom the procedure was performed.)
     */
    public Procedure setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person on whom the procedure was performed.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person on whom the procedure was performed.)
     */
    public Procedure setPatientTarget(Patient value) { 
      this.patientTarget = value;
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

    /**
     * @return {@link #bodySite} (Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.)
     */
    // syntactic sugar
    public CodeableConcept addBodySite() { //3
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

    /**
     * @return {@link #indication} (The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
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

    /**
     * @return {@link #performer} (Limited to 'real' people rather than equipment.)
     */
    // syntactic sugar
    public ProcedurePerformerComponent addPerformer() { //3
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
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The encounter during which the procedure was performed.)
     */
    public Procedure setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The encounter during which the procedure was performed.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The encounter during which the procedure was performed.)
     */
    public Procedure setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #outcome} (What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
     */
    public StringType getOutcomeElement() { 
      return this.outcome;
    }

    /**
     * @param value {@link #outcome} (What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
     */
    public Procedure setOutcomeElement(StringType value) { 
      this.outcome = value;
      return this;
    }

    /**
     * @return What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    public String getOutcome() { 
      return this.outcome == null ? null : this.outcome.getValue();
    }

    /**
     * @param value What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.
     */
    public Procedure setOutcome(String value) { 
      if (Utilities.noString(value))
        this.outcome = null;
      else {
        if (this.outcome == null)
          this.outcome = new StringType();
        this.outcome.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #report} (This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public List<Reference> getReport() { 
      return this.report;
    }

    /**
     * @return {@link #report} (This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    // syntactic sugar
    public Reference addReport() { //3
      Reference t = new Reference();
      this.report.add(t);
      return t;
    }

    /**
     * @return {@link #report} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
     */
    public List<DiagnosticReport> getReportTarget() { 
      return this.reportTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #report} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.)
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

    /**
     * @return {@link #complication} (Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.)
     */
    // syntactic sugar
    public CodeableConcept addComplication() { //3
      CodeableConcept t = new CodeableConcept();
      this.complication.add(t);
      return t;
    }

    /**
     * @return {@link #followUp} (If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.). This is the underlying object with id, value and extensions. The accessor "getFollowUp" gives direct access to the value
     */
    public StringType getFollowUpElement() { 
      return this.followUp;
    }

    /**
     * @param value {@link #followUp} (If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.). This is the underlying object with id, value and extensions. The accessor "getFollowUp" gives direct access to the value
     */
    public Procedure setFollowUpElement(StringType value) { 
      this.followUp = value;
      return this;
    }

    /**
     * @return If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.
     */
    public String getFollowUp() { 
      return this.followUp == null ? null : this.followUp.getValue();
    }

    /**
     * @param value If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.
     */
    public Procedure setFollowUp(String value) { 
      if (Utilities.noString(value))
        this.followUp = null;
      else {
        if (this.followUp == null)
          this.followUp = new StringType();
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

    /**
     * @return {@link #relatedItem} (Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.)
     */
    // syntactic sugar
    public ProcedureRelatedItemComponent addRelatedItem() { //3
      ProcedureRelatedItemComponent t = new ProcedureRelatedItemComponent();
      this.relatedItem.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Any other notes about the procedure - e.g. the operative notes.). This is the underlying object with id, value and extensions. The accessor "getNotes" gives direct access to the value
     */
    public StringType getNotesElement() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (Any other notes about the procedure - e.g. the operative notes.). This is the underlying object with id, value and extensions. The accessor "getNotes" gives direct access to the value
     */
    public Procedure setNotesElement(StringType value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return Any other notes about the procedure - e.g. the operative notes.
     */
    public String getNotes() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value Any other notes about the procedure - e.g. the operative notes.
     */
    public Procedure setNotes(String value) { 
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
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this procedure that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("patient", "Reference(Patient)", "The person on whom the procedure was performed.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("type", "CodeableConcept", "The specific procedure that is performed. Use text if the exact nature of the procedure can't be coded.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("bodySite", "CodeableConcept", "Detailed and structured anatomical location information. Multiple locations are allowed - e.g. multiple punch biopsies of a lesion.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("indication", "CodeableConcept", "The reason why the procedure was performed. This may be due to a Condition, may be coded entity of some type, or may simply be present as text.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("performer", "", "Limited to 'real' people rather than equipment.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("date", "Period", "The dates over which the procedure was performed. Allows a period to support complex procedures that span more than one date, and also allows for the length of the procedure to be captured.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The encounter during which the procedure was performed.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("outcome", "string", "What was the outcome of the procedure - did it resolve reasons why the procedure was performed?.", 0, java.lang.Integer.MAX_VALUE, outcome));
        childrenList.add(new Property("report", "Reference(DiagnosticReport)", "This could be a histology result. There could potentially be multiple reports - e.g. if this was a procedure that made multiple biopsies.", 0, java.lang.Integer.MAX_VALUE, report));
        childrenList.add(new Property("complication", "CodeableConcept", "Any complications that occurred during the procedure, or in the immediate post-operative period. These are generally tracked separately from the notes, which typically will describe the procedure itself rather than any 'post procedure' issues.", 0, java.lang.Integer.MAX_VALUE, complication));
        childrenList.add(new Property("followUp", "string", "If the procedure required specific follow up - e.g. removal of sutures. The followup may be represented as a simple note, or potentially could be more complex in which case the CarePlan resource can be used.", 0, java.lang.Integer.MAX_VALUE, followUp));
        childrenList.add(new Property("relatedItem", "", "Procedures may be related to other items such as procedures or medications. For example treating wound dehiscence following a previous procedure.", 0, java.lang.Integer.MAX_VALUE, relatedItem));
        childrenList.add(new Property("notes", "string", "Any other notes about the procedure - e.g. the operative notes.", 0, java.lang.Integer.MAX_VALUE, notes));
      }

      public Procedure copy() {
        Procedure dst = new Procedure();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.patient = patient == null ? null : patient.copy();
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
        dst.report = new ArrayList<Reference>();
        for (Reference i : report)
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

