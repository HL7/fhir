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
 * A scheduled healthcare event for a patient and/or practitioner(s) where a service may take place at a specific date/time.
 */
public class Appointment extends DomainResource {

    public enum Participantrequired {
        REQUIRED, // The participant is required to attend the appointment.
        OPTIONAL, // The participant may optionally attend the appointment.
        INFORMATIONONLY, // The participant is not required to attend the appointment (appointment is about them, not for them).
        NULL; // added to help the parsers
        public static Participantrequired fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return REQUIRED;
        if ("optional".equals(codeString))
          return OPTIONAL;
        if ("information-only".equals(codeString))
          return INFORMATIONONLY;
        throw new Exception("Unknown Participantrequired code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUIRED: return "required";
            case OPTIONAL: return "optional";
            case INFORMATIONONLY: return "information-only";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUIRED: return "The participant is required to attend the appointment.";
            case OPTIONAL: return "The participant may optionally attend the appointment.";
            case INFORMATIONONLY: return "The participant is not required to attend the appointment (appointment is about them, not for them).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUIRED: return "required";
            case OPTIONAL: return "optional";
            case INFORMATIONONLY: return "information-only";
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
          return Participantrequired.REQUIRED;
        if ("optional".equals(codeString))
          return Participantrequired.OPTIONAL;
        if ("information-only".equals(codeString))
          return Participantrequired.INFORMATIONONLY;
        throw new Exception("Unknown Participantrequired code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Participantrequired.REQUIRED)
        return "required";
      if (code == Participantrequired.OPTIONAL)
        return "optional";
      if (code == Participantrequired.INFORMATIONONLY)
        return "information-only";
      return "?";
      }
    }

    public enum Participationstatus {
        ACCEPTED, // The participant has accepted the appointment.
        DECLINED, // The participant has declined the appointment and will not participate in the appointment.
        TENTATIVE, // The participant has  tentatively accepted the appointment. This could be automatically created by a system and requires further processing before it can be accepted. There is no commitment that attendance will occur.
        INPROCESS, // The participant has started the appointment.
        COMPLETED, // The participant's involvement in the appointment has been completed.
        NEEDSACTION, // The participant needs to indicate if they accept the appointment by changing this status to one of the other statuses.
        NULL; // added to help the parsers
        public static Participationstatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("accepted".equals(codeString))
          return ACCEPTED;
        if ("declined".equals(codeString))
          return DECLINED;
        if ("tentative".equals(codeString))
          return TENTATIVE;
        if ("in-process".equals(codeString))
          return INPROCESS;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("needs-action".equals(codeString))
          return NEEDSACTION;
        throw new Exception("Unknown Participationstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ACCEPTED: return "accepted";
            case DECLINED: return "declined";
            case TENTATIVE: return "tentative";
            case INPROCESS: return "in-process";
            case COMPLETED: return "completed";
            case NEEDSACTION: return "needs-action";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ACCEPTED: return "The participant has accepted the appointment.";
            case DECLINED: return "The participant has declined the appointment and will not participate in the appointment.";
            case TENTATIVE: return "The participant has  tentatively accepted the appointment. This could be automatically created by a system and requires further processing before it can be accepted. There is no commitment that attendance will occur.";
            case INPROCESS: return "The participant has started the appointment.";
            case COMPLETED: return "The participant's involvement in the appointment has been completed.";
            case NEEDSACTION: return "The participant needs to indicate if they accept the appointment by changing this status to one of the other statuses.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ACCEPTED: return "accepted";
            case DECLINED: return "declined";
            case TENTATIVE: return "tentative";
            case INPROCESS: return "in-process";
            case COMPLETED: return "completed";
            case NEEDSACTION: return "needs-action";
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
          return Participationstatus.ACCEPTED;
        if ("declined".equals(codeString))
          return Participationstatus.DECLINED;
        if ("tentative".equals(codeString))
          return Participationstatus.TENTATIVE;
        if ("in-process".equals(codeString))
          return Participationstatus.INPROCESS;
        if ("completed".equals(codeString))
          return Participationstatus.COMPLETED;
        if ("needs-action".equals(codeString))
          return Participationstatus.NEEDSACTION;
        throw new Exception("Unknown Participationstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Participationstatus.ACCEPTED)
        return "accepted";
      if (code == Participationstatus.DECLINED)
        return "declined";
      if (code == Participationstatus.TENTATIVE)
        return "tentative";
      if (code == Participationstatus.INPROCESS)
        return "in-process";
      if (code == Participationstatus.COMPLETED)
        return "completed";
      if (code == Participationstatus.NEEDSACTION)
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
         * A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.
         */
        protected Reference actor;

        /**
         * The actual object that is the target of the reference (A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.)
         */
        protected Resource actorTarget;

        /**
         * Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        protected Enumeration<Participantrequired> required;

        /**
         * Participation status of the Patient.
         */
        protected Enumeration<Participationstatus> status;

        private static final long serialVersionUID = -16788247L;

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

        /**
         * @return {@link #type} (Role of participant in the appointment.)
         */
    // syntactic sugar
        public CodeableConcept addType() { //3
          CodeableConcept t = new CodeableConcept();
          this.type.add(t);
          return t;
        }

        /**
         * @return {@link #actor} (A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.)
         */
        public Reference getActor() { 
          return this.actor;
        }

        /**
         * @param value {@link #actor} (A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.)
         */
        public AppointmentParticipantComponent setActor(Reference value) { 
          this.actor = value;
          return this;
        }

        /**
         * @return {@link #actor} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.)
         */
        public Resource getActorTarget() { 
          return this.actorTarget;
        }

        /**
         * @param value {@link #actor} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.)
         */
        public AppointmentParticipantComponent setActorTarget(Resource value) { 
          this.actorTarget = value;
          return this;
        }

        /**
         * @return {@link #required} (Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public Enumeration<Participantrequired> getRequiredElement() { 
          return this.required;
        }

        /**
         * @param value {@link #required} (Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.). This is the underlying object with id, value and extensions. The accessor "getRequired" gives direct access to the value
         */
        public AppointmentParticipantComponent setRequiredElement(Enumeration<Participantrequired> value) { 
          this.required = value;
          return this;
        }

        /**
         * @return Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        public Participantrequired getRequired() { 
          return this.required == null ? null : this.required.getValue();
        }

        /**
         * @param value Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.
         */
        public AppointmentParticipantComponent setRequired(Participantrequired value) { 
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
         * @return {@link #status} (Participation status of the Patient.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public Enumeration<Participationstatus> getStatusElement() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (Participation status of the Patient.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public AppointmentParticipantComponent setStatusElement(Enumeration<Participationstatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return Participation status of the Patient.
         */
        public Participationstatus getStatus() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value Participation status of the Patient.
         */
        public AppointmentParticipantComponent setStatus(Participationstatus value) { 
            if (this.status == null)
              this.status = new Enumeration<Participationstatus>();
            this.status.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "Role of participant in the appointment.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("actor", "Reference(Any)", "A Person of device that is participating in the appointment, usually Practitioner, Patient, RelatedPerson or Device.", 0, java.lang.Integer.MAX_VALUE, actor));
          childrenList.add(new Property("required", "code", "Is this participant required to be present at the meeting. This covers a use-case where 2 doctors need to meet to discuss the results for a specific patient, and the patient is not required to be present.", 0, java.lang.Integer.MAX_VALUE, required));
          childrenList.add(new Property("status", "code", "Participation status of the Patient.", 0, java.lang.Integer.MAX_VALUE, status));
        }

      public AppointmentParticipantComponent copy() {
        AppointmentParticipantComponent dst = new AppointmentParticipantComponent();
        copyValues(dst);
        dst.type = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : type)
          dst.type.add(i.copy());
        dst.actor = actor == null ? null : actor.copy();
        dst.required = required == null ? null : required.copy();
        dst.status = status == null ? null : status.copy();
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
    protected IntegerType priority;

    /**
     * Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    protected CodeType status;

    /**
     * The type of appointments that is being booked (ideally this would be an identifiable service - which is at a location, rather than the location itself).
     */
    protected CodeableConcept type;

    /**
     * The reason that this appointment is being scheduled, this is more clinical than administrative.
     */
    protected CodeableConcept reason;

    /**
     * The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    protected StringType description;

    /**
     * Date/Time that the appointment is to take place.
     */
    protected InstantType start;

    /**
     * Date/Time that the appointment is to conclude.
     */
    protected InstantType end;

    /**
     * The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.
     */
    protected List<Reference> slot = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    protected List<Slot> slotTarget = new ArrayList<Slot>();


    /**
     * The primary location that this appointment is to take place.
     */
    protected Reference location;

    /**
     * The actual object that is the target of the reference (The primary location that this appointment is to take place.)
     */
    protected Location locationTarget;

    /**
     * Additional comments about the appointment.
     */
    protected StringType comment;

    /**
     * An Order that lead to the creation of this appointment.
     */
    protected Reference order;

    /**
     * The actual object that is the target of the reference (An Order that lead to the creation of this appointment.)
     */
    protected Order orderTarget;

    /**
     * List of participants involved in the appointment.
     */
    protected List<AppointmentParticipantComponent> participant = new ArrayList<AppointmentParticipantComponent>();

    /**
     * Who recorded the appointment.
     */
    protected Reference lastModifiedBy;

    /**
     * The actual object that is the target of the reference (Who recorded the appointment.)
     */
    protected Resource lastModifiedByTarget;

    /**
     * Date when the appointment was recorded.
     */
    protected DateTimeType lastModified;

    private static final long serialVersionUID = -57274067L;

    public Appointment() {
      super();
    }

    public Appointment(InstantType start, InstantType end) {
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

    /**
     * @return {@link #identifier} (This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #priority} (The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public IntegerType getPriorityElement() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public Appointment setPriorityElement(IntegerType value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).
     */
    public int getPriority() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).
     */
    public Appointment setPriority(int value) { 
      if (value == -1)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new IntegerType();
        this.priority.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public CodeType getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Appointment setStatusElement(CodeType value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    public String getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.
     */
    public Appointment setStatus(String value) { 
      if (Utilities.noString(value))
        this.status = null;
      else {
        if (this.status == null)
          this.status = new CodeType();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #type} (The type of appointments that is being booked (ideally this would be an identifiable service - which is at a location, rather than the location itself).)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The type of appointments that is being booked (ideally this would be an identifiable service - which is at a location, rather than the location itself).)
     */
    public Appointment setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #reason} (The reason that this appointment is being scheduled, this is more clinical than administrative.)
     */
    public CodeableConcept getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (The reason that this appointment is being scheduled, this is more clinical than administrative.)
     */
    public Appointment setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #description} (The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public Appointment setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.
     */
    public Appointment setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #start} (Date/Time that the appointment is to take place.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
     */
    public InstantType getStartElement() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (Date/Time that the appointment is to take place.). This is the underlying object with id, value and extensions. The accessor "getStart" gives direct access to the value
     */
    public Appointment setStartElement(InstantType value) { 
      this.start = value;
      return this;
    }

    /**
     * @return Date/Time that the appointment is to take place.
     */
    public DateAndTime getStart() { 
      return this.start == null ? null : this.start.getValue();
    }

    /**
     * @param value Date/Time that the appointment is to take place.
     */
    public Appointment setStart(DateAndTime value) { 
        if (this.start == null)
          this.start = new InstantType();
        this.start.setValue(value);
      return this;
    }

    /**
     * @return {@link #end} (Date/Time that the appointment is to conclude.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public InstantType getEndElement() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Date/Time that the appointment is to conclude.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public Appointment setEndElement(InstantType value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Date/Time that the appointment is to conclude.
     */
    public DateAndTime getEnd() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Date/Time that the appointment is to conclude.
     */
    public Appointment setEnd(DateAndTime value) { 
        if (this.end == null)
          this.end = new InstantType();
        this.end.setValue(value);
      return this;
    }

    /**
     * @return {@link #slot} (The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    public List<Reference> getSlot() { 
      return this.slot;
    }

    /**
     * @return {@link #slot} (The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    // syntactic sugar
    public Reference addSlot() { //3
      Reference t = new Reference();
      this.slot.add(t);
      return t;
    }

    /**
     * @return {@link #slot} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    public List<Slot> getSlotTarget() { 
      return this.slotTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #slot} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.)
     */
    public Slot addSlotTarget() { 
      Slot r = new Slot();
      this.slotTarget.add(r);
      return r;
    }

    /**
     * @return {@link #location} (The primary location that this appointment is to take place.)
     */
    public Reference getLocation() { 
      return this.location;
    }

    /**
     * @param value {@link #location} (The primary location that this appointment is to take place.)
     */
    public Appointment setLocation(Reference value) { 
      this.location = value;
      return this;
    }

    /**
     * @return {@link #location} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The primary location that this appointment is to take place.)
     */
    public Location getLocationTarget() { 
      return this.locationTarget;
    }

    /**
     * @param value {@link #location} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The primary location that this appointment is to take place.)
     */
    public Appointment setLocationTarget(Location value) { 
      this.locationTarget = value;
      return this;
    }

    /**
     * @return {@link #comment} (Additional comments about the appointment.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public StringType getCommentElement() { 
      return this.comment;
    }

    /**
     * @param value {@link #comment} (Additional comments about the appointment.). This is the underlying object with id, value and extensions. The accessor "getComment" gives direct access to the value
     */
    public Appointment setCommentElement(StringType value) { 
      this.comment = value;
      return this;
    }

    /**
     * @return Additional comments about the appointment.
     */
    public String getComment() { 
      return this.comment == null ? null : this.comment.getValue();
    }

    /**
     * @param value Additional comments about the appointment.
     */
    public Appointment setComment(String value) { 
      if (Utilities.noString(value))
        this.comment = null;
      else {
        if (this.comment == null)
          this.comment = new StringType();
        this.comment.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #order} (An Order that lead to the creation of this appointment.)
     */
    public Reference getOrder() { 
      return this.order;
    }

    /**
     * @param value {@link #order} (An Order that lead to the creation of this appointment.)
     */
    public Appointment setOrder(Reference value) { 
      this.order = value;
      return this;
    }

    /**
     * @return {@link #order} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (An Order that lead to the creation of this appointment.)
     */
    public Order getOrderTarget() { 
      return this.orderTarget;
    }

    /**
     * @param value {@link #order} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (An Order that lead to the creation of this appointment.)
     */
    public Appointment setOrderTarget(Order value) { 
      this.orderTarget = value;
      return this;
    }

    /**
     * @return {@link #participant} (List of participants involved in the appointment.)
     */
    public List<AppointmentParticipantComponent> getParticipant() { 
      return this.participant;
    }

    /**
     * @return {@link #participant} (List of participants involved in the appointment.)
     */
    // syntactic sugar
    public AppointmentParticipantComponent addParticipant() { //3
      AppointmentParticipantComponent t = new AppointmentParticipantComponent();
      this.participant.add(t);
      return t;
    }

    /**
     * @return {@link #lastModifiedBy} (Who recorded the appointment.)
     */
    public Reference getLastModifiedBy() { 
      return this.lastModifiedBy;
    }

    /**
     * @param value {@link #lastModifiedBy} (Who recorded the appointment.)
     */
    public Appointment setLastModifiedBy(Reference value) { 
      this.lastModifiedBy = value;
      return this;
    }

    /**
     * @return {@link #lastModifiedBy} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Who recorded the appointment.)
     */
    public Resource getLastModifiedByTarget() { 
      return this.lastModifiedByTarget;
    }

    /**
     * @param value {@link #lastModifiedBy} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Who recorded the appointment.)
     */
    public Appointment setLastModifiedByTarget(Resource value) { 
      this.lastModifiedByTarget = value;
      return this;
    }

    /**
     * @return {@link #lastModified} (Date when the appointment was recorded.). This is the underlying object with id, value and extensions. The accessor "getLastModified" gives direct access to the value
     */
    public DateTimeType getLastModifiedElement() { 
      return this.lastModified;
    }

    /**
     * @param value {@link #lastModified} (Date when the appointment was recorded.). This is the underlying object with id, value and extensions. The accessor "getLastModified" gives direct access to the value
     */
    public Appointment setLastModifiedElement(DateTimeType value) { 
      this.lastModified = value;
      return this;
    }

    /**
     * @return Date when the appointment was recorded.
     */
    public DateAndTime getLastModified() { 
      return this.lastModified == null ? null : this.lastModified.getValue();
    }

    /**
     * @param value Date when the appointment was recorded.
     */
    public Appointment setLastModified(DateAndTime value) { 
      if (value == null)
        this.lastModified = null;
      else {
        if (this.lastModified == null)
          this.lastModified = new DateTimeType();
        this.lastModified.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("priority", "integer", "The priority of the appointment. Can be used to make informed decisions if needing to re-prioritize appointments. (The iCal Standard specifies 0 as undefined, 1 as highest, 9 as lowest priority) (Need to change back to CodeableConcept).", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("status", "code", "Each of the participants has their own participation status which indicates their involvement in the process, however this status indicates the shared status.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("type", "CodeableConcept", "The type of appointments that is being booked (ideally this would be an identifiable service - which is at a location, rather than the location itself).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("reason", "CodeableConcept", "The reason that this appointment is being scheduled, this is more clinical than administrative.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("description", "string", "The brief description of the appointment as would be shown on a subject line in a meeting request, or appointment list. Detailed or expanded information should be put in the comment field.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("start", "instant", "Date/Time that the appointment is to take place.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "instant", "Date/Time that the appointment is to conclude.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("slot", "Reference(Slot)", "The slot that this appointment is filling. If provided then the schedule will not be provided as slots are not recursive, and the start/end values MUST be the same as from the slot.", 0, java.lang.Integer.MAX_VALUE, slot));
        childrenList.add(new Property("location", "Reference(Location)", "The primary location that this appointment is to take place.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("comment", "string", "Additional comments about the appointment.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("order", "Reference(Order)", "An Order that lead to the creation of this appointment.", 0, java.lang.Integer.MAX_VALUE, order));
        childrenList.add(new Property("participant", "", "List of participants involved in the appointment.", 0, java.lang.Integer.MAX_VALUE, participant));
        childrenList.add(new Property("lastModifiedBy", "Reference(Practitioner|Patient|RelatedPerson)", "Who recorded the appointment.", 0, java.lang.Integer.MAX_VALUE, lastModifiedBy));
        childrenList.add(new Property("lastModified", "dateTime", "Date when the appointment was recorded.", 0, java.lang.Integer.MAX_VALUE, lastModified));
      }

      public Appointment copy() {
        Appointment dst = new Appointment();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.priority = priority == null ? null : priority.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.description = description == null ? null : description.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.slot = new ArrayList<Reference>();
        for (Reference i : slot)
          dst.slot.add(i.copy());
        dst.location = location == null ? null : location.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.order = order == null ? null : order.copy();
        dst.participant = new ArrayList<AppointmentParticipantComponent>();
        for (AppointmentParticipantComponent i : participant)
          dst.participant.add(i.copy());
        dst.lastModifiedBy = lastModifiedBy == null ? null : lastModifiedBy.copy();
        dst.lastModified = lastModified == null ? null : lastModified.copy();
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

