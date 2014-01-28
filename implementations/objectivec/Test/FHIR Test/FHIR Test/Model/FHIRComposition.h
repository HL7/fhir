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
 * A set of resources composed into a single coherent clinical statement with clinical attestation
 *
 * [FhirResource("Composition")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRInstant;
@class FHIRCodeableConcept;
@class FHIRString;
@class FHIRCode;
@class FHIRCoding;
@class FHIRResourceReference;
@class FHIRCompositionAttesterComponent;
@class FHIRCompositionEventComponent;
@class FHIRSectionComponent;

@interface FHIRComposition : FHIRResource

/*
 * The workflow/clinical status of the composition
 */
typedef enum 
{
    kCompositionStatusPreliminary, // This is an preliminary composition or document (also known as initial or interim). The content may be incomplete or unverified.
    kCompositionStatusFinal, // The composition or document is complete and verified by an appropriate person, and no further work is planned.
    kCompositionStatusAppended, // The composition or document has been modified subsequent to being released as "final", and is complete and verified by an authorised person. The modifications added new information to the composition or document, but did not revise existing content.
    kCompositionStatusAmended, // The composition or document has been modified subsequent to being released as "final", and is complete and verified by an authorised person.
    kCompositionStatusEnteredInError, // The composition or document was originally created/issued in error, and this is an amendment that marks that the entire series should not be considered as valid.
} kCompositionStatus;

/*
 * The way in which a person authenticated a composition
 */
typedef enum 
{
    kCompositionAttestationModePersonal, // The person authenticated the content in their personal capacity.
    kCompositionAttestationModeProfessional, // The person authenticated the content in their professional capacity.
    kCompositionAttestationModeLegal, // The person authenticated the content and accepted legal responsibility for its content.
    kCompositionAttestationModeOfficial, // The organization authenticated the content as consistent with their policies and procedures.
} kCompositionAttestationMode;

/*
 * Logical identifier of composition (version-independent)
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * Composition editing time
 */
@property (nonatomic, strong) FHIRInstant *instantElement;

@property (nonatomic, strong) NSDate *instant;

/*
 * Kind of composition (LOINC if possible)
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * Categorisation of Composition
 */
@property (nonatomic, strong) FHIRCodeableConcept *class_;

/*
 * Human Readable name/title
 */
@property (nonatomic, strong) FHIRString *titleElement;

@property (nonatomic, strong) NSString *title;

/*
 * preliminary | final | appended | amended | entered in error
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kCompositionStatus status;

/*
 * As defined by affinity domain
 */
@property (nonatomic, strong) FHIRCoding *confidentiality;

/*
 * Who/what the composition is about
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Who/what authored the composition
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *author;

/*
 * Attests to accuracy of composition
 */
@property (nonatomic, strong) NSArray/*<CompositionAttesterComponent>*/ *attester;

/*
 * Org which maintains the composition
 */
@property (nonatomic, strong) FHIRResourceReference *custodian;

/*
 * The clinical event/act/item being documented
 */
@property (nonatomic, strong) FHIRCompositionEventComponent *event_;

/*
 * Context of the conposition
 */
@property (nonatomic, strong) FHIRResourceReference *encounter;

/*
 * Composition is broken into sections
 */
@property (nonatomic, strong) NSArray/*<SectionComponent>*/ *section;

- (FHIRErrorList *)validate;

@end
