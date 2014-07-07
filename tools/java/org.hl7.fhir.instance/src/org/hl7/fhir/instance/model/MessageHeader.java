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
 * The header for a message exchange that is either requesting or responding to an action.  The resource(s) that are the subject of the action as well as other Information related to the action are typically transmitted in a bundle in which the MessageHeader resource instance is the first resource in the bundle.
 */
public class MessageHeader extends Resource {

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

    public static class MessageHeaderResponseComponent extends BackboneElement {
        /**
         * The id of the message that this message is a response to.
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

        /**
         * The actual object that is the target of the reference (Full details of any issues found in the message.)
         */
        protected OperationOutcome detailsTarget;

        private static final long serialVersionUID = 902866821L;

      public MessageHeaderResponseComponent() {
        super();
      }

      public MessageHeaderResponseComponent(Id identifier, Enumeration<ResponseCode> code) {
        super();
        this.identifier = identifier;
        this.code = code;
      }

        /**
         * @return {@link #identifier} (The id of the message that this message is a response to.)
         */
        public Id getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (The id of the message that this message is a response to.)
         */
        public MessageHeaderResponseComponent setIdentifier(Id value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return The id of the message that this message is a response to.
         */
        public String getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        /**
         * @param value The id of the message that this message is a response to.
         */
        public MessageHeaderResponseComponent setIdentifierSimple(String value) { 
            if (this.identifier == null)
              this.identifier = new Id();
            this.identifier.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.)
         */
        public Enumeration<ResponseCode> getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.)
         */
        public MessageHeaderResponseComponent setCode(Enumeration<ResponseCode> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.
         */
        public ResponseCode getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.
         */
        public MessageHeaderResponseComponent setCodeSimple(ResponseCode value) { 
            if (this.code == null)
              this.code = new Enumeration<ResponseCode>();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #details} (Full details of any issues found in the message.)
         */
        public ResourceReference getDetails() { 
          return this.details;
        }

        /**
         * @param value {@link #details} (Full details of any issues found in the message.)
         */
        public MessageHeaderResponseComponent setDetails(ResourceReference value) { 
          this.details = value;
          return this;
        }

        /**
         * @return {@link #details} (The actual object that is the target of the reference. Full details of any issues found in the message.)
         */
        public OperationOutcome getDetailsTarget() { 
          return this.detailsTarget;
        }

        /**
         * @param value {@link #details} (The actual object that is the target of the reference. Full details of any issues found in the message.)
         */
        public MessageHeaderResponseComponent setDetailsTarget(OperationOutcome value) { 
          this.detailsTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "id", "The id of the message that this message is a response to.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("code", "code", "Code that identifies the type of response to the message - whether it was successful or not, and whether it should be resent or not.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("details", "Resource(OperationOutcome)", "Full details of any issues found in the message.", 0, java.lang.Integer.MAX_VALUE, details));
        }

      public MessageHeaderResponseComponent copy() {
        MessageHeaderResponseComponent dst = new MessageHeaderResponseComponent();
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

        private static final long serialVersionUID = -186111349L;

      public MessageSourceComponent() {
        super();
      }

      public MessageSourceComponent(String_ software, Uri endpoint) {
        super();
        this.software = software;
        this.endpoint = endpoint;
      }

        /**
         * @return {@link #name} (Human-readable name for the target system.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Human-readable name for the target system.)
         */
        public MessageSourceComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Human-readable name for the target system.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Human-readable name for the target system.
         */
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

        /**
         * @return {@link #software} (May include configuration or other information useful in debugging.)
         */
        public String_ getSoftware() { 
          return this.software;
        }

        /**
         * @param value {@link #software} (May include configuration or other information useful in debugging.)
         */
        public MessageSourceComponent setSoftware(String_ value) { 
          this.software = value;
          return this;
        }

        /**
         * @return May include configuration or other information useful in debugging.
         */
        public String getSoftwareSimple() { 
          return this.software == null ? null : this.software.getValue();
        }

        /**
         * @param value May include configuration or other information useful in debugging.
         */
        public MessageSourceComponent setSoftwareSimple(String value) { 
            if (this.software == null)
              this.software = new String_();
            this.software.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (Can convey versions of multiple systems in situations where a message passes through multiple hands.)
         */
        public String_ getVersion() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (Can convey versions of multiple systems in situations where a message passes through multiple hands.)
         */
        public MessageSourceComponent setVersion(String_ value) { 
          this.version = value;
          return this;
        }

        /**
         * @return Can convey versions of multiple systems in situations where a message passes through multiple hands.
         */
        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value Can convey versions of multiple systems in situations where a message passes through multiple hands.
         */
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

        /**
         * @return {@link #contact} (An e-mail, phone, website or other contact point to use to resolve issues with message communications.)
         */
        public Contact getContact() { 
          return this.contact;
        }

        /**
         * @param value {@link #contact} (An e-mail, phone, website or other contact point to use to resolve issues with message communications.)
         */
        public MessageSourceComponent setContact(Contact value) { 
          this.contact = value;
          return this;
        }

        /**
         * @return {@link #endpoint} (Identifies the routing target to send acknowledgements to.)
         */
        public Uri getEndpoint() { 
          return this.endpoint;
        }

        /**
         * @param value {@link #endpoint} (Identifies the routing target to send acknowledgements to.)
         */
        public MessageSourceComponent setEndpoint(Uri value) { 
          this.endpoint = value;
          return this;
        }

        /**
         * @return Identifies the routing target to send acknowledgements to.
         */
        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        /**
         * @param value Identifies the routing target to send acknowledgements to.
         */
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

      public MessageSourceComponent copy() {
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
         * The actual object that is the target of the reference (Identifies the target end system in situations where the initial message transmission is to an intermediary system.)
         */
        protected Device targetTarget;

        /**
         * Indicates where the message should be routed to.
         */
        protected Uri endpoint;

        private static final long serialVersionUID = 346560150L;

      public MessageDestinationComponent() {
        super();
      }

      public MessageDestinationComponent(Uri endpoint) {
        super();
        this.endpoint = endpoint;
      }

        /**
         * @return {@link #name} (Human-readable name for the source system.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Human-readable name for the source system.)
         */
        public MessageDestinationComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Human-readable name for the source system.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Human-readable name for the source system.
         */
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

        /**
         * @return {@link #target} (Identifies the target end system in situations where the initial message transmission is to an intermediary system.)
         */
        public ResourceReference getTarget() { 
          return this.target;
        }

        /**
         * @param value {@link #target} (Identifies the target end system in situations where the initial message transmission is to an intermediary system.)
         */
        public MessageDestinationComponent setTarget(ResourceReference value) { 
          this.target = value;
          return this;
        }

        /**
         * @return {@link #target} (The actual object that is the target of the reference. Identifies the target end system in situations where the initial message transmission is to an intermediary system.)
         */
        public Device getTargetTarget() { 
          return this.targetTarget;
        }

        /**
         * @param value {@link #target} (The actual object that is the target of the reference. Identifies the target end system in situations where the initial message transmission is to an intermediary system.)
         */
        public MessageDestinationComponent setTargetTarget(Device value) { 
          this.targetTarget = value;
          return this;
        }

        /**
         * @return {@link #endpoint} (Indicates where the message should be routed to.)
         */
        public Uri getEndpoint() { 
          return this.endpoint;
        }

        /**
         * @param value {@link #endpoint} (Indicates where the message should be routed to.)
         */
        public MessageDestinationComponent setEndpoint(Uri value) { 
          this.endpoint = value;
          return this;
        }

        /**
         * @return Indicates where the message should be routed to.
         */
        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        /**
         * @param value Indicates where the message should be routed to.
         */
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

      public MessageDestinationComponent copy() {
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
    protected MessageHeaderResponseComponent response;

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
     * The actual object that is the target of the reference (The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.)
     */
    protected Practitioner entererTarget;

    /**
     * The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.
     */
    protected ResourceReference author;

    /**
     * The actual object that is the target of the reference (The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.)
     */
    protected Practitioner authorTarget;

    /**
     * Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.
     */
    protected ResourceReference receiver;

    /**
     * The actual object that is the target of the reference (Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.)
     */
    protected Resource receiverTarget;

    /**
     * The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.
     */
    protected ResourceReference responsible;

    /**
     * The actual object that is the target of the reference (The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.)
     */
    protected Resource responsibleTarget;

    /**
     * Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.
     */
    protected CodeableConcept reason;

    /**
     * The actual data of the message - a reference to the root/focus class of the event.
     */
    protected List<ResourceReference> data = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (The actual data of the message - a reference to the root/focus class of the event.)
     */
    protected List<Resource> dataTarget = new ArrayList<Resource>();


    private static final long serialVersionUID = -1894921749L;

    public MessageHeader() {
      super();
    }

    public MessageHeader(Id identifier, Instant timestamp, Coding event, MessageSourceComponent source) {
      super();
      this.identifier = identifier;
      this.timestamp = timestamp;
      this.event = event;
      this.source = source;
    }

    /**
     * @return {@link #identifier} (The identifier of this message.)
     */
    public Id getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier of this message.)
     */
    public MessageHeader setIdentifier(Id value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier of this message.
     */
    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier of this message.
     */
    public MessageHeader setIdentifierSimple(String value) { 
        if (this.identifier == null)
          this.identifier = new Id();
        this.identifier.setValue(value);
      return this;
    }

    /**
     * @return {@link #timestamp} (The time that the message was sent.)
     */
    public Instant getTimestamp() { 
      return this.timestamp;
    }

    /**
     * @param value {@link #timestamp} (The time that the message was sent.)
     */
    public MessageHeader setTimestamp(Instant value) { 
      this.timestamp = value;
      return this;
    }

    /**
     * @return The time that the message was sent.
     */
    public DateAndTime getTimestampSimple() { 
      return this.timestamp == null ? null : this.timestamp.getValue();
    }

    /**
     * @param value The time that the message was sent.
     */
    public MessageHeader setTimestampSimple(DateAndTime value) { 
        if (this.timestamp == null)
          this.timestamp = new Instant();
        this.timestamp.setValue(value);
      return this;
    }

    /**
     * @return {@link #event} (Code that identifies the event this message represents and connects it with it's definition. Events defined as part of the FHIR specification have the system value "http://hl7.org/fhir/message-type".)
     */
    public Coding getEvent() { 
      return this.event;
    }

    /**
     * @param value {@link #event} (Code that identifies the event this message represents and connects it with it's definition. Events defined as part of the FHIR specification have the system value "http://hl7.org/fhir/message-type".)
     */
    public MessageHeader setEvent(Coding value) { 
      this.event = value;
      return this;
    }

    /**
     * @return {@link #response} (Information about the message that this message is a response to.  Only present if this message is a response.)
     */
    public MessageHeaderResponseComponent getResponse() { 
      return this.response;
    }

    /**
     * @param value {@link #response} (Information about the message that this message is a response to.  Only present if this message is a response.)
     */
    public MessageHeader setResponse(MessageHeaderResponseComponent value) { 
      this.response = value;
      return this;
    }

    /**
     * @return {@link #source} (The source application from which this message originated.)
     */
    public MessageSourceComponent getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (The source application from which this message originated.)
     */
    public MessageHeader setSource(MessageSourceComponent value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #destination} (The destination application which the message is intended for.)
     */
    public List<MessageDestinationComponent> getDestination() { 
      return this.destination;
    }

    // syntactic sugar
    /**
     * @return {@link #destination} (The destination application which the message is intended for.)
     */
    public MessageDestinationComponent addDestination() { 
      MessageDestinationComponent t = new MessageDestinationComponent();
      this.destination.add(t);
      return t;
    }

    /**
     * @return {@link #enterer} (The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.)
     */
    public ResourceReference getEnterer() { 
      return this.enterer;
    }

    /**
     * @param value {@link #enterer} (The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.)
     */
    public MessageHeader setEnterer(ResourceReference value) { 
      this.enterer = value;
      return this;
    }

    /**
     * @return {@link #enterer} (The actual object that is the target of the reference. The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.)
     */
    public Practitioner getEntererTarget() { 
      return this.entererTarget;
    }

    /**
     * @param value {@link #enterer} (The actual object that is the target of the reference. The person or device that performed the data entry leading to this message. Where there is more than one candidate, pick the most proximal to the message. Can provide other enterers in extensions.)
     */
    public MessageHeader setEntererTarget(Practitioner value) { 
      this.entererTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.)
     */
    public ResourceReference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.)
     */
    public MessageHeader setAuthor(ResourceReference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} (The actual object that is the target of the reference. The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.)
     */
    public Practitioner getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} (The actual object that is the target of the reference. The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.)
     */
    public MessageHeader setAuthorTarget(Practitioner value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #receiver} (Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.)
     */
    public ResourceReference getReceiver() { 
      return this.receiver;
    }

    /**
     * @param value {@link #receiver} (Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.)
     */
    public MessageHeader setReceiver(ResourceReference value) { 
      this.receiver = value;
      return this;
    }

    /**
     * @return {@link #receiver} (The actual object that is the target of the reference. Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.)
     */
    public Resource getReceiverTarget() { 
      return this.receiverTarget;
    }

    /**
     * @param value {@link #receiver} (The actual object that is the target of the reference. Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.)
     */
    public MessageHeader setReceiverTarget(Resource value) { 
      this.receiverTarget = value;
      return this;
    }

    /**
     * @return {@link #responsible} (The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.)
     */
    public ResourceReference getResponsible() { 
      return this.responsible;
    }

    /**
     * @param value {@link #responsible} (The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.)
     */
    public MessageHeader setResponsible(ResourceReference value) { 
      this.responsible = value;
      return this;
    }

    /**
     * @return {@link #responsible} (The actual object that is the target of the reference. The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.)
     */
    public Resource getResponsibleTarget() { 
      return this.responsibleTarget;
    }

    /**
     * @param value {@link #responsible} (The actual object that is the target of the reference. The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.)
     */
    public MessageHeader setResponsibleTarget(Resource value) { 
      this.responsibleTarget = value;
      return this;
    }

    /**
     * @return {@link #reason} (Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.)
     */
    public CodeableConcept getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.)
     */
    public MessageHeader setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #data} (The actual data of the message - a reference to the root/focus class of the event.)
     */
    public List<ResourceReference> getData() { 
      return this.data;
    }

    // syntactic sugar
    /**
     * @return {@link #data} (The actual data of the message - a reference to the root/focus class of the event.)
     */
    public ResourceReference addData() { 
      ResourceReference t = new ResourceReference();
      this.data.add(t);
      return t;
    }

    /**
     * @return {@link #data} (The actual objects that are the target of the reference. The actual data of the message - a reference to the root/focus class of the event.)
     */
    public List<Resource> getDataTarget() { 
      return this.dataTarget;
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
        childrenList.add(new Property("author", "Resource(Practitioner)", "The logical author of the message - the person or device that decided the described event should happen. Where there is more than one candidate, pick the most proximal to the MessageHeader. Can provide other authors in extensions.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("receiver", "Resource(Practitioner|Organization)", "Allows data conveyed by a message to be addressed to a particular person or department when routing to a specific application isn't sufficient.", 0, java.lang.Integer.MAX_VALUE, receiver));
        childrenList.add(new Property("responsible", "Resource(Practitioner|Organization)", "The person or organization that accepts overall responsibility for the contents of the message. The implication is that the message event happened under the policies of the responsible party.", 0, java.lang.Integer.MAX_VALUE, responsible));
        childrenList.add(new Property("reason", "CodeableConcept", "Coded indication of the cause for the event - indicates  a reason for the occurance of the event that is a focus of this message.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("data", "Resource(Any)", "The actual data of the message - a reference to the root/focus class of the event.", 0, java.lang.Integer.MAX_VALUE, data));
      }

      public MessageHeader copy() {
        MessageHeader dst = new MessageHeader();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.timestamp = timestamp == null ? null : timestamp.copy();
        dst.event = event == null ? null : event.copy();
        dst.response = response == null ? null : response.copy();
        dst.source = source == null ? null : source.copy();
        dst.destination = new ArrayList<MessageDestinationComponent>();
        for (MessageDestinationComponent i : destination)
          dst.destination.add(i.copy());
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

      protected MessageHeader typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MessageHeader;
   }


}

