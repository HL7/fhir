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
 * A value set specifies a set of codes drawn from one or more code systems.
 */
public class ValueSet extends DomainResource {

    public enum ValuesetStatus {
        DRAFT, // This valueset is still under development.
        ACTIVE, // This valueset is ready for normal use.
        RETIRED, // This valueset has been withdrawn or superceded and should no longer be used.
        NULL; // added to help the parsers
        public static ValuesetStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("retired".equals(codeString))
          return RETIRED;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
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
            case DRAFT: return "This valueset is still under development.";
            case ACTIVE: return "This valueset is ready for normal use.";
            case RETIRED: return "This valueset has been withdrawn or superceded and should no longer be used.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "Draft";
            case ACTIVE: return "Active";
            case RETIRED: return "Retired";
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
          return ValuesetStatus.DRAFT;
        if ("active".equals(codeString))
          return ValuesetStatus.ACTIVE;
        if ("retired".equals(codeString))
          return ValuesetStatus.RETIRED;
        throw new Exception("Unknown ValuesetStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ValuesetStatus.DRAFT)
        return "draft";
      if (code == ValuesetStatus.ACTIVE)
        return "active";
      if (code == ValuesetStatus.RETIRED)
        return "retired";
      return "?";
      }
    }

    public enum FilterOperator {
        EQUAL, // The specified property of the code equals the provided value.
        ISA, // The specified property of the code has an is-a relationship with the provided value.
        ISNOTA, // The specified property of the code does not have an is-a relationship with the provided value.
        REGEX, // The specified property of the code  matches the regex specified in the provided value.
        IN, // The specified property of the code is in the set of codes or concepts specified in the provided value (comma separated list).
        NOTIN, // The specified property of the code is not in the set of codes or concepts specified in the provided value (comma separated list).
        NULL; // added to help the parsers
        public static FilterOperator fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("=".equals(codeString))
          return EQUAL;
        if ("is-a".equals(codeString))
          return ISA;
        if ("is-not-a".equals(codeString))
          return ISNOTA;
        if ("regex".equals(codeString))
          return REGEX;
        if ("in".equals(codeString))
          return IN;
        if ("not in".equals(codeString))
          return NOTIN;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case EQUAL: return "=";
            case ISA: return "is-a";
            case ISNOTA: return "is-not-a";
            case REGEX: return "regex";
            case IN: return "in";
            case NOTIN: return "not in";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case EQUAL: return "The specified property of the code equals the provided value.";
            case ISA: return "The specified property of the code has an is-a relationship with the provided value.";
            case ISNOTA: return "The specified property of the code does not have an is-a relationship with the provided value.";
            case REGEX: return "The specified property of the code  matches the regex specified in the provided value.";
            case IN: return "The specified property of the code is in the set of codes or concepts specified in the provided value (comma separated list).";
            case NOTIN: return "The specified property of the code is not in the set of codes or concepts specified in the provided value (comma separated list).";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case EQUAL: return "=";
            case ISA: return "is-a";
            case ISNOTA: return "is-not-a";
            case REGEX: return "regex";
            case IN: return "in";
            case NOTIN: return "not in";
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
          return FilterOperator.EQUAL;
        if ("is-a".equals(codeString))
          return FilterOperator.ISA;
        if ("is-not-a".equals(codeString))
          return FilterOperator.ISNOTA;
        if ("regex".equals(codeString))
          return FilterOperator.REGEX;
        if ("in".equals(codeString))
          return FilterOperator.IN;
        if ("not in".equals(codeString))
          return FilterOperator.NOTIN;
        throw new Exception("Unknown FilterOperator code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == FilterOperator.EQUAL)
        return "=";
      if (code == FilterOperator.ISA)
        return "is-a";
      if (code == FilterOperator.ISNOTA)
        return "is-not-a";
      if (code == FilterOperator.REGEX)
        return "regex";
      if (code == FilterOperator.IN)
        return "in";
      if (code == FilterOperator.NOTIN)
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
        protected List<ConceptDefinitionComponent> concept = new ArrayList<ConceptDefinitionComponent>();

        private static final long serialVersionUID = 1963348227L;

      public ValueSetDefineComponent() {
        super();
      }

      public ValueSetDefineComponent(UriType system) {
        super();
        this.system = system;
      }

        /**
         * @return {@link #system} (URI to identify the code system.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public UriType getSystemElement() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (URI to identify the code system.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public ValueSetDefineComponent setSystemElement(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return URI to identify the code system.
         */
        public String getSystem() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value URI to identify the code system.
         */
        public ValueSetDefineComponent setSystem(String value) { 
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public StringType getVersionElement() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public ValueSetDefineComponent setVersionElement(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        public String getVersion() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version of this code system that defines the codes. Note that the version is optional because a well maintained code system does not suffer from versioning, and therefore the version does not need to be maintained. However many code systems are not well maintained, and the version needs to be defined and tracked.
         */
        public ValueSetDefineComponent setVersion(String value) { 
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
         * @return {@link #caseSensitive} (If code comparison is case sensitive when codes within this system are compared to each other.). This is the underlying object with id, value and extensions. The accessor "getCaseSensitive" gives direct access to the value
         */
        public BooleanType getCaseSensitiveElement() { 
          return this.caseSensitive;
        }

        /**
         * @param value {@link #caseSensitive} (If code comparison is case sensitive when codes within this system are compared to each other.). This is the underlying object with id, value and extensions. The accessor "getCaseSensitive" gives direct access to the value
         */
        public ValueSetDefineComponent setCaseSensitiveElement(BooleanType value) { 
          this.caseSensitive = value;
          return this;
        }

        /**
         * @return If code comparison is case sensitive when codes within this system are compared to each other.
         */
        public boolean getCaseSensitive() { 
          return this.caseSensitive == null ? false : this.caseSensitive.getValue();
        }

        /**
         * @param value If code comparison is case sensitive when codes within this system are compared to each other.
         */
        public ValueSetDefineComponent setCaseSensitive(boolean value) { 
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
        public List<ConceptDefinitionComponent> getConcept() { 
          return this.concept;
        }

        /**
         * @return {@link #concept} (Concepts in the code system.)
         */
    // syntactic sugar
        public ConceptDefinitionComponent addConcept() { //3
          ConceptDefinitionComponent t = new ConceptDefinitionComponent();
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
        copyValues(dst);
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.caseSensitive = caseSensitive == null ? null : caseSensitive.copy();
        dst.concept = new ArrayList<ConceptDefinitionComponent>();
        for (ConceptDefinitionComponent i : concept)
          dst.concept.add(i.copy());
        return dst;
      }

  }

    public static class ConceptDefinitionComponent extends BackboneElement {
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
         * Additional representations for the concept - other languages, aliases, specialised purposes, used for particular purposes, etc.
         */
        protected List<ConceptDefinitionDesignationComponent> designation = new ArrayList<ConceptDefinitionDesignationComponent>();

        /**
         * Child Concepts (is-a / contains).
         */
        protected List<ConceptDefinitionComponent> concept = new ArrayList<ConceptDefinitionComponent>();

        private static final long serialVersionUID = 1252361765L;

      public ConceptDefinitionComponent() {
        super();
      }

      public ConceptDefinitionComponent(CodeType code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Code that identifies concept.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public CodeType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies concept.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ConceptDefinitionComponent setCodeElement(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code that identifies concept.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code that identifies concept.
         */
        public ConceptDefinitionComponent setCode(String value) { 
            if (this.code == null)
              this.code = new CodeType();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #abstract_} (If this code is not for use as a real concept.). This is the underlying object with id, value and extensions. The accessor "getAbstract" gives direct access to the value
         */
        public BooleanType getAbstractElement() { 
          return this.abstract_;
        }

        /**
         * @param value {@link #abstract_} (If this code is not for use as a real concept.). This is the underlying object with id, value and extensions. The accessor "getAbstract" gives direct access to the value
         */
        public ConceptDefinitionComponent setAbstractElement(BooleanType value) { 
          this.abstract_ = value;
          return this;
        }

        /**
         * @return If this code is not for use as a real concept.
         */
        public boolean getAbstract() { 
          return this.abstract_ == null ? false : this.abstract_.getValue();
        }

        /**
         * @param value If this code is not for use as a real concept.
         */
        public ConceptDefinitionComponent setAbstract(boolean value) { 
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
         * @return {@link #display} (Text to Display to the user.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public StringType getDisplayElement() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Text to Display to the user.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public ConceptDefinitionComponent setDisplayElement(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Text to Display to the user.
         */
        public String getDisplay() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Text to Display to the user.
         */
        public ConceptDefinitionComponent setDisplay(String value) { 
          if (Utilities.noString(value))
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #definition} (The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
         */
        public StringType getDefinitionElement() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
         */
        public ConceptDefinitionComponent setDefinitionElement(StringType value) { 
          this.definition = value;
          return this;
        }

        /**
         * @return The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        public String getDefinition() { 
          return this.definition == null ? null : this.definition.getValue();
        }

        /**
         * @param value The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.
         */
        public ConceptDefinitionComponent setDefinition(String value) { 
          if (Utilities.noString(value))
            this.definition = null;
          else {
            if (this.definition == null)
              this.definition = new StringType();
            this.definition.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #designation} (Additional representations for the concept - other languages, aliases, specialised purposes, used for particular purposes, etc.)
         */
        public List<ConceptDefinitionDesignationComponent> getDesignation() { 
          return this.designation;
        }

        /**
         * @return {@link #designation} (Additional representations for the concept - other languages, aliases, specialised purposes, used for particular purposes, etc.)
         */
    // syntactic sugar
        public ConceptDefinitionDesignationComponent addDesignation() { //3
          ConceptDefinitionDesignationComponent t = new ConceptDefinitionDesignationComponent();
          this.designation.add(t);
          return t;
        }

        /**
         * @return {@link #concept} (Child Concepts (is-a / contains).)
         */
        public List<ConceptDefinitionComponent> getConcept() { 
          return this.concept;
        }

        /**
         * @return {@link #concept} (Child Concepts (is-a / contains).)
         */
    // syntactic sugar
        public ConceptDefinitionComponent addConcept() { //3
          ConceptDefinitionComponent t = new ConceptDefinitionComponent();
          this.concept.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Code that identifies concept.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("abstract", "boolean", "If this code is not for use as a real concept.", 0, java.lang.Integer.MAX_VALUE, abstract_));
          childrenList.add(new Property("display", "string", "Text to Display to the user.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("definition", "string", "The formal definition of the concept. Formal definitions are not required, because of the prevalence of legacy systems without them, but they are highly recommended, as without them there is no formal meaning associated with the concept.", 0, java.lang.Integer.MAX_VALUE, definition));
          childrenList.add(new Property("designation", "", "Additional representations for the concept - other languages, aliases, specialised purposes, used for particular purposes, etc.", 0, java.lang.Integer.MAX_VALUE, designation));
          childrenList.add(new Property("concept", "@ValueSet.define.concept", "Child Concepts (is-a / contains).", 0, java.lang.Integer.MAX_VALUE, concept));
        }

      public ConceptDefinitionComponent copy() {
        ConceptDefinitionComponent dst = new ConceptDefinitionComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.abstract_ = abstract_ == null ? null : abstract_.copy();
        dst.display = display == null ? null : display.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.designation = new ArrayList<ConceptDefinitionDesignationComponent>();
        for (ConceptDefinitionDesignationComponent i : designation)
          dst.designation.add(i.copy());
        dst.concept = new ArrayList<ConceptDefinitionComponent>();
        for (ConceptDefinitionComponent i : concept)
          dst.concept.add(i.copy());
        return dst;
      }

  }

    public static class ConceptDefinitionDesignationComponent extends BackboneElement {
        /**
         * The language this designation is defined for.
         */
        protected CodeType language;

        /**
         * A code that details how this designation would be used.
         */
        protected Coding use;

        /**
         * The text value for this designation.
         */
        protected StringType value;

        private static final long serialVersionUID = 1515662414L;

      public ConceptDefinitionDesignationComponent() {
        super();
      }

      public ConceptDefinitionDesignationComponent(StringType value) {
        super();
        this.value = value;
      }

        /**
         * @return {@link #language} (The language this designation is defined for.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
         */
        public CodeType getLanguageElement() { 
          return this.language;
        }

        /**
         * @param value {@link #language} (The language this designation is defined for.). This is the underlying object with id, value and extensions. The accessor "getLanguage" gives direct access to the value
         */
        public ConceptDefinitionDesignationComponent setLanguageElement(CodeType value) { 
          this.language = value;
          return this;
        }

        /**
         * @return The language this designation is defined for.
         */
        public String getLanguage() { 
          return this.language == null ? null : this.language.getValue();
        }

        /**
         * @param value The language this designation is defined for.
         */
        public ConceptDefinitionDesignationComponent setLanguage(String value) { 
          if (Utilities.noString(value))
            this.language = null;
          else {
            if (this.language == null)
              this.language = new CodeType();
            this.language.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #use} (A code that details how this designation would be used.)
         */
        public Coding getUse() { 
          return this.use;
        }

        /**
         * @param value {@link #use} (A code that details how this designation would be used.)
         */
        public ConceptDefinitionDesignationComponent setUse(Coding value) { 
          this.use = value;
          return this;
        }

        /**
         * @return {@link #value} (The text value for this designation.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public StringType getValueElement() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The text value for this designation.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public ConceptDefinitionDesignationComponent setValueElement(StringType value) { 
          this.value = value;
          return this;
        }

        /**
         * @return The text value for this designation.
         */
        public String getValue() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value The text value for this designation.
         */
        public ConceptDefinitionDesignationComponent setValue(String value) { 
            if (this.value == null)
              this.value = new StringType();
            this.value.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("language", "code", "The language this designation is defined for.", 0, java.lang.Integer.MAX_VALUE, language));
          childrenList.add(new Property("use", "Coding", "A code that details how this designation would be used.", 0, java.lang.Integer.MAX_VALUE, use));
          childrenList.add(new Property("value", "string", "The text value for this designation.", 0, java.lang.Integer.MAX_VALUE, value));
        }

      public ConceptDefinitionDesignationComponent copy() {
        ConceptDefinitionDesignationComponent dst = new ConceptDefinitionDesignationComponent();
        copyValues(dst);
        dst.language = language == null ? null : language.copy();
        dst.use = use == null ? null : use.copy();
        dst.value = value == null ? null : value.copy();
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

        /**
         * @return {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
    // syntactic sugar
        public UriType addImportElement() {//2 
          UriType t = new UriType();
          this.import_.add(t);
          return t;
        }

        /**
         * @param value {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public ValueSetComposeComponent addImport(String value) { //1
          UriType t = new UriType();
          t.setValue(value);
          this.import_.add(t);
          return this;
        }

        /**
         * @param value {@link #import_} (Includes the contents of the referenced value set as a part of the contents of this value set.)
         */
        public boolean hasImport(String value) { 
          for (UriType v : this.import_)
            if (v.equals(value)) // uri
              return true;
          return false;
        }

        /**
         * @return {@link #include} (Include one or more codes from a code system.)
         */
        public List<ConceptSetComponent> getInclude() { 
          return this.include;
        }

        /**
         * @return {@link #include} (Include one or more codes from a code system.)
         */
    // syntactic sugar
        public ConceptSetComponent addInclude() { //3
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

        /**
         * @return {@link #exclude} (Exclude one or more codes from the value set.)
         */
    // syntactic sugar
        public ConceptSetComponent addExclude() { //3
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
        copyValues(dst);
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
         * Specifies a concept to be included or excluded.
         */
        protected List<ConceptReferenceComponent> concept = new ArrayList<ConceptReferenceComponent>();

        /**
         * Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.
         */
        protected List<ConceptSetFilterComponent> filter = new ArrayList<ConceptSetFilterComponent>();

        private static final long serialVersionUID = -950487150L;

      public ConceptSetComponent() {
        super();
      }

      public ConceptSetComponent(UriType system) {
        super();
        this.system = system;
      }

        /**
         * @return {@link #system} (The code system from which the selected codes come from.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public UriType getSystemElement() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (The code system from which the selected codes come from.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public ConceptSetComponent setSystemElement(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return The code system from which the selected codes come from.
         */
        public String getSystem() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value The code system from which the selected codes come from.
         */
        public ConceptSetComponent setSystem(String value) { 
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          return this;
        }

        /**
         * @return {@link #version} (The version of the code system that the codes are selected from.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public StringType getVersionElement() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version of the code system that the codes are selected from.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public ConceptSetComponent setVersionElement(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version of the code system that the codes are selected from.
         */
        public String getVersion() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version of the code system that the codes are selected from.
         */
        public ConceptSetComponent setVersion(String value) { 
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
         * @return {@link #concept} (Specifies a concept to be included or excluded.)
         */
        public List<ConceptReferenceComponent> getConcept() { 
          return this.concept;
        }

        /**
         * @return {@link #concept} (Specifies a concept to be included or excluded.)
         */
    // syntactic sugar
        public ConceptReferenceComponent addConcept() { //3
          ConceptReferenceComponent t = new ConceptReferenceComponent();
          this.concept.add(t);
          return t;
        }

        /**
         * @return {@link #filter} (Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.)
         */
        public List<ConceptSetFilterComponent> getFilter() { 
          return this.filter;
        }

        /**
         * @return {@link #filter} (Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.)
         */
    // syntactic sugar
        public ConceptSetFilterComponent addFilter() { //3
          ConceptSetFilterComponent t = new ConceptSetFilterComponent();
          this.filter.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "The code system from which the selected codes come from.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("version", "string", "The version of the code system that the codes are selected from.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("concept", "", "Specifies a concept to be included or excluded.", 0, java.lang.Integer.MAX_VALUE, concept));
          childrenList.add(new Property("filter", "", "Select concepts by specify a matching criteria based on the properties (including relationships) defined by the system. If multiple filters are specified, they SHALL all be true.", 0, java.lang.Integer.MAX_VALUE, filter));
        }

      public ConceptSetComponent copy() {
        ConceptSetComponent dst = new ConceptSetComponent();
        copyValues(dst);
        dst.system = system == null ? null : system.copy();
        dst.version = version == null ? null : version.copy();
        dst.concept = new ArrayList<ConceptReferenceComponent>();
        for (ConceptReferenceComponent i : concept)
          dst.concept.add(i.copy());
        dst.filter = new ArrayList<ConceptSetFilterComponent>();
        for (ConceptSetFilterComponent i : filter)
          dst.filter.add(i.copy());
        return dst;
      }

  }

    public static class ConceptReferenceComponent extends BackboneElement {
        /**
         * Specifies a code for the concept to be included or excluded.
         */
        protected CodeType code;

        /**
         * The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.
         */
        protected StringType display;

        /**
         * Additional representations for this concept when used in this value set - other languages, aliases, specialised purposes, used for particular purposes, etc.
         */
        protected List<ConceptDefinitionDesignationComponent> designation = new ArrayList<ConceptDefinitionDesignationComponent>();

        private static final long serialVersionUID = -955988739L;

      public ConceptReferenceComponent() {
        super();
      }

      public ConceptReferenceComponent(CodeType code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Specifies a code for the concept to be included or excluded.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public CodeType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Specifies a code for the concept to be included or excluded.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ConceptReferenceComponent setCodeElement(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Specifies a code for the concept to be included or excluded.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Specifies a code for the concept to be included or excluded.
         */
        public ConceptReferenceComponent setCode(String value) { 
            if (this.code == null)
              this.code = new CodeType();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public StringType getDisplayElement() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public ConceptReferenceComponent setDisplayElement(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.
         */
        public String getDisplay() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.
         */
        public ConceptReferenceComponent setDisplay(String value) { 
          if (Utilities.noString(value))
            this.display = null;
          else {
            if (this.display == null)
              this.display = new StringType();
            this.display.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #designation} (Additional representations for this concept when used in this value set - other languages, aliases, specialised purposes, used for particular purposes, etc.)
         */
        public List<ConceptDefinitionDesignationComponent> getDesignation() { 
          return this.designation;
        }

        /**
         * @return {@link #designation} (Additional representations for this concept when used in this value set - other languages, aliases, specialised purposes, used for particular purposes, etc.)
         */
    // syntactic sugar
        public ConceptDefinitionDesignationComponent addDesignation() { //3
          ConceptDefinitionDesignationComponent t = new ConceptDefinitionDesignationComponent();
          this.designation.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Specifies a code for the concept to be included or excluded.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("display", "string", "The text to display to the user for this concept in the context of this valueset. If no display is provided, then applications using the value set use the display specified for the code by the system.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("designation", "@ValueSet.define.concept.designation", "Additional representations for this concept when used in this value set - other languages, aliases, specialised purposes, used for particular purposes, etc.", 0, java.lang.Integer.MAX_VALUE, designation));
        }

      public ConceptReferenceComponent copy() {
        ConceptReferenceComponent dst = new ConceptReferenceComponent();
        copyValues(dst);
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.designation = new ArrayList<ConceptDefinitionDesignationComponent>();
        for (ConceptDefinitionDesignationComponent i : designation)
          dst.designation.add(i.copy());
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
         * @return {@link #property} (A code that identifies a property defined in the code system.). This is the underlying object with id, value and extensions. The accessor "getProperty" gives direct access to the value
         */
        public CodeType getPropertyElement() { 
          return this.property;
        }

        /**
         * @param value {@link #property} (A code that identifies a property defined in the code system.). This is the underlying object with id, value and extensions. The accessor "getProperty" gives direct access to the value
         */
        public ConceptSetFilterComponent setPropertyElement(CodeType value) { 
          this.property = value;
          return this;
        }

        /**
         * @return A code that identifies a property defined in the code system.
         */
        public String getProperty() { 
          return this.property == null ? null : this.property.getValue();
        }

        /**
         * @param value A code that identifies a property defined in the code system.
         */
        public ConceptSetFilterComponent setProperty(String value) { 
            if (this.property == null)
              this.property = new CodeType();
            this.property.setValue(value);
          return this;
        }

        /**
         * @return {@link #op} (The kind of operation to perform as a part of the filter criteria.). This is the underlying object with id, value and extensions. The accessor "getOp" gives direct access to the value
         */
        public Enumeration<FilterOperator> getOpElement() { 
          return this.op;
        }

        /**
         * @param value {@link #op} (The kind of operation to perform as a part of the filter criteria.). This is the underlying object with id, value and extensions. The accessor "getOp" gives direct access to the value
         */
        public ConceptSetFilterComponent setOpElement(Enumeration<FilterOperator> value) { 
          this.op = value;
          return this;
        }

        /**
         * @return The kind of operation to perform as a part of the filter criteria.
         */
        public FilterOperator getOp() { 
          return this.op == null ? null : this.op.getValue();
        }

        /**
         * @param value The kind of operation to perform as a part of the filter criteria.
         */
        public ConceptSetFilterComponent setOp(FilterOperator value) { 
            if (this.op == null)
              this.op = new Enumeration<FilterOperator>();
            this.op.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public CodeType getValueElement() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.). This is the underlying object with id, value and extensions. The accessor "getValue" gives direct access to the value
         */
        public ConceptSetFilterComponent setValueElement(CodeType value) { 
          this.value = value;
          return this;
        }

        /**
         * @return The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        public String getValue() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value The match value may be either a code defined by the system, or a string value which is used a regex match on the literal string of the property value.
         */
        public ConceptSetFilterComponent setValue(String value) { 
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
        copyValues(dst);
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
         * The time at which the expansion was produced by the expanding system.
         */
        protected DateTimeType timestamp;

        /**
         * The codes that are contained in the value set expansion.
         */
        protected List<ValueSetExpansionContainsComponent> contains = new ArrayList<ValueSetExpansionContainsComponent>();

        private static final long serialVersionUID = 631584613L;

      public ValueSetExpansionComponent() {
        super();
      }

      public ValueSetExpansionComponent(DateTimeType timestamp) {
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
         * @return {@link #timestamp} (The time at which the expansion was produced by the expanding system.). This is the underlying object with id, value and extensions. The accessor "getTimestamp" gives direct access to the value
         */
        public DateTimeType getTimestampElement() { 
          return this.timestamp;
        }

        /**
         * @param value {@link #timestamp} (The time at which the expansion was produced by the expanding system.). This is the underlying object with id, value and extensions. The accessor "getTimestamp" gives direct access to the value
         */
        public ValueSetExpansionComponent setTimestampElement(DateTimeType value) { 
          this.timestamp = value;
          return this;
        }

        /**
         * @return The time at which the expansion was produced by the expanding system.
         */
        public DateAndTime getTimestamp() { 
          return this.timestamp == null ? null : this.timestamp.getValue();
        }

        /**
         * @param value The time at which the expansion was produced by the expanding system.
         */
        public ValueSetExpansionComponent setTimestamp(DateAndTime value) { 
            if (this.timestamp == null)
              this.timestamp = new DateTimeType();
            this.timestamp.setValue(value);
          return this;
        }

        /**
         * @return {@link #contains} (The codes that are contained in the value set expansion.)
         */
        public List<ValueSetExpansionContainsComponent> getContains() { 
          return this.contains;
        }

        /**
         * @return {@link #contains} (The codes that are contained in the value set expansion.)
         */
    // syntactic sugar
        public ValueSetExpansionContainsComponent addContains() { //3
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "An identifier that uniquely identifies this expansion of the valueset. Systems may re-use the same identifier as long as the expansion and the definition remain the same, but are not required to do so.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("timestamp", "dateTime", "The time at which the expansion was produced by the expanding system.", 0, java.lang.Integer.MAX_VALUE, timestamp));
          childrenList.add(new Property("contains", "", "The codes that are contained in the value set expansion.", 0, java.lang.Integer.MAX_VALUE, contains));
        }

      public ValueSetExpansionComponent copy() {
        ValueSetExpansionComponent dst = new ValueSetExpansionComponent();
        copyValues(dst);
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
         * The system in which the code for this item in the expansion is defined.
         */
        protected UriType system;

        /**
         * If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.
         */
        protected BooleanType abstract_;

        /**
         * The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.
         */
        protected StringType version;

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

        private static final long serialVersionUID = -965807666L;

      public ValueSetExpansionContainsComponent() {
        super();
      }

        /**
         * @return {@link #system} (The system in which the code for this item in the expansion is defined.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public UriType getSystemElement() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (The system in which the code for this item in the expansion is defined.). This is the underlying object with id, value and extensions. The accessor "getSystem" gives direct access to the value
         */
        public ValueSetExpansionContainsComponent setSystemElement(UriType value) { 
          this.system = value;
          return this;
        }

        /**
         * @return The system in which the code for this item in the expansion is defined.
         */
        public String getSystem() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value The system in which the code for this item in the expansion is defined.
         */
        public ValueSetExpansionContainsComponent setSystem(String value) { 
          if (Utilities.noString(value))
            this.system = null;
          else {
            if (this.system == null)
              this.system = new UriType();
            this.system.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #abstract_} (If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.). This is the underlying object with id, value and extensions. The accessor "getAbstract" gives direct access to the value
         */
        public BooleanType getAbstractElement() { 
          return this.abstract_;
        }

        /**
         * @param value {@link #abstract_} (If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.). This is the underlying object with id, value and extensions. The accessor "getAbstract" gives direct access to the value
         */
        public ValueSetExpansionContainsComponent setAbstractElement(BooleanType value) { 
          this.abstract_ = value;
          return this;
        }

        /**
         * @return If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.
         */
        public boolean getAbstract() { 
          return this.abstract_ == null ? false : this.abstract_.getValue();
        }

        /**
         * @param value If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.
         */
        public ValueSetExpansionContainsComponent setAbstract(boolean value) { 
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
         * @return {@link #version} (The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public StringType getVersionElement() { 
          return this.version;
        }

        /**
         * @param value {@link #version} (The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
         */
        public ValueSetExpansionContainsComponent setVersionElement(StringType value) { 
          this.version = value;
          return this;
        }

        /**
         * @return The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.
         */
        public String getVersion() { 
          return this.version == null ? null : this.version.getValue();
        }

        /**
         * @param value The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.
         */
        public ValueSetExpansionContainsComponent setVersion(String value) { 
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
         * @return {@link #code} (Code - if blank, this is not a choosable code.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public CodeType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code - if blank, this is not a choosable code.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ValueSetExpansionContainsComponent setCodeElement(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code - if blank, this is not a choosable code.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code - if blank, this is not a choosable code.
         */
        public ValueSetExpansionContainsComponent setCode(String value) { 
          if (Utilities.noString(value))
            this.code = null;
          else {
            if (this.code == null)
              this.code = new CodeType();
            this.code.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #display} (User display for the concept.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public StringType getDisplayElement() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (User display for the concept.). This is the underlying object with id, value and extensions. The accessor "getDisplay" gives direct access to the value
         */
        public ValueSetExpansionContainsComponent setDisplayElement(StringType value) { 
          this.display = value;
          return this;
        }

        /**
         * @return User display for the concept.
         */
        public String getDisplay() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value User display for the concept.
         */
        public ValueSetExpansionContainsComponent setDisplay(String value) { 
          if (Utilities.noString(value))
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

        /**
         * @return {@link #contains} (Codes contained in this concept.)
         */
    // syntactic sugar
        public ValueSetExpansionContainsComponent addContains() { //3
          ValueSetExpansionContainsComponent t = new ValueSetExpansionContainsComponent();
          this.contains.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "The system in which the code for this item in the expansion is defined.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("abstract", "boolean", "If true, this entry is included in the expansion for navigational purposes, and the user cannot select the code directly as a proper value.", 0, java.lang.Integer.MAX_VALUE, abstract_));
          childrenList.add(new Property("version", "string", "The version of this code system that defined this code and/or display. This should only be used with code systems that do not enforce concept permanence.", 0, java.lang.Integer.MAX_VALUE, version));
          childrenList.add(new Property("code", "code", "Code - if blank, this is not a choosable code.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("display", "string", "User display for the concept.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("contains", "@ValueSet.expansion.contains", "Codes contained in this concept.", 0, java.lang.Integer.MAX_VALUE, contains));
        }

      public ValueSetExpansionContainsComponent copy() {
        ValueSetExpansionContainsComponent dst = new ValueSetExpansionContainsComponent();
        copyValues(dst);
        dst.system = system == null ? null : system.copy();
        dst.abstract_ = abstract_ == null ? null : abstract_.copy();
        dst.version = version == null ? null : version.copy();
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
    protected UriType identifier;

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
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.
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
     * A value set can also be "expanded", where the value set is turned into a simple collection of enumerated codes. This element holds the expansion, if it has been performed.
     */
    protected ValueSetExpansionComponent expansion;

    private static final long serialVersionUID = -1400499134L;

    public ValueSet() {
      super();
    }

    public ValueSet(Enumeration<ValuesetStatus> status) {
      super();
      this.status = status;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public UriType getIdentifierElement() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public ValueSet setIdentifierElement(UriType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifier() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public ValueSet setIdentifier(String value) { 
      if (Utilities.noString(value))
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new UriType();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public ValueSet setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public ValueSet setVersion(String value) { 
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
     * @return {@link #name} (A free text natural language name describing the value set.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name describing the value set.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public ValueSet setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name describing the value set.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name describing the value set.
     */
    public ValueSet setName(String value) { 
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
     * @return {@link #purpose} (This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.). This is the underlying object with id, value and extensions. The accessor "getPurpose" gives direct access to the value
     */
    public StringType getPurposeElement() { 
      return this.purpose;
    }

    /**
     * @param value {@link #purpose} (This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.). This is the underlying object with id, value and extensions. The accessor "getPurpose" gives direct access to the value
     */
    public ValueSet setPurposeElement(StringType value) { 
      this.purpose = value;
      return this;
    }

    /**
     * @return This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.
     */
    public String getPurpose() { 
      return this.purpose == null ? null : this.purpose.getValue();
    }

    /**
     * @param value This should describe "the semantic space" to be included in the value set. This can also describe the approach taken to build the value set.
     */
    public ValueSet setPurpose(String value) { 
      if (Utilities.noString(value))
        this.purpose = null;
      else {
        if (this.purpose == null)
          this.purpose = new StringType();
        this.purpose.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #immutable} (If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.). This is the underlying object with id, value and extensions. The accessor "getImmutable" gives direct access to the value
     */
    public BooleanType getImmutableElement() { 
      return this.immutable;
    }

    /**
     * @param value {@link #immutable} (If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.). This is the underlying object with id, value and extensions. The accessor "getImmutable" gives direct access to the value
     */
    public ValueSet setImmutableElement(BooleanType value) { 
      this.immutable = value;
      return this;
    }

    /**
     * @return If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.
     */
    public boolean getImmutable() { 
      return this.immutable == null ? false : this.immutable.getValue();
    }

    /**
     * @param value If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.
     */
    public ValueSet setImmutable(boolean value) { 
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
     * @return {@link #publisher} (The name of the individual or organization that published the value set.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (The name of the individual or organization that published the value set.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public ValueSet setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return The name of the individual or organization that published the value set.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value The name of the individual or organization that published the value set.
     */
    public ValueSet setPublisher(String value) { 
      if (Utilities.noString(value))
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
    public List<ContactPoint> getTelecom() { 
      return this.telecom;
    }

    /**
     * @return {@link #telecom} (Contacts of the publisher to assist a user in finding and communicating with the publisher.)
     */
    // syntactic sugar
    public ContactPoint addTelecom() { //3
      ContactPoint t = new ContactPoint();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #description} (A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ValueSet setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.
     */
    public ValueSet setDescription(String value) { 
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
     * @return {@link #copyright} (A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.). This is the underlying object with id, value and extensions. The accessor "getCopyright" gives direct access to the value
     */
    public StringType getCopyrightElement() { 
      return this.copyright;
    }

    /**
     * @param value {@link #copyright} (A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.). This is the underlying object with id, value and extensions. The accessor "getCopyright" gives direct access to the value
     */
    public ValueSet setCopyrightElement(StringType value) { 
      this.copyright = value;
      return this;
    }

    /**
     * @return A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.
     */
    public String getCopyright() { 
      return this.copyright == null ? null : this.copyright.getValue();
    }

    /**
     * @param value A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.
     */
    public ValueSet setCopyright(String value) { 
      if (Utilities.noString(value))
        this.copyright = null;
      else {
        if (this.copyright == null)
          this.copyright = new StringType();
        this.copyright.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (The status of the value set.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ValuesetStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the value set.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public ValueSet setStatusElement(Enumeration<ValuesetStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the value set.
     */
    public ValuesetStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the value set.
     */
    public ValueSet setStatus(ValuesetStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ValuesetStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public BooleanType getExperimentalElement() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public ValueSet setExperimentalElement(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimental() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public ValueSet setExperimental(boolean value) { 
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
     * @return {@link #extensible} (Whether this is intended to be used with an extensible binding or not.). This is the underlying object with id, value and extensions. The accessor "getExtensible" gives direct access to the value
     */
    public BooleanType getExtensibleElement() { 
      return this.extensible;
    }

    /**
     * @param value {@link #extensible} (Whether this is intended to be used with an extensible binding or not.). This is the underlying object with id, value and extensions. The accessor "getExtensible" gives direct access to the value
     */
    public ValueSet setExtensibleElement(BooleanType value) { 
      this.extensible = value;
      return this;
    }

    /**
     * @return Whether this is intended to be used with an extensible binding or not.
     */
    public boolean getExtensible() { 
      return this.extensible == null ? false : this.extensible.getValue();
    }

    /**
     * @param value Whether this is intended to be used with an extensible binding or not.
     */
    public ValueSet setExtensible(boolean value) { 
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
     * @return {@link #date} (The date that the value set status was last changed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the value set status was last changed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public ValueSet setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the value set status was last changed.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the value set status was last changed.
     */
    public ValueSet setDate(DateAndTime value) { 
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
     * @return {@link #stableDate} (If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.). This is the underlying object with id, value and extensions. The accessor "getStableDate" gives direct access to the value
     */
    public DateType getStableDateElement() { 
      return this.stableDate;
    }

    /**
     * @param value {@link #stableDate} (If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.). This is the underlying object with id, value and extensions. The accessor "getStableDate" gives direct access to the value
     */
    public ValueSet setStableDateElement(DateType value) { 
      this.stableDate = value;
      return this;
    }

    /**
     * @return If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.
     */
    public DateAndTime getStableDate() { 
      return this.stableDate == null ? null : this.stableDate.getValue();
    }

    /**
     * @param value If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.
     */
    public ValueSet setStableDate(DateAndTime value) { 
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
     * @return {@link #expansion} (A value set can also be "expanded", where the value set is turned into a simple collection of enumerated codes. This element holds the expansion, if it has been performed.)
     */
    public ValueSetExpansionComponent getExpansion() { 
      return this.expansion;
    }

    /**
     * @param value {@link #expansion} (A value set can also be "expanded", where the value set is turned into a simple collection of enumerated codes. This element holds the expansion, if it has been performed.)
     */
    public ValueSet setExpansion(ValueSetExpansionComponent value) { 
      this.expansion = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "uri", "The identifier that is used to identify this value set when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the value set when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name describing the value set.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("purpose", "string", "This should describe 'the semantic space' to be included in the value set. This can also describe the approach taken to build the value set.", 0, java.lang.Integer.MAX_VALUE, purpose));
        childrenList.add(new Property("immutable", "boolean", "If this is set to 'true', then no new versions of the content logical definition can be created.  Note: Other metadata might still change.", 0, java.lang.Integer.MAX_VALUE, immutable));
        childrenList.add(new Property("publisher", "string", "The name of the individual or organization that published the value set.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "ContactPoint", "Contacts of the publisher to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the use of the value set - reason for definition, conditions of use, etc. The description may include a list of expected usages for the value set.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("copyright", "string", "A copyright statement relating to the value set and/or its contents. These are generally legal restrictions on the use and publishing of the value set.", 0, java.lang.Integer.MAX_VALUE, copyright));
        childrenList.add(new Property("status", "code", "The status of the value set.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This valueset was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("extensible", "boolean", "Whether this is intended to be used with an extensible binding or not.", 0, java.lang.Integer.MAX_VALUE, extensible));
        childrenList.add(new Property("date", "dateTime", "The date that the value set status was last changed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("stableDate", "date", "If a Stability Date is expanded by evaluating the Content Logical Definition using the current version of all referenced code system(s) and value sets as of the Stability Date.", 0, java.lang.Integer.MAX_VALUE, stableDate));
        childrenList.add(new Property("define", "", "When value set defines its own codes.", 0, java.lang.Integer.MAX_VALUE, define));
        childrenList.add(new Property("compose", "", "When value set includes codes from elsewhere.", 0, java.lang.Integer.MAX_VALUE, compose));
        childrenList.add(new Property("expansion", "", "A value set can also be 'expanded', where the value set is turned into a simple collection of enumerated codes. This element holds the expansion, if it has been performed.", 0, java.lang.Integer.MAX_VALUE, expansion));
      }

      public ValueSet copy() {
        ValueSet dst = new ValueSet();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.immutable = immutable == null ? null : immutable.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
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

