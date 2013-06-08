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

// Generated on Sat, Jun 8, 2013 18:38+1000 for FHIR v0.09

import java.util.*;

/**
 * Demographics and other administrative information about a person or animal receiving care or other health-related services
 */
public class Patient extends Resource {

    public class ContactComponent extends Element {
        /**
         * The nature of the relationship between the patient and the contactperson
         */
        private List<CodeableConcept> relationship = new ArrayList<CodeableConcept>();

        /**
         * Details about the contact person
         */
        private Demographics details;

        /**
         * Organization on behalf of which the contact is acting or for which the contact is working.
         */
        private ResourceReference organization;

        public List<CodeableConcept> getRelationship() { 
          return this.relationship;
        }

        public Demographics getDetails() { 
          return this.details;
        }

        public void setDetails(Demographics value) { 
          this.details = value;
        }

        public ResourceReference getOrganization() { 
          return this.organization;
        }

        public void setOrganization(ResourceReference value) { 
          this.organization = value;
        }

  }

    public class AnimalComponent extends Element {
        /**
         * Identifies the high level categorization of the kind of animal
         */
        private CodeableConcept species;

        /**
         * Identifies the detailed categorization of the kind of animal.
         */
        private CodeableConcept breed;

        /**
         * Indicates the current state of the animal's reproductive organs
         */
        private CodeableConcept genderStatus;

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

  }

    /**
     * An identifier that applies to this person as a patient
     */
    private List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Patient Demographic details
     */
    private Demographics details;

    /**
     * A contact party (e.g. guardian, partner, friend) for the patient
     */
    private List<ContactComponent> contact = new ArrayList<ContactComponent>();

    /**
     * This element has a value if the patient is an animal
     */
    private AnimalComponent animal;

    /**
     * The provider for whom this is a patient record
     */
    private ResourceReference provider;

    /**
     * Indicates whether the patient is part of a multiple or indicates the actual birth order.
     */
    private Type multipleBirth;

    /**
     * Date of death of patient. May include time.
     */
    private DateTime deceasedDate;

    /**
     * A linked patient resource is a resource that concerns the same patient. Resources are linked after it is realized that at least one was created in error.
     */
    private List<ResourceReference> link = new ArrayList<ResourceReference>();

    /**
     * Whether this patient record is in active use
     */
    private Boolean active;

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    public Demographics getDetails() { 
      return this.details;
    }

    public void setDetails(Demographics value) { 
      this.details = value;
    }

    public List<ContactComponent> getContact() { 
      return this.contact;
    }

    public AnimalComponent getAnimal() { 
      return this.animal;
    }

    public void setAnimal(AnimalComponent value) { 
      this.animal = value;
    }

    public ResourceReference getProvider() { 
      return this.provider;
    }

    public void setProvider(ResourceReference value) { 
      this.provider = value;
    }

    public Type getMultipleBirth() { 
      return this.multipleBirth;
    }

    public void setMultipleBirth(Type value) { 
      this.multipleBirth = value;
    }

    public DateTime getDeceasedDate() { 
      return this.deceasedDate;
    }

    public void setDeceasedDate(DateTime value) { 
      this.deceasedDate = value;
    }

    public String getDeceasedDateSimple() { 
      return this.deceasedDate == null ? null : this.deceasedDate.getValue();
    }

    public void setDeceasedDateSimple(String value) { 
      if (value == null)
        this.deceasedDate = null;
      else {
        if (this.deceasedDate == null)
          this.deceasedDate = new DateTime();
        this.deceasedDate.setValue(value);
      }
    }

    public List<ResourceReference> getLink() { 
      return this.link;
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

  @Override
  public ResourceType getResourceType() {
    return ResourceType.Patient;
   }


}

