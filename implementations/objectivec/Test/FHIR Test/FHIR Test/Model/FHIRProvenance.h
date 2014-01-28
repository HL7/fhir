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
 * Who, What, When for a set of resources
 *
 * [FhirResource("Provenance")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRResourceReference;
@class FHIRPeriod;
@class FHIRInstant;
@class FHIRCodeableConcept;
@class FHIRUri;
@class FHIRProvenanceAgentComponent;
@class FHIRProvenanceEntityComponent;
@class FHIRString;

@interface FHIRProvenance : FHIRResource

/*
 * How an entity was used in an activity
 */
typedef enum 
{
    kProvenanceEntityRoleDerivation, // A transformation of an entity into another, an update of an entity resulting in a new one, or the construction of a new entity based on a preexisting entity.
    kProvenanceEntityRoleRevision, // A derivation for which the resulting entity is a revised version of some original.
    kProvenanceEntityRoleQuotation, // The repeat of (some or all of) an entity, such as text or image, by someone who may or may not be its original author.
    kProvenanceEntityRoleSource, // A primary source for a topic refers to something produced by some agent with direct experience and knowledge about the topic, at the time of the topic's study, without benefit from hindsight.
} kProvenanceEntityRole;

/*
 * Target resource(s) (usually version specific)
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *target;

/*
 * When the activity occurred
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * When the activity was recorded / updated
 */
@property (nonatomic, strong) FHIRInstant *recordedElement;

@property (nonatomic, strong) NSDate *recorded;

/*
 * Reason the activity is occurring
 */
@property (nonatomic, strong) FHIRCodeableConcept *reason;

/*
 * Where the activity occurred, if relevant
 */
@property (nonatomic, strong) FHIRResourceReference *location;

/*
 * Policy or plan the activity was defined by
 */
@property (nonatomic, strong) NSArray/*<uri>*/ *policyElement;

@property (nonatomic, strong) NSArray /*<NSString>*/ *policy;

/*
 * Person, organization, records, etc. involved in creating resource
 */
@property (nonatomic, strong) NSArray/*<ProvenanceAgentComponent>*/ *agent;

/*
 * An entity used in this activity
 */
@property (nonatomic, strong) NSArray/*<ProvenanceEntityComponent>*/ *entity;

/*
 * Base64 signature (DigSig) - integrity check
 */
@property (nonatomic, strong) FHIRString *integritySignatureElement;

@property (nonatomic, strong) NSString *integritySignature;

- (FHIRErrorList *)validate;

@end
