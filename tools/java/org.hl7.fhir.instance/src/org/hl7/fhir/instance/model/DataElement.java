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
 * The formal description of a single piece of information that can be gathered and reported.
 */
public class DataElement extends DomainResource {

    public enum ResourceObservationDefStatus {
        DRAFT, // This data element is still under development.
        ACTIVE, // This data element is ready for normal use.
        RETIRED, // This data element has been deprecated, withdrawn or superseded and should no longer be used.
        NULL; // added to help the parsers
        public static ResourceObservationDefStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("retired".equals(codeString))
          return RETIRED;
        throw new Exception("Unknown ResourceObservationDefStatus code '"+codeString+"'");
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
            case DRAFT: return "This data element is still under development.";
            case ACTIVE: return "This data element is ready for normal use.";
            case RETIRED: return "This data element has been deprecated, withdrawn or superseded and should no longer be used.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "draft";
            case ACTIVE: return "active";
            case RETIRED: return "retired";
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
          return ResourceObservationDefStatus.DRAFT;
        if ("active".equals(codeString))
          return ResourceObservationDefStatus.ACTIVE;
        if ("retired".equals(codeString))
          return ResourceObservationDefStatus.RETIRED;
        throw new Exception("Unknown ResourceObservationDefStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceObservationDefStatus.DRAFT)
        return "draft";
      if (code == ResourceObservationDefStatus.ACTIVE)
        return "active";
      if (code == ResourceObservationDefStatus.RETIRED)
        return "retired";
      return "?";
      }
    }

    public enum BindingConformance {
        REQUIRED, // Only codes in the specified set are allowed.  If the binding is extensible, other codes may be used for concepts not covered by the bound set of codes.
        PREFERRED, // For greater interoperability, implementers are strongly encouraged to use the bound set of codes, however alternate codes may be used in derived profiles and implementations if necessary without being considered non-conformant.
        EXAMPLE, // The codes in the set are an example to illustrate the meaning of the field. There is no particular preference for its use nor any assertion that the provided values are sufficient to meet implementation needs.
        NULL; // added to help the parsers
        public static BindingConformance fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("required".equals(codeString))
          return REQUIRED;
        if ("preferred".equals(codeString))
          return PREFERRED;
        if ("example".equals(codeString))
          return EXAMPLE;
        throw new Exception("Unknown BindingConformance code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REQUIRED: return "required";
            case PREFERRED: return "preferred";
            case EXAMPLE: return "example";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REQUIRED: return "Only codes in the specified set are allowed.  If the binding is extensible, other codes may be used for concepts not covered by the bound set of codes.";
            case PREFERRED: return "For greater interoperability, implementers are strongly encouraged to use the bound set of codes, however alternate codes may be used in derived profiles and implementations if necessary without being considered non-conformant.";
            case EXAMPLE: return "The codes in the set are an example to illustrate the meaning of the field. There is no particular preference for its use nor any assertion that the provided values are sufficient to meet implementation needs.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REQUIRED: return "required";
            case PREFERRED: return "preferred";
            case EXAMPLE: return "example";
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
          return BindingConformance.REQUIRED;
        if ("preferred".equals(codeString))
          return BindingConformance.PREFERRED;
        if ("example".equals(codeString))
          return BindingConformance.EXAMPLE;
        throw new Exception("Unknown BindingConformance code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == BindingConformance.REQUIRED)
        return "required";
      if (code == BindingConformance.PREFERRED)
        return "preferred";
      if (code == BindingConformance.EXAMPLE)
        return "example";
      return "?";
      }
    }

    public static class DataElementBindingComponent extends BackboneElement {
        /**
         * If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        protected BooleanType isExtensible;

        /**
         * Indicates the degree of conformance expectations associated with this binding.
         */
        protected Enumeration<BindingConformance> conformance;

        /**
         * Describes the intended use of this particular set of codes.
         */
        protected StringType description;

        /**
         * Points to the value set that identifies the set of codes to be used.
         */
        protected Reference valueSet;

        /**
         * The actual object that is the target of the reference (Points to the value set that identifies the set of codes to be used.)
         */
        protected ValueSet valueSetTarget;

        private static final long serialVersionUID = -1297440999L;

      public DataElementBindingComponent() {
        super();
      }

      public DataElementBindingComponent(BooleanType isExtensible) {
        super();
        this.isExtensible = isExtensible;
      }

        /**
         * @return {@link #isExtensible} (If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.). This is the underlying object with id, value and extensions. The accessor "getIsExtensible" gives direct access to the value
         */
        public BooleanType getIsExtensibleElement() { 
          return this.isExtensible;
        }

        /**
         * @param value {@link #isExtensible} (If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.). This is the underlying object with id, value and extensions. The accessor "getIsExtensible" gives direct access to the value
         */
        public DataElementBindingComponent setIsExtensibleElement(BooleanType value) { 
          this.isExtensible = value;
          return this;
        }

        /**
         * @return If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        public boolean getIsExtensible() { 
          return this.isExtensible == null ? false : this.isExtensible.getValue();
        }

        /**
         * @param value If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.
         */
        public DataElementBindingComponent setIsExtensible(boolean value) { 
            if (this.isExtensible == null)
              this.isExtensible = new BooleanType();
            this.isExtensible.setValue(value);
          return this;
        }

        /**
         * @return {@link #conformance} (Indicates the degree of conformance expectations associated with this binding.). This is the underlying object with id, value and extensions. The accessor "getConformance" gives direct access to the value
         */
        public Enumeration<BindingConformance> getConformanceElement() { 
          return this.conformance;
        }

        /**
         * @param value {@link #conformance} (Indicates the degree of conformance expectations associated with this binding.). This is the underlying object with id, value and extensions. The accessor "getConformance" gives direct access to the value
         */
        public DataElementBindingComponent setConformanceElement(Enumeration<BindingConformance> value) { 
          this.conformance = value;
          return this;
        }

        /**
         * @return Indicates the degree of conformance expectations associated with this binding.
         */
        public BindingConformance getConformance() { 
          return this.conformance == null ? null : this.conformance.getValue();
        }

        /**
         * @param value Indicates the degree of conformance expectations associated with this binding.
         */
        public DataElementBindingComponent setConformance(BindingConformance value) { 
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
         * @return {@link #description} (Describes the intended use of this particular set of codes.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public StringType getDescriptionElement() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Describes the intended use of this particular set of codes.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
         */
        public DataElementBindingComponent setDescriptionElement(StringType value) { 
          this.description = value;
          return this;
        }

        /**
         * @return Describes the intended use of this particular set of codes.
         */
        public String getDescription() { 
          return this.description == null ? null : this.description.getValue();
        }

        /**
         * @param value Describes the intended use of this particular set of codes.
         */
        public DataElementBindingComponent setDescription(String value) { 
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
         * @return {@link #valueSet} (Points to the value set that identifies the set of codes to be used.)
         */
        public Reference getValueSet() { 
          return this.valueSet;
        }

        /**
         * @param value {@link #valueSet} (Points to the value set that identifies the set of codes to be used.)
         */
        public DataElementBindingComponent setValueSet(Reference value) { 
          this.valueSet = value;
          return this;
        }

        /**
         * @return {@link #valueSet} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Points to the value set that identifies the set of codes to be used.)
         */
        public ValueSet getValueSetTarget() { 
          return this.valueSetTarget;
        }

        /**
         * @param value {@link #valueSet} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Points to the value set that identifies the set of codes to be used.)
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
          childrenList.add(new Property("valueSet", "Reference(ValueSet)", "Points to the value set that identifies the set of codes to be used.", 0, java.lang.Integer.MAX_VALUE, valueSet));
        }

      public DataElementBindingComponent copy() {
        DataElementBindingComponent dst = new DataElementBindingComponent();
        copyValues(dst);
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
        protected UriType uri;

        /**
         * A name for the specification that is being mapped to.
         */
        protected StringType name;

        /**
         * Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        protected StringType comments;

        /**
         * Expresses what part of the target specification corresponds to this element.
         */
        protected StringType map;

        private static final long serialVersionUID = -229299076L;

      public DataElementMappingComponent() {
        super();
      }

      public DataElementMappingComponent(StringType map) {
        super();
        this.map = map;
      }

        /**
         * @return {@link #uri} (A URI that identifies the specification that this mapping is expressed to.). This is the underlying object with id, value and extensions. The accessor "getUri" gives direct access to the value
         */
        public UriType getUriElement() { 
          return this.uri;
        }

        /**
         * @param value {@link #uri} (A URI that identifies the specification that this mapping is expressed to.). This is the underlying object with id, value and extensions. The accessor "getUri" gives direct access to the value
         */
        public DataElementMappingComponent setUriElement(UriType value) { 
          this.uri = value;
          return this;
        }

        /**
         * @return A URI that identifies the specification that this mapping is expressed to.
         */
        public String getUri() { 
          return this.uri == null ? null : this.uri.getValue();
        }

        /**
         * @param value A URI that identifies the specification that this mapping is expressed to.
         */
        public DataElementMappingComponent setUri(String value) { 
          if (Utilities.noString(value))
            this.uri = null;
          else {
            if (this.uri == null)
              this.uri = new UriType();
            this.uri.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #name} (A name for the specification that is being mapped to.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A name for the specification that is being mapped to.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public DataElementMappingComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return A name for the specification that is being mapped to.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value A name for the specification that is being mapped to.
         */
        public DataElementMappingComponent setName(String value) { 
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
         * @return {@link #comments} (Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
         */
        public StringType getCommentsElement() { 
          return this.comments;
        }

        /**
         * @param value {@link #comments} (Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
         */
        public DataElementMappingComponent setCommentsElement(StringType value) { 
          this.comments = value;
          return this;
        }

        /**
         * @return Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        public String getComments() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        /**
         * @param value Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.
         */
        public DataElementMappingComponent setComments(String value) { 
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
         * @return {@link #map} (Expresses what part of the target specification corresponds to this element.). This is the underlying object with id, value and extensions. The accessor "getMap" gives direct access to the value
         */
        public StringType getMapElement() { 
          return this.map;
        }

        /**
         * @param value {@link #map} (Expresses what part of the target specification corresponds to this element.). This is the underlying object with id, value and extensions. The accessor "getMap" gives direct access to the value
         */
        public DataElementMappingComponent setMapElement(StringType value) { 
          this.map = value;
          return this;
        }

        /**
         * @return Expresses what part of the target specification corresponds to this element.
         */
        public String getMap() { 
          return this.map == null ? null : this.map.getValue();
        }

        /**
         * @param value Expresses what part of the target specification corresponds to this element.
         */
        public DataElementMappingComponent setMap(String value) { 
            if (this.map == null)
              this.map = new StringType();
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
        copyValues(dst);
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
    protected StringType version;

    /**
     * Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    protected StringType publisher;

    /**
     * Contact details to assist a user in finding and communicating with the publisher.
     */
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * The status of the data element.
     */
    protected Enumeration<ResourceObservationDefStatus> status;

    /**
     * The date that this version of the data element was published.
     */
    protected DateTimeType date;

    /**
     * The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    protected StringType name;

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
    protected StringType question;

    /**
     * Provides a complete explanation of the meaning of the data element for human readability.
     */
    protected StringType definition;

    /**
     * Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    protected StringType comments;

    /**
     * Explains why this element is needed and why it's been constrained as it has.
     */
    protected StringType requirements;

    /**
     * Identifies additional names by which this element might also be known.
     */
    protected List<StringType> synonym = new ArrayList<StringType>();

    /**
     * The FHIR data type that is the type for this element.
     */
    protected CodeType type;

    /**
     * An example value for this element.
     */
    protected org.hl7.fhir.instance.model.Type example;

    /**
     * Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    protected IntegerType maxLength;

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

    private static final long serialVersionUID = 1322414906L;

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
     * @return {@link #version} (The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public DataElement setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.
     */
    public DataElement setVersion(String value) { 
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
     * @return {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the data element.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the data element.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public DataElement setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Details of the individual or organization who accepts responsibility for publishing the data element.
     */
    public DataElement setPublisher(String value) { 
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
     * @return {@link #telecom} (Contact details to assist a user in finding and communicating with the publisher.)
     */
    public List<ContactPoint> getTelecom() { 
      return this.telecom;
    }

    /**
     * @return {@link #telecom} (Contact details to assist a user in finding and communicating with the publisher.)
     */
    // syntactic sugar
    public ContactPoint addTelecom() { //3
      ContactPoint t = new ContactPoint();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the data element.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ResourceObservationDefStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the data element.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public DataElement setStatusElement(Enumeration<ResourceObservationDefStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the data element.
     */
    public ResourceObservationDefStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the data element.
     */
    public DataElement setStatus(ResourceObservationDefStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceObservationDefStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #date} (The date that this version of the data element was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the data element was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DataElement setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that this version of the data element was published.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that this version of the data element was published.
     */
    public DataElement setDate(DateAndTime value) { 
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
     * @return {@link #name} (The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public DataElement setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The term used by humans to refer to the data element.  Should ideally be unique within the context in which the data element is expected to be used.
     */
    public DataElement setName(String value) { 
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
     * @return {@link #category} (A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.)
     */
    public List<CodeableConcept> getCategory() { 
      return this.category;
    }

    /**
     * @return {@link #category} (A set of terms from external terminologies that may be used to assist with indexing and searching of data element definitions.)
     */
    // syntactic sugar
    public CodeableConcept addCategory() { //3
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

    /**
     * @return {@link #code} (A code that provides the meaning for a data element according to a particular terminology.)
     */
    // syntactic sugar
    public Coding addCode() { //3
      Coding t = new Coding();
      this.code.add(t);
      return t;
    }

    /**
     * @return {@link #question} (The default/suggested phrasing to use when prompting a human to capture the data element.). This is the underlying object with id, value and extensions. The accessor "getQuestion" gives direct access to the value
     */
    public StringType getQuestionElement() { 
      return this.question;
    }

    /**
     * @param value {@link #question} (The default/suggested phrasing to use when prompting a human to capture the data element.). This is the underlying object with id, value and extensions. The accessor "getQuestion" gives direct access to the value
     */
    public DataElement setQuestionElement(StringType value) { 
      this.question = value;
      return this;
    }

    /**
     * @return The default/suggested phrasing to use when prompting a human to capture the data element.
     */
    public String getQuestion() { 
      return this.question == null ? null : this.question.getValue();
    }

    /**
     * @param value The default/suggested phrasing to use when prompting a human to capture the data element.
     */
    public DataElement setQuestion(String value) { 
      if (Utilities.noString(value))
        this.question = null;
      else {
        if (this.question == null)
          this.question = new StringType();
        this.question.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #definition} (Provides a complete explanation of the meaning of the data element for human readability.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
     */
    public StringType getDefinitionElement() { 
      return this.definition;
    }

    /**
     * @param value {@link #definition} (Provides a complete explanation of the meaning of the data element for human readability.). This is the underlying object with id, value and extensions. The accessor "getDefinition" gives direct access to the value
     */
    public DataElement setDefinitionElement(StringType value) { 
      this.definition = value;
      return this;
    }

    /**
     * @return Provides a complete explanation of the meaning of the data element for human readability.
     */
    public String getDefinition() { 
      return this.definition == null ? null : this.definition.getValue();
    }

    /**
     * @param value Provides a complete explanation of the meaning of the data element for human readability.
     */
    public DataElement setDefinition(String value) { 
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
     * @return {@link #comments} (Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
     */
    public StringType getCommentsElement() { 
      return this.comments;
    }

    /**
     * @param value {@link #comments} (Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.). This is the underlying object with id, value and extensions. The accessor "getComments" gives direct access to the value
     */
    public DataElement setCommentsElement(StringType value) { 
      this.comments = value;
      return this;
    }

    /**
     * @return Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    public String getComments() { 
      return this.comments == null ? null : this.comments.getValue();
    }

    /**
     * @param value Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.
     */
    public DataElement setComments(String value) { 
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
     * @return {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.). This is the underlying object with id, value and extensions. The accessor "getRequirements" gives direct access to the value
     */
    public StringType getRequirementsElement() { 
      return this.requirements;
    }

    /**
     * @param value {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.). This is the underlying object with id, value and extensions. The accessor "getRequirements" gives direct access to the value
     */
    public DataElement setRequirementsElement(StringType value) { 
      this.requirements = value;
      return this;
    }

    /**
     * @return Explains why this element is needed and why it's been constrained as it has.
     */
    public String getRequirements() { 
      return this.requirements == null ? null : this.requirements.getValue();
    }

    /**
     * @param value Explains why this element is needed and why it's been constrained as it has.
     */
    public DataElement setRequirements(String value) { 
      if (Utilities.noString(value))
        this.requirements = null;
      else {
        if (this.requirements == null)
          this.requirements = new StringType();
        this.requirements.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public List<StringType> getSynonym() { 
      return this.synonym;
    }

    /**
     * @return {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    // syntactic sugar
    public StringType addSynonymElement() {//2 
      StringType t = new StringType();
      this.synonym.add(t);
      return t;
    }

    /**
     * @param value {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public DataElement addSynonym(String value) { //1
      StringType t = new StringType();
      t.setValue(value);
      this.synonym.add(t);
      return this;
    }

    /**
     * @param value {@link #synonym} (Identifies additional names by which this element might also be known.)
     */
    public boolean hasSynonym(String value) { 
      for (StringType v : this.synonym)
        if (v.equals(value)) // string
          return true;
      return false;
    }

    /**
     * @return {@link #type} (The FHIR data type that is the type for this element.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public CodeType getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The FHIR data type that is the type for this element.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public DataElement setTypeElement(CodeType value) { 
      this.type = value;
      return this;
    }

    /**
     * @return The FHIR data type that is the type for this element.
     */
    public String getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value The FHIR data type that is the type for this element.
     */
    public DataElement setType(String value) { 
      if (Utilities.noString(value))
        this.type = null;
      else {
        if (this.type == null)
          this.type = new CodeType();
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
     * @return {@link #maxLength} (Indicates the shortest length that SHALL be supported by conformant instances without truncation.). This is the underlying object with id, value and extensions. The accessor "getMaxLength" gives direct access to the value
     */
    public IntegerType getMaxLengthElement() { 
      return this.maxLength;
    }

    /**
     * @param value {@link #maxLength} (Indicates the shortest length that SHALL be supported by conformant instances without truncation.). This is the underlying object with id, value and extensions. The accessor "getMaxLength" gives direct access to the value
     */
    public DataElement setMaxLengthElement(IntegerType value) { 
      this.maxLength = value;
      return this;
    }

    /**
     * @return Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    public int getMaxLength() { 
      return this.maxLength == null ? null : this.maxLength.getValue();
    }

    /**
     * @param value Indicates the shortest length that SHALL be supported by conformant instances without truncation.
     */
    public DataElement setMaxLength(int value) { 
      if (value == -1)
        this.maxLength = null;
      else {
        if (this.maxLength == null)
          this.maxLength = new IntegerType();
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

    /**
     * @return {@link #mapping} (Identifies a concept from an external specification that roughly corresponds to this element.)
     */
    // syntactic sugar
    public DataElementMappingComponent addMapping() { //3
      DataElementMappingComponent t = new DataElementMappingComponent();
      this.mapping.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "The identifier that is used to identify this data element when it is referenced in a Profile, Questionnaire or an instance.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the data element when it is referenced in a Profile, Questionnaire or instance. This is an arbitrary value managed by the definition author manually.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the data element.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "ContactPoint", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
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
        copyValues(dst);
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
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
        dst.synonym = new ArrayList<StringType>();
        for (StringType i : synonym)
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

