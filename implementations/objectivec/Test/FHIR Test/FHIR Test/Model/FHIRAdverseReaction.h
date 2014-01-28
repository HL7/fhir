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
 * Specific reactions to a substance
 *
 * [FhirResource("AdverseReaction")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRDateTime;
@class FHIRResourceReference;
@class FHIRBoolean;
@class FHIRAdverseReactionSymptomComponent;
@class FHIRAdverseReactionExposureComponent;

@interface FHIRAdverseReaction : FHIRResource

/*
 * The severity of an adverse reaction.
 */
typedef enum 
{
    kReactionSeveritySevere, // Severe complications arose due to the reaction.
    kReactionSeveritySerious, // Serious inconvenience to the subject.
    kReactionSeverityModerate, // Moderate inconvenience to the subject.
    kReactionSeverityMinor, // Minor inconvenience to the subject.
} kReactionSeverity;

/*
 * The type of exposure that resulted in an adverse reaction
 */
typedef enum 
{
    kExposureTypeDrugadmin, // Drug Administration.
    kExposureTypeImmuniz, // Immunization.
    kExposureTypeCoincidental, // In the same area as the substance.
} kExposureType;

/*
 * How likely is it that the given exposure caused a reaction
 */
typedef enum 
{
    kCausalityExpectationLikely, // Likely that this specific exposure caused the reaction.
    kCausalityExpectationUnlikely, // Unlikely that this specific exposure caused the reaction - the exposure is being linked to for information purposes.
    kCausalityExpectationConfirmed, // It has been confirmed that this exposure was one of the causes of the reaction.
    kCausalityExpectationUnknown, // It is unknown whether this exposure had anything to do with the reaction.
} kCausalityExpectation;

/*
 * External Ids for this adverse reaction
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * When the reaction occurred
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Who had the reaction
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Indicates lack of reaction
 */
@property (nonatomic, strong) FHIRBoolean *didNotOccurFlagElement;

@property (nonatomic, strong) NSNumber *didNotOccurFlag;

/*
 * Who recorded the reaction
 */
@property (nonatomic, strong) FHIRResourceReference *recorder;

/*
 * What was reaction?
 */
@property (nonatomic, strong) NSArray/*<AdverseReactionSymptomComponent>*/ *symptom;

/*
 * Suspected substance
 */
@property (nonatomic, strong) NSArray/*<AdverseReactionExposureComponent>*/ *exposure;

- (FHIRErrorList *)validate;

@end
