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
 * Immunization event information
 *
 * [FhirResource("Immunization")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRDateTime;
@class FHIRCodeableConcept;
@class FHIRResourceReference;
@class FHIRBoolean;
@class FHIRString;
@class FHIRDate;
@class FHIRQuantity;
@class FHIRImmunizationExplanationComponent;
@class FHIRImmunizationReactionComponent;
@class FHIRImmunizationVaccinationProtocolComponent;

@interface FHIRImmunization : FHIRResource

/*
 * Business identifier
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Vaccination administration date
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Vaccine product administered
 */
@property (nonatomic, strong) FHIRCodeableConcept *vaccineType;

/*
 * Who was immunized?
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Was immunization refused?
 */
@property (nonatomic, strong) FHIRBoolean *refusedIndicatorElement;

@property (nonatomic, strong) NSNumber *refusedIndicator;

/*
 * Is this a self-reported record?
 */
@property (nonatomic, strong) FHIRBoolean *reportedElement;

@property (nonatomic, strong) NSNumber *reported;

/*
 * Who administered vaccine?
 */
@property (nonatomic, strong) FHIRResourceReference *performer;

/*
 * Who ordered vaccination?
 */
@property (nonatomic, strong) FHIRResourceReference *requester;

/*
 * Vaccine manufacturer
 */
@property (nonatomic, strong) FHIRResourceReference *manufacturer;

/*
 * Where did vaccination occur?
 */
@property (nonatomic, strong) FHIRResourceReference *location;

/*
 * Vaccine lot number
 */
@property (nonatomic, strong) FHIRString *lotNumberElement;

@property (nonatomic, strong) NSString *lotNumber;

/*
 * Vaccine expiration date
 */
@property (nonatomic, strong) FHIRDate *expirationDateElement;

@property (nonatomic, strong) NSString *expirationDate;

/*
 * Body site vaccine  was administered
 */
@property (nonatomic, strong) FHIRCodeableConcept *site;

/*
 * How vaccine entered body
 */
@property (nonatomic, strong) FHIRCodeableConcept *route;

/*
 * Amount of vaccine administered
 */
@property (nonatomic, strong) FHIRQuantity *doseQuantity;

/*
 * Administration / refusal reasons
 */
@property (nonatomic, strong) FHIRImmunizationExplanationComponent *explanation;

/*
 * Details of a reaction that follows immunization
 */
@property (nonatomic, strong) NSArray/*<ImmunizationReactionComponent>*/ *reaction;

/*
 * What protocol was followed
 */
@property (nonatomic, strong) NSArray/*<ImmunizationVaccinationProtocolComponent>*/ *vaccinationProtocol;

- (FHIRErrorList *)validate;

@end
