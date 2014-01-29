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
  

 * Generated on Thu, Jan 30, 2014 05:26+1100 for FHIR v0.12
 */

#import "FHIRBindings.h"

#import "FHIRAddress.h"
#import "FHIRContact.h"
#import "FHIRHumanName.h"
#import "FHIRIdentifier.h"
#import "FHIRNarrative.h"
#import "FHIRQuantity.h"
#import "FHIRSchedule.h"
#import "FHIRAdverseReaction.h"
#import "FHIRAlert.h"
#import "FHIRAllergyIntolerance.h"
#import "FHIRCarePlan.h"
#import "FHIRComposition.h"
#import "FHIRConceptMap.h"
#import "FHIRCondition.h"
#import "FHIRConformance.h"
#import "FHIRDiagnosticOrder.h"
#import "FHIRDiagnosticReport.h"
#import "FHIRDocumentReference.h"
#import "FHIREncounter.h"
#import "FHIRGroup.h"
#import "FHIRImagingStudy.h"
#import "FHIRList.h"
#import "FHIRLocation.h"
#import "FHIRMedia.h"
#import "FHIRMedication.h"
#import "FHIRMedicationAdministration.h"
#import "FHIRMedicationDispense.h"
#import "FHIRMedicationPrescription.h"
#import "FHIRMessageHeader.h"
#import "FHIRObservation.h"
#import "FHIROperationOutcome.h"
#import "FHIROrderResponse.h"
#import "FHIRPatient.h"
#import "FHIRProcedure.h"
#import "FHIRProfile.h"
#import "FHIRProvenance.h"
#import "FHIRQuery.h"
#import "FHIRQuestionnaire.h"
#import "FHIRSecurityEvent.h"
#import "FHIRSpecimen.h"
#import "FHIRSupply.h"
#import "FHIRValueSet.h"

@interface FHIRBindingsHelper : NSObject

+ (kDataAbsentReason )parseDataAbsentReasonString:(NSString *)value;
+ (NSString *)stringDataAbsentReason:(kDataAbsentReason )name;


+ (kSpecialValues )parseSpecialValuesString:(NSString *)value;
+ (NSString *)stringSpecialValues:(kSpecialValues )name;


+ (kResourceType )parseResourceTypeString:(NSString *)value;
+ (NSString *)stringResourceType:(kResourceType )name;


+ (kAddressUse )parseAddressUseString:(NSString *)value;
+ (NSString *)stringAddressUse:(kAddressUse )name;


+ (kContactSystem )parseContactSystemString:(NSString *)value;
+ (NSString *)stringContactSystem:(kContactSystem )name;


+ (kContactUse )parseContactUseString:(NSString *)value;
+ (NSString *)stringContactUse:(kContactUse )name;


+ (kNameUse )parseNameUseString:(NSString *)value;
+ (NSString *)stringNameUse:(kNameUse )name;


+ (kIdentifierUse )parseIdentifierUseString:(NSString *)value;
+ (NSString *)stringIdentifierUse:(kIdentifierUse )name;


+ (kNarrativeStatus )parseNarrativeStatusString:(NSString *)value;
+ (NSString *)stringNarrativeStatus:(kNarrativeStatus )name;


+ (kQuantityCompararator )parseQuantityCompararatorString:(NSString *)value;
+ (NSString *)stringQuantityCompararator:(kQuantityCompararator )name;


+ (kUnitsOfTime )parseUnitsOfTimeString:(NSString *)value;
+ (NSString *)stringUnitsOfTime:(kUnitsOfTime )name;


+ (kEventTiming )parseEventTimingString:(NSString *)value;
+ (NSString *)stringEventTiming:(kEventTiming )name;


+ (kReactionSeverity )parseReactionSeverityString:(NSString *)value;
+ (NSString *)stringReactionSeverity:(kReactionSeverity )name;


+ (kExposureType )parseExposureTypeString:(NSString *)value;
+ (NSString *)stringExposureType:(kExposureType )name;


+ (kCausalityExpectation )parseCausalityExpectationString:(NSString *)value;
+ (NSString *)stringCausalityExpectation:(kCausalityExpectation )name;


+ (kAlertStatus )parseAlertStatusString:(NSString *)value;
+ (NSString *)stringAlertStatus:(kAlertStatus )name;


+ (kSensitivityStatus )parseSensitivityStatusString:(NSString *)value;
+ (NSString *)stringSensitivityStatus:(kSensitivityStatus )name;


+ (kCriticality )parseCriticalityString:(NSString *)value;
+ (NSString *)stringCriticality:(kCriticality )name;


+ (kSensitivityType )parseSensitivityTypeString:(NSString *)value;
+ (NSString *)stringSensitivityType:(kSensitivityType )name;


+ (kCarePlanStatus )parseCarePlanStatusString:(NSString *)value;
+ (NSString *)stringCarePlanStatus:(kCarePlanStatus )name;


+ (kCarePlanActivityCategory )parseCarePlanActivityCategoryString:(NSString *)value;
+ (NSString *)stringCarePlanActivityCategory:(kCarePlanActivityCategory )name;


+ (kCarePlanGoalStatus )parseCarePlanGoalStatusString:(NSString *)value;
+ (NSString *)stringCarePlanGoalStatus:(kCarePlanGoalStatus )name;


+ (kCarePlanActivityStatus )parseCarePlanActivityStatusString:(NSString *)value;
+ (NSString *)stringCarePlanActivityStatus:(kCarePlanActivityStatus )name;


+ (kCompositionStatus )parseCompositionStatusString:(NSString *)value;
+ (NSString *)stringCompositionStatus:(kCompositionStatus )name;


+ (kCompositionAttestationMode )parseCompositionAttestationModeString:(NSString *)value;
+ (NSString *)stringCompositionAttestationMode:(kCompositionAttestationMode )name;


+ (kConceptMapEquivalence )parseConceptMapEquivalenceString:(NSString *)value;
+ (NSString *)stringConceptMapEquivalence:(kConceptMapEquivalence )name;


+ (kConditionStatus )parseConditionStatusString:(NSString *)value;
+ (NSString *)stringConditionStatus:(kConditionStatus )name;


+ (kConditionRelationshipType )parseConditionRelationshipTypeString:(NSString *)value;
+ (NSString *)stringConditionRelationshipType:(kConditionRelationshipType )name;


+ (kDocumentMode )parseDocumentModeString:(NSString *)value;
+ (NSString *)stringDocumentMode:(kDocumentMode )name;


+ (kRestfulConformanceMode )parseRestfulConformanceModeString:(NSString *)value;
+ (NSString *)stringRestfulConformanceMode:(kRestfulConformanceMode )name;


+ (kMessageTransport )parseMessageTransportString:(NSString *)value;
+ (NSString *)stringMessageTransport:(kMessageTransport )name;


+ (kConformanceEventMode )parseConformanceEventModeString:(NSString *)value;
+ (NSString *)stringConformanceEventMode:(kConformanceEventMode )name;


+ (kMessageSignificanceCategory )parseMessageSignificanceCategoryString:(NSString *)value;
+ (NSString *)stringMessageSignificanceCategory:(kMessageSignificanceCategory )name;


+ (kRestfulOperationType )parseRestfulOperationTypeString:(NSString *)value;
+ (NSString *)stringRestfulOperationType:(kRestfulOperationType )name;


+ (kConformanceStatementStatus )parseConformanceStatementStatusString:(NSString *)value;
+ (NSString *)stringConformanceStatementStatus:(kConformanceStatementStatus )name;


+ (kRestfulOperationSystem )parseRestfulOperationSystemString:(NSString *)value;
+ (NSString *)stringRestfulOperationSystem:(kRestfulOperationSystem )name;


+ (kSearchParamType )parseSearchParamTypeString:(NSString *)value;
+ (NSString *)stringSearchParamType:(kSearchParamType )name;


+ (kRestfulSecurityService )parseRestfulSecurityServiceString:(NSString *)value;
+ (NSString *)stringRestfulSecurityService:(kRestfulSecurityService )name;


+ (kDiagnosticOrderStatus )parseDiagnosticOrderStatusString:(NSString *)value;
+ (NSString *)stringDiagnosticOrderStatus:(kDiagnosticOrderStatus )name;


+ (kDiagnosticOrderPriority )parseDiagnosticOrderPriorityString:(NSString *)value;
+ (NSString *)stringDiagnosticOrderPriority:(kDiagnosticOrderPriority )name;


+ (kDiagnosticReportStatus )parseDiagnosticReportStatusString:(NSString *)value;
+ (NSString *)stringDiagnosticReportStatus:(kDiagnosticReportStatus )name;


+ (kDocumentRelationshipType )parseDocumentRelationshipTypeString:(NSString *)value;
+ (NSString *)stringDocumentRelationshipType:(kDocumentRelationshipType )name;


+ (kDocumentReferenceStatus )parseDocumentReferenceStatusString:(NSString *)value;
+ (NSString *)stringDocumentReferenceStatus:(kDocumentReferenceStatus )name;


+ (kEncounterClass )parseEncounterClassString:(NSString *)value;
+ (NSString *)stringEncounterClass:(kEncounterClass )name;


+ (kEncounterState )parseEncounterStateString:(NSString *)value;
+ (NSString *)stringEncounterState:(kEncounterState )name;


+ (kGroupType )parseGroupTypeString:(NSString *)value;
+ (NSString *)stringGroupType:(kGroupType )name;


+ (kImagingModality )parseImagingModalityString:(NSString *)value;
+ (NSString *)stringImagingModality:(kImagingModality )name;


+ (kInstanceAvailability )parseInstanceAvailabilityString:(NSString *)value;
+ (NSString *)stringInstanceAvailability:(kInstanceAvailability )name;


+ (kModality )parseModalityString:(NSString *)value;
+ (NSString *)stringModality:(kModality )name;


+ (kListMode )parseListModeString:(NSString *)value;
+ (NSString *)stringListMode:(kListMode )name;


+ (kLocationStatus )parseLocationStatusString:(NSString *)value;
+ (NSString *)stringLocationStatus:(kLocationStatus )name;


+ (kLocationMode )parseLocationModeString:(NSString *)value;
+ (NSString *)stringLocationMode:(kLocationMode )name;


+ (kMediaType )parseMediaTypeString:(NSString *)value;
+ (NSString *)stringMediaType:(kMediaType )name;


+ (kMedicationKind )parseMedicationKindString:(NSString *)value;
+ (NSString *)stringMedicationKind:(kMedicationKind )name;


+ (kMedicationAdministrationStatus )parseMedicationAdministrationStatusString:(NSString *)value;
+ (NSString *)stringMedicationAdministrationStatus:(kMedicationAdministrationStatus )name;


+ (kMedicationDispenseStatus )parseMedicationDispenseStatusString:(NSString *)value;
+ (NSString *)stringMedicationDispenseStatus:(kMedicationDispenseStatus )name;


+ (kMedicationPrescriptionStatus )parseMedicationPrescriptionStatusString:(NSString *)value;
+ (NSString *)stringMedicationPrescriptionStatus:(kMedicationPrescriptionStatus )name;


+ (kResponseType )parseResponseTypeString:(NSString *)value;
+ (NSString *)stringResponseType:(kResponseType )name;


+ (kObservationReliability )parseObservationReliabilityString:(NSString *)value;
+ (NSString *)stringObservationReliability:(kObservationReliability )name;


+ (kObservationStatus )parseObservationStatusString:(NSString *)value;
+ (NSString *)stringObservationStatus:(kObservationStatus )name;


+ (kIssueType )parseIssueTypeString:(NSString *)value;
+ (NSString *)stringIssueType:(kIssueType )name;


+ (kIssueSeverity )parseIssueSeverityString:(NSString *)value;
+ (NSString *)stringIssueSeverity:(kIssueSeverity )name;


+ (kOrderOutcomeStatus )parseOrderOutcomeStatusString:(NSString *)value;
+ (NSString *)stringOrderOutcomeStatus:(kOrderOutcomeStatus )name;


+ (kLinkType )parseLinkTypeString:(NSString *)value;
+ (NSString *)stringLinkType:(kLinkType )name;


+ (kProcedureRelationshipType )parseProcedureRelationshipTypeString:(NSString *)value;
+ (NSString *)stringProcedureRelationshipType:(kProcedureRelationshipType )name;


+ (kBindingConformance )parseBindingConformanceString:(NSString *)value;
+ (NSString *)stringBindingConformance:(kBindingConformance )name;


+ (kConstraintSeverity )parseConstraintSeverityString:(NSString *)value;
+ (NSString *)stringConstraintSeverity:(kConstraintSeverity )name;


+ (kResourceProfileStatus )parseResourceProfileStatusString:(NSString *)value;
+ (NSString *)stringResourceProfileStatus:(kResourceProfileStatus )name;


+ (kPropertyRepresentation )parsePropertyRepresentationString:(NSString *)value;
+ (NSString *)stringPropertyRepresentation:(kPropertyRepresentation )name;


+ (kAggregationMode )parseAggregationModeString:(NSString *)value;
+ (NSString *)stringAggregationMode:(kAggregationMode )name;


+ (kExtensionContext )parseExtensionContextString:(NSString *)value;
+ (NSString *)stringExtensionContext:(kExtensionContext )name;


+ (kSlicingRules )parseSlicingRulesString:(NSString *)value;
+ (NSString *)stringSlicingRules:(kSlicingRules )name;


+ (kProvenanceEntityRole )parseProvenanceEntityRoleString:(NSString *)value;
+ (NSString *)stringProvenanceEntityRole:(kProvenanceEntityRole )name;


+ (kQueryOutcome )parseQueryOutcomeString:(NSString *)value;
+ (NSString *)stringQueryOutcome:(kQueryOutcome )name;


+ (kQuestionnaireStatus )parseQuestionnaireStatusString:(NSString *)value;
+ (NSString *)stringQuestionnaireStatus:(kQuestionnaireStatus )name;


+ (kSecurityEventObjectRole )parseSecurityEventObjectRoleString:(NSString *)value;
+ (NSString *)stringSecurityEventObjectRole:(kSecurityEventObjectRole )name;


+ (kSecurityEventAction )parseSecurityEventActionString:(NSString *)value;
+ (NSString *)stringSecurityEventAction:(kSecurityEventAction )name;


+ (kSecurityEventObjectType )parseSecurityEventObjectTypeString:(NSString *)value;
+ (NSString *)stringSecurityEventObjectType:(kSecurityEventObjectType )name;


+ (kSecurityEventObjectLifecycle )parseSecurityEventObjectLifecycleString:(NSString *)value;
+ (NSString *)stringSecurityEventObjectLifecycle:(kSecurityEventObjectLifecycle )name;


+ (kSecurityEventParticipantNetworkType )parseSecurityEventParticipantNetworkTypeString:(NSString *)value;
+ (NSString *)stringSecurityEventParticipantNetworkType:(kSecurityEventParticipantNetworkType )name;


+ (kSecurityEventOutcome )parseSecurityEventOutcomeString:(NSString *)value;
+ (NSString *)stringSecurityEventOutcome:(kSecurityEventOutcome )name;


+ (kHierarchicalRelationshipType )parseHierarchicalRelationshipTypeString:(NSString *)value;
+ (NSString *)stringHierarchicalRelationshipType:(kHierarchicalRelationshipType )name;


+ (kSupplyDispenseStatus )parseSupplyDispenseStatusString:(NSString *)value;
+ (NSString *)stringSupplyDispenseStatus:(kSupplyDispenseStatus )name;


+ (kSupplyStatus )parseSupplyStatusString:(NSString *)value;
+ (NSString *)stringSupplyStatus:(kSupplyStatus )name;


+ (kValueSetStatus )parseValueSetStatusString:(NSString *)value;
+ (NSString *)stringValueSetStatus:(kValueSetStatus )name;


+ (kFilterOperator )parseFilterOperatorString:(NSString *)value;
+ (NSString *)stringFilterOperator:(kFilterOperator )name;

@end
