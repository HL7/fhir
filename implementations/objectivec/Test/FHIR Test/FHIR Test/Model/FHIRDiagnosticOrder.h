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
 * A request for a diagnostic service
 *
 * [FhirResource("DiagnosticOrder")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRResourceReference;
@class FHIRIdentifier;
@class FHIRString;
@class FHIRCode;
@class FHIRDiagnosticOrderEventComponent;
@class FHIRDiagnosticOrderItemComponent;

@interface FHIRDiagnosticOrder : FHIRResource

/*
 * The status of a diagnostic order
 */
typedef enum 
{
    kDiagnosticOrderStatusRequested, // The request has been placed.
    kDiagnosticOrderStatusReceived, // The receiving system has received the order, but not yet decided whether it will be performed.
    kDiagnosticOrderStatusAccepted, // The receiving system has accepted the order, but work has not yet commenced.
    kDiagnosticOrderStatusInProgress, // The work to fulfill the order is happening.
    kDiagnosticOrderStatusReview, // The work is complete, and the outcomes are being reviewed for approval.
    kDiagnosticOrderStatusCompleted, // The work has been complete, the report(s) released, and no further work is planned.
    kDiagnosticOrderStatusSuspended, // The request has been held by originating system/user request.
    kDiagnosticOrderStatusRejected, // The receiving system has declined to fulfill the request.
    kDiagnosticOrderStatusFailed, // The diagnostic investigation was attempted, but due to some procedural error, it could not be completed.
} kDiagnosticOrderStatus;

/*
 * The clinical priority of a diagnostic order
 */
typedef enum 
{
    kDiagnosticOrderPriorityRoutine, // The order has a normal priority.
    kDiagnosticOrderPriorityUrgent, // The order should be urgently.
    kDiagnosticOrderPriorityStat, // The order is time-critical.
    kDiagnosticOrderPriorityAsap, // The order should be acted on as soon as possible.
} kDiagnosticOrderPriority;

/*
 * Who/what test is about
 */
@property (nonatomic, strong) FHIRResourceReference *subject;

/*
 * Who ordered the test
 */
@property (nonatomic, strong) FHIRResourceReference *orderer;

/*
 * Identifiers assigned to this order
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * The encounter that this diagnostic order is associated with
 */
@property (nonatomic, strong) FHIRResourceReference *encounter;

/*
 * Explanation/Justification for test
 */
@property (nonatomic, strong) FHIRString *clinicalNotesElement;

@property (nonatomic, strong) NSString *clinicalNotes;

/*
 * If the whole order relates to specific specimens
 */
@property (nonatomic, strong) NSArray/*<ResourceReference>*/ *specimen;

/*
 * requested | received | accepted | in progress | review | completed | suspended | rejected | failed
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *statusElement;

@property (nonatomic) kDiagnosticOrderStatus status;

/*
 * routine | urgent | stat | asap
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *priorityElement;

@property (nonatomic) kDiagnosticOrderPriority priority;

/*
 * A list of events of interest in the lifecycle
 */
@property (nonatomic, strong) NSArray/*<DiagnosticOrderEventComponent>*/ *event_;

/*
 * The items the orderer requested
 */
@property (nonatomic, strong) NSArray/*<DiagnosticOrderItemComponent>*/ *item;

- (FHIRErrorList *)validate;

@end
