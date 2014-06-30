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
 * An order for both supply of the medication and the instructions for administration of the medicine to a patient.
 */
public class MedicationPrescription extends Resource {

    public enum MedicationPrescriptionStatus {
        active, // The prescription is 'actionable', but not all actions that are implied by it have occurred yet.
        onHold, // Actions implied by the prescription have been temporarily halted, but are expected to continue later.  May also be called "suspended".
        completed, // All actions that are implied by the prescription have occurred (this will rarely be made explicit).
        enteredInError, // The prescription was entered in error and therefore nullified.
        stopped, // Actions implied by the prescription have been permanently halted, before all of them occurred.
        superceded, // The prescription was replaced by a newer one, which encompasses all the information in the previous one.
        Null; // added to help the parsers
        public static MedicationPrescriptionStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return active;
        if ("on hold".equals(codeString))
          return onHold;
        if ("completed".equals(codeString))
          return completed;
        if ("entered in error".equals(codeString))
          return enteredInError;
        if ("stopped".equals(codeString))
          return stopped;
        if ("superceded".equals(codeString))
          return superceded;
        throw new Exception("Unknown MedicationPrescriptionStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case onHold: return "on hold";
            case completed: return "completed";
            case enteredInError: return "entered in error";
            case stopped: return "stopped";
            case superceded: return "superceded";
            default: return "?";
          }
        }
    }

  public static class MedicationPrescriptionStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return MedicationPrescriptionStatus.active;
        if ("on hold".equals(codeString))
          return MedicationPrescriptionStatus.onHold;
        if ("completed".equals(codeString))
          return MedicationPrescriptionStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationPrescriptionStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationPrescriptionStatus.stopped;
        if ("superceded".equals(codeString))
          return MedicationPrescriptionStatus.superceded;
        throw new Exception("Unknown MedicationPrescriptionStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationPrescriptionStatus.active)
        return "active";
      if (code == MedicationPrescriptionStatus.onHold)
        return "on hold";
      if (code == MedicationPrescriptionStatus.completed)
        return "completed";
      if (code == MedicationPrescriptionStatus.enteredInError)
        return "entered in error";
      if (code == MedicationPrescriptionStatus.stopped)
        return "stopped";
      if (code == MedicationPrescriptionStatus.superceded)
        return "superceded";
      return "?";
      }
    }

    public static class MedicationPrescriptionDosageInstructionComponent extends BackboneElement {
        /**
         * Free text dosage instructions for cases where the instructions are too complex to code.
         */
        protected String_ text;

        /**
         * Additional instructions such as "Swallow with plenty of water" which may or may not be coded.
         */
        protected CodeableConcept additionalInstructions;

        /**
         * The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
         */
        protected Type timing;

        /**
         * If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.
         */
        protected Type asNeeded;

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
         * The amount of therapeutic or other substance given at one administration event.
         */
        protected Quantity doseQuantity;

        /**
         * Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time. E.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

        private static final long serialVersionUID = 1413384161L;

      public MedicationPrescriptionDosageInstructionComponent() {
        super();
      }

        /**
         * @return {@link #text} (Free text dosage instructions for cases where the instructions are too complex to code.)
         */
        public String_ getText() { 
          return this.text;
        }

        /**
         * @param value {@link #text} (Free text dosage instructions for cases where the instructions are too complex to code.)
         */
        public MedicationPrescriptionDosageInstructionComponent setText(String_ value) { 
          this.text = value;
          return this;
        }

        /**
         * @return Free text dosage instructions for cases where the instructions are too complex to code.
         */
        public String getTextSimple() { 
          return this.text == null ? null : this.text.getValue();
        }

        /**
         * @param value Free text dosage instructions for cases where the instructions are too complex to code.
         */
        public MedicationPrescriptionDosageInstructionComponent setTextSimple(String value) { 
          if (value == null)
            this.text = null;
          else {
            if (this.text == null)
              this.text = new String_();
            this.text.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #additionalInstructions} (Additional instructions such as "Swallow with plenty of water" which may or may not be coded.)
         */
        public CodeableConcept getAdditionalInstructions() { 
          return this.additionalInstructions;
        }

        /**
         * @param value {@link #additionalInstructions} (Additional instructions such as "Swallow with plenty of water" which may or may not be coded.)
         */
        public MedicationPrescriptionDosageInstructionComponent setAdditionalInstructions(CodeableConcept value) { 
          this.additionalInstructions = value;
          return this;
        }

        /**
         * @return {@link #timing} (The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
         */
        public Type getTiming() { 
          return this.timing;
        }

        /**
         * @param value {@link #timing} (The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".)
         */
        public MedicationPrescriptionDosageInstructionComponent setTiming(Type value) { 
          this.timing = value;
          return this;
        }

        /**
         * @return {@link #asNeeded} (If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.)
         */
        public Type getAsNeeded() { 
          return this.asNeeded;
        }

        /**
         * @param value {@link #asNeeded} (If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.)
         */
        public MedicationPrescriptionDosageInstructionComponent setAsNeeded(Type value) { 
          this.asNeeded = value;
          return this;
        }

        /**
         * @return {@link #site} (A coded specification of the anatomic site where the medication first enters the body.)
         */
        public CodeableConcept getSite() { 
          return this.site;
        }

        /**
         * @param value {@link #site} (A coded specification of the anatomic site where the medication first enters the body.)
         */
        public MedicationPrescriptionDosageInstructionComponent setSite(CodeableConcept value) { 
          this.site = value;
          return this;
        }

        /**
         * @return {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto a patient.)
         */
        public CodeableConcept getRoute() { 
          return this.route;
        }

        /**
         * @param value {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto a patient.)
         */
        public MedicationPrescriptionDosageInstructionComponent setRoute(CodeableConcept value) { 
          this.route = value;
          return this;
        }

        /**
         * @return {@link #method} (A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.)
         */
        public CodeableConcept getMethod() { 
          return this.method;
        }

        /**
         * @param value {@link #method} (A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.)
         */
        public MedicationPrescriptionDosageInstructionComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        /**
         * @return {@link #doseQuantity} (The amount of therapeutic or other substance given at one administration event.)
         */
        public Quantity getDoseQuantity() { 
          return this.doseQuantity;
        }

        /**
         * @param value {@link #doseQuantity} (The amount of therapeutic or other substance given at one administration event.)
         */
        public MedicationPrescriptionDosageInstructionComponent setDoseQuantity(Quantity value) { 
          this.doseQuantity = value;
          return this;
        }

        /**
         * @return {@link #rate} (Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.)
         */
        public Ratio getRate() { 
          return this.rate;
        }

        /**
         * @param value {@link #rate} (Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.)
         */
        public MedicationPrescriptionDosageInstructionComponent setRate(Ratio value) { 
          this.rate = value;
          return this;
        }

        /**
         * @return {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time. E.g. 1000mg in 24 hours.)
         */
        public Ratio getMaxDosePerPeriod() { 
          return this.maxDosePerPeriod;
        }

        /**
         * @param value {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time. E.g. 1000mg in 24 hours.)
         */
        public MedicationPrescriptionDosageInstructionComponent setMaxDosePerPeriod(Ratio value) { 
          this.maxDosePerPeriod = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("text", "string", "Free text dosage instructions for cases where the instructions are too complex to code.", 0, java.lang.Integer.MAX_VALUE, text));
          childrenList.add(new Property("additionalInstructions", "CodeableConcept", "Additional instructions such as 'Swallow with plenty of water' which may or may not be coded.", 0, java.lang.Integer.MAX_VALUE, additionalInstructions));
          childrenList.add(new Property("timing[x]", "dateTime|Period|Schedule", "The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  'Every  8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:';  '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
          childrenList.add(new Property("asNeeded[x]", "boolean|CodeableConcept", "If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.", 0, java.lang.Integer.MAX_VALUE, asNeeded));
          childrenList.add(new Property("site", "CodeableConcept", "A coded specification of the anatomic site where the medication first enters the body.", 0, java.lang.Integer.MAX_VALUE, site));
          childrenList.add(new Property("route", "CodeableConcept", "A code specifying the route or physiological path of administration of a therapeutic agent into or onto a patient.", 0, java.lang.Integer.MAX_VALUE, route));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.\n\nTerminologies used often pre-coordinate this term with the route and or form of administration.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("doseQuantity", "Quantity", "The amount of therapeutic or other substance given at one administration event.", 0, java.lang.Integer.MAX_VALUE, doseQuantity));
          childrenList.add(new Property("rate", "Ratio", "Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.", 0, java.lang.Integer.MAX_VALUE, rate));
          childrenList.add(new Property("maxDosePerPeriod", "Ratio", "The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time. E.g. 1000mg in 24 hours.", 0, java.lang.Integer.MAX_VALUE, maxDosePerPeriod));
        }

      public MedicationPrescriptionDosageInstructionComponent copy() {
        MedicationPrescriptionDosageInstructionComponent dst = new MedicationPrescriptionDosageInstructionComponent();
        dst.text = text == null ? null : text.copy();
        dst.additionalInstructions = additionalInstructions == null ? null : additionalInstructions.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.asNeeded = asNeeded == null ? null : asNeeded.copy();
        dst.site = site == null ? null : site.copy();
        dst.route = route == null ? null : route.copy();
        dst.method = method == null ? null : method.copy();
        dst.doseQuantity = doseQuantity == null ? null : doseQuantity.copy();
        dst.rate = rate == null ? null : rate.copy();
        dst.maxDosePerPeriod = maxDosePerPeriod == null ? null : maxDosePerPeriod.copy();
        return dst;
      }

  }

    public static class MedicationPrescriptionDispenseComponent extends BackboneElement {
        /**
         * Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
         */
        protected ResourceReference medication;

        /**
         * The actual object that is the target of the reference (Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        protected Medication medicationTarget;

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
In some situations, this attribute may be used instead of quantity to identify the amount supplied by how long it is expected to last, rather than the physical quantity issued, e.g. 90 days supply of medication (based on an ordered dosage) When possible, it is always better to specify quantity, as this tends to be more precise. expectedSupplyDuration will always be an estimate that can be influenced by external factors.
         */
        protected Duration expectedSupplyDuration;

        private static final long serialVersionUID = 763971836L;

      public MedicationPrescriptionDispenseComponent() {
        super();
      }

        /**
         * @return {@link #medication} (Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public ResourceReference getMedication() { 
          return this.medication;
        }

        /**
         * @param value {@link #medication} (Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public MedicationPrescriptionDispenseComponent setMedication(ResourceReference value) { 
          this.medication = value;
          return this;
        }

        /**
         * @return {@link #medication} (The actual object that is the target of the reference. Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public Medication getMedicationTarget() { 
          return this.medicationTarget;
        }

        /**
         * @param value {@link #medication} (The actual object that is the target of the reference. Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        public MedicationPrescriptionDispenseComponent setMedicationTarget(Medication value) { 
          this.medicationTarget = value;
          return this;
        }

        /**
         * @return {@link #validityPeriod} (Design Comments: This indicates the validity period of a prescription (stale dating the Prescription) 
It reflects the prescriber perspective for the validity of the prescription. Dispenses must not be made against the prescription outside of this period. The lower-bound of the Dispensing Window signifies the earliest date that the prescription can be filled for the first time. If an upper-bound is not specified then the Prescription is open-ended or will default to a stale-date based on regulations. 
Rationale: Indicates when the Prescription becomes valid, and when it ceases to be a dispensable Prescription.)
         */
        public Period getValidityPeriod() { 
          return this.validityPeriod;
        }

        /**
         * @param value {@link #validityPeriod} (Design Comments: This indicates the validity period of a prescription (stale dating the Prescription) 
It reflects the prescriber perspective for the validity of the prescription. Dispenses must not be made against the prescription outside of this period. The lower-bound of the Dispensing Window signifies the earliest date that the prescription can be filled for the first time. If an upper-bound is not specified then the Prescription is open-ended or will default to a stale-date based on regulations. 
Rationale: Indicates when the Prescription becomes valid, and when it ceases to be a dispensable Prescription.)
         */
        public MedicationPrescriptionDispenseComponent setValidityPeriod(Period value) { 
          this.validityPeriod = value;
          return this;
        }

        /**
         * @return {@link #numberOfRepeatsAllowed} (An integer indicating the number of repeats of the Dispense. 
UsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.)
         */
        public Integer getNumberOfRepeatsAllowed() { 
          return this.numberOfRepeatsAllowed;
        }

        /**
         * @param value {@link #numberOfRepeatsAllowed} (An integer indicating the number of repeats of the Dispense. 
UsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.)
         */
        public MedicationPrescriptionDispenseComponent setNumberOfRepeatsAllowed(Integer value) { 
          this.numberOfRepeatsAllowed = value;
          return this;
        }

        /**
         * @return An integer indicating the number of repeats of the Dispense. 
UsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.
         */
        public int getNumberOfRepeatsAllowedSimple() { 
          return this.numberOfRepeatsAllowed == null ? null : this.numberOfRepeatsAllowed.getValue();
        }

        /**
         * @param value An integer indicating the number of repeats of the Dispense. 
UsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.
         */
        public MedicationPrescriptionDispenseComponent setNumberOfRepeatsAllowedSimple(int value) { 
          if (value == -1)
            this.numberOfRepeatsAllowed = null;
          else {
            if (this.numberOfRepeatsAllowed == null)
              this.numberOfRepeatsAllowed = new Integer();
            this.numberOfRepeatsAllowed.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #quantity} (The amount that is to be dispensed.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount that is to be dispensed.)
         */
        public MedicationPrescriptionDispenseComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #expectedSupplyDuration} (Identifies the period time over which the supplied product is expected to be used, or the length of time the dispense is expected to last. 
In some situations, this attribute may be used instead of quantity to identify the amount supplied by how long it is expected to last, rather than the physical quantity issued, e.g. 90 days supply of medication (based on an ordered dosage) When possible, it is always better to specify quantity, as this tends to be more precise. expectedSupplyDuration will always be an estimate that can be influenced by external factors.)
         */
        public Duration getExpectedSupplyDuration() { 
          return this.expectedSupplyDuration;
        }

        /**
         * @param value {@link #expectedSupplyDuration} (Identifies the period time over which the supplied product is expected to be used, or the length of time the dispense is expected to last. 
In some situations, this attribute may be used instead of quantity to identify the amount supplied by how long it is expected to last, rather than the physical quantity issued, e.g. 90 days supply of medication (based on an ordered dosage) When possible, it is always better to specify quantity, as this tends to be more precise. expectedSupplyDuration will always be an estimate that can be influenced by external factors.)
         */
        public MedicationPrescriptionDispenseComponent setExpectedSupplyDuration(Duration value) { 
          this.expectedSupplyDuration = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("medication", "Resource(Medication)", "Identifies the medication that is to be dispensed.  This may be a more specifically defined than the medicationPrescription.medication . This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, medication));
          childrenList.add(new Property("validityPeriod", "Period", "Design Comments: This indicates the validity period of a prescription (stale dating the Prescription) \nIt reflects the prescriber perspective for the validity of the prescription. Dispenses must not be made against the prescription outside of this period. The lower-bound of the Dispensing Window signifies the earliest date that the prescription can be filled for the first time. If an upper-bound is not specified then the Prescription is open-ended or will default to a stale-date based on regulations. \nRationale: Indicates when the Prescription becomes valid, and when it ceases to be a dispensable Prescription.", 0, java.lang.Integer.MAX_VALUE, validityPeriod));
          childrenList.add(new Property("numberOfRepeatsAllowed", "integer", "An integer indicating the number of repeats of the Dispense. \nUsageNotes: For example, the number of times the prescribed quantity is to be supplied including the initial standard fill.", 0, java.lang.Integer.MAX_VALUE, numberOfRepeatsAllowed));
          childrenList.add(new Property("quantity", "Quantity", "The amount that is to be dispensed.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("expectedSupplyDuration", "Duration", "Identifies the period time over which the supplied product is expected to be used, or the length of time the dispense is expected to last. \nIn some situations, this attribute may be used instead of quantity to identify the amount supplied by how long it is expected to last, rather than the physical quantity issued, e.g. 90 days supply of medication (based on an ordered dosage) When possible, it is always better to specify quantity, as this tends to be more precise. expectedSupplyDuration will always be an estimate that can be influenced by external factors.", 0, java.lang.Integer.MAX_VALUE, expectedSupplyDuration));
        }

      public MedicationPrescriptionDispenseComponent copy() {
        MedicationPrescriptionDispenseComponent dst = new MedicationPrescriptionDispenseComponent();
        dst.medication = medication == null ? null : medication.copy();
        dst.validityPeriod = validityPeriod == null ? null : validityPeriod.copy();
        dst.numberOfRepeatsAllowed = numberOfRepeatsAllowed == null ? null : numberOfRepeatsAllowed.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.expectedSupplyDuration = expectedSupplyDuration == null ? null : expectedSupplyDuration.copy();
        return dst;
      }

  }

    public static class MedicationPrescriptionSubstitutionComponent extends BackboneElement {
        /**
         * A code signifying whether a different drug should be dispensed from what was prescribed.
         */
        protected CodeableConcept type;

        /**
         * Indicates the reason for the substitution, or why substitution must or must not be performed.
         */
        protected CodeableConcept reason;

        private static final long serialVersionUID = 1693602518L;

      public MedicationPrescriptionSubstitutionComponent() {
        super();
      }

      public MedicationPrescriptionSubstitutionComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (A code signifying whether a different drug should be dispensed from what was prescribed.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A code signifying whether a different drug should be dispensed from what was prescribed.)
         */
        public MedicationPrescriptionSubstitutionComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #reason} (Indicates the reason for the substitution, or why substitution must or must not be performed.)
         */
        public CodeableConcept getReason() { 
          return this.reason;
        }

        /**
         * @param value {@link #reason} (Indicates the reason for the substitution, or why substitution must or must not be performed.)
         */
        public MedicationPrescriptionSubstitutionComponent setReason(CodeableConcept value) { 
          this.reason = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "A code signifying whether a different drug should be dispensed from what was prescribed.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("reason", "CodeableConcept", "Indicates the reason for the substitution, or why substitution must or must not be performed.", 0, java.lang.Integer.MAX_VALUE, reason));
        }

      public MedicationPrescriptionSubstitutionComponent copy() {
        MedicationPrescriptionSubstitutionComponent dst = new MedicationPrescriptionSubstitutionComponent();
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
     * The actual object that is the target of the reference (A link to a resource representing the person to whom the medication will be given.)
     */
    protected Patient patientTarget;

    /**
     * The healthcare professional responsible for authorizing the prescription.
     */
    protected ResourceReference prescriber;

    /**
     * The actual object that is the target of the reference (The healthcare professional responsible for authorizing the prescription.)
     */
    protected Practitioner prescriberTarget;

    /**
     * A link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (A link to a resource that identifies the particular occurrence of contact between patient and health care provider.)
     */
    protected Encounter encounterTarget;

    /**
     * Can be the reason or the indication for writing the prescription.
     */
    protected Type reason;

    /**
     * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected ResourceReference medication;

    /**
     * The actual object that is the target of the reference (Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    protected Medication medicationTarget;

    /**
     * Indicates how the medication is to be used by the patient.
     */
    protected List<MedicationPrescriptionDosageInstructionComponent> dosageInstruction = new ArrayList<MedicationPrescriptionDosageInstructionComponent>();

    /**
     * Deals with details of the dispense part of the order.
     */
    protected MedicationPrescriptionDispenseComponent dispense;

    /**
     * Indicates whether or not substitution can or should be part of the dispense. In some cases substitution must happen, in other cases substitution must not happen, and in others it does not matter. This block explains the prescriber's intent. If nothing is specified substitution may be done.
     */
    protected MedicationPrescriptionSubstitutionComponent substitution;

    private static final long serialVersionUID = -1812163359L;

    public MedicationPrescription() {
      super();
    }

    /**
     * @return {@link #identifier} (External identifier - one that would be used by another non-FHIR system - for example a re-imbursement system might issue its own id for each prescription that is created.  This is particularly important where FHIR only provides part of an erntire workflow process where records have to be tracked through an entire system.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (External identifier - one that would be used by another non-FHIR system - for example a re-imbursement system might issue its own id for each prescription that is created.  This is particularly important where FHIR only provides part of an erntire workflow process where records have to be tracked through an entire system.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #dateWritten} (The date (and perhaps time) when the prescription was written.)
     */
    public DateTime getDateWritten() { 
      return this.dateWritten;
    }

    /**
     * @param value {@link #dateWritten} (The date (and perhaps time) when the prescription was written.)
     */
    public MedicationPrescription setDateWritten(DateTime value) { 
      this.dateWritten = value;
      return this;
    }

    /**
     * @return The date (and perhaps time) when the prescription was written.
     */
    public DateAndTime getDateWrittenSimple() { 
      return this.dateWritten == null ? null : this.dateWritten.getValue();
    }

    /**
     * @param value The date (and perhaps time) when the prescription was written.
     */
    public MedicationPrescription setDateWrittenSimple(DateAndTime value) { 
      if (value == null)
        this.dateWritten = null;
      else {
        if (this.dateWritten == null)
          this.dateWritten = new DateTime();
        this.dateWritten.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #status} (A code specifying the state of the order.  Generally this will be active or completed state.)
     */
    public Enumeration<MedicationPrescriptionStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (A code specifying the state of the order.  Generally this will be active or completed state.)
     */
    public MedicationPrescription setStatus(Enumeration<MedicationPrescriptionStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return A code specifying the state of the order.  Generally this will be active or completed state.
     */
    public MedicationPrescriptionStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value A code specifying the state of the order.  Generally this will be active or completed state.
     */
    public MedicationPrescription setStatusSimple(MedicationPrescriptionStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<MedicationPrescriptionStatus>();
        this.status.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #patient} (A link to a resource representing the person to whom the medication will be given.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (A link to a resource representing the person to whom the medication will be given.)
     */
    public MedicationPrescription setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. A link to a resource representing the person to whom the medication will be given.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. A link to a resource representing the person to whom the medication will be given.)
     */
    public MedicationPrescription setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #prescriber} (The healthcare professional responsible for authorizing the prescription.)
     */
    public ResourceReference getPrescriber() { 
      return this.prescriber;
    }

    /**
     * @param value {@link #prescriber} (The healthcare professional responsible for authorizing the prescription.)
     */
    public MedicationPrescription setPrescriber(ResourceReference value) { 
      this.prescriber = value;
      return this;
    }

    /**
     * @return {@link #prescriber} (The actual object that is the target of the reference. The healthcare professional responsible for authorizing the prescription.)
     */
    public Practitioner getPrescriberTarget() { 
      return this.prescriberTarget;
    }

    /**
     * @param value {@link #prescriber} (The actual object that is the target of the reference. The healthcare professional responsible for authorizing the prescription.)
     */
    public MedicationPrescription setPrescriberTarget(Practitioner value) { 
      this.prescriberTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (A link to a resource that identifies the particular occurrence of contact between patient and health care provider.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (A link to a resource that identifies the particular occurrence of contact between patient and health care provider.)
     */
    public MedicationPrescription setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. A link to a resource that identifies the particular occurrence of contact between patient and health care provider.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. A link to a resource that identifies the particular occurrence of contact between patient and health care provider.)
     */
    public MedicationPrescription setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #reason} (Can be the reason or the indication for writing the prescription.)
     */
    public Type getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Can be the reason or the indication for writing the prescription.)
     */
    public MedicationPrescription setReason(Type value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #medication} (Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public ResourceReference getMedication() { 
      return this.medication;
    }

    /**
     * @param value {@link #medication} (Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationPrescription setMedication(ResourceReference value) { 
      this.medication = value;
      return this;
    }

    /**
     * @return {@link #medication} (The actual object that is the target of the reference. Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public Medication getMedicationTarget() { 
      return this.medicationTarget;
    }

    /**
     * @param value {@link #medication} (The actual object that is the target of the reference. Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationPrescription setMedicationTarget(Medication value) { 
      this.medicationTarget = value;
      return this;
    }

    /**
     * @return {@link #dosageInstruction} (Indicates how the medication is to be used by the patient.)
     */
    public List<MedicationPrescriptionDosageInstructionComponent> getDosageInstruction() { 
      return this.dosageInstruction;
    }

    // syntactic sugar
    /**
     * @return {@link #dosageInstruction} (Indicates how the medication is to be used by the patient.)
     */
    public MedicationPrescriptionDosageInstructionComponent addDosageInstruction() { 
      MedicationPrescriptionDosageInstructionComponent t = new MedicationPrescriptionDosageInstructionComponent();
      this.dosageInstruction.add(t);
      return t;
    }

    /**
     * @return {@link #dispense} (Deals with details of the dispense part of the order.)
     */
    public MedicationPrescriptionDispenseComponent getDispense() { 
      return this.dispense;
    }

    /**
     * @param value {@link #dispense} (Deals with details of the dispense part of the order.)
     */
    public MedicationPrescription setDispense(MedicationPrescriptionDispenseComponent value) { 
      this.dispense = value;
      return this;
    }

    /**
     * @return {@link #substitution} (Indicates whether or not substitution can or should be part of the dispense. In some cases substitution must happen, in other cases substitution must not happen, and in others it does not matter. This block explains the prescriber's intent. If nothing is specified substitution may be done.)
     */
    public MedicationPrescriptionSubstitutionComponent getSubstitution() { 
      return this.substitution;
    }

    /**
     * @param value {@link #substitution} (Indicates whether or not substitution can or should be part of the dispense. In some cases substitution must happen, in other cases substitution must not happen, and in others it does not matter. This block explains the prescriber's intent. If nothing is specified substitution may be done.)
     */
    public MedicationPrescription setSubstitution(MedicationPrescriptionSubstitutionComponent value) { 
      this.substitution = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External identifier - one that would be used by another non-FHIR system - for example a re-imbursement system might issue its own id for each prescription that is created.  This is particularly important where FHIR only provides part of an erntire workflow process where records have to be tracked through an entire system.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("dateWritten", "dateTime", "The date (and perhaps time) when the prescription was written.", 0, java.lang.Integer.MAX_VALUE, dateWritten));
        childrenList.add(new Property("status", "code", "A code specifying the state of the order.  Generally this will be active or completed state.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Resource(Patient)", "A link to a resource representing the person to whom the medication will be given.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("prescriber", "Resource(Practitioner)", "The healthcare professional responsible for authorizing the prescription.", 0, java.lang.Integer.MAX_VALUE, prescriber));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "A link to a resource that identifies the particular occurrence of contact between patient and health care provider.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("reason[x]", "CodeableConcept|Resource(Condition)", "Can be the reason or the indication for writing the prescription.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("medication", "Resource(Medication)", "Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, medication));
        childrenList.add(new Property("dosageInstruction", "", "Indicates how the medication is to be used by the patient.", 0, java.lang.Integer.MAX_VALUE, dosageInstruction));
        childrenList.add(new Property("dispense", "", "Deals with details of the dispense part of the order.", 0, java.lang.Integer.MAX_VALUE, dispense));
        childrenList.add(new Property("substitution", "", "Indicates whether or not substitution can or should be part of the dispense. In some cases substitution must happen, in other cases substitution must not happen, and in others it does not matter. This block explains the prescriber's intent. If nothing is specified substitution may be done.", 0, java.lang.Integer.MAX_VALUE, substitution));
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
        dst.reason = reason == null ? null : reason.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.dosageInstruction = new ArrayList<MedicationPrescriptionDosageInstructionComponent>();
        for (MedicationPrescriptionDosageInstructionComponent i : dosageInstruction)
          dst.dosageInstruction.add(i.copy());
        dst.dispense = dispense == null ? null : dispense.copy();
        dst.substitution = substitution == null ? null : substitution.copy();
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

