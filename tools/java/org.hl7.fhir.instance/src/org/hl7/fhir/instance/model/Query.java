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
 * A description of a query with a set of parameters.
 */
public class Query extends DomainResource {

    public enum QueryOutcome {
        OK, // The query was processed successfully.
        LIMITED, // The query was processed successfully, but some additional limitations were added.
        REFUSED, // The server refused to process the query.
        ERROR, // The server tried to process the query, but some error occurred.
        NULL; // added to help the parsers
        public static QueryOutcome fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return OK;
        if ("limited".equals(codeString))
          return LIMITED;
        if ("refused".equals(codeString))
          return REFUSED;
        if ("error".equals(codeString))
          return ERROR;
        throw new Exception("Unknown QueryOutcome code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case OK: return "ok";
            case LIMITED: return "limited";
            case REFUSED: return "refused";
            case ERROR: return "error";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case OK: return "The query was processed successfully.";
            case LIMITED: return "The query was processed successfully, but some additional limitations were added.";
            case REFUSED: return "The server refused to process the query.";
            case ERROR: return "The server tried to process the query, but some error occurred.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case OK: return "ok";
            case LIMITED: return "limited";
            case REFUSED: return "refused";
            case ERROR: return "error";
            default: return "?";
          }
        }
    }

  public static class QueryOutcomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return QueryOutcome.OK;
        if ("limited".equals(codeString))
          return QueryOutcome.LIMITED;
        if ("refused".equals(codeString))
          return QueryOutcome.REFUSED;
        if ("error".equals(codeString))
          return QueryOutcome.ERROR;
        throw new Exception("Unknown QueryOutcome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QueryOutcome.OK)
        return "ok";
      if (code == QueryOutcome.LIMITED)
        return "limited";
      if (code == QueryOutcome.REFUSED)
        return "refused";
      if (code == QueryOutcome.ERROR)
        return "error";
      return "?";
      }
    }

    public static class QueryResponseComponent extends BackboneElement {
        /**
         * Links response to source query.
         */
        protected UriType identifier;

        /**
         * Outcome of processing the query.
         */
        protected Enumeration<QueryOutcome> outcome;

        /**
         * Total number of matching records.
         */
        protected IntegerType total;

        /**
         * Parameters server used.
         */
        protected List<Extension> parameter = new ArrayList<Extension>();

        /**
         * To get first page (if paged).
         */
        protected List<Extension> first = new ArrayList<Extension>();

        /**
         * To get previous page (if paged).
         */
        protected List<Extension> previous = new ArrayList<Extension>();

        /**
         * To get next page (if paged).
         */
        protected List<Extension> next = new ArrayList<Extension>();

        /**
         * To get last page (if paged).
         */
        protected List<Extension> last = new ArrayList<Extension>();

        /**
         * Resources that are the results of the search.
         */
        protected List<Reference> reference = new ArrayList<Reference>();
        /**
         * The actual objects that are the target of the reference (Resources that are the results of the search.)
         */
        protected List<Resource> referenceTarget = new ArrayList<Resource>();


        private static final long serialVersionUID = -1765656247L;

      public QueryResponseComponent() {
        super();
      }

      public QueryResponseComponent(UriType identifier, Enumeration<QueryOutcome> outcome) {
        super();
        this.identifier = identifier;
        this.outcome = outcome;
      }

        /**
         * @return {@link #identifier} (Links response to source query.). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
         */
        public UriType getIdentifierElement() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Links response to source query.). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
         */
        public QueryResponseComponent setIdentifierElement(UriType value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return Links response to source query.
         */
        public String getIdentifier() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        /**
         * @param value Links response to source query.
         */
        public QueryResponseComponent setIdentifier(String value) { 
            if (this.identifier == null)
              this.identifier = new UriType();
            this.identifier.setValue(value);
          return this;
        }

        /**
         * @return {@link #outcome} (Outcome of processing the query.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
         */
        public Enumeration<QueryOutcome> getOutcomeElement() { 
          return this.outcome;
        }

        /**
         * @param value {@link #outcome} (Outcome of processing the query.). This is the underlying object with id, value and extensions. The accessor "getOutcome" gives direct access to the value
         */
        public QueryResponseComponent setOutcomeElement(Enumeration<QueryOutcome> value) { 
          this.outcome = value;
          return this;
        }

        /**
         * @return Outcome of processing the query.
         */
        public QueryOutcome getOutcome() { 
          return this.outcome == null ? null : this.outcome.getValue();
        }

        /**
         * @param value Outcome of processing the query.
         */
        public QueryResponseComponent setOutcome(QueryOutcome value) { 
            if (this.outcome == null)
              this.outcome = new Enumeration<QueryOutcome>();
            this.outcome.setValue(value);
          return this;
        }

        /**
         * @return {@link #total} (Total number of matching records.). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
         */
        public IntegerType getTotalElement() { 
          return this.total;
        }

        /**
         * @param value {@link #total} (Total number of matching records.). This is the underlying object with id, value and extensions. The accessor "getTotal" gives direct access to the value
         */
        public QueryResponseComponent setTotalElement(IntegerType value) { 
          this.total = value;
          return this;
        }

        /**
         * @return Total number of matching records.
         */
        public int getTotal() { 
          return this.total == null ? null : this.total.getValue();
        }

        /**
         * @param value Total number of matching records.
         */
        public QueryResponseComponent setTotal(int value) { 
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
         * @return {@link #parameter} (Parameters server used.)
         */
        public List<Extension> getParameter() { 
          return this.parameter;
        }

        /**
         * @return {@link #parameter} (Parameters server used.)
         */
    // syntactic sugar
        public Extension addParameter() { //3
          Extension t = new Extension();
          this.parameter.add(t);
          return t;
        }

        /**
         * @return {@link #first} (To get first page (if paged).)
         */
        public List<Extension> getFirst() { 
          return this.first;
        }

        /**
         * @return {@link #first} (To get first page (if paged).)
         */
    // syntactic sugar
        public Extension addFirst() { //3
          Extension t = new Extension();
          this.first.add(t);
          return t;
        }

        /**
         * @return {@link #previous} (To get previous page (if paged).)
         */
        public List<Extension> getPrevious() { 
          return this.previous;
        }

        /**
         * @return {@link #previous} (To get previous page (if paged).)
         */
    // syntactic sugar
        public Extension addPrevious() { //3
          Extension t = new Extension();
          this.previous.add(t);
          return t;
        }

        /**
         * @return {@link #next} (To get next page (if paged).)
         */
        public List<Extension> getNext() { 
          return this.next;
        }

        /**
         * @return {@link #next} (To get next page (if paged).)
         */
    // syntactic sugar
        public Extension addNext() { //3
          Extension t = new Extension();
          this.next.add(t);
          return t;
        }

        /**
         * @return {@link #last} (To get last page (if paged).)
         */
        public List<Extension> getLast() { 
          return this.last;
        }

        /**
         * @return {@link #last} (To get last page (if paged).)
         */
    // syntactic sugar
        public Extension addLast() { //3
          Extension t = new Extension();
          this.last.add(t);
          return t;
        }

        /**
         * @return {@link #reference} (Resources that are the results of the search.)
         */
        public List<Reference> getReference() { 
          return this.reference;
        }

        /**
         * @return {@link #reference} (Resources that are the results of the search.)
         */
    // syntactic sugar
        public Reference addReference() { //3
          Reference t = new Reference();
          this.reference.add(t);
          return t;
        }

        /**
         * @return {@link #reference} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Resources that are the results of the search.)
         */
        public List<Resource> getReferenceTarget() { 
          return this.referenceTarget;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "uri", "Links response to source query.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("outcome", "code", "Outcome of processing the query.", 0, java.lang.Integer.MAX_VALUE, outcome));
          childrenList.add(new Property("total", "integer", "Total number of matching records.", 0, java.lang.Integer.MAX_VALUE, total));
          childrenList.add(new Property("parameter", "Extension", "Parameters server used.", 0, java.lang.Integer.MAX_VALUE, parameter));
          childrenList.add(new Property("first", "Extension", "To get first page (if paged).", 0, java.lang.Integer.MAX_VALUE, first));
          childrenList.add(new Property("previous", "Extension", "To get previous page (if paged).", 0, java.lang.Integer.MAX_VALUE, previous));
          childrenList.add(new Property("next", "Extension", "To get next page (if paged).", 0, java.lang.Integer.MAX_VALUE, next));
          childrenList.add(new Property("last", "Extension", "To get last page (if paged).", 0, java.lang.Integer.MAX_VALUE, last));
          childrenList.add(new Property("reference", "Reference(Any)", "Resources that are the results of the search.", 0, java.lang.Integer.MAX_VALUE, reference));
        }

      public QueryResponseComponent copy() {
        QueryResponseComponent dst = new QueryResponseComponent();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.outcome = outcome == null ? null : outcome.copy();
        dst.total = total == null ? null : total.copy();
        dst.parameter = new ArrayList<Extension>();
        for (Extension i : parameter)
          dst.parameter.add(i.copy());
        dst.first = new ArrayList<Extension>();
        for (Extension i : first)
          dst.first.add(i.copy());
        dst.previous = new ArrayList<Extension>();
        for (Extension i : previous)
          dst.previous.add(i.copy());
        dst.next = new ArrayList<Extension>();
        for (Extension i : next)
          dst.next.add(i.copy());
        dst.last = new ArrayList<Extension>();
        for (Extension i : last)
          dst.last.add(i.copy());
        dst.reference = new ArrayList<Reference>();
        for (Reference i : reference)
          dst.reference.add(i.copy());
        return dst;
      }

  }

    /**
     * Links query and its response(s).
     */
    protected UriType identifier;

    /**
     * Set of query parameters with values.
     */
    protected List<Extension> parameter = new ArrayList<Extension>();

    /**
     * If this is a response to a query.
     */
    protected QueryResponseComponent response;

    private static final long serialVersionUID = 1612122334L;

    public Query() {
      super();
    }

    public Query(UriType identifier) {
      super();
      this.identifier = identifier;
    }

    /**
     * @return {@link #identifier} (Links query and its response(s).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public UriType getIdentifierElement() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Links query and its response(s).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public Query setIdentifierElement(UriType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return Links query and its response(s).
     */
    public String getIdentifier() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value Links query and its response(s).
     */
    public Query setIdentifier(String value) { 
        if (this.identifier == null)
          this.identifier = new UriType();
        this.identifier.setValue(value);
      return this;
    }

    /**
     * @return {@link #parameter} (Set of query parameters with values.)
     */
    public List<Extension> getParameter() { 
      return this.parameter;
    }

    /**
     * @return {@link #parameter} (Set of query parameters with values.)
     */
    // syntactic sugar
    public Extension addParameter() { //3
      Extension t = new Extension();
      this.parameter.add(t);
      return t;
    }

    /**
     * @return {@link #response} (If this is a response to a query.)
     */
    public QueryResponseComponent getResponse() { 
      return this.response;
    }

    /**
     * @param value {@link #response} (If this is a response to a query.)
     */
    public Query setResponse(QueryResponseComponent value) { 
      this.response = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "uri", "Links query and its response(s).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("parameter", "Extension", "Set of query parameters with values.", 0, java.lang.Integer.MAX_VALUE, parameter));
        childrenList.add(new Property("response", "", "If this is a response to a query.", 0, java.lang.Integer.MAX_VALUE, response));
      }

      public Query copy() {
        Query dst = new Query();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.parameter = new ArrayList<Extension>();
        for (Extension i : parameter)
          dst.parameter.add(i.copy());
        dst.response = response == null ? null : response.copy();
        return dst;
      }

      protected Query typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Query;
   }


}

