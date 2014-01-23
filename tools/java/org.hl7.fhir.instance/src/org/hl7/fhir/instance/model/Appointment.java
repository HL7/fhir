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

// Generated on Wed, Jan 22, 2014 22:45-0600 for FHIR v0.12

import java.util.*;

/**
 * A scheduled appointment for a patient and/or practitioner(s) where a service may take place.
 */
public class Appointment extends Resource {

    public enum Participantrequired {
        required, // The participant is required to attend the appointment.
        optional, // The participant may optionally attend the appointment.
        informationonly, // The participant is not required to attend the appointment (appointment is about them, not for them).
        Null; // added to help the parsers
        public static Participantrequired fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return required;
        if ("optional".equals(codeString))
          return optional;
        if ("information-only".equals(codeString))
          return informationonly;
        throw new Exception("Unknown Participantrequired code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case required: return "required";
            case optional: return "optional";
            case informationonly: return "information-only";
            default: return "?";
          }
        }
    }

  public static class ParticipantrequiredEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return Participantrequired.required;
        if ("optional".equals(codeString))
          return Participantrequired.optional;
        if ("information-only".equals(codeString))
          return Participantrequired.informationonly;
        throw new Exception("Unknown Participantrequired code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Participantrequired.required)
        return "required";
      if (code == Participantrequired.optional)
        return "optional";
      if (code == Participantrequired.informationonly)
        return "information-only";
      return "?";
      }
    }

    public enum Participationstatus {
        accepted, // The participant has accepted the appointment.
        declined, // The participant has declined the appointment.
        tentative, // The participant has tentative the appointment.
        inprocess, // The participant has in-process the appointment.
        completed, // The participant has completed the appointment.
        needsaction, // The participant has needs-action the appointment.
        Null; // added to help the parsers
        public static Participationstatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("accepted".equals(codeString))
          return accepted;
        if ("declined".equals(codeString))
          return declined;
        if ("tentative".equals(codeString))
          return tentative;
        if ("in-process".equals(codeString))
          return inprocess;
        if ("completed".equals(codeString))
          return completed;
        if ("needs-action".equals(codeString))
          return needsaction;
        throw new Exception("Unknown Participationstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case accepted: return "accepted";
            case declined: return "declined";
            case tentative: return "tentative";
            case inprocess: return "in-process";
            case completed: return "completed";
            case needsaction: return "needs-action";
            default: return "?";
          }
        }
    }

  public static class ParticipationstatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("accepted".equals(codeString))
          return Participationstatus.accepted;
        if ("declined".equals(codeString))
          return Participationstatus.declined;
        if ("tentative".equals(codeString))
          return Participationstatus.tentative;
        if ("in-process".equals(codeString))
          return Participationstatus.inprocess;
        if ("completed".equals(codeString))
          return Participationstatus.completed;
        if ("needs-action".equals(codeString))
          return Participationstatus.needsaction;
        throw new Exception("Unknown Participationstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Participationstatus.accepted)
        return "accepted";
      if (code == Participationstatus.declined)
        return "declined";
      if (code == Participationstatus.tentative)
        return "tentative";
      if (code == Participationstatus.inprocess)
        return "in-process";
      if (code == Participationstatus.completed)
        return "completed";
      if (code == Participationstatus.needsaction)
        return "needs-action";
      return "?";
      }
    }

    public static class AppointmentParticipantComponent extends BackboneElement {
        /**
         * Role of participant in the appointment.
         */
        protected List<CodeableConcept> type = new ArrayList<CodeableConcept>();

        /**
         * A Person of device that is participating in the appointment.
         */
        protected List<ResourceReference> individual = new ArrayList<ResourceReference>();

        /**
         * Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        protected Enumeration<Participantrequired> required;

        /**
         * Participation status of the Patient.
         */
        protected Enumeration<Participationstatus> status;

        /**
         * Observations that lead to the creation of this appointment. (Is this 80%).
         */
        protected List<ResourceReference> observation = new ArrayList<ResourceReference>();

      public AppointmentParticipantComponent() {
        super();
      }

      public AppointmentParticipantComponent(Enumeration<Participationstatus> status) {
        super();
        this.status = status;
      }

        /**
         * @return {@link #type} (Role of participant in the appointment.)
         */
        public List<CodeableConcept> getType() { 
          return this.type;
        }

    // syntactic sugar
        /**
         * @return {@link #type} (Role of participant in the appointment.)
         */
        public CodeableConcept addType() { 
          CodeableConcept t = new CodeableConcept();
          this.type.add(t);
          return t;
        }

        /**
         * @return {@link #individual} (A Person of device that is participating in the appointment.)
         */
        public List<ResourceReference> getIndividual() { 
          return this.individual;
        }

    // syntactic sugar
        /**
         * @return {@link #individual} (A Person of device that is participating in the appointment.)
         */
        public ResourceReference addIndividual() { 
          ResourceReference t = new ResourceReference();
          this.individual.add(t);
          return t;
        }

        /**
         * @return {@link #required} (Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.)
         */
        public Enumeration<Participantrequired> getRequired() { 
          return this.required;
        }

        /**
         * @param value {@link #required} (Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.)
         */
        public AppointmentParticipantComponent setRequired(Enumeration<Participantrequired> value) { 
          this.required = value;
          return this;
        }

        /**
         * @return Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        public Participantrequired getRequiredSimple() { 
          return this.required == null ? null : this.required.getValue();
        }

        /**
         * @param value Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        public AppointmentParticipantComponent setRequiredSimple(Participantrequired value) { 
          if (value == null)
            this.required = null;
          else {
            if (this.required == null)
              this.required = new Enumeration<Participantrequired>();
            this.required.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #status} (Participation status of the Patient.)
         */
        public Enumeration<Participationstatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (Participation status of the Patient.)
         */
        public AppointmentParticipantComponent setStatus(Enumeration<Participationstatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return Participation status of the Patient.
         */
        public Participationstatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value Participation status of the Patient.
         */
        public AppointmentParticipantComponent setStatusSimple(Participationstatus value) { 
            if (this.status == null)
              this.status = new Enumeration<Participationstatus>();
            this.status.setValue(value);
          return this;
        }

        /**
         * @return {@link #observation} (Observations that lead to the creation of this appointment. (Is this 80%).)
         */
        public List<ResourceReference> getObservation() { 
          return this.observation;
        }

    // syntactic sugar
        /**
         * @return {@link #observation} (Observations that lead to the creation of this appointment. (Is this 80%).)
         */
        public ResourceReference addObservation() { 
          ResourceReference t = new ResourceReference();
          this.observation.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Role of participant in the appointment.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("individual", "Resource(Practitioner|Patient|RelatedPerson|Device)", "A Person of device that is participating in the appointment.", 0, java.lang.Integer.MAX_VALUE, individual));
          childrenList.add(new Property("required", "code", "Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.", 0, java.lang.Integer.MAX_VALUE, required));
          childrenList.add(new Property("status", "code", "Participation status of the Patient.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("observation", "Resource(Observation)", "Observations that lead to the creation of this appointment. (Is this 80%).", 0, java.lang.Integer.MAX_VALUE, observation));
        }

      public AppointmentParticipantComponent copy(Appointment e) {
        AppointmentParticipantComponent dst = new AppointmentParticipantComponent();
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.individual = new ArrayList<ResourceReference>();
        for (ResourceReference i : individual)
          dst.individual.add(i.copy());
        dst.required = required == null ? null : required.copy();
        dst.status = status == null ? null : status.copy();
        dst.observation = new ArrayList<ResourceReference>();
        for (ResourceReference i : observation)
          dst.observation.add(i.copy());
        return dst;
      }

  }

    /**
     * This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).
     */
    protected Integer priority;

    /**
     * Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    protected Code status;

    /**
     * The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    protected String_ description;

    /**
     * Date/Time that the appointment is to take place.
     */
    protected Instant start;

    /**
     * Date/Time that the appointment is to conclude.
     */
    protected Instant end;

    /**
     * The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment.
     */
    protected Schedule schedule;

    /**
     * The timezone should be a value referenced from a timezone database.
     */
    protected String_ timezone;

    /**
     * The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.
     */
    protected List<ResourceReference> slot = new ArrayList<ResourceReference>();

    /**
     * The primary location that this appointment is to take place.
     */
    protected ResourceReference location;

    /**
     * Additional comments about the appointment.
     */
    protected String_ comment;

    /**
     * An Order that lead to the creation of this appointment.
     */
    protected ResourceReference order;

    /**
     * List of participants involved in the appointment.
     */
    protected List<AppointmentParticipantComponent> participant = new ArrayList<AppointmentParticipantComponent>();

    /**
     * Who recorded the appointment.
     */
    protected ResourceReference recorder;

    /**
     * Date when the sensitivity was recorded.
     */
    protected DateTime recordedDate;

    public Appointment() {
      super();
    }

    public Appointment(Instant start, Instant end) {
      super();
      this.start = start;
      this.end = end;
    }

    /**
     * @return {@link #identifier} (This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #priority} (The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).)
     */
    public Integer getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).)
     */
    public Appointment setPriority(Integer value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).
     */
    public int getPrioritySimple() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).
     */
    public Appointment setPrioritySimple(int value) { 
      if (value == -1)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Integer();
        this.priority.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.)
     */
    public Code getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.)
     */
    public Appointment setStatus(Code value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    public String getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    public Appointment setStatusSimple(String value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Code();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #description} (The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.)
     */
    public Appointment setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    public Appointment setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #start} (Date/Time that the appointment is to take place.)
     */
    public Instant getStart() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (Date/Time that the appointment is to take place.)
     */
    public Appointment setStart(Instant value) { 
      this.start = value;
      return this;
    }

    /**
     * @return Date/Time that the appointment is to take place.
     */
    public DateAndTime getStartSimple() { 
      return this.start == null ? null : this.start.getValue();
    }

    /**
     * @param value Date/Time that the appointment is to take place.
     */
    public Appointment setStartSimple(DateAndTime value) { 
        if (this.start == null)
          this.start = new Instant();
        this.start.setValue(value);
      return this;
    }

    /**
     * @return {@link #end} (Date/Time that the appointment is to conclude.)
     */
    public Instant getEnd() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Date/Time that the appointment is to conclude.)
     */
    public Appointment setEnd(Instant value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Date/Time that the appointment is to conclude.
     */
    public DateAndTime getEndSimple() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Date/Time that the appointment is to conclude.
     */
    public Appointment setEndSimple(DateAndTime value) { 
        if (this.end == null)
          this.end = new Instant();
        this.end.setValue(value);
      return this;
    }

    /**
     * @return {@link #schedule} (The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment.)
     */
    public Schedule getSchedule() { 
      return this.schedule;
    }

    /**
     * @param value {@link #schedule} (The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment.)
     */
    public Appointment setSchedule(Schedule value) { 
      this.schedule = value;
      return this;
    }

    /**
     * @return {@link #timezone} (The timezone should be a value referenced from a timezone database.)
     */
    public String_ getTimezone() { 
      return this.timezone;
    }

    /**
     * @param value {@link #timezone} (The timezone should be a value referenced from a timezone database.)
     */
    public Appointment setTimezone(String_ value) { 
      this.timezone = value;
      return this;
    }

    /**
     * @return The timezone should be a value referenced from a timezone database.
     */
    public String getTimezoneSimple() { 
      return this.timezone == null ? null : this.timezone.getValue();
    }

    /**
     * @param value The timezone should be a value referenced from a timezone database.
     */
    public Appointment setTimezoneSimple(String value) { 
      if (value == null)
        this.timezone = null;
      else {
        if (this.timezone == null)
          this.timezone = new String_();
        this.timezone.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #slot} (The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    public List<ResourceReference> getSlot() { 
      return this.slot;
    }

    // syntactic sugar
    /**
     * @return {@link #slot} (The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    public ResourceReference addSlot() { 
      ResourceReference t = new ResourceReference();
      this.slot.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The primary location that this appointment is to take place.)
     */
    public ResourceReference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (The primary location that this appointment is to take place.)
     */
    public Appointment setLocation(ResourceReference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #comment} (Additional comments about the appointment.)
     */
    public String_ getComment() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Additional comments about the appointment.)
     */
    public Appointment setComment(String_ value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Additional comments about the appointment.
     */
    public String getCommentSimple() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Additional comments about the appointment.
     */
    public Appointment setCommentSimple(String value) { 
      if (value == null)
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new String_();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #order} (An Order that lead to the creation of this appointment.)
     */
    public ResourceReference getOrder() { 
      return this.order;
    }

    /**
     * @param value {@link #order} (An Order that lead to the creation of this appointment.)
     */
    public Appointment setOrder(ResourceReference value) { 
      this.order = value;
      return this;
    }

    /**
     * @return {@link #participant} (List of participants involved in the appointment.)
     */
    public List<AppointmentParticipantComponent> getParticipant() { 
      return this.participant;
    }

    // syntactic sugar
    /**
     * @return {@link #participant} (List of participants involved in the appointment.)
     */
    public AppointmentParticipantComponent addParticipant() { 
      AppointmentParticipantComponent t = new AppointmentParticipantComponent();
      this.participant.add(t);
      return t;
    }

    /**
     * @return {@link #recorder} (Who recorded the appointment.)
     */
    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Who recorded the appointment.)
     */
    public Appointment setRecorder(ResourceReference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recordedDate} (Date when the sensitivity was recorded.)
     */
    public DateTime getRecordedDate() { 
      return this.recordedDate;
    }

    /**
     * @param value {@link #recordedDate} (Date when the sensitivity was recorded.)
     */
    public Appointment setRecordedDate(DateTime value) { 
      this.recordedDate = value;
      return this;
    }

    /**
     * @return Date when the sensitivity was recorded.
     */
    public DateAndTime getRecordedDateSimple() { 
      return this.recordedDate == null ? null : this.recordedDate.getValue();
    }

    /**
     * @param value Date when the sensitivity was recorded.
     */
    public Appointment setRecordedDateSimple(DateAndTime value) { 
      if (value == null)
        this.recordedDate = null;
      else {
        if (this.recordedDate == null)
          this.recordedDate = new DateTime();
        this.recordedDate.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("priority", "integer", "The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("status", "code", "Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("description", "string", "The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("start", "instant", "Date/Time that the appointment is to take place.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "instant", "Date/Time that the appointment is to conclude.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("schedule", "Schedule", "The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment.", 0, java.lang.Integer.MAX_VALUE, schedule));
        childrenList.add(new Property("timezone", "string", "The timezone should be a value referenced from a timezone database.", 0, java.lang.Integer.MAX_VALUE, timezone));
        childrenList.add(new Property("slot", "Resource(Slot)", "The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.", 0, java.lang.Integer.MAX_VALUE, slot));
        childrenList.add(new Property("location", "Resource(Location)", "The primary location that this appointment is to take place.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("comment", "string", "Additional comments about the appointment.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("order", "Resource(Order)", "An Order that lead to the creation of this appointment.", 0, java.lang.Integer.MAX_VALUE, order));
        childrenList.add(new Property("participant", "", "List of participants involved in the appointment.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("recorder", "Resource(Practitioner|Patient|RelatedPerson)", "Who recorded the appointment.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("recordedDate", "dateTime", "Date when the sensitivity was recorded.", 0, java.lang.Integer.MAX_VALUE, recordedDate));
      }

      public Appointment copy() {
        Appointment dst = new Appointment();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.priority = priority == null ? null : priority.copy();
        dst.status = status == null ? null : status.copy();
        dst.description = description == null ? null : description.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.schedule = schedule == null ? null : schedule.copy();
        dst.timezone = timezone == null ? null : timezone.copy();
        dst.slot = new ArrayList<ResourceReference>();
        for (ResourceReference i : slot)
          dst.slot.add(i.copy());
        dst.location = location == null ? null : location.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.order = order == null ? null : order.copy();
        dst.participant = new ArrayList<AppointmentParticipantComponent>();
        for (AppointmentParticipantComponent i : participant)
          dst.participant.add(i.copy(dst));
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.recordedDate = recordedDate == null ? null : recordedDate.copy();
        return dst;
      }

      protected Appointment typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Appointment;
   }


}

