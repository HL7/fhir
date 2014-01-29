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
 * A response to an order
 *
 * [FhirResource("OrderResponse")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRResourceReference;
@class FHIRDateTime;
@class FHIRElement;
@class FHIRCode;
@class FHIRString;

@interface FHIROrderResponse : FHIRResource

/*
 * The status of the response to an order
 */
typedef enum 
{
    kOrderOutcomeStatusPending, // The order is known, but no processing has occurred at this time.
    kOrderOutcomeStatusReview, // The order is undergoing initial processing to determine whether it will be accepted (usually this involves human review).
    kOrderOutcomeStatusRejected, // The order was rejected because of a workflow/business logic reason.
    kOrderOutcomeStatusError, // The order was unable to be processed because of a technical error (i.e. unexpected error).
    kOrderOutcomeStatusAccepted, // The order has been accepted, and work is in progress.
    kOrderOutcomeStatusCancelled, // Processing the order was halted at the initiators request.
    kOrderOutcomeStatusReplaced, // The order has been cancelled and replaced by another.
    kOrderOutcomeStatusAborted, // Processing the order was stopped because of some workflow/business logic reason.
    kOrderOutcomeStatusComplete, // The order has been completed.
} kOrderOutcomeStatus;

/*
 * Identifiers assigned to this order by the orderer or by the receiver
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * The order that this is a response to
 */
@property (nonatomic, strong) FHIRResourceReference *request;

/*
 * When the response was made
 */
@property (nonatomic, strong) FHIRDateTime *dateElement;

@property (nonatomic, strong) NSString *date;

/*
 * Who made the response
 */
@property (nonatomic, strong) FHIRResourceReference *who;

/*
 * If required by policy
 */
@property (nonatomic, strong) FHIRElement *authority;

/*
 * pending | review | rejected | error | accepted | cancelled | replaced | aborted | complete
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *codeElement;

@property (nonatomic) kOrderOutcomeStatus code;

/*
 * Additional description of the response
 */
@property (nonatomic, strong) FHIRString *descriptionElement;

@property (nonatomic, strong) NSString *description;

/*
 * Details of the outcome of performing the order
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *fulfillment;

- (FHIRErrorList *)validate;

@end
