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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

/**
 * Describes the intention of how one or more practitioners intend to deliver care for a particular patient for a period of time, possibly limited to care for a specific condition or set of conditions.
 */
public class CarePlan extends Resource {

    public enum CarePlanStatus {
        planned, // The plan is in development or awaiting use but is not yet intended to be acted upon.
        active, // The plan is intended to be followed and used as part of patient care.
        completed, // The plan is no longer in use and is not expected to be followed or used in patient care.
        Null; // added to help the parsers
        public static CarePlanStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return planned;
        if ("active".equals(codeString))
          return active;
        if ("completed".equals(codeString))
          return completed;
        throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case planned: return "planned";
            case active: return "active";
            case completed: return "completed";
            default: return "?";
          }
        }
    }

  public static class CarePlanStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return CarePlanStatus.planned;
        if ("active".equals(codeString))
          return CarePlanStatus.active;
        if ("completed".equals(codeString))
          return CarePlanStatus.completed;
        throw new Exception("Unknown CarePlanStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanStatus.planned)
        return "planned";
      if (code == CarePlanStatus.active)
        return "active";
      if (code == CarePlanStatus.completed)
        return "completed";
      return "?";
      }
    }

    public enum CarePlanGoalStatus {
        inProgress, // The goal is being sought but has not yet been reached.  (Also applies if goal was reached in the past but there has been regression and goal is being sought again).
        achieved, // The goal has been met and no further action is needed.
        sustaining, // The goal has been met, but ongoing activity is needed to sustain the goal objective.
        cancelled, // The goal is no longer being sought.
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
        if ("cancelled".equals(codeString))
          return cancelled;
        throw new Exception("Unknown CarePlanGoalStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inProgress: return "in progress";
            case achieved: return "achieved";
            case sustaining: return "sustaining";
            case cancelled: return "cancelled";
            default: return "?";
          }
        }
    }

  public static class CarePlanGoalStatusEnumFactory implements EnumFactory {
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
        if ("cancelled".equals(codeString))
          return CarePlanGoalStatus.cancelled;
        throw new Exception("Unknown CarePlanGoalStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanGoalStatus.inProgress)
        return "in progress";
      if (code == CarePlanGoalStatus.achieved)
        return "achieved";
      if (code == CarePlanGoalStatus.sustaining)
        return "sustaining";
      if (code == CarePlanGoalStatus.cancelled)
        return "cancelled";
      return "?";
      }
    }

    public enum CarePlanActivityStatus {
        notStarted, // Activity is planned but no action has yet been taken.
        scheduled, // Appointment or other booking has occurred but activity has not yet begun.
        inProgress, // Activity has been started but is not yet complete.
        onHold, // Activity was started but has temporarily ceased with an expectation of resumption at a future time.
        completed, // The activities have been completed (more or less) as planned.
        cancelled, // The activities have been ended prior to completion (perhaps even before they were started).
        Null; // added to help the parsers
        public static CarePlanActivityStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("not started".equals(codeString))
          return notStarted;
        if ("scheduled".equals(codeString))
          return scheduled;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("on hold".equals(codeString))
          return onHold;
        if ("completed".equals(codeString))
          return completed;
        if ("cancelled".equals(codeString))
          return cancelled;
        throw new Exception("Unknown CarePlanActivityStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case notStarted: return "not started";
            case scheduled: return "scheduled";
            case inProgress: return "in progress";
            case onHold: return "on hold";
            case completed: return "completed";
            case cancelled: return "cancelled";
            default: return "?";
          }
        }
    }

  public static class CarePlanActivityStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("not started".equals(codeString))
          return CarePlanActivityStatus.notStarted;
        if ("scheduled".equals(codeString))
          return CarePlanActivityStatus.scheduled;
        if ("in progress".equals(codeString))
          return CarePlanActivityStatus.inProgress;
        if ("on hold".equals(codeString))
          return CarePlanActivityStatus.onHold;
        if ("completed".equals(codeString))
          return CarePlanActivityStatus.completed;
        if ("cancelled".equals(codeString))
          return CarePlanActivityStatus.cancelled;
        throw new Exception("Unknown CarePlanActivityStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CarePlanActivityStatus.notStarted)
        return "not started";
      if (code == CarePlanActivityStatus.scheduled)
        return "scheduled";
      if (code == CarePlanActivityStatus.inProgress)
        return "in progress";
      if (code == CarePlanActivityStatus.onHold)
        return "on hold";
      if (code == CarePlanActivityStatus.completed)
        return "completed";
      if (code == CarePlanActivityStatus.cancelled)
        return "cancelled";
      return "?";
      }
    }

    public enum CarePlanActivityCategory {
        diet, // Plan for the patient to consume food of a specified nature.
        drug, // Plan for the patient to consume/receive a drug, vaccine or other product.
        encounter, // Plan to meet or communicate with the patient (in-patient, out-patient, phone call, etc.).
        observation, // Plan to capture information about a patient (vitals, labs, diagnostic images, etc.).
        procedure, // Plan to modify the patient in some way (surgery, physiotherapy, education, counseling, etc.).
        supply, // Plan to provide something to the patient (medication, medical supply, etc.).
        other, // Some other form of action.
        Null; // added to help the parsers
        public static CarePlanActivityCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return diet;
        if ("drug".equals(codeString))
          return drug;
        if ("encounter".equals(codeString))
          return encounter;
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
            case encounter: return "encounter";
            case observation: return "observation";
            case procedure: return "procedure";
            case supply: return "supply";
            case other: return "other";
            default: return "?";
          }
        }
    }

  public static class CarePlanActivityCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("diet".equals(codeString))
          return CarePlanActivityCategory.diet;
        if ("drug".equals(codeString))
          return CarePlanActivityCategory.drug;
        if ("encounter".equals(codeString))
          return CarePlanActivityCategory.encounter;
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
      if (code == CarePlanActivityCategory.encounter)
        return "encounter";
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

    public static class CarePlanParticipantComponent extends BackboneElement {
        /**
         * Indicates specific responsibility of an individual within the care plan.  E.g. "Primary physician", "Team coordinator", "Caregiver", etc.
         */
        protected CodeableConcept role;

        /**
         * The specific person or organization who is participating/expected to participate in the care plan.
         */
        protected ResourceReference member;

        /**
         * The actual object that is the target of the reference (The specific person or organization who is participating/expected to participate in the care plan.)
         */
        protected Resource memberTarget;

        private static final long serialVersionUID = -1745583963L;

      public CarePlanParticipantComponent() {
        super();
      }

      public CarePlanParticipantComponent(ResourceReference member) {
        super();
        this.member = member;
      }

        /**
         * @return {@link #role} (Indicates specific responsibility of an individual within the care plan.  E.g. "Primary physician", "Team coordinator", "Caregiver", etc.)
         */
        public CodeableConcept getRole() { 
          return this.role;
        }

        /**
         * @param value {@link #role} (Indicates specific responsibility of an individual within the care plan.  E.g. "Primary physician", "Team coordinator", "Caregiver", etc.)
         */
        public CarePlanParticipantComponent setRole(CodeableConcept value) { 
          this.role = value;
          return this;
        }

        /**
         * @return {@link #member} (The specific person or organization who is participating/expected to participate in the care plan.)
         */
        public ResourceReference getMember() { 
          return this.member;
        }

        /**
         * @param value {@link #member} (The specific person or organization who is participating/expected to participate in the care plan.)
         */
        public CarePlanParticipantComponent setMember(ResourceReference value) { 
          this.member = value;
          return this;
        }

        /**
         * @return {@link #member} (The actual object that is the target of the reference. The specific person or organization who is participating/expected to participate in the care plan.)
         */
        public Resource getMemberTarget() { 
          return this.memberTarget;
        }

        /**
         * @param value {@link #member} (The actual object that is the target of the reference. The specific person or organization who is participating/expected to participate in the care plan.)
         */
        public CarePlanParticipantComponent setMemberTarget(Resource value) { 
          this.memberTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("role", "CodeableConcept", "Indicates specific responsibility of an individual within the care plan.  E.g. 'Primary physician', 'Team coordinator', 'Caregiver', etc.", 0, java.lang.Integer.MAX_VALUE, role));
          childrenList.add(new Property("member", "Resource(Practitioner|RelatedPerson|Patient|Organization)", "The specific person or organization who is participating/expected to participate in the care plan.", 0, java.lang.Integer.MAX_VALUE, member));
        }

      public CarePlanParticipantComponent copy() {
        CarePlanParticipantComponent dst = new CarePlanParticipantComponent();
        dst.role = role == null ? null : role.copy();
        dst.member = member == null ? null : member.copy();
        return dst;
      }

  }

    public static class CarePlanGoalComponent extends BackboneElement {
        /**
         * Human-readable description of a specific desired objective of the care plan.
         */
        protected StringType description;

        /**
         * Indicates whether the goal has been reached and is still considered relevant.
         */
        protected Enumeration<CarePlanGoalStatus> status;

        /**
         * Any comments related to the goal.
         */
        protected StringType notes;

        /**
         * The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.
         */
        protected List<ResourceReference> concern = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.)
         */
        protected List<Condition> concernTarget = new ArrayList<Condition>();


        private static final long serialVersionUID = -483526324L;

      public CarePlanGoalComponent() {
        super();
      }

      public CarePlanGoalComponent(StringType description) {
        super();
        this.description = description;
      }

        /**
         * @return {@link #description} (Human-readable description of a specific desired objective of the care plan.)
         */
        public StringType getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Human-readable description of a specific desired objective of the care plan.)
         */
        public CarePlanGoalComponent setDescription(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Human-readable description of a specific desired objective of the care plan.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Human-readable description of a specific desired objective of the care plan.
         */
        public CarePlanGoalComponent setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          return this;
        }

        /**
         * @return {@link #status} (Indicates whether the goal has been reached and is still considered relevant.)
         */
        public Enumeration<CarePlanGoalStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (Indicates whether the goal has been reached and is still considered relevant.)
         */
        public CarePlanGoalComponent setStatus(Enumeration<CarePlanGoalStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return Indicates whether the goal has been reached and is still considered relevant.
         */
        public CarePlanGoalStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value Indicates whether the goal has been reached and is still considered relevant.
         */
        public CarePlanGoalComponent setStatusSimple(CarePlanGoalStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<CarePlanGoalStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #notes} (Any comments related to the goal.)
         */
        public StringType getNotes() { 
          return this.notes;
        }

        /**
         * @param value {@link #notes} (Any comments related to the goal.)
         */
        public CarePlanGoalComponent setNotes(StringType value) { 
          this.notes = value;
          return this;
        }

        /**
         * @return Any comments related to the goal.
         */
        public String getNotesSimple() { 
          return this.notes == null ? null : this.notes.getValue();
        }

        /**
         * @param value Any comments related to the goal.
         */
        public CarePlanGoalComponent setNotesSimple(String value) { 
          if (value == null)
            this.notes = null;
          else {
            if (this.notes == null)
              this.notes = new StringType();
            this.notes.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #concern} (The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.)
         */
        public List<ResourceReference> getConcern() { 
          return this.concern;
        }

    // syntactic sugar
        /**
         * @return {@link #concern} (The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.)
         */
        public ResourceReference addConcern() { 
          ResourceReference t = new ResourceReference();
          this.concern.add(t);
          return t;
        }

        /**
         * @return {@link #concern} (The actual objects that are the target of the reference. The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.)
         */
        public List<Condition> getConcernTarget() { 
          return this.concernTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #concern} (Add an actual object that is the target of the reference. The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.)
         */
        public Condition addConcernTarget() { 
          Condition r = new Condition();
          this.concernTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("description", "string", "Human-readable description of a specific desired objective of the care plan.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("status", "code", "Indicates whether the goal has been reached and is still considered relevant.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("notes", "string", "Any comments related to the goal.", 0, java.lang.Integer.MAX_VALUE, notes));
          childrenList.add(new Property("concern", "Resource(Condition)", "The identified conditions that this goal relates to - the condition that caused it to be created, or that it is intended to address.", 0, java.lang.Integer.MAX_VALUE, concern));
        }

      public CarePlanGoalComponent copy() {
        CarePlanGoalComponent dst = new CarePlanGoalComponent();
        dst.description = description == null ? null : description.copy();
        dst.status = status == null ? null : status.copy();
        dst.notes = notes == null ? null : notes.copy();
        dst.concern = new ArrayList<ResourceReference>();
        for (ResourceReference i : concern)
          dst.concern.add(i.copy());
        return dst;
      }

  }

    public static class CarePlanActivityComponent extends BackboneElement {
        /**
         * Internal reference that identifies the goals that this activity is intended to contribute towards meeting.
         */
        protected List<StringType> goal = new ArrayList<StringType>();

        /**
         * Identifies what progress is being made for the specific activity.
         */
        protected Enumeration<CarePlanActivityStatus> status;

        /**
         * If true, indicates that the described activity is one that must NOT be engaged in when following the plan.
         */
        protected BooleanType prohibited;

        /**
         * Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.
         */
        protected List<ResourceReference> actionResulting = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.)
         */
        protected List<Resource> actionResultingTarget = new ArrayList<Resource>();


        /**
         * Notes about the execution of the activity.
         */
        protected StringType notes;

        /**
         * The details of the proposed activity represented in a specific resource.
         */
        protected ResourceReference detail;

        /**
         * The actual object that is the target of the reference (The details of the proposed activity represented in a specific resource.)
         */
        protected Resource detailTarget;

        /**
         * A simple summary of details suitable for a general care plan system (e.g. form driven) that doesn't know about specific resources such as procedure etc.
         */
        protected CarePlanActivitySimpleComponent simple;

        private static final long serialVersionUID = -2114558145L;

      public CarePlanActivityComponent() {
        super();
      }

      public CarePlanActivityComponent(BooleanType prohibited) {
        super();
        this.prohibited = prohibited;
      }

        /**
         * @return {@link #goal} (Internal reference that identifies the goals that this activity is intended to contribute towards meeting.)
         */
        public List<StringType> getGoal() { 
          return this.goal;
        }

    // syntactic sugar
        /**
         * @return {@link #goal} (Internal reference that identifies the goals that this activity is intended to contribute towards meeting.)
         */
        public StringType addGoal() { 
          StringType t = new StringType();
          this.goal.add(t);
          return t;
        }

        /**
         * @param value {@link #goal} (Internal reference that identifies the goals that this activity is intended to contribute towards meeting.)
         */
        public StringType addGoalSimple(String value) { 
          StringType t = new StringType();
          t.setValue(value);
          this.goal.add(t);
          return t;
        }

        /**
         * @param value {@link #goal} (Internal reference that identifies the goals that this activity is intended to contribute towards meeting.)
         */
        public boolean hasGoalSimple(String value) { 
          for (StringType v : this.goal)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #status} (Identifies what progress is being made for the specific activity.)
         */
        public Enumeration<CarePlanActivityStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (Identifies what progress is being made for the specific activity.)
         */
        public CarePlanActivityComponent setStatus(Enumeration<CarePlanActivityStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return Identifies what progress is being made for the specific activity.
         */
        public CarePlanActivityStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value Identifies what progress is being made for the specific activity.
         */
        public CarePlanActivityComponent setStatusSimple(CarePlanActivityStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<CarePlanActivityStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #prohibited} (If true, indicates that the described activity is one that must NOT be engaged in when following the plan.)
         */
        public BooleanType getProhibited() { 
          return this.prohibited;
        }

        /**
         * @param value {@link #prohibited} (If true, indicates that the described activity is one that must NOT be engaged in when following the plan.)
         */
        public CarePlanActivityComponent setProhibited(BooleanType value) { 
          this.prohibited = value;
          return this;
        }

        /**
         * @return If true, indicates that the described activity is one that must NOT be engaged in when following the plan.
         */
        public boolean getProhibitedSimple() { 
          return this.prohibited == null ? false : this.prohibited.getValue();
        }

        /**
         * @param value If true, indicates that the described activity is one that must NOT be engaged in when following the plan.
         */
        public CarePlanActivityComponent setProhibitedSimple(boolean value) { 
            if (this.prohibited == null)
              this.prohibited = new BooleanType();
            this.prohibited.setValue(value);
          return this;
        }

        /**
         * @return {@link #actionResulting} (Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.)
         */
        public List<ResourceReference> getActionResulting() { 
          return this.actionResulting;
        }

    // syntactic sugar
        /**
         * @return {@link #actionResulting} (Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.)
         */
        public ResourceReference addActionResulting() { 
          ResourceReference t = new ResourceReference();
          this.actionResulting.add(t);
          return t;
        }

        /**
         * @return {@link #actionResulting} (The actual objects that are the target of the reference. Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.)
         */
        public List<Resource> getActionResultingTarget() { 
          return this.actionResultingTarget;
        }

        /**
         * @return {@link #notes} (Notes about the execution of the activity.)
         */
        public StringType getNotes() { 
          return this.notes;
        }

        /**
         * @param value {@link #notes} (Notes about the execution of the activity.)
         */
        public CarePlanActivityComponent setNotes(StringType value) { 
          this.notes = value;
          return this;
        }

        /**
         * @return Notes about the execution of the activity.
         */
        public String getNotesSimple() { 
          return this.notes == null ? null : this.notes.getValue();
        }

        /**
         * @param value Notes about the execution of the activity.
         */
        public CarePlanActivityComponent setNotesSimple(String value) { 
          if (value == null)
            this.notes = null;
          else {
            if (this.notes == null)
              this.notes = new StringType();
            this.notes.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #detail} (The details of the proposed activity represented in a specific resource.)
         */
        public ResourceReference getDetail() { 
          return this.detail;
        }

        /**
         * @param value {@link #detail} (The details of the proposed activity represented in a specific resource.)
         */
        public CarePlanActivityComponent setDetail(ResourceReference value) { 
          this.detail = value;
          return this;
        }

        /**
         * @return {@link #detail} (The actual object that is the target of the reference. The details of the proposed activity represented in a specific resource.)
         */
        public Resource getDetailTarget() { 
          return this.detailTarget;
        }

        /**
         * @param value {@link #detail} (The actual object that is the target of the reference. The details of the proposed activity represented in a specific resource.)
         */
        public CarePlanActivityComponent setDetailTarget(Resource value) { 
          this.detailTarget = value;
          return this;
        }

        /**
         * @return {@link #simple} (A simple summary of details suitable for a general care plan system (e.g. form driven) that doesn't know about specific resources such as procedure etc.)
         */
        public CarePlanActivitySimpleComponent getSimple() { 
          return this.simple;
        }

        /**
         * @param value {@link #simple} (A simple summary of details suitable for a general care plan system (e.g. form driven) that doesn't know about specific resources such as procedure etc.)
         */
        public CarePlanActivityComponent setSimple(CarePlanActivitySimpleComponent value) { 
          this.simple = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("goal", "idref", "Internal reference that identifies the goals that this activity is intended to contribute towards meeting.", 0, java.lang.Integer.MAX_VALUE, goal));
          childrenList.add(new Property("status", "code", "Identifies what progress is being made for the specific activity.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("prohibited", "boolean", "If true, indicates that the described activity is one that must NOT be engaged in when following the plan.", 0, java.lang.Integer.MAX_VALUE, prohibited));
          childrenList.add(new Property("actionResulting", "Resource(Any)", "Resources that describe follow-on actions resulting from the plan, such as drug prescriptions, encounter records, appointments, etc.", 0, java.lang.Integer.MAX_VALUE, actionResulting));
          childrenList.add(new Property("notes", "string", "Notes about the execution of the activity.", 0, java.lang.Integer.MAX_VALUE, notes));
          childrenList.add(new Property("detail", "Resource(Procedure|MedicationPrescription|DiagnosticOrder|Encounter|Supply)", "The details of the proposed activity represented in a specific resource.", 0, java.lang.Integer.MAX_VALUE, detail));
          childrenList.add(new Property("simple", "", "A simple summary of details suitable for a general care plan system (e.g. form driven) that doesn't know about specific resources such as procedure etc.", 0, java.lang.Integer.MAX_VALUE, simple));
        }

      public CarePlanActivityComponent copy() {
        CarePlanActivityComponent dst = new CarePlanActivityComponent();
        dst.goal = new ArrayList<StringType>();
        for (StringType i : goal)
          dst.goal.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.prohibited = prohibited == null ? null : prohibited.copy();
        dst.actionResulting = new ArrayList<ResourceReference>();
        for (ResourceReference i : actionResulting)
          dst.actionResulting.add(i.copy());
        dst.notes = notes == null ? null : notes.copy();
        dst.detail = detail == null ? null : detail.copy();
        dst.simple = simple == null ? null : simple.copy();
        return dst;
      }

  }

    public static class CarePlanActivitySimpleComponent extends BackboneElement {
        /**
         * High-level categorization of the type of activity in a care plan.
         */
        protected Enumeration<CarePlanActivityCategory> category;

        /**
         * Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.
         */
        protected CodeableConcept code;

        /**
         * The period, timing or frequency upon which the described activity is to occur.
         */
        protected Type timing;

        /**
         * Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.
         */
        protected ResourceReference location;

        /**
         * The actual object that is the target of the reference (Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
         */
        protected Location locationTarget;

        /**
         * Identifies who's expected to be involved in the activity.
         */
        protected List<ResourceReference> performer = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Identifies who's expected to be involved in the activity.)
         */
        protected List<Resource> performerTarget = new ArrayList<Resource>();


        /**
         * Identifies the food, drug or other product being consumed or supplied in the activity.
         */
        protected ResourceReference product;

        /**
         * The actual object that is the target of the reference (Identifies the food, drug or other product being consumed or supplied in the activity.)
         */
        protected Resource productTarget;

        /**
         * Identifies the quantity expected to be consumed in a given day.
         */
        protected Quantity dailyAmount;

        /**
         * Identifies the quantity expected to be supplied.
         */
        protected Quantity quantity;

        /**
         * This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
         */
        protected StringType details;

        private static final long serialVersionUID = -403342401L;

      public CarePlanActivitySimpleComponent() {
        super();
      }

      public CarePlanActivitySimpleComponent(Enumeration<CarePlanActivityCategory> category) {
        super();
        this.category = category;
      }

        /**
         * @return {@link #category} (High-level categorization of the type of activity in a care plan.)
         */
        public Enumeration<CarePlanActivityCategory> getCategory() { 
          return this.category;
        }

        /**
         * @param value {@link #category} (High-level categorization of the type of activity in a care plan.)
         */
        public CarePlanActivitySimpleComponent setCategory(Enumeration<CarePlanActivityCategory> value) { 
          this.category = value;
          return this;
        }

        /**
         * @return High-level categorization of the type of activity in a care plan.
         */
        public CarePlanActivityCategory getCategorySimple() { 
          return this.category == null ? null : this.category.getValue();
        }

        /**
         * @param value High-level categorization of the type of activity in a care plan.
         */
        public CarePlanActivitySimpleComponent setCategorySimple(CarePlanActivityCategory value) { 
            if (this.category == null)
              this.category = new Enumeration<CarePlanActivityCategory>();
            this.category.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.)
         */
        public CarePlanActivitySimpleComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
         */
        public Type getTiming() { 
          return this.timing;
        }

        /**
         * @param value {@link #timing} (The period, timing or frequency upon which the described activity is to occur.)
         */
        public CarePlanActivitySimpleComponent setTiming(Type value) { 
          this.timing = value;
          return this;
        }

        /**
         * @return {@link #location} (Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
         */
        public ResourceReference getLocation() { 
          return this.location;
        }

        /**
         * @param value {@link #location} (Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
         */
        public CarePlanActivitySimpleComponent setLocation(ResourceReference value) { 
          this.location = value;
          return this;
        }

        /**
         * @return {@link #location} (The actual object that is the target of the reference. Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
         */
        public Location getLocationTarget() { 
          return this.locationTarget;
        }

        /**
         * @param value {@link #location} (The actual object that is the target of the reference. Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.)
         */
        public CarePlanActivitySimpleComponent setLocationTarget(Location value) { 
          this.locationTarget = value;
          return this;
        }

        /**
         * @return {@link #performer} (Identifies who's expected to be involved in the activity.)
         */
        public List<ResourceReference> getPerformer() { 
          return this.performer;
        }

    // syntactic sugar
        /**
         * @return {@link #performer} (Identifies who's expected to be involved in the activity.)
         */
        public ResourceReference addPerformer() { 
          ResourceReference t = new ResourceReference();
          this.performer.add(t);
          return t;
        }

        /**
         * @return {@link #performer} (The actual objects that are the target of the reference. Identifies who's expected to be involved in the activity.)
         */
        public List<Resource> getPerformerTarget() { 
          return this.performerTarget;
        }

        /**
         * @return {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
         */
        public ResourceReference getProduct() { 
          return this.product;
        }

        /**
         * @param value {@link #product} (Identifies the food, drug or other product being consumed or supplied in the activity.)
         */
        public CarePlanActivitySimpleComponent setProduct(ResourceReference value) { 
          this.product = value;
          return this;
        }

        /**
         * @return {@link #product} (The actual object that is the target of the reference. Identifies the food, drug or other product being consumed or supplied in the activity.)
         */
        public Resource getProductTarget() { 
          return this.productTarget;
        }

        /**
         * @param value {@link #product} (The actual object that is the target of the reference. Identifies the food, drug or other product being consumed or supplied in the activity.)
         */
        public CarePlanActivitySimpleComponent setProductTarget(Resource value) { 
          this.productTarget = value;
          return this;
        }

        /**
         * @return {@link #dailyAmount} (Identifies the quantity expected to be consumed in a given day.)
         */
        public Quantity getDailyAmount() { 
          return this.dailyAmount;
        }

        /**
         * @param value {@link #dailyAmount} (Identifies the quantity expected to be consumed in a given day.)
         */
        public CarePlanActivitySimpleComponent setDailyAmount(Quantity value) { 
          this.dailyAmount = value;
          return this;
        }

        /**
         * @return {@link #quantity} (Identifies the quantity expected to be supplied.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (Identifies the quantity expected to be supplied.)
         */
        public CarePlanActivitySimpleComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #details} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.)
         */
        public StringType getDetails() { 
          return this.details;
        }

        /**
         * @param value {@link #details} (This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.)
         */
        public CarePlanActivitySimpleComponent setDetails(StringType value) { 
          this.details = value;
          return this;
        }

        /**
         * @return This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
         */
        public String getDetailsSimple() { 
          return this.details == null ? null : this.details.getValue();
        }

        /**
         * @param value This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.
         */
        public CarePlanActivitySimpleComponent setDetailsSimple(String value) { 
          if (value == null)
            this.details = null;
          else {
            if (this.details == null)
              this.details = new StringType();
            this.details.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("category", "code", "High-level categorization of the type of activity in a care plan.", 0, java.lang.Integer.MAX_VALUE, category));
          childrenList.add(new Property("code", "CodeableConcept", "Detailed description of the type of activity.  E.g. What lab test, what procedure, what kind of encounter.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("timing[x]", "Schedule|Period|string", "The period, timing or frequency upon which the described activity is to occur.", 0, java.lang.Integer.MAX_VALUE, timing));
          childrenList.add(new Property("location", "Resource(Location)", "Identifies the facility where the activity will occur.  E.g. home, hospital, specific clinic, etc.", 0, java.lang.Integer.MAX_VALUE, location));
          childrenList.add(new Property("performer", "Resource(Practitioner|Organization|RelatedPerson|Patient)", "Identifies who's expected to be involved in the activity.", 0, java.lang.Integer.MAX_VALUE, performer));
          childrenList.add(new Property("product", "Resource(Medication|Substance)", "Identifies the food, drug or other product being consumed or supplied in the activity.", 0, java.lang.Integer.MAX_VALUE, product));
          childrenList.add(new Property("dailyAmount", "Quantity", "Identifies the quantity expected to be consumed in a given day.", 0, java.lang.Integer.MAX_VALUE, dailyAmount));
          childrenList.add(new Property("quantity", "Quantity", "Identifies the quantity expected to be supplied.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("details", "string", "This provides a textual description of constraints on the activity occurrence, including relation to other activities.  It may also include objectives, pre-conditions and end-conditions.  Finally, it may convey specifics about the activity such as body site, method, route, etc.", 0, java.lang.Integer.MAX_VALUE, details));
        }

      public CarePlanActivitySimpleComponent copy() {
        CarePlanActivitySimpleComponent dst = new CarePlanActivitySimpleComponent();
        dst.category = category == null ? null : category.copy();
        dst.code = code == null ? null : code.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.location = location == null ? null : location.copy();
        dst.performer = new ArrayList<ResourceReference>();
        for (ResourceReference i : performer)
          dst.performer.add(i.copy());
        dst.product = product == null ? null : product.copy();
        dst.dailyAmount = dailyAmount == null ? null : dailyAmount.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.details = details == null ? null : details.copy();
        return dst;
      }

  }

    /**
     * This records identifiers associated with this care plan that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Identifies the patient/subject whose intended care is described by the plan.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (Identifies the patient/subject whose intended care is described by the plan.)
     */
    protected Patient patientTarget;

    /**
     * Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
     */
    protected Enumeration<CarePlanStatus> status;

    /**
     * Indicates when the plan did (or is intended to) come into effect and end.
     */
    protected Period period;

    /**
     * Identifies the most recent date on which the plan has been revised.
     */
    protected DateTimeType modified;

    /**
     * Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.
     */
    protected List<ResourceReference> concern = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.)
     */
    protected List<Condition> concernTarget = new ArrayList<Condition>();


    /**
     * Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.
     */
    protected List<CarePlanParticipantComponent> participant = new ArrayList<CarePlanParticipantComponent>();

    /**
     * Describes the intended objective(s) of carrying out the Care Plan.
     */
    protected List<CarePlanGoalComponent> goal = new ArrayList<CarePlanGoalComponent>();

    /**
     * Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.
     */
    protected List<CarePlanActivityComponent> activity = new ArrayList<CarePlanActivityComponent>();

    /**
     * General notes about the care plan not covered elsewhere.
     */
    protected StringType notes;

    private static final long serialVersionUID = 1455393599L;

    public CarePlan() {
      super();
    }

    public CarePlan(Enumeration<CarePlanStatus> status) {
      super();
      this.status = status;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this care plan that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this care plan that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #patient} (Identifies the patient/subject whose intended care is described by the plan.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (Identifies the patient/subject whose intended care is described by the plan.)
     */
    public CarePlan setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. Identifies the patient/subject whose intended care is described by the plan.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. Identifies the patient/subject whose intended care is described by the plan.)
     */
    public CarePlan setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #status} (Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.)
     */
    public Enumeration<CarePlanStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.)
     */
    public CarePlan setStatus(Enumeration<CarePlanStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
     */
    public CarePlanStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.
     */
    public CarePlan setStatusSimple(CarePlanStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<CarePlanStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #period} (Indicates when the plan did (or is intended to) come into effect and end.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (Indicates when the plan did (or is intended to) come into effect and end.)
     */
    public CarePlan setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #modified} (Identifies the most recent date on which the plan has been revised.)
     */
    public DateTimeType getModified() { 
      return this.modified;
    }

    /**
     * @param value {@link #modified} (Identifies the most recent date on which the plan has been revised.)
     */
    public CarePlan setModified(DateTimeType value) { 
      this.modified = value;
      return this;
    }

    /**
     * @return Identifies the most recent date on which the plan has been revised.
     */
    public DateAndTime getModifiedSimple() { 
      return this.modified == null ? null : this.modified.getValue();
    }

    /**
     * @param value Identifies the most recent date on which the plan has been revised.
     */
    public CarePlan setModifiedSimple(DateAndTime value) { 
      if (value == null)
        this.modified = null;
      else {
        if (this.modified == null)
          this.modified = new DateTimeType();
        this.modified.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #concern} (Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.)
     */
    public List<ResourceReference> getConcern() { 
      return this.concern;
    }

    // syntactic sugar
    /**
     * @return {@link #concern} (Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.)
     */
    public ResourceReference addConcern() { 
      ResourceReference t = new ResourceReference();
      this.concern.add(t);
      return t;
    }

    /**
     * @return {@link #concern} (The actual objects that are the target of the reference. Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.)
     */
    public List<Condition> getConcernTarget() { 
      return this.concernTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #concern} (Add an actual object that is the target of the reference. Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.)
     */
    public Condition addConcernTarget() { 
      Condition r = new Condition();
      this.concernTarget.add(r);
      return r;
    }

    /**
     * @return {@link #participant} (Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.)
     */
    public List<CarePlanParticipantComponent> getParticipant() { 
      return this.participant;
    }

    // syntactic sugar
    /**
     * @return {@link #participant} (Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.)
     */
    public CarePlanParticipantComponent addParticipant() { 
      CarePlanParticipantComponent t = new CarePlanParticipantComponent();
      this.participant.add(t);
      return t;
    }

    /**
     * @return {@link #goal} (Describes the intended objective(s) of carrying out the Care Plan.)
     */
    public List<CarePlanGoalComponent> getGoal() { 
      return this.goal;
    }

    // syntactic sugar
    /**
     * @return {@link #goal} (Describes the intended objective(s) of carrying out the Care Plan.)
     */
    public CarePlanGoalComponent addGoal() { 
      CarePlanGoalComponent t = new CarePlanGoalComponent();
      this.goal.add(t);
      return t;
    }

    /**
     * @return {@link #activity} (Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.)
     */
    public List<CarePlanActivityComponent> getActivity() { 
      return this.activity;
    }

    // syntactic sugar
    /**
     * @return {@link #activity} (Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.)
     */
    public CarePlanActivityComponent addActivity() { 
      CarePlanActivityComponent t = new CarePlanActivityComponent();
      this.activity.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (General notes about the care plan not covered elsewhere.)
     */
    public StringType getNotes() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (General notes about the care plan not covered elsewhere.)
     */
    public CarePlan setNotes(StringType value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return General notes about the care plan not covered elsewhere.
     */
    public String getNotesSimple() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value General notes about the care plan not covered elsewhere.
     */
    public CarePlan setNotesSimple(String value) { 
      if (value == null)
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
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this care plan that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("patient", "Resource(Patient)", "Identifies the patient/subject whose intended care is described by the plan.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("status", "code", "Indicates whether the plan is currently being acted upon, represents future intentions or is now just historical record.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("period", "Period", "Indicates when the plan did (or is intended to) come into effect and end.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("modified", "dateTime", "Identifies the most recent date on which the plan has been revised.", 0, java.lang.Integer.MAX_VALUE, modified));
        childrenList.add(new Property("concern", "Resource(Condition)", "Identifies the conditions/problems/concerns/diagnoses/etc. whose management and/or mitigation are handled by this plan.", 0, java.lang.Integer.MAX_VALUE, concern));
        childrenList.add(new Property("participant", "", "Identifies all people and organizations who are expected to be involved in the care envisioned by this plan.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("goal", "", "Describes the intended objective(s) of carrying out the Care Plan.", 0, java.lang.Integer.MAX_VALUE, goal));
        childrenList.add(new Property("activity", "", "Identifies a planned action to occur as part of the plan.  For example, a medication to be used, lab tests to perform, self-monitoring, education, etc.", 0, java.lang.Integer.MAX_VALUE, activity));
        childrenList.add(new Property("notes", "string", "General notes about the care plan not covered elsewhere.", 0, java.lang.Integer.MAX_VALUE, notes));
      }

      public CarePlan copy() {
        CarePlan dst = new CarePlan();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.patient = patient == null ? null : patient.copy();
        dst.status = status == null ? null : status.copy();
        dst.period = period == null ? null : period.copy();
        dst.modified = modified == null ? null : modified.copy();
        dst.concern = new ArrayList<ResourceReference>();
        for (ResourceReference i : concern)
          dst.concern.add(i.copy());
        dst.participant = new ArrayList<CarePlanParticipantComponent>();
        for (CarePlanParticipantComponent i : participant)
          dst.participant.add(i.copy());
        dst.goal = new ArrayList<CarePlanGoalComponent>();
        for (CarePlanGoalComponent i : goal)
          dst.goal.add(i.copy());
        dst.activity = new ArrayList<CarePlanActivityComponent>();
        for (CarePlanActivityComponent i : activity)
          dst.activity.add(i.copy());
        dst.notes = notes == null ? null : notes.copy();
        return dst;
      }

      protected CarePlan typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.CarePlan;
   }


}

