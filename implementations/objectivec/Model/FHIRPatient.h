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
 * Information about a person or animal receiving health care services
 *
 * [FhirResource("Patient")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRHumanName;
@class FHIRContact;
@class FHIRCodeableConcept;
@class FHIRDateTime;
@class FHIRElement;
@class FHIRAddress;
@class FHIRAttachment;
@class FHIRContactComponent;
@class FHIRAnimalComponent;
@class FHIRResourceReference;
@class FHIRPatientLinkComponent;
@class FHIRBoolean;

@interface FHIRPatient : FHIRResource

/*
 * The type of link between this patient resource and another patient resource.
 */
typedef enum 
{
    kLinkTypeReplace, // The patient resource containing this link must no longer be used. The link points forward to another patient resource that must be used in lieu of the patient resource that contains the link.
    kLinkTypeRefer, // The patient resource containing this link is in use and valid but not considered the main source of information about a patient. The link points forward to another patient resource that should be consulted to retrieve additional patient information.
    kLinkTypeSeealso, // The patient resource containing this link is in use and valid, but points to another patient resource that is known to contain data about the same person. Data in this resource might overlap or contradict information found in the other patient resource. This link does not indicate any relative importance of the resources concerned, and both should be regarded as equally valid.
} kLinkType;

/*
 * An identifier for the person as this patient
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * A name associated with the patient
 */
@property (nonatomic, strong) NSArray/*<HumanName>*/ *name;

/*
 * A contact detail for the individual
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * Gender for administrative purposes
 */
@property (nonatomic, strong) FHIRCodeableConcept *gender;

/*
 * The date and time of birth for the individual
 */
@property (nonatomic, strong) FHIRDateTime *birthDateElement;

@property (nonatomic, strong) NSString *birthDate;

/*
 * Indicates if the individual is deceased or not
 */
@property (nonatomic, strong) FHIRElement *deceased;

/*
 * Addresses for the individual
 */
@property (nonatomic, strong) NSArray/*<Address>*/ *address;

/*
 * Marital (civil) status of a person
 */
@property (nonatomic, strong) FHIRCodeableConcept *maritalStatus;

/*
 * Whether patient is part of a multiple birth
 */
@property (nonatomic, strong) FHIRElement *multipleBirth;

/*
 * Image of the person
 */
@property (nonatomic, strong) NSArray/*<Attachment>*/ *photo;

/*
 * A contact party (e.g. guardian, partner, friend) for the patient
 */
@property (nonatomic, strong) NSArray/*<ContactComponent>*/ *contact;

/*
 * If this patient is an animal (non-human)
 */
@property (nonatomic, strong) FHIRAnimalComponent *animal;

/*
 * Languages which may be used to communicate with the patient about his or her health
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *communication;

/*
 * Patient's nominated care provider
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *careProvider;

/*
 * Organization that is the custodian of the patient record
 */
@property (nonatomic, strong) FHIRResourceReference *managingOrganization;

/*
 * Link to another patient resource that concerns the same actual person
 */
@property (nonatomic, strong) NSArray/*<PatientLinkComponent>*/ *link;

/*
 * Whether this patient's record is in active use
 */
@property (nonatomic, strong) FHIRBoolean *activeElement;

@property (nonatomic, strong) NSNumber *active;

- (FHIRErrorList *)validate;

@end
