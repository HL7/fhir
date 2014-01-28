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
 * A set of images produced in single study (one or more series of references images)
 *
 * [FhirResource("ImagingStudy")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRDateTime;
@class FHIRResourceReference;
@class FHIROid;
@class FHIRIdentifier;
@class FHIRCode;
@class FHIRUri;
@class FHIRInteger;
@class FHIRString;
@class FHIRCoding;
@class FHIRImagingStudySeriesComponent;

@interface FHIRImagingStudy : FHIRResource

/*
 * Type of acquired image data in the instance
 */
typedef enum 
{
    kImagingModalityAR,
    kImagingModalityBMD,
    kImagingModalityBDUS,
    kImagingModalityEPS,
    kImagingModalityCR,
    kImagingModalityCT,
    kImagingModalityDX,
    kImagingModalityECG,
    kImagingModalityES,
    kImagingModalityXC,
    kImagingModalityGM,
    kImagingModalityHD,
    kImagingModalityIO,
    kImagingModalityIVOCT,
    kImagingModalityIVUS,
    kImagingModalityKER,
    kImagingModalityLEN,
    kImagingModalityMR,
    kImagingModalityMG,
    kImagingModalityNM,
    kImagingModalityOAM,
    kImagingModalityOCT,
    kImagingModalityOPM,
    kImagingModalityOP,
    kImagingModalityOPR,
    kImagingModalityOPT,
    kImagingModalityOPV,
    kImagingModalityPX,
    kImagingModalityPT,
    kImagingModalityRF,
    kImagingModalityRG,
    kImagingModalitySM,
    kImagingModalitySRF,
    kImagingModalityUS,
    kImagingModalityVA,
    kImagingModalityXA,
} kImagingModality;

/*
 * Availability of the resource
 */
typedef enum 
{
    kInstanceAvailabilityONLINE, // Resources are immediately available,.
    kInstanceAvailabilityOFFLINE, // Resources need to be retrieved by manual intervention.
    kInstanceAvailabilityNEARLINE, // Resources need to be retrieved from relatively slow media.
    kInstanceAvailabilityUNAVAILABLE, // Resources cannot be retrieved.
} kInstanceAvailability;

/*
 * Type of data in the instance
 */
typedef enum 
{
    kModalityAR,
    kModalityAU,
    kModalityBDUS,
    kModalityBI,
    kModalityBMD,
    kModalityCR,
    kModalityCT,
    kModalityDG,
    kModalityDX,
    kModalityECG,
    kModalityEPS,
    kModalityES,
    kModalityGM,
    kModalityHC,
    kModalityHD,
    kModalityIO,
    kModalityIVOCT,
    kModalityIVUS,
    kModalityKER,
    kModalityKO,
    kModalityLEN,
    kModalityLS,
    kModalityMG,
    kModalityMR,
    kModalityNM,
    kModalityOAM,
    kModalityOCT,
    kModalityOP,
    kModalityOPM,
    kModalityOPT,
    kModalityOPV,
    kModalityOT,
    kModalityPR,
    kModalityPT,
    kModalityPX,
    kModalityREG,
    kModalityRF,
    kModalityRG,
    kModalityRTDOSE,
    kModalityRTIMAGE,
    kModalityRTPLAN,
    kModalityRTRECORD,
    kModalityRTSTRUCT,
    kModalitySEG,
    kModalitySM,
    kModalitySMR,
    kModalitySR,
    kModalitySRF,
    kModalityTG,
    kModalityUS,
    kModalityVA,
    kModalityXA,
    kModalityXC,
} kModality;

/*
 * When the study was performed
 */
@property (nonatomic, strong) FHIRDateTime *dateTimeElement;

@property (nonatomic, strong) NSString *dateTime;

/*
 * Who the images are of
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Formal identifier for the study (0020,000D)
 */
@property (nonatomic, strong) FHIROid *uidElement;

@property (nonatomic, strong) NSString *uid;

/*
 * Accession Number (0008,0050)
 */
@property (nonatomic, strong) FHIRIdentifier *accessionNo;

/*
 * Other identifiers for the study (0020,0010)
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * Order(s) that caused this study to be performed
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *order;

/*
 * All series.modality if actual acquisition modalities
 */
@property (nonatomic, strong) NSArray/*<code>*/ *modalityElement;

@property (nonatomic, strong) NSArray /*<kImagingModality>*/ *modality;

/*
 * Referring physician (0008,0090)
 */
@property (nonatomic, strong) FHIRResourceReference *referrer;

/*
 * ONLINE | OFFLINE | NEARLINE | UNAVAILABLE (0008,0056)
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *availabilityElement;

@property (nonatomic) kInstanceAvailability availability;

/*
 * Retrieve URI (0008,1190)
 */
@property (nonatomic, strong) FHIRUri *urlElement;

@property (nonatomic, strong) NSString *url;

/*
 * Number of Study Related Series (0020,1206)
 */
@property (nonatomic, strong) FHIRInteger *numberOfSeriesElement;

@property (nonatomic, strong) NSNumber *numberOfSeries;

/*
 * Number of Study Related Instances (0020,1208)
 */
@property (nonatomic, strong) FHIRInteger *numberOfInstancesElement;

@property (nonatomic, strong) NSNumber *numberOfInstances;

/*
 * Diagnoses etc with request (0040,1002)
 */
@property (nonatomic, strong) FHIRString *clinicalInformationElement;

@property (nonatomic, strong) NSString *clinicalInformation;

/*
 * Type of procedure performed (0008,1032)
 */
@property (nonatomic, strong) NSArray/*<Coding>*/ *procedure;

/*
 * Who interpreted images (0008,1060)
 */
@property (nonatomic, strong) FHIRResourceReference *interpreter;

/*
 * Institution-generated description (0008,1030)
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Each study has one or more series of instances
 */
@property (nonatomic, strong) NSArray/*<ImagingStudySeriesComponent>*/ *series;

- (FHIRErrorList *)validate;

@end
