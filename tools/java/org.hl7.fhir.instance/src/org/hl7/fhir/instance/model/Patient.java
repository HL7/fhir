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

        /**
         * The actual object that is the target of the reference (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        protected Organization organizationTarget;

        private static final long serialVersionUID = -384461371L;

      public ContactComponent() {
        super();
      }

        /**
         * @return {@link #relationship} (The nature of the relationship between the patient and the contact person.)
         */
        public List<CodeableConcept> getRelationship() { 
          return this.relationship;
        }

    // syntactic sugar
        /**
         * @return {@link #relationship} (The nature of the relationship between the patient and the contact person.)
         */
        public CodeableConcept addRelationship() { 
          CodeableConcept t = new CodeableConcept();
          this.relationship.add(t);
          return t;
        }

        /**
         * @return {@link #name} (A name associated with the person.)
         */
        public HumanName getName() { 
          return this.name;
        }

        /**
         * @param value {@link #name} (A name associated with the person.)
         */
        public ContactComponent setName(HumanName value) { 
          this.name = value;
          return this;
        }

        /**
         * @return {@link #telecom} (A contact detail for the person, e.g. a telephone number or an email address.)
         */
        public List<Contact> getTelecom() { 
          return this.telecom;
        }

    // syntactic sugar
        /**
         * @return {@link #telecom} (A contact detail for the person, e.g. a telephone number or an email address.)
         */
        public Contact addTelecom() { 
          Contact t = new Contact();
          this.telecom.add(t);
          return t;
        }

        /**
         * @return {@link #address} (Address for the contact person.)
         */
        public Address getAddress() { 
          return this.address;
        }

        /**
         * @param value {@link #address} (Address for the contact person.)
         */
        public ContactComponent setAddress(Address value) { 
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
        public ContactComponent setGender(CodeableConcept value) { 
          this.gender = value;
          return this;
        }

        /**
         * @return {@link #organization} (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public ResourceReference getOrganization() { 
          return this.organization;
        }

        /**
         * @param value {@link #organization} (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public ContactComponent setOrganization(ResourceReference value) { 
          this.organization = value;
          return this;
        }

        /**
         * @return {@link #organization} (The actual object that is the target of the reference. Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public Organization getOrganizationTarget() { 
          return this.organizationTarget;
        }

        /**
         * @param value {@link #organization} (The actual object that is the target of the reference. Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public ContactComponent setOrganizationTarget(Organization value) { 
          this.organizationTarget = value;
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

      public ContactComponent copy() {
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

        private static final long serialVersionUID = -549738382L;

      public AnimalComponent() {
        super();
      }

      public AnimalComponent(CodeableConcept species) {
        super();
        this.species = species;
      }

        /**
         * @return {@link #species} (Identifies the high level categorization of the kind of animal.)
         */
        public CodeableConcept getSpecies() { 
          return this.species;
        }

        /**
         * @param value {@link #species} (Identifies the high level categorization of the kind of animal.)
         */
        public AnimalComponent setSpecies(CodeableConcept value) { 
          this.species = value;
          return this;
        }

        /**
         * @return {@link #breed} (Identifies the detailed categorization of the kind of animal.)
         */
        public CodeableConcept getBreed() { 
          return this.breed;
        }

        /**
         * @param value {@link #breed} (Identifies the detailed categorization of the kind of animal.)
         */
        public AnimalComponent setBreed(CodeableConcept value) { 
          this.breed = value;
          return this;
        }

        /**
         * @return {@link #genderStatus} (Indicates the current state of the animal's reproductive organs.)
         */
        public CodeableConcept getGenderStatus() { 
          return this.genderStatus;
        }

        /**
         * @param value {@link #genderStatus} (Indicates the current state of the animal's reproductive organs.)
         */
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

      public AnimalComponent copy() {
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
         * The actual object that is the target of the reference (The other patient resource that the link refers to.)
         */
        protected Patient otherTarget;

        /**
         * The type of link between this patient resource and another patient resource.
         */
        protected Enumeration<LinkType> type;

        private static final long serialVersionUID = 383172000L;

      public PatientLinkComponent() {
        super();
      }

      public PatientLinkComponent(ResourceReference other, Enumeration<LinkType> type) {
        super();
        this.other = other;
        this.type = type;
      }

        /**
         * @return {@link #other} (The other patient resource that the link refers to.)
         */
        public ResourceReference getOther() { 
          return this.other;
        }

        /**
         * @param value {@link #other} (The other patient resource that the link refers to.)
         */
        public PatientLinkComponent setOther(ResourceReference value) { 
          this.other = value;
          return this;
        }

        /**
         * @return {@link #other} (The actual object that is the target of the reference. The other patient resource that the link refers to.)
         */
        public Patient getOtherTarget() { 
          return this.otherTarget;
        }

        /**
         * @param value {@link #other} (The actual object that is the target of the reference. The other patient resource that the link refers to.)
         */
        public PatientLinkComponent setOtherTarget(Patient value) { 
          this.otherTarget = value;
          return this;
        }

        /**
         * @return {@link #type} (The type of link between this patient resource and another patient resource.)
         */
        public Enumeration<LinkType> getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of link between this patient resource and another patient resource.)
         */
        public PatientLinkComponent setType(Enumeration<LinkType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of link between this patient resource and another patient resource.
         */
        public LinkType getTypeSimple() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of link between this patient resource and another patient resource.
         */
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

      public PatientLinkComponent copy() {
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
     * Languages which may be used to communicate with the patient about his or her health.
     */
    protected List<CodeableConcept> communication = new ArrayList<CodeableConcept>();

    /**
     * Patient's nominated care provider.
     */
    protected List<ResourceReference> careProvider = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Patient's nominated care provider.)
     */
    protected List<Resource> careProviderTarget = new ArrayList<Resource>();


    /**
     * Organization that is the custodian of the patient record.
     */
    protected ResourceReference managingOrganization;

    /**
     * The actual object that is the target of the reference (Organization that is the custodian of the patient record.)
     */
    protected Organization managingOrganizationTarget;

    /**
     * Link to another patient resource that concerns the same actual person.
     */
    protected List<PatientLinkComponent> link = new ArrayList<PatientLinkComponent>();

    /**
     * Whether this patient record is in active use.
     */
    protected Boolean active;

    private static final long serialVersionUID = 1622992530L;

    public Patient() {
      super();
    }

    /**
     * @return {@link #identifier} (An identifier that applies to this person as a patient.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (An identifier that applies to this person as a patient.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #name} (A name associated with the individual.)
     */
    public List<HumanName> getName() { 
      return this.name;
    }

    // syntactic sugar
    /**
     * @return {@link #name} (A name associated with the individual.)
     */
    public HumanName addName() { 
      HumanName t = new HumanName();
      this.name.add(t);
      return t;
    }

    /**
     * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #gender} (Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.)
     */
    public CodeableConcept getGender() { 
      return this.gender;
    }

    /**
     * @param value {@link #gender} (Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.)
     */
    public Patient setGender(CodeableConcept value) { 
      this.gender = value;
      return this;
    }

    /**
     * @return {@link #birthDate} (The date and time of birth for the individual.)
     */
    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    /**
     * @param value {@link #birthDate} (The date and time of birth for the individual.)
     */
    public Patient setBirthDate(DateTime value) { 
      this.birthDate = value;
      return this;
    }

    /**
     * @return The date and time of birth for the individual.
     */
    public DateAndTime getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    /**
     * @param value The date and time of birth for the individual.
     */
    public Patient setBirthDateSimple(DateAndTime value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTime();
        this.birthDate.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #deceased} (Indicates if the individual is deceased or not.)
     */
    public Type getDeceased() { 
      return this.deceased;
    }

    /**
     * @param value {@link #deceased} (Indicates if the individual is deceased or not.)
     */
    public Patient setDeceased(Type value) { 
      this.deceased = value;
      return this;
    }

    /**
     * @return {@link #address} (Addresses for the individual.)
     */
    public List<Address> getAddress() { 
      return this.address;
    }

    // syntactic sugar
    /**
     * @return {@link #address} (Addresses for the individual.)
     */
    public Address addAddress() { 
      Address t = new Address();
      this.address.add(t);
      return t;
    }

    /**
     * @return {@link #maritalStatus} (This field contains a patient's most recent marital (civil) status.)
     */
    public CodeableConcept getMaritalStatus() { 
      return this.maritalStatus;
    }

    /**
     * @param value {@link #maritalStatus} (This field contains a patient's most recent marital (civil) status.)
     */
    public Patient setMaritalStatus(CodeableConcept value) { 
      this.maritalStatus = value;
      return this;
    }

    /**
     * @return {@link #multipleBirth} (Indicates whether the patient is part of a multiple or indicates the actual birth order.)
     */
    public Type getMultipleBirth() { 
      return this.multipleBirth;
    }

    /**
     * @param value {@link #multipleBirth} (Indicates whether the patient is part of a multiple or indicates the actual birth order.)
     */
    public Patient setMultipleBirth(Type value) { 
      this.multipleBirth = value;
      return this;
    }

    /**
     * @return {@link #photo} (Image of the person.)
     */
    public List<Attachment> getPhoto() { 
      return this.photo;
    }

    // syntactic sugar
    /**
     * @return {@link #photo} (Image of the person.)
     */
    public Attachment addPhoto() { 
      Attachment t = new Attachment();
      this.photo.add(t);
      return t;
    }

    /**
     * @return {@link #contact} (A contact party (e.g. guardian, partner, friend) for the patient.)
     */
    public List<ContactComponent> getContact() { 
      return this.contact;
    }

    // syntactic sugar
    /**
     * @return {@link #contact} (A contact party (e.g. guardian, partner, friend) for the patient.)
     */
    public ContactComponent addContact() { 
      ContactComponent t = new ContactComponent();
      this.contact.add(t);
      return t;
    }

    /**
     * @return {@link #animal} (This element has a value if the patient is an animal.)
     */
    public AnimalComponent getAnimal() { 
      return this.animal;
    }

    /**
     * @param value {@link #animal} (This element has a value if the patient is an animal.)
     */
    public Patient setAnimal(AnimalComponent value) { 
      this.animal = value;
      return this;
    }

    /**
     * @return {@link #communication} (Languages which may be used to communicate with the patient about his or her health.)
     */
    public List<CodeableConcept> getCommunication() { 
      return this.communication;
    }

    // syntactic sugar
    /**
     * @return {@link #communication} (Languages which may be used to communicate with the patient about his or her health.)
     */
    public CodeableConcept addCommunication() { 
      CodeableConcept t = new CodeableConcept();
      this.communication.add(t);
      return t;
    }

    /**
     * @return {@link #careProvider} (Patient's nominated care provider.)
     */
    public List<ResourceReference> getCareProvider() { 
      return this.careProvider;
    }

    // syntactic sugar
    /**
     * @return {@link #careProvider} (Patient's nominated care provider.)
     */
    public ResourceReference addCareProvider() { 
      ResourceReference t = new ResourceReference();
      this.careProvider.add(t);
      return t;
    }

    /**
     * @return {@link #careProvider} (The actual objects that are the target of the reference. Patient's nominated care provider.)
     */
    public List<Resource> getCareProviderTarget() { 
      return this.careProviderTarget;
    }

    /**
     * @return {@link #managingOrganization} (Organization that is the custodian of the patient record.)
     */
    public ResourceReference getManagingOrganization() { 
      return this.managingOrganization;
    }

    /**
     * @param value {@link #managingOrganization} (Organization that is the custodian of the patient record.)
     */
    public Patient setManagingOrganization(ResourceReference value) { 
      this.managingOrganization = value;
      return this;
    }

    /**
     * @return {@link #managingOrganization} (The actual object that is the target of the reference. Organization that is the custodian of the patient record.)
     */
    public Organization getManagingOrganizationTarget() { 
      return this.managingOrganizationTarget;
    }

    /**
     * @param value {@link #managingOrganization} (The actual object that is the target of the reference. Organization that is the custodian of the patient record.)
     */
    public Patient setManagingOrganizationTarget(Organization value) { 
      this.managingOrganizationTarget = value;
      return this;
    }

    /**
     * @return {@link #link} (Link to another patient resource that concerns the same actual person.)
     */
    public List<PatientLinkComponent> getLink() { 
      return this.link;
    }

    // syntactic sugar
    /**
     * @return {@link #link} (Link to another patient resource that concerns the same actual person.)
     */
    public PatientLinkComponent addLink() { 
      PatientLinkComponent t = new PatientLinkComponent();
      this.link.add(t);
      return t;
    }

    /**
     * @return {@link #active} (Whether this patient record is in active use.)
     */
    public Boolean getActive() { 
      return this.active;
    }

    /**
     * @param value {@link #active} (Whether this patient record is in active use.)
     */
    public Patient setActive(Boolean value) { 
      this.active = value;
      return this;
    }

    /**
     * @return Whether this patient record is in active use.
     */
    public boolean getActiveSimple() { 
      return this.active == null ? false : this.active.getValue();
    }

    /**
     * @param value Whether this patient record is in active use.
     */
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
        childrenList.add(new Property("communication", "CodeableConcept", "Languages which may be used to communicate with the patient about his or her health.", 0, java.lang.Integer.MAX_VALUE, communication));
        childrenList.add(new Property("careProvider", "Resource(Organization|Practitioner)", "Patient's nominated care provider.", 0, java.lang.Integer.MAX_VALUE, careProvider));
        childrenList.add(new Property("managingOrganization", "Resource(Organization)", "Organization that is the custodian of the patient record.", 0, java.lang.Integer.MAX_VALUE, managingOrganization));
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
          dst.contact.add(i.copy());
        dst.animal = animal == null ? null : animal.copy();
        dst.communication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : communication)
          dst.communication.add(i.copy());
        dst.careProvider = new ArrayList<ResourceReference>();
        for (ResourceReference i : careProvider)
          dst.careProvider.add(i.copy());
        dst.managingOrganization = managingOrganization == null ? null : managingOrganization.copy();
        dst.link = new ArrayList<PatientLinkComponent>();
        for (PatientLinkComponent i : link)
          dst.link.add(i.copy());
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

