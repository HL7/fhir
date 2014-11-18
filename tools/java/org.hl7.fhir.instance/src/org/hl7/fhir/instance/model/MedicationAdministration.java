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

import org.hl7.fhir.utilities.Utilities;
/**
 * Describes the event of a patient being given a dose of a medication.  This may be as simple as swallowing a tablet or it may be a long running infusion.

Related resources tie this event to the authorizing prescription, and the specific encounter between patient and health care practitioner.
 */
public class MedicationAdministration extends DomainResource {

    public enum MedicationAdminStatus {
        INPROGRESS, // The administration has started but has not yet completed.
        ONHOLD, // Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called "suspended".
        COMPLETED, // All actions that are implied by the administration have occurred.
        ENTEREDINERROR, // The administration was entered in error and therefore nullified.
        STOPPED, // Actions implied by the administration have been permanently halted, before all of them occurred.
        NULL; // added to help the parsers
        public static MedicationAdminStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("in progress".equals(codeString))
          return INPROGRESS;
        if ("on hold".equals(codeString))
          return ONHOLD;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("entered in error".equals(codeString))
          return ENTEREDINERROR;
        if ("stopped".equals(codeString))
          return STOPPED;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case INPROGRESS: return "in progress";
            case ONHOLD: return "on hold";
            case COMPLETED: return "completed";
            case ENTEREDINERROR: return "entered in error";
            case STOPPED: return "stopped";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case INPROGRESS: return "The administration has started but has not yet completed.";
            case ONHOLD: return "Actions implied by the administration have been temporarily halted, but are expected to continue later. May also be called 'suspended'.";
            case COMPLETED: return "All actions that are implied by the administration have occurred.";
            case ENTEREDINERROR: return "The administration was entered in error and therefore nullified.";
            case STOPPED: return "Actions implied by the administration have been permanently halted, before all of them occurred.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case INPROGRESS: return "in progress";
            case ONHOLD: return "on hold";
            case COMPLETED: return "completed";
            case ENTEREDINERROR: return "entered in error";
            case STOPPED: return "stopped";
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
          return MedicationAdminStatus.INPROGRESS;
        if ("on hold".equals(codeString))
          return MedicationAdminStatus.ONHOLD;
        if ("completed".equals(codeString))
          return MedicationAdminStatus.COMPLETED;
        if ("entered in error".equals(codeString))
          return MedicationAdminStatus.ENTEREDINERROR;
        if ("stopped".equals(codeString))
          return MedicationAdminStatus.STOPPED;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationAdminStatus.INPROGRESS)
        return "in progress";
      if (code == MedicationAdminStatus.ONHOLD)
        return "on hold";
      if (code == MedicationAdminStatus.COMPLETED)
        return "completed";
      if (code == MedicationAdminStatus.ENTEREDINERROR)
        return "entered in error";
      if (code == MedicationAdminStatus.STOPPED)
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
        copyValues(dst);
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
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The person or animal to whom the medication was given.)
     */
    protected Patient patientTarget;

    /**
     * The individual who was responsible for giving the medication to the patient.
     */
    protected Reference practitioner;

    /**
     * The actual object that is the target of the reference (The individual who was responsible for giving the medication to the patient.)
     */
    protected Practitioner practitionerTarget;

    /**
     * The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    protected Encounter encounterTarget;

    /**
     * The original request, instruction or authority to perform the administration.
     */
    protected Reference prescription;

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
    protected Type effectiveTime;

    /**
     * Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    protected Reference medication;

    /**
     * The actual object that is the target of the reference (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    protected Medication medicationTarget;

    /**
     * The device used in administering the medication to the patient.  E.g. a particular infusion pump.
     */
    protected List<Reference> device = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    protected List<Device> deviceTarget = new ArrayList<Device>();


    /**
     * Provides details of how much of the medication was administered.
     */
    protected List<MedicationAdministrationDosageComponent> dosage = new ArrayList<MedicationAdministrationDosageComponent>();

    private static final long serialVersionUID = -1037637350L;

    public MedicationAdministration() {
      super();
    }

    public MedicationAdministration(Enumeration<MedicationAdminStatus> status, Reference patient, Reference practitioner, Reference prescription, Type effectiveTime) {
      super();
      this.status = status;
      this.patient = patient;
      this.practitioner = practitioner;
      this.prescription = prescription;
      this.effectiveTime = effectiveTime;
    }

    /**
     * @return {@link #identifier} (External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #status} (Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<MedicationAdminStatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public MedicationAdministration setStatusElement(Enumeration<MedicationAdminStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    public MedicationAdminStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    public MedicationAdministration setStatus(MedicationAdminStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<MedicationAdminStatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #patient} (The person or animal to whom the medication was given.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (The person or animal to whom the medication was given.)
     */
    public MedicationAdministration setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The person or animal to whom the medication was given.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The person or animal to whom the medication was given.)
     */
    public MedicationAdministration setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #practitioner} (The individual who was responsible for giving the medication to the patient.)
     */
    public Reference getPractitioner() { 
      return this.practitioner;
    }

    /**
     * @param value {@link #practitioner} (The individual who was responsible for giving the medication to the patient.)
     */
    public MedicationAdministration setPractitioner(Reference value) { 
      this.practitioner = value;
      return this;
    }

    /**
     * @return {@link #practitioner} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The individual who was responsible for giving the medication to the patient.)
     */
    public Practitioner getPractitionerTarget() { 
      return this.practitionerTarget;
    }

    /**
     * @param value {@link #practitioner} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The individual who was responsible for giving the medication to the patient.)
     */
    public MedicationAdministration setPractitionerTarget(Practitioner value) { 
      this.practitionerTarget = value;
      return this;
    }

    /**
     * @return {@link #encounter} (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public MedicationAdministration setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.)
     */
    public MedicationAdministration setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #prescription} (The original request, instruction or authority to perform the administration.)
     */
    public Reference getPrescription() { 
      return this.prescription;
    }

    /**
     * @param value {@link #prescription} (The original request, instruction or authority to perform the administration.)
     */
    public MedicationAdministration setPrescription(Reference value) { 
      this.prescription = value;
      return this;
    }

    /**
     * @return {@link #prescription} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The original request, instruction or authority to perform the administration.)
     */
    public MedicationPrescription getPrescriptionTarget() { 
      return this.prescriptionTarget;
    }

    /**
     * @param value {@link #prescription} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The original request, instruction or authority to perform the administration.)
     */
    public MedicationAdministration setPrescriptionTarget(MedicationPrescription value) { 
      this.prescriptionTarget = value;
      return this;
    }

    /**
     * @return {@link #wasNotGiven} (Set this to true if the record is saying that the medication was NOT administered.). This is the underlying object with id, value and extensions. The accessor "getWasNotGiven" gives direct access to the value
     */
    public BooleanType getWasNotGivenElement() { 
      return this.wasNotGiven;
    }

    /**
     * @param value {@link #wasNotGiven} (Set this to true if the record is saying that the medication was NOT administered.). This is the underlying object with id, value and extensions. The accessor "getWasNotGiven" gives direct access to the value
     */
    public MedicationAdministration setWasNotGivenElement(BooleanType value) { 
      this.wasNotGiven = value;
      return this;
    }

    /**
     * @return Set this to true if the record is saying that the medication was NOT administered.
     */
    public boolean getWasNotGiven() { 
      return this.wasNotGiven == null ? false : this.wasNotGiven.getValue();
    }

    /**
     * @param value Set this to true if the record is saying that the medication was NOT administered.
     */
    public MedicationAdministration setWasNotGiven(boolean value) { 
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

    /**
     * @return {@link #reasonNotGiven} (A code indicating why the administration was not performed.)
     */
    // syntactic sugar
    public CodeableConcept addReasonNotGiven() { //3
      CodeableConcept t = new CodeableConcept();
      this.reasonNotGiven.add(t);
      return t;
    }

    /**
     * @return {@link #effectiveTime} (An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.)
     */
    public Type getEffectiveTime() { 
      return this.effectiveTime;
    }

    /**
     * @param value {@link #effectiveTime} (An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.)
     */
    public MedicationAdministration setEffectiveTime(Type value) { 
      this.effectiveTime = value;
      return this;
    }

    /**
     * @return {@link #medication} (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public Reference getMedication() { 
      return this.medication;
    }

    /**
     * @param value {@link #medication} (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationAdministration setMedication(Reference value) { 
      this.medication = value;
      return this;
    }

    /**
     * @return {@link #medication} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public Medication getMedicationTarget() { 
      return this.medicationTarget;
    }

    /**
     * @param value {@link #medication} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.)
     */
    public MedicationAdministration setMedicationTarget(Medication value) { 
      this.medicationTarget = value;
      return this;
    }

    /**
     * @return {@link #device} (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public List<Reference> getDevice() { 
      return this.device;
    }

    /**
     * @return {@link #device} (The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    // syntactic sugar
    public Reference addDevice() { //3
      Reference t = new Reference();
      this.device.add(t);
      return t;
    }

    /**
     * @return {@link #device} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
     */
    public List<Device> getDeviceTarget() { 
      return this.deviceTarget;
    }

    // syntactic sugar
    /**
     * @return {@link #device} (Add an actual object that is the target of the reference. The reference library doesn't use these, but you can use this to hold the resources if you resolvethemt. The device used in administering the medication to the patient.  E.g. a particular infusion pump.)
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

    /**
     * @return {@link #dosage} (Provides details of how much of the medication was administered.)
     */
    // syntactic sugar
    public MedicationAdministrationDosageComponent addDosage() { //3
      MedicationAdministrationDosageComponent t = new MedicationAdministrationDosageComponent();
      this.dosage.add(t);
      return t;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("status", "code", "Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("patient", "Reference(Patient)", "The person or animal to whom the medication was given.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("practitioner", "Reference(Practitioner)", "The individual who was responsible for giving the medication to the patient.", 0, java.lang.Integer.MAX_VALUE, practitioner));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The visit or admission the or other contact between patient and health care provider the medication administration was performed as part of.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("prescription", "Reference(MedicationPrescription)", "The original request, instruction or authority to perform the administration.", 0, java.lang.Integer.MAX_VALUE, prescription));
        childrenList.add(new Property("wasNotGiven", "boolean", "Set this to true if the record is saying that the medication was NOT administered.", 0, java.lang.Integer.MAX_VALUE, wasNotGiven));
        childrenList.add(new Property("reasonNotGiven", "CodeableConcept", "A code indicating why the administration was not performed.", 0, java.lang.Integer.MAX_VALUE, reasonNotGiven));
        childrenList.add(new Property("effectiveTime[x]", "dateTime|Period", "An interval of time during which the administration took place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.", 0, java.lang.Integer.MAX_VALUE, effectiveTime));
        childrenList.add(new Property("medication", "Reference(Medication)", "Identifies the medication that was administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.", 0, java.lang.Integer.MAX_VALUE, medication));
        childrenList.add(new Property("device", "Reference(Device)", "The device used in administering the medication to the patient.  E.g. a particular infusion pump.", 0, java.lang.Integer.MAX_VALUE, device));
        childrenList.add(new Property("dosage", "", "Provides details of how much of the medication was administered.", 0, java.lang.Integer.MAX_VALUE, dosage));
      }

      public MedicationAdministration copy() {
        MedicationAdministration dst = new MedicationAdministration();
        copyValues(dst);
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
        dst.effectiveTime = effectiveTime == null ? null : effectiveTime.copy();
        dst.medication = medication == null ? null : medication.copy();
        dst.device = new ArrayList<Reference>();
        for (Reference i : device)
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

