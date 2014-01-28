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
 * null
 *
 * [FhirComposite("ConformanceMessagingEventComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRConformance.h"

@class FHIRCoding;
@class FHIRCode;
@class FHIRResourceReference;
@class FHIRString;

@interface FHIRConformanceMessagingEventComponent : FHIRElement

/*
 * Event type
 */
@property (nonatomic, strong) FHIRCoding *code;

/*
 * Consequence | Currency | Notification
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *categoryElement;

@property (nonatomic) kMessageSignificanceCategory category;

/*
 * sender | receiver
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *modeElement;

@property (nonatomic) kConformanceEventMode mode;

/*
 * http | ftp | mllp +
 */
@property (nonatomic, strong) NSArray/*<Coding>*/ *protocol;

/*
 * Resource that's focus of message
 */
@property (nonatomic, strong) FHIRCode *focusElement;

@property (nonatomic, strong) NSString *focus;

/*
 * Profile that describes the request
 */
@property (nonatomic, strong) FHIRResourceReference *request;

/*
 * Profile that describes the response
 */
@property (nonatomic, strong) FHIRResourceReference *response;

/*
 * Endpoint-specific event documentation
 */
@property (nonatomic, strong) FHIRString *documentationElement;

@property (nonatomic, strong) NSString *documentation;

- (FHIRErrorList *)validate;

@end
