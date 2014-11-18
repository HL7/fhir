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
 * A Resource Profile - a statement of use of one or more FHIR Resources.  It may include constraints on Resources and Data Types, Terminology Binding Statements and Extension Definitions.
 */
public class Profile extends DomainResource {

    public enum ResourceProfileStatus {
        DRAFT, // This profile is still under development.
        ACTIVE, // This profile is ready for normal use.
        RETIRED, // This profile has been deprecated, withdrawn or superseded and should no longer be used.
        NULL; // added to help the parsers
        public static ResourceProfileStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("retired".equals(codeString))
          return RETIRED;
        throw new Exception("Unknown ResourceProfileStatus code '"+codeString+"'");
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
            case DRAFT: return "This profile is still under development.";
            case ACTIVE: return "This profile is ready for normal use.";
            case RETIRED: return "This profile has been deprecated, withdrawn or superseded and should no longer be used.";
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

  public static class ResourceProfileStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ResourceProfileStatus.DRAFT;
        if ("active".equals(codeString))
          return ResourceProfileStatus.ACTIVE;
        if ("retired".equals(codeString))
          return ResourceProfileStatus.RETIRED;
        throw new Exception("Unknown ResourceProfileStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == ResourceProfileStatus.DRAFT)
        return "draft";
      if (code == ResourceProfileStatus.ACTIVE)
        return "active";
      if (code == ResourceProfileStatus.RETIRED)
        return "retired";
      return "?";
      }
    }

    public enum SearchParamType {
        NUMBER, // Search parameter SHALL be a number (a whole number, or a decimal).
        DATE, // Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.
        STRING, // Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.
        TOKEN, // Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a "|", depending on the modifier used.
        REFERENCE, // A reference to another resource.
        COMPOSITE, // A composite search parameter that combines a search on two values together.
        QUANTITY, // A search parameter that searches on a quantity.
        NULL; // added to help the parsers
        public static SearchParamType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("number".equals(codeString))
          return NUMBER;
        if ("date".equals(codeString))
          return DATE;
        if ("string".equals(codeString))
          return STRING;
        if ("token".equals(codeString))
          return TOKEN;
        if ("reference".equals(codeString))
          return REFERENCE;
        if ("composite".equals(codeString))
          return COMPOSITE;
        if ("quantity".equals(codeString))
          return QUANTITY;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case NUMBER: return "number";
            case DATE: return "date";
            case STRING: return "string";
            case TOKEN: return "token";
            case REFERENCE: return "reference";
            case COMPOSITE: return "composite";
            case QUANTITY: return "quantity";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case NUMBER: return "Search parameter SHALL be a number (a whole number, or a decimal).";
            case DATE: return "Search parameter is on a date/time. The date format is the standard XML format, though other formats may be supported.";
            case STRING: return "Search parameter is a simple string, like a name part. Search is case-insensitive and accent-insensitive. May match just the start of a string. String parameters may contain spaces.";
            case TOKEN: return "Search parameter on a coded element or identifier. May be used to search through the text, displayname, code and code/codesystem (for codes) and label, system and key (for identifier). Its value is either a string or a pair of namespace and value, separated by a '|', depending on the modifier used.";
            case REFERENCE: return "A reference to another resource.";
            case COMPOSITE: return "A composite search parameter that combines a search on two values together.";
            case QUANTITY: return "A search parameter that searches on a quantity.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case NUMBER: return "number";
            case DATE: return "date";
            case STRING: return "string";
            case TOKEN: return "token";
            case REFERENCE: return "reference";
            case COMPOSITE: return "composite";
            case QUANTITY: return "quantity";
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
          return SearchParamType.NUMBER;
        if ("date".equals(codeString))
          return SearchParamType.DATE;
        if ("string".equals(codeString))
          return SearchParamType.STRING;
        if ("token".equals(codeString))
          return SearchParamType.TOKEN;
        if ("reference".equals(codeString))
          return SearchParamType.REFERENCE;
        if ("composite".equals(codeString))
          return SearchParamType.COMPOSITE;
        if ("quantity".equals(codeString))
          return SearchParamType.QUANTITY;
        throw new Exception("Unknown SearchParamType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == SearchParamType.NUMBER)
        return "number";
      if (code == SearchParamType.DATE)
        return "date";
      if (code == SearchParamType.STRING)
        return "string";
      if (code == SearchParamType.TOKEN)
        return "token";
      if (code == SearchParamType.REFERENCE)
        return "reference";
      if (code == SearchParamType.COMPOSITE)
        return "composite";
      if (code == SearchParamType.QUANTITY)
        return "quantity";
      return "?";
      }
    }

    public static class ProfileMappingComponent extends BackboneElement {
        /**
         * An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        protected IdType identity;

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

        private static final long serialVersionUID = 299630820L;

      public ProfileMappingComponent() {
        super();
      }

      public ProfileMappingComponent(IdType identity) {
        super();
        this.identity = identity;
      }

        /**
         * @return {@link #identity} (An Internal id that is used to identify this mapping set when specific mappings are made.). This is the underlying object with id, value and extensions. The accessor "getIdentity" gives direct access to the value
         */
        public IdType getIdentityElement() { 
          return this.identity;
        }

        /**
         * @param value {@link #identity} (An Internal id that is used to identify this mapping set when specific mappings are made.). This is the underlying object with id, value and extensions. The accessor "getIdentity" gives direct access to the value
         */
        public ProfileMappingComponent setIdentityElement(IdType value) { 
          this.identity = value;
          return this;
        }

        /**
         * @return An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        public String getIdentity() { 
          return this.identity == null ? null : this.identity.getValue();
        }

        /**
         * @param value An Internal id that is used to identify this mapping set when specific mappings are made.
         */
        public ProfileMappingComponent setIdentity(String value) { 
            if (this.identity == null)
              this.identity = new IdType();
            this.identity.setValue(value);
          return this;
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
        public ProfileMappingComponent setUriElement(UriType value) { 
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
        public ProfileMappingComponent setUri(String value) { 
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
        public ProfileMappingComponent setNameElement(StringType value) { 
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
        public ProfileMappingComponent setName(String value) { 
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
        public ProfileMappingComponent setCommentsElement(StringType value) { 
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
        public ProfileMappingComponent setComments(String value) { 
          if (Utilities.noString(value))
            this.comments = null;
          else {
            if (this.comments == null)
              this.comments = new StringType();
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
        copyValues(dst);
        dst.identity = identity == null ? null : identity.copy();
        dst.uri = uri == null ? null : uri.copy();
        dst.name = name == null ? null : name.copy();
        dst.comments = comments == null ? null : comments.copy();
        return dst;
      }

  }

    public static class ConstraintComponent extends BackboneElement {
        /**
         * Captures constraints on each element within the resource.
         */
        protected List<ElementDefinition> element = new ArrayList<ElementDefinition>();

        private static final long serialVersionUID = -1136379017L;

      public ConstraintComponent() {
        super();
      }

        /**
         * @return {@link #element} (Captures constraints on each element within the resource.)
         */
        public List<ElementDefinition> getElement() { 
          return this.element;
        }

        /**
         * @return {@link #element} (Captures constraints on each element within the resource.)
         */
    // syntactic sugar
        public ElementDefinition addElement() { //3
          ElementDefinition t = new ElementDefinition();
          this.element.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("element", "ElementDefinition", "Captures constraints on each element within the resource.", 0, java.lang.Integer.MAX_VALUE, element));
        }

      public ConstraintComponent copy() {
        ConstraintComponent dst = new ConstraintComponent();
        copyValues(dst);
        dst.element = new ArrayList<ElementDefinition>();
        for (ElementDefinition i : element)
          dst.element.add(i.copy());
        return dst;
      }

  }

    public static class ProfileSearchParamComponent extends BackboneElement {
        /**
         * The name of the standard or custom search parameter.
         */
        protected StringType name;

        /**
         * The type of value a search parameter refers to, and how the content is interpreted.
         */
        protected Enumeration<SearchParamType> type;

        /**
         * A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        protected StringType documentation;

        /**
         * An XPath expression that returns a set of elements for the search parameter.
         */
        protected StringType xpath;

        /**
         * Types of resource (if a resource is referenced).
         */
        protected List<CodeType> target = new ArrayList<CodeType>();

        private static final long serialVersionUID = -1550477651L;

      public ProfileSearchParamComponent() {
        super();
      }

      public ProfileSearchParamComponent(StringType name, Enumeration<SearchParamType> type, StringType documentation) {
        super();
        this.name = name;
        this.type = type;
        this.documentation = documentation;
      }

        /**
         * @return {@link #name} (The name of the standard or custom search parameter.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public StringType getNameElement() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of the standard or custom search parameter.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
         */
        public ProfileSearchParamComponent setNameElement(StringType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of the standard or custom search parameter.
         */
        public String getName() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of the standard or custom search parameter.
         */
        public ProfileSearchParamComponent setName(String value) { 
            if (this.name == null)
              this.name = new StringType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<SearchParamType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of value a search parameter refers to, and how the content is interpreted.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public ProfileSearchParamComponent setTypeElement(Enumeration<SearchParamType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of value a search parameter refers to, and how the content is interpreted.
         */
        public SearchParamType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of value a search parameter refers to, and how the content is interpreted.
         */
        public ProfileSearchParamComponent setType(SearchParamType value) { 
            if (this.type == null)
              this.type = new Enumeration<SearchParamType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public StringType getDocumentationElement() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.). This is the underlying object with id, value and extensions. The accessor "getDocumentation" gives direct access to the value
         */
        public ProfileSearchParamComponent setDocumentationElement(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        public String getDocumentation() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value A specification for search parameters. For standard parameters, provides additional information on how the parameter is used in this solution.  For custom parameters, provides a description of what the parameter does.
         */
        public ProfileSearchParamComponent setDocumentation(String value) { 
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          return this;
        }

        /**
         * @return {@link #xpath} (An XPath expression that returns a set of elements for the search parameter.). This is the underlying object with id, value and extensions. The accessor "getXpath" gives direct access to the value
         */
        public StringType getXpathElement() { 
          return this.xpath;
        }

        /**
         * @param value {@link #xpath} (An XPath expression that returns a set of elements for the search parameter.). This is the underlying object with id, value and extensions. The accessor "getXpath" gives direct access to the value
         */
        public ProfileSearchParamComponent setXpathElement(StringType value) { 
          this.xpath = value;
          return this;
        }

        /**
         * @return An XPath expression that returns a set of elements for the search parameter.
         */
        public String getXpath() { 
          return this.xpath == null ? null : this.xpath.getValue();
        }

        /**
         * @param value An XPath expression that returns a set of elements for the search parameter.
         */
        public ProfileSearchParamComponent setXpath(String value) { 
          if (Utilities.noString(value))
            this.xpath = null;
          else {
            if (this.xpath == null)
              this.xpath = new StringType();
            this.xpath.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
        public List<CodeType> getTarget() { 
          return this.target;
        }

        /**
         * @return {@link #target} (Types of resource (if a resource is referenced).)
         */
    // syntactic sugar
        public CodeType addTargetElement() {//2 
          CodeType t = new CodeType();
          this.target.add(t);
          return t;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public ProfileSearchParamComponent addTarget(String value) { //1
          CodeType t = new CodeType();
          t.setValue(value);
          this.target.add(t);
          return this;
        }

        /**
         * @param value {@link #target} (Types of resource (if a resource is referenced).)
         */
        public boolean hasTarget(String value) { 
          for (CodeType v : this.target)
            if (v.equals(value)) // code
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

      public ProfileSearchParamComponent copy() {
        ProfileSearchParamComponent dst = new ProfileSearchParamComponent();
        copyValues(dst);
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.xpath = xpath == null ? null : xpath.copy();
        dst.target = new ArrayList<CodeType>();
        for (CodeType i : target)
          dst.target.add(i.copy());
        return dst;
      }

  }

    /**
     * The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    protected UriType url;

    /**
     * Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.
     */
    protected StringType version;

    /**
     * A free text natural language name identifying the Profile.
     */
    protected StringType name;

    /**
     * Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    protected StringType publisher;

    /**
     * Contact details to assist a user in finding and communicating with the publisher.
     */
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * A free text natural language description of the profile and its use.
     */
    protected StringType description;

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
    protected BooleanType experimental;

    /**
     * The date that this version of the profile was published.
     */
    protected DateTimeType date;

    /**
     * The Scope and Usage that this profile was created to meet.
     */
    protected StringType requirements;

    /**
     * The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.
     */
    protected IdType fhirVersion;

    /**
     * An external specification that the content is mapped to.
     */
    protected List<ProfileMappingComponent> mapping = new ArrayList<ProfileMappingComponent>();

    /**
     * The Resource or Data type being described.
     */
    protected CodeType type;

    /**
     * The structure that is the base on which this set of constraints is derived from.
     */
    protected UriType base;

    /**
     * A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.
     */
    protected ConstraintComponent snapshot;

    /**
     * A differential view is expressed relative to the base profile - a statement of differences that it applies.
     */
    protected ConstraintComponent differential;

    /**
     * Additional search parameters defined for this structure that implementations can support and/or make use of.
     */
    protected List<ProfileSearchParamComponent> searchParam = new ArrayList<ProfileSearchParamComponent>();

    private static final long serialVersionUID = -407540206L;

    public Profile() {
      super();
    }

    public Profile(UriType url, StringType name, Enumeration<ResourceProfileStatus> status, CodeType type) {
      super();
      this.url = url;
      this.name = name;
      this.status = status;
      this.type = type;
    }

    /**
     * @return {@link #url} (The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public UriType getUrlElement() { 
      return this.url;
    }

    /**
     * @param value {@link #url} (The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.). This is the underlying object with id, value and extensions. The accessor "getUrl" gives direct access to the value
     */
    public Profile setUrlElement(UriType value) { 
      this.url = value;
      return this;
    }

    /**
     * @return The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    public String getUrl() { 
      return this.url == null ? null : this.url.getValue();
    }

    /**
     * @param value The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.
     */
    public Profile setUrl(String value) { 
        if (this.url == null)
          this.url = new UriType();
        this.url.setValue(value);
      return this;
    }

    /**
     * @return {@link #identifier} (Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public StringType getVersionElement() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.). This is the underlying object with id, value and extensions. The accessor "getVersion" gives direct access to the value
     */
    public Profile setVersionElement(StringType value) { 
      this.version = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.
     */
    public String getVersion() { 
      return this.version == null ? null : this.version.getValue();
    }

    /**
     * @param value The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.
     */
    public Profile setVersion(String value) { 
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
     * @return {@link #name} (A free text natural language name identifying the Profile.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A free text natural language name identifying the Profile.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public Profile setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A free text natural language name identifying the Profile.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A free text natural language name identifying the Profile.
     */
    public Profile setName(String value) { 
        if (this.name == null)
          this.name = new StringType();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public StringType getPublisherElement() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.). This is the underlying object with id, value and extensions. The accessor "getPublisher" gives direct access to the value
     */
    public Profile setPublisherElement(StringType value) { 
      this.publisher = value;
      return this;
    }

    /**
     * @return Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    public String getPublisher() { 
      return this.publisher == null ? null : this.publisher.getValue();
    }

    /**
     * @param value Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    public Profile setPublisher(String value) { 
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
     * @return {@link #description} (A free text natural language description of the profile and its use.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the profile and its use.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public Profile setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return A free text natural language description of the profile and its use.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value A free text natural language description of the profile and its use.
     */
    public Profile setDescription(String value) { 
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
     * @return {@link #code} (A set of terms from external terminologies that may be used to assist with indexing and searching of templates.)
     */
    public List<Coding> getCode() { 
      return this.code;
    }

    /**
     * @return {@link #code} (A set of terms from external terminologies that may be used to assist with indexing and searching of templates.)
     */
    // syntactic sugar
    public Coding addCode() { //3
      Coding t = new Coding();
      this.code.add(t);
      return t;
    }

    /**
     * @return {@link #status} (The status of the profile.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ResourceProfileStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The status of the profile.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Profile setStatusElement(Enumeration<ResourceProfileStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the profile.
     */
    public ResourceProfileStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the profile.
     */
    public Profile setStatus(ResourceProfileStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceProfileStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public BooleanType getExperimentalElement() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.). This is the underlying object with id, value and extensions. The accessor "getExperimental" gives direct access to the value
     */
    public Profile setExperimentalElement(BooleanType value) { 
      this.experimental = value;
      return this;
    }

    /**
     * @return This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public boolean getExperimental() { 
      return this.experimental == null ? false : this.experimental.getValue();
    }

    /**
     * @param value This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.
     */
    public Profile setExperimental(boolean value) { 
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
     * @return {@link #date} (The date that this version of the profile was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public DateTimeType getDateElement() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the profile was published.). This is the underlying object with id, value and extensions. The accessor "getDate" gives direct access to the value
     */
    public Profile setDateElement(DateTimeType value) { 
      this.date = value;
      return this;
    }

    /**
     * @return The date that this version of the profile was published.
     */
    public DateAndTime getDate() { 
      return this.date == null ? null : this.date.getValue();
    }

    /**
     * @param value The date that this version of the profile was published.
     */
    public Profile setDate(DateAndTime value) { 
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
     * @return {@link #requirements} (The Scope and Usage that this profile was created to meet.). This is the underlying object with id, value and extensions. The accessor "getRequirements" gives direct access to the value
     */
    public StringType getRequirementsElement() { 
      return this.requirements;
    }

    /**
     * @param value {@link #requirements} (The Scope and Usage that this profile was created to meet.). This is the underlying object with id, value and extensions. The accessor "getRequirements" gives direct access to the value
     */
    public Profile setRequirementsElement(StringType value) { 
      this.requirements = value;
      return this;
    }

    /**
     * @return The Scope and Usage that this profile was created to meet.
     */
    public String getRequirements() { 
      return this.requirements == null ? null : this.requirements.getValue();
    }

    /**
     * @param value The Scope and Usage that this profile was created to meet.
     */
    public Profile setRequirements(String value) { 
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
     * @return {@link #fhirVersion} (The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.). This is the underlying object with id, value and extensions. The accessor "getFhirVersion" gives direct access to the value
     */
    public IdType getFhirVersionElement() { 
      return this.fhirVersion;
    }

    /**
     * @param value {@link #fhirVersion} (The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.). This is the underlying object with id, value and extensions. The accessor "getFhirVersion" gives direct access to the value
     */
    public Profile setFhirVersionElement(IdType value) { 
      this.fhirVersion = value;
      return this;
    }

    /**
     * @return The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.
     */
    public String getFhirVersion() { 
      return this.fhirVersion == null ? null : this.fhirVersion.getValue();
    }

    /**
     * @param value The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.
     */
    public Profile setFhirVersion(String value) { 
      if (Utilities.noString(value))
        this.fhirVersion = null;
      else {
        if (this.fhirVersion == null)
          this.fhirVersion = new IdType();
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

    /**
     * @return {@link #mapping} (An external specification that the content is mapped to.)
     */
    // syntactic sugar
    public ProfileMappingComponent addMapping() { //3
      ProfileMappingComponent t = new ProfileMappingComponent();
      this.mapping.add(t);
      return t;
    }

    /**
     * @return {@link #type} (The Resource or Data type being described.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public CodeType getTypeElement() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The Resource or Data type being described.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
     */
    public Profile setTypeElement(CodeType value) { 
      this.type = value;
      return this;
    }

    /**
     * @return The Resource or Data type being described.
     */
    public String getType() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value The Resource or Data type being described.
     */
    public Profile setType(String value) { 
        if (this.type == null)
          this.type = new CodeType();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #base} (The structure that is the base on which this set of constraints is derived from.). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public UriType getBaseElement() { 
      return this.base;
    }

    /**
     * @param value {@link #base} (The structure that is the base on which this set of constraints is derived from.). This is the underlying object with id, value and extensions. The accessor "getBase" gives direct access to the value
     */
    public Profile setBaseElement(UriType value) { 
      this.base = value;
      return this;
    }

    /**
     * @return The structure that is the base on which this set of constraints is derived from.
     */
    public String getBase() { 
      return this.base == null ? null : this.base.getValue();
    }

    /**
     * @param value The structure that is the base on which this set of constraints is derived from.
     */
    public Profile setBase(String value) { 
      if (Utilities.noString(value))
        this.base = null;
      else {
        if (this.base == null)
          this.base = new UriType();
        this.base.setValue(value);
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
    public Profile setSnapshot(ConstraintComponent value) { 
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
    public Profile setDifferential(ConstraintComponent value) { 
      this.differential = value;
      return this;
    }

    /**
     * @return {@link #searchParam} (Additional search parameters defined for this structure that implementations can support and/or make use of.)
     */
    public List<ProfileSearchParamComponent> getSearchParam() { 
      return this.searchParam;
    }

    /**
     * @return {@link #searchParam} (Additional search parameters defined for this structure that implementations can support and/or make use of.)
     */
    // syntactic sugar
    public ProfileSearchParamComponent addSearchParam() { //3
      ProfileSearchParamComponent t = new ProfileSearchParamComponent();
      this.searchParam.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("url", "uri", "The URL at which this profile is (or will be) published, and which is used to reference this profile in extension urls and tag values in operational FHIR systems.", 0, java.lang.Integer.MAX_VALUE, url));
        childrenList.add(new Property("identifier", "Identifier", "Formal identifier that is used to identify this profile when it is represented in other formats, or referenced in a specification, model, design or an instance  (should be globally unique OID, UUID, or URI), (if it's not possible to use the literal URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("name", "string", "A free text natural language name identifying the Profile.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the profile.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "ContactPoint", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the profile and its use.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("code", "Coding", "A set of terms from external terminologies that may be used to assist with indexing and searching of templates.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("status", "code", "The status of the profile.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the profile was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("requirements", "string", "The Scope and Usage that this profile was created to meet.", 0, java.lang.Integer.MAX_VALUE, requirements));
        childrenList.add(new Property("fhirVersion", "id", "The version of the FHIR specification on which this profile is based - this is the formal version of the specification, without the revision number, e.g. [publication].[major].[minor], which is 0.3.0 for this version.", 0, java.lang.Integer.MAX_VALUE, fhirVersion));
        childrenList.add(new Property("mapping", "", "An external specification that the content is mapped to.", 0, java.lang.Integer.MAX_VALUE, mapping));
        childrenList.add(new Property("type", "code", "The Resource or Data type being described.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("base", "uri", "The structure that is the base on which this set of constraints is derived from.", 0, java.lang.Integer.MAX_VALUE, base));
        childrenList.add(new Property("snapshot", "", "A snapshot view is expressed in a stand alone form that can be used and interpreted without considering the base profile.", 0, java.lang.Integer.MAX_VALUE, snapshot));
        childrenList.add(new Property("differential", "@Profile.snapshot", "A differential view is expressed relative to the base profile - a statement of differences that it applies.", 0, java.lang.Integer.MAX_VALUE, differential));
        childrenList.add(new Property("searchParam", "", "Additional search parameters defined for this structure that implementations can support and/or make use of.", 0, java.lang.Integer.MAX_VALUE, searchParam));
      }

      public Profile copy() {
        Profile dst = new Profile();
        copyValues(dst);
        dst.url = url == null ? null : url.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.version = version == null ? null : version.copy();
        dst.name = name == null ? null : name.copy();
        dst.publisher = publisher == null ? null : publisher.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
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
        dst.type = type == null ? null : type.copy();
        dst.base = base == null ? null : base.copy();
        dst.snapshot = snapshot == null ? null : snapshot.copy();
        dst.differential = differential == null ? null : differential.copy();
        dst.searchParam = new ArrayList<ProfileSearchParamComponent>();
        for (ProfileSearchParamComponent i : searchParam)
          dst.searchParam.add(i.copy());
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

