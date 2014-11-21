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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Demographics and other administrative information about a person or animal receiving care or other health-related services.
 */
public class Patient extends DomainResource {

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

    public enum LinkType {
        REPLACE, // The patient resource containing this link must no longer be used. The link points forward to another patient resource that must be used in lieu of the patient resource that contains the link.
        REFER, // The patient resource containing this link is in use and valid but not considered the main source of information about a patient. The link points forward to another patient resource that should be consulted to retrieve additional patient information.
        SEEALSO, // The patient resource containing this link is in use and valid, but points to another patient resource that is known to contain data about the same person. Data in this resource might overlap or contradict information found in the other patient resource. This link does not indicate any relative importance of the resources concerned, and both should be regarded as equally valid.
        NULL; // added to help the parsers
        public static LinkType fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("replace".equals(codeString))
          return REPLACE;
        if ("refer".equals(codeString))
          return REFER;
        if ("seealso".equals(codeString))
          return SEEALSO;
        throw new Exception("Unknown LinkType code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case REPLACE: return "replace";
            case REFER: return "refer";
            case SEEALSO: return "seealso";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case REPLACE: return "The patient resource containing this link must no longer be used. The link points forward to another patient resource that must be used in lieu of the patient resource that contains the link.";
            case REFER: return "The patient resource containing this link is in use and valid but not considered the main source of information about a patient. The link points forward to another patient resource that should be consulted to retrieve additional patient information.";
            case SEEALSO: return "The patient resource containing this link is in use and valid, but points to another patient resource that is known to contain data about the same person. Data in this resource might overlap or contradict information found in the other patient resource. This link does not indicate any relative importance of the resources concerned, and both should be regarded as equally valid.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case REPLACE: return "replace";
            case REFER: return "refer";
            case SEEALSO: return "see also";
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
          return LinkType.REPLACE;
        if ("refer".equals(codeString))
          return LinkType.REFER;
        if ("seealso".equals(codeString))
          return LinkType.SEEALSO;
        throw new Exception("Unknown LinkType code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == LinkType.REPLACE)
        return "replace";
      if (code == LinkType.REFER)
        return "refer";
      if (code == LinkType.SEEALSO)
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
        protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

        /**
         * Address for the contact person.
         */
        protected Address address;

        /**
         * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
         */
        protected Enumeration<AdministrativeGender> gender;

        /**
         * Organization on behalf of which the contact is acting or for which the contact is working.
         */
        protected Reference organization;

        /**
         * The actual object that is the target of the reference (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        protected Organization organizationTarget;

        /**
         * The period during which this person or organisation is valid to be contacted relating to this patient.
         */
        protected Period period;

        private static final long serialVersionUID = -455382710L;

      public ContactComponent() {
        super();
      }

        /**
         * @return {@link #relationship} (The nature of the relationship between the patient and the contact person.)
         */
        public List<CodeableConcept> getRelationship() { 
          return this.relationship;
        }

        /**
         * @return {@link #relationship} (The nature of the relationship between the patient and the contact person.)
         */
    // syntactic sugar
        public CodeableConcept addRelationship() { //3
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
        public List<ContactPoint> getTelecom() { 
          return this.telecom;
        }

        /**
         * @return {@link #telecom} (A contact detail for the person, e.g. a telephone number or an email address.)
         */
    // syntactic sugar
        public ContactPoint addTelecom() { //3
          ContactPoint t = new ContactPoint();
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
         * @return {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
         */
        public Enumeration<AdministrativeGender> getGenderElement() { 
          return this.gender;
        }

        /**
         * @param value {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
         */
        public ContactComponent setGenderElement(Enumeration<AdministrativeGender> value) { 
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
        public ContactComponent setGender(AdministrativeGender value) { 
          if (value == null)
            this.gender = null;
          else {
            if (this.gender == null)
              this.gender = new Enumeration<AdministrativeGender>();
            this.gender.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #organization} (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public Reference getOrganization() { 
          return this.organization;
        }

        /**
         * @param value {@link #organization} (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public ContactComponent setOrganization(Reference value) { 
          this.organization = value;
          return this;
        }

        /**
         * @return {@link #organization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public Organization getOrganizationTarget() { 
          return this.organizationTarget;
        }

        /**
         * @param value {@link #organization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Organization on behalf of which the contact is acting or for which the contact is working.)
         */
        public ContactComponent setOrganizationTarget(Organization value) { 
          this.organizationTarget = value;
          return this;
        }

        /**
         * @return {@link #period} (The period during which this person or organisation is valid to be contacted relating to this patient.)
         */
        public Period getPeriod() { 
          return this.period;
        }

        /**
         * @param value {@link #period} (The period during which this person or organisation is valid to be contacted relating to this patient.)
         */
        public ContactComponent setPeriod(Period value) { 
          this.period = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("relationship", "CodeableConcept", "The nature of the relationship between the patient and the contact person.", 0, java.lang.Integer.MAX_VALUE, relationship));
          childrenList.add(new Property("name", "HumanName", "A name associated with the person.", 0, java.lang.Integer.MAX_VALUE, name));
          childrenList.add(new Property("telecom", "ContactPoint", "A contact detail for the person, e.g. a telephone number or an email address.", 0, java.lang.Integer.MAX_VALUE, telecom));
          childrenList.add(new Property("address", "Address", "Address for the contact person.", 0, java.lang.Integer.MAX_VALUE, address));
          childrenList.add(new Property("gender", "code", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
          childrenList.add(new Property("organization", "Reference(Organization)", "Organization on behalf of which the contact is acting or for which the contact is working.", 0, java.lang.Integer.MAX_VALUE, organization));
          childrenList.add(new Property("period", "Period", "The period during which this person or organisation is valid to be contacted relating to this patient.", 0, java.lang.Integer.MAX_VALUE, period));
        }

      public ContactComponent copy() {
        ContactComponent dst = new ContactComponent();
        copyValues(dst);
        dst.relationship = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : relationship)
          dst.relationship.add(i.copy());
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
          dst.telecom.add(i.copy());
        dst.address = address == null ? null : address.copy();
        dst.gender = gender == null ? null : gender.copy();
        dst.organization = organization == null ? null : organization.copy();
        dst.period = period == null ? null : period.copy();
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
        copyValues(dst);
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
        protected Reference other;

        /**
         * The actual object that is the target of the reference (The other patient resource that the link refers to.)
         */
        protected Patient otherTarget;

        /**
         * The type of link between this patient resource and another patient resource.
         */
        protected Enumeration<LinkType> type;

        private static final long serialVersionUID = -1942104050L;

      public PatientLinkComponent() {
        super();
      }

      public PatientLinkComponent(Reference other, Enumeration<LinkType> type) {
        super();
        this.other = other;
        this.type = type;
      }

        /**
         * @return {@link #other} (The other patient resource that the link refers to.)
         */
        public Reference getOther() { 
          return this.other;
        }

        /**
         * @param value {@link #other} (The other patient resource that the link refers to.)
         */
        public PatientLinkComponent setOther(Reference value) { 
          this.other = value;
          return this;
        }

        /**
         * @return {@link #other} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The other patient resource that the link refers to.)
         */
        public Patient getOtherTarget() { 
          return this.otherTarget;
        }

        /**
         * @param value {@link #other} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The other patient resource that the link refers to.)
         */
        public PatientLinkComponent setOtherTarget(Patient value) { 
          this.otherTarget = value;
          return this;
        }

        /**
         * @return {@link #type} (The type of link between this patient resource and another patient resource.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public Enumeration<LinkType> getTypeElement() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (The type of link between this patient resource and another patient resource.). This is the underlying object with id, value and extensions. The accessor "getType" gives direct access to the value
         */
        public PatientLinkComponent setTypeElement(Enumeration<LinkType> value) { 
          this.type = value;
          return this;
        }

        /**
         * @return The type of link between this patient resource and another patient resource.
         */
        public LinkType getType() { 
          return this.type == null ? null : this.type.getValue();
        }

        /**
         * @param value The type of link between this patient resource and another patient resource.
         */
        public PatientLinkComponent setType(LinkType value) { 
            if (this.type == null)
              this.type = new Enumeration<LinkType>();
            this.type.setValue(value);
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("other", "Reference(Patient)", "The other patient resource that the link refers to.", 0, java.lang.Integer.MAX_VALUE, other));
          childrenList.add(new Property("type", "code", "The type of link between this patient resource and another patient resource.", 0, java.lang.Integer.MAX_VALUE, type));
        }

      public PatientLinkComponent copy() {
        PatientLinkComponent dst = new PatientLinkComponent();
        copyValues(dst);
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
    protected List<ContactPoint> telecom = new ArrayList<ContactPoint>();

    /**
     * Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.
     */
    protected Enumeration<AdministrativeGender> gender;

    /**
     * The date and time of birth for the individual.
     */
    protected DateTimeType birthDate;

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
    protected List<Reference> careProvider = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Patient's nominated care provider.)
     */
    protected List<Resource> careProviderTarget = new ArrayList<Resource>();


    /**
     * Organization that is the custodian of the patient record.
     */
    protected Reference managingOrganization;

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
    protected BooleanType active;

    private static final long serialVersionUID = -340628307L;

    public Patient() {
      super();
    }

    /**
     * @return {@link #identifier} (An identifier that applies to this person as a patient.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (An identifier that applies to this person as a patient.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
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

    /**
     * @return {@link #name} (A name associated with the individual.)
     */
    // syntactic sugar
    public HumanName addName() { //3
      HumanName t = new HumanName();
      this.name.add(t);
      return t;
    }

    /**
     * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.)
     */
    public List<ContactPoint> getTelecom() { 
      return this.telecom;
    }

    /**
     * @return {@link #telecom} (A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.)
     */
    // syntactic sugar
    public ContactPoint addTelecom() { //3
      ContactPoint t = new ContactPoint();
      this.telecom.add(t);
      return t;
    }

    /**
     * @return {@link #gender} (Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
     */
    public Enumeration<AdministrativeGender> getGenderElement() { 
      return this.gender;
    }

    /**
     * @param value {@link #gender} (Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.). This is the underlying object with id, value and extensions. The accessor "getGender" gives direct access to the value
     */
    public Patient setGenderElement(Enumeration<AdministrativeGender> value) { 
      this.gender = value;
      return this;
    }

    /**
     * @return Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.
     */
    public AdministrativeGender getGender() { 
      return this.gender == null ? null : this.gender.getValue();
    }

    /**
     * @param value Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.
     */
    public Patient setGender(AdministrativeGender value) { 
      if (value == null)
        this.gender = null;
      else {
        if (this.gender == null)
          this.gender = new Enumeration<AdministrativeGender>();
        this.gender.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #birthDate} (The date and time of birth for the individual.). This is the underlying object with id, value and extensions. The accessor "getBirthDate" gives direct access to the value
     */
    public DateTimeType getBirthDateElement() { 
      return this.birthDate;
    }

    /**
     * @param value {@link #birthDate} (The date and time of birth for the individual.). This is the underlying object with id, value and extensions. The accessor "getBirthDate" gives direct access to the value
     */
    public Patient setBirthDateElement(DateTimeType value) { 
      this.birthDate = value;
      return this;
    }

    /**
     * @return The date and time of birth for the individual.
     */
    public DateAndTime getBirthDate() { 
      return this.birthDate == null ? null : this.birthDate.getValue();
    }

    /**
     * @param value The date and time of birth for the individual.
     */
    public Patient setBirthDate(DateAndTime value) { 
      if (value == null)
        this.birthDate = null;
      else {
        if (this.birthDate == null)
          this.birthDate = new DateTimeType();
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

    /**
     * @return {@link #address} (Addresses for the individual.)
     */
    // syntactic sugar
    public Address addAddress() { //3
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

    /**
     * @return {@link #photo} (Image of the person.)
     */
    // syntactic sugar
    public Attachment addPhoto() { //3
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

    /**
     * @return {@link #contact} (A contact party (e.g. guardian, partner, friend) for the patient.)
     */
    // syntactic sugar
    public ContactComponent addContact() { //3
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

    /**
     * @return {@link #communication} (Languages which may be used to communicate with the patient about his or her health.)
     */
    // syntactic sugar
    public CodeableConcept addCommunication() { //3
      CodeableConcept t = new CodeableConcept();
      this.communication.add(t);
      return t;
    }

    /**
     * @return {@link #careProvider} (Patient's nominated care provider.)
     */
    public List<Reference> getCareProvider() { 
      return this.careProvider;
    }

    /**
     * @return {@link #careProvider} (Patient's nominated care provider.)
     */
    // syntactic sugar
    public Reference addCareProvider() { //3
      Reference t = new Reference();
      this.careProvider.add(t);
      return t;
    }

    /**
     * @return {@link #careProvider} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Patient's nominated care provider.)
     */
    public List<Resource> getCareProviderTarget() { 
      return this.careProviderTarget;
    }

    /**
     * @return {@link #managingOrganization} (Organization that is the custodian of the patient record.)
     */
    public Reference getManagingOrganization() { 
      return this.managingOrganization;
    }

    /**
     * @param value {@link #managingOrganization} (Organization that is the custodian of the patient record.)
     */
    public Patient setManagingOrganization(Reference value) { 
      this.managingOrganization = value;
      return this;
    }

    /**
     * @return {@link #managingOrganization} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Organization that is the custodian of the patient record.)
     */
    public Organization getManagingOrganizationTarget() { 
      return this.managingOrganizationTarget;
    }

    /**
     * @param value {@link #managingOrganization} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Organization that is the custodian of the patient record.)
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

    /**
     * @return {@link #link} (Link to another patient resource that concerns the same actual person.)
     */
    // syntactic sugar
    public PatientLinkComponent addLink() { //3
      PatientLinkComponent t = new PatientLinkComponent();
      this.link.add(t);
      return t;
    }

    /**
     * @return {@link #active} (Whether this patient record is in active use.). This is the underlying object with id, value and extensions. The accessor "getActive" gives direct access to the value
     */
    public BooleanType getActiveElement() { 
      return this.active;
    }

    /**
     * @param value {@link #active} (Whether this patient record is in active use.). This is the underlying object with id, value and extensions. The accessor "getActive" gives direct access to the value
     */
    public Patient setActiveElement(BooleanType value) { 
      this.active = value;
      return this;
    }

    /**
     * @return Whether this patient record is in active use.
     */
    public boolean getActive() { 
      return this.active == null ? false : this.active.getValue();
    }

    /**
     * @param value Whether this patient record is in active use.
     */
    public Patient setActive(boolean value) { 
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
        childrenList.add(new Property("identifier", "Identifier", "An identifier that applies to this person as a patient.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("name", "HumanName", "A name associated with the individual.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("telecom", "ContactPoint", "A contact detail (e.g. a telephone number or an email address) by which the individual may be contacted.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("gender", "code", "Administrative Gender - the gender that the patient is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        childrenList.add(new Property("birthDate", "dateTime", "The date and time of birth for the individual.", 0, java.lang.Integer.MAX_VALUE, birthDate));
        childrenList.add(new Property("deceased[x]", "boolean|dateTime", "Indicates if the individual is deceased or not.", 0, java.lang.Integer.MAX_VALUE, deceased));
        childrenList.add(new Property("address", "Address", "Addresses for the individual.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("maritalStatus", "CodeableConcept", "This field contains a patient's most recent marital (civil) status.", 0, java.lang.Integer.MAX_VALUE, maritalStatus));
        childrenList.add(new Property("multipleBirth[x]", "boolean|integer", "Indicates whether the patient is part of a multiple or indicates the actual birth order.", 0, java.lang.Integer.MAX_VALUE, multipleBirth));
        childrenList.add(new Property("photo", "Attachment", "Image of the person.", 0, java.lang.Integer.MAX_VALUE, photo));
        childrenList.add(new Property("contact", "", "A contact party (e.g. guardian, partner, friend) for the patient.", 0, java.lang.Integer.MAX_VALUE, contact));
        childrenList.add(new Property("animal", "", "This element has a value if the patient is an animal.", 0, java.lang.Integer.MAX_VALUE, animal));
        childrenList.add(new Property("communication", "CodeableConcept", "Languages which may be used to communicate with the patient about his or her health.", 0, java.lang.Integer.MAX_VALUE, communication));
        childrenList.add(new Property("careProvider", "Reference(Organization|Practitioner)", "Patient's nominated care provider.", 0, java.lang.Integer.MAX_VALUE, careProvider));
        childrenList.add(new Property("managingOrganization", "Reference(Organization)", "Organization that is the custodian of the patient record.", 0, java.lang.Integer.MAX_VALUE, managingOrganization));
        childrenList.add(new Property("link", "", "Link to another patient resource that concerns the same actual person.", 0, java.lang.Integer.MAX_VALUE, link));
        childrenList.add(new Property("active", "boolean", "Whether this patient record is in active use.", 0, java.lang.Integer.MAX_VALUE, active));
      }

      public Patient copy() {
        Patient dst = new Patient();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.name = new ArrayList<HumanName>();
        for (HumanName i : name)
          dst.name.add(i.copy());
        dst.telecom = new ArrayList<ContactPoint>();
        for (ContactPoint i : telecom)
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
        dst.careProvider = new ArrayList<Reference>();
        for (Reference i : careProvider)
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

