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

#import "FHIRSearchParamDefinition.h"
#import "FHIRModelInfo.h"

#import "FHIRAddress.h"
#import "FHIRAttachment.h"
#import "FHIRCodeableConcept.h"
#import "FHIRCoding.h"
#import "FHIRContact.h"
#import "FHIRElement.h"
#import "FHIRExtension.h"
#import "FHIRHumanName.h"
#import "FHIRIdentifier.h"
#import "FHIRNarrative.h"
#import "FHIRPeriod.h"
#import "FHIRQuantity.h"
#import "FHIRRange.h"
#import "FHIRRatio.h"
#import "FHIRResourceReference.h"
#import "FHIRSampledData.h"
#import "FHIRSchedule.h"
#import "FHIRScheduleRepeatComponent.h"
#import "FHIRBase64Binary.h"
#import "FHIRBoolean.h"
#import "FHIRCode.h"
#import "FHIRDate.h"
#import "FHIRDateTime.h"
#import "FHIRDecimal.h"
#import "FHIRId.h"
#import "FHIRIdref.h"
#import "FHIRInstant.h"
#import "FHIRInteger.h"
#import "FHIROid.h"
#import "FHIRString.h"
#import "FHIRUri.h"
#import "FHIRUuid.h"
#import "FHIRXhtml.h"
#import "FHIRResource.h"
#import "FHIRAdverseReaction.h"
#import "FHIRAdverseReactionSymptomComponent.h"
#import "FHIRAdverseReactionExposureComponent.h"
#import "FHIRAlert.h"
#import "FHIRAllergyIntolerance.h"
#import "FHIRAppointment.h"
#import "FHIRAppointmentParticipantComponent.h"
#import "FHIRAppointmentResponse.h"
#import "FHIRAvailability.h"
#import "FHIRCarePlan.h"
#import "FHIRCarePlanGoalComponent.h"
#import "FHIRCarePlanParticipantComponent.h"
#import "FHIRCarePlanActivityComponent.h"
#import "FHIRCarePlanActivitySimpleComponent.h"
#import "FHIRComposition.h"
#import "FHIRSectionComponent.h"
#import "FHIRCompositionEventComponent.h"
#import "FHIRCompositionAttesterComponent.h"
#import "FHIRConceptMap.h"
#import "FHIRConceptMapConceptMapComponent.h"
#import "FHIROtherConceptComponent.h"
#import "FHIRConceptMapConceptComponent.h"
#import "FHIRCondition.h"
#import "FHIRConditionRelatedItemComponent.h"
#import "FHIRConditionEvidenceComponent.h"
#import "FHIRConditionStageComponent.h"
#import "FHIRConditionLocationComponent.h"
#import "FHIRConformance.h"
#import "FHIRConformanceSoftwareComponent.h"
#import "FHIRConformanceRestComponent.h"
#import "FHIRConformanceMessagingComponent.h"
#import "FHIRConformanceImplementationComponent.h"
#import "FHIRConformanceRestResourceComponent.h"
#import "FHIRConformanceRestResourceOperationComponent.h"
#import "FHIRConformanceMessagingEventComponent.h"
#import "FHIRConformanceRestSecurityCertificateComponent.h"
#import "FHIRConformanceRestOperationComponent.h"
#import "FHIRConformanceRestQueryComponent.h"
#import "FHIRConformanceDocumentComponent.h"
#import "FHIRConformanceRestSecurityComponent.h"
#import "FHIRConformanceRestResourceSearchParamComponent.h"
#import "FHIRDevice.h"
#import "FHIRDeviceObservationReport.h"
#import "FHIRDeviceObservationReportVirtualDeviceComponent.h"
#import "FHIRDeviceObservationReportVirtualDeviceChannelMetricComponent.h"
#import "FHIRDeviceObservationReportVirtualDeviceChannelComponent.h"
#import "FHIRDiagnosticOrder.h"
#import "FHIRDiagnosticOrderItemComponent.h"
#import "FHIRDiagnosticOrderEventComponent.h"
#import "FHIRDiagnosticReport.h"
#import "FHIRDiagnosticReportImageComponent.h"
#import "FHIRResultGroupComponent.h"
#import "FHIRDocumentManifest.h"
#import "FHIRDocumentReference.h"
#import "FHIRDocumentReferenceContextComponent.h"
#import "FHIRDocumentReferenceRelatesToComponent.h"
#import "FHIRDocumentReferenceServiceParameterComponent.h"
#import "FHIRDocumentReferenceServiceComponent.h"
#import "FHIREncounter.h"
#import "FHIREncounterHospitalizationComponent.h"
#import "FHIREncounterHospitalizationAccomodationComponent.h"
#import "FHIREncounterLocationComponent.h"
#import "FHIREncounterParticipantComponent.h"
#import "FHIRFamilyHistory.h"
#import "FHIRFamilyHistoryRelationConditionComponent.h"
#import "FHIRFamilyHistoryRelationComponent.h"
#import "FHIRGroup.h"
#import "FHIRGroupCharacteristicComponent.h"
#import "FHIRImagingStudy.h"
#import "FHIRImagingStudySeriesComponent.h"
#import "FHIRImagingStudySeriesInstanceComponent.h"
#import "FHIRImmunization.h"
#import "FHIRImmunizationVaccinationProtocolComponent.h"
#import "FHIRImmunizationExplanationComponent.h"
#import "FHIRImmunizationReactionComponent.h"
#import "FHIRImmunizationRecommendation.h"
#import "FHIRImmunizationRecommendationRecommendationDateCriterionComponent.h"
#import "FHIRImmunizationRecommendationRecommendationProtocolComponent.h"
#import "FHIRImmunizationRecommendationRecommendationComponent.h"
#import "FHIRList.h"
#import "FHIRListEntryComponent.h"
#import "FHIRLocation.h"
#import "FHIRLocationPositionComponent.h"
#import "FHIRMedia.h"
#import "FHIRMedication.h"
#import "FHIRMedicationPackageContentComponent.h"
#import "FHIRMedicationPackageComponent.h"
#import "FHIRMedicationProductIngredientComponent.h"
#import "FHIRMedicationProductComponent.h"
#import "FHIRMedicationAdministration.h"
#import "FHIRMedicationAdministrationDosageComponent.h"
#import "FHIRMedicationDispense.h"
#import "FHIRMedicationDispenseDispenseDosageComponent.h"
#import "FHIRMedicationDispenseSubstitutionComponent.h"
#import "FHIRMedicationDispenseDispenseComponent.h"
#import "FHIRMedicationPrescription.h"
#import "FHIRMedicationPrescriptionDosageInstructionComponent.h"
#import "FHIRMedicationPrescriptionSubstitutionComponent.h"
#import "FHIRMedicationPrescriptionDispenseComponent.h"
#import "FHIRMedicationStatement.h"
#import "FHIRMedicationStatementDosageComponent.h"
#import "FHIRMessageHeader.h"
#import "FHIRMessageDestinationComponent.h"
#import "FHIRMessageSourceComponent.h"
#import "FHIRMessageHeaderResponseComponent.h"
#import "FHIRObservation.h"
#import "FHIRObservationReferenceRangeComponent.h"
#import "FHIROperationOutcome.h"
#import "FHIROperationOutcomeIssueComponent.h"
#import "FHIROrder.h"
#import "FHIROrderWhenComponent.h"
#import "FHIROrderResponse.h"
#import "FHIROrganization.h"
#import "FHIROrganizationContactComponent.h"
#import "FHIROther.h"
#import "FHIRPatient.h"
#import "FHIRContactComponent.h"
#import "FHIRAnimalComponent.h"
#import "FHIRPatientLinkComponent.h"
#import "FHIRPractitioner.h"
#import "FHIRPractitionerQualificationComponent.h"
#import "FHIRProcedure.h"
#import "FHIRProcedureRelatedItemComponent.h"
#import "FHIRProcedurePerformerComponent.h"
#import "FHIRProfile.h"
#import "FHIRElementDefinitionComponent.h"
#import "FHIRElementSlicingComponent.h"
#import "FHIRProfileStructureComponent.h"
#import "FHIRTypeRefComponent.h"
#import "FHIRProfileMappingComponent.h"
#import "FHIRElementDefinitionMappingComponent.h"
#import "FHIRElementDefinitionBindingComponent.h"
#import "FHIRElementDefinitionConstraintComponent.h"
#import "FHIRElementComponent.h"
#import "FHIRProfileExtensionDefnComponent.h"
#import "FHIRProvenance.h"
#import "FHIRProvenanceAgentComponent.h"
#import "FHIRProvenanceEntityComponent.h"
#import "FHIRQuery.h"
#import "FHIRQueryResponseComponent.h"
#import "FHIRQuestionnaire.h"
#import "FHIRQuestionComponent.h"
#import "FHIRGroupComponent.h"
#import "FHIRRelatedPerson.h"
#import "FHIRSecurityEvent.h"
#import "FHIRSecurityEventObjectDetailComponent.h"
#import "FHIRSecurityEventObjectComponent.h"
#import "FHIRSecurityEventSourceComponent.h"
#import "FHIRSecurityEventEventComponent.h"
#import "FHIRSecurityEventParticipantNetworkComponent.h"
#import "FHIRSecurityEventParticipantComponent.h"
#import "FHIRSlot.h"
#import "FHIRSpecimen.h"
#import "FHIRSpecimenCollectionComponent.h"
#import "FHIRSpecimenSourceComponent.h"
#import "FHIRSpecimenTreatmentComponent.h"
#import "FHIRSpecimenContainerComponent.h"
#import "FHIRSubstance.h"
#import "FHIRSubstanceIngredientComponent.h"
#import "FHIRSubstanceInstanceComponent.h"
#import "FHIRSupply.h"
#import "FHIRSupplyDispenseComponent.h"
#import "FHIRValueSet.h"
#import "FHIRValueSetDefineComponent.h"
#import "FHIRValueSetExpansionContainsComponent.h"
#import "FHIRConceptSetComponent.h"
#import "FHIRConceptSetFilterComponent.h"
#import "FHIRValueSetComposeComponent.h"
#import "FHIRValueSetDefineConceptComponent.h"
#import "FHIRValueSetExpansionComponent.h"
#import "FHIRBinary.h"

@interface FHIRModelInfo()
@property (nonatomic, strong) NSArray *supportedResources;
@property (nonatomic, strong) NSString *version;
@property (nonatomic, strong) NSDictionary *fhirStringToOcType;
@property (nonatomic, strong) NSDictionary *ocTypeToFhirString;
@property (nonatomic, strong) NSArray *searchParameters;
@end


@implementation FHIRModelInfo

/**
 * Get the instance of IWCalendarDatasource
 */
+ (FHIRModelInfo *)instance
{
    static FHIRModelInfo *instance;
    @synchronized(self)
    {
        if (instance == nil)
        {
            instance = [[FHIRModelInfo alloc] init];
        }
        return instance;
    }
}

- (id)init
{
    if(self=[super init])
    {
        
        [self setSupportedResources:@[
            @"AdverseReaction",
            @"Alert",
            @"AllergyIntolerance",
            @"Appointment",
            @"AppointmentResponse",
            @"Availability",
            @"CarePlan",
            @"Composition",
            @"ConceptMap",
            @"Condition",
            @"Conformance",
            @"Device",
            @"DeviceObservationReport",
            @"DiagnosticOrder",
            @"DiagnosticReport",
            @"DocumentManifest",
            @"DocumentReference",
            @"Encounter",
            @"FamilyHistory",
            @"Group",
            @"ImagingStudy",
            @"Immunization",
            @"ImmunizationRecommendation",
            @"List",
            @"Location",
            @"Media",
            @"Medication",
            @"MedicationAdministration",
            @"MedicationDispense",
            @"MedicationPrescription",
            @"MedicationStatement",
            @"MessageHeader",
            @"Observation",
            @"OperationOutcome",
            @"Order",
            @"OrderResponse",
            @"Organization",
            @"Other",
            @"Patient",
            @"Practitioner",
            @"Procedure",
            @"Profile",
            @"Provenance",
            @"Query",
            @"Questionnaire",
            @"RelatedPerson",
            @"SecurityEvent",
            @"Slot",
            @"Specimen",
            @"Substance",
            @"Supply",
            @"ValueSet",
            @"Binary",
        ]];
        
        [self setVersion:@"0.12"];
        
        [self setFhirStringToOcType:@{
            @"Address" : [FHIRAddress class],
            @"Attachment" : [FHIRAttachment class],
            @"CodeableConcept" : [FHIRCodeableConcept class],
            @"Coding" : [FHIRCoding class],
            @"Contact" : [FHIRContact class],
            @"Element" : [FHIRElement class],
            @"Extension" : [FHIRExtension class],
            @"HumanName" : [FHIRHumanName class],
            @"Identifier" : [FHIRIdentifier class],
            @"Narrative" : [FHIRNarrative class],
            @"Period" : [FHIRPeriod class],
            @"Quantity" : [FHIRQuantity class],
            @"Range" : [FHIRRange class],
            @"Ratio" : [FHIRRatio class],
            @"ResourceReference" : [FHIRResourceReference class],
            @"SampledData" : [FHIRSampledData class],
            @"Schedule" : [FHIRSchedule class],
            @"ScheduleRepeatComponent" : [FHIRScheduleRepeatComponent class],
            @"base64Binary" : [FHIRBase64Binary class],
            @"boolean" : [FHIRBoolean class],
            @"code" : [FHIRCode class],
            @"date" : [FHIRDate class],
            @"dateTime" : [FHIRDateTime class],
            @"decimal" : [FHIRDecimal class],
            @"id" : [FHIRId class],
            @"idref" : [FHIRIdref class],
            @"instant" : [FHIRInstant class],
            @"integer" : [FHIRInteger class],
            @"oid" : [FHIROid class],
            @"string" : [FHIRString class],
            @"uri" : [FHIRUri class],
            @"uuid" : [FHIRUuid class],
            @"xhtml" : [FHIRXhtml class],
            @"Resource" : [FHIRResource class],
            @"AdverseReaction" : [FHIRAdverseReaction class],
            @"AdverseReactionSymptomComponent" : [FHIRAdverseReactionSymptomComponent class],
            @"AdverseReactionExposureComponent" : [FHIRAdverseReactionExposureComponent class],
            @"Alert" : [FHIRAlert class],
            @"AllergyIntolerance" : [FHIRAllergyIntolerance class],
            @"Appointment" : [FHIRAppointment class],
            @"AppointmentParticipantComponent" : [FHIRAppointmentParticipantComponent class],
            @"AppointmentResponse" : [FHIRAppointmentResponse class],
            @"Availability" : [FHIRAvailability class],
            @"CarePlan" : [FHIRCarePlan class],
            @"CarePlanGoalComponent" : [FHIRCarePlanGoalComponent class],
            @"CarePlanParticipantComponent" : [FHIRCarePlanParticipantComponent class],
            @"CarePlanActivityComponent" : [FHIRCarePlanActivityComponent class],
            @"CarePlanActivitySimpleComponent" : [FHIRCarePlanActivitySimpleComponent class],
            @"Composition" : [FHIRComposition class],
            @"SectionComponent" : [FHIRSectionComponent class],
            @"CompositionEventComponent" : [FHIRCompositionEventComponent class],
            @"CompositionAttesterComponent" : [FHIRCompositionAttesterComponent class],
            @"ConceptMap" : [FHIRConceptMap class],
            @"ConceptMapConceptMapComponent" : [FHIRConceptMapConceptMapComponent class],
            @"OtherConceptComponent" : [FHIROtherConceptComponent class],
            @"ConceptMapConceptComponent" : [FHIRConceptMapConceptComponent class],
            @"Condition" : [FHIRCondition class],
            @"ConditionRelatedItemComponent" : [FHIRConditionRelatedItemComponent class],
            @"ConditionEvidenceComponent" : [FHIRConditionEvidenceComponent class],
            @"ConditionStageComponent" : [FHIRConditionStageComponent class],
            @"ConditionLocationComponent" : [FHIRConditionLocationComponent class],
            @"Conformance" : [FHIRConformance class],
            @"ConformanceSoftwareComponent" : [FHIRConformanceSoftwareComponent class],
            @"ConformanceRestComponent" : [FHIRConformanceRestComponent class],
            @"ConformanceMessagingComponent" : [FHIRConformanceMessagingComponent class],
            @"ConformanceImplementationComponent" : [FHIRConformanceImplementationComponent class],
            @"ConformanceRestResourceComponent" : [FHIRConformanceRestResourceComponent class],
            @"ConformanceRestResourceOperationComponent" : [FHIRConformanceRestResourceOperationComponent class],
            @"ConformanceMessagingEventComponent" : [FHIRConformanceMessagingEventComponent class],
            @"ConformanceRestSecurityCertificateComponent" : [FHIRConformanceRestSecurityCertificateComponent class],
            @"ConformanceRestOperationComponent" : [FHIRConformanceRestOperationComponent class],
            @"ConformanceRestQueryComponent" : [FHIRConformanceRestQueryComponent class],
            @"ConformanceDocumentComponent" : [FHIRConformanceDocumentComponent class],
            @"ConformanceRestSecurityComponent" : [FHIRConformanceRestSecurityComponent class],
            @"ConformanceRestResourceSearchParamComponent" : [FHIRConformanceRestResourceSearchParamComponent class],
            @"Device" : [FHIRDevice class],
            @"DeviceObservationReport" : [FHIRDeviceObservationReport class],
            @"DeviceObservationReportVirtualDeviceComponent" : [FHIRDeviceObservationReportVirtualDeviceComponent class],
            @"DeviceObservationReportVirtualDeviceChannelMetricComponent" : [FHIRDeviceObservationReportVirtualDeviceChannelMetricComponent class],
            @"DeviceObservationReportVirtualDeviceChannelComponent" : [FHIRDeviceObservationReportVirtualDeviceChannelComponent class],
            @"DiagnosticOrder" : [FHIRDiagnosticOrder class],
            @"DiagnosticOrderItemComponent" : [FHIRDiagnosticOrderItemComponent class],
            @"DiagnosticOrderEventComponent" : [FHIRDiagnosticOrderEventComponent class],
            @"DiagnosticReport" : [FHIRDiagnosticReport class],
            @"DiagnosticReportImageComponent" : [FHIRDiagnosticReportImageComponent class],
            @"ResultGroupComponent" : [FHIRResultGroupComponent class],
            @"DocumentManifest" : [FHIRDocumentManifest class],
            @"DocumentReference" : [FHIRDocumentReference class],
            @"DocumentReferenceContextComponent" : [FHIRDocumentReferenceContextComponent class],
            @"DocumentReferenceRelatesToComponent" : [FHIRDocumentReferenceRelatesToComponent class],
            @"DocumentReferenceServiceParameterComponent" : [FHIRDocumentReferenceServiceParameterComponent class],
            @"DocumentReferenceServiceComponent" : [FHIRDocumentReferenceServiceComponent class],
            @"Encounter" : [FHIREncounter class],
            @"EncounterHospitalizationComponent" : [FHIREncounterHospitalizationComponent class],
            @"EncounterHospitalizationAccomodationComponent" : [FHIREncounterHospitalizationAccomodationComponent class],
            @"EncounterLocationComponent" : [FHIREncounterLocationComponent class],
            @"EncounterParticipantComponent" : [FHIREncounterParticipantComponent class],
            @"FamilyHistory" : [FHIRFamilyHistory class],
            @"FamilyHistoryRelationConditionComponent" : [FHIRFamilyHistoryRelationConditionComponent class],
            @"FamilyHistoryRelationComponent" : [FHIRFamilyHistoryRelationComponent class],
            @"Group" : [FHIRGroup class],
            @"GroupCharacteristicComponent" : [FHIRGroupCharacteristicComponent class],
            @"ImagingStudy" : [FHIRImagingStudy class],
            @"ImagingStudySeriesComponent" : [FHIRImagingStudySeriesComponent class],
            @"ImagingStudySeriesInstanceComponent" : [FHIRImagingStudySeriesInstanceComponent class],
            @"Immunization" : [FHIRImmunization class],
            @"ImmunizationVaccinationProtocolComponent" : [FHIRImmunizationVaccinationProtocolComponent class],
            @"ImmunizationExplanationComponent" : [FHIRImmunizationExplanationComponent class],
            @"ImmunizationReactionComponent" : [FHIRImmunizationReactionComponent class],
            @"ImmunizationRecommendation" : [FHIRImmunizationRecommendation class],
            @"ImmunizationRecommendationRecommendationDateCriterionComponent" : [FHIRImmunizationRecommendationRecommendationDateCriterionComponent class],
            @"ImmunizationRecommendationRecommendationProtocolComponent" : [FHIRImmunizationRecommendationRecommendationProtocolComponent class],
            @"ImmunizationRecommendationRecommendationComponent" : [FHIRImmunizationRecommendationRecommendationComponent class],
            @"List" : [FHIRList class],
            @"ListEntryComponent" : [FHIRListEntryComponent class],
            @"Location" : [FHIRLocation class],
            @"LocationPositionComponent" : [FHIRLocationPositionComponent class],
            @"Media" : [FHIRMedia class],
            @"Medication" : [FHIRMedication class],
            @"MedicationPackageContentComponent" : [FHIRMedicationPackageContentComponent class],
            @"MedicationPackageComponent" : [FHIRMedicationPackageComponent class],
            @"MedicationProductIngredientComponent" : [FHIRMedicationProductIngredientComponent class],
            @"MedicationProductComponent" : [FHIRMedicationProductComponent class],
            @"MedicationAdministration" : [FHIRMedicationAdministration class],
            @"MedicationAdministrationDosageComponent" : [FHIRMedicationAdministrationDosageComponent class],
            @"MedicationDispense" : [FHIRMedicationDispense class],
            @"MedicationDispenseDispenseDosageComponent" : [FHIRMedicationDispenseDispenseDosageComponent class],
            @"MedicationDispenseSubstitutionComponent" : [FHIRMedicationDispenseSubstitutionComponent class],
            @"MedicationDispenseDispenseComponent" : [FHIRMedicationDispenseDispenseComponent class],
            @"MedicationPrescription" : [FHIRMedicationPrescription class],
            @"MedicationPrescriptionDosageInstructionComponent" : [FHIRMedicationPrescriptionDosageInstructionComponent class],
            @"MedicationPrescriptionSubstitutionComponent" : [FHIRMedicationPrescriptionSubstitutionComponent class],
            @"MedicationPrescriptionDispenseComponent" : [FHIRMedicationPrescriptionDispenseComponent class],
            @"MedicationStatement" : [FHIRMedicationStatement class],
            @"MedicationStatementDosageComponent" : [FHIRMedicationStatementDosageComponent class],
            @"MessageHeader" : [FHIRMessageHeader class],
            @"MessageDestinationComponent" : [FHIRMessageDestinationComponent class],
            @"MessageSourceComponent" : [FHIRMessageSourceComponent class],
            @"MessageHeaderResponseComponent" : [FHIRMessageHeaderResponseComponent class],
            @"Observation" : [FHIRObservation class],
            @"ObservationReferenceRangeComponent" : [FHIRObservationReferenceRangeComponent class],
            @"OperationOutcome" : [FHIROperationOutcome class],
            @"OperationOutcomeIssueComponent" : [FHIROperationOutcomeIssueComponent class],
            @"Order" : [FHIROrder class],
            @"OrderWhenComponent" : [FHIROrderWhenComponent class],
            @"OrderResponse" : [FHIROrderResponse class],
            @"Organization" : [FHIROrganization class],
            @"OrganizationContactComponent" : [FHIROrganizationContactComponent class],
            @"Other" : [FHIROther class],
            @"Patient" : [FHIRPatient class],
            @"ContactComponent" : [FHIRContactComponent class],
            @"AnimalComponent" : [FHIRAnimalComponent class],
            @"PatientLinkComponent" : [FHIRPatientLinkComponent class],
            @"Practitioner" : [FHIRPractitioner class],
            @"PractitionerQualificationComponent" : [FHIRPractitionerQualificationComponent class],
            @"Procedure" : [FHIRProcedure class],
            @"ProcedureRelatedItemComponent" : [FHIRProcedureRelatedItemComponent class],
            @"ProcedurePerformerComponent" : [FHIRProcedurePerformerComponent class],
            @"Profile" : [FHIRProfile class],
            @"ElementDefinitionComponent" : [FHIRElementDefinitionComponent class],
            @"ElementSlicingComponent" : [FHIRElementSlicingComponent class],
            @"ProfileStructureComponent" : [FHIRProfileStructureComponent class],
            @"TypeRefComponent" : [FHIRTypeRefComponent class],
            @"ProfileMappingComponent" : [FHIRProfileMappingComponent class],
            @"ElementDefinitionMappingComponent" : [FHIRElementDefinitionMappingComponent class],
            @"ElementDefinitionBindingComponent" : [FHIRElementDefinitionBindingComponent class],
            @"ElementDefinitionConstraintComponent" : [FHIRElementDefinitionConstraintComponent class],
            @"ElementComponent" : [FHIRElementComponent class],
            @"ProfileExtensionDefnComponent" : [FHIRProfileExtensionDefnComponent class],
            @"Provenance" : [FHIRProvenance class],
            @"ProvenanceAgentComponent" : [FHIRProvenanceAgentComponent class],
            @"ProvenanceEntityComponent" : [FHIRProvenanceEntityComponent class],
            @"Query" : [FHIRQuery class],
            @"QueryResponseComponent" : [FHIRQueryResponseComponent class],
            @"Questionnaire" : [FHIRQuestionnaire class],
            @"QuestionComponent" : [FHIRQuestionComponent class],
            @"GroupComponent" : [FHIRGroupComponent class],
            @"RelatedPerson" : [FHIRRelatedPerson class],
            @"SecurityEvent" : [FHIRSecurityEvent class],
            @"SecurityEventObjectDetailComponent" : [FHIRSecurityEventObjectDetailComponent class],
            @"SecurityEventObjectComponent" : [FHIRSecurityEventObjectComponent class],
            @"SecurityEventSourceComponent" : [FHIRSecurityEventSourceComponent class],
            @"SecurityEventEventComponent" : [FHIRSecurityEventEventComponent class],
            @"SecurityEventParticipantNetworkComponent" : [FHIRSecurityEventParticipantNetworkComponent class],
            @"SecurityEventParticipantComponent" : [FHIRSecurityEventParticipantComponent class],
            @"Slot" : [FHIRSlot class],
            @"Specimen" : [FHIRSpecimen class],
            @"SpecimenCollectionComponent" : [FHIRSpecimenCollectionComponent class],
            @"SpecimenSourceComponent" : [FHIRSpecimenSourceComponent class],
            @"SpecimenTreatmentComponent" : [FHIRSpecimenTreatmentComponent class],
            @"SpecimenContainerComponent" : [FHIRSpecimenContainerComponent class],
            @"Substance" : [FHIRSubstance class],
            @"SubstanceIngredientComponent" : [FHIRSubstanceIngredientComponent class],
            @"SubstanceInstanceComponent" : [FHIRSubstanceInstanceComponent class],
            @"Supply" : [FHIRSupply class],
            @"SupplyDispenseComponent" : [FHIRSupplyDispenseComponent class],
            @"ValueSet" : [FHIRValueSet class],
            @"ValueSetDefineComponent" : [FHIRValueSetDefineComponent class],
            @"ValueSetExpansionContainsComponent" : [FHIRValueSetExpansionContainsComponent class],
            @"ConceptSetComponent" : [FHIRConceptSetComponent class],
            @"ConceptSetFilterComponent" : [FHIRConceptSetFilterComponent class],
            @"ValueSetComposeComponent" : [FHIRValueSetComposeComponent class],
            @"ValueSetDefineConceptComponent" : [FHIRValueSetDefineConceptComponent class],
            @"ValueSetExpansionComponent" : [FHIRValueSetExpansionComponent class],
            @"Binary" : [FHIRBinary class],
        }];
        
        [self setOcTypeToFhirString:@{
            @"FHIRAddress" : @"Address",
            @"FHIRAttachment" : @"Attachment",
            @"FHIRCodeableConcept" : @"CodeableConcept",
            @"FHIRCoding" : @"Coding",
            @"FHIRContact" : @"Contact",
            @"FHIRElement" : @"Element",
            @"FHIRExtension" : @"Extension",
            @"FHIRHumanName" : @"HumanName",
            @"FHIRIdentifier" : @"Identifier",
            @"FHIRNarrative" : @"Narrative",
            @"FHIRPeriod" : @"Period",
            @"FHIRQuantity" : @"Quantity",
            @"FHIRRange" : @"Range",
            @"FHIRRatio" : @"Ratio",
            @"FHIRResourceReference" : @"ResourceReference",
            @"FHIRSampledData" : @"SampledData",
            @"FHIRSchedule" : @"Schedule",
            @"FHIRScheduleRepeatComponent" : @"ScheduleRepeatComponent",
            @"FHIRBase64Binary" : @"base64Binary",
            @"FHIRBoolean" : @"boolean",
            @"FHIRCode" : @"code",
            @"FHIRDate" : @"date",
            @"FHIRDateTime" : @"dateTime",
            @"FHIRDecimal" : @"decimal",
            @"FHIRId" : @"id",
            @"FHIRIdref" : @"idref",
            @"FHIRInstant" : @"instant",
            @"FHIRInteger" : @"integer",
            @"FHIROid" : @"oid",
            @"FHIRString" : @"string",
            @"FHIRUri" : @"uri",
            @"FHIRUuid" : @"uuid",
            @"FHIRXhtml" : @"xhtml",
            @"FHIRResource" : @"Resource",
            @"FHIRAdverseReaction" : @"AdverseReaction",
            @"FHIRAdverseReactionSymptomComponent" : @"AdverseReactionSymptomComponent",
            @"FHIRAdverseReactionExposureComponent" : @"AdverseReactionExposureComponent",
            @"FHIRAlert" : @"Alert",
            @"FHIRAllergyIntolerance" : @"AllergyIntolerance",
            @"FHIRAppointment" : @"Appointment",
            @"FHIRAppointmentParticipantComponent" : @"AppointmentParticipantComponent",
            @"FHIRAppointmentResponse" : @"AppointmentResponse",
            @"FHIRAvailability" : @"Availability",
            @"FHIRCarePlan" : @"CarePlan",
            @"FHIRCarePlanGoalComponent" : @"CarePlanGoalComponent",
            @"FHIRCarePlanParticipantComponent" : @"CarePlanParticipantComponent",
            @"FHIRCarePlanActivityComponent" : @"CarePlanActivityComponent",
            @"FHIRCarePlanActivitySimpleComponent" : @"CarePlanActivitySimpleComponent",
            @"FHIRComposition" : @"Composition",
            @"FHIRSectionComponent" : @"SectionComponent",
            @"FHIRCompositionEventComponent" : @"CompositionEventComponent",
            @"FHIRCompositionAttesterComponent" : @"CompositionAttesterComponent",
            @"FHIRConceptMap" : @"ConceptMap",
            @"FHIRConceptMapConceptMapComponent" : @"ConceptMapConceptMapComponent",
            @"FHIROtherConceptComponent" : @"OtherConceptComponent",
            @"FHIRConceptMapConceptComponent" : @"ConceptMapConceptComponent",
            @"FHIRCondition" : @"Condition",
            @"FHIRConditionRelatedItemComponent" : @"ConditionRelatedItemComponent",
            @"FHIRConditionEvidenceComponent" : @"ConditionEvidenceComponent",
            @"FHIRConditionStageComponent" : @"ConditionStageComponent",
            @"FHIRConditionLocationComponent" : @"ConditionLocationComponent",
            @"FHIRConformance" : @"Conformance",
            @"FHIRConformanceSoftwareComponent" : @"ConformanceSoftwareComponent",
            @"FHIRConformanceRestComponent" : @"ConformanceRestComponent",
            @"FHIRConformanceMessagingComponent" : @"ConformanceMessagingComponent",
            @"FHIRConformanceImplementationComponent" : @"ConformanceImplementationComponent",
            @"FHIRConformanceRestResourceComponent" : @"ConformanceRestResourceComponent",
            @"FHIRConformanceRestResourceOperationComponent" : @"ConformanceRestResourceOperationComponent",
            @"FHIRConformanceMessagingEventComponent" : @"ConformanceMessagingEventComponent",
            @"FHIRConformanceRestSecurityCertificateComponent" : @"ConformanceRestSecurityCertificateComponent",
            @"FHIRConformanceRestOperationComponent" : @"ConformanceRestOperationComponent",
            @"FHIRConformanceRestQueryComponent" : @"ConformanceRestQueryComponent",
            @"FHIRConformanceDocumentComponent" : @"ConformanceDocumentComponent",
            @"FHIRConformanceRestSecurityComponent" : @"ConformanceRestSecurityComponent",
            @"FHIRConformanceRestResourceSearchParamComponent" : @"ConformanceRestResourceSearchParamComponent",
            @"FHIRDevice" : @"Device",
            @"FHIRDeviceObservationReport" : @"DeviceObservationReport",
            @"FHIRDeviceObservationReportVirtualDeviceComponent" : @"DeviceObservationReportVirtualDeviceComponent",
            @"FHIRDeviceObservationReportVirtualDeviceChannelMetricComponent" : @"DeviceObservationReportVirtualDeviceChannelMetricComponent",
            @"FHIRDeviceObservationReportVirtualDeviceChannelComponent" : @"DeviceObservationReportVirtualDeviceChannelComponent",
            @"FHIRDiagnosticOrder" : @"DiagnosticOrder",
            @"FHIRDiagnosticOrderItemComponent" : @"DiagnosticOrderItemComponent",
            @"FHIRDiagnosticOrderEventComponent" : @"DiagnosticOrderEventComponent",
            @"FHIRDiagnosticReport" : @"DiagnosticReport",
            @"FHIRDiagnosticReportImageComponent" : @"DiagnosticReportImageComponent",
            @"FHIRResultGroupComponent" : @"ResultGroupComponent",
            @"FHIRDocumentManifest" : @"DocumentManifest",
            @"FHIRDocumentReference" : @"DocumentReference",
            @"FHIRDocumentReferenceContextComponent" : @"DocumentReferenceContextComponent",
            @"FHIRDocumentReferenceRelatesToComponent" : @"DocumentReferenceRelatesToComponent",
            @"FHIRDocumentReferenceServiceParameterComponent" : @"DocumentReferenceServiceParameterComponent",
            @"FHIRDocumentReferenceServiceComponent" : @"DocumentReferenceServiceComponent",
            @"FHIREncounter" : @"Encounter",
            @"FHIREncounterHospitalizationComponent" : @"EncounterHospitalizationComponent",
            @"FHIREncounterHospitalizationAccomodationComponent" : @"EncounterHospitalizationAccomodationComponent",
            @"FHIREncounterLocationComponent" : @"EncounterLocationComponent",
            @"FHIREncounterParticipantComponent" : @"EncounterParticipantComponent",
            @"FHIRFamilyHistory" : @"FamilyHistory",
            @"FHIRFamilyHistoryRelationConditionComponent" : @"FamilyHistoryRelationConditionComponent",
            @"FHIRFamilyHistoryRelationComponent" : @"FamilyHistoryRelationComponent",
            @"FHIRGroup" : @"Group",
            @"FHIRGroupCharacteristicComponent" : @"GroupCharacteristicComponent",
            @"FHIRImagingStudy" : @"ImagingStudy",
            @"FHIRImagingStudySeriesComponent" : @"ImagingStudySeriesComponent",
            @"FHIRImagingStudySeriesInstanceComponent" : @"ImagingStudySeriesInstanceComponent",
            @"FHIRImmunization" : @"Immunization",
            @"FHIRImmunizationVaccinationProtocolComponent" : @"ImmunizationVaccinationProtocolComponent",
            @"FHIRImmunizationExplanationComponent" : @"ImmunizationExplanationComponent",
            @"FHIRImmunizationReactionComponent" : @"ImmunizationReactionComponent",
            @"FHIRImmunizationRecommendation" : @"ImmunizationRecommendation",
            @"FHIRImmunizationRecommendationRecommendationDateCriterionComponent" : @"ImmunizationRecommendationRecommendationDateCriterionComponent",
            @"FHIRImmunizationRecommendationRecommendationProtocolComponent" : @"ImmunizationRecommendationRecommendationProtocolComponent",
            @"FHIRImmunizationRecommendationRecommendationComponent" : @"ImmunizationRecommendationRecommendationComponent",
            @"FHIRList" : @"List",
            @"FHIRListEntryComponent" : @"ListEntryComponent",
            @"FHIRLocation" : @"Location",
            @"FHIRLocationPositionComponent" : @"LocationPositionComponent",
            @"FHIRMedia" : @"Media",
            @"FHIRMedication" : @"Medication",
            @"FHIRMedicationPackageContentComponent" : @"MedicationPackageContentComponent",
            @"FHIRMedicationPackageComponent" : @"MedicationPackageComponent",
            @"FHIRMedicationProductIngredientComponent" : @"MedicationProductIngredientComponent",
            @"FHIRMedicationProductComponent" : @"MedicationProductComponent",
            @"FHIRMedicationAdministration" : @"MedicationAdministration",
            @"FHIRMedicationAdministrationDosageComponent" : @"MedicationAdministrationDosageComponent",
            @"FHIRMedicationDispense" : @"MedicationDispense",
            @"FHIRMedicationDispenseDispenseDosageComponent" : @"MedicationDispenseDispenseDosageComponent",
            @"FHIRMedicationDispenseSubstitutionComponent" : @"MedicationDispenseSubstitutionComponent",
            @"FHIRMedicationDispenseDispenseComponent" : @"MedicationDispenseDispenseComponent",
            @"FHIRMedicationPrescription" : @"MedicationPrescription",
            @"FHIRMedicationPrescriptionDosageInstructionComponent" : @"MedicationPrescriptionDosageInstructionComponent",
            @"FHIRMedicationPrescriptionSubstitutionComponent" : @"MedicationPrescriptionSubstitutionComponent",
            @"FHIRMedicationPrescriptionDispenseComponent" : @"MedicationPrescriptionDispenseComponent",
            @"FHIRMedicationStatement" : @"MedicationStatement",
            @"FHIRMedicationStatementDosageComponent" : @"MedicationStatementDosageComponent",
            @"FHIRMessageHeader" : @"MessageHeader",
            @"FHIRMessageDestinationComponent" : @"MessageDestinationComponent",
            @"FHIRMessageSourceComponent" : @"MessageSourceComponent",
            @"FHIRMessageHeaderResponseComponent" : @"MessageHeaderResponseComponent",
            @"FHIRObservation" : @"Observation",
            @"FHIRObservationReferenceRangeComponent" : @"ObservationReferenceRangeComponent",
            @"FHIROperationOutcome" : @"OperationOutcome",
            @"FHIROperationOutcomeIssueComponent" : @"OperationOutcomeIssueComponent",
            @"FHIROrder" : @"Order",
            @"FHIROrderWhenComponent" : @"OrderWhenComponent",
            @"FHIROrderResponse" : @"OrderResponse",
            @"FHIROrganization" : @"Organization",
            @"FHIROrganizationContactComponent" : @"OrganizationContactComponent",
            @"FHIROther" : @"Other",
            @"FHIRPatient" : @"Patient",
            @"FHIRContactComponent" : @"ContactComponent",
            @"FHIRAnimalComponent" : @"AnimalComponent",
            @"FHIRPatientLinkComponent" : @"PatientLinkComponent",
            @"FHIRPractitioner" : @"Practitioner",
            @"FHIRPractitionerQualificationComponent" : @"PractitionerQualificationComponent",
            @"FHIRProcedure" : @"Procedure",
            @"FHIRProcedureRelatedItemComponent" : @"ProcedureRelatedItemComponent",
            @"FHIRProcedurePerformerComponent" : @"ProcedurePerformerComponent",
            @"FHIRProfile" : @"Profile",
            @"FHIRElementDefinitionComponent" : @"ElementDefinitionComponent",
            @"FHIRElementSlicingComponent" : @"ElementSlicingComponent",
            @"FHIRProfileStructureComponent" : @"ProfileStructureComponent",
            @"FHIRTypeRefComponent" : @"TypeRefComponent",
            @"FHIRProfileMappingComponent" : @"ProfileMappingComponent",
            @"FHIRElementDefinitionMappingComponent" : @"ElementDefinitionMappingComponent",
            @"FHIRElementDefinitionBindingComponent" : @"ElementDefinitionBindingComponent",
            @"FHIRElementDefinitionConstraintComponent" : @"ElementDefinitionConstraintComponent",
            @"FHIRElementComponent" : @"ElementComponent",
            @"FHIRProfileExtensionDefnComponent" : @"ProfileExtensionDefnComponent",
            @"FHIRProvenance" : @"Provenance",
            @"FHIRProvenanceAgentComponent" : @"ProvenanceAgentComponent",
            @"FHIRProvenanceEntityComponent" : @"ProvenanceEntityComponent",
            @"FHIRQuery" : @"Query",
            @"FHIRQueryResponseComponent" : @"QueryResponseComponent",
            @"FHIRQuestionnaire" : @"Questionnaire",
            @"FHIRQuestionComponent" : @"QuestionComponent",
            @"FHIRGroupComponent" : @"GroupComponent",
            @"FHIRRelatedPerson" : @"RelatedPerson",
            @"FHIRSecurityEvent" : @"SecurityEvent",
            @"FHIRSecurityEventObjectDetailComponent" : @"SecurityEventObjectDetailComponent",
            @"FHIRSecurityEventObjectComponent" : @"SecurityEventObjectComponent",
            @"FHIRSecurityEventSourceComponent" : @"SecurityEventSourceComponent",
            @"FHIRSecurityEventEventComponent" : @"SecurityEventEventComponent",
            @"FHIRSecurityEventParticipantNetworkComponent" : @"SecurityEventParticipantNetworkComponent",
            @"FHIRSecurityEventParticipantComponent" : @"SecurityEventParticipantComponent",
            @"FHIRSlot" : @"Slot",
            @"FHIRSpecimen" : @"Specimen",
            @"FHIRSpecimenCollectionComponent" : @"SpecimenCollectionComponent",
            @"FHIRSpecimenSourceComponent" : @"SpecimenSourceComponent",
            @"FHIRSpecimenTreatmentComponent" : @"SpecimenTreatmentComponent",
            @"FHIRSpecimenContainerComponent" : @"SpecimenContainerComponent",
            @"FHIRSubstance" : @"Substance",
            @"FHIRSubstanceIngredientComponent" : @"SubstanceIngredientComponent",
            @"FHIRSubstanceInstanceComponent" : @"SubstanceInstanceComponent",
            @"FHIRSupply" : @"Supply",
            @"FHIRSupplyDispenseComponent" : @"SupplyDispenseComponent",
            @"FHIRValueSet" : @"ValueSet",
            @"FHIRValueSetDefineComponent" : @"ValueSetDefineComponent",
            @"FHIRValueSetExpansionContainsComponent" : @"ValueSetExpansionContainsComponent",
            @"FHIRConceptSetComponent" : @"ConceptSetComponent",
            @"FHIRConceptSetFilterComponent" : @"ConceptSetFilterComponent",
            @"FHIRValueSetComposeComponent" : @"ValueSetComposeComponent",
            @"FHIRValueSetDefineConceptComponent" : @"ValueSetDefineConceptComponent",
            @"FHIRValueSetExpansionComponent" : @"ValueSetExpansionComponent",
            @"FHIRBinary" : @"Binary",
        }];
        
        [self setSearchParameters:@[
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAdverseReaction" name:@"substance" description:@"The name or code of the substance that produces the sensitivity" type:kSearchParamTypeReference compositeParams: nil path: @[@"AdverseReaction.exposure.substance", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAdverseReaction" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAdverseReaction" name:@"subject" description:@"The subject that the sensitivity is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"AdverseReaction.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAdverseReaction" name:@"date" description:@"The date of the reaction" type:kSearchParamTypeDate compositeParams: nil path: @[@"AdverseReaction.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAdverseReaction" name:@"symptom" description:@"One of the symptoms of the reaction" type:kSearchParamTypeToken compositeParams: nil path: @[@"AdverseReaction.symptom.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAlert" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAlert" name:@"subject" description:@"The identity of a subject to list alerts for" type:kSearchParamTypeReference compositeParams: nil path: @[@"Alert.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"substance" description:@"The name or code of the substance that produces the sensitivity" type:kSearchParamTypeReference compositeParams: nil path: @[@"AllergyIntolerance.substance", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"status" description:@"The status of the sensitivity" type:kSearchParamTypeToken compositeParams: nil path: @[@"AllergyIntolerance.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"recorder" description:@"Who recorded the sensitivity" type:kSearchParamTypeReference compositeParams: nil path: @[@"AllergyIntolerance.recorder", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"subject" description:@"The subject that the sensitivity is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"AllergyIntolerance.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"date" description:@"Recorded date/time." type:kSearchParamTypeDate compositeParams: nil path: @[@"AllergyIntolerance.recordedDate", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAllergyIntolerance" name:@"type" description:@"The type of sensitivity" type:kSearchParamTypeToken compositeParams: nil path: @[@"AllergyIntolerance.sensitivityType", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointment" name:@"partstatus" description:@"The Participation status of the subject, or other participant on the appointment" type:kSearchParamTypeToken compositeParams: nil path: @[@"Appointment.participant.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointment" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointment" name:@"status" description:@"The overall status of the appointment" type:kSearchParamTypeString compositeParams: nil path: @[@"Appointment.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointment" name:@"subject" description:@"The subject that the sensitivity is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"Appointment.participant.individual", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointment" name:@"date" description:@"Appointment date/time." type:kSearchParamTypeDate compositeParams: nil path: @[@"Appointment.start", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointmentResponse" name:@"partstatus" description:@"The overall status of the appointment" type:kSearchParamTypeString compositeParams: nil path: @[@"AppointmentResponse.participantStatus", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointmentResponse" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointmentResponse" name:@"subject" description:@"The subject that the appointment response replies for" type:kSearchParamTypeReference compositeParams: nil path: @[@"AppointmentResponse.individual", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAppointmentResponse" name:@"appointment" description:@"The appointment that the response is attached to" type:kSearchParamTypeReference compositeParams: nil path: @[@"AppointmentResponse.appointment", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAvailability" name:@"individual" description:@"The individual to find an availability for" type:kSearchParamTypeReference compositeParams: nil path: @[@"Availability.individual", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAvailability" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRAvailability" name:@"slottype" description:@"The type of appointments that can be booked into associated slot(s)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Availability.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"activitycode" description:@"Detail type of activity" type:kSearchParamTypeToken compositeParams: nil path: @[@"CarePlan.activity.simple.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"patient" description:@"Who care plan is for" type:kSearchParamTypeReference compositeParams: nil path: @[@"CarePlan.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"condition" description:@"Health issues this plan addresses" type:kSearchParamTypeReference compositeParams: nil path: @[@"CarePlan.concern", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"activitydetail" description:@"Activity details defined in specific resource" type:kSearchParamTypeReference compositeParams: nil path: @[@"CarePlan.activity.detail", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"activitydate" description:@"Specified date occurs within period specified by CarePlan.activity.timingSchedule" type:kSearchParamTypeDate compositeParams: nil path: @[@"CarePlan.activity.simple.timing[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"participant" description:@"Who is involved" type:kSearchParamTypeReference compositeParams: nil path: @[@"CarePlan.participant.member", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCarePlan" name:@"date" description:@"Time period plan covers" type:kSearchParamTypeDate compositeParams: nil path: @[@"CarePlan.period", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"author" description:@"Who and/or what authored the composition" type:kSearchParamTypeReference compositeParams: nil path: @[@"Composition.author", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"attester" description:@"Who attested the composition" type:kSearchParamTypeReference compositeParams: nil path: @[@"Composition.attester.party", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"subject" description:@"Who and/or what the composition is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"Composition.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"section-content" description:@"The actual data for the section" type:kSearchParamTypeReference compositeParams: nil path: @[@"Composition.section.content", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"context" description:@"Code(s) that apply to the event being documented" type:kSearchParamTypeToken compositeParams: nil path: @[@"Composition.event.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"class" description:@"Categorization of Composition" type:kSearchParamTypeToken compositeParams: nil path: @[@"Composition.class", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"section-type" description:@"Classification of section (recommended)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Composition.section.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"type" description:@"Kind of composition (LOINC if possible)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Composition.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"identifier" description:@"Logical identifier of composition (version-independent)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Composition.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRComposition" name:@"instant" description:@"Composition editing time" type:kSearchParamTypeDate compositeParams: nil path: @[@"Composition.instant", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"dependson" description:@"Reference to element/field/valueset provides the context" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.concept.dependsOn.concept", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"status" description:@"Status of the concept map" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"date" description:@"The concept map publication date" type:kSearchParamTypeDate compositeParams: nil path: @[@"ConceptMap.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"version" description:@"The version identifier of the concept map" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.version", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"publisher" description:@"Name of the publisher of the concept map" type:kSearchParamTypeString compositeParams: nil path: @[@"ConceptMap.publisher", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"product" description:@"Reference to element/field/valueset provides the context" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.concept.map.product.concept", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"system" description:@"The system for any destination concepts mapped by this map" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.concept.map.system", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"source" description:@"The system for any concepts mapped by this concept map" type:kSearchParamTypeReference compositeParams: nil path: @[@"ConceptMap.source", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"description" description:@"Text search in the description of the concept map" type:kSearchParamTypeString compositeParams: nil path: @[@"ConceptMap.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"name" description:@"Name of the concept map" type:kSearchParamTypeString compositeParams: nil path: @[@"ConceptMap.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"target" description:@"Provides context to the mappings" type:kSearchParamTypeReference compositeParams: nil path: @[@"ConceptMap.target", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConceptMap" name:@"identifier" description:@"The identifier of the concept map" type:kSearchParamTypeToken compositeParams: nil path: @[@"ConceptMap.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"asserter" description:@"Person who asserts this condition" type:kSearchParamTypeReference compositeParams: nil path: @[@"Condition.asserter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"status" description:@"The status of the condition" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"location" description:@"Location - may include laterality" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.location.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"subject" description:@"Who has the condition?" type:kSearchParamTypeReference compositeParams: nil path: @[@"Condition.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"onset" description:@"When the Condition started (if started on a date)" type:kSearchParamTypeDate compositeParams: nil path: @[@"Condition.onset[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"evidence" description:@"Manifestation/symptom" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.evidence.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"severity" description:@"The severity of the condition" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.severity", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"code" description:@"Code for the condition" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"encounter" description:@"Encounter when condition first asserted" type:kSearchParamTypeReference compositeParams: nil path: @[@"Condition.encounter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"date-asserted" description:@"When first detected/suspected/entered" type:kSearchParamTypeDate compositeParams: nil path: @[@"Condition.dateAsserted", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"stage" description:@"Simple summary (disease specific)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.stage.summary", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"related-code" description:@"Relationship target by means of a predefined code" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.relatedItem.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"category" description:@"The category of the condition" type:kSearchParamTypeToken compositeParams: nil path: @[@"Condition.category", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRCondition" name:@"related-item" description:@"Relationship target resource" type:kSearchParamTypeReference compositeParams: nil path: @[@"Condition.relatedItem.target", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"status" description:@"The current status of the conformance statement" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"resource" description:@"Name of a resource mentioned in a conformance statement" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.rest.resource.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"security" description:@"Information about security of implementation" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.rest.security", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"format" description:@"formats supported (xml | json | mime type)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.format", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"date" description:@"The conformance statement publication date" type:kSearchParamTypeDate compositeParams: nil path: @[@"Conformance.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"version" description:@"The version identifier of the conformance statement" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.version", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"publisher" description:@"Name of the publisher of the conformance statement" type:kSearchParamTypeString compositeParams: nil path: @[@"Conformance.publisher", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"mode" description:@"Mode - restful (server/client) or messaging (sender/receiver)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.rest.mode", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"software" description:@"Part of a the name of a software application" type:kSearchParamTypeString compositeParams: nil path: @[@"Conformance.software.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"description" description:@"Text search in the description of the conformance statement" type:kSearchParamTypeString compositeParams: nil path: @[@"Conformance.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"event" description:@"Event code in a conformance statement" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.messaging.event.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"name" description:@"Name of the conformance statement" type:kSearchParamTypeString compositeParams: nil path: @[@"Conformance.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"supported-profile" description:@"Profiles supported by the system" type:kSearchParamTypeReference compositeParams: nil path: @[@"Conformance.profile", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"fhirversion" description:@"The version of FHIR" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.version", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"identifier" description:@"The identifier of the conformance statement" type:kSearchParamTypeToken compositeParams: nil path: @[@"Conformance.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRConformance" name:@"profile" description:@"A profile id invoked in a conformance statement" type:kSearchParamTypeReference compositeParams: nil path: @[@"Conformance.rest.resource.profile", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"organization" description:@"The organization responsible for the device" type:kSearchParamTypeReference compositeParams: nil path: @[@"Device.owner", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"model" description:@"The model of the device" type:kSearchParamTypeString compositeParams: nil path: @[@"Device.model", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"patient" description:@"Patient information, if the resource is affixed to a person" type:kSearchParamTypeReference compositeParams: nil path: @[@"Device.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"location" description:@"A location, where the resource is found" type:kSearchParamTypeReference compositeParams: nil path: @[@"Device.location", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"manufacturer" description:@"The manufacturer of the device" type:kSearchParamTypeString compositeParams: nil path: @[@"Device.manufacturer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"udi" description:@"FDA Mandated Unique Device Identifier" type:kSearchParamTypeString compositeParams: nil path: @[@"Device.udi", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"type" description:@"The type of the device" type:kSearchParamTypeToken compositeParams: nil path: @[@"Device.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDevice" name:@"identifier" description:@"Instance id from manufacturer, owner and others" type:kSearchParamTypeToken compositeParams: nil path: @[@"Device.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"observation" description:@"The data for the metric" type:kSearchParamTypeReference compositeParams: nil path: @[@"DeviceObservationReport.virtualDevice.channel.metric.observation", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"source" description:@"Identifies/describes where the data came from" type:kSearchParamTypeReference compositeParams: nil path: @[@"DeviceObservationReport.source", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"subject" description:@"Subject of the measurement" type:kSearchParamTypeReference compositeParams: nil path: @[@"DeviceObservationReport.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"code" description:@"The compatment code" type:kSearchParamTypeToken compositeParams: nil path: @[@"DeviceObservationReport.virtualDevice.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDeviceObservationReport" name:@"channel" description:@"The channel code" type:kSearchParamTypeToken compositeParams: nil path: @[@"DeviceObservationReport.virtualDevice.channel.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"orderer" description:@"Who ordered the test" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticOrder.orderer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"status" description:@"requested | received | accepted | in progress | review | completed | suspended | rejected | failed" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"subject" description:@"Who and/or what test is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticOrder.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"item-status" description:@"requested | received | accepted | in progress | review | completed | suspended | rejected | failed" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.item.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"event-status" description:@"requested | received | accepted | in progress | review | completed | suspended | rejected | failed" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.event.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"actor" description:@"Who recorded or did this" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticOrder.event.actor", @"DiagnosticOrder.item.event.actor", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"code" description:@"Code to indicate the item (test or panel) being ordered" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.item.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"encounter" description:@"The encounter that this diagnostic order is associated with" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticOrder.encounter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"item-past-status" description:@"requested | received | accepted | in progress | review | completed | suspended | rejected | failed" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.item.event.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"bodysite" description:@"Location of requested test (if applicable)" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.item.bodySite", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"item-date" description:@"The date at which the event happened" type:kSearchParamTypeDate compositeParams: nil path: @[@"DiagnosticOrder.item.event.dateTime", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"specimen" description:@"If the whole order relates to specific specimens" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticOrder.specimen", @"DiagnosticOrder.item.specimen", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"event-status-date" description:@"A combination of past-status and date" type:kSearchParamTypeComposite compositeParams: @[@"event-status", @"event-date", ] path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"event-date" description:@"The date at which the event happened" type:kSearchParamTypeDate compositeParams: nil path: @[@"DiagnosticOrder.event.dateTime", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"identifier" description:@"Identifiers assigned to this order" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticOrder.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticOrder" name:@"item-status-date" description:@"A combination of item-past-status and item-date" type:kSearchParamTypeComposite compositeParams: @[@"item-past-status", @"item-date", ] path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"result" description:@"Link to an atomic result (observation resource)" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.results.result", @"DiagnosticReport.results.group.result", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"status" description:@"The status of the report" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticReport.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"subject" description:@"The subject of the report" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"issued" description:@"When the report was issued" type:kSearchParamTypeDate compositeParams: nil path: @[@"DiagnosticReport.issued", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"diagnosis" description:@"A coded diagnosis on the report" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticReport.codedDiagnosis", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"image" description:@"Reference to the image source" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.image.link", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"date" description:@"The clinically relevant time of the report" type:kSearchParamTypeDate compositeParams: nil path: @[@"DiagnosticReport.diagnostic[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"request" description:@"What was requested" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.requestDetail", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"specimen" description:@"The specimen details" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.results.specimen", @"DiagnosticReport.results.group.specimen", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"name" description:@"The name of the report (e.g. the code for the report as a whole, as opposed to codes for the atomic results, which are the names on the observation resource referred to from the result)" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticReport.results.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"service" description:@"Which diagnostic discipline/department created the report" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticReport.serviceCategory", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"performer" description:@"Who was the source of the report (organization)" type:kSearchParamTypeReference compositeParams: nil path: @[@"DiagnosticReport.performer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"group" description:@"Name /code of a group in the report" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDiagnosticReport" name:@"identifier" description:@"An identifier for the report" type:kSearchParamTypeToken compositeParams: nil path: @[@"DiagnosticReport.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"content" description:@"Contents of this set of documents" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentManifest.content", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"author" description:@"Who and/or what authored the document" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentManifest.author", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"supersedes" description:@"If this document manifest replaces another" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentManifest.supercedes", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"status" description:@"current | superceded | entered in error" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentManifest.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"created" description:@"When this document manifest created" type:kSearchParamTypeDate compositeParams: nil path: @[@"DocumentManifest.created", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"confidentiality" description:@"Sensitivity of set of documents" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentManifest.confidentiality", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"description" description:@"Human-readable description (title)" type:kSearchParamTypeString compositeParams: nil path: @[@"DocumentManifest.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"subject" description:@"The subject of the set of documents" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentManifest.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"type" description:@"What kind of document set this is" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentManifest.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"identifier" description:@"Unique Identifier for the set of documents" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentManifest.masterIdentifier", @"DocumentManifest.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentManifest" name:@"recipient" description:@"Intended to get notified about this set of documents" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentManifest.recipient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"location" description:@"Where to access the document" type:kSearchParamTypeString compositeParams: nil path: @[@"DocumentReference.location", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"indexed" description:@"When this document reference created" type:kSearchParamTypeDate compositeParams: nil path: @[@"DocumentReference.indexed", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"status" description:@"current | superceded | entered in error" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"subject" description:@"Who|what is the subject of the document" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentReference.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"relatesto" description:@"Target of the relationship" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentReference.relatesTo.target", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"relation" description:@"replaces | transforms | signs | appends" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.relatesTo.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"class" description:@"Categorization of Document" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.class", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"format" description:@"Format/content rules for the document" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.format", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"period" description:@"Time of service that is being documented" type:kSearchParamTypeDate compositeParams: nil path: @[@"DocumentReference.context.period", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"type" description:@"What kind of document this is (LOINC if possible)" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"authenticator" description:@"Who/What authenticated the document" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentReference.authenticator", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"size" description:@"Size of the document in bytes" type:kSearchParamTypeNumber compositeParams: nil path: @[@"DocumentReference.size", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"relationship" description:@"Combination of relation and relatesTo" type:kSearchParamTypeComposite compositeParams: @[@"relatesto", @"relation", ] path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"author" description:@"Who and/or what authored the document" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentReference.author", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"custodian" description:@"Org which maintains the document" type:kSearchParamTypeReference compositeParams: nil path: @[@"DocumentReference.custodian", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"facility" description:@"Kind of facility where patient was seen" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.context.facilityType", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"created" description:@"Document creation time" type:kSearchParamTypeDate compositeParams: nil path: @[@"DocumentReference.created", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"event" description:@"Main Clinical Acts Documented" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.context.event", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"confidentiality" description:@"Sensitivity of source document" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.confidentiality", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"description" description:@"Human-readable description (title)" type:kSearchParamTypeString compositeParams: nil path: @[@"DocumentReference.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"language" description:@"The marked primary language for the document" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.primaryLanguage", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRDocumentReference" name:@"identifier" description:@"Master Version Specific Identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"DocumentReference.masterIdentifier", @"DocumentReference.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"location" description:@"Location the encounter takes place" type:kSearchParamTypeReference compositeParams: nil path: @[@"Encounter.location.location", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"status" description:@"planned | in progress | onleave | finished | cancelled" type:kSearchParamTypeToken compositeParams: nil path: @[@"Encounter.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"subject" description:@"The patient present at the encounter" type:kSearchParamTypeReference compositeParams: nil path: @[@"Encounter.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"indication" description:@"Reason the encounter takes place (resource)" type:kSearchParamTypeReference compositeParams: nil path: @[@"Encounter.indication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"length" description:@"Length of encounter in days" type:kSearchParamTypeNumber compositeParams: nil path: @[@"Encounter.length", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"date" description:@"A date within the period the Encounter lasted" type:kSearchParamTypeDate compositeParams: nil path: @[@"Encounter.period", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"identifier" description:@"Identifier(s) by which this encounter is known" type:kSearchParamTypeToken compositeParams: nil path: @[@"Encounter.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIREncounter" name:@"location-period" description:@"Time period during which the patient was present at the location" type:kSearchParamTypeDate compositeParams: nil path: @[@"Encounter.location.period", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRFamilyHistory" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRFamilyHistory" name:@"subject" description:@"The identity of a subject to list family history items for" type:kSearchParamTypeReference compositeParams: nil path: @[@"FamilyHistory.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"member" description:@"Who is in group" type:kSearchParamTypeReference compositeParams: nil path: @[@"Group.member", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"characteristic-value" description:@"A composite of both characteristic and value" type:kSearchParamTypeComposite compositeParams: @[@"characteristic", @"value", ] path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"value" description:@"Value held by characteristic" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.characteristic.value[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"actual" description:@"Descriptive or actual" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.actual", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"exclude" description:@"Group includes or excludes" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.characteristic.exclude", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"code" description:@"The kind of resources contained" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"characteristic" description:@"Kind of characteristic" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.characteristic.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"type" description:@"The type of resources the group contains" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRGroup" name:@"identifier" description:@"Unique id" type:kSearchParamTypeToken compositeParams: nil path: @[@"Group.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"uid" description:@"Formal identifier for this instance (0008,0018)" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.series.instance.uid", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"series" description:@"The series id for the image" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.series.uid", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"bodysite" description:@"Body part examined (Map from 0018,0015)" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.series.bodySite", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"subject" description:@"Who the study is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"ImagingStudy.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"accession" description:@"The accession id for the image" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.accessionNo", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"study" description:@"The study id for the image" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.uid", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"modality" description:@"The modality of the image" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.series.modality", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"date" description:@"The date the study was done was taken" type:kSearchParamTypeDate compositeParams: nil path: @[@"ImagingStudy.dateTime", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"dicom-class" description:@"DICOM class type (0008,0016)" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImagingStudy.series.instance.sopclass", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImagingStudy" name:@"size" description:@"The size of the image in MB - may include > or < in the value" type:kSearchParamTypeNumber compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"reaction" description:@"Additional information on reaction" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.reaction.detail", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"requester" description:@"The practitioner who ordered the vaccination" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.requester", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"dose-sequence" description:@"What dose number within series?" type:kSearchParamTypeNumber compositeParams: nil path: @[@"Immunization.vaccinationProtocol.doseSequence", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"vaccine-type" description:@"Vaccine Product Type Administered" type:kSearchParamTypeToken compositeParams: nil path: @[@"Immunization.vaccineType", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"location" description:@"The service delivery location or facility in which the vaccine was / was to be administered" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.location", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"reason" description:@"Why immunization occurred" type:kSearchParamTypeToken compositeParams: nil path: @[@"Immunization.explanation.reason", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"subject" description:@"The subject of the vaccination event / refusal" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"reaction-date" description:@"When did reaction start?" type:kSearchParamTypeDate compositeParams: nil path: @[@"Immunization.reaction.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"date" description:@"Vaccination  Administration / Refusal Date" type:kSearchParamTypeDate compositeParams: nil path: @[@"Immunization.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"lot-number" description:@"Vaccine Lot Number" type:kSearchParamTypeString compositeParams: nil path: @[@"Immunization.lotNumber", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"manufacturer" description:@"Vaccine Manufacturer" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.manufacturer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"performer" description:@"The practitioner who administered the vaccination" type:kSearchParamTypeReference compositeParams: nil path: @[@"Immunization.performer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"refusal-reason" description:@"Explanation of refusal / exemption" type:kSearchParamTypeToken compositeParams: nil path: @[@"Immunization.explanation.refusalReason", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"refused" description:@"Was immunization refused?" type:kSearchParamTypeToken compositeParams: nil path: @[@"Immunization.refusedIndicator", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunization" name:@"identifier" description:@"Business identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"Immunization.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"information" description:@"Patient observations supporting recommendation" type:kSearchParamTypeReference compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.supportingPatientInformation", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"dose-sequence" description:@"Number of dose within sequence" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.protocol.doseSequence", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"support" description:@"Past immunizations supporting recommendation" type:kSearchParamTypeReference compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.supportingImmunization", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"vaccine-type" description:@"Vaccine recommendation applies to" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.vaccineType", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"status" description:@"Vaccine administration status" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.forecastStatus", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"dose-number" description:@"Recommended dose number" type:kSearchParamTypeNumber compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.doseNumber", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"subject" description:@"Who this profile is for" type:kSearchParamTypeReference compositeParams: nil path: @[@"ImmunizationRecommendation.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"date" description:@"Date recommendation created" type:kSearchParamTypeDate compositeParams: nil path: @[@"ImmunizationRecommendation.recommendation.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRImmunizationRecommendation" name:@"identifier" description:@"Business identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"ImmunizationRecommendation.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"source" description:@"Who and/or what defined the list contents" type:kSearchParamTypeReference compositeParams: nil path: @[@"List.source", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"subject" description:@"If all resources have the same subject" type:kSearchParamTypeReference compositeParams: nil path: @[@"List.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"item" description:@"Actual entry" type:kSearchParamTypeReference compositeParams: nil path: @[@"List.entry.item", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"code" description:@"What the purpose of this list is" type:kSearchParamTypeToken compositeParams: nil path: @[@"List.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"date" description:@"When the list was prepared" type:kSearchParamTypeDate compositeParams: nil path: @[@"List.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRList" name:@"empty-reason" description:@"Why list is empty" type:kSearchParamTypeToken compositeParams: nil path: @[@"List.emptyReason", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"near" description:@"The coordinates expressed as [lat],[long] (using KML, see notes) to find locations near to (servers may search using a square rather than a circle for efficiency)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"partof" description:@"The location of which this location is a part" type:kSearchParamTypeReference compositeParams: nil path: @[@"Location.partOf", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"status" description:@"Searches for locations with a specific kind of status" type:kSearchParamTypeToken compositeParams: nil path: @[@"Location.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"address" description:@"A (part of the) address of the location" type:kSearchParamTypeString compositeParams: nil path: @[@"Location.address", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"name" description:@"A (portion of the) name of the location" type:kSearchParamTypeString compositeParams: nil path: @[@"Location.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"near-distance" description:@"A distance quantity to limit the near search to locations within a specific distance" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"type" description:@"A code for the type of location" type:kSearchParamTypeToken compositeParams: nil path: @[@"Location.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRLocation" name:@"identifier" description:@"Unique code or number identifying the location to its users" type:kSearchParamTypeToken compositeParams: nil path: @[@"Location.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"subject" description:@"Who/What this Media is a record of" type:kSearchParamTypeReference compositeParams: nil path: @[@"Media.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"subtype" description:@"The type of acquisition equipment/process" type:kSearchParamTypeToken compositeParams: nil path: @[@"Media.subtype", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"view" description:@"Imaging view e.g Lateral or Antero-posterior" type:kSearchParamTypeToken compositeParams: nil path: @[@"Media.view", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"date" description:@"When the media was taken/recorded (end)" type:kSearchParamTypeDate compositeParams: nil path: @[@"Media.dateTime", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"type" description:@"photo | video | audio" type:kSearchParamTypeToken compositeParams: nil path: @[@"Media.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"identifier" description:@"Identifier(s) for the image" type:kSearchParamTypeToken compositeParams: nil path: @[@"Media.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedia" name:@"operator" description:@"The person who generated the image" type:kSearchParamTypeReference compositeParams: nil path: @[@"Media.operator", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"content" description:@"A product in the package" type:kSearchParamTypeReference compositeParams: nil path: @[@"Medication.package.content.item", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"form" description:@"powder | tablets | carton +" type:kSearchParamTypeToken compositeParams: nil path: @[@"Medication.product.form", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"container" description:@"E.g. box, vial, blister-pack" type:kSearchParamTypeToken compositeParams: nil path: @[@"Medication.package.container", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"manufacturer" description:@"Manufacturer of the item" type:kSearchParamTypeReference compositeParams: nil path: @[@"Medication.manufacturer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"name" description:@"Common / Commercial name" type:kSearchParamTypeString compositeParams: nil path: @[@"Medication.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"ingredient" description:@"The product contained" type:kSearchParamTypeReference compositeParams: nil path: @[@"Medication.product.ingredient.item", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedication" name:@"code" description:@"Codes that identify this medication" type:kSearchParamTypeToken compositeParams: nil path: @[@"Medication.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"medication" description:@"Return administrations of this medication" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationAdministration.medication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"patient" description:@"The identity of a patient to list administrations  for" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationAdministration.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"status" description:@"MedicationAdministration event status (for example one of active/paused/completed/nullified)" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationAdministration.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"prescription" description:@"The identity of a prescription to list administrations from" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationAdministration.prescription", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"device" description:@"Return administrations with this administration device identity" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationAdministration.device", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"notgiven" description:@"Administrations that were not made" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationAdministration.wasNotGiven", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"whengiven" description:@"Date of administration" type:kSearchParamTypeDate compositeParams: nil path: @[@"MedicationAdministration.whenGiven", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"encounter" description:@"Return administrations that share this encounter" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationAdministration.encounter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationAdministration" name:@"identifier" description:@"Return administrations with this external identity" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationAdministration.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"medication" description:@"Returns dispenses of this medicine" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.dispense.medication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"patient" description:@"The identity of a patient to list dispenses  for" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"status" description:@"Status of the dispense" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationDispense.dispense.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"prescription" description:@"The identity of a prescription to list dispenses from" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.authorizingPrescription", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"responsibleparty" description:@"Return all dispenses with the specified responsible party" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.substitution.responsibleParty", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"dispenser" description:@"Return all dispenses performed by a specific indiividual" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.dispenser", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"type" description:@"Return all dispenses of a specific type" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationDispense.dispense.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"identifier" description:@"Return dispenses with this external identity" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationDispense.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"whenprepared" description:@"Date when medication prepared" type:kSearchParamTypeDate compositeParams: nil path: @[@"MedicationDispense.dispense.whenPrepared", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"whenhandedover" description:@"Date when medication handed over to patient (outpatient setting), or supplied to ward or clinic (inpatient setting)" type:kSearchParamTypeDate compositeParams: nil path: @[@"MedicationDispense.dispense.whenHandedOver", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationDispense" name:@"destination" description:@"Return dispenses that should be sent to a secific destination" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationDispense.dispense.destination", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"medication" description:@"Code for medicine or text in medicine name" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationPrescription.medication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"datewritten" description:@"Return prescriptions written on this date" type:kSearchParamTypeDate compositeParams: nil path: @[@"MedicationPrescription.dateWritten", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"patient" description:@"The identity of a patient to list dispenses  for" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationPrescription.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"status" description:@"Status of the prescription" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationPrescription.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"encounter" description:@"Return prescriptions with this encounter identity" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationPrescription.encounter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationPrescription" name:@"identifier" description:@"Return prescriptions with this external identity" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationPrescription.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"medication" description:@"Code for medicine or text in medicine name" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationStatement.medication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"patient" description:@"The identity of a patient to list administrations  for" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationStatement.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"device" description:@"Return administrations with this administration device identity" type:kSearchParamTypeReference compositeParams: nil path: @[@"MedicationStatement.device", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"when-given" description:@"Date of administration" type:kSearchParamTypeDate compositeParams: nil path: @[@"MedicationStatement.whenGiven", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMedicationStatement" name:@"identifier" description:@"Return administrations with this external identity" type:kSearchParamTypeToken compositeParams: nil path: @[@"MedicationStatement.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRMessageHeader" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"status" description:@"The status of the observation" type:kSearchParamTypeToken compositeParams: nil path: @[@"Observation.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"subject" description:@"The subject that the observation is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"Observation.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"specimen" description:@"Specimen used for this observation" type:kSearchParamTypeReference compositeParams: nil path: @[@"Observation.specimen", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"name" description:@"The name of the observation type" type:kSearchParamTypeToken compositeParams: nil path: @[@"Observation.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"reliability" description:@"The reliability of the observation" type:kSearchParamTypeToken compositeParams: nil path: @[@"Observation.reliability", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"value" description:@"The code or value of a result" type:kSearchParamTypeToken compositeParams: nil path: @[@"Observation.value[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"performer" description:@"Who and/or what performed the observation" type:kSearchParamTypeReference compositeParams: nil path: @[@"Observation.performer", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"name-value" description:@"Both name and value" type:kSearchParamTypeComposite compositeParams: @[@"name", @"value", ] path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRObservation" name:@"date" description:@"Obtained date/time. If the obtained element is a period, a date that falls in the period" type:kSearchParamTypeDate compositeParams: nil path: @[@"Observation.applies[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROperationOutcome" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"authority" description:@"If required by policy" type:kSearchParamTypeReference compositeParams: nil path: @[@"Order.authority", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"detail" description:@"What action is being ordered" type:kSearchParamTypeReference compositeParams: nil path: @[@"Order.detail", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"source" description:@"Who initiated the order" type:kSearchParamTypeReference compositeParams: nil path: @[@"Order.source", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"subject" description:@"Patient this order is about" type:kSearchParamTypeReference compositeParams: nil path: @[@"Order.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"when" description:@"A formal schedule" type:kSearchParamTypeDate compositeParams: nil path: @[@"Order.when.schedule", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"target" description:@"Who is intended to fulfill the order" type:kSearchParamTypeReference compositeParams: nil path: @[@"Order.target", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"when_code" description:@"Code specifies when request should be done. The code may simply be a priority code" type:kSearchParamTypeToken compositeParams: nil path: @[@"Order.when.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrder" name:@"date" description:@"When the order was made" type:kSearchParamTypeDate compositeParams: nil path: @[@"Order.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"fulfillment" description:@"Details of the outcome of performing the order" type:kSearchParamTypeReference compositeParams: nil path: @[@"OrderResponse.fulfillment", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"request" description:@"The order that this is a response to" type:kSearchParamTypeReference compositeParams: nil path: @[@"OrderResponse.request", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"code" description:@"pending | review | rejected | error | accepted | cancelled | replaced | aborted | complete" type:kSearchParamTypeToken compositeParams: nil path: @[@"OrderResponse.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"date" description:@"When the response was made" type:kSearchParamTypeDate compositeParams: nil path: @[@"OrderResponse.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrderResponse" name:@"who" description:@"Who made the response" type:kSearchParamTypeReference compositeParams: nil path: @[@"OrderResponse.who", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"phonetic" description:@"A portion of the organization's name using some kind of phonetic matching algorithm" type:kSearchParamTypeString compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"partof" description:@"Search all organizations that are part of the given organization" type:kSearchParamTypeReference compositeParams: nil path: @[@"Organization.partOf", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"name" description:@"A portion of the organization's name" type:kSearchParamTypeString compositeParams: nil path: @[@"Organization.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"active" description:@"Whether the organization's record is active" type:kSearchParamTypeToken compositeParams: nil path: @[@"Organization.active", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"type" description:@"A code for the type of organization" type:kSearchParamTypeToken compositeParams: nil path: @[@"Organization.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROrganization" name:@"identifier" description:@"Any identifier for the organization (not the accreditation issuer's identifier)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Organization.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROther" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROther" name:@"created" description:@"When created" type:kSearchParamTypeDate compositeParams: nil path: @[@"Other.created", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROther" name:@"subject" description:@"Identifies the" type:kSearchParamTypeReference compositeParams: nil path: @[@"Other.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIROther" name:@"code" description:@"Kind of Resource" type:kSearchParamTypeToken compositeParams: nil path: @[@"Other.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"animal-breed" description:@"The breed for animal patients" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.animal.breed", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"phonetic" description:@"A portion of either family or given name using some kind of phonetic matching algorithm" type:kSearchParamTypeString compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"link" description:@"All patients linked to the given patient" type:kSearchParamTypeReference compositeParams: nil path: @[@"Patient.link.other", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"provider" description:@"The organization at which this person is a patient" type:kSearchParamTypeReference compositeParams: nil path: @[@"Patient.managingOrganization", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"animal-species" description:@"The species for animal patients" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.animal.species", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"given" description:@"A portion of the given name of the patient" type:kSearchParamTypeString compositeParams: nil path: @[@"Patient.name.given", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"address" description:@"An address in any kind of address/part of the patient" type:kSearchParamTypeString compositeParams: nil path: @[@"Patient.address", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"family" description:@"A portion of the family name of the patient" type:kSearchParamTypeString compositeParams: nil path: @[@"Patient.name.family", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"name" description:@"A portion of either family or given name of the patient" type:kSearchParamTypeString compositeParams: nil path: @[@"Patient.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"telecom" description:@"The value in any kind of telecom details of the patient" type:kSearchParamTypeString compositeParams: nil path: @[@"Patient.telecom", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"birthdate" description:@"The patient's date of birth" type:kSearchParamTypeDate compositeParams: nil path: @[@"Patient.birthDate", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"gender" description:@"Gender of the patient" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.gender", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"active" description:@"Whether the patient record is active" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.active", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"language" description:@"Language code (irrespective of use value)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.communication", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPatient" name:@"identifier" description:@"A patient identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"Patient.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"organization" description:@"The identity of the organization the practitioner represents / acts on behalf of" type:kSearchParamTypeReference compositeParams: nil path: @[@"Practitioner.organization", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"phonetic" description:@"A portion of either family or given name using some kind of phonetic matching algorithm" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"given" description:@"A portion of the given name" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"address" description:@"An address in any kind of address/part" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.address", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"family" description:@"A portion of the family name" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"name" description:@"A portion of either family or given name" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"telecom" description:@"The value in any kind of contact" type:kSearchParamTypeString compositeParams: nil path: @[@"Practitioner.telecom", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"gender" description:@"Gender of the practitioner" type:kSearchParamTypeToken compositeParams: nil path: @[@"Practitioner.gender", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRPractitioner" name:@"identifier" description:@"A practitioner's Identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"Practitioner.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProcedure" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProcedure" name:@"subject" description:@"The identity of a patient to list procedures  for" type:kSearchParamTypeReference compositeParams: nil path: @[@"Procedure.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProcedure" name:@"date" description:@"The date the procedure was performed on" type:kSearchParamTypeDate compositeParams: nil path: @[@"Procedure.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProcedure" name:@"type" description:@"Type of procedure" type:kSearchParamTypeToken compositeParams: nil path: @[@"Procedure.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"extension" description:@"An extension code (use or definition)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.extensionDefn.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"valueset" description:@"A vocabulary binding code" type:kSearchParamTypeReference compositeParams: nil path: @[@"Profile.structure.element.definition.binding.reference[x]", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"status" description:@"The current status of the profile" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"description" description:@"Text search in the description of the profile" type:kSearchParamTypeString compositeParams: nil path: @[@"Profile.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"name" description:@"Name of the profile" type:kSearchParamTypeString compositeParams: nil path: @[@"Profile.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"code" description:@"A code for the profile in the format uri::code (server may choose to do subsumption)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"type" description:@"Type of resource that is constrained in the profile" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.structure.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"date" description:@"The profile publication date" type:kSearchParamTypeDate compositeParams: nil path: @[@"Profile.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"identifier" description:@"The identifier of the profile" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"publisher" description:@"Name of the publisher of the profile" type:kSearchParamTypeString compositeParams: nil path: @[@"Profile.publisher", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProfile" name:@"version" description:@"The version identifier of the profile" type:kSearchParamTypeToken compositeParams: nil path: @[@"Profile.version", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"location" description:@"Where the activity occurred, if relevant" type:kSearchParamTypeReference compositeParams: nil path: @[@"Provenance.location", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"start" description:@"Starting time with inclusive boundary" type:kSearchParamTypeDate compositeParams: nil path: @[@"Provenance.period.start", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"partytype" description:@"e.g. Resource | Person | Application | Record | Document +" type:kSearchParamTypeToken compositeParams: nil path: @[@"Provenance.agent.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"target" description:@"Target resource(s) (usually version specific)" type:kSearchParamTypeReference compositeParams: nil path: @[@"Provenance.target", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"party" description:@"Identity of agent (urn or url)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Provenance.agent.reference", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRProvenance" name:@"end" description:@"End time with inclusive boundary, if not ongoing" type:kSearchParamTypeDate compositeParams: nil path: @[@"Provenance.period.end", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuery" name:@"response" description:@"Links response to source query" type:kSearchParamTypeToken compositeParams: nil path: @[@"Query.response.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuery" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuery" name:@"identifier" description:@"Links query and its response(s)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Query.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"author" description:@"The author of the questionnaire" type:kSearchParamTypeReference compositeParams: nil path: @[@"Questionnaire.author", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"authored" description:@"When the questionnaire was authored" type:kSearchParamTypeDate compositeParams: nil path: @[@"Questionnaire.authored", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"status" description:@"The status of the questionnaire" type:kSearchParamTypeToken compositeParams: nil path: @[@"Questionnaire.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"subject" description:@"The subject of the questionnaire" type:kSearchParamTypeReference compositeParams: nil path: @[@"Questionnaire.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"name" description:@"Name of the questionnaire" type:kSearchParamTypeToken compositeParams: nil path: @[@"Questionnaire.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"encounter" description:@"Encounter during which questionnaire was authored" type:kSearchParamTypeReference compositeParams: nil path: @[@"Questionnaire.encounter", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRQuestionnaire" name:@"identifier" description:@"An identifier for the questionnaire" type:kSearchParamTypeToken compositeParams: nil path: @[@"Questionnaire.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"patient" description:@"The patient this person is related to" type:kSearchParamTypeReference compositeParams: nil path: @[@"RelatedPerson.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"phonetic" description:@"A portion of name using some kind of phonetic matching algorithm" type:kSearchParamTypeString compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"address" description:@"An address in any kind of address/part" type:kSearchParamTypeString compositeParams: nil path: @[@"RelatedPerson.address", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"name" description:@"A portion of name in any name part" type:kSearchParamTypeString compositeParams: nil path: @[@"RelatedPerson.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"telecom" description:@"The value in any kind of contact" type:kSearchParamTypeString compositeParams: nil path: @[@"RelatedPerson.telecom", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"gender" description:@"Gender of the person" type:kSearchParamTypeToken compositeParams: nil path: @[@"RelatedPerson.gender", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRRelatedPerson" name:@"identifier" description:@"A patient Identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"RelatedPerson.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"site" description:@"Logical source location within the enterprise" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.source.site", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"desc" description:@"Instance-specific descriptor for Object" type:kSearchParamTypeString compositeParams: nil path: @[@"SecurityEvent.object.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"type" description:@"Type/identifier of event" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.event.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"date" description:@"Time when the event occurred on source" type:kSearchParamTypeDate compositeParams: nil path: @[@"SecurityEvent.event.dateTime", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"reference" description:@"Specific instance of resource (e.g. versioned)" type:kSearchParamTypeReference compositeParams: nil path: @[@"SecurityEvent.object.reference", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"identity" description:@"Specific instance of object (e.g. versioned)" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.object.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"altid" description:@"Alternative User id e.g. authentication" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.participant.altId", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"patientid" description:@"The id of the patient (one of multiple kinds of participations)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"source" description:@"The id of source where event originated" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.source.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"address" description:@"Identifier for the network access point of the user device" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.participant.network.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"subtype" description:@"More specific type/id for the event" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.event.subtype", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"name" description:@"Human-meaningful name for the user" type:kSearchParamTypeString compositeParams: nil path: @[@"SecurityEvent.participant.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"action" description:@"Type of action performed during the event" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.event.action", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"object-type" description:@"Object type being audited" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.object.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSecurityEvent" name:@"user" description:@"Unique identifier for the user" type:kSearchParamTypeToken compositeParams: nil path: @[@"SecurityEvent.participant.userId", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSlot" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSlot" name:@"start" description:@"Appointment date/time." type:kSearchParamTypeDate compositeParams: nil path: @[@"Slot.start", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSlot" name:@"slottype" description:@"The type of appointments that can be booked into the slot" type:kSearchParamTypeToken compositeParams: nil path: @[@"Slot.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSlot" name:@"fbtype" description:@"The free/busy status of the appointment" type:kSearchParamTypeToken compositeParams: nil path: @[@"Slot.freeBusyType", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSlot" name:@"availability" description:@"The Availability Resource that we are seeking a slot within" type:kSearchParamTypeReference compositeParams: nil path: @[@"Slot.availability", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSpecimen" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSpecimen" name:@"subject" description:@"The subject of the specimen" type:kSearchParamTypeReference compositeParams: nil path: @[@"Specimen.subject", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"substance" description:@"A component of the substance" type:kSearchParamTypeReference compositeParams: nil path: @[@"Substance.ingredient.substance", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"quantity" description:@"Amount of substance in the package" type:kSearchParamTypeNumber compositeParams: nil path: @[@"Substance.instance.quantity", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"type" description:@"The type of the substance" type:kSearchParamTypeToken compositeParams: nil path: @[@"Substance.type", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"identifier" description:@"Identifier of the package/container" type:kSearchParamTypeToken compositeParams: nil path: @[@"Substance.instance.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSubstance" name:@"expiry" description:@"When no longer valid to use" type:kSearchParamTypeDate compositeParams: nil path: @[@"Substance.instance.expiry", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"patient" description:@"Patient for whom the item is supplied" type:kSearchParamTypeReference compositeParams: nil path: @[@"Supply.patient", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"status" description:@"requested | dispensed | received | failed | cancelled" type:kSearchParamTypeToken compositeParams: nil path: @[@"Supply.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"dispenseid" description:@"External identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"Supply.dispense.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"identifier" description:@"Unique identifier" type:kSearchParamTypeToken compositeParams: nil path: @[@"Supply.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"supplier" description:@"Dispenser" type:kSearchParamTypeReference compositeParams: nil path: @[@"Supply.dispense.supplier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"kind" description:@"The kind of supply (central, non-stock, etc)" type:kSearchParamTypeToken compositeParams: nil path: @[@"Supply.kind", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRSupply" name:@"dispensestatus" description:@"in progress | dispensed | abandoned" type:kSearchParamTypeToken compositeParams: nil path: @[@"Supply.dispense.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"system" description:@"The system for any codes defined by this value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.define.system", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"_id" description:@"The logical resource id associated with the resource (must be supported by all servers)" type:kSearchParamTypeToken compositeParams: nil path: nil], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"status" description:@"The status of the value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.status", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"description" description:@"Text search in the description of the value set" type:kSearchParamTypeString compositeParams: nil path: @[@"ValueSet.description", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"name" description:@"The name of the value set" type:kSearchParamTypeString compositeParams: nil path: @[@"ValueSet.name", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"code" description:@"A code defined in the value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.define.concept.code", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"date" description:@"The value set publication date" type:kSearchParamTypeDate compositeParams: nil path: @[@"ValueSet.date", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"identifier" description:@"The identifier of the value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.identifier", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"reference" description:@"A code system included or excluded in the value set or an imported value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.compose.include.system", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"publisher" description:@"Name of the publisher of the value set" type:kSearchParamTypeString compositeParams: nil path: @[@"ValueSet.publisher", ]], 
            [[FHIRSearchParamDefinition alloc] initWithResource:@"FHIRValueSet" name:@"version" description:@"The version identifier of the value set" type:kSearchParamTypeToken compositeParams: nil path: @[@"ValueSet.version", ]], 
        ]];
        
    }
    return self;
}

@end
