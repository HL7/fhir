package org.hl7.fhir.dstu3.model;

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

// Generated on Sun, Feb 5, 2017 22:32-0500 for FHIR v1.9.0

import java.util.*;

import org.hl7.fhir.utilities.Utilities;
import ca.uhn.fhir.model.api.annotation.ResourceDef;
import ca.uhn.fhir.model.api.annotation.SearchParamDefinition;
import ca.uhn.fhir.model.api.annotation.Child;
import ca.uhn.fhir.model.api.annotation.ChildOrder;
import ca.uhn.fhir.model.api.annotation.Description;
import ca.uhn.fhir.model.api.annotation.Block;
import org.hl7.fhir.instance.model.api.*;
import org.hl7.fhir.exceptions.FHIRException;
/**
 * Used to record and send details about a request for referral service or transfer of a patient to the care of another provider or provider organization.
 */
@ResourceDef(name="ReferralRequest", profile="http://hl7.org/fhir/Profile/ReferralRequest")
public class ReferralRequest extends DomainResource {

    public enum ReferralRequestStatus {
        /**
         * A draft referral that has yet to be send.
         */
        DRAFT, 
        /**
         * The referral is complete and is ready for fulfillment.
         */
        ACTIVE, 
        /**
         * The referral has been cancelled without being completed. For example it is no longer needed.
         */
        CANCELLED, 
        /**
         * The referral has been completely actioned.
         */
        COMPLETED, 
        /**
         * This referral record should never have existed, though it's possible some degree of real-world activity or decisions may have been taken due to its existence
         */
        ENTEREDINERROR, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static ReferralRequestStatus fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return DRAFT;
        if ("active".equals(codeString))
          return ACTIVE;
        if ("cancelled".equals(codeString))
          return CANCELLED;
        if ("completed".equals(codeString))
          return COMPLETED;
        if ("entered-in-error".equals(codeString))
          return ENTEREDINERROR;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown ReferralRequestStatus code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case DRAFT: return "draft";
            case ACTIVE: return "active";
            case CANCELLED: return "cancelled";
            case COMPLETED: return "completed";
            case ENTEREDINERROR: return "entered-in-error";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case DRAFT: return "http://hl7.org/fhir/referral-request-status";
            case ACTIVE: return "http://hl7.org/fhir/referral-request-status";
            case CANCELLED: return "http://hl7.org/fhir/referral-request-status";
            case COMPLETED: return "http://hl7.org/fhir/referral-request-status";
            case ENTEREDINERROR: return "http://hl7.org/fhir/referral-request-status";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case DRAFT: return "A draft referral that has yet to be send.";
            case ACTIVE: return "The referral is complete and is ready for fulfillment.";
            case CANCELLED: return "The referral has been cancelled without being completed. For example it is no longer needed.";
            case COMPLETED: return "The referral has been completely actioned.";
            case ENTEREDINERROR: return "This referral record should never have existed, though it's possible some degree of real-world activity or decisions may have been taken due to its existence";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case DRAFT: return "Draft";
            case ACTIVE: return "Active";
            case CANCELLED: return "Cancelled";
            case COMPLETED: return "Completed";
            case ENTEREDINERROR: return "Entered in Error";
            default: return "?";
          }
        }
    }

  public static class ReferralRequestStatusEnumFactory implements EnumFactory<ReferralRequestStatus> {
    public ReferralRequestStatus fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("draft".equals(codeString))
          return ReferralRequestStatus.DRAFT;
        if ("active".equals(codeString))
          return ReferralRequestStatus.ACTIVE;
        if ("cancelled".equals(codeString))
          return ReferralRequestStatus.CANCELLED;
        if ("completed".equals(codeString))
          return ReferralRequestStatus.COMPLETED;
        if ("entered-in-error".equals(codeString))
          return ReferralRequestStatus.ENTEREDINERROR;
        throw new IllegalArgumentException("Unknown ReferralRequestStatus code '"+codeString+"'");
        }
        public Enumeration<ReferralRequestStatus> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<ReferralRequestStatus>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("draft".equals(codeString))
          return new Enumeration<ReferralRequestStatus>(this, ReferralRequestStatus.DRAFT);
        if ("active".equals(codeString))
          return new Enumeration<ReferralRequestStatus>(this, ReferralRequestStatus.ACTIVE);
        if ("cancelled".equals(codeString))
          return new Enumeration<ReferralRequestStatus>(this, ReferralRequestStatus.CANCELLED);
        if ("completed".equals(codeString))
          return new Enumeration<ReferralRequestStatus>(this, ReferralRequestStatus.COMPLETED);
        if ("entered-in-error".equals(codeString))
          return new Enumeration<ReferralRequestStatus>(this, ReferralRequestStatus.ENTEREDINERROR);
        throw new FHIRException("Unknown ReferralRequestStatus code '"+codeString+"'");
        }
    public String toCode(ReferralRequestStatus code) {
      if (code == ReferralRequestStatus.DRAFT)
        return "draft";
      if (code == ReferralRequestStatus.ACTIVE)
        return "active";
      if (code == ReferralRequestStatus.CANCELLED)
        return "cancelled";
      if (code == ReferralRequestStatus.COMPLETED)
        return "completed";
      if (code == ReferralRequestStatus.ENTEREDINERROR)
        return "entered-in-error";
      return "?";
      }
    public String toSystem(ReferralRequestStatus code) {
      return code.getSystem();
      }
    }

    public enum ReferralCategory {
        /**
         * The request is a suggestion made by someone/something that doesn't have an intention to ensure it occurs and without providing an authorization to act
         */
        PROPOSAL, 
        /**
         * The request represents an intension to ensure something occurs without providing an authorization for others to act
         */
        PLAN, 
        /**
         * The request represents a request/demand and authorization for action
         */
        ORDER, 
        /**
         * The request represents an original authorization for action
         */
        ORIGINALORDER, 
        /**
         * The request represents an automatically generated supplemental authorization for action based on a parent authorization together with initial results of the action taken against that parent authorization
         */
        REFLEXORDER, 
        /**
         * The request represents the view of an authorization instantiated by a fulfilling system representing the details of the fulfiller's intention to act upon a submitted order
         */
        FILLERORDER, 
        /**
         * An order created in fulfillment of a broader order that represents the authorization for a single activity occurrence.  E.g. The administration of a single dose of a drug.
         */
        INSTANCEORDER, 
        /**
         * The request represents a component or option for a RequestGroup that establishes timing, conditionality and/or other constraints among a set of requests.

Refer to [[[RequestGroup]]] for additional information on how this status is used
         */
        OPTION, 
        /**
         * added to help the parsers with the generic types
         */
        NULL;
        public static ReferralCategory fromCode(String codeString) throws FHIRException {
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposal".equals(codeString))
          return PROPOSAL;
        if ("plan".equals(codeString))
          return PLAN;
        if ("order".equals(codeString))
          return ORDER;
        if ("original-order".equals(codeString))
          return ORIGINALORDER;
        if ("reflex-order".equals(codeString))
          return REFLEXORDER;
        if ("filler-order".equals(codeString))
          return FILLERORDER;
        if ("instance-order".equals(codeString))
          return INSTANCEORDER;
        if ("option".equals(codeString))
          return OPTION;
        if (Configuration.isAcceptInvalidEnums())
          return null;
        else
          throw new FHIRException("Unknown ReferralCategory code '"+codeString+"'");
        }
        public String toCode() {
          switch (this) {
            case PROPOSAL: return "proposal";
            case PLAN: return "plan";
            case ORDER: return "order";
            case ORIGINALORDER: return "original-order";
            case REFLEXORDER: return "reflex-order";
            case FILLERORDER: return "filler-order";
            case INSTANCEORDER: return "instance-order";
            case OPTION: return "option";
            default: return "?";
          }
        }
        public String getSystem() {
          switch (this) {
            case PROPOSAL: return "http://hl7.org/fhir/request-intent";
            case PLAN: return "http://hl7.org/fhir/request-intent";
            case ORDER: return "http://hl7.org/fhir/request-intent";
            case ORIGINALORDER: return "http://hl7.org/fhir/request-intent";
            case REFLEXORDER: return "http://hl7.org/fhir/request-intent";
            case FILLERORDER: return "http://hl7.org/fhir/request-intent";
            case INSTANCEORDER: return "http://hl7.org/fhir/request-intent";
            case OPTION: return "http://hl7.org/fhir/request-intent";
            default: return "?";
          }
        }
        public String getDefinition() {
          switch (this) {
            case PROPOSAL: return "The request is a suggestion made by someone/something that doesn't have an intention to ensure it occurs and without providing an authorization to act";
            case PLAN: return "The request represents an intension to ensure something occurs without providing an authorization for others to act";
            case ORDER: return "The request represents a request/demand and authorization for action";
            case ORIGINALORDER: return "The request represents an original authorization for action";
            case REFLEXORDER: return "The request represents an automatically generated supplemental authorization for action based on a parent authorization together with initial results of the action taken against that parent authorization";
            case FILLERORDER: return "The request represents the view of an authorization instantiated by a fulfilling system representing the details of the fulfiller's intention to act upon a submitted order";
            case INSTANCEORDER: return "An order created in fulfillment of a broader order that represents the authorization for a single activity occurrence.  E.g. The administration of a single dose of a drug.";
            case OPTION: return "The request represents a component or option for a RequestGroup that establishes timing, conditionality and/or other constraints among a set of requests.\n\nRefer to [[[RequestGroup]]] for additional information on how this status is used";
            default: return "?";
          }
        }
        public String getDisplay() {
          switch (this) {
            case PROPOSAL: return "Proposal";
            case PLAN: return "Plan";
            case ORDER: return "Order";
            case ORIGINALORDER: return "Original Order";
            case REFLEXORDER: return "Reflex Order";
            case FILLERORDER: return "Filler Order";
            case INSTANCEORDER: return "Instance Order";
            case OPTION: return "Option";
            default: return "?";
          }
        }
    }

  public static class ReferralCategoryEnumFactory implements EnumFactory<ReferralCategory> {
    public ReferralCategory fromCode(String codeString) throws IllegalArgumentException {
      if (codeString == null || "".equals(codeString))
            if (codeString == null || "".equals(codeString))
                return null;
        if ("proposal".equals(codeString))
          return ReferralCategory.PROPOSAL;
        if ("plan".equals(codeString))
          return ReferralCategory.PLAN;
        if ("order".equals(codeString))
          return ReferralCategory.ORDER;
        if ("original-order".equals(codeString))
          return ReferralCategory.ORIGINALORDER;
        if ("reflex-order".equals(codeString))
          return ReferralCategory.REFLEXORDER;
        if ("filler-order".equals(codeString))
          return ReferralCategory.FILLERORDER;
        if ("instance-order".equals(codeString))
          return ReferralCategory.INSTANCEORDER;
        if ("option".equals(codeString))
          return ReferralCategory.OPTION;
        throw new IllegalArgumentException("Unknown ReferralCategory code '"+codeString+"'");
        }
        public Enumeration<ReferralCategory> fromType(Base code) throws FHIRException {
          if (code == null)
            return null;
          if (code.isEmpty())
            return new Enumeration<ReferralCategory>(this);
          String codeString = ((PrimitiveType) code).asStringValue();
          if (codeString == null || "".equals(codeString))
            return null;
        if ("proposal".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.PROPOSAL);
        if ("plan".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.PLAN);
        if ("order".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.ORDER);
        if ("original-order".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.ORIGINALORDER);
        if ("reflex-order".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.REFLEXORDER);
        if ("filler-order".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.FILLERORDER);
        if ("instance-order".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.INSTANCEORDER);
        if ("option".equals(codeString))
          return new Enumeration<ReferralCategory>(this, ReferralCategory.OPTION);
        throw new FHIRException("Unknown ReferralCategory code '"+codeString+"'");
        }
    public String toCode(ReferralCategory code) {
      if (code == ReferralCategory.PROPOSAL)
        return "proposal";
      if (code == ReferralCategory.PLAN)
        return "plan";
      if (code == ReferralCategory.ORDER)
        return "order";
      if (code == ReferralCategory.ORIGINALORDER)
        return "original-order";
      if (code == ReferralCategory.REFLEXORDER)
        return "reflex-order";
      if (code == ReferralCategory.FILLERORDER)
        return "filler-order";
      if (code == ReferralCategory.INSTANCEORDER)
        return "instance-order";
      if (code == ReferralCategory.OPTION)
        return "option";
      return "?";
      }
    public String toSystem(ReferralCategory code) {
      return code.getSystem();
      }
    }

    /**
     * Business identifier that uniquely identifies the referral/care transfer request instance.
     */
    @Child(name = "identifier", type = {Identifier.class}, order=0, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Business identifier", formalDefinition="Business identifier that uniquely identifies the referral/care transfer request instance." )
    protected List<Identifier> identifier;

    /**
     * Indicates any plans, proposals or orders that this request is intended to satisfy - in whole or in part.
     */
    @Child(name = "basedOn", type = {ReferralRequest.class, CarePlan.class, ProcedureRequest.class}, order=1, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Request fulfilled by this request", formalDefinition="Indicates any plans, proposals or orders that this request is intended to satisfy - in whole or in part." )
    protected List<Reference> basedOn;
    /**
     * The actual objects that are the target of the reference (Indicates any plans, proposals or orders that this request is intended to satisfy - in whole or in part.)
     */
    protected List<Resource> basedOnTarget;


    /**
     * The business identifier of the logical "grouping" request/order that this referral is a part of.
     */
    @Child(name = "groupIdentifier", type = {Identifier.class}, order=2, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Composite request this is part of", formalDefinition="The business identifier of the logical \"grouping\" request/order that this referral is a part of." )
    protected Identifier groupIdentifier;

    /**
     * The status of the authorization/intention reflected by the referral request record.
     */
    @Child(name = "status", type = {CodeType.class}, order=3, min=1, max=1, modifier=true, summary=true)
    @Description(shortDefinition="draft | active | cancelled | completed | entered-in-error", formalDefinition="The status of the authorization/intention reflected by the referral request record." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/referral-request-status")
    protected Enumeration<ReferralRequestStatus> status;

    /**
     * Distinguishes the "level" of authorization/demand implicit in this request.
     */
    @Child(name = "intent", type = {CodeType.class}, order=4, min=1, max=1, modifier=true, summary=true)
    @Description(shortDefinition="proposal | plan | order", formalDefinition="Distinguishes the \"level\" of authorization/demand implicit in this request." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/request-intent")
    protected Enumeration<ReferralCategory> intent;

    /**
     * An indication of the type of referral (or where applicable the type of transfer of care) request.
     */
    @Child(name = "type", type = {CodeableConcept.class}, order=5, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Referral/Transition of care request type", formalDefinition="An indication of the type of referral (or where applicable the type of transfer of care) request." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/referral-type")
    protected CodeableConcept type;

    /**
     * An indication of the urgency of referral (or where applicable the type of transfer of care) request.
     */
    @Child(name = "priority", type = {CodeableConcept.class}, order=6, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Urgency of referral / transfer of care request", formalDefinition="An indication of the urgency of referral (or where applicable the type of transfer of care) request." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/request-priority")
    protected CodeableConcept priority;

    /**
     * The service(s) that is/are requested to be provided to the patient.  For example: cardiac pacemaker insertion.
     */
    @Child(name = "serviceRequested", type = {CodeableConcept.class}, order=7, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Actions requested as part of the referral", formalDefinition="The service(s) that is/are requested to be provided to the patient.  For example: cardiac pacemaker insertion." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/c80-practice-codes")
    protected List<CodeableConcept> serviceRequested;

    /**
     * The patient who is the subject of a referral or transfer of care request.
     */
    @Child(name = "patient", type = {Patient.class}, order=8, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Patient referred to care or transfer", formalDefinition="The patient who is the subject of a referral or transfer of care request." )
    protected Reference patient;

    /**
     * The actual object that is the target of the reference (The patient who is the subject of a referral or transfer of care request.)
     */
    protected Patient patientTarget;

    /**
     * The encounter at which the request for referral or transfer of care is initiated.
     */
    @Child(name = "context", type = {Encounter.class, EpisodeOfCare.class}, order=9, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Originating encounter", formalDefinition="The encounter at which the request for referral or transfer of care is initiated." )
    protected Reference context;

    /**
     * The actual object that is the target of the reference (The encounter at which the request for referral or transfer of care is initiated.)
     */
    protected Resource contextTarget;

    /**
     * The period of time within which the services identified in the referral/transfer of care is specified or required to occur.
     */
    @Child(name = "fulfillmentTime", type = {Period.class}, order=10, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Requested service(s) fulfillment time", formalDefinition="The period of time within which the services identified in the referral/transfer of care is specified or required to occur." )
    protected Period fulfillmentTime;

    /**
     * Date/DateTime of creation for draft requests and date of activation for active requests.
     */
    @Child(name = "authoredOn", type = {DateTimeType.class}, order=11, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Date of creation/activation", formalDefinition="Date/DateTime of creation for draft requests and date of activation for active requests." )
    protected DateTimeType authoredOn;

    /**
     * The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).
     */
    @Child(name = "requester", type = {Practitioner.class, Organization.class, Patient.class}, order=12, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Requester of referral / transfer of care", formalDefinition="The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral)." )
    protected Reference requester;

    /**
     * The actual object that is the target of the reference (The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    protected Resource requesterTarget;

    /**
     * Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.  For example: Cardiology Gastroenterology Diabetology.
     */
    @Child(name = "specialty", type = {CodeableConcept.class}, order=13, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="The clinical specialty (discipline) that the referral is requested for", formalDefinition="Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.  For example: Cardiology Gastroenterology Diabetology." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/practitioner-specialty")
    protected CodeableConcept specialty;

    /**
     * The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.
     */
    @Child(name = "recipient", type = {Practitioner.class, Organization.class}, order=14, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=true)
    @Description(shortDefinition="Receiver of referral / transfer of care request", formalDefinition="The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request." )
    protected List<Reference> recipient;
    /**
     * The actual objects that are the target of the reference (The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    protected List<Resource> recipientTarget;


    /**
     * Description of clinical condition indicating why referral/transfer of care is requested.  For example:  Pathological Anomalies, Disabled (physical or mental),  Behavioral Management.
     */
    @Child(name = "reason", type = {CodeableConcept.class}, order=15, min=0, max=1, modifier=false, summary=true)
    @Description(shortDefinition="Reason for referral / transfer of care request", formalDefinition="Description of clinical condition indicating why referral/transfer of care is requested.  For example:  Pathological Anomalies, Disabled (physical or mental),  Behavioral Management." )
    @ca.uhn.fhir.model.api.annotation.Binding(valueSet="http://hl7.org/fhir/ValueSet/clinical-findings")
    protected CodeableConcept reason;

    /**
     * The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
     */
    @Child(name = "description", type = {StringType.class}, order=16, min=0, max=1, modifier=false, summary=false)
    @Description(shortDefinition="A textual description of the referral", formalDefinition="The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary." )
    protected StringType description;

    /**
     * Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.  For example: Presenting problems/chief complaints Medical History Family History Alerts Allergy/Intolerance and Adverse Reactions Medications Observations/Assessments (may include cognitive and fundtional assessments) Diagnostic Reports Care Plan.
     */
    @Child(name = "supportingInfo", type = {Reference.class}, order=17, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Additonal information to support referral or transfer of care request", formalDefinition="Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.  For example: Presenting problems/chief complaints Medical History Family History Alerts Allergy/Intolerance and Adverse Reactions Medications Observations/Assessments (may include cognitive and fundtional assessments) Diagnostic Reports Care Plan." )
    protected List<Reference> supportingInfo;
    /**
     * The actual objects that are the target of the reference (Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.  For example: Presenting problems/chief complaints Medical History Family History Alerts Allergy/Intolerance and Adverse Reactions Medications Observations/Assessments (may include cognitive and fundtional assessments) Diagnostic Reports Care Plan.)
     */
    protected List<Resource> supportingInfoTarget;


    /**
     * Comments made about the referral request by any of the participants.
     */
    @Child(name = "note", type = {Annotation.class}, order=18, min=0, max=Child.MAX_UNLIMITED, modifier=false, summary=false)
    @Description(shortDefinition="Comments made about referral request", formalDefinition="Comments made about the referral request by any of the participants." )
    protected List<Annotation> note;

    private static final long serialVersionUID = 919221985L;

  /**
   * Constructor
   */
    public ReferralRequest() {
      super();
    }

  /**
   * Constructor
   */
    public ReferralRequest(Enumeration<ReferralRequestStatus> status, Enumeration<ReferralCategory> intent) {
      super();
      this.status = status;
      this.intent = intent;
    }

    /**
     * @return {@link #identifier} (Business identifier that uniquely identifies the referral/care transfer request instance.)
     */
    public List<Identifier> getIdentifier() { 
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      return this.identifier;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setIdentifier(List<Identifier> theIdentifier) { 
      this.identifier = theIdentifier;
      return this;
    }

    public boolean hasIdentifier() { 
      if (this.identifier == null)
        return false;
      for (Identifier item : this.identifier)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Identifier addIdentifier() { //3
      Identifier t = new Identifier();
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return t;
    }

    public ReferralRequest addIdentifier(Identifier t) { //3
      if (t == null)
        return this;
      if (this.identifier == null)
        this.identifier = new ArrayList<Identifier>();
      this.identifier.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #identifier}, creating it if it does not already exist
     */
    public Identifier getIdentifierFirstRep() { 
      if (getIdentifier().isEmpty()) {
        addIdentifier();
      }
      return getIdentifier().get(0);
    }

    /**
     * @return {@link #basedOn} (Indicates any plans, proposals or orders that this request is intended to satisfy - in whole or in part.)
     */
    public List<Reference> getBasedOn() { 
      if (this.basedOn == null)
        this.basedOn = new ArrayList<Reference>();
      return this.basedOn;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setBasedOn(List<Reference> theBasedOn) { 
      this.basedOn = theBasedOn;
      return this;
    }

    public boolean hasBasedOn() { 
      if (this.basedOn == null)
        return false;
      for (Reference item : this.basedOn)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addBasedOn() { //3
      Reference t = new Reference();
      if (this.basedOn == null)
        this.basedOn = new ArrayList<Reference>();
      this.basedOn.add(t);
      return t;
    }

    public ReferralRequest addBasedOn(Reference t) { //3
      if (t == null)
        return this;
      if (this.basedOn == null)
        this.basedOn = new ArrayList<Reference>();
      this.basedOn.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #basedOn}, creating it if it does not already exist
     */
    public Reference getBasedOnFirstRep() { 
      if (getBasedOn().isEmpty()) {
        addBasedOn();
      }
      return getBasedOn().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getBasedOnTarget() { 
      if (this.basedOnTarget == null)
        this.basedOnTarget = new ArrayList<Resource>();
      return this.basedOnTarget;
    }

    /**
     * @return {@link #groupIdentifier} (The business identifier of the logical "grouping" request/order that this referral is a part of.)
     */
    public Identifier getGroupIdentifier() { 
      if (this.groupIdentifier == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.groupIdentifier");
        else if (Configuration.doAutoCreate())
          this.groupIdentifier = new Identifier(); // cc
      return this.groupIdentifier;
    }

    public boolean hasGroupIdentifier() { 
      return this.groupIdentifier != null && !this.groupIdentifier.isEmpty();
    }

    /**
     * @param value {@link #groupIdentifier} (The business identifier of the logical "grouping" request/order that this referral is a part of.)
     */
    public ReferralRequest setGroupIdentifier(Identifier value) { 
      this.groupIdentifier = value;
      return this;
    }

    /**
     * @return {@link #status} (The status of the authorization/intention reflected by the referral request record.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public Enumeration<ReferralRequestStatus> getStatusElement() { 
      if (this.status == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.status");
        else if (Configuration.doAutoCreate())
          this.status = new Enumeration<ReferralRequestStatus>(new ReferralRequestStatusEnumFactory()); // bb
      return this.status;
    }

    public boolean hasStatusElement() { 
      return this.status != null && !this.status.isEmpty();
    }

    public boolean hasStatus() { 
      return this.status != null && !this.status.isEmpty();
    }

    /**
     * @param value {@link #status} (The status of the authorization/intention reflected by the referral request record.). This is the underlying object with id, value and extensions. The accessor "getStatus" gives direct access to the value
     */
    public ReferralRequest setStatusElement(Enumeration<ReferralRequestStatus> value) { 
      this.status = value;
      return this;
    }

    /**
     * @return The status of the authorization/intention reflected by the referral request record.
     */
    public ReferralRequestStatus getStatus() { 
      return this.status == null ? null : this.status.getValue();
    }

    /**
     * @param value The status of the authorization/intention reflected by the referral request record.
     */
    public ReferralRequest setStatus(ReferralRequestStatus value) { 
        if (this.status == null)
          this.status = new Enumeration<ReferralRequestStatus>(new ReferralRequestStatusEnumFactory());
        this.status.setValue(value);
      return this;
    }

    /**
     * @return {@link #intent} (Distinguishes the "level" of authorization/demand implicit in this request.). This is the underlying object with id, value and extensions. The accessor "getIntent" gives direct access to the value
     */
    public Enumeration<ReferralCategory> getIntentElement() { 
      if (this.intent == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.intent");
        else if (Configuration.doAutoCreate())
          this.intent = new Enumeration<ReferralCategory>(new ReferralCategoryEnumFactory()); // bb
      return this.intent;
    }

    public boolean hasIntentElement() { 
      return this.intent != null && !this.intent.isEmpty();
    }

    public boolean hasIntent() { 
      return this.intent != null && !this.intent.isEmpty();
    }

    /**
     * @param value {@link #intent} (Distinguishes the "level" of authorization/demand implicit in this request.). This is the underlying object with id, value and extensions. The accessor "getIntent" gives direct access to the value
     */
    public ReferralRequest setIntentElement(Enumeration<ReferralCategory> value) { 
      this.intent = value;
      return this;
    }

    /**
     * @return Distinguishes the "level" of authorization/demand implicit in this request.
     */
    public ReferralCategory getIntent() { 
      return this.intent == null ? null : this.intent.getValue();
    }

    /**
     * @param value Distinguishes the "level" of authorization/demand implicit in this request.
     */
    public ReferralRequest setIntent(ReferralCategory value) { 
        if (this.intent == null)
          this.intent = new Enumeration<ReferralCategory>(new ReferralCategoryEnumFactory());
        this.intent.setValue(value);
      return this;
    }

    /**
     * @return {@link #type} (An indication of the type of referral (or where applicable the type of transfer of care) request.)
     */
    public CodeableConcept getType() { 
      if (this.type == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.type");
        else if (Configuration.doAutoCreate())
          this.type = new CodeableConcept(); // cc
      return this.type;
    }

    public boolean hasType() { 
      return this.type != null && !this.type.isEmpty();
    }

    /**
     * @param value {@link #type} (An indication of the type of referral (or where applicable the type of transfer of care) request.)
     */
    public ReferralRequest setType(CodeableConcept value) { 
      this.type = value;
      return this;
    }

    /**
     * @return {@link #priority} (An indication of the urgency of referral (or where applicable the type of transfer of care) request.)
     */
    public CodeableConcept getPriority() { 
      if (this.priority == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.priority");
        else if (Configuration.doAutoCreate())
          this.priority = new CodeableConcept(); // cc
      return this.priority;
    }

    public boolean hasPriority() { 
      return this.priority != null && !this.priority.isEmpty();
    }

    /**
     * @param value {@link #priority} (An indication of the urgency of referral (or where applicable the type of transfer of care) request.)
     */
    public ReferralRequest setPriority(CodeableConcept value) { 
      this.priority = value;
      return this;
    }

    /**
     * @return {@link #serviceRequested} (The service(s) that is/are requested to be provided to the patient.  For example: cardiac pacemaker insertion.)
     */
    public List<CodeableConcept> getServiceRequested() { 
      if (this.serviceRequested == null)
        this.serviceRequested = new ArrayList<CodeableConcept>();
      return this.serviceRequested;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setServiceRequested(List<CodeableConcept> theServiceRequested) { 
      this.serviceRequested = theServiceRequested;
      return this;
    }

    public boolean hasServiceRequested() { 
      if (this.serviceRequested == null)
        return false;
      for (CodeableConcept item : this.serviceRequested)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public CodeableConcept addServiceRequested() { //3
      CodeableConcept t = new CodeableConcept();
      if (this.serviceRequested == null)
        this.serviceRequested = new ArrayList<CodeableConcept>();
      this.serviceRequested.add(t);
      return t;
    }

    public ReferralRequest addServiceRequested(CodeableConcept t) { //3
      if (t == null)
        return this;
      if (this.serviceRequested == null)
        this.serviceRequested = new ArrayList<CodeableConcept>();
      this.serviceRequested.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #serviceRequested}, creating it if it does not already exist
     */
    public CodeableConcept getServiceRequestedFirstRep() { 
      if (getServiceRequested().isEmpty()) {
        addServiceRequested();
      }
      return getServiceRequested().get(0);
    }

    /**
     * @return {@link #patient} (The patient who is the subject of a referral or transfer of care request.)
     */
    public Reference getPatient() { 
      if (this.patient == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.patient");
        else if (Configuration.doAutoCreate())
          this.patient = new Reference(); // cc
      return this.patient;
    }

    public boolean hasPatient() { 
      return this.patient != null && !this.patient.isEmpty();
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
      if (this.patientTarget == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.patient");
        else if (Configuration.doAutoCreate())
          this.patientTarget = new Patient(); // aa
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
     * @return {@link #context} (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public Reference getContext() { 
      if (this.context == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.context");
        else if (Configuration.doAutoCreate())
          this.context = new Reference(); // cc
      return this.context;
    }

    public boolean hasContext() { 
      return this.context != null && !this.context.isEmpty();
    }

    /**
     * @param value {@link #context} (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public ReferralRequest setContext(Reference value) { 
      this.context = value;
      return this;
    }

    /**
     * @return {@link #context} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public Resource getContextTarget() { 
      return this.contextTarget;
    }

    /**
     * @param value {@link #context} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The encounter at which the request for referral or transfer of care is initiated.)
     */
    public ReferralRequest setContextTarget(Resource value) { 
      this.contextTarget = value;
      return this;
    }

    /**
     * @return {@link #fulfillmentTime} (The period of time within which the services identified in the referral/transfer of care is specified or required to occur.)
     */
    public Period getFulfillmentTime() { 
      if (this.fulfillmentTime == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.fulfillmentTime");
        else if (Configuration.doAutoCreate())
          this.fulfillmentTime = new Period(); // cc
      return this.fulfillmentTime;
    }

    public boolean hasFulfillmentTime() { 
      return this.fulfillmentTime != null && !this.fulfillmentTime.isEmpty();
    }

    /**
     * @param value {@link #fulfillmentTime} (The period of time within which the services identified in the referral/transfer of care is specified or required to occur.)
     */
    public ReferralRequest setFulfillmentTime(Period value) { 
      this.fulfillmentTime = value;
      return this;
    }

    /**
     * @return {@link #authoredOn} (Date/DateTime of creation for draft requests and date of activation for active requests.). This is the underlying object with id, value and extensions. The accessor "getAuthoredOn" gives direct access to the value
     */
    public DateTimeType getAuthoredOnElement() { 
      if (this.authoredOn == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.authoredOn");
        else if (Configuration.doAutoCreate())
          this.authoredOn = new DateTimeType(); // bb
      return this.authoredOn;
    }

    public boolean hasAuthoredOnElement() { 
      return this.authoredOn != null && !this.authoredOn.isEmpty();
    }

    public boolean hasAuthoredOn() { 
      return this.authoredOn != null && !this.authoredOn.isEmpty();
    }

    /**
     * @param value {@link #authoredOn} (Date/DateTime of creation for draft requests and date of activation for active requests.). This is the underlying object with id, value and extensions. The accessor "getAuthoredOn" gives direct access to the value
     */
    public ReferralRequest setAuthoredOnElement(DateTimeType value) { 
      this.authoredOn = value;
      return this;
    }

    /**
     * @return Date/DateTime of creation for draft requests and date of activation for active requests.
     */
    public Date getAuthoredOn() { 
      return this.authoredOn == null ? null : this.authoredOn.getValue();
    }

    /**
     * @param value Date/DateTime of creation for draft requests and date of activation for active requests.
     */
    public ReferralRequest setAuthoredOn(Date value) { 
      if (value == null)
        this.authoredOn = null;
      else {
        if (this.authoredOn == null)
          this.authoredOn = new DateTimeType();
        this.authoredOn.setValue(value);
      }
      return this;
    }

    /**
     * @return {@link #requester} (The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public Reference getRequester() { 
      if (this.requester == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.requester");
        else if (Configuration.doAutoCreate())
          this.requester = new Reference(); // cc
      return this.requester;
    }

    public boolean hasRequester() { 
      return this.requester != null && !this.requester.isEmpty();
    }

    /**
     * @param value {@link #requester} (The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public ReferralRequest setRequester(Reference value) { 
      this.requester = value;
      return this;
    }

    /**
     * @return {@link #requester} The actual object that is the target of the reference. The reference library doesn't populate this, but you can use it to hold the resource if you resolve it. (The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public Resource getRequesterTarget() { 
      return this.requesterTarget;
    }

    /**
     * @param value {@link #requester} The actual object that is the target of the reference. The reference library doesn't use these, but you can use it to hold the resource if you resolve it. (The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).)
     */
    public ReferralRequest setRequesterTarget(Resource value) { 
      this.requesterTarget = value;
      return this;
    }

    /**
     * @return {@link #specialty} (Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.  For example: Cardiology Gastroenterology Diabetology.)
     */
    public CodeableConcept getSpecialty() { 
      if (this.specialty == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.specialty");
        else if (Configuration.doAutoCreate())
          this.specialty = new CodeableConcept(); // cc
      return this.specialty;
    }

    public boolean hasSpecialty() { 
      return this.specialty != null && !this.specialty.isEmpty();
    }

    /**
     * @param value {@link #specialty} (Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.  For example: Cardiology Gastroenterology Diabetology.)
     */
    public ReferralRequest setSpecialty(CodeableConcept value) { 
      this.specialty = value;
      return this;
    }

    /**
     * @return {@link #recipient} (The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.)
     */
    public List<Reference> getRecipient() { 
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      return this.recipient;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setRecipient(List<Reference> theRecipient) { 
      this.recipient = theRecipient;
      return this;
    }

    public boolean hasRecipient() { 
      if (this.recipient == null)
        return false;
      for (Reference item : this.recipient)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addRecipient() { //3
      Reference t = new Reference();
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      this.recipient.add(t);
      return t;
    }

    public ReferralRequest addRecipient(Reference t) { //3
      if (t == null)
        return this;
      if (this.recipient == null)
        this.recipient = new ArrayList<Reference>();
      this.recipient.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #recipient}, creating it if it does not already exist
     */
    public Reference getRecipientFirstRep() { 
      if (getRecipient().isEmpty()) {
        addRecipient();
      }
      return getRecipient().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getRecipientTarget() { 
      if (this.recipientTarget == null)
        this.recipientTarget = new ArrayList<Resource>();
      return this.recipientTarget;
    }

    /**
     * @return {@link #reason} (Description of clinical condition indicating why referral/transfer of care is requested.  For example:  Pathological Anomalies, Disabled (physical or mental),  Behavioral Management.)
     */
    public CodeableConcept getReason() { 
      if (this.reason == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.reason");
        else if (Configuration.doAutoCreate())
          this.reason = new CodeableConcept(); // cc
      return this.reason;
    }

    public boolean hasReason() { 
      return this.reason != null && !this.reason.isEmpty();
    }

    /**
     * @param value {@link #reason} (Description of clinical condition indicating why referral/transfer of care is requested.  For example:  Pathological Anomalies, Disabled (physical or mental),  Behavioral Management.)
     */
    public ReferralRequest setReason(CodeableConcept value) { 
      this.reason = value;
      return this;
    }

    /**
     * @return {@link #description} (The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public StringType getDescriptionElement() { 
      if (this.description == null)
        if (Configuration.errorOnAutoCreate())
          throw new Error("Attempt to auto-create ReferralRequest.description");
        else if (Configuration.doAutoCreate())
          this.description = new StringType(); // bb
      return this.description;
    }

    public boolean hasDescriptionElement() { 
      return this.description != null && !this.description.isEmpty();
    }

    public boolean hasDescription() { 
      return this.description != null && !this.description.isEmpty();
    }

    /**
     * @param value {@link #description} (The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.). This is the underlying object with id, value and extensions. The accessor "getDescription" gives direct access to the value
     */
    public ReferralRequest setDescriptionElement(StringType value) { 
      this.description = value;
      return this;
    }

    /**
     * @return The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
     */
    public String getDescription() { 
      return this.description == null ? null : this.description.getValue();
    }

    /**
     * @param value The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.
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
     * @return {@link #supportingInfo} (Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.  For example: Presenting problems/chief complaints Medical History Family History Alerts Allergy/Intolerance and Adverse Reactions Medications Observations/Assessments (may include cognitive and fundtional assessments) Diagnostic Reports Care Plan.)
     */
    public List<Reference> getSupportingInfo() { 
      if (this.supportingInfo == null)
        this.supportingInfo = new ArrayList<Reference>();
      return this.supportingInfo;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setSupportingInfo(List<Reference> theSupportingInfo) { 
      this.supportingInfo = theSupportingInfo;
      return this;
    }

    public boolean hasSupportingInfo() { 
      if (this.supportingInfo == null)
        return false;
      for (Reference item : this.supportingInfo)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Reference addSupportingInfo() { //3
      Reference t = new Reference();
      if (this.supportingInfo == null)
        this.supportingInfo = new ArrayList<Reference>();
      this.supportingInfo.add(t);
      return t;
    }

    public ReferralRequest addSupportingInfo(Reference t) { //3
      if (t == null)
        return this;
      if (this.supportingInfo == null)
        this.supportingInfo = new ArrayList<Reference>();
      this.supportingInfo.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #supportingInfo}, creating it if it does not already exist
     */
    public Reference getSupportingInfoFirstRep() { 
      if (getSupportingInfo().isEmpty()) {
        addSupportingInfo();
      }
      return getSupportingInfo().get(0);
    }

    /**
     * @deprecated Use Reference#setResource(IBaseResource) instead
     */
    @Deprecated
    public List<Resource> getSupportingInfoTarget() { 
      if (this.supportingInfoTarget == null)
        this.supportingInfoTarget = new ArrayList<Resource>();
      return this.supportingInfoTarget;
    }

    /**
     * @return {@link #note} (Comments made about the referral request by any of the participants.)
     */
    public List<Annotation> getNote() { 
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      return this.note;
    }

    /**
     * @return Returns a reference to <code>this</code> for easy method chaining
     */
    public ReferralRequest setNote(List<Annotation> theNote) { 
      this.note = theNote;
      return this;
    }

    public boolean hasNote() { 
      if (this.note == null)
        return false;
      for (Annotation item : this.note)
        if (!item.isEmpty())
          return true;
      return false;
    }

    public Annotation addNote() { //3
      Annotation t = new Annotation();
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return t;
    }

    public ReferralRequest addNote(Annotation t) { //3
      if (t == null)
        return this;
      if (this.note == null)
        this.note = new ArrayList<Annotation>();
      this.note.add(t);
      return this;
    }

    /**
     * @return The first repetition of repeating field {@link #note}, creating it if it does not already exist
     */
    public Annotation getNoteFirstRep() { 
      if (getNote().isEmpty()) {
        addNote();
      }
      return getNote().get(0);
    }

      protected void listChildren(List<Property> childrenList) {
        super.listChildren(childrenList);
        childrenList.add(new Property("identifier", "Identifier", "Business identifier that uniquely identifies the referral/care transfer request instance.", 0, java.lang.Integer.MAX_VALUE, identifier));
        childrenList.add(new Property("basedOn", "Reference(ReferralRequest|CarePlan|ProcedureRequest)", "Indicates any plans, proposals or orders that this request is intended to satisfy - in whole or in part.", 0, java.lang.Integer.MAX_VALUE, basedOn));
        childrenList.add(new Property("groupIdentifier", "Identifier", "The business identifier of the logical \"grouping\" request/order that this referral is a part of.", 0, java.lang.Integer.MAX_VALUE, groupIdentifier));
        childrenList.add(new Property("status", "code", "The status of the authorization/intention reflected by the referral request record.", 0, java.lang.Integer.MAX_VALUE, status));
        childrenList.add(new Property("intent", "code", "Distinguishes the \"level\" of authorization/demand implicit in this request.", 0, java.lang.Integer.MAX_VALUE, intent));
        childrenList.add(new Property("type", "CodeableConcept", "An indication of the type of referral (or where applicable the type of transfer of care) request.", 0, java.lang.Integer.MAX_VALUE, type));
        childrenList.add(new Property("priority", "CodeableConcept", "An indication of the urgency of referral (or where applicable the type of transfer of care) request.", 0, java.lang.Integer.MAX_VALUE, priority));
        childrenList.add(new Property("serviceRequested", "CodeableConcept", "The service(s) that is/are requested to be provided to the patient.  For example: cardiac pacemaker insertion.", 0, java.lang.Integer.MAX_VALUE, serviceRequested));
        childrenList.add(new Property("patient", "Reference(Patient)", "The patient who is the subject of a referral or transfer of care request.", 0, java.lang.Integer.MAX_VALUE, patient));
        childrenList.add(new Property("context", "Reference(Encounter|EpisodeOfCare)", "The encounter at which the request for referral or transfer of care is initiated.", 0, java.lang.Integer.MAX_VALUE, context));
        childrenList.add(new Property("fulfillmentTime", "Period", "The period of time within which the services identified in the referral/transfer of care is specified or required to occur.", 0, java.lang.Integer.MAX_VALUE, fulfillmentTime));
        childrenList.add(new Property("authoredOn", "dateTime", "Date/DateTime of creation for draft requests and date of activation for active requests.", 0, java.lang.Integer.MAX_VALUE, authoredOn));
        childrenList.add(new Property("requester", "Reference(Practitioner|Organization|Patient)", "The healthcare provider or provider organization who/which initiated the referral/transfer of care request. Can also be  Patient (a self referral).", 0, java.lang.Integer.MAX_VALUE, requester));
        childrenList.add(new Property("specialty", "CodeableConcept", "Indication of the clinical domain or discipline to which the referral or transfer of care request is sent.  For example: Cardiology Gastroenterology Diabetology.", 0, java.lang.Integer.MAX_VALUE, specialty));
        childrenList.add(new Property("recipient", "Reference(Practitioner|Organization)", "The healthcare provider(s) or provider organization(s) who/which is to receive the referral/transfer of care request.", 0, java.lang.Integer.MAX_VALUE, recipient));
        childrenList.add(new Property("reason", "CodeableConcept", "Description of clinical condition indicating why referral/transfer of care is requested.  For example:  Pathological Anomalies, Disabled (physical or mental),  Behavioral Management.", 0, java.lang.Integer.MAX_VALUE, reason));
        childrenList.add(new Property("description", "string", "The reason element gives a short description of why the referral is being made, the description expands on this to support a more complete clinical summary.", 0, java.lang.Integer.MAX_VALUE, description));
        childrenList.add(new Property("supportingInfo", "Reference(Any)", "Any additional (administrative, financial or clinical) information required to support request for referral or transfer of care.  For example: Presenting problems/chief complaints Medical History Family History Alerts Allergy/Intolerance and Adverse Reactions Medications Observations/Assessments (may include cognitive and fundtional assessments) Diagnostic Reports Care Plan.", 0, java.lang.Integer.MAX_VALUE, supportingInfo));
        childrenList.add(new Property("note", "Annotation", "Comments made about the referral request by any of the participants.", 0, java.lang.Integer.MAX_VALUE, note));
      }

      @Override
      public Base[] getProperty(int hash, String name, boolean checkValid) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return this.identifier == null ? new Base[0] : this.identifier.toArray(new Base[this.identifier.size()]); // Identifier
        case -332612366: /*basedOn*/ return this.basedOn == null ? new Base[0] : this.basedOn.toArray(new Base[this.basedOn.size()]); // Reference
        case -445338488: /*groupIdentifier*/ return this.groupIdentifier == null ? new Base[0] : new Base[] {this.groupIdentifier}; // Identifier
        case -892481550: /*status*/ return this.status == null ? new Base[0] : new Base[] {this.status}; // Enumeration<ReferralRequestStatus>
        case -1183762788: /*intent*/ return this.intent == null ? new Base[0] : new Base[] {this.intent}; // Enumeration<ReferralCategory>
        case 3575610: /*type*/ return this.type == null ? new Base[0] : new Base[] {this.type}; // CodeableConcept
        case -1165461084: /*priority*/ return this.priority == null ? new Base[0] : new Base[] {this.priority}; // CodeableConcept
        case 190229561: /*serviceRequested*/ return this.serviceRequested == null ? new Base[0] : this.serviceRequested.toArray(new Base[this.serviceRequested.size()]); // CodeableConcept
        case -791418107: /*patient*/ return this.patient == null ? new Base[0] : new Base[] {this.patient}; // Reference
        case 951530927: /*context*/ return this.context == null ? new Base[0] : new Base[] {this.context}; // Reference
        case 1098185163: /*fulfillmentTime*/ return this.fulfillmentTime == null ? new Base[0] : new Base[] {this.fulfillmentTime}; // Period
        case -1500852503: /*authoredOn*/ return this.authoredOn == null ? new Base[0] : new Base[] {this.authoredOn}; // DateTimeType
        case 693933948: /*requester*/ return this.requester == null ? new Base[0] : new Base[] {this.requester}; // Reference
        case -1694759682: /*specialty*/ return this.specialty == null ? new Base[0] : new Base[] {this.specialty}; // CodeableConcept
        case 820081177: /*recipient*/ return this.recipient == null ? new Base[0] : this.recipient.toArray(new Base[this.recipient.size()]); // Reference
        case -934964668: /*reason*/ return this.reason == null ? new Base[0] : new Base[] {this.reason}; // CodeableConcept
        case -1724546052: /*description*/ return this.description == null ? new Base[0] : new Base[] {this.description}; // StringType
        case 1922406657: /*supportingInfo*/ return this.supportingInfo == null ? new Base[0] : this.supportingInfo.toArray(new Base[this.supportingInfo.size()]); // Reference
        case 3387378: /*note*/ return this.note == null ? new Base[0] : this.note.toArray(new Base[this.note.size()]); // Annotation
        default: return super.getProperty(hash, name, checkValid);
        }

      }

      @Override
      public Base setProperty(int hash, String name, Base value) throws FHIRException {
        switch (hash) {
        case -1618432855: // identifier
          this.getIdentifier().add(castToIdentifier(value)); // Identifier
          return value;
        case -332612366: // basedOn
          this.getBasedOn().add(castToReference(value)); // Reference
          return value;
        case -445338488: // groupIdentifier
          this.groupIdentifier = castToIdentifier(value); // Identifier
          return value;
        case -892481550: // status
          value = new ReferralRequestStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<ReferralRequestStatus>
          return value;
        case -1183762788: // intent
          value = new ReferralCategoryEnumFactory().fromType(castToCode(value));
          this.intent = (Enumeration) value; // Enumeration<ReferralCategory>
          return value;
        case 3575610: // type
          this.type = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1165461084: // priority
          this.priority = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 190229561: // serviceRequested
          this.getServiceRequested().add(castToCodeableConcept(value)); // CodeableConcept
          return value;
        case -791418107: // patient
          this.patient = castToReference(value); // Reference
          return value;
        case 951530927: // context
          this.context = castToReference(value); // Reference
          return value;
        case 1098185163: // fulfillmentTime
          this.fulfillmentTime = castToPeriod(value); // Period
          return value;
        case -1500852503: // authoredOn
          this.authoredOn = castToDateTime(value); // DateTimeType
          return value;
        case 693933948: // requester
          this.requester = castToReference(value); // Reference
          return value;
        case -1694759682: // specialty
          this.specialty = castToCodeableConcept(value); // CodeableConcept
          return value;
        case 820081177: // recipient
          this.getRecipient().add(castToReference(value)); // Reference
          return value;
        case -934964668: // reason
          this.reason = castToCodeableConcept(value); // CodeableConcept
          return value;
        case -1724546052: // description
          this.description = castToString(value); // StringType
          return value;
        case 1922406657: // supportingInfo
          this.getSupportingInfo().add(castToReference(value)); // Reference
          return value;
        case 3387378: // note
          this.getNote().add(castToAnnotation(value)); // Annotation
          return value;
        default: return super.setProperty(hash, name, value);
        }

      }

      @Override
      public Base setProperty(String name, Base value) throws FHIRException {
        if (name.equals("identifier")) {
          this.getIdentifier().add(castToIdentifier(value));
        } else if (name.equals("basedOn")) {
          this.getBasedOn().add(castToReference(value));
        } else if (name.equals("groupIdentifier")) {
          this.groupIdentifier = castToIdentifier(value); // Identifier
        } else if (name.equals("status")) {
          value = new ReferralRequestStatusEnumFactory().fromType(castToCode(value));
          this.status = (Enumeration) value; // Enumeration<ReferralRequestStatus>
        } else if (name.equals("intent")) {
          value = new ReferralCategoryEnumFactory().fromType(castToCode(value));
          this.intent = (Enumeration) value; // Enumeration<ReferralCategory>
        } else if (name.equals("type")) {
          this.type = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("priority")) {
          this.priority = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("serviceRequested")) {
          this.getServiceRequested().add(castToCodeableConcept(value));
        } else if (name.equals("patient")) {
          this.patient = castToReference(value); // Reference
        } else if (name.equals("context")) {
          this.context = castToReference(value); // Reference
        } else if (name.equals("fulfillmentTime")) {
          this.fulfillmentTime = castToPeriod(value); // Period
        } else if (name.equals("authoredOn")) {
          this.authoredOn = castToDateTime(value); // DateTimeType
        } else if (name.equals("requester")) {
          this.requester = castToReference(value); // Reference
        } else if (name.equals("specialty")) {
          this.specialty = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("recipient")) {
          this.getRecipient().add(castToReference(value));
        } else if (name.equals("reason")) {
          this.reason = castToCodeableConcept(value); // CodeableConcept
        } else if (name.equals("description")) {
          this.description = castToString(value); // StringType
        } else if (name.equals("supportingInfo")) {
          this.getSupportingInfo().add(castToReference(value));
        } else if (name.equals("note")) {
          this.getNote().add(castToAnnotation(value));
        } else
          return super.setProperty(name, value);
        return value;
      }

      @Override
      public Base makeProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855:  return addIdentifier(); 
        case -332612366:  return addBasedOn(); 
        case -445338488:  return getGroupIdentifier(); 
        case -892481550:  return getStatusElement();
        case -1183762788:  return getIntentElement();
        case 3575610:  return getType(); 
        case -1165461084:  return getPriority(); 
        case 190229561:  return addServiceRequested(); 
        case -791418107:  return getPatient(); 
        case 951530927:  return getContext(); 
        case 1098185163:  return getFulfillmentTime(); 
        case -1500852503:  return getAuthoredOnElement();
        case 693933948:  return getRequester(); 
        case -1694759682:  return getSpecialty(); 
        case 820081177:  return addRecipient(); 
        case -934964668:  return getReason(); 
        case -1724546052:  return getDescriptionElement();
        case 1922406657:  return addSupportingInfo(); 
        case 3387378:  return addNote(); 
        default: return super.makeProperty(hash, name);
        }

      }

      @Override
      public String[] getTypesForProperty(int hash, String name) throws FHIRException {
        switch (hash) {
        case -1618432855: /*identifier*/ return new String[] {"Identifier"};
        case -332612366: /*basedOn*/ return new String[] {"Reference"};
        case -445338488: /*groupIdentifier*/ return new String[] {"Identifier"};
        case -892481550: /*status*/ return new String[] {"code"};
        case -1183762788: /*intent*/ return new String[] {"code"};
        case 3575610: /*type*/ return new String[] {"CodeableConcept"};
        case -1165461084: /*priority*/ return new String[] {"CodeableConcept"};
        case 190229561: /*serviceRequested*/ return new String[] {"CodeableConcept"};
        case -791418107: /*patient*/ return new String[] {"Reference"};
        case 951530927: /*context*/ return new String[] {"Reference"};
        case 1098185163: /*fulfillmentTime*/ return new String[] {"Period"};
        case -1500852503: /*authoredOn*/ return new String[] {"dateTime"};
        case 693933948: /*requester*/ return new String[] {"Reference"};
        case -1694759682: /*specialty*/ return new String[] {"CodeableConcept"};
        case 820081177: /*recipient*/ return new String[] {"Reference"};
        case -934964668: /*reason*/ return new String[] {"CodeableConcept"};
        case -1724546052: /*description*/ return new String[] {"string"};
        case 1922406657: /*supportingInfo*/ return new String[] {"Reference"};
        case 3387378: /*note*/ return new String[] {"Annotation"};
        default: return super.getTypesForProperty(hash, name);
        }

      }

      @Override
      public Base addChild(String name) throws FHIRException {
        if (name.equals("identifier")) {
          return addIdentifier();
        }
        else if (name.equals("basedOn")) {
          return addBasedOn();
        }
        else if (name.equals("groupIdentifier")) {
          this.groupIdentifier = new Identifier();
          return this.groupIdentifier;
        }
        else if (name.equals("status")) {
          throw new FHIRException("Cannot call addChild on a primitive type ReferralRequest.status");
        }
        else if (name.equals("intent")) {
          throw new FHIRException("Cannot call addChild on a primitive type ReferralRequest.intent");
        }
        else if (name.equals("type")) {
          this.type = new CodeableConcept();
          return this.type;
        }
        else if (name.equals("priority")) {
          this.priority = new CodeableConcept();
          return this.priority;
        }
        else if (name.equals("serviceRequested")) {
          return addServiceRequested();
        }
        else if (name.equals("patient")) {
          this.patient = new Reference();
          return this.patient;
        }
        else if (name.equals("context")) {
          this.context = new Reference();
          return this.context;
        }
        else if (name.equals("fulfillmentTime")) {
          this.fulfillmentTime = new Period();
          return this.fulfillmentTime;
        }
        else if (name.equals("authoredOn")) {
          throw new FHIRException("Cannot call addChild on a primitive type ReferralRequest.authoredOn");
        }
        else if (name.equals("requester")) {
          this.requester = new Reference();
          return this.requester;
        }
        else if (name.equals("specialty")) {
          this.specialty = new CodeableConcept();
          return this.specialty;
        }
        else if (name.equals("recipient")) {
          return addRecipient();
        }
        else if (name.equals("reason")) {
          this.reason = new CodeableConcept();
          return this.reason;
        }
        else if (name.equals("description")) {
          throw new FHIRException("Cannot call addChild on a primitive type ReferralRequest.description");
        }
        else if (name.equals("supportingInfo")) {
          return addSupportingInfo();
        }
        else if (name.equals("note")) {
          return addNote();
        }
        else
          return super.addChild(name);
      }

  public String fhirType() {
    return "ReferralRequest";

  }

      public ReferralRequest copy() {
        ReferralRequest dst = new ReferralRequest();
        copyValues(dst);
        if (identifier != null) {
          dst.identifier = new ArrayList<Identifier>();
          for (Identifier i : identifier)
            dst.identifier.add(i.copy());
        };
        if (basedOn != null) {
          dst.basedOn = new ArrayList<Reference>();
          for (Reference i : basedOn)
            dst.basedOn.add(i.copy());
        };
        dst.groupIdentifier = groupIdentifier == null ? null : groupIdentifier.copy();
        dst.status = status == null ? null : status.copy();
        dst.intent = intent == null ? null : intent.copy();
        dst.type = type == null ? null : type.copy();
        dst.priority = priority == null ? null : priority.copy();
        if (serviceRequested != null) {
          dst.serviceRequested = new ArrayList<CodeableConcept>();
          for (CodeableConcept i : serviceRequested)
            dst.serviceRequested.add(i.copy());
        };
        dst.patient = patient == null ? null : patient.copy();
        dst.context = context == null ? null : context.copy();
        dst.fulfillmentTime = fulfillmentTime == null ? null : fulfillmentTime.copy();
        dst.authoredOn = authoredOn == null ? null : authoredOn.copy();
        dst.requester = requester == null ? null : requester.copy();
        dst.specialty = specialty == null ? null : specialty.copy();
        if (recipient != null) {
          dst.recipient = new ArrayList<Reference>();
          for (Reference i : recipient)
            dst.recipient.add(i.copy());
        };
        dst.reason = reason == null ? null : reason.copy();
        dst.description = description == null ? null : description.copy();
        if (supportingInfo != null) {
          dst.supportingInfo = new ArrayList<Reference>();
          for (Reference i : supportingInfo)
            dst.supportingInfo.add(i.copy());
        };
        if (note != null) {
          dst.note = new ArrayList<Annotation>();
          for (Annotation i : note)
            dst.note.add(i.copy());
        };
        return dst;
      }

      protected ReferralRequest typedCopy() {
        return copy();
      }

      @Override
      public boolean equalsDeep(Base other) {
        if (!super.equalsDeep(other))
          return false;
        if (!(other instanceof ReferralRequest))
          return false;
        ReferralRequest o = (ReferralRequest) other;
        return compareDeep(identifier, o.identifier, true) && compareDeep(basedOn, o.basedOn, true) && compareDeep(groupIdentifier, o.groupIdentifier, true)
           && compareDeep(status, o.status, true) && compareDeep(intent, o.intent, true) && compareDeep(type, o.type, true)
           && compareDeep(priority, o.priority, true) && compareDeep(serviceRequested, o.serviceRequested, true)
           && compareDeep(patient, o.patient, true) && compareDeep(context, o.context, true) && compareDeep(fulfillmentTime, o.fulfillmentTime, true)
           && compareDeep(authoredOn, o.authoredOn, true) && compareDeep(requester, o.requester, true) && compareDeep(specialty, o.specialty, true)
           && compareDeep(recipient, o.recipient, true) && compareDeep(reason, o.reason, true) && compareDeep(description, o.description, true)
           && compareDeep(supportingInfo, o.supportingInfo, true) && compareDeep(note, o.note, true);
      }

      @Override
      public boolean equalsShallow(Base other) {
        if (!super.equalsShallow(other))
          return false;
        if (!(other instanceof ReferralRequest))
          return false;
        ReferralRequest o = (ReferralRequest) other;
        return compareValues(status, o.status, true) && compareValues(intent, o.intent, true) && compareValues(authoredOn, o.authoredOn, true)
           && compareValues(description, o.description, true);
      }

      public boolean isEmpty() {
        return super.isEmpty() && ca.uhn.fhir.util.ElementUtil.isEmpty(identifier, basedOn, groupIdentifier
          , status, intent, type, priority, serviceRequested, patient, context, fulfillmentTime
          , authoredOn, requester, specialty, recipient, reason, description, supportingInfo
          , note);
      }

  @Override
  public ResourceType getResourceType() {
    return ResourceType.ReferralRequest;
   }

 /**
   * Search parameter: <b>date</b>
   * <p>
   * Description: <b>Creation or activation date</b><br>
   * Type: <b>date</b><br>
   * Path: <b>ReferralRequest.authoredOn</b><br>
   * </p>
   */
  @SearchParamDefinition(name="date", path="ReferralRequest.authoredOn", description="Creation or activation date", type="date" )
  public static final String SP_DATE = "date";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>date</b>
   * <p>
   * Description: <b>Creation or activation date</b><br>
   * Type: <b>date</b><br>
   * Path: <b>ReferralRequest.authoredOn</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.DateClientParam DATE = new ca.uhn.fhir.rest.gclient.DateClientParam(SP_DATE);

 /**
   * Search parameter: <b>requester</b>
   * <p>
   * Description: <b>Requester of referral / transfer of care</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.requester</b><br>
   * </p>
   */
  @SearchParamDefinition(name="requester", path="ReferralRequest.requester", description="Requester of referral / transfer of care", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Patient"), @ca.uhn.fhir.model.api.annotation.Compartment(name="Practitioner") }, target={Organization.class, Patient.class, Practitioner.class } )
  public static final String SP_REQUESTER = "requester";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>requester</b>
   * <p>
   * Description: <b>Requester of referral / transfer of care</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.requester</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam REQUESTER = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_REQUESTER);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>ReferralRequest:requester</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_REQUESTER = new ca.uhn.fhir.model.api.Include("ReferralRequest:requester").toLocked();

 /**
   * Search parameter: <b>identifier</b>
   * <p>
   * Description: <b>Business identifier</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.identifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="identifier", path="ReferralRequest.identifier", description="Business identifier", type="token" )
  public static final String SP_IDENTIFIER = "identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>identifier</b>
   * <p>
   * Description: <b>Business identifier</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.identifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_IDENTIFIER);

 /**
   * Search parameter: <b>specialty</b>
   * <p>
   * Description: <b>The specialty that the referral is for</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.specialty</b><br>
   * </p>
   */
  @SearchParamDefinition(name="specialty", path="ReferralRequest.specialty", description="The specialty that the referral is for", type="token" )
  public static final String SP_SPECIALTY = "specialty";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>specialty</b>
   * <p>
   * Description: <b>The specialty that the referral is for</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.specialty</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam SPECIALTY = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_SPECIALTY);

 /**
   * Search parameter: <b>type</b>
   * <p>
   * Description: <b>The type of the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.type</b><br>
   * </p>
   */
  @SearchParamDefinition(name="type", path="ReferralRequest.type", description="The type of the referral", type="token" )
  public static final String SP_TYPE = "type";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>type</b>
   * <p>
   * Description: <b>The type of the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.type</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam TYPE = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_TYPE);

 /**
   * Search parameter: <b>priority</b>
   * <p>
   * Description: <b>The priority assigned to the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.priority</b><br>
   * </p>
   */
  @SearchParamDefinition(name="priority", path="ReferralRequest.priority", description="The priority assigned to the referral", type="token" )
  public static final String SP_PRIORITY = "priority";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>priority</b>
   * <p>
   * Description: <b>The priority assigned to the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.priority</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam PRIORITY = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_PRIORITY);

 /**
   * Search parameter: <b>intent</b>
   * <p>
   * Description: <b>Proposal, plan or order</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.intent</b><br>
   * </p>
   */
  @SearchParamDefinition(name="intent", path="ReferralRequest.intent", description="Proposal, plan or order", type="token" )
  public static final String SP_INTENT = "intent";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>intent</b>
   * <p>
   * Description: <b>Proposal, plan or order</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.intent</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam INTENT = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_INTENT);

 /**
   * Search parameter: <b>group-identifier</b>
   * <p>
   * Description: <b>Part of common request</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.groupIdentifier</b><br>
   * </p>
   */
  @SearchParamDefinition(name="group-identifier", path="ReferralRequest.groupIdentifier", description="Part of common request", type="token" )
  public static final String SP_GROUP_IDENTIFIER = "group-identifier";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>group-identifier</b>
   * <p>
   * Description: <b>Part of common request</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.groupIdentifier</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam GROUP_IDENTIFIER = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_GROUP_IDENTIFIER);

 /**
   * Search parameter: <b>patient</b>
   * <p>
   * Description: <b>Who the referral is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.patient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="patient", path="ReferralRequest.patient", description="Who the referral is about", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Patient") }, target={Patient.class } )
  public static final String SP_PATIENT = "patient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>patient</b>
   * <p>
   * Description: <b>Who the referral is about</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.patient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam PATIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_PATIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>ReferralRequest:patient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_PATIENT = new ca.uhn.fhir.model.api.Include("ReferralRequest:patient").toLocked();

 /**
   * Search parameter: <b>recipient</b>
   * <p>
   * Description: <b>The person that the referral was sent to</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.recipient</b><br>
   * </p>
   */
  @SearchParamDefinition(name="recipient", path="ReferralRequest.recipient", description="The person that the referral was sent to", type="reference", providesMembershipIn={ @ca.uhn.fhir.model.api.annotation.Compartment(name="Practitioner") }, target={Organization.class, Practitioner.class } )
  public static final String SP_RECIPIENT = "recipient";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>recipient</b>
   * <p>
   * Description: <b>The person that the referral was sent to</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.recipient</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam RECIPIENT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_RECIPIENT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>ReferralRequest:recipient</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_RECIPIENT = new ca.uhn.fhir.model.api.Include("ReferralRequest:recipient").toLocked();

 /**
   * Search parameter: <b>context</b>
   * <p>
   * Description: <b>Part of encounter or episode of care</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.context</b><br>
   * </p>
   */
  @SearchParamDefinition(name="context", path="ReferralRequest.context", description="Part of encounter or episode of care", type="reference", target={Encounter.class, EpisodeOfCare.class } )
  public static final String SP_CONTEXT = "context";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>context</b>
   * <p>
   * Description: <b>Part of encounter or episode of care</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.context</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam CONTEXT = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_CONTEXT);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>ReferralRequest:context</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_CONTEXT = new ca.uhn.fhir.model.api.Include("ReferralRequest:context").toLocked();

 /**
   * Search parameter: <b>basedon</b>
   * <p>
   * Description: <b>Request being fulfilled</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.basedOn</b><br>
   * </p>
   */
  @SearchParamDefinition(name="basedon", path="ReferralRequest.basedOn", description="Request being fulfilled", type="reference", target={CarePlan.class, ProcedureRequest.class, ReferralRequest.class } )
  public static final String SP_BASEDON = "basedon";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>basedon</b>
   * <p>
   * Description: <b>Request being fulfilled</b><br>
   * Type: <b>reference</b><br>
   * Path: <b>ReferralRequest.basedOn</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.ReferenceClientParam BASEDON = new ca.uhn.fhir.rest.gclient.ReferenceClientParam(SP_BASEDON);

/**
   * Constant for fluent queries to be used to add include statements. Specifies
   * the path value of "<b>ReferralRequest:basedon</b>".
   */
  public static final ca.uhn.fhir.model.api.Include INCLUDE_BASEDON = new ca.uhn.fhir.model.api.Include("ReferralRequest:basedon").toLocked();

 /**
   * Search parameter: <b>status</b>
   * <p>
   * Description: <b>The status of the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.status</b><br>
   * </p>
   */
  @SearchParamDefinition(name="status", path="ReferralRequest.status", description="The status of the referral", type="token" )
  public static final String SP_STATUS = "status";
 /**
   * <b>Fluent Client</b> search parameter constant for <b>status</b>
   * <p>
   * Description: <b>The status of the referral</b><br>
   * Type: <b>token</b><br>
   * Path: <b>ReferralRequest.status</b><br>
   * </p>
   */
  public static final ca.uhn.fhir.rest.gclient.TokenClientParam STATUS = new ca.uhn.fhir.rest.gclient.TokenClientParam(SP_STATUS);


}

