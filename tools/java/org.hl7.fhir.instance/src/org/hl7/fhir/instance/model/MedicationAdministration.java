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

// Generated on Tue, Aug 26, 2014 16:54+1000 for FHIR v0.3.0

import java.util.*;

/**
 * Describes the event of a patient being given a dose of a medication.  This may be as simple as swallowing a tablet or it may be a long running infusion.

Related resources tie this event to the authorizing prescription, and the specific encounter between patient and health care practitioner.
 */
public class MedicationAdministration extends Resource {

    public enum MedicationAdminStatus {
        inProgress, // The administration has started but has not yet completed.
        onHold, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "suspended".
        completed, // All actions that are implied by the administration have occurred.
        enteredInError, // The administration was entered in error and therefore nullified.
        stopped, // Actions implied by the administration have been permanently halted, before all of them occurred.
        Null; // added to help the parsers
        public static MedicationAdminStatus fromCode(String codeString) throws Exception {
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
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
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

  public static class MedicationAdminStatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return MedicationAdminStatus.inProgress;
        if ("on hold".equals(codeString))
          return MedicationAdminStatus.onHold;
        if ("completed".equals(codeString))
          return MedicationAdminStatus.completed;
        if ("entered in error".equals(codeString))
          return MedicationAdminStatus.enteredInError;
        if ("stopped".equals(codeString))
          return MedicationAdminStatus.stopped;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationAdminStatus.inProgress)
        return "in progress";
      if (code == MedicationAdminStatus.onHold)
        return "on hold";
      if (code == MedicationAdminStatus.completed)
        return "completed";
      if (code == MedicationAdminStatus.enteredInError)
        return "entered in error";
      if (code == MedicationAdminStatus.stopped)
        return "stopped";
      return "?";
      }
    }

    public static class MedicationAdministrationDosageComponent extends BackboneElement {
        /**
         * The timing schedule for giving the medication to the patient.  This may be a single time point (using dateTime) or it may be a start and end dateTime (Period).
         */
        protected Type timing;

        /**
         * If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.
         */
        protected Type asNeeded;

        /**
         * A coded specification of the anatomic site where the medication first entered the body.  E.g. "left arm".
         */
        protected CodeableConcept site;

        /**
         * A code specifying the route or physiological path of administration of a therapeutic agent into or onto the patient.   E.g. topical, intravenous, etc.
         */
        protected CodeableConcept route;

        /**
         * A coded value indicating the method by which the medication was introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.
         */
        protected CodeableConcept method;

        /**
         * The amount of the medication given at one administration event.   Use this value when the administration is essentially an instantaneous event such as a swallowing a tablet or giving an injection.
         */
        protected Quantity quantity;

        /**
         * Identifies the speed with which the medication was introduced into the patient. Typically the rate for an infusion e.g. 200ml in 2 hours.  May also expressed as a rate per unit of time such as 100ml per hour - the duration is then not specified, or is specified in the quantity.
         */
        protected Ratio rate;

        /**
         * The maximum total quantity of a therapeutic substance that was administered to the patient over the specified period of time. E.g. 1000mg in 24 hours.
         */
        protected Ratio maxDosePerPeriod;

        private static final long serialVersionUID = 1667442570L;

      public MedicationAdministrationDosageComponent() {
        super();
      }

        /**
         * @return {@link #timing} (The timing schedule for giving the medication to the patient.  This may be a single time point (using dateTime) or it may be a start and end dateTime (Period).)
         */
        public Type getTiming() { 
          return this.timing;
        }

        /**
         * @param value {@link #timing} (The timing schedule for giving the medication to the patient.  This may be a single time point (using dateTime) or it may be a start and end dateTime (Period).)
         */
        public MedicationAdministrationDosageComponent setTiming(Type value) { 
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
        public MedicationAdministrationDosageComponent setAsNeeded(Type value) { 
          this.asNeeded = value;
          return this;
        }

        /**
         * @return {@link #site} (A coded specification of the anatomic site where the medication first entered the body.  E.g. "left arm".)
         */
        public CodeableConcept getSite() { 
          return this.site;
        }

        /**
         * @param value {@link #site} (A coded specification of the anatomic site where the medication first entered the body.  E.g. "left arm".)
         */
        public MedicationAdministrationDosageComponent setSite(CodeableConcept value) { 
          this.site = value;
          return this;
        }

        /**
         * @return {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto the patient.   E.g. topical, intravenous, etc.)
         */
        public CodeableConcept getRoute() { 
          return this.route;
        }

        /**
         * @param value {@link #route} (A code specifying the route or physiological path of administration of a therapeutic agent into or onto the patient.   E.g. topical, intravenous, etc.)
         */
        public MedicationAdministrationDosageComponent setRoute(CodeableConcept value) { 
          this.route = value;
          return this;
        }

        /**
         * @return {@link #method} (A coded value indicating the method by which the medication was introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.)
         */
        public CodeableConcept getMethod() { 
          return this.method;
        }

        /**
         * @param value {@link #method} (A coded value indicating the method by which the medication was introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.

Terminologies used often pre-coordinate this term with the route and or form of administration.)
         */
        public MedicationAdministrationDosageComponent setMethod(CodeableConcept value) { 
          this.method = value;
          return this;
        }

        /**
         * @return {@link #quantity} (The amount of the medication given at one administration event.   Use this value when the administration is essentially an instantaneous event such as a swallowing a tablet or giving an injection.)
         */
        public Quantity getQuantity() { 
          return this.quantity;
        }

        /**
         * @param value {@link #quantity} (The amount of the medication given at one administration event.   Use this value when the administration is essentially an instantaneous event such as a swallowing a tablet or giving an injection.)
         */
        public MedicationAdministrationDosageComponent setQuantity(Quantity value) { 
          this.quantity = value;
          return this;
        }

        /**
         * @return {@link #rate} (Identifies the speed with which the medication was introduced into the patient. Typically the rate for an infusion e.g. 200ml in 2 hours.  May also expressed as a rate per unit of time such as 100ml per hour - the duration is then not specified, or is specified in the quantity.)
         */
        public Ratio getRate() { 
          return this.rate;
        }

        /**
         * @param value {@link #rate} (Identifies the speed with which the medication was introduced into the patient. Typically the rate for an infusion e.g. 200ml in 2 hours.  May also expressed as a rate per unit of time such as 100ml per hour - the duration is then not specified, or is specified in the quantity.)
         */
        public MedicationAdministrationDosageComponent setRate(Ratio value) { 
          this.rate = value;
          return this;
        }

        /**
         * @return {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that was administered to the patient over the specified period of time. E.g. 1000mg in 24 hours.)
         */
        public Ratio getMaxDosePerPeriod() { 
          return this.maxDosePerPeriod;
        }

        /**
         * @param value {@link #maxDosePerPeriod} (The maximum total quantity of a therapeutic substance that was administered to the patient over the specified period of time. E.g. 1000mg in 24 hours.)
         */
        public MedicationAdministrationDosageComponent setMaxDosePerPeriod(Ratio value) { 
          this.maxDosePerPeriod = value;
          return this;
        }

        protected void listChildren(List<Property> childrenList) {
          super.listChildren(childrenList);
          childrenList.add(new Property("timing[x]", "dateTime|Period", "The timing schedule for giving the medication to the patient.  This may be a single time point (using dateTime) or it may be a start and end dateTime (Period).", 0, java.lang.Integer.MAX_VALUE, timing));
          childrenList.add(new Property("asNeeded[x]", "boolean|CodeableConcept", "If set to true or if specified as a CodeableConcept, indicates that the medication is only taken when needed within the specified schedule rather than at every scheduled dose.  If a CodeableConcept is present, it indicates the pre-condition for taking the Medication.", 0, java.lang.Integer.MAX_VALUE, asNeeded));
          childrenList.add(new Property("site", "CodeableConcept", "A coded specification of the anatomic site where the medication first entered the body.  E.g. 'left arm'.", 0, java.lang.Integer.MAX_VALUE, site));
          childrenList.add(new Property("route", "CodeableConcept", "A code specifying the route or physiological path of administration of a therapeutic agent into or onto the patient.   E.g. topical, intravenous, etc.", 0, java.lang.Integer.MAX_VALUE, route));
          childrenList.add(new Property("method", "CodeableConcept", "A coded value indicating the method by which the medication was introduced into or onto the body. Most commonly used for injections.  Examples:  Slow Push; Deep IV.\n\nTerminologies used often pre-coordinate this term with the route and or form of administration.", 0, java.lang.Integer.MAX_VALUE, method));
          childrenList.add(new Property("quantity", "Quantity", "The amount of the medication given at one administration event.   Use this value when the administration is essentially an instantaneous event such as a swallowing a tablet or giving an injection.", 0, java.lang.Integer.MAX_VALUE, quantity));
          childrenList.add(new Property("rate", "Ratio", "Identifies the speed with which the medication was introduced into the patient. Typically the rate for an infusion e.g. 200ml in 2 hours.  May also expressed as a rate per unit of time such as 100ml per hour - the duration is then not specified, or is specified in the quantity.", 0, java.lang.Integer.MAX_VALUE, rate));
          childrenList.add(new Property("maxDosePerPeriod", "Ratio", "The maximum total quantity of a therapeutic substance that was administered to the patient over the specified period of time. E.g. 1000mg in 24 hours.", 0, java.lang.Integer.MAX_VALUE, maxDosePerPeriod));
        }

      public MedicationAdministrationDosageComponent copy() {
        MedicationAdministrationDosageComponent dst = new MedicationAdministrationDosageComponent();
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

    /**
     * External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    protected Enumeration<MedicationAdminStatus> status;

    /**
     * The person or animal to whom the medication was given.
     */
    protected ResourceReference patient;

    /**
     * The actual object that is the target of the reference (The person or animal to whom the medication was given.)
     */
    protected Patient patientTarget;

    /**
     * The individual who was responsible for giving the medication to the patient.
     */
    protected ResourceReference practitioner;

    /**
     * The actual object that is the target of the reference (The individual who was responsible for giving the medication to the patient.)
     */
    protected Practitioner practitionerTarget;

    /**
     * The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.
     */
    protected ResourceReference encounter;

    /**
     * The actual object that is the target of the reference (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    protected Encounter encounterTarget;

    /**
     * The original request, instruction or authority to perform the administration.
     */
    protected ResourceReference prescription;

    /**
     * The actual object that is the target of the reference (The original request, instruction or authority to perform the administration.)
     */
    protected MedicationPrescription prescriptionTarget;

    /**
     * Set this to true if the record is saying that the medication was NOT administered.
     */
    protected BooleanType wasNotGiven;

    /**
     * A code indicating why the administration was not performed.
     */
    protected List<CodeableConcept> reasonNotGiven = new ArrayList<CodeableConcept>();

    /**
     * An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.
     */
    protected Period whenGiven;

    /**
     * Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected ResourceReference medication;

    /**
     * The actual object that is the target of the reference (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    protected Medication medicationTarget;

    /**
     * The device used in administering the medication to the patient.  E.g. a particular infusion pump.
     */
    protected List<ResourceReference> device = new ArrayList<ResourceReference>();
    /**
     * The actual objects that are the target of the reference (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    protected List<Device> deviceTarget = new ArrayList<Device>();


    /**
     * Provides details of how much of the medication was administered.
     */
    protected List<MedicationAdministrationDosageComponent> dosage = new ArrayList<MedicationAdministrationDosageComponent>();

    private static final long serialVersionUID = -1505926298L;

    public MedicationAdministration() {
      super();
    }

    public MedicationAdministration(Enumeration<MedicationAdminStatus> status, ResourceReference patient, ResourceReference practitioner, ResourceReference prescription, Period whenGiven) {
      super();
      this.status = status;
      this.patient = patient;
      this.practitioner = practitioner;
      this.prescription = prescription;
      this.whenGiven = whenGiven;
    }

    /**
     * @return {@link #identifier} (External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    // syntactic sugar
    /**
     * @return {@link #identifier} (External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.)
     */
    public Identifier addIdentifier() { 
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #status} (Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.)
     */
    public Enumeration<MedicationAdminStatus> getStatus() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.)
     */
    public MedicationAdministration setStatus(Enumeration<MedicationAdminStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    public MedicationAdminStatus getStatusSimple() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    public MedicationAdministration setStatusSimple(MedicationAdminStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<MedicationAdminStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #patient} (The person or animal to whom the medication was given.)
     */
    public ResourceReference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (The person or animal to whom the medication was given.)
     */
    public MedicationAdministration setPatient(ResourceReference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} (The actual object that is the target of the reference. The person or animal to whom the medication was given.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} (The actual object that is the target of the reference. The person or animal to whom the medication was given.)
     */
    public MedicationAdministration setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #practitioner} (The individual who was responsible for giving the medication to the patient.)
     */
    public ResourceReference getPractitioner() { 
      return this.practitioner;
    }

    /**
     * @param value {@link #practitioner} (The individual who was responsible for giving the medication to the patient.)
     */
    public MedicationAdministration setPractitioner(ResourceReference value) { 
      this.practitioner = value;
      return this;
    }

    /**
     * @return {@link #practitioner} (The actual object that is the target of the reference. The individual who was responsible for giving the medication to the patient.)
     */
    public Practitioner getPractitionerTarget() { 
      return this.practitionerTarget;
    }

    /**
     * @param value {@link #practitioner} (The actual object that is the target of the reference. The individual who was responsible for giving the medication to the patient.)
     */
    public MedicationAdministration setPractitionerTarget(Practitioner value) { 
      this.practitionerTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public ResourceReference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public MedicationAdministration setEncounter(ResourceReference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The actual object that is the target of the reference. The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} (The actual object that is the target of the reference. The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public MedicationAdministration setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #prescription} (The original request, instruction or authority to perform the administration.)
     */
    public ResourceReference getPrescription() { 
      return this.prescription;
    }

    /**
     * @param value {@link #prescription} (The original request, instruction or authority to perform the administration.)
     */
    public MedicationAdministration setPrescription(ResourceReference value) { 
      this.prescription = value;
      return this;
    }

    /**
     * @return {@link #prescription} (The actual object that is the target of the reference. The original request, instruction or authority to perform the administration.)
     */
    public MedicationPrescription getPrescriptionTarget() { 
      return this.prescriptionTarget;
    }

    /**
     * @param value {@link #prescription} (The actual object that is the target of the reference. The original request, instruction or authority to perform the administration.)
     */
    public MedicationAdministration setPrescriptionTarget(MedicationPrescription value) { 
      this.prescriptionTarget = value;
      return this;
    }

    /**
     * @return {@link #wasNotGiven} (Set this to true if the record is saying that the medication was NOT administered.)
     */
    public BooleanType getWasNotGiven() { 
      return this.wasNotGiven;
    }

    /**
     * @param value {@link #wasNotGiven} (Set this to true if the record is saying that the medication was NOT administered.)
     */
    public MedicationAdministration setWasNotGiven(BooleanType value) { 
      this.wasNotGiven = value;
      return this;
    }

    /**
     * @return Set this to true if the record is saying that the medication was NOT administered.
     */
    public boolean getWasNotGivenSimple() { 
      return this.wasNotGiven == null ? false : this.wasNotGiven.getValue();
    }

    /**
     * @param value Set this to true if the record is saying that the medication was NOT administered.
     */
    public MedicationAdministration setWasNotGivenSimple(boolean value) { 
      if (value == false)
        this.wasNotGiven = null;
      else {
        if (this.wasNotGiven == null)
          this.wasNotGiven = new BooleanType();
        this.wasNotGiven.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #reasonNotGiven} (A code indicating why the administration was not performed.)
     */
    public List<CodeableConcept> getReasonNotGiven() { 
      return this.reasonNotGiven;
    }

    // syntactic sugar
    /**
     * @return {@link #reasonNotGiven} (A code indicating why the administration was not performed.)
     */
    public CodeableConcept addReasonNotGiven() { 
      CodeableConcept t = new CodeableConcept();
      this.reasonNotGiven.add(t);
      return t;
    }

    /**
     * @return {@link #whenGiven} (An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.)
     */
    public Period getWhenGiven() { 
      return this.whenGiven;
    }

    /**
     * @param value {@link #whenGiven} (An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.)
     */
    public MedicationAdministration setWhenGiven(Period value) { 
      this.whenGiven = value;
      return this;
    }

    /**
     * @return {@link #medication} (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public ResourceReference getMedication() { 
      return this.medication;
    }

    /**
     * @param value {@link #medication} (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationAdministration setMedication(ResourceReference value) { 
      this.medication = value;
      return this;
    }

    /**
     * @return {@link #medication} (The actual object that is the target of the reference. Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public Medication getMedicationTarget() { 
      return this.medicationTarget;
    }

    /**
     * @param value {@link #medication} (The actual object that is the target of the reference. Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationAdministration setMedicationTarget(Medication value) { 
      this.medicationTarget = value;
      return this;
    }

    /**
     * @return {@link #device} (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public List<ResourceReference> getDevice() { 
      return this.device;
    }

    // syntactic sugar
    /**
     * @return {@link #device} (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public ResourceReference addDevice() { 
      ResourceReference t = new ResourceReference();
      this.device.add(t);
      return t;
    }

    /**
     * @return {@link #device} (The actual objects that are the target of the reference. The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public List<Device> getDeviceTarget() { 
      return this.deviceTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #device} (Add an actual object that is the target of the reference. The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public Device addDeviceTarget() { 
      Device r = new Device();
      this.deviceTarget.add(r);
      return r;
    }

    /**
     * @return {@link #dosage} (Provides details of how much of the medication was administered.)
     */
    public List<MedicationAdministrationDosageComponent> getDosage() { 
      return this.dosage;
    }

    // syntactic sugar
    /**
     * @return {@link #dosage} (Provides details of how much of the medication was administered.)
     */
    public MedicationAdministrationDosageComponent addDosage() { 
      MedicationAdministrationDosageComponent t = new MedicationAdministrationDosageComponent();
      this.dosage.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Resource(Patient)", "The person or animal to whom the medication was given.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("practitioner", "Resource(Practitioner)", "The individual who was responsible for giving the medication to the patient.", 0, java.lang.Integer.MAX_VALUE, practitioner));
        childrenList.add(new Property("encounter", "Resource(Encounter)", "The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("prescription", "Resource(MedicationPrescription)", "The original request, instruction or authority to perform the administration.", 0, java.lang.Integer.MAX_VALUE, prescription));
        childrenList.add(new Property("wasNotGiven", "boolean", "Set this to true if the record is saying that the medication was NOT administered.", 0, java.lang.Integer.MAX_VALUE, wasNotGiven));
        childrenList.add(new Property("reasonNotGiven", "CodeableConcept", "A code indicating why the administration was not performed.", 0, java.lang.Integer.MAX_VALUE, reasonNotGiven));
        childrenList.add(new Property("whenGiven", "Period", "An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.", 0, java.lang.Integer.MAX_VALUE, whenGiven));
        childrenList.add(new Property("medication", "Resource(Medication)", "Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, medication));
        childrenList.add(new Property("device", "Resource(Device)", "The device used in administering the medication to the patient.  E.g. a particular infusion pump.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("dosage", "", "Provides details of how much of the medication was administered.", 0, java.lang.Integer.MAX_VALUE, dosage));
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
        dst.device = new ArrayList<ResourceReference>();
        for (ResourceReference i : device)
          dst.device.add(i.copy());
        dst.dosage = new ArrayList<MedicationAdministrationDosageComponent>();
        for (MedicationAdministrationDosageComponent i : dosage)
          dst.dosage.add(i.copy());
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

