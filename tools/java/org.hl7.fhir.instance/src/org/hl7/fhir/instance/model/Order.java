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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A request to perform an action.
 */
public class Order extends Resource {

    public static class OrderWhenComponent extends BackboneElement {
        /**
         * Code specifies when request should be done. The code may simply be a priority code.
         */
        protected CodeableConcept code;

        /**
         * A formal schedule.
         */
        protected Schedule schedule;

        private static final long serialVersionUID = -987281180L;

      public OrderWhenComponent() {
        super();
      }

        /**
         * @return {@link #code} (Code specifies when request should be done. The code may simply be a priority code.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code specifies when request should be done. The code may simply be a priority code.)
         */
        public OrderWhenComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #schedule} (A formal schedule.)
         */
        public Schedule getSchedule() { 
          return this.schedule;
        }

        /**
         * @param value {@link #schedule} (A formal schedule.)
         */
        public OrderWhenComponent setSchedule(Schedule value) { 
          this.schedule = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Code specifies when request should be done. The code may simply be a priority code.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("schedule", "Schedule", "A formal schedule.", 0, java.lang.Integer.MAX_VALUE, schedule));
        }

      public OrderWhenComponent copy() {
        OrderWhenComponent dst = new OrderWhenComponent();
        dst.code = code == null ? null : code.copy();
        dst.schedule = schedule == null ? null : schedule.copy();
        return dst;
      }

  }

    /**
     * Identifiers assigned to this order by the orderer or by the receiver.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * When the order was made.
     */
    protected DateTime date;

    /**
     * Patient this order is about.
     */
    protected ResourceReference subject;

    /**
     * The actual object that is the target of the reference (Patient this order is about.)
     */
    protected Patient subjectTarget;

    /**
     * Who initiated the order.
     */
    protected ResourceReference source;

    /**
     * The actual object that is the target of the reference (Who initiated the order.)
     */
    protected Practitioner sourceTarget;

    /**
     * Who is intended to fulfill the order.
     */
    protected ResourceReference target;

    /**
     * The actual object that is the target of the reference (Who is intended to fulfill the order.)
     */
    protected Resource targetTarget;

    /**
     * Text - why the order was made.
     */
    protected Type reason;

    /**
     * If required by policy.
     */
    protected ResourceReference authority;

    /**
     * The actual object that is the target of the reference (If required by policy.)
     */
    protected Resource authorityTarget;

    /**
     * When order should be fulfilled.
     */
    protected OrderWhenComponent when;

    /**
     * What action is being ordered.
     */
    protected List<ResourceReference> detail = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (What action is being ordered.)
     */
    protected List<Resource> detailTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = -1072413136L;

    public Order() {
      super();
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers assigned to this order by the orderer or by the receiver.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #date} (When the order was made.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (When the order was made.)
     */
    public Order setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return When the order was made.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value When the order was made.
     */
    public Order setDateSimple(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #subject} (Patient this order is about.)
     */
    public ResourceReference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Patient this order is about.)
     */
    public Order setSubject(ResourceReference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} (The actual object that is the target of the reference. Patient this order is about.)
     */
    public Patient getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} (The actual object that is the target of the reference. Patient this order is about.)
     */
    public Order setSubjectTarget(Patient value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #source} (Who initiated the order.)
     */
    public ResourceReference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (Who initiated the order.)
     */
    public Order setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #source} (The actual object that is the target of the reference. Who initiated the order.)
     */
    public Practitioner getSourceTarget() { 
      return this.sourceTarget;
    }

    /**
     * @param value {@link #source} (The actual object that is the target of the reference. Who initiated the order.)
     */
    public Order setSourceTarget(Practitioner value) { 
      this.sourceTarget = value;
      return this;
    }

    /**
     * @return {@link #target} (Who is intended to fulfill the order.)
     */
    public ResourceReference getTarget() { 
      return this.target;
    }

    /**
     * @param value {@link #target} (Who is intended to fulfill the order.)
     */
    public Order setTarget(ResourceReference value) { 
      this.target = value;
      return this;
    }

    /**
     * @return {@link #target} (The actual object that is the target of the reference. Who is intended to fulfill the order.)
     */
    public Resource getTargetTarget() { 
      return this.targetTarget;
    }

    /**
     * @param value {@link #target} (The actual object that is the target of the reference. Who is intended to fulfill the order.)
     */
    public Order setTargetTarget(Resource value) { 
      this.targetTarget = value;
      return this;
    }

    /**
     * @return {@link #reason} (Text - why the order was made.)
     */
    public Type getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Text - why the order was made.)
     */
    public Order setReason(Type value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #authority} (If required by policy.)
     */
    public ResourceReference getAuthority() { 
      return this.authority;
    }

    /**
     * @param value {@link #authority} (If required by policy.)
     */
    public Order setAuthority(ResourceReference value) { 
      this.authority = value;
      return this;
    }

    /**
     * @return {@link #authority} (The actual object that is the target of the reference. If required by policy.)
     */
    public Resource getAuthorityTarget() { 
      return this.authorityTarget;
    }

    /**
     * @param value {@link #authority} (The actual object that is the target of the reference. If required by policy.)
     */
    public Order setAuthorityTarget(Resource value) { 
      this.authorityTarget = value;
      return this;
    }

    /**
     * @return {@link #when} (When order should be fulfilled.)
     */
    public OrderWhenComponent getWhen() { 
      return this.when;
    }

    /**
     * @param value {@link #when} (When order should be fulfilled.)
     */
    public Order setWhen(OrderWhenComponent value) { 
      this.when = value;
      return this;
    }

    /**
     * @return {@link #detail} (What action is being ordered.)
     */
    public List<ResourceReference> getDetail() { 
      return this.detail;
    }

    // syntactic sugar
    /**
     * @return {@link #detail} (What action is being ordered.)
     */
    public ResourceReference addDetail() { 
      ResourceReference t = new ResourceReference();
      this.detail.add(t);
      return t;
    }

    /**
     * @return {@link #detail} (The actual objects that are the target of the reference. What action is being ordered.)
     */
    public List<Resource> getDetailTarget() { 
      return this.detailTarget;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order by the orderer or by the receiver.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("date", "dateTime", "When the order was made.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("subject", "Resource(Patient)", "Patient this order is about.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("source", "Resource(Practitioner)", "Who initiated the order.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("target", "Resource(Organization|Device|Practitioner)", "Who is intended to fulfill the order.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("reason[x]", "CodeableConcept|Resource(Any)", "Text - why the order was made.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("authority", "Resource(Any)", "If required by policy.", 0, java.lang.Integer.MAX_VALUE, authority));
        childrenList.add(new Property("when", "", "When order should be fulfilled.", 0, java.lang.Integer.MAX_VALUE, when));
        childrenList.add(new Property("detail", "Resource(Any)", "What action is being ordered.", 0, java.lang.Integer.MAX_VALUE, detail));
      }

      public Order copy() {
        Order dst = new Order();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.date = date == null ? null : date.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.source = source == null ? null : source.copy();
        dst.target = target == null ? null : target.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.when = when == null ? null : when.copy();
        dst.detail = new ArrayList<ResourceReference>();
        for (ResourceReference i : detail)
          dst.detail.add(i.copy());
        return dst;
      }

      protected Order typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Order;
   }


}

