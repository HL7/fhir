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
 * (informative) A slot of time that may be available for booking appointments
 *
 * [FhirResource("Slot")]
 * [Serializable]
 */

#import "FHIRResource.h"


@class FHIRIdentifier;
@class FHIRCodeableConcept;
@class FHIRResourceReference;
@class FHIRCode;
@class FHIRInstant;
@class FHIRString;
@class FHIRDateTime;

@interface FHIRSlot : FHIRResource

/*
 * The free/busy status of an appointment
 */
typedef enum 
{
    kSlotStatusBUSY, // Indicates that the time interval is busy because one  or more events have been scheduled for that interval.
    kSlotStatusFREE, // Indicates that the time interval is free for scheduling.
    kSlotStatusBUSYUNAVAILABLE, // Indicates that the time interval is busy and that the interval can not be scheduled.
    kSlotStatusBUSYTENTATIVE, // Indicates that the time interval is busy because one or more events have been tentatively scheduled for that interval.
} kSlotStatus;

/*
 * External Ids for this item
 */
@property (nonatomic, strong) NSArray/*<Identifier>*/ *identifier;

/*
 * The type of appointments that can be booked into this slot (ideally this would be an identifiable service - which is at a location, rather than the location itself). If provided then this overrides the value provided on the availability resource
 */
@property (nonatomic, strong) FHIRCodeableConcept *type;

/*
 * The availability resource that this slot defines an interval of status information
 */
@property (nonatomic, strong) FHIRResourceReference *availability;

/*
 * BUSY | FREE | BUSY-UNAVAILABLE | BUSY-TENTATIVE
 */
@property (nonatomic, strong) FHIRCode/*<code>*/ *freeBusyTypeElement;

@property (nonatomic) kSlotStatus freeBusyType;

/*
 * Date/Time that the slot is to begin
 */
@property (nonatomic, strong) FHIRInstant *startElement;

@property (nonatomic, strong) NSDate *start;

/*
 * Date/Time that the slot is to conclude
 */
@property (nonatomic, strong) FHIRInstant *endElement;

@property (nonatomic, strong) NSDate *end;

/*
 * Comments on the slot to describe any extended information. Such as custom constraints on the slot
 */
@property (nonatomic, strong) FHIRString *commentElement;

@property (nonatomic, strong) NSString *comment;

/*
 * Who authored the slot
 */
@property (nonatomic, strong) FHIRResourceReference *author;

/*
 * When this slot was created, or last revised
 */
@property (nonatomic, strong) FHIRDateTime *authorDateElement;

@property (nonatomic, strong) NSString *authorDate;

- (FHIRErrorList *)validate;

@end
