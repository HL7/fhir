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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

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

  public static class ValuesetStatusEnumFactory implements EnumFactory {
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
        in, // The property value is in the set of codes or concepts identified by the value.
        notIn, // The property value is not in the set of codes or concepts identified by the value.
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
        if ("in".equals(codeString))
          return in;
        if ("not in".equals(codeString))
          return notIn;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case equal: return "=";
            case isa: return "is-a";
            case isnota: return "is-not-a";
            case regex: return "regex";
            case in: return "in";
            case notIn: return "not in";
            default: return "?";
          }
        }
    }

  public static class FilterOperatorEnumFactory implements EnumFactory {
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
        if ("in".equals(codeString))
          return FilterOperator.in;
        if ("not in".equals(codeString))
          return FilterOperator.notIn;
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
      if (code == FilterOperator.in)
        return "in";
      if (code == FilterOperator.notIn)
        return "not in";
      return "?";
      }
    }

    public static class ValueSetDefineComponent extends BackboneElement {
        /**
         * URI to identify the code system.
         */
        protected UriType system;

        /**
         * The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        protected StringType version;

        /**
         * If code comparison is case sensitive when codes within this system are compared to each other.
         */
        protected BooleanType caseSensitive;

        /**
         * Concepts in the code system.
         */
        protected List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

        private static final long serialVersionUID = 819436673L;

      public ValueSetDefineComponent() {
        super();
      }

      public ValueSetDefineComponent(UriType system) {
        super();
        this.system = system;
      }

        /**
         * @return {@link #system} (URI to identify the code system.)
         */
        public UriType getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (URI to identify the code system.)
         */
        public ValueSetDefineComponent setSystem(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return URI to identify the code system.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value URI to identify the code system.
         */
        public ValueSetDefineComponent setSystemSimple(String value) { 
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.)
         */
        public StringType getVersion() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.)
         */
        public ValueSetDefineComponent setVersion(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        public ValueSetDefineComponent setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new StringType();
            this.version.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #caseSensitive} (If code comparison is case sensitive when codes within this system are compared to each other.)
         */
        public BooleanType getCaseSensitive() { 
          return this.caseSensitive;
        }

        /**
         * @param value {@link #caseSensitive} (If code comparison is case sensitive when codes within this system are compared to each other.)
         */
        public ValueSetDefineComponent setCaseSensitive(BooleanType value) { 
          this.caseSensitive = value;
          return this;
        }

        /**
         * @return If code comparison is case sensitive when codes within this system are compared to each other.
         */
        public boolean getCaseSensitiveSimple() { 
          return this.caseSensitive == null ? false : this.caseSensitive.getValue();
        }

        /**
         * @param value If code comparison is case sensitive when codes within this system are compared to each other.
         */
        public ValueSetDefineComponent setCaseSensitiveSimple(boolean value) { 
          if (value == false)
            this.caseSensitive = null;
          else {
            if (this.caseSensitive == null)
              this.caseSensitive = new BooleanType();
            this.caseSensitive.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #concept} (Concepts in the code system.)
         */
        public List<ValueSetDefineConceptComponent> getConcept() { 
          return this.concept;
        }

    // syntactic sugar
        /**
         * @return {@link #concept} (Concepts in the code system.)
         */
        public ValueSetDefineConceptComponent addConcept() { 
          ValueSetDefineConceptComponent t = new ValueSetDefineConceptComponent();
          this.concept.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "URI to identify the code system.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("version", "string", "The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("caseSensitive", "boolean", "If code comparison is case sensitive when codes within this system are compared to each other.", 0, java.lang.Integer.MAX_VALUE, caseSensitive));
          childrenList.add(new Property("concept", "", "Concepts in the code system.", 0, java.lang.Integer.MAX_VALUE, concept));
        }

      public ValueSetDefineComponent copy() {
        ValueSetDefineComponent dst = new ValueSetDefineComponent();
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.caseSensitive = caseSensitive == null ? null : caseSensitive.copy();
        dst.concept = new ArrayList<ValueSetDefineConceptComponent>();
        for (ValueSetDefineConceptComponent i : concept)
          dst.concept.add(i.copy());
        return dst;
      }

  }

    public static class ValueSetDefineConceptComponent extends BackboneElement {
        /**
         * Code that identifies concept.
         */
        protected CodeType code;

        /**
         * If this code is not for use as a real concept.
         */
        protected BooleanType abstract_;

        /**
         * Text to Display to the user.
         */
        protected StringType display;

        /**
         * The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        protected StringType definition;

        /**
         * Child Concepts (is-a / contains).
         */
        protected List<ValueSetDefineConceptComponent> concept = new ArrayList<ValueSetDefineConceptComponent>();

        private static final long serialVersionUID = -1870725379L;

      public ValueSetDefineConceptComponent() {
        super();
      }

      public ValueSetDefineConceptComponent(CodeType code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Code that identifies concept.)
         */
        public CodeType getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies concept.)
         */
        public ValueSetDefineConceptComponent setCode(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code that identifies concept.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code that identifies concept.
         */
        public ValueSetDefineConceptComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new CodeType();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #abstract_} (If this code is not for use as a real concept.)
         */
        public BooleanType getAbstract() { 
          return this.abstract_;
        }

        /**
         * @param value {@link #abstract_} (If this code is not for use as a real concept.)
         */
        public ValueSetDefineConceptComponent setAbstract(BooleanType value) { 
          this.abstract_ = value;
          return this;
        }

        /**
         * @return If this code is not for use as a real concept.
         */
        public boolean getAbstractSimple() { 
          return this.abstract_ == null ? false : this.abstract_.getValue();
        }

        /**
         * @param value If this code is not for use as a real concept.
         */
        public ValueSetDefineConceptComponent setAbstractSimple(boolean value) { 
          if (value == false)
            this.abstract_ = null;
          else {
            if (this.abstract_ == null)
              this.abstract_ = new BooleanType();
            this.abstract_.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #display} (Text to Display to the user.)
         */
        public StringType getDisplay() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Text to Display to the user.)
         */
        public ValueSetDefineConceptComponent setDisplay(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Text to Display to the user.
         */
        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Text to Display to the user.
         */
        public ValueSetDefineConceptComponent setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #definition} (The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.)
         */
        public StringType getDefinition() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.)
         */
        public ValueSetDefineConceptComponent setDefinition(StringType value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        public String getDefinitionSimple() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        /**
         * @param value The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        public ValueSetDefineConceptComponent setDefinitionSimple(String value) { 
          if (value == null)
            this.definition = null;
          else {
            if (this.definition == null)
              this.definition = new StringType();
            this.definition.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #concept} (Child Concepts (is-a / contains).)
         */
        public List<ValueSetDefineConceptComponent> getConcept() { 
          return this.concept;
        }

    // syntactic sugar
        /**
         * @return {@link #concept} (Child Concepts (is-a / contains).)
         */
        public ValueSetDefineConceptComponent addConcept() { 
          ValueSetDefineConceptComponent t = new ValueSetDefineConceptComponent();
          this.concept.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Code that identifies concept.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("abstract", "boolean", "If this code is not for use as a real concept.", 0, java.lang.Integer.MAX_VALUE, abstract_));
          childrenList.add(new Property("display", "string", "Text to Display to the user.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("definition", "string", "The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.", 0, java.lang.Integer.MAX_VALUE, definition));
          childrenList.add(new Property("concept", "@ValueSet.define.concept", "Child Concepts (is-a / contains).", 0, java.lang.Integer.MAX_VALUE, concept));
        }

      public ValueSetDefineConceptComponent copy() {
        ValueSetDefineConceptComponent dst = new ValueSetDefineConceptComponent();
        dst.code = code == null ? null : code.copy();
        dst.abstract_ = abstract_ == null ? null : abstract_.copy();
        dst.display = display == null ? null : display.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.concept = new ArrayList<ValueSetDefineConceptComponent>();
        for (ValueSetDefineConceptComponent i : concept)
          dst.concept.add(i.copy());
        return dst;
      }

  }

    public static class ValueSetComposeComponent extends BackboneElement {
        /**
         * Includes the contents of the referenced value set as a part of the contents of this value set.
         */
        protected List<UriType> import_ = new ArrayList<UriType>();

        /**
         * Include one or more codes from a code system.
         */
        protected List<ConceptSetComponent> include = new ArrayList<ConceptSetComponent>();

        /**
         * Exclude one or more codes from the value set.
         */
        protected List<ConceptSetComponent> exclude = new ArrayList<ConceptSetComponent>();

        private static final long serialVersionUID = 1784534855L;

      public ValueSetComposeComponent() {
        super();
      }

        /**
         * @return {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public List<UriType> getImport() { 
          return this.import_;
        }

    // syntactic sugar
        /**
         * @return {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public UriType addImport() { 
          UriType t = new UriType();
          this.import_.add(t);
          return t;
        }

        /**
         * @param value {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public UriType addImportSimple(String value) { 
          UriType t = new UriType();
          t.setValue(value);
          this.import_.add(t);
          return t;
        }

        /**
         * @param value {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public boolean hasImportSimple(String value) { 
          for (UriType v : this.import_)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #include} (Include one or more codes from a code system.)
         */
        public List<ConceptSetComponent> getInclude() { 
          return this.include;
        }

    // syntactic sugar
        /**
         * @return {@link #include} (Include one or more codes from a code system.)
         */
        public ConceptSetComponent addInclude() { 
          ConceptSetComponent t = new ConceptSetComponent();
          this.include.add(t);
          return t;
        }

        /**
         * @return {@link #exclude} (Exclude one or more codes from the value set.)
         */
        public List<ConceptSetComponent> getExclude() { 
          return this.exclude;
        }

    // syntactic sugar
        /**
         * @return {@link #exclude} (Exclude one or more codes from the value set.)
         */
        public ConceptSetComponent addExclude() { 
          ConceptSetComponent t = new ConceptSetComponent();
          this.exclude.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("import", "uri", "Includes the contents of the referenced value set as a part of the contents of this value set.", 0, java.lang.Integer.MAX_VALUE, import_));
          childrenList.add(new Property("include", "", "Include one or more codes from a code system.", 0, java.lang.Integer.MAX_VALUE, include));
          childrenList.add(new Property("exclude", "@ValueSet.compose.include", "Exclude one or more codes from the value set.", 0, java.lang.Integer.MAX_VALUE, exclude));
        }

      public ValueSetComposeComponent copy() {
        ValueSetComposeComponent dst = new ValueSetComposeComponent();
        dst.import_ = new ArrayList<UriType>();
        for (UriType i : import_)
          dst.import_.add(i.copy());
        dst.include = new ArrayList<ConceptSetComponent>();
        for (ConceptSetComponent i : include)
          dst.include.add(i.copy());
        dst.exclude = new ArrayList<ConceptSetComponent>();
        for (ConceptSetComponent i : exclude)
          dst.exclude.add(i.copy());
        return dst;
      }

  }

    public static class ConceptSetComponent extends BackboneElement {
        /**
         * The code system from which the selected codes come from.
         */
        protected UriType system;

        /**
         * The version of the code system that the codes are selected from.
         */
        protected StringType version;

        /**
         * Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.
         */
        protected List<CodeType> code = new ArrayList<CodeType>();

        /**
         * Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.
         */
        protected List<ConceptSetFilterComponent> filter = new ArrayList<ConceptSetFilterComponent>();

        private static final long serialVersionUID = -1360238143L;

      public ConceptSetComponent() {
        super();
      }

      public ConceptSetComponent(UriType system) {
        super();
        this.system = system;
      }

        /**
         * @return {@link #system} (The code system from which the selected codes come from.)
         */
        public UriType getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (The code system from which the selected codes come from.)
         */
        public ConceptSetComponent setSystem(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return The code system from which the selected codes come from.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value The code system from which the selected codes come from.
         */
        public ConceptSetComponent setSystemSimple(String value) { 
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version of the code system that the codes are selected from.)
         */
        public StringType getVersion() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version of the code system that the codes are selected from.)
         */
        public ConceptSetComponent setVersion(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version of the code system that the codes are selected from.
         */
        public String getVersionSimple() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version of the code system that the codes are selected from.
         */
        public ConceptSetComponent setVersionSimple(String value) { 
          if (value == null)
            this.version = null;
          else {
            if (this.version == null)
              this.version = new StringType();
            this.version.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.)
         */
        public List<CodeType> getCode() { 
          return this.code;
        }

    // syntactic sugar
        /**
         * @return {@link #code} (Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.)
         */
        public CodeType addCode() { 
          CodeType t = new CodeType();
          this.code.add(t);
          return t;
        }

        /**
         * @param value {@link #code} (Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.)
         */
        public CodeType addCodeSimple(String value) { 
          CodeType t = new CodeType();
          t.setValue(value);
          this.code.add(t);
          return t;
        }

        /**
         * @param value {@link #code} (Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.)
         */
        public boolean hasCodeSimple(String value) { 
          for (CodeType v : this.code)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #filter} (Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.)
         */
        public List<ConceptSetFilterComponent> getFilter() { 
          return this.filter;
        }

    // syntactic sugar
        /**
         * @return {@link #filter} (Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.)
         */
        public ConceptSetFilterComponent addFilter() { 
          ConceptSetFilterComponent t = new ConceptSetFilterComponent();
          this.filter.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "The code system from which the selected codes come from.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("version", "string", "The version of the code system that the codes are selected from.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("code", "code", "Specifies a code or concept to be included or excluded. The list of codes is considered ordered, though the order may not have any particular significance.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("filter", "", "Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.", 0, java.lang.Integer.MAX_VALUE, filter));
        }

      public ConceptSetComponent copy() {
        ConceptSetComponent dst = new ConceptSetComponent();
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.code = new ArrayList<CodeType>();
        for (CodeType i : code)
          dst.code.add(i.copy());
        dst.filter = new ArrayList<ConceptSetFilterComponent>();
        for (ConceptSetFilterComponent i : filter)
          dst.filter.add(i.copy());
        return dst;
      }

  }

    public static class ConceptSetFilterComponent extends BackboneElement {
        /**
         * A code that identifies a property defined in the code system.
         */
        protected CodeType property;

        /**
         * The kind of operation to perform as a part of the filter criteria.
         */
        protected Enumeration<FilterOperator> op;

        /**
         * The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        protected CodeType value;

        private static final long serialVersionUID = 1985515000L;

      public ConceptSetFilterComponent() {
        super();
      }

      public ConceptSetFilterComponent(CodeType property, Enumeration<FilterOperator> op, CodeType value) {
        super();
        this.property = property;
        this.op = op;
        this.value = value;
      }

        /**
         * @return {@link #property} (A code that identifies a property defined in the code system.)
         */
        public CodeType getProperty() { 
          return this.property;
        }

        /**
         * @param value {@link #property} (A code that identifies a property defined in the code system.)
         */
        public ConceptSetFilterComponent setProperty(CodeType value) { 
          this.property = value;
          return this;
        }

        /**
         * @return A code that identifies a property defined in the code system.
         */
        public String getPropertySimple() { 
          return this.property == null ? null : this.property.getValue();
        }

        /**
         * @param value A code that identifies a property defined in the code system.
         */
        public ConceptSetFilterComponent setPropertySimple(String value) { 
            if (this.property == null)
              this.property = new CodeType();
            this.property.setValue(value);
          return this;
        }

        /**
         * @return {@link #op} (The kind of operation to perform as a part of the filter criteria.)
         */
        public Enumeration<FilterOperator> getOp() { 
          return this.op;
        }

        /**
         * @param value {@link #op} (The kind of operation to perform as a part of the filter criteria.)
         */
        public ConceptSetFilterComponent setOp(Enumeration<FilterOperator> value) { 
          this.op = value;
          return this;
        }

        /**
         * @return The kind of operation to perform as a part of the filter criteria.
         */
        public FilterOperator getOpSimple() { 
          return this.op == null ? null : this.op.getValue();
        }

        /**
         * @param value The kind of operation to perform as a part of the filter criteria.
         */
        public ConceptSetFilterComponent setOpSimple(FilterOperator value) { 
            if (this.op == null)
              this.op = new Enumeration<FilterOperator>();
            this.op.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.)
         */
        public CodeType getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.)
         */
        public ConceptSetFilterComponent setValue(CodeType value) { 
          this.value = value;
          return this;
        }

        /**
         * @return The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        public ConceptSetFilterComponent setValueSimple(String value) { 
            if (this.value == null)
              this.value = new CodeType();
            this.value.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("property", "code", "A code that identifies a property defined in the code system.", 0, java.lang.Integer.MAX_VALUE, property));
          childrenList.add(new Property("op", "code", "The kind of operation to perform as a part of the filter criteria.", 0, java.lang.Integer.MAX_VALUE, op));
          childrenList.add(new Property("value", "code", "The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public ConceptSetFilterComponent copy() {
        ConceptSetFilterComponent dst = new ConceptSetFilterComponent();
        dst.property = property == null ? null : property.copy();
        dst.op = op == null ? null : op.copy();
        dst.value = value == null ? null : value.copy();
        return dst;
      }

  }

    public static class ValueSetExpansionComponent extends BackboneElement {
        /**
         * An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.
         */
        protected Identifier identifier;

        /**
         * Time valueset expansion happened.
         */
        protected InstantType timestamp;

        /**
         * Codes in the value set.
         */
        protected List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        private static final long serialVersionUID = 1886819543L;

      public ValueSetExpansionComponent() {
        super();
      }

      public ValueSetExpansionComponent(InstantType timestamp) {
        super();
        this.timestamp = timestamp;
      }

        /**
         * @return {@link #identifier} (An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.)
         */
        public ValueSetExpansionComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #timestamp} (Time valueset expansion happened.)
         */
        public InstantType getTimestamp() { 
          return this.timestamp;
        }

        /**
         * @param value {@link #timestamp} (Time valueset expansion happened.)
         */
        public ValueSetExpansionComponent setTimestamp(InstantType value) { 
          this.timestamp = value;
          return this;
        }

        /**
         * @return Time valueset expansion happened.
         */
        public DateAndTime getTimestampSimple() { 
          return this.timestamp == null ? null : this.timestamp.getValue();
        }

        /**
         * @param value Time valueset expansion happened.
         */
        public ValueSetExpansionComponent setTimestampSimple(DateAndTime value) { 
            if (this.timestamp == null)
              this.timestamp = new InstantType();
            this.timestamp.setValue(value);
          return this;
        }

        /**
         * @return {@link #contains} (Codes in the value set.)
         */
        public List<ValueSetExpansionContainsComponent> getContains() { 
          return this.contains;
        }

    // syntactic sugar
        /**
         * @return {@link #contains} (Codes in the value set.)
         */
        public ValueSetExpansionContainsComponent addContains() { 
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("timestamp", "instant", "Time valueset expansion happened.", 0, java.lang.Integer.MAX_VALUE, timestamp));
          childrenList.add(new Property("contains", "", "Codes in the value set.", 0, java.lang.Integer.MAX_VALUE, contains));
        }

      public ValueSetExpansionComponent copy() {
        ValueSetExpansionComponent dst = new ValueSetExpansionComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.timestamp = timestamp == null ? null : timestamp.copy();
        dst.contains = new ArrayList<ValueSetExpansionContainsComponent>();
        for (ValueSetExpansionContainsComponent i : contains)
          dst.contains.add(i.copy());
        return dst;
      }

  }

    public static class ValueSetExpansionContainsComponent extends BackboneElement {
        /**
         * System value for the code.
         */
        protected UriType system;

        /**
         * Code - if blank, this is not a choosable code.
         */
        protected CodeType code;

        /**
         * User display for the concept.
         */
        protected StringType display;

        /**
         * Codes contained in this concept.
         */
        protected List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        private static final long serialVersionUID = 1346310636L;

      public ValueSetExpansionContainsComponent() {
        super();
      }

        /**
         * @return {@link #system} (System value for the code.)
         */
        public UriType getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (System value for the code.)
         */
        public ValueSetExpansionContainsComponent setSystem(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return System value for the code.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value System value for the code.
         */
        public ValueSetExpansionContainsComponent setSystemSimple(String value) { 
          if (value == null)
            this.system = null;
          else {
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Code - if blank, this is not a choosable code.)
         */
        public CodeType getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code - if blank, this is not a choosable code.)
         */
        public ValueSetExpansionContainsComponent setCode(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code - if blank, this is not a choosable code.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code - if blank, this is not a choosable code.
         */
        public ValueSetExpansionContainsComponent setCodeSimple(String value) { 
          if (value == null)
            this.code = null;
          else {
            if (this.code == null)
              this.code = new CodeType();
            this.code.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #display} (User display for the concept.)
         */
        public StringType getDisplay() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (User display for the concept.)
         */
        public ValueSetExpansionContainsComponent setDisplay(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return User display for the concept.
         */
        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value User display for the concept.
         */
        public ValueSetExpansionContainsComponent setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #contains} (Codes contained in this concept.)
         */
        public List<ValueSetExpansionContainsComponent> getContains() { 
          return this.contains;
        }

    // syntactic sugar
        /**
         * @return {@link #contains} (Codes contained in this concept.)
         */
        public ValueSetExpansionContainsComponent addContains() { 
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "System value for the code.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("code", "code", "Code - if blank, this is not a choosable code.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("display", "string", "User display for the concept.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("contains", "@ValueSet.expansion.contains", "Codes contained in this concept.", 0, java.lang.Integer.MAX_VALUE, contains));
        }

      public ValueSetExpansionContainsComponent copy() {
        ValueSetExpansionContainsComponent dst = new ValueSetExpansionContainsComponent();
        dst.system = system == null ? null : system.copy();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.contains = new ArrayList<ValueSetExpansionContainsComponent>();
        for (ValueSetExpansionContainsComponent i : contains)
          dst.contains.add(i.copy());
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected StringType identifier;

    /**
     * The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected StringType version;

    /**
     * A free text natural language name describing the value set.
     */
    protected StringType name;

    /**
     * This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.
     */
    protected StringType purpose;

    /**
     * If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.
     */
    protected BooleanType immutable;

    /**
     * The name of the individual or organization that published the value set.
     */
    protected StringType publisher;

    /**
     * Contacts of the publisher to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.
     */
    protected StringType description;

    /**
     * A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.
     */
    protected StringType copyright;

    /**
     * The status of the value set.
     */
    protected Enumeration<ValuesetStatus> status;

    /**
     * This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected BooleanType experimental;

    /**
     * Whether this is intended to be used with an extensible binding or not.
     */
    protected BooleanType extensible;

    /**
     * The date that the value set status was last changed.
     */
    protected DateTimeType date;

    /**
     * If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.
     */
    protected DateType stableDate;

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

    private static final long serialVersionUID = -1172765595L;

    public ValueSet() {
      super();
    }

    public ValueSet(StringType name, StringType description, Enumeration<ValuesetStatus> status) {
      super();
      this.name = name;
      this.description = description;
      this.status = status;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public StringType getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public ValueSet setIdentifier(StringType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public ValueSet setIdentifierSimple(String value) { 
      if (value == null)
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new StringType();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public StringType getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public ValueSet setVersion(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public ValueSet setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new StringType();
        this.version.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #name} (A free text natural language name describing the value set.)
     */
    public StringType getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name describing the value set.)
     */
    public ValueSet setName(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name describing the value set.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name describing the value set.
     */
    public ValueSet setNameSimple(String value) { 
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #purpose} (This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.)
     */
    public StringType getPurpose() { 
      return this.purpose;
    }

    /**
     * @param value {@link #purpose} (This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.)
     */
    public ValueSet setPurpose(StringType value) { 
      this.purpose = value;
      return this;
    }

    /**
     * @return This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.
     */
    public String getPurposeSimple() { 
      return this.purpose == null ? null : this.purpose.getValue();
    }

    /**
     * @param value This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.
     */
    public ValueSet setPurposeSimple(String value) { 
      if (value == null)
        this.purpose = null;
      else {
        if (this.purpose == null)
          this.purpose = new StringType();
        this.purpose.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #immutable} (If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.)
     */
    public BooleanType getImmutable() { 
      return this.immutable;
    }

    /**
     * @param value {@link #immutable} (If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.)
     */
    public ValueSet setImmutable(BooleanType value) { 
      this.immutable = value;
      return this;
    }

    /**
     * @return If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.
     */
    public boolean getImmutableSimple() { 
      return this.immutable == null ? false : this.immutable.getValue();
    }

    /**
     * @param value If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.
     */
    public ValueSet setImmutableSimple(boolean value) { 
      if (value == false)
        this.immutable = null;
      else {
        if (this.immutable == null)
          this.immutable = new BooleanType();
        this.immutable.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #publisher} (The name of the individual or organization that published the value set.)
     */
    public StringType getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (The name of the individual or organization that published the value set.)
     */
    public ValueSet setPublisher(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return The name of the individual or organization that published the value set.
     */
    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value The name of the individual or organization that published the value set.
     */
    public ValueSet setPublisherSimple(String value) { 
      if (value == null)
        this.publisher = null;
      else {
        if (this.publisher == null)
          this.publisher = new StringType();
        this.publisher.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #telecom} (Contacts of the publisher to assist a user in finding and communicating with the publisher.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (Contacts of the publisher to assist a user in finding and communicating with the publisher.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #description} (A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.)
     */
    public StringType getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.)
     */
    public ValueSet setDescription(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.
     */
    public ValueSet setDescriptionSimple(String value) { 
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      return this;
    }

    /**
     * @return {@link #copyright} (A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.)
     */
    public StringType getCopyright() { 
      return this.copyright;
    }

    /**
     * @param value {@link #copyright} (A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.)
     */
    public ValueSet setCopyright(StringType value) { 
      this.copyright = value;
      return this;
    }

    /**
     * @return A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.
     */
    public String getCopyrightSimple() { 
      return this.copyright == null ? null : this.copyright.getValue();
    }

    /**
     * @param value A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.
     */
    public ValueSet setCopyrightSimple(String value) { 
      if (value == null)
        this.copyright = null;
      else {
        if (this.copyright == null)
          this.copyright = new StringType();
        this.copyright.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of the value set.)
     */
    public Enumeration<ValuesetStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the value set.)
     */
    public ValueSet setStatus(Enumeration<ValuesetStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the value set.
     */
    public ValuesetStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the value set.
     */
    public ValueSet setStatusSimple(ValuesetStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ValuesetStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public BooleanType getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public ValueSet setExperimental(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimentalSimple() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public ValueSet setExperimentalSimple(boolean value) { 
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
     * @return {@link #extensible} (Whether this is intended to be used with an extensible binding or not.)
     */
    public BooleanType getExtensible() { 
      return this.extensible;
    }

    /**
     * @param value {@link #extensible} (Whether this is intended to be used with an extensible binding or not.)
     */
    public ValueSet setExtensible(BooleanType value) { 
      this.extensible = value;
      return this;
    }

    /**
     * @return Whether this is intended to be used with an extensible binding or not.
     */
    public boolean getExtensibleSimple() { 
      return this.extensible == null ? false : this.extensible.getValue();
    }

    /**
     * @param value Whether this is intended to be used with an extensible binding or not.
     */
    public ValueSet setExtensibleSimple(boolean value) { 
      if (value == false)
        this.extensible = null;
      else {
        if (this.extensible == null)
          this.extensible = new BooleanType();
        this.extensible.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #date} (The date that the value set status was last changed.)
     */
    public DateTimeType getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the value set status was last changed.)
     */
    public ValueSet setDate(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the value set status was last changed.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the value set status was last changed.
     */
    public ValueSet setDateSimple(DateAndTime value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTimeType();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #stableDate} (If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.)
     */
    public DateType getStableDate() { 
      return this.stableDate;
    }

    /**
     * @param value {@link #stableDate} (If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.)
     */
    public ValueSet setStableDate(DateType value) { 
      this.stableDate = value;
      return this;
    }

    /**
     * @return If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.
     */
    public DateAndTime getStableDateSimple() { 
      return this.stableDate == null ? null : this.stableDate.getValue();
    }

    /**
     * @param value If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.
     */
    public ValueSet setStableDateSimple(DateAndTime value) { 
      if (value == null)
        this.stableDate = null;
      else {
        if (this.stableDate == null)
          this.stableDate = new DateType();
        this.stableDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #define} (When value set defines its own codes.)
     */
    public ValueSetDefineComponent getDefine() { 
      return this.define;
    }

    /**
     * @param value {@link #define} (When value set defines its own codes.)
     */
    public ValueSet setDefine(ValueSetDefineComponent value) { 
      this.define = value;
      return this;
    }

    /**
     * @return {@link #compose} (When value set includes codes from elsewhere.)
     */
    public ValueSetComposeComponent getCompose() { 
      return this.compose;
    }

    /**
     * @param value {@link #compose} (When value set includes codes from elsewhere.)
     */
    public ValueSet setCompose(ValueSetComposeComponent value) { 
      this.compose = value;
      return this;
    }

    /**
     * @return {@link #expansion} (When value set is an expansion.)
     */
    public ValueSetExpansionComponent getExpansion() { 
      return this.expansion;
    }

    /**
     * @param value {@link #expansion} (When value set is an expansion.)
     */
    public ValueSet setExpansion(ValueSetExpansionComponent value) { 
      this.expansion = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "string", "The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name describing the value set.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("purpose", "string", "This should describe 'the semantic space' to be included in the value set. This can also describe the approach taken to build the value set.", 0, java.lang.Integer.MAX_VALUE, purpose));
        childrenList.add(new Property("immutable", "boolean", "If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.", 0, java.lang.Integer.MAX_VALUE, immutable));
        childrenList.add(new Property("publisher", "string", "The name of the individual or organization that published the value set.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contacts of the publisher to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("copyright", "string", "A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.", 0, java.lang.Integer.MAX_VALUE, copyright));
        childrenList.add(new Property("status", "code", "The status of the value set.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("extensible", "boolean", "Whether this is intended to be used with an extensible binding or not.", 0, java.lang.Integer.MAX_VALUE, extensible));
        childrenList.add(new Property("date", "dateTime", "The date that the value set status was last changed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("stableDate", "date", "If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.", 0, java.lang.Integer.MAX_VALUE, stableDate));
        childrenList.add(new Property("define", "", "When value set defines its own codes.", 0, java.lang.Integer.MAX_VALUE, define));
        childrenList.add(new Property("compose", "", "When value set includes codes from elsewhere.", 0, java.lang.Integer.MAX_VALUE, compose));
        childrenList.add(new Property("expansion", "", "When value set is an expansion.", 0, java.lang.Integer.MAX_VALUE, expansion));
      }

      public ValueSet copy() {
        ValueSet dst = new ValueSet();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.immutable = immutable == null ? null : immutable.copy();
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
        dst.stableDate = stableDate == null ? null : stableDate.copy();
        dst.define = define == null ? null : define.copy();
        dst.compose = compose == null ? null : compose.copy();
        dst.expansion = expansion == null ? null : expansion.copy();
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

