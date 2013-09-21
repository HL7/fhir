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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * A conformance statement about how an application or implementation supports FHIR or the set of requirements for a desired implementation.
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

  public class ConformanceStatementStatusEnumFactory implements EnumFactory {
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

  public class RestfulConformanceModeEnumFactory implements EnumFactory {
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

    public enum RestfulOperation {
        read, // Read the current state of the resource.
        vread, // Read the state of a specific version of the resource.
        update, // Update an existing resource by its id (or create it if it is new).
        delete, // Delete a resource.
        historyinstance, // Retrieve the update history for a resource instance.
        validate, // Check that the content would be acceptable as an update.
        historytype, // Get a list of updates to resources of this type.
        create, // Create a new resource with a server assigned id.
        search, // Supports search operations using the parameters described in the profile.
        transaction, // Transaction performed on multiple resources.
        historysystem, // Get a list of updates to all resources on the system.
        Null; // added to help the parsers
        public static RestfulOperation fromCode(String codeString) throws Exception {
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
        if ("search".equals(codeString))
          return search;
        if ("transaction".equals(codeString))
          return transaction;
        if ("history-system".equals(codeString))
          return historysystem;
        throw new Exception("Unknown RestfulOperation code '"+codeString+"'");
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
            case search: return "search";
            case transaction: return "transaction";
            case historysystem: return "history-system";
            default: return "?";
          }
        }
    }

  public class RestfulOperationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("read".equals(codeString))
          return RestfulOperation.read;
        if ("vread".equals(codeString))
          return RestfulOperation.vread;
        if ("update".equals(codeString))
          return RestfulOperation.update;
        if ("delete".equals(codeString))
          return RestfulOperation.delete;
        if ("history-instance".equals(codeString))
          return RestfulOperation.historyinstance;
        if ("validate".equals(codeString))
          return RestfulOperation.validate;
        if ("history-type".equals(codeString))
          return RestfulOperation.historytype;
        if ("create".equals(codeString))
          return RestfulOperation.create;
        if ("search".equals(codeString))
          return RestfulOperation.search;
        if ("transaction".equals(codeString))
          return RestfulOperation.transaction;
        if ("history-system".equals(codeString))
          return RestfulOperation.historysystem;
        throw new Exception("Unknown RestfulOperation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == RestfulOperation.read)
        return "read";
      if (code == RestfulOperation.vread)
        return "vread";
      if (code == RestfulOperation.update)
        return "update";
      if (code == RestfulOperation.delete)
        return "delete";
      if (code == RestfulOperation.historyinstance)
        return "history-instance";
      if (code == RestfulOperation.validate)
        return "validate";
      if (code == RestfulOperation.historytype)
        return "history-type";
      if (code == RestfulOperation.create)
        return "create";
      if (code == RestfulOperation.search)
        return "search";
      if (code == RestfulOperation.transaction)
        return "transaction";
      if (code == RestfulOperation.historysystem)
        return "history-system";
      return "?";
      }
    }

    public enum SearchParamType {
        integer, // Search parameter must be a simple whole number.
        string, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces and are delineated by double quotes, e.g. "van Zanten".
        text, // Search parameter is on a long string. Used for text filter type search: it functions on searches within a body of text and may contain spaces to separate words. May match even if the separate words are found out of order. Text parameters are delineated by double quotes.
        date, // Search parameter is on a date (and should support :before and :after modifiers). The date format is the standard XML format, though other formats may be supported.
        token, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "/", depending on the modifier used.
        reference, // A pair of resource type and resource id, separated by "/". Matches when the resource reference resolves to a resource of the given type and id. The resource type may be omitted to search all types if used with the modifier :any.
        composite, // A composite search parameter that combines other search parameters together.
        Null; // added to help the parsers
        public static SearchParamType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("integer".equals(codeString))
          return integer;
        if ("string".equals(codeString))
          return string;
        if ("text".equals(codeString))
          return text;
        if ("date".equals(codeString))
          return date;
        if ("token".equals(codeString))
          return token;
        if ("reference".equals(codeString))
          return reference;
        if ("composite".equals(codeString))
          return composite;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case integer: return "integer";
            case string: return "string";
            case text: return "text";
            case date: return "date";
            case token: return "token";
            case reference: return "reference";
            case composite: return "composite";
            default: return "?";
          }
        }
    }

  public class SearchParamTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("integer".equals(codeString))
          return SearchParamType.integer;
        if ("string".equals(codeString))
          return SearchParamType.string;
        if ("text".equals(codeString))
          return SearchParamType.text;
        if ("date".equals(codeString))
          return SearchParamType.date;
        if ("token".equals(codeString))
          return SearchParamType.token;
        if ("reference".equals(codeString))
          return SearchParamType.reference;
        if ("composite".equals(codeString))
          return SearchParamType.composite;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SearchParamType.integer)
        return "integer";
      if (code == SearchParamType.string)
        return "string";
      if (code == SearchParamType.text)
        return "text";
      if (code == SearchParamType.date)
        return "date";
      if (code == SearchParamType.token)
        return "token";
      if (code == SearchParamType.reference)
        return "reference";
      if (code == SearchParamType.composite)
        return "composite";
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

  public class MessageConformanceEventModeEnumFactory implements EnumFactory {
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

  public class DocumentModeEnumFactory implements EnumFactory {
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

    public class ConformanceSoftwareComponent extends Element {
        /**
         * Name software is known by.
         */
        protected String_ name;

        /**
         * Version covered by this statement.
         */
        protected String_ version;

        /**
         * Date this version of the software released.
         */
        protected DateTime releaseDate;

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
        }

        public String_ getVersion() { 
          return this.version;
        }

        public void setVersion(String_ value) { 
          this.version = value;
        }

        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        public void setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new String_();
            this.version.setValue(value);
          }
        }

        public DateTime getReleaseDate() { 
          return this.releaseDate;
        }

        public void setReleaseDate(DateTime value) { 
          this.releaseDate = value;
        }

        public String getReleaseDateSimple() { 
          return this.releaseDate == null ? null : this.releaseDate.getValue();
        }

        public void setReleaseDateSimple(String value) { 
          if (value == null)
            this.releaseDate = null;
          else {
            if (this.releaseDate == null)
              this.releaseDate = new DateTime();
            this.releaseDate.setValue(value);
          }
        }

      public ConformanceSoftwareComponent copy(Conformance e) {
        ConformanceSoftwareComponent dst = e.new ConformanceSoftwareComponent();
        dst.name = name == null ? null : name.copy();
        dst.version = version == null ? null : version.copy();
        dst.releaseDate = releaseDate == null ? null : releaseDate.copy();
        return dst;
      }

  }

    public class ConformanceImplementationComponent extends Element {
        /**
         * Information about the specific installation that this conformance statement relates to.
         */
        protected String_ description;

        /**
         * The base URL for the implementation.  This forms the base for REST interfaces as well as the mailbox and document interfaces.
         */
        protected Uri url;

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
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
        }

        public Uri getUrl() { 
          return this.url;
        }

        public void setUrl(Uri value) { 
          this.url = value;
        }

        public String getUrlSimple() { 
          return this.url == null ? null : this.url.getValue();
        }

        public void setUrlSimple(String value) { 
          if (value == null)
            this.url = null;
          else {
            if (this.url == null)
              this.url = new Uri();
            this.url.setValue(value);
          }
        }

      public ConformanceImplementationComponent copy(Conformance e) {
        ConformanceImplementationComponent dst = e.new ConformanceImplementationComponent();
        dst.description = description == null ? null : description.copy();
        dst.url = url == null ? null : url.copy();
        return dst;
      }

  }

    public class ConformanceRestComponent extends Element {
        /**
         * Identifies whether this portion of the statement is describing ability to initiate or receive restful operations.
         */
        protected Enumeration<RestfulConformanceMode> mode;

        /**
         * Provides documentation about the system's restful capabilities that apply across all applications, such as security.
         */
        protected String_ documentation;

        /**
         * Information about security of implementation.
         */
        protected ConformanceRestSecurityComponent security;

        /**
         * Identifies the restful capabilities of the solution for a specific resource type.
         */
        protected List<ConformanceRestResourceComponent> resource = new ArrayList<ConformanceRestResourceComponent>();

        /**
         * If batches are supported.
         */
        protected Boolean batch;

        /**
         * If a system wide history list is supported.
         */
        protected Boolean history;

        /**
         * Definition of a named query and its parameters and their meaning.
         */
        protected List<ConformanceRestQueryComponent> query = new ArrayList<ConformanceRestQueryComponent>();

        public Enumeration<RestfulConformanceMode> getMode() { 
          return this.mode;
        }

        public void setMode(Enumeration<RestfulConformanceMode> value) { 
          this.mode = value;
        }

        public RestfulConformanceMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        public void setModeSimple(RestfulConformanceMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<RestfulConformanceMode>();
            this.mode.setValue(value);
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
        }

        public ConformanceRestSecurityComponent getSecurity() { 
          return this.security;
        }

        public void setSecurity(ConformanceRestSecurityComponent value) { 
          this.security = value;
        }

        public List<ConformanceRestResourceComponent> getResource() { 
          return this.resource;
        }

    // syntactic sugar
        public ConformanceRestResourceComponent addResource() { 
          ConformanceRestResourceComponent t = new ConformanceRestResourceComponent();
          this.resource.add(t);
          return t;
        }

        public Boolean getBatch() { 
          return this.batch;
        }

        public void setBatch(Boolean value) { 
          this.batch = value;
        }

        public boolean getBatchSimple() { 
          return this.batch == null ? null : this.batch.getValue();
        }

        public void setBatchSimple(boolean value) { 
          if (value == false)
            this.batch = null;
          else {
            if (this.batch == null)
              this.batch = new Boolean();
            this.batch.setValue(value);
          }
        }

        public Boolean getHistory() { 
          return this.history;
        }

        public void setHistory(Boolean value) { 
          this.history = value;
        }

        public boolean getHistorySimple() { 
          return this.history == null ? null : this.history.getValue();
        }

        public void setHistorySimple(boolean value) { 
          if (value == false)
            this.history = null;
          else {
            if (this.history == null)
              this.history = new Boolean();
            this.history.setValue(value);
          }
        }

        public List<ConformanceRestQueryComponent> getQuery() { 
          return this.query;
        }

    // syntactic sugar
        public ConformanceRestQueryComponent addQuery() { 
          ConformanceRestQueryComponent t = new ConformanceRestQueryComponent();
          this.query.add(t);
          return t;
        }

      public ConformanceRestComponent copy(Conformance e) {
        ConformanceRestComponent dst = e.new ConformanceRestComponent();
        dst.mode = mode == null ? null : mode.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.security = security == null ? null : security.copy(e);
        dst.resource = new ArrayList<ConformanceRestResourceComponent>();
        for (ConformanceRestResourceComponent i : resource)
          dst.resource.add(i.copy(e));
        dst.batch = batch == null ? null : batch.copy();
        dst.history = history == null ? null : history.copy();
        dst.query = new ArrayList<ConformanceRestQueryComponent>();
        for (ConformanceRestQueryComponent i : query)
          dst.query.add(i.copy(e));
        return dst;
      }

  }

    public class ConformanceRestSecurityComponent extends Element {
        /**
         * What type of security services are supported/required.
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

        public List<CodeableConcept> getService() { 
          return this.service;
        }

    // syntactic sugar
        public CodeableConcept addService() { 
          CodeableConcept t = new CodeableConcept();
          this.service.add(t);
          return t;
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

        public List<ConformanceRestSecurityCertificateComponent> getCertificate() { 
          return this.certificate;
        }

    // syntactic sugar
        public ConformanceRestSecurityCertificateComponent addCertificate() { 
          ConformanceRestSecurityCertificateComponent t = new ConformanceRestSecurityCertificateComponent();
          this.certificate.add(t);
          return t;
        }

      public ConformanceRestSecurityComponent copy(Conformance e) {
        ConformanceRestSecurityComponent dst = e.new ConformanceRestSecurityComponent();
        dst.service = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : service)
          dst.service.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.certificate = new ArrayList<ConformanceRestSecurityCertificateComponent>();
        for (ConformanceRestSecurityCertificateComponent i : certificate)
          dst.certificate.add(i.copy(e));
        return dst;
      }

  }

    public class ConformanceRestSecurityCertificateComponent extends Element {
        /**
         * Mime type for certificate.
         */
        protected Code type;

        /**
         * Actual certificate.
         */
        protected Base64Binary blob;

        public Code getType() { 
          return this.type;
        }

        public void setType(Code value) { 
          this.type = value;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(String value) { 
          if (value == null)
            this.type = null;
          else {
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
          }
        }

        public Base64Binary getBlob() { 
          return this.blob;
        }

        public void setBlob(Base64Binary value) { 
          this.blob = value;
        }

        public byte[] getBlobSimple() { 
          return this.blob == null ? null : this.blob.getValue();
        }

        public void setBlobSimple(byte[] value) { 
          if (value == null)
            this.blob = null;
          else {
            if (this.blob == null)
              this.blob = new Base64Binary();
            this.blob.setValue(value);
          }
        }

      public ConformanceRestSecurityCertificateComponent copy(Conformance e) {
        ConformanceRestSecurityCertificateComponent dst = e.new ConformanceRestSecurityCertificateComponent();
        dst.type = type == null ? null : type.copy();
        dst.blob = blob == null ? null : blob.copy();
        return dst;
      }

  }

    public class ConformanceRestResourceComponent extends Element {
        /**
         * Identifies the resource exposed via the restful interface.
         */
        protected Code type;

        /**
         * Identifies the profile that describes the solution's support for the resource, including any constraints on cardinality, bindings, lengths or other limitations.
         */
        protected ResourceReference profile;

        /**
         * Identifies a restful operation supported by the solution.
         */
        protected List<ConformanceRestResourceOperationComponent> operation = new ArrayList<ConformanceRestResourceOperationComponent>();

        /**
         * A flag for whether the server is able to return past versions as part of the vRead operation.
         */
        protected Boolean readHistory;

        /**
         * _include values supported by the server.
         */
        protected List<String_> searchInclude = new ArrayList<String_>();

        /**
         * Defines additional search parameters for implementations to support and/or make use of.
         */
        protected List<ConformanceRestResourceSearchParamComponent> searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();

        public Code getType() { 
          return this.type;
        }

        public void setType(Code value) { 
          this.type = value;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
        }

        public ResourceReference getProfile() { 
          return this.profile;
        }

        public void setProfile(ResourceReference value) { 
          this.profile = value;
        }

        public List<ConformanceRestResourceOperationComponent> getOperation() { 
          return this.operation;
        }

    // syntactic sugar
        public ConformanceRestResourceOperationComponent addOperation() { 
          ConformanceRestResourceOperationComponent t = new ConformanceRestResourceOperationComponent();
          this.operation.add(t);
          return t;
        }

        public Boolean getReadHistory() { 
          return this.readHistory;
        }

        public void setReadHistory(Boolean value) { 
          this.readHistory = value;
        }

        public boolean getReadHistorySimple() { 
          return this.readHistory == null ? null : this.readHistory.getValue();
        }

        public void setReadHistorySimple(boolean value) { 
          if (value == false)
            this.readHistory = null;
          else {
            if (this.readHistory == null)
              this.readHistory = new Boolean();
            this.readHistory.setValue(value);
          }
        }

        public List<String_> getSearchInclude() { 
          return this.searchInclude;
        }

    // syntactic sugar
        public String_ addSearchInclude() { 
          String_ t = new String_();
          this.searchInclude.add(t);
          return t;
        }

        public String_ addSearchIncludeSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.searchInclude.add(t);
          return t;
        }

        public List<ConformanceRestResourceSearchParamComponent> getSearchParam() { 
          return this.searchParam;
        }

    // syntactic sugar
        public ConformanceRestResourceSearchParamComponent addSearchParam() { 
          ConformanceRestResourceSearchParamComponent t = new ConformanceRestResourceSearchParamComponent();
          this.searchParam.add(t);
          return t;
        }

      public ConformanceRestResourceComponent copy(Conformance e) {
        ConformanceRestResourceComponent dst = e.new ConformanceRestResourceComponent();
        dst.type = type == null ? null : type.copy();
        dst.profile = profile == null ? null : profile.copy();
        dst.operation = new ArrayList<ConformanceRestResourceOperationComponent>();
        for (ConformanceRestResourceOperationComponent i : operation)
          dst.operation.add(i.copy(e));
        dst.readHistory = readHistory == null ? null : readHistory.copy();
        dst.searchInclude = new ArrayList<String_>();
        for (String_ i : searchInclude)
          dst.searchInclude.add(i.copy());
        dst.searchParam = new ArrayList<ConformanceRestResourceSearchParamComponent>();
        for (ConformanceRestResourceSearchParamComponent i : searchParam)
          dst.searchParam.add(i.copy(e));
        return dst;
      }

  }

    public class ConformanceRestResourceOperationComponent extends Element {
        /**
         * Identifies which operation is supported.
         */
        protected Enumeration<RestfulOperation> code;

        /**
         * Provides guidance specific to the implementation of this operation, such as 'delete is a logical delete' or 'updates are only allowed with version id' or 'creates permitted from pre-authorized certificates only'.
         */
        protected String_ documentation;

        public Enumeration<RestfulOperation> getCode() { 
          return this.code;
        }

        public void setCode(Enumeration<RestfulOperation> value) { 
          this.code = value;
        }

        public RestfulOperation getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public void setCodeSimple(RestfulOperation value) { 
            if (this.code == null)
              this.code = new Enumeration<RestfulOperation>();
            this.code.setValue(value);
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
        }

      public ConformanceRestResourceOperationComponent copy(Conformance e) {
        ConformanceRestResourceOperationComponent dst = e.new ConformanceRestResourceOperationComponent();
        dst.code = code == null ? null : code.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        return dst;
      }

  }

    public class ConformanceRestResourceSearchParamComponent extends Element {
        /**
         * Corresponds to the name of the standard or custom search parameter.
         */
        protected String_ name;

        /**
         * A formal reference to where this parameter was first defined, so that a client can be confident of the meaning of the search parameter.
         */
        protected Uri source;

        /**
         * The type of value a search parameter refers to, and how the content is interpreted.
         */
        protected Enumeration<SearchParamType> type;

        /**
         * For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        protected String_ documentation;

        /**
         * An XPath expression that extracts the set of elements that contain values that a search parameter matches.
         */
        protected String_ xpath;

        /**
         * Types of resource (if a resource reference).
         */
        protected List<Code> target = new ArrayList<Code>();

        /**
         * Chained names supported.
         */
        protected List<String_> chain = new ArrayList<String_>();

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
        }

        public Uri getSource() { 
          return this.source;
        }

        public void setSource(Uri value) { 
          this.source = value;
        }

        public String getSourceSimple() { 
          return this.source == null ? null : this.source.getValue();
        }

        public void setSourceSimple(String value) { 
          if (value == null)
            this.source = null;
          else {
            if (this.source == null)
              this.source = new Uri();
            this.source.setValue(value);
          }
        }

        public Enumeration<SearchParamType> getType() { 
          return this.type;
        }

        public void setType(Enumeration<SearchParamType> value) { 
          this.type = value;
        }

        public SearchParamType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public void setTypeSimple(SearchParamType value) { 
            if (this.type == null)
              this.type = new Enumeration<SearchParamType>();
            this.type.setValue(value);
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
        }

        public String_ getXpath() { 
          return this.xpath;
        }

        public void setXpath(String_ value) { 
          this.xpath = value;
        }

        public String getXpathSimple() { 
          return this.xpath == null ? null : this.xpath.getValue();
        }

        public void setXpathSimple(String value) { 
          if (value == null)
            this.xpath = null;
          else {
            if (this.xpath == null)
              this.xpath = new String_();
            this.xpath.setValue(value);
          }
        }

        public List<Code> getTarget() { 
          return this.target;
        }

    // syntactic sugar
        public Code addTarget() { 
          Code t = new Code();
          this.target.add(t);
          return t;
        }

        public Code addTargetSimple(String value) { 
          Code t = new Code();
          t.setValue(value);
          this.target.add(t);
          return t;
        }

        public List<String_> getChain() { 
          return this.chain;
        }

    // syntactic sugar
        public String_ addChain() { 
          String_ t = new String_();
          this.chain.add(t);
          return t;
        }

        public String_ addChainSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.chain.add(t);
          return t;
        }

      public ConformanceRestResourceSearchParamComponent copy(Conformance e) {
        ConformanceRestResourceSearchParamComponent dst = e.new ConformanceRestResourceSearchParamComponent();
        dst.name = name == null ? null : name.copy();
        dst.source = source == null ? null : source.copy();
        dst.type = type == null ? null : type.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.xpath = xpath == null ? null : xpath.copy();
        dst.target = new ArrayList<Code>();
        for (Code i : target)
          dst.target.add(i.copy());
        dst.chain = new ArrayList<String_>();
        for (String_ i : chain)
          dst.chain.add(i.copy());
        return dst;
      }

  }

    public class ConformanceRestQueryComponent extends Element {
        /**
         * The name of this query, which is used in the _query parameter when the query is used.
         */
        protected String_ name;

        /**
         * Description of the query - the functionality it offers, and considerations about how it functions and to use it.
         */
        protected String_ documentation;

        /**
         * Parameter for the named query.
         */
        protected List<ConformanceRestResourceSearchParamComponent> parameter = new ArrayList<ConformanceRestResourceSearchParamComponent>();

        public String_ getName() { 
          return this.name;
        }

        public void setName(String_ value) { 
          this.name = value;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public void setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
        }

        public List<ConformanceRestResourceSearchParamComponent> getParameter() { 
          return this.parameter;
        }

    // syntactic sugar
        public ConformanceRestResourceSearchParamComponent addParameter() { 
          ConformanceRestResourceSearchParamComponent t = new ConformanceRestResourceSearchParamComponent();
          this.parameter.add(t);
          return t;
        }

      public ConformanceRestQueryComponent copy(Conformance e) {
        ConformanceRestQueryComponent dst = e.new ConformanceRestQueryComponent();
        dst.name = name == null ? null : name.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.parameter = new ArrayList<ConformanceRestResourceSearchParamComponent>();
        for (ConformanceRestResourceSearchParamComponent i : parameter)
          dst.parameter.add(i.copy(e));
        return dst;
      }

  }

    public class ConformanceMessagingComponent extends Element {
        /**
         * The address to which messages and/or replies are to be sent.
         */
        protected Uri endpoint;

        /**
         * The length if the receiver's reliable messaging cache length (if a receiver) or how long the cache length on the receiver should be (if a sender).
         */
        protected Integer reliableCache;

        /**
         * Provides documentation about the system's messaging capabilities for this endpoint not otherwise documented by the conformance statement.  For example, process for becoming an authorized messaging exchange partner.
         */
        protected String_ documentation;

        /**
         * Describes the solution's support for an event at this end point.
         */
        protected List<ConformanceMessagingEventComponent> event = new ArrayList<ConformanceMessagingEventComponent>();

        public Uri getEndpoint() { 
          return this.endpoint;
        }

        public void setEndpoint(Uri value) { 
          this.endpoint = value;
        }

        public String getEndpointSimple() { 
          return this.endpoint == null ? null : this.endpoint.getValue();
        }

        public void setEndpointSimple(String value) { 
          if (value == null)
            this.endpoint = null;
          else {
            if (this.endpoint == null)
              this.endpoint = new Uri();
            this.endpoint.setValue(value);
          }
        }

        public Integer getReliableCache() { 
          return this.reliableCache;
        }

        public void setReliableCache(Integer value) { 
          this.reliableCache = value;
        }

        public int getReliableCacheSimple() { 
          return this.reliableCache == null ? null : this.reliableCache.getValue();
        }

        public void setReliableCacheSimple(int value) { 
          if (value == -1)
            this.reliableCache = null;
          else {
            if (this.reliableCache == null)
              this.reliableCache = new Integer();
            this.reliableCache.setValue(value);
          }
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
        }

        public List<ConformanceMessagingEventComponent> getEvent() { 
          return this.event;
        }

    // syntactic sugar
        public ConformanceMessagingEventComponent addEvent() { 
          ConformanceMessagingEventComponent t = new ConformanceMessagingEventComponent();
          this.event.add(t);
          return t;
        }

      public ConformanceMessagingComponent copy(Conformance e) {
        ConformanceMessagingComponent dst = e.new ConformanceMessagingComponent();
        dst.endpoint = endpoint == null ? null : endpoint.copy();
        dst.reliableCache = reliableCache == null ? null : reliableCache.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.event = new ArrayList<ConformanceMessagingEventComponent>();
        for (ConformanceMessagingEventComponent i : event)
          dst.event.add(i.copy(e));
        return dst;
      }

  }

    public class ConformanceMessagingEventComponent extends Element {
        /**
         * Identifies the supported messaging event.
         */
        protected Code code;

        /**
         * The mode of this event declaration - whether application is sender or receiver.
         */
        protected Enumeration<MessageConformanceEventMode> mode;

        /**
         * Identifies the messaging transport protocol(s) supported by this endpoint.
         */
        protected List<Coding> protocol = new ArrayList<Coding>();

        /**
         * Identifies the resource associated with the event.  This is the resource that defines the event.
         */
        protected Code focus;

        /**
         * Information about the request for this event.
         */
        protected ResourceReference request;

        /**
         * Information about the response for this event.
         */
        protected ResourceReference response;

        /**
         * Guidance on how this event is handled, such as internal system trigger points, business rules, etc.
         */
        protected String_ documentation;

        public Code getCode() { 
          return this.code;
        }

        public void setCode(Code value) { 
          this.code = value;
        }

        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public void setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
        }

        public Enumeration<MessageConformanceEventMode> getMode() { 
          return this.mode;
        }

        public void setMode(Enumeration<MessageConformanceEventMode> value) { 
          this.mode = value;
        }

        public MessageConformanceEventMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        public void setModeSimple(MessageConformanceEventMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<MessageConformanceEventMode>();
            this.mode.setValue(value);
        }

        public List<Coding> getProtocol() { 
          return this.protocol;
        }

    // syntactic sugar
        public Coding addProtocol() { 
          Coding t = new Coding();
          this.protocol.add(t);
          return t;
        }

        public Code getFocus() { 
          return this.focus;
        }

        public void setFocus(Code value) { 
          this.focus = value;
        }

        public String getFocusSimple() { 
          return this.focus == null ? null : this.focus.getValue();
        }

        public void setFocusSimple(String value) { 
            if (this.focus == null)
              this.focus = new Code();
            this.focus.setValue(value);
        }

        public ResourceReference getRequest() { 
          return this.request;
        }

        public void setRequest(ResourceReference value) { 
          this.request = value;
        }

        public ResourceReference getResponse() { 
          return this.response;
        }

        public void setResponse(ResourceReference value) { 
          this.response = value;
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
        }

      public ConformanceMessagingEventComponent copy(Conformance e) {
        ConformanceMessagingEventComponent dst = e.new ConformanceMessagingEventComponent();
        dst.code = code == null ? null : code.copy();
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

    public class ConformanceDocumentComponent extends Element {
        /**
         * The mode of this event declaration - whether application is sender or receiver.
         */
        protected Enumeration<DocumentMode> mode;

        /**
         * Describes how the application supports or uses the specified document profile.  For example, when are documents created, what action is taken with consumed documents, etc.
         */
        protected String_ documentation;

        /**
         * Constraint on a resource used in the document.
         */
        protected ResourceReference profile;

        public Enumeration<DocumentMode> getMode() { 
          return this.mode;
        }

        public void setMode(Enumeration<DocumentMode> value) { 
          this.mode = value;
        }

        public DocumentMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        public void setModeSimple(DocumentMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<DocumentMode>();
            this.mode.setValue(value);
        }

        public String_ getDocumentation() { 
          return this.documentation;
        }

        public void setDocumentation(String_ value) { 
          this.documentation = value;
        }

        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        public void setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          }
        }

        public ResourceReference getProfile() { 
          return this.profile;
        }

        public void setProfile(ResourceReference value) { 
          this.profile = value;
        }

      public ConformanceDocumentComponent copy(Conformance e) {
        ConformanceDocumentComponent dst = e.new ConformanceDocumentComponent();
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
     * Name of Organization.
     */
    protected String_ publisher;

    /**
     * Contacts for Organization relevant to this conformance statement.  May be website, email, phone numbers, etc.
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
     * This conformance statement was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected Boolean experimental;

    /**
     * Date that the conformance statement is published.
     */
    protected DateTime date;

    /**
     * Describes the software that is covered by this conformance statement.  Used when the profile describes the capabilities of a particular software version, independent of an installation.
     */
    protected ConformanceSoftwareComponent software;

    /**
     * Used when the statement describes the capabilities of a specific implementation instance - i.e. a particular installation, rather than the capabilities of a software program.
     */
    protected ConformanceImplementationComponent implementation;

    /**
     * The version of the FHIR specification on which this conformance statement is based.
     */
    protected Id fhirVersion;

    /**
     * Whether the application accepts unknown non-"must understand" elements as part of a resource. This does not include extensions, but genuine new additions to a resource.
     */
    protected Boolean acceptUnknown;

    /**
     * The formats supported by this implementation.
     */
    protected List<Code> format = new ArrayList<Code>();

    /**
     * Defines the restful capabilities of the solution, if any.
     */
    protected List<ConformanceRestComponent> rest = new ArrayList<ConformanceRestComponent>();

    /**
     * Describes the messaging capabilities of the solution.
     */
    protected List<ConformanceMessagingComponent> messaging = new ArrayList<ConformanceMessagingComponent>();

    /**
     * A document definition.
     */
    protected List<ConformanceDocumentComponent> document = new ArrayList<ConformanceDocumentComponent>();

    public String_ getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(String_ value) { 
      this.identifier = value;
    }

    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    public void setIdentifierSimple(String value) { 
      if (value == null)
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new String_();
        this.identifier.setValue(value);
      }
    }

    public String_ getVersion() { 
      return this.version;
    }

    public void setVersion(String_ value) { 
      this.version = value;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public void setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
    }

    public String_ getName() { 
      return this.name;
    }

    public void setName(String_ value) { 
      this.name = value;
    }

    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    public void setNameSimple(String value) { 
      if (value == null)
        this.name = null;
      else {
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      }
    }

    public String_ getPublisher() { 
      return this.publisher;
    }

    public void setPublisher(String_ value) { 
      this.publisher = value;
    }

    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    public void setPublisherSimple(String value) { 
        if (this.publisher == null)
          this.publisher = new String_();
        this.publisher.setValue(value);
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
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

    public Enumeration<ConformanceStatementStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<ConformanceStatementStatus> value) { 
      this.status = value;
    }

    public ConformanceStatementStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(ConformanceStatementStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<ConformanceStatementStatus>();
        this.status.setValue(value);
      }
    }

    public Boolean getExperimental() { 
      return this.experimental;
    }

    public void setExperimental(Boolean value) { 
      this.experimental = value;
    }

    public boolean getExperimentalSimple() { 
      return this.experimental == null ? null : this.experimental.getValue();
    }

    public void setExperimentalSimple(boolean value) { 
      if (value == false)
        this.experimental = null;
      else {
        if (this.experimental == null)
          this.experimental = new Boolean();
        this.experimental.setValue(value);
      }
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
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
    }

    public ConformanceSoftwareComponent getSoftware() { 
      return this.software;
    }

    public void setSoftware(ConformanceSoftwareComponent value) { 
      this.software = value;
    }

    public ConformanceImplementationComponent getImplementation() { 
      return this.implementation;
    }

    public void setImplementation(ConformanceImplementationComponent value) { 
      this.implementation = value;
    }

    public Id getFhirVersion() { 
      return this.fhirVersion;
    }

    public void setFhirVersion(Id value) { 
      this.fhirVersion = value;
    }

    public String getFhirVersionSimple() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    public void setFhirVersionSimple(String value) { 
        if (this.fhirVersion == null)
          this.fhirVersion = new Id();
        this.fhirVersion.setValue(value);
    }

    public Boolean getAcceptUnknown() { 
      return this.acceptUnknown;
    }

    public void setAcceptUnknown(Boolean value) { 
      this.acceptUnknown = value;
    }

    public boolean getAcceptUnknownSimple() { 
      return this.acceptUnknown == null ? null : this.acceptUnknown.getValue();
    }

    public void setAcceptUnknownSimple(boolean value) { 
        if (this.acceptUnknown == null)
          this.acceptUnknown = new Boolean();
        this.acceptUnknown.setValue(value);
    }

    public List<Code> getFormat() { 
      return this.format;
    }

    // syntactic sugar
    public Code addFormat() { 
      Code t = new Code();
      this.format.add(t);
      return t;
    }

    public Code addFormatSimple(String value) { 
      Code t = new Code();
      t.setValue(value);
      this.format.add(t);
      return t;
    }

    public List<ConformanceRestComponent> getRest() { 
      return this.rest;
    }

    // syntactic sugar
    public ConformanceRestComponent addRest() { 
      ConformanceRestComponent t = new ConformanceRestComponent();
      this.rest.add(t);
      return t;
    }

    public List<ConformanceMessagingComponent> getMessaging() { 
      return this.messaging;
    }

    // syntactic sugar
    public ConformanceMessagingComponent addMessaging() { 
      ConformanceMessagingComponent t = new ConformanceMessagingComponent();
      this.messaging.add(t);
      return t;
    }

    public List<ConformanceDocumentComponent> getDocument() { 
      return this.document;
    }

    // syntactic sugar
    public ConformanceDocumentComponent addDocument() { 
      ConformanceDocumentComponent t = new ConformanceDocumentComponent();
      this.document.add(t);
      return t;
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
        dst.software = software == null ? null : software.copy(dst);
        dst.implementation = implementation == null ? null : implementation.copy(dst);
        dst.fhirVersion = fhirVersion == null ? null : fhirVersion.copy();
        dst.acceptUnknown = acceptUnknown == null ? null : acceptUnknown.copy();
        dst.format = new ArrayList<Code>();
        for (Code i : format)
          dst.format.add(i.copy());
        dst.rest = new ArrayList<ConformanceRestComponent>();
        for (ConformanceRestComponent i : rest)
          dst.rest.add(i.copy(dst));
        dst.messaging = new ArrayList<ConformanceMessagingComponent>();
        for (ConformanceMessagingComponent i : messaging)
          dst.messaging.add(i.copy(dst));
        dst.document = new ArrayList<ConformanceDocumentComponent>();
        for (ConformanceDocumentComponent i : document)
          dst.document.add(i.copy(dst));
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

