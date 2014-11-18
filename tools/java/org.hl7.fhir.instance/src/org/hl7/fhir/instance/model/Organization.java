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
 * A formally or informally recognized grouping of people or organizations formed for the purpose of achieving some form of collective action.  Includes companies, institutions, corporations, departments, community groups, healthcare practice groups, etc.
 */
public class Organization extends DomainResource {

    public enum AdministrativeGender {
        MALE, // Male
        FEMALE, // Female
        OTHER, // Other
        UNKNOWN, // Unknown
        NULL; // added to help the parsers
        public static AdministrativeGender fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("male".equals(codeString))
          return MALE;
        if ("female".equals(codeString))
          return FEMALE;
        if ("other".equals(codeString))
          return OTHER;
        if ("unknown".equals(codeString))
          return UNKNOWN;
        throw new Exception("Unknown AdministrativeGender code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case MALE: return "male";
            case FEMALE: return "female";
            case OTHER: return "other";
            case UNKNOWN: return "unknown";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case MALE: return "Male";
            case FEMALE: return "Female";
            case OTHER: return "Other";
            case UNKNOWN: return "Unknown";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case MALE: return "male";
            case FEMALE: return "female";
            case OTHER: return "other";
            case UNKNOWN: return "unknown";
            default: return "?";
          }
        }
    }

  public static class AdministrativeGenderEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("male".equals(codeString))
          return AdministrativeGender.MALE;
        if ("female".equals(codeString))
          return AdministrativeGender.FEMALE;
        if ("other".equals(codeString))
          return AdministrativeGender.OTHER;
        if ("unknown".equals(codeString))
          return AdministrativeGender.UNKNOWN;
        throw new Exception("Unknown AdministrativeGender code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == AdministrativeGender.MALE)
        return "male";
      if (code == AdministrativeGender.FEMALE)
        return "female";
      if (code == AdministrativeGender.OTHER)
        return "other";
      if (code == AdministrativeGender.UNKNOWN)
        return "unknown";
      return "?";
      }
    }

    public static class OrganizationContactComponent extends BackboneElement {
        /**
         * Indicates a purpose for which the contact can be reached.
         */
        protected CodeableConcept purpose;

        /**
         * A name associated with the contact.
         */
        protected HumanName name;

        /**
         * A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.
         */
        protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

        /**
         * Visiting or postal addresses for the contact.
         */
        protected Address address;

        /**
         * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        protected Enumeration<AdministrativeGender> gender;

        private static final long serialVersionUID = -1196460915L;

      public OrganizationContactComponent() {
        super();
      }

        /**
         * @return {@link #purpose} (Indicates a purpose for which the contact can be reached.)
         */
        public CodeableConcept getPurpose() { 
          return this.purpose;
        }

        /**
         * @param value {@link #purpose} (Indicates a purpose for which the contact can be reached.)
         */
        public OrganizationContactComponent setPurpose(CodeableConcept value) { 
          this.purpose = value;
          return this;
        }

        /**
         * @return {@link #name} (A name associated with the contact.)
         */
        public HumanName getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A name associated with the contact.)
         */
        public OrganizationContactComponent setName(HumanName value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.)
         */
        public List<ContactPoint> getTelecom() { 
          return this.telecom;
        }

        /**
         * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.)
         */
    // syntactic sugar
        public ContactPoint addTelecom() { //3
          ContactPoint t = new ContactPoint();
          this.telecom.add(t);
          return t;
        }

        /**
         * @return {@link #address} (Visiting or postal addresses for the contact.)
         */
        public Address getAddress() { 
          return this.address;
        }

        /**
         * @param value {@link #address} (Visiting or postal addresses for the contact.)
         */
        public OrganizationContactComponent setAddress(Address value) { 
          this.address = value;
          return this;
        }

        /**
         * @return {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
         */
        public Enumeration<AdministrativeGender> getGenderElement() { 
          return this.gender;
        }

        /**
         * @param value {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
         */
        public OrganizationContactComponent setGenderElement(Enumeration<AdministrativeGender> value) { 
          this.gender = value;
          return this;
        }

        /**
         * @return Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        public AdministrativeGender getGender() { 
          return this.gender == null ? null : this.gender.getValue();
        }

        /**
         * @param value Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        public OrganizationContactComponent setGender(AdministrativeGender value) { 
          if (value == null)
            this.gender = null;
          else {
            if (this.gender == null)
              this.gender = new Enumeration<AdministrativeGender>();
            this.gender.setValue(value);
          }
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("purpose", "CodeableConcept", "Indicates a purpose for which the contact can be reached.", 0, java.lang.Integer.MAX_VALUE, purpose));
          childrenList.add(new Property("name", "HumanName", "A name associated with the contact.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("telecom", "ContactPoint", "A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.", 0, java.lang.Integer.MAX_VALUE, telecom));
          childrenList.add(new Property("address", "Address", "Visiting or postal addresses for the contact.", 0, java.lang.Integer.MAX_VALUE, address));
          childrenList.add(new Property("gender", "code", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        }

      public OrganizationContactComponent copy() {
        OrganizationContactComponent dst = new OrganizationContactComponent();
        copyValues(dst);
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
          dst.telecom.add(i.copy());
        dst.address = address == null ? null : address.copy();
        dst.gender = gender == null ? null : gender.copy();
        return dst;
      }

  }

    /**
     * Identifier for the organization that is used to identify the organization across multiple disparate systems.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A name associated with the organization.
     */
    protected StringType name;

    /**
     * The kind of organization that this is.
     */
    protected CodeableConcept type;

    /**
     * A contact detail for the organization.
     */
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * An address for the organization.
     */
    protected List<Address> address = new ArrayList<Address>();

    /**
     * The organization of which this organization forms a part.
     */
    protected Reference partOf;

    /**
     * The actual object that is the target of the reference (The organization of which this organization forms a part.)
     */
    protected Organization partOfTarget;

    /**
     * Contact for the organization for a certain purpose.
     */
    protected List<OrganizationContactComponent> contact = new ArrayList<OrganizationContactComponent>();

    /**
     * Location(s) the organization uses to provide services.
     */
    protected List<Reference> location = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Location(s) the organization uses to provide services.)
     */
    protected List<Location> locationTarget = new ArrayList<Location>();


    /**
     * Whether the organization's record is still in active use.
     */
    protected BooleanType active;

    private static final long serialVersionUID = 462842125L;

    public Organization() {
      super();
    }

    /**
     * @return {@link #identifier} (Identifier for the organization that is used to identify the organization across multiple disparate systems.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifier for the organization that is used to identify the organization across multiple disparate systems.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #name} (A name associated with the organization.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public StringType getNameElement() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A name associated with the organization.). This is the underlying object with id, value and extensions. The accessor "getName" gives direct access to the value
     */
    public Organization setNameElement(StringType value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A name associated with the organization.
     */
    public String getName() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A name associated with the organization.
     */
    public Organization setName(String value) { 
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
     * @return {@link #type} (The kind of organization that this is.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (The kind of organization that this is.)
     */
    public Organization setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #telecom} (A contact detail for the organization.)
     */
    public List<ContactPoint> getTelecom() { 
      return this.telecom;
    }

    /**
     * @return {@link #telecom} (A contact detail for the organization.)
     */
    // syntactic sugar
    public ContactPoint addTelecom() { //3
      ContactPoint t = new ContactPoint();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #address} (An address for the organization.)
     */
    public List<Address> getAddress() { 
      return this.address;
    }

    /**
     * @return {@link #address} (An address for the organization.)
     */
    // syntactic sugar
    public Address addAddress() { //3
      Address t = new Address();
      this.address.add(t);
      return t;
    }

    /**
     * @return {@link #partOf} (The organization of which this organization forms a part.)
     */
    public Reference getPartOf() { 
      return this.partOf;
    }

    /**
     * @param value {@link #partOf} (The organization of which this organization forms a part.)
     */
    public Organization setPartOf(Reference value) { 
      this.partOf = value;
      return this;
    }

    /**
     * @return {@link #partOf} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The organization of which this organization forms a part.)
     */
    public Organization getPartOfTarget() { 
      return this.partOfTarget;
    }

    /**
     * @param value {@link #partOf} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The organization of which this organization forms a part.)
     */
    public Organization setPartOfTarget(Organization value) { 
      this.partOfTarget = value;
      return this;
    }

    /**
     * @return {@link #contact} (Contact for the organization for a certain purpose.)
     */
    public List<OrganizationContactComponent> getContact() { 
      return this.contact;
    }

    /**
     * @return {@link #contact} (Contact for the organization for a certain purpose.)
     */
    // syntactic sugar
    public OrganizationContactComponent addContact() { //3
      OrganizationContactComponent t = new OrganizationContactComponent();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #location} (Location(s) the organization uses to provide services.)
     */
    public List<Reference> getLocation() { 
      return this.location;
    }

    /**
     * @return {@link #location} (Location(s) the organization uses to provide services.)
     */
    // syntactic sugar
    public Reference addLocation() { //3
      Reference t = new Reference();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Location(s) the organization uses to provide services.)
     */
    public List<Location> getLocationTarget() { 
      return this.locationTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #location} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. Location(s) the organization uses to provide services.)
     */
    public Location addLocationTarget() { 
      Location r = new Location();
      this.locationTarget.add(r);
      return r;
    }

    /**
     * @return {@link #active} (Whether the organization's record is still in active use.). This is the underlying object with id, value and extensions. The accessor "getActive" gives direct access to the value
     */
    public BooleanType getActiveElement() { 
      return this.active;
    }

    /**
     * @param value {@link #active} (Whether the organization's record is still in active use.). This is the underlying object with id, value and extensions. The accessor "getActive" gives direct access to the value
     */
    public Organization setActiveElement(BooleanType value) { 
      this.active = value;
      return this;
    }

    /**
     * @return Whether the organization's record is still in active use.
     */
    public boolean getActive() { 
      return this.active == null ? false : this.active.getValue();
    }

    /**
     * @param value Whether the organization's record is still in active use.
     */
    public Organization setActive(boolean value) { 
      if (value == false)
        this.active = null;
      else {
        if (this.active == null)
          this.active = new BooleanType();
        this.active.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier for the organization that is used to identify the organization across multiple disparate systems.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "string", "A name associated with the organization.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("type", "CodeableConcept", "The kind of organization that this is.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("telecom", "ContactPoint", "A contact detail for the organization.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("address", "Address", "An address for the organization.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("partOf", "Reference(Organization)", "The organization of which this organization forms a part.", 0, java.lang.Integer.MAX_VALUE, partOf));
        childrenList.add(new Property("contact", "", "Contact for the organization for a certain purpose.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("location", "Reference(Location)", "Location(s) the organization uses to provide services.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("active", "boolean", "Whether the organization's record is still in active use.", 0, java.lang.Integer.MAX_VALUE, active));
      }

      public Organization copy() {
        Organization dst = new Organization();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
          dst.telecom.add(i.copy());
        dst.address = new ArrayList<Address>();
        for (Address i : address)
          dst.address.add(i.copy());
        dst.partOf = partOf == null ? null : partOf.copy();
        dst.contact = new ArrayList<OrganizationContactComponent>();
        for (OrganizationContactComponent i : contact)
          dst.contact.add(i.copy());
        dst.location = new ArrayList<Reference>();
        for (Reference i : location)
          dst.location.add(i.copy());
        dst.active = active == null ? null : active.copy();
        return dst;
      }

      protected Organization typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Organization;
   }


}

