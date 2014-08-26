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
 * A statement of relationships from one set of concepts to one or more other concepts - either code systems or data elements, or classes in class models.
 */
public class ConceptMap extends Resource {

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

    public enum ConceptEquivalence {
        equal, // The definitions of the concepts are exactly the same (i.e. only grammatical differences) and structural implications of meaning are identifical or irrelevant (i.e. intensionally identical).
        equivalent, // The definitions of the concepts mean the same thing (including when structural implications of meaning are considered) (i.e. extensionally identical).
        wider, // The target mapping is wider in meaning than the source concept.
        subsumes, // The target mapping subsumes the meaning of the source concept (e.g. the source is-a target).
        narrower, // The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
        specialises, // The target mapping specialises the meaning of the source concept (e.g. the target is-a source).
        inexact, // The target mapping overlaps with the source concept, but both source and target cover additional meaning. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
        unmatched, // There is no match for this concept in the destination concept system.
        disjoint, // This is an explicit assertion that there is no mapping between the source and target concept.
        Null; // added to help the parsers
        public static ConceptEquivalence fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("equal".equals(codeString))
          return equal;
        if ("equivalent".equals(codeString))
          return equivalent;
        if ("wider".equals(codeString))
          return wider;
        if ("subsumes".equals(codeString))
          return subsumes;
        if ("narrower".equals(codeString))
          return narrower;
        if ("specialises".equals(codeString))
          return specialises;
        if ("inexact".equals(codeString))
          return inexact;
        if ("unmatched".equals(codeString))
          return unmatched;
        if ("disjoint".equals(codeString))
          return disjoint;
        throw new Exception("Unknown ConceptEquivalence code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case equal: return "equal";
            case equivalent: return "equivalent";
            case wider: return "wider";
            case subsumes: return "subsumes";
            case narrower: return "narrower";
            case specialises: return "specialises";
            case inexact: return "inexact";
            case unmatched: return "unmatched";
            case disjoint: return "disjoint";
            default: return "?";
          }
        }
    }

  public static class ConceptEquivalenceEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("equal".equals(codeString))
          return ConceptEquivalence.equal;
        if ("equivalent".equals(codeString))
          return ConceptEquivalence.equivalent;
        if ("wider".equals(codeString))
          return ConceptEquivalence.wider;
        if ("subsumes".equals(codeString))
          return ConceptEquivalence.subsumes;
        if ("narrower".equals(codeString))
          return ConceptEquivalence.narrower;
        if ("specialises".equals(codeString))
          return ConceptEquivalence.specialises;
        if ("inexact".equals(codeString))
          return ConceptEquivalence.inexact;
        if ("unmatched".equals(codeString))
          return ConceptEquivalence.unmatched;
        if ("disjoint".equals(codeString))
          return ConceptEquivalence.disjoint;
        throw new Exception("Unknown ConceptEquivalence code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConceptEquivalence.equal)
        return "equal";
      if (code == ConceptEquivalence.equivalent)
        return "equivalent";
      if (code == ConceptEquivalence.wider)
        return "wider";
      if (code == ConceptEquivalence.subsumes)
        return "subsumes";
      if (code == ConceptEquivalence.narrower)
        return "narrower";
      if (code == ConceptEquivalence.specialises)
        return "specialises";
      if (code == ConceptEquivalence.inexact)
        return "inexact";
      if (code == ConceptEquivalence.unmatched)
        return "unmatched";
      if (code == ConceptEquivalence.disjoint)
        return "disjoint";
      return "?";
      }
    }

    public static class ConceptMapElementComponent extends BackboneElement {
        /**
         * Code System (if value set crosses code systems).
         */
        protected UriType codeSystem;

        /**
         * Identifies element being mapped.
         */
        protected CodeType code;

        /**
         * A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.
         */
        protected List<OtherElementComponent> dependsOn = new ArrayList<OtherElementComponent>();

        /**
         * A concept from the target value set that this concept maps to.
         */
        protected List<ConceptMapElementMapComponent> map = new ArrayList<ConceptMapElementMapComponent>();

        private static final long serialVersionUID = -1778473686L;

      public ConceptMapElementComponent() {
        super();
      }

      public ConceptMapElementComponent(UriType codeSystem) {
        super();
        this.codeSystem = codeSystem;
      }

        /**
         * @return {@link #codeSystem} (Code System (if value set crosses code systems).)
         */
        public UriType getCodeSystem() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (Code System (if value set crosses code systems).)
         */
        public ConceptMapElementComponent setCodeSystem(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return Code System (if value set crosses code systems).
         */
        public String getCodeSystemSimple() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value Code System (if value set crosses code systems).
         */
        public ConceptMapElementComponent setCodeSystemSimple(String value) { 
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Identifies element being mapped.)
         */
        public CodeType getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Identifies element being mapped.)
         */
        public ConceptMapElementComponent setCode(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Identifies element being mapped.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Identifies element being mapped.
         */
        public ConceptMapElementComponent setCodeSimple(String value) { 
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
         * @return {@link #dependsOn} (A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.)
         */
        public List<OtherElementComponent> getDependsOn() { 
          return this.dependsOn;
        }

    // syntactic sugar
        /**
         * @return {@link #dependsOn} (A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.)
         */
        public OtherElementComponent addDependsOn() { 
          OtherElementComponent t = new OtherElementComponent();
          this.dependsOn.add(t);
          return t;
        }

        /**
         * @return {@link #map} (A concept from the target value set that this concept maps to.)
         */
        public List<ConceptMapElementMapComponent> getMap() { 
          return this.map;
        }

    // syntactic sugar
        /**
         * @return {@link #map} (A concept from the target value set that this concept maps to.)
         */
        public ConceptMapElementMapComponent addMap() { 
          ConceptMapElementMapComponent t = new ConceptMapElementMapComponent();
          this.map.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeSystem", "uri", "Code System (if value set crosses code systems).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "code", "Identifies element being mapped.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("dependsOn", "", "A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.", 0, java.lang.Integer.MAX_VALUE, dependsOn));
          childrenList.add(new Property("map", "", "A concept from the target value set that this concept maps to.", 0, java.lang.Integer.MAX_VALUE, map));
        }

      public ConceptMapElementComponent copy() {
        ConceptMapElementComponent dst = new ConceptMapElementComponent();
        dst.codeSystem = codeSystem == null ? null : codeSystem.copy();
        dst.code = code == null ? null : code.copy();
        dst.dependsOn = new ArrayList<OtherElementComponent>();
        for (OtherElementComponent i : dependsOn)
          dst.dependsOn.add(i.copy());
        dst.map = new ArrayList<ConceptMapElementMapComponent>();
        for (ConceptMapElementMapComponent i : map)
          dst.map.add(i.copy());
        return dst;
      }

  }

    public static class OtherElementComponent extends BackboneElement {
        /**
         * A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.
         */
        protected UriType element;

        /**
         * Code System (if necessary).
         */
        protected UriType codeSystem;

        /**
         * Value of the referenced element.
         */
        protected StringType code;

        private static final long serialVersionUID = 1488522448L;

      public OtherElementComponent() {
        super();
      }

      public OtherElementComponent(UriType element, UriType codeSystem, StringType code) {
        super();
        this.element = element;
        this.codeSystem = codeSystem;
        this.code = code;
      }

        /**
         * @return {@link #element} (A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.)
         */
        public UriType getElement() { 
          return this.element;
        }

        /**
         * @param value {@link #element} (A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.)
         */
        public OtherElementComponent setElement(UriType value) { 
          this.element = value;
          return this;
        }

        /**
         * @return A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.
         */
        public String getElementSimple() { 
          return this.element == null ? null : this.element.getValue();
        }

        /**
         * @param value A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.
         */
        public OtherElementComponent setElementSimple(String value) { 
            if (this.element == null)
              this.element = new UriType();
            this.element.setValue(value);
          return this;
        }

        /**
         * @return {@link #codeSystem} (Code System (if necessary).)
         */
        public UriType getCodeSystem() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (Code System (if necessary).)
         */
        public OtherElementComponent setCodeSystem(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return Code System (if necessary).
         */
        public String getCodeSystemSimple() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value Code System (if necessary).
         */
        public OtherElementComponent setCodeSystemSimple(String value) { 
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Value of the referenced element.)
         */
        public StringType getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Value of the referenced element.)
         */
        public OtherElementComponent setCode(StringType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Value of the referenced element.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Value of the referenced element.
         */
        public OtherElementComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new StringType();
            this.code.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("element", "uri", "A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.", 0, java.lang.Integer.MAX_VALUE, element));
          childrenList.add(new Property("codeSystem", "uri", "Code System (if necessary).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "string", "Value of the referenced element.", 0, java.lang.Integer.MAX_VALUE, code));
        }

      public OtherElementComponent copy() {
        OtherElementComponent dst = new OtherElementComponent();
        dst.element = element == null ? null : element.copy();
        dst.codeSystem = codeSystem == null ? null : codeSystem.copy();
        dst.code = code == null ? null : code.copy();
        return dst;
      }

  }

    public static class ConceptMapElementMapComponent extends BackboneElement {
        /**
         * System of the target (if necessary).
         */
        protected UriType codeSystem;

        /**
         * Code that identifies the target element.
         */
        protected CodeType code;

        /**
         * equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.
         */
        protected Enumeration<ConceptEquivalence> equivalence;

        /**
         * Description of status/issues in mapping.
         */
        protected StringType comments;

        /**
         * A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.
         */
        protected List<OtherElementComponent> product = new ArrayList<OtherElementComponent>();

        private static final long serialVersionUID = -822381252L;

      public ConceptMapElementMapComponent() {
        super();
      }

      public ConceptMapElementMapComponent(Enumeration<ConceptEquivalence> equivalence) {
        super();
        this.equivalence = equivalence;
      }

        /**
         * @return {@link #codeSystem} (System of the target (if necessary).)
         */
        public UriType getCodeSystem() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (System of the target (if necessary).)
         */
        public ConceptMapElementMapComponent setCodeSystem(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return System of the target (if necessary).
         */
        public String getCodeSystemSimple() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value System of the target (if necessary).
         */
        public ConceptMapElementMapComponent setCodeSystemSimple(String value) { 
          if (value == null)
            this.codeSystem = null;
          else {
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Code that identifies the target element.)
         */
        public CodeType getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Code that identifies the target element.)
         */
        public ConceptMapElementMapComponent setCode(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Code that identifies the target element.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Code that identifies the target element.
         */
        public ConceptMapElementMapComponent setCodeSimple(String value) { 
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
         * @return {@link #equivalence} (equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.)
         */
        public Enumeration<ConceptEquivalence> getEquivalence() { 
          return this.equivalence;
        }

        /**
         * @param value {@link #equivalence} (equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.)
         */
        public ConceptMapElementMapComponent setEquivalence(Enumeration<ConceptEquivalence> value) { 
          this.equivalence = value;
          return this;
        }

        /**
         * @return equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.
         */
        public ConceptEquivalence getEquivalenceSimple() { 
          return this.equivalence == null ? null : this.equivalence.getValue();
        }

        /**
         * @param value equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.
         */
        public ConceptMapElementMapComponent setEquivalenceSimple(ConceptEquivalence value) { 
            if (this.equivalence == null)
              this.equivalence = new Enumeration<ConceptEquivalence>();
            this.equivalence.setValue(value);
          return this;
        }

        /**
         * @return {@link #comments} (Description of status/issues in mapping.)
         */
        public StringType getComments() { 
          return this.comments;
        }

        /**
         * @param value {@link #comments} (Description of status/issues in mapping.)
         */
        public ConceptMapElementMapComponent setComments(StringType value) { 
          this.comments = value;
          return this;
        }

        /**
         * @return Description of status/issues in mapping.
         */
        public String getCommentsSimple() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        /**
         * @param value Description of status/issues in mapping.
         */
        public ConceptMapElementMapComponent setCommentsSimple(String value) { 
          if (value == null)
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new StringType();
            this.comments.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #product} (A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.)
         */
        public List<OtherElementComponent> getProduct() { 
          return this.product;
        }

    // syntactic sugar
        /**
         * @return {@link #product} (A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.)
         */
        public OtherElementComponent addProduct() { 
          OtherElementComponent t = new OtherElementComponent();
          this.product.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeSystem", "uri", "System of the target (if necessary).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "code", "Code that identifies the target element.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("equivalence", "code", "equal | equivalent | wider | subsumes | narrower | specialises | inexact | unmatched | disjoint.", 0, java.lang.Integer.MAX_VALUE, equivalence));
          childrenList.add(new Property("comments", "string", "Description of status/issues in mapping.", 0, java.lang.Integer.MAX_VALUE, comments));
          childrenList.add(new Property("product", "@ConceptMap.element.dependsOn", "A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.", 0, java.lang.Integer.MAX_VALUE, product));
        }

      public ConceptMapElementMapComponent copy() {
        ConceptMapElementMapComponent dst = new ConceptMapElementMapComponent();
        dst.codeSystem = codeSystem == null ? null : codeSystem.copy();
        dst.code = code == null ? null : code.copy();
        dst.equivalence = equivalence == null ? null : equivalence.copy();
        dst.comments = comments == null ? null : comments.copy();
        dst.product = new ArrayList<OtherElementComponent>();
        for (OtherElementComponent i : product)
          dst.product.add(i.copy());
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected StringType identifier;

    /**
     * The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected StringType version;

    /**
     * A free text natural language name describing the concept map.
     */
    protected StringType name;

    /**
     * The name of the individual or organization that published the concept map.
     */
    protected StringType publisher;

    /**
     * Contacts of the publisher to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    protected StringType description;

    /**
     * A copyright statement relating to the concept map and/or its contents.
     */
    protected StringType copyright;

    /**
     * The status of the concept map.
     */
    protected Enumeration<ValuesetStatus> status;

    /**
     * This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected BooleanType experimental;

    /**
     * The date that the concept map status was last changed.
     */
    protected DateTimeType date;

    /**
     * The source value set that specifies the concepts that are being mapped.
     */
    protected Type source;

    /**
     * The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.
     */
    protected Type target;

    /**
     * Mappings for a concept from the source set.
     */
    protected List<ConceptMapElementComponent> element = new ArrayList<ConceptMapElementComponent>();

    private static final long serialVersionUID = -1887054207L;

    public ConceptMap() {
      super();
    }

    public ConceptMap(StringType name, Enumeration<ValuesetStatus> status, Type source, Type target) {
      super();
      this.name = name;
      this.status = status;
      this.source = source;
      this.target = target;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public StringType getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public ConceptMap setIdentifier(StringType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public ConceptMap setIdentifierSimple(String value) { 
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
     * @return {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public StringType getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public ConceptMap setVersion(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public ConceptMap setVersionSimple(String value) { 
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
     * @return {@link #name} (A free text natural language name describing the concept map.)
     */
    public StringType getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name describing the concept map.)
     */
    public ConceptMap setName(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name describing the concept map.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name describing the concept map.
     */
    public ConceptMap setNameSimple(String value) { 
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #publisher} (The name of the individual or organization that published the concept map.)
     */
    public StringType getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (The name of the individual or organization that published the concept map.)
     */
    public ConceptMap setPublisher(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return The name of the individual or organization that published the concept map.
     */
    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value The name of the individual or organization that published the concept map.
     */
    public ConceptMap setPublisherSimple(String value) { 
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
     * @return {@link #description} (A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.)
     */
    public StringType getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.)
     */
    public ConceptMap setDescription(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    public ConceptMap setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #copyright} (A copyright statement relating to the concept map and/or its contents.)
     */
    public StringType getCopyright() { 
      return this.copyright;
    }

    /**
     * @param value {@link #copyright} (A copyright statement relating to the concept map and/or its contents.)
     */
    public ConceptMap setCopyright(StringType value) { 
      this.copyright = value;
      return this;
    }

    /**
     * @return A copyright statement relating to the concept map and/or its contents.
     */
    public String getCopyrightSimple() { 
      return this.copyright == null ? null : this.copyright.getValue();
    }

    /**
     * @param value A copyright statement relating to the concept map and/or its contents.
     */
    public ConceptMap setCopyrightSimple(String value) { 
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
     * @return {@link #status} (The status of the concept map.)
     */
    public Enumeration<ValuesetStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the concept map.)
     */
    public ConceptMap setStatus(Enumeration<ValuesetStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the concept map.
     */
    public ValuesetStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the concept map.
     */
    public ConceptMap setStatusSimple(ValuesetStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ValuesetStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public BooleanType getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public ConceptMap setExperimental(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimentalSimple() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public ConceptMap setExperimentalSimple(boolean value) { 
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
     * @return {@link #date} (The date that the concept map status was last changed.)
     */
    public DateTimeType getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the concept map status was last changed.)
     */
    public ConceptMap setDate(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the concept map status was last changed.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the concept map status was last changed.
     */
    public ConceptMap setDateSimple(DateAndTime value) { 
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
     * @return {@link #source} (The source value set that specifies the concepts that are being mapped.)
     */
    public Type getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (The source value set that specifies the concepts that are being mapped.)
     */
    public ConceptMap setSource(Type value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #target} (The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.)
     */
    public Type getTarget() { 
      return this.target;
    }

    /**
     * @param value {@link #target} (The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.)
     */
    public ConceptMap setTarget(Type value) { 
      this.target = value;
      return this;
    }

    /**
     * @return {@link #element} (Mappings for a concept from the source set.)
     */
    public List<ConceptMapElementComponent> getElement() { 
      return this.element;
    }

    // syntactic sugar
    /**
     * @return {@link #element} (Mappings for a concept from the source set.)
     */
    public ConceptMapElementComponent addElement() { 
      ConceptMapElementComponent t = new ConceptMapElementComponent();
      this.element.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "string", "The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name describing the concept map.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("publisher", "string", "The name of the individual or organization that published the concept map.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contacts of the publisher to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("copyright", "string", "A copyright statement relating to the concept map and/or its contents.", 0, java.lang.Integer.MAX_VALUE, copyright));
        childrenList.add(new Property("status", "code", "The status of the concept map.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that the concept map status was last changed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("source[x]", "uri|Resource(ValueSet|Profile)", "The source value set that specifies the concepts that are being mapped.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("target[x]", "uri|Resource(ValueSet|Profile)", "The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("element", "", "Mappings for a concept from the source set.", 0, java.lang.Integer.MAX_VALUE, element));
      }

      public ConceptMap copy() {
        ConceptMap dst = new ConceptMap();
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
        dst.date = date == null ? null : date.copy();
        dst.source = source == null ? null : source.copy();
        dst.target = target == null ? null : target.copy();
        dst.element = new ArrayList<ConceptMapElementComponent>();
        for (ConceptMapElementComponent i : element)
          dst.element.add(i.copy());
        return dst;
      }

      protected ConceptMap typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ConceptMap;
   }


}

