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
 * Todo.
 */
public class Subscription extends Resource {

    public enum SubscriptionStatus {
        requested, // The client has requested the subscription, and the server has not yet set it up.
        active, // The subscription is active.
        error, // The server has an error executing the notification.
        off, // Too many errors have occurred or the subscription has expired.
        Null; // added to help the parsers
        public static SubscriptionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("requested".equals(codeString))
          return requested;
        if ("active".equals(codeString))
          return active;
        if ("error".equals(codeString))
          return error;
        if ("off".equals(codeString))
          return off;
        throw new Exception("Unknown SubscriptionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case requested: return "requested";
            case active: return "active";
            case error: return "error";
            case off: return "off";
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
          return SubscriptionStatus.requested;
        if ("active".equals(codeString))
          return SubscriptionStatus.active;
        if ("error".equals(codeString))
          return SubscriptionStatus.error;
        if ("off".equals(codeString))
          return SubscriptionStatus.off;
        throw new Exception("Unknown SubscriptionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SubscriptionStatus.requested)
        return "requested";
      if (code == SubscriptionStatus.active)
        return "active";
      if (code == SubscriptionStatus.error)
        return "error";
      if (code == SubscriptionStatus.off)
        return "off";
      return "?";
      }
    }

    public enum SubscriptionChannelType {
        resthook, // The channel is executed by making a post to the URI. If a payload is included, the URL is interpreted as the service base, and an update (PUT) is made.
        websocket, // The channel is executed by sending a packet across a web socket connection maintained by the client. The URL identifies the websocket, and the client binds to this URL.
        email, // The channel is executed by sending an email to the email addressed in the URI (which must be a mailto:).
        sms, // The channel is executed by sending an SMS message to the phone number identified in the URL (tel:).
        message, // The channel Is executed by sending a message (e.g. a Bundle with a MessageHeader resource etc) to the application identified in the URI.
        Null; // added to help the parsers
        public static SubscriptionChannelType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("rest-hook".equals(codeString))
          return resthook;
        if ("websocket".equals(codeString))
          return websocket;
        if ("email".equals(codeString))
          return email;
        if ("sms".equals(codeString))
          return sms;
        if ("message".equals(codeString))
          return message;
        throw new Exception("Unknown SubscriptionChannelType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case resthook: return "rest-hook";
            case websocket: return "websocket";
            case email: return "email";
            case sms: return "sms";
            case message: return "message";
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
          return SubscriptionChannelType.resthook;
        if ("websocket".equals(codeString))
          return SubscriptionChannelType.websocket;
        if ("email".equals(codeString))
          return SubscriptionChannelType.email;
        if ("sms".equals(codeString))
          return SubscriptionChannelType.sms;
        if ("message".equals(codeString))
          return SubscriptionChannelType.message;
        throw new Exception("Unknown SubscriptionChannelType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SubscriptionChannelType.resthook)
        return "rest-hook";
      if (code == SubscriptionChannelType.websocket)
        return "websocket";
      if (code == SubscriptionChannelType.email)
        return "email";
      if (code == SubscriptionChannelType.sms)
        return "sms";
      if (code == SubscriptionChannelType.message)
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
        protected Uri url;

        /**
         * ToDo.
         */
        protected String_ payload;

        /**
         * Usage depends on the channel type.
         */
        protected String_ header;

        private static final long serialVersionUID = -1450483897L;

      public SubscriptionChannelComponent() {
        super();
      }

      public SubscriptionChannelComponent(Enumeration<SubscriptionChannelType> type, String_ payload) {
        super();
        this.type = type;
        this.payload = payload;
      }

        /**
         * @return {@link #type} (Todo.)
         */
        public Enumeration<SubscriptionChannelType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Todo.)
         */
        public SubscriptionChannelComponent setType(Enumeration<SubscriptionChannelType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public SubscriptionChannelType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionChannelComponent setTypeSimple(SubscriptionChannelType value) { 
            if (this.type == null)
              this.type = new Enumeration<SubscriptionChannelType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (Todo.)
         */
        public Uri getUrl() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (Todo.)
         */
        public SubscriptionChannelComponent setUrl(Uri value) { 
          this.url = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionChannelComponent setUrlSimple(String value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #payload} (ToDo.)
         */
        public String_ getPayload() { 
          return this.payload;
        }

        /**
         * @param value {@link #payload} (ToDo.)
         */
        public SubscriptionChannelComponent setPayload(String_ value) { 
          this.payload = value;
          return this;
        }

        /**
         * @return ToDo.
         */
        public String getPayloadSimple() { 
          return this.payload == null ? null : this.payload.getValue();
        }

        /**
         * @param value ToDo.
         */
        public SubscriptionChannelComponent setPayloadSimple(String value) { 
            if (this.payload == null)
              this.payload = new String_();
            this.payload.setValue(value);
          return this;
        }

        /**
         * @return {@link #header} (Usage depends on the channel type.)
         */
        public String_ getHeader() { 
          return this.header;
        }

        /**
         * @param value {@link #header} (Usage depends on the channel type.)
         */
        public SubscriptionChannelComponent setHeader(String_ value) { 
          this.header = value;
          return this;
        }

        /**
         * @return Usage depends on the channel type.
         */
        public String getHeaderSimple() { 
          return this.header == null ? null : this.header.getValue();
        }

        /**
         * @param value Usage depends on the channel type.
         */
        public SubscriptionChannelComponent setHeaderSimple(String value) { 
          if (value == null)
            this.header = null;
          else {
            if (this.header == null)
              this.header = new String_();
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
        protected Uri term;

        /**
         * Todo.
         */
        protected Uri scheme;

        /**
         * Todo.
         */
        protected String_ description;

        private static final long serialVersionUID = 289225799L;

      public SubscriptionTagComponent() {
        super();
      }

      public SubscriptionTagComponent(Uri term, Uri scheme) {
        super();
        this.term = term;
        this.scheme = scheme;
      }

        /**
         * @return {@link #term} (Todo.)
         */
        public Uri getTerm() { 
          return this.term;
        }

        /**
         * @param value {@link #term} (Todo.)
         */
        public SubscriptionTagComponent setTerm(Uri value) { 
          this.term = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getTermSimple() { 
          return this.term == null ? null : this.term.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setTermSimple(String value) { 
            if (this.term == null)
              this.term = new Uri();
            this.term.setValue(value);
          return this;
        }

        /**
         * @return {@link #scheme} (Todo.)
         */
        public Uri getScheme() { 
          return this.scheme;
        }

        /**
         * @param value {@link #scheme} (Todo.)
         */
        public SubscriptionTagComponent setScheme(Uri value) { 
          this.scheme = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getSchemeSimple() { 
          return this.scheme == null ? null : this.scheme.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setSchemeSimple(String value) { 
            if (this.scheme == null)
              this.scheme = new Uri();
            this.scheme.setValue(value);
          return this;
        }

        /**
         * @return {@link #description} (Todo.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Todo.)
         */
        public SubscriptionTagComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Todo.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Todo.
         */
        public SubscriptionTagComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
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
        dst.term = term == null ? null : term.copy();
        dst.scheme = scheme == null ? null : scheme.copy();
        dst.description = description == null ? null : description.copy();
        return dst;
      }

  }

    /**
     * Todo.
     */
    protected String_ criteria;

    /**
     * Todo.
     */
    protected List<Contact> contact = new ArrayList<Contact>();

    /**
     * Todo.
     */
    protected String_ reason;

    /**
     * Todo.
     */
    protected Enumeration<SubscriptionStatus> status;

    /**
     * Todo.
     */
    protected String_ error;

    /**
     * Todo.
     */
    protected SubscriptionChannelComponent channel;

    /**
     * Todo.
     */
    protected Instant end;

    /**
     * Todo.
     */
    protected List<SubscriptionTagComponent> tag = new ArrayList<SubscriptionTagComponent>();

    private static final long serialVersionUID = -1645996685L;

    public Subscription() {
      super();
    }

    public Subscription(String_ criteria, String_ reason, Enumeration<SubscriptionStatus> status, SubscriptionChannelComponent channel) {
      super();
      this.criteria = criteria;
      this.reason = reason;
      this.status = status;
      this.channel = channel;
    }

    /**
     * @return {@link #criteria} (Todo.)
     */
    public String_ getCriteria() { 
      return this.criteria;
    }

    /**
     * @param value {@link #criteria} (Todo.)
     */
    public Subscription setCriteria(String_ value) { 
      this.criteria = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getCriteriaSimple() { 
      return this.criteria == null ? null : this.criteria.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setCriteriaSimple(String value) { 
        if (this.criteria == null)
          this.criteria = new String_();
        this.criteria.setValue(value);
      return this;
    }

    /**
     * @return {@link #contact} (Todo.)
     */
    public List<Contact> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    /**
     * @return {@link #contact} (Todo.)
     */
    public Contact addContact() { 
      Contact t = new Contact();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #reason} (Todo.)
     */
    public String_ getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Todo.)
     */
    public Subscription setReason(String_ value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getReasonSimple() { 
      return this.reason == null ? null : this.reason.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setReasonSimple(String value) { 
        if (this.reason == null)
          this.reason = new String_();
        this.reason.setValue(value);
      return this;
    }

    /**
     * @return {@link #status} (Todo.)
     */
    public Enumeration<SubscriptionStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Todo.)
     */
    public Subscription setStatus(Enumeration<SubscriptionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public SubscriptionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setStatusSimple(SubscriptionStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<SubscriptionStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #error} (Todo.)
     */
    public String_ getError() { 
      return this.error;
    }

    /**
     * @param value {@link #error} (Todo.)
     */
    public Subscription setError(String_ value) { 
      this.error = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public String getErrorSimple() { 
      return this.error == null ? null : this.error.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setErrorSimple(String value) { 
      if (value == null)
        this.error = null;
      else {
        if (this.error == null)
          this.error = new String_();
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
     * @return {@link #end} (Todo.)
     */
    public Instant getEnd() { 
      return this.end;
    }

    /**
     * @param value {@link #end} (Todo.)
     */
    public Subscription setEnd(Instant value) { 
      this.end = value;
      return this;
    }

    /**
     * @return Todo.
     */
    public DateAndTime getEndSimple() { 
      return this.end == null ? null : this.end.getValue();
    }

    /**
     * @param value Todo.
     */
    public Subscription setEndSimple(DateAndTime value) { 
      if (value == null)
        this.end = null;
      else {
        if (this.end == null)
          this.end = new Instant();
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

    // syntactic sugar
    /**
     * @return {@link #tag} (Todo.)
     */
    public SubscriptionTagComponent addTag() { 
      SubscriptionTagComponent t = new SubscriptionTagComponent();
      this.tag.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("criteria", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, criteria));
        childrenList.add(new Property("contact", "Contact", "Todo.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("reason", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("status", "code", "Todo.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("error", "string", "Todo.", 0, java.lang.Integer.MAX_VALUE, error));
        childrenList.add(new Property("channel", "", "Todo.", 0, java.lang.Integer.MAX_VALUE, channel));
        childrenList.add(new Property("end", "instant", "Todo.", 0, java.lang.Integer.MAX_VALUE, end));
        childrenList.add(new Property("tag", "", "Todo.", 0, java.lang.Integer.MAX_VALUE, tag));
      }

      public Subscription copy() {
        Subscription dst = new Subscription();
        dst.criteria = criteria == null ? null : criteria.copy();
        dst.contact = new ArrayList<Contact>();
        for (Contact i : contact)
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

