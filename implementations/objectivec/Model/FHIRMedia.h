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
 * A photo, video, or audio recording acquired or used in healthcare. The actual content may be inline or provided by direct reference
 *
 * [FhirResource("Media")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRCode;
@class FHIRCodeableConcept;
@class FHIRIdentifier;
@class FHIRDateTime;
@class FHIRResourceReference;
@class FHIRString;
@class FHIRInteger;
@class FHIRAttachment;

@interface FHIRMedia : FHIRResource

/*
 * Whether the Media is a photo, video, or audio
 */
typedef enum 
{
    kMediaTypePhoto, // The media consists of one or more unmoving images, including photographs, computer-generated graphs and charts, and scanned documents.
    kMediaTypeVideo, // The media consists of a series of frames that capture a moving image.
    kMediaTypeAudio, // The media consists of a sound recording.
} kMediaType;

/*
 * photo | video | audio
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *typeElement;

@property (nonatomic) kMediaType type;

/*
 * The type of acquisition equipment/process
 */
@property (nonatomic, strong) FHIRCodeableConcept *subtype;

/*
 * Identifier(s) for the image
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * When the media was taken/recorded (end)
 */
@property (nonatomic, strong) FHIRDateTime *dateTimeElement;

@property (nonatomic, strong) NSString *dateTime;

/*
 * Who/What this Media is a record of
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * The person who generated the image
 */
@property (nonatomic, strong) FHIRResourceReference *operator_;

/*
 * Imaging view e.g Lateral or Antero-posterior
 */
@property (nonatomic, strong) FHIRCodeableConcept *view;

/*
 * Name of the device/manufacturer
 */
@property (nonatomic, strong) FHIRString *deviceNameElement;

@property (nonatomic, strong) NSString *deviceName;

/*
 * Height of the image in pixels(photo/video)
 */
@property (nonatomic, strong) FHIRInteger *heightElement;

@property (nonatomic, strong) NSNumber *height;

/*
 * Width of the image in pixels (photo/video)
 */
@property (nonatomic, strong) FHIRInteger *widthElement;

@property (nonatomic, strong) NSNumber *width;

/*
 * Number of frames if > 1 (photo)
 */
@property (nonatomic, strong) FHIRInteger *framesElement;

@property (nonatomic, strong) NSNumber *frames;

/*
 * Length in seconds (audio / video)
 */
@property (nonatomic, strong) FHIRInteger *lengthElement;

@property (nonatomic, strong) NSNumber *length;

/*
 * Actual Media - reference or data
 */
@property (nonatomic, strong) FHIRAttachment *content;

- (FHIRErrorList *)validate;

@end
