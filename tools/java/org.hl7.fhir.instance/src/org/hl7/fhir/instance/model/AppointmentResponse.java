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

// Generated on Thu, Jan 23, 2014 01:09-0600 for FHIR v0.12

import java.util.*;

/**
 * A scheduled appointment for a patient and/or practitioner(s) where a service may take place.
 */
public class AppointmentResponse extends Resource {

    public enum Participantstatus {
        accepted, // The participant has accepted the appointment.
        declined, // The participant has declined the appointment.
        tentative, // The participant has tentative the appointment.
        inprocess, // The participant has in-process the appointment.
        completed, // The participant has completed the appointment.
        needsaction, // The participant has needs-action the appointment.
        Null; // added to help the parsers
        public static Participantstatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown Participantstatus code '"+codeString+"'");
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

  public static class ParticipantstatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("accepted".equals(codeString))
          return Participantstatus.accepted;
        if ("declined".equals(codeString))
          return Participantstatus.declined;
        if ("tentative".equals(codeString))
          return Participantstatus.tentative;
        if ("in-process".equals(codeString))
          return Participantstatus.inprocess;
        if ("completed".equals(codeString))
          return Participantstatus.completed;
        if ("needs-action".equals(codeString))
          return Participantstatus.needsaction;
        throw new Exception("Unknown Participantstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Participantstatus.accepted)
        return "accepted";
      if (code == Participantstatus.declined)
        return "declined";
      if (code == Participantstatus.tentative)
        return "tentative";
      if (code == Participantstatus.inprocess)
        return "in-process";
      if (code == Participantstatus.completed)
        return "completed";
      if (code == Participantstatus.needsaction)
        return "needs-action";
      return "?";
      }
    }

    /**
     * This records identifiers associated with this appointment concern that are defined by business processed and/ or used to refer to it when a direct URL reference to the resource itself is not appropriate (e.g. in CDA documents, or in written / printed documentation).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Parent appointment that this response is replying to.
     */
    protected ResourceReference appointment;

    /**
     * Role of participant in the appointment.
     */
    protected List<CodeableConcept> participantType = new ArrayList<CodeableConcept>();

    /**
     * A Person of device that is participating in the appointment.
     */
    protected List<ResourceReference> individual = new ArrayList<ResourceReference>();

    /**
     * Participation status of the Patient.
     */
    protected Enumeration<Participantstatus> participantStatus;

    /**
     * Additional comments about the appointment.
     */
    protected String_ comment;

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
     * Who recorded the appointment response.
     */
    protected ResourceReference recorder;

    /**
     * Date when the response was recorded or last updated.
     */
    protected DateTime recordedDate;

    public AppointmentResponse() {
      super();
    }

    public AppointmentResponse(ResourceReference appointment, Enumeration<Participantstatus> participantStatus) {
      super();
      this.appointment = appointment;
      this.participantStatus = participantStatus;
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
     * @return {@link #appointment} (Parent appointment that this response is replying to.)
     */
    public ResourceReference getAppointment() { 
      return this.appointment;
    }

    /**
     * @param value {@link #appointment} (Parent appointment that this response is replying to.)
     */
    public AppointmentResponse setAppointment(ResourceReference value) { 
      this.appointment = value;
      return this;
    }

    /**
     * @return {@link #participantType} (Role of participant in the appointment.)
     */
    public List<CodeableConcept> getParticipantType() { 
      return this.participantType;
    }

    // syntactic sugar
    /**
     * @return {@link #participantType} (Role of participant in the appointment.)
     */
    public CodeableConcept addParticipantType() { 
      CodeableConcept t = new CodeableConcept();
      this.participantType.add(t);
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
     * @return {@link #participantStatus} (Participation status of the Patient.)
     */
    public Enumeration<Participantstatus> getParticipantStatus() { 
      return this.participantStatus;
    }

    /**
     * @param value {@link #participantStatus} (Participation status of the Patient.)
     */
    public AppointmentResponse setParticipantStatus(Enumeration<Participantstatus> value) { 
      this.participantStatus = value;
      return this;
    }

    /**
     * @return Participation status of the Patient.
     */
    public Participantstatus getParticipantStatusSimple() { 
      return this.participantStatus == null ? null : this.participantStatus.getValue();
    }

    /**
     * @param value Participation status of the Patient.
     */
    public AppointmentResponse setParticipantStatusSimple(Participantstatus value) { 
        if (this.participantStatus == null)
          this.participantStatus = new Enumeration<Participantstatus>();
        this.participantStatus.setValue(value);
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
    public AppointmentResponse setComment(String_ value) { 
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
    public AppointmentResponse setCommentSimple(String value) { 
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
     * @return {@link #start} (Date/Time that the appointment is to take place.)
     */
    public Instant getStart() { 
      return this.start;
    }

    /**
     * @param value {@link #start} (Date/Time that the appointment is to take place.)
     */
    public AppointmentResponse setStart(Instant value) { 
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
    public AppointmentResponse setStartSimple(DateAndTime value) { 
      if (value == null)
        this.start = null;
      else {
        if (this.start == null)
          this.start = new Instant();
        this.start.setValue(value);
      }
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
    public AppointmentResponse setEnd(Instant value) { 
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
    public AppointmentResponse setEndSimple(DateAndTime value) { 
      if (value == null)
        this.end = null;
      else {
        if (this.end == null)
          this.end = new Instant();
        this.end.setValue(value);
      }
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
    public AppointmentResponse setSchedule(Schedule value) { 
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
    public AppointmentResponse setTimezone(String_ value) { 
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
    public AppointmentResponse setTimezoneSimple(String value) { 
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
     * @return {@link #recorder} (Who recorded the appointment response.)
     */
    public ResourceReference getRecorder() { 
      return this.recorder;
    }

    /**
     * @param value {@link #recorder} (Who recorded the appointment response.)
     */
    public AppointmentResponse setRecorder(ResourceReference value) { 
      this.recorder = value;
      return this;
    }

    /**
     * @return {@link #recordedDate} (Date when the response was recorded or last updated.)
     */
    public DateTime getRecordedDate() { 
      return this.recordedDate;
    }

    /**
     * @param value {@link #recordedDate} (Date when the response was recorded or last updated.)
     */
    public AppointmentResponse setRecordedDate(DateTime value) { 
      this.recordedDate = value;
      return this;
    }

    /**
     * @return Date when the response was recorded or last updated.
     */
    public DateAndTime getRecordedDateSimple() { 
      return this.recordedDate == null ? null : this.recordedDate.getValue();
    }

    /**
     * @param value Date when the response was recorded or last updated.
     */
    public AppointmentResponse setRecordedDateSimple(DateAndTime value) { 
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
        childrenList.add(new Property("appointment", "Resource(Appointment)", "Parent appointment that this response is replying to.", 0, java.lang.Integer.MAX_VALUE, appointment));
        childrenList.add(new Property("participantType", "CodeableConcept", "Role of participant in the appointment.", 0, java.lang.Integer.MAX_VALUE, participantType));
        childrenList.add(new Property("individual", "Resource(Practitioner|Patient|RelatedPerson|Device)", "A Person of device that is participating in the appointment.", 0, java.lang.Integer.MAX_VALUE, individual));
        childrenList.add(new Property("participantStatus", "code", "Participation status of the Patient.", 0, java.lang.Integer.MAX_VALUE, participantStatus));
        childrenList.add(new Property("comment", "string", "Additional comments about the appointment.", 0, java.lang.Integer.MAX_VALUE, comment));
        childrenList.add(new Property("start", "instant", "Date/Time that the appointment is to take place.", 0, java.lang.Integer.MAX_VALUE, start));
        childrenList.add(new Property("end", "instant", "Date/Time that the appointment is to conclude.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("schedule", "Schedule", "The recurrence schedule for the appointment. The end date in the schedule marks the end of the recurrence(s), not the end of an individual appointment.", 0, java.lang.Integer.MAX_VALUE, schedule));
        childrenList.add(new Property("timezone", "string", "The timezone should be a value referenced from a timezone database.", 0, java.lang.Integer.MAX_VALUE, timezone));
        childrenList.add(new Property("recorder", "Resource(Practitioner|Patient|RelatedPerson)", "Who recorded the appointment response.", 0, java.lang.Integer.MAX_VALUE, recorder));
        childrenList.add(new Property("recordedDate", "dateTime", "Date when the response was recorded or last updated.", 0, java.lang.Integer.MAX_VALUE, recordedDate));
      }

      public AppointmentResponse copy() {
        AppointmentResponse dst = new AppointmentResponse();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.appointment = appointment == null ? null : appointment.copy();
        dst.participantType = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : participantType)
          dst.participantType.add(i.copy());
        dst.individual = new ArrayList<ResourceReference>();
        for (ResourceReference i : individual)
          dst.individual.add(i.copy());
        dst.participantStatus = participantStatus == null ? null : participantStatus.copy();
        dst.comment = comment == null ? null : comment.copy();
        dst.start = start == null ? null : start.copy();
        dst.end = end == null ? null : end.copy();
        dst.schedule = schedule == null ? null : schedule.copy();
        dst.timezone = timezone == null ? null : timezone.copy();
        dst.recorder = recorder == null ? null : recorder.copy();
        dst.recordedDate = recordedDate == null ? null : recordedDate.copy();
        return dst;
      }

      protected AppointmentResponse typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.AppointmentResponse;
   }


}

