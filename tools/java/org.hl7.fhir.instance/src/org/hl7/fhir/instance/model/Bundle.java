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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import java.math.*;
import org.hl7.fhir.utilities.Utilities;
/**
 * A container for a group of resources.
 */
public class Bundle extends Resource {

    public enum BundleType {
        DOCUMENT, // The bundle is a document. The first resource is a Composition.
        MESSAGE, // The bundle is a message. The first resource is a MessageHeader.
        TRANSACTION, // The bundle is a transaction - intended to be processed by a server as an atomic commit.
        TRANSACTIONRESPONSE, // The bundle is a transaction response.
        HISTORY, // The bundle is a list of resources from a _history interaction on a server.
        SEARCHSET, // The bundle is a list of resources returned as a result of a search/query interaction, operation, or message.
        COLLECTION, // The bundle is a set of resources collected into a single document for ease of distribution.
        NULL; // added to help the parsers
        public static BundleType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("document".equals(codeString))
          return DOCUMENT;
        if ("message".equals(codeString))
          return MESSAGE;
        if ("transaction".equals(codeString))
          return TRANSACTION;
        if ("transaction-response".equals(codeString))
          return TRANSACTIONRESPONSE;
        if ("history".equals(codeString))
          return HISTORY;
        if ("searchset".equals(codeString))
          return SEARCHSET;
        if ("collection".equals(codeString))
          return COLLECTION;
        throw new Exception("Unknown BundleType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DOCUMENT: return "document";
            case MESSAGE: return "message";
            case TRANSACTION: return "transaction";
            case TRANSACTIONRESPONSE: return "transaction-response";
            case HISTORY: return "history";
            case SEARCHSET: return "searchset";
            case COLLECTION: return "collection";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DOCUMENT: return "The bundle is a document. The first resource is a Composition.";
            case MESSAGE: return "The bundle is a message. The first resource is a MessageHeader.";
            case TRANSACTION: return "The bundle is a transaction - intended to be processed by a server as an atomic commit.";
            case TRANSACTIONRESPONSE: return "The bundle is a transaction response.";
            case HISTORY: return "The bundle is a list of resources from a _history interaction on a server.";
            case SEARCHSET: return "The bundle is a list of resources returned as a result of a search/query interaction, operation, or message.";
            case COLLECTION: return "The bundle is a set of resources collected into a single document for ease of distribution.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DOCUMENT: return "Document";
            case MESSAGE: return "Message";
            case TRANSACTION: return "Transaction";
            case TRANSACTIONRESPONSE: return "Transaction Response";
            case HISTORY: return "History List";
            case SEARCHSET: return "Search Results";
            case COLLECTION: return "Collection";
            default: return "?";
          }
        }
    }

  public static class BundleTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("document".equals(codeString))
          return BundleType.DOCUMENT;
        if ("message".equals(codeString))
          return BundleType.MESSAGE;
        if ("transaction".equals(codeString))
          return BundleType.TRANSACTION;
        if ("transaction-response".equals(codeString))
          return BundleType.TRANSACTIONRESPONSE;
        if ("history".equals(codeString))
          return BundleType.HISTORY;
        if ("searchset".equals(codeString))
          return BundleType.SEARCHSET;
        if ("collection".equals(codeString))
          return BundleType.COLLECTION;
        throw new Exception("Unknown BundleType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == BundleType.DOCUMENT)
        return "document";
      if (code == BundleType.MESSAGE)
        return "message";
      if (code == BundleType.TRANSACTION)
        return "transaction";
      if (code == BundleType.TRANSACTIONRESPONSE)
        return "transaction-response";
      if (code == BundleType.HISTORY)
        return "history";
      if (code == BundleType.SEARCHSET)
        return "searchset";
      if (code == BundleType.COLLECTION)
        return "collection";
      return "?";
      }
    }

    public enum BundleEntryStatus {
        CREATE, // Transaction: perform a create operation on this resource.
        UPDATE, // Transaction: perform an update operation on this resource.
        MATCH, // Transaction: look for this resource using the search url provided. If there's no match, create it. Search: this resource is returned because it matches the search criteria.
        INCLUDE, // Search: this resource is returned because it meets an _include criteria.
        NULL; // added to help the parsers
        public static BundleEntryStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("create".equals(codeString))
          return CREATE;
        if ("update".equals(codeString))
          return UPDATE;
        if ("match".equals(codeString))
          return MATCH;
        if ("include".equals(codeString))
          return INCLUDE;
        throw new Exception("Unknown BundleEntryStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case CREATE: return "create";
            case UPDATE: return "update";
            case MATCH: return "match";
            case INCLUDE: return "include";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case CREATE: return "Transaction: perform a create operation on this resource.";
            case UPDATE: return "Transaction: perform an update operation on this resource.";
            case MATCH: return "Transaction: look for this resource using the search url provided. If there's no match, create it. Search: this resource is returned because it matches the search criteria.";
            case INCLUDE: return "Search: this resource is returned because it meets an _include criteria.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case CREATE: return "Create";
            case UPDATE: return "Update";
            case MATCH: return "Match";
            case INCLUDE: return "Include";
            default: return "?";
          }
        }
    }

  public static class BundleEntryStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("create".equals(codeString))
          return BundleEntryStatus.CREATE;
        if ("update".equals(codeString))
          return BundleEntryStatus.UPDATE;
        if ("match".equals(codeString))
          return BundleEntryStatus.MATCH;
        if ("include".equals(codeString))
          return BundleEntryStatus.INCLUDE;
        throw new Exception("Unknown BundleEntryStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == BundleEntryStatus.CREATE)
        return "create";
      if (code == BundleEntryStatus.UPDATE)
        return "update";
      if (code == BundleEntryStatus.MATCH)
        return "match";
      if (code == BundleEntryStatus.INCLUDE)
        return "include";
      return "?";
      }
    }

    public static class BundleLinkComponent extends BackboneElement {
        /**
         * A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        protected StringType relation;

        /**
         * The reference details for the link.
         */
        protected UriType url;

        private static final long serialVersionUID = -1010386066L;

      public BundleLinkComponent() {
        super();
      }

      public BundleLinkComponent(StringType relation, UriType url) {
        super();
        this.relation = relation;
        this.url = url;
      }

        /**
         * @return {@link #relation} (A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].). This is the underlying object with id, value and extensions. The accessor "getRelation" gives direct access to the value
         */
        public StringType getRelationElement() { 
          return this.relation;
        }

        /**
         * @param value {@link #relation} (A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].). This is the underlying object with id, value and extensions. The accessor "getRelation" gives direct access to the value
         */
        public BundleLinkComponent setRelationElement(StringType value) { 
          this.relation = value;
          return this;
        }

        /**
         * @return A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        public String getRelation() { 
          return this.relation == null ? null : this.relation.getValue();
        }

        /**
         * @param value A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].
         */
        public BundleLinkComponent setRelation(String value) { 
            if (this.relation == null)
              this.relation = new StringType();
            this.relation.setValue(value);
          return this;
        }

        /**
         * @return {@link #url} (The reference details for the link.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public UriType getUrlElement() { 
          return this.url;
        }

        /**
         * @param value {@link #url} (The reference details for the link.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
         */
        public BundleLinkComponent setUrlElement(UriType value) { 
          this.url = value;
          return this;
        }

        /**
         * @return The reference details for the link.
         */
        public String getUrl() { 
          return this.url == null ? null : this.url.getValue();
        }

        /**
         * @param value The reference details for the link.
         */
        public BundleLinkComponent setUrl(String value) { 
            if (this.url == null)
              this.url = new UriType();
            this.url.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relation", "string", "A name which details the functional use for this link - see [[http://www.iana.org/assignments/link-relations/link-relations.xhtml]].", 0, java.lang.Integer.MAX_VALUE, relation));
          childrenList.add(new Property("url", "uri", "The reference details for the link.", 0, java.lang.Integer.MAX_VALUE, url));
        }

      public BundleLinkComponent copy() {
        BundleLinkComponent dst = new BundleLinkComponent();
        copyValues(dst);
        dst.relation = relation == null ? null : relation.copy();
        dst.url = url == null ? null : url.copy();
        return dst;
      }

  }

    public static class BundleEntryComponent extends BackboneElement {
        /**
         * The Base URL for the resource, if different to the base URL specified for the bundle as a whole.
         */
        protected UriType base;

        /**
         * The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).
         */
        protected Enumeration<BundleEntryStatus> status;

        /**
         * Search URL for this resource when processing a transaction (see transaction documentation).
         */
        protected UriType search;

        /**
         * When searching, the server's search ranking score for the entry.
         */
        protected DecimalType score;

        /**
         * If this is an entry that represents a deleted resource. Only used when the bundle is a transaction or a history type. See RESTful API documentation for further informatino.
         */
        protected BundleEntryDeletedComponent deleted;

        /**
         * The Resources for the entry.
         */
        protected Resource resource;

        private static final long serialVersionUID = 509077972L;

      public BundleEntryComponent() {
        super();
      }

        /**
         * @return {@link #base} (The Base URL for the resource, if different to the base URL specified for the bundle as a whole.). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
         */
        public UriType getBaseElement() { 
          return this.base;
        }

        /**
         * @param value {@link #base} (The Base URL for the resource, if different to the base URL specified for the bundle as a whole.). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
         */
        public BundleEntryComponent setBaseElement(UriType value) { 
          this.base = value;
          return this;
        }

        /**
         * @return The Base URL for the resource, if different to the base URL specified for the bundle as a whole.
         */
        public String getBase() { 
          return this.base == null ? null : this.base.getValue();
        }

        /**
         * @param value The Base URL for the resource, if different to the base URL specified for the bundle as a whole.
         */
        public BundleEntryComponent setBase(String value) { 
          if (Utilities.noString(value))
            this.base = null;
          else {
            if (this.base == null)
              this.base = new UriType();
            this.base.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #status} (The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public Enumeration<BundleEntryStatus> getStatusElement() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
         */
        public BundleEntryComponent setStatusElement(Enumeration<BundleEntryStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).
         */
        public BundleEntryStatus getStatus() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).
         */
        public BundleEntryComponent setStatus(BundleEntryStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<BundleEntryStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #search} (Search URL for this resource when processing a transaction (see transaction documentation).). This is the underlying object with id, value and extensions. The accessor "getSearch" gives direct access to the value
         */
        public UriType getSearchElement() { 
          return this.search;
        }

        /**
         * @param value {@link #search} (Search URL for this resource when processing a transaction (see transaction documentation).). This is the underlying object with id, value and extensions. The accessor "getSearch" gives direct access to the value
         */
        public BundleEntryComponent setSearchElement(UriType value) { 
          this.search = value;
          return this;
        }

        /**
         * @return Search URL for this resource when processing a transaction (see transaction documentation).
         */
        public String getSearch() { 
          return this.search == null ? null : this.search.getValue();
        }

        /**
         * @param value Search URL for this resource when processing a transaction (see transaction documentation).
         */
        public BundleEntryComponent setSearch(String value) { 
          if (Utilities.noString(value))
            this.search = null;
          else {
            if (this.search == null)
              this.search = new UriType();
            this.search.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #score} (When searching, the server's search ranking score for the entry.). This is the underlying object with id, value and extensions. The accessor "getScore" gives direct access to the value
         */
        public DecimalType getScoreElement() { 
          return this.score;
        }

        /**
         * @param value {@link #score} (When searching, the server's search ranking score for the entry.). This is the underlying object with id, value and extensions. The accessor "getScore" gives direct access to the value
         */
        public BundleEntryComponent setScoreElement(DecimalType value) { 
          this.score = value;
          return this;
        }

        /**
         * @return When searching, the server's search ranking score for the entry.
         */
        public BigDecimal getScore() { 
          return this.score == null ? null : this.score.getValue();
        }

        /**
         * @param value When searching, the server's search ranking score for the entry.
         */
        public BundleEntryComponent setScore(BigDecimal value) { 
          if (value == null)
            this.score = null;
          else {
            if (this.score == null)
              this.score = new DecimalType();
            this.score.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #deleted} (If this is an entry that represents a deleted resource. Only used when the bundle is a transaction or a history type. See RESTful API documentation for further informatino.)
         */
        public BundleEntryDeletedComponent getDeleted() { 
          return this.deleted;
        }

        /**
         * @param value {@link #deleted} (If this is an entry that represents a deleted resource. Only used when the bundle is a transaction or a history type. See RESTful API documentation for further informatino.)
         */
        public BundleEntryComponent setDeleted(BundleEntryDeletedComponent value) { 
          this.deleted = value;
          return this;
        }

        /**
         * @return {@link #resource} (The Resources for the entry.)
         */
        public Resource getResource() { 
          return this.resource;
        }

        /**
         * @param value {@link #resource} (The Resources for the entry.)
         */
        public BundleEntryComponent setResource(Resource value) { 
          this.resource = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("base", "uri", "The Base URL for the resource, if different to the base URL specified for the bundle as a whole.", 0, java.lang.Integer.MAX_VALUE, base));
          childrenList.add(new Property("status", "code", "The status of a resource in the bundle. Used for search (to differentiate between resources included as a match, and resources included as an _include), and for transactions (create/update/delete).", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("search", "uri", "Search URL for this resource when processing a transaction (see transaction documentation).", 0, java.lang.Integer.MAX_VALUE, search));
          childrenList.add(new Property("score", "decimal", "When searching, the server's search ranking score for the entry.", 0, java.lang.Integer.MAX_VALUE, score));
          childrenList.add(new Property("deleted", "", "If this is an entry that represents a deleted resource. Only used when the bundle is a transaction or a history type. See RESTful API documentation for further informatino.", 0, java.lang.Integer.MAX_VALUE, deleted));
          childrenList.add(new Property("resource", "Resource", "The Resources for the entry.", 0, java.lang.Integer.MAX_VALUE, resource));
        }

      public BundleEntryComponent copy() {
        BundleEntryComponent dst = new BundleEntryComponent();
        copyValues(dst);
        dst.base = base == null ? null : base.copy();
        dst.status = status == null ? null : status.copy();
        dst.search = search == null ? null : search.copy();
        dst.score = score == null ? null : score.copy();
        dst.deleted = deleted == null ? null : deleted.copy();
        dst.resource = resource == null ? null : resource.copy();
        return dst;
      }

  }

    public static class BundleEntryDeletedComponent extends BackboneElement {
        /**
         * The type of resource that was deleted (required to construct the identity).
         */
        protected CodeType type;

        /**
         * The id of the resource that was deleted.
         */
        protected IdType id;

        /**
         * Version id for releted resource.
         */
        protected IdType versionId;

        /**
         * The date/time that the resource was deleted.
         */
        protected InstantType instant;

        private static final long serialVersionUID = 1013425873L;

      public BundleEntryDeletedComponent() {
        super();
      }

      public BundleEntryDeletedComponent(CodeType type, IdType id, IdType versionId, InstantType instant) {
        super();
        this.type = type;
        this.id = id;
        this.versionId = versionId;
        this.instant = instant;
      }

        /**
         * @return {@link #type} (The type of resource that was deleted (required to construct the identity).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public CodeType getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of resource that was deleted (required to construct the identity).). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public BundleEntryDeletedComponent setTypeElement(CodeType value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of resource that was deleted (required to construct the identity).
         */
        public String getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of resource that was deleted (required to construct the identity).
         */
        public BundleEntryDeletedComponent setType(String value) { 
            if (this.type == null)
              this.type = new CodeType();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #id} (The id of the resource that was deleted.). This is the underlying object with id, value and extensions. The accessor "getId" gives direct access to the value
         */
        public IdType getIdElement() { 
          return this.id;
        }

        /**
         * @param value {@link #id} (The id of the resource that was deleted.). This is the underlying object with id, value and extensions. The accessor "getId" gives direct access to the value
         */
        public BundleEntryDeletedComponent setIdElement(IdType value) { 
          this.id = value;
          return this;
        }

        /**
         * @return The id of the resource that was deleted.
         */
        public String getId() { 
          return this.id == null ? null : this.id.getValue();
        }

        /**
         * @param value The id of the resource that was deleted.
         */
        public BundleEntryDeletedComponent setId(String value) { 
            if (this.id == null)
              this.id = new IdType();
            this.id.setValue(value);
          return this;
        }

        /**
         * @return {@link #versionId} (Version id for releted resource.). This is the underlying object with id, value and extensions. The accessor "getVersionId" gives direct access to the value
         */
        public IdType getVersionIdElement() { 
          return this.versionId;
        }

        /**
         * @param value {@link #versionId} (Version id for releted resource.). This is the underlying object with id, value and extensions. The accessor "getVersionId" gives direct access to the value
         */
        public BundleEntryDeletedComponent setVersionIdElement(IdType value) { 
          this.versionId = value;
          return this;
        }

        /**
         * @return Version id for releted resource.
         */
        public String getVersionId() { 
          return this.versionId == null ? null : this.versionId.getValue();
        }

        /**
         * @param value Version id for releted resource.
         */
        public BundleEntryDeletedComponent setVersionId(String value) { 
            if (this.versionId == null)
              this.versionId = new IdType();
            this.versionId.setValue(value);
          return this;
        }

        /**
         * @return {@link #instant} (The date/time that the resource was deleted.). This is the underlying object with id, value and extensions. The accessor "getInstant" gives direct access to the value
         */
        public InstantType getInstantElement() { 
          return this.instant;
        }

        /**
         * @param value {@link #instant} (The date/time that the resource was deleted.). This is the underlying object with id, value and extensions. The accessor "getInstant" gives direct access to the value
         */
        public BundleEntryDeletedComponent setInstantElement(InstantType value) { 
          this.instant = value;
          return this;
        }

        /**
         * @return The date/time that the resource was deleted.
         */
        public DateAndTime getInstant() { 
          return this.instant == null ? null : this.instant.getValue();
        }

        /**
         * @param value The date/time that the resource was deleted.
         */
        public BundleEntryDeletedComponent setInstant(DateAndTime value) { 
            if (this.instant == null)
              this.instant = new InstantType();
            this.instant.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The type of resource that was deleted (required to construct the identity).", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("id", "id", "The id of the resource that was deleted.", 0, java.lang.Integer.MAX_VALUE, id));
          childrenList.add(new Property("versionId", "id", "Version id for releted resource.", 0, java.lang.Integer.MAX_VALUE, versionId));
          childrenList.add(new Property("instant", "instant", "The date/time that the resource was deleted.", 0, java.lang.Integer.MAX_VALUE, instant));
        }

      public BundleEntryDeletedComponent copy() {
        BundleEntryDeletedComponent dst = new BundleEntryDeletedComponent();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.id = id == null ? null : id.copy();
        dst.versionId = versionId == null ? null : versionId.copy();
        dst.instant = instant == null ? null : instant.copy();
        return dst;
      }

  }

    /**
     * Indicates the purpose of this bundle- how it was intended to be used.
     */
    protected Enumeration<BundleType> type;

    /**
     * The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    protected UriType base;

    /**
     * If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).
     */
    protected IntegerType total;

    /**
     * A series of links that provide context to this bundle.
     */
    protected List<BundleLinkComponent> link = new ArrayList<BundleLinkComponent>();

    /**
     * An entry in a bundle resource - will either contain a resource, or a deleted entry (transaction and history bundles only).
     */
    protected List<BundleEntryComponent> entry = new ArrayList<BundleEntryComponent>();

    /**
     * XML Digital Signature - base64 encoded.
     */
    protected Base64BinaryType signature;

    private static final long serialVersionUID = -1152759872L;

    public Bundle() {
      super();
    }

    public Bundle(Enumeration<BundleType> type) {
      super();
      this.type = type;
    }

    /**
     * @return {@link #type} (Indicates the purpose of this bundle- how it was intended to be used.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Enumeration<BundleType> getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Indicates the purpose of this bundle- how it was intended to be used.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Bundle setTypeElement(Enumeration<BundleType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Indicates the purpose of this bundle- how it was intended to be used.
     */
    public BundleType getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Indicates the purpose of this bundle- how it was intended to be used.
     */
    public Bundle setType(BundleType value) { 
        if (this.type == null)
          this.type = new Enumeration<BundleType>();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #base} (The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public UriType getBaseElement() { 
      return this.base;
    }

    /**
     * @param value {@link #base} (The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public Bundle setBaseElement(UriType value) { 
      this.base = value;
      return this;
    }

    /**
     * @return The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    public String getBase() { 
      return this.base == null ? null : this.base.getValue();
    }

    /**
     * @param value The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).
     */
    public Bundle setBase(String value) { 
      if (Utilities.noString(value))
        this.base = null;
      else {
        if (this.base == null)
          this.base = new UriType();
        this.base.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #total} (If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
     */
    public IntegerType getTotalElement() { 
      return this.total;
    }

    /**
     * @param value {@link #total} (If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
     */
    public Bundle setTotalElement(IntegerType value) { 
      this.total = value;
      return this;
    }

    /**
     * @return If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).
     */
    public int getTotal() { 
      return this.total == null ? null : this.total.getValue();
    }

    /**
     * @param value If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).
     */
    public Bundle setTotal(int value) { 
      if (value == -1)
        this.total = null;
      else {
        if (this.total == null)
          this.total = new IntegerType();
        this.total.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #link} (A series of links that provide context to this bundle.)
     */
    public List<BundleLinkComponent> getLink() { 
      return this.link;
    }

    /**
     * @return {@link #link} (A series of links that provide context to this bundle.)
     */
    // syntactic sugar
    public BundleLinkComponent addLink() { //3
      BundleLinkComponent t = new BundleLinkComponent();
      this.link.add(t);
      return t;
    }

    /**
     * @return {@link #entry} (An entry in a bundle resource - will either contain a resource, or a deleted entry (transaction and history bundles only).)
     */
    public List<BundleEntryComponent> getEntry() { 
      return this.entry;
    }

    /**
     * @return {@link #entry} (An entry in a bundle resource - will either contain a resource, or a deleted entry (transaction and history bundles only).)
     */
    // syntactic sugar
    public BundleEntryComponent addEntry() { //3
      BundleEntryComponent t = new BundleEntryComponent();
      this.entry.add(t);
      return t;
    }

    /**
     * @return {@link #signature} (XML Digital Signature - base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getSignature" gives direct access to the value
     */
    public Base64BinaryType getSignatureElement() { 
      return this.signature;
    }

    /**
     * @param value {@link #signature} (XML Digital Signature - base64 encoded.). This is the underlying object with id, value and extensions. The accessor "getSignature" gives direct access to the value
     */
    public Bundle setSignatureElement(Base64BinaryType value) { 
      this.signature = value;
      return this;
    }

    /**
     * @return XML Digital Signature - base64 encoded.
     */
    public byte[] getSignature() { 
      return this.signature == null ? null : this.signature.getValue();
    }

    /**
     * @param value XML Digital Signature - base64 encoded.
     */
    public Bundle setSignature(byte[] value) { 
      if (value == null)
        this.signature = null;
      else {
        if (this.signature == null)
          this.signature = new Base64BinaryType();
        this.signature.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "code", "Indicates the purpose of this bundle- how it was intended to be used.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("base", "uri", "The base URL for the service that provided these resources. All relative URLs are relative to this one (equivalent to xml:base).", 0, java.lang.Integer.MAX_VALUE, base));
        childrenList.add(new Property("total", "integer", "If a set of search matches, this is the total number of matches for the search (as opposed to the number of results in this bundle).", 0, java.lang.Integer.MAX_VALUE, total));
        childrenList.add(new Property("link", "", "A series of links that provide context to this bundle.", 0, java.lang.Integer.MAX_VALUE, link));
        childrenList.add(new Property("entry", "", "An entry in a bundle resource - will either contain a resource, or a deleted entry (transaction and history bundles only).", 0, java.lang.Integer.MAX_VALUE, entry));
        childrenList.add(new Property("signature", "base64Binary", "XML Digital Signature - base64 encoded.", 0, java.lang.Integer.MAX_VALUE, signature));
      }

      public Bundle copy() {
        Bundle dst = new Bundle();
        copyValues(dst);
        dst.type = type == null ? null : type.copy();
        dst.base = base == null ? null : base.copy();
        dst.total = total == null ? null : total.copy();
        dst.link = new ArrayList<BundleLinkComponent>();
        for (BundleLinkComponent i : link)
          dst.link.add(i.copy());
        dst.entry = new ArrayList<BundleEntryComponent>();
        for (BundleEntryComponent i : entry)
          dst.entry.add(i.copy());
        dst.signature = signature == null ? null : signature.copy();
        return dst;
      }

      protected Bundle typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Bundle;
   }


}

