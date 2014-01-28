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
 * null
 *
 * [FhirComposite("ImagingStudySeriesInstanceComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRImagingStudy.h"

@class FHIRInteger;
@class FHIROid;
@class FHIRString;
@class FHIRUri;
@class FHIRResourceReference;

@interface FHIRImagingStudySeriesInstanceComponent : FHIRElement

/*
 * The number of this instance in the series (0020,0013)
 */
@property (nonatomic, strong) FHIRInteger *numberElement;

@property (nonatomic, strong) NSNumber *number;

/*
 * Formal identifier for this instance (0008,0018)
 */
@property (nonatomic, strong) FHIROid *uidElement;

@property (nonatomic, strong) NSString *uid;

/*
 * DICOM class type (0008,0016)
 */
@property (nonatomic, strong) FHIROid *sopclassElement;

@property (nonatomic, strong) NSString *sopclass;

/*
 * Type of instance (image etc) (0004,1430)
 */
@property (nonatomic, strong) FHIRString *typeElement;

@property (nonatomic, strong) NSString *type;

/*
 * Description (0070,0080 | 0040,A043 > 0008,0104 | 0042,0010 | 0008,0008)
 */
@property (nonatomic, strong) FHIRString *titleElement;

@property (nonatomic, strong) NSString *title;

/*
 * WADO-RS service where instance is available  (0008,1199 > 0008,1190)
 */
@property (nonatomic, strong) FHIRUri *urlElement;

@property (nonatomic, strong) NSString *url;

/*
 * A FHIR resource with content for this instance
 */
@property (nonatomic, strong) FHIRResourceReference *attachment;

- (FHIRErrorList *)validate;

@end
