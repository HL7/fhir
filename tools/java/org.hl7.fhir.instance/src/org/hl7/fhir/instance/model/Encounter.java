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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * An interaction between a patient and healthcare provider(s) for the purpose of providing healthcare service(s) or assessing the health status of a patient.
 */
public class Encounter extends Resource {

    public enum EncounterState {
        planned, // The Encounter has not yet started.
        current, // The Encounter has begun and the patient is present / the practitioner and the patient are meeting.
        onleave, // The Encounter has begun, but the patient is temporarily on leave.
        finished, // The Encounter has ended.
        cancelled, // The Encounter has ended before it has begun.
        Null; // added to help the parsers
        public static EncounterState fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return planned;
        if ("current".equals(codeString))
          return current;
        if ("onleave".equals(codeString))
          return onleave;
        if ("finished".equals(codeString))
          return finished;
        if ("cancelled".equals(codeString))
          return cancelled;
        throw new Exception("Unknown EncounterState code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case planned: return "planned";
            case current: return "current";
            case onleave: return "onleave";
            case finished: return "finished";
            case cancelled: return "cancelled";
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
          return EncounterState.planned;
        if ("current".equals(codeString))
          return EncounterState.current;
        if ("onleave".equals(codeString))
          return EncounterState.onleave;
        if ("finished".equals(codeString))
          return EncounterState.finished;
        if ("cancelled".equals(codeString))
          return EncounterState.cancelled;
        throw new Exception("Unknown EncounterState code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == EncounterState.planned)
        return "planned";
      if (code == EncounterState.current)
        return "current";
      if (code == EncounterState.onleave)
        return "onleave";
      if (code == EncounterState.finished)
        return "finished";
      if (code == EncounterState.cancelled)
        return "cancelled";
      return "?";
      }
    }

    public enum EncounterClass {
        inpatient, // An encounter during which the patient is hospitalized and stays overnight.
        outpatient, // An encounter during which the patient is not hospitalized overnight.
        ambulatory, // An encounter where the patient visits the practitioner in his/her office, e.g. a G.P. visit.
        emergency, // An encounter where the patient needs urgent care.
        home, // An encounter where the practitioner visits the patient at his/her home.
        field, // An encounter taking place outside the regular environment for giving care.
        daytime, // An encounter where the patient needs more prolonged treatment or investigations than outpatients, but who do not need to stay in the hospital overnight.
        virtual, // An encounter that takes place where the patient and practitioner do not physically meet but use electronic means for contact.
        Null; // added to help the parsers
        public static EncounterClass fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("inpatient".equals(codeString))
          return inpatient;
        if ("outpatient".equals(codeString))
          return outpatient;
        if ("ambulatory".equals(codeString))
          return ambulatory;
        if ("emergency".equals(codeString))
          return emergency;
        if ("home".equals(codeString))
          return home;
        if ("field".equals(codeString))
          return field;
        if ("daytime".equals(codeString))
          return daytime;
        if ("virtual".equals(codeString))
          return virtual;
        throw new Exception("Unknown EncounterClass code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inpatient: return "inpatient";
            case outpatient: return "outpatient";
            case ambulatory: return "ambulatory";
            case emergency: return "emergency";
            case home: return "home";
            case field: return "field";
            case daytime: return "daytime";
            case virtual: return "virtual";
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
          return EncounterClass.inpatient;
        if ("outpatient".equals(codeString))
          return EncounterClass.outpatient;
        if ("ambulatory".equals(codeString))
          return EncounterClass.ambulatory;
        if ("emergency".equals(codeString))
          return EncounterClass.emergency;
        if ("home".equals(codeString))
          return EncounterClass.home;
        if ("field".equals(codeString))
          return EncounterClass.field;
        if ("daytime".equals(codeString))
          return EncounterClass.daytime;
        if ("virtual".equals(codeString))
          return EncounterClass.virtual;
        throw new Exception("Unknown EncounterClass code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == EncounterClass.inpatient)
        return "inpatient";
      if (code == EncounterClass.outpatient)
        return "outpatient";
      if (code == EncounterClass.ambulatory)
        return "ambulatory";
      if (code == EncounterClass.emergency)
        return "emergency";
      if (code == EncounterClass.home)
        return "home";
      if (code == EncounterClass.field)
        return "field";
      if (code == EncounterClass.daytime)
        return "daytime";
      if (code == EncounterClass.virtual)
        return "virtual";
      return "?";
      }
    }

    public enum ParticipantType {
        admitter, // The practitioner responsible for admitting a patient to a hospital or other inpatient health facility.
        discharger, // The practitioner responsible for discharging the patient from a hospital or other inpatient health facility.
        responsible, // The practitioner responsible for overseeing a patient's care during a patient encounter.
        attending, // The practitioner responsible for overseeing a patient's care during a patient encounter.
        consulting, // An advisor participating in the service by performing evaluations and making recommendations.
        referrer, // The practitioner that referred the patient to another practitioner.
        Null; // added to help the parsers
        public static ParticipantType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("admitter".equals(codeString))
          return admitter;
        if ("discharger".equals(codeString))
          return discharger;
        if ("responsible".equals(codeString))
          return responsible;
        if ("attending".equals(codeString))
          return attending;
        if ("consulting".equals(codeString))
          return consulting;
        if ("referrer".equals(codeString))
          return referrer;
        throw new Exception("Unknown ParticipantType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case admitter: return "admitter";
            case discharger: return "discharger";
            case responsible: return "responsible";
            case attending: return "attending";
            case consulting: return "consulting";
            case referrer: return "referrer";
            default: return "?";
          }
        }
    }

  public static class ParticipantTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("admitter".equals(codeString))
          return ParticipantType.admitter;
        if ("discharger".equals(codeString))
          return ParticipantType.discharger;
        if ("responsible".equals(codeString))
          return ParticipantType.responsible;
        if ("attending".equals(codeString))
          return ParticipantType.attending;
        if ("consulting".equals(codeString))
          return ParticipantType.consulting;
        if ("referrer".equals(codeString))
          return ParticipantType.referrer;
        throw new Exception("Unknown ParticipantType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ParticipantType.admitter)
        return "admitter";
      if (code == ParticipantType.discharger)
        return "discharger";
      if (code == ParticipantType.responsible)
        return "responsible";
      if (code == ParticipantType.attending)
        return "attending";
      if (code == ParticipantType.consulting)
        return "consulting";
      if (code == ParticipantType.referrer)
        return "referrer";
      return "?";
      }
    }

    public static class EncounterParticipantComponent extends BackboneElement {
        /**
         * Role of participant in encounter.
         */
        protected List<Enumeration<ParticipantType>> type = new ArrayList<Enumeration<ParticipantType>>();

        /**
         * The practitioner that is involved.
         */
        protected ResourceReference practitioner;

      public EncounterParticipantComponent() {
        super();
      }

        public List<Enumeration<ParticipantType>> getType() { 
          return this.type;
        }

    // syntactic sugar
        public Enumeration<ParticipantType> addType() { 
          Enumeration<ParticipantType> t = new Enumeration<ParticipantType>();
          this.type.add(t);
          return t;
        }

        public Enumeration<ParticipantType> addTypeSimple(ParticipantType value) { 
          Enumeration<ParticipantType> t = new Enumeration<ParticipantType>();
          t.setValue(value);
          this.type.add(t);
          return t;
        }

        public ResourceReference getPractitioner() { 
          return this.practitioner;
        }

        public EncounterParticipantComponent setPractitioner(ResourceReference value) { 
          this.practitioner = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "Role of participant in encounter.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("practitioner", "Resource(Practitioner)", "The practitioner that is involved.", 0, java.lang.Integer.MAX_VALUE, practitioner));
        }

      public EncounterParticipantComponent copy(Encounter e) {
        EncounterParticipantComponent dst = new EncounterParticipantComponent();
        dst.type = new ArrayList<Enumeration<ParticipantType>>();
        for (Enumeration<ParticipantType> i : type)
          dst.type.add(i.copy());
        dst.practitioner = practitioner == null ? null : practitioner.copy();
        return dst;
      }

  }

    public static class EncounterHospitalizationComponent extends BackboneElement {
        /**
         * Pre-admission identifier.
         */
        protected Identifier preAdmissionIdentifier;

        /**
         * The location the patient came from before admission.
         */
        protected ResourceReference origin;

        /**
         * Where patient was admitted from (physician referral, transfer).
         */
        protected CodeableConcept admitSource;

        /**
         * Period of hospitalization.
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
         * Location the patient is discharged to.
         */
        protected ResourceReference destination;

        /**
         * Disposition patient released to.
         */
        protected CodeableConcept dischargeDisposition;

        /**
         * Is readmission?.
         */
        protected Boolean reAdmission;

      public EncounterHospitalizationComponent() {
        super();
      }

        public Identifier getPreAdmissionIdentifier() { 
          return this.preAdmissionIdentifier;
        }

        public EncounterHospitalizationComponent setPreAdmissionIdentifier(Identifier value) { 
          this.preAdmissionIdentifier = value;
          return this;
        }

        public ResourceReference getOrigin() { 
          return this.origin;
        }

        public EncounterHospitalizationComponent setOrigin(ResourceReference value) { 
          this.origin = value;
          return this;
        }

        public CodeableConcept getAdmitSource() { 
          return this.admitSource;
        }

        public EncounterHospitalizationComponent setAdmitSource(CodeableConcept value) { 
          this.admitSource = value;
          return this;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public EncounterHospitalizationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        public List<EncounterHospitalizationAccomodationComponent> getAccomodation() { 
          return this.accomodation;
        }

    // syntactic sugar
        public EncounterHospitalizationAccomodationComponent addAccomodation() { 
          EncounterHospitalizationAccomodationComponent t = new EncounterHospitalizationAccomodationComponent();
          this.accomodation.add(t);
          return t;
        }

        public CodeableConcept getDiet() { 
          return this.diet;
        }

        public EncounterHospitalizationComponent setDiet(CodeableConcept value) { 
          this.diet = value;
          return this;
        }

        public List<CodeableConcept> getSpecialCourtesy() { 
          return this.specialCourtesy;
        }

    // syntactic sugar
        public CodeableConcept addSpecialCourtesy() { 
          CodeableConcept t = new CodeableConcept();
          this.specialCourtesy.add(t);
          return t;
        }

        public List<CodeableConcept> getSpecialArrangement() { 
          return this.specialArrangement;
        }

    // syntactic sugar
        public CodeableConcept addSpecialArrangement() { 
          CodeableConcept t = new CodeableConcept();
          this.specialArrangement.add(t);
          return t;
        }

        public ResourceReference getDestination() { 
          return this.destination;
        }

        public EncounterHospitalizationComponent setDestination(ResourceReference value) { 
          this.destination = value;
          return this;
        }

        public CodeableConcept getDischargeDisposition() { 
          return this.dischargeDisposition;
        }

        public EncounterHospitalizationComponent setDischargeDisposition(CodeableConcept value) { 
          this.dischargeDisposition = value;
          return this;
        }

        public Boolean getReAdmission() { 
          return this.reAdmission;
        }

        public EncounterHospitalizationComponent setReAdmission(Boolean value) { 
          this.reAdmission = value;
          return this;
        }

        public boolean getReAdmissionSimple() { 
          return this.reAdmission == null ? null : this.reAdmission.getValue();
        }

        public EncounterHospitalizationComponent setReAdmissionSimple(boolean value) { 
          if (value == false)
            this.reAdmission = null;
          else {
            if (this.reAdmission == null)
              this.reAdmission = new Boolean();
            this.reAdmission.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("preAdmissionIdentifier", "Identifier", "Pre-admission identifier.", 0, java.lang.Integer.MAX_VALUE, preAdmissionIdentifier));
          childrenList.add(new Property("origin", "Resource(Location)", "The location the patient came from before admission.", 0, java.lang.Integer.MAX_VALUE, origin));
          childrenList.add(new Property("admitSource", "CodeableConcept", "Where patient was admitted from (physician referral, transfer).", 0, java.lang.Integer.MAX_VALUE, admitSource));
          childrenList.add(new Property("period", "Period", "Period of hospitalization.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("accomodation", "", "Where the patient stays during this encounter.", 0, java.lang.Integer.MAX_VALUE, accomodation));
          childrenList.add(new Property("diet", "CodeableConcept", "Dietary restrictions for the patient.", 0, java.lang.Integer.MAX_VALUE, diet));
          childrenList.add(new Property("specialCourtesy", "CodeableConcept", "Special courtesies (VIP, board member).", 0, java.lang.Integer.MAX_VALUE, specialCourtesy));
          childrenList.add(new Property("specialArrangement", "CodeableConcept", "Wheelchair, translator, stretcher, etc.", 0, java.lang.Integer.MAX_VALUE, specialArrangement));
          childrenList.add(new Property("destination", "Resource(Location)", "Location the patient is discharged to.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("dischargeDisposition", "CodeableConcept", "Disposition patient released to.", 0, java.lang.Integer.MAX_VALUE, dischargeDisposition));
          childrenList.add(new Property("reAdmission", "boolean", "Is readmission?.", 0, java.lang.Integer.MAX_VALUE, reAdmission));
        }

      public EncounterHospitalizationComponent copy(Encounter e) {
        EncounterHospitalizationComponent dst = new EncounterHospitalizationComponent();
        dst.preAdmissionIdentifier = preAdmissionIdentifier == null ? null : preAdmissionIdentifier.copy();
        dst.origin = origin == null ? null : origin.copy();
        dst.admitSource = admitSource == null ? null : admitSource.copy();
        dst.period = period == null ? null : period.copy();
        dst.accomodation = new ArrayList<EncounterHospitalizationAccomodationComponent>();
        for (EncounterHospitalizationAccomodationComponent i : accomodation)
          dst.accomodation.add(i.copy(e));
        dst.diet = diet == null ? null : diet.copy();
        dst.specialCourtesy = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialCourtesy)
          dst.specialCourtesy.add(i.copy());
        dst.specialArrangement = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialArrangement)
          dst.specialArrangement.add(i.copy());
        dst.destination = destination == null ? null : destination.copy();
        dst.dischargeDisposition = dischargeDisposition == null ? null : dischargeDisposition.copy();
        dst.reAdmission = reAdmission == null ? null : reAdmission.copy();
        return dst;
      }

  }

    public static class EncounterHospitalizationAccomodationComponent extends BackboneElement {
        /**
         * Bed.
         */
        protected ResourceReference bed;

        /**
         * Period during which the patient was assigned the bed.
         */
        protected Period period;

      public EncounterHospitalizationAccomodationComponent() {
        super();
      }

        public ResourceReference getBed() { 
          return this.bed;
        }

        public EncounterHospitalizationAccomodationComponent setBed(ResourceReference value) { 
          this.bed = value;
          return this;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public EncounterHospitalizationAccomodationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("bed", "Resource(Location)", "Bed.", 0, java.lang.Integer.MAX_VALUE, bed));
          childrenList.add(new Property("period", "Period", "Period during which the patient was assigned the bed.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public EncounterHospitalizationAccomodationComponent copy(Encounter e) {
        EncounterHospitalizationAccomodationComponent dst = new EncounterHospitalizationAccomodationComponent();
        dst.bed = bed == null ? null : bed.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

  }

    public static class EncounterLocationComponent extends BackboneElement {
        /**
         * The location the encounter takes place.
         */
        protected ResourceReference location;

        /**
         * Time period during which the patient was present at the location.
         */
        protected Period period;

      public EncounterLocationComponent() {
        super();
      }

      public EncounterLocationComponent(ResourceReference location, Period period) {
        super();
        this.location = location;
        this.period = period;
      }

        public ResourceReference getLocation() { 
          return this.location;
        }

        public EncounterLocationComponent setLocation(ResourceReference value) { 
          this.location = value;
          return this;
        }

        public Period getPeriod() { 
          return this.period;
        }

        public EncounterLocationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("location", "Resource(Location)", "The location the encounter takes place.", 0, java.lang.Integer.MAX_VALUE, location));
          childrenList.add(new Property("period", "Period", "Time period during which the patient was present at the location.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public EncounterLocationComponent copy(Encounter e) {
        EncounterLocationComponent dst = new EncounterLocationComponent();
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
     * E.g. active, aborted, finished.
     */
    protected Enumeration<EncounterState> status;

    /**
     * Inpatient | Outpatient etc.
     */
    protected Enumeration<EncounterClass> class_;

    /**
     * Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).
     */
    protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

    /**
     * The patient present at the encounter.
     */
    protected ResourceReference subject;

    /**
     * The main practitioner responsible for providing the service.
     */
    protected List<EncounterParticipantComponent> participant = new ArrayList<EncounterParticipantComponent>();

    /**
     * The appointment that scheduled this encounter.
     */
    protected ResourceReference fulfills;

    /**
     * The date and time the encounter starts, e.g. the patient arrives.
     */
    protected DateTime start;

    /**
     * Quantity of time the encounter lasted. This excludes the time during leaves of absence.
     */
    protected Duration length;

    /**
     * Reason the encounter takes place.
     */
    protected CodeableConcept reason;

    /**
     * Reason the encounter takes place.
     */
    protected ResourceReference indication;

    /**
     * Indicates the urgency of the encounter.
     */
    protected CodeableConcept priority;

    /**
     * Details about an admission to a clinic.
     */
    protected EncounterHospitalizationComponent hospitalization;

    /**
     * List of locations the patient has been at.
     */
    protected List<EncounterLocationComponent> location = new ArrayList<EncounterLocationComponent>();

    /**
     * Department or team providing care.
     */
    protected ResourceReference serviceProvider;

    /**
     * Another Encounter this encounter is part of (administratively or in time).
     */
    protected ResourceReference partOf;

    public Encounter() {
      super();
    }

    public Encounter(Enumeration<EncounterState> status, Enumeration<EncounterClass> class_) {
      super();
      this.status = status;
      this.class_ = class_;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public Enumeration<EncounterState> getStatus() { 
      return this.status;
    }

    public Encounter setStatus(Enumeration<EncounterState> value) { 
      this.status = value;
      return this;
    }

    public EncounterState getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public Encounter setStatusSimple(EncounterState value) { 
        if (this.status == null)
          this.status = new Enumeration<EncounterState>();
        this.status.setValue(value);
      return this;
    }

    public Enumeration<EncounterClass> getClass_() { 
      return this.class_;
    }

    public Encounter setClass_(Enumeration<EncounterClass> value) { 
      this.class_ = value;
      return this;
    }

    public EncounterClass getClass_Simple() { 
      return this.class_ == null ? null : this.class_.getValue();
    }

    public Encounter setClass_Simple(EncounterClass value) { 
        if (this.class_ == null)
          this.class_ = new Enumeration<EncounterClass>();
        this.class_.setValue(value);
      return this;
    }

    public List<CodeableConcept> getType() { 
      return this.type;
    }

    // syntactic sugar
    public CodeableConcept addType() { 
      CodeableConcept t = new CodeableConcept();
      this.type.add(t);
      return t;
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public Encounter setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    public List<EncounterParticipantComponent> getParticipant() { 
      return this.participant;
    }

    // syntactic sugar
    public EncounterParticipantComponent addParticipant() { 
      EncounterParticipantComponent t = new EncounterParticipantComponent();
      this.participant.add(t);
      return t;
    }

    public ResourceReference getFulfills() { 
      return this.fulfills;
    }

    public Encounter setFulfills(ResourceReference value) { 
      this.fulfills = value;
      return this;
    }

    public DateTime getStart() { 
      return this.start;
    }

    public Encounter setStart(DateTime value) { 
      this.start = value;
      return this;
    }

    public String getStartSimple() { 
      return this.start == null ? null : this.start.getValue();
    }

    public Encounter setStartSimple(String value) { 
      if (value == null)
        this.start = null;
      else {
        if (this.start == null)
          this.start = new DateTime();
        this.start.setValue(value);
      }
      return this;
    }

    public Duration getLength() { 
      return this.length;
    }

    public Encounter setLength(Duration value) { 
      this.length = value;
      return this;
    }

    public CodeableConcept getReason() { 
      return this.reason;
    }

    public Encounter setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    public ResourceReference getIndication() { 
      return this.indication;
    }

    public Encounter setIndication(ResourceReference value) { 
      this.indication = value;
      return this;
    }

    public CodeableConcept getPriority() { 
      return this.priority;
    }

    public Encounter setPriority(CodeableConcept value) { 
      this.priority = value;
      return this;
    }

    public EncounterHospitalizationComponent getHospitalization() { 
      return this.hospitalization;
    }

    public Encounter setHospitalization(EncounterHospitalizationComponent value) { 
      this.hospitalization = value;
      return this;
    }

    public List<EncounterLocationComponent> getLocation() { 
      return this.location;
    }

    // syntactic sugar
    public EncounterLocationComponent addLocation() { 
      EncounterLocationComponent t = new EncounterLocationComponent();
      this.location.add(t);
      return t;
    }

    public ResourceReference getServiceProvider() { 
      return this.serviceProvider;
    }

    public Encounter setServiceProvider(ResourceReference value) { 
      this.serviceProvider = value;
      return this;
    }

    public ResourceReference getPartOf() { 
      return this.partOf;
    }

    public Encounter setPartOf(ResourceReference value) { 
      this.partOf = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier(s) by which this encounter is known.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "E.g. active, aborted, finished.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("class", "code", "Inpatient | Outpatient etc.", 0, java.lang.Integer.MAX_VALUE, class_));
        childrenList.add(new Property("type", "CodeableConcept", "Specific type of encounter (e.g. e-mail consultation, surgical day-care, skilled nursing, rehabilitation).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("subject", "Resource(Patient)", "The patient present at the encounter.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("participant", "", "The main practitioner responsible for providing the service.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("fulfills", "Resource(Appointment)", "The appointment that scheduled this encounter.", 0, java.lang.Integer.MAX_VALUE, fulfills));
        childrenList.add(new Property("start", "dateTime", "The date and time the encounter starts, e.g. the patient arrives.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("length", "Duration", "Quantity of time the encounter lasted. This excludes the time during leaves of absence.", 0, java.lang.Integer.MAX_VALUE, length));
        childrenList.add(new Property("reason", "CodeableConcept", "Reason the encounter takes place.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("indication", "Resource(Any)", "Reason the encounter takes place.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("priority", "CodeableConcept", "Indicates the urgency of the encounter.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("hospitalization", "", "Details about an admission to a clinic.", 0, java.lang.Integer.MAX_VALUE, hospitalization));
        childrenList.add(new Property("location", "", "List of locations the patient has been at.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("serviceProvider", "Resource(Organization)", "Department or team providing care.", 0, java.lang.Integer.MAX_VALUE, serviceProvider));
        childrenList.add(new Property("partOf", "Resource(Encounter)", "Another Encounter this encounter is part of (administratively or in time).", 0, java.lang.Integer.MAX_VALUE, partOf));
      }

      public Encounter copy() {
        Encounter dst = new Encounter();
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
          dst.participant.add(i.copy(dst));
        dst.fulfills = fulfills == null ? null : fulfills.copy();
        dst.start = start == null ? null : start.copy();
        dst.length = length == null ? null : length.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.indication = indication == null ? null : indication.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.hospitalization = hospitalization == null ? null : hospitalization.copy(dst);
        dst.location = new ArrayList<EncounterLocationComponent>();
        for (EncounterLocationComponent i : location)
          dst.location.add(i.copy(dst));
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

