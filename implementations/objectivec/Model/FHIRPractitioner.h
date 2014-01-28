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
 * A person with a  formal responsibility in the provisioning of healthcare or related services
 *
 * [FhirResource("Practitioner")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRHumanName;
@class FHIRContact;
@class FHIRAddress;
@class FHIRCodeableConcept;
@class FHIRDateTime;
@class FHIRAttachment;
@class FHIRResourceReference;
@class FHIRPeriod;
@class FHIRPractitionerQualificationComponent;

@interface FHIRPractitioner : FHIRResource

/*
 * A identifier for the person as this agent
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * A name associated with the person
 */
@property (nonatomic, strong) FHIRHumanName *name;

/*
 * A contact detail for the practitioner
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * Where practitioner can be found/visited
 */
@property (nonatomic, strong) FHIRAddress *address;

/*
 * Gender for administrative purposes
 */
@property (nonatomic, strong) FHIRCodeableConcept *gender;

/*
 * The date and time of birth for the practitioner
 */
@property (nonatomic, strong) FHIRDateTime *birthDateElement;

@property (nonatomic, strong) NSString *birthDate;

/*
 * Image of the person
 */
@property (nonatomic, strong) NSArray/*<Attachment>*/ *photo;

/*
 * The represented organization
 */
@property (nonatomic, strong) FHIRResourceReference *organization;

/*
 * Roles which this practitioner may perform
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *role;

/*
 * Specific specialty of the practitioner
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *specialty;

/*
 * The period during which the practitioner is authorized to perform in these role(s)
 */
@property (nonatomic, strong) FHIRPeriod *period;

/*
 * The location(s) at which this practitioner provides care
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *location;

/*
 * Qualifications obtained by training and certification
 */
@property (nonatomic, strong) NSArray/*<PractitionerQualificationComponent>*/ *qualification;

/*
 * A language the practitioner is able to use in patient communication
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *communication;

- (FHIRErrorList *)validate;

@end
