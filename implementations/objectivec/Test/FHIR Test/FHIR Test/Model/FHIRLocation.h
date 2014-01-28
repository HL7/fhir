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
 * Details and position information for a physical place
 *
 * [FhirResource("Location")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRString;
@class FHIRCodeableConcept;
@class FHIRContact;
@class FHIRAddress;
@class FHIRLocationPositionComponent;
@class FHIRResourceReference;
@class FHIRCode;

@interface FHIRLocation : FHIRResource

/*
 * Indicates whether the location is still in use
 */
typedef enum 
{
    kLocationStatusActive, // The location is operational.
    kLocationStatusSuspended, // The location is temporarily closed.
    kLocationStatusInactive, // The location is no longer used.
} kLocationStatus;

/*
 * Indicates whether a resource instance represents a specific location or a class of locations
 */
typedef enum 
{
    kLocationModeInstance, // The Location resource represents a specific instance of a Location.
    kLocationModeKind, // The Location represents a class of Locations.
} kLocationMode;

/*
 * Unique code or number identifying the location to its users
 */
@property (nonatomic, strong) FHIRIdentifier *identifier;

/*
 * Name of the location as used by humans
 */
@property (nonatomic, strong) FHIRString *nameElement;

@property (nonatomic, strong) NSString *name;

/*
 * Description of the Location, which helps in finding or referencing the place
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Indicates the type of function performed at the location
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * Contact details of the location
 */
@property (nonatomic, strong) FHIRContact *telecom;

/*
 * Physical location
 */
@property (nonatomic, strong) FHIRAddress *address;

/*
 * Physical form of the location
 */
@property (nonatomic, strong) FHIRCodeableConcept *physicalType;

/*
 * The absolute geographic location
 */
@property (nonatomic, strong) FHIRLocationPositionComponent *position;

/*
 * The organization that is responsible for the provisioning and upkeep of the location
 */
@property (nonatomic, strong) FHIRResourceReference *managingOrganization;

/*
 * active | suspended | inactive
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kLocationStatus status;

/*
 * Another Location which this Location is physically part of
 */
@property (nonatomic, strong) FHIRResourceReference *partOf;

/*
 * instance | kind
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *modeElement;

@property (nonatomic) kLocationMode mode;

- (FHIRErrorList *)validate;

@end
