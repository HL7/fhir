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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * An interaction between a patient and healthcare provider(s) for the purpose of providing healthcare service(s) or assessing the health status of a patient.
 */
public class Encounter extends DomainResource {

    public enum EncounterState {
        PLANNED, // The Encounter has not yet started.
        INPROGRESS, // The Encounter has begun and the patient is present / the practitioner and the patient are meeting.
        ONLEAVE, // The Encounter has begun, but the patient is temporarily on leave.
        FINISHED, // The Encounter has ended.
        CANCELLED, // The Encounter has ended before it has begun.
        NULL; // added to help the parsers
        public static EncounterState fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("in progress".equals(codeString))
          return INPROGRESS;
        if ("onleave".equals(codeString))
          return ONLEAVE;
        if ("finished".equals(codeString))
          return FINISHED;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        throw new Exception("Unknown EncounterState code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PLANNED: return "planned";
            case INPROGRESS: return "in progress";
            case ONLEAVE: return "onleave";
            case FINISHED: return "finished";
            case CANCELLED: return "cancelled";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PLANNED: return "The Encounter has not yet started.";
            case INPROGRESS: return "The Encounter has begun and the patient is present / the practitioner and the patient are meeting.";
            case ONLEAVE: return "The Encounter has begun, but the patient is temporarily on leave.";
            case FINISHED: return "The Encounter has ended.";
            case CANCELLED: return "The Encounter has ended before it has begun.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PLANNED: return "planned";
            case INPROGRESS: return "in progress";
            case ONLEAVE: return "onleave";
            case FINISHED: return "finished";
            case CANCELLED: return "cancelled";
            default: return "?";
          }
        }
    }

  public static class EncounterStateEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return EncounterState.PLANNED;
        if ("in progress".equals(codeString))
          return EncounterState.INPROGRESS;
        if ("onleave".equals(codeString))
          return EncounterState.ONLEAVE;
        if ("finished".equals(codeString))
          return EncounterState.FINISHED;
        if ("cancelled".equals(codeString))
          return EncounterState.CANCELLED;
        throw new Exception("Unknown EncounterState code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == EncounterState.PLANNED)
        return "planned";
      if (code == EncounterState.INPROGRESS)
        return "in progress";
      if (code == EncounterState.ONLEAVE)
        return "onleave";
      if (code == EncounterState.FINISHED)
        return "finished";
      if (code == EncounterState.CANCELLED)
        return "cancelled";
      return "?";
      }
    }

    public enum EncounterClass {
        INPATIENT, // An encounter during which the patient is hospitalized and stays overnight.
        OUTPATIENT, // An encounter during which the patient is not hospitalized overnight.
        AMBULATORY, // An encounter where the patient visits the practitioner in his/her office, e.g. a G.P. visit.
        EMERGENCY, // An encounter where the patient needs urgent care.
        HOME, // An encounter where the practitioner visits the patient at his/her home.
        FIELD, // An encounter taking place outside the regular environment for giving care.
        DAYTIME, // An encounter where the patient needs more prolonged treatment or investigations than outpatients, but who do not need to stay in the hospital overnight.
        VIRTUAL, // An encounter that takes place where the patient and practitioner do not physically meet but use electronic means for contact.
        NULL; // added to help the parsers
        public static EncounterClass fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("inpatient".equals(codeString))
          return INPATIENT;
        if ("outpatient".equals(codeString))
          return OUTPATIENT;
        if ("ambulatory".equals(codeString))
          return AMBULATORY;
        if ("emergency".equals(codeString))
          return EMERGENCY;
        if ("home".equals(codeString))
          return HOME;
        if ("field".equals(codeString))
          return FIELD;
        if ("daytime".equals(codeString))
          return DAYTIME;
        if ("virtual".equals(codeString))
          return VIRTUAL;
        throw new Exception("Unknown EncounterClass code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case INPATIENT: return "inpatient";
            case OUTPATIENT: return "outpatient";
            case AMBULATORY: return "ambulatory";
            case EMERGENCY: return "emergency";
            case HOME: return "home";
            case FIELD: return "field";
            case DAYTIME: return "daytime";
            case VIRTUAL: return "virtual";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case INPATIENT: return "An encounter during which the patient is hospitalized and stays overnight.";
            case OUTPATIENT: return "An encounter during which the patient is not hospitalized overnight.";
            case AMBULATORY: return "An encounter where the patient visits the practitioner in his/her office, e.g. a G.P. visit.";
            case EMERGENCY: return "An encounter where the patient needs urgent care.";
            case HOME: return "An encounter where the practitioner visits the patient at his/her home.";
            case FIELD: return "An encounter taking place outside the regular environment for giving care.";
            case DAYTIME: return "An encounter where the patient needs more prolonged treatment or investigations than outpatients, but who do not need to stay in the hospital overnight.";
            case VIRTUAL: return "An encounter that takes place where the patient and practitioner do not physically meet but use electronic means for contact.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case INPATIENT: return "inpatient";
            case OUTPATIENT: return "outpatient";
            case AMBULATORY: return "ambulatory";
            case EMERGENCY: return "emergency";
            case HOME: return "home";
            case FIELD: return "field";
            case DAYTIME: return "daytime";
            case VIRTUAL: return "virtual";
            default: return "?";
          }
        }
    }

  public static class EncounterClassEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("inpatient".equals(codeString))
          return EncounterClass.INPATIENT;
        if ("outpatient".equals(codeString))
          return EncounterClass.OUTPATIENT;
        if ("ambulatory".equals(codeString))
          return EncounterClass.AMBULATORY;
        if ("emergency".equals(codeString))
          return EncounterClass.EMERGENCY;
        if ("home".equals(codeString))
          return EncounterClass.HOME;
        if ("field".equals(codeString))
          return EncounterClass.FIELD;
        if ("daytime".equals(codeString))
          return EncounterClass.DAYTIME;
        if ("virtual".equals(codeString))
          return EncounterClass.VIRTUAL;
        throw new Exception("Unknown EncounterClass code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == EncounterClass.INPATIENT)
        return "inpatient";
      if (code == EncounterClass.OUTPATIENT)
        return "outpatient";
      if (code == EncounterClass.AMBULATORY)
        return "ambulatory";
      if (code == EncounterClass.EMERGENCY)
        return "emergency";
      if (code == EncounterClass.HOME)
        return "home";
      if (code == EncounterClass.FIELD)
        return "field";
      if (code == EncounterClass.DAYTIME)
        return "daytime";
      if (code == EncounterClass.VIRTUAL)
        return "virtual";
      return "?";
      }
    }

    public static class EncounterParticipantComponent extends BackboneElement {
        /**
         * Role of participant in encounter.
         */
        protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

        /**
         * Persons involved in the encounter other than the patient.
         */
        protected Reference individual;

        /**
         * The actual object that is the target of the reference (Persons involved in the encounter other than the patient.)
         */
        protected Resource individualTarget;

        private static final long serialVersionUID = -1094895325L;

      public EncounterParticipantComponent() {
        super();
      }

        /**
         * @return {@link #type} (Role of participant in encounter.)
         */
        public List<CodeableConcept> getType() { 
          return this.type;
        }

        /**
         * @return {@link #type} (Role of participant in encounter.)
         */
    // syntactic sugar
        public CodeableConcept addType() { //3
          CodeableConcept t = new CodeableConcept();
          this.type.add(t);
          return t;
        }

        /**
         * @return {@link #individual} (Persons involved in the encounter other than the patient.)
         */
        public Reference getIndividual() { 
          return this.individual;
        }

        /**
         * @param value {@link #individual} (Persons involved in the encounter other than the patient.)
         */
        public EncounterParticipantComponent setIndividual(Reference value) { 
          this.individual = value;
          return this;
        }

        /**
         * @return {@link #individual} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Persons involved in the encounter other than the patient.)
         */
        public Resource getIndividualTarget() { 
          return this.individualTarget;
        }

        /**
         * @param value {@link #individual} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Persons involved in the encounter other than the patient.)
         */
        public EncounterParticipantComponent setIndividualTarget(Resource value) { 
          this.individualTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Role of participant in encounter.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("individual", "Reference(Practitioner|RelatedPerson)", "Persons involved in the encounter other than the patient.", 0, java.lang.Integer.MAX_VALUE, individual));
        }

      public EncounterParticipantComponent copy() {
        EncounterParticipantComponent dst = new EncounterParticipantComponent();
        copyValues(dst);
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.individual = individual == null ? null : individual.copy();
        return dst;
      }

  }

    public static class EncounterHospitalizationComponent extends BackboneElement {
        /**
         * Pre-admission identifier.
         */
        protected Identifier preAdmissionIdentifier;

        /**
         * The location from which the patient came before admission.
         */
        protected Reference origin;

        /**
         * The actual object that is the target of the reference (The location from which the patient came before admission.)
         */
        protected Location originTarget;

        /**
         * From where patient was admitted (physician referral, transfer).
         */
        protected CodeableConcept admitSource;

        /**
         * Period during which the patient was admitted.
         */
        protected Period period;

        /**
         * Where the patient stays during this encounter.
         */
        protected List<EncounterHospitalizationAccomodationComponent> accomodation = new ArrayList<EncounterHospitalizationAccomodationComponent>();

        /**
         * Dietary restrictions for the patient.
         */
        protected CodeableConcept diet;

        /**
         * Special courtesies (VIP, board member).
         */
        protected List<CodeableConcept> specialCourtesy = new ArrayList<CodeableConcept>();

        /**
         * Wheelchair, translator, stretcher, etc.
         */
        protected List<CodeableConcept> specialArrangement = new ArrayList<CodeableConcept>();

        /**
         * Location to which the patient is discharged.
         */
        protected Reference destination;

        /**
         * The actual object that is the target of the reference (Location to which the patient is discharged.)
         */
        protected Location destinationTarget;

        /**
         * Category or kind of location after discharge.
         */
        protected CodeableConcept dischargeDisposition;

        /**
         * The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.
         */
        protected Reference dischargeDiagnosis;

        /**
         * The actual object that is the target of the reference (The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.)
         */
        protected Resource dischargeDiagnosisTarget;

        /**
         * Whether this hospitalization is a readmission.
         */
        protected BooleanType reAdmission;

        private static final long serialVersionUID = -809806884L;

      public EncounterHospitalizationComponent() {
        super();
      }

        /**
         * @return {@link #preAdmissionIdentifier} (Pre-admission identifier.)
         */
        public Identifier getPreAdmissionIdentifier() { 
          return this.preAdmissionIdentifier;
        }

        /**
         * @param value {@link #preAdmissionIdentifier} (Pre-admission identifier.)
         */
        public EncounterHospitalizationComponent setPreAdmissionIdentifier(Identifier value) { 
          this.preAdmissionIdentifier = value;
          return this;
        }

        /**
         * @return {@link #origin} (The location from which the patient came before admission.)
         */
        public Reference getOrigin() { 
          return this.origin;
        }

        /**
         * @param value {@link #origin} (The location from which the patient came before admission.)
         */
        public EncounterHospitalizationComponent setOrigin(Reference value) { 
          this.origin = value;
          return this;
        }

        /**
         * @return {@link #origin} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The location from which the patient came before admission.)
         */
        public Location getOriginTarget() { 
          return this.originTarget;
        }

        /**
         * @param value {@link #origin} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The location from which the patient came before admission.)
         */
        public EncounterHospitalizationComponent setOriginTarget(Location value) { 
          this.originTarget = value;
          return this;
        }

        /**
         * @return {@link #admitSource} (From where patient was admitted (physician referral, transfer).)
         */
        public CodeableConcept getAdmitSource() { 
          return this.admitSource;
        }

        /**
         * @param value {@link #admitSource} (From where patient was admitted (physician referral, transfer).)
         */
        public EncounterHospitalizationComponent setAdmitSource(CodeableConcept value) { 
          this.admitSource = value;
          return this;
        }

        /**
         * @return {@link #period} (Period during which the patient was admitted.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (Period during which the patient was admitted.)
         */
        public EncounterHospitalizationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        /**
         * @return {@link #accomodation} (Where the patient stays during this encounter.)
         */
        public List<EncounterHospitalizationAccomodationComponent> getAccomodation() { 
          return this.accomodation;
        }

        /**
         * @return {@link #accomodation} (Where the patient stays during this encounter.)
         */
    // syntactic sugar
        public EncounterHospitalizationAccomodationComponent addAccomodation() { //3
          EncounterHospitalizationAccomodationComponent t = new EncounterHospitalizationAccomodationComponent();
          this.accomodation.add(t);
          return t;
        }

        /**
         * @return {@link #diet} (Dietary restrictions for the patient.)
         */
        public CodeableConcept getDiet() { 
          return this.diet;
        }

        /**
         * @param value {@link #diet} (Dietary restrictions for the patient.)
         */
        public EncounterHospitalizationComponent setDiet(CodeableConcept value) { 
          this.diet = value;
          return this;
        }

        /**
         * @return {@link #specialCourtesy} (Special courtesies (VIP, board member).)
         */
        public List<CodeableConcept> getSpecialCourtesy() { 
          return this.specialCourtesy;
        }

        /**
         * @return {@link #specialCourtesy} (Special courtesies (VIP, board member).)
         */
    // syntactic sugar
        public CodeableConcept addSpecialCourtesy() { //3
          CodeableConcept t = new CodeableConcept();
          this.specialCourtesy.add(t);
          return t;
        }

        /**
         * @return {@link #specialArrangement} (Wheelchair, translator, stretcher, etc.)
         */
        public List<CodeableConcept> getSpecialArrangement() { 
          return this.specialArrangement;
        }

        /**
         * @return {@link #specialArrangement} (Wheelchair, translator, stretcher, etc.)
         */
    // syntactic sugar
        public CodeableConcept addSpecialArrangement() { //3
          CodeableConcept t = new CodeableConcept();
          this.specialArrangement.add(t);
          return t;
        }

        /**
         * @return {@link #destination} (Location to which the patient is discharged.)
         */
        public Reference getDestination() { 
          return this.destination;
        }

        /**
         * @param value {@link #destination} (Location to which the patient is discharged.)
         */
        public EncounterHospitalizationComponent setDestination(Reference value) { 
          this.destination = value;
          return this;
        }

        /**
         * @return {@link #destination} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Location to which the patient is discharged.)
         */
        public Location getDestinationTarget() { 
          return this.destinationTarget;
        }

        /**
         * @param value {@link #destination} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Location to which the patient is discharged.)
         */
        public EncounterHospitalizationComponent setDestinationTarget(Location value) { 
          this.destinationTarget = value;
          return this;
        }

        /**
         * @return {@link #dischargeDisposition} (Category or kind of location after discharge.)
         */
        public CodeableConcept getDischargeDisposition() { 
          return this.dischargeDisposition;
        }

        /**
         * @param value {@link #dischargeDisposition} (Category or kind of location after discharge.)
         */
        public EncounterHospitalizationComponent setDischargeDisposition(CodeableConcept value) { 
          this.dischargeDisposition = value;
          return this;
        }

        /**
         * @return {@link #dischargeDiagnosis} (The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.)
         */
        public Reference getDischargeDiagnosis() { 
          return this.dischargeDiagnosis;
        }

        /**
         * @param value {@link #dischargeDiagnosis} (The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.)
         */
        public EncounterHospitalizationComponent setDischargeDiagnosis(Reference value) { 
          this.dischargeDiagnosis = value;
          return this;
        }

        /**
         * @return {@link #dischargeDiagnosis} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.)
         */
        public Resource getDischargeDiagnosisTarget() { 
          return this.dischargeDiagnosisTarget;
        }

        /**
         * @param value {@link #dischargeDiagnosis} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.)
         */
        public EncounterHospitalizationComponent setDischargeDiagnosisTarget(Resource value) { 
          this.dischargeDiagnosisTarget = value;
          return this;
        }

        /**
         * @return {@link #reAdmission} (Whether this hospitalization is a readmission.). This is the underlying object with id, value and extensions. The accessor "getReAdmission" gives direct access to the value
         */
        public BooleanType getReAdmissionElement() { 
          return this.reAdmission;
        }

        /**
         * @param value {@link #reAdmission} (Whether this hospitalization is a readmission.). This is the underlying object with id, value and extensions. The accessor "getReAdmission" gives direct access to the value
         */
        public EncounterHospitalizationComponent setReAdmissionElement(BooleanType value) { 
          this.reAdmission = value;
          return this;
        }

        /**
         * @return Whether this hospitalization is a readmission.
         */
        public boolean getReAdmission() { 
          return this.reAdmission == null ? false : this.reAdmission.getValue();
        }

        /**
         * @param value Whether this hospitalization is a readmission.
         */
        public EncounterHospitalizationComponent setReAdmission(boolean value) { 
          if (value == false)
            this.reAdmission = null;
          else {
            if (this.reAdmission == null)
              this.reAdmission = new BooleanType();
            this.reAdmission.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("preAdmissionIdentifier", "Identifier", "Pre-admission identifier.", 0, java.lang.Integer.MAX_VALUE, preAdmissionIdentifier));
          childrenList.add(new Property("origin", "Reference(Location)", "The location from which the patient came before admission.", 0, java.lang.Integer.MAX_VALUE, origin));
          childrenList.add(new Property("admitSource", "CodeableConcept", "From where patient was admitted (physician referral, transfer).", 0, java.lang.Integer.MAX_VALUE, admitSource));
          childrenList.add(new Property("period", "Period", "Period during which the patient was admitted.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("accomodation", "", "Where the patient stays during this encounter.", 0, java.lang.Integer.MAX_VALUE, accomodation));
          childrenList.add(new Property("diet", "CodeableConcept", "Dietary restrictions for the patient.", 0, java.lang.Integer.MAX_VALUE, diet));
          childrenList.add(new Property("specialCourtesy", "CodeableConcept", "Special courtesies (VIP, board member).", 0, java.lang.Integer.MAX_VALUE, specialCourtesy));
          childrenList.add(new Property("specialArrangement", "CodeableConcept", "Wheelchair, translator, stretcher, etc.", 0, java.lang.Integer.MAX_VALUE, specialArrangement));
          childrenList.add(new Property("destination", "Reference(Location)", "Location to which the patient is discharged.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("dischargeDisposition", "CodeableConcept", "Category or kind of location after discharge.", 0, java.lang.Integer.MAX_VALUE, dischargeDisposition));
          childrenList.add(new Property("dischargeDiagnosis", "Reference(Any)", "The final diagnosis given a patient before release from the hospital after all testing, surgery, and workup are complete.", 0, java.lang.Integer.MAX_VALUE, dischargeDiagnosis));
          childrenList.add(new Property("reAdmission", "boolean", "Whether this hospitalization is a readmission.", 0, java.lang.Integer.MAX_VALUE, reAdmission));
        }

      public EncounterHospitalizationComponent copy() {
        EncounterHospitalizationComponent dst = new EncounterHospitalizationComponent();
        copyValues(dst);
        dst.preAdmissionIdentifier = preAdmissionIdentifier == null ? null : preAdmissionIdentifier.copy();
        dst.origin = origin == null ? null : origin.copy();
        dst.admitSource = admitSource == null ? null : admitSource.copy();
        dst.period = period == null ? null : period.copy();
        dst.accomodation = new ArrayList<EncounterHospitalizationAccomodationComponent>();
        for (EncounterHospitalizationAccomodationComponent i : accomodation)
          dst.accomodation.add(i.copy());
        dst.diet = diet == null ? null : diet.copy();
        dst.specialCourtesy = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialCourtesy)
          dst.specialCourtesy.add(i.copy());
        dst.specialArrangement = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialArrangement)
          dst.specialArrangement.add(i.copy());
        dst.destination = destination == null ? null : destination.copy();
        dst.dischargeDisposition = dischargeDisposition == null ? null : dischargeDisposition.copy();
        dst.dischargeDiagnosis = dischargeDiagnosis == null ? null : dischargeDiagnosis.copy();
        dst.reAdmission = reAdmission == null ? null : reAdmission.copy();
        return dst;
      }

  }

    public static class EncounterHospitalizationAccomodationComponent extends BackboneElement {
        /**
         * The bed that is assigned to the patient.
         */
        protected Reference bed;

        /**
         * The actual object that is the target of the reference (The bed that is assigned to the patient.)
         */
        protected Location bedTarget;

        /**
         * Period during which the patient was assigned the bed.
         */
        protected Period period;

        private static final long serialVersionUID = 1058938507L;

      public EncounterHospitalizationAccomodationComponent() {
        super();
      }

        /**
         * @return {@link #bed} (The bed that is assigned to the patient.)
         */
        public Reference getBed() { 
          return this.bed;
        }

        /**
         * @param value {@link #bed} (The bed that is assigned to the patient.)
         */
        public EncounterHospitalizationAccomodationComponent setBed(Reference value) { 
          this.bed = value;
          return this;
        }

        /**
         * @return {@link #bed} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The bed that is assigned to the patient.)
         */
        public Location getBedTarget() { 
          return this.bedTarget;
        }

        /**
         * @param value {@link #bed} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The bed that is assigned to the patient.)
         */
        public EncounterHospitalizationAccomodationComponent setBedTarget(Location value) { 
          this.bedTarget = value;
          return this;
        }

        /**
         * @return {@link #period} (Period during which the patient was assigned the bed.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (Period during which the patient was assigned the bed.)
         */
        public EncounterHospitalizationAccomodationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("bed", "Reference(Location)", "The bed that is assigned to the patient.", 0, java.lang.Integer.MAX_VALUE, bed));
          childrenList.add(new Property("period", "Period", "Period during which the patient was assigned the bed.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public EncounterHospitalizationAccomodationComponent copy() {
        EncounterHospitalizationAccomodationComponent dst = new EncounterHospitalizationAccomodationComponent();
        copyValues(dst);
        dst.bed = bed == null ? null : bed.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

  }

    public static class EncounterLocationComponent extends BackboneElement {
        /**
         * The location where the encounter takes place.
         */
        protected Reference location;

        /**
         * The actual object that is the target of the reference (The location where the encounter takes place.)
         */
        protected Location locationTarget;

        /**
         * Time period during which the patient was present at the location.
         */
        protected Period period;

        private static final long serialVersionUID = 1137166303L;

      public EncounterLocationComponent() {
        super();
      }

      public EncounterLocationComponent(Reference location) {
        super();
        this.location = location;
      }

        /**
         * @return {@link #location} (The location where the encounter takes place.)
         */
        public Reference getLocation() { 
          return this.location;
        }

        /**
         * @param value {@link #location} (The location where the encounter takes place.)
         */
        public EncounterLocationComponent setLocation(Reference value) { 
          this.location = value;
          return this;
        }

        /**
         * @return {@link #location} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The location where the encounter takes place.)
         */
        public Location getLocationTarget() { 
          return this.locationTarget;
        }

        /**
         * @param value {@link #location} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The location where the encounter takes place.)
         */
        public EncounterLocationComponent setLocationTarget(Location value) { 
          this.locationTarget = value;
          return this;
        }

        /**
         * @return {@link #period} (Time period during which the patient was present at the location.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (Time period during which the patient was present at the location.)
         */
        public EncounterLocationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("location", "Reference(Location)", "The location where the encounter takes place.", 0, java.lang.Integer.MAX_VALUE, location));
          childrenList.add(new Property("period", "Period", "Time period during which the patient was present at the location.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public EncounterLocationComponent copy() {
        EncounterLocationComponent dst = new EncounterLocationComponent();
        copyValues(dst);
        dst.location = location == null ? null : location.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

  }

    /**
     * Identifier(s) by which this encounter is known.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * planned | in progress | onleave | finished | cancelled.
     */
    protected Enumeration<EncounterState> status;

    /**
     * inpatient | outpatient | ambulatory | emergency +.
     */
    protected Enumeration<EncounterClass> class_;

    /**
     * Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).
     */
    protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

    /**
     * The patient present at the encounter.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient present at the encounter.)
     */
    protected Patient subjectTarget;

    /**
     * The main practitioner responsible for providing the service.
     */
    protected List<EncounterParticipantComponent> participant = new ArrayList<EncounterParticipantComponent>();

    /**
     * The appointment that scheduled this encounter.
     */
    protected Reference fulfills;

    /**
     * The actual object that is the target of the reference (The appointment that scheduled this encounter.)
     */
    protected Appointment fulfillsTarget;

    /**
     * The start and end time of the encounter.
     */
    protected Period period;

    /**
     * Quantity of time the encounter lasted. This excludes the time during leaves of absence.
     */
    protected Duration length;

    /**
     * Reason the encounter takes place, expressed as a code. For admissions, this can be used for a coded admission diagnosis.
     */
    protected CodeableConcept reason;

    /**
     * Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.
     */
    protected Reference indication;

    /**
     * The actual object that is the target of the reference (Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.)
     */
    protected Resource indicationTarget;

    /**
     * Indicates the urgency of the encounter.
     */
    protected CodeableConcept priority;

    /**
     * Details about an admission to a clinic.
     */
    protected EncounterHospitalizationComponent hospitalization;

    /**
     * List of locations at which the patient has been.
     */
    protected List<EncounterLocationComponent> location = new ArrayList<EncounterLocationComponent>();

    /**
     * Department or team providing care.
     */
    protected Reference serviceProvider;

    /**
     * The actual object that is the target of the reference (Department or team providing care.)
     */
    protected Organization serviceProviderTarget;

    /**
     * Another Encounter of which this encounter is a part of (administratively or in time).
     */
    protected Reference partOf;

    /**
     * The actual object that is the target of the reference (Another Encounter of which this encounter is a part of (administratively or in time).)
     */
    protected Encounter partOfTarget;

    private static final long serialVersionUID = 1326272483L;

    public Encounter() {
      super();
    }

    public Encounter(Enumeration<EncounterState> status, Enumeration<EncounterClass> class_) {
      super();
      this.status = status;
      this.class_ = class_;
    }

    /**
     * @return {@link #identifier} (Identifier(s) by which this encounter is known.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifier(s) by which this encounter is known.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #status} (planned | in progress | onleave | finished | cancelled.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<EncounterState> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (planned | in progress | onleave | finished | cancelled.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Encounter setStatusElement(Enumeration<EncounterState> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return planned | in progress | onleave | finished | cancelled.
     */
    public EncounterState getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value planned | in progress | onleave | finished | cancelled.
     */
    public Encounter setStatus(EncounterState value) { 
        if (this.status == null)
          this.status = new Enumeration<EncounterState>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #class_} (inpatient | outpatient | ambulatory | emergency +.). This is the underlying object with id, value and extensions. The accessor "getClass_" gives direct access to the value
     */
    public Enumeration<EncounterClass> getClass_Element() { 
      return this.class_;
    }

    /**
     * @param value {@link #class_} (inpatient | outpatient | ambulatory | emergency +.). This is the underlying object with id, value and extensions. The accessor "getClass_" gives direct access to the value
     */
    public Encounter setClass_Element(Enumeration<EncounterClass> value) { 
      this.class_ = value;
      return this;
    }

    /**
     * @return inpatient | outpatient | ambulatory | emergency +.
     */
    public EncounterClass getClass_() { 
      return this.class_ == null ? null : this.class_.getValue();
    }

    /**
     * @param value inpatient | outpatient | ambulatory | emergency +.
     */
    public Encounter setClass_(EncounterClass value) { 
        if (this.class_ == null)
          this.class_ = new Enumeration<EncounterClass>();
        this.class_.setValue(value);
      return this;
    }

    /**
     * @return {@link #type} (Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).)
     */
    public List<CodeableConcept> getType() { 
      return this.type;
    }

    /**
     * @return {@link #type} (Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).)
     */
    // syntactic sugar
    public CodeableConcept addType() { //3
      CodeableConcept t = new CodeableConcept();
      this.type.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The patient present at the encounter.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient present at the encounter.)
     */
    public Encounter setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient present at the encounter.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient present at the encounter.)
     */
    public Encounter setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #participant} (The main practitioner responsible for providing the service.)
     */
    public List<EncounterParticipantComponent> getParticipant() { 
      return this.participant;
    }

    /**
     * @return {@link #participant} (The main practitioner responsible for providing the service.)
     */
    // syntactic sugar
    public EncounterParticipantComponent addParticipant() { //3
      EncounterParticipantComponent t = new EncounterParticipantComponent();
      this.participant.add(t);
      return t;
    }

    /**
     * @return {@link #fulfills} (The appointment that scheduled this encounter.)
     */
    public Reference getFulfills() { 
      return this.fulfills;
    }

    /**
     * @param value {@link #fulfills} (The appointment that scheduled this encounter.)
     */
    public Encounter setFulfills(Reference value) { 
      this.fulfills = value;
      return this;
    }

    /**
     * @return {@link #fulfills} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The appointment that scheduled this encounter.)
     */
    public Appointment getFulfillsTarget() { 
      return this.fulfillsTarget;
    }

    /**
     * @param value {@link #fulfills} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The appointment that scheduled this encounter.)
     */
    public Encounter setFulfillsTarget(Appointment value) { 
      this.fulfillsTarget = value;
      return this;
    }

    /**
     * @return {@link #period} (The start and end time of the encounter.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (The start and end time of the encounter.)
     */
    public Encounter setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #length} (Quantity of time the encounter lasted. This excludes the time during leaves of absence.)
     */
    public Duration getLength() { 
      return this.length;
    }

    /**
     * @param value {@link #length} (Quantity of time the encounter lasted. This excludes the time during leaves of absence.)
     */
    public Encounter setLength(Duration value) { 
      this.length = value;
      return this;
    }

    /**
     * @return {@link #reason} (Reason the encounter takes place, expressed as a code. For admissions, this can be used for a coded admission diagnosis.)
     */
    public CodeableConcept getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Reason the encounter takes place, expressed as a code. For admissions, this can be used for a coded admission diagnosis.)
     */
    public Encounter setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #indication} (Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.)
     */
    public Reference getIndication() { 
      return this.indication;
    }

    /**
     * @param value {@link #indication} (Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.)
     */
    public Encounter setIndication(Reference value) { 
      this.indication = value;
      return this;
    }

    /**
     * @return {@link #indication} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.)
     */
    public Resource getIndicationTarget() { 
      return this.indicationTarget;
    }

    /**
     * @param value {@link #indication} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.)
     */
    public Encounter setIndicationTarget(Resource value) { 
      this.indicationTarget = value;
      return this;
    }

    /**
     * @return {@link #priority} (Indicates the urgency of the encounter.)
     */
    public CodeableConcept getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (Indicates the urgency of the encounter.)
     */
    public Encounter setPriority(CodeableConcept value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return {@link #hospitalization} (Details about an admission to a clinic.)
     */
    public EncounterHospitalizationComponent getHospitalization() { 
      return this.hospitalization;
    }

    /**
     * @param value {@link #hospitalization} (Details about an admission to a clinic.)
     */
    public Encounter setHospitalization(EncounterHospitalizationComponent value) { 
      this.hospitalization = value;
      return this;
    }

    /**
     * @return {@link #location} (List of locations at which the patient has been.)
     */
    public List<EncounterLocationComponent> getLocation() { 
      return this.location;
    }

    /**
     * @return {@link #location} (List of locations at which the patient has been.)
     */
    // syntactic sugar
    public EncounterLocationComponent addLocation() { //3
      EncounterLocationComponent t = new EncounterLocationComponent();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #serviceProvider} (Department or team providing care.)
     */
    public Reference getServiceProvider() { 
      return this.serviceProvider;
    }

    /**
     * @param value {@link #serviceProvider} (Department or team providing care.)
     */
    public Encounter setServiceProvider(Reference value) { 
      this.serviceProvider = value;
      return this;
    }

    /**
     * @return {@link #serviceProvider} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Department or team providing care.)
     */
    public Organization getServiceProviderTarget() { 
      return this.serviceProviderTarget;
    }

    /**
     * @param value {@link #serviceProvider} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Department or team providing care.)
     */
    public Encounter setServiceProviderTarget(Organization value) { 
      this.serviceProviderTarget = value;
      return this;
    }

    /**
     * @return {@link #partOf} (Another Encounter of which this encounter is a part of (administratively or in time).)
     */
    public Reference getPartOf() { 
      return this.partOf;
    }

    /**
     * @param value {@link #partOf} (Another Encounter of which this encounter is a part of (administratively or in time).)
     */
    public Encounter setPartOf(Reference value) { 
      this.partOf = value;
      return this;
    }

    /**
     * @return {@link #partOf} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Another Encounter of which this encounter is a part of (administratively or in time).)
     */
    public Encounter getPartOfTarget() { 
      return this.partOfTarget;
    }

    /**
     * @param value {@link #partOf} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Another Encounter of which this encounter is a part of (administratively or in time).)
     */
    public Encounter setPartOfTarget(Encounter value) { 
      this.partOfTarget = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier(s) by which this encounter is known.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "planned | in progress | onleave | finished | cancelled.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("class", "code", "inpatient | outpatient | ambulatory | emergency +.", 0, java.lang.Integer.MAX_VALUE, class_));
        childrenList.add(new Property("type", "CodeableConcept", "Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient present at the encounter.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("participant", "", "The main practitioner responsible for providing the service.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("fulfills", "Reference(Appointment)", "The appointment that scheduled this encounter.", 0, java.lang.Integer.MAX_VALUE, fulfills));
        childrenList.add(new Property("period", "Period", "The start and end time of the encounter.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("length", "Duration", "Quantity of time the encounter lasted. This excludes the time during leaves of absence.", 0, java.lang.Integer.MAX_VALUE, length));
        childrenList.add(new Property("reason", "CodeableConcept", "Reason the encounter takes place, expressed as a code. For admissions, this can be used for a coded admission diagnosis.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("indication", "Reference(Any)", "Reason the encounter takes place, as specified using information from another resource. For admissions, this is the admission diagnosis.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("priority", "CodeableConcept", "Indicates the urgency of the encounter.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("hospitalization", "", "Details about an admission to a clinic.", 0, java.lang.Integer.MAX_VALUE, hospitalization));
        childrenList.add(new Property("location", "", "List of locations at which the patient has been.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("serviceProvider", "Reference(Organization)", "Department or team providing care.", 0, java.lang.Integer.MAX_VALUE, serviceProvider));
        childrenList.add(new Property("partOf", "Reference(Encounter)", "Another Encounter of which this encounter is a part of (administratively or in time).", 0, java.lang.Integer.MAX_VALUE, partOf));
      }

      public Encounter copy() {
        Encounter dst = new Encounter();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.class_ = class_ == null ? null : class_.copy();
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.participant = new ArrayList<EncounterParticipantComponent>();
        for (EncounterParticipantComponent i : participant)
          dst.participant.add(i.copy());
        dst.fulfills = fulfills == null ? null : fulfills.copy();
        dst.period = period == null ? null : period.copy();
        dst.length = length == null ? null : length.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.indication = indication == null ? null : indication.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.hospitalization = hospitalization == null ? null : hospitalization.copy();
        dst.location = new ArrayList<EncounterLocationComponent>();
        for (EncounterLocationComponent i : location)
          dst.location.add(i.copy());
        dst.serviceProvider = serviceProvider == null ? null : serviceProvider.copy();
        dst.partOf = partOf == null ? null : partOf.copy();
        return dst;
      }

      protected Encounter typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Encounter;
   }


}

