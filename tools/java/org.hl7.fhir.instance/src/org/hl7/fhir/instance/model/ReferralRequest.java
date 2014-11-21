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

// Generated on Fri, Nov 21, 2014 17:07+1100 for FHIR v0.3.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
/**
 * Used to record and send details about a request for referral service or transfer of a patient to the care of another provider or provider organisation.
 */
public class ReferralRequest extends DomainResource {

    public enum Referralstatus {
        DRAFT, // A draft referral that has yet to be send.
        SENT, // The referral has been transmitted, but not yet acknowledged by the recipient.
        ACTIVE, // The referral has been acknowledged by the recipient, and is in the process of being actioned.
        CANCELLED, // The referral has been cancelled without being completed. For example it is no longer needed.
        REFUSED, // The recipient has declined to accept the referral.
        COMPLETED, // The referral has been completely actioned.
        NULL; // added to help the parsers
        public static Referralstatus fromCode(String codeString) throws Exception {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("sent".equals(codeString))
          return SENT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("refused".equals(codeString))
          return REFUSED;
        if ("completed".equals(codeString))
          return COMPLETED;
        throw new Exception("Unknown Referralstatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case SENT: return "sent";
            case ACTIVE: return "active";
            case CANCELLED: return "cancelled";
            case REFUSED: return "refused";
            case COMPLETED: return "completed";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "A draft referral that has yet to be send.";
            case SENT: return "The referral has been transmitted, but not yet acknowledged by the recipient.";
            case ACTIVE: return "The referral has been acknowledged by the recipient, and is in the process of being actioned.";
            case CANCELLED: return "The referral has been cancelled without being completed. For example it is no longer needed.";
            case REFUSED: return "The recipient has declined to accept the referral.";
            case COMPLETED: return "The referral has been completely actioned.";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "draft";
            case SENT: return "sent";
            case ACTIVE: return "active";
            case CANCELLED: return "cancelled";
            case REFUSED: return "refused";
            case COMPLETED: return "completed";
            default: return "?";
          }
        }
    }

  public static class ReferralstatusEnumFactory implements EnumFactory {
    public Enum<?> fromCode(String codeString) throws Exception {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return Referralstatus.DRAFT;
        if ("sent".equals(codeString))
          return Referralstatus.SENT;
        if ("active".equals(codeString))
          return Referralstatus.ACTIVE;
        if ("cancelled".equals(codeString))
          return Referralstatus.CANCELLED;
        if ("refused".equals(codeString))
          return Referralstatus.REFUSED;
        if ("completed".equals(codeString))
          return Referralstatus.COMPLETED;
        throw new Exception("Unknown Referralstatus code '"+codeString+"'");
        }
    public String toCode(Enum<?> code) throws Exception {
      if (code == Referralstatus.DRAFT)
        return "draft";
      if (code == Referralstatus.SENT)
        return "sent";
      if (code == Referralstatus.ACTIVE)
        return "active";
      if (code == Referralstatus.CANCELLED)
        return "cancelled";
      if (code == Referralstatus.REFUSED)
        return "refused";
      if (code == Referralstatus.COMPLETED)
        return "completed";
      return "?";
      }
    }

    /**
     * The workflow status of the referral or transfer of care request.
     */
    protected Enumeration<Referralstatus> status;

    /**
     * Business Id that uniquely identifies the referral/care transfer request instance.
     */
    protected List<Identifier> identifier = new ArrayList<Identifier>();

    /**
     * An indication of the type of referral (or where applicable the type of transfer of care) request.
     */
    protected CodeableConcept type;

    /**
     * Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.
     */
    protected CodeableConcept specialty;

    /**
     * An indication of the urgency of referral (or where applicable the type of transfer of care) request.
     */
    protected CodeableConcept priority;

    /**
     * The patient who is the subject of a referral or transfer of care request.
     */
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The patient who is the subject of a referral or transfer of care request.)
     */
    protected Patient patientTarget;

    /**
     * The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).
     */
    protected Reference requester;

    /**
     * The actual object that is the target of the reference (The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    protected Resource requesterTarget;

    /**
     * The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.
     */
    protected List<Reference> recipient = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    protected List<Resource> recipientTarget = new ArrayList<Resource>();


    /**
     * The encounter at which the request for referral or transfer of care is initiated.
     */
    protected Reference encounter;

    /**
     * The actual object that is the target of the reference (The encounter at which the request for referral or transfer of care is initiated.)
     */
    protected Encounter encounterTarget;

    /**
     * Date/DateTime the request for referral or transfer of care is sent by the author.
     */
    protected DateTimeType dateSent;

    /**
     * Description of clinical condition indicating why referral/transfer of care is requested.
     */
    protected CodeableConcept reason;

    /**
     * The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
     */
    protected StringType description;

    /**
     * The service(s) that is/are requested to be provided to the patient.
     */
    protected List<CodeableConcept> serviceRequested = new ArrayList<CodeableConcept>();

    /**
     * Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.
     */
    protected List<Reference> supportingInformation = new ArrayList<Reference>();
    /**
     * The actual objects that are the target of the reference (Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.)
     */
    protected List<Resource> supportingInformationTarget = new ArrayList<Resource>();


    /**
     * The period of time within which the services identified in the referral/transfer of care is specified or required to occur.
     */
    protected Period fulfillmentTime;

    private static final long serialVersionUID = 404401742L;

    public ReferralRequest() {
      super();
    }

    public ReferralRequest(Enumeration<Referralstatus> status) {
      super();
      this.status = status;
    }

    /**
     * @return {@link #status} (The workflow status of the referral or transfer of care request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<Referralstatus> getStatusElement() { 
      return this.status;
    }

    /**
     * @param value {@link #status} (The workflow status of the referral or transfer of care request.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public ReferralRequest setStatusElement(Enumeration<Referralstatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The workflow status of the referral or transfer of care request.
     */
    public Referralstatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The workflow status of the referral or transfer of care request.
     */
    public ReferralRequest setStatus(Referralstatus value) { 
        if (this.status == null)
          this.status = new Enumeration<Referralstatus>();
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #identifier} (Business Id that uniquely identifies the referral/care transfer request instance.)
     */
    public List<Identifier> getIdentifier() { 
      return this.identifier;
    }

    /**
     * @return {@link #identifier} (Business Id that uniquely identifies the referral/care transfer request instance.)
     */
    // syntactic sugar
    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      this.identifier.add(t);
      return t;
    }

    /**
     * @return {@link #type} (An indication of the type of referral (or where applicable the type of transfer of care) request.)
     */
    public CodeableConcept getType() { 
      return this.type;
    }

    /**
     * @param value {@link #type} (An indication of the type of referral (or where applicable the type of transfer of care) request.)
     */
    public ReferralRequest setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #specialty} (Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.)
     */
    public CodeableConcept getSpecialty() { 
      return this.specialty;
    }

    /**
     * @param value {@link #specialty} (Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.)
     */
    public ReferralRequest setSpecialty(CodeableConcept value) { 
      this.specialty = value;
      return this;
    }

    /**
     * @return {@link #priority} (An indication of the urgency of referral (or where applicable the type of transfer of care) request.)
     */
    public CodeableConcept getPriority() { 
      return this.priority;
    }

    /**
     * @param value {@link #priority} (An indication of the urgency of referral (or where applicable the type of transfer of care) request.)
     */
    public ReferralRequest setPriority(CodeableConcept value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return {@link #patient} (The patient who is the subject of a referral or transfer of care request.)
     */
    public Reference getPatient() { 
      return this.patient;
    }

    /**
     * @param value {@link #patient} (The patient who is the subject of a referral or transfer of care request.)
     */
    public ReferralRequest setPatient(Reference value) { 
      this.patient = value;
      return this;
    }

    /**
     * @return {@link #patient} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The patient who is the subject of a referral or transfer of care request.)
     */
    public Patient getPatientTarget() { 
      return this.patientTarget;
    }

    /**
     * @param value {@link #patient} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The patient who is the subject of a referral or transfer of care request.)
     */
    public ReferralRequest setPatientTarget(Patient value) { 
      this.patientTarget = value;
      return this;
    }

    /**
     * @return {@link #requester} (The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public Reference getRequester() { 
      return this.requester;
    }

    /**
     * @param value {@link #requester} (The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public ReferralRequest setRequester(Reference value) { 
      this.requester = value;
      return this;
    }

    /**
     * @return {@link #requester} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public Resource getRequesterTarget() { 
      return this.requesterTarget;
    }

    /**
     * @param value {@link #requester} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public ReferralRequest setRequesterTarget(Resource value) { 
      this.requesterTarget = value;
      return this;
    }

    /**
     * @return {@link #recipient} (The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    public List<Reference> getRecipient() { 
      return this.recipient;
    }

    /**
     * @return {@link #recipient} (The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    // syntactic sugar
    public Reference addRecipient() { //3
      Reference t = new Reference();
      this.recipient.add(t);
      return t;
    }

    /**
     * @return {@link #recipient} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    public List<Resource> getRecipientTarget() { 
      return this.recipientTarget;
    }

    /**
     * @return {@link #encounter} (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public Reference getEncounter() { 
      return this.encounter;
    }

    /**
     * @param value {@link #encounter} (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public ReferralRequest setEncounter(Reference value) { 
      this.encounter = value;
      return this;
    }

    /**
     * @return {@link #encounter} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public Encounter getEncounterTarget() { 
      return this.encounterTarget;
    }

    /**
     * @param value {@link #encounter} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public ReferralRequest setEncounterTarget(Encounter value) { 
      this.encounterTarget = value;
      return this;
    }

    /**
     * @return {@link #dateSent} (Date/DateTime the request for referral or transfer of care is sent by the author.). This is the underlying object with id, value and extensions. The accessor "getDateSent" gives direct access to the value
     */
    public DateTimeType getDateSentElement() { 
      return this.dateSent;
    }

    /**
     * @param value {@link #dateSent} (Date/DateTime the request for referral or transfer of care is sent by the author.). This is the underlying object with id, value and extensions. The accessor "getDateSent" gives direct access to the value
     */
    public ReferralRequest setDateSentElement(DateTimeType value) { 
      this.dateSent = value;
      return this;
    }

    /**
     * @return Date/DateTime the request for referral or transfer of care is sent by the author.
     */
    public DateAndTime getDateSent() { 
      return this.dateSent == null ? null : this.dateSent.getValue();
    }

    /**
     * @param value Date/DateTime the request for referral or transfer of care is sent by the author.
     */
    public ReferralRequest setDateSent(DateAndTime value) { 
      if (value == null)
        this.dateSent = null;
      else {
        if (this.dateSent == null)
          this.dateSent = new DateTimeType();
        this.dateSent.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #reason} (Description of clinical condition indicating why referral/transfer of care is requested.)
     */
    public CodeableConcept getReason() { 
      return this.reason;
    }

    /**
     * @param value {@link #reason} (Description of clinical condition indicating why referral/transfer of care is requested.)
     */
    public ReferralRequest setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #description} (The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      return this.description;
    }

    /**
     * @param value {@link #description} (The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ReferralRequest setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
     */
    public ReferralRequest setDescription(String value) { 
      if (Utilities.noString(value))
        this.description = null;
      else {
        if (this.description == null)
          this.description = new StringType();
        this.description.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #serviceRequested} (The service(s) that is/are requested to be provided to the patient.)
     */
    public List<CodeableConcept> getServiceRequested() { 
      return this.serviceRequested;
    }

    /**
     * @return {@link #serviceRequested} (The service(s) that is/are requested to be provided to the patient.)
     */
    // syntactic sugar
    public CodeableConcept addServiceRequested() { //3
      CodeableConcept t = new CodeableConcept();
      this.serviceRequested.add(t);
      return t;
    }

    /**
     * @return {@link #supportingInformation} (Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.)
     */
    public List<Reference> getSupportingInformation() { 
      return this.supportingInformation;
    }

    /**
     * @return {@link #supportingInformation} (Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.)
     */
    // syntactic sugar
    public Reference addSupportingInformation() { //3
      Reference t = new Reference();
      this.supportingInformation.add(t);
      return t;
    }

    /**
     * @return {@link #supportingInformation} (The actual objects that are the target of the reference. The reference library doesn't populate this, but you can use this to hold the resources if you resolvethemt. Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.)
     */
    public List<Resource> getSupportingInformationTarget() { 
      return this.supportingInformationTarget;
    }

    /**
     * @return {@link #fulfillmentTime} (The period of time within which the services identified in the referral/transfer of care is specified or required to occur.)
     */
    public Period getFulfillmentTime() { 
      return this.fulfillmentTime;
    }

    /**
     * @param value {@link #fulfillmentTime} (The period of time within which the services identified in the referral/transfer of care is specified or required to occur.)
     */
    public ReferralRequest setFulfillmentTime(Period value) { 
      this.fulfillmentTime = value;
      return this;
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("status", "code", "The workflow status of the referral or transfer of care request.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("identifier", "Identifier", "Business Id that uniquely identifies the referral/care transfer request instance.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("type", "CodeableConcept", "An indication of the type of referral (or where applicable the type of transfer of care) request.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("specialty", "CodeableConcept", "Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.", 0, java.lang.Integer.MAX_VALUE, specialty));
        childrenList.add(new Property("priority", "CodeableConcept", "An indication of the urgency of referral (or where applicable the type of transfer of care) request.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("patient", "Reference(Patient)", "The patient who is the subject of a referral or transfer of care request.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("requester", "Reference(Practitioner|Organization|Patient)", "The healthcare provider or provider organization who/which initaited the referral/transfer of care request. Can also be  Patient (a self referral).", 0, java.lang.Integer.MAX_VALUE, requester));
        childrenList.add(new Property("recipient", "Reference(Practitioner|Organization)", "The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.", 0, java.lang.Integer.MAX_VALUE, recipient));
        childrenList.add(new Property("encounter", "Reference(Encounter)", "The encounter at which the request for referral or transfer of care is initiated.", 0, java.lang.Integer.MAX_VALUE, encounter));
        childrenList.add(new Property("dateSent", "dateTime", "Date/DateTime the request for referral or transfer of care is sent by the author.", 0, java.lang.Integer.MAX_VALUE, dateSent));
        childrenList.add(new Property("reason", "CodeableConcept", "Description of clinical condition indicating why referral/transfer of care is requested.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("description", "string", "The reason gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("serviceRequested", "CodeableConcept", "The service(s) that is/are requested to be provided to the patient.", 0, java.lang.Integer.MAX_VALUE, serviceRequested));
        childrenList.add(new Property("supportingInformation", "Reference(Any)", "Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.", 0, java.lang.Integer.MAX_VALUE, supportingInformation));
        childrenList.add(new Property("fulfillmentTime", "Period", "The period of time within which the services identified in the referral/transfer of care is specified or required to occur.", 0, java.lang.Integer.MAX_VALUE, fulfillmentTime));
      }

      public ReferralRequest copy() {
        ReferralRequest dst = new ReferralRequest();
        copyValues(dst);
        dst.status = status == null ? null : status.copy();
        dst.identifier = new ArrayList<Identifier>();
        for (Identifier i : identifier)
          dst.identifier.add(i.copy());
        dst.type = type == null ? null : type.copy();
        dst.specialty = specialty == null ? null : specialty.copy();
        dst.priority = priority == null ? null : priority.copy();
        dst.patient = patient == null ? null : patient.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.recipient = new ArrayList<Reference>();
        for (Reference i : recipient)
          dst.recipient.add(i.copy());
        dst.encounter = encounter == null ? null : encounter.copy();
        dst.dateSent = dateSent == null ? null : dateSent.copy();
        dst.reason = reason == null ? null : reason.copy();
        dst.description = description == null ? null : description.copy();
        dst.serviceRequested = new ArrayList<CodeableConcept>();
        for (CodeableConcept i : serviceRequested)
          dst.serviceRequested.add(i.copy());
        dst.supportingInformation = new ArrayList<Reference>();
        for (Reference i : supportingInformation)
          dst.supportingInformation.add(i.copy());
        dst.fulfillmentTime = fulfillmentTime == null ? null : fulfillmentTime.copy();
        return dst;
      }

      protected ReferralRequest typedCopy() {
        return copy();
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ReferralRequest;
   }


}

