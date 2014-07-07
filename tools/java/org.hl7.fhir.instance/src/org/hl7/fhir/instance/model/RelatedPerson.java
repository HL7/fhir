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
 * Information about a person that is involved in the care for a patient, but who is not the target of healthcare, nor has a formal responsibility in the care process.
 */
public class RelatedPerson extends Resource {

    /**
     * Identifier for a person within a particular scope.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The patient this person is related to.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (The patient this person is related to.)
     */
    protected Patient patientTarget;

    /**
     * The nature of the relationship between a patient and the related person.
     */
    protected CodeableConcept relationship;

    /**
     * A name associated with the person.
     */
    protected HumanName name;

    /**
     * A contact detail for the person, e.g. a telephone number or an email address.
     */
    protected List<Contact> telecom = new ArrayList<Contact>();

    /**
     * Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.
     */
    protected CodeableConcept gender;

    /**
     * Address where the related person can be contacted or visited.
     */
    protected Address address;

    /**
     * Image of the person.
     */
    protected List<Attachment> photo = new ArrayList<Attachment>();

    private static final long serialVersionUID = -995377560L;

    public RelatedPerson() {
      super();
    }

    public RelatedPerson(ResourceReference patient) {
      super();
      this.patient = patient;
    }

    /**
     * @return {@link #identifier} (Identifier for a person within a particular scope.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (Identifier for a person within a particular scope.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #patient} (The patient this person is related to.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (The patient this person is related to.)
     */
    public RelatedPerson setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. The patient this person is related to.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. The patient this person is related to.)
     */
    public RelatedPerson setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #relationship} (The nature of the relationship between a patient and the related person.)
     */
    public CodeableConcept getRelationship() { 
      return this.relationship;
    }

    /**
     * @param value {@link #relationship} (The nature of the relationship between a patient and the related person.)
     */
    public RelatedPerson setRelationship(CodeableConcept value) { 
      this.relationship = value;
      return this;
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
    public RelatedPerson setName(HumanName value) { 
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
     * @return {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.)
     */
    public CodeableConcept getGender() { 
      return this.gender;
    }

    /**
     * @param value {@link #gender} (Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.)
     */
    public RelatedPerson setGender(CodeableConcept value) { 
      this.gender = value;
      return this;
    }

    /**
     * @return {@link #address} (Address where the related person can be contacted or visited.)
     */
    public Address getAddress() { 
      return this.address;
    }

    /**
     * @param value {@link #address} (Address where the related person can be contacted or visited.)
     */
    public RelatedPerson setAddress(Address value) { 
      this.address = value;
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

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier for a person within a particular scope.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("patient", "Resource(Patient)", "The patient this person is related to.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("relationship", "CodeableConcept", "The nature of the relationship between a patient and the related person.", 0, java.lang.Integer.MAX_VALUE, relationship));
        childrenList.add(new Property("name", "HumanName", "A name associated with the person.", 0, java.lang.Integer.MAX_VALUE, name));
        childrenList.add(new Property("telecom", "Contact", "A contact detail for the person, e.g. a telephone number or an email address.", 0, java.lang.Integer.MAX_VALUE, telecom));
        childrenList.add(new Property("gender", "CodeableConcept", "Administrative Gender - the gender that the person is considered to have for administration and record keeping purposes.", 0, java.lang.Integer.MAX_VALUE, gender));
        childrenList.add(new Property("address", "Address", "Address where the related person can be contacted or visited.", 0, java.lang.Integer.MAX_VALUE, address));
        childrenList.add(new Property("photo", "Attachment", "Image of the person.", 0, java.lang.Integer.MAX_VALUE, photo));
      }

      public RelatedPerson copy() {
        RelatedPerson dst = new RelatedPerson();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.patient = patient == null ? null : patient.copy();
        dst.relationship = relationship == null ? null : relationship.copy();
        dst.name = name == null ? null : name.copy();
        dst.telecom = new ArrayList<Contact>();
        for (Contact i : telecom)
          dst.telecom.add(i.copy());
        dst.gender = gender == null ? null : gender.copy();
        dst.address = address == null ? null : address.copy();
        dst.photo = new ArrayList<Attachment>();
        for (Attachment i : photo)
          dst.photo.add(i.copy());
        return dst;
      }

      protected RelatedPerson typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.RelatedPerson;
   }


}

