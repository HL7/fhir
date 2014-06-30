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
 * A curated namespace that issues unique symbols within that namespace for the identification of concepts, people, devices, etc.  Represents a "System" used within the Identifier and Coding data types.
 */
public class Namespace extends Resource {

    public enum NamespaceType {
        codesystem, // The namespace is used to define concepts and symbols to represent those concepts.  E.g. UCUM, LOINC, NDC code, local lab codes, etc.
        identifier, // The namespace is used to manage identifiers (e.g. license numbers, order numbers, etc.).
        root, // The namespace is used as the root for other identifiers and namespaces.
        Null; // added to help the parsers
        public static NamespaceType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("codesystem".equals(codeString))
          return codesystem;
        if ("identifier".equals(codeString))
          return identifier;
        if ("root".equals(codeString))
          return root;
        throw new Exception("Unknown NamespaceType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case codesystem: return "codesystem";
            case identifier: return "identifier";
            case root: return "root";
            default: return "?";
          }
        }
    }

  public static class NamespaceTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("codesystem".equals(codeString))
          return NamespaceType.codesystem;
        if ("identifier".equals(codeString))
          return NamespaceType.identifier;
        if ("root".equals(codeString))
          return NamespaceType.root;
        throw new Exception("Unknown NamespaceType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NamespaceType.codesystem)
        return "codesystem";
      if (code == NamespaceType.identifier)
        return "identifier";
      if (code == NamespaceType.root)
        return "root";
      return "?";
      }
    }

    public enum NamespaceStatus {
        proposed, // System has been submitted but not yet approved.
        active, // System is valid for use.
        retired, // System should no longer be used.
        Null; // added to help the parsers
        public static NamespaceStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return proposed;
        if ("active".equals(codeString))
          return active;
        if ("retired".equals(codeString))
          return retired;
        throw new Exception("Unknown NamespaceStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case proposed: return "proposed";
            case active: return "active";
            case retired: return "retired";
            default: return "?";
          }
        }
    }

  public static class NamespaceStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposed".equals(codeString))
          return NamespaceStatus.proposed;
        if ("active".equals(codeString))
          return NamespaceStatus.active;
        if ("retired".equals(codeString))
          return NamespaceStatus.retired;
        throw new Exception("Unknown NamespaceStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NamespaceStatus.proposed)
        return "proposed";
      if (code == NamespaceStatus.active)
        return "active";
      if (code == NamespaceStatus.retired)
        return "retired";
      return "?";
      }
    }

    public enum NamespaceIdentifierType {
        oid, // An ISO object identifier.  E.g. 1.2.3.4.5.
        uuid, // A universally unique identifier of the form a5afddf4-e880-459b-876e-e4591b0acc11.
        uri, // A uniform resource identifier (ideally a URL - uniform resource locator).  E.g. http://unitsofmeasure.org.
        other, // Some other type of unique identifier.  E.g HL7-assigned reserved string such as LN for LOINC.
        Null; // added to help the parsers
        public static NamespaceIdentifierType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("oid".equals(codeString))
          return oid;
        if ("uuid".equals(codeString))
          return uuid;
        if ("uri".equals(codeString))
          return uri;
        if ("other".equals(codeString))
          return other;
        throw new Exception("Unknown NamespaceIdentifierType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case oid: return "oid";
            case uuid: return "uuid";
            case uri: return "uri";
            case other: return "other";
            default: return "?";
          }
        }
    }

  public static class NamespaceIdentifierTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("oid".equals(codeString))
          return NamespaceIdentifierType.oid;
        if ("uuid".equals(codeString))
          return NamespaceIdentifierType.uuid;
        if ("uri".equals(codeString))
          return NamespaceIdentifierType.uri;
        if ("other".equals(codeString))
          return NamespaceIdentifierType.other;
        throw new Exception("Unknown NamespaceIdentifierType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == NamespaceIdentifierType.oid)
        return "oid";
      if (code == NamespaceIdentifierType.uuid)
        return "uuid";
      if (code == NamespaceIdentifierType.uri)
        return "uri";
      if (code == NamespaceIdentifierType.other)
        return "other";
      return "?";
      }
    }

    public static class NamespaceUniqueIdComponent extends BackboneElement {
        /**
         * Identifies the unique identifier scheme used for this particular identifier.
         */
        protected Enumeration<NamespaceIdentifierType> type;

        /**
         * The string that should be sent over the wire to identify the code system or identifier system.
         */
        protected String_ value;

        /**
         * Indicates whether this identifier is the "preferred" identifier of this type.
         */
        protected Boolean preferred;

        /**
         * Identifies the period of time over which this identifier is considered appropriate to refer to the namespace.  Outside of this window, the identifier might be non-deterministic.
         */
        protected Period period;

        private static final long serialVersionUID = -1352328709L;

      public NamespaceUniqueIdComponent() {
        super();
      }

      public NamespaceUniqueIdComponent(Enumeration<NamespaceIdentifierType> type, String_ value) {
        super();
        this.type = type;
        this.value = value;
      }

        /**
         * @return {@link #type} (Identifies the unique identifier scheme used for this particular identifier.)
         */
        public Enumeration<NamespaceIdentifierType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Identifies the unique identifier scheme used for this particular identifier.)
         */
        public NamespaceUniqueIdComponent setType(Enumeration<NamespaceIdentifierType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return Identifies the unique identifier scheme used for this particular identifier.
         */
        public NamespaceIdentifierType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value Identifies the unique identifier scheme used for this particular identifier.
         */
        public NamespaceUniqueIdComponent setTypeSimple(NamespaceIdentifierType value) { 
            if (this.type == null)
              this.type = new Enumeration<NamespaceIdentifierType>();
            this.type.setValue(value);
          return this;
        }

        /**
         * @return {@link #value} (The string that should be sent over the wire to identify the code system or identifier system.)
         */
        public String_ getValue() { 
          return this.value;
        }

        /**
         * @param value {@link #value} (The string that should be sent over the wire to identify the code system or identifier system.)
         */
        public NamespaceUniqueIdComponent setValue(String_ value) { 
          this.value = value;
          return this;
        }

        /**
         * @return The string that should be sent over the wire to identify the code system or identifier system.
         */
        public String getValueSimple() { 
          return this.value == null ? null : this.value.getValue();
        }

        /**
         * @param value The string that should be sent over the wire to identify the code system or identifier system.
         */
        public NamespaceUniqueIdComponent setValueSimple(String value) { 
            if (this.value == null)
              this.value = new String_();
            this.value.setValue(value);
          return this;
        }

        /**
         * @return {@link #preferred} (Indicates whether this identifier is the "preferred" identifier of this type.)
         */
        public Boolean getPreferred() { 
          return this.preferred;
        }

        /**
         * @param value {@link #preferred} (Indicates whether this identifier is the "preferred" identifier of this type.)
         */
        public NamespaceUniqueIdComponent setPreferred(Boolean value) { 
          this.preferred = value;
          return this;
        }

        /**
         * @return Indicates whether this identifier is the "preferred" identifier of this type.
         */
        public boolean getPreferredSimple() { 
          return this.preferred == null ? false : this.preferred.getValue();
        }

        /**
         * @param value Indicates whether this identifier is the "preferred" identifier of this type.
         */
        public NamespaceUniqueIdComponent setPreferredSimple(boolean value) { 
          if (value == false)
            this.preferred = null;
          else {
            if (this.preferred == null)
              this.preferred = new Boolean();
            this.preferred.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #period} (Identifies the period of time over which this identifier is considered appropriate to refer to the namespace.  Outside of this window, the identifier might be non-deterministic.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (Identifies the period of time over which this identifier is considered appropriate to refer to the namespace.  Outside of this window, the identifier might be non-deterministic.)
         */
        public NamespaceUniqueIdComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "code", "Identifies the unique identifier scheme used for this particular identifier.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("value", "string", "The string that should be sent over the wire to identify the code system or identifier system.", 0, java.lang.Integer.MAX_VALUE, value));
          childrenList.add(new Property("preferred", "boolean", "Indicates whether this identifier is the 'preferred' identifier of this type.", 0, java.lang.Integer.MAX_VALUE, preferred));
          childrenList.add(new Property("period", "Period", "Identifies the period of time over which this identifier is considered appropriate to refer to the namespace.  Outside of this window, the identifier might be non-deterministic.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public NamespaceUniqueIdComponent copy() {
        NamespaceUniqueIdComponent dst = new NamespaceUniqueIdComponent();
        dst.type = type == null ? null : type.copy();
        dst.value = value == null ? null : value.copy();
        dst.preferred = preferred == null ? null : preferred.copy();
        dst.period = period == null ? null : period.copy();
        return dst;
      }

  }

    public static class NamespaceContactComponent extends BackboneElement {
        /**
         * Names of the person who can be contacted.
         */
        protected HumanName name;

        /**
         * Identifies the mechanism(s) by which they can be contacted.
         */
        protected List<Contact> telecom = new ArrayList<Contact>();

        private static final long serialVersionUID = -1469576513L;

      public NamespaceContactComponent() {
        super();
      }

        /**
         * @return {@link #name} (Names of the person who can be contacted.)
         */
        public HumanName getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (Names of the person who can be contacted.)
         */
        public NamespaceContactComponent setName(HumanName value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #telecom} (Identifies the mechanism(s) by which they can be contacted.)
         */
        public List<Contact> getTelecom() { 
          return this.telecom;
        }

    // syntactic sugar
        /**
         * @return {@link #telecom} (Identifies the mechanism(s) by which they can be contacted.)
         */
        public Contact addTelecom() { 
          Contact t = new Contact();
          this.telecom.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("name", "HumanName", "Names of the person who can be contacted.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("telecom", "Contact", "Identifies the mechanism(s) by which they can be contacted.", 0, java.lang.Integer.MAX_VALUE, telecom));
        }

      public NamespaceContactComponent copy() {
        NamespaceContactComponent dst = new NamespaceContactComponent();
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        return dst;
      }

  }

    /**
     * Indicates the purpose for the namespace - what kinds of things does it make unique?.
     */
    protected Enumeration<NamespaceType> type;

    /**
     * The descriptive name of this particular identifier type or code system.
     */
    protected String_ name;

    /**
     * Indicates whether the namespace is "ready for use" or not.
     */
    protected Enumeration<NamespaceStatus> status;

    /**
     * If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.
     */
    protected Code country;

    /**
     * Categorizes a namespace for easier search by grouping related namespaces.
     */
    protected CodeableConcept category;

    /**
     * The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.
     */
    protected String_ responsible;

    /**
     * Details about what the namespace identifies including scope, granularity, version labeling, etc.
     */
    protected String_ description;

    /**
     * Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.
     */
    protected String_ usage;

    /**
     * Indicates how the system may be identified when referenced in electronic exchange.
     */
    protected List<NamespaceUniqueIdComponent> uniqueId = new ArrayList<NamespaceUniqueIdComponent>();

    /**
     * The person who can be contacted about this system registration entry.
     */
    protected NamespaceContactComponent contact;

    /**
     * For namespaces that are retired, indicates the namespace that should be used in their place (if any).
     */
    protected ResourceReference replacedBy;

    /**
     * The actual object that is the target of the reference (For namespaces that are retired, indicates the namespace that should be used in their place (if any).)
     */
    protected Namespace replacedByTarget;

    private static final long serialVersionUID = 1650087846L;

    public Namespace() {
      super();
    }

    public Namespace(Enumeration<NamespaceType> type, String_ name, Enumeration<NamespaceStatus> status) {
      super();
      this.type = type;
      this.name = name;
      this.status = status;
    }

    /**
     * @return {@link #type} (Indicates the purpose for the namespace - what kinds of things does it make unique?.)
     */
    public Enumeration<NamespaceType> getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (Indicates the purpose for the namespace - what kinds of things does it make unique?.)
     */
    public Namespace setType(Enumeration<NamespaceType> value) { 
      this.type = value;
      return this;
    }

    /**
     * @return Indicates the purpose for the namespace - what kinds of things does it make unique?.
     */
    public NamespaceType getTypeSimple() { 
      return this.type == null ? null : this.type.getValue();
    }

    /**
     * @param value Indicates the purpose for the namespace - what kinds of things does it make unique?.
     */
    public Namespace setTypeSimple(NamespaceType value) { 
        if (this.type == null)
          this.type = new Enumeration<NamespaceType>();
        this.type.setValue(value);
      return this;
    }

    /**
     * @return {@link #name} (The descriptive name of this particular identifier type or code system.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (The descriptive name of this particular identifier type or code system.)
     */
    public Namespace setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return The descriptive name of this particular identifier type or code system.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value The descriptive name of this particular identifier type or code system.
     */
    public Namespace setNameSimple(String value) { 
        if (this.name == null)
          this.name = new String_();
        this.name.setValue(value);
      return this;
    }

    /**
     * @return {@link #status} (Indicates whether the namespace is "ready for use" or not.)
     */
    public Enumeration<NamespaceStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Indicates whether the namespace is "ready for use" or not.)
     */
    public Namespace setStatus(Enumeration<NamespaceStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Indicates whether the namespace is "ready for use" or not.
     */
    public NamespaceStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Indicates whether the namespace is "ready for use" or not.
     */
    public Namespace setStatusSimple(NamespaceStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<NamespaceStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #country} (If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.)
     */
    public Code getCountry() { 
      return this.country;
    }

    /**
     * @param value {@link #country} (If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.)
     */
    public Namespace setCountry(Code value) { 
      this.country = value;
      return this;
    }

    /**
     * @return If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.
     */
    public String getCountrySimple() { 
      return this.country == null ? null : this.country.getValue();
    }

    /**
     * @param value If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.
     */
    public Namespace setCountrySimple(String value) { 
      if (value == null)
        this.country = null;
      else {
        if (this.country == null)
          this.country = new Code();
        this.country.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #category} (Categorizes a namespace for easier search by grouping related namespaces.)
     */
    public CodeableConcept getCategory() { 
      return this.category;
    }

    /**
     * @param value {@link #category} (Categorizes a namespace for easier search by grouping related namespaces.)
     */
    public Namespace setCategory(CodeableConcept value) { 
      this.category = value;
      return this;
    }

    /**
     * @return {@link #responsible} (The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.)
     */
    public String_ getResponsible() { 
      return this.responsible;
    }

    /**
     * @param value {@link #responsible} (The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.)
     */
    public Namespace setResponsible(String_ value) { 
      this.responsible = value;
      return this;
    }

    /**
     * @return The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.
     */
    public String getResponsibleSimple() { 
      return this.responsible == null ? null : this.responsible.getValue();
    }

    /**
     * @param value The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.
     */
    public Namespace setResponsibleSimple(String value) { 
      if (value == null)
        this.responsible = null;
      else {
        if (this.responsible == null)
          this.responsible = new String_();
        this.responsible.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #description} (Details about what the namespace identifies including scope, granularity, version labeling, etc.)
     */
    public String_ getDescription() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (Details about what the namespace identifies including scope, granularity, version labeling, etc.)
     */
    public Namespace setDescription(String_ value) { 
      this.description = value;
      return this;
    }

    /**
     * @return Details about what the namespace identifies including scope, granularity, version labeling, etc.
     */
    public String getDescriptionSimple() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value Details about what the namespace identifies including scope, granularity, version labeling, etc.
     */
    public Namespace setDescriptionSimple(String value) { 
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
     * @return {@link #usage} (Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.)
     */
    public String_ getUsage() { 
      return this.usage;
    }

    /**
     * @param value {@link #usage} (Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.)
     */
    public Namespace setUsage(String_ value) { 
      this.usage = value;
      return this;
    }

    /**
     * @return Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.
     */
    public String getUsageSimple() { 
      return this.usage == null ? null : this.usage.getValue();
    }

    /**
     * @param value Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.
     */
    public Namespace setUsageSimple(String value) { 
      if (value == null)
        this.usage = null;
      else {
        if (this.usage == null)
          this.usage = new String_();
        this.usage.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #uniqueId} (Indicates how the system may be identified when referenced in electronic exchange.)
     */
    public List<NamespaceUniqueIdComponent> getUniqueId() { 
      return this.uniqueId;
    }

    // syntactic sugar
    /**
     * @return {@link #uniqueId} (Indicates how the system may be identified when referenced in electronic exchange.)
     */
    public NamespaceUniqueIdComponent addUniqueId() { 
      NamespaceUniqueIdComponent t = new NamespaceUniqueIdComponent();
      this.uniqueId.add(t);
      return t;
    }

    /**
     * @return {@link #contact} (The person who can be contacted about this system registration entry.)
     */
    public NamespaceContactComponent getContact() { 
      return this.contact;
    }

    /**
     * @param value {@link #contact} (The person who can be contacted about this system registration entry.)
     */
    public Namespace setContact(NamespaceContactComponent value) { 
      this.contact = value;
      return this;
    }

    /**
     * @return {@link #replacedBy} (For namespaces that are retired, indicates the namespace that should be used in their place (if any).)
     */
    public ResourceReference getReplacedBy() { 
      return this.replacedBy;
    }

    /**
     * @param value {@link #replacedBy} (For namespaces that are retired, indicates the namespace that should be used in their place (if any).)
     */
    public Namespace setReplacedBy(ResourceReference value) { 
      this.replacedBy = value;
      return this;
    }

    /**
     * @return {@link #replacedBy} (The actual object that is the target of the reference. For namespaces that are retired, indicates the namespace that should be used in their place (if any).)
     */
    public Namespace getReplacedByTarget() { 
      return this.replacedByTarget;
    }

    /**
     * @param value {@link #replacedBy} (The actual object that is the target of the reference. For namespaces that are retired, indicates the namespace that should be used in their place (if any).)
     */
    public Namespace setReplacedByTarget(Namespace value) { 
      this.replacedByTarget = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("type", "code", "Indicates the purpose for the namespace - what kinds of things does it make unique?.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("name", "string", "The descriptive name of this particular identifier type or code system.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("status", "code", "Indicates whether the namespace is 'ready for use' or not.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("country", "code", "If present, indicates that the identifier or code system is principally intended for use or applies to entities within the specified country.  For example, the country associated with a national code system.", 0, java.lang.Integer.MAX_VALUE, country));
        childrenList.add(new Property("category", "CodeableConcept", "Categorizes a namespace for easier search by grouping related namespaces.", 0, java.lang.Integer.MAX_VALUE, category));
        childrenList.add(new Property("responsible", "string", "The name of the organization that is responsible for issuing identifiers or codes for this namespace and ensuring their non-collision.", 0, java.lang.Integer.MAX_VALUE, responsible));
        childrenList.add(new Property("description", "string", "Details about what the namespace identifies including scope, granularity, version labeling, etc.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("usage", "string", "Provides guidance on the use of the namespace, including the handling of formatting characters, use of upper vs. lower case, etc.", 0, java.lang.Integer.MAX_VALUE, usage));
        childrenList.add(new Property("uniqueId", "", "Indicates how the system may be identified when referenced in electronic exchange.", 0, java.lang.Integer.MAX_VALUE, uniqueId));
        childrenList.add(new Property("contact", "", "The person who can be contacted about this system registration entry.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("replacedBy", "Resource(Namespace)", "For namespaces that are retired, indicates the namespace that should be used in their place (if any).", 0, java.lang.Integer.MAX_VALUE, replacedBy));
      }

      public Namespace copy() {
        Namespace dst = new Namespace();
        dst.type = type == null ? null : type.copy();
        dst.name = name == null ? null : name.copy();
        dst.status = status == null ? null : status.copy();
        dst.country = country == null ? null : country.copy();
        dst.category = category == null ? null : category.copy();
        dst.responsible = responsible == null ? null : responsible.copy();
        dst.description = description == null ? null : description.copy();
        dst.usage = usage == null ? null : usage.copy();
        dst.uniqueId = new ArrayList<NamespaceUniqueIdComponent>();
        for (NamespaceUniqueIdComponent i : uniqueId)
          dst.uniqueId.add(i.copy());
        dst.contact = contact == null ? null : contact.copy();
        dst.replacedBy = replacedBy == null ? null : replacedBy.copy();
        return dst;
      }

      protected Namespace typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Namespace;
   }


}

