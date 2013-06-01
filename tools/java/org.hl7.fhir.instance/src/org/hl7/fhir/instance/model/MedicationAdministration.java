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

// Generated on Sat, Jun 1, 2013 09:23+1000 for FHIR v0.09

import java.util.*;

/**
 * Describes the event of a patient being given a dose of a medication.  This may be as simple as swallowing a tablet or it may be a long running infusion.

Related resources tie this event to the authorizing prescription, and the specific visit between patient and health care practitioner
 */
public class MedicationAdministration extends Resource {

    public enum MedicationAdminStatus {
        active, // The administration of the medication has started and is currently in progress.
        paused, // The administration of the medication has started but is currently stopped with a firm intention of restarting.
        completed, // The administration of the medication has finished
        nullified, // The administration of the medication was recorded in error and the record should now be disregarded.
        Null; // added to help the parsers
        public static MedicationAdminStatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("active".equals(codeString))
          return active;
        if ("paused".equals(codeString))
          return paused;
        if ("completed".equals(codeString))
          return completed;
        if ("nullified".equals(codeString))
          return nullified;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case active: return "active";
            case paused: return "paused";
            case completed: return "completed";
            case nullified: return "nullified";
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
        if ("paused".equals(codeString))
          return MedicationAdminStatus.paused;
        if ("completed".equals(codeString))
          return MedicationAdminStatus.completed;
        if ("nullified".equals(codeString))
          return MedicationAdminStatus.nullified;
        throw new Exception("Unknown MedicationAdminStatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == MedicationAdminStatus.active)
        return "active";
      if (code == MedicationAdminStatus.paused)
        return "paused";
      if (code == MedicationAdminStatus.completed)
        return "completed";
      if (code == MedicationAdminStatus.nullified)
        return "nullified";
      return "?";
      }
    }

    public class MedicationAdministrationDosageComponent extends Element {
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

    /**
     * External identifier - FHIR will generate its own internal IDs (probably URLs) which do not need to be explicitly managed by the resource.  The identifier here is one that would be used by another non-FHIR system - for example an automated medication pump would provide a record each time it operated; an administration while the patient was off the ward might be made with a different system and entered after the event.  Particularly important if these records have to be updated.
     */
    private List<Identifier> externalID = new ArrayList<Identifier>();

    /**
     * Will generally be set to show that the administration has been completed.  For some long running administrations such as infusions it is possible for an administration to be started but not completed or it may be paused while some other process is under way.
     */
    private Enumeration<MedicationAdminStatus> status;

    /**
     * A link to a resource representing the person to whom the medication was given.
     */
    private ResourceReference patient;

    /**
     * This is the individual who is responsible for giving the medication to the patient.
     */
    private ResourceReference practitioner;

    /**
     * An link to a resource that identifies the particular occurrence of contact between patient and health care provider.
     */
    private ResourceReference visit;

    /**
     * A link to a resource that provides the original request, instruction and authority to perform the administration.
     */
    private ResourceReference prescription;

    /**
     * Set this to true if the record is saying that the medication was NOT administered.
     */
    private Boolean wasNotGiven;

    /**
     * A code indicating why the administration has been negated.

Use only if isNegated is set to TRUE
     */
    private List<CodeableConcept> reasonNotGiven = new ArrayList<CodeableConcept>();

    /**
     * An interval of time during which the administration takes place.  For many administrations, such as swallowing a tablet the lower and upper values of the interval will be the same.
     */
    private Period whenGiven;

    /**
     * Identifies the medication being administered. This is either a link to a resource representing the details of the medication or a simple attribute carrying a code that identifies the medication from a known list of medications.
     */
    private ResourceReference medication;

    /**
     * An identifier or a link to a resource that identifies a device used in administering the medication to the patient.
     */
    private List<ResourceReference> administrationDevice = new ArrayList<ResourceReference>();

    /**
     * Indicates how the medication is to be used by the patient
     */
    private List<MedicationAdministrationDosageComponent> dosage = new ArrayList<MedicationAdministrationDosageComponent>();

    public List<Identifier> getExternalID() { 
      return this.externalID;
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

    public ResourceReference getVisit() { 
      return this.visit;
    }

    public void setVisit(ResourceReference value) { 
      this.visit = value;
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

    public List<MedicationAdministrationDosageComponent> getDosage() { 
      return this.dosage;
    }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.MedicationAdministration;
   }


}

