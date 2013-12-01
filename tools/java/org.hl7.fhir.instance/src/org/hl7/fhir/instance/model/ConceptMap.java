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

// Generated on Sun, Dec 1, 2013 22:52+1100 for FHIR v0.12

import java.util.*;

/**
 * A statement of relationships from one set of concepts to one or more other concept systems.
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
        narrower, // The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
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
        if ("narrower".equals(codeString))
          return narrower;
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
            case narrower: return "narrower";
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
        if ("narrower".equals(codeString))
          return ConceptEquivalence.narrower;
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
      if (code == ConceptEquivalence.narrower)
        return "narrower";
      if (code == ConceptEquivalence.inexact)
        return "inexact";
      if (code == ConceptEquivalence.unmatched)
        return "unmatched";
      if (code == ConceptEquivalence.disjoint)
        return "disjoint";
      return "?";
      }
    }

    public static class ConceptMapConceptComponent extends BackboneElement {
        /**
         * Name for this concept (if just a group).
         */
        protected String_ name;

        /**
         * System that defines the concept being mapped.
         */
        protected Uri system;

        /**
         * Identifies concept being mapped.
         */
        protected Code code;

        /**
         * Targets mapped to this concept.
         */
        protected List<ConceptMapConceptMapComponent> map = new ArrayList<ConceptMapConceptMapComponent>();

        /**
         * Mappings for sub concepts.
         */
        protected List<ConceptMapConceptComponent> concept = new ArrayList<ConceptMapConceptComponent>();

      public ConceptMapConceptComponent() {
        super();
      }

        /**
         * @return {@link #name} (Name for this concept (if just a group).)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Name for this concept (if just a group).)
         */
        public ConceptMapConceptComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Name for this concept (if just a group).
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Name for this concept (if just a group).
         */
        public ConceptMapConceptComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #system} (System that defines the concept being mapped.)
         */
        public Uri getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (System that defines the concept being mapped.)
         */
        public ConceptMapConceptComponent setSystem(Uri value) { 
          this.system = value;
          return this;
        }

        /**
         * @return System that defines the concept being mapped.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value System that defines the concept being mapped.
         */
        public ConceptMapConceptComponent setSystemSimple(String value) { 
          if (value == null)
            this.system = null;
          else {
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #code} (Identifies concept being mapped.)
         */
        public Code getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Identifies concept being mapped.)
         */
        public ConceptMapConceptComponent setCode(Code value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Identifies concept being mapped.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Identifies concept being mapped.
         */
        public ConceptMapConceptComponent setCodeSimple(String value) { 
          if (value == null)
            this.code = null;
          else {
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #map} (Targets mapped to this concept.)
         */
        public List<ConceptMapConceptMapComponent> getMap() { 
          return this.map;
        }

    // syntactic sugar
        /**
         * @return {@link #map} (Targets mapped to this concept.)
         */
        public ConceptMapConceptMapComponent addMap() { 
          ConceptMapConceptMapComponent t = new ConceptMapConceptMapComponent();
          this.map.add(t);
          return t;
        }

        /**
         * @return {@link #concept} (Mappings for sub concepts.)
         */
        public List<ConceptMapConceptComponent> getConcept() { 
          return this.concept;
        }

    // syntactic sugar
        /**
         * @return {@link #concept} (Mappings for sub concepts.)
         */
        public ConceptMapConceptComponent addConcept() { 
          ConceptMapConceptComponent t = new ConceptMapConceptComponent();
          this.concept.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "Name for this concept (if just a group).", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("system", "uri", "System that defines the concept being mapped.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("code", "code", "Identifies concept being mapped.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("map", "", "Targets mapped to this concept.", 0, java.lang.Integer.MAX_VALUE, map));
          childrenList.add(new Property("concept", "@ConceptMap.concept", "Mappings for sub concepts.", 0, java.lang.Integer.MAX_VALUE, concept));
        }

      public ConceptMapConceptComponent copy(ConceptMap e) {
        ConceptMapConceptComponent dst = new ConceptMapConceptComponent();
        dst.name = name == null ? null : name.copy();
        dst.system = system == null ? null : system.copy();
        dst.code = code == null ? null : code.copy();
        dst.map = new ArrayList<ConceptMapConceptMapComponent>();
        for (ConceptMapConceptMapComponent i : map)
          dst.map.add(i.copy(e));
        dst.concept = new ArrayList<ConceptMapConceptComponent>();
        for (ConceptMapConceptComponent i : concept)
          dst.concept.add(i.copy(e));
        return dst;
      }

  }

    public static class ConceptMapConceptMapComponent extends BackboneElement {
        /**
         * If this code is not for use as a real concept.
         */
        protected Uri system;

        /**
         * Text to Display to the user.
         */
        protected String_ code;

        /**
         * equal | equivalent | wider | narrower | inexact | unmatched | disjoint.
         */
        protected Enumeration<ConceptEquivalence> equivalence;

        /**
         * Description of status/issues in mapping.
         */
        protected String_ comments;

      public ConceptMapConceptMapComponent() {
        super();
      }

      public ConceptMapConceptMapComponent(Uri system, Enumeration<ConceptEquivalence> equivalence) {
        super();
        this.system = system;
        this.equivalence = equivalence;
      }

        /**
         * @return {@link #system} (If this code is not for use as a real concept.)
         */
        public Uri getSystem() { 
          return this.system;
        }

        /**
         * @param value {@link #system} (If this code is not for use as a real concept.)
         */
        public ConceptMapConceptMapComponent setSystem(Uri value) { 
          this.system = value;
          return this;
        }

        /**
         * @return If this code is not for use as a real concept.
         */
        public String getSystemSimple() { 
          return this.system == null ? null : this.system.getValue();
        }

        /**
         * @param value If this code is not for use as a real concept.
         */
        public ConceptMapConceptMapComponent setSystemSimple(String value) { 
            if (this.system == null)
              this.system = new Uri();
            this.system.setValue(value);
          return this;
        }

        /**
         * @return {@link #code} (Text to Display to the user.)
         */
        public String_ getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Text to Display to the user.)
         */
        public ConceptMapConceptMapComponent setCode(String_ value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Text to Display to the user.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Text to Display to the user.
         */
        public ConceptMapConceptMapComponent setCodeSimple(String value) { 
          if (value == null)
            this.code = null;
          else {
            if (this.code == null)
              this.code = new String_();
            this.code.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #equivalence} (equal | equivalent | wider | narrower | inexact | unmatched | disjoint.)
         */
        public Enumeration<ConceptEquivalence> getEquivalence() { 
          return this.equivalence;
        }

        /**
         * @param value {@link #equivalence} (equal | equivalent | wider | narrower | inexact | unmatched | disjoint.)
         */
        public ConceptMapConceptMapComponent setEquivalence(Enumeration<ConceptEquivalence> value) { 
          this.equivalence = value;
          return this;
        }

        /**
         * @return equal | equivalent | wider | narrower | inexact | unmatched | disjoint.
         */
        public ConceptEquivalence getEquivalenceSimple() { 
          return this.equivalence == null ? null : this.equivalence.getValue();
        }

        /**
         * @param value equal | equivalent | wider | narrower | inexact | unmatched | disjoint.
         */
        public ConceptMapConceptMapComponent setEquivalenceSimple(ConceptEquivalence value) { 
            if (this.equivalence == null)
              this.equivalence = new Enumeration<ConceptEquivalence>();
            this.equivalence.setValue(value);
          return this;
        }

        /**
         * @return {@link #comments} (Description of status/issues in mapping.)
         */
        public String_ getComments() { 
          return this.comments;
        }

        /**
         * @param value {@link #comments} (Description of status/issues in mapping.)
         */
        public ConceptMapConceptMapComponent setComments(String_ value) { 
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
        public ConceptMapConceptMapComponent setCommentsSimple(String value) { 
          if (value == null)
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new String_();
            this.comments.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("system", "uri", "If this code is not for use as a real concept.", 0, java.lang.Integer.MAX_VALUE, system));
          childrenList.add(new Property("code", "string", "Text to Display to the user.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("equivalence", "code", "equal | equivalent | wider | narrower | inexact | unmatched | disjoint.", 0, java.lang.Integer.MAX_VALUE, equivalence));
          childrenList.add(new Property("comments", "string", "Description of status/issues in mapping.", 0, java.lang.Integer.MAX_VALUE, comments));
        }

      public ConceptMapConceptMapComponent copy(ConceptMap e) {
        ConceptMapConceptMapComponent dst = new ConceptMapConceptMapComponent();
        dst.system = system == null ? null : system.copy();
        dst.code = code == null ? null : code.copy();
        dst.equivalence = equivalence == null ? null : equivalence.copy();
        dst.comments = comments == null ? null : comments.copy();
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected String_ identifier;

    /**
     * The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected String_ version;

    /**
     * A free text natural language name describing the concept map.
     */
    protected String_ name;

    /**
     * The name of the individual or organization that published the concept map.
     */
    protected String_ publisher;

    /**
     * Contacts of the publisher to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.
     */
    protected String_ description;

    /**
     * A copyright statement relating to the concept map and/or its contents.
     */
    protected String_ copyright;

    /**
     * The status of the concept map.
     */
    protected Enumeration<ValuesetStatus> status;

    /**
     * This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected Boolean experimental;

    /**
     * The date that the concept map status was last changed.
     */
    protected DateTime date;

    /**
     * The source value set that specifies the concepts that are being mapped.
     */
    protected ResourceReference source;

    /**
     * The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.
     */
    protected ResourceReference target;

    /**
     * Mappings for a concept.
     */
    protected List<ConceptMapConceptComponent> concept = new ArrayList<ConceptMapConceptComponent>();

    public ConceptMap() {
      super();
    }

    public ConceptMap(String_ name, String_ description, Enumeration<ValuesetStatus> status, ResourceReference source, ResourceReference target) {
      super();
      this.name = name;
      this.description = description;
      this.status = status;
      this.source = source;
      this.target = target;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public String_ getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this concept map when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public ConceptMap setIdentifier(String_ value) { 
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
          this.identifier = new String_();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public String_ getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the concept map when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public ConceptMap setVersion(String_ value) { 
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
          this.version = new String_();
        this.version.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #name} (A free text natural language name describing the concept map.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name describing the concept map.)
     */
    public ConceptMap setName(String_ value) { 
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
          this.name = new String_();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #publisher} (The name of the individual or organization that published the concept map.)
     */
    public String_ getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (The name of the individual or organization that published the concept map.)
     */
    public ConceptMap setPublisher(String_ value) { 
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
          this.publisher = new String_();
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
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the use of the concept map - reason for definition, conditions of use, etc.)
     */
    public ConceptMap setDescription(String_ value) { 
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
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      return this;
    }

    /**
     * @return {@link #copyright} (A copyright statement relating to the concept map and/or its contents.)
     */
    public String_ getCopyright() { 
      return this.copyright;
    }

    /**
     * @param value {@link #copyright} (A copyright statement relating to the concept map and/or its contents.)
     */
    public ConceptMap setCopyright(String_ value) { 
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
          this.copyright = new String_();
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
    public Boolean getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public ConceptMap setExperimental(Boolean value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimentalSimple() { 
      return this.experimental == null ? null : this.experimental.getValue();
    }

    /**
     * @param value This ConceptMap was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public ConceptMap setExperimentalSimple(boolean value) { 
      if (value == false)
        this.experimental = null;
      else {
        if (this.experimental == null)
          this.experimental = new Boolean();
        this.experimental.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #date} (The date that the concept map status was last changed.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that the concept map status was last changed.)
     */
    public ConceptMap setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that the concept map status was last changed.
     */
    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that the concept map status was last changed.
     */
    public ConceptMap setDateSimple(String value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #source} (The source value set that specifies the concepts that are being mapped.)
     */
    public ResourceReference getSource() { 
      return this.source;
    }

    /**
     * @param value {@link #source} (The source value set that specifies the concepts that are being mapped.)
     */
    public ConceptMap setSource(ResourceReference value) { 
      this.source = value;
      return this;
    }

    /**
     * @return {@link #target} (The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.)
     */
    public ResourceReference getTarget() { 
      return this.target;
    }

    /**
     * @param value {@link #target} (The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.)
     */
    public ConceptMap setTarget(ResourceReference value) { 
      this.target = value;
      return this;
    }

    /**
     * @return {@link #concept} (Mappings for a concept.)
     */
    public List<ConceptMapConceptComponent> getConcept() { 
      return this.concept;
    }

    // syntactic sugar
    /**
     * @return {@link #concept} (Mappings for a concept.)
     */
    public ConceptMapConceptComponent addConcept() { 
      ConceptMapConceptComponent t = new ConceptMapConceptComponent();
      this.concept.add(t);
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
        childrenList.add(new Property("source", "Resource(ValueSet)", "The source value set that specifies the concepts that are being mapped.", 0, java.lang.Integer.MAX_VALUE, source));
        childrenList.add(new Property("target", "Resource(ValueSet)", "The target value set provides context to the mappings. Note that the mapping is made between concepts, not between value sets, but the value set provides important context about how the concept mapping choices are made.", 0, java.lang.Integer.MAX_VALUE, target));
        childrenList.add(new Property("concept", "", "Mappings for a concept.", 0, java.lang.Integer.MAX_VALUE, concept));
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
        dst.concept = new ArrayList<ConceptMapConceptComponent>();
        for (ConceptMapConceptComponent i : concept)
          dst.concept.add(i.copy(dst));
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

