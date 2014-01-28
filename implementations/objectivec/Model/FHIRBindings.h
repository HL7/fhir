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
  

 * Generated on Wed, Jan 29, 2014 07:56+1100 for FHIR v0.12
 */

/*
 * Used to specify why the normally expected content of the data element is missing
 */
typedef enum 
{
    kDataAbsentReasonUnknown, // The value is not known
    kDataAbsentReasonAsked, // The source human does not know the value
    kDataAbsentReasonTemp, // There is reason to expect (from the workflow) that the value may become known
    kDataAbsentReasonNotasked, // The workflow didn't lead to this value being known
    kDataAbsentReasonMasked, // The information is not available due to security, privacy or related reasons
    kDataAbsentReasonUnsupported, // The source system wasn't capable of supporting this element
    kDataAbsentReasonAstext, // The content of the data is represented in the resource narrative
    kDataAbsentReasonError, // Some system or workflow process error means that the information is not available
} kDataAbsentReason;

/*
 * A set of generally useful codes defined so they can be included in value sets
 */
typedef enum 
{
    kSpecialValuesTrue, // Boolean true
    kSpecialValuesFalse, // Boolean false
    kSpecialValuesTrace, // The content is greater than zero, but too small to be quantified
    kSpecialValuesSufficient, // The specific quantity is not known, but is known to be non-zero and is not specified because it makes up the bulk of the material
    kSpecialValuesWithdrawn, // The value is no longer available
    kSpecialValuesNilKnown, // The are no known applicable values in this context
} kSpecialValues;

/*
 * List of all supported FHIR Resources
 */
typedef enum 
{
    kResourceTypeResource, // The Resource resource
    kResourceTypeAdverseReaction, // The AdverseReaction resource
    kResourceTypeAlert, // The Alert resource
    kResourceTypeAllergyIntolerance, // The AllergyIntolerance resource
    kResourceTypeAppointment, // The Appointment resource
    kResourceTypeAppointmentResponse, // The AppointmentResponse resource
    kResourceTypeAvailability, // The Availability resource
    kResourceTypeCarePlan, // The CarePlan resource
    kResourceTypeComposition, // The Composition resource
    kResourceTypeConceptMap, // The ConceptMap resource
    kResourceTypeCondition, // The Condition resource
    kResourceTypeConformance, // The Conformance resource
    kResourceTypeDevice, // The Device resource
    kResourceTypeDeviceObservationReport, // The DeviceObservationReport resource
    kResourceTypeDiagnosticOrder, // The DiagnosticOrder resource
    kResourceTypeDiagnosticReport, // The DiagnosticReport resource
    kResourceTypeDocumentManifest, // The DocumentManifest resource
    kResourceTypeDocumentReference, // The DocumentReference resource
    kResourceTypeEncounter, // The Encounter resource
    kResourceTypeFamilyHistory, // The FamilyHistory resource
    kResourceTypeGroup, // The Group resource
    kResourceTypeImagingStudy, // The ImagingStudy resource
    kResourceTypeImmunization, // The Immunization resource
    kResourceTypeImmunizationRecommendation, // The ImmunizationRecommendation resource
    kResourceTypeList, // The List resource
    kResourceTypeLocation, // The Location resource
    kResourceTypeMedia, // The Media resource
    kResourceTypeMedication, // The Medication resource
    kResourceTypeMedicationAdministration, // The MedicationAdministration resource
    kResourceTypeMedicationDispense, // The MedicationDispense resource
    kResourceTypeMedicationPrescription, // The MedicationPrescription resource
    kResourceTypeMedicationStatement, // The MedicationStatement resource
    kResourceTypeMessageHeader, // The MessageHeader resource
    kResourceTypeObservation, // The Observation resource
    kResourceTypeOperationOutcome, // The OperationOutcome resource
    kResourceTypeOrder, // The Order resource
    kResourceTypeOrderResponse, // The OrderResponse resource
    kResourceTypeOrganization, // The Organization resource
    kResourceTypeOther, // The Other resource
    kResourceTypePatient, // The Patient resource
    kResourceTypePractitioner, // The Practitioner resource
    kResourceTypeProcedure, // The Procedure resource
    kResourceTypeProfile, // The Profile resource
    kResourceTypeProvenance, // The Provenance resource
    kResourceTypeQuery, // The Query resource
    kResourceTypeQuestionnaire, // The Questionnaire resource
    kResourceTypeRelatedPerson, // The RelatedPerson resource
    kResourceTypeSecurityEvent, // The SecurityEvent resource
    kResourceTypeSlot, // The Slot resource
    kResourceTypeSpecimen, // The Specimen resource
    kResourceTypeSubstance, // The Substance resource
    kResourceTypeSupply, // The Supply resource
    kResourceTypeValueSet, // The ValueSet resource
    kResourceTypeBinary, // The Binary resource
} kResourceType;


