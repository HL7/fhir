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
 * A request for a diagnostic investigation service to be performed.
 */
public class DiagnosticOrder extends Resource {

    public enum DiagnosticOrderStatus {
        requested, // The request has been placed.
        received, // The receiving system has received the order, but not yet decided whether it will be performed.
        accepted, // The receiving system has accepted the order, but work has not yet commenced.
        inProgress, // The work to fulfill the order is happening.
        review, // The work is complete, and the outcomes are being reviewed for approval.
        completed, // The work has been complete, the report(s) released, and no further work is planned.
        suspended, // The request has been held by originating system/user request.
        rejected, // The receiving system has declined to fulfill the request.
        failed, // The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.
        Null; // added to help the parsers
        public static DiagnosticOrderStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return requested;
        if ("received".equals(codeString))
          return received;
        if ("accepted".equals(codeString))
          return accepted;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("review".equals(codeString))
          return review;
        if ("completed".equals(codeString))
          return completed;
        if ("suspended".equals(codeString))
          return suspended;
        if ("rejected".equals(codeString))
          return rejected;
        if ("failed".equals(codeString))
          return failed;
        throw new Exception("Unknown DiagnosticOrderStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case requested: return "requested";
            case received: return "received";
            case accepted: return "accepted";
            case inProgress: return "in progress";
            case review: return "review";
            case completed: return "completed";
            case suspended: return "suspended";
            case rejected: return "rejected";
            case failed: return "failed";
            default: return "?";
          }
        }
    }

  public static class DiagnosticOrderStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return DiagnosticOrderStatus.requested;
        if ("received".equals(codeString))
          return DiagnosticOrderStatus.received;
        if ("accepted".equals(codeString))
          return DiagnosticOrderStatus.accepted;
        if ("in progress".equals(codeString))
          return DiagnosticOrderStatus.inProgress;
        if ("review".equals(codeString))
          return DiagnosticOrderStatus.review;
        if ("completed".equals(codeString))
          return DiagnosticOrderStatus.completed;
        if ("suspended".equals(codeString))
          return DiagnosticOrderStatus.suspended;
        if ("rejected".equals(codeString))
          return DiagnosticOrderStatus.rejected;
        if ("failed".equals(codeString))
          return DiagnosticOrderStatus.failed;
        throw new Exception("Unknown DiagnosticOrderStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DiagnosticOrderStatus.requested)
        return "requested";
      if (code == DiagnosticOrderStatus.received)
        return "received";
      if (code == DiagnosticOrderStatus.accepted)
        return "accepted";
      if (code == DiagnosticOrderStatus.inProgress)
        return "in progress";
      if (code == DiagnosticOrderStatus.review)
        return "review";
      if (code == DiagnosticOrderStatus.completed)
        return "completed";
      if (code == DiagnosticOrderStatus.suspended)
        return "suspended";
      if (code == DiagnosticOrderStatus.rejected)
        return "rejected";
      if (code == DiagnosticOrderStatus.failed)
        return "failed";
      return "?";
      }
    }

    public enum DiagnosticOrderPriority {
        routine, // The order has a normal priority.
        urgent, // The order should be urgently.
        stat, // The order is time-critical.
        asap, // The order should be acted on as soon as possible.
        Null; // added to help the parsers
        public static DiagnosticOrderPriority fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return routine;
        if ("urgent".equals(codeString))
          return urgent;
        if ("stat".equals(codeString))
          return stat;
        if ("asap".equals(codeString))
          return asap;
        throw new Exception("Unknown DiagnosticOrderPriority code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case routine: return "routine";
            case urgent: return "urgent";
            case stat: return "stat";
            case asap: return "asap";
            default: return "?";
          }
        }
    }

  public static class DiagnosticOrderPriorityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("routine".equals(codeString))
          return DiagnosticOrderPriority.routine;
        if ("urgent".equals(codeString))
          return DiagnosticOrderPriority.urgent;
        if ("stat".equals(codeString))
          return DiagnosticOrderPriority.stat;
        if ("asap".equals(codeString))
          return DiagnosticOrderPriority.asap;
        throw new Exception("Unknown DiagnosticOrderPriority code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DiagnosticOrderPriority.routine)
        return "routine";
      if (code == DiagnosticOrderPriority.urgent)
        return "urgent";
      if (code == DiagnosticOrderPriority.stat)
        return "stat";
      if (code == DiagnosticOrderPriority.asap)
        return "asap";
      return "?";
      }
    }

    public static class DiagnosticOrderEventComponent extends BackboneElement {
        /**
         * The status for the event.
         */
        protected Enumeration<DiagnosticOrderStatus> status;

        /**
         * Additional information about the event that occurred - e.g. if the status remained unchanged.
         */
        protected CodeableConcept description;

        /**
         * The date/time at which the event occurred.
         */
        protected DateTimeType dateTime;

        /**
         * The person who was responsible for performing or recording the action.
         */
        protected ResourceReference actor;

        /**
         * The actual object that is the target of the reference (The person who was responsible for performing or recording the action.)
         */
        protected Resource actorTarget;

        private static final long serialVersionUID = -2127877353L;

      public DiagnosticOrderEventComponent() {
        super();
      }

      public DiagnosticOrderEventComponent(Enumeration<DiagnosticOrderStatus> status, DateTimeType dateTime) {
        super();
        this.status = status;
        this.dateTime = dateTime;
      }

        /**
         * @return {@link #status} (The status for the event.)
         */
        public Enumeration<DiagnosticOrderStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (The status for the event.)
         */
        public DiagnosticOrderEventComponent setStatus(Enumeration<DiagnosticOrderStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return The status for the event.
         */
        public DiagnosticOrderStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value The status for the event.
         */
        public DiagnosticOrderEventComponent setStatusSimple(DiagnosticOrderStatus value) { 
            if (this.status == null)
              this.status = new Enumeration<DiagnosticOrderStatus>();
            this.status.setValue(value);
          return this;
        }

        /**
         * @return {@link #description} (Additional information about the event that occurred - e.g. if the status remained unchanged.)
         */
        public CodeableConcept getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Additional information about the event that occurred - e.g. if the status remained unchanged.)
         */
        public DiagnosticOrderEventComponent setDescription(CodeableConcept value) { 
          this.description = value;
          return this;
        }

        /**
         * @return {@link #dateTime} (The date/time at which the event occurred.)
         */
        public DateTimeType getDateTime() { 
          return this.dateTime;
        }

        /**
         * @param value {@link #dateTime} (The date/time at which the event occurred.)
         */
        public DiagnosticOrderEventComponent setDateTime(DateTimeType value) { 
          this.dateTime = value;
          return this;
        }

        /**
         * @return The date/time at which the event occurred.
         */
        public DateAndTime getDateTimeSimple() { 
          return this.dateTime == null ? null : this.dateTime.getValue();
        }

        /**
         * @param value The date/time at which the event occurred.
         */
        public DiagnosticOrderEventComponent setDateTimeSimple(DateAndTime value) { 
            if (this.dateTime == null)
              this.dateTime = new DateTimeType();
            this.dateTime.setValue(value);
          return this;
        }

        /**
         * @return {@link #actor} (The person who was responsible for performing or recording the action.)
         */
        public ResourceReference getActor() { 
          return this.actor;
        }

        /**
         * @param value {@link #actor} (The person who was responsible for performing or recording the action.)
         */
        public DiagnosticOrderEventComponent setActor(ResourceReference value) { 
          this.actor = value;
          return this;
        }

        /**
         * @return {@link #actor} (The actual object that is the target of the reference. The person who was responsible for performing or recording the action.)
         */
        public Resource getActorTarget() { 
          return this.actorTarget;
        }

        /**
         * @param value {@link #actor} (The actual object that is the target of the reference. The person who was responsible for performing or recording the action.)
         */
        public DiagnosticOrderEventComponent setActorTarget(Resource value) { 
          this.actorTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("status", "code", "The status for the event.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("description", "CodeableConcept", "Additional information about the event that occurred - e.g. if the status remained unchanged.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("dateTime", "dateTime", "The date/time at which the event occurred.", 0, java.lang.Integer.MAX_VALUE, dateTime));
          childrenList.add(new Property("actor", "Resource(Practitioner|Device)", "The person who was responsible for performing or recording the action.", 0, java.lang.Integer.MAX_VALUE, actor));
        }

      public DiagnosticOrderEventComponent copy() {
        DiagnosticOrderEventComponent dst = new DiagnosticOrderEventComponent();
        dst.status = status == null ? null : status.copy();
        dst.description = description == null ? null : description.copy();
        dst.dateTime = dateTime == null ? null : dateTime.copy();
        dst.actor = actor == null ? null : actor.copy();
        return dst;
      }

  }

    public static class DiagnosticOrderItemComponent extends BackboneElement {
        /**
         * A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.
         */
        protected CodeableConcept code;

        /**
         * If the item is related to a specific speciment.
         */
        protected List<ResourceReference> specimen = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (If the item is related to a specific speciment.)
         */
        protected List<Specimen> specimenTarget = new ArrayList<Specimen>();


        /**
         * Anatomical location where the request test should be performed.
         */
        protected CodeableConcept bodySite;

        /**
         * The status of this individual item within the order.
         */
        protected Enumeration<DiagnosticOrderStatus> status;

        /**
         * A summary of the events of interest that have occurred as this item of the request is processed.
         */
        protected List<DiagnosticOrderEventComponent> event = new ArrayList<DiagnosticOrderEventComponent>();

        private static final long serialVersionUID = -326190686L;

      public DiagnosticOrderItemComponent() {
        super();
      }

      public DiagnosticOrderItemComponent(CodeableConcept code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.)
         */
        public DiagnosticOrderItemComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #specimen} (If the item is related to a specific speciment.)
         */
        public List<ResourceReference> getSpecimen() { 
          return this.specimen;
        }

    // syntactic sugar
        /**
         * @return {@link #specimen} (If the item is related to a specific speciment.)
         */
        public ResourceReference addSpecimen() { 
          ResourceReference t = new ResourceReference();
          this.specimen.add(t);
          return t;
        }

        /**
         * @return {@link #specimen} (The actual objects that are the target of the reference. If the item is related to a specific speciment.)
         */
        public List<Specimen> getSpecimenTarget() { 
          return this.specimenTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #specimen} (Add an actual object that is the target of the reference. If the item is related to a specific speciment.)
         */
        public Specimen addSpecimenTarget() { 
          Specimen r = new Specimen();
          this.specimenTarget.add(r);
          return r;
        }

        /**
         * @return {@link #bodySite} (Anatomical location where the request test should be performed.)
         */
        public CodeableConcept getBodySite() { 
          return this.bodySite;
        }

        /**
         * @param value {@link #bodySite} (Anatomical location where the request test should be performed.)
         */
        public DiagnosticOrderItemComponent setBodySite(CodeableConcept value) { 
          this.bodySite = value;
          return this;
        }

        /**
         * @return {@link #status} (The status of this individual item within the order.)
         */
        public Enumeration<DiagnosticOrderStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (The status of this individual item within the order.)
         */
        public DiagnosticOrderItemComponent setStatus(Enumeration<DiagnosticOrderStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return The status of this individual item within the order.
         */
        public DiagnosticOrderStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value The status of this individual item within the order.
         */
        public DiagnosticOrderItemComponent setStatusSimple(DiagnosticOrderStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<DiagnosticOrderStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #event} (A summary of the events of interest that have occurred as this item of the request is processed.)
         */
        public List<DiagnosticOrderEventComponent> getEvent() { 
          return this.event;
        }

    // syntactic sugar
        /**
         * @return {@link #event} (A summary of the events of interest that have occurred as this item of the request is processed.)
         */
        public DiagnosticOrderEventComponent addEvent() { 
          DiagnosticOrderEventComponent t = new DiagnosticOrderEventComponent();
          this.event.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "A code that identifies a particular diagnostic investigation, or panel of investigations, that have been requested.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("specimen", "Resource(Specimen)", "If the item is related to a specific speciment.", 0, java.lang.Integer.MAX_VALUE, specimen));
          childrenList.add(new Property("bodySite", "CodeableConcept", "Anatomical location where the request test should be performed.", 0, java.lang.Integer.MAX_VALUE, bodySite));
          childrenList.add(new Property("status", "code", "The status of this individual item within the order.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("event", "@DiagnosticOrder.event", "A summary of the events of interest that have occurred as this item of the request is processed.", 0, java.lang.Integer.MAX_VALUE, event));
        }

      public DiagnosticOrderItemComponent copy() {
        DiagnosticOrderItemComponent dst = new DiagnosticOrderItemComponent();
        dst.code = code == null ? null : code.copy();
        dst.specimen = new ArrayList<ResourceReference>();
        for (ResourceReference i : specimen)
          dst.specimen.add(i.copy());
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.status = status == null ? null : status.copy();
        dst.event = new ArrayList<DiagnosticOrderEventComponent>();
        for (DiagnosticOrderEventComponent i : event)
          dst.event.add(i.copy());
        return dst;
      }

  }

    /**
     * Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    protected Resource subjectTarget;

    /**
     * The practitioner that holds legal responsibility for ordering the investigation.
     */
    protected ResourceReference orderer;

    /**
     * The actual object that is the target of the reference (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    protected Practitioner ordererTarget;

    /**
     * Identifiers assigned to this order by the order or by the receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * An encounter that provides additional information about the healthcare context in which this request is made.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    protected Encounter encounterTarget;

    /**
     * An explanation or justification for why this diagnostic investigation is being requested.
     */
    protected StringType clinicalNotes;

    /**
     * Additional clinical information about the patient or specimen that may influence test interpretations.
     */
    protected List<ResourceReference> supportingInformation = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Additional clinical information about the patient or specimen that may influence test interpretations.)
     */
    protected List<Resource> supportingInformationTarget = new ArrayList<Resource>();


    /**
     * One or more specimens that the diagnostic investigation is about.
     */
    protected List<ResourceReference> specimen = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (One or more specimens that the diagnostic investigation is about.)
     */
    protected List<Specimen> specimenTarget = new ArrayList<Specimen>();


    /**
     * The status of the order.
     */
    protected Enumeration<DiagnosticOrderStatus> status;

    /**
     * The clinical priority associated with this order.
     */
    protected Enumeration<DiagnosticOrderPriority> priority;

    /**
     * A summary of the events of interest that have occurred as the request is processed. E.g. when the order was made, various processing steps (specimens received), when it was completed.
     */
    protected List<DiagnosticOrderEventComponent> event = new ArrayList<DiagnosticOrderEventComponent>();

    /**
     * The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.
     */
    protected List<DiagnosticOrderItemComponent> item = new ArrayList<DiagnosticOrderItemComponent>();

    private static final long serialVersionUID = -417583997L;

    public DiagnosticOrder() {
      super();
    }

    public DiagnosticOrder(ResourceReference subject) {
      super();
      this.subject = subject;
    }

    /**
     * @return {@link #subject} (Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public DiagnosticOrder setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).)
     */
    public DiagnosticOrder setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #orderer} (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public ResourceReference getOrderer() { 
      return this.orderer;
    }

    /**
     * @param value {@link #orderer} (The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public DiagnosticOrder setOrderer(ResourceReference value) { 
      this.orderer = value;
      return this;
    }

    /**
     * @return {@link #orderer} (The actual object that is the target of the reference. The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public Practitioner getOrdererTarget() { 
      return this.ordererTarget;
    }

    /**
     * @param value {@link #orderer} (The actual object that is the target of the reference. The practitioner that holds legal responsibility for ordering the investigation.)
     */
    public DiagnosticOrder setOrdererTarget(Practitioner value) { 
      this.ordererTarget = value;
      return this;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order or by the receiver.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the order or by the receiver.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #encounter} (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public DiagnosticOrder setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. An encounter that provides additional information about the healthcare context in which this request is made.)
     */
    public DiagnosticOrder setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #clinicalNotes} (An explanation or justification for why this diagnostic investigation is being requested.)
     */
    public StringType getClinicalNotes() { 
      return this.clinicalNotes;
    }

    /**
     * @param value {@link #clinicalNotes} (An explanation or justification for why this diagnostic investigation is being requested.)
     */
    public DiagnosticOrder setClinicalNotes(StringType value) { 
      this.clinicalNotes = value;
      return this;
    }

    /**
     * @return An explanation or justification for why this diagnostic investigation is being requested.
     */
    public String getClinicalNotesSimple() { 
      return this.clinicalNotes == null ? null : this.clinicalNotes.getValue();
    }

    /**
     * @param value An explanation or justification for why this diagnostic investigation is being requested.
     */
    public DiagnosticOrder setClinicalNotesSimple(String value) { 
      if (value == null)
        this.clinicalNotes = null;
      else {
        if (this.clinicalNotes == null)
          this.clinicalNotes = new StringType();
        this.clinicalNotes.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #supportingInformation} (Additional clinical information about the patient or specimen that may influence test interpretations.)
     */
    public List<ResourceReference> getSupportingInformation() { 
      return this.supportingInformation;
    }

    // syntactic sugar
    /**
     * @return {@link #supportingInformation} (Additional clinical information about the patient or specimen that may influence test interpretations.)
     */
    public ResourceReference addSupportingInformation() { 
      ResourceReference t = new ResourceReference();
      this.supportingInformation.add(t);
      return t;
    }

    /**
     * @return {@link #supportingInformation} (The actual objects that are the target of the reference. Additional clinical information about the patient or specimen that may influence test interpretations.)
     */
    public List<Resource> getSupportingInformationTarget() { 
      return this.supportingInformationTarget;
    }

    /**
     * @return {@link #specimen} (One or more specimens that the diagnostic investigation is about.)
     */
    public List<ResourceReference> getSpecimen() { 
      return this.specimen;
    }

    // syntactic sugar
    /**
     * @return {@link #specimen} (One or more specimens that the diagnostic investigation is about.)
     */
    public ResourceReference addSpecimen() { 
      ResourceReference t = new ResourceReference();
      this.specimen.add(t);
      return t;
    }

    /**
     * @return {@link #specimen} (The actual objects that are the target of the reference. One or more specimens that the diagnostic investigation is about.)
     */
    public List<Specimen> getSpecimenTarget() { 
      return this.specimenTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #specimen} (Add an actual object that is the target of the reference. One or more specimens that the diagnostic investigation is about.)
     */
    public Specimen addSpecimenTarget() { 
      Specimen r = new Specimen();
      this.specimenTarget.add(r);
      return r;
    }

    /**
     * @return {@link #status} (The status of the order.)
     */
    public Enumeration<DiagnosticOrderStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the order.)
     */
    public DiagnosticOrder setStatus(Enumeration<DiagnosticOrderStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the order.
     */
    public DiagnosticOrderStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the order.
     */
    public DiagnosticOrder setStatusSimple(DiagnosticOrderStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<DiagnosticOrderStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #priority} (The clinical priority associated with this order.)
     */
    public Enumeration<DiagnosticOrderPriority> getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (The clinical priority associated with this order.)
     */
    public DiagnosticOrder setPriority(Enumeration<DiagnosticOrderPriority> value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return The clinical priority associated with this order.
     */
    public DiagnosticOrderPriority getPrioritySimple() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    /**
     * @param value The clinical priority associated with this order.
     */
    public DiagnosticOrder setPrioritySimple(DiagnosticOrderPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<DiagnosticOrderPriority>();
        this.priority.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #event} (A summary of the events of interest that have occurred as the request is processed. E.g. when the order was made, various processing steps (specimens received), when it was completed.)
     */
    public List<DiagnosticOrderEventComponent> getEvent() { 
      return this.event;
    }

    // syntactic sugar
    /**
     * @return {@link #event} (A summary of the events of interest that have occurred as the request is processed. E.g. when the order was made, various processing steps (specimens received), when it was completed.)
     */
    public DiagnosticOrderEventComponent addEvent() { 
      DiagnosticOrderEventComponent t = new DiagnosticOrderEventComponent();
      this.event.add(t);
      return t;
    }

    /**
     * @return {@link #item} (The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.)
     */
    public List<DiagnosticOrderItemComponent> getItem() { 
      return this.item;
    }

    // syntactic sugar
    /**
     * @return {@link #item} (The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.)
     */
    public DiagnosticOrderItemComponent addItem() { 
      DiagnosticOrderItemComponent t = new DiagnosticOrderItemComponent();
      this.item.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("subject", "Resource(Patient|Group|Location|Device)", "Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("orderer", "Resource(Practitioner)", "The practitioner that holds legal responsibility for ordering the investigation.", 0, java.lang.Integer.MAX_VALUE, orderer));
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the order or by the receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "An encounter that provides additional information about the healthcare context in which this request is made.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("clinicalNotes", "string", "An explanation or justification for why this diagnostic investigation is being requested.", 0, java.lang.Integer.MAX_VALUE, clinicalNotes));
        childrenList.add(new Property("supportingInformation", "Resource(Observation|Condition)", "Additional clinical information about the patient or specimen that may influence test interpretations.", 0, java.lang.Integer.MAX_VALUE, supportingInformation));
        childrenList.add(new Property("specimen", "Resource(Specimen)", "One or more specimens that the diagnostic investigation is about.", 0, java.lang.Integer.MAX_VALUE, specimen));
        childrenList.add(new Property("status", "code", "The status of the order.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("priority", "code", "The clinical priority associated with this order.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("event", "", "A summary of the events of interest that have occurred as the request is processed. E.g. when the order was made, various processing steps (specimens received), when it was completed.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("item", "", "The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.", 0, java.lang.Integer.MAX_VALUE, item));
      }

      public DiagnosticOrder copy() {
        DiagnosticOrder dst = new DiagnosticOrder();
        dst.subject = subject == null ? null : subject.copy();
        dst.orderer = orderer == null ? null : orderer.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.clinicalNotes = clinicalNotes == null ? null : clinicalNotes.copy();
        dst.supportingInformation = new ArrayList<ResourceReference>();
        for (ResourceReference i : supportingInformation)
          dst.supportingInformation.add(i.copy());
        dst.specimen = new ArrayList<ResourceReference>();
        for (ResourceReference i : specimen)
          dst.specimen.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.event = new ArrayList<DiagnosticOrderEventComponent>();
        for (DiagnosticOrderEventComponent i : event)
          dst.event.add(i.copy());
        dst.item = new ArrayList<DiagnosticOrderItemComponent>();
        for (DiagnosticOrderItemComponent i : item)
          dst.item.add(i.copy());
        return dst;
      }

      protected DiagnosticOrder typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DiagnosticOrder;
   }


}

