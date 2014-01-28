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
 * An instance of a manufactured thing that is used in the provision of healthcare
 *
 * [FhirResource("Device")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCodeableConcept;
@class FHIRString;
@class FHIRDate;
@class FHIRResourceReference;
@class FHIRContact;
@class FHIRUri;

@interface FHIRDevice : FHIRResource

/*
 * Instance id from manufacturer, owner and others
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * What kind of device this is
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * Name of device manufacturer
 */
@property (nonatomic, strong) FHIRString *manufacturerElement;

@property (nonatomic, strong) NSString *manufacturer;

/*
 * Model id assigned by the manufacturer
 */
@property (nonatomic, strong) FHIRString *modelElement;

@property (nonatomic, strong) NSString *model;

/*
 * Version number (i.e. software)
 */
@property (nonatomic, strong) FHIRString *versionElement;

@property (nonatomic, strong) NSString *version;

/*
 * Date of expiry of this device (if applicable)
 */
@property (nonatomic, strong) FHIRDate *expiryElement;

@property (nonatomic, strong) NSString *expiry;

/*
 * FDA Mandated Unique Device Identifier
 */
@property (nonatomic, strong) FHIRString *udiElement;

@property (nonatomic, strong) NSString *udi;

/*
 * Lot number of manufacture
 */
@property (nonatomic, strong) FHIRString *lotNumberElement;

@property (nonatomic, strong) NSString *lotNumber;

/*
 * Organization responsible for device
 */
@property (nonatomic, strong) FHIRResourceReference *owner;

/*
 * Where the resource is found
 */
@property (nonatomic, strong) FHIRResourceReference *location;

/*
 * If the resource is affixed to a person
 */
@property (nonatomic, strong) FHIRResourceReference *patient;

/*
 * Details for human/organization for support
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *contact;

/*
 * Network address to contact device
 */
@property (nonatomic, strong) FHIRUri *urlElement;

@property (nonatomic, strong) NSString *url;

- (FHIRErrorList *)validate;

@end
