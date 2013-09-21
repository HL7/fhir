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

// Generated on Sun, Sep 22, 2013 06:57+1000 for FHIR v0.11

import java.util.*;

/**
 * Describes the event of a patient being given a dose of a medication.  This may be as simple as swallowing a tablet or it may be a long running infusion.

Related resources tie this event to the authorizing prescription, and the specific encounter between patient and health care practitioner.
 */
public class MedicationAdministration extends Resource {

    public enum MedicationAdminStatus {
        active, // The administration has started but has not yet completed.
        held, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "held".
        completed, // All actions that are implied by the administration have occurred.
        enteredInError, // The administration was entered in error and therefore nullified.
        stopped, // Actions implied by the administration have been permanently halted, before all of them occurred.
        Null; // added to help the parsers
        public static MedicationAdminStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return active;
        if ("held".equals(codeString))
          return held;
        if ("completed".equals(codeString))
          return completed;
        if ("entered in error".equals(codeString))
          return enteredInError;
        if ("stopped".equals(codeString))
          return stopped;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case held: return "held";
            case completed: return "completed";
            case enteredInError: return "entered in error";
            case stopped: return "stopped";
            default: return "?";
          }
        }
    }

  public class MedicationAdminStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return MedicationAdminStatus.active;
        if ("held".equals(codeString))
          return MedicationAdminStatus.held;
        if ("completed".equals(codeString))
          return MedicationAdminStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationAdminStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationAdminStatus.stopped;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationAdminStatus.active)
        return "active";
      if (code == MedicationAdminStatus.held)
        return "held";
      if (code == MedicationAdminStatus.completed)
        return "completed";
      if (code == MedicationAdminStatus.enteredInError)
        return "entered in error";
      if (code == MedicationAdminStatus.stopped)
        return "stopped";
      return "?";
      }
    }

    public class MedicationAdministrationDosageComponent extends Element {
        /**
         * The timing schedule for giving the medication to the patient.  The Schedule data type allows many different expressions, for example.  "Every  8 hours"; "Three times a day"; "1/2 an hour before breakfast for 10 days from 23-Dec 2011:";  "15 Oct 2013, 17 Oct 2013 and 1 Nov 2013".
         */
        protected Schedule timing;

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
         * The amount of themedication given at one administration event.   Use this value when the administration is essentially an instantaneous event such as a swallowing a tablet or giving an injection.
         */
        protected Quantity quantity;

        /**
         * Identifies the speed with which the medication is introduced into the patient. Typically the rate for an infusion e.g. 200ml in 2 hours.  May also expressed as a rate per unit of time such as 100ml per hour - the duration is then not specified, or is specified in the quantity.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that my be administered to a subject over the period of time. E.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

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

      public MedicationAdministrationDosageComponent copy(MedicationAdministration e) {
        MedicationAdministrationDosageComponent dst = e.new MedicationAdministrationDosageComponent();
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

    /**
     * External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    protected Enumeration<MedicationAdminStatus> status;

    /**
     * A link to a resource representing the person to whom the medication was given.
     */
    protected ResourceReference patient;

    /**
     * The individual who is responsible for giving the medication to the patient.
     */
    protected ResourceReference practitioner;

    /**
     * An link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    protected ResourceReference encounter;

    /**
     * A link to a resource that provides the original request, instruction and authority to perform the administration.
     */
    protected ResourceReference prescription;

    /**
     * Set this to true if the record is saying that the medication was NOT administered.
     */
    protected Boolean wasNotGiven;

    /**
     * A code indicating why the administration has been negated.

Use only if isNegated is set to TRUE.
     */
    protected List<CodeableConcept> reasonNotGiven = new ArrayList<CodeableConcept>();

    /**
     * An interval of time during which the administration takes place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.
     */
    protected Period whenGiven;

    /**
     * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected ResourceReference medication;

    /**
     * An identifier or a link to a resource that identifies a device used in administering the medication to the patient.
     */
    protected List<ResourceReference> administrationDevice = new ArrayList<ResourceReference>();

    /**
     * Indicates how the medication is to be used by the patient.
     */
    protected List<MedicationAdministrationDosageComponent> dosage = new ArrayList<MedicationAdministrationDosageComponent>();

    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    public Enumeration<MedicationAdminStatus> getStatus() { 
      return this.status;
    }

    public void setStatus(Enumeration<MedicationAdminStatus> value) { 
      this.status = value;
    }

    public MedicationAdminStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    public void setStatusSimple(MedicationAdminStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<MedicationAdminStatus>();
        this.status.setValue(value);
    }

    public ResourceReference getPatient() { 
      return this.patient;
    }

    public void setPatient(ResourceReference value) { 
      this.patient = value;
    }

    public ResourceReference getPractitioner() { 
      return this.practitioner;
    }

    public void setPractitioner(ResourceReference value) { 
      this.practitioner = value;
    }

    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    public void setEncounter(ResourceReference value) { 
      this.encounter = value;
    }

    public ResourceReference getPrescription() { 
      return this.prescription;
    }

    public void setPrescription(ResourceReference value) { 
      this.prescription = value;
    }

    public Boolean getWasNotGiven() { 
      return this.wasNotGiven;
    }

    public void setWasNotGiven(Boolean value) { 
      this.wasNotGiven = value;
    }

    public boolean getWasNotGivenSimple() { 
      return this.wasNotGiven == null ? null : this.wasNotGiven.getValue();
    }

    public void setWasNotGivenSimple(boolean value) { 
      if (value == false)
        this.wasNotGiven = null;
      else {
        if (this.wasNotGiven == null)
          this.wasNotGiven = new Boolean();
        this.wasNotGiven.setValue(value);
      }
    }

    public List<CodeableConcept> getReasonNotGiven() { 
      return this.reasonNotGiven;
    }

    // syntactic sugar
    public CodeableConcept addReasonNotGiven() { 
      CodeableConcept t = new CodeableConcept();
      this.reasonNotGiven.add(t);
      return t;
    }

    public Period getWhenGiven() { 
      return this.whenGiven;
    }

    public void setWhenGiven(Period value) { 
      this.whenGiven = value;
    }

    public ResourceReference getMedication() { 
      return this.medication;
    }

    public void setMedication(ResourceReference value) { 
      this.medication = value;
    }

    public List<ResourceReference> getAdministrationDevice() { 
      return this.administrationDevice;
    }

    // syntactic sugar
    public ResourceReference addAdministrationDevice() { 
      ResourceReference t = new ResourceReference();
      this.administrationDevice.add(t);
      return t;
    }

    public List<MedicationAdministrationDosageComponent> getDosage() { 
      return this.dosage;
    }

    // syntactic sugar
    public MedicationAdministrationDosageComponent addDosage() { 
      MedicationAdministrationDosageComponent t = new MedicationAdministrationDosageComponent();
      this.dosage.add(t);
      return t;
    }

      public MedicationAdministration copy() {
        MedicationAdministration dst = new MedicationAdministration();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.status = status == null ? null : status.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.practitioner = practitioner == null ? null : practitioner.copy();
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.prescription = prescription == null ? null : prescription.copy();
        dst.wasNotGiven = wasNotGiven == null ? null : wasNotGiven.copy();
        dst.reasonNotGiven = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : reasonNotGiven)
          dst.reasonNotGiven.add(i.copy());
        dst.whenGiven = whenGiven == null ? null : whenGiven.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.administrationDevice = new ArrayList<ResourceReference>();
        for (ResourceReference i : administrationDevice)
          dst.administrationDevice.add(i.copy());
        dst.dosage = new ArrayList<MedicationAdministrationDosageComponent>();
        for (MedicationAdministrationDosageComponent i : dosage)
          dst.dosage.add(i.copy(dst));
        return dst;
      }

      protected MedicationAdministration typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationAdministration;
   }


}

