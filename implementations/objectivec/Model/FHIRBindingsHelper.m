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


#import "FHIRBindingsHelper.h"
@implementation FHIRBindingsHelper
/*
 * Conversion of kDataAbsentReasonfrom string
 */
+ (kDataAbsentReason )parseDataAbsentReasonString:(NSString *)value
{
    kDataAbsentReason result;
    
    if( [value isEqualToString:@"unknown"])
        result = kDataAbsentReasonUnknown;
    else if( [value isEqualToString:@"asked"])
        result = kDataAbsentReasonAsked;
    else if( [value isEqualToString:@"temp"])
        result = kDataAbsentReasonTemp;
    else if( [value isEqualToString:@"notasked"])
        result = kDataAbsentReasonNotasked;
    else if( [value isEqualToString:@"masked"])
        result = kDataAbsentReasonMasked;
    else if( [value isEqualToString:@"unsupported"])
        result = kDataAbsentReasonUnsupported;
    else if( [value isEqualToString:@"astext"])
        result = kDataAbsentReasonAstext;
    else if( [value isEqualToString:@"error"])
        result = kDataAbsentReasonError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDataAbsentReason" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DataAbsentReasonto string
 */
+ (NSString *)stringDataAbsentReason:(kDataAbsentReason )name
{
    if( name==kDataAbsentReasonUnknown )
        return @"unknown";
    else if( name==kDataAbsentReasonAsked )
        return @"asked";
    else if( name==kDataAbsentReasonTemp )
        return @"temp";
    else if( name==kDataAbsentReasonNotasked )
        return @"notasked";
    else if( name==kDataAbsentReasonMasked )
        return @"masked";
    else if( name==kDataAbsentReasonUnsupported )
        return @"unsupported";
    else if( name==kDataAbsentReasonAstext )
        return @"astext";
    else if( name==kDataAbsentReasonError )
        return @"error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDataAbsentReason" userInfo:nil];
}
/*
 * Conversion of kSpecialValuesfrom string
 */
+ (kSpecialValues )parseSpecialValuesString:(NSString *)value
{
    kSpecialValues result;
    
    if( [value isEqualToString:@"true"])
        result = kSpecialValuesTrue;
    else if( [value isEqualToString:@"false"])
        result = kSpecialValuesFalse;
    else if( [value isEqualToString:@"trace"])
        result = kSpecialValuesTrace;
    else if( [value isEqualToString:@"sufficient"])
        result = kSpecialValuesSufficient;
    else if( [value isEqualToString:@"withdrawn"])
        result = kSpecialValuesWithdrawn;
    else if( [value isEqualToString:@"nil known"])
        result = kSpecialValuesNilKnown;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSpecialValues" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SpecialValuesto string
 */
+ (NSString *)stringSpecialValues:(kSpecialValues )name
{
    if( name==kSpecialValuesTrue )
        return @"true";
    else if( name==kSpecialValuesFalse )
        return @"false";
    else if( name==kSpecialValuesTrace )
        return @"trace";
    else if( name==kSpecialValuesSufficient )
        return @"sufficient";
    else if( name==kSpecialValuesWithdrawn )
        return @"withdrawn";
    else if( name==kSpecialValuesNilKnown )
        return @"nil known";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSpecialValues" userInfo:nil];
}
/*
 * Conversion of kResourceTypefrom string
 */
+ (kResourceType )parseResourceTypeString:(NSString *)value
{
    kResourceType result;
    
    if( [value isEqualToString:@"Resource"])
        result = kResourceTypeResource;
    else if( [value isEqualToString:@"AdverseReaction"])
        result = kResourceTypeAdverseReaction;
    else if( [value isEqualToString:@"Alert"])
        result = kResourceTypeAlert;
    else if( [value isEqualToString:@"AllergyIntolerance"])
        result = kResourceTypeAllergyIntolerance;
    else if( [value isEqualToString:@"CarePlan"])
        result = kResourceTypeCarePlan;
    else if( [value isEqualToString:@"Composition"])
        result = kResourceTypeComposition;
    else if( [value isEqualToString:@"ConceptMap"])
        result = kResourceTypeConceptMap;
    else if( [value isEqualToString:@"Condition"])
        result = kResourceTypeCondition;
    else if( [value isEqualToString:@"Conformance"])
        result = kResourceTypeConformance;
    else if( [value isEqualToString:@"Device"])
        result = kResourceTypeDevice;
    else if( [value isEqualToString:@"DeviceObservationReport"])
        result = kResourceTypeDeviceObservationReport;
    else if( [value isEqualToString:@"DiagnosticOrder"])
        result = kResourceTypeDiagnosticOrder;
    else if( [value isEqualToString:@"DiagnosticReport"])
        result = kResourceTypeDiagnosticReport;
    else if( [value isEqualToString:@"DocumentManifest"])
        result = kResourceTypeDocumentManifest;
    else if( [value isEqualToString:@"DocumentReference"])
        result = kResourceTypeDocumentReference;
    else if( [value isEqualToString:@"Encounter"])
        result = kResourceTypeEncounter;
    else if( [value isEqualToString:@"FamilyHistory"])
        result = kResourceTypeFamilyHistory;
    else if( [value isEqualToString:@"Group"])
        result = kResourceTypeGroup;
    else if( [value isEqualToString:@"ImagingStudy"])
        result = kResourceTypeImagingStudy;
    else if( [value isEqualToString:@"Immunization"])
        result = kResourceTypeImmunization;
    else if( [value isEqualToString:@"ImmunizationRecommendation"])
        result = kResourceTypeImmunizationRecommendation;
    else if( [value isEqualToString:@"List"])
        result = kResourceTypeList;
    else if( [value isEqualToString:@"Location"])
        result = kResourceTypeLocation;
    else if( [value isEqualToString:@"Media"])
        result = kResourceTypeMedia;
    else if( [value isEqualToString:@"Medication"])
        result = kResourceTypeMedication;
    else if( [value isEqualToString:@"MedicationAdministration"])
        result = kResourceTypeMedicationAdministration;
    else if( [value isEqualToString:@"MedicationDispense"])
        result = kResourceTypeMedicationDispense;
    else if( [value isEqualToString:@"MedicationPrescription"])
        result = kResourceTypeMedicationPrescription;
    else if( [value isEqualToString:@"MedicationStatement"])
        result = kResourceTypeMedicationStatement;
    else if( [value isEqualToString:@"MessageHeader"])
        result = kResourceTypeMessageHeader;
    else if( [value isEqualToString:@"Observation"])
        result = kResourceTypeObservation;
    else if( [value isEqualToString:@"OperationOutcome"])
        result = kResourceTypeOperationOutcome;
    else if( [value isEqualToString:@"Order"])
        result = kResourceTypeOrder;
    else if( [value isEqualToString:@"OrderResponse"])
        result = kResourceTypeOrderResponse;
    else if( [value isEqualToString:@"Organization"])
        result = kResourceTypeOrganization;
    else if( [value isEqualToString:@"Other"])
        result = kResourceTypeOther;
    else if( [value isEqualToString:@"Patient"])
        result = kResourceTypePatient;
    else if( [value isEqualToString:@"Practitioner"])
        result = kResourceTypePractitioner;
    else if( [value isEqualToString:@"Procedure"])
        result = kResourceTypeProcedure;
    else if( [value isEqualToString:@"Profile"])
        result = kResourceTypeProfile;
    else if( [value isEqualToString:@"Provenance"])
        result = kResourceTypeProvenance;
    else if( [value isEqualToString:@"Query"])
        result = kResourceTypeQuery;
    else if( [value isEqualToString:@"Questionnaire"])
        result = kResourceTypeQuestionnaire;
    else if( [value isEqualToString:@"RelatedPerson"])
        result = kResourceTypeRelatedPerson;
    else if( [value isEqualToString:@"SecurityEvent"])
        result = kResourceTypeSecurityEvent;
    else if( [value isEqualToString:@"Specimen"])
        result = kResourceTypeSpecimen;
    else if( [value isEqualToString:@"Substance"])
        result = kResourceTypeSubstance;
    else if( [value isEqualToString:@"Supply"])
        result = kResourceTypeSupply;
    else if( [value isEqualToString:@"ValueSet"])
        result = kResourceTypeValueSet;
    else if( [value isEqualToString:@"Binary"])
        result = kResourceTypeBinary;
    else if( [value isEqualToString:@"Appointment"])
        result = kResourceTypeAppointment;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResourceType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ResourceTypeto string
 */
+ (NSString *)stringResourceType:(kResourceType )name
{
    if( name==kResourceTypeResource )
        return @"Resource";
    else if( name==kResourceTypeAdverseReaction )
        return @"AdverseReaction";
    else if( name==kResourceTypeAlert )
        return @"Alert";
    else if( name==kResourceTypeAllergyIntolerance )
        return @"AllergyIntolerance";
    else if( name==kResourceTypeCarePlan )
        return @"CarePlan";
    else if( name==kResourceTypeComposition )
        return @"Composition";
    else if( name==kResourceTypeConceptMap )
        return @"ConceptMap";
    else if( name==kResourceTypeCondition )
        return @"Condition";
    else if( name==kResourceTypeConformance )
        return @"Conformance";
    else if( name==kResourceTypeDevice )
        return @"Device";
    else if( name==kResourceTypeDeviceObservationReport )
        return @"DeviceObservationReport";
    else if( name==kResourceTypeDiagnosticOrder )
        return @"DiagnosticOrder";
    else if( name==kResourceTypeDiagnosticReport )
        return @"DiagnosticReport";
    else if( name==kResourceTypeDocumentManifest )
        return @"DocumentManifest";
    else if( name==kResourceTypeDocumentReference )
        return @"DocumentReference";
    else if( name==kResourceTypeEncounter )
        return @"Encounter";
    else if( name==kResourceTypeFamilyHistory )
        return @"FamilyHistory";
    else if( name==kResourceTypeGroup )
        return @"Group";
    else if( name==kResourceTypeImagingStudy )
        return @"ImagingStudy";
    else if( name==kResourceTypeImmunization )
        return @"Immunization";
    else if( name==kResourceTypeImmunizationRecommendation )
        return @"ImmunizationRecommendation";
    else if( name==kResourceTypeList )
        return @"List";
    else if( name==kResourceTypeLocation )
        return @"Location";
    else if( name==kResourceTypeMedia )
        return @"Media";
    else if( name==kResourceTypeMedication )
        return @"Medication";
    else if( name==kResourceTypeMedicationAdministration )
        return @"MedicationAdministration";
    else if( name==kResourceTypeMedicationDispense )
        return @"MedicationDispense";
    else if( name==kResourceTypeMedicationPrescription )
        return @"MedicationPrescription";
    else if( name==kResourceTypeMedicationStatement )
        return @"MedicationStatement";
    else if( name==kResourceTypeMessageHeader )
        return @"MessageHeader";
    else if( name==kResourceTypeObservation )
        return @"Observation";
    else if( name==kResourceTypeOperationOutcome )
        return @"OperationOutcome";
    else if( name==kResourceTypeOrder )
        return @"Order";
    else if( name==kResourceTypeOrderResponse )
        return @"OrderResponse";
    else if( name==kResourceTypeOrganization )
        return @"Organization";
    else if( name==kResourceTypeOther )
        return @"Other";
    else if( name==kResourceTypePatient )
        return @"Patient";
    else if( name==kResourceTypePractitioner )
        return @"Practitioner";
    else if( name==kResourceTypeProcedure )
        return @"Procedure";
    else if( name==kResourceTypeProfile )
        return @"Profile";
    else if( name==kResourceTypeProvenance )
        return @"Provenance";
    else if( name==kResourceTypeQuery )
        return @"Query";
    else if( name==kResourceTypeQuestionnaire )
        return @"Questionnaire";
    else if( name==kResourceTypeRelatedPerson )
        return @"RelatedPerson";
    else if( name==kResourceTypeSecurityEvent )
        return @"SecurityEvent";
    else if( name==kResourceTypeSpecimen )
        return @"Specimen";
    else if( name==kResourceTypeSubstance )
        return @"Substance";
    else if( name==kResourceTypeSupply )
        return @"Supply";
    else if( name==kResourceTypeValueSet )
        return @"ValueSet";
    else if( name==kResourceTypeBinary )
        return @"Binary";
    else if( name==kResourceTypeAppointment )
        return @"Appointment";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResourceType" userInfo:nil];
}
/*
 * Conversion of kAddressUsefrom string
 */
+ (kAddressUse )parseAddressUseString:(NSString *)value
{
    kAddressUse result;
    
    if( [value isEqualToString:@"home"])
        result = kAddressUseHome;
    else if( [value isEqualToString:@"work"])
        result = kAddressUseWork;
    else if( [value isEqualToString:@"temp"])
        result = kAddressUseTemp;
    else if( [value isEqualToString:@"old"])
        result = kAddressUseOld;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAddressUse" userInfo:nil];
    
    return result;
}

/*
 * Conversion of AddressUseto string
 */
+ (NSString *)stringAddressUse:(kAddressUse )name
{
    if( name==kAddressUseHome )
        return @"home";
    else if( name==kAddressUseWork )
        return @"work";
    else if( name==kAddressUseTemp )
        return @"temp";
    else if( name==kAddressUseOld )
        return @"old";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAddressUse" userInfo:nil];
}
/*
 * Conversion of kContactSystemfrom string
 */
+ (kContactSystem )parseContactSystemString:(NSString *)value
{
    kContactSystem result;
    
    if( [value isEqualToString:@"phone"])
        result = kContactSystemPhone;
    else if( [value isEqualToString:@"fax"])
        result = kContactSystemFax;
    else if( [value isEqualToString:@"email"])
        result = kContactSystemEmail;
    else if( [value isEqualToString:@"url"])
        result = kContactSystemUrl;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kContactSystem" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ContactSystemto string
 */
+ (NSString *)stringContactSystem:(kContactSystem )name
{
    if( name==kContactSystemPhone )
        return @"phone";
    else if( name==kContactSystemFax )
        return @"fax";
    else if( name==kContactSystemEmail )
        return @"email";
    else if( name==kContactSystemUrl )
        return @"url";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kContactSystem" userInfo:nil];
}
/*
 * Conversion of kContactUsefrom string
 */
+ (kContactUse )parseContactUseString:(NSString *)value
{
    kContactUse result;
    
    if( [value isEqualToString:@"home"])
        result = kContactUseHome;
    else if( [value isEqualToString:@"work"])
        result = kContactUseWork;
    else if( [value isEqualToString:@"temp"])
        result = kContactUseTemp;
    else if( [value isEqualToString:@"old"])
        result = kContactUseOld;
    else if( [value isEqualToString:@"mobile"])
        result = kContactUseMobile;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kContactUse" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ContactUseto string
 */
+ (NSString *)stringContactUse:(kContactUse )name
{
    if( name==kContactUseHome )
        return @"home";
    else if( name==kContactUseWork )
        return @"work";
    else if( name==kContactUseTemp )
        return @"temp";
    else if( name==kContactUseOld )
        return @"old";
    else if( name==kContactUseMobile )
        return @"mobile";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kContactUse" userInfo:nil];
}
/*
 * Conversion of kNameUsefrom string
 */
+ (kNameUse )parseNameUseString:(NSString *)value
{
    kNameUse result;
    
    if( [value isEqualToString:@"usual"])
        result = kNameUseUsual;
    else if( [value isEqualToString:@"official"])
        result = kNameUseOfficial;
    else if( [value isEqualToString:@"temp"])
        result = kNameUseTemp;
    else if( [value isEqualToString:@"nickname"])
        result = kNameUseNickname;
    else if( [value isEqualToString:@"anonymous"])
        result = kNameUseAnonymous;
    else if( [value isEqualToString:@"old"])
        result = kNameUseOld;
    else if( [value isEqualToString:@"maiden"])
        result = kNameUseMaiden;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kNameUse" userInfo:nil];
    
    return result;
}

/*
 * Conversion of NameUseto string
 */
+ (NSString *)stringNameUse:(kNameUse )name
{
    if( name==kNameUseUsual )
        return @"usual";
    else if( name==kNameUseOfficial )
        return @"official";
    else if( name==kNameUseTemp )
        return @"temp";
    else if( name==kNameUseNickname )
        return @"nickname";
    else if( name==kNameUseAnonymous )
        return @"anonymous";
    else if( name==kNameUseOld )
        return @"old";
    else if( name==kNameUseMaiden )
        return @"maiden";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kNameUse" userInfo:nil];
}
/*
 * Conversion of kIdentifierUsefrom string
 */
+ (kIdentifierUse )parseIdentifierUseString:(NSString *)value
{
    kIdentifierUse result;
    
    if( [value isEqualToString:@"usual"])
        result = kIdentifierUseUsual;
    else if( [value isEqualToString:@"official"])
        result = kIdentifierUseOfficial;
    else if( [value isEqualToString:@"temp"])
        result = kIdentifierUseTemp;
    else if( [value isEqualToString:@"secondary"])
        result = kIdentifierUseSecondary;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIdentifierUse" userInfo:nil];
    
    return result;
}

/*
 * Conversion of IdentifierUseto string
 */
+ (NSString *)stringIdentifierUse:(kIdentifierUse )name
{
    if( name==kIdentifierUseUsual )
        return @"usual";
    else if( name==kIdentifierUseOfficial )
        return @"official";
    else if( name==kIdentifierUseTemp )
        return @"temp";
    else if( name==kIdentifierUseSecondary )
        return @"secondary";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIdentifierUse" userInfo:nil];
}
/*
 * Conversion of kNarrativeStatusfrom string
 */
+ (kNarrativeStatus )parseNarrativeStatusString:(NSString *)value
{
    kNarrativeStatus result;
    
    if( [value isEqualToString:@"generated"])
        result = kNarrativeStatusGenerated;
    else if( [value isEqualToString:@"extensions"])
        result = kNarrativeStatusExtensions;
    else if( [value isEqualToString:@"additional"])
        result = kNarrativeStatusAdditional;
    else if( [value isEqualToString:@"empty"])
        result = kNarrativeStatusEmpty;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kNarrativeStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of NarrativeStatusto string
 */
+ (NSString *)stringNarrativeStatus:(kNarrativeStatus )name
{
    if( name==kNarrativeStatusGenerated )
        return @"generated";
    else if( name==kNarrativeStatusExtensions )
        return @"extensions";
    else if( name==kNarrativeStatusAdditional )
        return @"additional";
    else if( name==kNarrativeStatusEmpty )
        return @"empty";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kNarrativeStatus" userInfo:nil];
}
/*
 * Conversion of kQuantityCompararatorfrom string
 */
+ (kQuantityCompararator )parseQuantityCompararatorString:(NSString *)value
{
    kQuantityCompararator result;
    
    if( [value isEqualToString:@"<"])
        result = kQuantityCompararatorLessThan;
    else if( [value isEqualToString:@"<="])
        result = kQuantityCompararatorLessOrEqual;
    else if( [value isEqualToString:@">="])
        result = kQuantityCompararatorGreaterOrEqual;
    else if( [value isEqualToString:@">"])
        result = kQuantityCompararatorGreaterThan;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQuantityCompararator" userInfo:nil];
    
    return result;
}

/*
 * Conversion of QuantityCompararatorto string
 */
+ (NSString *)stringQuantityCompararator:(kQuantityCompararator )name
{
    if( name==kQuantityCompararatorLessThan )
        return @"<";
    else if( name==kQuantityCompararatorLessOrEqual )
        return @"<=";
    else if( name==kQuantityCompararatorGreaterOrEqual )
        return @">=";
    else if( name==kQuantityCompararatorGreaterThan )
        return @">";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQuantityCompararator" userInfo:nil];
}
/*
 * Conversion of kUnitsOfTimefrom string
 */
+ (kUnitsOfTime )parseUnitsOfTimeString:(NSString *)value
{
    kUnitsOfTime result;
    
    if( [value isEqualToString:@"s"])
        result = kUnitsOfTimeS;
    else if( [value isEqualToString:@"min"])
        result = kUnitsOfTimeMin;
    else if( [value isEqualToString:@"h"])
        result = kUnitsOfTimeH;
    else if( [value isEqualToString:@"d"])
        result = kUnitsOfTimeD;
    else if( [value isEqualToString:@"wk"])
        result = kUnitsOfTimeWk;
    else if( [value isEqualToString:@"mo"])
        result = kUnitsOfTimeMo;
    else if( [value isEqualToString:@"a"])
        result = kUnitsOfTimeA;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kUnitsOfTime" userInfo:nil];
    
    return result;
}

/*
 * Conversion of UnitsOfTimeto string
 */
+ (NSString *)stringUnitsOfTime:(kUnitsOfTime )name
{
    if( name==kUnitsOfTimeS )
        return @"s";
    else if( name==kUnitsOfTimeMin )
        return @"min";
    else if( name==kUnitsOfTimeH )
        return @"h";
    else if( name==kUnitsOfTimeD )
        return @"d";
    else if( name==kUnitsOfTimeWk )
        return @"wk";
    else if( name==kUnitsOfTimeMo )
        return @"mo";
    else if( name==kUnitsOfTimeA )
        return @"a";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kUnitsOfTime" userInfo:nil];
}
/*
 * Conversion of kEventTimingfrom string
 */
+ (kEventTiming )parseEventTimingString:(NSString *)value
{
    kEventTiming result;
    
    if( [value isEqualToString:@"HS"])
        result = kEventTimingHS;
    else if( [value isEqualToString:@"WAKE"])
        result = kEventTimingWAKE;
    else if( [value isEqualToString:@"AC"])
        result = kEventTimingAC;
    else if( [value isEqualToString:@"ACM"])
        result = kEventTimingACM;
    else if( [value isEqualToString:@"ACD"])
        result = kEventTimingACD;
    else if( [value isEqualToString:@"ACV"])
        result = kEventTimingACV;
    else if( [value isEqualToString:@"PC"])
        result = kEventTimingPC;
    else if( [value isEqualToString:@"PCM"])
        result = kEventTimingPCM;
    else if( [value isEqualToString:@"PCD"])
        result = kEventTimingPCD;
    else if( [value isEqualToString:@"PCV"])
        result = kEventTimingPCV;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEventTiming" userInfo:nil];
    
    return result;
}

/*
 * Conversion of EventTimingto string
 */
+ (NSString *)stringEventTiming:(kEventTiming )name
{
    if( name==kEventTimingHS )
        return @"HS";
    else if( name==kEventTimingWAKE )
        return @"WAKE";
    else if( name==kEventTimingAC )
        return @"AC";
    else if( name==kEventTimingACM )
        return @"ACM";
    else if( name==kEventTimingACD )
        return @"ACD";
    else if( name==kEventTimingACV )
        return @"ACV";
    else if( name==kEventTimingPC )
        return @"PC";
    else if( name==kEventTimingPCM )
        return @"PCM";
    else if( name==kEventTimingPCD )
        return @"PCD";
    else if( name==kEventTimingPCV )
        return @"PCV";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEventTiming" userInfo:nil];
}
/*
 * Conversion of kReactionSeverityfrom string
 */
+ (kReactionSeverity )parseReactionSeverityString:(NSString *)value
{
    kReactionSeverity result;
    
    if( [value isEqualToString:@"severe"])
        result = kReactionSeveritySevere;
    else if( [value isEqualToString:@"serious"])
        result = kReactionSeveritySerious;
    else if( [value isEqualToString:@"moderate"])
        result = kReactionSeverityModerate;
    else if( [value isEqualToString:@"minor"])
        result = kReactionSeverityMinor;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kReactionSeverity" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ReactionSeverityto string
 */
+ (NSString *)stringReactionSeverity:(kReactionSeverity )name
{
    if( name==kReactionSeveritySevere )
        return @"severe";
    else if( name==kReactionSeveritySerious )
        return @"serious";
    else if( name==kReactionSeverityModerate )
        return @"moderate";
    else if( name==kReactionSeverityMinor )
        return @"minor";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kReactionSeverity" userInfo:nil];
}
/*
 * Conversion of kExposureTypefrom string
 */
+ (kExposureType )parseExposureTypeString:(NSString *)value
{
    kExposureType result;
    
    if( [value isEqualToString:@"drugadmin"])
        result = kExposureTypeDrugadmin;
    else if( [value isEqualToString:@"immuniz"])
        result = kExposureTypeImmuniz;
    else if( [value isEqualToString:@"coincidental"])
        result = kExposureTypeCoincidental;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kExposureType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ExposureTypeto string
 */
+ (NSString *)stringExposureType:(kExposureType )name
{
    if( name==kExposureTypeDrugadmin )
        return @"drugadmin";
    else if( name==kExposureTypeImmuniz )
        return @"immuniz";
    else if( name==kExposureTypeCoincidental )
        return @"coincidental";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kExposureType" userInfo:nil];
}
/*
 * Conversion of kCausalityExpectationfrom string
 */
+ (kCausalityExpectation )parseCausalityExpectationString:(NSString *)value
{
    kCausalityExpectation result;
    
    if( [value isEqualToString:@"likely"])
        result = kCausalityExpectationLikely;
    else if( [value isEqualToString:@"unlikely"])
        result = kCausalityExpectationUnlikely;
    else if( [value isEqualToString:@"confirmed"])
        result = kCausalityExpectationConfirmed;
    else if( [value isEqualToString:@"unknown"])
        result = kCausalityExpectationUnknown;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCausalityExpectation" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CausalityExpectationto string
 */
+ (NSString *)stringCausalityExpectation:(kCausalityExpectation )name
{
    if( name==kCausalityExpectationLikely )
        return @"likely";
    else if( name==kCausalityExpectationUnlikely )
        return @"unlikely";
    else if( name==kCausalityExpectationConfirmed )
        return @"confirmed";
    else if( name==kCausalityExpectationUnknown )
        return @"unknown";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCausalityExpectation" userInfo:nil];
}
/*
 * Conversion of kAlertStatusfrom string
 */
+ (kAlertStatus )parseAlertStatusString:(NSString *)value
{
    kAlertStatus result;
    
    if( [value isEqualToString:@"active"])
        result = kAlertStatusActive;
    else if( [value isEqualToString:@"inactive"])
        result = kAlertStatusInactive;
    else if( [value isEqualToString:@"entered in error"])
        result = kAlertStatusEnteredInError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAlertStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of AlertStatusto string
 */
+ (NSString *)stringAlertStatus:(kAlertStatus )name
{
    if( name==kAlertStatusActive )
        return @"active";
    else if( name==kAlertStatusInactive )
        return @"inactive";
    else if( name==kAlertStatusEnteredInError )
        return @"entered in error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAlertStatus" userInfo:nil];
}
/*
 * Conversion of kSensitivityStatusfrom string
 */
+ (kSensitivityStatus )parseSensitivityStatusString:(NSString *)value
{
    kSensitivityStatus result;
    
    if( [value isEqualToString:@"suspected"])
        result = kSensitivityStatusSuspected;
    else if( [value isEqualToString:@"confirmed"])
        result = kSensitivityStatusConfirmed;
    else if( [value isEqualToString:@"refuted"])
        result = kSensitivityStatusRefuted;
    else if( [value isEqualToString:@"resolved"])
        result = kSensitivityStatusResolved;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSensitivityStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SensitivityStatusto string
 */
+ (NSString *)stringSensitivityStatus:(kSensitivityStatus )name
{
    if( name==kSensitivityStatusSuspected )
        return @"suspected";
    else if( name==kSensitivityStatusConfirmed )
        return @"confirmed";
    else if( name==kSensitivityStatusRefuted )
        return @"refuted";
    else if( name==kSensitivityStatusResolved )
        return @"resolved";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSensitivityStatus" userInfo:nil];
}
/*
 * Conversion of kCriticalityfrom string
 */
+ (kCriticality )parseCriticalityString:(NSString *)value
{
    kCriticality result;
    
    if( [value isEqualToString:@"fatal"])
        result = kCriticalityFatal;
    else if( [value isEqualToString:@"high"])
        result = kCriticalityHigh;
    else if( [value isEqualToString:@"medium"])
        result = kCriticalityMedium;
    else if( [value isEqualToString:@"low"])
        result = kCriticalityLow;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCriticality" userInfo:nil];
    
    return result;
}

/*
 * Conversion of Criticalityto string
 */
+ (NSString *)stringCriticality:(kCriticality )name
{
    if( name==kCriticalityFatal )
        return @"fatal";
    else if( name==kCriticalityHigh )
        return @"high";
    else if( name==kCriticalityMedium )
        return @"medium";
    else if( name==kCriticalityLow )
        return @"low";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCriticality" userInfo:nil];
}
/*
 * Conversion of kSensitivityTypefrom string
 */
+ (kSensitivityType )parseSensitivityTypeString:(NSString *)value
{
    kSensitivityType result;
    
    if( [value isEqualToString:@"allergy"])
        result = kSensitivityTypeAllergy;
    else if( [value isEqualToString:@"intolerance"])
        result = kSensitivityTypeIntolerance;
    else if( [value isEqualToString:@"unknown"])
        result = kSensitivityTypeUnknown;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSensitivityType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SensitivityTypeto string
 */
+ (NSString *)stringSensitivityType:(kSensitivityType )name
{
    if( name==kSensitivityTypeAllergy )
        return @"allergy";
    else if( name==kSensitivityTypeIntolerance )
        return @"intolerance";
    else if( name==kSensitivityTypeUnknown )
        return @"unknown";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSensitivityType" userInfo:nil];
}
/*
 * Conversion of kCarePlanStatusfrom string
 */
+ (kCarePlanStatus )parseCarePlanStatusString:(NSString *)value
{
    kCarePlanStatus result;
    
    if( [value isEqualToString:@"planned"])
        result = kCarePlanStatusPlanned;
    else if( [value isEqualToString:@"active"])
        result = kCarePlanStatusActive;
    else if( [value isEqualToString:@"completed"])
        result = kCarePlanStatusCompleted;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CarePlanStatusto string
 */
+ (NSString *)stringCarePlanStatus:(kCarePlanStatus )name
{
    if( name==kCarePlanStatusPlanned )
        return @"planned";
    else if( name==kCarePlanStatusActive )
        return @"active";
    else if( name==kCarePlanStatusCompleted )
        return @"completed";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanStatus" userInfo:nil];
}
/*
 * Conversion of kCarePlanActivityCategoryfrom string
 */
+ (kCarePlanActivityCategory )parseCarePlanActivityCategoryString:(NSString *)value
{
    kCarePlanActivityCategory result;
    
    if( [value isEqualToString:@"diet"])
        result = kCarePlanActivityCategoryDiet;
    else if( [value isEqualToString:@"drug"])
        result = kCarePlanActivityCategoryDrug;
    else if( [value isEqualToString:@"encounter"])
        result = kCarePlanActivityCategoryEncounter;
    else if( [value isEqualToString:@"observation"])
        result = kCarePlanActivityCategoryObservation;
    else if( [value isEqualToString:@"procedure"])
        result = kCarePlanActivityCategoryProcedure;
    else if( [value isEqualToString:@"supply"])
        result = kCarePlanActivityCategorySupply;
    else if( [value isEqualToString:@"other"])
        result = kCarePlanActivityCategoryOther;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanActivityCategory" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CarePlanActivityCategoryto string
 */
+ (NSString *)stringCarePlanActivityCategory:(kCarePlanActivityCategory )name
{
    if( name==kCarePlanActivityCategoryDiet )
        return @"diet";
    else if( name==kCarePlanActivityCategoryDrug )
        return @"drug";
    else if( name==kCarePlanActivityCategoryEncounter )
        return @"encounter";
    else if( name==kCarePlanActivityCategoryObservation )
        return @"observation";
    else if( name==kCarePlanActivityCategoryProcedure )
        return @"procedure";
    else if( name==kCarePlanActivityCategorySupply )
        return @"supply";
    else if( name==kCarePlanActivityCategoryOther )
        return @"other";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanActivityCategory" userInfo:nil];
}
/*
 * Conversion of kCarePlanGoalStatusfrom string
 */
+ (kCarePlanGoalStatus )parseCarePlanGoalStatusString:(NSString *)value
{
    kCarePlanGoalStatus result;
    
    if( [value isEqualToString:@"in progress"])
        result = kCarePlanGoalStatusInProgress;
    else if( [value isEqualToString:@"achieved"])
        result = kCarePlanGoalStatusAchieved;
    else if( [value isEqualToString:@"sustaining"])
        result = kCarePlanGoalStatusSustaining;
    else if( [value isEqualToString:@"cancelled"])
        result = kCarePlanGoalStatusCancelled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanGoalStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CarePlanGoalStatusto string
 */
+ (NSString *)stringCarePlanGoalStatus:(kCarePlanGoalStatus )name
{
    if( name==kCarePlanGoalStatusInProgress )
        return @"in progress";
    else if( name==kCarePlanGoalStatusAchieved )
        return @"achieved";
    else if( name==kCarePlanGoalStatusSustaining )
        return @"sustaining";
    else if( name==kCarePlanGoalStatusCancelled )
        return @"cancelled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanGoalStatus" userInfo:nil];
}
/*
 * Conversion of kCarePlanActivityStatusfrom string
 */
+ (kCarePlanActivityStatus )parseCarePlanActivityStatusString:(NSString *)value
{
    kCarePlanActivityStatus result;
    
    if( [value isEqualToString:@"not started"])
        result = kCarePlanActivityStatusNotStarted;
    else if( [value isEqualToString:@"scheduled"])
        result = kCarePlanActivityStatusScheduled;
    else if( [value isEqualToString:@"in progress"])
        result = kCarePlanActivityStatusInProgress;
    else if( [value isEqualToString:@"on hold"])
        result = kCarePlanActivityStatusOnHold;
    else if( [value isEqualToString:@"completed"])
        result = kCarePlanActivityStatusCompleted;
    else if( [value isEqualToString:@"cancelled"])
        result = kCarePlanActivityStatusCancelled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanActivityStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CarePlanActivityStatusto string
 */
+ (NSString *)stringCarePlanActivityStatus:(kCarePlanActivityStatus )name
{
    if( name==kCarePlanActivityStatusNotStarted )
        return @"not started";
    else if( name==kCarePlanActivityStatusScheduled )
        return @"scheduled";
    else if( name==kCarePlanActivityStatusInProgress )
        return @"in progress";
    else if( name==kCarePlanActivityStatusOnHold )
        return @"on hold";
    else if( name==kCarePlanActivityStatusCompleted )
        return @"completed";
    else if( name==kCarePlanActivityStatusCancelled )
        return @"cancelled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCarePlanActivityStatus" userInfo:nil];
}
/*
 * Conversion of kCompositionStatusfrom string
 */
+ (kCompositionStatus )parseCompositionStatusString:(NSString *)value
{
    kCompositionStatus result;
    
    if( [value isEqualToString:@"preliminary"])
        result = kCompositionStatusPreliminary;
    else if( [value isEqualToString:@"final"])
        result = kCompositionStatusFinal;
    else if( [value isEqualToString:@"appended"])
        result = kCompositionStatusAppended;
    else if( [value isEqualToString:@"amended"])
        result = kCompositionStatusAmended;
    else if( [value isEqualToString:@"entered in error"])
        result = kCompositionStatusEnteredInError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCompositionStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CompositionStatusto string
 */
+ (NSString *)stringCompositionStatus:(kCompositionStatus )name
{
    if( name==kCompositionStatusPreliminary )
        return @"preliminary";
    else if( name==kCompositionStatusFinal )
        return @"final";
    else if( name==kCompositionStatusAppended )
        return @"appended";
    else if( name==kCompositionStatusAmended )
        return @"amended";
    else if( name==kCompositionStatusEnteredInError )
        return @"entered in error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCompositionStatus" userInfo:nil];
}
/*
 * Conversion of kCompositionAttestationModefrom string
 */
+ (kCompositionAttestationMode )parseCompositionAttestationModeString:(NSString *)value
{
    kCompositionAttestationMode result;
    
    if( [value isEqualToString:@"personal"])
        result = kCompositionAttestationModePersonal;
    else if( [value isEqualToString:@"professional"])
        result = kCompositionAttestationModeProfessional;
    else if( [value isEqualToString:@"legal"])
        result = kCompositionAttestationModeLegal;
    else if( [value isEqualToString:@"official"])
        result = kCompositionAttestationModeOfficial;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCompositionAttestationMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of CompositionAttestationModeto string
 */
+ (NSString *)stringCompositionAttestationMode:(kCompositionAttestationMode )name
{
    if( name==kCompositionAttestationModePersonal )
        return @"personal";
    else if( name==kCompositionAttestationModeProfessional )
        return @"professional";
    else if( name==kCompositionAttestationModeLegal )
        return @"legal";
    else if( name==kCompositionAttestationModeOfficial )
        return @"official";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kCompositionAttestationMode" userInfo:nil];
}
/*
 * Conversion of kConceptMapEquivalencefrom string
 */
+ (kConceptMapEquivalence )parseConceptMapEquivalenceString:(NSString *)value
{
    kConceptMapEquivalence result;
    
    if( [value isEqualToString:@"equal"])
        result = kConceptMapEquivalenceEqual;
    else if( [value isEqualToString:@"equivalent"])
        result = kConceptMapEquivalenceEquivalent;
    else if( [value isEqualToString:@"wider"])
        result = kConceptMapEquivalenceWider;
    else if( [value isEqualToString:@"subsumes"])
        result = kConceptMapEquivalenceSubsumes;
    else if( [value isEqualToString:@"narrower"])
        result = kConceptMapEquivalenceNarrower;
    else if( [value isEqualToString:@"specialises"])
        result = kConceptMapEquivalenceSpecialises;
    else if( [value isEqualToString:@"inexact"])
        result = kConceptMapEquivalenceInexact;
    else if( [value isEqualToString:@"unmatched"])
        result = kConceptMapEquivalenceUnmatched;
    else if( [value isEqualToString:@"disjoint"])
        result = kConceptMapEquivalenceDisjoint;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConceptMapEquivalence" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConceptMapEquivalenceto string
 */
+ (NSString *)stringConceptMapEquivalence:(kConceptMapEquivalence )name
{
    if( name==kConceptMapEquivalenceEqual )
        return @"equal";
    else if( name==kConceptMapEquivalenceEquivalent )
        return @"equivalent";
    else if( name==kConceptMapEquivalenceWider )
        return @"wider";
    else if( name==kConceptMapEquivalenceSubsumes )
        return @"subsumes";
    else if( name==kConceptMapEquivalenceNarrower )
        return @"narrower";
    else if( name==kConceptMapEquivalenceSpecialises )
        return @"specialises";
    else if( name==kConceptMapEquivalenceInexact )
        return @"inexact";
    else if( name==kConceptMapEquivalenceUnmatched )
        return @"unmatched";
    else if( name==kConceptMapEquivalenceDisjoint )
        return @"disjoint";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConceptMapEquivalence" userInfo:nil];
}
/*
 * Conversion of kConditionStatusfrom string
 */
+ (kConditionStatus )parseConditionStatusString:(NSString *)value
{
    kConditionStatus result;
    
    if( [value isEqualToString:@"provisional"])
        result = kConditionStatusProvisional;
    else if( [value isEqualToString:@"working"])
        result = kConditionStatusWorking;
    else if( [value isEqualToString:@"confirmed"])
        result = kConditionStatusConfirmed;
    else if( [value isEqualToString:@"refuted"])
        result = kConditionStatusRefuted;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConditionStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConditionStatusto string
 */
+ (NSString *)stringConditionStatus:(kConditionStatus )name
{
    if( name==kConditionStatusProvisional )
        return @"provisional";
    else if( name==kConditionStatusWorking )
        return @"working";
    else if( name==kConditionStatusConfirmed )
        return @"confirmed";
    else if( name==kConditionStatusRefuted )
        return @"refuted";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConditionStatus" userInfo:nil];
}
/*
 * Conversion of kConditionRelationshipTypefrom string
 */
+ (kConditionRelationshipType )parseConditionRelationshipTypeString:(NSString *)value
{
    kConditionRelationshipType result;
    
    if( [value isEqualToString:@"due-to"])
        result = kConditionRelationshipTypeDueTo;
    else if( [value isEqualToString:@"following"])
        result = kConditionRelationshipTypeFollowing;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConditionRelationshipType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConditionRelationshipTypeto string
 */
+ (NSString *)stringConditionRelationshipType:(kConditionRelationshipType )name
{
    if( name==kConditionRelationshipTypeDueTo )
        return @"due-to";
    else if( name==kConditionRelationshipTypeFollowing )
        return @"following";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConditionRelationshipType" userInfo:nil];
}
/*
 * Conversion of kDocumentModefrom string
 */
+ (kDocumentMode )parseDocumentModeString:(NSString *)value
{
    kDocumentMode result;
    
    if( [value isEqualToString:@"producer"])
        result = kDocumentModeProducer;
    else if( [value isEqualToString:@"consumer"])
        result = kDocumentModeConsumer;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DocumentModeto string
 */
+ (NSString *)stringDocumentMode:(kDocumentMode )name
{
    if( name==kDocumentModeProducer )
        return @"producer";
    else if( name==kDocumentModeConsumer )
        return @"consumer";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentMode" userInfo:nil];
}
/*
 * Conversion of kRestfulConformanceModefrom string
 */
+ (kRestfulConformanceMode )parseRestfulConformanceModeString:(NSString *)value
{
    kRestfulConformanceMode result;
    
    if( [value isEqualToString:@"client"])
        result = kRestfulConformanceModeClient;
    else if( [value isEqualToString:@"server"])
        result = kRestfulConformanceModeServer;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulConformanceMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of RestfulConformanceModeto string
 */
+ (NSString *)stringRestfulConformanceMode:(kRestfulConformanceMode )name
{
    if( name==kRestfulConformanceModeClient )
        return @"client";
    else if( name==kRestfulConformanceModeServer )
        return @"server";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulConformanceMode" userInfo:nil];
}
/*
 * Conversion of kMessageTransportfrom string
 */
+ (kMessageTransport )parseMessageTransportString:(NSString *)value
{
    kMessageTransport result;
    
    if( [value isEqualToString:@"http"])
        result = kMessageTransportHttp;
    else if( [value isEqualToString:@"ftp"])
        result = kMessageTransportFtp;
    else if( [value isEqualToString:@"mllp"])
        result = kMessageTransportMllp;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMessageTransport" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MessageTransportto string
 */
+ (NSString *)stringMessageTransport:(kMessageTransport )name
{
    if( name==kMessageTransportHttp )
        return @"http";
    else if( name==kMessageTransportFtp )
        return @"ftp";
    else if( name==kMessageTransportMllp )
        return @"mllp";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMessageTransport" userInfo:nil];
}
/*
 * Conversion of kConformanceEventModefrom string
 */
+ (kConformanceEventMode )parseConformanceEventModeString:(NSString *)value
{
    kConformanceEventMode result;
    
    if( [value isEqualToString:@"sender"])
        result = kConformanceEventModeSender;
    else if( [value isEqualToString:@"receiver"])
        result = kConformanceEventModeReceiver;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConformanceEventMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConformanceEventModeto string
 */
+ (NSString *)stringConformanceEventMode:(kConformanceEventMode )name
{
    if( name==kConformanceEventModeSender )
        return @"sender";
    else if( name==kConformanceEventModeReceiver )
        return @"receiver";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConformanceEventMode" userInfo:nil];
}
/*
 * Conversion of kMessageSignificanceCategoryfrom string
 */
+ (kMessageSignificanceCategory )parseMessageSignificanceCategoryString:(NSString *)value
{
    kMessageSignificanceCategory result;
    
    if( [value isEqualToString:@"Consequence"])
        result = kMessageSignificanceCategoryConsequence;
    else if( [value isEqualToString:@"Currency"])
        result = kMessageSignificanceCategoryCurrency;
    else if( [value isEqualToString:@"Notification"])
        result = kMessageSignificanceCategoryNotification;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMessageSignificanceCategory" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MessageSignificanceCategoryto string
 */
+ (NSString *)stringMessageSignificanceCategory:(kMessageSignificanceCategory )name
{
    if( name==kMessageSignificanceCategoryConsequence )
        return @"Consequence";
    else if( name==kMessageSignificanceCategoryCurrency )
        return @"Currency";
    else if( name==kMessageSignificanceCategoryNotification )
        return @"Notification";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMessageSignificanceCategory" userInfo:nil];
}
/*
 * Conversion of kRestfulOperationTypefrom string
 */
+ (kRestfulOperationType )parseRestfulOperationTypeString:(NSString *)value
{
    kRestfulOperationType result;
    
    if( [value isEqualToString:@"read"])
        result = kRestfulOperationTypeRead;
    else if( [value isEqualToString:@"vread"])
        result = kRestfulOperationTypeVread;
    else if( [value isEqualToString:@"update"])
        result = kRestfulOperationTypeUpdate;
    else if( [value isEqualToString:@"delete"])
        result = kRestfulOperationTypeDelete;
    else if( [value isEqualToString:@"history-instance"])
        result = kRestfulOperationTypeHistoryInstance;
    else if( [value isEqualToString:@"validate"])
        result = kRestfulOperationTypeValidate;
    else if( [value isEqualToString:@"history-type"])
        result = kRestfulOperationTypeHistoryType;
    else if( [value isEqualToString:@"create"])
        result = kRestfulOperationTypeCreate;
    else if( [value isEqualToString:@"search-type"])
        result = kRestfulOperationTypeSearchType;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulOperationType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of RestfulOperationTypeto string
 */
+ (NSString *)stringRestfulOperationType:(kRestfulOperationType )name
{
    if( name==kRestfulOperationTypeRead )
        return @"read";
    else if( name==kRestfulOperationTypeVread )
        return @"vread";
    else if( name==kRestfulOperationTypeUpdate )
        return @"update";
    else if( name==kRestfulOperationTypeDelete )
        return @"delete";
    else if( name==kRestfulOperationTypeHistoryInstance )
        return @"history-instance";
    else if( name==kRestfulOperationTypeValidate )
        return @"validate";
    else if( name==kRestfulOperationTypeHistoryType )
        return @"history-type";
    else if( name==kRestfulOperationTypeCreate )
        return @"create";
    else if( name==kRestfulOperationTypeSearchType )
        return @"search-type";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulOperationType" userInfo:nil];
}
/*
 * Conversion of kConformanceStatementStatusfrom string
 */
+ (kConformanceStatementStatus )parseConformanceStatementStatusString:(NSString *)value
{
    kConformanceStatementStatus result;
    
    if( [value isEqualToString:@"draft"])
        result = kConformanceStatementStatusDraft;
    else if( [value isEqualToString:@"active"])
        result = kConformanceStatementStatusActive;
    else if( [value isEqualToString:@"retired"])
        result = kConformanceStatementStatusRetired;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConformanceStatementStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConformanceStatementStatusto string
 */
+ (NSString *)stringConformanceStatementStatus:(kConformanceStatementStatus )name
{
    if( name==kConformanceStatementStatusDraft )
        return @"draft";
    else if( name==kConformanceStatementStatusActive )
        return @"active";
    else if( name==kConformanceStatementStatusRetired )
        return @"retired";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConformanceStatementStatus" userInfo:nil];
}
/*
 * Conversion of kRestfulOperationSystemfrom string
 */
+ (kRestfulOperationSystem )parseRestfulOperationSystemString:(NSString *)value
{
    kRestfulOperationSystem result;
    
    if( [value isEqualToString:@"transaction"])
        result = kRestfulOperationSystemTransaction;
    else if( [value isEqualToString:@"search-system"])
        result = kRestfulOperationSystemSearchSystem;
    else if( [value isEqualToString:@"history-system"])
        result = kRestfulOperationSystemHistorySystem;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulOperationSystem" userInfo:nil];
    
    return result;
}

/*
 * Conversion of RestfulOperationSystemto string
 */
+ (NSString *)stringRestfulOperationSystem:(kRestfulOperationSystem )name
{
    if( name==kRestfulOperationSystemTransaction )
        return @"transaction";
    else if( name==kRestfulOperationSystemSearchSystem )
        return @"search-system";
    else if( name==kRestfulOperationSystemHistorySystem )
        return @"history-system";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulOperationSystem" userInfo:nil];
}
/*
 * Conversion of kSearchParamTypefrom string
 */
+ (kSearchParamType )parseSearchParamTypeString:(NSString *)value
{
    kSearchParamType result;
    
    if( [value isEqualToString:@"number"])
        result = kSearchParamTypeNumber;
    else if( [value isEqualToString:@"date"])
        result = kSearchParamTypeDate;
    else if( [value isEqualToString:@"string"])
        result = kSearchParamTypeString;
    else if( [value isEqualToString:@"token"])
        result = kSearchParamTypeToken;
    else if( [value isEqualToString:@"reference"])
        result = kSearchParamTypeReference;
    else if( [value isEqualToString:@"composite"])
        result = kSearchParamTypeComposite;
    else if( [value isEqualToString:@"variable"])
        result = kSearchParamTypeVariable;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSearchParamType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SearchParamTypeto string
 */
+ (NSString *)stringSearchParamType:(kSearchParamType )name
{
    if( name==kSearchParamTypeNumber )
        return @"number";
    else if( name==kSearchParamTypeDate )
        return @"date";
    else if( name==kSearchParamTypeString )
        return @"string";
    else if( name==kSearchParamTypeToken )
        return @"token";
    else if( name==kSearchParamTypeReference )
        return @"reference";
    else if( name==kSearchParamTypeComposite )
        return @"composite";
    else if( name==kSearchParamTypeVariable )
        return @"variable";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSearchParamType" userInfo:nil];
}
/*
 * Conversion of kRestfulSecurityServicefrom string
 */
+ (kRestfulSecurityService )parseRestfulSecurityServiceString:(NSString *)value
{
    kRestfulSecurityService result;
    
    if( [value isEqualToString:@"OAuth"])
        result = kRestfulSecurityServiceOAuth;
    else if( [value isEqualToString:@"OAuth2"])
        result = kRestfulSecurityServiceOAuth2;
    else if( [value isEqualToString:@"NTLM"])
        result = kRestfulSecurityServiceNTLM;
    else if( [value isEqualToString:@"Basic"])
        result = kRestfulSecurityServiceBasic;
    else if( [value isEqualToString:@"Kerberos"])
        result = kRestfulSecurityServiceKerberos;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulSecurityService" userInfo:nil];
    
    return result;
}

/*
 * Conversion of RestfulSecurityServiceto string
 */
+ (NSString *)stringRestfulSecurityService:(kRestfulSecurityService )name
{
    if( name==kRestfulSecurityServiceOAuth )
        return @"OAuth";
    else if( name==kRestfulSecurityServiceOAuth2 )
        return @"OAuth2";
    else if( name==kRestfulSecurityServiceNTLM )
        return @"NTLM";
    else if( name==kRestfulSecurityServiceBasic )
        return @"Basic";
    else if( name==kRestfulSecurityServiceKerberos )
        return @"Kerberos";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kRestfulSecurityService" userInfo:nil];
}
/*
 * Conversion of kDiagnosticOrderStatusfrom string
 */
+ (kDiagnosticOrderStatus )parseDiagnosticOrderStatusString:(NSString *)value
{
    kDiagnosticOrderStatus result;
    
    if( [value isEqualToString:@"requested"])
        result = kDiagnosticOrderStatusRequested;
    else if( [value isEqualToString:@"received"])
        result = kDiagnosticOrderStatusReceived;
    else if( [value isEqualToString:@"accepted"])
        result = kDiagnosticOrderStatusAccepted;
    else if( [value isEqualToString:@"in progress"])
        result = kDiagnosticOrderStatusInProgress;
    else if( [value isEqualToString:@"review"])
        result = kDiagnosticOrderStatusReview;
    else if( [value isEqualToString:@"completed"])
        result = kDiagnosticOrderStatusCompleted;
    else if( [value isEqualToString:@"suspended"])
        result = kDiagnosticOrderStatusSuspended;
    else if( [value isEqualToString:@"rejected"])
        result = kDiagnosticOrderStatusRejected;
    else if( [value isEqualToString:@"failed"])
        result = kDiagnosticOrderStatusFailed;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticOrderStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DiagnosticOrderStatusto string
 */
+ (NSString *)stringDiagnosticOrderStatus:(kDiagnosticOrderStatus )name
{
    if( name==kDiagnosticOrderStatusRequested )
        return @"requested";
    else if( name==kDiagnosticOrderStatusReceived )
        return @"received";
    else if( name==kDiagnosticOrderStatusAccepted )
        return @"accepted";
    else if( name==kDiagnosticOrderStatusInProgress )
        return @"in progress";
    else if( name==kDiagnosticOrderStatusReview )
        return @"review";
    else if( name==kDiagnosticOrderStatusCompleted )
        return @"completed";
    else if( name==kDiagnosticOrderStatusSuspended )
        return @"suspended";
    else if( name==kDiagnosticOrderStatusRejected )
        return @"rejected";
    else if( name==kDiagnosticOrderStatusFailed )
        return @"failed";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticOrderStatus" userInfo:nil];
}
/*
 * Conversion of kDiagnosticOrderPriorityfrom string
 */
+ (kDiagnosticOrderPriority )parseDiagnosticOrderPriorityString:(NSString *)value
{
    kDiagnosticOrderPriority result;
    
    if( [value isEqualToString:@"routine"])
        result = kDiagnosticOrderPriorityRoutine;
    else if( [value isEqualToString:@"urgent"])
        result = kDiagnosticOrderPriorityUrgent;
    else if( [value isEqualToString:@"stat"])
        result = kDiagnosticOrderPriorityStat;
    else if( [value isEqualToString:@"asap"])
        result = kDiagnosticOrderPriorityAsap;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticOrderPriority" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DiagnosticOrderPriorityto string
 */
+ (NSString *)stringDiagnosticOrderPriority:(kDiagnosticOrderPriority )name
{
    if( name==kDiagnosticOrderPriorityRoutine )
        return @"routine";
    else if( name==kDiagnosticOrderPriorityUrgent )
        return @"urgent";
    else if( name==kDiagnosticOrderPriorityStat )
        return @"stat";
    else if( name==kDiagnosticOrderPriorityAsap )
        return @"asap";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticOrderPriority" userInfo:nil];
}
/*
 * Conversion of kDiagnosticReportStatusfrom string
 */
+ (kDiagnosticReportStatus )parseDiagnosticReportStatusString:(NSString *)value
{
    kDiagnosticReportStatus result;
    
    if( [value isEqualToString:@"registered"])
        result = kDiagnosticReportStatusRegistered;
    else if( [value isEqualToString:@"partial"])
        result = kDiagnosticReportStatusPartial;
    else if( [value isEqualToString:@"final"])
        result = kDiagnosticReportStatusFinal;
    else if( [value isEqualToString:@"corrected"])
        result = kDiagnosticReportStatusCorrected;
    else if( [value isEqualToString:@"amended"])
        result = kDiagnosticReportStatusAmended;
    else if( [value isEqualToString:@"appended"])
        result = kDiagnosticReportStatusAppended;
    else if( [value isEqualToString:@"cancelled"])
        result = kDiagnosticReportStatusCancelled;
    else if( [value isEqualToString:@"entered in error"])
        result = kDiagnosticReportStatusEnteredInError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticReportStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DiagnosticReportStatusto string
 */
+ (NSString *)stringDiagnosticReportStatus:(kDiagnosticReportStatus )name
{
    if( name==kDiagnosticReportStatusRegistered )
        return @"registered";
    else if( name==kDiagnosticReportStatusPartial )
        return @"partial";
    else if( name==kDiagnosticReportStatusFinal )
        return @"final";
    else if( name==kDiagnosticReportStatusCorrected )
        return @"corrected";
    else if( name==kDiagnosticReportStatusAmended )
        return @"amended";
    else if( name==kDiagnosticReportStatusAppended )
        return @"appended";
    else if( name==kDiagnosticReportStatusCancelled )
        return @"cancelled";
    else if( name==kDiagnosticReportStatusEnteredInError )
        return @"entered in error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDiagnosticReportStatus" userInfo:nil];
}
/*
 * Conversion of kDocumentRelationshipTypefrom string
 */
+ (kDocumentRelationshipType )parseDocumentRelationshipTypeString:(NSString *)value
{
    kDocumentRelationshipType result;
    
    if( [value isEqualToString:@"replaces"])
        result = kDocumentRelationshipTypeReplaces;
    else if( [value isEqualToString:@"transforms"])
        result = kDocumentRelationshipTypeTransforms;
    else if( [value isEqualToString:@"signs"])
        result = kDocumentRelationshipTypeSigns;
    else if( [value isEqualToString:@"appends"])
        result = kDocumentRelationshipTypeAppends;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentRelationshipType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DocumentRelationshipTypeto string
 */
+ (NSString *)stringDocumentRelationshipType:(kDocumentRelationshipType )name
{
    if( name==kDocumentRelationshipTypeReplaces )
        return @"replaces";
    else if( name==kDocumentRelationshipTypeTransforms )
        return @"transforms";
    else if( name==kDocumentRelationshipTypeSigns )
        return @"signs";
    else if( name==kDocumentRelationshipTypeAppends )
        return @"appends";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentRelationshipType" userInfo:nil];
}
/*
 * Conversion of kDocumentReferenceStatusfrom string
 */
+ (kDocumentReferenceStatus )parseDocumentReferenceStatusString:(NSString *)value
{
    kDocumentReferenceStatus result;
    
    if( [value isEqualToString:@"current"])
        result = kDocumentReferenceStatusCurrent;
    else if( [value isEqualToString:@"superceded"])
        result = kDocumentReferenceStatusSuperceded;
    else if( [value isEqualToString:@"entered in error"])
        result = kDocumentReferenceStatusEnteredInError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentReferenceStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of DocumentReferenceStatusto string
 */
+ (NSString *)stringDocumentReferenceStatus:(kDocumentReferenceStatus )name
{
    if( name==kDocumentReferenceStatusCurrent )
        return @"current";
    else if( name==kDocumentReferenceStatusSuperceded )
        return @"superceded";
    else if( name==kDocumentReferenceStatusEnteredInError )
        return @"entered in error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kDocumentReferenceStatus" userInfo:nil];
}
/*
 * Conversion of kEncounterClassfrom string
 */
+ (kEncounterClass )parseEncounterClassString:(NSString *)value
{
    kEncounterClass result;
    
    if( [value isEqualToString:@"inpatient"])
        result = kEncounterClassInpatient;
    else if( [value isEqualToString:@"outpatient"])
        result = kEncounterClassOutpatient;
    else if( [value isEqualToString:@"ambulatory"])
        result = kEncounterClassAmbulatory;
    else if( [value isEqualToString:@"emergency"])
        result = kEncounterClassEmergency;
    else if( [value isEqualToString:@"home"])
        result = kEncounterClassHome;
    else if( [value isEqualToString:@"field"])
        result = kEncounterClassField;
    else if( [value isEqualToString:@"daytime"])
        result = kEncounterClassDaytime;
    else if( [value isEqualToString:@"virtual"])
        result = kEncounterClassVirtual;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEncounterClass" userInfo:nil];
    
    return result;
}

/*
 * Conversion of EncounterClassto string
 */
+ (NSString *)stringEncounterClass:(kEncounterClass )name
{
    if( name==kEncounterClassInpatient )
        return @"inpatient";
    else if( name==kEncounterClassOutpatient )
        return @"outpatient";
    else if( name==kEncounterClassAmbulatory )
        return @"ambulatory";
    else if( name==kEncounterClassEmergency )
        return @"emergency";
    else if( name==kEncounterClassHome )
        return @"home";
    else if( name==kEncounterClassField )
        return @"field";
    else if( name==kEncounterClassDaytime )
        return @"daytime";
    else if( name==kEncounterClassVirtual )
        return @"virtual";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEncounterClass" userInfo:nil];
}
/*
 * Conversion of kEncounterStatefrom string
 */
+ (kEncounterState )parseEncounterStateString:(NSString *)value
{
    kEncounterState result;
    
    if( [value isEqualToString:@"planned"])
        result = kEncounterStatePlanned;
    else if( [value isEqualToString:@"in progress"])
        result = kEncounterStateInProgress;
    else if( [value isEqualToString:@"onleave"])
        result = kEncounterStateOnleave;
    else if( [value isEqualToString:@"finished"])
        result = kEncounterStateFinished;
    else if( [value isEqualToString:@"cancelled"])
        result = kEncounterStateCancelled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEncounterState" userInfo:nil];
    
    return result;
}

/*
 * Conversion of EncounterStateto string
 */
+ (NSString *)stringEncounterState:(kEncounterState )name
{
    if( name==kEncounterStatePlanned )
        return @"planned";
    else if( name==kEncounterStateInProgress )
        return @"in progress";
    else if( name==kEncounterStateOnleave )
        return @"onleave";
    else if( name==kEncounterStateFinished )
        return @"finished";
    else if( name==kEncounterStateCancelled )
        return @"cancelled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kEncounterState" userInfo:nil];
}
/*
 * Conversion of kGroupTypefrom string
 */
+ (kGroupType )parseGroupTypeString:(NSString *)value
{
    kGroupType result;
    
    if( [value isEqualToString:@"person"])
        result = kGroupTypePerson;
    else if( [value isEqualToString:@"animal"])
        result = kGroupTypeAnimal;
    else if( [value isEqualToString:@"practitioner"])
        result = kGroupTypePractitioner;
    else if( [value isEqualToString:@"device"])
        result = kGroupTypeDevice;
    else if( [value isEqualToString:@"medication"])
        result = kGroupTypeMedication;
    else if( [value isEqualToString:@"substance"])
        result = kGroupTypeSubstance;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kGroupType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of GroupTypeto string
 */
+ (NSString *)stringGroupType:(kGroupType )name
{
    if( name==kGroupTypePerson )
        return @"person";
    else if( name==kGroupTypeAnimal )
        return @"animal";
    else if( name==kGroupTypePractitioner )
        return @"practitioner";
    else if( name==kGroupTypeDevice )
        return @"device";
    else if( name==kGroupTypeMedication )
        return @"medication";
    else if( name==kGroupTypeSubstance )
        return @"substance";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kGroupType" userInfo:nil];
}
/*
 * Conversion of kImagingModalityfrom string
 */
+ (kImagingModality )parseImagingModalityString:(NSString *)value
{
    kImagingModality result;
    
    if( [value isEqualToString:@"AR"])
        result = kImagingModalityAR;
    else if( [value isEqualToString:@"BMD"])
        result = kImagingModalityBMD;
    else if( [value isEqualToString:@"BDUS"])
        result = kImagingModalityBDUS;
    else if( [value isEqualToString:@"EPS"])
        result = kImagingModalityEPS;
    else if( [value isEqualToString:@"CR"])
        result = kImagingModalityCR;
    else if( [value isEqualToString:@"CT"])
        result = kImagingModalityCT;
    else if( [value isEqualToString:@"DX"])
        result = kImagingModalityDX;
    else if( [value isEqualToString:@"ECG"])
        result = kImagingModalityECG;
    else if( [value isEqualToString:@"ES"])
        result = kImagingModalityES;
    else if( [value isEqualToString:@"XC"])
        result = kImagingModalityXC;
    else if( [value isEqualToString:@"GM"])
        result = kImagingModalityGM;
    else if( [value isEqualToString:@"HD"])
        result = kImagingModalityHD;
    else if( [value isEqualToString:@"IO"])
        result = kImagingModalityIO;
    else if( [value isEqualToString:@"IVOCT"])
        result = kImagingModalityIVOCT;
    else if( [value isEqualToString:@"IVUS"])
        result = kImagingModalityIVUS;
    else if( [value isEqualToString:@"KER"])
        result = kImagingModalityKER;
    else if( [value isEqualToString:@"LEN"])
        result = kImagingModalityLEN;
    else if( [value isEqualToString:@"MR"])
        result = kImagingModalityMR;
    else if( [value isEqualToString:@"MG"])
        result = kImagingModalityMG;
    else if( [value isEqualToString:@"NM"])
        result = kImagingModalityNM;
    else if( [value isEqualToString:@"OAM"])
        result = kImagingModalityOAM;
    else if( [value isEqualToString:@"OCT"])
        result = kImagingModalityOCT;
    else if( [value isEqualToString:@"OPM"])
        result = kImagingModalityOPM;
    else if( [value isEqualToString:@"OP"])
        result = kImagingModalityOP;
    else if( [value isEqualToString:@"OPR"])
        result = kImagingModalityOPR;
    else if( [value isEqualToString:@"OPT"])
        result = kImagingModalityOPT;
    else if( [value isEqualToString:@"OPV"])
        result = kImagingModalityOPV;
    else if( [value isEqualToString:@"PX"])
        result = kImagingModalityPX;
    else if( [value isEqualToString:@"PT"])
        result = kImagingModalityPT;
    else if( [value isEqualToString:@"RF"])
        result = kImagingModalityRF;
    else if( [value isEqualToString:@"RG"])
        result = kImagingModalityRG;
    else if( [value isEqualToString:@"SM"])
        result = kImagingModalitySM;
    else if( [value isEqualToString:@"SRF"])
        result = kImagingModalitySRF;
    else if( [value isEqualToString:@"US"])
        result = kImagingModalityUS;
    else if( [value isEqualToString:@"VA"])
        result = kImagingModalityVA;
    else if( [value isEqualToString:@"XA"])
        result = kImagingModalityXA;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kImagingModality" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ImagingModalityto string
 */
+ (NSString *)stringImagingModality:(kImagingModality )name
{
    if( name==kImagingModalityAR )
        return @"AR";
    else if( name==kImagingModalityBMD )
        return @"BMD";
    else if( name==kImagingModalityBDUS )
        return @"BDUS";
    else if( name==kImagingModalityEPS )
        return @"EPS";
    else if( name==kImagingModalityCR )
        return @"CR";
    else if( name==kImagingModalityCT )
        return @"CT";
    else if( name==kImagingModalityDX )
        return @"DX";
    else if( name==kImagingModalityECG )
        return @"ECG";
    else if( name==kImagingModalityES )
        return @"ES";
    else if( name==kImagingModalityXC )
        return @"XC";
    else if( name==kImagingModalityGM )
        return @"GM";
    else if( name==kImagingModalityHD )
        return @"HD";
    else if( name==kImagingModalityIO )
        return @"IO";
    else if( name==kImagingModalityIVOCT )
        return @"IVOCT";
    else if( name==kImagingModalityIVUS )
        return @"IVUS";
    else if( name==kImagingModalityKER )
        return @"KER";
    else if( name==kImagingModalityLEN )
        return @"LEN";
    else if( name==kImagingModalityMR )
        return @"MR";
    else if( name==kImagingModalityMG )
        return @"MG";
    else if( name==kImagingModalityNM )
        return @"NM";
    else if( name==kImagingModalityOAM )
        return @"OAM";
    else if( name==kImagingModalityOCT )
        return @"OCT";
    else if( name==kImagingModalityOPM )
        return @"OPM";
    else if( name==kImagingModalityOP )
        return @"OP";
    else if( name==kImagingModalityOPR )
        return @"OPR";
    else if( name==kImagingModalityOPT )
        return @"OPT";
    else if( name==kImagingModalityOPV )
        return @"OPV";
    else if( name==kImagingModalityPX )
        return @"PX";
    else if( name==kImagingModalityPT )
        return @"PT";
    else if( name==kImagingModalityRF )
        return @"RF";
    else if( name==kImagingModalityRG )
        return @"RG";
    else if( name==kImagingModalitySM )
        return @"SM";
    else if( name==kImagingModalitySRF )
        return @"SRF";
    else if( name==kImagingModalityUS )
        return @"US";
    else if( name==kImagingModalityVA )
        return @"VA";
    else if( name==kImagingModalityXA )
        return @"XA";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kImagingModality" userInfo:nil];
}
/*
 * Conversion of kInstanceAvailabilityfrom string
 */
+ (kInstanceAvailability )parseInstanceAvailabilityString:(NSString *)value
{
    kInstanceAvailability result;
    
    if( [value isEqualToString:@"ONLINE"])
        result = kInstanceAvailabilityONLINE;
    else if( [value isEqualToString:@"OFFLINE"])
        result = kInstanceAvailabilityOFFLINE;
    else if( [value isEqualToString:@"NEARLINE"])
        result = kInstanceAvailabilityNEARLINE;
    else if( [value isEqualToString:@"UNAVAILABLE"])
        result = kInstanceAvailabilityUNAVAILABLE;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kInstanceAvailability" userInfo:nil];
    
    return result;
}

/*
 * Conversion of InstanceAvailabilityto string
 */
+ (NSString *)stringInstanceAvailability:(kInstanceAvailability )name
{
    if( name==kInstanceAvailabilityONLINE )
        return @"ONLINE";
    else if( name==kInstanceAvailabilityOFFLINE )
        return @"OFFLINE";
    else if( name==kInstanceAvailabilityNEARLINE )
        return @"NEARLINE";
    else if( name==kInstanceAvailabilityUNAVAILABLE )
        return @"UNAVAILABLE";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kInstanceAvailability" userInfo:nil];
}
/*
 * Conversion of kModalityfrom string
 */
+ (kModality )parseModalityString:(NSString *)value
{
    kModality result;
    
    if( [value isEqualToString:@"AR"])
        result = kModalityAR;
    else if( [value isEqualToString:@"AU"])
        result = kModalityAU;
    else if( [value isEqualToString:@"BDUS"])
        result = kModalityBDUS;
    else if( [value isEqualToString:@"BI"])
        result = kModalityBI;
    else if( [value isEqualToString:@"BMD"])
        result = kModalityBMD;
    else if( [value isEqualToString:@"CR"])
        result = kModalityCR;
    else if( [value isEqualToString:@"CT"])
        result = kModalityCT;
    else if( [value isEqualToString:@"DG"])
        result = kModalityDG;
    else if( [value isEqualToString:@"DX"])
        result = kModalityDX;
    else if( [value isEqualToString:@"ECG"])
        result = kModalityECG;
    else if( [value isEqualToString:@"EPS"])
        result = kModalityEPS;
    else if( [value isEqualToString:@"ES"])
        result = kModalityES;
    else if( [value isEqualToString:@"GM"])
        result = kModalityGM;
    else if( [value isEqualToString:@"HC"])
        result = kModalityHC;
    else if( [value isEqualToString:@"HD"])
        result = kModalityHD;
    else if( [value isEqualToString:@"IO"])
        result = kModalityIO;
    else if( [value isEqualToString:@"IVOCT"])
        result = kModalityIVOCT;
    else if( [value isEqualToString:@"IVUS"])
        result = kModalityIVUS;
    else if( [value isEqualToString:@"KER"])
        result = kModalityKER;
    else if( [value isEqualToString:@"KO"])
        result = kModalityKO;
    else if( [value isEqualToString:@"LEN"])
        result = kModalityLEN;
    else if( [value isEqualToString:@"LS"])
        result = kModalityLS;
    else if( [value isEqualToString:@"MG"])
        result = kModalityMG;
    else if( [value isEqualToString:@"MR"])
        result = kModalityMR;
    else if( [value isEqualToString:@"NM"])
        result = kModalityNM;
    else if( [value isEqualToString:@"OAM"])
        result = kModalityOAM;
    else if( [value isEqualToString:@"OCT"])
        result = kModalityOCT;
    else if( [value isEqualToString:@"OP"])
        result = kModalityOP;
    else if( [value isEqualToString:@"OPM"])
        result = kModalityOPM;
    else if( [value isEqualToString:@"OPT"])
        result = kModalityOPT;
    else if( [value isEqualToString:@"OPV"])
        result = kModalityOPV;
    else if( [value isEqualToString:@"OT"])
        result = kModalityOT;
    else if( [value isEqualToString:@"PR"])
        result = kModalityPR;
    else if( [value isEqualToString:@"PT"])
        result = kModalityPT;
    else if( [value isEqualToString:@"PX"])
        result = kModalityPX;
    else if( [value isEqualToString:@"REG"])
        result = kModalityREG;
    else if( [value isEqualToString:@"RF"])
        result = kModalityRF;
    else if( [value isEqualToString:@"RG"])
        result = kModalityRG;
    else if( [value isEqualToString:@"RTDOSE"])
        result = kModalityRTDOSE;
    else if( [value isEqualToString:@"RTIMAGE"])
        result = kModalityRTIMAGE;
    else if( [value isEqualToString:@"RTPLAN"])
        result = kModalityRTPLAN;
    else if( [value isEqualToString:@"RTRECORD"])
        result = kModalityRTRECORD;
    else if( [value isEqualToString:@"RTSTRUCT"])
        result = kModalityRTSTRUCT;
    else if( [value isEqualToString:@"SEG"])
        result = kModalitySEG;
    else if( [value isEqualToString:@"SM"])
        result = kModalitySM;
    else if( [value isEqualToString:@"SMR"])
        result = kModalitySMR;
    else if( [value isEqualToString:@"SR"])
        result = kModalitySR;
    else if( [value isEqualToString:@"SRF"])
        result = kModalitySRF;
    else if( [value isEqualToString:@"TG"])
        result = kModalityTG;
    else if( [value isEqualToString:@"US"])
        result = kModalityUS;
    else if( [value isEqualToString:@"VA"])
        result = kModalityVA;
    else if( [value isEqualToString:@"XA"])
        result = kModalityXA;
    else if( [value isEqualToString:@"XC"])
        result = kModalityXC;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kModality" userInfo:nil];
    
    return result;
}

/*
 * Conversion of Modalityto string
 */
+ (NSString *)stringModality:(kModality )name
{
    if( name==kModalityAR )
        return @"AR";
    else if( name==kModalityAU )
        return @"AU";
    else if( name==kModalityBDUS )
        return @"BDUS";
    else if( name==kModalityBI )
        return @"BI";
    else if( name==kModalityBMD )
        return @"BMD";
    else if( name==kModalityCR )
        return @"CR";
    else if( name==kModalityCT )
        return @"CT";
    else if( name==kModalityDG )
        return @"DG";
    else if( name==kModalityDX )
        return @"DX";
    else if( name==kModalityECG )
        return @"ECG";
    else if( name==kModalityEPS )
        return @"EPS";
    else if( name==kModalityES )
        return @"ES";
    else if( name==kModalityGM )
        return @"GM";
    else if( name==kModalityHC )
        return @"HC";
    else if( name==kModalityHD )
        return @"HD";
    else if( name==kModalityIO )
        return @"IO";
    else if( name==kModalityIVOCT )
        return @"IVOCT";
    else if( name==kModalityIVUS )
        return @"IVUS";
    else if( name==kModalityKER )
        return @"KER";
    else if( name==kModalityKO )
        return @"KO";
    else if( name==kModalityLEN )
        return @"LEN";
    else if( name==kModalityLS )
        return @"LS";
    else if( name==kModalityMG )
        return @"MG";
    else if( name==kModalityMR )
        return @"MR";
    else if( name==kModalityNM )
        return @"NM";
    else if( name==kModalityOAM )
        return @"OAM";
    else if( name==kModalityOCT )
        return @"OCT";
    else if( name==kModalityOP )
        return @"OP";
    else if( name==kModalityOPM )
        return @"OPM";
    else if( name==kModalityOPT )
        return @"OPT";
    else if( name==kModalityOPV )
        return @"OPV";
    else if( name==kModalityOT )
        return @"OT";
    else if( name==kModalityPR )
        return @"PR";
    else if( name==kModalityPT )
        return @"PT";
    else if( name==kModalityPX )
        return @"PX";
    else if( name==kModalityREG )
        return @"REG";
    else if( name==kModalityRF )
        return @"RF";
    else if( name==kModalityRG )
        return @"RG";
    else if( name==kModalityRTDOSE )
        return @"RTDOSE";
    else if( name==kModalityRTIMAGE )
        return @"RTIMAGE";
    else if( name==kModalityRTPLAN )
        return @"RTPLAN";
    else if( name==kModalityRTRECORD )
        return @"RTRECORD";
    else if( name==kModalityRTSTRUCT )
        return @"RTSTRUCT";
    else if( name==kModalitySEG )
        return @"SEG";
    else if( name==kModalitySM )
        return @"SM";
    else if( name==kModalitySMR )
        return @"SMR";
    else if( name==kModalitySR )
        return @"SR";
    else if( name==kModalitySRF )
        return @"SRF";
    else if( name==kModalityTG )
        return @"TG";
    else if( name==kModalityUS )
        return @"US";
    else if( name==kModalityVA )
        return @"VA";
    else if( name==kModalityXA )
        return @"XA";
    else if( name==kModalityXC )
        return @"XC";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kModality" userInfo:nil];
}
/*
 * Conversion of kListModefrom string
 */
+ (kListMode )parseListModeString:(NSString *)value
{
    kListMode result;
    
    if( [value isEqualToString:@"working"])
        result = kListModeWorking;
    else if( [value isEqualToString:@"snapshot"])
        result = kListModeSnapshot;
    else if( [value isEqualToString:@"changes"])
        result = kListModeChanges;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kListMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ListModeto string
 */
+ (NSString *)stringListMode:(kListMode )name
{
    if( name==kListModeWorking )
        return @"working";
    else if( name==kListModeSnapshot )
        return @"snapshot";
    else if( name==kListModeChanges )
        return @"changes";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kListMode" userInfo:nil];
}
/*
 * Conversion of kLocationStatusfrom string
 */
+ (kLocationStatus )parseLocationStatusString:(NSString *)value
{
    kLocationStatus result;
    
    if( [value isEqualToString:@"active"])
        result = kLocationStatusActive;
    else if( [value isEqualToString:@"suspended"])
        result = kLocationStatusSuspended;
    else if( [value isEqualToString:@"inactive"])
        result = kLocationStatusInactive;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLocationStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of LocationStatusto string
 */
+ (NSString *)stringLocationStatus:(kLocationStatus )name
{
    if( name==kLocationStatusActive )
        return @"active";
    else if( name==kLocationStatusSuspended )
        return @"suspended";
    else if( name==kLocationStatusInactive )
        return @"inactive";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLocationStatus" userInfo:nil];
}
/*
 * Conversion of kLocationModefrom string
 */
+ (kLocationMode )parseLocationModeString:(NSString *)value
{
    kLocationMode result;
    
    if( [value isEqualToString:@"instance"])
        result = kLocationModeInstance;
    else if( [value isEqualToString:@"kind"])
        result = kLocationModeKind;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLocationMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of LocationModeto string
 */
+ (NSString *)stringLocationMode:(kLocationMode )name
{
    if( name==kLocationModeInstance )
        return @"instance";
    else if( name==kLocationModeKind )
        return @"kind";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLocationMode" userInfo:nil];
}
/*
 * Conversion of kMediaTypefrom string
 */
+ (kMediaType )parseMediaTypeString:(NSString *)value
{
    kMediaType result;
    
    if( [value isEqualToString:@"photo"])
        result = kMediaTypePhoto;
    else if( [value isEqualToString:@"video"])
        result = kMediaTypeVideo;
    else if( [value isEqualToString:@"audio"])
        result = kMediaTypeAudio;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMediaType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MediaTypeto string
 */
+ (NSString *)stringMediaType:(kMediaType )name
{
    if( name==kMediaTypePhoto )
        return @"photo";
    else if( name==kMediaTypeVideo )
        return @"video";
    else if( name==kMediaTypeAudio )
        return @"audio";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMediaType" userInfo:nil];
}
/*
 * Conversion of kMedicationKindfrom string
 */
+ (kMedicationKind )parseMedicationKindString:(NSString *)value
{
    kMedicationKind result;
    
    if( [value isEqualToString:@"product"])
        result = kMedicationKindProduct;
    else if( [value isEqualToString:@"package"])
        result = kMedicationKindPackage;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationKind" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MedicationKindto string
 */
+ (NSString *)stringMedicationKind:(kMedicationKind )name
{
    if( name==kMedicationKindProduct )
        return @"product";
    else if( name==kMedicationKindPackage )
        return @"package";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationKind" userInfo:nil];
}
/*
 * Conversion of kMedicationAdministrationStatusfrom string
 */
+ (kMedicationAdministrationStatus )parseMedicationAdministrationStatusString:(NSString *)value
{
    kMedicationAdministrationStatus result;
    
    if( [value isEqualToString:@"in progress"])
        result = kMedicationAdministrationStatusInProgress;
    else if( [value isEqualToString:@"on hold"])
        result = kMedicationAdministrationStatusOnHold;
    else if( [value isEqualToString:@"completed"])
        result = kMedicationAdministrationStatusCompleted;
    else if( [value isEqualToString:@"entered in error"])
        result = kMedicationAdministrationStatusEnteredInError;
    else if( [value isEqualToString:@"stopped"])
        result = kMedicationAdministrationStatusStopped;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationAdministrationStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MedicationAdministrationStatusto string
 */
+ (NSString *)stringMedicationAdministrationStatus:(kMedicationAdministrationStatus )name
{
    if( name==kMedicationAdministrationStatusInProgress )
        return @"in progress";
    else if( name==kMedicationAdministrationStatusOnHold )
        return @"on hold";
    else if( name==kMedicationAdministrationStatusCompleted )
        return @"completed";
    else if( name==kMedicationAdministrationStatusEnteredInError )
        return @"entered in error";
    else if( name==kMedicationAdministrationStatusStopped )
        return @"stopped";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationAdministrationStatus" userInfo:nil];
}
/*
 * Conversion of kMedicationDispenseStatusfrom string
 */
+ (kMedicationDispenseStatus )parseMedicationDispenseStatusString:(NSString *)value
{
    kMedicationDispenseStatus result;
    
    if( [value isEqualToString:@"in progress"])
        result = kMedicationDispenseStatusInProgress;
    else if( [value isEqualToString:@"on hold"])
        result = kMedicationDispenseStatusOnHold;
    else if( [value isEqualToString:@"completed"])
        result = kMedicationDispenseStatusCompleted;
    else if( [value isEqualToString:@"entered in error"])
        result = kMedicationDispenseStatusEnteredInError;
    else if( [value isEqualToString:@"stopped"])
        result = kMedicationDispenseStatusStopped;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationDispenseStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MedicationDispenseStatusto string
 */
+ (NSString *)stringMedicationDispenseStatus:(kMedicationDispenseStatus )name
{
    if( name==kMedicationDispenseStatusInProgress )
        return @"in progress";
    else if( name==kMedicationDispenseStatusOnHold )
        return @"on hold";
    else if( name==kMedicationDispenseStatusCompleted )
        return @"completed";
    else if( name==kMedicationDispenseStatusEnteredInError )
        return @"entered in error";
    else if( name==kMedicationDispenseStatusStopped )
        return @"stopped";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationDispenseStatus" userInfo:nil];
}
/*
 * Conversion of kMedicationPrescriptionStatusfrom string
 */
+ (kMedicationPrescriptionStatus )parseMedicationPrescriptionStatusString:(NSString *)value
{
    kMedicationPrescriptionStatus result;
    
    if( [value isEqualToString:@"active"])
        result = kMedicationPrescriptionStatusActive;
    else if( [value isEqualToString:@"on hold"])
        result = kMedicationPrescriptionStatusOnHold;
    else if( [value isEqualToString:@"completed"])
        result = kMedicationPrescriptionStatusCompleted;
    else if( [value isEqualToString:@"entered in error"])
        result = kMedicationPrescriptionStatusEnteredInError;
    else if( [value isEqualToString:@"stopped"])
        result = kMedicationPrescriptionStatusStopped;
    else if( [value isEqualToString:@"superceded"])
        result = kMedicationPrescriptionStatusSuperceded;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationPrescriptionStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of MedicationPrescriptionStatusto string
 */
+ (NSString *)stringMedicationPrescriptionStatus:(kMedicationPrescriptionStatus )name
{
    if( name==kMedicationPrescriptionStatusActive )
        return @"active";
    else if( name==kMedicationPrescriptionStatusOnHold )
        return @"on hold";
    else if( name==kMedicationPrescriptionStatusCompleted )
        return @"completed";
    else if( name==kMedicationPrescriptionStatusEnteredInError )
        return @"entered in error";
    else if( name==kMedicationPrescriptionStatusStopped )
        return @"stopped";
    else if( name==kMedicationPrescriptionStatusSuperceded )
        return @"superceded";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kMedicationPrescriptionStatus" userInfo:nil];
}
/*
 * Conversion of kResponseTypefrom string
 */
+ (kResponseType )parseResponseTypeString:(NSString *)value
{
    kResponseType result;
    
    if( [value isEqualToString:@"ok"])
        result = kResponseTypeOk;
    else if( [value isEqualToString:@"transient-error"])
        result = kResponseTypeTransientError;
    else if( [value isEqualToString:@"fatal-error"])
        result = kResponseTypeFatalError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResponseType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ResponseTypeto string
 */
+ (NSString *)stringResponseType:(kResponseType )name
{
    if( name==kResponseTypeOk )
        return @"ok";
    else if( name==kResponseTypeTransientError )
        return @"transient-error";
    else if( name==kResponseTypeFatalError )
        return @"fatal-error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResponseType" userInfo:nil];
}
/*
 * Conversion of kObservationReliabilityfrom string
 */
+ (kObservationReliability )parseObservationReliabilityString:(NSString *)value
{
    kObservationReliability result;
    
    if( [value isEqualToString:@"ok"])
        result = kObservationReliabilityOk;
    else if( [value isEqualToString:@"ongoing"])
        result = kObservationReliabilityOngoing;
    else if( [value isEqualToString:@"early"])
        result = kObservationReliabilityEarly;
    else if( [value isEqualToString:@"questionable"])
        result = kObservationReliabilityQuestionable;
    else if( [value isEqualToString:@"calibrating"])
        result = kObservationReliabilityCalibrating;
    else if( [value isEqualToString:@"error"])
        result = kObservationReliabilityError;
    else if( [value isEqualToString:@"unknown"])
        result = kObservationReliabilityUnknown;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kObservationReliability" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ObservationReliabilityto string
 */
+ (NSString *)stringObservationReliability:(kObservationReliability )name
{
    if( name==kObservationReliabilityOk )
        return @"ok";
    else if( name==kObservationReliabilityOngoing )
        return @"ongoing";
    else if( name==kObservationReliabilityEarly )
        return @"early";
    else if( name==kObservationReliabilityQuestionable )
        return @"questionable";
    else if( name==kObservationReliabilityCalibrating )
        return @"calibrating";
    else if( name==kObservationReliabilityError )
        return @"error";
    else if( name==kObservationReliabilityUnknown )
        return @"unknown";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kObservationReliability" userInfo:nil];
}
/*
 * Conversion of kObservationStatusfrom string
 */
+ (kObservationStatus )parseObservationStatusString:(NSString *)value
{
    kObservationStatus result;
    
    if( [value isEqualToString:@"registered"])
        result = kObservationStatusRegistered;
    else if( [value isEqualToString:@"preliminary"])
        result = kObservationStatusPreliminary;
    else if( [value isEqualToString:@"final"])
        result = kObservationStatusFinal;
    else if( [value isEqualToString:@"amended"])
        result = kObservationStatusAmended;
    else if( [value isEqualToString:@"cancelled"])
        result = kObservationStatusCancelled;
    else if( [value isEqualToString:@"entered in error"])
        result = kObservationStatusEnteredInError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kObservationStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ObservationStatusto string
 */
+ (NSString *)stringObservationStatus:(kObservationStatus )name
{
    if( name==kObservationStatusRegistered )
        return @"registered";
    else if( name==kObservationStatusPreliminary )
        return @"preliminary";
    else if( name==kObservationStatusFinal )
        return @"final";
    else if( name==kObservationStatusAmended )
        return @"amended";
    else if( name==kObservationStatusCancelled )
        return @"cancelled";
    else if( name==kObservationStatusEnteredInError )
        return @"entered in error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kObservationStatus" userInfo:nil];
}
/*
 * Conversion of kIssueTypefrom string
 */
+ (kIssueType )parseIssueTypeString:(NSString *)value
{
    kIssueType result;
    
    if( [value isEqualToString:@"invalid"])
        result = kIssueTypeInvalid;
    else if( [value isEqualToString:@"structure"])
        result = kIssueTypeStructure;
    else if( [value isEqualToString:@"required"])
        result = kIssueTypeRequired;
    else if( [value isEqualToString:@"value"])
        result = kIssueTypeValue;
    else if( [value isEqualToString:@"invariant"])
        result = kIssueTypeInvariant;
    else if( [value isEqualToString:@"security"])
        result = kIssueTypeSecurity;
    else if( [value isEqualToString:@"login"])
        result = kIssueTypeLogin;
    else if( [value isEqualToString:@"unknown"])
        result = kIssueTypeUnknown;
    else if( [value isEqualToString:@"expired"])
        result = kIssueTypeExpired;
    else if( [value isEqualToString:@"forbidden"])
        result = kIssueTypeForbidden;
    else if( [value isEqualToString:@"processing"])
        result = kIssueTypeProcessing;
    else if( [value isEqualToString:@"not-supported"])
        result = kIssueTypeNotSupported;
    else if( [value isEqualToString:@"duplicate"])
        result = kIssueTypeDuplicate;
    else if( [value isEqualToString:@"not-found"])
        result = kIssueTypeNotFound;
    else if( [value isEqualToString:@"too-long"])
        result = kIssueTypeTooLong;
    else if( [value isEqualToString:@"code-unknown"])
        result = kIssueTypeCodeUnknown;
    else if( [value isEqualToString:@"extension"])
        result = kIssueTypeExtension;
    else if( [value isEqualToString:@"too-costly"])
        result = kIssueTypeTooCostly;
    else if( [value isEqualToString:@"business-rule"])
        result = kIssueTypeBusinessRule;
    else if( [value isEqualToString:@"conflict"])
        result = kIssueTypeConflict;
    else if( [value isEqualToString:@"transient"])
        result = kIssueTypeTransient;
    else if( [value isEqualToString:@"lock-error"])
        result = kIssueTypeLockError;
    else if( [value isEqualToString:@"no-store"])
        result = kIssueTypeNoStore;
    else if( [value isEqualToString:@"exception"])
        result = kIssueTypeException;
    else if( [value isEqualToString:@"timeout"])
        result = kIssueTypeTimeout;
    else if( [value isEqualToString:@"throttled"])
        result = kIssueTypeThrottled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIssueType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of IssueTypeto string
 */
+ (NSString *)stringIssueType:(kIssueType )name
{
    if( name==kIssueTypeInvalid )
        return @"invalid";
    else if( name==kIssueTypeStructure )
        return @"structure";
    else if( name==kIssueTypeRequired )
        return @"required";
    else if( name==kIssueTypeValue )
        return @"value";
    else if( name==kIssueTypeInvariant )
        return @"invariant";
    else if( name==kIssueTypeSecurity )
        return @"security";
    else if( name==kIssueTypeLogin )
        return @"login";
    else if( name==kIssueTypeUnknown )
        return @"unknown";
    else if( name==kIssueTypeExpired )
        return @"expired";
    else if( name==kIssueTypeForbidden )
        return @"forbidden";
    else if( name==kIssueTypeProcessing )
        return @"processing";
    else if( name==kIssueTypeNotSupported )
        return @"not-supported";
    else if( name==kIssueTypeDuplicate )
        return @"duplicate";
    else if( name==kIssueTypeNotFound )
        return @"not-found";
    else if( name==kIssueTypeTooLong )
        return @"too-long";
    else if( name==kIssueTypeCodeUnknown )
        return @"code-unknown";
    else if( name==kIssueTypeExtension )
        return @"extension";
    else if( name==kIssueTypeTooCostly )
        return @"too-costly";
    else if( name==kIssueTypeBusinessRule )
        return @"business-rule";
    else if( name==kIssueTypeConflict )
        return @"conflict";
    else if( name==kIssueTypeTransient )
        return @"transient";
    else if( name==kIssueTypeLockError )
        return @"lock-error";
    else if( name==kIssueTypeNoStore )
        return @"no-store";
    else if( name==kIssueTypeException )
        return @"exception";
    else if( name==kIssueTypeTimeout )
        return @"timeout";
    else if( name==kIssueTypeThrottled )
        return @"throttled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIssueType" userInfo:nil];
}
/*
 * Conversion of kIssueSeverityfrom string
 */
+ (kIssueSeverity )parseIssueSeverityString:(NSString *)value
{
    kIssueSeverity result;
    
    if( [value isEqualToString:@"fatal"])
        result = kIssueSeverityFatal;
    else if( [value isEqualToString:@"error"])
        result = kIssueSeverityError;
    else if( [value isEqualToString:@"warning"])
        result = kIssueSeverityWarning;
    else if( [value isEqualToString:@"information"])
        result = kIssueSeverityInformation;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIssueSeverity" userInfo:nil];
    
    return result;
}

/*
 * Conversion of IssueSeverityto string
 */
+ (NSString *)stringIssueSeverity:(kIssueSeverity )name
{
    if( name==kIssueSeverityFatal )
        return @"fatal";
    else if( name==kIssueSeverityError )
        return @"error";
    else if( name==kIssueSeverityWarning )
        return @"warning";
    else if( name==kIssueSeverityInformation )
        return @"information";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kIssueSeverity" userInfo:nil];
}
/*
 * Conversion of kOrderOutcomeStatusfrom string
 */
+ (kOrderOutcomeStatus )parseOrderOutcomeStatusString:(NSString *)value
{
    kOrderOutcomeStatus result;
    
    if( [value isEqualToString:@"pending"])
        result = kOrderOutcomeStatusPending;
    else if( [value isEqualToString:@"review"])
        result = kOrderOutcomeStatusReview;
    else if( [value isEqualToString:@"rejected"])
        result = kOrderOutcomeStatusRejected;
    else if( [value isEqualToString:@"error"])
        result = kOrderOutcomeStatusError;
    else if( [value isEqualToString:@"accepted"])
        result = kOrderOutcomeStatusAccepted;
    else if( [value isEqualToString:@"cancelled"])
        result = kOrderOutcomeStatusCancelled;
    else if( [value isEqualToString:@"replaced"])
        result = kOrderOutcomeStatusReplaced;
    else if( [value isEqualToString:@"aborted"])
        result = kOrderOutcomeStatusAborted;
    else if( [value isEqualToString:@"complete"])
        result = kOrderOutcomeStatusComplete;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kOrderOutcomeStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of OrderOutcomeStatusto string
 */
+ (NSString *)stringOrderOutcomeStatus:(kOrderOutcomeStatus )name
{
    if( name==kOrderOutcomeStatusPending )
        return @"pending";
    else if( name==kOrderOutcomeStatusReview )
        return @"review";
    else if( name==kOrderOutcomeStatusRejected )
        return @"rejected";
    else if( name==kOrderOutcomeStatusError )
        return @"error";
    else if( name==kOrderOutcomeStatusAccepted )
        return @"accepted";
    else if( name==kOrderOutcomeStatusCancelled )
        return @"cancelled";
    else if( name==kOrderOutcomeStatusReplaced )
        return @"replaced";
    else if( name==kOrderOutcomeStatusAborted )
        return @"aborted";
    else if( name==kOrderOutcomeStatusComplete )
        return @"complete";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kOrderOutcomeStatus" userInfo:nil];
}
/*
 * Conversion of kLinkTypefrom string
 */
+ (kLinkType )parseLinkTypeString:(NSString *)value
{
    kLinkType result;
    
    if( [value isEqualToString:@"replace"])
        result = kLinkTypeReplace;
    else if( [value isEqualToString:@"refer"])
        result = kLinkTypeRefer;
    else if( [value isEqualToString:@"seealso"])
        result = kLinkTypeSeealso;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLinkType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of LinkTypeto string
 */
+ (NSString *)stringLinkType:(kLinkType )name
{
    if( name==kLinkTypeReplace )
        return @"replace";
    else if( name==kLinkTypeRefer )
        return @"refer";
    else if( name==kLinkTypeSeealso )
        return @"seealso";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kLinkType" userInfo:nil];
}
/*
 * Conversion of kProcedureRelationshipTypefrom string
 */
+ (kProcedureRelationshipType )parseProcedureRelationshipTypeString:(NSString *)value
{
    kProcedureRelationshipType result;
    
    if( [value isEqualToString:@"caused-by"])
        result = kProcedureRelationshipTypeCausedBy;
    else if( [value isEqualToString:@"because-of"])
        result = kProcedureRelationshipTypeBecauseOf;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kProcedureRelationshipType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ProcedureRelationshipTypeto string
 */
+ (NSString *)stringProcedureRelationshipType:(kProcedureRelationshipType )name
{
    if( name==kProcedureRelationshipTypeCausedBy )
        return @"caused-by";
    else if( name==kProcedureRelationshipTypeBecauseOf )
        return @"because-of";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kProcedureRelationshipType" userInfo:nil];
}
/*
 * Conversion of kBindingConformancefrom string
 */
+ (kBindingConformance )parseBindingConformanceString:(NSString *)value
{
    kBindingConformance result;
    
    if( [value isEqualToString:@"required"])
        result = kBindingConformanceRequired;
    else if( [value isEqualToString:@"preferred"])
        result = kBindingConformancePreferred;
    else if( [value isEqualToString:@"example"])
        result = kBindingConformanceExample;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kBindingConformance" userInfo:nil];
    
    return result;
}

/*
 * Conversion of BindingConformanceto string
 */
+ (NSString *)stringBindingConformance:(kBindingConformance )name
{
    if( name==kBindingConformanceRequired )
        return @"required";
    else if( name==kBindingConformancePreferred )
        return @"preferred";
    else if( name==kBindingConformanceExample )
        return @"example";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kBindingConformance" userInfo:nil];
}
/*
 * Conversion of kConstraintSeverityfrom string
 */
+ (kConstraintSeverity )parseConstraintSeverityString:(NSString *)value
{
    kConstraintSeverity result;
    
    if( [value isEqualToString:@"error"])
        result = kConstraintSeverityError;
    else if( [value isEqualToString:@"warning"])
        result = kConstraintSeverityWarning;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConstraintSeverity" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ConstraintSeverityto string
 */
+ (NSString *)stringConstraintSeverity:(kConstraintSeverity )name
{
    if( name==kConstraintSeverityError )
        return @"error";
    else if( name==kConstraintSeverityWarning )
        return @"warning";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kConstraintSeverity" userInfo:nil];
}
/*
 * Conversion of kResourceProfileStatusfrom string
 */
+ (kResourceProfileStatus )parseResourceProfileStatusString:(NSString *)value
{
    kResourceProfileStatus result;
    
    if( [value isEqualToString:@"draft"])
        result = kResourceProfileStatusDraft;
    else if( [value isEqualToString:@"active"])
        result = kResourceProfileStatusActive;
    else if( [value isEqualToString:@"retired"])
        result = kResourceProfileStatusRetired;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResourceProfileStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ResourceProfileStatusto string
 */
+ (NSString *)stringResourceProfileStatus:(kResourceProfileStatus )name
{
    if( name==kResourceProfileStatusDraft )
        return @"draft";
    else if( name==kResourceProfileStatusActive )
        return @"active";
    else if( name==kResourceProfileStatusRetired )
        return @"retired";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kResourceProfileStatus" userInfo:nil];
}
/*
 * Conversion of kPropertyRepresentationfrom string
 */
+ (kPropertyRepresentation )parsePropertyRepresentationString:(NSString *)value
{
    kPropertyRepresentation result;
    
    if( [value isEqualToString:@"xmlAttr"])
        result = kPropertyRepresentationXmlAttr;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kPropertyRepresentation" userInfo:nil];
    
    return result;
}

/*
 * Conversion of PropertyRepresentationto string
 */
+ (NSString *)stringPropertyRepresentation:(kPropertyRepresentation )name
{
    if( name==kPropertyRepresentationXmlAttr )
        return @"xmlAttr";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kPropertyRepresentation" userInfo:nil];
}
/*
 * Conversion of kAggregationModefrom string
 */
+ (kAggregationMode )parseAggregationModeString:(NSString *)value
{
    kAggregationMode result;
    
    if( [value isEqualToString:@"contained"])
        result = kAggregationModeContained;
    else if( [value isEqualToString:@"referenced"])
        result = kAggregationModeReferenced;
    else if( [value isEqualToString:@"bundled"])
        result = kAggregationModeBundled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAggregationMode" userInfo:nil];
    
    return result;
}

/*
 * Conversion of AggregationModeto string
 */
+ (NSString *)stringAggregationMode:(kAggregationMode )name
{
    if( name==kAggregationModeContained )
        return @"contained";
    else if( name==kAggregationModeReferenced )
        return @"referenced";
    else if( name==kAggregationModeBundled )
        return @"bundled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kAggregationMode" userInfo:nil];
}
/*
 * Conversion of kExtensionContextfrom string
 */
+ (kExtensionContext )parseExtensionContextString:(NSString *)value
{
    kExtensionContext result;
    
    if( [value isEqualToString:@"resource"])
        result = kExtensionContextResource;
    else if( [value isEqualToString:@"datatype"])
        result = kExtensionContextDatatype;
    else if( [value isEqualToString:@"mapping"])
        result = kExtensionContextMapping;
    else if( [value isEqualToString:@"extension"])
        result = kExtensionContextExtension;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kExtensionContext" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ExtensionContextto string
 */
+ (NSString *)stringExtensionContext:(kExtensionContext )name
{
    if( name==kExtensionContextResource )
        return @"resource";
    else if( name==kExtensionContextDatatype )
        return @"datatype";
    else if( name==kExtensionContextMapping )
        return @"mapping";
    else if( name==kExtensionContextExtension )
        return @"extension";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kExtensionContext" userInfo:nil];
}
/*
 * Conversion of kSlicingRulesfrom string
 */
+ (kSlicingRules )parseSlicingRulesString:(NSString *)value
{
    kSlicingRules result;
    
    if( [value isEqualToString:@"closed"])
        result = kSlicingRulesClosed;
    else if( [value isEqualToString:@"open"])
        result = kSlicingRulesOpen;
    else if( [value isEqualToString:@"openAtEnd"])
        result = kSlicingRulesOpenAtEnd;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSlicingRules" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SlicingRulesto string
 */
+ (NSString *)stringSlicingRules:(kSlicingRules )name
{
    if( name==kSlicingRulesClosed )
        return @"closed";
    else if( name==kSlicingRulesOpen )
        return @"open";
    else if( name==kSlicingRulesOpenAtEnd )
        return @"openAtEnd";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSlicingRules" userInfo:nil];
}
/*
 * Conversion of kProvenanceEntityRolefrom string
 */
+ (kProvenanceEntityRole )parseProvenanceEntityRoleString:(NSString *)value
{
    kProvenanceEntityRole result;
    
    if( [value isEqualToString:@"derivation"])
        result = kProvenanceEntityRoleDerivation;
    else if( [value isEqualToString:@"revision"])
        result = kProvenanceEntityRoleRevision;
    else if( [value isEqualToString:@"quotation"])
        result = kProvenanceEntityRoleQuotation;
    else if( [value isEqualToString:@"source"])
        result = kProvenanceEntityRoleSource;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kProvenanceEntityRole" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ProvenanceEntityRoleto string
 */
+ (NSString *)stringProvenanceEntityRole:(kProvenanceEntityRole )name
{
    if( name==kProvenanceEntityRoleDerivation )
        return @"derivation";
    else if( name==kProvenanceEntityRoleRevision )
        return @"revision";
    else if( name==kProvenanceEntityRoleQuotation )
        return @"quotation";
    else if( name==kProvenanceEntityRoleSource )
        return @"source";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kProvenanceEntityRole" userInfo:nil];
}
/*
 * Conversion of kQueryOutcomefrom string
 */
+ (kQueryOutcome )parseQueryOutcomeString:(NSString *)value
{
    kQueryOutcome result;
    
    if( [value isEqualToString:@"ok"])
        result = kQueryOutcomeOk;
    else if( [value isEqualToString:@"limited"])
        result = kQueryOutcomeLimited;
    else if( [value isEqualToString:@"refused"])
        result = kQueryOutcomeRefused;
    else if( [value isEqualToString:@"error"])
        result = kQueryOutcomeError;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQueryOutcome" userInfo:nil];
    
    return result;
}

/*
 * Conversion of QueryOutcometo string
 */
+ (NSString *)stringQueryOutcome:(kQueryOutcome )name
{
    if( name==kQueryOutcomeOk )
        return @"ok";
    else if( name==kQueryOutcomeLimited )
        return @"limited";
    else if( name==kQueryOutcomeRefused )
        return @"refused";
    else if( name==kQueryOutcomeError )
        return @"error";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQueryOutcome" userInfo:nil];
}
/*
 * Conversion of kQuestionnaireStatusfrom string
 */
+ (kQuestionnaireStatus )parseQuestionnaireStatusString:(NSString *)value
{
    kQuestionnaireStatus result;
    
    if( [value isEqualToString:@"draft"])
        result = kQuestionnaireStatusDraft;
    else if( [value isEqualToString:@"published"])
        result = kQuestionnaireStatusPublished;
    else if( [value isEqualToString:@"retired"])
        result = kQuestionnaireStatusRetired;
    else if( [value isEqualToString:@"in progress"])
        result = kQuestionnaireStatusInProgress;
    else if( [value isEqualToString:@"completed"])
        result = kQuestionnaireStatusCompleted;
    else if( [value isEqualToString:@"amended"])
        result = kQuestionnaireStatusAmended;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQuestionnaireStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of QuestionnaireStatusto string
 */
+ (NSString *)stringQuestionnaireStatus:(kQuestionnaireStatus )name
{
    if( name==kQuestionnaireStatusDraft )
        return @"draft";
    else if( name==kQuestionnaireStatusPublished )
        return @"published";
    else if( name==kQuestionnaireStatusRetired )
        return @"retired";
    else if( name==kQuestionnaireStatusInProgress )
        return @"in progress";
    else if( name==kQuestionnaireStatusCompleted )
        return @"completed";
    else if( name==kQuestionnaireStatusAmended )
        return @"amended";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kQuestionnaireStatus" userInfo:nil];
}
/*
 * Conversion of kSecurityEventObjectRolefrom string
 */
+ (kSecurityEventObjectRole )parseSecurityEventObjectRoleString:(NSString *)value
{
    kSecurityEventObjectRole result;
    
    if( [value isEqualToString:@"1"])
        result = kSecurityEventObjectRoleN1;
    else if( [value isEqualToString:@"2"])
        result = kSecurityEventObjectRoleN2;
    else if( [value isEqualToString:@"3"])
        result = kSecurityEventObjectRoleN3;
    else if( [value isEqualToString:@"4"])
        result = kSecurityEventObjectRoleN4;
    else if( [value isEqualToString:@"5"])
        result = kSecurityEventObjectRoleN5;
    else if( [value isEqualToString:@"6"])
        result = kSecurityEventObjectRoleN6;
    else if( [value isEqualToString:@"7"])
        result = kSecurityEventObjectRoleN7;
    else if( [value isEqualToString:@"8"])
        result = kSecurityEventObjectRoleN8;
    else if( [value isEqualToString:@"9"])
        result = kSecurityEventObjectRoleN9;
    else if( [value isEqualToString:@"10"])
        result = kSecurityEventObjectRoleN10;
    else if( [value isEqualToString:@"11"])
        result = kSecurityEventObjectRoleN11;
    else if( [value isEqualToString:@"12"])
        result = kSecurityEventObjectRoleN12;
    else if( [value isEqualToString:@"13"])
        result = kSecurityEventObjectRoleN13;
    else if( [value isEqualToString:@"14"])
        result = kSecurityEventObjectRoleN14;
    else if( [value isEqualToString:@"15"])
        result = kSecurityEventObjectRoleN15;
    else if( [value isEqualToString:@"16"])
        result = kSecurityEventObjectRoleN16;
    else if( [value isEqualToString:@"17"])
        result = kSecurityEventObjectRoleN17;
    else if( [value isEqualToString:@"18"])
        result = kSecurityEventObjectRoleN18;
    else if( [value isEqualToString:@"19"])
        result = kSecurityEventObjectRoleN19;
    else if( [value isEqualToString:@"20"])
        result = kSecurityEventObjectRoleN20;
    else if( [value isEqualToString:@"21"])
        result = kSecurityEventObjectRoleN21;
    else if( [value isEqualToString:@"22"])
        result = kSecurityEventObjectRoleN22;
    else if( [value isEqualToString:@"23"])
        result = kSecurityEventObjectRoleN23;
    else if( [value isEqualToString:@"24"])
        result = kSecurityEventObjectRoleN24;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectRole" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventObjectRoleto string
 */
+ (NSString *)stringSecurityEventObjectRole:(kSecurityEventObjectRole )name
{
    if( name==kSecurityEventObjectRoleN1 )
        return @"1";
    else if( name==kSecurityEventObjectRoleN2 )
        return @"2";
    else if( name==kSecurityEventObjectRoleN3 )
        return @"3";
    else if( name==kSecurityEventObjectRoleN4 )
        return @"4";
    else if( name==kSecurityEventObjectRoleN5 )
        return @"5";
    else if( name==kSecurityEventObjectRoleN6 )
        return @"6";
    else if( name==kSecurityEventObjectRoleN7 )
        return @"7";
    else if( name==kSecurityEventObjectRoleN8 )
        return @"8";
    else if( name==kSecurityEventObjectRoleN9 )
        return @"9";
    else if( name==kSecurityEventObjectRoleN10 )
        return @"10";
    else if( name==kSecurityEventObjectRoleN11 )
        return @"11";
    else if( name==kSecurityEventObjectRoleN12 )
        return @"12";
    else if( name==kSecurityEventObjectRoleN13 )
        return @"13";
    else if( name==kSecurityEventObjectRoleN14 )
        return @"14";
    else if( name==kSecurityEventObjectRoleN15 )
        return @"15";
    else if( name==kSecurityEventObjectRoleN16 )
        return @"16";
    else if( name==kSecurityEventObjectRoleN17 )
        return @"17";
    else if( name==kSecurityEventObjectRoleN18 )
        return @"18";
    else if( name==kSecurityEventObjectRoleN19 )
        return @"19";
    else if( name==kSecurityEventObjectRoleN20 )
        return @"20";
    else if( name==kSecurityEventObjectRoleN21 )
        return @"21";
    else if( name==kSecurityEventObjectRoleN22 )
        return @"22";
    else if( name==kSecurityEventObjectRoleN23 )
        return @"23";
    else if( name==kSecurityEventObjectRoleN24 )
        return @"24";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectRole" userInfo:nil];
}
/*
 * Conversion of kSecurityEventActionfrom string
 */
+ (kSecurityEventAction )parseSecurityEventActionString:(NSString *)value
{
    kSecurityEventAction result;
    
    if( [value isEqualToString:@"C"])
        result = kSecurityEventActionC;
    else if( [value isEqualToString:@"R"])
        result = kSecurityEventActionR;
    else if( [value isEqualToString:@"U"])
        result = kSecurityEventActionU;
    else if( [value isEqualToString:@"D"])
        result = kSecurityEventActionD;
    else if( [value isEqualToString:@"E"])
        result = kSecurityEventActionE;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventAction" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventActionto string
 */
+ (NSString *)stringSecurityEventAction:(kSecurityEventAction )name
{
    if( name==kSecurityEventActionC )
        return @"C";
    else if( name==kSecurityEventActionR )
        return @"R";
    else if( name==kSecurityEventActionU )
        return @"U";
    else if( name==kSecurityEventActionD )
        return @"D";
    else if( name==kSecurityEventActionE )
        return @"E";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventAction" userInfo:nil];
}
/*
 * Conversion of kSecurityEventObjectTypefrom string
 */
+ (kSecurityEventObjectType )parseSecurityEventObjectTypeString:(NSString *)value
{
    kSecurityEventObjectType result;
    
    if( [value isEqualToString:@"1"])
        result = kSecurityEventObjectTypeN1;
    else if( [value isEqualToString:@"2"])
        result = kSecurityEventObjectTypeN2;
    else if( [value isEqualToString:@"3"])
        result = kSecurityEventObjectTypeN3;
    else if( [value isEqualToString:@"4"])
        result = kSecurityEventObjectTypeN4;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventObjectTypeto string
 */
+ (NSString *)stringSecurityEventObjectType:(kSecurityEventObjectType )name
{
    if( name==kSecurityEventObjectTypeN1 )
        return @"1";
    else if( name==kSecurityEventObjectTypeN2 )
        return @"2";
    else if( name==kSecurityEventObjectTypeN3 )
        return @"3";
    else if( name==kSecurityEventObjectTypeN4 )
        return @"4";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectType" userInfo:nil];
}
/*
 * Conversion of kSecurityEventObjectLifecyclefrom string
 */
+ (kSecurityEventObjectLifecycle )parseSecurityEventObjectLifecycleString:(NSString *)value
{
    kSecurityEventObjectLifecycle result;
    
    if( [value isEqualToString:@"1"])
        result = kSecurityEventObjectLifecycleN1;
    else if( [value isEqualToString:@"2"])
        result = kSecurityEventObjectLifecycleN2;
    else if( [value isEqualToString:@"3"])
        result = kSecurityEventObjectLifecycleN3;
    else if( [value isEqualToString:@"4"])
        result = kSecurityEventObjectLifecycleN4;
    else if( [value isEqualToString:@"5"])
        result = kSecurityEventObjectLifecycleN5;
    else if( [value isEqualToString:@"6"])
        result = kSecurityEventObjectLifecycleN6;
    else if( [value isEqualToString:@"7"])
        result = kSecurityEventObjectLifecycleN7;
    else if( [value isEqualToString:@"8"])
        result = kSecurityEventObjectLifecycleN8;
    else if( [value isEqualToString:@"9"])
        result = kSecurityEventObjectLifecycleN9;
    else if( [value isEqualToString:@"10"])
        result = kSecurityEventObjectLifecycleN10;
    else if( [value isEqualToString:@"11"])
        result = kSecurityEventObjectLifecycleN11;
    else if( [value isEqualToString:@"12"])
        result = kSecurityEventObjectLifecycleN12;
    else if( [value isEqualToString:@"13"])
        result = kSecurityEventObjectLifecycleN13;
    else if( [value isEqualToString:@"14"])
        result = kSecurityEventObjectLifecycleN14;
    else if( [value isEqualToString:@"15"])
        result = kSecurityEventObjectLifecycleN15;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectLifecycle" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventObjectLifecycleto string
 */
+ (NSString *)stringSecurityEventObjectLifecycle:(kSecurityEventObjectLifecycle )name
{
    if( name==kSecurityEventObjectLifecycleN1 )
        return @"1";
    else if( name==kSecurityEventObjectLifecycleN2 )
        return @"2";
    else if( name==kSecurityEventObjectLifecycleN3 )
        return @"3";
    else if( name==kSecurityEventObjectLifecycleN4 )
        return @"4";
    else if( name==kSecurityEventObjectLifecycleN5 )
        return @"5";
    else if( name==kSecurityEventObjectLifecycleN6 )
        return @"6";
    else if( name==kSecurityEventObjectLifecycleN7 )
        return @"7";
    else if( name==kSecurityEventObjectLifecycleN8 )
        return @"8";
    else if( name==kSecurityEventObjectLifecycleN9 )
        return @"9";
    else if( name==kSecurityEventObjectLifecycleN10 )
        return @"10";
    else if( name==kSecurityEventObjectLifecycleN11 )
        return @"11";
    else if( name==kSecurityEventObjectLifecycleN12 )
        return @"12";
    else if( name==kSecurityEventObjectLifecycleN13 )
        return @"13";
    else if( name==kSecurityEventObjectLifecycleN14 )
        return @"14";
    else if( name==kSecurityEventObjectLifecycleN15 )
        return @"15";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventObjectLifecycle" userInfo:nil];
}
/*
 * Conversion of kSecurityEventParticipantNetworkTypefrom string
 */
+ (kSecurityEventParticipantNetworkType )parseSecurityEventParticipantNetworkTypeString:(NSString *)value
{
    kSecurityEventParticipantNetworkType result;
    
    if( [value isEqualToString:@"1"])
        result = kSecurityEventParticipantNetworkTypeN1;
    else if( [value isEqualToString:@"2"])
        result = kSecurityEventParticipantNetworkTypeN2;
    else if( [value isEqualToString:@"3"])
        result = kSecurityEventParticipantNetworkTypeN3;
    else if( [value isEqualToString:@"4"])
        result = kSecurityEventParticipantNetworkTypeN4;
    else if( [value isEqualToString:@"5"])
        result = kSecurityEventParticipantNetworkTypeN5;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventParticipantNetworkType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventParticipantNetworkTypeto string
 */
+ (NSString *)stringSecurityEventParticipantNetworkType:(kSecurityEventParticipantNetworkType )name
{
    if( name==kSecurityEventParticipantNetworkTypeN1 )
        return @"1";
    else if( name==kSecurityEventParticipantNetworkTypeN2 )
        return @"2";
    else if( name==kSecurityEventParticipantNetworkTypeN3 )
        return @"3";
    else if( name==kSecurityEventParticipantNetworkTypeN4 )
        return @"4";
    else if( name==kSecurityEventParticipantNetworkTypeN5 )
        return @"5";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventParticipantNetworkType" userInfo:nil];
}
/*
 * Conversion of kSecurityEventOutcomefrom string
 */
+ (kSecurityEventOutcome )parseSecurityEventOutcomeString:(NSString *)value
{
    kSecurityEventOutcome result;
    
    if( [value isEqualToString:@"0"])
        result = kSecurityEventOutcomeN0;
    else if( [value isEqualToString:@"4"])
        result = kSecurityEventOutcomeN4;
    else if( [value isEqualToString:@"8"])
        result = kSecurityEventOutcomeN8;
    else if( [value isEqualToString:@"12"])
        result = kSecurityEventOutcomeN12;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventOutcome" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SecurityEventOutcometo string
 */
+ (NSString *)stringSecurityEventOutcome:(kSecurityEventOutcome )name
{
    if( name==kSecurityEventOutcomeN0 )
        return @"0";
    else if( name==kSecurityEventOutcomeN4 )
        return @"4";
    else if( name==kSecurityEventOutcomeN8 )
        return @"8";
    else if( name==kSecurityEventOutcomeN12 )
        return @"12";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSecurityEventOutcome" userInfo:nil];
}
/*
 * Conversion of kHierarchicalRelationshipTypefrom string
 */
+ (kHierarchicalRelationshipType )parseHierarchicalRelationshipTypeString:(NSString *)value
{
    kHierarchicalRelationshipType result;
    
    if( [value isEqualToString:@"parent"])
        result = kHierarchicalRelationshipTypeParent;
    else if( [value isEqualToString:@"child"])
        result = kHierarchicalRelationshipTypeChild;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kHierarchicalRelationshipType" userInfo:nil];
    
    return result;
}

/*
 * Conversion of HierarchicalRelationshipTypeto string
 */
+ (NSString *)stringHierarchicalRelationshipType:(kHierarchicalRelationshipType )name
{
    if( name==kHierarchicalRelationshipTypeParent )
        return @"parent";
    else if( name==kHierarchicalRelationshipTypeChild )
        return @"child";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kHierarchicalRelationshipType" userInfo:nil];
}
/*
 * Conversion of kSupplyDispenseStatusfrom string
 */
+ (kSupplyDispenseStatus )parseSupplyDispenseStatusString:(NSString *)value
{
    kSupplyDispenseStatus result;
    
    if( [value isEqualToString:@"in progress"])
        result = kSupplyDispenseStatusInProgress;
    else if( [value isEqualToString:@"dispensed"])
        result = kSupplyDispenseStatusDispensed;
    else if( [value isEqualToString:@"abandoned"])
        result = kSupplyDispenseStatusAbandoned;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSupplyDispenseStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SupplyDispenseStatusto string
 */
+ (NSString *)stringSupplyDispenseStatus:(kSupplyDispenseStatus )name
{
    if( name==kSupplyDispenseStatusInProgress )
        return @"in progress";
    else if( name==kSupplyDispenseStatusDispensed )
        return @"dispensed";
    else if( name==kSupplyDispenseStatusAbandoned )
        return @"abandoned";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSupplyDispenseStatus" userInfo:nil];
}
/*
 * Conversion of kSupplyStatusfrom string
 */
+ (kSupplyStatus )parseSupplyStatusString:(NSString *)value
{
    kSupplyStatus result;
    
    if( [value isEqualToString:@"requested"])
        result = kSupplyStatusRequested;
    else if( [value isEqualToString:@"dispensed"])
        result = kSupplyStatusDispensed;
    else if( [value isEqualToString:@"received"])
        result = kSupplyStatusReceived;
    else if( [value isEqualToString:@"failed"])
        result = kSupplyStatusFailed;
    else if( [value isEqualToString:@"cancelled"])
        result = kSupplyStatusCancelled;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSupplyStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of SupplyStatusto string
 */
+ (NSString *)stringSupplyStatus:(kSupplyStatus )name
{
    if( name==kSupplyStatusRequested )
        return @"requested";
    else if( name==kSupplyStatusDispensed )
        return @"dispensed";
    else if( name==kSupplyStatusReceived )
        return @"received";
    else if( name==kSupplyStatusFailed )
        return @"failed";
    else if( name==kSupplyStatusCancelled )
        return @"cancelled";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kSupplyStatus" userInfo:nil];
}
/*
 * Conversion of kValueSetStatusfrom string
 */
+ (kValueSetStatus )parseValueSetStatusString:(NSString *)value
{
    kValueSetStatus result;
    
    if( [value isEqualToString:@"draft"])
        result = kValueSetStatusDraft;
    else if( [value isEqualToString:@"active"])
        result = kValueSetStatusActive;
    else if( [value isEqualToString:@"retired"])
        result = kValueSetStatusRetired;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kValueSetStatus" userInfo:nil];
    
    return result;
}

/*
 * Conversion of ValueSetStatusto string
 */
+ (NSString *)stringValueSetStatus:(kValueSetStatus )name
{
    if( name==kValueSetStatusDraft )
        return @"draft";
    else if( name==kValueSetStatusActive )
        return @"active";
    else if( name==kValueSetStatusRetired )
        return @"retired";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kValueSetStatus" userInfo:nil];
}
/*
 * Conversion of kFilterOperatorfrom string
 */
+ (kFilterOperator )parseFilterOperatorString:(NSString *)value
{
    kFilterOperator result;
    
    if( [value isEqualToString:@"="])
        result = kFilterOperatorEqual;
    else if( [value isEqualToString:@"is-a"])
        result = kFilterOperatorIsA;
    else if( [value isEqualToString:@"is-not-a"])
        result = kFilterOperatorIsNotA;
    else if( [value isEqualToString:@"regex"])
        result = kFilterOperatorRegex;
    else if( [value isEqualToString:@"in"])
        result = kFilterOperatorIn;
    else if( [value isEqualToString:@"not in"])
        result = kFilterOperatorNotIn;
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kFilterOperator" userInfo:nil];
    
    return result;
}

/*
 * Conversion of FilterOperatorto string
 */
+ (NSString *)stringFilterOperator:(kFilterOperator )name
{
    if( name==kFilterOperatorEqual )
        return @"=";
    else if( name==kFilterOperatorIsA )
        return @"is-a";
    else if( name==kFilterOperatorIsNotA )
        return @"is-not-a";
    else if( name==kFilterOperatorRegex )
        return @"regex";
    else if( name==kFilterOperatorIn )
        return @"in";
    else if( name==kFilterOperatorNotIn )
        return @"not in";
    else
        @throw [NSException exceptionWithName:@"Unrecognized" reason:@"Unrecognized kFilterOperator" userInfo:nil];
}
@end
