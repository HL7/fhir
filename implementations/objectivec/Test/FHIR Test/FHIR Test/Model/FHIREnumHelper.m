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
  

 * Generated on Mon, Jan 27, 2014 13:55-0500 for FHIR v0.12
 */
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
  

 * Generated on Mon, Jan 27, 2014 13:55-0500 for FHIR v0.12
 */
#import "FHIREnumHelper.h"
#import "FHIRBindingsHelper.h"

@implementation FHIREnumHelper
+ (int )parseString:(NSString *)value enumType:(kEnumType )enumType
{
    if(enumType == kEnumTypeDataAbsentReason)
    {
        return [FHIRBindingsHelper parseDataAbsentReasonString:value];
    }
    else if(enumType == kEnumTypeSpecialValues)
    {
        return [FHIRBindingsHelper parseSpecialValuesString:value];
    }
    else if(enumType == kEnumTypeResourceType)
    {
        return [FHIRBindingsHelper parseResourceTypeString:value];
    }
    else if(enumType == kEnumTypeAddressUse)
    {
        return [FHIRBindingsHelper parseAddressUseString:value];
    }
    else if(enumType == kEnumTypeContactSystem)
    {
        return [FHIRBindingsHelper parseContactSystemString:value];
    }
    else if(enumType == kEnumTypeContactUse)
    {
        return [FHIRBindingsHelper parseContactUseString:value];
    }
    else if(enumType == kEnumTypeNameUse)
    {
        return [FHIRBindingsHelper parseNameUseString:value];
    }
    else if(enumType == kEnumTypeIdentifierUse)
    {
        return [FHIRBindingsHelper parseIdentifierUseString:value];
    }
    else if(enumType == kEnumTypeNarrativeStatus)
    {
        return [FHIRBindingsHelper parseNarrativeStatusString:value];
    }
    else if(enumType == kEnumTypeQuantityCompararator)
    {
        return [FHIRBindingsHelper parseQuantityCompararatorString:value];
    }
    else if(enumType == kEnumTypeUnitsOfTime)
    {
        return [FHIRBindingsHelper parseUnitsOfTimeString:value];
    }
    else if(enumType == kEnumTypeEventTiming)
    {
        return [FHIRBindingsHelper parseEventTimingString:value];
    }
    else if(enumType == kEnumTypeReactionSeverity)
    {
        return [FHIRBindingsHelper parseReactionSeverityString:value];
    }
    else if(enumType == kEnumTypeExposureType)
    {
        return [FHIRBindingsHelper parseExposureTypeString:value];
    }
    else if(enumType == kEnumTypeCausalityExpectation)
    {
        return [FHIRBindingsHelper parseCausalityExpectationString:value];
    }
    else if(enumType == kEnumTypeAlertStatus)
    {
        return [FHIRBindingsHelper parseAlertStatusString:value];
    }
    else if(enumType == kEnumTypeSensitivityStatus)
    {
        return [FHIRBindingsHelper parseSensitivityStatusString:value];
    }
    else if(enumType == kEnumTypeCriticality)
    {
        return [FHIRBindingsHelper parseCriticalityString:value];
    }
    else if(enumType == kEnumTypeSensitivityType)
    {
        return [FHIRBindingsHelper parseSensitivityTypeString:value];
    }
    else if(enumType == kEnumTypeAppointmentStatus)
    {
        return [FHIRBindingsHelper parseAppointmentStatusString:value];
    }
    else if(enumType == kEnumTypeParticipantRequired)
    {
        return [FHIRBindingsHelper parseParticipantRequiredString:value];
    }
    else if(enumType == kEnumTypeParticipationStatus)
    {
        return [FHIRBindingsHelper parseParticipationStatusString:value];
    }
    else if(enumType == kEnumTypeParticipantStatus)
    {
        return [FHIRBindingsHelper parseParticipantStatusString:value];
    }
    else if(enumType == kEnumTypeCarePlanStatus)
    {
        return [FHIRBindingsHelper parseCarePlanStatusString:value];
    }
    else if(enumType == kEnumTypeCarePlanActivityCategory)
    {
        return [FHIRBindingsHelper parseCarePlanActivityCategoryString:value];
    }
    else if(enumType == kEnumTypeCarePlanGoalStatus)
    {
        return [FHIRBindingsHelper parseCarePlanGoalStatusString:value];
    }
    else if(enumType == kEnumTypeCarePlanActivityStatus)
    {
        return [FHIRBindingsHelper parseCarePlanActivityStatusString:value];
    }
    else if(enumType == kEnumTypeCompositionStatus)
    {
        return [FHIRBindingsHelper parseCompositionStatusString:value];
    }
    else if(enumType == kEnumTypeCompositionAttestationMode)
    {
        return [FHIRBindingsHelper parseCompositionAttestationModeString:value];
    }
    else if(enumType == kEnumTypeConceptMapEquivalence)
    {
        return [FHIRBindingsHelper parseConceptMapEquivalenceString:value];
    }
    else if(enumType == kEnumTypeConditionStatus)
    {
        return [FHIRBindingsHelper parseConditionStatusString:value];
    }
    else if(enumType == kEnumTypeConditionRelationshipType)
    {
        return [FHIRBindingsHelper parseConditionRelationshipTypeString:value];
    }
    else if(enumType == kEnumTypeDocumentMode)
    {
        return [FHIRBindingsHelper parseDocumentModeString:value];
    }
    else if(enumType == kEnumTypeRestfulConformanceMode)
    {
        return [FHIRBindingsHelper parseRestfulConformanceModeString:value];
    }
    else if(enumType == kEnumTypeMessageTransport)
    {
        return [FHIRBindingsHelper parseMessageTransportString:value];
    }
    else if(enumType == kEnumTypeConformanceEventMode)
    {
        return [FHIRBindingsHelper parseConformanceEventModeString:value];
    }
    else if(enumType == kEnumTypeMessageSignificanceCategory)
    {
        return [FHIRBindingsHelper parseMessageSignificanceCategoryString:value];
    }
    else if(enumType == kEnumTypeRestfulOperationType)
    {
        return [FHIRBindingsHelper parseRestfulOperationTypeString:value];
    }
    else if(enumType == kEnumTypeConformanceStatementStatus)
    {
        return [FHIRBindingsHelper parseConformanceStatementStatusString:value];
    }
    else if(enumType == kEnumTypeRestfulOperationSystem)
    {
        return [FHIRBindingsHelper parseRestfulOperationSystemString:value];
    }
    else if(enumType == kEnumTypeSearchParamType)
    {
        return [FHIRBindingsHelper parseSearchParamTypeString:value];
    }
    else if(enumType == kEnumTypeRestfulSecurityService)
    {
        return [FHIRBindingsHelper parseRestfulSecurityServiceString:value];
    }
    else if(enumType == kEnumTypeDiagnosticOrderStatus)
    {
        return [FHIRBindingsHelper parseDiagnosticOrderStatusString:value];
    }
    else if(enumType == kEnumTypeDiagnosticOrderPriority)
    {
        return [FHIRBindingsHelper parseDiagnosticOrderPriorityString:value];
    }
    else if(enumType == kEnumTypeDiagnosticReportStatus)
    {
        return [FHIRBindingsHelper parseDiagnosticReportStatusString:value];
    }
    else if(enumType == kEnumTypeDocumentRelationshipType)
    {
        return [FHIRBindingsHelper parseDocumentRelationshipTypeString:value];
    }
    else if(enumType == kEnumTypeDocumentReferenceStatus)
    {
        return [FHIRBindingsHelper parseDocumentReferenceStatusString:value];
    }
    else if(enumType == kEnumTypeEncounterClass)
    {
        return [FHIRBindingsHelper parseEncounterClassString:value];
    }
    else if(enumType == kEnumTypeEncounterState)
    {
        return [FHIRBindingsHelper parseEncounterStateString:value];
    }
    else if(enumType == kEnumTypeGroupType)
    {
        return [FHIRBindingsHelper parseGroupTypeString:value];
    }
    else if(enumType == kEnumTypeImagingModality)
    {
        return [FHIRBindingsHelper parseImagingModalityString:value];
    }
    else if(enumType == kEnumTypeInstanceAvailability)
    {
        return [FHIRBindingsHelper parseInstanceAvailabilityString:value];
    }
    else if(enumType == kEnumTypeModality)
    {
        return [FHIRBindingsHelper parseModalityString:value];
    }
    else if(enumType == kEnumTypeListMode)
    {
        return [FHIRBindingsHelper parseListModeString:value];
    }
    else if(enumType == kEnumTypeLocationStatus)
    {
        return [FHIRBindingsHelper parseLocationStatusString:value];
    }
    else if(enumType == kEnumTypeLocationMode)
    {
        return [FHIRBindingsHelper parseLocationModeString:value];
    }
    else if(enumType == kEnumTypeMediaType)
    {
        return [FHIRBindingsHelper parseMediaTypeString:value];
    }
    else if(enumType == kEnumTypeMedicationKind)
    {
        return [FHIRBindingsHelper parseMedicationKindString:value];
    }
    else if(enumType == kEnumTypeMedicationAdministrationStatus)
    {
        return [FHIRBindingsHelper parseMedicationAdministrationStatusString:value];
    }
    else if(enumType == kEnumTypeMedicationDispenseStatus)
    {
        return [FHIRBindingsHelper parseMedicationDispenseStatusString:value];
    }
    else if(enumType == kEnumTypeMedicationPrescriptionStatus)
    {
        return [FHIRBindingsHelper parseMedicationPrescriptionStatusString:value];
    }
    else if(enumType == kEnumTypeResponseType)
    {
        return [FHIRBindingsHelper parseResponseTypeString:value];
    }
    else if(enumType == kEnumTypeObservationReliability)
    {
        return [FHIRBindingsHelper parseObservationReliabilityString:value];
    }
    else if(enumType == kEnumTypeObservationStatus)
    {
        return [FHIRBindingsHelper parseObservationStatusString:value];
    }
    else if(enumType == kEnumTypeIssueType)
    {
        return [FHIRBindingsHelper parseIssueTypeString:value];
    }
    else if(enumType == kEnumTypeIssueSeverity)
    {
        return [FHIRBindingsHelper parseIssueSeverityString:value];
    }
    else if(enumType == kEnumTypeOrderOutcomeStatus)
    {
        return [FHIRBindingsHelper parseOrderOutcomeStatusString:value];
    }
    else if(enumType == kEnumTypeLinkType)
    {
        return [FHIRBindingsHelper parseLinkTypeString:value];
    }
    else if(enumType == kEnumTypeProcedureRelationshipType)
    {
        return [FHIRBindingsHelper parseProcedureRelationshipTypeString:value];
    }
    else if(enumType == kEnumTypeBindingConformance)
    {
        return [FHIRBindingsHelper parseBindingConformanceString:value];
    }
    else if(enumType == kEnumTypeConstraintSeverity)
    {
        return [FHIRBindingsHelper parseConstraintSeverityString:value];
    }
    else if(enumType == kEnumTypeResourceProfileStatus)
    {
        return [FHIRBindingsHelper parseResourceProfileStatusString:value];
    }
    else if(enumType == kEnumTypePropertyRepresentation)
    {
        return [FHIRBindingsHelper parsePropertyRepresentationString:value];
    }
    else if(enumType == kEnumTypeAggregationMode)
    {
        return [FHIRBindingsHelper parseAggregationModeString:value];
    }
    else if(enumType == kEnumTypeExtensionContext)
    {
        return [FHIRBindingsHelper parseExtensionContextString:value];
    }
    else if(enumType == kEnumTypeSlicingRules)
    {
        return [FHIRBindingsHelper parseSlicingRulesString:value];
    }
    else if(enumType == kEnumTypeProvenanceEntityRole)
    {
        return [FHIRBindingsHelper parseProvenanceEntityRoleString:value];
    }
    else if(enumType == kEnumTypeQueryOutcome)
    {
        return [FHIRBindingsHelper parseQueryOutcomeString:value];
    }
    else if(enumType == kEnumTypeQuestionnaireStatus)
    {
        return [FHIRBindingsHelper parseQuestionnaireStatusString:value];
    }
    else if(enumType == kEnumTypeSecurityEventAction)
    {
        return [FHIRBindingsHelper parseSecurityEventActionString:value];
    }
    else if(enumType == kEnumTypeSecurityEventParticipantNetworkType)
    {
        return [FHIRBindingsHelper parseSecurityEventParticipantNetworkTypeString:value];
    }
    else if(enumType == kEnumTypeSecurityEventObjectRole)
    {
        return [FHIRBindingsHelper parseSecurityEventObjectRoleString:value];
    }
    else if(enumType == kEnumTypeSecurityEventObjectType)
    {
        return [FHIRBindingsHelper parseSecurityEventObjectTypeString:value];
    }
    else if(enumType == kEnumTypeSecurityEventObjectLifecycle)
    {
        return [FHIRBindingsHelper parseSecurityEventObjectLifecycleString:value];
    }
    else if(enumType == kEnumTypeSecurityEventOutcome)
    {
        return [FHIRBindingsHelper parseSecurityEventOutcomeString:value];
    }
    else if(enumType == kEnumTypeSlotStatus)
    {
        return [FHIRBindingsHelper parseSlotStatusString:value];
    }
    else if(enumType == kEnumTypeHierarchicalRelationshipType)
    {
        return [FHIRBindingsHelper parseHierarchicalRelationshipTypeString:value];
    }
    else if(enumType == kEnumTypeSupplyDispenseStatus)
    {
        return [FHIRBindingsHelper parseSupplyDispenseStatusString:value];
    }
    else if(enumType == kEnumTypeSupplyStatus)
    {
        return [FHIRBindingsHelper parseSupplyStatusString:value];
    }
    else if(enumType == kEnumTypeCodeSelectionMode)
    {
        return [FHIRBindingsHelper parseCodeSelectionModeString:value];
    }
    else if(enumType == kEnumTypeValueSetStatus)
    {
        return [FHIRBindingsHelper parseValueSetStatusString:value];
    }
    else if(enumType == kEnumTypeFilterOperator)
    {
        return [FHIRBindingsHelper parseFilterOperatorString:value];
    }
    else
    {
        @throw [NSException exceptionWithName:@"ArgumentException" reason:@"Unrecognized value " userInfo:nil];
    }
    
}

+ (NSString *)enumToString:(int )value enumType:(kEnumType )enumType
{
    if(enumType == kEnumTypeDataAbsentReason)
        return [FHIRBindingsHelper stringDataAbsentReason:value];
    else if(enumType == kEnumTypeSpecialValues)
        return [FHIRBindingsHelper stringSpecialValues:value];
    else if(enumType == kEnumTypeResourceType)
        return [FHIRBindingsHelper stringResourceType:value];
    else if(enumType == kEnumTypeAddressUse)
        return [FHIRBindingsHelper stringAddressUse:value];
    else if(enumType == kEnumTypeContactSystem)
        return [FHIRBindingsHelper stringContactSystem:value];
    else if(enumType == kEnumTypeContactUse)
        return [FHIRBindingsHelper stringContactUse:value];
    else if(enumType == kEnumTypeNameUse)
        return [FHIRBindingsHelper stringNameUse:value];
    else if(enumType == kEnumTypeIdentifierUse)
        return [FHIRBindingsHelper stringIdentifierUse:value];
    else if(enumType == kEnumTypeNarrativeStatus)
        return [FHIRBindingsHelper stringNarrativeStatus:value];
    else if(enumType == kEnumTypeQuantityCompararator)
        return [FHIRBindingsHelper stringQuantityCompararator:value];
    else if(enumType == kEnumTypeUnitsOfTime)
        return [FHIRBindingsHelper stringUnitsOfTime:value];
    else if(enumType == kEnumTypeEventTiming)
        return [FHIRBindingsHelper stringEventTiming:value];
    else if(enumType == kEnumTypeReactionSeverity)
        return [FHIRBindingsHelper stringReactionSeverity:value];
    else if(enumType == kEnumTypeExposureType)
        return [FHIRBindingsHelper stringExposureType:value];
    else if(enumType == kEnumTypeCausalityExpectation)
        return [FHIRBindingsHelper stringCausalityExpectation:value];
    else if(enumType == kEnumTypeAlertStatus)
        return [FHIRBindingsHelper stringAlertStatus:value];
    else if(enumType == kEnumTypeSensitivityStatus)
        return [FHIRBindingsHelper stringSensitivityStatus:value];
    else if(enumType == kEnumTypeCriticality)
        return [FHIRBindingsHelper stringCriticality:value];
    else if(enumType == kEnumTypeSensitivityType)
        return [FHIRBindingsHelper stringSensitivityType:value];
    else if(enumType == kEnumTypeAppointmentStatus)
        return [FHIRBindingsHelper stringAppointmentStatus:value];
    else if(enumType == kEnumTypeParticipantRequired)
        return [FHIRBindingsHelper stringParticipantRequired:value];
    else if(enumType == kEnumTypeParticipationStatus)
        return [FHIRBindingsHelper stringParticipationStatus:value];
    else if(enumType == kEnumTypeParticipantStatus)
        return [FHIRBindingsHelper stringParticipantStatus:value];
    else if(enumType == kEnumTypeCarePlanStatus)
        return [FHIRBindingsHelper stringCarePlanStatus:value];
    else if(enumType == kEnumTypeCarePlanActivityCategory)
        return [FHIRBindingsHelper stringCarePlanActivityCategory:value];
    else if(enumType == kEnumTypeCarePlanGoalStatus)
        return [FHIRBindingsHelper stringCarePlanGoalStatus:value];
    else if(enumType == kEnumTypeCarePlanActivityStatus)
        return [FHIRBindingsHelper stringCarePlanActivityStatus:value];
    else if(enumType == kEnumTypeCompositionStatus)
        return [FHIRBindingsHelper stringCompositionStatus:value];
    else if(enumType == kEnumTypeCompositionAttestationMode)
        return [FHIRBindingsHelper stringCompositionAttestationMode:value];
    else if(enumType == kEnumTypeConceptMapEquivalence)
        return [FHIRBindingsHelper stringConceptMapEquivalence:value];
    else if(enumType == kEnumTypeConditionStatus)
        return [FHIRBindingsHelper stringConditionStatus:value];
    else if(enumType == kEnumTypeConditionRelationshipType)
        return [FHIRBindingsHelper stringConditionRelationshipType:value];
    else if(enumType == kEnumTypeDocumentMode)
        return [FHIRBindingsHelper stringDocumentMode:value];
    else if(enumType == kEnumTypeRestfulConformanceMode)
        return [FHIRBindingsHelper stringRestfulConformanceMode:value];
    else if(enumType == kEnumTypeMessageTransport)
        return [FHIRBindingsHelper stringMessageTransport:value];
    else if(enumType == kEnumTypeConformanceEventMode)
        return [FHIRBindingsHelper stringConformanceEventMode:value];
    else if(enumType == kEnumTypeMessageSignificanceCategory)
        return [FHIRBindingsHelper stringMessageSignificanceCategory:value];
    else if(enumType == kEnumTypeRestfulOperationType)
        return [FHIRBindingsHelper stringRestfulOperationType:value];
    else if(enumType == kEnumTypeConformanceStatementStatus)
        return [FHIRBindingsHelper stringConformanceStatementStatus:value];
    else if(enumType == kEnumTypeRestfulOperationSystem)
        return [FHIRBindingsHelper stringRestfulOperationSystem:value];
    else if(enumType == kEnumTypeSearchParamType)
        return [FHIRBindingsHelper stringSearchParamType:value];
    else if(enumType == kEnumTypeRestfulSecurityService)
        return [FHIRBindingsHelper stringRestfulSecurityService:value];
    else if(enumType == kEnumTypeDiagnosticOrderStatus)
        return [FHIRBindingsHelper stringDiagnosticOrderStatus:value];
    else if(enumType == kEnumTypeDiagnosticOrderPriority)
        return [FHIRBindingsHelper stringDiagnosticOrderPriority:value];
    else if(enumType == kEnumTypeDiagnosticReportStatus)
        return [FHIRBindingsHelper stringDiagnosticReportStatus:value];
    else if(enumType == kEnumTypeDocumentRelationshipType)
        return [FHIRBindingsHelper stringDocumentRelationshipType:value];
    else if(enumType == kEnumTypeDocumentReferenceStatus)
        return [FHIRBindingsHelper stringDocumentReferenceStatus:value];
    else if(enumType == kEnumTypeEncounterClass)
        return [FHIRBindingsHelper stringEncounterClass:value];
    else if(enumType == kEnumTypeEncounterState)
        return [FHIRBindingsHelper stringEncounterState:value];
    else if(enumType == kEnumTypeGroupType)
        return [FHIRBindingsHelper stringGroupType:value];
    else if(enumType == kEnumTypeImagingModality)
        return [FHIRBindingsHelper stringImagingModality:value];
    else if(enumType == kEnumTypeInstanceAvailability)
        return [FHIRBindingsHelper stringInstanceAvailability:value];
    else if(enumType == kEnumTypeModality)
        return [FHIRBindingsHelper stringModality:value];
    else if(enumType == kEnumTypeListMode)
        return [FHIRBindingsHelper stringListMode:value];
    else if(enumType == kEnumTypeLocationStatus)
        return [FHIRBindingsHelper stringLocationStatus:value];
    else if(enumType == kEnumTypeLocationMode)
        return [FHIRBindingsHelper stringLocationMode:value];
    else if(enumType == kEnumTypeMediaType)
        return [FHIRBindingsHelper stringMediaType:value];
    else if(enumType == kEnumTypeMedicationKind)
        return [FHIRBindingsHelper stringMedicationKind:value];
    else if(enumType == kEnumTypeMedicationAdministrationStatus)
        return [FHIRBindingsHelper stringMedicationAdministrationStatus:value];
    else if(enumType == kEnumTypeMedicationDispenseStatus)
        return [FHIRBindingsHelper stringMedicationDispenseStatus:value];
    else if(enumType == kEnumTypeMedicationPrescriptionStatus)
        return [FHIRBindingsHelper stringMedicationPrescriptionStatus:value];
    else if(enumType == kEnumTypeResponseType)
        return [FHIRBindingsHelper stringResponseType:value];
    else if(enumType == kEnumTypeObservationReliability)
        return [FHIRBindingsHelper stringObservationReliability:value];
    else if(enumType == kEnumTypeObservationStatus)
        return [FHIRBindingsHelper stringObservationStatus:value];
    else if(enumType == kEnumTypeIssueType)
        return [FHIRBindingsHelper stringIssueType:value];
    else if(enumType == kEnumTypeIssueSeverity)
        return [FHIRBindingsHelper stringIssueSeverity:value];
    else if(enumType == kEnumTypeOrderOutcomeStatus)
        return [FHIRBindingsHelper stringOrderOutcomeStatus:value];
    else if(enumType == kEnumTypeLinkType)
        return [FHIRBindingsHelper stringLinkType:value];
    else if(enumType == kEnumTypeProcedureRelationshipType)
        return [FHIRBindingsHelper stringProcedureRelationshipType:value];
    else if(enumType == kEnumTypeBindingConformance)
        return [FHIRBindingsHelper stringBindingConformance:value];
    else if(enumType == kEnumTypeConstraintSeverity)
        return [FHIRBindingsHelper stringConstraintSeverity:value];
    else if(enumType == kEnumTypeResourceProfileStatus)
        return [FHIRBindingsHelper stringResourceProfileStatus:value];
    else if(enumType == kEnumTypePropertyRepresentation)
        return [FHIRBindingsHelper stringPropertyRepresentation:value];
    else if(enumType == kEnumTypeAggregationMode)
        return [FHIRBindingsHelper stringAggregationMode:value];
    else if(enumType == kEnumTypeExtensionContext)
        return [FHIRBindingsHelper stringExtensionContext:value];
    else if(enumType == kEnumTypeSlicingRules)
        return [FHIRBindingsHelper stringSlicingRules:value];
    else if(enumType == kEnumTypeProvenanceEntityRole)
        return [FHIRBindingsHelper stringProvenanceEntityRole:value];
    else if(enumType == kEnumTypeQueryOutcome)
        return [FHIRBindingsHelper stringQueryOutcome:value];
    else if(enumType == kEnumTypeQuestionnaireStatus)
        return [FHIRBindingsHelper stringQuestionnaireStatus:value];
    else if(enumType == kEnumTypeSecurityEventAction)
        return [FHIRBindingsHelper stringSecurityEventAction:value];
    else if(enumType == kEnumTypeSecurityEventParticipantNetworkType)
        return [FHIRBindingsHelper stringSecurityEventParticipantNetworkType:value];
    else if(enumType == kEnumTypeSecurityEventObjectRole)
        return [FHIRBindingsHelper stringSecurityEventObjectRole:value];
    else if(enumType == kEnumTypeSecurityEventObjectType)
        return [FHIRBindingsHelper stringSecurityEventObjectType:value];
    else if(enumType == kEnumTypeSecurityEventObjectLifecycle)
        return [FHIRBindingsHelper stringSecurityEventObjectLifecycle:value];
    else if(enumType == kEnumTypeSecurityEventOutcome)
        return [FHIRBindingsHelper stringSecurityEventOutcome:value];
    else if(enumType == kEnumTypeSlotStatus)
        return [FHIRBindingsHelper stringSlotStatus:value];
    else if(enumType == kEnumTypeHierarchicalRelationshipType)
        return [FHIRBindingsHelper stringHierarchicalRelationshipType:value];
    else if(enumType == kEnumTypeSupplyDispenseStatus)
        return [FHIRBindingsHelper stringSupplyDispenseStatus:value];
    else if(enumType == kEnumTypeSupplyStatus)
        return [FHIRBindingsHelper stringSupplyStatus:value];
    else if(enumType == kEnumTypeCodeSelectionMode)
        return [FHIRBindingsHelper stringCodeSelectionMode:value];
    else if(enumType == kEnumTypeValueSetStatus)
        return [FHIRBindingsHelper stringValueSetStatus:value];
    else if(enumType == kEnumTypeFilterOperator)
        return [FHIRBindingsHelper stringFilterOperator:value];
    else
        @throw [NSException exceptionWithName:@"ArgumentException" reason:@"Unrecognized enumeration " userInfo:nil];
    
}
@end
