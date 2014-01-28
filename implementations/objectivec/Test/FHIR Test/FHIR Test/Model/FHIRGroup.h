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
 * Group of multiple entities
 *
 * [FhirResource("Group")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCode;
@class FHIRBoolean;
@class FHIRCodeableConcept;
@class FHIRString;
@class FHIRInteger;
@class FHIRGroupCharacteristicComponent;
@class FHIRResourceReference;

@interface FHIRGroup : FHIRResource

/*
 * Types of resources that are part of group
 */
typedef enum 
{
    kGroupTypePerson, // Group contains "person" Patient resources.
    kGroupTypeAnimal, // Group contains "animal" Patient resources.
    kGroupTypePractitioner, // Group contains healthcare practitioner resources.
    kGroupTypeDevice, // Group contains Device resources.
    kGroupTypeMedication, // Group contains Medication resources.
    kGroupTypeSubstance, // Group contains Substance resources.
} kGroupType;

/*
 * Unique id
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * person | animal | practitioner | device | medication | substance
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *typeElement;

@property (nonatomic) kGroupType type;

/*
 * Descriptive or actual
 */
@property (nonatomic, strong) FHIRBoolean *actualElement;

@property (nonatomic, strong) NSNumber *actual;

/*
 * Kind of Group members
 */
@property (nonatomic, strong) FHIRCodeableConcept *code;

/*
 * Label for Group
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Number of members
 */
@property (nonatomic, strong) FHIRInteger *quantityElement;

@property (nonatomic, strong) NSNumber *quantity;

/*
 * Trait of group members
 */
@property (nonatomic, strong) NSArray/*<GroupCharacteristicComponent>*/ *characteristic;

/*
 * Who is in group
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *member;

- (FHIRErrorList *)validate;

@end
