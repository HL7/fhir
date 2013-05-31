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

// Generated on Fri, May 31, 2013 07:30+1000 for FHIR v0.09

import java.util.*;

import java.net.*;
/**
 * A value set specifies a set of codes drawn from one or more code systems
 */
public class ValueSet extends Resource {

    public enum ValuesetStatus {
        draft, // This valueset is still under development
        testing, // This valueset was authored for testing purposes (or education/evaluation/evangelisation)
        review, // This valueset is undergoing review to check that it is ready for production use
        production, // This valueset is ready for use in production systems
        withdrawn, // This valueset has been withdrawn and should no longer be used
        superseded, // This valueset has been replaced and a different valueset should be used in its place
        Null; // added to help the parsers
        public static ValuesetStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("testing".equals(codeString))
          return testing;
        if ("review".equals(codeString))
          return review;
        if ("production".equals(codeString))
          return production;
        if ("withdrawn".equals(codeString))
          return withdrawn;
        if ("superseded".equals(codeString))
          return superseded;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case draft: return "draft";
            case testing: return "testing";
            case review: return "review";
            case production: return "production";
            case withdrawn: return "withdrawn";
            case superseded: return "superseded";
            default: return "?";
          }
        }
    }

  public class ValuesetStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ValuesetStatus.draft;
        if ("testing".equals(codeString))
          return ValuesetStatus.testing;
        if ("review".equals(codeString))
          return ValuesetStatus.review;
        if ("production".equals(codeString))
          return ValuesetStatus.production;
        if ("withdrawn".equals(codeString))
          return ValuesetStatus.withdrawn;
        if ("superseded".equals(codeString))
          return ValuesetStatus.superseded;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetStatus.draft)
        return "draft";
      if (code == ValuesetStatus.testing)
        return "testing";
      if (code == ValuesetStatus.review)
        return "review";
      if (code == ValuesetStatus.production)
        return "production";
      if (code == ValuesetStatus.withdrawn)
        return "withdrawn";
      if (code == ValuesetStatus.superseded)
        return "superseded";
      return "?";
      }
    }

    public enum CodeSelectionMode {
        code, // Only this code is selected
        children, // Only the immediate children (codes with a is_a relationship) are selected, but not this code itself
        descendants, // All descendants of this code are selected, but not this code itself
        all, // This code and any descendants are selected
        system, // All codes from the specified code system
        Null; // added to help the parsers
        public static CodeSelectionMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("code".equals(codeString))
          return code;
        if ("children".equals(codeString))
          return children;
        if ("descendants".equals(codeString))
          return descendants;
        if ("all".equals(codeString))
          return all;
        if ("system".equals(codeString))
          return system;
        throw new Exception("Unknown CodeSelectionMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case code: return "code";
            case children: return "children";
            case descendants: return "descendants";
            case all: return "all";
            case system: return "system";
            default: return "?";
          }
        }
    }

  public class CodeSelectionModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("code".equals(codeString))
          return CodeSelectionMode.code;
        if ("children".equals(codeString))
          return CodeSelectionMode.children;
        if ("descendants".equals(codeString))
          return CodeSelectionMode.descendants;
        if ("all".equals(codeString))
          return CodeSelectionMode.all;
        if ("system".equals(codeString))
          return CodeSelectionMode.system;
        throw new Exception("Unknown CodeSelectionMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == CodeSelectionMode.code)
        return "code";
      if (code == CodeSelectionMode.children)
        return "children";
      if (code == CodeSelectionMode.descendants)
        return "descendants";
      if (code == CodeSelectionMode.all)
        return "all";
      if (code == CodeSelectionMode.system)
        return "system";
      return "?";
      }
    }

    public enum FilterOperator {
        equal, // The property value has the concept specified by the value
        isA, // The property value has a concept that has an is_a relationship with the value
        isNotA, // The property value has a concept that does not have an is_a relationship with the value
        regex, // The property value representation matches the regex specified in the value
        Null; // added to help the parsers
        public static FilterOperator fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("=".equals(codeString))
          return equal;
        if ("is_a".equals(codeString))
          return isA;
        if ("is_not_a".equals(codeString))
          return isNotA;
        if ("regex".equals(codeString))
          return regex;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case equal: return "=";
            case isA: return "is_a";
            case isNotA: return "is_not_a";
            case regex: return "regex";
            default: return "?";
          }
        }
    }

  public class FilterOperatorEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("=".equals(codeString))
          return FilterOperator.equal;
        if ("is_a".equals(codeString))
          return FilterOperator.isA;
        if ("is_not_a".equals(codeString))
          return FilterOperator.isNotA;
        if ("regex".equals(codeString))
          return FilterOperator.regex;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == FilterOperator.equal)
        return "=";
      if (code == FilterOperator.isA)
        return "is_a";
      if (code == FilterOperator.isNotA)
        return "is_not_a";
      if (code == FilterOperator.regex)
        return "regex";
      return "?";
      }
    }

    public class ValueSetDefineComponent extends Element {
        /**
         * URI to identify the code system
         */
        private Uri system;

        /**
         * Concepts in the code system
         */
        private List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public URI getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(URI value) { 
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
        }

        public List<ValueSetDefineConceptComponent> getConcept() { 
          return this.concept;
        }

  }

    public class ValueSetDefineConceptComponent extends Element {
        /**
         * Code that identifies concept
         */
        private Code code;

        /**
         * If this code is not for use as a real concept
         */
        private Boolean abstract_;

        /**
         * Text to Display to the user
         */
        private String_ display;

        /**
         * Formal Definition
         */
        private String_ definition;

        /**
         * Child Concepts (is-a / contains)
         */
        private List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

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

        public Boolean getAbstract() { 
          return this.abstract_;
        }

        public void setAbstract(Boolean value) { 
          this.abstract_ = value;
        }

        public boolean getAbstractSimple() { 
          return this.abstract_ == null ? null : this.abstract_.getValue();
        }

        public void setAbstractSimple(boolean value) { 
          if (value == false)
            this.abstract_ = null;
          else {
            if (this.abstract_ == null)
              this.abstract_ = new Boolean();
            this.abstract_.setValue(value);
          }
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public void setDisplay(String_ value) { 
          this.display = value;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        public void setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
            this.display.setValue(value);
          }
        }

        public String_ getDefinition() { 
          return this.definition;
        }

        public void setDefinition(String_ value) { 
          this.definition = value;
        }

        public String getDefinitionSimple() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        public void setDefinitionSimple(String value) { 
          if (value == null)
            this.definition = null;
          else {
            if (this.definition == null)
              this.definition = new String_();
            this.definition.setValue(value);
          }
        }

        public List<ValueSetDefineConceptComponent> getConcept() { 
          return this.concept;
        }

  }

    public class ValueSetComposeComponent extends Element {
        /**
         * Includes the contents of the referenced value set as part of the contents of this value set
         */
        private List<Uri> import_ = new ArrayList<Uri>();

        /**
         * Include one or more codes from a code system
         */
        private List<ConceptSetComponent> include = new ArrayList<ConceptSetComponent>();

        /**
         * Exclude one or more codes from the value set
         */
        private List<ConceptSetComponent> exclude = new ArrayList<ConceptSetComponent>();

        public List<Uri> getImport() { 
          return this.import_;
        }

        public List<ConceptSetComponent> getInclude() { 
          return this.include;
        }

        public List<ConceptSetComponent> getExclude() { 
          return this.exclude;
        }

  }

    public class ConceptSetComponent extends Element {
        /**
         * The code system from which the selected codes come from
         */
        private Uri system;

        /**
         * The version of the code system that the codes are selected from
         */
        private String_ version;

        /**
         * The mode of selection - whether the code itself, and/or its descendants are being selected
         */
        private Enumeration<CodeSelectionMode> mode;

        /**
         * Specifies a code or concept to be included or excluded as specified by the mode from the value set
         */
        private List<Code> code = new ArrayList<Code>();

        /**
         * Select concepts by specify a matching criteria based on the properties defined by the system. If multiple filters are specified, they must all be true
         */
        private List<ConceptSetFilterComponent> filter = new ArrayList<ConceptSetFilterComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public URI getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(URI value) { 
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
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

        public Enumeration<CodeSelectionMode> getMode() { 
          return this.mode;
        }

        public void setMode(Enumeration<CodeSelectionMode> value) { 
          this.mode = value;
        }

        public CodeSelectionMode getModeSimple() { 
          return this.mode == null ? null : this.mode.getValue();
        }

        public void setModeSimple(CodeSelectionMode value) { 
            if (this.mode == null)
              this.mode = new Enumeration<CodeSelectionMode>();
            this.mode.setValue(value);
        }

        public List<Code> getCode() { 
          return this.code;
        }

        public List<ConceptSetFilterComponent> getFilter() { 
          return this.filter;
        }

  }

    public class ConceptSetFilterComponent extends Element {
        /**
         * A code that identifies a property defined in the code system
         */
        private Code property;

        /**
         * The kind of operation to perform as part of the filter criteria
         */
        private Enumeration<FilterOperator> op;

        /**
         * The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value
         */
        private Code value;

        public Code getProperty() { 
          return this.property;
        }

        public void setProperty(Code value) { 
          this.property = value;
        }

        public String getPropertySimple() { 
          return this.property == null ? null : this.property.getValue();
        }

        public void setPropertySimple(String value) { 
            if (this.property == null)
              this.property = new Code();
            this.property.setValue(value);
        }

        public Enumeration<FilterOperator> getOp() { 
          return this.op;
        }

        public void setOp(Enumeration<FilterOperator> value) { 
          this.op = value;
        }

        public FilterOperator getOpSimple() { 
          return this.op == null ? null : this.op.getValue();
        }

        public void setOpSimple(FilterOperator value) { 
            if (this.op == null)
              this.op = new Enumeration<FilterOperator>();
            this.op.setValue(value);
        }

        public Code getValue() { 
          return this.value;
        }

        public void setValue(Code value) { 
          this.value = value;
        }

        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        public void setValueSimple(String value) { 
            if (this.value == null)
              this.value = new Code();
            this.value.setValue(value);
        }

  }

    public class ValueSetExpansionComponent extends Element {
        /**
         * Time valueset expansion happened
         */
        private Instant timestamp;

        /**
         * Codes in the value set
         */
        private List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        public Instant getTimestamp() { 
          return this.timestamp;
        }

        public void setTimestamp(Instant value) { 
          this.timestamp = value;
        }

        public Calendar getTimestampSimple() { 
          return this.timestamp == null ? null : this.timestamp.getValue();
        }

        public void setTimestampSimple(Calendar value) { 
            if (this.timestamp == null)
              this.timestamp = new Instant();
            this.timestamp.setValue(value);
        }

        public List<ValueSetExpansionContainsComponent> getContains() { 
          return this.contains;
        }

  }

    public class ValueSetExpansionContainsComponent extends Element {
        /**
         * System value for the code
         */
        private Uri system;

        /**
         * Code - if blank, this is not a choosable code
         */
        private Code code;

        /**
         * User display for the concept
         */
        private String_ display;

        /**
         * Codes contained in this concept
         */
        private List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public URI getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(URI value) { 
          if (value == null)
            this.system = null;
          else {
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
          }
        }

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
          if (value == null)
            this.code = null;
          else {
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          }
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public void setDisplay(String_ value) { 
          this.display = value;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        public void setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
            this.display.setValue(value);
          }
        }

        public List<ValueSetExpansionContainsComponent> getContains() { 
          return this.contains;
        }

  }

    /**
     * The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI)
     */
    private String_ identifier;

    /**
     * The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp
     */
    private String_ version;

    /**
     * A free text natural language name describing the value set
     */
    private String_ name;

    /**
     * The name of the individual or organization that published the value set
     */
    private String_ publisher;

    /**
     * Contacts of the publisher to assist a user in finding and communicating with the publisher
     */
    private List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the use of the value set - reason for definition, conditions of use, etc.
     */
    private String_ description;

    /**
     * The status of the value set
     */
    private Enumeration<ValuesetStatus> status;

    /**
     * The date that the value set status was last changed
     */
    private DateTime date;

    /**
     * When value set defines it's own codes
     */
    private ValueSetDefineComponent define;

    /**
     * When value set includes codes from elsewhere
     */
    private ValueSetComposeComponent compose;

    /**
     * When value set is an expansion
     */
    private ValueSetExpansionComponent expansion;

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
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
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
      if (value == null)
        this.publisher = null;
      else {
        if (this.publisher == null)
          this.publisher = new String_();
        this.publisher.setValue(value);
      }
    }

    public List<Contact> getTelecom() { 
      return this.telecom;
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
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
    }

    public Enumeration<ValuesetStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<ValuesetStatus> value) { 
      this.status = value;
    }

    public ValuesetStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(ValuesetStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ValuesetStatus>();
        this.status.setValue(value);
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
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
    }

    public ValueSetDefineComponent getDefine() { 
      return this.define;
    }

    public void setDefine(ValueSetDefineComponent value) { 
      this.define = value;
    }

    public ValueSetComposeComponent getCompose() { 
      return this.compose;
    }

    public void setCompose(ValueSetComposeComponent value) { 
      this.compose = value;
    }

    public ValueSetExpansionComponent getExpansion() { 
      return this.expansion;
    }

    public void setExpansion(ValueSetExpansionComponent value) { 
      this.expansion = value;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ValueSet;
   }


}

