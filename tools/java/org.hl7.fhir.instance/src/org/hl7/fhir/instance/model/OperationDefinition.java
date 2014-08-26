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
 * A formal computable definition of an operation (on the RESTful interface) or a named query (using the search interaction).
 */
public class OperationDefinition extends Resource {

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

    public enum OperationKind {
        operation, // This operation is invoked as an operation.
        query, // This operation is a named query, invoked using the search mechanism.
        Null; // added to help the parsers
        public static OperationKind fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("operation".equals(codeString))
          return operation;
        if ("query".equals(codeString))
          return query;
        throw new Exception("Unknown OperationKind code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case operation: return "operation";
            case query: return "query";
            default: return "?";
          }
        }
    }

  public static class OperationKindEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("operation".equals(codeString))
          return OperationKind.operation;
        if ("query".equals(codeString))
          return OperationKind.query;
        throw new Exception("Unknown OperationKind code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == OperationKind.operation)
        return "operation";
      if (code == OperationKind.query)
        return "query";
      return "?";
      }
    }

    public enum OperationParameterUse {
        in, // This is an input parameter.
        out, // This is an output parameter.
        Null; // added to help the parsers
        public static OperationParameterUse fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in".equals(codeString))
          return in;
        if ("out".equals(codeString))
          return out;
        throw new Exception("Unknown OperationParameterUse code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case in: return "in";
            case out: return "out";
            default: return "?";
          }
        }
    }

  public static class OperationParameterUseEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in".equals(codeString))
          return OperationParameterUse.in;
        if ("out".equals(codeString))
          return OperationParameterUse.out;
        throw new Exception("Unknown OperationParameterUse code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == OperationParameterUse.in)
        return "in";
      if (code == OperationParameterUse.out)
        return "out";
      return "?";
      }
    }

    public static class OperationDefinitionParameterComponent extends BackboneElement {
        /**
         * The name of used to identify the parameter.
         */
        protected CodeType name;

        /**
         * Whether this is an input or an output parameter.
         */
        protected Enumeration<OperationParameterUse> use;

        /**
         * The minimum number of times this parameter SHALL appear in the request or response.
         */
        protected IntegerType min;

        /**
         * The maximum number of times this element is permitted to appear in the request or response.
         */
        protected StringType max;

        /**
         * Describes the meaning or use of this parameter.
         */
        protected StringType documentation;

        /**
         * The type for this parameter.
         */
        protected Coding type;

        /**
         * A profile the specifies the rules that this parameter must conform to.
         */
        protected ResourceReference profile;

        /**
         * The actual object that is the target of the reference (A profile the specifies the rules that this parameter must conform to.)
         */
        protected Profile profileTarget;

        private static final long serialVersionUID = 1779939130L;

      public OperationDefinitionParameterComponent() {
        super();
      }

      public OperationDefinitionParameterComponent(CodeType name, Enumeration<OperationParameterUse> use, IntegerType min, StringType max, Coding type) {
        super();
        this.name = name;
        this.use = use;
        this.min = min;
        this.max = max;
        this.type = type;
      }

        /**
         * @return {@link #name} (The name of used to identify the parameter.)
         */
        public CodeType getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (The name of used to identify the parameter.)
         */
        public OperationDefinitionParameterComponent setName(CodeType value) { 
          this.name = value;
          return this;
        }

        /**
         * @return The name of used to identify the parameter.
         */
        public String getNameSimple() { 
          return this.name == null ? null : this.name.getValue();
        }

        /**
         * @param value The name of used to identify the parameter.
         */
        public OperationDefinitionParameterComponent setNameSimple(String value) { 
            if (this.name == null)
              this.name = new CodeType();
            this.name.setValue(value);
          return this;
        }

        /**
         * @return {@link #use} (Whether this is an input or an output parameter.)
         */
        public Enumeration<OperationParameterUse> getUse() { 
          return this.use;
        }

        /**
         * @param value {@link #use} (Whether this is an input or an output parameter.)
         */
        public OperationDefinitionParameterComponent setUse(Enumeration<OperationParameterUse> value) { 
          this.use = value;
          return this;
        }

        /**
         * @return Whether this is an input or an output parameter.
         */
        public OperationParameterUse getUseSimple() { 
          return this.use == null ? null : this.use.getValue();
        }

        /**
         * @param value Whether this is an input or an output parameter.
         */
        public OperationDefinitionParameterComponent setUseSimple(OperationParameterUse value) { 
            if (this.use == null)
              this.use = new Enumeration<OperationParameterUse>();
            this.use.setValue(value);
          return this;
        }

        /**
         * @return {@link #min} (The minimum number of times this parameter SHALL appear in the request or response.)
         */
        public IntegerType getMin() { 
          return this.min;
        }

        /**
         * @param value {@link #min} (The minimum number of times this parameter SHALL appear in the request or response.)
         */
        public OperationDefinitionParameterComponent setMin(IntegerType value) { 
          this.min = value;
          return this;
        }

        /**
         * @return The minimum number of times this parameter SHALL appear in the request or response.
         */
        public int getMinSimple() { 
          return this.min == null ? null : this.min.getValue();
        }

        /**
         * @param value The minimum number of times this parameter SHALL appear in the request or response.
         */
        public OperationDefinitionParameterComponent setMinSimple(int value) { 
            if (this.min == null)
              this.min = new IntegerType();
            this.min.setValue(value);
          return this;
        }

        /**
         * @return {@link #max} (The maximum number of times this element is permitted to appear in the request or response.)
         */
        public StringType getMax() { 
          return this.max;
        }

        /**
         * @param value {@link #max} (The maximum number of times this element is permitted to appear in the request or response.)
         */
        public OperationDefinitionParameterComponent setMax(StringType value) { 
          this.max = value;
          return this;
        }

        /**
         * @return The maximum number of times this element is permitted to appear in the request or response.
         */
        public String getMaxSimple() { 
          return this.max == null ? null : this.max.getValue();
        }

        /**
         * @param value The maximum number of times this element is permitted to appear in the request or response.
         */
        public OperationDefinitionParameterComponent setMaxSimple(String value) { 
            if (this.max == null)
              this.max = new StringType();
            this.max.setValue(value);
          return this;
        }

        /**
         * @return {@link #documentation} (Describes the meaning or use of this parameter.)
         */
        public StringType getDocumentation() { 
          return this.documentation;
        }

        /**
         * @param value {@link #documentation} (Describes the meaning or use of this parameter.)
         */
        public OperationDefinitionParameterComponent setDocumentation(StringType value) { 
          this.documentation = value;
          return this;
        }

        /**
         * @return Describes the meaning or use of this parameter.
         */
        public String getDocumentationSimple() { 
          return this.documentation == null ? null : this.documentation.getValue();
        }

        /**
         * @param value Describes the meaning or use of this parameter.
         */
        public OperationDefinitionParameterComponent setDocumentationSimple(String value) { 
          if (value == null)
            this.documentation = null;
          else {
            if (this.documentation == null)
              this.documentation = new StringType();
            this.documentation.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (The type for this parameter.)
         */
        public Coding getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type for this parameter.)
         */
        public OperationDefinitionParameterComponent setType(Coding value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #profile} (A profile the specifies the rules that this parameter must conform to.)
         */
        public ResourceReference getProfile() { 
          return this.profile;
        }

        /**
         * @param value {@link #profile} (A profile the specifies the rules that this parameter must conform to.)
         */
        public OperationDefinitionParameterComponent setProfile(ResourceReference value) { 
          this.profile = value;
          return this;
        }

        /**
         * @return {@link #profile} (The actual object that is the target of the reference. A profile the specifies the rules that this parameter must conform to.)
         */
        public Profile getProfileTarget() { 
          return this.profileTarget;
        }

        /**
         * @param value {@link #profile} (The actual object that is the target of the reference. A profile the specifies the rules that this parameter must conform to.)
         */
        public OperationDefinitionParameterComponent setProfileTarget(Profile value) { 
          this.profileTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "code", "The name of used to identify the parameter.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("use", "code", "Whether this is an input or an output parameter.", 0, java.lang.Integer.MAX_VALUE, use));
          childrenList.add(new Property("min", "integer", "The minimum number of times this parameter SHALL appear in the request or response.", 0, java.lang.Integer.MAX_VALUE, min));
          childrenList.add(new Property("max", "string", "The maximum number of times this element is permitted to appear in the request or response.", 0, java.lang.Integer.MAX_VALUE, max));
          childrenList.add(new Property("documentation", "string", "Describes the meaning or use of this parameter.", 0, java.lang.Integer.MAX_VALUE, documentation));
          childrenList.add(new Property("type", "Coding", "The type for this parameter.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("profile", "Resource(Profile)", "A profile the specifies the rules that this parameter must conform to.", 0, java.lang.Integer.MAX_VALUE, profile));
        }

      public OperationDefinitionParameterComponent copy() {
        OperationDefinitionParameterComponent dst = new OperationDefinitionParameterComponent();
        dst.name = name == null ? null : name.copy();
        dst.use = use == null ? null : use.copy();
        dst.min = min == null ? null : min.copy();
        dst.max = max == null ? null : max.copy();
        dst.documentation = documentation == null ? null : documentation.copy();
        dst.type = type == null ? null : type.copy();
        dst.profile = profile == null ? null : profile.copy();
        return dst;
      }

  }

    /**
     * The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    protected UriType identifier;

    /**
     * The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.
     */
    protected StringType version;

    /**
     * A free text natural language name identifying the Profile.
     */
    protected StringType title;

    /**
     * Details of the individual or organization who accepts responsibility for publishing the profile.
     */
    protected StringType publisher;

    /**
     * Contact details to assist a user in finding and communicating with the publisher.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

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
     * Whether this is operation or named query.
     */
    protected Enumeration<OperationKind> kind;

    /**
     * The name used to invoke the operation.
     */
    protected CodeType name;

    /**
     * Additional information about how to use this operation or named query.
     */
    protected StringType notes;

    /**
     * Indicates that this operation definition is a constraining profile on the base.
     */
    protected ResourceReference base;

    /**
     * The actual object that is the target of the reference (Indicates that this operation definition is a constraining profile on the base.)
     */
    protected OperationDefinition baseTarget;

    /**
     * Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).
     */
    protected BooleanType system;

    /**
     * Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).
     */
    protected List<CodeType> type = new ArrayList<CodeType>();

    /**
     * Indicates whether this operation can be invoked on a particular instance of one of the given types.
     */
    protected BooleanType instance;

    /**
     * Parameters for the operation/query.
     */
    protected List<OperationDefinitionParameterComponent> parameter = new ArrayList<OperationDefinitionParameterComponent>();

    private static final long serialVersionUID = 418692053L;

    public OperationDefinition() {
      super();
    }

    public OperationDefinition(StringType title, Enumeration<ResourceProfileStatus> status, Enumeration<OperationKind> kind, CodeType name, BooleanType system, BooleanType instance) {
      super();
      this.title = title;
      this.status = status;
      this.kind = kind;
      this.name = name;
      this.system = system;
      this.instance = instance;
    }

    /**
     * @return {@link #identifier} (The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public UriType getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).)
     */
    public OperationDefinition setIdentifier(UriType value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public String getIdentifierSimple() { 
      return this.identifier == null ? null : this.identifier.getValue();
    }

    /**
     * @param value The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).
     */
    public OperationDefinition setIdentifierSimple(String value) { 
      if (value == null)
        this.identifier = null;
      else {
        if (this.identifier == null)
          this.identifier = new UriType();
        this.identifier.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public StringType getVersion() { 
      return this.version;
    }

    /**
     * @param value {@link #version} (The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.)
     */
    public OperationDefinition setVersion(StringType value) { 
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
    public OperationDefinition setVersionSimple(String value) { 
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
     * @return {@link #title} (A free text natural language name identifying the Profile.)
     */
    public StringType getTitle() { 
      return this.title;
    }

    /**
     * @param value {@link #title} (A free text natural language name identifying the Profile.)
     */
    public OperationDefinition setTitle(StringType value) { 
      this.title = value;
      return this;
    }

    /**
     * @return A free text natural language name identifying the Profile.
     */
    public String getTitleSimple() { 
      return this.title == null ? null : this.title.getValue();
    }

    /**
     * @param value A free text natural language name identifying the Profile.
     */
    public OperationDefinition setTitleSimple(String value) { 
        if (this.title == null)
          this.title = new StringType();
        this.title.setValue(value);
      return this;
    }

    /**
     * @return {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.)
     */
    public StringType getPublisher() { 
      return this.publisher;
    }

    /**
     * @param value {@link #publisher} (Details of the individual or organization who accepts responsibility for publishing the profile.)
     */
    public OperationDefinition setPublisher(StringType value) { 
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
    public OperationDefinition setPublisherSimple(String value) { 
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
    public StringType getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (A free text natural language description of the profile and its use.)
     */
    public OperationDefinition setDescription(StringType value) { 
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
    public OperationDefinition setDescriptionSimple(String value) { 
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
    public OperationDefinition setStatus(Enumeration<ResourceProfileStatus> value) { 
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
    public OperationDefinition setStatusSimple(ResourceProfileStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ResourceProfileStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public BooleanType getExperimental() { 
      return this.experimental;
    }

    /**
     * @param value {@link #experimental} (This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.)
     */
    public OperationDefinition setExperimental(BooleanType value) { 
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
    public OperationDefinition setExperimentalSimple(boolean value) { 
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
     * @return {@link #date} (The date that this version of the profile was published.)
     */
    public DateTimeType getDate() { 
      return this.date;
    }

    /**
     * @param value {@link #date} (The date that this version of the profile was published.)
     */
    public OperationDefinition setDate(DateTimeType value) { 
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
    public OperationDefinition setDateSimple(DateAndTime value) { 
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
     * @return {@link #kind} (Whether this is operation or named query.)
     */
    public Enumeration<OperationKind> getKind() { 
      return this.kind;
    }

    /**
     * @param value {@link #kind} (Whether this is operation or named query.)
     */
    public OperationDefinition setKind(Enumeration<OperationKind> value) { 
      this.kind = value;
      return this;
    }

    /**
     * @return Whether this is operation or named query.
     */
    public OperationKind getKindSimple() { 
      return this.kind == null ? null : this.kind.getValue();
    }

    /**
     * @param value Whether this is operation or named query.
     */
    public OperationDefinition setKindSimple(OperationKind value) { 
        if (this.kind == null)
          this.kind = new Enumeration<OperationKind>();
        this.kind.setValue(value);
      return this;
    }

    /**
     * @return {@link #name} (The name used to invoke the operation.)
     */
    public CodeType getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The name used to invoke the operation.)
     */
    public OperationDefinition setName(CodeType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The name used to invoke the operation.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The name used to invoke the operation.
     */
    public OperationDefinition setNameSimple(String value) { 
        if (this.name == null)
          this.name = new CodeType();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #notes} (Additional information about how to use this operation or named query.)
     */
    public StringType getNotes() { 
      return this.notes;
    }

    /**
     * @param value {@link #notes} (Additional information about how to use this operation or named query.)
     */
    public OperationDefinition setNotes(StringType value) { 
      this.notes = value;
      return this;
    }

    /**
     * @return Additional information about how to use this operation or named query.
     */
    public String getNotesSimple() { 
      return this.notes == null ? null : this.notes.getValue();
    }

    /**
     * @param value Additional information about how to use this operation or named query.
     */
    public OperationDefinition setNotesSimple(String value) { 
      if (value == null)
        this.notes = null;
      else {
        if (this.notes == null)
          this.notes = new StringType();
        this.notes.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #base} (Indicates that this operation definition is a constraining profile on the base.)
     */
    public ResourceReference getBase() { 
      return this.base;
    }

    /**
     * @param value {@link #base} (Indicates that this operation definition is a constraining profile on the base.)
     */
    public OperationDefinition setBase(ResourceReference value) { 
      this.base = value;
      return this;
    }

    /**
     * @return {@link #base} (The actual object that is the target of the reference. Indicates that this operation definition is a constraining profile on the base.)
     */
    public OperationDefinition getBaseTarget() { 
      return this.baseTarget;
    }

    /**
     * @param value {@link #base} (The actual object that is the target of the reference. Indicates that this operation definition is a constraining profile on the base.)
     */
    public OperationDefinition setBaseTarget(OperationDefinition value) { 
      this.baseTarget = value;
      return this;
    }

    /**
     * @return {@link #system} (Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).)
     */
    public BooleanType getSystem() { 
      return this.system;
    }

    /**
     * @param value {@link #system} (Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).)
     */
    public OperationDefinition setSystem(BooleanType value) { 
      this.system = value;
      return this;
    }

    /**
     * @return Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).
     */
    public boolean getSystemSimple() { 
      return this.system == null ? false : this.system.getValue();
    }

    /**
     * @param value Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).
     */
    public OperationDefinition setSystemSimple(boolean value) { 
        if (this.system == null)
          this.system = new BooleanType();
        this.system.setValue(value);
      return this;
    }

    /**
     * @return {@link #type} (Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).)
     */
    public List<CodeType> getType() { 
      return this.type;
    }

    // syntactic sugar
    /**
     * @return {@link #type} (Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).)
     */
    public CodeType addType() { 
      CodeType t = new CodeType();
      this.type.add(t);
      return t;
    }

    /**
     * @param value {@link #type} (Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).)
     */
    public CodeType addTypeSimple(String value) { 
      CodeType t = new CodeType();
      t.setValue(value);
      this.type.add(t);
      return t;
    }

    /**
     * @param value {@link #type} (Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).)
     */
    public boolean hasTypeSimple(String value) { 
      for (CodeType v : this.type)
        if (v.getValue().equals(value))
          return true;
      return false;
    }

    /**
     * @return {@link #instance} (Indicates whether this operation can be invoked on a particular instance of one of the given types.)
     */
    public BooleanType getInstance() { 
      return this.instance;
    }

    /**
     * @param value {@link #instance} (Indicates whether this operation can be invoked on a particular instance of one of the given types.)
     */
    public OperationDefinition setInstance(BooleanType value) { 
      this.instance = value;
      return this;
    }

    /**
     * @return Indicates whether this operation can be invoked on a particular instance of one of the given types.
     */
    public boolean getInstanceSimple() { 
      return this.instance == null ? false : this.instance.getValue();
    }

    /**
     * @param value Indicates whether this operation can be invoked on a particular instance of one of the given types.
     */
    public OperationDefinition setInstanceSimple(boolean value) { 
        if (this.instance == null)
          this.instance = new BooleanType();
        this.instance.setValue(value);
      return this;
    }

    /**
     * @return {@link #parameter} (Parameters for the operation/query.)
     */
    public List<OperationDefinitionParameterComponent> getParameter() { 
      return this.parameter;
    }

    // syntactic sugar
    /**
     * @return {@link #parameter} (Parameters for the operation/query.)
     */
    public OperationDefinitionParameterComponent addParameter() { 
      OperationDefinitionParameterComponent t = new OperationDefinitionParameterComponent();
      this.parameter.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "uri", "The identifier that is used to identify this operation definition when it is referenced in a specification, model, design or an instance (should be globally unique OID, UUID, or URI).", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("version", "string", "The identifier that is used to identify this version of the profile when it is referenced in a specification, model, design or instance. This is an arbitrary value managed by the profile author manually and the value should be a timestamp.", 0, java.lang.Integer.MAX_VALUE, version));
        childrenList.add(new Property("title", "string", "A free text natural language name identifying the Profile.", 0, java.lang.Integer.MAX_VALUE, title));
        childrenList.add(new Property("publisher", "string", "Details of the individual or organization who accepts responsibility for publishing the profile.", 0, java.lang.Integer.MAX_VALUE, publisher));
        childrenList.add(new Property("telecom", "Contact", "Contact details to assist a user in finding and communicating with the publisher.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("description", "string", "A free text natural language description of the profile and its use.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("code", "Coding", "A set of terms from external terminologies that may be used to assist with indexing and searching of templates.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("status", "code", "The status of the profile.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("experimental", "boolean", "This profile was authored for testing purposes (or education/evaluation/marketing), and is not intended to be used for genuine usage.", 0, java.lang.Integer.MAX_VALUE, experimental));
        childrenList.add(new Property("date", "dateTime", "The date that this version of the profile was published.", 0, java.lang.Integer.MAX_VALUE, date));
        childrenList.add(new Property("kind", "code", "Whether this is operation or named query.", 0, java.lang.Integer.MAX_VALUE, kind));
        childrenList.add(new Property("name", "code", "The name used to invoke the operation.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("notes", "string", "Additional information about how to use this operation or named query.", 0, java.lang.Integer.MAX_VALUE, notes));
        childrenList.add(new Property("base", "Resource(OperationDefinition)", "Indicates that this operation definition is a constraining profile on the base.", 0, java.lang.Integer.MAX_VALUE, base));
        childrenList.add(new Property("system", "boolean", "Indicates whether this operation or named query can be invoked at the system level (e.g. without needing to choose a resource type for the context).", 0, java.lang.Integer.MAX_VALUE, system));
        childrenList.add(new Property("type", "code", "Indicates whether this operation or named query can be invoked at the resource type level for any given resource type level (e.g. without needing to choose a resource type for the context).", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("instance", "boolean", "Indicates whether this operation can be invoked on a particular instance of one of the given types.", 0, java.lang.Integer.MAX_VALUE, instance));
        childrenList.add(new Property("parameter", "", "Parameters for the operation/query.", 0, java.lang.Integer.MAX_VALUE, parameter));
      }

      public OperationDefinition copy() {
        OperationDefinition dst = new OperationDefinition();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.version = version == null ? null : version.copy();
        dst.title = title == null ? null : title.copy();
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
        dst.kind = kind == null ? null : kind.copy();
        dst.name = name == null ? null : name.copy();
        dst.notes = notes == null ? null : notes.copy();
        dst.base = base == null ? null : base.copy();
        dst.system = system == null ? null : system.copy();
        dst.type = new ArrayList<CodeType>();
        for (CodeType i : type)
          dst.type.add(i.copy());
        dst.instance = instance == null ? null : instance.copy();
        dst.parameter = new ArrayList<OperationDefinitionParameterComponent>();
        for (OperationDefinitionParameterComponent i : parameter)
          dst.parameter.add(i.copy());
        return dst;
      }

      protected OperationDefinition typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.OperationDefinition;
   }


}

