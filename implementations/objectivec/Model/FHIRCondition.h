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
/*
 * Detailed information about conditions, problems or diagnoses
 *
 * [FhirResource("Condition")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRDate;
@class FHIRCodeableConcept;
@class FHIRCode;
@class FHIRElement;
@class FHIRConditionStageComponent;
@class FHIRConditionEvidenceComponent;
@class FHIRConditionLocationComponent;
@class FHIRConditionRelatedItemComponent;
@class FHIRString;

@interface FHIRCondition : FHIRResource

/*
 * The clinical status of the Condition or diagnosis
 */
typedef enum 
{
    kConditionStatusProvisional, // This is a tentative diagnosis - still a candidate that is under consideration.
    kConditionStatusWorking, // The patient is being treated on the basis that this is the condition, but it is still not confirmed.
    kConditionStatusConfirmed, // There is sufficient diagnostic and/or clinical evidence to treat this as a confirmed condition.
    kConditionStatusRefuted, // This condition has been ruled out by diagnostic and clinical evidence.
} kConditionStatus;

/*
 * The type of relationship between a condition and its related item
 */
typedef enum 
{
    kConditionRelationshipTypeDueTo, // this condition follows the identified condition/procedure/substance and is a consequence of it.
    kConditionRelationshipTypeFollowing, // this condition follows the identified condition/procedure/substance, but it is not known whether they are causually linked.
} kConditionRelationshipType;

/*
 * External Ids for this condition
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Who has the condition?
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Encounter when condition first asserted
 */
@property (nonatomic, strong) FHIRResourceReference *encounter;

/*
 * Person who asserts this condition
 */
@property (nonatomic, strong) FHIRResourceReference *asserter;

/*
 * When first detected/suspected/entered
 */
@property (nonatomic, strong) FHIRDate *dateAssertedElement;

@property (nonatomic, strong) NSString *dateAsserted;

/*
 * Identification of the condition, problem or diagnosis
 */
@property (nonatomic, strong) FHIRCodeableConcept *code;

/*
 * E.g. complaint | symptom | finding | diagnosis
 */
@property (nonatomic, strong) FHIRCodeableConcept *category;

/*
 * provisional | working | confirmed | refuted
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kConditionStatus status;

/*
 * Degree of confidence
 */
@property (nonatomic, strong) FHIRCodeableConcept *certainty;

/*
 * Subjective severity of condition
 */
@property (nonatomic, strong) FHIRCodeableConcept *severity;

/*
 * Estimated or actual date, or age
 */
@property (nonatomic, strong) FHIRElement *onset;

/*
 * If/when in resolution/remission
 */
@property (nonatomic, strong) FHIRElement *abatement;

/*
 * Stage/grade, usually assessed formally
 */
@property (nonatomic, strong) FHIRConditionStageComponent *stage;

/*
 * Supporting evidence
 */
@property (nonatomic, strong) NSArray/*<ConditionEvidenceComponent>*/ *evidence;

/*
 * Anatomical location, if relevant
 */
@property (nonatomic, strong) NSArray/*<ConditionLocationComponent>*/ *location;

/*
 * Causes or precedents for this Condition
 */
@property (nonatomic, strong) NSArray/*<ConditionRelatedItemComponent>*/ *relatedItem;

/*
 * Additional information about the Condition
 */
@property (nonatomic, strong) FHIRString *notesElement;

@property (nonatomic, strong) NSString *notes;

- (FHIRErrorList *)validate;

@end
