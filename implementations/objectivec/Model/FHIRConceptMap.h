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
 * A statement of relationships from one set of concepts to one or more other concept systems
 *
 * [FhirResource("ConceptMap")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRString;
@class FHIRContact;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRDateTime;
@class FHIRResourceReference;
@class FHIRConceptMapConceptComponent;

@interface FHIRConceptMap : FHIRResource

/*
 * The degree of equivalence between concepts
 */
typedef enum 
{
    kConceptMapEquivalenceEqual, // The definitions of the concepts are exactly the same (i.e. only grammatical differences) and structural implications of meaning are identifical or irrelevant (i.e. intensionally identical).
    kConceptMapEquivalenceEquivalent, // The definitions of the concepts mean the same thing (including when structural implications of meaning are considered) (i.e. extensionally identical).
    kConceptMapEquivalenceWider, // The target mapping is wider in meaning than the source concept.
    kConceptMapEquivalenceSubsumes, // The target mapping subsumes the meaning of the source concept (e.g. the source is-a target).
    kConceptMapEquivalenceNarrower, // The target mapping is narrower in meaning that the source concept. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
    kConceptMapEquivalenceSpecialises, // The target mapping specialises the meaning of the source concept (e.g. the target is-a source).
    kConceptMapEquivalenceInexact, // The target mapping overlaps with the source concept, but both source and target cover additional meaning. The sense in which the mapping is narrower SHALL be described in the comments in this case, and applications should be careful when atempting to use these mappings operationally.
    kConceptMapEquivalenceUnmatched, // There is no match for this concept in the destination concept system.
    kConceptMapEquivalenceDisjoint, // This is an explicit assertion that there is no mapping between the source and target concept.
} kConceptMapEquivalence;

/*
 * Logical id to reference this concept map
 */
@property (nonatomic, strong) FHIRString *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Logical id for this version of the concept map
 */
@property (nonatomic, strong) FHIRString *versionElement;

@property (nonatomic, strong) NSString *version;

/*
 * Informal name for this concept map
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Name of the publisher (Organization or individual)
 */
@property (nonatomic, strong) FHIRString *publisherElement;

@property (nonatomic, strong) NSString *publisher;

/*
 * Contact information of the publisher
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * Human language description of the concept map
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * About the concept map or its content
 */
@property (nonatomic, strong) FHIRString *copyrightElement;

@property (nonatomic, strong) NSString *copyright;

/*
 * draft | active | retired
 */
@property (nonatomic, strong) FHIRCode *statusElement;

@property (nonatomic, strong) NSString *status;

/*
 * If for testing purposes, not real usage
 */
@property (nonatomic, strong) FHIRBoolean *experimentalElement;

@property (nonatomic, strong) NSNumber *experimental;

/*
 * Date for given status
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Identifies the source value set which is being mapped
 */
@property (nonatomic, strong) FHIRResourceReference *source;

/*
 * Provides context to the mappings
 */
@property (nonatomic, strong) FHIRResourceReference *target;

/*
 * Mappings for a concept from the source valueset
 */
@property (nonatomic, strong) NSArray/*<ConceptMapConceptComponent>*/ *concept;

- (FHIRErrorList *)validate;

@end
