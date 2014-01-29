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
 * A resource that describes a message that is exchanged between systems
 *
 * [FhirResource("MessageHeader")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRId;
@class FHIRInstant;
@class FHIRCoding;
@class FHIRMessageHeaderResponseComponent;
@class FHIRMessageSourceComponent;
@class FHIRMessageDestinationComponent;
@class FHIRResourceReference;
@class FHIRCodeableConcept;

@interface FHIRMessageHeader : FHIRResource

/*
 * The kind of response to a message
 */
typedef enum 
{
    kResponseTypeOk, // The message was accepted and processed without error.
    kResponseTypeTransientError, // Some internal unexpected error occurred - wait and try again. Note - this is usually used for things like database unavailable, which may be expected to resolve, though human intervention may be required.
    kResponseTypeFatalError, // The message was rejected because of some content in it. There is no point in re-sending without change. The response narrative SHALL describe what the issue is.
} kResponseType;

/*
 * Id of this message
 */
@property (nonatomic, strong) FHIRId *identifierElement;

@property (nonatomic, strong) NSString *identifier;

/*
 * Time that the message was sent
 */
@property (nonatomic, strong) FHIRInstant *timestampElement;

@property (nonatomic, strong) NSDate *timestamp;

/*
 * Code for the event this message represents
 */
@property (nonatomic, strong) FHIRCoding *event_;

/*
 * If this is a reply to prior message
 */
@property (nonatomic, strong) FHIRMessageHeaderResponseComponent *response;

/*
 * Message Source Application
 */
@property (nonatomic, strong) FHIRMessageSourceComponent *source;

/*
 * Message Destination Application(s)
 */
@property (nonatomic, strong) NSArray/*<MessageDestinationComponent>*/ *destination;

/*
 * The source of the data entry
 */
@property (nonatomic, strong) FHIRResourceReference *enterer;

/*
 * The source of the decision
 */
@property (nonatomic, strong) FHIRResourceReference *author;

/*
 * Intended "real-world" recipient for the data
 */
@property (nonatomic, strong) FHIRResourceReference *receiver;

/*
 * Final responsibility for event
 */
@property (nonatomic, strong) FHIRResourceReference *responsible;

/*
 * Cause of event
 */
@property (nonatomic, strong) FHIRCodeableConcept *reason;

/*
 * The actual content of the message
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *data;

- (FHIRErrorList *)validate;

@end
