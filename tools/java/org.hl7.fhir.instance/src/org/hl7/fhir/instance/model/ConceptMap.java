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
 * A statement of relationships from one set of concepts to one or more other concepts - either code systems or data elements, or classes in class models.
 */
public class ConceptMap extends DomainResource {

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

    public enum ConceptEquivalence {
        EQUAL, // The definitions of the concepts are exactly the same (i.e. only grammatical differences) and structural implications of meaning are identifical or irrelevant (i.e. intensionally identical).
        EQUIVALENT, // The definitions of the concepts mean the same thing (including when structural implications of meaning are considered) (i.e. extensionally identical).
        WIDER, // The target mapping is wider in meaning than the source concept.
        SUBSUMES, // The target mapping subsumes the meaning of the source concept (e.g. the source is-a target).
        NARROWER, // The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
        SPECIALISES, // The target mapping specialises the meaning of the source concept (e.g. the target is-a source).
        INEXACT, // The target mapping overlaps with the source concept, but both source and target cover additional meaning. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
        UNMATCHED, // There is no match for this concept in the destination concept system.
        DISJOINT, // This is an explicit assertion that there is no mapping between the source and target concept.
        NULL; // added to help the parsers
        public static ConceptEquivalence fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("equal".equals(codeString))
          return EQUAL;
        if ("equivalent".equals(codeString))
          return EQUIVALENT;
        if ("wider".equals(codeString))
          return WIDER;
        if ("subsumes".equals(codeString))
          return SUBSUMES;
        if ("narrower".equals(codeString))
          return NARROWER;
        if ("specialises".equals(codeString))
          return SPECIALISES;
        if ("inexact".equals(codeString))
          return INEXACT;
        if ("unmatched".equals(codeString))
          return UNMATCHED;
        if ("disjoint".equals(codeString))
          return DISJOINT;
        throw new Exception("Unknown ConceptEquivalence code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case EQUAL: return "equal";
            case EQUIVALENT: return "equivalent";
            case WIDER: return "wider";
            case SUBSUMES: return "subsumes";
            case NARROWER: return "narrower";
            case SPECIALISES: return "specialises";
            case INEXACT: return "inexact";
            case UNMATCHED: return "unmatched";
            case DISJOINT: return "disjoint";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case EQUAL: return "The definitions of the concepts are exactly the same (i.e. only grammatical differences) and structural implications of meaning are identifical or irrelevant (i.e. intensionally identical).";
            case EQUIVALENT: return "The definitions of the concepts mean the same thing (including when structural implications of meaning are considered) (i.e. extensionally identical).";
            case WIDER: return "The target mapping is wider in meaning than the source concept.";
            case SUBSUMES: return "The target mapping subsumes the meaning of the source concept (e.g. the source is-a target).";
            case NARROWER: return "The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.";
            case SPECIALISES: return "The target mapping specialises the meaning of the source concept (e.g. the target is-a source).";
            case INEXACT: return "The target mapping overlaps with the source concept, but both source and target cover additional meaning. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.";
            case UNMATCHED: return "There is no match for this concept in the destination concept system.";
            case DISJOINT: return "This is an explicit assertion that there is no mapping between the source and target concept.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case EQUAL: return "equal";
            case EQUIVALENT: return "equivalent";
            case WIDER: return "wider";
            case SUBSUMES: return "subsumes";
            case NARROWER: return "narrower";
            case SPECIALISES: return "specialises";
            case INEXACT: return "inexact";
            case UNMATCHED: return "unmatched";
            case DISJOINT: return "disjoint";
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
          return ConceptEquivalence.EQUAL;
        if ("equivalent".equals(codeString))
          return ConceptEquivalence.EQUIVALENT;
        if ("wider".equals(codeString))
          return ConceptEquivalence.WIDER;
        if ("subsumes".equals(codeString))
          return ConceptEquivalence.SUBSUMES;
        if ("narrower".equals(codeString))
          return ConceptEquivalence.NARROWER;
        if ("specialises".equals(codeString))
          return ConceptEquivalence.SPECIALISES;
        if ("inexact".equals(codeString))
          return ConceptEquivalence.INEXACT;
        if ("unmatched".equals(codeString))
          return ConceptEquivalence.UNMATCHED;
        if ("disjoint".equals(codeString))
          return ConceptEquivalence.DISJOINT;
        throw new Exception("Unknown ConceptEquivalence code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConceptEquivalence.EQUAL)
        return "equal";
      if (code == ConceptEquivalence.EQUIVALENT)
        return "equivalent";
      if (code == ConceptEquivalence.WIDER)
        return "wider";
      if (code == ConceptEquivalence.SUBSUMES)
        return "subsumes";
      if (code == ConceptEquivalence.NARROWER)
        return "narrower";
      if (code == ConceptEquivalence.SPECIALISES)
        return "specialises";
      if (code == ConceptEquivalence.INEXACT)
        return "inexact";
      if (code == ConceptEquivalence.UNMATCHED)
        return "unmatched";
      if (code == ConceptEquivalence.DISJOINT)
        return "disjoint";
      return "?";
      }
    }

    public static class ConceptMapElementComponent extends BackboneElement {
        /**
         * Code System (if the source is a value value set that crosses more than one code system).
         */
        protected UriType codeSystem;

        /**
         * Identity (code or path) or the element/item being mapped.
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

        /**
         * @return {@link #codeSystem} (Code System (if the source is a value value set that crosses more than one code system).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public UriType getCodeSystemElement() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (Code System (if the source is a value value set that crosses more than one code system).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public ConceptMapElementComponent setCodeSystemElement(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return Code System (if the source is a value value set that crosses more than one code system).
         */
        public String getCodeSystem() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value Code System (if the source is a value value set that crosses more than one code system).
         */
        public ConceptMapElementComponent setCodeSystem(String value) { 
          if (Utilities.noString(value))
            this.codeSystem = null;
          else {
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Identity (code or path) or the element/item being mapped.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public CodeType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Identity (code or path) or the element/item being mapped.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ConceptMapElementComponent setCodeElement(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Identity (code or path) or the element/item being mapped.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Identity (code or path) or the element/item being mapped.
         */
        public ConceptMapElementComponent setCode(String value) { 
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
         * @return {@link #dependsOn} (A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.)
         */
        public List<OtherElementComponent> getDependsOn() { 
          return this.dependsOn;
        }

        /**
         * @return {@link #dependsOn} (A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.)
         */
    // syntactic sugar
        public OtherElementComponent addDependsOn() { //3
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

        /**
         * @return {@link #map} (A concept from the target value set that this concept maps to.)
         */
    // syntactic sugar
        public ConceptMapElementMapComponent addMap() { //3
          ConceptMapElementMapComponent t = new ConceptMapElementMapComponent();
          this.map.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeSystem", "uri", "Code System (if the source is a value value set that crosses more than one code system).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "code", "Identity (code or path) or the element/item being mapped.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("dependsOn", "", "A set of additional dependencies for this mapping to hold. This mapping is only applicable if the specified element can be resolved, and it has the specified value.", 0, java.lang.Integer.MAX_VALUE, dependsOn));
          childrenList.add(new Property("map", "", "A concept from the target value set that this concept maps to.", 0, java.lang.Integer.MAX_VALUE, map));
        }

      public ConceptMapElementComponent copy() {
        ConceptMapElementComponent dst = new ConceptMapElementComponent();
        copyValues(dst);
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
         * The code system of the dependency code (if the source/dependency is a value set that cross code systems).
         */
        protected UriType codeSystem;

        /**
         * Identity (code or path) or the element/item that the map depends on / refers to.
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
         * @return {@link #element} (A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.). This is the underlying object with id, value and extensions. The accessor "getElement" gives direct access to the value
         */
        public UriType getElementElement() { 
          return this.element;
        }

        /**
         * @param value {@link #element} (A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.). This is the underlying object with id, value and extensions. The accessor "getElement" gives direct access to the value
         */
        public OtherElementComponent setElementElement(UriType value) { 
          this.element = value;
          return this;
        }

        /**
         * @return A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.
         */
        public String getElement() { 
          return this.element == null ? null : this.element.getValue();
        }

        /**
         * @param value A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.
         */
        public OtherElementComponent setElement(String value) { 
            if (this.element == null)
              this.element = new UriType();
            this.element.setValue(value);
          return this;
        }

        /**
         * @return {@link #codeSystem} (The code system of the dependency code (if the source/dependency is a value set that cross code systems).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public UriType getCodeSystemElement() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (The code system of the dependency code (if the source/dependency is a value set that cross code systems).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public OtherElementComponent setCodeSystemElement(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return The code system of the dependency code (if the source/dependency is a value set that cross code systems).
         */
        public String getCodeSystem() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value The code system of the dependency code (if the source/dependency is a value set that cross code systems).
         */
        public OtherElementComponent setCodeSystem(String value) { 
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Identity (code or path) or the element/item that the map depends on / refers to.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public StringType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Identity (code or path) or the element/item that the map depends on / refers to.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public OtherElementComponent setCodeElement(StringType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Identity (code or path) or the element/item that the map depends on / refers to.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Identity (code or path) or the element/item that the map depends on / refers to.
         */
        public OtherElementComponent setCode(String value) { 
            if (this.code == null)
              this.code = new StringType();
            this.code.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("element", "uri", "A reference to a specific concept that holds a coded value. This can be an element in a FHIR resource, or a specific reference to a data element in a different specification (e.g. v2) or a general reference to a kind of data field, or a reference to a value set with an appropriately narrow definition.", 0, java.lang.Integer.MAX_VALUE, element));
          childrenList.add(new Property("codeSystem", "uri", "The code system of the dependency code (if the source/dependency is a value set that cross code systems).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "string", "Identity (code or path) or the element/item that the map depends on / refers to.", 0, java.lang.Integer.MAX_VALUE, code));
        }

      public OtherElementComponent copy() {
        OtherElementComponent dst = new OtherElementComponent();
        copyValues(dst);
        dst.element = element == null ? null : element.copy();
        dst.codeSystem = codeSystem == null ? null : codeSystem.copy();
        dst.code = code == null ? null : code.copy();
        return dst;
      }

  }

    public static class ConceptMapElementMapComponent extends BackboneElement {
        /**
         * The code system of the target code (if the target is a value set that cross code systems).
         */
        protected UriType codeSystem;

        /**
         * Identity (code or path) or the element/item that the map refers to.
         */
        protected CodeType code;

        /**
         * The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.
         */
        protected Enumeration<ConceptEquivalence> equivalence;

        /**
         * A description of status/issues in mapping that conveys additional information not represented in  the structured data.
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
         * @return {@link #codeSystem} (The code system of the target code (if the target is a value set that cross code systems).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public UriType getCodeSystemElement() { 
          return this.codeSystem;
        }

        /**
         * @param value {@link #codeSystem} (The code system of the target code (if the target is a value set that cross code systems).). This is the underlying object with id, value and extensions. The accessor "getCodeSystem" gives direct access to the value
         */
        public ConceptMapElementMapComponent setCodeSystemElement(UriType value) { 
          this.codeSystem = value;
          return this;
        }

        /**
         * @return The code system of the target code (if the target is a value set that cross code systems).
         */
        public String getCodeSystem() { 
          return this.codeSystem == null ? null : this.codeSystem.getValue();
        }

        /**
         * @param value The code system of the target code (if the target is a value set that cross code systems).
         */
        public ConceptMapElementMapComponent setCodeSystem(String value) { 
          if (Utilities.noString(value))
            this.codeSystem = null;
          else {
            if (this.codeSystem == null)
              this.codeSystem = new UriType();
            this.codeSystem.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Identity (code or path) or the element/item that the map refers to.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public CodeType getCodeElement() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Identity (code or path) or the element/item that the map refers to.). This is the underlying object with id, value and extensions. The accessor "getCode" gives direct access to the value
         */
        public ConceptMapElementMapComponent setCodeElement(CodeType value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Identity (code or path) or the element/item that the map refers to.
         */
        public String getCode() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Identity (code or path) or the element/item that the map refers to.
         */
        public ConceptMapElementMapComponent setCode(String value) { 
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
         * @return {@link #equivalence} (The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.). This is the underlying object with id, value and extensions. The accessor "getEquivalence" gives direct access to the value
         */
        public Enumeration<ConceptEquivalence> getEquivalenceElement() { 
          return this.equivalence;
        }

        /**
         * @param value {@link #equivalence} (The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.). This is the underlying object with id, value and extensions. The accessor "getEquivalence" gives direct access to the value
         */
        public ConceptMapElementMapComponent setEquivalenceElement(Enumeration<ConceptEquivalence> value) { 
          this.equivalence = value;
          return this;
        }

        /**
         * @return The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.
         */
        public ConceptEquivalence getEquivalence() { 
          return this.equivalence == null ? null : this.equivalence.getValue();
        }

        /**
         * @param value The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.
         */
        public ConceptMapElementMapComponent setEquivalence(ConceptEquivalence value) { 
            if (this.equivalence == null)
              this.equivalence = new Enumeration<ConceptEquivalence>();
            this.equivalence.setValue(value);
          return this;
        }

        /**
         * @return {@link #comments} (A description of status/issues in mapping that conveys additional information not represented in  the structured data.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
         */
        public StringType getCommentsElement() { 
          return this.comments;
        }

        /**
         * @param value {@link #comments} (A description of status/issues in mapping that conveys additional information not represented in  the structured data.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
         */
        public ConceptMapElementMapComponent setCommentsElement(StringType value) { 
          this.comments = value;
          return this;
        }

        /**
         * @return A description of status/issues in mapping that conveys additional information not represented in  the structured data.
         */
        public String getComments() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        /**
         * @param value A description of status/issues in mapping that conveys additional information not represented in  the structured data.
         */
        public ConceptMapElementMapComponent setComments(String value) { 
          if (Utilities.noString(value))
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

        /**
         * @return {@link #product} (A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.)
         */
    // syntactic sugar
        public OtherElementComponent addProduct() { //3
          OtherElementComponent t = new OtherElementComponent();
          this.product.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("codeSystem", "uri", "The code system of the target code (if the target is a value set that cross code systems).", 0, java.lang.Integer.MAX_VALUE, codeSystem));
          childrenList.add(new Property("code", "code", "Identity (code or path) or the element/item that the map refers to.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("equivalence", "code", "The equivalence between the source and target concepts (counting for the dependencies and products). The equivalence is read from source to target (e.g. the source is 'wider' than the target.", 0, java.lang.Integer.MAX_VALUE, equivalence));
          childrenList.add(new Property("comments", "string", "A description of status/issues in mapping that conveys additional information not represented in  the structured data.", 0, java.lang.Integer.MAX_VALUE, comments));
          childrenList.add(new Property("product", "@ConceptMap.element.dependsOn", "A set of additional outcomes from this mapping to other elements. To properly execute this mapping, the specified element must be mapped to some data element or source that is in context. The mapping may still be useful without a place for the additional data elements, but the equivalence cannot be relied on.", 0, java.lang.Integer.MAX_VALUE, product));
        }

      public ConceptMapElementMapComponent copy() {
        ConceptMapElementMapComponent dst = new ConceptMapElementMapComponent();
        copyValues(dst);
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
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

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
     * Mappings for an individual concept in the source to one or more concepts in the target.
     */
    protected List<ConceptMapElementComponent> element = new ArrayList<ConceptMapElementComponent>();

    private static final long serialVersionUID = -1624315749L;

    public ConceptMap() {
      super();
    }

    public ConceptMap(Enumeration<ValuesetStatus> status, Type source, Type target) {
      super();
      this.status = status;
      this.source = source;
      this.target = target;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public StringType getIdentifierElement() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).). This is the underlying object with id, value and extensions. The accessor "getIdentifier" gives direct access to the value
     */
    public ConceptMap setIdentifierElement(StringType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifier() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public ConceptMap setIdentifier(String value) { 
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
     * @return {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public ConceptMap setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public ConceptMap setVersion(String value) { 
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
     * @return {@link #name} (A free text natural language name describing the concept map.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name describing the concept map.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public ConceptMap setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name describing the concept map.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name describing the concept map.
     */
    public ConceptMap setName(String value) { 
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
     * @return {@link #publisher} (The name of the individual or organization that published the concept map.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (The name of the individual or organization that published the concept map.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public ConceptMap setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return The name of the individual or organization that published the concept map.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value The name of the individual or organization that published the concept map.
     */
    public ConceptMap setPublisher(String value) { 
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
     * @return {@link #description} (A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ConceptMap setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    public ConceptMap setDescription(String value) { 
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
     * @return {@link #copyright} (A copyright statement relating to the concept map and/or its contents.). This is the underlying object with id, value and extensions. The accessor "getCopyright" gives direct access to the value
     */
    public StringType getCopyrightElement() { 
      return this.copyright;
    }

    /**
     * @param value {@link #copyright} (A copyright statement relating to the concept map and/or its contents.). This is the underlying object with id, value and extensions. The accessor "getCopyright" gives direct access to the value
     */
    public ConceptMap setCopyrightElement(StringType value) { 
      this.copyright = value;
      return this;
    }

    /**
     * @return A copyright statement relating to the concept map and/or its contents.
     */
    public String getCopyright() { 
      return this.copyright == null ? null : this.copyright.getValue();
    }

    /**
     * @param value A copyright statement relating to the concept map and/or its contents.
     */
    public ConceptMap setCopyright(String value) { 
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
     * @return {@link #status} (The status of the concept map.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ValuesetStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the concept map.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public ConceptMap setStatusElement(Enumeration<ValuesetStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the concept map.
     */
    public ValuesetStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the concept map.
     */
    public ConceptMap setStatus(ValuesetStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ValuesetStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public BooleanType getExperimentalElement() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public ConceptMap setExperimentalElement(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimental() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public ConceptMap setExperimental(boolean value) { 
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
     * @return {@link #date} (The date that the concept map status was last changed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the concept map status was last changed.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public ConceptMap setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the concept map status was last changed.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the concept map status was last changed.
     */
    public ConceptMap setDate(DateAndTime value) { 
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
     * @return {@link #element} (Mappings for an individual concept in the source to one or more concepts in the target.)
     */
    public List<ConceptMapElementComponent> getElement() { 
      return this.element;
    }

    /**
     * @return {@link #element} (Mappings for an individual concept in the source to one or more concepts in the target.)
     */
    // syntactic sugar
    public ConceptMapElementComponent addElement() { //3
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
        childrenList.add(new Property("telecom", "ContactPoint", "Contacts of the publisher to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("copyright", "string", "A copyright statement relating to the concept map and/or its contents.", 0, java.lang.Integer.MAX_VALUE, copyright));
        childrenList.add(new Property("status", "code", "The status of the concept map.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that the concept map status was last changed.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("source[x]", "uri|Reference(ValueSet|Profile)", "The source value set that specifies the concepts that are being mapped.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("target[x]", "uri|Reference(ValueSet|Profile)", "The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("element", "", "Mappings for an individual concept in the source to one or more concepts in the target.", 0, java.lang.Integer.MAX_VALUE, element));
      }

      public ConceptMap copy() {
        ConceptMap dst = new ConceptMap();
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
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

