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
 * A conformance statement is a set of requirements for a desired implementation or a description of how a target application fulfills those requirements in a particular implementation.
 */
public class Conformance extends Resource {

    public enum ConformanceStatementStatus {
        draft, // This conformance statement is still under development.
        active, // This conformance statement is ready for use in production systems.
        retired, // This conformance statement has been withdrawn or superceded and should no longer be used.
        Null; // added to help the parsers
        public static ConformanceStatementStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("active".equals(codeString))
          return active;
        if ("retired".equals(codeString))
          return retired;
        throw new Exception("Unknown ConformanceStatementStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case draft: return "draft";
            case active: return "active";
            case retired: return "retired";
            default: return "?";
          }
        }
    }

  public static class ConformanceStatementStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ConformanceStatementStatus.draft;
        if ("active".equals(codeString))
          return ConformanceStatementStatus.active;
        if ("retired".equals(codeString))
          return ConformanceStatementStatus.retired;
        throw new Exception("Unknown ConformanceStatementStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConformanceStatementStatus.draft)
        return "draft";
      if (code == ConformanceStatementStatus.active)
        return "active";
      if (code == ConformanceStatementStatus.retired)
        return "retired";
      return "?";
      }
    }

    public enum RestfulConformanceMode {
        client, // The application acts as a server for this resource.
        server, // The application acts as a client for this resource.
        Null; // added to help the parsers
        public static RestfulConformanceMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("client".equals(codeString))
          return client;
        if ("server".equals(codeString))
          return server;
        throw new Exception("Unknown RestfulConformanceMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case client: return "client";
            case server: return "server";
            default: return "?";
          }
        }
    }

  public static class RestfulConformanceModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("client".equals(codeString))
          return RestfulConformanceMode.client;
        if ("server".equals(codeString))
          return RestfulConformanceMode.server;
        throw new Exception("Unknown RestfulConformanceMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == RestfulConformanceMode.client)
        return "client";
      if (code == RestfulConformanceMode.server)
        return "server";
      return "?";
      }
    }

    public enum TypeRestfulOperation {
        read, // 
        vread, // 
        update, // 
        delete, // 
        historyinstance, // 
        validate, // 
        historytype, // 
        create, // 
        searchtype, // 
        Null; // added to help the parsers
        public static TypeRestfulOperation fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("read".equals(codeString))
          return read;
        if ("vread".equals(codeString))
          return vread;
        if ("update".equals(codeString))
          return update;
        if ("delete".equals(codeString))
          return delete;
        if ("history-instance".equals(codeString))
          return historyinstance;
        if ("validate".equals(codeString))
          return validate;
        if ("history-type".equals(codeString))
          return historytype;
        if ("create".equals(codeString))
          return create;
        if ("search-type".equals(codeString))
          return searchtype;
        throw new Exception("Unknown TypeRestfulOperation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case read: return "read";
            case vread: return "vread";
            case update: return "update";
            case delete: return "delete";
            case historyinstance: return "history-instance";
            case validate: return "validate";
            case historytype: return "history-type";
            case create: return "create";
            case searchtype: return "search-type";
            default: return "?";
          }
        }
    }

  public static class TypeRestfulOperationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("read".equals(codeString))
          return TypeRestfulOperation.read;
        if ("vread".equals(codeString))
          return TypeRestfulOperation.vread;
        if ("update".equals(codeString))
          return TypeRestfulOperation.update;
        if ("delete".equals(codeString))
          return TypeRestfulOperation.delete;
        if ("history-instance".equals(codeString))
          return TypeRestfulOperation.historyinstance;
        if ("validate".equals(codeString))
          return TypeRestfulOperation.validate;
        if ("history-type".equals(codeString))
          return TypeRestfulOperation.historytype;
        if ("create".equals(codeString))
          return TypeRestfulOperation.create;
        if ("search-type".equals(codeString))
          return TypeRestfulOperation.searchtype;
        throw new Exception("Unknown TypeRestfulOperation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == TypeRestfulOperation.read)
        return "read";
      if (code == TypeRestfulOperation.vread)
        return "vread";
      if (code == TypeRestfulOperation.update)
        return "update";
      if (code == TypeRestfulOperation.delete)
        return "delete";
      if (code == TypeRestfulOperation.historyinstance)
        return "history-instance";
      if (code == TypeRestfulOperation.validate)
        return "validate";
      if (code == TypeRestfulOperation.historytype)
        return "history-type";
      if (code == TypeRestfulOperation.create)
        return "create";
      if (code == TypeRestfulOperation.searchtype)
        return "search-type";
      return "?";
      }
    }

    public enum SearchParamType {
        number, // Search parameter SHALL be a number (a whole number, or a decimal).
        date, // Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.
        string, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.
        token, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "|", depending on the modifier used.
        reference, // A reference to another resource.
        composite, // A composite search parameter that combines a search on two values together.
        quantity, // A search parameter that searches on a quantity.
        Null; // added to help the parsers
        public static SearchParamType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return number;
        if ("date".equals(codeString))
          return date;
        if ("string".equals(codeString))
          return string;
        if ("token".equals(codeString))
          return token;
        if ("reference".equals(codeString))
          return reference;
        if ("composite".equals(codeString))
          return composite;
        if ("quantity".equals(codeString))
          return quantity;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case number: return "number";
            case date: return "date";
            case string: return "string";
            case token: return "token";
            case reference: return "reference";
            case composite: return "composite";
            case quantity: return "quantity";
            default: return "?";
          }
        }
    }

  public static class SearchParamTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return SearchParamType.number;
        if ("date".equals(codeString))
          return SearchParamType.date;
        if ("string".equals(codeString))
          return SearchParamType.string;
        if ("token".equals(codeString))
          return SearchParamType.token;
        if ("reference".equals(codeString))
          return SearchParamType.reference;
        if ("composite".equals(codeString))
          return SearchParamType.composite;
        if ("quantity".equals(codeString))
          return SearchParamType.quantity;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SearchParamType.number)
        return "number";
      if (code == SearchParamType.date)
        return "date";
      if (code == SearchParamType.string)
        return "string";
      if (code == SearchParamType.token)
        return "token";
      if (code == SearchParamType.reference)
        return "reference";
      if (code == SearchParamType.composite)
        return "composite";
      if (code == SearchParamType.quantity)
        return "quantity";
      return "?";
      }
    }

    public enum SystemRestfulOperation {
        transaction, // 
        searchsystem, // 
        historysystem, // 
        Null; // added to help the parsers
        public static SystemRestfulOperation fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("transaction".equals(codeString))
          return transaction;
        if ("search-system".equals(codeString))
          return searchsystem;
        if ("history-system".equals(codeString))
          return historysystem;
        throw new Exception("Unknown SystemRestfulOperation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case transaction: return "transaction";
            case searchsystem: return "search-system";
            case historysystem: return "history-system";
            default: return "?";
          }
        }
    }

  public static class SystemRestfulOperationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("transaction".equals(codeString))
          return SystemRestfulOperation.transaction;
        if ("search-system".equals(codeString))
          return SystemRestfulOperation.searchsystem;
        if ("history-system".equals(codeString))
          return SystemRestfulOperation.historysystem;
        throw new Exception("Unknown SystemRestfulOperation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SystemRestfulOperation.transaction)
        return "transaction";
      if (code == SystemRestfulOperation.searchsystem)
        return "search-system";
      if (code == SystemRestfulOperation.historysystem)
        return "history-system";
      return "?";
      }
    }

    public enum MessageSignificanceCategory {
        consequence, // The message represents/requests a change that should not be processed more than once. E.g. Making a booking for an appointment.
        currency, // The message represents a response to query for current information. Retrospective processing is wrong and/or wasteful.
        notification, // The content is not necessarily intended to be current, and it can be reprocessed, though there may be version issues created by processing old notifications.
        Null; // added to help the parsers
        public static MessageSignificanceCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Consequence".equals(codeString))
          return consequence;
        if ("Currency".equals(codeString))
          return currency;
        if ("Notification".equals(codeString))
          return notification;
        throw new Exception("Unknown MessageSignificanceCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case consequence: return "Consequence";
            case currency: return "Currency";
            case notification: return "Notification";
            default: return "?";
          }
        }
    }

  public static class MessageSignificanceCategoryEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Consequence".equals(codeString))
          return MessageSignificanceCategory.consequence;
        if ("Currency".equals(codeString))
          return MessageSignificanceCategory.currency;
        if ("Notification".equals(codeString))
          return MessageSignificanceCategory.notification;
        throw new Exception("Unknown MessageSignificanceCategory code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MessageSignificanceCategory.consequence)
        return "Consequence";
      if (code == MessageSignificanceCategory.currency)
        return "Currency";
      if (code == MessageSignificanceCategory.notification)
        return "Notification";
      return "?";
      }
    }

    public enum MessageConformanceEventMode {
        sender, // The application sends requests and receives responses.
        receiver, // The application receives requests and sends responses.
        Null; // added to help the parsers
        public static MessageConformanceEventMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("sender".equals(codeString))
          return sender;
        if ("receiver".equals(codeString))
          return receiver;
        throw new Exception("Unknown MessageConformanceEventMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case sender: return "sender";
            case receiver: return "receiver";
            default: return "?";
          }
        }
    }

  public static class MessageConformanceEventModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("sender".equals(codeString))
          return MessageConformanceEventMode.sender;
        if ("receiver".equals(codeString))
          return MessageConformanceEventMode.receiver;
        throw new Exception("Unknown MessageConformanceEventMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MessageConformanceEventMode.sender)
        return "sender";
      if (code == MessageConformanceEventMode.receiver)
        return "receiver";
      return "?";
      }
    }

    public enum DocumentMode {
        producer, // The application produces documents of the specified type.
        consumer, // The application consumes documents of the specified type.
        Null; // added to help the parsers
        public static DocumentMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("producer".equals(codeString))
          return producer;
        if ("consumer".equals(codeString))
          return consumer;
        throw new Exception("Unknown DocumentMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case producer: return "producer";
            case consumer: return "consumer";
            default: return "?";
          }
        }
    }

  public static class DocumentModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("producer".equals(codeString))
          return DocumentMode.producer;
        if ("consumer".equals(codeString))
          return DocumentMode.consumer;
        throw new Exception("Unknown DocumentMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentMode.producer)
        return "producer";
      if (code == DocumentMode.consumer)
        return "consumer";
      return "?";
      }
    }

    public static class ConformanceSoftwareComponent extends BackboneElement {
        /**
         * Name software is known by.
         */
        protected String_ name;

        /**
         * The version identifier for the software covered by this statement.
         */
        protected String_ version;

        /**
         * Date this version of the software released.
         */
        protected DateTime releaseDate;

        private static final long serialVersionUID = 1288763369L;

      public ConformanceSoftwareComponent() {
        super();
      }

      public ConformanceSoftwareComponent(String_ name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (Name software is known by.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Name software is known by.)
         */
        public ConformanceSoftwareComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Name software is known by.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Name software is known by.
         */
        public ConformanceSoftwareComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version identifier for the software covered by this statement.)
         */
        public String_ getVersion() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version identifier for the software covered by this statement.)
         */
        public ConformanceSoftwareComponent setVersion(String_ value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version identifier for the software covered by this statement.
         */
        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version identifier for the software covered by this statement.
         */
        public ConformanceSoftwareComponent setVersionSimple(String value) { 
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
         * @return {@link #releaseDate} (Date this version of the software released.)
         */
        public DateTime getReleaseDate() { 
          return this.releaseDate;
        }

        /**
         * @param value {@link #releaseDate} (Date this version of the software released.)
         */
        public ConformanceSoftwareComponent setReleaseDate(DateTime value) { 
          this.releaseDate = value;
          return this;
        }

        /**
         * @return Date this version of the software released.
         */
        public DateAndTime getReleaseDateSimple() { 
          return this.releaseDate == null ? null : this.releaseDate.getValue();
        }

        /**
         * @param value Date this version of the software released.
         */
        public ConformanceSoftwareComponent setReleaseDateSimple(DateAndTime value) { 
          if (value == null)
            this.releaseDate = null;
          else {
            if (this.releaseDate == null)
              this.releaseDate = new DateTime();
            this.releaseDate.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "Name software is known by.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("version", "string", "The version identifier for the software covered by this statement.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("releaseDate", "dateTime", "Date this version of the software released.", 0, java.lang.Integer.MAX_VALUE, releaseDate));
        }

      public ConformanceSoftwareComponent copy() {
        ConformanceSoftwareComponent dst = new ConformanceSoftwareComponent();
        dst.name = name == null ? null : name.copy();
        dst.version = version == null ? null : version.copy();
        dst.releaseDate = releaseDate == null ? null : releaseDate.copy();
        return dst;
      }

  }

    public static class ConformanceImplementationComponent extends BackboneElement {
        /**
         * Information about the specific installation that this conformance statement relates to.
         */
        protected String_ description;

        /**
         * A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        protected Uri url;

        private static final long serialVersionUID = 861760175L;

      public ConformanceImplementationComponent() {
        super();
      }

      public ConformanceImplementationComponent(String_ description) {
        super();
        this.description = description;
      }

        /**
         * @return {@link #description} (Information about the specific installation that this conformance statement relates to.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Information about the specific installation that this conformance statement relates to.)
         */
        public ConformanceImplementationComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Information about the specific installation that this conformance statement relates to.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Information about the specific installation that this conformance statement relates to.
         */
        public ConformanceImplementationComponent setDescriptionSimple(String value) { 
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.)
         */
        public Uri getUrl() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.)
         */
        public ConformanceImplementationComponent setUrl(Uri value) { 
          this.url = value;
          return this;
        }

        /**
         * @return A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        public ConformanceImplementationComponent setUrlSimple(String value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("description", "string", "Information about the specific installation that this conformance statement relates to.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("url", "uri", "A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.", 0, java.lang.Integer.MAX_VALUE, url));
        }

      public ConformanceImplementationComponent copy() {
        ConformanceImplementationComponent dst = new ConformanceImplementationComponent();
        dst.description = description == null ? null : description.copy();
        dst.url = url == null ? null : url.copy();
        return dst;
      }

  }

    public static class ConformanceRestComponent extends BackboneElement {
        /**
         * Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        protected Enumeration<RestfulConformanceMode> mode;

        /**
         * Information about the system's restful capabilities that apply across all applications, such as security.
         */
        protected String_ documentation;

        /**
         * Information about security of implementation.
         */
        protected ConformanceRestSecurityComponent security;

        /**
         * A specification of the restful capabilities of the solution for a specific resource type.
         */
        protected List<ConformanceRestResourceComponent> resource = new ArrayList<ConformanceRestResourceComponent>();

        /**
         * A specification of restful operations supported by the system.
         */
        protected List<ConformanceRestOperationComponent> operation = new ArrayList<ConformanceRestOperationComponent>();

        /**
         * Definition of a named query and its parameters and their meaning.
         */
        protected List<ConformanceRestQueryComponent> query = new ArrayList<ConformanceRestQueryComponent>();

        /**
         * A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.
         */
        protected List<Uri> documentMailbox = new ArrayList<Uri>();

        private static final long serialVersionUID = 2080191569L;

      public ConformanceRestComponent() {
        super();
      }

      public ConformanceRestComponent(Enumeration<RestfulConformanceMode> mode) {
        super();
        this.mode = mode;
      }

        /**
         * @return {@link #mode} (Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.)
         */
        public Enumeration<RestfulConformanceMode> getMode() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.)
         */
        public ConformanceRestComponent setMode(Enumeration<RestfulConformanceMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        public RestfulConformanceMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        public ConformanceRestComponent setModeSimple(RestfulConformanceMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<RestfulConformanceMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Information about the system's restful capabilities that apply across all applications, such as security.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Information about the system's restful capabilities that apply across all applications, such as security.)
         */
        public ConformanceRestComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Information about the system's restful capabilities that apply across all applications, such as security.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Information about the system's restful capabilities that apply across all applications, such as security.
         */
        public ConformanceRestComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #security} (Information about security of implementation.)
         */
        public ConformanceRestSecurityComponent getSecurity() { 
          return this.security;
        }

        /**
         * @param value {@link #security} (Information about security of implementation.)
         */
        public ConformanceRestComponent setSecurity(ConformanceRestSecurityComponent value) { 
          this.security = value;
          return this;
        }

        /**
         * @return {@link #resource} (A specification of the restful capabilities of the solution for a specific resource type.)
         */
        public List<ConformanceRestResourceComponent> getResource() { 
          return this.resource;
        }

    // syntactic sugar
        /**
         * @return {@link #resource} (A specification of the restful capabilities of the solution for a specific resource type.)
         */
        public ConformanceRestResourceComponent addResource() { 
          ConformanceRestResourceComponent t = new ConformanceRestResourceComponent();
          this.resource.add(t);
          return t;
        }

        /**
         * @return {@link #operation} (A specification of restful operations supported by the system.)
         */
        public List<ConformanceRestOperationComponent> getOperation() { 
          return this.operation;
        }

    // syntactic sugar
        /**
         * @return {@link #operation} (A specification of restful operations supported by the system.)
         */
        public ConformanceRestOperationComponent addOperation() { 
          ConformanceRestOperationComponent t = new ConformanceRestOperationComponent();
          this.operation.add(t);
          return t;
        }

        /**
         * @return {@link #query} (Definition of a named query and its parameters and their meaning.)
         */
        public List<ConformanceRestQueryComponent> getQuery() { 
          return this.query;
        }

    // syntactic sugar
        /**
         * @return {@link #query} (Definition of a named query and its parameters and their meaning.)
         */
        public ConformanceRestQueryComponent addQuery() { 
          ConformanceRestQueryComponent t = new ConformanceRestQueryComponent();
          this.query.add(t);
          return t;
        }

        /**
         * @return {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public List<Uri> getDocumentMailbox() { 
          return this.documentMailbox;
        }

    // syntactic sugar
        /**
         * @return {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public Uri addDocumentMailbox() { 
          Uri t = new Uri();
          this.documentMailbox.add(t);
          return t;
        }

        /**
         * @param value {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public Uri addDocumentMailboxSimple(String value) { 
          Uri t = new Uri();
          t.setValue(value);
          this.documentMailbox.add(t);
          return t;
        }

        /**
         * @param value {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public boolean hasDocumentMailboxSimple(String value) { 
          for (Uri v : this.documentMailbox)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("documentation", "string", "Information about the system's restful capabilities that apply across all applications, such as security.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("security", "", "Information about security of implementation.", 0, java.lang.Integer.MAX_VALUE, security));
          childrenList.add(new Property("resource", "", "A specification of the restful capabilities of the solution for a specific resource type.", 0, java.lang.Integer.MAX_VALUE, resource));
          childrenList.add(new Property("operation", "", "A specification of restful operations supported by the system.", 0, java.lang.Integer.MAX_VALUE, operation));
          childrenList.add(new Property("query", "", "Definition of a named query and its parameters and their meaning.", 0, java.lang.Integer.MAX_VALUE, query));
          childrenList.add(new Property("documentMailbox", "uri", "A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier 'http://hl7.org/fhir/documents/mailbox'. Other specifications can declare their own identifier for this purpose.", 0, java.lang.Integer.MAX_VALUE, documentMailbox));
        }

      public ConformanceRestComponent copy() {
        ConformanceRestComponent dst = new ConformanceRestComponent();
        dst.mode = mode == null ? null : mode.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.security = security == null ? null : security.copy();
        dst.resource = new ArrayList<ConformanceRestResourceComponent>();
        for (ConformanceRestResourceComponent i : resource)
          dst.resource.add(i.copy());
        dst.operation = new ArrayList<ConformanceRestOperationComponent>();
        for (ConformanceRestOperationComponent i : operation)
          dst.operation.add(i.copy());
        dst.query = new ArrayList<ConformanceRestQueryComponent>();
        for (ConformanceRestQueryComponent i : query)
          dst.query.add(i.copy());
        dst.documentMailbox = new ArrayList<Uri>();
        for (Uri i : documentMailbox)
          dst.documentMailbox.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceRestSecurityComponent extends BackboneElement {
        /**
         * Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.
         */
        protected Boolean cors;

        /**
         * Types of security services are supported/required by the system.
         */
        protected List<CodeableConcept> service = new ArrayList<CodeableConcept>();

        /**
         * General description of how security works.
         */
        protected String_ description;

        /**
         * Certificates associated with security profiles.
         */
        protected List<ConformanceRestSecurityCertificateComponent> certificate = new ArrayList<ConformanceRestSecurityCertificateComponent>();

        private static final long serialVersionUID = -1683331005L;

      public ConformanceRestSecurityComponent() {
        super();
      }

        /**
         * @return {@link #cors} (Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.)
         */
        public Boolean getCors() { 
          return this.cors;
        }

        /**
         * @param value {@link #cors} (Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.)
         */
        public ConformanceRestSecurityComponent setCors(Boolean value) { 
          this.cors = value;
          return this;
        }

        /**
         * @return Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.
         */
        public boolean getCorsSimple() { 
          return this.cors == null ? false : this.cors.getValue();
        }

        /**
         * @param value Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.
         */
        public ConformanceRestSecurityComponent setCorsSimple(boolean value) { 
          if (value == false)
            this.cors = null;
          else {
            if (this.cors == null)
              this.cors = new Boolean();
            this.cors.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #service} (Types of security services are supported/required by the system.)
         */
        public List<CodeableConcept> getService() { 
          return this.service;
        }

    // syntactic sugar
        /**
         * @return {@link #service} (Types of security services are supported/required by the system.)
         */
        public CodeableConcept addService() { 
          CodeableConcept t = new CodeableConcept();
          this.service.add(t);
          return t;
        }

        /**
         * @return {@link #description} (General description of how security works.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (General description of how security works.)
         */
        public ConformanceRestSecurityComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return General description of how security works.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value General description of how security works.
         */
        public ConformanceRestSecurityComponent setDescriptionSimple(String value) { 
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
         * @return {@link #certificate} (Certificates associated with security profiles.)
         */
        public List<ConformanceRestSecurityCertificateComponent> getCertificate() { 
          return this.certificate;
        }

    // syntactic sugar
        /**
         * @return {@link #certificate} (Certificates associated with security profiles.)
         */
        public ConformanceRestSecurityCertificateComponent addCertificate() { 
          ConformanceRestSecurityCertificateComponent t = new ConformanceRestSecurityCertificateComponent();
          this.certificate.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("cors", "boolean", "Server adds CORS headers when responding to requests - this enables javascript applications to yuse the server.", 0, java.lang.Integer.MAX_VALUE, cors));
          childrenList.add(new Property("service", "CodeableConcept", "Types of security services are supported/required by the system.", 0, java.lang.Integer.MAX_VALUE, service));
          childrenList.add(new Property("description", "string", "General description of how security works.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("certificate", "", "Certificates associated with security profiles.", 0, java.lang.Integer.MAX_VALUE, certificate));
        }

      public ConformanceRestSecurityComponent copy() {
        ConformanceRestSecurityComponent dst = new ConformanceRestSecurityComponent();
        dst.cors = cors == null ? null : cors.copy();
        dst.service = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : service)
          dst.service.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.certificate = new ArrayList<ConformanceRestSecurityCertificateComponent>();
        for (ConformanceRestSecurityCertificateComponent i : certificate)
          dst.certificate.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceRestSecurityCertificateComponent extends BackboneElement {
        /**
         * Mime type for certificate.
         */
        protected Code type;

        /**
         * Actual certificate.
         */
        protected Base64Binary blob;

        private static final long serialVersionUID = -1385672262L;

      public ConformanceRestSecurityCertificateComponent() {
        super();
      }

        /**
         * @return {@link #type} (Mime type for certificate.)
         */
        public Code getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Mime type for certificate.)
         */
        public ConformanceRestSecurityCertificateComponent setType(Code value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Mime type for certificate.
         */
        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Mime type for certificate.
         */
        public ConformanceRestSecurityCertificateComponent setTypeSimple(String value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #blob} (Actual certificate.)
         */
        public Base64Binary getBlob() { 
          return this.blob;
        }

        /**
         * @param value {@link #blob} (Actual certificate.)
         */
        public ConformanceRestSecurityCertificateComponent setBlob(Base64Binary value) { 
          this.blob = value;
          return this;
        }

        /**
         * @return Actual certificate.
         */
        public byte[] getBlobSimple() { 
          return this.blob == null ? null : this.blob.getValue();
        }

        /**
         * @param value Actual certificate.
         */
        public ConformanceRestSecurityCertificateComponent setBlobSimple(byte[] value) { 
          if (value == null)
            this.blob = null;
          else {
            if (this.blob == null)
              this.blob = new Base64Binary();
            this.blob.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "Mime type for certificate.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("blob", "base64Binary", "Actual certificate.", 0, java.lang.Integer.MAX_VALUE, blob));
        }

      public ConformanceRestSecurityCertificateComponent copy() {
        ConformanceRestSecurityCertificateComponent dst = new ConformanceRestSecurityCertificateComponent();
        dst.type = type == null ? null : type.copy();
        dst.blob = blob == null ? null : blob.copy();
        return dst;
      }

  }

    public static class ConformanceRestResourceComponent extends BackboneElement {
        /**
         * A type of resource exposed via the restful interface.
         */
        protected Code type;

        /**
         * A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.
         */
        protected ResourceReference profile;

        /**
         * The actual object that is the target of the reference (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        protected Profile profileTarget;

        /**
         * Identifies a restful operation supported by the solution.
         */
        protected List<ConformanceRestResourceOperationComponent> operation = new ArrayList<ConformanceRestResourceOperationComponent>();

        /**
         * A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        protected Boolean readHistory;

        /**
         * A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        protected Boolean updateCreate;

        /**
         * A list of _include values supported by the server.
         */
        protected List<String_> searchInclude = new ArrayList<String_>();

        /**
         * Additional search parameters for implementations to support and/or make use of.
         */
        protected List<ConformanceRestResourceSearchParamComponent> searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();

        private static final long serialVersionUID = -2082612240L;

      public ConformanceRestResourceComponent() {
        super();
      }

      public ConformanceRestResourceComponent(Code type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (A type of resource exposed via the restful interface.)
         */
        public Code getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A type of resource exposed via the restful interface.)
         */
        public ConformanceRestResourceComponent setType(Code value) { 
          this.type = value;
          return this;
        }

        /**
         * @return A type of resource exposed via the restful interface.
         */
        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value A type of resource exposed via the restful interface.
         */
        public ConformanceRestResourceComponent setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #profile} (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public ResourceReference getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public ConformanceRestResourceComponent setProfile(ResourceReference value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return {@link #profile} (The actual object that is the target of the reference. A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public Profile getProfileTarget() { 
          return this.profileTarget;
        }

        /**
         * @param value {@link #profile} (The actual object that is the target of the reference. A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public ConformanceRestResourceComponent setProfileTarget(Profile value) { 
          this.profileTarget = value;
          return this;
        }

        /**
         * @return {@link #operation} (Identifies a restful operation supported by the solution.)
         */
        public List<ConformanceRestResourceOperationComponent> getOperation() { 
          return this.operation;
        }

    // syntactic sugar
        /**
         * @return {@link #operation} (Identifies a restful operation supported by the solution.)
         */
        public ConformanceRestResourceOperationComponent addOperation() { 
          ConformanceRestResourceOperationComponent t = new ConformanceRestResourceOperationComponent();
          this.operation.add(t);
          return t;
        }

        /**
         * @return {@link #readHistory} (A flag for whether the server is able to return past versions as part of the vRead operation.)
         */
        public Boolean getReadHistory() { 
          return this.readHistory;
        }

        /**
         * @param value {@link #readHistory} (A flag for whether the server is able to return past versions as part of the vRead operation.)
         */
        public ConformanceRestResourceComponent setReadHistory(Boolean value) { 
          this.readHistory = value;
          return this;
        }

        /**
         * @return A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        public boolean getReadHistorySimple() { 
          return this.readHistory == null ? false : this.readHistory.getValue();
        }

        /**
         * @param value A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        public ConformanceRestResourceComponent setReadHistorySimple(boolean value) { 
          if (value == false)
            this.readHistory = null;
          else {
            if (this.readHistory == null)
              this.readHistory = new Boolean();
            this.readHistory.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #updateCreate} (A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.)
         */
        public Boolean getUpdateCreate() { 
          return this.updateCreate;
        }

        /**
         * @param value {@link #updateCreate} (A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.)
         */
        public ConformanceRestResourceComponent setUpdateCreate(Boolean value) { 
          this.updateCreate = value;
          return this;
        }

        /**
         * @return A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        public boolean getUpdateCreateSimple() { 
          return this.updateCreate == null ? false : this.updateCreate.getValue();
        }

        /**
         * @param value A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        public ConformanceRestResourceComponent setUpdateCreateSimple(boolean value) { 
          if (value == false)
            this.updateCreate = null;
          else {
            if (this.updateCreate == null)
              this.updateCreate = new Boolean();
            this.updateCreate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public List<String_> getSearchInclude() { 
          return this.searchInclude;
        }

    // syntactic sugar
        /**
         * @return {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public String_ addSearchInclude() { 
          String_ t = new String_();
          this.searchInclude.add(t);
          return t;
        }

        /**
         * @param value {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public String_ addSearchIncludeSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.searchInclude.add(t);
          return t;
        }

        /**
         * @param value {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public boolean hasSearchIncludeSimple(String value) { 
          for (String_ v : this.searchInclude)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
        public List<ConformanceRestResourceSearchParamComponent> getSearchParam() { 
          return this.searchParam;
        }

    // syntactic sugar
        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
        public ConformanceRestResourceSearchParamComponent addSearchParam() { 
          ConformanceRestResourceSearchParamComponent t = new ConformanceRestResourceSearchParamComponent();
          this.searchParam.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "A type of resource exposed via the restful interface.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("profile", "Resource(Profile)", "A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.", 0, java.lang.Integer.MAX_VALUE, profile));
          childrenList.add(new Property("operation", "", "Identifies a restful operation supported by the solution.", 0, java.lang.Integer.MAX_VALUE, operation));
          childrenList.add(new Property("readHistory", "boolean", "A flag for whether the server is able to return past versions as part of the vRead operation.", 0, java.lang.Integer.MAX_VALUE, readHistory));
          childrenList.add(new Property("updateCreate", "boolean", "A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.", 0, java.lang.Integer.MAX_VALUE, updateCreate));
          childrenList.add(new Property("searchInclude", "string", "A list of _include values supported by the server.", 0, java.lang.Integer.MAX_VALUE, searchInclude));
          childrenList.add(new Property("searchParam", "", "Additional search parameters for implementations to support and/or make use of.", 0, java.lang.Integer.MAX_VALUE, searchParam));
        }

      public ConformanceRestResourceComponent copy() {
        ConformanceRestResourceComponent dst = new ConformanceRestResourceComponent();
        dst.type = type == null ? null : type.copy();
        dst.profile = profile == null ? null : profile.copy();
        dst.operation = new ArrayList<ConformanceRestResourceOperationComponent>();
        for (ConformanceRestResourceOperationComponent i : operation)
          dst.operation.add(i.copy());
        dst.readHistory = readHistory == null ? null : readHistory.copy();
        dst.updateCreate = updateCreate == null ? null : updateCreate.copy();
        dst.searchInclude = new ArrayList<String_>();
        for (String_ i : searchInclude)
          dst.searchInclude.add(i.copy());
        dst.searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();
        for (ConformanceRestResourceSearchParamComponent i : searchParam)
          dst.searchParam.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceRestResourceOperationComponent extends BackboneElement {
        /**
         * Coded identifier of the operation, supported by the system resource.
         */
        protected Enumeration<TypeRestfulOperation> code;

        /**
         * Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        protected String_ documentation;

        private static final long serialVersionUID = 1478000140L;

      public ConformanceRestResourceOperationComponent() {
        super();
      }

      public ConformanceRestResourceOperationComponent(Enumeration<TypeRestfulOperation> code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Coded identifier of the operation, supported by the system resource.)
         */
        public Enumeration<TypeRestfulOperation> getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Coded identifier of the operation, supported by the system resource.)
         */
        public ConformanceRestResourceOperationComponent setCode(Enumeration<TypeRestfulOperation> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Coded identifier of the operation, supported by the system resource.
         */
        public TypeRestfulOperation getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Coded identifier of the operation, supported by the system resource.
         */
        public ConformanceRestResourceOperationComponent setCodeSimple(TypeRestfulOperation value) { 
            if (this.code == null)
              this.code = new Enumeration<TypeRestfulOperation>();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.)
         */
        public ConformanceRestResourceOperationComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        public ConformanceRestResourceOperationComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Coded identifier of the operation, supported by the system resource.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("documentation", "string", "Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public ConformanceRestResourceOperationComponent copy() {
        ConformanceRestResourceOperationComponent dst = new ConformanceRestResourceOperationComponent();
        dst.code = code == null ? null : code.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public static class ConformanceRestResourceSearchParamComponent extends BackboneElement {
        /**
         * The name of the search parameter used in the interface.
         */
        protected String_ name;

        /**
         * A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        protected Uri definition;

        /**
         * The type of value a search parameter refers to, and how the content is interpreted.
         */
        protected Enumeration<SearchParamType> type;

        /**
         * This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        protected String_ documentation;

        /**
         * Types of resource (if a resource is referenced).
         */
        protected List<Code> target = new ArrayList<Code>();

        /**
         * Chained names supported.
         */
        protected List<String_> chain = new ArrayList<String_>();

        private static final long serialVersionUID = 296524510L;

      public ConformanceRestResourceSearchParamComponent() {
        super();
      }

      public ConformanceRestResourceSearchParamComponent(String_ name, Enumeration<SearchParamType> type) {
        super();
        this.name = name;
        this.type = type;
      }

        /**
         * @return {@link #name} (The name of the search parameter used in the interface.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the search parameter used in the interface.)
         */
        public ConformanceRestResourceSearchParamComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the search parameter used in the interface.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the search parameter used in the interface.
         */
        public ConformanceRestResourceSearchParamComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #definition} (A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.)
         */
        public Uri getDefinition() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.)
         */
        public ConformanceRestResourceSearchParamComponent setDefinition(Uri value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        public String getDefinitionSimple() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        /**
         * @param value A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        public ConformanceRestResourceSearchParamComponent setDefinitionSimple(String value) { 
          if (value == null)
            this.definition = null;
          else {
            if (this.definition == null)
              this.definition = new Uri();
            this.definition.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.)
         */
        public Enumeration<SearchParamType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.)
         */
        public ConformanceRestResourceSearchParamComponent setType(Enumeration<SearchParamType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of value a search parameter refers to, and how the content is interpreted.
         */
        public SearchParamType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of value a search parameter refers to, and how the content is interpreted.
         */
        public ConformanceRestResourceSearchParamComponent setTypeSimple(SearchParamType value) { 
            if (this.type == null)
              this.type = new Enumeration<SearchParamType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.)
         */
        public ConformanceRestResourceSearchParamComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        public ConformanceRestResourceSearchParamComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public List<Code> getTarget() { 
          return this.target;
        }

    // syntactic sugar
        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public Code addTarget() { 
          Code t = new Code();
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public Code addTargetSimple(String value) { 
          Code t = new Code();
          t.setValue(value);
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public boolean hasTargetSimple(String value) { 
          for (Code v : this.target)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #chain} (Chained names supported.)
         */
        public List<String_> getChain() { 
          return this.chain;
        }

    // syntactic sugar
        /**
         * @return {@link #chain} (Chained names supported.)
         */
        public String_ addChain() { 
          String_ t = new String_();
          this.chain.add(t);
          return t;
        }

        /**
         * @param value {@link #chain} (Chained names supported.)
         */
        public String_ addChainSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.chain.add(t);
          return t;
        }

        /**
         * @param value {@link #chain} (Chained names supported.)
         */
        public boolean hasChainSimple(String value) { 
          for (String_ v : this.chain)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of the search parameter used in the interface.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("definition", "uri", "A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.", 0, java.lang.Integer.MAX_VALUE, definition));
          childrenList.add(new Property("type", "code", "The type of value a search parameter refers to, and how the content is interpreted.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("documentation", "string", "This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("target", "code", "Types of resource (if a resource is referenced).", 0, java.lang.Integer.MAX_VALUE, target));
          childrenList.add(new Property("chain", "string", "Chained names supported.", 0, java.lang.Integer.MAX_VALUE, chain));
        }

      public ConformanceRestResourceSearchParamComponent copy() {
        ConformanceRestResourceSearchParamComponent dst = new ConformanceRestResourceSearchParamComponent();
        dst.name = name == null ? null : name.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.type = type == null ? null : type.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.target = new ArrayList<Code>();
        for (Code i : target)
          dst.target.add(i.copy());
        dst.chain = new ArrayList<String_>();
        for (String_ i : chain)
          dst.chain.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceRestOperationComponent extends BackboneElement {
        /**
         * A coded identifier of the operation, supported by the system.
         */
        protected Enumeration<SystemRestfulOperation> code;

        /**
         * Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        protected String_ documentation;

        private static final long serialVersionUID = 234115255L;

      public ConformanceRestOperationComponent() {
        super();
      }

      public ConformanceRestOperationComponent(Enumeration<SystemRestfulOperation> code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (A coded identifier of the operation, supported by the system.)
         */
        public Enumeration<SystemRestfulOperation> getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A coded identifier of the operation, supported by the system.)
         */
        public ConformanceRestOperationComponent setCode(Enumeration<SystemRestfulOperation> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return A coded identifier of the operation, supported by the system.
         */
        public SystemRestfulOperation getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value A coded identifier of the operation, supported by the system.
         */
        public ConformanceRestOperationComponent setCodeSimple(SystemRestfulOperation value) { 
            if (this.code == null)
              this.code = new Enumeration<SystemRestfulOperation>();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.)
         */
        public ConformanceRestOperationComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        public ConformanceRestOperationComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "A coded identifier of the operation, supported by the system.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("documentation", "string", "Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public ConformanceRestOperationComponent copy() {
        ConformanceRestOperationComponent dst = new ConformanceRestOperationComponent();
        dst.code = code == null ? null : code.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public static class ConformanceRestQueryComponent extends BackboneElement {
        /**
         * The name of a query, which is used in the _query parameter when the query is called.
         */
        protected String_ name;

        /**
         * Identifies the custom query, defined either in FHIR core or another profile.
         */
        protected Uri definition;

        /**
         * Additional information about how the query functions in this particular implementation.
         */
        protected String_ documentation;

        /**
         * Identifies which of the parameters for the named query are supported.
         */
        protected List<ConformanceRestResourceSearchParamComponent> parameter = new ArrayList<ConformanceRestResourceSearchParamComponent>();

        private static final long serialVersionUID = 853016812L;

      public ConformanceRestQueryComponent() {
        super();
      }

      public ConformanceRestQueryComponent(String_ name, Uri definition) {
        super();
        this.name = name;
        this.definition = definition;
      }

        /**
         * @return {@link #name} (The name of a query, which is used in the _query parameter when the query is called.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of a query, which is used in the _query parameter when the query is called.)
         */
        public ConformanceRestQueryComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of a query, which is used in the _query parameter when the query is called.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of a query, which is used in the _query parameter when the query is called.
         */
        public ConformanceRestQueryComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #definition} (Identifies the custom query, defined either in FHIR core or another profile.)
         */
        public Uri getDefinition() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (Identifies the custom query, defined either in FHIR core or another profile.)
         */
        public ConformanceRestQueryComponent setDefinition(Uri value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return Identifies the custom query, defined either in FHIR core or another profile.
         */
        public String getDefinitionSimple() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        /**
         * @param value Identifies the custom query, defined either in FHIR core or another profile.
         */
        public ConformanceRestQueryComponent setDefinitionSimple(String value) { 
            if (this.definition == null)
              this.definition = new Uri();
            this.definition.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Additional information about how the query functions in this particular implementation.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Additional information about how the query functions in this particular implementation.)
         */
        public ConformanceRestQueryComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Additional information about how the query functions in this particular implementation.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Additional information about how the query functions in this particular implementation.
         */
        public ConformanceRestQueryComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #parameter} (Identifies which of the parameters for the named query are supported.)
         */
        public List<ConformanceRestResourceSearchParamComponent> getParameter() { 
          return this.parameter;
        }

    // syntactic sugar
        /**
         * @return {@link #parameter} (Identifies which of the parameters for the named query are supported.)
         */
        public ConformanceRestResourceSearchParamComponent addParameter() { 
          ConformanceRestResourceSearchParamComponent t = new ConformanceRestResourceSearchParamComponent();
          this.parameter.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of a query, which is used in the _query parameter when the query is called.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("definition", "uri", "Identifies the custom query, defined either in FHIR core or another profile.", 0, java.lang.Integer.MAX_VALUE, definition));
          childrenList.add(new Property("documentation", "string", "Additional information about how the query functions in this particular implementation.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("parameter", "@Conformance.rest.resource.searchParam", "Identifies which of the parameters for the named query are supported.", 0, java.lang.Integer.MAX_VALUE, parameter));
        }

      public ConformanceRestQueryComponent copy() {
        ConformanceRestQueryComponent dst = new ConformanceRestQueryComponent();
        dst.name = name == null ? null : name.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.parameter = new ArrayList<ConformanceRestResourceSearchParamComponent>();
        for (ConformanceRestResourceSearchParamComponent i : parameter)
          dst.parameter.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceMessagingComponent extends BackboneElement {
        /**
         * An address to which messages and/or replies are to be sent.
         */
        protected Uri endpoint;

        /**
         * Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        protected Integer reliableCache;

        /**
         * Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        protected String_ documentation;

        /**
         * A description of the solution's support for an event at this end point.
         */
        protected List<ConformanceMessagingEventComponent> event = new ArrayList<ConformanceMessagingEventComponent>();

        private static final long serialVersionUID = -1576769754L;

      public ConformanceMessagingComponent() {
        super();
      }

        /**
         * @return {@link #endpoint} (An address to which messages and/or replies are to be sent.)
         */
        public Uri getEndpoint() { 
          return this.endpoint;
        }

        /**
         * @param value {@link #endpoint} (An address to which messages and/or replies are to be sent.)
         */
        public ConformanceMessagingComponent setEndpoint(Uri value) { 
          this.endpoint = value;
          return this;
        }

        /**
         * @return An address to which messages and/or replies are to be sent.
         */
        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        /**
         * @param value An address to which messages and/or replies are to be sent.
         */
        public ConformanceMessagingComponent setEndpointSimple(String value) { 
          if (value == null)
            this.endpoint = null;
          else {
            if (this.endpoint == null)
              this.endpoint = new Uri();
            this.endpoint.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #reliableCache} (Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).)
         */
        public Integer getReliableCache() { 
          return this.reliableCache;
        }

        /**
         * @param value {@link #reliableCache} (Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).)
         */
        public ConformanceMessagingComponent setReliableCache(Integer value) { 
          this.reliableCache = value;
          return this;
        }

        /**
         * @return Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        public int getReliableCacheSimple() { 
          return this.reliableCache == null ? null : this.reliableCache.getValue();
        }

        /**
         * @param value Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        public ConformanceMessagingComponent setReliableCacheSimple(int value) { 
          if (value == -1)
            this.reliableCache = null;
          else {
            if (this.reliableCache == null)
              this.reliableCache = new Integer();
            this.reliableCache.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #documentation} (Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.)
         */
        public ConformanceMessagingComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        public ConformanceMessagingComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #event} (A description of the solution's support for an event at this end point.)
         */
        public List<ConformanceMessagingEventComponent> getEvent() { 
          return this.event;
        }

    // syntactic sugar
        /**
         * @return {@link #event} (A description of the solution's support for an event at this end point.)
         */
        public ConformanceMessagingEventComponent addEvent() { 
          ConformanceMessagingEventComponent t = new ConformanceMessagingEventComponent();
          this.event.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("endpoint", "uri", "An address to which messages and/or replies are to be sent.", 0, java.lang.Integer.MAX_VALUE, endpoint));
          childrenList.add(new Property("reliableCache", "integer", "Length if the receiver's reliable messaging cache (if a receiver) or how long the cache length on the receiver should be (if a sender).", 0, java.lang.Integer.MAX_VALUE, reliableCache));
          childrenList.add(new Property("documentation", "string", "Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("event", "", "A description of the solution's support for an event at this end point.", 0, java.lang.Integer.MAX_VALUE, event));
        }

      public ConformanceMessagingComponent copy() {
        ConformanceMessagingComponent dst = new ConformanceMessagingComponent();
        dst.endpoint = endpoint == null ? null : endpoint.copy();
        dst.reliableCache = reliableCache == null ? null : reliableCache.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.event = new ArrayList<ConformanceMessagingEventComponent>();
        for (ConformanceMessagingEventComponent i : event)
          dst.event.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceMessagingEventComponent extends BackboneElement {
        /**
         * A coded identifier of a supported messaging event.
         */
        protected Coding code;

        /**
         * The impact of the content of the message.
         */
        protected Enumeration<MessageSignificanceCategory> category;

        /**
         * The mode of this event declaration - whether application is sender or receiver.
         */
        protected Enumeration<MessageConformanceEventMode> mode;

        /**
         * A list of the messaging transport protocol(s) identifiers, supported by this endpoint.
         */
        protected List<Coding> protocol = new ArrayList<Coding>();

        /**
         * A resource associated with the event.  This is the resource that defines the event.
         */
        protected Code focus;

        /**
         * Information about the request for this event.
         */
        protected ResourceReference request;

        /**
         * The actual object that is the target of the reference (Information about the request for this event.)
         */
        protected Profile requestTarget;

        /**
         * Information about the response for this event.
         */
        protected ResourceReference response;

        /**
         * The actual object that is the target of the reference (Information about the response for this event.)
         */
        protected Profile responseTarget;

        /**
         * Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        protected String_ documentation;

        private static final long serialVersionUID = 1849031993L;

      public ConformanceMessagingEventComponent() {
        super();
      }

      public ConformanceMessagingEventComponent(Coding code, Enumeration<MessageConformanceEventMode> mode, Code focus, ResourceReference request, ResourceReference response) {
        super();
        this.code = code;
        this.mode = mode;
        this.focus = focus;
        this.request = request;
        this.response = response;
      }

        /**
         * @return {@link #code} (A coded identifier of a supported messaging event.)
         */
        public Coding getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A coded identifier of a supported messaging event.)
         */
        public ConformanceMessagingEventComponent setCode(Coding value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #category} (The impact of the content of the message.)
         */
        public Enumeration<MessageSignificanceCategory> getCategory() { 
          return this.category;
        }

        /**
         * @param value {@link #category} (The impact of the content of the message.)
         */
        public ConformanceMessagingEventComponent setCategory(Enumeration<MessageSignificanceCategory> value) { 
          this.category = value;
          return this;
        }

        /**
         * @return The impact of the content of the message.
         */
        public MessageSignificanceCategory getCategorySimple() { 
          return this.category == null ? null : this.category.getValue();
        }

        /**
         * @param value The impact of the content of the message.
         */
        public ConformanceMessagingEventComponent setCategorySimple(MessageSignificanceCategory value) { 
          if (value == null)
            this.category = null;
          else {
            if (this.category == null)
              this.category = new Enumeration<MessageSignificanceCategory>();
            this.category.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #mode} (The mode of this event declaration - whether application is sender or receiver.)
         */
        public Enumeration<MessageConformanceEventMode> getMode() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (The mode of this event declaration - whether application is sender or receiver.)
         */
        public ConformanceMessagingEventComponent setMode(Enumeration<MessageConformanceEventMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return The mode of this event declaration - whether application is sender or receiver.
         */
        public MessageConformanceEventMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value The mode of this event declaration - whether application is sender or receiver.
         */
        public ConformanceMessagingEventComponent setModeSimple(MessageConformanceEventMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<MessageConformanceEventMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #protocol} (A list of the messaging transport protocol(s) identifiers, supported by this endpoint.)
         */
        public List<Coding> getProtocol() { 
          return this.protocol;
        }

    // syntactic sugar
        /**
         * @return {@link #protocol} (A list of the messaging transport protocol(s) identifiers, supported by this endpoint.)
         */
        public Coding addProtocol() { 
          Coding t = new Coding();
          this.protocol.add(t);
          return t;
        }

        /**
         * @return {@link #focus} (A resource associated with the event.  This is the resource that defines the event.)
         */
        public Code getFocus() { 
          return this.focus;
        }

        /**
         * @param value {@link #focus} (A resource associated with the event.  This is the resource that defines the event.)
         */
        public ConformanceMessagingEventComponent setFocus(Code value) { 
          this.focus = value;
          return this;
        }

        /**
         * @return A resource associated with the event.  This is the resource that defines the event.
         */
        public String getFocusSimple() { 
          return this.focus == null ? null : this.focus.getValue();
        }

        /**
         * @param value A resource associated with the event.  This is the resource that defines the event.
         */
        public ConformanceMessagingEventComponent setFocusSimple(String value) { 
            if (this.focus == null)
              this.focus = new Code();
            this.focus.setValue(value);
          return this;
        }

        /**
         * @return {@link #request} (Information about the request for this event.)
         */
        public ResourceReference getRequest() { 
          return this.request;
        }

        /**
         * @param value {@link #request} (Information about the request for this event.)
         */
        public ConformanceMessagingEventComponent setRequest(ResourceReference value) { 
          this.request = value;
          return this;
        }

        /**
         * @return {@link #request} (The actual object that is the target of the reference. Information about the request for this event.)
         */
        public Profile getRequestTarget() { 
          return this.requestTarget;
        }

        /**
         * @param value {@link #request} (The actual object that is the target of the reference. Information about the request for this event.)
         */
        public ConformanceMessagingEventComponent setRequestTarget(Profile value) { 
          this.requestTarget = value;
          return this;
        }

        /**
         * @return {@link #response} (Information about the response for this event.)
         */
        public ResourceReference getResponse() { 
          return this.response;
        }

        /**
         * @param value {@link #response} (Information about the response for this event.)
         */
        public ConformanceMessagingEventComponent setResponse(ResourceReference value) { 
          this.response = value;
          return this;
        }

        /**
         * @return {@link #response} (The actual object that is the target of the reference. Information about the response for this event.)
         */
        public Profile getResponseTarget() { 
          return this.responseTarget;
        }

        /**
         * @param value {@link #response} (The actual object that is the target of the reference. Information about the response for this event.)
         */
        public ConformanceMessagingEventComponent setResponseTarget(Profile value) { 
          this.responseTarget = value;
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance on how this event is handled, such as internal system trigger points, business rules, etc.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance on how this event is handled, such as internal system trigger points, business rules, etc.)
         */
        public ConformanceMessagingEventComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        public ConformanceMessagingEventComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "Coding", "A coded identifier of a supported messaging event.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("category", "code", "The impact of the content of the message.", 0, java.lang.Integer.MAX_VALUE, category));
          childrenList.add(new Property("mode", "code", "The mode of this event declaration - whether application is sender or receiver.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("protocol", "Coding", "A list of the messaging transport protocol(s) identifiers, supported by this endpoint.", 0, java.lang.Integer.MAX_VALUE, protocol));
          childrenList.add(new Property("focus", "code", "A resource associated with the event.  This is the resource that defines the event.", 0, java.lang.Integer.MAX_VALUE, focus));
          childrenList.add(new Property("request", "Resource(Profile)", "Information about the request for this event.", 0, java.lang.Integer.MAX_VALUE, request));
          childrenList.add(new Property("response", "Resource(Profile)", "Information about the response for this event.", 0, java.lang.Integer.MAX_VALUE, response));
          childrenList.add(new Property("documentation", "string", "Guidance on how this event is handled, such as internal system trigger points, business rules, etc.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public ConformanceMessagingEventComponent copy() {
        ConformanceMessagingEventComponent dst = new ConformanceMessagingEventComponent();
        dst.code = code == null ? null : code.copy();
        dst.category = category == null ? null : category.copy();
        dst.mode = mode == null ? null : mode.copy();
        dst.protocol = new ArrayList<Coding>();
        for (Coding i : protocol)
          dst.protocol.add(i.copy());
        dst.focus = focus == null ? null : focus.copy();
        dst.request = request == null ? null : request.copy();
        dst.response = response == null ? null : response.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public static class ConformanceDocumentComponent extends BackboneElement {
        /**
         * Mode of this document declaration - whether application is producer or consumer.
         */
        protected Enumeration<DocumentMode> mode;

        /**
         * A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        protected String_ documentation;

        /**
         * A constraint on a resource used in the document.
         */
        protected ResourceReference profile;

        /**
         * The actual object that is the target of the reference (A constraint on a resource used in the document.)
         */
        protected Profile profileTarget;

        private static final long serialVersionUID = -1485733893L;

      public ConformanceDocumentComponent() {
        super();
      }

      public ConformanceDocumentComponent(Enumeration<DocumentMode> mode, ResourceReference profile) {
        super();
        this.mode = mode;
        this.profile = profile;
      }

        /**
         * @return {@link #mode} (Mode of this document declaration - whether application is producer or consumer.)
         */
        public Enumeration<DocumentMode> getMode() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (Mode of this document declaration - whether application is producer or consumer.)
         */
        public ConformanceDocumentComponent setMode(Enumeration<DocumentMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return Mode of this document declaration - whether application is producer or consumer.
         */
        public DocumentMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value Mode of this document declaration - whether application is producer or consumer.
         */
        public ConformanceDocumentComponent setModeSimple(DocumentMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<DocumentMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.)
         */
        public ConformanceDocumentComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        public ConformanceDocumentComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #profile} (A constraint on a resource used in the document.)
         */
        public ResourceReference getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (A constraint on a resource used in the document.)
         */
        public ConformanceDocumentComponent setProfile(ResourceReference value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return {@link #profile} (The actual object that is the target of the reference. A constraint on a resource used in the document.)
         */
        public Profile getProfileTarget() { 
          return this.profileTarget;
        }

        /**
         * @param value {@link #profile} (The actual object that is the target of the reference. A constraint on a resource used in the document.)
         */
        public ConformanceDocumentComponent setProfileTarget(Profile value) { 
          this.profileTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "Mode of this document declaration - whether application is producer or consumer.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("documentation", "string", "A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("profile", "Resource(Profile)", "A constraint on a resource used in the document.", 0, java.lang.Integer.MAX_VALUE, profile));
        }

      public ConformanceDocumentComponent copy() {
        ConformanceDocumentComponent dst = new ConformanceDocumentComponent();
        dst.mode = mode == null ? null : mode.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.profile = profile == null ? null : profile.copy();
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected String_ identifier;

    /**
     * The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected String_ version;

    /**
     * A free text natural language name identifying the conformance statement.
     */
    protected String_ name;

    /**
     * Name of Organization publishing this conformance statement.
     */
    protected String_ publisher;

    /**
     * Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    protected String_ description;

    /**
     * The status of this conformance statement.
     */
    protected Enumeration<ConformanceStatementStatus> status;

    /**
     * A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected Boolean experimental;

    /**
     * The date when the conformance statement was published.
     */
    protected DateTime date;

    /**
     * Software that is covered by this conformance statement.  It is used when the profile describes the capabilities of a particular software version, independent of an installation.
     */
    protected ConformanceSoftwareComponent software;

    /**
     * Identifies a specific implementation instance that is described by the conformance statement - i.e. a particular installation, rather than the capabilities of a software program.
     */
    protected ConformanceImplementationComponent implementation;

    /**
     * The version of the FHIR specification on which this conformance statement is based.
     */
    protected Id fhirVersion;

    /**
     * A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    protected Boolean acceptUnknown;

    /**
     * A list of the formats supported by this implementation.
     */
    protected List<Code> format = new ArrayList<Code>();

    /**
     * A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.
     */
    protected List<ResourceReference> profile = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    protected List<Profile> profileTarget = new ArrayList<Profile>();


    /**
     * A definition of the restful capabilities of the solution, if any.
     */
    protected List<ConformanceRestComponent> rest = new ArrayList<ConformanceRestComponent>();

    /**
     * A description of the messaging capabilities of the solution.
     */
    protected List<ConformanceMessagingComponent> messaging = new ArrayList<ConformanceMessagingComponent>();

    /**
     * A document definition.
     */
    protected List<ConformanceDocumentComponent> document = new ArrayList<ConformanceDocumentComponent>();

    private static final long serialVersionUID = -1878231005L;

    public Conformance() {
      super();
    }

    public Conformance(String_ publisher, DateTime date, Id fhirVersion, Boolean acceptUnknown) {
      super();
      this.publisher = publisher;
      this.date = date;
      this.fhirVersion = fhirVersion;
      this.acceptUnknown = acceptUnknown;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public String_ getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public Conformance setIdentifier(String_ value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public Conformance setIdentifierSimple(String value) { 
      if (value == null)
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new String_();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public String_ getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public Conformance setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public Conformance setVersionSimple(String value) { 
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
     * @return {@link #name} (A free text natural language name identifying the conformance statement.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name identifying the conformance statement.)
     */
    public Conformance setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name identifying the conformance statement.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name identifying the conformance statement.
     */
    public Conformance setNameSimple(String value) { 
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
     * @return {@link #publisher} (Name of Organization publishing this conformance statement.)
     */
    public String_ getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Name of Organization publishing this conformance statement.)
     */
    public Conformance setPublisher(String_ value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Name of Organization publishing this conformance statement.
     */
    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Name of Organization publishing this conformance statement.
     */
    public Conformance setPublisherSimple(String value) { 
        if (this.publisher == null)
          this.publisher = new String_();
        this.publisher.setValue(value);
      return this;
    }

    /**
     * @return {@link #telecom} (Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #description} (A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.)
     */
    public Conformance setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    public Conformance setDescriptionSimple(String value) { 
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
     * @return {@link #status} (The status of this conformance statement.)
     */
    public Enumeration<ConformanceStatementStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of this conformance statement.)
     */
    public Conformance setStatus(Enumeration<ConformanceStatementStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of this conformance statement.
     */
    public ConformanceStatementStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of this conformance statement.
     */
    public Conformance setStatusSimple(ConformanceStatementStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ConformanceStatementStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #experimental} (A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public Boolean getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public Conformance setExperimental(Boolean value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimentalSimple() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public Conformance setExperimentalSimple(boolean value) { 
      if (value == false)
        this.experimental = null;
      else {
        if (this.experimental == null)
          this.experimental = new Boolean();
        this.experimental.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #date} (The date when the conformance statement was published.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date when the conformance statement was published.)
     */
    public Conformance setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date when the conformance statement was published.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date when the conformance statement was published.
     */
    public Conformance setDateSimple(DateAndTime value) { 
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      return this;
    }

    /**
     * @return {@link #software} (Software that is covered by this conformance statement.  It is used when the profile describes the capabilities of a particular software version, independent of an installation.)
     */
    public ConformanceSoftwareComponent getSoftware() { 
      return this.software;
    }

    /**
     * @param value {@link #software} (Software that is covered by this conformance statement.  It is used when the profile describes the capabilities of a particular software version, independent of an installation.)
     */
    public Conformance setSoftware(ConformanceSoftwareComponent value) { 
      this.software = value;
      return this;
    }

    /**
     * @return {@link #implementation} (Identifies a specific implementation instance that is described by the conformance statement - i.e. a particular installation, rather than the capabilities of a software program.)
     */
    public ConformanceImplementationComponent getImplementation() { 
      return this.implementation;
    }

    /**
     * @param value {@link #implementation} (Identifies a specific implementation instance that is described by the conformance statement - i.e. a particular installation, rather than the capabilities of a software program.)
     */
    public Conformance setImplementation(ConformanceImplementationComponent value) { 
      this.implementation = value;
      return this;
    }

    /**
     * @return {@link #fhirVersion} (The version of the FHIR specification on which this conformance statement is based.)
     */
    public Id getFhirVersion() { 
      return this.fhirVersion;
    }

    /**
     * @param value {@link #fhirVersion} (The version of the FHIR specification on which this conformance statement is based.)
     */
    public Conformance setFhirVersion(Id value) { 
      this.fhirVersion = value;
      return this;
    }

    /**
     * @return The version of the FHIR specification on which this conformance statement is based.
     */
    public String getFhirVersionSimple() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    /**
     * @param value The version of the FHIR specification on which this conformance statement is based.
     */
    public Conformance setFhirVersionSimple(String value) { 
        if (this.fhirVersion == null)
          this.fhirVersion = new Id();
        this.fhirVersion.setValue(value);
      return this;
    }

    /**
     * @return {@link #acceptUnknown} (A flag that indicates whether the application accepts unknown elements as part of a resource.)
     */
    public Boolean getAcceptUnknown() { 
      return this.acceptUnknown;
    }

    /**
     * @param value {@link #acceptUnknown} (A flag that indicates whether the application accepts unknown elements as part of a resource.)
     */
    public Conformance setAcceptUnknown(Boolean value) { 
      this.acceptUnknown = value;
      return this;
    }

    /**
     * @return A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    public boolean getAcceptUnknownSimple() { 
      return this.acceptUnknown == null ? false : this.acceptUnknown.getValue();
    }

    /**
     * @param value A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    public Conformance setAcceptUnknownSimple(boolean value) { 
        if (this.acceptUnknown == null)
          this.acceptUnknown = new Boolean();
        this.acceptUnknown.setValue(value);
      return this;
    }

    /**
     * @return {@link #format} (A list of the formats supported by this implementation.)
     */
    public List<Code> getFormat() { 
      return this.format;
    }

    // syntactic sugar
    /**
     * @return {@link #format} (A list of the formats supported by this implementation.)
     */
    public Code addFormat() { 
      Code t = new Code();
      this.format.add(t);
      return t;
    }

    /**
     * @param value {@link #format} (A list of the formats supported by this implementation.)
     */
    public Code addFormatSimple(String value) { 
      Code t = new Code();
      t.setValue(value);
      this.format.add(t);
      return t;
    }

    /**
     * @param value {@link #format} (A list of the formats supported by this implementation.)
     */
    public boolean hasFormatSimple(String value) { 
      for (Code v : this.format)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #profile} (A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public List<ResourceReference> getProfile() { 
      return this.profile;
    }

    // syntactic sugar
    /**
     * @return {@link #profile} (A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public ResourceReference addProfile() { 
      ResourceReference t = new ResourceReference();
      this.profile.add(t);
      return t;
    }

    /**
     * @return {@link #profile} (The actual objects that are the target of the reference. A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public List<Profile> getProfileTarget() { 
      return this.profileTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #profile} (Add an actual object that is the target of the reference. A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public Profile addProfileTarget() { 
      Profile r = new Profile();
      this.profileTarget.add(r);
      return r;
    }

    /**
     * @return {@link #rest} (A definition of the restful capabilities of the solution, if any.)
     */
    public List<ConformanceRestComponent> getRest() { 
      return this.rest;
    }

    // syntactic sugar
    /**
     * @return {@link #rest} (A definition of the restful capabilities of the solution, if any.)
     */
    public ConformanceRestComponent addRest() { 
      ConformanceRestComponent t = new ConformanceRestComponent();
      this.rest.add(t);
      return t;
    }

    /**
     * @return {@link #messaging} (A description of the messaging capabilities of the solution.)
     */
    public List<ConformanceMessagingComponent> getMessaging() { 
      return this.messaging;
    }

    // syntactic sugar
    /**
     * @return {@link #messaging} (A description of the messaging capabilities of the solution.)
     */
    public ConformanceMessagingComponent addMessaging() { 
      ConformanceMessagingComponent t = new ConformanceMessagingComponent();
      this.messaging.add(t);
      return t;
    }

    /**
     * @return {@link #document} (A document definition.)
     */
    public List<ConformanceDocumentComponent> getDocument() { 
      return this.document;
    }

    // syntactic sugar
    /**
     * @return {@link #document} (A document definition.)
     */
    public ConformanceDocumentComponent addDocument() { 
      ConformanceDocumentComponent t = new ConformanceDocumentComponent();
      this.document.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "string", "The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name identifying the conformance statement.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("publisher", "string", "Name of Organization publishing this conformance statement.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("status", "code", "The status of this conformance statement.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date when the conformance statement was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("software", "", "Software that is covered by this conformance statement.  It is used when the profile describes the capabilities of a particular software version, independent of an installation.", 0, java.lang.Integer.MAX_VALUE, software));
        childrenList.add(new Property("implementation", "", "Identifies a specific implementation instance that is described by the conformance statement - i.e. a particular installation, rather than the capabilities of a software program.", 0, java.lang.Integer.MAX_VALUE, implementation));
        childrenList.add(new Property("fhirVersion", "id", "The version of the FHIR specification on which this conformance statement is based.", 0, java.lang.Integer.MAX_VALUE, fhirVersion));
        childrenList.add(new Property("acceptUnknown", "boolean", "A flag that indicates whether the application accepts unknown elements as part of a resource.", 0, java.lang.Integer.MAX_VALUE, acceptUnknown));
        childrenList.add(new Property("format", "code", "A list of the formats supported by this implementation.", 0, java.lang.Integer.MAX_VALUE, format));
        childrenList.add(new Property("profile", "Resource(Profile)", "A list of profiles supported by the system. For a server, 'supported by the system' means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.", 0, java.lang.Integer.MAX_VALUE, profile));
        childrenList.add(new Property("rest", "", "A definition of the restful capabilities of the solution, if any.", 0, java.lang.Integer.MAX_VALUE, rest));
        childrenList.add(new Property("messaging", "", "A description of the messaging capabilities of the solution.", 0, java.lang.Integer.MAX_VALUE, messaging));
        childrenList.add(new Property("document", "", "A document definition.", 0, java.lang.Integer.MAX_VALUE, document));
      }

      public Conformance copy() {
        Conformance dst = new Conformance();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.status = status == null ? null : status.copy();
        dst.experimental = experimental == null ? null : experimental.copy();
        dst.date = date == null ? null : date.copy();
        dst.software = software == null ? null : software.copy();
        dst.implementation = implementation == null ? null : implementation.copy();
        dst.fhirVersion = fhirVersion == null ? null : fhirVersion.copy();
        dst.acceptUnknown = acceptUnknown == null ? null : acceptUnknown.copy();
        dst.format = new ArrayList<Code>();
        for (Code i : format)
          dst.format.add(i.copy());
        dst.profile = new ArrayList<ResourceReference>();
        for (ResourceReference i : profile)
          dst.profile.add(i.copy());
        dst.rest = new ArrayList<ConformanceRestComponent>();
        for (ConformanceRestComponent i : rest)
          dst.rest.add(i.copy());
        dst.messaging = new ArrayList<ConformanceMessagingComponent>();
        for (ConformanceMessagingComponent i : messaging)
          dst.messaging.add(i.copy());
        dst.document = new ArrayList<ConformanceDocumentComponent>();
        for (ConformanceDocumentComponent i : document)
          dst.document.add(i.copy());
        return dst;
      }

      protected Conformance typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Conformance;
   }


}

