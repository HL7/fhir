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
 * A conformance statement is a set of requirements for a desired implementation or a description of how a target application fulfills those requirements in a particular implementation.
 */
public class Conformance extends DomainResource {

    public enum ConformanceStatementStatus {
        DRAFT, // This conformance statement is still under development.
        ACTIVE, // This conformance statement is ready for use in production systems.
        RETIRED, // This conformance statement has been withdrawn or superceded and should no longer be used.
        NULL; // added to help the parsers
        public static ConformanceStatementStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("retired".equals(codeString))
          return RETIRED;
        throw new Exception("Unknown ConformanceStatementStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case ACTIVE: return "active";
            case RETIRED: return "retired";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "This conformance statement is still under development.";
            case ACTIVE: return "This conformance statement is ready for use in production systems.";
            case RETIRED: return "This conformance statement has been withdrawn or superceded and should no longer be used.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "draft";
            case ACTIVE: return "active";
            case RETIRED: return "retired";
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
          return ConformanceStatementStatus.DRAFT;
        if ("active".equals(codeString))
          return ConformanceStatementStatus.ACTIVE;
        if ("retired".equals(codeString))
          return ConformanceStatementStatus.RETIRED;
        throw new Exception("Unknown ConformanceStatementStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConformanceStatementStatus.DRAFT)
        return "draft";
      if (code == ConformanceStatementStatus.ACTIVE)
        return "active";
      if (code == ConformanceStatementStatus.RETIRED)
        return "retired";
      return "?";
      }
    }

    public enum RestfulConformanceMode {
        CLIENT, // The application acts as a server for this resource.
        SERVER, // The application acts as a client for this resource.
        NULL; // added to help the parsers
        public static RestfulConformanceMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("client".equals(codeString))
          return CLIENT;
        if ("server".equals(codeString))
          return SERVER;
        throw new Exception("Unknown RestfulConformanceMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CLIENT: return "client";
            case SERVER: return "server";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case CLIENT: return "The application acts as a server for this resource.";
            case SERVER: return "The application acts as a client for this resource.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CLIENT: return "client";
            case SERVER: return "server";
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
          return RestfulConformanceMode.CLIENT;
        if ("server".equals(codeString))
          return RestfulConformanceMode.SERVER;
        throw new Exception("Unknown RestfulConformanceMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == RestfulConformanceMode.CLIENT)
        return "client";
      if (code == RestfulConformanceMode.SERVER)
        return "server";
      return "?";
      }
    }

    public enum TypeRestfulInteraction {
        READ, // 
        VREAD, // 
        UPDATE, // 
        DELETE, // 
        HISTORYINSTANCE, // 
        VALIDATE, // 
        HISTORYTYPE, // 
        CREATE, // 
        SEARCHTYPE, // 
        NULL; // added to help the parsers
        public static TypeRestfulInteraction fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("read".equals(codeString))
          return READ;
        if ("vread".equals(codeString))
          return VREAD;
        if ("update".equals(codeString))
          return UPDATE;
        if ("delete".equals(codeString))
          return DELETE;
        if ("history-instance".equals(codeString))
          return HISTORYINSTANCE;
        if ("validate".equals(codeString))
          return VALIDATE;
        if ("history-type".equals(codeString))
          return HISTORYTYPE;
        if ("create".equals(codeString))
          return CREATE;
        if ("search-type".equals(codeString))
          return SEARCHTYPE;
        throw new Exception("Unknown TypeRestfulInteraction code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case READ: return "read";
            case VREAD: return "vread";
            case UPDATE: return "update";
            case DELETE: return "delete";
            case HISTORYINSTANCE: return "history-instance";
            case VALIDATE: return "validate";
            case HISTORYTYPE: return "history-type";
            case CREATE: return "create";
            case SEARCHTYPE: return "search-type";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case READ: return "";
            case VREAD: return "";
            case UPDATE: return "";
            case DELETE: return "";
            case HISTORYINSTANCE: return "";
            case VALIDATE: return "";
            case HISTORYTYPE: return "";
            case CREATE: return "";
            case SEARCHTYPE: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case READ: return "read";
            case VREAD: return "vread";
            case UPDATE: return "update";
            case DELETE: return "delete";
            case HISTORYINSTANCE: return "history-instance";
            case VALIDATE: return "validate";
            case HISTORYTYPE: return "history-type";
            case CREATE: return "create";
            case SEARCHTYPE: return "search-type";
            default: return "?";
          }
        }
    }

  public static class TypeRestfulInteractionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("read".equals(codeString))
          return TypeRestfulInteraction.READ;
        if ("vread".equals(codeString))
          return TypeRestfulInteraction.VREAD;
        if ("update".equals(codeString))
          return TypeRestfulInteraction.UPDATE;
        if ("delete".equals(codeString))
          return TypeRestfulInteraction.DELETE;
        if ("history-instance".equals(codeString))
          return TypeRestfulInteraction.HISTORYINSTANCE;
        if ("validate".equals(codeString))
          return TypeRestfulInteraction.VALIDATE;
        if ("history-type".equals(codeString))
          return TypeRestfulInteraction.HISTORYTYPE;
        if ("create".equals(codeString))
          return TypeRestfulInteraction.CREATE;
        if ("search-type".equals(codeString))
          return TypeRestfulInteraction.SEARCHTYPE;
        throw new Exception("Unknown TypeRestfulInteraction code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == TypeRestfulInteraction.READ)
        return "read";
      if (code == TypeRestfulInteraction.VREAD)
        return "vread";
      if (code == TypeRestfulInteraction.UPDATE)
        return "update";
      if (code == TypeRestfulInteraction.DELETE)
        return "delete";
      if (code == TypeRestfulInteraction.HISTORYINSTANCE)
        return "history-instance";
      if (code == TypeRestfulInteraction.VALIDATE)
        return "validate";
      if (code == TypeRestfulInteraction.HISTORYTYPE)
        return "history-type";
      if (code == TypeRestfulInteraction.CREATE)
        return "create";
      if (code == TypeRestfulInteraction.SEARCHTYPE)
        return "search-type";
      return "?";
      }
    }

    public enum VersioningPolicy {
        NOVERSION, // VersionId meta-property is not suppoerted (server) or used (client).
        VERSIONED, // VersionId meta-property is suppoerted (server) or used (client).
        VERSIONEDUPDATE, // VersionId is must be correct for updates (server) or will be specified (If-match header) for updates (client).
        NULL; // added to help the parsers
        public static VersioningPolicy fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("no-version".equals(codeString))
          return NOVERSION;
        if ("versioned".equals(codeString))
          return VERSIONED;
        if ("versioned-update".equals(codeString))
          return VERSIONEDUPDATE;
        throw new Exception("Unknown VersioningPolicy code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case NOVERSION: return "no-version";
            case VERSIONED: return "versioned";
            case VERSIONEDUPDATE: return "versioned-update";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case NOVERSION: return "VersionId meta-property is not suppoerted (server) or used (client).";
            case VERSIONED: return "VersionId meta-property is suppoerted (server) or used (client).";
            case VERSIONEDUPDATE: return "VersionId is must be correct for updates (server) or will be specified (If-match header) for updates (client).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case NOVERSION: return "No VersionId Support";
            case VERSIONED: return "Versioned";
            case VERSIONEDUPDATE: return "VersionId tracked fully";
            default: return "?";
          }
        }
    }

  public static class VersioningPolicyEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("no-version".equals(codeString))
          return VersioningPolicy.NOVERSION;
        if ("versioned".equals(codeString))
          return VersioningPolicy.VERSIONED;
        if ("versioned-update".equals(codeString))
          return VersioningPolicy.VERSIONEDUPDATE;
        throw new Exception("Unknown VersioningPolicy code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == VersioningPolicy.NOVERSION)
        return "no-version";
      if (code == VersioningPolicy.VERSIONED)
        return "versioned";
      if (code == VersioningPolicy.VERSIONEDUPDATE)
        return "versioned-update";
      return "?";
      }
    }

    public enum SearchParamType {
        NUMBER, // Search parameter SHALL be a number (a whole number, or a decimal).
        DATE, // Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.
        STRING, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.
        TOKEN, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "|", depending on the modifier used.
        REFERENCE, // A reference to another resource.
        COMPOSITE, // A composite search parameter that combines a search on two values together.
        QUANTITY, // A search parameter that searches on a quantity.
        NULL; // added to help the parsers
        public static SearchParamType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return NUMBER;
        if ("date".equals(codeString))
          return DATE;
        if ("string".equals(codeString))
          return STRING;
        if ("token".equals(codeString))
          return TOKEN;
        if ("reference".equals(codeString))
          return REFERENCE;
        if ("composite".equals(codeString))
          return COMPOSITE;
        if ("quantity".equals(codeString))
          return QUANTITY;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case NUMBER: return "number";
            case DATE: return "date";
            case STRING: return "string";
            case TOKEN: return "token";
            case REFERENCE: return "reference";
            case COMPOSITE: return "composite";
            case QUANTITY: return "quantity";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case NUMBER: return "Search parameter SHALL be a number (a whole number, or a decimal).";
            case DATE: return "Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.";
            case STRING: return "Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.";
            case TOKEN: return "Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a '|', depending on the modifier used.";
            case REFERENCE: return "A reference to another resource.";
            case COMPOSITE: return "A composite search parameter that combines a search on two values together.";
            case QUANTITY: return "A search parameter that searches on a quantity.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case NUMBER: return "number";
            case DATE: return "date";
            case STRING: return "string";
            case TOKEN: return "token";
            case REFERENCE: return "reference";
            case COMPOSITE: return "composite";
            case QUANTITY: return "quantity";
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
          return SearchParamType.NUMBER;
        if ("date".equals(codeString))
          return SearchParamType.DATE;
        if ("string".equals(codeString))
          return SearchParamType.STRING;
        if ("token".equals(codeString))
          return SearchParamType.TOKEN;
        if ("reference".equals(codeString))
          return SearchParamType.REFERENCE;
        if ("composite".equals(codeString))
          return SearchParamType.COMPOSITE;
        if ("quantity".equals(codeString))
          return SearchParamType.QUANTITY;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SearchParamType.NUMBER)
        return "number";
      if (code == SearchParamType.DATE)
        return "date";
      if (code == SearchParamType.STRING)
        return "string";
      if (code == SearchParamType.TOKEN)
        return "token";
      if (code == SearchParamType.REFERENCE)
        return "reference";
      if (code == SearchParamType.COMPOSITE)
        return "composite";
      if (code == SearchParamType.QUANTITY)
        return "quantity";
      return "?";
      }
    }

    public enum SystemRestfulInteraction {
        TRANSACTION, // 
        SEARCHSYSTEM, // 
        HISTORYSYSTEM, // 
        NULL; // added to help the parsers
        public static SystemRestfulInteraction fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("transaction".equals(codeString))
          return TRANSACTION;
        if ("search-system".equals(codeString))
          return SEARCHSYSTEM;
        if ("history-system".equals(codeString))
          return HISTORYSYSTEM;
        throw new Exception("Unknown SystemRestfulInteraction code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case TRANSACTION: return "transaction";
            case SEARCHSYSTEM: return "search-system";
            case HISTORYSYSTEM: return "history-system";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case TRANSACTION: return "";
            case SEARCHSYSTEM: return "";
            case HISTORYSYSTEM: return "";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case TRANSACTION: return "transaction";
            case SEARCHSYSTEM: return "search-system";
            case HISTORYSYSTEM: return "history-system";
            default: return "?";
          }
        }
    }

  public static class SystemRestfulInteractionEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("transaction".equals(codeString))
          return SystemRestfulInteraction.TRANSACTION;
        if ("search-system".equals(codeString))
          return SystemRestfulInteraction.SEARCHSYSTEM;
        if ("history-system".equals(codeString))
          return SystemRestfulInteraction.HISTORYSYSTEM;
        throw new Exception("Unknown SystemRestfulInteraction code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SystemRestfulInteraction.TRANSACTION)
        return "transaction";
      if (code == SystemRestfulInteraction.SEARCHSYSTEM)
        return "search-system";
      if (code == SystemRestfulInteraction.HISTORYSYSTEM)
        return "history-system";
      return "?";
      }
    }

    public enum MessageSignificanceCategory {
        CONSEQUENCE, // The message represents/requests a change that should not be processed more than once. E.g. Making a booking for an appointment.
        CURRENCY, // The message represents a response to query for current information. Retrospective processing is wrong and/or wasteful.
        NOTIFICATION, // The content is not necessarily intended to be current, and it can be reprocessed, though there may be version issues created by processing old notifications.
        NULL; // added to help the parsers
        public static MessageSignificanceCategory fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("Consequence".equals(codeString))
          return CONSEQUENCE;
        if ("Currency".equals(codeString))
          return CURRENCY;
        if ("Notification".equals(codeString))
          return NOTIFICATION;
        throw new Exception("Unknown MessageSignificanceCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CONSEQUENCE: return "Consequence";
            case CURRENCY: return "Currency";
            case NOTIFICATION: return "Notification";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case CONSEQUENCE: return "The message represents/requests a change that should not be processed more than once. E.g. Making a booking for an appointment.";
            case CURRENCY: return "The message represents a response to query for current information. Retrospective processing is wrong and/or wasteful.";
            case NOTIFICATION: return "The content is not necessarily intended to be current, and it can be reprocessed, though there may be version issues created by processing old notifications.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CONSEQUENCE: return "Consequence";
            case CURRENCY: return "Currency";
            case NOTIFICATION: return "Notification";
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
          return MessageSignificanceCategory.CONSEQUENCE;
        if ("Currency".equals(codeString))
          return MessageSignificanceCategory.CURRENCY;
        if ("Notification".equals(codeString))
          return MessageSignificanceCategory.NOTIFICATION;
        throw new Exception("Unknown MessageSignificanceCategory code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MessageSignificanceCategory.CONSEQUENCE)
        return "Consequence";
      if (code == MessageSignificanceCategory.CURRENCY)
        return "Currency";
      if (code == MessageSignificanceCategory.NOTIFICATION)
        return "Notification";
      return "?";
      }
    }

    public enum MessageConformanceEventMode {
        SENDER, // The application sends requests and receives responses.
        RECEIVER, // The application receives requests and sends responses.
        NULL; // added to help the parsers
        public static MessageConformanceEventMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("sender".equals(codeString))
          return SENDER;
        if ("receiver".equals(codeString))
          return RECEIVER;
        throw new Exception("Unknown MessageConformanceEventMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case SENDER: return "sender";
            case RECEIVER: return "receiver";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case SENDER: return "The application sends requests and receives responses.";
            case RECEIVER: return "The application receives requests and sends responses.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case SENDER: return "sender";
            case RECEIVER: return "receiver";
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
          return MessageConformanceEventMode.SENDER;
        if ("receiver".equals(codeString))
          return MessageConformanceEventMode.RECEIVER;
        throw new Exception("Unknown MessageConformanceEventMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MessageConformanceEventMode.SENDER)
        return "sender";
      if (code == MessageConformanceEventMode.RECEIVER)
        return "receiver";
      return "?";
      }
    }

    public enum DocumentMode {
        PRODUCER, // The application produces documents of the specified type.
        CONSUMER, // The application consumes documents of the specified type.
        NULL; // added to help the parsers
        public static DocumentMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("producer".equals(codeString))
          return PRODUCER;
        if ("consumer".equals(codeString))
          return CONSUMER;
        throw new Exception("Unknown DocumentMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PRODUCER: return "producer";
            case CONSUMER: return "consumer";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PRODUCER: return "The application produces documents of the specified type.";
            case CONSUMER: return "The application consumes documents of the specified type.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PRODUCER: return "producer";
            case CONSUMER: return "consumer";
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
          return DocumentMode.PRODUCER;
        if ("consumer".equals(codeString))
          return DocumentMode.CONSUMER;
        throw new Exception("Unknown DocumentMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == DocumentMode.PRODUCER)
        return "producer";
      if (code == DocumentMode.CONSUMER)
        return "consumer";
      return "?";
      }
    }

    public static class ConformanceSoftwareComponent extends BackboneElement {
        /**
         * Name software is known by.
         */
        protected StringType name;

        /**
         * The version identifier for the software covered by this statement.
         */
        protected StringType version;

        /**
         * Date this version of the software released.
         */
        protected DateTimeType releaseDate;

        private static final long serialVersionUID = 1819769027L;

      public ConformanceSoftwareComponent() {
        super();
      }

      public ConformanceSoftwareComponent(StringType name) {
        super();
        this.name = name;
      }

        /**
         * @return {@link #name} (Name software is known by.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Name software is known by.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public ConformanceSoftwareComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Name software is known by.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Name software is known by.
         */
        public ConformanceSoftwareComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version identifier for the software covered by this statement.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public StringType getVersionElement() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version identifier for the software covered by this statement.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public ConformanceSoftwareComponent setVersionElement(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version identifier for the software covered by this statement.
         */
        public String getVersion() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version identifier for the software covered by this statement.
         */
        public ConformanceSoftwareComponent setVersion(String value) { 
          if (Utilities.noString(value))
            this.version = null;
          else {
            if (this.version == null)
              this.version = new StringType();
            this.version.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #releaseDate} (Date this version of the software released.). This is the underlying object with id, value and extensions. The accessor "getReleaseDate" gives direct access to the value
         */
        public DateTimeType getReleaseDateElement() { 
          return this.releaseDate;
        }

        /**
         * @param value {@link #releaseDate} (Date this version of the software released.). This is the underlying object with id, value and extensions. The accessor "getReleaseDate" gives direct access to the value
         */
        public ConformanceSoftwareComponent setReleaseDateElement(DateTimeType value) { 
          this.releaseDate = value;
          return this;
        }

        /**
         * @return Date this version of the software released.
         */
        public DateAndTime getReleaseDate() { 
          return this.releaseDate == null ? null : this.releaseDate.getValue();
        }

        /**
         * @param value Date this version of the software released.
         */
        public ConformanceSoftwareComponent setReleaseDate(DateAndTime value) { 
          if (value == null)
            this.releaseDate = null;
          else {
            if (this.releaseDate == null)
              this.releaseDate = new DateTimeType();
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
        copyValues(dst);
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
        protected StringType description;

        /**
         * A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        protected UriType url;

        private static final long serialVersionUID = -289238508L;

      public ConformanceImplementationComponent() {
        super();
      }

      public ConformanceImplementationComponent(StringType description) {
        super();
        this.description = description;
      }

        /**
         * @return {@link #description} (Information about the specific installation that this conformance statement relates to.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Information about the specific installation that this conformance statement relates to.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public ConformanceImplementationComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Information about the specific installation that this conformance statement relates to.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Information about the specific installation that this conformance statement relates to.
         */
        public ConformanceImplementationComponent setDescription(String value) { 
            if (this.description == null)
              this.description = new StringType();
            this.description.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public ConformanceImplementationComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value A base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        public ConformanceImplementationComponent setUrl(String value) { 
          if (Utilities.noString(value))
            this.url = null;
          else {
            if (this.url == null)
              this.url = new UriType();
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
        copyValues(dst);
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
        protected StringType documentation;

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
        protected List<SystemInteractionComponent> interaction = new ArrayList<SystemInteractionComponent>();

        /**
         * Definition of an operation or a named query and with its parameters and their meaning and type.
         */
        protected List<ConformanceRestOperationComponent> operation = new ArrayList<ConformanceRestOperationComponent>();

        /**
         * A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.
         */
        protected List<UriType> documentMailbox = new ArrayList<UriType>();

        private static final long serialVersionUID = -1994615780L;

      public ConformanceRestComponent() {
        super();
      }

      public ConformanceRestComponent(Enumeration<RestfulConformanceMode> mode) {
        super();
        this.mode = mode;
      }

        /**
         * @return {@link #mode} (Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public Enumeration<RestfulConformanceMode> getModeElement() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public ConformanceRestComponent setModeElement(Enumeration<RestfulConformanceMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        public RestfulConformanceMode getMode() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        public ConformanceRestComponent setMode(RestfulConformanceMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<RestfulConformanceMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Information about the system's restful capabilities that apply across all applications, such as security.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Information about the system's restful capabilities that apply across all applications, such as security.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ConformanceRestComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Information about the system's restful capabilities that apply across all applications, such as security.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Information about the system's restful capabilities that apply across all applications, such as security.
         */
        public ConformanceRestComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
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

        /**
         * @return {@link #resource} (A specification of the restful capabilities of the solution for a specific resource type.)
         */
    // syntactic sugar
        public ConformanceRestResourceComponent addResource() { //3
          ConformanceRestResourceComponent t = new ConformanceRestResourceComponent();
          this.resource.add(t);
          return t;
        }

        /**
         * @return {@link #interaction} (A specification of restful operations supported by the system.)
         */
        public List<SystemInteractionComponent> getInteraction() { 
          return this.interaction;
        }

        /**
         * @return {@link #interaction} (A specification of restful operations supported by the system.)
         */
    // syntactic sugar
        public SystemInteractionComponent addInteraction() { //3
          SystemInteractionComponent t = new SystemInteractionComponent();
          this.interaction.add(t);
          return t;
        }

        /**
         * @return {@link #operation} (Definition of an operation or a named query and with its parameters and their meaning and type.)
         */
        public List<ConformanceRestOperationComponent> getOperation() { 
          return this.operation;
        }

        /**
         * @return {@link #operation} (Definition of an operation or a named query and with its parameters and their meaning and type.)
         */
    // syntactic sugar
        public ConformanceRestOperationComponent addOperation() { //3
          ConformanceRestOperationComponent t = new ConformanceRestOperationComponent();
          this.operation.add(t);
          return t;
        }

        /**
         * @return {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public List<UriType> getDocumentMailbox() { 
          return this.documentMailbox;
        }

        /**
         * @return {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
    // syntactic sugar
        public UriType addDocumentMailboxElement() {//2 
          UriType t = new UriType();
          this.documentMailbox.add(t);
          return t;
        }

        /**
         * @param value {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public ConformanceRestComponent addDocumentMailbox(String value) { //1
          UriType t = new UriType();
          t.setValue(value);
          this.documentMailbox.add(t);
          return this;
        }

        /**
         * @param value {@link #documentMailbox} (A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier "http://hl7.org/fhir/documents/mailbox". Other specifications can declare their own identifier for this purpose.)
         */
        public boolean hasDocumentMailbox(String value) { 
          for (UriType v : this.documentMailbox)
            if (v.equals(value)) // uri
              return true;
          return false;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("documentation", "string", "Information about the system's restful capabilities that apply across all applications, such as security.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("security", "", "Information about security of implementation.", 0, java.lang.Integer.MAX_VALUE, security));
          childrenList.add(new Property("resource", "", "A specification of the restful capabilities of the solution for a specific resource type.", 0, java.lang.Integer.MAX_VALUE, resource));
          childrenList.add(new Property("interaction", "", "A specification of restful operations supported by the system.", 0, java.lang.Integer.MAX_VALUE, interaction));
          childrenList.add(new Property("operation", "", "Definition of an operation or a named query and with its parameters and their meaning and type.", 0, java.lang.Integer.MAX_VALUE, operation));
          childrenList.add(new Property("documentMailbox", "uri", "A list of profiles that this server implements for accepting documents in the mailbox. If this list is empty, then documents are not accepted. The base specification has the profile identifier 'http://hl7.org/fhir/documents/mailbox'. Other specifications can declare their own identifier for this purpose.", 0, java.lang.Integer.MAX_VALUE, documentMailbox));
        }

      public ConformanceRestComponent copy() {
        ConformanceRestComponent dst = new ConformanceRestComponent();
        copyValues(dst);
        dst.mode = mode == null ? null : mode.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.security = security == null ? null : security.copy();
        dst.resource = new ArrayList<ConformanceRestResourceComponent>();
        for (ConformanceRestResourceComponent i : resource)
          dst.resource.add(i.copy());
        dst.interaction = new ArrayList<SystemInteractionComponent>();
        for (SystemInteractionComponent i : interaction)
          dst.interaction.add(i.copy());
        dst.operation = new ArrayList<ConformanceRestOperationComponent>();
        for (ConformanceRestOperationComponent i : operation)
          dst.operation.add(i.copy());
        dst.documentMailbox = new ArrayList<UriType>();
        for (UriType i : documentMailbox)
          dst.documentMailbox.add(i.copy());
        return dst;
      }

  }

    public static class ConformanceRestSecurityComponent extends BackboneElement {
        /**
         * Server adds CORS headers when responding to requests - this enables javascript applications to use the server.
         */
        protected BooleanType cors;

        /**
         * Types of security services are supported/required by the system.
         */
        protected List<CodeableConcept> service = new ArrayList<CodeableConcept>();

        /**
         * General description of how security works.
         */
        protected StringType description;

        /**
         * Certificates associated with security profiles.
         */
        protected List<ConformanceRestSecurityCertificateComponent> certificate = new ArrayList<ConformanceRestSecurityCertificateComponent>();

        private static final long serialVersionUID = -1974024888L;

      public ConformanceRestSecurityComponent() {
        super();
      }

        /**
         * @return {@link #cors} (Server adds CORS headers when responding to requests - this enables javascript applications to use the server.). This is the underlying object with id, value and extensions. The accessor "getCors" gives direct access to the value
         */
        public BooleanType getCorsElement() { 
          return this.cors;
        }

        /**
         * @param value {@link #cors} (Server adds CORS headers when responding to requests - this enables javascript applications to use the server.). This is the underlying object with id, value and extensions. The accessor "getCors" gives direct access to the value
         */
        public ConformanceRestSecurityComponent setCorsElement(BooleanType value) { 
          this.cors = value;
          return this;
        }

        /**
         * @return Server adds CORS headers when responding to requests - this enables javascript applications to use the server.
         */
        public boolean getCors() { 
          return this.cors == null ? false : this.cors.getValue();
        }

        /**
         * @param value Server adds CORS headers when responding to requests - this enables javascript applications to use the server.
         */
        public ConformanceRestSecurityComponent setCors(boolean value) { 
          if (value == false)
            this.cors = null;
          else {
            if (this.cors == null)
              this.cors = new BooleanType();
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

        /**
         * @return {@link #service} (Types of security services are supported/required by the system.)
         */
    // syntactic sugar
        public CodeableConcept addService() { //3
          CodeableConcept t = new CodeableConcept();
          this.service.add(t);
          return t;
        }

        /**
         * @return {@link #description} (General description of how security works.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (General description of how security works.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public ConformanceRestSecurityComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return General description of how security works.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value General description of how security works.
         */
        public ConformanceRestSecurityComponent setDescription(String value) { 
          if (Utilities.noString(value))
            this.description = null;
          else {
            if (this.description == null)
              this.description = new StringType();
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

        /**
         * @return {@link #certificate} (Certificates associated with security profiles.)
         */
    // syntactic sugar
        public ConformanceRestSecurityCertificateComponent addCertificate() { //3
          ConformanceRestSecurityCertificateComponent t = new ConformanceRestSecurityCertificateComponent();
          this.certificate.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("cors", "boolean", "Server adds CORS headers when responding to requests - this enables javascript applications to use the server.", 0, java.lang.Integer.MAX_VALUE, cors));
          childrenList.add(new Property("service", "CodeableConcept", "Types of security services are supported/required by the system.", 0, java.lang.Integer.MAX_VALUE, service));
          childrenList.add(new Property("description", "string", "General description of how security works.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("certificate", "", "Certificates associated with security profiles.", 0, java.lang.Integer.MAX_VALUE, certificate));
        }

      public ConformanceRestSecurityComponent copy() {
        ConformanceRestSecurityComponent dst = new ConformanceRestSecurityComponent();
        copyValues(dst);
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
        protected CodeType type;

        /**
         * Actual certificate.
         */
        protected Base64BinaryType blob;

        private static final long serialVersionUID = 2092655854L;

      public ConformanceRestSecurityCertificateComponent() {
        super();
      }

        /**
         * @return {@link #type} (Mime type for certificate.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public CodeType getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Mime type for certificate.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ConformanceRestSecurityCertificateComponent setTypeElement(CodeType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Mime type for certificate.
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Mime type for certificate.
         */
        public ConformanceRestSecurityCertificateComponent setType(String value) { 
          if (Utilities.noString(value))
            this.type = null;
          else {
            if (this.type == null)
              this.type = new CodeType();
            this.type.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #blob} (Actual certificate.). This is the underlying object with id, value and extensions. The accessor "getBlob" gives direct access to the value
         */
        public Base64BinaryType getBlobElement() { 
          return this.blob;
        }

        /**
         * @param value {@link #blob} (Actual certificate.). This is the underlying object with id, value and extensions. The accessor "getBlob" gives direct access to the value
         */
        public ConformanceRestSecurityCertificateComponent setBlobElement(Base64BinaryType value) { 
          this.blob = value;
          return this;
        }

        /**
         * @return Actual certificate.
         */
        public byte[] getBlob() { 
          return this.blob == null ? null : this.blob.getValue();
        }

        /**
         * @param value Actual certificate.
         */
        public ConformanceRestSecurityCertificateComponent setBlob(byte[] value) { 
          if (value == null)
            this.blob = null;
          else {
            if (this.blob == null)
              this.blob = new Base64BinaryType();
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
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.blob = blob == null ? null : blob.copy();
        return dst;
      }

  }

    public static class ConformanceRestResourceComponent extends BackboneElement {
        /**
         * A type of resource exposed via the restful interface.
         */
        protected CodeType type;

        /**
         * A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.
         */
        protected Reference profile;

        /**
         * The actual object that is the target of the reference (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        protected Profile profileTarget;

        /**
         * Identifies a restful operation supported by the solution.
         */
        protected List<ResourceInteractionComponent> interaction = new ArrayList<ResourceInteractionComponent>();

        /**
         * Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.
         */
        protected Enumeration<VersioningPolicy> versioning;

        /**
         * A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        protected BooleanType readHistory;

        /**
         * A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        protected BooleanType updateCreate;

        /**
         * A list of _include values supported by the server.
         */
        protected List<StringType> searchInclude = new ArrayList<StringType>();

        /**
         * Additional search parameters for implementations to support and/or make use of.
         */
        protected List<ConformanceRestResourceSearchParamComponent> searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();

        private static final long serialVersionUID = -1375327002L;

      public ConformanceRestResourceComponent() {
        super();
      }

      public ConformanceRestResourceComponent(CodeType type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (A type of resource exposed via the restful interface.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public CodeType getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A type of resource exposed via the restful interface.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ConformanceRestResourceComponent setTypeElement(CodeType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return A type of resource exposed via the restful interface.
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value A type of resource exposed via the restful interface.
         */
        public ConformanceRestResourceComponent setType(String value) { 
            if (this.type == null)
              this.type = new CodeType();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #profile} (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public Reference getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public ConformanceRestResourceComponent setProfile(Reference value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return {@link #profile} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public Profile getProfileTarget() { 
          return this.profileTarget;
        }

        /**
         * @param value {@link #profile} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.)
         */
        public ConformanceRestResourceComponent setProfileTarget(Profile value) { 
          this.profileTarget = value;
          return this;
        }

        /**
         * @return {@link #interaction} (Identifies a restful operation supported by the solution.)
         */
        public List<ResourceInteractionComponent> getInteraction() { 
          return this.interaction;
        }

        /**
         * @return {@link #interaction} (Identifies a restful operation supported by the solution.)
         */
    // syntactic sugar
        public ResourceInteractionComponent addInteraction() { //3
          ResourceInteractionComponent t = new ResourceInteractionComponent();
          this.interaction.add(t);
          return t;
        }

        /**
         * @return {@link #versioning} (Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.). This is the underlying object with id, value and extensions. The accessor "getVersioning" gives direct access to the value
         */
        public Enumeration<VersioningPolicy> getVersioningElement() { 
          return this.versioning;
        }

        /**
         * @param value {@link #versioning} (Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.). This is the underlying object with id, value and extensions. The accessor "getVersioning" gives direct access to the value
         */
        public ConformanceRestResourceComponent setVersioningElement(Enumeration<VersioningPolicy> value) { 
          this.versioning = value;
          return this;
        }

        /**
         * @return Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.
         */
        public VersioningPolicy getVersioning() { 
          return this.versioning == null ? null : this.versioning.getValue();
        }

        /**
         * @param value Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.
         */
        public ConformanceRestResourceComponent setVersioning(VersioningPolicy value) { 
          if (value == null)
            this.versioning = null;
          else {
            if (this.versioning == null)
              this.versioning = new Enumeration<VersioningPolicy>();
            this.versioning.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #readHistory} (A flag for whether the server is able to return past versions as part of the vRead operation.). This is the underlying object with id, value and extensions. The accessor "getReadHistory" gives direct access to the value
         */
        public BooleanType getReadHistoryElement() { 
          return this.readHistory;
        }

        /**
         * @param value {@link #readHistory} (A flag for whether the server is able to return past versions as part of the vRead operation.). This is the underlying object with id, value and extensions. The accessor "getReadHistory" gives direct access to the value
         */
        public ConformanceRestResourceComponent setReadHistoryElement(BooleanType value) { 
          this.readHistory = value;
          return this;
        }

        /**
         * @return A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        public boolean getReadHistory() { 
          return this.readHistory == null ? false : this.readHistory.getValue();
        }

        /**
         * @param value A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        public ConformanceRestResourceComponent setReadHistory(boolean value) { 
          if (value == false)
            this.readHistory = null;
          else {
            if (this.readHistory == null)
              this.readHistory = new BooleanType();
            this.readHistory.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #updateCreate} (A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.). This is the underlying object with id, value and extensions. The accessor "getUpdateCreate" gives direct access to the value
         */
        public BooleanType getUpdateCreateElement() { 
          return this.updateCreate;
        }

        /**
         * @param value {@link #updateCreate} (A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.). This is the underlying object with id, value and extensions. The accessor "getUpdateCreate" gives direct access to the value
         */
        public ConformanceRestResourceComponent setUpdateCreateElement(BooleanType value) { 
          this.updateCreate = value;
          return this;
        }

        /**
         * @return A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        public boolean getUpdateCreate() { 
          return this.updateCreate == null ? false : this.updateCreate.getValue();
        }

        /**
         * @param value A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.
         */
        public ConformanceRestResourceComponent setUpdateCreate(boolean value) { 
          if (value == false)
            this.updateCreate = null;
          else {
            if (this.updateCreate == null)
              this.updateCreate = new BooleanType();
            this.updateCreate.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public List<StringType> getSearchInclude() { 
          return this.searchInclude;
        }

        /**
         * @return {@link #searchInclude} (A list of _include values supported by the server.)
         */
    // syntactic sugar
        public StringType addSearchIncludeElement() {//2 
          StringType t = new StringType();
          this.searchInclude.add(t);
          return t;
        }

        /**
         * @param value {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public ConformanceRestResourceComponent addSearchInclude(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          this.searchInclude.add(t);
          return this;
        }

        /**
         * @param value {@link #searchInclude} (A list of _include values supported by the server.)
         */
        public boolean hasSearchInclude(String value) { 
          for (StringType v : this.searchInclude)
            if (v.equals(value)) // string
              return true;
          return false;
        }

        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
        public List<ConformanceRestResourceSearchParamComponent> getSearchParam() { 
          return this.searchParam;
        }

        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
    // syntactic sugar
        public ConformanceRestResourceSearchParamComponent addSearchParam() { //3
          ConformanceRestResourceSearchParamComponent t = new ConformanceRestResourceSearchParamComponent();
          this.searchParam.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "A type of resource exposed via the restful interface.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("profile", "Reference(Profile)", "A specification of the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.", 0, java.lang.Integer.MAX_VALUE, profile));
          childrenList.add(new Property("interaction", "", "Identifies a restful operation supported by the solution.", 0, java.lang.Integer.MAX_VALUE, interaction));
          childrenList.add(new Property("versioning", "code", "Thi field is set to true to specify that the system does not support (server) or use (client) versioning for this resource type. If this is not set to true, the server must at least correctly track and populate the versionId meta-property on resources.", 0, java.lang.Integer.MAX_VALUE, versioning));
          childrenList.add(new Property("readHistory", "boolean", "A flag for whether the server is able to return past versions as part of the vRead operation.", 0, java.lang.Integer.MAX_VALUE, readHistory));
          childrenList.add(new Property("updateCreate", "boolean", "A flag to indicate that the server allows the client to create new identities on the server. If the update operation is used (client) or allowed (server) to a new location where a resource doesn't already exist. This means that the server allows the client to create new identities on the server.", 0, java.lang.Integer.MAX_VALUE, updateCreate));
          childrenList.add(new Property("searchInclude", "string", "A list of _include values supported by the server.", 0, java.lang.Integer.MAX_VALUE, searchInclude));
          childrenList.add(new Property("searchParam", "", "Additional search parameters for implementations to support and/or make use of.", 0, java.lang.Integer.MAX_VALUE, searchParam));
        }

      public ConformanceRestResourceComponent copy() {
        ConformanceRestResourceComponent dst = new ConformanceRestResourceComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.profile = profile == null ? null : profile.copy();
        dst.interaction = new ArrayList<ResourceInteractionComponent>();
        for (ResourceInteractionComponent i : interaction)
          dst.interaction.add(i.copy());
        dst.versioning = versioning == null ? null : versioning.copy();
        dst.readHistory = readHistory == null ? null : readHistory.copy();
        dst.updateCreate = updateCreate == null ? null : updateCreate.copy();
        dst.searchInclude = new ArrayList<StringType>();
        for (StringType i : searchInclude)
          dst.searchInclude.add(i.copy());
        dst.searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();
        for (ConformanceRestResourceSearchParamComponent i : searchParam)
          dst.searchParam.add(i.copy());
        return dst;
      }

  }

    public static class ResourceInteractionComponent extends BackboneElement {
        /**
         * Coded identifier of the operation, supported by the system resource.
         */
        protected Enumeration<TypeRestfulInteraction> code;

        /**
         * Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        protected StringType documentation;

        private static final long serialVersionUID = -437507806L;

      public ResourceInteractionComponent() {
        super();
      }

      public ResourceInteractionComponent(Enumeration<TypeRestfulInteraction> code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Coded identifier of the operation, supported by the system resource.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public Enumeration<TypeRestfulInteraction> getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Coded identifier of the operation, supported by the system resource.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ResourceInteractionComponent setCodeElement(Enumeration<TypeRestfulInteraction> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Coded identifier of the operation, supported by the system resource.
         */
        public TypeRestfulInteraction getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Coded identifier of the operation, supported by the system resource.
         */
        public ResourceInteractionComponent setCode(TypeRestfulInteraction value) { 
            if (this.code == null)
              this.code = new Enumeration<TypeRestfulInteraction>();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ResourceInteractionComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        public ResourceInteractionComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Coded identifier of the operation, supported by the system resource.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("documentation", "string", "Guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public ResourceInteractionComponent copy() {
        ResourceInteractionComponent dst = new ResourceInteractionComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public static class ConformanceRestResourceSearchParamComponent extends BackboneElement {
        /**
         * The name of the search parameter used in the interface.
         */
        protected StringType name;

        /**
         * A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        protected UriType definition;

        /**
         * The type of value a search parameter refers to, and how the content is interpreted.
         */
        protected Enumeration<SearchParamType> type;

        /**
         * This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        protected StringType documentation;

        /**
         * Types of resource (if a resource is referenced).
         */
        protected List<CodeType> target = new ArrayList<CodeType>();

        /**
         * Chained names supported.
         */
        protected List<StringType> chain = new ArrayList<StringType>();

        private static final long serialVersionUID = -1498791020L;

      public ConformanceRestResourceSearchParamComponent() {
        super();
      }

      public ConformanceRestResourceSearchParamComponent(StringType name, Enumeration<SearchParamType> type) {
        super();
        this.name = name;
        this.type = type;
      }

        /**
         * @return {@link #name} (The name of the search parameter used in the interface.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the search parameter used in the interface.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public ConformanceRestResourceSearchParamComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the search parameter used in the interface.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the search parameter used in the interface.
         */
        public ConformanceRestResourceSearchParamComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #definition} (A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
         */
        public UriType getDefinitionElement() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
         */
        public ConformanceRestResourceSearchParamComponent setDefinitionElement(UriType value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        public String getDefinition() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        /**
         * @param value A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        public ConformanceRestResourceSearchParamComponent setDefinition(String value) { 
          if (Utilities.noString(value))
            this.definition = null;
          else {
            if (this.definition == null)
              this.definition = new UriType();
            this.definition.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<SearchParamType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ConformanceRestResourceSearchParamComponent setTypeElement(Enumeration<SearchParamType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of value a search parameter refers to, and how the content is interpreted.
         */
        public SearchParamType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of value a search parameter refers to, and how the content is interpreted.
         */
        public ConformanceRestResourceSearchParamComponent setType(SearchParamType value) { 
            if (this.type == null)
              this.type = new Enumeration<SearchParamType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ConformanceRestResourceSearchParamComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value This allows documentation of any distinct behaviors about how the search parameter is used.  For example, text matching algorithms.
         */
        public ConformanceRestResourceSearchParamComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public List<CodeType> getTarget() { 
          return this.target;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
    // syntactic sugar
        public CodeType addTargetElement() {//2 
          CodeType t = new CodeType();
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public ConformanceRestResourceSearchParamComponent addTarget(String value) { //1
          CodeType t = new CodeType();
          t.setValue(value);
          this.target.add(t);
          return this;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public boolean hasTarget(String value) { 
          for (CodeType v : this.target)
            if (v.equals(value)) // code
              return true;
          return false;
        }

        /**
         * @return {@link #chain} (Chained names supported.)
         */
        public List<StringType> getChain() { 
          return this.chain;
        }

        /**
         * @return {@link #chain} (Chained names supported.)
         */
    // syntactic sugar
        public StringType addChainElement() {//2 
          StringType t = new StringType();
          this.chain.add(t);
          return t;
        }

        /**
         * @param value {@link #chain} (Chained names supported.)
         */
        public ConformanceRestResourceSearchParamComponent addChain(String value) { //1
          StringType t = new StringType();
          t.setValue(value);
          this.chain.add(t);
          return this;
        }

        /**
         * @param value {@link #chain} (Chained names supported.)
         */
        public boolean hasChain(String value) { 
          for (StringType v : this.chain)
            if (v.equals(value)) // string
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
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.type = type == null ? null : type.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.target = new ArrayList<CodeType>();
        for (CodeType i : target)
          dst.target.add(i.copy());
        dst.chain = new ArrayList<StringType>();
        for (StringType i : chain)
          dst.chain.add(i.copy());
        return dst;
      }

  }

    public static class SystemInteractionComponent extends BackboneElement {
        /**
         * A coded identifier of the operation, supported by the system.
         */
        protected Enumeration<SystemRestfulInteraction> code;

        /**
         * Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        protected StringType documentation;

        private static final long serialVersionUID = 510675287L;

      public SystemInteractionComponent() {
        super();
      }

      public SystemInteractionComponent(Enumeration<SystemRestfulInteraction> code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (A coded identifier of the operation, supported by the system.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public Enumeration<SystemRestfulInteraction> getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A coded identifier of the operation, supported by the system.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public SystemInteractionComponent setCodeElement(Enumeration<SystemRestfulInteraction> value) { 
          this.code = value;
          return this;
        }

        /**
         * @return A coded identifier of the operation, supported by the system.
         */
        public SystemRestfulInteraction getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value A coded identifier of the operation, supported by the system.
         */
        public SystemInteractionComponent setCode(SystemRestfulInteraction value) { 
            if (this.code == null)
              this.code = new Enumeration<SystemRestfulInteraction>();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public SystemInteractionComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.
         */
        public SystemInteractionComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "A coded identifier of the operation, supported by the system.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("documentation", "string", "Guidance specific to the implementation of this operation, such as limitations on the kind of transactions allowed, or information about system wide search is implemented.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public SystemInteractionComponent copy() {
        SystemInteractionComponent dst = new SystemInteractionComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public static class ConformanceRestOperationComponent extends BackboneElement {
        /**
         * The name of a query, which is used in the _query parameter when the query is called.
         */
        protected StringType name;

        /**
         * Where the formal definition can be found.
         */
        protected Reference definition;

        /**
         * The actual object that is the target of the reference (Where the formal definition can be found.)
         */
        protected OperationDefinition definitionTarget;

        private static final long serialVersionUID = 122107272L;

      public ConformanceRestOperationComponent() {
        super();
      }

      public ConformanceRestOperationComponent(StringType name, Reference definition) {
        super();
        this.name = name;
        this.definition = definition;
      }

        /**
         * @return {@link #name} (The name of a query, which is used in the _query parameter when the query is called.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of a query, which is used in the _query parameter when the query is called.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public ConformanceRestOperationComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of a query, which is used in the _query parameter when the query is called.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of a query, which is used in the _query parameter when the query is called.
         */
        public ConformanceRestOperationComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #definition} (Where the formal definition can be found.)
         */
        public Reference getDefinition() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (Where the formal definition can be found.)
         */
        public ConformanceRestOperationComponent setDefinition(Reference value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return {@link #definition} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Where the formal definition can be found.)
         */
        public OperationDefinition getDefinitionTarget() { 
          return this.definitionTarget;
        }

        /**
         * @param value {@link #definition} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Where the formal definition can be found.)
         */
        public ConformanceRestOperationComponent setDefinitionTarget(OperationDefinition value) { 
          this.definitionTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of a query, which is used in the _query parameter when the query is called.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("definition", "Reference(OperationDefinition)", "Where the formal definition can be found.", 0, java.lang.Integer.MAX_VALUE, definition));
        }

      public ConformanceRestOperationComponent copy() {
        ConformanceRestOperationComponent dst = new ConformanceRestOperationComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.definition = definition == null ? null : definition.copy();
        return dst;
      }

  }

    public static class ConformanceMessagingComponent extends BackboneElement {
        /**
         * An address to which messages and/or replies are to be sent.
         */
        protected UriType endpoint;

        /**
         * Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        protected IntegerType reliableCache;

        /**
         * Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        protected StringType documentation;

        /**
         * A description of the solution's support for an event at this end point.
         */
        protected List<ConformanceMessagingEventComponent> event = new ArrayList<ConformanceMessagingEventComponent>();

        private static final long serialVersionUID = -1845309943L;

      public ConformanceMessagingComponent() {
        super();
      }

        /**
         * @return {@link #endpoint} (An address to which messages and/or replies are to be sent.). This is the underlying object with id, value and extensions. The accessor "getEndpoint" gives direct access to the value
         */
        public UriType getEndpointElement() { 
          return this.endpoint;
        }

        /**
         * @param value {@link #endpoint} (An address to which messages and/or replies are to be sent.). This is the underlying object with id, value and extensions. The accessor "getEndpoint" gives direct access to the value
         */
        public ConformanceMessagingComponent setEndpointElement(UriType value) { 
          this.endpoint = value;
          return this;
        }

        /**
         * @return An address to which messages and/or replies are to be sent.
         */
        public String getEndpoint() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        /**
         * @param value An address to which messages and/or replies are to be sent.
         */
        public ConformanceMessagingComponent setEndpoint(String value) { 
          if (Utilities.noString(value))
            this.endpoint = null;
          else {
            if (this.endpoint == null)
              this.endpoint = new UriType();
            this.endpoint.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #reliableCache} (Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).). This is the underlying object with id, value and extensions. The accessor "getReliableCache" gives direct access to the value
         */
        public IntegerType getReliableCacheElement() { 
          return this.reliableCache;
        }

        /**
         * @param value {@link #reliableCache} (Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).). This is the underlying object with id, value and extensions. The accessor "getReliableCache" gives direct access to the value
         */
        public ConformanceMessagingComponent setReliableCacheElement(IntegerType value) { 
          this.reliableCache = value;
          return this;
        }

        /**
         * @return Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        public int getReliableCache() { 
          return this.reliableCache == null ? null : this.reliableCache.getValue();
        }

        /**
         * @param value Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        public ConformanceMessagingComponent setReliableCache(int value) { 
          if (value == -1)
            this.reliableCache = null;
          else {
            if (this.reliableCache == null)
              this.reliableCache = new IntegerType();
            this.reliableCache.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #documentation} (Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ConformanceMessagingComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        public ConformanceMessagingComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
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

        /**
         * @return {@link #event} (A description of the solution's support for an event at this end point.)
         */
    // syntactic sugar
        public ConformanceMessagingEventComponent addEvent() { //3
          ConformanceMessagingEventComponent t = new ConformanceMessagingEventComponent();
          this.event.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("endpoint", "uri", "An address to which messages and/or replies are to be sent.", 0, java.lang.Integer.MAX_VALUE, endpoint));
          childrenList.add(new Property("reliableCache", "integer", "Length if the receiver's reliable messaging cache in minutes (if a receiver) or how long the cache length on the receiver should be (if a sender).", 0, java.lang.Integer.MAX_VALUE, reliableCache));
          childrenList.add(new Property("documentation", "string", "Documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("event", "", "A description of the solution's support for an event at this end point.", 0, java.lang.Integer.MAX_VALUE, event));
        }

      public ConformanceMessagingComponent copy() {
        ConformanceMessagingComponent dst = new ConformanceMessagingComponent();
        copyValues(dst);
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
        protected CodeType focus;

        /**
         * Information about the request for this event.
         */
        protected Reference request;

        /**
         * The actual object that is the target of the reference (Information about the request for this event.)
         */
        protected Profile requestTarget;

        /**
         * Information about the response for this event.
         */
        protected Reference response;

        /**
         * The actual object that is the target of the reference (Information about the response for this event.)
         */
        protected Profile responseTarget;

        /**
         * Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        protected StringType documentation;

        private static final long serialVersionUID = 1554067488L;

      public ConformanceMessagingEventComponent() {
        super();
      }

      public ConformanceMessagingEventComponent(Coding code, Enumeration<MessageConformanceEventMode> mode, CodeType focus, Reference request, Reference response) {
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
         * @return {@link #category} (The impact of the content of the message.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
         */
        public Enumeration<MessageSignificanceCategory> getCategoryElement() { 
          return this.category;
        }

        /**
         * @param value {@link #category} (The impact of the content of the message.). This is the underlying object with id, value and extensions. The accessor "getCategory" gives direct access to the value
         */
        public ConformanceMessagingEventComponent setCategoryElement(Enumeration<MessageSignificanceCategory> value) { 
          this.category = value;
          return this;
        }

        /**
         * @return The impact of the content of the message.
         */
        public MessageSignificanceCategory getCategory() { 
          return this.category == null ? null : this.category.getValue();
        }

        /**
         * @param value The impact of the content of the message.
         */
        public ConformanceMessagingEventComponent setCategory(MessageSignificanceCategory value) { 
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
         * @return {@link #mode} (The mode of this event declaration - whether application is sender or receiver.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public Enumeration<MessageConformanceEventMode> getModeElement() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (The mode of this event declaration - whether application is sender or receiver.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public ConformanceMessagingEventComponent setModeElement(Enumeration<MessageConformanceEventMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return The mode of this event declaration - whether application is sender or receiver.
         */
        public MessageConformanceEventMode getMode() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value The mode of this event declaration - whether application is sender or receiver.
         */
        public ConformanceMessagingEventComponent setMode(MessageConformanceEventMode value) { 
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

        /**
         * @return {@link #protocol} (A list of the messaging transport protocol(s) identifiers, supported by this endpoint.)
         */
    // syntactic sugar
        public Coding addProtocol() { //3
          Coding t = new Coding();
          this.protocol.add(t);
          return t;
        }

        /**
         * @return {@link #focus} (A resource associated with the event.  This is the resource that defines the event.). This is the underlying object with id, value and extensions. The accessor "getFocus" gives direct access to the value
         */
        public CodeType getFocusElement() { 
          return this.focus;
        }

        /**
         * @param value {@link #focus} (A resource associated with the event.  This is the resource that defines the event.). This is the underlying object with id, value and extensions. The accessor "getFocus" gives direct access to the value
         */
        public ConformanceMessagingEventComponent setFocusElement(CodeType value) { 
          this.focus = value;
          return this;
        }

        /**
         * @return A resource associated with the event.  This is the resource that defines the event.
         */
        public String getFocus() { 
          return this.focus == null ? null : this.focus.getValue();
        }

        /**
         * @param value A resource associated with the event.  This is the resource that defines the event.
         */
        public ConformanceMessagingEventComponent setFocus(String value) { 
            if (this.focus == null)
              this.focus = new CodeType();
            this.focus.setValue(value);
          return this;
        }

        /**
         * @return {@link #request} (Information about the request for this event.)
         */
        public Reference getRequest() { 
          return this.request;
        }

        /**
         * @param value {@link #request} (Information about the request for this event.)
         */
        public ConformanceMessagingEventComponent setRequest(Reference value) { 
          this.request = value;
          return this;
        }

        /**
         * @return {@link #request} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Information about the request for this event.)
         */
        public Profile getRequestTarget() { 
          return this.requestTarget;
        }

        /**
         * @param value {@link #request} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Information about the request for this event.)
         */
        public ConformanceMessagingEventComponent setRequestTarget(Profile value) { 
          this.requestTarget = value;
          return this;
        }

        /**
         * @return {@link #response} (Information about the response for this event.)
         */
        public Reference getResponse() { 
          return this.response;
        }

        /**
         * @param value {@link #response} (Information about the response for this event.)
         */
        public ConformanceMessagingEventComponent setResponse(Reference value) { 
          this.response = value;
          return this;
        }

        /**
         * @return {@link #response} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Information about the response for this event.)
         */
        public Profile getResponseTarget() { 
          return this.responseTarget;
        }

        /**
         * @param value {@link #response} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Information about the response for this event.)
         */
        public ConformanceMessagingEventComponent setResponseTarget(Profile value) { 
          this.responseTarget = value;
          return this;
        }

        /**
         * @return {@link #documentation} (Guidance on how this event is handled, such as internal system trigger points, business rules, etc.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Guidance on how this event is handled, such as internal system trigger points, business rules, etc.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ConformanceMessagingEventComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        public ConformanceMessagingEventComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
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
          childrenList.add(new Property("request", "Reference(Profile)", "Information about the request for this event.", 0, java.lang.Integer.MAX_VALUE, request));
          childrenList.add(new Property("response", "Reference(Profile)", "Information about the response for this event.", 0, java.lang.Integer.MAX_VALUE, response));
          childrenList.add(new Property("documentation", "string", "Guidance on how this event is handled, such as internal system trigger points, business rules, etc.", 0, java.lang.Integer.MAX_VALUE, documentation));
        }

      public ConformanceMessagingEventComponent copy() {
        ConformanceMessagingEventComponent dst = new ConformanceMessagingEventComponent();
        copyValues(dst);
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
        protected StringType documentation;

        /**
         * A constraint on a resource used in the document.
         */
        protected Reference profile;

        /**
         * The actual object that is the target of the reference (A constraint on a resource used in the document.)
         */
        protected Profile profileTarget;

        private static final long serialVersionUID = 437404016L;

      public ConformanceDocumentComponent() {
        super();
      }

      public ConformanceDocumentComponent(Enumeration<DocumentMode> mode, Reference profile) {
        super();
        this.mode = mode;
        this.profile = profile;
      }

        /**
         * @return {@link #mode} (Mode of this document declaration - whether application is producer or consumer.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public Enumeration<DocumentMode> getModeElement() { 
          return this.mode;
        }

        /**
         * @param value {@link #mode} (Mode of this document declaration - whether application is producer or consumer.). This is the underlying object with id, value and extensions. The accessor "getMode" gives direct access to the value
         */
        public ConformanceDocumentComponent setModeElement(Enumeration<DocumentMode> value) { 
          this.mode = value;
          return this;
        }

        /**
         * @return Mode of this document declaration - whether application is producer or consumer.
         */
        public DocumentMode getMode() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        /**
         * @param value Mode of this document declaration - whether application is producer or consumer.
         */
        public ConformanceDocumentComponent setMode(DocumentMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<DocumentMode>();
            this.mode.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ConformanceDocumentComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        public ConformanceDocumentComponent setDocumentation(String value) { 
          if (Utilities.noString(value))
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #profile} (A constraint on a resource used in the document.)
         */
        public Reference getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (A constraint on a resource used in the document.)
         */
        public ConformanceDocumentComponent setProfile(Reference value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return {@link #profile} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (A constraint on a resource used in the document.)
         */
        public Profile getProfileTarget() { 
          return this.profileTarget;
        }

        /**
         * @param value {@link #profile} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (A constraint on a resource used in the document.)
         */
        public ConformanceDocumentComponent setProfileTarget(Profile value) { 
          this.profileTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("mode", "code", "Mode of this document declaration - whether application is producer or consumer.", 0, java.lang.Integer.MAX_VALUE, mode));
          childrenList.add(new Property("documentation", "string", "A description of how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("profile", "Reference(Profile)", "A constraint on a resource used in the document.", 0, java.lang.Integer.MAX_VALUE, profile));
        }

      public ConformanceDocumentComponent copy() {
        ConformanceDocumentComponent dst = new ConformanceDocumentComponent();
        copyValues(dst);
        dst.mode = mode == null ? null : mode.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.profile = profile == null ? null : profile.copy();
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected StringType identifier;

    /**
     * The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected StringType version;

    /**
     * A free text natural language name identifying the conformance statement.
     */
    protected StringType name;

    /**
     * Name of Organization publishing this conformance statement.
     */
    protected StringType publisher;

    /**
     * Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.
     */
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    protected StringType description;

    /**
     * The status of this conformance statement.
     */
    protected Enumeration<ConformanceStatementStatus> status;

    /**
     * A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected BooleanType experimental;

    /**
     * The date  (and optionally time) when the conformance statement was published.
     */
    protected DateTimeType date;

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
    protected IdType fhirVersion;

    /**
     * A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    protected BooleanType acceptUnknown;

    /**
     * A list of the formats supported by this implementation.
     */
    protected List<CodeType> format = new ArrayList<CodeType>();

    /**
     * A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.
     */
    protected List<Reference> profile = new ArrayList<Reference>();
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

    private static final long serialVersionUID = 277150712L;

    public Conformance() {
      super();
    }

    public Conformance(StringType publisher, DateTimeType date, IdType fhirVersion, BooleanType acceptUnknown) {
      super();
      this.publisher = publisher;
      this.date = date;
      this.fhirVersion = fhirVersion;
      this.acceptUnknown = acceptUnknown;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public StringType getIdentifierElement() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public Conformance setIdentifierElement(StringType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifier() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this conformance statement when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public Conformance setIdentifier(String value) { 
      if (Utilities.noString(value))
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new StringType();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public Conformance setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the conformance statement when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public Conformance setVersion(String value) { 
      if (Utilities.noString(value))
        this.version = null;
      else {
        if (this.version == null)
          this.version = new StringType();
        this.version.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #name} (A free text natural language name identifying the conformance statement.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name identifying the conformance statement.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public Conformance setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name identifying the conformance statement.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name identifying the conformance statement.
     */
    public Conformance setName(String value) { 
      if (Utilities.noString(value))
        this.name = null;
      else {
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #publisher} (Name of Organization publishing this conformance statement.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Name of Organization publishing this conformance statement.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public Conformance setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Name of Organization publishing this conformance statement.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Name of Organization publishing this conformance statement.
     */
    public Conformance setPublisher(String value) { 
        if (this.publisher == null)
          this.publisher = new StringType();
        this.publisher.setValue(value);
      return this;
    }

    /**
     * @return {@link #telecom} (Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.)
     */
    public List<ContactPoint> getTelecom() { 
      return this.telecom;
    }

    /**
     * @return {@link #telecom} (Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.)
     */
    // syntactic sugar
    public ContactPoint addTelecom() { //3
      ContactPoint t = new ContactPoint();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #description} (A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public Conformance setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.
     */
    public Conformance setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of this conformance statement.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ConformanceStatementStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of this conformance statement.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Conformance setStatusElement(Enumeration<ConformanceStatementStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of this conformance statement.
     */
    public ConformanceStatementStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of this conformance statement.
     */
    public Conformance setStatus(ConformanceStatementStatus value) { 
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
     * @return {@link #experimental} (A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public BooleanType getExperimentalElement() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public Conformance setExperimentalElement(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimental() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public Conformance setExperimental(boolean value) { 
      if (value == false)
        this.experimental = null;
      else {
        if (this.experimental == null)
          this.experimental = new BooleanType();
        this.experimental.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #date} (The date  (and optionally time) when the conformance statement was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date  (and optionally time) when the conformance statement was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public Conformance setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date  (and optionally time) when the conformance statement was published.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date  (and optionally time) when the conformance statement was published.
     */
    public Conformance setDate(DateAndTime value) { 
        if (this.date == null)
          this.date = new DateTimeType();
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
     * @return {@link #fhirVersion} (The version of the FHIR specification on which this conformance statement is based.). This is the underlying object with id, value and extensions. The accessor "getFhirVersion" gives direct access to the value
     */
    public IdType getFhirVersionElement() { 
      return this.fhirVersion;
    }

    /**
     * @param value {@link #fhirVersion} (The version of the FHIR specification on which this conformance statement is based.). This is the underlying object with id, value and extensions. The accessor "getFhirVersion" gives direct access to the value
     */
    public Conformance setFhirVersionElement(IdType value) { 
      this.fhirVersion = value;
      return this;
    }

    /**
     * @return The version of the FHIR specification on which this conformance statement is based.
     */
    public String getFhirVersion() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    /**
     * @param value The version of the FHIR specification on which this conformance statement is based.
     */
    public Conformance setFhirVersion(String value) { 
        if (this.fhirVersion == null)
          this.fhirVersion = new IdType();
        this.fhirVersion.setValue(value);
      return this;
    }

    /**
     * @return {@link #acceptUnknown} (A flag that indicates whether the application accepts unknown elements as part of a resource.). This is the underlying object with id, value and extensions. The accessor "getAcceptUnknown" gives direct access to the value
     */
    public BooleanType getAcceptUnknownElement() { 
      return this.acceptUnknown;
    }

    /**
     * @param value {@link #acceptUnknown} (A flag that indicates whether the application accepts unknown elements as part of a resource.). This is the underlying object with id, value and extensions. The accessor "getAcceptUnknown" gives direct access to the value
     */
    public Conformance setAcceptUnknownElement(BooleanType value) { 
      this.acceptUnknown = value;
      return this;
    }

    /**
     * @return A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    public boolean getAcceptUnknown() { 
      return this.acceptUnknown == null ? false : this.acceptUnknown.getValue();
    }

    /**
     * @param value A flag that indicates whether the application accepts unknown elements as part of a resource.
     */
    public Conformance setAcceptUnknown(boolean value) { 
        if (this.acceptUnknown == null)
          this.acceptUnknown = new BooleanType();
        this.acceptUnknown.setValue(value);
      return this;
    }

    /**
     * @return {@link #format} (A list of the formats supported by this implementation.)
     */
    public List<CodeType> getFormat() { 
      return this.format;
    }

    /**
     * @return {@link #format} (A list of the formats supported by this implementation.)
     */
    // syntactic sugar
    public CodeType addFormatElement() {//2 
      CodeType t = new CodeType();
      this.format.add(t);
      return t;
    }

    /**
     * @param value {@link #format} (A list of the formats supported by this implementation.)
     */
    public Conformance addFormat(String value) { //1
      CodeType t = new CodeType();
      t.setValue(value);
      this.format.add(t);
      return this;
    }

    /**
     * @param value {@link #format} (A list of the formats supported by this implementation.)
     */
    public boolean hasFormat(String value) { 
      for (CodeType v : this.format)
        if (v.equals(value)) // code
          return true;
      return false;
    }

    /**
     * @return {@link #profile} (A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public List<Reference> getProfile() { 
      return this.profile;
    }

    /**
     * @return {@link #profile} (A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    // syntactic sugar
    public Reference addProfile() { //3
      Reference t = new Reference();
      this.profile.add(t);
      return t;
    }

    /**
     * @return {@link #profile} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
     */
    public List<Profile> getProfileTarget() { 
      return this.profileTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #profile} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. A list of profiles supported by the system. For a server, "supported by the system" means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.)
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

    /**
     * @return {@link #rest} (A definition of the restful capabilities of the solution, if any.)
     */
    // syntactic sugar
    public ConformanceRestComponent addRest() { //3
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

    /**
     * @return {@link #messaging} (A description of the messaging capabilities of the solution.)
     */
    // syntactic sugar
    public ConformanceMessagingComponent addMessaging() { //3
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

    /**
     * @return {@link #document} (A document definition.)
     */
    // syntactic sugar
    public ConformanceDocumentComponent addDocument() { //3
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
        childrenList.add(new Property("telecom", "ContactPoint", "Contacts for Organization relevant to this conformance statement.  The contacts may be a website, email, phone numbers, etc.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the conformance statement and its use. Typically, this is used when the profile describes a desired rather than an actual solution, for example as a formal expression of requirements as part of an RFP.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("status", "code", "The status of this conformance statement.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "A flag to indicate that this conformance statement is authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date  (and optionally time) when the conformance statement was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("software", "", "Software that is covered by this conformance statement.  It is used when the profile describes the capabilities of a particular software version, independent of an installation.", 0, java.lang.Integer.MAX_VALUE, software));
        childrenList.add(new Property("implementation", "", "Identifies a specific implementation instance that is described by the conformance statement - i.e. a particular installation, rather than the capabilities of a software program.", 0, java.lang.Integer.MAX_VALUE, implementation));
        childrenList.add(new Property("fhirVersion", "id", "The version of the FHIR specification on which this conformance statement is based.", 0, java.lang.Integer.MAX_VALUE, fhirVersion));
        childrenList.add(new Property("acceptUnknown", "boolean", "A flag that indicates whether the application accepts unknown elements as part of a resource.", 0, java.lang.Integer.MAX_VALUE, acceptUnknown));
        childrenList.add(new Property("format", "code", "A list of the formats supported by this implementation.", 0, java.lang.Integer.MAX_VALUE, format));
        childrenList.add(new Property("profile", "Reference(Profile)", "A list of profiles supported by the system. For a server, 'supported by the system' means the system hosts/produces a set of recourses, conformant to a particular profile, and allows its clients to search using this profile and to find appropriate data. For a client, it means the system will search by this profile and process data according to the guidance implicit in the profile.", 0, java.lang.Integer.MAX_VALUE, profile));
        childrenList.add(new Property("rest", "", "A definition of the restful capabilities of the solution, if any.", 0, java.lang.Integer.MAX_VALUE, rest));
        childrenList.add(new Property("messaging", "", "A description of the messaging capabilities of the solution.", 0, java.lang.Integer.MAX_VALUE, messaging));
        childrenList.add(new Property("document", "", "A document definition.", 0, java.lang.Integer.MAX_VALUE, document));
      }

      public Conformance copy() {
        Conformance dst = new Conformance();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
          dst.telecom.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.status = status == null ? null : status.copy();
        dst.experimental = experimental == null ? null : experimental.copy();
        dst.date = date == null ? null : date.copy();
        dst.software = software == null ? null : software.copy();
        dst.implementation = implementation == null ? null : implementation.copy();
        dst.fhirVersion = fhirVersion == null ? null : fhirVersion.copy();
        dst.acceptUnknown = acceptUnknown == null ? null : acceptUnknown.copy();
        dst.format = new ArrayList<CodeType>();
        for (CodeType i : format)
          dst.format.add(i.copy());
        dst.profile = new ArrayList<Reference>();
        for (Reference i : profile)
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

