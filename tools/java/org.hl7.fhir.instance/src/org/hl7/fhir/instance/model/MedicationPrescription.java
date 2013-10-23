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

// Generated on Wed, Oct 23, 2013 23:11+1100 for FHIR v0.12

import java.util.*;

/**
 * An order for both supply of the medication and the instructions for administration of the medicine to a patient.
 */
public class MedicationPrescription extends Resource {

    public enum MedicationPrescriptionStatus {
        active, // The prescription is 'actionable', but not all actions that are implied by it have occurred yet.
        suspended, // Actions implied by the prescription have been temporarily halted, but are expected to continue later.  May also be called "held".
        completed, // All actions that are implied by the prescription have occurred (this will rarely be made explicit).
        enteredInError, // The prescription was entered in error and therefore nullified.
        stopped, // Actions implied by the prescription have been permanently halted, before all of them occurred.
        obsolete, // The prescription was replaced by a newer one, which encompasses all the information in the previous one.
        Null; // added to help the parsers
        public static MedicationPrescriptionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return active;
        if ("suspended".equals(codeString))
          return suspended;
        if ("completed".equals(codeString))
          return completed;
        if ("entered in error".equals(codeString))
          return enteredInError;
        if ("stopped".equals(codeString))
          return stopped;
        if ("obsolete".equals(codeString))
          return obsolete;
        throw new Exception("Unknown MedicationPrescriptionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case suspended: return "suspended";
            case completed: return "completed";
            case enteredInError: return "entered in error";
            case stopped: return "stopped";
            case obsolete: return "obsolete";
            default: return "?";
          }
        }
    }

  public class MedicationPrescriptionStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return MedicationPrescriptionStatus.active;
        if ("suspended".equals(codeString))
          return MedicationPrescriptionStatus.suspended;
        if ("completed".equals(codeString))
          return MedicationPrescriptionStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationPrescriptionStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationPrescriptionStatus.stopped;
        if ("obsolete".equals(codeString))
          return MedicationPrescriptionStatus.obsolete;
        throw new Exception("Unknown MedicationPrescriptionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationPrescriptionStatus.active)
        return "active";
      if (code == MedicationPrescriptionStatus.suspended)
        return "suspended";
      if (code == MedicationPrescriptionStatus.completed)
        return "completed";
      if (code == MedicationPrescriptionStatus.enteredInError)
        return "entered in error";
      if (code == MedicationPrescriptionStatus.stopped)
        return "stopped";
      if (code == MedicationPrescriptionStatus.obsolete)
        return "obsolete";
      return "?";
      }
    }

    public class MedicationPrescriptionDosageInstructionComponent extends BackboneElement {
        /**
         * Free text dosage instructions for cases where the instructions are too complex to code.
         */
        protected String_ dosageInstructionsText;

        /**
         * Additional instructions such as "Swallow with plenty of water" which may or may not be coded.
         */
        protected CodeableConcept additionalInstructions;

        /**
         * The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
         */
        protected Type timing;

        /**
         * A coded specification of the anatomic site where the medication first enters the body.
         */
        protected CodeableConcept site;

        /**
         * A code specifying the route or physiological path of administration of a therapeutic agent into or onto a patient.
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

        public CodeableConcept getAdditionalInstructions() { 
          return this.additionalInstructions;
        }

        public void setAdditionalInstructions(CodeableConcept value) { 
          this.additionalInstructions = value;
        }

        public Type getTiming() { 
          return this.timing;
        }

        public void setTiming(Type value) { 
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

      public MedicationPrescriptionDosageInstructionComponent copy(MedicationPrescription e) {
        MedicationPrescriptionDosageInstructionComponent dst = e.new MedicationPrescriptionDosageInstructionComponent();
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

    public class MedicationPrescriptionDispenseComponent extends BackboneElement {
        /**
         * Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
         */
        protected ResourceReference medication;

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

        public ResourceReference getMedication() { 
          return this.medication;
        }

        public void setMedication(ResourceReference value) { 
          this.medication = value;
        }

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
        dst.medication = medication == null ? null : medication.copy();
        dst.validityPeriod = validityPeriod == null ? null : validityPeriod.copy();
        dst.numberOfRepeatsAllowed = numberOfRepeatsAllowed == null ? null : numberOfRepeatsAllowed.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.expectedSupplyDuration = expectedSupplyDuration == null ? null : expectedSupplyDuration.copy();
        return dst;
      }

  }

    public class MedicationPrescriptionSubstitutionComponent extends BackboneElement {
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
     * External identifier - one that would be used by another non-FHIR system - for example a re-imbursement system might issue its own id for each prescription that is created.  This is particularly important where FHIR only provides part of an erntire workflow process where records have to be tracked through an entire system.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * The date (and perhaps time) when the prescription was written.
     */
    protected DateTime dateWritten;

    /**
     * A code specifying the state of the order.  Generally this will be active or completed state.
     */
    protected Enumeration<MedicationPrescriptionStatus> status;

    /**
     * A link to a resource representing the person to whom the medication will be given.
     */
    protected ResourceReference patient;

    /**
     * The healthcare professional responsible for authorising the prescription.
     */
    protected ResourceReference prescriber;

    /**
     * A link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    protected ResourceReference encounter;

    /**
     * Can be the reason or the indication for writing the prescription.
     */
    protected CodeableConcept reasonForPrescribing;

    /**
     * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected ResourceReference medication;

    /**
     * Indicates how the medication is to be used by the patient.
     */
    protected List<MedicationPrescriptionDosageInstructionComponent> dosageInstruction = new ArrayList<MedicationPrescriptionDosageInstructionComponent>();

    /**
     * Deals with details of the dispense part of the order.
     */
    protected MedicationPrescriptionDispenseComponent dispense;

    /**
     * Indicates whether or not substitution can or should as part of the dispense.  In some cases substitution must  happen, in other cases substitution must not happen, and in others it does not matter.  This block explains the prescribers intent.  If nothing is specified substitution may be done.
     */
    protected MedicationPrescriptionSubstitutionComponent substitution;

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
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

    public Enumeration<MedicationPrescriptionStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<MedicationPrescriptionStatus> value) { 
      this.status = value;
    }

    public MedicationPrescriptionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(MedicationPrescriptionStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<MedicationPrescriptionStatus>();
        this.status.setValue(value);
      }
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

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public CodeableConcept getReasonForPrescribing() { 
      return this.reasonForPrescribing;
    }

    public void setReasonForPrescribing(CodeableConcept value) { 
      this.reasonForPrescribing = value;
    }

    public ResourceReference getMedication() { 
      return this.medication;
    }

    public void setMedication(ResourceReference value) { 
      this.medication = value;
    }

    public List<MedicationPrescriptionDosageInstructionComponent> getDosageInstruction() { 
      return this.dosageInstruction;
    }

    // syntactic sugar
    public MedicationPrescriptionDosageInstructionComponent addDosageInstruction() { 
      MedicationPrescriptionDosageInstructionComponent t = new MedicationPrescriptionDosageInstructionComponent();
      this.dosageInstruction.add(t);
      return t;
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
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.reasonForPrescribing = reasonForPrescribing == null ? null : reasonForPrescribing.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.dosageInstruction = new ArrayList<MedicationPrescriptionDosageInstructionComponent>();
        for (MedicationPrescriptionDosageInstructionComponent i : dosageInstruction)
          dst.dosageInstruction.add(i.copy(dst));
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

