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

// Generated on Sat, Jun 8, 2013 18:38+1000 for FHIR v0.09

import java.util.*;

import java.net.*;
/**
 * A description of a query with a set of parameters
 */
public class Query extends Resource {

    public enum QueryOutcome {
        ok, // The query was processed successfully
        limited, // The query was processed successfully, but some additional limitations were added
        refused, // The server refused to process the query
        error, // The server tried to process the query, but some erorr occurred
        Null; // added to help the parsers
        public static QueryOutcome fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return ok;
        if ("limited".equals(codeString))
          return limited;
        if ("refused".equals(codeString))
          return refused;
        if ("error".equals(codeString))
          return error;
        throw new Exception("Unknown QueryOutcome code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case ok: return "ok";
            case limited: return "limited";
            case refused: return "refused";
            case error: return "error";
            default: return "?";
          }
        }
    }

  public class QueryOutcomeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("ok".equals(codeString))
          return QueryOutcome.ok;
        if ("limited".equals(codeString))
          return QueryOutcome.limited;
        if ("refused".equals(codeString))
          return QueryOutcome.refused;
        if ("error".equals(codeString))
          return QueryOutcome.error;
        throw new Exception("Unknown QueryOutcome code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == QueryOutcome.ok)
        return "ok";
      if (code == QueryOutcome.limited)
        return "limited";
      if (code == QueryOutcome.refused)
        return "refused";
      if (code == QueryOutcome.error)
        return "error";
      return "?";
      }
    }

    public class QueryParameterComponent extends Element {
        /**
         * Name of parameter
         */
        private String_ name;

        /**
         * Value of parameter
         */
        private String_ value;

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

        public String_ getValue() { 
          return this.value;
        }

        public void setValue(String_ value) { 
          this.value = value;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(String value) { 
          if (value == null)
            this.value = null;
          else {
            if (this.value == null)
              this.value = new String_();
            this.value.setValue(value);
          }
        }

  }

    public class QueryResponseComponent extends Element {
        /**
         * Links response to source query
         */
        private Uri identifier;

        /**
         * Outcome of processing the query
         */
        private Enumeration<QueryOutcome> outcome;

        /**
         * Total number of matching records
         */
        private Integer total;

        /**
         * Parameters server used
         */
        private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

        /**
         * To get first page
         */
        private QueryResponseFirstComponent first;

        /**
         * To get previous page
         */
        private QueryResponsePreviousComponent previous;

        /**
         * To get next page
         */
        private QueryResponseNextComponent next;

        /**
         * To get last page
         */
        private QueryResponseLastComponent last;

        /**
         * Resources that are the results of the search
         */
        private List<ResourceReference> reference = new ArrayList<ResourceReference>();

        public Uri getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Uri value) { 
          this.identifier = value;
        }

        public URI getIdentifierSimple() { 
          return this.identifier == null ? null : this.identifier.getValue();
        }

        public void setIdentifierSimple(URI value) { 
            if (this.identifier == null)
              this.identifier = new Uri();
            this.identifier.setValue(value);
        }

        public Enumeration<QueryOutcome> getOutcome() { 
          return this.outcome;
        }

        public void setOutcome(Enumeration<QueryOutcome> value) { 
          this.outcome = value;
        }

        public QueryOutcome getOutcomeSimple() { 
          return this.outcome == null ? null : this.outcome.getValue();
        }

        public void setOutcomeSimple(QueryOutcome value) { 
            if (this.outcome == null)
              this.outcome = new Enumeration<QueryOutcome>();
            this.outcome.setValue(value);
        }

        public Integer getTotal() { 
          return this.total;
        }

        public void setTotal(Integer value) { 
          this.total = value;
        }

        public int getTotalSimple() { 
          return this.total == null ? null : this.total.getValue();
        }

        public void setTotalSimple(int value) { 
          if (value == -1)
            this.total = null;
          else {
            if (this.total == null)
              this.total = new Integer();
            this.total.setValue(value);
          }
        }

        public List<QueryParameterComponent> getParameter() { 
          return this.parameter;
        }

        public QueryResponseFirstComponent getFirst() { 
          return this.first;
        }

        public void setFirst(QueryResponseFirstComponent value) { 
          this.first = value;
        }

        public QueryResponsePreviousComponent getPrevious() { 
          return this.previous;
        }

        public void setPrevious(QueryResponsePreviousComponent value) { 
          this.previous = value;
        }

        public QueryResponseNextComponent getNext() { 
          return this.next;
        }

        public void setNext(QueryResponseNextComponent value) { 
          this.next = value;
        }

        public QueryResponseLastComponent getLast() { 
          return this.last;
        }

        public void setLast(QueryResponseLastComponent value) { 
          this.last = value;
        }

        public List<ResourceReference> getReference() { 
          return this.reference;
        }

  }

    public class QueryResponseFirstComponent extends Element {
        /**
         * Parameter list
         */
        private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

        public List<QueryParameterComponent> getParameter() { 
          return this.parameter;
        }

  }

    public class QueryResponsePreviousComponent extends Element {
        /**
         * Parameter list
         */
        private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

        public List<QueryParameterComponent> getParameter() { 
          return this.parameter;
        }

  }

    public class QueryResponseNextComponent extends Element {
        /**
         * Parameter list
         */
        private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

        public List<QueryParameterComponent> getParameter() { 
          return this.parameter;
        }

  }

    public class QueryResponseLastComponent extends Element {
        /**
         * Parameter list
         */
        private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

        public List<QueryParameterComponent> getParameter() { 
          return this.parameter;
        }

  }

    /**
     * Links query and it's response(s)
     */
    private Uri identifier;

    /**
     * Set of query parameters
     */
    private List<QueryParameterComponent> parameter = new ArrayList<QueryParameterComponent>();

    /**
     * If this is a response to a query
     */
    private QueryResponseComponent response;

    public Uri getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Uri value) { 
      this.identifier = value;
    }

    public URI getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    public void setIdentifierSimple(URI value) { 
        if (this.identifier == null)
          this.identifier = new Uri();
        this.identifier.setValue(value);
    }

    public List<QueryParameterComponent> getParameter() { 
      return this.parameter;
    }

    public QueryResponseComponent getResponse() { 
      return this.response;
    }

    public void setResponse(QueryResponseComponent value) { 
      this.response = value;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Query;
   }


}

