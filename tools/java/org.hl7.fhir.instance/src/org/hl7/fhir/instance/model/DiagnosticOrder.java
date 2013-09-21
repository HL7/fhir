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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * A request for a diagnostic investigation service to be performed.
 */
public class DiagnosticOrder extends Resource {

    public enum DiagnosticOrderStatus {
        requested, // The request has been placed.
        received, // The receiving system has received the order, but not yet decided whether it will be performed.
        accepted, // The receiving system has accepted the order, but work has not yet commenced.
        inprogress, // The work to fulfill the order is happening.
        review, // The work is complete, and the outcomes are being reviewed for approval.
        complete, // The work has been complete, the report(s) released, and no further work is planned.
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
        if ("inprogress".equals(codeString))
          return inprogress;
        if ("review".equals(codeString))
          return review;
        if ("complete".equals(codeString))
          return complete;
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
            case inprogress: return "inprogress";
            case review: return "review";
            case complete: return "complete";
            case suspended: return "suspended";
            case rejected: return "rejected";
            case failed: return "failed";
            default: return "?";
          }
        }
    }

  public class DiagnosticOrderStatusEnumFactory implements EnumFactory {
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
        if ("inprogress".equals(codeString))
          return DiagnosticOrderStatus.inprogress;
        if ("review".equals(codeString))
          return DiagnosticOrderStatus.review;
        if ("complete".equals(codeString))
          return DiagnosticOrderStatus.complete;
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
      if (code == DiagnosticOrderStatus.inprogress)
        return "inprogress";
      if (code == DiagnosticOrderStatus.review)
        return "review";
      if (code == DiagnosticOrderStatus.complete)
        return "complete";
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
        normal, // The order has no particular priority with it.
        urgent, // The order should be urgently.
        stat, // The order is time-critical.
        Null; // added to help the parsers
        public static DiagnosticOrderPriority fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("normal".equals(codeString))
          return normal;
        if ("urgent".equals(codeString))
          return urgent;
        if ("stat".equals(codeString))
          return stat;
        throw new Exception("Unknown DiagnosticOrderPriority code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case normal: return "normal";
            case urgent: return "urgent";
            case stat: return "stat";
            default: return "?";
          }
        }
    }

  public class DiagnosticOrderPriorityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("normal".equals(codeString))
          return DiagnosticOrderPriority.normal;
        if ("urgent".equals(codeString))
          return DiagnosticOrderPriority.urgent;
        if ("stat".equals(codeString))
          return DiagnosticOrderPriority.stat;
        throw new Exception("Unknown DiagnosticOrderPriority code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DiagnosticOrderPriority.normal)
        return "normal";
      if (code == DiagnosticOrderPriority.urgent)
        return "urgent";
      if (code == DiagnosticOrderPriority.stat)
        return "stat";
      return "?";
      }
    }

    public class DiagnosticOrderEventComponent extends Element {
        /**
         * The status for the event.
         */
        protected Enumeration<DiagnosticOrderStatus> status;

        /**
         * The date/time at which the event occurred.
         */
        protected DateTime date;

        /**
         * The person who was responsible for performing or recording the action.
         */
        protected ResourceReference actor;

        public Enumeration<DiagnosticOrderStatus> getStatus() { 
          return this.status;
        }

        public void setStatus(Enumeration<DiagnosticOrderStatus> value) { 
          this.status = value;
        }

        public DiagnosticOrderStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        public void setStatusSimple(DiagnosticOrderStatus value) { 
            if (this.status == null)
              this.status = new Enumeration<DiagnosticOrderStatus>();
            this.status.setValue(value);
        }

        public DateTime getDate() { 
          return this.date;
        }

        public void setDate(DateTime value) { 
          this.date = value;
        }

        public String getDateSimple() { 
          return this.date == null ? null : this.date.getValue();
        }

        public void setDateSimple(String value) { 
            if (this.date == null)
              this.date = new DateTime();
            this.date.setValue(value);
        }

        public ResourceReference getActor() { 
          return this.actor;
        }

        public void setActor(ResourceReference value) { 
          this.actor = value;
        }

      public DiagnosticOrderEventComponent copy(DiagnosticOrder e) {
        DiagnosticOrderEventComponent dst = e.new DiagnosticOrderEventComponent();
        dst.status = status == null ? null : status.copy();
        dst.date = date == null ? null : date.copy();
        dst.actor = actor == null ? null : actor.copy();
        return dst;
      }

  }

    public class DiagnosticOrderItemComponent extends Element {
        /**
         * A code that identifies a particular diagnostic investigation that has been requested.
         */
        protected CodeableConcept code;

        /**
         * If the item is related to a specific speciment.
         */
        protected List<ResourceReference> specimen = new ArrayList<ResourceReference>();

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

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public List<ResourceReference> getSpecimen() { 
          return this.specimen;
        }

    // syntactic sugar
        public ResourceReference addSpecimen() { 
          ResourceReference t = new ResourceReference();
          this.specimen.add(t);
          return t;
        }

        public CodeableConcept getBodySite() { 
          return this.bodySite;
        }

        public void setBodySite(CodeableConcept value) { 
          this.bodySite = value;
        }

        public Enumeration<DiagnosticOrderStatus> getStatus() { 
          return this.status;
        }

        public void setStatus(Enumeration<DiagnosticOrderStatus> value) { 
          this.status = value;
        }

        public DiagnosticOrderStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        public void setStatusSimple(DiagnosticOrderStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<DiagnosticOrderStatus>();
            this.status.setValue(value);
          }
        }

        public List<DiagnosticOrderEventComponent> getEvent() { 
          return this.event;
        }

    // syntactic sugar
        public DiagnosticOrderEventComponent addEvent() { 
          DiagnosticOrderEventComponent t = new DiagnosticOrderEventComponent();
          this.event.add(t);
          return t;
        }

      public DiagnosticOrderItemComponent copy(DiagnosticOrder e) {
        DiagnosticOrderItemComponent dst = e.new DiagnosticOrderItemComponent();
        dst.code = code == null ? null : code.copy();
        dst.specimen = new ArrayList<ResourceReference>();
        for (ResourceReference i : specimen)
          dst.specimen.add(i.copy());
        dst.bodySite = bodySite == null ? null : bodySite.copy();
        dst.status = status == null ? null : status.copy();
        dst.event = new ArrayList<DiagnosticOrderEventComponent>();
        for (DiagnosticOrderEventComponent i : event)
          dst.event.add(i.copy(e));
        return dst;
      }

  }

    /**
     * Who or what the investigation is to be performed on. This is usually a human patient, but diagnostic tests can also be requested on animals, groups of humans or animals, devices such as dialysis machines, or even locations (typically for environmental scans).
     */
    protected ResourceReference subject;

    /**
     * The practitioner that holds legal responsibility for ordering the investigation.
     */
    protected ResourceReference orderer;

    /**
     * Identifiers assigned to this order by the order or by the receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * An encounter that provides additional informaton about the healthcare context in which this request is made.
     */
    protected ResourceReference encounter;

    /**
     * An explanation or justification for why this diagnostic investigation is being requested.
     */
    protected String_ clinicalNotes;

    /**
     * One or more specimens that the diagnostic investigation is about.
     */
    protected List<ResourceReference> specimen = new ArrayList<ResourceReference>();

    /**
     * The status of the order.
     */
    protected Enumeration<DiagnosticOrderStatus> status;

    /**
     * The clinical priority associated with this order.
     */
    protected Enumeration<DiagnosticOrderPriority> priority;

    /**
     * A summary of the events of interest that have occurred as the request is processed.
     */
    protected List<DiagnosticOrderEventComponent> event = new ArrayList<DiagnosticOrderEventComponent>();

    /**
     * The specific diagnostic investigations that are requested as part of this request. Sometimes, there can only be one item per request, but in most contexts, more than one investigation can be requested.
     */
    protected List<DiagnosticOrderItemComponent> item = new ArrayList<DiagnosticOrderItemComponent>();

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getOrderer() { 
      return this.orderer;
    }

    public void setOrderer(ResourceReference value) { 
      this.orderer = value;
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public String_ getClinicalNotes() { 
      return this.clinicalNotes;
    }

    public void setClinicalNotes(String_ value) { 
      this.clinicalNotes = value;
    }

    public String getClinicalNotesSimple() { 
      return this.clinicalNotes == null ? null : this.clinicalNotes.getValue();
    }

    public void setClinicalNotesSimple(String value) { 
      if (value == null)
        this.clinicalNotes = null;
      else {
        if (this.clinicalNotes == null)
          this.clinicalNotes = new String_();
        this.clinicalNotes.setValue(value);
      }
    }

    public List<ResourceReference> getSpecimen() { 
      return this.specimen;
    }

    // syntactic sugar
    public ResourceReference addSpecimen() { 
      ResourceReference t = new ResourceReference();
      this.specimen.add(t);
      return t;
    }

    public Enumeration<DiagnosticOrderStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<DiagnosticOrderStatus> value) { 
      this.status = value;
    }

    public DiagnosticOrderStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(DiagnosticOrderStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<DiagnosticOrderStatus>();
        this.status.setValue(value);
      }
    }

    public Enumeration<DiagnosticOrderPriority> getPriority() { 
      return this.priority;
    }

    public void setPriority(Enumeration<DiagnosticOrderPriority> value) { 
      this.priority = value;
    }

    public DiagnosticOrderPriority getPrioritySimple() { 
      return this.priority == null ? null : this.priority.getValue();
    }

    public void setPrioritySimple(DiagnosticOrderPriority value) { 
      if (value == null)
        this.priority = null;
      else {
        if (this.priority == null)
          this.priority = new Enumeration<DiagnosticOrderPriority>();
        this.priority.setValue(value);
      }
    }

    public List<DiagnosticOrderEventComponent> getEvent() { 
      return this.event;
    }

    // syntactic sugar
    public DiagnosticOrderEventComponent addEvent() { 
      DiagnosticOrderEventComponent t = new DiagnosticOrderEventComponent();
      this.event.add(t);
      return t;
    }

    public List<DiagnosticOrderItemComponent> getItem() { 
      return this.item;
    }

    // syntactic sugar
    public DiagnosticOrderItemComponent addItem() { 
      DiagnosticOrderItemComponent t = new DiagnosticOrderItemComponent();
      this.item.add(t);
      return t;
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
        dst.specimen = new ArrayList<ResourceReference>();
        for (ResourceReference i : specimen)
          dst.specimen.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.event = new ArrayList<DiagnosticOrderEventComponent>();
        for (DiagnosticOrderEventComponent i : event)
          dst.event.add(i.copy(dst));
        dst.item = new ArrayList<DiagnosticOrderItemComponent>();
        for (DiagnosticOrderItemComponent i : item)
          dst.item.add(i.copy(dst));
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

