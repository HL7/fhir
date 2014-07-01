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

    public enum PropertyRepresentation {
        xmlAttr, // In XML, this property is represented as an attribute not an element.
        Null; // added to help the parsers
        public static PropertyRepresentation fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("xmlAttr".equals(codeString))
          return xmlAttr;
        throw new Exception("Unknown PropertyRepresentation code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case xmlAttr: return "xmlAttr";
            default: return "?";
          }
        }
    }

  public static class PropertyRepresentationEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("xmlAttr".equals(codeString))
          return PropertyRepresentation.xmlAttr;
        throw new Exception("Unknown PropertyRepresentation code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == PropertyRepresentation.xmlAttr)
        return "xmlAttr";
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

    public enum SearchParamType {
        number, // Search parameter SHALL be a number (a whole number, or a decimal).
        date, // Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.
        string, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.
        token, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "|", depending on the modifier used.
        reference, // A reference to another resource.
        composite, // A composite search parameter that combines a search on two values together.
        quantity, // A search parameter that searches on a quantity.
        Null; // added to help the parsers
        public static SearchParamType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return number;
        if ("date".equals(codeString))
          return date;
        if ("string".equals(codeString))
          return string;
        if ("token".equals(codeString))
          return token;
        if ("reference".equals(codeString))
          return reference;
        if ("composite".equals(codeString))
          return composite;
        if ("quantity".equals(codeString))
          return quantity;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case number: return "number";
            case date: return "date";
            case string: return "string";
            case token: return "token";
            case reference: return "reference";
            case composite: return "composite";
            case quantity: return "quantity";
            default: return "?";
          }
        }
    }

  public static class SearchParamTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return SearchParamType.number;
        if ("date".equals(codeString))
          return SearchParamType.date;
        if ("string".equals(codeString))
          return SearchParamType.string;
        if ("token".equals(codeString))
          return SearchParamType.token;
        if ("reference".equals(codeString))
          return SearchParamType.reference;
        if ("composite".equals(codeString))
          return SearchParamType.composite;
        if ("quantity".equals(codeString))
          return SearchParamType.quantity;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SearchParamType.number)
        return "number";
      if (code == SearchParamType.date)
        return "date";
      if (code == SearchParamType.string)
        return "string";
      if (code == SearchParamType.token)
        return "token";
      if (code == SearchParamType.reference)
        return "reference";
      if (code == SearchParamType.composite)
        return "composite";
      if (code == SearchParamType.quantity)
        return "quantity";
      return "?";
      }
    }

    public enum ExtensionContext {
        resource, // The context is all elements matching a particular resource element path.
        datatype, // The context is all nodes matching a particular data type element path (root or repeating element) or all elements referencing a particular primitive data type (expressed as the datatype name).
        mapping, // The context is all nodes whose mapping to a specified reference model corresponds to a particular mapping structure.  The context identifies the mapping target. The mapping should clearly identify where such an extension could be used.
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

    public static class ProfileMappingComponent extends BackboneElement {
        /**
         * An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        protected Id identity;

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

        private static final long serialVersionUID = 514246768L;

      public ProfileMappingComponent() {
        super();
      }

      public ProfileMappingComponent(Id identity) {
        super();
        this.identity = identity;
      }

        /**
         * @return {@link #identity} (An Internal id that is used to identify this mapping set when specific mappings are made.)
         */
        public Id getIdentity() { 
          return this.identity;
        }

        /**
         * @param value {@link #identity} (An Internal id that is used to identify this mapping set when specific mappings are made.)
         */
        public ProfileMappingComponent setIdentity(Id value) { 
          this.identity = value;
          return this;
        }

        /**
         * @return An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        public String getIdentitySimple() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        /**
         * @param value An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        public ProfileMappingComponent setIdentitySimple(String value) { 
            if (this.identity == null)
              this.identity = new Id();
            this.identity.setValue(value);
          return this;
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
        public ProfileMappingComponent setUri(Uri value) { 
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
        public ProfileMappingComponent setUriSimple(String value) { 
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
        public ProfileMappingComponent setName(String_ value) { 
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
        public ProfileMappingComponent setNameSimple(String value) { 
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
        public ProfileMappingComponent setComments(String_ value) { 
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
        public ProfileMappingComponent setCommentsSimple(String value) { 
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
          childrenList.add(new Property("identity", "id", "An Internal id that is used to identify this mapping set when specific mappings are made.", 0, java.lang.Integer.MAX_VALUE, identity));
          childrenList.add(new Property("uri", "uri", "A URI that identifies the specification that this mapping is expressed to.", 0, java.lang.Integer.MAX_VALUE, uri));
          childrenList.add(new Property("name", "string", "A name for the specification that is being mapped to.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("comments", "string", "Comments about this mapping, including version notes, issues, scope limitations, and other important notes for usage.", 0, java.lang.Integer.MAX_VALUE, comments));
        }

      public ProfileMappingComponent copy() {
        ProfileMappingComponent dst = new ProfileMappingComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.uri = uri == null ? null : uri.copy();
        dst.name = name == null ? null : name.copy();
        dst.comments = comments == null ? null : comments.copy();
        return dst;
      }

  }

    public static class ProfileStructureComponent extends BackboneElement {
        /**
         * The Resource or Data type being described.
         */
        protected Code type;

        /**
         * The structure that is the base on which this set of constraints is derived from.
         */
        protected Uri base;

        /**
         * The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).
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
         * A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.
         */
        protected ConstraintComponent snapshot;

        /**
         * A differential view is expressed relative to the base profile - a statement of differences that it applies.
         */
        protected ConstraintComponent differential;

        /**
         * Additional search parameters for implementations to support and/or make use of.
         */
        protected List<ProfileStructureSearchParamComponent> searchParam = new ArrayList<ProfileStructureSearchParamComponent>();

        private static final long serialVersionUID = -911790602L;

      public ProfileStructureComponent() {
        super();
      }

      public ProfileStructureComponent(Code type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (The Resource or Data type being described.)
         */
        public Code getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The Resource or Data type being described.)
         */
        public ProfileStructureComponent setType(Code value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The Resource or Data type being described.
         */
        public String getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The Resource or Data type being described.
         */
        public ProfileStructureComponent setTypeSimple(String value) { 
            if (this.type == null)
              this.type = new Code();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #base} (The structure that is the base on which this set of constraints is derived from.)
         */
        public Uri getBase() { 
          return this.base;
        }

        /**
         * @param value {@link #base} (The structure that is the base on which this set of constraints is derived from.)
         */
        public ProfileStructureComponent setBase(Uri value) { 
          this.base = value;
          return this;
        }

        /**
         * @return The structure that is the base on which this set of constraints is derived from.
         */
        public String getBaseSimple() { 
          return this.base == null ? null : this.base.getValue();
        }

        /**
         * @param value The structure that is the base on which this set of constraints is derived from.
         */
        public ProfileStructureComponent setBaseSimple(String value) { 
          if (value == null)
            this.base = null;
          else {
            if (this.base == null)
              this.base = new Uri();
            this.base.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #name} (The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).)
         */
        public ProfileStructureComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).
         */
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

        /**
         * @return {@link #publish} (This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.)
         */
        public Boolean getPublish() { 
          return this.publish;
        }

        /**
         * @param value {@link #publish} (This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.)
         */
        public ProfileStructureComponent setPublish(Boolean value) { 
          this.publish = value;
          return this;
        }

        /**
         * @return This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.
         */
        public boolean getPublishSimple() { 
          return this.publish == null ? false : this.publish.getValue();
        }

        /**
         * @param value This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.
         */
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

        /**
         * @return {@link #purpose} (Human summary: why describe this resource?.)
         */
        public String_ getPurpose() { 
          return this.purpose;
        }

        /**
         * @param value {@link #purpose} (Human summary: why describe this resource?.)
         */
        public ProfileStructureComponent setPurpose(String_ value) { 
          this.purpose = value;
          return this;
        }

        /**
         * @return Human summary: why describe this resource?.
         */
        public String getPurposeSimple() { 
          return this.purpose == null ? null : this.purpose.getValue();
        }

        /**
         * @param value Human summary: why describe this resource?.
         */
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

        /**
         * @return {@link #snapshot} (A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.)
         */
        public ConstraintComponent getSnapshot() { 
          return this.snapshot;
        }

        /**
         * @param value {@link #snapshot} (A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.)
         */
        public ProfileStructureComponent setSnapshot(ConstraintComponent value) { 
          this.snapshot = value;
          return this;
        }

        /**
         * @return {@link #differential} (A differential view is expressed relative to the base profile - a statement of differences that it applies.)
         */
        public ConstraintComponent getDifferential() { 
          return this.differential;
        }

        /**
         * @param value {@link #differential} (A differential view is expressed relative to the base profile - a statement of differences that it applies.)
         */
        public ProfileStructureComponent setDifferential(ConstraintComponent value) { 
          this.differential = value;
          return this;
        }

        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
        public List<ProfileStructureSearchParamComponent> getSearchParam() { 
          return this.searchParam;
        }

    // syntactic sugar
        /**
         * @return {@link #searchParam} (Additional search parameters for implementations to support and/or make use of.)
         */
        public ProfileStructureSearchParamComponent addSearchParam() { 
          ProfileStructureSearchParamComponent t = new ProfileStructureSearchParamComponent();
          this.searchParam.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "The Resource or Data type being described.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("base", "uri", "The structure that is the base on which this set of constraints is derived from.", 0, java.lang.Integer.MAX_VALUE, base));
          childrenList.add(new Property("name", "id", "The name of this resource constraint statement (to refer to it from other resource constraints - from Profile.structure.snapshot.element.definition.type.profile).", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("publish", "boolean", "This definition of a profile on a structure is published as a formal statement. Some structural definitions might be defined purely for internal use within the profile, and not intended to be used outside that context.", 0, java.lang.Integer.MAX_VALUE, publish));
          childrenList.add(new Property("purpose", "string", "Human summary: why describe this resource?.", 0, java.lang.Integer.MAX_VALUE, purpose));
          childrenList.add(new Property("snapshot", "", "A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.", 0, java.lang.Integer.MAX_VALUE, snapshot));
          childrenList.add(new Property("differential", "@Profile.structure.snapshot", "A differential view is expressed relative to the base profile - a statement of differences that it applies.", 0, java.lang.Integer.MAX_VALUE, differential));
          childrenList.add(new Property("searchParam", "", "Additional search parameters for implementations to support and/or make use of.", 0, java.lang.Integer.MAX_VALUE, searchParam));
        }

      public ProfileStructureComponent copy() {
        ProfileStructureComponent dst = new ProfileStructureComponent();
        dst.type = type == null ? null : type.copy();
        dst.base = base == null ? null : base.copy();
        dst.name = name == null ? null : name.copy();
        dst.publish = publish == null ? null : publish.copy();
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.snapshot = snapshot == null ? null : snapshot.copy();
        dst.differential = differential == null ? null : differential.copy();
        dst.searchParam = new ArrayList<ProfileStructureSearchParamComponent>();
        for (ProfileStructureSearchParamComponent i : searchParam)
          dst.searchParam.add(i.copy());
        return dst;
      }

  }

    public static class ConstraintComponent extends BackboneElement {
        /**
         * Captures constraints on each element within the resource.
         */
        protected List<ElementComponent> element = new ArrayList<ElementComponent>();

        private static final long serialVersionUID = -413892939L;

      public ConstraintComponent() {
        super();
      }

        /**
         * @return {@link #element} (Captures constraints on each element within the resource.)
         */
        public List<ElementComponent> getElement() { 
          return this.element;
        }

    // syntactic sugar
        /**
         * @return {@link #element} (Captures constraints on each element within the resource.)
         */
        public ElementComponent addElement() { 
          ElementComponent t = new ElementComponent();
          this.element.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("element", "", "Captures constraints on each element within the resource.", 0, java.lang.Integer.MAX_VALUE, element));
        }

      public ConstraintComponent copy() {
        ConstraintComponent dst = new ConstraintComponent();
        dst.element = new ArrayList<ElementComponent>();
        for (ElementComponent i : element)
          dst.element.add(i.copy());
        return dst;
      }

  }

    public static class ElementComponent extends BackboneElement {
        /**
         * The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.
         */
        protected String_ path;

        /**
         * Codes that define how this element is represented in instances, when the deviation varies from the normal case.
         */
        protected List<Enumeration<PropertyRepresentation>> representation = new ArrayList<Enumeration<PropertyRepresentation>>();

        /**
         * The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.
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

        private static final long serialVersionUID = 853411662L;

      public ElementComponent() {
        super();
      }

      public ElementComponent(String_ path) {
        super();
        this.path = path;
      }

        /**
         * @return {@link #path} (The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.)
         */
        public String_ getPath() { 
          return this.path;
        }

        /**
         * @param value {@link #path} (The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.)
         */
        public ElementComponent setPath(String_ value) { 
          this.path = value;
          return this;
        }

        /**
         * @return The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.
         */
        public String getPathSimple() { 
          return this.path == null ? null : this.path.getValue();
        }

        /**
         * @param value The path identifies the element and is expressed as a "."-separated list of ancestor elements, beginning with the name of the resource.
         */
        public ElementComponent setPathSimple(String value) { 
            if (this.path == null)
              this.path = new String_();
            this.path.setValue(value);
          return this;
        }

        /**
         * @return {@link #representation} (Codes that define how this element is represented in instances, when the deviation varies from the normal case.)
         */
        public List<Enumeration<PropertyRepresentation>> getRepresentation() { 
          return this.representation;
        }

    // syntactic sugar
        /**
         * @return {@link #representation} (Codes that define how this element is represented in instances, when the deviation varies from the normal case.)
         */
        public Enumeration<PropertyRepresentation> addRepresentation() { 
          Enumeration<PropertyRepresentation> t = new Enumeration<PropertyRepresentation>();
          this.representation.add(t);
          return t;
        }

        /**
         * @param value {@link #representation} (Codes that define how this element is represented in instances, when the deviation varies from the normal case.)
         */
        public Enumeration<PropertyRepresentation> addRepresentationSimple(PropertyRepresentation value) { 
          Enumeration<PropertyRepresentation> t = new Enumeration<PropertyRepresentation>();
          t.setValue(value);
          this.representation.add(t);
          return t;
        }

        /**
         * @param value {@link #representation} (Codes that define how this element is represented in instances, when the deviation varies from the normal case.)
         */
        public boolean hasRepresentationSimple(PropertyRepresentation value) { 
          for (Enumeration<PropertyRepresentation> v : this.representation)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #name} (The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.)
         */
        public ElementComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.
         */
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

        /**
         * @return {@link #slicing} (Indicates that the element is sliced into a set of alternative definitions (there are multiple definitions on a single element in the base resource). The set of slices is any elements that come after this in the element sequence that have the same path, until a shorter path occurs (the shorter path terminates the set).)
         */
        public ElementSlicingComponent getSlicing() { 
          return this.slicing;
        }

        /**
         * @param value {@link #slicing} (Indicates that the element is sliced into a set of alternative definitions (there are multiple definitions on a single element in the base resource). The set of slices is any elements that come after this in the element sequence that have the same path, until a shorter path occurs (the shorter path terminates the set).)
         */
        public ElementComponent setSlicing(ElementSlicingComponent value) { 
          this.slicing = value;
          return this;
        }

        /**
         * @return {@link #definition} (Definition of the content of the element to provide a more specific definition than that contained for the element in the base resource.)
         */
        public ElementDefinitionComponent getDefinition() { 
          return this.definition;
        }

        /**
         * @param value {@link #definition} (Definition of the content of the element to provide a more specific definition than that contained for the element in the base resource.)
         */
        public ElementComponent setDefinition(ElementDefinitionComponent value) { 
          this.definition = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("path", "string", "The path identifies the element and is expressed as a '.'-separated list of ancestor elements, beginning with the name of the resource.", 0, java.lang.Integer.MAX_VALUE, path));
          childrenList.add(new Property("representation", "code", "Codes that define how this element is represented in instances, when the deviation varies from the normal case.", 0, java.lang.Integer.MAX_VALUE, representation));
          childrenList.add(new Property("name", "string", "The name of this element definition (to refer to it from other element definitions using Profile.structure.snapshot.element.definition.nameReference). This is a unique name referring to a specific set of constraints applied to this element. One use of this is to provide a name to different slices of the same element.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("slicing", "", "Indicates that the element is sliced into a set of alternative definitions (there are multiple definitions on a single element in the base resource). The set of slices is any elements that come after this in the element sequence that have the same path, until a shorter path occurs (the shorter path terminates the set).", 0, java.lang.Integer.MAX_VALUE, slicing));
          childrenList.add(new Property("definition", "", "Definition of the content of the element to provide a more specific definition than that contained for the element in the base resource.", 0, java.lang.Integer.MAX_VALUE, definition));
        }

      public ElementComponent copy() {
        ElementComponent dst = new ElementComponent();
        dst.path = path == null ? null : path.copy();
        dst.representation = new ArrayList<Enumeration<PropertyRepresentation>>();
        for (Enumeration<PropertyRepresentation> i : representation)
          dst.representation.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.slicing = slicing == null ? null : slicing.copy();
        dst.definition = definition == null ? null : definition.copy();
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

        private static final long serialVersionUID = -1808715996L;

      public ElementSlicingComponent() {
        super();
      }

      public ElementSlicingComponent(Enumeration<ResourceSlicingRules> rules) {
        super();
        this.rules = rules;
      }

        /**
         * @return {@link #discriminator} (Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.)
         */
        public Id getDiscriminator() { 
          return this.discriminator;
        }

        /**
         * @param value {@link #discriminator} (Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.)
         */
        public ElementSlicingComponent setDiscriminator(Id value) { 
          this.discriminator = value;
          return this;
        }

        /**
         * @return Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.
         */
        public String getDiscriminatorSimple() { 
          return this.discriminator == null ? null : this.discriminator.getValue();
        }

        /**
         * @param value Designates which child element is used to discriminate between the slices when processing an instance. The value of the child element in the instance SHALL completely distinguish which slice the element in the resource matches based on the allowed values for that element in each of the slices.
         */
        public ElementSlicingComponent setDiscriminatorSimple(String value) { 
          if (value == null)
            this.discriminator = null;
          else {
            if (this.discriminator == null)
              this.discriminator = new Id();
            this.discriminator.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #ordered} (If the matching elements have to occur in the same order as defined in the profile.)
         */
        public Boolean getOrdered() { 
          return this.ordered;
        }

        /**
         * @param value {@link #ordered} (If the matching elements have to occur in the same order as defined in the profile.)
         */
        public ElementSlicingComponent setOrdered(Boolean value) { 
          this.ordered = value;
          return this;
        }

        /**
         * @return If the matching elements have to occur in the same order as defined in the profile.
         */
        public boolean getOrderedSimple() { 
          return this.ordered == null ? false : this.ordered.getValue();
        }

        /**
         * @param value If the matching elements have to occur in the same order as defined in the profile.
         */
        public ElementSlicingComponent setOrderedSimple(boolean value) { 
          if (value == false)
            this.ordered = null;
          else {
            if (this.ordered == null)
              this.ordered = new Boolean();
            this.ordered.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #rules} (Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.)
         */
        public Enumeration<ResourceSlicingRules> getRules() { 
          return this.rules;
        }

        /**
         * @param value {@link #rules} (Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.)
         */
        public ElementSlicingComponent setRules(Enumeration<ResourceSlicingRules> value) { 
          this.rules = value;
          return this;
        }

        /**
         * @return Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.
         */
        public ResourceSlicingRules getRulesSimple() { 
          return this.rules == null ? null : this.rules.getValue();
        }

        /**
         * @param value Whether additional slices are allowed or not. When the slices are ordered, profile authors can also say that additional slices are only allowed at the end.
         */
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

      public ElementSlicingComponent copy() {
        ElementSlicingComponent dst = new ElementSlicingComponent();
        dst.discriminator = discriminator == null ? null : discriminator.copy();
        dst.ordered = ordered == null ? null : ordered.copy();
        dst.rules = rules == null ? null : rules.copy();
        return dst;
      }

  }

    public static class ElementDefinitionComponent extends BackboneElement {
        /**
         * A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).
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
         * Specifies a primitive value that SHALL hold for this element in the instance.
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
         * A reference to an invariant that may make additional statements about the cardinality or value in the instance.
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

        private static final long serialVersionUID = 468958412L;

      public ElementDefinitionComponent() {
        super();
      }

        /**
         * @return {@link #short_} (A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).)
         */
        public String_ getShort() { 
          return this.short_;
        }

        /**
         * @param value {@link #short_} (A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).)
         */
        public ElementDefinitionComponent setShort(String_ value) { 
          this.short_ = value;
          return this;
        }

        /**
         * @return A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).
         */
        public String getShortSimple() { 
          return this.short_ == null ? null : this.short_.getValue();
        }

        /**
         * @param value A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).
         */
        public ElementDefinitionComponent setShortSimple(String value) { 
          if (value == null)
            this.short_ = null;
          else {
            if (this.short_ == null)
              this.short_ = new String_();
            this.short_.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #formal} (The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.)
         */
        public String_ getFormal() { 
          return this.formal;
        }

        /**
         * @param value {@link #formal} (The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.)
         */
        public ElementDefinitionComponent setFormal(String_ value) { 
          this.formal = value;
          return this;
        }

        /**
         * @return The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.
         */
        public String getFormalSimple() { 
          return this.formal == null ? null : this.formal.getValue();
        }

        /**
         * @param value The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.
         */
        public ElementDefinitionComponent setFormalSimple(String value) { 
          if (value == null)
            this.formal = null;
          else {
            if (this.formal == null)
              this.formal = new String_();
            this.formal.setValue(value);
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
        public ElementDefinitionComponent setComments(String_ value) { 
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

        /**
         * @return {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.)
         */
        public String_ getRequirements() { 
          return this.requirements;
        }

        /**
         * @param value {@link #requirements} (Explains why this element is needed and why it's been constrained as it has.)
         */
        public ElementDefinitionComponent setRequirements(String_ value) { 
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
         * @return {@link #min} (The minimum number of times this element SHALL appear in the instance.)
         */
        public Integer getMin() { 
          return this.min;
        }

        /**
         * @param value {@link #min} (The minimum number of times this element SHALL appear in the instance.)
         */
        public ElementDefinitionComponent setMin(Integer value) { 
          this.min = value;
          return this;
        }

        /**
         * @return The minimum number of times this element SHALL appear in the instance.
         */
        public int getMinSimple() { 
          return this.min == null ? null : this.min.getValue();
        }

        /**
         * @param value The minimum number of times this element SHALL appear in the instance.
         */
        public ElementDefinitionComponent setMinSimple(int value) { 
          if (value == -1)
            this.min = null;
          else {
            if (this.min == null)
              this.min = new Integer();
            this.min.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #max} (The maximum number of times this element is permitted to appear in the instance.)
         */
        public String_ getMax() { 
          return this.max;
        }

        /**
         * @param value {@link #max} (The maximum number of times this element is permitted to appear in the instance.)
         */
        public ElementDefinitionComponent setMax(String_ value) { 
          this.max = value;
          return this;
        }

        /**
         * @return The maximum number of times this element is permitted to appear in the instance.
         */
        public String getMaxSimple() { 
          return this.max == null ? null : this.max.getValue();
        }

        /**
         * @param value The maximum number of times this element is permitted to appear in the instance.
         */
        public ElementDefinitionComponent setMaxSimple(String value) { 
          if (value == null)
            this.max = null;
          else {
            if (this.max == null)
              this.max = new String_();
            this.max.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The data type or resource that the value of this element is permitted to be.)
         */
        public List<TypeRefComponent> getType() { 
          return this.type;
        }

    // syntactic sugar
        /**
         * @return {@link #type} (The data type or resource that the value of this element is permitted to be.)
         */
        public TypeRefComponent addType() { 
          TypeRefComponent t = new TypeRefComponent();
          this.type.add(t);
          return t;
        }

        /**
         * @return {@link #nameReference} (Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.)
         */
        public String_ getNameReference() { 
          return this.nameReference;
        }

        /**
         * @param value {@link #nameReference} (Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.)
         */
        public ElementDefinitionComponent setNameReference(String_ value) { 
          this.nameReference = value;
          return this;
        }

        /**
         * @return Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.
         */
        public String getNameReferenceSimple() { 
          return this.nameReference == null ? null : this.nameReference.getValue();
        }

        /**
         * @param value Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.
         */
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

        /**
         * @return {@link #value} (Specifies a primitive value that SHALL hold for this element in the instance.)
         */
        public org.hl7.fhir.instance.model.Type getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (Specifies a primitive value that SHALL hold for this element in the instance.)
         */
        public ElementDefinitionComponent setValue(org.hl7.fhir.instance.model.Type value) { 
          this.value = value;
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
        public ElementDefinitionComponent setExample(org.hl7.fhir.instance.model.Type value) { 
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
        public ElementDefinitionComponent setMaxLength(Integer value) { 
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

        /**
         * @return {@link #condition} (A reference to an invariant that may make additional statements about the cardinality or value in the instance.)
         */
        public List<Id> getCondition() { 
          return this.condition;
        }

    // syntactic sugar
        /**
         * @return {@link #condition} (A reference to an invariant that may make additional statements about the cardinality or value in the instance.)
         */
        public Id addCondition() { 
          Id t = new Id();
          this.condition.add(t);
          return t;
        }

        /**
         * @param value {@link #condition} (A reference to an invariant that may make additional statements about the cardinality or value in the instance.)
         */
        public Id addConditionSimple(String value) { 
          Id t = new Id();
          t.setValue(value);
          this.condition.add(t);
          return t;
        }

        /**
         * @param value {@link #condition} (A reference to an invariant that may make additional statements about the cardinality or value in the instance.)
         */
        public boolean hasConditionSimple(String value) { 
          for (Id v : this.condition)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #constraint} (Formal constraints such as co-occurrence and other constraints that can be computationally evaluated within the context of the instance.)
         */
        public List<ElementDefinitionConstraintComponent> getConstraint() { 
          return this.constraint;
        }

    // syntactic sugar
        /**
         * @return {@link #constraint} (Formal constraints such as co-occurrence and other constraints that can be computationally evaluated within the context of the instance.)
         */
        public ElementDefinitionConstraintComponent addConstraint() { 
          ElementDefinitionConstraintComponent t = new ElementDefinitionConstraintComponent();
          this.constraint.add(t);
          return t;
        }

        /**
         * @return {@link #mustSupport} (If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.)
         */
        public Boolean getMustSupport() { 
          return this.mustSupport;
        }

        /**
         * @param value {@link #mustSupport} (If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.)
         */
        public ElementDefinitionComponent setMustSupport(Boolean value) { 
          this.mustSupport = value;
          return this;
        }

        /**
         * @return If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.
         */
        public boolean getMustSupportSimple() { 
          return this.mustSupport == null ? false : this.mustSupport.getValue();
        }

        /**
         * @param value If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.
         */
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

        /**
         * @return {@link #isModifier} (If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.)
         */
        public Boolean getIsModifier() { 
          return this.isModifier;
        }

        /**
         * @param value {@link #isModifier} (If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.)
         */
        public ElementDefinitionComponent setIsModifier(Boolean value) { 
          this.isModifier = value;
          return this;
        }

        /**
         * @return If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.
         */
        public boolean getIsModifierSimple() { 
          return this.isModifier == null ? false : this.isModifier.getValue();
        }

        /**
         * @param value If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.
         */
        public ElementDefinitionComponent setIsModifierSimple(boolean value) { 
          if (value == false)
            this.isModifier = null;
          else {
            if (this.isModifier == null)
              this.isModifier = new Boolean();
            this.isModifier.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #binding} (Binds to a value set if this element is coded (code, Coding, CodeableConcept).)
         */
        public ElementDefinitionBindingComponent getBinding() { 
          return this.binding;
        }

        /**
         * @param value {@link #binding} (Binds to a value set if this element is coded (code, Coding, CodeableConcept).)
         */
        public ElementDefinitionComponent setBinding(ElementDefinitionBindingComponent value) { 
          this.binding = value;
          return this;
        }

        /**
         * @return {@link #mapping} (Identifies a concept from an external specification that roughly corresponds to this element.)
         */
        public List<ElementDefinitionMappingComponent> getMapping() { 
          return this.mapping;
        }

    // syntactic sugar
        /**
         * @return {@link #mapping} (Identifies a concept from an external specification that roughly corresponds to this element.)
         */
        public ElementDefinitionMappingComponent addMapping() { 
          ElementDefinitionMappingComponent t = new ElementDefinitionMappingComponent();
          this.mapping.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("short", "string", "A concise definition that  is shown in the generated XML format that summarizes profiles (used throughout the specification).", 0, java.lang.Integer.MAX_VALUE, short_));
          childrenList.add(new Property("formal", "string", "The definition SHALL be consistent with the base definition, but convey the meaning of the element in the particular context of use of the resource.", 0, java.lang.Integer.MAX_VALUE, formal));
          childrenList.add(new Property("comments", "string", "Comments about the use of the element, including notes about how to use the data properly, exceptions to proper use, etc.", 0, java.lang.Integer.MAX_VALUE, comments));
          childrenList.add(new Property("requirements", "string", "Explains why this element is needed and why it's been constrained as it has.", 0, java.lang.Integer.MAX_VALUE, requirements));
          childrenList.add(new Property("synonym", "string", "Identifies additional names by which this element might also be known.", 0, java.lang.Integer.MAX_VALUE, synonym));
          childrenList.add(new Property("min", "integer", "The minimum number of times this element SHALL appear in the instance.", 0, java.lang.Integer.MAX_VALUE, min));
          childrenList.add(new Property("max", "string", "The maximum number of times this element is permitted to appear in the instance.", 0, java.lang.Integer.MAX_VALUE, max));
          childrenList.add(new Property("type", "", "The data type or resource that the value of this element is permitted to be.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("nameReference", "string", "Identifies the name of a slice defined elsewhere in the profile whose constraints should be applied to the current element.", 0, java.lang.Integer.MAX_VALUE, nameReference));
          childrenList.add(new Property("value[x]", "*", "Specifies a primitive value that SHALL hold for this element in the instance.", 0, java.lang.Integer.MAX_VALUE, value));
          childrenList.add(new Property("example[x]", "*", "An example value for this element.", 0, java.lang.Integer.MAX_VALUE, example));
          childrenList.add(new Property("maxLength", "integer", "Indicates the shortest length that SHALL be supported by conformant instances without truncation.", 0, java.lang.Integer.MAX_VALUE, maxLength));
          childrenList.add(new Property("condition", "id", "A reference to an invariant that may make additional statements about the cardinality or value in the instance.", 0, java.lang.Integer.MAX_VALUE, condition));
          childrenList.add(new Property("constraint", "", "Formal constraints such as co-occurrence and other constraints that can be computationally evaluated within the context of the instance.", 0, java.lang.Integer.MAX_VALUE, constraint));
          childrenList.add(new Property("mustSupport", "boolean", "If true, conformant resource authors SHALL be capable of providing a value for the element and resource consumers SHALL be capable of extracting and doing something useful with the data element.  If false, the element may be ignored and not supported.", 0, java.lang.Integer.MAX_VALUE, mustSupport));
          childrenList.add(new Property("isModifier", "boolean", "If true, the value of this element affects the interpretation of the element or resource that contains it, and the value of the element cannot be ignored. Typically, this is used for status, negation and qualification codes. The effect of this is that the element cannot be ignored by systems: they SHALL either recognize the element and process it, and/or a pre-determination has been made that it is not relevant to their particular system.", 0, java.lang.Integer.MAX_VALUE, isModifier));
          childrenList.add(new Property("binding", "", "Binds to a value set if this element is coded (code, Coding, CodeableConcept).", 0, java.lang.Integer.MAX_VALUE, binding));
          childrenList.add(new Property("mapping", "", "Identifies a concept from an external specification that roughly corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, mapping));
        }

      public ElementDefinitionComponent copy() {
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
          dst.type.add(i.copy());
        dst.nameReference = nameReference == null ? null : nameReference.copy();
        dst.value = value == null ? null : value.copy();
        dst.example = example == null ? null : example.copy();
        dst.maxLength = maxLength == null ? null : maxLength.copy();
        dst.condition = new ArrayList<Id>();
        for (Id i : condition)
          dst.condition.add(i.copy());
        dst.constraint = new ArrayList<ElementDefinitionConstraintComponent>();
        for (ElementDefinitionConstraintComponent i : constraint)
          dst.constraint.add(i.copy());
        dst.mustSupport = mustSupport == null ? null : mustSupport.copy();
        dst.isModifier = isModifier == null ? null : isModifier.copy();
        dst.binding = binding == null ? null : binding.copy();
        dst.mapping = new ArrayList<ElementDefinitionMappingComponent>();
        for (ElementDefinitionMappingComponent i : mapping)
          dst.mapping.add(i.copy());
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

        private static final long serialVersionUID = -1330878106L;

      public TypeRefComponent() {
        super();
      }

      public TypeRefComponent(Code code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Name of Data type or Resource.)
         */
        public Code getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Name of Data type or Resource.)
         */
        public TypeRefComponent setCode(Code value) { 
          this.code = value;
          return this;
        }

        /**
         * @return Name of Data type or Resource.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value Name of Data type or Resource.
         */
        public TypeRefComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #profile} (Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.)
         */
        public Uri getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.)
         */
        public TypeRefComponent setProfile(Uri value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.
         */
        public String getProfileSimple() { 
          return this.profile == null ? null : this.profile.getValue();
        }

        /**
         * @param value Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.
         */
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

        /**
         * @return {@link #aggregation} (If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.)
         */
        public List<Enumeration<ResourceAggregationMode>> getAggregation() { 
          return this.aggregation;
        }

    // syntactic sugar
        /**
         * @return {@link #aggregation} (If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.)
         */
        public Enumeration<ResourceAggregationMode> addAggregation() { 
          Enumeration<ResourceAggregationMode> t = new Enumeration<ResourceAggregationMode>();
          this.aggregation.add(t);
          return t;
        }

        /**
         * @param value {@link #aggregation} (If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.)
         */
        public Enumeration<ResourceAggregationMode> addAggregationSimple(ResourceAggregationMode value) { 
          Enumeration<ResourceAggregationMode> t = new Enumeration<ResourceAggregationMode>();
          t.setValue(value);
          this.aggregation.add(t);
          return t;
        }

        /**
         * @param value {@link #aggregation} (If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.)
         */
        public boolean hasAggregationSimple(ResourceAggregationMode value) { 
          for (Enumeration<ResourceAggregationMode> v : this.aggregation)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "Name of Data type or Resource.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("profile", "uri", "Identifies a profile that SHALL hold for resources or datatypes referenced as the type of this element. Can be a local reference - to another structure in this profile, or a reference to a structure in another profile.", 0, java.lang.Integer.MAX_VALUE, profile));
          childrenList.add(new Property("aggregation", "code", "If the type is a reference to another resource, how the resource is or can be aggreated - is it a contained resource, or a reference, and if the context is a bundle, is it included in the bundle.", 0, java.lang.Integer.MAX_VALUE, aggregation));
        }

      public TypeRefComponent copy() {
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

        private static final long serialVersionUID = -191188023L;

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

        /**
         * @return {@link #key} (Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.)
         */
        public Id getKey() { 
          return this.key;
        }

        /**
         * @param value {@link #key} (Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.)
         */
        public ElementDefinitionConstraintComponent setKey(Id value) { 
          this.key = value;
          return this;
        }

        /**
         * @return Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.
         */
        public String getKeySimple() { 
          return this.key == null ? null : this.key.getValue();
        }

        /**
         * @param value Allows identification of which elements have their cardinalities impacted by the constraint.  Will not be referenced for constraints that do not affect cardinality.
         */
        public ElementDefinitionConstraintComponent setKeySimple(String value) { 
            if (this.key == null)
              this.key = new Id();
            this.key.setValue(value);
          return this;
        }

        /**
         * @return {@link #name} (Used to label the constraint in OCL or in short displays incapable of displaying the full human description.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Used to label the constraint in OCL or in short displays incapable of displaying the full human description.)
         */
        public ElementDefinitionConstraintComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return Used to label the constraint in OCL or in short displays incapable of displaying the full human description.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value Used to label the constraint in OCL or in short displays incapable of displaying the full human description.
         */
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

        /**
         * @return {@link #severity} (Identifies the impact constraint violation has on the conformance of the instance.)
         */
        public Enumeration<ConstraintSeverity> getSeverity() { 
          return this.severity;
        }

        /**
         * @param value {@link #severity} (Identifies the impact constraint violation has on the conformance of the instance.)
         */
        public ElementDefinitionConstraintComponent setSeverity(Enumeration<ConstraintSeverity> value) { 
          this.severity = value;
          return this;
        }

        /**
         * @return Identifies the impact constraint violation has on the conformance of the instance.
         */
        public ConstraintSeverity getSeveritySimple() { 
          return this.severity == null ? null : this.severity.getValue();
        }

        /**
         * @param value Identifies the impact constraint violation has on the conformance of the instance.
         */
        public ElementDefinitionConstraintComponent setSeveritySimple(ConstraintSeverity value) { 
            if (this.severity == null)
              this.severity = new Enumeration<ConstraintSeverity>();
            this.severity.setValue(value);
          return this;
        }

        /**
         * @return {@link #human} (Text that can be used to describe the constraint in messages identifying that the constraint has been violated.)
         */
        public String_ getHuman() { 
          return this.human;
        }

        /**
         * @param value {@link #human} (Text that can be used to describe the constraint in messages identifying that the constraint has been violated.)
         */
        public ElementDefinitionConstraintComponent setHuman(String_ value) { 
          this.human = value;
          return this;
        }

        /**
         * @return Text that can be used to describe the constraint in messages identifying that the constraint has been violated.
         */
        public String getHumanSimple() { 
          return this.human == null ? null : this.human.getValue();
        }

        /**
         * @param value Text that can be used to describe the constraint in messages identifying that the constraint has been violated.
         */
        public ElementDefinitionConstraintComponent setHumanSimple(String value) { 
            if (this.human == null)
              this.human = new String_();
            this.human.setValue(value);
          return this;
        }

        /**
         * @return {@link #xpath} (XPath expression of constraint.)
         */
        public String_ getXpath() { 
          return this.xpath;
        }

        /**
         * @param value {@link #xpath} (XPath expression of constraint.)
         */
        public ElementDefinitionConstraintComponent setXpath(String_ value) { 
          this.xpath = value;
          return this;
        }

        /**
         * @return XPath expression of constraint.
         */
        public String getXpathSimple() { 
          return this.xpath == null ? null : this.xpath.getValue();
        }

        /**
         * @param value XPath expression of constraint.
         */
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

      public ElementDefinitionConstraintComponent copy() {
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

        private static final long serialVersionUID = 1398404837L;

      public ElementDefinitionBindingComponent() {
        super();
      }

      public ElementDefinitionBindingComponent(String_ name, Boolean isExtensible) {
        super();
        this.name = name;
        this.isExtensible = isExtensible;
      }

        /**
         * @return {@link #name} (A descriptive name for this - can be useful for generating implementation artifacts.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A descriptive name for this - can be useful for generating implementation artifacts.)
         */
        public ElementDefinitionBindingComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return A descriptive name for this - can be useful for generating implementation artifacts.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value A descriptive name for this - can be useful for generating implementation artifacts.
         */
        public ElementDefinitionBindingComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
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
        public ElementDefinitionBindingComponent setIsExtensible(Boolean value) { 
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
        public ElementDefinitionBindingComponent setIsExtensibleSimple(boolean value) { 
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
        public ElementDefinitionBindingComponent setConformance(Enumeration<BindingConformance> value) { 
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

        /**
         * @return {@link #description} (Describes the intended use of this particular set of codes.)
         */
        public String_ getDescription() { 
          return this.description;
        }

        /**
         * @param value {@link #description} (Describes the intended use of this particular set of codes.)
         */
        public ElementDefinitionBindingComponent setDescription(String_ value) { 
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

        /**
         * @return {@link #reference} (Points to the value set or external definition that identifies the set of codes to be used.)
         */
        public Type getReference() { 
          return this.reference;
        }

        /**
         * @param value {@link #reference} (Points to the value set or external definition that identifies the set of codes to be used.)
         */
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

      public ElementDefinitionBindingComponent copy() {
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
         * An internal reference to the definition of a mapping.
         */
        protected Id identity;

        /**
         * Expresses what part of the target specification corresponds to this element.
         */
        protected String_ map;

        private static final long serialVersionUID = 257647079L;

      public ElementDefinitionMappingComponent() {
        super();
      }

      public ElementDefinitionMappingComponent(Id identity, String_ map) {
        super();
        this.identity = identity;
        this.map = map;
      }

        /**
         * @return {@link #identity} (An internal reference to the definition of a mapping.)
         */
        public Id getIdentity() { 
          return this.identity;
        }

        /**
         * @param value {@link #identity} (An internal reference to the definition of a mapping.)
         */
        public ElementDefinitionMappingComponent setIdentity(Id value) { 
          this.identity = value;
          return this;
        }

        /**
         * @return An internal reference to the definition of a mapping.
         */
        public String getIdentitySimple() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        /**
         * @param value An internal reference to the definition of a mapping.
         */
        public ElementDefinitionMappingComponent setIdentitySimple(String value) { 
            if (this.identity == null)
              this.identity = new Id();
            this.identity.setValue(value);
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
        public ElementDefinitionMappingComponent setMap(String_ value) { 
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
        public ElementDefinitionMappingComponent setMapSimple(String value) { 
            if (this.map == null)
              this.map = new String_();
            this.map.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identity", "id", "An internal reference to the definition of a mapping.", 0, java.lang.Integer.MAX_VALUE, identity));
          childrenList.add(new Property("map", "string", "Expresses what part of the target specification corresponds to this element.", 0, java.lang.Integer.MAX_VALUE, map));
        }

      public ElementDefinitionMappingComponent copy() {
        ElementDefinitionMappingComponent dst = new ElementDefinitionMappingComponent();
        dst.identity = identity == null ? null : identity.copy();
        dst.map = map == null ? null : map.copy();
        return dst;
      }

  }

    public static class ProfileStructureSearchParamComponent extends BackboneElement {
        /**
         * The name of the standard or custom search parameter.
         */
        protected String_ name;

        /**
         * The type of value a search parameter refers to, and how the content is interpreted.
         */
        protected Enumeration<SearchParamType> type;

        /**
         * A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        protected String_ documentation;

        /**
         * An XPath expression that returns a set of elements for the search parameter.
         */
        protected String_ xpath;

        /**
         * Types of resource (if a resource is referenced).
         */
        protected List<Code> target = new ArrayList<Code>();

        private static final long serialVersionUID = -872306768L;

      public ProfileStructureSearchParamComponent() {
        super();
      }

      public ProfileStructureSearchParamComponent(String_ name, Enumeration<SearchParamType> type, String_ documentation) {
        super();
        this.name = name;
        this.type = type;
        this.documentation = documentation;
      }

        /**
         * @return {@link #name} (The name of the standard or custom search parameter.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the standard or custom search parameter.)
         */
        public ProfileStructureSearchParamComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the standard or custom search parameter.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the standard or custom search parameter.
         */
        public ProfileStructureSearchParamComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.)
         */
        public Enumeration<SearchParamType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.)
         */
        public ProfileStructureSearchParamComponent setType(Enumeration<SearchParamType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of value a search parameter refers to, and how the content is interpreted.
         */
        public SearchParamType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of value a search parameter refers to, and how the content is interpreted.
         */
        public ProfileStructureSearchParamComponent setTypeSimple(SearchParamType value) { 
            if (this.type == null)
              this.type = new Enumeration<SearchParamType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.)
         */
        public ProfileStructureSearchParamComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        public ProfileStructureSearchParamComponent setDocumentationSimple(String value) { 
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          return this;
        }

        /**
         * @return {@link #xpath} (An XPath expression that returns a set of elements for the search parameter.)
         */
        public String_ getXpath() { 
          return this.xpath;
        }

        /**
         * @param value {@link #xpath} (An XPath expression that returns a set of elements for the search parameter.)
         */
        public ProfileStructureSearchParamComponent setXpath(String_ value) { 
          this.xpath = value;
          return this;
        }

        /**
         * @return An XPath expression that returns a set of elements for the search parameter.
         */
        public String getXpathSimple() { 
          return this.xpath == null ? null : this.xpath.getValue();
        }

        /**
         * @param value An XPath expression that returns a set of elements for the search parameter.
         */
        public ProfileStructureSearchParamComponent setXpathSimple(String value) { 
          if (value == null)
            this.xpath = null;
          else {
            if (this.xpath == null)
              this.xpath = new String_();
            this.xpath.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public List<Code> getTarget() { 
          return this.target;
        }

    // syntactic sugar
        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public Code addTarget() { 
          Code t = new Code();
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public Code addTargetSimple(String value) { 
          Code t = new Code();
          t.setValue(value);
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public boolean hasTargetSimple(String value) { 
          for (Code v : this.target)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of the standard or custom search parameter.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("type", "code", "The type of value a search parameter refers to, and how the content is interpreted.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("documentation", "string", "A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("xpath", "string", "An XPath expression that returns a set of elements for the search parameter.", 0, java.lang.Integer.MAX_VALUE, xpath));
          childrenList.add(new Property("target", "code", "Types of resource (if a resource is referenced).", 0, java.lang.Integer.MAX_VALUE, target));
        }

      public ProfileStructureSearchParamComponent copy() {
        ProfileStructureSearchParamComponent dst = new ProfileStructureSearchParamComponent();
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.xpath = xpath == null ? null : xpath.copy();
        dst.target = new ArrayList<Code>();
        for (Code i : target)
          dst.target.add(i.copy());
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
        protected List<ElementComponent> element = new ArrayList<ElementComponent>();

        private static final long serialVersionUID = -1308658505L;

      public ProfileExtensionDefnComponent() {
        super();
      }

      public ProfileExtensionDefnComponent(Code code, Enumeration<ExtensionContext> contextType) {
        super();
        this.code = code;
        this.contextType = contextType;
      }

        /**
         * @return {@link #code} (A unique code (within the profile) used to identify the extension.)
         */
        public Code getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (A unique code (within the profile) used to identify the extension.)
         */
        public ProfileExtensionDefnComponent setCode(Code value) { 
          this.code = value;
          return this;
        }

        /**
         * @return A unique code (within the profile) used to identify the extension.
         */
        public String getCodeSimple() { 
          return this.code == null ? null : this.code.getValue();
        }

        /**
         * @param value A unique code (within the profile) used to identify the extension.
         */
        public ProfileExtensionDefnComponent setCodeSimple(String value) { 
            if (this.code == null)
              this.code = new Code();
            this.code.setValue(value);
          return this;
        }

        /**
         * @return {@link #display} (Defined so that applications can use this name when displaying the value of the extension to the user.)
         */
        public String_ getDisplay() { 
          return this.display;
        }

        /**
         * @param value {@link #display} (Defined so that applications can use this name when displaying the value of the extension to the user.)
         */
        public ProfileExtensionDefnComponent setDisplay(String_ value) { 
          this.display = value;
          return this;
        }

        /**
         * @return Defined so that applications can use this name when displaying the value of the extension to the user.
         */
        public String getDisplaySimple() { 
          return this.display == null ? null : this.display.getValue();
        }

        /**
         * @param value Defined so that applications can use this name when displaying the value of the extension to the user.
         */
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

        /**
         * @return {@link #contextType} (Identifies the type of context to which the extension applies.)
         */
        public Enumeration<ExtensionContext> getContextType() { 
          return this.contextType;
        }

        /**
         * @param value {@link #contextType} (Identifies the type of context to which the extension applies.)
         */
        public ProfileExtensionDefnComponent setContextType(Enumeration<ExtensionContext> value) { 
          this.contextType = value;
          return this;
        }

        /**
         * @return Identifies the type of context to which the extension applies.
         */
        public ExtensionContext getContextTypeSimple() { 
          return this.contextType == null ? null : this.contextType.getValue();
        }

        /**
         * @param value Identifies the type of context to which the extension applies.
         */
        public ProfileExtensionDefnComponent setContextTypeSimple(ExtensionContext value) { 
            if (this.contextType == null)
              this.contextType = new Enumeration<ExtensionContext>();
            this.contextType.setValue(value);
          return this;
        }

        /**
         * @return {@link #context} (Identifies the types of resource or data type elements to which the extension can be applied.)
         */
        public List<String_> getContext() { 
          return this.context;
        }

    // syntactic sugar
        /**
         * @return {@link #context} (Identifies the types of resource or data type elements to which the extension can be applied.)
         */
        public String_ addContext() { 
          String_ t = new String_();
          this.context.add(t);
          return t;
        }

        /**
         * @param value {@link #context} (Identifies the types of resource or data type elements to which the extension can be applied.)
         */
        public String_ addContextSimple(String value) { 
          String_ t = new String_();
          t.setValue(value);
          this.context.add(t);
          return t;
        }

        /**
         * @param value {@link #context} (Identifies the types of resource or data type elements to which the extension can be applied.)
         */
        public boolean hasContextSimple(String value) { 
          for (String_ v : this.context)
            if (v.getValue().equals(value))
              return true;
          return false;
        }

        /**
         * @return {@link #element} (Definition of the extension and its content.)
         */
        public List<ElementComponent> getElement() { 
          return this.element;
        }

    // syntactic sugar
        /**
         * @return {@link #element} (Definition of the extension and its content.)
         */
        public ElementComponent addElement() { 
          ElementComponent t = new ElementComponent();
          this.element.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "code", "A unique code (within the profile) used to identify the extension.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("display", "string", "Defined so that applications can use this name when displaying the value of the extension to the user.", 0, java.lang.Integer.MAX_VALUE, display));
          childrenList.add(new Property("contextType", "code", "Identifies the type of context to which the extension applies.", 0, java.lang.Integer.MAX_VALUE, contextType));
          childrenList.add(new Property("context", "string", "Identifies the types of resource or data type elements to which the extension can be applied.", 0, java.lang.Integer.MAX_VALUE, context));
          childrenList.add(new Property("element", "@Profile.structure.snapshot.element", "Definition of the extension and its content.", 0, java.lang.Integer.MAX_VALUE, element));
        }

      public ProfileExtensionDefnComponent copy() {
        ProfileExtensionDefnComponent dst = new ProfileExtensionDefnComponent();
        dst.code = code == null ? null : code.copy();
        dst.display = display == null ? null : display.copy();
        dst.contextType = contextType == null ? null : contextType.copy();
        dst.context = new ArrayList<String_>();
        for (String_ i : context)
          dst.context.add(i.copy());
        dst.element = new ArrayList<ElementComponent>();
        for (ElementComponent i : element)
          dst.element.add(i.copy());
        return dst;
      }

  }

    public static class ProfileQueryComponent extends BackboneElement {
        /**
         * The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.
         */
        protected String_ name;

        /**
         * Description of the query - the functionality it offers, and considerations about how it functions and to use it.
         */
        protected String_ documentation;

        /**
         * A parameter of a named query.
         */
        protected List<ProfileStructureSearchParamComponent> parameter = new ArrayList<ProfileStructureSearchParamComponent>();

        private static final long serialVersionUID = 1319228927L;

      public ProfileQueryComponent() {
        super();
      }

      public ProfileQueryComponent(String_ name, String_ documentation) {
        super();
        this.name = name;
        this.documentation = documentation;
      }

        /**
         * @return {@link #name} (The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.)
         */
        public String_ getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.)
         */
        public ProfileQueryComponent setName(String_ value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.
         */
        public ProfileQueryComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new String_();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Description of the query - the functionality it offers, and considerations about how it functions and to use it.)
         */
        public String_ getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Description of the query - the functionality it offers, and considerations about how it functions and to use it.)
         */
        public ProfileQueryComponent setDocumentation(String_ value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Description of the query - the functionality it offers, and considerations about how it functions and to use it.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Description of the query - the functionality it offers, and considerations about how it functions and to use it.
         */
        public ProfileQueryComponent setDocumentationSimple(String value) { 
            if (this.documentation == null)
              this.documentation = new String_();
            this.documentation.setValue(value);
          return this;
        }

        /**
         * @return {@link #parameter} (A parameter of a named query.)
         */
        public List<ProfileStructureSearchParamComponent> getParameter() { 
          return this.parameter;
        }

    // syntactic sugar
        /**
         * @return {@link #parameter} (A parameter of a named query.)
         */
        public ProfileStructureSearchParamComponent addParameter() { 
          ProfileStructureSearchParamComponent t = new ProfileStructureSearchParamComponent();
          this.parameter.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "string", "The name of a query, which is used in the URI from Conformance statements declaring use of the query.  Typically this will also be the name for the _query parameter when the query is called, though in some cases it may be aliased by a server to avoid collisions.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("documentation", "string", "Description of the query - the functionality it offers, and considerations about how it functions and to use it.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("parameter", "@Profile.structure.searchParam", "A parameter of a named query.", 0, java.lang.Integer.MAX_VALUE, parameter));
        }

      public ProfileQueryComponent copy() {
        ProfileQueryComponent dst = new ProfileQueryComponent();
        dst.name = name == null ? null : name.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.parameter = new ArrayList<ProfileStructureSearchParamComponent>();
        for (ProfileStructureSearchParamComponent i : parameter)
          dst.parameter.add(i.copy());
        return dst;
      }

  }

    /**
     * The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    protected Uri url;

    /**
     * Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

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
     * The Scope and Usage that this profile was created to meet.
     */
    protected String_ requirements;

    /**
     * The version of the FHIR specification on which this profile is based.
     */
    protected Id fhirVersion;

    /**
     * An external specification that the content is mapped to.
     */
    protected List<ProfileMappingComponent> mapping = new ArrayList<ProfileMappingComponent>();

    /**
     * A constraint statement about what contents a resource or data type may have.
     */
    protected List<ProfileStructureComponent> structure = new ArrayList<ProfileStructureComponent>();

    /**
     * An extension defined as part of the profile.
     */
    protected List<ProfileExtensionDefnComponent> extensionDefn = new ArrayList<ProfileExtensionDefnComponent>();

    /**
     * Definition of a named query and its parameters and their meaning.
     */
    protected List<ProfileQueryComponent> query = new ArrayList<ProfileQueryComponent>();

    private static final long serialVersionUID = 1629111250L;

    public Profile() {
      super();
    }

    public Profile(Uri url, String_ name, Enumeration<ResourceProfileStatus> status) {
      super();
      this.url = url;
      this.name = name;
      this.status = status;
    }

    /**
     * @return {@link #url} (The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.)
     */
    public Uri getUrl() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.)
     */
    public Profile setUrl(Uri value) { 
      this.url = value;
      return this;
    }

    /**
     * @return The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    public String getUrlSimple() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    public Profile setUrlSimple(String value) { 
        if (this.url == null)
          this.url = new Uri();
        this.url.setValue(value);
      return this;
    }

    /**
     * @return {@link #identifier} (Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public String_ getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public Profile setVersion(String_ value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    public String getVersionSimple() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
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

    /**
     * @return {@link #name} (A free text natural language name identifying the Profile.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name identifying the Profile.)
     */
    public Profile setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name identifying the Profile.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name identifying the Profile.
     */
    public Profile setNameSimple(String value) { 
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.)
     */
    public String_ getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.)
     */
    public Profile setPublisher(String_ value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    public String getPublisherSimple() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Details of the individual or organization who accepts responsibility for publishing the profile.
     */
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
     * @return {@link #description} (A free text natural language description of the profile and its use.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the profile and its use.)
     */
    public Profile setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the profile and its use.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the profile and its use.
     */
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

    /**
     * @return {@link #code} (A set of terms from external terminologies that may be used to assist with indexing and searching of templates.)
     */
    public List<Coding> getCode() { 
      return this.code;
    }

    // syntactic sugar
    /**
     * @return {@link #code} (A set of terms from external terminologies that may be used to assist with indexing and searching of templates.)
     */
    public Coding addCode() { 
      Coding t = new Coding();
      this.code.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the profile.)
     */
    public Enumeration<ResourceProfileStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the profile.)
     */
    public Profile setStatus(Enumeration<ResourceProfileStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the profile.
     */
    public ResourceProfileStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the profile.
     */
    public Profile setStatusSimple(ResourceProfileStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceProfileStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public Boolean getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public Profile setExperimental(Boolean value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimentalSimple() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
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

    /**
     * @return {@link #date} (The date that this version of the profile was published.)
     */
    public DateTime getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the profile was published.)
     */
    public Profile setDate(DateTime value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that this version of the profile was published.
     */
    public DateAndTime getDateSimple() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that this version of the profile was published.
     */
    public Profile setDateSimple(DateAndTime value) { 
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
     * @return {@link #requirements} (The Scope and Usage that this profile was created to meet.)
     */
    public String_ getRequirements() { 
      return this.requirements;
    }

    /**
     * @param value {@link #requirements} (The Scope and Usage that this profile was created to meet.)
     */
    public Profile setRequirements(String_ value) { 
      this.requirements = value;
      return this;
    }

    /**
     * @return The Scope and Usage that this profile was created to meet.
     */
    public String getRequirementsSimple() { 
      return this.requirements == null ? null : this.requirements.getValue();
    }

    /**
     * @param value The Scope and Usage that this profile was created to meet.
     */
    public Profile setRequirementsSimple(String value) { 
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
     * @return {@link #fhirVersion} (The version of the FHIR specification on which this profile is based.)
     */
    public Id getFhirVersion() { 
      return this.fhirVersion;
    }

    /**
     * @param value {@link #fhirVersion} (The version of the FHIR specification on which this profile is based.)
     */
    public Profile setFhirVersion(Id value) { 
      this.fhirVersion = value;
      return this;
    }

    /**
     * @return The version of the FHIR specification on which this profile is based.
     */
    public String getFhirVersionSimple() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    /**
     * @param value The version of the FHIR specification on which this profile is based.
     */
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

    /**
     * @return {@link #mapping} (An external specification that the content is mapped to.)
     */
    public List<ProfileMappingComponent> getMapping() { 
      return this.mapping;
    }

    // syntactic sugar
    /**
     * @return {@link #mapping} (An external specification that the content is mapped to.)
     */
    public ProfileMappingComponent addMapping() { 
      ProfileMappingComponent t = new ProfileMappingComponent();
      this.mapping.add(t);
      return t;
    }

    /**
     * @return {@link #structure} (A constraint statement about what contents a resource or data type may have.)
     */
    public List<ProfileStructureComponent> getStructure() { 
      return this.structure;
    }

    // syntactic sugar
    /**
     * @return {@link #structure} (A constraint statement about what contents a resource or data type may have.)
     */
    public ProfileStructureComponent addStructure() { 
      ProfileStructureComponent t = new ProfileStructureComponent();
      this.structure.add(t);
      return t;
    }

    /**
     * @return {@link #extensionDefn} (An extension defined as part of the profile.)
     */
    public List<ProfileExtensionDefnComponent> getExtensionDefn() { 
      return this.extensionDefn;
    }

    // syntactic sugar
    /**
     * @return {@link #extensionDefn} (An extension defined as part of the profile.)
     */
    public ProfileExtensionDefnComponent addExtensionDefn() { 
      ProfileExtensionDefnComponent t = new ProfileExtensionDefnComponent();
      this.extensionDefn.add(t);
      return t;
    }

    /**
     * @return {@link #query} (Definition of a named query and its parameters and their meaning.)
     */
    public List<ProfileQueryComponent> getQuery() { 
      return this.query;
    }

    // syntactic sugar
    /**
     * @return {@link #query} (Definition of a named query and its parameters and their meaning.)
     */
    public ProfileQueryComponent addQuery() { 
      ProfileQueryComponent t = new ProfileQueryComponent();
      this.query.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("url", "uri", "The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.", 0, java.lang.Integer.MAX_VALUE, url));
        childrenList.add(new Property("identifier", "Identifier", "Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name identifying the Profile.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the profile.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the profile and its use.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("code", "Coding", "A set of terms from external terminologies that may be used to assist with indexing and searching of templates.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("status", "code", "The status of the profile.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the profile was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("requirements", "string", "The Scope and Usage that this profile was created to meet.", 0, java.lang.Integer.MAX_VALUE, requirements));
        childrenList.add(new Property("fhirVersion", "id", "The version of the FHIR specification on which this profile is based.", 0, java.lang.Integer.MAX_VALUE, fhirVersion));
        childrenList.add(new Property("mapping", "", "An external specification that the content is mapped to.", 0, java.lang.Integer.MAX_VALUE, mapping));
        childrenList.add(new Property("structure", "", "A constraint statement about what contents a resource or data type may have.", 0, java.lang.Integer.MAX_VALUE, structure));
        childrenList.add(new Property("extensionDefn", "", "An extension defined as part of the profile.", 0, java.lang.Integer.MAX_VALUE, extensionDefn));
        childrenList.add(new Property("query", "", "Definition of a named query and its parameters and their meaning.", 0, java.lang.Integer.MAX_VALUE, query));
      }

      public Profile copy() {
        Profile dst = new Profile();
        dst.url = url == null ? null : url.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
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
        dst.requirements = requirements == null ? null : requirements.copy();
        dst.fhirVersion = fhirVersion == null ? null : fhirVersion.copy();
        dst.mapping = new ArrayList<ProfileMappingComponent>();
        for (ProfileMappingComponent i : mapping)
          dst.mapping.add(i.copy());
        dst.structure = new ArrayList<ProfileStructureComponent>();
        for (ProfileStructureComponent i : structure)
          dst.structure.add(i.copy());
        dst.extensionDefn = new ArrayList<ProfileExtensionDefnComponent>();
        for (ProfileExtensionDefnComponent i : extensionDefn)
          dst.extensionDefn.add(i.copy());
        dst.query = new ArrayList<ProfileQueryComponent>();
        for (ProfileQueryComponent i : query)
          dst.query.add(i.copy());
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

