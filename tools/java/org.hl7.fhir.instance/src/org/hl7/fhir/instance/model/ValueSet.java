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

// Generated on Wed, Oct 23, 2013 23:11+1100 for FHIR v0.12

import java.util.*;

/**
 * A value set specifies a set of codes drawn from one or more code systems.
 */
public class ValueSet extends Resource {

    public enum ValuesetStatus {
        draft, // This valueset is still under development.
        active, // This valueset is ready for normal use.
        retired, // This valueset has been withdrawn or superceded and should no longer be used.
        Null; // added to help the parsers
        public static ValuesetStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("active".equals(codeString))
          return active;
        if ("retired".equals(codeString))
          return retired;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
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

  public class ValuesetStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ValuesetStatus.draft;
        if ("active".equals(codeString))
          return ValuesetStatus.active;
        if ("retired".equals(codeString))
          return ValuesetStatus.retired;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetStatus.draft)
        return "draft";
      if (code == ValuesetStatus.active)
        return "active";
      if (code == ValuesetStatus.retired)
        return "retired";
      return "?";
      }
    }

    public enum FilterOperator {
        equal, // The property value has the concept specified by the value.
        isa, // The property value has a concept that has an is-a relationship with the value.
        isnota, // The property value has a concept that does not have an is-a relationship with the value.
        regex, // The property value representation matches the regex specified in the value.
        Null; // added to help the parsers
        public static FilterOperator fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("=".equals(codeString))
          return equal;
        if ("is-a".equals(codeString))
          return isa;
        if ("is-not-a".equals(codeString))
          return isnota;
        if ("regex".equals(codeString))
          return regex;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case equal: return "=";
            case isa: return "is-a";
            case isnota: return "is-not-a";
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
        if ("is-a".equals(codeString))
          return FilterOperator.isa;
        if ("is-not-a".equals(codeString))
          return FilterOperator.isnota;
        if ("regex".equals(codeString))
          return FilterOperator.regex;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == FilterOperator.equal)
        return "=";
      if (code == FilterOperator.isa)
        return "is-a";
      if (code == FilterOperator.isnota)
        return "is-not-a";
      if (code == FilterOperator.regex)
        return "regex";
      return "?";
      }
    }

    public class ValueSetDefineComponent extends BackboneElement {
        /**
         * URI to identify the code system.
         */
        protected Uri system;

        /**
         * The version of this code system that the defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need t obe maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        protected String_ version;

        /**
         * If code comparison is case sensitive when codes within this systemare compared to each other.
         */
        protected Boolean caseSensitive;

        /**
         * Concepts in the code system.
         */
        protected List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(String value) { 
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

        public Boolean getCaseSensitive() { 
          return this.caseSensitive;
        }

        public void setCaseSensitive(Boolean value) { 
          this.caseSensitive = value;
        }

        public boolean getCaseSensitiveSimple() { 
          return this.caseSensitive == null ? null : this.caseSensitive.getValue();
        }

        public void setCaseSensitiveSimple(boolean value) { 
          if (value == false)
            this.caseSensitive = null;
          else {
            if (this.caseSensitive == null)
              this.caseSensitive = new Boolean();
            this.caseSensitive.setValue(value);
          }
        }

        public List<ValueSetDefineConceptComponent> getConcept() { 
          return this.concept;
        }

    // syntactic sugar
        public ValueSetDefineConceptComponent addConcept() { 
          ValueSetDefineConceptComponent t = new ValueSetDefineConceptComponent();
          this.concept.add(t);
          return t;
        }

      public ValueSetDefineComponent copy(ValueSet e) {
        ValueSetDefineComponent dst = e.new ValueSetDefineComponent();
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.caseSensitive = caseSensitive == null ? null : caseSensitive.copy();
        dst.concept = new ArrayList<ValueSetDefineConceptComponent>();
        for (ValueSetDefineConceptComponent i : concept)
          dst.concept.add(i.copy(e));
        return dst;
      }

  }

    public class ValueSetDefineConceptComponent extends BackboneElement {
        /**
         * Code that identifies concept.
         */
        protected Code code;

        /**
         * If this code is not for use as a real concept.
         */
        protected Boolean abstract_;

        /**
         * Text to Display to the user.
         */
        protected String_ display;

        /**
         * The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        protected String_ definition;

        /**
         * Child Concepts (is-a / contains).
         */
        protected List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

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

    // syntactic sugar
        public ValueSetDefineConceptComponent addConcept() { 
          ValueSetDefineConceptComponent t = new ValueSetDefineConceptComponent();
          this.concept.add(t);
          return t;
        }

      public ValueSetDefineConceptComponent copy(ValueSet e) {
        ValueSetDefineConceptComponent dst = e.new ValueSetDefineConceptComponent();
        dst.code = code == null ? null : code.copy();
        dst.abstract_ = abstract_ == null ? null : abstract_.copy();
        dst.display = display == null ? null : display.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.concept = new ArrayList<ValueSetDefineConceptComponent>();
        for (ValueSetDefineConceptComponent i : concept)
          dst.concept.add(i.copy(e));
        return dst;
      }

  }

    public class ValueSetComposeComponent extends BackboneElement {
        /**
         * Includes the contents of the referenced value set as part of the contents of this value set.
         */
        protected List<Uri> import_ = new ArrayList<Uri>();

        /**
         * Include one or more codes from a code system.
         */
        protected List<ConceptSetComponent> include = new ArrayList<ConceptSetComponent>();

        /**
         * Exclude one or more codes from the value set.
         */
        protected List<ConceptSetComponent> exclude = new ArrayList<ConceptSetComponent>();

        public List<Uri> getImport() { 
          return this.import_;
        }

    // syntactic sugar
        public Uri addImport() { 
          Uri t = new Uri();
          this.import_.add(t);
          return t;
        }

        public Uri addImportSimple(String value) { 
          Uri t = new Uri();
          t.setValue(value);
          this.import_.add(t);
          return t;
        }

        public List<ConceptSetComponent> getInclude() { 
          return this.include;
        }

    // syntactic sugar
        public ConceptSetComponent addInclude() { 
          ConceptSetComponent t = new ConceptSetComponent();
          this.include.add(t);
          return t;
        }

        public List<ConceptSetComponent> getExclude() { 
          return this.exclude;
        }

    // syntactic sugar
        public ConceptSetComponent addExclude() { 
          ConceptSetComponent t = new ConceptSetComponent();
          this.exclude.add(t);
          return t;
        }

      public ValueSetComposeComponent copy(ValueSet e) {
        ValueSetComposeComponent dst = e.new ValueSetComposeComponent();
        dst.import_ = new ArrayList<Uri>();
        for (Uri i : import_)
          dst.import_.add(i.copy());
        dst.include = new ArrayList<ConceptSetComponent>();
        for (ConceptSetComponent i : include)
          dst.include.add(i.copy(e));
        dst.exclude = new ArrayList<ConceptSetComponent>();
        for (ConceptSetComponent i : exclude)
          dst.exclude.add(i.copy(e));
        return dst;
      }

  }

    public class ConceptSetComponent extends BackboneElement {
        /**
         * The code system from which the selected codes come from.
         */
        protected Uri system;

        /**
         * The version of the code system that the codes are selected from.
         */
        protected String_ version;

        /**
         * Specifies a code or concept to be included or excluded.
         */
        protected List<Code> code = new ArrayList<Code>();

        /**
         * Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.
         */
        protected List<ConceptSetFilterComponent> filter = new ArrayList<ConceptSetFilterComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(String value) { 
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

        public List<Code> getCode() { 
          return this.code;
        }

    // syntactic sugar
        public Code addCode() { 
          Code t = new Code();
          this.code.add(t);
          return t;
        }

        public Code addCodeSimple(String value) { 
          Code t = new Code();
          t.setValue(value);
          this.code.add(t);
          return t;
        }

        public List<ConceptSetFilterComponent> getFilter() { 
          return this.filter;
        }

    // syntactic sugar
        public ConceptSetFilterComponent addFilter() { 
          ConceptSetFilterComponent t = new ConceptSetFilterComponent();
          this.filter.add(t);
          return t;
        }

      public ConceptSetComponent copy(ValueSet e) {
        ConceptSetComponent dst = e.new ConceptSetComponent();
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.code = new ArrayList<Code>();
        for (Code i : code)
          dst.code.add(i.copy());
        dst.filter = new ArrayList<ConceptSetFilterComponent>();
        for (ConceptSetFilterComponent i : filter)
          dst.filter.add(i.copy(e));
        return dst;
      }

  }

    public class ConceptSetFilterComponent extends BackboneElement {
        /**
         * A code that identifies a property defined in the code system.
         */
        protected Code property;

        /**
         * The kind of operation to perform as part of the filter criteria.
         */
        protected Enumeration<FilterOperator> op;

        /**
         * The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        protected Code value;

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

      public ConceptSetFilterComponent copy(ValueSet e) {
        ConceptSetFilterComponent dst = e.new ConceptSetFilterComponent();
        dst.property = property == null ? null : property.copy();
        dst.op = op == null ? null : op.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public class ValueSetExpansionComponent extends BackboneElement {
        /**
         * An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.
         */
        protected Identifier identifier;

        /**
         * Time valueset expansion happened.
         */
        protected Instant timestamp;

        /**
         * Codes in the value set.
         */
        protected List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Identifier value) { 
          this.identifier = value;
        }

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

    // syntactic sugar
        public ValueSetExpansionContainsComponent addContains() { 
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

      public ValueSetExpansionComponent copy(ValueSet e) {
        ValueSetExpansionComponent dst = e.new ValueSetExpansionComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.timestamp = timestamp == null ? null : timestamp.copy();
        dst.contains = new ArrayList<ValueSetExpansionContainsComponent>();
        for (ValueSetExpansionContainsComponent i : contains)
          dst.contains.add(i.copy(e));
        return dst;
      }

  }

    public class ValueSetExpansionContainsComponent extends BackboneElement {
        /**
         * System value for the code.
         */
        protected Uri system;

        /**
         * Code - if blank, this is not a choosable code.
         */
        protected Code code;

        /**
         * User display for the concept.
         */
        protected String_ display;

        /**
         * Codes contained in this concept.
         */
        protected List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        public Uri getSystem() { 
          return this.system;
        }

        public void setSystem(Uri value) { 
          this.system = value;
        }

        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        public void setSystemSimple(String value) { 
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

    // syntactic sugar
        public ValueSetExpansionContainsComponent addContains() { 
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

      public ValueSetExpansionContainsComponent copy(ValueSet e) {
        ValueSetExpansionContainsComponent dst = e.new ValueSetExpansionContainsComponent();
        dst.system = system == null ? null : system.copy();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.contains = new ArrayList<ValueSetExpansionContainsComponent>();
        for (ValueSetExpansionContainsComponent i : contains)
          dst.contains.add(i.copy(e));
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected String_ identifier;

    /**
     * The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected String_ version;

    /**
     * A free text natural language name describing the value set.
     */
    protected String_ name;

    /**
     * The name of the individual or organization that published the value set.
     */
    protected String_ publisher;

    /**
     * Contacts of the publisher to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the use of the value set - reason for definition, conditions of use, etc.
     */
    protected String_ description;

    /**
     * A copyright statement relating to the value set and/or its contents.
     */
    protected String_ copyright;

    /**
     * The status of the value set.
     */
    protected Enumeration<ValuesetStatus> status;

    /**
     * This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected Boolean experimental;

    /**
     * Whether this is intended to be used with an extensible binding or not.
     */
    protected Boolean extensible;

    /**
     * The date that the value set status was last changed.
     */
    protected DateTime date;

    /**
     * When value set defines its own codes.
     */
    protected ValueSetDefineComponent define;

    /**
     * When value set includes codes from elsewhere.
     */
    protected ValueSetComposeComponent compose;

    /**
     * When value set is an expansion.
     */
    protected ValueSetExpansionComponent expansion;

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
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
    }

    public String_ getCopyright() { 
      return this.copyright;
    }

    public void setCopyright(String_ value) { 
      this.copyright = value;
    }

    public String getCopyrightSimple() { 
      return this.copyright == null ? null : this.copyright.getValue();
    }

    public void setCopyrightSimple(String value) { 
      if (value == null)
        this.copyright = null;
      else {
        if (this.copyright == null)
          this.copyright = new String_();
        this.copyright.setValue(value);
      }
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

    public Boolean getExtensible() { 
      return this.extensible;
    }

    public void setExtensible(Boolean value) { 
      this.extensible = value;
    }

    public boolean getExtensibleSimple() { 
      return this.extensible == null ? null : this.extensible.getValue();
    }

    public void setExtensibleSimple(boolean value) { 
      if (value == false)
        this.extensible = null;
      else {
        if (this.extensible == null)
          this.extensible = new Boolean();
        this.extensible.setValue(value);
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

      public ValueSet copy() {
        ValueSet dst = new ValueSet();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.copyright = copyright == null ? null : copyright.copy();
        dst.status = status == null ? null : status.copy();
        dst.experimental = experimental == null ? null : experimental.copy();
        dst.extensible = extensible == null ? null : extensible.copy();
        dst.date = date == null ? null : date.copy();
        dst.define = define == null ? null : define.copy(dst);
        dst.compose = compose == null ? null : compose.copy(dst);
        dst.expansion = expansion == null ? null : expansion.copy(dst);
        return dst;
      }

      protected ValueSet typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ValueSet;
   }


}

