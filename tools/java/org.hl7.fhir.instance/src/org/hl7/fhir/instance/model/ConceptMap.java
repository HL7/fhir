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

// Generated on Wed, Oct 2, 2013 10:45+1000 for FHIR v0.11

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

    public enum ConceptEquivalence {
        equal, // The definitions of the concepts are exactly the same (i.e. only grammatical differences) and structural implications of meaning are identifical or irrelevant (i.e. intensionally identical).
        equivalent, // The definitions of the concepts mean the same thing (including when structural implications of meaning are considered) (i.e. extensionally identical).
        wider, // The target mapping is wider in meaning than the source concept.
        narrower, // The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower must be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
        inexact, // The target mapping overlaps with the source concept, but both source and target cover additional meaning. The sense in which the mapping is narrower must be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
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

  public class ConceptEquivalenceEnumFactory implements EnumFactory {
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

    public class ConceptMapConceptComponent extends Element {
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
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
        }

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

        public List<ConceptMapConceptMapComponent> getMap() { 
          return this.map;
        }

    // syntactic sugar
        public ConceptMapConceptMapComponent addMap() { 
          ConceptMapConceptMapComponent t = new ConceptMapConceptMapComponent();
          this.map.add(t);
          return t;
        }

        public List<ConceptMapConceptComponent> getConcept() { 
          return this.concept;
        }

    // syntactic sugar
        public ConceptMapConceptComponent addConcept() { 
          ConceptMapConceptComponent t = new ConceptMapConceptComponent();
          this.concept.add(t);
          return t;
        }

      public ConceptMapConceptComponent copy(ConceptMap e) {
        ConceptMapConceptComponent dst = e.new ConceptMapConceptComponent();
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

    public class ConceptMapConceptMapComponent extends Element {
        /**
         * If this code is not for use as a real concept.
         */
        protected Uri system;

        /**
         * Text to Display to the user.
         */
        protected String_ code;

        /**
         * equal | equivalent | wider | narrower | unmatched | disjoint.
         */
        protected Enumeration<ConceptEquivalence> equivalence;

        /**
         * Description of status/issues in mapping.
         */
        protected String_ comments;

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

        public String_ getCode() { 
          return this.code;
        }

        public void setCode(String_ value) { 
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
              this.code = new String_();
            this.code.setValue(value);
          }
        }

        public Enumeration<ConceptEquivalence> getEquivalence() { 
          return this.equivalence;
        }

        public void setEquivalence(Enumeration<ConceptEquivalence> value) { 
          this.equivalence = value;
        }

        public ConceptEquivalence getEquivalenceSimple() { 
          return this.equivalence == null ? null : this.equivalence.getValue();
        }

        public void setEquivalenceSimple(ConceptEquivalence value) { 
            if (this.equivalence == null)
              this.equivalence = new Enumeration<ConceptEquivalence>();
            this.equivalence.setValue(value);
        }

        public String_ getComments() { 
          return this.comments;
        }

        public void setComments(String_ value) { 
          this.comments = value;
        }

        public String getCommentsSimple() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        public void setCommentsSimple(String value) { 
          if (value == null)
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new String_();
            this.comments.setValue(value);
          }
        }

      public ConceptMapConceptMapComponent copy(ConceptMap e) {
        ConceptMapConceptMapComponent dst = e.new ConceptMapConceptMapComponent();
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

    public ResourceReference getSource() { 
      return this.source;
    }

    public void setSource(ResourceReference value) { 
      this.source = value;
    }

    public ResourceReference getTarget() { 
      return this.target;
    }

    public void setTarget(ResourceReference value) { 
      this.target = value;
    }

    public List<ConceptMapConceptComponent> getConcept() { 
      return this.concept;
    }

    // syntactic sugar
    public ConceptMapConceptComponent addConcept() { 
      ConceptMapConceptComponent t = new ConceptMapConceptComponent();
      this.concept.add(t);
      return t;
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

