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

/**
 * Other is a conformant for handling resource concepts not yet defined for FHIR or outside HL7's scope of interest.
 */
public class Other extends DomainResource {

    /**
     * Identifier assigned to the resource for business purposes, outside the context of FHIR.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Identifies the 'type' of resource - equivalent to the resource name for other resources.
     */
    protected CodeableConcept code;

    /**
     * Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.
     */
    protected Reference subject;

    /**
     * The actual object that is the target of the reference (Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.)
     */
    protected Resource subjectTarget;

    /**
     * Indicates who was responsible for creating the resource instance.
     */
    protected Reference author;

    /**
     * The actual object that is the target of the reference (Indicates who was responsible for creating the resource instance.)
     */
    protected Resource authorTarget;

    /**
     * Identifies when the resource was first created.
     */
    protected DateType created;

    private static final long serialVersionUID = -58884148L;

    public Other() {
      super();
    }

    public Other(CodeableConcept code) {
      super();
      this.code = code;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the resource for business purposes, outside the context of FHIR.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Identifier assigned to the resource for business purposes, outside the context of FHIR.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #code} (Identifies the 'type' of resource - equivalent to the resource name for other resources.)
     */
    public CodeableConcept getCode() { 
      return this.code;
    }

    /**
     * @param value {@link #code} (Identifies the 'type' of resource - equivalent to the resource name for other resources.)
     */
    public Other setCode(CodeableConcept value) { 
      this.code = value;
      return this;
    }

    /**
     * @return {@link #subject} (Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.)
     */
    public Reference getSubject() { 
      return this.subject;
    }

    /**
     * @param value {@link #subject} (Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.)
     */
    public Other setSubject(Reference value) { 
      this.subject = value;
      return this;
    }

    /**
     * @return {@link #subject} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.)
     */
    public Resource getSubjectTarget() { 
      return this.subjectTarget;
    }

    /**
     * @param value {@link #subject} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the patient, practitioner, device or any other resource that is the "focus" of this resoruce.)
     */
    public Other setSubjectTarget(Resource value) { 
      this.subjectTarget = value;
      return this;
    }

    /**
     * @return {@link #author} (Indicates who was responsible for creating the resource instance.)
     */
    public Reference getAuthor() { 
      return this.author;
    }

    /**
     * @param value {@link #author} (Indicates who was responsible for creating the resource instance.)
     */
    public Other setAuthor(Reference value) { 
      this.author = value;
      return this;
    }

    /**
     * @return {@link #author} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Indicates who was responsible for creating the resource instance.)
     */
    public Resource getAuthorTarget() { 
      return this.authorTarget;
    }

    /**
     * @param value {@link #author} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Indicates who was responsible for creating the resource instance.)
     */
    public Other setAuthorTarget(Resource value) { 
      this.authorTarget = value;
      return this;
    }

    /**
     * @return {@link #created} (Identifies when the resource was first created.). This is the underlying object with id, value and extensions. The accessor "getCreated" gives direct access to the value
     */
    public DateType getCreatedElement() { 
      return this.created;
    }

    /**
     * @param value {@link #created} (Identifies when the resource was first created.). This is the underlying object with id, value and extensions. The accessor "getCreated" gives direct access to the value
     */
    public Other setCreatedElement(DateType value) { 
      this.created = value;
      return this;
    }

    /**
     * @return Identifies when the resource was first created.
     */
    public DateAndTime getCreated() { 
      return this.created == null ? null : this.created.getValue();
    }

    /**
     * @param value Identifies when the resource was first created.
     */
    public Other setCreated(DateAndTime value) { 
      if (value == null)
        this.created = null;
      else {
        if (this.created == null)
          this.created = new DateType();
        this.created.setValue(value);
      }
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier assigned to the resource for business purposes, outside the context of FHIR.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("code", "CodeableConcept", "Identifies the 'type' of resource - equivalent to the resource name for other resources.", 0, java.lang.Integer.MAX_VALUE, code));
        childrenList.add(new Property("subject", "Reference(Any)", "Identifies the patient, practitioner, device or any other resource that is the 'focus' of this resoruce.", 0, java.lang.Integer.MAX_VALUE, subject));
        childrenList.add(new Property("author", "Reference(Practitioner|Patient|RelatedPerson)", "Indicates who was responsible for creating the resource instance.", 0, java.lang.Integer.MAX_VALUE, author));
        childrenList.add(new Property("created", "date", "Identifies when the resource was first created.", 0, java.lang.Integer.MAX_VALUE, created));
      }

      public Other copy() {
        Other dst = new Other();
        copyValues(dst);
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.code = code == null ? null : code.copy();
        dst.subject = subject == null ? null : subject.copy();
        dst.author = author == null ? null : author.copy();
        dst.created = created == null ? null : created.copy();
        return dst;
      }

      protected Other typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Other;
   }


}

