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

// Generated on Mon, Oct 28, 2013 15:39+1100 for FHIR v0.12

import java.util.*;

/**
 * Dispensing a medication to a named patient.  This includes a description of the supply provided and the instructions for administering the medication.
 */
public class MedicationDispense extends Resource {

    public enum MedicationDispenseStatus {
        active, // The dispense has started but has not yet completed.
        suspended, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "held".
        completed, // All actions that are implied by the dispense have occurred.
        enteredInError, // The dispense was entered in error and therefore nullified.
        stopped, // Actions implied by the dispense have been permanently halted, before all of them occurred.
        Null; // added to help the parsers
        public static MedicationDispenseStatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown MedicationDispenseStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case suspended: return "suspended";
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
        if ("active".equals(codeString))
          return MedicationDispenseStatus.active;
        if ("suspended".equals(codeString))
          return MedicationDispenseStatus.suspended;
        if ("completed".equals(codeString))
          return MedicationDispenseStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationDispenseStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationDispenseStatus.stopped;
        throw new Exception("Unknown MedicationDispenseStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationDispenseStatus.active)
        return "active";
      if (code == MedicationDispenseStatus.suspended)
        return "suspended";
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
         * The time the dispense event occurred.
         */
        protected Period whenPrepared;

        /**
         * The time the dispense event occurred.
         */
        protected Period whenHandedOver;

        /**
         * Identification of the facility/location where the medication was shipped to, as part of the dispense event.
         */
        protected ResourceReference destination;

        /**
         * Identifies the person who picked up the medication.
         */
        protected List<ResourceReference> receiver = new ArrayList<ResourceReference>();

        /**
         * Indicates how the medication is to be used by the patient.
         */
        protected List<MedicationDispenseDispenseDosageComponent> dosage = new ArrayList<MedicationDispenseDispenseDosageComponent>();

      public MedicationDispenseDispenseComponent() {
        super();
      }

        public Identifier getIdentifier() { 
          return this.identifier;
        }

        public MedicationDispenseDispenseComponent setIdentifier(Identifier value) { 
          this.identifier = value;
          return this;
        }

        public Enumeration<MedicationDispenseStatus> getStatus() { 
          return this.status;
        }

        public MedicationDispenseDispenseComponent setStatus(Enumeration<MedicationDispenseStatus> value) { 
          this.status = value;
          return this;
        }

        public MedicationDispenseStatus getStatusSimple() { 
          return this.status == null ? null : this.status.getValue();
        }

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

        public CodeableConcept getType() { 
          return this.type;
        }

        public MedicationDispenseDispenseComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public MedicationDispenseDispenseComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        public ResourceReference getMedication() { 
          return this.medication;
        }

        public MedicationDispenseDispenseComponent setMedication(ResourceReference value) { 
          this.medication = value;
          return this;
        }

        public Period getWhenPrepared() { 
          return this.whenPrepared;
        }

        public MedicationDispenseDispenseComponent setWhenPrepared(Period value) { 
          this.whenPrepared = value;
          return this;
        }

        public Period getWhenHandedOver() { 
          return this.whenHandedOver;
        }

        public MedicationDispenseDispenseComponent setWhenHandedOver(Period value) { 
          this.whenHandedOver = value;
          return this;
        }

        public ResourceReference getDestination() { 
          return this.destination;
        }

        public MedicationDispenseDispenseComponent setDestination(ResourceReference value) { 
          this.destination = value;
          return this;
        }

        public List<ResourceReference> getReceiver() { 
          return this.receiver;
        }

    // syntactic sugar
        public ResourceReference addReceiver() { 
          ResourceReference t = new ResourceReference();
          this.receiver.add(t);
          return t;
        }

        public List<MedicationDispenseDispenseDosageComponent> getDosage() { 
          return this.dosage;
        }

    // syntactic sugar
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
          childrenList.add(new Property("whenPrepared", "Period", "The time the dispense event occurred.", 0, java.lang.Integer.MAX_VALUE, whenPrepared));
          childrenList.add(new Property("whenHandedOver", "Period", "The time the dispense event occurred.", 0, java.lang.Integer.MAX_VALUE, whenHandedOver));
          childrenList.add(new Property("destination", "Resource(Location)", "Identification of the facility/location where the medication was shipped to, as part of the dispense event.", 0, java.lang.Integer.MAX_VALUE, destination));
          childrenList.add(new Property("receiver", "Resource(Practitioner)", "Identifies the person who picked up the medication.", 0, java.lang.Integer.MAX_VALUE, receiver));
          childrenList.add(new Property("dosage", "", "Indicates how the medication is to be used by the patient.", 0, java.lang.Integer.MAX_VALUE, dosage));
        }

      public MedicationDispenseDispenseComponent copy(MedicationDispense e) {
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
          dst.dosage.add(i.copy(e));
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
         * The amount of the therapeutic or other substance given at one administration event.
         */
        protected Quantity quantity;

        /**
         * Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that my be administered to a subject over the period of time. E.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

      public MedicationDispenseDispenseDosageComponent() {
        super();
      }

        public CodeableConcept getAdditionalInstructions() { 
          return this.additionalInstructions;
        }

        public MedicationDispenseDispenseDosageComponent setAdditionalInstructions(CodeableConcept value) { 
          this.additionalInstructions = value;
          return this;
        }

        public Type getTiming() { 
          return this.timing;
        }

        public MedicationDispenseDispenseDosageComponent setTiming(Type value) { 
          this.timing = value;
          return this;
        }

        public CodeableConcept getSite() { 
          return this.site;
        }

        public MedicationDispenseDispenseDosageComponent setSite(CodeableConcept value) { 
          this.site = value;
          return this;
        }

        public CodeableConcept getRoute() { 
          return this.route;
        }

        public MedicationDispenseDispenseDosageComponent setRoute(CodeableConcept value) { 
          this.route = value;
          return this;
        }

        public CodeableConcept getMethod() { 
          return this.method;
        }

        public MedicationDispenseDispenseDosageComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        public Quantity getQuantity() { 
          return this.quantity;
        }

        public MedicationDispenseDispenseDosageComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        public Ratio getRate() { 
          return this.rate;
        }

        public MedicationDispenseDispenseDosageComponent setRate(Ratio value) { 
          this.rate = value;
          return this;
        }

        public Ratio getMaxDosePerPeriod() { 
          return this.maxDosePerPeriod;
        }

        public MedicationDispenseDispenseDosageComponent setMaxDosePerPeriod(Ratio value) { 
          this.maxDosePerPeriod = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("additionalInstructions", "CodeableConcept", "Additional instructions such as 'Swallow with plenty of water' which may or may not be coded.", 0, java.lang.Integer.MAX_VALUE, additionalInstructions));
          childrenList.add(new Property("timing[x]", "dateTime|Period|Schedule", "The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  'Every  8 hours'; 'Three times a day'; '1/2 an hour before breakfast for 10 days from 23-Dec 2011:';  '15 Oct 2013, 17 Oct 2013 and 1 Nov 2013'.", 0, java.lang.Integer.MAX_VALUE, timing));
          childrenList.add(new Property("site", "CodeableConcept", "A coded specification of the anatomic site where the medication first enters the body.", 0, java.lang.Integer.MAX_VALUE, site));
          childrenList.add(new Property("route", "CodeableConcept", "A code specifying the route or physiological path of administration of a therapeutic agent into or onto a subject.", 0, java.lang.Integer.MAX_VALUE, route));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value indicating the method by which the medication is introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.\n\nTerminologies used often pre-coordinate this term with the route and or form of administration.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("quantity", "Quantity", "The amount of the therapeutic or other substance given at one administration event.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("rate", "Ratio", "Identifies the speed with which the substance is introduced into the subject. Typically the rate for an infusion. 200ml in 2 hours.", 0, java.lang.Integer.MAX_VALUE, rate));
          childrenList.add(new Property("maxDosePerPeriod", "Ratio", "The maximum total quantity of a therapeutic substance that my be administered to a subject over the period of time. E.g. 1000mg in 24 hours.", 0, java.lang.Integer.MAX_VALUE, maxDosePerPeriod));
        }

      public MedicationDispenseDispenseDosageComponent copy(MedicationDispense e) {
        MedicationDispenseDispenseDosageComponent dst = new MedicationDispenseDispenseDosageComponent();
        dst.additionalInstructions = additionalInstructions == null ? null : additionalInstructions.copy();
        dst.timing = timing == null ? null : timing.copy();
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

      public MedicationDispenseSubstitutionComponent() {
        super();
      }

      public MedicationDispenseSubstitutionComponent(CodeableConcept type) {
        super();
        this.type = type;
      }

        public CodeableConcept getType() { 
          return this.type;
        }

        public MedicationDispenseSubstitutionComponent setType(CodeableConcept value) { 
          this.type = value;
          return this;
        }

        public List<CodeableConcept> getReason() { 
          return this.reason;
        }

    // syntactic sugar
        public CodeableConcept addReason() { 
          CodeableConcept t = new CodeableConcept();
          this.reason.add(t);
          return t;
        }

        public List<ResourceReference> getResponsibleParty() { 
          return this.responsibleParty;
        }

    // syntactic sugar
        public ResourceReference addResponsibleParty() { 
          ResourceReference t = new ResourceReference();
          this.responsibleParty.add(t);
          return t;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("type", "CodeableConcept", "A code signifying whether a different drug was dispensed from what was prescribed.", 0, java.lang.Integer.MAX_VALUE, type));
          childrenList.add(new Property("reason", "CodeableConcept", "Indicates the reason for the substitution of (or lack of substitution) from what was prescribed.", 0, java.lang.Integer.MAX_VALUE, reason));
          childrenList.add(new Property("responsibleParty", "Resource(Practitioner)", "The person or organization that has primary responsibility for the substitution.", 0, java.lang.Integer.MAX_VALUE, responsibleParty));
        }

      public MedicationDispenseSubstitutionComponent copy(MedicationDispense e) {
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
     * The individual reponsible for dispensing the medication.
     */
    protected ResourceReference dispenser;

    /**
     * Indicates the medication order that is being dispensed against.
     */
    protected List<ResourceReference> authorizingPrescription = new ArrayList<ResourceReference>();

    /**
     * Indicates the details of the dispense event such as the days supply and quantity of medication dispensed.
     */
    protected List<MedicationDispenseDispenseComponent> dispense = new ArrayList<MedicationDispenseDispenseComponent>();

    /**
     * Indicates whether or not substitution was made as part of the dispense.  In some cases substitution will be expected but doesn't happen, in other cases substitution is not expected but does happen.  This block explains what substitition did or did not happen and why.
     */
    protected MedicationDispenseSubstitutionComponent substitution;

    public MedicationDispense() {
      super();
    }

    public Identifier getIdentifier() { 
      return this.identifier;
    }

    public MedicationDispense setIdentifier(Identifier value) { 
      this.identifier = value;
      return this;
    }

    public Enumeration<MedicationDispenseStatus> getStatus() { 
      return this.status;
    }

    public MedicationDispense setStatus(Enumeration<MedicationDispenseStatus> value) { 
      this.status = value;
      return this;
    }

    public MedicationDispenseStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

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

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public MedicationDispense setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    public ResourceReference getDispenser() { 
      return this.dispenser;
    }

    public MedicationDispense setDispenser(ResourceReference value) { 
      this.dispenser = value;
      return this;
    }

    public List<ResourceReference> getAuthorizingPrescription() { 
      return this.authorizingPrescription;
    }

    // syntactic sugar
    public ResourceReference addAuthorizingPrescription() { 
      ResourceReference t = new ResourceReference();
      this.authorizingPrescription.add(t);
      return t;
    }

    public List<MedicationDispenseDispenseComponent> getDispense() { 
      return this.dispense;
    }

    // syntactic sugar
    public MedicationDispenseDispenseComponent addDispense() { 
      MedicationDispenseDispenseComponent t = new MedicationDispenseDispenseComponent();
      this.dispense.add(t);
      return t;
    }

    public MedicationDispenseSubstitutionComponent getSubstitution() { 
      return this.substitution;
    }

    public MedicationDispense setSubstitution(MedicationDispenseSubstitutionComponent value) { 
      this.substitution = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Identifier assigned by the dispensing facility - this is an identifier assigned outside FHIR.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "A code specifying the state of the set of dispense events.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Resource(Patient)", "A link to a resource representing the person to whom the medication will be given.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("dispenser", "Resource(Practitioner)", "The individual reponsible for dispensing the medication.", 0, java.lang.Integer.MAX_VALUE, dispenser));
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
          dst.dispense.add(i.copy(dst));
        dst.substitution = substitution == null ? null : substitution.copy(dst);
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

