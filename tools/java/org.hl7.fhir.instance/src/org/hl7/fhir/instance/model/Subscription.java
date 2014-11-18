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
 * Todo.
 */
public class Subscription extends DomainResource {

    public enum SubscriptionStatus {
        REQUESTED, // The client has requested the subscription, and the server has not yet set it up.
        ACTIVE, // The subscription is active.
        ERROR, // The server has an error executing the notification.
        OFF, // Too many errors have occurred or the subscription has expired.
        NULL; // added to help the parsers
        public static SubscriptionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return REQUESTED;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("error".equals(codeString))
          return ERROR;
        if ("off".equals(codeString))
          return OFF;
        throw new Exception("Unknown SubscriptionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUESTED: return "requested";
            case ACTIVE: return "active";
            case ERROR: return "error";
            case OFF: return "off";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUESTED: return "The client has requested the subscription, and the server has not yet set it up.";
            case ACTIVE: return "The subscription is active.";
            case ERROR: return "The server has an error executing the notification.";
            case OFF: return "Too many errors have occurred or the subscription has expired.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUESTED: return "requested";
            case ACTIVE: return "active";
            case ERROR: return "error";
            case OFF: return "off";
            default: return "?";
          }
        }
    }

  public static class SubscriptionStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return SubscriptionStatus.REQUESTED;
        if ("active".equals(codeString))
          return SubscriptionStatus.ACTIVE;
        if ("error".equals(codeString))
          return SubscriptionStatus.ERROR;
        if ("off".equals(codeString))
          return SubscriptionStatus.OFF;
        throw new Exception("Unknown SubscriptionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SubscriptionStatus.REQUESTED)
        return "requested";
      if (code == SubscriptionStatus.ACTIVE)
        return "active";
      if (code == SubscriptionStatus.ERROR)
        return "error";
      if (code == SubscriptionStatus.OFF)
        return "off";
      return "?";
      }
    }

    public enum SubscriptionChannelType {
        RESTHOOK, // The channel is executed by making a post to the URI. If a payload is included, the URL is interpreted as the service base, and an update (PUT) is made.
        WEBSOCKET, // The channel is executed by sending a packet across a web socket connection maintained by the client. The URL identifies the websocket, and the client binds to this URL.
        EMAIL, // The channel is executed by sending an email to the email addressed in the URI (which must be a mailto:).
        SMS, // The channel is executed by sending an SMS message to the phone number identified in the URL (tel:).
        MESSAGE, // The channel Is executed by sending a message (e.g. a Bundle with a MessageHeader resource etc) to the application identified in the URI.
        NULL; // added to help the parsers
        public static SubscriptionChannelType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("rest-hook".equals(codeString))
          return RESTHOOK;
        if ("websocket".equals(codeString))
          return WEBSOCKET;
        if ("email".equals(codeString))
          return EMAIL;
        if ("sms".equals(codeString))
          return SMS;
        if ("message".equals(codeString))
          return MESSAGE;
        throw new Exception("Unknown SubscriptionChannelType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case RESTHOOK: return "rest-hook";
            case WEBSOCKET: return "websocket";
            case EMAIL: return "email";
            case SMS: return "sms";
            case MESSAGE: return "message";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case RESTHOOK: return "The channel is executed by making a post to the URI. If a payload is included, the URL is interpreted as the service base, and an update (PUT) is made.";
            case WEBSOCKET: return "The channel is executed by sending a packet across a web socket connection maintained by the client. The URL identifies the websocket, and the client binds to this URL.";
            case EMAIL: return "The channel is executed by sending an email to the email addressed in the URI (which must be a mailto:).";
            case SMS: return "The channel is executed by sending an SMS message to the phone number identified in the URL (tel:).";
            case MESSAGE: return "The channel Is executed by sending a message (e.g. a Bundle with a MessageHeader resource etc) to the application identified in the URI.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case RESTHOOK: return "rest-hook";
            case WEBSOCKET: return "websocket";
            case EMAIL: return "email";
            case SMS: return "sms";
            case MESSAGE: return "message";
            default: return "?";
          }
        }
    }

  public static class SubscriptionChannelTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("rest-hook".equals(codeString))
          return SubscriptionChannelType.RESTHOOK;
        if ("websocket".equals(codeString))
          return SubscriptionChannelType.WEBSOCKET;
        if ("email".equals(codeString))
          return SubscriptionChannelType.EMAIL;
        if ("sms".equals(codeString))
          return SubscriptionChannelType.SMS;
        if ("message".equals(codeString))
          return SubscriptionChannelType.MESSAGE;
        throw new Exception("Unknown SubscriptionChannelType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SubscriptionChannelType.RESTHOOK)
        return "rest-hook";
      if (code == SubscriptionChannelType.WEBSOCKET)
        return "websocket";
      if (code == SubscriptionChannelType.EMAIL)
        return "email";
      if (code == SubscriptionChannelType.SMS)
        return "sms";
      if (code == SubscriptionChannelType.MESSAGE)
        return "message";
      return "?";
      }
    }

    public static class SubscriptionChannelComponent extends BackboneElement {
        /**
         * Todo.
         */
        protected Enumeration<SubscriptionChannelType> type;

        /**
         * Todo.
         */
        protected UriType url;

        /**
         * ToDo.
         */
        protected StringType payload;

        /**
         * Usage depends on the channel type.
         */
        protected StringType header;

        private static final long serialVersionUID = 904575965L;

      public SubscriptionChannelComponent() {
        super();
      }

      public SubscriptionChannelComponent(Enumeration<SubscriptionChannelType> type, StringType payload) {
        super();
        this.type = type;
        this.payload = payload;
      }

        /**
         * @return {@link #type} (Todo.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<SubscriptionChannelType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Todo.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public SubscriptionChannelComponent setTypeElement(Enumeration<SubscriptionChannelType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public SubscriptionChannelType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionChannelComponent setType(SubscriptionChannelType value) { 
            if (this.type == null)
              this.type = new Enumeration<SubscriptionChannelType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (Todo.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (Todo.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public SubscriptionChannelComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionChannelComponent setUrl(String value) { 
          if (Utilities.noString(value))
            this.url = null;
          else {
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #payload} (ToDo.). This is the underlying object with id, value and extensions. The accessor "getPayload" gives direct access to the value
         */
        public StringType getPayloadElement() { 
          return this.payload;
        }

        /**
         * @param value {@link #payload} (ToDo.). This is the underlying object with id, value and extensions. The accessor "getPayload" gives direct access to the value
         */
        public SubscriptionChannelComponent setPayloadElement(StringType value) { 
          this.payload = value;
          return this;
        }

        /**
         * @return ToDo.
         */
        public String getPayload() { 
          return this.payload == null ? null : this.payload.getValue();
        }

        /**
         * @param value ToDo.
         */
        public SubscriptionChannelComponent setPayload(String value) { 
            if (this.payload == null)
              this.payload = new StringType();
            this.payload.setValue(value);
          return this;
        }

        /**
         * @return {@link #header} (Usage depends on the channel type.). This is the underlying object with id, value and extensions. The accessor "getHeader" gives direct access to the value
         */
        public StringType getHeaderElement() { 
          return this.header;
        }

        /**
         * @param value {@link #header} (Usage depends on the channel type.). This is the underlying object with id, value and extensions. The accessor "getHeader" gives direct access to the value
         */
        public SubscriptionChannelComponent setHeaderElement(StringType value) { 
          this.header = value;
          return this;
        }

        /**
         * @return Usage depends on the channel type.
         */
        public String getHeader() { 
          return this.header == null ? null : this.header.getValue();
        }

        /**
         * @param value Usage depends on the channel type.
         */
        public SubscriptionChannelComponent setHeader(String value) { 
          if (Utilities.noString(value))
            this.header = null;
          else {
            if (this.header == null)
              this.header = new StringType();
            this.header.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "Todo.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("url", "uri", "Todo.", 0, java.lang.Integer.MAX_VALUE, url));
          childrenList.add(new Property("payload", "string", "ToDo.", 0, java.lang.Integer.MAX_VALUE, payload));
          childrenList.add(new Property("header", "string", "Usage depends on the channel type.", 0, java.lang.Integer.MAX_VALUE, header));
        }

      public SubscriptionChannelComponent copy() {
        SubscriptionChannelComponent dst = new SubscriptionChannelComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.url = url == null ? null : url.copy();
        dst.payload = payload == null ? null : payload.copy();
        dst.header = header == null ? null : header.copy();
        return dst;
      }

  }

    public static class SubscriptionTagComponent extends BackboneElement {
        /**
         * Todo.
         */
        protected UriType term;

        /**
         * Todo.
         */
        protected UriType scheme;

        /**
         * Todo.
         */
        protected StringType description;

        private static final long serialVersionUID = 957833176L;

      public SubscriptionTagComponent() {
        super();
      }

      public SubscriptionTagComponent(UriType term, UriType scheme) {
        super();
        this.term = term;
        this.scheme = scheme;
      }

        /**
         * @return {@link #term} (Todo.). This is the underlying object with id, value and extensions. The accessor "getTerm" gives direct access to the value
         */
        public UriType getTermElement() { 
          return this.term;
        }

        /**
         * @param value {@link #term} (Todo.). This is the underlying object with id, value and extensions. The accessor "getTerm" gives direct access to the value
         */
        public SubscriptionTagComponent setTermElement(UriType value) { 
          this.term = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getTerm() { 
          return this.term == null ? null : this.term.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setTerm(String value) { 
            if (this.term == null)
              this.term = new UriType();
            this.term.setValue(value);
          return this;
        }

        /**
         * @return {@link #scheme} (Todo.). This is the underlying object with id, value and extensions. The accessor "getScheme" gives direct access to the value
         */
        public UriType getSchemeElement() { 
          return this.scheme;
        }

        /**
         * @param value {@link #scheme} (Todo.). This is the underlying object with id, value and extensions. The accessor "getScheme" gives direct access to the value
         */
        public SubscriptionTagComponent setSchemeElement(UriType value) { 
          this.scheme = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getScheme() { 
          return this.scheme == null ? null : this.scheme.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setScheme(String value) { 
            if (this.scheme == null)
              this.scheme = new UriType();
            this.scheme.setValue(value);
          return this;
        }

        /**
         * @return {@link #description} (Todo.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Todo.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public SubscriptionTagComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("term", "uri", "Todo.", 0, java.lang.Integer.MAX_VALUE, term));
          childrenList.add(new Property("scheme", "uri", "Todo.", 0, java.lang.Integer.MAX_VALUE, scheme));
          childrenList.add(new Property("description", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, description));
        }

      public SubscriptionTagComponent copy() {
        SubscriptionTagComponent dst = new SubscriptionTagComponent();
        copyValues(dst);
        dst.term = term == null ? null : term.copy();
        dst.scheme = scheme == null ? null : scheme.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    /**
     * Todo.
     */
    protected StringType criteria;

    /**
     * Todo.
     */
    protected List<ContactPoint> contact = new ArrayList<ContactPoint>();

    /**
     * Todo.
     */
    protected StringType reason;

    /**
     * Todo.
     */
    protected Enumeration<SubscriptionStatus> status;

    /**
     * Todo.
     */
    protected StringType error;

    /**
     * Todo.
     */
    protected SubscriptionChannelComponent channel;

    /**
     * Todo.
     */
    protected InstantType end;

    /**
     * Todo.
     */
    protected List<SubscriptionTagComponent> tag = new ArrayList<SubscriptionTagComponent>();

    private static final long serialVersionUID = 1514571556L;

    public Subscription() {
      super();
    }

    public Subscription(StringType criteria, StringType reason, Enumeration<SubscriptionStatus> status, SubscriptionChannelComponent channel) {
      super();
      this.criteria = criteria;
      this.reason = reason;
      this.status = status;
      this.channel = channel;
    }

    /**
     * @return {@link #criteria} (Todo.). This is the underlying object with id, value and extensions. The accessor "getCriteria" gives direct access to the value
     */
    public StringType getCriteriaElement() { 
      return this.criteria;
    }

    /**
     * @param value {@link #criteria} (Todo.). This is the underlying object with id, value and extensions. The accessor "getCriteria" gives direct access to the value
     */
    public Subscription setCriteriaElement(StringType value) { 
      this.criteria = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getCriteria() { 
      return this.criteria == null ? null : this.criteria.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setCriteria(String value) { 
        if (this.criteria == null)
          this.criteria = new StringType();
        this.criteria.setValue(value);
      return this;
    }

    /**
     * @return {@link #contact} (Todo.)
     */
    public List<ContactPoint> getContact() { 
      return this.contact;
    }

    /**
     * @return {@link #contact} (Todo.)
     */
    // syntactic sugar
    public ContactPoint addContact() { //3
      ContactPoint t = new ContactPoint();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #reason} (Todo.). This is the underlying object with id, value and extensions. The accessor "getReason" gives direct access to the value
     */
    public StringType getReasonElement() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Todo.). This is the underlying object with id, value and extensions. The accessor "getReason" gives direct access to the value
     */
    public Subscription setReasonElement(StringType value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getReason() { 
      return this.reason == null ? null : this.reason.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setReason(String value) { 
        if (this.reason == null)
          this.reason = new StringType();
        this.reason.setValue(value);
      return this;
    }

    /**
     * @return {@link #status} (Todo.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<SubscriptionStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Todo.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Subscription setStatusElement(Enumeration<SubscriptionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public SubscriptionStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setStatus(SubscriptionStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<SubscriptionStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #error} (Todo.). This is the underlying object with id, value and extensions. The accessor "getError" gives direct access to the value
     */
    public StringType getErrorElement() { 
      return this.error;
    }

    /**
     * @param value {@link #error} (Todo.). This is the underlying object with id, value and extensions. The accessor "getError" gives direct access to the value
     */
    public Subscription setErrorElement(StringType value) { 
      this.error = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getError() { 
      return this.error == null ? null : this.error.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setError(String value) { 
      if (Utilities.noString(value))
        this.error = null;
      else {
        if (this.error == null)
          this.error = new StringType();
        this.error.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #channel} (Todo.)
     */
    public SubscriptionChannelComponent getChannel() { 
      return this.channel;
    }

    /**
     * @param value {@link #channel} (Todo.)
     */
    public Subscription setChannel(SubscriptionChannelComponent value) { 
      this.channel = value;
      return this;
    }

    /**
     * @return {@link #end} (Todo.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public InstantType getEndElement() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Todo.). This is the underlying object with id, value and extensions. The accessor "getEnd" gives direct access to the value
     */
    public Subscription setEndElement(InstantType value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public DateAndTime getEnd() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setEnd(DateAndTime value) { 
      if (value == null)
        this.end = null;
      else {
        if (this.end == null)
          this.end = new InstantType();
        this.end.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #tag} (Todo.)
     */
    public List<SubscriptionTagComponent> getTag() { 
      return this.tag;
    }

    /**
     * @return {@link #tag} (Todo.)
     */
    // syntactic sugar
    public SubscriptionTagComponent addTag() { //3
      SubscriptionTagComponent t = new SubscriptionTagComponent();
      this.tag.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("criteria", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, criteria));
        childrenList.add(new Property("contact", "ContactPoint", "Todo.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("reason", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("status", "code", "Todo.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("error", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, error));
        childrenList.add(new Property("channel", "", "Todo.", 0, java.lang.Integer.MAX_VALUE, channel));
        childrenList.add(new Property("end", "instant", "Todo.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("tag", "", "Todo.", 0, java.lang.Integer.MAX_VALUE, tag));
      }

      public Subscription copy() {
        Subscription dst = new Subscription();
        copyValues(dst);
        dst.criteria = criteria == null ? null : criteria.copy();
        dst.contact = new ArrayList<ContactPoint>();
        for (ContactPoint i : contact)
          dst.contact.add(i.copy());
        dst.reason = reason == null ? null : reason.copy();
        dst.status = status == null ? null : status.copy();
        dst.error = error == null ? null : error.copy();
        dst.channel = channel == null ? null : channel.copy();
        dst.end = end == null ? null : end.copy();
        dst.tag = new ArrayList<SubscriptionTagComponent>();
        for (SubscriptionTagComponent i : tag)
          dst.tag.add(i.copy());
        return dst;
      }

      protected Subscription typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Subscription;
   }


}

