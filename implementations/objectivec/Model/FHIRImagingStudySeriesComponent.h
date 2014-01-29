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
 * null
 *
 * [FhirComposite("ImagingStudySeriesComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRImagingStudy.h"

@class FHIRInteger;
@class FHIRCode;
@class FHIROid;
@class FHIRString;
@class FHIRUri;
@class FHIRCoding;
@class FHIRDateTime;
@class FHIRImagingStudySeriesInstanceComponent;

@interface FHIRImagingStudySeriesComponent : FHIRElement

/*
 * Number of this series in overall sequence (0020,0011)
 */
@property (nonatomic, strong) FHIRInteger *numberElement;

@property (nonatomic, strong) NSNumber *number;

/*
 * The modality of the instances in the series (0008,0060)
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *modalityElement;

@property (nonatomic) kModality modality;

/*
 * Formal identifier for this series (0020,000E)
 */
@property (nonatomic, strong) FHIROid *uidElement;

@property (nonatomic, strong) NSString *uid;

/*
 * A description of the series (0008,103E)
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Number of Series Related Instances (0020,1209)
 */
@property (nonatomic, strong) FHIRInteger *numberOfInstancesElement;

@property (nonatomic, strong) NSNumber *numberOfInstances;

/*
 * ONLINE | OFFLINE | NEARLINE | UNAVAILABLE (0008,0056)
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *availabilityElement;

@property (nonatomic) kInstanceAvailability availability;

/*
 * Retrieve URI (0008,1115 > 0008,1190)
 */
@property (nonatomic, strong) FHIRUri *urlElement;

@property (nonatomic, strong) NSString *url;

/*
 * Body part examined (Map from 0018,0015)
 */
@property (nonatomic, strong) FHIRCoding *bodySite;

/*
 * When the series started
 */
@property (nonatomic, strong) FHIRDateTime *dateTimeElement;

@property (nonatomic, strong) NSString *dateTime;

/*
 * A single instance taken from a patient (image or other)
 */
@property (nonatomic, strong) NSArray/*<ImagingStudySeriesInstanceComponent>*/ *instance;

- (FHIRErrorList *)validate;

@end
