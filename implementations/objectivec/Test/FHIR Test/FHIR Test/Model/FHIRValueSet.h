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
 * A set of codes drawn from one or more code systems
 *
 * [FhirResource("ValueSet")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRString;
@class FHIRContact;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRDateTime;
@class FHIRValueSetDefineComponent;
@class FHIRValueSetComposeComponent;
@class FHIRValueSetExpansionComponent;

@interface FHIRValueSet : FHIRResource

/*
 * The way in which the code is selected
 */
typedef enum 
{
    kCodeSelectionModeCode, // Only this code is selected.
    kCodeSelectionModeChildren, // Only the immediate children (codes with a is-a relationship) are selected, but not this code itself.
    kCodeSelectionModeDescendants, // All descendants of this code are selected, but not this code itself.
    kCodeSelectionModeAll, // This code and any descendants are selected.
    kCodeSelectionModeSystem, // All codes from the specified code system.
} kCodeSelectionMode;

/*
 * The lifecycle status of a Value Set
 */
typedef enum 
{
    kValueSetStatusDraft, // This valueset is still under development.
    kValueSetStatusActive, // This valueset is ready for normal use.
    kValueSetStatusRetired, // This valueset has been withdrawn or superceded and should no longer be used.
} kValueSetStatus;

/*
 * The kind of operation to perform as part of a property based filter
 */
typedef enum 
{
    kFilterOperatorEqual, // The property value has the concept specified by the value.
    kFilterOperatorIsA, // The property value has a concept that has an is-a relationship with the value.
    kFilterOperatorIsNotA, // The property value has a concept that does not have an is-a relationship with the value.
    kFilterOperatorRegex, // The property value representation matches the regex specified in the value.
    kFilterOperatorIn, // The property value is in the set of codes or concepts identified by the value.
    kFilterOperatorNotIn, // The property value is not in the set of codes or concepts identified by the value.
} kFilterOperator;

/*
 * Logical id to reference this value set
 */
@property (nonatomic, strong) FHIRString *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Logical id for this version of the value set
 */
@property (nonatomic, strong) FHIRString *versionElement;

@property (nonatomic, strong) NSString *version;

/*
 * Informal name for this value set
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
 * Human language description of the value set
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * About the value set or its content
 */
@property (nonatomic, strong) FHIRString *copyrightElement;

@property (nonatomic, strong) NSString *copyright;

/*
 * draft | active | retired
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kValueSetStatus status;

/*
 * If for testing purposes, not real usage
 */
@property (nonatomic, strong) FHIRBoolean *experimentalElement;

@property (nonatomic, strong) NSNumber *experimental;

/*
 * Whether this is intended to be used with an extensible binding
 */
@property (nonatomic, strong) FHIRBoolean *extensibleElement;

@property (nonatomic, strong) NSNumber *extensible;

/*
 * Date for given status
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * When value set defines its own codes
 */
@property (nonatomic, strong) FHIRValueSetDefineComponent *define;

/*
 * When value set includes codes from elsewhere
 */
@property (nonatomic, strong) FHIRValueSetComposeComponent *compose;

/*
 * When value set is an expansion
 */
@property (nonatomic, strong) FHIRValueSetExpansionComponent *expansion;

- (FHIRErrorList *)validate;

@end
