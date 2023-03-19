library CMS9v4_CDS version '1'

using FHIR // version '1.4'

valueset "Feeding Intention-Breast": 'urn:oid:2.16.840.1.113762.1.4.1045.29'
valueset "Feeding Intention-Not-To-Breast": 'urn:oid:TBD' // Code '636111000124114' from "SNOMED-CT" display: 'Mother refuses to breastfeed (situation)'
valueset "Galactosemia": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.35'
valueset "Single Live Birth": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.25'
valueset "Single Live Born Newborn Born in Hospital": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.26'
valueset "Encounter Inpatient": 'urn:oid:2.16.840.1.113883.3.666.5.307'
valueset "Estimated Gestational Age at Birth": 'urn:oid:2.16.840.1.113762.1.4.1045.47'
valueset "Parenteral Nutrition": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.38'
valueset "Breast Milk": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.30'
valueset "Dietary Intake Other than Breast Milk": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.27'
valueset "Neonatal Intensive Care Unit (NICU)": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.75'
valueset "Patient Expired": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.309'
valueset "Discharge To Acute Care Facility": 'urn:oid:2.16.840.1.113883.3.117.1.7.1.87'

/*
If the mother qualifies for breast feeding
      a:  if the mother's intention regarding breast feeding has not been documented
             if there is a pending and uncancelled service request for education of the mother
                  create a communication request to the bedside nurse to perform the procedure
             elseif there is an unsigned service request for education of the mother
                  create a communication request to the primary provider to sign the order
             elseif there is no service request for education of the mother
                   create a service request for an education for the mother regarding exclusive breastfeeding
                   create a communication request to the provider to sign the order
             endif
       b: elseif the mother's intention regarding breast feeding has been documented
              if the mother has refused to breastfeed but no education of the mother has been documented
                   if there is a pending and uncancelled service request for education of the mother
                            create a communication request to the bedside nurse to perform the procedure
                   elseif there is an unsigned service request for education of the mother
                          create a communication request to the primary provider to sign the order
                   elseif there is no service request for education of the mother
                           create a service request for an education for the mother regarding exclusive breastfeeding
                           create a communication request to the primary provider to sign the order
                elseif the mother has refused to breastfeed and education of the mother has been documented
                           create a referral request for a lactation consult for the mother
                endif
*/

define "Needs Breastfeeding Readiness Assessment":
  not exists ( "Breastfeeding Exclusions" )
    and (
      not exists ( "Breastfeeding Intention" )
        or (
          exists ( "Intention not to Breastfeed" )
            and not exists ( "Completed Breastfeeding Readiness Assessment" )
        )
    )

define "Should Notify Nurse to Perform Assessment":
  "Needs Breastfeeding Readiness Assessment"
    and exists ( "Pending Breastfeeding Readiness Assessment Order" )

define "Should Notify Provider to Sign Assessment Order":
  "Needs Breastfeeding Readiness Assessment"
    and exists ( "Unsigned Breastfeeding Readiness Assessment Order" )

define "Should Create Assessment Order":
  "Needs Breastfeeding Readiness Assessment"
    and not exists ( "Breastfeeding Readiness Assessment Order" ) // Effectively captures all statuses

define "Should Create Lactation Consult":
  not exists ( "Breastfeeding Exclusions" )
    and exists ( "Intention not to Breastfeed" )
    and exists ( "Completed Breastfeeding Readiness Assessment" )
    and not exists ( "Lactation Consult Request" )

define "Breastfeeding Exclusions":
  [Condition: "Galactosemia"]
  // other exclusions...

define "Infant Birth Date":

define "Intention to Breastfeed":
  [RiskAssessment: "Feeding Intention-Breast"] R
    where R.date 1 hour or more after "Infant Birth Date"

define "Intention not to Breastfeed":
  [RiskAssessment: "Feeding Intention-Not-To-Breastfeed"] R
    where R.date 1 hour or more after "Infant Birth Date"

define "Breastfeeding Intention":
  "Intention to Breastfeed"
    union "Intention not to Breastfeed"

// NOTE: Workflow statuses are based on current definitions, these are subject to change based on ongoing workflow discussions in FHIR-I
// NOTE: Assumption here is that the only statuses that will result in a
// Procedure being created are the 'completed' and 'in-progress' statuses...
define "Breastfeeding Readiness Assessment Order":
  [ServiceRequest: "Breastfeeding Readiness Assessment"] P
    where P.orderedOn after "Infant Birth Date"

define "Unsigned Breastfeeding Readiness Assessment Order":
  "Breastfeeding Readiness Assessment Order" P
    where P.status in { 'proposed', 'draft', 'requested', 'received' }

define "Cancelled Breastfeeding Readiness Assessment Order":
  "Breasting Readiness Assessment Order" P
    where P.status in { 'suspended', 'rejected', 'aborted' }

define "Pending Breastfeeding Readiness Assessment Order":
  "Breastfeeding Readiness Assessment Order" P
    where P.status in { 'accepted' }

define "Completed Breastfeeding Readiness Assessment":
  [Procedure: "Breastfeeding Readiness Assessmet"] P
    where P.date after "Infant Birth Date"
      and P.status in { 'completed' }

define "Lactation Consult Request":
  [ServiceRequest: "Lactation Consult"] R
    where R.date after "Infant Birth Date"

// This is a request to administer a "Breastfeeding Readiness Assessment" as a Questionnaire
// Use Data Elements to coordinate the responses?
define "Create Breastfeeding Risk Assessment":
  ServiceRequest {
    subject: Patient,
    code: Concept {
      Code 'breastfeeding-readiness-assessment' from "http://example.org/documentation-templates"
      display 'Breastfeeding Readiness Assessment'
    },
    reason: "Single Live Birth",
    scheduled: Now(),
    encounter: "Live Birth Encounter",
    performer: "Charge Nurse",
    status: 'proposed',
    orderedOn: Now(),
    orderer: "CDS System",
    priority: 'routine'
  }

define "Communication Request to Provider":
  CommunicationRequest {
    sender: "CDS System",
    recipient: "Primary Provider",
    payload: {
      Tuple { content: 'A Breastfeeding Readiness Assessment is recommended, please authorize or reject the order.' },
      Tuple { content: "Create Breastfeeding Risk Assessment" }
    },
    status: 'proposed',
    encounter: "Live Birth Encounter",
    scheduled: Now(),
    reason: "Single Live Birth",
    requestedOn: Now(),
    subject: Patient,
    priority: Code 'routine' from "http://hl7.org/fhir/diagnostic-order-priority" display 'Routine'
  }

define "Communication Request to Charge Nurse":
CommunicationRequest {
  sender: "CDS System",
  recipient: "Charge Nurse",
  payload: {
    Tuple { content: 'A Breastfeeding Readiness Assessment is recommended, please administer the assessment.' },
    Tuple { content: "Create Breastfeeding Risk Assessment" }
  },
  status: 'proposed',
  encounter: "Live Birth Encounter",
  scheduled: Now(),
  reason: "Single Live Birth",
  requestedOn: Now(),
  subject: Patient,
  priority: Code 'routine' from "http://hl7.org/fhir/diagnostic-order-priority" display 'Routine'
}

define "Communication Request to Bedside Nurse":
CommunicationRequest {
  sender: "CDS System",
  recipient: "Bedside Nurse",
  payload: {
    Tuple { content: 'A Breastfeeding Readiness Assessment is recommended, please administer the assessment.' },
    Tuple { content: "Create Breastfeeding Risk Assessment" }
  },
  status: 'proposed',
  encounter: "Live Birth Encounter",
  scheduled: Now(),
  reason: "Single Live Birth",
  requestedOn: Now(),
  subject: Patient,
  priority: Code 'routine' from "http://hl7.org/fhir/diagnostic-order-priority" display 'Routine'
}

define "Create Lactation Consult Request":
  ServiceRequest {
    status: 'requested',
    date: Now(),
    type: Code 'lactation-consult' from "http://example.org/consultations" display 'Lactation Consult',
    priority: Code 'routine' from "http://hl7.org/fhir/diagnostic-order-priority" display 'Routine',
    patient: Patient,
    requester: "Primary Provider", // TODO: This should actually be the device, but the ServiceRequest doesn't support that
    encounter: "Live Birth Encounter",
    reason: Code 'negative' from "http://example.org/breastfeeding-intention" display 'Intention not to breast feed',
    description: 'Mother has declared an intention not to breastfeed, despite being given appropriate education regarding the benefits of exclusive breastmilk feeding.',
    fulfillmentTime: Now()
  }
