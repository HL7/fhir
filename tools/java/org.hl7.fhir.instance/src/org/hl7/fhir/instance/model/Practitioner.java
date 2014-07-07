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
 * A person who is directly or indirectly involved in the provisioning of healthcare.
 */
public class Practitioner extends Resource {

    public static class PractitionerQualificationComponent extends BackboneElement {
        /**
         * Coded representation of the qualification.
         */
        protected CodeableConcept code;

        /**
         * Period during which the qualification is valid.
         */
        protected Period period;

        /**
         * Organization that regulates and issues the qualification.
         */
        protected ResourceReference issuer;

        /**
         * The actual object that is the target of the reference (Organization that regulates and issues the qualification.)
         */
        protected Organization issuerTarget;

        private static final long serialVersionUID = -878582183L;

      public PractitionerQualificationComponent() {
        super();
      }

      public PractitionerQualificationComponent(CodeableConcept code) {
        super();
        this.code = code;
      }

        /**
         * @return {@link #code} (Coded representation of the qualification.)
         */
        public CodeableConcept getCode() { 
          return this.code;
        }

        /**
         * @param value {@link #code} (Coded representation of the qualification.)
         */
        public PractitionerQualificationComponent setCode(CodeableConcept value) { 
          this.code = value;
          return this;
        }

        /**
         * @return {@link #period} (Period during which the qualification is valid.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (Period during which the qualification is valid.)
         */
        public PractitionerQualificationComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        /**
         * @return {@link #issuer} (Organization that regulates and issues the qualification.)
         */
        public ResourceReference getIssuer() { 
          return this.issuer;
        }

        /**
         * @param value {@link #issuer} (Organization that regulates and issues the qualification.)
         */
        public PractitionerQualificationComponent setIssuer(ResourceReference value) { 
          this.issuer = value;
          return this;
        }

        /**
         * @return {@link #issuer} (The actual object that is the target of the reference. Organization that regulates and issues the qualification.)
         */
        public Organization getIssuerTarget() { 
          return this.issuerTarget;
        }

        /**
         * @param value {@link #issuer} (The actual object that is the target of the reference. Organization that regulates and issues the qualification.)
         */
        public PractitionerQualificationComponent setIssuerTarget(Organization value) { 
          this.issuerTarget = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("code", "CodeableConcept", "Coded representation of the qualification.", 0, java.lang.Integer.MAX_VALUE, code));
          childrenList.add(new Property("period", "Period", "Period during which the qualification is valid.", 0, java.lang.Integer.MAX_VALUE, period));
          childrenList.add(new Property("issuer", "Resource(Organization)", "Organization that regulates and issues the qualification.", 0, java.lang.Integer.MAX_VALUE, issuer));
        }

      public PractitionerQualificationComponent copy() {
        PractitionerQualificationComponent dst = new PractitionerQualificationComponent();
        dst.code = code == null ? null : code.copy();
        dst.period = period == null ? null : period.copy();
        dst.issuer = issuer == null ? null : issuer.copy();
        return dst;
      }

  }

    /**
     * An identifier that applies to this person in this role.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * A name associated with the person.
     */
    protected HumanName name;

    /**
     * A contact detail for the practitioner, e.g. a telephone number or an email address.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * The postal address where the practitioner can be found or visited or to which mail can be delivered.
     */
    protected Address address;

    /**
     * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
     */
    protected CodeableConcept gender;

    /**
     * The date and time of birth for the practitioner.
     */
    protected DateTime birthDate;

    /**
     * Image of the person.
     */
    protected List<Attachment> photo = new ArrayList<Attachment>();

    /**
     * The organization that the practitioner represents.
     */
    protected ResourceReference organization;

    /**
     * The actual object that is the target of the reference (The organization that the practitioner represents.)
     */
    protected Organization organizationTarget;

    /**
     * Roles which this practitioner is authorized to perform for the organization.
     */
    protected List<CodeableConcept> role = new ArrayList<CodeableConcept>();

    /**
     * Specific specialty of the practitioner.
     */
    protected List<CodeableConcept> specialty = new ArrayList<CodeableConcept>();

    /**
     * The period during which the person is authorized to act as a practitioner in these role(s) for the organization.
     */
    protected Period period;

    /**
     * The location(s) at which this practitioner provides care.
     */
    protected List<ResourceReference> location = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (The location(s) at which this practitioner provides care.)
     */
    protected List<Location> locationTarget = new ArrayList<Location>();


    /**
     * Qualifications obtained by training and certification.
     */
    protected List<PractitionerQualificationComponent> qualification = new ArrayList<PractitionerQualificationComponent>();

    /**
     * A language the practitioner is able to use in patient communication.
     */
    protected List<CodeableConcept> communication = new ArrayList<CodeableConcept>();

    private static final long serialVersionUID = -1959079809L;

    public Practitioner() {
      super();
    }

    /**
     * @return {@link #identifier} (An identifier that applies to this person in this role.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (An identifier that applies to this person in this role.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
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
    public Practitioner setName(HumanName value) { 
      this.name = value;
      return this;
    }

    /**
     * @return {@link #telecom} (A contact detail for the practitioner, e.g. a telephone number or an email address.)
     */
    public List<Contact> getTelecom() { 
      return this.telecom;
    }

    // syntactic sugar
    /**
     * @return {@link #telecom} (A contact detail for the practitioner, e.g. a telephone number or an email address.)
     */
    public Contact addTelecom() { 
      Contact t = new Contact();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #address} (The postal address where the practitioner can be found or visited or to which mail can be delivered.)
     */
    public Address getAddress() { 
      return this.address;
    }

    /**
     * @param value {@link #address} (The postal address where the practitioner can be found or visited or to which mail can be delivered.)
     */
    public Practitioner setAddress(Address value) { 
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
    public Practitioner setGender(CodeableConcept value) { 
      this.gender = value;
      return this;
    }

    /**
     * @return {@link #birthDate} (The date and time of birth for the practitioner.)
     */
    public DateTime getBirthDate() { 
      return this.birthDate;
    }

    /**
     * @param value {@link #birthDate} (The date and time of birth for the practitioner.)
     */
    public Practitioner setBirthDate(DateTime value) { 
      this.birthDate = value;
      return this;
    }

    /**
     * @return The date and time of birth for the practitioner.
     */
    public DateAndTime getBirthDateSimple() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    /**
     * @param value The date and time of birth for the practitioner.
     */
    public Practitioner setBirthDateSimple(DateAndTime value) { 
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
     * @return {@link #organization} (The organization that the practitioner represents.)
     */
    public ResourceReference getOrganization() { 
      return this.organization;
    }

    /**
     * @param value {@link #organization} (The organization that the practitioner represents.)
     */
    public Practitioner setOrganization(ResourceReference value) { 
      this.organization = value;
      return this;
    }

    /**
     * @return {@link #organization} (The actual object that is the target of the reference. The organization that the practitioner represents.)
     */
    public Organization getOrganizationTarget() { 
      return this.organizationTarget;
    }

    /**
     * @param value {@link #organization} (The actual object that is the target of the reference. The organization that the practitioner represents.)
     */
    public Practitioner setOrganizationTarget(Organization value) { 
      this.organizationTarget = value;
      return this;
    }

    /**
     * @return {@link #role} (Roles which this practitioner is authorized to perform for the organization.)
     */
    public List<CodeableConcept> getRole() { 
      return this.role;
    }

    // syntactic sugar
    /**
     * @return {@link #role} (Roles which this practitioner is authorized to perform for the organization.)
     */
    public CodeableConcept addRole() { 
      CodeableConcept t = new CodeableConcept();
      this.role.add(t);
      return t;
    }

    /**
     * @return {@link #specialty} (Specific specialty of the practitioner.)
     */
    public List<CodeableConcept> getSpecialty() { 
      return this.specialty;
    }

    // syntactic sugar
    /**
     * @return {@link #specialty} (Specific specialty of the practitioner.)
     */
    public CodeableConcept addSpecialty() { 
      CodeableConcept t = new CodeableConcept();
      this.specialty.add(t);
      return t;
    }

    /**
     * @return {@link #period} (The period during which the person is authorized to act as a practitioner in these role(s) for the organization.)
     */
    public Period getPeriod() { 
      return this.period;
    }

    /**
     * @param value {@link #period} (The period during which the person is authorized to act as a practitioner in these role(s) for the organization.)
     */
    public Practitioner setPeriod(Period value) { 
      this.period = value;
      return this;
    }

    /**
     * @return {@link #location} (The location(s) at which this practitioner provides care.)
     */
    public List<ResourceReference> getLocation() { 
      return this.location;
    }

    // syntactic sugar
    /**
     * @return {@link #location} (The location(s) at which this practitioner provides care.)
     */
    public ResourceReference addLocation() { 
      ResourceReference t = new ResourceReference();
      this.location.add(t);
      return t;
    }

    /**
     * @return {@link #location} (The actual objects that are the target of the reference. The location(s) at which this practitioner provides care.)
     */
    public List<Location> getLocationTarget() { 
      return this.locationTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #location} (Add an actual object that is the target of the reference. The location(s) at which this practitioner provides care.)
     */
    public Location addLocationTarget() { 
      Location r = new Location();
      this.locationTarget.add(r);
      return r;
    }

    /**
     * @return {@link #qualification} (Qualifications obtained by training and certification.)
     */
    public List<PractitionerQualificationComponent> getQualification() { 
      return this.qualification;
    }

    // syntactic sugar
    /**
     * @return {@link #qualification} (Qualifications obtained by training and certification.)
     */
    public PractitionerQualificationComponent addQualification() { 
      PractitionerQualificationComponent t = new PractitionerQualificationComponent();
      this.qualification.add(t);
      return t;
    }

    /**
     * @return {@link #communication} (A language the practitioner is able to use in patient communication.)
     */
    public List<CodeableConcept> getCommunication() { 
      return this.communication;
    }

    // syntactic sugar
    /**
     * @return {@link #communication} (A language the practitioner is able to use in patient communication.)
     */
    public CodeableConcept addCommunication() { 
      CodeableConcept t = new CodeableConcept();
      this.communication.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "An identifier that applies to this person in this role.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "HumanName", "A name associated with the person.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("telecom", "Contact", "A contact detail for the practitioner, e.g. a telephone number or an email address.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("address", "Address", "The postal address where the practitioner can be found or visited or to which mail can be delivered.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("gender", "CodeableConcept", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        childrenList.add(new Property("birthDate", "dateTime", "The date and time of birth for the practitioner.", 0, java.lang.Integer.MAX_VALUE, birthDate));
        childrenList.add(new Property("photo", "Attachment", "Image of the person.", 0, java.lang.Integer.MAX_VALUE, photo));
        childrenList.add(new Property("organization", "Resource(Organization)", "The organization that the practitioner represents.", 0, java.lang.Integer.MAX_VALUE, organization));
        childrenList.add(new Property("role", "CodeableConcept", "Roles which this practitioner is authorized to perform for the organization.", 0, java.lang.Integer.MAX_VALUE, role));
        childrenList.add(new Property("specialty", "CodeableConcept", "Specific specialty of the practitioner.", 0, java.lang.Integer.MAX_VALUE, specialty));
        childrenList.add(new Property("period", "Period", "The period during which the person is authorized to act as a practitioner in these role(s) for the organization.", 0, java.lang.Integer.MAX_VALUE, period));
        childrenList.add(new Property("location", "Resource(Location)", "The location(s) at which this practitioner provides care.", 0, java.lang.Integer.MAX_VALUE, location));
        childrenList.add(new Property("qualification", "", "Qualifications obtained by training and certification.", 0, java.lang.Integer.MAX_VALUE, qualification));
        childrenList.add(new Property("communication", "CodeableConcept", "A language the practitioner is able to use in patient communication.", 0, java.lang.Integer.MAX_VALUE, communication));
      }

      public Practitioner copy() {
        Practitioner dst = new Practitioner();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.address = address == null ? null : address.copy();
        dst.gender = gender == null ? null : gender.copy();
        dst.birthDate = birthDate == null ? null : birthDate.copy();
        dst.photo = new ArrayList<Attachment>();
        for (Attachment i : photo)
          dst.photo.add(i.copy());
        dst.organization = organization == null ? null : organization.copy();
        dst.role = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : role)
          dst.role.add(i.copy());
        dst.specialty = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : specialty)
          dst.specialty.add(i.copy());
        dst.period = period == null ? null : period.copy();
        dst.location = new ArrayList<ResourceReference>();
        for (ResourceReference i : location)
          dst.location.add(i.copy());
        dst.qualification = new ArrayList<PractitionerQualificationComponent>();
        for (PractitionerQualificationComponent i : qualification)
          dst.qualification.add(i.copy());
        dst.communication = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : communication)
          dst.communication.add(i.copy());
        return dst;
      }

      protected Practitioner typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Practitioner;
   }


}

