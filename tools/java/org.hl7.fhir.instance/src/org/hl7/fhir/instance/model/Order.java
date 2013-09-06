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

// Generated on Fri, Sep 6, 2013 22:32+1000 for FHIR v0.11

import java.util.*;

/**
 * A request to perform an action.
 */
public class Order extends Resource {

    public class OrderWhenComponent extends Element {
        /**
         * Code specifies when request should be done. The code may simply be a priority code.
         */
        protected CodeableConcept code;

        /**
         * A formal schedule.
         */
        protected Schedule schedule;

        public CodeableConcept getCode() { 
          return this.code;
        }

        public void setCode(CodeableConcept value) { 
          this.code = value;
        }

        public Schedule getSchedule() { 
          return this.schedule;
        }

        public void setSchedule(Schedule value) { 
          this.schedule = value;
        }

      public OrderWhenComponent copy(Order e) {
        OrderWhenComponent dst = e.new OrderWhenComponent();
        dst.code = code == null ? null : code.copy();
        dst.schedule = schedule == null ? null : schedule.copy();
        return dst;
      }

  }

    /**
     * When the order was made.
     */
    protected DateTime date;

    /**
     * Patient this order is about.
     */
    protected ResourceReference subject;

    /**
     * Who initiated the order.
     */
    protected ResourceReference source;

    /**
     * Who is intended to fulfill the order.
     */
    protected ResourceReference target;

    /**
     * Text - why the order was made.
     */
    protected String_ reason;

    /**
     * If required by policy.
     */
    protected ResourceReference authority;

    /**
     * When order should be fulfilled.
     */
    protected OrderWhenComponent when;

    /**
     * What action is being ordered.
     */
    protected List<ResourceReference> detail = new ArrayList<ResourceReference>();

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
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
    }

    public ResourceReference getSubject() { 
      return this.subject;
    }

    public void setSubject(ResourceReference value) { 
      this.subject = value;
    }

    public ResourceReference getSource() { 
      return this.source;
    }

    public void setSource(ResourceReference value) { 
      this.source = value;
    }

    public ResourceReference getTarget() { 
      return this.target;
    }

    public void setTarget(ResourceReference value) { 
      this.target = value;
    }

    public String_ getReason() { 
      return this.reason;
    }

    public void setReason(String_ value) { 
      this.reason = value;
    }

    public String getReasonSimple() { 
      return this.reason == null ? null : this.reason.getValue();
    }

    public void setReasonSimple(String value) { 
      if (value == null)
        this.reason = null;
      else {
        if (this.reason == null)
          this.reason = new String_();
        this.reason.setValue(value);
      }
    }

    public ResourceReference getAuthority() { 
      return this.authority;
    }

    public void setAuthority(ResourceReference value) { 
      this.authority = value;
    }

    public OrderWhenComponent getWhen() { 
      return this.when;
    }

    public void setWhen(OrderWhenComponent value) { 
      this.when = value;
    }

    public List<ResourceReference> getDetail() { 
      return this.detail;
    }

      public Order copy() {
        Order dst = new Order();
        dst.date = date == null ? null : date.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.source = source == null ? null : source.copy();
        dst.target = target == null ? null : target.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.when = when == null ? null : when.copy(dst);
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

