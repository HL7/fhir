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
 * A request to convey information. E.g., the CDS system proposes that an alert be sent to a responsible provider, the CDS system proposes that the public health agency be notified about a reportable condition.
 */
public class CommunicationRequest extends DomainResource {

    public enum CommunicationRequestStatus {
        REQUESTED, // The request has been placed.
        RECEIVED, // The receiving system has received the request but not yet decided whether it will be performed.
        ACCEPTED, // The receiving system has accepted the order, but work has not yet commenced.
        INPROGRESS, // The work to fulfill the order is happening.
        REVIEW, // The work is complete, and the outcomes are being reviewed for approval.
        COMPLETED, // The work has been complete, the report(s) released, and no further work is planned.
        SUSPENDED, // The request has been held by originating system/user request.
        REJECTED, // The receiving system has declined to fulfill the request.
        FAILED, // The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.
        NULL; // added to help the parsers
        public static CommunicationRequestStatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown CommunicationRequestStatus code '"+codeString+"'");
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
            case ACCEPTED: return "The receiving system has accepted the order, but work has not yet commenced.";
            case INPROGRESS: return "The work to fulfill the order is happening.";
            case REVIEW: return "The work is complete, and the outcomes are being reviewed for approval.";
            case COMPLETED: return "The work has been complete, the report(s) released, and no further work is planned.";
            case SUSPENDED: return "The request has been held by originating system/user request.";
            case REJECTED: return "The receiving system has declined to fulfill the request.";
            case FAILED: return "The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.";
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

  public static class CommunicationRequestStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return CommunicationRequestStatus.REQUESTED;
        if ("received".equals(codeString))
          return CommunicationRequestStatus.RECEIVED;
        if ("accepted".equals(codeString))
          return CommunicationRequestStatus.ACCEPTED;
        if ("in progress".equals(codeString))
          return CommunicationRequestStatus.INPROGRESS;
        if ("review".equals(codeString))
          return CommunicationRequestStatus.REVIEW;
        if ("completed".equals(codeString))
          return CommunicationRequestStatus.COMPLETED;
        if ("suspended".equals(codeString))
          return CommunicationRequestStatus.SUSPENDED;
        if ("rejected".equals(codeString))
          return CommunicationRequestStatus.REJECTED;
        if ("failed".equals(codeString))
          return CommunicationRequestStatus.FAILED;
        throw new Exception("Unknown CommunicationRequestStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CommunicationRequestStatus.REQUESTED)
        return "requested";
      if (code == CommunicationRequestStatus.RECEIVED)
        return "received";
      if (code == CommunicationRequestStatus.ACCEPTED)
        return "accepted";
      if (code == CommunicationRequestStatus.INPROGRESS)
        return "in progress";
      if (code == CommunicationRequestStatus.REVIEW)
        return "review";
      if (code == CommunicationRequestStatus.COMPLETED)
        return "completed";
      if (code == CommunicationRequestStatus.SUSPENDED)
        return "suspended";
      if (code == CommunicationRequestStatus.REJECTED)
        return "rejected";
      if (code == CommunicationRequestStatus.FAILED)
        return "failed";
      return "?";
      }
    }

    public enum CommunicationRequestMode {
        PLANNED, // planned.
        PROPOSED, // proposed.
        ORDERED, // ordered.
        NULL; // added to help the parsers
        public static CommunicationRequestMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("ordered".equals(codeString))
          return ORDERED;
        throw new Exception("Unknown CommunicationRequestMode code '"+codeString+"'");
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

  public static class CommunicationRequestModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return CommunicationRequestMode.PLANNED;
        if ("proposed".equals(codeString))
          return CommunicationRequestMode.PROPOSED;
        if ("ordered".equals(codeString))
          return CommunicationRequestMode.ORDERED;
        throw new Exception("Unknown CommunicationRequestMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CommunicationRequestMode.PLANNED)
        return "planned";
      if (code == CommunicationRequestMode.PROPOSED)
        return "proposed";
      if (code == CommunicationRequestMode.ORDERED)
        return "ordered";
      return "?";
      }
    }

    public static class CommunicationRequestMessagePartComponent extends BackboneElement {
        /**
         * An individual message part for multi-part messages.
         */
        protected Type content;

        private static final long serialVersionUID = -1763459053L;

      public CommunicationRequestMessagePartComponent() {
        super();
      }

      public CommunicationRequestMessagePartComponent(Type content) {
        super();
        this.content = content;
      }

        /**
         * @return {@link #content} (An individual message part for multi-part messages.)
         */
        public Type getContent() { 
          return this.content;
        }

        /**
         * @param value {@link #content} (An individual message part for multi-part messages.)
         */
        public CommunicationRequestMessagePartComponent setContent(Type value) { 
          this.content = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("content[x]", "string|Attachment|Reference(any)", "An individual message part for multi-part messages.", 0, java.lang.Integer.MAX_VALUE, content));
        }

      public CommunicationRequestMessagePartComponent copy() {
        CommunicationRequestMessagePartComponent dst = new CommunicationRequestMessagePartComponent();
        copyValues(dst);
        dst.content = content == null ? null : content.copy();
        return dst;
      }

  }

    /**
     * A unique ID of this request for reference purposes. It must be provided if user wants it returned as part of any output, otherwise it will be auto-generated, if needed, by CDS system. Does not need to be the actual ID of the source system.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The type of message such as alert, notification, reminder, instruction, etc.
     */
    protected CodeableConcept category;

    /**
     * The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.
     */
    protected Reference sender;

    /**
     * The actual object that is the target of the reference (The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.)
     */
    protected Resource senderTarget;

    /**
     * The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.
     */
    protected List<Reference> recipient = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.)
     */
    protected List<Resource> recipientTarget = new ArrayList<Resource>();


    /**
     * Text, attachment(s), or resource(s) to be communicated to the recipient.
     */
    protected List<CommunicationRequestMessagePartComponent> messagePart = new ArrayList<CommunicationRequestMessagePartComponent>();

    /**
     * The communication medium, e.g., email, fax.
     */
    protected List<CodeableConcept> medium = new ArrayList<CodeableConcept>();

    /**
     * The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.
     */
    protected Reference requester;

    /**
     * The actual object that is the target of the reference (The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.)
     */
    protected Resource requesterTarget;

    /**
     * The status of the proposal or order.
     */
    protected Enumeration<CommunicationRequestStatus> status;

    /**
     * Whether the communication is proposed, ordered, or planned.
     */
    protected Enumeration<CommunicationRequestMode> mode;

    /**
     * The encounter within which the communication request was created.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The encounter within which the communication request was created.)
     */
    protected Encounter encounterTarget;

    /**
     * The time when this communication is to occur.
     */
    protected DateTimeType scheduledTime;

    /**
     * The reason or justification for the communication request.
     */
    protected List<CodeableConcept> indication = new ArrayList<CodeableConcept>();

    /**
     * The time when the request was made.
     */
    protected DateTimeType orderedOn;

    /**
     * The patient who is the focus of this communication request.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who is the focus of this communication request.)
     */
    protected Patient subjectTarget;

    /**
     * Characterizes how quickly the proposed act must be initiated. Includes concepts such as stat, urgent, routine.
     */
    protected CodeableConcept priority;

    private static final long serialVersionUID = -1138443991L;

    public CommunicationRequest() {
      super();
    }

    public CommunicationRequest(Reference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (A unique ID of this request for reference purposes. It must be provided if user wants it returned as part of any output, otherwise it will be auto-generated, if needed, by CDS system. Does not need to be the actual ID of the source system.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (A unique ID of this request for reference purposes. It must be provided if user wants it returned as part of any output, otherwise it will be auto-generated, if needed, by CDS system. Does not need to be the actual ID of the source system.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #category} (The type of message such as alert, notification, reminder, instruction, etc.)
     */
    public CodeableConcept getCategory() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (The type of message such as alert, notification, reminder, instruction, etc.)
     */
    public CommunicationRequest setCategory(CodeableConcept value) { 
      this.category = value;
      return this;
    }

    /**
     * @return {@link #sender} (The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.)
     */
    public Reference getSender() { 
      return this.sender;
    }

    /**
     * @param value {@link #sender} (The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.)
     */
    public CommunicationRequest setSender(Reference value) { 
      this.sender = value;
      return this;
    }

    /**
     * @return {@link #sender} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.)
     */
    public Resource getSenderTarget() { 
      return this.senderTarget;
    }

    /**
     * @param value {@link #sender} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.)
     */
    public CommunicationRequest setSenderTarget(Resource value) { 
      this.senderTarget = value;
      return this;
    }

    /**
     * @return {@link #recipient} (The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.)
     */
    public List<Reference> getRecipient() { 
      return this.recipient;
    }

    /**
     * @return {@link #recipient} (The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.)
     */
    // syntactic sugar
    public Reference addRecipient() { //3
      Reference t = new Reference();
      this.recipient.add(t);
      return t;
    }

    /**
     * @return {@link #recipient} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.)
     */
    public List<Resource> getRecipientTarget() { 
      return this.recipientTarget;
    }

    /**
     * @return {@link #messagePart} (Text, attachment(s), or resource(s) to be communicated to the recipient.)
     */
    public List<CommunicationRequestMessagePartComponent> getMessagePart() { 
      return this.messagePart;
    }

    /**
     * @return {@link #messagePart} (Text, attachment(s), or resource(s) to be communicated to the recipient.)
     */
    // syntactic sugar
    public CommunicationRequestMessagePartComponent addMessagePart() { //3
      CommunicationRequestMessagePartComponent t = new CommunicationRequestMessagePartComponent();
      this.messagePart.add(t);
      return t;
    }

    /**
     * @return {@link #medium} (The communication medium, e.g., email, fax.)
     */
    public List<CodeableConcept> getMedium() { 
      return this.medium;
    }

    /**
     * @return {@link #medium} (The communication medium, e.g., email, fax.)
     */
    // syntactic sugar
    public CodeableConcept addMedium() { //3
      CodeableConcept t = new CodeableConcept();
      this.medium.add(t);
      return t;
    }

    /**
     * @return {@link #requester} (The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.)
     */
    public Reference getRequester() { 
      return this.requester;
    }

    /**
     * @param value {@link #requester} (The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.)
     */
    public CommunicationRequest setRequester(Reference value) { 
      this.requester = value;
      return this;
    }

    /**
     * @return {@link #requester} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.)
     */
    public Resource getRequesterTarget() { 
      return this.requesterTarget;
    }

    /**
     * @param value {@link #requester} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.)
     */
    public CommunicationRequest setRequesterTarget(Resource value) { 
      this.requesterTarget = value;
      return this;
    }

    /**
     * @return {@link #status} (The status of the proposal or order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<CommunicationRequestStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the proposal or order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public CommunicationRequest setStatusElement(Enumeration<CommunicationRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the proposal or order.
     */
    public CommunicationRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the proposal or order.
     */
    public CommunicationRequest setStatus(CommunicationRequestStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<CommunicationRequestStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #mode} (Whether the communication is proposed, ordered, or planned.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public Enumeration<CommunicationRequestMode> getModeElement() { 
      return this.mode;
    }

    /**
     * @param value {@link #mode} (Whether the communication is proposed, ordered, or planned.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public CommunicationRequest setModeElement(Enumeration<CommunicationRequestMode> value) { 
      this.mode = value;
      return this;
    }

    /**
     * @return Whether the communication is proposed, ordered, or planned.
     */
    public CommunicationRequestMode getMode() { 
      return this.mode == null ? null : this.mode.getValue();
    }

    /**
     * @param value Whether the communication is proposed, ordered, or planned.
     */
    public CommunicationRequest setMode(CommunicationRequestMode value) { 
      if (value == null)
        this.mode = null;
      else {
        if (this.mode == null)
          this.mode = new Enumeration<CommunicationRequestMode>();
        this.mode.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #encounter} (The encounter within which the communication request was created.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The encounter within which the communication request was created.)
     */
    public CommunicationRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The encounter within which the communication request was created.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The encounter within which the communication request was created.)
     */
    public CommunicationRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #scheduledTime} (The time when this communication is to occur.). This is the underlying object with id, value and extensions. The accessor "getScheduledTime" gives direct access to the value
     */
    public DateTimeType getScheduledTimeElement() { 
      return this.scheduledTime;
    }

    /**
     * @param value {@link #scheduledTime} (The time when this communication is to occur.). This is the underlying object with id, value and extensions. The accessor "getScheduledTime" gives direct access to the value
     */
    public CommunicationRequest setScheduledTimeElement(DateTimeType value) { 
      this.scheduledTime = value;
      return this;
    }

    /**
     * @return The time when this communication is to occur.
     */
    public DateAndTime getScheduledTime() { 
      return this.scheduledTime == null ? null : this.scheduledTime.getValue();
    }

    /**
     * @param value The time when this communication is to occur.
     */
    public CommunicationRequest setScheduledTime(DateAndTime value) { 
      if (value == null)
        this.scheduledTime = null;
      else {
        if (this.scheduledTime == null)
          this.scheduledTime = new DateTimeType();
        this.scheduledTime.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #indication} (The reason or justification for the communication request.)
     */
    public List<CodeableConcept> getIndication() { 
      return this.indication;
    }

    /**
     * @return {@link #indication} (The reason or justification for the communication request.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
      CodeableConcept t = new CodeableConcept();
      this.indication.add(t);
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
    public CommunicationRequest setOrderedOnElement(DateTimeType value) { 
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
    public CommunicationRequest setOrderedOn(DateAndTime value) { 
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
     * @return {@link #subject} (The patient who is the focus of this communication request.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who is the focus of this communication request.)
     */
    public CommunicationRequest setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who is the focus of this communication request.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who is the focus of this communication request.)
     */
    public CommunicationRequest setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #priority} (Characterizes how quickly the proposed act must be initiated. Includes concepts such as stat, urgent, routine.)
     */
    public CodeableConcept getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (Characterizes how quickly the proposed act must be initiated. Includes concepts such as stat, urgent, routine.)
     */
    public CommunicationRequest setPriority(CodeableConcept value) { 
      this.priority = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "A unique ID of this request for reference purposes. It must be provided if user wants it returned as part of any output, otherwise it will be auto-generated, if needed, by CDS system. Does not need to be the actual ID of the source system.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("category", "CodeableConcept", "The type of message such as alert, notification, reminder, instruction, etc.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("sender", "Reference(Patient|Practitioner|Device|RelatedPerson|Organization)", "The entity (e.g., person, organization, clinical information system, or device) which is the source of the communication.", 0, java.lang.Integer.MAX_VALUE, sender));
        childrenList.add(new Property("recipient", "Reference(Patient|Device|RelatedPerson|Practitioner)", "The entity (e.g., person, organization, clinical information system, or device) which is the intended target of the communication.", 0, java.lang.Integer.MAX_VALUE, recipient));
        childrenList.add(new Property("messagePart", "", "Text, attachment(s), or resource(s) to be communicated to the recipient.", 0, java.lang.Integer.MAX_VALUE, messagePart));
        childrenList.add(new Property("medium", "CodeableConcept", "The communication medium, e.g., email, fax.", 0, java.lang.Integer.MAX_VALUE, medium));
        childrenList.add(new Property("requester", "Reference(Practitioner|Patient|RelatedPerson)", "The responsible person who authorizes this order, e.g., physician. This may be different than the author of the order statement, e.g., clerk, who may have entered the statement into the order entry application.", 0, java.lang.Integer.MAX_VALUE, requester));
        childrenList.add(new Property("status", "code", "The status of the proposal or order.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("mode", "code", "Whether the communication is proposed, ordered, or planned.", 0, java.lang.Integer.MAX_VALUE, mode));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The encounter within which the communication request was created.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("scheduledTime", "dateTime", "The time when this communication is to occur.", 0, java.lang.Integer.MAX_VALUE, scheduledTime));
        childrenList.add(new Property("indication", "CodeableConcept", "The reason or justification for the communication request.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("orderedOn", "dateTime", "The time when the request was made.", 0, java.lang.Integer.MAX_VALUE, orderedOn));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who is the focus of this communication request.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("priority", "CodeableConcept", "Characterizes how quickly the proposed act must be initiated. Includes concepts such as stat, urgent, routine.", 0, java.lang.Integer.MAX_VALUE, priority));
      }

      public CommunicationRequest copy() {
        CommunicationRequest dst = new CommunicationRequest();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.category = category == null ? null : category.copy();
        dst.sender = sender == null ? null : sender.copy();
        dst.recipient = new ArrayList<Reference>();
        for (Reference i : recipient)
          dst.recipient.add(i.copy());
        dst.messagePart = new ArrayList<CommunicationRequestMessagePartComponent>();
        for (CommunicationRequestMessagePartComponent i : messagePart)
          dst.messagePart.add(i.copy());
        dst.medium = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : medium)
          dst.medium.add(i.copy());
        dst.requester = requester == null ? null : requester.copy();
        dst.status = status == null ? null : status.copy();
        dst.mode = mode == null ? null : mode.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.scheduledTime = scheduledTime == null ? null : scheduledTime.copy();
        dst.indication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : indication)
          dst.indication.add(i.copy());
        dst.orderedOn = orderedOn == null ? null : orderedOn.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.priority = priority == null ? null : priority.copy();
        return dst;
      }

      protected CommunicationRequest typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.CommunicationRequest;
   }


}

