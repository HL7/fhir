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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * A Resource Profile - a statement of use of one or more FHIR Resources.  It may include constraints on Resources and Data Types, Terminology Binding Statements and Extension Definitions.
 */
public class Profile extends Resource {

    public enum ResourceProfileStatus {
        draft, // This profile is still under development.
        active, // This profile is ready for normal use.
        retired, // This profile has been deprecated, withdrawn or superseded and should no longer be used.
        Null; // added to help the parsers
        public static ResourceProfileStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return draft;
        if ("active".equals(codeString))
          return active;
        if ("retired".equals(codeString))
          return retired;
        throw new Exception("Unknown ResourceProfileStatus code '"+codeString+"'");
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

  public static class ResourceProfileStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ResourceProfileStatus.draft;
        if ("active".equals(codeString))
          return ResourceProfileStatus.active;
        if ("retired".equals(codeString))
          return ResourceProfileStatus.retired;
        throw new Exception("Unknown ResourceProfileStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceProfileStatus.draft)
        return "draft";
      if (code == ResourceProfileStatus.active)
        return "active";
      if (code == ResourceProfileStatus.retired)
        return "retired";
      return "?";
      }
    }

    public enum ResourceSlicingRules {
        closed, // No additional content is allowed other than that described by the slices in this profile.
        open, // Additional content is allowed anywhere in the list.
        openAtEnd, // Additional content is allowed, but only at the end of the list.
        Null; // added to help the parsers
        public static ResourceSlicingRules fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("closed".equals(codeString))
          return closed;
        if ("open".equals(codeString))
          return open;
        if ("openAtEnd".equals(codeString))
          return openAtEnd;
        throw new Exception("Unknown ResourceSlicingRules code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case closed: return "closed";
            case open: return "open";
            case openAtEnd: return "openAtEnd";
            default: return "?";
          }
        }
    }

  public static class ResourceSlicingRulesEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("closed".equals(codeString))
          return ResourceSlicingRules.closed;
        if ("open".equals(codeString))
          return ResourceSlicingRules.open;
        if ("openAtEnd".equals(codeString))
          return ResourceSlicingRules.openAtEnd;
        throw new Exception("Unknown ResourceSlicingRules code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceSlicingRules.closed)
        return "closed";
      if (code == ResourceSlicingRules.open)
        return "open";
      if (code == ResourceSlicingRules.openAtEnd)
        return "openAtEnd";
      return "?";
      }
    }

    public enum ResourceAggregationMode {
        contained, // The reference is a local reference to a contained resource.
        referenced, // The reference to to a resource that has to be resolved externally to the resource that includes the reference.
        bundled, // The resource the reference points to will be found in the same bundle as the resource that includes the reference.
        Null; // added to help the parsers
        public static ResourceAggregationMode fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("contained".equals(codeString))
          return contained;
        if ("referenced".equals(codeString))
          return referenced;
        if ("bundled".equals(codeString))
          return bundled;
        throw new Exception("Unknown ResourceAggregationMode code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case contained: return "contained";
            case referenced: return "referenced";
            case bundled: return "bundled";
            default: return "?";
          }
        }
    }

  public static class ResourceAggregationModeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("contained".equals(codeString))
          return ResourceAggregationMode.contained;
        if ("referenced".equals(codeString))
          return ResourceAggregationMode.referenced;
        if ("bundled".equals(codeString))
          return ResourceAggregationMode.bundled;
        throw new Exception("Unknown ResourceAggregationMode code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceAggregationMode.contained)
        return "contained";
      if (code == ResourceAggregationMode.referenced)
        return "referenced";
      if (code == ResourceAggregationMode.bundled)
        return "bundled";
      return "?";
      }
    }

    public enum ConstraintSeverity {
        error, // If the constraint is violated, the resource is not conformant.
        warning, // If the constraint is violated, the resource is conformant, but it is not necessarily following best practice.
        Null; // added to help the parsers
        public static ConstraintSeverity fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("error".equals(codeString))
          return error;
        if ("warning".equals(codeString))
          return warning;
        throw new Exception("Unknown ConstraintSeverity code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case error: return "error";
            case warning: return "warning";
            default: return "?";
          }
        }
    }

  public static class ConstraintSeverityEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("error".equals(codeString))
          return ConstraintSeverity.error;
        if ("warning".equals(codeString))
          return ConstraintSeverity.warning;
        throw new Exception("Unknown ConstraintSeverity code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ConstraintSeverity.error)
        return "error";
      if (code == ConstraintSeverity.warning)
        return "warning";
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

    public enum ExtensionContext {
        resource, // The context is all elements matching a particular resource element path.
        datatype, // The context is all nodes matching a particular data type element path (root or repeating element) or all elements referencing a particular primitive data type (expressed as the datatype name).
        mapping, // The context is all nodes whose mapping to a specified reference model corresponds to a particular mapping structure.  The context identifies the mapping target. The mapping should clearly identify where such an extension could be used, though this.
        extension, // The context is a particular extension from a particular profile.  Expressed as uri#name, where uri identifies the profile and #name identifies the extension code.
        Null; // added to help the parsers
        public static ExtensionContext fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("resource".equals(codeString))
          return resource;
        if ("datatype".equals(codeString))
          return datatype;
        if ("mapping".equals(codeString))
          return mapping;
        if ("extension".equals(codeString))
          return extension;
        throw new Exception("Unknown ExtensionContext code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case resource: return "resource";
            case datatype: return "datatype";
            case mapping: return "mapping";
            case extension: return "extension";
            default: return "?";
          }
        }
    }

  public static class ExtensionContextEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("resource".equals(codeString))
          return ExtensionContext.resource;
        if ("datatype".equals(codeString))
          return ExtensionContext.datatype;
        if ("mapping".equals(codeString))
          return ExtensionContext.mapping;
        if ("extension".equals(codeString))
          return ExtensionContext.extension;
        throw new Exception("Unknown ExtensionContext code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ExtensionContext.resource)
        return "resource";
      if (code == ExtensionContext.datatype)
        return "datatype";
      if (code == ExtensionContext.mapping)
        return "mapping";
      if (code == ExtensionContext.extension)
        return "extension";
      return "?";
      }
    }

    public static class ProfileStructureComponent extends BackboneElement {
        /**
         * The Resource or Data type being described.
         */
        protected Code type;

        /**
         * The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.element.definition.type.profile).
         */
        protected String_ name;

        /**
         * This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.
         */
        protected Boolean publish;

        /**
         * Human summary: why describe this resource?.
         */
        protected String_ purpose;

        /**
         * Captures constraints on each element within the resource.
         */
        protected List<ElementComponent> element = new ArrayList<ElementComponent>();

      public ProfileStructureComponent() {
        super();
      }

      public ProfileStructureComponent(Code type) {
        super();
        this.type = type;
      }

        public Code getType() { 
          return this.type;
        }

        public ProfileStructureComponent setType(Code value) { 
          this.type = value;
          return this;
        }

        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public ProfileStructureComponent setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
          return this;
        }

        public String_ getName() { 
          return this.name;
        }

        public ProfileStructureComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public ProfileStructureComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        public Boolean getPublish() { 
          return this.publish;
        }

        public ProfileStructureComponent setPublish(Boolean value) { 
          this.publish = value;
          return this;
        }

        public boolean getPublishSimple() { 
          return this.publish == null ? null : this.publish.getValue();
        }

        public ProfileStructureComponent setPublishSimple(boolean value) { 
          if (value == false)
            this.publish = null;
          else {
            if (this.publish == null)
              this.publish = new Boolean();
            this.publish.setValue(value);
          }
          return this;
        }

        public String_ getPurpose() { 
          return this.purpose;
        }

        public ProfileStructureComponent setPurpose(String_ value) { 
          this.purpose = value;
          return this;
        }

        public String getPurposeSimple() { 
          return this.purpose == null ? null : this.purpose.getValue();
        }

        public ProfileStructureComponent setPurposeSimple(String value) { 
          if (value == null)
            this.purpose = null;
          else {
            if (this.purpose == null)
              this.purpose = new String_();
            this.purpose.setValue(value);
          }
          return this;
        }

        public List<ElementComponent> getElement() { 
          return this.element;
        }

    // syntactic sugar
        public ElementComponent addElement() { 
          ElementComponent t = new ElementComponent();
          this.element.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The Resource or Data type being described.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("name", "string", "The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.element.definition.type.profile).", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("publish", "boolean", "This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.", 0, java.lang.Integer.MAX_VALUE, publish));
          childrenList.add(new Property("purpose", "string", "Human summary: why describe this resource?.", 0, java.lang.Integer.MAX_VALUE, purpose));
          childrenList.add(new Property("element", "", "Captures constraints on each element within the resource.", 0, java.lang.Integer.MAX_VALUE, element));
        }

      public ProfileStructureComponent copy(Profile e) {
        ProfileStructureComponent dst = new ProfileStructureComponent();
        dst.type = type == null ? null : type.copy();
        dst.name = name == null ? null : name.copy();
        dst.publish = publish == null ? null : publish.copy();
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.element = new ArrayList<ElementComponent>();
        for (ElementComponent i : element)
          dst.element.add(i.copy(e));
        return dst;
      }

  }

    public static class ElementComponent extends BackboneElement {
        /**
         * The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.
         */
        protected String_ path;

        /**
         * The name of this element definition (to refer to it from other element definitions using Profile.structure.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.
         */
        protected String_ name;

        /**
         * Indicates that the element is sliced into a set of alternative definitions (there are multiple definitions on a single element in the base resource). The set of slices is any elements that come after this in the element sequence that have the same path, until a shorter path occurs (the shorter path terminates the set).
         */
        protected ElementSlicingComponent slicing;

        /**
         * Definition of the content of the element to provide a more specific definition than that contained for the element in the base resource.
         */
        protected ElementDefinitionComponent definition;

      public ElementComponent() {
        super();
      }

      public ElementComponent(String_ path) {
        super();
        this.path = path;
      }

        public String_ getPath() { 
          return this.path;
        }

        public ElementComponent setPath(String_ value) { 
          this.path = value;
          return this;
        }

        public String getPathSimple() { 
          return this.path == null ? null : this.path.getValue();
        }

        public ElementComponent setPathSimple(String value) { 
            if (this.path == null)
              this.path = new String_();
            this.path.setValue(value);
          return this;
        }

        public String_ getName() { 
          return this.name;
        }

        public ElementComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public ElementComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        public ElementSlicingComponent getSlicing() { 
          return this.slicing;
        }

        public ElementComponent setSlicing(ElementSlicingComponent value) { 
          this.slicing = value;
          return this;
        }

        public ElementDefinitionComponent getDefinition() { 
          return this.definition;
        }

        public ElementComponent setDefinition(ElementDefinitionComponent value) { 
          this.definition = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("path", "string", "The path identifies the element and is expressed as a '.'-separated list of ancestor elements, beginning with the name of the resource.", 0, java.lang.Integer.MAX_VALUE, path));
          childrenList.add(new Property("name", "string", "The name of this element definition (to refer to it from other element definitions using Profile.structure.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("slicing", "", "Indicates that the element is sliced into a set of alternative definitions (there are multiple definitions on a single element in the base resource). The set of slices is any elements that come after this in the element sequence that have the same path, until a shorter path occurs (the shorter path terminates the set).", 0, java.lang.Integer.MAX_VALUE, slicing));
          childrenList.add(new Property("definition", "", "Definition of the content of the element to provide a more specific definition than that contained for the element in the base resource.", 0, java.lang.Integer.MAX_VALUE, definition));
        }

      public ElementComponent copy(Profile e) {
        ElementComponent dst = new ElementComponent();
        dst.path = path == null ? null : path.copy();
        dst.name = name == null ? null : name.copy();
        dst.slicing = slicing == null ? null : slicing.copy(e);
        dst.definition = definition == null ? null : definition.copy(e);
        return dst;
      }

  }

    public static class ElementSlicingComponent extends BackboneElement {
        /**
         * Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.
         */
        protected Id discriminator;

        /**
         * If the matching elements have to occur in the same order as defined in the profile.
         */
        protected Boolean ordered;

        /**
         * Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.
         */
        protected Enumeration<ResourceSlicingRules> rules;

      public ElementSlicingComponent() {
        super();
      }

      public ElementSlicingComponent(Id discriminator, Boolean ordered, Enumeration<ResourceSlicingRules> rules) {
        super();
        this.discriminator = discriminator;
        this.ordered = ordered;
        this.rules = rules;
      }

        public Id getDiscriminator() { 
          return this.discriminator;
        }

        public ElementSlicingComponent setDiscriminator(Id value) { 
          this.discriminator = value;
          return this;
        }

        public String getDiscriminatorSimple() { 
          return this.discriminator == null ? null : this.discriminator.getValue();
        }

        public ElementSlicingComponent setDiscriminatorSimple(String value) { 
            if (this.discriminator == null)
              this.discriminator = new Id();
            this.discriminator.setValue(value);
          return this;
        }

        public Boolean getOrdered() { 
          return this.ordered;
        }

        public ElementSlicingComponent setOrdered(Boolean value) { 
          this.ordered = value;
          return this;
        }

        public boolean getOrderedSimple() { 
          return this.ordered == null ? null : this.ordered.getValue();
        }

        public ElementSlicingComponent setOrderedSimple(boolean value) { 
            if (this.ordered == null)
              this.ordered = new Boolean();
            this.ordered.setValue(value);
          return this;
        }

        public Enumeration<ResourceSlicingRules> getRules() { 
          return this.rules;
        }

        public ElementSlicingComponent setRules(Enumeration<ResourceSlicingRules> value) { 
          this.rules = value;
          return this;
        }

        public ResourceSlicingRules getRulesSimple() { 
          return this.rules == null ? null : this.rules.getValue();
        }

        public ElementSlicingComponent setRulesSimple(ResourceSlicingRules value) { 
            if (this.rules == null)
              this.rules = new Enumeration<ResourceSlicingRules>();
            this.rules.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("discriminator", "id", "Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.", 0, java.lang.Integer.MAX_VALUE, discriminator));
          childrenList.add(new Property("ordered", "boolean", "If the matching elements have to occur in the same order as defined in the profile.", 0, java.lang.Integer.MAX_VALUE, ordered));
          childrenList.add(new Property("rules", "code", "Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.", 0, java.lang.Integer.MAX_VALUE, rules));
        }

      public ElementSlicingComponent copy(Profile e) {
        ElementSlicingComponent dst = new ElementSlicingComponent();
        dst.discriminator = discriminator == null ? null : discriminator.copy();
        dst.ordered = ordered == null ? null : ordered.copy();
        dst.rules = rules == null ? null : rules.copy();
        return dst;
      }

  }

    public static class ElementDefinitionComponent extends BackboneElement {
        /**
         * A concise definition that  is shown in the concise XML format that summarizes profiles.
         */
        protected String_ short_;

        /**
         * The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.
         */
        protected String_ formal;

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
         * The minimum number of times this element SHALL appear in the instance.
         */
        protected Integer min;

        /**
         * The maximum number of times this element is permitted to appear in the instance.
         */
        protected String_ max;

        /**
         * The data type or resource that the value of this element is permitted to be.
         */
        protected List<TypeRefComponent> type = new ArrayList<TypeRefComponent>();

        /**
         * Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.
         */
        protected String_ nameReference;

        /**
         * Specifies a value that SHALL hold for this element in the instance.
         */
        protected org.hl7.fhir.instance.model.Type value;

        /**
         * An example value for this element.
         */
        protected org.hl7.fhir.instance.model.Type example;

        /**
         * Indicates the shortest length that SHALL be supported by conformant instances without truncation.
         */
        protected Integer maxLength;

        /**
         * A reference to an invariant that may make additional statements about the cardinality in the instance.
         */
        protected List<Id> condition = new ArrayList<Id>();

        /**
         * Formal constraints such as co-occurrence and other constraints that can be computationally evaluated within the context of the instance.
         */
        protected List<ElementDefinitionConstraintComponent> constraint = new ArrayList<ElementDefinitionConstraintComponent>();

        /**
         * If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.
         */
        protected Boolean mustSupport;

        /**
         * If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.
         */
        protected Boolean isModifier;

        /**
         * Binds to a value set if this element is coded (code, Coding, CodeableConcept).
         */
        protected ElementDefinitionBindingComponent binding;

        /**
         * Identifies a concept from an external specification that roughly corresponds to this element.
         */
        protected List<ElementDefinitionMappingComponent> mapping = new ArrayList<ElementDefinitionMappingComponent>();

      public ElementDefinitionComponent() {
        super();
      }

      public ElementDefinitionComponent(String_ short_, String_ formal, Integer min, String_ max, Boolean isModifier) {
        super();
        this.short_ = short_;
        this.formal = formal;
        this.min = min;
        this.max = max;
        this.isModifier = isModifier;
      }

        public String_ getShort() { 
          return this.short_;
        }

        public ElementDefinitionComponent setShort(String_ value) { 
          this.short_ = value;
          return this;
        }

        public String getShortSimple() { 
          return this.short_ == null ? null : this.short_.getValue();
        }

        public ElementDefinitionComponent setShortSimple(String value) { 
            if (this.short_ == null)
              this.short_ = new String_();
            this.short_.setValue(value);
          return this;
        }

        public String_ getFormal() { 
          return this.formal;
        }

        public ElementDefinitionComponent setFormal(String_ value) { 
          this.formal = value;
          return this;
        }

        public String getFormalSimple() { 
          return this.formal == null ? null : this.formal.getValue();
        }

        public ElementDefinitionComponent setFormalSimple(String value) { 
            if (this.formal == null)
              this.formal = new String_();
            this.formal.setValue(value);
          return this;
        }

        public String_ getComments() { 
          return this.comments;
        }

        public ElementDefinitionComponent setComments(String_ value) { 
          this.comments = value;
          return this;
        }

        public String getCommentsSimple() { 
          return this.comments == null ? null : this.comments.getValue();
        }

        public ElementDefinitionComponent setCommentsSimple(String value) { 
          if (value == null)
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new String_();
            this.comments.setValue(value);
          }
          return this;
        }

        public String_ getRequirements() { 
          return this.requirements;
        }

        public ElementDefinitionComponent setRequirements(String_ value) { 
          this.requirements = value;
          return this;
        }

        public String getRequirementsSimple() { 
          return this.requirements == null ? null : this.requirements.getValue();
        }

        public ElementDefinitionComponent setRequirementsSimple(String value) { 
          if (value == null)
            this.requirements = null;
          else {
            if (this.requirements == null)
              this.requirements = new String_();
            this.requirements.setValue(value);
          }
          return this;
        }

        public List<String_> getSynonym() { 
          return this.synonym;
        }

    // syntactic sugar
        public String_ addSynonym() { 
          String_ t = new String_();
          this.synonym.add(t);
          return t;
        }

        public String_ addSynonymSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.synonym.add(t);
          return t;
        }

        public Integer getMin() { 
          return this.min;
        }

        public ElementDefinitionComponent setMin(Integer value) { 
          this.min = value;
          return this;
        }

        public int getMinSimple() { 
          return this.min == null ? null : this.min.getValue();
        }

        public ElementDefinitionComponent setMinSimple(int value) { 
            if (this.min == null)
              this.min = new Integer();
            this.min.setValue(value);
          return this;
        }

        public String_ getMax() { 
          return this.max;
        }

        public ElementDefinitionComponent setMax(String_ value) { 
          this.max = value;
          return this;
        }

        public String getMaxSimple() { 
          return this.max == null ? null : this.max.getValue();
        }

        public ElementDefinitionComponent setMaxSimple(String value) { 
            if (this.max == null)
              this.max = new String_();
            this.max.setValue(value);
          return this;
        }

        public List<TypeRefComponent> getType() { 
          return this.type;
        }

    // syntactic sugar
        public TypeRefComponent addType() { 
          TypeRefComponent t = new TypeRefComponent();
          this.type.add(t);
          return t;
        }

        public String_ getNameReference() { 
          return this.nameReference;
        }

        public ElementDefinitionComponent setNameReference(String_ value) { 
          this.nameReference = value;
          return this;
        }

        public String getNameReferenceSimple() { 
          return this.nameReference == null ? null : this.nameReference.getValue();
        }

        public ElementDefinitionComponent setNameReferenceSimple(String value) { 
          if (value == null)
            this.nameReference = null;
          else {
            if (this.nameReference == null)
              this.nameReference = new String_();
            this.nameReference.setValue(value);
          }
          return this;
        }

        public org.hl7.fhir.instance.model.Type getValue() { 
          return this.value;
        }

        public ElementDefinitionComponent setValue(org.hl7.fhir.instance.model.Type value) { 
          this.value = value;
          return this;
        }

        public org.hl7.fhir.instance.model.Type getExample() { 
          return this.example;
        }

        public ElementDefinitionComponent setExample(org.hl7.fhir.instance.model.Type value) { 
          this.example = value;
          return this;
        }

        public Integer getMaxLength() { 
          return this.maxLength;
        }

        public ElementDefinitionComponent setMaxLength(Integer value) { 
          this.maxLength = value;
          return this;
        }

        public int getMaxLengthSimple() { 
          return this.maxLength == null ? null : this.maxLength.getValue();
        }

        public ElementDefinitionComponent setMaxLengthSimple(int value) { 
          if (value == -1)
            this.maxLength = null;
          else {
            if (this.maxLength == null)
              this.maxLength = new Integer();
            this.maxLength.setValue(value);
          }
          return this;
        }

        public List<Id> getCondition() { 
          return this.condition;
        }

    // syntactic sugar
        public Id addCondition() { 
          Id t = new Id();
          this.condition.add(t);
          return t;
        }

        public Id addConditionSimple(String value) { 
          Id t = new Id();
          t.setValue(value);
          this.condition.add(t);
          return t;
        }

        public List<ElementDefinitionConstraintComponent> getConstraint() { 
          return this.constraint;
        }

    // syntactic sugar
        public ElementDefinitionConstraintComponent addConstraint() { 
          ElementDefinitionConstraintComponent t = new ElementDefinitionConstraintComponent();
          this.constraint.add(t);
          return t;
        }

        public Boolean getMustSupport() { 
          return this.mustSupport;
        }

        public ElementDefinitionComponent setMustSupport(Boolean value) { 
          this.mustSupport = value;
          return this;
        }

        public boolean getMustSupportSimple() { 
          return this.mustSupport == null ? null : this.mustSupport.getValue();
        }

        public ElementDefinitionComponent setMustSupportSimple(boolean value) { 
          if (value == false)
            this.mustSupport = null;
          else {
            if (this.mustSupport == null)
              this.mustSupport = new Boolean();
            this.mustSupport.setValue(value);
          }
          return this;
        }

        public Boolean getIsModifier() { 
          return this.isModifier;
        }

        public ElementDefinitionComponent setIsModifier(Boolean value) { 
          this.isModifier = value;
          return this;
        }

        public boolean getIsModifierSimple() { 
          return this.isModifier == null ? null : this.isModifier.getValue();
        }

        public ElementDefinitionComponent setIsModifierSimple(boolean value) { 
            if (this.isModifier == null)
              this.isModifier = new Boolean();
            this.isModifier.setValue(value);
          return this;
        }

        public ElementDefinitionBindingComponent getBinding() { 
          return this.binding;
        }

        public ElementDefinitionComponent setBinding(ElementDefinitionBindingComponent value) { 
          this.binding = value;
          return this;
        }

        public List<ElementDefinitionMappingComponent> getMapping() { 
          return this.mapping;
        }

    // syntactic sugar
        public ElementDefinitionMappingComponent addMapping() { 
          ElementDefinitionMappingComponent t = new ElementDefinitionMappingComponent();
          this.mapping.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("short", "string", "A concise definition that  is shown in the concise XML format that summarizes profiles.", 0, java.lang.Integer.MAX_VALUE, short_));
          childrenList.add(new Property("formal", "string", "The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.", 0, java.lang.Integer.MAX_VALUE, formal));
          childrenList.add(new Property("comments", "string", "Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.", 0, java.lang.Integer.MAX_VALUE, comments));
          childrenList.add(new Property("requirements", "string", "Explains why this element is needed and why it's been constrained as it has.", 0, java.lang.Integer.MAX_VALUE, requirements));
          childrenList.add(new Property("synonym", "string", "Identifies additional names by which this element might also be known.", 0, java.lang.Integer.MAX_VALUE, synonym));
          childrenList.add(new Property("min", "integer", "The minimum number of times this element SHALL appear in the instance.", 0, java.lang.Integer.MAX_VALUE, min));
          childrenList.add(new Property("max", "string", "The maximum number of times this element is permitted to appear in the instance.", 0, java.lang.Integer.MAX_VALUE, max));
          childrenList.add(new Property("type", "", "The data type or resource that the value of this element is permitted to be.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("nameReference", "string", "Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.", 0, java.lang.Integer.MAX_VALUE, nameReference));
          childrenList.add(new Property("value[x]", "*", "Specifies a value that SHALL hold for this element in the instance.", 0, java.lang.Integer.MAX_VALUE, value));
          childrenList.add(new Property("example[x]", "*", "An example value for this element.", 0, java.lang.Integer.MAX_VALUE, example));
          childrenList.add(new Property("maxLength", "integer", "Indicates the shortest length that SHALL be supported by conformant instances without truncation.", 0, java.lang.Integer.MAX_VALUE, maxLength));
          childrenList.add(new Property("condition", "id", "A reference to an invariant that may make additional statements about the cardinality in the instance.", 0, java.lang.Integer.MAX_VALUE, condition));
          childrenList.add(new Property("constraint", "", "Formal constraints such as co-occurrence and other constraints that can be computationally evaluated within the context of the instance.", 0, java.lang.Integer.MAX_VALUE, constraint));
          childrenList.add(new Property("mustSupport", "boolean", "If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.", 0, java.lang.Integer.MAX_VALUE, mustSupport));
          childrenList.add(new Property("isModifier", "boolean", "If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.", 0, java.lang.Integer.MAX_VALUE, isModifier));
          childrenList.add(new Property("binding", "", "Binds to a value set if this element is coded (code, Coding, CodeableConcept).", 0, java.lang.Integer.MAX_VALUE, binding));
          childrenList.add(new Property("mapping", "", "Identifies a concept from an external specification that roughly corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, mapping));
        }

      public ElementDefinitionComponent copy(Profile e) {
        ElementDefinitionComponent dst = new ElementDefinitionComponent();
        dst.short_ = short_ == null ? null : short_.copy();
        dst.formal = formal == null ? null : formal.copy();
        dst.comments = comments == null ? null : comments.copy();
        dst.requirements = requirements == null ? null : requirements.copy();
        dst.synonym = new ArrayList<String_>();
        for (String_ i : synonym)
          dst.synonym.add(i.copy());
        dst.min = min == null ? null : min.copy();
        dst.max = max == null ? null : max.copy();
        dst.type = new ArrayList<TypeRefComponent>();
        for (TypeRefComponent i : type)
          dst.type.add(i.copy(e));
        dst.nameReference = nameReference == null ? null : nameReference.copy();
        dst.value = value == null ? null : value.copy();
        dst.example = example == null ? null : example.copy();
        dst.maxLength = maxLength == null ? null : maxLength.copy();
        dst.condition = new ArrayList<Id>();
        for (Id i : condition)
          dst.condition.add(i.copy());
        dst.constraint = new ArrayList<ElementDefinitionConstraintComponent>();
        for (ElementDefinitionConstraintComponent i : constraint)
          dst.constraint.add(i.copy(e));
        dst.mustSupport = mustSupport == null ? null : mustSupport.copy();
        dst.isModifier = isModifier == null ? null : isModifier.copy();
        dst.binding = binding == null ? null : binding.copy(e);
        dst.mapping = new ArrayList<ElementDefinitionMappingComponent>();
        for (ElementDefinitionMappingComponent i : mapping)
          dst.mapping.add(i.copy(e));
        return dst;
      }

  }

    public static class TypeRefComponent extends BackboneElement {
        /**
         * Name of Data type or Resource.
         */
        protected Code code;

        /**
         * Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.
         */
        protected Uri profile;

        /**
         * If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.
         */
        protected List<Enumeration<ResourceAggregationMode>> aggregation = new ArrayList<Enumeration<ResourceAggregationMode>>();

      public TypeRefComponent() {
        super();
      }

      public TypeRefComponent(Code code) {
        super();
        this.code = code;
      }

        public Code getCode() { 
          return this.code;
        }

        public TypeRefComponent setCode(Code value) { 
          this.code = value;
          return this;
        }

        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public TypeRefComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          return this;
        }

        public Uri getProfile() { 
          return this.profile;
        }

        public TypeRefComponent setProfile(Uri value) { 
          this.profile = value;
          return this;
        }

        public String getProfileSimple() { 
          return this.profile == null ? null : this.profile.getValue();
        }

        public TypeRefComponent setProfileSimple(String value) { 
          if (value == null)
            this.profile = null;
          else {
            if (this.profile == null)
              this.profile = new Uri();
            this.profile.setValue(value);
          }
          return this;
        }

        public List<Enumeration<ResourceAggregationMode>> getAggregation() { 
          return this.aggregation;
        }

    // syntactic sugar
        public Enumeration<ResourceAggregationMode> addAggregation() { 
          Enumeration<ResourceAggregationMode> t = new Enumeration<ResourceAggregationMode>();
          this.aggregation.add(t);
          return t;
        }

        public Enumeration<ResourceAggregationMode> addAggregationSimple(ResourceAggregationMode value) { 
          Enumeration<ResourceAggregationMode> t = new Enumeration<ResourceAggregationMode>();
          t.setValue(value);
          this.aggregation.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Name of Data type or Resource.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("profile", "uri", "Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.", 0, java.lang.Integer.MAX_VALUE, profile));
          childrenList.add(new Property("aggregation", "code", "If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.", 0, java.lang.Integer.MAX_VALUE, aggregation));
        }

      public TypeRefComponent copy(Profile e) {
        TypeRefComponent dst = new TypeRefComponent();
        dst.code = code == null ? null : code.copy();
        dst.profile = profile == null ? null : profile.copy();
        dst.aggregation = new ArrayList<Enumeration<ResourceAggregationMode>>();
        for (Enumeration<ResourceAggregationMode> i : aggregation)
          dst.aggregation.add(i.copy());
        return dst;
      }

  }

    public static class ElementDefinitionConstraintComponent extends BackboneElement {
        /**
         * Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.
         */
        protected Id key;

        /**
         * Used to label the constraint in OCL or in short displays incapable of displaying the full human description.
         */
        protected String_ name;

        /**
         * Identifies the impact constraint violation has on the conformance of the instance.
         */
        protected Enumeration<ConstraintSeverity> severity;

        /**
         * Text that can be used to describe the constraint in messages identifying that the constraint has been violated.
         */
        protected String_ human;

        /**
         * XPath expression of constraint.
         */
        protected String_ xpath;

      public ElementDefinitionConstraintComponent() {
        super();
      }

      public ElementDefinitionConstraintComponent(Id key, Enumeration<ConstraintSeverity> severity, String_ human, String_ xpath) {
        super();
        this.key = key;
        this.severity = severity;
        this.human = human;
        this.xpath = xpath;
      }

        public Id getKey() { 
          return this.key;
        }

        public ElementDefinitionConstraintComponent setKey(Id value) { 
          this.key = value;
          return this;
        }

        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        public ElementDefinitionConstraintComponent setKeySimple(String value) { 
            if (this.key == null)
              this.key = new Id();
            this.key.setValue(value);
          return this;
        }

        public String_ getName() { 
          return this.name;
        }

        public ElementDefinitionConstraintComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public ElementDefinitionConstraintComponent setNameSimple(String value) { 
          if (value == null)
            this.name = null;
          else {
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          }
          return this;
        }

        public Enumeration<ConstraintSeverity> getSeverity() { 
          return this.severity;
        }

        public ElementDefinitionConstraintComponent setSeverity(Enumeration<ConstraintSeverity> value) { 
          this.severity = value;
          return this;
        }

        public ConstraintSeverity getSeveritySimple() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        public ElementDefinitionConstraintComponent setSeveritySimple(ConstraintSeverity value) { 
            if (this.severity == null)
              this.severity = new Enumeration<ConstraintSeverity>();
            this.severity.setValue(value);
          return this;
        }

        public String_ getHuman() { 
          return this.human;
        }

        public ElementDefinitionConstraintComponent setHuman(String_ value) { 
          this.human = value;
          return this;
        }

        public String getHumanSimple() { 
          return this.human == null ? null : this.human.getValue();
        }

        public ElementDefinitionConstraintComponent setHumanSimple(String value) { 
            if (this.human == null)
              this.human = new String_();
            this.human.setValue(value);
          return this;
        }

        public String_ getXpath() { 
          return this.xpath;
        }

        public ElementDefinitionConstraintComponent setXpath(String_ value) { 
          this.xpath = value;
          return this;
        }

        public String getXpathSimple() { 
          return this.xpath == null ? null : this.xpath.getValue();
        }

        public ElementDefinitionConstraintComponent setXpathSimple(String value) { 
            if (this.xpath == null)
              this.xpath = new String_();
            this.xpath.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("key", "id", "Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.", 0, java.lang.Integer.MAX_VALUE, key));
          childrenList.add(new Property("name", "string", "Used to label the constraint in OCL or in short displays incapable of displaying the full human description.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("severity", "code", "Identifies the impact constraint violation has on the conformance of the instance.", 0, java.lang.Integer.MAX_VALUE, severity));
          childrenList.add(new Property("human", "string", "Text that can be used to describe the constraint in messages identifying that the constraint has been violated.", 0, java.lang.Integer.MAX_VALUE, human));
          childrenList.add(new Property("xpath", "string", "XPath expression of constraint.", 0, java.lang.Integer.MAX_VALUE, xpath));
        }

      public ElementDefinitionConstraintComponent copy(Profile e) {
        ElementDefinitionConstraintComponent dst = new ElementDefinitionConstraintComponent();
        dst.key = key == null ? null : key.copy();
        dst.name = name == null ? null : name.copy();
        dst.severity = severity == null ? null : severity.copy();
        dst.human = human == null ? null : human.copy();
        dst.xpath = xpath == null ? null : xpath.copy();
        return dst;
      }

  }

    public static class ElementDefinitionBindingComponent extends BackboneElement {
        /**
         * A descriptive name for this - can be useful for generating implementation artifacts.
         */
        protected String_ name;

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
         * Points to the value set or external definition that identifies the set of codes to be used.
         */
        protected Type reference;

      public ElementDefinitionBindingComponent() {
        super();
      }

      public ElementDefinitionBindingComponent(String_ name, Boolean isExtensible) {
        super();
        this.name = name;
        this.isExtensible = isExtensible;
      }

        public String_ getName() { 
          return this.name;
        }

        public ElementDefinitionBindingComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        public ElementDefinitionBindingComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        public Boolean getIsExtensible() { 
          return this.isExtensible;
        }

        public ElementDefinitionBindingComponent setIsExtensible(Boolean value) { 
          this.isExtensible = value;
          return this;
        }

        public boolean getIsExtensibleSimple() { 
          return this.isExtensible == null ? null : this.isExtensible.getValue();
        }

        public ElementDefinitionBindingComponent setIsExtensibleSimple(boolean value) { 
            if (this.isExtensible == null)
              this.isExtensible = new Boolean();
            this.isExtensible.setValue(value);
          return this;
        }

        public Enumeration<BindingConformance> getConformance() { 
          return this.conformance;
        }

        public ElementDefinitionBindingComponent setConformance(Enumeration<BindingConformance> value) { 
          this.conformance = value;
          return this;
        }

        public BindingConformance getConformanceSimple() { 
          return this.conformance == null ? null : this.conformance.getValue();
        }

        public ElementDefinitionBindingComponent setConformanceSimple(BindingConformance value) { 
          if (value == null)
            this.conformance = null;
          else {
            if (this.conformance == null)
              this.conformance = new Enumeration<BindingConformance>();
            this.conformance.setValue(value);
          }
          return this;
        }

        public String_ getDescription() { 
          return this.description;
        }

        public ElementDefinitionBindingComponent setDescription(String_ value) { 
          this.description = value;
          return this;
        }

        public String getDescriptionSimple() { 
          return this.description == null ? null : this.description.getValue();
        }

        public ElementDefinitionBindingComponent setDescriptionSimple(String value) { 
          if (value == null)
            this.description = null;
          else {
            if (this.description == null)
              this.description = new String_();
            this.description.setValue(value);
          }
          return this;
        }

        public Type getReference() { 
          return this.reference;
        }

        public ElementDefinitionBindingComponent setReference(Type value) { 
          this.reference = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "A descriptive name for this - can be useful for generating implementation artifacts.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("isExtensible", "boolean", "If true, then conformant systems may use additional codes or (where the data type permits) text alone to convey concepts not covered by the set of codes identified in the binding.  If false, then conformant systems are constrained to the provided codes alone.", 0, java.lang.Integer.MAX_VALUE, isExtensible));
          childrenList.add(new Property("conformance", "code", "Indicates the degree of conformance expectations associated with this binding.", 0, java.lang.Integer.MAX_VALUE, conformance));
          childrenList.add(new Property("description", "string", "Describes the intended use of this particular set of codes.", 0, java.lang.Integer.MAX_VALUE, description));
          childrenList.add(new Property("reference[x]", "uri|Resource(ValueSet)", "Points to the value set or external definition that identifies the set of codes to be used.", 0, java.lang.Integer.MAX_VALUE, reference));
        }

      public ElementDefinitionBindingComponent copy(Profile e) {
        ElementDefinitionBindingComponent dst = new ElementDefinitionBindingComponent();
        dst.name = name == null ? null : name.copy();
        dst.isExtensible = isExtensible == null ? null : isExtensible.copy();
        dst.conformance = conformance == null ? null : conformance.copy();
        dst.description = description == null ? null : description.copy();
        dst.reference = reference == null ? null : reference.copy();
        return dst;
      }

  }

    public static class ElementDefinitionMappingComponent extends BackboneElement {
        /**
         * A URI that identifies the specification that this mapping is expressed to.
         */
        protected Uri target;

        /**
         * Expresses what part of the target specification corresponds to this element.
         */
        protected String_ map;

      public ElementDefinitionMappingComponent() {
        super();
      }

      public ElementDefinitionMappingComponent(Uri target) {
        super();
        this.target = target;
      }

        public Uri getTarget() { 
          return this.target;
        }

        public ElementDefinitionMappingComponent setTarget(Uri value) { 
          this.target = value;
          return this;
        }

        public String getTargetSimple() { 
          return this.target == null ? null : this.target.getValue();
        }

        public ElementDefinitionMappingComponent setTargetSimple(String value) { 
            if (this.target == null)
              this.target = new Uri();
            this.target.setValue(value);
          return this;
        }

        public String_ getMap() { 
          return this.map;
        }

        public ElementDefinitionMappingComponent setMap(String_ value) { 
          this.map = value;
          return this;
        }

        public String getMapSimple() { 
          return this.map == null ? null : this.map.getValue();
        }

        public ElementDefinitionMappingComponent setMapSimple(String value) { 
          if (value == null)
            this.map = null;
          else {
            if (this.map == null)
              this.map = new String_();
            this.map.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("target", "uri", "A URI that identifies the specification that this mapping is expressed to.", 0, java.lang.Integer.MAX_VALUE, target));
          childrenList.add(new Property("map", "string", "Expresses what part of the target specification corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, map));
        }

      public ElementDefinitionMappingComponent copy(Profile e) {
        ElementDefinitionMappingComponent dst = new ElementDefinitionMappingComponent();
        dst.target = target == null ? null : target.copy();
        dst.map = map == null ? null : map.copy();
        return dst;
      }

  }

    public static class ProfileExtensionDefnComponent extends BackboneElement {
        /**
         * A unique code (within the profile) used to identify the extension.
         */
        protected Code code;

        /**
         * Defined so that applications can use this name when displaying the value of the extension to the user.
         */
        protected String_ display;

        /**
         * Identifies the type of context to which the extension applies.
         */
        protected Enumeration<ExtensionContext> contextType;

        /**
         * Identifies the types of resource or data type elements to which the extension can be applied.
         */
        protected List<String_> context = new ArrayList<String_>();

        /**
         * Definition of the extension and its content.
         */
        protected ElementDefinitionComponent definition;

      public ProfileExtensionDefnComponent() {
        super();
      }

      public ProfileExtensionDefnComponent(Code code, Enumeration<ExtensionContext> contextType, ElementDefinitionComponent definition) {
        super();
        this.code = code;
        this.contextType = contextType;
        this.definition = definition;
      }

        public Code getCode() { 
          return this.code;
        }

        public ProfileExtensionDefnComponent setCode(Code value) { 
          this.code = value;
          return this;
        }

        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        public ProfileExtensionDefnComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          return this;
        }

        public String_ getDisplay() { 
          return this.display;
        }

        public ProfileExtensionDefnComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        public ProfileExtensionDefnComponent setDisplaySimple(String value) { 
          if (value == null)
            this.display = null;
          else {
            if (this.display == null)
              this.display = new String_();
            this.display.setValue(value);
          }
          return this;
        }

        public Enumeration<ExtensionContext> getContextType() { 
          return this.contextType;
        }

        public ProfileExtensionDefnComponent setContextType(Enumeration<ExtensionContext> value) { 
          this.contextType = value;
          return this;
        }

        public ExtensionContext getContextTypeSimple() { 
          return this.contextType == null ? null : this.contextType.getValue();
        }

        public ProfileExtensionDefnComponent setContextTypeSimple(ExtensionContext value) { 
            if (this.contextType == null)
              this.contextType = new Enumeration<ExtensionContext>();
            this.contextType.setValue(value);
          return this;
        }

        public List<String_> getContext() { 
          return this.context;
        }

    // syntactic sugar
        public String_ addContext() { 
          String_ t = new String_();
          this.context.add(t);
          return t;
        }

        public String_ addContextSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.context.add(t);
          return t;
        }

        public ElementDefinitionComponent getDefinition() { 
          return this.definition;
        }

        public ProfileExtensionDefnComponent setDefinition(ElementDefinitionComponent value) { 
          this.definition = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "A unique code (within the profile) used to identify the extension.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("display", "string", "Defined so that applications can use this name when displaying the value of the extension to the user.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("contextType", "code", "Identifies the type of context to which the extension applies.", 0, java.lang.Integer.MAX_VALUE, contextType));
          childrenList.add(new Property("context", "string", "Identifies the types of resource or data type elements to which the extension can be applied.", 0, java.lang.Integer.MAX_VALUE, context));
          childrenList.add(new Property("definition", "@Profile.structure.element.definition", "Definition of the extension and its content.", 0, java.lang.Integer.MAX_VALUE, definition));
        }

      public ProfileExtensionDefnComponent copy(Profile e) {
        ProfileExtensionDefnComponent dst = new ProfileExtensionDefnComponent();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.contextType = contextType == null ? null : contextType.copy();
        dst.context = new ArrayList<String_>();
        for (String_ i : context)
          dst.context.add(i.copy());
        dst.definition = definition == null ? null : definition.copy(e);
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this profile when it is referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI).
     */
    protected String_ identifier;

    /**
     * The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected String_ version;

    /**
     * A free text natural language name identifying the Profile.
     */
    protected String_ name;

    /**
     * Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    protected String_ publisher;

    /**
     * Contact details to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * A free text natural language description of the profile and its use.
     */
    protected String_ description;

    /**
     * A set of terms from external terminologies that may be used to assist with indexing and searching of templates.
     */
    protected List<Coding> code = new ArrayList<Coding>();

    /**
     * The status of the profile.
     */
    protected Enumeration<ResourceProfileStatus> status;

    /**
     * This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    protected Boolean experimental;

    /**
     * The date that this version of the profile was published.
     */
    protected DateTime date;

    /**
     * The version of the FHIR specification on which this profile is based.
     */
    protected Id fhirVersion;

    /**
     * A constraint statement about what contents a resource or data type may have.
     */
    protected List<ProfileStructureComponent> structure = new ArrayList<ProfileStructureComponent>();

    /**
     * An extension defined as part of the profile.
     */
    protected List<ProfileExtensionDefnComponent> extensionDefn = new ArrayList<ProfileExtensionDefnComponent>();

    public Profile() {
      super();
    }

    public Profile(String_ name, Enumeration<ResourceProfileStatus> status) {
      super();
      this.name = name;
      this.status = status;
    }

    public String_ getIdentifier() { 
      return this.identifier;
    }

    public Profile setIdentifier(String_ value) { 
      this.identifier = value;
      return this;
    }

    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    public Profile setIdentifierSimple(String value) { 
      if (value == null)
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new String_();
        this.identifier.setValue(value);
      }
      return this;
    }

    public String_ getVersion() { 
      return this.version;
    }

    public Profile setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    public Profile setVersionSimple(String value) { 
      if (value == null)
        this.version = null;
      else {
        if (this.version == null)
          this.version = new String_();
        this.version.setValue(value);
      }
      return this;
    }

    public String_ getName() { 
      return this.name;
    }

    public Profile setName(String_ value) { 
      this.name = value;
      return this;
    }

    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    public Profile setNameSimple(String value) { 
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      return this;
    }

    public String_ getPublisher() { 
      return this.publisher;
    }

    public Profile setPublisher(String_ value) { 
      this.publisher = value;
      return this;
    }

    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    public Profile setPublisherSimple(String value) { 
      if (value == null)
        this.publisher = null;
      else {
        if (this.publisher == null)
          this.publisher = new String_();
        this.publisher.setValue(value);
      }
      return this;
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

    public Profile setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    public Profile setDescriptionSimple(String value) { 
      if (value == null)
        this.description = null;
      else {
        if (this.description == null)
          this.description = new String_();
        this.description.setValue(value);
      }
      return this;
    }

    public List<Coding> getCode() { 
      return this.code;
    }

    // syntactic sugar
    public Coding addCode() { 
      Coding t = new Coding();
      this.code.add(t);
      return t;
    }

    public Enumeration<ResourceProfileStatus> getStatus() { 
      return this.status;
    }

    public Profile setStatus(Enumeration<ResourceProfileStatus> value) { 
      this.status = value;
      return this;
    }

    public ResourceProfileStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public Profile setStatusSimple(ResourceProfileStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceProfileStatus>();
        this.status.setValue(value);
      return this;
    }

    public Boolean getExperimental() { 
      return this.experimental;
    }

    public Profile setExperimental(Boolean value) { 
      this.experimental = value;
      return this;
    }

    public boolean getExperimentalSimple() { 
      return this.experimental == null ? null : this.experimental.getValue();
    }

    public Profile setExperimentalSimple(boolean value) { 
      if (value == false)
        this.experimental = null;
      else {
        if (this.experimental == null)
          this.experimental = new Boolean();
        this.experimental.setValue(value);
      }
      return this;
    }

    public DateTime getDate() { 
      return this.date;
    }

    public Profile setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    public String getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    public Profile setDateSimple(String value) { 
      if (value == null)
        this.date = null;
      else {
        if (this.date == null)
          this.date = new DateTime();
        this.date.setValue(value);
      }
      return this;
    }

    public Id getFhirVersion() { 
      return this.fhirVersion;
    }

    public Profile setFhirVersion(Id value) { 
      this.fhirVersion = value;
      return this;
    }

    public String getFhirVersionSimple() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    public Profile setFhirVersionSimple(String value) { 
      if (value == null)
        this.fhirVersion = null;
      else {
        if (this.fhirVersion == null)
          this.fhirVersion = new Id();
        this.fhirVersion.setValue(value);
      }
      return this;
    }

    public List<ProfileStructureComponent> getStructure() { 
      return this.structure;
    }

    // syntactic sugar
    public ProfileStructureComponent addStructure() { 
      ProfileStructureComponent t = new ProfileStructureComponent();
      this.structure.add(t);
      return t;
    }

    public List<ProfileExtensionDefnComponent> getExtensionDefn() { 
      return this.extensionDefn;
    }

    // syntactic sugar
    public ProfileExtensionDefnComponent addExtensionDefn() { 
      ProfileExtensionDefnComponent t = new ProfileExtensionDefnComponent();
      this.extensionDefn.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "string", "The identifier that is used to identify this profile when it is referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name identifying the Profile.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the profile.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the profile and its use.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("code", "Coding", "A set of terms from external terminologies that may be used to assist with indexing and searching of templates.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("status", "code", "The status of the profile.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the profile was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("fhirVersion", "id", "The version of the FHIR specification on which this profile is based.", 0, java.lang.Integer.MAX_VALUE, fhirVersion));
        childrenList.add(new Property("structure", "", "A constraint statement about what contents a resource or data type may have.", 0, java.lang.Integer.MAX_VALUE, structure));
        childrenList.add(new Property("extensionDefn", "", "An extension defined as part of the profile.", 0, java.lang.Integer.MAX_VALUE, extensionDefn));
      }

      public Profile copy() {
        Profile dst = new Profile();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.description = description == null ? null : description.copy();
        dst.code = new ArrayList<Coding>();
        for (Coding i : code)
          dst.code.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.experimental = experimental == null ? null : experimental.copy();
        dst.date = date == null ? null : date.copy();
        dst.fhirVersion = fhirVersion == null ? null : fhirVersion.copy();
        dst.structure = new ArrayList<ProfileStructureComponent>();
        for (ProfileStructureComponent i : structure)
          dst.structure.add(i.copy(dst));
        dst.extensionDefn = new ArrayList<ProfileExtensionDefnComponent>();
        for (ProfileExtensionDefnComponent i : extensionDefn)
          dst.extensionDefn.add(i.copy(dst));
        return dst;
      }

      protected Profile typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Profile;
   }


}

