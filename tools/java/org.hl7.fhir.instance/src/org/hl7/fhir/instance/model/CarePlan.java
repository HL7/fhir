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
 * Describes the intention of how one or more practitioners intend to deliver care for a particular patient for a period of time, possibly limited to care for a specific condition or set of conditions.
 */
public class CarePlan extends Resource {

    public enum CarePlanStatus {
        planned, // The plan is in development or awaiting use but is not yet intended to be acted upon.
        active, // The plan is intended to be followed and used as part of patient care
        ended, // The plan is no longer in use and is not expected to be followed or used in patient care
        Null; // added to help the parsers
        public static CarePlanStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return planned;
        if ("active".equals(codeString))
          return active;
        if ("ended".equals(codeString))
          return ended;
        throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case planned: return "planned";
            case active: return "active";
            case ended: return "ended";
            default: return "?";
          }
        }
    }

  public class CarePlanStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return CarePlanStatus.planned;
        if ("active".equals(codeString))
          return CarePlanStatus.active;
        if ("ended".equals(codeString))
          return CarePlanStatus.ended;
        throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanStatus.planned)
        return "planned";
      if (code == CarePlanStatus.active)
        return "active";
      if (code == CarePlanStatus.ended)
        return "ended";
      return "?";
      }
    }

    public enum CarePlanGoalStatus {
        inProgress, // The goal is being sought but has not yet been reached.  (Also applies if goal was reached in the past but there has been regression and goal is being sought again)
        achieved, // The goal has been met and no further action is needed
        sustaining, // The goal has been met, but ongoing activity is needed to sustain the goal objective
        abandoned, // The goal is no longer being sought
        Null; // added to help the parsers
        public static CarePlanGoalStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("achieved".equals(codeString))
          return achieved;
        if ("sustaining".equals(codeString))
          return sustaining;
        if ("abandoned".equals(codeString))
          return abandoned;
        throw new Exception("Unknown CarePlanGoalStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inProgress: return "in progress";
            case achieved: return "achieved";
            case sustaining: return "sustaining";
            case abandoned: return "abandoned";
            default: return "?";
          }
        }
    }

  public class CarePlanGoalStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return CarePlanGoalStatus.inProgress;
        if ("achieved".equals(codeString))
          return CarePlanGoalStatus.achieved;
        if ("sustaining".equals(codeString))
          return CarePlanGoalStatus.sustaining;
        if ("abandoned".equals(codeString))
          return CarePlanGoalStatus.abandoned;
        throw new Exception("Unknown CarePlanGoalStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanGoalStatus.inProgress)
        return "in progress";
      if (code == CarePlanGoalStatus.achieved)
        return "achieved";
      if (code == CarePlanGoalStatus.sustaining)
        return "sustaining";
      if (code == CarePlanGoalStatus.abandoned)
        return "abandoned";
      return "?";
      }
    }

    public enum CarePlanActivityCategory {
        diet, // Plan for the patient to consume food of a specified nature
        drug, // Plan for the patient to consume/receive a drug, vaccine or other product
        visit, // Plan to meet or communicate with the patient (in-patient, out-patient, phone call, etc.)
        observation, // Plan to capture information about a patient (vitals, labs, diagnostic images, etc.)
        procedure, // Plan to modify the patient in some way (surgery, physio-therapy, education, counselling, etc.)
        supply, // Plan to provide something to the patient (medication, medical supply, etc.)
        other, // Some other form of action
        Null; // added to help the parsers
        public static CarePlanActivityCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return diet;
        if ("drug".equals(codeString))
          return drug;
        if ("visit".equals(codeString))
          return visit;
        if ("observation".equals(codeString))
          return observation;
        if ("procedure".equals(codeString))
          return procedure;
        if ("supply".equals(codeString))
          return supply;
        if ("other".equals(codeString))
          return other;
        throw new Exception("Unknown CarePlanActivityCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case diet: return "diet";
            case drug: return "drug";
            case visit: return "visit";
            case observation: return "observation";
            case procedure: return "procedure";
            case supply: return "supply";
            case other: return "other";
            default: return "?";
          }
        }
    }

  public class CarePlanActivityCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return CarePlanActivityCategory.diet;
        if ("drug".equals(codeString))
          return CarePlanActivityCategory.drug;
        if ("visit".equals(codeString))
          return CarePlanActivityCategory.visit;
        if ("observation".equals(codeString))
          return CarePlanActivityCategory.observation;
        if ("procedure".equals(codeString))
          return CarePlanActivityCategory.procedure;
        if ("supply".equals(codeString))
          return CarePlanActivityCategory.supply;
        if ("other".equals(codeString))
          return CarePlanActivityCategory.other;
        throw new Exception("Unknown CarePlanActivityCategory code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanActivityCategory.diet)
        return "diet";
      if (code == CarePlanActivityCategory.drug)
        return "drug";
      if (code == CarePlanActivityCategory.visit)
        return "visit";
      if (code == CarePlanActivityCategory.observation)
        return "observation";
      if (code == CarePlanActivityCategory.procedure)
        return "procedure";
      if (code == CarePlanActivityCategory.supply)
        return "supply";
      if (code == CarePlanActivityCategory.other)
        return "other";
      return "?";
      }
    }

    public enum CarePlanActivityStatus {
        notStarted, // Activity is planned but no action has yet been taken
        scheduled, // Appointment or other booking has occurred but activity has not yet begun
        ongoing, // Activity has been started but is not yet complete
        onHold, // Activity was started but has temporarily ceased with an expectation of resumption at a future time.
        completed, // The activities have been completed (more or less) as planned
        discontinued, // The activities have been ended prior to completion (perhaps even before they were started)
        Null; // added to help the parsers
        public static CarePlanActivityStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("not started".equals(codeString))
          return notStarted;
        if ("scheduled".equals(codeString))
          return scheduled;
        if ("ongoing".equals(codeString))
          return ongoing;
        if ("on hold".equals(codeString))
          return onHold;
        if ("completed".equals(codeString))
          return completed;
        if ("discontinued".equals(codeString))
          return discontinued;
        throw new Exception("Unknown CarePlanActivityStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case notStarted: return "not started";
            case scheduled: return "scheduled";
            case ongoing: return "ongoing";
            case onHold: return "on hold";
            case completed: return "completed";
            case discontinued: return "discontinued";
            default: return "?";
          }
        }
    }

  public class CarePlanActivityStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("not started".equals(codeString))
          return CarePlanActivityStatus.notStarted;
        if ("scheduled".equals(codeString))
          return CarePlanActivityStatus.scheduled;
        if ("ongoing".equals(codeString))
          return CarePlanActivityStatus.ongoing;
        if ("on hold".equals(codeString))
          return CarePlanActivityStatus.onHold;
        if ("completed".equals(codeString))
          return CarePlanActivityStatus.completed;
        if ("discontinued".equals(codeString))
          return CarePlanActivityStatus.discontinued;
        throw new Exception("Unknown CarePlanActivityStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanActivityStatus.notStarted)
        return "not started";
      if (code == CarePlanActivityStatus.scheduled)
        return "scheduled";
      if (code == CarePlanActivityStatus.ongoing)
        return "ongoing";
      if (code == CarePlanActivityStatus.onHold)
        return "on hold";
      if (code == CarePlanActivityStatus.completed)
        return "completed";
      if (code == CarePlanActivityStatus.discontinued)
        return "discontinued";
      return "?";
      }
    }

    public class CarePlanParticipantComponent extends Element {
        /**
         * Indicates specific responsibility of an individual within the care plan.  E.g. "Primary physician", "Team coordinator", "Caregiver", etc.
         */
        private CodeableConcept role;

        /**
         * The specific person or organization who is participating/expected to participate in the care plan.
         */
        private ResourceReference member;

        public CodeableConcept getRole() { 
          return this.role;
        }

        public void setRole(CodeableConcept value) { 
          this.role = value;
        }

        public ResourceReference getMember() { 
          return this.member;
        }

        public void setMember(ResourceReference value) { 
          this.member = value;
        }

  }

    public class CarePlanGoalComponent extends Element {
        /**
         * Human readable description of a specific desired objective of the care plan.
         */
        private String_ description;

        /**
         * Indicates whether the goal has been reached and is still considered relevant
         */
        private Enumeration<CarePlanGoalStatus> status;

        /**
         * Any comments related to the goal
         */
        private String_ notes;

        public String_ getDescription() { 
          return this.description;
        }

        public void setDescription(String_ value) { 
          this.description = value;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public void setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
        }

        public Enumeration<CarePlanGoalStatus> getStatus() { 
          return this.status;
        }

        public void setStatus(Enumeration<CarePlanGoalStatus> value) { 
          this.status = value;
        }

        public CarePlanGoalStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        public void setStatusSimple(CarePlanGoalStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<CarePlanGoalStatus>();
            this.status.setValue(value);
          }
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

  }

    public class CarePlanActivityComponent extends Element {
        /**
         * High-level categorization of the type of activity in a care plan.
         */
        private Enumeration<CarePlanActivityCategory> category;

        /**
         * Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of visit.
         */
        private CodeableConcept code;

        /**
         * Identifies what progress is being made for the specific activity.
         */
        private Enumeration<CarePlanActivityStatus> status;

        /**
         * If true, indicates that the described activity is one that must NOT be engaged in when following the plan.
         */
        private Boolean prohibited;

        /**
         * The period, timing or frequency upon which the described activity is to occur.
         */
        private Type timing;

        /**
         * Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.
         */
        private ResourceReference location;

        /**
         * Identifies who's expected to be involved in the activity.
         */
        private List<ResourceReference> performer = new ArrayList<ResourceReference>();

        /**
         * Identifies the food, drug or other product being consumed or supplied in the activity.
         */
        private ResourceReference product;

        /**
         * Identifies the quantity expected to be consumed in a given day.
         */
        private Quantity dailyAmount;

        /**
         * Identifies the quantity expected to be supplied.
         */
        private Quantity quantity;

        /**
         * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
         */
        private String_ details;

        /**
         * Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, visit records, appointments, etc.
         */
        private List<ResourceReference> actionTaken = new ArrayList<ResourceReference>();

        /**
         * Notes about the execution of the activity
         */
        private String_ notes;

        public Enumeration<CarePlanActivityCategory> getCategory() { 
          return this.category;
        }

        public void setCategory(Enumeration<CarePlanActivityCategory> value) { 
          this.category = value;
        }

        public CarePlanActivityCategory getCategorySimple() { 
          return this.category == null ? null : this.category.getValue();
        }

        public void setCategorySimple(CarePlanActivityCategory value) { 
            if (this.category == null)
              this.category = new Enumeration<CarePlanActivityCategory>();
            this.category.setValue(value);
        }

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public Enumeration<CarePlanActivityStatus> getStatus() { 
          return this.status;
        }

        public void setStatus(Enumeration<CarePlanActivityStatus> value) { 
          this.status = value;
        }

        public CarePlanActivityStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        public void setStatusSimple(CarePlanActivityStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<CarePlanActivityStatus>();
            this.status.setValue(value);
          }
        }

        public Boolean getProhibited() { 
          return this.prohibited;
        }

        public void setProhibited(Boolean value) { 
          this.prohibited = value;
        }

        public boolean getProhibitedSimple() { 
          return this.prohibited == null ? null : this.prohibited.getValue();
        }

        public void setProhibitedSimple(boolean value) { 
            if (this.prohibited == null)
              this.prohibited = new Boolean();
            this.prohibited.setValue(value);
        }

        public Type getTiming() { 
          return this.timing;
        }

        public void setTiming(Type value) { 
          this.timing = value;
        }

        public ResourceReference getLocation() { 
          return this.location;
        }

        public void setLocation(ResourceReference value) { 
          this.location = value;
        }

        public List<ResourceReference> getPerformer() { 
          return this.performer;
        }

        public ResourceReference getProduct() { 
          return this.product;
        }

        public void setProduct(ResourceReference value) { 
          this.product = value;
        }

        public Quantity getDailyAmount() { 
          return this.dailyAmount;
        }

        public void setDailyAmount(Quantity value) { 
          this.dailyAmount = value;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
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

        public List<ResourceReference> getActionTaken() { 
          return this.actionTaken;
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

  }

    /**
     * Unique identifier by which the care plan is known in different business contexts.
     */
    private Identifier identifier;

    /**
     * Identifies the patient/subject whose intended care is described by the plan.
     */
    private ResourceReference patient;

    /**
     * Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
     */
    private Enumeration<CarePlanStatus> status;

    /**
     * Indicates when the plan did (or is intended to) come into effect and end.
     */
    private Period period;

    /**
     * Identifies the most recent date on which the plan has been revised.
     */
    private DateTime modified;

    /**
     * Identifies the problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.
     */
    private List<ResourceReference> concern = new ArrayList<ResourceReference>();

    /**
     * Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.
     */
    private List<CarePlanParticipantComponent> participant = new ArrayList<CarePlanParticipantComponent>();

    /**
     * Describes the intended objective(s) of carrying out the Care Plan.
     */
    private List<CarePlanGoalComponent> goal = new ArrayList<CarePlanGoalComponent>();

    /**
     * Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.
     */
    private List<CarePlanActivityComponent> activity = new ArrayList<CarePlanActivityComponent>();

    /**
     * General notes about the care plan not covered elsewhere
     */
    private String_ notes;

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public Enumeration<CarePlanStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<CarePlanStatus> value) { 
      this.status = value;
    }

    public CarePlanStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(CarePlanStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<CarePlanStatus>();
        this.status.setValue(value);
    }

    public Period getPeriod() { 
      return this.period;
    }

    public void setPeriod(Period value) { 
      this.period = value;
    }

    public DateTime getModified() { 
      return this.modified;
    }

    public void setModified(DateTime value) { 
      this.modified = value;
    }

    public String getModifiedSimple() { 
      return this.modified == null ? null : this.modified.getValue();
    }

    public void setModifiedSimple(String value) { 
      if (value == null)
        this.modified = null;
      else {
        if (this.modified == null)
          this.modified = new DateTime();
        this.modified.setValue(value);
      }
    }

    public List<ResourceReference> getConcern() { 
      return this.concern;
    }

    public List<CarePlanParticipantComponent> getParticipant() { 
      return this.participant;
    }

    public List<CarePlanGoalComponent> getGoal() { 
      return this.goal;
    }

    public List<CarePlanActivityComponent> getActivity() { 
      return this.activity;
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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.CarePlan;
   }


}

