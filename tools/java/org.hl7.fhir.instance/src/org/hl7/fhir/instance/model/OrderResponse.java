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

// Generated on Sat, Jun 1, 2013 09:23+1000 for FHIR v0.09

import java.util.*;

/**
 * A Response to an order
 */
public class OrderResponse extends Resource {

    public enum OrderOutcomeCode {
        pending, // 
        review, // 
        rejected, // 
        error, // 
        accepted, // 
        cancelled, // 
        aborted, // 
        complete, // 
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
            case aborted: return "aborted";
            case complete: return "complete";
            default: return "?";
          }
        }
    }

  public class OrderOutcomeCodeEnumFactory implements EnumFactory {
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
      if (code == OrderOutcomeCode.aborted)
        return "aborted";
      if (code == OrderOutcomeCode.complete)
        return "complete";
      return "?";
      }
    }

    /**
     * The order this is a response to
     */
    private ResourceReference request;

    /**
     * When the response was made
     */
    private DateTime date;

    /**
     * Who made the rResponse
     */
    private ResourceReference who;

    /**
     * If required by policy
     */
    private ResourceReference authority;

    /**
     * How much the request will/did cost
     */
    private Money cost;

    /**
     * The status of the response
     */
    private Enumeration<OrderOutcomeCode> code;

    /**
     * Additional description of the response
     */
    private String_ description;

    /**
     * Details of the outcome of performing the order
     */
    private List<ResourceReference> fulfillment = new ArrayList<ResourceReference>();

    public ResourceReference getRequest() { 
      return this.request;
    }

    public void setRequest(ResourceReference value) { 
      this.request = value;
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
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
    }

    public ResourceReference getWho() { 
      return this.who;
    }

    public void setWho(ResourceReference value) { 
      this.who = value;
    }

    public ResourceReference getAuthority() { 
      return this.authority;
    }

    public void setAuthority(ResourceReference value) { 
      this.authority = value;
    }

    public Money getCost() { 
      return this.cost;
    }

    public void setCost(Money value) { 
      this.cost = value;
    }

    public Enumeration<OrderOutcomeCode> getCode() { 
      return this.code;
    }

    public void setCode(Enumeration<OrderOutcomeCode> value) { 
      this.code = value;
    }

    public OrderOutcomeCode getCodeSimple() { 
      return this.code == null ? null : this.code.getValue();
    }

    public void setCodeSimple(OrderOutcomeCode value) { 
        if (this.code == null)
          this.code = new Enumeration<OrderOutcomeCode>();
        this.code.setValue(value);
    }

    public String_ getDescription() { 
      return this.description;
    }

    public void setDescription(String_ value) { 
      this.description = value;
    }

    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    public void setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
    }

    public List<ResourceReference> getFulfillment() { 
      return this.fulfillment;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OrderResponse;
   }


}

