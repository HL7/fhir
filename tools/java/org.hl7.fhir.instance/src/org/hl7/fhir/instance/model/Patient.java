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
 * Demographics and other administrative information about a person or animal receiving care or other health-related services.
 */
public class Patient extends Resource {

    public enum LinkType {
        replace, // The patient resource containing this link must no longer be used. The link points forward to another patient resource that must be used in lieu of the patient resource that contains the link.
        refer, // The patient resource containing this link is in use and valid but not considered the main source of information about a patient. The link points forward to another patient resource that should be consulted to retrieve additional patient information.
        seealso, // The patient resource containing this link is in use and valid, but points to another patient resource that is known to contain data about the same person. Data in this resource might overlap or contradict information found in the other patient resource. This link does not indicate any relative importance of the resources concerned, and both should be regarded as equally valid.
        Null; // added to help the parsers
        public static LinkType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("replace".equals(codeString))
          return replace;
        if ("refer".equals(codeString))
          return refer;
        if ("seealso".equals(codeString))
          return seealso;
        throw new Exception("Unknown LinkType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case replace: return "replace";
            case refer: return "refer";
            case seealso: return "seealso";
            default: return "?";
          }
        }
    }

  public static class LinkTypeEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("replace".equals(codeString))
          return LinkType.replace;
        if ("refer".equals(codeString))
          return LinkType.refer;
        if ("seealso".equals(codeString))
          return LinkType.seealso;
        throw new Exception("Unknown LinkType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == LinkType.replace)
        return "replace";
      if (code == LinkType.refer)
        return "refer";
      if (code == LinkType.seealso)
        return "seealso";
      return "?";
      }
    }

    public static class ContactComponent extends BackboneElement {
        /**
         * The nature of the relationship between the patient and the contact person.
         */
        protected List<CodeableConcept> relationship = new ArrayList<CodeableConcept>();

        /**
         * A name associated with the person.
         */
        protected HumanName name;

        /**
         * A contact detail for the person, e.g. a telephone number or an email address.
         */
        protected List<Contact> telecom = new ArrayList<Contact>();

        /**
         * Address for the contact person.
         */
        protected Address address;

        /**
         * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        protected CodeableConcept gender;

        /**
         * Organization on behalf of which the contact is acting or for which the contact is working.
         */
        protected ResourceReference organization;

      public ContactComponent() {
        super();
      }

        public List<CodeableConcept> getRelationship() { 
          return this.relationship;
        }

    // syntactic sugar
        public CodeableConcept addRelationship() { 
          CodeableConcept t = new CodeableConcept();
          this.relationship.add(t);
          return t;
        }

        public HumanName getName() { 
          return this.name;
        }

        public ContactComponent setName(HumanName value) { 
          this.name = value;
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

        public Address getAddress() { 
          return this.address;
        }

        public ContactComponent setAddress(Address value) { 
          this.address = value;
          return this;
        }

        public CodeableConcept getGender() { 
          return this.gender;
        }

        public ContactComponent setGender(CodeableConcept value) { 
          this.gender = value;
          return this;
        }

        public ResourceReference getOrganization() { 
          return this.organization;
        }

        public ContactComponent setOrganization(ResourceReference value) { 
          this.organization = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationship", "CodeableConcept", "The nature of the relationship between the patient and the contact person.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("name", "HumanName", "A name associated with the person.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("telecom", "Contact", "A contact detail for the person, e.g. a telephone number or an email address.", 0, java.lang.Integer.MAX_VALUE, telecom));
          childrenList.add(new Property("address", "Address", "Address for the contact person.", 0, java.lang.Integer.MAX_VALUE, address));
          childrenList.add(new Property("gender", "CodeableConcept", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
          childrenList.add(new Property("organization", "Resource(Organization)", "Organization on behalf of which the contact is acting or for which the contact is working.", 0, java.lang.Integer.MAX_VALUE, organization));
        }

      public ContactComponent copy(Patient e) {
        ContactComponent dst = new ContactComponent();
        dst.relationship = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : relationship)
          dst.relationship.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.address = address == null ? null : address.copy();
        dst.gender = gender == null ? null : gender.copy();
        dst.organization = organization == null ? null : organization.copy();
        return dst;
      }

  }

    public static class AnimalComponent extends BackboneElement {
        /**
         * Identifies the high level categorization of the kind of animal.
         */
        protected CodeableConcept species;

        /**
         * Identifies the detailed categorization of the kind of animal.
         */
        protected CodeableConcept breed;

        /**
         * Indicates the current state of the animal's reproductive organs.
         */
        protected CodeableConcept genderStatus;

      public AnimalComponent() {
        super();
      }

      public AnimalComponent(CodeableConcept species) {
        super();
        this.species = species;
      }

        public CodeableConcept getSpecies() { 
          return this.species;
        }

        public AnimalComponent setSpecies(CodeableConcept value) { 
          this.species = value;
          return this;
        }

        public CodeableConcept getBreed() { 
          return this.breed;
        }

        public AnimalComponent setBreed(CodeableConcept value) { 
          this.breed = value;
          return this;
        }

        public CodeableConcept getGenderStatus() { 
          return this.genderStatus;
        }

        public AnimalComponent setGenderStatus(CodeableConcept value) { 
          this.genderStatus = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("species", "CodeableConcept", "Identifies the high level categorization of the kind of animal.", 0, java.lang.Integer.MAX_VALUE, species));
          childrenList.add(new Property("breed", "CodeableConcept", "Identifies the detailed categorization of the kind of animal.", 0, java.lang.Integer.MAX_VALUE, breed));
          childrenList.add(new Property("genderStatus", "CodeableConcept", "Indicates the current state of the animal's reproductive organs.", 0, java.lang.Integer.MAX_VALUE, genderStatus));
        }

      public AnimalComponent copy(Patient e) {
        AnimalComponent dst = new AnimalComponent();
        dst.species = species == null ? null : species.copy();
        dst.breed = breed == null ? null : breed.copy();
        dst.genderStatus = genderStatus == null ? null : genderStatus.copy();
        return dst;
      }

  }

    public static class PatientLinkComponent extends BackboneElement {
        /**
         * The other patient resource that the link refers to.
         */
        protected ResourceReference other;

        /**
         * The type of link between this patient resource and another patient resource.
         */
        protected Enumeration<LinkType> type;

      public PatientLinkComponent() {
        super();
      }

      public PatientLinkComponent(ResourceReference other, Enumeration<LinkType> type) {
        super();
        this.other = other;
        this.type = type;
      }

        public ResourceReference getOther() { 
          return this.other;
        }

        public PatientLinkComponent setOther(ResourceReference value) { 
          this.other = value;
          return this;
        }

        public Enumeration<LinkType> getType() { 
          return this.type;
        }

        public PatientLinkComponent setType(Enumeration<LinkType> value) { 
          this.type = value;
          return this;
        }

        public LinkType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        public PatientLinkComponent setTypeSimple(LinkType value) { 
            if (this.type == null)
              this.type = new Enumeration<LinkType>();
            this.type.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("other", "Resource(Patient)", "The other patient resource that the link refers to.", 0, java.lang.Integer.MAX_VALUE, other));
          childrenList.add(new Property("type", "code", "The type of link between this patient resource and another patient resource.", 0, java.lang.Integer.MAX_VALUE, type));
        }

      public PatientLinkComponent copy(Patient e) {
        PatientLinkComponent dst = new PatientLinkComponent();
        dst.other = other == null ? null : other.copy();
        dst.type = type == null ? null : type.copy();
        return dst;
      }

  }

    /**
     * An identifier that applies to this person as a patient.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A name associated with the individual.
     */
    protected List<HumanName> name = new ArrayList<HumanName>();

    /**
     * A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.
     */
    protected CodeableConcept gender;

    /**
     * The date and time of birth for the individual.
     */
    protected DateTime birthDate;

    /**
     * Indicates if the individual is deceased or not.
     */
    protected Type deceased;

    /**
     * Addresses for the individual.
     */
    protected List<Address> address = new ArrayList<Address>();

    /**
     * This field contains a patient's most recent marital (civil) status.
     */
    protected CodeableConcept maritalStatus;

    /**
     * Indicates whether the patient is part of a multiple or indicates the actual birth order.
     */
    protected Type multipleBirth;

    /**
     * Image of the person.
     */
    protected List<Attachment> photo = new ArrayList<Attachment>();

    /**
     * A contact party (e.g. guardian, partner, friend) for the patient.
     */
    protected List<ContactComponent> contact = new ArrayList<ContactComponent>();

    /**
     * This element has a value if the patient is an animal.
     */
    protected AnimalComponent animal;

    /**
     * Languages which may be used to communicate with the patient.
     */
    protected List<CodeableConcept> communication = new ArrayList<CodeableConcept>();

    /**
     * The provider for whom this is a patient record.
     */
    protected ResourceReference provider;

    /**
     * Link to another patient resource that concerns the same actual person.
     */
    protected List<PatientLinkComponent> link = new ArrayList<PatientLinkComponent>();

    /**
     * Whether this patient record is in active use.
     */
    protected Boolean active;

    public Patient() {
      super();
    }

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public List<HumanName> getName() { 
      return this.name;
    }

    // syntactic sugar
    public HumanName addName() { 
      HumanName t = new HumanName();
      this.name.add(t);
      return t;
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

    public CodeableConcept getGender() { 
      return this.gender;
    }

    public Patient setGender(CodeableConcept value) { 
      this.gender = value;
      return this;
    }

    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    public Patient setBirthDate(DateTime value) { 
      this.birthDate = value;
      return this;
    }

    public String getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    public Patient setBirthDateSimple(String value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTime();
        this.birthDate.setValue(value);
      }
      return this;
    }

    public Type getDeceased() { 
      return this.deceased;
    }

    public Patient setDeceased(Type value) { 
      this.deceased = value;
      return this;
    }

    public List<Address> getAddress() { 
      return this.address;
    }

    // syntactic sugar
    public Address addAddress() { 
      Address t = new Address();
      this.address.add(t);
      return t;
    }

    public CodeableConcept getMaritalStatus() { 
      return this.maritalStatus;
    }

    public Patient setMaritalStatus(CodeableConcept value) { 
      this.maritalStatus = value;
      return this;
    }

    public Type getMultipleBirth() { 
      return this.multipleBirth;
    }

    public Patient setMultipleBirth(Type value) { 
      this.multipleBirth = value;
      return this;
    }

    public List<Attachment> getPhoto() { 
      return this.photo;
    }

    // syntactic sugar
    public Attachment addPhoto() { 
      Attachment t = new Attachment();
      this.photo.add(t);
      return t;
    }

    public List<ContactComponent> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    public ContactComponent addContact() { 
      ContactComponent t = new ContactComponent();
      this.contact.add(t);
      return t;
    }

    public AnimalComponent getAnimal() { 
      return this.animal;
    }

    public Patient setAnimal(AnimalComponent value) { 
      this.animal = value;
      return this;
    }

    public List<CodeableConcept> getCommunication() { 
      return this.communication;
    }

    // syntactic sugar
    public CodeableConcept addCommunication() { 
      CodeableConcept t = new CodeableConcept();
      this.communication.add(t);
      return t;
    }

    public ResourceReference getProvider() { 
      return this.provider;
    }

    public Patient setProvider(ResourceReference value) { 
      this.provider = value;
      return this;
    }

    public List<PatientLinkComponent> getLink() { 
      return this.link;
    }

    // syntactic sugar
    public PatientLinkComponent addLink() { 
      PatientLinkComponent t = new PatientLinkComponent();
      this.link.add(t);
      return t;
    }

    public Boolean getActive() { 
      return this.active;
    }

    public Patient setActive(Boolean value) { 
      this.active = value;
      return this;
    }

    public boolean getActiveSimple() { 
      return this.active == null ? null : this.active.getValue();
    }

    public Patient setActiveSimple(boolean value) { 
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
        childrenList.add(new Property("identifier", "Identifier", "An identifier that applies to this person as a patient.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "HumanName", "A name associated with the individual.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("telecom", "Contact", "A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("gender", "CodeableConcept", "Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        childrenList.add(new Property("birthDate", "dateTime", "The date and time of birth for the individual.", 0, java.lang.Integer.MAX_VALUE, birthDate));
        childrenList.add(new Property("deceased[x]", "boolean|dateTime", "Indicates if the individual is deceased or not.", 0, java.lang.Integer.MAX_VALUE, deceased));
        childrenList.add(new Property("address", "Address", "Addresses for the individual.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("maritalStatus", "CodeableConcept", "This field contains a patient's most recent marital (civil) status.", 0, java.lang.Integer.MAX_VALUE, maritalStatus));
        childrenList.add(new Property("multipleBirth[x]", "boolean|integer", "Indicates whether the patient is part of a multiple or indicates the actual birth order.", 0, java.lang.Integer.MAX_VALUE, multipleBirth));
        childrenList.add(new Property("photo", "Attachment", "Image of the person.", 0, java.lang.Integer.MAX_VALUE, photo));
        childrenList.add(new Property("contact", "", "A contact party (e.g. guardian, partner, friend) for the patient.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("animal", "", "This element has a value if the patient is an animal.", 0, java.lang.Integer.MAX_VALUE, animal));
        childrenList.add(new Property("communication", "CodeableConcept", "Languages which may be used to communicate with the patient.", 0, java.lang.Integer.MAX_VALUE, communication));
        childrenList.add(new Property("provider", "Resource(Organization)", "The provider for whom this is a patient record.", 0, java.lang.Integer.MAX_VALUE, provider));
        childrenList.add(new Property("link", "", "Link to another patient resource that concerns the same actual person.", 0, java.lang.Integer.MAX_VALUE, link));
        childrenList.add(new Property("active", "boolean", "Whether this patient record is in active use.", 0, java.lang.Integer.MAX_VALUE, active));
      }

      public Patient copy() {
        Patient dst = new Patient();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.name = new ArrayList<HumanName>();
        for (HumanName i : name)
          dst.name.add(i.copy());
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.gender = gender == null ? null : gender.copy();
        dst.birthDate = birthDate == null ? null : birthDate.copy();
        dst.deceased = deceased == null ? null : deceased.copy();
        dst.address = new ArrayList<Address>();
        for (Address i : address)
          dst.address.add(i.copy());
        dst.maritalStatus = maritalStatus == null ? null : maritalStatus.copy();
        dst.multipleBirth = multipleBirth == null ? null : multipleBirth.copy();
        dst.photo = new ArrayList<Attachment>();
        for (Attachment i : photo)
          dst.photo.add(i.copy());
        dst.contact = new ArrayList<ContactComponent>();
        for (ContactComponent i : contact)
          dst.contact.add(i.copy(dst));
        dst.animal = animal == null ? null : animal.copy(dst);
        dst.communication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : communication)
          dst.communication.add(i.copy());
        dst.provider = provider == null ? null : provider.copy();
        dst.link = new ArrayList<PatientLinkComponent>();
        for (PatientLinkComponent i : link)
          dst.link.add(i.copy(dst));
        dst.active = active == null ? null : active.copy();
        return dst;
      }

      protected Patient typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Patient;
   }


}

