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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * The root for a transmission that is either requesting or responding to an action.  The resource(s) that are the subject of the action as well as other Information related to the action are typically transmitted in a bundle of which the Message resource instance is the first resource in the bundle.
 */
public class Message extends Resource {

    public enum ResponseCode {
        ok, // The message was accepted and processed without error.
        transienterror, // Some internal unexpected error occurred - wait and try again. Note - this is usually used for things like database unavailable, which may be expected to resolve, though human intervention may be required.
        fatalerror, // The message was rejected because of some content in it. There is no point in re-sending without change. The response narrative SHALL describe what the issue is.
        Null; // added to help the parsers
        public static ResponseCode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ok;
        if ("transient-error".equals(codeString))
          return transienterror;
        if ("fatal-error".equals(codeString))
          return fatalerror;
        throw new Exception("Unknown ResponseCode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ok: return "ok";
            case transienterror: return "transient-error";
            case fatalerror: return "fatal-error";
            default: return "?";
          }
        }
    }

  public static class ResponseCodeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ResponseCode.ok;
        if ("transient-error".equals(codeString))
          return ResponseCode.transienterror;
        if ("fatal-error".equals(codeString))
          return ResponseCode.fatalerror;
        throw new Exception("Unknown ResponseCode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResponseCode.ok)
        return "ok";
      if (code == ResponseCode.transienterror)
        return "transient-error";
      if (code == ResponseCode.fatalerror)
        return "fatal-error";
      return "?";
      }
    }

    public static class MessageResponseComponent extends BackboneElement {
        /**
         * The id of the message that this a response to.
         */
        protected Id identifier;

        /**
         * Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.
         */
        protected Enumeration<ResponseCode> code;

        /**
         * Full details of any issues found in the message.
         */
        protected ResourceReference details;

      public MessageResponseComponent() {
        super();
      }

      public MessageResponseComponent(Id identifier, Enumeration<ResponseCode> code) {
        super();
        this.identifier = identifier;
        this.code = code;
      }

        public Id getIdentifier() { 
          return this.identifier;
        }

        public MessageResponseComponent setIdentifier(Id value) { 
          this.identifier = value;
          return this;
        }

        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public MessageResponseComponent setIdentifierSimple(String value) { 
            if (this.identifier == null)
              this.identifier = new Id();
            this.identifier.setValue(value);
          return this;
        }

        public Enumeration<ResponseCode> getCode() { 
          return this.code;
        }

        public MessageResponseComponent setCode(Enumeration<ResponseCode> value) { 
          this.code = value;
          return this;
        }

        public ResponseCode getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public MessageResponseComponent setCodeSimple(ResponseCode value) { 
            if (this.code == null)
              this.code = new Enumeration<ResponseCode>();
            this.code.setValue(value);
          return this;
        }

        public ResourceReference getDetails() { 
          return this.details;
        }

        public MessageResponseComponent setDetails(ResourceReference value) { 
          this.details = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "id", "The id of the message that this a response to.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("code", "code", "Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("details", "Resource(OperationOutcome)", "Full details of any issues found in the message.", 0, java.lang.Integer.MAX_VALUE, details));
        }

      public MessageResponseComponent copy(Message e) {
        MessageResponseComponent dst = new MessageResponseComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.code = code == null ? null : code.copy();
        dst.details = details == null ? null : details.copy();
        return dst;
      }

  }

    public static class MessageSourceComponent extends BackboneElement {
        /**
         * Human-readable name for the target system.
         */
        protected String_ name;

        /**
         * May include configuration or other information useful in debugging.
         */
        protected String_ software;

        /**
         * Can convey versions of multiple systems in situations where a message passes through multiple hands.
         */
        protected String_ version;

        /**
         * An e-mail, phone, website or other contact point to use to resolve issues with message communications.
         */
        protected Contact contact;

        /**
         * Identifies the routing target to send acknowledgements to.
         */
        protected Uri endpoint;

      public MessageSourceComponent() {
        super();
      }

      public MessageSourceComponent(String_ software, Uri endpoint) {
        super();
        this.software = software;
        this.endpoint = endpoint;
      }

        public String_ getName() { 
          return this.name;
        }

        public MessageSourceComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public MessageSourceComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        public String_ getSoftware() { 
          return this.software;
        }

        public MessageSourceComponent setSoftware(String_ value) { 
          this.software = value;
          return this;
        }

        public String getSoftwareSimple() { 
          return this.software == null ? null : this.software.getValue();
        }

        public MessageSourceComponent setSoftwareSimple(String value) { 
            if (this.software == null)
              this.software = new String_();
            this.software.setValue(value);
          return this;
        }

        public String_ getVersion() { 
          return this.version;
        }

        public MessageSourceComponent setVersion(String_ value) { 
          this.version = value;
          return this;
        }

        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        public MessageSourceComponent setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new String_();
            this.version.setValue(value);
          }
          return this;
        }

        public Contact getContact() { 
          return this.contact;
        }

        public MessageSourceComponent setContact(Contact value) { 
          this.contact = value;
          return this;
        }

        public Uri getEndpoint() { 
          return this.endpoint;
        }

        public MessageSourceComponent setEndpoint(Uri value) { 
          this.endpoint = value;
          return this;
        }

        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        public MessageSourceComponent setEndpointSimple(String value) { 
            if (this.endpoint == null)
              this.endpoint = new Uri();
            this.endpoint.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "Human-readable name for the target system.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("software", "string", "May include configuration or other information useful in debugging.", 0, java.lang.Integer.MAX_VALUE, software));
          childrenList.add(new Property("version", "string", "Can convey versions of multiple systems in situations where a message passes through multiple hands.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("contact", "Contact", "An e-mail, phone, website or other contact point to use to resolve issues with message communications.", 0, java.lang.Integer.MAX_VALUE, contact));
          childrenList.add(new Property("endpoint", "uri", "Identifies the routing target to send acknowledgements to.", 0, java.lang.Integer.MAX_VALUE, endpoint));
        }

      public MessageSourceComponent copy(Message e) {
        MessageSourceComponent dst = new MessageSourceComponent();
        dst.name = name == null ? null : name.copy();
        dst.software = software == null ? null : software.copy();
        dst.version = version == null ? null : version.copy();
        dst.contact = contact == null ? null : contact.copy();
        dst.endpoint = endpoint == null ? null : endpoint.copy();
        return dst;
      }

  }

    public static class MessageDestinationComponent extends BackboneElement {
        /**
         * Human-readable name for the source system.
         */
        protected String_ name;

        /**
         * Identifies the target end system in situations where the initial message transmission is to an intermediary system.
         */
        protected ResourceReference target;

        /**
         * Indicates where the message should be routed to.
         */
        protected Uri endpoint;

      public MessageDestinationComponent() {
        super();
      }

      public MessageDestinationComponent(Uri endpoint) {
        super();
        this.endpoint = endpoint;
      }

        public String_ getName() { 
          return this.name;
        }

        public MessageDestinationComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public MessageDestinationComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        public ResourceReference getTarget() { 
          return this.target;
        }

        public MessageDestinationComponent setTarget(ResourceReference value) { 
          this.target = value;
          return this;
        }

        public Uri getEndpoint() { 
          return this.endpoint;
        }

        public MessageDestinationComponent setEndpoint(Uri value) { 
          this.endpoint = value;
          return this;
        }

        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        public MessageDestinationComponent setEndpointSimple(String value) { 
            if (this.endpoint == null)
              this.endpoint = new Uri();
            this.endpoint.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "Human-readable name for the source system.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("target", "Resource(Device)", "Identifies the target end system in situations where the initial message transmission is to an intermediary system.", 0, java.lang.Integer.MAX_VALUE, target));
          childrenList.add(new Property("endpoint", "uri", "Indicates where the message should be routed to.", 0, java.lang.Integer.MAX_VALUE, endpoint));
        }

      public MessageDestinationComponent copy(Message e) {
        MessageDestinationComponent dst = new MessageDestinationComponent();
        dst.name = name == null ? null : name.copy();
        dst.target = target == null ? null : target.copy();
        dst.endpoint = endpoint == null ? null : endpoint.copy();
        return dst;
      }

  }

    /**
     * The identifier of this message.
     */
    protected Id identifier;

    /**
     * The time that the message was sent.
     */
    protected Instant timestamp;

    /**
     * Code that identifies the event this message represents and connects it with it's definition. Events defined as part of the FHIR specification have the system value "http://hl7.org/fhir/message-type".
     */
    protected Coding event;

    /**
     * Information about the message that this message is a response to.  Only present if this message is a response.
     */
    protected MessageResponseComponent response;

    /**
     * The source application from which this message originated.
     */
    protected MessageSourceComponent source;

    /**
     * The destination application which the message is intended for.
     */
    protected List<MessageDestinationComponent> destination = new ArrayList<MessageDestinationComponent>();

    /**
     * The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.
     */
    protected ResourceReference enterer;

    /**
     * The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the Message. Can provide other authors in extensions.
     */
    protected ResourceReference author;

    /**
     * Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.
     */
    protected ResourceReference receiver;

    /**
     * The person or organization that accepts overall responsibility for the contents of the Message. The implication is that the message event happened under the policies of the responsible party.
     */
    protected ResourceReference responsible;

    /**
     * Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.
     */
    protected CodeableConcept reason;

    /**
     * The actual data of the message - a reference to the root/focus class of the event.
     */
    protected List<ResourceReference> data = new ArrayList<ResourceReference>();

    public Message() {
      super();
    }

    public Message(Id identifier, Instant timestamp, Coding event, MessageSourceComponent source) {
      super();
      this.identifier = identifier;
      this.timestamp = timestamp;
      this.event = event;
      this.source = source;
    }

    public Id getIdentifier() { 
      return this.identifier;
    }

    public Message setIdentifier(Id value) { 
      this.identifier = value;
      return this;
    }

    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    public Message setIdentifierSimple(String value) { 
        if (this.identifier == null)
          this.identifier = new Id();
        this.identifier.setValue(value);
      return this;
    }

    public Instant getTimestamp() { 
      return this.timestamp;
    }

    public Message setTimestamp(Instant value) { 
      this.timestamp = value;
      return this;
    }

    public Calendar getTimestampSimple() { 
      return this.timestamp == null ? null : this.timestamp.getValue();
    }

    public Message setTimestampSimple(Calendar value) { 
        if (this.timestamp == null)
          this.timestamp = new Instant();
        this.timestamp.setValue(value);
      return this;
    }

    public Coding getEvent() { 
      return this.event;
    }

    public Message setEvent(Coding value) { 
      this.event = value;
      return this;
    }

    public MessageResponseComponent getResponse() { 
      return this.response;
    }

    public Message setResponse(MessageResponseComponent value) { 
      this.response = value;
      return this;
    }

    public MessageSourceComponent getSource() { 
      return this.source;
    }

    public Message setSource(MessageSourceComponent value) { 
      this.source = value;
      return this;
    }

    public List<MessageDestinationComponent> getDestination() { 
      return this.destination;
    }

    // syntactic sugar
    public MessageDestinationComponent addDestination() { 
      MessageDestinationComponent t = new MessageDestinationComponent();
      this.destination.add(t);
      return t;
    }

    public ResourceReference getEnterer() { 
      return this.enterer;
    }

    public Message setEnterer(ResourceReference value) { 
      this.enterer = value;
      return this;
    }

    public ResourceReference getAuthor() { 
      return this.author;
    }

    public Message setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    public ResourceReference getReceiver() { 
      return this.receiver;
    }

    public Message setReceiver(ResourceReference value) { 
      this.receiver = value;
      return this;
    }

    public ResourceReference getResponsible() { 
      return this.responsible;
    }

    public Message setResponsible(ResourceReference value) { 
      this.responsible = value;
      return this;
    }

    public CodeableConcept getReason() { 
      return this.reason;
    }

    public Message setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    public List<ResourceReference> getData() { 
      return this.data;
    }

    // syntactic sugar
    public ResourceReference addData() { 
      ResourceReference t = new ResourceReference();
      this.data.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "id", "The identifier of this message.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("timestamp", "instant", "The time that the message was sent.", 0, java.lang.Integer.MAX_VALUE, timestamp));
        childrenList.add(new Property("event", "Coding", "Code that identifies the event this message represents and connects it with it's definition. Events defined as part of the FHIR specification have the system value 'http://hl7.org/fhir/message-type'.", 0, java.lang.Integer.MAX_VALUE, event));
        childrenList.add(new Property("response", "", "Information about the message that this message is a response to.  Only present if this message is a response.", 0, java.lang.Integer.MAX_VALUE, response));
        childrenList.add(new Property("source", "", "The source application from which this message originated.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("destination", "", "The destination application which the message is intended for.", 0, java.lang.Integer.MAX_VALUE, destination));
        childrenList.add(new Property("enterer", "Resource(Practitioner)", "The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.", 0, java.lang.Integer.MAX_VALUE, enterer));
        childrenList.add(new Property("author", "Resource(Practitioner)", "The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the Message. Can provide other authors in extensions.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("receiver", "Resource(Practitioner|Organization)", "Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.", 0, java.lang.Integer.MAX_VALUE, receiver));
        childrenList.add(new Property("responsible", "Resource(Practitioner|Organization)", "The person or organization that accepts overall responsibility for the contents of the Message. The implication is that the message event happened under the policies of the responsible party.", 0, java.lang.Integer.MAX_VALUE, responsible));
        childrenList.add(new Property("reason", "CodeableConcept", "Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("data", "Resource(Any)", "The actual data of the message - a reference to the root/focus class of the event.", 0, java.lang.Integer.MAX_VALUE, data));
      }

      public Message copy() {
        Message dst = new Message();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.timestamp = timestamp == null ? null : timestamp.copy();
        dst.event = event == null ? null : event.copy();
        dst.response = response == null ? null : response.copy(dst);
        dst.source = source == null ? null : source.copy(dst);
        dst.destination = new ArrayList<MessageDestinationComponent>();
        for (MessageDestinationComponent i : destination)
          dst.destination.add(i.copy(dst));
        dst.enterer = enterer == null ? null : enterer.copy();
        dst.author = author == null ? null : author.copy();
        dst.receiver = receiver == null ? null : receiver.copy();
        dst.responsible = responsible == null ? null : responsible.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.data = new ArrayList<ResourceReference>();
        for (ResourceReference i : data)
          dst.data.add(i.copy());
        return dst;
      }

      protected Message typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Message;
   }


}

