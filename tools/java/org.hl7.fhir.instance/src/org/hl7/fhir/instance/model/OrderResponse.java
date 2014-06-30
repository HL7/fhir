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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A response to an order.
 */
public class OrderResponse extends Resource {

    public enum OrderOutcomeCode {
        pending, // The order is known, but no processing has occurred at this time.
        review, // The order is undergoing initial processing to determine whether it will be accepted (usually this involves human review).
        rejected, // The order was rejected because of a workflow/business logic reason.
        error, // The order was unable to be processed because of a technical error (i.e. unexpected error).
        accepted, // The order has been accepted, and work is in progress.
        cancelled, // Processing the order was halted at the initiators request.
        replaced, // The order has been cancelled and replaced by another.
        aborted, // Processing the order was stopped because of some workflow/business logic reason.
        complete, // The order has been completed.
        Null; // added to help the parsers
        public static OrderOutcomeCode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("pending".equals(codeString))
          return pending;
        if ("review".equals(codeString))
          return review;
        if ("rejected".equals(codeString))
          return rejected;
        if ("error".equals(codeString))
          return error;
        if ("accepted".equals(codeString))
          return accepted;
        if ("cancelled".equals(codeString))
          return cancelled;
        if ("replaced".equals(codeString))
          return replaced;
        if ("aborted".equals(codeString))
          return aborted;
        if ("complete".equals(codeString))
          return complete;
        throw new Exception("Unknown OrderOutcomeCode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case pending: return "pending";
            case review: return "review";
            case rejected: return "rejected";
            case error: return "error";
            case accepted: return "accepted";
            case cancelled: return "cancelled";
            case replaced: return "replaced";
            case aborted: return "aborted";
            case complete: return "complete";
            default: return "?";
          }
        }
    }

  public static class OrderOutcomeCodeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("pending".equals(codeString))
          return OrderOutcomeCode.pending;
        if ("review".equals(codeString))
          return OrderOutcomeCode.review;
        if ("rejected".equals(codeString))
          return OrderOutcomeCode.rejected;
        if ("error".equals(codeString))
          return OrderOutcomeCode.error;
        if ("accepted".equals(codeString))
          return OrderOutcomeCode.accepted;
        if ("cancelled".equals(codeString))
          return OrderOutcomeCode.cancelled;
        if ("replaced".equals(codeString))
          return OrderOutcomeCode.replaced;
        if ("aborted".equals(codeString))
          return OrderOutcomeCode.aborted;
        if ("complete".equals(codeString))
          return OrderOutcomeCode.complete;
        throw new Exception("Unknown OrderOutcomeCode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == OrderOutcomeCode.pending)
        return "pending";
      if (code == OrderOutcomeCode.review)
        return "review";
      if (code == OrderOutcomeCode.rejected)
        return "rejected";
      if (code == OrderOutcomeCode.error)
        return "error";
      if (code == OrderOutcomeCode.accepted)
        return "accepted";
      if (code == OrderOutcomeCode.cancelled)
        return "cancelled";
      if (code == OrderOutcomeCode.replaced)
        return "replaced";
      if (code == OrderOutcomeCode.aborted)
        return "aborted";
      if (code == OrderOutcomeCode.complete)
        return "complete";
      return "?";
      }
    }

    /**
     * Identifiers assigned to this order. The identifiers are usually assigned by the system responding to the order, but they may be provided or added to by other systems.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A reference to the order that this is in response to.
     */
    protected ResourceReference request;

    /**
     * The actual object that is the target of the reference (A reference to the order that this is in response to.)
     */
    protected Order requestTarget;

    /**
     * The date and time at which this order response was made (created/posted).
     */
    protected DateTime date;

    /**
     * The person, organization, or device credited with making the response.
     */
    protected ResourceReference who;

    /**
     * The actual object that is the target of the reference (The person, organization, or device credited with making the response.)
     */
    protected Resource whoTarget;

    /**
     * A reference to an authority policy that is the reason for the response. Usually this is used when the order is rejected, to provide a reason for rejection.
     */
    protected Type authority;

    /**
     * What this response says about the status of the original order.
     */
    protected Enumeration<OrderOutcomeCode> code;

    /**
     * Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.
     */
    protected String_ description;

    /**
     * Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.
     */
    protected List<ResourceReference> fulfillment = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.)
     */
    protected List<Resource> fulfillmentTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = 834951178L;

    public OrderResponse() {
      super();
    }

    public OrderResponse(ResourceReference request, Enumeration<OrderOutcomeCode> code) {
      super();
      this.request = request;
      this.code = code;
    }

    /**
     * @return {@link #identifier} (Identifiers assigned to this order. The identifiers are usually assigned by the system responding to the order, but they may be provided or added to by other systems.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifiers assigned to this order. The identifiers are usually assigned by the system responding to the order, but they may be provided or added to by other systems.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #request} (A reference to the order that this is in response to.)
     */
    public ResourceReference getRequest() { 
      return this.request;
    }

    /**
     * @param value {@link #request} (A reference to the order that this is in response to.)
     */
    public OrderResponse setRequest(ResourceReference value) { 
      this.request = value;
      return this;
    }

    /**
     * @return {@link #request} (The actual object that is the target of the reference. A reference to the order that this is in response to.)
     */
    public Order getRequestTarget() { 
      return this.requestTarget;
    }

    /**
     * @param value {@link #request} (The actual object that is the target of the reference. A reference to the order that this is in response to.)
     */
    public OrderResponse setRequestTarget(Order value) { 
      this.requestTarget = value;
      return this;
    }

    /**
     * @return {@link #date} (The date and time at which this order response was made (created/posted).)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date and time at which this order response was made (created/posted).)
     */
    public OrderResponse setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date and time at which this order response was made (created/posted).
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date and time at which this order response was made (created/posted).
     */
    public OrderResponse setDateSimple(DateAndTime value) { 
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
     * @return {@link #who} (The person, organization, or device credited with making the response.)
     */
    public ResourceReference getWho() { 
      return this.who;
    }

    /**
     * @param value {@link #who} (The person, organization, or device credited with making the response.)
     */
    public OrderResponse setWho(ResourceReference value) { 
      this.who = value;
      return this;
    }

    /**
     * @return {@link #who} (The actual object that is the target of the reference. The person, organization, or device credited with making the response.)
     */
    public Resource getWhoTarget() { 
      return this.whoTarget;
    }

    /**
     * @param value {@link #who} (The actual object that is the target of the reference. The person, organization, or device credited with making the response.)
     */
    public OrderResponse setWhoTarget(Resource value) { 
      this.whoTarget = value;
      return this;
    }

    /**
     * @return {@link #authority} (A reference to an authority policy that is the reason for the response. Usually this is used when the order is rejected, to provide a reason for rejection.)
     */
    public Type getAuthority() { 
      return this.authority;
    }

    /**
     * @param value {@link #authority} (A reference to an authority policy that is the reason for the response. Usually this is used when the order is rejected, to provide a reason for rejection.)
     */
    public OrderResponse setAuthority(Type value) { 
      this.authority = value;
      return this;
    }

    /**
     * @return {@link #code} (What this response says about the status of the original order.)
     */
    public Enumeration<OrderOutcomeCode> getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (What this response says about the status of the original order.)
     */
    public OrderResponse setCode(Enumeration<OrderOutcomeCode> value) { 
      this.code = value;
      return this;
    }

    /**
     * @return What this response says about the status of the original order.
     */
    public OrderOutcomeCode getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    /**
     * @param value What this response says about the status of the original order.
     */
    public OrderResponse setCodeSimple(OrderOutcomeCode value) { 
        if (this.code == null)
          this.code = new Enumeration<OrderOutcomeCode>();
        this.code.setValue(value);
      return this;
    }

    /**
     * @return {@link #description} (Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.)
     */
    public OrderResponse setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.
     */
    public OrderResponse setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #fulfillment} (Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.)
     */
    public List<ResourceReference> getFulfillment() { 
      return this.fulfillment;
    }

    // syntactic sugar
    /**
     * @return {@link #fulfillment} (Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.)
     */
    public ResourceReference addFulfillment() { 
      ResourceReference t = new ResourceReference();
      this.fulfillment.add(t);
      return t;
    }

    /**
     * @return {@link #fulfillment} (The actual objects that are the target of the reference. Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.)
     */
    public List<Resource> getFulfillmentTarget() { 
      return this.fulfillmentTarget;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifiers assigned to this order. The identifiers are usually assigned by the system responding to the order, but they may be provided or added to by other systems.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("request", "Resource(Order)", "A reference to the order that this is in response to.", 0, java.lang.Integer.MAX_VALUE, request));
        childrenList.add(new Property("date", "dateTime", "The date and time at which this order response was made (created/posted).", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("who", "Resource(Practitioner|Organization|Device)", "The person, organization, or device credited with making the response.", 0, java.lang.Integer.MAX_VALUE, who));
        childrenList.add(new Property("authority[x]", "CodeableConcept|Resource(Any)", "A reference to an authority policy that is the reason for the response. Usually this is used when the order is rejected, to provide a reason for rejection.", 0, java.lang.Integer.MAX_VALUE, authority));
        childrenList.add(new Property("code", "code", "What this response says about the status of the original order.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("description", "string", "Additional description about the response - e.g. a text description provided by a human user when making decisions about the order.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("fulfillment", "Resource(Any)", "Links to resources that provide details of the outcome of performing the order. E.g. Diagnostic Reports in a response that is made to an order that referenced a diagnostic order.", 0, java.lang.Integer.MAX_VALUE, fulfillment));
      }

      public OrderResponse copy() {
        OrderResponse dst = new OrderResponse();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.request = request == null ? null : request.copy();
        dst.date = date == null ? null : date.copy();
        dst.who = who == null ? null : who.copy();
        dst.authority = authority == null ? null : authority.copy();
        dst.code = code == null ? null : code.copy();
        dst.description = description == null ? null : description.copy();
        dst.fulfillment = new ArrayList<ResourceReference>();
        for (ResourceReference i : fulfillment)
          dst.fulfillment.add(i.copy());
        return dst;
      }

      protected OrderResponse typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OrderResponse;
   }


}

