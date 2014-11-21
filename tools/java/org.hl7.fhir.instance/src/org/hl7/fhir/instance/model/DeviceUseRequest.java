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
 * Represents a request for the use of a device.
 */
public class DeviceUseRequest extends DomainResource {

    public enum DeviceUseRequestStatus {
        REQUESTED, // The request has been placed.
        RECEIVED, // The receiving system has received the request but not yet decided whether it will be performed.
        ACCEPTED, // The receiving system has accepted the request but work has not yet commenced.
        INPROGRESS, // The work to fulfill the order is happening.
        REVIEW, // The work is complete, and the outcomes are being reviewed for approval.
        COMPLETED, // The work has been complete, the report(s) released, and no further work is planned.
        SUSPENDED, // The request has been held by originating system/user request.
        REJECTED, // The receiving system has declined to fulfill the request.
        FAILED, // The request was attempted, but due to some procedural error, it could not be completed.
        NULL; // added to help the parsers
        public static DeviceUseRequestStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("received".equals(codeString))
          return RECEIVED;
        if ("accepted".equals(codeString))
          return ACCEPTED;
        if ("in progress".equals(codeString))
          return INPROGRESS;
        if ("review".equals(codeString))
          return REVIEW;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("suspended".equals(codeString))
          return SUSPENDED;
        if ("rejected".equals(codeString))
          return REJECTED;
        if ("failed".equals(codeString))
          return FAILED;
        throw new Exception("Unknown DeviceUseRequestStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUESTED: return "requested";
            case RECEIVED: return "received";
            case ACCEPTED: return "accepted";
            case INPROGRESS: return "in progress";
            case REVIEW: return "review";
            case COMPLETED: return "completed";
            case SUSPENDED: return "suspended";
            case REJECTED: return "rejected";
            case FAILED: return "failed";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUESTED: return "The request has been placed.";
            case RECEIVED: return "The receiving system has received the request but not yet decided whether it will be performed.";
            case ACCEPTED: return "The receiving system has accepted the request but work has not yet commenced.";
            case INPROGRESS: return "The work to fulfill the order is happening.";
            case REVIEW: return "The work is complete, and the outcomes are being reviewed for approval.";
            case COMPLETED: return "The work has been complete, the report(s) released, and no further work is planned.";
            case SUSPENDED: return "The request has been held by originating system/user request.";
            case REJECTED: return "The receiving system has declined to fulfill the request.";
            case FAILED: return "The request was attempted, but due to some procedural error, it could not be completed.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUESTED: return "requested";
            case RECEIVED: return "received";
            case ACCEPTED: return "accepted";
            case INPROGRESS: return "in progress";
            case REVIEW: return "review";
            case COMPLETED: return "completed";
            case SUSPENDED: return "suspended";
            case REJECTED: return "rejected";
            case FAILED: return "failed";
            default: return "?";
          }
        }
    }

  public static class DeviceUseRequestStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return DeviceUseRequestStatus.REQUESTED;
        if ("received".equals(codeString))
          return DeviceUseRequestStatus.RECEIVED;
        if ("accepted".equals(codeString))
          return DeviceUseRequestStatus.ACCEPTED;
        if ("in progress".equals(codeString))
          return DeviceUseRequestStatus.INPROGRESS;
        if ("review".equals(codeString))
          return DeviceUseRequestStatus.REVIEW;
        if ("completed".equals(codeString))
          return DeviceUseRequestStatus.COMPLETED;
        if ("suspended".equals(codeString))
          return DeviceUseRequestStatus.SUSPENDED;
        if ("rejected".equals(codeString))
          return DeviceUseRequestStatus.REJECTED;
        if ("failed".equals(codeString))
          return DeviceUseRequestStatus.FAILED;
        throw new Exception("Unknown DeviceUseRequestStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceUseRequestStatus.REQUESTED)
        return "requested";
      if (code == DeviceUseRequestStatus.RECEIVED)
        return "received";
      if (code == DeviceUseRequestStatus.ACCEPTED)
        return "accepted";
      if (code == DeviceUseRequestStatus.INPROGRESS)
        return "in progress";
      if (code == DeviceUseRequestStatus.REVIEW)
        return "review";
      if (code == DeviceUseRequestStatus.COMPLETED)
        return "completed";
      if (code == DeviceUseRequestStatus.SUSPENDED)
        return "suspended";
      if (code == DeviceUseRequestStatus.REJECTED)
        return "rejected";
      if (code == DeviceUseRequestStatus.FAILED)
        return "failed";
      return "?";
      }
    }

    public enum DeviceUseRequestMode {
        PLANNED, // planned.
        PROPOSED, // proposed.
        ORDERED, // ordered.
        NULL; // added to help the parsers
        public static DeviceUseRequestMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("ordered".equals(codeString))
          return ORDERED;
        throw new Exception("Unknown DeviceUseRequestMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PLANNED: return "planned";
            case PROPOSED: return "proposed";
            case ORDERED: return "ordered";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PLANNED: return "planned.";
            case PROPOSED: return "proposed.";
            case ORDERED: return "ordered.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PLANNED: return "planned";
            case PROPOSED: return "proposed";
            case ORDERED: return "ordered";
            default: return "?";
          }
        }
    }

  public static class DeviceUseRequestModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return DeviceUseRequestMode.PLANNED;
        if ("proposed".equals(codeString))
          return DeviceUseRequestMode.PROPOSED;
        if ("ordered".equals(codeString))
          return DeviceUseRequestMode.ORDERED;
        throw new Exception("Unknown DeviceUseRequestMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceUseRequestMode.PLANNED)
        return "planned";
      if (code == DeviceUseRequestMode.PROPOSED)
        return "proposed";
      if (code == DeviceUseRequestMode.ORDERED)
        return "ordered";
      return "?";
      }
    }

    public enum DeviceUseRequestPriority {
        ROUTINE, // The request has a normal priority.
        URGENT, // The request should be done urgently.
        STAT, // The request is time-critical.
        ASAP, // The request should be acted on as soon as possible.
        NULL; // added to help the parsers
        public static DeviceUseRequestPriority fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return ROUTINE;
        if ("urgent".equals(codeString))
          return URGENT;
        if ("stat".equals(codeString))
          return STAT;
        if ("asap".equals(codeString))
          return ASAP;
        throw new Exception("Unknown DeviceUseRequestPriority code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ROUTINE: return "routine";
            case URGENT: return "urgent";
            case STAT: return "stat";
            case ASAP: return "asap";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ROUTINE: return "The request has a normal priority.";
            case URGENT: return "The request should be done urgently.";
            case STAT: return "The request is time-critical.";
            case ASAP: return "The request should be acted on as soon as possible.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ROUTINE: return "routine";
            case URGENT: return "urgent";
            case STAT: return "stat";
            case ASAP: return "asap";
            default: return "?";
          }
        }
    }

  public static class DeviceUseRequestPriorityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return DeviceUseRequestPriority.ROUTINE;
        if ("urgent".equals(codeString))
          return DeviceUseRequestPriority.URGENT;
        if ("stat".equals(codeString))
          return DeviceUseRequestPriority.STAT;
        if ("asap".equals(codeString))
          return DeviceUseRequestPriority.ASAP;
        throw new Exception("Unknown DeviceUseRequestPriority code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DeviceUseRequestPriority.ROUTINE)
        return "routine";
      if (code == DeviceUseRequestPriority.URGENT)
        return "urgent";
      if (code == DeviceUseRequestPriority.STAT)
        return "stat";
      if (code == DeviceUseRequestPriority.ASAP)
        return "asap";
      return "?";
      }
    }

    /**
     * Body site where the device is to be used.
     */
    protected List<CodeableConcept> bodySite = new ArrayList<CodeableConcept>();

    /**
     * The status of the request.
     */
    protected Enumeration<DeviceUseRequestStatus> status;

    /**
     * The mode of the request.
     */
    protected Enumeration<DeviceUseRequestMode> mode;

    /**
     * The details of the device  to be used.
     */
    protected Reference device;

    /**
     * The actual object that is the target of the reference (The details of the device  to be used.)
     */
    protected Device deviceTarget;

    /**
     * An encounter that provides additional context in which this request is made.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (An encounter that provides additional context in which this request is made.)
     */
    protected Encounter encounterTarget;

    /**
     * Identifiers assigned to this order by the orderer or by the receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Reason or justification for the use of this device.
     */
    protected List<CodeableConcept> indication = new ArrayList<CodeableConcept>();

    /**
     * Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.
     */
    protected List<StringType> notes = new ArrayList<StringType>();

    /**
     * The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.
     */
    protected List<CodeableConcept> prnReason = new ArrayList<CodeableConcept>();

    /**
     * The time when the request was made.
     */
    protected DateTimeType orderedOn;

    /**
     * The time at which the request was made/recorded.
     */
    protected DateTimeType recordedOn;

    /**
     * The patient who will use the device.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who will use the device.)
     */
    protected Patient subjectTarget;

    /**
     * The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
     */
    protected Type timing;

    /**
     * Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    protected Enumeration<DeviceUseRequestPriority> priority;

    private static final long serialVersionUID = -925060392L;

    public DeviceUseRequest() {
      super();
    }

    public DeviceUseRequest(Reference device, Reference subject) {
      super();
      this.device = device;
      this.subject = subject;
    }

    /**
     * @return {@link #bodySite} (Body site where the device is to be used.)
     */
    public List<CodeableConcept> getBodySite() { 
      return this.bodySite;
    }

    /**
     * @return {@link #bodySite} (Body site where the device is to be used.)
     */
    // syntactic sugar
    public CodeableConcept addBodySite() { //3
      CodeableConcept t = new CodeableConcept();
      this.bodySite.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<DeviceUseRequestStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public DeviceUseRequest setStatusElement(Enumeration<DeviceUseRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the request.
     */
    public DeviceUseRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the request.
     */
    public DeviceUseRequest setStatus(DeviceUseRequestStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<DeviceUseRequestStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #mode} (The mode of the request.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public Enumeration<DeviceUseRequestMode> getModeElement() { 
      return this.mode;
    }

    /**
     * @param value {@link #mode} (The mode of the request.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public DeviceUseRequest setModeElement(Enumeration<DeviceUseRequestMode> value) { 
      this.mode = value;
      return this;
    }

    /**
     * @return The mode of the request.
     */
    public DeviceUseRequestMode getMode() { 
      return this.mode == null ? null : this.mode.getValue();
    }

    /**
     * @param value The mode of the request.
     */
    public DeviceUseRequest setMode(DeviceUseRequestMode value) { 
      if (value == null)
        this.mode = null;
      else {
        if (this.mode == null)
          this.mode = new Enumeration<DeviceUseRequestMode>();
        this.mode.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #device} (The details of the device  to be used.)
     */
    public Reference getDevice() { 
      return this.device;
    }

    /**
     * @param value {@link #device} (The details of the device  to be used.)
     */
    public DeviceUseRequest setDevice(Reference value) { 
      this.device = value;
      return this;
    }

    /**
     * @return {@link #device} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The details of the device  to be used.)
     */
    public Device getDeviceTarget() { 
      return this.deviceTarget;
    }

    /**
     * @param value {@link #device} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The details of the device  to be used.)
     */
    public DeviceUseRequest setDeviceTarget(Device value) { 
      this.deviceTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (An encounter that provides additional context in which this request is made.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (An encounter that provides additional context in which this request is made.)
     */
    public DeviceUseRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (An encounter that provides additional context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (An encounter that provides additional context in which this request is made.)
     */
    public DeviceUseRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of this device.)
     */
    public List<CodeableConcept> getIndication() { 
      return this.indication;
    }

    /**
     * @return {@link #indication} (Reason or justification for the use of this device.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
      CodeableConcept t = new CodeableConcept();
      this.indication.add(t);
      return t;
    }

    /**
     * @return {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public List<StringType> getNotes() { 
      return this.notes;
    }

    /**
     * @return {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    // syntactic sugar
    public StringType addNotesElement() {//2 
      StringType t = new StringType();
      this.notes.add(t);
      return t;
    }

    /**
     * @param value {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public DeviceUseRequest addNotes(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.notes.add(t);
      return this;
    }

    /**
     * @param value {@link #notes} (Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.)
     */
    public boolean hasNotes(String value) { 
      for (StringType v : this.notes)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #prnReason} (The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.)
     */
    public List<CodeableConcept> getPrnReason() { 
      return this.prnReason;
    }

    /**
     * @return {@link #prnReason} (The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.)
     */
    // syntactic sugar
    public CodeableConcept addPrnReason() { //3
      CodeableConcept t = new CodeableConcept();
      this.prnReason.add(t);
      return t;
    }

    /**
     * @return {@link #orderedOn} (The time when the request was made.). This is the underlying object with id, value and extensions. The accessor "getOrderedOn" gives direct access to the value
     */
    public DateTimeType getOrderedOnElement() { 
      return this.orderedOn;
    }

    /**
     * @param value {@link #orderedOn} (The time when the request was made.). This is the underlying object with id, value and extensions. The accessor "getOrderedOn" gives direct access to the value
     */
    public DeviceUseRequest setOrderedOnElement(DateTimeType value) { 
      this.orderedOn = value;
      return this;
    }

    /**
     * @return The time when the request was made.
     */
    public DateAndTime getOrderedOn() { 
      return this.orderedOn == null ? null : this.orderedOn.getValue();
    }

    /**
     * @param value The time when the request was made.
     */
    public DeviceUseRequest setOrderedOn(DateAndTime value) { 
      if (value == null)
        this.orderedOn = null;
      else {
        if (this.orderedOn == null)
          this.orderedOn = new DateTimeType();
        this.orderedOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #recordedOn} (The time at which the request was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DateTimeType getRecordedOnElement() { 
      return this.recordedOn;
    }

    /**
     * @param value {@link #recordedOn} (The time at which the request was made/recorded.). This is the underlying object with id, value and extensions. The accessor "getRecordedOn" gives direct access to the value
     */
    public DeviceUseRequest setRecordedOnElement(DateTimeType value) { 
      this.recordedOn = value;
      return this;
    }

    /**
     * @return The time at which the request was made/recorded.
     */
    public DateAndTime getRecordedOn() { 
      return this.recordedOn == null ? null : this.recordedOn.getValue();
    }

    /**
     * @param value The time at which the request was made/recorded.
     */
    public DeviceUseRequest setRecordedOn(DateAndTime value) { 
      if (value == null)
        this.recordedOn = null;
      else {
        if (this.recordedOn == null)
          this.recordedOn = new DateTimeType();
        this.recordedOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (The patient who will use the device.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who will use the device.)
     */
    public DeviceUseRequest setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who will use the device.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who will use the device.)
     */
    public DeviceUseRequest setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public Type getTiming() { 
      return this.timing;
    }

    /**
     * @param value {@link #timing} (The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public DeviceUseRequest setTiming(Type value) { 
      this.timing = value;
      return this;
    }

    /**
     * @return {@link #priority} (Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public Enumeration<DeviceUseRequestPriority> getPriorityElement() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public DeviceUseRequest setPriorityElement(Enumeration<DeviceUseRequestPriority> value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    public DeviceUseRequestPriority getPriority() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.
     */
    public DeviceUseRequest setPriority(DeviceUseRequestPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<DeviceUseRequestPriority>();
        this.priority.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("bodySite", "CodeableConcept", "Body site where the device is to be used.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("status", "code", "The status of the request.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("mode", "code", "The mode of the request.", 0, java.lang.Integer.MAX_VALUE, mode));
        childrenList.add(new Property("device", "Reference(Device)", "The details of the device  to be used.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "An encounter that provides additional context in which this request is made.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the orderer or by the receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("indication", "CodeableConcept", "Reason or justification for the use of this device.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("notes", "string", "Details about this request that were not represented at all or sufficiently in one of the attributes provided in a class. These may include for example a comment, an instruction, or a note associated with the statement.", 0, java.lang.Integer.MAX_VALUE, notes));
        childrenList.add(new Property("prnReason", "CodeableConcept", "The proposed act must be performed if the indicated conditions occur, e.g.., shortness of breath, SpO2 less than x%.", 0, java.lang.Integer.MAX_VALUE, prnReason));
        childrenList.add(new Property("orderedOn", "dateTime", "The time when the request was made.", 0, java.lang.Integer.MAX_VALUE, orderedOn));
        childrenList.add(new Property("recordedOn", "dateTime", "The time at which the request was made/recorded.", 0, java.lang.Integer.MAX_VALUE, recordedOn));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who will use the device.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("timing[x]", "Timing|Period|dateTime", "The timing schedule for the use of the device The Schedule data type allows many different expressions, for example. 'Every 8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:'; '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
        childrenList.add(new Property("priority", "code", "Characterizes how quickly the  use of device must be initiated. Includes concepts such as stat, urgent, routine.", 0, java.lang.Integer.MAX_VALUE, priority));
      }

      public DeviceUseRequest copy() {
        DeviceUseRequest dst = new DeviceUseRequest();
        copyValues(dst);
        dst.bodySite = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : bodySite)
          dst.bodySite.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.mode = mode == null ? null : mode.copy();
        dst.device = device == null ? null : device.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.indication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : indication)
          dst.indication.add(i.copy());
        dst.notes = new ArrayList<StringType>();
        for (StringType i : notes)
          dst.notes.add(i.copy());
        dst.prnReason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : prnReason)
          dst.prnReason.add(i.copy());
        dst.orderedOn = orderedOn == null ? null : orderedOn.copy();
        dst.recordedOn = recordedOn == null ? null : recordedOn.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.priority = priority == null ? null : priority.copy();
        return dst;
      }

      protected DeviceUseRequest typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DeviceUseRequest;
   }


}

