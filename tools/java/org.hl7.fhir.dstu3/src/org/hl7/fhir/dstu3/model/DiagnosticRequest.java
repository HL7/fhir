package org.hl7.fhir.dstu3.model;

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

// Generated on Mon, Jul 18, 2016 00:26+1000 for FHIR v1.5.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.dstu3.exceptions.FHIRException;
/**
 * A record of a request for a diagnostic investigation service to be performed.
 */
@ResourceDef(name="DiagnosticRequest", profile="http://hl7.org/fhir/Profile/DiagnosticRequest")
public class DiagnosticRequest extends DomainResource {

    public enum DiagnosticRequestStatus {
        /**
         * The request has been proposed.
         */
        PROPOSED, 
        /**
         * The request is in preliminary form prior to being sent.
         */
        DRAFT, 
        /**
         * The request has been planned.
         */
        PLANNED, 
        /**
         * The request has been placed.
         */
        REQUESTED, 
        /**
         * The receiving system has received the order, but not yet decided whether it will be performed.
         */
        RECEIVED, 
        /**
         * The receiving system has accepted the order, but work has not yet commenced.
         */
        ACCEPTED, 
        /**
         * The work to fulfill the order is happening.
         */
        INPROGRESS, 
        /**
         * The work is complete, and the outcomes are being reviewed for approval.
         */
        REVIEW, 
        /**
         * The work has been completed, the report(s) released, and no further work is planned.
         */
        COMPLETED, 
        /**
         * The request has been withdrawn.
         */
        CANCELLED, 
        /**
         * The request has been held by originating system/user request.
         */
        SUSPENDED, 
        /**
         * The receiving system has declined to fulfill the request.
         */
        REJECTED, 
        /**
         * The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.
         */
        FAILED, 
        /**
         * The request was entered in error and voided.
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static DiagnosticRequestStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return PROPOSED;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("planned".equals(codeString))
          return PLANNED;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("received".equals(codeString))
          return RECEIVED;
        if ("accepted".equals(codeString))
          return ACCEPTED;
        if ("in-progress".equals(codeString))
          return INPROGRESS;
        if ("review".equals(codeString))
          return REVIEW;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("suspended".equals(codeString))
          return SUSPENDED;
        if ("rejected".equals(codeString))
          return REJECTED;
        if ("failed".equals(codeString))
          return FAILED;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown DiagnosticRequestStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PROPOSED: return "proposed";
            case DRAFT: return "draft";
            case PLANNED: return "planned";
            case REQUESTED: return "requested";
            case RECEIVED: return "received";
            case ACCEPTED: return "accepted";
            case INPROGRESS: return "in-progress";
            case REVIEW: return "review";
            case COMPLETED: return "completed";
            case CANCELLED: return "cancelled";
            case SUSPENDED: return "suspended";
            case REJECTED: return "rejected";
            case FAILED: return "failed";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case PROPOSED: return "http://hl7.org/fhir/diagnostic-request-status";
            case DRAFT: return "http://hl7.org/fhir/diagnostic-request-status";
            case PLANNED: return "http://hl7.org/fhir/diagnostic-request-status";
            case REQUESTED: return "http://hl7.org/fhir/diagnostic-request-status";
            case RECEIVED: return "http://hl7.org/fhir/diagnostic-request-status";
            case ACCEPTED: return "http://hl7.org/fhir/diagnostic-request-status";
            case INPROGRESS: return "http://hl7.org/fhir/diagnostic-request-status";
            case REVIEW: return "http://hl7.org/fhir/diagnostic-request-status";
            case COMPLETED: return "http://hl7.org/fhir/diagnostic-request-status";
            case CANCELLED: return "http://hl7.org/fhir/diagnostic-request-status";
            case SUSPENDED: return "http://hl7.org/fhir/diagnostic-request-status";
            case REJECTED: return "http://hl7.org/fhir/diagnostic-request-status";
            case FAILED: return "http://hl7.org/fhir/diagnostic-request-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/diagnostic-request-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PROPOSED: return "The request has been proposed.";
            case DRAFT: return "The request is in preliminary form prior to being sent.";
            case PLANNED: return "The request has been planned.";
            case REQUESTED: return "The request has been placed.";
            case RECEIVED: return "The receiving system has received the order, but not yet decided whether it will be performed.";
            case ACCEPTED: return "The receiving system has accepted the order, but work has not yet commenced.";
            case INPROGRESS: return "The work to fulfill the order is happening.";
            case REVIEW: return "The work is complete, and the outcomes are being reviewed for approval.";
            case COMPLETED: return "The work has been completed, the report(s) released, and no further work is planned.";
            case CANCELLED: return "The request has been withdrawn.";
            case SUSPENDED: return "The request has been held by originating system/user request.";
            case REJECTED: return "The receiving system has declined to fulfill the request.";
            case FAILED: return "The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.";
            case ENTEREDINERROR: return "The request was entered in error and voided.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PROPOSED: return "Proposed";
            case DRAFT: return "Draft";
            case PLANNED: return "Planned";
            case REQUESTED: return "Requested";
            case RECEIVED: return "Received";
            case ACCEPTED: return "Accepted";
            case INPROGRESS: return "In-Progress";
            case REVIEW: return "Review";
            case COMPLETED: return "Completed";
            case CANCELLED: return "Cancelled";
            case SUSPENDED: return "Suspended";
            case REJECTED: return "Rejected";
            case FAILED: return "Failed";
            case ENTEREDINERROR: return "Entered in Error";
            default: return "?";
          }
        }
    }

  public static class DiagnosticRequestStatusEnumFactory implements EnumFactory<DiagnosticRequestStatus> {
    public DiagnosticRequestStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return DiagnosticRequestStatus.PROPOSED;
        if ("draft".equals(codeString))
          return DiagnosticRequestStatus.DRAFT;
        if ("planned".equals(codeString))
          return DiagnosticRequestStatus.PLANNED;
        if ("requested".equals(codeString))
          return DiagnosticRequestStatus.REQUESTED;
        if ("received".equals(codeString))
          return DiagnosticRequestStatus.RECEIVED;
        if ("accepted".equals(codeString))
          return DiagnosticRequestStatus.ACCEPTED;
        if ("in-progress".equals(codeString))
          return DiagnosticRequestStatus.INPROGRESS;
        if ("review".equals(codeString))
          return DiagnosticRequestStatus.REVIEW;
        if ("completed".equals(codeString))
          return DiagnosticRequestStatus.COMPLETED;
        if ("cancelled".equals(codeString))
          return DiagnosticRequestStatus.CANCELLED;
        if ("suspended".equals(codeString))
          return DiagnosticRequestStatus.SUSPENDED;
        if ("rejected".equals(codeString))
          return DiagnosticRequestStatus.REJECTED;
        if ("failed".equals(codeString))
          return DiagnosticRequestStatus.FAILED;
        if ("entered-in-error".equals(codeString))
          return DiagnosticRequestStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown DiagnosticRequestStatus code '"+codeString+"'");
        }
        public Enumeration<DiagnosticRequestStatus> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("proposed".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.PROPOSED);
        if ("draft".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.DRAFT);
        if ("planned".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.PLANNED);
        if ("requested".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.REQUESTED);
        if ("received".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.RECEIVED);
        if ("accepted".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.ACCEPTED);
        if ("in-progress".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.INPROGRESS);
        if ("review".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.REVIEW);
        if ("completed".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.COMPLETED);
        if ("cancelled".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.CANCELLED);
        if ("suspended".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.SUSPENDED);
        if ("rejected".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.REJECTED);
        if ("failed".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.FAILED);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<DiagnosticRequestStatus>(this, DiagnosticRequestStatus.ENTEREDINERROR);
        throw new FHIRException("Unknown DiagnosticRequestStatus code '"+codeString+"'");
        }
    public String toCode(DiagnosticRequestStatus code) {
      if (code == DiagnosticRequestStatus.PROPOSED)
        return "proposed";
      if (code == DiagnosticRequestStatus.DRAFT)
        return "draft";
      if (code == DiagnosticRequestStatus.PLANNED)
        return "planned";
      if (code == DiagnosticRequestStatus.REQUESTED)
        return "requested";
      if (code == DiagnosticRequestStatus.RECEIVED)
        return "received";
      if (code == DiagnosticRequestStatus.ACCEPTED)
        return "accepted";
      if (code == DiagnosticRequestStatus.INPROGRESS)
        return "in-progress";
      if (code == DiagnosticRequestStatus.REVIEW)
        return "review";
      if (code == DiagnosticRequestStatus.COMPLETED)
        return "completed";
      if (code == DiagnosticRequestStatus.CANCELLED)
        return "cancelled";
      if (code == DiagnosticRequestStatus.SUSPENDED)
        return "suspended";
      if (code == DiagnosticRequestStatus.REJECTED)
        return "rejected";
      if (code == DiagnosticRequestStatus.FAILED)
        return "failed";
      if (code == DiagnosticRequestStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    public String toSystem(DiagnosticRequestStatus code) {
      return code.getSystem();
      }
    }

    public enum DiagnosticRequestPriority {
        /**
         * The order has a normal priority .
         */
        ROUTINE, 
        /**
         * The order should be urgently.
         */
        URGENT, 
        /**
         * The order is time-critical.
         */
        STAT, 
        /**
         * The order should be acted on as soon as possible.
         */
        ASAP, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static DiagnosticRequestPriority fromCode(String codeString) throws FHIRException {
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
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown DiagnosticRequestPriority code '"+codeString+"'");
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
        public String getSystem() {
          switch (this) {
            case ROUTINE: return "http://hl7.org/fhir/diagnostic-request-priority";
            case URGENT: return "http://hl7.org/fhir/diagnostic-request-priority";
            case STAT: return "http://hl7.org/fhir/diagnostic-request-priority";
            case ASAP: return "http://hl7.org/fhir/diagnostic-request-priority";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case ROUTINE: return "The order has a normal priority .";
            case URGENT: return "The order should be urgently.";
            case STAT: return "The order is time-critical.";
            case ASAP: return "The order should be acted on as soon as possible.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case ROUTINE: return "Routine";
            case URGENT: return "Urgent";
            case STAT: return "Stat";
            case ASAP: return "ASAP";
            default: return "?";
          }
        }
    }

  public static class DiagnosticRequestPriorityEnumFactory implements EnumFactory<DiagnosticRequestPriority> {
    public DiagnosticRequestPriority fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return DiagnosticRequestPriority.ROUTINE;
        if ("urgent".equals(codeString))
          return DiagnosticRequestPriority.URGENT;
        if ("stat".equals(codeString))
          return DiagnosticRequestPriority.STAT;
        if ("asap".equals(codeString))
          return DiagnosticRequestPriority.ASAP;
        throw new IllegalArgumentException("Unknown DiagnosticRequestPriority code '"+codeString+"'");
        }
        public Enumeration<DiagnosticRequestPriority> fromType(Base code) throws FHIRException {
          if (code == null || code.isEmpty())
            return null;
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("routine".equals(codeString))
          return new Enumeration<DiagnosticRequestPriority>(this, DiagnosticRequestPriority.ROUTINE);
        if ("urgent".equals(codeString))
          return new Enumeration<DiagnosticRequestPriority>(this, DiagnosticRequestPriority.URGENT);
        if ("stat".equals(codeString))
          return new Enumeration<DiagnosticRequestPriority>(this, DiagnosticRequestPriority.STAT);
        if ("asap".equals(codeString))
          return new Enumeration<DiagnosticRequestPriority>(this, DiagnosticRequestPriority.ASAP);
        throw new FHIRException("Unknown DiagnosticRequestPriority code '"+codeString+"'");
        }
    public String toCode(DiagnosticRequestPriority code) {
      if (code == DiagnosticRequestPriority.ROUTINE)
        return "routine";
      if (code == DiagnosticRequestPriority.URGENT)
        return "urgent";
      if (code == DiagnosticRequestPriority.STAT)
        return "stat";
      if (code == DiagnosticRequestPriority.ASAP)
        return "asap";
      return "?";
      }
    public String toSystem(DiagnosticRequestPriority code) {
      return code.getSystem();
      }
    }

    @Block()
    public static class DiagnosticRequestEventComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * The status for the event.
         */
        @Child(name = "status", type = {CodeType.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", formalDefinition="The status for the event." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-request-status")
        protected Enumeration<DiagnosticRequestStatus> status;

        /**
         * Additional information about the event that occurred - e.g. if the status remained unchanged.
         */
        @Child(name = "description", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="More information about the event and its context", formalDefinition="Additional information about the event that occurred - e.g. if the status remained unchanged." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-request-event")
        protected CodeableConcept description;

        /**
         * The date/time at which the event occurred.
         */
        @Child(name = "dateTime", type = {DateTimeType.class}, order=3, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="The date at which the event happened", formalDefinition="The date/time at which the event occurred." )
        protected DateTimeType dateTime;

        /**
         * The person responsible for performing or recording the action.
         */
        @Child(name = "actor", type = {Practitioner.class, Device.class}, order=4, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Who recorded or did this", formalDefinition="The person responsible for performing or recording the action." )
        protected Reference actor;

        /**
         * The actual object that is the target of the reference (The person responsible for performing or recording the action.)
         */
        protected Resource actorTarget;

        private static final long serialVersionUID = 1681339078L;

    /**
     * Constructor
     */
      public DiagnosticRequestEventComponent() {
        super();
      }

    /**
     * Constructor
     */
      public DiagnosticRequestEventComponent(Enumeration<DiagnosticRequestStatus> status, DateTimeType dateTime) {
        super();
        this.status = status;
        this.dateTime = dateTime;
      }

        /**
         * @return {@link #status} (The status for the event.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public Enumeration<DiagnosticRequestStatus> getStatusElement() { 
          if (this.status == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestEventComponent.status");
            else if (Configuration.doAutoCreate())
              this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory()); // bb
          return this.status;
        }

        public boolean hasStatusElement() { 
          return this.status != null && !this.status.isEmpty();
        }

        public boolean hasStatus() { 
          return this.status != null && !this.status.isEmpty();
        }

        /**
         * @param value {@link #status} (The status for the event.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public DiagnosticRequestEventComponent setStatusElement(Enumeration<DiagnosticRequestStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return The status for the event.
         */
        public DiagnosticRequestStatus getStatus() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value The status for the event.
         */
        public DiagnosticRequestEventComponent setStatus(DiagnosticRequestStatus value) { 
            if (this.status == null)
              this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory());
            this.status.setValue(value);
          return this;
        }

        /**
         * @return {@link #description} (Additional information about the event that occurred - e.g. if the status remained unchanged.)
         */
        public CodeableConcept getDescription() { 
          if (this.description == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestEventComponent.description");
            else if (Configuration.doAutoCreate())
              this.description = new CodeableConcept(); // cc
          return this.description;
        }

        public boolean hasDescription() { 
          return this.description != null && !this.description.isEmpty();
        }

        /**
         * @param value {@link #description} (Additional information about the event that occurred - e.g. if the status remained unchanged.)
         */
        public DiagnosticRequestEventComponent setDescription(CodeableConcept value) { 
          this.description = value;
          return this;
        }

        /**
         * @return {@link #dateTime} (The date/time at which the event occurred.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
         */
        public DateTimeType getDateTimeElement() { 
          if (this.dateTime == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestEventComponent.dateTime");
            else if (Configuration.doAutoCreate())
              this.dateTime = new DateTimeType(); // bb
          return this.dateTime;
        }

        public boolean hasDateTimeElement() { 
          return this.dateTime != null && !this.dateTime.isEmpty();
        }

        public boolean hasDateTime() { 
          return this.dateTime != null && !this.dateTime.isEmpty();
        }

        /**
         * @param value {@link #dateTime} (The date/time at which the event occurred.). This is the underlying object with id, value and extensions. The accessor "getDateTime" gives direct access to the value
         */
        public DiagnosticRequestEventComponent setDateTimeElement(DateTimeType value) { 
          this.dateTime = value;
          return this;
        }

        /**
         * @return The date/time at which the event occurred.
         */
        public Date getDateTime() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        /**
         * @param value The date/time at which the event occurred.
         */
        public DiagnosticRequestEventComponent setDateTime(Date value) { 
            if (this.dateTime == null)
              this.dateTime = new DateTimeType();
            this.dateTime.setValue(value);
          return this;
        }

        /**
         * @return {@link #actor} (The person responsible for performing or recording the action.)
         */
        public Reference getActor() { 
          if (this.actor == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestEventComponent.actor");
            else if (Configuration.doAutoCreate())
              this.actor = new Reference(); // cc
          return this.actor;
        }

        public boolean hasActor() { 
          return this.actor != null && !this.actor.isEmpty();
        }

        /**
         * @param value {@link #actor} (The person responsible for performing or recording the action.)
         */
        public DiagnosticRequestEventComponent setActor(Reference value) { 
          this.actor = value;
          return this;
        }

        /**
         * @return {@link #actor} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person responsible for performing or recording the action.)
         */
        public Resource getActorTarget() { 
          return this.actorTarget;
        }

        /**
         * @param value {@link #actor} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person responsible for performing or recording the action.)
         */
        public DiagnosticRequestEventComponent setActorTarget(Resource value) { 
          this.actorTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("status", "code", "The status for the event.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("description", "CodeableConcept", "Additional information about the event that occurred - e.g. if the status remained unchanged.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("dateTime", "dateTime", "The date/time at which the event occurred.", 0, java.lang.Integer.MAX_VALUE, dateTime));
          childrenList.add(new Property("actor", "Reference(Practitioner|Device)", "The person responsible for performing or recording the action.", 0, java.lang.Integer.MAX_VALUE, actor));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<DiagnosticRequestStatus>
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // CodeableConcept
        case 1792749467: /*dateTime*/ return this.dateTime == null ? new Base[0] : new Base[] {this.dateTime}; // DateTimeType
        case 92645877: /*actor*/ return this.actor == null ? new Base[0] : new Base[] {this.actor}; // Reference
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -892481550: // status
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
          break;
        case -1724546052: // description
          this.description = castToCodeableConcept(value); // CodeableConcept
          break;
        case 1792749467: // dateTime
          this.dateTime = castToDateTime(value); // DateTimeType
          break;
        case 92645877: // actor
          this.actor = castToReference(value); // Reference
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("status"))
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
        else if (name.equals("description"))
          this.description = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("dateTime"))
          this.dateTime = castToDateTime(value); // DateTimeType
        else if (name.equals("actor"))
          this.actor = castToReference(value); // Reference
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -892481550: throw new FHIRException("Cannot make property status as it is not a complex type"); // Enumeration<DiagnosticRequestStatus>
        case -1724546052:  return getDescription(); // CodeableConcept
        case 1792749467: throw new FHIRException("Cannot make property dateTime as it is not a complex type"); // DateTimeType
        case 92645877:  return getActor(); // Reference
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type DiagnosticRequest.status");
        }
        else if (name.equals("description")) {
          this.description = new CodeableConcept();
          return this.description;
        }
        else if (name.equals("dateTime")) {
          throw new FHIRException("Cannot call addChild on a primitive type DiagnosticRequest.dateTime");
        }
        else if (name.equals("actor")) {
          this.actor = new Reference();
          return this.actor;
        }
        else
          return super.addChild(name);
      }

      public DiagnosticRequestEventComponent copy() {
        DiagnosticRequestEventComponent dst = new DiagnosticRequestEventComponent();
        copyValues(dst);
        dst.status = status == null ? null : status.copy();
        dst.description = description == null ? null : description.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.actor = actor == null ? null : actor.copy();
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof DiagnosticRequestEventComponent))
          return false;
        DiagnosticRequestEventComponent o = (DiagnosticRequestEventComponent) other;
        return compareDeep(status, o.status, true) && compareDeep(description, o.description, true) && compareDeep(dateTime, o.dateTime, true)
           && compareDeep(actor, o.actor, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof DiagnosticRequestEventComponent))
          return false;
        DiagnosticRequestEventComponent o = (DiagnosticRequestEventComponent) other;
        return compareValues(status, o.status, true) && compareValues(dateTime, o.dateTime, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(status, description, dateTime
          , actor);
      }

  public String fhirType() {
    return "DiagnosticRequest.event";

  }

  }

    @Block()
    public static class DiagnosticRequestItemComponent extends BackboneElement implements IBaseBackboneElement {
        /**
         * A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.
         */
        @Child(name = "code", type = {CodeableConcept.class}, order=1, min=1, max=1, modifier=false, summary=true)
        @Description(shortDefinition="Code to indicate the item (test or panel) being ordered", formalDefinition="A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-requests")
        protected CodeableConcept code;

        /**
         * Anatomical location where the request test should be performed.  This is the target site.
         */
        @Child(name = "bodySite", type = {CodeableConcept.class}, order=2, min=0, max=1, modifier=false, summary=false)
        @Description(shortDefinition="Location of requested test (if applicable)", formalDefinition="Anatomical location where the request test should be performed.  This is the target site." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/body-site")
        protected CodeableConcept bodySite;

        /**
         * The status of this individual item within the order.
         */
        @Child(name = "status", type = {CodeType.class}, order=3, min=0, max=1, modifier=false, summary=true)
        @Description(shortDefinition="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", formalDefinition="The status of this individual item within the order." )
        @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-request-status")
        protected Enumeration<DiagnosticRequestStatus> status;

        /**
         * A summary of the events of interest that have occurred as this item of the request is processed.
         */
        @Child(name = "event", type = {DiagnosticRequestEventComponent.class}, order=4, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
        @Description(shortDefinition="Events specific to this item", formalDefinition="A summary of the events of interest that have occurred as this item of the request is processed." )
        protected List<DiagnosticRequestEventComponent> event;

        private static final long serialVersionUID = -866445644L;

    /**
     * Constructor
     */
      public DiagnosticRequestItemComponent() {
        super();
      }

    /**
     * Constructor
     */
      public DiagnosticRequestItemComponent(CodeableConcept code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.)
         */
        public CodeableConcept getCode() { 
          if (this.code == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestItemComponent.code");
            else if (Configuration.doAutoCreate())
              this.code = new CodeableConcept(); // cc
          return this.code;
        }

        public boolean hasCode() { 
          return this.code != null && !this.code.isEmpty();
        }

        /**
         * @param value {@link #code} (A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.)
         */
        public DiagnosticRequestItemComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #bodySite} (Anatomical location where the request test should be performed.  This is the target site.)
         */
        public CodeableConcept getBodySite() { 
          if (this.bodySite == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestItemComponent.bodySite");
            else if (Configuration.doAutoCreate())
              this.bodySite = new CodeableConcept(); // cc
          return this.bodySite;
        }

        public boolean hasBodySite() { 
          return this.bodySite != null && !this.bodySite.isEmpty();
        }

        /**
         * @param value {@link #bodySite} (Anatomical location where the request test should be performed.  This is the target site.)
         */
        public DiagnosticRequestItemComponent setBodySite(CodeableConcept value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #status} (The status of this individual item within the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public Enumeration<DiagnosticRequestStatus> getStatusElement() { 
          if (this.status == null)
            if (Configuration.errorOnAutoCreate())
              throw new Error("Attempt to auto-create DiagnosticRequestItemComponent.status");
            else if (Configuration.doAutoCreate())
              this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory()); // bb
          return this.status;
        }

        public boolean hasStatusElement() { 
          return this.status != null && !this.status.isEmpty();
        }

        public boolean hasStatus() { 
          return this.status != null && !this.status.isEmpty();
        }

        /**
         * @param value {@link #status} (The status of this individual item within the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public DiagnosticRequestItemComponent setStatusElement(Enumeration<DiagnosticRequestStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return The status of this individual item within the order.
         */
        public DiagnosticRequestStatus getStatus() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value The status of this individual item within the order.
         */
        public DiagnosticRequestItemComponent setStatus(DiagnosticRequestStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory());
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #event} (A summary of the events of interest that have occurred as this item of the request is processed.)
         */
        public List<DiagnosticRequestEventComponent> getEvent() { 
          if (this.event == null)
            this.event = new ArrayList<DiagnosticRequestEventComponent>();
          return this.event;
        }

        /**
         * @return Returns a reference to <code>this</code> for easy method chaining
         */
        public DiagnosticRequestItemComponent setEvent(List<DiagnosticRequestEventComponent> theEvent) { 
          this.event = theEvent;
          return this;
        }

        public boolean hasEvent() { 
          if (this.event == null)
            return false;
          for (DiagnosticRequestEventComponent item : this.event)
            if (!item.isEmpty())
              return true;
          return false;
        }

        public DiagnosticRequestEventComponent addEvent() { //3
          DiagnosticRequestEventComponent t = new DiagnosticRequestEventComponent();
          if (this.event == null)
            this.event = new ArrayList<DiagnosticRequestEventComponent>();
          this.event.add(t);
          return t;
        }

        public DiagnosticRequestItemComponent addEvent(DiagnosticRequestEventComponent t) { //3
          if (t == null)
            return this;
          if (this.event == null)
            this.event = new ArrayList<DiagnosticRequestEventComponent>();
          this.event.add(t);
          return this;
        }

        /**
         * @return The first repetition of repeating field {@link #event}, creating it if it does not already exist
         */
        public DiagnosticRequestEventComponent getEventFirstRep() { 
          if (getEvent().isEmpty()) {
            addEvent();
          }
          return getEvent().get(0);
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("bodySite", "CodeableConcept", "Anatomical location where the request test should be performed.  This is the target site.", 0, java.lang.Integer.MAX_VALUE, bodySite));
          childrenList.add(new Property("status", "code", "The status of this individual item within the order.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("event", "@DiagnosticRequest.event", "A summary of the events of interest that have occurred as this item of the request is processed.", 0, java.lang.Integer.MAX_VALUE, event));
        }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case 3059181: /*code*/ return this.code == null ? new Base[0] : new Base[] {this.code}; // CodeableConcept
        case 1702620169: /*bodySite*/ return this.bodySite == null ? new Base[0] : new Base[] {this.bodySite}; // CodeableConcept
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<DiagnosticRequestStatus>
        case 96891546: /*event*/ return this.event == null ? new Base[0] : this.event.toArray(new Base[this.event.size()]); // DiagnosticRequestEventComponent
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case 3059181: // code
          this.code = castToCodeableConcept(value); // CodeableConcept
          break;
        case 1702620169: // bodySite
          this.bodySite = castToCodeableConcept(value); // CodeableConcept
          break;
        case -892481550: // status
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
          break;
        case 96891546: // event
          this.getEvent().add((DiagnosticRequestEventComponent) value); // DiagnosticRequestEventComponent
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("code"))
          this.code = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("bodySite"))
          this.bodySite = castToCodeableConcept(value); // CodeableConcept
        else if (name.equals("status"))
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
        else if (name.equals("event"))
          this.getEvent().add((DiagnosticRequestEventComponent) value);
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case 3059181:  return getCode(); // CodeableConcept
        case 1702620169:  return getBodySite(); // CodeableConcept
        case -892481550: throw new FHIRException("Cannot make property status as it is not a complex type"); // Enumeration<DiagnosticRequestStatus>
        case 96891546:  return addEvent(); // DiagnosticRequestEventComponent
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("code")) {
          this.code = new CodeableConcept();
          return this.code;
        }
        else if (name.equals("bodySite")) {
          this.bodySite = new CodeableConcept();
          return this.bodySite;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type DiagnosticRequest.status");
        }
        else if (name.equals("event")) {
          return addEvent();
        }
        else
          return super.addChild(name);
      }

      public DiagnosticRequestItemComponent copy() {
        DiagnosticRequestItemComponent dst = new DiagnosticRequestItemComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.status = status == null ? null : status.copy();
        if (event != null) {
          dst.event = new ArrayList<DiagnosticRequestEventComponent>();
          for (DiagnosticRequestEventComponent i : event)
            dst.event.add(i.copy());
        };
        return dst;
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof DiagnosticRequestItemComponent))
          return false;
        DiagnosticRequestItemComponent o = (DiagnosticRequestItemComponent) other;
        return compareDeep(code, o.code, true) && compareDeep(bodySite, o.bodySite, true) && compareDeep(status, o.status, true)
           && compareDeep(event, o.event, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof DiagnosticRequestItemComponent))
          return false;
        DiagnosticRequestItemComponent o = (DiagnosticRequestItemComponent) other;
        return compareValues(status, o.status, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(code, bodySite, status, event
          );
      }

  public String fhirType() {
    return "DiagnosticRequest.item";

  }

  }

    /**
     * Identifiers assigned to this order instance by the orderer and/or  the receiver and/or order fulfiller.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Identifiers assigned to this order", formalDefinition="Identifiers assigned to this order instance by the orderer and/or  the receiver and/or order fulfiller." )
    protected List<Identifier> identifier;

    /**
     * The status of the order.
     */
    @Child(name = "status", type = {CodeType.class}, order=1, min=0, max=1, modifier=true, summary=true)
    @Description(shortDefinition="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", formalDefinition="The status of the order." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-request-status")
    protected Enumeration<DiagnosticRequestStatus> status;

    /**
     * The clinical priority associated with this order.
     */
    @Child(name = "priority", type = {CodeType.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="routine | urgent | stat | asap", formalDefinition="The clinical priority associated with this order." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/diagnostic-request-priority")
    protected Enumeration<DiagnosticRequestPriority> priority;

    /**
     * On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).
     */
    @Child(name = "subject", type = {Patient.class, Group.class, Location.class, Device.class}, order=3, min=1, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Who and/or what test is about", formalDefinition="On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans)." )
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    protected Resource subjectTarget;

    /**
     * An encounter that provides additional information about the healthcare context in which this request is made.
     */
    @Child(name = "encounter", type = {Encounter.class}, order=4, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="The encounter that this diagnostic order is associated with", formalDefinition="An encounter that provides additional information about the healthcare context in which this request is made." )
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    protected Encounter encounterTarget;

    /**
     * The practitioner that holds legal responsibility for ordering the investigation.
     */
    @Child(name = "orderer", type = {Practitioner.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Who ordered the test", formalDefinition="The practitioner that holds legal responsibility for ordering the investigation." )
    protected Reference orderer;

    /**
     * The actual object that is the target of the reference (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    protected Practitioner ordererTarget;

    /**
     * An explanation or justification for why this diagnostic investigation is being requested.   This is often for billing purposes.  May relate to the resources referred to in supportingInformation.
     */
    @Child(name = "reason", type = {CodeableConcept.class}, order=6, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Explanation/Justification for test", formalDefinition="An explanation or justification for why this diagnostic investigation is being requested.   This is often for billing purposes.  May relate to the resources referred to in supportingInformation." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/condition-code")
    protected List<CodeableConcept> reason;

    /**
     * Additional clinical information about the patient or specimen that may influence test interpretations.  This includes observations explicitly requested by the producer(filler) to provide context or supporting information needed to complete the order.
     */
    @Child(name = "supportingInformation", type = {Observation.class, Condition.class, DocumentReference.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Additional clinical information", formalDefinition="Additional clinical information about the patient or specimen that may influence test interpretations.  This includes observations explicitly requested by the producer(filler) to provide context or supporting information needed to complete the order." )
    protected List<Reference> supportingInformation;
    /**
     * The actual objects that are the target of the reference (Additional clinical information about the patient or specimen that may influence test interpretations.  This includes observations explicitly requested by the producer(filler) to provide context or supporting information needed to complete the order.)
     */
    protected List<Resource> supportingInformationTarget;


    /**
     * A summary of the events of interest that have occurred as the request is processed; e.g. when the order was made, various processing steps (specimens received), when it was completed.
     */
    @Child(name = "event", type = {}, order=8, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="A list of events of interest in the lifecycle", formalDefinition="A summary of the events of interest that have occurred as the request is processed; e.g. when the order was made, various processing steps (specimens received), when it was completed." )
    protected List<DiagnosticRequestEventComponent> event;

    /**
     * The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.
     */
    @Child(name = "item", type = {}, order=9, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="The items the orderer requested", formalDefinition="The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested." )
    protected List<DiagnosticRequestItemComponent> item;

    /**
     * Any other notes associated with this patient, specimen or order (e.g. "patient hates needles").
     */
    @Child(name = "note", type = {Annotation.class}, order=10, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Other notes and comments", formalDefinition="Any other notes associated with this patient, specimen or order (e.g. \"patient hates needles\")." )
    protected List<Annotation> note;

    private static final long serialVersionUID = -1305937195L;

  /**
   * Constructor
   */
    public DiagnosticRequest() {
      super();
    }

  /**
   * Constructor
   */
    public DiagnosticRequest(Reference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order instance by the orderer and/or  the receiver and/or order fulfiller.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public DiagnosticRequest addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #status} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<DiagnosticRequestStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The status of the order.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public DiagnosticRequest setStatusElement(Enumeration<DiagnosticRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the order.
     */
    public DiagnosticRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the order.
     */
    public DiagnosticRequest setStatus(DiagnosticRequestStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<DiagnosticRequestStatus>(new DiagnosticRequestStatusEnumFactory());
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #priority} (The clinical priority associated with this order.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public Enumeration<DiagnosticRequestPriority> getPriorityElement() { 
      if (this.priority == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.priority");
        else if (Configuration.doAutoCreate())
          this.priority = new Enumeration<DiagnosticRequestPriority>(new DiagnosticRequestPriorityEnumFactory()); // bb
      return this.priority;
    }

    public boolean hasPriorityElement() { 
      return this.priority != null && !this.priority.isEmpty();
    }

    public boolean hasPriority() { 
      return this.priority != null && !this.priority.isEmpty();
    }

    /**
     * @param value {@link #priority} (The clinical priority associated with this order.). This is the underlying object with id, value and extensions. The accessor "getPriority" gives direct access to the value
     */
    public DiagnosticRequest setPriorityElement(Enumeration<DiagnosticRequestPriority> value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return The clinical priority associated with this order.
     */
    public DiagnosticRequestPriority getPriority() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value The clinical priority associated with this order.
     */
    public DiagnosticRequest setPriority(DiagnosticRequestPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<DiagnosticRequestPriority>(new DiagnosticRequestPriorityEnumFactory());
        this.priority.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public Reference getSubject() { 
      if (this.subject == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.subject");
        else if (Configuration.doAutoCreate())
          this.subject = new Reference(); // cc
      return this.subject;
    }

    public boolean hasSubject() { 
      return this.subject != null && !this.subject.isEmpty();
    }

    /**
     * @param value {@link #subject} (On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public DiagnosticRequest setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public DiagnosticRequest setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public Reference getEncounter() { 
      if (this.encounter == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.encounter");
        else if (Configuration.doAutoCreate())
          this.encounter = new Reference(); // cc
      return this.encounter;
    }

    public boolean hasEncounter() { 
      return this.encounter != null && !this.encounter.isEmpty();
    }

    /**
     * @param value {@link #encounter} (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public DiagnosticRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      if (this.encounterTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.encounter");
        else if (Configuration.doAutoCreate())
          this.encounterTarget = new Encounter(); // aa
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public DiagnosticRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #orderer} (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public Reference getOrderer() { 
      if (this.orderer == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.orderer");
        else if (Configuration.doAutoCreate())
          this.orderer = new Reference(); // cc
      return this.orderer;
    }

    public boolean hasOrderer() { 
      return this.orderer != null && !this.orderer.isEmpty();
    }

    /**
     * @param value {@link #orderer} (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public DiagnosticRequest setOrderer(Reference value) { 
      this.orderer = value;
      return this;
    }

    /**
     * @return {@link #orderer} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public Practitioner getOrdererTarget() { 
      if (this.ordererTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create DiagnosticRequest.orderer");
        else if (Configuration.doAutoCreate())
          this.ordererTarget = new Practitioner(); // aa
      return this.ordererTarget;
    }

    /**
     * @param value {@link #orderer} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public DiagnosticRequest setOrdererTarget(Practitioner value) { 
      this.ordererTarget = value;
      return this;
    }

    /**
     * @return {@link #reason} (An explanation or justification for why this diagnostic investigation is being requested.   This is often for billing purposes.  May relate to the resources referred to in supportingInformation.)
     */
    public List<CodeableConcept> getReason() { 
      if (this.reason == null)
        this.reason = new ArrayList<CodeableConcept>();
      return this.reason;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setReason(List<CodeableConcept> theReason) { 
      this.reason = theReason;
      return this;
    }

    public boolean hasReason() { 
      if (this.reason == null)
        return false;
      for (CodeableConcept item : this.reason)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addReason() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.reason == null)
        this.reason = new ArrayList<CodeableConcept>();
      this.reason.add(t);
      return t;
    }

    public DiagnosticRequest addReason(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.reason == null)
        this.reason = new ArrayList<CodeableConcept>();
      this.reason.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #reason}, creating it if it does not already exist
     */
    public CodeableConcept getReasonFirstRep() { 
      if (getReason().isEmpty()) {
        addReason();
      }
      return getReason().get(0);
    }

    /**
     * @return {@link #supportingInformation} (Additional clinical information about the patient or specimen that may influence test interpretations.  This includes observations explicitly requested by the producer(filler) to provide context or supporting information needed to complete the order.)
     */
    public List<Reference> getSupportingInformation() { 
      if (this.supportingInformation == null)
        this.supportingInformation = new ArrayList<Reference>();
      return this.supportingInformation;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setSupportingInformation(List<Reference> theSupportingInformation) { 
      this.supportingInformation = theSupportingInformation;
      return this;
    }

    public boolean hasSupportingInformation() { 
      if (this.supportingInformation == null)
        return false;
      for (Reference item : this.supportingInformation)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addSupportingInformation() { //3
      Reference t = new Reference();
      if (this.supportingInformation == null)
        this.supportingInformation = new ArrayList<Reference>();
      this.supportingInformation.add(t);
      return t;
    }

    public DiagnosticRequest addSupportingInformation(Reference t) { //3
      if (t == null)
        return this;
      if (this.supportingInformation == null)
        this.supportingInformation = new ArrayList<Reference>();
      this.supportingInformation.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #supportingInformation}, creating it if it does not already exist
     */
    public Reference getSupportingInformationFirstRep() { 
      if (getSupportingInformation().isEmpty()) {
        addSupportingInformation();
      }
      return getSupportingInformation().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getSupportingInformationTarget() { 
      if (this.supportingInformationTarget == null)
        this.supportingInformationTarget = new ArrayList<Resource>();
      return this.supportingInformationTarget;
    }

    /**
     * @return {@link #event} (A summary of the events of interest that have occurred as the request is processed; e.g. when the order was made, various processing steps (specimens received), when it was completed.)
     */
    public List<DiagnosticRequestEventComponent> getEvent() { 
      if (this.event == null)
        this.event = new ArrayList<DiagnosticRequestEventComponent>();
      return this.event;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setEvent(List<DiagnosticRequestEventComponent> theEvent) { 
      this.event = theEvent;
      return this;
    }

    public boolean hasEvent() { 
      if (this.event == null)
        return false;
      for (DiagnosticRequestEventComponent item : this.event)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public DiagnosticRequestEventComponent addEvent() { //3
      DiagnosticRequestEventComponent t = new DiagnosticRequestEventComponent();
      if (this.event == null)
        this.event = new ArrayList<DiagnosticRequestEventComponent>();
      this.event.add(t);
      return t;
    }

    public DiagnosticRequest addEvent(DiagnosticRequestEventComponent t) { //3
      if (t == null)
        return this;
      if (this.event == null)
        this.event = new ArrayList<DiagnosticRequestEventComponent>();
      this.event.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #event}, creating it if it does not already exist
     */
    public DiagnosticRequestEventComponent getEventFirstRep() { 
      if (getEvent().isEmpty()) {
        addEvent();
      }
      return getEvent().get(0);
    }

    /**
     * @return {@link #item} (The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.)
     */
    public List<DiagnosticRequestItemComponent> getItem() { 
      if (this.item == null)
        this.item = new ArrayList<DiagnosticRequestItemComponent>();
      return this.item;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setItem(List<DiagnosticRequestItemComponent> theItem) { 
      this.item = theItem;
      return this;
    }

    public boolean hasItem() { 
      if (this.item == null)
        return false;
      for (DiagnosticRequestItemComponent item : this.item)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public DiagnosticRequestItemComponent addItem() { //3
      DiagnosticRequestItemComponent t = new DiagnosticRequestItemComponent();
      if (this.item == null)
        this.item = new ArrayList<DiagnosticRequestItemComponent>();
      this.item.add(t);
      return t;
    }

    public DiagnosticRequest addItem(DiagnosticRequestItemComponent t) { //3
      if (t == null)
        return this;
      if (this.item == null)
        this.item = new ArrayList<DiagnosticRequestItemComponent>();
      this.item.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #item}, creating it if it does not already exist
     */
    public DiagnosticRequestItemComponent getItemFirstRep() { 
      if (getItem().isEmpty()) {
        addItem();
      }
      return getItem().get(0);
    }

    /**
     * @return {@link #note} (Any other notes associated with this patient, specimen or order (e.g. "patient hates needles").)
     */
    public List<Annotation> getNote() { 
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      return this.note;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public DiagnosticRequest setNote(List<Annotation> theNote) { 
      this.note = theNote;
      return this;
    }

    public boolean hasNote() { 
      if (this.note == null)
        return false;
      for (Annotation item : this.note)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Annotation addNote() { //3
      Annotation t = new Annotation();
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return t;
    }

    public DiagnosticRequest addNote(Annotation t) { //3
      if (t == null)
        return this;
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #note}, creating it if it does not already exist
     */
    public Annotation getNoteFirstRep() { 
      if (getNote().isEmpty()) {
        addNote();
      }
      return getNote().get(0);
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order instance by the orderer and/or  the receiver and/or order fulfiller.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "The status of the order.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("priority", "code", "The clinical priority associated with this order.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("subject", "Reference(Patient|Group|Location|Device)", "On whom or what the investigation is to be performed. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "An encounter that provides additional information about the healthcare context in which this request is made.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("orderer", "Reference(Practitioner)", "The practitioner that holds legal responsibility for ordering the investigation.", 0, java.lang.Integer.MAX_VALUE, orderer));
        childrenList.add(new Property("reason", "CodeableConcept", "An explanation or justification for why this diagnostic investigation is being requested.   This is often for billing purposes.  May relate to the resources referred to in supportingInformation.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("supportingInformation", "Reference(Observation|Condition|DocumentReference)", "Additional clinical information about the patient or specimen that may influence test interpretations.  This includes observations explicitly requested by the producer(filler) to provide context or supporting information needed to complete the order.", 0, java.lang.Integer.MAX_VALUE, supportingInformation));
        childrenList.add(new Property("event", "", "A summary of the events of interest that have occurred as the request is processed; e.g. when the order was made, various processing steps (specimens received), when it was completed.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("item", "", "The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.", 0, java.lang.Integer.MAX_VALUE, item));
        childrenList.add(new Property("note", "Annotation", "Any other notes associated with this patient, specimen or order (e.g. \"patient hates needles\").", 0, java.lang.Integer.MAX_VALUE, note));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<DiagnosticRequestStatus>
        case -1165461084: /*priority*/ return this.priority == null ? new Base[0] : new Base[] {this.priority}; // Enumeration<DiagnosticRequestPriority>
        case -1867885268: /*subject*/ return this.subject == null ? new Base[0] : new Base[] {this.subject}; // Reference
        case 1524132147: /*encounter*/ return this.encounter == null ? new Base[0] : new Base[] {this.encounter}; // Reference
        case -1207109509: /*orderer*/ return this.orderer == null ? new Base[0] : new Base[] {this.orderer}; // Reference
        case -934964668: /*reason*/ return this.reason == null ? new Base[0] : this.reason.toArray(new Base[this.reason.size()]); // CodeableConcept
        case -1248768647: /*supportingInformation*/ return this.supportingInformation == null ? new Base[0] : this.supportingInformation.toArray(new Base[this.supportingInformation.size()]); // Reference
        case 96891546: /*event*/ return this.event == null ? new Base[0] : this.event.toArray(new Base[this.event.size()]); // DiagnosticRequestEventComponent
        case 3242771: /*item*/ return this.item == null ? new Base[0] : this.item.toArray(new Base[this.item.size()]); // DiagnosticRequestItemComponent
        case 3387378: /*note*/ return this.note == null ? new Base[0] : this.note.toArray(new Base[this.note.size()]); // Annotation
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public void setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(castToIdentifier(value)); // Identifier
          break;
        case -892481550: // status
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
          break;
        case -1165461084: // priority
          this.priority = new DiagnosticRequestPriorityEnumFactory().fromType(value); // Enumeration<DiagnosticRequestPriority>
          break;
        case -1867885268: // subject
          this.subject = castToReference(value); // Reference
          break;
        case 1524132147: // encounter
          this.encounter = castToReference(value); // Reference
          break;
        case -1207109509: // orderer
          this.orderer = castToReference(value); // Reference
          break;
        case -934964668: // reason
          this.getReason().add(castToCodeableConcept(value)); // CodeableConcept
          break;
        case -1248768647: // supportingInformation
          this.getSupportingInformation().add(castToReference(value)); // Reference
          break;
        case 96891546: // event
          this.getEvent().add((DiagnosticRequestEventComponent) value); // DiagnosticRequestEventComponent
          break;
        case 3242771: // item
          this.getItem().add((DiagnosticRequestItemComponent) value); // DiagnosticRequestItemComponent
          break;
        case 3387378: // note
          this.getNote().add(castToAnnotation(value)); // Annotation
          break;
        default: super.setProperty(hash, name, value);
        }

      }

      @Override
      public void setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier"))
          this.getIdentifier().add(castToIdentifier(value));
        else if (name.equals("status"))
          this.status = new DiagnosticRequestStatusEnumFactory().fromType(value); // Enumeration<DiagnosticRequestStatus>
        else if (name.equals("priority"))
          this.priority = new DiagnosticRequestPriorityEnumFactory().fromType(value); // Enumeration<DiagnosticRequestPriority>
        else if (name.equals("subject"))
          this.subject = castToReference(value); // Reference
        else if (name.equals("encounter"))
          this.encounter = castToReference(value); // Reference
        else if (name.equals("orderer"))
          this.orderer = castToReference(value); // Reference
        else if (name.equals("reason"))
          this.getReason().add(castToCodeableConcept(value));
        else if (name.equals("supportingInformation"))
          this.getSupportingInformation().add(castToReference(value));
        else if (name.equals("event"))
          this.getEvent().add((DiagnosticRequestEventComponent) value);
        else if (name.equals("item"))
          this.getItem().add((DiagnosticRequestItemComponent) value);
        else if (name.equals("note"))
          this.getNote().add(castToAnnotation(value));
        else
          super.setProperty(name, value);
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); // Identifier
        case -892481550: throw new FHIRException("Cannot make property status as it is not a complex type"); // Enumeration<DiagnosticRequestStatus>
        case -1165461084: throw new FHIRException("Cannot make property priority as it is not a complex type"); // Enumeration<DiagnosticRequestPriority>
        case -1867885268:  return getSubject(); // Reference
        case 1524132147:  return getEncounter(); // Reference
        case -1207109509:  return getOrderer(); // Reference
        case -934964668:  return addReason(); // CodeableConcept
        case -1248768647:  return addSupportingInformation(); // Reference
        case 96891546:  return addEvent(); // DiagnosticRequestEventComponent
        case 3242771:  return addItem(); // DiagnosticRequestItemComponent
        case 3387378:  return addNote(); // Annotation
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type DiagnosticRequest.status");
        }
        else if (name.equals("priority")) {
          throw new FHIRException("Cannot call addChild on a primitive type DiagnosticRequest.priority");
        }
        else if (name.equals("subject")) {
          this.subject = new Reference();
          return this.subject;
        }
        else if (name.equals("encounter")) {
          this.encounter = new Reference();
          return this.encounter;
        }
        else if (name.equals("orderer")) {
          this.orderer = new Reference();
          return this.orderer;
        }
        else if (name.equals("reason")) {
          return addReason();
        }
        else if (name.equals("supportingInformation")) {
          return addSupportingInformation();
        }
        else if (name.equals("event")) {
          return addEvent();
        }
        else if (name.equals("item")) {
          return addItem();
        }
        else if (name.equals("note")) {
          return addNote();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "DiagnosticRequest";

  }

      public DiagnosticRequest copy() {
        DiagnosticRequest dst = new DiagnosticRequest();
        copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        dst.status = status == null ? null : status.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.orderer = orderer == null ? null : orderer.copy();
        if (reason != null) {
          dst.reason = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : reason)
            dst.reason.add(i.copy());
        };
        if (supportingInformation != null) {
          dst.supportingInformation = new ArrayList<Reference>();
          for (Reference i : supportingInformation)
            dst.supportingInformation.add(i.copy());
        };
        if (event != null) {
          dst.event = new ArrayList<DiagnosticRequestEventComponent>();
          for (DiagnosticRequestEventComponent i : event)
            dst.event.add(i.copy());
        };
        if (item != null) {
          dst.item = new ArrayList<DiagnosticRequestItemComponent>();
          for (DiagnosticRequestItemComponent i : item)
            dst.item.add(i.copy());
        };
        if (note != null) {
          dst.note = new ArrayList<Annotation>();
          for (Annotation i : note)
            dst.note.add(i.copy());
        };
        return dst;
      }

      protected DiagnosticRequest typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof DiagnosticRequest))
          return false;
        DiagnosticRequest o = (DiagnosticRequest) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(status, o.status, true) && compareDeep(priority, o.priority, true)
           && compareDeep(subject, o.subject, true) && compareDeep(encounter, o.encounter, true) && compareDeep(orderer, o.orderer, true)
           && compareDeep(reason, o.reason, true) && compareDeep(supportingInformation, o.supportingInformation, true)
           && compareDeep(event, o.event, true) && compareDeep(item, o.item, true) && compareDeep(note, o.note, true)
          ;
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof DiagnosticRequest))
          return false;
        DiagnosticRequest o = (DiagnosticRequest) other;
        return compareValues(status, o.status, true) && compareValues(priority, o.priority, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, status, priority
          , subject, encounter, orderer, reason, supportingInformation, event, item, note
          );
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DiagnosticRequest;
   }

 /**
   * Search parameter: <b>item-past-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.event.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="item-past-status", path="DiagnosticRequest.item.event.status", description="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", type="token" )
  public static final String SP_ITEM_PAST_STATUS = "item-past-status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>item-past-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.event.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam ITEM_PAST_STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_ITEM_PAST_STATUS);

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>Identifiers assigned to this order</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="DiagnosticRequest.identifier", description="Identifiers assigned to this order", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>Identifiers assigned to this order</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>bodysite</b>
   * <p>
   * Description: <b>Location of requested test (if applicable)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.bodySite</b><br>
   * </p>
   */
  @SearchParamDefinition(name="bodysite", path="DiagnosticRequest.item.bodySite", description="Location of requested test (if applicable)", type="token" )
  public static final String SP_BODYSITE = "bodysite";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>bodysite</b>
   * <p>
   * Description: <b>Location of requested test (if applicable)</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.bodySite</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam BODYSITE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_BODYSITE);

 /**
   * Search parameter: <b>code</b>
   * <p>
   * Description: <b>Code to indicate the item (test or panel) being ordered</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.code</b><br>
   * </p>
   */
  @SearchParamDefinition(name="code", path="DiagnosticRequest.item.code", description="Code to indicate the item (test or panel) being ordered", type="token" )
  public static final String SP_CODE = "code";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>code</b>
   * <p>
   * Description: <b>Code to indicate the item (test or panel) being ordered</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.code</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam CODE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_CODE);

 /**
   * Search parameter: <b>event-date</b>
   * <p>
   * Description: <b>The date at which the event happened</b><br>
   * Type: <b>date</b><br>
   * Path: <b>DiagnosticRequest.event.dateTime</b><br>
   * </p>
   */
  @SearchParamDefinition(name="event-date", path="DiagnosticRequest.event.dateTime", description="The date at which the event happened", type="date" )
  public static final String SP_EVENT_DATE = "event-date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>event-date</b>
   * <p>
   * Description: <b>The date at which the event happened</b><br>
   * Type: <b>date</b><br>
   * Path: <b>DiagnosticRequest.event.dateTime</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam EVENT_DATE = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_EVENT_DATE);

 /**
   * Search parameter: <b>event-status-date</b>
   * <p>
   * Description: <b>A combination of past-status and date</b><br>
   * Type: <b>composite</b><br>
   * Path: <b></b><br>
   * </p>
   */
  @SearchParamDefinition(name="event-status-date", path="", description="A combination of past-status and date", type="composite", compositeOf={"event-status", "event-date"} )
  public static final String SP_EVENT_STATUS_DATE = "event-status-date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>event-status-date</b>
   * <p>
   * Description: <b>A combination of past-status and date</b><br>
   * Type: <b>composite</b><br>
   * Path: <b></b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.CompositeClientParam<ca.uhn.fhir.rest.gclient.TokenClientParam, ca.uhn.fhir.rest.gclient.DateClientParam> EVENT_STATUS_DATE = new ca.uhn.fhir.rest.gclient.CompositeClientParam<ca.uhn.fhir.rest.gclient.TokenClientParam, ca.uhn.fhir.rest.gclient.DateClientParam>(SP_EVENT_STATUS_DATE);

 /**
   * Search parameter: <b>subject</b>
   * <p>
   * Description: <b>Who and/or what test is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="subject", path="DiagnosticRequest.subject", description="Who and/or what test is about", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Device"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Patient") }, target={Device.class, Group.class, Location.class, Patient.class } )
  public static final String SP_SUBJECT = "subject";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>subject</b>
   * <p>
   * Description: <b>Who and/or what test is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam SUBJECT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_SUBJECT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>DiagnosticRequest:subject</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_SUBJECT = new ca.uhn.fhir.model.api.Include("DiagnosticRequest:subject").toLocked();

 /**
   * Search parameter: <b>encounter</b>
   * <p>
   * Description: <b>The encounter that this diagnostic order is associated with</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.encounter</b><br>
   * </p>
   */
  @SearchParamDefinition(name="encounter", path="DiagnosticRequest.encounter", description="The encounter that this diagnostic order is associated with", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Encounter") }, target={Encounter.class } )
  public static final String SP_ENCOUNTER = "encounter";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>encounter</b>
   * <p>
   * Description: <b>The encounter that this diagnostic order is associated with</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.encounter</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam ENCOUNTER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_ENCOUNTER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>DiagnosticRequest:encounter</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_ENCOUNTER = new ca.uhn.fhir.model.api.Include("DiagnosticRequest:encounter").toLocked();

 /**
   * Search parameter: <b>actor</b>
   * <p>
   * Description: <b>Who recorded or did this</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.event.actor, DiagnosticRequest.item.event.actor</b><br>
   * </p>
   */
  @SearchParamDefinition(name="actor", path="DiagnosticRequest.event.actor | DiagnosticRequest.item.event.actor", description="Who recorded or did this", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Device"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Practitioner") }, target={Device.class, Practitioner.class } )
  public static final String SP_ACTOR = "actor";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>actor</b>
   * <p>
   * Description: <b>Who recorded or did this</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.event.actor, DiagnosticRequest.item.event.actor</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam ACTOR = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_ACTOR);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>DiagnosticRequest:actor</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_ACTOR = new ca.uhn.fhir.model.api.Include("DiagnosticRequest:actor").toLocked();

 /**
   * Search parameter: <b>item-date</b>
   * <p>
   * Description: <b>The date at which the event happened</b><br>
   * Type: <b>date</b><br>
   * Path: <b>DiagnosticRequest.item.event.dateTime</b><br>
   * </p>
   */
  @SearchParamDefinition(name="item-date", path="DiagnosticRequest.item.event.dateTime", description="The date at which the event happened", type="date" )
  public static final String SP_ITEM_DATE = "item-date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>item-date</b>
   * <p>
   * Description: <b>The date at which the event happened</b><br>
   * Type: <b>date</b><br>
   * Path: <b>DiagnosticRequest.item.event.dateTime</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam ITEM_DATE = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_ITEM_DATE);

 /**
   * Search parameter: <b>item-status-date</b>
   * <p>
   * Description: <b>A combination of item-past-status and item-date</b><br>
   * Type: <b>composite</b><br>
   * Path: <b></b><br>
   * </p>
   */
  @SearchParamDefinition(name="item-status-date", path="", description="A combination of item-past-status and item-date", type="composite", compositeOf={"item-past-status", "item-date"} )
  public static final String SP_ITEM_STATUS_DATE = "item-status-date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>item-status-date</b>
   * <p>
   * Description: <b>A combination of item-past-status and item-date</b><br>
   * Type: <b>composite</b><br>
   * Path: <b></b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.CompositeClientParam<ca.uhn.fhir.rest.gclient.TokenClientParam, ca.uhn.fhir.rest.gclient.DateClientParam> ITEM_STATUS_DATE = new ca.uhn.fhir.rest.gclient.CompositeClientParam<ca.uhn.fhir.rest.gclient.TokenClientParam, ca.uhn.fhir.rest.gclient.DateClientParam>(SP_ITEM_STATUS_DATE);

 /**
   * Search parameter: <b>event-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.event.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="event-status", path="DiagnosticRequest.event.status", description="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", type="token" )
  public static final String SP_EVENT_STATUS = "event-status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>event-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.event.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam EVENT_STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_EVENT_STATUS);

 /**
   * Search parameter: <b>item-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="item-status", path="DiagnosticRequest.item.status", description="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", type="token" )
  public static final String SP_ITEM_STATUS = "item-status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>item-status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.item.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam ITEM_STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_ITEM_STATUS);

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>Who and/or what test is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.subject</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="DiagnosticRequest.subject", description="Who and/or what test is about", type="reference", target={Patient.class } )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>Who and/or what test is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.subject</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>DiagnosticRequest:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("DiagnosticRequest:patient").toLocked();

 /**
   * Search parameter: <b>orderer</b>
   * <p>
   * Description: <b>Who ordered the test</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.orderer</b><br>
   * </p>
   */
  @SearchParamDefinition(name="orderer", path="DiagnosticRequest.orderer", description="Who ordered the test", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Practitioner") }, target={Practitioner.class } )
  public static final String SP_ORDERER = "orderer";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>orderer</b>
   * <p>
   * Description: <b>Who ordered the test</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>DiagnosticRequest.orderer</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam ORDERER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_ORDERER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>DiagnosticRequest:orderer</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_ORDERER = new ca.uhn.fhir.model.api.Include("DiagnosticRequest:orderer").toLocked();

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="DiagnosticRequest.status", description="proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>proposed | draft | planned | requested | received | accepted | in-progress | review | completed | cancelled | suspended | rejected | failed | entered-in-error</b><br>
   * Type: <b>token</b><br>
   * Path: <b>DiagnosticRequest.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}

