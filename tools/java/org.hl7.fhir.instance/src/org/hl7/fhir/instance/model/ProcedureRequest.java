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
 * A request for a procedure to be performed. May be a proposal or an order.
 */
public class ProcedureRequest extends DomainResource {

    public enum ProcedureRequestStatus {
        REQUESTED, // The request has been placed.
        RECEIVED, // The receiving system has received the request but not yet decided whether it will be performed.
        ACCEPTED, // The receiving system has accepted the request, but work has not yet commenced.
        INPROGRESS, // The work to fulfill the request is happening.
        REVIEW, // The work is complete, and the outcomes are being reviewed for approval.
        COMPLETED, // The work has been complete, the report(s) released, and no further work is planned.
        SUSPENDED, // The request has been held by originating system/user request.
        REJECTED, // The receiving system has declined to fulfill the request.
        FAILED, // The request was attempted, but due to some procedural error, it could not be completed.
        NULL; // added to help the parsers
        public static ProcedureRequestStatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown ProcedureRequestStatus code '"+codeString+"'");
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
            case ACCEPTED: return "The receiving system has accepted the request, but work has not yet commenced.";
            case INPROGRESS: return "The work to fulfill the request is happening.";
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

  public static class ProcedureRequestStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return ProcedureRequestStatus.REQUESTED;
        if ("received".equals(codeString))
          return ProcedureRequestStatus.RECEIVED;
        if ("accepted".equals(codeString))
          return ProcedureRequestStatus.ACCEPTED;
        if ("in progress".equals(codeString))
          return ProcedureRequestStatus.INPROGRESS;
        if ("review".equals(codeString))
          return ProcedureRequestStatus.REVIEW;
        if ("completed".equals(codeString))
          return ProcedureRequestStatus.COMPLETED;
        if ("suspended".equals(codeString))
          return ProcedureRequestStatus.SUSPENDED;
        if ("rejected".equals(codeString))
          return ProcedureRequestStatus.REJECTED;
        if ("failed".equals(codeString))
          return ProcedureRequestStatus.FAILED;
        throw new Exception("Unknown ProcedureRequestStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRequestStatus.REQUESTED)
        return "requested";
      if (code == ProcedureRequestStatus.RECEIVED)
        return "received";
      if (code == ProcedureRequestStatus.ACCEPTED)
        return "accepted";
      if (code == ProcedureRequestStatus.INPROGRESS)
        return "in progress";
      if (code == ProcedureRequestStatus.REVIEW)
        return "review";
      if (code == ProcedureRequestStatus.COMPLETED)
        return "completed";
      if (code == ProcedureRequestStatus.SUSPENDED)
        return "suspended";
      if (code == ProcedureRequestStatus.REJECTED)
        return "rejected";
      if (code == ProcedureRequestStatus.FAILED)
        return "failed";
      return "?";
      }
    }

    public enum ProcedureRequestMode {
        PLANNED, // planned.
        PROPOSED, // proposed.
        ORDERED, // ordered.
        NULL; // added to help the parsers
        public static ProcedureRequestMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("ordered".equals(codeString))
          return ORDERED;
        throw new Exception("Unknown ProcedureRequestMode code '"+codeString+"'");
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

  public static class ProcedureRequestModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("planned".equals(codeString))
          return ProcedureRequestMode.PLANNED;
        if ("proposed".equals(codeString))
          return ProcedureRequestMode.PROPOSED;
        if ("ordered".equals(codeString))
          return ProcedureRequestMode.ORDERED;
        throw new Exception("Unknown ProcedureRequestMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRequestMode.PLANNED)
        return "planned";
      if (code == ProcedureRequestMode.PROPOSED)
        return "proposed";
      if (code == ProcedureRequestMode.ORDERED)
        return "ordered";
      return "?";
      }
    }

    public enum ProcedureRequestPriority {
        ROUTINE, // The request has a normal priority.
        URGENT, // The request should be done urgently.
        STAT, // The request is time-critical.
        ASAP, // The request should be acted on as soon as possible.
        NULL; // added to help the parsers
        public static ProcedureRequestPriority fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown ProcedureRequestPriority code '"+codeString+"'");
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

  public static class ProcedureRequestPriorityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return ProcedureRequestPriority.ROUTINE;
        if ("urgent".equals(codeString))
          return ProcedureRequestPriority.URGENT;
        if ("stat".equals(codeString))
          return ProcedureRequestPriority.STAT;
        if ("asap".equals(codeString))
          return ProcedureRequestPriority.ASAP;
        throw new Exception("Unknown ProcedureRequestPriority code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ProcedureRequestPriority.ROUTINE)
        return "routine";
      if (code == ProcedureRequestPriority.URGENT)
        return "urgent";
      if (code == ProcedureRequestPriority.STAT)
        return "stat";
      if (code == ProcedureRequestPriority.ASAP)
        return "asap";
      return "?";
      }
    }

    /**
     * Identifiers assigned to this order by the order or by the receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The patient who will receive the procedure.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (The patient who will receive the procedure.)
     */
    protected Patient subjectTarget;

    /**
     * The specific procedure that is ordered. Use text if the exact nature of the procedure can't be coded.
     */
    protected CodeableConcept type;

    /**
     * The site where the procedure is to be performed.
     */
    protected List<CodeableConcept> bodySite = new ArrayList<CodeableConcept>();

    /**
     * The reason why the procedure is proposed or ordered. This procedure request may be motivated by a Condition for instance.
     */
    protected List<CodeableConcept> indication = new ArrayList<CodeableConcept>();

    /**
     * The timing schedule for the proposed or ordered procedure. The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
     */
    protected Type timing;

    /**
     * The encounter within which the procedure proposal or request was created.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The encounter within which the procedure proposal or request was created.)
     */
    protected Encounter encounterTarget;

    /**
     * E.g. surgeon, anaethetist, endoscopist.
     */
    protected Reference performer;

    /**
     * The actual object that is the target of the reference (E.g. surgeon, anaethetist, endoscopist.)
     */
    protected Resource performerTarget;

    /**
     * The status of the order.
     */
    protected Enumeration<ProcedureRequestStatus> status;

    /**
     * The status of the order.
     */
    protected Enumeration<ProcedureRequestMode> mode;

    /**
     * Any other notes associated with this proposal or order - e.g., provider instructions.
     */
    protected List<StringType> notes = new ArrayList<StringType>();

    /**
     * If a CodeableConcept is present, it indicates the pre-condition for performing the procedure.
     */
    protected Type asNeeded;

    /**
     * The time when the request was made.
     */
    protected DateTimeType orderedOn;

    /**
     * The healthcare professional responsible for proposing or ordering the procedure.
     */
    protected Reference orderer;

    /**
     * The actual object that is the target of the reference (The healthcare professional responsible for proposing or ordering the procedure.)
     */
    protected Resource ordererTarget;

    /**
     * The clinical priority associated with this order.
     */
    protected Enumeration<ProcedureRequestPriority> priority;

    private static final long serialVersionUID = 613508565L;

    public ProcedureRequest() {
      super();
    }

    public ProcedureRequest(Reference subject, CodeableConcept type) {
      super();
      this.subject = subject;
      this.type = type;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order or by the receiver.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order or by the receiver.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #subject} (The patient who will receive the procedure.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (The patient who will receive the procedure.)
     */
    public ProcedureRequest setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who will receive the procedure.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who will receive the procedure.)
     */
    public ProcedureRequest setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #type} (The specific procedure that is ordered. Use text if the exact nature of the procedure can't be coded.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The specific procedure that is ordered. Use text if the exact nature of the procedure can't be coded.)
     */
    public ProcedureRequest setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #bodySite} (The site where the procedure is to be performed.)
     */
    public List<CodeableConcept> getBodySite() { 
      return this.bodySite;
    }

    /**
     * @return {@link #bodySite} (The site where the procedure is to be performed.)
     */
    // syntactic sugar
    public CodeableConcept addBodySite() { //3
      CodeableConcept t = new CodeableConcept();
      this.bodySite.add(t);
      return t;
    }

    /**
     * @return {@link #indication} (The reason why the procedure is proposed or ordered. This procedure request may be motivated by a Condition for instance.)
     */
    public List<CodeableConcept> getIndication() { 
      return this.indication;
    }

    /**
     * @return {@link #indication} (The reason why the procedure is proposed or ordered. This procedure request may be motivated by a Condition for instance.)
     */
    // syntactic sugar
    public CodeableConcept addIndication() { //3
      CodeableConcept t = new CodeableConcept();
      this.indication.add(t);
      return t;
    }

    /**
     * @return {@link #timing} (The timing schedule for the proposed or ordered procedure. The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public Type getTiming() { 
      return this.timing;
    }

    /**
     * @param value {@link #timing} (The timing schedule for the proposed or ordered procedure. The Schedule data type allows many different expressions, for example. "Every 8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:"; "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
     */
    public ProcedureRequest setTiming(Type value) { 
      this.timing = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The encounter within which the procedure proposal or request was created.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The encounter within which the procedure proposal or request was created.)
     */
    public ProcedureRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The encounter within which the procedure proposal or request was created.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The encounter within which the procedure proposal or request was created.)
     */
    public ProcedureRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #performer} (E.g. surgeon, anaethetist, endoscopist.)
     */
    public Reference getPerformer() { 
      return this.performer;
    }

    /**
     * @param value {@link #performer} (E.g. surgeon, anaethetist, endoscopist.)
     */
    public ProcedureRequest setPerformer(Reference value) { 
      this.performer = value;
      return this;
    }

    /**
     * @return {@link #performer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (E.g. surgeon, anaethetist, endoscopist.)
     */
    public Resource getPerformerTarget() { 
      return this.performerTarget;
    }

    /**
     * @param value {@link #performer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (E.g. surgeon, anaethetist, endoscopist.)
     */
    public ProcedureRequest setPerformerTarget(Resource value) { 
      this.performerTarget = value;
      return this;
    }

    /**
     * @return {@link #status} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ProcedureRequestStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public ProcedureRequest setStatusElement(Enumeration<ProcedureRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the order.
     */
    public ProcedureRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the order.
     */
    public ProcedureRequest setStatus(ProcedureRequestStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ProcedureRequestStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #mode} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public Enumeration<ProcedureRequestMode> getModeElement() { 
      return this.mode;
    }

    /**
     * @param value {@link #mode} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
     */
    public ProcedureRequest setModeElement(Enumeration<ProcedureRequestMode> value) { 
      this.mode = value;
      return this;
    }

    /**
     * @return The status of the order.
     */
    public ProcedureRequestMode getMode() { 
      return this.mode == null ? null : this.mode.getValue();
    }

    /**
     * @param value The status of the order.
     */
    public ProcedureRequest setMode(ProcedureRequestMode value) { 
      if (value == null)
        this.mode = null;
      else {
        if (this.mode == null)
          this.mode = new Enumeration<ProcedureRequestMode>();
        this.mode.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #notes} (Any other notes associated with this proposal or order - e.g., provider instructions.)
     */
    public List<StringType> getNotes() { 
      return this.notes;
    }

    /**
     * @return {@link #notes} (Any other notes associated with this proposal or order - e.g., provider instructions.)
     */
    // syntactic sugar
    public StringType addNotesElement() {//2 
      StringType t = new StringType();
      this.notes.add(t);
      return t;
    }

    /**
     * @param value {@link #notes} (Any other notes associated with this proposal or order - e.g., provider instructions.)
     */
    public ProcedureRequest addNotes(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.notes.add(t);
      return this;
    }

    /**
     * @param value {@link #notes} (Any other notes associated with this proposal or order - e.g., provider instructions.)
     */
    public boolean hasNotes(String value) { 
      for (StringType v : this.notes)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #asNeeded} (If a CodeableConcept is present, it indicates the pre-condition for performing the procedure.)
     */
    public Type getAsNeeded() { 
      return this.asNeeded;
    }

    /**
     * @param value {@link #asNeeded} (If a CodeableConcept is present, it indicates the pre-condition for performing the procedure.)
     */
    public ProcedureRequest setAsNeeded(Type value) { 
      this.asNeeded = value;
      return this;
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
    public ProcedureRequest setOrderedOnElement(DateTimeType value) { 
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
    public ProcedureRequest setOrderedOn(DateAndTime value) { 
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
     * @return {@link #orderer} (The healthcare professional responsible for proposing or ordering the procedure.)
     */
    public Reference getOrderer() { 
      return this.orderer;
    }

    /**
     * @param value {@link #orderer} (The healthcare professional responsible for proposing or ordering the procedure.)
     */
    public ProcedureRequest setOrderer(Reference value) { 
      this.orderer = value;
      return this;
    }

    /**
     * @return {@link #orderer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The healthcare professional responsible for proposing or ordering the procedure.)
     */
    public Resource getOrdererTarget() { 
      return this.ordererTarget;
    }

    /**
     * @param value {@link #orderer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The healthcare professional responsible for proposing or ordering the procedure.)
     */
    public ProcedureRequest setOrdererTarget(Resource value) { 
      this.ordererTarget = value;
      return this;
    }

    /**
     * @return {@link #priority} (The clinical priority associated with this order.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public Enumeration<ProcedureRequestPriority> getPriorityElement() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (The clinical priority associated with this order.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public ProcedureRequest setPriorityElement(Enumeration<ProcedureRequestPriority> value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return The clinical priority associated with this order.
     */
    public ProcedureRequestPriority getPriority() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value The clinical priority associated with this order.
     */
    public ProcedureRequest setPriority(ProcedureRequestPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<ProcedureRequestPriority>();
        this.priority.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the order or by the receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("subject", "Reference(Patient)", "The patient who will receive the procedure.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("type", "CodeableConcept", "The specific procedure that is ordered. Use text if the exact nature of the procedure can't be coded.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("bodySite", "CodeableConcept", "The site where the procedure is to be performed.", 0, java.lang.Integer.MAX_VALUE, bodySite));
        childrenList.add(new Property("indication", "CodeableConcept", "The reason why the procedure is proposed or ordered. This procedure request may be motivated by a Condition for instance.", 0, java.lang.Integer.MAX_VALUE, indication));
        childrenList.add(new Property("timing[x]", "dateTime|Period|Timing", "The timing schedule for the proposed or ordered procedure. The Schedule data type allows many different expressions, for example. 'Every 8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:'; '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The encounter within which the procedure proposal or request was created.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("performer", "Reference(Practitioner|Organization|Patient|RelatedPerson)", "E.g. surgeon, anaethetist, endoscopist.", 0, java.lang.Integer.MAX_VALUE, performer));
        childrenList.add(new Property("status", "code", "The status of the order.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("mode", "code", "The status of the order.", 0, java.lang.Integer.MAX_VALUE, mode));
        childrenList.add(new Property("notes", "string", "Any other notes associated with this proposal or order - e.g., provider instructions.", 0, java.lang.Integer.MAX_VALUE, notes));
        childrenList.add(new Property("asNeeded[x]", "boolean|CodeableConcept", "If a CodeableConcept is present, it indicates the pre-condition for performing the procedure.", 0, java.lang.Integer.MAX_VALUE, asNeeded));
        childrenList.add(new Property("orderedOn", "dateTime", "The time when the request was made.", 0, java.lang.Integer.MAX_VALUE, orderedOn));
        childrenList.add(new Property("orderer", "Reference(Practitioner|Patient|RelatedPerson|Device)", "The healthcare professional responsible for proposing or ordering the procedure.", 0, java.lang.Integer.MAX_VALUE, orderer));
        childrenList.add(new Property("priority", "code", "The clinical priority associated with this order.", 0, java.lang.Integer.MAX_VALUE, priority));
      }

      public ProcedureRequest copy() {
        ProcedureRequest dst = new ProcedureRequest();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.subject = subject == null ? null : subject.copy();
        dst.type = type == null ? null : type.copy();
        dst.bodySite = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : bodySite)
          dst.bodySite.add(i.copy());
        dst.indication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : indication)
          dst.indication.add(i.copy());
        dst.timing = timing == null ? null : timing.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.performer = performer == null ? null : performer.copy();
        dst.status = status == null ? null : status.copy();
        dst.mode = mode == null ? null : mode.copy();
        dst.notes = new ArrayList<StringType>();
        for (StringType i : notes)
          dst.notes.add(i.copy());
        dst.asNeeded = asNeeded == null ? null : asNeeded.copy();
        dst.orderedOn = orderedOn == null ? null : orderedOn.copy();
        dst.orderer = orderer == null ? null : orderer.copy();
        dst.priority = priority == null ? null : priority.copy();
        return dst;
      }

      protected ProcedureRequest typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ProcedureRequest;
   }


}

