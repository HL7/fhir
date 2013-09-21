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

// Generated on Sun, Sep 22, 2013 08:29+1000 for FHIR v0.11

import java.util.*;

/**
 * Demographics and other administrative information about a person or animal receiving care or other health-related services.
 */
public class Patient extends Resource {

    public class ContactComponent extends Element {
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

        public void setName(HumanName value) { 
          this.name = value;
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

        public void setAddress(Address value) { 
          this.address = value;
        }

        public CodeableConcept getGender() { 
          return this.gender;
        }

        public void setGender(CodeableConcept value) { 
          this.gender = value;
        }

        public ResourceReference getOrganization() { 
          return this.organization;
        }

        public void setOrganization(ResourceReference value) { 
          this.organization = value;
        }

      public ContactComponent copy(Patient e) {
        ContactComponent dst = e.new ContactComponent();
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

    public class AnimalComponent extends Element {
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

        public CodeableConcept getSpecies() { 
          return this.species;
        }

        public void setSpecies(CodeableConcept value) { 
          this.species = value;
        }

        public CodeableConcept getBreed() { 
          return this.breed;
        }

        public void setBreed(CodeableConcept value) { 
          this.breed = value;
        }

        public CodeableConcept getGenderStatus() { 
          return this.genderStatus;
        }

        public void setGenderStatus(CodeableConcept value) { 
          this.genderStatus = value;
        }

      public AnimalComponent copy(Patient e) {
        AnimalComponent dst = e.new AnimalComponent();
        dst.species = species == null ? null : species.copy();
        dst.breed = breed == null ? null : breed.copy();
        dst.genderStatus = genderStatus == null ? null : genderStatus.copy();
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
     * A linked patient resource is a resource that concerns the same patient. Resources are linked after it is realized that at least one was created in error.
     */
    protected List<ResourceReference> link = new ArrayList<ResourceReference>();

    /**
     * Whether this patient record is in active use.
     */
    protected Boolean active;

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

    public void setGender(CodeableConcept value) { 
      this.gender = value;
    }

    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    public void setBirthDate(DateTime value) { 
      this.birthDate = value;
    }

    public String getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    public void setBirthDateSimple(String value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTime();
        this.birthDate.setValue(value);
      }
    }

    public Type getDeceased() { 
      return this.deceased;
    }

    public void setDeceased(Type value) { 
      this.deceased = value;
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

    public void setMaritalStatus(CodeableConcept value) { 
      this.maritalStatus = value;
    }

    public Type getMultipleBirth() { 
      return this.multipleBirth;
    }

    public void setMultipleBirth(Type value) { 
      this.multipleBirth = value;
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

    public void setAnimal(AnimalComponent value) { 
      this.animal = value;
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

    public void setProvider(ResourceReference value) { 
      this.provider = value;
    }

    public List<ResourceReference> getLink() { 
      return this.link;
    }

    // syntactic sugar
    public ResourceReference addLink() { 
      ResourceReference t = new ResourceReference();
      this.link.add(t);
      return t;
    }

    public Boolean getActive() { 
      return this.active;
    }

    public void setActive(Boolean value) { 
      this.active = value;
    }

    public boolean getActiveSimple() { 
      return this.active == null ? null : this.active.getValue();
    }

    public void setActiveSimple(boolean value) { 
      if (value == false)
        this.active = null;
      else {
        if (this.active == null)
          this.active = new Boolean();
        this.active.setValue(value);
      }
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
        dst.link = new ArrayList<ResourceReference>();
        for (ResourceReference i : link)
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

