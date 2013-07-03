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

// Generated on Tue, Jul 2, 2013 18:37+1000 for FHIR v0.09

import java.util.*;

/**
 * An order for both supply of the medication and the instructions for administration of the medicine to a patient.
 */
public class MedicationPrescription extends Resource {

    public class MedicationPrescriptionDosageInstructionsComponent extends Element {
        /**
         * Free text dosage instructions for cases where the instructions are too complex to code.
         */
        protected String_ dosageInstructionsText;

        /**
         * Additional instructions such as "Swallow with plenty of water" which may or may not be coded.
         */
        protected Type additionalInstructions;

        /**
         * The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013"
         */
        protected Schedule timing;

        /**
         * A coded specification of the anatomic site where the medication first enters the body
         */
        protected CodeableConcept site;

        /**
         * A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.
         */
        protected CodeableConcept route;

        /**
         * A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.
         */
        protected CodeableConcept method;

        /**
         * The amount of the therapeutic or other substance given at one administration event.
         */
        protected Quantity doseQuantity;

        /**
         * Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that my be administered to a subject over the period of time. E.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

        public String_ getDosageInstructionsText() { 
          return this.dosageInstructionsText;
        }

        public void setDosageInstructionsText(String_ value) { 
          this.dosageInstructionsText = value;
        }

        public String getDosageInstructionsTextSimple() { 
          return this.dosageInstructionsText == null ? null : this.dosageInstructionsText.getValue();
        }

        public void setDosageInstructionsTextSimple(String value) { 
          if (value == null)
            this.dosageInstructionsText = null;
          else {
            if (this.dosageInstructionsText == null)
              this.dosageInstructionsText = new String_();
            this.dosageInstructionsText.setValue(value);
          }
        }

        public Type getAdditionalInstructions() { 
          return this.additionalInstructions;
        }

        public void setAdditionalInstructions(Type value) { 
          this.additionalInstructions = value;
        }

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

        public Quantity getDoseQuantity() { 
          return this.doseQuantity;
        }

        public void setDoseQuantity(Quantity value) { 
          this.doseQuantity = value;
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

      public MedicationPrescriptionDosageInstructionsComponent copy(MedicationPrescription e) {
        MedicationPrescriptionDosageInstructionsComponent dst = e.new MedicationPrescriptionDosageInstructionsComponent();
        dst.dosageInstructionsText = dosageInstructionsText == null ? null : dosageInstructionsText.copy();
        dst.additionalInstructions = additionalInstructions == null ? null : additionalInstructions.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.site = site == null ? null : site.copy();
        dst.route = route == null ? null : route.copy();
        dst.method = method == null ? null : method.copy();
        dst.doseQuantity = doseQuantity == null ? null : doseQuantity.copy();
        dst.rate = rate == null ? null : rate.copy();
        dst.maxDosePerPeriod = maxDosePerPeriod == null ? null : maxDosePerPeriod.copy();
        return dst;
      }

  }

    public class MedicationPrescriptionDispenseComponent extends Element {
        /**
         * Design Comments: This indicates the validity period of a prescription (stale dating the Prescription) 
It reflects the prescriber perspective for the validity of the prescription. Dispenses must not be made against the prescription outside of this period. The lower-bound of the Dispensing Window signifies the earliest date that the prescription can be filled for the first time. If an upper-bound is not specified then the Prescription is open-ended or will default to a stale-date based on regulations. 
Rationale: Indicates when the Prescription becomes valid, and when it ceases to be a dispensable Prescription.
         */
        protected Period validityPeriod;

        /**
         * An integer indicating the number of repeats of the Dispense. 
UsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.
         */
        protected Integer numberOfRepeatsAllowed;

        /**
         * The amount that is to be dispensed.
         */
        protected Quantity quantity;

        /**
         * Identifies the period time over which the supplied product is expected to be used, or the length of time the dispense is expected to last. 
In some situations, this attribute may be used instead of quantity to identify the amount supplied by how long it is expected to last, rather than the physical quantity issued. E.g. 90 days supply of medication (based on an ordered dosage) When possible, it is always better to specify quantity, as this tends to be more precise. expectedSupplyDuration will always be an estimate that can be influenced by external factors.
         */
        protected Duration expectedSupplyDuration;

        public Period getValidityPeriod() { 
          return this.validityPeriod;
        }

        public void setValidityPeriod(Period value) { 
          this.validityPeriod = value;
        }

        public Integer getNumberOfRepeatsAllowed() { 
          return this.numberOfRepeatsAllowed;
        }

        public void setNumberOfRepeatsAllowed(Integer value) { 
          this.numberOfRepeatsAllowed = value;
        }

        public int getNumberOfRepeatsAllowedSimple() { 
          return this.numberOfRepeatsAllowed == null ? null : this.numberOfRepeatsAllowed.getValue();
        }

        public void setNumberOfRepeatsAllowedSimple(int value) { 
          if (value == -1)
            this.numberOfRepeatsAllowed = null;
          else {
            if (this.numberOfRepeatsAllowed == null)
              this.numberOfRepeatsAllowed = new Integer();
            this.numberOfRepeatsAllowed.setValue(value);
          }
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public void setQuantity(Quantity value) { 
          this.quantity = value;
        }

        public Duration getExpectedSupplyDuration() { 
          return this.expectedSupplyDuration;
        }

        public void setExpectedSupplyDuration(Duration value) { 
          this.expectedSupplyDuration = value;
        }

      public MedicationPrescriptionDispenseComponent copy(MedicationPrescription e) {
        MedicationPrescriptionDispenseComponent dst = e.new MedicationPrescriptionDispenseComponent();
        dst.validityPeriod = validityPeriod == null ? null : validityPeriod.copy();
        dst.numberOfRepeatsAllowed = numberOfRepeatsAllowed == null ? null : numberOfRepeatsAllowed.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.expectedSupplyDuration = expectedSupplyDuration == null ? null : expectedSupplyDuration.copy();
        return dst;
      }

  }

    public class MedicationPrescriptionSubstitutionComponent extends Element {
        /**
         * A code signifying whether a different drug should be dispensed from what was prescribed.
         */
        protected CodeableConcept type;

        /**
         * Indicates the reason for the substitution why substitution must or must not be performed.
         */
        protected CodeableConcept reason;

        public CodeableConcept getType() { 
          return this.type;
        }

        public void setType(CodeableConcept value) { 
          this.type = value;
        }

        public CodeableConcept getReason() { 
          return this.reason;
        }

        public void setReason(CodeableConcept value) { 
          this.reason = value;
        }

      public MedicationPrescriptionSubstitutionComponent copy(MedicationPrescription e) {
        MedicationPrescriptionSubstitutionComponent dst = e.new MedicationPrescriptionSubstitutionComponent();
        dst.type = type == null ? null : type.copy();
        dst.reason = reason == null ? null : reason.copy();
        return dst;
      }

  }

    /**
     * External identifier - FHIR will generate its own internal IDs which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example a re-imbursement system might issue its own id for each prescription that is created.  This is particularly important where FHIR only provides part of an erntire workflow process where records have to be tracked through an entire system.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The date (and perhaps time) when the prescription was written
     */
    protected DateTime dateWritten;

    /**
     * A code specifying the state of the order.  Generally this will be active or completed state
     */
    protected CodeableConcept status;

    /**
     * A link to a resource representing the person to whom the medication will be given.
     */
    protected ResourceReference patient;

    /**
     * The healthcare professional responsible for authorising the prescription
     */
    protected ResourceReference prescriber;

    /**
     * A link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    protected ResourceReference visit;

    /**
     * Can be the reason or the indication for writing the prescription.
     */
    protected Type reasonForPrescribing;

    /**
     * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected ResourceReference medication;

    /**
     * Indicates how the medication is to be used by the patient
     */
    protected List<MedicationPrescriptionDosageInstructionsComponent> dosageInstructions = new ArrayList<MedicationPrescriptionDosageInstructionsComponent>();

    /**
     * Deals with details of the dispense part of the order
     */
    protected MedicationPrescriptionDispenseComponent dispense;

    /**
     * Indicates whether or not substitution can or should as part of the dispense.  In some cases substitution must  happen, in other cases substitution must not happen, and in others it does not matter.  This block explains the prescribers intent.  If nothing is specified substitution may be done.
     */
    protected MedicationPrescriptionSubstitutionComponent substitution;

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    public DateTime getDateWritten() { 
      return this.dateWritten;
    }

    public void setDateWritten(DateTime value) { 
      this.dateWritten = value;
    }

    public String getDateWrittenSimple() { 
      return this.dateWritten == null ? null : this.dateWritten.getValue();
    }

    public void setDateWrittenSimple(String value) { 
      if (value == null)
        this.dateWritten = null;
      else {
        if (this.dateWritten == null)
          this.dateWritten = new DateTime();
        this.dateWritten.setValue(value);
      }
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

    public ResourceReference getPrescriber() { 
      return this.prescriber;
    }

    public void setPrescriber(ResourceReference value) { 
      this.prescriber = value;
    }

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
    }

    public Type getReasonForPrescribing() { 
      return this.reasonForPrescribing;
    }

    public void setReasonForPrescribing(Type value) { 
      this.reasonForPrescribing = value;
    }

    public ResourceReference getMedication() { 
      return this.medication;
    }

    public void setMedication(ResourceReference value) { 
      this.medication = value;
    }

    public List<MedicationPrescriptionDosageInstructionsComponent> getDosageInstructions() { 
      return this.dosageInstructions;
    }

    public MedicationPrescriptionDispenseComponent getDispense() { 
      return this.dispense;
    }

    public void setDispense(MedicationPrescriptionDispenseComponent value) { 
      this.dispense = value;
    }

    public MedicationPrescriptionSubstitutionComponent getSubstitution() { 
      return this.substitution;
    }

    public void setSubstitution(MedicationPrescriptionSubstitutionComponent value) { 
      this.substitution = value;
    }

      public MedicationPrescription copy() {
        MedicationPrescription dst = new MedicationPrescription();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.dateWritten = dateWritten == null ? null : dateWritten.copy();
        dst.status = status == null ? null : status.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.prescriber = prescriber == null ? null : prescriber.copy();
        dst.visit = visit == null ? null : visit.copy();
        dst.reasonForPrescribing = reasonForPrescribing == null ? null : reasonForPrescribing.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.dosageInstructions = new ArrayList<MedicationPrescriptionDosageInstructionsComponent>();
        for (MedicationPrescriptionDosageInstructionsComponent i : dosageInstructions)
          dst.dosageInstructions.add(i.copy(dst));
        dst.dispense = dispense == null ? null : dispense.copy(dst);
        dst.substitution = substitution == null ? null : substitution.copy(dst);
        return dst;
      }

      protected MedicationPrescription typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationPrescription;
   }


}

