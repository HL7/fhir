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

// Generated on Mon, Jun 30, 2014 21:30+1000 for FHIR v0.2.1

import java.util.*;

/**
 * The formal description of a single piece of information that can be gathered and reported.
 */
public class DataElement extends Resource {

    public enum ResourceObservationDefStatus {
        draft, // This data element is still under development.
        active, // This data element is ready for normal use.
        retired, // This data element has been deprecated, withdrawn or superseded and should no longer be used.
        Null; // added to help the parsers
        public static ResourceObservationDefStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("active".equals(codeString))
          return active;
        if ("retired".equals(codeString))
          return retired;
        throw new Exception("Unknown ResourceObservationDefStatus code '"+codeString+"'");
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

  public static class ResourceObservationDefStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ResourceObservationDefStatus.draft;
        if ("active".equals(codeString))
          return ResourceObservationDefStatus.active;
        if ("retired".equals(codeString))
          return ResourceObservationDefStatus.retired;
        throw new Exception("Unknown ResourceObservationDefStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceObservationDefStatus.draft)
        return "draft";
      if (code == ResourceObservationDefStatus.active)
        return "active";
      if (code == ResourceObservationDefStatus.retired)
        return "retired";
      return "?";
      }
    }

    public enum BindingConformance {
        required, // Only codes in the specified set are allowed.  If the binding is extensible, other codes may be used for concepts not covered by the bound set of codes.
        preferred, // For greater interoperability, implementers are strongly encouraged to use the bound set of codes, however alternate codes may be used in derived profiles and implementations if necessary without being considered non-conformant.
        example, // The codes in the set are an example to illustrate the meaning of the field. There is no particular preference for its use nor any assertion that the provided values are sufficient to meet implementation needs.
        Null; // added to help the parsers
        public static BindingConformance fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return required;
        if ("preferred".equals(codeString))
          return preferred;
        if ("example".equals(codeString))
          return example;
        throw new Exception("Unknown BindingConformance code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case required: return "required";
            case preferred: return "preferred";
            case example: return "example";
            default: return "?";
          }
        }
    }

  public static class BindingConformanceEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return BindingConformance.required;
        if ("preferred".equals(codeString))
          return BindingConformance.preferred;
        if ("example".equals(codeString))
          return BindingConformance.example;
        throw new Exception("Unknown BindingConformance code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == BindingConformance.required)
        return "required";
      if (code == BindingConformance.preferred)
        return "preferred";
      if (code == BindingConformance.example)
        return "example";
      return "?";
      }
    }

    public static class DataElementBindingComponent extends BackboneElement {
        /**
         * If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        protected Boolean isExtensible;

        /**
         * Indicates the degree of conformance expectations associated with this binding.
         */
        protected Enumeration<BindingConformance> conformance;

        /**
         * Describes the intended use of this particular set of codes.
         */
        protected String_ description;

        /**
         * Points to the value set that identifies the set of codes to be used.
         */
        protected ResourceReference valueSet;

        /**
         * The actual object that is the target of the reference (Points to the value set that identifies the set of codes to be used.)
         */
        protected ValueSet valueSetTarget;

        private static final long serialVersionUID = 1873055942L;

      public DataElementBindingComponent() {
        super();
      }

      public DataElementBindingComponent(Boolean isExtensible) {
        super();
        this.isExtensible = isExtensible;
      }

        /**
         * @return {@link #isExtensible} (If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.)
         */
        public Boolean getIsExtensible() { 
          return this.isExtensible;
        }

        /**
         * @param value {@link #isExtensible} (If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.)
         */
        public DataElementBindingComponent setIsExtensible(Boolean value) { 
          this.isExtensible = value;
          return this;
        }

        /**
         * @return If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        public boolean getIsExtensibleSimple() { 
          return this.isExtensible == null ? false : this.isExtensible.getValue();
        }

        /**
         * @param value If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        public DataElementBindingComponent setIsExtensibleSimple(boolean value) { 
            if (this.isExtensible == null)
              this.isExtensible = new Boolean();
            this.isExtensible.setValue(value);
          return this;
        }

        /**
         * @return {@link #conformance} (Indicates the degree of conformance expectations associated with this binding.)
         */
        public Enumeration<BindingConformance> getConformance() { 
          return this.conformance;
        }

        /**
         * @param value {@link #conformance} (Indicates the degree of conformance expectations associated with this binding.)
         */
        public DataElementBindingComponent setConformance(Enumeration<BindingConformance> value) { 
          this.conformance = value;
          return this;
        }

        /**
         * @return Indicates the degree of conformance expectations associated with this binding.
         */
        public BindingConformance getConformanceSimple() { 
          return this.conformance == null ? null : this.conformance.getValue();
        }

        /**
         * @param value Indicates the degree of conformance expectations associated with this binding.
         */
        public DataElementBindingComponent setConformanceSimple(BindingConformance value) { 
          if (value == null)
            this.conformance = null;
          else {
            if (this.conformance == null)
              this.conformance = new Enumeration<BindingConformance>();
            this.conformance.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #description} (Describes the intended use of this particular set of codes.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Describes the intended use of this particular set of codes.)
         */
        public DataElementBindingComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Describes the intended use of this particular set of codes.
         */
        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Describes the intended use of this particular set of codes.
         */
        public DataElementBindingComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #valueSet} (Points to the value set that identifies the set of codes to be used.)
         */
        public ResourceReference getValueSet() { 
          return this.valueSet;
        }

        /**
         * @param value {@link #valueSet} (Points to the value set that identifies the set of codes to be used.)
         */
        public DataElementBindingComponent setValueSet(ResourceReference value) { 
          this.valueSet = value;
          return this;
        }

        /**
         * @return {@link #valueSet} (The actual object that is the target of the reference. Points to the value set that identifies the set of codes to be used.)
         */
        public ValueSet getValueSetTarget() { 
          return this.valueSetTarget;
        }

        /**
         * @param value {@link #valueSet} (The actual object that is the target of the reference. Points to the value set that identifies the set of codes to be used.)
         */
        public DataElementBindingComponent setValueSetTarget(ValueSet value) { 
          this.valueSetTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("isExtensible", "boolean", "If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.", 0, java.lang.Integer.MAX_VALUE, isExtensible));
          childrenList.add(new Property("conformance", "code", "Indicates the degree of conformance expectations associated with this binding.", 0, java.lang.Integer.MAX_VALUE, conformance));
          childrenList.add(new Property("description", "string", "Describes the intended use of this particular set of codes.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("valueSet", "Resource(ValueSet)", "Points to the value set that identifies the set of codes to be used.", 0, java.lang.Integer.MAX_VALUE, valueSet));
        }

      public DataElementBindingComponent copy() {
        DataElementBindingComponent dst = new DataElementBindingComponent();
        dst.isExtensible = isExtensible == null ? null : isExtensible.copy();
        dst.conformance = conformance == null ? null : conformance.copy();
        dst.description = description == null ? null : description.copy();
        dst.valueSet = valueSet == null ? null : valueSet.copy();
        return dst;
      }

  }

    public static class DataElementMappingComponent extends BackboneElement {
        /**
         * A URI that identifies the specification that this mapping is expressed to.
         */
        protected Uri uri;

        /**
         * A name for the specification that is being mapped to.
         */
        protected String_ name;

        /**
         * Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        protected String_ comments;

        /**
         * Expresses what part of the target specification corresponds to this element.
         */
        protected String_ map;

        private static final long serialVersionUID = 884283977L;

      public DataElementMappingComponent() {
        super();
      }

      public DataElementMappingComponent(String_ map) {
        super();
        this.map = map;
      }

        /**
         * @return {@link #uri} (A URI that identifies the specification that this mapping is expressed to.)
         */
        public Uri getUri() { 
          return this.uri;
        }

        /**
         * @param value {@link #uri} (A URI that identifies the specification that this mapping is expressed to.)
         */
        public DataElementMappingComponent setUri(Uri value) { 
          this.uri = value;
          return this;
        }

        /**
         * @return A URI that identifies the specification that this mapping is expressed to.
         */
        public String getUriSimple() { 
          return this.uri == null ? null : this.uri.getValue();
        }

        /**
         * @param value A URI that identifies the specification that this mapping is expressed to.
         */
        public DataElementMappingComponent setUriSimple(String value) { 
          if (value == null)
            this.uri = null;
          else {
            if (this.uri == null)
              this.uri = new Uri();
            this.uri.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #name} (A name for the specification that is being mapped to.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A name for the specification that is being mapped to.)
         */
        public DataElementMappingComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return A name for the specification that is being mapped to.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value A name for the specification that is being mapped to.
         */
        public DataElementMappingComponent setNameSimple(String value) { 
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
         * @return {@link #comments} (Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.)
         */
        public String_ getComments() { 
          return this.comments;
        }

        /**
         * @param value {@link #comments} (Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.)
         */
        public DataElementMappingComponent setComments(String_ value) { 
          this.comments = value;
          return this;
        }

        /**
         * @return Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        public String getCommentsSimple() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        /**
         * @param value Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        public DataElementMappingComponent setCommentsSimple(String value) { 
          if (value == null)
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new String_();
            this.comments.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #map} (Expresses what part of the target specification corresponds to this element.)
         */
        public String_ getMap() { 
          return this.map;
        }

        /**
         * @param value {@link #map} (Expresses what part of the target specification corresponds to this element.)
         */
        public DataElementMappingComponent setMap(String_ value) { 
          this.map = value;
          return this;
        }

        /**
         * @return Expresses what part of the target specification corresponds to this element.
         */
        public String getMapSimple() { 
          return this.map == null ? null : this.map.getValue();
        }

        /**
         * @param value Expresses what part of the target specification corresponds to this element.
         */
        public DataElementMappingComponent setMapSimple(String value) { 
            if (this.map == null)
              this.map = new String_();
            this.map.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("uri", "uri", "A URI that identifies the specification that this mapping is expressed to.", 0, java.lang.Integer.MAX_VALUE, uri));
          childrenList.add(new Property("name", "string", "A name for the specification that is being mapped to.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("comments", "string", "Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.", 0, java.lang.Integer.MAX_VALUE, comments));
          childrenList.add(new Property("map", "string", "Expresses what part of the target specification corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, map));
        }

      public DataElementMappingComponent copy() {
        DataElementMappingComponent dst = new DataElementMappingComponent();
        dst.uri = uri == null ? null : uri.copy();
        dst.name = name == null ? null : name.copy();
        dst.comments = comments == null ? null : comments.copy();
        dst.map = map == null ? null : map.copy();
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this data element when it is referenced in a Profile, Questionnaire or an instance.
     */
    protected Identifier identifier;

    /**
     * The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.
     */
    protected String_ version;

    /**
     * Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    protected String_ publisher;

    /**
     * Contact details to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * The status of the data element.
     */
    protected Enumeration<ResourceObservationDefStatus> status;

    /**
     * The date that this version of the data element was published.
     */
    protected DateTime date;

    /**
     * The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    protected String_ name;

    /**
     * A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.
     */
    protected List<CodeableConcept> category = new ArrayList<CodeableConcept>();

    /**
     * A code that provides the meaning for a data element according to a particular terminology.
     */
    protected List<Coding> code = new ArrayList<Coding>();

    /**
     * The default/suggested phrasing to use when prompting a human to capture the data element.
     */
    protected String_ question;

    /**
     * Provides a complete explanation of the meaning of the data element for human readability.
     */
    protected String_ definition;

    /**
     * Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    protected String_ comments;

    /**
     * Explains why this element is needed and why it's been constrained as it has.
     */
    protected String_ requirements;

    /**
     * Identifies additional names by which this element might also be known.
     */
    protected List<String_> synonym = new ArrayList<String_>();

    /**
     * The FHIR data type that is the type for this element.
     */
    protected Code type;

    /**
     * An example value for this element.
     */
    protected org.hl7.fhir.instance.model.Type example;

    /**
     * Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    protected Integer maxLength;

    /**
     * Identifies the units of measure in which the data element should be captured or expressed.
     */
    protected CodeableConcept units;

    /**
     * Binds to a value set if this element is coded (code, Coding, CodeableConcept).
     */
    protected DataElementBindingComponent binding;

    /**
     * Identifies a concept from an external specification that roughly corresponds to this element.
     */
    protected List<DataElementMappingComponent> mapping = new ArrayList<DataElementMappingComponent>();

    private static final long serialVersionUID = 1512979501L;

    public DataElement() {
      super();
    }

    public DataElement(Enumeration<ResourceObservationDefStatus> status) {
      super();
      this.status = status;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this data element when it is referenced in a Profile, Questionnaire or an instance.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this data element when it is referenced in a Profile, Questionnaire or an instance.)
     */
    public DataElement setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.)
     */
    public String_ getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.)
     */
    public DataElement setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.
     */
    public DataElement setVersionSimple(String value) { 
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
     * @return {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the data element.)
     */
    public String_ getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the data element.)
     */
    public DataElement setPublisher(String_ value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    public DataElement setPublisherSimple(String value) { 
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
     * @return {@link #telecom} (Contact details to assist a user in finding and communicating with the publisher.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (Contact details to assist a user in finding and communicating with the publisher.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the data element.)
     */
    public Enumeration<ResourceObservationDefStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the data element.)
     */
    public DataElement setStatus(Enumeration<ResourceObservationDefStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the data element.
     */
    public ResourceObservationDefStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the data element.
     */
    public DataElement setStatusSimple(ResourceObservationDefStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceObservationDefStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #date} (The date that this version of the data element was published.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the data element was published.)
     */
    public DataElement setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that this version of the data element was published.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that this version of the data element was published.
     */
    public DataElement setDateSimple(DateAndTime value) { 
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
     * @return {@link #name} (The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.)
     */
    public DataElement setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    public DataElement setNameSimple(String value) { 
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
     * @return {@link #category} (A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.)
     */
    public List<CodeableConcept> getCategory() { 
      return this.category;
    }

    // syntactic sugar
    /**
     * @return {@link #category} (A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.)
     */
    public CodeableConcept addCategory() { 
      CodeableConcept t = new CodeableConcept();
      this.category.add(t);
      return t;
    }

    /**
     * @return {@link #code} (A code that provides the meaning for a data element according to a particular terminology.)
     */
    public List<Coding> getCode() { 
      return this.code;
    }

    // syntactic sugar
    /**
     * @return {@link #code} (A code that provides the meaning for a data element according to a particular terminology.)
     */
    public Coding addCode() { 
      Coding t = new Coding();
      this.code.add(t);
      return t;
    }

    /**
     * @return {@link #question} (The default/suggested phrasing to use when prompting a human to capture the data element.)
     */
    public String_ getQuestion() { 
      return this.question;
    }

    /**
     * @param value {@link #question} (The default/suggested phrasing to use when prompting a human to capture the data element.)
     */
    public DataElement setQuestion(String_ value) { 
      this.question = value;
      return this;
    }

    /**
     * @return The default/suggested phrasing to use when prompting a human to capture the data element.
     */
    public String getQuestionSimple() { 
      return this.question == null ? null : this.question.getValue();
    }

    /**
     * @param value The default/suggested phrasing to use when prompting a human to capture the data element.
     */
    public DataElement setQuestionSimple(String value) { 
      if (value == null)
        this.question = null;
      else {
        if (this.question == null)
          this.question = new String_();
        this.question.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #definition} (Provides a complete explanation of the meaning of the data element for human readability.)
     */
    public String_ getDefinition() { 
      return this.definition;
    }

    /**
     * @param value {@link #definition} (Provides a complete explanation of the meaning of the data element for human readability.)
     */
    public DataElement setDefinition(String_ value) { 
      this.definition = value;
      return this;
    }

    /**
     * @return Provides a complete explanation of the meaning of the data element for human readability.
     */
    public String getDefinitionSimple() { 
      return this.definition == null ? null : this.definition.getValue();
    }

    /**
     * @param value Provides a complete explanation of the meaning of the data element for human readability.
     */
    public DataElement setDefinitionSimple(String value) { 
      if (value == null)
        this.definition = null;
      else {
        if (this.definition == null)
          this.definition = new String_();
        this.definition.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #comments} (Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.)
     */
    public String_ getComments() { 
      return this.comments;
    }

    /**
     * @param value {@link #comments} (Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.)
     */
    public DataElement setComments(String_ value) { 
      this.comments = value;
      return this;
    }

    /**
     * @return Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    public String getCommentsSimple() { 
      return this.comments == null ? null : this.comments.getValue();
    }

    /**
     * @param value Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    public DataElement setCommentsSimple(String value) { 
      if (value == null)
        this.comments = null;
      else {
        if (this.comments == null)
          this.comments = new String_();
        this.comments.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.)
     */
    public String_ getRequirements() { 
      return this.requirements;
    }

    /**
     * @param value {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.)
     */
    public DataElement setRequirements(String_ value) { 
      this.requirements = value;
      return this;
    }

    /**
     * @return Explains why this element is needed and why it's been constrained as it has.
     */
    public String getRequirementsSimple() { 
      return this.requirements == null ? null : this.requirements.getValue();
    }

    /**
     * @param value Explains why this element is needed and why it's been constrained as it has.
     */
    public DataElement setRequirementsSimple(String value) { 
      if (value == null)
        this.requirements = null;
      else {
        if (this.requirements == null)
          this.requirements = new String_();
        this.requirements.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public List<String_> getSynonym() { 
      return this.synonym;
    }

    // syntactic sugar
    /**
     * @return {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public String_ addSynonym() { 
      String_ t = new String_();
      this.synonym.add(t);
      return t;
    }

    /**
     * @param value {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public String_ addSynonymSimple(String value) { 
      String_ t = new String_();
      t.setValue(value);
      this.synonym.add(t);
      return t;
    }

    /**
     * @param value {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public boolean hasSynonymSimple(String value) { 
      for (String_ v : this.synonym)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #type} (The FHIR data type that is the type for this element.)
     */
    public Code getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The FHIR data type that is the type for this element.)
     */
    public DataElement setType(Code value) { 
      this.type = value;
      return this;
    }

    /**
     * @return The FHIR data type that is the type for this element.
     */
    public String getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value The FHIR data type that is the type for this element.
     */
    public DataElement setTypeSimple(String value) { 
      if (value == null)
        this.type = null;
      else {
        if (this.type == null)
          this.type = new Code();
        this.type.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #example} (An example value for this element.)
     */
    public org.hl7.fhir.instance.model.Type getExample() { 
      return this.example;
    }

    /**
     * @param value {@link #example} (An example value for this element.)
     */
    public DataElement setExample(org.hl7.fhir.instance.model.Type value) { 
      this.example = value;
      return this;
    }

    /**
     * @return {@link #maxLength} (Indicates the shortest length that SHALL be supported by conformant instances without truncation.)
     */
    public Integer getMaxLength() { 
      return this.maxLength;
    }

    /**
     * @param value {@link #maxLength} (Indicates the shortest length that SHALL be supported by conformant instances without truncation.)
     */
    public DataElement setMaxLength(Integer value) { 
      this.maxLength = value;
      return this;
    }

    /**
     * @return Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    public int getMaxLengthSimple() { 
      return this.maxLength == null ? null : this.maxLength.getValue();
    }

    /**
     * @param value Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    public DataElement setMaxLengthSimple(int value) { 
      if (value == -1)
        this.maxLength = null;
      else {
        if (this.maxLength == null)
          this.maxLength = new Integer();
        this.maxLength.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #units} (Identifies the units of measure in which the data element should be captured or expressed.)
     */
    public CodeableConcept getUnits() { 
      return this.units;
    }

    /**
     * @param value {@link #units} (Identifies the units of measure in which the data element should be captured or expressed.)
     */
    public DataElement setUnits(CodeableConcept value) { 
      this.units = value;
      return this;
    }

    /**
     * @return {@link #binding} (Binds to a value set if this element is coded (code, Coding, CodeableConcept).)
     */
    public DataElementBindingComponent getBinding() { 
      return this.binding;
    }

    /**
     * @param value {@link #binding} (Binds to a value set if this element is coded (code, Coding, CodeableConcept).)
     */
    public DataElement setBinding(DataElementBindingComponent value) { 
      this.binding = value;
      return this;
    }

    /**
     * @return {@link #mapping} (Identifies a concept from an external specification that roughly corresponds to this element.)
     */
    public List<DataElementMappingComponent> getMapping() { 
      return this.mapping;
    }

    // syntactic sugar
    /**
     * @return {@link #mapping} (Identifies a concept from an external specification that roughly corresponds to this element.)
     */
    public DataElementMappingComponent addMapping() { 
      DataElementMappingComponent t = new DataElementMappingComponent();
      this.mapping.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The identifier that is used to identify this data element when it is referenced in a Profile, Questionnaire or an instance.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the data element.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("status", "code", "The status of the data element.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the data element was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("name", "string", "The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("category", "CodeableConcept", "A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("code", "Coding", "A code that provides the meaning for a data element according to a particular terminology.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("question", "string", "The default/suggested phrasing to use when prompting a human to capture the data element.", 0, java.lang.Integer.MAX_VALUE, question));
        childrenList.add(new Property("definition", "string", "Provides a complete explanation of the meaning of the data element for human readability.", 0, java.lang.Integer.MAX_VALUE, definition));
        childrenList.add(new Property("comments", "string", "Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.", 0, java.lang.Integer.MAX_VALUE, comments));
        childrenList.add(new Property("requirements", "string", "Explains why this element is needed and why it's been constrained as it has.", 0, java.lang.Integer.MAX_VALUE, requirements));
        childrenList.add(new Property("synonym", "string", "Identifies additional names by which this element might also be known.", 0, java.lang.Integer.MAX_VALUE, synonym));
        childrenList.add(new Property("type", "code", "The FHIR data type that is the type for this element.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("example[x]", "*", "An example value for this element.", 0, java.lang.Integer.MAX_VALUE, example));
        childrenList.add(new Property("maxLength", "integer", "Indicates the shortest length that SHALL be supported by conformant instances without truncation.", 0, java.lang.Integer.MAX_VALUE, maxLength));
        childrenList.add(new Property("units", "CodeableConcept", "Identifies the units of measure in which the data element should be captured or expressed.", 0, java.lang.Integer.MAX_VALUE, units));
        childrenList.add(new Property("binding", "", "Binds to a value set if this element is coded (code, Coding, CodeableConcept).", 0, java.lang.Integer.MAX_VALUE, binding));
        childrenList.add(new Property("mapping", "", "Identifies a concept from an external specification that roughly corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, mapping));
      }

      public DataElement copy() {
        DataElement dst = new DataElement();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.date = date == null ? null : date.copy();
        dst.name = name == null ? null : name.copy();
        dst.category = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : category)
          dst.category.add(i.copy());
        dst.code = new ArrayList<Coding>();
        for (Coding i : code)
          dst.code.add(i.copy());
        dst.question = question == null ? null : question.copy();
        dst.definition = definition == null ? null : definition.copy();
        dst.comments = comments == null ? null : comments.copy();
        dst.requirements = requirements == null ? null : requirements.copy();
        dst.synonym = new ArrayList<String_>();
        for (String_ i : synonym)
          dst.synonym.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.example = example == null ? null : example.copy();
        dst.maxLength = maxLength == null ? null : maxLength.copy();
        dst.units = units == null ? null : units.copy();
        dst.binding = binding == null ? null : binding.copy();
        dst.mapping = new ArrayList<DataElementMappingComponent>();
        for (DataElementMappingComponent i : mapping)
          dst.mapping.add(i.copy());
        return dst;
      }

      protected DataElement typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.DataElement;
   }


}

