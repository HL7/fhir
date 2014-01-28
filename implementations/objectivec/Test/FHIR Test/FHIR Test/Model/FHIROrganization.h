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
 * A grouping of people or organizations with a common purpose
 *
 * [FhirResource("Organization")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRString;
@class FHIRCodeableConcept;
@class FHIRContact;
@class FHIRAddress;
@class FHIRResourceReference;
@class FHIROrganizationContactComponent;
@class FHIRBoolean;

@interface FHIROrganization : FHIRResource

/*
 * Identifies this organization  across multiple systems
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Name used for the organization
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Kind of organization
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * A contact detail for the organization
 */
@property (nonatomic, strong) NSArray/*<Contact>*/ *telecom;

/*
 * An address for the organization
 */
@property (nonatomic, strong) NSArray/*<Address>*/ *address;

/*
 * The organization of which this organization forms a part
 */
@property (nonatomic, strong) FHIRResourceReference *partOf;

/*
 * Contact for the organization for a certain purpose
 */
@property (nonatomic, strong) NSArray/*<OrganizationContactComponent>*/ *contact;

/*
 * Location(s) the organization uses to provide services
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *location;

/*
 * Whether the organization's record is still in active use
 */
@property (nonatomic, strong) FHIRBoolean *activeElement;

@property (nonatomic, strong) NSNumber *active;

- (FHIRErrorList *)validate;

@end
