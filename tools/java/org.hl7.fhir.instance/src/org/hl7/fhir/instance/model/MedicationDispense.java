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
 * Dispensing a medication to a named patient.  This includes a description of the supply provided and the instructions for administering the medication.
 */
public class MedicationDispense extends Resource {

    public class MedicationDispenseDispenseComponent extends Element {
        /**
         * Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.
         */
        private Identifier identifier;

        /**
         * A code specifying the state of the dispense event.
         */
        private CodeableConcept status;

        /**
         * Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.
         */
        private CodeableConcept type;

        /**
         * The amount of medication that has been dispensed. Includes unit of measure.
         */
        private Quantity quantity;

        /**
         * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
         */
        private ResourceReference medication;

        /**
         * The time the dispense event occurred.
         */
        private Period whenPrepared;

        /**
         * The time the dispense event occurred.
         */
        private Period whenHandedOver;

        /**
         * Identification of the facility/location where the medication was shipped to, as part of the dispense event.
         */
        private ResourceReference destination;

        /**
         * Identifies the person who picked up the medication.
         */
        private List<ResourceReference> receiver = new ArrayList<ResourceReference>();

        /**
         * Indicates how the medication is to be used by the patient
         */
        private List<MedicationDispenseDispenseDosageComponent> dosage = new ArrayList<MedicationDispenseDispenseDosageComponent>();

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public void setIdentifier(Identifier value) { 
          this.identifier = value;
        }

        public CodeableConcept getStatus() { 
          return this.status;
        }

        public void setStatus(CodeableConcept value) { 
          this.status = value;
        }

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

        public ResourceReference getMedication() { 
          return this.medication;
        }

        public void setMedication(ResourceReference value) { 
          this.medication = value;
        }

        public Period getWhenPrepared() { 
          return this.whenPrepared;
        }

        public void setWhenPrepared(Period value) { 
          this.whenPrepared = value;
        }

        public Period getWhenHandedOver() { 
          return this.whenHandedOver;
        }

        public void setWhenHandedOver(Period value) { 
          this.whenHandedOver = value;
        }

        public ResourceReference getDestination() { 
          return this.destination;
        }

        public void setDestination(ResourceReference value) { 
          this.destination = value;
        }

        public List<ResourceReference> getReceiver() { 
          return this.receiver;
        }

        public List<MedicationDispenseDispenseDosageComponent> getDosage() { 
          return this.dosage;
        }

  }

    public class MedicationDispenseDispenseDosageComponent extends Element {
        /**
         * The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013"
         */
        private Schedule timing;

        /**
         * A coded specification of the anatomic site where the medication first enters the body
         */
        private CodeableConcept site;

        /**
         * A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.
         */
        private CodeableConcept route;

        /**
         * A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.
         */
        private CodeableConcept method;

        /**
         * The amount of the therapeutic or other substance given at one administration event.
         */
        private Quantity quantity;

        /**
         * Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.
         */
        private Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that my be administered to a subject over the period of time. E.g. 1000mg in 24 hours.
         */
        private Ratio maxDosePerPeriod;

        public Schedule getTiming() { 
          return this.timing;
        }

        public void setTiming(Schedule value) { 
          this.timing = value;
        }

        public CodeableConcept getSite() { 
          return this.site;
        }

        public void setSite(CodeableConcept value) { 
          this.site = value;
        }

        public CodeableConcept getRoute() { 
          return this.route;
        }

        public void setRoute(CodeableConcept value) { 
          this.route = value;
        }

        public CodeableConcept getMethod() { 
          return this.method;
        }

        public void setMethod(CodeableConcept value) { 
          this.method = value;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

        public Ratio getRate() { 
          return this.rate;
        }

        public void setRate(Ratio value) { 
          this.rate = value;
        }

        public Ratio getMaxDosePerPeriod() { 
          return this.maxDosePerPeriod;
        }

        public void setMaxDosePerPeriod(Ratio value) { 
          this.maxDosePerPeriod = value;
        }

  }

    public class MedicationDispenseSubstitutionComponent extends Element {
        /**
         * A code signifying whether a different drug was dispensed from what was prescribed.
         */
        private CodeableConcept type;

        /**
         * Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.
         */
        private List<CodeableConcept> reason = new ArrayList<CodeableConcept>();

        /**
         * The person or organization that has primary responsibility for the substitution
         */
        private List<ResourceReference> responsibleParty = new ArrayList<ResourceReference>();

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public List<CodeableConcept> getReason() { 
          return this.reason;
        }

        public List<ResourceReference> getResponsibleParty() { 
          return this.responsibleParty;
        }

  }

    /**
     * Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.
     */
    private Identifier identifier;

    /**
     * A code specifying the state of the set of dispense events.
     */
    private CodeableConcept status;

    /**
     * A link to a resource representing the person to whom the medication will be given.
     */
    private ResourceReference patient;

    /**
     * The individual reponsible for dispensing the medication
     */
    private ResourceReference dispenser;

    /**
     * A link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    private ResourceReference visit;

    /**
     * Indicates the medication order that is being dispensed against.
     */
    private List<ResourceReference> authorizingPrescription = new ArrayList<ResourceReference>();

    /**
     * Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.
     */
    private List<MedicationDispenseDispenseComponent> dispense = new ArrayList<MedicationDispenseDispenseComponent>();

    /**
     * Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.
     */
    private MedicationDispenseSubstitutionComponent substitution;

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public void setIdentifier(Identifier value) { 
      this.identifier = value;
    }

    public CodeableConcept getStatus() { 
      return this.status;
    }

    public void setStatus(CodeableConcept value) { 
      this.status = value;
    }

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public ResourceReference getDispenser() { 
      return this.dispenser;
    }

    public void setDispenser(ResourceReference value) { 
      this.dispenser = value;
    }

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
    }

    public List<ResourceReference> getAuthorizingPrescription() { 
      return this.authorizingPrescription;
    }

    public List<MedicationDispenseDispenseComponent> getDispense() { 
      return this.dispense;
    }

    public MedicationDispenseSubstitutionComponent getSubstitution() { 
      return this.substitution;
    }

    public void setSubstitution(MedicationDispenseSubstitutionComponent value) { 
      this.substitution = value;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationDispense;
   }


}

