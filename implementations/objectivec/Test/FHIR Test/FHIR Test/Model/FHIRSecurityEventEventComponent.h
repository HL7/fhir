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
 * [FhirComposite("SecurityEventEventComponent")]
 * [Serializable]
 */

#import "FHIRElement.h"

#import "FHIRSecurityEvent.h"

@class FHIRCodeableConcept;
@class FHIRCode;
@class FHIRInstant;
@class FHIRString;

@interface FHIRSecurityEventEventComponent : FHIRElement

/*
 * Type/identifier of event
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * More specific type/id for the event
 */
@property (nonatomic, strong) NSArray/*<CodeableConcept>*/ *subtype;

/*
 * Type of action performed during the event
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *actionElement;

@property (nonatomic) kSecurityEventAction action;

/*
 * Time when the event occurred on source
 */
@property (nonatomic, strong) FHIRInstant *dateTimeElement;

@property (nonatomic, strong) NSDate *dateTime;

/*
 * Whether the event succeeded or failed
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *outcomeElement;

@property (nonatomic) kSecurityEventOutcome outcome;

/*
 * Description of the event outcome
 */
@property (nonatomic, strong) FHIRString *outcomeDescElement;

@property (nonatomic, strong) NSString *outcomeDesc;

- (FHIRErrorList *)validate;

@end
