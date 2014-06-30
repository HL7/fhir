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
 * Dispensing a medication to a named patient.  This includes a description of the supply provided and the instructions for administering the medication.
 */
public class MedicationDispense extends Resource {

    public enum MedicationDispenseStatus {
        inProgress, // The dispense has started but has not yet completed.
        onHold, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "suspended".
        completed, // All actions that are implied by the dispense have occurred.
        enteredInError, // The dispense was entered in error and therefore nullified.
        stopped, // Actions implied by the dispense have been permanently halted, before all of them occurred.
        Null; // added to help the parsers
        public static MedicationDispenseStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return inProgress;
        if ("on hold".equals(codeString))
          return onHold;
        if ("completed".equals(codeString))
          return completed;
        if ("entered in error".equals(codeString))
          return enteredInError;
        if ("stopped".equals(codeString))
          return stopped;
        throw new Exception("Unknown MedicationDispenseStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case inProgress: return "in progress";
            case onHold: return "on hold";
            case completed: return "completed";
            case enteredInError: return "entered in error";
            case stopped: return "stopped";
            default: return "?";
          }
        }
    }

  public static class MedicationDispenseStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return MedicationDispenseStatus.inProgress;
        if ("on hold".equals(codeString))
          return MedicationDispenseStatus.onHold;
        if ("completed".equals(codeString))
          return MedicationDispenseStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationDispenseStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationDispenseStatus.stopped;
        throw new Exception("Unknown MedicationDispenseStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationDispenseStatus.inProgress)
        return "in progress";
      if (code == MedicationDispenseStatus.onHold)
        return "on hold";
      if (code == MedicationDispenseStatus.completed)
        return "completed";
      if (code == MedicationDispenseStatus.enteredInError)
        return "entered in error";
      if (code == MedicationDispenseStatus.stopped)
        return "stopped";
      return "?";
      }
    }

    public static class MedicationDispenseDispenseComponent extends BackboneElement {
        /**
         * Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.
         */
        protected Identifier identifier;

        /**
         * A code specifying the state of the dispense event.
         */
        protected Enumeration<MedicationDispenseStatus> status;

        /**
         * Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.
         */
        protected CodeableConcept type;

        /**
         * The amount of medication that has been dispensed. Includes unit of measure.
         */
        protected Quantity quantity;

        /**
         * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
         */
        protected ResourceReference medication;

        /**
         * The actual object that is the target of the reference (Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
         */
        protected Medication medicationTarget;

        /**
         * The time when the dispensed product was packaged and reviewed.
         */
        protected DateTime whenPrepared;

        /**
         * The time the dispensed product was provided to the patient or their representative.
         */
        protected DateTime whenHandedOver;

        /**
         * Identification of the facility/location where the medication was shipped to, as part of the dispense event.
         */
        protected ResourceReference destination;

        /**
         * The actual object that is the target of the reference (Identification of the facility/location where the medication was shipped to, as part of the dispense event.)
         */
        protected Location destinationTarget;

        /**
         * Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.
         */
        protected List<ResourceReference> receiver = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.)
         */
        protected List<Resource> receiverTarget = new ArrayList<Resource>();


        /**
         * Indicates how the medication is to be used by the patient.
         */
        protected List<MedicationDispenseDispenseDosageComponent> dosage = new ArrayList<MedicationDispenseDispenseDosageComponent>();

        private static final long serialVersionUID = 1523629260L;

      public MedicationDispenseDispenseComponent() {
        super();
      }

        /**
         * @return {@link #identifier} (Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.)
         */
        public Identifier getIdentifier() { 
          return this.identifier;
        }

        /**
         * @param value {@link #identifier} (Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.)
         */
        public MedicationDispenseDispenseComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        /**
         * @return {@link #status} (A code specifying the state of the dispense event.)
         */
        public Enumeration<MedicationDispenseStatus> getStatus() { 
          return this.status;
        }

        /**
         * @param value {@link #status} (A code specifying the state of the dispense event.)
         */
        public MedicationDispenseDispenseComponent setStatus(Enumeration<MedicationDispenseStatus> value) { 
          this.status = value;
          return this;
        }

        /**
         * @return A code specifying the state of the dispense event.
         */
        public MedicationDispenseStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

        /**
         * @param value A code specifying the state of the dispense event.
         */
        public MedicationDispenseDispenseComponent setStatusSimple(MedicationDispenseStatus value) { 
          if (value == null)
            this.status = null;
          else {
            if (this.status == null)
              this.status = new Enumeration<MedicationDispenseStatus>();
            this.status.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #type} (Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.)
         */
        public MedicationDispenseDispenseComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The amount of medication that has been dispensed. Includes unit of measure.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of medication that has been dispensed. Includes unit of measure.)
         */
        public MedicationDispenseDispenseComponent setQuantity(Quantity value) { 
          this.quantity = value;
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
        public MedicationDispenseDispenseComponent setMedication(ResourceReference value) { 
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
        public MedicationDispenseDispenseComponent setMedicationTarget(Medication value) { 
          this.medicationTarget = value;
          return this;
        }

        /**
         * @return {@link #whenPrepared} (The time when the dispensed product was packaged and reviewed.)
         */
        public DateTime getWhenPrepared() { 
          return this.whenPrepared;
        }

        /**
         * @param value {@link #whenPrepared} (The time when the dispensed product was packaged and reviewed.)
         */
        public MedicationDispenseDispenseComponent setWhenPrepared(DateTime value) { 
          this.whenPrepared = value;
          return this;
        }

        /**
         * @return The time when the dispensed product was packaged and reviewed.
         */
        public DateAndTime getWhenPreparedSimple() { 
          return this.whenPrepared == null ? null : this.whenPrepared.getValue();
        }

        /**
         * @param value The time when the dispensed product was packaged and reviewed.
         */
        public MedicationDispenseDispenseComponent setWhenPreparedSimple(DateAndTime value) { 
          if (value == null)
            this.whenPrepared = null;
          else {
            if (this.whenPrepared == null)
              this.whenPrepared = new DateTime();
            this.whenPrepared.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #whenHandedOver} (The time the dispensed product was provided to the patient or their representative.)
         */
        public DateTime getWhenHandedOver() { 
          return this.whenHandedOver;
        }

        /**
         * @param value {@link #whenHandedOver} (The time the dispensed product was provided to the patient or their representative.)
         */
        public MedicationDispenseDispenseComponent setWhenHandedOver(DateTime value) { 
          this.whenHandedOver = value;
          return this;
        }

        /**
         * @return The time the dispensed product was provided to the patient or their representative.
         */
        public DateAndTime getWhenHandedOverSimple() { 
          return this.whenHandedOver == null ? null : this.whenHandedOver.getValue();
        }

        /**
         * @param value The time the dispensed product was provided to the patient or their representative.
         */
        public MedicationDispenseDispenseComponent setWhenHandedOverSimple(DateAndTime value) { 
          if (value == null)
            this.whenHandedOver = null;
          else {
            if (this.whenHandedOver == null)
              this.whenHandedOver = new DateTime();
            this.whenHandedOver.setValue(value);
          }
          return this;
        }

        /**
         * @return {@link #destination} (Identification of the facility/location where the medication was shipped to, as part of the dispense event.)
         */
        public ResourceReference getDestination() { 
          return this.destination;
        }

        /**
         * @param value {@link #destination} (Identification of the facility/location where the medication was shipped to, as part of the dispense event.)
         */
        public MedicationDispenseDispenseComponent setDestination(ResourceReference value) { 
          this.destination = value;
          return this;
        }

        /**
         * @return {@link #destination} (The actual object that is the target of the reference. Identification of the facility/location where the medication was shipped to, as part of the dispense event.)
         */
        public Location getDestinationTarget() { 
          return this.destinationTarget;
        }

        /**
         * @param value {@link #destination} (The actual object that is the target of the reference. Identification of the facility/location where the medication was shipped to, as part of the dispense event.)
         */
        public MedicationDispenseDispenseComponent setDestinationTarget(Location value) { 
          this.destinationTarget = value;
          return this;
        }

        /**
         * @return {@link #receiver} (Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.)
         */
        public List<ResourceReference> getReceiver() { 
          return this.receiver;
        }

    // syntactic sugar
        /**
         * @return {@link #receiver} (Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.)
         */
        public ResourceReference addReceiver() { 
          ResourceReference t = new ResourceReference();
          this.receiver.add(t);
          return t;
        }

        /**
         * @return {@link #receiver} (The actual objects that are the target of the reference. Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.)
         */
        public List<Resource> getReceiverTarget() { 
          return this.receiverTarget;
        }

        /**
         * @return {@link #dosage} (Indicates how the medication is to be used by the patient.)
         */
        public List<MedicationDispenseDispenseDosageComponent> getDosage() { 
          return this.dosage;
        }

    // syntactic sugar
        /**
         * @return {@link #dosage} (Indicates how the medication is to be used by the patient.)
         */
        public MedicationDispenseDispenseDosageComponent addDosage() { 
          MedicationDispenseDispenseDosageComponent t = new MedicationDispenseDispenseDosageComponent();
          this.dosage.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("identifier", "Identifier", "Identifier assigned by the dispensing facility.   This is an identifier assigned outside FHIR.", 0, java.lang.Integer.MAX_VALUE, identifier));
          childrenList.add(new Property("status", "code", "A code specifying the state of the dispense event.", 0, java.lang.Integer.MAX_VALUE, status));
          childrenList.add(new Property("type", "CodeableConcept", "Indicates the type of dispensing event that is performed. Examples include: Trial Fill, Completion of Trial, Partial Fill, Emergency Fill, Samples, etc.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("quantity", "Quantity", "The amount of medication that has been dispensed. Includes unit of measure.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("medication", "Resource(Medication)", "Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, medication));
          childrenList.add(new Property("whenPrepared", "dateTime", "The time when the dispensed product was packaged and reviewed.", 0, java.lang.Integer.MAX_VALUE, whenPrepared));
          childrenList.add(new Property("whenHandedOver", "dateTime", "The time the dispensed product was provided to the patient or their representative.", 0, java.lang.Integer.MAX_VALUE, whenHandedOver));
          childrenList.add(new Property("destination", "Resource(Location)", "Identification of the facility/location where the medication was shipped to, as part of the dispense event.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("receiver", "Resource(Patient|Practitioner)", "Identifies the person who picked up the medication.  This will usually be a patient or their carer, but some cases exist where it can be a healthcare professional.", 0, java.lang.Integer.MAX_VALUE, receiver));
          childrenList.add(new Property("dosage", "", "Indicates how the medication is to be used by the patient.", 0, java.lang.Integer.MAX_VALUE, dosage));
        }

      public MedicationDispenseDispenseComponent copy() {
        MedicationDispenseDispenseComponent dst = new MedicationDispenseDispenseComponent();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.type = type == null ? null : type.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.whenPrepared = whenPrepared == null ? null : whenPrepared.copy();
        dst.whenHandedOver = whenHandedOver == null ? null : whenHandedOver.copy();
        dst.destination = destination == null ? null : destination.copy();
        dst.receiver = new ArrayList<ResourceReference>();
        for (ResourceReference i : receiver)
          dst.receiver.add(i.copy());
        dst.dosage = new ArrayList<MedicationDispenseDispenseDosageComponent>();
        for (MedicationDispenseDispenseDosageComponent i : dosage)
          dst.dosage.add(i.copy());
        return dst;
      }

  }

    public static class MedicationDispenseDispenseDosageComponent extends BackboneElement {
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
         * A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.
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
        protected Quantity quantity;

        /**
         * Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time,  e.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

        private static final long serialVersionUID = 498364389L;

      public MedicationDispenseDispenseDosageComponent() {
        super();
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
        public MedicationDispenseDispenseDosageComponent setAdditionalInstructions(CodeableConcept value) { 
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
        public MedicationDispenseDispenseDosageComponent setTiming(Type value) { 
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
        public MedicationDispenseDispenseDosageComponent setAsNeeded(Type value) { 
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
        public MedicationDispenseDispenseDosageComponent setSite(CodeableConcept value) { 
          this.site = value;
          return this;
        }

        /**
         * @return {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.)
         */
        public CodeableConcept getRoute() { 
          return this.route;
        }

        /**
         * @param value {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.)
         */
        public MedicationDispenseDispenseDosageComponent setRoute(CodeableConcept value) { 
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
        public MedicationDispenseDispenseDosageComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The amount of therapeutic or other substance given at one administration event.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of therapeutic or other substance given at one administration event.)
         */
        public MedicationDispenseDispenseDosageComponent setQuantity(Quantity value) { 
          this.quantity = value;
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
        public MedicationDispenseDispenseDosageComponent setRate(Ratio value) { 
          this.rate = value;
          return this;
        }

        /**
         * @return {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time,  e.g. 1000mg in 24 hours.)
         */
        public Ratio getMaxDosePerPeriod() { 
          return this.maxDosePerPeriod;
        }

        /**
         * @param value {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time,  e.g. 1000mg in 24 hours.)
         */
        public MedicationDispenseDispenseDosageComponent setMaxDosePerPeriod(Ratio value) { 
          this.maxDosePerPeriod = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("additionalInstructions", "CodeableConcept", "Additional instructions such as 'Swallow with plenty of water' which may or may not be coded.", 0, java.lang.Integer.MAX_VALUE, additionalInstructions));
          childrenList.add(new Property("timing[x]", "dateTime|Period|Schedule", "The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  'Every  8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:';  '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
          childrenList.add(new Property("asNeeded[x]", "boolean|CodeableConcept", "If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.", 0, java.lang.Integer.MAX_VALUE, asNeeded));
          childrenList.add(new Property("site", "CodeableConcept", "A coded specification of the anatomic site where the medication first enters the body.", 0, java.lang.Integer.MAX_VALUE, site));
          childrenList.add(new Property("route", "CodeableConcept", "A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.", 0, java.lang.Integer.MAX_VALUE, route));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.\n\nTerminologies used often pre-coordinate this term with the route and or form of administration.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("quantity", "Quantity", "The amount of therapeutic or other substance given at one administration event.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("rate", "Ratio", "Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.", 0, java.lang.Integer.MAX_VALUE, rate));
          childrenList.add(new Property("maxDosePerPeriod", "Ratio", "The maximum total quantity of a therapeutic substance that may be administered to a subject over the period of time,  e.g. 1000mg in 24 hours.", 0, java.lang.Integer.MAX_VALUE, maxDosePerPeriod));
        }

      public MedicationDispenseDispenseDosageComponent copy() {
        MedicationDispenseDispenseDosageComponent dst = new MedicationDispenseDispenseDosageComponent();
        dst.additionalInstructions = additionalInstructions == null ? null : additionalInstructions.copy();
        dst.timing = timing == null ? null : timing.copy();
        dst.asNeeded = asNeeded == null ? null : asNeeded.copy();
        dst.site = site == null ? null : site.copy();
        dst.route = route == null ? null : route.copy();
        dst.method = method == null ? null : method.copy();
        dst.quantity = quantity == null ? null : quantity.copy();
        dst.rate = rate == null ? null : rate.copy();
        dst.maxDosePerPeriod = maxDosePerPeriod == null ? null : maxDosePerPeriod.copy();
        return dst;
      }

  }

    public static class MedicationDispenseSubstitutionComponent extends BackboneElement {
        /**
         * A code signifying whether a different drug was dispensed from what was prescribed.
         */
        protected CodeableConcept type;

        /**
         * Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.
         */
        protected List<CodeableConcept> reason = new ArrayList<CodeableConcept>();

        /**
         * The person or organization that has primary responsibility for the substitution.
         */
        protected List<ResourceReference> responsibleParty = new ArrayList<ResourceReference>();
        /**
         * The actual objects that are the target of the reference (The person or organization that has primary responsibility for the substitution.)
         */
        protected List<Practitioner> responsiblePartyTarget = new ArrayList<Practitioner>();


        private static final long serialVersionUID = -1275850915L;

      public MedicationDispenseSubstitutionComponent() {
        super();
      }

      public MedicationDispenseSubstitutionComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        /**
         * @return {@link #type} (A code signifying whether a different drug was dispensed from what was prescribed.)
         */
        public CodeableConcept getType() { 
          return this.type;
        }

        /**
         * @param value {@link #type} (A code signifying whether a different drug was dispensed from what was prescribed.)
         */
        public MedicationDispenseSubstitutionComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        /**
         * @return {@link #reason} (Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.)
         */
        public List<CodeableConcept> getReason() { 
          return this.reason;
        }

    // syntactic sugar
        /**
         * @return {@link #reason} (Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.)
         */
        public CodeableConcept addReason() { 
          CodeableConcept t = new CodeableConcept();
          this.reason.add(t);
          return t;
        }

        /**
         * @return {@link #responsibleParty} (The person or organization that has primary responsibility for the substitution.)
         */
        public List<ResourceReference> getResponsibleParty() { 
          return this.responsibleParty;
        }

    // syntactic sugar
        /**
         * @return {@link #responsibleParty} (The person or organization that has primary responsibility for the substitution.)
         */
        public ResourceReference addResponsibleParty() { 
          ResourceReference t = new ResourceReference();
          this.responsibleParty.add(t);
          return t;
        }

        /**
         * @return {@link #responsibleParty} (The actual objects that are the target of the reference. The person or organization that has primary responsibility for the substitution.)
         */
        public List<Practitioner> getResponsiblePartyTarget() { 
          return this.responsiblePartyTarget;
        }

    // syntactic sugar
        /**
         * @return {@link #responsibleParty} (Add an actual object that is the target of the reference. The person or organization that has primary responsibility for the substitution.)
         */
        public Practitioner addResponsiblePartyTarget() { 
          Practitioner r = new Practitioner();
          this.responsiblePartyTarget.add(r);
          return r;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "A code signifying whether a different drug was dispensed from what was prescribed.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("reason", "CodeableConcept", "Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.", 0, java.lang.Integer.MAX_VALUE, reason));
          childrenList.add(new Property("responsibleParty", "Resource(Practitioner)", "The person or organization that has primary responsibility for the substitution.", 0, java.lang.Integer.MAX_VALUE, responsibleParty));
        }

      public MedicationDispenseSubstitutionComponent copy() {
        MedicationDispenseSubstitutionComponent dst = new MedicationDispenseSubstitutionComponent();
        dst.type = type == null ? null : type.copy();
        dst.reason = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : reason)
          dst.reason.add(i.copy());
        dst.responsibleParty = new ArrayList<ResourceReference>();
        for (ResourceReference i : responsibleParty)
          dst.responsibleParty.add(i.copy());
        return dst;
      }

  }

    /**
     * Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.
     */
    protected Identifier identifier;

    /**
     * A code specifying the state of the set of dispense events.
     */
    protected Enumeration<MedicationDispenseStatus> status;

    /**
     * A link to a resource representing the person to whom the medication will be given.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (A link to a resource representing the person to whom the medication will be given.)
     */
    protected Patient patientTarget;

    /**
     * The individual responsible for dispensing the medication.
     */
    protected ResourceReference dispenser;

    /**
     * The actual object that is the target of the reference (The individual responsible for dispensing the medication.)
     */
    protected Practitioner dispenserTarget;

    /**
     * Indicates the medication order that is being dispensed against.
     */
    protected List<ResourceReference> authorizingPrescription = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (Indicates the medication order that is being dispensed against.)
     */
    protected List<MedicationPrescription> authorizingPrescriptionTarget = new ArrayList<MedicationPrescription>();


    /**
     * Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.
     */
    protected List<MedicationDispenseDispenseComponent> dispense = new ArrayList<MedicationDispenseDispenseComponent>();

    /**
     * Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.
     */
    protected MedicationDispenseSubstitutionComponent substitution;

    private static final long serialVersionUID = 1726669338L;

    public MedicationDispense() {
      super();
    }

    /**
     * @return {@link #identifier} (Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.)
     */
    public Identifier getIdentifier() { 
      return this.identifier;
    }

    /**
     * @param value {@link #identifier} (Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.)
     */
    public MedicationDispense setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    /**
     * @return {@link #status} (A code specifying the state of the set of dispense events.)
     */
    public Enumeration<MedicationDispenseStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (A code specifying the state of the set of dispense events.)
     */
    public MedicationDispense setStatus(Enumeration<MedicationDispenseStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return A code specifying the state of the set of dispense events.
     */
    public MedicationDispenseStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value A code specifying the state of the set of dispense events.
     */
    public MedicationDispense setStatusSimple(MedicationDispenseStatus value) { 
      if (value == null)
        this.status = null;
      else {
        if (this.status == null)
          this.status = new Enumeration<MedicationDispenseStatus>();
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
    public MedicationDispense setPatient(ResourceReference value) { 
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
    public MedicationDispense setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #dispenser} (The individual responsible for dispensing the medication.)
     */
    public ResourceReference getDispenser() { 
      return this.dispenser;
    }

    /**
     * @param value {@link #dispenser} (The individual responsible for dispensing the medication.)
     */
    public MedicationDispense setDispenser(ResourceReference value) { 
      this.dispenser = value;
      return this;
    }

    /**
     * @return {@link #dispenser} (The actual object that is the target of the reference. The individual responsible for dispensing the medication.)
     */
    public Practitioner getDispenserTarget() { 
      return this.dispenserTarget;
    }

    /**
     * @param value {@link #dispenser} (The actual object that is the target of the reference. The individual responsible for dispensing the medication.)
     */
    public MedicationDispense setDispenserTarget(Practitioner value) { 
      this.dispenserTarget = value;
      return this;
    }

    /**
     * @return {@link #authorizingPrescription} (Indicates the medication order that is being dispensed against.)
     */
    public List<ResourceReference> getAuthorizingPrescription() { 
      return this.authorizingPrescription;
    }

    // syntactic sugar
    /**
     * @return {@link #authorizingPrescription} (Indicates the medication order that is being dispensed against.)
     */
    public ResourceReference addAuthorizingPrescription() { 
      ResourceReference t = new ResourceReference();
      this.authorizingPrescription.add(t);
      return t;
    }

    /**
     * @return {@link #authorizingPrescription} (The actual objects that are the target of the reference. Indicates the medication order that is being dispensed against.)
     */
    public List<MedicationPrescription> getAuthorizingPrescriptionTarget() { 
      return this.authorizingPrescriptionTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #authorizingPrescription} (Add an actual object that is the target of the reference. Indicates the medication order that is being dispensed against.)
     */
    public MedicationPrescription addAuthorizingPrescriptionTarget() { 
      MedicationPrescription r = new MedicationPrescription();
      this.authorizingPrescriptionTarget.add(r);
      return r;
    }

    /**
     * @return {@link #dispense} (Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.)
     */
    public List<MedicationDispenseDispenseComponent> getDispense() { 
      return this.dispense;
    }

    // syntactic sugar
    /**
     * @return {@link #dispense} (Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.)
     */
    public MedicationDispenseDispenseComponent addDispense() { 
      MedicationDispenseDispenseComponent t = new MedicationDispenseDispenseComponent();
      this.dispense.add(t);
      return t;
    }

    /**
     * @return {@link #substitution} (Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.)
     */
    public MedicationDispenseSubstitutionComponent getSubstitution() { 
      return this.substitution;
    }

    /**
     * @param value {@link #substitution} (Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.)
     */
    public MedicationDispense setSubstitution(MedicationDispenseSubstitutionComponent value) { 
      this.substitution = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "A code specifying the state of the set of dispense events.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Resource(Patient)", "A link to a resource representing the person to whom the medication will be given.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("dispenser", "Resource(Practitioner)", "The individual responsible for dispensing the medication.", 0, java.lang.Integer.MAX_VALUE, dispenser));
        childrenList.add(new Property("authorizingPrescription", "Resource(MedicationPrescription)", "Indicates the medication order that is being dispensed against.", 0, java.lang.Integer.MAX_VALUE, authorizingPrescription));
        childrenList.add(new Property("dispense", "", "Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.", 0, java.lang.Integer.MAX_VALUE, dispense));
        childrenList.add(new Property("substitution", "", "Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.", 0, java.lang.Integer.MAX_VALUE, substitution));
      }

      public MedicationDispense copy() {
        MedicationDispense dst = new MedicationDispense();
        dst.identifier = identifier == null ? null : identifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.dispenser = dispenser == null ? null : dispenser.copy();
        dst.authorizingPrescription = new ArrayList<ResourceReference>();
        for (ResourceReference i : authorizingPrescription)
          dst.authorizingPrescription.add(i.copy());
        dst.dispense = new ArrayList<MedicationDispenseDispenseComponent>();
        for (MedicationDispenseDispenseComponent i : dispense)
          dst.dispense.add(i.copy());
        dst.substitution = substitution == null ? null : substitution.copy();
        return dst;
      }

      protected MedicationDispense typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationDispense;
   }


}

