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
 * Drug, food, environmental and others
 *
 * [FhirResource("AllergyIntolerance")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCode;
@class FHIRDateTime;
@class FHIRResourceReference;

@interface FHIRAllergyIntolerance : FHIRResource

/*
 * The status of the adverse sensitivity
 */
typedef enum 
{
    kSensitivityStatusSuspected, // A suspected sensitivity to a substance.
    kSensitivityStatusConfirmed, // The sensitivity has been confirmed and is active.
    kSensitivityStatusRefuted, // The sensitivity has been shown to never have existed.
    kSensitivityStatusResolved, // The sensitivity used to exist but no longer does.
} kSensitivityStatus;

/*
 * The criticality of an adverse sensitivity
 */
typedef enum 
{
    kCriticalityFatal, // Likely to result in death if re-exposed.
    kCriticalityHigh, // Likely to result in reactions that will need to be treated if re-exposed.
    kCriticalityMedium, // Likely to result in reactions that will inconvenience the subject.
    kCriticalityLow, // Not likely to result in any inconveniences for the subject.
} kCriticality;

/*
 * The type of an adverse sensitivity
 */
typedef enum 
{
    kSensitivityTypeAllergy, // Allergic Reaction.
    kSensitivityTypeIntolerance, // Non-Allergic Reaction.
    kSensitivityTypeUnknown, // Unknown type.
} kSensitivityType;

/*
 * External Ids for this item
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * fatal | high | medium | low
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *criticalityElement;

@property (nonatomic) kCriticality criticality;

/*
 * allergy | intolerance | unknown
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *sensitivityTypeElement;

@property (nonatomic) kSensitivityType sensitivityType;

/*
 * When recorded
 */
@property (nonatomic, strong) FHIRDateTime *recordedDateElement;

@property (nonatomic, strong) NSString *recordedDate;

/*
 * suspected | confirmed | refuted | resolved
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kSensitivityStatus status;

/*
 * Who the sensitivity is for
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Who recorded the sensitivity
 */
@property (nonatomic, strong) FHIRResourceReference *recorder;

/*
 * The substance that causes the sensitivity
 */
@property (nonatomic, strong) FHIRResourceReference *substance;

/*
 * Reactions associated with the sensitivity
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *reaction;

/*
 * Observations that confirm or refute
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *sensitivityTest;

- (FHIRErrorList *)validate;

@end
