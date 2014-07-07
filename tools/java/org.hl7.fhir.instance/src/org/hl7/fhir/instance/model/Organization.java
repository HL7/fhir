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

// Generated on Mon, Jul 7, 2014 07:04+1000 for FHIR v0.2.1

import java.util.*;

/**
 * A formally or informally recognized grouping of people or organizations formed for the purpose of achieving some form of collective action.  Includes companies, institutions, corporations, departments, community groups, healthcare practice groups, etc.
 */
public class Organization extends Resource {

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
        protected List<Contact> telecom = new ArrayList<Contact>();

        /**
         * Visiting or postal addresses for the contact.
         */
        protected Address address;

        /**
         * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        protected CodeableConcept gender;

        private static final long serialVersionUID = 2147286938L;

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
        public List<Contact> getTelecom() { 
          return this.telecom;
        }

    // syntactic sugar
        /**
         * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.)
         */
        public Contact addTelecom() { 
          Contact t = new Contact();
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
         * @return {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.)
         */
        public CodeableConcept getGender() { 
          return this.gender;
        }

        /**
         * @param value {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.)
         */
        public OrganizationContactComponent setGender(CodeableConcept value) { 
          this.gender = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("purpose", "CodeableConcept", "Indicates a purpose for which the contact can be reached.", 0, java.lang.Integer.MAX_VALUE, purpose));
          childrenList.add(new Property("name", "HumanName", "A name associated with the contact.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("telecom", "Contact", "A contact detail (e.g. a telephone number or an email address) by which the party may be contacted.", 0, java.lang.Integer.MAX_VALUE, telecom));
          childrenList.add(new Property("address", "Address", "Visiting or postal addresses for the contact.", 0, java.lang.Integer.MAX_VALUE, address));
          childrenList.add(new Property("gender", "CodeableConcept", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        }

      public OrganizationContactComponent copy() {
        OrganizationContactComponent dst = new OrganizationContactComponent();
        dst.purpose = purpose == null ? null : purpose.copy();
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
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
    protected String_ name;

    /**
     * The kind of organization that this is.
     */
    protected CodeableConcept type;

    /**
     * A contact detail for the organization.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * An address for the organization.
     */
    protected List<Address> address = new ArrayList<Address>();

    /**
     * The organization of which this organization forms a part.
     */
    protected ResourceReference partOf;

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
    protected List<ResourceReference> location = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Location(s) the organization uses to provide services.)
     */
    protected List<Location> locationTarget = new ArrayList<Location>();


    /**
     * Whether the organization's record is still in active use.
     */
    protected Boolean active;

    private static final long serialVersionUID = -789178682L;

    public Organization() {
      super();
    }

    /**
     * @return {@link #identifier} (Identifier for the organization that is used to identify the organization across multiple disparate systems.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifier for the organization that is used to identify the organization across multiple disparate systems.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #name} (A name associated with the organization.)
     */
    public String_ getName() { 
      return this.name;
    }

    /**
     * @param value {@link #name} (A name associated with the organization.)
     */
    public Organization setName(String_ value) { 
      this.name = value;
      return this;
    }

    /**
     * @return A name associated with the organization.
     */
    public String getNameSimple() { 
      return this.name == null ? null : this.name.getValue();
    }

    /**
     * @param value A name associated with the organization.
     */
    public Organization setNameSimple(String value) { 
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
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (A contact detail for the organization.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #address} (An address for the organization.)
     */
    public List<Address> getAddress() { 
      return this.address;
    }

    // syntactic sugar
    /**
     * @return {@link #address} (An address for the organization.)
     */
    public Address addAddress() { 
      Address t = new Address();
      this.address.add(t);
      return t;
    }

    /**
     * @return {@link #partOf} (The organization of which this organization forms a part.)
     */
    public ResourceReference getPartOf() { 
      return this.partOf;
    }

    /**
     * @param value {@link #partOf} (The organization of which this organization forms a part.)
     */
    public Organization setPartOf(ResourceReference value) { 
      this.partOf = value;
      return this;
    }

    /**
     * @return {@link #partOf} (The actual object that is the target of the reference. The organization of which this organization forms a part.)
     */
    public Organization getPartOfTarget() { 
      return this.partOfTarget;
    }

    /**
     * @param value {@link #partOf} (The actual object that is the target of the reference. The organization of which this organization forms a part.)
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

    // syntactic sugar
    /**
     * @return {@link #contact} (Contact for the organization for a certain purpose.)
     */
    public OrganizationContactComponent addContact() { 
      OrganizationContactComponent t = new OrganizationContactComponent();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #location} (Location(s) the organization uses to provide services.)
     */
    public List<ResourceReference> getLocation() { 
      return this.location;
    }

    // syntactic sugar
    /**
     * @return {@link #location} (Location(s) the organization uses to provide services.)
     */
    public ResourceReference addLocation() { 
      ResourceReference t = new ResourceReference();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The actual objects that are the target of the reference. Location(s) the organization uses to provide services.)
     */
    public List<Location> getLocationTarget() { 
      return this.locationTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #location} (Add an actual object that is the target of the reference. Location(s) the organization uses to provide services.)
     */
    public Location addLocationTarget() { 
      Location r = new Location();
      this.locationTarget.add(r);
      return r;
    }

    /**
     * @return {@link #active} (Whether the organization's record is still in active use.)
     */
    public Boolean getActive() { 
      return this.active;
    }

    /**
     * @param value {@link #active} (Whether the organization's record is still in active use.)
     */
    public Organization setActive(Boolean value) { 
      this.active = value;
      return this;
    }

    /**
     * @return Whether the organization's record is still in active use.
     */
    public boolean getActiveSimple() { 
      return this.active == null ? false : this.active.getValue();
    }

    /**
     * @param value Whether the organization's record is still in active use.
     */
    public Organization setActiveSimple(boolean value) { 
      if (value == false)
        this.active = null;
      else {
        if (this.active == null)
          this.active = new Boolean();
        this.active.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier for the organization that is used to identify the organization across multiple disparate systems.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "string", "A name associated with the organization.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("type", "CodeableConcept", "The kind of organization that this is.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("telecom", "Contact", "A contact detail for the organization.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("address", "Address", "An address for the organization.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("partOf", "Resource(Organization)", "The organization of which this organization forms a part.", 0, java.lang.Integer.MAX_VALUE, partOf));
        childrenList.add(new Property("contact", "", "Contact for the organization for a certain purpose.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("location", "Resource(Location)", "Location(s) the organization uses to provide services.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("active", "boolean", "Whether the organization's record is still in active use.", 0, java.lang.Integer.MAX_VALUE, active));
      }

      public Organization copy() {
        Organization dst = new Organization();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.type = type == null ? null : type.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.address = new ArrayList<Address>();
        for (Address i : address)
          dst.address.add(i.copy());
        dst.partOf = partOf == null ? null : partOf.copy();
        dst.contact = new ArrayList<OrganizationContactComponent>();
        for (OrganizationContactComponent i : contact)
          dst.contact.add(i.copy());
        dst.location = new ArrayList<ResourceReference>();
        for (ResourceReference i : location)
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

